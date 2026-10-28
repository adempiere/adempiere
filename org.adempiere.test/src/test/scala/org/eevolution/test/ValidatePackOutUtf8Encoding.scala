/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.eevolution.test

import org.adempiere.Adempiere
import org.adempiere.core.domains.models.X_AD_Package_Exp_Detail
import org.adempiere.pipo.PackOut
import org.compiere.model.{MPackageExp, MPInstance, MProcess, MTable}
import org.compiere.process.ProcessInfo
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterAll
import org.scalatest.featurespec.AnyFeatureSpec

import java.nio.charset.{Charset, CodingErrorAction}
import java.nio.file.{Files, Path}
import java.util.logging.Level

/**
  * Regression test for https://github.com/adempiere/adempiere/issues/4199
  *
  * PackOut used to write the package documentation and the application
  * dictionary XML files with ISO-8859-1. The exported packout files must
  * use UTF-8, since the application dictionary can contain characters
  * outside ISO-8859-1 (accents, CJK, ...).
  *
  * The test exports a package whose description contains characters that
  * cannot be represented in ISO-8859-1, runs the real PackOut process and
  * asserts that both generated XML files:
  *   - declare `encoding="UTF-8"` in the XML declaration,
  *   - are valid UTF-8 byte streams,
  *   - keep the non ISO-8859-1 characters intact.
  *
  * With the pre-fix ISO-8859-1 implementation this test fails on the
  * encoding declaration and on the mangled description.
  */
class ValidatePackOutUtf8Encoding extends AnyFeatureSpec
  with BeforeAndAfterAll
  with GivenWhenThen {

  private val trxName = Trx.createTrxName(getClass.getName + "_")

  private val uniqueName = "TestPackOutUTF8_" + System.currentTimeMillis()

  // Contains characters outside ISO-8859-1 (CJK), so the old ISO-8859-1
  // writer would replace them and the export would be corrupted.
  private val description =
    "Regression adempiere#4199: Exportación UTF-8 con caracteres 测试回归 fuera de ISO-8859-1"

  private var outputDir: Path = _

  override def beforeAll(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)

    Ini.setProperty(Ini.P_UID, "SuperUser")
    Ini.setProperty(Ini.P_PWD, "System")
    Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG, "HQ")
    Ini.setProperty(Ini.P_WAREHOUSE, "HQ Warehouse")
    Ini.setProperty(Ini.P_LANGUAGE, "English")
    val login = new Login(Env.getCtx)
    if (!login.batchLogin(null))
      fail("Login failed for " + s"${Ini.getProperty(Ini.P_UID)} / " +
        s"${Ini.getProperty(Ini.P_ROLE)} @ " +
        s"${Ini.getProperty(Ini.P_CLIENT)}/${Ini.getProperty(Ini.P_ORG)}: " +
        "aborting the suite instead of killing the JVM, so the runner can report it")
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
  }

  override def afterAll(): Unit = {
    val trx: Trx = Trx.get(trxName, false)
    if (trx != null && trx.isActive) {
      trx.rollback
      trx.close
    }
    if (outputDir != null) {
      deleteRecursively(outputDir)
    }
  }

  private def clientID: Int =
    Env.getContextAsInt(Env.getCtx, "#AD_Client_ID")

  private def findProcessID(): Int =
    DB.getSQLValue(trxName, "SELECT AD_Process_ID FROM AD_Process WHERE Name = 'PackOut' AND IsActive = 'Y'")

  private def findTableId(): Int =
    MTable.get(Env.getCtx, "AD_Package_Exp").get_ID

  private def createExportPackage(): MPackageExp = {
    outputDir = Files.createTempDirectory("packoutUtf8Test_")
    val packageExp = new MPackageExp(Env.getCtx, 0, trxName)
    packageExp.setName(uniqueName)
    packageExp.setIsActive(true)
    packageExp.setDescription(description)
    packageExp.setEMail("regression@example.com")
    packageExp.setUserName("regression@example.com")
    packageExp.setFile_Directory(outputDir.toAbsolutePath.toString + java.io.File.separator)
    packageExp.setInstructions("Regression test for adempiere#4199: PackOut must export UTF-8")
    packageExp.setReleaseNo(X_AD_Package_Exp_Detail.RELEASENO_NoSpecificRelease)
    packageExp.setVersion("1.0")
    packageExp.setPK_Version("1.0")
    packageExp.save()

    val packDetail = new X_AD_Package_Exp_Detail(Env.getCtx, 0, trxName)
    packDetail.setAD_Org_ID(packageExp.getAD_Org_ID)
    packDetail.setAD_Package_Exp_ID(packageExp.get_ID)
    packDetail.setType(X_AD_Package_Exp_Detail.TYPE_SQLStatement)
    packDetail.setDBType("ALL")
    packDetail.setSQLStatement("select * from ad_table")
    packDetail.setDescription("2pack test sql statement")
    packDetail.setLine(10)
    packDetail.save()

    packageExp
  }

  private def readStrictUtf8(path: Path): String = {
    val bytes = Files.readAllBytes(path)
    val decoder = Charset.forName("UTF-8")
      .newDecoder()
      .onMalformedInput(CodingErrorAction.REPORT)
      .onUnmappableCharacter(CodingErrorAction.REPORT)
    decoder.decode(java.nio.ByteBuffer.wrap(bytes)).toString
  }

  private def assertXmlDeclarationIsUtf8(path: Path): Unit = {
    val content = readStrictUtf8(path)
    val head = if (content.length > 300) content.substring(0, 300) else content
    val headLines = head.lines().limit(2).toArray().mkString(" | ")
    assert(head.contains("encoding=\"UTF-8\""),
      s"${path} does not declare UTF-8 in its XML declaration: $headLines")
  }

  private def deleteRecursively(path: Path): Unit = {
    if (!Files.exists(path)) return
    // Files.walk opens a directory stream; it must be closed even when a
    // deletion fails, otherwise the handle leaks and can block removal.
    val walker = Files.walk(path)
    try {
      walker.sorted(java.util.Comparator.reverseOrder())
        .forEach(p => Files.deleteIfExists(p))
    } finally {
      walker.close()
    }
  }

  Feature("PackOut exports the package documentation and the application dictionary in UTF-8") {

    Scenario("both exported XML files declare UTF-8 and keep non ISO-8859-1 characters intact") {
      Given("an export package with a description containing characters outside ISO-8859-1")
      val packageExp = createExportPackage()

      val tableId = findTableId()
      val processId = findProcessID()
      val processInfo = new ProcessInfo("PackOut", processId, tableId, packageExp.get_ID)
      val instance = new MPInstance(new MProcess(Env.getCtx, processId, trxName), packageExp.get_ID)
      instance.saveEx()
      processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID)

      When("the PackOut process runs")
      val packOut = new PackOut()
      packOut.startProcess(Env.getCtx, processInfo, Trx.get(trxName, false))
      instance.load(trxName)

      Then("the process completes without error")
      assert(!processInfo.isError, "PackOut process reported error")

      val packageDir = outputDir.resolve(packageExp.getName())
      val docXml = packageDir.resolve("doc").resolve(packageExp.getName() + "Doc.xml")
      val dictXml = packageDir.resolve("dict").resolve("PackOut.xml")
      assert(Files.exists(docXml), s"Package documentation XML was not generated: $docXml")
      assert(Files.exists(dictXml), s"Application dictionary XML was not generated: $dictXml")

      And("both XML files declare UTF-8 encoding and are valid UTF-8")
      assertXmlDeclarationIsUtf8(docXml)
      assertXmlDeclarationIsUtf8(dictXml)

      And("the package documentation keeps the non ISO-8859-1 characters intact")
      assert(readStrictUtf8(docXml).contains(description),
        "Package documentation XML does not contain the original description; " +
          "characters outside ISO-8859-1 were corrupted")

      And("the application dictionary keeps the non ISO-8859-1 characters intact")
      // PackOut writes the package description as the Description attribute of
      // the adempiereAD root element of dict/PackOut.xml. Without this check a
      // dictionary writer could declare UTF-8 while replacing the CJK
      // characters and the suite would still pass.
      assert(readStrictUtf8(dictXml).contains(description),
        "Application dictionary XML does not contain the original description; " +
          "characters outside ISO-8859-1 were corrupted")
    }
  }
}

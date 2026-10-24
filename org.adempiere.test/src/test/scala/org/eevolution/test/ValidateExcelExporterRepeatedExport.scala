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

import java.io.FileInputStream

import org.adempiere.impexp.AbstractExcelExporter
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.compiere.util.DisplayType
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for PR #4427 (CodeRabbit review finding): the exporter now
  * re-initializes its workbook on every public export() call, so a repeated
  * export on the same instance must start with fresh per-workbook state.
  *
  * Without the reset in initWorkbook(), the cached fonts still belong to the
  * workbook that was just disposed and the sheet counter keeps growing: on the
  * second export, closeTableSheet() would call setSheetName(sheetCount - 1, ...)
  * past the last sheet of the new workbook, the exception gets swallowed and
  * the sheet name from the page-break cell is silently lost.
  *
  * The spec writes two files with one exporter instance and verifies the second
  * file keeps the same structure: two sheets, the page-break sheet name applied,
  * a bold header row, and the post-break data in the second sheet.
  */
class ValidateExcelExporterRepeatedExport extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen {

  private val pageBreakRow = 1
  private val pageBreakSheetName = "Second sheet"

  /**
    * Deterministic exporter: two printed columns, three data rows, and a page
    * break on row 1 column 0. The value of the page-break cell becomes the name
    * of the sheet that closes, so the sheet name doubles as the regression
    * signal: if the sheet counter is not reset, the second export loses it.
    */
  private class ReexportTestExporter extends AbstractExcelExporter {
    override def isFunctionRow(): Boolean = false
    override def getColumnCount(): Int = 2
    override def getRowCount(): Int = 3
    protected override def setCurrentRow(row: Int): Unit = ()
    override def isColumnPrinted(col: Int): Boolean = true
    override def getHeaderName(col: Int): String = s"Header ${col + 1}"
    override def getFormatPattern(col: Int): String = null
    override def getDisplayType(row: Int, col: Int): Int = DisplayType.String
    override def getValueAt(row: Int, col: Int): Object =
      if (row == pageBreakRow && col == 0) pageBreakSheetName else s"r${row}c${col}"
    override def isPageBreak(row: Int, col: Int): Boolean =
      row == pageBreakRow && col == 0
  }

  /** Reads the written file back as an OOXML workbook and returns it to the caller for assertions. */
  private def readWorkbook(file: java.io.File): XSSFWorkbook =
    new XSSFWorkbook(new FileInputStream(file))

  private def assertExportStructure(workbook: XSSFWorkbook, exportNumber: Int): Unit = {
    assert(workbook.getNumberOfSheets == 2,
      s"export $exportNumber: expected two sheets (page break after row $pageBreakRow), got ${workbook.getNumberOfSheets}")
    assert(workbook.getSheetName(0) == pageBreakSheetName,
      s"export $exportNumber: first sheet must be named from the page-break cell, got '${workbook.getSheetName(0)}'")
    val headerCell = workbook.getSheetAt(0).getRow(0).getCell(0)
    assert(headerCell != null, s"export $exportNumber: header row is missing")
    assert(headerCell.getStringCellValue == "Header 1",
      s"export $exportNumber: unexpected header value '${headerCell.getStringCellValue}'")
    val headerFont = workbook.getFontAt(headerCell.getCellStyle.getFontIndex)
    assert(headerFont.getBold, s"export $exportNumber: header font must be bold")
    val afterBreakRow = workbook.getSheetAt(1).getRow(1)
    assert(afterBreakRow != null && afterBreakRow.getCell(0) != null,
      s"export $exportNumber: post-break data row is missing in the second sheet")
    assert(afterBreakRow.getCell(0).getStringCellValue == "r2c0",
      s"export $exportNumber: unexpected post-break value '${afterBreakRow.getCell(0).getStringCellValue}'")
  }

  Feature("AbstractExcelExporter repeated export state") {
    info("PR #4427: a second export on the same instance must produce a complete, correctly named workbook")

    Scenario("The exporter reuses one instance for two exports") {
      Given("an exporter with a page break whose cell text names the sheet")
      val exporter = new ReexportTestExporter()

      When("the first export is written to a file")
      val firstFile = exporter.`export`() 
      val firstWorkbook = readWorkbook(firstFile)

      Then("the first file has the expected structure")
      assertExportStructure(firstWorkbook, 1)
      firstWorkbook.close()

      When("the same instance performs a second export")
      val secondFile = exporter.`export`() 
      assert(!secondFile.getCanonicalPath.equals(firstFile.getCanonicalPath),
        "the second export must write a new file")
      val secondWorkbook = readWorkbook(secondFile)

      Then("the second file is a fresh workbook with the same structure and sheet name")
      assertExportStructure(secondWorkbook, 2)
      secondWorkbook.close()
    }
  }
}

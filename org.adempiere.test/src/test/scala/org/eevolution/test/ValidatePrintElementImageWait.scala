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
  * ****************************************************************************/
package org.eevolution.test

import java.awt.{Color, Graphics2D, Toolkit}
import java.awt.geom.Point2D
import java.awt.image.{BufferedImage, ImageObserver}
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.Properties
import javax.imageio.ImageIO

import org.compiere.print.layout.PrintElement
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * PrintElement.waitForLoad must not hold a print for its timeout (about 22 s) when the image cannot be loaded.
  * Example: a product image URL that now redirects to an HTML page makes AWT report ERROR and ABORT.
  * An image that loads is still waited for, and the result of imageUpdate keeps its meaning.
  */
class ValidatePrintElementImageWait extends AnyFeatureSpec with GivenWhenThen {

  /** Short bound; the old behavior waited about 22 s for an image that failed. */
  private val FailedImageBoundMillis = 5000L

  private class Element extends PrintElement {
    override protected def calculateSize(): Boolean = true
    override def paint(g2D: Graphics2D, pageNo: Int, pageStart: Point2D, context: Properties, isView: Boolean): Unit = ()
  }

  private def withTempFile[T](suffix: String)(write: File => Unit)(body: File => T): T = {
    val file = File.createTempFile("printelement-image", suffix)
    try {
      write(file)
      body(file)
    } finally file.delete()
  }

  private def writeNotAnImage(file: File): Unit =
    Files.write(file.toPath, "<html><body>moved</body></html>".getBytes(StandardCharsets.UTF_8))

  private def writePng(file: File): Unit = {
    val image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()
    graphics.setColor(Color.RED)
    graphics.fillRect(0, 0, 8, 8)
    graphics.dispose()
    assert(ImageIO.write(image, "png", file))
  }

  private def timed[T](body: => T): (T, Long) = {
    val start = System.currentTimeMillis()
    val result = body
    (result, System.currentTimeMillis() - start)
  }

  Feature("PrintElement.waitForLoad") {

    Scenario("an image that fails to load returns false as soon as it fails") {
      Given("a file with bytes that are not an image")
      withTempFile(".png")(writeNotAnImage) { file =>
        val image = Toolkit.getDefaultToolkit.getImage(file.getAbsolutePath)
        When("the element waits for the image")
        val (loaded, elapsed) = timed(new Element().waitForLoad(image))
        Then("the result is not loaded and it did not wait for the timeout")
        assert(!loaded, "an image that failed must not be reported as loaded")
        assert(elapsed < FailedImageBoundMillis, s"waited ${elapsed}ms for an image that failed")
      }
    }

    Scenario("an image that loads is waited for and reported as loaded") {
      Given("a PNG written with ImageIO")
      withTempFile(".png")(writePng) { file =>
        val image = Toolkit.getDefaultToolkit.getImage(file.getAbsolutePath)
        When("the element waits for the image")
        val (loaded, elapsed) = timed(new Element().waitForLoad(image))
        Then("the result is loaded with its size known")
        assert(loaded)
        assert(elapsed < FailedImageBoundMillis, s"took ${elapsed}ms")
        assert(image.getWidth(null) == 8 && image.getHeight(null) == 8)
      }
    }

    Scenario("a failed image does not poison the next wait of the same element") {
      Given("one element, first with a failing image and then with a valid one")
      val element = new Element()
      withTempFile(".png")(writeNotAnImage) { bad =>
        withTempFile(".png")(writePng) { good =>
          When("the element waits for the failing image and then for the valid one")
          val failed = element.waitForLoad(Toolkit.getDefaultToolkit.getImage(bad.getAbsolutePath))
          val loaded = element.waitForLoad(Toolkit.getDefaultToolkit.getImage(good.getAbsolutePath))
          Then("the first is not loaded and the second is loaded")
          assert(!failed)
          assert(loaded)
        }
      }
    }

    Scenario("imageUpdate keeps asking for updates until the image is complete or aborted") {
      val element = new Element()
      Then("a partial image keeps the observer, a complete or aborted one releases it")
      assert(element.imageUpdate(null, ImageObserver.WIDTH | ImageObserver.HEIGHT, 0, 0, 8, 8))
      assert(!element.imageUpdate(null, ImageObserver.ALLBITS, 0, 0, 8, 8))
      assert(!element.imageUpdate(null, ImageObserver.ERROR | ImageObserver.ABORT, 0, 0, 0, 0))
    }
  }
}

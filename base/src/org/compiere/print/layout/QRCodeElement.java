/**
 * Copyright (C) 2003-2019, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */


package org.compiere.print.layout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.compiere.print.MPrintFormatItem;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * Barcode Print Element
 * author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class QRCodeElement extends PrintElement {

    /**
     * Valid
     */
    private boolean isValid = true;
    /**
     * QR Image
     */
    private BufferedImage qrImage = null;
    /**
     * QR Value
     */
    private String qrCode = null;
    /**
     * Allow this field to overflow over next fields
     */
    private boolean allowOverflow = true;
    private float scaleFactor = 1;

    /**
     * QR Code Element Constructor
     *
     * @param code barcode data string
     * @param item format item
     */
    public QRCodeElement(String code, MPrintFormatItem item) {
        super();
        qrCode = code;
        if (qrCode == null || qrCode.length() == 0
                || item == null
                || item.getBarcodeType() == null || item.getBarcodeType().length() == 0)
            isValid = false;

        createQR(code, item);
        if (qrImage == null)
            isValid = false;
        allowOverflow = item.isHeightOneLine();
    }    //	QRCodeElement


    /**
     * Create QRCode
     *
     * @param code data string
     * @param item print format item
     */
    private void createQR(String code, MPrintFormatItem item) {
        String type = item.getBarcodeType();
        try {

            if (type.equals(MPrintFormatItem.BARCODETYPE_QRQuickResponseCode)) {
                if (item.getMaxWidth() <= 0 && item.getMaxHeight() <= 0) {
                    qrImage = null;
                    isValid = false;
                }
                try {
                    QRCodeWriter qr = new QRCodeWriter();
                    BitMatrix byteMatrix = qr.encode(code, BarcodeFormat.QR_CODE, item.getMaxWidth(), item.getMaxHeight());
                    qrImage = new BufferedImage(item.getMaxWidth(), item.getMaxHeight(), BufferedImage.TYPE_INT_RGB); // create an empty image
                    int white = 255 << 16 | 255 << 8 | 255;
                    int black = 0;
                    for (int i = 0; i < item.getMaxWidth(); i++) {
                        for (int j = 0; j < item.getMaxHeight(); j++) {
                            qrImage.setRGB(i, j, byteMatrix.get(i, j) ? black : white);
                        }
                    }
                } catch (WriterException e) {
                    log.warning("Invalid Type" + type);
                }
            } else
                log.warning("Invalid Type" + type);
        } catch (Exception e) {
            log.warning(code + " - " + e.toString());
            isValid = false;
        }
    }    //	createBarcode


    /**
     * Get Barcode
     *
     * @return Barcode
     */
    public Image getQR() {
        return qrImage;
    }    //	get QR Image

    /**
     * Is QR Code Valid
     *
     * @return true if valid
     */
    public boolean isValid() {
        return isValid;
    }    //	isValid

    /**
     * Layout and Calculate Size
     * Set p_width & p_height
     *
     * @return true if calculated
     */
    protected boolean calculateSize() {
        p_width = 0;
        p_height = 0;
        if (qrImage == null)
            return true;

        p_width = qrImage.getWidth();
        p_height = qrImage.getHeight();

        if (p_width * p_height == 0)
            return true;    //	don't bother scaling and prevent div by 0

        scaleFactor = 1f;
        if (p_maxWidth != 0 && p_width > p_maxWidth)
            scaleFactor = p_maxWidth / p_width;
        if (p_maxHeight != 0 && p_height > p_maxHeight && p_maxHeight / p_height < scaleFactor)
            scaleFactor = p_maxHeight / p_height;

        p_width = (float) scaleFactor * p_width;
        p_height = (float) scaleFactor * p_height;

        return true;
    }    //	calculateSize

    public float getScaleFactor() {
        if (!p_sizeCalculated)
            p_sizeCalculated = calculateSize();
        return scaleFactor;
    }

    /**
     * @return can this element overflow over the next fields
     */
    public boolean isAllowOverflow() { //
        return allowOverflow;
    }

    /**
     * Paint Image
     *
     * @param g2D       Graphics
     * @param pageStart top left Location of page
     * @param pageNo    page number for multi page support (0 = header/footer) - ignored
     * @param ctx       print context
     * @param isView    true if online view (IDs are links)
     */
    public void paint(Graphics2D g2D, int pageNo, Point2D pageStart, Properties ctx, boolean isView) {
        if (qrImage == null)
            return;
        //	Position
        Point2D.Double location = getAbsoluteLocation(pageStart);
        int x = (int) location.x;
        if (MPrintFormatItem.FIELDALIGNMENTTYPE_TrailingRight.equals(p_FieldAlignmentType))
            x += p_maxWidth - p_width;
        else if (MPrintFormatItem.FIELDALIGNMENTTYPE_Center.equals(p_FieldAlignmentType))
            x += (p_maxWidth - p_width) / 2;
        int y = (int) location.y;

        // 	map a scaled and shifted version of the image to device space
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        transform.scale(scaleFactor, scaleFactor);
        g2D.drawImage(qrImage, transform, this);
    }    //	paint

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return qrCode;
    }    //	toString

}    //	QR Code Element

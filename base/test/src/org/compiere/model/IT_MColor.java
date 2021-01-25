/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MColor")
class IT_MColor extends CommonGWSetup {

    private MColor clr;

    @BeforeEach
    void localSetup() {

        clr = new MColor(getCtx(), 0, trxName);

    }

    @Test
    void ifNew_NameIsSet() {

        assertEquals("-/-", clr.getName(), "Name not set as expected");

    }

    @Test
    void ifSavedWithNoColorType_ThrowsException() {

        assertThrows(AdempiereException.class, () -> {
            clr.saveEx();
        });

    }

    @Test
    void ifSavedWithNoColorInfo_ThrowsException() {

        clr.setColorType(MColor.COLORTYPE_NormalFlat);
        assertThrows(AdempiereException.class, () -> {
            clr.saveEx();
        });

    }

    @Test
    void ifSavedWithNoImageAlpha_ThrowsException() {

        clr.setColorType(MColor.COLORTYPE_NormalFlat);
        clr.setRed(0);
        clr.setGreen(0);
        clr.setBlue(0);
        clr.setAlpha(0);
        assertThrows(AdempiereException.class, () -> {
            clr.saveEx();
        });

    }

    @Test
    void canSave() {

        clr.setName("new");
        clr.setColorType(MColor.COLORTYPE_NormalFlat);
        clr.setRed(0);
        clr.setGreen(0);
        clr.setBlue(0);
        clr.setAlpha(0);
        clr.setImageAlpha(Env.ZERO);
        clr.saveEx();
        int id = clr.getAD_Color_ID();

        MColor newClr = new MColor(ctx, id, trxName);

        assertEquals(id, newClr.getAD_Color_ID(), "Did not recover the id as expected");
        assertEquals("new", newClr.getName(), "Did not save the name");

    }

    @Test
    void getAdempiereColor_returnsNullIfIdNotSaved() {

        assertNull(clr.getAdempiereColor(), "Did not return null when id == 0");

    }

    @Test
    void getAdempiereColor_returnsNullIfColorTypeIsNull() {

        clr.setAD_Color_ID(1);
        assertNull(clr.getAdempiereColor(), "Did not return null when colorType == null");

    }

    @Test
    void getAdempiereColor_returnsNullIfTextureButNoImage() {

        clr.setAD_Color_ID(1);
        clr.setColorType(MColor.COLORTYPE_TexturePicture);
        assertNull(clr.getAdempiereColor(), "Did not return null when texture has no image id");

    }

    @Test
    void getAdempiereColor_returnsNullWhenImageURLIsNull() {

        MImage image = new MImage(ctx, 0, trxName);
        image.setImageURL(null);
        image.saveEx();

        clr.setAD_Color_ID(1);
        clr.setColorType(MColor.COLORTYPE_TexturePicture);
        clr.setAD_Image_ID(image.getAD_Image_ID());

        assertNull(clr.getAdempiereColor(), "Did not return null when texture has no URL");

    }

    @Test
    void getAdempiereColor_returnsColorWhenImageURLIsNotNull() {

        MImage image = new MImage(ctx, 0, trxName);
        image.setImageURL("ImageURL");
        image.saveEx();

        clr.setAD_Color_ID(1);
        clr.setColorType(MColor.COLORTYPE_TexturePicture);
        clr.setAD_Image_ID(image.getAD_Image_ID());

        assertNotNull(clr.getAdempiereColor(), "Should return AdempiereColor object");

    }

}

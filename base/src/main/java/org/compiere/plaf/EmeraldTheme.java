/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.plaf;

/*
 * @(#)EmeraldTheme.java	1.9 04/07/26
 */
 
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * This class describes a theme using glowing green colors.
 *
 * @version 1.9 07/26/04
 * @author Jeff Dinkins
 */
public class EmeraldTheme extends DefaultMetalTheme {

    public String getName()
    {
    	return "Emerald"; 
    }

    private final ColorUIResource primary1 = new ColorUIResource(51, 142, 71);
    private final ColorUIResource primary2 = new ColorUIResource(102, 193, 122);
    private final ColorUIResource primary3 = new ColorUIResource(153, 244, 173); 

    protected ColorUIResource getPrimary1 ()
	{
		return primary1;
	}

	protected ColorUIResource getPrimary2 ()
	{
		return primary2;
	}

	protected ColorUIResource getPrimary3 ()
	{
		return primary3;
	} 

}

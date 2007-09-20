/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_Menu
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.0
 */
public interface I_U_Menu 
{

    /** TableName=U_Menu */
    public static final String Table_Name = "U_Menu";

    /** AD_Table_ID=52003 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Category */
    public static final String COLUMNNAME_Category = "Category";

	/** Set Category	  */
	public void setCategory (String Category);

	/** Get Category	  */
	public String getCategory();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description	  */
	public void setDescription (String Description);

	/** Get Description	  */
	public String getDescription();

    /** Column name HasSubMenu */
    public static final String COLUMNNAME_HasSubMenu = "HasSubMenu";

	/** Set HasSubMenu	  */
	public void setHasSubMenu (boolean HasSubMenu);

	/** Get HasSubMenu	  */
	public boolean isHasSubMenu();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Help	  */
	public void setHelp (String Help);

	/** Get Help	  */
	public String getHelp();

    /** Column name ImageLink */
    public static final String COLUMNNAME_ImageLink = "ImageLink";

	/** Set ImageLink	  */
	public void setImageLink (String ImageLink);

	/** Get ImageLink	  */
	public String getImageLink();

    /** Column name MenuLink */
    public static final String COLUMNNAME_MenuLink = "MenuLink";

	/** Set MenuLink	  */
	public void setMenuLink (String MenuLink);

	/** Get MenuLink	  */
	public String getMenuLink();

    /** Column name Module */
    public static final String COLUMNNAME_Module = "Module";

	/** Set Module	  */
	public void setModule (String Module);

	/** Get Module	  */
	public String getModule();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name	  */
	public void setName (String Name);

	/** Get Name	  */
	public String getName();

    /** Column name ParentMenu_ID */
    public static final String COLUMNNAME_ParentMenu_ID = "ParentMenu_ID";

	/** Set ParentMenu_ID	  */
	public void setParentMenu_ID (int ParentMenu_ID);

	/** Get ParentMenu_ID	  */
	public int getParentMenu_ID();

    /** Column name Position */
    public static final String COLUMNNAME_Position = "Position";

	/** Set Position	  */
	public void setPosition (String Position);

	/** Get Position	  */
	public String getPosition();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

    /** Column name U_Menu_ID */
    public static final String COLUMNNAME_U_Menu_ID = "U_Menu_ID";

	/** Set U_Menu_ID	  */
	public void setU_Menu_ID (int U_Menu_ID);

	/** Get U_Menu_ID	  */
	public int getU_Menu_ID();
}

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
package org.eevolution.model;

import java.math.BigDecimal;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXP_FormatLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_EXP_FormatLine 
{

    /** TableName=EXP_FormatLine */
    public static final String Table_Name = "EXP_FormatLine";

    /** AD_Table_ID=53073 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws Exception;

    /** Column name AD_Reference_ID */
    public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

	/** Set Reference.
	  * System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID);

	/** Get Reference.
	  * System Reference and Validation
	  */
	public int getAD_Reference_ID();

    /** Column name DateFormat */
    public static final String COLUMNNAME_DateFormat = "DateFormat";

	/** Set Date Format.
	  * Date format used in the imput format
	  */
	public void setDateFormat (String DateFormat);

	/** Get Date Format.
	  * Date format used in the imput format
	  */
	public String getDateFormat();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXP_EmbeddedFormat_ID */
    public static final String COLUMNNAME_EXP_EmbeddedFormat_ID = "EXP_EmbeddedFormat_ID";

	/** Set EXP_EmbeddedFormat_ID	  */
	public void setEXP_EmbeddedFormat_ID (int EXP_EmbeddedFormat_ID);

	/** Get EXP_EmbeddedFormat_ID	  */
	public int getEXP_EmbeddedFormat_ID();

    /** Column name EXP_FormatLine_ID */
    public static final String COLUMNNAME_EXP_FormatLine_ID = "EXP_FormatLine_ID";

	/** Set EXP_FormatLine_ID	  */
	public void setEXP_FormatLine_ID (int EXP_FormatLine_ID);

	/** Get EXP_FormatLine_ID	  */
	public int getEXP_FormatLine_ID();

    /** Column name EXP_Format_ID */
    public static final String COLUMNNAME_EXP_Format_ID = "EXP_Format_ID";

	/** Set Export Format ID	  */
	public void setEXP_Format_ID (int EXP_Format_ID);

	/** Get Export Format ID	  */
	public int getEXP_Format_ID();

	public org.eevolution.model.I_EXP_Format getEXP_Format() throws Exception;

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsMandatory */
    public static final String COLUMNNAME_IsMandatory = "IsMandatory";

	/** Set Mandatory.
	  * Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory);

	/** Get Mandatory.
	  * Data entry is required in this column
	  */
	public boolean isMandatory();

    /** Column name IsPartUniqueIndex */
    public static final String COLUMNNAME_IsPartUniqueIndex = "IsPartUniqueIndex";

	/** Set IsPartUniqueIndex	  */
	public void setIsPartUniqueIndex (boolean IsPartUniqueIndex);

	/** Get IsPartUniqueIndex	  */
	public boolean isPartUniqueIndex();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Position */
    public static final String COLUMNNAME_Position = "Position";

	/** Set Position	  */
	public void setPosition (int Position);

	/** Get Position	  */
	public int getPosition();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}

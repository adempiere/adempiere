/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Attribute_Value
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_AD_Attribute_Value 
{

    /** TableName=AD_Attribute_Value */
    public static final String Table_Name = "AD_Attribute_Value";

    /** AD_Table_ID=406 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Attribute_ID */
    public static final String COLUMNNAME_AD_Attribute_ID = "AD_Attribute_ID";

	/** Set System Attribute	  */
	public void setAD_Attribute_ID (int AD_Attribute_ID);

	/** Get System Attribute	  */
	public int getAD_Attribute_ID();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name V_Date */
    public static final String COLUMNNAME_V_Date = "V_Date";

	/** Set Date	  */
	public void setV_Date (Timestamp V_Date);

	/** Get Date	  */
	public Timestamp getV_Date();

    /** Column name V_Number */
    public static final String COLUMNNAME_V_Number = "V_Number";

	/** Set Number	  */
	public void setV_Number (String V_Number);

	/** Get Number	  */
	public String getV_Number();

    /** Column name V_String */
    public static final String COLUMNNAME_V_String = "V_String";

	/** Set String	  */
	public void setV_String (String V_String);

	/** Get String	  */
	public String getV_String();
}

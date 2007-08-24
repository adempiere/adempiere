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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for AD_Attribute_Value
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:20.968
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
    BigDecimal accessLevel = new BigDecimal(7);

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

    /** Column name V_Date */
    public static final String COLUMNNAME_V_Date = "V_Date";

	/** Set V_Date	  */
	public void setV_Date (Timestamp V_Date);

	/** Get V_Date	  */
	public Timestamp getV_Date();

    /** Column name V_Number */
    public static final String COLUMNNAME_V_Number = "V_Number";

	/** Set V_Number	  */
	public void setV_Number (String V_Number);

	/** Get V_Number	  */
	public String getV_Number();

    /** Column name V_String */
    public static final String COLUMNNAME_V_String = "V_String";

	/** Set V_String	  */
	public void setV_String (String V_String);

	/** Get V_String	  */
	public String getV_String();
}

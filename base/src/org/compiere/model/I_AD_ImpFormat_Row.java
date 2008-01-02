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

/** Generated Interface for AD_ImpFormat_Row
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_AD_ImpFormat_Row 
{

    /** TableName=AD_ImpFormat_Row */
    public static final String Table_Name = "AD_ImpFormat_Row";

    /** AD_Table_ID=382 */
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

    /** Column name AD_ImpFormat_ID */
    public static final String COLUMNNAME_AD_ImpFormat_ID = "AD_ImpFormat_ID";

	/** Set Import Format	  */
	public void setAD_ImpFormat_ID (int AD_ImpFormat_ID);

	/** Get Import Format	  */
	public int getAD_ImpFormat_ID();

	public I_AD_ImpFormat getAD_ImpFormat() throws Exception;

    /** Column name AD_ImpFormat_Row_ID */
    public static final String COLUMNNAME_AD_ImpFormat_Row_ID = "AD_ImpFormat_Row_ID";

	/** Set Format Field	  */
	public void setAD_ImpFormat_Row_ID (int AD_ImpFormat_Row_ID);

	/** Get Format Field	  */
	public int getAD_ImpFormat_Row_ID();

    /** Column name Callout */
    public static final String COLUMNNAME_Callout = "Callout";

	/** Set Callout.
	  * Fully qualified class names and method - separated by semicolons
	  */
	public void setCallout (String Callout);

	/** Get Callout.
	  * Fully qualified class names and method - separated by semicolons
	  */
	public String getCallout();

    /** Column name ConstantValue */
    public static final String COLUMNNAME_ConstantValue = "ConstantValue";

	/** Set Constant Value.
	  * Constant value
	  */
	public void setConstantValue (String ConstantValue);

	/** Get Constant Value.
	  * Constant value
	  */
	public String getConstantValue();

    /** Column name DataFormat */
    public static final String COLUMNNAME_DataFormat = "DataFormat";

	/** Set Data Format.
	  * Format String in Java Notation, e.g. ddMMyy
	  */
	public void setDataFormat (String DataFormat);

	/** Get Data Format.
	  * Format String in Java Notation, e.g. ddMMyy
	  */
	public String getDataFormat();

    /** Column name DataType */
    public static final String COLUMNNAME_DataType = "DataType";

	/** Set Data Type.
	  * Type of data
	  */
	public void setDataType (String DataType);

	/** Get Data Type.
	  * Type of data
	  */
	public String getDataType();

    /** Column name DecimalPoint */
    public static final String COLUMNNAME_DecimalPoint = "DecimalPoint";

	/** Set Decimal Point.
	  * Decimal Point in the data file - if any
	  */
	public void setDecimalPoint (String DecimalPoint);

	/** Get Decimal Point.
	  * Decimal Point in the data file - if any
	  */
	public String getDecimalPoint();

    /** Column name DivideBy100 */
    public static final String COLUMNNAME_DivideBy100 = "DivideBy100";

	/** Set Divide by 100.
	  * Divide number by 100 to get correct amount
	  */
	public void setDivideBy100 (boolean DivideBy100);

	/** Get Divide by 100.
	  * Divide number by 100 to get correct amount
	  */
	public boolean isDivideBy100();

    /** Column name EndNo */
    public static final String COLUMNNAME_EndNo = "EndNo";

	/** Set End No	  */
	public void setEndNo (int EndNo);

	/** Get End No	  */
	public int getEndNo();

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

    /** Column name Script */
    public static final String COLUMNNAME_Script = "Script";

	/** Set Script.
	  * Dynamic Java Language Script to calculate result
	  */
	public void setScript (String Script);

	/** Get Script.
	  * Dynamic Java Language Script to calculate result
	  */
	public String getScript();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name StartNo */
    public static final String COLUMNNAME_StartNo = "StartNo";

	/** Set Start No.
	  * Starting number/position
	  */
	public void setStartNo (int StartNo);

	/** Get Start No.
	  * Starting number/position
	  */
	public int getStartNo();
}

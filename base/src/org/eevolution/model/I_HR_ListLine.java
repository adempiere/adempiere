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

/** Generated Interface for HR_ListLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_HR_ListLine 
{

    /** TableName=HR_ListLine */
    public static final String Table_Name = "HR_ListLine";

    /** AD_Table_ID=53101 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Col_1 */
    public static final String COLUMNNAME_Col_1 = "Col_1";

	/** Set Col_1	  */
	public void setCol_1 (BigDecimal Col_1);

	/** Get Col_1	  */
	public BigDecimal getCol_1();

    /** Column name Col_2 */
    public static final String COLUMNNAME_Col_2 = "Col_2";

	/** Set Col_2	  */
	public void setCol_2 (BigDecimal Col_2);

	/** Get Col_2	  */
	public BigDecimal getCol_2();

    /** Column name Col_3 */
    public static final String COLUMNNAME_Col_3 = "Col_3";

	/** Set Col_3	  */
	public void setCol_3 (BigDecimal Col_3);

	/** Get Col_3	  */
	public BigDecimal getCol_3();

    /** Column name Col_4 */
    public static final String COLUMNNAME_Col_4 = "Col_4";

	/** Set Col_4	  */
	public void setCol_4 (BigDecimal Col_4);

	/** Get Col_4	  */
	public BigDecimal getCol_4();

    /** Column name Col_5 */
    public static final String COLUMNNAME_Col_5 = "Col_5";

	/** Set Col_5	  */
	public void setCol_5 (BigDecimal Col_5);

	/** Get Col_5	  */
	public BigDecimal getCol_5();

    /** Column name Col_6 */
    public static final String COLUMNNAME_Col_6 = "Col_6";

	/** Set Col_6	  */
	public void setCol_6 (BigDecimal Col_6);

	/** Get Col_6	  */
	public BigDecimal getCol_6();

    /** Column name Col_7 */
    public static final String COLUMNNAME_Col_7 = "Col_7";

	/** Set Col_7	  */
	public void setCol_7 (BigDecimal Col_7);

	/** Get Col_7	  */
	public BigDecimal getCol_7();

    /** Column name Col_8 */
    public static final String COLUMNNAME_Col_8 = "Col_8";

	/** Set Col_8	  */
	public void setCol_8 (BigDecimal Col_8);

	/** Get Col_8	  */
	public BigDecimal getCol_8();

    /** Column name HR_ListLine_ID */
    public static final String COLUMNNAME_HR_ListLine_ID = "HR_ListLine_ID";

	/** Set Payroll List Line	  */
	public void setHR_ListLine_ID (int HR_ListLine_ID);

	/** Get Payroll List Line	  */
	public int getHR_ListLine_ID();

    /** Column name HR_ListVersion_ID */
    public static final String COLUMNNAME_HR_ListVersion_ID = "HR_ListVersion_ID";

	/** Set Payroll List Version	  */
	public void setHR_ListVersion_ID (int HR_ListVersion_ID);

	/** Get Payroll List Version	  */
	public int getHR_ListVersion_ID();

	public org.eevolution.model.I_HR_ListVersion getHR_ListVersion() throws Exception;

    /** Column name MaxValue */
    public static final String COLUMNNAME_MaxValue = "MaxValue";

	/** Set MaxValue	  */
	public void setMaxValue (BigDecimal MaxValue);

	/** Get MaxValue	  */
	public BigDecimal getMaxValue();

    /** Column name MinValue */
    public static final String COLUMNNAME_MinValue = "MinValue";

	/** Set MinValue	  */
	public void setMinValue (BigDecimal MinValue);

	/** Get MinValue	  */
	public BigDecimal getMinValue();

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
}

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

/** Generated Interface for A_Asset_Spread
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Spread 
{

    /** TableName=A_Asset_Spread */
    public static final String Table_Name = "A_Asset_Spread";

    /** AD_Table_ID=53126 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_Spread_ID */
    public static final String COLUMNNAME_A_Asset_Spread_ID = "A_Asset_Spread_ID";

	/** Set A_Asset_Spread_ID	  */
	public void setA_Asset_Spread_ID (int A_Asset_Spread_ID);

	/** Get A_Asset_Spread_ID	  */
	public int getA_Asset_Spread_ID();

    /** Column name A_Asset_Spread_Type */
    public static final String COLUMNNAME_A_Asset_Spread_Type = "A_Asset_Spread_Type";

	/** Set A_Asset_Spread_Type	  */
	public void setA_Asset_Spread_Type (String A_Asset_Spread_Type);

	/** Get A_Asset_Spread_Type	  */
	public String getA_Asset_Spread_Type();

    /** Column name A_Period_1 */
    public static final String COLUMNNAME_A_Period_1 = "A_Period_1";

	/** Set A_Period_1	  */
	public void setA_Period_1 (BigDecimal A_Period_1);

	/** Get A_Period_1	  */
	public BigDecimal getA_Period_1();

    /** Column name A_Period_10 */
    public static final String COLUMNNAME_A_Period_10 = "A_Period_10";

	/** Set A_Period_10	  */
	public void setA_Period_10 (BigDecimal A_Period_10);

	/** Get A_Period_10	  */
	public BigDecimal getA_Period_10();

    /** Column name A_Period_11 */
    public static final String COLUMNNAME_A_Period_11 = "A_Period_11";

	/** Set A_Period_11	  */
	public void setA_Period_11 (BigDecimal A_Period_11);

	/** Get A_Period_11	  */
	public BigDecimal getA_Period_11();

    /** Column name A_Period_12 */
    public static final String COLUMNNAME_A_Period_12 = "A_Period_12";

	/** Set A_Period_12	  */
	public void setA_Period_12 (BigDecimal A_Period_12);

	/** Get A_Period_12	  */
	public BigDecimal getA_Period_12();

    /** Column name A_Period_13 */
    public static final String COLUMNNAME_A_Period_13 = "A_Period_13";

	/** Set A_Period_13	  */
	public void setA_Period_13 (BigDecimal A_Period_13);

	/** Get A_Period_13	  */
	public BigDecimal getA_Period_13();

    /** Column name A_Period_14 */
    public static final String COLUMNNAME_A_Period_14 = "A_Period_14";

	/** Set A_Period_14	  */
	public void setA_Period_14 (BigDecimal A_Period_14);

	/** Get A_Period_14	  */
	public BigDecimal getA_Period_14();

    /** Column name A_Period_2 */
    public static final String COLUMNNAME_A_Period_2 = "A_Period_2";

	/** Set A_Period_2	  */
	public void setA_Period_2 (BigDecimal A_Period_2);

	/** Get A_Period_2	  */
	public BigDecimal getA_Period_2();

    /** Column name A_Period_3 */
    public static final String COLUMNNAME_A_Period_3 = "A_Period_3";

	/** Set A_Period_3	  */
	public void setA_Period_3 (BigDecimal A_Period_3);

	/** Get A_Period_3	  */
	public BigDecimal getA_Period_3();

    /** Column name A_Period_4 */
    public static final String COLUMNNAME_A_Period_4 = "A_Period_4";

	/** Set A_Period_4	  */
	public void setA_Period_4 (BigDecimal A_Period_4);

	/** Get A_Period_4	  */
	public BigDecimal getA_Period_4();

    /** Column name A_Period_5 */
    public static final String COLUMNNAME_A_Period_5 = "A_Period_5";

	/** Set A_Period_5	  */
	public void setA_Period_5 (BigDecimal A_Period_5);

	/** Get A_Period_5	  */
	public BigDecimal getA_Period_5();

    /** Column name A_Period_6 */
    public static final String COLUMNNAME_A_Period_6 = "A_Period_6";

	/** Set A_Period_6	  */
	public void setA_Period_6 (BigDecimal A_Period_6);

	/** Get A_Period_6	  */
	public BigDecimal getA_Period_6();

    /** Column name A_Period_7 */
    public static final String COLUMNNAME_A_Period_7 = "A_Period_7";

	/** Set A_Period_7	  */
	public void setA_Period_7 (BigDecimal A_Period_7);

	/** Get A_Period_7	  */
	public BigDecimal getA_Period_7();

    /** Column name A_Period_8 */
    public static final String COLUMNNAME_A_Period_8 = "A_Period_8";

	/** Set A_Period_8	  */
	public void setA_Period_8 (BigDecimal A_Period_8);

	/** Get A_Period_8	  */
	public BigDecimal getA_Period_8();

    /** Column name A_Period_9 */
    public static final String COLUMNNAME_A_Period_9 = "A_Period_9";

	/** Set A_Period_9	  */
	public void setA_Period_9 (BigDecimal A_Period_9);

	/** Get A_Period_9	  */
	public BigDecimal getA_Period_9();

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
}

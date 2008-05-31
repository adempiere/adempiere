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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Info_Ins
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Info_Ins 
{

    /** TableName=A_Asset_Info_Ins */
    public static final String Table_Name = "A_Asset_Info_Ins";

    /** AD_Table_ID=53135 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Info_Ins_ID */
    public static final String COLUMNNAME_A_Asset_Info_Ins_ID = "A_Asset_Info_Ins_ID";

	/** Set A_Asset_Info_Ins_ID	  */
	public void setA_Asset_Info_Ins_ID (int A_Asset_Info_Ins_ID);

	/** Get A_Asset_Info_Ins_ID	  */
	public int getA_Asset_Info_Ins_ID();

    /** Column name A_Ins_Premium */
    public static final String COLUMNNAME_A_Ins_Premium = "A_Ins_Premium";

	/** Set A_Ins_Premium	  */
	public void setA_Ins_Premium (BigDecimal A_Ins_Premium);

	/** Get A_Ins_Premium	  */
	public BigDecimal getA_Ins_Premium();

    /** Column name A_Ins_Value */
    public static final String COLUMNNAME_A_Ins_Value = "A_Ins_Value";

	/** Set A_Ins_Value	  */
	public void setA_Ins_Value (BigDecimal A_Ins_Value);

	/** Get A_Ins_Value	  */
	public BigDecimal getA_Ins_Value();

    /** Column name A_Insurance_Co */
    public static final String COLUMNNAME_A_Insurance_Co = "A_Insurance_Co";

	/** Set A_Insurance_Co	  */
	public void setA_Insurance_Co (String A_Insurance_Co);

	/** Get A_Insurance_Co	  */
	public String getA_Insurance_Co();

    /** Column name A_Policy_No */
    public static final String COLUMNNAME_A_Policy_No = "A_Policy_No";

	/** Set A_Policy_No	  */
	public void setA_Policy_No (String A_Policy_No);

	/** Get A_Policy_No	  */
	public String getA_Policy_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set A_Renewal_Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get A_Renewal_Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_Replace_Cost */
    public static final String COLUMNNAME_A_Replace_Cost = "A_Replace_Cost";

	/** Set A_Replace_Cost	  */
	public void setA_Replace_Cost (BigDecimal A_Replace_Cost);

	/** Get A_Replace_Cost	  */
	public BigDecimal getA_Replace_Cost();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Text	  */
	public void setText (String Text);

	/** Get Text	  */
	public String getText();
}

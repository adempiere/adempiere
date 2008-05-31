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

/** Generated Interface for A_Asset_Info_Lic
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Info_Lic 
{

    /** TableName=A_Asset_Info_Lic */
    public static final String Table_Name = "A_Asset_Info_Lic";

    /** AD_Table_ID=53134 */
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

    /** Column name A_Asset_Info_Lic_ID */
    public static final String COLUMNNAME_A_Asset_Info_Lic_ID = "A_Asset_Info_Lic_ID";

	/** Set A_Asset_Info_Lic_ID	  */
	public void setA_Asset_Info_Lic_ID (int A_Asset_Info_Lic_ID);

	/** Get A_Asset_Info_Lic_ID	  */
	public int getA_Asset_Info_Lic_ID();

    /** Column name A_Issuing_Agency */
    public static final String COLUMNNAME_A_Issuing_Agency = "A_Issuing_Agency";

	/** Set A_Issuing_Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency);

	/** Get A_Issuing_Agency	  */
	public String getA_Issuing_Agency();

    /** Column name A_License_Fee */
    public static final String COLUMNNAME_A_License_Fee = "A_License_Fee";

	/** Set A_License_Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee);

	/** Get A_License_Fee	  */
	public BigDecimal getA_License_Fee();

    /** Column name A_License_No */
    public static final String COLUMNNAME_A_License_No = "A_License_No";

	/** Set A_License_No	  */
	public void setA_License_No (String A_License_No);

	/** Get A_License_No	  */
	public String getA_License_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set A_Renewal_Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get A_Renewal_Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Text	  */
	public void setText (String Text);

	/** Get Text	  */
	public String getText();
}

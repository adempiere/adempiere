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

/** Generated Interface for A_Asset_Info_Fin
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Info_Fin 
{

    /** TableName=A_Asset_Info_Fin */
    public static final String Table_Name = "A_Asset_Info_Fin";

    /** AD_Table_ID=53132 */
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

    /** Column name A_Asset_Info_Fin_ID */
    public static final String COLUMNNAME_A_Asset_Info_Fin_ID = "A_Asset_Info_Fin_ID";

	/** Set A_Asset_Info_Fin_ID	  */
	public void setA_Asset_Info_Fin_ID (int A_Asset_Info_Fin_ID);

	/** Get A_Asset_Info_Fin_ID	  */
	public int getA_Asset_Info_Fin_ID();

    /** Column name A_Contract_Date */
    public static final String COLUMNNAME_A_Contract_Date = "A_Contract_Date";

	/** Set A_Contract_Date	  */
	public void setA_Contract_Date (Timestamp A_Contract_Date);

	/** Get A_Contract_Date	  */
	public Timestamp getA_Contract_Date();

    /** Column name A_Due_On */
    public static final String COLUMNNAME_A_Due_On = "A_Due_On";

	/** Set A_Due_On	  */
	public void setA_Due_On (String A_Due_On);

	/** Get A_Due_On	  */
	public String getA_Due_On();

    /** Column name A_Expired_Date */
    public static final String COLUMNNAME_A_Expired_Date = "A_Expired_Date";

	/** Set A_Expired_Date	  */
	public void setA_Expired_Date (Timestamp A_Expired_Date);

	/** Get A_Expired_Date	  */
	public Timestamp getA_Expired_Date();

    /** Column name A_Finance_Meth */
    public static final String COLUMNNAME_A_Finance_Meth = "A_Finance_Meth";

	/** Set A_Finance_Meth	  */
	public void setA_Finance_Meth (String A_Finance_Meth);

	/** Get A_Finance_Meth	  */
	public String getA_Finance_Meth();

    /** Column name A_Monthly_Payment */
    public static final String COLUMNNAME_A_Monthly_Payment = "A_Monthly_Payment";

	/** Set A_Monthly_Payment	  */
	public void setA_Monthly_Payment (BigDecimal A_Monthly_Payment);

	/** Get A_Monthly_Payment	  */
	public BigDecimal getA_Monthly_Payment();

    /** Column name A_Purchase_Option */
    public static final String COLUMNNAME_A_Purchase_Option = "A_Purchase_Option";

	/** Set A_Purchase_Option	  */
	public void setA_Purchase_Option (boolean A_Purchase_Option);

	/** Get A_Purchase_Option	  */
	public boolean isA_Purchase_Option();

    /** Column name A_Purchase_Option_Credit */
    public static final String COLUMNNAME_A_Purchase_Option_Credit = "A_Purchase_Option_Credit";

	/** Set A_Purchase_Option_Credit	  */
	public void setA_Purchase_Option_Credit (int A_Purchase_Option_Credit);

	/** Get A_Purchase_Option_Credit	  */
	public int getA_Purchase_Option_Credit();

    /** Column name A_Purchase_Option_Credit_Per */
    public static final String COLUMNNAME_A_Purchase_Option_Credit_Per = "A_Purchase_Option_Credit_Per";

	/** Set A_Purchase_Option_Credit_Per	  */
	public void setA_Purchase_Option_Credit_Per (BigDecimal A_Purchase_Option_Credit_Per);

	/** Get A_Purchase_Option_Credit_Per	  */
	public BigDecimal getA_Purchase_Option_Credit_Per();

    /** Column name A_Purchase_Price */
    public static final String COLUMNNAME_A_Purchase_Price = "A_Purchase_Price";

	/** Set A_Purchase_Price	  */
	public void setA_Purchase_Price (BigDecimal A_Purchase_Price);

	/** Get A_Purchase_Price	  */
	public BigDecimal getA_Purchase_Price();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}

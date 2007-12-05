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

/** Generated Interface for C_DunningLevel
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_DunningLevel 
{

    /** TableName=C_DunningLevel */
    public static final String Table_Name = "C_DunningLevel";

    /** AD_Table_ID=331 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_DunningLevel_ID */
    public static final String COLUMNNAME_C_DunningLevel_ID = "C_DunningLevel_ID";

	/** Set Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID);

	/** Get Dunning Level	  */
	public int getC_DunningLevel_ID();

    /** Column name C_Dunning_ID */
    public static final String COLUMNNAME_C_Dunning_ID = "C_Dunning_ID";

	/** Set Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public void setC_Dunning_ID (int C_Dunning_ID);

	/** Get Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID();

	public I_C_Dunning getC_Dunning() throws Exception;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public I_C_PaymentTerm getC_PaymentTerm() throws Exception;

    /** Column name ChargeFee */
    public static final String COLUMNNAME_ChargeFee = "ChargeFee";

	/** Set Charge fee.
	  * Indicates if fees will be charged for overdue invoices
	  */
	public void setChargeFee (boolean ChargeFee);

	/** Get Charge fee.
	  * Indicates if fees will be charged for overdue invoices
	  */
	public boolean isChargeFee();

    /** Column name ChargeInterest */
    public static final String COLUMNNAME_ChargeInterest = "ChargeInterest";

	/** Set Charge Interest.
	  * Indicates if interest will be charged on overdue invoices
	  */
	public void setChargeInterest (boolean ChargeInterest);

	/** Get Charge Interest.
	  * Indicates if interest will be charged on overdue invoices
	  */
	public boolean isChargeInterest();

    /** Column name DaysAfterDue */
    public static final String COLUMNNAME_DaysAfterDue = "DaysAfterDue";

	/** Set Days after due date.
	  * Days after due date to dun (if negative days until due)
	  */
	public void setDaysAfterDue (BigDecimal DaysAfterDue);

	/** Get Days after due date.
	  * Days after due date to dun (if negative days until due)
	  */
	public BigDecimal getDaysAfterDue();

    /** Column name DaysBetweenDunning */
    public static final String COLUMNNAME_DaysBetweenDunning = "DaysBetweenDunning";

	/** Set Days between dunning.
	  * Days between sending dunning notices
	  */
	public void setDaysBetweenDunning (int DaysBetweenDunning);

	/** Get Days between dunning.
	  * Days between sending dunning notices
	  */
	public int getDaysBetweenDunning();

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

    /** Column name Dunning_PrintFormat_ID */
    public static final String COLUMNNAME_Dunning_PrintFormat_ID = "Dunning_PrintFormat_ID";

	/** Set Dunning Print Format.
	  * Print Format for printing Dunning Letters
	  */
	public void setDunning_PrintFormat_ID (int Dunning_PrintFormat_ID);

	/** Get Dunning Print Format.
	  * Print Format for printing Dunning Letters
	  */
	public int getDunning_PrintFormat_ID();

    /** Column name FeeAmt */
    public static final String COLUMNNAME_FeeAmt = "FeeAmt";

	/** Set Fee Amount.
	  * Fee amount in invoice currency
	  */
	public void setFeeAmt (BigDecimal FeeAmt);

	/** Get Fee Amount.
	  * Fee amount in invoice currency
	  */
	public BigDecimal getFeeAmt();

    /** Column name InterestPercent */
    public static final String COLUMNNAME_InterestPercent = "InterestPercent";

	/** Set Interest in percent.
	  * Percentage interest to charge on overdue invoices
	  */
	public void setInterestPercent (BigDecimal InterestPercent);

	/** Get Interest in percent.
	  * Percentage interest to charge on overdue invoices
	  */
	public BigDecimal getInterestPercent();

    /** Column name IsSetCreditStop */
    public static final String COLUMNNAME_IsSetCreditStop = "IsSetCreditStop";

	/** Set Credit Stop.
	  * Set the business partner to credit stop
	  */
	public void setIsSetCreditStop (boolean IsSetCreditStop);

	/** Get Credit Stop.
	  * Set the business partner to credit stop
	  */
	public boolean isSetCreditStop();

    /** Column name IsSetPaymentTerm */
    public static final String COLUMNNAME_IsSetPaymentTerm = "IsSetPaymentTerm";

	/** Set Set Payment Term.
	  * Set the payment term of the Business Partner
	  */
	public void setIsSetPaymentTerm (boolean IsSetPaymentTerm);

	/** Get Set Payment Term.
	  * Set the payment term of the Business Partner
	  */
	public boolean isSetPaymentTerm();

    /** Column name IsShowAllDue */
    public static final String COLUMNNAME_IsShowAllDue = "IsShowAllDue";

	/** Set Show All Due.
	  * Show/print all due invoices
	  */
	public void setIsShowAllDue (boolean IsShowAllDue);

	/** Get Show All Due.
	  * Show/print all due invoices
	  */
	public boolean isShowAllDue();

    /** Column name IsShowNotDue */
    public static final String COLUMNNAME_IsShowNotDue = "IsShowNotDue";

	/** Set Show Not Due.
	  * Show/print all invoices which are not due (yet).
	  */
	public void setIsShowNotDue (boolean IsShowNotDue);

	/** Get Show Not Due.
	  * Show/print all invoices which are not due (yet).
	  */
	public boolean isShowNotDue();

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

    /** Column name Note */
    public static final String COLUMNNAME_Note = "Note";

	/** Set Note.
	  * Optional additional user defined information
	  */
	public void setNote (String Note);

	/** Get Note.
	  * Optional additional user defined information
	  */
	public String getNote();

    /** Column name PrintName */
    public static final String COLUMNNAME_PrintName = "PrintName";

	/** Set Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName);

	/** Get Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public String getPrintName();
}

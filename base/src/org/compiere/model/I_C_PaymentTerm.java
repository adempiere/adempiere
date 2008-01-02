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

/** Generated Interface for C_PaymentTerm
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_C_PaymentTerm 
{

    /** TableName=C_PaymentTerm */
    public static final String Table_Name = "C_PaymentTerm";

    /** AD_Table_ID=113 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AfterDelivery */
    public static final String COLUMNNAME_AfterDelivery = "AfterDelivery";

	/** Set After Delivery.
	  * Due after delivery rather than after invoicing
	  */
	public void setAfterDelivery (boolean AfterDelivery);

	/** Get After Delivery.
	  * Due after delivery rather than after invoicing
	  */
	public boolean isAfterDelivery();

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

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

    /** Column name Discount2 */
    public static final String COLUMNNAME_Discount2 = "Discount2";

	/** Set Discount 2 %.
	  * Discount in percent
	  */
	public void setDiscount2 (BigDecimal Discount2);

	/** Get Discount 2 %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount2();

    /** Column name DiscountDays */
    public static final String COLUMNNAME_DiscountDays = "DiscountDays";

	/** Set Discount Days.
	  * Number of days from invoice date to be eligible for discount
	  */
	public void setDiscountDays (int DiscountDays);

	/** Get Discount Days.
	  * Number of days from invoice date to be eligible for discount
	  */
	public int getDiscountDays();

    /** Column name DiscountDays2 */
    public static final String COLUMNNAME_DiscountDays2 = "DiscountDays2";

	/** Set Discount Days 2.
	  * Number of days from invoice date to be eligible for discount
	  */
	public void setDiscountDays2 (int DiscountDays2);

	/** Get Discount Days 2.
	  * Number of days from invoice date to be eligible for discount
	  */
	public int getDiscountDays2();

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

    /** Column name FixMonthCutoff */
    public static final String COLUMNNAME_FixMonthCutoff = "FixMonthCutoff";

	/** Set Fix month cutoff.
	  * Last day to include for next due date
	  */
	public void setFixMonthCutoff (int FixMonthCutoff);

	/** Get Fix month cutoff.
	  * Last day to include for next due date
	  */
	public int getFixMonthCutoff();

    /** Column name FixMonthDay */
    public static final String COLUMNNAME_FixMonthDay = "FixMonthDay";

	/** Set Fix month day.
	  * Day of the month of the due date
	  */
	public void setFixMonthDay (int FixMonthDay);

	/** Get Fix month day.
	  * Day of the month of the due date
	  */
	public int getFixMonthDay();

    /** Column name FixMonthOffset */
    public static final String COLUMNNAME_FixMonthOffset = "FixMonthOffset";

	/** Set Fix month offset.
	  * Number of months (0=same, 1=following)
	  */
	public void setFixMonthOffset (int FixMonthOffset);

	/** Get Fix month offset.
	  * Number of months (0=same, 1=following)
	  */
	public int getFixMonthOffset();

    /** Column name GraceDays */
    public static final String COLUMNNAME_GraceDays = "GraceDays";

	/** Set Grace Days.
	  * Days after due date to send first dunning letter
	  */
	public void setGraceDays (int GraceDays);

	/** Get Grace Days.
	  * Days after due date to send first dunning letter
	  */
	public int getGraceDays();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsDueFixed */
    public static final String COLUMNNAME_IsDueFixed = "IsDueFixed";

	/** Set Fixed due date.
	  * Payment is due on a fixed date
	  */
	public void setIsDueFixed (boolean IsDueFixed);

	/** Get Fixed due date.
	  * Payment is due on a fixed date
	  */
	public boolean isDueFixed();

    /** Column name IsNextBusinessDay */
    public static final String COLUMNNAME_IsNextBusinessDay = "IsNextBusinessDay";

	/** Set Next Business Day.
	  * Payment due on the next business day
	  */
	public void setIsNextBusinessDay (boolean IsNextBusinessDay);

	/** Get Next Business Day.
	  * Payment due on the next business day
	  */
	public boolean isNextBusinessDay();

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

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

    /** Column name NetDay */
    public static final String COLUMNNAME_NetDay = "NetDay";

	/** Set Net Day.
	  * Day when payment is due net
	  */
	public void setNetDay (String NetDay);

	/** Get Net Day.
	  * Day when payment is due net
	  */
	public String getNetDay();

    /** Column name NetDays */
    public static final String COLUMNNAME_NetDays = "NetDays";

	/** Set Net Days.
	  * Net Days in which payment is due
	  */
	public void setNetDays (int NetDays);

	/** Get Net Days.
	  * Net Days in which payment is due
	  */
	public int getNetDays();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

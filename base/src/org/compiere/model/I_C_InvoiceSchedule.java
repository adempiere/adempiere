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

/** Generated Interface for C_InvoiceSchedule
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_C_InvoiceSchedule 
{

    /** TableName=C_InvoiceSchedule */
    public static final String Table_Name = "C_InvoiceSchedule";

    /** AD_Table_ID=257 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Amt */
    public static final String COLUMNNAME_Amt = "Amt";

	/** Set Amount.
	  * Amount
	  */
	public void setAmt (BigDecimal Amt);

	/** Get Amount.
	  * Amount
	  */
	public BigDecimal getAmt();

    /** Column name C_InvoiceSchedule_ID */
    public static final String COLUMNNAME_C_InvoiceSchedule_ID = "C_InvoiceSchedule_ID";

	/** Set Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID);

	/** Get Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public int getC_InvoiceSchedule_ID();

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

    /** Column name EvenInvoiceWeek */
    public static final String COLUMNNAME_EvenInvoiceWeek = "EvenInvoiceWeek";

	/** Set Invoice on even weeks.
	  * Send invoices on even weeks
	  */
	public void setEvenInvoiceWeek (boolean EvenInvoiceWeek);

	/** Get Invoice on even weeks.
	  * Send invoices on even weeks
	  */
	public boolean isEvenInvoiceWeek();

    /** Column name InvoiceDay */
    public static final String COLUMNNAME_InvoiceDay = "InvoiceDay";

	/** Set Invoice Day.
	  * Day of Invoice Generation
	  */
	public void setInvoiceDay (int InvoiceDay);

	/** Get Invoice Day.
	  * Day of Invoice Generation
	  */
	public int getInvoiceDay();

    /** Column name InvoiceDayCutoff */
    public static final String COLUMNNAME_InvoiceDayCutoff = "InvoiceDayCutoff";

	/** Set Invoice day cut-off.
	  * Last day for including shipments
	  */
	public void setInvoiceDayCutoff (int InvoiceDayCutoff);

	/** Get Invoice day cut-off.
	  * Last day for including shipments
	  */
	public int getInvoiceDayCutoff();

    /** Column name InvoiceFrequency */
    public static final String COLUMNNAME_InvoiceFrequency = "InvoiceFrequency";

	/** Set Invoice Frequency.
	  * How often invoices will be generated
	  */
	public void setInvoiceFrequency (String InvoiceFrequency);

	/** Get Invoice Frequency.
	  * How often invoices will be generated
	  */
	public String getInvoiceFrequency();

    /** Column name InvoiceWeekDay */
    public static final String COLUMNNAME_InvoiceWeekDay = "InvoiceWeekDay";

	/** Set Invoice Week Day.
	  * Day to generate invoices
	  */
	public void setInvoiceWeekDay (String InvoiceWeekDay);

	/** Get Invoice Week Day.
	  * Day to generate invoices
	  */
	public String getInvoiceWeekDay();

    /** Column name InvoiceWeekDayCutoff */
    public static final String COLUMNNAME_InvoiceWeekDayCutoff = "InvoiceWeekDayCutoff";

	/** Set Invoice weekday cutoff.
	  * Last day in the week for shipments to be included
	  */
	public void setInvoiceWeekDayCutoff (String InvoiceWeekDayCutoff);

	/** Get Invoice weekday cutoff.
	  * Last day in the week for shipments to be included
	  */
	public String getInvoiceWeekDayCutoff();

    /** Column name IsAmount */
    public static final String COLUMNNAME_IsAmount = "IsAmount";

	/** Set Amount Limit.
	  * Send invoices only if the amount exceeds the limit
	  */
	public void setIsAmount (boolean IsAmount);

	/** Get Amount Limit.
	  * Send invoices only if the amount exceeds the limit
	  */
	public boolean isAmount();

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

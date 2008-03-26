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

/** Generated Interface for C_PaymentAllocate
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_C_PaymentAllocate 
{

    /** TableName=C_PaymentAllocate */
    public static final String Table_Name = "C_PaymentAllocate";

    /** AD_Table_ID=812 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_AllocationLine_ID */
    public static final String COLUMNNAME_C_AllocationLine_ID = "C_AllocationLine_ID";

	/** Set Allocation Line.
	  * Allocation Line
	  */
	public void setC_AllocationLine_ID (int C_AllocationLine_ID);

	/** Get Allocation Line.
	  * Allocation Line
	  */
	public int getC_AllocationLine_ID();

	public I_C_AllocationLine getC_AllocationLine() throws Exception;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws Exception;

    /** Column name C_PaymentAllocate_ID */
    public static final String COLUMNNAME_C_PaymentAllocate_ID = "C_PaymentAllocate_ID";

	/** Set Allocate Payment.
	  * Allocate Payment to Invoices
	  */
	public void setC_PaymentAllocate_ID (int C_PaymentAllocate_ID);

	/** Get Allocate Payment.
	  * Allocate Payment to Invoices
	  */
	public int getC_PaymentAllocate_ID();

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws Exception;

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

    /** Column name InvoiceAmt */
    public static final String COLUMNNAME_InvoiceAmt = "InvoiceAmt";

	/** Set Invoice Amt	  */
	public void setInvoiceAmt (BigDecimal InvoiceAmt);

	/** Get Invoice Amt	  */
	public BigDecimal getInvoiceAmt();

    /** Column name OverUnderAmt */
    public static final String COLUMNNAME_OverUnderAmt = "OverUnderAmt";

	/** Set Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public void setOverUnderAmt (BigDecimal OverUnderAmt);

	/** Get Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public BigDecimal getOverUnderAmt();

    /** Column name RemainingAmt */
    public static final String COLUMNNAME_RemainingAmt = "RemainingAmt";

	/** Set Remaining Amt.
	  * Remaining Amount
	  */
	public void setRemainingAmt (BigDecimal RemainingAmt);

	/** Get Remaining Amt.
	  * Remaining Amount
	  */
	public BigDecimal getRemainingAmt();

    /** Column name WriteOffAmt */
    public static final String COLUMNNAME_WriteOffAmt = "WriteOffAmt";

	/** Set Write-off Amount.
	  * Amount to write-off
	  */
	public void setWriteOffAmt (BigDecimal WriteOffAmt);

	/** Get Write-off Amount.
	  * Amount to write-off
	  */
	public BigDecimal getWriteOffAmt();
}

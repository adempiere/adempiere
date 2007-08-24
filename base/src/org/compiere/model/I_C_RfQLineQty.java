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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for C_RfQLineQty
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:44.406
     */
    public interface I_C_RfQLineQty 
{

    /** TableName=C_RfQLineQty */
    public static final String Table_Name = "C_RfQLineQty";

    /** AD_Table_ID=675 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = new BigDecimal(1);

    /** Load Meta Data */

    /** Column name BenchmarkPrice */
    public static final String COLUMNNAME_BenchmarkPrice = "BenchmarkPrice";

	/** Set Benchmark Price.
	  * Price to compare responses to
	  */
	public void setBenchmarkPrice (BigDecimal BenchmarkPrice);

	/** Get Benchmark Price.
	  * Price to compare responses to
	  */
	public BigDecimal getBenchmarkPrice();

    /** Column name BestResponseAmt */
    public static final String COLUMNNAME_BestResponseAmt = "BestResponseAmt";

	/** Set Best Response Amount.
	  * Best Response Amount
	  */
	public void setBestResponseAmt (BigDecimal BestResponseAmt);

	/** Get Best Response Amount.
	  * Best Response Amount
	  */
	public BigDecimal getBestResponseAmt();

    /** Column name C_RfQLineQty_ID */
    public static final String COLUMNNAME_C_RfQLineQty_ID = "C_RfQLineQty_ID";

	/** Set RfQ Line Quantity.
	  * Request for Quotation Line Quantity
	  */
	public void setC_RfQLineQty_ID (int C_RfQLineQty_ID);

	/** Get RfQ Line Quantity.
	  * Request for Quotation Line Quantity
	  */
	public int getC_RfQLineQty_ID();

    /** Column name C_RfQLine_ID */
    public static final String COLUMNNAME_C_RfQLine_ID = "C_RfQLine_ID";

	/** Set RfQ Line.
	  * Request for Quotation Line
	  */
	public void setC_RfQLine_ID (int C_RfQLine_ID);

	/** Get RfQ Line.
	  * Request for Quotation Line
	  */
	public int getC_RfQLine_ID();

	public I_C_RfQLine getI_C_RfQLine() throws Exception;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getI_C_UOM() throws Exception;

    /** Column name IsOfferQty */
    public static final String COLUMNNAME_IsOfferQty = "IsOfferQty";

	/** Set Offer Quantity.
	  * This quantity is used in the Offer to the Customer
	  */
	public void setIsOfferQty (boolean IsOfferQty);

	/** Get Offer Quantity.
	  * This quantity is used in the Offer to the Customer
	  */
	public boolean isOfferQty();

    /** Column name IsPurchaseQty */
    public static final String COLUMNNAME_IsPurchaseQty = "IsPurchaseQty";

	/** Set Purchase Quantity.
	  * This quantity is used in the Purchase Order to the Supplier
	  */
	public void setIsPurchaseQty (boolean IsPurchaseQty);

	/** Get Purchase Quantity.
	  * This quantity is used in the Purchase Order to the Supplier
	  */
	public boolean isPurchaseQty();

    /** Column name IsRfQQty */
    public static final String COLUMNNAME_IsRfQQty = "IsRfQQty";

	/** Set RfQ Quantity.
	  * The quantity is used when generating RfQ Responses
	  */
	public void setIsRfQQty (boolean IsRfQQty);

	/** Get RfQ Quantity.
	  * The quantity is used when generating RfQ Responses
	  */
	public boolean isRfQQty();

    /** Column name Margin */
    public static final String COLUMNNAME_Margin = "Margin";

	/** Set Margin %.
	  * Margin for a product as a percentage
	  */
	public void setMargin (BigDecimal Margin);

	/** Get Margin %.
	  * Margin for a product as a percentage
	  */
	public BigDecimal getMargin();

    /** Column name OfferAmt */
    public static final String COLUMNNAME_OfferAmt = "OfferAmt";

	/** Set Offer Amount.
	  * Amount of the Offer
	  */
	public void setOfferAmt (BigDecimal OfferAmt);

	/** Get Offer Amount.
	  * Amount of the Offer
	  */
	public BigDecimal getOfferAmt();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();
}

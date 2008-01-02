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

/** Generated Interface for M_PriceList
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_M_PriceList 
{

    /** TableName=M_PriceList */
    public static final String Table_Name = "M_PriceList";

    /** AD_Table_ID=255 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name BasePriceList_ID */
    public static final String COLUMNNAME_BasePriceList_ID = "BasePriceList_ID";

	/** Set Base Pricelist.
	  * Pricelist to be used, if product not found on this pricelist
	  */
	public void setBasePriceList_ID (int BasePriceList_ID);

	/** Get Base Pricelist.
	  * Pricelist to be used, if product not found on this pricelist
	  */
	public int getBasePriceList_ID();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws Exception;

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

    /** Column name EnforcePriceLimit */
    public static final String COLUMNNAME_EnforcePriceLimit = "EnforcePriceLimit";

	/** Set Enforce price limit.
	  * Do not allow prices below the limit price
	  */
	public void setEnforcePriceLimit (boolean EnforcePriceLimit);

	/** Get Enforce price limit.
	  * Do not allow prices below the limit price
	  */
	public boolean isEnforcePriceLimit();

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

    /** Column name IsSOPriceList */
    public static final String COLUMNNAME_IsSOPriceList = "IsSOPriceList";

	/** Set Sales Price list.
	  * This is a Sales Price List
	  */
	public void setIsSOPriceList (boolean IsSOPriceList);

	/** Get Sales Price list.
	  * This is a Sales Price List
	  */
	public boolean isSOPriceList();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

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

    /** Column name PricePrecision */
    public static final String COLUMNNAME_PricePrecision = "PricePrecision";

	/** Set Price Precision.
	  * Precision (number of decimals) for the Price
	  */
	public void setPricePrecision (BigDecimal PricePrecision);

	/** Get Price Precision.
	  * Precision (number of decimals) for the Price
	  */
	public BigDecimal getPricePrecision();
}

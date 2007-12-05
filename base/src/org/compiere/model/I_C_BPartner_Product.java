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

/** Generated Interface for C_BPartner_Product
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_BPartner_Product 
{

    /** TableName=C_BPartner_Product */
    public static final String Table_Name = "C_BPartner_Product";

    /** AD_Table_ID=632 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

	public I_C_BPartner getC_BPartner() throws Exception;

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws Exception;

    /** Column name Manufacturer */
    public static final String COLUMNNAME_Manufacturer = "Manufacturer";

	/** Set Manufacturer.
	  * Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer);

	/** Get Manufacturer.
	  * Manufacturer of the Product
	  */
	public String getManufacturer();

    /** Column name QualityRating */
    public static final String COLUMNNAME_QualityRating = "QualityRating";

	/** Set Quality Rating.
	  * Method for rating vendors
	  */
	public void setQualityRating (BigDecimal QualityRating);

	/** Get Quality Rating.
	  * Method for rating vendors
	  */
	public BigDecimal getQualityRating();

    /** Column name ShelfLifeMinDays */
    public static final String COLUMNNAME_ShelfLifeMinDays = "ShelfLifeMinDays";

	/** Set Min Shelf Life Days.
	  * Minimum Shelf Life in days based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinDays (int ShelfLifeMinDays);

	/** Get Min Shelf Life Days.
	  * Minimum Shelf Life in days based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinDays();

    /** Column name ShelfLifeMinPct */
    public static final String COLUMNNAME_ShelfLifeMinPct = "ShelfLifeMinPct";

	/** Set Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinPct (int ShelfLifeMinPct);

	/** Get Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct();

    /** Column name VendorCategory */
    public static final String COLUMNNAME_VendorCategory = "VendorCategory";

	/** Set Partner Category.
	  * Product Category of the Business Partner
	  */
	public void setVendorCategory (String VendorCategory);

	/** Get Partner Category.
	  * Product Category of the Business Partner
	  */
	public String getVendorCategory();

    /** Column name VendorProductNo */
    public static final String COLUMNNAME_VendorProductNo = "VendorProductNo";

	/** Set Partner Product Key.
	  * Product Key of the Business Partner
	  */
	public void setVendorProductNo (String VendorProductNo);

	/** Get Partner Product Key.
	  * Product Key of the Business Partner
	  */
	public String getVendorProductNo();
}

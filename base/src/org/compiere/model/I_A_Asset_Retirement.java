/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Retirement
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_A_Asset_Retirement 
{

    /** TableName=A_Asset_Retirement */
    public static final String Table_Name = "A_Asset_Retirement";

    /** AD_Table_ID=540 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

	public I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name A_Asset_Retirement_ID */
    public static final String COLUMNNAME_A_Asset_Retirement_ID = "A_Asset_Retirement_ID";

	/** Set Asset Retirement.
	  * Internally used asset is not longer used.
	  */
	public void setA_Asset_Retirement_ID (int A_Asset_Retirement_ID);

	/** Get Asset Retirement.
	  * Internally used asset is not longer used.
	  */
	public int getA_Asset_Retirement_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AssetMarketValueAmt */
    public static final String COLUMNNAME_AssetMarketValueAmt = "AssetMarketValueAmt";

	/** Set Market value Amount.
	  * Market value of the asset
	  */
	public void setAssetMarketValueAmt (BigDecimal AssetMarketValueAmt);

	/** Get Market value Amount.
	  * Market value of the asset
	  */
	public BigDecimal getAssetMarketValueAmt();

    /** Column name AssetValueAmt */
    public static final String COLUMNNAME_AssetValueAmt = "AssetValueAmt";

	/** Set Asset value.
	  * Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt);

	/** Get Asset value.
	  * Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt();

    /** Column name C_InvoiceLine_ID */
    public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

	/** Set Invoice Line.
	  * Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID);

	/** Get Invoice Line.
	  * Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID();

	public I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;
}

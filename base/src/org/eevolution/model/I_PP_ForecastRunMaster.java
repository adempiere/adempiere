/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_ForecastRunMaster
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_PP_ForecastRunMaster 
{

    /** TableName=PP_ForecastRunMaster */
    public static final String Table_Name = "PP_ForecastRunMaster";

    /** AD_Table_ID=53392 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name FactorAlpha */
    public static final String COLUMNNAME_FactorAlpha = "FactorAlpha";

	/** Set Factor Alpha.
	  * Identifies an Factor Alpha
	  */
	public void setFactorAlpha (BigDecimal FactorAlpha);

	/** Get Factor Alpha.
	  * Identifies an Factor Alpha
	  */
	public BigDecimal getFactorAlpha();

    /** Column name FactorBeta */
    public static final String COLUMNNAME_FactorBeta = "FactorBeta";

	/** Set Factor Beta.
	  * Identifies a Factor Beta
	  */
	public void setFactorBeta (BigDecimal FactorBeta);

	/** Get Factor Beta.
	  * Identifies a Factor Beta
	  */
	public BigDecimal getFactorBeta();

    /** Column name FactorGamma */
    public static final String COLUMNNAME_FactorGamma = "FactorGamma";

	/** Set Factor Gamma.
	  * Identifies a Factor Gamma
	  */
	public void setFactorGamma (BigDecimal FactorGamma);

	/** Get Factor Gamma.
	  * Identifies a Factor Gamma
	  */
	public BigDecimal getFactorGamma();

    /** Column name FactorMultiplier */
    public static final String COLUMNNAME_FactorMultiplier = "FactorMultiplier";

	/** Set Factor Multiplier.
	  * Identifies a Factor Multiplier
	  */
	public void setFactorMultiplier (BigDecimal FactorMultiplier);

	/** Get Factor Multiplier.
	  * Identifies a Factor Multiplier
	  */
	public BigDecimal getFactorMultiplier();

    /** Column name FactorScale */
    public static final String COLUMNNAME_FactorScale = "FactorScale";

	/** Set Factor Scale.
	  * Identifies a Factor Scale
	  */
	public void setFactorScale (BigDecimal FactorScale);

	/** Get Factor Scale.
	  * Identifies a Factor Scale
	  */
	public BigDecimal getFactorScale();

    /** Column name FactorUser */
    public static final String COLUMNNAME_FactorUser = "FactorUser";

	/** Set User Factor.
	  * Identifies a User Factor
	  */
	public void setFactorUser (BigDecimal FactorUser);

	/** Get User Factor.
	  * Identifies a User Factor
	  */
	public BigDecimal getFactorUser();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name PP_ForecastDefinitionLine_ID */
    public static final String COLUMNNAME_PP_ForecastDefinitionLine_ID = "PP_ForecastDefinitionLine_ID";

	/** Set Forecast Definition Line	  */
	public void setPP_ForecastDefinitionLine_ID (int PP_ForecastDefinitionLine_ID);

	/** Get Forecast Definition Line	  */
	public int getPP_ForecastDefinitionLine_ID();

	public org.eevolution.model.I_PP_ForecastDefinitionLine getPP_ForecastDefinitionLine() throws RuntimeException;

    /** Column name PP_ForecastRunMaster_ID */
    public static final String COLUMNNAME_PP_ForecastRunMaster_ID = "PP_ForecastRunMaster_ID";

	/** Set Forecast Run Master	  */
	public void setPP_ForecastRunMaster_ID (int PP_ForecastRunMaster_ID);

	/** Get Forecast Run Master	  */
	public int getPP_ForecastRunMaster_ID();

    /** Column name PP_ForecastRun_ID */
    public static final String COLUMNNAME_PP_ForecastRun_ID = "PP_ForecastRun_ID";

	/** Set Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public void setPP_ForecastRun_ID (int PP_ForecastRun_ID);

	/** Get Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public int getPP_ForecastRun_ID();

	public org.eevolution.model.I_PP_ForecastRun getPP_ForecastRun() throws RuntimeException;

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}

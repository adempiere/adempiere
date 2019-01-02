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

/** Generated Interface for PP_ForecastDefinitionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_PP_ForecastDefinitionLine 
{

    /** TableName=PP_ForecastDefinitionLine */
    public static final String Table_Name = "PP_ForecastDefinitionLine";

    /** AD_Table_ID=53398 */
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

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException;

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

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_Class_ID */
    public static final String COLUMNNAME_M_Product_Class_ID = "M_Product_Class_ID";

	/** Set Product Class.
	  * Class of a Product
	  */
	public void setM_Product_Class_ID (int M_Product_Class_ID);

	/** Get Product Class.
	  * Class of a Product
	  */
	public int getM_Product_Class_ID();

	public org.compiere.model.I_M_Product_Class getM_Product_Class() throws RuntimeException;

    /** Column name M_Product_Classification_ID */
    public static final String COLUMNNAME_M_Product_Classification_ID = "M_Product_Classification_ID";

	/** Set Product Classification.
	  * Classification of a Product
	  */
	public void setM_Product_Classification_ID (int M_Product_Classification_ID);

	/** Get Product Classification.
	  * Classification of a Product
	  */
	public int getM_Product_Classification_ID();

	public org.compiere.model.I_M_Product_Classification getM_Product_Classification() throws RuntimeException;

    /** Column name M_Product_Group_ID */
    public static final String COLUMNNAME_M_Product_Group_ID = "M_Product_Group_ID";

	/** Set Product Group.
	  * Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID);

	/** Get Product Group.
	  * Group of a Product
	  */
	public int getM_Product_Group_ID();

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException;

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

    /** Column name PP_ForecastDefinitionLine_ID */
    public static final String COLUMNNAME_PP_ForecastDefinitionLine_ID = "PP_ForecastDefinitionLine_ID";

	/** Set Forecast Definition Line	  */
	public void setPP_ForecastDefinitionLine_ID (int PP_ForecastDefinitionLine_ID);

	/** Get Forecast Definition Line	  */
	public int getPP_ForecastDefinitionLine_ID();

    /** Column name PP_ForecastDefinition_ID */
    public static final String COLUMNNAME_PP_ForecastDefinition_ID = "PP_ForecastDefinition_ID";

	/** Set Forecast Definition	  */
	public void setPP_ForecastDefinition_ID (int PP_ForecastDefinition_ID);

	/** Get Forecast Definition	  */
	public int getPP_ForecastDefinition_ID();

	public org.eevolution.model.I_PP_ForecastDefinition getPP_ForecastDefinition() throws RuntimeException;

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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

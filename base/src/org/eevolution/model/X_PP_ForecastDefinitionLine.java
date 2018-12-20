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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PP_ForecastDefinitionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_ForecastDefinitionLine extends PO implements I_PP_ForecastDefinitionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_ForecastDefinitionLine (Properties ctx, int PP_ForecastDefinitionLine_ID, String trxName)
    {
      super (ctx, PP_ForecastDefinitionLine_ID, trxName);
      /** if (PP_ForecastDefinitionLine_ID == 0)
        {
			setName (null);
			setPP_ForecastDefinitionLine_ID (0);
			setPP_ForecastDefinition_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_ForecastDefinitionLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PP_ForecastDefinitionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (org.compiere.model.I_C_SalesRegion)MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Factor Alpha.
		@param FactorAlpha 
		Identifies an Factor Alpha
	  */
	public void setFactorAlpha (BigDecimal FactorAlpha)
	{
		set_Value (COLUMNNAME_FactorAlpha, FactorAlpha);
	}

	/** Get Factor Alpha.
		@return Identifies an Factor Alpha
	  */
	public BigDecimal getFactorAlpha () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorAlpha);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Beta.
		@param FactorBeta 
		Identifies a Factor Beta
	  */
	public void setFactorBeta (BigDecimal FactorBeta)
	{
		set_Value (COLUMNNAME_FactorBeta, FactorBeta);
	}

	/** Get Factor Beta.
		@return Identifies a Factor Beta
	  */
	public BigDecimal getFactorBeta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorBeta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Gamma.
		@param FactorGamma 
		Identifies a Factor Gamma
	  */
	public void setFactorGamma (BigDecimal FactorGamma)
	{
		set_Value (COLUMNNAME_FactorGamma, FactorGamma);
	}

	/** Get Factor Gamma.
		@return Identifies a Factor Gamma
	  */
	public BigDecimal getFactorGamma () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorGamma);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Multiplier.
		@param FactorMultiplier 
		Identifies a Factor Multiplier
	  */
	public void setFactorMultiplier (BigDecimal FactorMultiplier)
	{
		set_Value (COLUMNNAME_FactorMultiplier, FactorMultiplier);
	}

	/** Get Factor Multiplier.
		@return Identifies a Factor Multiplier
	  */
	public BigDecimal getFactorMultiplier () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorMultiplier);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Scale.
		@param FactorScale 
		Identifies a Factor Scale
	  */
	public void setFactorScale (BigDecimal FactorScale)
	{
		set_Value (COLUMNNAME_FactorScale, FactorScale);
	}

	/** Get Factor Scale.
		@return Identifies a Factor Scale
	  */
	public BigDecimal getFactorScale () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorScale);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set User Factor.
		@param FactorUser 
		Identifies a User Factor
	  */
	public void setFactorUser (BigDecimal FactorUser)
	{
		set_Value (COLUMNNAME_FactorUser, FactorUser);
	}

	/** Get User Factor.
		@return Identifies a User Factor
	  */
	public BigDecimal getFactorUser () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorUser);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Class getM_Product_Class() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Class)MTable.get(getCtx(), org.compiere.model.I_M_Product_Class.Table_Name)
			.getPO(getM_Product_Class_ID(), get_TrxName());	}

	/** Set Product Class.
		@param M_Product_Class_ID 
		Class of a Product
	  */
	public void setM_Product_Class_ID (int M_Product_Class_ID)
	{
		if (M_Product_Class_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Class_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Class_ID, Integer.valueOf(M_Product_Class_ID));
	}

	/** Get Product Class.
		@return Class of a Product
	  */
	public int getM_Product_Class_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Class_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Classification getM_Product_Classification() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Classification)MTable.get(getCtx(), org.compiere.model.I_M_Product_Classification.Table_Name)
			.getPO(getM_Product_Classification_ID(), get_TrxName());	}

	/** Set Product Classification.
		@param M_Product_Classification_ID 
		Classification of a Product
	  */
	public void setM_Product_Classification_ID (int M_Product_Classification_ID)
	{
		if (M_Product_Classification_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Classification_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Classification_ID, Integer.valueOf(M_Product_Classification_ID));
	}

	/** Get Product Classification.
		@return Classification of a Product
	  */
	public int getM_Product_Classification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Classification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Group)MTable.get(getCtx(), org.compiere.model.I_M_Product_Group.Table_Name)
			.getPO(getM_Product_Group_ID(), get_TrxName());	}

	/** Set Product Group.
		@param M_Product_Group_ID 
		Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID)
	{
		if (M_Product_Group_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Group_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Group_ID, Integer.valueOf(M_Product_Group_ID));
	}

	/** Get Product Group.
		@return Group of a Product
	  */
	public int getM_Product_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Forecast Definition Line.
		@param PP_ForecastDefinitionLine_ID Forecast Definition Line	  */
	public void setPP_ForecastDefinitionLine_ID (int PP_ForecastDefinitionLine_ID)
	{
		if (PP_ForecastDefinitionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastDefinitionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastDefinitionLine_ID, Integer.valueOf(PP_ForecastDefinitionLine_ID));
	}

	/** Get Forecast Definition Line.
		@return Forecast Definition Line	  */
	public int getPP_ForecastDefinitionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastDefinitionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_ForecastDefinition getPP_ForecastDefinition() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_ForecastDefinition)MTable.get(getCtx(), org.eevolution.model.I_PP_ForecastDefinition.Table_Name)
			.getPO(getPP_ForecastDefinition_ID(), get_TrxName());	}

	/** Set Forecast Definition.
		@param PP_ForecastDefinition_ID Forecast Definition	  */
	public void setPP_ForecastDefinition_ID (int PP_ForecastDefinition_ID)
	{
		if (PP_ForecastDefinition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastDefinition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastDefinition_ID, Integer.valueOf(PP_ForecastDefinition_ID));
	}

	/** Get Forecast Definition.
		@return Forecast Definition	  */
	public int getPP_ForecastDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}
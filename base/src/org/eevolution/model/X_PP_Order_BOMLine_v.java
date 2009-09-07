/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.adempiere.model.*;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for PP_Order_BOMLine_v
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_PP_Order_BOMLine_v extends PO implements I_PP_Order_BOMLine_v, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_PP_Order_BOMLine_v (Properties ctx, int PP_Order_BOMLine_v_ID, String trxName)
    {
      super (ctx, PP_Order_BOMLine_v_ID, trxName);
      /** if (PP_Order_BOMLine_v_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_PP_Order_BOMLine_v (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_Order_BOMLine_v[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User()
    {
		return (I_AD_User)MTable.get(getCtx(), I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity Assay.
		@param Assay 
		Indicated the Quantity Assay to use into Quality Order
	  */
	public void setAssay (BigDecimal Assay)
	{
		set_Value (COLUMNNAME_Assay, Assay);
	}

	/** Get Quantity Assay.
		@return Indicated the Quantity Assay to use into Quality Order
	  */
	public BigDecimal getAssay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Assay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Backflush Group.
		@param BackflushGroup 
		The Grouping Components to the Backflush
	  */
	public void setBackflushGroup (String BackflushGroup)
	{
		set_Value (COLUMNNAME_BackflushGroup, BackflushGroup);
	}

	/** Get Backflush Group.
		@return The Grouping Components to the Backflush
	  */
	public String getBackflushGroup () 
	{
		return (String)get_Value(COLUMNNAME_BackflushGroup);
	}

	public I_C_UOM getC_UOM()
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ComponentType AD_Reference_ID=53225 */
	public static final int COMPONENTTYPE_AD_Reference_ID=53225;
	/** By-Product = BY */
	public static final String COMPONENTTYPE_By_Product = "BY";
	/** Component = CO */
	public static final String COMPONENTTYPE_Component = "CO";
	/** Phantom = PH */
	public static final String COMPONENTTYPE_Phantom = "PH";
	/** Packing = PK */
	public static final String COMPONENTTYPE_Packing = "PK";
	/** Planning = PL */
	public static final String COMPONENTTYPE_Planning = "PL";
	/** Tools = TL */
	public static final String COMPONENTTYPE_Tools = "TL";
	/** Option = OP */
	public static final String COMPONENTTYPE_Option = "OP";
	/** Variant = VA */
	public static final String COMPONENTTYPE_Variant = "VA";
	/** Co-Product = CP */
	public static final String COMPONENTTYPE_Co_Product = "CP";
	/** Set Component Type.
		@param ComponentType 
		Component Type for a Bill of Material or Formula
	  */
	public void setComponentType (String ComponentType)
	{

		set_Value (COLUMNNAME_ComponentType, ComponentType);
	}

	/** Get Component Type.
		@return Component Type for a Bill of Material or Formula
	  */
	public String getComponentType () 
	{
		return (String)get_Value(COLUMNNAME_ComponentType);
	}

	/** Set Date Delivered.
		@param DateDelivered 
		Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered)
	{
		set_Value (COLUMNNAME_DateDelivered, DateDelivered);
	}

	/** Get Date Delivered.
		@return Date when the product was delivered
	  */
	public Timestamp getDateDelivered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDelivered);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Feature.
		@param Feature 
		Indicated the Feature for Product Configure
	  */
	public void setFeature (String Feature)
	{
		set_Value (COLUMNNAME_Feature, Feature);
	}

	/** Get Feature.
		@return Indicated the Feature for Product Configure
	  */
	public String getFeature () 
	{
		return (String)get_Value(COLUMNNAME_Feature);
	}

	/** Set Forecast.
		@param Forecast 
		Indicated the % of participation this component into a of the BOM Planning
	  */
	public void setForecast (BigDecimal Forecast)
	{
		set_Value (COLUMNNAME_Forecast, Forecast);
	}

	/** Get Forecast.
		@return Indicated the % of participation this component into a of the BOM Planning
	  */
	public BigDecimal getForecast () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Forecast);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Is Critical Component.
		@param IsCritical 
		Indicate that a Manufacturing Order can not begin without have this component
	  */
	public void setIsCritical (boolean IsCritical)
	{
		set_Value (COLUMNNAME_IsCritical, Boolean.valueOf(IsCritical));
	}

	/** Get Is Critical Component.
		@return Indicate that a Manufacturing Order can not begin without have this component
	  */
	public boolean isCritical () 
	{
		Object oo = get_Value(COLUMNNAME_IsCritical);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Qty Percentage.
		@param IsQtyPercentage 
		Indicate that this component is based in % Quantity
	  */
	public void setIsQtyPercentage (BigDecimal IsQtyPercentage)
	{
		set_Value (COLUMNNAME_IsQtyPercentage, IsQtyPercentage);
	}

	/** Get Is Qty Percentage.
		@return Indicate that this component is based in % Quantity
	  */
	public BigDecimal getIsQtyPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IsQtyPercentage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** IssueMethod AD_Reference_ID=53226 */
	public static final int ISSUEMETHOD_AD_Reference_ID=53226;
	/** Issue = 0 */
	public static final String ISSUEMETHOD_Issue = "0";
	/** Backflush = 1 */
	public static final String ISSUEMETHOD_Backflush = "1";
	/** Floor Stock = 2 */
	public static final String ISSUEMETHOD_FloorStock = "2";
	/** Set Issue Method.
		@param IssueMethod 
		There are two methods for issue the components to Manufacturing Order
	  */
	public void setIssueMethod (String IssueMethod)
	{

		set_Value (COLUMNNAME_IssueMethod, IssueMethod);
	}

	/** Get Issue Method.
		@return There are two methods for issue the components to Manufacturing Order
	  */
	public String getIssueMethod () 
	{
		return (String)get_Value(COLUMNNAME_IssueMethod);
	}

	/** Set Lead Time Offset.
		@param LeadTimeOffset 
		Optional Lead Time offest before starting production
	  */
	public void setLeadTimeOffset (int LeadTimeOffset)
	{
		set_Value (COLUMNNAME_LeadTimeOffset, Integer.valueOf(LeadTimeOffset));
	}

	/** Get Lead Time Offset.
		@return Optional Lead Time offest before starting production
	  */
	public int getLeadTimeOffset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeadTimeOffset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance()
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_ChangeNotice getM_ChangeNotice()
    {
		return (I_M_ChangeNotice)MTable.get(getCtx(), I_M_ChangeNotice.Table_Name)
			.getPO(getM_ChangeNotice_ID(), get_TrxName());	}

	/** Set Change Notice.
		@param M_ChangeNotice_ID 
		Bill of Materials (Engineering) Change Notice (Version)
	  */
	public void setM_ChangeNotice_ID (int M_ChangeNotice_ID)
	{
		if (M_ChangeNotice_ID < 1) 
			set_Value (COLUMNNAME_M_ChangeNotice_ID, null);
		else 
			set_Value (COLUMNNAME_M_ChangeNotice_ID, Integer.valueOf(M_ChangeNotice_ID));
	}

	/** Get Change Notice.
		@return Bill of Materials (Engineering) Change Notice (Version)
	  */
	public int getM_ChangeNotice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ChangeNotice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator()
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product()
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
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

	public I_M_Warehouse getM_Warehouse()
    {
		return (I_M_Warehouse)MTable.get(getCtx(), I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_BOM getPP_Order_BOM()
    {
		return (org.eevolution.model.I_PP_Order_BOM)MTable.get(getCtx(), org.eevolution.model.I_PP_Order_BOM.Table_Name)
			.getPO(getPP_Order_BOM_ID(), get_TrxName());	}

	/** Set Manufacturing Order BOM.
		@param PP_Order_BOM_ID Manufacturing Order BOM	  */
	public void setPP_Order_BOM_ID (int PP_Order_BOM_ID)
	{
		if (PP_Order_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_BOM_ID, Integer.valueOf(PP_Order_BOM_ID));
	}

	/** Get Manufacturing Order BOM.
		@return Manufacturing Order BOM	  */
	public int getPP_Order_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_BOMLine getPP_Order_BOMLine()
    {
		return (org.eevolution.model.I_PP_Order_BOMLine)MTable.get(getCtx(), org.eevolution.model.I_PP_Order_BOMLine.Table_Name)
			.getPO(getPP_Order_BOMLine_ID(), get_TrxName());	}

	/** Set Manufacturing Order BOM Line.
		@param PP_Order_BOMLine_ID Manufacturing Order BOM Line	  */
	public void setPP_Order_BOMLine_ID (int PP_Order_BOMLine_ID)
	{
		if (PP_Order_BOMLine_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_BOMLine_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_BOMLine_ID, Integer.valueOf(PP_Order_BOMLine_ID));
	}

	/** Get Manufacturing Order BOM Line.
		@return Manufacturing Order BOM Line	  */
	public int getPP_Order_BOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_BOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order getPP_Order()
    {
		return (org.eevolution.model.I_PP_Order)MTable.get(getCtx(), org.eevolution.model.I_PP_Order.Table_Name)
			.getPO(getPP_Order_ID(), get_TrxName());	}

	/** Set Manufacturing Order.
		@param PP_Order_ID Manufacturing Order	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get Manufacturing Order.
		@return Manufacturing Order	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Available Quantity.
		@param QtyAvailable 
		Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable)
	{
		set_Value (COLUMNNAME_QtyAvailable, QtyAvailable);
	}

	/** Get Available Quantity.
		@return Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAvailable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity in %.
		@param QtyBatch 
		Indicate the Quantity % use in this Formula
	  */
	public void setQtyBatch (BigDecimal QtyBatch)
	{
		set_Value (COLUMNNAME_QtyBatch, QtyBatch);
	}

	/** Get Quantity in %.
		@return Indicate the Quantity % use in this Formula
	  */
	public BigDecimal getQtyBatch () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBatch);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Batch Size.
		@param QtyBatchSize Qty Batch Size	  */
	public void setQtyBatchSize (BigDecimal QtyBatchSize)
	{
		set_Value (COLUMNNAME_QtyBatchSize, QtyBatchSize);
	}

	/** Get Qty Batch Size.
		@return Qty Batch Size	  */
	public BigDecimal getQtyBatchSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBatchSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyBOM 
		Indicate the Quantity  use in this BOM
	  */
	public void setQtyBOM (BigDecimal QtyBOM)
	{
		set_Value (COLUMNNAME_QtyBOM, QtyBOM);
	}

	/** Get Quantity.
		@return Indicate the Quantity  use in this BOM
	  */
	public BigDecimal getQtyBOM () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBOM);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Delivered Quantity.
		@param QtyDelivered 
		Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered)
	{
		set_Value (COLUMNNAME_QtyDelivered, QtyDelivered);
	}

	/** Get Delivered Quantity.
		@return Delivered Quantity
	  */
	public BigDecimal getQtyDelivered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDelivered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set On Hand Quantity.
		@param QtyOnHand 
		On Hand Quantity
	  */
	public void setQtyOnHand (BigDecimal QtyOnHand)
	{
		set_Value (COLUMNNAME_QtyOnHand, QtyOnHand);
	}

	/** Get On Hand Quantity.
		@return On Hand Quantity
	  */
	public BigDecimal getQtyOnHand () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOnHand);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Post.
		@param QtyPost Qty Post	  */
	public void setQtyPost (BigDecimal QtyPost)
	{
		set_Value (COLUMNNAME_QtyPost, QtyPost);
	}

	/** Get Qty Post.
		@return Qty Post	  */
	public BigDecimal getQtyPost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Reject.
		@param QtyReject Qty Reject	  */
	public void setQtyReject (BigDecimal QtyReject)
	{
		set_Value (COLUMNNAME_QtyReject, QtyReject);
	}

	/** Get Qty Reject.
		@return Qty Reject	  */
	public BigDecimal getQtyReject () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReject);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Requiered.
		@param QtyRequiered Qty Requiered	  */
	public void setQtyRequiered (BigDecimal QtyRequiered)
	{
		set_Value (COLUMNNAME_QtyRequiered, QtyRequiered);
	}

	/** Get Qty Requiered.
		@return Qty Requiered	  */
	public BigDecimal getQtyRequiered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyRequiered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reserved Quantity.
		@param QtyReserved 
		Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		set_Value (COLUMNNAME_QtyReserved, QtyReserved);
	}

	/** Get Reserved Quantity.
		@return Reserved Quantity
	  */
	public BigDecimal getQtyReserved () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyScrap.
		@param QtyScrap 
		Scrap Quantity for this componet
	  */
	public void setQtyScrap (BigDecimal QtyScrap)
	{
		set_Value (COLUMNNAME_QtyScrap, QtyScrap);
	}

	/** Get QtyScrap.
		@return Scrap Quantity for this componet
	  */
	public BigDecimal getQtyScrap () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyScrap);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set % Scrap.
		@param Scrap 
		Indicate the % Scrap  for calculate the Scrap Quantity
	  */
	public void setScrap (BigDecimal Scrap)
	{
		set_Value (COLUMNNAME_Scrap, Scrap);
	}

	/** Get % Scrap.
		@return Indicate the % Scrap  for calculate the Scrap Quantity
	  */
	public BigDecimal getScrap () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Scrap);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}
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
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_ReplenishPlan
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_M_ReplenishPlan extends PO implements I_M_ReplenishPlan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_M_ReplenishPlan (Properties ctx, int M_ReplenishPlan_ID, String trxName)
    {
      super (ctx, M_ReplenishPlan_ID, trxName);
      /** if (M_ReplenishPlan_ID == 0)
        {
			setIsDeletePlannedPO (false);
// N
			setIsDeleteUnconfirmedProduction (false);
// N
			setM_ReplenishPlan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_ReplenishPlan (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_ReplenishPlan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_DocType getC_DocType_ConfirmedOr() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ConfirmedOrder(), get_TrxName());	}

	/** Set Confirmed Mfg Order Doc Type.
		@param C_DocType_ConfirmedOrder Confirmed Mfg Order Doc Type	  */
	public void setC_DocType_ConfirmedOrder (int C_DocType_ConfirmedOrder)
	{
		set_Value (COLUMNNAME_C_DocType_ConfirmedOrder, Integer.valueOf(C_DocType_ConfirmedOrder));
	}

	/** Get Confirmed Mfg Order Doc Type.
		@return Confirmed Mfg Order Doc Type	  */
	public int getC_DocType_ConfirmedOrder () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ConfirmedOrder);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_PO(), get_TrxName());	}

	/** Set Purchase Order Doc Type.
		@param C_DocType_PO Purchase Order Doc Type	  */
	public void setC_DocType_PO (int C_DocType_PO)
	{
		set_Value (COLUMNNAME_C_DocType_PO, Integer.valueOf(C_DocType_PO));
	}

	/** Get Purchase Order Doc Type.
		@return Purchase Order Doc Type	  */
	public int getC_DocType_PO () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_PO);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType_PlannedOr() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_PlannedOrder(), get_TrxName());	}

	/** Set Planned Mfg Order Doc Type.
		@param C_DocType_PlannedOrder Planned Mfg Order Doc Type	  */
	public void setC_DocType_PlannedOrder (int C_DocType_PlannedOrder)
	{
		set_Value (COLUMNNAME_C_DocType_PlannedOrder, Integer.valueOf(C_DocType_PlannedOrder));
	}

	/** Get Planned Mfg Order Doc Type.
		@return Planned Mfg Order Doc Type	  */
	public int getC_DocType_PlannedOrder () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_PlannedOrder);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType_Requisit() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_Requisition(), get_TrxName());	}

	/** Set Replenish Plan Requisition Doc Type.
		@param C_DocType_Requisition Replenish Plan Requisition Doc Type	  */
	public void setC_DocType_Requisition (int C_DocType_Requisition)
	{
		set_Value (COLUMNNAME_C_DocType_Requisition, Integer.valueOf(C_DocType_Requisition));
	}

	/** Get Replenish Plan Requisition Doc Type.
		@return Replenish Plan Requisition Doc Type	  */
	public int getC_DocType_Requisition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_Requisition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finish Date.
		@param DateFinish 
		Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish)
	{
		set_Value (COLUMNNAME_DateFinish, DateFinish);
	}

	/** Get Finish Date.
		@return Finish or (planned) completion date
	  */
	public Timestamp getDateFinish () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFinish);
	}

	/** Set Date Start.
		@param DateStart 
		Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart)
	{
		set_Value (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start for this Order
	  */
	public Timestamp getDateStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
	}

	/** Set Generate Report.
		@param GenerateReport Generate Report	  */
	public void setGenerateReport (String GenerateReport)
	{
		set_Value (COLUMNNAME_GenerateReport, GenerateReport);
	}

	/** Get Generate Report.
		@return Generate Report	  */
	public String getGenerateReport () 
	{
		return (String)get_Value(COLUMNNAME_GenerateReport);
	}

	/** Set Delete Planned Purchase Orders.
		@param IsDeletePlannedPO Delete Planned Purchase Orders	  */
	public void setIsDeletePlannedPO (boolean IsDeletePlannedPO)
	{
		set_Value (COLUMNNAME_IsDeletePlannedPO, Boolean.valueOf(IsDeletePlannedPO));
	}

	/** Get Delete Planned Purchase Orders.
		@return Delete Planned Purchase Orders	  */
	public boolean isDeletePlannedPO () 
	{
		Object oo = get_Value(COLUMNNAME_IsDeletePlannedPO);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Delete Unconfirmed Production.
		@param IsDeleteUnconfirmedProduction Delete Unconfirmed Production	  */
	public void setIsDeleteUnconfirmedProduction (boolean IsDeleteUnconfirmedProduction)
	{
		set_Value (COLUMNNAME_IsDeleteUnconfirmedProduction, Boolean.valueOf(IsDeleteUnconfirmedProduction));
	}

	/** Get Delete Unconfirmed Production.
		@return Delete Unconfirmed Production	  */
	public boolean isDeleteUnconfirmedProduction () 
	{
		Object oo = get_Value(COLUMNNAME_IsDeleteUnconfirmedProduction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceList_ID(), get_TrxName());	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_ReplenishPlan ID.
		@param M_ReplenishPlan_ID M_ReplenishPlan ID	  */
	public void setM_ReplenishPlan_ID (int M_ReplenishPlan_ID)
	{
		if (M_ReplenishPlan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_ReplenishPlan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_ReplenishPlan_ID, Integer.valueOf(M_ReplenishPlan_ID));
	}

	/** Get M_ReplenishPlan ID.
		@return M_ReplenishPlan ID	  */
	public int getM_ReplenishPlan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ReplenishPlan_ID);
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

	/** Set Generate Planned Production Report.
		@param PlannedProductionReport Generate Planned Production Report	  */
	public void setPlannedProductionReport (String PlannedProductionReport)
	{
		set_Value (COLUMNNAME_PlannedProductionReport, PlannedProductionReport);
	}

	/** Get Generate Planned Production Report.
		@return Generate Planned Production Report	  */
	public String getPlannedProductionReport () 
	{
		return (String)get_Value(COLUMNNAME_PlannedProductionReport);
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Replenish Plan Initial Setup.
		@param ReplenishPlanInitialSetup Replenish Plan Initial Setup	  */
	public void setReplenishPlanInitialSetup (String ReplenishPlanInitialSetup)
	{
		set_Value (COLUMNNAME_ReplenishPlanInitialSetup, ReplenishPlanInitialSetup);
	}

	/** Get Replenish Plan Initial Setup.
		@return Replenish Plan Initial Setup	  */
	public String getReplenishPlanInitialSetup () 
	{
		return (String)get_Value(COLUMNNAME_ReplenishPlanInitialSetup);
	}

	/** Set Generate Suggested Requisition Report.
		@param SuggestedRequisitionReport Generate Suggested Requisition Report	  */
	public void setSuggestedRequisitionReport (String SuggestedRequisitionReport)
	{
		set_Value (COLUMNNAME_SuggestedRequisitionReport, SuggestedRequisitionReport);
	}

	/** Get Generate Suggested Requisition Report.
		@return Generate Suggested Requisition Report	  */
	public String getSuggestedRequisitionReport () 
	{
		return (String)get_Value(COLUMNNAME_SuggestedRequisitionReport);
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
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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for PP_ForecastRun
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_ForecastRun extends PO implements I_PP_ForecastRun, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_ForecastRun (Properties ctx, int PP_ForecastRun_ID, String trxName)
    {
      super (ctx, PP_ForecastRun_ID, trxName);
      /** if (PP_ForecastRun_ID == 0)
        {
			setDescription (null);
			setDocumentNo (null);
			setM_WarehouseSource_ID (0);
			setPP_Calendar_ID (0);
			setPP_ForecastDefinition_ID (0);
			setPP_ForecastRule_ID (0);
			setPP_ForecastRun_ID (0);
			setPP_PeriodDefinition_ID (0);
			setRef_DefinitionPeriod_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_ForecastRun (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_ForecastRun[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDocumentNo());
    }

	public org.compiere.model.I_M_Warehouse getM_WarehouseSource() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_WarehouseSource_ID(), get_TrxName());	}

	/** Set Source Warehouse.
		@param M_WarehouseSource_ID 
		Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
	{
		if (M_WarehouseSource_ID < 1) 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, null);
		else 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, Integer.valueOf(M_WarehouseSource_ID));
	}

	/** Get Source Warehouse.
		@return Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 0) 
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

	public org.eevolution.model.I_PP_Calendar getPP_Calendar() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Calendar)MTable.get(getCtx(), org.eevolution.model.I_PP_Calendar.Table_Name)
			.getPO(getPP_Calendar_ID(), get_TrxName());	}

	/** Set Operational Calendar.
		@param PP_Calendar_ID 
		Operational Period, allows to define the periods for the Operational Calendar
	  */
	public void setPP_Calendar_ID (int PP_Calendar_ID)
	{
		if (PP_Calendar_ID < 1) 
			set_Value (COLUMNNAME_PP_Calendar_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Calendar_ID, Integer.valueOf(PP_Calendar_ID));
	}

	/** Get Operational Calendar.
		@return Operational Period, allows to define the periods for the Operational Calendar
	  */
	public int getPP_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Calendar_ID);
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
			set_Value (COLUMNNAME_PP_ForecastDefinition_ID, null);
		else 
			set_Value (COLUMNNAME_PP_ForecastDefinition_ID, Integer.valueOf(PP_ForecastDefinition_ID));
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

	public org.eevolution.model.I_PP_ForecastRule getPP_ForecastRule() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_ForecastRule)MTable.get(getCtx(), org.eevolution.model.I_PP_ForecastRule.Table_Name)
			.getPO(getPP_ForecastRule_ID(), get_TrxName());	}

	/** Set Forecast Rule.
		@param PP_ForecastRule_ID 
		Forecast Rules define the business logic according to a previously implemented algorithm.
	  */
	public void setPP_ForecastRule_ID (int PP_ForecastRule_ID)
	{
		if (PP_ForecastRule_ID < 1) 
			set_Value (COLUMNNAME_PP_ForecastRule_ID, null);
		else 
			set_Value (COLUMNNAME_PP_ForecastRule_ID, Integer.valueOf(PP_ForecastRule_ID));
	}

	/** Get Forecast Rule.
		@return Forecast Rules define the business logic according to a previously implemented algorithm.
	  */
	public int getPP_ForecastRule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastRule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Forecast Run.
		@param PP_ForecastRun_ID 
		Create the forecast simulation based on the forecast definition
	  */
	public void setPP_ForecastRun_ID (int PP_ForecastRun_ID)
	{
		if (PP_ForecastRun_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRun_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRun_ID, Integer.valueOf(PP_ForecastRun_ID));
	}

	/** Get Forecast Run.
		@return Create the forecast simulation based on the forecast definition
	  */
	public int getPP_ForecastRun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastRun_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_PeriodDefinition getPP_PeriodDefinition() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_PeriodDefinition)MTable.get(getCtx(), org.eevolution.model.I_PP_PeriodDefinition.Table_Name)
			.getPO(getPP_PeriodDefinition_ID(), get_TrxName());	}

	/** Set Current Period.
		@param PP_PeriodDefinition_ID 
		Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setPP_PeriodDefinition_ID (int PP_PeriodDefinition_ID)
	{
		if (PP_PeriodDefinition_ID < 1) 
			set_Value (COLUMNNAME_PP_PeriodDefinition_ID, null);
		else 
			set_Value (COLUMNNAME_PP_PeriodDefinition_ID, Integer.valueOf(PP_PeriodDefinition_ID));
	}

	/** Get Current Period.
		@return Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getPP_PeriodDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_PeriodDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Periods of History.
		@param PeriodHistory 
		Number Period of History
	  */
	public void setPeriodHistory (int PeriodHistory)
	{
		set_Value (COLUMNNAME_PeriodHistory, Integer.valueOf(PeriodHistory));
	}

	/** Get Periods of History.
		@return Number Period of History
	  */
	public int getPeriodHistory () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PeriodHistory);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public org.eevolution.model.I_PP_PeriodDefinition getRef_DefinitionPeriod() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_PeriodDefinition)MTable.get(getCtx(), org.eevolution.model.I_PP_PeriodDefinition.Table_Name)
			.getPO(getRef_DefinitionPeriod_ID(), get_TrxName());	}

	/** Set Past Period Definition.
		@param Ref_DefinitionPeriod_ID 
		Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setRef_DefinitionPeriod_ID (int Ref_DefinitionPeriod_ID)
	{
		if (Ref_DefinitionPeriod_ID < 1) 
			set_Value (COLUMNNAME_Ref_DefinitionPeriod_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_DefinitionPeriod_ID, Integer.valueOf(Ref_DefinitionPeriod_ID));
	}

	/** Get Past Period Definition.
		@return Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getRef_DefinitionPeriod_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_DefinitionPeriod_ID);
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
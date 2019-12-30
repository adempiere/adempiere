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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for R_RequestUpdate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_R_RequestUpdate extends PO implements I_R_RequestUpdate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_R_RequestUpdate (Properties ctx, int R_RequestUpdate_ID, String trxName)
    {
      super (ctx, R_RequestUpdate_ID, trxName);
      /** if (R_RequestUpdate_ID == 0)
        {
			setConfidentialTypeEntry (null);
			setR_RequestUpdate_ID (0);
			setR_Request_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_RequestUpdate (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_R_RequestUpdate[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ConfidentialTypeEntry AD_Reference_ID=340 */
	public static final int CONFIDENTIALTYPEENTRY_AD_Reference_ID=340;
	/** Public Information = A */
	public static final String CONFIDENTIALTYPEENTRY_PublicInformation = "A";
	/** Partner Confidential = C */
	public static final String CONFIDENTIALTYPEENTRY_PartnerConfidential = "C";
	/** Internal = I */
	public static final String CONFIDENTIALTYPEENTRY_Internal = "I";
	/** Private Information = P */
	public static final String CONFIDENTIALTYPEENTRY_PrivateInformation = "P";
	/** Set Entry Confidentiality.
		@param ConfidentialTypeEntry 
		Confidentiality of the individual entry
	  */
	public void setConfidentialTypeEntry (String ConfidentialTypeEntry)
	{

		set_Value (COLUMNNAME_ConfidentialTypeEntry, ConfidentialTypeEntry);
	}

	/** Get Entry Confidentiality.
		@return Confidentiality of the individual entry
	  */
	public String getConfidentialTypeEntry () 
	{
		return (String)get_Value(COLUMNNAME_ConfidentialTypeEntry);
	}

	/** Set End Time.
		@param EndTime 
		End of the time span
	  */
	public void setEndTime (Timestamp EndTime)
	{
		set_Value (COLUMNNAME_EndTime, EndTime);
	}

	/** Get End Time.
		@return End of the time span
	  */
	public Timestamp getEndTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndTime);
	}

	public org.compiere.model.I_M_Product getM_ProductSpent() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductSpent_ID(), get_TrxName());	}

	/** Set Product Used.
		@param M_ProductSpent_ID 
		Product/Resource/Service used in Request
	  */
	public void setM_ProductSpent_ID (int M_ProductSpent_ID)
	{
		if (M_ProductSpent_ID < 1) 
			set_Value (COLUMNNAME_M_ProductSpent_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductSpent_ID, Integer.valueOf(M_ProductSpent_ID));
	}

	/** Get Product Used.
		@return Product/Resource/Service used in Request
	  */
	public int getM_ProductSpent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductSpent_ID);
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

	/** Set Quantity Invoiced.
		@param QtyInvoiced 
		Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		set_Value (COLUMNNAME_QtyInvoiced, QtyInvoiced);
	}

	/** Get Quantity Invoiced.
		@return Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInvoiced);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Used.
		@param QtySpent 
		Quantity used for this event
	  */
	public void setQtySpent (BigDecimal QtySpent)
	{
		set_Value (COLUMNNAME_QtySpent, QtySpent);
	}

	/** Get Quantity Used.
		@return Quantity used for this event
	  */
	public BigDecimal getQtySpent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtySpent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_R_RequestAction getR_RequestAction() throws RuntimeException
    {
		return (org.compiere.model.I_R_RequestAction)MTable.get(getCtx(), org.compiere.model.I_R_RequestAction.Table_Name)
			.getPO(getR_RequestAction_ID(), get_TrxName());	}

	/** Set Request History.
		@param R_RequestAction_ID 
		Request has been changed
	  */
	public void setR_RequestAction_ID (int R_RequestAction_ID)
	{
		if (R_RequestAction_ID < 1) 
			set_Value (COLUMNNAME_R_RequestAction_ID, null);
		else 
			set_Value (COLUMNNAME_R_RequestAction_ID, Integer.valueOf(R_RequestAction_ID));
	}

	/** Get Request History.
		@return Request has been changed
	  */
	public int getR_RequestAction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestAction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Request Update.
		@param R_RequestUpdate_ID 
		Request Updates
	  */
	public void setR_RequestUpdate_ID (int R_RequestUpdate_ID)
	{
		if (R_RequestUpdate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_RequestUpdate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_RequestUpdate_ID, Integer.valueOf(R_RequestUpdate_ID));
	}

	/** Get Request Update.
		@return Request Updates
	  */
	public int getR_RequestUpdate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestUpdate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getR_RequestUpdate_ID()));
    }

	public org.compiere.model.I_R_Request getR_Request() throws RuntimeException
    {
		return (org.compiere.model.I_R_Request)MTable.get(getCtx(), org.compiere.model.I_R_Request.Table_Name)
			.getPO(getR_Request_ID(), get_TrxName());	}

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_Request_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Result.
		@param Result 
		Result of the action taken
	  */
	public void setResult (String Result)
	{
		set_ValueNoCheck (COLUMNNAME_Result, Result);
	}

	/** Get Result.
		@return Result of the action taken
	  */
	public String getResult () 
	{
		return (String)get_Value(COLUMNNAME_Result);
	}

	/** Set Start Time.
		@param StartTime 
		Time started
	  */
	public void setStartTime (Timestamp StartTime)
	{
		set_Value (COLUMNNAME_StartTime, StartTime);
	}

	/** Get Start Time.
		@return Time started
	  */
	public Timestamp getStartTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartTime);
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
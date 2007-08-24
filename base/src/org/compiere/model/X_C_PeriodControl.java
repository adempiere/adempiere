/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for C_PeriodControl
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_PeriodControl extends PO implements I_C_PeriodControl, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_PeriodControl (Properties ctx, int C_PeriodControl_ID, String trxName)
    {
      super (ctx, C_PeriodControl_ID, trxName);
      /** if (C_PeriodControl_ID == 0)        {			setC_PeriodControl_ID (0);
			setC_Period_ID (0);
			setDocBaseType (null);
			setPeriodAction (null);
// N
} */
    }

    /** Load Constructor */
    public X_C_PeriodControl (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_PeriodControl[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Period Control.
		@param C_PeriodControl_ID Period Control	  */
	public void setC_PeriodControl_ID (int C_PeriodControl_ID)
	{
		if (C_PeriodControl_ID < 1)
			 throw new IllegalArgumentException ("C_PeriodControl_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_PeriodControl_ID, Integer.valueOf(C_PeriodControl_ID));
	}

	/** Get Period Control.
@return Period Control	  */
	public int getC_PeriodControl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PeriodControl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_PeriodControl_ID()));
    }

	public I_C_Period getI_C_Period() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Period.Table_Name);
        I_C_Period result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Period)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Period_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1)
			 throw new IllegalArgumentException ("C_Period_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

/** DocBaseType AD_Reference_ID=183 */
public static final int DOCBASETYPE_AD_Reference_ID=183;/** AP Credit Memo = APC */
public static final String DOCBASETYPE_APCreditMemo = "APC";/** AP Invoice = API */
public static final String DOCBASETYPE_APInvoice = "API";/** AP Payment = APP */
public static final String DOCBASETYPE_APPayment = "APP";/** AR Credit Memo = ARC */
public static final String DOCBASETYPE_ARCreditMemo = "ARC";/** AR Pro Forma Invoice = ARF */
public static final String DOCBASETYPE_ARProFormaInvoice = "ARF";/** AR Invoice = ARI */
public static final String DOCBASETYPE_ARInvoice = "ARI";/** AR Receipt = ARR */
public static final String DOCBASETYPE_ARReceipt = "ARR";/** Payment Allocation = CMA */
public static final String DOCBASETYPE_PaymentAllocation = "CMA";/** Bank Statement = CMB */
public static final String DOCBASETYPE_BankStatement = "CMB";/** Cash Journal = CMC */
public static final String DOCBASETYPE_CashJournal = "CMC";/** GL Document = GLD */
public static final String DOCBASETYPE_GLDocument = "GLD";/** GL Journal = GLJ */
public static final String DOCBASETYPE_GLJournal = "GLJ";/** Material Physical Inventory = MMI */
public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";/** Material Movement = MMM */
public static final String DOCBASETYPE_MaterialMovement = "MMM";/** Material Production = MMP */
public static final String DOCBASETYPE_MaterialProduction = "MMP";/** Material Receipt = MMR */
public static final String DOCBASETYPE_MaterialReceipt = "MMR";/** Material Delivery = MMS */
public static final String DOCBASETYPE_MaterialDelivery = "MMS";/** Match Invoice = MXI */
public static final String DOCBASETYPE_MatchInvoice = "MXI";/** Match PO = MXP */
public static final String DOCBASETYPE_MatchPO = "MXP";/** Project Issue = PJI */
public static final String DOCBASETYPE_ProjectIssue = "PJI";/** Purchase Order = POO */
public static final String DOCBASETYPE_PurchaseOrder = "POO";/** Purchase Requisition = POR */
public static final String DOCBASETYPE_PurchaseRequisition = "POR";/** Sales Order = SOO */
public static final String DOCBASETYPE_SalesOrder = "SOO";
	/** Set Document BaseType.
		@param DocBaseType 
		Logical type of document
	  */
	public void setDocBaseType (String DocBaseType)
	{
if (DocBaseType == null) throw new IllegalArgumentException ("DocBaseType is mandatory");if (DocBaseType.equals("APC") || DocBaseType.equals("API") || DocBaseType.equals("APP") || DocBaseType.equals("ARC") || DocBaseType.equals("ARF") || DocBaseType.equals("ARI") || DocBaseType.equals("ARR") || DocBaseType.equals("CMA") || DocBaseType.equals("CMB") || DocBaseType.equals("CMC") || DocBaseType.equals("GLD") || DocBaseType.equals("GLJ") || DocBaseType.equals("MMI") || DocBaseType.equals("MMM") || DocBaseType.equals("MMP") || DocBaseType.equals("MMR") || DocBaseType.equals("MMS") || DocBaseType.equals("MXI") || DocBaseType.equals("MXP") || DocBaseType.equals("PJI") || DocBaseType.equals("POO") || DocBaseType.equals("POR") || DocBaseType.equals("SOO")); else throw new IllegalArgumentException ("DocBaseType Invalid value - " + DocBaseType + " - Reference_ID=183 - APC - API - APP - ARC - ARF - ARI - ARR - CMA - CMB - CMC - GLD - GLJ - MMI - MMM - MMP - MMR - MMS - MXI - MXP - PJI - POO - POR - SOO");		if (DocBaseType.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			DocBaseType = DocBaseType.substring(0, 2);
		}
		set_ValueNoCheck (COLUMNNAME_DocBaseType, DocBaseType);
	}

	/** Get Document BaseType.
		@return Logical type of document
	  */
	public String getDocBaseType () 
	{
		return (String)get_Value(COLUMNNAME_DocBaseType);
	}

/** PeriodAction AD_Reference_ID=176 */
public static final int PERIODACTION_AD_Reference_ID=176;/** Close Period = C */
public static final String PERIODACTION_ClosePeriod = "C";/** <No Action> = N */
public static final String PERIODACTION_NoAction = "N";/** Open Period = O */
public static final String PERIODACTION_OpenPeriod = "O";/** Permanently Close Period = P */
public static final String PERIODACTION_PermanentlyClosePeriod = "P";
	/** Set Period Action.
		@param PeriodAction 
		Action taken for this period
	  */
	public void setPeriodAction (String PeriodAction)
	{
if (PeriodAction == null) throw new IllegalArgumentException ("PeriodAction is mandatory");if (PeriodAction.equals("C") || PeriodAction.equals("N") || PeriodAction.equals("O") || PeriodAction.equals("P")); else throw new IllegalArgumentException ("PeriodAction Invalid value - " + PeriodAction + " - Reference_ID=176 - C - N - O - P");		if (PeriodAction.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PeriodAction = PeriodAction.substring(0, 0);
		}
		set_Value (COLUMNNAME_PeriodAction, PeriodAction);
	}

	/** Get Period Action.
		@return Action taken for this period
	  */
	public String getPeriodAction () 
	{
		return (String)get_Value(COLUMNNAME_PeriodAction);
	}

/** PeriodStatus AD_Reference_ID=177 */
public static final int PERIODSTATUS_AD_Reference_ID=177;/** Closed = C */
public static final String PERIODSTATUS_Closed = "C";/** Never opened = N */
public static final String PERIODSTATUS_NeverOpened = "N";/** Open = O */
public static final String PERIODSTATUS_Open = "O";/** Permanently closed = P */
public static final String PERIODSTATUS_PermanentlyClosed = "P";
	/** Set Period Status.
		@param PeriodStatus 
		Current state of this period
	  */
	public void setPeriodStatus (String PeriodStatus)
	{
if (PeriodStatus == null || PeriodStatus.equals("C") || PeriodStatus.equals("N") || PeriodStatus.equals("O") || PeriodStatus.equals("P")); else throw new IllegalArgumentException ("PeriodStatus Invalid value - " + PeriodStatus + " - Reference_ID=177 - C - N - O - P");		if (PeriodStatus != null && PeriodStatus.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PeriodStatus = PeriodStatus.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_PeriodStatus, PeriodStatus);
	}

	/** Get Period Status.
		@return Current state of this period
	  */
	public String getPeriodStatus () 
	{
		return (String)get_Value(COLUMNNAME_PeriodStatus);
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
}
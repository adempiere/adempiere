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
package org.compiere.model;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for C_GATEPASS
 *  @author Adempiere (generated) 
 *  @version R/3 - $Id$ */
public class X_C_GATEPASS extends PO implements I_C_GATEPASS, I_Persistent , DocAction
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150911L;
    private String m_processMsg = null;
    /** Standard Constructor */
    public X_C_GATEPASS (Properties ctx, int C_GATEPASS_ID, String trxName)
    {
      super (ctx, C_GATEPASS_ID, trxName);
      /** if (C_GATEPASS_ID == 0)
        {
			setC_GATEPASS_ID (0);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setIsApproved (false);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_C_GATEPASS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_GATEPASS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Gate Pass for Invoice Form ID.
		@param C_GATEPASS_ID Gate Pass for Invoice Form ID	  */
	public void setC_GATEPASS_ID (int C_GATEPASS_ID)
	{
		if (C_GATEPASS_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_GATEPASS_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_GATEPASS_ID, Integer.valueOf(C_GATEPASS_ID));
	}

	/** Get Gate Pass for Invoice Form ID.
		@return Gate Pass for Invoice Form ID	  */
	public int getC_GATEPASS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_GATEPASS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Driver Licence Expiry Date.
		@param DLExpiryDate Driver Licence Expiry Date	  */
	public void setDLExpiryDate (Timestamp DLExpiryDate)
	{
		set_Value (COLUMNNAME_DLExpiryDate, DLExpiryDate);
	}

	/** Get Driver Licence Expiry Date.
		@return Driver Licence Expiry Date	  */
	public Timestamp getDLExpiryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DLExpiryDate);
	}

	/** Set DL No.
		@param DLNumber DL No	  */
	public void setDLNumber (int DLNumber)
	{
		set_Value (COLUMNNAME_DLNumber, Integer.valueOf(DLNumber));
	}

	/** Get DL No.
		@return DL No	  */
	public int getDLNumber () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DLNumber);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Destination.
		@param Destination Destination	  */
	public void setDestination (String Destination)
	{
		set_Value (COLUMNNAME_Destination, Destination);
	}

	/** Get Destination.
		@return Destination	  */
	public String getDestination () 
	{
		return (String)get_Value(COLUMNNAME_Destination);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** Set Document Date.
		@param DocDate Document Date	  */
	public void setDocDate (Timestamp DocDate)
	{
		set_Value (COLUMNNAME_DocDate, DocDate);
	}

	/** Get Document Date.
		@return Document Date	  */
	public Timestamp getDocDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DocDate);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Driver Name.
		@param DrName Driver Name	  */
	public void setDrName (String DrName)
	{
		set_Value (COLUMNNAME_DrName, DrName);
	}

	/** Get Driver Name.
		@return Driver Name	  */
	public String getDrName () 
	{
		return (String)get_Value(COLUMNNAME_DrName);
	}

	/** Set Dry Weight.
		@param DryWeight Dry Weight	  */
	public void setDryWeight (BigDecimal DryWeight)
	{
		set_Value (COLUMNNAME_DryWeight, DryWeight);
	}

	/** Get Dry Weight.
		@return Dry Weight	  */
	public BigDecimal getDryWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DryWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set GR Number.
		@param GRNumber GR Number	  */
	public void setGRNumber (int GRNumber)
	{
		set_Value (COLUMNNAME_GRNumber, Integer.valueOf(GRNumber));
	}

	/** Get GR Number.
		@return GR Number	  */
	public int getGRNumber () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GRNumber);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Material Weight.
		@param MatWeight Material Weight	  */
	public void setMatWeight (BigDecimal MatWeight)
	{
		set_Value (COLUMNNAME_MatWeight, MatWeight);
	}

	/** Get Material Weight.
		@return Material Weight	  */
	public BigDecimal getMatWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MatWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set PANCARD.
		@param PANCARD PANCARD	  */
	public void setPANCARD (int PANCARD)
	{
		set_Value (COLUMNNAME_PANCARD, Integer.valueOf(PANCARD));
	}

	/** Get PANCARD.
		@return PANCARD	  */
	public int getPANCARD () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PANCARD);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pay Load.
		@param PayLoad Pay Load	  */
	public void setPayLoad (BigDecimal PayLoad)
	{
		set_Value (COLUMNNAME_PayLoad, PayLoad);
	}

	/** Get Pay Load.
		@return Pay Load	  */
	public BigDecimal getPayLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Total Weight.
		@param TotalWeight Total Weight	  */
	public void setTotalWeight (BigDecimal TotalWeight)
	{
		set_Value (COLUMNNAME_TotalWeight, TotalWeight);
	}

	/** Get Total Weight.
		@return Total Weight	  */
	public BigDecimal getTotalWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transporter.
		@param Transporter Transporter	  */
	public void setTransporter (String Transporter)
	{
		set_Value (COLUMNNAME_Transporter, Transporter);
	}

	/** Get Transporter.
		@return Transporter	  */
	public String getTransporter () 
	{
		return (String)get_Value(COLUMNNAME_Transporter);
	}

	/** Set Vehicle Make.
		@param VMake Vehicle Make	  */
	public void setVMake (String VMake)
	{
		set_Value (COLUMNNAME_VMake, VMake);
	}

	/** Get Vehicle Make.
		@return Vehicle Make	  */
	public String getVMake () 
	{
		return (String)get_Value(COLUMNNAME_VMake);
	}

	/** Set Vehicle Number.
		@param VNumber Vehicle Number	  */
	public void setVNumber (String VNumber)
	{
		set_Value (COLUMNNAME_VNumber, VNumber);
	}

	/** Get Vehicle Number.
		@return Vehicle Number	  */
	public String getVNumber () 
	{
		return (String)get_Value(COLUMNNAME_VNumber);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    public boolean processIt(String action) throws Exception {

        m_processMsg = null;
        DocumentEngine engine = new DocumentEngine(this, getDocStatus());
        return engine.processIt(action, getDocAction());

    }


    public boolean unlockIt() {

        return false;
    }

    /**  Before Save    */

    /**
     protected boolean beforeSave(boolean newRecord) {
     boolean haveEnoughLeaves = Boolean.FALSE;


     return haveEnoughLeaves;
     }
     */


    public boolean invalidateIt() {

        return false;
    }


    public String prepareIt() {

        log.info(toString());
        m_processMsg = ModelValidationEngine.get().fireDocValidate(this,
                ModelValidator.TIMING_BEFORE_PREPARE);
        if (m_processMsg != null)
            return DocAction.STATUS_Invalid;

        return DocAction.STATUS_InProgress;
    }


    public boolean approveIt()
    {

        if ( ! isApproved())
        {
            setIsApproved( Boolean.TRUE );
        }
        return true;
    }


    public boolean rejectIt() {

        return false;
    }



    protected boolean beforeSave(boolean newRecord)
    {
        boolean DateCheck = Boolean.TRUE;
        return DateCheck;
    }


    public String completeIt()
    {

        if ( isApproved() )	{
            setIsApproved(Boolean.TRUE);
        }

        setProcessed(true);
        //
        setDocAction(DOCACTION_Close);
        return DocAction.STATUS_Completed;

    }


    public boolean voidIt() {

        return false;
    }


    public boolean closeIt() {

        return false;
    }


    public boolean reverseCorrectIt() {

        return false;
    }


    public boolean reverseAccrualIt() {

        return false;
    }


    public boolean reActivateIt() {

        return false;
    }


    public String getSummary() {

        return null;
    }


    public String getDocumentNo() {

        return null;
    }


    public String getDocumentInfo() {

        return null;
    }


    public File createPDF() {

        return null;
    }


    public String getProcessMsg() {

        return m_processMsg;
    }


    public int getDoc_User_ID() {

        return 0;
    }


    public int getC_Currency_ID() {

        return 0;
    }


    public BigDecimal getApprovalAmt() {

        return null;
    }
}
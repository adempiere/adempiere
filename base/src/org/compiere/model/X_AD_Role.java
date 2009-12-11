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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Role
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a - $Id$ */
public class X_AD_Role extends PO implements I_AD_Role, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20091210L;

    /** Standard Constructor */
    public X_AD_Role (Properties ctx, int AD_Role_ID, String trxName)
    {
      super (ctx, AD_Role_ID, trxName);
      /** if (AD_Role_ID == 0)
        {
			setAD_Role_ID (0);
			setAllow_Info_Account (true);
// Y
			setAllow_Info_Asset (true);
// Y
			setAllow_Info_BPartner (true);
// Y
			setAllow_Info_CashJournal (true);
// Y
			setAllow_Info_CRP (true);
// Y
			setAllow_Info_InOut (true);
// Y
			setAllow_Info_Invoice (true);
// Y
			setAllow_Info_MRP (true);
// Y
			setAllow_Info_Order (true);
// Y
			setAllow_Info_Payment (true);
// Y
			setAllow_Info_Product (true);
// Y
			setAllow_Info_Resource (true);
// Y
			setAllow_Info_Schedule (true);
// Y
			setConfirmQueryRecords (0);
// 0
			setIsAccessAllOrgs (false);
// N
			setIsCanApproveOwnDoc (false);
			setIsCanExport (true);
// Y
			setIsCanReport (true);
// Y
			setIsChangeLog (false);
// N
			setIsDiscountAllowedOnTotal (false);
			setIsDiscountUptoLimitPrice (false);
			setIsManual (false);
			setIsPersonalAccess (false);
// N
			setIsPersonalLock (false);
// N
			setIsShowAcct (false);
// N
			setIsUseUserOrgAccess (false);
// N
			setMaxQueryRecords (0);
// 0
			setName (null);
			setOverwritePriceLimit (false);
// N
			setPreferenceType (null);
// O
			setUserLevel (null);
// O
        } */
    }

    /** Load Constructor */
    public X_AD_Role (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_Role[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Menu() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Menu_ID(), get_TrxName());	}

	/** Set Menu Tree.
		@param AD_Tree_Menu_ID 
		Tree of the menu
	  */
	public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID)
	{
		if (AD_Tree_Menu_ID < 1) 
			set_Value (COLUMNNAME_AD_Tree_Menu_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Tree_Menu_ID, Integer.valueOf(AD_Tree_Menu_ID));
	}

	/** Get Menu Tree.
		@return Tree of the menu
	  */
	public int getAD_Tree_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Org() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Org_ID(), get_TrxName());	}

	/** Set Organization Tree.
		@param AD_Tree_Org_ID 
		Tree to determine organizational hierarchy
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID)
	{
		if (AD_Tree_Org_ID < 1) 
			set_Value (COLUMNNAME_AD_Tree_Org_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Tree_Org_ID, Integer.valueOf(AD_Tree_Org_ID));
	}

	/** Get Organization Tree.
		@return Tree to determine organizational hierarchy
	  */
	public int getAD_Tree_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allow Info Account.
		@param Allow_Info_Account Allow Info Account	  */
	public void setAllow_Info_Account (boolean Allow_Info_Account)
	{
		set_Value (COLUMNNAME_Allow_Info_Account, Boolean.valueOf(Allow_Info_Account));
	}

	/** Get Allow Info Account.
		@return Allow Info Account	  */
	public boolean isAllow_Info_Account () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Account);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Asset.
		@param Allow_Info_Asset Allow Info Asset	  */
	public void setAllow_Info_Asset (boolean Allow_Info_Asset)
	{
		set_Value (COLUMNNAME_Allow_Info_Asset, Boolean.valueOf(Allow_Info_Asset));
	}

	/** Get Allow Info Asset.
		@return Allow Info Asset	  */
	public boolean isAllow_Info_Asset () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Asset);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info BPartner.
		@param Allow_Info_BPartner Allow Info BPartner	  */
	public void setAllow_Info_BPartner (boolean Allow_Info_BPartner)
	{
		set_Value (COLUMNNAME_Allow_Info_BPartner, Boolean.valueOf(Allow_Info_BPartner));
	}

	/** Get Allow Info BPartner.
		@return Allow Info BPartner	  */
	public boolean isAllow_Info_BPartner () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_BPartner);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info CashJournal.
		@param Allow_Info_CashJournal Allow Info CashJournal	  */
	public void setAllow_Info_CashJournal (boolean Allow_Info_CashJournal)
	{
		set_Value (COLUMNNAME_Allow_Info_CashJournal, Boolean.valueOf(Allow_Info_CashJournal));
	}

	/** Get Allow Info CashJournal.
		@return Allow Info CashJournal	  */
	public boolean isAllow_Info_CashJournal () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_CashJournal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info CRP.
		@param Allow_Info_CRP Allow Info CRP	  */
	public void setAllow_Info_CRP (boolean Allow_Info_CRP)
	{
		set_Value (COLUMNNAME_Allow_Info_CRP, Boolean.valueOf(Allow_Info_CRP));
	}

	/** Get Allow Info CRP.
		@return Allow Info CRP	  */
	public boolean isAllow_Info_CRP () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_CRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info InOut.
		@param Allow_Info_InOut Allow Info InOut	  */
	public void setAllow_Info_InOut (boolean Allow_Info_InOut)
	{
		set_Value (COLUMNNAME_Allow_Info_InOut, Boolean.valueOf(Allow_Info_InOut));
	}

	/** Get Allow Info InOut.
		@return Allow Info InOut	  */
	public boolean isAllow_Info_InOut () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_InOut);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Invoice.
		@param Allow_Info_Invoice Allow Info Invoice	  */
	public void setAllow_Info_Invoice (boolean Allow_Info_Invoice)
	{
		set_Value (COLUMNNAME_Allow_Info_Invoice, Boolean.valueOf(Allow_Info_Invoice));
	}

	/** Get Allow Info Invoice.
		@return Allow Info Invoice	  */
	public boolean isAllow_Info_Invoice () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Invoice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info MRP.
		@param Allow_Info_MRP Allow Info MRP	  */
	public void setAllow_Info_MRP (boolean Allow_Info_MRP)
	{
		set_Value (COLUMNNAME_Allow_Info_MRP, Boolean.valueOf(Allow_Info_MRP));
	}

	/** Get Allow Info MRP.
		@return Allow Info MRP	  */
	public boolean isAllow_Info_MRP () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_MRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Order.
		@param Allow_Info_Order Allow Info Order	  */
	public void setAllow_Info_Order (boolean Allow_Info_Order)
	{
		set_Value (COLUMNNAME_Allow_Info_Order, Boolean.valueOf(Allow_Info_Order));
	}

	/** Get Allow Info Order.
		@return Allow Info Order	  */
	public boolean isAllow_Info_Order () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Order);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Payment.
		@param Allow_Info_Payment Allow Info Payment	  */
	public void setAllow_Info_Payment (boolean Allow_Info_Payment)
	{
		set_Value (COLUMNNAME_Allow_Info_Payment, Boolean.valueOf(Allow_Info_Payment));
	}

	/** Get Allow Info Payment.
		@return Allow Info Payment	  */
	public boolean isAllow_Info_Payment () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Payment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Product.
		@param Allow_Info_Product Allow Info Product	  */
	public void setAllow_Info_Product (boolean Allow_Info_Product)
	{
		set_Value (COLUMNNAME_Allow_Info_Product, Boolean.valueOf(Allow_Info_Product));
	}

	/** Get Allow Info Product.
		@return Allow Info Product	  */
	public boolean isAllow_Info_Product () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Product);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Resource.
		@param Allow_Info_Resource Allow Info Resource	  */
	public void setAllow_Info_Resource (boolean Allow_Info_Resource)
	{
		set_Value (COLUMNNAME_Allow_Info_Resource, Boolean.valueOf(Allow_Info_Resource));
	}

	/** Get Allow Info Resource.
		@return Allow Info Resource	  */
	public boolean isAllow_Info_Resource () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Resource);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Info Schedule.
		@param Allow_Info_Schedule Allow Info Schedule	  */
	public void setAllow_Info_Schedule (boolean Allow_Info_Schedule)
	{
		set_Value (COLUMNNAME_Allow_Info_Schedule, Boolean.valueOf(Allow_Info_Schedule));
	}

	/** Get Allow Info Schedule.
		@return Allow Info Schedule	  */
	public boolean isAllow_Info_Schedule () 
	{
		Object oo = get_Value(COLUMNNAME_Allow_Info_Schedule);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Approval Amount.
		@param AmtApproval 
		The approval amount limit for this role
	  */
	public void setAmtApproval (BigDecimal AmtApproval)
	{
		set_Value (COLUMNNAME_AmtApproval, AmtApproval);
	}

	/** Get Approval Amount.
		@return The approval amount limit for this role
	  */
	public BigDecimal getAmtApproval () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtApproval);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Currency getC_Currency() throws RuntimeException
    {
		return (I_C_Currency)MTable.get(getCtx(), I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Confirm Query Records.
		@param ConfirmQueryRecords 
		Require Confirmation if more records will be returned by the query (If not defined 500)
	  */
	public void setConfirmQueryRecords (int ConfirmQueryRecords)
	{
		set_Value (COLUMNNAME_ConfirmQueryRecords, Integer.valueOf(ConfirmQueryRecords));
	}

	/** Get Confirm Query Records.
		@return Require Confirmation if more records will be returned by the query (If not defined 500)
	  */
	public int getConfirmQueryRecords () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConfirmQueryRecords);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ConnectionProfile AD_Reference_ID=364 */
	public static final int CONNECTIONPROFILE_AD_Reference_ID=364;
	/** LAN = L */
	public static final String CONNECTIONPROFILE_LAN = "L";
	/** Terminal Server = T */
	public static final String CONNECTIONPROFILE_TerminalServer = "T";
	/** VPN = V */
	public static final String CONNECTIONPROFILE_VPN = "V";
	/** WAN = W */
	public static final String CONNECTIONPROFILE_WAN = "W";
	/** Set Connection Profile.
		@param ConnectionProfile 
		How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile)
	{

		set_Value (COLUMNNAME_ConnectionProfile, ConnectionProfile);
	}

	/** Get Connection Profile.
		@return How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile () 
	{
		return (String)get_Value(COLUMNNAME_ConnectionProfile);
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

	/** Set Access all Orgs.
		@param IsAccessAllOrgs 
		Access all Organizations (no org access control) of the client
	  */
	public void setIsAccessAllOrgs (boolean IsAccessAllOrgs)
	{
		set_Value (COLUMNNAME_IsAccessAllOrgs, Boolean.valueOf(IsAccessAllOrgs));
	}

	/** Get Access all Orgs.
		@return Access all Organizations (no org access control) of the client
	  */
	public boolean isAccessAllOrgs () 
	{
		Object oo = get_Value(COLUMNNAME_IsAccessAllOrgs);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Approve own Documents.
		@param IsCanApproveOwnDoc 
		Users with this role can approve their own documents
	  */
	public void setIsCanApproveOwnDoc (boolean IsCanApproveOwnDoc)
	{
		set_Value (COLUMNNAME_IsCanApproveOwnDoc, Boolean.valueOf(IsCanApproveOwnDoc));
	}

	/** Get Approve own Documents.
		@return Users with this role can approve their own documents
	  */
	public boolean isCanApproveOwnDoc () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanApproveOwnDoc);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Can Export.
		@param IsCanExport 
		Users with this role can export data
	  */
	public void setIsCanExport (boolean IsCanExport)
	{
		set_Value (COLUMNNAME_IsCanExport, Boolean.valueOf(IsCanExport));
	}

	/** Get Can Export.
		@return Users with this role can export data
	  */
	public boolean isCanExport () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanExport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Can Report.
		@param IsCanReport 
		Users with this role can create reports
	  */
	public void setIsCanReport (boolean IsCanReport)
	{
		set_Value (COLUMNNAME_IsCanReport, Boolean.valueOf(IsCanReport));
	}

	/** Get Can Report.
		@return Users with this role can create reports
	  */
	public boolean isCanReport () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanReport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Maintain Change Log.
		@param IsChangeLog 
		Maintain a log of changes
	  */
	public void setIsChangeLog (boolean IsChangeLog)
	{
		set_Value (COLUMNNAME_IsChangeLog, Boolean.valueOf(IsChangeLog));
	}

	/** Get Maintain Change Log.
		@return Maintain a log of changes
	  */
	public boolean isChangeLog () 
	{
		Object oo = get_Value(COLUMNNAME_IsChangeLog);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsDiscountAllowedOnTotal.
		@param IsDiscountAllowedOnTotal IsDiscountAllowedOnTotal	  */
	public void setIsDiscountAllowedOnTotal (boolean IsDiscountAllowedOnTotal)
	{
		set_Value (COLUMNNAME_IsDiscountAllowedOnTotal, Boolean.valueOf(IsDiscountAllowedOnTotal));
	}

	/** Get IsDiscountAllowedOnTotal.
		@return IsDiscountAllowedOnTotal	  */
	public boolean isDiscountAllowedOnTotal () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountAllowedOnTotal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsDiscountUptoLimitPrice.
		@param IsDiscountUptoLimitPrice IsDiscountUptoLimitPrice	  */
	public void setIsDiscountUptoLimitPrice (boolean IsDiscountUptoLimitPrice)
	{
		set_Value (COLUMNNAME_IsDiscountUptoLimitPrice, Boolean.valueOf(IsDiscountUptoLimitPrice));
	}

	/** Get IsDiscountUptoLimitPrice.
		@return IsDiscountUptoLimitPrice	  */
	public boolean isDiscountUptoLimitPrice () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountUptoLimitPrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Manual.
		@param IsManual 
		This is a manual process
	  */
	public void setIsManual (boolean IsManual)
	{
		set_Value (COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
	}

	/** Get Manual.
		@return This is a manual process
	  */
	public boolean isManual () 
	{
		Object oo = get_Value(COLUMNNAME_IsManual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Personal Access.
		@param IsPersonalAccess 
		Allow access to all personal records
	  */
	public void setIsPersonalAccess (boolean IsPersonalAccess)
	{
		set_Value (COLUMNNAME_IsPersonalAccess, Boolean.valueOf(IsPersonalAccess));
	}

	/** Get Personal Access.
		@return Allow access to all personal records
	  */
	public boolean isPersonalAccess () 
	{
		Object oo = get_Value(COLUMNNAME_IsPersonalAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Personal Lock.
		@param IsPersonalLock 
		Allow users with role to lock access to personal records
	  */
	public void setIsPersonalLock (boolean IsPersonalLock)
	{
		set_Value (COLUMNNAME_IsPersonalLock, Boolean.valueOf(IsPersonalLock));
	}

	/** Get Personal Lock.
		@return Allow users with role to lock access to personal records
	  */
	public boolean isPersonalLock () 
	{
		Object oo = get_Value(COLUMNNAME_IsPersonalLock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Show Accounting.
		@param IsShowAcct 
		Users with this role can see accounting information
	  */
	public void setIsShowAcct (boolean IsShowAcct)
	{
		set_Value (COLUMNNAME_IsShowAcct, Boolean.valueOf(IsShowAcct));
	}

	/** Get Show Accounting.
		@return Users with this role can see accounting information
	  */
	public boolean isShowAcct () 
	{
		Object oo = get_Value(COLUMNNAME_IsShowAcct);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Use User Org Access.
		@param IsUseUserOrgAccess 
		Use Org Access defined by user instead of Role Org Access
	  */
	public void setIsUseUserOrgAccess (boolean IsUseUserOrgAccess)
	{
		set_Value (COLUMNNAME_IsUseUserOrgAccess, Boolean.valueOf(IsUseUserOrgAccess));
	}

	/** Get Use User Org Access.
		@return Use Org Access defined by user instead of Role Org Access
	  */
	public boolean isUseUserOrgAccess () 
	{
		Object oo = get_Value(COLUMNNAME_IsUseUserOrgAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Max Query Records.
		@param MaxQueryRecords 
		If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public void setMaxQueryRecords (int MaxQueryRecords)
	{
		set_Value (COLUMNNAME_MaxQueryRecords, Integer.valueOf(MaxQueryRecords));
	}

	/** Get Max Query Records.
		@return If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public int getMaxQueryRecords () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxQueryRecords);
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

	/** Set Overwrite Price Limit.
		@param OverwritePriceLimit 
		Overwrite Price Limit if the Price List  enforces the Price Limit
	  */
	public void setOverwritePriceLimit (boolean OverwritePriceLimit)
	{
		set_Value (COLUMNNAME_OverwritePriceLimit, Boolean.valueOf(OverwritePriceLimit));
	}

	/** Get Overwrite Price Limit.
		@return Overwrite Price Limit if the Price List  enforces the Price Limit
	  */
	public boolean isOverwritePriceLimit () 
	{
		Object oo = get_Value(COLUMNNAME_OverwritePriceLimit);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** PreferenceType AD_Reference_ID=330 */
	public static final int PREFERENCETYPE_AD_Reference_ID=330;
	/** Client = C */
	public static final String PREFERENCETYPE_Client = "C";
	/** Organization = O */
	public static final String PREFERENCETYPE_Organization = "O";
	/** User = U */
	public static final String PREFERENCETYPE_User = "U";
	/** None = N */
	public static final String PREFERENCETYPE_None = "N";
	/** Set Preference Level.
		@param PreferenceType 
		Determines what preferences the user can set
	  */
	public void setPreferenceType (String PreferenceType)
	{

		set_Value (COLUMNNAME_PreferenceType, PreferenceType);
	}

	/** Get Preference Level.
		@return Determines what preferences the user can set
	  */
	public String getPreferenceType () 
	{
		return (String)get_Value(COLUMNNAME_PreferenceType);
	}

	public I_AD_User getSupervisor() throws RuntimeException
    {
		return (I_AD_User)MTable.get(getCtx(), I_AD_User.Table_Name)
			.getPO(getSupervisor_ID(), get_TrxName());	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_Value (COLUMNNAME_Supervisor_ID, null);
		else 
			set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UserDiscount.
		@param UserDiscount UserDiscount	  */
	public void setUserDiscount (BigDecimal UserDiscount)
	{
		set_Value (COLUMNNAME_UserDiscount, UserDiscount);
	}

	/** Get UserDiscount.
		@return UserDiscount	  */
	public BigDecimal getUserDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UserDiscount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** UserLevel AD_Reference_ID=226 */
	public static final int USERLEVEL_AD_Reference_ID=226;
	/** System = S   */
	public static final String USERLEVEL_System = "S  ";
	/** Client =  C  */
	public static final String USERLEVEL_Client = " C ";
	/** Organization =   O */
	public static final String USERLEVEL_Organization = "  O";
	/** Client+Organization =  CO */
	public static final String USERLEVEL_ClientPlusOrganization = " CO";
	/** Set User Level.
		@param UserLevel 
		System Client Organization
	  */
	public void setUserLevel (String UserLevel)
	{

		set_Value (COLUMNNAME_UserLevel, UserLevel);
	}

	/** Get User Level.
		@return System Client Organization
	  */
	public String getUserLevel () 
	{
		return (String)get_Value(COLUMNNAME_UserLevel);
	}
}
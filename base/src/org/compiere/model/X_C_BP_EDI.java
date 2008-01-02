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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_BP_EDI
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_C_BP_EDI extends PO implements I_C_BP_EDI, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BP_EDI (Properties ctx, int C_BP_EDI_ID, String trxName)
    {
      super (ctx, C_BP_EDI_ID, trxName);
      /** if (C_BP_EDI_ID == 0)
        {
			setAD_Sequence_ID (0);
			setC_BP_EDI_ID (0);
			setC_BPartner_ID (0);
			setCustomerNo (null);
			setEDIType (null);
			setEMail_Error_To (null);
			setEMail_Info_To (null);
			setIsAudited (false);
			setIsInfoSent (false);
			setM_Warehouse_ID (0);
			setName (null);
			setReceiveInquiryReply (false);
			setReceiveOrderReply (false);
			setSendInquiry (false);
			setSendOrder (false);
        } */
    }

    /** Load Constructor */
    public X_C_BP_EDI (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BP_EDI[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_Sequence_ID AD_Reference_ID=128 */
	public static final int AD_SEQUENCE_ID_AD_Reference_ID=128;
	/** Set Sequence.
		@param AD_Sequence_ID 
		Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID)
	{
		if (AD_Sequence_ID < 1)
			 throw new IllegalArgumentException ("AD_Sequence_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Sequence_ID, Integer.valueOf(AD_Sequence_ID));
	}

	/** Get Sequence.
		@return Document Sequence
	  */
	public int getAD_Sequence_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Sequence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EDI Definition.
		@param C_BP_EDI_ID 
		Electronic Data Interchange
	  */
	public void setC_BP_EDI_ID (int C_BP_EDI_ID)
	{
		if (C_BP_EDI_ID < 1)
			 throw new IllegalArgumentException ("C_BP_EDI_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BP_EDI_ID, Integer.valueOf(C_BP_EDI_ID));
	}

	/** Get EDI Definition.
		@return Electronic Data Interchange
	  */
	public int getC_BP_EDI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_EDI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartner() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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

	/** Set Customer No.
		@param CustomerNo 
		EDI Identification Number 
	  */
	public void setCustomerNo (String CustomerNo)
	{
		if (CustomerNo == null)
			throw new IllegalArgumentException ("CustomerNo is mandatory.");

		if (CustomerNo.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			CustomerNo = CustomerNo.substring(0, 20);
		}
		set_Value (COLUMNNAME_CustomerNo, CustomerNo);
	}

	/** Get Customer No.
		@return EDI Identification Number 
	  */
	public String getCustomerNo () 
	{
		return (String)get_Value(COLUMNNAME_CustomerNo);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{

		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 255);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EDIType AD_Reference_ID=201 */
	public static final int EDITYPE_AD_Reference_ID=201;
	/** ASC X12  = X */
	public static final String EDITYPE_ASCX12 = "X";
	/** EDIFACT = E */
	public static final String EDITYPE_EDIFACT = "E";
	/** Email EDI = M */
	public static final String EDITYPE_EmailEDI = "M";
	/** Set EDI Type.
		@param EDIType EDI Type	  */
	public void setEDIType (String EDIType)
	{
		if (EDIType == null) throw new IllegalArgumentException ("EDIType is mandatory");
		if (EDIType.equals("X") || EDIType.equals("E") || EDIType.equals("M")); else throw new IllegalArgumentException ("EDIType Invalid value - " + EDIType + " - Reference_ID=201 - X - E - M");
		if (EDIType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			EDIType = EDIType.substring(0, 1);
		}
		set_Value (COLUMNNAME_EDIType, EDIType);
	}

	/** Get EDI Type.
		@return EDI Type	  */
	public String getEDIType () 
	{
		return (String)get_Value(COLUMNNAME_EDIType);
	}

	/** Set Error EMail.
		@param EMail_Error_To 
		Email address to send error messages to
	  */
	public void setEMail_Error_To (String EMail_Error_To)
	{
		if (EMail_Error_To == null)
			throw new IllegalArgumentException ("EMail_Error_To is mandatory.");

		if (EMail_Error_To.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			EMail_Error_To = EMail_Error_To.substring(0, 60);
		}
		set_Value (COLUMNNAME_EMail_Error_To, EMail_Error_To);
	}

	/** Get Error EMail.
		@return Email address to send error messages to
	  */
	public String getEMail_Error_To () 
	{
		return (String)get_Value(COLUMNNAME_EMail_Error_To);
	}

	/** Set From EMail.
		@param EMail_From 
		Full EMail address used to send requests - e.g. edi@organization.com
	  */
	public void setEMail_From (String EMail_From)
	{

		if (EMail_From != null && EMail_From.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			EMail_From = EMail_From.substring(0, 60);
		}
		set_Value (COLUMNNAME_EMail_From, EMail_From);
	}

	/** Get From EMail.
		@return Full EMail address used to send requests - e.g. edi@organization.com
	  */
	public String getEMail_From () 
	{
		return (String)get_Value(COLUMNNAME_EMail_From);
	}

	/** Set From EMail Password.
		@param EMail_From_Pwd 
		Password of the sending EMail address
	  */
	public void setEMail_From_Pwd (String EMail_From_Pwd)
	{

		if (EMail_From_Pwd != null && EMail_From_Pwd.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			EMail_From_Pwd = EMail_From_Pwd.substring(0, 20);
		}
		set_Value (COLUMNNAME_EMail_From_Pwd, EMail_From_Pwd);
	}

	/** Get From EMail Password.
		@return Password of the sending EMail address
	  */
	public String getEMail_From_Pwd () 
	{
		return (String)get_Value(COLUMNNAME_EMail_From_Pwd);
	}

	/** Set From EMail User ID.
		@param EMail_From_Uid 
		User ID of the sending EMail address (on default SMTP Host) - e.g. edi
	  */
	public void setEMail_From_Uid (String EMail_From_Uid)
	{

		if (EMail_From_Uid != null && EMail_From_Uid.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			EMail_From_Uid = EMail_From_Uid.substring(0, 20);
		}
		set_Value (COLUMNNAME_EMail_From_Uid, EMail_From_Uid);
	}

	/** Get From EMail User ID.
		@return User ID of the sending EMail address (on default SMTP Host) - e.g. edi
	  */
	public String getEMail_From_Uid () 
	{
		return (String)get_Value(COLUMNNAME_EMail_From_Uid);
	}

	/** Set Info EMail.
		@param EMail_Info_To 
		EMail address to send informational messages and copies
	  */
	public void setEMail_Info_To (String EMail_Info_To)
	{
		if (EMail_Info_To == null)
			throw new IllegalArgumentException ("EMail_Info_To is mandatory.");

		if (EMail_Info_To.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			EMail_Info_To = EMail_Info_To.substring(0, 60);
		}
		set_Value (COLUMNNAME_EMail_Info_To, EMail_Info_To);
	}

	/** Get Info EMail.
		@return EMail address to send informational messages and copies
	  */
	public String getEMail_Info_To () 
	{
		return (String)get_Value(COLUMNNAME_EMail_Info_To);
	}

	/** Set To EMail.
		@param EMail_To 
		EMail address to send requests to - e.g. edi@manufacturer.com 
	  */
	public void setEMail_To (String EMail_To)
	{

		if (EMail_To != null && EMail_To.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			EMail_To = EMail_To.substring(0, 60);
		}
		set_Value (COLUMNNAME_EMail_To, EMail_To);
	}

	/** Get To EMail.
		@return EMail address to send requests to - e.g. edi@manufacturer.com 
	  */
	public String getEMail_To () 
	{
		return (String)get_Value(COLUMNNAME_EMail_To);
	}

	/** Set Activate Audit.
		@param IsAudited 
		Activate Audit Trail of what numbers are generated
	  */
	public void setIsAudited (boolean IsAudited)
	{
		set_Value (COLUMNNAME_IsAudited, Boolean.valueOf(IsAudited));
	}

	/** Get Activate Audit.
		@return Activate Audit Trail of what numbers are generated
	  */
	public boolean isAudited () 
	{
		Object oo = get_Value(COLUMNNAME_IsAudited);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Send Info.
		@param IsInfoSent 
		Send informational messages and copies
	  */
	public void setIsInfoSent (boolean IsInfoSent)
	{
		set_Value (COLUMNNAME_IsInfoSent, Boolean.valueOf(IsInfoSent));
	}

	/** Get Send Info.
		@return Send informational messages and copies
	  */
	public boolean isInfoSent () 
	{
		Object oo = get_Value(COLUMNNAME_IsInfoSent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Warehouse getM_Warehouse() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 60);
		}
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

	/** Set Received Inquiry Reply.
		@param ReceiveInquiryReply Received Inquiry Reply	  */
	public void setReceiveInquiryReply (boolean ReceiveInquiryReply)
	{
		set_Value (COLUMNNAME_ReceiveInquiryReply, Boolean.valueOf(ReceiveInquiryReply));
	}

	/** Get Received Inquiry Reply.
		@return Received Inquiry Reply	  */
	public boolean isReceiveInquiryReply () 
	{
		Object oo = get_Value(COLUMNNAME_ReceiveInquiryReply);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Receive Order Reply.
		@param ReceiveOrderReply Receive Order Reply	  */
	public void setReceiveOrderReply (boolean ReceiveOrderReply)
	{
		set_Value (COLUMNNAME_ReceiveOrderReply, Boolean.valueOf(ReceiveOrderReply));
	}

	/** Get Receive Order Reply.
		@return Receive Order Reply	  */
	public boolean isReceiveOrderReply () 
	{
		Object oo = get_Value(COLUMNNAME_ReceiveOrderReply);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Send Inquiry.
		@param SendInquiry 
		Quantity Availability Inquiry
	  */
	public void setSendInquiry (boolean SendInquiry)
	{
		set_Value (COLUMNNAME_SendInquiry, Boolean.valueOf(SendInquiry));
	}

	/** Get Send Inquiry.
		@return Quantity Availability Inquiry
	  */
	public boolean isSendInquiry () 
	{
		Object oo = get_Value(COLUMNNAME_SendInquiry);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Send Order.
		@param SendOrder Send Order	  */
	public void setSendOrder (boolean SendOrder)
	{
		set_Value (COLUMNNAME_SendOrder, Boolean.valueOf(SendOrder));
	}

	/** Get Send Order.
		@return Send Order	  */
	public boolean isSendOrder () 
	{
		Object oo = get_Value(COLUMNNAME_SendOrder);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}
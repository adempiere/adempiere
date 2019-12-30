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
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for W_Counter
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_W_Counter extends PO implements I_W_Counter, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_W_Counter (Properties ctx, int W_Counter_ID, String trxName)
    {
      super (ctx, W_Counter_ID, trxName);
      /** if (W_Counter_ID == 0)
        {
			setPageURL (null);
			setProcessed (false);
			setRemote_Addr (null);
			setRemote_Host (null);
			setW_Counter_ID (0);
        } */
    }

    /** Load Constructor */
    public X_W_Counter (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_W_Counter[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set Accept Language.
		@param AcceptLanguage 
		Language accepted based on browser information
	  */
	public void setAcceptLanguage (String AcceptLanguage)
	{
		set_Value (COLUMNNAME_AcceptLanguage, AcceptLanguage);
	}

	/** Get Accept Language.
		@return Language accepted based on browser information
	  */
	public String getAcceptLanguage () 
	{
		return (String)get_Value(COLUMNNAME_AcceptLanguage);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Page URL.
		@param PageURL Page URL	  */
	public void setPageURL (String PageURL)
	{
		set_Value (COLUMNNAME_PageURL, PageURL);
	}

	/** Get Page URL.
		@return Page URL	  */
	public String getPageURL () 
	{
		return (String)get_Value(COLUMNNAME_PageURL);
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

	/** Set Referrer.
		@param Referrer 
		Referring web address
	  */
	public void setReferrer (String Referrer)
	{
		set_Value (COLUMNNAME_Referrer, Referrer);
	}

	/** Get Referrer.
		@return Referring web address
	  */
	public String getReferrer () 
	{
		return (String)get_Value(COLUMNNAME_Referrer);
	}

	/** Set Remote Addr.
		@param Remote_Addr 
		Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr)
	{
		set_Value (COLUMNNAME_Remote_Addr, Remote_Addr);
	}

	/** Get Remote Addr.
		@return Remote Address
	  */
	public String getRemote_Addr () 
	{
		return (String)get_Value(COLUMNNAME_Remote_Addr);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getRemote_Addr());
    }

	/** Set Remote Host.
		@param Remote_Host 
		Remote host Info
	  */
	public void setRemote_Host (String Remote_Host)
	{
		set_Value (COLUMNNAME_Remote_Host, Remote_Host);
	}

	/** Get Remote Host.
		@return Remote host Info
	  */
	public String getRemote_Host () 
	{
		return (String)get_Value(COLUMNNAME_Remote_Host);
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

	/** Set User Agent.
		@param UserAgent 
		Browser Used
	  */
	public void setUserAgent (String UserAgent)
	{
		set_Value (COLUMNNAME_UserAgent, UserAgent);
	}

	/** Get User Agent.
		@return Browser Used
	  */
	public String getUserAgent () 
	{
		return (String)get_Value(COLUMNNAME_UserAgent);
	}

	public org.compiere.model.I_W_CounterCount getW_CounterCount() throws RuntimeException
    {
		return (org.compiere.model.I_W_CounterCount)MTable.get(getCtx(), org.compiere.model.I_W_CounterCount.Table_Name)
			.getPO(getW_CounterCount_ID(), get_TrxName());	}

	/** Set Counter Count.
		@param W_CounterCount_ID 
		Web Counter Count Management
	  */
	public void setW_CounterCount_ID (int W_CounterCount_ID)
	{
		if (W_CounterCount_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_CounterCount_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_CounterCount_ID, Integer.valueOf(W_CounterCount_ID));
	}

	/** Get Counter Count.
		@return Web Counter Count Management
	  */
	public int getW_CounterCount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_CounterCount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Web Counter.
		@param W_Counter_ID 
		Individual Count hit
	  */
	public void setW_Counter_ID (int W_Counter_ID)
	{
		if (W_Counter_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_Counter_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_Counter_ID, Integer.valueOf(W_Counter_ID));
	}

	/** Get Web Counter.
		@return Individual Count hit
	  */
	public int getW_Counter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Counter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}
/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_QueueType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_QueueType extends PO implements I_AD_QueueType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_QueueType (Properties ctx, int AD_QueueType_ID, String trxName)
    {
      super (ctx, AD_QueueType_ID, trxName);
      /** if (AD_QueueType_ID == 0)
        {
			setAD_QueueType_ID (0);
			setClassname (null);
			setName (null);
			setQueueType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_QueueType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_QueueType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Queue Type.
		@param AD_QueueType_ID Queue Type	  */
	public void setAD_QueueType_ID (int AD_QueueType_ID)
	{
		if (AD_QueueType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_QueueType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_QueueType_ID, Integer.valueOf(AD_QueueType_ID));
	}

	/** Get Queue Type.
		@return Queue Type	  */
	public int getAD_QueueType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_QueueType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
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

	/** QueueType AD_Reference_ID=54274 */
	public static final int QUEUETYPE_AD_Reference_ID=54274;
	/** System Notification = NTF */
	public static final String QUEUETYPE_SystemNotification = "NTF";
	/** Queue Test Loader = QTL */
	public static final String QUEUETYPE_QueueTestLoader = "QTL";
	/** Set Queue Type.
		@param QueueType 
		Queue Type define the queue implementation for manage
	  */
	public void setQueueType (String QueueType)
	{

		set_Value (COLUMNNAME_QueueType, QueueType);
	}

	/** Get Queue Type.
		@return Queue Type define the queue implementation for manage
	  */
	public String getQueueType () 
	{
		return (String)get_Value(COLUMNNAME_QueueType);
	}

	/** TimeUnit AD_Reference_ID=53376 */
	public static final int TIMEUNIT_AD_Reference_ID=53376;
	/** Day = D */
	public static final String TIMEUNIT_Day = "D";
	/** Week = W */
	public static final String TIMEUNIT_Week = "W";
	/** Month = M */
	public static final String TIMEUNIT_Month = "M";
	/** Quarter = Q */
	public static final String TIMEUNIT_Quarter = "Q";
	/** Year = Y */
	public static final String TIMEUNIT_Year = "Y";
	/** Hour = H */
	public static final String TIMEUNIT_Hour = "H";
	/** Minute = I */
	public static final String TIMEUNIT_Minute = "I";
	/** Set Time Unit.
		@param TimeUnit 
		The unit of time for grouping chart data.
	  */
	public void setTimeUnit (String TimeUnit)
	{

		set_Value (COLUMNNAME_TimeUnit, TimeUnit);
	}

	/** Get Time Unit.
		@return The unit of time for grouping chart data.
	  */
	public String getTimeUnit () 
	{
		return (String)get_Value(COLUMNNAME_TimeUnit);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set Waiting Time.
		@param WaitingTime 
		Workflow Simulation Waiting time
	  */
	public void setWaitingTime (int WaitingTime)
	{
		set_Value (COLUMNNAME_WaitingTime, Integer.valueOf(WaitingTime));
	}

	/** Get Waiting Time.
		@return Workflow Simulation Waiting time
	  */
	public int getWaitingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WaitingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}
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

/** Generated Model for R_IssueProject
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_R_IssueProject extends PO implements I_R_IssueProject, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_IssueProject (Properties ctx, int R_IssueProject_ID, String trxName)
    {
      super (ctx, R_IssueProject_ID, trxName);
      /** if (R_IssueProject_ID == 0)
        {
			setName (null);
			setR_IssueProject_ID (0);
			setSystemStatus (null);
        } */
    }

    /** Load Constructor */
    public X_R_IssueProject (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_IssueProject[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_A_Asset getA_Asset() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset.Table_Name);
        I_A_Asset result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID <= 0) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Project getC_Project() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Project.Table_Name);
        I_C_Project result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Project)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Project_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID <= 0) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Name = Name.substring(0, 120);
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

	/** Set Profile.
		@param ProfileInfo 
		Information to help profiling the system for solving support issues
	  */
	public void setProfileInfo (String ProfileInfo)
	{

		if (ProfileInfo != null && ProfileInfo.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			ProfileInfo = ProfileInfo.substring(0, 60);
		}
		set_Value (COLUMNNAME_ProfileInfo, ProfileInfo);
	}

	/** Get Profile.
		@return Information to help profiling the system for solving support issues
	  */
	public String getProfileInfo () 
	{
		return (String)get_Value(COLUMNNAME_ProfileInfo);
	}

	/** Set Issue Project.
		@param R_IssueProject_ID 
		Implementation Projects
	  */
	public void setR_IssueProject_ID (int R_IssueProject_ID)
	{
		if (R_IssueProject_ID < 1)
			 throw new IllegalArgumentException ("R_IssueProject_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_IssueProject_ID, Integer.valueOf(R_IssueProject_ID));
	}

	/** Get Issue Project.
		@return Implementation Projects
	  */
	public int getR_IssueProject_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_IssueProject_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Statistics.
		@param StatisticsInfo 
		Information to help profiling the system for solving support issues
	  */
	public void setStatisticsInfo (String StatisticsInfo)
	{

		if (StatisticsInfo != null && StatisticsInfo.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			StatisticsInfo = StatisticsInfo.substring(0, 60);
		}
		set_Value (COLUMNNAME_StatisticsInfo, StatisticsInfo);
	}

	/** Get Statistics.
		@return Information to help profiling the system for solving support issues
	  */
	public String getStatisticsInfo () 
	{
		return (String)get_Value(COLUMNNAME_StatisticsInfo);
	}

	/** SystemStatus AD_Reference_ID=374 */
	public static final int SYSTEMSTATUS_AD_Reference_ID=374;
	/** Evaluation = E */
	public static final String SYSTEMSTATUS_Evaluation = "E";
	/** Implementation = I */
	public static final String SYSTEMSTATUS_Implementation = "I";
	/** Production = P */
	public static final String SYSTEMSTATUS_Production = "P";
	/** Set System Status.
		@param SystemStatus 
		Status of the system - Support priority depends on system status
	  */
	public void setSystemStatus (String SystemStatus)
	{
		if (SystemStatus == null) throw new IllegalArgumentException ("SystemStatus is mandatory");
		if (SystemStatus.equals("E") || SystemStatus.equals("I") || SystemStatus.equals("P")); else throw new IllegalArgumentException ("SystemStatus Invalid value - " + SystemStatus + " - Reference_ID=374 - E - I - P");
		if (SystemStatus.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			SystemStatus = SystemStatus.substring(0, 1);
		}
		set_Value (COLUMNNAME_SystemStatus, SystemStatus);
	}

	/** Get System Status.
		@return Status of the system - Support priority depends on system status
	  */
	public String getSystemStatus () 
	{
		return (String)get_Value(COLUMNNAME_SystemStatus);
	}
}
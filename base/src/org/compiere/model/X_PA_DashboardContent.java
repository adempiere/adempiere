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
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PA_DashboardContent
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PA_DashboardContent extends PO implements I_PA_DashboardContent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PA_DashboardContent (Properties ctx, int PA_DashboardContent_ID, String trxName)
    {
      super (ctx, PA_DashboardContent_ID, trxName);
      /** if (PA_DashboardContent_ID == 0)
        {
			setIsCollapsible (true);
// Y
			setName (null);
			setPA_DashboardContent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PA_DashboardContent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PA_DashboardContent[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.model.I_AD_Browse getAD_Browse() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_Browse)MTable.get(getCtx(), org.adempiere.model.I_AD_Browse.Table_Name)
			.getPO(getAD_Browse_ID(), get_TrxName());	}

	/** Set Smart Browse.
		@param AD_Browse_ID Smart Browse	  */
	public void setAD_Browse_ID (int AD_Browse_ID)
	{
		if (AD_Browse_ID < 1) 
			set_Value (COLUMNNAME_AD_Browse_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Browse_ID, Integer.valueOf(AD_Browse_ID));
	}

	/** Get Smart Browse.
		@return Smart Browse	  */
	public int getAD_Browse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Browse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Window getAD_Window() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Window)MTable.get(getCtx(), org.compiere.model.I_AD_Window.Table_Name)
			.getPO(getAD_Window_ID(), get_TrxName());	}

	/** Set Window.
		@param AD_Window_ID 
		Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID)
	{
		if (AD_Window_ID < 1) 
			set_Value (COLUMNNAME_AD_Window_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
	}

	/** Get Window.
		@return Data entry or display window
	  */
	public int getAD_Window_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AccessLevel AD_Reference_ID=5 */
	public static final int ACCESSLEVEL_AD_Reference_ID=5;
	/** Organization = 1 */
	public static final String ACCESSLEVEL_Organization = "1";
	/** Client+Organization = 3 */
	public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
	/** System only = 4 */
	public static final String ACCESSLEVEL_SystemOnly = "4";
	/** All = 7 */
	public static final String ACCESSLEVEL_All = "7";
	/** System+Client = 6 */
	public static final String ACCESSLEVEL_SystemPlusClient = "6";
	/** Client only = 2 */
	public static final String ACCESSLEVEL_ClientOnly = "2";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{

		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
	}

	/** Set Column No.
		@param ColumnNo 
		Dashboard content column number
	  */
	public void setColumnNo (int ColumnNo)
	{
		set_Value (COLUMNNAME_ColumnNo, Integer.valueOf(ColumnNo));
	}

	/** Get Column No.
		@return Dashboard content column number
	  */
	public int getColumnNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ColumnNo);
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

	/** GoalDisplay AD_Reference_ID=53316 */
	public static final int GOALDISPLAY_AD_Reference_ID=53316;
	/** HTML Table = T */
	public static final String GOALDISPLAY_HTMLTable = "T";
	/** Chart = C */
	public static final String GOALDISPLAY_Chart = "C";
	/** Set Goal Display.
		@param GoalDisplay 
		Type of goal display on dashboard
	  */
	public void setGoalDisplay (String GoalDisplay)
	{

		set_Value (COLUMNNAME_GoalDisplay, GoalDisplay);
	}

	/** Get Goal Display.
		@return Type of goal display on dashboard
	  */
	public String getGoalDisplay () 
	{
		return (String)get_Value(COLUMNNAME_GoalDisplay);
	}

	/** Set HTML.
		@param HTML HTML	  */
	public void setHTML (String HTML)
	{
		set_Value (COLUMNNAME_HTML, HTML);
	}

	/** Get HTML.
		@return HTML	  */
	public String getHTML () 
	{
		return (String)get_Value(COLUMNNAME_HTML);
	}

	/** Set Collapsible.
		@param IsCollapsible 
		Flag to indicate the state of the dashboard panel
	  */
	public void setIsCollapsible (boolean IsCollapsible)
	{
		set_Value (COLUMNNAME_IsCollapsible, Boolean.valueOf(IsCollapsible));
	}

	/** Get Collapsible.
		@return Flag to indicate the state of the dashboard panel
	  */
	public boolean isCollapsible () 
	{
		Object oo = get_Value(COLUMNNAME_IsCollapsible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsEventRequired.
		@param IsEventRequired IsEventRequired	  */
	public void setIsEventRequired (boolean IsEventRequired)
	{
		set_Value (COLUMNNAME_IsEventRequired, Boolean.valueOf(IsEventRequired));
	}

	/** Get IsEventRequired.
		@return IsEventRequired	  */
	public boolean isEventRequired () 
	{
		Object oo = get_Value(COLUMNNAME_IsEventRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open By Default.
		@param IsOpenByDefault Open By Default	  */
	public void setIsOpenByDefault (boolean IsOpenByDefault)
	{
		set_Value (COLUMNNAME_IsOpenByDefault, Boolean.valueOf(IsOpenByDefault));
	}

	/** Get Open By Default.
		@return Open By Default	  */
	public boolean isOpenByDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsOpenByDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
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

	/** Set Dashboard Content.
		@param PA_DashboardContent_ID Dashboard Content	  */
	public void setPA_DashboardContent_ID (int PA_DashboardContent_ID)
	{
		if (PA_DashboardContent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_DashboardContent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_DashboardContent_ID, Integer.valueOf(PA_DashboardContent_ID));
	}

	/** Get Dashboard Content.
		@return Dashboard Content	  */
	public int getPA_DashboardContent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_DashboardContent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_PA_Goal getPA_Goal() throws RuntimeException
    {
		return (org.compiere.model.I_PA_Goal)MTable.get(getCtx(), org.compiere.model.I_PA_Goal.Table_Name)
			.getPO(getPA_Goal_ID(), get_TrxName());	}

	/** Set Goal.
		@param PA_Goal_ID 
		Performance Goal
	  */
	public void setPA_Goal_ID (int PA_Goal_ID)
	{
		if (PA_Goal_ID < 1) 
			set_Value (COLUMNNAME_PA_Goal_ID, null);
		else 
			set_Value (COLUMNNAME_PA_Goal_ID, Integer.valueOf(PA_Goal_ID));
	}

	/** Get Goal.
		@return Performance Goal
	  */
	public int getPA_Goal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_Goal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PageSize.
		@param PageSize PageSize	  */
	public void setPageSize (BigDecimal PageSize)
	{
		set_Value (COLUMNNAME_PageSize, PageSize);
	}

	/** Get PageSize.
		@return PageSize	  */
	public BigDecimal getPageSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PageSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.adempiere.model.I_AD_Browse_Field getZoom_Field() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_Browse_Field)MTable.get(getCtx(), org.adempiere.model.I_AD_Browse_Field.Table_Name)
			.getPO(getZoom_Field_ID(), get_TrxName());	}

	/** Set Zoom_Field_ID.
		@param Zoom_Field_ID Zoom_Field_ID	  */
	public void setZoom_Field_ID (int Zoom_Field_ID)
	{
		if (Zoom_Field_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Field_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Field_ID, Integer.valueOf(Zoom_Field_ID));
	}

	/** Get Zoom_Field_ID.
		@return Zoom_Field_ID	  */
	public int getZoom_Field_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Zoom_Field_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Tab getZoom_Tab() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Tab)MTable.get(getCtx(), org.compiere.model.I_AD_Tab.Table_Name)
			.getPO(getZoom_Tab_ID(), get_TrxName());	}

	/** Set Zoom_Tab_ID.
		@param Zoom_Tab_ID Zoom_Tab_ID	  */
	public void setZoom_Tab_ID (int Zoom_Tab_ID)
	{
		if (Zoom_Tab_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Tab_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Tab_ID, Integer.valueOf(Zoom_Tab_ID));
	}

	/** Get Zoom_Tab_ID.
		@return Zoom_Tab_ID	  */
	public int getZoom_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Zoom_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Window getZoom_Window() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Window)MTable.get(getCtx(), org.compiere.model.I_AD_Window.Table_Name)
			.getPO(getZoom_Window_ID(), get_TrxName());	}

	/** Set Zoom_Window_ID.
		@param Zoom_Window_ID Zoom_Window_ID	  */
	public void setZoom_Window_ID (int Zoom_Window_ID)
	{
		if (Zoom_Window_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Window_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Window_ID, Integer.valueOf(Zoom_Window_ID));
	}

	/** Get Zoom_Window_ID.
		@return Zoom_Window_ID	  */
	public int getZoom_Window_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Zoom_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ZUL File Path.
		@param ZulFilePath 
		Absolute path to zul file
	  */
	public void setZulFilePath (String ZulFilePath)
	{
		set_Value (COLUMNNAME_ZulFilePath, ZulFilePath);
	}

	/** Get ZUL File Path.
		@return Absolute path to zul file
	  */
	public String getZulFilePath () 
	{
		return (String)get_Value(COLUMNNAME_ZulFilePath);
	}

	/** onevent AD_Reference_ID=53574 */
	public static final int ONEVENT_AD_Reference_ID=53574;
	/** onClick = onClick */
	public static final String ONEVENT_OnClick = "onClick";
	/** onDoubleClick = onDoubleClick */
	public static final String ONEVENT_OnDoubleClick = "onDoubleClick";
	/** Set onevent.
		@param onevent onevent	  */
	public void setonevent (String onevent)
	{

		set_Value (COLUMNNAME_onevent, onevent);
	}

	/** Get onevent.
		@return onevent	  */
	public String getonevent () 
	{
		return (String)get_Value(COLUMNNAME_onevent);
	}
}
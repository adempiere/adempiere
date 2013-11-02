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

/** Generated Model for PA_DashboardContent
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public class X_PA_DashboardContent extends org.compiere.model.PO implements I_PA_DashboardContent, org.compiere.model.I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = -1942880105L;

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


	/** Load Meta Data */
	@Override
	protected org.compiere.model.POInfo initPO (Properties ctx)
	{
		org.compiere.model.POInfo poi = org.compiere.model.POInfo.getPOInfo (ctx, Table_Name, get_TrxName());
		return poi;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("X_PA_DashboardContent[")
		.append(get_ID()).append("]");
		return sb.toString();
	}

	/** Set Smart Browse.
		@param AD_Browse_ID Smart Browse	  */
	@Override
	public void setAD_Browse_ID (int AD_Browse_ID)
	{
		if (AD_Browse_ID < 1) 
			set_Value (COLUMNNAME_AD_Browse_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Browse_ID, Integer.valueOf(AD_Browse_ID));
	}

	/** Get Smart Browse.
		@return Smart Browse	  */
	@Override
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
		.getPO(getAD_Window_ID(), get_TrxName());	
	}

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



	/** Set Column No.
		@param ColumnNo 
		Dashboard content column number
	 */
	@Override
	public void setColumnNo (int ColumnNo)
	{
		set_Value (COLUMNNAME_ColumnNo, Integer.valueOf(ColumnNo));
	}

	/** Get Column No.
		@return Dashboard content column number
	 */
	@Override
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
	@Override
	public void setDescription (java.lang.String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	 */
	@Override
	public java.lang.String getDescription () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_Description);
	}

	/** 
	 * GoalDisplay AD_Reference_ID=53316
	 * Reference name: PA_DashboardContent GoalDisplay
	 */
	public static final int GOALDISPLAY_AD_Reference_ID=53316;
	/** HTML Table = T */
	public static final String GOALDISPLAY_HTMLTable = "T";
	/** Chart = C */
	public static final String GOALDISPLAY_Chart = "C";
	/** Set Goal Display.
		@param GoalDisplay 
		Type of goal display on dashboard
	 */
	@Override
	public void setGoalDisplay (java.lang.String GoalDisplay)
	{

		set_Value (COLUMNNAME_GoalDisplay, GoalDisplay);
	}

	/** Get Goal Display.
		@return Type of goal display on dashboard
	 */
	@Override
	public java.lang.String getGoalDisplay () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_GoalDisplay);
	}

	/** Set HTML.
		@param HTML HTML	  */
	@Override
	public void setHTML (java.lang.String HTML)
	{
		set_Value (COLUMNNAME_HTML, HTML);
	}

	/** Get HTML.
		@return HTML	  */
	@Override
	public java.lang.String getHTML () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_HTML);
	}

	/** Set Collapsible.
		@param IsCollapsible 
		Flag to indicate the state of the dashboard panel
	 */
	@Override
	public void setIsCollapsible (boolean IsCollapsible)
	{
		set_Value (COLUMNNAME_IsCollapsible, Boolean.valueOf(IsCollapsible));
	}

	/** Get Collapsible.
		@return Flag to indicate the state of the dashboard panel
	 */
	@Override
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
	@Override
	public void setIsEventRequired (boolean IsEventRequired)
	{
		set_Value (COLUMNNAME_IsEventRequired, Boolean.valueOf(IsEventRequired));
	}

	/** Get IsEventRequired.
		@return IsEventRequired	  */
	@Override
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
	@Override
	public void setIsOpenByDefault (boolean IsOpenByDefault)
	{
		set_Value (COLUMNNAME_IsOpenByDefault, Boolean.valueOf(IsOpenByDefault));
	}

	/** Get Open By Default.
		@return Open By Default	  */
	@Override
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
	@Override
	public void setLine (java.math.BigDecimal Line)
	{
		set_Value (COLUMNNAME_Line, Line);
	}

	/** Get Line No.
		@return Unique line for this document
	 */
	@Override
	public java.math.BigDecimal getLine () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Line);
		if (bd == null)
			return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	 */
	@Override
	public void setName (java.lang.String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	 */
	@Override
	public java.lang.String getName () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_Name);
	}

	/** 
	 * onevent AD_Reference_ID=53574
	 * Reference name: Event List
	 */
	public static final int ONEVENT_AD_Reference_ID=53574;
	/** onClick = onClick */
	public static final String ONEVENT_OnClick = "onClick";
	/** onDoubleClick = onDoubleClick */
	public static final String ONEVENT_OnDoubleClick = "onDoubleClick";
	/** Set On Event.
		@param onevent On Event	  */
	@Override
	public void setonevent (java.lang.String onevent)
	{

		set_Value (COLUMNNAME_onevent, onevent);
	}

	/** Get On Event.
		@return On Event	  */
	@Override
	public java.lang.String getonevent () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_onevent);
	}

	/** Set Dashboard Content.
		@param PA_DashboardContent_ID Dashboard Content	  */
	@Override
	public void setPA_DashboardContent_ID (int PA_DashboardContent_ID)
	{
		if (PA_DashboardContent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_DashboardContent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_DashboardContent_ID, Integer.valueOf(PA_DashboardContent_ID));
	}

	/** Get Dashboard Content.
		@return Dashboard Content	  */
	@Override
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
		.getPO(getPA_Goal_ID(), get_TrxName());
	}


	/** Set Goal.
		@param PA_Goal_ID 
		Performance Goal
	 */
	@Override
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
	@Override
	public int getPA_Goal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_Goal_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/** Set Page Size.
		@param PageSize Page Size	  */
	@Override
	public void setPageSize (java.math.BigDecimal PageSize)
	{
		set_Value (COLUMNNAME_PageSize, PageSize);
	}

	/** Get Page Size.
		@return Page Size	  */
	@Override
	public java.math.BigDecimal getPageSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PageSize);
		if (bd == null)
			return Env.ZERO;
		return bd;
	}

	/** Set Zoom_Field_ID.
		@param Zoom_Field_ID Zoom_Field_ID	  */
	@Override
	public void setZoom_Field_ID (int Zoom_Field_ID)
	{
		if (Zoom_Field_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Field_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Field_ID, Integer.valueOf(Zoom_Field_ID));
	}

	/** Get Zoom_Field_ID.
		@return Zoom_Field_ID	  */
	@Override
	public int getZoom_Field_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Zoom_Field_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/** Set Zoom_Tab_ID.
		@param Zoom_Tab_ID Zoom_Tab_ID	  */
	@Override
	public void setZoom_Tab_ID (int Zoom_Tab_ID)
	{
		if (Zoom_Tab_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Tab_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Tab_ID, Integer.valueOf(Zoom_Tab_ID));
	}

	/** Get Zoom_Tab_ID.
		@return Zoom_Tab_ID	  */
	@Override
	public int getZoom_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Zoom_Tab_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/** Set Zoom_Window_ID.
		@param Zoom_Window_ID Zoom_Window_ID	  */
	@Override
	public void setZoom_Window_ID (int Zoom_Window_ID)
	{
		if (Zoom_Window_ID < 1) 
			set_Value (COLUMNNAME_Zoom_Window_ID, null);
		else 
			set_Value (COLUMNNAME_Zoom_Window_ID, Integer.valueOf(Zoom_Window_ID));
	}

	/** Get Zoom_Window_ID.
		@return Zoom_Window_ID	  */
	@Override
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
	@Override
	public void setZulFilePath (java.lang.String ZulFilePath)
	{
		set_Value (COLUMNNAME_ZulFilePath, ZulFilePath);
	}

	/** Get ZUL File Path.
		@return Absolute path to zul file
	 */
	@Override
	public java.lang.String getZulFilePath () 
	{
		return (java.lang.String)get_Value(COLUMNNAME_ZulFilePath);
	}

	@Override
	protected int get_AccessLevel() {
		// TODO Auto-generated method stub
		return accessLevel.intValue();
	}
}
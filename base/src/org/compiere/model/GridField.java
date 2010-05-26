/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * Contributor(s): Victor Perez e-Evolution victor.perez@e-evolution.com      *
 *****************************************************************************/
package org.compiere.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;

/**
 *  Grid Field Model.
 *  <p>
 *  Fields are a combination of AD_Field (the display attributes)
 *  and AD_Column (the storage attributes).
 *  <p>
 *  The Field maintains the current edited value. If the value is changed,
 *  it fire PropertyChange "FieldValue".
 *  If the background is changed the PropertyChange "FieldAttribute" is fired.
 *  <br>
 *  Usually editors listen to their fields.
 *
 *  @author Jorg Janke
 *  @author Victor Perez , e-Evolution.SC FR [ 1757088 ], [1877902] Implement JSR 223 Scripting APIs to Callout
 *  		http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1877902&group_id=176962 to FR [1877902]
 *  @author Carlos Ruiz, qss FR [1877902]
 *  @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process Parameter: add display and readonly logic
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  		<li>BF [ 2874646 ] GridField issue when a lookup is key
 *  			https://sourceforge.net/tracker/?func=detail&aid=2874646&group_id=176962&atid=879332
 *  @author victor.perez@e-evolution.com,www.e-evolution.com
 *  		<li>BF [ 2910358 ] Error in context when a field is found in different tabs.
 *  			https://sourceforge.net/tracker/?func=detail&aid=2910358&group_id=176962&atid=879332
 *     		<li>BF [ 2910368 ] Error in context when IsActive field is found in different
 *  			https://sourceforge.net/tracker/?func=detail&aid=2910368&group_id=176962&atid=879332
 *  		<li>BF [ 3007342 ] Included tab context conflict issue
 *  			https://sourceforge.net/tracker/?func=detail&aid=3007342&group_id=176962&atid=879332
 *  @version $Id: GridField.java,v 1.5 2006/07/30 00:51:02 jjanke Exp $
 */
public class GridField 
	implements Serializable, Evaluatee
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1124123543602986028L;

	/**
	 *  Field Constructor.
	 *  requires initField for complete instantiation
	 *  @param vo ValueObjecy
	 */
	public GridField (GridFieldVO vo)
	{
		m_vo = vo;
		//  Set Attributes
		loadLookup();
		setError(false);
	}   //  MField

	/** Value Object                */
	private GridFieldVO        m_vo;
	/** The Mnemonic				*/
	private char			m_mnemonic = 0;
	
	private GridTab m_gridTab;
	
	/**
	 *  Dispose
	 */
	protected void dispose()
	{
	//	log.fine( "MField.dispose = " + m_vo.ColumnName);
		m_propertyChangeListeners = null;
		if (m_lookup != null)
			m_lookup.dispose();
		m_lookup = null;
		m_vo.lookupInfo = null;
		m_vo = null;
	}   //  dispose


	/** Lookup for this field       */
	private Lookup			m_lookup = null;
	/** New Row / inserting         */
	private boolean			m_inserting = false;


	/** Max Display Length = 60		*/
	public static final int MAXDISPLAY_LENGTH = 60;

	/** The current value                   */
	private Object          m_value = null;
	/** The old to force Property Change    */
	private static Object   s_oldValue = new Object();
	/** The old/previous value              */
	private Object          m_oldValue = s_oldValue;
	/** Only fire Property Change if old value really changed   */
	private boolean         m_valueNoFire = true;
	/** Error Status                        */
	private boolean         m_error = false;
	/** Parent Check						*/
	private Boolean			m_parentValue = null;

	/** Property Change         			*/
	private PropertyChangeSupport m_propertyChangeListeners = new PropertyChangeSupport(this);
	/** PropertyChange Name 				*/
	public static final String  PROPERTY = "FieldValue";
	/** Indicator for new Value				*/
	public static final String  INSERTING = "FieldValueInserting";

	/** Error Value for HTML interface          */
	private String			m_errorValue = null;
	/** Error Value indicator for HTML interface    */
	private boolean			m_errorValueFlag = false;

	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(GridField.class);
	
	
	/**************************************************************************
	 *  Set Lookup for columns with lookup
	 */
	public void loadLookup()
	{
		if (!isLookup())
			return;
		log.config("(" + m_vo.ColumnName + ")");

		if (DisplayType.isLookup(m_vo.displayType) && m_vo.IsDisplayed)
		{
			if (m_vo.lookupInfo == null)
			{
				log.log(Level.SEVERE, "(" + m_vo.ColumnName + ") - No LookupInfo");
				return;
			}
			//	Prevent loading of CreatedBy/UpdatedBy
			if (m_vo.displayType == DisplayType.Table
				&& (m_vo.ColumnName.equals("CreatedBy") || m_vo.ColumnName.equals("UpdatedBy")) )
			{
				m_vo.lookupInfo.IsCreadedUpdatedBy = true;
				m_vo.lookupInfo.DisplayType = DisplayType.Search;
			}
			//
			m_vo.lookupInfo.IsKey = isKey();
			MLookup ml = new MLookup (m_vo.lookupInfo, m_vo.TabNo);
			m_lookup = ml;
		}
		else if (m_vo.displayType == DisplayType.Location)   //  not cached
		{
			MLocationLookup ml = new MLocationLookup (m_vo.ctx, m_vo.WindowNo);
			m_lookup = ml;
		}
		else if (m_vo.displayType == DisplayType.Locator)
		{
			MLocatorLookup ml = new MLocatorLookup (m_vo.ctx, m_vo.WindowNo);
			m_lookup = ml;
		}
		else if (m_vo.displayType == DisplayType.Account)    //  not cached
		{
			MAccountLookup ma = new MAccountLookup (m_vo.ctx, m_vo.WindowNo);
			m_lookup = ma;
		}
		else if (m_vo.displayType == DisplayType.PAttribute)    //  not cached
		{
			MPAttributeLookup pa = new MPAttributeLookup (m_vo.ctx, m_vo.WindowNo);
			m_lookup = pa;
		}
	}   //  m_lookup

	/**
	 *  Wait until Load is complete
	 */
	public void lookupLoadComplete()
	{
		if (m_lookup == null)
			return;
		m_lookup.loadComplete();
	}   //  loadCompete

	/**
	 *  Get Lookup, may return null
	 *  @return lookup
	 */
	public Lookup getLookup()
	{
		return m_lookup;
	}   //  getLookup

	/**
	 *  Is this field a Lookup?.
	 *  @return true if lookup field
	 */
	public boolean isLookup()
	{
		boolean retValue = false;
		if (DisplayType.isLookup(m_vo.displayType))
			retValue = true;
		else if (m_vo.IsKey)
			retValue = false;
	//	else if (m_vo.ColumnName.equals("CreatedBy") || m_vo.ColumnName.equals("UpdatedBy"))
	//		retValue = false;
		else if (m_vo.displayType == DisplayType.Location
			|| m_vo.displayType == DisplayType.Locator
			|| m_vo.displayType == DisplayType.Account
			|| m_vo.displayType == DisplayType.PAttribute)
			retValue = true;

		return retValue;
	}   //  isLookup

	/**
	 *  Refresh Lookup if the lookup is unstable
	 *  @return true if lookup is validated
	 */
	public boolean refreshLookup()
	{
		//  if there is a validation string, the lookup is unstable
		if (m_lookup == null || m_lookup.getValidation().length() == 0)
			return true;
		//
		log.fine("(" + m_vo.ColumnName + ")");
		m_lookup.refresh();
		return m_lookup.isValidated();
	}   //  refreshLookup

	/**
	 *  Get a list of variables, this field is dependent on.
	 *  - for display purposes or
	 *  - for lookup purposes
	 *  @return ArrayList
	 */
	public ArrayList<String> getDependentOn()
	{
		ArrayList<String> list = new ArrayList<String>();
		//  Display
		Evaluator.parseDepends(list, m_vo.DisplayLogic);
		Evaluator.parseDepends(list, m_vo.ReadOnlyLogic);
		Evaluator.parseDepends(list, m_vo.MandatoryLogic);
		//  Lookup
		if (m_lookup != null)
			Evaluator.parseDepends(list, m_lookup.getValidation());
		//
		if (list.size() > 0 && CLogMgt.isLevelFiner())
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++)
				sb.append(list.get(i)).append(" ");
			log.finer("(" + m_vo.ColumnName + ") " + sb.toString());
		}
		return list;
	}   //  getDependentOn

	
	/**************************************************************************
	 *	Set Error.
	 *  Used by editors to set the color
	 *  @param error true if error
	 */
	public void setError (boolean error)
	{
		m_error = error;
	}	//	setBackground

	/**
	 *	Get Background Error.
	 *  @return error
	 */
	public boolean isError()
	{
		return m_error;
	}	//	isError


	/**
	 *	Is it Mandatory to enter for user?
	 *  Mandatory checking is dome in MTable.getMandatory
	 *  @param checkContext - check environment (requires correct row position)
	 *  @return true if mandatory
	 */
	public boolean isMandatory (boolean checkContext)
	{
//	  Do we have a mandatory rule
		if (checkContext && m_vo.MandatoryLogic.length() > 0)
		{
			boolean retValue = Evaluator.evaluateLogic(this, m_vo.MandatoryLogic);
			log.finest(m_vo.ColumnName + " Mandatory(" + m_vo.MandatoryLogic + ") => Mandatory-" + retValue);
			if (retValue)
				return true;
		}
		
		//  Not mandatory
		if (!m_vo.IsMandatory || isVirtualColumn())
			return false;

		//  Numeric Keys and Created/Updated as well as 
		//	DocumentNo/Value/ASI ars not mandatory (persistency layer manages them)
		if (m_gridTab != null &&  // if gridtab doesn't exist then it's not a window field (probably a process parameter field)
			  (   (m_vo.IsKey && m_vo.ColumnName.endsWith("_ID"))
				|| m_vo.ColumnName.startsWith("Created") || m_vo.ColumnName.startsWith("Updated")
				|| m_vo.ColumnName.equals("Value") 
				|| m_vo.ColumnName.equals("DocumentNo")
				|| m_vo.ColumnName.equals("M_AttributeSetInstance_ID") 	//	0 is valid
			  )
			)
			return false;

		//  Mandatory if displayed
		return isDisplayed (checkContext);
	}	//	isMandatory

	/**
	 *	Is parameter Editable - checks if parameter is Read Only
	 *  @param checkContext if true checks Context
	 *  @return true, if editable
	 */
	public boolean isEditablePara(boolean checkContext) {
		if (checkContext && m_vo.ReadOnlyLogic.length() > 0)
		{
			boolean retValue = !Evaluator.evaluateLogic(this, m_vo.ReadOnlyLogic);
			log.finest(m_vo.ColumnName + " R/O(" + m_vo.ReadOnlyLogic + ") => R/W-" + retValue);
			if (!retValue)
				return false;
		}

		//  ultimately visibility decides
		return isDisplayed (checkContext);
	}
	
	
	/**
	 *	Is it Editable - checks IsActive, IsUpdateable, and isDisplayed
	 *  @param checkContext if true checks Context for Active, IsProcessed, LinkColumn
	 *  @return true, if editable
	 */
	public boolean isEditable (boolean checkContext)
	{
		if (isVirtualColumn())
			return false;
		//  Fields always enabled (are usually not updateable)
		if (m_vo.ColumnName.equals("Posted")
			|| (m_vo.ColumnName.equals("Record_ID") && m_vo.displayType == DisplayType.Button))	//  Zoom
			return true;

		//  Fields always updareable
		if (m_vo.IsAlwaysUpdateable)      //  Zoom
			return true;
		
		//  Tab or field is R/O
		if (m_vo.tabReadOnly || m_vo.IsReadOnly)
		{
			log.finest(m_vo.ColumnName + " NO - TabRO=" + m_vo.tabReadOnly + ", FieldRO=" + m_vo.IsReadOnly);
			return false;
		}

		//	Not Updateable - only editable if new updateable row
		if (!m_vo.IsUpdateable && !m_inserting)
		{
			log.finest(m_vo.ColumnName + " NO - FieldUpdateable=" + m_vo.IsUpdateable);
			return false;
		}

		//	Field is the Link Column of the tab
		if (m_vo.ColumnName.equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, GridTab.CTX_LinkColumnName)))
		{
			log.finest(m_vo.ColumnName + " NO - LinkColumn");
			return false;
		}

		//	Role Access & Column Access			
		if (checkContext)
		{
			int AD_Client_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, "AD_Client_ID");
			int AD_Org_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, "AD_Org_ID");
			String keyColumn = Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, GridTab.CTX_KeyColumnName);
			if ("EntityType".equals(keyColumn))
				keyColumn = "AD_EntityType_ID";
			if (!keyColumn.endsWith("_ID"))
				keyColumn += "_ID";			//	AD_Language_ID
			int Record_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, keyColumn);
			int AD_Table_ID = m_vo.AD_Table_ID;
			if (!MRole.getDefault(m_vo.ctx, false).canUpdate(
				AD_Client_ID, AD_Org_ID, AD_Table_ID, Record_ID, false))
				return false;
			if (!MRole.getDefault(m_vo.ctx, false).isColumnAccess(AD_Table_ID, m_vo.AD_Column_ID, false))
				return false;
		}
			
		//  Do we have a readonly rule
		if (checkContext && m_vo.ReadOnlyLogic.length() > 0)
		{
			boolean retValue = !Evaluator.evaluateLogic(this, m_vo.ReadOnlyLogic);
			log.finest(m_vo.ColumnName + " R/O(" + m_vo.ReadOnlyLogic + ") => R/W-" + retValue);
			if (!retValue)
				return false;
		}
		
		//BF [ 2910368 ]
		//  Always editable if Active
		if (checkContext && "Y".equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, "IsActive"))
				&& (   m_vo.ColumnName.equals("Processing")
					|| m_vo.ColumnName.equals("PaymentRule")
					|| m_vo.ColumnName.equals("DocAction") 
					|| m_vo.ColumnName.equals("GenerateTo")))
			return true;

		//  Record is Processed	***	
		if (checkContext 
			&& ("Y".equals(get_ValueAsString("Processed")) || "Y".equals(get_ValueAsString("Processing"))) )
			return false;

		//  IsActive field is editable, if record not processed
		if (m_vo.ColumnName.equals("IsActive"))
			return true;
		// BF [ 2910368 ]
		// Record is not Active
		if (checkContext && !Env.getContext(m_vo.ctx, m_vo.WindowNo,m_vo.TabNo, "IsActive").equals("Y"))
			return false;

		//  ultimately visibility decides
		return isDisplayed (checkContext);
	}	//	isEditable

	/**
	 *  Set Inserting (allows to enter not updateable fields).
	 *  Reset when setting the Field Value
	 *  @param inserting true if inserting
	 */
	public void setInserting (boolean inserting)
	{
		m_inserting = inserting;
	}   //  setInserting

	
	/**************************************************************************
	 *	Create default value.
	 *  <pre>
	 *		(a) Key/Parent/IsActive/SystemAccess
	 *      (b) SQL Default
	 *		(c) Column Default		//	system integrity
	 *      (d) User Preference
	 *		(e) System Preference
	 *		(f) DataType Defaults
	 *
	 *  Don't default from Context => use explicit defaultValue
	 *  (would otherwise copy previous record)
	 *  </pre>
	 *  @return default value or null
	 */
	public Object getDefault()
	{
		/**
		 *  (a) Key/Parent/IsActive/SystemAccess
		 */

		//	No defaults for these fields
		if (m_vo.IsKey || m_vo.displayType == DisplayType.RowID 
			|| DisplayType.isLOB(m_vo.displayType))
			return null;
		//	Set Parent to context if not explitly set
		if (isParentValue()
			&& (m_vo.DefaultValue == null || m_vo.DefaultValue.length() == 0))
		{
			String parent = Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName);
			log.fine("[Parent] " + m_vo.ColumnName + "=" + parent);
			return createDefault(parent);
		}
		//	Always Active
		if (m_vo.ColumnName.equals("IsActive"))
		{
			log.fine("[IsActive] " + m_vo.ColumnName + "=Y");
			return "Y";
		}
		
		//	Set Client & Org to System, if System access
		if (X_AD_Table.ACCESSLEVEL_SystemOnly.equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, GridTab.CTX_AccessLevel))
			&& (m_vo.ColumnName.equals("AD_Client_ID") || m_vo.ColumnName.equals("AD_Org_ID")))
		{
			log.fine("[SystemAccess] " + m_vo.ColumnName + "=0");
			return new Integer(0);
		}
		//	Set Org to System, if Client access
		else if (X_AD_Table.ACCESSLEVEL_SystemPlusClient.equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, GridTab.CTX_AccessLevel))
			&& m_vo.ColumnName.equals("AD_Org_ID"))
		{
			log.fine("[ClientAccess] " + m_vo.ColumnName + "=0");
			return new Integer(0);
		}

		/**
		 *  (b) SQL Statement (for data integity & consistency)
		 */
		String	defStr = "";
		if (m_vo.DefaultValue.startsWith("@SQL="))
		{
			String sql = m_vo.DefaultValue.substring(5);			//	w/o tag
			//sql = Env.parseContext(m_vo.ctx, m_vo.WindowNo, sql, false, true);	//	replace variables
			//hengsin, capture unparseable error to avoid subsequent sql exception
			sql = Env.parseContext(m_vo.ctx, m_vo.WindowNo, sql, false, false);	//	replace variables
			if (sql.equals(""))
			{
				log.log(Level.WARNING, "(" + m_vo.ColumnName + ") - Default SQL variable parse failed: "
					+ m_vo.DefaultValue);
			}
			else
			{
				try
				{
					PreparedStatement stmt = DB.prepareStatement(sql, null);
					ResultSet rs = stmt.executeQuery();
					if (rs.next())
						defStr = rs.getString(1);
					else
						log.log(Level.WARNING, "(" + m_vo.ColumnName + ") - no Result: " + sql);
					rs.close();
					stmt.close();
				}
				catch (SQLException e)
				{
					log.log(Level.WARNING, "(" + m_vo.ColumnName + ") " + sql, e);
				}
			}
			if (defStr != null && defStr.length() > 0)
			{
				log.fine("[SQL] " + m_vo.ColumnName + "=" + defStr);
				return createDefault(defStr);
			}
		}	//	SQL Statement

		/**
		 * 	(c) Field DefaultValue		=== similar code in AStartRPDialog.getDefault ===
		 */
		if (!m_vo.DefaultValue.equals("") && !m_vo.DefaultValue.startsWith("@SQL="))
		{
			defStr = "";		//	problem is with texts like 'sss;sss'
			//	It is one or more variables/constants
			StringTokenizer st = new StringTokenizer(m_vo.DefaultValue, ",;", false);
			while (st.hasMoreTokens())
			{
				defStr = st.nextToken().trim();
				if (defStr.equals("@SysDate@"))				//	System Time
					return new Timestamp (System.currentTimeMillis());
				else if (defStr.indexOf('@') != -1)			//	it is a variable
					defStr = Env.getContext(m_vo.ctx, m_vo.WindowNo, defStr.replace('@',' ').trim());
				else if (defStr.indexOf("'") != -1)			//	it is a 'String'
					defStr = defStr.replace('\'', ' ').trim();

				if (!defStr.equals(""))
				{
					log.fine("[DefaultValue] " + m_vo.ColumnName + "=" + defStr);
					return createDefault(defStr);
				 }
			}	//	while more Tokens
		}	//	Default value

		/**
		 *	(d) Preference (user) - P|
		 */
		defStr = Env.getPreference (m_vo.ctx, m_vo.AD_Window_ID, m_vo.ColumnName, false);
		if (!defStr.equals(""))
		{
			log.fine("[UserPreference] " + m_vo.ColumnName + "=" + defStr);
			return createDefault(defStr);
		}

		/**
		 *	(e) Preference (System) - # $
		 */
		defStr = Env.getPreference (m_vo.ctx, m_vo.AD_Window_ID, m_vo.ColumnName, true);
		if (!defStr.equals(""))
		{
			log.fine("[SystemPreference] " + m_vo.ColumnName + "=" + defStr);
			return createDefault(defStr);
		}

		/**
		 *	(f) DataType defaults
		 */

		//	Button to N
		if (m_vo.displayType == DisplayType.Button && !m_vo.ColumnName.endsWith("_ID"))
		{
			log.fine("[Button=N] " + m_vo.ColumnName);
			return "N";
		}
		//	CheckBoxes default to No
		if (m_vo.displayType == DisplayType.YesNo)
		{
			log.fine("[YesNo=N] " + m_vo.ColumnName);
			return "N";
		}
		//  lookups with one value
	//	if (DisplayType.isLookup(m_vo.displayType) && m_lookup.getSize() == 1)
	//	{
	//		/** @todo default if only one lookup value */
	//	}
		//  IDs remain null
		if (m_vo.ColumnName.endsWith("_ID"))
		{
			log.fine("[ID=null] "  + m_vo.ColumnName);
			return null;
		}
		//  actual Numbers default to zero
		if (DisplayType.isNumeric(m_vo.displayType))
		{
			log.fine("[Number=0] " + m_vo.ColumnName);
			return createDefault("0");
		}

		/**
		 *  No resolution
		 */
		log.fine("[NONE] " + m_vo.ColumnName);
		return null;
	}	//	getDefault

	/**
	 *	Create Default Object type.
	 *  <pre>
	 *		Integer 	(IDs, Integer)
	 *		BigDecimal 	(Numbers)
	 *		Timestamp	(Dates)
	 *		Boolean		(YesNo)
	 *		default: String
	 *  </pre>
	 *  @param value string
	 *  @return type dependent converted object
	 */
	private Object createDefault (String value)
	{
		//	true NULL
		if (value == null || value.toString().length() == 0)
			return null;
		//	see also MTable.readData
		try
		{
			//	IDs & Integer & CreatedBy/UpdatedBy
			if (m_vo.ColumnName.endsWith("atedBy")
					|| (m_vo.ColumnName.endsWith("_ID") && DisplayType.isID(m_vo.displayType))) // teo_sarca [ 1672725 ] Process parameter that ends with _ID but is boolean
			{
				try	//	defaults -1 => null
				{
					int ii = Integer.parseInt(value);
					if (ii < 0)
						return null;
					return new Integer(ii);
				}
				catch (Exception e)
				{
					log.warning("Cannot parse: " + value + " - " + e.getMessage());
				}
				return new Integer(0);
			}
			//	Integer
			if (m_vo.displayType == DisplayType.Integer)
				return new Integer(value);
			
			//	Number
			if (DisplayType.isNumeric(m_vo.displayType))
				return new BigDecimal(value);
			
			//	Timestamps
			if (DisplayType.isDate(m_vo.displayType))
			{
				// try timestamp format - then date format -- [ 1950305 ]
				java.util.Date date = null;
				try {
					date = DisplayType.getTimestampFormat_Default().parse (value);
				} catch (java.text.ParseException e) {
					date = DisplayType.getDateFormat_JDBC().parse (value);
				}
				return new Timestamp (date.getTime());
			}
			
			//	Boolean
			if (m_vo.displayType == DisplayType.YesNo)
				return Boolean.valueOf ("Y".equals(value));
			
			//	Default
			return value;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_vo.ColumnName + " - " + e.getMessage());
		}
		return null;
	}	//	createDefault

	/**
	 *  Validate initial Field Value.
	 * 	Called from MTab.dataNew and MTab.setCurrentRow when inserting
	 *  @return true if valid
	 */
	public boolean validateValue()
	{
		//  null
		if (m_value == null || m_value.toString().length() == 0)
		{
			if (isMandatory(true))
			{
				m_error = true;
				return false;
			}
			else
				return true;
		}
		
		//	Search not cached
		if (getDisplayType() == DisplayType.Search && m_lookup != null)
		{
			// need to re-set invalid values - OK BPartner in PO Line - not OK SalesRep in Invoice
			if (m_lookup.getDirect(m_value, false, true) == null)
			{
				log.finest(m_vo.ColumnName + " Serach not valid - set to null");
				setValue(null, m_inserting);
				m_error = true;
				return false;
			}
			return true;
		} 

		//  cannot be validated
		if (!isLookup()
			|| m_lookup == null
			|| m_lookup.containsKey(m_value))
			return true;
		//	it's not null, a lookup and does not have the key
		if (isKey() || isParentValue())		//	parents/ket are not validated
			return true;	
			
		log.finest(m_vo.ColumnName + " - set to null");
		setValue(null, m_inserting);
		m_error = true;
		return false;
	}   //  validateValue


	/**************************************************************************
	 *	Is the Column Visible ?
	 *  @param checkContext - check environment (requires correct row position)
	 *  @return true, if visible
	 */
	public boolean isDisplayed (boolean checkContext)
	{
		//  ** static content **
		//  not displayed
		if (!m_vo.IsDisplayed)
			return false;
		//  no restrictions
		if (m_vo.DisplayLogic.equals(""))
			return true;

		//  ** dynamic content **
		if (checkContext)
		{
			boolean retValue = Evaluator.evaluateLogic(this, m_vo.DisplayLogic);
			log.finest(m_vo.ColumnName 
				+ " (" + m_vo.DisplayLogic + ") => " + retValue);
			return retValue;
		}
		return true;
	}	//	isDisplayed

	/**
	 * 	Get Variable Value (Evaluatee)
	 *	@param variableName name
	 *	@return value
	 */
	public String get_ValueAsString (String variableName)
	{
		return Env.getContext (m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, variableName, false, true);
	}	//	get_ValueAsString


	/**
	 *	Add Display Dependencies to given List.
	 *  Source: DisplayLogic
	 *  @param list list to be added to
	 */
	public void addDependencies (ArrayList<String> list)
	{
		//	nothing to parse
		if (!m_vo.IsDisplayed || m_vo.DisplayLogic.equals(""))
			return;

		StringTokenizer logic = new StringTokenizer(m_vo.DisplayLogic.trim(), "&|", false);

		while (logic.hasMoreTokens())
		{
			StringTokenizer st = new StringTokenizer(logic.nextToken().trim(), "!=^", false);
			while (st.hasMoreTokens())
			{
				String tag = st.nextToken().trim();					//	get '@tag@'
				//	Do we have a @variable@ ?
				if (tag.indexOf('@') != -1)
				{
					tag = tag.replace('@', ' ').trim();				//	strip 'tag'
					//	Add columns (they might not be a column, but then it is static)
					if (!list.contains(tag))
						list.add(tag);
				}
			}
		}
	}	//	addDependencies

	
	/**************************************************************************
	 *  Get Column Name
	 *  @return column name
	 */
	public String getColumnName()
	{
		return m_vo.ColumnName;
	}	//	getColumnName
	
	/**
	 *  Get Column Name or SQL .. with/without AS
	 *  @param withAS include AS ColumnName for virtual columns in select statements
	 *  @return column name
	 */
	public String getColumnSQL(boolean withAS)
	{
		if (m_vo.ColumnSQL != null && m_vo.ColumnSQL.length() > 0)
		{
			if (withAS)
				return m_vo.ColumnSQL + " AS " + m_vo.ColumnName;
			else
				return m_vo.ColumnSQL;
		}
		return m_vo.ColumnName;
	}	//	getColumnSQL

	/**
	 *  Is Virtual Column
	 *  @return column is virtual
	 */
	public boolean isVirtualColumn()
	{
		if (m_vo.ColumnSQL != null && m_vo.ColumnSQL.length() > 0)
			return true; 
		return false;
	}	//	isColumnVirtual
	
	/**
	 * 	Get Header
	 *	@return header
	 */
	public String getHeader()
	{
		return m_vo.Header;
	}
	/**
	 * 	Get Display Type
	 *	@return dt
	 */
	public int getDisplayType()
	{
		return m_vo.displayType;
	}
	/**
	 * 	Get AD_Reference_Value_ID
	 *	@return reference value
	 */
	public int getAD_Reference_Value_ID()
	{
		return m_vo.AD_Reference_Value_ID;
	}
	/**
	 * 	Get AD_Window_ID
	 *	@return window
	 */
	public int getAD_Window_ID()
	{
		return m_vo.AD_Window_ID;
	}
	/**
	 * 	Get Window No
	 *	@return window no
	 */
	public int getWindowNo()
	{
		return m_vo.WindowNo;
	}
	/**
	 * 	Get AD_Column_ID
	 *	@return column
	 */
	public int getAD_Column_ID()
	{
		return m_vo.AD_Column_ID;
	}
	/**
	 * 	Get Display Length
	 *	@return display
	 */
	public int getDisplayLength()
	{
		return m_vo.DisplayLength;
	}
	/**
	 * 	Is SameLine
	 *	@return trie if same line
	 */
	public boolean isSameLine()
	{
		return m_vo.IsSameLine;
	}
	/**
	 * 	Is Displayed
	 *	@return true if displayed
	 */
	public boolean isDisplayed()
	{
		return m_vo.IsDisplayed;
	}
	/**
	 * 	Get DisplayLogic
	 *	@return display logic
	 */
	public String getDisplayLogic()
	{
		return m_vo.DisplayLogic;
	}
	/**
	 * 	Get Default Value
	 *	@return default
	 */
	public String getDefaultValue()
	{
		return m_vo.DefaultValue;
	}
	/**
	 * 	Is ReadOnly
	 *	@return true if read only
	 */
	public boolean isReadOnly()
	{
		if (isVirtualColumn())
			return true;
		return m_vo.IsReadOnly;
	}
	/**
	 * 	Is Updateable
	 *	@return true if updateable
	 */
	public boolean isUpdateable()
	{
		if (isVirtualColumn())
			return false;
		return m_vo.IsUpdateable;
	}
	/**
	 * 	Is Autocomplete
	 *	@return true if autocomplete
	 */
	public boolean isAutocomplete() {
		return m_vo.IsAutocomplete;
	}
	/**
	 * 	Is Always Updateable
	 *	@return true if always updateable
	 */
	public boolean isAlwaysUpdateable()
	{
		if (isVirtualColumn() || !m_vo.IsUpdateable)
			return false;
		return m_vo.IsAlwaysUpdateable;
	}
	/**
	 * 	Is Heading
	 *	@return heading
	 */
	public boolean isHeading()
	{
		return m_vo.IsHeading;
	}
	/**
	 * 	Is Field Only
	 *	@return field only
	 */
	public boolean isFieldOnly()
	{
		return m_vo.IsFieldOnly;
	}
	/**
	 * 	Is Encrypted Field (display)
	 *	@return encrypted field
	 */
	public boolean isEncryptedField()
	{
		return m_vo.IsEncryptedField;
	}
	/**
	 * 	Is Encrypted Field (display) or obscured
	 *	@return encrypted field
	 */
	public boolean isEncrypted()
	{
		if (m_vo.IsEncryptedField)
			return true;
		String ob = getObscureType();
		if (ob != null && ob.length() > 0)
			return true;
		return m_vo.ColumnName.equals("Password");
	}
	/**
	 * 	Is Encrypted Column (data)
	 *	@return encrypted column
	 */
	public boolean isEncryptedColumn()
	{
		return m_vo.IsEncryptedColumn;
	}
	/**
	 * 	Is Selection Column
	 *	@return selection
	 */
	public boolean isSelectionColumn()
	{
		return m_vo.IsSelectionColumn;
	}
	/**
	 * 	Get Obscure Type
	 *	@return obscure
	 */
	public String getObscureType()
	{
		return m_vo.ObscureType;
	}
	/**
	 * 	Get Sort No
	 *	@return  sort
	 */
	public int getSortNo()
	{
		return m_vo.SortNo;
	}
	/**
	 * 	Get Field Length
	 *	@return field length
	 */
	public int getFieldLength()
	{
		return m_vo.FieldLength;
	}
	/**
	 * 	Get VFormat
	 *	@return format
	 */
	public String getVFormat()
	{
		return m_vo.VFormat;
	}
	/**
	 * 	Get Value Min
	 *	@return min
	 */
	public String getValueMin()
	{
		return m_vo.ValueMin;
	}
	/**
	 * 	Get Value Max
	 *	@return max
	 */
	public String getValueMax()
	{
		return m_vo.ValueMax;
	}
	/**
	 * 	Get Field Group
	 *	@return field group
	 */
	public String getFieldGroup()
	{
		return m_vo.FieldGroup;
	}
	/**
	 * 	Get Field Group Type
	 *	@return field group type
	 */
	public String getFieldGroupType()
	{
		return m_vo.FieldGroupType;
	}
	/**
	 * 	Key
	 *	@return key
	 */
	public boolean isKey()
	{
		return m_vo.IsKey;
	}
	/**
	 * 	Parent Column
	 *	@return parent column
	 */
	public boolean isParentColumn()
	{
			return m_vo.IsParent;
	}
	/**
	 * 	Parent Link Value
	 *	@return parent value
	 */
	public boolean isParentValue()
	{
		if (m_parentValue != null)
			return m_parentValue.booleanValue();
		if (!DisplayType.isID(m_vo.displayType) || m_vo.TabNo == 0)
			m_parentValue = Boolean.FALSE;
		else 
		{
			String LinkColumnName = Env.getContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, GridTab.CTX_LinkColumnName);
			if (LinkColumnName == null || LinkColumnName.length() == 0)
				m_parentValue = Boolean.FALSE; // teo_sarca, [ 1673886 ]
			else 
				m_parentValue = Boolean.valueOf(m_vo.ColumnName.equals(LinkColumnName));
			if (m_parentValue)
				log.config(m_parentValue
					+ " - Link(" + LinkColumnName + ", W=" + m_vo.WindowNo + ",T=" + m_vo.TabNo
					+ ") = " + m_vo.ColumnName);
			else
				m_parentValue = Boolean.valueOf(isIndirectParentValue());
		}
		return m_parentValue.booleanValue();
	}	//	isParentValue
	
	/**
	 * 	Get Callout
	 *	@return callout
	 */
	public String getCallout()
	{
		return m_vo.Callout;
	}
	
	/**
	 * 	Get AD_Process_ID
	 *	@return process
	 */
	public int getAD_Process_ID()
	{
		return m_vo.AD_Process_ID;
	}
	/**
	 * 	Get Description
	 *	@return description
	 */
	public String getDescription()
	{
		return m_vo.Description;
	}
	/**
	 * 	Get Help
	 *	@return help
	 */
	public String getHelp()
	{
		return m_vo.Help;
	}
	/**
	 * 	Get AD_Tab_ID
	 *	@return tab
	 */
	public int getAD_Tab_ID()
	{
		return m_vo.AD_Tab_ID;
	}
	/**
	 * 	Get VO
	 *	@return value object
	 */
	public GridFieldVO getVO()
	{
		return m_vo;
	}

	/**
	 *  Is this a long (string/text) field (over 60/2=30 characters)
	 *  @return true if long field
	 */
	public boolean isLongField()
	{
	//	if (m_vo.displayType == DisplayType.String 
	//		|| m_vo.displayType == DisplayType.Text 
	//		|| m_vo.displayType == DisplayType.Memo
	//		|| m_vo.displayType == DisplayType.TextLong
	//		|| m_vo.displayType == DisplayType.Image)
		return (m_vo.DisplayLength >= MAXDISPLAY_LENGTH/2);
	//	return false;
	}   //  isLongField

	/**
	 *  Set Value to null.
	 *  <p>
	 *  Do not update context - called from GridTab.setCurrentRow
	 *  Send Bean PropertyChange if there is a change
	 */
	public void setValue ()
	{
	//	log.fine(ColumnName + "=" + newValue);
		if (m_valueNoFire)      //  set the old value
			m_oldValue = m_value;
		m_value = null;
		m_inserting = false;
		m_error = false;        //  reset error

		//  Does not fire, if same value
		m_propertyChangeListeners.firePropertyChange(PROPERTY, m_oldValue, m_value);
	//	m_propertyChangeListeners.firePropertyChange(PROPERTY, s_oldValue, null);
	}   //  setValue

	/**
	 *  Set Value to null.
	 *  <p>
	 *  Do update context - called from GridTab.setCurrentRow
	 *  Send Bean PropertyChange if there is a change
	 */
	public void setValueAndUpdateContext ()
	{
	//	log.fine(ColumnName + "=" + newValue);
		if (m_valueNoFire)      //  set the old value
			m_oldValue = m_value;
		m_value = null;
		m_inserting = false;
		m_error = false;        //  reset error

		// [ 1881480 ] Navigation problem between tabs
		Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName, (String) m_value);

		//  Does not fire, if same value
		m_propertyChangeListeners.firePropertyChange(PROPERTY, m_oldValue, m_value);
	//	m_propertyChangeListeners.firePropertyChange(PROPERTY, s_oldValue, null);
	}   //  setValue

	/**
	 *  Set Value.
	 *  <p>
	 *  Update context, if not text or RowID;
	 *  Send Bean PropertyChange if there is a change
	 *  @param newValue new value
	 *  @param inserting true if inserting
	 */
	public void setValue (Object newValue, boolean inserting)
	{
	//	log.fine(ColumnName + "=" + newValue);
		if (m_valueNoFire)      //  set the old value
			m_oldValue = m_value;
		m_value = newValue;
		m_inserting = inserting;
		m_error = false;        //  reset error

		updateContext();

		//  Does not fire, if same value
		Object oldValue = m_oldValue;
		if (inserting)
			oldValue = INSERTING;
		m_propertyChangeListeners.firePropertyChange(PROPERTY, oldValue, m_value);
	}   //  setValue

	/**
	 * Update env. context with current value
	 */
	public void updateContext() {
		//	Set Context
		if (m_vo.displayType == DisplayType.Text 
			|| m_vo.displayType == DisplayType.Memo
			|| m_vo.displayType == DisplayType.TextLong
			|| m_vo.displayType == DisplayType.Binary
			|| m_vo.displayType == DisplayType.RowID
			|| isEncrypted())
			;	//	ignore
		else if (m_value instanceof Boolean)
		{
			backupValue(); // teo_sarca [ 1699826 ]
			if (!isParentTabField())
			{
				Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName, 
					((Boolean)m_value).booleanValue());
			}
			Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, m_vo.ColumnName, 
					m_value==null ? null : (((Boolean)m_value) ? "Y" : "N"));
		}
		else if (m_value instanceof Timestamp)
		{
			backupValue(); // teo_sarca [ 1699826 ]
			if (!isParentTabField())
			{
				Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName, (Timestamp)m_value);
			}
			Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, m_vo.ColumnName, 
					m_value==null ? null : m_value.toString().substring(0, m_value.toString().indexOf(".")));
		}
		else
		{
			backupValue(); // teo_sarca [ 1699826 ]
			if (!isParentTabField())
			{
				Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName, 
					m_value==null ? null : m_value.toString());
			}
			Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, m_vo.ColumnName, 
				m_value==null ? null : m_value.toString());
		}		
	}

	/**
	 * 	Set Value and Validate
	 *	@param newValue value
	 *	@param inserting insert
	 *	@return null or error message
	 */
	public String setValueValidate (String newValue, boolean inserting)
	{
		if (newValue == null)
			setValue();

		//	Data Type Test
		int dt = getDisplayType();
		try
		{
			//	Return Integer
			if (dt == DisplayType.Integer
				|| (DisplayType.isID(dt) && getColumnName().endsWith("_ID")))
			{
				int i = Integer.parseInt(newValue);
				setValue (new Integer(i), inserting);
			}
			//	Return BigDecimal
			else if (DisplayType.isNumeric(dt))
			{
				BigDecimal value = (BigDecimal)DisplayType.getNumberFormat(dt).parse(newValue);
				setValue (value, inserting);
				return null;
			}
			//	Return Timestamp
			else if (DisplayType.isDate(dt))
			{
				long time = DisplayType.getDateFormat_JDBC().parse(newValue).getTime();
				setValue (new Timestamp(time), inserting);
				return null;
			}
			//	Return Boolean
			else if (dt == DisplayType.YesNo)
			{
				Boolean value = null;
				if (newValue.equals("Y"))
					value = Boolean.TRUE;
				else if (newValue.equals("N"))
					value = Boolean.FALSE;
				else
					return getColumnName() + " = " + newValue + " - Must be Y/N";
				setValue (value, inserting);
				return null;
			}
			else if (DisplayType.isText(dt))
			{
				setValue (newValue, inserting);
				return null;
			}
			else
				return getColumnName() + " not mapped "
					+ DisplayType.getDescription(dt);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Value=" + newValue, ex);
			
			String error = ex.getLocalizedMessage();
			if (error == null || error.length() == 0)
				error = ex.toString();
			return getColumnName() + " = " + newValue + " - " + error;
		}
		
		//	ID - test ID
		if (!DisplayType.isID(dt))
			return null;
		
		//TODO: setValueValidate

		return null;
	}	//	setValueValidate

	/**
	 *  Get Value
	 *  @return current value
	 */
	public Object getValue()
	{
		return m_value;
	}   //  getValue

	/**
	 *  Set old/previous Value.
	 *  (i.e. don't fire Property change)
	 *  Used by VColor.setField
	 *  @param value if false property change will always be fires
	 */
	public void setValueNoFire (boolean value)
	{
		m_valueNoFire = value;
	}   //  setOldValue

	/**
	 *  Get old/previous Value.
	 * 	Called from MTab.processCallout
	 *  @return old value
	 */
	public Object getOldValue()
	{
		return m_oldValue;
	}   //  getOldValue

	/**
	 *  Set Error Value (the value, which cuased some Error)
	 *  @param errorValue error message
	 */
	public void setErrorValue (String errorValue)
	{
		m_errorValue = errorValue;
		m_errorValueFlag = true;
	}   //  setErrorValue

	/**
	 *  Get Error Value (the value, which cuased some Error) <b>AND</b> reset it to null
	 *  @return error value
	 */
	public String getErrorValue ()
	{
		String s = m_errorValue;
		m_errorValue = null;
		m_errorValueFlag = false;
		return s;
	}   //  getErrorValue

	/**
	 *  Return true, if value has Error (for HTML interface) <b>AND</b> reset it to false
	 *  @return has error
	 */
	public boolean isErrorValue()
	{
		boolean b = m_errorValueFlag;
		m_errorValueFlag = false;
		return b;
	}   //  isErrorValue

	/**
	 *  Overwrite default DisplayLength
	 *  @param length new length
	 */
	public void setDisplayLength (int length)
	{
		m_vo.DisplayLength = length;
	}   //  setDisplayLength

	/**
	 *  Overwrite Displayed
	 *  @param displayed trie if displayed
	 */
	public void setDisplayed (boolean displayed)
	{
		m_vo.IsDisplayed = displayed;
	}   //  setDisplayed

	
	/**
	 * 	Create Mnemonic for field
	 *	@return no for r/o, client, org, document no
	 */
	public boolean isCreateMnemonic()
	{
		if (isReadOnly() 
			|| m_vo.ColumnName.equals("AD_Client_ID")
			|| m_vo.ColumnName.equals("AD_Org_ID")
			|| m_vo.ColumnName.equals("DocumentNo"))
			return false;
		return true;
	}
	
	/**
	 * 	Get Label Mnemonic
	 *	@return Mnemonic
	 */
	public char getMnemonic()
	{
		return m_mnemonic;
	}	//	getMnemonic
	
	/**
	 * 	Set Label Mnemonic
	 *	@param mnemonic Mnemonic
	 */
	public void setMnemonic (char mnemonic)
	{
		m_mnemonic = mnemonic;
	}	//	setMnemonic

	
	/**
	 *  String representation
	 *  @return string representation
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("GridField[")
			.append(m_vo.ColumnName).append("=").append(m_value);
		if (isKey())
			sb.append("(Key)");
		if (isParentColumn())
			sb.append("(Parent)");
		sb.append(", IsDisplayed="+m_vo.IsDisplayed);
		sb.append("]");
		return sb.toString();
	}   //  toString

	/**
	 *  Extended String representation
	 *  @return string representation
	 */
	public String toStringX()
	{
		StringBuffer sb = new StringBuffer("MField[");
		sb.append(m_vo.ColumnName).append("=").append(m_value)
			.append(",DisplayType=").append(getDisplayType())
			.append("]");
		return sb.toString();
	}   //  toStringX


	/*************************************************************************
	 *  Remove Property Change Listener
	 *  @param l listener
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener l)
	{
		m_propertyChangeListeners.removePropertyChangeListener(l);
	}

	/**
	 *  Add Property Change Listener
	 *  @param l listener
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l)
	{
		m_propertyChangeListeners.addPropertyChangeListener(l);
	}
	
	
	/**************************************************************************
	 * 	Create Fields.
	 * 	Used by APanel.cmd_find  and  Viewer.cmd_find
	 * 	@param ctx context
	 * 	@param WindowNo window
	 * 	@param TabNo tab no
	 * 	@param AD_Tab_ID tab
	 * 	@return array of all fields in display order
	 */
	public static GridField[] createFields (Properties ctx, int WindowNo, int TabNo,
		 int AD_Tab_ID)
	{
		ArrayList<GridFieldVO> listVO = new ArrayList<GridFieldVO>();
		int AD_Window_ID = 0;
		boolean readOnly = false;
		
		String sql = GridFieldVO.getSQL(ctx);
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Tab_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				GridFieldVO vo = GridFieldVO.create(ctx, WindowNo, TabNo, 
					AD_Window_ID, AD_Tab_ID, readOnly, rs);
				listVO.add(vo);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		//
		GridField[] retValue = new GridField[listVO.size()];
		for (int i = 0; i < listVO.size(); i++)
			retValue[i] = new GridField ((GridFieldVO)listVO.get(i));
		return retValue;
	}	//	createFields
		
	/**
	 * bug[1637757]
	 * Check whether is indirect parent. 
	 * @return boolean
	 */
	private boolean isIndirectParentValue()
	{
		boolean result = false;
		int tabNo = m_vo.TabNo;
		int currentLevel = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, tabNo, GridTab.CTX_TabLevel);
		if (tabNo > 1 && currentLevel > 1)
		{
			while ( tabNo >= 1 && !result)
			{
				tabNo--;
				int level = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, tabNo, GridTab.CTX_TabLevel);
				if (level > 0 && level < currentLevel) 
				{
					String linkColumn = Env.getContext(m_vo.ctx, m_vo.WindowNo, tabNo, GridTab.CTX_LinkColumnName);
					if (m_vo.ColumnName.equals(linkColumn))
					{
						result = true;
						log.config(result
								+ " - Link(" + linkColumn + ", W=" + m_vo.WindowNo + ",T=" + m_vo.TabNo
								+ ") = " + m_vo.ColumnName);
					}
					currentLevel = level;
				}
			}
		}
		return result;
	}			
	
	/** Initial context value for this field - teo_sarca [ 1699826 ] */
	private String m_backupValue = null;
	
	/** Is the initial context value for this field backup ? - teo_sarca [ 1699826 ] */
	private boolean m_isBackupValue = false;
	
	/**
	 * Backup the context value
	 * @author teo_sarca [ 1699826 ]
	 */
	private final void backupValue() {
		if (!m_isBackupValue) {
			m_backupValue = get_ValueAsString(m_vo.ColumnName);
			if (CLogMgt.isLevelFinest())
				log.finest("Backup " + m_vo.WindowNo + "|" + m_vo.ColumnName + "=" + m_backupValue);
			m_isBackupValue = true;
		}
	}
	
	/**
	 * Restore the backup value if any
	 * @author teo_sarca [ 1699826 ]
	 */
	public void restoreValue() {
		if (m_isBackupValue) {
			if (isParentTabField())
			{
				if (CLogMgt.isLevelFinest())
					log.finest("Restore " + m_vo.WindowNo + "|" + m_vo.TabNo + "|" + m_vo.ColumnName + "=" + m_backupValue);
				Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, m_vo.ColumnName, m_backupValue);
			}
			else
			{
				if (CLogMgt.isLevelFinest())
					log.finest("Restore " + m_vo.WindowNo + "|" + m_vo.ColumnName + "=" + m_backupValue);
				Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.ColumnName, m_backupValue);
			}
		}
	}
	
	/**
	 * Feature Request [1707462]
	 * Enable user to change VFormat on runtime
	 * @param strNewFormat VFormat mask
	 * @author fer_luck
	 */
	public void setVFormat(String strNewFormat){
		m_vo.VFormat = strNewFormat;
	} //setVFormat
	
	
	/**
    *  Feature Request FR [ 1757088 ]
	*  Get the id tab include
	*  @return id Tab
	*/
	public int getIncluded_Tab_ID ()
	{	 
	 return m_vo.Included_Tab_ID;
	}
	
	/**
	 * Set the default state of collapse field group type
	 * @param flag
	 */
	public void setIsCollapsedByDefault(boolean flag) {
		m_vo.IsCollapsedByDefault = flag;
	}

	/**
	 * Get the default state of collapse field group type
	 * @param collapseDefaultState
	 */
	public boolean getIsCollapsedByDefault() {
		return m_vo.IsCollapsedByDefault;
	}
	
	/**
	 * Returns a list containing all existing entries of this field
	 * with the actual AD_Org_ID and AD_Client_ID.
	 * @return List of existing entries for this field
	 */
	public List<String> getEntries() {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		String sql = "";
		
		try
		{
			String tableName = null;
			String columnName = null;
			int AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
			int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
			sql = "SELECT t.TableName, c.ColumnName " +
					" FROM AD_COLUMN c INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)" +
					" WHERE AD_Column_ID=?";
			pstmt1 = DB.prepareStatement(sql, null);
			pstmt1.setInt(1, getAD_Column_ID());
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next())
			{
				tableName = rs1.getString(1);
				columnName = rs1.getString(2);
							}
			DB.close(rs1, pstmt1);
			
			if (tableName != null && columnName != null) {
				sql = "SELECT DISTINCT "  + columnName + " FROM " + tableName + " WHERE AD_Client_ID=? "
				+ " AND AD_Org_ID=?";
				pstmt2 = DB.prepareStatement(sql, null);
				pstmt2.setInt(1, AD_Client_ID);
				pstmt2.setInt(2, AD_Org_ID);
				
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next())
				{
					list.add(rs2.getString(1));
				}
				DB.close(rs2, pstmt2);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		
		return list;
	}
	
	/**
	 * @param gridTab
	 */
	public void setGridTab(GridTab gridTab)
	{
		m_gridTab = gridTab;
	}

	/**
	 * @return GridTab
	 */
	public GridTab getGridTab()
	{
		return m_gridTab;
	}
	
	/**
	 * @param columnName
	 * @return true if columnName also exist in parent tab
	 */
	private boolean isParentTabField(String columnName)
	{
		if (m_gridTab == null)
			return false;
		GridTab parentTab = m_gridTab.getParentTab();
		if (parentTab == null)
			return false;
		return parentTab.getField(columnName) != null;
	}
	
	/**
	 * 
	 * @return true if this field (m_vo.ColumnName) also exist in parent tab
	 */
	private boolean isParentTabField()
	{
		return isParentTabField(m_vo.ColumnName);
	}
}   //  MField

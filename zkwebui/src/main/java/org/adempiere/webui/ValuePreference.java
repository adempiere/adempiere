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
 *****************************************************************************/
package org.adempiere.webui;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;

/**
 *  Maintain Value Preferences.
 *  To delete a preference, select a null value and save.
 *
 *  @author Jorg Janke
 *  @version  $Id: ValuePreference.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class ValuePreference extends Window implements EventListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8490929927886340040L;

	/**
	 *  Factory
	 *  @param mField	field
	 *  @param aValue	value
	 *  @return ValuePreference or null
	 */
	public static ValuePreference start (GridField mField, Object aValue)
	{
		return start (mField, aValue, null);
	}   //  start

	/**
	 *  Factory
	 *  @param mField	field
	 *  @param aValue	value
	 *  @param aDisplayValue	display value
	 *  @return ValuePreference or null
	 */
	public static ValuePreference start (GridField mField, Object aValue, String aDisplayValue)
	{
		if (!mField.isEditable(false))
		{
			log.info("Field not editable (R/O)");
			return null;
		}
		
		//  Set Value/DisplayValue
		String Value = null;
		String DisplayValue = null;
		if (aValue != null)
		{
			Value = aValue.toString();
			DisplayValue = (aDisplayValue == null) ? Value : aDisplayValue;
		}

		//  Get from mField
		//  AD_Window_ID, DisplayAttribute, Attribute, DisplayType, AD_Referenece_ID
		int AD_Window_ID = mField.getAD_Window_ID();
		String Attribute = mField.getColumnName();
		String DisplayAttribute = mField.getHeader();
		int displayType = mField.getDisplayType();
		int AD_Reference_ID = 0;
		int WindowNo = mField.getWindowNo();

		//  Get from Environment (WindowNo)
		//  AD_Client_ID, AD_Org_ID, AD_User_ID, Frame
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		int AD_Org_ID = Env.getContextAsInt(Env.getCtx(), WindowNo, "AD_Org_ID");
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		
		//  Create Editor
		ValuePreference vp = new ValuePreference (WindowNo,
			AD_Client_ID, AD_Org_ID, AD_User_ID, AD_Window_ID,
			Attribute, DisplayAttribute, Value, DisplayValue,
			displayType, AD_Reference_ID);
		return vp;
	}   //  create

	/**
	 *  Create the popup menu item to start the ValuePreference editor.
	 *  <code>
	 *  .. add method
	 *  public void setField (MField mField)
	 *  {
	 *      m_mField = mField;
	 *      if (m_mField != null)
	 *          ValuePreference.addMenu (this, m_popupMenu);
	 *	}   //  setField
	 *
	 *  .. in actionPerformed add ..
	 *  if (e.getActionCommand().equals(ValuePreference.NAME))
	 *  {
	 *      ValuePreference.start (m_mField, getValue(), DisplayValue);
	 *      return;
	 *  }
	 *  </code>
	 *  @param l listener
	 *  @param popupMenu menu
	 *  @return JMenuItem
	 */
	/*
	public static CMenuItem addMenu (ActionListener l, JPopupMenu popupMenu)
	{
		CMenuItem mi = new CMenuItem (Msg.getMsg(Env.getCtx(), NAME), s_icon);
		mi.setActionCommand(NAME);
		mi.addActionListener(l);
		popupMenu.add(mi);
		return mi;
	}*/   //  addMenu

	/** The Name of the Editor      */
	public static final String      NAME = "ValuePreference";
	/** The Menu Icon               */
	private static String ICON_URL = "images/Preference16.png";
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ValuePreference.class);
	
	/**
	 *  Constructor
	 *
	 *  @param WindowNo window no
	 *  @param AD_Client_ID client
	 *  @param AD_Org_ID org
	 *  @param AD_User_ID user
	 *  @param AD_Window_ID window id
	 *  @param Attribute attribute
	 *  @param DisplayAttribute attribute display
	 *  @param Value value
	 *  @param DisplayValue value display
	 *  @param displayType display type
	 *  @param AD_Reference_ID reference
	 */
	public ValuePreference (int WindowNo,
		int AD_Client_ID, int AD_Org_ID, int AD_User_ID, int AD_Window_ID,
		String Attribute, String DisplayAttribute, String Value, String DisplayValue,
		int displayType, int AD_Reference_ID)
	{
		super();
		this.setTitle(Msg.getMsg(Env.getCtx(), NAME) + " " + DisplayAttribute);
		
		log.config("WindowNo=" + WindowNo
			+ ", Client_ID=" + AD_Client_ID + ", Org_ID=" + AD_Org_ID + ", User_ID=" + AD_User_ID + ", Window_ID=" + AD_Window_ID
			+ ",  Attribute=" + Attribute + "/" + DisplayAttribute + ",  Value=" + Value + "/" + DisplayValue
			+ ",  DisplayType=" + displayType + ", Reference_ID=" + AD_Reference_ID);
		
		m_ctx = Env.getCtx();
		m_WindowNo = WindowNo;
		m_AD_Client_ID = AD_Client_ID;
		m_AD_Org_ID = AD_Org_ID;
		m_AD_User_ID = AD_User_ID;
		m_AD_Window_ID = AD_Window_ID;
		m_Attribute = Attribute;
		m_DisplayAttribute = DisplayAttribute;
		m_Value = Value;
		m_DisplayValue = DisplayValue;
		m_DisplayType = displayType;
		//
		m_role = MRole.getDefault();
		try
		{
			init();
			dynInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		} 
		
		this.setClosable(true);
		AEnv.showCenterScreen(this);
	}   //  ValuePreference

	private Properties      m_ctx;
	private int             m_WindowNo;
	private int             m_AD_Client_ID;
	private int             m_AD_Org_ID;
	private int             m_AD_User_ID;
	private int             m_AD_Window_ID;
	private String          m_Attribute;
	private String          m_DisplayAttribute;
	private String          m_Value;
	private String          m_DisplayValue;
	private int             m_DisplayType;
	private MRole			m_role;

	//  Display
	private Panel setPanel = new Panel();
	private Grid setLayout = new Grid();
	private Label lAttribute = new Label();
	private Textbox fAttribute = new Textbox();
	private Label lAttributeValue = new Label();
	private Label lValue = new Label();
	private Label lValueValue = new Label();
	private Textbox fValue = new Textbox();
	private Label lSetFor = new Label();
	private Checkbox cbClient = new Checkbox();
	private Checkbox cbOrg = new Checkbox();
	private Checkbox cbUser = new Checkbox();
	private Checkbox cbWindow = new Checkbox();
	private Label lExplanation = new Label();

	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Button bDelete;

	/**
	 *  Static Layout
	 *  @throws Exception
	 */
	private void init() throws Exception
	{
		//
		lAttribute.setValue(Msg.translate(m_ctx, "Attribute").replace("&", ""));
		lValue.setValue(Msg.translate(m_ctx, "Value").replace("&", ""));
		lSetFor.setValue(Msg.getMsg(m_ctx, "ValuePreferenceSetFor"));
		cbClient.setLabel(Msg.translate(m_ctx, "AD_Client_ID"));
		cbOrg.setLabel(Msg.translate(m_ctx, "AD_Org_ID"));
		cbUser.setLabel(Msg.translate(m_ctx, "AD_User_ID"));
		cbUser.setChecked(true);
		cbWindow.setLabel(Msg.translate(m_ctx, "AD_Window_ID"));
		cbWindow.setChecked(true);
		// 
		setPanel.appendChild(setLayout);
		fAttribute.setReadonly(true);
		fValue.setReadonly(true);
		
		Vbox box = new Vbox();
		box.setWidth("100%");
		box.setHeight("100%");
		box.setParent(this);
		box.appendChild(setPanel);
		
		Rows rows = new Rows();
		rows.setParent(setLayout);
		
		Row row = new Row();
		row.setSpans("1, 4, 1");
		Div div = new Div();
		div.setStyle("text-align: right");
		div.appendChild(lAttribute);
		row.appendChild(div);
		row.appendChild(fAttribute);
		fAttribute.setWidth("100%");
		row.appendChild(lAttributeValue);
		rows.appendChild(row);
		
		row = new Row();
		row.setSpans("1, 4, 1");
		div = new Div();
		div.setStyle("text-align: right");
		div.appendChild(lValue);
		row.appendChild(div);
		row.appendChild(fValue);
		fValue.setWidth("100%");
		row.appendChild(lValueValue);
		rows.appendChild(row);
		
		row = new Row();
		div = new Div();
		div.setStyle("text-align: right");
		div.appendChild(lSetFor);
		row.appendChild(div);
		row.appendChild(cbClient);
		row.appendChild(cbOrg);
		row.appendChild(cbUser);
		row.appendChild(cbWindow);
		rows.appendChild(row);
		
		row = new Row();
		row.setSpans("1, 5");
		row.appendChild(new Space());
		row.appendChild(lExplanation);
		rows.appendChild(row);
		
		//
		Separator separator = new Separator();
		separator.setBar(true);
		separator.setHeight("20px");
		box.appendChild(separator);
		box.appendChild(confirmPanel);
		
		this.setBorder("normal");
		setLayout.makeNoStrip();
		setLayout.setOddRowSclass("even");
		
	}   //  jbInit

	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		//  Set Attribute/Value
		fAttribute.setText(m_DisplayAttribute);
		lAttributeValue.setValue(m_Attribute);
		fValue.setText(m_DisplayValue);
		lValueValue.setValue(m_Value);
		if (CLogMgt.isLevelFine())
		{
			lAttributeValue.setVisible(false);
			lValueValue.setVisible(false);
		}

		//  ActionListener
		cbClient.setEnabled(false);
		cbClient.setChecked(true);
	//	cbClient.addActionListener(this);
		
		//	Can Change Org
		if (MRole.PREFERENCETYPE_Client.equals(m_role.getPreferenceType()))
			cbOrg.addEventListener(Events.ON_CHECK, this);
		else
		{
			cbOrg.setEnabled(false);
			cbOrg.setChecked(true);
		}
		
		//	Can Change User
		if (MRole.PREFERENCETYPE_Client.equals(m_role.getPreferenceType())
			|| MRole.PREFERENCETYPE_Organization.equals(m_role.getPreferenceType()))
			cbUser.addEventListener(Events.ON_CHECK, this);
		else
		{
			cbUser.setEnabled(false);
			cbUser.setChecked(true);
		}
		//	Can change all/specific
		cbWindow.addEventListener(Events.ON_CHECK, this);

		//  Other
		confirmPanel.addComponentsLeft(confirmPanel.createButton("Delete"));
		confirmPanel.addActionListener(Events.ON_CLICK, this);		
		bDelete = confirmPanel.getButton("Delete");
		setExplanation();
	}   //  dynInit

	/**
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e) throws Exception
	{
		if (e.getTarget().getId().equals("Cancel"))
		{
			this.detach();
		}
		else if (e.getTarget().getId().equals("Ok"))
		{
			insert();
			detach();
		}
		else if (e.getTarget() == bDelete)
		{
			int no = delete();
			if (no == 0)
				FDialog.warn(m_WindowNo, this.getTitle(), "ValuePreferenceNotFound");
			else
				FDialog.info(m_WindowNo, this, "ValuePreferenceDeleted", String.valueOf(no));
			detach();
		}
		else
			setExplanation();
	}   //  actionPerformed

	/**
	 *  Set Explanation
	 */
	private void setExplanation()
	{
		/** @todo translation */
		StringBuffer expl = new StringBuffer("For ");
		if (cbClient.isChecked() && cbOrg.isChecked() )
			expl.append("this Client and Organization");
		else if (cbClient.isChecked() && !cbOrg.isChecked())
			expl.append("all Organizations of this Client");
		else if (!cbClient.isChecked() && cbOrg.isChecked())
		{
			cbOrg.setChecked(false);
			expl.append("entire System");
		}
		else
			expl.append("entire System");
		//
		if (cbUser.isChecked())
			expl.append(", this User");
		else
			expl.append(", all Users");
		//
		if (cbWindow.isChecked())
			expl.append(" and this Window");
		else
			expl.append(" and all Windows");
		//
		if (Env.getLanguage(Env.getCtx()).isBaseLanguage())
		{
			lExplanation.setValue(expl.toString ());
		}
	}   //  setExplanation

	/**
	 *  Delete Preference
	 *  @return number of rows deleted
	 */
	public int delete()
	{
		log.info("");

		StringBuffer sql = new StringBuffer ("DELETE FROM AD_Preference WHERE ");
		sql.append("AD_Client_ID=").append(cbClient.isChecked() ? m_AD_Client_ID : 0);
		sql.append(" AND AD_Org_ID=").append(cbOrg.isChecked() ? m_AD_Org_ID : 0);
		if (cbUser.isChecked())
			sql.append(" AND AD_User_ID=").append(m_AD_User_ID);
		else
			sql.append(" AND AD_User_ID IS NULL");
		if (cbWindow.isChecked())
			sql.append(" AND AD_Window_ID=").append(m_AD_Window_ID);
		else
			sql.append(" AND AD_Window_ID IS NULL");
		sql.append(" AND Attribute='").append(m_Attribute).append("'");
		//
		log.fine( sql.toString());
		int no = DB.executeUpdate(sql.toString(), null);
		if (no > 0)
			Env.setContext(m_ctx, getContextKey(), (String)null);
		return no;
	}   //  delete

	/**
	 *  Get Context Key
	 *  @return Context Key
	 */
	private String getContextKey()
	{
		if (cbWindow.isChecked())
			return "P" + m_AD_Window_ID + "|" + m_Attribute;
		else
			return "P|" + m_Attribute;
	}   //  getContextKey

	/**
	 *  Save to Disk
	 */
	public void insert()
	{
		log.info("");

		//  --- Delete first
		int no = delete();
		
		//	Handle NULL
		if (m_Value == null || m_Value.length() == 0)
		{
			if (DisplayType.isLookup(m_DisplayType))
				m_Value = "-1";	//	 -1 may cause problems (BPartner - M_DiscountSchema
			else if (DisplayType.isDate(m_DisplayType))
				m_Value = " ";
			else
			{
				FDialog.warn(m_WindowNo, this.getTitle(), "ValuePreferenceNotInserted");
				return;
			}
		}

		//  --- Inserting
		int Client_ID = cbClient.isChecked() ? m_AD_Client_ID : 0;
		int Org_ID = cbOrg.isChecked() ? m_AD_Org_ID : 0;
		int AD_Preference_ID = DB.getNextID(m_ctx, "AD_Preference", null);
		//
		StringBuffer sql = new StringBuffer ("INSERT INTO AD_Preference ("
			+ "AD_Preference_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,CreatedBy,Updated,UpdatedBy,"
			+ "AD_Window_ID, AD_User_ID, Attribute, Value) VALUES (");
		sql.append(AD_Preference_ID).append(",").append(Client_ID).append(",").append(Org_ID)
			.append(", 'Y',SysDate,").append(m_AD_User_ID).append(",SysDate,").append(m_AD_User_ID).append(", ");
		if (cbWindow.isChecked())
			sql.append(m_AD_Window_ID).append(",");
		else
			sql.append("NULL,") ;
		if (cbUser.isChecked())
			sql.append(m_AD_User_ID).append(",");
		else
			sql.append("NULL,");
		//
		sql.append(DB.TO_STRING(m_Attribute)).append(",").append(DB.TO_STRING(m_Value)).append(")");
		//
		log.fine( sql.toString());
		no = DB.executeUpdate(sql.toString(), null);
		if (no == 1)
		{
			Env.setContext(m_ctx, getContextKey(), m_Value);
			FDialog.info(m_WindowNo, this, "ValuePreferenceInserted");
		}
		else
			FDialog.warn(m_WindowNo, this.getTitle(), "ValuePreferenceNotInserted");

	}   //  insert


}   //  ValuePreference

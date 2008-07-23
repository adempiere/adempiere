/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.adempiere.webui.window;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.*;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.panel.WSchedule;
import org.compiere.model.*;
import org.compiere.util.*;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Vbox;


/**
 *	Schedule - Resource availability & assigment.
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: InfoSchedule.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 *  Zk Port
 *  @author Low Heng Sin
 */
public class InfoSchedule extends Window implements EventListener //, ChangeListener
{
	/**
	 *  Constructor
	 *  @param mAssignment optional assignment
	 *  @param createNew if true, allows to create new assignments
	 */
	public InfoSchedule (MResourceAssignment mAssignment, boolean createNew)
	{
		super();
		setTitle(Msg.getMsg(Env.getCtx(), "InfoSchedule"));
//		setAttribute("modal", Boolean.valueOf(createNew));
		setAttribute("mode", "overlapped");
		this.setWidth("600px");
		this.setHeight("600px");
		this.setBorder("normal");
		this.setStyle("position: absolute");
		if (mAssignment == null)
			m_mAssignment = new MResourceAssignment(Env.getCtx(), 0, null);
		else
			m_mAssignment = mAssignment;
		if (mAssignment != null)
			log.info(mAssignment.toString());
		m_dateFrom = m_mAssignment.getAssignDateFrom();
		if (m_dateFrom == null)
			m_dateFrom = new Timestamp(System.currentTimeMillis());
		m_createNew = createNew;
		try
		{
			init();
			dynInit(createNew);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "InfoSchedule", ex);
		}
		AEnv.showWindow(this);
	}	//	InfoSchedule

	/**
	 * 	IDE Constructor
	 */
	public InfoSchedule()
	{
		this (null, false);
	}	//	InfoSchedule

	/**	Resource 					*/
	private MResourceAssignment		m_mAssignment;
	/** Date						*/
	private Timestamp		m_dateFrom = null;
	/**	Loading						*/
	private boolean			m_loading = false;
	/**	 Ability to create new assignments	*/
	private boolean			m_createNew;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(InfoSchedule.class);

	private Vbox mainLayout = new Vbox();
	private Grid parameterPanel = new Grid();
	private Label labelResourceType = new Label();
	private Listbox fieldResourceType = new Listbox();
	private Label labelResource = new Label();
	private Listbox fieldResource = new Listbox();
	private Button bPrevious = new Button();
	private Label labelDate = new Label();
	private Datebox fieldDate = new Datebox();
	private Button bNext = new Button();
	private WSchedule schedulePane = new WSchedule(this);
	private StatusBarPanel statusBar = new StatusBarPanel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	/**
	 * 	Static Layout
	 * 	@throws Exception
	 */
	private void init() throws Exception
	{
		this.appendChild(mainLayout);
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		
		labelResourceType.setValue(Msg.translate(Env.getCtx(), "S_ResourceType_ID"));
		labelResource.setValue(Msg.translate(Env.getCtx(), "S_Resource_ID"));
		bPrevious.setLabel("<");
		labelDate.setValue(Msg.translate(Env.getCtx(), "Date"));
		bNext.setLabel(">");
		
		mainLayout.appendChild(parameterPanel);
		
		Rows rows = new Rows();
		rows.setParent(parameterPanel);
		Row row = new Row();
		rows.appendChild(row);
		
		row.appendChild(labelResourceType);
		row.appendChild(labelResource);
		row.appendChild(bPrevious);
		row.appendChild(labelDate);
		row.appendChild(bNext);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(fieldResourceType);
		row.appendChild(fieldResource);
		row.appendChild(new Label(" "));
		row.appendChild(fieldDate);		
		//
		
		mainLayout.appendChild(schedulePane);
		
		schedulePane.setWidth("100%");
		schedulePane.setHeight("400px");
		Div div = new Div();
		div.appendChild(confirmPanel);
		div.appendChild(statusBar);
		mainLayout.appendChild(div);
		
		fieldResourceType.setMold("select");
		fieldResource.setMold("select");
	}	//	jbInit

	/**
	 * 	Dynamic Init
	 *  @param createNew if true, allows to create new assignments
	 */
	private void dynInit (boolean createNew) 
	{
		//	Resource
		fillResourceType();
		fillResource();
		fieldResourceType.addEventListener(Events.ON_SELECT, this);
		fieldResource.addEventListener(Events.ON_SELECT, this);

		//	Date
		fieldDate.setValue(m_dateFrom);
		fieldDate.addEventListener(Events.ON_CHANGE, this);
		bPrevious.addEventListener(Events.ON_CLICK, this);
		bNext.addEventListener(Events.ON_CLICK, this);

		//
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		Button button = confirmPanel.createButton("Add");
		confirmPanel.addComponentsLeft(button);
		button.addEventListener(Events.ON_CLICK, this);
		button.setLabel("Add");
		
		displayCalendar();
	}	//	dynInit

	/**
	 * 	Fill Resource Type (one time)
	 */
	private void fillResourceType()
	{
		//	Get ResourceType of selected Resource
		int S_ResourceType_ID = 0;
		if (m_mAssignment.getS_Resource_ID() != 0)
		{
			String sql = "SELECT S_ResourceType_ID FROM S_Resource WHERE S_Resource_ID=?";
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, m_mAssignment.getS_Resource_ID());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					S_ResourceType_ID = rs.getInt(1);
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
		}

		//	Get Resource Types
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT S_ResourceType_ID, Name FROM S_ResourceType WHERE IsActive='Y' ORDER BY 2",
			"S_ResourceType", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		KeyNamePair defaultValue = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				if (S_ResourceType_ID == pp.getKey())
					defaultValue = pp;
				fieldResourceType.appendItem(pp.getName(), pp.getKey());
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		if (defaultValue != null) {
			int cnt = fieldResourceType.getItemCount();
			for(int i = 0; i < cnt; i++) {
				ListItem li = fieldResourceType.getItemAtIndex(i);
				Integer key = (Integer) li.getValue();
				if (key.intValue() == defaultValue.getKey()) {
					fieldResourceType.setSelectedItem(li);
					break;
				}
			}
		} else if (fieldResourceType.getItemCount() > 0) {
			fieldResourceType.setSelectedIndex(0);
		}
	}	//	fillResourceType

	/**
	 * 	Fill Resource Pick from Resource Type
	 */
	private void fillResource()
	{
		ListItem listItem = fieldResourceType.getSelectedItem();
		if (listItem == null)
			return;
		//	Get Resource Type
		KeyNamePair pp = new KeyNamePair((Integer)listItem.getValue(), listItem.getLabel());
		int S_ResourceType_ID = pp.getKey();

		KeyNamePair defaultValue = null;

		//	Load Resources
		m_loading = true;
		fieldResource.getChildren().clear();
		String sql = "SELECT S_Resource_ID, Name FROM S_Resource WHERE S_ResourceType_ID=? ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, S_ResourceType_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				if (m_mAssignment.getS_Resource_ID() == pp.getKey())
					defaultValue = pp;
				fieldResource.appendItem(pp.getName(), pp.getKey());
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		if (defaultValue != null) {
			int cnt = fieldResource.getItemCount();
			for(int i = 0; i < cnt; i++) {
				ListItem li = fieldResource.getItemAtIndex(i);
				Integer key = (Integer) li.getValue();
				if (key.intValue() == defaultValue.getKey()) {
					fieldResource.setSelectedItem(li);
					break;
				}
			}
		} else if ( fieldResource.getItemCount() > 0) {
			fieldResource.setSelectedIndex(0);
		}

		m_loading = false;
	}	//	fillResource

	/**
	 * 	Display Calendar for selected Resource, Time(day/week/month) and Date
	 */
	private void displayCalendar ()
	{
		//	Get Values
		ListItem listItem = fieldResource.getSelectedItem();
		if (listItem == null)
			return;
		KeyNamePair pp = new KeyNamePair((Integer)listItem.getValue(), listItem.getLabel());
		int S_Resource_ID = pp.getKey();
		m_mAssignment.setS_Resource_ID(S_Resource_ID);
		Date date = fieldDate.getValue();

		//	Set Info
		m_loading = true;
		schedulePane.recreate(S_Resource_ID, date);
		m_loading = false;
		invalidate();
	}	//	displayCalendar

	/**************************************************************************
	 * 	Dispose.
	 */
	public void dispose()
	{
		this.detach();
	}	//	dispose

	/**
	 * 	Adjust Date
	 * 	@param diff difference
	 */
	private void adjustDate (int diff)
	{
		Date date = fieldDate.getValue();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(java.util.Calendar.DAY_OF_YEAR, diff);
		fieldDate.setValue(new Date(cal.getTimeInMillis()));
		displayCalendar ();
	}	//	adjustDate

	/*************************************************************************/

	/**
	 * 	Callback.
	 * 	Called from VSchedulePanel after VAssignmentDialog finished
	 * 	@param assignment New/Changed Assignment
	 */
	public void mAssignmentCallback (MResourceAssignment assignment)
	{
		m_mAssignment = assignment;
		if (m_createNew)
			dispose();
		else
			displayCalendar();
	}	//	mAssignmentCallback

	/**
	 * 	Get Assignment
	 * 	@return Assignment
	 */
	public MResourceAssignment getMResourceAssignment()
	{
		return m_mAssignment;
	}	//	getMResourceAssignment

	public void onEvent(Event event) throws Exception {
		if (m_loading)
			return;

		if (event.getTarget().getId().equals("Ok"))
			dispose();
		else if (event.getTarget().getId().equals("Cancel"))
			dispose();
		//
		else if (event.getTarget() == fieldResourceType)
		{
			fillResource();
			displayCalendar();
		}
		//
		else if (event.getTarget() == fieldResource || event.getTarget() == fieldDate)
			displayCalendar();
		//
		else if (event.getTarget() == bPrevious)
			adjustDate(-1);
		else if (event.getTarget() == bNext)
			adjustDate(+1);
		else if (event.getTarget().getId().equals("Add"))
			doAdd();
		//
		
	}

	private void doAdd() {
		// TODO Auto-generated method stub
		
	}



	/**
SELECT o.DocumentNo, ol.Line, ol.Description
FROM C_OrderLine ol, C_Order o
WHERE ol.S_ResourceAssignment_ID=1
  AND ol.C_Order_ID=o.C_Order_ID
UNION
SELECT i.DocumentNo, il.Line, il.Description
FROM C_InvoiceLine il, C_Invoice i
WHERE il.S_ResourceAssignment_ID=1
  AND il.C_Invoice_ID=i.C_Invoice_ID
UNION
SELECT e.DocumentNo, el.Line, el.Description
FROM S_TimeExpenseLine el, S_TimeExpense e
WHERE el.S_ResourceAssignment_ID=1
  AND el.S_TimeExpense_ID=el.S_TimeExpense_ID
	 */
}	//	InfoSchedule

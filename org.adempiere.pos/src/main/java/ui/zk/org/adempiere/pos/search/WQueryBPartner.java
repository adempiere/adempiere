/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.pos.search;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.adempiere.pos.WPOS;
import org.adempiere.pos.WPOSKeyboard;
import org.adempiere.pos.WPOSTextField;
import org.adempiere.pos.grid.WPOSBPartner;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MBPartnerInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

/**
 *	POS Query BPartner
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @version $Id: QueryBPartner.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WQueryBPartner extends WPOSQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7109518709654253628L;
		
	
	/**
	 * 	Constructor
	 */
	public WQueryBPartner (WPOS posPanel)
	{
		super(posPanel);
	}	//	PosQueryBPartner
	
	private WPOSTextField		f_value;
	private WPOSTextField		f_name;
	private WPOSTextField		f_contact;
	private WPOSTextField		f_email;
	private WPOSTextField		f_phone;
	private WPOSTextField		f_city;
	private int					m_C_BPartner_ID;
	private boolean 			isKeyboard;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WQueryBPartner.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "C_BPartner_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Email"), "Email", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "Phone", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Postal"), "Postal", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "City"), "City", String.class) 
	};
	
	/**
	 * 	Set up Panel
	 */
	protected void init()
	{

		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid bPartnerLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(p_ctx, "Query"));
		
		//	Set title window
		this.setClosable(true);
		
		appendChild(panel);
		//	North
		northPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(groupPanel);
		groupPanel.appendChild(v_TitleBorder);
		groupPanel.appendChild(bPartnerLayout);
		appendChild(mainPanel);
		bPartnerLayout.setWidth("100%");
		Rows rows = null;
		Row row = null;
		rows = bPartnerLayout.newRows();
		row = rows.newRow();
		
		Label lValue = new Label(Msg.translate(p_ctx, "Value"));
		row.appendChild(lValue.rightAlign());
		lValue.setStyle(WPOS.FONTSIZESMALL);
		f_value = new WPOSTextField(null, v_POSPanel.getKeyboard());
		row.appendChild(f_value);
		f_value.setWidth("120px");
		f_value.addEventListener(this);
		f_value.setStyle(WPOS.FONTSIZESMALL);
		
		Label lContact = new Label(Msg.translate(p_ctx, "Contact"));
		row.appendChild(lContact.rightAlign());
		lContact.setStyle(WPOS.FONTSIZESMALL);
		f_contact = new WPOSTextField(null, v_POSPanel.getKeyboard());
		row.appendChild(f_contact);
		f_contact.setWidth("120px");
		f_contact.addEventListener(this);
		f_contact.setStyle(WPOS.FONTSIZESMALL);
		
		Label lPhone = new Label(Msg.translate(p_ctx, "Phone"));
		row.appendChild(lPhone.rightAlign());
		lPhone.setStyle(WPOS.FONTSIZESMALL);
		f_phone = new WPOSTextField(null, v_POSPanel.getKeyboard());
		row.appendChild(f_phone);
		f_phone.setWidth("120px");
		f_phone.addEventListener(this);
		f_phone.setStyle(WPOS.FONTSIZESMALL);
		
		// New Line
		row = rows.newRow();
		Label lName = new Label(Msg.translate(p_ctx, "Name"));
		row.appendChild(lName.rightAlign());
		lName.setStyle(WPOS.FONTSIZESMALL);
		f_name = new WPOSTextField(null, v_POSPanel.getKeyboard());
		row.appendChild(f_name);
		f_name.addEventListener(this);
		f_name.setWidth("120px");
		f_name.setStyle(WPOS.FONTSIZESMALL);
		//
		Label lEmail = new Label(Msg.translate(p_ctx, "Email"));
		row.appendChild(lEmail.rightAlign());
		lEmail.setStyle(WPOS.FONTSIZESMALL);
		f_email = new WPOSTextField(null, v_POSPanel.getKeyboard());
		row.appendChild(f_email);
		f_email.addEventListener(this);
		f_email.setWidth("120px");
		f_email.setStyle(WPOS.FONTSIZESMALL);
		//
		Label lCity = new Label(Msg.translate(p_ctx, "City"));
		row.appendChild(lCity.rightAlign());
		lCity.setStyle(WPOS.FONTSIZESMALL);
		f_city = new WPOSTextField(null, v_POSPanel.getKeyboard());
		f_city.setWidth("120px");
		row.appendChild(f_city);
		f_city.addEventListener("onFocus", this);
		f_city.setStyle(WPOS.FONTSIZESMALL);
		
		m_table = ListboxFactory.newDataTable();
		m_table.prepareTable (s_layout, "C_Order", 
				"C_POS_ID = " + v_POSPanel.getC_POS_ID()
				, false, "C_Order");

		center = new Center();
		center.setStyle("border: none");
		m_table.setWidth("100%");
		m_table.setHeight("99%");
		m_table.addActionListener(this);
		center.appendChild(m_table);
		mainLayout.appendChild(center);
		m_table.setClass("Table-OrderLine");
		m_table.autoSize();
		addNewAction();
	}	//	init
	


	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MBPartnerInfo[] results) {
		m_table.loadTable(results);
		int rowCount = m_table.getRowCount();
		if (rowCount > 0) {
			if(rowCount == 1) {
				select();
			}
		}
	}	//	setResults
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (MBPartnerInfo[] results)
	{
		//	Valid Result
		if(results == null
				|| !(results instanceof MBPartnerInfo[]))
			return;
		//	
		setResultsFromArray((MBPartnerInfo[]) results);
//		m_table.loadTable(results);
		enableButtons();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		m_C_BPartner_ID = -1;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null)
			{
				m_C_BPartner_ID = ID.intValue();
			//	m_BPartnerName = (String)m_table.getValueAt(row, 2);
			//	m_Price = (BigDecimal)m_table.getValueAt(row, 7);
			}
		}
//		f_ok.setEnabled(enabled);
		log.fine("C_BPartner_ID=" + m_C_BPartner_ID); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_C_BPartner_ID = ID.intValue();
	
		if (m_C_BPartner_ID > 0) {
			v_POSPanel.setC_BPartner_ID(m_C_BPartner_ID);
			log.fine("C_BPartner_ID=" + m_C_BPartner_ID);
		} else {
			v_POSPanel.setC_BPartner_ID(0);
		}
		dispose();
	}	//	close


	@Override
	public void reset() {
		f_value.setText(null);
		f_name.setText(null);
		f_contact.setText(null);
		f_email.setText(null);
		f_phone.setText(null);
		f_city.setText(null);
		setResults(new MBPartnerInfo[0]);
		f_Edit.setEnabled(false);
	}
	
	public String showKeyboard(Event e){
		isKeyboard = true;
		Textbox field = (Textbox) e.getTarget();

		WPOSKeyboard keyboard = v_POSPanel.getKeyboard();
		if(e.getName().equals(Events.ON_FOCUS)){
			keyboard.setPosTextField(field);	
			AEnv.showWindow(keyboard);
		}
		return field.getText();
	}
	
	@Override
	protected void newAction() {
		super.newAction();
		WPOSBPartner t = new WPOSBPartner(1, v_POSPanel);
		AEnv.showWindow(t);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
		return;
	}

	/**
	 * Edit Action
	 * @return void
	 */
	public void editAction() {
		WPOSBPartner t = new WPOSBPartner(1, v_POSPanel);
		t.loadBPartner(m_C_BPartner_ID);
		select();
		AEnv.showWindow(t);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
		return;
	}
	/**
	 * Load Data BPartner
	 * @return void
	 */
	public void loadData() {
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try  {
			sql.append(" SELECT b.C_BPartner_ID, b.Value, b.Name, b.Email, b.Phone, b.Postal, b.City")
				.append(" FROM RV_BPartner AS b")
				.append(" WHERE b.C_BPartner_ID = ?");
			int i = 1;			
			pstm = DB.prepareStatement(sql.toString(), null);
			//	POS
			pstm.setInt(i++, v_POSPanel.getC_BPartner_ID());
			rs = pstm.executeQuery();
			m_table.loadTable(rs);
			int rowNo = m_table.getRowCount();
			if (rowNo > 0) {
				m_table.setSelectedIndex(0);
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
		}

	}

	@Override
	public void onEvent(Event e) throws Exception {
		
//		Support for creating customers from the point of sale
		if(e.getTarget().getId().equals("New")) {
			newAction();
			dispose();
			return;
		}else if(e.getTarget().getId().equals("Edit")) {
			editAction();
			dispose();
			return;
		}
		else if(e.getTarget().getId().equals("Reset")){
			reset();
		}
		else if(e.getTarget().equals(f_name.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			f_name.setValue(showKeyboard(e));
			f_name.setFocus(true);
		}
		else if(e.getTarget().equals(f_contact.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			f_contact.setValue(showKeyboard(e));
			f_contact.setFocus(true);
		}
		else if(e.getTarget().equals(f_value.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			f_value.setValue(showKeyboard(e));
			f_value.setFocus(true);
		}
		else if(e.getTarget().equals(f_email.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			f_email.setValue(showKeyboard(e));
			f_email.setFocus(true);
		}
		else if(e.getTarget().equals(f_city.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			f_city.setValue(showKeyboard(e));
			refresh();
			f_city.setFocus(true);
		}
		else if(e.getTarget().equals(f_phone.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			f_phone.setValue(showKeyboard(e));
			f_phone.setFocus(true);
		}
		else if(e.getTarget().equals(f_name.getComponent(WPOSTextField.PRIMARY))  
					|| e.getTarget().equals(f_contact.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(f_value.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(f_email.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(f_city.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(f_phone.getComponent(WPOSTextField.PRIMARY))) {
			 	 refresh();
				 isKeyboard = false;
		}
		if (e.getTarget().getId().equals("Refresh")) {
						refresh();
						return;
		}

			enableButtons();
			if(e.getTarget().getId().equals("Ok")){
				close();
			}
			if(e.getTarget().getId().equals("Cancel")){
				reset();
				dispose();
			}
			if(e.getTarget().equals(m_table)){
				select();
			}
	}

	@Override
	public void refresh() {
		setResults(MBPartnerInfo.find (p_ctx,
				f_value.getText(), f_name.getText(), 
				null, f_email.getText(),
				f_phone.getText(), f_city.getText()));
	}

	@Override
	protected void select() {
		m_C_BPartner_ID = -1;
		
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		f_Edit.setEnabled(false);
		if (enabled) {
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null) {
				f_Edit.setEnabled(true);
				m_C_BPartner_ID = ID.intValue();
			//	m_BPartnerName = (String)m_table.getValueAt(row, 2);
			//	m_Price = (BigDecimal)m_table.getValueAt(row, 7);
			}
		}
		log.fine("C_BPartner_ID=" + m_C_BPartner_ID);
	}


	@Override
	protected void cancel() {
		// TODO Auto-generated method stub
		dispose();
	}
	
	/** 
	 * Get Record ID
	 * @return
	 * @return int
	 */
	public int getRecord_ID() {
		return m_C_BPartner_ID;
	}

	/** 
	 * Get Value
	 * @return
	 * @return String
	 */
	public String getValue() {
		return null;
	}
}	//	PosQueryBPartner

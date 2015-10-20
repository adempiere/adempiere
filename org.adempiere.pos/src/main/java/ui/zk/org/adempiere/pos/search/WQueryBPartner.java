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

import org.adempiere.pos.WPOS;
import org.adempiere.pos.WPOSKeyboard;
import org.adempiere.pos.WPosTextField;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.grid.WBPartner;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
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
 */
public class WQueryBPartner extends WPosQuery {
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
	
	private WPosTextField		f_value;
	private WPosTextField		f_name;
	private WPosTextField		f_contact;
	private WPosTextField		f_email;
	private WPosTextField		f_phone;
	private Textbox				f_city;

	private int					m_C_BPartner_ID;
	
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
	/**	From Clause							*/
	private static String s_sqlFrom = "RV_BPartner";
	/** Where Clause						*/
	private static String s_sqlWhere = "IsActive='Y'"; 

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
		
		Label lvalue = new Label(Msg.translate(p_ctx, "Value"));
		row.appendChild(lvalue);
		f_value = new WPosTextField(v_POSPanel, v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_value);
		f_value.addEventListener("onFocus", this);
		
		Label lcontact = new Label(Msg.translate(p_ctx, "Contact"));
		row.appendChild(lcontact);
		f_contact = new WPosTextField(v_POSPanel, v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_contact);
		f_contact.addEventListener("onFocus", this);
		
		Label lphone = new Label(Msg.translate(p_ctx, "Phone"));
		row.appendChild(lphone);
		f_phone = new WPosTextField(v_POSPanel, v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_phone);
		f_phone.addEventListener("onFocus", this);
		
		// New Line
		row = rows.newRow();
		Label lname = new Label(Msg.translate(p_ctx, "Name"));
		row.appendChild(lname);
		f_name = new WPosTextField(v_POSPanel, v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_name);
		f_name.addEventListener("onFocus", this);
		//
		Label lemail = new Label(Msg.translate(p_ctx, "Email"));
		row.appendChild(lemail);
		f_email = new WPosTextField(v_POSPanel, v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_email);
		f_email.addEventListener("onFocus", this);
		//
		Label lcity = new Label(Msg.translate(p_ctx, "City"));
		row.appendChild(lcity);
		f_city = new Textbox();
		row.appendChild(f_city);
		f_city.addEventListener("onFocus", this);
		
		m_table.prepareTable (s_layout, s_sqlFrom, 
				s_sqlWhere, false, "RV_BPartner");
		center = new Center();
		center.setStyle("border: none; Height=80%");
		m_table.setWidth("100%");
		m_table.addActionListener(this);
		center.appendChild(m_table);
		mainLayout.appendChild(center);
		m_table.loadTable(new PO[0]);
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
	
	public void showKeyboard(Component  p_field, String eventName){
		WPosTextField field = (WPosTextField) p_field;

		WPOSKeyboard keyboard = v_POSPanel.getKeyboard(field.getKeyLayoutId()); 
		keyboard.setTitle(Msg.translate(Env.getCtx(), ""));
		keyboard.setPosTextField(field);	
		if(eventName.equals("onFocus")) {
			keyboard.setVisible(true);
			keyboard.setWidth("750px");
			keyboard.setHeight("380px");
			AEnv.showWindow(keyboard);
			m_table.setFocus(true);
		}
	}
	@Override
	protected void newAction() {
		super.newAction();
		WBPartner t = new WBPartner(1);
		AEnv.showWindow(t);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
		return;
	}
	
	public void editAction() {
		WBPartner t = new WBPartner(1);
		t.loadBPartner(m_C_BPartner_ID);
		select();
		AEnv.showWindow(t);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
		return;
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
		else if(e.getTarget().equals(f_name) || e.getTarget().equals(f_contact)
				|| e.getTarget().equals(f_value) || e.getTarget().equals(f_email)
				|| e.getTarget().equals(f_city) || e.getTarget().equals(f_phone)){
				showKeyboard(e.getTarget(), e.getName());
				refresh();
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
		m_C_BPartner_ID = 0;
		dispose();
	}
	
	public int getRecord_ID() {
		return m_C_BPartner_ID;
	}

	
	public String getValue() {
		return null;
	}
}	//	PosQueryBPartner

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
import org.adempiere.pos.service.POSQueryListener;
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
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
	
	private WPOSTextField 		fieldValue;
	private WPOSTextField 		fieldTaxID;
	private WPOSTextField 		fieldName;
	private WPOSTextField 		fieldName2;
	private WPOSTextField 		fieldContact;
	private WPOSTextField 		fieldEmail;
	private WPOSTextField 		fieldPhone;
	private WPOSTextField 		fieldCity;
	private int 				partnerId;
	private boolean 			isKeyboard;
	/**	Listener		*/
	private POSQueryListener listener;

	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(WQueryBPartner.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] columnInfos = new ColumnInfo[]
	{
		new ColumnInfo(" ", "C_BPartner_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "TaxID"), "TaxID", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name2"), "Name2", String.class),
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
		setTitle(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid bPartnerLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(ctx, "Query"));
		
		//	Set title window
		this.setClosable(true);
		// add listener on 'ENTER' key 
        addEventListener(Events.ON_OK,this);
        
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
		
		Label labelValue = new Label(Msg.translate(ctx, "Value"));
		row.appendChild(labelValue.rightAlign());
		labelValue.setStyle(WPOS.FONTSIZESMALL);
		fieldValue = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldValue);
		fieldValue.setWidth("120px");
		fieldValue.addEventListener(this);
		fieldValue.setStyle(WPOS.FONTSIZESMALL);

		Label labelTaxID = new Label(Msg.translate(ctx, "TaxID"));
		row.appendChild(labelTaxID.rightAlign());
		labelValue.setStyle(WPOS.FONTSIZESMALL);
		fieldTaxID = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldTaxID);
		fieldTaxID.setWidth("120px");
		fieldTaxID.addEventListener(this);
		fieldTaxID.setStyle(WPOS.FONTSIZESMALL);

		
		Label labelContact = new Label(Msg.translate(ctx, "Contact"));
		row.appendChild(labelContact.rightAlign());
		labelContact.setStyle(WPOS.FONTSIZESMALL);
		fieldContact = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldContact);
		fieldContact.setWidth("120px");
		fieldContact.addEventListener(this);
		fieldContact.setStyle(WPOS.FONTSIZESMALL);
		
		Label labelPhone = new Label(Msg.translate(ctx, "Phone"));
		row.appendChild(labelPhone.rightAlign());
		labelPhone.setStyle(WPOS.FONTSIZESMALL);
		fieldPhone = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldPhone);
		fieldPhone.setWidth("120px");
		fieldPhone.addEventListener(this);
		fieldPhone.setStyle(WPOS.FONTSIZESMALL);
		
		// New Line
		row = rows.newRow();
		Label labelName = new Label(Msg.translate(ctx, "Name"));
		row.appendChild(labelName.rightAlign());
		labelName.setStyle(WPOS.FONTSIZESMALL);
		fieldName = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldName);
		fieldName.addEventListener(this);
		fieldName.setWidth("120px");
		fieldName.setStyle(WPOS.FONTSIZESMALL);

		Label labelName2 = new Label(Msg.translate(ctx, "Name2"));
		row.appendChild(labelName2.rightAlign());
		labelName2.setStyle(WPOS.FONTSIZESMALL);
		fieldName2 = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldName2);
		fieldName2.addEventListener(this);
		fieldName2.setWidth("120px");
		fieldName2.setStyle(WPOS.FONTSIZESMALL);
		//
		Label labelEmail = new Label(Msg.translate(ctx, "Email"));
		row.appendChild(labelEmail.rightAlign());
		labelEmail.setStyle(WPOS.FONTSIZESMALL);
		fieldEmail = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldEmail);
		fieldEmail.addEventListener(this);
		fieldEmail.setWidth("120px");
		fieldEmail.setStyle(WPOS.FONTSIZESMALL);
		//
		Label labelCity = new Label(Msg.translate(ctx, "City"));
		row.appendChild(labelCity.rightAlign());
		labelCity.setStyle(WPOS.FONTSIZESMALL);
		fieldCity = new WPOSTextField(null, posPanel.getKeyboard());
		fieldCity.setWidth("120px");
		row.appendChild(fieldCity);
		fieldCity.addEventListener("onFocus", this);
		fieldCity.setStyle(WPOS.FONTSIZESMALL);
		
		posTable = ListboxFactory.newDataTable();
		posTable.prepareTable (columnInfos, "C_Order",
				"C_POS_ID = " + posPanel.getC_POS_ID()
				, false, "C_Order");

		center = new Center();
		center.setStyle("border: none");
		posTable.setWidth("100%");
		posTable.setHeight("99%");
		posTable.addActionListener(this);
		center.appendChild(posTable);
		mainLayout.appendChild(center);
		posTable.setClass("Table-OrderLine");
		posTable.autoSize();
		posTable.addEventListener(Events.ON_DOUBLE_CLICK, this);
		addNewAction();
	}	//	init
	


	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MBPartnerInfo[] results) {
		posTable.loadTable(results);
		int rowCount = posTable.getRowCount();
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
//		table.loadTable(results);
		enableButtons();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		partnerId = -1;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				partnerId = ID.intValue();
			//	m_BPartnerName = (String)table.getValueAt(row, 2);
			//	m_Price = (BigDecimal)table.getValueAt(row, 7);
			}
		}
//		f_ok.setEnabled(enabled);
		logger.fine("C_BPartner_ID=" + partnerId);
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		Integer ID = posTable.getSelectedRowKey();
		if (ID != null)
			partnerId = ID.intValue();
	
		if (partnerId > 0) {
			posPanel.configureBPartner(partnerId);
			//	Refresh
			posPanel.refreshPanel();
			logger.fine("C_BPartner_ID=" + partnerId);
		} else {
			posPanel.configureBPartner(0);
		}
		dispose();
	}	//	close


	@Override
	public void reset() {
		fieldValue.setText(null);
		fieldTaxID.setText(null);
		fieldName.setText(null);
		fieldName2.setText(null);
		fieldContact.setText(null);
		fieldEmail.setText(null);
		fieldPhone.setText(null);
		fieldCity.setText(null);
		setResults(new MBPartnerInfo[0]);
		buttonEdit.setEnabled(false);
	}
	
	public String showKeyboard(Event e){
		isKeyboard = true;
		Textbox field = (Textbox) e.getTarget();

		WPOSKeyboard keyboard = posPanel.getKeyboard();
		
		if(keyboard != null) {
			if(e.getName().equals(Events.ON_FOCUS)){
				keyboard.setPosTextField(field);	
				AEnv.showWindow(keyboard);
			}
		}
		return field.getText();
	}
	
	@Override
	protected void newAction() {
		super.newAction();
		WPOSBPartner t = new WPOSBPartner(1, posPanel);
		AEnv.showWindow(t);
		partnerId = t.getC_BPartner_ID();
		//	Close
		close();
		return;
	}

	/**
	 * Edit Action
	 * @return void
	 */
	public void editAction() {
		WPOSBPartner t = new WPOSBPartner(1, posPanel);
		t.loadBPartner(partnerId);
		select();
		AEnv.showWindow(t);
		partnerId = t.getC_BPartner_ID();
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
			pstm.setInt(i++, posPanel.getC_BPartner_ID());
			rs = pstm.executeQuery();
			posTable.loadTable(rs);
			int rowNo = posTable.getRowCount();
			if (rowNo > 0) {
				posTable.setSelectedIndex(0);
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			logger.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
		}

	}

	@Override
	public void onEvent(Event e) throws Exception {
		String m_Text;
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
		else if(e.getTarget().equals(fieldName.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldName.setValue(m_Text);
			fieldName.setFocus(true);
		}
		else if(e.getTarget().equals(fieldName2.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldName2.setValue(m_Text);
			fieldName2.setFocus(true);
		}
		else if(e.getTarget().equals(fieldContact.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldContact.setValue(m_Text);
			fieldContact.setFocus(true);
		}
		else if(e.getTarget().equals(fieldValue.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldValue.setValue(m_Text);
			fieldValue.setFocus(true);
		}
		else if(e.getTarget().equals(fieldTaxID.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldTaxID.setValue(m_Text);
			fieldTaxID.setFocus(true);
		}
		else if(e.getTarget().equals(fieldEmail.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldEmail.setValue(m_Text);
			
			fieldEmail.setFocus(true);
		}
		else if(e.getTarget().equals(fieldCity.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldCity.setValue(m_Text);
			refresh();
			fieldCity.setFocus(true);
		}
		else if(e.getTarget().equals(fieldPhone.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			m_Text = showKeyboard(e);
			if(m_Text.length() > 0)
				fieldPhone.setValue(m_Text);
			fieldPhone.setFocus(true);
		}
		else if(e.getTarget().equals(fieldName.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldName2.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldContact.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldValue.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldTaxID.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldEmail.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldCity.getComponent(WPOSTextField.PRIMARY))
					|| e.getTarget().equals(fieldPhone.getComponent(WPOSTextField.PRIMARY))) {
			 	 refresh();
				 isKeyboard = false;
		}
        else if (Events.ON_OK.equals(e.getName()) 
        		|| e.getTarget().equals(posTable) 
        		&& e.getName().equals(Events.ON_DOUBLE_CLICK)) {
        	close();
			//	Fire
			if(listener != null) {
				okAction();
			}
        }
		if (e.getTarget().getId().equals("Refresh")) {
			refresh();
			return;
		}

			enableButtons();
			if(e.getTarget().getId().equals("Ok")){
				close();
				//	Fire
				if(listener != null) {
					okAction();
				}
			}
			if(e.getTarget().getId().equals("Cancel")){
				reset();
				dispose();
			}
			if(e.getTarget().equals(posTable)){
				select();
			}
	}

	@Override
	public void refresh() {
		lockUI();
		setResults(MBPartnerInfo.find (ctx,
				fieldValue.getText(), fieldTaxID.getText(),
				fieldName.getText(), fieldName2.getText() ,
				null, fieldEmail.getText(), fieldPhone.getText(), fieldCity.getText()));
		unlockUI();
	}

	@Override
	protected void select() {
		partnerId = -1;
		
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		buttonEdit.setEnabled(false);
		if (enabled) {
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null) {
				buttonEdit.setEnabled(true);
				partnerId = ID.intValue();
			//	m_BPartnerName = (String)table.getValueAt(row, 2);
			//	m_Price = (BigDecimal)table.getValueAt(row, 7);
			}
		}
		logger.fine("C_BPartner_ID=" + partnerId);
	}


	@Override
	protected void cancel() {
		dispose();
	}
	
	/** 
	 * Get Record ID
	 * @return
	 * @return int
	 */
	public int getRecord_ID() {
		return partnerId;
	}

	/** 
	 * Get Value
	 * @return
	 * @return String
	 */
	public String getValue() {
		return null;
	}
	/**
	 * Add Listener
	 * @param listener
	 * @return void
	 */
	public void addOptionListener(POSQueryListener listener) {
		this.listener = listener;
	}



	@Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}
}	//	PosQueryBPartner

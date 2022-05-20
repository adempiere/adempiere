/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

import java.io.IOException;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.form.ICreateFrom;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public class WCreateFromPanel extends Panel implements EventListener, WTableModelListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4455920669263736422L;
	
	/**	Create From Parent	*/
	private ICreateFrom 	createFrom;
	/**	Parameter Panel		*/
	private Panel 			parameterPanel = new Panel();
	/**	Confirm Panel		*/
	private ConfirmPanel 	confirmPanel = new ConfirmPanel(true);
	/**	Status Bar			*/
	private StatusBarPanel 	statusBar = new StatusBarPanel();
	/**	Table				*/
	private WListbox 		dataTable = ListboxFactory.newDataTable();
	/**	Select All Constant	*/
	public static final String SELECT_ALL = "SelectAll";
	
	/**
	 * Standard Constructor
	 * @param createFrom
	 */
	public WCreateFromPanel(ICreateFrom createFrom) throws IOException {
		super();
		this.createFrom = createFrom;
		//	Create UI
		zkInit();
		confirmPanel.addActionListener(this);
		//	Set Status Bar	
		statusBar.setStatusDB("");
		tableChanged(null);
    }
	
	/**
	 * Create UI
	 * @throws IOException 
	 */
	protected void zkInit() throws IOException {
		Borderlayout contentPane = new Borderlayout();
		appendChild(contentPane);
		
		North north = new North();
		contentPane.appendChild(north);
		north.appendChild(parameterPanel);
		
		Center center = new Center();
        contentPane.appendChild(center);
		center.appendChild(dataTable);
		
		WAppsAction selectAllAction = new WAppsAction (SELECT_ALL, null, null);
		Button selectAllButton = selectAllAction.getButton();
		confirmPanel.addComponentsLeft(selectAllButton);
		selectAllButton.addActionListener(this);
		
		South south = new South();
		contentPane.appendChild(south);
		Panel southPanel = new Panel();
		south.appendChild(southPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(confirmPanel);

		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		
		setWidth("750px");
		setHeight("550px");
		contentPane.setWidth("100%");
		contentPane.setHeight("100%");
	}

	public void onEvent(Event e) throws Exception
	{
		//  OK - Save
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			try {
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						if (save(trxName)) {
							createFrom.dispose();
						}
					}
				});
			} catch (Exception ex) {
				FDialog.error(createFrom.getWindowNo(), this, "Error", ex.getLocalizedMessage());
			}
		}
		//  Cancel
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			createFrom.dispose();
		}
		// Select All
		// Trifon
		else if (e.getTarget().getId().equals(SELECT_ALL)) {
			ListModelTable model = dataTable.getModel();
			int rows = model.getSize();
			for (int i = 0; i < rows; i++) {
				model.setValueAt(Boolean.TRUE, i, 0);
			}
			//refresh
			dataTable.setModel(model);
			info();
		}
	}

	/**
	 * Changes in Table
	 */
	public void tableChanged (WTableModelEvent e) {
		int type = -1;
		if (e != null)
		{
			type = e.getType();
			if (type != WTableModelEvent.CONTENTS_CHANGED)
				return;
		}
		info();
	}
	
	/**
	 * Save Record
	 * @param trxName
	 * @return
	 */
	public boolean save(String trxName) {
		ListModelTable model = dataTable.getModel();
		int rows = model.getSize();
		if (rows == 0)
			return false;
		
		return createFrom.save(dataTable, trxName);
	}

	/**
	 * Update Info
	 */
	public void info() {
		//	If the method is not used then refresh
		if(!createFrom.info()) {
			ListModelTable model = dataTable.getModel();
			int rows = model.getRowCount();
			int count = 0;
			for (int i = 0; i < rows; i++) {
				if (((Boolean) model.getValueAt(i, 0)).booleanValue())
					count++;
			}
			//	Set Status Bar
			setStatusLine(count, Msg.getMsg(Env.getCtx(), "Selected"));
		}
	}
	
	/**
	 * Set data to Status Bar
	 * @param selectedRowCount
	 * @param text
	 */
	public void setStatusLine(int selectedRowCount, String text)  {
		StringBuffer sb = new StringBuffer(String.valueOf(selectedRowCount));
		if (text != null && text.trim().length() > 0) {
			sb.append(" - ").append(text);
		}
		statusBar.setStatusLine(sb.toString());
		//
		confirmPanel.getOKButton().setEnabled(selectedRowCount > 0);
	}
	
	/**
	 * Get Table
	 * @return
	 */
	public WListbox getWListbox() {
		return dataTable;
	}
	
	/**
	 * Get Parameter Panel, you must add custom parameter here
	 * @return
	 */
	public Panel getParameterPanel() {
		return parameterPanel;
	}
	
	/**
	 * Get Confirm Panel
	 * @return
	 */
	public ConfirmPanel getConfirmPanel() {
		return confirmPanel;
	}
}
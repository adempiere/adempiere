/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.webui.apps.form;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.apps.form.CreateFromStatement;
import org.compiere.apps.form.ICreateFrom;
import org.compiere.model.MBankStatement;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zul.Hbox;

/**
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 *		<li> FR [ 441 ] Create From in C_BankStatement change to Smart Browse
 *		@see https://github.com/adempiere/adempiere/issues/441
 */
@Deprecated
public class WCreateFromStatementUI extends CreateFromStatement 
	implements IFormController, ICreateFrom, EventListener {
	
	/**
	 * Standard Constructor
	 */
	public WCreateFromStatementUI() {
		try {
			v_CreateFromPanel = new WCreateFromPanel(this);
			v_Container = new CustomForm() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -6022139819209111460L;

				public void setProcessInfo(ProcessInfo pi) {
					p_WindowNo = pi.getWindowNo();
					try {
						//	Valid for launched from a window
						if(pi != null) {
							//	Valid Table and Record
							validTable(pi.getTable_ID(), 
									pi.getRecord_ID());
						}
						//	Init
						if (!dynInit())
							return;
						zkInit();
					} catch(Exception e) {
						log.log(Level.SEVERE, "", e);
					}
				}
			};
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
		}
	}

	//	Yamel Senih FR [ 114 ], 2015-11-26
	//	Change to form
	private CustomForm v_Container = null;
	/**	Main Panel for Create From	*/
	private WCreateFromPanel v_CreateFromPanel;
	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	
	private Label bankAccountLabel = new Label();
	private WTableDirEditor bankAccountField;
	
	private Label documentNoLabel = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	private WStringEditor documentNoField = new WStringEditor();

	private Label documentTypeLabel = new Label();
	private WTableDirEditor documentTypeField;

	private Label authorizationLabel = new Label();
	private WStringEditor authorizationField = new WStringEditor();

	private Label tenderTypeLabel = new Label();
	private WTableDirEditor tenderTypeField;
	
	private Label amtFromLabel = new Label(Msg.translate(Env.getCtx(), "PayAmt"));
	private WNumberEditor amtFromField = new WNumberEditor("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private Label amtToLabel = new Label("-");
	private WNumberEditor amtToField = new WNumberEditor("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	
	private Label BPartner_idLabel = new Label(Msg.translate(Env.getCtx(), "BPartner"));
	private WEditor bPartnerLookup;

	private Label dateFromLabel = new Label(Msg.translate(Env.getCtx(), "DateTrx"));
	private WDateEditor dateFromField = new WDateEditor("DateFrom", false, false, true, Msg.translate(Env.getCtx(), "DateFrom"));
	private Label dateToLabel = new Label("-");
	private WDateEditor dateToField = new WDateEditor("DateTo", false, false, true, Msg.translate(Env.getCtx(), "DateTo"));

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception {
		log.config("");
		
		//Refresh button
		Button refreshButton = v_CreateFromPanel.getConfirmPanel().createButton(ConfirmPanel.A_REFRESH);
		refreshButton.addEventListener(Events.ON_CLICK, this);
		v_CreateFromPanel.getConfirmPanel().addButton(refreshButton);
		
		int AD_Column_ID = 4917;        //  C_BankStatement.C_BankAccount_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir);
		bankAccountField = new WTableDirEditor ("C_BankAccount_ID", true, true, true, lookup);
		//  Set Default
		bankAccountField.setValue(getC_BankAccount_ID());
		//  initial Loading
		authorizationField = new WStringEditor ("authorization", false, false, true, 10, 30, null, null);
		authorizationField.getComponent().addEventListener(Events.ON_CHANGE, this);

		lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_DocType_ID), DisplayType.TableDir);
		documentTypeField = new WTableDirEditor (MPayment.COLUMNNAME_C_DocType_ID,false,false,true,lookup);
		documentTypeField.getComponent().addEventListener(Events.ON_CHANGE, this);
		
		lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_TenderType), DisplayType.List);
		tenderTypeField = new WTableDirEditor (MPayment.COLUMNNAME_TenderType,false,false,true,lookup);
		tenderTypeField.getComponent().addEventListener(Events.ON_CHANGE, this);
		
		lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search);
		bPartnerLookup = new WSearchEditor ("C_BPartner_ID", false, false, true, lookup);
		
		Timestamp date = Env.getContextAsDate(Env.getCtx(), p_WindowNo, MBankStatement.COLUMNNAME_StatementDate);
		dateToField.setValue(date);
		
		loadBankAccount();
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception {
		bankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
    	authorizationLabel.setText(Msg.translate(Env.getCtx(), "R_AuthCode"));
    	
    	documentTypeLabel.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
    	tenderTypeLabel.setText(Msg.translate(Env.getCtx(), "TenderType"));
    	
    	dateFromField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "DateFrom"));
    	dateToField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "DateTo"));
    	
    	amtFromField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "AmtFrom"));
    	amtToField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "AmtTo"));
    	
    	Borderlayout parameterLayout = new Borderlayout();
		parameterLayout.setHeight("120px");
		parameterLayout.setWidth("100%");
    	Panel parameterPanel = v_CreateFromPanel.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid parameterBankLayout = GridFactory.newGridLayout();
    	Panel parameterBankPanel = new Panel();
    	parameterBankPanel.appendChild(parameterBankLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterBankPanel);
		
		Rows rows = (Rows) parameterBankLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(bankAccountLabel.rightAlign());
		row.appendChild(bankAccountField.getComponent());
		row.appendChild(documentNoLabel.rightAlign());
		row.appendChild(documentNoField.getComponent());
		
		row = rows.newRow();
		row.appendChild(documentTypeLabel.rightAlign());
		row.appendChild(documentTypeField.getComponent());
		row.appendChild(authorizationLabel.rightAlign());
		row.appendChild(authorizationField.getComponent());
		
		row = rows.newRow();
		row.appendChild(tenderTypeLabel.rightAlign());
		row.appendChild(tenderTypeField.getComponent());

		row.appendChild(amtFromLabel.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(amtFromField.getComponent());
		hbox.appendChild(amtToLabel.rightAlign());
		hbox.appendChild(amtToField.getComponent());
		row.appendChild(hbox);
		
		row = rows.newRow();
		row.appendChild(BPartner_idLabel.rightAlign());
		row.appendChild(bPartnerLookup.getComponent());
		row.appendChild(dateFromLabel.rightAlign());
		
		hbox = new Hbox();
		hbox.appendChild(dateFromField.getComponent());
		hbox.appendChild(dateToLabel.rightAlign());
		hbox.appendChild(dateToField.getComponent());
		row.appendChild(hbox);
		//	Add to Main
		v_CreateFromPanel.setWidth("100%");
		v_CreateFromPanel.setHeight("100%");
		v_Container.appendChild(v_CreateFromPanel);
	}

	/**
	 *  Action Listener
	 *  @param e event
	 * @throws Exception 
	 */
	public void onEvent(Event e) throws Exception {
		log.config("Action=" + e.getTarget().getId());
		if(e.getTarget().equals(v_CreateFromPanel.getConfirmPanel().getButton(ConfirmPanel.A_REFRESH))) {
			loadBankAccount();
			v_CreateFromPanel.tableChanged(null);
		}
	}
	
	/**
	 * Load Bank Account
	 */
	private void loadBankAccount() {
		loadTableOIS(getBankData(documentNoField.getValue().toString(), bPartnerLookup.getValue(), dateFromField.getValue(), dateToField.getValue(),
				amtFromField.getValue(), amtToField.getValue(), documentTypeField.getValue(), tenderTypeField.getValue(), 
				authorizationField.getValue().toString()));
	}
	
	/**
	 * Load Table Columns
	 * @param data
	 */
	private void loadTableOIS (Vector<?> data) {
		v_CreateFromPanel.getWListbox().clear();
		
		//  Remove previous listeners
		v_CreateFromPanel.getWListbox().getModel().removeTableModelListener(v_CreateFromPanel);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(v_CreateFromPanel);
		v_CreateFromPanel.getWListbox().setData(model, getOISColumnNames());
		//
		
		configureMiniTable(v_CreateFromPanel.getWListbox());
	}
	
	/**
	 *  List total amount
	 */
	public boolean info() {
		//	Valid null
		if(v_CreateFromPanel == null)
			return false;
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

		BigDecimal total = new BigDecimal(0.0);
		int rows = v_CreateFromPanel.getWListbox().getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)v_CreateFromPanel.getWListbox().getValueAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)v_CreateFromPanel.getWListbox().getValueAt(i, 4));
				count++;
			}
		}
		v_CreateFromPanel.setStatusLine(count, Msg.getMsg(Env.getCtx(), "Sum") + "  " + format.format(total));
		//	Default return true for update panel from it method
		return true;
	}   //  infoStatement

	@Override
	public int getWindowNo() {
		return v_Container.getWindowNo();
	}

	@Override
	public void dispose() {
		v_Container.dispose();
	}

	@Override
	public ADForm getForm() {
		return v_Container;
	}
}

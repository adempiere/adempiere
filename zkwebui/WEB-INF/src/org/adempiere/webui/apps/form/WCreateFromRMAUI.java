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
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.apps.form.CreateFromRMA;
import org.compiere.apps.form.ICreateFrom;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;

/**
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Deprecated (Change "Create From" UI for Form like Dialog in window without "hardcode")
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public class WCreateFromRMAUI extends CreateFromRMA 
	implements IFormController, ICreateFrom, ValueChangeListener
{
	/**
	 * Standard Constructor
	 */
	public WCreateFromRMAUI() {
		try {
			v_CreateFromPanel = new WCreateFromPanel(this);
			v_Container = new CustomForm() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -3454354880167040226L;

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
		
	protected Label bPartnerLabel = new Label();
	protected WEditor bPartnerField;
	
	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		//	Init BPartner
		initBPartner(true);
		bPartnerField.addValueChangeListener(this);
		//	Load RMA
		loadRMA();
		//	Return
		return true;
	}   //  dynInit
	
	/**
	 * Init ZK
	 * @throws Exception
	 */
	protected void zkInit() throws Exception
	{
		bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));
        
		Borderlayout parameterLayout = new Borderlayout();
		parameterLayout.setHeight("120px");
		parameterLayout.setWidth("100%");
    	Panel parameterPanel = v_CreateFromPanel.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid parameterStdLayout = GridFactory.newGridLayout();
    	Panel parameterStdPanel = new Panel();
		parameterStdPanel.appendChild(parameterStdLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterStdPanel);
		
		Rows rows = (Rows) parameterStdLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(bPartnerLabel.rightAlign());
		if (bPartnerField != null)
			row.appendChild(bPartnerField.getComponent());
    	//	Add to Main
    	v_CreateFromPanel.setWidth("100%");
    	v_CreateFromPanel.setHeight("100%");
    	v_Container.appendChild(v_CreateFromPanel);
	}
	
	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e)
	{
		log.config(e.getPropertyName() + "=" + e.getNewValue());

		//  BPartner - load Order/Invoice/Shipment
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			loadRMA();
		}
		v_CreateFromPanel.tableChanged(null);
	}   //  vetoableChange
	
	/**************************************************************************
	 *  Load BPartner Field
	 *  @param forInvoice true if Invoices are to be created, false receipts
	 *  @throws Exception if Lookups cannot be initialized
	 */
	protected void initBPartner (boolean forInvoice) throws Exception
	{
		//  load BPartner
		int AD_Column_ID = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		bPartnerField = new WSearchEditor ("C_BPartner_ID", true, false, true, lookup);
		//
		int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
		bPartnerField.setValue(Integer.valueOf(C_BPartner_ID));
	}   //  initBPartner
	
	protected void loadRMA()
	{
		loadTableOIS(getRMAData());
	}
	
	/**
	 *  Load Order/Invoice/Shipment data into Table
	 *  @param data data
	 */
	protected void loadTableOIS (Vector<?> data)
	{
		v_CreateFromPanel.getWListbox().clear();
		
		//  Remove previous listeners
		v_CreateFromPanel.getWListbox().getModel().removeTableModelListener(v_CreateFromPanel);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(v_CreateFromPanel);
		v_CreateFromPanel.getWListbox().setData(model, getOISColumnNames());
		//
		
		configureMiniTable(v_CreateFromPanel.getWListbox());
	}   //  loadOrder
	
	/**
	 *  List total amount
	 */
	public boolean info() {
		return false;
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

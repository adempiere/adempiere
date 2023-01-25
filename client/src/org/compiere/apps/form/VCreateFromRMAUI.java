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
package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.compiere.grid.ed.VLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author	Michael McKay
 * 				<li>release/380 - fix row selection event handling to fire single event per row selection
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */

public class VCreateFromRMAUI extends CreateFromRMA 
	implements FormPanel, ICreateFrom, VetoableChangeListener
{
	/**
	 * Standard Constructor
	 */
	public VCreateFromRMAUI() {
		v_CreateFromPanel = new VCreateFromPanel(this);
	}

	//	Yamel Senih FR [ 114 ], 2015-11-26
	//	Change to form
	private FormFrame	v_Container = null;
	/**	Main Panel for Create From	*/
	private VCreateFromPanel v_CreateFromPanel;
	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	
	//
	private JLabel bPartnerLabel = new JLabel();
	private VLookup bPartnerField;
	
	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		initBPartner(true);
		bPartnerField.addVetoableChangeListener(this);
		//	Load RMA Data
		loadRMA();
		
		return true;
	}   //  dynInit
    
	/**
	 *  Static Init.
	 *  <pre>
	 *  parameterPanel
	 *      parameterBankPanel
	 *      parameterStdPanel
	 *          bPartner/order/invoice/shopment/licator Label/Field
	 *  dataPane
	 *  southPanel
	 *      confirmPanel
	 *      statusBar
	 *  </pre>
	 *  @throws Exception
	 */
    private void jbInit() throws Exception
    {
    	bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));
		//	Add to Main Form
		v_Container.getContentPane().add(v_CreateFromPanel);
		//	
    	CPanel parameterPanel = v_CreateFromPanel.getParameterPanel();
    	parameterPanel.setLayout(new BorderLayout());
    	
    	CPanel parameterStdPanel = new CPanel(new GridBagLayout());
    	
    	parameterPanel.add(parameterStdPanel, BorderLayout.CENTER);

    	parameterStdPanel.add(bPartnerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if (bPartnerField != null)
    		parameterStdPanel.add(bPartnerField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    }   //  jbInit

	/*************************************************************************/
	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e)
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
		bPartnerField = new VLookup ("C_BPartner_ID", true, false, true, lookup);
		//
		int C_BPartner_ID = getC_BPartner_ID();
		bPartnerField.setValue(Integer.valueOf(C_BPartner_ID));
	}   //  initBPartner
	
	/**
	 * Load RMA
	 */
	protected void loadRMA()
	{
		loadTableOIS(getRMAData());
	}
	
	/**
	 *  Load Order/Invoice/Shipment data into Table
	 *  @param data data
	 */
	protected void loadTableOIS (Vector<? extends Vector> data)
	{
		//  Remove previous listeners
		v_CreateFromPanel.getMiniTable().removeMiniTableSelectionListener(v_CreateFromPanel);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, getOISColumnNames());
		v_CreateFromPanel.getMiniTable().setModel(model);
		// 
		configureMiniTable(v_CreateFromPanel.getMiniTable());
		v_CreateFromPanel.getMiniTable().addMiniTableSelectionListener(v_CreateFromPanel);
	}   //  loadOrder
	
	@Override
	public boolean info() {
		return false;
	}

	@Override
	public void init(int WindowNo, FormFrame frame) {
		p_WindowNo = WindowNo;
		v_Container = frame;
		ProcessInfo info = frame.getProcessInfo();
		try {
			//	Valid for launched from a window
			if(info != null) {
				//	Valid Table and Record
				validTable(info.getTable_ID(), 
						info.getRecord_ID());
			}
			//	Init
			if (!dynInit())
				return;
			jbInit();
		} catch(Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	}
	
	@Override
	public void dispose() {
		if (v_Container != null)
			v_Container.dispose();
		v_Container = null;
	}

	@Override
	public int getWindowNo() {
		return p_WindowNo;
	}
}

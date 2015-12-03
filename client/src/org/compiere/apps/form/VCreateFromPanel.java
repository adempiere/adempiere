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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import org.compiere.apps.ADialog;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.minigrid.MiniTable;
import org.compiere.minigrid.MiniTable.MiniTableSelectionListener;
import org.compiere.minigrid.MiniTable.RowSelectionEvent;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 * 
 *  @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 				<li>release/380 - fix row selection event handling to fire single event per row selection
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 *
 */
public class VCreateFromPanel extends CPanel implements ActionListener, MiniTableSelectionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -670830233093725938L;
	
	/**	Create From Parent	*/
	private ICreateFrom 	createFrom;
	/**	Parameter Panel		*/
	private CPanel 			parameterPanel 	= new CPanel();
	/**	Confirm Panel		*/
	private ConfirmPanel 	confirmPanel 	= new ConfirmPanel(true);
	/**	Status Bar			*/
	private StatusBar 		statusBar 		= new StatusBar();
	/**	Table				*/
	private MiniTable 		dataTable 		= new MiniTable();
	/**	Select All Constant	*/
	private static final String SELECT_ALL 	= "SelectAll";
	
	/**
	 * Standard Constructor
	 * @param createFrom
	 */
	public VCreateFromPanel(ICreateFrom createFrom) {
		this.createFrom = createFrom;
		//	Create UI
		jbInit();
		confirmPanel.addActionListener(this);
	    //	Set Status Bar
		statusBar.setStatusDB("");
		tableChanged(null);
    }
	
	/**
	 * Create UI
	 */
	protected void jbInit() {
		//	Set Layout
		setLayout(new BorderLayout());
		//	Add Parameter
		JScrollPane dataPane = new JScrollPane();

    	dataPane.getViewport().add(dataTable, null);
    	
    	AppsAction selectAllAction = new AppsAction (SELECT_ALL, KeyStroke.getKeyStroke(KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK), null);
    	CButton selectAllButton = (CButton)selectAllAction.getButton();
    	selectAllButton.setMargin(new Insets (0, 10, 0, 10));
    	selectAllButton.setDefaultCapable(true);
    	selectAllButton.addActionListener(this);
    	confirmPanel.addButton(selectAllButton);

    	CPanel southPanel = new CPanel();
    	BorderLayout southLayout = new BorderLayout();
    	southPanel.setLayout(southLayout);
    	southPanel.add(confirmPanel, BorderLayout.CENTER);
    	southPanel.add(statusBar, BorderLayout.SOUTH);
    	
    	dataTable.setMultiSelection(true);
    	//	Add to Main
		add(parameterPanel, BorderLayout.NORTH);
		add(dataPane, BorderLayout.CENTER);
    	add(southPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			try {
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						if (save(trxName)) {
							createFrom.dispose();
						}
					}
				});
			} catch (Exception ex) {
				ADialog.error(createFrom.getWindowNo(), this, "Error", ex.getLocalizedMessage());
			}
		}
		//  Cancel
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			createFrom.dispose();
		}
		// Select All
		// Trifon
		else if (e.getActionCommand().equals(SELECT_ALL)) {
			dataTable.selectAll();
			dataTable.matchCheckWithSelectedRows();
			info();
		}
	}
	
	/**
	 * Save Data
	 * @param trxName
	 * @return
	 */
	public boolean save(String trxName) {
		dataTable.stopEditor(true);
		//	
		TableModel model = dataTable.getModel();
		int rows = model.getRowCount();	
		if (rows == 0)
			return false;
		//	Default Return	
		return createFrom.save(dataTable, trxName);
	}
	
	/**
	 * Change in table
	 * @param e
	 */
	public void tableChanged (TableModelEvent e) {
		int type = -1;
		if (e != null)
		{
			type = e.getType();
			if (type != TableModelEvent.UPDATE)
				return;
		}
		info();
		dataTable.repaint();
	}
	
	/**
	 * Create Info
	 */
	public void info() {
		//	If the method is not used then refresh
		if(!createFrom.info()) {
			TableModel model = dataTable.getModel();
			int rows = model.getRowCount();
			int count = 0;
			for (int i = 0; i < rows; i++) {
				if (((Boolean)model.getValueAt(i, 0)).booleanValue())
					count++;
			}
			//	Set Status Bar
			setStatusLine(count, Msg.getMsg(Env.getCtx(), "Selected"));
		}
	}
	
	/**
	 * Set Values of Status Line
	 * @param selectedRowCount
	 * @param text
	 */
	public void setStatusLine(int selectedRowCount, String text) {
		StringBuffer sb = new StringBuffer(String.valueOf(selectedRowCount));
		if (text != null && text.trim().length() > 0) {
			sb.append(" - ").append(text);
		}
		statusBar.setStatusLine(sb.toString());
		//
		confirmPanel.getOKButton().setEnabled(selectedRowCount > 0);
	}
	
	/**
	 * Get Mini Table
	 * @return
	 */
	public MiniTable getMiniTable() {
		return dataTable;
	}
	
	/**
	 * Get Parameter Panel, you must add custom parameter here
	 * @return
	 */
	public CPanel getParameterPanel() {
		return parameterPanel;
	}
	
	/**
	 * Get Confirm Panel
	 * @return
	 */
	public ConfirmPanel getConfirmPanel() {
		return confirmPanel;
	}

	@Override
	public void rowSelected(RowSelectionEvent e) {
		info();
	}
}
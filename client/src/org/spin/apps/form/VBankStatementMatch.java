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
package org.spin.apps.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;

/**
 * Controller class for handle all method of Bank Statement Matcher
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1699 ] Add support view for Bank Statement
 * @see https://github.com/adempiere/adempiere/issues/1699
 */
public class VBankStatementMatch extends BankStatementMatchController 
	implements FormPanel, ActionListener, TableModelListener, VetoableChangeListener {

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public static CLogger log = CLogger.getCLogger(VBankStatementMatch.class);
	
	/**
	 * Init form
	 */
	public void init (int windowNo, FormFrame frame) {
		super.init(windowNo, frame.getProcessInfo());
		this.frame = frame;
		try {
			dynInit();
			jbInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
			frame.setMaximize(true);
		} catch(Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**	FormFrame			*/
	private FormFrame 	frame;
	private MiniTable currentPaymentTable = new MiniTable();
	private CPanel currentPaymentPanel = new CPanel();
	private JLabel currentPaymentLabel = new JLabel();
	private BorderLayout currentPaymentLayout = new BorderLayout();
	private JScrollPane currentPaymentScrollPane = new JScrollPane();
	
	private MiniTable importedPaymentTable = new MiniTable();
	private CPanel importedPaymentPanel = new CPanel();
	private JLabel importedPaymentLabel = new JLabel();
	private BorderLayout importedPaymentLayout = new BorderLayout();
	private JScrollPane importedPaymentScrollPane = new JScrollPane();
	
	private MiniTable matchedPaymentTable = new MiniTable();
	private CPanel matchedPaymentPanel = new CPanel();
	private JLabel matchedPaymentLabel = new JLabel();
	private BorderLayout matchedPaymentLayout = new BorderLayout();
	private JScrollPane matchedPaymentScrollPane = new JScrollPane();
	
	private CPanel  centerPanel = new CPanel();
	private BorderLayout centerLayout = new BorderLayout();
	
	private JSplitPane comparePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	private StatusBar statusBar = new StatusBar();
	
	private GridLayout allocationLayout = new GridLayout(0, 2);
	
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CPanel leftProcessPanel = new CPanel(new FlowLayout(FlowLayout.LEFT));
	private ConfirmPanel rightProcessPanel = new ConfirmPanel();
	private CPanel matchPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();

	private JLabel matchTypeLabel = new JLabel();
	private CComboBox matchMode = new CComboBox(m_matchMode);
	
	private JLabel bankAccountLabel = new JLabel();
	private VLookup bankAccountField;
	
	private CLabel amtFromLabel = new CLabel(Msg.translate(Env.getCtx(), "PayAmt"));
	private VNumber amtFromField = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel amtToLabel = new CLabel("-");
	private VNumber amtToField = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	
	private CLabel bpartnerLabel = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup bPartnerLookup;
	
	private CLabel dateFromLabel = new CLabel(Msg.translate(Env.getCtx(), "DateTrx"));
	private VDate dateFromField = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel dateToLabel = new CLabel("-");
	private VDate dateToField = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	//	Buttons
	private CButton searchButton;
	private CButton simulateMatchButton;
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception {
		CompiereColor.setBackground(mainPanel);
		searchButton = ConfirmPanel.createRefreshButton(true);
		simulateMatchButton = ConfirmPanel.createProcessButton(true);
		simulateMatchButton.setText(getButtonMatchMessage());
		simulateMatchButton.setToolTipText(getButtonMatchMessage());
		rightProcessPanel.setOKVisible(true);
		rightProcessPanel.setCancelVisible(true);
		
		matchedPaymentTable.setMultiSelection(true);
		
		mainPanel.setLayout(mainLayout);
		centerPanel.setLayout(centerLayout);
		bankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
    	
		importedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Imported"));
		currentPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Current"));
		matchedPaymentLabel.setText(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched"));
    	
    	dateFromLabel.setLabelFor(dateFromField);
    	dateFromField.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
    	dateToLabel.setLabelFor(dateToField);
    	dateToField.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
    	amtFromLabel.setLabelFor(amtFromField);
    	amtFromField.setToolTipText(Msg.translate(Env.getCtx(), "AmtFrom"));
    	amtToLabel.setLabelFor(amtToField);
    	amtToField.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));
    	
    	matchTypeLabel.setText(Msg.translate(Env.getCtx(), "MatchMode"));
    	//	
    	searchButton.addActionListener(this);
    	simulateMatchButton.addActionListener(this);
    	rightProcessPanel.addActionListener(this);
		//
		parameterPanel.setLayout(parameterLayout);
		importedPaymentPanel.setLayout(importedPaymentLayout);
		currentPaymentPanel.setLayout(currentPaymentLayout);
		matchedPaymentPanel.setLayout(matchedPaymentLayout);
		matchPanel.setLayout(allocationLayout);
		
		
		importedPaymentPanel.setPreferredSize(new Dimension(300, 600));
		currentPaymentPanel.setPreferredSize(new Dimension(300, 600));
		matchedPaymentPanel.setPreferredSize(new Dimension(400, 300));
		
		
		bankAccountField.setSize(bankAccountField.getWidth() + 10, bankAccountField.getHeight() + 10);
		
		
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		
		parameterPanel.add(bankAccountLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(bankAccountField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterPanel.add(bpartnerLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(bPartnerLookup, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));   	
    	parameterPanel.add(amtFromLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(amtFromField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterPanel.add(amtToLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(amtToField, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterPanel.add(dateFromLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(dateFromField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterPanel.add(dateToLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(dateToField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterPanel.add(matchTypeLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterPanel.add(matchMode, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterPanel.add(searchButton, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	
    	mainPanel.add(matchPanel, BorderLayout.SOUTH);
    	matchPanel.add(leftProcessPanel);
    	leftProcessPanel.add(simulateMatchButton);
    	matchPanel.add(rightProcessPanel);
    	//	
    	importedPaymentPanel.add(importedPaymentLabel, BorderLayout.NORTH);
		importedPaymentPanel.add(importedPaymentScrollPane, BorderLayout.CENTER);
		importedPaymentScrollPane.getViewport().add(importedPaymentTable, null);
		
		currentPaymentPanel.add(currentPaymentLabel, BorderLayout.NORTH);
		currentPaymentPanel.add(currentPaymentScrollPane, BorderLayout.CENTER);
		currentPaymentScrollPane.getViewport().add(currentPaymentTable, null);
		
		matchedPaymentPanel.add(matchedPaymentLabel, BorderLayout.NORTH);
		matchedPaymentPanel.add(matchedPaymentScrollPane, BorderLayout.CENTER);
		matchedPaymentScrollPane.getViewport().add(matchedPaymentTable, null);
		
		comparePanel.setLeftComponent(importedPaymentPanel);
		comparePanel.setRightComponent(currentPaymentPanel);
		comparePanel.add(importedPaymentPanel, JSplitPane.LEFT);
		comparePanel.add(currentPaymentPanel, JSplitPane.RIGHT);
		comparePanel.setBorder(BorderFactory.createEtchedBorder());
		comparePanel.setContinuousLayout(true);
		comparePanel.setPreferredSize(new Dimension(2048, 1536));
		
		centerPanel.add(comparePanel,BorderLayout.CENTER);
		centerPanel.add(matchedPaymentPanel,BorderLayout.SOUTH);
		//
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//	
		chageLayout();
		//	
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	comparePanel.setDividerLocation(frame.getContainer().getPreferredSize().height /2);
            }
        });
	}   //  jbInit

	/**
	 * 	Dispose
	 */
	public void dispose() {
		if (frame != null)
			frame.dispose();
		frame = null;
	}	//	dispose

	/**
	 *  Dynamic Init (prepare dynamic fields)
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception {
		
		int columnId = 4917;        //  C_BankStatement.C_BankAccount_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, columnId, DisplayType.TableDir);
		
		bankAccountField = new VLookup("C_BankAccount_ID", true,false, true, lookup);
		bankAccountField.addActionListener(this);
		bankAccountField.setSize(bankAccountField.getWidth() + 10, bankAccountField.getHeight() + 10);
		if(getBankAccountId() > 0) {
			bankAccountField.setValue(getBankAccountId());
		}
		
		bPartnerLookup = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, 3499, DisplayType.Search));
		bpartnerLabel.setLabelFor(bPartnerLookup);
		//	
		matchMode.setMandatory(true);
	}   //  dynInit
	
	/**************************************************************************
	 *  Action Listener.
	 *  - MultiCurrency
	 *  - Allocate
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		log.config("");
		if(e.getSource().equals(searchButton)) {
			log.config("search");
			mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			refresh();
			mainPanel.setCursor(Cursor.getDefaultCursor());
		} else if(e.getSource().equals(simulateMatchButton)) {
			mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if(!isHasSelection()) {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.Matched") + ": " + actionMatchUnMatch());
			} else {
				statusBar.setStatusLine(Msg.translate(Env.getCtx(), "BankStatementMatch.UnMatched") + ": " + actionUnMatchSelected(matchedPaymentTable));
			}
			loadMatchedPaymentsFromMatch();
			changeMessageButton();
			mainPanel.setCursor(Cursor.getDefaultCursor());
		} else if(e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			if(!isAvailableForSave()) {
				return;
			}
			if(ADialog.ask(getWindowNo(), frame.getContainer(), "SaveChanges?", getAskMatchMessage())) {
				mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				saveData();
				mainPanel.setCursor(Cursor.getDefaultCursor());
			}
		} else if(e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		}
	}   //  actionPerformed
	
	/**
	 * Refresh Data
	 */
	private void refresh() {
		clear();
		setIsAvailableForSave(false);
		getParameters();
		String message = validateParameters();
		if(Util.isEmpty(message)) {
			loadPayments();
			loadImportedPayments();
			loadMatchedPayments();
		} else {
			ADialog.error(getWindowNo(), frame.getContainer(), "ValidationError", Msg.parseTranslation(Env.getCtx(), message));
		}
	}
	
	/**
	 * Get parameters for search
	 */
	private void getParameters() {
		setBankAccountId((int) (bankAccountField.getValue() != null? bankAccountField.getValue(): 0));
		setAmtFrom((amtFromField.getValue() != null? (BigDecimal) amtFromField.getValue(): null));
		setAmtTo((amtToField.getValue() != null? (BigDecimal) amtToField.getValue(): null));
		setDateFrom((dateFromField.getValue() != null? (Timestamp) dateFromField.getValue(): null));
		setDateTo((dateToField.getValue() != null? (Timestamp) dateToField.getValue(): null));
		//	Get for matched
		setMatchMode(matchMode.getSelectedIndex());
		//	
		chageLayout();
		//	
		setHasSelection(false);
		statusBar.setStatusLine("");
	}
	
	/**
	 * Apply changes for view
	 */
	private void chageLayout() {
		//	Disable account
		if(isFromStatement()) {
			bankAccountField.setEnabled(false);
			bankAccountField.setReadWrite(false);
			if(dateToField.getValue() == null) {
				dateToField.setValue(getStatementDate());
			}
		} else {
			bankAccountField.setEnabled(true);
			bankAccountField.setReadWrite(true);
		}
		simulateMatchButton.setText(getButtonMatchMessage());
		simulateMatchButton.setToolTipText(getButtonMatchMessage());
	}
	
	/**
	 * Load Payments from DB
	 */
	private void loadPayments() {
		fillPayments(getPaymentData());
	}
	
	/**
	 * Fill Payments
	 * @param data
	 */
	private void fillPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
		currentPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, getCurrentPaymentColumnNames());
		model.addTableModelListener(this);
		currentPaymentTable.setModel(model);
		// 
		configureCurrentPaymentTable(currentPaymentTable);
	}
	
	/**
	 * Load Imported Payments from DB
	 */
	private void loadImportedPayments() {
		fillImportedPayments(getImportedPaymentData());
	}
	
	/**
	 * Fill Imported Payments
	 * @param data
	 */
	private void fillImportedPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
		importedPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, getImportedPaymentColumnNames());
		model.addTableModelListener(this);
		importedPaymentTable.setModel(model);
		// 
		configureImportedPaymentTable(importedPaymentTable);
	}
	
	/**
	 * Load Matched Payments from DB
	 */
	private void loadMatchedPayments() {
		fillMatchedPayments(new Vector<Vector<Object>>());
	}
	
	/**
	 * Load Matched payments from a match process
	 */
	private void loadMatchedPaymentsFromMatch() {
		Vector<Vector<Object>> matchedPayments = getMatchedPayments();
		setIsAvailableForSave(matchedPayments != null && !matchedPayments.isEmpty());
		fillMatchedPayments(matchedPayments);
	}
	
	/**
	 * Fill Matched Payments
	 * @param data
	 */
	private void fillMatchedPayments(Vector<Vector<Object>> data) {
		//  Remove previous listeners
		matchedPaymentTable.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, getMatchedPaymentColumnNames());
		model.addTableModelListener(this);
		matchedPaymentTable.setModel(model);
		// 
		configureMatchedPaymentTable(matchedPaymentTable);
		//	
	}
	
	/**
	 *  Table Model Listener.
	 *  - Recalculate Totals
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e) {
		if (e.getColumn() != 0)
			return;
		log.config("Row=" + e.getFirstRow() + "-" + e.getLastRow() + ", Col=" + e.getColumn()
			+ ", Type=" + e.getType());
		boolean isUpdate = (e.getType() == TableModelEvent.UPDATE);
		int row = e.getFirstRow();
		//  Not a table update
		if (!isUpdate) {
			return;
		}
		//	
		boolean isMatched = false;
		if(e.getSource().equals(currentPaymentTable.getModel())) {
			int matchedRow = currentPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn paymentIdColumn = (IDColumn) currentPaymentTable.getValueAt(matchedRow, 0);
				isMatched = paymentApplyForMatch(paymentIdColumn.getRecord_ID());
			}
		} else if(e.getSource().equals(importedPaymentTable.getModel())) {
			int matchedRow = importedPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn importedPaymentIdColumn = (IDColumn) importedPaymentTable.getValueAt(matchedRow, 0);
				isMatched = importedPaymentApplyForMatch(importedPaymentIdColumn.getRecord_ID());
			}
		} else if(e.getSource().equals(matchedPaymentTable.getModel())) {
			int matchedRow = matchedPaymentTable.getSelectedRow();
			if(matchedRow != row) {
				return;
			}
			if(matchedRow >= 0) {
				IDColumn matchedPaymentIdColumn = (IDColumn) matchedPaymentTable.getValueAt(matchedRow, 0);
				selectFromMatch(matchedPaymentIdColumn.getRecord_ID());
				changeMessageButton();
			}
		}
		//	Validate screen
		if(isMatched) {
			loadMatchedPaymentsFromMatch();
		}
	}   //  tableChanged

	/**
	 * Select source from match
	 * @param paymentId
	 */
	private void selectFromMatch(int paymentId) {
		for (int row = 0; row < currentPaymentTable.getRowCount(); row++) {
			IDColumn record = (IDColumn) currentPaymentTable.getValueAt(row, 0);
			if(record != null
					&& record.getRecord_ID() == paymentId) {
				currentPaymentTable.setRowSelectionInterval(row, row);
			}
		}
		int importedPaymentId = getImportedPaymentId(paymentId);
		if(importedPaymentId > 0) {
			for (int row = 0; row < importedPaymentTable.getRowCount(); row++) {
				IDColumn record = (IDColumn) importedPaymentTable.getValueAt(row, 0);
				if(record != null
						&& record.getRecord_ID() == importedPaymentId) {
					importedPaymentTable.setRowSelectionInterval(row, row);
				}
			}
		}
	}
	
	/**
	 * Change Message from selection
	 */
	private void changeMessageButton() {
		boolean deleteAllocation = false;
		for (int row = 0; row < matchedPaymentTable.getRowCount(); row++) {
			IDColumn record = (IDColumn) matchedPaymentTable.getValueAt(row, 0);
			if(record != null
					&& record.isSelected()) {
				deleteAllocation = true;
				break;
			}
		}
		setHasSelection(deleteAllocation);
		//	change button
		simulateMatchButton.setText(getButtonMatchMessage(deleteAllocation));
		simulateMatchButton.setToolTipText(getButtonMatchMessage(deleteAllocation));
	}
	
	/**
	 *  Vetoable Change Listener.
	 *  - Business Partner
	 *  - Currency
	 * 	- Date
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e) {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		
		if (value == null)
			return;
	}   //  vetoableChange
	
	/**************************************************************************
	 *  Save Data
	 */
	public void saveData() {
		if(!isAvailableForSave()) {
			return;
		}
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					statusBar.setStatusLine(saveData(getWindowNo(), trxName));
				}
			});
			//	If Ok
			refresh();
		} catch (Exception e) {
			ADialog.error(getWindowNo(), mainPanel, "Error", e.getLocalizedMessage());
		} finally {
			if(isFromStatement()) {
				dispose();
			}
		}
	}   //  saveData
}
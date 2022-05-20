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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 * 
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/407">
 * 		@see FR [ 407 ] Enhance visualization of allocation payment window</a>
 */
public class VAllocation extends Allocation
	implements FormPanel, ActionListener, TableModelListener, VetoableChangeListener
{
	private CPanel panel = new CPanel();

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame) {
		setWindowNo(WindowNo);
		this.frame = frame;
		try {
			super.dynInit();
			setFromPO(frame.getProcessInfo());
			dynInit();
			jbInit();
			if(isFromParent()) {
				loadBPartner();
				setDefaultRecord(paymentTable, invoiceTable);
			} else {
				calculate();
			}
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		} catch(Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**	FormFrame			*/
	private FormFrame 	frame;

	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CPanel allocationPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private JLabel bpartnerLabel = new JLabel();
	private VLookup bpartnerSearch = null;
	private MiniTable invoiceTable = new MiniTable();
	private MiniTable paymentTable = new MiniTable();
	private JSplitPane infoPanel = new JSplitPane();
	private CPanel paymentPanel = new CPanel();
	private CPanel invoicePanel = new CPanel();
	private JLabel paymentLabel = new JLabel();
	private JLabel invoiceLabel = new JLabel();
	private BorderLayout paymentLayout = new BorderLayout();
	private BorderLayout invoiceLayout = new BorderLayout();
	private JLabel paymentInfo = new JLabel();
	private JLabel invoiceInfo = new JLabel();
	private JScrollPane paymentScrollPane = new JScrollPane();
	private JScrollPane invoiceScrollPane = new JScrollPane();
	private GridBagLayout allocationLayout = new GridBagLayout();
	private JLabel differenceLabel = new JLabel();
	private CTextField differenceField = new CTextField();
	private JLabel currencyLabel = new JLabel();
	private VLookup currencyPick = null;
	private JCheckBox multiCurrency = new JCheckBox();
	private JLabel chargeLabel = new JLabel();
    private VLookup chargePick = null;
    private JLabel orgWriteLabel = new JLabel();
    private VLookup orgWritePick = null;
    private JLabel descriptionLabel = new JLabel();
	private CTextField descriptionField = new CTextField();
	private JLabel allocCurrencyLabel = new JLabel();
	private StatusBar statusBar = new StatusBar();
	private JLabel dateLabel = new JLabel();
	private VDate dateField = new VDate();
	private JCheckBox autoWriteOff = new JCheckBox();
	private JLabel organizationLabel = new JLabel();
	private VLookup organizationPick = null;
	private JLabel aparLabel = new JLabel();
	private VLookup aparPick = null;
	/**	Confirm Panel		*/
	private ConfirmPanel confirmPanel;
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(panel);
		//	Add
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		//
		paymentTable.setMultiSelection(true);  // Should be performed before the class is set.
		invoiceTable.setMultiSelection(true);  // Should be performed before the class is set.
		invoiceTable.setSurrendersFocusOnKeystroke(true);
		//
		mainPanel.setLayout(mainLayout);
		dateLabel.setText(Msg.getMsg(Env.getCtx(), "Date"));
		dateLabel.setToolTipText(Msg.getMsg(Env.getCtx(), "AllocDate", false));
		autoWriteOff.setSelected(false);
		autoWriteOff.setText(Msg.getMsg(Env.getCtx(), "AutoWriteOff", true));
		autoWriteOff.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoWriteOff", false));
		//
		parameterPanel.setLayout(parameterLayout);
		allocationPanel.setLayout(allocationLayout);
		bpartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		paymentLabel.setRequestFocusEnabled(false);
		paymentLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Payment_ID"));
		invoiceLabel.setRequestFocusEnabled(false);
		invoiceLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Invoice_ID"));
		paymentPanel.setLayout(paymentLayout);
		invoicePanel.setLayout(invoiceLayout);
		invoiceInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		invoiceInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		invoiceInfo.setText(".");
		paymentInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		paymentInfo.setText(".");
		chargeLabel.setText(Msg.translate(Env.getCtx(), "C_Charge_ID"));
	    chargeLabel.setToolTipText(Msg.getMsg(Env.getCtx(), "ChargeDifference", false));
	    orgWriteLabel.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
	    descriptionLabel.setText(Msg.getMsg(Env.getCtx(), "Description"));
		descriptionField.setColumns(20);
		differenceLabel.setText(Msg.getMsg(Env.getCtx(), "Difference"));
		differenceField.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		differenceField.setEditable(false);
		differenceField.setText("0");
		differenceField.setColumns(8);
		differenceField.setHorizontalAlignment(SwingConstants.RIGHT);
		currencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		multiCurrency.setText(Msg.getMsg(Env.getCtx(), "MultiCurrency"));
		multiCurrency.setSelected(isDefaultMultiCurrency());
		multiCurrency.addActionListener(this);
		allocCurrencyLabel.setText(".");
		invoiceScrollPane.setPreferredSize(new Dimension(200, 200));
		paymentScrollPane.setPreferredSize(new Dimension(200, 200));
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		
		//org filter
		organizationLabel.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		//	APAR
		aparLabel.setText(Msg.translate(Env.getCtx(), "APAR"));
		//	
		parameterPanel.add(organizationLabel, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		parameterPanel.add(organizationPick, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		parameterPanel.add(aparLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		parameterPanel.add(aparPick, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		
		parameterPanel.add(bpartnerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(bpartnerSearch, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(dateLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(dateField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(currencyLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(currencyPick, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(multiCurrency, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(autoWriteOff, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		mainPanel.add(allocationPanel, BorderLayout.SOUTH);
		allocationPanel.add(differenceLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		allocationPanel.add(differenceField, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		allocationPanel.add(chargeLabel, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0)); 
		allocationPanel.add(chargePick, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		allocationPanel.add(orgWriteLabel, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0)); 
		allocationPanel.add(orgWritePick, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		allocationPanel.add(descriptionLabel, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		allocationPanel.add(descriptionField, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		allocationPanel.add(allocCurrencyLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		allocationPanel.add(confirmPanel, new GridBagConstraints(10, 0, 1, 1, 1, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));     
		paymentPanel.add(paymentLabel, BorderLayout.NORTH);
		paymentPanel.add(paymentInfo, BorderLayout.SOUTH);
		paymentPanel.add(paymentScrollPane, BorderLayout.CENTER);
		paymentScrollPane.getViewport().add(paymentTable, null);
		invoicePanel.add(invoiceLabel, BorderLayout.NORTH);
		invoicePanel.add(invoiceInfo, BorderLayout.SOUTH);
		invoicePanel.add(invoiceScrollPane, BorderLayout.CENTER);
		invoiceScrollPane.getViewport().add(invoiceTable, null);
		//
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		infoPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		infoPanel.setBorder(BorderFactory.createEtchedBorder());
		infoPanel.setTopComponent(paymentPanel);
		infoPanel.setBottomComponent(invoicePanel);
		infoPanel.add(paymentPanel, JSplitPane.TOP);
		infoPanel.add(invoicePanel, JSplitPane.BOTTOM);
		infoPanel.setContinuousLayout(true);
		infoPanel.setPreferredSize(new Dimension(800,250));
		infoPanel.setDividerLocation(110);
	}   //  jbInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (frame != null)
			frame.dispose();
		frame = null;
	}	//	dispose

	/**
	 *  Dynamic Init (prepare dynamic fields)
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception
	{
		
		//  Currency
		int AD_Column_ID = 3505;    //  C_Invoice.C_Currency_ID
		MLookup lookupCur = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		currencyPick = new VLookup("C_Currency_ID", true, false, true, lookupCur);
		currencyPick.setValue(currencyId);
		currencyPick.addVetoableChangeListener(this);

		// Organization filter selection
		AD_Column_ID = 839; //C_Period.AD_Org_ID (needed to allow org 0)
		MLookup lookupOrg = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		organizationPick = new VLookup("AD_Org_ID", true, false, true, lookupOrg);
		organizationPick.setValue(orgId);
		organizationPick.addVetoableChangeListener(this);

		//  BPartner
		AD_Column_ID = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookupBP = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.Search);
		bpartnerSearch = new VLookup("C_BPartner_ID", true, false, true, lookupBP);
		bpartnerSearch.addVetoableChangeListener(this);
		if(bPartnerId > 0) {
			bpartnerSearch.setValue(bPartnerId);
		}
		if(isFromParent()) {
			bpartnerSearch.setReadWrite(false);
		}
		//  Translation
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "AllocateStatus"));
		statusBar.setStatusDB("");

		//  Date set to Login Date
		dateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		dateField.addVetoableChangeListener(this);
		

		AD_Column_ID = 61804; // C_AllocationLine.C_Charge_ID

		MLookup lookupCharge = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		chargePick = new VLookup("C_Charge_ID", false, false, true, lookupCharge);
		chargePick.setValue(Integer.valueOf(chargeId));
		chargePick.addVetoableChangeListener(this);
		
		// Organization filter selection
		AD_Column_ID = 3863; //C_Period.AD_Org_ID
		MLookup lookupOrgWrite = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.TableDir);
		orgWritePick = new VLookup("AD_OrgTrx_ID", false, false, true, lookupOrgWrite);
		orgWritePick.addVetoableChangeListener(this);
		
		//	APAR
		AD_Column_ID = 14082;    //  T_InvoiceGL.APAR
		MLookup lookupAPAR = MLookupFactory.get (Env.getCtx(), getWindowNo(), 0, AD_Column_ID, DisplayType.List);
		aparPick = new VLookup("APAR", true, false, true, lookupAPAR);
		aparPick.setValue(APAR_A);
		aparPick.addVetoableChangeListener(this);
	}   //  dynInit
	
	/**************************************************************************
	 *  Action Listener.
	 *  - MultiCurrency
	 *  - Allocate
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		log.config("");
		if (e.getSource().equals(multiCurrency))
			loadBPartner();
		//	Allocate
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			confirmPanel.getOKButton().setEnabled(false);
			description = descriptionField.getText();
			saveData();
		}
	}   //  actionPerformed

	/**
	 *  Table Model Listener.
	 *  - Recalculate Totals
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e)
	{
		boolean isUpdate = (e.getType() == TableModelEvent.UPDATE);
		//  Not a table update
		if (!isUpdate)
		{
			calculate();
			return;
		}
		
		int row = e.getFirstRow();
		int col = e.getColumn();
		boolean isInvoice = (e.getSource().equals(invoiceTable.getModel()));
		boolean isAutoWriteOff = autoWriteOff.isSelected();
		
		String msg = writeOff(row, col, isInvoice, paymentTable, invoiceTable, isAutoWriteOff);
		if(msg != null && msg.length() > 0)
			ADialog.warn(getWindowNo(), panel, "AllocationWriteOffWarn");
		
		calculate();
	}   //  tableChanged

	/**
	 *  Vetoable Change Listener.
	 *  - Business Partner
	 *  - Currency
	 * 	- Date
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e)
	{
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		
		if (value == null)
			return;
		
		// Organization
		if (name.equals("AD_Org_ID")) {
			setAD_Org_ID(((Integer) value).intValue());
			loadBPartner();
		}
		
		else if(name.equals("C_Charge_ID")) {
			chargeId = ((Integer) value).intValue();
			setAllocateButton();
		}

		else if(name.equals("AD_OrgTrx_ID")) {
			orgWriteId = ((Integer) value).intValue();
		}
		
		//  BPartner
		if (name.equals("C_BPartner_ID"))
		{
			bpartnerSearch.setValue(value);
			bPartnerId = ((Integer)value).intValue();
			loadBPartner();
		}
		//	Currency
		else if (name.equals("C_Currency_ID"))
		{
			currencyId = ((Integer)value).intValue();
			loadBPartner();
		}
		//	Date for Multi-Currency
		else if (name.equals("Date") && multiCurrency.isSelected())
			loadBPartner();
		else if(name.equals("APAR")) {
			if(value.toString().length() == 0)
				apar = APAR_A;
			else 
				apar = value.toString();
			loadBPartner();
		}
	}   //  vetoableChange
	
	public void loadBPartner()
	{
		checkBPartner();
		
		Vector<Vector<Object>> data = getPaymentData(multiCurrency.isSelected(), dateField.getValue(), paymentTable);
		Vector<String> columnNames = getPaymentColumnNames(multiCurrency.isSelected());
		
		//  Remove previous listeners
		paymentTable.getModel().removeTableModelListener(this);
		
		//  Set Model
		DefaultTableModel modelP = new DefaultTableModel(data, columnNames);
		modelP.addTableModelListener(this);
		paymentTable.setModel(modelP);
		setPaymentColumnClass(paymentTable, multiCurrency.isSelected());
		//

		data = getInvoiceData(multiCurrency.isSelected(), dateField.getValue(), invoiceTable);
		columnNames = getInvoiceColumnNames(multiCurrency.isSelected());
		
		//  Remove previous listeners
		invoiceTable.getModel().removeTableModelListener(this);
		
		//  Set Model
		DefaultTableModel modelI = new DefaultTableModel(data, columnNames);
		modelI.addTableModelListener(this);
		invoiceTable.setModel(modelI);
		setInvoiceColumnClass(invoiceTable, multiCurrency.isSelected());
		//
		
		changeIndexForTables(multiCurrency.isSelected());
		
		//  Calculate Totals
		calculate();
	}
	
	
	public void calculate()
	{
		allocDate = null;
		
		paymentInfo.setText(calculatePayment(paymentTable, multiCurrency.isSelected()));
		invoiceInfo.setText(calculateInvoice(invoiceTable, multiCurrency.isSelected()));
		
		//	Set AllocationDate
		if (allocDate != null)
			dateField.setValue(allocDate);
		//  Set Allocation Currency
		allocCurrencyLabel.setText(currencyPick.getDisplay());
		//  Difference
		totalDiff = totalPay.subtract(totalInv);
		differenceField.setText(format.format(totalDiff));
				
	}
	
	private void setAllocateButton() {

		if (totalDiff.compareTo(new BigDecimal(0.0)) == 0 ^ chargeId > 0)

		{
			confirmPanel.getOKButton().setEnabled(true);
		}
		else
		{
			confirmPanel.getOKButton().setEnabled(false);
		}

		if (totalDiff.compareTo(new BigDecimal(0.0)) == 0)
		{
			chargePick.setValue(null);
			chargeId = 0;
		}
	}
	
	/**************************************************************************
	 *  Save Data
	 */
	public void saveData() {
		setAD_Org_ID();
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					statusBar.setStatusLine(saveData(getWindowNo(), dateField.getValue(), paymentTable, invoiceTable, trxName));
				}
			});
			//	If Ok
			loadBPartner();
		} catch (Exception e) {
			ADialog.error(getWindowNo(), panel, "Error", e.getLocalizedMessage());
		} finally {
			confirmPanel.getOKButton().setEnabled(true);
		}
	}   //  saveData
}
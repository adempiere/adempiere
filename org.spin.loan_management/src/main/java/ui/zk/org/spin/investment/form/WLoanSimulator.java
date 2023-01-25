/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.spin.investment.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.spin.investment.loan.util.AmortizationValue;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.model.MFMRate;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1585 ] Loan Simulator
 *		@see https://github.com/adempiere/adempiere/issues/1585
 */
public class WLoanSimulator extends org.spin.investment.form.LoanSimulator
	implements IFormController, EventListener, ValueChangeListener {
	
	private CustomForm form = new CustomForm();

	//
	private Panel mainPanel = new Panel();
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Label businessPartnerLabel = new Label();
	private WSearchEditor businessPartnerField;
	private Label financialProductLabel = new Label();
	private WTableDirEditor financialProductField;
	private Label startDateLabel = new Label();
	private WDateEditor startDateField;
	private Label endDateLabel = new Label();
	private WDateEditor endDateField;
	private Label currencyLabel = new Label();
	private WTableDirEditor currencyField;
	private Label paymentFrequencyLabel = new Label();
	private WTableDirEditor paymentFrequencyField;
	private Label feesQtyLabel = new Label();
	private WNumberEditor feesQtyField;
	private Label payDateLabel = new Label();
	private WDateEditor payDateField;
	private Label interestAmtLabel = new Label();
	private WNumberEditor interestAmtField;
	private Label taxAmtLabel = new Label();
	private WNumberEditor taxAmtField;
	private Label grandTotalLabel = new Label();
	private WNumberEditor grandTotalField;
	private Label capitalAmtLabel = new Label();
	private WNumberEditor capitalAmtField;
	private Label financialRateLabel = new Label();
	private Label userLabel = new Label();
	private WTableDirEditor userField;
	private WNumberEditor financialRateField;
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Panel southPanel = new Panel();
	private Button calculateButton = null;
	private ConfirmPanel confirmPanel = new ConfirmPanel(true, false, false, false, false, false, false);
	private StatusBarPanel statusBar = new StatusBarPanel();
	private WListbox miniTable = ListboxFactory.newDataTable();


	/**
	 *	Initialize Panel
	 */
	public WLoanSimulator() {
		log.info("");
		try {
			dynParameter();
			zkInit();
			dynInit();			
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	void zkInit() throws Exception
	{
		form.appendChild(mainPanel);
		mainPanel.setStyle("width: 99%; height: 100%; border: none; padding: 0; margin: 0");
		mainPanel.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		parameterPanel.appendChild(parameterLayout);
		//	
		businessPartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		financialProductLabel.setText(Msg.translate(Env.getCtx(), "FM_Product_ID"));
		capitalAmtLabel.setText(Msg.translate(Env.getCtx(), "CapitalAmt"));
		startDateLabel.setText(Msg.translate(Env.getCtx(), "StartDate"));
		feesQtyLabel.setText(Msg.translate(Env.getCtx(), "FeesQty"));
		financialRateLabel.setText(Msg.translate(Env.getCtx(), "FM_Rate_ID"));
		payDateLabel.setText(Msg.translate(Env.getCtx(), "PayDate"));
		interestAmtLabel.setText(Msg.translate(Env.getCtx(), "InterestAmt"));
		taxAmtLabel.setText(Msg.translate(Env.getCtx(), "TaxAmt"));
		currencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		userLabel.setText(Msg.translate(Env.getCtx(), "AD_User_ID"));
		grandTotalLabel.setText(Msg.translate(Env.getCtx(), "GrandTotal"));
		paymentFrequencyLabel.setText(Msg.translate(Env.getCtx(), "PaymentFrequency"));
		endDateLabel.setText(Msg.translate(Env.getCtx(), "EndDate"));
		//
		North north = new North();
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		
		Rows rows = parameterLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(businessPartnerLabel.rightAlign());
		row.appendChild(businessPartnerField.getComponent());
		row.appendChild(financialProductLabel.rightAlign());
		row.appendChild(financialProductField.getComponent());
		row = rows.newRow();
		row.appendChild(userLabel.rightAlign());
		row.appendChild(userField.getComponent());
		row = rows.newRow();
		row.appendChild(capitalAmtLabel.rightAlign());
		row.appendChild(capitalAmtField.getComponent());
		row.appendChild(paymentFrequencyLabel.rightAlign());
		row.appendChild(paymentFrequencyField.getComponent());
		row = rows.newRow();
		row.appendChild(startDateLabel.rightAlign());
		row.appendChild(startDateField.getComponent());
		row.appendChild(endDateLabel.rightAlign());
		row.appendChild(endDateField.getComponent());
		row = rows.newRow();
		row.appendChild(feesQtyLabel.rightAlign());
		row.appendChild(feesQtyField.getComponent());
		row.appendChild(payDateLabel.rightAlign());
		row.appendChild(payDateField.getComponent());
		row = rows.newRow();
		row.appendChild(currencyLabel.rightAlign());
		row.appendChild(currencyField.getComponent());
		row.appendChild(financialRateLabel.rightAlign());
		row.appendChild(financialRateField.getComponent());
		row = rows.newRow();
		row.appendChild(interestAmtLabel.rightAlign());
		row.appendChild(interestAmtField.getComponent());
		row.appendChild(taxAmtLabel.rightAlign());
		row.appendChild(taxAmtField.getComponent());
		row = rows.newRow();
		row.appendChild(grandTotalLabel.rightAlign());
		row.appendChild(grandTotalField.getComponent());
		//	
		row.appendChild(new Separator());
		row.appendChild(calculateButton);
		
		southPanel.appendChild(confirmPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(southPanel);
		//	
		LayoutUtils.addSclass("status-border", statusBar);
	}   //  jbInit

	/**
	 *  Initialize Parameter fields
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void dynParameter() throws Exception {
		Properties ctx = Env.getCtx();
		//  Business Partner
		MLookup businessPartnerLookup = MLookupFactory.get (ctx, windowNo, 0, 87269, DisplayType.Search);
		businessPartnerField = new WSearchEditor("C_BPartner_ID", true, false, true, businessPartnerLookup);
		businessPartnerField.addValueChangeListener(this);
		//	Financial Product
		MLookup financialProductLookup = MLookupFactory.get (ctx, windowNo, 0, 87268, DisplayType.TableDir);
		financialProductField = new WTableDirEditor("FM_Product_ID", true, false, true, financialProductLookup);
		financialProductField.addValueChangeListener(this);
		//	User/Contact
		MLookup userLookup = MLookupFactory.get (ctx, windowNo, 0, 88319, DisplayType.TableDir);
		userField = new WTableDirEditor("AD_User_ID", true, false, true, userLookup);
		userField.addValueChangeListener(this);
		//	Capital Amount
		capitalAmtField = new WNumberEditor();
		capitalAmtField.setMandatory(true);
		//	Loan Frequency
		MLookup paymentFrequencyLookup = MLookupFactory.get (Env.getCtx(), windowNo, 0, 87478, DisplayType.List);
		paymentFrequencyField = new WTableDirEditor("PaymentFrequency", true, false, true, paymentFrequencyLookup);
		paymentFrequencyField.addValueChangeListener(this);
		//	Fees Amount
		feesQtyField = new WNumberEditor("FeesQty", true, false, true, DisplayType.Integer, "");
		feesQtyField.setMandatory(true);
		feesQtyField.addValueChangeListener(this);
		//	Start Date
		startDateField = new WDateEditor("StartDate", true, false, true, "");
		startDateField.setMandatory(true);
		startDateField.setValue(new Timestamp(System.currentTimeMillis()));
		startDateField.addValueChangeListener(this);
		//	End Date
		endDateField = new WDateEditor("EndDate", true, false, true, "");
		endDateField.setMandatory(true);
		endDateField.setValue(new Timestamp(System.currentTimeMillis()));
		endDateField.addValueChangeListener(this);
		//	Payment Date
		payDateField = new WDateEditor("PayDate", true, false, true, "");
		payDateField.setValue(new Timestamp(System.currentTimeMillis()));
		payDateField.setMandatory(true);
		//	Fee Amount
		financialRateField = new WNumberEditor();
		financialRateField.setReadWrite(false);
		//	Currency
		MLookup currencyLookup = MLookupFactory.get (ctx, windowNo, 0, 87162, DisplayType.TableDir);
		currencyField = new WTableDirEditor("C_Currency_ID", true, true, true, currencyLookup);
		//	Interest Amount
		interestAmtField = new WNumberEditor();
		interestAmtField.setReadWrite(false);
		//	Tax Amount
		taxAmtField = new WNumberEditor();
		taxAmtField.setReadWrite(false);
		//	Tax Amount
		grandTotalField = new WNumberEditor();
		grandTotalField.setReadWrite(false);
		calculateButton = confirmPanel.createButton(ConfirmPanel.A_PROCESS);
		calculateButton.addEventListener(Events.ON_CLICK, this);
		//	
		confirmPanel.addActionListener(this);
		statusBar.setStatusLine("");
	}   //  dynParameter

	/**
	 *  Dynamic Layout (Grid).
	 * 	Based on AD_Window: Material Transactions
	 */
	private void dynInit() {
		super.dynInit(statusBar);
		//
		Center center = new Center();
		mainLayout.appendChild(center);
		center.setFlex(true);
		center.appendChild(miniTable);
		miniTable.setVflex(true);
		miniTable.setFixedLayout(true);
		miniTable.setWidth("99%");
		miniTable.setHeight("99%");
		configureMiniTable();
	}   //  dynInit
	
	/**
	 * Configure Table
	 */
	private void configureMiniTable() {
		ListModelTable model = new ListModelTable();
		miniTable.setModel(model);
		setColumnClass(miniTable);
	}

	/**
	 * 	Dispose
	 */
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent (Event e) {
		if(e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if(e.getTarget().getId().equals(ConfirmPanel.A_PROCESS)) {
			simulate();
		} else if(e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			if (FDialog.ask(windowNo, parameterPanel, "GenerateLoan", 
					Msg.parseTranslation(Env.getCtx(), 
							"@GenerateLoanAsk@"))) {
				saveData();
			}
		}
	}   //  actionPerformed

	
	/**************************************************************************
	 *  Property Listener
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e) {
		if(e == null) {
			return;
		}
		if (e.getPropertyName().equals("C_BPartner_ID")) {
			Env.setContext(Env.getCtx(), windowNo, "C_BPartner_ID", ((Integer)e.getNewValue()).intValue());
			businessPartnerId = ((Integer)e.getNewValue()).intValue();
			clearFieldValues(false);
			financialProductField.actionRefresh();
			userField.actionRefresh();
		} else if(e.getPropertyName().equals("FM_Product_ID")) {
			financialProductId = ((Integer)e.getNewValue()).intValue();
			reloadFinancialProductInfo();
		} else if(e.getPropertyName().equals("AD_User_ID")) {
			userId = ((Integer)e.getNewValue()).intValue();
		} else if(e.getPropertyName().equals("StartDate")) {
			payDateField.setValue((Timestamp) e.getNewValue());
			reloadFinancialProductInfo();
		} else if(e.getPropertyName().equals("FeesQty")) {
			feesQty = ((BigDecimal) (e.getNewValue() != null? new BigDecimal((Integer) e.getNewValue()): Env.ZERO)).intValue();
			startDate = (Timestamp) (startDateField.getValue() != null? startDateField.getValue(): new Timestamp(System.currentTimeMillis()));
			endDate = (Timestamp) (endDateField.getValue() != null? endDateField.getValue(): new Timestamp(System.currentTimeMillis()));
			if(feesQty != 0) {
				endDateField.setValue(getEndDateFromFrequency());
			}
		} else if(e.getPropertyName().equals("EndDate")) {
			endDate = (Timestamp) (e.getNewValue() != null? e.getNewValue(): new Timestamp(System.currentTimeMillis()));
			startDate = (Timestamp) (startDateField.getValue() != null? startDateField.getValue(): new Timestamp(System.currentTimeMillis()));
			feesQtyField.setValue(getFeesQtyFromFrequency());
			if(paymentFrequency.equals("F")) {
				payDateField.setValue(endDate);
			}
		} else if(e.getPropertyName().equals("PaymentFrequency")) {
			paymentFrequency = (String) e.getNewValue();
			paymentFrequencyField.setValue(paymentFrequency);
			startDate = (Timestamp) (startDateField.getValue() != null? startDateField.getValue(): new Timestamp(System.currentTimeMillis()));
			endDate = (Timestamp) (endDateField.getValue() != null? endDateField.getValue(): new Timestamp(System.currentTimeMillis()));
			feesQtyField.setValue(getFeesQtyFromFrequency());
			//	Set Read Only from payment frequency
			feesQtyField.setReadWrite(!paymentFrequency.equals("F"));
		}
	}   //  vetoableChange
	
	/**
	 * Reload Financial Product Information
	 */
	private void reloadFinancialProductInfo() {
		startDate = (Timestamp) (startDateField.getValue() != null? startDateField.getValue(): new Timestamp(System.currentTimeMillis()));
		MFMProduct financialProduct = MFMProduct.getById(Env.getCtx(), financialProductId, null);
		if(financialProduct != null) {
			isDueFixed = financialProduct.get_ValueAsBoolean("IsDueFixed");
			//	Currency
			if(financialProduct.get_ValueAsInt("C_Currency_ID") != 0) {
				currencyId = financialProduct.get_ValueAsInt("C_Currency_ID");
				currencyField.setValue(currencyId);
			}
			//	Financial Rate
			if(financialProduct.get_ValueAsInt("FM_Rate_ID") != 0) {
				MFMRate rate = MFMRate.getById(Env.getCtx(), financialProduct.get_ValueAsInt("FM_Rate_ID"));
				//	Get Version
				financialRate = rate.getValidRate(startDate);
				if(financialRate == null) {
					financialRate = Env.ZERO;
				}
				//	Set
				financialRateField.setValue(financialRate);
			}
			//	For Payment Frequency
			if(financialProduct.get_ValueAsString("PaymentFrequency") != null) {
				paymentFrequencyField.setValue(financialProduct.get_ValueAsString("PaymentFrequency"));
				paymentFrequency = financialProduct.get_ValueAsString("PaymentFrequency");
				//	Fee Quantity
				if(financialProduct.get_ValueAsInt("MinFeesQty") != 0) {
					feesQty = financialProduct.get_ValueAsInt("MinFeesQty");
					feesQtyField.setValue(feesQty);
					endDateField.setValue(getEndDateFromFrequency());
				}
			}
		}
	}

	/**
	 * Get Values from fields
	 */
	private void getValues() {
		businessPartnerId = (int) (businessPartnerField.getValue() != null? businessPartnerField.getValue(): 0);
		financialProductId = (int) (financialProductField.getValue() != null? financialProductField.getValue(): 0);
		currencyId = (int) (currencyField.getValue() != null? currencyField.getValue(): 0);
		userId = (int) (userField.getValue() != null? userField.getValue(): 0);
		capitalAmt = (BigDecimal) capitalAmtField.getValue();
		feesQty = ((BigDecimal) (feesQtyField.getValue() != null? feesQtyField.getValue(): Env.ZERO)).intValue();
		financialRate = (BigDecimal) financialRateField.getValue();
		interestAmt = (BigDecimal) interestAmtField.getValue();
		taxAmt = (BigDecimal) taxAmtField.getValue();
		startDate = (Timestamp) (startDateField.getValue() != null? startDateField.getValue(): new Timestamp(System.currentTimeMillis()));
		endDate = (Timestamp) (endDateField.getValue() != null? endDateField.getValue(): new Timestamp(System.currentTimeMillis()));
		payDate = (Timestamp) (payDateField.getValue() != null? payDateField.getValue(): new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * Clear Values for Form
	 */
	private void clearFieldValues(boolean all) {
		clearValues();
		if(all) {
			businessPartnerField.setValue(null);
		}
		financialProductField.setValue(null);
		currencyField.setValue(null);
		userField.setValue(null);
		capitalAmtField.setValue(null);
		feesQtyField.setValue(null);
		financialRateField.setValue(null);
		interestAmtField.setValue(null);
		taxAmtField.setValue(null);
		grandTotalField.setValue(null);
		paymentFrequencyField.setValue(null);
		startDateField.setValue(new Timestamp(System.currentTimeMillis()));
		endDateField.setValue(null);
		payDateField.setValue(new Timestamp(System.currentTimeMillis()));
		miniTable.setRowCount(0);
	}
	
	/**************************************************************************
	 *  Simulate Data
	 */
	public void simulate() {
		getValues();
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					String msg = validateData();
					if(msg == null) {
						msg = simulateData(trxName);
						statusBar.setStatusLine(msg);
					} else {
						statusBar.setStatusLine(msg);
						throw new AdempiereException(msg);
					}
				}
			});
		} catch (Exception e) {
			FDialog.error(windowNo, form , "Error", Msg.parseTranslation(Env.getCtx(), e.getLocalizedMessage()));
		} finally {
			confirmPanel.getOKButton().setEnabled(true);
		}
	}   //  simulate
	
	/**************************************************************************
	 *  Save Data
	 */
	public void saveData() {
		getValues();
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					String msg = validateData();
					if(msg == null) {
						msg = saveData(trxName);
						statusBar.setStatusLine(msg);
						clearFieldValues(true);
					} else {
						statusBar.setStatusLine(msg);
						throw new AdempiereException(msg);
					}
				}
			});
		} catch (Exception e) {
			FDialog.error(windowNo, form , "Error", Msg.parseTranslation(Env.getCtx(), e.getLocalizedMessage()));
		} finally {
			confirmPanel.getOKButton().setEnabled(true);
		}
	}   //  saveData
	
	public ADForm getForm() {
		return form;
	}

	@Override
	public void setInterestFeeAmt(BigDecimal interestFeeAmt) {
		interestAmtField.setValue(interestFeeAmt);
	}

	@Override
	public void setTaxAmt(BigDecimal taxAmt) {
		taxAmtField.setValue(taxAmt);
	}
	
	@Override
	public void setGrandToral(BigDecimal grandTotal) {
		grandTotalField.setValue(grandTotal);
	}

	@Override
	public void reloadAmortization(List<AmortizationValue> amortizationList) {
		miniTable.setRowCount(0);
		ListModelTable model = miniTable.getModel();
		if(amortizationList != null) {
			for(AmortizationValue amortizationLine : amortizationList) {
				Vector<Object> line = new Vector<Object>();
				line.add(amortizationLine.getPeriodNo());	//	PeriodNo
				line.add(amortizationLine.getStartDate());	//	StartDate
				line.add(amortizationLine.getEndDate());	//	EndDate
				line.add(amortizationLine.getDueDate());	//	DueDate
				line.add(amortizationLine.getCapitalAmtFee().setScale(getStdPrecision(), RoundingMode.HALF_UP));	//	CapitalAmtFee
				line.add(amortizationLine.getInterestAmtFee().setScale(getStdPrecision(), RoundingMode.HALF_UP));	//	InterestAmtFee
				line.add(amortizationLine.getTaxAmtFee().setScale(getStdPrecision(), RoundingMode.HALF_UP));	//	TaxAmtFee
				line.add(amortizationLine.getFixedFeeAmt().setScale(getStdPrecision(), RoundingMode.HALF_UP));	//	FixedFeeAmt
				line.add(amortizationLine.getRemainingCapital().setScale(getStdPrecision(), RoundingMode.HALF_UP));	//	RemainingCapital
				//	Add to model
				model.add(line);
			}
		}
		miniTable.setData(model ,getColumnNames());
		miniTable.autoSize();
		setColumnClass(miniTable);
	}
}   //  VTrxMaterial

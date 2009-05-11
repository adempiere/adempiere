/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLocator;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_RMA;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 *  CreateFrom (Called from GridController.startProcess)
 *
 *  @author  Jorg Janke
 *  @version $Id: VCreateFrom.java,v 1.4 2006/10/11 09:52:23 comdivision Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1794050 ] Usability: VCreateFrom OK button always enabled
 * 			<li>FR [ 1974354 ] VCreateFrom.create should be more flexible
 * 			<li>BF [ 2007837 ] VCreateFrom.save() should run in trx
 * 			<li>BF [ 2584790 ] Material Receipt error
 * @author Victor Perez, e-Evolucion 
 *          <li> RF [1811114] http://sourceforge.net/tracker/index.php?func=detail&aid=1811114&group_id=176962&atid=879335
 * @author Karsten Thiemann, Schaeffer AG
 * 			<li>Bug [ 1759431 ] Problems with VCreateFrom
 */
public abstract class VCreateFrom extends CDialog
	implements ICreateFrom, ActionListener, TableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7749087067953806144L;
	
	/**
	 * Register custom VCreateFrom* class
	 * @param ad_table_id
	 * @param cl custom class
	 */
	public static final void registerClass(int ad_table_id, Class<? extends VCreateFrom> cl)
	{
		s_registeredClasses.put(ad_table_id, cl);
		s_log.info("Registered AD_Table_ID="+ad_table_id+", Class="+cl);
	}
	
	/** Registered classes map (AD_Table_ID -> Class) */
	private static HashMap<Integer, Class<? extends VCreateFrom>> s_registeredClasses = null;
	static
	{
		// Register defaults:
		s_registeredClasses = new HashMap<Integer, Class<? extends VCreateFrom>>();
		s_registeredClasses.put(I_C_BankStatement.Table_ID, VCreateFromStatement.class);
		s_registeredClasses.put(I_C_Invoice.Table_ID, VCreateFromInvoice.class);
		s_registeredClasses.put(I_M_InOut.Table_ID, VCreateFromShipment.class);
		//s_registeredClasses.put(I_C_PaySelection.Table_ID, null); //	ignore - will call process C_PaySelection_CreateFrom
		s_registeredClasses.put(I_M_RMA.Table_ID, VCreateFromRMA.class);
	}
	
	/**
	 *  Factory - called from APanel
	 *  @param  mTab        Model Tab for the trx
	 *  @return JDialog
	 */
	public static VCreateFrom create (GridTab mTab)
	{
		//	dynamic init preparation
		int AD_Table_ID = Env.getContextAsInt(Env.getCtx(), mTab.getWindowNo(), "BaseTable_ID");

		VCreateFrom retValue = null;
		Class<? extends VCreateFrom> cl = s_registeredClasses.get(AD_Table_ID);
		if (cl != null)
		{
			try
			{
				java.lang.reflect.Constructor<? extends VCreateFrom> ctor = cl.getConstructor(GridTab.class);
				retValue = ctor.newInstance(mTab);
			}
			catch (Throwable e)
			{
				s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				return null;
			}
		}
		if (retValue == null)
		{
			s_log.info("Unsupported AD_Table_ID=" + AD_Table_ID);
			return null;
		}
		return retValue;
	}   //  create

	
	/**************************************************************************
	 *  Protected super class Constructor
	 *  @param mTab MTab
	 */
	VCreateFrom (GridTab mTab)
	{
		super(Env.getWindow(mTab.getWindowNo()), true);
		log.info(mTab.toString());
		p_WindowNo = mTab.getWindowNo();
		p_mTab = mTab;

		try
		{
			if (!dynInit())
				return;
			jbInit();
			confirmPanel.addActionListener(this);
			//  Set status
			statusBar.setStatusDB("");
			tableChanged(null);
			p_initOK = true;
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			p_initOK = false;
		}
		AEnv.positionCenterWindow(Env.getWindow(p_WindowNo), this);
	}   //  VCreateFrom

	/** Window No               */
	protected int               p_WindowNo;
	/** Model Tab               */
	protected GridTab         		p_mTab;

	private boolean             p_initOK = false;

	/** Loaded Order            */
	protected MOrder 			p_order = null;
	/**	Logger			*/
	protected CLogger 		log = CLogger.getCLogger(getClass());
	/**	Static Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (VCreateFrom.class);
	
	//
	protected CPanel parameterPanel = new CPanel();
	protected CPanel parameterBankPanel = new CPanel();
	private BorderLayout parameterLayout = new BorderLayout();
	private JLabel bankAccountLabel = new JLabel();
	protected CPanel parameterStdPanel = new CPanel();
	private JLabel bPartnerLabel = new JLabel();
	protected VLookup bankAccountField;
	//RF [1811114]
	private JLabel authorizationLabel = new JLabel();
	protected VString authorizationField = new VString();
	private GridBagLayout parameterStdLayout = new GridBagLayout();
	private GridBagLayout parameterBankLayout = new GridBagLayout();
	//
	private JLabel tenderTypeLabel = new JLabel();
	protected VLookup tenderTypeField;
	private JLabel documentTypeLabel = new JLabel();
	protected VLookup documentTypeField;
	// Bug [1759431]
	protected JCheckBox sameWarehouseCb = new JCheckBox();
	protected VLookup bPartnerField;
	protected JLabel orderLabel = new JLabel();
	protected JComboBox orderField = new JComboBox();
	protected JLabel invoiceLabel = new JLabel();
	protected JComboBox invoiceField = new JComboBox();
	protected JLabel shipmentLabel = new JLabel();
	protected JComboBox shipmentField = new JComboBox();
    protected JLabel upcLabel = new JLabel();
    protected JTextField upcField = new JTextField();
	private JScrollPane dataPane = new JScrollPane();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	protected ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private StatusBar statusBar = new StatusBar();
	protected MiniTable dataTable = new MiniTable();
	protected JLabel locatorLabel = new JLabel();
	protected VLocator locatorField = new VLocator();
	private CLabel documentNoLabel = new CLabel(Msg.translate(Env.getCtx(), "DocumentNo"));
	protected CTextField documentNoField = new CTextField(10);
	protected CLabel BPartner_idLabel = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	protected VLookup bPartnerLookup;
	private CLabel dateFromLabel = new CLabel(Msg.translate(Env.getCtx(), "DateTrx"));
	protected VDate dateFromField = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel dateToLabel = new CLabel("-");
	protected VDate dateToField = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel amtFromLabel = new CLabel(Msg.translate(Env.getCtx(), "PayAmt"));
	protected VNumber amtFromField = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel amtToLabel = new CLabel("-");
	protected VNumber amtToField = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	
	public static final String SELECT_ALL = "SelectAll";
//	public static final String SELECT_ALL_TOOLTIP = "Select all records";	
    
    /** Label for the rma selection */
    protected JLabel rmaLabel = new JLabel();
    /** Combo box for selecting RMA document */
    protected JComboBox rmaField = new JComboBox();
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
    	parameterPanel.setLayout(parameterLayout);
    	parameterStdPanel.setLayout(parameterStdLayout);
    	parameterBankPanel.setLayout(parameterBankLayout);
    	//
    	bankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
    	//RF [1811114]
    	authorizationLabel.setText(Msg.translate(Env.getCtx(), "R_AuthCode"));
    	bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));
    	orderLabel.setText(Msg.getElement(Env.getCtx(), "C_Order_ID", false));
    	invoiceLabel.setText(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false));
    	shipmentLabel.setText(Msg.getElement(Env.getCtx(), "M_InOut_ID", false));
    	locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
    	rmaLabel.setText(Msg.translate(Env.getCtx(), "M_RMA_ID"));
    	sameWarehouseCb.setText(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", true));
    	sameWarehouseCb.setToolTipText(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", false));
        upcLabel.setText(Msg.getElement(Env.getCtx(), "UPC", false));
    	documentTypeLabel.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
    	tenderTypeLabel.setText(Msg.translate(Env.getCtx(), "TenderType"));

    	//
    	this.getContentPane().add(parameterPanel, BorderLayout.NORTH);
    	parameterPanel.add(parameterBankPanel, BorderLayout.NORTH);
    	//RF [1811114]
    	documentNoLabel.setLabelFor(documentNoField);
    	//


    	dateFromLabel.setLabelFor(dateFromField);
    	dateFromField.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
    	dateToLabel.setLabelFor(dateToField);
    	dateToField.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
    	amtFromLabel.setLabelFor(amtFromField);
    	amtFromField.setToolTipText(Msg.translate(Env.getCtx(), "AmtFrom"));
    	amtToLabel.setLabelFor(amtToField);
    	amtToField.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));

    	parameterBankPanel.add(bankAccountLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if (bankAccountField != null)
    		parameterBankPanel.add(bankAccountField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterBankPanel.add(documentTypeLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if(documentTypeField!= null)
    		parameterBankPanel.add(documentTypeField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterBankPanel.add(tenderTypeLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if(tenderTypeField!=null)
    		parameterBankPanel.add(tenderTypeField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0)); 

    	parameterBankPanel.add(BPartner_idLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if ( bPartnerLookup != null )
    	{
    		parameterBankPanel.add(bPartnerLookup, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));   	
    	}

    
    	parameterBankPanel.add(documentNoLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(documentNoField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterBankPanel.add(authorizationLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(authorizationField, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));



    	parameterBankPanel.add(amtFromLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(amtFromField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterBankPanel.add(amtToLabel, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(amtToField, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterBankPanel.add(dateFromLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(dateFromField, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterBankPanel.add(dateToLabel, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterBankPanel.add(dateToField, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	parameterStdPanel.add(orderField,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 1, 5, 5), 0, 0));

    	parameterStdPanel.add(invoiceLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(invoiceField,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 1, 5, 5), 0, 0));
    	parameterPanel.add(parameterStdPanel, BorderLayout.CENTER);
    	parameterStdPanel.add(bPartnerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	if (bPartnerField != null)
    		parameterStdPanel.add(bPartnerField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
    				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterStdPanel.add(orderLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(orderField,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterStdPanel.add(invoiceLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(invoiceField,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterStdPanel.add(shipmentLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(shipmentField,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterStdPanel.add(locatorLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(locatorField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
    	parameterStdPanel.add(sameWarehouseCb, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
        if (this instanceof VCreateFromShipment) {
            parameterStdPanel.add(upcLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            parameterStdPanel.add(upcField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
        }


    	// Add RMA document selection to panel
    	parameterStdPanel.add(rmaLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	parameterStdPanel.add(rmaField,  new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
    			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

    	this.getContentPane().add(dataPane, BorderLayout.CENTER);
    	dataPane.getViewport().add(dataTable, null);
    	//
    	//
    	// @Trifon
    	AppsAction selectAllAction = new AppsAction (SELECT_ALL, KeyStroke.getKeyStroke(KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK), null);

    	CButton selectAllButton = (CButton)selectAllAction.getButton();
    	selectAllButton.setMargin(new Insets (0, 10, 0, 10));
    	selectAllButton.setDefaultCapable(true);
    	selectAllButton.addActionListener(this);
//  	selectAllButton.setToolTipText(Msg.getMsg(Env.getCtx(), SELECT_ALL_TOOLTIP));
    	confirmPanel.addButton(selectAllButton);
    	//
    	this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    	southPanel.setLayout(southLayout);
    	southPanel.add(confirmPanel, BorderLayout.CENTER);
    	// Trifon End

    	this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    	southPanel.setLayout(southLayout);
    	southPanel.add(confirmPanel, BorderLayout.CENTER);
    	southPanel.add(statusBar, BorderLayout.SOUTH);
    }   //  jbInit

	/**
	 *	Init OK to be able to make changes?
	 *  @return on if initialized
	 */
	public boolean isInitOK()
	{
		return p_initOK;
	}	//	isInitOK
	
	/**
	 * Get Warehouse from window's context
	 * @return warehouse id
	 */
	public int getM_Warehouse_ID()
	{
		return Env.getContextAsInt(Env.getCtx(), p_WindowNo, "M_Warehouse_ID");
	}

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	abstract boolean dynInit() throws Exception;

	/**
	 *  Init Business Partner Details
	 *  @param C_BPartner_ID BPartner
	 */
	abstract void initBPDetails(int C_BPartner_ID);

	/**
	 *  Add Info
	 */
	abstract void info();

	/**
	 *  Save & Insert Data
	 *  @return true if saved
	 */
	abstract boolean save(String trxName);

	/*************************************************************************/

	/**
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		log.config("Action=" + e.getActionCommand());
	//	if (m_action)
	//		return;
	//	m_action = true;

		//  OK - Save
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			try
			{
				Trx.run(new TrxRunnable()
				{
					public void run(String trxName)
					{
						if (save(trxName))
						{
							dispose();
						}
					}
				});
			}
			catch (Exception ex)
			{
				ADialog.error(p_WindowNo, this, "Error", ex.getLocalizedMessage());
			}
		}
		//  Cancel
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
		}
		// Select All
		// Trifon
		else if (e.getActionCommand().equals(SELECT_ALL))
		{
			TableModel model = dataTable.getModel();
			int rows = model.getRowCount();
			for (int i = 0; i < rows; i++)
			{
				model.setValueAt(new Boolean(true), i, 0);
			}
			info();
		}
	//	m_action = false;
	}   //  actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged (TableModelEvent e)
	{
		int type = -1;
		if (e != null)
		{
			type = e.getType();
			if (type != TableModelEvent.UPDATE)
				return;
		}
		log.config("Type=" + type);
		info();
		dataTable.repaint();
	}   //  tableChanged

	
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
		int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
		bPartnerField.setValue(new Integer(C_BPartner_ID));

		//  initial loading
		initBPartnerOIS(C_BPartner_ID, forInvoice);
	}   //  initBPartner

	/**
	 *  Load PBartner dependent Order/Invoice/Shipment Field.
	 *  @param C_BPartner_ID BPartner
	 *  @param forInvoice for invoice
	 */
	protected void initBPartnerOIS (int C_BPartner_ID, boolean forInvoice)
	{
		log.config("C_BPartner_ID=" + C_BPartner_ID);
		KeyNamePair pp = new KeyNamePair(0,"");
		boolean sameWarehouseOnly = sameWarehouseCb.isVisible() && sameWarehouseCb.isSelected();
		//  load PO Orders - Closed, Completed
		orderField.removeActionListener(this);
		orderField.removeAllItems();
		orderField.addItem(pp);
		//	Display
		StringBuffer display = new StringBuffer("o.DocumentNo||' - ' ||")
			.append(DB.TO_CHAR("o.DateOrdered", DisplayType.Date, Env.getAD_Language(Env.getCtx())))
			.append("||' - '||")
			.append(DB.TO_CHAR("o.GrandTotal", DisplayType.Amount, Env.getAD_Language(Env.getCtx())));
		//
		String column = "ol.QtyDelivered";
		if (forInvoice)
			column = "ol.QtyInvoiced";
		StringBuffer sql = new StringBuffer("SELECT o.C_Order_ID,").append(display)
			.append(" FROM C_Order o "
			+ "WHERE o.C_BPartner_ID=? AND o.IsSOTrx='N' AND o.DocStatus IN ('CL','CO')"
			+ " AND o.C_Order_ID IN "
				  + "(SELECT ol.C_Order_ID FROM C_OrderLine ol"
				  + " WHERE ol.QtyOrdered - ").append(column).append(" != 0) ");
		if(sameWarehouseOnly)
		{
			sql = sql.append(" AND o.M_Warehouse_ID=? ");
		}
		sql = sql.append("ORDER BY o.DateOrdered");
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			if(sameWarehouseOnly)
			{
				//only active for material receipts
				pstmt.setInt(2, getM_Warehouse_ID());
			}
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				orderField.addItem(pp);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		orderField.setSelectedIndex(0);
		orderField.addActionListener(this);
		this.pack();

		initBPDetails(C_BPartner_ID);
	}   //  initBPartnerOIS

	/**
	 *  Load Data - Order
	 *  @param C_Order_ID Order
	 *  @param forInvoice true if for invoice vs. delivery qty
	 */
	protected void loadOrder (int C_Order_ID, boolean forInvoice)
	{
		/**
		 *  Selected        - 0
		 *  Qty             - 1
		 *  C_UOM_ID        - 2
		 *  M_Product_ID    - 3
		 *  VendorProductNo - 4
		 *  OrderLine       - 5
		 *  ShipmentLine    - 6
		 *  InvoiceLine     - 7
		 */
		log.config("C_Order_ID=" + C_Order_ID);
		p_order = new MOrder (Env.getCtx(), C_Order_ID, null);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		StringBuffer sql = new StringBuffer("SELECT "
			+ "l.QtyOrdered-SUM(COALESCE(m.Qty,0)),"					//	1
			+ "CASE WHEN l.QtyOrdered=0 THEN 0 ELSE l.QtyEntered/l.QtyOrdered END,"	//	2
			+ " l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name),"			//	3..4
			+ " COALESCE(l.M_Product_ID,0),COALESCE(p.Name,c.Name),po.VendorProductNo,"	//	5..7
			+ " l.C_OrderLine_ID,l.Line "								//	8..9
			+ "FROM C_OrderLine l"
			+ " LEFT OUTER JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND l.C_BPartner_ID = po.C_BPartner_ID) "
			+ " LEFT OUTER JOIN M_MatchPO m ON (l.C_OrderLine_ID=m.C_OrderLine_ID AND ");
		sql.append(forInvoice ? "m.C_InvoiceLine_ID" : "m.M_InOutLine_ID");
		sql.append(" IS NOT NULL)")
			.append(" LEFT OUTER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID)"
			+ " LEFT OUTER JOIN C_Charge c ON (l.C_Charge_ID=c.C_Charge_ID)");
		if (Env.isBaseLanguage(Env.getCtx(), "C_UOM"))
			sql.append(" LEFT OUTER JOIN C_UOM uom ON (l.C_UOM_ID=uom.C_UOM_ID)");
		else
			sql.append(" LEFT OUTER JOIN C_UOM_Trl uom ON (l.C_UOM_ID=uom.C_UOM_ID AND uom.AD_Language='")
				.append(Env.getAD_Language(Env.getCtx())).append("')");
		//
		sql.append(" WHERE l.C_Order_ID=? "			//	#1
			+ "GROUP BY l.QtyOrdered,CASE WHEN l.QtyOrdered=0 THEN 0 ELSE l.QtyEntered/l.QtyOrdered END, "
			+ "l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name),po.VendorProductNo, "
				+ "l.M_Product_ID,COALESCE(p.Name,c.Name), l.Line,l.C_OrderLine_ID "
			+ "ORDER BY l.Line");
		//
		log.finer(sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_Order_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>();
				line.add(new Boolean(false));           //  0-Selection
				BigDecimal qtyOrdered = rs.getBigDecimal(1);
				BigDecimal multiplier = rs.getBigDecimal(2);
				BigDecimal qtyEntered = qtyOrdered.multiply(multiplier);
				line.add(qtyEntered);                   //  1-Qty
				KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(4).trim());
				line.add(pp);                           //  2-UOM
				pp = new KeyNamePair(rs.getInt(5), rs.getString(6));
				line.add(pp);                           //  3-Product
				line.add(rs.getString(7));				// 4-VendorProductNo
				pp = new KeyNamePair(rs.getInt(8), rs.getString(9));
				line.add(pp);                           //  5-OrderLine
				line.add(null);                         //  6-Ship
				line.add(null);                         //  7-Invoice
				data.add(line);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		loadTableOIS (data);
	}   //  LoadOrder


	/**
	 *  Load Order/Invoice/Shipment data into Table
	 *  @param data data
	 */
	protected void loadTableOIS (Vector<?> data)
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Quantity"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_UOM_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.getElement(Env.getCtx(), "VendorProductNo", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Order_ID", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "M_InOut_ID", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false));

		//  Remove previous listeners
		dataTable.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		model.addTableModelListener(this);
		dataTable.setModel(model);
		//
		dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		dataTable.setColumnClass(1, BigDecimal.class, true);        //  1-Qty
		dataTable.setColumnClass(2, String.class, true);        //  2-UOM
		dataTable.setColumnClass(3, String.class, true);        //  3-Product
		dataTable.setColumnClass(4, String.class, true);        //  4-VendorProductNo
		dataTable.setColumnClass(5, String.class, true);        //  5-Order
		dataTable.setColumnClass(6, String.class, true);        //  6-Ship
		dataTable.setColumnClass(7, String.class, true);        //  7-Invoice
		//  Table UI
		dataTable.autoSize();
	}   //  loadOrder

	/**
	 * Set form status line.
	 * Please note, will enable/disable the OK button if the selectedRowCount > 0.
	 * @param selectedRowCount number of selected lines
	 * @param text additional text
	 */
	protected void setStatusLine(int selectedRowCount, String text) {
		StringBuffer sb = new StringBuffer(String.valueOf(selectedRowCount));
		if (text != null && text.trim().length() > 0) {
			sb.append(" - ").append(text);
		}
		statusBar.setStatusLine(sb.toString());
		//
		confirmPanel.getOKButton().setEnabled(selectedRowCount > 0);
	}
	
	public void showWindow()
	{
		setVisible(true);
	}
	
	public void closeWindow()
	{
		dispose();
	}
}   //  VCreateFrom

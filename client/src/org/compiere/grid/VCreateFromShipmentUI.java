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

package org.compiere.grid;

import org.compiere.apps.AEnv;
import org.compiere.grid.ed.VLocator;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IMiniTable;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.*;
import org.compiere.swing.CPanel;
import org.compiere.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

/*
 * @author	Michael McKay
 * 				<li>release/380 - fix row selection event handling to fire single event per row selection
 */

public class VCreateFromShipmentUI extends CreateFromShipment implements ActionListener, VetoableChangeListener
{
    private static final int WINDOW_CUSTOMER_RETURN = 53097;

    private static final int WINDOW_RETURN_TO_VENDOR = 53098;

    private VCreateFromDialog dialog;

    private char isSOTrx='N';

    public VCreateFromShipmentUI(GridTab mTab)
    {
        super(mTab);
        log.info(getGridTab().toString());

        dialog = new VCreateFromDialog(this, getGridTab().getWindowNo(), true);

        p_WindowNo = getGridTab().getWindowNo();

        //AB 15-09-2015 Sales=Purchase Changes
        if(mTab.getAD_Tab_ID()==257)
        {
            isSOTrx='Y';
        }

        try
        {
            if (!dynInit())
                return;
            jbInit();

            setInitOK(true);
        }
        catch(Exception e)
        {
            log.log(Level.SEVERE, "", e);
            setInitOK(false);
        }
        AEnv.positionCenterWindow(Env.getWindow(p_WindowNo), dialog);
    }   //  VCreateFrom

    /** Window No               */
    private int p_WindowNo;

    /**	Logger			*/
    private CLogger log = CLogger.getCLogger(getClass());

    //
    private JLabel bPartnerLabel = new JLabel();
    private VLookup bPartnerField;

    private JLabel orderLabel = new JLabel();
    private JComboBox orderField = new JComboBox();

    private JLabel invoiceLabel = new JLabel();
    private JComboBox invoiceField = new JComboBox();

    /** Label for the rma selection */
    private JLabel rmaLabel = new JLabel();
    /** Combo box for selecting RMA document */
    private JComboBox rmaField = new JComboBox();

    private JLabel upcLabel = new JLabel();
    private JTextField upcField = new JTextField();

    private JCheckBox sameWarehouseCb = new JCheckBox();
    private JLabel locatorLabel = new JLabel();
    private VLocator locatorField = new VLocator();

    /**
     *  Dynamic Init
     *  @throws Exception if Lookups cannot be initialized
     *  @return true if initialized
     */
    public boolean dynInit() throws Exception
    {
        log.config("");

        super.dynInit();

        dialog.setTitle(getTitle());

        //  load Locator
        MLocatorLookup locator = new MLocatorLookup(Env.getCtx(), p_WindowNo);
        locatorField = new VLocator ("M_Locator_ID", true, false, true,	locator, p_WindowNo);
        sameWarehouseCb.setSelected(true);
        sameWarehouseCb.addActionListener(this);

        initBPartner(false);
        bPartnerField.addVetoableChangeListener(this);

        return true;
    }   //  dynInit

    /**
     *  Static Init.
     *  <pre>
     *  parameterPanel0
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
        boolean isRMAWindow = ((getGridTab().getAD_Window_ID() == WINDOW_RETURN_TO_VENDOR) || (getGridTab().getAD_Window_ID() == WINDOW_CUSTOMER_RETURN));

        bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));

        //AB 15-09-2015 Sales=Purchase Changes
        if (isSOTrx=='N')
        {
            orderLabel.setText(Msg.getElement(Env.getCtx(), "C_Order_ID", false));
        }
        else {orderLabel.setText("Sales Order");}

        locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
        invoiceLabel.setText(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false));
        sameWarehouseCb.setText(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", true));
        sameWarehouseCb.setToolTipText(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", false));
        rmaLabel.setText(Msg.translate(Env.getCtx(), "M_RMA_ID"));
        upcLabel.setText(Msg.getElement(Env.getCtx(), "UPC", false));

        CPanel parameterPanel = dialog.getParameterPanel();
        parameterPanel.setLayout(new BorderLayout());
        CPanel parameterStdPanel = new CPanel(new GridBagLayout());
        parameterPanel.add(parameterStdPanel, BorderLayout.CENTER);

        parameterStdPanel.add(bPartnerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        if (bPartnerField != null)
            parameterStdPanel.add(bPartnerField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

        if (! isRMAWindow) {
            parameterStdPanel.add(orderLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            parameterStdPanel.add(orderField,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

/*        	parameterStdPanel.add(invoiceLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
        			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        	parameterStdPanel.add(invoiceField,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
        			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));*/
        }

        parameterStdPanel.add(locatorLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        parameterStdPanel.add(locatorField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
        parameterStdPanel.add(sameWarehouseCb, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
        parameterStdPanel.add(upcLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        parameterStdPanel.add(upcField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));

        if (isRMAWindow) {
            // Add RMA document selection to panel
            parameterStdPanel.add(rmaLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            parameterStdPanel.add(rmaField,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
                    ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
        }
    }   //  jbInit

    /*************************************************************************/

    private boolean 	m_actionActive = false;

    /**
     *  Action Listener
     *  @param e event
     */
    public void actionPerformed(ActionEvent e)
    {
        log.config("Action=" + e.getActionCommand());

        if (m_actionActive)
            return;
        m_actionActive = true;
        log.config("Action=" + e.getActionCommand());
        //  Order
        if (e.getSource().equals(orderField))
        {
            KeyNamePair pp = (KeyNamePair)orderField.getSelectedItem();
            int C_Order_ID = 0;
            if (pp != null)
                C_Order_ID = pp.getKey();
            //  set Invoice, RMA and Shipment to Null
            rmaField.setSelectedIndex(-1);
            invoiceField.setSelectedIndex(-1);
            loadOrder(C_Order_ID, false, locatorField.getValue()!=null?((Integer)locatorField.getValue()).intValue():0);
        }
        //  Invoice
        else if (e.getSource().equals(invoiceField))
        {
            KeyNamePair pp = (KeyNamePair)invoiceField.getSelectedItem();
            int C_Invoice_ID = 0;
            if (pp != null)
                C_Invoice_ID = pp.getKey();
            //  set Order, RMA to Null
            orderField.setSelectedIndex(-1);
            rmaField.setSelectedIndex(-1);
            loadInvoice(C_Invoice_ID, locatorField.getValue()!=null?((Integer)locatorField.getValue()).intValue():0);
        }
        //  RMA
        else if (e.getSource().equals(rmaField))
        {
            KeyNamePair pp = (KeyNamePair)rmaField.getSelectedItem();
            int M_RMA_ID = 0;
            if (pp != null)
                M_RMA_ID = pp.getKey();
            //  set Order and Invoice to Null
            orderField.setSelectedIndex(-1);
            invoiceField.setSelectedIndex(-1);
            loadRMA(M_RMA_ID, locatorField.getValue()!=null?((Integer)locatorField.getValue()).intValue():0);
        }
        //sameWarehouseCb
        else if (e.getSource().equals(sameWarehouseCb))
        {
            initBPOrderDetails(((Integer)bPartnerField.getValue()).intValue(), false);
        }
        else if (e.getSource().equals(upcField))
        {
            checkProductUsingUPC();
        }
        m_actionActive = false;
    }   //  actionPerformed

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
            int C_BPartner_ID = ((Integer)e.getNewValue()).intValue();
            initBPOrderDetails (C_BPartner_ID, false);
        }
        dialog.tableChanged(null);
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
        int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
        bPartnerField.setValue(new Integer(C_BPartner_ID));

        //  initial loading
        initBPOrderDetails(C_BPartner_ID, forInvoice);
    }   //  initBPartner

    /**
     *  Load PBartner dependent Order/Invoice/Shipment Field.
     *  @param C_BPartner_ID BPartner
     *  @param forInvoice for invoice
     */
    protected void initBPOrderDetails (int C_BPartner_ID, boolean forInvoice)
    {
        log.config("C_BPartner_ID=" + C_BPartner_ID);
        KeyNamePair pp = new KeyNamePair(0,"");
        //  load PO Orders - Closed, Completed
        orderField.removeActionListener(this);
        orderField.removeAllItems();
        orderField.addItem(pp);

        //ArrayList<KeyNamePair> list = loadOrderData(C_BPartner_ID, forInvoice, sameWarehouseCb.isSelected());

        //AB 15-09-2015 Sales=Purchase Changes
        ArrayList<KeyNamePair> list = loadOrderData(C_BPartner_ID, forInvoice, sameWarehouseCb.isSelected(),isSOTrx);

        for(KeyNamePair knp : list)
            orderField.addItem(knp);

        orderField.setSelectedIndex(0);
        orderField.addActionListener(this);
        dialog.pack();

        initBPDetails(C_BPartner_ID);
    }   //  initBPartnerOIS

    public void initBPDetails(int C_BPartner_ID)
    {
        initBPInvoiceDetails(C_BPartner_ID);
        initBPRMADetails(C_BPartner_ID);
    }

    /**
     * Init Details - load invoices not shipped
     * @param C_BPartner_ID BPartner
     */
    private void initBPInvoiceDetails(int C_BPartner_ID)
    {
        log.config("C_BPartner_ID" + C_BPartner_ID);

        //  load Shipments (Receipts) - Completed, Closed
        invoiceField.removeActionListener(this);
        invoiceField.removeAllItems();
        //	None
        KeyNamePair pp = new KeyNamePair(0,"");
        invoiceField.addItem(pp);

        //ArrayList<KeyNamePair> list = loadInvoiceData(C_BPartner_ID);

        //AB 15-09-2015 Sales=Purchase Changes
        ArrayList<KeyNamePair> list = loadInvoiceData(C_BPartner_ID,isSOTrx);
        for(KeyNamePair knp : list)
            invoiceField.addItem(knp);

        invoiceField.setSelectedIndex(0);
        invoiceField.addActionListener(this);
        upcField.addActionListener(this);
    }

    /**
     * Load RMA that are candidates for shipment
     * @param C_BPartner_ID BPartner
     */
    private void initBPRMADetails(int C_BPartner_ID)
    {
        rmaField.removeActionListener(this);
        rmaField.removeAllItems();
        //  None
        KeyNamePair pp = new KeyNamePair(0,"");
        rmaField.addItem(pp);

        ArrayList<KeyNamePair> list = loadRMAData(C_BPartner_ID);
        for(KeyNamePair knp : list)
            rmaField.addItem(knp);

        rmaField.setSelectedIndex(0);
        rmaField.addActionListener(this);
    }

    /**
     *  Load Data - Order
     *  @param C_Order_ID Order
     *  @param forInvoice true if for invoice vs. delivery qty
     *  @param M_Locator_ID
     */
    protected void loadOrder (int C_Order_ID, boolean forInvoice, int M_Locator_ID)
    {
        loadTableOIS(getOrderData(C_Order_ID, forInvoice, M_Locator_ID));
    }   //  LoadOrder

    /**
     *  Load Data - RMA
     *  @param M_RMA_ID RMA
     *  @param M_Locator_ID
     */
    protected void loadRMA (int M_RMA_ID, int M_Locator_ID)
    {
        loadTableOIS(getRMAData(M_RMA_ID, M_Locator_ID));
    }

    /**
     *  Load Data - Invoice
     *  @param C_Invoice_ID Invoice
     *  @param M_Locator_ID
     */
    protected void loadInvoice (int C_Invoice_ID, int M_Locator_ID)
    {
        loadTableOIS(getInvoiceData(C_Invoice_ID, M_Locator_ID));
    }

    /**
     *  Load Order/Invoice/Shipment data into Table
     *  @param data data
     */
    protected void loadTableOIS (Vector<?> data)
    {
        //  Remove previous listeners
        dialog.getMiniTable().removeMiniTableSelectionListener(dialog);
        //  Set Model
        DefaultTableModel model = new DefaultTableModel(data, getOISColumnNames());
        dialog.getMiniTable().setModel(model);
        //
        configureMiniTable(dialog.getMiniTable());
        dialog.getMiniTable().addMiniTableSelectionListener(dialog);
    }   //  loadOrder

    public void showWindow()
    {
        dialog.setVisible(true);
    }

    public void closeWindow()
    {
        dialog.dispose();
    }

    /**
     * Checks the UPC value and checks if the UPC matches any of the products in the
     * list.
     */
    private void checkProductUsingUPC()
    {
        String upc = upcField.getText();
        DefaultTableModel model = (DefaultTableModel)dialog.getMiniTable().getModel();
        // Lookup UPC
        List<MProduct> products = MProduct.getByUPC(Env.getCtx(), upc, null);
        for (MProduct product : products)
        {
            int row = findProductRow(product.get_ID());
            if (row >= 0)
            {
                BigDecimal qty = (BigDecimal)model.getValueAt(row, 1);
                model.setValueAt(qty, row, 1);
                model.setValueAt(Boolean.TRUE, row, 0);
                model.fireTableRowsUpdated(row, row);
            }
        }
        upcField.setText("");
        upcField.requestFocusInWindow();
    }

    /**
     * Finds the row where a given product is. If the product is not found
     * in the table -1 is returned.
     * @param M_Product_ID
     * @return  Row of the product or -1 if non existing.
     *
     */
    private int findProductRow(int M_Product_ID)
    {
        DefaultTableModel model = (DefaultTableModel)dialog.getMiniTable().getModel();
        KeyNamePair kp;
        for (int i=0; i<model.getRowCount(); i++) {
            kp = (KeyNamePair)model.getValueAt(i, 4);
            if (kp.getKey()==M_Product_ID) {
                return(i);
            }
        }
        return(-1);
    }

    @Override
    protected void configureMiniTable(IMiniTable miniTable) {
        super.configureMiniTable(miniTable);
        // Set custom cell editor to enable editing locators
        MiniTable swingTable = (MiniTable) miniTable;
        TableColumn col = swingTable.getColumn(3);
        col.setCellEditor(new InnerLocatorTableCellEditor());
    }

    /**
     * Custom cell editor for setting locator from minitable.
     *
     * @author Daniel Tamm
     *
     */
    public class InnerLocatorTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        /**
         *
         */
        private static final long serialVersionUID = -7143484413792778213L;
        KeyNamePair currentValue;
        JTextField 	editor;

        public Object getCellEditorValue() {
            String locatorValue = editor.getText();
            MLocator loc = null;
            try {
                // Lookup locator using value
                loc = new Query(Env.getCtx(), MLocator.Table_Name, "value=?", null)
                        .setParameters(locatorValue)
                        .setClient_ID()
                        .first();
                // Set new keyNamePair for minitable
                currentValue = getLocatorKeyNamePair(loc.get_ID());

            } catch (Exception e) {
                String message = Msg.getMsg(Env.getCtx(), "Invalid") + " " + editor.getText();
                JOptionPane.showMessageDialog(null, message);
            }
            return(currentValue);

        }

        public Component getTableCellEditorComponent(JTable table,
                                                     Object value, boolean isSelected, int row, int column) {

            currentValue = (KeyNamePair)value;
            editor = new JTextField();
            editor.setText(currentValue.getName());
            return(editor);

        }

    }

}

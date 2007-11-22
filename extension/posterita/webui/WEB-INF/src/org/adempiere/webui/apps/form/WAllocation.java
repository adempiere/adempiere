/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.WStatusBar;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.event.ListDataEvent;


/**
 * Allocation panel. Panel for selecting sets of invoices and payments to
 * process. The panel contains a set of paramater components that select and
 * filter the invoices and payments, a panel for displaying and selecting
 * invoices, a panel for selecting and displaying payments, and a panel for
 * allocating payments.
 *
 *
 * @author Andrew Kimball
 *
 */
public class WAllocation extends ADForm implements EventListener,
        ValueChangeListener, WTableModelListener
{
    /*
     * TODO A lot of the code here replicates code in the rich client version of
     * VAllocation. Creating a common superclass would remove a lot of this
     * duplication.
     */

    /** Unique identifier. */
    private static final long serialVersionUID = 1L;

    /** */
    private Panel m_pnlMain = new Panel();

    /** Parameter panel. */
    private Panel m_pnlParameter = new Panel();
    /** Parameter grid. */
    private Grid m_grdParameter = new Grid();
    /** Business Partner label. */
    private Label m_lblBusinessPartner = new Label();
    /** Business Partner search editor. */
    private WEditor m_wedBusinessPartnerSearch = null;
    /** Currency label. */
    private Label m_lblCurrency = new Label();
    /** Currency pick editor. */
    private WEditor m_wedCurrencyPick = null;
    /** Date label. */
    private Label m_lblDate = new Label();
    /** Date Field editor. */
    private WDateEditor m_wdeDateField = new WDateEditor();
    /** Multi Currency checkbox. */
    private Checkbox m_chbMultiCurrency = new Checkbox();

    /** Payment panel. */
    private Panel m_pnlPayment = new Panel();
    /** Payment Panel label. */
    private Label m_lblPayment = new Label();
    /** Payment Info label. */
    private Label m_lblPaymentInfo = new Label();
    /** Payment Info table. */
    private WListbox m_lsbPayments = new WListbox();

    /** Invoice panel. */
    private Panel m_pnlInvoice = new Panel();
    /** Invoice Panel label. */
    private Label m_lblInvoice = new Label();
    /** Invoice Info label. */
    private Label m_lblInvoiceInfo = new Label();
    /** Invoice Info table. */
    private WListbox m_lsbInvoices = new WListbox();

    /** Panel to group Invoice and Payment panels. */
    private Panel m_pnlInfo = new Panel();

    /** Allocation panel. */
    private Panel m_pnlAllocate = new Panel();
    /** Difference label. */
    private Label m_lblDifference = new Label();
    /** Difference field. */
    private Textbox m_txbDifferenceField = new Textbox();
    /** Allocation button. */
    private Button m_btnAllocate = new Button();
    /** Allocation Currency label. */
    private Label m_lblAllocCurrency = new Label();
    /** Automatic write-off checkbox. */
    private Checkbox m_chbAutoWriteOff = new Checkbox();

    /** Status Bar. */
    private WStatusBar m_statusBar = new WStatusBar();

    /** true if a calculation is currentl being performed. */
    private boolean m_isCalculating = false;

    /** index of selected currency. */
    private int m_currencyId = 0;

    /** index of selected business partner. */
    private int m_businessPartnerId = 0;

    /** number of affected invoices. */
    private int m_noSelectedInvoices = 0;

    /** number of affected payments. */
    private int m_noSelectedPayments = 0;

    /** the index of the last row containing an invoice. */
    private int m_rowLastInvoice = 0;

    private ArrayList<Integer> m_bPartnerCheck = new ArrayList<Integer>();

    /** index of the <I>selected</I> column. */
    static final private int ms_selectedColIndex = 0;

    /** index of the <I>date</I> column. */
    static final private int ms_dateColIndex = 1;

    /** index of the <I>value</I> column. */
    static final private int ms_valueColIndex = 2;

    // Index changed if multi-currency (either 5 or 7)
    /** index of the <I>payment</I> column. */
    private int m_paymentColIndex = 7;

    /** index of the <I>open</I> column. */
    private int m_openColIndex = 6;

    /** index of the <I>discount</I> column. */
    private int m_discountColIndex = 7;

    /** index of the <I>write-off</I> column. */
    private int m_writeOffColIndex = 8;

    /** index of the <I>applied</I> column. */
    private int m_appliedColIndex = 9;

    /** value of total payments */
    private BigDecimal m_totalPayment = new BigDecimal(0.0);

    /** value of total invoice */
    private BigDecimal m_totalInvoiced = new BigDecimal(0.0);

    /** value of total credit */
    private BigDecimal m_totalCredit = new BigDecimal(0.0);

    /** enumeration of the possible types of allocation */
    private enum EIndicator
    {
        /** No allocation. */
        NONE,
        /** Total payment = Total invoiced. */
        TOTAL_PAY,
        /**
         * Subpayment
         * <p>
         * issotrx=y
         */
        SUBPAYMENT_SO,
        /**
         * Total payment > Total invoiced.
         * <p>
         * issotrx=y
         */
        GREATER_PAYMENT_SO,
        /** Subpayment with credit note. */
        CREDIT_MEMO,
        /**
         * Total payment > Total invoiced
         * <p>
         * issotrx=n
         */
        GREATER_PAYMENT_PO,
        /**
         * Total payment < Total invoiced
         * <p>
         * issotrx=n
         */
        GREATER_INVOICED_PO,
        /** Credit emo > Invoiced. */
        GREATER_CREDIT
    }

    /** Indicator for the type of allocation being made. */
    private EIndicator m_eIndicator = EIndicator.NONE;

    /** column index for Invoice. */
    private int m_invoiceIndex = 0;

    /** column index for credit. */
    private int m_creditIndex = 0;

    /**
     * Default constructor.
     */
    public WAllocation()
    {
        super();
    }

    /**
     * Create a panel containing details of payments.
     *
     * @see #createInfoPanel()
     * @see #createInvoicePanel()
     */
    private void createPaymentPanel()
    {
        Hbox hbox = new Hbox();

        // Put the table label at the top of this panel
        hbox.setWidth("98%");
        m_lblPayment.setValue(Msg.translate(Env.getCtx(), "C_Payment_ID"));
        hbox.setStyle("text-align:left");
        hbox.appendChild(m_lblPayment);
        m_pnlPayment.appendChild(hbox);

        // Add the payment details table
        m_lsbPayments.setWidth(null);
        m_lsbPayments.setRows(10);
        m_pnlPayment.appendChild(m_lsbPayments);

        // Put the selected payment information just below the table and
        // right-align it
        hbox = new Hbox();
        hbox.setWidth("98%");
        m_lblPaymentInfo.setValue(".");
        hbox.setStyle("text-align:right");
        hbox.appendChild(m_lblPaymentInfo);
        m_pnlPayment.appendChild(hbox);

        return;
    }

    /**
     * Create a panel containing details of invoices
     *
     * @see #createInfoPanel()
     * @see #createPaymentPanel()
     */
    private void createInvoicePanel()
    {
        Hbox hbox = new Hbox();

        // Put the table label at the top of this panel
        hbox.setWidth("98%");
        m_lblInvoice.setValue(Msg.translate(Env.getCtx(), "C_Invoice_ID"));
        hbox.setStyle("text-align:left");
        hbox.appendChild(m_lblInvoice);
        m_pnlInvoice.appendChild(hbox);

        // Add the invoice details table
        m_lsbInvoices.setWidth(null);
        m_lsbInvoices.setRows(10);
        m_pnlInvoice.appendChild(m_lsbInvoices);

        // Put the selected invoice information just below the table and
        // right-align it
        hbox = new Hbox();
        hbox.setWidth("98%");
        m_lblInvoiceInfo.setValue(".");
        hbox.setStyle("text-align:right");
        hbox.appendChild(m_lblInvoiceInfo);
        m_pnlInvoice.appendChild(hbox);

        return;
    }

    /**
     * Create a panel containing details of payments and invoices.
     *
     * @see #createInvoicePanel()
     * @see #createPaymentPanel()
     */
    private void createInfoPanel()
    {
        Vbox vbox = new Vbox();
        Separator separator = new Separator();

        vbox.setWidth("98%");
        vbox.setHeight("200px");
        vbox.setHeights("45%,5%,45%");

        // Put the payment panel in the top half of the box
        createPaymentPanel();
        vbox.appendChild(m_pnlPayment);

        // adda separator between the two panels
        // this should be a splitter, but splitters don't work with Listboxes
        separator.setBar(true);
        vbox.appendChild(separator);

        // Put the invoice panel in the bottom half of the box
        createInvoicePanel();
        vbox.appendChild(m_pnlInvoice);

        // add the box to the panel
        m_pnlInfo.appendChild(vbox);

        return;
    }

    /**
     * Create a panel containing components to enable allocations of payments to
     * invoices.
     */
    private void createAllocationPanel()
    {
        Hbox box = new Hbox();

        // align the components
        box.setValign("center");

        // First add the <I>difference</I> label
        m_lblDifference.setValue(Msg.getMsg(Env.getCtx(), "Difference"));
        box.appendChild(m_lblDifference);

        // Then put a label showing the currency
        m_lblAllocCurrency.setValue(".");
        box.appendChild(m_lblAllocCurrency);

        // Then add the label showing the difference between the selected
        // payment and invoice
        // amounts
        m_txbDifferenceField.setReadonly(true);
        m_txbDifferenceField.setText("0");
        m_txbDifferenceField.setCols(8);
        m_txbDifferenceField.setStyle("text-align:right");
        box.appendChild(m_txbDifferenceField);

        // Then add the auto write-off checkbox
        m_chbAutoWriteOff.setChecked(false);
        m_chbAutoWriteOff.setLabel(Msg.getMsg(Env.getCtx(), "AutoWriteOff", true));
        m_chbAutoWriteOff.setTooltiptext(Msg.getMsg(Env.getCtx(), "AutoWriteOff",
                false));
        box.appendChild(m_chbAutoWriteOff);

        // Finally add the <I>allocate</I> button
        m_btnAllocate.setLabel(Msg.getMsg(Env.getCtx(), "Process"));
        box.appendChild(m_btnAllocate);
        m_btnAllocate.addEventListener(Events.ON_CLICK, this);

        // Add the complete box to the panel
        m_pnlAllocate.appendChild(box);

        return;
    }

    /**
     * Create the panel containing the business partner, currency and date
     * parameters.
     *
     * These parameters are used for filtering and displaying
     * invoices and payments.
     */
    private void createParameterPanel()
    {
        Rows rows = new Rows();
        Row rowTop = new Row();
        Row rowBottom = new Row();

        // set the labels
        m_lblDate.setValue(Msg.getMsg(Env.getCtx(), "Date"));
        m_lblCurrency.setValue(Msg.translate(Env.getCtx(), "C_Currency_ID"));
        m_lblBusinessPartner.setValue(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
        m_chbMultiCurrency.setLabel(Msg.getMsg(Env.getCtx(), "MultiCurrency"));
        m_chbMultiCurrency.addEventListener(Events.ON_CHECK, this);

        rowTop.setAlign("left");
        rowTop.setStyle("text-align:right");
        rowBottom.setAlign("left");
        rowBottom.setStyle("text-align:right");

        // add the business partner search box to the top row
        rowTop.appendChild(m_lblBusinessPartner);
        rowTop.appendChild(m_wedBusinessPartnerSearch.getComponent());

        // add the date box to the top row
        rowTop.appendChild(m_lblDate);
        rowTop.appendChild(m_wdeDateField.getComponent());

        rows.appendChild(rowTop);

        // add the currency search box to the bottom row
        rowBottom.appendChild(m_lblCurrency);
        rowBottom.appendChild(m_wedCurrencyPick.getComponent());

        // add the mult-currency check-box to the bottom row
        rowBottom.appendChild(new Space());
        rowBottom.appendChild(m_chbMultiCurrency);

        // put it all together
        rows.appendChild(rowBottom);

        m_grdParameter.setWidth("600px");
        m_grdParameter.appendChild(rows);

        m_pnlParameter.appendChild(m_grdParameter);

        return;
    }

    /**
     * Initialise the panel.
     *
     * @param adFormId
     *            The Adempiere identifier for the form
     * @param name
     *            The name of the form
     */
    public void init(int adFormId, String name)
    {
        super.init(adFormId, name);

        m_currencyId = Env.getContextAsInt(Env.getCtx(), "$C_Currency_ID"); // default
        logger.info("Currency=" + m_currencyId);

        dynamicInitialise();

        createParameterPanel();
        m_pnlMain.appendChild(m_pnlParameter);

        createInfoPanel();
        m_pnlMain.appendChild(m_pnlInfo);

        createAllocationPanel();
        m_pnlMain.appendChild(m_pnlAllocate);

        m_pnlMain.setAlign("center");

        this.appendChild(m_pnlMain);

        this.appendChild(m_statusBar);

        this.setWidth("850px");

        calculate();

        return;
    }

    /**
     * Create the components of the panel which have dynamic content.
     *
     */
    private void dynamicInitialise()
    {
        // these magic numbers are copied from VAllocation
        final int adCurrencyId = 3505; // C_Invoice.C_Currency_ID
        final int adBPartnerId = 3499; // C_Invoice.C_BPartner_ID

        // status bar
        m_statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "AllocateStatus"));
        m_statusBar.setStatusDB("");

        // date field
        m_wdeDateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
        m_wdeDateField.addValueChangeListner(this);

        // business partner search edit box
        MLookup lookupBP = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
                0, adBPartnerId, DisplayType.Search);
        m_wedBusinessPartnerSearch = new WSearchEditor(lookupBP, Msg.translate(
                Env.getCtx(), "C_BPartner_ID"), "", true, false, true);
        m_wedBusinessPartnerSearch.addValueChangeListner(this);

        // currency pick search box
        MLookup lookupCur = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
                0, adCurrencyId, DisplayType.TableDir);
        m_wedCurrencyPick = new WTableDirEditor(lookupCur, Msg.translate(Env
                .getCtx(), "C_Currency_ID"), "", true, false, true);
        m_wedCurrencyPick.addValueChangeListner(this);
        m_wedCurrencyPick.setValue(new Integer(m_currencyId));

        return;
    }

    /**
     * Retrieve unallocated payments from the database and store them in the
     * payments table.
     */
    private void loadUnallocatedPayments()
    {
        /*
         * Load unallocated Payments 1-TrxDate, 2-DocumentNo, (3-Currency,
         * 4-PayAmt,) 5-ConvAmt, 6-ConvOpen, 7-Allocated
         */
        Vector<Object> data = null;
        // Header Info
        Vector<String> columnNames = getPaymentColumnNames();

        data = getPaymentData();

        // Set Model
        ListModelTable model = new ListModelTable(data);
        model.addTableModelListener(this);
        m_lsbPayments.setData(model, columnNames);
        //
        setPaymentColumnClasses();

        // removed terniary operator for clarity
        if (m_chbMultiCurrency.isChecked())
        {
            m_paymentColIndex = 7;
        }
        else
        {
            m_paymentColIndex = 5;
        }

        // Table UI
        // paymentTable.autoSize();

        return;
    }

    /**
     * Get the payment data.
     *
     * @return A vector containing the payment data
     */
    private Vector<Object> getPaymentData()
    {
        Vector<Object> data = new Vector<Object>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try
        {
            pstmt = preparePaymentStatement();
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                Vector<Object> line = new Vector<Object>();
                line.add(new Boolean(false)); // 0-Selection
                line.add(rs.getTimestamp(1)); // 1-TrxDate
                KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(2));
                line.add(pp); // 2-DocumentNo
                if (m_chbMultiCurrency.isChecked())
                {
                    line.add(rs.getString(4)); // 3-Currency
                    line.add(rs.getBigDecimal(5)); // 4-PayAmt
                }
                line.add(rs.getBigDecimal(6)); // 3/5-ConvAmt
                BigDecimal available = rs.getBigDecimal(7);
                if (available == null || available.signum() == 0) // nothing
                // available
                {
                    continue;
                }
                line.add(available); // 4/6-ConvOpen/Available
                line.add(Env.ZERO); // 5/7-Payment
                //
                data.add(line);
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException exception)
        {
            // TODO what to report here if don't have access to SQL statement
            logger.log(Level.SEVERE, getPaymentSql(), exception);
        }

        return data;
    }

    /**
     * Prepare the SQL statement for obtaining the unallocated payments.
     *
     * @return the precompiled SQL statement
     * @throws SQLException
     */
    private PreparedStatement preparePaymentStatement() throws SQLException
    {
        String sql = getPaymentSql();
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
        pstmt.setInt(1, m_currencyId);
        pstmt.setInt(2, m_currencyId);
        pstmt.setInt(3, m_businessPartnerId);

        if (!m_chbMultiCurrency.isChecked())
        {
            pstmt.setInt(4, m_currencyId);
        }
        return pstmt;
    }

    /**
     * Create the SQL statement for obtaining unallocated payments.
     *
     * @return the SQL statement for obtaining unallocated payments
     */
    private String getPaymentSql()
    {
        // Create SELECT statement
        StringBuffer sql = new StringBuffer("SELECT p.DateTrx, "
                + "p.DocumentNo, "
                + "p.C_Payment_ID," // 1..3
                + "c.ISO_Code, "
                + "p.PayAmt," // 4..5
                + "currencyConvert(p.PayAmt," + "p.C_Currency_ID,"
                + "?,"
                + "p.DateTrx,"
                + "p.C_ConversionType_ID,"
                + "p.AD_Client_ID,"
                + "p.AD_Org_ID),"// 6 #1
                + "currencyConvert(paymentAvailable(C_Payment_ID),"
                + "p.C_Currency_ID," + "?," + "p.DateTrx,"
                + "p.C_ConversionType_ID," + "p.AD_Client_ID,"
                + "p.AD_Org_ID)," // 7 #2
                + "p.MultiplierAP ");

        // Append FROM clause
        sql
                .append("FROM C_Payment_v p" // Corrected for AP/AR
                        + " INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID) ");

        // Append WHERE clause
        sql.append("WHERE p.IsAllocated='N' AND p.Processed='Y'"
                + " AND p.C_Charge_ID IS NULL" // Prepayments OK
                + " AND p.C_BPartner_ID=?"); // #3

        if (!m_chbMultiCurrency.isChecked())
        {
            sql.append(" AND p.C_Currency_ID=?"); // #4
        }
        sql.append(" ORDER BY p.DateTrx,p.DocumentNo");

        logger.fine("PaySQL=" + sql.toString());

        return sql.toString();
    }

    /**
     * Set the classes and read-only property for all payment table columns.
     */
    private void setPaymentColumnClasses()
    {
        int columnIndex = 0;
        m_lsbPayments.setColumnClass(columnIndex++, Boolean.class, false); // 0-Selection
        m_lsbPayments.setColumnClass(columnIndex++, Timestamp.class, true); // 1-TrxDate
        m_lsbPayments.setColumnClass(columnIndex++, String.class, true); // 2-Value
        if (m_chbMultiCurrency.isChecked())
        {
            m_lsbPayments.setColumnClass(columnIndex++, String.class, true); // 3-Currency
            m_lsbPayments.setColumnClass(columnIndex++, BigDecimal.class, true); // 4-PayAmt
        }
        m_lsbPayments.setColumnClass(columnIndex++, BigDecimal.class, true); // 5-ConvAmt
        m_lsbPayments.setColumnClass(columnIndex++, BigDecimal.class, true); // 6-ConvOpen
        m_lsbPayments.setColumnClass(columnIndex++, BigDecimal.class, false); // 7-Allocated

        return;
    }

    /**
     * Get a all of the columnn names.
     *
     * @return vector containing all of the column names
     */
    private Vector<String> getPaymentColumnNames()
    {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
        columnNames.add(Msg.translate(Env.getCtx(), "Date"));
        columnNames.add(Util
                .cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
        if (m_chbMultiCurrency.isChecked())
        {
            columnNames.add(Msg.getMsg(Env.getCtx(), "TrxCurrency"));
            columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
        }
        columnNames.add(Msg.getMsg(Env.getCtx(), "ConvertedAmount"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "OpenAmt"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "AppliedAmt"));
        return columnNames;
    }

    /**
     *
     *
     */
    private void loadUnpaidInvoices()
    {
        /*
         * Load unpaid Invoices 1-TrxDate, 2-Value, (3-Currency, 4-InvAmt,)
         * 5-ConvAmt, 6-ConvOpen, 7-ConvDisc, 8-WriteOff, 9-Applied
         *
         * SELECT i.DateInvoiced,i.DocumentNo,i.C_Invoice_ID,c.ISO_Code,
         * i.GrandTotal*i.MultiplierAP "GrandTotal",
         * currencyConvert(i.GrandTotal*i.MultiplierAP,i.C_Currency_ID,i.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID)
         * "GrandTotal $", invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID)
         * "Open",
         * currencyConvert(invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID),i.C_Currency_ID,i.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID)*i.MultiplierAP
         * "Open $",
         * invoiceDiscount(i.C_Invoice_ID,SysDate,C_InvoicePaySchedule_ID)
         * "Discount",
         * currencyConvert(invoiceDiscount(i.C_Invoice_ID,SysDate,C_InvoicePaySchedule_ID),i.C_Currency_ID,i.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID)*i.Multiplier*i.MultiplierAP
         * "Discount $", i.MultiplierAP, i.Multiplier FROM C_Invoice_v i INNER
         * JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID) WHERE --
         * i.IsPaid='N' AND i.Processed='Y' AND i.C_BPartner_ID=1000001
         */
        Vector<Object> data = new Vector<Object>();
        // Header Info
        Vector<String> columnNames = getInvoiceColumnNames();
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try
        {
            pstmt = prepareInvoiceStatement();
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                Vector<Object> line = new Vector<Object>();
                line.add(new Boolean(false)); // 0-Selection
                line.add(rs.getTimestamp(1)); // 1-TrxDate
                KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(2));
                line.add(pp); // 2-Value
                if (m_chbMultiCurrency.isChecked())
                {
                    line.add(rs.getString(4)); // 3-Currency
                    line.add(rs.getBigDecimal(5)); // 4-Orig Amount
                }
                line.add(rs.getBigDecimal(6)); // 3/5-ConvAmt
                BigDecimal open = rs.getBigDecimal(7);
                if (open == null) // no conversion rate
                {
                    open = Env.ZERO;
                }
                line.add(open); // 4/6-ConvOpen
                BigDecimal discount = rs.getBigDecimal(8);
                if (discount == null) // no conversion rate
                {
                    discount = Env.ZERO;
                }
                line.add(discount); // 5/7-ConvAllowedDisc
                line.add(Env.ZERO); // 6/8-WriteOff
                line.add(Env.ZERO); // 7/9-Applied

                // Add when open <> 0 (i.e. not if no conversion rate)
                if (Env.ZERO.compareTo(open) != 0)
                {
                    data.add(line);
                }
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException exception)
        {
            logger.log(Level.SEVERE, getInvoiceSql(), exception);
        }

        // Remove previous listeners
        // TODO
        // invoiceTable.getModel().removeTableModelListener(this);

        ListModelTable model = new ListModelTable(data);
        model.addTableModelListener(this);
        m_lsbInvoices.setData(model, columnNames);

        // set column data
        setInvoiceColumnClasses();

        // TODO Table UI
        // invoiceTable.autoSize();

        return;
    }

    /**
     * Prepare the SQL statement for obtaining the unallocated invoices.
     *
     * @return the precompiled SQL statement
     * @throws SQLException
     */
    private PreparedStatement prepareInvoiceStatement() throws SQLException
    {
        String sql = getInvoiceSql();
        PreparedStatement pstmt = DB.prepareStatement(sql, null);

        pstmt.setInt(1, m_currencyId);
        pstmt.setInt(2, m_currencyId);
        pstmt.setTimestamp(3, (Timestamp) m_wdeDateField.getValue());
        pstmt.setInt(4, m_currencyId);
        pstmt.setInt(5, m_businessPartnerId);

        if (!m_chbMultiCurrency.isChecked())
        {
            pstmt.setInt(6, m_currencyId);
        }

        return pstmt;
    }

    /**
     * Get all of the columnn names for the invoice table.
     *
     * @return vector containing all of the column names
     */
    private Vector<String> getInvoiceColumnNames()
    {
        Vector<String> columnNames = new Vector<String>();

        columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
        columnNames.add(Msg.translate(Env.getCtx(), "Date"));
        columnNames.add(Util
                .cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
        if (m_chbMultiCurrency.isChecked())
        {
            columnNames.add(Msg.getMsg(Env.getCtx(), "TrxCurrency"));
            columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
        }
        columnNames.add(Msg.getMsg(Env.getCtx(), "ConvertedAmount"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "OpenAmt"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "Discount"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "WriteOff"));
        columnNames.add(Msg.getMsg(Env.getCtx(), "AppliedAmt"));

        return columnNames;
    }

    /**
     * Set the classes and read-only property for all invoice table columns.
     */
    private void setInvoiceColumnClasses()
    {
        int columnIndex = 0;
        m_lsbInvoices.setColumnClass(columnIndex++, Boolean.class, false); // 0-Selection
        m_lsbInvoices.setColumnClass(columnIndex++, Timestamp.class, true); // 1-TrxDate
        m_lsbInvoices.setColumnClass(columnIndex++, String.class, true); // 2-Value
        if (m_chbMultiCurrency.isChecked())
        {
            m_lsbInvoices.setColumnClass(columnIndex++, String.class, true); // 3-Currency
            m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, true); // 4-Amt
        }
        m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, true); // 5-ConvAmt
        m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, true); // 6-ConvAmt
        // Open
        m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, false); // 7-Conv
        // Discount
        m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, false); // 8-Conv
        // WriteOff
        m_lsbInvoices.setColumnClass(columnIndex++, BigDecimal.class, false); // 9-Conv
        // Applied
    }

    /**
     * Create the SQL statement for obtaining unallocated invoices.
     *
     * @return the SQL statement for obtaining unallocated invoices
     */
    private String getInvoiceSql()
    {
        StringBuffer sql = new StringBuffer(
                "SELECT i.DateInvoiced, "
                        + "i.DocumentNo, "
                        + "i.C_Invoice_ID," // 1..3
                        + "c.ISO_Code, "
                        + "i.GrandTotal * i.MultiplierAP, " // 4..5
                        // Orig
                        // Currency
                        + "currencyConvert(i.GrandTotal * i.MultiplierAP, "
                        + "i.C_Currency_ID, "
                        + "?, "
                        + "i.DateInvoiced, "
                        + "i.C_ConversionType_ID, "
                        + "i.AD_Client_ID, "
                        + "i.AD_Org_ID), " // 6 #1 Converted
                        + "currencyConvert(invoiceOpen(C_Invoice_ID, C_InvoicePaySchedule_ID), "
                        + "i.C_Currency_ID, "
                        + "?, "
                        + "i.DateInvoiced, "
                        + "i.C_ConversionType_ID, "
                        + "i.AD_Client_ID, "
                        + "i.AD_Org_ID) * i.MultiplierAP, " // 7 #2 Converted
                        // Open
                        + "currencyConvert(invoiceDiscount(i.C_Invoice_ID, "
                        + "?, "
                        + "C_InvoicePaySchedule_ID), " // 8 AllowedDiscount
                        + "i.C_Currency_ID, " + "?, " + "i.DateInvoiced, "
                        + "i.C_ConversionType_ID, " + "i.AD_Client_ID, "
                        + "i.AD_Org_ID) * i.Multiplier * i.MultiplierAP, " // #3,
                        // #4
                        + "i.MultiplierAP");

        // FROM
        sql
                .append(" FROM C_Invoice_v i" // corrected for CM/Split
                        + " INNER JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID) ");

        // WHERE
        sql.append(" WHERE i.IsPaid='N' AND i.Processed='Y'"
                + " AND i.C_BPartner_ID=?"); // #5

        // additional WHERE
        if (!m_chbMultiCurrency.isChecked())
        {
            sql.append(" AND i.C_Currency_ID=?"); // #6
        }

        // ORDER BY
        sql.append(" ORDER BY i.DateInvoiced, i.DocumentNo");

        logger.fine("InvSQL=" + sql.toString());

        return sql.toString();
    }

    /**
     * Load Business Partner Info - Payments - Invoices.
     */
    private void loadBPartner()
    {
        Integer key = null;
        Thread thread;

        logger.config("BPartner=" + m_businessPartnerId + ", Cur="
                + m_currencyId);

        // Need to have both values
        if ((m_businessPartnerId == 0) || (m_currencyId == 0))
        {
            return;
        }

        // Async BPartner Test
        key = new Integer(m_businessPartnerId);
        if (!m_bPartnerCheck.contains(key))
        {
            thread = new Thread()
            {
                public void run()
                {
                    MPayment.setIsAllocated(Env.getCtx(), m_businessPartnerId,
                            null);
                    MInvoice.setIsPaid(Env.getCtx(), m_businessPartnerId, null);
                }
            };
            thread.start();
            m_bPartnerCheck.add(key);
        }

        loadUnallocatedPayments();

        loadUnpaidInvoices();

        setColumnIndices();

        // Calculate Totals
        calculate();

        return;
    } // loadBPartner

    /**
     * Set the column indices, depending on whether multi-currency is checked.
     */
    private void setColumnIndices()
    {
        final int defaultOpenColIndex = 4;
        final int defaultDiscountColIndex = 5;
        final int defaultWriteOffColIndex = 6;
        final int defaultAppliedColIndex = 7;
        int colOffset = 0;

        // removed use of tertiary operator to aid readability
        if (m_chbMultiCurrency.isChecked())
        {
            colOffset = 2;
        }
        // set column indices
        m_openColIndex = defaultOpenColIndex + colOffset;           // 4 or 6
        m_discountColIndex = defaultDiscountColIndex + colOffset;   // 5 or 7
        m_writeOffColIndex = defaultWriteOffColIndex + colOffset;   // 6 or 8
        m_appliedColIndex = defaultAppliedColIndex + colOffset;     // 7 or 9

        return;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.adempiere.webui.panel.ADForm#onEvent(org.zkoss.zk.ui.event.Event)
     */
    public void onEvent(Event event) throws Exception
    {
        if (event == null)
        {
            return;
        }

        logger.config("");

        if (event.getTarget().equals(m_chbMultiCurrency))
        {
            loadBPartner();
        }
        else if (event.getTarget().equals(m_btnAllocate))
        {
            m_btnAllocate.setEnabled(false);
            saveData();
            loadBPartner();
            m_btnAllocate.setEnabled(true);
        }

        return;
    }

    /**
     * Calculate Allocation info.
     */
    private void calculate()
    {
        // TODO refactor this. Function is too long to maintain easily
        DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);
        Timestamp allocDate = null;
        boolean isPaymentSelected = false;
        boolean isInvoiceSelected = false;

        logger.config("");

        // Payment
        BigDecimal totalPayment = new BigDecimal(0.0);
        int rows = m_lsbPayments.getRowCount();
        m_noSelectedPayments = 0;

        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            isPaymentSelected = isRowSelected(m_lsbPayments, rowIndex);
            if (isPaymentSelected)
            {
                // TODO remove magic number
                Timestamp ts = (Timestamp) m_lsbPayments.getValueAt(rowIndex,
                        ms_dateColIndex);
                allocDate = TimeUtil.max(allocDate, ts);
                BigDecimal bd = (BigDecimal) m_lsbPayments.getValueAt(rowIndex,
                        m_paymentColIndex);
                totalPayment = totalPayment.add(bd); // Applied Pay
                m_noSelectedPayments++;

                logger.fine("Payment_" + rowIndex + " = " + bd
                        + " - Total=" + totalPayment);
            }
        }

        m_lblPaymentInfo.setValue(String.valueOf(m_noSelectedPayments) + " - "
                + Msg.getMsg(Env.getCtx(), "Sum") + "  "
                + format.format(totalPayment) + " ");

        // Invoices
        BigDecimal totalInvoice = new BigDecimal(0.0);
        rows = m_lsbInvoices.getRowCount();
        m_noSelectedInvoices = 0;

        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            isInvoiceSelected = isRowSelected(m_lsbInvoices, rowIndex);
            if (isInvoiceSelected)
            {
                Timestamp ts = (Timestamp) m_lsbInvoices.getValueAt(rowIndex,
                        ms_dateColIndex);
                allocDate = TimeUtil.max(allocDate, ts);
                BigDecimal bd = (BigDecimal) m_lsbInvoices.getValueAt(rowIndex,
                        m_appliedColIndex);
                totalInvoice = totalInvoice.add(bd); // Applied Inv
                m_noSelectedInvoices++;

                logger.fine("Invoice_" + rowIndex + " = " + bd + " - Total="
                        + totalPayment);
            }
        }
        m_lblInvoiceInfo.setValue(String.valueOf(m_noSelectedInvoices) + " - "
                + Msg.getMsg(Env.getCtx(), "Sum") + "  "
                + format.format(totalInvoice) + " ");

        // Set AllocationDate
        if (allocDate != null)
        {
            m_wdeDateField.setValue(allocDate);
        }
        // Set Allocation Currency
        m_lblAllocCurrency.setValue(m_wedCurrencyPick.getDisplay());

        // TODO extract method

        // modification fabian-subpayments 040506
        int noInvoices = m_noSelectedInvoices;
        int noPayments = m_noSelectedPayments;
        logger.config("Npayments:" + noPayments + " Ninvoice:" + noInvoices);
        m_totalPayment = totalPayment;
        m_totalInvoiced = totalInvoice;

        // Difference
        BigDecimal difference = m_totalPayment.subtract(m_totalInvoiced);
        m_txbDifferenceField.setText(format.format(difference));

        logger.info("Total-Pay:" + m_totalPayment + " TotalInv: "
                + m_totalInvoiced + " Diference: " + difference + " npayments="
                + noPayments + " ninvoices=" + noInvoices);

        m_totalCredit = new BigDecimal("0");

        boolean enableAllocButton = false;
        m_eIndicator = EIndicator.NONE;

        // isTotalPay
        if (difference.compareTo(new BigDecimal(0.0)) == 0 && noPayments == 1)
        {// ----------------------------------
            m_eIndicator = EIndicator.TOTAL_PAY; // totalpay=totalinvoiced
            enableAllocButton = true;
        }
        // isSubPayment
        else if (difference.compareTo(new BigDecimal(0.0)) < 0
                && noPayments == 1 && noInvoices == 1
                && m_totalPayment.compareTo(new BigDecimal(0.0)) > 0
                && m_totalInvoiced.compareTo(new BigDecimal(0.0)) > 0)
        {
            m_eIndicator = EIndicator.SUBPAYMENT_SO; // subpayment issotrx=y
            enableAllocButton = true;
        }
        // isGreaterPaymentSO
        else if (difference.compareTo(new BigDecimal(0.0)) > 0
                && noPayments == 1 && noInvoices == 1
                && m_totalPayment.compareTo(new BigDecimal(0.0)) > 0
                && m_totalInvoiced.compareTo(new BigDecimal(0.0)) > 0)
        {
            m_eIndicator = EIndicator.GREATER_PAYMENT_SO; // totalpay>totalinvoiced
            // issotrx=y
            enableAllocButton = true;
        }
        // isCreditMemo
        else if (difference.compareTo(new BigDecimal(0.0)) <= 0
                && noPayments == 0 && noInvoices > 0)
        {
            m_eIndicator = EIndicator.CREDIT_MEMO; // Credit Memo
            if (revisionSubcredit())
            {
                enableAllocButton = true;
            }
        }
        // isGreaterPaymentPO
        else if (difference.compareTo(new BigDecimal(0.0)) < 0
                && noPayments == 1 && noInvoices == 1
                && m_totalPayment.compareTo(new BigDecimal(0.0)) < 0
                && m_totalInvoiced.compareTo(new BigDecimal(0.0)) < 0)
        {
            m_eIndicator = EIndicator.GREATER_PAYMENT_PO; // totalpay>totalinvoiced
            // issotrx=n
            enableAllocButton = true;
        }
        // special case vendor subpayments
        else if (difference.compareTo(new BigDecimal(0.0)) > 0
                && noPayments == 1 && noInvoices == 1
                && m_totalPayment.compareTo(new BigDecimal(0.0)) < 0
                && m_totalInvoiced.compareTo(new BigDecimal(0.0)) < 0)
        {
            m_eIndicator = EIndicator.GREATER_INVOICED_PO; // totalpay<totalinvoiced
            // issotrx=n
            enableAllocButton = true;
        }
        else if (difference.compareTo(new BigDecimal(0.0)) > 0
                && noPayments == 0 && noInvoices > 0)
        {
            m_eIndicator = EIndicator.GREATER_CREDIT;
            if (revisionSubcredit()) // creditmemo > invoiced
            {
                enableAllocButton = true;
            }
        }

        logger.info("Enable Allocate Button=" + enableAllocButton
                + " Indicator=" + m_eIndicator);

        m_btnAllocate.setEnabled(enableAllocButton);

        return;
    } // calculate

    /**
     * Query whether the specified <code>row</code> of the specified
     * <code>table</code> is selected.
     *
     * @param table The table to query
     * @param row   The row to query
     * @return true if the row is selected, false otherwise
     */
    private boolean isRowSelected(WListbox table, int row)
    {
        return ((Boolean) table.getValueAt(row, ms_selectedColIndex))
                .booleanValue();
    }

    /**
     * Queries whether the alocation is a subcredit.
     *
     * @return true if the selected allocation represent a subcredit. False otherwise.
     */
    private boolean revisionSubcredit()
    {
        int noCredits = 0;

        logger.fine("Recalculating grid");

        // TODO factor out
        calculatePayments();

        // TODO factor out
        // Invoices
        noCredits = calculateInvoices();

        if (m_noSelectedInvoices == 1
                && m_noSelectedPayments == 0
                && noCredits == 1
                && m_totalInvoiced.subtract(m_totalCredit).compareTo(Env.ZERO) >= 0)
        {
            logger.info("return true");
            return true;
        }
        return false;
    }

    /**
     * Calculate the number of selected payments and the total payment amount.
     *
     * @return the number of credits
     */
    private int calculateInvoices()
    {
        BigDecimal totalInvoice = Env.ZERO;
        BigDecimal totalCredit = Env.ZERO;
        int noInvoices = m_lsbInvoices.getRowCount();
        int noCredits = 0;
        BigDecimal openAmount = null;

        m_noSelectedInvoices = 0;
        m_totalCredit = Env.ZERO;

        for (int rowIndex = 0; rowIndex < noInvoices; rowIndex++)
        {
            if (isRowSelected(m_lsbInvoices, rowIndex))
            {
                openAmount = (BigDecimal) m_lsbInvoices.getValueAt(rowIndex,
                        m_openColIndex);

                logger.info("*Value:" + openAmount);

                if (isNegative(openAmount))
                {
                    noCredits++;
                    totalCredit = totalCredit.add(openAmount);
                    m_creditIndex = rowIndex;
                }
                else
                {
                    m_noSelectedInvoices++;
                    totalInvoice = totalInvoice.add(openAmount); // Applied
                    // Inv
                    m_invoiceIndex = rowIndex;
                }

                logger.info("Invoice_" + rowIndex + " = " + openAmount
                        + " - Total=" + totalInvoice);
            }
        }
        m_totalInvoiced = totalInvoice;
        m_totalCredit = totalCredit;

        logger.info("totalInvoiced= " + m_totalInvoiced
                + "--totalcredit= " + m_totalCredit);

        logger.info("inv-cr: " + m_totalInvoiced.subtract(m_totalCredit));

        return noCredits;
    }

    /**
     * Calculate the number of selected payments and the total payment amount.
     */
    private void calculatePayments()
    {
        BigDecimal totalPayment = Env.ZERO;
        int noPayments = m_lsbPayments.getRowCount();

        m_noSelectedPayments = 0;
        for (int paymentIndex = 0; paymentIndex < noPayments; paymentIndex++)
        {
            if (isRowSelected(m_lsbPayments, paymentIndex))
            {
                BigDecimal bd = (BigDecimal) m_lsbPayments.getValueAt(
                        paymentIndex, m_paymentColIndex);
                totalPayment = totalPayment.add(bd); // Applied Pay
                m_noSelectedPayments++;

                logger.info("Payment_" + paymentIndex + " = " + bd
                        + " - Total=" + totalPayment);
            }
        }
        m_totalPayment = totalPayment;

        return;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.adempiere.webui.event.ValueChangeListener#valueChange(org.adempiere.webui.event.ValueChangeEvent)
     */
    public void valueChange(ValueChangeEvent event)
    {
        String name = event.getPropertyName();
        Object value = event.getNewValue();

        logger.config(name + "=" + value);

        if (value == null)
        {
            return;
        }

        // BPartner
        if (name.equals("C_BPartner_ID"))
        {
            m_wedBusinessPartnerSearch.setValue(value);
            m_businessPartnerId = ((Integer) value).intValue();
            loadBPartner();
        }
        // Currency
        else if (name.equals("C_Currency_ID"))
        {
            m_currencyId = ((Integer) value).intValue();
            loadBPartner();
        }
        // Date for Multi-Currency
        else if (name.equals("Date") && m_chbMultiCurrency.isChecked())
        {
            loadBPartner();
        }

        return;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.adempiere.webui.event.WTableModelListener#tableChanged(org.adempiere.webui.event.WTableModelEvent)
     */
    public void tableChanged(WTableModelEvent event)
    {
        // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
        boolean isUpdate = (event.getType() == ListDataEvent.CONTENTS_CHANGED);
        boolean isPayment = false;

        // Not a table update
        if (!isUpdate)
        {
            calculate();
            return;
        }

        if (m_isCalculating) // Avoid recursive calls
        {
            return;
        }
        m_isCalculating = true;

        isPayment = (event.getModel().equals(m_lsbPayments.getModel()));

        logger.config("Row=" + event.getFirstRow() + ", Col="
                + event.getColumn() + ", InvoiceTable=" + isPayment);

        // Payments
        if (isPayment)
        {
            paymentTableChanged(event);
        }
        else
        {
            invoiceTableChanged(event);
        }

        m_isCalculating = false;
        calculate();

        return;
    }

    /**
     * Respond to an event on the invoice table.
     *
     * @param event The event to consider
     */
    private void invoiceTableChanged(WTableModelEvent event)
    {
        boolean isSubPayment = false;
        int row = event.getFirstRow();
        int col = event.getColumn();
        final double thirtyPercent = 0.30;

        // Invoice Selection
        if (col == ms_selectedColIndex)
        {
            // TableModel invoice = invoiceTable.getModel();
            // selected - set applied amount
            if (isRowSelected(m_lsbInvoices, row))
            {
                BigDecimal amount = (BigDecimal) m_lsbInvoices.getValueAt(row,
                        m_openColIndex); // Open Amount
                amount = amount.subtract((BigDecimal) m_lsbInvoices.getValueAt(
                        row, m_discountColIndex));
                m_lsbInvoices.setValueAt(Env.ZERO, row, m_writeOffColIndex); // to
                // be
                // sure
                m_lsbInvoices.setValueAt(amount, row, m_appliedColIndex);

                // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
                isSubPayment = revisionSubpayment();

                logger.fine("Invoice-issubpayment: " + isSubPayment);

                m_rowLastInvoice = row;
                if (isSubPayment)
                {
                    logger.fine("End Process SubPayment");
                    // TODO factor out to isNegative
                    if (isNegative(amount.subtract(m_totalPayment)))
                    {
                        m_lsbInvoices.setValueAt(amount, row, m_appliedColIndex);
                    }
                    else
                    {
                        m_lsbInvoices.setValueAt(m_totalPayment, row,
                                m_appliedColIndex);
                    }
                }
                // ---END----------
            }
            else
            // de-selected
            {
                m_lsbInvoices.setValueAt(Env.ZERO, row, m_writeOffColIndex);
                m_lsbInvoices.setValueAt(Env.ZERO, row, m_appliedColIndex);
            }
            m_lsbInvoices.repaint(); // update r/o
        }

        // Invoice - Try to balance entry
        else if (m_chbAutoWriteOff.isChecked())
        {
            // TableModel invoice = invoiceTable.getModel();
            // if applied entered, adjust writeOff
            if (col == m_appliedColIndex)
            {
                BigDecimal openAmount = (BigDecimal) m_lsbInvoices.getValueAt(
                        row, m_openColIndex); // Open Amount
                BigDecimal amount = openAmount
                        .subtract((BigDecimal) m_lsbInvoices.getValueAt(row,
                                m_discountColIndex));
                amount = amount.subtract((BigDecimal) m_lsbInvoices.getValueAt(
                        row, m_appliedColIndex));
                m_lsbInvoices.setValueAt(amount, row, m_writeOffColIndex);

                // Warning if > 30%
                if (amount.doubleValue() / openAmount.doubleValue() > thirtyPercent)
                {
                    FDialog.warn(m_windowNo, "AllocationWriteOffWarn");
                }
            }
            else
            // adjust applied
            {
                BigDecimal amount = (BigDecimal) m_lsbInvoices.getValueAt(row,
                        m_openColIndex); // Open Amount
                amount = amount.subtract((BigDecimal) m_lsbInvoices.getValueAt(
                        row, m_discountColIndex));
                amount = amount.subtract((BigDecimal) m_lsbInvoices.getValueAt(
                        row, m_writeOffColIndex));
                m_lsbInvoices.setValueAt(amount, row, m_appliedColIndex);
            }
        }

        return;
    }

    /**
     * Respond to an event on the payment table.
     *
     * @param event the event to respond to
     */
    private void paymentTableChanged(WTableModelEvent event)
    {
        boolean isSubPayment;
        int row = event.getFirstRow();
        int col = event.getColumn();

        if (col == ms_selectedColIndex)
        {
            // selected - set payment amount
            if (isRowSelected(m_lsbPayments, row))
            {
                BigDecimal amount = (BigDecimal) m_lsbPayments.getValueAt(row,
                        m_openColIndex); // Open Amount
                m_lsbPayments.setValueAt(amount, row, m_paymentColIndex);

                // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
                isSubPayment = revisionSubpayment();

                logger.fine("Payment-issubpayment: " + isSubPayment);

                if (isSubPayment)
                {
                    m_rowLastInvoice = getRowInvoice();

                    if (m_rowLastInvoice > -1)
                    {
                        if (isNegative(m_totalInvoiced.subtract(m_totalPayment)))
                        {
                            m_lsbInvoices.setValueAt(m_totalInvoiced,
                                    m_rowLastInvoice, m_appliedColIndex);
                        }
                        else
                        {
                            m_lsbInvoices.setValueAt(m_totalPayment,
                                    m_rowLastInvoice, m_appliedColIndex);
                        }
                    }
                }
                else if (m_totalPayment == m_totalInvoiced)
                {
                    m_lsbInvoices.setValueAt((BigDecimal) m_lsbInvoices.getValueAt(m_rowLastInvoice,
                            m_openColIndex), m_rowLastInvoice, m_appliedColIndex);
                }
                // -End-------------
            }
            else
            // de-selected
            {
                m_lsbPayments.setValueAt(Env.ZERO, row, m_paymentColIndex);
            }
            m_lsbPayments.repaint(); // update r/o
        }
    }

    /**
     * Find the index of the row containing the last invoice.
     *
     * @return index of the row containing the last invoice.
     */
    private int getRowInvoice()
    {
        // Invoices
        // TableModel invoice = invoiceTable.getModel();
        BigDecimal totalInvoice = Env.ZERO;
        BigDecimal totalCredit = Env.ZERO;
        int noInvoices = m_lsbInvoices.getRowCount();
        int noCredits = 0;
        int rowInvoice = -1;
        BigDecimal openAmount = null;

        logger.fine("Finding row");

        m_noSelectedInvoices = 0;
        m_totalCredit = Env.ZERO;

        for (int invoiceIndex = 0; invoiceIndex < noInvoices; invoiceIndex++)
        {
            if (isRowSelected(m_lsbInvoices, invoiceIndex))
            {
                openAmount = (BigDecimal) m_lsbInvoices.getValueAt(invoiceIndex,
                        m_openColIndex);

                if (isNegative(openAmount))
                {
                    noCredits++;
                    totalCredit = totalCredit.add(openAmount);
                }
                else
                {
                    m_noSelectedInvoices++;
                    totalInvoice = totalInvoice.add(openAmount); // Applied
                    // Inv
                    rowInvoice = invoiceIndex;
                }
            }
        }

        logger.fine("totalInvoiced= " + totalInvoice + "--totalcredit= "
                + totalCredit);

        return rowInvoice;
    }

    /**
     * Performs subpayment revision.
     */
    // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
    private boolean revisionSubpayment()
    {
        logger.fine("Recalculating grid");

        // Payment
        // TableModel payment = paymentTable.getModel();
        BigDecimal totalPayment = Env.ZERO;
        int rows = m_lsbPayments.getRowCount();

        m_noSelectedPayments = 0;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            if (isRowSelected(m_lsbPayments, rowIndex))
            {
                BigDecimal bd = (BigDecimal) m_lsbPayments.getValueAt(rowIndex,
                        m_paymentColIndex);
                totalPayment = totalPayment.add(bd); // Applied Pay
                m_noSelectedPayments++;

                logger.fine("Payment_" + rowIndex + " = " + bd + " - Total="
                        + totalPayment);
            }
        }

        // Invoices
        // TableModel invoice = invoiceTable.getModel();
        BigDecimal totalInvoice = Env.ZERO;
        rows = m_lsbInvoices.getRowCount();
        m_noSelectedInvoices = 0;

        for (int rowIndex = 0; rowIndex < rows; rowIndex++)
        {
            if (isRowSelected(m_lsbInvoices, rowIndex))
            {
                BigDecimal bd = (BigDecimal) m_lsbInvoices.getValueAt(rowIndex,
                        m_openColIndex);
                totalInvoice = totalInvoice.add(bd); // Applied Inv
                m_noSelectedInvoices++;

                logger.fine("Invoice_" + rowIndex + " = " + bd + " - Total="
                        + totalPayment);
            }
        }
        m_totalPayment = totalPayment;
        m_totalInvoiced = totalInvoice;

        logger.fine("**totalPayment: " + m_totalPayment + " totalInvoiced: "
                + m_totalInvoiced);

        if ((m_noSelectedInvoices == 1) && (m_noSelectedPayments == 1))
        {
            BigDecimal difference = totalPayment.subtract(totalInvoice);
            if (isNegative(difference))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Query whether the specified value is negative.
     *
     * @param value
     *            the value to test for sign
     * @return true if the value is negative, false otherwise
     */
    public static boolean isNegative(BigDecimal value)
    {
        return (value.signum() == -1);
    }

    /**
     * Query whether the specified value is positive.
     *
     * @param value
     *            the value to test for sign
     * @return true if the value is positive, false otherwise
     */
    public static boolean isPositive(BigDecimal value)
    {
        return (value.signum() == 1);
    }

    /**
     * Query whether the specified value is non-zero.
     *
     * @param value
     *            the value to test for sign
     * @return true if the value is non-zero, false otherwise
     */
    public static boolean isNonZero(BigDecimal value)
    {
        return (value.signum() != 0);
    }

    /**
     * Query whether the specified value is zero.
     *
     * @param value
     *            the value to test for sign
     * @return true if the value is zero, false otherwise
     */
    public static boolean isZero(BigDecimal value)
    {
        return (value.signum() == 0);
    }

    /**
     * Save Data.
     */
    private void saveData()
    {
        // TODO refactor this
        // fixed fields
        final int AD_Client_ID = Env.getContextAsInt(Env.getCtx(), m_windowNo,
                "AD_Client_ID");
        final int AD_Org_ID = Env.getContextAsInt(Env.getCtx(), m_windowNo,
                "AD_Org_ID");
        final int C_BPartner_ID = m_businessPartnerId;
        final int C_Currency_ID = m_currencyId; // the allocation currency
        final int C_Order_ID = 0;
        final int C_CashLine_ID = 0;
        final Timestamp DateTrx = (Timestamp) m_wdeDateField.getValue();

        if (m_noSelectedInvoices + m_noSelectedPayments == 0)
        {
            return;
        }
        //
        if (AD_Org_ID == 0)
        {
            FDialog.error(m_windowNo, "Org0NotAllowed");
            return;
        }
        //
        logger.config("Client=" + AD_Client_ID + ", Org=" + AD_Org_ID
                + ", BPartner=" + C_BPartner_ID + ", Date=" + DateTrx);

        Trx trx = Trx.get(Trx.createTrxName("AL"), true);

        /**
         * Generation of allocations: amount/discount/writeOff - if there is one
         * payment -- one line per invoice is generated with both the Invoice
         * and Payment reference Pay=80 Inv=100 Disc=10 WOff=10 => 80/10/10
         * Pay#1 Inv#1 or Pay=160 Inv=100 Disc=10 WOff=10 => 80/10/10 Pay#1
         * Inv#1 Pay=160 Inv=100 Disc=10 WOff=10 => 80/10/10 Pay#1 Inv#2 - if
         * there are multiple payment lines -- the amounts are allocated
         * starting with the first payment and payment Pay=60 Inv=100 Disc=10
         * WOff=10 => 60/10/10 Pay#1 Inv#1 Pay=100 Inv=100 Disc=10 WOff=10 =>
         * 20/0/0 Pay#2 Inv#1 Pay=100 Inv=100 Disc=10 WOff=10 => 80/10/10 Pay#2
         * Inv#2 - if you apply a credit memo to an invoice Inv=10 Disc=0 WOff=0 =>
         * 10/0/0 Inv#1 Inv=-10 Disc=0 WOff=0 => -10/0/0 Inv#2 - if you want to
         * write off a (partial) invoice without applying, enter zero in applied
         * Inv=10 Disc=1 WOff=9 => 0/1/9 Inv#1 Issues - you cannot write-off a
         * payment
         */

        // Payment - Loop and add them to paymentList/amountList
        int paymentRows = m_lsbPayments.getRowCount();
        ArrayList<Integer> paymentList = new ArrayList<Integer>(paymentRows);
        ArrayList<BigDecimal> amountList = new ArrayList<BigDecimal>(
                paymentRows);

        BigDecimal paymentAppliedAmt = Env.ZERO;

        for (int rowIndex = 0; rowIndex < paymentRows; rowIndex++)
        {
            // Payment line is selected
            if (isRowSelected(m_lsbPayments, rowIndex))
            {
                KeyNamePair pp = (KeyNamePair) m_lsbPayments.getValueAt(
                        rowIndex, ms_valueColIndex); // Value
                // Payment variables
                int C_Payment_ID = pp.getKey();
                paymentList.add(new Integer(C_Payment_ID));
                //
                BigDecimal PaymentAmt = (BigDecimal) m_lsbPayments.getValueAt(
                        rowIndex, m_paymentColIndex); // Applied Payment
                amountList.add(PaymentAmt);
                //
                paymentAppliedAmt = paymentAppliedAmt.add(PaymentAmt);
                //
                logger.fine("C_Payment_ID=" + C_Payment_ID + " - PaymentAmt="
                        + PaymentAmt); // + " * " + Multiplier + " = " +
                // PaymentAmtAbs);
            }
        }

        logger.config("Number of Payments=" + paymentList.size() + " - Total="
                + paymentAppliedAmt);

        // Invoices - Loop and generate alloctions
        int invoiceRows = m_lsbInvoices.getRowCount();
        BigDecimal totalAppliedAmt = Env.ZERO;

        // Create Allocation - but don't save yet
        MAllocationHdr alloc = new MAllocationHdr(Env.getCtx(), true, DateTrx,
                C_Currency_ID, Env.getContext(Env.getCtx(), "#AD_User_Name"),
                trx.getTrxName()); // manual
        alloc.setAD_Org_ID(AD_Org_ID);

        // For all invoices
        int invoiceLines = 0;
        for (int rowIndex = 0; rowIndex < invoiceRows; rowIndex++)
        {
            // Invoice line is selected
            if (isRowSelected(m_lsbInvoices, rowIndex))
            {
                invoiceLines++;
                KeyNamePair pp = (KeyNamePair) m_lsbInvoices.getValueAt(
                        rowIndex, ms_valueColIndex); // Value
                // Invoice variables
                int C_Invoice_ID = pp.getKey();
                BigDecimal AppliedAmt = (BigDecimal) m_lsbInvoices.getValueAt(
                        rowIndex, m_appliedColIndex);
                // semi-fixed fields (reset after first invoice)
                BigDecimal DiscountAmt = (BigDecimal) m_lsbInvoices.getValueAt(
                        rowIndex, m_discountColIndex);
                BigDecimal WriteOffAmt = (BigDecimal) m_lsbInvoices.getValueAt(
                        rowIndex, m_writeOffColIndex);
                // OverUnderAmt needs to be in Allocation Currency
                BigDecimal OverUnderAmt = ((BigDecimal) m_lsbInvoices
                        .getValueAt(rowIndex, m_openColIndex)).subtract(
                        AppliedAmt).subtract(DiscountAmt).subtract(WriteOffAmt);

                // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
                if (m_eIndicator == EIndicator.SUBPAYMENT_SO)
                {
                    OverUnderAmt = ((BigDecimal) m_lsbInvoices.getValueAt(
                            rowIndex, m_openColIndex)).subtract(m_totalPayment);
                    AppliedAmt = m_totalPayment;
                }
                else if (m_eIndicator == EIndicator.GREATER_INVOICED_PO)
                {
                    OverUnderAmt = m_totalInvoiced.subtract(m_totalPayment);
                    AppliedAmt = m_totalPayment;
                }
                else if (m_eIndicator == EIndicator.GREATER_PAYMENT_PO)
                {
                    OverUnderAmt = new BigDecimal("0");
                    AppliedAmt = m_totalInvoiced;
                }
                else if (m_eIndicator == EIndicator.GREATER_CREDIT)
                {
                    OverUnderAmt = new BigDecimal("0");
                    AppliedAmt = m_totalInvoiced;
                }
                else if (m_eIndicator == EIndicator.CREDIT_MEMO) // subpayment
                // with
                // credit
                // note
                {
                    logger.fine("**AppliedAmt: " + AppliedAmt);
                    if (isPositive(AppliedAmt))
                    {
                        OverUnderAmt = AppliedAmt.add(m_totalCredit);
                        AppliedAmt = m_totalCredit.abs();
                    }
                }
                // ------END---------------------------

                logger.config("Invoice #" + rowIndex + " - AppliedAmt="
                        + AppliedAmt);// + " -> " + AppliedAbs);

                // loop through all payments until invoice applied
                int noPayments = 0;
                for (int paymentIndex = 0; (paymentIndex < paymentList.size())
                        && (isNonZero(AppliedAmt)); paymentIndex++)
                {
                    int C_Payment_ID = ((Integer) paymentList.get(paymentIndex))
                            .intValue();
                    BigDecimal PaymentAmt = (BigDecimal) amountList
                            .get(paymentIndex);
                    if (isNonZero(PaymentAmt))
                    {
                        logger.config(".. with payment #" + paymentIndex
                                + ", Amt=" + PaymentAmt);

                        noPayments++;
                        // use Invoice Applied Amt
                        BigDecimal amount = AppliedAmt;
                        logger.fine("C_Payment_ID=" + C_Payment_ID
                                + ", C_Invoice_ID=" + C_Invoice_ID
                                + ", Amount=" + amount + ", Discount="
                                + DiscountAmt + ", WriteOff=" + WriteOffAmt);

                        // Allocation Header
                        if ((alloc.get_ID() == 0) && !alloc.save())
                        {
                            logger.log(Level.SEVERE, "Allocation not created");
                            return;
                        }
                        // Allocation Line
                        MAllocationLine aLine = new MAllocationLine(alloc,
                                amount, DiscountAmt, WriteOffAmt, OverUnderAmt);
                        aLine.setDocInfo(C_BPartner_ID, C_Order_ID,
                                C_Invoice_ID);
                        aLine.setPaymentInfo(C_Payment_ID, C_CashLine_ID);

                        if (!aLine.save())
                        {
                            logger.log(Level.SEVERE,
                                    "Allocation Line not written - Invoice="
                                            + C_Invoice_ID);
                        }

                        // Apply Discounts and WriteOff only first time
                        DiscountAmt = Env.ZERO;
                        WriteOffAmt = Env.ZERO;
                        // subtract amount from Payment/Invoice
                        AppliedAmt = AppliedAmt.subtract(amount);
                        PaymentAmt = PaymentAmt.subtract(amount);

                        logger.fine("Allocation Amount=" + amount
                                + " - Remaining  Applied=" + AppliedAmt
                                + ", Payment=" + PaymentAmt);

                        amountList.set(paymentIndex, PaymentAmt); // update
                    } // for all applied amounts
                } // noop through payments for invoice

                // No Payments allocated and none existing (e.g. Inv/CM)
                if ((noPayments == 0) && (paymentList.size() == 0))
                {
                    int C_Payment_ID = 0;

                    logger.config(" ... no payment - TotalApplied="
                            + totalAppliedAmt);

                    // Begin Enable MultiAllocation Fabian Aguilar OFBConsulting
                    if (m_eIndicator == EIndicator.GREATER_CREDIT)
                    {
                        if (rowIndex == m_creditIndex)
                        {
                            AppliedAmt = AppliedAmt.negate();
                        }
                    }
                    // --------END------------------------

                    // Create Allocation

                    logger.fine("C_Payment_ID=" + C_Payment_ID
                            + ", C_Invoice_ID=" + C_Invoice_ID + ", Amount="
                            + AppliedAmt + ", Discount=" + DiscountAmt
                            + ", WriteOff=" + WriteOffAmt);

                    // Allocation Header
                    if ((alloc.get_ID() == 0) && !alloc.save())
                    {
                        logger.log(Level.SEVERE, "Allocation not created");
                        return;
                    }
                    // Allocation Line
                    MAllocationLine aLine = new MAllocationLine(alloc,
                            AppliedAmt, DiscountAmt, WriteOffAmt, OverUnderAmt);
                    aLine.setDocInfo(C_BPartner_ID, C_Order_ID, C_Invoice_ID);
                    aLine.setPaymentInfo(C_Payment_ID, C_CashLine_ID);
                    if (!aLine.save(trx.getTrxName()))
                    {
                        logger.log(Level.SEVERE,
                                "Allocation Line not written - Invoice="
                                        + C_Invoice_ID);
                    }

                    logger.fine("Allocation Amount=" + AppliedAmt);
                }
                totalAppliedAmt = totalAppliedAmt.add(AppliedAmt);
                logger.config("TotalRemaining=" + totalAppliedAmt);
            } // invoice selected
        } // invoice loop

        // Only Payments and total of 0 (e.g. Payment/Reversal)
        if ((invoiceLines == 0) && (paymentList.size() > 0)
                && (isZero(paymentAppliedAmt)))
        {
            for (int paymentIndex = 0; paymentIndex < paymentList.size(); paymentIndex++)
            {
                int C_Payment_ID = ((Integer) paymentList.get(paymentIndex))
                        .intValue();
                BigDecimal paymentAmt = (BigDecimal) amountList.get(paymentIndex);

                logger.fine("Payment=" + C_Payment_ID + ", Amount="
                        + paymentAmt); // + ", Abs=" + PaymentAbs);

                // Allocation Header
                if ((alloc.get_ID() == 0) && !alloc.save())
                {
                    logger.log(Level.SEVERE, "Allocation not created");
                    return;
                }

                // Allocation Line
                MAllocationLine aLine = new MAllocationLine(alloc, paymentAmt,
                        Env.ZERO, Env.ZERO, Env.ZERO);
                aLine.setDocInfo(C_BPartner_ID, 0, 0);
                aLine.setPaymentInfo(C_Payment_ID, 0);

                if (!aLine.save(trx.getTrxName()))
                {
                    logger.log(Level.SEVERE,
                            "Allocation Line not saved - Payment="
                                    + C_Payment_ID);
                }
            }
        } // onlyPayments

        if (isNonZero(totalAppliedAmt))
        {
            logger.log(Level.SEVERE, "Remaining TotalAppliedAmt="
                    + totalAppliedAmt);
        }

        // Should start WF
        if (alloc.get_ID() != 0)
        {
            alloc.processIt(DocAction.ACTION_Complete);
            alloc.save();
        }

        // Test/Set IsPaid for Invoice - requires that allocation is posted
        for (int rowIndex = 0; rowIndex < invoiceRows; rowIndex++)
        {
            // Invoice line is selected
            if (isRowSelected(m_lsbInvoices, rowIndex))
            {
                KeyNamePair pp = (KeyNamePair) m_lsbInvoices.getValueAt(
                        rowIndex, 2); // Value
                // Invoice variables
                int C_Invoice_ID = pp.getKey();
                String sql = "SELECT invoiceOpen(C_Invoice_ID, 0) "
                        + "FROM C_Invoice " + "WHERE C_Invoice_ID=?";

                BigDecimal open = DB.getSQLValueBD(trx.getTrxName(), sql,
                        C_Invoice_ID);

                // Enable MultiAllocation Fabian Aguilar OFBConsulting
                if ((open != null)
                        && (isZero(open))
                        && (m_eIndicator != EIndicator.SUBPAYMENT_SO)
                        && (m_eIndicator != EIndicator.GREATER_INVOICED_PO)
                        && ((m_eIndicator != EIndicator.CREDIT_MEMO)
                                && (getRowInvoice() != rowIndex)))
                {
                    sql = "UPDATE C_Invoice " + "SET IsPaid='Y' "
                            + "WHERE C_Invoice_ID=" + C_Invoice_ID;

                    int noAffectedRows = DB.executeUpdate(sql, trx.getTrxName());

                    logger.config("Invoice #" + rowIndex
                            + " is paid - updated=" + noAffectedRows);
                }
                else
                {
                    logger.config("Invoice #" + rowIndex + " is not paid - "
                            + open);
                }
            }
        }

        // Test/Set Payment is fully allocated
        for (int paymentIndex = 0; paymentIndex < paymentList.size(); paymentIndex++)
        {
            int C_Payment_ID = ((Integer) paymentList.get(paymentIndex)).intValue();
            MPayment pay = new MPayment(Env.getCtx(), C_Payment_ID, trx.getTrxName());

            // Enable MultiAllocation Fabian Aguilar OFBConsulting
            if ((pay.testAllocation() || (m_eIndicator == EIndicator.SUBPAYMENT_SO))
                    && (m_eIndicator != EIndicator.GREATER_PAYMENT_SO)
                    && (m_eIndicator != EIndicator.GREATER_PAYMENT_PO))
            {
                pay.save();
            }

            logger
                    .config("Payment #" + paymentIndex
                            + (pay.isAllocated() ? " not" : " is")
                            + " fully allocated");
        }

        paymentList.clear();
        amountList.clear();
        trx.commit();
        trx.close();

        return;
    } // saveData

} // WAllocation

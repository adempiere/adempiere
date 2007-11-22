/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 * Search Invoice and return selection
 * Based on InfoInvoice by Jorg Janke
 * @author Sendy Yagambrum
 * @date July 30, 2007
 **/
public class InfoInvoicePanel extends InfoPanel implements ValueChangeListener
{
    /**
     * Detail protected constructor
     * @param WindowNo window no
     * @param value query value
     * @param multiSelection multiple selection
     * @param whereClause where clause
    *
     */
    protected InfoInvoicePanel(int WindowNo, String value,
            boolean multiSelection, String whereClause)
    {
        super ( WindowNo, "i", "C_Invoice_ID", multiSelection, whereClause);
        
        setTitle(Msg.getMsg(Env.getCtx(), "InfoInvoice"));
        //
        initComponents();
        init();
           
       p_loadedOK = initInfo ();
       int no = contentPanel.getRowCount();
       setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
       setStatusDB(Integer.toString(no));
       if (value != null && value.length() > 0)
       {
           String values[] = value.split("_");
           txtDocumentNo.setText(values[0]);
           executeQuery();
           renderItems();
       }
    }

    private Label lblDocumentNo;
    private Label lblDescription;
    private Label lblBPartner;
    private Label lblOrder;
    private Label lblIsSOTrx;
    private Label lblIsPaid;
    private Label lblDateInvoiced;
    private Label lblGrandTotal;
    
    private Textbox txtDocumentNo;
    private Textbox txtDescription;
    
    private Datebox dateFrom;
    private Datebox dateTo;
    
    private NumberBox amountFrom;
    private NumberBox amountTo;
    
    private WSearchEditor editorBPartner;
    private WSearchEditor editorOrder;
    
    private Checkbox isSoTrx;
    private Checkbox isPaid;
    
    /**  Array of Column Info    */
    private static final ColumnInfo[] s_invoiceLayout = {
        new ColumnInfo(" ", "i.C_Invoice_ID", IDColumn.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "DateInvoiced"), "i.DateInvoiced", Timestamp.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=i.C_Currency_ID)", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "i.GrandTotal",  BigDecimal.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(i.GrandTotal, i.C_Currency_ID, i.DateAcct, i.AD_Client_ID, i.AD_Org_ID)", BigDecimal.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "OpenAmt"), "invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID)", BigDecimal.class, true, true, null),
        new ColumnInfo(Msg.translate(Env.getCtx(), "IsPaid"), "i.IsPaid", Boolean.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "IsSOTrx"), "i.IsSOTrx", Boolean.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
        new ColumnInfo("", "''", KeyNamePair.class, "i.C_InvoicePaySchedule_ID")
    };
    
    private static int INDEX_PAYSCHEDULE = s_invoiceLayout.length - 1;  //  last item
   
    private static final long serialVersionUID = 1L;

    private void initComponents()
    {

        lblDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo").substring(1));
        lblDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
        lblBPartner = new Label(Msg.translate(Env.getCtx(), "BPartner").substring(1));
        lblIsSOTrx = new Label(Msg.translate(Env.getCtx(), "IsSOTrx"));
        lblIsPaid = new Label(Msg.translate(Env.getCtx(), "IsPaid"));
        lblDateInvoiced = new Label(Msg.translate(Env.getCtx(), "DateInvoiced"));
        lblOrder = new Label(Msg.translate(Env.getCtx(), "POReference"));
        lblGrandTotal = new Label(Msg.translate(Env.getCtx(), "GrandTotal"));
        
        txtDocumentNo = new Textbox();
        txtDescription = new Textbox();
        
        dateFrom = new Datebox();
        dateFrom.setWidth("180px");
        dateTo= new Datebox();
        dateTo.setWidth("180px");
        
        amountFrom = new NumberBox(false);
        amountFrom.setWidth("180px");
        amountTo = new NumberBox(false);
        amountTo.setWidth("180px");
        
        isPaid = new Checkbox();
        isPaid.setChecked(false);
        isSoTrx = new Checkbox();
        isSoTrx.setChecked(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
        MLookup lookupBP = MLookupFactory.get(Env.getCtx(), p_WindowNo,
                0, 3499, DisplayType.Search);
        editorBPartner = new WSearchEditor(lookupBP, Msg.translate(
                Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
        editorBPartner.addValueChangeListner(this);
        
        MLookup lookupOrder = MLookupFactory.get(Env.getCtx(), p_WindowNo,
                0, 4247, DisplayType.Search);
        editorOrder = new WSearchEditor(lookupOrder, Msg.translate(
                Env.getCtx(), "C_Order_ID"), "", false, false, true);
        editorOrder.addValueChangeListner(this);
        
        contentPanel = new WListbox();
        contentPanel.setWidth("1300px");
        contentPanel.setHeight("500px");
    }
    
    private void init()
    {
        Hbox pnlDocumentNo = new Hbox();
        pnlDocumentNo.appendChild(lblDocumentNo);
        pnlDocumentNo.appendChild(txtDocumentNo);
        pnlDocumentNo.setStyle("text-align:right");
        
        Hbox pnlDescription = new Hbox();
        pnlDescription.appendChild(lblDescription);
        pnlDescription.appendChild(txtDescription);
        pnlDescription.setStyle("text-align:right");
        pnlDescription.setWidth("100%");
        
        Hbox pnlOrder = new Hbox();
        pnlOrder.appendChild(editorOrder.getLabel());
        pnlOrder.appendChild(editorOrder.getComponent());
        pnlOrder.setStyle("text-align:right");
        pnlOrder.setWidth("100%");
        
        Hbox pnlBPartner = new Hbox();
        pnlBPartner.appendChild(lblBPartner);
        pnlBPartner.appendChild(editorBPartner.getComponent()); 
        pnlBPartner.setStyle("text-align:right");
        pnlBPartner.setWidth("100%");
        
        Hbox hboxDateOrdered = new Hbox();
        Panel pnlDateOrdered = new Panel();
        pnlDateOrdered.appendChild(lblDateInvoiced);
        pnlDateOrdered.appendChild(dateFrom);
        pnlDateOrdered.setAlign("right");
        hboxDateOrdered.appendChild(pnlDateOrdered);
        hboxDateOrdered.setStyle("text-align:right");
        hboxDateOrdered.setWidth("100%");
        
        Hbox pnlGrandTotal = new Hbox();
        pnlGrandTotal.appendChild(lblGrandTotal);
        pnlGrandTotal.appendChild(amountFrom);
        pnlGrandTotal.setStyle("text-align:right");
        pnlGrandTotal.setWidth("100%");
        
        Hbox pnlCheckbox = new Hbox();
        Panel pnlIsSoTrx = new Panel();
        pnlIsSoTrx.appendChild(isSoTrx);
        pnlIsSoTrx.appendChild(lblIsSOTrx);
        pnlIsSoTrx.setAlign("left");
        
        Panel pnlIsPaid = new Panel();
        pnlIsPaid.appendChild(isPaid);
        pnlIsPaid.appendChild(lblIsPaid);
        pnlIsPaid.setAlign("left");
        
        pnlCheckbox.appendChild(pnlIsSoTrx);
        pnlCheckbox.appendChild(pnlIsPaid);
        

        Panel pnlDateTo = new Panel();
        pnlDateTo.appendChild(dateTo);
        pnlDateTo.setAlign("left");
        
        Panel pnlAmountTo = new Panel();
        pnlAmountTo.appendChild(amountTo);     
        pnlAmountTo.setAlign("left");
        
        Vbox vbox1 = new Vbox();
        vbox1.setWidth("100%");
        vbox1.appendChild(pnlDocumentNo);
        vbox1.appendChild(pnlDescription);
        vbox1.appendChild(pnlOrder);
        
        Vbox vbox2 = new Vbox();
        vbox2.setWidth("100%");
        vbox2.appendChild(pnlBPartner);
        vbox2.appendChild(pnlDateOrdered);
        vbox2.appendChild(pnlGrandTotal);
        
        Vbox vbox3 = new Vbox();
        vbox3.setWidth("100%");
        vbox3.appendChild(pnlCheckbox);
        vbox3.appendChild(pnlDateTo);
        vbox3.appendChild(pnlAmountTo);
        
        Hbox parameterPanel = new Hbox();
        parameterPanel.appendChild(vbox1);
        parameterPanel.appendChild(vbox2);
        parameterPanel.appendChild(vbox3);
        parameterPanel.setWidth("100%");
        
        Vbox mainPanel = new Vbox();
        mainPanel.setWidth("100%");
        mainPanel.appendChild(parameterPanel);
        Div div = new Div();
        div.setStyle("overflow:scroll");
        div.setWidth("100%");
        div.appendChild(contentPanel);
        mainPanel.appendChild(div);
        mainPanel.appendChild(confirmPanel);
        mainPanel.appendChild(statusBar);
        
        this.appendChild(mainPanel);
        this.setBorder("normal");
        this.setWidth("850px");         
    }
    
    /**
     *  General Init
     *  @return true, if success
     */
    private boolean initInfo ()
    {
        //  Set Defaults
        String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
        if (bp != null && bp.length() != 0)
            editorBPartner.setValue(new Integer(bp));

        //  prepare table
        StringBuffer where = new StringBuffer("i.IsActive='Y'");
        if (p_whereClause.length() > 0)
            where.append(" AND ").append(Util.replace(p_whereClause, "C_Invoice.", "i."));
        prepareTable(s_invoiceLayout,
            " C_Invoice_v i",   //  corrected for CM
            where.toString(),
            "2,3,4");
        //
    //  MAllocationLine.setIsPaid(Env.getCtx(), 0, null);
        return true;
           
    }   //  initInfo
    @Override
    public String getSQLWhere()
    {
        StringBuffer sql = new StringBuffer();
        if (txtDocumentNo.getText().length() > 0)
            sql.append(" AND UPPER(i.DocumentNo) LIKE ?");
        if (txtDescription.getText().length() > 0)
            sql.append(" AND UPPER(i.Description) LIKE ?");
    //  if (fPOReference.getText().length() > 0)
    //      sql.append(" AND UPPER(i.POReference) LIKE ?");
        //
        if (editorBPartner.getValue() != null)
            sql.append(" AND i.C_BPartner_ID=?");
        //
        if (editorOrder.getValue() != null)
            sql.append(" AND i.C_Order_ID=?");
        Date fromDate = null;
        Date toDate = null;
        try
        {
            fromDate = dateFrom.getValue();
        }
        catch (WrongValueException e)
        {
            
        }
        try
        {
            toDate = dateTo.getValue();
        }
        catch (WrongValueException e)
        {
            
        }
        if (fromDate == null && toDate != null)
        {
            sql.append(" AND TRUNC(o.DateOrdered) <= ?");
        }
        else if (fromDate != null && toDate == null)
        {
            sql.append(" AND TRUNC(o.DateOrdered) >= ?");
        }
        else if (fromDate != null && toDate != null)
        {    
                sql.append(" AND TRUNC(o.DateOrdered) BETWEEN ? AND ?");
        }
        //
        Double fromAmount = null;
        Double toAmount = null;
        if (!amountFrom.getText().equals(""))
        {
            try
            {
                fromAmount = Double.parseDouble(amountFrom.getText());
            }
            catch (NumberFormatException e)
            {
                
            }
        }
        if (!amountTo.getText().equals(""))
        {
            try
            {
                toAmount = Double.parseDouble(amountTo.getText());
            }
            catch (NumberFormatException e)
            {
                
            }
        }
        if (fromAmount == null && toAmount != null)
        {
            sql.append(" AND o.GrandTotal <= ?");
        }
        else if (fromAmount != null && toAmount == null)
        {
            sql.append(" AND o.GrandTotal >= ?");
        }
        else if (fromAmount != null && toAmount != null)
        {
              sql.append(" AND o.GrandTotal BETWEEN ? AND ?");
        }
        sql.append(" AND i.IsPaid=? AND i.IsSOTrx=?");

        log.finer(sql.toString());
        return sql.toString();
    }

    @Override
    void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
    {
        int index = 1;
        if (txtDocumentNo.getText().length() > 0)
            pstmt.setString(index++, getSQLText(txtDocumentNo));
        if (txtDescription.getText().length() > 0)
            pstmt.setString(index++, getSQLText(txtDescription));
        
        //
        if (editorBPartner.getValue() != null)
        {
            Integer bp = (Integer)editorBPartner.getValue();
            pstmt.setInt(index++, bp.intValue());
            log.fine("BPartner=" + bp);
        }
        //
        if (editorOrder.getValue() != null)
        {
            Integer order = (Integer)editorOrder.getValue();
            pstmt.setInt(index++, order.intValue());
            log.fine("Order=" + order);
        }
        Date fromD = null;
        Date toD = null;
        Timestamp from = null;
        Timestamp to = null;
        try
        {
            if (dateFrom.getValue() != null)
            {
                fromD = dateFrom.getValue();
                from = new Timestamp(fromD.getTime());
            }
        }
        catch (WrongValueException e)
        {
            
        }
        try
        {
            if (dateTo.getValue() != null)
            {
                toD = dateTo.getValue();
                to = new Timestamp(toD.getTime());
            }
        }
        catch (WrongValueException e)
        {
            
        }
        
        log.fine("Date From=" + from + ", To=" + to);
        if (from == null && to != null)
        {
            pstmt.setTimestamp(index++, to);
        }
        else if (from != null && to == null)
        {
            pstmt.setTimestamp(index++, from);
        }
        else if (from != null && to != null)
        {
            pstmt.setTimestamp(index++, from);
            pstmt.setTimestamp(index++, to);
        }
    
    //
        BigDecimal fromBD = null;
        BigDecimal toBD = null;
        Double fromAmt = null;
        Double toAmt = null;
        
        if (!amountFrom.getText().equals(""))
        {
            try
            {
                fromAmt = Double.parseDouble(amountFrom.getText());
                fromBD = BigDecimal.valueOf(fromAmt);
            }
            catch (Exception e)
            {
                
            }
        }
        
        if (!amountTo.getText().equals(""))
        {
            try
            {
                toAmt = Double.parseDouble(amountTo.getText());
                toBD = BigDecimal.valueOf(toAmt);
            }
            catch (Exception e)
            {
                
            }
        }
        
        if (fromBD == null && toBD != null)
        {
            pstmt.setBigDecimal(index++, toBD);
        }
        else if (fromBD != null && toBD == null)
        {
            pstmt.setBigDecimal(index++, fromBD);
        }
        else if (fromBD != null && toBD != null)
        {
              pstmt.setBigDecimal(index++, fromBD);
              pstmt.setBigDecimal(index++, toBD);
        }
        pstmt.setString(index++,isPaid.isChecked() ? "Y" : "N");
        pstmt.setString(index++,isSoTrx.isChecked() ? "Y" : "N");
       
    }

    /**
     *  Get SQL WHERE parameter
     *  @param f field
     *  @return sql
     */
    private String getSQLText (Textbox f)
    {
        String s = f.getText().toUpperCase();
        if (!s.endsWith("%"))
            s += "%";
        log.fine("String=" + s);
        return s;
    }   //  getSQLText
    
    public void tableChanged(WTableModelEvent event)
    {
        
    }
    public void valueChange(ValueChangeEvent evt)
    {
        if (editorBPartner.equals(evt.getSource()))
        {
            editorBPartner.setValue(evt.getNewValue());
        }
        if (editorOrder.equals(evt.getSource()))
        {
            editorOrder.setValue(evt.getNewValue());
        }
    }

}

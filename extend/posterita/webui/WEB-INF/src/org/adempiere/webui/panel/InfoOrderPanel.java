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
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 * Search Order info and return selection
 * Based on InfoOrder by Jorg Janke
 * 
 * @author Sendy Yagambrum
 * @date July 27, 2007
 **/
public class InfoOrderPanel extends InfoPanel implements ValueChangeListener
{

    private static final long serialVersionUID = 1L;
   
    private Label lblDocumentNo;
    private Label lblDescription;
    private Label lblBPartner;
    private Label lblSalesTransaction;
    private Label lblDateOrdered;
    private Label lblOrderRef;
    private Label lblGrandTotal;
    
    private Textbox txtDocumentNo;
    private Textbox txtDescription;
    private Textbox txtOrderRef;
    
    private Datebox dateFrom;
    private Datebox dateTo;
    
    private NumberBox amountFrom;
    private NumberBox amountTo;
    
    private WSearchEditor editorBPartner;
    
    private Checkbox isSoTrx;
   
    /**  Array of Column Info    */
    private static final ColumnInfo[] s_invoiceLayout = {
        new ColumnInfo(" ", "o.C_Order_ID", IDColumn.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=o.C_BPartner_ID)", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "DateOrdered"), "o.DateOrdered", Timestamp.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "o.DocumentNo", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=o.C_Currency_ID)", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "o.GrandTotal",  BigDecimal.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(o.GrandTotal,o.C_Currency_ID,o.DateAcct, o.AD_Client_ID,o.AD_Org_ID)", BigDecimal.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "IsSOTrx"), "o.IsSOTrx", Boolean.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "o.Description", String.class),
        new ColumnInfo(Msg.translate(Env.getCtx(), "POReference"), "o.POReference", String.class)
    };
    
    protected InfoOrderPanel(int WindowNo, String value,
            boolean multiSelection, String whereClause)
    {
            super ( WindowNo, "o", "C_Order_ID", multiSelection, whereClause);
            log.info( "InfoOrder");
            setTitle(Msg.getMsg(Env.getCtx(), "InfoOrder"));
            //
            initComponents();
            init();
           
            p_loadedOK = initInfo ();
            int no = contentPanel.getRowCount();
            setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
            setStatusDB(Integer.toString(no));
            //
            if (value != null && value.length() > 0)
            {
                String values[] = value.split("_");
                txtDocumentNo.setText(values[0]);
                executeQuery();
                renderItems();
            }
    }
    public void initComponents()
    {

        lblDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo").substring(1));
        lblDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
        lblBPartner = new Label(Msg.translate(Env.getCtx(), "BPartner").substring(1));
        lblSalesTransaction = new Label(Msg.translate(Env.getCtx(), "IsSOTrx"));
        lblDateOrdered = new Label(Msg.translate(Env.getCtx(), "DateOrdered"));
        lblOrderRef = new Label(Msg.translate(Env.getCtx(), "POReference"));
        lblGrandTotal = new Label(Msg.translate(Env.getCtx(), "GrandTotal"));
        
        txtDocumentNo = new Textbox();
        txtDescription = new Textbox();
        txtOrderRef = new Textbox();
        
        dateFrom = new Datebox();
        dateFrom.setWidth("180px");
        dateTo= new Datebox();
        dateTo.setWidth("180px");
        
        amountFrom = new NumberBox(false);
        amountFrom.setWidth("180px");
        amountTo = new NumberBox(false);
        amountTo.setWidth("180px");
        
        isSoTrx = new Checkbox();
        isSoTrx.setChecked(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
        MLookup lookupBP = MLookupFactory.get(Env.getCtx(), p_WindowNo,
                0, 3499, DisplayType.Search);
        editorBPartner = new WSearchEditor(lookupBP, Msg.translate(
                Env.getCtx(), "C_BPartner_ID"), "", true, false, true);
        editorBPartner.addValueChangeListner(this);
        contentPanel = new WListbox();
        contentPanel.setWidth("1000px");
        contentPanel.setHeight("500px");
    }
    
    public void init()
    {
        Panel pnlDocumentNo = new Panel();
        pnlDocumentNo.appendChild(lblDocumentNo);
        pnlDocumentNo.appendChild(txtDocumentNo);
        pnlDocumentNo.setAlign("right");
        
        Panel pnlDescription = new Panel();
        pnlDescription.appendChild(lblDescription);
        pnlDescription.appendChild(txtDescription);
        pnlDescription.setAlign("right");
        
        Panel pnlOrderRef = new Panel();
        pnlOrderRef.appendChild(lblOrderRef);
        pnlOrderRef.appendChild(txtOrderRef);
        pnlOrderRef.setAlign("right");
        
        Hbox pnlBPartner = new Hbox();
        pnlBPartner.appendChild(lblBPartner);
        pnlBPartner.appendChild(editorBPartner.getComponent()); 
        pnlBPartner.setStyle("text-align:right");
        pnlBPartner.setWidth("100%");
        
        Hbox hboxDateOrdered = new Hbox();
        Panel pnlDateOrdered = new Panel();
        pnlDateOrdered.appendChild(lblDateOrdered);
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
        Panel pnlIsSoTrx = new Panel();
        
        pnlIsSoTrx.appendChild(isSoTrx);
        pnlIsSoTrx.appendChild(lblSalesTransaction);
        pnlIsSoTrx.setAlign("left");

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
        vbox1.appendChild(pnlOrderRef);
        
        Vbox vbox2 = new Vbox();
        vbox2.setWidth("100%");
        vbox2.appendChild(pnlBPartner);
        vbox2.appendChild(pnlDateOrdered);
        vbox2.appendChild(pnlGrandTotal);
        
        Vbox vbox3 = new Vbox();
        vbox3.setWidth("100%");
        vbox3.appendChild(pnlIsSoTrx);
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
        div.setStyle("overflow:auto");
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
        StringBuffer where = new StringBuffer("o.IsActive='Y'");
        if (p_whereClause.length() > 0)
            where.append(" AND ").append(Util.replace(p_whereClause, "C_Order.", "o."));
       prepareTable(s_invoiceLayout,
            " C_Order o",
            where.toString(),"2,3,4");

        return true;
    }   //  initInfo
    @Override
    public String getSQLWhere()
    {
        StringBuffer sql = new StringBuffer();
        if (txtDocumentNo.getText().length() > 0)
            sql.append(" AND UPPER(o.DocumentNo) LIKE ?");
        if (txtDescription.getText().length() > 0)
            sql.append(" AND UPPER(o.Description) LIKE ?");
        if (txtOrderRef.getText().length() > 0)
            sql.append(" AND UPPER(o.POReference) LIKE ?");
        //
        if (editorBPartner.getValue() != null)
            sql.append(" AND o.C_BPartner_ID=?");
        //
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
        sql.append(" AND o.IsSOTrx=?");

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
        if (txtOrderRef.getText().length() > 0)
            pstmt.setString(index++, getSQLText(txtOrderRef));
        //
        if (editorBPartner.getValue() != null)
        {
            Integer bp = (Integer)editorBPartner.getValue();
            pstmt.setInt(index++, bp.intValue());
            log.fine("BPartner=" + bp);
        }
        //
        
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
        
        pstmt.setString(index++, isSoTrx.isChecked() ? "Y" : "N");
        
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
        // TODO Auto-generated method stub
        
    }
    
    public void valueChange(ValueChangeEvent evt)
    {
        if (editorBPartner.equals(evt.getSource()))
        {
            editorBPartner.setValue(evt.getNewValue());
        }
        
    }

}

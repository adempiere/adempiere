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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
 * Match PO-Invoice-Receipt Custom Form : Based on VMatch
 * 
 * @author  Niraj Sohun
 * @date    Jul 2, 2007
 */

public class WMatch extends ADForm implements EventListener, ValueChangeListener, WTableModelListener
{
	private static final long serialVersionUID = 1L;
	
	private Grid grdParameters;
	private Grid grdMatch;
	private Grid grdProcess;
	
	private Rows rows;
	private Row row;
	
	private Listbox lstInvoice;
	private Listbox lstReceipt;
	
	private Button cmdSearch;
	private Button cmdProcess;
	
	private Datebox dateFrom;
	private Datebox dateTo;
	
	private Listbox lstMatchFrom;
	private Listbox lstMatchTo;
	private Listbox lstSearchMode;
		
	private Checkbox chkSameBP;
	private Checkbox chkSameProduct;
	private Checkbox chkSameQty;
	
	private Textbox txtToBeMatched;
	private Textbox txtMatching;
	private Textbox txtDifference;
	
	private Label lblMatchFrom;
	private Label lblMatchTo;
	private Label lblStatus;
	
	private String[] m_matchOptions = new String[] {
			Msg.getElement(Env.getCtx(), "C_Invoice_ID", false),
			Msg.getElement(Env.getCtx(), "M_InOut_ID", false),
			Msg.getElement(Env.getCtx(), "C_Order_ID", false) };
	
	private static final int MATCH_INVOICE = 0;
	private static final int MATCH_SHIPMENT = 1;
	private static final int MATCH_ORDER = 2;

	private String[] m_matchMode = new String[] { 
			Msg.translate(Env.getCtx(), "NotMatched"),
			Msg.translate(Env.getCtx(), "Matched")};
	
	private static final int MODE_NOTMATCHED = 0;
	private static final int MODE_MATCHED = 1;

	private static final int I_BPartner = 3;
	private static final int I_Line = 4;
	private static final int I_Product = 5;
	private static final int I_QTY = 6;
	private static final int I_MATCHED = 7;

	private StringBuffer m_sql = null;
	private String m_dateColumn = "";
	private String m_qtyColumn = "";
	private String m_groupBy = "";

	private BigDecimal m_xMatched = Env.ZERO;
	private BigDecimal m_xMatchedTo = Env.ZERO;
	
	private WEditor bPartnerSearch = null;
	private WEditor productSearch = null;

	//private int m_C_BPartner_ID;
	//private int productID;
	
	private WListbox xMatchedTable;
	private WListbox xMatchedToTable;
	
	@SuppressWarnings("unused")
	private String strMatchedTable;
	
	@SuppressWarnings("unused")
	private String strMatchedToTable;

	private static CLogger log = CLogger.getCLogger(WMatch.class);
	
	public WMatch()
	{
		init();
		initComponents();
	}
	
	private void init()
	{
		try
		{
			new Thread()
			{
				public void run()
				{
					log.info("Starting ...");
					MMatchPO.consolidate(Env.getCtx());
					log.info("... Done");
				}
			}.start();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}		
		
		grdParameters = new Grid();
		grdParameters.setWidth("700px");
		
		grdMatch = new Grid();
		grdMatch.setWidth("700px");
		
		grdProcess = new Grid();
		grdProcess.setWidth("700px");
		
		lstInvoice = new Listbox();
		lstInvoice.setWidth("700px");
   
		lstReceipt = new Listbox();
		lstReceipt.setWidth("700px");
		
		cmdSearch = new Button();
		cmdSearch.setImage("/images/FindX24.gif");
		cmdSearch.addEventListener(Events.ON_CLICK, this);
		
		cmdProcess = new Button();
		cmdProcess.setImage("/images/Process24.gif");
		cmdProcess.addEventListener(Events.ON_CLICK, this);
		
		dateFrom = new Datebox();
		dateTo = new Datebox();
		
		lstMatchFrom = new Listbox();
		lstMatchFrom.setRows(0);
        lstMatchFrom.setMold("select");
        lstMatchFrom.addEventListener(Events.ON_SELECT, this);
        
		lstMatchTo = new Listbox();
		lstMatchTo.setRows(0);
        lstMatchTo.setMold("select");
        lstMatchTo.addEventListener(Events.ON_SELECT, this);
        
		lstSearchMode = new Listbox();
		lstSearchMode.setRows(1);
        lstSearchMode.setMold("select");
        //lstSearchMode.addEventListener(Events.ON_SELECT, this);
        
        chkSameBP = new Checkbox();
        chkSameBP.setLabel("Same Business Partner");
        chkSameBP.setChecked(true);
        chkSameBP.addEventListener(Events.ON_CHECK, this);
        
        chkSameProduct = new Checkbox();
        chkSameProduct.setLabel("Same Product");
        chkSameProduct.setChecked(true);
        chkSameProduct.addEventListener(Events.ON_CHECK, this);
        
        chkSameQty = new Checkbox();
        chkSameQty.setLabel("Same Quantity");
        chkSameQty.setChecked(false);
        chkSameQty.addEventListener(Events.ON_CHECK, this);
        
        txtToBeMatched = new Textbox();
        txtToBeMatched.setEnabled(false);
        
        txtMatching = new Textbox();
        txtMatching.setEnabled(false);
        
        txtDifference = new Textbox();
        txtDifference.setEnabled(false);
        
        lblMatchFrom = new Label(" ");
        
        lblMatchTo = new Label(" ");
        
        lblStatus = new Label("Invoice#");
        lblStatus.setWidth("700px");
        
        xMatchedTable = new WListbox();
        xMatchedTable.setWidth("700px");
        xMatchedTable.setHeight("150px");
        xMatchedTable.getModel().addTableModelListener(this);
        xMatchedTable.addEventListener(Events.ON_SELECT, this);
        
        xMatchedToTable = new WListbox();
        xMatchedToTable.setWidth("700px");
        xMatchedToTable.setHeight("150px");
        xMatchedToTable.getModel().addTableModelListener(this);
        xMatchedToTable.addEventListener(Events.ON_SELECT, this);
	}
	
	private void initComponents()
	{
		this.setWidth("710px");
		this.setHeight("100%");
		//this.setBorder("normal");
		
		rows = new Rows();
		
		// Row 1
		row = new Row();
		row.appendChild(new Label("Match From"));
		row.appendChild(lstMatchFrom);
		row.appendChild(new Label("Match To"));
		row.appendChild(lstMatchTo);
		rows.appendChild(row);
		
		// Row 2
		row = new Row();
		row.appendChild(new Label("Search Mode"));
		row.appendChild(lstSearchMode);
		rows.appendChild(row);
		
		// Row 3
		row = new Row();
		row.appendChild(new Label("Business Partner"));
		showBusinessPartner();
		row.appendChild(bPartnerSearch.getComponent());
		row.appendChild(new Label("Product"));
		showProduct();
		row.appendChild(productSearch.getComponent());
		rows.appendChild(row);
		
		// Row 4
		row = new Row();
		row.appendChild(new Label("Date From"));
		row.appendChild(dateFrom);
		row.appendChild(new Label("Date To"));
		row.appendChild(dateTo);
		rows.appendChild(row);
		
		grdParameters.appendChild(rows);
		this.appendChild(grdParameters);
		this.appendChild(new Separator());

		Hbox hbox = new Hbox();
		hbox.appendChild(cmdSearch);
		hbox.appendChild(cmdProcess);
		
		this.appendChild(hbox);
		this.appendChild(new Separator());
		
		// Listbox Invoice
		
		this.appendChild(new Label(" "));
		this.appendChild(lblMatchFrom);
		this.appendChild(new Separator());
		this.appendChild(new Label(" "));
		this.appendChild(xMatchedTable);
		this.appendChild(new Separator());
		
		// Match Parameters
		
		rows = new Rows();
		
		this.appendChild(new Label(" "));
		row = new Row();
		row.appendChild(chkSameBP);
		row.appendChild(chkSameProduct);
		row.appendChild(chkSameQty);
		rows.appendChild(row);
		
		grdMatch.appendChild(rows);
		this.appendChild(grdMatch);
		this.appendChild(new Separator());
				
		// Listbox Receipt
		this.appendChild(new Label(" "));
		this.appendChild(lblMatchTo);
		this.appendChild(new Separator());
		this.appendChild(new Label(" "));
		this.appendChild(xMatchedToTable);
		this.appendChild(new Separator());
		
		// Process Parameters
		
		rows = new Rows();
		
		row = new Row();
		row.appendChild(new Label("To Be Matched"));
		row.appendChild(txtToBeMatched);
		row.appendChild(new Label("Matching"));
		row.appendChild(txtMatching);
		row.appendChild(new Label("Difference"));
		row.appendChild(txtDifference);
		rows.appendChild(row);
		
		grdProcess.appendChild(rows);
		this.appendChild(grdProcess);
		
		this.appendChild(new Separator());
		this.appendChild(lblStatus);
		
		populateMatchFrom();
		populateMatchTo();
		populateSearchMode();
		prepareTable();
	}
	
	private void populateMatchFrom()
	{
		for (int i = 0; i < m_matchOptions.length; i++)
		{
			String temp = m_matchOptions[i]; 
			lstMatchFrom.appendItem(temp, temp);
		}
		
		lstMatchFrom.setSelectedIndex(0);
		lblMatchFrom.setValue(m_matchOptions[0]);
	}
	
	private void populateMatchTo()
	{
		lstMatchTo.getChildren().clear();
		
		ListItem lstIteMatchFromSelection = lstMatchFrom.getSelectedItem();
		String selection = (String)lstIteMatchFromSelection.getValue();
		
		Vector<String> vector = new Vector<String>(2);
		
		if (selection.equals(m_matchOptions[MATCH_INVOICE]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else if (selection.equals(m_matchOptions[MATCH_ORDER]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else
		{
			vector.add(m_matchOptions[MATCH_INVOICE]);
			vector.add(m_matchOptions[MATCH_ORDER]);
		}
		
		for (int i = 0; i < vector.size(); i++)
		{
			String temp = vector.get(i).toString();
			lstMatchTo.appendItem(temp, temp);
		}
		
		lstMatchTo.setSelectedIndex(0);
		lblMatchTo.setValue(vector.get(0).toString());
		
		//  Reset Table
		
		//xMatchedTable.setRowCount(0);
		//xMatchedToTable.setRowCount(0);
	}
	
	private void populateSearchMode()
	{
		for (int i = 0; i < m_matchMode.length; i++)
		{
			lstSearchMode.appendItem(m_matchMode[i], m_matchMode[i]);
		}
		
		lstSearchMode.setSelectedIndex(0);
	}
	
	private void showBusinessPartner()
	{
		final int AD_Column_ID = 3499;
		
		MLookup lookupBP = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
				0, AD_Column_ID, DisplayType.Search);
		
		bPartnerSearch = new WSearchEditor(lookupBP, Msg.translate(
				Env.getCtx(), "C_BPartner_ID"), "", true, false, true);

		bPartnerSearch.addValueChangeListner(this);
	}
	
	private void showProduct()
	{
		final int AD_Column_ID = 3840;
		
		MLookup lookupP = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
				0, AD_Column_ID, DisplayType.Search);
		
		productSearch = new WSearchEditor(lookupP, Msg.translate(
				Env.getCtx(), "M_Product_ID"), "", true, false, true);

		productSearch.addValueChangeListner(this);
	}
	
	private void prepareTable()
	{
		ColumnInfo[] layout = new ColumnInfo[] {
				new ColumnInfo(" ",                                         ".", IDColumn.class, false, false, ""),
				new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"),   ".", String.class),             //  1
				new ColumnInfo(Msg.translate(Env.getCtx(), "Date"),         ".", Timestamp.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"),".", KeyNamePair.class, "."),   //  3
				new ColumnInfo(Msg.translate(Env.getCtx(), "Line"),         ".", KeyNamePair.class, "."),
				new ColumnInfo(Msg.translate(Env.getCtx(), "M_Product_ID"), ".", KeyNamePair.class, "."),   //  5
				new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"),          ".", Double.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Matched"),      ".", Double.class)
			};

		strMatchedTable = xMatchedTable.prepareTable(layout, "", "", false, "");
		strMatchedToTable = xMatchedToTable.prepareTable(layout, "", "", true, "");
	}
	
	private void searchRecords()
	{
		int display = lstMatchFrom.getSelectedIndex();
		
		ListItem lstIteMatchTo = lstMatchTo.getSelectedItem();
		String matchToString = (String)lstIteMatchTo.getValue();
		int matchToType = MATCH_INVOICE;
		
		if (matchToString.equals(m_matchOptions[MATCH_SHIPMENT]))
			matchToType = MATCH_SHIPMENT;
		else if (matchToString.equals(m_matchOptions[MATCH_ORDER]))
			matchToType = MATCH_ORDER;

		tableInit(display, matchToType);	//	sets m_sql

		// Where Clause
		// Product
		
		if (productSearch.getDisplay() != "")
		{
			Integer Product = (Integer)productSearch.getValue();
			m_sql.append(" AND lin.M_Product_ID=").append(Product);
		}
		
		//  Business Partner
		
		if (bPartnerSearch.getDisplay() != "")
		{
			Integer Vendor = (Integer)bPartnerSearch.getValue();
			m_sql.append(" AND hdr.C_BPartner_ID=").append(Vendor);
		}
		
		Date f =null;
		Timestamp from  =null;
		
		if (dateFrom.getValue() != null)
		{
			f = dateFrom.getValue();
			from = new Timestamp(f.getTime());
		}
		
		Date t = null;
		Timestamp to = null;

		if (dateTo.getValue() != null)
		{
			t = dateTo.getValue();
			to = new Timestamp(t.getTime());
		}
		
		if (from != null && to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" BETWEEN ")
				.append(DB.TO_DATE(from)).append(" AND ").append(DB.TO_DATE(to));
		else if (from != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" >= ").append(DB.TO_DATE(from));
		else if (to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" <= ").append(DB.TO_DATE(to));

		//  Load Table
		tableLoad (xMatchedTable);
		txtMatching.setText(Env.ZERO.toString());
		
		//  Status Info
		ListItem lstIteMatchFrom = lstMatchFrom.getSelectedItem();
		Integer rowCount = xMatchedTable.getItemCount();
		
		lblStatus.setValue(lstIteMatchFrom.getLabel() + "# = " + rowCount.toString());
		//	xMatchedTable.getRowCount() == 0);
		//statusBar.setStatusDB(0);		
	}
	
	private void process()
	{
		// Matched From
		int matchedRow = xMatchedTable.getSelectedRow();
		
		if (matchedRow < 0)
			return;

		//KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_BPartner);
		KeyNamePair lineMatched = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Line);
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Product);

		//int M_Product_ID = Product.getKey();
		double totalQty = m_xMatched.doubleValue();

		//  Matched To
		for (int row = 0; row < xMatchedToTable.getItemCount(); row++)
		{
			IDColumn id = (IDColumn)xMatchedToTable.getValueAt(row, 0);
			if (id != null && id.isSelected())
			{
				// Need to be the same product
				KeyNamePair ProductCompare = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Product);
				
				if (Product.getKey() != ProductCompare.getKey())
					continue;

				KeyNamePair lineMatchedTo = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Line);

				//	Qty
				double qty = 0.0;
				
				if (lstSearchMode.getSelectedIndex() == MODE_NOTMATCHED)
					qty = ((Double)xMatchedToTable.getValueAt(row, I_QTY)).doubleValue();	// doc
				
				qty -= ((Double)xMatchedToTable.getValueAt(row, I_MATCHED)).doubleValue();  // matched
				
				if (qty > totalQty)
					qty = totalQty;
				
				totalQty -= qty;

				//  Invoice or PO
				boolean invoice = true;
				if (lstMatchFrom.getSelectedIndex() == MATCH_ORDER ||
						lstMatchTo.getSelectedItem().equals(m_matchOptions[MATCH_ORDER]))
					invoice = false;

				//  Get Shipment_ID
				int M_InOutLine_ID = 0;
				int Line_ID = 0;
				
				if (lstMatchFrom.getSelectedIndex() == MATCH_SHIPMENT)
				{
					M_InOutLine_ID = lineMatched.getKey();      //  upper table
					Line_ID = lineMatchedTo.getKey();
				}
				else
				{
					M_InOutLine_ID = lineMatchedTo.getKey();    //  lower table
					Line_ID = lineMatched.getKey();
				}

				//  Create it
				createMatchRecord(invoice, M_InOutLine_ID, Line_ID, new BigDecimal(qty));
			}
		}
		//  Requery
		searchRecords();		
	}

	public void tableChanged(WTableModelEvent e)
	{
		if (e.getColumn() != 0)
			return;
		
		log.config("Row=" + e.getFirstRow() + "-" + e.getLastRow() + ", Col=" + e.getColumn()
			+ ", Type=" + e.getType());
		//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		//  Matched From
		int matchedRow = xMatchedTable.getSelectedRow();
		if (matchedRow == -1)
		{
			return;
		}
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, 5);

		//  Matched To
		double qty = 0.0;
		Integer noRows = 0;
		
		for (int row = 0; row < xMatchedToTable.getItemCount(); row++)
		{
			IDColumn id = (IDColumn)xMatchedToTable.getValueAt(row, 0);
		
			if (id != null && id.isSelected())
			{
				KeyNamePair ProductCompare = (KeyNamePair)xMatchedToTable.getValueAt(row, 5);
				if (Product.getKey() != ProductCompare.getKey())
				{
					id.setSelected(false);
				}
				else
				{
					if (lstSearchMode.getSelectedIndex() == MODE_NOTMATCHED)
						qty += ((Double)xMatchedToTable.getValueAt(row, I_QTY)).doubleValue();  //  doc
					
					qty -= ((Double)xMatchedToTable.getValueAt(row, I_MATCHED)).doubleValue();  //  matched
					noRows++;
				}
			}
		}
		
		//  Update qualtities
		m_xMatchedTo = new BigDecimal(qty);
		txtMatching.setValue(m_xMatchedTo.toString());
		txtDifference.setValue(m_xMatched.subtract(m_xMatchedTo).toString());
		cmdProcess.setEnabled(noRows != 0);
		
		//setCursor(Cursor.getDefaultCursor());
		//  Status
		//lblStatus.setValue(noRows.toString());
	}
	
	private void tableInit(int display, int matchToType)
	{
		boolean matched = lstSearchMode.getSelectedIndex() == MODE_MATCHED;

		m_sql = new StringBuffer ();
		
		if (display == MATCH_INVOICE)
		{
			m_dateColumn = "hdr.DateInvoiced";
			m_qtyColumn = "lin.QtyInvoiced";
			m_sql.append("SELECT hdr.C_Invoice_ID,hdr.DocumentNo, hdr.DateInvoiced, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyInvoiced,SUM(NVL(mi.Qty,0)) "
				+ "FROM C_Invoice hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_InvoiceLine lin ON (hdr.C_Invoice_ID=lin.C_Invoice_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType IN ('API','APC'))"
				+ " FULL JOIN M_MatchInv mi ON (lin.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.C_Invoice_ID,hdr.DocumentNo,hdr.DateInvoiced,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID,p.Name,lin.M_Product_ID,lin.QtyInvoiced "
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyInvoiced")
				+ "<>SUM(NVL(mi.Qty,0))";
		}
		else if (display == MATCH_ORDER)
		{
			m_dateColumn = "hdr.DateOrdered";
			m_qtyColumn = "lin.QtyOrdered";
			m_sql.append("SELECT hdr.C_Order_ID,hdr.DocumentNo, hdr.DateOrdered, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyOrdered,SUM(COALESCE(mo.Qty,0)) "
				+ "FROM C_Order hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_OrderLine lin ON (hdr.C_Order_ID=lin.C_Order_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType='POO')"
				+ " FULL JOIN M_MatchPO mo ON (lin.C_OrderLine_ID=mo.C_OrderLine_ID) "
				+ "WHERE mo.")
				.append(matchToType == MATCH_SHIPMENT ? "M_InOutLine_ID" : "C_InvoiceLine_ID")
				.append(matched ? " IS NOT NULL" : " IS NULL"
				+ " AND hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.C_Order_ID,hdr.DocumentNo,hdr.DateOrdered,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID,p.Name,lin.M_Product_ID,lin.QtyOrdered "
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyOrdered")
				+ "<>SUM(COALESCE(mo.Qty,0))";
		}
		else    //  Shipment
		{
			m_dateColumn = "hdr.MovementDate";
			m_qtyColumn = "lin.MovementQty";
			m_sql.append("SELECT hdr.M_InOut_ID,hdr.DocumentNo, hdr.MovementDate, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.MovementQty,SUM(NVL(m.Qty,0)) "
				+ "FROM M_InOut hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN M_InOutLine lin ON (hdr.M_InOut_ID=lin.M_InOut_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID = dt.C_DocType_ID AND dt.DocBaseType='MMR')"
				+ " FULL JOIN ")
				.append(matchToType == MATCH_ORDER ? "M_MatchPO" : "M_MatchInv")
				.append(" m ON (lin.M_InOutLine_ID=m.M_InOutLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.M_InOut_ID,hdr.DocumentNo,hdr.MovementDate,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID,p.Name,lin.M_Product_ID,lin.MovementQty "
				+ "HAVING "
				+ (matched ? "0" : "lin.MovementQty")
				+ "<>SUM(NVL(m.Qty,0))";
		}
	}
	
	private void tableLoad(WListbox table)
	{
		log.finest(m_sql + " - " +  m_groupBy);
		String sql = MRole.getDefault().addAccessSQL(
			m_sql.toString(), "hdr", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ m_groupBy;
		log.finest(sql);
		
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			table.loadTable(rs);
			stmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}
	
	private boolean createMatchRecord (boolean invoice, int M_InOutLine_ID, int Line_ID,
			BigDecimal qty)
	{
		if (qty.compareTo(Env.ZERO) == 0)
			return true;

		log.fine("IsInvoice=" + invoice
				+ ", M_InOutLine_ID=" + M_InOutLine_ID + ", Line_ID=" + Line_ID
				+ ", Qty=" + qty);

		boolean success = false;
		MInOutLine sLine = new MInOutLine (Env.getCtx(), M_InOutLine_ID, null);

		if (invoice)	//	Shipment - Invoice
		{
			//	Update Invoice Line
		
			MInvoiceLine iLine = new MInvoiceLine (Env.getCtx(), Line_ID, null);
			iLine.setM_InOutLine_ID(M_InOutLine_ID);
			
			if (sLine.getC_OrderLine_ID() != 0)
				iLine.setC_OrderLine_ID(sLine.getC_OrderLine_ID());
			
			iLine.save();
			
			//	Create Shipment - Invoice Link
			if (iLine.getM_Product_ID() != 0)
			{
				MMatchInv match = new MMatchInv (iLine, null, qty);
				match.setM_InOutLine_ID(M_InOutLine_ID);
				if (match.save())
					success = true;
				else
					log.log(Level.SEVERE, "Inv Match not created: " + match);
			}
			else
				success = true;
			
			//	Create PO - Invoice Link = corrects PO
			if (iLine.getC_OrderLine_ID() != 0 && iLine.getM_Product_ID() != 0)
			{
				MMatchPO matchPO = MMatchPO.create(iLine, sLine, null, qty);
				matchPO.setC_InvoiceLine_ID(iLine);
				matchPO.setM_InOutLine_ID(M_InOutLine_ID);
				
				if (!matchPO.save())
				{
					log.log(Level.SEVERE, "PO(Inv) Match not created: " + matchPO);
				}
			}
		}
		else	//	Shipment - Order
		{
			//	Update Shipment Line
			sLine.setC_OrderLine_ID(Line_ID);
			sLine.save();
			
			//	Update Order Line
			MOrderLine oLine = new MOrderLine(Env.getCtx(), Line_ID, null);
			
			if (oLine.get_ID() != 0)	//	other in MInOut.completeIt
			{
				oLine.setQtyReserved(oLine.getQtyReserved().subtract(qty));
				if(!oLine.save())
					;//log.severe("QtyReserved not updated - C_OrderLine_ID=" + Line_ID);
			}

			//	Create PO - Shipment Link
			if (sLine.getM_Product_ID() != 0)
			{
				MMatchPO match = new MMatchPO (sLine, null, qty);
				if (!match.save())
					;//	log.log(Level.SEVERE, "PO Match not created: " + match);
				else
				{
					success = true;

					//	Correct Ordered Qty for Stocked Products (see MOrder.reserveStock / MInOut.processIt)
					if (sLine.getProduct() != null && sLine.getProduct().isStocked())
						success = MStorage.add (Env.getCtx(), sLine.getM_Warehouse_ID(), 
								sLine.getM_Locator_ID(), 
								sLine.getM_Product_ID(), 
								sLine.getM_AttributeSetInstance_ID(), oLine.getM_AttributeSetInstance_ID(), 
								null, null, qty.negate(), null);
				}
			}
			else
				success = true;
		}
		return success;
	}
	
	private void searchTo()
	{
		int row = xMatchedTable.getSelectedRow();
		log.config("Row=" + row);

		double qty = 0.0;
		
		if (row < 0)
		{
			xMatchedToTable.setRowCount(0);
		}
		else
		{
			//  ** Create SQL **
			ListItem lstIteDisplayStr = lstMatchTo.getSelectedItem();
			String displayString = (String)lstIteDisplayStr.getValue();
			int display = MATCH_INVOICE;
			
			if (displayString.equals(m_matchOptions[MATCH_SHIPMENT]))
				display = MATCH_SHIPMENT;
			else if (displayString.equals(m_matchOptions[MATCH_ORDER]))
				display = MATCH_ORDER;
			
			int matchToType = lstMatchFrom.getSelectedIndex();
			
			tableInit (display, matchToType);	//	sets m_sql
			
			//  ** Add Where Clause **
			KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(row, I_BPartner);
			KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(row, I_Product);
			log.fine("BPartner=" + BPartner + " - Product=" + Product);

			if (chkSameBP.isChecked())
				m_sql.append(" AND hdr.C_BPartner_ID=").append(BPartner.getKey());
			
			if (chkSameProduct.isChecked())
				m_sql.append(" AND lin.M_Product_ID=").append(Product.getKey());

			// Calculate qty
			double docQty = ((Double)xMatchedTable.getValueAt(row, I_QTY)).doubleValue();
			double matchedQty = ((Double)xMatchedTable.getValueAt(row, I_MATCHED)).doubleValue();
			qty = docQty - matchedQty;
			
			if (chkSameQty.isChecked())
				m_sql.append(" AND ").append(m_qtyColumn).append("=").append(docQty);

			//  ** Load Table **
			tableLoad (xMatchedToTable);
		}

		//  Display To be Matched Qty
		m_xMatched = new BigDecimal (qty);
		txtToBeMatched.setValue(m_xMatched.toString());
		txtMatching.setValue(Env.ZERO.toString());
		txtDifference.setValue(m_xMatched.toString());
		
		//  Status Info
		ListItem lstIteMatchFrom = lstMatchFrom.getSelectedItem();
		ListItem lstIteMatchTo = lstMatchTo.getSelectedItem();
		
		Integer matchedRowCount = xMatchedTable.getItemCount();
		Integer matchToRowCount = xMatchedToTable.getItemCount();
		
		String stat = 	lstIteMatchFrom.getLabel() + "# = " + matchedRowCount.toString() + " - "
						+ lstIteMatchTo.getLabel() +  "# = " + matchToRowCount.toString();
		
		lblStatus.setValue(stat);
		//	xMatchedToTable.getRowCount() == 0);
		//statusBar.setStatusDB(0);
	}
	
	public void onEvent(Event evt) throws Exception
	{
		if (evt != null)
		{
			if (evt.getTarget() == lstMatchFrom)
			{
				populateMatchTo();
				
				ListItem lstIteMatchFrom = lstMatchFrom.getSelectedItem();
				lblMatchFrom.setValue(lstIteMatchFrom.getLabel());
				
				ListItem lstIteMatchTo = lstMatchTo.getSelectedItem();
				lblMatchTo.setValue(lstIteMatchTo.getLabel());
				
				xMatchedTable.clear();
				xMatchedToTable.clear();
			}

			if (evt.getTarget() == lstMatchTo)
			{
				ListItem lstIteMatchTo = lstMatchTo.getSelectedItem();
				lblMatchTo.setValue(lstIteMatchTo.getLabel());
				
				xMatchedTable.clear();
				xMatchedToTable.clear();
			}
			
			if (evt.getTarget() == chkSameBP)
			{
				searchTo();
			}
			
			if (evt.getTarget() == chkSameProduct)
			{
				searchTo();
			}

			if (evt.getTarget() == chkSameQty)
			{
				searchTo();
			}
			
			if (evt.getTarget() == cmdSearch)
			{
				xMatchedTable.clear();
				xMatchedToTable.clear();
				searchRecords();
			}
			
			if (evt.getTarget() == cmdProcess)
			{
				process();
				
				xMatchedTable.clear();
				xMatchedToTable.clear();
				
				txtMatching.setValue("");
				txtToBeMatched.setValue("");
				txtDifference.setValue("");
				
				lblStatus.setValue("");
				
				searchRecords();
			}
			
			if (evt.getTarget() == xMatchedTable)
			{
				searchTo();
			}
		}
	}

	//public void tableChanged(WTableModelEvent event)
	//{
		//if (event.getColumn() == 0)
		//{
		//	searchTo();
		//}
	//}
	
	public void valueChange(ValueChangeEvent evt) 
	{
		if (evt == null)
			return;

		if (evt.getSource() instanceof WEditor)
		{
			String name = evt.getPropertyName();
			Object value = evt.getNewValue() == null ? "" : evt.getNewValue();

			xMatchedTable.clear();
			xMatchedToTable.clear();
			
			if (name.equals("C_BPartner_ID"))
			{
				bPartnerSearch.setValue(value);
				//m_C_BPartner_ID = ((Integer) value).intValue();
			}
			else if (name.equals("M_Product_ID"))
			{
				productSearch.setValue(value);
				//productID = new Integer(value);
			}
		}
	}
}

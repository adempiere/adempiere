/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos.search;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.pos.WPOS;
import org.adempiere.pos.WPOSKeyboard;
import org.adempiere.pos.WPOSTextField;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WQueryOrderHistory extends WPOSQuery implements POSQueryInterface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;
	/**
	 * 	Constructor
	 */
	public WQueryOrderHistory (WPOS posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct

	/** Fields 				*/
	private WPOSTextField 	fieldDocumentNo;
	private WPOSTextField 	fieldBPartner;
	private Datebox 		fieldDateTo;
	private Datebox 		fieldDateFrom;
	private Checkbox 		fieldProcessed;
	private Checkbox 		fieldAllowDate;

	private Date 			dateTo;
	private Date 			dateFrom;
	private int 			orderId;
	private boolean			isKeyboard;
	
	static final private String DOCUMENTNO      = "DocumentNo";
	static final private String DOCTYPE         = "C_DocType_ID";  // Just display of column name. The actual doctype will be target doctype
	static final private String BPARTNERID      = "C_BPartner_ID";
	static final private String GRANDTOTAL      = "GrandTotal";
	static final private String OPENAMT         = "OpenAmt";
	static final private String PAID            = "IsPaid";
	static final private String PROCESSED       = "Processed";
	static final private String INVOICED       	= "IsInvoiced";
	static final private String DATE	        = "Date";
	static final private String DATEORDEREDFROM = "From";
	static final private String DATEORDEREDTO   = "To";
	static final private String DATEORDERED     = "DateOrdered";
	static final private String QUERY           = "Query";
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] columnInfos = new ColumnInfo[] {
		new ColumnInfo(" ", "C_Order_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCUMENTNO), DOCUMENTNO, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCTYPE), DOCTYPE, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), BPARTNERID), BPARTNERID, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DATEORDERED), DATEORDERED, Date.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), GRANDTOTAL), GRANDTOTAL, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), OPENAMT), OPENAMT, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PAID), PAID, Boolean.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), PROCESSED), PROCESSED, Boolean.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), INVOICED), INVOICED, Boolean.class)
	};

	/**
	 * 	Set up Panel
	 */
	protected void init()
	{
		setTitle(Msg.translate(Env.getCtx(), "C_Order_ID"));
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid productLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(ctx, QUERY));
		
		//	Set title window
		this.setClosable(true);
		// add listener on 'ENTER' key 
        addEventListener(Events.ON_OK,this);
		
		appendChild(panel);
		northPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		groupPanel.appendChild(v_TitleBorder);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(groupPanel);
		groupPanel.appendChild(productLayout);
		appendChild(mainPanel);
		productLayout.setWidth("100%");
		Rows rows = null;
		Row row = null;
		rows = productLayout.newRows();
		row = rows.newRow();
		
		Label labelDocumentNo = new Label(Msg.translate(ctx, DOCUMENTNO));
		labelDocumentNo.setStyle(WPOS.FONTSIZESMALL);
		row.setHeight("20px");
		row.appendChild(labelDocumentNo.rightAlign());
		fieldDocumentNo = new WPOSTextField("", posPanel.getKeyboard());
		row.appendChild(fieldDocumentNo);
		fieldDocumentNo.addEventListener(this);
		fieldDocumentNo.setWidth("120px");
		fieldDocumentNo.setStyle(WPOS.FONTSIZESMALL);
		//
		Label labelDateFrom = new Label(Msg.translate(ctx, DATEORDEREDFROM));
		labelDateFrom.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(labelDateFrom.rightAlign());
		fieldDateFrom = new Datebox();
		fieldDateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		fieldDateFrom.addEventListener("onBlur",this);
		fieldDateFrom.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(fieldDateFrom);

		fieldAllowDate = new Checkbox();
		fieldAllowDate.setLabel(Msg.translate(ctx, DATE));
		fieldAllowDate.setSelected(false);
		row.appendChild(fieldAllowDate);
		fieldAllowDate.addActionListener(this);
		fieldAllowDate.setStyle(WPOS.FONTSIZESMALL);
		
		row = rows.newRow();
		
		Label labelBPartner = new Label(Msg.translate(ctx, BPARTNERID));
		labelBPartner.setStyle(WPOS.FONTSIZESMALL);
		row.setHeight("60px");
		row.appendChild(labelBPartner.rightAlign());
		fieldBPartner = new WPOSTextField("", posPanel.getKeyboard());
		row.appendChild(fieldBPartner);
		fieldBPartner.addEventListener(this);
		fieldBPartner.setWidth("120px");
		fieldBPartner.setStyle(WPOS.FONTSIZESMALL);
		
		Label labelDateTo = new Label(Msg.translate(ctx, DATEORDEREDTO));
		labelDateTo.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(labelDateTo.rightAlign());
		fieldDateTo = new Datebox();
		fieldDateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		fieldDateTo.addEventListener("onBlur",this);
		fieldDateTo.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(fieldDateTo);
		
		fieldProcessed = new Checkbox();
		fieldProcessed.setLabel(Msg.translate(ctx, PROCESSED));
		fieldProcessed.setSelected(false);
		row.appendChild(fieldProcessed);
		fieldProcessed.addActionListener(this);
		fieldProcessed.setStyle(WPOS.FONTSIZESMALL);
		
		//	Center
		posTable = ListboxFactory.newDataTable();
		posTable.prepareTable (columnInfos, "C_Order",
				"C_POS_ID = " + posPanel.getC_POS_ID()
				, false, "C_Order");

		enableButtons();
		center = new Center();
		center.setStyle("border: none");
		posTable.setWidth("100%");
		posTable.setHeight("99%");
		posTable.addActionListener(this);
		center.appendChild(posTable);
		mainLayout.appendChild(center);
		posTable.setClass("Table-OrderLine");
		posTable.autoSize();
		posTable.addEventListener(Events.ON_DOUBLE_CLICK, this);
		refresh();
	}	//	init
	
	/**
	 * 
	 */
	@Override
	public void reset()
	{
		fieldProcessed.setSelected(false);
		fieldDocumentNo.setText(null);
		fieldDateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		fieldDateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		refresh();
	}

	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, boolean processed, String doc, Date dateFrom, Date dateTo, String bPartner, boolean aDate)
	{
		StringBuffer sql = new StringBuffer();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try  {
			sql.append(" SELECT o.C_Order_ID, o.DocumentNo, dt.Name AS C_DocType_ID ,")
				.append(" b.Name, TRUNC(o.dateordered,'DD') as dateordered, o.GrandTotal, ")
				// priority for open amounts: invoices, allocations, order
				.append(" COALESCE(SUM(invoiceopen(i.C_Invoice_ID, 0)), COALESCE(o.GrandTotal - SUM(al.amount),0)) AS InvoiceOpen, ")
			    .append(" COALESCE(i.IsPaid, CASE WHEN o.GrandTotal - SUM(al.amount) = 0 THEN 'Y' ELSE 'N' END) IsPaid, ")
			    .append(" o.Processed, ")
			    .append(" CASE WHEN COALESCE(COUNT(i.C_Invoice_ID), 0) > 0 THEN 'Y' ELSE 'N' END")
				.append(" FROM C_Order o ")
				.append(" INNER JOIN C_BPartner      b ON (o.C_BPartner_ID = b.C_BPartner_ID)");
			
			if (Env.isBaseLanguage(Env.getAD_Language(ctx), "C_DocType"))
				sql.append(" INNER JOIN C_DocType      dt ON (o.C_DocTypeTarget_ID = dt.C_DocType_ID)");
			else
				sql.append(" INNER JOIN C_DocType_trl  dt ON (o.C_DocTypeTarget_ID = dt.C_DocType_ID AND dt.AD_LANGUAGE = '" + Env.getAD_Language(ctx) + "')");
			
			sql.append(" LEFT JOIN C_invoice        i ON (i.C_Order_ID = o.C_Order_ID)")
				.append(" LEFT JOIN C_AllocationLine al ON (o.C_Order_ID = al.C_Order_ID)")
				.append(" WHERE  o.DocStatus <> 'VO'")
				.append(" AND o.C_POS_ID = ?")
				.append(" AND o.Processed= ?");
			if (doc != null && !doc.equalsIgnoreCase(""))
				sql.append(" AND (o.DocumentNo LIKE '%" + doc + "%' OR  i.DocumentNo LIKE '%" + doc + "%')");
			if ( dateFrom != null && aDate) {
				if ( dateTo != null && !dateTo.equals(dateFrom))
					sql.append(" AND o.DateOrdered >= ? AND o.DateOrdered <= ?");						
				else
					sql.append(" AND o.DateOrdered = ? ");	
			}
			if (bPartner != null && !bPartner.equalsIgnoreCase(""))
				sql.append(" AND (UPPER(b.name) LIKE '%" + bPartner + "%' OR UPPER(b.value) LIKE '%" + bPartner + "%' )");
			//	Group By
			sql.append(" GROUP BY o.C_Order_ID, o.DocumentNo, dt.Name , b.Name, o.GrandTotal, o.Processed, i.IsPaid ");
			sql.append(" ORDER BY o.Updated");
			int i = 1;			
			preparedStatement = DB.prepareStatement(sql.toString(), null);
			//	POS
			preparedStatement.setInt(i++, posPanel.getC_POS_ID());
			//	Processed
			preparedStatement.setString(i++, processed? "Y": "N");
			//	Date From and To
			if (dateFrom != null && aDate) {				
				preparedStatement.setDate(i++, dateFrom);
				if (dateTo != null 
						&& !dateTo.equals(dateFrom)) {
					preparedStatement.setDate(i++, dateTo);
				}
			}
			//	
			resultSet = preparedStatement.executeQuery();
			posTable.loadTable(resultSet);
			int rowNo = posTable.getRowCount();
			if (rowNo > 0) {
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			logger.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(resultSet);
			DB.close(preparedStatement);
		}
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		orderId = -1;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				orderId = ID.intValue();
			}
		}
		logger.info("ID=" + orderId);
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	@Override
	protected void close()
	{
		logger.info("C_Order_ID=" + orderId);
		
		if (orderId > 0)
		{
			posPanel.setOrder(orderId);

		}
		dispose();
	}	//	close


	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getTarget().equals(fieldDocumentNo.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			isKeyboard = true;
			//	Get Keyboard Panel
			WPOSKeyboard keyboard = fieldDocumentNo.getKeyboard();
			
			if(keyboard != null) {
				
				//	Set Title
				keyboard.setTitle(Msg.translate(Env.getCtx(), DOCUMENTNO).substring(1));
				keyboard.setPosTextField(fieldDocumentNo);
				AEnv.showWindow(keyboard);
				
			}

			fieldDocumentNo.setFocus(true);
			refresh();
		}
		else if(e.getTarget().equals(fieldDocumentNo.getComponent(WPOSTextField.PRIMARY))) {
			 isKeyboard = false;
		}else if(e.getTarget().equals(fieldBPartner.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			isKeyboard = true;
			//	Get Keyboard Panel
			WPOSKeyboard keyboard = fieldBPartner.getKeyboard();
			
			if(keyboard != null) {
				
				//	Set Title
				keyboard.setTitle(Msg.translate(Env.getCtx(), BPARTNERID));
				keyboard.setPosTextField(fieldBPartner);
				AEnv.showWindow(keyboard);
				
			}
			fieldBPartner.setFocus(true);
			refresh();
		}
		else if(e.getTarget().equals(fieldBPartner.getComponent(WPOSTextField.PRIMARY))) {
			 isKeyboard = false;
		}
		else if(e.getTarget().getId().equals("Refresh")) {
			refresh();
		}
		if ( e.getTarget().equals(fieldProcessed) || e.getTarget().equals(fieldAllowDate)
				|| e.getTarget().equals(fieldDateTo) || e.getTarget().equals(fieldDateFrom)) {
				refresh();
				return;
		}
		else if (Events.ON_OK.equals(e.getName()) 
				|| e.getTarget().equals(posTable) 
				&& e.getName().equals(Events.ON_DOUBLE_CLICK)) {
			close();
		}
		else if(e.getTarget().getId().equals("Ok")){
			close();
		}
		else if(e.getTarget().getId().equals("Cancel")){
			close();
		}		else if(e.getTarget().getId().equals("Reset")){
			reset();
		}
		enableButtons();
	}

	@Override
	public void refresh() {
		lockUI();
		fieldDateTo.setEnabled(fieldAllowDate.isSelected());
		fieldDateFrom.setEnabled(fieldAllowDate.isSelected());
		if(fieldDateTo.getValue()!=null) {
			dateTo = new Date(fieldDateTo.getValue().getTime());
		}	
		else {
			dateTo = null;
		}
		if(fieldDateFrom.getValue()!=null) {
			dateFrom = new Date(fieldDateFrom.getValue().getTime());
		}
		else {
			dateFrom = null;
		}
		setResults(ctx, fieldProcessed.isSelected(), fieldDocumentNo.getText(), dateFrom, dateTo, 
					fieldBPartner.getText().toUpperCase(), fieldAllowDate.isSelected());
		unlockUI();
	}

	@Override
	protected void select() {
		orderId = -1;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				orderId = ID.intValue();
			}
		}
		logger.info("ID=" + orderId);
	}
	@Override
	protected void cancel() {
		orderId = -1;
		dispose();
	}
	
	@Override
	public int getRecord_ID() {
		return orderId;
	}
	
	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}
	
}	//	PosQueryProduct

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
import org.adempiere.pos.WPosTextField;
import org.adempiere.pos.service.I_POSQuery;
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
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

/**
 *	POS Query Product
 *	
 *  
 */

public class WQueryTicket extends WPosQuery implements I_POSQuery
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;
	/**
	 * 	Constructor
	 */
	public WQueryTicket (WPOS posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct

	
	private WPosTextField	f_documentno;
	private Datebox			f_DateTo;
	private Datebox			f_DateFrom;
	private Date			m_DateTo;
	private Date			m_DateFrom;

	private int				m_c_order_id;
	private Checkbox 		f_processed;
	private boolean			isKeyboard;
	/**	Internal Variables	*/
	private int				m_C_Order_ID;
	
	static final private String DOCUMENTNO      = "DocumentNo";
	static final private String BPARTNERID      = "C_BPartner_ID";
	static final private String GRANDTOTAL      = "GrandTotal";
	static final private String OPENAMT         = "OpenAmt";
	static final private String PAID            = "IsPaid";
	static final private String PROCESSED       = "Processed";
	static final private String INVOICED       	= "IsInvoiced";
	static final private String DATEORDEREDFROM = "From";
	static final private String DATEORDEREDTO   = "To";
	static final private String QUERY           = "Query";
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_Order_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCUMENTNO), DOCUMENTNO, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), BPARTNERID), BPARTNERID, String.class),
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
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid productLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(p_ctx, QUERY));
		
		//	Set title window
		this.setClosable(true);
		
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
		
		Label ldoc = new Label(Msg.translate(p_ctx, DOCUMENTNO));
		ldoc.setStyle(WPOS.FONTSIZESMALL);
		row.setHeight("60px");
		row.appendChild(ldoc.rightAlign());
		f_documentno = new WPosTextField(v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(f_documentno);
		f_documentno.addEventListener(this);
		f_documentno.setWidth("120px");
		f_documentno.setStyle(WPOS.FONTSIZESMALL);
		//
		Label ldateFrom = new Label(Msg.translate(p_ctx, DATEORDEREDFROM));
		ldateFrom.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(ldateFrom.rightAlign());
		f_DateFrom = new Datebox();
		f_DateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		f_DateFrom.addEventListener("onBlur",this);
		f_DateFrom.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(f_DateFrom);
		
		// Date To
		Label ldate = new Label(Msg.translate(p_ctx, DATEORDEREDTO));
		ldate.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(ldate.rightAlign());
		f_DateTo = new Datebox();
		f_DateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		f_DateTo.addEventListener("onBlur",this);
		f_DateTo.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(f_DateTo);
		
		f_processed = new Checkbox();
		f_processed.setLabel(Msg.translate(p_ctx, PROCESSED));
		f_processed.setSelected(false);
		row.appendChild(f_processed);
		f_processed.addActionListener(this);
		f_processed.setStyle(WPOS.FONTSIZESMALL);
		
		//	Center
		m_table = ListboxFactory.newDataTable();
		m_table.prepareTable (s_layout, "C_Order", 
				"C_POS_ID = " + p_pos.getC_POS_ID()
				, false, "C_Order");

		enableButtons();
		center = new Center();
		center.setStyle("border: none");
		m_table.setWidth("100%");
		m_table.setHeight("99%");
		m_table.addActionListener(this);
		center.appendChild(m_table);
		mainLayout.appendChild(center);
		m_table.setClass("Table-OrderLine");
		m_table.autoSize();
		refresh();
	}	//	init
	
	/**
	 * 
	 */
	@Override
	public void reset()
	{
		f_processed.setSelected(false);
		f_documentno.setText(null);
		f_DateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		f_DateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		refresh();
	}

	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, boolean processed, String doc, Date dateFrom, Date dateTo)
	{
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try  {
			sql.append(" SELECT o.C_Order_ID, o.DocumentNo, ")
			.append(" b.Name, o.GrandTotal, ")
			.append(" COALESCE(SUM(invoiceopen(i.C_Invoice_ID, 0)), o.GrandTotal - SUM(p.PayAmt), o.GrandTotal) AS InvoiceOpen, ")
		    .append(" COALESCE(i.IsPaid, CASE WHEN o.GrandTotal - SUM(p.PayAmt) = 0 THEN 'Y' ELSE 'N' END) IsPaid, ")
		    .append(" o.Processed, ")
		    .append(" CASE WHEN COALESCE(COUNT(i.C_Invoice_ID), 0) > 0 THEN 'Y' ELSE 'N' END")
			.append(" FROM C_Order o ")
			.append(" INNER JOIN C_BPartner b ON (o.C_BPartner_ID = b.C_BPartner_ID)")
			.append(" LEFT JOIN C_invoice   i ON (i.C_Order_ID = o.C_Order_ID)")
			.append(" LEFT JOIN C_Payment   p ON (p.C_Order_ID = o.C_Order_ID)")
			.append(" WHERE  o.DocStatus <> 'VO'")
			.append(" AND o.C_POS_ID = ?")
			.append(" AND o.Processed= ?");
			if (doc != null && !doc.equalsIgnoreCase(""))
				sql.append(" AND (o.DocumentNo LIKE '%" + doc + "%' OR  i.DocumentNo LIKE '%" + doc + "%')");
			if ( dateFrom != null ) {
				if ( dateTo != null && !dateTo.equals(dateFrom))
					sql.append(" AND o.DateOrdered >= ? AND o.DateOrdered <= ?");						
				else
					sql.append(" AND o.DateOrdered = ? ");	
			}
			//	Group By
			sql.append(" GROUP BY o.C_Order_ID, o.DocumentNo, b.Name, o.GrandTotal, o.Processed, i.IsPaid ");
			sql.append(" ORDER BY o.Updated");
			int i = 1;			
			pstm = DB.prepareStatement(sql.toString(), null);
			//	POS
			pstm.setInt(i++, v_POSPanel.getC_POS_ID());
			//	Processed
			pstm.setString(i++, processed? "Y": "N");
			//	Date From and To
			if (dateFrom != null) {				
				pstm.setDate(i++, dateFrom);
				if (dateTo != null 
						&& !dateTo.equals(dateFrom)) {
					pstm.setDate(i++, dateTo);
				}
			}
			//	
			rs = pstm.executeQuery();
			m_table.loadTable(rs);
			int rowNo = m_table.getRowCount();
			if (rowNo > 0) {
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
		}
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		m_c_order_id = -1;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null)
			{
				m_c_order_id = ID.intValue();
			}
		}
		log.info("ID=" + m_c_order_id); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	@Override
	protected void close()
	{
		log.info("C_Order_ID=" + m_c_order_id); 
		
		if (m_c_order_id > 0)
		{
			v_POSPanel.setOrder(m_c_order_id);

		}
		dispose();
	}	//	close


	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getTarget().equals(f_documentno.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
			isKeyboard = true;
			//	Get Keyboard Panel
			WPOSKeyboard keyboard = v_POSPanel.getKeyboard(f_documentno.getKeyLayoutId(), f_documentno);
			//	Set Title
			keyboard.setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
			keyboard.setWidth("750px");
			keyboard.setHeight("380px");
			AEnv.showWindow(keyboard);
			refresh();
			f_documentno.setFocus(true);

		}
		else if(e.getTarget().equals(f_documentno.getComponent(WPosTextField.PRIMARY))) {
			 isKeyboard = false;
		}
		else if(e.getTarget().getId().equals("Refresh")) {
			refresh();
		}
		if ( e.getTarget().equals(f_processed) 
				|| e.getTarget().equals(f_DateTo) || e.getTarget().equals(f_DateFrom)) {
				refresh();
				return;
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
		if(f_DateTo.getValue()!=null) {
			m_DateTo = new Date(f_DateTo.getValue().getTime());
		}	
		else {
			m_DateTo = null;
		}
		if(f_DateFrom.getValue()!=null) {
			m_DateFrom = new Date(f_DateFrom.getValue().getTime());
		}
		else {
			m_DateFrom = null;
		}
		setResults(p_ctx, f_processed.isSelected(), f_documentno.getText(), m_DateFrom, m_DateTo);

	}

	@Override
	protected void select() {
		m_C_Order_ID = -1;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null)
			{
				m_C_Order_ID = ID.intValue();
			}
		}
		log.info("ID=" + m_C_Order_ID); 
	}
	@Override
	protected void cancel() {
		m_C_Order_ID = -1;
		dispose();
	}
	
	@Override
	public int getRecord_ID() {
		return m_C_Order_ID;
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

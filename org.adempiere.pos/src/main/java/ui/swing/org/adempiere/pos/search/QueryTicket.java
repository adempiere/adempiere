/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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

package org.adempiere.pos.search;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.search.POSQuery;
import org.compiere.grid.ed.VDate;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.PO;
import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CLabel;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	POS Query Ticket
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Jose A.Gonzalez, Conserti.
 *  author $Id: Consultoria y Soporte en Redes y Tecnologias de la Informacion S.L.
 *  @author Dixon Martinez, ERPCYA 
 *  @author Susanne Calderón Schöningh, Systemhaus Westfalia
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  @version $Id: QueryTicket.java,v 0.9 $
 *  @version $Id: QueryProduct.java,v 1.1 jjanke Exp $
 *  @version $Id: QueryProduct.java,v 2.0 2015/09/01 00:00:00 scalderon
 * 
 */

public class QueryTicket extends POSQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;
	/**
	 * 	Constructor
	 */
	public QueryTicket (VPOS posPanel) {
		super(posPanel);
	}	//	PosQueryProduct

	/**	Search Fields		*/
	private POSTextField	f_DocumentNo;
	private VDate			f_DateFrom;
	private VDate			f_DateTo;
	/**	Internal Variables	*/
	private int				m_C_Order_ID;
	private CCheckBox 		f_Processed;
	
	
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
	@Override
	protected void init() {
		//	North
		v_ParameterPanel.setLayout(new MigLayout("fill","", "[50][50][]"));
		v_ParameterPanel.setBorder(new TitledBorder(Msg.getMsg(m_ctx, QUERY)));
		
		CLabel ldoc = new CLabel(Msg.translate(m_ctx, DOCUMENTNO));
		v_ParameterPanel.add (ldoc, " growy");
		f_DocumentNo = new POSTextField("", v_POSPanel.getKeyboard());
		ldoc.setLabelFor(f_DocumentNo);
		v_ParameterPanel.add(f_DocumentNo, "h 30, w 200");
		f_DocumentNo.addActionListener(this);
		//
		CLabel ldateFrom = new CLabel(Msg.translate(m_ctx, DATEORDEREDFROM));
		v_ParameterPanel.add (ldateFrom, "growy");
		f_DateFrom = new VDate();
		f_DateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		ldateFrom.setLabelFor(f_DateFrom);
		v_ParameterPanel.add(f_DateFrom, "h 30, w 200");
		f_DateFrom.addVetoableChangeListener(this);
		
		// Date To
		CLabel ldateTo = new CLabel(Msg.translate(m_ctx, DATEORDEREDTO));
		v_ParameterPanel.add (ldateTo, "growy");
		f_DateTo = new VDate();
		f_DateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		ldateTo.setLabelFor(f_DateTo);
		v_ParameterPanel.add(f_DateTo, "h 30, w 200");
		f_DateTo.addVetoableChangeListener(this);
		
		f_Processed = new CCheckBox(Msg.translate(m_ctx, PROCESSED));
		f_Processed.setSelected(false);
		f_Processed.addActionListener(this);
		v_ParameterPanel.add(f_Processed, "");
		//	
		m_table.prepareTable (s_layout, "C_Order", 
				"C_POS_ID = " + v_POSPanel.getC_POS_ID()
				, false, "C_Order");
		m_table.growScrollbars();
		f_DocumentNo.requestFocus();
		pack();
		refresh();
	}	//	init
	
	/**
	 * 
	 */
	@Override
	public void reset() {
		f_Processed.setSelected(false);
		f_DocumentNo.setText(null);
		f_DateFrom.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		f_DateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		refresh();
	}
	
	/**
	 * Clean Values
	 * @return void
	 */
	private void cleanValues() {
		m_C_Order_ID = -1;
	}
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResultsFromArray(Properties ctx, boolean processed, String doc, Timestamp dateFrom, Timestamp dateTo) {
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
				pstm.setTimestamp(i++, dateFrom);
				if (dateTo != null 
						&& !dateTo.equals(dateFrom)) {
					pstm.setTimestamp(i++, dateTo);
				}
			}
			//	
			rs = pstm.executeQuery();
			m_table.loadTable(rs);
			int rowNo = m_table.getRowCount();
			if (rowNo > 0) {
				m_table.setRowSelectionInterval(0, 0);
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
	protected void select() {
		cleanValues();
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
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	@Override
	protected void close() {
		select();	
		dispose();
	}	//	close
	
	@Override
	public void refresh() {
		cleanValues();
		setResultsFromArray(m_ctx, f_Processed.isSelected(), f_DocumentNo.getText(), 
				f_DateFrom.getTimestamp(), f_DateTo.getTimestamp());
	}
	
	@Override
	public void setResults(PO[] results) {
		//	
	}
	
	@Override
	protected void cancel() {
		cleanValues();
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
}	//	QueryTicket
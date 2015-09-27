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

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.service.I_POSQuery;
import org.compiere.grid.ed.VDate;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
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

public class QueryTicket extends POSQuery implements I_POSQuery {
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
	static final private String TOTALLINES      = "TotalLines";
	static final private String OPENAMT         = "OpenAmt";
	static final private String GRANDTOTAL      = "GrandTotal";
	static final private String BPARTNERID      = "C_BPartner_ID";
	static final private String PROCESSED       = "Processed";
	static final private String PAID            = "IsPaid";
	static final private String DATEORDEREDFROM = "DateOrderedFrom";
	static final private String DATEORDEREDTO   = "DateOrderedTo";
	static final private String QUERY           = "Query";

	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_Order_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCUMENTNO), DOCUMENTNO, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), OPENAMT), TOTALLINES, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), GRANDTOTAL), GRANDTOTAL, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), BPARTNERID), BPARTNERID, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PROCESSED), PROCESSED, Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PAID), PAID, Boolean.class)
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
		f_DateFrom.addActionListener(this);
		
		// Date To
		CLabel ldateTo = new CLabel(Msg.translate(m_ctx, DATEORDEREDTO));
		v_ParameterPanel.add (ldateTo, "growy");
		f_DateTo = new VDate();
		f_DateTo.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		ldateTo.setLabelFor(f_DateTo);
		v_ParameterPanel.add(f_DateTo, "h 30, w 200");
		f_DateTo.addActionListener(this);
		
		f_Processed = new CCheckBox(Msg.translate(m_ctx, PROCESSED));
		f_Processed.setSelected(false);
		v_ParameterPanel.add(f_Processed, "");
		//	
		m_table.prepareTable (s_layout, "C_Order", 
				"C_POS_ID = " + v_POSPanel.getC_POS_ID()
				, false, "C_Order");
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		select();
		m_table.growScrollbars();
		f_DocumentNo.requestFocus();
		pack();
		refresh();
	}	//	init
	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	@Override
	public void actionPerformed (ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource() == f_Processed || e.getSource() == f_DocumentNo
			|| e.getSource() == f_DateFrom || e.getSource() == f_DateTo) {
			refresh();
			return;
		}
	}	//	actionPerformed
	
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
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, boolean processed, String doc, Timestamp dateFrom, Timestamp dateTo) {
		StringBuffer sql = new StringBuffer();
		try  {
			sql.append(" SELECT o.C_Order_ID, o.DocumentNo, coalesce(invoiceopen(i.c_invoice_ID, 0), o.grandtotal) as invoiceopen")
			     .append(", o.GrandTotal, b.Name, o.Processed, i.ispaid ")
				.append(" FROM C_Order o ")
				.append(" INNER JOIN C_BPartner b ON o.C_BPartner_ID=b.C_BPartner_ID")
				.append(" LEFT JOIN c_invoice i on i.c_order_ID = o.c_order_ID")
				.append(" WHERE o.C_POS_ID = " + v_POSPanel.getC_POS_ID())
				.append(" AND coalesce(invoiceopen(i.c_invoice_ID, 0), 0)  >= 0 ")
				.append(" AND (i.ispaid='N' OR o.processed= "+ ( processed ? "'Y' )" : "'N' )"));
			if (doc != null && !doc.equalsIgnoreCase(""))
				sql.append(" AND (o.DocumentNo LIKE '%" + doc + "%' OR  i.DocumentNo LIKE '%" + doc + "%')");
			if ( dateFrom != null ) {
				if ( dateTo != null && !dateTo.equals(dateFrom))
					sql.append(" AND trunc(o.DateOrdered) BETWEEN ? AND ?");						
				else
					sql.append(" AND trunc(o.DateOrdered) = ? ");	
			}
			sql.append(" ORDER BY o.DocumentNo DESC");
			
			PreparedStatement pstm = DB.prepareStatement(sql.toString(), null);
			if ( dateFrom != null ) {				
				pstm.setTimestamp(1, dateFrom);
				if ( dateTo != null && !dateTo.equals(dateFrom))	
					pstm.setTimestamp(2, dateTo);
			}
			ResultSet rs = pstm.executeQuery();
			m_table.loadTable(rs);
			if ( m_table.getRowCount() > 0 )
				m_table.setRowSelectionInterval(0, 0);
			select();
		} catch(Exception e) {
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
			
		}
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
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
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	@Override
	protected void close() {
		log.info("C_Order_ID=" + m_C_Order_ID); 
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_C_Order_ID = ID.intValue(); 		
		dispose();
	}	//	close
	
	@Override
	public void refresh() {
		setResults(m_ctx, f_Processed.isSelected(), f_DocumentNo.getText(), 
				f_DateFrom.getTimestamp(), f_DateTo.getTimestamp());
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
}	//	QueryTicket
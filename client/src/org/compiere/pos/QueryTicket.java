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

package org.compiere.pos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.compiere.grid.ed.VDate;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
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
 *  
 *  @version $Id: QueryTicket.java,v 0.9 $
 *  @version $Id: QueryProduct.java,v 1.1 jjanke Exp $
 *  @version $Id: QueryProduct.java,v 2.0 2015/09/01 00:00:00 scalderon
 * 
 */

public class QueryTicket extends PosQuery
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;
	/**
	 * 	Constructor
	 */
	public QueryTicket (PosBasePanel posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct

	
	private PosTextField	f_documentno;
	private VDate			f_date;

	private int				m_c_order_id;
	private CCheckBox 		f_processed;
	private CButton 		f_refresh;
	private CButton 		f_ok;
	private CButton 		f_cancel;
	
	static final private String DOCUMENTNO  = "DocumentNo";
	static final private String TOTALLINES  = "TotalLines";
	static final private String OPENAAMT    = "OpenAmt";
	static final private String GRANDTOTAL  = "GrandTotal";
	static final private String BPARTNERID  = "C_BPartner_ID";
	static final private String PROCESSED   = "Processed";
	static final private String PAID        = "IsPaid";
	static final private String DATEORDERED = "DateOrdered";
	static final private String REFRESH     = "Refresh";
	static final private String QUERY       = "Query";
	static final private String PREVIOUS    = "Previous";
	static final private String NEXT        = "Next";
	static final private String OK          = "Ok";
	static final private String CANCEL      = "Cancel";
	static final private String RESET       = "Reset";

	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "C_Order_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCUMENTNO), DOCUMENTNO, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), OPENAAMT), TOTALLINES, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), GRANDTOTAL), GRANDTOTAL, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), BPARTNERID), BPARTNERID, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PROCESSED), PROCESSED, Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PAID), PAID, Boolean.class)
	};

	/**
	 * 	Set up Panel
	 */
	@Override
	protected void init()
	{
		CPanel panel = new CPanel();
		
		panel.setLayout(new MigLayout("fill"));
		getContentPane().add(panel);
		//	North
		northPanel = new CPanel(new MigLayout("fill","", "[50][50][]"));
		panel.add (northPanel, "north");
		northPanel.setBorder(new TitledBorder(Msg.getMsg(p_ctx, QUERY)));
		
		CLabel ldoc = new CLabel(Msg.translate(p_ctx, DOCUMENTNO));
		northPanel.add (ldoc, " growy");
		f_documentno = new PosTextField("", p_posPanel, p_pos.get_ValueAsInt("OSK_KeyLayout_ID"));
		ldoc.setLabelFor(f_documentno);
		northPanel.add(f_documentno, "h 30, w 200");
		f_documentno.addActionListener(this);
		//
		CLabel ldate = new CLabel(Msg.translate(p_ctx, DATEORDERED));
		northPanel.add (ldate, "growy");
		f_date = new VDate();
		f_date.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		ldate.setLabelFor(f_date);
		northPanel.add(f_date, "h 30, w 200");
		f_date.addActionListener(this);
		
		f_processed = new CCheckBox(Msg.translate(p_ctx, PROCESSED));
		f_processed.setSelected(false);
		northPanel.add(f_processed, "");
		
		f_refresh = createButtonAction(REFRESH, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		northPanel.add(f_refresh, "w 50!, h 50!, wrap, alignx trailing");
		
		f_up = createButtonAction(PREVIOUS, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		northPanel.add(f_up, "w 50!, h 50!, span, split 4");
		f_down = createButtonAction(NEXT, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		northPanel.add(f_down, "w 50!, h 50!");
		
		f_ok = createButtonAction(OK, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		northPanel.add(f_ok, "w 50!, h 50!");
		
		f_cancel = createButtonAction(CANCEL, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		northPanel.add(f_cancel, "w 50!, h 50!");

		
		//	Center
		m_table = new PosTable();
		
		String sql = m_table.prepareTable (s_layout, "C_Order", 
				"C_POS_ID = " + p_pos.getC_POS_ID()
				, false, "C_Order")
			+ " ORDER BY Margin, QtyAvailable";
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		enableButtons();
		centerScroll = new CScrollPane(m_table);
		panel.add (centerScroll, "growx, growy");
		m_table.growScrollbars();
		panel.setPreferredSize(new Dimension(800,600));

		f_documentno.requestFocus();
		pack();
		
		setResults(p_ctx, f_processed.isSelected(), f_documentno.getText(), f_date.getTimestamp());
	}	//	init

	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	@Override
	public void actionPerformed (ActionEvent e)
	{
		log.info("PosQueryProduct.actionPerformed - " + e.getActionCommand());
		if (REFRESH.equals(e.getActionCommand())
			|| e.getSource() == f_processed || e.getSource() == f_documentno
			|| e.getSource() == f_date)
		{
			setResults(p_ctx, f_processed.isSelected(), f_documentno.getText(), f_date.getTimestamp());
			return;
		}
		else if (RESET.equals(e.getActionCommand()))
		{
			reset();
			return;
		}
		else if (PREVIOUS.equalsIgnoreCase(e.getActionCommand()))
		{
			int rows = m_table.getRowCount();
			if (rows == 0)
				return;
			int row = m_table.getSelectedRow();
			row--;
			if (row < 0)
				row = 0;
			m_table.getSelectionModel().setSelectionInterval(row, row);
			return;
		}
		else if (NEXT.equalsIgnoreCase(e.getActionCommand()))
		{
			int rows = m_table.getRowCount();
			if (rows == 0)
				return;
			int row = m_table.getSelectedRow();
			row++;
			if (row >= rows)
				row = rows - 1;
			m_table.getSelectionModel().setSelectionInterval(row, row);
			return;
		}
		else if (CANCEL.equalsIgnoreCase(e.getActionCommand()))
		{
			dispose();
			return;
		}
		//	Exit
		close();
	}	//	actionPerformed
	
	
	/**
	 * 
	 */
	@Override
	public void reset()
	{
		f_processed.setSelected(false);
		f_documentno.setText(null);
		f_date.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		setResults(p_ctx, f_processed.isSelected(), f_documentno.getText(), f_date.getTimestamp());
	}


	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, boolean processed, String doc, Timestamp date)
	{
		StringBuffer sql = new StringBuffer();
		try 
		{
			sql.append(" SELECT o.C_Order_ID, o.DocumentNo, coalesce(invoiceopen(i.c_invoice_ID, 0), o.grandtotal) as invoiceopen")
			     .append(", o.GrandTotal, b.Name, o.Processed, i.ispaid ")
				.append(" FROM C_Order o ")
				.append(" INNER JOIN C_BPartner b ON o.C_BPartner_ID=b.C_BPartner_ID")
				.append(" LEFT JOIN c_invoice i on i.c_order_ID = o.c_order_ID")
				.append(" WHERE o.C_POS_ID = " + p_pos.getC_POS_ID())
				.append(" AND coalesce(invoiceopen(i.c_invoice_ID, 0), 0)  >= 0 ")
				.append(" AND (i.ispaid='N' OR o.processed= "+ ( processed ? "'Y' )" : "'N' )"));
			if (doc != null && !doc.equalsIgnoreCase(""))
				sql.append(" AND (o.DocumentNo LIKE '%" + doc + "%' OR  i.DocumentNo LIKE '%" + doc + "%')");
			if ( date != null )
				sql.append(" AND trunc(o.DateOrdered) = ? ");
			sql.append(" ORDER BY o.DocumentNo DESC");
			
			PreparedStatement pstm = DB.prepareStatement(sql.toString(), null);
			if ( date != null )
				pstm.setTimestamp(1, date);
			ResultSet rs = pstm.executeQuery();
			m_table.loadTable(rs);
			if ( m_table.getRowCount() > 0 )
				m_table.setRowSelectionInterval(0, 0);
			enableButtons();
		}
		catch(Exception e)
		{
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
			
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
		
		f_ok.setEnabled(enabled);
		
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
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_c_order_id = ID.intValue(); 		
		
		if (m_c_order_id > 0)
		{
			p_posPanel.setOrder(m_c_order_id);
			p_posPanel.updateInfo();

		}
		dispose();
	}	//	close
	
}	//	QueryTicket

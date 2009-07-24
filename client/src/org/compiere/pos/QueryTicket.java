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

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VDate;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	POS Query Product
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Jose A.Gonzalez, Conserti.
 * 
 *  @version $Id: QueryTicket.java,v 0.9 $
 * 
 *  @Colaborador $Id: Consultoria y Soporte en Redes y Tecnologias de la Informacion S.L.
 * 
 */

public class QueryTicket extends PosSubPanel
	implements ActionListener, MouseListener, ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;

	/**
	 * 	Constructor
	 */
	public QueryTicket (PosPanel posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct

	/** The Table					*/
	private MiniTable		m_table;
	
	private CPanel 			northPanel;
	private CScrollPane 	centerScroll;
	private ConfirmPanel	confirm;
	
	private CTextField		f_c_order_id;
	private CTextField		f_documentno;
	private VDate			f_date;

	private CButton			f_up;
	private CButton			f_down;

	private int				m_c_order_id;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(QueryProduct.class);
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "C_Order_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "TotalLines"), "TotalLines", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "GrandTotal", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "Name", String.class)
	};

	/**
	 * 	Set up Panel
	 */
	protected void init()
	{
		setLayout(new BorderLayout(5,5));
		setVisible(false);
		//	North
		northPanel = new CPanel(new GridBagLayout());
		add (northPanel, BorderLayout.NORTH);
		northPanel.setBorder(new TitledBorder(Msg.getMsg(p_ctx, "Query")));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = PosSubPanel.INSETS2;
		//
		gbc.gridy = 0;
		gbc.gridx = GridBagConstraints.RELATIVE;
		CLabel lorder_id = new CLabel(Msg.translate(p_ctx, "C_Order_ID"));
		gbc.anchor = GridBagConstraints.EAST;
		northPanel.add (lorder_id, gbc);
		f_c_order_id = new CTextField(20);
		lorder_id.setLabelFor(f_c_order_id);
		gbc.anchor = GridBagConstraints.WEST;
		northPanel.add(f_c_order_id, gbc);
		f_c_order_id.addActionListener(this);
		//
		CLabel ldoc = new CLabel(Msg.translate(p_ctx, "DocumentNo"));
		gbc.anchor = GridBagConstraints.EAST;
		northPanel.add (ldoc, gbc);
		f_documentno = new CTextField(15);
		ldoc.setLabelFor(f_documentno);
		gbc.anchor = GridBagConstraints.WEST;
		northPanel.add(f_documentno, gbc);
		f_documentno.addActionListener(this);
		//
		gbc.gridy = 1;
		CLabel ldate = new CLabel(Msg.translate(p_ctx, "DateOrdered"));
		gbc.anchor = GridBagConstraints.EAST;
		northPanel.add (ldate, gbc);
		f_date = new VDate();
		f_date.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		ldate.setLabelFor(f_date);
		gbc.anchor = GridBagConstraints.WEST;
		northPanel.add(f_date, gbc);
		f_date.addActionListener(this);
		//
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = .1;
		f_up = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		northPanel.add(f_up, gbc);
		gbc.weightx = 0;
		f_down = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		northPanel.add(f_down, gbc);
		
		//	Confirm
		confirm = new ConfirmPanel (true, true, true, false, false, false, false);
		add (confirm, BorderLayout.SOUTH);
		confirm.addActionListener(this);

		//	Center
		m_table = new MiniTable();
		
		String sql = m_table.prepareTable (s_layout, "C_Order", 
				"C_DocTypeTarget_ID" + p_pos.getC_DocType_ID()
				, false, "C_Order")
			+ " ORDER BY Margin, QtyAvailable";
		m_table.setRowSelectionAllowed(true);
		m_table.setColumnSelectionAllowed(false);
		m_table.setMultiSelection(false);
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		enableButtons();
		centerScroll = new CScrollPane(m_table);
		add (centerScroll, BorderLayout.CENTER);
	}	//	init

	
	/**
	 * 	Get GridBagConstraints
	 *	@return constraints
	 */
	protected GridBagConstraints getGridBagConstraints ()
	{
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridwidth = 2; //	GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.weighty = 0.5;
		return gbc;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		removeAll();
		northPanel = null;
		centerScroll = null;
		confirm = null;
		m_table = null;
	}	//	dispose
	
	/**
	 * 	Set Visible
	 *	@param aFlag visible
	 */
	public void setVisible (boolean aFlag)
	{
		super.setVisible (aFlag);
		if (aFlag)
			f_c_order_id.requestFocus();
	}	//	setVisible

	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info("PosQueryProduct.actionPerformed - " + e.getActionCommand());
		if ("Refresh".equals(e.getActionCommand())
			|| e.getSource() == f_c_order_id || e.getSource() == f_documentno
			|| e.getSource() == f_date)
		{
			setResults(p_ctx, f_c_order_id.getText(), f_documentno.getText(), f_date.getTimestamp());
			return;
		}
		else if ("Reset".equals(e.getActionCommand()))
		{
			reset();
			return;
		}
		else if ("Previous".equalsIgnoreCase(e.getActionCommand()))
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
		else if ("Next".equalsIgnoreCase(e.getActionCommand()))
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
		//	Exit
		close();
	}	//	actionPerformed
	
	
	/**
	 * 
	 */
	public void reset()
	{
		f_c_order_id.setText(null);
		f_documentno.setText(null);
		f_date.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		setResults(p_ctx, f_c_order_id.getText(), f_documentno.getText(), f_date.getTimestamp());
	}


	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, String id, String doc, Timestamp date)
	{
		String sql = "";
		try 
		{
			sql = "SELECT o.C_Order_ID, o.DocumentNo, o.TotalLines, o.GrandTotal, b.Name FROM C_Order o INNER JOIN C_BPartner b ON o.C_BPartner_ID=b.C_BPartner_ID WHERE o.C_DocTypeTarget_ID = " + p_pos.getC_DocType_ID();
			if (id != null && !id.equalsIgnoreCase(""))
				sql += " AND o.C_Order_ID = " + id;
			if (doc != null && !doc.equalsIgnoreCase(""))
				sql += " AND o.DocumentNo = '" + doc + "'";
			sql += " AND o.DateOrdered = ? Order By o.DocumentNo";
			
			PreparedStatement pstm = DB.prepareStatement(sql, null);
			pstm.setTimestamp(1, date);
			ResultSet rs = pstm.executeQuery();
			m_table.loadTable(rs);
			enableButtons();
		}
		catch(Exception e)
		{
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
		}
	}	//	setResults
	
	/**
	 * 	Table selection changed
	 *	@param e event
	 */
	public void valueChanged (ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			return;
		enableButtons();
	}	//	valueChanged

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	private void enableButtons()
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
		confirm.getOKButton().setEnabled(enabled);
		log.info("ID=" + m_c_order_id); 
	}	//	enableButtons

	/**
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		//  Double click with selected row => exit
		if (e.getClickCount() > 1 && m_table.getSelectedRow() != -1)
		{
			enableButtons();
			close();
		}
	}   //  mouseClicked

	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	private void close()
	{
		log.info("C_Order_ID=" + m_c_order_id); 
		
		if (m_c_order_id > 0)
		{
			p_posPanel.f_curLine.setOrder(m_c_order_id);
			p_posPanel.updateInfo();

		}
		p_posPanel.closeQuery(this);
	}	//	close
	
}	//	PosQueryProduct

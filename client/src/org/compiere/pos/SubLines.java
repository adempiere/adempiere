/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.pos;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import org.compiere.grid.ed.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import java.util.logging.*;
import org.compiere.util.*;


/**
 *	POS All Lines Sub Panel
 *	
 *  @author Jorg Janke
 *  @version $Id: SubLines.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class SubLines extends PosSubPanel implements ActionListener
{
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public SubLines (PosPanel posPanel)
	{
		super (posPanel);
	}	//	PosSubAllLines
	
	/** The Table					*/
	private MiniTable		m_table;
	/** The Query SQL				*/
	private String			m_sql;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubLines.class);
	
	private CButton f_up;
	private CButton f_delete;
	private CButton f_down;
	//
	private VNumber f_net;
	private VNumber f_tax;
	private VNumber f_total;
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "C_OrderLine_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Line"), "Line", Integer.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), "QtyOrdered", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_UOM_ID"), "UOMSymbol", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "PriceActual"), "PriceActual", BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "LineNetAmt"), "LineNetAmt", BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Tax_ID"), "TaxIndicator", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "Description", String.class) 
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "C_Order_LineTax_v";
	/** Where Clause						*/
	private static String s_sqlWhere = "C_Order_ID=? AND LineNetAmt<>0"; 

	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Title
		TitledBorder border = new TitledBorder(Msg.translate(Env.getCtx(), "C_OrderLine_ID"));
		setBorder(border);
		
		//	Content
		setLayout(new BorderLayout(5, 5));
		m_table = new MiniTable();
		CScrollPane scroll = new CScrollPane(m_table);
		m_sql = m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "C_Order_LineTax_v")
			+ " ORDER BY Line";
		m_table.setRowSelectionAllowed(true);
		m_table.setColumnSelectionAllowed(false);
		m_table.setMultiSelection(false);
	//	m_table.addMouseListener(this);
	//	m_table.getSelectionModel().addListSelectionListener(this);
		scroll.setPreferredSize(new Dimension(100,100));
		add (scroll, BorderLayout.CENTER);
		
		//	Right side
		CPanel right = new CPanel();
		add (right, BorderLayout.EAST);
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		//
		right.add(Box.createGlue());
		f_up = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		right.add (f_up);
		right.add(Box.createGlue());
		f_delete = createButtonAction("Delete", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Event.SHIFT_MASK));
		right.add (f_delete);
		right.add(Box.createGlue());
		f_down = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		right.add (f_down);
		right.add(Box.createGlue());
		
		//	Summary
		FlowLayout summaryLayout = new FlowLayout(FlowLayout.LEADING, 2,0);
		CPanel summary = new CPanel(summaryLayout);
		add (summary, BorderLayout.SOUTH);
		//
		CLabel lNet = new CLabel (Msg.translate(Env.getCtx(), "TotalLines"));
		summary.add(lNet);
		f_net = new VNumber("TotalLines", false, true, false, DisplayType.Amount, "TotalLines");
		f_net.setColumns(6, 22);
		lNet.setLabelFor(f_net);
		summary.add(f_net);
		f_net.setValue (Env.ZERO);
		//
		CLabel lTax = new CLabel (Msg.translate(Env.getCtx(), "TaxAmt"));
		summary.add(lTax);
		f_tax = new VNumber("TaxAmt", false, true, false, DisplayType.Amount, "TaxAmt");
		f_tax.setColumns(6, 22);
		lTax.setLabelFor(f_tax);
		summary.add(f_tax);
		f_tax.setValue (Env.ZERO);
		//
		CLabel lTotal = new CLabel (Msg.translate(Env.getCtx(), "GrandTotal"));
		summary.add(lTotal);
		f_total = new VNumber("GrandTotal", false, true, false, DisplayType.Amount, "GrandTotal");
		f_total.setColumns(6, 22);
		lTotal.setLabelFor(f_total);
		summary.add(f_total);
		f_total.setValue (Env.ZERO);
		//
		f_delete.setReadWrite(false);
	}	//	init

	/**
	 * 	Get Panel Position
	 */
	public GridBagConstraints getGridBagConstraints()
	{
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.7;
		gbc.weighty = 0.7;
		return gbc;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		super.dispose();
	}	//	dispose
	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "PosSubAllLines - actionPerformed: " + action);
		if ("Previous".equalsIgnoreCase(e.getActionCommand()))
		{
			int rows = m_table.getRowCount();
			if (rows == 0)
				return;
			int row = m_table.getSelectedRow();
			row--;
			if (row < 0)
				row = 0;
			m_table.getSelectionModel().setSelectionInterval(row, row);
			f_delete.setReadWrite(true);
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
			f_delete.setReadWrite(true);
		}
		//	Delete
		else if (action.equals("Delete"))
		{
			
		}
	}	//	actionPerformed
	
	/**
	 * 	Update Table
	 *	@param order order
	 */
	public void updateTable (MOrder order)
	{
		int C_Order_ID = 0;
		if (order != null)
			C_Order_ID = order.getC_Order_ID();
		if (C_Order_ID == 0)
		{
			m_table.loadTable(new PO[0]);
			setSums(order);
		}
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (m_sql, null);
			pstmt.setInt (1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery ();
			m_table.loadTable(rs);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		setSums(order);
	}	//	updateTable

	/**
	 * 	Set Sums from Table
	 */
	private void setSums(MOrder order)
	{
		int noLines = m_table.getRowCount();
		p_posPanel.f_status.setStatusDB(noLines);
		if (order == null)
		{
			f_net.setValue(Env.ZERO);
			f_total.setValue(Env.ZERO);
			f_tax.setValue(Env.ZERO);
		}
		else
		{
			order.prepareIt();
			f_net.setValue(order.getTotalLines());
			f_total.setValue(order.getGrandTotal());
			f_tax.setValue(order.getGrandTotal().subtract(order.getTotalLines()));
		}
	}	//	setSums
	
	
}	//	PosSubAllLines

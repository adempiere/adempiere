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
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	All Lines Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @version $Id: SubLines.java,v 1.2 2004/07/21 05:37:51 jjanke Exp $
 */
public class SubLines extends PosSubPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1536223059244074580L;

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
		scroll.setPreferredSize(new Dimension(100,120));
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
		f_net.setColumns(11, 22);
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
		f_total.setColumns(11, 22);
		lTotal.setLabelFor(f_total);
		summary.add(f_total);
		f_total.setValue (Env.ZERO);
		//
		f_delete.setReadWrite(true);
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
		//	Delete
		else if (action.equals("Delete"))
		{
			int rows = m_table.getRowCount();
			if (rows != 0)
			{
				int row = m_table.getSelectedRow();
				if (row != -1)
				{
					p_posPanel.f_curLine.deleteLine(row);
				}
			}
		}
		p_posPanel.updateInfo();
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
			setSums(null);
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (m_sql, null);
			pstmt.setInt (1, C_Order_ID);
			rs = pstmt.executeQuery ();
			m_table.loadTable(rs);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
		if (order == null || noLines == 0) //red1 WORKAROUND (noLines == 0) means total and tax in order head is false.
		{
			f_net.setValue(Env.ZERO);
			f_total.setValue(Env.ZERO);
			f_tax.setValue(Env.ZERO);
		}
		else
		{
//			order.prepareIt(); //red1 Avoid Reserving Inventory until final process and update context directly from DB.
			p_posPanel.f_curLine.setOrder(order.getC_Order_ID());
			MOrder retValue = p_posPanel.f_curLine.getOrder();
			//red1 - end -
			f_net.setValue(retValue.getTotalLines());
			f_total.setValue(retValue.getGrandTotal());
			f_tax.setValue(retValue.getGrandTotal().subtract(retValue.getTotalLines()));

		}
	}	//	setSums
}	//	PosSubAllLines

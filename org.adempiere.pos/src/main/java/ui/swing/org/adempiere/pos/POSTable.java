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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import org.adempiere.pos.minigrid.ButtonRenderer;
import org.adempiere.pos.minigrid.DeleteColumn;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.minigrid.CheckRenderer;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IDColumnEditor;
import org.compiere.minigrid.IDColumnRenderer;
import org.compiere.minigrid.MiniCellEditor;
import org.compiere.minigrid.MiniTable;
import org.compiere.minigrid.ROCellEditor;
import org.compiere.swing.CCheckBox;
import org.compiere.util.DisplayType;
import org.compiere.util.Util;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSTable extends MiniTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7884238751207398699L;
	
	/**	For Scroll Bar	*/
	public static final int 	SCROLLBAR_WIDTH = 30;
	public static final int 	SCROLLBAR_HEIGHT = 30;
	
	/**
	 * *** Constructor ***
	 */
	public POSTable() {
		
		super();
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setMultiSelection(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(30);
		setAutoResize(true);
	}
	
	/**
	 * 
	 * @return void
	 */
	public void growScrollbars() {
		// fatter scroll bars
		Container p = getParent();
		if (p instanceof JViewport) {
			Container gp = p.getParent();
			if (gp instanceof JScrollPane) {
				JScrollPane scrollPane = (JScrollPane) gp;             
				scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(SCROLLBAR_WIDTH, SCROLLBAR_HEIGHT));
				scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(SCROLLBAR_WIDTH, SCROLLBAR_HEIGHT));
			}
		}
	}
	
	@Override
	public void setColumnClass (int index, @SuppressWarnings("rawtypes") Class c, int displayType ,boolean readOnly, String header)
	{
	//	log.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		TableColumn tc = getColumnModel().getColumn(index);
		if (tc == null)
			return;
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		//  Header
		if (header != null && header.length() > 0)
			tc.setHeaderValue(Util.cleanAmp(header));
		ArrayList<Integer>   m_minWidth = new ArrayList<Integer>();
		boolean m_multiSelection = false;
		//  ID Column & Selection
		if (c == IDColumn.class)
		{
			IDColumnRenderer idcr = new IDColumnRenderer(m_multiSelection);
			tc.setCellRenderer(idcr);
			if (m_multiSelection)
			{
				VHeaderRenderer vhr = new VHeaderRenderer(m_multiSelection);
				tc.setCellEditor(new IDColumnEditor());
				tc.setHeaderRenderer(vhr);
				idcr.addChangeListener(vhr);  //  Connect the IDColumn with the header
				setColumnReadOnly(index, false);
			}
			else
			{
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
			}
			m_minWidth.add(new Integer(10));
			tc.setMaxWidth(20);
			tc.setPreferredWidth(20);
			tc.setResizable(false);
		}
		//  Boolean
		else if (DisplayType.YesNo == displayType || c == Boolean.class )
		{
			CheckRenderer cr = new CheckRenderer();
			tc.setCellRenderer(cr);
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.YesNo));
			}
			else
			{
				if (m_multiSelection)
				{
					VHeaderRenderer vhr = new VHeaderRenderer(m_multiSelection);
					setColumnReadOnly(index, false);
					CCheckBox check = new CCheckBox();
					check.setMargin(new Insets(0,0,0,0));
					check.setHorizontalAlignment(SwingConstants.CENTER);
					tc.setCellEditor(new DefaultCellEditor(check));
					tc.setHeaderRenderer(vhr);
					cr.addChangeListener(vhr);  //  Connect the check control with the header
				}
			}
			m_minWidth.add(new Integer(30));
			
		}
		else if (c == DeleteColumn.class)
		{
			ButtonRenderer cr = new ButtonRenderer();
			
			tc.setCellRenderer(cr);
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				tc.setHeaderRenderer(new VHeaderRenderer());
			}
			m_minWidth.add(new Integer(30));
			
		}
		//  Date
		else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType ||  c == Timestamp.class )
		{
			if(DisplayType.DateTime == displayType)
				tc.setCellRenderer(new VCellRenderer(DisplayType.DateTime));
			else 
				tc.setCellRenderer(new VCellRenderer(DisplayType.Date));
			
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType)
				tc.setCellEditor(new MiniCellEditor(c, displayType));
			else 
				tc.setCellEditor(new MiniCellEditor(c));
			
			m_minWidth.add(new Integer(30));
			if (DisplayType.DateTime == displayType)
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.DateTime));
			else 
				tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Date));
		}
		//  Amount
		else if (DisplayType.Amount == displayType || c == BigDecimal.class )
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Amount));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(new Integer(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(new Integer(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Number
		else if (DisplayType.Number == displayType || c == Double.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Number));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(new Integer(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(new Integer(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Integer
		else if (DisplayType.Integer == displayType || c == Integer.class )
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Integer));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(c));
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  String
		else
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.String));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(String.class));
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.String));
		}
	//	log.fine( "Renderer=" + tc.getCellRenderer().toString() + ", Editor=" + tc.getCellEditor().toString());
	}

	
}
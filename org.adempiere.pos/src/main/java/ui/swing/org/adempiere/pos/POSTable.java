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
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
	//	logger.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		TableColumn tableColumn = getColumnModel().getColumn(index);
		if (tableColumn == null)
			return;
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		//  Header
		if (header != null && header.length() > 0)
			tableColumn.setHeaderValue(Util.cleanAmp(header));
		ArrayList<Integer>   minWidth = new ArrayList<Integer>();
		boolean multiSelection = false;
		//  ID Column & Selection
		if (c == IDColumn.class)
		{
			IDColumnRenderer columnRenderer = new IDColumnRenderer(multiSelection);
			tableColumn.setCellRenderer(columnRenderer);
			if (multiSelection)
			{
				VHeaderRenderer headerRenderer = new VHeaderRenderer(multiSelection);
				tableColumn.setCellEditor(new IDColumnEditor());
				tableColumn.setHeaderRenderer(headerRenderer);
				columnRenderer.addChangeListener(headerRenderer);  //  Connect the IDColumn with the header
				setColumnReadOnly(index, false);
			}
			else
			{
				tableColumn.setCellEditor(new ROCellEditor());
				tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
			}
			minWidth.add(new Integer(10));
			tableColumn.setMaxWidth(20);
			tableColumn.setPreferredWidth(20);
			tableColumn.setResizable(false);
		}
		//  Boolean
		else if (DisplayType.YesNo == displayType || c == Boolean.class )
		{
			CheckRenderer checkRenderer = new CheckRenderer();
			tableColumn.setCellRenderer(checkRenderer);
			if (readOnly)
			{
				tableColumn.setCellEditor(new ROCellEditor());
				tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.YesNo));
			}
			else
			{
				if (multiSelection)
				{
					VHeaderRenderer headerRenderer = new VHeaderRenderer(multiSelection);
					setColumnReadOnly(index, false);
					CCheckBox check = new CCheckBox();
					check.setMargin(new Insets(0,0,0,0));
					check.setHorizontalAlignment(SwingConstants.CENTER);
					tableColumn.setCellEditor(new DefaultCellEditor(check));
					tableColumn.setHeaderRenderer(headerRenderer);
					checkRenderer.addChangeListener(headerRenderer);  //  Connect the check control with the header
				}
			}
			minWidth.add(new Integer(30));
			
		}
		/*else if (c == DeleteColumn.class)
		{
			ButtonRenderer buttonRenderer = new ButtonRenderer();
			
			tableColumn.setCellRenderer(buttonRenderer);
			if (readOnly)
			{
				tableColumn.setCellEditor(new ROCellEditor());
				tableColumn.setHeaderRenderer(new VHeaderRenderer());
			}
			minWidth.add(new Integer(30));
			
		}*/
		//  Date
		else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType ||  c == Timestamp.class )
		{
			if(DisplayType.DateTime == displayType)
				tableColumn.setCellRenderer(new VCellRenderer(DisplayType.DateTime));
			else 
				tableColumn.setCellRenderer(new VCellRenderer(DisplayType.Date));
			
			if (readOnly)
				tableColumn.setCellEditor(new ROCellEditor());
			else if (DisplayType.Date == displayType || DisplayType.DateTime == displayType)
				tableColumn.setCellEditor(new MiniCellEditor(c, displayType));
			else 
				tableColumn.setCellEditor(new MiniCellEditor(c));
			
			minWidth.add(new Integer(30));
			if (DisplayType.DateTime == displayType)
				tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.DateTime));
			else 
				tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.Date));
		}
		//  Amount
		else if (DisplayType.Amount == displayType || c == BigDecimal.class )
		{
			tableColumn.setCellRenderer(new VCellRenderer(DisplayType.Amount));
			if (readOnly)
			{
				tableColumn.setCellEditor(new ROCellEditor());
				minWidth.add(new Integer(70));
			}
			else
			{
				tableColumn.setCellEditor(new MiniCellEditor(c));
				minWidth.add(new Integer(80));
			}
			
			tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Number
		else if (DisplayType.Number == displayType || c == Double.class)
		{
			tableColumn.setCellRenderer(new VCellRenderer(DisplayType.Number));
			if (readOnly)
			{
				tableColumn.setCellEditor(new ROCellEditor());
				minWidth.add(new Integer(70));
			}
			else
			{
				tableColumn.setCellEditor(new MiniCellEditor(c));
				minWidth.add(new Integer(80));
			}
			
			tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Integer
		else if (DisplayType.Integer == displayType || c == Integer.class )
		{
			tableColumn.setCellRenderer(new VCellRenderer(DisplayType.Integer));
			if (readOnly)
				tableColumn.setCellEditor(new ROCellEditor());
			else
				tableColumn.setCellEditor(new MiniCellEditor(c));
			minWidth.add(new Integer(30));
			
			tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  String
		else
		{
			tableColumn.setCellRenderer(new VCellRenderer(DisplayType.String));
			if (readOnly)
				tableColumn.setCellEditor(new ROCellEditor());
			else
				tableColumn.setCellEditor(new MiniCellEditor(String.class));
			minWidth.add(new Integer(30));
			
			tableColumn.setHeaderRenderer(new VHeaderRenderer(DisplayType.String));
		}
	//	logger.fine( "Renderer=" + tc.getCellRenderer().toString() + ", Editor=" + tc.getCellEditor().toString());
	}

	
}
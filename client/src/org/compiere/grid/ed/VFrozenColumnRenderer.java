/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.grid.ed;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.compiere.grid.VTable;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTable;
import org.compiere.swing.ExtendedTheme;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;

/**
 * A class that add the ability to hide, freeze or unfreeze columns based in
 * a VTable 
 * 
 * @author Michael McKay, mckayERP@gmail.com
 * 
 * @version 3.9.4
 *
 */
public class VFrozenColumnRenderer extends VTable 
					implements PropertyChangeListener, ChangeListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2009512017635649135L;

	/**************************************************************************
	 *  MouseListener for the header - deals with header popup menu only
	 */
	class VTableHeaderMouseListener extends MouseAdapter
	{
		
		private VFrozenColumnRenderer adaptee;

		/**
		 *  Constructor
		 */
		public VTableHeaderMouseListener(VFrozenColumnRenderer adaptee)
		{
			super();
			this.adaptee = adaptee;			
		}   //  CTableMouseListener

		/**
		 *  Mouse clicked
		 *  @param e event
		 */
		public void mouseClicked (MouseEvent e)
		{
		}
		
		
		public void mouseReleased(MouseEvent e) {
			//  The popup action is added to both mouseRelease and mousePressed
			//  for cross-platform compatibility. Only one gets used on any 
			//  platform.
			if (e.isPopupTrigger())
			{
				adaptee.showFreezeColumnPopup(e);
				e.consume();
			}
        }


		public void mousePressed(MouseEvent e) {
			//  The popup action is added to both mouseRelease and mousePressed
			//  for cross-platform compatibility. Only one gets used on any 
			//  platform.
			if (e.isPopupTrigger())
			{
				adaptee.showFreezeColumnPopup(e);
				e.consume();
			}
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
        }

	}	//  VTableHeaderMouseListener

	private VTable mainTable;
	
	private CMenuItem freezeMenuItem;
	private CMenuItem hideMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "Hide"));
 		
	private int freezeColumnCandidate;

	private Color viewportBG;

	public VFrozenColumnRenderer(VTable table)
	{
		super();
		mainTable = table;
		mainTable.addPropertyChangeListener( this );
		mainTable.getModel().addTableModelListener( this );
		mainTable.setFrozenColumnRenderer(this);
		setGridController(mainTable.getGridController());
		
		VTableHeaderMouseListener listener = new VTableHeaderMouseListener(this);
		mainTable.getTableHeader().addMouseListener(listener);
		this.getTableHeader().addMouseListener(listener);
		
		//  TODO allow resizing of the frozen columns.  Currently
		//  This causes the column to resize but the row header 
		//  area remains the same width, resulting in blank space or
		//  the loss of the width control if the the size is increased 
		//  beyond the row header. 
		this.getTableHeader().setResizingAllowed(false);

		this.setAutoCreateColumnsFromModel(false);

		// The frozen columns are from the same model.
		// We are just going to manipulate the view
		this.setModel(mainTable.getModel());
		
		setFocusable( true );
		setSelectionModel( mainTable.getSelectionModel() );
		
		viewportBG = UIManager.getColor(ExtendedTheme.TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_NORMAL);
		
		hideMenuItem.addActionListener(this);
		popupMenu.add(hideMenuItem);
		
	}

	/**
	 * @return the mainTable
	 */
	public CTable getMainTable() {
		return mainTable;
	}

	@Override
	public void addNotify()
	{
		super.addNotify();

		Component c = getParent();

		//  Keep scrolling of the row table in sync with the main table.

		if (c instanceof JViewport)
		{
			JViewport viewport = (JViewport)c;
			viewport.addChangeListener( this );
		}
	}

	/*
	 *  Delegate method to main table
	 */
	@Override
	public int getRowCount()
	{
		return mainTable.getRowCount();
	}

	@Override
	public int getRowHeight(int row)
	{
		int rowHeight = mainTable.getRowHeight(row);

		if (rowHeight != super.getRowHeight(row))
		{
			super.setRowHeight(row, rowHeight);
		}

		return rowHeight;
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		//  Keep the scrolling of the row table in sync with main table

		JViewport viewport = (JViewport) e.getSource();
		JScrollPane scrollPane = (JScrollPane)viewport.getParent();
		scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		//  Keep the row table in sync with the main table

		if ("selectionModel".equals(e.getPropertyName()))
		{
			setSelectionModel( mainTable.getSelectionModel() );
		}

		if ("rowHeight".equals(e.getPropertyName()))
		{
			repaint();
		}

		if ("model".equals(e.getPropertyName()))
		{
			// TODO - changing models should remove all the frozen columns
			this.setModel(mainTable.getModel());
			mainTable.getModel().addTableModelListener( this );
			revalidate();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		revalidate();
	}
	

	private void hideColumn(int columnIndex) {

		TableColumnModel tcm = mainTable.getColumnModel(); // The visible columns
		int currentRow = mainTable.getSelectedRow();
		mainTable.setColumnVisibility(tcm.getColumn(columnIndex), false);
		mainTable.changeSelection(currentRow, columnIndex, false, false);

	}

	private void freezeColumns(int columnIndex) {

		if (columnIndex >= 0 && columnIndex < mainTable.getColumnModel().getColumnCount())
		{

			//  The orientation will determine which side will hold the frozen
			//  columns.  In either case, the lower column indexes will be frozen.
			TableColumnModel tcm = mainTable.getColumnModel(); // The visible columns
			TableColumnModel fcs = this.getColumnModel();
			
			int currentRow = mainTable.getSelectedRow();

			for (int i = 0; i <= columnIndex; i++)
			{
				TableColumn tc = tcm.getColumn(0);
				fcs.addColumn(tc);
				tcm.removeColumn(tc);  // This puts the next column into the "0" position
//				mainTable.setColumnVisibility(tcm.getColumn(i), false);
			}
			
			//  Attach the Frozen columns to the view
			showFrozenColumns(true);
			mainTable.setHasFrozenColumns(true);
			
			//  Shift the selection to the first cell in the remaining
			//  main table columns
			mainTable.changeSelection(currentRow, 0, false, false);
			
		}
		else
		{
			// Remove the columns from this table move them to 
			// the first position in the main table
			TableColumnModel tcm = mainTable.getColumnModel();
			TableColumnModel fcs = this.getColumnModel();
			
			while (fcs.getColumnCount() > 0)
			{
				int i = fcs.getColumnCount()-1;
				TableColumn fc = fcs.getColumn(i);
				fcs.removeColumn(fc);
				tcm.addColumn(fc);
				tcm.moveColumn(tcm.getColumnCount()-1, 0);
			}

			// Remove the frozen column renderer
			showFrozenColumns(false);
			mainTable.setHasFrozenColumns(false);
			
		}
		
	}

	private void showFrozenColumns(boolean show) {
		
		JScrollPane scrollPane = null;
		Container parent = SwingUtilities.getUnwrappedParent(mainTable);
		if (parent instanceof JViewport)
			scrollPane = (JScrollPane) ((JViewport) parent).getParent();
		
		if (scrollPane == null)
			return;

		if (show)
		{
			// If nothing to show, return.
			if (getColumnModel().getColumnCount() == 0)
				return;

			Border border = BorderFactory.createMatteBorder(0,0,0,2, UIManager.getColor("primary0"));
			this.setBorder(border);
			this.getTableHeader().setBorder(border);
			scrollPane.setRowHeaderView((Component) this);
			scrollPane.getRowHeader().setBackground(viewportBG);
			scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, 
										this.getTableHeader());
			
			// Create the dummy empty spot in the bottom left
			// corner that was occupied by the scroll bar
			JLabel dummy = new JLabel("");
			border = BorderFactory.createMatteBorder(1,0,0,1, PlasticLookAndFeel.getControlDarkShadow());
			dummy.setBorder(border);
			scrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER, 
					dummy);
		}
		else
		{
			scrollPane.setRowHeaderView(null);
			scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, null);			
		}
		setPreferredScrollableViewportSize(getPreferredSize());
//		scrollPane.revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		
		if (mainTable.isFreezeColumnsEnabled())
		{
			if (e.getActionCommand().equals("Freeze"))
			{
				//  Freeze the column that was clicked-on and all
				//  columns to the right/left according to the direction
				//  of the table.
				freezeColumns(freezeColumnCandidate);
			}
			else if (e.getActionCommand().equals("Unfreeze"))
			{
				freezeColumns(-1);
			}
			if (e.getActionCommand().equals("Hide"))
			{
				hideColumn(freezeColumnCandidate);
			}
		}		
	}

    private void showFreezeColumnPopup(MouseEvent e) {
    	
		TableColumnModel tcm = mainTable.getColumnModel();
		freezeColumnCandidate = tcm.getColumnIndexAtX(e.getX());
		TableColumn column = tcm.getColumn(freezeColumnCandidate);

    	if(mainTable.isFreezeColumnsEnabled())
 		{
 			 			 
 			String menuText = "";
 			String action = "";
 			
 			if (VFrozenColumnRenderer.this.getColumnModel().getColumnCount() > 0)
 			{
 				menuText = Msg.getMsg(Env.getCtx(), "Unfreeze columns");
 				action = "Unfreeze";
 			}
 			else
 			{
 				menuText = Msg.getMsg(Env.getCtx(), "Freeze columns");
 				action = "Freeze";
 			}
 			
 			if (freezeMenuItem == null)
 			{
 				freezeMenuItem = new CMenuItem (menuText);
 				freezeMenuItem.addActionListener((ActionListener) this);
 				popupMenu.add(freezeMenuItem);
 			}
 			freezeMenuItem.setText(menuText);
 			freezeMenuItem.setActionCommand(action);
 			
 		}					

    	//  If the column is not visible, can't hide it.
    	//  Also, frozen columns are simply hidden in the main table.  
    	//  Don't hide frozen columns.
    	if (e.getSource() instanceof JTableHeader &&
    		((JTableHeader) e.getSource()).getTable() instanceof VFrozenColumnRenderer)
    	{
    		hideMenuItem.setVisible(false);
    	}
    	else
    	{
    		hideMenuItem.setVisible(true);
    	}
		
 		popupMenu.show((Component)e.getSource(), e.getX(), e.getY());

 	}

}


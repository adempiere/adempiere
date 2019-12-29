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

package org.adempiere.plaf;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import sun.swing.SwingUtilities2;

/**
 * Extends the BasicTableHeaderUI to allows dragged columns to be dragged when
 * the mouse is outside the table and to support frozen columns.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *
 * @version 3.9.4
 * 
 */
public class AdempiereTableHeaderUI extends BasicTableHeaderUI {

	/**
	 * Copied from the BasicTableHeaderUI to allow dragged columns to continue dragging
	 * when the mouse is outside of the scroll pane. This helps for very wide tables with 
	 * many columns.  As some of the required super classes are private, they are recreated
	 * here.  The main requirements is to change the function of updateRollOverColumn
	 * 
	 */
    public class MouseInputHandler extends BasicTableHeaderUI.MouseInputHandler {

    	private final Cursor resizeCursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);

        private int mouseXOffset;
        private Cursor otherCursor = resizeCursor;

        public void mouseClicked(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            if (e.getClickCount() % 2 == 1 &&
                    SwingUtilities.isLeftMouseButton(e)) {
                JTable table = header.getTable();
                RowSorter<?> sorter;
                if (table != null && (sorter = table.getRowSorter()) != null) {
                    int columnIndex = header.columnAtPoint(e.getPoint());
                    if (columnIndex != -1) {
                        columnIndex = table.convertColumnIndexToModel(
                                columnIndex);
                        sorter.toggleSortOrder(columnIndex);
                    }
                }
            }
        }

        private TableColumn getResizingColumn(Point p) {
            return getResizingColumn(p, header.columnAtPoint(p));
        }

        private TableColumn getResizingColumn(Point p, int column) {
            if (column == -1) {
                 return null;
            }
            Rectangle r = header.getHeaderRect(column);
            r.grow(-3, 0);
            if (r.contains(p)) {
                return null;
            }
            int midPoint = r.x + r.width/2;
            int columnIndex;
            if( header.getComponentOrientation().isLeftToRight() ) {
                columnIndex = (p.x < midPoint) ? column - 1 : column;
            } else {
                columnIndex = (p.x < midPoint) ? column : column - 1;
            }
            if (columnIndex == -1) {
                return null;
            }
            return header.getColumnModel().getColumn(columnIndex);
        }

        public void mousePressed(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            header.setDraggedColumn(null);
            header.setResizingColumn(null);
            header.setDraggedDistance(0);

            Point p = e.getPoint();

            // First find which header cell was hit
            TableColumnModel columnModel = header.getColumnModel();
            int index = header.columnAtPoint(p);

            if (index != -1) {
                // The last 3 pixels + 3 pixels of next column are for resizing
                TableColumn resizingColumn = getResizingColumn(p, index);
                if (canResize(resizingColumn, header)) {
                    header.setResizingColumn(resizingColumn);
                    if( header.getComponentOrientation().isLeftToRight() ) {
                        mouseXOffset = p.x - resizingColumn.getWidth();
                    } else {
                        mouseXOffset = p.x + resizingColumn.getWidth();
                    }
                }
                else if (header.getReorderingAllowed()) {
                    TableColumn hitColumn = columnModel.getColumn(index);
                    header.setDraggedColumn(hitColumn);
                    mouseXOffset = p.x;
                }
            }

            if (header.getReorderingAllowed()) {
                int oldRolloverColumn = rolloverColumn;
                rolloverColumn = -1;
                rolloverColumnUpdated(oldRolloverColumn, rolloverColumn);
            }
        }

        private void swapCursor() {
            Cursor tmp = header.getCursor();
            header.setCursor(otherCursor);
            otherCursor = tmp;
        }

        public void mouseMoved(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            if (canResize(getResizingColumn(e.getPoint()), header) !=
                (header.getCursor() == resizeCursor)) {
                swapCursor();
            }
            updateRolloverColumn(e);
       }

        public void mouseDragged(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            int mouseX = e.getX();

            TableColumn resizingColumn  = header.getResizingColumn();
            TableColumn draggedColumn  = header.getDraggedColumn();

            boolean headerLeftToRight = header.getComponentOrientation().isLeftToRight();

            if (resizingColumn != null) {
                int oldWidth = resizingColumn.getWidth();
                int newWidth;
                if (headerLeftToRight) {
                    newWidth = mouseX - mouseXOffset;
                } else  {
                    newWidth = mouseXOffset - mouseX;
                }
                mouseXOffset += changeColumnWidth(resizingColumn, header,
                                                  oldWidth, newWidth);
            }
            else if (draggedColumn != null) {
                TableColumnModel cm = header.getColumnModel();
                int draggedDistance = mouseX - mouseXOffset;
                int direction = (draggedDistance < 0) ? -1 : 1;
                int columnIndex = viewIndexForColumn(draggedColumn);
                int newColumnIndex = columnIndex + (headerLeftToRight ? direction : -direction);
                if (0 <= newColumnIndex && newColumnIndex < cm.getColumnCount()) {
                    int width = cm.getColumn(newColumnIndex).getWidth();
                    if (Math.abs(draggedDistance) > (width / 2)) {

                        mouseXOffset = mouseXOffset + direction * width;
                        header.setDraggedDistance(draggedDistance - direction * width);

                        //Cache the selected column.
                        int selectedIndex =
                                SwingUtilities2.convertColumnIndexToModel(
                                        header.getColumnModel(),
                                        getSelectedColumnIndex());

                        //Now do the move.
                        cm.moveColumn(columnIndex, newColumnIndex);

                        //Update the selected index.
                        selectColumnImp(
                            SwingUtilities2.convertColumnIndexToView(
                                    header.getColumnModel(), selectedIndex),
                            false);

                        return;
                    }
                }
                setDraggedDistance(draggedDistance, columnIndex);
                
                int headerX = header.getBounds().x;
                int headerW = header.getBounds().width;
                // Make sure the dragged column is visible
                if (headerX + mouseX < 1)
                {
                	Point upperLeft = new Point(0,0);
                	if (header.getParent() instanceof JScrollPane)
                		upperLeft = ((JScrollPane) header.getParent()).getViewport().getViewPosition();
                	else
                		upperLeft = ((JViewport) header.getParent()).getViewPosition();
                	
                    int col = header.getTable().columnAtPoint(upperLeft);
                    int row = header.getTable().rowAtPoint(upperLeft);
                    Rectangle rect = header.getTable().getCellRect(row, col, false);
                    rect.x += (header.getBounds().x + mouseX);
                    rect.width = 1;
                    header.getTable().scrollRectToVisible(rect);
                }
                else
                {
                	Point upperRight = new Point(headerX+headerW-1,0);
                	if (header.getParent() instanceof JScrollPane)
                	{
                		upperRight = ((JScrollPane) header.getParent()).getViewport().getViewPosition();
                		upperRight.x += ((JScrollPane) header.getParent()).getViewport().getWidth();
                	}
                	else
                	{
                		upperRight = ((JViewport) header.getParent()).getViewPosition();
                		upperRight.x += ((JViewport) header.getParent()).getWidth();
                	}
                	                	
                	if (mouseX > upperRight.x)
                	{
	                		                	
	                    int col = header.getTable().columnAtPoint(upperRight);
	                    int row = header.getTable().rowAtPoint(upperRight);
	                    if (col < header.getTable().getColumnCount()-1)
	                    {
		                    Rectangle rect = header.getTable().getCellRect(row, col+1, false);
		                    header.getTable().scrollRectToVisible(rect);
	                    }
                	}                	
                }
                
            }

            updateRolloverColumn(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            setDraggedDistance(0, viewIndexForColumn(header.getDraggedColumn()));

            header.setResizingColumn(null);
            header.setDraggedColumn(null);

            updateRolloverColumn(e);
        }

        public void mouseEntered(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            updateRolloverColumn(e);
        }

        public void mouseExited(MouseEvent e) {
            if (!header.isEnabled()) {
                return;
            }
            int oldRolloverColumn = rolloverColumn;
            rolloverColumn = -1;
            rolloverColumnUpdated(oldRolloverColumn, rolloverColumn);
        }

        private void setDraggedDistance(int draggedDistance, int column) {
            header.setDraggedDistance(draggedDistance);
            if (column != -1) {
                header.getColumnModel().moveColumn(column, column);
            }
        }

    }  //  MouseInputHandler

    /** The column header over which the mouse currently is. */
    private int rolloverColumn = -1;
    
    /** The column that should be highlighted when the table header has the focus. */
    private int selectedColumnIndex = 0; // Read ONLY via getSelectedColumnIndex!
    
    
    public static ComponentUI createUI(JComponent h) {
        return new AdempiereTableHeaderUI();
    }

    
	
    /** Can the column be resized */
    private boolean canResize(TableColumn column,
            JTableHeader header) {
    	return (column != null) && header.getResizingAllowed()
    			&& column.getResizable();
    }

    /** Update the rollover column with a new position */
    private void updateRolloverColumn(MouseEvent e) {
    	
        if (header.getDraggedColumn() == null &&
            header.contains(e.getPoint())) {

            int col = header.columnAtPoint(e.getPoint());
            if (col != rolloverColumn) {
                int oldRolloverColumn = rolloverColumn;
                rolloverColumn = col;
                rolloverColumnUpdated(oldRolloverColumn, rolloverColumn);
            }
        }
    }

    /**
     * Change the column width
     * @param resizingColumn
     * @param th
     * @param oldWidth
     * @param newWidth
     * @return
     */
    private int changeColumnWidth(TableColumn resizingColumn,
    		JTableHeader th,
    		int oldWidth, int newWidth) {
    	
    	resizingColumn.setWidth(newWidth);

    	Container container;
    	JTable table;

    	boolean parentIsScrollPane = false;
    	
    	container = th.getParent();
    	if (container != null && container instanceof JScrollPane)
    	{
    		parentIsScrollPane = true;
    		container.revalidate();
    		container.getParent().revalidate();
    	}
    	else 
    	{
    		container = container.getParent();
        	if (container != null && container instanceof JScrollPane)
        		parentIsScrollPane = true;
    	}
    	//  For frozen columns, the header parent container is a JScrollPane
    	//  For the regular table, its a viewport within a JScrollPane
    	if (!parentIsScrollPane ||
    			((table = th.getTable()) == null)) {
    		return 0;
    	}

    	if (!container.getComponentOrientation().isLeftToRight() &&
    			!th.getComponentOrientation().isLeftToRight()) {
    		JViewport viewport = ((JScrollPane)container).getViewport();
    		int viewportWidth = viewport.getWidth();
    		int diff = newWidth - oldWidth;
    		int newHeaderWidth = table.getWidth() + diff;

    		/* Resize a table */
    		Dimension tableSize = table.getSize();
    		tableSize.width += diff;
    		table.setSize(tableSize);

    		/* If this table is in AUTO_RESIZE_OFF mode and
    		 * has a horizontal scrollbar, we need to update
    		 * a view's position.
    		 */
    		if ((newHeaderWidth >= viewportWidth) &&
    				(table.getAutoResizeMode() == JTable.AUTO_RESIZE_OFF)) {
    			Point p = viewport.getViewPosition();
    			p.x = Math.max(0, Math.min(newHeaderWidth - viewportWidth,
    					p.x + diff));
    			viewport.setViewPosition(p);
    			return diff;
    		}
    	}
    	return 0;
    }

    /**
     * Change the view index for the column
     * @param aColumn
     * @return
     */
    private int viewIndexForColumn(TableColumn aColumn) {
        TableColumnModel cm = header.getColumnModel();
        for (int column = 0; column < cm.getColumnCount(); column++) {
            if (cm.getColumn(column) == aColumn) {
                return column;
            }
        }
        return -1;
    }
    
    /**
     * Get the currently selected column Index
     * @return the column index
     */
    private int getSelectedColumnIndex() {
        int numCols = header.getColumnModel().getColumnCount();
        if (selectedColumnIndex >= numCols && numCols > 0) {
            selectedColumnIndex = numCols - 1;
        }
        return selectedColumnIndex;
    }
    
    /**
     * Selects the specified column in the table header. Repaints the
     * affected header cells and makes sure the newly selected one is visible.
     */
	void selectColumnImp(int newColIndex) {
        selectColumnImp(newColIndex, true);
    }

    void selectColumnImp(int newColIndex, boolean doScroll) {
        Rectangle repaintRect = header.getHeaderRect(selectedColumnIndex);
        header.repaint(repaintRect);
        selectedColumnIndex = newColIndex;
        repaintRect = header.getHeaderRect(newColIndex);
        header.repaint(repaintRect);
        if (doScroll) {
            scrollToColumn(newColIndex);
        }
        return;
    }
    
    /**
     * Used by selectColumn to scroll horizontally, if necessary,
     * to ensure that the newly selected column is visible.
     */
    private void scrollToColumn(int col) {
        Container container;
        JTable table;

        //Test whether the header is in a scroll pane and has a table.
        if ((header.getParent() == null) ||
            ((container = header.getParent().getParent()) == null) ||
            !(container instanceof JScrollPane) ||
            ((table = header.getTable()) == null)) {
            return;
        }

        //Now scroll, if necessary.
        Rectangle vis = table.getVisibleRect();
        Rectangle cellBounds = table.getCellRect(0, col, true);
        vis.x = cellBounds.x;
        vis.width = cellBounds.width;
        table.scrollRectToVisible(vis);
    }

    /**
     * Creates the mouse listener for the JTableHeader.
     */
    @Override
    protected MouseInputListener createMouseInputListener() {
        return new MouseInputHandler();
    }

}

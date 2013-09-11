/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
package org.adempiere.webui.component;

import java.util.Comparator;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.adempiere.webui.util.SortComparator;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.ListModel;
//import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.ListitemComparator;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GridTableListModel extends AbstractListModel implements TableModelListener, Sortable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 698185856751242764L;
	private GridTable tableModel;
	private GridField[] gridField;
	private int windowNo;
	
	private int pageSize = -1;
	private int pageNo = 0;

	private boolean editing = false;

	/**
	 * 
	 * @param tableModel
	 * @param windowNo
	 */
	public GridTableListModel(GridTable tableModel, int windowNo) {
		this.tableModel = tableModel;
		this.windowNo = windowNo;
		gridField = tableModel.getFields();
		tableModel.addTableModelListener(this);
	}

	/**
	 * 获取选择行的所有字段的数据
	 * 考虑到了分页，如果分页了，根据页码和每页的行数量进行计算。
	 * @param rowIndex
	 * @see ListModel#getElementAt(int)
	 */
	public Object getElementAt(int rowIndex) {
		int columnCount = tableModel.getColumnCount();
		Object[] values = new Object[columnCount];
		if (pageSize > 0) {
			rowIndex = (pageNo * pageSize) + rowIndex;
		}
		if (rowIndex < tableModel.getRowCount()) {
			for (int i = 0; i < columnCount; i++) {
				values[i] = tableModel.getValueAt(rowIndex, i);
			}
		}
		return values;
	}
	
	/**
	 * 设置页码
	 * 		  重新计算开始的值
	 * set current page no ( starting from 0 )
	 * @param pg
	 */
	public void setPage(int pg) {
		if (pageNo != pg) {
			if (pg > 0) {
				int start = pg * pageSize;
				if (start >= tableModel.getRowCount()) {
					return;
				}
			}
			pageNo = pg;
			fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
		}
	}
	
	/**
	 * 获取页码
	 * @return current page no ( starting from 0 )
	 */
	public int getPage() {
		return pageNo;
	}
	
	/**
	 * 设置每页显示数量
	 * Set number of rows per page
	 * @param pgSize
	 */
	public void setPageSize(int pgSize) {
		pageSize = pgSize;
	}

	/**
	 * 获取每页显示数量
	 * Get number of rows per page
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 获取页面的总行数
	 * 		如果是1页，返回:数据库行数
	 * 		如果是多页，返回：计算值
	 * Get total number of rows
	 * @return int
	 * @see ListModel#getSize()
	 */
	public int getSize() {
		int total = tableModel.getRowCount(); 
		if (pageSize <= 0)
			return total;
		else if ((total - ( pageNo * pageSize)) < 0) {
			pageNo = 0;
			return pageSize > total ? total : pageSize;
		} else {
			int end = (pageNo + 1) * pageSize;
			if (end > total)
				return total - ( pageNo * pageSize);
			else
				return pageSize;
		}
	}
	
	/**
	 * 更新某一行的数据
	 * Request components that attached to this model to re-render a row.
	 * @param row
	 */
	public void updateComponent(int row) {
		updateComponent(row, row);
	}
	
	/**
	 * 更新多行的数据
	 * Request components that attached to this model to re-render a range of row.
	 * @param fromRow 开始行号
	 * @param toRow		结束行号
	 */
	public void updateComponent(int fromRow, int toRow) {
		//must run from the UI thread
		if (Executions.getCurrent() != null) {
			fireEvent(ListDataEvent.CONTENTS_CHANGED, fromRow, toRow);
		}
	}

	/**
	 * 排序
	 * @param cmpr		比较类（支持两种比较类ListitemComparator、SortComparator）
	 * @param ascending	是否是升序
	 * @see ListModelExt#sort(Comparator, boolean) 
	 */
	@SuppressWarnings("unchecked")
	public void sort(Comparator cmpr, boolean ascending) {
		//use default zk comparator
		if (cmpr instanceof ListitemComparator) {			
			ListitemComparator lic = (ListitemComparator) cmpr;
			tableModel.sort(lic.getListheader().getColumnIndex(), ascending);
		} else if (cmpr instanceof SortComparator) {
			SortComparator sc = (SortComparator)cmpr;
			tableModel.sort(sc.getColumnIndex(), ascending);
		}
		fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
	}

	/**
	 * 表格发生更改
	 * @param e
	 * @see TableModelListener#tableChanged(TableModelEvent) 
	 */
	public void tableChanged(TableModelEvent e) {
		if (Executions.getCurrent() != null) {
			if (e.getType() == TableModelEvent.DELETE) 
			{
				if (pageSize > 0)
				{
					int pgIndex = e.getFirstRow() % pageSize;
					fireEvent(ListDataEvent.CONTENTS_CHANGED, pgIndex, getSize());
				}
				else
					fireEvent(ListDataEvent.INTERVAL_REMOVED, e.getFirstRow(), e.getLastRow());
			}
			else if (e.getType() == TableModelEvent.INSERT)
			{
				if (pageSize > 0)
				{
					int pgIndex = e.getFirstRow() % pageSize;
					fireEvent(ListDataEvent.CONTENTS_CHANGED, pgIndex, getSize());
				}
				else
					fireEvent(ListDataEvent.INTERVAL_ADDED, e.getFirstRow(), e.getLastRow());
			}
			else if (e.getLastRow() == Integer.MAX_VALUE)
			{
				fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
			}
			else
			{
				if (!editing)
					fireEvent(ListDataEvent.CONTENTS_CHANGED, e.getFirstRow(), e.getLastRow());
			}
		}
	}

	/**
	 * 设置是否可修改
	 * @param b
	 */
	public void setEditing(boolean b) {
		editing = b;
	}

	@Override
	public String getSortDirection(Comparator arg0) {
		return "natural";
	}

}

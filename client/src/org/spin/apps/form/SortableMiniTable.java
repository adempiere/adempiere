/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 *************************************************************************************/
package org.spin.apps.form;

import org.compiere.minigrid.MiniTable;

/**
 * MiniTable that is able to keep the sort chosen by the user when the model is replaced.
 *
 * CTable does not use a RowSorter, it sorts the data vector in place and keeps the state on
 * p_lastSortIndex / p_asc, which are reset as soon as a new model is set. Those fields are
 * protected, so this subclass can read them before the reload and apply the same sort again.
 *
 * @author Carlos Parada, cparada@erpya.com , http://www.erpya.com
 */
public class SortableMiniTable extends MiniTable {

	private static final long serialVersionUID = 1L;

	/**
	 * Column used on the last sort
	 * @return model column index, -1 when the table is not sorted
	 */
	public int getSortColumnIndex() {
		return p_lastSortIndex;
	}

	/**
	 * Direction of the last sort
	 * @return true when it is ascending
	 */
	public boolean isSortAscending() {
		return p_asc;
	}

	/**
	 * Apply a sort over the current model, it is used to restore the sort after a reload
	 * @param columnIndex model column index, ignored when it is negative
	 * @param ascending
	 */
	public void applySort(int columnIndex, boolean ascending) {
		if (columnIndex < 0
				|| columnIndex >= getColumnCount()
				|| getRowCount() == 0) {
			return;
		}
		//	CTable.sort toggles the direction when the column is the same one, so the state is
		//	prepared to end up on the requested direction
		p_lastSortIndex = columnIndex;
		p_asc = !ascending;
		sort(columnIndex);
	}

	/**
	 * Sort applied on a table, taken before the model is replaced and applied again after it
	 */
	public static class SortState {

		private final int columnIndex;
		private final boolean ascending;

		private SortState(int columnIndex, boolean ascending) {
			this.columnIndex = columnIndex;
			this.ascending = ascending;
		}

		/**
		 * Take the sort currently applied on a table
		 * @param table
		 * @return
		 */
		public static SortState of(SortableMiniTable table) {
			return new SortState(table.getSortColumnIndex(), table.isSortAscending());
		}

		/**
		 * Apply the sort again, it does nothing when the table was not sorted
		 * @param table
		 */
		public void restore(SortableMiniTable table) {
			table.applySort(columnIndex, ascending);
		}
	}
}

package org.compiere.apps.search;

import java.awt.Frame;

public interface InfoFactory {

	/**
	 *  Factory Constructor
	 *  @param  frame   parent frame
	 *  @param  modal   new window is modal
	 *  @param  WindowNo	window no
	 *  @param  tableName   table name of the search
	 *  @param  keyColumn   key column of the search
	 *  @param	value		query value
	 *  @param  multiSelection  allow to select more than one row
	 *  @param  whereClause fully qualified where clause for the search
	 *  @return special or general Info Window
	 */
	@Deprecated
	public Info create (Frame frame, boolean modal, int WindowNo,
		String tableName, String keyColumn, String value,
		boolean multiSelection, String whereClause);
	
	/**
	 *  Factory Constructor
	 *  @param  frame   parent frame
	 *  @param  modal   new window is modal
	 *  @param  WindowNo	window no
	 *  @param  tableName   table name of the search
	 *  @param  keyColumn   key column of the search
	 *  @param  record_id The record ID to find
	 *  @param  value query value to find, exclusive of record_id
	 *  @param  multiSelection  allow to select more than one row
	 *  @param  whereClause fully qualified where clause for the search
	 *  @return special or general Info Window
	 *  
	 *
	 * @author Michael McKay, 
	 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
	 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
	 */
	public Info create (Frame frame, boolean modal, int WindowNo,
		String tableName, String keyColumn, int record_id, String value,
		boolean multiSelection, String whereClause);
}

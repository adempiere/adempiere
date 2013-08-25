package org.adempiere.webui.panel;

public interface InfoPanelFactory {

	
	/**
	 *  Factory Constructor
     * @param WindowNo  WindowNo
     * @param modal	Open the window as modal
     * @param tableName tableName
     * @param keyColumn keyColumn
     * @param  record_id The record ID to find
	 * @param  value query value to find, exclusive of record_id
	 * @param  multiSelection  allow to select more than one row
	 * @param saveResults flag if the results will be saved in context
     * @param whereClause   whereClause
	 *  
	 *
	 * @author Michael McKay, 
	 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
	 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
	 */
	public InfoPanel create(int WindowNo, boolean modal,
			String tableName, String keyColumn, int record_id, String value, 
			boolean multipleSelection, boolean saveResults, String whereClause);
}

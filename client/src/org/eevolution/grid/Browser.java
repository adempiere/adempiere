/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.eevolution.grid;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.impexp.ArrayExcelExporter;
import org.adempiere.model.I_AD_View_Column;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * Abstract Smart Browser <li>FR [ 3426137 ] Smart Browser
 * https://sourceforge.net
 * /tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 242 ] Parent Window Number, is not put in Browse constructor
 * 		@see https://github.com/adempiere/adempiere/issues/242
 * 		<li>FR [ 245 ] Change Smart Browse to MVC
 * 		@see https://github.com/adempiere/adempiere/issues/245
 * 		<li>FR [ 246 ] Smart Browse validate parameters when is auto-query
 * 		@see https://github.com/adempiere/adempiere/issues/246
 * 		<li>BR [ 253 ] Selection fields is not saved in T_Selection_Browse
 * 		@see https://github.com/adempiere/adempiere/issues/253
 * 		<li>BR [ 257 ] Smart Browse does not get the hidden fields in Selection Browse
 * 		@see https://github.com/adempiere/adempiere/issues/257
 * 		<li>BR [ 318 ] Problem with context parameters filter in smart browser #318
 * 		@see https://github.com/adempiere/adempiere/issues/318
 * 		<li>BR [ 344 ] Smart Browse Search View is not MVC
 * 		@see https://github.com/adempiere/adempiere/issues/344
 * 		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352 * 
 * 		<li>BR [ 456 ] Smart Browser fill bad value for search
 * 		@see https://github.com/adempiere/adempiere/issues/456
 */
public abstract class Browser {
	static public LinkedHashMap<String, Object> getBrowseValues(
			int AD_PInstance_ID, String alias, int recordId, String trxName) {
		LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> parameters = new ArrayList<Object>();
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT ColumnName , Value_String, Value_Date , Value_Number FROM T_Selection_Browse "
							+ "WHERE  AD_PInstance_ID=? AND T_Selection_ID=? ");
			parameters.add(AD_PInstance_ID);
			parameters.add(recordId);
			
			if(alias != null)
			{	
				sql.append("AND ColumnName LIKE ?");
				parameters.add(alias.toUpperCase() + "_%");
			}	
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String columnName = rs.getString(1);
				String valueString = rs.getString(2);
				Timestamp valueDate = rs.getTimestamp(3);
				BigDecimal valueBigDecimal = rs.getBigDecimal(4);
				if (valueString != null) {
					values.put(columnName, valueString);
					continue;
				} else if (valueDate != null) {
					values.put(columnName, valueDate);
					continue;
				} else if (valueBigDecimal != null) {
					values.put(columnName, valueBigDecimal);
					continue;
				} else values.put(columnName, valueString);
			}

		} catch (SQLException ex) {
			throw new DBException(ex);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return values;
	}
	
	/** Smart Browse */
	private MBrowse browseModel = null;
	/** Smart View */
	private MView view = null;

	public static final int WINDOW_WIDTH = 1024; // width of the window

	/** list of query columns */
	private ArrayList<String> m_queryColumns = new ArrayList<String>();
	/** list of query columns (SQL) */
	private ArrayList<String> m_queryColumnsSql = new ArrayList<String>();
	
	/** Parameters */
	private ArrayList<Object> parameters;
	/** Parameters */
	private ArrayList<Object> parametersValues;
	/** Parameters */
	private ArrayList<Object> axisParameters;
	/** Parameters */
	private ArrayList<Object> axisParametersValues;
	/** Parameters */
	private ArrayList<GridFieldVO> parametersField;
	/** Cache m_whereClause **/
	private String whereClause = ""; 
	
	/** MProcess process */
	private MProcess process = null;
	/** ProcessInfo */
	private ProcessInfo processInfo = null;
	/** Browse Process Info */
	private ProcessInfo browsePI = null;

	/** Loading success indicator */
	public boolean loadedOK = false;
	/** Model Index of Key Column */
	public int keyColumnIndex = -1;
	/** OK pressed */
	public boolean m_ok = false;
	/** Result IDs */
	private ArrayList<Integer> m_results = new ArrayList<Integer>(3);
	/** Result Values */
	private LinkedHashMap<Integer, LinkedHashMap<String, Object>> values = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();
	/** Logger */
	public CLogger log = CLogger.getCLogger(getClass());

	/** Layout of Grid */
	public List<MBrowseField> browserFields;

	public String m_sqlMain;
	/** Count SQL Statement */
	public String m_sqlCount;
	/** Order By Clause */
	public String m_sqlOrderBy;
	/** Master (owning) Window */
	public int windowNo;
	/** Table Name */
	public String p_FromClause;
	/** Key Column Name */
	public String p_keyColumn;
	/** Enable more than one selection */
	public boolean isMultiSelection;
	/** Initial WHERE Clause */
	public String p_whereClause = "";
	/** Window Width */
	public static final int INFO_WIDTH = 800;
	public boolean isAllSelected = false;
	/** Exporter */
	private Exporter m_exporter = null;
	/** Language **/
	private Language m_language = null;
	/** Export rows **/
	protected ArrayList<ArrayList<Object>> m_rows = new ArrayList<ArrayList<Object>>();
	//	BR [ 242 ]
	private int parentWindowNo;
	
	/**
	 * Standard Contructor
	 * @param modal
	 * @param WindowNo
	 * @param value
	 * @param browse
	 * @param keyColumn
	 * @param multiSelection
	 * @param where
	 */
	public Browser(boolean modal, int WindowNo, String value, MBrowse browse,
                   String keyColumn, boolean multiSelection, String where) {
		browseModel = browse;
		view = browse.getAD_View();
		p_keyColumn = keyColumn;
		isMultiSelection = multiSelection;
		m_language = Language.getLanguage(Env
				.getAD_Language(browseModel.getCtx()));
		//	
		parentWindowNo = WindowNo;
		
		log.info(browseModel.getName() + " - " + keyColumn + " - " + p_whereClause);
	}
	
	/**
	 * Parse context in where clause
	 * @param where
	 */
	public void setContextWhere(String where) {
		p_whereClause = null;
		
		String whereClause = where != null ? where : "";

		if(browseModel.getWhereClause() != null )
			   whereClause = whereClause + browseModel.getWhereClause();
		else
				whereClause = " 1=1 ";
		if (whereClause.indexOf('@') == -1)
			p_whereClause = whereClause;
		else {
			//	BR [ 242 ]
			p_whereClause = Env.parseContext(Env.getCtx(), getWindowNo(),
					whereClause, false, false);
			if (p_whereClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
		}

		log.info(browseModel.getName() + " - " + p_whereClause);
	}
	
	/**
	 * Copy Context from parent window
	 * @param fromWindowNo
	 * @param toWindowNo
	 */
	public void copyWinContext() {
		//
		Object[] keys = Env.getCtx().keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			String tag = keys[i].toString();
			if (tag.startsWith(getParentWindowNo()+"|")) {
				String context = tag.substring(tag.lastIndexOf("|") + 1);
				String value = Env.getContext(Env.getCtx(), getParentWindowNo(), context);
				Env.setContext(Env.getCtx(), getWindowNo(), context, value);
			}
		}
	}	//	copyWinContext
	
	/**
	 * Initialize data of browser
	 * @return
	 */
	public void initBrowserData() {

		browserFields = new ArrayList<MBrowseField>();
		MBrowseField fieldKey =  browseModel.getFieldKey();
		if(fieldKey != null) {
			browserFields.add(fieldKey);
		} else {
			MViewColumn column = new MViewColumn(browseModel.getCtx() , 0 , browseModel.get_TrxName());
			column.setName("Row");
			column.setColumnSQL("'Row' AS \"Row\"");


			MBrowseField browseField = new MBrowseField(browseModel , column);
			browseField.setAD_Reference_ID(DisplayType.ID);
			browseField.setIsKey(true);
			browseField.setIsReadOnly(false);
		}
		//	
		for (MBrowseField field : browseModel.getDisplayFields()) {
			//	
			if (field.isQueryCriteria()) {
				m_queryColumns.add(field.getName());
			}
			m_queryColumnsSql.add(field.getAD_View_Column().getColumnSQL());

			if(field.isKey())
				continue;

			// Defines Field as Y-Axis
			if(field.getAxis_Column_ID() > 0)
			{
				for (MBrowseField fieldAxis : getInfoColumnForAxisField(field)){
					browserFields.add(fieldAxis);
				}
				continue;
			}
			browserFields.add(field);
		}
	}

	/**
	 * Get Axis Parameters
	 * @return
	 */
	public ArrayList<Object> getAxisParameters() {
		return axisParameters;
	}

	/**
	 * Get Axis parameters
	 * @return
	 */
	public ArrayList<Object> getAxisParametersValues() {
		return axisParametersValues;
	}

	/**
	 * get Parameters Name (ColumnSQL) for search
	 * @return
	 */
	public ArrayList<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * Get Parameters Value (Values of parameters in Search)
	 * @return
	 */
	public ArrayList<Object> getParametersValues() {
		return parametersValues;
	}
	
	public void addSQLWhere(StringBuffer sql, int index, String value) {
		if (!(value.equals("") || value.equals("%"))
				&& index < m_queryColumns.size()) {
			// sql.append(" AND UPPER(").append(m_queryColumnsSql.get(index).toString()).append(") LIKE '");
			sql.append(" UPPER(")
					.append(m_queryColumnsSql.get(index).toString())
					.append(") LIKE '");
			sql.append(value);
			if (value.endsWith("%"))
				sql.append("'");
			else
				sql.append("%'");
		}
	}

	/**
	 * Test Row Count
	 * 
	 * @return > 0 if display
	 */
	public int testCount() {
		int no = -1;

		no = getCount();
		// log.fine("#" + no + " - " + (System.currentTimeMillis()-start) +
		// "ms");
		MRole role = MRole.getDefault();
		if (role.isQueryMax(no))
			return no;
		//	Default
		return -1;
	} // testCount
	
	/**
	 * Get row Quantity
	 * @return
	 */
	public int getCount() {
		long start = System.currentTimeMillis();
		String dynWhere = getSQLWhere(true);
		StringBuffer sql = new StringBuffer(m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND

		//	BR [ 318 ]
		String countSql = Env.parseContext(Env.getCtx(), getWindowNo(), sql.toString(), true, true); // Variables
		//	
		countSql = MRole.getDefault().addAccessSQL(countSql,
				view.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finer(countSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = -1;
		try {
			pstmt = DB.prepareStatement(countSql, null);
			if (getParametersValues() != null && getParametersValues().size() > 0)
				DB.setParameters(pstmt, getParametersValues());
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1);
		} catch (Exception e) {
			log.log(Level.SEVERE, countSql, e);
			no = -2;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		log.fine("#" + no + " - " + (System.currentTimeMillis() - start) + "ms");

		return no;
	}
	
	/**
	 * BR [ 246 ]
	 * Validate if has mandatory parameters
	 * @return
	 */
	public boolean hasMandatoryParams() {
		for (Entry<String, GridField> entry : getPanelParameters().entrySet()) {
			GridField editor = (GridField) entry.getValue();
			if(editor.isMandatory(true))
				return true;
		}
		//	Default
		return false;
	}
	
	/**
	 * FR [ 245 ]
	 * Initialize process info
	 */
	public void initProcessInfo() {
		process = MProcess.get(Env.getCtx(), browseModel.getAD_Process_ID());
		browsePI = new ProcessInfo(process.getName(), browseModel.getAD_Process_ID());
		browsePI.setAD_User_ID(Env.getAD_User_ID(Env.getCtx()));
		browsePI.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		browsePI.setWindowNo(getWindowNo());
		browsePI.setIsSelection(true);
		//	Copy Values
		if(processInfo != null) {
			browsePI.setTable_ID(processInfo.getTable_ID());
			browsePI.setRecord_ID(processInfo.getRecord_ID());
		}
	}
	
	/**
	 * Get Process ID
	 * @return
	 */
	public int getAD_Process_ID() {
		return browseModel.getAD_Process_ID();
	}
	
	/**
	 * Get View Name
	 * @return
	 */
	public String getViewName() {
		return view.getName();
	}
	
	/**
	 * Get Browser Name
	 * @return
	 */
	public String getBrowserName() {
		return browseModel.getName();
	}
	
	/**
	 * FR [ 245 ]
	 * FR [ 344 ] Add ColumnSQL like columns
	 * Get Where Clause
	 * @param refresh
	 * @return
	 */
	public String getSQLWhere(boolean refresh) {
		
		if(!refresh)
			return whereClause;

		StringBuilder sql = new StringBuilder(p_whereClause);

		//	Valid null
		LinkedHashMap<String, GridField> panelParameters = getPanelParameters();
		if(panelParameters == null
				|| panelParameters.size() == 0) {
			whereClause = sql.toString();
			return whereClause;
		}
		//
		parametersValues = new ArrayList<Object>();
		parameters = new ArrayList<Object>();

		boolean onRange = false;

		for (Entry<String, GridField> entry : panelParameters.entrySet()) {
			GridField editor = (GridField) entry.getValue();
			GridFieldVO field = editor.getVO();
			if (!onRange) {

				if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& !field.IsRange) {
					sql.append(" AND ");
					if(DisplayType.String == field.displayType)
					{
						if (field.ColumnName.equals("Value")
								|| field.ColumnName.equals("DocumentNo"))
						{
							String value = (String)editor.getValue();
							if (value.contains(","))
							{
								value = value.replace(" ", "");
								String inStr = new String(value);
								StringBuffer outStr = new StringBuffer("(");
								int i = inStr.indexOf(',');
								while (i != -1)
								{
									outStr.append("'" + inStr.substring(0, i) + "',");	
									inStr = inStr.substring(i+1, inStr.length());
									i = inStr.indexOf(',');

								}
								outStr.append("'" + inStr + "')");
								//	BR [ 342 ]
								sql.append(field.ColumnSQL).append(" IN ")
								.append(outStr);
							}						
						}
						else
						{
							sql.append(field.ColumnSQL).append(" LIKE ? ");
							parameters.add(field.ColumnSQL);
							parametersValues.add("%" + editor.getValue() + "%");
						}		
					}
					else
					{
						sql.append(field.ColumnSQL).append("=? ");
						parameters.add(field.ColumnSQL);
						parametersValues.add(editor.getValue());
					}
				} 
				else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.IsRange) {
					sql.append(" AND ");
					//sql.append(field.Help).append(" BETWEEN ?");
					sql.append(field.ColumnSQL).append(" >= ? ");
					parameters.add(field.ColumnSQL);
					parametersValues.add(editor.getValue());
					onRange = true;
				}
				else if (editor.getValue() == null
						&& field.IsRange) {
					onRange = true;
				} else
					continue;
			} else if (editor.getValue() != null
					&& !editor.getValue().toString().isEmpty()) {
				//sql.append(" AND ? ");
				sql.append(" AND ").append(field.ColumnSQL).append(" <= ? ");
				parameters.add(field.ColumnSQL);
				parametersValues.add(editor.getValue());
				onRange = false;
			}
			else
				onRange = false;
		}
		whereClause = sql.toString();
		return sql.toString();
	}
	
	/**
	 * FR [ 245 ]
	 * Set Parameters
	 */
	public void setParameters() {
		parametersValues = new ArrayList<Object>();
		parameters = new ArrayList<Object>();
		parametersField = new ArrayList<GridFieldVO>();
		boolean onRange = false;
		
		for (Entry<String, GridField> entry : getPanelParameters().entrySet()) {
			GridField editor = (GridField) entry.getValue();
			GridFieldVO field = editor.getVO();
			if (!onRange) {

				if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& !field.IsRange) {
					parameters.add(field.ColumnNameAlias);
					parametersValues.add(editor.getValue());
					parametersField.add(field);
				} else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.IsRange) {
					parameters.add(field.ColumnNameAlias);
					parametersValues.add(editor.getValue());
					parametersField.add(field);
					onRange = true;
				} else
					continue;
			} else if (editor.getValue() != null
					&& !editor.getValue().toString().isEmpty()) {
				parameters.add(field.ColumnNameAlias);
				parametersValues.add(editor.getValue());
				parametersField.add(field);
				onRange = false;
			}
		}
	}

	/**
	 * FR [ 245 ]
	 * Get a Parameter value from a key
	 * @param key
	 * @return
	 */
	public Object getParameterValue(Object key) {
		GridField field = getPanelParameters().get(key);
		//	
		if(field != null)
			return field.getValue();
		else
			return null;
	}

	
	/**
	 * FR [ 245 ]
	 * save result values
	 * @param browseTable
	 */
	protected void saveResultSelection(IBrowserTable browseTable) {
		if (keyColumnIndex == -1) {
			return;
		}
		//	Verify if is Multi-Selection
		if (isMultiSelection) {
			int rows = browseTable.getRowCount();
			BrowserRow browserRows = browseTable.getData();
			values = new LinkedHashMap<Integer,LinkedHashMap<String,Object>>();
			//	BR [ 257 ]
			List <MBrowseField> fields = browseModel.getFields();
			for (int row = 0; row < rows; row++) {
				//Find the IDColumn Key
				GridField selectedGridField = (GridField)browserRows.getValue(row,
						keyColumnIndex);
				//	Get Value
				Object data = selectedGridField.getValue();
				//	
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						LinkedHashMap<String, Object> selectedValues = new LinkedHashMap<String, Object>();
						for(MBrowseField field : fields) {
							if (!field.isReadOnly() || field.isIdentifier() || field.isKey()) {
								GridField gridField = (GridField) browserRows.getValueOfColumn(row, field.getAD_View_Column().getColumnName());
								if (gridField != null) {
									Object value = gridField.getValue();
									//	Parse value to standard values
									if (value instanceof IDColumn) {
										IDColumn id = (IDColumn) value;
										value = id.getRecord_ID();
									} else if (value instanceof Double) {
										value = BigDecimal.valueOf((Double) value);
									} else if (value instanceof Date) {
										value = new Timestamp(((Date) value).getTime());
									}
									//	Set
									selectedValues.put(field.getAD_View_Column().getColumnName(), value);
								}
							}
						}
						//	
						if(selectedValues.size() > 0) {
							values.put(dataColumn.getRecord_ID(), selectedValues);
						}
					}
				}
			}
		}
	}
	
	/**
	 * FR [ 245 ]
	 * Get Data of rows
	 * @param browserTable
	 * @return
	 */
	public  ArrayList<ArrayList<Object>> getDataRows(IBrowserTable browserTable) {
		ArrayList<ArrayList<Object>> rows = m_rows;
		if (isShowTotal()) {
			ArrayList<Object> row = new ArrayList<Object>(rows.size());
			BrowserRow data = browserTable.getData();
			int lastRow = browserTable.getRowCount() - 1;
			for (int column = 0 ; column < data.getColumnCount() ; column++) {
				GridField gridField = data.getValue(lastRow , column);
				Object value = null;
				if (gridField != null)
					value = gridField.getValue();

				if (value == null)
					row.add(null);
				else
					row.add(value);
			}
			rows.add(row);
		}
		return rows;
	}
	
	/**
	 * FR [ 245 ]
	 * Save Selection - Called by dispose
	 * @param browserTable
	 */
	protected void saveSelection(IBrowserTable browserTable) {
		// Already disposed
		if (browserTable == null)
			return;

		log.config("OK=" + m_ok);
		if (!m_ok) // did not press OK
		{
			m_results.clear();
			return;
		}

		// Multi Selection
		if (isMultiSelection) {
			m_results.clear();
			m_results.addAll(getSelectedRowKeys(browserTable));
		} else // singleSelection
		{
			Integer data = getSelectedRowKey(browserTable);
			if (data != null)
				m_results.add(data);
		}
	} // saveSelection
	
	/**
	 * FR [ 245 ]
	 */
	public void selectedRows(IBrowserTable browserTable) {
		int topIndex = browserTable.isShowTotals() ? 2 : 1;
		int rows = browserTable.getRowCount();

		if (isAllSelected) {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = browserTable.getValueAt(row, keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(true);
					browserTable.setValueAt(dataColumn, row, keyColumnIndex);
				}
			}

		} else {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = browserTable.getValueAt(row, keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(false);
					browserTable.setValueAt(dataColumn, row, keyColumnIndex);
				}
			}
		}
		isAllSelected = !isAllSelected;
	}
	
	/**
	 * Get the keys of selected row/s based on layout defined in prepareTable
	 * @param browseTable
	 * @return IDs if selection present
	 */
	public ArrayList<Integer> getSelectedRowKeys(IBrowserTable browseTable) {
		ArrayList<Integer> selectedDataList = new ArrayList<Integer>();

		if (keyColumnIndex == -1) {
			return selectedDataList;
		}

		if (isMultiSelection) {
			int rows = browseTable.getRowCount();
			for (int row = 0; row < rows; row++) {
				Object data = browseTable.getValueAt(row,
						keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						selectedDataList.add(dataColumn.getRecord_ID());
					}
				}
			}
		}

		if (selectedDataList.size() == 0) {
			int row = browseTable.getSelectedRow();
			if (row != -1 && keyColumnIndex != -1) {
				Object data = browseTable.getValueAt(row,
						keyColumnIndex);
				if (data instanceof IDColumn)
					selectedDataList.add(((IDColumn) data).getRecord_ID());
				if (data instanceof Integer)
					selectedDataList.add((Integer) data);
			}
		}

		return selectedDataList;
	}
	
	/**
	 * Init info with Table. - find QueryColumns (Value, Name, ..) - build
	 * gridController & column
	 * @param table table to initialize
	 * @return void
	 */
	public void initBrowserTable(IBrowserTable table) {
		// Clear Table
		table.setRowCount(0);
		//	
		if(browserFields != null)
			return;
		//	
		initBrowserData();
		
		log.finest("Browse Fields #" + browserFields.size());
		//	
		prepareTable(table);
	} // initInfoTable
	
	/**************************************************************************
	 * Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd) and size Window
	 * @param table table to prepare
	 */
	private void prepareTable(IBrowserTable table) {
		//	Get values
		setContextWhere(null);
		String from = view.getFromClause();
		//	
		StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
		sql.append(table.prepareTable(browserFields, isMultiSelection));
		// Table Selection (Invoked before setting column class so that row
		// selection is enabled)
		table.setMultiSelection(isMultiSelection);
		table.setShowTotals(browseModel.isShowTotal());
		//	
		sql.append(" FROM ").append(from);
		sql.append(" WHERE ");
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE ";
		m_sqlOrderBy = getSQLOrderBy();

		if (keyColumnIndex == -1)
			log.log(Level.WARNING, "No KeyColumn - " + sql);
	} // prepareTable
	
	/**
	 * Set process info (called)
	 * @param pi
	 */
	public void setProcessInfo(ProcessInfo pi) {
		processInfo = pi;
		if(processInfo != null)
			if(	browsePI !=null) {
				browsePI.setRecord_ID(processInfo.getRecord_ID());
				browsePI.setTable_ID(processInfo.getTable_ID());
			}
	}

	/**
	 * Get Process info (called)
	 * @return
	 */
	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	/**
	 * Set process info for browser from other
	 * @param pi
	 */
	public void setBrowseProcessInfo(ProcessInfo pi) {
		browsePI = pi;
	}

	/**
	 * Get process info for browser
	 * @return
	 */
	public ProcessInfo getBrowseProcessInfo() {
		return browsePI;
	}
	
	public String getKeyColumn() {
		if(p_keyColumn == null || p_keyColumn.isEmpty())
			p_keyColumn = browseModel.getFieldKey().getAD_View_Column().getColumnName();
		
		return p_keyColumn;
	}

	/**
	 * Get a Selected key from row
	 * @param browseTable
	 * @return
	 */
	public Integer getSelectedRowKey(IBrowserTable browseTable) {
		ArrayList<Integer> selectedDataList = getSelectedRowKeys(browseTable);
		if (selectedDataList.size() == 0) {
			return null;
		} else {
			return selectedDataList.get(0);
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Integer> getSelectedKeys() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results;
	}

	/**
	 * Get keys selected
	 * @return
	 */
	public Object getSelectedKey() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.get(0);
	}

	/**
	 * Delete a Selection
	 * @param browseTable
	 * @return
	 */
    protected int deleteSelection(IBrowserTable browseTable) {
    	MTable table = getTable();
        //	
        if(table == null
        		|| table.getAD_Table_ID() <= 0) {
        	return 0;
        }
        int records = 0 ;
        for (int id : getSelectedRowKeys(browseTable)) {
        	table.getPO(id, null).deleteEx(true);
            records++;
        }
        return records;
    }
	
    /**
     * Get Table
     * @return
     */
    private MTable getTable() {
    	MTable table = MTable.get(browseModel.getCtx(), browseModel.getAD_Table_ID());
    	if(table != null
    			&& table.getAD_Table_ID() > 0) {
    		return table;
    	}
    	//	
    	MBrowseField fieldKey  = browseModel.getFieldKey();
        if (fieldKey != null) {
        	if (fieldKey.getAD_View_Column().getAD_Column_ID() > 0) {
        		table = (MTable) fieldKey.getAD_View_Column().getAD_Column().getAD_Table();
        	}
        }
        //	Return 
        return table;
    }
    
	/**
	 * Delete a Selection
	 * @param browseTable
	 * @return
	 */
    protected int updateSelection(IBrowserTable browseTable) {
        MTable table = getTable();
        //	
        if(table == null
        		|| table.getAD_Table_ID() <= 0) {
        	return 0;
        }
        int records = 0 ;
        List<MColumn> columns = table.getColumnsAsList();
        List<ValueNamePair> converter = new ArrayList<ValueNamePair>();
        for(MBrowseField field : browseModel.getDisplayFields()) {
        	if (!field.isKey()
					&& !field.isReadOnly()) {
				if(field.getAD_Element_ID() == 0) {
					continue;
				}
				//	
				for(MColumn column : columns) {
					if(column.getAD_Element_ID() == field.getAD_Element_ID()) {
						converter.add(new ValueNamePair(field.getAD_View_Column().getColumnName(), column.getColumnName()));
						break;
					}
				}
        	}
        }
        //	Validate if is for delete
        if(converter.size() == 0) {
        	return 0;
        }
        saveResultSelection(browseTable);
        for(Entry<Integer,LinkedHashMap<String, Object>> row : getSelectedValues().entrySet()) {
        	PO model = table.getPO(row.getKey(), null);
        	for(ValueNamePair columnName : converter) {
        		model.set_ValueOfColumn(columnName.getName(), row.getValue().get(columnName.getValue()));
        	}
        	//	
        	model.saveEx();
        	records++;
        }
        return records;
    }
    
    /**
     * Is Selected by default
     * @return true if is Selected by default
     */
	protected boolean isSelectedByDefault()
	{
		return browseModel.isSelectedByDefault();
	}
	
	/**
	 * Is execute query by default
	 * @return true if is execute query by default
	 */
	protected boolean isExecuteQueryByDefault()
	{
		return browseModel.isExecutedQueryByDefault();
	}

	/**
	 * Is Collapsible by default
	 * @return true if is collapsible by default
	 */
	protected boolean isCollapsibleByDefault()
	{
		return browseModel.isCollapsibleByDefault();
	}
	
	/**
	 * Is Deleteable records
	 * @return true if is deleteable
	 */
	protected boolean isDeleteable() {
		return browseModel.isDeleteable();
	}
	
	/**
	 * Is Updateable records
	 * @return true if is deleteable
	 */
	protected boolean isUpdateable() {
		return browseModel.isUpdateable();
	}
	
	/**
	 * Is Show Totals
	 * Return
	 * @return true if it show total row
	 */
	protected boolean isShowTotal()
	{
		return browseModel.isShowTotal();
	}
	
	/**
	 * Get Window Identifier
	 * @return
	 */
	protected int getAD_Window_ID()
	{
		return browseModel.getAD_Window_ID();
	}
	
	/**
	 * Get Browser Identifier
	 * @return
	 */
	public int getAD_Browse_ID() {
		return browseModel.getAD_Browse_ID();
	}

	/**
	 * Get Info_Column for Axis Field
	 * @param field defined as Axis
	 * @return Info_Column with Axis Field
	 */
	public List<MBrowseField> getInfoColumnForAxisField(MBrowseField field)
	{
		List<MBrowseField> list = new ArrayList<MBrowseField>();
		axisParameters = new ArrayList<>();
		axisParametersValues = new ArrayList<>();

		try {
			I_AD_View_Column xcol, pcol, ycol;
			xcol = field.getAD_View_Column();
			pcol = field.getAxis_Parent_Column();
			ycol = field.getAxis_Column();

			String columnName = xcol.getAD_Column().getColumnName();

			MBrowseField fieldKey = ((MBrowse) field.getAD_Browse()).getFieldKey();
			if(fieldKey == null)
				throw new AdempiereException("@NotFound@ @IsKey@");

			MTable xTable = (MTable) ycol.getAD_View_Definition().getAD_Table();
			String xTableName = xTable.getTableName();

			String keyColumn = MQuery.getZoomColumnName(columnName);
			String tableName = MQuery.getZoomTableName(columnName);

			String whereClause =  "";

			if (pcol != null && pcol.getAD_View_Column_ID() > 0)
			{
				MTable parentTable = MTable.get(field.getCtx(), tableName);
				MColumn parentColumn = getParentColumn(parentTable.getAD_Table_ID());
				if (parentColumn == null)
					throw new AdempiereException("@NotFound@ @IsParent@");
				//	BR [ 242 ]
				if(field.getAD_Val_Rule_ID() > 0)
					whereClause = Env.parseContext(Env.getCtx(), getWindowNo() , field.getAD_Val_Rule().getCode(), false);

			}


			MLookup lookup = MLookupFactory.get(Env.getCtx(), 0,
					xcol.getAD_Column_ID(), field.getAD_Reference_ID(),
					m_language, keyColumn, field.getAD_Reference_Value_ID(), false, whereClause);

			int cols = 0;

			StringBuilder axisSql = new StringBuilder("(SELECT ");
			axisSql.append("SUM(")
					.append(ycol.getAD_Column()
							.getColumnName())
					.append(") FROM  ")
					.append(ycol.getAD_View_Definition().getAD_Table().getTableName())
					.append(" WHERE ")
					.append(xTableName)
					.append(".")
					.append(fieldKey.getAD_View_Column().getAD_Column()
							.getColumnName()).append("=")
					.append(fieldKey.getAD_View_Column().getColumnSQL());

			for (int id :  getAxisRecordIds(tableName, whereClause)) {
				cols ++;
				String display =  lookup.getDisplay(id).trim();
				display = display.length() > 12 ? display.substring(1,12) + "_" + cols : display;
				String joinColumn = Msg.translate(m_language, ycol.getAD_Column()
						.getColumnName());
				joinColumn = joinColumn.length() > 15 ? joinColumn.substring(1, 15) :  joinColumn;
				String sqlColName = display + "/" + joinColumn;
				String colName = lookup.getDisplay(id).trim() + "/" + Msg.translate(m_language, ycol.getAD_Column()
						.getColumnName());

				StringBuilder axisWhere = new StringBuilder(" ");
						axisWhere.append(getAxisSQLWhere(ycol))
						.append(" AND ")
						.append(xcol.getAD_View_Definition().getTableAlias()).append(".")
						.append(xcol.getAD_Column().getColumnName());

				StringBuffer select = new StringBuffer();
				select.append(axisSql).append(axisWhere);
				select.append("=").append(id).append(")");

				MViewColumn viewColumn = new MViewColumn(field.getCtx() , 0 , field.get_TrxName());
				MViewColumn.copyValues((MViewColumn) ycol, viewColumn);
				viewColumn.setAD_View_Column_ID(ycol.getAD_View_Column_ID());
				viewColumn.setAD_Column_ID(ycol.getAD_Column_ID());
				viewColumn.setColumnSQL(select.toString());
				viewColumn.setColumnName("\"" + sqlColName + "\"");

				MBrowseField browseField = new MBrowseField((MBrowse)field.getAD_Browse() , viewColumn);
				browseField.setAD_Browse_ID(field.getAD_Browse_ID());
				browseField.setAD_Element_ID(field.getAD_Element_ID());
				browseField.setName(colName);
				browseField.setDescription(viewColumn.getDescription());
				browseField.setHelp(viewColumn.getHelp());
				if (viewColumn.get_ID() > 0)
					browseField.setAD_View_Column_ID(viewColumn.getAD_View_Column_ID());
				browseField.setIsActive(true);
				browseField.setIsIdentifier(viewColumn.isIdentifier());
				browseField.setIsRange(false);
				browseField.setIsQueryCriteria(false);
				browseField.setAD_Reference_ID(ycol.getAD_Column().getAD_Reference_ID());
				browseField.setAD_Reference_Value_ID(ycol.getAD_Column().getAD_Reference_Value_ID());
				browseField.setIsKey(false);
				browseField.setIsDisplayed(true);
				browseField.setAxis_Column_ID(field.getAxis_Column_ID());
				browseField.setAxis_Parent_Column_ID(field.getAxis_Parent_Column_ID());
				browseField.setIsReadOnly(field.isReadOnly());
				browseField.setAD_Element_ID(field.getAD_Element_ID());

				list.add(browseField);
				log.finest("Added Column=" + sqlColName +  " SQL = " + select);
			}

		} catch (Exception e) {
			throw new AdempiereException(e);
		}
		return list;
	}

	/**
	 * Get record ID for axis
	 * @param tableName
	 * @param tableWhereClause
	 * @return
	 */
    private int[] getAxisRecordIds(String tableName, String tableWhereClause) {

        StringBuilder whereClause = new StringBuilder();
        StringBuilder orderBy = new StringBuilder();
        whereClause.append("EXISTS (SELECT 1 FROM AD_Table t WHERE t.TableName=? AND t.AD_Table_ID=AD_Column.AD_Table_ID) AND ");
        whereClause.append(I_AD_Column.COLUMNNAME_IsIdentifier).append("=?");

        List<MColumn> columns =  new Query(Env.getCtx() , I_AD_Column.Table_Name , whereClause.toString(), null)
                .setOnlyActiveRecords(true)
                .setParameters(tableName, true)
                .setOrderBy(I_AD_Column.COLUMNNAME_SeqNo)
                .list();

        int count = 1;
        for (MColumn column : columns)
        {
            orderBy.append(column.getColumnName());
            if (count != columns.size())
                orderBy.append(",");
            count++;
        }

        return new Query(Env.getCtx(), tableName , tableWhereClause, null)
                .setOnlyActiveRecords(true)
                .setOrderBy(orderBy.toString()).getIDs();
    }

    /**
	 * Get Parent Column for Table
	 * @param AD_Table_ID Table ID
	 * @return MColumn
	 */
	private MColumn getParentColumn(int AD_Table_ID)
	{
		String whereClause = MColumn.COLUMNNAME_AD_Table_ID + "=? AND "
				+ MColumn.COLUMNNAME_IsParent + "=? ";
		return new Query(Env.getCtx(), MColumn.Table_Name, whereClause, null)
				.setParameters(AD_Table_ID, true).first();
	}
	
	/**
	 * Get field Key
	 * @return
	 */
	public MBrowseField getFieldKey()
	{
		MBrowseField fieldKey = browseModel.getFieldKey();
		return fieldKey;
	}
	
	/**
	 * Is Identifier selection
	 * @param columnName
	 * @return
	 */
	public boolean IsIdentifierSelection(String columnName)
	{	
		for (MBrowseField field : browseModel.getIdentifierFields()) {
			if (field.getAD_View_Column().getColumnName().equals(columnName))
				return true;
		}
		return false;
	}
	
	/**
	 * Get Query from Record Identifier
	 * @return
	 */
	public MQuery getMQuery(IBrowserTable browseTable) {
		List<Integer> ids = getSelectedRowKeys(browseTable);
		String inClause = " IN" + ids.toString().replace('[','(').replace(']',')');
		Integer record_ID = getSelectedRowKey(browseTable);

		if (record_ID == null)
			return null;
		
		MBrowseField fieldKey = getFieldKey();
		if(fieldKey == null)
			return null;
		
		MColumn column = fieldKey.getAD_View_Column().getAD_Column();
		String keyColumn = MQuery.getZoomColumnName(column.getColumnName());
		String tableName = column.getAD_Table().getTableName();
		MQuery query = new MQuery(tableName);
		query.addRestriction(keyColumn + inClause);
		return query;
	}
	 
	/**
	 * Get parameter
	 * @return
	 */
	public abstract LinkedHashMap<String, GridField> getPanelParameters();
	
	/**
	 * Initialize Smart Browse
	 */
	public abstract void init();
	
	/**
	 * Get SQL where for axis
	 * @param viewColumn
	 * @return
	 */
	public String getAxisSQLWhere(I_AD_View_Column viewColumn)
	{
		 MViewDefinition viewDefinition = (MViewDefinition) viewColumn.getAD_View_Definition();
		 MTable tableBaseName = (MTable) viewDefinition.getAD_Table();
		 StringBuilder whereAxis = new StringBuilder();
		 boolean onRange = false;
		 setParameters();
		 
			for (int i = 0; i < parametersField.size(); i++) {
                String fieldName = "";
                MColumn  column = tableBaseName.getColumn(parametersField.get(i).ColumnName);
                if (column != null)
                    fieldName = tableBaseName.getTableName() + "." + column.getColumnName();
                else
                    continue;

				if (!onRange) {

					if (parametersValues.get(i) != null
							&& !parametersValues.get(i).toString().isEmpty()
							&& !parametersField.get(i).IsRange) {
						whereAxis.append(" AND ");
						whereAxis.append(fieldName).append("=").append(parametersValues.get(i).toString());
					}
					else if (parametersValues.get(i) != null
							&& !parametersValues.get(i).toString().isEmpty()
							&& parametersField.get(i).IsRange) {
						whereAxis.append(" AND ");
						whereAxis.append(fieldName).append(" >= ? ");
						axisParameters.add(parametersField.get(i));
						axisParametersValues.add(parametersValues.get(i));
						onRange = true;
					} else
						continue;
				} else if (parametersValues.get(i) != null
						&& !parametersValues.get(i).toString().isEmpty()) {
					whereAxis.append(" AND ");
					whereAxis.append(fieldName).append(" <= ? ");
					axisParameters.add(parametersField.get(i));
					axisParametersValues.add(parametersValues.get(i));
					onRange = false;
				}
			}
			
			return whereAxis.toString();
	 }
	
	/**
	 * get main SQL
	 * @return SQL parsed
	 */
	protected String getSQL() {
		String dynWhere = getSQLWhere(false);
		StringBuilder sql = new StringBuilder(m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND

		//	BR [ 318 ]
		String dataSql = Env.parseContext(Env.getCtx(), getWindowNo(), sql.toString(), true, true); // Variables
		//	
		dataSql = MRole.getDefault().addAccessSQL(dataSql,
				view.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
        dataSql = dataSql + m_sqlOrderBy;
		log.finer(dataSql);
		return dataSql;
	}

	public String getSQLOrderBy() {
		StringBuilder sqlOrderBy = new StringBuilder();
		for (MBrowseField field : browseModel.getOrderByFields()) {
			if (field.isOrderBy()) {
				int orderByPosition = getOrderByPosition(field);
				if (orderByPosition <= 0)
					continue;

				if (sqlOrderBy.length() > 0)
					sqlOrderBy.append(",");

					sqlOrderBy.append(orderByPosition);
			}
		}
		return sqlOrderBy.length() > 0 ? " ORDER BY " + sqlOrderBy.toString()
				: "";
	}

	/**
	 * Get Order By Postirion for SB
	 * @param BrowserField
	 * @return
	 */
	private int getOrderByPosition(MBrowseField BrowserField)
	{
		int colOffset = 1; // columns start with 1
		int col = 0;
		for (MBrowseField field : browserFields) {
			int sortBySqlNo = col + colOffset;
			if (BrowserField.getAD_Browse_Field_ID() == field.getAD_Browse_Field_ID())
				return sortBySqlNo;
			col ++;
		}


		return -1;
	}
	
	/**
	 * Get Statement from parameters value
	 * @param sql
	 * @return
	 */
	protected PreparedStatement getStatement(String sql) {
		PreparedStatement stmt = null;
		ArrayList<Object> parametersValue = new ArrayList<Object>();
		if (getAxisParametersValues() != null && getAxisParametersValues().size() > 0)
			parametersValue.addAll(getAxisParametersValues());
		if (getParametersValues() != null && getParametersValues().size() > 0)
			parametersValue.addAll(getParametersValues());

		try {
			stmt = DB.prepareStatement(sql, null);
			if (parametersValue.size() > 0)
				DB.setParameters(stmt, parametersValue);
			return stmt;
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		}
		return stmt;
	}
	
	/**
	 * Export from Table
	 * @param browserTable
	 * @return
	 */
	protected File exportXLS(IBrowserTable browserTable) {
		File file = null;
		try {
			if (m_exporter != null && m_exporter.isAlive())
				return file;

			m_exporter = new Exporter();
			m_exporter.start();
			while (m_exporter.isAlive())
				;
			
			ArrayList<ArrayList<Object>> rows = getDataRows(browserTable);
			if (rows.size() > 0) {
				String path = System.getProperty("java.io.tmpdir");
				String prefix = makePrefix(browseModel.getName());
				if (log.isLoggable(Level.FINE)) {
					log.log(Level.FINE, "Path=" + path + " Prefix=" + prefix);
				}
				file = File.createTempFile(prefix, ".xls", new File(path));
				ArrayExcelExporter exporter = new ArrayExcelExporter(Env.getCtx(), rows, true);
				exporter.export(file, m_language, false);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
		return file;
	}

	/**
	 * Make a prefix
	 * @param name
	 * @return
	 */
	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}

	/**
	 * Exporter
	 */
	class Exporter extends Thread {
		private PreparedStatement pstmt = null;
		private ResultSet resultSet = null;
		private String dataSql = null;

		/**
		 * Do Work (load data)
		 */
		public void run() {
			long start = System.currentTimeMillis();
			int no = 0;
			dataSql = getSQL();
			pstmt = getStatement(dataSql);
			m_rows = new ArrayList<ArrayList<Object>>();
			try {
				log.fine("Start query - "
						+ (System.currentTimeMillis() - start) + "ms");
				resultSet = pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis() - start)
						+ "ms");
				boolean isFirstRow = true;
				while (resultSet.next()) {
					if (this.isInterrupted()) {
						log.finer("Interrupted");
						close();
						return;
					}
					no++;
					ArrayList<Object> header = (isFirstRow ? new ArrayList<Object>()
							: null);
					ArrayList<Object> row = new ArrayList<Object>();
					int colOffset = 1; // columns start with 1
					int col = 0;
					for (MBrowseField field : browserFields) {

						if (isFirstRow) {
							String columnName = field.getName();
							header.add(columnName);
						}

						Object data = null;
						int colIndex = col + colOffset;
						if(field.isKey() && 
								(DisplayType.isID(field.getAD_Reference_ID())
								|| DisplayType.Integer == field.getAD_Reference_ID()))
							data = new IDColumn(resultSet.getInt(colIndex));
						else if (field.isKey() 
								&& (field.getName().equals(field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
										|| (!DisplayType.isID(field.getAD_Reference_ID()) 
												&& DisplayType.Integer != field.getAD_Reference_ID())))
							data = new IDColumn(no);
						else if (DisplayType.YesNo == field.getAD_Reference_ID())
							data = new Boolean("Y".equals(resultSet
									.getString(colIndex)));
						else if (DisplayType.isDate(field.getAD_Reference_ID()))
							data = resultSet.getTimestamp(colIndex);
						else if(DisplayType.isID(field.getAD_Reference_ID())
								|| DisplayType.Integer == field.getAD_Reference_ID())
							data = new Integer(resultSet.getInt(colIndex));
						else if (DisplayType.isNumeric(field.getAD_Reference_ID()))
							data = resultSet.getBigDecimal(colIndex);
						else
							data = resultSet.getString(colIndex);

						row.add(data);
						col++;
					}

					if (isFirstRow)
						m_rows.add(header);
					m_rows.add(row);
					isFirstRow = false;
				}

			} catch (Throwable e) {
				log.log(Level.SEVERE, dataSql, e);
			} finally {
				close();
			}
			log.fine("#" + no + " - " + (System.currentTimeMillis() - start)
					+ "ms");
			if (no == 0)
				log.fine(dataSql);

		} // run

		private void close() {
			DB.close(resultSet, pstmt);
			resultSet = null;
			pstmt = null;
		}
	} // Exporter
	
	/**
	 * Get Window No
	 * @return
	 */
	public int getWindowNo()
	{
		return windowNo;
	}
	
	/**
	 * BR [242 ]
	 * Get Window Number from parent window
	 * @return
	 */
	public int getParentWindowNo() {
		return parentWindowNo;
	}
	
	/**
	 * get Selected values in Smart Browse
	 * @return selected values
	 */
	public LinkedHashMap<Integer, LinkedHashMap<String, Object>> getSelectedValues() {
		return values;
	}
}

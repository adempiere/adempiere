/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]                  *
 *              Teo Sarca, www.arhipac.ro                                     *
 *****************************************************************************/
package org.compiere.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.script.ScriptEngine;
import javax.swing.event.EventListenerList;

import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

/**
 *	Tab Model.
 *  - a combination of AD_Tab (the display attributes) and AD_Table information.
 *  <p>
 *  The Tab owns also it's Table model
 *  and listens to data changes to update the Field values.
 *
 *  <p>
 *  The Tab maintains the bound property: CurrentRow
 *
 *  <pre>
 *  Event Hierarchies:
 *      - dataChanged (from MTable)
 *          - setCurrentRow
 *              - Update all Field Values
 *
 *      - setValue
 *          - Update Field Value
 *          - Callout
 *  </pre>
 *  @author 	Jorg Janke
 *  @version 	$Id: GridTab.java,v 1.10 2006/10/02 05:18:39 jjanke Exp $
 *  
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  			<li>BF [ 1742159 ] Editable number field for inactive record
 *  			<li>BF [ 1968598 ] Callout is not called if tab is processed
 *  			<li>BF [ 2104022 ] GridTab.processCallout: throws NPE if callout returns null
 *  			<li>FR [ 2846871 ] Add method org.compiere.model.GridTab.getIncludedTabs
 *  				https://sourceforge.net/tracker/?func=detail&aid=2846871&group_id=176962&atid=879335
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  			<li>BF [ 2873323 ] ABP: Do not concatenate strings in SQL queries
 *  				https://sourceforge.net/tracker/?func=detail&aid=2873323&group_id=176962&atid=879332
 *  			<li>BF [ 2874109 ] Tab ORDER BY clause is not supporting context variables
 *  				https://sourceforge.net/tracker/?func=detail&aid=2874109&group_id=176962&atid=879332
 *  @author Victor Perez , e-Evolution.SC [1877902] Implement JSR 223 Scripting APIs to Callout
 *  @author Carlos Ruiz, qss FR [1877902]
 *  @see  http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1877902&group_id=176962 to FR [1877902]
 *  @author Cristina Ghita, www.arhipac.ro FR [2870645] Set null value for an ID
 *  @see  https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2870645&group_id=176962
 */
public class GridTab implements DataStatusListener, Evaluatee, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7198494041906579986L;

	public static final String DEFAULT_STATUS_MESSAGE = "NavigateOrUpdate";
	
	/**
	 *	Create Tab (Model) from Value Object.
	 *  <p>
	 *  MTab provides a property listener for changed rows and a
	 *  DataStatusListener for communicating changes of the underlying data
	 *  @param vo Value Object
	 */
	public GridTab(GridTabVO vo, GridWindow w)
	{
		m_window = w;
		m_vo = vo;
		//  Create MTable
		m_mTable = new GridTable (m_vo.ctx, m_vo.AD_Table_ID, m_vo.TableName, m_vo.WindowNo, m_vo.TabNo, true);
		m_mTable.setReadOnly(m_vo.IsReadOnly || m_vo.IsView);
		m_mTable.setDeleteable(m_vo.IsDeleteable);
		//  Load Tab
		//initTab(false);
	}	//	GridTab

	/** Value Object                    */
	private GridTabVO          	m_vo;

	// The window of this tab
	private GridWindow			m_window;

	/** The Table Model for Query   */
	private GridTable          	m_mTable = null;

	private String 				m_keyColumnName = "";
	private String 				m_linkColumnName = "";

	private String m_parentColumnName = "";
	private String				m_extendedWhere;
	/** Attachments         */
	private HashMap<Integer,Integer>	m_Attachments = null;
	/** Chats				*/
	private HashMap<Integer,Integer>	m_Chats = null;
	/** Locks		        */
	private ArrayList<Integer>	m_Lock = null;

	/** Current Row         */
	private int					m_currentRow = -1;

	/** Property Change     */
	private PropertyChangeSupport m_propertyChangeSupport = new PropertyChangeSupport(this);
	/** Property Change Type    */
	public static final String  PROPERTY = "CurrentRow";
    /** A list of event listeners for this component.	*/
    protected EventListenerList m_listenerList = new EventListenerList();
    /** Current Data Status Event						*/
	private DataStatusEvent 	m_DataStatusEvent = null;
	/**	Query							*/
	private MQuery 				m_query = new MQuery();
	private String 				m_oldQuery = "0=9";
	private String 				m_linkValue = "999999";

	/** Order By Array if SortNo 1..3   */
	private String[]	    	m_OrderBys = new String[3];
	/** List of Key Parents     */
	private ArrayList<String>	m_parents = new ArrayList<String>(2);

	/** Map of ColumnName of source field (key) and the dependant field (value) */
	private MultiMap<String,GridField>	m_depOnField = new MultiMap<String,GridField>();

	/** Async Loader            */
	private Loader              m_loader = null;
	/** Async Loading complete  */
	private volatile boolean    m_loadComplete = false;
	/** Is Tab Included in other Tab  */
	private boolean    			m_included = false;

	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	
	private boolean m_parentNeedSave = false;

	private long m_lastDataStatusEventTime;

	private DataStatusEvent m_lastDataStatusEvent;
	
	// Context property names:
	public static final String CTX_KeyColumnName = "KeyColumnName";
	public static final String CTX_LinkColumnName = "LinkColumnName";
	public static final String CTX_TabLevel = "TabLevel";
	public static final String CTX_AccessLevel = "AccessLevel";
	public static final String CTX_AD_Tab_ID = "AD_Tab_ID";
	public static final String CTX_Name = "Name";
	public static final String CTX_AD_Table_ID = "AD_Table_ID";
	


	/**************************************************************************
	 *  Tab loader for Tabs > 0
	 */
	class Loader extends Thread
	{
		/**
		 *  Async Loading of Tab > 0
		 */
		public void run()
		{
			loadTab();
		}   //  run
	}   //  Loader

	/**
	 *  Wait until load is complete
	 */
	private void waitLoadCompete()
	{
		if (m_loadComplete)
			return;
		//
		m_loader.setPriority(Thread.NORM_PRIORITY);
		log.config ("");
		while (m_loader.isAlive())
		{
			try
			{
				Thread.sleep(100);     //  1/10 sec
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "", e);
			}
		}
		log.config ("fini");
	}   //  waitLoadComplete

	public boolean isLoadComplete()
	{
		return m_loadComplete;
	}
	
	/**
	 *	Initialize Tab with record from AD_Tab_v
	 *  @param async async
	 *	@return true, if correctly initialized (ignored)
	 */
	public boolean initTab (boolean async)
	{
		log.fine("#" + m_vo.TabNo + " - Async=" + async + " - Where=" + m_vo.WhereClause);
		if (isLoadComplete()) return true;
		
		if (m_loader != null && m_loader.isAlive())
		{
			waitLoadCompete();
			if (isLoadComplete())
				return true;
		}

		if (async)
		{
			m_loader = new Loader();
			m_loader.start();
			return false;
		}
		else
		{
			return loadTab();
		}
	}	//	initTab

	protected boolean loadTab()
	{	
		m_extendedWhere = m_vo.WhereClause;
		
		//	Get Field Data
		if (!loadFields())
		{
			m_loadComplete = true;
			return false;
		}

		//  Order By
		m_mTable.setOrderClause(getOrderByClause(m_vo.onlyCurrentRows));

		m_loadComplete = true;
		return true;
	}
	
	/**
	 *	Dispose - clean up resources
	 */
	protected void dispose()
	{
		log.fine("#" + m_vo.TabNo);
		m_OrderBys = null;
		//
		m_parents.clear();
		m_parents = null;
		//
		m_mTable.close (true);  //  also disposes Fields
		m_mTable = null;
		//
		m_depOnField.clear();
		m_depOnField = null;
		if (m_Attachments != null)
			m_Attachments.clear();
		m_Attachments = null;
		if (m_Chats != null)
			m_Chats.clear();
		m_Chats = null;
		//
		if (m_vo.isInitFields())
			m_vo.getFields().clear();
		//m_vo.Fields = null;
		m_vo = null;
		if (m_loader != null)
		{
			if (m_loader.isAlive()) m_loader.interrupt();
			m_loader = null;
		}
	}	//	dispose


	/**
	 *	Get Field data and add to MTable, if it's required or displayed.
	 *  Reqiored fields are keys, parents, or standard Columns
	 *  @return true if fields loaded
	 */
	private boolean loadFields()
	{
		log.fine("#" + m_vo.TabNo);

		if (m_vo.getFields() == null)
			return false;

		//  Add Fields
		for (int f = 0; f < m_vo.getFields().size(); f++)
		{
			GridFieldVO voF = (GridFieldVO)m_vo.getFields().get(f);
			//	Add Fields to Table
			if (voF != null)
			{
				GridField field = new GridField (voF);
				field.setGridTab(this);
				String columnName = field.getColumnName();
				//FR [ 1757088 ] - this create Bug [ 1866793 ]
				/*
				if(this.isReadOnly()) {
				   voF.IsReadOnly = true;
				}*/
				//	Record Info
				if (field.isKey()) {
					setKeyColumnName(columnName);
				}
				//	Parent Column(s)
				if (field.isParentColumn())
					m_parents.add(columnName);
				//	Order By
				int sortNo = field.getSortNo();
				if (sortNo == 0)
					;
				else if (Math.abs(sortNo) == 1)
				{
					m_OrderBys[0] = columnName;
					if (sortNo < 0)
						m_OrderBys[0] += " DESC";
				}
				else if (Math.abs(sortNo) == 2)
				{
					m_OrderBys[1] = columnName;
					if (sortNo < 0)
						m_OrderBys[1] += " DESC";
				}
				else if (Math.abs(sortNo) == 3)
				{
					m_OrderBys[2] = columnName;
					if (sortNo < 0)
						m_OrderBys[2] += " DESC";
				}
				//  Add field
				m_mTable.addField(field);

				//  List of ColumnNames, this field is dependent on
				ArrayList<String> list = field.getDependentOn();
				for (int i = 0; i < list.size(); i++)
					m_depOnField.put(list.get(i), field);   //  ColumnName, Field
				//  Add fields all fields are dependent on
				if (columnName.equals("IsActive")
					|| columnName.equals("Processed")
					|| columnName.equals("Processing"))
					m_depOnField.put(columnName, null);
			}
		}   //  for all fields

		if (! m_mTable.getTableName().equals(X_AD_PInstance_Log.Table_Name)) { // globalqss, bug 1662433 
			//  Add Standard Fields
			if (m_mTable.getField("Created") == null)
			{
				GridField created = new GridField (GridFieldVO.createStdField(m_vo.ctx,
					m_vo.WindowNo, m_vo.TabNo,
					m_vo.AD_Window_ID, m_vo.AD_Tab_ID, false, true, true));
				m_mTable.addField(created);
			}
			if (m_mTable.getField("CreatedBy") == null)
			{
				GridField createdBy = new GridField (GridFieldVO.createStdField(m_vo.ctx,
					m_vo.WindowNo, m_vo.TabNo,
					m_vo.AD_Window_ID, m_vo.AD_Tab_ID, false, true, false));
				m_mTable.addField(createdBy);
			}
			if (m_mTable.getField("Updated") == null)
			{
				GridField updated = new GridField (GridFieldVO.createStdField(m_vo.ctx,
					m_vo.WindowNo, m_vo.TabNo,
					m_vo.AD_Window_ID, m_vo.AD_Tab_ID, false, false, true));
				m_mTable.addField(updated);
			}
			if (m_mTable.getField("UpdatedBy") == null)
			{
				GridField updatedBy = new GridField (GridFieldVO.createStdField(m_vo.ctx,
					m_vo.WindowNo, m_vo.TabNo,
					m_vo.AD_Window_ID, m_vo.AD_Tab_ID, false, false, false));
				m_mTable.addField(updatedBy);
			}
		}
		return true;
	}	//	loadFields

	/**
	 *  Get a list of variables, this tab is dependent on.
	 *  - for display purposes
	 *  @return ArrayList
	 */
	public ArrayList<String> getDependentOn()
	{
		ArrayList<String> list = new ArrayList<String>();
		//  Display
		Evaluator.parseDepends(list, m_vo.DisplayLogic);
		//
		if (list.size() > 0 && CLogMgt.isLevelFiner())
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++)
				sb.append(list.get(i)).append(" ");
			log.finer("(" + m_vo.Name + ") " + sb.toString());
		}
		return list;
	}   //  getDependentOn

	/**
	 * 	Get Display Logic
	 *	@return display logic
	 */
	public String getDisplayLogic()
	{
		return m_vo.DisplayLogic;
	}	//	getDisplayLogic

	/**
	 *  Get TableModel.
	 *  <B>Do not directly communicate with the table model,
	 *  but through the methods of this class</B>
	 *  @return Table Model
	 */
	public GridTable getTableModel()
	{
		if (!m_loadComplete) initTab(false);
		return m_mTable;
	}   //  getTableModel

	/**
	 *  Get Tab Icon
	 *  @return Icon
	 */
	public javax.swing.Icon getIcon()
	{
		if (m_vo.AD_Image_ID == 0)
			return null;
		//
		/** @todo Load Image */
		return null;
	}   //  getIcon


	/**************************************************************************
	 *  Has this field dependents ?
	 *  @param columnName column name
	 *  @return true if column has dependent
	 */
	public boolean hasDependants (String columnName)
	{
	//	m_depOnField.printToLog();
		return m_depOnField.containsKey(columnName);
	}   //  isDependentOn

	/**
	 *  Get dependents fields of columnName
	 *  @param columnName column name
	 *  @return ArrayList with GridFields dependent on columnName
	 */
	public ArrayList<GridField> getDependantFields (String columnName)
	{
		return m_depOnField.getValues(columnName);
	}   //  getDependentFields


	/**************************************************************************
	 *	Set Query
	 *  @param query query
	 */
	public void setQuery(MQuery query)
	{
		if (query == null)
			m_query = new MQuery();
		else
			m_query = query;
	}	//	setQuery

	/**
	 *	Get Query
	 *  @return query
	 */
	public MQuery getQuery()
	{
		return m_query;
	}	//	getQuery

	/**
	 *	Is Query Active
	 *  @return true if query active
	 */
	public boolean isQueryActive()
	{
		if (m_query != null)
			return m_query.isActive();
		return false;
	}	//	isQueryActive

	/**
	 *	Is Query New Record
	 *  @return true if query active
	 */
	public boolean isQueryNewRecord()
	{
		if (m_query != null)
			return m_query.isNewRecordQuery();
		return false;
	}	//	isQueryNewRecord

	/**
	 *  Enable Events - enable data events of tabs (add listeners)
	 */
	public void enableEvents()
	{
		//  Setup Events
		m_mTable.addDataStatusListener(this);
	//	m_mTable.addTableModelListener(this);
	}   //  enableEvents

	/**
	 *	Assemble whereClause and query MTable and position to row 0.
	 *  <pre>
	 *		Scenarios:
	 *		- Never opened 					(full query)
	 *		- query changed 				(full query)
	 *		- Detail link value changed		(full query)
	 *		- otherwise 					(refreshAll)
	 *  </pre>
	 *  @param onlyCurrentRows only current rows (1 day)
	 */
	public void query (boolean onlyCurrentRows)
	{
		query (onlyCurrentRows, 0, 0);
	}	//	query

	/**
	 *	Assemble whereClause and query MTable and position to row 0.
	 *  <pre>
	 *		Scenarios:
	 *		- Never opened 					(full query)
	 *		- query changed 				(full query)
	 *		- Detail link value changed		(full query)
	 *		- otherwise 					(refreshAll)
	 *  </pre>
	 *  @param onlyCurrentRows only current rows
	 *  @param onlyCurrentDays if only current row, how many days back
	 *  @param maxRows maximim rows or 0 for all
	 */
	public void query (boolean onlyCurrentRows, int onlyCurrentDays, int maxRows)
	{
		if (!m_loadComplete) initTab(false);
		
		log.fine("#" + m_vo.TabNo
			+ " - Only Current Rows=" + onlyCurrentRows
			+ ", Days=" + onlyCurrentDays + ", Detail=" + isDetail());
		//	is it same query?
		boolean refresh = m_oldQuery.equals(m_query.getWhereClause())
			&& m_vo.onlyCurrentRows == onlyCurrentRows && m_vo.onlyCurrentDays == onlyCurrentDays;
		m_oldQuery = m_query.getWhereClause();
		m_vo.onlyCurrentRows = onlyCurrentRows;
		m_vo.onlyCurrentDays = onlyCurrentDays;

		/**
		 *	Set Where Clause
		 */
		//	Tab Where Clause
		StringBuffer where = new StringBuffer(m_vo.WhereClause);
		if (m_vo.onlyCurrentDays > 0)
		{
			if (where.length() > 0)
				where.append(" AND ");
			where.append("Created >= ");
			where.append("SysDate-").append(m_vo.onlyCurrentDays);
		}
		//	Detail Query
		if (isDetail())
		{
			m_parentNeedSave = false;
			String lc = getLinkColumnName();
			if (lc.equals("")) {
				log.warning ("No link column");
				where.append (" 2=3");
			}
			else
			{
				String value = Env.getContext(m_vo.ctx, m_vo.WindowNo, lc, true);
				// explicit parent link defined
				if ( m_parentColumnName.length() > 0 )
					value = Env.getContext(m_vo.ctx, m_vo.WindowNo, m_parentColumnName, true);
				
				//	Same link value?
				if (refresh)
					refresh = m_linkValue.equals(value);
				m_linkValue = value;
				//	Check validity
				if (value.length() == 0)
				{
					//log.severe ("No value for link column " + lc);
					//parent is new, can't retrieve detail
					m_parentNeedSave = true;
					if (where.length() != 0)
						where.append(" AND ");
					// where.append(lc).append(" is null ");
					// as opened by this fix [ 1881480 ] Navigation problem between tabs
					// it's safer to avoid retrieving details at all if there is no parent value
					where.append (" 2=3");
				}
				else
				{
					//	we have column and value
					if (where.length() != 0)
						where.append(" AND ");
					where.append(getTableName()).append(".").append(lc).append("=");
					if (lc.endsWith("_ID"))
						where.append(DB.TO_NUMBER(new BigDecimal(value), DisplayType.ID));
					else
						where.append(DB.TO_STRING(value));
				}
			}
		}	//	isDetail

		m_extendedWhere = where.toString();

		//	Final Query
		if (m_query.isActive())
		{
			String q = validateQuery(m_query);
			if (q != null)
			{
				if (where.length() > 0 )
					where.append(" AND ");
				where.append(q);
			}
		}

		/**
		 *	Query
		 */
		log.fine("#" + m_vo.TabNo + " - " + where);
		if (m_mTable.isOpen())
		{
			if (refresh)
				m_mTable.dataRefreshAll();
			else
				m_mTable.dataRequery(where.toString(), m_vo.onlyCurrentRows && !isDetail(), onlyCurrentDays);
		}
		else
		{
			m_mTable.setSelectWhereClause(where.toString(), m_vo.onlyCurrentRows && !isDetail(), onlyCurrentDays);
			m_mTable.open(maxRows);
		}
		//  Go to Record 0
		setCurrentRow(0, true);
	}	//	query

	/**
	 * 	Validate Query.
	 *  If query column is not a tab column create EXISTS query
	 * 	@param query query
	 * 	@return where clause
	 */
	private String validateQuery (MQuery query)
	{
		if (query == null || query.getRestrictionCount() == 0)
			return null;

		//	Check: only one restriction
		if (query.getRestrictionCount() != 1)
		{
			log.fine("Ignored(More than 1 Restriction): " + query);
			return query.getWhereClause();
		}

		String colName = query.getColumnName(0);
		if (colName == null)
		{
			log.fine("Ignored(No Column): " + query);
			return query.getWhereClause();
		}
		//	a '(' in the name = function - don't try to resolve
		if (colName.indexOf('(') != -1)
		{
			log.fine("Ignored(Function): " + colName);
			return query.getWhereClause();
		}
		//	OK - Query is valid

		//	Zooms to the same Window (Parents, ..)
		String refColName = null;
		if (colName.equals("R_RequestRelated_ID"))
			refColName = "R_Request_ID";
		else if (colName.startsWith("C_DocType"))
			refColName = "C_DocType_ID";
		if (refColName != null)
		{
			query.setColumnName(0, refColName);
			if (getField(refColName) != null)
			{
				log.fine("Column " + colName + " replaced with synonym " + refColName);
				return query.getWhereClause();
			}
			refColName = null;
		}

		//	Simple Query.
		if (getField(colName) != null)
		{
			log.fine("Field Found: " + colName);
			return query.getWhereClause();
		}

		//	Find Refernce Column e.g. BillTo_ID -> C_BPartner_Location_ID
		String sql = "SELECT cc.ColumnName "
			+ "FROM AD_Column c"
			+ " INNER JOIN AD_Ref_Table r ON (c.AD_Reference_Value_ID=r.AD_Reference_ID)"
			+ " INNER JOIN AD_Column cc ON (r.AD_Key=cc.AD_Column_ID) "
			+ "WHERE c.AD_Reference_ID IN (18,30)" 	//	Table/Search
			+ " AND c.ColumnName=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, colName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				refColName = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "(ref) - Column=" + colName, e);
			return query.getWhereClause();
		}
		//	Reference Column found
		if (refColName != null)
		{
			query.setColumnName(0, refColName);
			if (getField(refColName) != null)
			{
				log.fine("Column " + colName + " replaced with " + refColName);
				return query.getWhereClause();
			}
			colName = refColName;
		}

		//	Column NOT in Tab - create EXISTS subquery
		String tableName = null;
		String tabKeyColumn = getKeyColumnName();
		//	Column=SalesRep_ID, Key=AD_User_ID, Query=SalesRep_ID=101

		sql = "SELECT t.TableName "
			+ "FROM AD_Column c"
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) "
			+ "WHERE c.ColumnName=? AND IsKey='Y'"		//	#1 Link Column
			+ " AND EXISTS (SELECT * FROM AD_Column cc"
			+ " WHERE cc.AD_Table_ID=t.AD_Table_ID AND cc.ColumnName=?)";	//	#2 Tab Key Column
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, colName);
			pstmt.setString(2, tabKeyColumn);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				tableName = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "Column=" + colName + ", Key=" + tabKeyColumn, e);
			return null;
		}

		//	Special Reference Handling
		if (tabKeyColumn.equals("AD_Reference_ID"))
		{
			//	Column=AccessLevel, Key=AD_Reference_ID, Query=AccessLevel='6'
			sql = "SELECT AD_Reference_ID FROM AD_Column WHERE ColumnName=?";
			int AD_Reference_ID = DB.getSQLValue(null, sql, colName);
			return "AD_Reference_ID=" + AD_Reference_ID;
		}

		//	Causes could be functions in query
		//	e.g. Column=UPPER(Name), Key=AD_Element_ID, Query=UPPER(AD_Element.Name) LIKE '%CUSTOMER%'
		if (tableName == null)
		{
			log.info ("Not successfull - Column="
				+ colName + ", Key=" + tabKeyColumn
				+ ", Query=" + query);
			return query.getWhereClause();
		}

		query.setTableName("xx");
		StringBuffer result = new StringBuffer ("EXISTS (SELECT * FROM ")
			.append(tableName).append(" xx WHERE ")
			.append(query.getWhereClause(true))
			.append(" AND xx.").append(tabKeyColumn).append("=")
			.append(getTableName()).append(".").append(tabKeyColumn).append(")");
		log.fine(result.toString());
		return result.toString();
	}	//	validateQuery


	/**************************************************************************
	 *  Refresh all data
	 */
	public void dataRefreshAll ()
	{
		log.fine("#" + m_vo.TabNo);
		/** @todo does not work with alpha key */
		int keyNo = m_mTable.getKeyID(m_currentRow);
		m_mTable.dataRefreshAll();
		//  Should use RowID - not working for tables with multiple keys
		if (keyNo != -1)
		{
			if (keyNo != m_mTable.getKeyID(m_currentRow))   //  something changed
			{
				int size = getRowCount();
				for (int i = 0; i < size; i++)
				{
					if (keyNo == m_mTable.getKeyID(i))
					{
						m_currentRow = i;
						break;
					}
				}
			}
		}
		setCurrentRow(m_currentRow, true);
		fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_REFRESH_ALL));
	}   //  dataRefreshAll

	/**
	 *  Refresh current row data
	 */
	public void dataRefresh ()
	{
		dataRefresh (m_currentRow);
	}   //  dataRefresh

	/**
	 *  Refresh row data
	 *  @param row index
	 */
	public void dataRefresh (int row)
	{
		log.fine("#" + m_vo.TabNo + " - row=" + row);
		m_mTable.dataRefresh(row);
		setCurrentRow(row, true);
		fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_REFRESH));
	}   //  dataRefresh


	/**************************************************************************
	 *  Unconditionally Save data
	 *  @param manualCmd if true, no vetoable PropertyChange will be fired for save confirmation from MTable
	 *  @return true if save complete (or nor required)
	 */
	public boolean dataSave(boolean manualCmd)
	{
		log.fine("#" + m_vo.TabNo + " - row=" + m_currentRow);
		try
		{
			if (hasChangedCurrentTabAndParents())
				return false;
			
			boolean retValue = (m_mTable.dataSave(manualCmd) == GridTable.SAVE_OK);
			if (manualCmd)
			{
				setCurrentRow(m_currentRow, false);
				if (m_lastDataStatusEvent != null && m_lastDataStatusEvent.getCurrentRow() == m_currentRow 
					&& ((m_lastDataStatusEvent.Record_ID != null && m_lastDataStatusEvent.Record_ID instanceof Integer 
					&& (Integer) m_lastDataStatusEvent.Record_ID == 0) || m_lastDataStatusEvent.Record_ID == null))
				{
					updateDataStatusEventProperties(m_lastDataStatusEvent);
				}
			}
			fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_SAVE));
			
			if (retValue) {
				// refresh parent tabs with the same table
				refreshParentsSameTable();
			}
			
			return retValue;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "#" + m_vo.TabNo + " - row=" + m_currentRow, e);
		}
		return false;
	}   //  dataSave

	// Validate if the current tab record has changed in database or any parent record
	// Return if there are changes
	public boolean hasChangedCurrentTabAndParents() {
		String msg = null;
		// Carlos Ruiz / globalqss - [ adempiere-Bugs-1985481 ] Processed documents can be edited
		// Validate that current record has not changed and validate that every parent above has not changed
		if (m_mTable.hasChanged(m_currentRow)) {
			// return error stating that current record has changed and it cannot be saved
			msg = Msg.getMsg(Env.getCtx(), "CurrentRecordModified");
			log.saveError("CurrentRecordModified", msg, false);
			return true;
		}
		if (isDetail()) {
			// get parent tab
			// the parent tab is the first tab above with level = this_tab_level-1
			int level = m_vo.TabLevel;
			for (int i = m_window.getTabIndex(this) - 1; i >= 0; i--) {
				GridTab parentTab = m_window.getTab(i);
				if (parentTab.m_vo.TabLevel == level-1) {
					// this is parent tab
					if (parentTab.m_mTable.hasChanged(parentTab.m_currentRow)) {
						// return error stating that current record has changed and it cannot be saved
						msg = Msg.getMsg(Env.getCtx(), "ParentRecordModified") + ": " + parentTab.getName();
						log.saveError("ParentRecordModified", msg, false);
						return true;
					} else {
						// search for the next parent
						if (parentTab.isDetail()) {
							level = parentTab.m_vo.TabLevel;
						} else {
							break;
						}
					}
				}
			}
		}
		return false;
	}

	private void refreshParentsSameTable() {
		if (isDetail()) {
			// get parent tab
			// the parent tab is the first tab above with level = this_tab_level-1
			int level = m_vo.TabLevel;
			for (int i = m_window.getTabIndex(this) - 1; i >= 0; i--) {
				GridTab parentTab = m_window.getTab(i);
				if (parentTab.m_vo.TabLevel == level-1) {
					if (parentTab.getAD_Table_ID() == getAD_Table_ID()) {
						parentTab.dataRefresh();
					}
					// search for the next parent
					if (parentTab.isDetail()) {
						level = parentTab.m_vo.TabLevel;
					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 *  Do we need to Save?
	 *  @param rowChange row change
	 *  @param  onlyRealChange if true the value of a field was actually changed
	 *  (e.g. for new records, which have not been changed) - default false
	 *	@return true it needs to be saved
	 */
	public boolean needSave (boolean rowChange, boolean onlyRealChange)
	{
		if (rowChange)
		{
			return m_mTable.needSave(-2, onlyRealChange);
		}
		else
		{
			if (onlyRealChange)
				return m_mTable.needSave();
			else
				return m_mTable.needSave(onlyRealChange);
		}
	}   //  isDataChanged

	/**
	 *  Ignore data changes
	 */
	public void dataIgnore()
	{
		log.fine("#" + m_vo.TabNo);
		m_mTable.dataIgnore();
		setCurrentRow(m_currentRow, false);    //  re-load data
		
		fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_IGNORE));
		log.fine("#" + m_vo.TabNo + "- fini");
	}   //  dataIgnore


	/**
	 *  Create (copy) new Row
	 *  and process Callouts
	 *  @param copy copy
	 *  @return true if copied/new
	 */
	public boolean dataNew (boolean copy)
	{
		log.fine("#" + m_vo.TabNo);
		if (!isInsertRecord())
		{
			log.warning ("Inset Not allowed in TabNo=" + m_vo.TabNo);
			return false;
		}
		//	Prevent New Where Main Record is processed
		//	but not apply for TabLevel=0 - teo_sarca [ 1673902 ]
		if (m_vo.TabLevel > 0 && m_vo.TabNo > 0)
		{
			boolean processed = "Y".equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, "Processed"));
		//	boolean active = "Y".equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, "IsActive"));
			if (processed)
			{
				log.warning ("Not allowed in TabNo=" + m_vo.TabNo + " -> Processed=" + processed);
				return false;
			}
			log.finest("Processed=" + processed);
		}
		
		//hengsin, dont create new when parent is empty
		if (isDetail() && m_parentNeedSave)
			return false;
		
		/**
		 * temporary set currentrow to point to the new row to ensure even cause by m_mTable.dataNew
		 * is handle properly.
		 */
		int oldCurrentRow = m_currentRow;
		m_currentRow = m_currentRow + 1;
		boolean retValue = m_mTable.dataNew (oldCurrentRow, copy);
		m_currentRow = oldCurrentRow;
		if (!retValue)
			return retValue;
		setCurrentRow(m_currentRow + 1, true);
		
		//  process all Callouts (no dependency check - assumed that settings are valid)
		for (int i = 0; i < getFieldCount(); i++)
			processCallout(getField(i));
		//  check validity of defaults
		for (int i = 0; i < getFieldCount(); i++)
		{
			getField(i).refreshLookup();
			getField(i).validateValue();
		}
		m_mTable.setChanged(false);
		
		fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_NEW));
		return retValue;
	}   //  dataNew

	/**
	 *  Delete current Row
	 *  @return true if deleted
	 */
	public boolean dataDelete()
	{
		log.fine("#" + m_vo.TabNo + " - row=" + m_currentRow);
		boolean retValue = m_mTable.dataDelete(m_currentRow);
		setCurrentRow(m_currentRow, true);
		fireStateChangeEvent(new StateChangeEvent(this, StateChangeEvent.DATA_DELETE));
		return retValue;
	}   //  dataDelete


	/**
	 *	Get Name of Tab
	 *  @return name
	 */
	public String getName()
	{
		return m_vo.Name;
	}	//	getName

	/**
	 *	Get Description of Tab
	 *  @return description
	 */
	public String getDescription()
	{
		return m_vo.Description;
	}	//	getDescription

	/**
	 *	Get Help of Tab
	 *  @return help
	 */
	public String getHelp()
	{
		return m_vo.Help;
	}	//	getHelp

	/**
	 *  Get Tab Level
	 *  @return tab level
	 */
	public int getTabLevel()
	{
		return m_vo.TabLevel;
	}   //  getTabLevel

	/**
	 *  Get Commit Warning
	 *  @return commit warning
	 */
	public String getCommitWarning()
	{
		return m_vo.CommitWarning;
	}   //  getCommitWarning

	/**
	 *	Return Table Model
	 *  @return MTable
	 */
	protected GridTable getMTable()
	{
		return m_mTable;
	}	//	getMTable

	/**
	 *	Return the name of the key column - may be ""
	 *  @return key column name
	 */
	public String getKeyColumnName()
	{
		return m_keyColumnName;
	}	//	getKeyColumnName
	
	/**
	 * Set Name of the Key Column
	 * @param keyColumnName
	 */
	private void setKeyColumnName(String keyColumnName) {
		this.m_keyColumnName = keyColumnName;
		Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, CTX_KeyColumnName, m_keyColumnName);
	}

	/**
	 *	Return Name of link column
	 *  @return link column name
	 */
	public String getLinkColumnName()
	{
		return m_linkColumnName;
	}	//	getLinkColumnName

	/**
	 *	Set Name of link column.
	 * 	Set from MWindow.loadTabData
	 *  Used in MTab.isCurreny, (.setCurrentRow) .query - APanel.cmd_report
	 *    and MField.isEditable and .isDefault via context
	 *	@param linkColumnName	name of column - or sets name to AD_Column_ID, if exists
	 */
	public void setLinkColumnName (String linkColumnName)
	{
		// set parent column name
		String sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
		m_parentColumnName = DB.getSQLValueString(null, sql, m_vo.Parent_Column_ID );
		if ( m_parentColumnName == null )
			m_parentColumnName = "";
		
		
		
		if (linkColumnName != null)
			m_linkColumnName = linkColumnName;
		else
		{
			if (m_vo.AD_Column_ID == 0)
				return;
			//	we have a link column identified (primary parent column)
			else
			{
				String SQL = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
				try
				{
					PreparedStatement pstmt = DB.prepareStatement(SQL, null);
					pstmt.setInt(1, m_vo.AD_Column_ID);		//	Parent Link Column
					ResultSet rs = pstmt.executeQuery();
					if (rs.next())
						m_linkColumnName = rs.getString(1);
					rs.close();
					pstmt.close();
				}
				catch (SQLException e)
				{
					log.log(Level.SEVERE, "", e);
				}
				log.fine("AD_Column_ID=" + m_vo.AD_Column_ID + " - " + m_linkColumnName);
			}
		}
		Env.setContext(m_vo.ctx, m_vo.WindowNo, m_vo.TabNo, CTX_LinkColumnName, m_linkColumnName);
	}	//	setLinkColumnName

	/**
	 *	Is the tab current?.
	 *  <pre>
	 *	Yes 	- Table must be open
	 *			- Query String is the same
	 *			- Not Detail
	 *			- Old link column value is same as current one
	 *  </pre>
	 *  @return true if current
	 */
	public boolean isCurrent()
	{
		//	Open?
		if (!m_mTable.isOpen())
			return false;
		//	Same Query
		if (!m_oldQuery.equals(m_query.getWhereClause()))
			return false;
		//	Detail?
		if (!isDetail())
			return true;
		//	Same link column value
		String value = Env.getContext(m_vo.ctx, m_vo.WindowNo, getLinkColumnName());
		return m_linkValue.equals(value);
	}	//	isCurrent

	/**
	 *	Is the tab/table currently open
	 *  @return true if open
	 */
	public boolean isOpen()
	{
		//	Open?
		if (m_mTable != null)
			return m_mTable.isOpen();
		return false;
	}	//	isCurrent


	/**
	 *  Is Tab Incluced in other Tab
	 *  @return true if included
	 */
	public boolean isIncluded()
	{
		return m_included;
	}   //  isIncluded

	/**
	 *  Is Tab Incluced in other Tab
	 *  @param isIncluded true if included
	 */
	public void setIncluded(boolean isIncluded)
	{
		m_included = isIncluded;
	}   //  setIncluded

	/**
	 *  Are Only Current Rows displayed
	 *  @return true if no history
	 */
	public boolean isOnlyCurrentRows()
	{
		return m_vo.onlyCurrentRows;
	}   //  isOnlyCurrentRows
	/**
	 *	Return Parent ArrayList
	 *  @return parent column names
	 */
	public ArrayList<String> getParentColumnNames()
	{
		return m_parents;
	}	//	getParentColumnNames

	/**
	 *	Get Tree ID of this tab
	 *  @return ID
	 */

	/**
	 *	Returns true if this is a detail record
	 *  @return true if not parent tab
	 */
	public boolean isDetail()
	{
		// First Tab Level is not a detail 
		if (m_vo.TabLevel == 0)
			return false;
		//	We have IsParent columns and/or a link column
		if (m_parents.size() > 0 || m_vo.AD_Column_ID != 0)
			return true;
		return false;
	}	//	isDetail

	/**
	 *	Is Printed (Document can be printed)
	 *  @return true if printing
	 */
	public boolean isPrinted()
	{
		return m_vo.AD_Process_ID != 0;
	}	//	isPrinted

	/**
	 *	Get WindowNo
	 *  @return window no
	 */
	public int getWindowNo()
	{
		return m_vo.WindowNo;
	}	//	getWindowNo

	/**
	 *	Get TabNo
	 *  @return tab no
	 */
	public int getTabNo()
	{
		return m_vo.TabNo;
	}	//	getTabNo

	/**
	 *	Get Process ID
	 *  @return Process ID
	 */
	public int getAD_Process_ID()
	{
		return m_vo.AD_Process_ID;
	}	//	getAD_Process_ID

	/**
	 *	Is High Volume?
	 *  @return true if high volumen table
	 */
	public boolean isHighVolume()
	{
		return m_vo.IsHighVolume;
	}	//	isHighVolume

	/**
	 *	Is Read Only?
	 *  @return true if read only
	 */
	public boolean isReadOnly()
	{
		if (m_vo.IsReadOnly)
			return true;
		
		//hengsin, make detail readonly when parent is empty
		if (m_parentNeedSave) return true;

		//  no restrictions
		if (m_vo.ReadOnlyLogic == null || m_vo.ReadOnlyLogic.equals(""))
			return m_vo.IsReadOnly;

		//  ** dynamic content **  uses get_ValueAsString
		boolean retValue = Evaluator.evaluateLogic(this, m_vo.ReadOnlyLogic);
		log.finest(m_vo.Name
			+ " (" + m_vo.ReadOnlyLogic + ") => " + retValue);
		return retValue;
	}	//	isReadOnly

	/**
	 * 	Tab contains Always Update Field
	 *	@return true if field with always updateable
	 */
	public boolean isAlwaysUpdateField()
	{
		for (int i = 0; i < m_mTable.getColumnCount(); i++)
		{
			GridField field = m_mTable.getField(i);
			if (field.isAlwaysUpdateable())
				return true;
		}
		return false;
	}	//	isAlwaysUpdateField

	/**
	 *	Can we Insert Records?
	 *  @return true not read only and allowed
	 */
	public boolean isInsertRecord()
	{
		if (isReadOnly())
			return false;
		return m_vo.IsInsertRecord;
	}	//	isInsertRecord

	/**
	 *	Is the Tab Visible.
	 *	Called when constructing the window.
	 *  @return true, if displayed
	 */
	public boolean isDisplayed ()
	{
		//  no restrictions
		String dl = m_vo.DisplayLogic;
		if (dl == null || dl.equals(""))
			return true;

		//  ** dynamic content **
		String parsed = Env.parseContext (m_vo.ctx, 0, dl, false, false).trim();
		if (parsed.length() == 0)
			return true;
		boolean retValue = Evaluator.evaluateLogic(this, dl);
		log.config(m_vo.Name + " (" + dl + ") => " + retValue);
		return retValue;
	}	//	isDisplayed

	/**
	 * 	Get Variable Value (Evaluatee)
	 *	@param variableName name
	 *	@return value
	 */
	public String get_ValueAsString (String variableName)
	{
		return Env.getContext (m_vo.ctx, m_vo.WindowNo, variableName, true);
	}	//	get_ValueAsString

	/**
	 *  Is Single Row
	 *  @return true if single row
	 */
	public boolean isSingleRow()
	{
		return m_vo.IsSingleRow;
	}   //  isSingleRow;

	/**
	 *  Set Single Row.
	 *  Temporary store of current value
	 *  @param isSingleRow toggle
	 */
	public void setSingleRow (boolean isSingleRow)
	{
		m_vo.IsSingleRow = isSingleRow;
	}   //  setSingleRow


	/**
	 *  Has Tree
	 *  @return true if tree exists
	 */
	public boolean isTreeTab()
	{
		return m_vo.HasTree;
	}   //  isTreeTab

	/**
	 *	Get Tab ID
	 *  @return Tab ID
	 */
	public int getAD_Tab_ID()
	{
		return m_vo.AD_Tab_ID;
	}	//	getAD_Tab_ID

	/**
	 *	Get Table ID
	 *  @return Table ID
	 */
	public int getAD_Table_ID()
	{
		return m_vo.AD_Table_ID;
	}	//	getAD_Table_ID

	/**
	 *	Get Window ID
	 *  @return Window ID
	 */
	public int getAD_Window_ID()
	{
		return m_vo.AD_Window_ID;
	}	//	getAD_Window_ID

	/**
	 *	Get Included Tab ID
	 *  @return Included_Tab_ID
	 */
	public int getIncluded_Tab_ID()
	{
		return m_vo.Included_Tab_ID;
	}	//	getIncluded_Tab_ID

	/**
	 *	Get TableName
	 *  @return Table Name
	 */
	public String getTableName()
	{
		return m_vo.TableName;
	}	//	getTableName

	/**
	 *	Get Tab Where Clause
	 *  @return where clause
	 */
	public String getWhereClause()
	{
		return m_vo.WhereClause;
	}	//	getWhereClause

	/**
	 * 	Is Sort Tab
	 * 	@return true if sort tab
	 */
	public boolean isSortTab()
	{
		return m_vo.IsSortTab;
	}	//	isSortTab

	/**
	 * 	Get Order column for sort tab
	 * 	@return AD_Column_ID
	 */
	public int getAD_ColumnSortOrder_ID()
	{
		return m_vo.AD_ColumnSortOrder_ID;
	}	//	getAD_ColumnSortOrder_ID

	/**
	 * 	Get Yes/No column for sort tab
	 * 	@return AD_Column_ID
	 */
	public int getAD_ColumnSortYesNo_ID()
	{
		return m_vo.AD_ColumnSortYesNo_ID;
	}	//	getAD_ColumnSortYesNo_ID


	/**************************************************************************
	 *	Get extended Where Clause (parent link)
	 *  @return parent link
	 */
	public String getWhereExtended()
	{
		return m_extendedWhere;
	}	//	getWhereExtended

	/**
	 *	Get Order By Clause
	 *  @param onlyCurrentRows only current rows
	 *  @return Order By Clause
	 */
	private String getOrderByClause(boolean onlyCurrentRows)
	{
		//	First Prio: Tab Order By
		if (m_vo.OrderByClause.length() > 0)
		{
			String orderBy = Env.parseContext(m_vo.ctx, m_vo.WindowNo, m_vo.OrderByClause, false, false);
			return orderBy;
		}

		//	Second Prio: Fields (save it)
		m_vo.OrderByClause = "";
		for (int i = 0; i < 3; i++)
		{
			String order = m_OrderBys[i];
			if (order != null && order.length() > 0)
			{
				if (m_vo.OrderByClause.length() > 0)
					m_vo.OrderByClause += ",";
				m_vo.OrderByClause += order;
			}
		}
		if (m_vo.OrderByClause.length() > 0)
			return m_vo.OrderByClause;

		//	Third Prio: onlyCurrentRows
		m_vo.OrderByClause = "Created";
		if (onlyCurrentRows && !isDetail())	//	first tab only
			m_vo.OrderByClause += " DESC";
		return m_vo.OrderByClause;
	}	//	getOrderByClause


	/**************************************************************************
	 *	Transaction support.
	 *	Depending on Table returns transaction info
	 *  @return info
	 */
	public String getTrxInfo()
	{
		//	InvoiceBatch
		if (m_vo.TableName.startsWith("C_InvoiceBatch"))
		{
			int Record_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, "C_InvoiceBatch_ID");
			log.fine(m_vo.TableName + " - " + Record_ID);
			MessageFormat mf = null;
			try
			{
				mf = new MessageFormat(Msg.getMsg(Env.getAD_Language(m_vo.ctx), "InvoiceBatchSummary"), Env.getLanguage(m_vo.ctx).getLocale());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "InvoiceBatchSummary=" + Msg.getMsg(Env.getAD_Language(m_vo.ctx), "InvoiceBatchSummary"), e);
			}
			if (mf == null)
				return " ";
			/**********************************************************************
			 *	** Message: ExpenseSummary **
			 *	{0} Line(s) {1,number,#,##0.00}  - Total: {2,number,#,##0.00}
			 *
			 *	{0} - Number of lines
			 *	{1} - Toral
			 *	{2} - Currency
			 */
			Object[] arguments = new Object[3];
			boolean filled = false;
			//
			String sql = "SELECT COUNT(*), NVL(SUM(LineNetAmt),0), NVL(SUM(LineTotalAmt),0) "
				+ "FROM C_InvoiceBatchLine "
				+ "WHERE C_InvoiceBatch_ID=? AND IsActive='Y'";
			//
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, Record_ID);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	{0} - Number of lines
					Integer lines = new Integer(rs.getInt(1));
					arguments[0] = lines;
					//	{1} - Line net
					Double net = new Double(rs.getDouble(2));
					arguments[1] = net;
					//	{2} - Line net
					Double total = new Double(rs.getDouble(3));
					arguments[2] = total;
					filled = true;
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, m_vo.TableName + "\nSQL=" + sql, e);
			}
			if (filled)
				return mf.format (arguments);
			return " ";
		}	//	InvoiceBatch

		//	Order || Invoice
		else if (m_vo.TableName.startsWith("C_Order") || m_vo.TableName.startsWith("C_Invoice"))
		{
			int Record_ID;
			boolean isOrder = m_vo.TableName.startsWith("C_Order");
			//
			StringBuffer sql = new StringBuffer("SELECT COUNT(*) AS Lines,c.ISO_Code,o.TotalLines,o.GrandTotal,"
				+ "currencyBase(o.GrandTotal,o.C_Currency_ID,o.DateAcct, o.AD_Client_ID,o.AD_Org_ID) AS ConvAmt ");
			if (isOrder)
			{
				Record_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, "C_Order_ID");
				sql.append("FROM C_Order o"
					+ " INNER JOIN C_Currency c ON (o.C_Currency_ID=c.C_Currency_ID)"
					+ " INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID) "
					+ "WHERE o.C_Order_ID=? ");
			}
			else
			{
				Record_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, "C_Invoice_ID");
				sql.append("FROM C_Invoice o"
					+ " INNER JOIN C_Currency c ON (o.C_Currency_ID=c.C_Currency_ID)"
					+ " INNER JOIN C_InvoiceLine l ON (o.C_Invoice_ID=l.C_Invoice_ID) "
					+ "WHERE o.C_Invoice_ID=? ");
			}
			sql.append("GROUP BY o.C_Currency_ID, c.ISO_Code, o.TotalLines, o.GrandTotal, o.DateAcct, o.AD_Client_ID, o.AD_Org_ID");

			log.fine(m_vo.TableName + " - " + Record_ID);
			MessageFormat mf = null;
			try
			{
				mf = new MessageFormat(Msg.getMsg(Env.getAD_Language(m_vo.ctx), "OrderSummary"), Env.getLanguage(m_vo.ctx).getLocale());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "OrderSummary=" + Msg.getMsg(Env.getAD_Language(m_vo.ctx), "OrderSummary"), e);
			}
			if (mf == null)
				return " ";
			/**********************************************************************
			 *	** Message: OrderSummary **
			 *	{0} Line(s) - {1,number,#,##0.00} - Toral: {2,number,#,##0.00} {3} = {4,number,#,##0.00}
			 *
			 *	{0} - Number of lines
			 *	{1} - Line toral
			 *	{2} - Grand total (including tax, etc.)
			 *	{3} - Currency
			 *	(4) - Grand total converted to local currency
			 */
			Object[] arguments = new Object[5];
			boolean filled = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	{0} - Number of lines
					Integer lines = new Integer(rs.getInt(1));
					arguments[0] = lines;
					//	{1} - Line toral
					Double lineTotal = new Double(rs.getDouble(3));
					arguments[1] = lineTotal;
					//	{2} - Grand total (including tax, etc.)
					Double grandTotal = new Double(rs.getDouble(4));
					arguments[2] = grandTotal;
					//	{3} - Currency
					String currency = rs.getString(2);
					arguments[3] = currency;
					//	(4) - Grand total converted to Euro
					Double grandEuro = new Double(rs.getDouble(5));
					arguments[4] = grandEuro;
					filled = true;
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, m_vo.TableName + "\nSQL=" + sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
	
			if (filled)
				return mf.format (arguments);
			return " ";
		}	//	Order || Invoice

		//	Expense Report
		else if (m_vo.TableName.startsWith("S_TimeExpense") && m_vo.TabNo == 0)
		{
			int Record_ID = Env.getContextAsInt(m_vo.ctx, m_vo.WindowNo, "S_TimeExpense_ID");
			log.fine(m_vo.TableName + " - " + Record_ID);
			MessageFormat mf = null;
			try
			{
				mf = new MessageFormat(Msg.getMsg(Env.getAD_Language(m_vo.ctx), "ExpenseSummary"), Env.getLanguage(m_vo.ctx).getLocale());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "ExpenseSummary=" + Msg.getMsg(Env.getAD_Language(m_vo.ctx), "ExpenseSummary"), e);
			}
			if (mf == null)
				return " ";
			/**********************************************************************
			 *	** Message: ExpenseSummary **
			 *	{0} Line(s) - Total: {1,number,#,##0.00} {2}
			 *
			 *	{0} - Number of lines
			 *	{1} - Toral
			 *	{2} - Currency
			 */
			Object[] arguments = new Object[3];
			boolean filled = false;
			//
			String SQL = "SELECT COUNT(*) AS Lines, SUM(ConvertedAmt*Qty) "
				+ "FROM S_TimeExpenseLine "
				+ "WHERE S_TimeExpense_ID=?";

			//
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(SQL, null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	{0} - Number of lines
					Integer lines = new Integer(rs.getInt(1));
					arguments[0] = lines;
					//	{1} - Line toral
					Double total = new Double(rs.getDouble(2));
					arguments[1] = total;
					//	{3} - Currency
					arguments[2] = " ";
					filled = true;
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, m_vo.TableName + "\nSQL=" + SQL, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
	
			if (filled)
				return mf.format (arguments);
			return " ";
		}	//	S_TimeExpense


		//	Default - No Trx Info
		return null;
	}	//	getTrxInfo

	/**
	 *  Load Dependant Information
	 */
	private void loadDependentInfo()
	{
		/**
		 * Load Order Type from C_DocTypeTarget_ID
		 */
		if (m_vo.TableName.equals("C_Order"))
		{
			int C_DocTyp_ID = 0;
			Integer target = (Integer)getValue("C_DocTypeTarget_ID");
			if (target != null)
				C_DocTyp_ID = target.intValue();
			if (C_DocTyp_ID == 0)
				return;

			String sql = "SELECT DocSubTypeSO FROM C_DocType WHERE C_DocType_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, C_DocTyp_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
					Env.setContext(m_vo.ctx, m_vo.WindowNo, "OrderType", rs.getString(1));
 			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}   //  loadOrderInfo

		// Set the Phone Format on BPartnerLocation based on Country
		if (m_vo.TableName.equals("C_BPartner_Location"))
		{
			Integer location_int = (Integer) getValue(X_C_BPartner_Location.COLUMNNAME_C_Location_ID);
			String phone_frm = null;
			if (location_int != null)
				// take the phone format from country
				phone_frm = DB.getSQLValueString(null, "SELECT ExpressionPhone FROM C_Country c, C_Location l WHERE c.C_Country_ID = l.C_Country_ID AND l.C_location_ID = ?", location_int);
			GridField fPhone = getField(X_C_BPartner_Location.COLUMNNAME_Phone);
			MColumn colPhone = null;
			if (fPhone != null)
				colPhone = MColumn.get(Env.getCtx(), fPhone.getAD_Column_ID());
			GridField fPhone2 = getField(X_C_BPartner_Location.COLUMNNAME_Phone2);
			MColumn colPhone2 = null;
			if (fPhone2 != null)
				colPhone2 = MColumn.get(Env.getCtx(), fPhone2.getAD_Column_ID());
			GridField fFax = getField(X_C_BPartner_Location.COLUMNNAME_Fax);
			MColumn colFax = null;
			if (fFax != null)
				colFax = MColumn.get(Env.getCtx(), fFax.getAD_Column_ID());
			// Apply the country format if the column doesn't have format
			if (colPhone != null && (colPhone.getVFormat() == null || colPhone.getVFormat().length() == 0))
				fPhone.setVFormat(phone_frm);
			if (colPhone2 != null && (colPhone2.getVFormat() == null || colPhone2.getVFormat().length() == 0))
				fPhone2.setVFormat(phone_frm);
			if (colFax != null && (colFax.getVFormat() == null || colFax.getVFormat().length() == 0))
				fFax.setVFormat(phone_frm);
		}
		
	}   //  loadDependentInfo


	/**************************************************************************
	 *	Load Attachments for this table
	 */
	public void loadAttachments()
	{
		log.fine("#" + m_vo.TabNo);
		if (!canHaveAttachment())
			return;

		String SQL = "SELECT AD_Attachment_ID, Record_ID FROM AD_Attachment "
			+ "WHERE AD_Table_ID=?";
		try
		{
			if (m_Attachments == null)
				m_Attachments = new HashMap<Integer,Integer>();
			else
				m_Attachments.clear();
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, m_vo.AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Integer key = new Integer(rs.getInt(2));
				Integer value = new Integer(rs.getInt(1));
				m_Attachments.put(key, value);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "loadAttachments", e);
		}
		log.config("#" + m_Attachments.size());
	}	//	loadAttachment

	/**
	 *	Can this tab have Attachments?.
	 *  <p>
	 *  It can have an attachment if it has a key column ending with _ID.
	 *  The key column is empty, if there is no single identifying key.
	 *  @return true if record can have attachment
	 */
	public boolean canHaveAttachment()
	{
		if (getKeyColumnName().endsWith("_ID"))
			return true;
		return false;
	}   //	canHaveAttachment

	/**
	 *	Returns true, if current row has an Attachment
	 *  @return true if record has attchment
	 */
	public boolean hasAttachment()
	{
		if (m_Attachments == null)
			loadAttachments();
		if (m_Attachments == null || m_Attachments.isEmpty())
			return false;
		//
		Integer key = new Integer(m_mTable.getKeyID (m_currentRow));
		return m_Attachments.containsKey(key);
	}	//	hasAttachment

	/**
	 *	Get Attachment_ID for current record.
	 *	@return ID or 0, if not found
	 */
	public int getAD_AttachmentID()
	{
		if (m_Attachments == null)
			loadAttachments();
		if (m_Attachments.isEmpty())
			return 0;
		//
		Integer key = new Integer(m_mTable.getKeyID (m_currentRow));
		Integer value = (Integer)m_Attachments.get(key);
		if (value == null)
			return 0;
		else
			return value.intValue();
	}	//	getAttachmentID

	/**************************************************************************
	 *	Load Chats for this table
	 */
	public void loadChats()
	{
		log.fine("#" + m_vo.TabNo);
		if (!canHaveAttachment())
			return;

		String sql = "SELECT CM_Chat_ID, Record_ID FROM CM_Chat "
			+ "WHERE AD_Table_ID=?";
		try
		{
			if (m_Chats == null)
				m_Chats = new HashMap<Integer,Integer>();
			else
				m_Chats.clear();
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_vo.AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Integer key = new Integer(rs.getInt(2));	//	Record_ID
				Integer value = new Integer(rs.getInt(1));	//	CM_Chat_ID
				m_Chats.put(key, value);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		log.config("#" + m_Chats.size());
	}	//	loadChats

	/**
	 *	Returns true, if current row has a Chat
	 *  @return true if record has chat
	 */
	public boolean hasChat()
	{
		if (m_Chats == null)
			loadChats();
		if (m_Chats == null || m_Chats.isEmpty())
			return false;
		//
		Integer key = new Integer(m_mTable.getKeyID (m_currentRow));
		return m_Chats.containsKey(key);
	}	//	hasChat

	/**
	 *	Get Chat_ID for this record.
	 *	@return ID or 0, if not found
	 */
	public int getCM_ChatID()
	{
		if (m_Chats == null)
			loadChats();
		if (m_Chats.isEmpty())
			return 0;
		//
		Integer key = new Integer(m_mTable.getKeyID (m_currentRow));
		Integer value = (Integer)m_Chats.get(key);
		if (value == null)
			return 0;
		else
			return value.intValue();
	}	//	getCM_ChatID


	/**************************************************************************
	 * 	Load Locks for Table and User
	 */
	public void loadLocks()
	{
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		log.fine("#" + m_vo.TabNo + " - AD_User_ID=" + AD_User_ID);
		if (!canHaveAttachment())
			return;

		String sql = "SELECT Record_ID "
			+ "FROM AD_Private_Access "
			+ "WHERE AD_User_ID=? AND AD_Table_ID=? AND IsActive='Y' "
			+ "ORDER BY Record_ID";
		try
		{
			if (m_Lock == null)
				m_Lock = new ArrayList<Integer>();
			else
				m_Lock.clear();
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_User_ID);
			pstmt.setInt(2, m_vo.AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Integer key = new Integer(rs.getInt(1));
				m_Lock.add(key);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		log.fine("#" + m_Lock.size());
	}	//	loadLooks

	/**
	 * 	Record Is Locked
	 * 	@return true if locked
	 */
	public boolean isLocked()
	{
		if (!MRole.getDefault(m_vo.ctx, false).isPersonalLock())
			return false;
		if (m_Lock == null)
			loadLocks();
		if (m_Lock == null || m_Lock.isEmpty())
			return false;
		//
		Integer key = new Integer(m_mTable.getKeyID (m_currentRow));
		return m_Lock.contains(key);
	}	//	isLocked

	/**
	 * 	Lock Record
	 * 	@param ctx context
	 *	@param Record_ID id
	 *	@param lock true if lock, otherwise unlock
	 */
	public void lock (Properties ctx, int Record_ID, boolean lock)
	{
		int AD_User_ID = Env.getContextAsInt(ctx, "#AD_User_ID");
		log.fine("Lock=" + lock + ", AD_User_ID=" + AD_User_ID
			+ ", AD_Table_ID=" + m_vo.AD_Table_ID + ", Record_ID=" + Record_ID);
		MPrivateAccess access = MPrivateAccess.get (ctx, AD_User_ID, m_vo.AD_Table_ID, Record_ID);
		if (access == null)
			access = new MPrivateAccess (ctx, AD_User_ID, m_vo.AD_Table_ID, Record_ID);
		access.setIsActive(lock);
		access.save();
		//
		loadLocks();
	}	//	lock


	/**************************************************************************
	 *	Data Status Listener from MTable.
	 *  - get raw info and add current row information
	 *  - update the current row
	 *  - redistribute (fire) Data Status event
	 *  @param e event
	 */
	public void dataStatusChanged (DataStatusEvent e)
	{
		log.fine("#" + m_vo.TabNo + " - " + e.toString());
		int oldCurrentRow = e.getCurrentRow();
		m_DataStatusEvent = e;          //  save it
		//  when sorted set current row to 0
		String msg = m_DataStatusEvent.getAD_Message();
		if (msg != null && msg.equals("Sorted"))
			setCurrentRow(0, true);
		//  set current row
		m_DataStatusEvent.setCurrentRow(m_currentRow);
		//  Same row - update value
		if (oldCurrentRow == m_currentRow)
		{
			GridField field = m_mTable.getField(e.getChangedColumn());
			if (field != null)
			{
				Object value = m_mTable.getValueAt(m_currentRow, e.getChangedColumn());
				field.setValue(value, m_mTable.isInserting());
			}
		}
		else    //  Redistribute Info with current row info
			fireDataStatusChanged(m_DataStatusEvent);
		
		//reset
		m_lastDataStatusEventTime = System.currentTimeMillis();
		m_lastDataStatusEvent = m_DataStatusEvent;
		m_DataStatusEvent = null;		
	//	log.fine("dataStatusChanged #" + m_vo.TabNo + "- fini", e.toString());
	}	//	dataStatusChanged

	/**
	 *	Inform Listeners and build WHO info
	 *  @param e event
	 */
	private void fireDataStatusChanged (DataStatusEvent e)
	{
		DataStatusListener[] listeners = m_listenerList.getListeners(DataStatusListener.class);
		if (listeners.length == 0)
			return;
		log.fine(e.toString());
		//  WHO Info
		if (e.getCurrentRow() >= 0)
		{
			updateDataStatusEventProperties(e);
		}
		e.setInserting(m_mTable.isInserting());
		//  Distribute/fire it
        for (int i = 0; i < listeners.length; i++)
        	listeners[i].dataStatusChanged(e);
	//	log.fine("fini - " + e.toString());
	}	//	fireDataStatusChanged

	private void updateDataStatusEventProperties(DataStatusEvent e) {
		e.Created = (Timestamp)getValue("Created");
		e.CreatedBy = (Integer)getValue("CreatedBy");
		e.Updated = (Timestamp)getValue("Updated");
		e.UpdatedBy = (Integer)getValue("UpdatedBy");
		e.Record_ID = getValue(m_keyColumnName);
		//  Info
		StringBuffer info = new StringBuffer(getTableName());
		//  We have a key column
		if (m_keyColumnName != null && m_keyColumnName.length() > 0)
		{
			info.append(" - ")
				.append(m_keyColumnName).append("=").append(e.Record_ID);
		}
		else    //  we have multiple parents
		{
			for (int i = 0; i < m_parents.size(); i++)
			{
				String keyCol = (String)m_parents.get(i);
				info.append(" - ")
					.append(keyCol).append("=").append(getValue(keyCol));
			}
		}
		e.Info = info.toString();
	}

	/**
	 *  Create and fire Data Status Error Event
	 *  @param AD_Message message
	 *  @param info info
	 *  @param isError if not true, it is a Warning
	 */
	public void fireDataStatusEEvent(String AD_Message, String info, boolean isError)
	{
		m_mTable.fireDataStatusEEvent(AD_Message, info, isError);
	}   //  fireDataStatusEvent

	/**
	 *  Create and fire Data Status Error Event (from Error Log)
	 *  @param errorLog log
	 */
	public void fireDataStatusEEvent (ValueNamePair errorLog)
	{
		if (errorLog != null)
			m_mTable.fireDataStatusEEvent(errorLog);
	}   //  fireDataStatusEvent

	/**
	 *  Get Current Row
	 *  @return current row
	 */
	public int getCurrentRow()
	{
		if (m_currentRow != verifyRow(m_currentRow))
			setCurrentRow(m_mTable.getRowCount()-1, true);
		return m_currentRow;
	}   //  getCurrentRow

	/**
	 *  Get Current Table Key ID
	 *  @return Record_ID
	 */
	public int getRecord_ID()
	{
		return m_mTable.getKeyID(m_currentRow);
	}   //  getRecord_ID

	/**
	 *  Get Key ID of row
	 *  @param  row row number
	 *  @return The Key ID of the row or -1 if not found
	 */
	public int getKeyID (int row)
	{
		return m_mTable.getKeyID (row);
	}   //  getCurrentKeyID

	/**
	 *  Navigate absolute - goto Row - (zero based).
	 *  - does nothing, if in current row
	 *  - saves old row if required
	 *  @param targetRow target row
	 *  @return current row
	 */
	public int navigate (int targetRow)
	{
		//  nothing to do
		if (targetRow == m_currentRow)
			return m_currentRow;
		log.info ("Row=" + targetRow);

		//  Row range check
		int newRow = verifyRow(targetRow);
		
		//  Check, if we have old uncommitted data
		if (m_mTable.dataSave(newRow, false) == false) 
			return m_currentRow;

		//remove/ignore new and unchange row
		if (m_mTable.isInserting())
		{
			if (newRow > m_currentRow)
				newRow--;
			dataIgnore();
		}
		
		//  new position
		return setCurrentRow(newRow, true);
	}   //  navigate

	/**
	 *  Navigate relatively - i.e. plus/minus from current position
	 *  @param rowChange row change
	 *  @return current row
	 */
	public int navigateRelative (int rowChange)
	{
		return navigate (m_currentRow + rowChange);
	}   //  navigateRelative

	/**
	 *  Navigate to current now (reload)
	 *  @return current row
	 */
	public int navigateCurrent()
	{
		log.info("Row=" + m_currentRow);
		return setCurrentRow(m_currentRow, true);
	}   //  navigateCurrent

	/**
	 *  Row Range check
	 *  @param targetRow target row
	 *  @return checked row
	 */
	private int verifyRow (int targetRow)
	{
		int newRow = targetRow;
		//  Table Open?
		if (!m_mTable.isOpen())
		{
			log.severe ("Table not open");
			return -1;
		}
		//  Row Count
		int rows = getRowCount();
		if (rows == 0)
		{
			log.fine("No Rows");
			return -1;
		}
		if (newRow >= rows)
		{
			newRow = rows-1;
			log.fine("Set to max Row: " + newRow);
		}
		else if (newRow < 0)
		{
			newRow = 0;
			log.fine("Set to first Row");
		}
		return newRow;
	}   //  verifyRow

	/**
	 *  Set current row and load data into fields.
	 *  If there is no row - load nulls
	 *  @param newCurrentRow new current row
	 *  @param fireEvents fire events
	 *  @return current row
	 */
	private int setCurrentRow (int newCurrentRow, boolean fireEvents)
	{
		int oldCurrentRow = m_currentRow;
		m_currentRow = verifyRow (newCurrentRow);
		log.fine("Row=" + m_currentRow + " - fire=" + fireEvents);

		//  Update Field Values
		int size = m_mTable.getColumnCount();
		for (int i = 0; i < size; i++)
		{
			GridField mField = m_mTable.getField(i);
			//  get Value from Table
			if (m_currentRow >= 0)
			{
				Object value = m_mTable.getValueAt(m_currentRow, i);
				mField.setValue(value, m_mTable.isInserting());
				if (m_mTable.isInserting())		//	set invalid values to null
					mField.validateValue();
			}
			else
			{   //  no rows - set to a reasonable value - not updateable
//				Object value = null;
//				if (mField.isKey() || mField.isParent() || mField.getColumnName().equals(m_linkColumnName))
//					value = mField.getDefault();
				
				// CarlosRuiz - globalqss [ 1881480 ] Navigation problem between tabs
				// the implementation of linking with window context variables is very weak
				// you must be careful when defining a column in a detail tab with a field
				// with the same column name as some of the links of the tabs above
				// this can cause bad behavior of linking
				if (mField.isKey())
					mField.setValueAndUpdateContext();
				else
					mField.setValue();
			}
		}
		loadDependentInfo();

		if (!fireEvents)    //  prevents informing twice
			return m_currentRow;

		//  inform VTable/..    -> rowChanged
		m_propertyChangeSupport.firePropertyChange(PROPERTY, oldCurrentRow, m_currentRow);

		//check last data status event
		long since = System.currentTimeMillis() - m_lastDataStatusEventTime;
		if (since <= 500 && m_lastDataStatusEvent != null)
		{
			m_DataStatusEvent = m_lastDataStatusEvent;
		}
		
		//  inform APanel/..    -> dataStatus with row updated
		if (m_DataStatusEvent == null)
			m_DataStatusEvent = new DataStatusEvent(this, getRowCount(),
				m_mTable.isInserting(),		//	changed
				Env.isAutoCommit(Env.getCtx(), m_vo.WindowNo), m_mTable.isInserting());
		//
		m_DataStatusEvent.setCurrentRow(m_currentRow);
		String status = m_DataStatusEvent.getAD_Message();
		if (status == null || status.length() == 0)
			 m_DataStatusEvent.setInfo(DEFAULT_STATUS_MESSAGE, null, false,false);
		fireDataStatusChanged(m_DataStatusEvent);
		
		//reset
		m_DataStatusEvent = null;
		
		return m_currentRow;
	}   //  setCurrentRow


	/**
	 *  Set current row - used for deleteSelection
	 *  @return current row
	 */
	public void setCurrentRow(int row){
			setCurrentRow(row, false);
	}


	/**************************************************************************
	 *  Get RowCount
	 *  @return row count
	 */
	public int getRowCount()
	{
		int count = m_mTable.getRowCount();
		//  Wait a bit if currently loading
		if (count == 0 && m_mTable.isLoading())
		{
			try
			{
				Thread.sleep(100);      //  .1 sec
			}
			catch (Exception e) {}
			count = m_mTable.getRowCount();
		}
		return count;
	}   //  getRowCount

	/**
	 *  Get Column/Field Count
	 *  @return field count
	 */
	public int getFieldCount()
	{
		return m_mTable.getColumnCount();
	}   //  getFieldCount

	/**
	 *  Get Field by index
	 *  @param index index
	 *  @return MField
	 */
	public GridField getField (int index)
	{
		return (index >= 0 ? m_mTable.getField(index) : null);
	}   //  getField

	/**
	 *  Get Field by DB column name
	 *  @param columnName column name
	 *  @return MField
	 */
	public GridField getField (String columnName)
	{
		return m_mTable.getField(columnName);
	}   //  getField

	/**
	 *  Get all Fields
	 *  @return MFields
	 */
	public GridField[] getFields ()
	{
		return m_mTable.getFields();
	}   //  getField

	/**
	 *  Set New Value & call Callout
	 *  @param columnName database column name
	 *  @param value value
	 *  @return error message or ""
	 */
	public String setValue (String columnName, Object value)
	{
		if (columnName == null)
			return "NoColumn";
		return setValue(m_mTable.getField(columnName), value);
	}   //  setValue

	/**
	 *  Set New Value & call Callout
	 *  @param field field
	 *  @param value value
	 *  @return error message or ""
	 */
	public String setValue (GridField field, Object value)
	{
		if (field == null)
			return "NoField";

		log.fine(field.getColumnName() + "=" + value + " - Row=" + m_currentRow);
		
		if (DisplayType.isID(field.getDisplayType()) && value instanceof Integer && ((Integer)value).intValue() < 0)
			value = null;
		
		int col = m_mTable.findColumn(field.getColumnName());
		m_mTable.setValueAt(value, m_currentRow, col, false);
		//
		return processFieldChange (field);
	}   //  setValue

	/**
	 * 	Is Processed
	 *	@return true if current record is processed
	 */
	public boolean isProcessed()
	{
		return getValueAsBoolean("Processed");
	}	//	isProcessed

	/**
	 * Is the current record active
	 * @return true if current record is active
	 * @author Teo Sarca - BF [ 1742159 ]
	 */
	public boolean isActive()
	{
		return getValueAsBoolean("IsActive");
	}	//	isProcessed

	/**
	 *  Process Field Change - evaluate Dependencies and process Callouts.
	 *
	 *  called from MTab.setValue or GridController.dataStatusChanged
	 *  @param changedField changed field
	 *  @return error message or ""
	 */
	public String processFieldChange (GridField changedField)
	{
		processDependencies (changedField);
		return processCallout (changedField);
	}   //  processFieldChange

	/**
	 *  Evaluate Dependencies
	 *  @param changedField changed field
	 */
	private void processDependencies (GridField changedField)
	{
		String columnName = changedField.getColumnName();
	//	log.trace(log.l4_Data, "Changed Column", columnName);

		//  when column name is not in list of DependentOn fields - fini
		if (!hasDependants(columnName))
			return;

		//  Get dependent MFields (may be because of display or dynamic lookup)
		ArrayList<GridField> list = getDependantFields(columnName);
		for (int i = 0; i < list.size(); i++)
		{
			GridField dependentField = (GridField)list.get(i);
		//	log.trace(log.l5_DData, "Dependent Field", dependentField==null ? "null" : dependentField.getColumnName());
			//  if the field has a lookup
			if (dependentField != null && dependentField.getLookup() instanceof MLookup)
			{
				MLookup mLookup = (MLookup)dependentField.getLookup();
			//	log.trace(log.l6_Database, "Lookup Validation", mLookup.getValidation());
				//  if the lookup is dynamic (i.e. contains this columnName as variable)
				if (mLookup.getValidation().indexOf("@"+columnName+"@") != -1)
				{
					log.fine(columnName + " changed - "
						+ dependentField.getColumnName() + " set to null");
					//  invalidate current selection
					setValue(dependentField, null);
				}
			}
		}   //  for all dependent fields
	}   //  processDependencies

	
	private List<String> activeCallouts = new ArrayList<String>();
	private List<Callout> activeCalloutInstance = new ArrayList<Callout>();
	
	/**
	 * 
	 * @return list of active call out for this tab
	 */
	public String[] getActiveCallouts()
	{
		String[] list = new String[activeCallouts.size()];
		return activeCallouts.toArray(list);
	}
	
	/**
	 * 
	 * @return list of active call out instance for this tab
	 */
	public Callout[] getActiveCalloutInstance()
	{
		Callout[] list = new Callout[activeCalloutInstance.size()];
		return activeCalloutInstance.toArray(list);
	}

	/**************************************************************************
	 *  Process Callout(s).
	 *  <p>
	 *  The Callout is in the string of
	 *  "class.method;class.method;"
	 * If there is no class name, i.e. only a method name, the class is regarded
	 * as CalloutSystem.
	 * The class needs to comply with the Interface Callout.
	 *
	 * For a limited time, the old notation of Sx_matheod / Ux_menthod is maintained.
	 *
	 * @param field field
	 * @return error message or ""
	 * @see org.compiere.model.Callout
	 */
	public String processCallout (GridField field)
	{
		String callout = field.getCallout();
		if (callout.length() == 0)
			return "";
		//
		if (isProcessed() && !field.isAlwaysUpdateable())		//	only active records
			return "";			//	"DocProcessed";

		Object value = field.getValue();
		Object oldValue = field.getOldValue();
		log.fine(field.getColumnName() + "=" + value
			+ " (" + callout + ") - old=" + oldValue);

		StringTokenizer st = new StringTokenizer(callout, ";,", false);
		while (st.hasMoreTokens())      //  for each callout
		{
			String cmd = st.nextToken().trim();
			
			//detect infinite loop
			if (activeCallouts.contains(cmd)) continue;
			
			String retValue = "";
			// FR [1877902]
			// CarlosRuiz - globalqss - implement beanshell callout
			// Victor Perez  - vpj-cd implement JSR 223 Scripting
			if (cmd.toLowerCase().startsWith(MRule.SCRIPT_PREFIX)) {
				
				MRule rule = MRule.get(m_vo.ctx, cmd.substring(MRule.SCRIPT_PREFIX.length()));
				if (rule == null) {
					retValue = "Callout " + cmd + " not found"; 
					log.log(Level.SEVERE, retValue);
					return retValue;
				}
				if ( !  (rule.getEventType().equals(MRule.EVENTTYPE_Callout) 
					  && rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
					retValue = "Callout " + cmd
						+ " must be of type JSR 223 and event Callout"; 
					log.log(Level.SEVERE, retValue);
					return retValue;
				}

				ScriptEngine engine = rule.getScriptEngine();

				// Window context are    W_
				// Login context  are    G_
				MRule.setContext(engine, m_vo.ctx, m_vo.WindowNo);
				// now add the callout parameters windowNo, tab, field, value, oldValue to the engine 
				// Method arguments context are A_
				engine.put(MRule.ARGUMENTS_PREFIX + "WindowNo", m_vo.WindowNo);
				engine.put(MRule.ARGUMENTS_PREFIX + "Tab", this);
				engine.put(MRule.ARGUMENTS_PREFIX + "Field", field);
				engine.put(MRule.ARGUMENTS_PREFIX + "Value", value);
				engine.put(MRule.ARGUMENTS_PREFIX + "OldValue", oldValue);
				engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", m_vo.ctx);

				try 
				{
					activeCallouts.add(cmd);
					retValue = engine.eval(rule.getScript()).toString();
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "", e);
					retValue = 	"Callout Invalid: " + e.toString();
					return retValue;
				}
				finally
				{
					activeCallouts.remove(cmd);
				}
				
			} else {

				Callout call = null;
				String method = null;
				int methodStart = cmd.lastIndexOf('.');
				try
				{
					if (methodStart != -1)      //  no class
					{
						Class<?> cClass = Class.forName(cmd.substring(0,methodStart));
						call = (Callout)cClass.newInstance();
						method = cmd.substring(methodStart+1);
					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "class", e);
					return "Callout Invalid: " + cmd + " (" + e.toString() + ")";
				}

				if (call == null || method == null || method.length() == 0)
					return "Callout Invalid: " + method;

				try
				{
					activeCallouts.add(cmd);
					activeCalloutInstance.add(call);
					retValue = call.start(m_vo.ctx, method, m_vo.WindowNo, this, field, value, oldValue);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "start", e);
					retValue = 	"Callout Invalid: " + e.toString();
					return retValue;
				}
				finally
				{
					activeCallouts.remove(cmd);
					activeCalloutInstance.remove(call);
				}
				
			}			
			
			if (!Util.isEmpty(retValue))		//	interrupt on first error
			{
				log.severe (retValue);
				return retValue;
			}
		}   //  for each callout
		return "";
	}	//	processCallout

	/**
	 *  Get Value of Field with columnName
	 *  @param columnName column name
	 *  @return value
	 */
	public Object getValue (String columnName)
	{
		if (columnName == null)
			return null;
		GridField field = m_mTable.getField(columnName);
		return getValue(field);
	}   //  getValue

	/**
	 * Get Boolean Value of Field with columnName.
	 * If there is no column with the given name, the context for current window will be checked.
	 * @param columnName column name
	 * @return boolean value or false if the field was not found
	 * @author Teo Sarca
	 */
	public boolean getValueAsBoolean(String columnName)
	{
		int index = m_mTable.findColumn(columnName);
		if (index != -1)
		{
			Object oo = m_mTable.getValueAt(m_currentRow, index);
			if (oo instanceof String)
				return "Y".equals(oo);
			if (oo instanceof Boolean)
				return ((Boolean)oo).booleanValue();
		}
		return "Y".equals(Env.getContext(m_vo.ctx, m_vo.WindowNo, columnName));
	}	//	isProcessed

	/**
	 *  Get Value of Field
	 *  @param field field
	 *  @return value
	 */
	public Object getValue (GridField field)
	{
		if (field == null)
			return null;
		return field.getValue();
	}   //  getValue

	/**
	 *  Get Value of Field in row
	 *  @param row row
	 *  @param columnName column name
	 *  @return value
	 */
	public Object getValue (int row, String columnName)
	{
		int col = m_mTable.findColumn(columnName);
		if (col == -1)
			return null;
		return m_mTable.getValueAt(row, col);
	}   //  getValue
	
	/*
	public boolean isNeedToSaveParent()
	{
		if (isDetail())
			return m_parentNeedSave;
		else
			return false;
	}*/

	/**
	 *  toString
	 *  @return String representation
	 */
	public String toString()
	{
		String retValue = "MTab #";
		if (m_vo != null)
			retValue += m_vo.TabNo + " " + m_vo.Name + " (" + m_vo.AD_Tab_ID + ")";
		else
			retValue += "???";
		return retValue;
	}   //  toString


	/**************************************************************************
	 *  @param l listener
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener l)
	{
		m_propertyChangeSupport.removePropertyChangeListener(l);
	}
	/**
	 *  @param l listener
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l)
	{
		m_propertyChangeSupport.addPropertyChangeListener(l);
	}

	/**
	 *  @param l listener
	 */
	public synchronized void removeDataStatusListener(DataStatusListener l)
	{
		m_listenerList.remove(DataStatusListener.class, l);
	}
	/**
	 *  @param l listener
	 */
	public synchronized void addDataStatusListener(DataStatusListener l)
	{
		m_listenerList.add(DataStatusListener.class, l);
	}
	
	/**
	 * @param l
	 */
	public synchronized void addStateChangeListener(StateChangeListener l) 
	{
		m_listenerList.add(StateChangeListener.class, l);
	}

	/**
	 * @param l
	 */
	public synchronized void removeStateChangeListener(StateChangeListener l) 
	{
		m_listenerList.remove(StateChangeListener.class, l);
	}
	
	/**
	 * Feature Request [1707462]
	 * Enable runtime change of VFormat
	 * @param Identifier field ident
	 * @param strNewFormat new mask
	 * @author fer_luck
	 */
	public void setFieldVFormat (String identifier, String strNewFormat)
	{
		m_mTable.setFieldVFormat(identifier, strNewFormat);
	}	//	setFieldVFormat	

	/**
	 * Switches the line/seqNo of the two rows
	 * @param from row index
	 * @param to row index
	 * @param sortColumn column index of sort column
	 * @param ascending sorting modus
	 */
	public void switchRows(int from, int to, int sortColumn, boolean ascending) {
		log.fine(from + " - " + to + " - " + sortColumn + " - " + ascending);
		// nothing to do
		if (from == to) {
			log.finest("nothing to do - from == to");
			return;
		}
		//check if lines are editable
		if(!(m_mTable.isRowEditable(from)&& m_mTable.isRowEditable(to))){
			log.finest("row not editable - return");
			return;
		}
		// Row range check
		to = verifyRow(to);
		if (to == -1) {
			log.finest("Row range check - return");
			return;
		}

		// Check, if we have old uncommitted data
		m_mTable.dataSave(to, false);

		//find the line column
		int lineCol = m_mTable.findColumn("Line");
		if (lineCol == -1) {
			lineCol = m_mTable.findColumn("SeqNo");
		}
		if(lineCol == -1){
			//no Line, no SeqNo
			return;
		}
		//get the line/seq numbers
		Integer lineNoCurrentRow = null;
		Integer lineNoNextRow = null;
		if (m_mTable.getValueAt(from, lineCol) instanceof Integer) {
			lineNoCurrentRow = (Integer) m_mTable.getValueAt(from, lineCol);
			lineNoNextRow = (Integer) m_mTable.getValueAt(to, lineCol);
		} else if (m_mTable.getValueAt(from, lineCol) instanceof BigDecimal) {
			lineNoCurrentRow = new Integer(((BigDecimal) m_mTable.getValueAt(from, lineCol))
					.intValue());
			lineNoNextRow = new Integer(((BigDecimal) m_mTable.getValueAt(to, lineCol))
					.intValue());
		} else {
			log.fine("unknown value format - return");
			return;
		}
		//don't sort special lines like taxes
		if (lineNoCurrentRow >= 9900
				|| lineNoNextRow >= 9900) {
			log.fine("don't sort - might be special lines");
			return; 
		}
		// switch the line numbers and save new values
		
		m_mTable.setValueAt(lineNoCurrentRow, to, lineCol);
		setCurrentRow(to, false);
		m_mTable.dataSave(true);
		m_mTable.setValueAt(lineNoNextRow, from, lineCol);
		setCurrentRow(from, false);
		m_mTable.dataSave(true);
		//resort
		if(sortColumn != -1) {
			m_mTable.sort(sortColumn, ascending);
		} else {
			m_mTable.sort(lineCol, true);
		}
		navigate(to);
	}
	
	private void fireStateChangeEvent(StateChangeEvent e)
	{
		StateChangeListener[] listeners = m_listenerList.getListeners(StateChangeListener.class);
		if (listeners.length == 0)
			return;
		for(int i = 0; i < listeners.length; i++) {
			listeners[i].stateChange(e);
		}
		
	}
	
	/**
	 * 
	 * @return list of all tabs included in this tab
	 */
	public List<GridTab> getIncludedTabs()
	{
		List<GridTab> list = new ArrayList<GridTab>(1);
		for (GridField field : getFields())
		{
			if (field.getIncluded_Tab_ID() > 0)
			{
				for (int i = 0; i < m_window.getTabCount(); i++)
				{
					final GridTab detailTab = m_window.getTab(i);
					if (detailTab.getAD_Tab_ID() == field.getIncluded_Tab_ID())
					{
						list.add(detailTab);
						break;
					}
				}
			}
		}
		return list;
	}
}	//	GridTab

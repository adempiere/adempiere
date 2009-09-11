/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.X_T_Selection;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.Adempiere;
import org.compiere.apps.ADialog;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.M_Element;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CFrame;
import org.compiere.swing.CLabel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

/**
 * UI Browser
 * @author victor.perez@e-evolution.com, victor.perez@e-evolution.com
 */
public class Browser extends CFrame implements ActionListener, VetoableChangeListener, ChangeListener, ListSelectionListener, TableModelListener, ASyncProcess
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1022167449752851083L;


	/**
	 *	Detail Protected Constructor.
	 *
	 * 	@param frame parent
	 * 	@param modal modal
	 * 	@param WindowNo window no
	 * 	@param value QueryValue
	 * 	@param tableName table name
	 * 	@param keyColumn key column (ignored)
	 * 	@param multiSelection multiple selections
	 * 	@param whereClause where clause
	 */
	public Browser(Frame frame, boolean modal, int WindowNo, String value,
		MBrowse browse, String keyColumn,
		boolean multiSelection, String whereClause)
	{
		m_Browse = browse;
		m_View = browse.getAD_View();
		p_WindowNo = WindowNo;
		p_keyColumn = keyColumn;
		p_multiSelection = multiSelection;
		if (whereClause == null || whereClause.indexOf('@') == -1)
			p_whereClause = whereClause;
		else
		{
			p_whereClause = Env.parseContext(Env.getCtx(), p_WindowNo, whereClause, false, false);
			if (p_whereClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
		}
		
		//super (frame, modal, WindowNo, tableName, keyColumn, multiSelection, whereClause);
		log.info(m_Browse.getName() + " - " + keyColumn + " - " + whereClause);
		setTitle(m_Browse.getName());
				

		initComponents();
		statInit();
		p_loadedOK = initBrowser ();
		setPreferredSize(getPreferredSize());
		
		//
		int no = detail.getRowCount();
		setStatusLine(Integer.toString(no) + " " 
			+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		//	Focus
		/*textField1.setValue(value);
		textField1.requestFocus();*/
		//if (value != null && value.length() > 0)
		//	executeQuery();
		
	}	//	InfoGeneral
	

	
	/** Smart Browse 							*/
	private MBrowse 	m_Browse = null;
	/** Smart View								*/
	private MView		m_View	= null;
	
	private static final int	WINDOW_WIDTH = 1024;	//	width of the window


	/** Table                   */
	//protected MiniTable detail = new MiniTable();
	
	/**  String Array of Column Info    */
	private Info_Column[] m_generalLayout;
	/** list of query columns           */
	private ArrayList<String> 	m_queryColumns = new ArrayList<String>();
	/** list of query columns (SQL) */
	private ArrayList<String>	m_queryColumnsSql = new ArrayList<String>();
	/** Process Parameters Panel    */
	private ProcessParameterPanel parameterPanel;
	/** Parameters					*/
	private ArrayList <String> m_parameters;
	/** Parameters Values 			*/
	private ArrayList <Object> m_values;
	/** MProcess process 			*/
	private MProcess m_process = null;
	/** ProcessInfo					*/
	ProcessInfo m_pi = null;
	
	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	/** Model Index of Key Column   */
	private int                 m_keyColumnIndex = -1;
	/** OK pressed                  */
	private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Result IDs              */
	private ArrayList<Integer>	m_results = new ArrayList<Integer>(3);
	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	/** StatusBar **/
	protected StatusBar statusBar = new StatusBar();
	/** Worker                  */
	private Worker              m_worker = null;
	/** Layout of Grid          */
	protected Info_Column[]   p_layout;
	private String              m_sqlMain;
	/** Count SQL Statement		*/
	private String              m_sqlCount;
	/** Order By Clause         */
	private String              m_sqlOrder;

	
	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Table Name              */
	protected String            p_FromClause;
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Enable more than one selection  */
	protected boolean			p_multiSelection;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";
	/** Window Width                */
	protected static final int        INFO_WIDTH = 800;
	
	/**
	 *	Static Setup - add fields to parameterPanel (GridLayout)
	 */
	private void statInit()
	{	
		searchPanel.setLayout(new ALayout());
		int cols = 0;
		int col = 2;
		int row = 0;
		for(MBrowseField field : m_Browse.getCriteriaFields())
		{
			M_Element element = new M_Element(m_Browse.getCtx(),field.getAD_Element_ID(), null);
			String title  = Msg.translate(Env.getCtx(), element.getColumnName());	
			String name  = field.getAD_View_Column().getAD_Column().getColumnName();
			addComponent(field, row, cols, field.getName(),title);
			cols = cols + col;
			
			if(field.isRange())
			{	
				title  = Msg.getMsg(Env.getCtx(), "To");
				addComponent(field, row, cols, field.getName()+"_To", title);
				cols = cols + col;
			}
			
			
			if(cols >= 4)
			{
				cols = 0;
				row ++;
			}
		}
	}
	
	private void addComponent(MBrowseField field,int row,int col,String name, String title) 
	{
		Component  data = null;
		CLabel label	= new CLabel(title);
			   label.setName("L_"+name);
		
		if(DisplayType.YesNo== field.getAD_Reference_ID())
		{
			data= new VCheckBox();
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if (DisplayType.String== field.getAD_Reference_ID())
		{
			data= new VString(name, false, false, true, 30, 30, "", null);
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if ( DisplayType.Number == field.getAD_Reference_ID() 
				|| DisplayType.Quantity == field.getAD_Reference_ID()
				|| DisplayType.CostPrice == field.getAD_Reference_ID()
				|| DisplayType.Integer == field.getAD_Reference_ID()
				|| DisplayType.Amount == field.getAD_Reference_ID())
		{
			data= new VNumber(name, false, false, true, field.getAD_Reference_ID(), title);
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if (DisplayType.Date== field.getAD_Reference_ID() 
			  || DisplayType.DateTime== field.getAD_Reference_ID())
		{
			data= new VDate();
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if (	DisplayType.TableDir== field.getAD_Reference_ID() 
				|| 	DisplayType.Table == field.getAD_Reference_ID() 
				||	DisplayType.ID == field.getAD_Reference_ID()
				||	DisplayType.List == field.getAD_Reference_ID())
		{
			data = (Component) getLookup(field);
			label.setLabelFor(data);
		}
		else if (DisplayType.Search == field.getAD_Reference_ID())
		{
			M_Element element = new M_Element(m_Browse.getCtx(), field.getAD_Element_ID(), null);
			MViewColumn column = field.getAD_View_Column();
			data	= new VLookup(element.getColumnName(),true, false, true,
					MLookupFactory.get (Env.getCtx(), p_WindowNo,  0, column.getAD_Column_ID() ,DisplayType.Search))			
			{
				private static final long serialVersionUID = 1L;
				public void setValue(Object arg0) {  				
					super.setValue(arg0);
				};
			}; 
			data.setBackground(AdempierePLAF.getInfoBackground());
			label.setLabelFor(data);
		}		
		searchPanel.add(label	, new ALayoutConstraint(row,col));
		searchPanel.add(data	, new ALayoutConstraint(row,col+1));
	
	}
	
	private Component getLookup(MBrowseField field) 
	{
		try 
		{
			MViewColumn column = field.getAD_View_Column();	
			String name = column.getAD_Column().getColumnName();
			Language language = Language.getLoginLanguage();
			MLookup dataL = MLookupFactory.get(m_Browse.getCtx(), p_WindowNo,column.getAD_Column_ID(),
					field.getAD_Reference_ID(), language, name , field.getAD_Reference_Value_ID(), false,"");
	
			VLookup data = new VLookup(name, field.isMandatory(), false, true, dataL);
			data.setBackground(AdempierePLAF.getInfoBackground());
			data.addVetoableChangeListener(this);			
			return data;
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "Browser.init", e);
		}
		return null;
	}

	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initBrowser ()
	{
		if (!initBrowserTable())
			return false;

		//  prepare table
		StringBuffer where = new StringBuffer(m_View.getParentEntityAliasName()+".IsActive='Y'");
		//StringBuffer where = new StringBuffer("");
		if (p_whereClause.length() > 0)
		{
			//where.append(" AND ").append(p_whereClause);
			where.append(p_whereClause);
		}
			
		prepareTable(m_generalLayout,
				     m_View.getFromClause(),
			where.toString(),
			"2");

		if(m_Browse.getAD_Process_ID() > 0)
		{
			m_process = MProcess.get(Env.getCtx(), m_Browse.getAD_Process_ID());
			m_pi = new ProcessInfo(m_process.getName(), m_Browse.getAD_Process_ID());
			m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
			m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			parameterPanel = new ProcessParameterPanel(p_WindowNo, m_pi);
			parameterPanel.setMode(ProcessParameterPanel.MODE_HORIZONTAL);
			parameterPanel.init();
			processPanel.add(parameterPanel,BorderLayout.CENTER);
		}
		return true;
	}	//	initInfo


	/**
	 *	Init info with Table.
	 *	- find QueryColumns (Value, Name, ..)
	 *	- build gridController & column
	 *  @return true if success
	 */
	private boolean initBrowserTable ()
	{
		Collection<MBrowseField> fields = m_Browse.getFields();
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		for (MBrowseField field : fields)
		{
			MViewColumn vcol = field.getAD_View_Column();
			M_Element element = new M_Element(m_Browse.getCtx(),field.getAD_Element_ID(),null);
			String columnName= element.getColumnName();
			if(field.isQueryCriteria())
			{	
				m_queryColumns.add(columnName);
			}	
			m_queryColumnsSql.add(vcol.getColumnSQL());
			
			//String columnName =vcol.getColumnName();
			int displayType = field.getAD_Reference_ID();
			boolean isKey = field.isKey();
			boolean isDisplayed = field.isDisplayed();
			int AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
			// teo_sarca
			String columnSql = vcol.getColumnSQL() + " AS "+ vcol.getColumnName();
			if (columnSql == null || columnSql.length() == 0)
				columnSql = columnName;
			//  Default
			StringBuffer colSql = new StringBuffer(columnSql);
			Class colClass = null;
			if (isKey)
				colClass = IDColumn.class;
			else if (!isDisplayed)
				;
			else if (DisplayType.YesNo 		== displayType)
				colClass = Boolean.class;
			else if (DisplayType.Amount 	== displayType)
				colClass = BigDecimal.class;
			else if (DisplayType.Number 	== displayType || 
					 DisplayType.Quantity 	== displayType)
				colClass = Double.class;
			else if (DisplayType.Integer 	== displayType || 
					 DisplayType.TableDir 	== displayType || 
					 DisplayType.Table 		== displayType || 
					 DisplayType.Search 	== displayType)
				colClass = Integer.class;
			else if (DisplayType.String 	== displayType || 
					 DisplayType.Text 		== displayType || 
					 DisplayType.Memo 		== displayType)
				colClass = String.class;
			else if (DisplayType.isDate(displayType))
				colClass = Timestamp.class;
			else if (DisplayType.List		== displayType)
			{
				if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
					colSql = new StringBuffer("(SELECT l.Name FROM AD_Ref_List l WHERE l.AD_Reference_ID=")
						.append(AD_Reference_Value_ID).append(" AND l.Value=").append(vcol.getColumnSQL())
						.append(") AS ").append(columnName);
				else
					colSql = new StringBuffer("(SELECT t.Name FROM AD_Ref_List l, AD_Ref_List_Trl t "
						+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID AND l.AD_Reference_ID=")
						.append(AD_Reference_Value_ID).append(" AND l.Value=").append(vcol.getColumnSQL())
						.append(" AND t.AD_Language='").append(Env.getAD_Language(Env.getCtx()))
						.append("') AS ").append(columnName);
				colClass = String.class;
			}
			if (colClass != null)
			{
				list.add(new Info_Column(Msg.translate(Env.getCtx(), columnName), colSql.toString(), colClass));
				log.finest("Added Column=" + columnName);
			}
			else
				log.finest("Not Added Column=" + columnName);
		}
	
		//	Miminum check
		/*if (m_queryColumns.size() == 0)
		{
			log.log(Level.SEVERE, "No query columns found");
			return false;
		}*/
	
		//log.finest("Table " + tableName + ", ID=" + AD_Table_ID 
		//	+ ", QueryColumns #" + m_queryColumns.size());
		//	Only 4 Query Columns
		while (m_queryColumns.size() > 4) {
			m_queryColumns.remove(m_queryColumns.size()-1);
			m_queryColumnsSql.remove(m_queryColumnsSql.size()-1);
		}
		
		//  Set Title
		String title = Msg.translate(Env.getCtx(), m_Browse.getName()); 
		setTitle(getTitle() + " " + title);
		
		if (list.size() == 0)
		{
			ADialog.error(p_WindowNo, this, "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Brwose for view=" + m_View.getName());
			return false;
		}
		log.finest("Browse Fields #" + list.size()); 

		//  Convert ArrayList to Array
		m_generalLayout = new Info_Column[list.size()];
		list.toArray(m_generalLayout);
		return true;
	}	//	initInfoTable


	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 * 	@return where clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer("");
		if(getParameters().size() > 0)
		{
			sql.append(" AND ");
		}
		
		Iterator <String> parameters = getParameters().iterator();
		while(parameters.hasNext())
		{
			String parameter = parameters.next();
			MBrowseField field = m_Browse.getField(parameter);
			MViewColumn column = field.getAD_View_Column();
			sql.append(column.getColumnSQL()).append("=? ");
			if(parameters.hasNext())
			{	
				sql.append(" AND ");
			}
		}
		return sql.toString();
	}	//	getSQLWhere
	
	/**
	 * set Parameteres and Values
	 */
	private void setParameters()
	{			
		/** Parameters **/
		m_parameters = new ArrayList();
		m_values = new ArrayList();
		
			for (Component c : searchPanel.getComponents())
			{	
				String name = c.getName();
				if(name == null)
					continue;
				MBrowseField field = m_Browse.getField(name);
				if(field == null)
					continue;
				
				if(field.getName().equals(name))
				{	
					if(c instanceof VLookup)
					{	
						VLookup component = (VLookup) c;
						addParameter(component.getName(), component.getValue());
					}	
					if(c instanceof VString)
					{	
						VString component  = (VString) c;
						addParameter(component.getName(), component.getValue());
					}	
					if(c instanceof VCheckBox)
					{
						VCheckBox component  = (VCheckBox) c;
						addParameter(component.getName(), component.getValue());
					}
				}	
			}
	}	
	
	/**
	 * add Parameters and Values
	 * @param name
	 * @param value
	 */
	private void addParameter(String name , Object value)
	{
		if(value != null && value.toString().length() > 0)
		{
			m_parameters.add(name);
			m_values.add(value);
		}		
	}
	
	/**
	 * get Parameters names
	 * @return ArrayList with parameters names
	 */
	private ArrayList <String> getParameters()
	{
		return m_parameters;
	}
	
	/**
	 * get Parameters values
	 * @return ArralyList with parameters values
	 */
	private ArrayList <Object> getParametersValues()
	{
		return m_values;
	}

	/**
	 *	Add directly Query as Strings
	 * 	@param sql sql buffer
	 * 	@param index index
	 * 	@param value value
	 */
	private void addSQLWhere(StringBuffer sql, int index, String value)
	{
		if (!(value.equals("") || value.equals("%")) && index < m_queryColumns.size())
		{
			//sql.append(" AND UPPER(").append(m_queryColumnsSql.get(index).toString()).append(") LIKE '");
			sql.append(" UPPER(").append(m_queryColumnsSql.get(index).toString()).append(") LIKE '");
			sql.append(value);
			if (value.endsWith("%"))
				sql.append("'");
			else
				sql.append("%'");
		}
	}	//	addSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 * 	@param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
	}   //  setParameters
	
	/**
	 *	Set Status Line
	 *  @param text text
	 *  @param error error
	 */
	public void setStatusLine (String text, boolean error)
	{
		statusBar.setStatusLine(text, error);
		Thread.yield();
	}	//	setStatusLine
	
	/**
	 *	Set Status DB
	 *  @param text text
	 */
	public void setStatusDB (String text)
	{
		statusBar.setStatusDB(text);
	}	//	setStatusDB
	
	/**************************************************************************
	 *  Execute Query
	 */
	protected void executeQuery()
	{
		setParameters();
		//  ignore when running
		if (m_worker != null && m_worker.isAlive())
			return;
		//
		if (!testCount())
			return;

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		m_worker = new Worker();
		m_worker.start();
	}   //  executeQuery

	/**************************************************************************
	 *  Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd)
	 *  and size Window
	 *  @param layout layout array
	 *  @param from from clause
	 *  @param staticWhere where clause
	 *  @param orderBy order by clause
	 */
	protected void prepareTable (Info_Column[] layout, String from, String staticWhere, String orderBy)
	{
		p_layout = layout;
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		for (int i = 0; i < layout.length; i++)
		{
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			//  adding ID column
			if (layout[i].isIDcol())
				sql.append(",").append(layout[i].getIDcolSQL());
			//  add to model
			detail.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				detail.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}
		
		//  Table Selection (Invoked before setting column class so that row selection is enabled)
		detail.setRowSelectionAllowed(true);
		//detail.addMouseListener(this);
		detail.setMultiSelection(p_multiSelection);
		detail.setShowTotals(true);
		
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			detail.setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		//
		sql.append(" WHERE ").append(staticWhere);
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + staticWhere;
		//
		m_sqlOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;

		if (m_keyColumnIndex == -1)
			log.log(Level.SEVERE, "No KeyColumn - " + sql);
		
		//  Window Sizing
		//parameterPanel.setPreferredSize(new Dimension (INFO_WIDTH, parameterPanel.getPreferredSize().height));
		//scrollPane.setPreferredSize(new Dimension(INFO_WIDTH, 300));
	}   //  prepareTable
	
	/**
	 * 	Test Row Count
	 *	@return true if display
	 */
	private boolean testCount()
	{
		long start = System.currentTimeMillis();
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		String countSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		countSql = MRole.getDefault().addAccessSQL(countSql, m_View.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(countSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = -1;
		try
		{
			pstmt = DB.prepareStatement(countSql, null);
			if(getParametersValues().size() > 0)
				DB.setParameters(pstmt, getParametersValues());
			setParameters (pstmt, true);
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, countSql, e);
			no = -2;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + no + " - " + (System.currentTimeMillis()-start) + "ms");
		MRole role = MRole.getDefault(); 		
		if (role.isQueryMax(no))
			return ADialog.ask(p_WindowNo, this, "InfoHighRecordCount", String.valueOf(no));
		return true;
	}	//	testCount

	private void setupToolBar()
	{
		AppsAction a = new AppsAction(ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
		bOk = new javax.swing.JButton(a);
        a = new AppsAction(ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
        bCancel = new javax.swing.JButton(a);
        toolsBar = new javax.swing.JToolBar();
        a = new AppsAction(ConfirmPanel.A_PRINT, null, ConfirmPanel.A_PRINT);
        bPrint = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_ZOOM, null, ConfirmPanel.A_ZOOM);
        bZoom = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_EXPORT, null, ConfirmPanel.A_EXPORT); 
        bExport = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
        bDelete = new javax.swing.JToggleButton(a);
        a = new AppsAction("Find", null, "Find"); 
        bFind = new javax.swing.JToggleButton(a);


        
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolsBar = new javax.swing.JToolBar();
        bPrint = new javax.swing.JToggleButton();
        bZoom = new javax.swing.JToggleButton();
        bExport = new javax.swing.JToggleButton();
        bDelete = new javax.swing.JToggleButton();
        bFind = new javax.swing.JToggleButton();
        tabsPanel = new javax.swing.JTabbedPane();
        searchTab = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        buttonSearchPanel = new javax.swing.JPanel();
        bSearch = new javax.swing.JButton();
        centerPanel = new javax.swing.JScrollPane();
        detail = new MiniTable();
        footPanel = new javax.swing.JPanel();
        footButtonPanel = new javax.swing.JPanel();
        bCancel = new javax.swing.JButton();
        bOk = new javax.swing.JButton();
        processPanel = new javax.swing.JPanel();
        graphPanel = new javax.swing.JPanel();
        
        setupToolBar();

        toolsBar.setRollover(true);

        bPrint.setText("Print");
        bPrint.setFocusable(false);
        bPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });
        toolsBar.add(bPrint);

        bZoom.setText("Zoom");
        bZoom.setFocusable(false);
        bZoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bZoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bZoomActionPerformed(evt);
            }
        });
        toolsBar.add(bZoom);

        bExport.setText("Export");
        bExport.setFocusable(false);
        bExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExportActionPerformed(evt);
            }
        });
        toolsBar.add(bExport);

        bDelete.setText("Delete");
        bDelete.setFocusable(false);
        bDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        toolsBar.add(bDelete);

        bFind.setText("Find");
        bFind.setFocusable(false);
        bFind.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFind.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFindActionPerformed(evt);
            }
        });
        toolsBar.add(bFind);

        getContentPane().add(toolsBar, java.awt.BorderLayout.PAGE_START);

        searchTab.setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new java.awt.BorderLayout());

        searchPanel.setLayout(new java.awt.GridBagLayout());
        topPanel.add(searchPanel, java.awt.BorderLayout.NORTH);

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });
        buttonSearchPanel.add(bSearch);

        topPanel.add(buttonSearchPanel, java.awt.BorderLayout.CENTER);

        searchTab.add(topPanel, java.awt.BorderLayout.NORTH);

        /*detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));*/
        centerPanel.setViewportView(detail);

        searchTab.add(centerPanel, java.awt.BorderLayout.CENTER);

        footPanel.setLayout(new java.awt.BorderLayout());

        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });
        footButtonPanel.add(bCancel);

        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });
        footButtonPanel.add(bOk);

        footPanel.add(footButtonPanel, java.awt.BorderLayout.SOUTH);

        processPanel.setLayout(new java.awt.BorderLayout());
        footPanel.add(processPanel, java.awt.BorderLayout.CENTER);

        searchTab.add(footPanel, java.awt.BorderLayout.SOUTH);

        tabsPanel.addTab("Search", searchTab);

        graphPanel.setLayout(new java.awt.BorderLayout());
        tabsPanel.addTab("Graph", graphPanel);

        getContentPane().add(tabsPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void bZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bZoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bZoomActionPerformed

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    	if(detail.getSelectedRowCount() == 0)
    		dispose();
		//	Prepare Process
		int AD_Process_ID = m_Browse.getAD_Process_ID();
		if(AD_Process_ID <= 0)
			return;

		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		instance.saveEx();
		for(Integer row : detail.getSelectedRows())
		{
			IDColumn id = (IDColumn)detail.getValueAt(row, 0);
			X_T_Selection selection = new X_T_Selection(Env.getCtx(), 0 , null);
			selection.setT_Selection_ID(id.getRecord_ID());
			selection.setAD_PInstance_ID(instance.get_ID());
			selection.saveEx();
		}					
		//call process
		m_pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
		parameterPanel.saveParameters();
		//	Execute Process
		ProcessCtl worker = new ProcessCtl(this, Env.getWindowNo(this), m_pi, null);
		worker.start();     //  complete tasks in unlockUI / generateShipments_complete
    	
    }   
    
    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    	dispose();
    }   
    
    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
       	this.executeQuery();
    }//GEN-LAST:event_bSearchActionPerformed

    private void bFindActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                                
    
    private void bExportActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                          
    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                        

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JToggleButton bDelete;
    private javax.swing.JToggleButton bExport;
    private javax.swing.JToggleButton bFind;
    private javax.swing.JButton bOk;
    private javax.swing.JToggleButton bPrint;
    private javax.swing.JButton bSearch;
    private javax.swing.JToggleButton bZoom;
    private javax.swing.JPanel buttonSearchPanel;
    private javax.swing.JScrollPane centerPanel;
    private MiniTable detail;
    private javax.swing.JPanel footButtonPanel;
    private javax.swing.JPanel footPanel;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JPanel processPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel searchTab;
    private javax.swing.JTabbedPane tabsPanel;
    private javax.swing.JToolBar toolsBar;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    
	/**
	 *  Worker
	 */
	class Worker extends Thread
	{
		private PreparedStatement m_pstmt = null;
		private ResultSet m_rs = null;
		
		/**
		 *  Do Work (load data)
		 */
		public void run()
		{
		//	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//	setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
			long start = System.currentTimeMillis();

			//  Clear Table
			detail.setRowCount(0);
			//
			String dynWhere = getSQLWhere();
			StringBuffer sql = new StringBuffer (m_sqlMain);
			if (dynWhere.length() > 0)
				sql.append(dynWhere);   //  includes first AND
			sql.append(m_sqlOrder);
			String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
			dataSql = MRole.getDefault().addAccessSQL(dataSql, m_View.getParentEntityAliasName(), 
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
			log.finer(dataSql);

			try
			{
				m_pstmt = DB.prepareStatement(dataSql, null);
				if(getParametersValues().size() > 0)
					DB.setParameters(m_pstmt, getParametersValues());
				
				setParameters (m_pstmt, false);	//	no count
				log.fine("Start query - " + (System.currentTimeMillis()-start) + "ms");
				m_rs = m_pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis()-start) + "ms");
				while (m_rs.next())
				{
					if (this.isInterrupted()) {
						log.finer("Interrupted");
						close();
						return;
					}
					int row = detail.getRowCount();
					detail.setRowCount(row+1);
					int colOffset = 1;  //  columns start with 1
					for (int col = 0; col < p_layout.length; col++)
					{
						Object data = null;
						Class<?> c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class)
							data = new IDColumn(m_rs.getInt(colIndex));
						else if (c == Boolean.class)
							data = new Boolean("Y".equals(m_rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = m_rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = m_rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(m_rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = new Integer(m_rs.getInt(colIndex));
						else if (c == KeyNamePair.class)
						{
							String display = m_rs.getString(colIndex);
							int key = m_rs.getInt(colIndex+1);
							data = new KeyNamePair(key, display);
							colOffset++;
						}
						else
							data = m_rs.getString(colIndex);
						//  store
						detail.setValueAt(data, row, col);
					//	log.fine( "r=" + row + ", c=" + col + " " + m_layout[col].getColHeader(),
					//  	"data=" + data.toString() + " " + data.getClass().getName() + " * " + m_table.getCellRenderer(row, col));
					}
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, dataSql, e);
			}
			close();
			//
			int no = detail.getRowCount();
			log.fine("#" + no + " - " + (System.currentTimeMillis()-start) + "ms");
			if(detail.getShowTotals())
				detail.addTotals(p_layout);
			detail.autoSize();
			//
			setCursor(Cursor.getDefaultCursor());
			setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
			setStatusDB(Integer.toString(no));
			if (no == 0)
				log.fine(dataSql);
			else
			{
				detail.getSelectionModel().setSelectionInterval(0, 0);
				detail.requestFocus();
			}
		}   //  run
		
		/**
		 *	Close ResultSet and Statement
		 */
		private void close() {
			DB.close(m_rs, m_pstmt);
			m_rs = null;
			m_pstmt = null;
		}
	}   //  Worker
	

	public static void main( String args[] ) 
	{
		Splash.getSplash();
		//Adempiere.startup(true);	//	needs to be here for UI
		//Adempiere.startupEnvironment(false);
		Adempiere.startup(true);
		Ini.setProperty(Ini.P_UID,"SuperUser");
		Ini.setProperty(Ini.P_PWD,"System");
		Ini.setProperty(Ini.P_ROLE,"GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
		Ini.setProperty(Ini.P_ORG,"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,"HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE,"English");
		//Ini.setProperty(Ini.P_PRINTER,"MyPrinter");
		Login login = new Login(Env.getCtx());
		login.batchLogin();
		
		Properties m_ctx = Env.getCtx();
		MBrowse browse = new MBrowse(m_ctx, 1000002 , null);
		JFrame frame = new JFrame();
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = "";
		Browser browser = 	new Browser(frame, modal , WindowNo, value, browse, keyColumn,multiSelection, whereClause);
		//browser.setPreferredSize(getPreferredSize ());
		browser.setVisible(true);
		browser.pack();
	}
	
	public int getAD_Browse_ID()
	{
		 return m_Browse.getAD_Browse_ID();
	}
	
	/**
	 * 	Get Preferred Size
	 *	@return size
	 */
	public Dimension getPreferredSize ()
	{
		Dimension size = super.getPreferredSize ();
		if (size.width > WINDOW_WIDTH)
			size.width = WINDOW_WIDTH - 30;
		return size;
	}	//	getPreferredSize


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		// TODO Auto-generated method stub
		
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void executeASync(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	public boolean isUILocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public void lockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	public void unlockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}
}

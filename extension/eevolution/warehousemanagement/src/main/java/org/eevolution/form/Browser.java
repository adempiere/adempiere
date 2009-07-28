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

import java.awt.Component;
import java.awt.Cursor;
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

import org.adempiere.model.I_AD_Element;
import org.adempiere.model.MSmartBrowse;
import org.adempiere.model.MSmartBrowseField;
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
import org.compiere.apps.StatusBar;
import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPInstance;
import org.compiere.model.MRole;
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
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

/**
 *
 * @author victor.perez@e-evolution.com, e-Evolution
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
	protected Browser(Frame frame, boolean modal, int WindowNo, String value,
		MSmartBrowse browse, String keyColumn,
		boolean multiSelection, String whereClause)
	{
		m_SmartBrowse = browse;
		m_SmartView = browse.getAD_View();
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
		log.info(m_SmartBrowse.getName() + " - " + keyColumn + " - " + whereClause);
		setTitle(m_SmartBrowse.getName());
				
		//
		initComponents();
		statInit();
		p_loadedOK = initBrowser ();
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
	private MSmartBrowse 	m_SmartBrowse = null;
	/** Smart View								*/
	private MView		m_SmartView	= null;
	/** User selection */
	private ArrayList<Integer> selection = null;


	/** Table                   */
	//protected MiniTable detail = new MiniTable();
	
	/**  String Array of Column Info    */
	private Info_Column[] m_generalLayout;
	/** list of query columns           */
	private ArrayList<String> 	m_queryColumns = new ArrayList<String>();
	/** list of query columns (SQL) */
	private ArrayList<String>	m_queryColumnsSql = new ArrayList<String>();

	
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


	private void Init()
	{
	}
	
	/**
	 *	Static Setup - add fields to parameterPanel (GridLayout)
	 */
	private void statInit()
	{	
		searchPanel.setLayout(new ALayout());
		int cols = 0;
		int col = 2;
		int row = 0;
		for(MSmartBrowseField field : m_SmartBrowse.getCriteriaFields())
		{
			I_AD_Element element = field.getAD_Element();
			String title  = Msg.translate(Env.getCtx(), element.getColumnName());			
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
	
	private void addComponent(MSmartBrowseField field,int row,int col,String name, String title)
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
				|| DisplayType.Integer == field.getAD_Reference_ID())
		{
			data= new VNumber(name, false, false, true, field.getAD_Reference_ID(), title);
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if (DisplayType.Date== field.getAD_Reference_ID())
		{
			data= new VDate();
			data.setName(name);
			label.setLabelFor(data);
			data.setBackground(AdempierePLAF.getInfoBackground());
		}
		else if (DisplayType.TableDir== field.getAD_Reference_ID() || DisplayType.Table == field.getAD_Reference_ID())
		{
			I_AD_Element element = field.getAD_Element();
			MViewColumn column = field.getAD_ViewColumn();
			data	= new VLookup(element.getColumnName(),true, false, true,
					MLookupFactory.get (Env.getCtx(), p_WindowNo, 0 , column.getAD_Column_ID() ,DisplayType.TableDir))
			{
				private static final long serialVersionUID = 1L;
				public void setValue(Object arg0) {  				
					super.setValue(arg0);
				};
			}; 
			data.setBackground(AdempierePLAF.getInfoBackground());
			label.setLabelFor(data);
		}
		else if (DisplayType.Search == field.getAD_Reference_ID())
		{
			I_AD_Element element = field.getAD_Element();
			MViewColumn column = field.getAD_ViewColumn();
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

	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initBrowser ()
	{
		if (!initBrowserTable())
			return false;

		//  prepare table
		StringBuffer where = new StringBuffer(m_SmartView.getParentEntityAliasName()+".IsActive='Y'");
		//StringBuffer where = new StringBuffer("");
		if (p_whereClause.length() > 0)
		{
			//where.append(" AND ").append(p_whereClause);
			where.append(p_whereClause);
		}
			
		prepareTable(m_generalLayout,
				     m_SmartView.getFromClause(),
			where.toString(),
			"2");

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
		Collection<MSmartBrowseField> fields = m_SmartBrowse.getFields();
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		for (MSmartBrowseField field : fields)
		{
			MViewColumn vcol = field.getAD_ViewColumn();
			I_AD_Element element = field.getAD_Element();
			String columnName= element.getColumnName();
			if(field.isQueryCriteria())
			{	
				m_queryColumns.add(columnName);
			}	
			m_queryColumnsSql.add(vcol.getSelectClause());
			
			//String columnName =vcol.getColumnName();
			int displayType = field.getAD_Reference_ID();
			boolean isKey = field.isKey();
			boolean isDisplayed = field.isDisplayed();
			int AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
			// teo_sarca
			String columnSql = vcol.getSelectClause() + " AS "+ vcol.getColumnName();
			if (columnSql == null || columnSql.length() == 0)
				columnSql = columnName;
			//  Default
			StringBuffer colSql = new StringBuffer(columnSql);
			Class colClass = null;
			if (isKey)
				colClass = IDColumn.class;
			else if (!isDisplayed)
				;
			else if (displayType == DisplayType.YesNo)
				colClass = Boolean.class;
			else if (displayType == DisplayType.Amount)
				colClass = BigDecimal.class;
			else if (displayType == DisplayType.Number || displayType == DisplayType.Quantity)
				colClass = Double.class;
			else if (displayType == DisplayType.Integer)
				colClass = Integer.class;
			else if (displayType == DisplayType.String || displayType == DisplayType.Text || displayType == DisplayType.Memo)
				colClass = String.class;
			else if (DisplayType.isDate(displayType))
				colClass = Timestamp.class;
			else if (displayType == DisplayType.List)
			{
				if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
					colSql = new StringBuffer("(SELECT l.Name FROM AD_Ref_List l WHERE l.AD_Reference_ID=")
						.append(AD_Reference_Value_ID).append(" AND l.Value=").append(columnSql)
						.append(") AS ").append(columnName);
				else
					colSql = new StringBuffer("(SELECT t.Name FROM AD_Ref_List l, AD_Ref_List_Trl t "
						+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID AND l.AD_Reference_ID=")
						.append(AD_Reference_Value_ID).append(" AND l.Value=").append(columnSql)
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
		String title = Msg.translate(Env.getCtx(), m_SmartBrowse.getName()); 
		setTitle(getTitle() + " " + title);
		
		if (list.size() == 0)
		{
			ADialog.error(p_WindowNo, this, "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Brwose for view=" + m_SmartView.getName());
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
		StringBuffer sql = new StringBuffer(" AND ");
		Iterator <MSmartBrowseField> fields = m_SmartBrowse.getCriteriaFields().iterator();
		while(fields.hasNext())
		{
			MSmartBrowseField field = fields.next();
			MViewColumn column = field.getAD_ViewColumn();
			sql.append(column.getSelectClause()).append("=? ");
			if(fields.hasNext())
			{	
				sql.append(" AND ");
			}
		}
		return sql.toString();
	}	//	getSQLWhere
	

	private ArrayList<Object> getParameters()
	{			
		/** Parameters **/
		ArrayList<Object> parameters = new ArrayList();
		
		for(MSmartBrowseField field : m_SmartBrowse.getCriteriaFields())
		{
			for (Component c : searchPanel.getComponents())
			{	
				String name = c.getName();
				if(name == null)
					continue;
					
				if(field.getAD_Element().getColumnName().equals(name))
				{	
					if(c instanceof VLookup)
					{	
						VLookup data = (VLookup) c;
						parameters.add(data.getValue());
					}	
					if(c instanceof VString)
					{	
						VString data = (VString) c;
						parameters.add(data.getValue());
					}	
					if(c instanceof VCheckBox)
					{
						VCheckBox data = (VCheckBox) c;
						parameters.add(data.getValue());
					}
				}	
			}
		}	
		return parameters;
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
		countSql = MRole.getDefault().addAccessSQL(countSql, m_SmartView.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(countSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = -1;
		try
		{
			pstmt = DB.prepareStatement(countSql, null);
			DB.setParameters(pstmt, getParameters());
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
        ToolsBar = new javax.swing.JToolBar();
        a = new AppsAction(ConfirmPanel.A_PRINT, null, ConfirmPanel.A_PRINT);
        bPrint = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_ZOOM, null, ConfirmPanel.A_ZOOM);
        bZoom = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_EXPORT, null, ConfirmPanel.A_EXPORT); 
        bExport = new javax.swing.JToggleButton(a);
        a = new AppsAction(ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
        bDelete = new javax.swing.JToggleButton(a);
        a = new AppsAction("Find", null, "Find"); 
        bFilter = new javax.swing.JToggleButton(a);
        bFilterStatus = new javax.swing.JToggleButton();
        bFilters = new javax.swing.JComboBox();
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabsPanel = new javax.swing.JTabbedPane();
        searchTab = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        buttonSearchPanel = new javax.swing.JPanel();
        buttonSearch = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        detail = new MiniTable();
        graphPanel = new javax.swing.JPanel();
        footPanel = new javax.swing.JPanel();
        /*bOk = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        ToolsBar = new javax.swing.JToolBar();
        bPrint = new javax.swing.JToggleButton();
        bZoom = new javax.swing.JToggleButton();
        bExport = new javax.swing.JToggleButton();
        bDelete = new javax.swing.JToggleButton();
        bFilter = new javax.swing.JToggleButton();
        bFilterStatus = new javax.swing.JToggleButton();
        bFilters = new javax.swing.JComboBox();*/
        setupToolBar();

        searchPanel.setLayout(new java.awt.GridBagLayout());

        buttonSearch.setText("Search");
        buttonSearchPanel.add(buttonSearch);

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
        scrollPane.setViewportView(detail);

        javax.swing.GroupLayout searchTabLayout = new javax.swing.GroupLayout(searchTab);
        searchTab.setLayout(searchTabLayout);
        searchTabLayout.setHorizontalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchTabLayout.createSequentialGroup()
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchTabLayout.createSequentialGroup()
                            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
                            .addContainerGap())
                        .addComponent(buttonSearchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1247, Short.MAX_VALUE))))
        );
        searchTabLayout.setVerticalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTabLayout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsPanel.addTab("Search", searchTab);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1253, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        tabsPanel.addTab("Graph", graphPanel);

        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });

        bCancel.setText("Cancel");

        javax.swing.GroupLayout footPanelLayout = new javax.swing.GroupLayout(footPanel);
        footPanel.setLayout(footPanelLayout);
        footPanelLayout.setHorizontalGroup(
            footPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footPanelLayout.createSequentialGroup()
                .addContainerGap(1082, Short.MAX_VALUE)
                .addComponent(bOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCancel)
                .addGap(19, 19, 19))
        );
        footPanelLayout.setVerticalGroup(
            footPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footPanelLayout.createSequentialGroup()
                .addGroup(footPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bOk)
                    .addComponent(bCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ToolsBar.setRollover(true);

        bPrint.setText("Print");
        bPrint.setFocusable(false);
        bPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });
        ToolsBar.add(bPrint);

        bZoom.setText("Zoom");
        bZoom.setFocusable(false);
        bZoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bZoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bZoomActionPerformed(evt);
            }
        });
        ToolsBar.add(bZoom);

        bExport.setText("Export");
        bExport.setFocusable(false);
        bExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolsBar.add(bExport);

        bDelete.setText("Delete");
        bDelete.setFocusable(false);
        bDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolsBar.add(bDelete);

        bFilter.setText("Filter");
        bFilter.setFocusable(false);
        bFilter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFilter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolsBar.add(bFilter);

        bFilterStatus.setText("Filter On");
        bFilterStatus.setFocusable(false);
        bFilterStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bFilterStatus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolsBar.add(bFilterStatus);

        bFilters.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFiltersActionPerformed(evt);
            }
        });
        ToolsBar.add(bFilters);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(footPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(ToolsBar, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ToolsBar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bZoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bZoomActionPerformed

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
		//	Prepare Process
		int AD_Process_ID = m_SmartBrowse.getAD_Process_ID();
		if(AD_Process_ID <= 0)
			return;

		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		instance.saveEx();
		for(Integer selectedId : selection)
		{
			X_T_Selection sel = new X_T_Selection(Env.getCtx(), selectedId , null);
			sel.setAD_PInstance_ID(instance.get_ID());
			sel.save();
		}			

		//call process
		ProcessInfo pi = new ProcessInfo ("Browser", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Execute Process
		ProcessCtl worker = new ProcessCtl(this, Env.getWindowNo(this), pi, null);
		worker.start();     //  complete tasks in unlockUI / generateShipments_complete
    	
    }   
    
    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    	this.dispose();
    }   
    
    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    	this.executeQuery();
    }   

    private void bFiltersActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolsBar;
    private javax.swing.JButton bCancel;
    private javax.swing.JToggleButton bDelete;
    private javax.swing.JToggleButton bExport;
    private javax.swing.JToggleButton bFilter;
    private javax.swing.JToggleButton bFilterStatus;
    private javax.swing.JComboBox bFilters;
    private javax.swing.JButton bOk;
    private javax.swing.JToggleButton bPrint;
    private javax.swing.JToggleButton bZoom;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JPanel buttonSearchPanel;
    private MiniTable detail;
    private javax.swing.JPanel footPanel;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel searchTab;
    private javax.swing.JTabbedPane tabsPanel;
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
			dataSql = MRole.getDefault().addAccessSQL(dataSql, m_SmartView.getParentEntityAliasName(), 
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
			log.finer(dataSql);

			try
			{
				m_pstmt = DB.prepareStatement(dataSql, null);
				DB.setParameters(m_pstmt, getParameters());
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
	
	/**
	 *	Save Selection & return selecion Query or ""
	 *  @return where clause like C_Order_ID IN (...)
	 */
	private void saveSelection()
	{
		log.info("");
		//  ID selection may be pending
		detail.editingStopped(new ChangeEvent(this));
		//  Array of Integers
		ArrayList<Integer> results = new ArrayList<Integer>();
		selection = null;

		//	Get selected entries
		int rows = detail.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)detail.getValueAt(i, 0);     //  ID in column 0
			//	log.fine( "Row=" + i + " - " + id);
			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;
		log.config("Selected #" + results.size());
		selection = results;

	}	//	saveSelection

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
		MSmartBrowse browse = new MSmartBrowse(m_ctx, 1000002 , null);
		JFrame frame = new JFrame();
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = "";
		Browser browser = 	new Browser(frame, modal , WindowNo, value, browse, keyColumn,multiSelection, whereClause);
		browser.setVisible(true);
		browser.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUILocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub
		
	}
}

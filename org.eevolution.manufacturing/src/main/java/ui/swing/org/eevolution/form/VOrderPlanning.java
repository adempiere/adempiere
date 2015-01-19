/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com           *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.search.Find;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridField;
import org.compiere.model.MOrder;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTab;
import org.compiere.model.MTable;
import org.compiere.swing.CPanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.eevolution.model.MPPOrder;
/**
 *
 * @author  vpj-cd
 */
public class VOrderPlanning extends CPanel
implements FormPanel, ActionListener, VetoableChangeListener, ChangeListener, ListSelectionListener, TableModelListener, ASyncProcess
{
	/** Creates new form VOrderPlanning */
	public VOrderPlanning()
	{
		initComponents();
	}

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		//Log.trace(Log.l1_User, "VOrderReceipIssue.init - WinNo=" + m_WindowNo,
		//	"AD_Client_ID=" + m_AD_Client_ID + ", AD_Org_ID=" + m_AD_Org_ID);
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "N");

		try
		{
			//	UI
			fillPicks();
			jbInit();
			//
			dynInit();
			m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			m_frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{			
			log.log(Level.SEVERE, "Info", e);

		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	private StatusBar statusBar = new StatusBar();
	private int AD_Table_ID = MTable.getTable_ID("PP_Order");
	private MTable table = null;

	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Table Name              */
	private String            p_tableName = getTableName();
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Enable more than one selection  */
	protected boolean			p_multiSelection =  true;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";

	/** Table                   */
	protected MiniTable         p_table = new MiniTable();
	/** Model Index of Key Column   */
	private int                 m_keyColumnIndex = -1;
	/** Layout of Grid          */
	protected Info_Column[]     p_layout;
	/** Main SQL Statement      */
	private String              m_sqlMain;
	/** Order By Clause         */
	private String              m_sqlAdd;

	protected int row = 0;

	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;				
	/** Worker                  */
	private Worker              m_worker = null;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VOrderPlanning.class);

	/** Static Layout           */
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true, true, true);
	protected CPanel parameterPanel = new CPanel();
	private JScrollPane scrollPane = new JScrollPane();
	//
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem calcMenu = new JMenuItem();

	/** Window Width                */
	static final int        INFO_WIDTH = 800;

	// public IDColumn id = new IDColumn(0);
	// id.setSelected(true);


	/**  Array of Column Info    */

	private  Info_Column[] m_layout = {
			//new ColumnInfo(" "," ", IDColumn.class, true, true, ""),
			new Info_Column(Msg.translate(Env.getCtx(), "Select"), p_tableName +".PP_Order_ID", IDColumn.class),
			new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), p_tableName + ".DocumentNo", String.class),
			new Info_Column(Msg.translate(Env.getCtx(), "Line"), p_tableName + ".Line", Integer.class),
			new Info_Column(Msg.translate(Env.getCtx(), "M_Product_ID"), "(SELECT Name FROM M_Product p WHERE p.M_Product_ID=" +p_tableName + ".M_Product_ID)", String.class),
			new Info_Column(Msg.translate(Env.getCtx(), "C_UOM_ID"), "(SELECT Name FROM C_UOM u WHERE u.C_UOM_ID=" +p_tableName + ".C_UOM_ID)", String.class),
			new Info_Column(Msg.translate(Env.getCtx(), "QtyEntered"), p_tableName+".QtyEntered",  BigDecimal.class),
			new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), p_tableName+".QtyOrdered",  BigDecimal.class),
			new Info_Column(Msg.translate(Env.getCtx(), "DateOrdered"), p_tableName+".DateOrdered", Timestamp.class),
			new Info_Column(Msg.translate(Env.getCtx(), "DateStartSchedule"), p_tableName+".DateStartSchedule", Timestamp.class),
			new Info_Column(Msg.translate(Env.getCtx(), "DateFinishSchedule"), p_tableName+".DateFinishSchedule", Timestamp.class)		

			//new Info_Column(Msg.translate(Env.getCtx(), "DateOrdered"), "o.DatePromided", Timestamp.class),
			//new Info_Column(Msg.translate(Env.getCtx(), "ConvertedAmount"), "C_Base_Convert(o.GrandTotal,o.C_Currency_ID,o.AD_Client_ID,o.DateAcct, o.AD_Org_ID)", BigDecimal.class),
			//new Info_Column(Msg.translate(Env.getCtx(), "IsSOTrx"), "o.IsSOTrx", Boolean.class),
			//new Info_Column(Msg.translate(Env.getCtx(), "Description"), "o.Description", String.class),
			//new Info_Column(Msg.translate(Env.getCtx(), "POReference"), "o.POReference", String.class)
	};




	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {//GEN-BEGIN:initComponents
		mainPanel = new javax.swing.JPanel();
		OrderPlanning = new javax.swing.JTabbedPane();
		PanelOrder = new javax.swing.JPanel();
		PanelFind = new javax.swing.JPanel();
		PanelCenter = new javax.swing.JPanel();
		PanelBottom = new javax.swing.JPanel();
		Results = new javax.swing.JPanel();

		setLayout(new java.awt.BorderLayout());

		mainPanel.setLayout(new java.awt.BorderLayout());

		PanelOrder.setLayout(new java.awt.BorderLayout());

		PanelOrder.add(PanelFind, java.awt.BorderLayout.NORTH);

		PanelOrder.add(PanelCenter, java.awt.BorderLayout.CENTER);

		PanelOrder.add(PanelBottom, java.awt.BorderLayout.SOUTH);

		OrderPlanning.addTab("Order", PanelOrder);

		OrderPlanning.addTab("Results", Results);

		mainPanel.add(OrderPlanning, java.awt.BorderLayout.CENTER);

		add(mainPanel, java.awt.BorderLayout.CENTER);

	}//GEN-END:initComponents

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	protected void jbInit() throws Exception
	{
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainPanel.setLayout(new java.awt.BorderLayout());

		setLayout(new java.awt.BorderLayout());
		southPanel.setLayout(southLayout);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		/*m_frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		m_frame.getContentPane().add(parameterPanel, BorderLayout.NORTH);
		m_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);*/

		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		/*add(southPanel, BorderLayout.SOUTH);
		add(parameterPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);*/

		scrollPane.getViewport().add(p_table, null);
		//
		confirmPanel.addActionListener(this);
		confirmPanel.getResetButton().setVisible(hasReset());
		confirmPanel.getCustomizeButton().setVisible(hasCustomize());
		confirmPanel.getHistoryButton().setVisible(hasHistory());
		confirmPanel.getZoomButton().setVisible(hasZoom());
		//
		JButton print = ConfirmPanel.createPrintButton(true);
		print.addActionListener(this);
		confirmPanel.addButton(print);
		//
		popup.add(calcMenu);
		calcMenu.setText(Msg.getMsg(Env.getCtx(), "Calculator"));
		calcMenu.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Calculator16.gif")));
		calcMenu.addActionListener(this);
		//
		p_table.getSelectionModel().addListSelectionListener(this);                     

		enableButtons();

	}	//	jbInit

	/**
	 *  Dynamic Init.
	 *  Table Layout, Visual, Listener
	 */
	private void dynInit()
	{

	}        

	/**
	 *	Fill Picks
	 *		Column_ID from C_Order
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception
	{

		//prepareTable (m_layout, getTableName(), " DocStatus='"+MPPOrder.DOCSTATUS_Drafted + "' " +find(), "2" );
		prepareTable (m_layout, getTableName(), " DocStatus='"+MPPOrder.DOCSTATUS_Drafted + "' ", "2" );
		executeQuery();
	}


	public void actionPerformed(ActionEvent e) 
	{
		//  Confirm Panel
		String cmd = e.getActionCommand();
		if (cmd.equals(ConfirmPanel.A_OK))                    
		{
			m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			//System.out.println("Row ...ID " + row);
			int rows[] = p_table.getSelectedRows();                    
			for (int r = 0 ; r < rows.length ; r ++ )
			{    

				IDColumn id = (IDColumn)p_table.getValueAt(rows[r], 0);  
				if (id != null && id.isSelected())
				{    
					Integer PP_Order_ID = id.getRecord_ID();                                                          
					MPPOrder order = new MPPOrder(Env.getCtx(), PP_Order_ID.intValue(), null);
					order.setDocStatus(order.prepareIt());
					order.setDocAction(MPPOrder.DOCACTION_Complete);
					order.save();
				}
			}        
			if(rows.length != 0)
			{	
				ADialog.info(m_WindowNo,this,"ProcessOK");
				executeQuery();
				m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				executeQuery();
			}   
			m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	       

		}
		if (cmd.equals(ConfirmPanel.A_CANCEL))                    
		{		     
			dispose();                
		}
		dispose(); 	
	}    

	public void dispose() {

		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}

	public void executeASync(org.compiere.process.ProcessInfo processInfo) {
	}

	public boolean isUILocked() {
		return false;    
	}

	public void lockUI(org.compiere.process.ProcessInfo processInfo) {
	}

	public void stateChanged(ChangeEvent e) {
	}

	public void tableChanged(TableModelEvent e) {
	}

	public void unlockUI(org.compiere.process.ProcessInfo processInfo) {
	}

	public void valueChanged(ListSelectionEvent e) 
	{                                  
	}

	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
	}


	private String find()
	{    
		int AD_Window_ID = MTable.get(Env.getCtx(), MOrder.Table_ID).getAD_Window_ID();
		int AD_Tab_ID = MTab.getTab_ID(AD_Window_ID, "Order");
		//
		GridField[] findFields = GridField.createFields(Env.getCtx(),AD_Window_ID, 0, AD_Tab_ID);	
		Find find = new Find (Env.getFrame(this),AD_Window_ID, this.getName() ,
				AD_Tab_ID, AD_Table_ID, getTableName(), 
				"", findFields, 1); 
		MQuery query = find.getQuery();
		if (query != null && query.isActive())
			return  query.getWhereClause();
		else return "";
	}


	/*private MField[] getFields()
    {
        ArrayList list = new ArrayList();

        M_Column[] cols = table.getColumns(true);

        for (int c = 0 ; c < cols.length ; c++)
        {
                StringBuffer sql = new StringBuffer("SELECT * FROM AD_Column WHERE AD_Column_ID = " + cols[c].getAD_Column_ID());

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {
                            MFieldVO vo = MFieldVO.create (Env.getCtx(), m_WindowNo, AD_Tab_ID , AD_Window_ID, true,rs);                           
                            MField field = new MField(vo);                
                            //System.out.println("Columna -------:" + field.getColumnName());      
                            list.add(field);
                        }

			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{			
                        log.log(Level.SEVERE, "InfoGeneral.initInfoTable 1", e);
		}

        }

        //
		MField[] lines = new MField[list.size ()];
		list.toArray (lines);
		return lines;
    }*/



	/**
	 *  Reset Parameters
	 *	To be overwritten by concrete classes
	 */
	void doReset()					{}
	/**
	 *  Has Reset (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has reset (default false)
	 */
	boolean hasReset()				{return false;}
	/**
	 *  History dialog
	 *	To be overwritten by concrete classes
	 */
	void showHistory()					{}
	/**
	 *  Has History (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has history (default false)
	 */
	boolean hasHistory()				{return false;}
	/**
	 *  Customize dialog
	 *	To be overwritten by concrete classes
	 */
	void customize()					{}
	/**
	 *  Has Customize (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has customize (default false)
	 */
	boolean hasCustomize()				{return false;}
	/**
	 *  Zoom action
	 *	To be overwritten by concrete classes
	 */
	void zoom()							{}
	/**
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	boolean hasZoom()					{return false;}


	/**
	 *  Enable OK, History, Zoom if row selected
	 */
	void enableButtons ()
	{
		boolean enable = true;//p_table.getSelectedRow() != -1;

		confirmPanel.getOKButton().setEnabled(true);
		if (hasHistory())
			confirmPanel.getHistoryButton().setEnabled(enable);
		if (hasZoom())
			confirmPanel.getZoomButton().setEnabled(enable);
	}   //  enableButtons


	/**************************************************************************
	 *  Execute Query
	 */
	void executeQuery()
	{
		//  ignore when running
		if (m_worker != null && m_worker.isAlive())
			return;
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
			p_table.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				p_table.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			p_table.setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		//
		if (!staticWhere.equals("")) 
			sql.append(" WHERE ").append(staticWhere);

		m_sqlMain = sql.toString();
		m_sqlAdd = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlAdd = " ORDER BY " + orderBy;

		if (m_keyColumnIndex == -1)			
			log.log(Level.SEVERE, "Info.prepareTable - No KeyColumn - " + sql);


		//  Table Selection
		p_table.setRowSelectionAllowed(true);
		//p_table.addMouseListener(this);
		p_table.setMultiSelection(false);
		p_table.setEditingColumn(0);
		p_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  

		//  Window Sizing
		parameterPanel.setPreferredSize(new Dimension (INFO_WIDTH, parameterPanel.getPreferredSize().height));
		scrollPane.setPreferredSize(new Dimension(INFO_WIDTH, 400));
	}   //  prepareTable

	/**
	 *  Get the key of currently selected row
	 *  @return selected key
	 */
	protected Integer getSelectedRowKey()
	{
		int row = p_table.getSelectedRow();
		if (row != -1 && m_keyColumnIndex != -1)
		{
			Object data = p_table.getModel().getValueAt(row, m_keyColumnIndex);
			if (data instanceof IDColumn)
				data = ((IDColumn)data).getRecord_ID();
			if (data instanceof Integer)
				return (Integer)data;
		}
		return null;
	}   //  getSelectedRowKey

	/**
	 *  Get Table name Synonym
	 *  @return table name
	 */
	String getTableName()
	{
		table = new MTable(Env.getCtx(),AD_Table_ID,"AD_Table");
		p_tableName = table.getTableName();
		return p_tableName;
	}   //  getTableName


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTabbedPane OrderPlanning;
	private javax.swing.JPanel PanelBottom;
	private javax.swing.JPanel PanelCenter;
	private javax.swing.JPanel PanelFind;
	private javax.swing.JPanel PanelOrder;
	private javax.swing.JPanel Results;
	private javax.swing.JPanel mainPanel;
	// End of variables declaration//GEN-END:variables

	/**
	 *  Worker
	 */
	class Worker extends Thread
	{
		/**
		 *  Do Work (load data)
		 */
		public void run()
		{
			log.fine("Info.Worker.run");
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                      
			StringBuffer sql = new StringBuffer (m_sqlMain);                    
			String dynWhere = find ();
			if (dynWhere.length() > 0)
				sql.append(" AND " + dynWhere);   //  includes first AND
			sql.append(m_sqlAdd);
			String xSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
			xSql = MRole.getDefault().addAccessSQL(xSql, getTableName(), 
					MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);

			try
			{
				PreparedStatement pstmt = DB.prepareStatement(xSql,null);
				log.fine("SQL=" + xSql);
				ResultSet rs = pstmt.executeQuery();
				while (!isInterrupted() & rs.next())
				{
					int row = p_table.getRowCount();
					p_table.setRowCount(row+1);
					int colOffset = 1;  //  columns start with 1
					for (int col = 0; col < p_layout.length; col++)
					{
						Object data = null;
						Class<?> c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class)
						{                                                       
							IDColumn id = new IDColumn(rs.getInt(colIndex));
							id.setSelected(true);
							data = id;                                                        
							p_table.setColumnReadOnly(0, false);
						}        
						else if (c == Boolean.class)
							data = new Boolean("Y".equals(rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = new Integer(rs.getInt(colIndex));
						else if (c == KeyNamePair.class)
						{
							String display = rs.getString(colIndex);
							int key = rs.getInt(colIndex+1);
							data = new KeyNamePair(key, display);
							colOffset++;
						}
						else
							data = rs.getString(colIndex);
						//  store
						p_table.setValueAt(data, row, col);
					}
				}
				log.config("Interrupted=" + isInterrupted());
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, xSql, e);
			}
			p_table.autoSize();
			//
			setCursor(Cursor.getDefaultCursor());

		}   //  run
	}   //  Worker

}

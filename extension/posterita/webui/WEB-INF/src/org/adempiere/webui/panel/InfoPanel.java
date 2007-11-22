/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.WStatusBar;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *	Search Information and return selection - Base Class.
 *  Based on Info written by Jorg Janke
 *  
 *  @author Sendy Yagambrum
 */
public abstract class InfoPanel extends Window implements EventListener, WTableModelListener
{
	
    public static InfoPanel create (int WindowNo,
            String tableName, String keyColumn, String value,
            boolean multiSelection, String whereClause)
        {
            InfoPanel info = null;

            if (tableName.equals("C_BPartner"))
                info = new InfoBPartnerPanel (value,WindowNo, !Env.getContext(Env.getCtx(),"IsSOTrx").equals("N"),
                        multiSelection, whereClause);
            else if (tableName.equals("M_Product"))
                info = new InfoProductPanel ( WindowNo,  0,0, 
                        multiSelection, value,whereClause);
            else if (tableName.equals("C_Invoice"))
                info = new InfoInvoicePanel ( WindowNo, value,
                        multiSelection, whereClause);
            else if (tableName.equals("A_Asset"))
                info = new InfoAssetPanel (WindowNo, 0, value,
                        multiSelection, whereClause);
            else if (tableName.equals("C_Order"))
                info = new InfoOrderPanel ( WindowNo, value,
                        multiSelection, whereClause);
            else if (tableName.equals("M_InOut"))
                info = new InfoInOutPanel (WindowNo, value,
                        multiSelection, whereClause);
            else if (tableName.equals("C_Payment"))
                info = new InfoPaymentPanel (WindowNo, value, multiSelection, whereClause);
            else if (tableName.equals("C_CashLine"))
               info = new InfoCashLinePanel (WindowNo, value,
                        multiSelection, whereClause);
            else if (tableName.equals("S_ResourceAssigment"))
                info = new InfoAssignmentPanel (WindowNo, value,
                        multiSelection, whereClause);
            else
                info = new InfoGeneralPanel (value, WindowNo,  
                    tableName, keyColumn, 
                    multiSelection, whereClause);
            //
            return info;
    
        }
    
	/**
	 * Show BPartner Info (non modal)
	 * @param WindowNo window no
	 */
	public static void showBPartner (int WindowNo)
	{
		InfoBPartnerPanel infoBPanel = new InfoBPartnerPanel ( "", WindowNo,  
			!Env.getContext(Env.getCtx(),"IsSOTrx").equals("N"),false, "");
		AEnv.showWindow(infoBPanel);
	}   //  showBPartner

	/**
	 * Show Asset Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showAsset (int WindowNo)
	{
		InfoPanel info = new InfoAssetPanel (WindowNo, 0, "", false, "");
		AEnv.showWindow(info);
	}   //  showBPartner

	/**
	 * Show Product Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	/*public static void showProduct (Frame frame, int WindowNo)
	{
		Info info = new InfoProduct (frame, false, WindowNo,
			Env.getContextAsInt(Env.getCtx(), WindowNo, "M_Warehouse_ID"),
			Env.getContextAsInt(Env.getCtx(), WindowNo, "M_PriceList_ID"), 
			"",		//	value 
			false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showProduct
*/
	/**
	 * Show Order Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	/*public static void showOrder (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoOrder (frame, false, WindowNo, value,
			false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showOrder
*/
	/**
	 * Show Invoice Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	/*public static void showInvoice (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoInvoice (frame, false, WindowNo, value,
			false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showInvoice
*/
	/**
	 * Show Shipment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showInOut (int WindowNo, String value)
	{
		InfoPanel info = new InfoInOutPanel (WindowNo, value,
			false, "");
		AEnv.showWindow(info);
	}   //  showInOut

	/**
	 * Show Payment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showPayment (int WindowNo, String value)
	{
		InfoPanel info = new InfoPaymentPanel (WindowNo, value,
			false, "");
		AEnv.showWindow(info);
	}   //  showPayment

	/**
	 * Show Cash Line Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showCashLine (int WindowNo, String value)
	{
		InfoPanel info = new InfoCashLinePanel (WindowNo, value,
			false, "");
		AEnv.showWindow(info);
	}   //  showCashLine

	/**
	 * Show Assignment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showAssignment (int WindowNo, String value)
	{
		InfoPanel info = new InfoAssignmentPanel (WindowNo, value,
			false, "");
		AEnv.showWindow(info);
	}   //  showAssignment

	/** Window Width                */
	static final int        INFO_WIDTH = 800;

	/**************************************************
     *  Detail Constructor 
     * @param WindowNo  WindowNo
     * @param tableName tableName
     * @param keyColumn keyColumn   
     * @param whereClause   whereClause
	 */
	protected InfoPanel (int WindowNo,
		String tableName, String keyColumn,boolean multipleSelection,
		 String whereClause)
	{
		
		log.info("WinNo=" + p_WindowNo + " " + whereClause);
		p_WindowNo = WindowNo;
		p_tableName = tableName;
		p_keyColumn = keyColumn;
        p_multipleSelection = multipleSelection;
		
		if (whereClause == null || whereClause.indexOf('@') == -1)
			p_whereClause = whereClause;
		else
		{
			p_whereClause = Env.parseContext(Env.getCtx(), p_WindowNo, whereClause, false, false);
			if (p_whereClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
		}
		init();
		
	}	//	InfoPanel
	
	private void init()
	{
        confirmPanel = new ConfirmPanel(true,true,false,false,true,true); 
        confirmPanel.addActionListener(Events.ON_CLICK, this);
        confirmPanel.setStyle("border-top: 2px groove #444; padding-top: 4px");
	}  //  init
	protected ConfirmPanel confirmPanel;
	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Table Name              */
	protected String            p_tableName;
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Enable more than one selection  */
	protected boolean			p_multipleSelection;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";
	protected WStatusBar statusBar = new WStatusBar();
	/**                    */
    private Vector<Object> line;
	private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Result IDs              */
	private ArrayList<Integer>	m_results = new ArrayList<Integer>(3);
    
    private ListModelTable model;
	/** Layout of Grid          */
	protected ColumnInfo[]     p_layout;
	/** Main SQL Statement      */
	private String              m_sqlMain;
	/** Count SQL Statement		*/
	private String              m_sqlCount;
	/** Order By Clause         */
	private String              m_sqlOrder;
	/**ValueChange listeners       */
    private ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();
	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	/**	SO Zoom Window						*/
	private int					m_SO_Window_ID = -1;
	/**	PO Zoom Window						*/
	private int					m_PO_Window_ID = -1;

	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	
	protected WListbox contentPanel = new WListbox();
	
	private static final String[] lISTENER_EVENTS = {};

	/**
	 *  Loaded correctly
	 *  @return true if loaded OK
	 */
	public boolean loadedOK()
	{
		return p_loadedOK;
	}   //  loadedOK

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


	
	
	protected void prepareTable (ColumnInfo[] layout, 
            String from, 
            String where, 
            String orderBy)
	{
        String sql =contentPanel.prepareTable(layout, from,
                where.toString(),false,
                getTableName(),false);
        p_layout = contentPanel.getLayout();
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + where;
		//
		m_sqlOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;
			
	}   //  prepareTable

	
	/**************************************************************************
	 *  Execute Query
	 */
	protected void executeQuery()
	{
		if (!testCount())
		{
			return ;
		}
		PreparedStatement m_pstmt = null;
		ResultSet m_rs = null;
		        
		long start = System.currentTimeMillis();
			//
	
        String dynWhere = getSQLWhere();
        StringBuffer sql = new StringBuffer (m_sqlMain);
        if (dynWhere.length() > 0)
            sql.append(dynWhere);   //  includes first AND
        sql.append(m_sqlOrder);
        String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());    //  Variables
        dataSql = MRole.getDefault().addAccessSQL(dataSql, getTableName(), 
            MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
        log.finer(dataSql);
		try
		{
			m_pstmt = DB.prepareStatement(dataSql, null);
			setParameters (m_pstmt, false);	//	no count
			log.fine("Start query - " + (System.currentTimeMillis()-start) + "ms");
			m_rs = m_pstmt.executeQuery();
			log.fine("End query - " + (System.currentTimeMillis()-start) + "ms");
            line = new Vector<Object>();
			while (m_rs.next())
			{
															
				int colOffset = 1;  //  columns start with 1
                Vector<Object> data = new Vector<Object>();
				for (int col = 0; col < p_layout.length; col++)
				{
					Object value = null;
					Class c = p_layout[col].getColClass();
					int colIndex = col + colOffset;
					if (c == IDColumn.class)
					{
                        value = new IDColumn(m_rs.getInt(colIndex));
						
					}
					else if (c == Boolean.class)
                        value = new Boolean("Y".equals(m_rs.getString(colIndex)));
					else if (c == Timestamp.class)
                        value = m_rs.getTimestamp(colIndex);
					else if (c == BigDecimal.class)
                        value = m_rs.getBigDecimal(colIndex);
					else if (c == Double.class)
                        value = new Double(m_rs.getDouble(colIndex));
					else if (c == Integer.class)
                        value = new Integer(m_rs.getInt(colIndex));
					else if (c == KeyNamePair.class)
					{
						String display = m_rs.getString(colIndex);
						int key = m_rs.getInt(colIndex+1);
                        value = new KeyNamePair(key, display);

						colOffset++;
					}
					else
					{
                        value = m_rs.getString(colIndex);
					}
					data.add(value);
				}
                line.add(data);
			}
            
		}        
		
		catch (SQLException e)
		{
			log.log(Level.SEVERE, dataSql, e);
		}
		
		try
		{
			if (m_rs != null)
				m_rs.close();
			if (m_pstmt != null)
				m_pstmt.close();
		}
		catch (SQLException e) 
		{
			log.log(Level.SEVERE, "closeRS", e);
		}
        
		m_rs = null;
		m_pstmt = null;        
	}  
    
    protected void renderItems()
    {
        Vector<String> columnHeader = getColumnHeader(p_layout);
        if (line != null)
        {
            model = new ListModelTable(line);
            model.addTableModelListener(this);
            contentPanel.setData(model, columnHeader);
        }
        int no = contentPanel.getRowCount();
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));
    }
    
   
    public Vector<String> getColumnHeader(ColumnInfo[] p_layout)
    {
        Vector<String> columnHeader = new Vector<String>();
        
        for (ColumnInfo info: p_layout)
        {
             columnHeader.add(info.getColHeader());
        }
        return columnHeader;
    }
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
		countSql = MRole.getDefault().addAccessSQL	(countSql, getTableName(), 
													MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(countSql);
		int no = -1;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(countSql, null);
			setParameters (pstmt, true);
			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next())
				no = rs.getInt(1);
			
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, countSql, e);
			no = -2;
		}
		
		log.fine("#" + no + " - " + (System.currentTimeMillis()-start) + "ms");
		
		//Armen: add role checking (Patch #1694788 )
		//MRole role = MRole.getDefault(); 		
		//if (role.isQueryMax(no))
		//	return ADialog.ask(p_WindowNo, this, "InfoHighRecordCount", String.valueOf(no));
		
		return true;
	}	//	testCount
			

	/**
	 *	Save Selection	- Called by dispose
	 */
	protected void saveSelection ()
	{
		//	Already disposed
		if (contentPanel == null)
			return;

		log.config( "OK=" + m_ok);
		
		if (!m_ok)      //  did not press OK
		{
			m_results.clear();
			contentPanel = null;
			this.detach();
            return;
		}

		//	Multi Selection
		if (p_multipleSelection)
		{
		}
		else    //  singleSelection
		{
			Integer data = getSelectedRowKey();
			if (data != null)
				m_results.add(data);
		}
		
		log.config(getSelectedSQL());

		//	Save Settings of detail info screens
		saveSelectionDetail();
		
	}	//	saveSelection

	/**
	 *  Get the key of currently selected row
	 *  @return selected key
	 */
	protected Integer getSelectedRowKey()
	{
		int key = contentPanel.getSelectedRowKey();
		
		return key;        
	}   //  getSelectedRowKey

	/**
	 *	Get selected Keys
	 *  @return selected keys (Integers)
	 */
	public Object[] getSelectedKeys()
	{
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.toArray();
	}	//	getSelectedKeys;

	/**
	 *	Get (first) selected Key
	 *  @return selected key
	 */
	public Object getSelectedKey()
	{
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.get(0);
	}	//	getSelectedKey

	/**
	 *	Is cancelled?
	 *	- if pressed Cancel = true
	 *	- if pressed OK or window closed = false
	 *  @return true if cancelled
	 */
	public boolean isCancelled()
	{
		return m_cancel;
	}	//	isCancelled

	/**
	 *	Get where clause for (first) selected key
	 *  @return WHERE Clause
	 */
	public String getSelectedSQL()
	{
		//	No results
		Object[] keys = getSelectedKeys();
		if (keys == null || keys.length == 0)
		{
			log.config("No Results - OK=" 
				+ m_ok + ", Cancel=" + m_cancel);
			return "";
		}
		//
		StringBuffer sb = new StringBuffer(getKeyColumn());
		if (keys.length > 1)
			sb.append(" IN (");
		else
			sb.append("=");

		//	Add elements
		for (int i = 0; i < keys.length; i++)
		{
			if (getKeyColumn().endsWith("_ID"))
				sb.append(keys[i].toString()).append(",");
			else
				sb.append("'").append(keys[i].toString()).append("',");
		}

		sb.replace(sb.length()-1, sb.length(), "");
		if (keys.length > 1)
			sb.append(")");
		return sb.toString();
	}	//	getSelectedSQL;

		
		
	/**
	 *  Get Table name Synonym
	 *  @return table name
	 */
	String getTableName()
	{
		return p_tableName;
	}   //  getTableName

	/**
	 *  Get Key Column Name
	 *  @return column name
	 */
	String getKeyColumn()
	{
		return p_keyColumn;
	}   //  getKeyColumn

	
	public String[] getEvents()
    {
        return InfoPanel.lISTENER_EVENTS;
    }
		
	/**************************************************************************
	 *  Get dynamic WHERE part of SQL
	 *	To be overwritten by concrete classes
	 *  @return WHERE clause
	 */
	abstract String getSQLWhere();
      	
	/**
	 *  Set Parameters for Query
	 *	To be overwritten by concrete classes
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	abstract void setParameters (PreparedStatement pstmt, boolean forCount) 
		throws SQLException;
    /**
     * notify to search editor of a value change in the selection info
     * @param event event
    *
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
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	boolean hasZoom()					{return false;}
	/**
	 *  Save Selection Details
	 *	To be overwritten by concrete classes
	 */
	void saveSelectionDetail()          {}

	/**
	 * 	Get Zoom Window
	 *	@param tableName table name
	 *	@param isSOTrx sales trx
	 *	@return AD_Window_ID
	 */
	protected int getAD_Window_ID (String tableName, boolean isSOTrx)
	{
		if (!isSOTrx && m_PO_Window_ID > 0)
			return m_PO_Window_ID;
		if (m_SO_Window_ID > 0)
			return m_SO_Window_ID;
		//
		String sql = "SELECT AD_Window_ID, PO_Window_ID FROM AD_Table WHERE TableName=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, tableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_SO_Window_ID = rs.getInt(1);
				m_PO_Window_ID = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//
		if (!isSOTrx && m_PO_Window_ID > 0)
			return m_PO_Window_ID;
		return m_SO_Window_ID;
	}	//	getAD_Window_ID
    
    public void onEvent(Event event)
    {
        if  (event!=null)
        {
            if (event.getTarget().equals(confirmPanel.getButton("Ok")))
            {
                if (!contentPanel.getChildren().isEmpty() && contentPanel.getSelectedRowKey()!=null)
                {
                    dispose(true);
                }
            }
            else if (event.getTarget().equals(confirmPanel.getButton("Refresh")))
            {
                executeQuery();
                renderItems();
            }
            else if (event.getTarget().equals(confirmPanel.getButton("Cancel")))
            {
                dispose(false);
            }
            else if (event.getTarget().equals(confirmPanel.getButton("Zoom")))
            {
                if (!contentPanel.getChildren().isEmpty() && contentPanel.getSelectedRowKey()!=null)
                {
                    zoom();
                    this.detach();
                }
            }
        }
    }  //  onEvent
    
    public void zoom()
    {
          ValueChangeEvent event = new ValueChangeEvent(this,"zoom",
                   contentPanel.getSelectedRowKey(),contentPanel.getSelectedRowKey());
          fireValueChange(event);
    }
    
    public void addValueChangeListener(ValueChangeListener listener)
    {
        if (listener == null)
        {
            return;
        }
        
        listeners.add(listener);
    }
        
    public void fireValueChange(ValueChangeEvent event)
    {
        for (ValueChangeListener listener : listeners)
        {
           listener.valueChange(event);
        }
    }
    /**
     *  Dispose and save Selection
     *  @param ok OK pressed
     */
    public void dispose(boolean ok)
    {
        log.config("OK=" + ok);
        m_ok = ok;

        //  End Worker
        saveSelection();
        if (contentPanel != null)
        {
            Object data = getSelectedKey();
            ValueChangeEvent valuechange = new ValueChangeEvent
            (this, p_keyColumn , data, data);
        
            fireValueChange(valuechange);
        }        
        this.detach();
    }   //  dispose
        

}	//	Info

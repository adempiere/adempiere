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
 *****************************************************************************/
package org.compiere.apps.wf;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AMenu;
import org.compiere.apps.AWindow;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MColumn;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MSysConfig;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTextArea;
import org.compiere.swing.CTextField;
import org.compiere.swing.CTextPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.ValueNamePair;
import org.compiere.wf.MWFActivity;
import org.compiere.wf.MWFNode;

/**
 *	WorkFlow Activities Panel
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: WFActivity.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *
 * 	@author 	Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1748449 ]
 * 	@author 	victor.perez@e-evolution.com
 * 			<li> BF[2992649] Issue in Workflow Activities when the records are ordered
 * 			<li> https://sourceforge.net/tracker/?func=detail&aid=2992649&group_id=176962&atid=879332
 *  @author     Compiere - CarlosRuiz integrate code for table selection on workflow present at GPL version of Compiere 3.2.0
 */
public class WFActivity extends CPanel 
	implements FormPanel, ActionListener, ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6917300855914216420L;

	private static final int MAX_ACTIVITIES_IN_LIST = MSysConfig.getIntValue("MAX_ACTIVITIES_IN_LIST", 200, Env.getAD_Client_ID(Env.getCtx()));

	/**
	 * 	WF Activity
	 */
	public WFActivity()
	{
		super ();
		//	needs to call init
	}	//	WFActivity

	/**
	 * 	WF Activity
	 * @param menu AMenu
	 */
	public WFActivity (AMenu menu)
	{
		super ();
		log.config("");
		try 
		{
			dynInit(0);
			jbInit();
		}
		catch(Exception e) 
		{
			log.log(Level.SEVERE, "", e);
		}
		m_menu = menu;
	}	//	WFActivity


	/**	Window No					*/
	private int         		m_WindowNo = 0;
	/**	FormFrame					*/
	private FormFrame 			m_frame = null;
	/**	Menu						*/
	private AMenu 				m_menu = null;
	/**	Current Activity			*/
	private MWFActivity 		m_activity = null;
	/**	Set Column					*/
	private	MColumn 			m_column = null; 
	
	private int columnValue = -1;   
	private int columnNewValue = -1;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WFActivity.class);
	
	DefaultTableModel 	selTableModel = new DefaultTableModel(
			new String[]{Msg.translate(Env.getCtx(),"ID"),
				Msg.translate(Env.getCtx(), "Priority"),
				Msg.translate(Env.getCtx(), "AD_WF_Node_ID"),
				Msg.translate(Env.getCtx(), "Summary")}, 0); 
	private MiniTable	selTable = new MiniTable();
	private CScrollPane selPane = new CScrollPane(selTable);
	//
	private CPanel centerPanel = new CPanel();
	private GridBagLayout centerLayout = new GridBagLayout();
	private CLabel lNode = new CLabel(Msg.translate(Env.getCtx(), "AD_WF_Node_ID"));
	private CTextField fNode = new CTextField();
	private CLabel lDesctiption = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private CTextArea fDescription = new CTextArea();
	private CLabel lHelp = new CLabel(Msg.translate(Env.getCtx(), "Help"));
	private CTextArea fHelp = new CTextArea();
	private CLabel lHistory = new CLabel(Msg.translate(Env.getCtx(), "History"));
	private CTextPane fHistory = new CTextPane();
	private CLabel lAnswer = new CLabel(Msg.getMsg(Env.getCtx(), "Answer"));
	private CPanel answers = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private CTextField fAnswerText = new CTextField();
	private CComboBox fAnswerList = new CComboBox();
	private CButton fAnswerButton = new CButton();
	// private CButton bPrevious = AEnv.getButton("Previous");
	// private CButton bNext = AEnv.getButton("Next");
	private CButton bZoom = AEnv.getButton("Zoom");
	private CLabel lTextMsg = new CLabel(Msg.getMsg(Env.getCtx(), "Messages"));
	private CTextArea fTextMsg = new CTextArea();
	private CButton bOK = ConfirmPanel.createOKButton(true);
	private VLookup fForward = null;	//	dynInit
	private CLabel lForward = new CLabel(Msg.getMsg(Env.getCtx(), "Forward"));
	private CLabel lOptional = new CLabel("(" + Msg.translate(Env.getCtx(), "Optional") + ")");
	private StatusBar statusBar = new StatusBar(); 
	
	/**
	 * 	Dynamic Init.
	 * 	Called before Static Init
	 * 	@param WindowNo window
	 */
	private void dynInit(int WindowNo)
	{
		loadActivities();
		//	Forward
		fForward = VLookup.createUser(WindowNo);
	}	//	dynInit

	/**
	 * 	Static Init.
	 * 	Called after Dynamic Init
	 * 	@throws Exception
	 */
	private void jbInit () throws Exception
	{
		int width = 150;
		centerPanel.setLayout (centerLayout);
		fNode.setReadWrite (false);
		fDescription.setReadWrite (false);
		fDescription.setPreferredSize(new Dimension (width,40));
		fHelp.setReadWrite (false);
		fHelp.setPreferredSize(new Dimension (width,40));
		fHistory.setReadWrite (false);
		fHistory.setPreferredSize(new Dimension (width,80));
		fTextMsg.setPreferredSize(new Dimension (width,40));
		//
		// bPrevious.addActionListener(this);
		// bNext.addActionListener(this);
		selTable.setModel(selTableModel);
		selTable.setColumnClass(0,IDColumn.class,false, " ");  //  0-ID
		selTable.setColumnClass(1, Integer.class, true);       //  1-Priority
		selTable.setColumnClass(2, String.class, true);        //  2-AD_WF_Node_ID
		selTable.setColumnClass(3, String.class, true);        //  3-Summary
		selTable.getSelectionModel().addListSelectionListener(this);
		
		// Listen the Column Move Event
		selTable.getColumnModel().addColumnModelListener(new TableColumnModelListener()   
		{ 
		  
		    public void columnMoved(TableColumnModelEvent e)   
		    {   
		        if (columnValue == -1)   
		            columnValue = e.getFromIndex();   
		  
		        columnNewValue = e.getToIndex();   
		    }

		    @Override
		    public void columnAdded(TableColumnModelEvent e) {
		    }

		    @Override
		    public void columnMarginChanged(ChangeEvent e) {
		    }

		    @Override
		    public void columnRemoved(TableColumnModelEvent e) {	
		    }
		    @Override
		    public void columnSelectionChanged(ListSelectionEvent e) {
		    }   
		}); 
		
		//Listen the mouse released Moved
		selTable.getTableHeader().addMouseListener(new MouseAdapter()   
		{   
		    @Override   
		    public void mouseReleased(MouseEvent e)   
		    {   
		        if (columnValue != -1 && (columnValue == 0 || columnNewValue == 0))   
		        selTable.moveColumn(columnNewValue, columnValue);   
		  
		        columnValue = -1;   
		        columnNewValue = -1;   
		    }   
		});

		bZoom.addActionListener(this);
		bOK.addActionListener(this);
		//
		this.setLayout(new BorderLayout());
		this.add (centerPanel, BorderLayout.CENTER);
		this.add (statusBar, BorderLayout.SOUTH);
		//
	//	answers.setOpaque(false);
		answers.add(fAnswerText);
		answers.add(fAnswerList);
		answers.add(fAnswerButton);
		fAnswerButton.addActionListener(this);
		//
		int row = 0;
		selPane.setPreferredSize(new Dimension(width, 60));
		selPane.setMinimumSize(new Dimension(100, 60));
		centerPanel.add (selPane, new GridBagConstraints (0, row, 4, 1, 0.3, 0.3, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 
			new Insets (5, 10, 5, 10), 0, 0));
		
		centerPanel.add (lNode, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fNode, new GridBagConstraints (1, row, 3, 2, 0.5, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5,	0, 5, 10), 0, 0));
		
		centerPanel.add (lDesctiption, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fDescription, new GridBagConstraints (1, row, 3, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5, 0, 5, 10), 0, 0));
		
		centerPanel.add (lHelp, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (2, 10, 5, 5), 0, 0));
		centerPanel.add (fHelp, new GridBagConstraints (1, row, 3, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (2, 0, 5, 10), 0, 0));
		
		centerPanel.add (lHistory, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fHistory, new GridBagConstraints (1, row, 3, 1, 0.5, 0.5, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 
			new Insets (5,	0, 5, 10), 0, 0));
		
		centerPanel.add (lAnswer, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10, 10, 5, 5), 0, 0));
		centerPanel.add (answers, new GridBagConstraints (1, row, 2, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
			new Insets (10,	0, 5, 5), 0, 0));
		centerPanel.add (bZoom, new GridBagConstraints (3, row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10,	0, 10, 10), 0, 0));

		centerPanel.add (lTextMsg, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fTextMsg, new GridBagConstraints (1, row, 3, 1, 0.5, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5, 0, 5, 10), 0, 0));
		
		centerPanel.add (lForward, new GridBagConstraints (0, ++row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10, 10,	5, 5), 0, 0));
		centerPanel.add (fForward, new GridBagConstraints (1, row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.NONE, 
			new Insets (10, 0, 5, 0), 0, 0));
		centerPanel.add (lOptional, new GridBagConstraints (2, row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.NONE, 
			new Insets (10,	5, 5, 5), 0, 0));
		centerPanel.add (bOK, new GridBagConstraints (3, row, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10,	5, 5, 10), 0, 0));
	}	//	jbInit

	/**
	 *	Initialize Panel for FormPanel
	 *  @param WindowNo window
	 *  @param frame frame
	 *	@see org.compiere.apps.form.FormPanel#init(int, FormFrame)
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		//
		log.info("");
		try
		{
			dynInit(WindowNo);
			jbInit();
			//
		//	this.setPreferredSize(new Dimension (400,400));
			frame.getContentPane().add(this, BorderLayout.CENTER);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**
	 * 	Dispose
	 * 	@see org.compiere.apps.form.FormPanel#dispose()
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose
	
	/**
	 * Get active activities count
	 * @return int
	 */
	public int getActivitiesCount() 
	{
		int count = 0;
		
		String sql = "SELECT count(*) FROM AD_WF_Activity a "
			+ "WHERE a.Processed='N' AND a.WFState='OS' AND ("
			//	Owner of Activity
			+ " a.AD_User_ID=?"	//	#1
			//	Invoker (if no invoker = all)
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND COALESCE(r.AD_User_ID,0)=0 AND COALESCE(r.AD_Role_ID,0)=0 AND (a.AD_User_ID=? OR a.AD_User_ID IS NULL))"	//	#2
			// Responsible User
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND r.AD_User_ID=?)"		//	#3
			//	Responsible Role
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r INNER JOIN AD_User_Roles ur ON (r.AD_Role_ID=ur.AD_Role_ID)"
			+ " WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID AND ur.AD_User_ID=?))";	//	#4
			//
			//+ ") ORDER BY a.Priority DESC, Created";
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_User_ID);
			pstmt.setInt (2, AD_User_ID);
			pstmt.setInt (3, AD_User_ID);
			pstmt.setInt (4, AD_User_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return count;
			
	}
	
	/**
	 * 	Load Activities
	 * 	@return int
	 */
	public int loadActivities()
	{
		while (selTableModel.getRowCount() > 0)
			selTableModel.removeRow(0);	
		long start = System.currentTimeMillis();
		ArrayList<MWFActivity> list = new ArrayList<MWFActivity>();
		String sql = "SELECT * FROM AD_WF_Activity a "
			+ "WHERE a.Processed='N' AND a.WFState='OS' AND ("
			//	Owner of Activity
			+ " a.AD_User_ID=?"	//	#1
			//	Invoker (if no invoker = all)
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND COALESCE(r.AD_User_ID,0)=0 AND COALESCE(r.AD_Role_ID,0)=0 AND (a.AD_User_ID=? OR a.AD_User_ID IS NULL))"	//	#2
			// Responsible User
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND r.AD_User_ID=?)"		//	#3
			//	Responsible Role
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r INNER JOIN AD_User_Roles ur ON (r.AD_Role_ID=ur.AD_Role_ID)"
			+ " WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID AND ur.AD_User_ID=?)"	//	#4
			//
			+ ") ORDER BY a.Priority DESC, Created";
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_User_ID);
			pstmt.setInt (2, AD_User_ID);
			pstmt.setInt (3, AD_User_ID);
			pstmt.setInt (4, AD_User_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MWFActivity activity = new MWFActivity(Env.getCtx(), rs, null);

				Object[] rowData = new Object[4];
				rowData[0] = new IDColumn(activity.get_ID());
				rowData[1] = activity.getPriority();
				rowData[2] = activity.getNodeName();
				rowData[3] = activity.getSummary();
				
				selTableModel.addRow(rowData);
				if (list.size() > MAX_ACTIVITIES_IN_LIST)
				{
					log.warning("More than " + MAX_ACTIVITIES_IN_LIST + " Activities - ignored");
					break;
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		selTable.autoSize(false);
	

		log.fine("#" + selTable.getModel().getRowCount() 
			+ "(" + (System.currentTimeMillis()-start) + "ms)");
		return selTable.getModel().getRowCount(); 
	}	//	loadActivities
	
	/**
	 * 	Display.
	 * 	@param index index of table
	 * 	Fill Editors
	 */
	public void display(IDColumn id)
	{
		log.fine("ID=" + id);
		m_activity = resetDisplay(id);
		//
		if (m_menu != null)
		{
		 	m_menu.updateActivities(selTable.getModel().getRowCount());
		}
		if (m_activity == null)
			return;
		
		//	Display Activity
		fNode.setText (m_activity.getNodeName());
		fDescription.setText (m_activity.getNodeDescription());
		fHelp.setText (m_activity.getNodeHelp());
		//
		fHistory.setText (m_activity.getHistoryHTML());
		
		//	User Actions
		MWFNode node = m_activity.getNode();
		if (MWFNode.ACTION_UserChoice.equals(node.getAction()))
		{
			if (m_column == null)
				m_column = node.getColumn();
			if (m_column != null && m_column.get_ID() != 0)
			{
				int dt = m_column.getAD_Reference_ID();
				if (dt == DisplayType.YesNo)
				{
					ValueNamePair[] values = MRefList.getList(Env.getCtx(), 319, false);		//	_YesNo
					fAnswerList.setModel(new DefaultComboBoxModel(values));
					fAnswerList.setVisible(true);
				}
				else if (dt == DisplayType.List)
				{
					ValueNamePair[] values = MRefList.getList(Env.getCtx(), m_column.getAD_Reference_Value_ID(), false);
					fAnswerList.setModel(new DefaultComboBoxModel(values));
					fAnswerList.setVisible(true);
				}
				else	//	other display types come here
				{
					fAnswerText.setText ("");
					fAnswerText.setVisible(true);
				}
			}
		}
		//	--
		else if (MWFNode.ACTION_UserWindow.equals(node.getAction())
			|| MWFNode.ACTION_UserForm.equals(node.getAction()))
		{
			fAnswerButton.setText(node.getName());
			fAnswerButton.setToolTipText(node.getDescription());
			fAnswerButton.setVisible(true);
		}
		/*
		else if (MWFNode.ACTION_UserWorkbench.equals(node.getAction()))
			log.log(Level.SEVERE, "Workflow Action not implemented yet"); */
		else
			log.log(Level.SEVERE, "Unknown Node Action: " + node.getAction());

		statusBar.setStatusDB(String.valueOf(selTable.getSelectedRow()+1) + "/" + selTable.getRowCount());
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "WFActivities"));
	}	//	display

	/**
	 * 	Reset Display
	 *	@param selIndex select index
	 *	@return selected activity
	 */
	private MWFActivity resetDisplay(IDColumn id)
	{
		fAnswerText.setVisible(false);
		fAnswerList.setVisible(false);
		fAnswerButton.setVisible(false);
		fTextMsg.setReadWrite(id != null);
		bZoom.setEnabled(id != null);
		bOK.setEnabled(id != null);
		fForward.setValue(null);
		fForward.setEnabled(id != null);
		//
		statusBar.setStatusDB(String.valueOf(selTable.getSelectedRow()+1) + "/" + selTable.getRowCount());
		m_activity = null;
		m_column = null;

		m_activity = new MWFActivity(Env.getCtx(),id.getRecord_ID(),null);

		if (m_activity == null)
		{
			fNode.setText ("");
			fDescription.setText ("");
			fHelp.setText ("");
			fHistory.setText ("");
			statusBar.setStatusDB("0/0");
			statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "WFNoActivities"));
		}
		return m_activity;
	}	//	resetDisplay
	
	
	/**
	 * 	Selection Listener
	 * 	@param e event
	 */
    public void valueChanged(ListSelectionEvent e)
    {
    	if(selTable.getSelectedRow()>=0)
    	{
    		IDColumn id = (IDColumn)selTable.getValueAt(selTable.getSelectedRow(),0);
    		if (id != null)
    			display(id);
    	}
    }	//	valueChanged

	
	/**
	 * 	Action Listener
	 *	@param e event
	 * 	@see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed (ActionEvent e)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		if (e.getSource() == bZoom)
			cmd_zoom();
		else if (e.getSource() == bOK)
			cmd_OK();
		else if (e.getSource() == fAnswerButton)
			cmd_button();
		//
		this.setCursor(Cursor.getDefaultCursor());
	}	//	actionPerformed

	
	/**
	 * 	Zoom
	 */
	private void cmd_zoom()
	{
		log.config("Activity=" + m_activity);
		if (m_activity == null)
			return;
		AEnv.zoom(m_activity.getAD_Table_ID(), m_activity.getRecord_ID());
	}	//	cmd_zoom

	/**
	 * 	Answer Button
	 */
	private void cmd_button()
	{
		log.config("Activity=" + m_activity);
		if (m_activity == null)
			return;
		//
		MWFNode node = m_activity.getNode();
		if (MWFNode.ACTION_UserWindow.equals(node.getAction()))
		{
			int AD_Window_ID = node.getAD_Window_ID();		// Explicit Window
			String ColumnName = m_activity.getPO().get_TableName() + "_ID";
			int Record_ID = m_activity.getRecord_ID();
			MQuery query = MQuery.getEqualQuery(ColumnName, Record_ID);
			boolean IsSOTrx = m_activity.isSOTrx();
			//
			log.info("Zoom to AD_Window_ID=" + AD_Window_ID 
				+ " - " + query + " (IsSOTrx=" + IsSOTrx + ")");
			AWindow frame = new AWindow();
			if (!frame.initWindow(AD_Window_ID, query))
				return;
			AEnv.addToWindowManager(frame);
			AEnv.showCenterScreen(frame);
			frame = null;
		}
		else if (MWFNode.ACTION_UserForm.equals(node.getAction()))
		{
			int AD_Form_ID = node.getAD_Form_ID();
			FormFrame ff = new FormFrame();
			ff.openForm(AD_Form_ID);
			ff.pack();
			AEnv.addToWindowManager(ff);
			AEnv.showCenterScreen(ff);
		}
		/*
		else if (MWFNode.ACTION_UserWorkbench.equals(node.getAction()))
		{
			
		}*/
		else
			log.log(Level.SEVERE, "No User Action:" + node.getAction());
	}	//	cmd_button
	
	
	/**
	 * 	Save
	 */
	private void cmd_OK()
	{
		log.config("Activity=" + m_activity);
		if (m_activity == null)
			return;
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		String textMsg = fTextMsg.getText();
		//
		MWFNode node = m_activity.getNode();
		
		Object forward = fForward.getValue();

		// ensure activity is ran within a transaction - [ 1953628 ]
		Trx trx = Trx.get(Trx.createTrxName("FWFA"), true);
		m_activity.set_TrxName(trx.getTrxName());
		
		if (forward != null)
		{
			log.config("Forward to " + forward);
			int fw = ((Integer)forward).intValue();
			if (fw == AD_User_ID || fw == 0)
			{
				log.log(Level.SEVERE, "Forward User=" + fw);
				trx.rollback();
				trx.close();
				return;
			}
			if (!m_activity.forwardTo(fw, textMsg))
			{
				ADialog.error(m_WindowNo, this, "CannotForward");
				trx.rollback();
				trx.close();
				return;
			}
		}
		//	User Choice - Answer
		else if (MWFNode.ACTION_UserChoice.equals(node.getAction()))
		{
			if (m_column == null)
				m_column = node.getColumn();
			//	Do we have an answer?
			int dt = m_column.getAD_Reference_ID();
			String value = fAnswerText.getText();
			if (dt == DisplayType.YesNo || dt == DisplayType.List)
			{
				ValueNamePair pp = (ValueNamePair)fAnswerList.getSelectedItem();
				value = pp.getValue();
			}
			if (value == null || value.length() == 0)
			{
				ADialog.error(m_WindowNo, this, "FillMandatory", Msg.getMsg(Env.getCtx(), "Answer"));
				trx.rollback();
				trx.close();
				return;
			}
			//
			log.config("Answer=" + value + " - " + textMsg);
			try
			{
				m_activity.setUserChoice(AD_User_ID, value, dt, textMsg);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, node.getName(), e);
				ADialog.error(m_WindowNo, this, "Error", e.toString());
				trx.rollback();
				trx.close();
				return;
			}
		}
		//	User Action
		else
		{
			log.config("Action=" + node.getAction() + " - " + textMsg);
			try
			{
				// ensure activity is ran within a transaction
				m_activity.setUserConfirmation(AD_User_ID, textMsg);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, node.getName(), e);
				ADialog.error(m_WindowNo, this, "Error", e.toString());
				trx.rollback();
				trx.close();
				return;
			}
			
		}
		
		trx.commit();
		trx.close();

		//	Next
		loadActivities();
	}	//	cmd_OK
	
	
}	//	WFActivity

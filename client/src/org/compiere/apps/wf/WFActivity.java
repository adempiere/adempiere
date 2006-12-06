/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.apps.*;
import org.compiere.apps.form.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;
import org.compiere.wf.*;

/**
 *	WorkFlow Activities Panel
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: WFActivity.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class WFActivity extends CPanel 
	implements FormPanel, ActionListener
{
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
	/**	Open Activities				*/
	private MWFActivity[] 		m_activities = null;
	/**	Current Activity			*/
	private MWFActivity 		m_activity = null;
	/**	Current Activity			*/
	private int	 				m_index = 0;
	/**	Set Column					*/
	private	MColumn 			m_column = null; 
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WFActivity.class);
	
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
	private CButton bPrevious = AEnv.getButton("Previous");
	private CButton bNext = AEnv.getButton("Next");
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
		centerPanel.setLayout (centerLayout);
		fNode.setReadWrite (false);
		fDescription.setReadWrite (false);
		fDescription.setPreferredSize(new Dimension (130,40));
		fHelp.setReadWrite (false);
		fHelp.setPreferredSize(new Dimension (150,80));
		fHistory.setReadWrite (false);
		fHistory.setPreferredSize(new Dimension (150,60));
		fTextMsg.setPreferredSize(new Dimension (150,40));
		//
		bPrevious.addActionListener(this);
		bNext.addActionListener(this);
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
		centerPanel.add (lNode, new GridBagConstraints (0, 0, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fNode, new GridBagConstraints (1, 0, 2, 1, 0.5, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5,	0, 5, 5), 0, 0));
		centerPanel.add (bPrevious, new GridBagConstraints (3, 0, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 5, 5, 10), 0, 0));
		
		centerPanel.add (lDesctiption, new GridBagConstraints (0, 1, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fDescription, new GridBagConstraints (1, 1, 2, 1, 0.0,	0.1, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5, 0, 5, 5), 0, 0));
		centerPanel.add (bNext, new GridBagConstraints (3, 1, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 5, 5, 10), 0, 0));
		
		centerPanel.add (lHelp, new GridBagConstraints (0, 2, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fHelp, new GridBagConstraints (1, 2, 3, 1, 0.0, 0.1, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5, 0, 5, 10), 0, 0));
		
		centerPanel.add (lHistory, new GridBagConstraints (0, 3, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fHistory, new GridBagConstraints (1, 3, 3, 1, 0.5, 0.5, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, 
			new Insets (5,	0, 5, 10), 0, 0));
		
		centerPanel.add (lAnswer, new GridBagConstraints (0, 4, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10, 10, 5, 5), 0, 0));
		centerPanel.add (answers, new GridBagConstraints (1, 4, 2, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
			new Insets (10,	0, 5, 5), 0, 0));
		centerPanel.add (bZoom, new GridBagConstraints (3, 4, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10,	0, 10, 10), 0, 0));

		centerPanel.add (lTextMsg, new GridBagConstraints (0, 5, 1, 1, 0.0, 0.0, 
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
			new Insets (5, 10, 5, 5), 0, 0));
		centerPanel.add (fTextMsg, new GridBagConstraints (1, 5, 3, 1, 0.5, 0.0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 
			new Insets (5, 0, 5, 10), 0, 0));
		
		centerPanel.add (lForward, new GridBagConstraints (0, 6, 1, 1, 0.0, 0.0, 
			GridBagConstraints.EAST, GridBagConstraints.NONE, 
			new Insets (10, 10,	5, 5), 0, 0));
		centerPanel.add (fForward, new GridBagConstraints (1, 6, 1, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.NONE, 
			new Insets (10, 0, 5, 0), 0, 0));
		centerPanel.add (lOptional, new GridBagConstraints (2, 6, 1, 1, 0.0, 0.0, 
			GridBagConstraints.WEST, GridBagConstraints.NONE, 
			new Insets (10,	5, 5, 5), 0, 0));
		centerPanel.add (bOK, new GridBagConstraints (3, 6, 1, 1, 0.0, 0.0, 
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
			display();
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
	 * 	Load Activities
	 * 	@return int
	 */
	public int loadActivities()
	{
		long start = System.currentTimeMillis();
		ArrayList<MWFActivity> list = new ArrayList<MWFActivity>();
		String sql = "SELECT * FROM AD_WF_Activity a "
			+ "WHERE a.Processed='N' AND a.WFState='OS' AND ("
			//	Owner of Activity
			+ " a.AD_User_ID=?"	//	#1
			//	Invoker (if no invoker = all)
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND COALESCE(r.AD_User_ID,0)=0 AND (a.AD_User_ID=? OR a.AD_User_ID IS NULL))"	//	#2
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
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_User_ID);
			pstmt.setInt (2, AD_User_ID);
			pstmt.setInt (3, AD_User_ID);
			pstmt.setInt (4, AD_User_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				list.add (new MWFActivity(Env.getCtx(), rs, null));
				if (list.size() > 200)		//	HARDCODED
				{
					log.warning("More then 200 Activities - ignored");
					break;
				}
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_activities = new MWFActivity[list.size ()];
		list.toArray (m_activities);
		//
		log.fine("#" + m_activities.length 
			+ "(" + (System.currentTimeMillis()-start) + "ms)");
		m_index = 0;
		return m_activities.length;
	}	//	loadActivities
	
	/**
	 * 	Display.
	 * 	Fill Editors
	 */
	public void display ()
	{
		log.fine("Index=" + m_index);
		//
		fTextMsg.setText("");
		fAnswerText.setVisible(false);
		fAnswerList.setVisible(false);
		fAnswerButton.setIcon(Env.getImageIcon("mWindow.gif"));
		fAnswerButton.setVisible(false);
		fTextMsg.setReadWrite(m_activities.length != 0);
		bZoom.setEnabled(m_activities.length != 0);
		bOK.setEnabled(m_activities.length != 0);
		fForward.setValue(null);
		fForward.setEnabled(m_activities.length != 0);
		statusBar.setStatusDB(String.valueOf(m_index) + "/" + m_activities.length);
		m_activity = null;
		if (m_activities.length > 0)
		{
			if (m_index+1 > m_activities.length)
			{
				log.log(Level.SEVERE, "Index (" + m_index 
					+ ") greater then activity length=" + m_activities.length);
				m_index = 0;
			}
			else
				m_activity = m_activities[m_index];
		}
		//	Nothing to show
		if (m_activity == null)
		{
			fNode.setText ("");
			fDescription.setText ("");
			fHelp.setText ("");
			fHistory.setText ("");
			statusBar.setStatusDB("0/0");
			statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "WFNoActivities"));
			bNext.setEnabled(false);
			bPrevious.setEnabled(false);
			return;
		}
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
					ValueNamePair[] values = MRefList.getList(319, false);		//	_YesNo
					fAnswerList.setModel(new DefaultComboBoxModel(values));
					fAnswerList.setVisible(true);
				}
				else if (dt == DisplayType.List)
				{
					ValueNamePair[] values = MRefList.getList(m_column.getAD_Reference_Value_ID(), false);
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
		else if (MWFNode.ACTION_UserWorkbench.equals(node.getAction()))
			log.log(Level.SEVERE, "Workflow Action not implemented yet");
		else
			log.log(Level.SEVERE, "Unknown Node Action: " + node.getAction());
		//
		if (m_menu != null)
		{
			m_menu.updateInfo();	//	calls loadActivities - updates menu tab
		}
		//	End
		if (m_index+1 >= m_activities.length)
		{
			m_index = m_activities.length - 1;
			bNext.setEnabled(false);
		}
		else
			bNext.setEnabled(true);
		//	Start
		if (m_index <= 0)
		{
			m_index = 0;
			bPrevious.setEnabled(false);
		}
		else
			bPrevious.setEnabled(true);
		statusBar.setStatusDB((m_index+1) + "/" + m_activities.length);
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "WFActivities"));
	}	//	display

	
	/**
	 * 	Action Listener
	 *	@param e event
	 * 	@see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed (ActionEvent e)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		if (e.getSource() == bNext || e.getSource() == bPrevious)
		{
			if (e.getSource() == bNext)
				m_index++;
			else
				m_index--;
			display();
		}
		else if (e.getSource() == bZoom)
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
			AEnv.showCenterScreen(ff);
		}
		else if (MWFNode.ACTION_UserWorkbench.equals(node.getAction()))
		{
			
		}
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
		if (forward != null)
		{
			log.config("Forward to " + forward);
			int fw = ((Integer)forward).intValue();
			if (fw == AD_User_ID || fw == 0)
			{
				log.log(Level.SEVERE, "Forward User=" + fw);
				return;
			}
			if (!m_activity.forwardTo(fw, textMsg))
			{
				ADialog.error(m_WindowNo, this, "CannotForward");
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
				return;
			}
		}
		//	User Action
		else
		{
			log.config("Action=" + node.getAction() + " - " + textMsg);
			try
			{
				m_activity.setUserConfirmation(AD_User_ID, textMsg);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, node.getName(), e);
				ADialog.error(m_WindowNo, this, "Error", e.toString());
				return;
			}
			
		}
		//	Next
		loadActivities();
		display();
	}	//	cmd_OK
	
	
}	//	WFActivity

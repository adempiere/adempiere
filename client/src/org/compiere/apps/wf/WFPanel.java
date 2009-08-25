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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.AMenu;
import org.compiere.apps.AMenuStartItem;
import org.compiere.apps.AWindow;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;

/**
 *	WorkFlow Panel
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: WFPanel.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 				<li>FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
 * 				<li>BF [ 2844102 ] Workfow Editor is displaying manufacturing routings too
 * 					https://sourceforge.net/tracker/?func=detail&aid=2844102&group_id=176962&atid=879332
 */
public class WFPanel extends CPanel
	implements PropertyChangeListener, ActionListener, FormPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4478193785606693055L;
	
	/** Workflow WhereClause : General, Document Process, Document Value */
	private static final String WORKFLOW_WhereClause = "WorkflowType IN ("
		+DB.TO_STRING(MWorkflow.WORKFLOWTYPE_General)
		+","+DB.TO_STRING(MWorkflow.WORKFLOWTYPE_DocumentProcess)
		+","+DB.TO_STRING(MWorkflow.WORKFLOWTYPE_DocumentValue)
		+")";


	/**
	 * 	Create Workflow Panel.
	 * 	FormPanel
	 */
	public WFPanel()
	{
		this (null, WORKFLOW_WhereClause, -1);
	}	//	WFPanel

	/**
	 * 	Create Workflow Panel
	 * 	@param menu menu
	 */
	public WFPanel (AMenu menu)
	{
		this(menu, WORKFLOW_WhereClause, -1);
	}

	/**
	 * 	Create Workflow Panel
	 * 	@param menu menu
	 */
	public WFPanel (AMenu menu, String wfWhereClause, int wfWindow_ID)
	{
		m_menu = menu;
		m_readWrite = (menu == null);
		m_WF_whereClause = wfWhereClause;
		m_WF_Window_ID = wfWindow_ID;
		log.info("RW=" + m_readWrite);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "WFPanel", e);
		}
	}	//	WFPanel

	/**	Menu Link					*/
	private AMenu		m_menu = null;
	/**	Window No			*/
	private int         m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 	m_frame;


	/**	Workflow Model				*/
	private MWorkflow	m_wf = null;
	/**	Context						*/
	private Properties	m_ctx = Env.getCtx();
	/** Active Node					*/
	private WFNode 		m_activeNode = null;
	/**	ReadWrite Mode (see WFNode)	*/
	private boolean		m_readWrite = false;
	
	/** Workflows List Where Clause	*/
	private String		m_WF_whereClause = null;
	/** Workflow Window ID			*/
	private int			m_WF_Window_ID = -1;

	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(WFPanel.class);
	
	//	UI
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel southPanel = new CPanel();
	private WFContentPanel centerPanel = new WFContentPanel(this);
	private BorderLayout southLayout = new BorderLayout();
	private JScrollPane infoScrollPane = new JScrollPane();
	private JTextPane infoTextPane = new JTextPane();
	private CPanel buttonPanel = new CPanel();
	private JButton wfStart = new JButton();
	private JButton wfBack = new JButton();
	private JButton wfNext = new JButton();
	private JButton wfEnd = new JButton();
	//
	private CPanel loadPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private CComboBox workflow = new CComboBox();
	private CButton bResetLayout = AEnv.getButton("Reset");
	private CButton bSaveLayout = AEnv.getButton ("Save");
	private CButton bZoom = AEnv.getButton("Zoom");
	private CButton bIgnore = AEnv.getButton("Ignore");
	
	
	/**
	 * 	Static Init
	 *  <pre>
	 * 		centerScrollPane
	 * 			centerPanel
	 * 		south Panel
	 * 			infoScrollPane
	 * 			buttonPanel
	 * 	</pre>
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setLayout(mainLayout);
		CompiereColor.setBackground(this);
		southPanel.setLayout(southLayout);
		//	Center
		this.add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		//	Info
		infoScrollPane.getViewport().add(infoTextPane, null);
		infoScrollPane.setPreferredSize(new Dimension(200, 140));
		infoTextPane.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		infoTextPane.setEditable(false);
		infoTextPane.setRequestFocusEnabled(false);
		infoTextPane.setContentType("text/html");
		//	South
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(infoScrollPane,  BorderLayout.CENTER);
		southPanel.add(buttonPanel,  BorderLayout.SOUTH);
		//
		wfStart.setIcon(Env.getImageIcon("wfStart24.gif"));
		wfStart.setMargin(new Insets(0, 10, 0, 10));
		wfStart.setRequestFocusEnabled(false);
		wfStart.addActionListener(this);
		wfStart.setToolTipText(Msg.getMsg(m_ctx, "WFStart"));
		wfBack.setIcon(Env.getImageIcon("wfBack24.gif"));
		wfBack.setMargin(new Insets(0, 15, 0, 15));
		wfBack.setRequestFocusEnabled(false);
		wfBack.addActionListener(this);
		wfBack.setToolTipText(Msg.getMsg(m_ctx, "WFPrevious"));
		wfNext.setIcon(Env.getImageIcon("wfNext24.gif"));
		wfNext.setMargin(new Insets(0, 15, 0, 15));
		wfNext.setRequestFocusEnabled(false);
		wfNext.addActionListener(this);
		wfNext.setToolTipText(Msg.getMsg(m_ctx, "WFNext"));
		wfEnd.setIcon(Env.getImageIcon("wfEnd24.gif"));
		wfEnd.setMargin(new Insets(0, 10, 0, 10));
		wfEnd.setRequestFocusEnabled(false);
		wfEnd.addActionListener(this);
		wfEnd.setToolTipText(Msg.getMsg(m_ctx, "WFExit"));
		buttonPanel.add(wfStart, null);
		buttonPanel.add(wfBack, null);
		buttonPanel.add(wfNext, null);
		buttonPanel.add(wfEnd, null);
	}	//	jbInit

	/**
	 *	Initialize Panel for FormPanel
	 *  @param WindowNo window
	 *  @param frame frame
	 * @see org.compiere.apps.form.FormPanel#init(int, FormFrame)
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		//
		log.fine("WindowNo=" + WindowNo);
		try
		{
			loadPanel();
			frame.getContentPane().add(loadPanel, BorderLayout.NORTH);
			this.setPreferredSize(new Dimension (500,500));
			frame.getContentPane().add(this, BorderLayout.CENTER);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
	}	//	init

	/**
	 * 	Dispose
	 * @see org.compiere.apps.form.FormPanel#dispose()
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 * 	Workflow Add & Load Panel
	 */
	private void loadPanel()
	{
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT AD_Workflow_ID, Name FROM AD_Workflow "
				+(!Util.isEmpty(m_WF_whereClause, true) ? " WHERE "+m_WF_whereClause : "")
				+" ORDER BY 2",
			"AD_Workflow", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
		KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		//
		workflow = new CComboBox(pp);
		AutoCompletion.enable(workflow);
		loadPanel.add(workflow);
		workflow.addActionListener(this);
		//
		loadPanel.add(bIgnore);
		bIgnore.addActionListener(this);
		loadPanel.add(bResetLayout);
		bResetLayout.addActionListener(this);
		loadPanel.add(bSaveLayout);
		bSaveLayout.addActionListener(this);
		loadPanel.add(bZoom);
		bZoom.addActionListener(this);
	}	//	loadPanel
	
	
	/**************************************************************************
	 * 	Load Workflow & Nodes
	 * 	@param readWrite if true, you can move nodes
	 */
	private void load (boolean readWrite)
	{
		KeyNamePair pp = (KeyNamePair)workflow.getSelectedItem();
		if (pp == null)
			return;
		load (pp.getKey(), readWrite);
	}	//	load
	
	/**
	 * 	Load Workflow & Nodes
	 * 	@param AD_Workflow_ID ID
	 * 	@param readWrite if true nodes can be moved
	 */
	public void load (int AD_Workflow_ID, boolean readWrite)
	{
		log.fine("RW=" + readWrite + " - AD_Workflow_ID=" + AD_Workflow_ID);
		if (AD_Workflow_ID == 0)
			return;
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		//	Get Workflow
		m_wf = new MWorkflow (Env.getCtx(), AD_Workflow_ID, null);
		centerPanel.removeAll();
		centerPanel.setReadWrite(readWrite);
		if (readWrite)
			centerPanel.setWorkflow(m_wf);
		//	Add Nodes for Paint
		MWFNode[] nodes = m_wf.getNodes(true, AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			WFNode wfn = new WFNode (nodes[i]);
			wfn.addPropertyChangeListener(WFNode.PROPERTY_SELECTED, this);
			boolean rw = readWrite 		//	in editor mode & owned
				&& (AD_Client_ID == nodes[i].getAD_Client_ID());
			centerPanel.add (wfn, rw);
			//	Add Lines
			MWFNodeNext[] nexts = nodes[i].getTransitions(AD_Client_ID);
			for (int j = 0; j < nexts.length; j++)
				centerPanel.add (new WFLine (nexts[j]), false);
		}
		//	Info Text
		StringBuffer msg = new StringBuffer("<HTML>");
		msg.append("<H2>").append(m_wf.getName(true)).append("</H2>");
		String s = m_wf.getDescription(true);
		if (s != null && s.length() > 0)
			msg.append("<B>").append(s).append("</B>");
		s = m_wf.getHelp(true);
		if (s != null && s.length() > 0)
			msg.append("<BR>").append(s);
		msg.append("</HTML>");
		infoTextPane.setText (msg.toString());
		infoTextPane.setCaretPosition(0);

		//	Layout
		centerPanel.validate();
		centerPanel.repaint();
		validate();
	}	//	load

	/**
	 * 	Property Change Listener
	 * 	@param e event
	 * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	public void propertyChange (PropertyChangeEvent e)
	{
		if (e.getNewValue() == Boolean.TRUE)
			start ((WFNode)e.getSource());
	}	//	propertyChange

	/**
	 * 	Action Listener
	 * 	@param e event
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (m_wf == null && e.getSource() != workflow)
			return;

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		//	Editing
		if (e.getSource() == bZoom)
			zoom();
		else if (e.getSource() == bIgnore)
			load(m_wf.getAD_Workflow_ID(), true);
		else if (e.getSource() == workflow)
			load(true);
		else if (e.getSource() == bSaveLayout)
		{
			if (m_wf.getAD_Client_ID() == AD_Client_ID)
				m_wf.save();
			MWFNode[] nodes = m_wf.getNodes(false, AD_Client_ID);
			for (int i = 0; i < nodes.length; i++)
			{
				if (nodes[i].getAD_Client_ID() == AD_Client_ID)
					nodes[i].save();
			}
		}
		else if (e.getSource() == bResetLayout)
			resetLayout();
		//	Buttons
		else if (e.getSource() == wfStart || m_activeNode == null)
			start (m_wf.getAD_WF_Node_ID());
		else if (e.getSource() == wfBack)
			start (m_wf.getPrevious (m_activeNode.getAD_WF_Node_ID(), AD_Client_ID));
		else if (e.getSource() == wfNext)
			start (m_wf.getNext (m_activeNode.getAD_WF_Node_ID(), AD_Client_ID));
		else if (e.getSource() == wfEnd)
			start (m_wf.getLast (m_activeNode.getAD_WF_Node_ID(), AD_Client_ID));
		//
		setCursor(Cursor.getDefaultCursor());
	}	//	actionPerformed


	/**************************************************************************
	 * 	Start Node
	 * 	@param node node
	 */
	public void start (WFNode node)
	{
		log.fine("Node=" + node);
		MWFNode model = node.getModel();
		//	Info Text
		StringBuffer msg = new StringBuffer("<HTML>");
		msg.append("<H2>").append(model.getName(true)).append("</H2>");
		String s = model.getDescription(true);
		if (s != null && s.length() > 0)
			msg.append("<B>").append(s).append("</B>");
		s = model.getHelp(true);
		if (s != null && s.length() > 0)
			msg.append("<BR>").append(s);
		msg.append("</HTML>");
		infoTextPane.setText (msg.toString());
		infoTextPane.setCaretPosition(0);

		//	Load Window
		if (m_menu != null)
			(new AMenuStartItem(model.getAD_WF_Node_ID(), false, model.getName(true), m_menu)).start();		//	async load
		//
		m_activeNode = node;
		//
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		boolean first = m_wf.isFirst(m_activeNode.getAD_WF_Node_ID(), AD_Client_ID);
		boolean last = m_wf.isLast(m_activeNode.getAD_WF_Node_ID(), AD_Client_ID);
		wfStart.setEnabled(!first);
		wfBack.setEnabled(!first);
		wfNext.setEnabled(!last);
		wfEnd.setEnabled(!last);
	}	//	start

	/**
	 * 	Start Node
	 * 	@param AD_WF_Node_ID node id
	 */
	public void start (int AD_WF_Node_ID)
	{
		if (AD_WF_Node_ID == 0)
			return;
		//
		for (int i = 0; i < centerPanel.getComponentCount(); i++)
		{
			Component comp = centerPanel.getComponent(i);
			if (comp instanceof WFNode)
			{
				WFNode node = (WFNode)comp;
				if (node.getAD_WF_Node_ID() == AD_WF_Node_ID)
				{
					start (node);
					return;
				}
			}	//	WFNode
		}	//	for all components
	}	//	start

	/**
	 * 	Reset Layout
	 */
	public void resetLayout()
	{
		Point p0 = new Point (0,0);
		for (int i = 0; i < centerPanel.getComponentCount(); i++)
		{
			Component comp = centerPanel.getComponent(i);
			comp.setLocation(p0);
		}
		centerPanel.validate();
	}	//	resetLayout

	/**
	 * 	Zoom to WorkFlow
	 */
	private void zoom()
	{
		if (m_WF_Window_ID <= 0) {
			m_WF_Window_ID = MTable.get(m_ctx, MWorkflow.Table_ID).getAD_Window_ID();
		}
		if (m_WF_Window_ID <= 0) {
			throw new AdempiereException("@NotFound@ @AD_Window_ID@");
		}
			
		MQuery query = null;
		if (m_wf != null)
			query = MQuery.getEqualQuery("AD_Workflow_ID", m_wf.getAD_Workflow_ID());
		AWindow frame = new AWindow();
		if (!frame.initWindow (m_WF_Window_ID, query))
			return;
		AEnv.addToWindowManager(frame);
		AEnv.showCenterScreen(frame);
		frame = null;
	}	//	zoom

	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("WFPanel[");
		if (m_wf != null)
			sb.append(m_wf.getAD_Workflow_ID());
		sb.append("]");
		return sb.toString();
	}	//	toString

	/**************************************************************************
	 * 	Test
	 * 	@param args ignored
	 */
	public static void main (String[] args)
	{
		org.compiere.Adempiere.startupEnvironment(true);
		JFrame jf = new JFrame ("WF");
		WFPanel pp = new WFPanel(null);
		pp.load(101, true);
		jf.getContentPane().add (pp);
		jf.pack();
		jf.setVisible(true);
	}	//	main


}	//	WFPanel

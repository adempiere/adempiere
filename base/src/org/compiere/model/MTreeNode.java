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
package org.compiere.model;

import java.awt.Color;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;

/**
 *  Mutable Tree Node (not a PO).
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MTreeNode.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public final class MTreeNode extends DefaultMutableTreeNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6046137020835035816L;


	/**
	 *  Construct Model TreeNode
	 *  @param node_ID	node
	 *  @param seqNo sequence
	 *  @param name name
	 *  @param description description
	 *  @param parent_ID parent
	 *  @param isSummary summary
	 *  @param imageIndicator image indicator
	 *  @param onBar on bar
	 *  @param color color
	 */
	public MTreeNode (int node_ID, int seqNo, String name, String description,
		int parent_ID, boolean isSummary, String imageIndicator, boolean onBar, Color color)
	{
		super();
	//	log.fine( "MTreeNode Node_ID=" + node_ID + ", Parent_ID=" + parent_ID + " - " + name);
		m_node_ID = node_ID;
		m_seqNo = seqNo;
		m_name = name;
		m_description = description;
		if (m_description == null)
			m_description = "";
		m_parent_ID = parent_ID;
		setSummary(isSummary);
		setImageIndicator(imageIndicator);
		m_onBar = onBar;
		m_color = color;
	}   //  MTreeNode

	/** Node ID         */
	private int     	m_node_ID;
	/**	SeqNo			*/
	private int     	m_seqNo;
	/** Name			*/
	private String  	m_name;
	/** Description		*/
	private String  	m_description;
	/**	Parent ID		*/
	private int     	m_parent_ID;
	/**	Summaty			*/
	private boolean 	m_isSummary;
	/** Image Indicator				*/
	private String      m_imageIndicator;
	/** Index to Icon               */
	private int 		m_imageIndex = 0;
	/**	On Bar			*/
	private boolean 	m_onBar;
	/**	Color			*/
	private Color 		m_color;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MTreeNode.class);
	
	/*************************************************************************/

	/**	Window - 1			*/
	public static int		TYPE_WINDOW = 1;
	/**	Report - 2			*/
	public static int		TYPE_REPORT = 2;
	/**	Process - 3			*/
	public static int		TYPE_PROCESS = 3;
	/**	Workflow - 4		*/
	public static int		TYPE_WORKFLOW = 4;
	/**	Workbench - 5		*/
	public static int		TYPE_WORKBENCH = 5;
	/**	Variable - 6		*/
	public static int		TYPE_SETVARIABLE = 6;
	/**	Choice - 7			*/
	public static int		TYPE_USERCHOICE = 7;
	/**	Action - 8			*/
	public static int		TYPE_DOCACTION = 8;

	/** 16* 16 Icons		*/
	public static Icon[] 	IMAGES = new Icon[]
	{
		null,
		Env.getImageIcon("mWindow.gif"),
		Env.getImageIcon("mReport.gif"),
		Env.getImageIcon("mProcess.gif"),
		Env.getImageIcon("mWorkFlow.gif"),
		Env.getImageIcon("mWorkbench.gif"),
		Env.getImageIcon("mSetVariable.gif"),
		Env.getImageIcon("mUserChoice.gif"),
		Env.getImageIcon("mDocAction.gif")
	};


	/**************************************************************************
	 *  Get Node ID
	 *  @return node id (e.g. AD_Menu_ID)
	 */
	public int getNode_ID()
	{
		return m_node_ID;
	}   //  getID

	/**
	 *  Set Name
	 *  @param name name
	 */
	public void setName (String name)
	{
		if (name == null)
			m_name = "";
		else
			m_name = name;
	}   //  setName

	/**
	 *  Get Name
	 *  @return name
	 */
	public String getName()
	{
		return m_name;
	}   //  setName

	/**
	 *	Get SeqNo (Index) as formatted String 0000 for sorting
	 *  @return SeqNo as String
	 */
	public String getSeqNo()
	{
		String retValue = "0000" + m_seqNo;	//	not more than 100,000 nodes
		if (m_seqNo > 99999)
			log.log(Level.SEVERE, "MTreeNode.getIndex - TreeNode Index is higher than 99999");
		if (retValue.length() > 5)
			retValue = retValue.substring(retValue.length()-5);	//	last 5
		return retValue;
	}	//	getSeqNo

	/**
	 *	Return parent
	 *  @return Parent_ID (e.g. AD_Menu_ID)
	 */
	public int getParent_ID()
	{
		return m_parent_ID;
	}	//	getParent

	/**
	 *  Print Name
	 *  @return info
	 */
	public String toString()
	{
		return //   m_node_ID + "/" + m_parent_ID + " " + m_seqNo + " - " +
			m_name;
	}   //  toString

	/**
	 *	Get Description
	 *  @return description
	 */
	public String getDescription()
	{
		return m_description;
	}	//	getDescription

	
	/**************************************************************************
	 *  Set Summary (allow children)
	 *  @param isSummary summary node
	 */
	public void setSummary (boolean isSummary)
	{
		m_isSummary = isSummary;
		super.setAllowsChildren(isSummary);
	}   //  setSummary

	/**
	 *  Set Summary (allow children)
	 *  @param isSummary true if summary
	 */
	public void setAllowsChildren (boolean isSummary)
	{
		super.setAllowsChildren (isSummary);
		m_isSummary = isSummary;
	}   //  setAllowsChildren

	/**
	 *  Allow children to be added to this node
	 *  @return true if summary node
	 */
	public boolean isSummary()
	{
		return m_isSummary;
	}   //  isSummary


	/**************************************************************************
	 *  Get Image Indicator/Index
	 *  @param imageIndicator image indicator (W/X/R/P/F/T/B) MWFNode.ACTION_
	 *  @return index of image
	 */
	public static int getImageIndex (String imageIndicator)
	{
		int imageIndex = 0;
		if (imageIndicator == null)
			;
		else if (imageIndicator.equals(MWFNode.ACTION_UserWindow)		//	Window 
			|| imageIndicator.equals(MWFNode.ACTION_UserForm))
			imageIndex = TYPE_WINDOW;
		else if (imageIndicator.equals(MWFNode.ACTION_AppsReport))		//	Report
			imageIndex = TYPE_REPORT;
		else if (imageIndicator.equals(MWFNode.ACTION_AppsProcess)		//	Process
			|| imageIndicator.equals(MWFNode.ACTION_AppsTask))
			imageIndex = TYPE_PROCESS;
		else if (imageIndicator.equals(MWFNode.ACTION_SubWorkflow))		//	WorkFlow
			imageIndex = TYPE_WORKFLOW;
		/*
		else if (imageIndicator.equals(MWFNode.ACTION_UserWorkbench))	//	Workbench
			imageIndex = TYPE_WORKBENCH;
		*/
		else if (imageIndicator.equals(MWFNode.ACTION_SetVariable))		//	Set Variable
			imageIndex = TYPE_SETVARIABLE;
		else if (imageIndicator.equals(MWFNode.ACTION_UserChoice))		//	User Choice
			imageIndex = TYPE_USERCHOICE;
		else if (imageIndicator.equals(MWFNode.ACTION_DocumentAction))	//	Document Action
			imageIndex = TYPE_DOCACTION;
		else if (imageIndicator.equals(MWFNode.ACTION_WaitSleep))		//	Sleep
			;
		return imageIndex;
	}   //  getImageIndex

	/**
	 *  Set Image Indicator and Index
	 *  @param imageIndicator image indicator (W/X/R/P/F/T/B) MWFNode.ACTION_
	 */
	public void setImageIndicator (String imageIndicator)
	{
		if (imageIndicator != null)
		{
			m_imageIndicator = imageIndicator;
			m_imageIndex = getImageIndex(m_imageIndicator);
		}
	}   //  setImageIndicator

	/**
	 *  Get Image Indicator
	 *  @return image indicator
	 */
	public String getImageIndiactor()
	{
		return m_imageIndicator;
	}   //  getImageIndiactor

	/**
	 *	Get Image Icon
	 *  @param index image index
	 *  @return Icon
	 */
	public static Icon getIcon (int index)
	{
		if (index == 0 || IMAGES == null || index > IMAGES.length)
			return null;
		return IMAGES[index];
	}	//	getIcon

	/**
	 *	Get Image Icon
	 *  @return Icon
	 */
	public Icon getIcon()
	{
		return getIcon(m_imageIndex);
	}	//	getIcon

	/**
	 *  Get Shortcut Bar info
	 *  @return true if node on bar
	 */
	public boolean isOnBar()
	{
		return m_onBar;
	}   //  isOnBar
	
	/**
	 * 	Is Process
	 *	@return true if Process
	 */
	public boolean isProcess()
	{
		return X_AD_Menu.ACTION_Process.equals(m_imageIndicator);
	}	//	isProcess

	/**
	 * 	Is Report
	 *	@return true if report
	 */
	public boolean isReport()
	{
		return X_AD_Menu.ACTION_Report.equals(m_imageIndicator);
	}	//	isReport
	
	/**
	 * 	Is Window
	 *	@return true if Window
	 */
	public boolean isWindow()
	{
		return X_AD_Menu.ACTION_Window.equals(m_imageIndicator);
	}	//	isWindow
	
	/**
	 * 	Is Workbench
	 *	@return true if Workbench
	 */
	public boolean isWorkbench()
	{
		return X_AD_Menu.ACTION_Workbench.equals(m_imageIndicator);
	}	//	isWorkbench
	
	/**
	 * 	Is Workflow
	 *	@return true if Workflow
	 */
	public boolean isWorkFlow()
	{
		return X_AD_Menu.ACTION_WorkFlow.equals(m_imageIndicator);
	}	//	isWorkFlow

	/**
	 * 	Is Form
	 *	@return true if Form
	 */
	public boolean isForm()
	{
		return X_AD_Menu.ACTION_Form.equals(m_imageIndicator);
	}	//	isForm

	/**
	 * 	Is Task
	 *	@return true if Task
	 */
	public boolean isTask()
	{
		return X_AD_Menu.ACTION_Task.equals(m_imageIndicator);
	}	//	isTask

	/**
	 * 	Get Color
	 *	@return color or black if not set
	 */
	public Color getColor()
	{
		if (m_color != null)
			return m_color;
		return Color.black;
	}	//	getColor
	
	/*************************************************************************/

	/**	Last found ID				*/
	private int                 m_lastID = -1;
	/** Last found Node				*/
	private MTreeNode           m_lastNode = null;

	/**
	 *	Return the Node with ID in list of children
	 *  @param ID id
	 *  @return VTreeNode with ID or null
	 */
	public MTreeNode findNode (int ID)
	{
		if (m_node_ID == ID)
			return this;
		//
		if (ID == m_lastID && m_lastNode != null)
			return m_lastNode;
		//
		Enumeration en = preorderEnumeration();
		while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();
			if (ID == nd.getNode_ID())
			{
				m_lastID = ID;
				m_lastNode = nd;
				return nd;
			}
		}
		return null;
	}   //  findNode

}   //  MTreeNode

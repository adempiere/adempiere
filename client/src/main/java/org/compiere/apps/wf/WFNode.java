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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;

/**
 *	Graphical Work Flow Node.
 *  Listen to PropertyChange for selection
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: WFNode.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class WFNode extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3267215731910132678L;


	/**
	 * 	Create WF Node
	 * 	@param node model
	 */
	public WFNode (MWFNode node)
	{
		super();
		setOpaque(true);
		m_node = node;
		setName(m_node.getName());
		m_icon = new WFIcon(node.getAction());
		m_name = m_node.getName(true);
		setBorder(s_border);
		
		//	Tool Tip
		String description = node.getDescription(true);
		if (description != null && description.length() > 0)
			setToolTipText(description);
		else
			setToolTipText(node.getName(true));
		
		//	Location
		setBounds(node.getXPosition(), node.getYPosition(), s_size.width, s_size.height);
		log.config(node.getAD_WF_Node_ID() 
			+ "," + node.getName() + " - " + getLocation());
		setSelected(false);
		setVisited(false);
	}	//	WFNode

	/**	Selected Property value			*/
	public static String	PROPERTY_SELECTED = "selected";
	/**	Standard (raised) Border		*/
	private static Border 	s_border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	/**	Selected (lowered) Border		*/
	private static Border 	s_borderSelected = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
	/**	Size of the Node				*/
	private static Dimension	s_size = new Dimension (120, 50);
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WFNode.class);
	
	/**	ID						*/
	private MWFNode 		m_node = null;
	/**	Icon					*/
	private WFIcon			m_icon = null;
	/** Name to paint			*/
	private String			m_name = null;
	/**	Selected Value			*/
	private boolean			m_selected = false;
	/**	Visited Value			*/
	private boolean			m_visited = false;
	/**	Was node moved			*/
	private boolean			m_moved = false;

	
	/**************************************************************************
	 * 	Set Selected.
	 * 	Selected: blue foreground - lowered border
	 * 	UnSelected: black foreground - raised border
	 * 	@param selected selected
	 */
	public void setSelected (boolean selected)
	{
		firePropertyChange(PROPERTY_SELECTED, m_selected, selected);
		m_selected = selected;
		if (m_selected)
		{
			setBorder (s_borderSelected);
			setForeground(Color.blue);
		}
		else
		{
			setBorder (s_border);
			setForeground (Color.black);
		}
	}	//	setSelected
	
	/**
	 * 	Set Visited.
	 * 	Visited: green background
	 * 	NotVisited: 
	 *	@param visited visited
	 */
	public void setVisited (boolean visited)
	{
		m_visited = visited;
		if (m_visited)
		{
			setBackground(Color.green);
		}
		else
		{
			setBackground(Color.lightGray);
		}
	}	//	setVisited

	/**
	 * 	Get Selected
	 * 	@return selected
	 */
	public boolean isSelected()
	{
		return m_selected;
	}	//	isSelected

	/**
	 * 	Get Client ID
	 * 	@return Client ID
	 */
	public int getAD_Client_ID()
	{
		return m_node.getAD_Client_ID();
	}	//	getAD_Client_ID

	/**
	 * 	Is the node Editable
	 *	@return yes if the Client is the same
	 */
	public boolean isEditable()
	{
		return getAD_Client_ID() == Env.getAD_Client_ID(Env.getCtx());
	}	//	isEditable
	
	
	/**
	 * 	Get Node ID
	 * 	@return Node ID
	 */
	public int getAD_WF_Node_ID()
	{
		return m_node.getAD_WF_Node_ID();
	}	//	getAD_WF_Node_ID

	/**
	 * 	Get Node Model
	 * 	@return Node Model
	 */
	public MWFNode getModel()
	{
		return m_node;
	}	//	getModel

	/**
	 * 	Set Location - also for Node.
	 *	@param x x
	 *	@param y y
	 */
	public void setLocation (int x, int y)
	{
		super.setLocation (x, y);
		m_node.setPosition(x, y);
	}
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("WFNode[");
		sb.append(getAD_WF_Node_ID()).append("-").append(m_name)
			.append(",").append(getBounds())
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Get Font.
	 * 	Italics if not editable
	 *	@return font
	 */
	public Font getFont ()
	{
		Font base = new Font(null);
		if (!isEditable())
			return base;
		//	Return Bold Italic Font
		return new Font(base.getName(), Font.ITALIC | Font.BOLD, base.getSize());
	}	//	getFont
	
	/**************************************************************************
	 * 	Get Preferred Size
	 *	@return size
	 */
	public Dimension getPreferredSize ()
	{
		return s_size;
	}	//	getPreferredSize

	/**
	 * 	Paint Component
	 *	@param g Graphics
	 */
	protected void paintComponent (Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		Rectangle bounds = getBounds();
		m_icon.paintIcon(this, g2D, 0, 0);
		//	Paint Text
		Color color = getForeground();
		g2D.setPaint(color);
		Font font = getFont();
		//
		AttributedString aString = new AttributedString(m_name);
		aString.addAttribute(TextAttribute.FONT, font);
		aString.addAttribute(TextAttribute.FOREGROUND, color);
		AttributedCharacterIterator iter = aString.getIterator();
		//
		LineBreakMeasurer measurer = new LineBreakMeasurer(iter, g2D.getFontRenderContext());
		float width = s_size.width - m_icon.getIconWidth() - 2;
		TextLayout layout = measurer.nextLayout(width);
		float xPos = m_icon.getIconWidth();
		float yPos = layout.getAscent() + 2;
		//
		layout.draw(g2D, xPos, yPos);
		width = s_size.width - 4;	//	2 pt 
		while (measurer.getPosition() < iter.getEndIndex())
		{
			layout = measurer.nextLayout(width);
			yPos += layout.getAscent() + layout.getDescent() + layout.getLeading();
			layout.draw(g2D, 2, yPos);
		}
	}	//	paintComponent
	
}	//	WFNode

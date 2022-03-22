/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.apps.wf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import org.compiere.wf.MWFNode;

/**
 * 
 * @author Low Heng Sin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1176">
 * 		@see FR [ 1176 ] Look and feel style to ADempiere 390 - Change icons on Work Flow</a>	
 */
public class WFNode {

	/**	Size of the Node				*/
	private static Dimension	s_size = new Dimension (120, 50);
	private MWFNode node;
	private WWFIcon icon;
	private Rectangle m_bounds;
	
	/**
	 * 	Create WF Node
	 * 	@param node model
	 */
	public WFNode (MWFNode node) {
		this.node = node;
		icon = new WWFIcon(node.getAction());		
		m_bounds = new Rectangle(node.getXPosition(), node.getYPosition(), s_size.width,
				s_size.height);
	}
	
	public void paint(Graphics2D g2D) {
		icon.paintIcon(null, g2D, 0, 0);
		//	Paint Text
		g2D.setPaint(Color.BLACK);
		Font base = new Font(null);
		Font font = new Font(base.getName(), Font.ITALIC | Font.BOLD, base.getSize());
		//
		AttributedString aString = new AttributedString(node.getName(true));
		aString.addAttribute(TextAttribute.FONT, font);
		aString.addAttribute(TextAttribute.FOREGROUND, Color.BLACK);
		AttributedCharacterIterator iter = aString.getIterator();
		//
		LineBreakMeasurer measurer = new LineBreakMeasurer(iter, g2D.getFontRenderContext());
		float width = s_size.width - icon.getIconWidth() - 2;
		TextLayout layout = measurer.nextLayout(width);
		float xPos = icon.getIconWidth();
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
	}

	/**
	 * 
	 * @return AD_WF_Node_ID
	 */
	public int getAD_WF_Node_ID() {
		return node.getAD_WF_Node_ID();
	}

	public Rectangle getBounds() {
		return m_bounds;
	}
	
	public MWFNode getNode() {
		return node;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setBounds(int x, int y, int width, int height) {
		m_bounds = new Rectangle(x, y, width, height);
	}
}

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
package org.adempiere.apps.graph;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.util.ArrayList;

import org.compiere.util.CLogger;

/**
 * 	Bar Graph Layout
 *	
 *  @author Jorg Janke
 *  @version $Id: BarGraphLayout.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class BarGraphLayout
	implements LayoutManager
{
	/**
	 * 	Bar Graph Layout
	 *	@param parent parenr
	 */
	public BarGraphLayout (BarGraph parent)
	{
		m_parent = parent;
	}	//	BarGraphLayout
	
	/**	Parent Container		*/
	private BarGraph			m_parent;
	/** List of Components		*/
	private ArrayList<BarGraphColumn> m_list = new ArrayList<BarGraphColumn>();
	/** Layout Complete 		*/
	private boolean				m_layoutComplete = false;
	/** Gap between columns		*/
	private static int			XGAP = 2;
	/** Gap to Axix				*/
	private static int			YGAP = 1;
	
	/**	Logger	*/
	private static CLogger 		log = CLogger.getCLogger (BarGraphLayout.class);
	
	/**
	 * 	Add Layout Component
	 *	@param name name
	 *	@param comp component
	 */
	public void addLayoutComponent (String name, Component comp)
	{
		if (comp instanceof BarGraphColumn)
			m_list.add((BarGraphColumn)comp);
		else
			log.severe("Invalid Class: " + comp);
		m_layoutComplete = false;
	}	//	addLayoutComponent

	/**
	 * 	Remove Layout Component
	 *	@param comp component
	 */
	public void removeLayoutComponent (Component comp)
	{
		m_list.remove(comp);
		m_layoutComplete = false;
	}	//	removeLayoutComponent

	/**
	 * 	Preferred Layout Size
	 *	@param parent parent
	 *	@return size
	 */
	public Dimension preferredLayoutSize (Container parent)
	{
		return parent.getPreferredSize();
	}	//	preferredLayoutSize

	/**
	 * 	Minimum Layout Size
	 *	@param parent parent
	 *	@return size
	 */
	public Dimension minimumLayoutSize (Container parent)
	{
		return parent.getMinimumSize();
	}	//	minimumLayoutSize

	
	/**
	 * 	Layout Container
	 *	@param parent
	 */
	public void layoutContainer (Container parent)
	{
		if (m_layoutComplete)
			return;
		
		//	Find Max
		double maxValue = 0;
		for (int i = 0; i < m_list.size(); i++)
		{
			BarGraphColumn column = m_list.get(i);
			maxValue = Math.max(maxValue, column.getValue());
		}
		//
		//Dimension size = m_parent.getPreferredSize();
		Dimension size =m_parent.getSize();
		log.fine("bgl: " +size.width + " x " + size.height);

		Point point0_0 = m_parent.getPoint0_0();
		
		double graphHeight = size.height - (size.height-point0_0.y) - (2*YGAP);
		double graphWidth = size.width - point0_0.x - XGAP;
		double columnWidth = (graphWidth - (XGAP*m_list.size())) / m_list.size();
		columnWidth = Math.min(30, columnWidth);
		FontMetrics fm = m_parent.getFontMetrics(m_parent.getFont());
		int fontHeight = fm.getHeight();
		columnWidth = Math.max(fontHeight, columnWidth);

		log.fine("Height=" + graphHeight + ", MaxValue=" + maxValue 
			+ ", Width=" + graphWidth + ", ColumnWidth=" + columnWidth);
		
		int x = point0_0.x + (2*XGAP);
		//	Set Values
		for (int i = 0; i < m_list.size(); i++)
		{
			BarGraphColumn column = m_list.get(i);
			double multiplier = column.getValue() / maxValue;
			double height = graphHeight * multiplier;
			column.setColHeight(height);
			column.setColWidth(columnWidth);
			Dimension ps = column.getPreferredSize();
			column.setBackground(GraphUtil.getBackground(i));
			//
			int y = point0_0.y - ps.height - YGAP;
			column.setLocation(x, y);
			column.setBounds(x, y, ps.width, ps.height);
			x += ps.width + XGAP;
			log.finer(i + " - " + ((int)(multiplier*100)) + "% - " + column.getBounds());
		}
		m_layoutComplete = true;
	}	//	layoutContainer
	
}	//	BarGraphLayout

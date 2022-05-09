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
package org.compiere.plaf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

/**
 *  Same implementation detail as in AdempierePanelUI.
 *  Additional handling of dwawing tabs.
 *  @see CompierePanelUI
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereTabbedPaneUI.java,v 1.3 2006/07/30 00:52:23 jjanke Exp $
 */
public class CompiereTabbedPaneUI extends MetalTabbedPaneUI
{
	/**
	 *  Static Create UI
	 *  @param c Component
	 *  @return Adempiere TabbedPaneUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new CompiereTabbedPaneUI();
	}   //  createUI

	/**
	 *  Install Defaults
	 */
	protected void installDefaults()
	{
		super.installDefaults();
		tabPane.setOpaque(false);
	}   //  installDefaults

	
	/*************************************************************************
	 *  Update -
	 *  This method is invoked by <code>JComponent</code> when the specified
	 *  component is being painted.
	 *
	 *  By default this method will fill the specified component with
	 *  its background color (if its <code>opaque</code> property is
	 *  <code>true</code>) and then immediately call <code>paint</code>.
	 *
	 *  @param g the <code>Graphics</code> context in which to paint
	 *  @param c the component being painted
	 *
	 *  @see #paint
	 *  @see javax.swing.JComponent#paintComponent
	 */
	public void update (Graphics g, JComponent c)
	{
		/**
		System.out.println (c.getClass().getName() + " ** " + c.isOpaque());
		Container container = c.getParent();
		while (container != null)
		{
			System.out.println (" - " + container.getClass() + " ** " + container.isOpaque() );
			container = container.getParent();
		}
		*/

	//	System.out.println("Tab: Bounds=" + c.getBounds() + " - " + c.getClass().getName());

		if (c.isOpaque())
			CompierePanelUI.updateIt (g, c);    //  tabAreaBackground
		paint (g, c);
	}   //  update

	/**
	 *  Paint it
	 *  @param g graphics
	 *  @param c component
	 */
	public void paint( Graphics g, JComponent c )
	{
		int tabPlacement = tabPane.getTabPlacement();
		Insets insets = c.getInsets();
		Dimension size = c.getSize();

		if ( tabPane.isOpaque() )
		{
			g.setColor(c.getBackground());
			/** @todo Printing of area behind Tabs */
			switch (tabPlacement)
			{
				case LEFT:
					g.fillRect( insets.left, insets.top,
						calculateTabAreaWidth( tabPlacement, runCount, maxTabWidth ),
						size.height - insets.bottom - insets.top );
					break;
				case BOTTOM:
					int totalTabHeight = calculateTabAreaHeight( tabPlacement, runCount, maxTabHeight );
					g.fillRect( insets.left, size.height - insets.bottom - totalTabHeight,
						size.width - insets.left - insets.right,
						totalTabHeight );
					break;
				case RIGHT:
					int totalTabWidth = calculateTabAreaWidth( tabPlacement, runCount, maxTabWidth );
					g.fillRect( size.width - insets.right - totalTabWidth,
						insets.top, totalTabWidth,
						size.height - insets.top - insets.bottom );
					break;
				//
				case TOP:
				default:
					g.fillRect( insets.left, insets.top,
						size.width - insets.right - insets.left,
						calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight) );
					paintHighlightBelowTab();
			}
		}
		else
			super.paint( g, c );
	}   //  paint

	
	/**************************************************************************
	 *  Paint the actual Tab Background.
	 *  Called from Basic.PaintTab (<- Basic.paintTabArea <- Basic.paint)
	 *  <p>
	 *  Based on MetalTabbedPaneUI.paintTabBackground:
	 *  Differences:
	 *  - Color based on primary Background of Tab
	 *  - Selected Tab is painted
	 *  <pre>
	 *          selected    not sel
	 *  top     //////////  //////////  (lighter)
	 *                      ++++++++++  (flat/darker)
	 *
	 *  bottom              //////////  (flat/lighter)
	 *          ++++++++++  ++++++++++  (darker)
	 *
	 *  sides               //////////  (flat/ligher)
	 *                      ++++++++++  (flat/darker)
	 *  </pre>
	 *  @param g graphics
	 *  @param tabPlacement tab placement
	 *  @param tabIndex tab index
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 *  @param isSelected selected
	 */
	 protected void paintTabBackground (Graphics g, int tabPlacement,
		int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		Graphics2D g2D = (Graphics2D)g;


		//  Get Background color of Tab
		Component comp = tabPane.getComponentAt(tabIndex);
	//	System.out.println("Tab " + tabIndex + " Comp=" + comp.getName() + " " + comp.getClass().getName() + " x=" + x + ", y=" + y + ", w=" +w + ", h=" + h);
		g2D.setPaint(comp.getBackground());
		CompiereColor bg = CompiereColor.getDefaultBackground();
		if (comp instanceof JPanel)
		{
			JPanel jp = (JPanel)comp;
			try
			{
				bg = (CompiereColor)jp.getClientProperty(CompiereLookAndFeel.BACKGROUND);
			}
			catch (Exception e)
			{
				System.err.println("AdempiereTabbedPaneUI - ClientProperty: " + e.getMessage());
			}
		}

		if (bg == null)     //  No Background
		{
			if (CompiereUtils.isLeftToRight(tabPane))
			{
				switch (tabPlacement)
				{
					case LEFT:
						g2D.fillRect( x + 5, y + 1, w - 5, h - 1);
						g2D.fillRect( x + 2, y + 4, 3, h - 4 );
						break;
					case BOTTOM:
						g2D.fillRect( x + 2, y, w - 2, h - 4 );
						g2D.fillRect( x + 5, y + (h - 1) - 3, w - 5, 3 );
						break;
					case RIGHT:
						g2D.fillRect( x + 1, y + 1, w - 5, h - 1);
						g2D.fillRect( x + (w - 1) - 3, y + 5, 3, h - 5 );
						break;
					case TOP:
					default:
						g2D.fillRect( x + 4, y + 2, (w - 1) - 3, (h - 1) - 1 );
						g2D.fillRect( x + 2, y + 5, 2, h - 5 );
				}
			}
			else
			{
				switch (tabPlacement)
				{
					case LEFT:
						g2D.fillRect( x + 5, y + 1, w - 5, h - 1);
						g2D.fillRect( x + 2, y + 4, 3, h - 4 );
						break;
					case BOTTOM:
						g2D.fillRect( x, y, w - 5, h - 1 );
						g2D.fillRect( x + (w - 1) - 4, y, 4, h - 5);
						g2D.fillRect( x + (w - 1) - 4, y + (h - 1) - 4, 2, 2);
						break;
					case RIGHT:
						g2D.fillRect( x + 1, y + 1, w - 5, h - 1);
						g2D.fillRect( x + (w - 1) - 3, y + 5, 3, h - 5 );
						break;
					case TOP:
					default:
						g2D.fillRect( x, y + 2, (w - 1) - 3, (h - 1) - 1 );
						g2D.fillRect( x + (w - 1) - 3, y + 4, 3, h - 4 );
				}
			}
		}
		else    //  we have a background
		{
			if (CompiereUtils.isLeftToRight(tabPane))
			{
				switch (tabPlacement)
				{
					case LEFT:
						bg.paintRect (g2D, tabPane, x + 5, y + 1, w - 5, h - 1);
						bg.paintRect (g2D, tabPane, x + 2, y + 4, 3, h - 4 );
						break;
					case BOTTOM:
						bg.paintRect (g2D, tabPane, x + 2, y, w - 2, h - 4 );
						bg.paintRect (g2D, tabPane, x + 5, y + (h - 1) - 3, w - 5, 3 );
						break;
					case RIGHT:
					//	bg.paintRect (g2D, tabPane, x + 1, y + 1, w - 5, h - 1);
						bg.paintRect (g2D, tabPane, x, y + 2, w - 4, h - 2);    //  changed
						bg.paintRect (g2D, tabPane, x + (w - 1) - 3, y + 5, 3, h - 5 );
						break;
					case TOP:
					default:
						bg.paintRect (g2D, tabPane, x + 4, y + 2, (w - 1) - 3, (h - 1) - 1 );
						bg.paintRect (g2D, tabPane, x + 2, y + 5, 2, h - 5 );
				}
			}
			else
			{
				switch (tabPlacement)
				{
					case LEFT:
						bg.paintRect (g2D, tabPane, x + 5, y + 1, w - 5, h - 1);
						bg.paintRect (g2D, tabPane, x + 2, y + 4, 3, h - 4 );
						break;
					case BOTTOM:
						bg.paintRect (g2D, tabPane, x, y, w - 5, h - 1 );
						bg.paintRect (g2D, tabPane, x + (w - 1) - 4, y, 4, h - 5);
						bg.paintRect (g2D, tabPane, x + (w - 1) - 4, y + (h - 1) - 4, 2, 2);
						break;
					case RIGHT:
						bg.paintRect (g2D, tabPane, x + 1, y + 1, w - 5, h - 1);
						bg.paintRect (g2D, tabPane, x + (w - 1) - 3, y + 5, 3, h - 5 );
						break;
					case TOP:
					default:
						bg.paintRect (g2D, tabPane, x, y + 2, (w - 1) - 3, (h - 1) - 1 );
						bg.paintRect (g2D, tabPane, x + (w - 1) - 3, y + 4, 3, h - 4 );
				}
			}
		}

		//  Upper Part - not when selected and R/L/B
		if (!(isSelected && (tabPlacement == RIGHT || tabPlacement == LEFT || tabPlacement == BOTTOM)))
		{
			Shape top = new Rectangle (x, y, w, h/2);          //  upper half
			if (tabPlacement == TOP || tabPlacement == LEFT)
				top = new Polygon (     //  top left triangle
					new int[] {x+6, x+w, x+w,     x,       x   },
					new int[] {y,   y,   y+(h/2), y+(h/2), y+6 }, 5);
			else if (tabPlacement == RIGHT)
				top = new Polygon (     //  top right triangle
					new int[] {x, x+w-6, x+w, x+w,     x       },
					new int[] {y, y,     y+6, y+(h/2), y+(h/2) }, 5);
		//  lighter
			GradientPaint paint = new GradientPaint (
				x, y, CompiereUtils.COL_1TOP,
				x, y+(h/2), CompiereUtils.COL_1END);
			g2D.setPaint(paint);
			g2D.fill(top);
		}

		//  Lower part - not when selected and T/R/L
		if (!(isSelected && (tabPlacement == TOP || tabPlacement == RIGHT || tabPlacement == LEFT)))
		{
			Shape end = new Rectangle (x, y+(h/2), w, h/2);     //  lower half
			if (tabPlacement == BOTTOM)
				end = new Polygon (     //  bottom left triangle
					new int[] {x,       x+w,     x+w, x+6, x     },
					new int[] {y+(h/2), y+(h/2), y+h, y+h, y+h-6 }, 5);
		//  darker
			GradientPaint paint = new GradientPaint (
				x, y+(h/2), CompiereUtils.COL_2TOP,
				x, y+h, CompiereUtils.COL_2END);
			g2D.setPaint(paint);
			g2D.fill(end);
		}

	}   //  paintTabBackground

	 
	/**************************************************************************
	 *  Paint Content Border (overwriting BasicTabbedPanelUI)
	 *  Uses Color from actual Tab (not from TabbedPane)
	 *  @param g graphics
	 *  @param tabPlacement tab placement
	 *  @param selectedIndex index
	 */
	protected void paintContentBorder (Graphics g, int tabPlacement, int selectedIndex)
	{
	//	System.out.println("TabContentBorder " );
		int width = tabPane.getWidth();
		int height = tabPane.getHeight();
		Insets insets = tabPane.getInsets();

		int x = insets.left;
		int y = insets.top;
		int w = width - insets.right - insets.left;
		int h = height - insets.top - insets.bottom;

		switch (tabPlacement)
		{
			case LEFT:
				x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
				w -= (x - insets.left);
				break;
			case RIGHT:
				w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
				break;
			case BOTTOM:
				h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
				break;
			case TOP:
			default:
				y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
				h -= (y - insets.top);
		}

		//  Fill region behind content area - basically a border
		Component comp = null;
		if (selectedIndex != -1)
			comp = tabPane.getComponentAt(selectedIndex);
		if (comp != null && comp instanceof JComponent)
		{
			JComponent jc = (JComponent)comp;
			CompiereColor bg = null;
			try
			{
				if (jc != null)
					bg = (CompiereColor)jc.getClientProperty(CompiereLookAndFeel.BACKGROUND);
			}
			catch (Exception e)
			{
				System.err.println("AdempiereTabbedPaneUI - ClientProperty: " + e.getMessage());
			}
			if (bg == null)
			{
				bg = new CompiereColor(jc.getBackground());
				jc.putClientProperty(CompiereLookAndFeel.BACKGROUND, bg);
			}
			bg.paintRect(g, jc, x,y, w,h);
		}
		//  Not a JComponent - paint flat
		else if (comp != null)
		{
			g.setColor(comp.getBackground());
			g.fillRect(x,y, w,h);
		}

		paintContentBorderTopEdge(g, tabPlacement, selectedIndex, x, y, w, h);
		paintContentBorderLeftEdge(g, tabPlacement, selectedIndex, x, y, w, h);
		paintContentBorderBottomEdge(g, tabPlacement, selectedIndex, x, y, w, h);
		paintContentBorderRightEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	}   //  paintContentBorder

	/**
	 *  Paint left content border edge
	 *  @param g graphics
	 *  @param tabPlacement tab placement
	 *  @param selectedIndex index
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 */
	protected void paintContentBorderLeftEdge (Graphics g, int tabPlacement,
		int selectedIndex, int x, int y, int w, int h)
	{
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
		g.setColor(selectHighlight);

		// Draw unbroken line if tabs are not on LEFT, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		if (tabPlacement != LEFT || selectedIndex < 0
				|| (selRect.x + selRect.width + 1 < x)
				|| (selRect.y < y || selRect.y > y + h))
		{
			g.drawLine(x, y, x, y+h-2);
		}
		else
		{
			// Break line to show visual connection to selected tab
			g.drawLine(x, y, x, selRect.y + 1);
			if (selRect.y + selRect.height < y + h - 2)
				g.drawLine(x, selRect.y + selRect.height + 1, x, y+h-2);    //  bug
		}
	}

	/**
	 *  Paint bottom content area edge
	 *  @param g graphics
	 *  @param tabPlacement tab placement
	 *  @param selectedIndex index
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 */
	protected void paintContentBorderBottomEdge (Graphics g, int tabPlacement,
		int selectedIndex, int x, int y, int w, int h)
	{
		boolean leftToRight = CompiereUtils.isLeftToRight(tabPane);
		int bottom = y + h - 1;
		int right = x + w - 1;
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
		g.setColor(shadow);

		// Draw unbroken line if tabs are not on BOTTOM, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		if (tabPlacement != BOTTOM
				|| selectedIndex < 0
//				|| (selRect.y - 1 > h)      //  bug!!
				|| (selRect.x < x || selRect.x > x + w))
		{
			g.setColor(darkShadow);
			g.drawLine(x, y+h-1, x+w-1, y+h-1);
		}
		else
		{
			// Break line to show visual connection to selected tab
			boolean lastInRun = isLastInRun(selectedIndex);
			g.setColor(darkShadow);
			if ( leftToRight || lastInRun )
				g.drawLine(x, bottom, selRect.x, bottom);
			else
				g.drawLine(x, bottom, selRect.x - 1, bottom);

			if (selRect.x + selRect.width < x + w - 2)
			{
				if ( leftToRight && !lastInRun )
					g.drawLine(selRect.x + selRect.width, bottom, right, bottom);
				else
					g.drawLine(selRect.x + selRect.width - 1, bottom, right, bottom);
			}
		}
	}   //  paintContentBorderBottomEdge

	/**
	 *  Paint right Contenr border edge
	 *  @param g graphics
	 *  @param tabPlacement tab placement
	 *  @param selectedIndex index
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 */
	protected void paintContentBorderRightEdge (Graphics g, int tabPlacement,
		int selectedIndex, int x, int y, int w, int h)
	{
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
		g.setColor(shadow);

		// Draw unbroken line if tabs are not on RIGHT, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		if (tabPlacement != RIGHT
				|| selectedIndex < 0
//				|| (selRect.x - 1 > w)      //  bug !!
				|| (selRect.y < y || selRect.y > y + h)
				)
		{
			g.setColor(darkShadow);
			g.drawLine(x+w-1, y, x+w-1, y+h-1);
		}
		else
		{
			// Break line to show visual connection to selected tab
			g.setColor(darkShadow);
			g.drawLine(x+w-1, y, x+w-1, selRect.y);
			if (selRect.y + selRect.height < y + h - 2)
			{
				g.setColor(darkShadow);
				g.drawLine(x+w-1, selRect.y + selRect.height, x+w-1, y+h-2);
			}
		}
	}   //  paintContentBorderRightEdge

	/**
	 *  Is Last Run
	 *  @param tabIndex index
	 *  @return true if last tab run
	 */
	private boolean isLastInRun (int tabIndex)
	{
		int run = getRunForTab( tabPane.getTabCount(), tabIndex );
		int lastIndex = lastTabInRun( tabPane.getTabCount(), run );
		return tabIndex == lastIndex;
	}   //  isLastRun

	
	/**************************************************************************
	 *  Fill Tab gap triangle (no)
	 *  @param currentRun current run
	 *  @param tabIndex tab index
	 *  @param x x
	 *  @param y y
	 *  @return false
	 */
	protected boolean shouldFillGap (int currentRun, int tabIndex, int x, int y)
	{
		return false;
	}   //  shouldFillGap

	/**
	 *  Paint Top Tab Border
	 *  @param tabIndex index
	 *  @param g graphics
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 *  @param btm bottom
	 *  @param rght right
	 *  @param isSelected selected
	 */
	protected void paintTopTabBorder ( int tabIndex, Graphics g,
		int x, int y, int w, int h, int btm, int rght, boolean isSelected )
	{
		int currentRun = getRunForTab( tabPane.getTabCount(), tabIndex );
		int lastIndex = lastTabInRun( tabPane.getTabCount(), currentRun );
		int firstIndex = tabRuns[ currentRun ];
		boolean leftToRight = CompiereUtils.isLeftToRight(tabPane);
		int bottom = h - 1;
		int right = w - 1;

		// **   Paint Gap **
		if ( shouldFillGap( currentRun, tabIndex, x, y ) )
		{
			g.translate( x, y );
			if ( leftToRight )
			{
				g.setColor( getColorForGap( currentRun, x, y + 1 ) );
				g.fillRect( 1, 0, 5, 3 );
				g.fillRect( 1, 3, 2, 2 );
			}
			else
			{
				g.setColor( getColorForGap( currentRun, x + w - 1, y + 1 ) );
				g.fillRect( right - 5, 0, 5, 3 );
				g.fillRect( right - 2, 3, 2, 2 );
			}
			g.translate( -x, -y );
		}

		g.translate( x, y );
		//  ** Paint Border **
		g.setColor( darkShadow );
		if (leftToRight)
		{
			// Paint slant
			g.drawLine( 1, 5, 6, 0 );
			// Paint top
			g.drawLine( 6, 0, right, 0 );
			// Paint right
			if ( tabIndex==lastIndex )  // last tab in run
				g.drawLine( right, 1, right, bottom );
			// Paint left
			if ( tabIndex != tabRuns[ runCount - 1 ] )  // not the first tab in the last run
				g.drawLine( 0, 0, 0, bottom );
			else                                        // the first tab in the last run
				g.drawLine( 0, 6, 0, bottom );
		}
		else
		{
			// Paint slant
			g.drawLine( right - 1, 5, right - 6, 0 );
			// Paint top
			g.drawLine( right - 6, 0, 0, 0 );
			// Paint right
			if ( tabIndex != tabRuns[ runCount - 1 ] )  // not the first tab in the last run
				g.drawLine( right, 0, right, bottom );
			else                                        // the first tab in the last run
				g.drawLine( right, 6, right, bottom );
			// Paint left
			if ( tabIndex==lastIndex )                  // last tab in run
				g.drawLine( 0, 1, 0, bottom );
		}

		//  Paint button
		if (!isSelected)
			g.drawLine(0, bottom, right, bottom);       //  added

		// ** Paint Highlight **
		g.setColor( isSelected ? selectHighlight : highlight );
		if ( leftToRight )
		{
			// Paint slant
			g.drawLine( 1, 6, 6, 1 );
			// Paint top
			if (tabIndex == lastIndex)
				g.drawLine( 6, 1, right-1, 1 );
			else
				g.drawLine( 6, 1, right, 1 );       //  bug !!
			// Paint left
			g.drawLine( 1, 6, 1, bottom );

			// paint highlight in the gap on tab behind this one
			// on the left end (where they all line up)
			if ( tabIndex==firstIndex && tabIndex!=tabRuns[runCount - 1] )
			{
				//  first tab in run but not first tab in last run
				if (tabPane.getSelectedIndex()==tabRuns[currentRun+1])
				{
					// tab in front of selected tab
					g.setColor( selectHighlight );
				}
				else
				{
					// tab in front of normal tab
					g.setColor( highlight );
				}
				g.drawLine( 1, 0, 1, 4 );
			}
		}
		else
		{
			// Paint slant
			g.drawLine( right - 1, 6, right - 6, 1 );
			// Paint top
			g.drawLine( right - 6, 1, 1, 1 );

			// Paint left
			if ( tabIndex==lastIndex )      // last tab in run
				g.drawLine( 1, 1, 1, bottom );
			else
				g.drawLine( 0, 1, 0, bottom );
		}
		g.translate( -x, -y );
	}   //  paintTopTabBorder

	/**
	 *  Paint Border of Left Tab.
	 *  Does not fill triangle
	 *
	 *  @param tabIndex index
	 *  @param g graphics
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 *  @param btm bottom
	 *  @param rght right
	 *  @param isSelected selected
	 */
	protected void paintLeftTabBorder (int tabIndex, Graphics g,
		int x, int y, int w, int h, int btm, int rght, boolean isSelected)
	{
		int tabCount = tabPane.getTabCount();
		int currentRun = getRunForTab( tabCount, tabIndex );
		int lastIndex = lastTabInRun( tabCount, currentRun );
		int firstIndex = tabRuns[ currentRun ];

		g.translate( x, y );

		int bottom = h - 1;
		int right = w - 1;

		// ** Paint Highlight **
		g.setColor( isSelected ? selectHighlight : highlight );
		// Paint slant
		g.drawLine( 1, 6, 6, 1 );
		// Paint top
		g.drawLine( 6, 1, right, 1 );
		// Paint left
		g.drawLine( 1, 6, 1, bottom );
		//  Paint right
		if (!isSelected)
			g.drawLine(right-1, 0, right-1, bottom );

		// ** Paint Border **
		g.setColor( darkShadow );
		// Paint slant
		g.drawLine( 1, 5, 6, 0 );
		// Paint top
		g.drawLine( 6, 0, right, 0 );
		// Paint left
		g.drawLine( 0, 6, 0, bottom );
		// Paint bottom
		g.drawLine( 0, bottom, right, bottom );
		//  Paint right
		if (!isSelected)
			g.drawLine(right, 0, right, bottom );
		//
		g.translate( -x, -y );
	}   //  paintLeftTabBorder


	/**
	 *  Paint Border of Right Tab.
	 *  Does not fill triangle
	 *
	 *  @param tabIndex index
	 *  @param g graphics
	 *  @param x x
	 *  @param y y
	 *  @param w width
	 *  @param h height
	 *  @param btm bottom
	 *  @param rght right
	 *  @param isSelected selected
	 */
	protected void paintRightTabBorder (int tabIndex, Graphics g,
		int x, int y, int w, int h, int btm, int rght, boolean isSelected)
	{
		int tabCount = tabPane.getTabCount();
		int currentRun = getRunForTab( tabCount, tabIndex );
		int lastIndex = lastTabInRun( tabCount, currentRun );
		int firstIndex = tabRuns[ currentRun ];

		g.translate( x, y );

		int bottom = h - 1;
		int right = w - 1;

		// ** Paint Highlight **
		g.setColor( isSelected ? selectHighlight : highlight );
		// Paint slant
		g.drawLine (right-6, 1, right-1, 6 );
		// Paint top
		g.drawLine (0, 1, right - 6, 1 );
		// Paint right
		g.drawLine (right-1, 6, right-1, bottom-1);
		// Paint left
		if (!isSelected)
			g.drawLine (0, 1, 0, bottom-1);

		// ** Paint Border **
		g.setColor( darkShadow );
		// Paint slant
		g.drawLine (right - 6, 0, right, 6 );
		// Paint top
		g.drawLine (0, 0, right - 6, 0 );
		// Paint right
		g.drawLine (right, 6, right, bottom );
		// Paint bottom
		g.drawLine( 0, bottom, right, bottom );

		g.translate( -x, -y );
	}   //  paintRightTabBorder


	/**************************************************************************
	 *  Calculate Tab Width.
	 *  We may have to overwrite to adjust width for TabHirarchyLevel
	 *  @param tabPlacement tab placement
	 *  @param tabIndex tab index
	 *  @param metrics metcics
	 *  @return tab width
	 */
	protected int calculateTabWidth (int tabPlacement, int tabIndex, FontMetrics metrics)
	{
		boolean calculate = !(tabPlacement == TOP || tabPlacement == BOTTOM);
		//  HTML
		if (getTextViewForTab(tabIndex) != null)
			calculate = false;
		//  No spaces in title
		String title = tabPane.getTitleAt(tabIndex);
		int pos = title.indexOf(' ');
		if (calculate && pos == -1)
			calculate = false;
		if (!calculate)
			return super.calculateTabWidth (tabPlacement, tabIndex, metrics);
		//
		Icon icon = getIconForTab(tabIndex);
		Insets tabInsetsLocal = getTabInsets(tabPlacement, tabIndex);
		int width = tabInsetsLocal.left + tabInsetsLocal.right + 3;

		if (icon != null)
			width += icon.getIconWidth() + textIconGap;

		String firstLine = title.substring(0, pos);
		String secondLine = title.substring(pos+1);
		width += Math.max(SwingUtilities.computeStringWidth (metrics, firstLine),
			SwingUtilities.computeStringWidth (metrics, secondLine));
		return width;
	}   //  calculateTabWidth

	/**
	 * Calculate TabHeight
	 * @param tabPlacement tab placement
	 * @param tabIndex tab index
	 * @param fontHeight font height
	 * @return tab height
	 */
	protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		boolean calculate = !(tabPlacement == TOP || tabPlacement == BOTTOM);
		//  HTML
		if (getTextViewForTab(tabIndex) != null)
			calculate = false;
		//  No spaces in title
		String title = tabPane.getTitleAt(tabIndex);
		int pos = title.indexOf(' ');
		if (calculate && pos == -1)
			calculate = false;
		if (!calculate)
			return super.calculateTabHeight (tabPlacement, tabIndex, fontHeight);
		//
		int height = fontHeight * 2;
		Icon icon = getIconForTab(tabIndex);
		Insets tabInsetsLocal = getTabInsets(tabPlacement, tabIndex);
		if (icon != null)
			height = Math.max(height, icon.getIconHeight());
		height += tabInsetsLocal.top + tabInsetsLocal.bottom + 2;
		return height;
	}

	/**
	 *  Layout Label
	 *  @param tabPlacement tab placement
	 *  @param metrics fint metrics
	 *  @param tabIndex tab index
	 *  @param title title
	 *  @param icon icon
	 *  @param tabRect tab bounds
	 *  @param iconRect icon bounds
	 *  @param textRect text bounds
	 *  @param isSelected selected
	 */
	protected void layoutLabel(int tabPlacement,
		FontMetrics metrics, int tabIndex, String title, Icon icon,
		Rectangle tabRect, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		boolean calculate = !(tabPlacement == TOP || tabPlacement == BOTTOM);
		//  HTML
		if (getTextViewForTab(tabIndex) != null)
			calculate = false;
		if (!calculate)
		{
			super.layoutLabel (tabPlacement, metrics, tabIndex, title, icon,
				tabRect, iconRect, textRect, isSelected);
		//	System.out.println("1.tabRect=" + tabRect + " - textRect=" + textRect + " - " + title);
			return;
		}
		//
		textRect.x = textRect.y = iconRect.x = iconRect.y = 0;
		SwingUtilities.layoutCompoundLabel(tabPane,
			metrics, title, icon,
			SwingUtilities.TOP,     //  vert
			SwingUtilities.LEFT,    //  horiz
			SwingUtilities.CENTER,      //  vert Text
			SwingUtilities.TRAILING,    //  horiz Text
			tabRect,
			iconRect,
			textRect,
			textIconGap);
		tabPane.putClientProperty("html", null);
		int xNudge = getTabLabelShiftX(tabPlacement, tabIndex, isSelected);
		int yNudge = getTabLabelShiftY(tabPlacement, tabIndex, isSelected);
		//  positioned top left - add gap
		Insets tabInsetsLocal = getTabInsets(tabPlacement, tabIndex);
		xNudge += tabInsetsLocal.left;
		yNudge += tabInsetsLocal.top + 1;
		iconRect.x += xNudge;
		iconRect.y += yNudge;
		textRect.x += xNudge;
		textRect.y += yNudge;
	//	System.out.println("2.tabRect=" + tabRect + " - textRect=" + textRect + " - " + title);
	}

	/**
	 * Paint Tab
	 * @param g graphics
	 * @param tabPlacement tab placement
	 * @param font font
	 * @param metrics font metrics
	 * @param tabIndex tab index
	 * @param title title
	 * @param textRect text bounds
	 * @param isSelected selected
	 */
	protected void paintText (Graphics g, int tabPlacement,
		Font font, FontMetrics metrics, int tabIndex,
		String title, Rectangle textRect, boolean isSelected)
	{
		boolean calculate = !(tabPlacement == TOP || tabPlacement == BOTTOM);
		//  HTML
		if (getTextViewForTab(tabIndex) != null)
			calculate = false;
		if (!calculate)
		{
			super.paintText (g, tabPlacement, font, metrics, tabIndex,
				title, textRect, isSelected);
			return;
		}

	//	System.out.println("3.textRect " + textRect + " - " + title);
		String firstLine = title;
		String secondLine = null;
		int pos = title.indexOf(' ');
		if (pos != -1)
		{
			firstLine = title.substring(0, pos);
			secondLine = title.substring(pos+1);
		}

		g.setFont(font);
		int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
		if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex))
		{
			Color c = tabPane.getForegroundAt(tabIndex);
			if (!isSelected)
			{
				if (c.equals(Color.black))
					c = Color.darkGray;
				else
					c = c.brighter();
			}
			g.setColor(c);
			//  first line
			BasicGraphicsUtils.drawStringUnderlineCharAt
				(g, firstLine, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
			//  secondLine
			if (secondLine != null)
				BasicGraphicsUtils.drawStringUnderlineCharAt
					(g, secondLine, mnemIndex-firstLine.length(),
					textRect.x, textRect.y + metrics.getAscent() + metrics.getHeight());
		}
		else
		{   // tab disabled
			g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
			BasicGraphicsUtils.drawStringUnderlineCharAt
				(g, firstLine, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
			//  secondLine
			if (secondLine != null)
				BasicGraphicsUtils.drawStringUnderlineCharAt
					(g, secondLine, mnemIndex-firstLine.length(),
					textRect.x, textRect.y + metrics.getAscent() + metrics.getHeight());
			//
			g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
			BasicGraphicsUtils.drawStringUnderlineCharAt
				(g, firstLine, mnemIndex, textRect.x -1, textRect.y + metrics.getAscent() -1);
			//  secondLine
			if (secondLine != null)
				BasicGraphicsUtils.drawStringUnderlineCharAt
					(g, secondLine, mnemIndex-firstLine.length(),
					textRect.x -1, textRect.y + metrics.getAscent() + metrics.getHeight() -1);
		}
	}   //  paintText

	
	/**************************************************************************
	 *  Create Layout Manager to size & position tabs
	 *  @return Layout Manager
	 */
	protected LayoutManager createLayoutManager()
	{
		return new TabbedPaneLayout();
	}   //  createLayoutManager

	/**
	 *  Layout Manager to overwrite TabRect size
	 */
	public class TabbedPaneLayout extends MetalTabbedPaneUI.TabbedPaneLayout
	{
		/**
		 *  Calculate Tab Rectangle Size
		 *  @param tabPlacement tab placement
		 *  @param tabCount no of tabs
		 */
		protected void calculateTabRects(int tabPlacement, int tabCount)
		{
			super.calculateTabRects(tabPlacement, tabCount);
			if (tabPlacement == TOP || tabPlacement == BOTTOM)
				return;
		//	System.out.println("calculateTabRects " + tabCount);
			int tabHeight = calculateMaxTabHeight(tabPlacement);
			for (int i = 0; i < rects.length; i++)
			{
				int level = 0;
				Component comp = tabPane.getComponentAt(i);
				if (comp instanceof JComponent)
				{
					JComponent jc = (JComponent)comp;
					try
					{
						Integer ll = (Integer)jc.getClientProperty(CompiereLookAndFeel.TABLEVEL);
						if (ll != null)
							level = ll.intValue();
					}
					catch (Exception e)
					{
						System.err.println("AdempiereTabbedPaneUI - ClientProperty: " + e.getMessage());
					}
				}
				if (level != 0)
				{
					if (tabPlacement == LEFT)
						rects[i].x += level * 5;
					rects[i].width -= level * 5;
				}
				//  Height
				rects[i].height = tabHeight;
				if (i > 0)
					rects[i].y = rects[i-1].y + tabHeight; //   rects[i-1].height;
			}   //   for all rects
		}   //  calculate TabRects

	}   //  TabbedPaneLayout
	
}   //  AdempiereTabbedPaneUI

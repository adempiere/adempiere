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
package org.compiere.print;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import org.compiere.model.MQuery;
import org.compiere.print.layout.LayoutEngine;
import org.compiere.print.layout.Page;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;

/**
 *	View Panel
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: View.java,v 1.2 2006/07/30 00:53:02 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2539927 ] Display Zoom combobox
 * 				https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2539927&group_id=176962
 */
public class View extends CPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5640892952739279088L;

	/**
	 *	Print Preview
	 *  @param layout Layout
	 */
	public View (LayoutEngine layout)
	{
		m_layout = layout;
	}	//	View

	/**	Layout to be Printed		*/
	private LayoutEngine 			m_layout;


	/**	Zoom Level						*/
	private int						m_zoomLevel = 2;
	/** Zoom Options					*/
	public static final String[]	ZOOM_OPTIONS = new String[] {"200%", "150%", "100%", "75%", "50%"};
	private static HashMap<String, Double> ZOOM_ScaleValues = new HashMap<String, Double>();
	static {
		ZOOM_ScaleValues.put("200%",	2.00);
		ZOOM_ScaleValues.put("150%",	1.50);
		ZOOM_ScaleValues.put("100%",	1.00);
		ZOOM_ScaleValues.put("75%",		0.75);
		ZOOM_ScaleValues.put("50%",		0.50);
	}
	
	/**	Margin around paper				*/
	public static int				MARGIN = 5;
	/** Margin Background Color			*/
	private static Color			COLOR_BACKGROUND = Color.lightGray;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(View.class);

	/*************************************************************************/

	public int getMarginSize(boolean doScale)
	{
		double scale = (doScale ? getScale() : 1.00);
		return (int)(MARGIN * scale); 
	}
	
	/**
	 * 	Minimum Size
	 * 	@return Max Page Size
	 */
	public Dimension getMinimumSize()
	{
		return getMaximumSize();
	}	//	getMinimumSize

	/**
	 * 	Minimum Size
	 * 	@return Max Page Size
	 */
	public Dimension getMaximumSize()
	{
		return new Dimension (getPaperWidth()+(2*getMarginSize(true)),
			(getPaperHeight()+getMarginSize(true))*getPageCount()+getMarginSize(true));
	}	//	getMaximumSize

	/**
	 * 	Preferred Size
	 * 	@return Max Page Size
	 */
	public Dimension getPreferredSize()
	{
		return getMaximumSize();
	}	//	getPreferredSize

	/**
	 * 	Is Archivable
	 *	@return true if archivable
	 */
	public boolean isArchivable()
	{
		return ArchiveEngine.isValid(m_layout);
	}	//	IsArchivable

	/**
	 * 	Paint Component
	 * 	@param g Graphics
	 */
	public void paintComponent (Graphics g)
	{
	//	log.fine( "View.paintComponent", g.getClip());
		Graphics2D g2D = (Graphics2D)g;
		g2D.scale(getScale(), getScale());
		Rectangle bounds = g2D.getClipBounds();
		//
		g2D.setColor(COLOR_BACKGROUND);
		g2D.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

		//	for all pages
		for (int page = 0; page < m_layout.getPages().size(); page++)
		{
			Rectangle pageRectangle = getRectangleOfPage(page+1, false);
			if (bounds.intersects(pageRectangle))
			{
				Page p = (Page)m_layout.getPages().get(page);
				p.paint (g2D, pageRectangle, true, false);		//	sets context
				m_layout.getHeaderFooter().paint(g2D, pageRectangle, true);
			}	//	paint page
		}	//	for all pages
	}	//	paintComponent


	/**************************************************************************
	 * 	Set Zoom Level
	 * 	@param level zoom level
	 */
	public void setZoomLevel(int level)
	{
		m_zoomLevel = level;
	}	//	setZoomLevel

	/**
	 * 	Set Zoom Level
	 * 	@param levelString zoom level string
	 */
	public void setZoomLevel(String levelString)
	{
		for (int i = 0; i < ZOOM_OPTIONS.length; i++)
		{
			if (ZOOM_OPTIONS[i].equals(levelString))
			{
				setZoomLevel(i);
				break;
			}
		}
	}	//	setZoomLevel

	/**
	 * 	Get Zoom Level
	 * 	@return zoom level
	 */
	public int getZoomLevel()
	{
		return m_zoomLevel;
	}	//	getZoomLevel
	
	private double getScale()
	{
		Double scale = ZOOM_ScaleValues.get(ZOOM_OPTIONS[m_zoomLevel]);
		if (scale != null)
			return scale.doubleValue();
		else
			return 1.00;
	}

	/**
	 * 	Get Rectange of Page
	 * 	@param pageNo page no
	 * 	@return rectangle
	 */
	public Rectangle getRectangleOfPage(int pageNo)
	{
		return getRectangleOfPage(pageNo, true);
//		int y = (int)(MARGIN + ((pageNo-1) * (getPaperHeight() + MARGIN)));
//		return new Rectangle (MARGIN, y, getPaperWidth(), getPaperHeight());
	}	//	getRectangleOfPage

	public Rectangle getRectangleOfPage(int pageNo, boolean doScale)
	{
		int y = (int)(getMarginSize(doScale) + ((pageNo-1) * (getPaperHeight(doScale) + getMarginSize(doScale))));
		return new Rectangle (getMarginSize(doScale), y, getPaperWidth(doScale), getPaperHeight(doScale));
	}	//	getRectangleOfPage

	/**
	 * 	Get Page at Point
	 * 	@param p Point
	 * 	@return page as float to determine also position on page
	 */
	public float getPageNoAt (Point p)
	{
		float y = (float)(p.y / getScale());
		float pageHeight = getPaperHeight(false) + getMarginSize(false);
		return 1f + (y/pageHeight);
	}	//	getPageAt

	/**
	 * 	Get Page Count
	 * 	@return page count
	 */
	public int getPageCount()
	{
		return m_layout.getPages().size();
	}	//	getPageCount

	/**
	 * 	Get Page Info for Multi-Page tables
	 * 	@param pageNo page
	 * 	@return info e.g. (1,1)
	 */
	public String getPageInfo(int pageNo)
	{
		return m_layout.getPageInfo(pageNo);
	}	//	getPageInfo

	/**
	 * 	Get Max Page Info for Multi-Page tables
	 * 	@return info e.g. (3,2)
	 */
	public String getPageInfoMax()
	{
		return m_layout.getPageInfoMax();
	}	//	getPageInfo

	/**
	 * 	Get Paper
	 * 	@return paper
	 */
	public CPaper getPaper()
	{
		return m_layout.getPaper();
	}	//	getPaper

	/**
	 * 	Get Paper Height
	 * 	@return paper height
	 */
	public int getPaperHeight()
	{
		return getPaperHeight(true);
//		return (int)(m_layout.getPaper().getHeight(true) * getScale());
	}	//	getPaperHeight

	public int getPaperHeight(boolean doScale)
	{
		double scale = (doScale ? getScale() : 1.0);
		return (int)(m_layout.getPaper().getHeight(true) * scale);
	}	//	getPaperHeight

	/**
	 * 	Get Paper Height
	 * 	@return paper height
	 */
	public int getPaperWidth()
	{
		return getPaperWidth(true);
//		return (int)(m_layout.getPaper().getWidth(true) * getScale());
	}	//	getPaperHeight

	public int getPaperWidth(boolean doScale)
	{
		double scale = (doScale ? getScale() : 1.0);
		return (int)(m_layout.getPaper().getWidth(true) * scale);
	}	//	getPaperHeight

	/**
	 * 	Get Drill Down
	 *  @param absolutePoint point
	 *  @return Drill Down
	 */
	public MQuery getDrillDown (Point absolutePoint)
	{
		int pageNo = (int)getPageNoAt(absolutePoint);
		Rectangle pageRectangle = getRectangleOfPage(pageNo, false);
		Point relativePoint = new Point (
				(int)(absolutePoint.x/getScale()-pageRectangle.x),
				(int)(absolutePoint.y/getScale()-pageRectangle.y)
		);
		Page page = (Page)m_layout.getPages().get(pageNo-1);
		//
		log.config("Relative=" + relativePoint + ", " + page);
	//	log.config("AbsolutePoint=" + absolutePoint + ", PageNo=" + pageNo + ", pageRectangle=" + pageRectangle);
		MQuery retValue = page.getDrillDown (relativePoint);
		if (retValue == null)
			retValue = m_layout.getHeaderFooter().getDrillDown (relativePoint);
		return retValue;
	}	//	getDrillDown

	/**
	 * 	Get Drill Across
	 *  @param absolutePoint point
	 *  @return Drill Across
	 */
	public MQuery getDrillAcross (Point absolutePoint)
	{
		int pageNo = (int)getPageNoAt(absolutePoint);
		Rectangle pageRectangle = getRectangleOfPage(pageNo);
		Point relativePoint = new Point (
				(int)(absolutePoint.x/getScale()-pageRectangle.x),
				(int)(absolutePoint.y/getScale()-pageRectangle.y)
		);
		Page page = (Page)m_layout.getPages().get(pageNo-1);
		//
		log.config("Relative=" + relativePoint + ", " + page);
	//	log.config("AbsolutePoint=" + absolutePoint + ", PageNo=" + pageNo + ", pageRectangle=" + pageRectangle);
		return page.getDrillAcross (relativePoint);
	}	//	getDrillAcross


}	//	View

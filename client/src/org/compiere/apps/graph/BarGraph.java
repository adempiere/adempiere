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
package org.compiere.apps.graph;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.logging.*;
import java.math.*;
import java.sql.*;

import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 * 	Bar Graph
 *	
 *  @author Jorg Janke
 *  @version $Id: BarGraph.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class BarGraph extends CPanel implements ActionListener
{
	/**
	 * 	Constructor
	 */
	public BarGraph()
	{
		super();
		setLayout(m_layout);
		//	Size
		m_size = new Dimension (500,300);
		setPreferredSize(m_size);
		setMinimumSize(m_size);
		setMaximumSize(m_size);
		//	0/0 point
		FontMetrics fm = getFontMetrics(getFont());
		int fontHeight = fm.getHeight();
		int yAxis_X = fontHeight+10;				//	|
		int xAxis_Y = m_size.height-fontHeight-10;	//	-
		m_point0_0 = new Point(yAxis_X, xAxis_Y);
	}	//	BarGraph

	/**
	 * 	Constructor
	 *	@param goal goal
	 */
	public BarGraph(MGoal goal)
	{
		this();
		m_goal = goal;
		m_Y_AxisLabel = goal.getName();
		m_X_AxisLabel = goal.getXAxisText();
		loadData();
		/**	test
		add (new BarGraphColumn("Column 1", 100));
		add (new BarGraphColumn("Column 2", 200));
		add (new BarGraphColumn("Column 3", 300));
		add (new BarGraphColumn("Column 4", 400));
		add (new BarGraphColumn("Column 5", 500));
		add (new BarGraphColumn("Column 6", 400));
		add (new BarGraphColumn("Column 7", 300));
		add (new BarGraphColumn("Column 8", 200));
		add (new BarGraphColumn("Column 9", 100));
		add (new BarGraphColumn("Column 10", 200));
		add (new BarGraphColumn("Column 11", 300));
		add (new BarGraphColumn("Column 12", 400));
		add (new BarGraphColumn("Column 13", 500));
		add (new BarGraphColumn("Column 14", 100));
		**/
	}	//	BarGraph
	
	/** The Goal				*/
	private MGoal 			m_goal = null;
	/** Graph Size				*/
	private Dimension 		m_size = null;
	/** Zero/Zero Coordibate point	*/
	private Point			m_point0_0 = null;
	/** Layout					*/
	private BarGraphLayout	m_layout = new BarGraphLayout(this);
	
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger (BarGraph.class);

	/** X Axis Label			*/
	private String		m_X_AxisLabel = "X Axis";
	/** Y Axis Label			*/
	private String		m_Y_AxisLabel = "Y Axis";
	/** Y Axis Max				*/
	private double		m_Y_Max	= 0;
	/** Y Axis Target Line		*/
	private double		m_Y_Target	= 0;
	/** Y Axis Target Line Label */
	private String		m_Y_TargetLabel = null;
	
	/**
	 * 	Load Performance Data
	 */
	private void loadData()
	{
		ArrayList<BarGraphColumn> list = new ArrayList<BarGraphColumn>();
		//	Calculated
		MMeasure measure = m_goal.getMeasure();
		if (measure == null)
		{
			log.warning("No Measure for " + m_goal);
			return;
		}
		if (MMeasure.MEASURETYPE_Calculated.equals(measure.getMeasureType()))
		{
			MMeasureCalc mc = MMeasureCalc.get(Env.getCtx(), measure.getPA_MeasureCalc_ID());
			String sql = mc.getSqlBarChart(m_goal.getRestrictions(false), 
				m_goal.getMeasureDisplay(), null, 
				MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					BarGraphColumn bgc = new BarGraphColumn(mc, data);
					bgc.setLabel(date, m_goal.getMeasureDisplay());
					list.add(bgc);
				}
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
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
		}
		else if (MMeasure.MEASURETYPE_Achievements.equals(measure.getMeasureType()))
		{
			if (MMeasure.MEASUREDATATYPE_StatusQtyAmount.equals(measure.getMeasureDataType()))
			{
				MAchievement[] achievements = MAchievement.get(measure);
				for (int i = 0; i < achievements.length; i++)
				{
					MAchievement achievement = achievements[i];
					BarGraphColumn bgc = new BarGraphColumn(achievement);
					list.add(bgc);
				}
			}
			else	//	MMeasure.MEASUREDATATYPE_QtyAmountInTime	
			{
				String MeasureDisplay = m_goal.getMeasureDisplay();
				String trunc = "D";
				if (MGoal.MEASUREDISPLAY_Year.equals(MeasureDisplay))
					trunc = "Y";
				else if (MGoal.MEASUREDISPLAY_Quarter.equals(MeasureDisplay))
					trunc = "Q";
				else if (MGoal.MEASUREDISPLAY_Month.equals(MeasureDisplay))
					trunc = "MM";
				else if (MGoal.MEASUREDISPLAY_Week.equals(MeasureDisplay))
					trunc = "W";
			//	else if (MGoal.MEASUREDISPLAY_Day.equals(MeasureDisplay))
			//		trunc = "D";
				trunc = "TRUNC(DateDoc,'" + trunc + "')";
				StringBuffer sql = new StringBuffer ("SELECT SUM(ManualActual), ")
					.append(trunc).append(" FROM PA_Achievement WHERE PA_Measure_ID=? AND IsAchieved='Y' ")
					.append("GROUP BY ").append(trunc)
					.append(" ORDER BY ").append(trunc);
				PreparedStatement pstmt = null;
				try
				{
					pstmt = DB.prepareStatement (sql.toString(), null);
					pstmt.setInt(1, measure.getPA_Measure_ID());
					ResultSet rs = pstmt.executeQuery ();
					while (rs.next ())
					{
						BigDecimal data = rs.getBigDecimal(1);
						Timestamp date = rs.getTimestamp(2);
						BarGraphColumn bgc = new BarGraphColumn(m_goal, data);
						bgc.setLabel(date, m_goal.getMeasureDisplay());
						list.add(bgc);
					}
					rs.close ();
					pstmt.close ();
					pstmt = null;
				}
				catch (Exception e)
				{
					log.log (Level.SEVERE, sql.toString(), e);
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
			}	//	Achievement in time
		}	//	Achievement
		
		//	Request
		else if (MMeasure.MEASURETYPE_Request.equals(measure.getMeasureType()))
		{
			MRequestType rt = MRequestType.get(Env.getCtx(), measure.getR_RequestType_ID());
			String sql = rt.getSqlBarChart(m_goal.getRestrictions(false), 
				m_goal.getMeasureDisplay(), measure.getMeasureDataType(), 
				null, MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					int R_Status_ID = rs.getInt(3);
					BarGraphColumn bgc = new BarGraphColumn(rt, data, R_Status_ID);
					if (R_Status_ID == 0)
					{
						Timestamp date = rs.getTimestamp(2);
						bgc.setLabel(date, m_goal.getMeasureDisplay());
					}
					else
					{
						MStatus status = MStatus.get(Env.getCtx(), R_Status_ID);
						bgc.setLabel(status.getName());
					}
					list.add(bgc);
				}
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
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
		}	//	Request
		
		//	Project
		else if (MMeasure.MEASURETYPE_Project.equals(measure.getMeasureType()))
		{
			MProjectType pt = MProjectType.get(Env.getCtx(), measure.getC_ProjectType_ID());
			String sql = pt.getSqlBarChart(m_goal.getRestrictions(false), 
				m_goal.getMeasureDisplay(), measure.getMeasureDataType(), 
				null, MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					int id = rs.getInt(3);
					BarGraphColumn bgc = new BarGraphColumn(pt, data, id);
					bgc.setLabel(date, m_goal.getMeasureDisplay());
					list.add(bgc);
				}
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
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
		}	//	Project
		
		//	Add last 20
		int startValue = 0;
		if (list.size() > 20)
			startValue = list.size()-20;
		for (int i = startValue; i < list.size(); i++)
			add (list.get(i));
	}	//	loadData
	
	/**
	 * 	Get Point 0_0
	 *	@return point
	 */
	public Point getPoint0_0()
	{
		return m_point0_0;
	}	//	getPoint0_0
	
	
	/**
	 * @return Returns the x_AxisLabel.
	 */
	public String getX_AxisLabel ()
	{
		return m_X_AxisLabel;
	}	//	getX_AxisLabel
	
	/**
	 * @param axisLabel The x_AxisLabel to set.
	 */
	public void setX_AxisLabel (String axisLabel)
	{
		m_X_AxisLabel = axisLabel;
	}	//	setX_AxisLabel

	/**
	 * @return Returns the y_AxisLabel.
	 */
	public String getY_AxisLabel ()
	{
		return m_Y_AxisLabel;
	}	//	getY_AxisLabel
	
	/**
	 * @param axisLabel The y_AxisLabel to set.
	 */
	public void setY_AxisLabel (String axisLabel)
	{
		m_Y_AxisLabel = axisLabel;
	}	//	setY_AxisLabel
	
	/**
	 * @return Returns the y_TargetLabel.
	 */
	public String getY_TargetLabel ()
	{
		return m_Y_TargetLabel;
	}	//	getY_TargetLabel
	
	/**
	 * @param targetLabel The y_TargetLabel to set.
	 */
	public void setY_TargetLabel (String targetLabel, double target)
	{
		m_Y_TargetLabel = targetLabel;
		m_Y_Target = target;
	}	//	setY_TargetLabel
	
	
	/**
	 * 	Add Column
	 *	@param column column
	 */
	public void add (BarGraphColumn column)
	{
		super.add (column, "column");
		column.addActionListener(this);
	}	//	add
	
	
	/**************************************************************************
	 * 	Paint Component
	 *	@param g graphics
	 */
	protected void paintComponent (Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		Rectangle bounds = getBounds();
		//	Background
	//	g2D.setColor(Color.white);
		Dimension size = getPreferredSize();
	//	g2D.fill3DRect(0, 0, size.width, size.height, true);
		
		Font font = getFont();
		FontMetrics fm = g2D.getFontMetrics(font);
		int fontHeight = fm.getHeight();
		//
		int yAxis_X = m_point0_0.x;	//  fontHeight+10;				//	|
		int xAxis_Y = m_point0_0.y;	//	size.height-fontHeight-10;	//	-
		
		//	Paint X axis
		g2D.setColor(Color.black);
		g2D.drawLine(yAxis_X, xAxis_Y, size.width-5, xAxis_Y);
		g2D.setFont(font);
		g2D.drawString(m_X_AxisLabel, yAxis_X, xAxis_Y+fontHeight);
		
		//	Paint Y axis
		g2D.drawLine(yAxis_X, 5, yAxis_X, xAxis_Y);
		AffineTransform transform = AffineTransform.getRotateInstance(Math.PI*3/2);
		font = font.deriveFont(transform);
		g2D.setFont(font);
		g2D.drawString(m_Y_AxisLabel, yAxis_X-fontHeight+10, xAxis_Y);
		
		//	Columns
		super.paintComponent(g2D);
	}	//	paintComponent

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof BarGraphColumn)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			BarGraphColumn bgc = (BarGraphColumn)e.getSource();
			log.info(bgc.getName());
			MQuery query = null;
			if (bgc.getAchievement() != null)	//	Single Achievement
			{
				MAchievement a = bgc.getAchievement();
				query = MQuery.getEqualQuery("PA_Measure_ID", a.getPA_Measure_ID());
			}
			else if (bgc.getGoal() != null)		//	Multiple Achievements 
			{
				MGoal goal = bgc.getGoal();
				query = MQuery.getEqualQuery("PA_Measure_ID", goal.getPA_Measure_ID());
			}
			else if (bgc.getMeasureCalc() != null)	//	Document
			{
				MMeasureCalc mc = bgc.getMeasureCalc();
				query = mc.getQuery(m_goal.getRestrictions(false), 
					bgc.getMeasureDisplay(), bgc.getDate(), 
					MRole.getDefault());	//	logged in role
			}
			else if (bgc.getProjectType() != null)	//	Document
			{
				MProjectType pt = bgc.getProjectType();
				query = pt.getQuery(m_goal.getRestrictions(false), 
					bgc.getMeasureDisplay(), bgc.getDate(), bgc.getID(), 
					MRole.getDefault());	//	logged in role
			}
			else if (bgc.getRequestType() != null)	//	Document
			{
				MRequestType rt = bgc.getRequestType();
				query = rt.getQuery(m_goal.getRestrictions(false), 
					bgc.getMeasureDisplay(), bgc.getDate(), bgc.getID(),
					MRole.getDefault());	//	logged in role
			}
			if (query != null)
				AEnv.zoom(query);
			else
				log.warning("Nothing to zoom to - " + bgc);
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}
	}	//	actionPerformed
	
}	//	BarGraph

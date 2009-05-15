/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2008 Idalica Corporation                                     *
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
package org.adempiere.webui.apps.graph;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import org.adempiere.apps.graph.BarGraphColumn;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Panel;
import org.compiere.model.MAchievement;
import org.compiere.model.MGoal;
import org.compiere.model.MMeasure;
import org.compiere.model.MMeasureCalc;
import org.compiere.model.MProjectType;
import org.compiere.model.MQuery;
import org.compiere.model.MRequestType;
import org.compiere.model.MRole;
import org.compiere.model.MStatus;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Area;
import org.zkoss.zul.Imagemap;

/**
 * 	Bar Graph
 *
 *	@author hengsin
 */
public class WBarGraph extends Panel
{
	/**
	 *
	 */
	private static final long serialVersionUID = -975989183542113080L;

	/**
	 * 	Constructor
	 */
	public WBarGraph()
	{
		super();
	}	//	BarGraph

	/**
	 * 	Constructor
	 *	@param goal goal
	 */
	public WBarGraph(MGoal goal)
	{
		this();
		m_goal = goal;
		m_Y_AxisLabel = goal.getName();
		m_X_AxisLabel = goal.getXAxisText();
		loadData();
	}	//	BarGraph

	/** The Goal				*/
	private MGoal 			m_goal = null;
	/** Zero/Zero Coordibate point	*/
	private Point			m_point0_0 = null;

	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger (WBarGraph.class);

	/** X Axis Label			*/
	private String		m_X_AxisLabel = "X Axis";
	/** Y Axis Label			*/
	private String		m_Y_AxisLabel = "Y Axis";
	/** Y Axis Target Line Label */
	private String		m_Y_TargetLabel = null;

	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	/**
	 * 	Load Performance Data
	 */
	ArrayList<BarGraphColumn> list = new ArrayList<BarGraphColumn>();

	private void loadData()
	{
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
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
				ArrayList<Timestamp> dataList = new ArrayList<Timestamp>();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					BarGraphColumn bgc = new BarGraphColumn(mc, data);
					bgc.setLabel(date, m_goal.getMeasureDisplay()); //TODO copy order-loop to other measures
					int pos=0;
					for (int i = 0; i <  dataList.size(); i++)
						if (dataList.get(i).before(date)) pos++;
					dataList.add(date); // list of dates
					list.add(pos, bgc);
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
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
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement (sql.toString(), null);
					pstmt.setInt(1, measure.getPA_Measure_ID());
					rs = pstmt.executeQuery ();
					while (rs.next ())
					{
						BigDecimal data = rs.getBigDecimal(1);
						Timestamp date = rs.getTimestamp(2);
						BarGraphColumn bgc = new BarGraphColumn(m_goal, data);
						bgc.setLabel(date, m_goal.getMeasureDisplay());
						list.add(bgc);
					}
				}
				catch (Exception e)
				{
					log.log (Level.SEVERE, sql.toString(), e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
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
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
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
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
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
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					int id = rs.getInt(3);
					BarGraphColumn bgc = new BarGraphColumn(pt, data, id);
					bgc.setLabel(date, m_goal.getMeasureDisplay());
					list.add(bgc);
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}	//	Project

		//	Add last 20
		int startValue = 0;
		for (int i = startValue; i < list.size(); i++){
			dataset.addValue(list.get(i).getValue(), list.get(i).getLabel(), list.get(i).getLabel());
		}

        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            measure.getName(),         // chart title
            m_X_AxisLabel,               // domain axis label
            m_Y_AxisLabel,                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips?
            true                     // URLs?
            );

        CategoryPlot plot = chart.getCategoryPlot();
        //plot.setBackgroundPaint(Color.lightGray); //GraphUtil.getForeground(getBackground())
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        chart.getCategoryPlot().setRenderer(renderer);
		 renderer.setSeriesPaint(0, new Color(92/255f, 178/255f, 232/255f));
		 renderer.setSeriesPaint(1, new Color(56/255f, 97/255f, 119/255f));
		 renderer.setSeriesPaint(2, new Color(242/255f, 70/255f, 78/255f));
		 renderer.setSeriesPaint(3, Color.orange);
		 renderer.setSeriesPaint(4, new Color(147/255f, 196/255f, 51/255f));
		 renderer.setSeriesPaint(5, new Color(210/255f, 247/255f, 91/255f));
		 renderer.setSeriesPaint(6, new Color(129/255f, 235/255f, 249/255f));
		 renderer.setSeriesPaint(7, new Color(60/255f, 84/255f, 8/255f));
		 renderer.setSeriesPaint(8, new Color(0.8f, 0.8f, 0.8f));

		ChartRenderingInfo info = new ChartRenderingInfo();
		BufferedImage bi = chart.createBufferedImage(700, 500, BufferedImage.TRANSLUCENT, info);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);
			Imagemap myImage = new Imagemap();

			myImage.setContent(image);
			appendChild(myImage);

			int count = 0;
			for(Iterator<?> it = info.getEntityCollection().getEntities().iterator(); it.hasNext(); )
			{
				ChartEntity ce = ( ChartEntity ) it.next();

				String tooltip = ce.getToolTipText();
				if(tooltip == null) continue;

				Area area = new Area();
				myImage.appendChild(area);
				area.setCoords(ce.getShapeCoords());
				area.setShape(ce.getShapeType());
				area.setTooltiptext(tooltip);
				area.setId("WBG_"+tooltip);
				count++;
			}

			myImage.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					MouseEvent me = (MouseEvent) event;
					String areaId = me.getArea();
					if(areaId != null)
					{
						for(int i = 0; i < list.size(); i++)
						{
							String s = "WBG_(" + list.get(i).getLabel() + ", " + list.get(i).getLabel() + ") = ";
							if(areaId.startsWith(s))
							{
								chartMouseClicked(i);
								return;
							}
						}
					}
				}
			});
		}
		catch (Exception e) {
			log.log (Level.SEVERE, "", e);
		}
	}	// loadData
	/**
	 * Get Point 0_0
	 *
	 * @return point
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
//		m_Y_Target = target;
	}	//	setY_TargetLabel


	/**
	 * 	Add Column
	 *	@param column column
	 */
	public void add (BarGraphColumn column)
	{
//		super.add (column, "column");
		//column.addActionListener(this);
	}	//	add


	/**************************************************************************
	 * 	Paint Component
	 *	@param g graphics
	 */

	 public void chartMouseClicked(int index)
	 {
		 BarGraphColumn bgc = list.get(index);
		if (null == bgc)
			return;
		log.info(bgc.getName());
		MQuery query = null;
		if (bgc.getAchievement() != null) // Single Achievement
		{
			MAchievement a = bgc.getAchievement();
			query = MQuery.getEqualQuery("PA_Measure_ID", a.getPA_Measure_ID());
		}
		else if (bgc.getGoal() != null) // Multiple Achievements
		{
			MGoal goal = bgc.getGoal();
			query = MQuery.getEqualQuery("PA_Measure_ID", goal
					.getPA_Measure_ID());
		}
		else if (bgc.getMeasureCalc() != null) // Document
		{
			MMeasureCalc mc = bgc.getMeasureCalc();
			query = mc.getQuery(m_goal.getRestrictions(false), bgc
					.getMeasureDisplay(), bgc.getDate(), MRole.getDefault()); // logged
																				// in
																				// role
		}
		else if (bgc.getProjectType() != null) // Document
		{
			MProjectType pt = bgc.getProjectType();
			query = pt.getQuery(m_goal.getRestrictions(false), bgc
					.getMeasureDisplay(), bgc.getDate(), bgc.getID(), MRole
					.getDefault()); // logged in role
		}
		else if (bgc.getRequestType() != null) // Document
		{
			MRequestType rt = bgc.getRequestType();
			query = rt.getQuery(m_goal.getRestrictions(false), bgc
					.getMeasureDisplay(), bgc.getDate(), bgc.getID(), MRole
					.getDefault()); // logged in role
		}
		if (query != null)
			AEnv.zoom(query);
		else
			log.warning("Nothing to zoom to - " + bgc);
	}

    public void chartMouseMoved(ChartMouseEvent event) {}

	public BarGraphColumn[] getBarGraphColumnList()
	{
		BarGraphColumn[] array = new BarGraphColumn[list.size()];
		for (int i = 0; i < list.size(); i++){
			array[i] = list.get(i);
		}
		return array;
	}
}	//	BarGraph

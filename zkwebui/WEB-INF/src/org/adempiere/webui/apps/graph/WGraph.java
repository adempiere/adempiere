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

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import org.adempiere.apps.graph.GraphBuilder;
import org.adempiere.apps.graph.GraphColumn;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.model.MGoal;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Area;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Imagemap;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Toolbar;

/**
 * 	Bar Graph
 *
 *	@author hengsin
 */
public class WGraph extends Div implements IdSpace
{
	/**
	 *
	 */
	private static final long serialVersionUID = -975989183542113080L;

	private static final String ZOOM_KEY = "queryZoom";

	private boolean m_hideTitle;

	private Panel panel;

	private boolean m_showDetail;

	/**
	 * 	Constructor
	 */
	public WGraph()
	{
		super();
		builder = new GraphBuilder();
	}	//	BarGraph

	/**
	 * 	Constructor
	 *	@param goal goal
	 */
	public WGraph(MGoal goal)
	{
		this(goal, 0, false, false, false);
	}

	/**
	 * 	Constructor
	 *	@param goal goal
	 */
	public WGraph(MGoal goal, int zoom, boolean userSelection, boolean hideTitle, boolean showDetail)
	{
		this();
		builder.setMGoal(goal);
		builder.setYAxisLabel(goal.getName());
		builder.setXAxisLabel(goal.getXAxisText());
		m_userSelection = userSelection;
		zoomFactor = zoom;
		m_hideTitle = hideTitle;
		m_showDetail = showDetail;
		panel = new Panel();
		Borderlayout layout = new Borderlayout();
		if (m_showDetail)
		{
			layout = new Borderlayout();
			appendChild(layout);
			layout.setStyle("height: 100%; width: 100%; position: absolute;");
			Center center = new Center();
			layout.appendChild(center);
			center.appendChild(panel);
		}
		else
		{
			appendChild(panel);
		}
		loadData();
		if (m_showDetail)
		{
			Html html = new Html();
			html.setContent(renderGoal());
			East east = new East();
			layout.appendChild(east);
			east.appendChild(html);
		}
	}	//	BarGraph

	/** Zero/Zero Coordibate point	*/
	private Point			m_point0_0 = null;

	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger (WGraph.class);

	/** Y Axis Target Line Label */
	private String		m_Y_TargetLabel = null;

	/**
	 * 	Load Performance Data
	 */
	ArrayList<GraphColumn> list = new ArrayList<GraphColumn>();

	private GraphBuilder builder;

	private boolean m_userSelection;

	private int zoomFactor = 0;

	private void loadData()
	{
		list = builder.loadData();

		if (m_userSelection)
		{
			Toolbar toolbar = new Toolbar();
			panel.appendChild(toolbar);

			int AD_Reference_Value_ID = DB.getSQLValue(null, "SELECT AD_Reference_ID FROM AD_Reference WHERE Name = ?", "PA_Goal ChartType");
			MLookupInfo info = MLookupFactory.getLookup_List(Env.getLanguage(Env.getCtx()), AD_Reference_Value_ID);
			MLookup mLookup = new MLookup(info, 0);
			WTableDirEditor editor = new WTableDirEditor("ChartType", false, false, true, mLookup);
			toolbar.appendChild(editor.getComponent());
			editor.addValueChangeListener(new ValueChangeListener() {

				public void valueChange(ValueChangeEvent evt) {
					Object value = evt.getNewValue();
					if (value == null || value.toString().trim().length() == 0) return;
					JFreeChart chart = null;
					chart = builder.createChart(value.toString());
					if (chart != null)
						render(chart);
				}

			});
		}
		JFreeChart chart = builder.createChart(builder.getMGoal().getChartType());

		render(chart);
	}	// loadData

	private void render(JFreeChart chart) {
		ChartRenderingInfo info = new ChartRenderingInfo();
		int width = 560;
		int height = 400;
		if (zoomFactor  > 0)
		{
			width = width * zoomFactor / 100;
			height = height * zoomFactor / 100;
		}
		if (m_hideTitle)
		{
			chart.setTitle("");
		}
		BufferedImage bi = chart.createBufferedImage(width, height, BufferedImage.TRANSLUCENT, info);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);
			Imagemap myImage = new Imagemap();

			myImage.setContent(image);
			if (panel.getPanelchildren() != null) {
				panel.getPanelchildren().getChildren().clear();
				panel.getPanelchildren().appendChild(myImage);
			} else {
				Panelchildren pc = new Panelchildren();
				panel.appendChild(pc);
				pc.appendChild(myImage);
			}

			int count = 0;
			for(Iterator<?> it = info.getEntityCollection().getEntities().iterator(); it.hasNext(); )
			{
				ChartEntity entity = ( ChartEntity ) it.next();

				String key = null;
				if (entity instanceof CategoryItemEntity)
				{
					Comparable<?> colKey = ((CategoryItemEntity)entity).getColumnKey();
					if (colKey != null)
					{
						key = colKey.toString();
					}
				}
				else if (entity instanceof PieSectionEntity)
				{
					Comparable<?> sectionKey = ((PieSectionEntity)entity).getSectionKey();
					if (sectionKey != null)
					{
						key = sectionKey.toString();
					}
				}
				if (key == null)
				{
					continue;
				}

				Area area = new Area();
				myImage.appendChild(area);
				area.setCoords(entity.getShapeCoords());
				area.setShape(entity.getShapeType());
				area.setTooltiptext(entity.getToolTipText());
				area.setId("WG_"+key);
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
							String s = "WG_" + list.get(i).getLabel();
							if(areaId.equals(s))
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
	}
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
		return builder.getXAxisLabel();
	}	//	getX_AxisLabel

	/**
	 * @param axisLabel The x_AxisLabel to set.
	 */
	public void setX_AxisLabel (String axisLabel)
	{
		builder.setXAxisLabel(axisLabel);
	}	//	setX_AxisLabel

	/**
	 * @return Returns the y_AxisLabel.
	 */
	public String getY_AxisLabel ()
	{
		return builder.getYAxisLabel();
	}	//	getY_AxisLabel

	/**
	 * @param axisLabel The y_AxisLabel to set.
	 */
	public void setY_AxisLabel (String axisLabel)
	{
		builder.setYAxisLabel(axisLabel);
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


	/**************************************************************************
	 * 	Paint Component
	 *	@param g graphics
	 */

	 public void chartMouseClicked(int index)
	 {
		GraphColumn bgc = list.get(index);
		if (null == bgc)
			return;
		MQuery query = bgc.getMQuery(builder.getMGoal());
		if (query != null)
			AEnv.zoom(query);
		else
			log.warning("Nothing to zoom to - " + bgc);
	}

    public void chartMouseMoved(ChartMouseEvent event) {}

	public GraphColumn[] getGraphColumnList()
	{
		GraphColumn[] array = new GraphColumn[list.size()];
		for (int i = 0; i < list.size(); i++){
			array[i] = list.get(i);
		}
		return array;
	}

	public int getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(int zoomFactor) {
		this.zoomFactor = zoomFactor;
	}

	protected String renderGoal()
	{
		String output = "<div class=\"pa-content\">";

		output += "<table class=\"pa-dataGrid\">\n";
		output += "<tr><td class=\"pa-label\">Target</td><td colspan=\"2\" class=\"pa-tdcontent\">"
				+ builder.getMGoal().getMeasureTarget().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "</td></tr>\n";
		output += "<tr><td class=\"pa-label\">Actual</td><td colspan=\"2\" class=\"pa-tdcontent\">"
				+ builder.getMGoal().getMeasureActual().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "</td></tr>\n";

		GraphColumn[] bList = getGraphColumnList();
		output += "<tr><td rowspan=\"" + bList.length
				+ "\" class=\"pa-label\" valign=\"top\">"
				+ builder.getMGoal().getXAxisText() + "</td>\n";

		for (int k = 0; k < bList.length; k++)
		{
			GraphColumn bgc = bList[k];
			if (k > 0)
				output += "<tr>";

			output += "<td class=\"pa-tdlabel\">" + bgc.getLabel()
					+ "</td><td  class=\"pa-tdvalue\">";
			if (bgc.getMQuery(builder.getMGoal()) != null) {
				Button btn = new Button();
				btn.setId(String.valueOf(ZOOM_KEY + k));
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
						Component comp = event.getTarget();
						String id = comp.getId();
		            	if(id.startsWith(ZOOM_KEY))
		            	{
		            		String ss = id.substring(ZOOM_KEY.length());
			        		int index = Integer.parseInt(String.valueOf(ss));
			        		GraphColumn[] colList = getGraphColumnList();
			            	if ((index >= 0) && (index < colList.length))
			                	AEnv.zoom(colList[index].getMQuery(builder.getMGoal()));
		            	}
					}

				});
				btn.setVisible(false);
				appendChild(btn);

				BigDecimal value = new BigDecimal(bgc.getValue());
				output += "<a class=\"pa-hrefNode\" id=\"" + ZOOM_KEY +
						+ k
						+ "\" href=\"javascript:;\" onclick=\"$('" + btn.getUuid() + "').click()\">"
						+ value.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
						+ "</a>\n";

			} else {
				output += bgc.getValue();
			}
			output += "</td></tr>";
		}
		output += "</tr>"
				+ "<tr><td colspan=\"3\">"
				+ builder.getMGoal().getDescription()
				+ "<br>"
				+ stripHtml(builder.getMGoal().getColorSchema()
						.getDescription(), true) + "</td></tr>"
				+ "</table>\n";

		output += "</div>";
		bList = null;

		return output;
	}

	protected String stripHtml(String htmlString, boolean all) {
		htmlString = htmlString
		.replace("<html>", "")
		.replace("</html>", "")
		.replace("<body>", "")
		.replace("</body>", "")
		.replace("<head>", "")
		.replace("</head>", "");

		if (all)
			htmlString = htmlString
			.replace(">", "&gt;")
			.replace("<", "&lt;");
		return htmlString;
	}
}	//	BarGraph

package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Properties;

import javax.swing.border.Border;

import org.adempiere.apps.graph.Graph;
import org.adempiere.apps.graph.GraphBuilder;
import org.adempiere.apps.graph.GraphColumn;
import org.compiere.apps.AEnv;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MCharge;
import org.compiere.model.MChart;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MQuery;
import org.compiere.model.X_AD_Chart;
import org.compiere.model.X_PA_Goal;
import org.compiere.swing.CPanel;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;

public class VChart extends CPanel implements ChartMouseListener, VEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChartPanel chartPanel;
	private MChart chartModel;

	/**
	 * 	Constructor
	 */
	public VChart()
	{
		super();
		this.setLayout(new BorderLayout());
	}	//	BarGraph
	
	public VChart(int AD_Chart_ID, int windowNo) {
		this();
		chartModel = new MChart(Env.getCtx(), AD_Chart_ID, null);
		chartModel.setWindowNo(windowNo);
		//createChart();
	}

	public void createChart()
	{
		JFreeChart chart = createChart(chartModel.getChartType());
		if (chartPanel != null)
			remove(chartPanel);

		chartModel.loadData();
		chartPanel = new ChartPanel(chart);
		Dimension size = getSize();
		size.height= chartModel.getWinHeight();
		chartPanel.setPreferredSize(size);
		chartPanel.addChartMouseListener(this);
		add(chartPanel,BorderLayout.CENTER);

		this.setMinimumSize(size);
	}	//	

	/**
	 *
	 * @param type
	 * @return JFreeChart
	 */
	public JFreeChart createChart(String type) {
		if(MChart.CHARTTYPE_BarChart.equals(type))
		{
			return createBarChart();
		}
		else if (MChart.CHARTTYPE_3DBarChart.equals(type))
		{
			return create3DBarChart();
		}
		else if (MChart.CHARTTYPE_StackedBarChart.equals(type))
		{
			return createStackedBarChart();
		}
		else if (MChart.CHARTTYPE_3DStackedBarChart.equals(type))
		{
			return create3DStackedBarChart();
		}
		else if (MChart.CHARTTYPE_3DPieChart.equals(type))
		{
			return create3DPieChart();
		}
		else if (MChart.CHARTTYPE_PieChart.equals(type))
		{
			return createPieChart();
		}
		else if (MChart.CHARTTYPE_3DLineChart.equals(type))
		{
			return create3DLineChart();
		}
		else if (MChart.CHARTTYPE_AreaChart.equals(type))
		{
			return createAreaChart();
		}
		else if (MChart.CHARTTYPE_StackedAreaChart.equals(type))
		{
			return createStackedAreaChart();
		}
		else if (MChart.CHARTTYPE_LineChart.equals(type))
		{
			return createLineChart();
		}
		else if (MChart.CHARTTYPE_RingChart.equals(type))
		{
			return createRingChart();
		}
		else if (MChart.CHARTTYPE_WaterfallChart.equals(type))
		{
			return createWaterfallChart();
		}
		else
		{
			throw new IllegalArgumentException("unknown chart type=" + type);
		}
	}

	private JFreeChart createWaterfallChart() {
		JFreeChart chart = ChartFactory.createWaterfallChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
					? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);

		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createRingChart() {
		final JFreeChart chart = ChartFactory.createRingChart(chartModel.getName(),
				chartModel.getPieDataset(), chartModel.isDisplayLegend(), true, true);

		return chart;
	}

	private JFreeChart createPieChart() {
		final JFreeChart chart = ChartFactory.createPieChart(chartModel.getName(),
				chartModel.getPieDataset(), false, true, true);

		return chart;
	}
	
	private JFreeChart create3DPieChart() {
		final JFreeChart chart = ChartFactory.createPieChart3D(chartModel.getName(),
				chartModel.getPieDataset(), false, true, true);

		return chart;
	}
	
	private JFreeChart createBarChart() {
		JFreeChart chart = ChartFactory.createBarChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);

        BarRenderer renderer = new BarRenderer();
        renderer.setBarPainter(new StandardBarPainter());

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRenderer(renderer);
		
		setupCategoryChart(chart);
		return chart;
	}
	
	private JFreeChart create3DBarChart() {
		JFreeChart chart = ChartFactory.createBarChart3D(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		
		setupCategoryChart(chart);
		return chart;
	}
	
	private JFreeChart createStackedBarChart() {
		JFreeChart chart = ChartFactory.createStackedBarChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);


        BarRenderer renderer = new BarRenderer();
        renderer.setBarPainter(new StandardBarPainter());

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRenderer(renderer);
        
		setupCategoryChart(chart);
		return chart;
	}
	private JFreeChart create3DStackedBarChart() {
		JFreeChart chart = ChartFactory.createStackedBarChart3D(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);

		setupCategoryChart(chart);
		return chart;
	}


	private JFreeChart createAreaChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createAreaChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);

		setupCategoryChart(chart);
		return chart;
	}
	
	private JFreeChart createStackedAreaChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createStackedAreaChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);

		setupCategoryChart(chart);
		return chart;
	}
	
	private JFreeChart createLineChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		

		setupCategoryChart(chart);
		return chart;
	}
	
	private JFreeChart create3DLineChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart3D(
				chartModel.getName(),         // chart title
				chartModel.getDomainLabel(),               // domain axis label
				chartModel.getRangeLabel(),                  // range axis label
				chartModel.getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(chartModel.getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				chartModel.isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		

		setupCategoryChart(chart);
		return chart;
	}
		
	private void setupCategoryChart(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.BLUE);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesPaint(3, Color.GREEN);
		renderer.setSeriesPaint(4, Color.ORANGE);
		renderer.setSeriesPaint(5, Color.CYAN);
		renderer.setSeriesPaint(6, Color.MAGENTA);
		renderer.setSeriesPaint(7, Color.GRAY);
		renderer.setSeriesPaint(8, Color.PINK);
		
		plot.setRenderer(renderer);
	}



	
	@Override
	public void addActionListener(ActionListener listener) {}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {}

	@Override
	public void dispose() {}

	@Override
	public String getName() {return null;}

	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		
		if ((event.getEntity()!=null) && (event.getTrigger().getClickCount() > 1))
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try
			{
				ChartEntity entity = event.getEntity();
				String key = null;
				String category = null;
				if (entity instanceof CategoryItemEntity)
				{
					CategoryItemEntity item = ((CategoryItemEntity)entity);
					Comparable<?> colKey = item.getColumnKey();
					Comparable<?> rowKey = item.getRowKey();
					if (colKey != null && rowKey !=null)
					{
						key = colKey.toString();
						category = rowKey.toString();
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
				
				if ( key == null )
					return;
				
				MQuery query = chartModel.getQuery(key, category);
				if (query != null)
					AEnv.zoom(query);
		
			}
			finally
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent arg0) {}

	@Override
	public void setField(GridField mField) {}

	@Override
	public String getDisplay() {return null;}

	@Override
	public Object getValue() {return null;}

	@Override
	public boolean isMandatory() {return false;}

	@Override
	public boolean isReadWrite() {return false;}

	@Override
	public void setBackground(boolean error) {}

	@Override
	public void setMandatory(boolean mandatory) {}

	@Override
	public void setReadWrite(boolean rw) {}

	@Override
	public void setValue(Object value) {}
	
	@Override
	public GridField getField() { return null; }

}

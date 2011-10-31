package org.compiere.model;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;

public class MChart extends X_AD_Chart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8851465915516536910L;
	
	private int windowNo=0;
	private Dataset dataset;
	private HashMap<String,MQuery> queries;

	public MChart(Properties ctx, int AD_Chart_ID, String trxName) {
		super(ctx, AD_Chart_ID, trxName);
	}

	public MChart(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void loadData() {
		queries = new HashMap<String,MQuery>();
		for ( MChartDatasource ds : getDatasources() )
		{
			ds.addData(this);
		}
	}

	public CategoryDataset getCategoryDataset() {
		dataset = new DefaultCategoryDataset();
		loadData();
		return (CategoryDataset) dataset;
	}
	
	public IntervalXYDataset getXYDataset() {
		dataset = new TimeSeriesCollection();
		loadData();
		return (IntervalXYDataset) dataset;
	}

	public PieDataset getPieDataset() {
		dataset = new DefaultPieDataset();
		loadData();
		return (PieDataset) dataset;
	}
	
	public Dataset getDataset() {
		return dataset;
	}
	
	private List<MChartDatasource> getDatasources() {
		
		return new Query(getCtx(), MChartDatasource.Table_Name, MChart.COLUMNNAME_AD_Chart_ID + "=?", null)
		.setParameters(getAD_Chart_ID()).setOnlyActiveRecords(true).list();
	}
	
	public HashMap<String, MQuery> getQueries() {
		return queries;
	}

	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;		
	}
	
	public int getWindowNo() {
		return windowNo;
	}

	public MQuery getQuery(String key) {
		

		if ( queries.containsKey(key) )
		{
			return queries.get(key);
		}
		
		return null;
	}

	/**
	 *
	 * @param type
	 * @return JFreeChart
	 */
	public JFreeChart createChart() {
		
		String type = getChartType();
		
		if(MChart.CHARTTYPE_BarChart.equals(type))
		{
			if ( isTimeSeries())
			{
				return createXYBarChart();
			}
			return createBarChart();
		}
		else if (MChart.CHARTTYPE_3DBarChart.equals(type))
		{
			return create3DBarChart();
		}
		else if (MChart.CHARTTYPE_StackedBarChart.equals(type))
		{

			if ( isTimeSeries())
				return createXYBarChart();
			
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
			if ( isTimeSeries() )
				return createTimeSeriesChart();
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

	private JFreeChart createXYBarChart() {
		JFreeChart chart = ChartFactory.createXYBarChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				true,
				getRangeLabel(),                  // range axis label
				getXYDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		
		return chart;
	}
	
	private JFreeChart createTimeSeriesChart() {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getXYDataset(),                  // data
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		
		return chart;
	}
	
	private JFreeChart createWaterfallChart() {
		JFreeChart chart = ChartFactory.createWaterfallChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
					? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createRingChart() {
		final JFreeChart chart = ChartFactory.createRingChart(getName(),
				getPieDataset(), isDisplayLegend(), true, true);
	
		return chart;
	}

	private JFreeChart createPieChart() {
		final JFreeChart chart = ChartFactory.createPieChart(getName(),
				getPieDataset(), false, true, true);
	
		return chart;
	}

	private JFreeChart create3DPieChart() {
		final JFreeChart chart = ChartFactory.createPieChart3D(getName(),
				getPieDataset(), false, true, true);
	
		return chart;
	}

	private JFreeChart createBarChart() {
		JFreeChart chart = ChartFactory.createBarChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
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
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createStackedBarChart() {
		JFreeChart chart = ChartFactory.createStackedBarChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
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
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createAreaChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createAreaChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createStackedAreaChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createStackedAreaChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
	
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart createLineChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		
	
		setupCategoryChart(chart);
		return chart;
	}

	private JFreeChart create3DLineChart() {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart3D(
				getName(),         // chart title
				getDomainLabel(),               // domain axis label
				getRangeLabel(),                  // range axis label
				getCategoryDataset(),                  // data
				X_AD_Chart.CHARTORIENTATION_Horizontal.equals(getChartOrientation()) 
				? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL, // orientation
				isDisplayLegend(),                     // include legend
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

}

package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class MChart extends X_AD_Chart {

	private int windowNo=0;
	private DefaultCategoryDataset categoryData = new DefaultCategoryDataset();
	private DefaultPieDataset pieData;

	public MChart(Properties ctx, int AD_Chart_ID, String trxName) {
		super(ctx, AD_Chart_ID, trxName);
	}

	public MChart(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void loadData() {
		categoryData = new DefaultCategoryDataset();
		pieData = new DefaultPieDataset();
		for ( MChartDatasource ds : getDatasources() )
		{
			ds.addData(this);
		}
	}

	public CategoryDataset getCategoryDataset() {
		return categoryData;
	}

	private List<MChartDatasource> getDatasources() {
		
		return new Query(getCtx(), MChartDatasource.Table_Name, MChart.COLUMNNAME_AD_Chart_ID + "=?", null)
		.setParameters(getAD_Chart_ID()).setOnlyActiveRecords(true).list();
	}
	
	private MChartDatasource getDatasourceByName(String name) {
		
		return new Query(getCtx(), MChartDatasource.Table_Name,
				MChart.COLUMNNAME_AD_Chart_ID + "=? AND " + MChart.COLUMNNAME_Name + "=?", null)
		.setParameters(getAD_Chart_ID(), name).setOnlyActiveRecords(true).first();
	}

	public PieDataset getPieDataset() {
		return pieData;
	}

	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;		
	}
	
	public int getWindowNo() {
		return windowNo;
	}

	public MQuery getQuery(String key, String category) {
		for ( MChartDatasource ds : getDatasources() )
		{
			if ( category == null || ds.getName().equals(category));
			return ds.getZoomQuery(this, key);
		}
		return null;
	}

}

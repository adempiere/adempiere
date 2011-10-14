package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

import org.compiere.apps.AEnv;
import org.compiere.model.GridField;
import org.compiere.model.MChart;
import org.compiere.model.MQuery;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;

public class VChart extends CPanel implements ChartMouseListener, VEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChartPanel chartPanel;
	MChart chartModel;
	/**
	 * 	Constructor
	 */
	public VChart()
	{
		super();
		this.setLayout(new BorderLayout());
	}	//	Chart
	
	public VChart(int AD_Chart_ID, int windowNo) {
		this();
		chartModel = new MChart(Env.getCtx(), AD_Chart_ID, null);
		chartModel.setWindowNo(windowNo);
		//createChart();
	}

	public void createChart()
	{
		JFreeChart chart = chartModel.createChart();
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
	}	//	BarGraph
	

	@Override
	public void addActionListener(ActionListener listener) {}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {}

	@Override
	public void dispose() {}

	@Override
	public String getName() {return chartModel.getName();}

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

/******************************************************************************
 * Copyright (C) 2007 Low Heng Sin  All Rights Reserved.                      *
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

package org.adempiere.webui.editor;


import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.compiere.model.GridField;
import org.compiere.model.MChart;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Area;
import org.zkoss.zul.Imagemap;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;

/**
 * This class is based on org.compiere.grid.ed.WImageEditor and WGraph written by Low Heng Sin.
 * @author Low Heng Sin
 * 
 * Modifications - chart display
 * @author Paul Bowden, Adaxa Pty Ltd
 */
public class WChartEditor extends WEditor
{
    private static final String[] LISTENER_EVENTS = {Events.ON_CLICK};
    
	private static final CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(WChartEditor.class);
    }
    
	private MChart  chartModel = null;
    
    /**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WChartEditor.class);
    
    public WChartEditor(GridField gridField, int windowNo)
    {
        super(new Panel(), gridField);
		chartModel = new MChart(Env.getCtx(), gridField.getAD_Chart_ID(), null);
		chartModel.setWindowNo(windowNo);
        init();
        
    }

    public void createChart() {

        render(chartModel.createChart());
    }
    
    @Override
    public Panel getComponent() {
    	return (Panel) component;
    }
    
	private void render(JFreeChart chart) {
		ChartRenderingInfo info = new ChartRenderingInfo();
		int width = 400;
		int height = chartModel.getWinHeight();
		BufferedImage bi = chart.createBufferedImage(width, height,
				BufferedImage.TRANSLUCENT, info);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);
			Imagemap myImage = new Imagemap();

			Panel panel = getComponent();
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
			for (Iterator<?> it = info.getEntityCollection().getEntities()
					.iterator(); it.hasNext();) {
				ChartEntity entity = (ChartEntity) it.next();
				
				String key = null;
				String seriesName = null;
				if (entity instanceof CategoryItemEntity)
				{
					CategoryItemEntity item = ((CategoryItemEntity)entity);
					Comparable<?> colKey = item.getColumnKey();
					Comparable<?> rowKey = item.getRowKey();
					if (colKey != null && rowKey !=null)
					{
						key = colKey.toString();
						seriesName = rowKey.toString();
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
				if (entity instanceof XYItemEntity)
				{
					XYItemEntity item = ((XYItemEntity)entity);
					if ( item.getDataset() instanceof TimeSeriesCollection )
					{
						TimeSeriesCollection data = (TimeSeriesCollection) item.getDataset();
						TimeSeries series = data.getSeries(item.getSeriesIndex());
						TimeSeriesDataItem dataitem = series.getDataItem(item.getItem());
						seriesName = series.getKey().toString();
						key = dataitem.getPeriod().toString();
					}
				}
				
				if ( key == null )
					continue;

				Area area = new Area();
				myImage.appendChild(area);
				area.setCoords(entity.getShapeCoords());
				area.setShape(entity.getShapeType());
				area.setTooltiptext(entity.getToolTipText());
				area.setId(count+"_WG__" + seriesName + "__" + key);
				count++;
			}

			myImage.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					MouseEvent me = (MouseEvent) event;
					String areaId = me.getArea();
					if (areaId != null) {
						String[] strs = areaId.split("__");
						if (strs.length == 3)
						{
							chartMouseClicked(strs[2], strs[1]);
						}
					}
				}
			});
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
		
		
	}
	
	public void chartMouseClicked(String key, String category) {
		MQuery query = chartModel.getQuery("null".equals(category) ? key : category + "__" + key);
		if (query != null)
			AEnv.zoom(query);
	}
    
    private void init()
    {
    	AImage img = null;
    }

     @Override
    public String getDisplay()
    {
    	 return chartModel.getName();
    }

    @Override
    public Object getValue()
    {
    	return null;
    }

    @Override
    public boolean isMandatory()
    {
        return false;
    }
   
    
    @Override
    public void setMandatory(boolean mandatory)
    {
        ;
    }
    
    @Override
	public boolean isReadWrite() {
		return true;
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		
	}

	@Override
    public void setValue(Object value)
    {
    	
    }
    
    @Override
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

	public void onEvent(Event event) throws Exception 
	{
		if (Events.ON_CLICK.equals(event.getName()))
		{
			
			
		}
	}

	@Override
	public void dynamicDisplay() {
		super.dynamicDisplay();
		createChart();
		
	}
}

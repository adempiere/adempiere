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
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;

import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.window.WImageDialog;
import org.compiere.model.GridField;
import org.compiere.model.MChart;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Area;
import org.zkoss.zul.Image;
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

		chartModel.loadData();
        render(chartModel.createChart());
    }
    
    @Override
    public Panel getComponent() {
    	return (Panel) component;
    }
    
	private void render(JFreeChart chart) {
		ChartRenderingInfo info = new ChartRenderingInfo();
		int width = 560;
		int height = 400;
		BufferedImage bi = chart.createBufferedImage(width, height,
				BufferedImage.TRANSLUCENT, info);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);
			Imagemap myImage = new Imagemap();

			myImage.setContent(image);

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
/*
			int count = 0;
			for (Iterator<?> it = info.getEntityCollection().getEntities()
					.iterator(); it.hasNext();) {
				ChartEntity entity = (ChartEntity) it.next();

				String key = null;
				if (entity instanceof CategoryItemEntity) {
					Comparable<?> colKey = ((CategoryItemEntity) entity)
							.getColumnKey();
					if (colKey != null) {
						key = colKey.toString();
					}
				} else if (entity instanceof PieSectionEntity) {
					Comparable<?> sectionKey = ((PieSectionEntity) entity)
							.getSectionKey();
					if (sectionKey != null) {
						key = sectionKey.toString();
					}
				}
				if (key == null) {
					continue;
				}

				Area area = new Area();
				myImage.appendChild(area);
				area.setCoords(entity.getShapeCoords());
				area.setShape(entity.getShapeType());
				area.setTooltiptext(entity.getToolTipText());
				area.setId(count+"_WG_" + key);
				count++;
			}

			myImage.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					MouseEvent me = (MouseEvent) event;
					String areaId = me.getArea();
					if (areaId != null) {
						for (int i = 0; i < list.size(); i++) {
							String s = "_WG_" + list.get(i).getLabel();
							if (areaId.endsWith(s)) {
								chartMouseClicked(i);
								return;
							}
						}
					}
				}
			});
			*/
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
		
		
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

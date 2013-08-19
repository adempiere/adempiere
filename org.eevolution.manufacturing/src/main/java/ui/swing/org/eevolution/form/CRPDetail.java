/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.              *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.Color;

import org.compiere.model.MResource;
import org.compiere.model.MUOM;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.tools.worker.SingleWorker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * Generate the Chart to show the Capacity Resource Planning in detail
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author alberto.juarez@e-evolution.com, www.e-evolution.com
 */
public class CRPDetail {

	class LabelGenerator extends StandardCategoryItemLabelGenerator {

		public String generateItemLabel(CategoryDataset categorydataset, int i,
				int j) {

			return categorydataset.getRowKey(i).toString();
		}

	}

	public SingleWorker worker;
	public static CLogger log = CLogger.getCLogger(CRPDetail.class);

	/**
	 * Create Chart based on UOM
	 * 
	 * @param dataset
	 * @param title
	 * @param uom
	 * @return JFreeChart Chart based On UOM
	 */
	public JFreeChart createChart(CategoryDataset dataset, String title,
			MUOM uom) {

		JFreeChart chart = ChartFactory
				.createBarChart3D(title,
						Msg.translate(Env.getCtx(), "Day"), // X-Axis label
						Msg.translate(Env.getCtx(),
								(uom == null) ? "" : uom.getName()), // Y-Axis
																		// label
						dataset, // Dataset
						PlotOrientation.VERTICAL, // orientation
						true, // include legend
						true, // tooltips?
						false // URLs?
				);

		chart.setBackgroundPaint(Color.WHITE);
		chart.setAntiAlias(true);
		chart.setBorderVisible(true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.GRAY);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.GRAY);

		BarRenderer3D barrenderer = (BarRenderer3D) plot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		barrenderer.setBaseItemLabelGenerator(new LabelGenerator());
		barrenderer.setBaseItemLabelsVisible(true);
		barrenderer.setSeriesPaint(0, new Color(10, 80, 150, 128));
		barrenderer.setSeriesPaint(1, new Color(180, 60, 50, 128));

		ItemLabelPosition itemlabelposition = new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		barrenderer.setPositiveItemLabelPosition(itemlabelposition);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));
		return chart;
	}
	
	public MUOM getSourceUOM(Object value) {
		MResource r = getResource(value);
		int uom_id = r.getResourceType().getC_UOM_ID();
		return (uom_id > 0) ? MUOM.get(Env.getCtx(), uom_id) : null;
	}

	public MResource getResource(Object value) {
		MResource r = null;
		if (value != null) {
			r = MResource.get(Env.getCtx(),
					((Integer)value).intValue());
		}
		return r;
	}

	public MUOM getTargetUOM(Object value) {
		MUOM u = null;
		if (value!= null) {
			u = MUOM.get(Env.getCtx(),
					((Integer) value).intValue());
		}
		return u;
	}
}

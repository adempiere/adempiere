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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MResource;
import org.compiere.model.MUOM;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.crp.CRPModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Row;

/**
 * ZK Component to show Capacity Resource Planning
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author alberto.juarez@e-evolution.com, www.e-evolution.com
 */
public class WCRP extends CRP implements IFormController, EventListener {

	CustomForm m_frame = new CustomForm();
	Borderlayout mainLayout = new Borderlayout();

	private Grid northPanel = GridFactory.newGridLayout();

	private Hbox centerPanel = new Hbox();

	private Borderlayout centerLayout = new Borderlayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Hashtable hash = new Hashtable();
	private WSearchEditor resource = null;
	private Label resourceLabel = new Label();

	private WDateEditor dateFrom = new WDateEditor("DateFrom", true, false,
			true, "DateFrom");
	private Label dateFromLabel = new Label();

	private Hbox chartPanel = new Hbox();
	private Image chart = new Image();

	protected CRPModel model;
	public WCRP() {
		m_frame.setWidth("99%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");
		init();
	}

	public void dispose() {
		SessionManager.getAppDesktop().closeWindow(m_WindowNo);
	}

	private void fillPicks() throws Exception {

		Properties ctx = Env.getCtx();
		MLookup resourceL = MLookupFactory.get(ctx, m_WindowNo, 0,
				MColumn.getColumn_ID(I_M_Product.Table_Name, "S_Resource_ID"),
				DisplayType.TableDir);
		resource = new WSearchEditor("S_Resource_ID", false, false, true,
				resourceL);
	}

	@Override
	public CustomForm getForm() {
		return m_frame;
	}

	public void init() {

		try {
			fillPicks();
			jbInit();

			North north = new North();
			north.appendChild(northPanel);
			mainLayout.appendChild(north);

			Center center = new Center();
			center.appendChild(centerPanel);
			mainLayout.appendChild(center);

			South south = new South();
			south.appendChild(confirmPanel);
			mainLayout.appendChild(south);

		} catch (Exception e) {
			log.log(Level.SEVERE, "VCRP.init", e);
		}
	}

	private void jbInit() throws Exception {
		resourceLabel.setText(Msg.translate(Env.getCtx(), "S_Resource_ID"));
		dateFromLabel.setText(Msg.translate(Env.getCtx(), "DateFrom"));

		Rows rows = new Rows();
		Row row = null;

		rows.setParent(northPanel);
		row = rows.newRow();
		row.appendChild(resourceLabel.rightAlign());
		row.appendChild(resource.getComponent());
		row.appendChild(dateFromLabel.rightAlign());
		row.appendChild(dateFrom.getComponent());
		centerPanel.appendChild(chartPanel);

		JFreeChart jchart = ChartFactory.createBarChart3D("",
				Msg.translate(Env.getCtx(), "Days"), // X-Axis label
				Msg.translate(Env.getCtx(), "Hours"), // Y-Axis label
				new DefaultCategoryDataset(), // Dataset
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		renderChart(jchart);

		confirmPanel.addActionListener(this);
	}

	@Override
	public void onEvent(Event event) throws Exception {
		String cmd = event.getTarget().getId();

		if (cmd.equals(ConfirmPanel.A_OK)) {
			
			Timestamp date = null;
			if(dateFrom.getValue() != null)
				date = (Timestamp) dateFrom.getValue();
			
			int S_Resource_ID = 0;
			if(resource.getValue() != null)
				 S_Resource_ID = ((Integer) resource.getValue()).intValue();

			if (date != null && S_Resource_ID != 0) {
				MResource r = MResource.get(Env.getCtx(), S_Resource_ID);

				int uom_id = r.getResourceType().getC_UOM_ID();
				MUOM uom = MUOM.get(Env.getCtx(), uom_id);

				CategoryDataset dataset = null;
				if (uom.isHour()) {
					dataset = createDataset(date, r);
				} else {
					dataset = createWeightDataset(date, r);
				}
				String title = r.getName() != null ? r.getName() : "";
				title = title + " " + r.getDescription() != null ? r
						.getDescription() : "";

				JFreeChart jfreechart = createChart(dataset, title, uom);
				renderChart(jfreechart);
			}
		}
		if (cmd.equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		}
	}

	private void renderChart(JFreeChart jchart) {

		BufferedImage bi = jchart.createBufferedImage(700, 500,
				Transparency.TRANSLUCENT, null);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);
			chartPanel.removeChild(chart);

			chart = new Image();
			chart.setContent(image);
			chartPanel.appendChild(chart);
			chartPanel.setVisible(true);
		} catch (Exception e) {
		}
	}

}

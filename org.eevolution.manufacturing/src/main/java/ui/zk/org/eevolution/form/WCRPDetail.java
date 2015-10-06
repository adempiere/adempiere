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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MResource;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.crp.CRPDatasetFactory;
import org.eevolution.form.crp.CRPModel;
import org.eevolution.tools.worker.SingleWorker;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.West;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Row;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;

/**
 * Capacity Requirement Planning Form
 * @author victor.perez@e-evolution.com, www-e-evolution.com
 * @author alberto.juarez@e-evolution.com, www-e-evolution.com
 */
public class WCRPDetail extends CRPDetail implements IFormController,
		EventListener {

	CustomForm m_frame = new CustomForm();

	@Override
	public void onEvent(Event event) throws Exception {

		String cmd = event.getTarget().getId();

		if (cmd.equals(ConfirmPanel.A_OK)) {

			handleActionEvent(event);
		}
		if (cmd.equals(ConfirmPanel.A_CANCEL)) {

			dispose();
		}
	}

	private WSearchEditor resource;
	private WDateEditor dateFrom;
	private WDateEditor dateTo;
	private Hbox chartPanel = new Hbox();
	private Image chart = new Image();
	private Hbox treePanel = new Hbox();
	private Tree tree = new Tree();
	private Center center = new Center();
	private West west = new West();

	private Borderlayout mainLayout = new Borderlayout();

	private SingleWorker worker;

	protected CRPModel model;

	public WCRPDetail() {

		m_frame.setWidth("99%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");

		init();
	}

	public void init() {

		fillPicks();
		jbInit();
	}

	private void jbInit() {

		dateFrom = new WDateEditor("DateFrom", true, false, true, "DateFrom");
		dateTo = new WDateEditor("DateTo", true, false, true, "DateTo");

		Rows rows = new Rows();
		Row row = null;

		new GridFactory();
		Grid northPanel = GridFactory.newGridLayout();

		rows.setParent(northPanel);

		row = rows.newRow();
		row.appendChild(new Label(Msg.translate(Env.getCtx(), "S_Resource_ID"))
				.rightAlign());
		row.appendChild(resource.getComponent());
		row.appendChild(new Label(Msg.translate(Env.getCtx(), "DateFrom"))
				.rightAlign());
		row.appendChild(dateFrom.getComponent());
		row.appendChild(new Label(Msg.translate(Env.getCtx(), "DateTo"))
				.rightAlign());
		row.appendChild(dateTo.getComponent());

		ConfirmPanel confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);

		North north = new North();
		north.appendChild(northPanel);
		mainLayout.appendChild(north);

		//West west = new West();
		//chartPanel.setWidth("400");
		//west.appendChild(chartPanel);
		//west.setSplittable(true);
		//west.setAutoscroll(true);
		//mainLayout.appendChild(west);

		South south = new South();
		south.appendChild(confirmPanel);
		mainLayout.appendChild(south);

	}

	private void fillPicks() {

		Properties ctx = Env.getCtx();

		// Hardcoded Column ID - Manufacturing Resource ID
		MLookup resourceL = MLookupFactory.get(ctx, 0, 0,
				MColumn.getColumn_ID(I_S_Resource.Table_Name, "S_Resource_ID"),
				DisplayType.TableDir);
		resource = new WSearchEditor("S_Resource_ID", false, false, true,
				resourceL);

	}

	private void handleActionEvent(Event e) {

		Timestamp df = getDateFrom();
		Timestamp dt = getDateTo();
		MResource r = getResource(resource.getValue());

		if (df != null && dt != null && r != null) {

			model = CRPDatasetFactory.get(df, dt, r);

			JFreeChart jfreechart = createChart(model.getDataset(),
					getChartTitle(), getSourceUOM(resource.getValue()));
			renderChart(jfreechart);

			tree = getTree();

			mainLayout.removeChild(center);
			treePanel = new Hbox();
			treePanel.appendChild(tree);
			tree.setStyle("border: none");

			center = new Center();
			center.appendChild(treePanel);
			center.setAutoscroll(true);
			mainLayout.appendChild(center);
		}
	}

	private Tree getTree() {
		Tree tree = new Tree();

		List<String> nodes = model.getDataset().getColumnKeys();
		DefaultTreeNode root = new DefaultTreeNode(getResource(resource.getValue()).getName(),
				new ArrayList());
		for (String node : nodes) {
			root.getChildren().add(new DefaultTreeNode(node, new ArrayList()));
		}

		Treecols treeCols = new Treecols();
		tree.appendChild(treeCols);
		Treecol treeCol = new Treecol();
		treeCols.appendChild(treeCol);

		SimpleTreeModel model = new SimpleTreeModel(root);
		tree.setPageSize(-1);
		tree.setTreeitemRenderer(model);
		tree.setModel(model);

		return tree;
	}

	private String getChartTitle() {

		MResource r = getResource(resource.getValue());
		String title = r.getName() != null ? r.getName() : "";
		title = title + " " + r.getDescription() != null ? r.getDescription()
				: "";

		return title;
	}

	public Timestamp getDateFrom() {

		Timestamp t = null;

		if (dateFrom.getValue() != null) {

			t = (Timestamp) dateFrom.getValue();
		}

		return t;
	}

	public Timestamp getDateTo() {

		Timestamp t = null;

		if (dateTo.getValue() != null) {

			t = (Timestamp) dateTo.getValue();
		}

		return t;
	}
	
	private void renderChart(JFreeChart jchart) {

		BufferedImage bi = jchart.createBufferedImage(700, 500,
				Transparency.TRANSLUCENT, null);
		try {
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

			AImage image = new AImage("", bytes);			
			mainLayout.removeChild(west);
			chartPanel = new Hbox();
			chart = new Image();
			chart.setContent(image);
			chartPanel.appendChild(chart);

			west = new West();
			west.appendChild(chartPanel);
			west.setSplittable(true);
			west.setSize("70%");
			west.setAutoscroll(true);
			west.setOpen(true);
			mainLayout.appendChild(west);
			

		} catch (Exception e) {
			log.log(Level.SEVERE, "WCRP.init", e.getMessage());
		}
	}

	public void dispose() {
		m_frame.dispose();
	}

	@Override
	public CustomForm getForm() {
		return m_frame;
	}
}

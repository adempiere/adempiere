/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MResource;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.action.PopupAction;
import org.eevolution.form.action.ZoomMenuAction;
import org.eevolution.form.crp.CRPDatasetFactory;
import org.eevolution.form.crp.CRPModel;
import org.eevolution.model.MPPOrderNode;
import org.eevolution.tools.swing.SwingTool;
import org.eevolution.tools.worker.SingleWorker;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * Capacity Requirement Planning Form
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @author victor.perez@e-evolution.com, www-e-evolution.com
 */
public class VCRPDetail extends CRPDetail implements FormPanel {

	CAbstractForm m_form = new CAbstractForm() {};

	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {

				SwingTool.setCursorsFromParent(m_form.getWindow(), true);

				final ActionEvent evt = e;
				worker = new SingleWorker() {

					@Override
					protected Object doIt() {

						handleActionEvent(evt);
						return null;
					}
				};
				worker.start();
			}
			if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {

				dispose();
			}
		}
	}

	class TreeHandler extends MouseInputAdapter implements
			TreeSelectionListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (model.getTree().getPathForLocation(e.getX(), e.getY()) == null) {

				return;
			}

			SwingTool.setCursorsFromChild(e.getComponent(), true);

			final MouseEvent evt = e;
			worker = new SingleWorker() {

				@Override
				protected Object doIt() {

					handleTreeEvent(evt);
					return null;
				}
			};

			worker.start();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void valueChanged(TreeSelectionEvent event) {
		}
	}

	class FrameHandler extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {

			dispose();
		}
	}

	private VLookup resource;
	private VDate dateFrom;
	private VDate dateTo;
	private ChartPanel chartPanel;
	private JSplitPane contentPanel;

	private SingleWorker worker;

	protected CRPModel model;
	protected JPopupMenu popup;

	public VCRPDetail() {
	}

	@Override
	public void init(int WindowNo, FormFrame frame) {

		m_form.init(WindowNo, frame);

		fillPicks();
		jbInit();
	}

	private void jbInit() {

		dateFrom = new VDate("DateFrom", true, false, true, DisplayType.Date,
				"DateFrom");
		dateTo = new VDate("DateTo", true, false, true, DisplayType.Date,
				"DateTo");

		CPanel northPanel = new CPanel();
		northPanel.setLayout(new java.awt.GridBagLayout());

		northPanel.add(
				new CLabel(Msg.translate(Env.getCtx(), "S_Resource_ID")),
				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(resource, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		northPanel.add(new CLabel(Msg.translate(Env.getCtx(), "DateFrom")),
				new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(dateFrom, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		northPanel.add(new CLabel(Msg.translate(Env.getCtx(), "DateTo")),
				new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(dateTo, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		ConfirmPanel confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(new ActionHandler());

		contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		contentPanel.setPreferredSize(new Dimension(800, 600));

		m_form.getWindow().getContentPane().add(northPanel, BorderLayout.NORTH);
		m_form.getWindow().getContentPane()
				.add(contentPanel, BorderLayout.CENTER);
		m_form.getWindow().getContentPane()
				.add(confirmPanel, BorderLayout.SOUTH);
	}

	private void fillPicks() {

		Properties ctx = Env.getCtx();
		MLookup resourceL = MLookupFactory.get(ctx, m_form.getWindowNo(), 0,
				MColumn.getColumn_ID(I_S_Resource.Table_Name, "S_Resource_ID"),
				DisplayType.TableDir);
		resource = new VLookup("S_Resource_ID", false, false, true, resourceL);
	}

	protected JPopupMenu createPopup(JTree tree) {

		JPopupMenu pm = new JPopupMenu();
		PopupAction action = null;

		try {

			action = new ZoomMenuAction(tree);
			pm.add(action);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return pm;
	}

	private void handleTreeEvent(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON3) {

			model.getTree().setSelectionPath(
					model.getTree().getPathForLocation(e.getX(), e.getY()));

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) model
					.getTree().getSelectionPath().getLastPathComponent();

			if (!(node.getUserObject() instanceof Date)
					&& !(node.getUserObject() instanceof MPPOrderNode)) {

				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		SwingTool.setCursorsFromChild(e.getComponent(), false);
	}

	private void handleActionEvent(ActionEvent e) {

		Timestamp df = getDateFrom();
		Timestamp dt = getDateTo();
		MResource r = getResource(resource.getValue());

		if (df != null && dt != null && r != null) {

			model = CRPDatasetFactory.get(df, dt, r);

			JFreeChart jfreechart = createChart(model.getDataset(),
					getChartTitle(), getSourceUOM(resource.getValue()));

			chartPanel = new ChartPanel(jfreechart, false);
			contentPanel.setLeftComponent(chartPanel);

			JTree tree = model.getTree();
			tree.addMouseListener(new TreeHandler());
			contentPanel.setRightComponent(new JScrollPane(tree));
			popup = createPopup(tree);
			contentPanel.setDividerLocation(0.70);
			contentPanel.setVisible(true);

			contentPanel.validate();
			contentPanel.repaint();
		}

		SwingTool.setCursorsFromParent(m_form.getWindow(), false);
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

	@Override
	public void dispose() {

		m_form.dispose();

		if (resource != null) {

			resource.dispose();
		}
		resource = null;

		if (dateFrom != null) {

			dateFrom.dispose();
		}
		dateFrom = null;

		if (dateTo != null) {

			dateTo.dispose();
		}
		dateTo = null;

		if (worker != null) {

			worker.stop();
		}
		worker = null;

		chartPanel = null;
		contentPanel = null;
		popup = null;
	}
}

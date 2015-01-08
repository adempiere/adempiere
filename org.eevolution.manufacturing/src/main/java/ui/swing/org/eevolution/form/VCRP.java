/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.I_M_Product;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MResource;
import org.compiere.model.MUOM;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.crp.CRPModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Swing Component to show Capacity Resource Planning
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class VCRP extends CRP implements FormPanel, ActionListener {

	static CLogger log = CLogger.getCLogger(VCRP.class);
	/** FormFrame */
	private FormFrame m_frame;
	private CPanel northPanel = new CPanel();

	private CPanel centerPanel = new CPanel();
	private BorderLayout centerLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Hashtable hash = new Hashtable();
	private VLookup resource = null;

	private CLabel resourceLabel = new CLabel();
	private VDate dateFrom = new VDate("DateFrom", true, false, true,
			DisplayType.Date, "DateFrom");

	private CLabel dateFromLabel = new CLabel();
	private ChartPanel chartPanel = new ChartPanel(createChart(
			new DefaultCategoryDataset(), "", null));
	protected CRPModel model;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {

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
				centerPanel.removeAll();
				chartPanel = new ChartPanel(jfreechart, false);
				centerPanel.add(chartPanel, BorderLayout.CENTER);
				centerPanel.setVisible(true);
				m_frame.pack();

			}
		}
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		}
	}

	/**
	 * Dispose
	 */
	@Override
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	} // dispose

	/**
	 * Fill Picks Column_ID from C_Order
	 * @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception {
		Properties ctx = Env.getCtx();
		MLookup resourceL = MLookupFactory.get(ctx, m_WindowNo, 0,
				MColumn.getColumn_ID(I_M_Product.Table_Name, "S_Resource_ID"),
				DisplayType.TableDir);
		resource = new VLookup("S_Resource_ID", false, false, true, resourceL);

	} // fillPicks

	/**
	 * Initialize Panel
	 * @param WindowNo window
	 * @param frame frame
	 */
	@Override
	public void init(int WindowNo, FormFrame frame) {
		log.info("VCRP.init");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try {
			fillPicks();
			jbInit();
			frame.getContentPane().add(northPanel, BorderLayout.NORTH);
			frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
			frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			frame.pack();
		} catch (Exception e) {
			log.log(Level.SEVERE, "VCRP.init", e);
		}
	} // init

	private void jbInit() throws Exception {
		northPanel.setLayout(new java.awt.GridBagLayout());

		resourceLabel.setText(Msg.translate(Env.getCtx(), "S_Resource_ID"));

		northPanel.add(resourceLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		northPanel.add(resource, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		dateFromLabel.setText(Msg.translate(Env.getCtx(), "DateFrom"));

		northPanel.add(dateFromLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		northPanel.add(dateFrom, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		chartPanel.setPreferredSize(new Dimension(750, 550));
		centerPanel.add(chartPanel, BorderLayout.CENTER);
		confirmPanel.addActionListener(this);
	}
}

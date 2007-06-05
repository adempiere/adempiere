/********************************************************************** 
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) 1999 - 2006 Compiere Inc.                            * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Bahman Movaqar (bmovaqar@users.sf.net)                          * 
 **********************************************************************/
package org.compiere.apps.graph;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.compiere.model.MGoal;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Performance Analysis Panel. Key Performace Indicators
 * 
 * @author Jorg Janke
 * @version $Id: PAPanel.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class PAPanel extends CPanel implements MouseListener, ActionListener {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 900002328486L;

	/**
	 * Get Panel if User has Perfpormance Goals
	 * 
	 * @return panel pr null
	 */
	public static PAPanel get() {
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		MGoal[] goals = MGoal.getUserGoals(Env.getCtx(), AD_User_ID);
		if (goals.length == 0)
			return null;
		return new PAPanel(goals);
	} // get

	/***************************************************************************
	 * Constructor
	 * 
	 * @param goals
	 */
	private PAPanel(MGoal[] goals) {
		super();
		m_goals = goals;
		init();
	} // PAPanel

	/** Goals */
	private MGoal[] m_goals = null;

	/** Logger */
	private static CLogger log = CLogger.getCLogger(PAPanel.class);

	/** Popup menu */
	JPopupMenu pmenu = new JPopupMenu();

	/** Update menu item */
	CMenuItem menuUpdate = new CMenuItem();

	/**
	 * Static/Dynamic Init
	 */
	private void init() {
		for (int i = 0; i < m_goals.length; i++) {
			PerformanceIndicator pi = new PerformanceIndicator(m_goals[i]);
			pi.addActionListener(this);
			add(pi);
		}
		// Setup the popup menu
		menuUpdate.setText("Update Indicators");
		menuUpdate.setActionCommand("update");
		menuUpdate.addActionListener(this);
		pmenu.setLightWeightPopupEnabled(false);
		pmenu.add(menuUpdate);

		this.addMouseListener(this);
	} // init

	/**
	 * Action Listener for Drill Down
	 * 
	 * @param e
	 *            event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof PerformanceIndicator) {
			PerformanceIndicator pi = (PerformanceIndicator) e.getSource();
			log.info(pi.getName());
			MGoal goal = pi.getGoal();
			if (goal.getMeasure() != null)
				new PerformanceDetail(goal);
		} else if (e.getActionCommand().equals("update"))
			updateInidcators();
	} // actionPerformed

	/**
	 * Updates indicators.
	 */
	private void updateInidcators() {
		for (int i = 0; i < m_goals.length; i++) {
			log.info("Updating indicator \"" + m_goals[i].getName() + "\"...");
			remove(i);
			PerformanceIndicator pi = new PerformanceIndicator(m_goals[i]);
			pi.addActionListener(this);
			add(pi, i);
			log.info("done.");
		}
	} // updateIndicators

	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e))
			pmenu.show((Component) e.getSource(), e.getX(), e.getY());
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

} // PAPanel

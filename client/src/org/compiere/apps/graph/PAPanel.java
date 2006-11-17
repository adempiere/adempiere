/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.graph;

import java.awt.event.*;
import javax.swing.*;

import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 * 	Performance Analysis Panel.
 * 	Key Performace Indicators
 *	
 *  @author Jorg Janke
 *  @version $Id: PAPanel.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class PAPanel extends CPanel implements ActionListener
{
	/**
	 * 	Get Panel if User has Perfpormance Goals
	 *	@return panel pr null
	 */
	public static PAPanel get()
	{
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		MGoal[] goals = MGoal.getUserGoals(Env.getCtx(), AD_User_ID);
		if (goals.length == 0)
			return null;
		return new PAPanel(goals);
	}	//	get
	
	
	/**************************************************************************
	 * 	Constructor
	 *	@param goals
	 */
	private PAPanel (MGoal[] goals)
	{
		super ();
		m_goals = goals;
		init();
	}	//	PAPanel
	
	/** Goals			*/
	private MGoal[] 	m_goals = null;
	
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (PAPanel.class);
	
	/**
	 * 	Static/Dynamic Init
	 */
	private void init()
	{
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		for (int i = 0; i < m_goals.length; i++)
		{
			PerformanceIndicator pi = new PerformanceIndicator(m_goals[i]);
			pi.addActionListener(this);
			add (pi);
		}
	}	//	init

	/**
	 * 	Action Listener for Drill Down
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof PerformanceIndicator)
		{
			PerformanceIndicator pi = (PerformanceIndicator)e.getSource();
			log.info(pi.getName());
			MGoal goal = pi.getGoal();
			if (goal.getMeasure() != null)
				new PerformanceDetail(goal);
		}
	}	//	actionPerformed

}	//	PAPanel

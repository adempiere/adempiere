/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Sponsors:                                                          		  *
 *  - E-evolution (http://www.e-evolution.com/)                     		  *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.compiere.apps.search.Find;
import org.compiere.grid.GridController;
import org.compiere.model.GridTab;
import org.compiere.model.MUserQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *	Application Search.
 *  Called from APanel; Queries Search Table.
 *	
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 	<li>RF [ 2853359 ] Popup Menu for Lookup Record
 * 	<li>http://sourceforge.net/tracker/?func=detail&aid=2853359&group_id=176962&atid=879335
 */
public class ASearch implements ActionListener
{
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public ASearch (JComponent invoker, Frame owner, int targetWindowNo, GridController gc ,GridTab gridTab)
	{
		m_invoker =  invoker;
		m_owner = owner;
		m_targetWindowNo = targetWindowNo;
		m_gt = gridTab;
		m_gc = gc;
		//	See What is there
		getSearchTargets ();
	}	//	AReport
	
	/**	The Popup						*/
	private JPopupMenu 	m_popup = new JPopupMenu("SearchMenu");
	/**	The Option List					*/
	private ArrayList<KeyNamePair>	m_list = new ArrayList<KeyNamePair>();
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ASearch.class);
	
	JComponent m_invoker;
	Frame m_owner;
	int m_targetWindowNo;
	GridTab m_gt;
	GridController m_gc;
	
	/**
	 * 	Get the UserQuery for the table.
	 *  Fill the list and the popup menu
	 *  @param invoker component to display popup (optional)
	 * 	@param AD_Tab_ID Tab ID
	 *  @param AD_Table_ID Table ID
	 *  @param AD_User_ID User ID
	 */
	private void getSearchTargets ()
	{
		boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(), "AD_Window"); 
		MUserQuery[] search = MUserQuery.get(Env.getCtx(), m_gt.getAD_Tab_ID());
		KeyNamePair pp = new KeyNamePair (0, Msg.translate(Env.getCtx(), "Find"));
		m_list.add(pp);
		m_popup.add(pp.toString()).addActionListener(this);
		
		for (MUserQuery query: search)
		{
			pp = new KeyNamePair (query.getAD_UserQuery_ID(), query.getName());
			m_list.add(pp);
			m_popup.add(pp.toString()).addActionListener(this);
		}

		if (m_invoker.isShowing() && m_list.size() > 1)
		{			
			m_popup.show(m_invoker, 0, m_invoker.getHeight());
		}	
		else
		{
			launchSearch(pp);
		}
	}	//	getZoomTargets

	
	/**
	 * 	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		m_popup.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		String cmd = e.getActionCommand();
		for (int i = 0; i < m_list.size(); i++)
		{
			KeyNamePair pp = (KeyNamePair)m_list.get(i);
			if (cmd.equals(pp.getName()))
			{
				launchSearch (pp);
				return;
			}
		}
	}	//	actionPerformed
	
	/**
	 * 	Launch Search
	 *	@param pp KeyPair
	 */
	private void launchSearch (KeyNamePair pp)
	{
		Find find = new Find (m_owner, m_targetWindowNo, m_gc , m_gt, 1);
		if(pp.getName().equals(Msg.getMsg(Env.getCtx(), "Find")))
		{
			AEnv.showCenterWindow(m_owner, find);
		}
		else
		{	
			int AD_UserQuery_ID = pp.getKey();
			MUserQuery userQuery = null;
			if(AD_UserQuery_ID  > 0)
			{
				userQuery = new MUserQuery(Env.getCtx(),AD_UserQuery_ID, null);
			}
			find.initFindAdvanced();
			find.parseUserQuery(userQuery);
			find.cmd_ok_Advanced();		
		}		
	}	
}	//	ASearch

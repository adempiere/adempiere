/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
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
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MUserQuery;
import org.compiere.print.ReportEngine;
import org.compiere.print.Viewer;
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
	
	private GridField[]		m_findFields;
	
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public ASearch (AppsAction appsAction, Frame owner, int targetWindowNo, GridController gc ,GridTab gridTab, int m_onlyCurrentDays)
	{
		m_onlycurrentdays = m_onlyCurrentDays;
		m_appsaction = appsAction;
		m_invoker =  appsAction.getButton();
		m_owner = owner;
		m_viewer = null;
		m_targetWindowNo = targetWindowNo;
		m_AD_Tab_ID = gridTab.getAD_Tab_ID();
		m_AD_Table_ID = gridTab.getAD_Table_ID();
		m_tableName = gridTab.getTableName();
		m_title = gridTab.getName();
		m_where = gridTab.getWhereExtended();
		m_gt = gridTab;
		m_gc = gc;		
		m_findFields = GridField.createFields(Env.getCtx(), m_targetWindowNo, 0, m_gt.getAD_Tab_ID());
		//	See What is there
		getSearchTargets ();
	}	//	ASearch
	
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public ASearch (JComponent invoker,Viewer viewer,String  title ,int AD_Tab_ID,int  AD_Table_ID,String tableName,ReportEngine reportEngine, GridField[] findFields ,int m_onlyCurrentDays)
	{
		
		m_onlycurrentdays = m_onlyCurrentDays;
		m_owner = (Frame)viewer;
		m_viewer = viewer;
		m_targetWindowNo = reportEngine.getWindowNo();
		m_AD_Tab_ID = AD_Tab_ID;
		m_AD_Table_ID = AD_Table_ID;
		m_tableName = tableName;
		m_where = reportEngine.getWhereExtended();
		m_gt = null;
		m_gc = null;
		m_findFields = findFields;
		m_invoker = invoker;
		m_reportEngine = reportEngine;
		//	See What is there
		getSearchTargets ();
	}	//	AReport
	
	
	
	/**	The Popup						*/
	private JPopupMenu 	m_popup = new JPopupMenu("SearchMenu");
	/**	The Option List					*/
	private ArrayList<KeyNamePair>	m_list = new ArrayList<KeyNamePair>();
	
	private int m_onlycurrentdays;
	private AppsAction m_appsaction;
	private JComponent m_invoker;
	private Frame m_owner;
	private int m_targetWindowNo;
	private GridTab m_gt;
	private GridController m_gc;
	private int m_AD_Tab_ID;
	private int m_AD_Table_ID;
	private String m_tableName;
	private String m_title;
	private String m_where;
	private MQuery m_query;
	private Viewer m_viewer;
	/**	Report Engine				*/
	private ReportEngine 	m_reportEngine;
	
	/**
	 * 	Get the UserQuery for the table.
	 *  Fill the list and the popup menu
	 */
	private void getSearchTargets ()
	{
		MUserQuery[] search = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID);
		KeyNamePair pp = null;
		
		if(search.length == 0)
		{
			find();
			return;
		}		
		else
		{	
			pp = new KeyNamePair (0, Msg.getMsg(Env.getCtx(), "Search"));
			m_list.add(pp);
			m_popup.add(pp.toString()).addActionListener(this);
		}
		
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
	}	//	getSearchTargets

	
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
		if(pp.getName().equals(Msg.getMsg(Env.getCtx(), "Search")))
		{
			find();
		}
		else
		{	
			filter(pp.getKey());
		}	
	}	
	
	/**
	 * 
	 * Open Find window
	 */
	private void find ()
	{
		GridField[] findFields = GridField.createFields(Env.getCtx(), m_targetWindowNo, 0, m_AD_Tab_ID);
		Find find = new Find (Env.getFrame(m_owner), m_targetWindowNo, m_title,
				m_AD_Tab_ID, m_AD_Table_ID, m_tableName, 
				m_where, findFields, 1);
		
		m_query = find.getQuery();
		find.dispose();
		find = null;

		if(m_gt != null && m_gc!= null)
		{
			//	Confirmed query
			if (m_query != null)
			{
				//m_onlyCurrentRows = false;      	//  search history too
				m_gt.setQuery(m_query);
				m_gc.query(false, m_onlycurrentdays, 0);   //  autoSize
			}
			m_appsaction.setPressed(m_gt.isQueryActive());	
		}
		if(m_reportEngine != null)
		{
			m_reportEngine.setQuery(m_query);
			m_viewer.revalidate();
		}
	}
	
	/**
	 * Set Query for this window based on User Query
	 * @param AD_UserQuery_ID
	 */
	private void filter(int AD_UserQuery_ID)
	{
		MUserQuery userQuery = null;
		if(AD_UserQuery_ID  > 0)
		{
			userQuery = new MUserQuery(Env.getCtx(),AD_UserQuery_ID, null);
			m_query = userQuery.getQuery(m_AD_Table_ID, m_targetWindowNo, m_findFields);
			
			//			Confirmed query
			if(m_gt != null && m_gc!= null)
			{
				if (m_query != null)
				{
					//m_onlyCurrentRows = false;      	//  search history too
					m_gt.setQuery(m_query);
					m_gc.query(false, m_onlycurrentdays, 0);   //  autoSize
				}
				m_appsaction.setPressed(m_gt.isQueryActive());	
			}	
			if(m_reportEngine != null && m_viewer != null )
			{
				m_reportEngine.setQuery(m_query);
				m_viewer.revalidate();
				//m_viewer.
			}
		}		
	}
				
	/**
	 * get Query
	 * @return MQuery
	 */
	public MQuery getQuery()
	{
		return m_query;
	}
		
}	//	ASearch

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
 *****************************************************************************/
package org.adempiere.webui;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.adempiere.webui.apps.AEnv;
import org.compiere.model.*;
import org.compiere.util.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 *	Application Zoom Across Launcher.
 *  Called from APanel; Queries available Zoom Targets for Table.
 *
 *  @author Jorg Janke
 *  @version $Id: AZoomAcross.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - FR [ 1762465 ]
 *
 * ZK Port
 * @author Low Heng Sin
 */
public class WZoomAcross implements EventListener
{
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public WZoomAcross (Component invoker, String tableName, MQuery query)
	{
		log.config("TableName=" + tableName + " - " + query);
		m_tableName = tableName;
		m_query = query;

		//	See What is there
		getZoomTargets (invoker, tableName);
	}	//	AReport

	/**	The Query						*/
	private MQuery	 	m_query;
	/**	The Popup						*/
	private Menupopup 	m_popup = new Menupopup(); //"ZoomMenu"
	/**	The Option List					*/
	private ArrayList<KeyNamePair>	m_list = new ArrayList<KeyNamePair>();
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WZoomAcross.class);
	/** Table Name */
	private String m_tableName;
	private ArrayList<String> m_targets = new ArrayList<String>();

	/**
	 * 	Get the Zomm Targets for the table.
	 *  Fill the list and the popup menu
	 *  @param invoker component to display popup (optional)
	 * 	@param tableName table
	 */
	private void getZoomTargets (Component invoker, String tableName)
	{
		String sql = "SELECT DISTINCT ws.AD_Window_ID,ws.Name, wp.AD_Window_ID,wp.Name, t.TableName "
			+ "FROM AD_Table t ";
		boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(), "AD_Window");
		if (baseLanguage)
			sql += "INNER JOIN AD_Window ws ON (t.AD_Window_ID=ws.AD_Window_ID)"
				+ " LEFT OUTER JOIN AD_Window wp ON (t.PO_Window_ID=wp.AD_Window_ID) ";
		else
			sql += "INNER JOIN AD_Window_Trl ws ON (t.AD_Window_ID=ws.AD_Window_ID AND ws.AD_Language=?)"
				+ " LEFT OUTER JOIN AD_Window_Trl wp ON (t.PO_Window_ID=wp.AD_Window_ID AND wp.AD_Language=?) ";
		//
		sql	+= "WHERE t.TableName NOT LIKE 'I%'"			//	No Import
			+ " AND EXISTS (SELECT * FROM AD_Tab tt " 		//	First Tab
				+ "WHERE (tt.AD_Window_ID=ws.AD_Window_ID OR tt.AD_Window_ID=wp.AD_Window_ID)"
				+ " AND tt.AD_Table_ID=t.AD_Table_ID AND tt.SeqNo=10)"
			+ " AND t.AD_Table_ID IN "
				+ "(SELECT AD_Table_ID FROM AD_Column "
				+ "WHERE ColumnName=? AND IsKey='N' AND IsParent='N') "	//	#x
			+ "ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			int index = 1;
			if (!baseLanguage)
			{
				pstmt.setString (index++, Env.getAD_Language(Env.getCtx()));
				pstmt.setString (index++, Env.getAD_Language(Env.getCtx()));
			}
			pstmt.setString (index++, tableName + "_ID");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int AD_Window_ID = rs.getInt(1);
				String Name = rs.getString(2);
				int PO_Window_ID = rs.getInt(3);
				String targetTableName = rs.getString(5);
				if (PO_Window_ID == 0)
					addTarget(targetTableName, AD_Window_ID, Name, null);
				else
					addTarget(targetTableName, AD_Window_ID, Name, Boolean.TRUE);
				//	PO
				if (PO_Window_ID != 0)
				{
					Name = rs.getString(4);
					addTarget(targetTableName, PO_Window_ID, Name, Boolean.FALSE);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		// Begin - afalcone - Bug Fix [ 1659420 ] Usability: zoom across
		/*
		//	No Zoom
		if (m_list.size() == 0)
		{
			ADialog.info(0, invoker, "NoZoomTarget");
			log.info("BaseLanguage=" + baseLanguage);
		}
		else if (invoker.isShowing())
			m_popup.show(invoker, 0, invoker.getHeight());	//	below button
		*/

		//	No Zoom
		if (m_list.size() == 0)
		{
			Menuitem menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "NoZoomTarget"));
			m_popup.appendChild(menuItem);  // Added
			log.info("BaseLanguage=" + baseLanguage);
		}
		m_popup.setPage(invoker.getPage());
		m_popup.open(invoker);

	    //End - afalcone - Bug Fix [ 1659420 ] Usability: zoom across

	}	//	getZoomTargets

	/**
	 * 	Check Target and Add to popup
	 *	@param targetTableName table name
	 *	@param AD_Window_ID window
	 *	@param Name name
	 *	@param isSO has po/so Window
	 *	@return true if there is a record
	 */
	private boolean addTarget (String targetTableName,
		int AD_Window_ID, String Name, Boolean isSO)
	{
		String key = targetTableName + AD_Window_ID + Name + isSO;
		if (m_targets.contains(key))
			return false;
		else
			m_targets.add(key);

		String sql = "SELECT COUNT(*) FROM " + targetTableName
			+ " WHERE " + m_query.getWhereClause(false);
		String sqlAdd = "";
		if (isSO != null)
		{
			/*
			For RMA, Material Receipt window should be loaded for IsSOTrx=true
		    and Shipment for IsSOTrx=false
			 */

			if (MRMA.Table_Name.equals(m_tableName) && (AD_Window_ID == 169
					|| AD_Window_ID == 184))
			{
				isSO = !isSO;
			}
			sqlAdd = " AND IsSOTrx=" + (isSO.booleanValue() ? "'Y'" : "'N'");
		}
		int count = DB.getSQLValue(null, sql+sqlAdd);
		if (count < 0 && isSO != null)	//	error try again w/o SO
			count = DB.getSQLValue(null, sql);
		if (count <= 0)
			return false;
		//
		KeyNamePair pp = new KeyNamePair (AD_Window_ID, Name + " (#"+count+")");
		m_list.add(pp);
		Menuitem menuItem = new Menuitem(pp.toString());
		menuItem.addEventListener(Events.ON_CLICK, this);
		m_popup.appendChild(menuItem);
		m_query.setRecordCount(count);
		return true;
	}	//	checkTarget

	/**
	 * 	Launch Zoom
	 *	@param pp KeyPair
	 */
	private void launchZoom (KeyNamePair pp)
	{
		int AD_Window_ID = pp.getKey();
		AEnv.zoom(AD_Window_ID, m_query);
	}	//	launchZoom

	public void onEvent(Event e) throws Exception {
		if (e.getTarget() instanceof Menuitem) {
			String cmd = ((Menuitem)e.getTarget()).getLabel();
			for (int i = 0; i < m_list.size(); i++)
			{
				KeyNamePair pp = (KeyNamePair)m_list.get(i);
				if (cmd.equals(pp.getName()))
				{
					launchZoom (pp);
					return;
				}
			}
		}
	}

}	//	AZoom

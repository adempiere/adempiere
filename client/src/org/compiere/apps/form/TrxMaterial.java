/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.apps.AEnv;
import org.compiere.apps.IStatusBar;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class TrxMaterial {

	/**	Window No			*/
	public int         	m_WindowNo = 0;
	/** MWindow                 */
	public GridWindow         m_mWindow = null;
	/** MTab pointer            */
	public GridTab            m_mTab = null;

	public MQuery          m_staticQuery = null;
	/**	Logger			*/
	public static CLogger log = CLogger.getCLogger(TrxMaterial.class);
	
	/**
	 *  Dynamic Layout (Grid).
	 * 	Based on AD_Window: Material Transactions
	 */
	public void dynInit(IStatusBar statusBar)
	{
		m_staticQuery = new MQuery();
		m_staticQuery.addRestriction("AD_Client_ID", MQuery.EQUAL, Env.getAD_Client_ID(Env.getCtx()));
		int AD_Window_ID = 223;		//	Hardcoded
		GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);
		if (wVO == null)
			return;
		m_mWindow = new GridWindow (wVO);
		m_mTab = m_mWindow.getTab(0);
		m_mWindow.initTab(0);
		//
		m_mTab.setQuery(MQuery.getEqualQuery("1", "2"));
		m_mTab.query(false);
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(" ");
	}   //  dynInit
	
	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	public void refresh(Object organization, Object locator, Object product, Object movementType, 
			Timestamp movementDateFrom, Timestamp movementDateTo, IStatusBar statusBar)
	{
		/**
		 *  Create Where Clause
		 */
		MQuery query = m_staticQuery.deepCopy();
		//  Organization
		if (organization != null && organization.toString().length() > 0)
			query.addRestriction("AD_Org_ID", MQuery.EQUAL, organization);
		//  Locator
		if (locator != null && locator.toString().length() > 0)
			query.addRestriction("M_Locator_ID", MQuery.EQUAL, locator);
		//  Product
		if (product != null && product.toString().length() > 0)
			query.addRestriction("M_Product_ID", MQuery.EQUAL, product);
		//  MovementType
		if (movementType != null && movementType.toString().length() > 0)
			query.addRestriction("MovementType", MQuery.EQUAL, movementType);
		//  DateFrom
		if (movementDateFrom != null)
			query.addRestriction("TRUNC(MovementDate, 'DD')", MQuery.GREATER_EQUAL, movementDateFrom);
		//  DateTO
		if (movementDateTo != null)
			query.addRestriction("TRUNC(MovementDate, 'DD')", MQuery.LESS_EQUAL, movementDateTo);
		log.info( "VTrxMaterial.refresh query=" + query.toString());

		/**
		 *  Refresh/Requery
		 */
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		//
		m_mTab.setQuery(query);
		m_mTab.query(false);
		//
		int no = m_mTab.getRowCount();
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(Integer.toString(no));
	}   //  refresh
	
	public int AD_Window_ID;
	public MQuery query;
	
	/**
	 *  Zoom
	 */
	public void zoom()
	{
		log.info("");
		//
		AD_Window_ID = 0;
		String ColumnName = null;
		String SQL = null;
		//
		int lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InOutLine_ID");
		if (lineID != 0)
		{
			log.fine("M_InOutLine_ID=" + lineID);
			if (Env.getContext(Env.getCtx(), m_WindowNo, "MovementType").startsWith("C"))
				AD_Window_ID = 169;     //  Customer
			else
				AD_Window_ID = 184;     //  Vendor
			ColumnName = "M_InOut_ID";
			SQL = "SELECT M_InOut_ID FROM M_InOutLine WHERE M_InOutLine_ID=?";
		}
		else
		{
			lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InventoryLine_ID");
			if (lineID != 0)
			{
				log.fine("M_InventoryLine_ID=" + lineID);
				AD_Window_ID = 168;
				ColumnName = "M_Inventory_ID";
				SQL = "SELECT M_Inventory_ID FROM M_InventoryLine WHERE M_InventoryLine_ID=?";
			}
			else
			{
				lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_MovementLine_ID");
				if (lineID != 0)
				{
					log.fine("M_MovementLine_ID=" + lineID);
					AD_Window_ID = 170;
					ColumnName = "M_Movement_ID";
					SQL = "SELECT M_Movement_ID FROM M_MovementLine WHERE M_MovementLine_ID=?";
				}
				else
				{
					lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_ProductionLine_ID");
					if (lineID != 0)
					{
						log.fine("M_ProductionLine_ID=" + lineID);
						AD_Window_ID = 191;
						ColumnName = "M_Production_ID";
						SQL = "SELECT M_Production_ID FROM M_ProductionLine WHERE M_ProductionLine_ID=?";
					}
					else
						log.fine("Not found WindowNo=" + m_WindowNo);
				}
			}
		}
		if (AD_Window_ID == 0)
			return;

		//  Get Parent ID
		int parentID = 0;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, lineID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				parentID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
		query = MQuery.getEqualQuery(ColumnName, parentID);
		log.config("AD_Window_ID=" + AD_Window_ID + " - " + query);
		if (parentID == 0)
			log.log(Level.SEVERE, "No ParentValue - " + SQL + " - " + lineID);
	}   //  zoom
}

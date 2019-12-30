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

import org.compiere.apps.AEnv;
import org.compiere.apps.IStatusBar;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Stocktake - Stocktake data entry form
 * 
 * @author Jorg Janke
 * @version $Id: VTrxMaterial.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Tony Snook - Physical Inventory Stocktake Enhancement
 */

public class Stocktake {

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
	 * 	Based on AD_Window: Stocktake (Indirect Use)
	 */
	public void dynInit(IStatusBar statusBar)
	{
		m_staticQuery = new MQuery();
		m_staticQuery.addRestriction("AD_Client_ID", MQuery.EQUAL, Env.getAD_Client_ID(Env.getCtx()));
		int AD_Window_ID = 53278;		//	Hardcoded Window: Stocktake (Indirect Use)
		GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);
		if (wVO == null)
			return;
		m_mWindow = new GridWindow (wVO);
		m_mTab = m_mWindow.getTab(0);
		m_mWindow.initTab(0);
		Env.setAutoCommit(Env.getCtx(), wVO.WindowNo, Env.isAutoCommit(Env.getCtx()));
		//
		
		m_mTab.setQuery(MQuery.getEqualQuery("1", "2"));
		m_mTab.query(false);
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(" ");
	}   //  dynInit
	
	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	public void refresh(Object inventory, Object locator, Object product, Object aislex, 
			Object lineFrom, Object lineTo, IStatusBar statusBar, Boolean isSecondCount)
	{
		m_mTab.dataSave(true);
		/**
		 *  Create Where Clause
		 */
		MQuery query = m_staticQuery.deepCopy();
		//  Physical Inventory
		if (inventory == null || inventory.toString().length() == 0) {
			return;
		}
		query.addRestriction("M_Inventory_ID", MQuery.EQUAL, inventory);
		//  Locator
		if (locator != null && locator.toString().length() > 0)
			query.addRestriction("M_Locator_ID", MQuery.EQUAL, locator);
		//  Product
		if (product != null && product.toString().length() > 0)
			query.addRestriction("M_Product_ID", MQuery.EQUAL, product);
		//  aislex
		if (aislex != null && aislex.toString().length() > 0)
			query.addRestriction("M_Locator_ID IN (SELECT M_Locator_ID FROM M_Locator WHERE X='"+aislex.toString()+"')");
		//  DateFrom
		if (lineFrom != null)
			query.addRestriction("Line", MQuery.GREATER_EQUAL, lineFrom);
		//  DateTO
		if (lineTo != null)
			query.addRestriction("Line", MQuery.LESS_EQUAL, lineTo);
		if (isSecondCount) 
		{
			int AD_Window_ID = 168;		//	Hardcoded Window: Physical Inventory
			GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);
			if (wVO == null)
				return;
			GridWindow m_mWindow2 = new GridWindow (wVO);
			GridTab m_mTab2 = m_mWindow2.getTab(3);  //second count tab

			String sql = m_mTab2.getWhereClause();
			query.addRestriction(sql);
		}
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
	

	/**
	 *  set_counted
	 */
	public void set_counted()
	{
		log.info("");
		//
		int rows = m_mTab.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			m_mTab.setCurrentRow(i);
			m_mTab.setValue("CountEntered", "Y");
		}
		m_mTab.dataSave(true);
		
	}

	
}

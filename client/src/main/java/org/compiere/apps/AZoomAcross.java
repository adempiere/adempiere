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
package org.compiere.apps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.adempiere.model.ZoomInfoFactory;
import org.compiere.model.MQuery;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 *	Application Zoom Across Launcher.
 *  Called from APanel; Queries available Zoom Targets for Table.
 *	
 *  @author Jorg Janke
 *  @version $Id: AZoomAcross.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - FR [ 1762465 ]
 * @author afalcone - Bug Fix [ 1659420 ] Usability: zoom across
 * @author Tobias Schoeneberg, www.metas.de - FR [ 2897194  ] Advanced Zoom and RelationTypes
 */
public class AZoomAcross 
{
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName zoom source table (i.e. the table we start from)
	 *  @param query query that specifies the zoom source PO (i.e. the PO we start from)
	 */
	public AZoomAcross (JComponent invoker, String tableName, final int windowID, MQuery query)
	{
		this(invoker, new Query(Env.getCtx(), tableName,
				query.getWhereClause(), null).first(), windowID);
	}

	public AZoomAcross(JComponent invoker, PO po, final int windowID) {
		
		logger.config("PO=" + po+", WindowID="+windowID);
		
		mkZoomTargets(po, windowID);
				
		for (final ZoomInfoFactory.ZoomInfo zoomInfo : zoomInfos) {

			final String label = zoomInfo.destinationDisplay + " (#"
					+ zoomInfo.query.getRecordCount() + ")";

			m_popup.add(label).addActionListener(new ActionListener() {
				@Override		
				public void actionPerformed(ActionEvent e) {
					launchZoom(zoomInfo);
				}
			});
		}

		if (zoomInfos.isEmpty()) {
			m_popup.add(Msg.getMsg(Env.getCtx(), "NoZoomTarget")); // Added
		}
		if (invoker.isShowing()) {
			m_popup.show(invoker, 0, invoker.getHeight());
		}
	}

	private final JPopupMenu 	m_popup = new JPopupMenu("ZoomMenu");
	
	private static final CLogger logger = CLogger.getCLogger(AZoomAcross.class);

	private final List<ZoomInfoFactory.ZoomInfo> zoomInfos = new ArrayList<ZoomInfoFactory.ZoomInfo>();
	
	private void mkZoomTargets(final PO po, final int windowID) {
	
		for (final ZoomInfoFactory.ZoomInfo zoomInfo : ZoomInfoFactory.retrieveZoomInfos(po,
				windowID)) {

			if (zoomInfo.query.getRecordCount() == 0) {
				logger.fine("No target records for destination "
						+ zoomInfo.destinationDisplay);
				continue;
			}
			zoomInfos.add(zoomInfo);
		}
	}

	
	/**
	 * 	Launch Zoom
	 *	@param pp KeyPair
	 */
	private void launchZoom (final ZoomInfoFactory.ZoomInfo zoomInfo)
	{
		final int AD_Window_ID = zoomInfo.windowId;
		final MQuery query = zoomInfo.query;
		
		logger.info("AD_Window_ID=" + AD_Window_ID 
			+ " - " + query); 
		
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, query))
			return;
		AEnv.addToWindowManager(frame);
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) 
		{
			AEnv.showMaximized(frame);
		}
		else
		{
			AEnv.showCenterScreen(frame);
		}
		frame = null;
	}	//	launchZoom
	
}	//	AZoom

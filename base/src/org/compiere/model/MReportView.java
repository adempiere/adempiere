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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;

/**
 * Model class for report view
 *
 */
public class MReportView extends X_AD_ReportView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8448730610011796268L;

	public MReportView(Properties ctx, int AD_ReportView_ID, String trxName) {
		super(ctx, AD_ReportView_ID, trxName);
	}

	public MReportView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**	Cache						*/
	private static CCache<Integer, MReportView> cache	= new CCache<Integer, MReportView>(Table_Name, 40, 5);	//	5 minutes
	
	/**
	 * Get from Cache
	 * @param ctx
	 * @param reportViewId
	 * @return
	 */
	public static MReportView get(Properties ctx, int reportViewId) {
		if (reportViewId <= 0) {
			return null;
		}
		Integer key = Integer.valueOf(reportViewId);
		MReportView retValue = (MReportView) cache.get (key);
		if (retValue != null) {
			return retValue;
		}
		retValue = new MReportView(ctx, reportViewId, null);
		if (retValue.get_ID () != 0) {
			cache.put (key, retValue);
		}
		return retValue;
	}	//	get
}

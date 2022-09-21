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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.manufacturing.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_PP_ForecastRunMaster;
import org.compiere.model.Query;

/**
 * Forecast Run Master
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class MPPForecastRunMaster extends X_PP_ForecastRunMaster {

	/**
	 * 
	 */
	private static final long serialVersionUID = -902212656388952987L;

	public static List<MPPForecastRunMaster> getLines(Properties ctx,
			int PP_ForecastRun_ID, String trxName) {
		return new Query(ctx, Table_Name, "PP_ForecastRun_ID=?", trxName)
				.setClient_ID().setParameters(PP_ForecastRun_ID).list();
	}

	public static List<MPPForecastRunDetail> getDetails(Properties ctx,
			int M_ForecastRunMaster_ID, String trxName) {
		String whereClause = MPPForecastRunDetail.COLUMNNAME_PP_ForecastRunMaster_ID
				+ "=?";
		return new Query(ctx, MPPForecastRunDetail.Table_Name, whereClause,
				trxName).setClient_ID().setParameters(M_ForecastRunMaster_ID)
				.list();
	}

	public static MPPForecastRunMaster getByProduct(Properties ctx,
			int PP_ForecastRun_ID, int M_Product_ID, String trxName) {
		final String whereClause = COLUMNNAME_PP_ForecastRun_ID + "=? AND "
				+ COLUMNNAME_M_Product_ID + "=? ";
		return new Query(ctx, Table_Name, whereClause, trxName).setClient_ID()
				.setParameters(PP_ForecastRun_ID, M_Product_ID).firstOnly();
	}

	public MPPForecastRunMaster(Properties ctx, int PP_ForecastRunMaster_ID,
			String trxName) {
		super(ctx, PP_ForecastRunMaster_ID, trxName);
	}

	public MPPForecastRunMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}

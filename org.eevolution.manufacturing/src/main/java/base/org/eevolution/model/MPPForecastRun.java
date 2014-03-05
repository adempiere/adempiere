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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

/**
 * Forecast Run Model
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class MPPForecastRun extends X_PP_ForecastRun {

	/**
	 * 
	 */
	private static final long serialVersionUID = 601163722987640072L;

	private List<MPPForecastRunResult> m_entries = null;

	public MPPForecastRun(Properties ctx, int M_ForecastRun_ID, String trxName) {
		super(ctx, M_ForecastRun_ID, trxName);
	}

	public MPPForecastRun(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Delete all Entries
	 * 
	 * @param force
	 *            delete also processed records
	 * @return true if deleted
	 */
	public boolean deleteEntries(boolean force) {
		getEntries(true);
		for (MPPForecastRunResult entry : m_entries) {
			entry.delete(force);
		}
		boolean ok = getEntries(true).size() == 0;
		if (ok)
			m_entries = null;
		return ok;
	} // deleteEntries

	/**
	 * Get Entries
	 * 
	 * @param requery
	 *            requery requery
	 * @return entries
	 */
	public List<MPPForecastRunResult> getEntries(boolean requery) {
		if (m_entries != null && !requery)
			return m_entries;

		final String whereClause = MPPForecastRunResult.COLUMNNAME_PP_ForecastRun_ID
				+ "=? ";
		// List<Object> parameters;

		m_entries = new Query(getCtx(), MPPForecastRunResult.Table_Name,
				whereClause, get_TrxName()).setClient_ID()
				.setParameters(get_ID()).list();

		return m_entries;
	} // getEntries

}

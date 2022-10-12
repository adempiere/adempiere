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
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Properties;

import org.adempiere.core.domains.models.I_PP_Period;
import org.adempiere.core.domains.models.X_PP_Period;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 * Period Model
 * 
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 * 
 */
public class MPPPeriod extends X_PP_Period {

	/** Cache */
	private static CCache<Integer, MPPPeriod> s_cache = new CCache<Integer, MPPPeriod>(
			"PP_Period", 10);
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MPPPeriod.class);

	/** Calendar */
	private int m_M_Calendar_ID = 0;

	public static int getIDByPeriodNo(MPPPeriodDefinition definition,
			int periodNo) {
		final String whereClause = MPPPeriod.COLUMNNAME_PP_PeriodDefinition_ID
				+ "=? AND " + MPPPeriod.COLUMNNAME_PeriodNo + "= ?";
		return new Query(definition.getCtx(), Table_Name, whereClause,
				definition.get_TrxName()).setClient_ID()
				.setParameters(definition.get_ID(), periodNo).firstIdOnly();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4217725345703715155L;

	public MPPPeriod(Properties ctx, int M_Period_ID, String trxName) {
		super(ctx, M_Period_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPPPeriod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parent constructor
	 * 
	 * @param year
	 *            year
	 * @param PeriodNo
	 *            no
	 * @param name
	 *            name
	 * @param startDate
	 *            start
	 * @param endDate
	 *            end
	 */
	public MPPPeriod(MPPPeriodDefinition pd, int PeriodNo, String name,
			Timestamp startDate, Timestamp endDate) {
		this(pd.getCtx(), 0, pd.get_TrxName());
		setClientOrg(pd);
		setPP_Period_ID(pd.getPP_PeriodDefinition_ID());
		setPeriodNo(PeriodNo);
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
	} // MPeriod

	/**
	 * 
	 * @param ctx
	 * @param Date
	 * @param M_Calendar_ID
	 * @param trxName
	 * @return MPeriod
	 */
	public static MPPPeriod findByCalendar(Properties ctx, Timestamp date,
			int M_Calendar_ID, String trxName) {

		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		// Search in Cache first
		Iterator<MPPPeriod> it = s_cache.values().iterator();
		while (it.hasNext()) {
			MPPPeriod period = (MPPPeriod) it.next();
			if (period.getM_Calendar_ID() == M_Calendar_ID
					&& period.isInPeriod(date)
					&& period.getAD_Client_ID() == AD_Client_ID)
				return period;
		}

		String whereClause = "PP_PeriodDefinition_ID IN (SELECT PP_PeriodDefinition_ID FROM PP_PeriodDefinition WHERE PP_Calendar_ID = ?) "
				+ " AND ? BETWEEN TRUNC(StartDate, 'DD') AND TRUNC(EndDate, 'DD')";

		MPPPeriod period = new Query(ctx, I_PP_Period.Table_Name, whereClause,
				trxName).setClient_ID()
				.setParameters(M_Calendar_ID, TimeUtil.getDay(date))
				.setOnlyActiveRecords(true).first();

		if (period == null)
			return period;

		Integer key = Integer.valueOf(period.getPP_Period_ID());
		s_cache.put(key, period);
		return period;
	}

	/**
	 * Date In Period
	 * 
	 * @param date
	 *            date
	 * @return true if in period
	 */
	public boolean isInPeriod(Timestamp date) {
		if (date == null)
			return false;
		Timestamp dateOnly = TimeUtil.getDay(date);
		Timestamp from = TimeUtil.getDay(getStartDate());
		if (dateOnly.before(from))
			return false;
		Timestamp to = TimeUtil.getDay(getEndDate());
		if (dateOnly.after(to))
			return false;
		return true;
	} // isInPeriod

	/**
	 * Get Calendar of Period
	 * 
	 * @return calendar
	 */
	public int getM_Calendar_ID() {
		if (m_M_Calendar_ID == 0) {
			MPPPeriodDefinition pd = (MPPPeriodDefinition) getPP_PeriodDefinition();
			if (pd != null)
				m_M_Calendar_ID = pd.getPP_Calendar_ID();
			else
				log.severe("@NotFound@ @PP_PeriodDefinition_ID@ ="
						+ getPP_PeriodDefinition_ID());
		}
		return m_M_Calendar_ID;
	} // getC_Calendar_ID
}

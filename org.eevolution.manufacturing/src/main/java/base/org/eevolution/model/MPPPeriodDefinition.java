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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 * Period Definition Model
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class MPPPeriodDefinition extends X_PP_PeriodDefinition {

	public List<MPPPeriod> getPeriodsOfHistory(int periodHistory) {
		List<MPPPeriod> periods = new ArrayList<MPPPeriod>();
		int countPeriod = 1;
		for (MPPPeriod period : getPeriodsOrderBy(MPPPeriod.COLUMNNAME_PeriodNo
				+ " DESC")) {
			periods.add(period);

			if (countPeriod >= periodHistory)
				break;

			countPeriod++;
		}
		return periods;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1849382103051033180L;

	/**
	 * @param ctx
	 * @param M_PeriodDefinition_ID
	 * @param trxName
	 */
	public MPPPeriodDefinition(Properties ctx, int M_PeriodDefinition_ID,
			String trxName) {
		super(ctx, M_PeriodDefinition_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPPPeriodDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public List<MPPPeriod> getPeriodsOrderBy(String orderBy) {
		final String whereClause = MPPPeriod.COLUMNNAME_PP_PeriodDefinition_ID
				+ "=?";
		return new Query(getCtx(), MPPPeriod.Table_Name, whereClause,
				get_TrxName()).setClient_ID().setParameters(get_ID())
				.setOrderBy(orderBy).list();
	}

	/**
	 * @param locale
	 *            locale
	 * @param startDate
	 *            first day of the calendar year
	 * @param dateFormat
	 *            SimpleDateFormat pattern for generating the period names.
	 * @return true if created
	 */
	public boolean createPeriods(Locale locale, Timestamp startDate,
			String dateFormat, int noPeriods) {

		if (locale == null) {
			MClient client = MClient.get(getCtx());
			locale = client.getLocale();
		}

		if (locale == null && Language.getLoginLanguage() != null)
			locale = Language.getLoginLanguage().getLocale();
		if (locale == null)
			locale = Env.getLanguage(getCtx()).getLocale();
		//
		SimpleDateFormat formatter;
		if (dateFormat == null || dateFormat.equals("")) {
			if (noPeriods == 52)
				dateFormat = "ww-yy";
			if (noPeriods == 12)
				dateFormat = "MMM-yy";
		}

		formatter = new SimpleDateFormat(dateFormat, locale);

		//
		int year = getYearAsInt();
		GregorianCalendar cal = new GregorianCalendar(locale);
		if (startDate != null) {
			cal.setTime(startDate);
			if (cal.get(Calendar.YEAR) != year) // specified start date takes
												// precedence in setting year
				year = cal.get(Calendar.YEAR);

		} else {
			cal.set(Calendar.YEAR, year);
			if (noPeriods == 12)
				cal.set(Calendar.MONTH, 0);
			if (noPeriods == 52)
				cal.set(Calendar.WEEK_OF_YEAR, 1);

			cal.set(Calendar.DAY_OF_MONTH, 1);

		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		//
		for (int p = 0; p < noPeriods; p++) {

			Timestamp start = new Timestamp(cal.getTimeInMillis());
			String name = formatter.format(start);
			// get last day of same month
			if (noPeriods == 52)
				cal.add(Calendar.WEEK_OF_YEAR, 1);
			if (noPeriods == 12)
				cal.add(Calendar.MONTH, 1);

			cal.add(Calendar.DAY_OF_YEAR, -1);

			Timestamp end = new Timestamp(cal.getTimeInMillis());
			//
			MPPPeriod period = MPPPeriod.findByCalendar(getCtx(), start,
					getPP_Calendar_ID(), get_TrxName());
			if (period == null) {
				period = new MPPPeriod(this, p + 1, name, start, end);
				period.setPP_PeriodDefinition_ID(this
						.getPP_PeriodDefinition_ID());
			}

			period.setPeriodNo(p + 1);
			period.setName(name);
			period.setStartDate(start);
			period.setEndDate(end);
			period.saveEx(get_TrxName()); // Creates Period Control
			// get first day of next month
			if (noPeriods == 52)
				cal.add(Calendar.DAY_OF_WEEK, 1);
			if (noPeriods == 12)
				cal.add(Calendar.DAY_OF_YEAR, 1);
		}

		return true;

	} // createStdPeriods

	/**
	 * Get Year As Int
	 * 
	 * @return year as int or 0
	 */
	public int getYearAsInt() {
		String year = getCalendarYear();
		try {
			return Integer.parseInt(year);
		} catch (Exception e) {
			StringTokenizer st = new StringTokenizer(year, "/-, \t\n\r\f");
			if (st.hasMoreTokens()) {
				String year2 = st.nextToken();
				try {
					return Integer.parseInt(year2);
				} catch (Exception e2) {
					log.log(Level.WARNING,
							year + "->" + year2 + " - " + e2.toString());
				}
			} else
				log.log(Level.WARNING, year + " - " + e.toString());
		}
		return 0;
	}
}

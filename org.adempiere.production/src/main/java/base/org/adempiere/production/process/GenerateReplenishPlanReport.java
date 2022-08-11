/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.production.process;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.compiere.production.model.MReplenishPlan;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_PrintFormatItem;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 * @author Sachin Bhimani
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/789">
 * 		@see FR [ 789 ] The Calculate Replenish Plan process not support SQL99</a>
 */
public class GenerateReplenishPlanReport extends GenerateReplenishPlanReportAbstract {
	
	private int						redPrintColorId;
	private Timestamp				dateFrom;
	private Timestamp				dateTo;
	private Calendar				calendar		= Calendar.getInstance();
	private Map<Integer, String>	weekDateInfo	= new HashMap<Integer, String>();

	@Override
	protected void prepare() {
		super.prepare();
		redPrintColorId = DB.getSQLValue(get_TrxName(),
				"SELECT AD_PrintColor_ID FROM AD_PrintColor WHERE Name ='Red'");
	}

	@Override
	protected String doIt() {
		MReplenishPlan run = new MReplenishPlan(getCtx(), getRecord_ID(), get_TrxName());
		dateFrom = run.getDateStart();
		dateTo = run.getDateFinish();

		if (dateFrom == null)
			throw new IllegalArgumentException(Msg.parseTranslation(getCtx(), "@FillMandatory@ @DateStart@"));
		if (dateTo == null)
			throw new IllegalArgumentException(Msg.parseTranslation(getCtx(), "@FillMandatory@ @DateFinish@"));
		//	
		addWeekDateInfo();

		if (Ini.isClient())
			getProcessInfo().setTransientObject(getPrintFormat());
		else
			getProcessInfo().setSerializableObject(getPrintFormat());
		return null;
	}

	/**
	 * @return
	 */
	private MPrintFormat getPrintFormat()
	{
		MPrintFormat pf = null;
		if (Ini.isClient()) {
			pf = (MPrintFormat) getProcessInfo().getTransientObject();
		} else {
			pf = (MPrintFormat) getProcessInfo().getSerializableObject();
		}
		if (pf == null) {
			pf = MPrintFormat.get(getCtx(), 0, MTable.getTable_ID("M_ReplenishPlanLine"));
		}
			
		int count = pf.getItemCount();
		int weeksToDisplay = TimeUtil.getWeeksBetween(dateFrom, dateTo);
		int startIndx = 1;
		int endIndx = weeksToDisplay;
		//	
		for (int i = 0; i < count; i++) {
			MPrintFormatItem pfi = pf.getItem(i);
			String columnName = pfi.getColumnName().toLowerCase();
			if (columnName != null && columnName.startsWith("week")) {
				int index = Integer.parseInt(columnName.substring(4));
				if (index > weeksToDisplay) {
					if (pfi.isPrinted())
						pfi.setIsPrinted(false);
				} else {
					int displayWeek = startIndx + (index - 1);
					String printName = weekDateInfo.get(displayWeek);
					pfi.setPrintName(printName);
					pfi.setIsPrinted(true);
					pfi.setIsSuppressNull(false);
					pfi.setIsSuppressRepeats(false);
					pfi.setMaxWidth(35);
					pfi.setIsFixedWidth(true);
					pfi.setIsHeightOneLine(false);
					// pfi.set_ValueOfColumn("DisplayLogic", "@" + columnName +
					// "@!0");
					pfi.setLineAlignmentType(X_AD_PrintFormatItem.LINEALIGNMENTTYPE_TrailingRight);
					pfi.setFieldAlignmentType(X_AD_PrintFormatItem.FIELDALIGNMENTTYPE_TrailingRight);

					// Dynamic sequencing is required as we are showing & hiding
					// elements based on week difference.
					pfi.setSeqNo(50 + index * 10);
					if (displayWeek > (startIndx + 1) && displayWeek < (endIndx - 1))
					{
						pfi.setAD_PrintColor_ID(redPrintColorId);
					}
					else
					{
						pfi.setAD_PrintColor_ID(-1);
					}
				}
			}
			pfi.saveEx();
			log.fine(pfi.toString());
		}
		pf.setTranslation();
		pf = MPrintFormat.get(getCtx(), pf.getAD_PrintFormat_ID(), true); // no
																			// cache
		return pf;
	}
	
	/**
	 * Get Week Info
	 */
	private void addWeekDateInfo() {
		int start = 1;
		int end = TimeUtil.getWeeksBetween(dateFrom, dateTo);
		Date dt = dateFrom;
		//	
		for (int count = start; count <= end; count++) {
			calendar.setTimeInMillis(dt.getTime());
			StringBuilder weekStartDate = new StringBuilder(new SimpleDateFormat("dd-").format(dt));
			calendar.add(Calendar.DAY_OF_WEEK, 6);
			dt = calendar.getTime();
			weekStartDate.append(new SimpleDateFormat("dd (MMM yyyy)").format(dt));
			calendar.add(Calendar.DAY_OF_WEEK, 1);
			dt.setTime(calendar.getTimeInMillis());

			weekDateInfo.put(count, weekStartDate.toString());
		}
	}
}

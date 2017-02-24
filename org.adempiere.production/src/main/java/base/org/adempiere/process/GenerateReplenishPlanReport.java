package org.adempiere.process;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.compiere.model.MReplenishPlan;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_PrintFormatItem;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 * @author Sachin Bhimani
 */
public class GenerateReplenishPlanReport extends SvrProcess
{
	private int						START_WEEK;
	private int						END_WEEK;
	private int						RED_AD_PrintColor_ID;
	private Timestamp				dateFrom;
	private Timestamp				dateTo;
	private Calendar				calendar		= Calendar.getInstance();
	private Map<Integer, String>	weekDateInfo	= new HashMap<Integer, String>();

	@Override
	protected void prepare()
	{
		RED_AD_PrintColor_ID = DB.getSQLValue(get_TrxName(),
				"SELECT AD_PrintColor_ID FROM AD_PrintColor WHERE Name ='Red'");
	}

	@Override
	protected String doIt() throws Exception
	{
		MReplenishPlan run = new MReplenishPlan(getCtx(), getRecord_ID(), get_TrxName());
		dateFrom = run.getDateStart();
		dateTo = run.getDateFinish();

		if (dateFrom == null)
			throw new IllegalArgumentException(Msg.translate(getCtx(), "FillMandatory")
					+ Msg.translate(getCtx(), "DatePosted - From"));
		if (dateTo == null)
			throw new IllegalArgumentException(Msg.translate(getCtx(), "FillMandatory")
					+ Msg.translate(getCtx(), "DatePosted - To"));

		int isAfterDate = dateTo.compareTo(dateFrom);

		if (isAfterDate > 0)
		{
			START_WEEK = DB.getSQLValue(get_TrxName(), "SELECT EXTRACT( WEEK FROM ?::Timestamp )", dateFrom) - 2;
			END_WEEK = DB.getSQLValue(get_TrxName(), CalculateReplenishPlan.SQL_GET_ISO_WEEKNO, dateFrom, dateTo, dateTo,
					dateTo) + 2;

			if (START_WEEK == 0)
			{
				START_WEEK = DB.getSQLValue(get_TrxName(),
						"SELECT EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', ?::DATE) + interval '-1 day')) ", dateFrom);
				END_WEEK += START_WEEK;
			}

			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.setTime(dateFrom);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.add(Calendar.WEEK_OF_YEAR, -2);

			dateFrom.setTime(cal.getTimeInMillis());

			int weekDifference = END_WEEK - START_WEEK;

			cal.setTime(dateFrom);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			cal.add(Calendar.WEEK_OF_YEAR, weekDifference);

			dateTo.setTime(cal.getTimeInMillis());

			if (weekDifference > 24)
			{
				throw new IllegalArgumentException(Msg.translate(getCtx(),
						"Week difference should not be greater than 20 for selected horizon.")
						+ Msg.translate(getCtx(), ""));
			}
		}
		else
		{
			throw new IllegalArgumentException(Msg.translate(getCtx(), "ToDate must me greater than selected FromDate")
					+ Msg.translate(getCtx(), ""));
		}

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
		int weeksToDisplay = END_WEEK - START_WEEK + 1;
		int startIndx = START_WEEK;
		int endIndx = END_WEEK;

		for (int i = 0; i < count; i++)
		{
			MPrintFormatItem pfi = pf.getItem(i);
			String columnName = pfi.getColumnName().toLowerCase();
			if (columnName != null && columnName.startsWith("week"))
			{
				int index = Integer.parseInt(columnName.substring(4));
				if (index > weeksToDisplay)
				{
					if (pfi.isPrinted())
						pfi.setIsPrinted(false);
				}
				else
				{
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
						pfi.setAD_PrintColor_ID(RED_AD_PrintColor_ID);
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

	private void addWeekDateInfo()
	{
		int start = START_WEEK;
		int end = END_WEEK;
		Date dt = dateFrom;

		for (int count = start; count <= end; count++)
		{

			calendar.setTimeInMillis(dt.getTime());
			StringBuilder weekStartDate = new StringBuilder(new SimpleDateFormat("dd-").format(dt));
			calendar.add(Calendar.DAY_OF_WEEK, 6);
			dt = calendar.getTime();
			weekStartDate.append(new SimpleDateFormat("dd MMM-yy").format(dt));
			calendar.add(Calendar.DAY_OF_WEEK, 1);
			dt.setTime(calendar.getTimeInMillis());

			weekDateInfo.put(count, weekStartDate.toString());
		}
	}
}

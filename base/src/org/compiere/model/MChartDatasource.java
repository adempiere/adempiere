package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;

public class MChartDatasource extends X_AD_ChartDatasource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5108909995064477463L;

	public MChartDatasource(Properties ctx, int AD_ChartDatasource_ID,
			String trxName) {
		super(ctx, AD_ChartDatasource_ID, trxName);
	}

	public MChartDatasource(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public void addData(MChart parent) {
		
		String value = getValueColumn();
		String category;
		String unit = "D";
		
		if ( !parent.isTimeSeries() )
			category = getCategoryColumn();
		else 
		{
			if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Week))
			{
				unit = "W";
			}
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Month))
			{
				unit = "MM";
			}
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Quarter))
			{
					unit = "Q";
			}
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Year))
			{
						unit = "Y";
			}
			
			category = " TRUNC(" + getDateColumn() + ", '" + unit + "') ";
		}
		
		String series = DB.TO_STRING(getName());
		boolean hasSeries = false;
		if (getSeriesColumn() != null)
		{
			series = getSeriesColumn();
			hasSeries = true;
		}
		
		String where = getWhereClause();
		if ( !Util.isEmpty(where))
		{
			where = Env.parseContext(getCtx(), parent.getWindowNo(), where, true);
		}
		
		boolean hasWhere = false;
		
		String sql = "SELECT " + value + ", " + category  + ", " + series
		+ " FROM " + getFromClause();
		if ( !Util.isEmpty(where))
		{
			sql += " WHERE " + where;
			hasWhere = true;
		}
		
		Date currentDate = Env.getContextAsDate(getCtx(), "#Date");
		Date startDate = null;
		Date endDate = null;
		
		int scope = parent.getTimeScope();
		int offset = getTimeOffset();
		
		if ( parent.isTimeSeries() && scope != 0 )
		{
			offset += -scope;
			startDate = increment(currentDate, parent.getTimeUnit(), offset);
			endDate = increment(startDate, parent.getTimeUnit(), scope);
		}
		
		if ( startDate != null && endDate != null )
		{
			sql += hasWhere ? " AND " : " WHERE ";
			sql += category + ">=TRUNC(" + DB.TO_DATE(new Timestamp(startDate.getTime())) + ", '" + unit + "') AND ";
			sql += category + "<=TRUNC(" + DB.TO_DATE(new Timestamp(endDate.getTime())) + ", '" + unit + "') ";
		}
		
		MRole role = MRole.getDefault(getCtx(), false);
		sql = role.addAccessSQL(sql, null, true, false);
		
		if (hasSeries)
			sql += " GROUP BY " + series + ", " + category + " ORDER BY " + series + ", "  + category;
		else
			sql += " GROUP BY " + category + " ORDER BY " + category;
		
		log.log(Level.FINE, sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TimeSeries tseries = null;
		Dataset dataset = parent.getDataset();

		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while(rs.next())
			{

				String key = rs.getString(2);
				String seriesName = rs.getString(3);
				if (seriesName == null)
					seriesName = getName();
				String queryWhere = "";
				if ( hasWhere )
					queryWhere += where + " AND ";

				queryWhere += series + " = " + DB.TO_STRING(seriesName) + " AND " + category + " = " ;

				if ( parent.isTimeSeries() && dataset instanceof TimeSeriesCollection )
				{

					if ( tseries == null || !tseries.getKey().equals(seriesName))
					{
						if (tseries != null)
							((TimeSeriesCollection) dataset).addSeries(tseries);

						tseries = new TimeSeries(seriesName);
					}

					Date date = rs.getDate(2);
					RegularTimePeriod period = null;

					if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Day))
						period = new Day(date);
					else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Week))
						period = new Week(date);
					else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Month))
						period = new Month(date);
					else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Quarter))
						period = new Quarter(date);
					else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Year))
						period = new Year(date);

					tseries.add(period, rs.getBigDecimal(1));
					key = period.toString();
					queryWhere += DB.TO_DATE(new Timestamp(date.getTime()));
				}
				else {
					queryWhere += DB.TO_STRING(key);
				}

				MQuery query = new MQuery(getAD_Table_ID());
				String keyCol = MTable.get(getCtx(), getAD_Table_ID()).getKeyColumns()[0];
				String whereClause = keyCol  + " IN (SELECT " + getKeyColumn() + " FROM " 
						+ getFromClause() + " WHERE " + queryWhere + " )";
				query.addRestriction(whereClause.toString());
				query.setRecordCount(1);

				HashMap<String, MQuery> map = parent.getQueries();

				if ( dataset instanceof DefaultPieDataset) {
					((DefaultPieDataset) dataset).setValue(key, rs.getBigDecimal(1));
					map.put(key, query);
				}
				else if ( dataset instanceof DefaultCategoryDataset ) {
				((DefaultCategoryDataset) dataset).addValue(rs.getBigDecimal(1), seriesName, key);
					map.put(seriesName + "__" + key, query);
				}
				else if (dataset instanceof TimeSeriesCollection )
				{
					map.put(seriesName + "__" + key, query);
				}
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (tseries != null)
			((TimeSeriesCollection) dataset).addSeries(tseries);

	}

	private Date increment(Date lastDate, String timeUnit, int qty) {
		
		if ( lastDate == null )
			return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastDate);
		
		if ( timeUnit.equals(MChart.TIMEUNIT_Day))
			cal.add(Calendar.DAY_OF_YEAR, qty);
		else if ( timeUnit.equals(MChart.TIMEUNIT_Week))
			cal.add(Calendar.WEEK_OF_YEAR, qty);
		else if ( timeUnit.equals(MChart.TIMEUNIT_Month))
			cal.add(Calendar.MONTH, qty);
		else if ( timeUnit.equals(MChart.TIMEUNIT_Quarter))
			cal.add(Calendar.MONTH, 3*qty);
		else if ( timeUnit.equals(MChart.TIMEUNIT_Year))
			cal.add(Calendar.YEAR, qty);
		
		return cal.getTime();
	}

	private String formatDate(Date date, String timeUnit)
	{
		String key = null;
		String unitFormat = "yyyy-MM-dd";
		 if ( timeUnit.equals(MChart.TIMEUNIT_Week))
			unitFormat = "yyyy-w";
		else if ( timeUnit.equals(MChart.TIMEUNIT_Month))
			unitFormat = "yyyy-MMM";
		else if ( timeUnit.equals(MChart.TIMEUNIT_Quarter))
				unitFormat = "yyyy-MM";
		else if ( timeUnit.equals(MChart.TIMEUNIT_Year))
					unitFormat = "yyyy";
		 SimpleDateFormat format = new SimpleDateFormat(unitFormat);
		 key = format.format(date);
		 if ( timeUnit.equals(MChart.TIMEUNIT_Quarter) )
			 key = convertToQuarter(format.format(date));
		 
		 return key;
	}
	
	/**
	 * Convert date formatted as yyyy-MM to yyyy-QQ
	 * @param month
	 * @return
	 */
	private String convertToQuarter(String month) {
		if ( month.length() != 7 )
			return month;
		
		String mm = month.substring(5,7);
		int mon = Integer.parseInt(mm);
		return month.substring(0,5)+"Q" + ((mon/3) + 1);
		
	}

	public MQuery getZoomQuery(MChart parent, String value, String category2) {
		MQuery query = new MQuery(getAD_Table_ID());
		
		String category;
		
		if ( !parent.isTimeSeries() )
			category = getCategoryColumn();
		else 
		{
			String unit = "D";
			if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Week))
				unit = "W";
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Month))
				unit = "MM";
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Quarter))
					unit = "Q";
			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Year))
						unit = "Y";
			
			category = " TRUNC(" + getDateColumn() + ", '" + unit + "') ";
		}
		
		String where = getWhereClause();
		if ( !Util.isEmpty(where))
		{
			where = Env.parseContext(Env.getCtx(), parent.getWindowNo(), where, true);
		}
		
		String series = DB.TO_STRING(getName());
		if (getSeriesColumn() != null)
			series = getSeriesColumn();
		
		String sql = "SELECT " + getKeyColumn() + "," + category + "," + series
		+ " FROM " + getFromClause();
		
		if ( !Util.isEmpty(where))
		{
			sql += " WHERE " + where;
		}
		

		sql += " GROUP BY " + series + ", " + category + "," + getKeyColumn()
		+ " ORDER BY " + series + ", " + category + "," + getKeyColumn();
		
		StringBuffer includedIds = new StringBuffer();
		//	Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				int id = rs.getInt(1);
				String key = rs.getString(2);
				if ( parent.isTimeSeries() )
		    	 {
		    		 Date date = rs.getDate(2);
		    		 String unit = "yyyy-MM-dd";
		    		 if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Week))
		 				unit = "yyyy-w";
		 			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Month))
		 				unit = "yyyy-MMM";
		 			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Quarter))
		 					unit = "yyyy-MM";
		 			else if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Year))
		 						unit = "yyyy";
		    		 SimpleDateFormat format = new SimpleDateFormat(unit);
		    		 key = format.format(date);
		    		 if ( parent.getTimeUnit().equals(MChart.TIMEUNIT_Quarter) )
		    			 key = convertToQuarter(format.format(date));
		    	 }

				if ( value.equals(key) )
				{
					if (includedIds.length() > 0)
						includedIds.append(",");
					includedIds.append(id);
				}
			}
		}
		catch (SQLException e)
		{
		     throw new DBException(e, sql);
		}
		finally
		{
		     DB.close(rs, pstmt);
		     rs = null; pstmt = null;
		}

		if (includedIds.length() == 0)
			return MQuery.getNoRecordQuery(query.getTableName(), false);


		return query;
	}

}

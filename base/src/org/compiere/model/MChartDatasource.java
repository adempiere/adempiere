package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class MChartDatasource extends X_AD_ChartDatasource {

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
		
		boolean hasWhere = false;
		String series = DB.TO_STRING(getName());
		boolean hasSeries = false;
		if (getSeriesColumn() != null)
		{
			series = getSeriesColumn();
			hasSeries = true;
		}
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
		
		if (hasSeries)
			sql += " GROUP BY " + series + ", " + category + " ORDER BY " + series + ", "  + category;
		else
			sql += " GROUP BY " + category + " ORDER BY " + category;
		
		log.log(Level.FINE, sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date lastDate = startDate;
		try
		{
		     pstmt = DB.prepareStatement(sql, null);
		     rs = pstmt.executeQuery();
		     while(rs.next())
		     {

		    	 String key = rs.getString(2);
		    	 String seriesName = rs.getString(3);
		    	 if ( parent.isTimeSeries() )
		    	 {
		    		 Date date = rs.getDate(2);
				    		
		    		 Date expected = increment(lastDate, parent.getTimeUnit());
		    		 
		    		 // generate empty data for missing periods
		    		 while ( lastDate != null && date.compareTo(expected) > 0 
		    				 && !formatDate(expected, parent.getTimeUnit()).equals(formatDate(date, parent.getTimeUnit())))
		    		 {
				         ((DefaultCategoryDataset)parent.getCategoryDataset()).addValue(Env.ZERO, Msg.getMsg(getCtx(), "NoData"), formatDate(expected, parent.getTimeUnit()));
				         expected = increment(expected, parent.getTimeUnit());
		    		 }
		    		 
		    		key = formatDate(date, parent.getTimeUnit());
		    		lastDate = date;
		    	 }
		    		 
		         ((DefaultCategoryDataset)parent.getCategoryDataset()).addValue(rs.getBigDecimal(1), seriesName, key);
		         ((DefaultPieDataset) parent.getPieDataset()).setValue(key, rs.getBigDecimal(1));
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
		
		while ( parent.isTimeSeries() && lastDate != null && endDate != null && endDate.compareTo(increment(lastDate, parent.getTimeUnit())) > 0 )
		 {

	         lastDate = increment(lastDate, parent.getTimeUnit());
	         ((DefaultCategoryDataset)parent.getCategoryDataset()).addValue(Env.ZERO,  Msg.getMsg(getCtx(), "NoData"), formatDate(lastDate, parent.getTimeUnit()));
		 }
		
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
	
	private Date increment(Date lastDate, String timeUnit) {
		return increment(lastDate, timeUnit, 1);
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

	public MQuery getZoomQuery(MChart parent, String value) {
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

		String keyCol = MTable.get(getCtx(), getAD_Table_ID()).getKeyColumns()[0];
		StringBuffer whereClause = new StringBuffer (keyCol)
		.append(" IN (").append(includedIds).append(")");
		query.addRestriction(whereClause.toString());
		query.setRecordCount(1);
		return query;
	}

}

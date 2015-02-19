/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Nikunj Panelia, ADAXA                                              *
 * ADEMPIERE-257 Update Seed Database - Fix dates in GardenWorld              *
 * https://adempiere.atlassian.net/browse/ADEMPIERE-257                       *
 *  			                                                              *
 *****************************************************************************/

package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MPeriod;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;

/**
 * A process to change the dates in GardenWorld for all existing transactions to
 * bring them into recent history
 *  
 * @author Nikunj Panelia
 *
 */
public class CleanUpGW extends SvrProcess 
{
	protected void prepare() {
		
		// TODO: add a parameter to make this a dictionary change or user change
		// TODO: add a parameter for the number of months to advance the dates
		// TODO: add a parameter to delete calendar years earlier than the earliest transaction
		
	}
	
	private Timestamp m_currentTime = new Timestamp(System.currentTimeMillis());
	private Timestamp m_minDate = m_currentTime;
	private Timestamp m_maxDate = new Timestamp(0);
	
	private long m_Offset = 0;
	
	private int gw_client_id = 11; // Hardcoded

	// This isn't all the date columns.  There a few tables that don't have AD_Client_ID
	private String m_sql=" SELECT t.tablename,c.ColumnName FROM AD_Column c "
			+  " JOIN AD_Table t ON c.AD_Table_ID=t.AD_Table_ID "
			+  " JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID) "
			+  " JOIN (SELECT nc.AD_Table_ID FROM AD_Table nc JOIN AD_Column c  "
			+  "      ON (nc.AD_Table_ID = c.AD_Table_ID) WHERE c.ColumnName = 'AD_Client_ID') nc "
			+  "   ON (nc.AD_Table_ID = t.AD_Table_ID) "
			+  " WHERE r.validationtype='D' and r.name in ('Date','DateTime') "
			+  " AND c.columnsql IS NULL AND t.tablename NOT LIKE 'I_%' AND t.tablename not like 'T_%' "
			+  " AND t.isview='N' AND upper(t.tableName) not like 'RV%' AND t.tableName <> 'C_Period' "
			+  " AND c.columnName NOT IN ('Created','Updated') "
			+  " AND t.EntityType='D'";

	// Don't include From/To dates in the min/max date ranges.  They can be set to anything.
	private String sqlWhereExclude = " AND c.columnName NOT LIKE '%From' AND c.columnName NOT LIKE '%To' ";
	
	private String sqlOrderBy = " ORDER BY t.tablename";
	
	protected String doIt() throws Exception 	
	{		
		if (!gardenWorldExists())
		{
			return "Garden World client can't be found.";
		}
		
		// Clean up unimportant dates
		// Delete all login records - 
		clearSessionLog();
		
		// Set the min/max date limits
		setDateLimits();

		// Determine the time offset
		determineOffset();
		
		// Adjust the calendars first
		adjustCalendarAndPeriods();
		
		// Apply the offset and update the dates.
		updateDates();

		// Point the periods to the correct dates.
		updatePeriods();
		
		// Cleanup
		cleanUp();

		return "Successfully updated Garden World data.";
	}
	
	private void updateDates() 
	{
		// Update the date columns
		PreparedStatement pstm=null;
		ResultSet rs=null;
		 try
		 {
			 pstm = DB.prepareStatement(m_sql+sqlOrderBy, get_TrxName());
			 rs = pstm.executeQuery();
			 String tableName=null;
			 String columnName=null;
			 ArrayList<String> columnList=new ArrayList<String>();
			 while(rs.next())
			 {	
				 tableName=rs.getString(1);
				 columnName=rs.getString(2);
				 		
				 updateTable(tableName,columnName);
				 
			 }
			 			 
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("", e);	 
		 }
		 finally {
			 DB.close(rs, pstm);
		 }

	}

	private void updatePeriods() 
	{
		// Update the date columns
		PreparedStatement pstm=null;
		ResultSet rs=null;
		 try
		 {
			 pstm = DB.prepareStatement(m_sql+sqlOrderBy, get_TrxName());
			 rs = pstm.executeQuery();
			 String tableName=null;
			 String columnName=null;
			 while(rs.next())
			 {	
				 tableName=rs.getString(1);
				 columnName=rs.getString(2);
				 		
				 setPeriods(tableName,columnName);
				 
			 }
			 			 
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("", e);	 
		 }
		 finally {
			 DB.close(rs, pstm);
		 }

	}

	private void updateTable(String tableName, String columnName)
	{
		
		if ( m_Offset == 0 || tableName.isEmpty() || columnName.isEmpty())
		{
			return;  // Nothing to do
		}

		// Convert offset to months
		long monthOffset = m_Offset/(1000L*60L*60L*24L*30L);  // 30 day months

		// Set only the date fields that have a value.  Leave the rest null.
		String sql="UPDATE "+ tableName + " SET  " + columnName + " = ("
				+ " CASE WHEN " + columnName + " > getdate() THEN add_months("+ columnName +", " + monthOffset + ") "  // columnName is already in the future, keep it there.
				+ "      WHEN add_months("+ columnName +", " + monthOffset + ") > getdate() THEN getdate() "  // columnName will be moved to the future, make it today instead
				+ "      ELSE add_months("+ columnName +", " + monthOffset + ")"
				+ " END ) "
				+ " WHERE " + columnName + " IS NOT NULL "  
				+ " AND AD_Client_ID=" + gw_client_id;
		
		PreparedStatement pstm=null;
		 try
		 {
			 pstm = DB.prepareStatement(sql, get_TrxName());
			 pstm.executeUpdate();
			
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("Problem in adding years to date columns", e);
		 }
		 finally {
			 DB.close(pstm);
		 }
		 
//		 //check if date less then 2010-01-01
//		 Timestamp timestamp = Timestamp.valueOf("2010-01-01 00:00:00.0");
//		 for(int i=0;i<columnList.size();i++)
//		 {
//			 StringBuffer sqlCheck=new StringBuffer();			
//			 sqlCheck.append( columnList.get(i)+ " = " +DB.TO_DATE(timestamp)+ " WHERE "+columnList.get(i)+" < " +DB.TO_DATE(timestamp));
//			 String sqlCkeck="UPDATE "+ tableName + " SET  " + sqlCheck.toString() + " AND AD_Client_ID=11 ";
//			
//			 try
//			 {
//				 pstm = DB.prepareStatement(sqlCkeck, null);
//				 pstm.executeUpdate();
//				
//			 }
//			 catch (SQLException e)
//			 {
//				 log.log(Level.SEVERE, e.getLocalizedMessage());
//				 throw new AdempiereException("Problem in updating date if it is less then 2010-01-01.", e);
//			 }
//			 finally {
//				 DB.close(pstm);
//			 }
//		 }

		
	}
	
	private void setPeriods(String tableName, String columnName)
	{
		 //update periods according to the new dates
		 String dateAcctColumn=null;
		 MTable table=MTable.get(getCtx(), tableName);
		 MColumn periodColumn=table.getColumn("C_Period_ID");
		 if(periodColumn!=null)
		 {
			 MColumn dateaAcctColumn=table.getColumn("DateAcct");
			 MColumn assetDepDateColumn=table.getColumn("AssetDepreciationDate");
			 if(dateaAcctColumn!=null)
				 dateAcctColumn=dateaAcctColumn.getColumnName();
			 else if(assetDepDateColumn!=null)
				 dateAcctColumn=assetDepDateColumn.getColumnName();
			 if(dateAcctColumn != null)
			 {
			 
				 log.fine("Table: " + tableName + " dateAcctColumn: " + dateAcctColumn);
				String updatePeriodSql="update "+tableName+" set "
						+ " C_Period_ID=(SELECT C_Period_ID from C_Period WHERE "+dateAcctColumn
						+ " BETWEEN StartDate and EndDate and AD_Client_ID=" + gw_client_id 
						+ ") WHERE AD_Client_ID=" + gw_client_id;
				
				PreparedStatement pstm = null;
				try
				{
					pstm = DB.prepareStatement(updatePeriodSql, get_TrxName());
					pstm.executeUpdate();
				
			 	}
			 	catch (SQLException e)
			 	{
			 		log.log(Level.SEVERE, e.getLocalizedMessage());
					throw new AdempiereException("Problem in updating periods according to new accounting values.", e);

			 		
			 	}
				finally {
					DB.close(pstm);
				}
			 }
		 }

	}
	private void findDateLimits(String tableName, String columnName)
	{

		String sql=	"SELECT MIN(" + columnName + "), MAX(" + columnName + ") from " + tableName 
					+ " WHERE AD_Client_ID=11 ";
		
		PreparedStatement pstm=null;
		ResultSet rs=null;
		 try
		 {
			 pstm = DB.prepareStatement(sql, get_TrxName());
			 rs = pstm.executeQuery();
			 while(rs.next())
			 {
				 if (rs.getTimestamp(1) == null || rs.getTimestamp(1).getTime() == 0L)
				 {
					 // Null or zero. Ignore it.
					 ;
				 }
				 else if (m_minDate.after(rs.getTimestamp(1)))
				 {
					 m_minDate = rs.getTimestamp(1);
					 log.fine("Setting min date to " + m_minDate.toString() + "(" + tableName + "/" + columnName + ")");
				 }
				 
				 if (rs.getTimestamp(2) == null || rs.getTimestamp(2).after(m_currentTime))
				 {
					 // Null or in the future. Ignore it.
					 continue;
				 }
				 else if (m_maxDate.before(rs.getTimestamp(2)))
				 {
					 m_maxDate = rs.getTimestamp(2);
					 log.fine("Setting max date to " + m_maxDate.toString() + "(" + tableName + "/" + columnName + ")");
				 }
			 }
			
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("Problem finding the current earliest date", e);
		 }
		 finally {
			 DB.close(pstm);
		 }
	}
	
	private void clearSessionLog() {

		String deleteSessionLogSQL = "DELETE FROM AD_Session where AD_Client_ID=" + gw_client_id;
		PreparedStatement pstm=null;
		 try
		 {
			 pstm = DB.prepareStatement(deleteSessionLogSQL, get_TrxName());
			 
			 pstm.executeUpdate();
			
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("Problem deleting the GardenWorld session log", e);
		 }
		 finally {
			 DB.close(pstm);
		 }

	}
	
	private Boolean gardenWorldExists() {

		StringBuilder whereClause = new StringBuilder();
		whereClause.append("AD_Client_ID=?");          // #1

		MClient gwClient = new Query(getCtx(), MClient.Table_Name, whereClause.toString(), null)
 		.setParameters(new Object[]{gw_client_id})
 		.first();
		if (gwClient != null && gwClient.getName().equals("GardenWorld"))
		{
			return true;
		}
		return false;
	}

	protected void setDateLimits() {
		// Find the minimum and maximum date in all the columns
		PreparedStatement pstm=null;
		ResultSet rs=null;
		 try
		 {
			 pstm = DB.prepareStatement(m_sql+sqlWhereExclude+sqlOrderBy, get_TrxName());
			 rs = pstm.executeQuery();
			 String tableName=null;
			 String columnName=null;
			 while(rs.next())
			 {	
				 tableName=rs.getString(1);
				 columnName = rs.getString(2);
		 		findDateLimits(tableName, columnName);
			 }
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("", e);	 
		 }
		 finally {
			 DB.close(rs, pstm);
		 }

	}
	
	private void determineOffset() {
		
		// Two years in milliseconds
		long twoYears = 1000L*60L*60L*24L*365L*2L;
		
		// Want to move the max date to today and the earliest date to two years previous
		long maxOffset = m_currentTime.getTime() - m_maxDate.getTime();
		long minOffset = m_currentTime.getTime() - twoYears - m_minDate.getTime();
		
		log.fine("Max offset - Years: " + maxOffset/(1000L*60L*60L*24L*365L));
		log.fine("Min offset - Years: " + minOffset/(1000L*60L*60L*24L*365L));
		
		if ( maxOffset < 0 || minOffset < 0 ) 
		{
			// We are already exceeding our limits
			m_Offset = 0;
			return;
		}
		if (maxOffset > minOffset)
		{
			// Move to have the max at today.  The min will be within the two years
			m_Offset = maxOffset;
		}
		else
		{
			// Move the min to within two years, the max will exceed today - be in the future
			// TODO - verify that the max data is possible
			m_Offset = minOffset;
		}

	}
	
	private void adjustCalendarAndPeriods()
	{
		if ( m_Offset == 0)
		{
			return; // nothing to do
		}
		
		 // Move the calendars by a year offset in milliseconds
		 long yearOffset = m_Offset/(1000L*60L*60L*24L*365L);  // round to years.
		 long monthOffset = yearOffset*12L;

		if ( monthOffset == 0)
		{
			return; // nothing to do
		}

		 //  Offset the periods 
		 String updatePeriod = "UPDATE C_Period SET StartDate = add_months(Startdate, " + monthOffset + "), "
				 			+  "   EndDate = add_months(Enddate, " + monthOffset + "), "
				 			+  "   Name = to_char(add_months(Enddate, " + monthOffset + "),'YYYY-MM') "
				 			+  " WHERE ad_client_id=" + gw_client_id;
		 DB.executeUpdate(updatePeriod, get_TrxName());
		 
		 // Offset the year - have to do this twice to avoid a constraint.
		 String updateYear = "UPDATE C_Year SET fiscalyear=(SELECT max(to_char(p.enddate,'YYYY-MM')) "
				 +  " from c_period p where p.c_year_id=c_year.c_year_id) "
		 			+  " WHERE ad_client_id=" + gw_client_id;
		 DB.executeUpdate(updateYear, get_TrxName());
		 updateYear = "UPDATE C_Year SET fiscalyear=(SELECT max(to_char(p.enddate,'YYYY')) "
				 +  " from c_period p where p.c_year_id=c_year.c_year_id) "
		 			+  " WHERE ad_client_id=" + gw_client_id;
		 DB.executeUpdate(updateYear, get_TrxName());
		 
//		 String updatem_Forcast="UPDATE m_forecast as y3 set c_year_id= "
//				 +  "  (select y1.c_year_id from c_year y1 join c_year y2 on "
//				 +  "    CAST (y1.fiscalyear as INT)=CAST (y2.fiscalyear as INT)+8 "
//				 +  "   where Y2.c_year_id=y3.c_year_id and y3.ad_client_id=" + gw_client_id + ")";
//		 DB.executeUpdate(updatem_Forcast, null);
//		 String sqlDeletePeriodctrl="DELETE FROM C_PeriodControl  where c_period_id in (select c_period_id from c_period where c_year_id in(select c_year_id from c_year where CAST (fiscalyear as INT) < 2010) ) and ad_client_id=11";
//		 DB.executeUpdate(sqlDeletePeriodctrl, null);
//		 String sqlDeletePeriod="DELETE  FROM c_period where c_year_id in(select c_year_id from c_year where CAST (fiscalyear as INT) < 2010)  and ad_client_id=11";
//		 DB.executeUpdate(sqlDeletePeriod, null);
//		 String sqlDeleteYear="DELETE  FROM c_year where CAST (fiscalyear as INT) < 2010  and ad_client_id=11";
//		 DB.executeUpdate(sqlDeleteYear, null);

	}
	
	private void cleanUp() {

		// Set bank statement names
		String deleteSessionLogSQL = "UPDATE C_BankStatement SET NAME = to_char(StatementDate, 'YYYY-MM_DD') where AD_Client_ID=" + gw_client_id;
		PreparedStatement pstm=null;
		 try
		 {
			 pstm = DB.prepareStatement(deleteSessionLogSQL, get_TrxName());
			 
			 pstm.executeUpdate();
			
		 }
		 catch (SQLException e)
		 {
			 log.log(Level.SEVERE, e.getLocalizedMessage());
			 throw new AdempiereException("Problem updating the bank statement names", e);
		 }
		 finally {
			 DB.close(pstm);
		 }

	}

}

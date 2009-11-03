/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MReportCube extends X_PA_ReportCube {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4771117572936231607L;

	public MReportCube(Properties ctx, int PA_ReportCube_ID, String trxName) {
		super(ctx, PA_ReportCube_ID, trxName);
	}

	public MReportCube(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public String update(boolean reset, boolean force) {
		
		String result = getName() + ": ";
		Timestamp ts = null;
		long start;
		long elapsed;
		
		String where = " WHERE PA_ReportCube_ID = " + getPA_ReportCube_ID();
		String periods = " (-1) ";
		if ( getLastRecalculated() != null && !reset )
		{
			StringBuilder periodList = new StringBuilder();
			StringBuilder periodNames = new StringBuilder();

			String sql = "SELECT DISTINCT p.C_Period_ID, p.Name FROM C_Period p " +
			 "INNER JOIN C_Year y ON (y.C_Year_ID=p.C_Year_ID) " +
			 "INNER JOIN PA_ReportCube c ON (c.C_Calendar_ID = y.C_Calendar_ID) " +
			 "INNER JOIN Fact_Acct fact ON (fact.dateacct between p.startdate and p.enddate " +
             "                      and fact.ad_client_id = c.ad_client_id) " +
			 "WHERE c.PA_ReportCube_ID = ? " +
			 "AND fact.updated > c.LastRecalculated " +
			 "AND p.periodtype='S' ";

			log.log (Level.FINE, sql);

			start = System.currentTimeMillis();
			KeyNamePair[] changedPeriods = DB.getKeyNamePairs(sql, false, getPA_ReportCube_ID());
			elapsed = (System.currentTimeMillis() - start)/1000;
			log.log(Level.FINE, "Selecting changed periods took:" + elapsed + "s");

			if (changedPeriods != null && changedPeriods.length > 0 )
			{
				periodList.append(" (");
				for (KeyNamePair p : changedPeriods )
				{
					periodList.append(p.getID() + ", ");
					periodNames.append(p.getName() + ", ");
				}
				periodList.delete(periodList.length() - 2, periodList.length());
				periodList.append(" )");

				log.log(Level.FINE, "Periods requiring update: " + periodNames.toString());
			}
			else
				return "Nothing to update in " + getName();

			periods = periodList.toString();
			where += (" AND C_Period_ID IN " + periods);
		}


		if ( !force )
		{
			String lockSQL = "UPDATE PA_ReportCube SET Processing = 'Y' " +
			"WHERE Processing = 'N' AND PA_ReportCube_ID = " + getPA_ReportCube_ID();
			int locked = DB.executeUpdateEx(lockSQL, get_TrxName());
			if (locked != 1)
			{
				throw new AdempiereException("Unable to lock cube for update:" + getName());
			}
		}
		try 
		{
			// delete
			String delSQL = "DELETE FROM Fact_Acct_Summary fas " + where;
			log.log(Level.FINE, "Delete sql: " + delSQL);
			start = System.currentTimeMillis();
			int deleted = DB.executeUpdateEx(delSQL, get_TrxName());
			elapsed = (System.currentTimeMillis() - start)/1000;
			result += "Deleted " + deleted + " in " + elapsed + " s;";
			
			log.log(Level.FINE, result);

			// insert
			StringBuilder insert = new StringBuilder("INSERT " +
					"INTO FACT_ACCT_SUMMARY (PA_ReportCube_ID , AD_Client_ID, " +
					"AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, " +
					"C_AcctSchema_ID, Account_ID, PostingType, " +
			"GL_Budget_ID, C_Period_ID, DateAcct, AmtAcctDr, AmtAcctCr, Qty");

			StringBuilder select = new StringBuilder(" ) SELECT " +
					"?, f.AD_CLIENT_ID, f.AD_ORG_ID, " +
					"max(f.Created), max(f.CreatedBy), max(f.Updated), max(f.UpdatedBy), 'Y', " +
					"f.C_ACCTSCHEMA_ID, f.ACCOUNT_ID, f.POSTINGTYPE, GL_Budget_ID, " +
					"p.c_period_id,	p.StartDate, COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), " +
			"COALESCE(SUM(Qty),0)");
			String from = " FROM fact_acct f " +
			" INNER JOIN C_Period p ON ( f.DateAcct BETWEEN p.StartDate AND p.EndDate ) " +
			" INNER JOIN C_Year y ON ( p.C_Year_ID = y.C_Year_ID ) " +
			" WHERE p.PeriodType = 'S' " +
			" AND y.C_Calendar_ID = ? ";
			if ( getLastRecalculated() != null && !reset )
				from += "AND  p.C_Period_ID IN " + periods;

			StringBuilder groups = new StringBuilder(" GROUP BY " +
					"f.AD_CLIENT_ID, f.AD_ORG_ID, f.C_ACCTSCHEMA_ID, f.ACCOUNT_ID, " +
			"f.POSTINGTYPE, GL_Budget_ID, p.c_period_id, p.StartDate ");

			ArrayList<String> values = new ArrayList<String>();

			if ( isProductDim() )	
				values.add("M_Product_ID");
			if ( isBPartnerDim() )
				values.add("C_BPartner_ID");
			if ( isProjectDim() )
				values.add("C_Project_ID");
			if ( isOrgTrxDim() )
				values.add("AD_OrgTrx_ID");
			if ( isSalesRegionDim() )
				values.add("C_SalesRegion_ID");
			if ( isActivityDim() )
				values.add("C_Activity_ID");
			if ( isCampaignDim() )
				values.add("C_Campaign_ID");
			if ( isLocToDim() )
				values.add("C_LocTo_ID");
			if ( isLocFromDim() )
				values.add("C_LocFrom_ID");
			if ( isUser1Dim() )
				values.add("User1_ID");
			if ( isUser2Dim() )
				values.add("User2_ID");
			if ( isUserElement1Dim() )
				values.add("UserElement1_ID");
			if ( isUserElement2Dim() )
				values.add("UserElement2_ID");
			if ( isSubAcctDim() )
				values.add("C_SubAcct_ID");
			if ( isProjectPhaseDim() )
				values.add("C_ProjectPhase_ID");
			if ( isProjectTaskDim() )
				values.add("C_ProjectTask_ID");
			
			//  --(CASE v.IsGL_Category_ID WHEN 'Y' THEN f."GL_Category_ID END) GL_Category_ID

			Iterator<String> iter = values.iterator();
			while ( iter.hasNext() )
			{
				String dim = iter.next();
				insert.append(", " + dim );
				select.append(", f." + dim);
				groups.append(", f." + dim);
			}


			String sql = insert.append(select.toString()).append(from).append(groups.toString()).toString();
			log.log(Level.FINE, sql);
			Object[] params = new Object[] { getPA_ReportCube_ID(), getC_Calendar_ID() };

			start = System.currentTimeMillis();
			int rows = DB.executeUpdateEx(sql, params, get_TrxName());
			long seconds = (System.currentTimeMillis() - start)/1000;
			
			String insertResult = "Inserted " + rows  + " in " + seconds + " s.";
			log.log(Level.FINE, insertResult);
			result += insertResult;
			

			// set timestamp
			String tsSQL = "SELECT max(fas.Updated)" +
			" FROM Fact_Acct_Summary fas" +
			" WHERE fas.PA_ReportCube_ID = " + getPA_ReportCube_ID();
			ts = DB.getSQLValueTS(get_TrxName(), tsSQL);
			log.log(Level.FINE, "Last updated: " + ts);
			
		}
		catch (DBException e)
		{
			// failure results in null timestamp => rebuild on next run
			// nothing else to do
			log.log(Level.FINE, getName() + " update failed:" + e.getMessage());
		}
		finally
		{
			// unlock
			String unlockSQL = "UPDATE PA_ReportCube SET Processing = 'N', " +
			"LastRecalculated = " + ( ts == null ? "null" : "?") +
			" WHERE PA_ReportCube_ID = " + getPA_ReportCube_ID();
			Object[] parameters = ts == null ? new Object[] {} : new Object[] {ts};
			DB.executeUpdateEx(unlockSQL, parameters, get_TrxName());
		}
		return result;
	}
}

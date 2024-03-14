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
package org.compiere.report;

import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MPeriod;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *  Statement of Account
 *
 *  @author Jorg Janke
 *  @version $Id: FinStatement.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 *  @author Low Heng Sin
 *  - Remove update balance option to resolved Feature Request [ 1557707 ] and
 *    bug [1619917]
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *	@author Armen Rizal, Goodwill Consulting
 *			<li>FR [2857076] User Element 1 and 2 completion - https://sourceforge.net/tracker/?func=detail&aid=2857076&group_id=176962&atid=879335
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 92 ] 
 *		@see https://github.com/adempiere/adempiere/issues/92
 *   
 */
public class FinStatement extends FinStatementAbstract
{
	/** AcctSchame Parameter			*/
	/**	Parameter Where Clause			*/
	private StringBuilder		parameterWhere = new StringBuilder();


	/**	Start Time						*/
	private long timeStartProcess = System.currentTimeMillis();

	/**
	 *  Prepare - e.g., get Parameters.
	 */




	/**************************************************************************
	 *  Perform process.
	 *  @return Message to be translated
	 */
	protected String doIt()
	{
		setWhereClause();
		createBalanceLine();
		createDetailLines();
		log.fine((System.currentTimeMillis() - timeStartProcess) + " ms");
		return "";
	}	//	doIt

	private void setWhereClause(){
		//	Mandatory C_AcctSchema_ID, PostingType
		parameterWhere.append("C_AcctSchema_ID=").append(getAcctSchemaId())
				.append(" AND PostingType='").append(getPostingType()).append("'");
		//	Optional Account_ID
		if (getAccountId() >0) {
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Account, getAccountId()));
		}

		//	Optional Org
		if (getOrgId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Organization, getOrgId()));
		//	Optional BPartner
		if (getBPartnerId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_BPartner, getBPartnerId()));
		//	Optional Product
		if (getProductId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Product, getProductId()));
		//	Optional Project
		if (getProjectId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Project, getProjectId()));
		//	Optional Activity
		if (getActivityId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Activity, getActivityId()));
		//	Optional Campaign
		if (getCampaignId() != 0)
			parameterWhere.append(" AND C_Campaign_ID=").append(getCampaignId());
		//	m_parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), 
		//		MAcctSchemaElement.ELEMENTTYPE_Campaign, p_C_Campaign_ID));
		//	Optional Sales Region
		if (getSalesRegionId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_SalesRegion, getSalesRegionId()));
		//	Optional User1_ID
		if (getUser1Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList1, getUser1Id()));
		//  Optional User2_ID
		if (getUser2Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList2, getUser2Id()));

		setDateAcct();
		StringBuffer logMessage = new StringBuffer();
		logMessage.append(" - DateAcct ").append(getDateAcct()).append("-").append(getDateAcctTo());
		logMessage.append(" - Where=").append(parameterWhere);
		log.fine(logMessage.toString());
	}

	private void setDateAcct() {
		//	Date defined
		if (getDateAcct() != null) {
			if (getDateAcctTo() == null)
				setDateAcctTo(new Timestamp(System.currentTimeMillis()));
			return;
		}
		//	Get Date from Period
		if (getPeriodId() == 0) {
			GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);        //	set to first of month
			setDateAcct(new Timestamp(cal.getTimeInMillis()));
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);        //	last of month
			setDateAcctTo(new Timestamp(cal.getTimeInMillis()));
			return;
		}
		MPeriod period = new MPeriod(getCtx(),getPeriodId(),get_TrxName());
		setDateAcct(period.getStartDate());
		setDateAcctTo(period.getEndDate());
	}

	/**
	 * 	Create Beginning Balance Line
	 */
	private void createBalanceLine()
	{
		StringBuilder where = new StringBuilder();
		List<Object> parameters = new ArrayList<>();
		where.append(" WHERE ").append(parameterWhere).append(" AND DateAcct < ").append("?");
		StringBuffer insertBalanceLine = new StringBuffer("INSERT INTO T_ReportStatement "
				+ "(AD_PInstance_ID, Fact_Acct_ID, LevelNo,"
				+ "DateAcct, Name, Description,"
				+ "AmtAcctDr, AmtAcctCr, Balance, Qty, ACCOUNT_ID, AccountValue, AccountName, AccountType) ");
		insertBalanceLine.append("SELECT ").append("?").append(",0,0,")
				.append("?").append(",")
				.append("?").append(",NULL,")
				.append("COALESCE(fa.AmtAcctDr, 0) AmtAcctDr, ")
				.append("COALESCE(fa.AmtAcctCr, 0) AmtAcctCr, ")
				.append("COALESCE(fa.Balance,0) Balance, ")
				.append("COALESCE(fa.Qty, 0) Qty, ")
				.append("ev.C_ElementValue_ID , ev.Value, ev.Name, ev.AccountType ")
				.append("FROM C_ElementValue ev ")
				.append("INNER JOIN C_Element e ON (ev.C_Element_ID=e.C_Element_ID) ")
				.append("LEFT JOIN (SELECT ")
							.append("Account_ID,")
							.append("SUM(AmtAcctDr) AmtAcctDr,")
							.append("SUM(AmtAcctCr) AmtAcctCr,")
							.append("SUM(AcctBalance(fa.Account_ID, fa.AmtAcctDr, fa.AmtAcctCr)) Balance, ")
							.append("SUM(AcctBalance(Account_ID, Qty, 0)) Qty ")
							.append("FROM Fact_Acct fa ")
							.append(where)
							.append(" GROUP BY fa.Account_ID) fa ON (fa.Account_ID = ev.C_ElementValue_ID) ")
				.append("WHERE e.ElementType = ? ");
		//Set parameters
		parameters.add(getAD_PInstance_ID());
		parameters.add(getDateAcct());
		parameters.add(Msg.getMsg(Env.getCtx(), "BeginningBalance"));
		parameters.add(getDateAcct());
		parameters.add("A");

		if (getAccountId() > 0) {
			insertBalanceLine.append(" AND  ev.C_ElementValue_ID = ? ");
			parameters.add(getAccountId());
		}

		if (getAccountType() != null && !getAccountType().isEmpty()) {
			insertBalanceLine.append(" AND ev.AccountType = ? ");
			parameters.add(getAccountType());
		}

		//Client Filter 
		insertBalanceLine.append(" AND ev.AD_Client_ID = ? ");
		parameters.add(getAD_Client_ID());
		
		int no = DB.executeUpdateEx(insertBalanceLine.toString() , parameters.toArray(), get_TrxName());
		log.fine("#" + no + " (Account_ID=" + getAccountId() + ")");
		log.finest(insertBalanceLine.toString());
	}	//	createBalanceLine

	/**
	 * 	Create Beginning Balance Line
	 */
	private void createDetailLines()
	{
		List<Object> parameters = new ArrayList<>();
		StringBuffer insertDetailLine = new StringBuffer ("INSERT INTO T_ReportStatement "
			+ "(AD_PInstance_ID, Fact_Acct_ID, LevelNo,"
			+ "DateAcct, Name, Description,"
			+ "AmtAcctDr, AmtAcctCr, Balance, Qty, ACCOUNT_ID , AccountValue, AccountName, AccountType ) ");
		insertDetailLine.append("SELECT ? ").append(",fact_Acct.Fact_Acct_ID,1,")
			.append("fact_Acct.DateAcct,NULL,NULL,"
			+ "AmtAcctDr, AmtAcctCr, AmtAcctDr-AmtAcctCr, Qty, fact_Acct.ACCOUNT_ID, ev.value, ev.name, ev.AccountType "
			+ "FROM Fact_Acct "
			+ " INNER JOIN (SELECT ev.c_ElementValue_ID,ev.Value, ev.Name, ev.AccountType, ev.AD_Client_ID FROM C_ElementValue ev ) ev on (fact_Acct.account_ID = ev.c_ElementValue_ID) "
			+ "WHERE ").append(parameterWhere)
			.append(" AND DateAcct BETWEEN ? ")
			.append(" AND ").append("?");
		
		//Client Filter 
		insertDetailLine.append(" AND ev.AD_Client_ID = ? ");
		parameters.add(getAD_PInstance_ID());
		parameters.add(getDateAcct());
		parameters.add(getDateAcctTo());
		parameters.add(getAD_Client_ID());
		//
		int no = DB.executeUpdateEx(insertDetailLine.toString(), parameters.toArray(), get_TrxName());
		log.fine("#" + no);
		log.finest(insertDetailLine.toString());

		List<Object> trlParameters = new ArrayList<>();
		//	Set Name,Description
		Language currentLanguage = Env.getLanguage(getCtx());
		Boolean isBaseLanguage = currentLanguage.isBaseLanguage();
		String selectFieldsForUpdateDetailLine = " e.Name, fa.Description ";
		if (!isBaseLanguage)
			selectFieldsForUpdateDetailLine = "etrl.Name, fa.Description ";
		StringBuilder selectForUpdateDetailLine = new StringBuilder("SELECT ");
		selectForUpdateDetailLine.append(selectFieldsForUpdateDetailLine)
		.append("FROM Fact_Acct fa")
		.append(" INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)")
		.append(" INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) ");
		if (!isBaseLanguage) {
			selectForUpdateDetailLine.append(" INNER JOIN AD_Element_Trl etrl ON (e.AD_Element_ID=etrl.AD_Element_ID AND AD_Language = ? ) ");
			trlParameters.add(currentLanguage.getAD_Language());
		}
		selectForUpdateDetailLine.append(" WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID");
		StringBuilder updateTrlDetailLine = new StringBuilder ("UPDATE T_ReportStatement r SET (Name,Description)=(")
			.append(selectForUpdateDetailLine).append(") "
			+ "WHERE Fact_Acct_ID <> 0 AND AD_PInstance_ID=? ");

		trlParameters.add(getAD_PInstance_ID());
		//
	   no = DB.executeUpdateEx(updateTrlDetailLine.toString() , trlParameters.toArray(), get_TrxName());
	   log.fine("Name #" + no);
	   log.finest("Name - " + insertDetailLine);

	}	//	createDetailLines

}	//	FinStatement

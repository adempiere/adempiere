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
import org.compiere.model.MElementValue;
import org.compiere.model.MPeriod;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;

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
	private StringBuffer		parameterWhere = new StringBuffer();
	/**	Account							*/ 
	private MElementValue 		m_acct = null;


	/**	Start Time						*/
	private long 				m_start = System.currentTimeMillis();

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
		log.fine((System.currentTimeMillis() - m_start) + " ms");
		return "";
	}	//	doIt

	private void setWhereClause(){
		//	Mandatory C_AcctSchema_ID, PostingType
		parameterWhere.append("C_AcctSchema_ID=").append(getAcctSchemaId())
				.append(" AND PostingType='").append(getPostingType()).append("'");
		//	Optional Account_ID
		if (getAccountId() >0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(),
					getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Account, getAccountId()));

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

		if (getAccountType() != null){
			parameterWhere.append(" AND AccountType='").append(getAccountType()).append("'");
		}
		setDateAcct();
		StringBuffer sb = new StringBuffer();
		sb.append(" - DateAcct ").append(getDateAcct()).append("-").append(getDateAcctTo());
		sb.append(" - Where=").append(parameterWhere);
		log.fine(sb.toString());
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
		where.append(" WHERE Account_ID = ev.C_ElementValue_ID AND ").append(parameterWhere).append(" AND TRUNC(DateAcct, 'DD') < ").append(DB.TO_DATE(getDateAcct()));
		StringBuffer sb = new StringBuffer("INSERT INTO T_ReportStatement "
				+ "(AD_PInstance_ID, Fact_Acct_ID, LevelNo,"
				+ "DateAcct, Name, Description,"
				+ "AmtAcctDr, AmtAcctCr, Balance, Qty, ACCOUNT_ID, accountvalue, accountName, accountType) ");
		sb.append("SELECT ").append(getAD_PInstance_ID()).append(",0,0,")
				.append(DB.TO_DATE(getDateAcct(), true)).append(",")
				.append(DB.TO_STRING(Msg.getMsg(Env.getCtx(), "BeginningBalance"))).append(",NULL,")
				.append("COALESCE((SELECT SUM(AcctBalance(Account_ID, AmtAcctDr , 0         )) FROM Fact_Acct ").append(where).append(" ),0), ")
				.append("COALESCE((SELECT SUM(AcctBalance(Account_ID, 0         , AmtAcctCr )) FROM Fact_Acct ").append(where).append(" ),0), ")
				.append("COALESCE((SELECT SUM(AcctBalance(Account_ID, AmtAcctDr , 0 ) - AcctBalance(Account_ID, 0 , AmtAcctCr )) FROM Fact_Acct ").append(where).append(" ),0), ")
				.append("COALESCE((SELECT SUM(AcctBalance(Account_ID, Qty       , 0         )) FROM Fact_Acct ").append(where).append(" ),0), ")
				.append("ev.C_ElementValue_ID , ev.value, ev.name, ev.accounttype ")
				.append(" FROM C_ElementValue ev INNER JOIN C_Element e ON (ev.C_Element_ID=e.C_Element_ID) WHERE e.ElementType = 'A' ");

		if (getAccountId() > 0)
			sb.append(" AND  ev.C_ElementValue_ID = ").append(getAccountId());

		if (getAccountType() != null && !getAccountType().isEmpty())
			sb.append(" AND  ev.AccountType = '").append(getAccountType()).append("'");

		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		log.fine("#" + no + " (Account_ID=" + getAccountId() + ")");
		log.finest(sb.toString());
	}	//	createBalanceLine

	/**
	 * 	Create Beginning Balance Line
	 */
	private void createDetailLines()
	{
		StringBuffer sb = new StringBuffer ("INSERT INTO T_ReportStatement "
			+ "(AD_PInstance_ID, Fact_Acct_ID, LevelNo,"
			+ "DateAcct, Name, Description,"
			+ "AmtAcctDr, AmtAcctCr, Balance, Qty, ACCOUNT_ID , accountvalue, accountName, accounttype ) ");
		sb.append("SELECT ").append(getAD_PInstance_ID()).append(",fact_Acct.Fact_Acct_ID,1,")
			.append("TRUNC(fact_Acct.DateAcct, 'DD'),NULL,NULL,"
			+ "AmtAcctDr, AmtAcctCr, AmtAcctDr-AmtAcctCr, Qty, fact_Acct.ACCOUNT_ID, ev.value, ev.name, accounttype "
			+ "FROM Fact_Acct "
			+ " INNER JOIN C_Elementvalue ev on fact_Acct.account_ID = ev.c_ElementValue_ID "
			+ "WHERE ").append(parameterWhere)
			.append(" AND TRUNC(DateAcct, 'DD') BETWEEN ").append(DB.TO_DATE(getDateAcct()))
			.append(" AND ").append(DB.TO_DATE(getDateAcctTo()));
		//
		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		log.fine("#" + no);
		log.finest(sb.toString());

		//	Set Name,Description
		Language currentLanguage = Env.getLanguage(getCtx());
		Boolean isBaseLanguage = currentLanguage.isBaseLanguage();
		String selectFields = " e.Name, fa.Description ";
		if (!isBaseLanguage)
			selectFields = "etrl.Name, fa.Description ";
		StringBuffer sql_select = new StringBuffer("SELECT ");
		sql_select.append(selectFields)
		.append("FROM Fact_Acct fa")
		.append(" INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)")
		.append(" INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) ");
		if (!isBaseLanguage)
			sql_select.append(" INNER JOIN AD_element_trl etrl ON (e.ad_element_ID=etrl.ad_element_ID AND AD_Language = '" + currentLanguage.getAD_Language() + "')");
		sql_select.append(" WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID");
		StringBuffer updateSql = new StringBuffer ("UPDATE T_ReportStatement r SET (Name,Description)=(")
			.append(sql_select).append(") "
			+ "WHERE Fact_Acct_ID <> 0 AND AD_PInstance_ID=").append(getAD_PInstance_ID());
		//
	   no = DB.executeUpdate(updateSql.toString(), get_TrxName());
	   log.fine("Name #" + no);
	   log.finest("Name - " + sb);

	}	//	createDetailLines

}	//	FinStatement

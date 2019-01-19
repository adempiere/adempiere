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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.impexp.BankStatementMatcherInterface;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Bank Statement Matcher Algorithm
 *	
 *  @author Jorg Janke
 *  @version $Id: MBankStatementMatcher.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MBankStatementMatcher extends X_C_BankStatementMatcher {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3756318777177414260L;
	
	/**	Cache						*/
	private static CCache<String, List<MBankStatementMatcher>> cache = new CCache<String, List<MBankStatementMatcher>>(Table_Name, 40, 5);	//	5 minutes
	/** Static Cache */
	private static CCache<Integer, MBankStatementMatcher> matchersCacheIds = new CCache<Integer, MBankStatementMatcher>(Table_Name, 30);

	/**
	 * 	Get Bank Statement Matcher Algorithms
	 * 	@param ctx context
	 *	@param trxName transaction
	 *	@return matchers
	 */
	public static MBankStatementMatcher[] getMatchers (Properties ctx, String trxName) {
		List<MBankStatementMatcher> list = getMatchersList(ctx, 0);
		if(list != null) {
			MBankStatementMatcher[] retValue = new MBankStatementMatcher[list.size()];
			list.toArray(retValue);
			return retValue;
		}
		//	
		return null;
	}	//	getMatchers
	
	/**
	 * Get Matcher from bank
	 * @param ctx
	 * @param bankId
	 * @return
	 */
	public static List<MBankStatementMatcher> getMatchersList(Properties ctx, int bankId) {
		String key = Env.getAD_Client_ID(ctx) + "|" + bankId;
		List<MBankStatementMatcher> matcherList = cache.get(key);
		if(matcherList == null) {
			s_log.fine("Not from cache");
			StringBuffer whereClause = new StringBuffer();
			if(bankId > 0) {
				whereClause.append("EXISTS(SELECT 1 FROM C_BankMatcher bm WHERE bm.C_Bank_ID = ")
					.append(bankId).append(" AND bm.C_BankStatementMatcher_ID = C_BankStatementMatcher.C_BankStatementMatcher_ID)");
			}
			//	
			matcherList = new Query(ctx, Table_Name, whereClause.toString(), null)
				.setOrderBy(COLUMNNAME_SeqNo)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.list();
			//	Set
			if(matcherList != null) {
				cache.put(key, matcherList);
			}
		}
		//	
		return matcherList;
	}

	/** Static Logger					*/
	private static CLogger 	s_log = CLogger.getCLogger(MBankStatementMatcher.class);

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_BankStatementMatcher_ID id
	 *	@param trxName transaction
	 */
	public MBankStatementMatcher(Properties ctx, int C_BankStatementMatcher_ID, String trxName)
	{
		super(ctx, C_BankStatementMatcher_ID, trxName);
	}	//	MBankStatementMatcher

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBankStatementMatcher(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBankStatementMatcher

	private BankStatementMatcherInterface	m_matcher = null;
	private Boolean							m_matcherValid = null;

	/**
	 * 	Is Matcher Valid
	 *	@return true if valid
	 */
	public boolean isMatcherValid()
	{
		if (m_matcherValid == null)
			getMatcher();
		return m_matcherValid.booleanValue();
	}	//	isMatcherValid

	/**
	 * 	Get Matcher 
	 *	@return Matcher Instance
	 */
	public BankStatementMatcherInterface getMatcher()
	{
		if (m_matcher != null 
			|| (m_matcherValid != null && m_matcherValid.booleanValue()))
			return m_matcher;
			
		String className = getClassname();
		if (className == null || className.length() == 0)
			return null;
		
		try
		{
			Class matcherClass = Class.forName(className);
			m_matcher = (BankStatementMatcherInterface)matcherClass.newInstance();
			m_matcherValid = Boolean.TRUE;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, className, e);
			m_matcher = null;
			m_matcherValid = Boolean.FALSE;
		}
		return m_matcher;
	}	//	getMatcher
	
	/**
	 * Get/Load Matcher [CACHED]
	 * @param ctx context
	 * @param matcherId
	 * @return matcher or null
	 */
	public static MBankStatementMatcher getById(Properties ctx, int matcherId) {
		if (matcherId <= 0)
			return null;

		MBankStatementMatcher matcher = matchersCacheIds.get(matcherId);
		if (matcher != null && matcher.get_ID() > 0)
			return matcher;

		matcher = new Query(ctx , Table_Name , COLUMNNAME_C_BankStatementMatcher_ID + "=?" , null)
				.setClient_ID()
				.setParameters(matcherId)
				.first();
		if (matcher != null && matcher.get_ID() > 0) {
			matchersCacheIds.put(matcher.get_ID(), matcher);
		}
		return matcher;
	}
}	//	MBankStatementMatcher

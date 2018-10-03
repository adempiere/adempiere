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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  Account Object Entity to maintain all segment values.
 * 	C_ValidCombination
 *
 *  @author		Jorg Janke
 *  @author     victor.perez@e-evolution.com, www.e-evolution.com
 *    			<li>RF [ 2214883 ] Remove SQL code and Replace for Query http://sourceforge.net/tracker/index.php?func=detail&aid=2214883&group_id=176962&atid=879335
 *  @author     Teo Sarca, www.arhipac.ro
 *    			<li>FR [ 2694043 ] Query. first/firstOnly usage best practice
 *  @version 	$Id: MAccount.java,v 1.4 2006/07/30 00:58:04 jjanke Exp $
 */
public class MAccount extends X_C_ValidCombination
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1936396369349550834L;

	
	
	/**
	 * 	Get existing Account or create it 
	 *	@param ctx context
	 *	@param clientId
	 *	@param orgId
	 *	@param acctSchemaId
	 *	@param accountId
	 *	@param subAcctId
	 *	@param productId
	 *	@param partnerId
	 *	@param orgTrxId
	 *	@param locFromId
	 *	@param locToId
	 *	@param salesRegionId
	 *	@param projectId
	 *	@param campaignId
	 *	@param activityId
	 *	@param user1Id
	 *	@param User2Id
	 *	@param userElement1Id
	 *	@param userElement2Id
	 *	@return account or null
	 * @deprecated Use {@link #get(Properties,int,int,int,int,int,int,int,int,int,int,int,int,int,int,int,int,int,int,String)} instead
	 */
	@Deprecated
	public static MAccount get (Properties ctx,
		int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID, 
		int Account_ID, int C_SubAcct_ID,
		int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID, 
		int C_LocFrom_ID, int C_LocTo_ID, int C_SalesRegion_ID, 
		int C_Project_ID, int C_Campaign_ID, int C_Activity_ID,
		int User1_ID, int User2_ID, int UserElement1_ID, int UserElement2_ID)
	{
		return get(ctx, AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, Account_ID,
				C_SubAcct_ID, M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID,
				C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID, C_Project_ID,
				C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, 0 , 0 ,
				UserElement1_ID, UserElement2_ID, null);
	}	//	get

	/**
	 * Get Account
	 * @param ctx
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param C_AcctSchema_ID
	 * @param Account_ID
	 * @param C_SubAcct_ID
	 * @param M_Product_ID
	 * @param C_BPartner_ID
	 * @param AD_OrgTrx_ID
	 * @param C_LocFrom_ID
	 * @param C_LocTo_ID
	 * @param C_SalesRegion_ID
	 * @param C_Project_ID
	 * @param C_Campaign_ID
	 * @param C_Activity_ID
	 * @param User1_ID
	 * @param User2_ID
	 * @param UserElement1_ID
	 * @param UserElement2_ID
	 * @param trxName
	 * @return
	 */
	@Deprecated
	public static MAccount get (Properties ctx,
								int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID,
								int Account_ID, int C_SubAcct_ID,
								int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID,
								int C_LocFrom_ID, int C_LocTo_ID, int C_SalesRegion_ID,
								int C_Project_ID, int C_Campaign_ID, int C_Activity_ID,
								int User1_ID, int User2_ID , int UserElement1_ID, int UserElement2_ID, String trxName)
	{
		return MAccount.get(ctx, AD_Client_ID , AD_Org_ID , C_AcctSchema_ID ,
				Account_ID , C_SubAcct_ID ,
				M_Product_ID , C_BPartner_ID , AD_OrgTrx_ID ,
				C_LocFrom_ID , C_LocTo_ID , C_SalesRegion_ID ,
				C_Project_ID, C_Campaign_ID , C_Activity_ID, User1_ID , User2_ID , 0 , 0 , UserElement1_ID , UserElement2_ID, trxName);
	}

	/**
	 * 	Get existing Account or create it 
	 *	@param ctx context
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param C_AcctSchema_ID
	 * @param Account_ID
	 * @param C_SubAcct_ID
	 * @param M_Product_ID
	 * @param C_BPartner_ID
	 * @param AD_OrgTrx_ID
	 * @param C_LocFrom_ID
	 * @param C_LocTo_ID
	 * @param C_SalesRegion_ID
	 * @param C_Project_ID
	 * @param C_Campaign_ID
	 * @param C_Activity_ID
	 * @param User1_ID
	 * @param User2_ID
	 * @param User3_ID
	 * @param User4_ID
	 * @param UserElement1_ID
	 * @param UserElement2_ID
	 * @param trxName TODO
	 *	@return account or null
	 */
	public static MAccount get (Properties ctx, 
		int clientId, int orgId, int acctSchemaId,
		int accountId, int subAcctId,
		int productId, int partnerId, int orgTrxId,
		int locFromId, int locToId, int salesRegionId,
		int projectId, int campaignId, int activityId,
		int user1Id, int User2Id, int user3Id, int user4Id , int userElement1Id, int userElement2Id, String trxName)
	{
		StringBuffer info = new StringBuffer();
		info.append("AD_Client_ID=").append(clientId).append(",AD_Org_ID=").append(orgId);
		//	Schema
		info.append(",C_AcctSchema_ID=").append(acctSchemaId);
		//	Account
		info.append(",Account_ID=").append(accountId).append(" ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		//		Mandatory fields
		StringBuffer whereClause =  new StringBuffer("AD_Client_ID=?"		//	#1
							+ " AND AD_Org_ID=?"
							+ " AND C_AcctSchema_ID=?"
							+ " AND Account_ID=?");			//	#4
		params.add(clientId);
		params.add(orgId);
		params.add(acctSchemaId);
		params.add(accountId);
		//	Optional fields
		if (subAcctId == 0)
			whereClause.append(" AND C_SubAcct_ID IS NULL");	
		else
		{	
			whereClause.append(" AND C_SubAcct_ID=?");
			params.add(subAcctId);
		}
		if (productId == 0)
			whereClause.append(" AND M_Product_ID IS NULL");
		else
		{
			whereClause.append(" AND M_Product_ID=?");
			params.add(productId);
		}	
		if (partnerId == 0)
			whereClause.append(" AND C_BPartner_ID IS NULL");
		else
		{
			whereClause.append(" AND C_BPartner_ID=?");
			params.add(partnerId);
		}
		if (orgTrxId == 0)
			whereClause.append(" AND AD_OrgTrx_ID IS NULL");
		else
		{	
			whereClause.append(" AND AD_OrgTrx_ID=?");
			params.add(orgTrxId);
		}
		if (locFromId == 0)
			whereClause.append(" AND C_LocFrom_ID IS NULL");
		else
		{
			whereClause.append(" AND C_LocFrom_ID=?");
			params.add(locFromId);
		}
		if (locToId == 0)
			whereClause.append(" AND C_LocTo_ID IS NULL");
		else
		{
			whereClause.append(" AND C_LocTo_ID=?");
			params.add(locToId);
		}	
		if (salesRegionId == 0)
			whereClause.append(" AND C_SalesRegion_ID IS NULL");
		else
		{
			whereClause.append(" AND C_SalesRegion_ID=?");
			params.add(salesRegionId);
		}
		if (projectId == 0)
			whereClause.append(" AND C_Project_ID IS NULL");
		else
		{	
			whereClause.append(" AND C_Project_ID=?");
			params.add(projectId);
		}
		if (campaignId == 0)
			whereClause.append(" AND C_Campaign_ID IS NULL");
		else
		{	
			whereClause.append(" AND C_Campaign_ID=?");
			params.add(campaignId);
		}
		if (activityId == 0)
			whereClause.append(" AND C_Activity_ID IS NULL");
		else
		{
			whereClause.append(" AND C_Activity_ID=?");
			params.add(activityId);
		}
		if (user1Id == 0)
			whereClause.append(" AND User1_ID IS NULL");
		else
		{
			whereClause.append(" AND User1_ID=?");
			params.add(user1Id);
		}
		if (User2Id == 0)
			whereClause.append(" AND User2_ID IS NULL");
		else
		{
			whereClause.append(" AND User2_ID=?");
			params.add(User2Id);
		}
		if (user3Id == 0)
			whereClause.append(" AND User3_ID IS NULL");
		else
		{
			whereClause.append(" AND User3_ID=?");
			params.add(user3Id);
		}
		if (user4Id == 0)
			whereClause.append(" AND User4_ID IS NULL");
		else
		{
			whereClause.append(" AND User4_ID=?");
			params.add(user4Id);
		}
		if (userElement1Id == 0)
			whereClause.append(" AND UserElement1_ID IS NULL");
		else
		{	
			whereClause.append(" AND UserElement1_ID=?");
			params.add(userElement1Id);
		}
		if (userElement2Id == 0)
			whereClause.append(" AND UserElement2_ID IS NULL");
		else
		{
			whereClause.append(" AND UserElement2_ID=?");
			params.add(userElement2Id);
		}
		//	whereClause.append(" ORDER BY IsFullyQualified DESC");
		
		MAccount existingAccount = new Query(ctx, MAccount.Table_Name, whereClause.toString(), trxName)
										.setClient_ID()
										.setParameters(params)
										.setOnlyActiveRecords(true)
										.firstOnly();

		//	Existing
		if (existingAccount != null)
			return existingAccount;

		//	New
		MAccount newAccount = new MAccount (ctx, 0, trxName);
		newAccount.setClientOrg(clientId, orgId);
		newAccount.setC_AcctSchema_ID(acctSchemaId);
		newAccount.setAccount_ID(accountId);
		//	--  Optional Accounting fields
		newAccount.setC_SubAcct_ID(subAcctId);
		newAccount.setM_Product_ID(productId);
		newAccount.setC_BPartner_ID(partnerId);
		newAccount.setAD_OrgTrx_ID(orgTrxId);
		newAccount.setC_LocFrom_ID(locFromId);
		newAccount.setC_LocTo_ID(locToId);
		newAccount.setC_SalesRegion_ID(salesRegionId);
		newAccount.setC_Project_ID(projectId);
		newAccount.setC_Campaign_ID(campaignId);
		newAccount.setC_Activity_ID(activityId);
		newAccount.setUser1_ID(user1Id);
		newAccount.setUser2_ID(User2Id);
		newAccount.setUser3_ID(user3Id);
		newAccount.setUser4_ID(user4Id);
		newAccount.setUserElement1_ID(userElement1Id);
		newAccount.setUserElement2_ID(userElement2Id);
		//
		if (!newAccount.save())
		{
			s_log.log(Level.SEVERE, "Could not create new account - " + info);
			return null;
		}
		s_log.fine("New: " + newAccount);
		return newAccount;
	}	//	get
	
	/**
	 * 	Get first with Alias
	 *	@param ctx context
	 *	@param C_AcctSchema_ID as
 	 *	@param alias alias
	 *	@return account
	 */
	@Deprecated
	public static MAccount get (Properties ctx, int C_AcctSchema_ID, String alias)
	{
		final String whereClause = "C_AcctSchema_ID=? AND Alias=?";
		MAccount retValue =  new Query(ctx,I_C_ValidCombination.Table_Name,whereClause,null)
		.setParameters(C_AcctSchema_ID,alias)
		.firstOnly();
		return retValue;
	}	//	get

	/**
	 * 	Get from existing Accounting fact
	 *	@param factAcct accounting fact
	 *	@return account
	 */
	public static MAccount get (X_Fact_Acct factAcct)
	{
		MAccount account = get (factAcct.getCtx(),
			factAcct.getAD_Client_ID(), factAcct.getAD_Org_ID(), factAcct.getC_AcctSchema_ID(),
			factAcct.getAccount_ID(), factAcct.getC_SubAcct_ID(),
			factAcct.getM_Product_ID(), factAcct.getC_BPartner_ID(), factAcct.getAD_OrgTrx_ID(),
			factAcct.getC_LocFrom_ID(), factAcct.getC_LocTo_ID(), factAcct.getC_SalesRegion_ID(),
			factAcct.getC_Project_ID(), factAcct.getC_Campaign_ID(), factAcct.getC_Activity_ID(),
			factAcct.getUser1_ID(), factAcct.getUser2_ID(), factAcct.getUser3_ID() , factAcct.getUser4_ID(),
			factAcct.getUserElement1_ID(), factAcct.getUserElement2_ID(), factAcct.get_TrxName());
		return account;
	}	//	get
	
	/**************************************************************************
	 *  Factory: default combination
	 *  @param ctx context
	 *  @param acctSchemaId accounting schema
	 * 	@param optionalNull if true the optional values are null
	 * 	@param trxName transaction
	 *  @return Account
	 */
	public static MAccount getDefault (Properties ctx, int acctSchemaId,
		boolean optionalNull, String trxName)
	{
		MAcctSchema acctSchema = new MAcctSchema (ctx, acctSchemaId, trxName);
		return getDefault (acctSchema, optionalNull);
	}   //  getDefault

	/**
	 *  Factory: default combination
	 *  @param acctSchema accounting schema
	 * 	@param optionalNull if true, the optional values are null
	 *  @return Account
	 */
	public static MAccount getDefault (MAcctSchema acctSchema, boolean optionalNull)
	{
		MAccount vc = new MAccount(acctSchema);
		//  Active Elements
		MAcctSchemaElement[] elements = acctSchema.getAcctSchemaElements();
		for (int i = 0; i < elements.length; i++)
		{
			MAcctSchemaElement ase = elements[i];
			String elementType = ase.getElementType();
			int defaultValue = ase.getDefaultValue();
			boolean setValue = ase.isMandatory() || (!ase.isMandatory() && !optionalNull);
			//
			if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Organization))
				vc.setAD_Org_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Account))
				vc.setAccount_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_SubAccount) && setValue)
				vc.setC_SubAcct_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_BPartner) && setValue)
				vc.setC_BPartner_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Product) && setValue)
				vc.setM_Product_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Activity) && setValue)
				vc.setC_Activity_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_LocationFrom) && setValue)
				vc.setC_LocFrom_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_LocationTo) && setValue)
				vc.setC_LocTo_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Campaign) && setValue)
				vc.setC_Campaign_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_OrgTrx) && setValue)
				vc.setAD_OrgTrx_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Project) && setValue)
				vc.setC_Project_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_SalesRegion) && setValue)
				vc.setC_SalesRegion_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserList1) && setValue)
				vc.setUser1_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserList2) && setValue)
				vc.setUser2_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserList3) && setValue)
				vc.setUser3_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserList4) && setValue)
				vc.setUser4_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserElement1) && setValue)
				vc.setUserElement1_ID(defaultValue);
			else if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_UserElement2) && setValue)
				vc.setUserElement2_ID(defaultValue);
		}
		s_log.fine("Client_ID="
			+ vc.getAD_Client_ID() + ", Org_ID=" + vc.getAD_Org_ID()
			+ " - AcctSchema_ID=" + vc.getC_AcctSchema_ID() + ", Account_ID=" + vc.getAccount_ID());
		return vc;
	}   //  getDefault

	
	/**
	 *  Get Account
	 *  @param ctx context
	 *  @param C_ValidCombination_ID combination
	 *  @return Account
	 */
	@Deprecated
	public static MAccount get (Properties ctx, int C_ValidCombination_ID)
	{
		//	Maybe later cache
		return new MAccount(ctx, C_ValidCombination_ID, null);
	}   //  getAccount


	/**
	 * Get Valid Combination
	 * @param ctx
	 * @param validCombinationId
	 * @param trxName
	 * @return
	 */
	public static MAccount getValidCombination(Properties ctx, int validCombinationId, String trxName)
	{
		return new MAccount(ctx, validCombinationId, trxName);
	}

	/**
	 * 	Update Value/Description after change of 
	 * 	account element value/description.
	 *	@param ctx context
	 *	@param where where clause
	 *	@param trxName transaction
	 */
	public static void updateValueDescription (Properties ctx, final String where, String trxName)
	{
		List<MAccount> accounts=  new Query(ctx,MAccount.Table_Name,where,trxName)
		.setOrderBy(MAccount.COLUMNNAME_C_ValidCombination_ID).list();
		
		for(MAccount account : accounts)
		{
			account.setValueDescription();
			account.saveEx();
		}
	}	//	updateValueDescription
	
	/**	Logger						*/
	private static CLogger		s_log = CLogger.getCLogger (MAccount.class);


	
	/**************************************************************************
	 *  Default constructor
	 * 	@param ctx context
	 *  @param validCombinationId combination
	 *	@param trxName transaction
	 */
	public MAccount (Properties ctx, int validCombinationId, String trxName)
	{
		super (ctx, validCombinationId, trxName);
		if (validCombinationId == 0)
		{
		//	setAccount_ID (0);
		//	setC_AcctSchema_ID (0);
			setIsFullyQualified (false);
		}
	}   //  MAccount

	/**
	 *  Load constructor
	 * 	@param ctx context
	 *  @param rs result set
	 *	@param trxName transaction
	 */
	public MAccount (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}   //  MAccount

	/**
	 * 	Parent Constructor
	 *	@param as account schema
	 */
	public MAccount (MAcctSchema as)
	{
		this (as.getCtx(), 0, as.get_TrxName());
		setClientOrg(as);
		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
	}	//	Account

	/**	Account Segment				*/
	private MElementValue	m_accountEV = null;

	
	/**************************************************************************
	 * Return String representation
	 * @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MAccount=[");
		sb.append(getC_ValidCombination_ID());
		if (getCombination() != null)
			sb.append(",")
				.append(getCombination());
		else
		{
			//	.append(",Client=").append(getAD_Client_ID())
			sb.append(",Schema=").append(getC_AcctSchema_ID())
				.append(",Org=").append(getAD_Org_ID())
				.append(",Acct=").append(getAccount_ID())
				.append(" ");
			if (getC_SubAcct_ID() != 0)
				sb.append(",C_SubAcct_ID=").append(getC_SubAcct_ID());
			if (getM_Product_ID() != 0)
				sb.append(",M_Product_ID=").append(getM_Product_ID());
			if (getC_BPartner_ID() != 0)
				sb.append(",C_BPartner_ID=").append(getC_BPartner_ID());
			if (getAD_OrgTrx_ID() != 0)
				sb.append(",AD_OrgTrx_ID=").append(getAD_OrgTrx_ID());
			if (getC_LocFrom_ID() != 0)
				sb.append(",C_LocFrom_ID=").append(getC_LocFrom_ID());
			if (getC_LocTo_ID() != 0)
				sb.append(",C_LocTo_ID=").append(getC_LocTo_ID());
			if (getC_SalesRegion_ID() != 0)
				sb.append(",C_SalesRegion_ID=").append(getC_SalesRegion_ID());
			if (getC_Project_ID() != 0)
				sb.append(",C_Project_ID=").append(getC_Project_ID());
			if (getC_Campaign_ID() != 0)
				sb.append(",C_Campaign_ID=").append(getC_Campaign_ID());
			if (getC_Activity_ID() != 0)
				sb.append(",C_Activity_ID=").append(getC_Activity_ID());
			if (getUser1_ID() != 0)
				sb.append(",User1_ID=").append(getUser1_ID());
			if (getUser2_ID() != 0)
				sb.append(",User2_ID=").append(getUser2_ID());
			if (getUser3_ID() != 0)
				sb.append(",User3_ID=").append(getUser3_ID());
			if (getUser4_ID() != 0)
				sb.append(",User4_ID=").append(getUser4_ID());
			if (getUserElement1_ID() != 0)
				sb.append(",UserElement1_ID=").append(getUserElement1_ID());
			if (getUserElement2_ID() != 0)
				sb.append(",UserElement2_ID=").append(getUserElement2_ID());
		}
		sb.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Set Account_ID
	 * 	@param accountId id
	 */
	public void setAccount_ID (int accountId)
	{
		m_accountEV = null;	//	reset
		super.setAccount_ID(accountId);
	}	//	setAccount
	
	/**
	 * 	Set Account_ID
	 * 	@return element value
	 */
	public MElementValue getAccount ()
	{
		if (m_accountEV == null)
		{
			if (getAccount_ID() != 0)
				m_accountEV = new MElementValue(getCtx(), getAccount_ID(), get_TrxName());
		}
		return m_accountEV;
	}	//	setAccount


	/**
	 * 	Get Account Type
	 *	@return Account Type of Account Element
	 */
	public String getAccountType()
	{
		if (m_accountEV == null)
			getAccount();
		if (m_accountEV == null)
		{
			log.log(Level.SEVERE, "No ElementValue for Account_ID=" + getAccount_ID());
			return "";
		}
		return m_accountEV.getAccountType();
	}	//	getAccountType

	/**
	 * Is this a Balance Sheet Account
	 * @return boolean
	 */
	public boolean isBalanceSheet()
	{
		String accountType = getAccountType();
		return (MElementValue.ACCOUNTTYPE_Asset.equals(accountType)
			|| MElementValue.ACCOUNTTYPE_Liability.equals(accountType)
			|| MElementValue.ACCOUNTTYPE_OwnerSEquity.equals(accountType));
	}	//	isBalanceSheet

	/**
	 * Is this an Activa Account
	 * @return boolean
	 */
	public boolean isActiva()
	{
		return MElementValue.ACCOUNTTYPE_Asset.equals(getAccountType());
	}	//	isActive

	/**
	 * Is this a Passiva Account
	 * @return boolean
	 */
	public boolean isPassiva()
	{
		String accountType = getAccountType();
		return (MElementValue.ACCOUNTTYPE_Liability.equals(accountType)
			|| MElementValue.ACCOUNTTYPE_OwnerSEquity.equals(accountType));
	}	//	isPassiva

	/**
	 * 	Set Value and Description and Fully Qualified Flag for Combination
	 */
	public void setValueDescription()
	{
		StringBuffer combi = new StringBuffer();
		StringBuffer descr = new StringBuffer();
		boolean fullyQualified = true;
		//
		MAcctSchema acctSchema = new MAcctSchema(getCtx(), getC_AcctSchema_ID(), get_TrxName());	//	In Trx!
		MAcctSchemaElement[] elements = MAcctSchemaElement.getAcctSchemaElements(acctSchema);
		for (int i = 0; i < elements.length; i++)
		{
			if (i > 0)
			{
				combi.append(acctSchema.getSeparator());
				descr.append(acctSchema.getSeparator());
			}
			MAcctSchemaElement element = elements[i];
			String combiStr = "_";		//	not defined
			String descrStr = "_";

			if (MAcctSchemaElement.ELEMENTTYPE_Organization.equals(element.getElementType()))
			{
				if (getAD_Org_ID() != 0)
				{
					MOrg org = new MOrg(getCtx(), getAD_Org_ID(), get_TrxName());	//	in Trx!
					combiStr = org.getValue();
					descrStr = org.getName();
				}
				else
				{
					combiStr = "*";
					descrStr = "*";
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Account.equals(element.getElementType()))
			{
				if (getAccount_ID() != 0)
				{
					if (m_accountEV == null)
						m_accountEV = new MElementValue(getCtx(), getAccount_ID(), get_TrxName());
					combiStr = m_accountEV.getValue();
					descrStr = m_accountEV.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Account");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_SubAccount.equals(element.getElementType()))
			{
				if (getC_SubAcct_ID() != 0)
				{
					X_C_SubAcct sa = new X_C_SubAcct(getCtx(), getC_SubAcct_ID(), get_TrxName());
					combiStr = sa.getValue();
					descrStr = sa.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Product.equals(element.getElementType()))
			{
				if (getM_Product_ID() != 0)
				{
					X_M_Product product = new X_M_Product (getCtx(), getM_Product_ID(), get_TrxName());
					combiStr = product.getValue();
					descrStr = product.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Product");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_BPartner.equals(element.getElementType()))
			{
				if (getC_BPartner_ID() != 0)
				{
					X_C_BPartner partner = new X_C_BPartner (getCtx(), getC_BPartner_ID(),get_TrxName());
					combiStr = partner.getValue();
					descrStr = partner.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Business Partner");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_OrgTrx.equals(element.getElementType()))
			{
				if (getAD_OrgTrx_ID() != 0)
				{
					MOrg org = new MOrg(getCtx(), getAD_OrgTrx_ID(), get_TrxName());	// in Trx!
					combiStr = org.getValue();
					descrStr = org.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Trx Org");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_LocationFrom.equals(element.getElementType()))
			{
				if (getC_LocFrom_ID() != 0)
				{
					MLocation loc = new MLocation(getCtx(), getC_LocFrom_ID(), get_TrxName());	//	in Trx!
					combiStr = loc.getPostal();
					descrStr = loc.getCity();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Location From");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_LocationTo.equals(element.getElementType()))
			{
				if (getC_LocTo_ID() != 0)
				{
					MLocation loc = new MLocation(getCtx(), getC_LocFrom_ID(), get_TrxName());	//	in Trx!
					combiStr = loc.getPostal();
					descrStr = loc.getCity();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Location To");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_SalesRegion.equals(element.getElementType()))
			{
				if (getC_SalesRegion_ID() != 0)
				{
					MSalesRegion loc = new MSalesRegion(getCtx(), getC_SalesRegion_ID(), get_TrxName());
					combiStr = loc.getValue();
					descrStr = loc.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: SalesRegion");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Project.equals(element.getElementType()))
			{
				if (getC_Project_ID() != 0)
				{
					X_C_Project project = new X_C_Project (getCtx(), getC_Project_ID(), get_TrxName());
					combiStr = project.getValue();
					descrStr = project.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Project");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Campaign.equals(element.getElementType()))
			{
				if (getC_Campaign_ID() != 0)
				{
					X_C_Campaign campaign = new X_C_Campaign (getCtx(), getC_Campaign_ID(), get_TrxName());
					combiStr = campaign.getValue();
					descrStr = campaign.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Campaign");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Activity.equals(element.getElementType()))
			{
				if (getC_Activity_ID() != 0)
				{
					X_C_Activity act = new X_C_Activity (getCtx(), getC_Activity_ID(), get_TrxName());
					combiStr = act.getValue();
					descrStr = act.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Campaign");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList1.equals(element.getElementType()))
			{
				if (getUser1_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser1_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList2.equals(element.getElementType()))
			{
				if (getUser2_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser2_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList3.equals(element.getElementType()))
			{
				if (getUser3_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser3_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList4.equals(element.getElementType()))
			{
				if (getUser4_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser4_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserElement1.equals(element.getElementType()))
			{
				if (getUserElement1_ID() != 0)
				{
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserElement2.equals(element.getElementType()))
			{
				if (getUserElement2_ID() != 0)
				{
				}
			}
			combi.append(combiStr);
			descr.append(descrStr);
		}
		//	Set Values
		super.setCombination(combi.toString());
		super.setDescription(descr.toString());
		if (fullyQualified != isFullyQualified())
			setIsFullyQualified(fullyQualified);
		log.fine("Combination=" + getCombination() 
			+ " - " + getDescription()
			+ " - FullyQualified=" + fullyQualified);
	}	//	setValueDescription
	
	/**
	 * 	Validate combination
	 *	@return true if valid
	 */
	public boolean validate()
	{
		boolean ok = true;
		//	Validate Sub-Account
		if (getC_SubAcct_ID() != 0)
		{
			X_C_SubAcct sa = new X_C_SubAcct(getCtx(), getC_SubAcct_ID(), get_TrxName());
			if (sa.getC_ElementValue_ID() != getAccount_ID())
			{
				log.saveError("Error", "C_SubAcct.C_ElementValue_ID=" + sa.getC_ElementValue_ID()
					+ "<>Account_ID=" + getAccount_ID());
				ok = false;
			}
		}
		return ok;
	}	//	validate
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setValueDescription();
		return validate();
	}	//	beforeSave
	
	
	/**
	 * 	Test
	 *	@param args
	 */
	public static void main (String[] args)
	{
		org.compiere.Adempiere.startup(true);
		MAccount acct = get (Env.getCtx(), 11, 11, 101, 600, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, null);
		System.out.println(acct);
		System.out.println(acct.get_xmlString(new StringBuffer ("xxxx")));
		
		//
		MAccount acct2 = get (Env.getCtx(), 11, 12, 101, 600, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , null);
		System.out.println(acct2);
		
	}	//	main
	
}	//	Account


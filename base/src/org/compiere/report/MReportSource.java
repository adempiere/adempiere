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

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_PA_ReportSource;


/**
 *	Report Line Source Model
 *
 *  @author Jorg Janke
 *  @version $Id: MReportSource.java,v 1.3 2006/08/03 22:16:52 jjanke Exp $
 */
public class MReportSource extends X_PA_ReportSource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6085437491271873555L;


	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param reportSourceId id
	 * 	@param trxName transaction
	 */
	public MReportSource (Properties ctx, int reportSourceId, String trxName)
	{
		super (ctx, reportSourceId, trxName);
		if (reportSourceId == 0)
		{
		}
	}	//	MReportSource

	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param rs ResultSet to load from
	 * 	@param trxName transaction
	 */
	public MReportSource (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MReportSource


	/**
	 * 	Get SQL where clause
	 * 	@param hierarchyId hierarchy
	 * 	@return where clause
	 */
	public String getWhereClause(int hierarchyId)
	{
		String elementType = getElementType();
		//	ID for Tree Leaf Value
		int dimensionId = 0;
		//
		if (MReportSource.ELEMENTTYPE_Account.equals(elementType))
			dimensionId = getC_ElementValue_ID();
		else if (MReportSource.ELEMENTTYPE_Activity.equals(elementType))
			dimensionId = getC_Activity_ID();
		else if (MReportSource.ELEMENTTYPE_BPartner.equals(elementType))
			dimensionId = getC_BPartner_ID();
		else if (MReportSource.ELEMENTTYPE_Campaign.equals(elementType))
			dimensionId = getC_Campaign_ID();
		else if (MReportSource.ELEMENTTYPE_LocationFrom.equals(elementType))
			dimensionId = getC_Location_ID();
		else if (MReportSource.ELEMENTTYPE_LocationTo.equals(elementType))
			dimensionId = getC_Location_ID();
		else if (MReportSource.ELEMENTTYPE_Organization.equals(elementType))
			dimensionId = getOrg_ID();
		else if (MReportSource.ELEMENTTYPE_Product.equals(elementType))
			dimensionId = getM_Product_ID();
		else if (MReportSource.ELEMENTTYPE_Project.equals(elementType))
			dimensionId = getC_Project_ID();
		else if (MReportSource.ELEMENTTYPE_SalesRegion.equals(elementType))
			dimensionId = getC_SalesRegion_ID();
		else if (MReportSource.ELEMENTTYPE_OrgTrx.equals(elementType))
			dimensionId = getAD_OrgTrx_ID();	//	(re)uses Org_ID
		else if (MReportSource.ELEMENTTYPE_UserList1.equals(elementType))
			dimensionId = getUser1_ID();
		else if (MReportSource.ELEMENTTYPE_UserList2.equals(elementType))
			dimensionId = getUser2_ID();
		else if (MReportSource.ELEMENTTYPE_UserList3.equals(elementType))
			dimensionId = getUser3_ID();
		else if (MReportSource.ELEMENTTYPE_UserList4.equals(elementType))
			dimensionId = getUser4_ID();
		else if (MReportSource.ELEMENTTYPE_UserElement1.equals(elementType))
			return "UserElement1_ID="+getUserElement1_ID(); // Not Tree
		else if (MReportSource.ELEMENTTYPE_UserElement2.equals(elementType))
			return "UserElement2_ID="+getUserElement2_ID(); // Not Tree
		// Financial Report Source with Type Combination
		else if (MReportSource.ELEMENTTYPE_Combination.equals(elementType))
			return getWhereCombination(hierarchyId);

		//
		return MReportTree.getWhereClause (getCtx(), hierarchyId, elementType, dimensionId);
	}	//	getWhereClause

	/**
	 * Obtain where clause for the combination type
	 * @param hierarchyId
	 * @return
	 */
	private String getWhereCombination(int hierarchyId) {
		StringBuffer whcomb = new StringBuffer();
		if (getC_ElementValue_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Account, getC_ElementValue_ID());
			if (isIncludeNullsElementValue())
				whcomb.append(" AND (Account_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsElementValue())
				whcomb.append(" AND Account_ID IS NULL");

		if (getC_Activity_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Activity, getC_Activity_ID());
			if (isIncludeNullsActivity())
				whcomb.append(" AND (C_Activity_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsActivity())
				whcomb.append(" AND C_Activity_ID IS NULL");

		if (getC_BPartner_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_BPartner, getC_BPartner_ID());
			if (isIncludeNullsBPartner())
				whcomb.append(" AND (C_BPartner_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsBPartner())
				whcomb.append(" AND C_BPartner_ID IS NULL");

		if (getC_Campaign_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Campaign, getC_Campaign_ID());
			if (isIncludeNullsCampaign())
				whcomb.append(" AND (C_Campaign_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsCampaign())
				whcomb.append(" AND C_Campaign_ID IS NULL");

		if (getC_Location_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_LocationFrom, getC_Location_ID());
			if (isIncludeNullsLocation())
				whcomb.append(" AND (C_LocFrom_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsLocation())
				whcomb.append(" AND C_LocFrom_ID IS NULL");

		if (getOrg_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Organization, getOrg_ID());
			if (isIncludeNullsOrg())
				whcomb.append(" AND (AD_Org_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsOrg())
				whcomb.append(" AND AD_Org_ID IS NULL");
		
		if (getAD_OrgTrx_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_OrgTrx, getAD_OrgTrx_ID());
			if (isIncludeNullsOrgTrx())
				whcomb.append(" AND (AD_OrgTrx_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsOrgTrx())
				whcomb.append(" AND AD_OrgTrx_ID IS NULL");

		if (getM_Product_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Product, getM_Product_ID());
			if (isIncludeNullsProduct())
				whcomb.append(" AND (M_Product_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsProduct())
				whcomb.append(" AND M_Product_ID IS NULL");

		if (getC_Project_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_Project, getC_Project_ID());
			if (isIncludeNullsProject())
				whcomb.append(" AND (C_Project_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsProject())
				whcomb.append(" AND C_Project_ID IS NULL");

		if (getC_SalesRegion_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_SalesRegion, getC_SalesRegion_ID());
			if (isIncludeNullsSalesRegion())
				whcomb.append(" AND (C_SalesRegion_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsSalesRegion())
				whcomb.append(" AND C_SalesRegion_ID IS NULL");

		if (getUserElement1_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserElement1, getUserElement1_ID());
			//String whtree = "UserElement1_ID=" + getUserElement1_ID(); // No Tree
			if (isIncludeNullsUserElement1())
				whcomb.append(" AND (UserElement1_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsUserElement1())
				whcomb.append(" AND UserElement1_ID IS NULL");

		if (getUserElement2_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserElement2,  getUserElement2_ID());
			//String whtree = "UserElement2_ID=" + getUserElement2_ID(); // No Tree
			if (isIncludeNullsUserElement2())
				whcomb.append(" AND (UserElement2_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsUserElement2())
				whcomb.append(" AND UserElement2_ID IS NULL");

		if (getUser1_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserList1, getUser1_ID());
			//String whtree = "User1_ID=" + getUser1_ID(); // No Tree
			if (isIncludeNullsUser1())
				whcomb.append(" AND (User1_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
		if (isIncludeNullsUser1())
			whcomb.append(" AND User1_ID IS NULL");

		if (getUser2_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserList2, getUser2_ID());
			//String whtree = "User2_ID=" + getUser2_ID(); // No Tree
			if (isIncludeNullsUserElement2())
				whcomb.append(" AND (User2_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
		if (isIncludeNullsUser2())
			whcomb.append(" AND User2_ID IS NULL");

		if (getUser3_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserList3, getUser3_ID());
			//String whtree = "User3_ID=" + getUser3_ID(); // No Tree
			if (isIncludeNullsUser3())
				whcomb.append(" AND (User3_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
		if (isIncludeNullsUser3())
			whcomb.append(" AND User3_ID IS NULL");

		if (getUser4_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), hierarchyId, MReportSource.ELEMENTTYPE_UserList4, getUser4_ID());
			//String whtree = "User4_ID=" + getUser4_ID(); // No Tree
			if (isIncludeNullsUser4())
				whcomb.append(" AND (User4_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
		if (isIncludeNullsUser4())
			whcomb.append(" AND User4_ID IS NULL");
		
		// drop the first " AND "
		if (whcomb.length() > 5 && whcomb.toString().startsWith(" AND "))
			whcomb.delete(0, 5);

		return whcomb.toString();
	}
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer stringBuffer = new StringBuffer ("MReportSource[")
			.append(get_ID()).append(" - ").append(getDescription())
			.append(" - ").append(getElementType());
		stringBuffer.append ("]");
		return stringBuffer.toString ();
	}	//	toString


	/**************************************************************************
	 * 	Copy Constructor
	 * 	@param ctx context
	 * 	@param clientId parent
	 * 	@param orgId parent
	 * 	@param reportLineId parent
	 * 	@param source copy source
	 * 	@param trxName transaction
	 * 	@return Report Source
	 */
	public static MReportSource copy (Properties ctx, int clientId, int orgId, int reportLineId, MReportSource source, String trxName)
	{
		MReportSource retValue = new MReportSource (ctx, 0, trxName);
		MReportSource.copyValues(source, retValue, clientId, orgId);
		retValue.setPA_ReportLine_ID(reportLineId);
		return retValue;
	}	//	copy
	
	/**
	 * @return
	 */
	public String getCombinationKey() {
		
		StringBuffer stringBuffer = new StringBuffer("");
		if(isIncludeNullsElementValue())
			stringBuffer.append("A");
		if(getOrg_ID() != 0)
			stringBuffer.append("B");
		if(isIncludeNullsOrg())
			stringBuffer.append("C");
		if(getAD_OrgTrx_ID() != 0)
			stringBuffer.append("D");
		if(isIncludeNullsOrgTrx())
			stringBuffer.append("E");
		if(getC_BPartner_ID() != 0)
			stringBuffer.append("F");
		if(isIncludeNullsBPartner())
			stringBuffer.append("G");
		if(getM_Product_ID() != 0)
			stringBuffer.append("H");
		if(isIncludeNullsProduct())
			stringBuffer.append("I");
		if(getC_Location_ID() != 0)
			stringBuffer.append("J");
		if(isIncludeNullsLocation())
			stringBuffer.append("K");
		if(getC_Project_ID() != 0)
			stringBuffer.append("L");
		if(isIncludeNullsProject())
			stringBuffer.append("M");
		if(getC_SalesRegion_ID() != 0)
			stringBuffer.append("N");
		if(isIncludeNullsSalesRegion())
			stringBuffer.append("O");
		if(getC_Activity_ID() != 0)
			stringBuffer.append("P");
		if(isIncludeNullsActivity())
			stringBuffer.append("Q");
		if(getC_Campaign_ID() != 0)
			stringBuffer.append("R");
		if(isIncludeNullsCampaign())
			stringBuffer.append("S");
		if(getUserElement1_ID() != 0)
			stringBuffer.append("T");
		if(isIncludeNullsUserElement1())
			stringBuffer.append("U");
		if(get_ValueAsInt("User1_ID") != 0)
			stringBuffer.append("V");
		if(get_ValueAsBoolean("IsIncludeNullsUserList1"))
			stringBuffer.append("W");
		if(get_ValueAsInt("User2_ID") != 0)
			stringBuffer.append("X");
		if(get_ValueAsBoolean("IsIncludeNullsUserList2"))
			stringBuffer.append("Y");
		if(getC_ElementValue_ID() != 0)
			stringBuffer.append("Z");
		
		return stringBuffer.toString();
	} // getCombinationKey

}	//	MReportSource

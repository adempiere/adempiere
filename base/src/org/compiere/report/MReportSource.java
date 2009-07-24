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

import org.compiere.model.X_PA_ReportSource;


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
	 * 	@param PA_ReportSource_ID id
	 * 	@param trxName transaction
	 */
	public MReportSource (Properties ctx, int PA_ReportSource_ID, String trxName)
	{
		super (ctx, PA_ReportSource_ID, trxName);
		if (PA_ReportSource_ID == 0)
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
	 * 	@param PA_Hierarchy_ID hierarchy 
	 * 	@return where clause
	 */
	public String getWhereClause(int PA_Hierarchy_ID)
	{
		String et = getElementType();
		//	ID for Tree Leaf Value
		int ID = 0;
		//
		if (MReportSource.ELEMENTTYPE_Account.equals(et))
			ID = getC_ElementValue_ID();
		else if (MReportSource.ELEMENTTYPE_Activity.equals(et))
			ID = getC_Activity_ID();
		else if (MReportSource.ELEMENTTYPE_BPartner.equals(et))
			ID = getC_BPartner_ID();
		else if (MReportSource.ELEMENTTYPE_Campaign.equals(et))
			ID = getC_Campaign_ID();
		else if (MReportSource.ELEMENTTYPE_LocationFrom.equals(et))
			ID = getC_Location_ID();
		else if (MReportSource.ELEMENTTYPE_LocationTo.equals(et))
			ID = getC_Location_ID();
		else if (MReportSource.ELEMENTTYPE_Organization.equals(et))
			ID = getOrg_ID();
		else if (MReportSource.ELEMENTTYPE_Product.equals(et))
			ID = getM_Product_ID();
		else if (MReportSource.ELEMENTTYPE_Project.equals(et))
			ID = getC_Project_ID();
		else if (MReportSource.ELEMENTTYPE_SalesRegion.equals(et))
			ID = getC_SalesRegion_ID();
		else if (MReportSource.ELEMENTTYPE_OrgTrx.equals(et))
			ID = getOrg_ID();	//	(re)uses Org_ID
		else if (MReportSource.ELEMENTTYPE_UserList1.equals(et))
			ID = getC_ElementValue_ID();
		else if (MReportSource.ELEMENTTYPE_UserList2.equals(et))
			ID = getC_ElementValue_ID();
		else if (MReportSource.ELEMENTTYPE_UserElement1.equals(et))
			return "UserElement1_ID="+getUserElement1_ID(); // Not Tree
		else if (MReportSource.ELEMENTTYPE_UserElement2.equals(et))
			return "UserElement2_ID="+getUserElement2_ID(); // Not Tree
		// Financial Report Source with Type Combination
		else if (MReportSource.ELEMENTTYPE_Combination.equals(et))
			return getWhereCombination(PA_Hierarchy_ID);

		//
		return MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, et, ID);
	}	//	getWhereClause

	/**
	 * Obtain where clause for the combination type
	 * @param PA_Hierarchy_ID
	 * @return
	 */
	private String getWhereCombination(int PA_Hierarchy_ID) {
		StringBuffer whcomb = new StringBuffer();
		if (getC_ElementValue_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Account, getC_ElementValue_ID());
			if (isIncludeNullsElementValue())
				whcomb.append(" AND (Account_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsElementValue())
				whcomb.append(" AND Account_ID IS NULL");

		if (getC_Activity_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Activity, getC_Activity_ID());
			if (isIncludeNullsActivity())
				whcomb.append(" AND (C_Activity_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsActivity())
				whcomb.append(" AND C_Activity_ID IS NULL");

		if (getC_BPartner_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_BPartner, getC_BPartner_ID());
			if (isIncludeNullsBPartner())
				whcomb.append(" AND (C_BPartner_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsBPartner())
				whcomb.append(" AND C_BPartner_ID IS NULL");

		if (getC_Campaign_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Campaign, getC_Campaign_ID());
			if (isIncludeNullsCampaign())
				whcomb.append(" AND (C_Campaign_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsCampaign())
				whcomb.append(" AND C_Campaign_ID IS NULL");

		if (getC_Location_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_LocationFrom, getC_Location_ID());
			if (isIncludeNullsLocation())
				whcomb.append(" AND (C_LocFrom_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsLocation())
				whcomb.append(" AND C_LocFrom_ID IS NULL");

		if (getOrg_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Organization, getOrg_ID());
			if (isIncludeNullsOrg())
				whcomb.append(" AND (AD_Org_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsOrg())
				whcomb.append(" AND AD_Org_ID IS NULL");
		
		if (getAD_OrgTrx_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_OrgTrx, getAD_OrgTrx_ID());
			if (isIncludeNullsOrgTrx())
				whcomb.append(" AND (AD_OrgTrx_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsOrgTrx())
				whcomb.append(" AND AD_OrgTrx_ID IS NULL");

		if (getM_Product_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Product, getM_Product_ID());
			if (isIncludeNullsProduct())
				whcomb.append(" AND (M_Product_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsProduct())
				whcomb.append(" AND M_Product_ID IS NULL");

		if (getC_Project_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_Project, getC_Project_ID());
			if (isIncludeNullsProject())
				whcomb.append(" AND (C_Project_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsProject())
				whcomb.append(" AND C_Project_ID IS NULL");

		if (getC_SalesRegion_ID() > 0) {
			String whtree = MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, MReportSource.ELEMENTTYPE_SalesRegion, getC_SalesRegion_ID());
			if (isIncludeNullsSalesRegion())
				whcomb.append(" AND (C_SalesRegion_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsSalesRegion())
				whcomb.append(" AND C_SalesRegion_ID IS NULL");

		if (getUserElement1_ID() > 0) {
			String whtree = "UserElement1_ID=" + getUserElement1_ID(); // No Tree
			if (isIncludeNullsUserElement1())
				whcomb.append(" AND (UserElement1_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsUserElement1())
				whcomb.append(" AND UserElement1_ID IS NULL");

		if (getUserElement2_ID() > 0) {
			String whtree = "UserElement2_ID=" + getUserElement2_ID(); // No Tree
			if (isIncludeNullsUserElement2())
				whcomb.append(" AND (UserElement2_ID IS NULL OR ").append(whtree).append(")");
			else
				whcomb.append(" AND ").append(whtree);
		} else
			if (isIncludeNullsUserElement2())
				whcomb.append(" AND UserElement2_ID IS NULL");
		
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
		StringBuffer sb = new StringBuffer ("MReportSource[")
			.append(get_ID()).append(" - ").append(getDescription())
			.append(" - ").append(getElementType());
		sb.append ("]");
		return sb.toString ();
	}	//	toString


	/**************************************************************************
	 * 	Copy Constructor
	 * 	@param ctx context
	 * 	@param AD_Client_ID parent
	 * 	@param AD_Org_ID parent
	 * 	@param PA_ReportLine_ID parent
	 * 	@param source copy source
	 * 	@param trxName transaction
	 * 	@return Report Source
	 */
	public static MReportSource copy (Properties ctx, int AD_Client_ID, int AD_Org_ID, 
		int PA_ReportLine_ID, MReportSource source, String trxName)
	{
		MReportSource retValue = new MReportSource (ctx, 0, trxName);
		MReportSource.copyValues(source, retValue, AD_Client_ID, AD_Org_ID);
		retValue.setPA_ReportLine_ID(PA_ReportLine_ID);
		return retValue;
	}	//	copy

}	//	MReportSource

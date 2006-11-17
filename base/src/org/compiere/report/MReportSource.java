/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.sql.*;
import java.util.*;
import org.compiere.model.*;


/**
 *	Report Line Source Model
 *
 *  @author Jorg Janke
 *  @version $Id: MReportSource.java,v 1.3 2006/08/03 22:16:52 jjanke Exp $
 */
public class MReportSource extends X_PA_ReportSource
{
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
		if (MAcctSchemaElement.ELEMENTTYPE_Account.equals(et))
			ID = getC_ElementValue_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_Activity.equals(et))
			ID = getC_Activity_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_BPartner.equals(et))
			ID = getC_BPartner_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_Campaign.equals(et))
			ID = getC_Campaign_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_LocationFrom.equals(et))
			ID = getC_Location_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_LocationTo.equals(et))
			ID = getC_Location_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_Organization.equals(et))
			ID = getOrg_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_Product.equals(et))
			ID = getM_Product_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_Project.equals(et))
			ID = getC_Project_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_SalesRegion.equals(et))
			ID = getC_SalesRegion_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_OrgTrx.equals(et))
			ID = getOrg_ID ();	//	(re)uses Org_ID
		else if (MAcctSchemaElement.ELEMENTTYPE_UserList1.equals(et))
			ID = getC_ElementValue_ID ();
		else if (MAcctSchemaElement.ELEMENTTYPE_UserList2.equals(et))
			ID = getC_ElementValue_ID ();
//		else if (MAcctSchemaElement.ELEMENTTYPE_UserElement1.equals(et))
//			ID = getUserElement1_ID ();
//		else if (MAcctSchemaElement.ELEMENTTYPE_UserElement2.equals(et))
//			ID = getUserElement2_ID ();
		//
		return MReportTree.getWhereClause (getCtx(), PA_Hierarchy_ID, et, ID);
	}	//	getWhereClause


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

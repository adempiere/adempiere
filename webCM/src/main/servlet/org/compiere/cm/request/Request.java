/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.cm.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.compiere.model.MColumn;
import org.compiere.model.MRequest;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.DB;

/**
 *	Request Class to create or update Requests.
 *	
 *  @author Kai Viiksaar
 *  @version $Id: Request.java,v 1.3 2006/10/16 11:34:47 comdivision Exp $
 */
public class Request {

	//Integer values	
	private final static int i_integer = 11;
	private final static int i_id = 13;
	private final static int i_table = 18;
	private final static int i_tableDirect = 19;
	private final static int i_rowID = 26;	
	private final static int i_searchField = 30;
	
	//BigDecimal values
	private final static int bd_amount = 12;
	private final static int bd_floatNumber = 22;
	private final static int bd_quantity = 29;
	private final static int bd_costsAndPrice = 37;

	//Date values
	private final static int d_date = 15;
	private final static int d_dateTime = 16;
	private final static int d_time = 24;
	
	//String values
	private final static int s_string = 10;	
	private final static int s_text = 14;
	private final static int s_textLong = 36;
	private final static int s_list = 17;
	private final static int s_location = 21;
	private final static int s_binary = 23;
	private final static int s_account = 25;
	private final static int s_color = 27;
	private final static int s_button = 28;
	private final static int s_warehousLocator = 31;
	private final static int simage = 32;
	private final static int s_assignment = 33;
	private final static int s_memo = 34;
	private final static int s_productAttribute = 35;

	//Boolean values
	private final static int b_bool = 20;
	
	
	/**
	 * Creates a new Request.
	 * 
	 * @param request
	 * @param ctx
	 * @return new Request ID
	 */
	public static String createRequest(HttpServletRequest request, Properties ctx) {
		String l_szTrxName 	= null;
		String l_szReturn	= null;
		
		boolean l_bSuccess 	= true;
		
		BigDecimal l_bdAmt 	= getParameterAsBD(request, "RequestAmt");
		
		int l_nOrgID 		= getParameterAsInt(request, "AD_Org_ID");
		//int l_nSalesRepID 	= getParameterAsInt(request, "SalesRep_ID");
		int l_nRoleID 		= getParameterAsInt(request, "AD_Role_ID");
		int l_nRReqTypeID 	= getParameterAsInt(request, "R_RequestType_ID");
		int l_nRGroupID 	= getParameterAsInt(request, "R_Group_ID");
		int l_nRCategoryID 	= getParameterAsInt(request, "R_Category_ID");
		int l_nRReqRelID 	= getParameterAsInt(request, "R_RequestRelated_ID");
		int l_nRStatusID 	= getParameterAsInt(request, "R_Status_ID");
		int l_nRResolID 	= getParameterAsInt(request, "R_Resolution_ID");
		
		int l_nBPartnerID 	= getParameterAsInt(request, "C_BPartner_ID");
		int l_nUserID 		= getParameterAsInt(request, "AD_User_ID");
		int l_nProjectID 	= getParameterAsInt(request, "C_Project_ID");
		int l_nAssetID 		= getParameterAsInt(request, "A_Asset_ID");
		int l_nOrderID 		= getParameterAsInt(request, "C_Order_ID");
		int l_nInvoiceID 	= getParameterAsInt(request, "C_Invoice_ID");
		int l_nProductID 	= getParameterAsInt(request, "M_Product_ID");
		int l_nPaymentID 	= getParameterAsInt(request, "C_Payment_ID");
		int l_nInOutID 		= getParameterAsInt(request, "M_InOut_ID");
		int l_nRMAID 		= getParameterAsInt(request, "M_RMA_ID");
		int l_nCampaignID 	= getParameterAsInt(request, "C_Campaign_ID");
	

 		/*
		 * Durchlauf der Parameter Werte. Dabei werden alle übergebenen Parameter
		 * als Columns in der MColumn gesucht und bei einem Treffer diese Werte
		 * dann über set_ValueOfColumn gesetzt
		 */  		
/*		MRequest l_newRequest = new MRequest(ctx, 0, l_szTrxName);
		Enumeration l_eParameterNames = request.getParameterNames();
		MColumn curColumn = null;
		
		int l_nColumnID = 0;
		int l_nRefID = 0;
		
		while (l_eParameterNames.hasMoreElements()) {
			String name = l_eParameterNames.nextElement().toString();
			
			l_nColumnID = l_newRequest.get_ColumnIndex(name);
			if (l_nColumnID > -1) {
				curColumn = new MColumn(ctx, l_nColumnID, l_szTrxName);
				l_nRefID = curColumn.getAD_Reference_ID();
				
				if (l_nRefID == b_bool) {
					l_newRequest.set_ValueOfColumn(l_nColumnID, getParameterAsBool(request, name));
				} else if (l_nRefID == bd_amount || l_nRefID == bd_costsAndPrice || l_nRefID == bd_floatNumber || l_nRefID == bd_quantity) {
					l_newRequest.set_ValueOfColumn(l_nColumnID, getParameterAsBD(request, name));
				} else if (l_nRefID == d_date || l_nRefID == d_dateTime || l_nRefID == d_time) {
					l_newRequest.set_ValueOfColumn(l_nColumnID, getParameterAsDate(request, name));
				} else if (l_nRefID == i_id || l_nRefID == i_integer || l_nRefID == i_rowID || l_nRefID == i_searchField || l_nRefID == i_table || l_nRefID == i_table) {
					l_newRequest.set_ValueOfColumn(l_nColumnID, getParameterAsInt(request, name));
				} else {
					l_newRequest.set_ValueOfColumn(l_nColumnID, getParameterAsString(request, name));
				}				
			}
		}		
		l_bSuccess &= l_newRequest.save();
*/		
		
		MRequest newRequest = new MRequest(ctx, 0, l_szTrxName);
		
		// values for values no fieldgroup
		newRequest.setAD_Org_ID(l_nOrgID);
		newRequest.setDueType(getParameterAsString(request, "DueType"));
		newRequest.setR_RequestType_ID(l_nRReqTypeID);
		newRequest.setR_Group_ID(l_nRGroupID);
		newRequest.setR_Category_ID(l_nRCategoryID);
		newRequest.setR_RequestRelated_ID(l_nRReqRelID);
		newRequest.setR_Status_ID(l_nRStatusID);
		newRequest.setR_Resolution_ID(l_nRResolID);
		newRequest.setPriority(getParameterAsString(request, "Priority"));
		newRequest.setPriorityUser(getParameterAsString(request, "PriorityUser"));
		newRequest.setSummary(getParameterAsString(request, "Summary"));
		newRequest.setConfidentialType(getParameterAsString(request, "ConfidentialType"));		
		newRequest.setIsInvoiced(getParameterAsBool(request, "IsInvoiced"));
		
		// Mandatory values for fieldgroup Action 
		newRequest.setConfidentialTypeEntry(getParameterAsString(request, "ConfidentialTypeEntry"));
		newRequest.setAD_Role_ID(l_nRoleID);
		//newRequest.setSalesRep_ID(l_nSalesRepID);
		
		// values for fieldgroup Reference
		newRequest.setC_BPartner_ID(l_nBPartnerID);
		newRequest.setAD_User_ID(l_nUserID);
		newRequest.setC_Project_ID(l_nProjectID);
		newRequest.setA_Asset_ID(l_nAssetID);
		newRequest.setC_Order_ID(l_nOrderID);
		newRequest.setC_Invoice_ID(l_nInvoiceID);
		newRequest.setM_Product_ID(l_nProductID);
		newRequest.setC_Payment_ID(l_nPaymentID);
		newRequest.setM_InOut_ID(l_nInOutID);
		newRequest.setM_RMA_ID(l_nRMAID);
		newRequest.setRequestAmt(l_bdAmt);
		newRequest.setC_Campaign_ID(l_nCampaignID);
		
		l_bSuccess &= newRequest.save();
		
		if (l_bSuccess) {
			try {
				DB.commit(true, l_szTrxName);
				l_szReturn = "" + newRequest.get_ID();
			} catch (Exception e) {
				l_szReturn = e.getMessage();
			}			
		}
		return l_szReturn;
	}
	
	/**
	 * Updates a Request.
	 * 
	 * @param request
	 * @param ctx
	 * @return
	 */
	public static String changeRequest(HttpServletRequest request, Properties ctx) {
		String l_szTrxName 	= null;
		String l_szReturn	= null;
				
		boolean l_bSuccess 	= true;
		
		BigDecimal l_bdAmt 	= getParameterAsBD(request, "RequestAmt");
		
		int l_nReqID		= getParameterAsInt(request, "R_Request_ID");
		int l_nOrgID 		= getParameterAsInt(request, "AD_Org_ID");
		//int l_nSalesRepID 	= getParameterAsInt(request, "SalesRep_ID");
		int l_nRReqTypeID 	= getParameterAsInt(request, "R_RequestType_ID");
		int l_nRGroupID 	= getParameterAsInt(request, "R_Group_ID");
		int l_nRCategoryID 	= getParameterAsInt(request, "R_Category_ID");
		int l_nRReqRelID 	= getParameterAsInt(request, "R_RequestRelated_ID");
		int l_nRStatusID 	= getParameterAsInt(request, "R_Status_ID");
		int l_nRResolID 	= getParameterAsInt(request, "R_Resolution_ID");
		
		int l_nBPartnerID 	= getParameterAsInt(request, "C_BPartner_ID");
		int l_nUserID 		= getParameterAsInt(request, "AD_User_ID");
		int l_nProjectID 	= getParameterAsInt(request, "C_Project_ID");
		int l_nAssetID 		= getParameterAsInt(request, "A_Asset_ID");
		int l_nOrderID 		= getParameterAsInt(request, "C_Order_ID");
		int l_nInvoiceID 	= getParameterAsInt(request, "C_Invoice_ID");
		int l_nProductID 	= getParameterAsInt(request, "M_Product_ID");
		int l_nPaymentID 	= getParameterAsInt(request, "C_Payment_ID");
		int l_nInOutID 		= getParameterAsInt(request, "M_InOut_ID");
		int l_nRMAID 		= getParameterAsInt(request, "M_RMA_ID");
		int l_nCampaignID 	= getParameterAsInt(request, "C_Campaign_ID");
		
		int l_nResponseID 	= getParameterAsInt(request, "R_StandardResponse_ID");
		int l_nMailTextID 	= getParameterAsInt(request, "R_MailText_ID");
		int l_nActivityID 	= getParameterAsInt(request, "C_Activity_ID");
		int l_nProdSpentID 	= getParameterAsInt(request, "M_ProductSpent_ID");
		
		BigDecimal l_QtySpent 		= getParameterAsBD(request, "QtySpent");
		BigDecimal l_QtyInvoiced 	= getParameterAsBD(request, "QtyInvoiced");
		BigDecimal l_QtyPlan 		= getParameterAsBD(request, "QtyPlan");
		
		Timestamp l_tsDateNextAction 	= getParameterAsDate(request, "DateNextAction");
		Timestamp l_tsDateStartPlan 	= getParameterAsDate(request, "DateStartPlan");
		Timestamp l_tsDateCompletePlan 	= getParameterAsDate(request, "DateCompletePlan");
		Timestamp l_tsStartDate 		= getParameterAsDate(request, "StartDate");
		Timestamp l_tsCloseDate 		= getParameterAsDate(request, "CloseDate");
		
		MRequest newRequest = new MRequest(ctx, l_nReqID, l_szTrxName);
		
		// values for values no fieldgroup
		newRequest.setAD_Org_ID(l_nOrgID);
		newRequest.setDueType(getParameterAsString(request, "DueType"));
		newRequest.setR_RequestType_ID(l_nRReqTypeID);
		newRequest.setR_Group_ID(l_nRGroupID);
		newRequest.setR_Category_ID(l_nRCategoryID);
		newRequest.setR_RequestRelated_ID(l_nRReqRelID);
		newRequest.setR_Status_ID(l_nRStatusID);
		newRequest.setR_Resolution_ID(l_nRResolID);
		newRequest.setPriority(getParameterAsString(request, "Priority"));
		newRequest.setPriorityUser(getParameterAsString(request, "PriorityUser"));
		newRequest.setSummary(getParameterAsString(request, "Summary"));
		newRequest.setConfidentialType(getParameterAsString(request, "ConfidentialType"));		
		newRequest.setIsInvoiced(getParameterAsBool(request, "IsInvoiced"));
		
		// values for fieldgroup Action
		newRequest.setDateNextAction(l_tsDateNextAction);
		newRequest.setConfidentialTypeEntry(getParameterAsString(request, "ConfidentialTypeEntry"));
		newRequest.setR_StandardResponse_ID(l_nResponseID);
		newRequest.setR_MailText_ID(l_nMailTextID);
		newRequest.setResult(getParameterAsString(request, "Result"));
		newRequest.setC_Activity_ID(l_nActivityID);		
		newRequest.setQtyPlan(l_QtyPlan);
		newRequest.setQtySpent(l_QtySpent);
		newRequest.setM_ProductSpent_ID(l_nProdSpentID);
		newRequest.setQtyInvoiced(l_QtyInvoiced);
		newRequest.setDateStartPlan(l_tsDateStartPlan);
		newRequest.setDateCompletePlan(l_tsDateCompletePlan);
		newRequest.setStartDate(l_tsStartDate);
		newRequest.setCloseDate(l_tsCloseDate);
		
		// values for fieldgroup Reference
		newRequest.setC_BPartner_ID(l_nBPartnerID);
		newRequest.setAD_User_ID(l_nUserID);
		newRequest.setC_Project_ID(l_nProjectID);
		newRequest.setA_Asset_ID(l_nAssetID);
		newRequest.setC_Order_ID(l_nOrderID);
		newRequest.setC_Invoice_ID(l_nInvoiceID);
		newRequest.setM_Product_ID(l_nProductID);
		newRequest.setC_Payment_ID(l_nPaymentID);
		newRequest.setM_InOut_ID(l_nInOutID);
		newRequest.setM_RMA_ID(l_nRMAID);
		newRequest.setRequestAmt(l_bdAmt);
		newRequest.setC_Campaign_ID(l_nCampaignID);
		
		l_bSuccess &= newRequest.save();
		
		if (l_bSuccess) {
			try {
				DB.commit(true, l_szTrxName);
				l_szReturn = "" + newRequest.get_ID();
			} catch (Exception e) {
				l_szReturn = e.getMessage();
			}			
		}
		return l_szReturn;
	}
	
	/**
	 * Returns a Request Parameter as String.
	 * Removes format characters.
	 * 
	 * @param request
	 * @param parameterName
	 * @return
	 */
	private static String getParameterAsString(HttpServletRequest request, String parameterName) {
		String l_szReturn = new String("");
		
		if (request.getParameter(parameterName) != null && request.getParameter(parameterName) != "") {
			l_szReturn = request.getParameter(parameterName).toString();
			l_szReturn = l_szReturn.replace("\r","");
			l_szReturn = l_szReturn.replace("\n","");
		}
		return l_szReturn;
	}
	
	/**
	 * Returns a Request Parameter as Integer.
	 *  
	 * @param request
	 * @param parameterName
	 * @return value, 0 if no parameter or parse error
	 */
	private static int getParameterAsInt(HttpServletRequest request, String parameterName) {
		int l_nID = 0;
		
		if (request.getParameter(parameterName) != null && request.getParameter(parameterName) != "") {
			try {
				l_nID = Integer.parseInt(request.getParameter(parameterName));
			} catch (Exception e) {
				l_nID = 0;
			}		
		}		
		return l_nID;
	}
	
	/**
	 * Returns a Request Parameter as BigDecimal.
	 * 
	 * @param request
	 * @param parameterName
	 * @return value, 0 if no parameter or parse error
	 */
	private static BigDecimal getParameterAsBD(HttpServletRequest request, String parameterName) {
		BigDecimal l_bdValue = new BigDecimal(0);
		
		if (request.getParameter(parameterName) != null && request.getParameter(parameterName) != "") {
			try {
				String l_szValue = request.getParameter(parameterName);
				l_szValue = l_szValue.replace(",", ".");
				l_bdValue = new BigDecimal(Float.parseFloat(l_szValue));
			} catch (Exception e) {
				l_bdValue = new BigDecimal(0);
			}
		}
		l_bdValue = l_bdValue.setScale(2, BigDecimal.ROUND_CEILING);
		return l_bdValue;
	}
	
	/**
	 * Returns a Parameter as boolean.
	 * 
	 * @param request
	 * @param parameterName
	 * @return true if parameter != null
	 */
	private static boolean getParameterAsBool(HttpServletRequest request, String parameterName) {
		boolean l_bIs = false;
		
		if (request.getParameter(parameterName) != null) {
			l_bIs = true;
		}
		return l_bIs;
	}
	
	/**
	 * Returns a Parameter as Timestamp.
	 * 
	 * @param request
	 * @param parameterName
	 * @return Timestamp or null
	 */
	private static Timestamp getParameterAsDate(HttpServletRequest request, String parameterName) {
		Date myDate = new Date();
		SimpleDateFormat ger = new SimpleDateFormat("dd.MM.yyyy");
		long time = 0;
		
		if (request.getParameter(parameterName) != null && request.getParameter(parameterName) != "") {
			try {
				myDate = ger.parse(request.getParameter(parameterName).toString());
				time = myDate.getTime();
			} catch (ParseException e) {
				return null;
			}
			return new Timestamp(time);
		}
		return null;
	}
}
 
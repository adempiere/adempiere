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
package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAdvertisement;
import org.compiere.model.X_A_Asset;
import org.compiere.model.MBPartner;
import org.compiere.model.MCommissionRun;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInterestArea;
import org.compiere.model.MInvoice;
import org.compiere.model.MNote;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MRegistration;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestType;
import org.compiere.model.MRfQ;
import org.compiere.model.MRfQResponse;
import org.compiere.model.MTimeExpense;
import org.compiere.model.X_AD_UserBPAccess;
import org.compiere.wf.MWFActivity;


/**
 * 	Information Storage.
 * 	Container for JSP Information, added in InfoLinkTag
 *
 *  @author Jorg Janke
 *  @version $Id$
 */
public class WebInfo
{
	/**
	 *	Get general Info
	 *	@return info
	 */
	public static WebInfo getGeneral()
	{
		if (m_general == null)
			m_general = new WebInfo (new Properties(), null);
		return m_general;
	}	//	getGeneral

	/**	General Info				*/
	private static WebInfo		m_general = null;

	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param wu BPartner
	 */
	public WebInfo (Properties	ctx, WebUser wu)
	{
		m_ctx = ctx;
		m_wu = wu;
	}	//	Info


	/**	JSP Name					*/
	static public final String		NAME = "info";

	/**	Logging						*/
	private static CLogger		log = CLogger.getCLogger(WebInfo.class); 
	/**	Context						*/
	private Properties	m_ctx = null;
	/** Business Partner			*/
	private WebUser		m_wu = null;
	/**	Info Message				*/
	private String		m_infoMessage = null;
	/**	Info ID						*/
	private int			m_id = 0;

	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("WebInfo[");
		sb.append(getC_BPartner_ID());
		sb.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Get Info Message & reset
	 * 	@return info message
	 */
	public String getMessage()
	{
		String retValue = m_infoMessage;
		m_infoMessage = null;
		return retValue;
	}	//	getMessage

	/**
	 * 	Get Info Message - do not reset
	 * 	@return info message
	 */
	public String getInfo()
	{
		return m_infoMessage;
	}	//	getInfo

	/**
	 * 	Set Info Message
	 * 	@param msg info message
	 */
	public void setMessage (String msg)
	{
		m_infoMessage = msg;
	}	//	setMessage

	/**
	 * 	Get Info Message
	 * 	@return info id
	 */
	public int getId()
	{
		return m_id;
	}	//	getId

	/**
	 * 	Set Info Message
	 * 	@param id info id
	 */
	public void setId (String id)
	{
		try
		{
			setId (Integer.parseInt (id));
		}
		catch (NumberFormatException ex)
		{
			log.log(Level.SEVERE, "ID=" + id + " - " + ex.toString());
			m_id = 0;
		}
	}	//	setId

	/**
	 * 	Set Info Message
	 * 	@param id info id
	 */
	public void setId (int id)
	{
		log.info("ID=" + id);
		m_id = id;
	}	//	setId

	/**
	 * 	Get Client
	 * 	@return AD_Client_ID
	 */
	public int getAD_Client_ID()
	{
		if (m_wu == null)
			return -1;
		return m_wu.getAD_Client_ID();
	}	//	getC_BPartner_ID

	/**
	 * 	Get BPartner
	 * 	@return C_BPartner_ID
	 */
	public int getC_BPartner_ID()
	{
		if (m_wu == null)
			return -1;
		return m_wu.getC_BPartner_ID();
	}	//	getC_BPartner_ID

	/**
	 * 	Get BPartner Contact/User
	 * 	@return AD_User_ID
	 */
	public int getAD_User_ID()
	{
		if (m_wu == null)
			return -1;
		return m_wu.getAD_User_ID();
	}	//	getAD_User_ID

	/**
	 * 	Get BPartner Contact/User
	 * 	@return AD_User_ID
	 */
	public int getUser_ID()
	{
		return getAD_User_ID();
	}	//	getAD_User_ID

	/**
	 *  Get WebUser
	 *  @return WebUser
	 */
	public WebUser getWebUser()
	{
		return m_wu;
	}
	
	/**************************************************************************
	 * 	Get Orders
	 *	@return invoices of BP
	 */
	public ArrayList<MOrder> getOrders()
	{
		m_infoMessage = null;
		ArrayList<MOrder> list = new ArrayList<MOrder>();
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_PurchaseOrder, MDocType.DOCBASETYPE_SalesOrder}))
		{
			log.info("No Access");
			return list;
		}
		
		String sql = "SELECT * FROM C_Order WHERE Bill_BPartner_ID=?"
			+ " AND DocStatus NOT IN ('DR','IN') "
			+ "ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MOrder (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getOrders

	/**
	 * 	Get Order.
	 * 	Needs to have ID set first
	 *	@return invoice of BP with ID
	 */
	public MOrder getOrder()
	{
		m_infoMessage = null;
		MOrder retValue = null;
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_PurchaseOrder, MDocType.DOCBASETYPE_SalesOrder}))
		{
			log.info("No Access");
			return null;
		}
		String sql = "SELECT * FROM C_Order WHERE Bill_BPartner_ID=? AND C_Order_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MOrder (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "C_Order_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("C_Order_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getOrder


	/**
	 * 	Get Shipments
	 *	@return shipments of BP
	 */
	public ArrayList<MInOut> getShipments()
	{
		m_infoMessage = null;
		ArrayList<MInOut> list = new ArrayList<MInOut>();
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_MaterialReceipt, MDocType.DOCBASETYPE_MaterialDelivery}))
		{
			log.info("No Access");
			return list;
		}

		String sql = "SELECT * FROM M_InOut WHERE C_BPartner_ID=?"
			+ " AND DocStatus NOT IN ('DR','IN') "
			+ " ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInOut (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getShipments

	/**
	 * 	Get Own Requests
	 *	@return Array of Own Requests
	 */
	public ArrayList<MRequest> getRequestsOwn ()
	{
		return getRequests(true);
	}	//	getRequestsOwn

	/**
	 * 	Get Own Requests
	 *	@return Array of Assigned Requests
	 */
	public ArrayList<MRequest> getRequestsAssigned ()
	{
		return getRequests(false);
	}	//	getRequestsAssigned

	/**
	 * 	Get Requests
	 * 	@param own if true its own requests otherwise or
	 *	@return Array of Requests
	 */
	public ArrayList<MRequest> getRequests (boolean own)
	{
		m_infoMessage = null;
		ArrayList<MRequest> list = new ArrayList<MRequest>();
		String sql = null;
		if (own)	//	All Requests
			sql = "SELECT * FROM R_Request r "
				+ "WHERE r.C_BPartner_ID=?"		//	#1
				+ " AND (r.AD_User_ID=?"		//	#2
				+ " OR EXISTS (SELECT * FROM AD_User u "
					+ "WHERE u.AD_User_ID=? AND r.C_BPartner_ID=u.C_BPartner_ID AND IsFullBPAccess='Y')" // #3
				+ " OR EXISTS (SELECT * FROM AD_User u INNER JOIN AD_UserBPAccess a ON (u.AD_User_ID=a.AD_User_ID) "
					+ "WHERE u.AD_User_ID=? AND r.C_BPartner_ID=u.C_BPartner_ID"	// #4
					+ " AND a.BPAccessType='R' AND (a.R_RequestType_ID IS NULL OR a.R_RequestType_ID=r.R_RequestType_ID)) ) "
				+ "ORDER BY r.DocumentNo DESC";
		else	//	Open Requests of Sales Rep
			sql = "SELECT * FROM R_Request "
				+ "WHERE SalesRep_ID IN (SELECT AD_User_ID FROM AD_User WHERE C_BPartner_ID=?)"
				+ " AND R_Status_ID IN (SELECT R_Status_ID FROM R_Status WHERE IsClosed='N')"
				+ "ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			if (own)
			{
				pstmt.setInt(2, getAD_User_ID());
				pstmt.setInt(3, getAD_User_ID());
				pstmt.setInt(4, getAD_User_ID());
			}
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MRequest (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("Own=" + own +" #" + list.size());
		return list;
	}	//	getRequests

	/**
	 * 	Get Request.
	 * 	Needs to have ID set first; Check that it is owned / created by requestor
	 *	@return invoice of BP with ID
	 */
	public MRequest getRequest()
	{
		m_infoMessage = null;
		MRequest retValue = null;
		String sql = "SELECT * FROM R_Request "
			+ "WHERE R_Request_ID=?"
			+ " AND (C_BPartner_ID=?"
			+ " OR SalesRep_ID IN (SELECT AD_User_ID FROM AD_User WHERE C_BPartner_ID=?))";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_id);
			pstmt.setInt(2, getC_BPartner_ID());
			pstmt.setInt(3, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MRequest (m_ctx, rs, null);
 		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "R_Request_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("R_Request_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getRequest


	/**
	 * 	Get Request Types
	 *	@return Array of Request Types
	 */
	public ArrayList<MRequestType> getRequestTypes ()
	{
		m_infoMessage = null;
		ArrayList<MRequestType> list = new ArrayList<MRequestType>();
		String sql = "SELECT * FROM R_RequestType "
			+ "WHERE IsSelfService='Y' AND AD_Client_ID=? ORDER BY Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MRequestType (m_ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getRequestTypes

	/**
	 * 	Get Request Type
	 *	@return Request Type
	 */
	public MRequestType getRequestType ()
	{
		m_infoMessage = null;
		MRequestType retValue = null;
		String sql = "SELECT * FROM R_RequestType WHERE IsSelfService='Y' AND R_RequestType_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MRequestType (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("R_RequestType_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getRequestType
	
	
	/**
	 * 	Get Invoices
	 *	@return invoices of BP
	 */
	public ArrayList<MInvoice> getInvoices()
	{
		m_infoMessage = null;
		ArrayList<MInvoice> list = new ArrayList<MInvoice>();
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_APInvoice, MDocType.DOCBASETYPE_APCreditMemo,
				MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCBASETYPE_ARCreditMemo}))
		{
			log.info("No Access");
			return list;
		}
		String sql = "SELECT * FROM C_Invoice WHERE C_BPartner_ID=?"
			+ " AND DocStatus NOT IN ('DR','IN') "
			+ "ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInvoice (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getInvoices

	/**
	 * 	Get Invoice.
	 * 	Needs to have ID set first
	 *	@return invoice with ID of BP
	 */
	public MInvoice getInvoice()
	{
		m_infoMessage = null;
		MInvoice retValue = null;
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_APInvoice, MDocType.DOCBASETYPE_APCreditMemo,
				MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCBASETYPE_ARCreditMemo}))
		{
			log.info("No Access");
			return null;
		}
		String sql = "SELECT * FROM C_Invoice WHERE C_BPartner_ID=? AND C_Invoice_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MInvoice (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "C_Invoice_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("C_Invoice_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getInvoice

	/**
	 * 	Get Payments
	 *	@return payments of BP
	 */
	public ArrayList<MPayment> getPayments()
	{
		m_infoMessage = null;
		ArrayList<MPayment> list = new ArrayList<MPayment>();
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_BusinessDocuments, 
				new Object[] {MDocType.DOCBASETYPE_APPayment, MDocType.DOCBASETYPE_ARReceipt}))
		{
			log.info("No Access");
			return list;
		}
		String sql = "SELECT * FROM C_Payment WHERE C_BPartner_ID=?"
			+ " AND DocStatus NOT IN ('DR','IN') "
			+ "ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MPayment (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getPayments

	/**
	 * 	Get Active Assets if not Credit Stop and EMail is verified
	 *	@return payments of BP
	 */
	public ArrayList<X_A_Asset> getAssets()
	{
		m_infoMessage = null;
		ArrayList<X_A_Asset> list = new ArrayList<X_A_Asset>();
		if (m_wu != null)
		{
			if (m_wu.isCreditStopHold())
				return list;
			if (!m_wu.isEMailVerified())
				return list;
		}
		if (m_wu != null && 
			!m_wu.hasBPAccess(X_AD_UserBPAccess.BPACCESSTYPE_AssetsDownload, null))
		{
			log.info("No Access");
			return list;
		}
		String sql = "SELECT * FROM A_Asset WHERE C_BPartner_ID=? AND IsActive='Y' ORDER BY Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new X_A_Asset(m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getAssets

	/**
	 * 	Get Interest Areas
	 *	@return interest areas of BPC
	 */
	public ArrayList<MInterestArea> getInterests()
	{
		m_infoMessage = null;
		int AD_Client_ID = Env.getAD_Client_ID(m_ctx);
		//
		ArrayList<MInterestArea> list = new ArrayList<MInterestArea>();
		String sql = "SELECT * FROM R_InterestArea "
			+ "WHERE IsActive='Y' AND IsSelfService='Y'"
			+ " AND AD_Client_ID=? "
			+ "ORDER BY Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MInterestArea ia = new MInterestArea (m_ctx, rs, null);
				ia.setSubscriptionInfo(getAD_User_ID());
				list.add (ia);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getInterests

	/**
	 * 	Get Advertisements
	 *	@return advertisements of BP
	 */
	public ArrayList<MAdvertisement> getAdvertisements()
	{
		m_infoMessage = null;
		ArrayList<MAdvertisement> list = new ArrayList<MAdvertisement>();
		String sql = "SELECT * FROM W_Advertisement WHERE C_BPartner_ID=? ORDER BY ValidFrom DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MAdvertisement (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getAdvertisement

	/**
	 * 	Get All Advertisements
	 *	@return all advertisements
	 */
	public ArrayList<MAdvertisement> getAllAds()
	{
		m_infoMessage = null;
		ArrayList<MAdvertisement> list = new ArrayList<MAdvertisement>();
		String sql = "SELECT * FROM W_Advertisement WHERE IsActive='Y' ORDER BY Description";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MAdvertisement (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getAllAds

	/**
	 * 	Get Commissioned Invoices
	 *	@return commissioned invoices 
	 */
	public ArrayList<MInvoice> getCommissionedInvoices()
	{
		m_infoMessage = null;
		ArrayList<MInvoice> list = new ArrayList<MInvoice>();
		String sql = "SELECT * FROM C_Invoice "
			+ "WHERE (C_Invoice.SalesRep_ID=?"	//	#1
			+ " OR EXISTS (SELECT * FROM C_BPartner bp WHERE C_Invoice.C_BPartner_ID=bp.C_BPartner_ID AND bp.SalesRep_ID=?)"
			+ " OR EXISTS (SELECT * FROM C_InvoiceLine il INNER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)  WHERE C_Invoice.C_Invoice_ID=il.C_Invoice_ID AND p.SalesRep_ID=?))" 
			+ " AND DocStatus NOT IN ('DR','IN') "
			+ "ORDER BY DocumentNo DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			pstmt.setInt(2, getAD_User_ID());
			pstmt.setInt(3, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInvoice (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getCommissionedInvoices

	/**
	 * 	Get Commission Runs
	 *	@return commissioned invoices 
	 */
	public ArrayList<MCommissionRun> getCommissionRuns()
	{
		m_infoMessage = null;
		ArrayList<MCommissionRun> list = new ArrayList<MCommissionRun>();
		String sql = "SELECT * FROM C_CommissionRun "
			+ "WHERE EXISTS (SELECT * FROM C_Commission c "
				+ "WHERE C_CommissionRun.C_Commission_ID=c.C_Commission_ID AND c.C_BPartner_ID=?) "
			+ "ORDER BY DocumentNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MCommissionRun (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getCommissionRuns

	
	/**
	 * 	Get Notices
	 *	@return notices
	 */
	public ArrayList<MNote> getNotes()
	{
		ArrayList<MNote> list = new ArrayList<MNote>();
		String sql = "SELECT * FROM AD_Note "
			+ "WHERE AD_User_ID=?"
			+ " AND (Processed='N' OR Processed IS NULL) "
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MNote (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getNotes


	/**
	 * 	Get Notification.
	 * 	Needs to have ID set first
	 *	@return notification of User with ID
	 */
	public MNote getNote()
	{
		m_infoMessage = null;
		MNote retValue = null;
		String sql = "SELECT * FROM AD_Note WHERE AD_User_ID=? AND AD_Note_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MNote (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "AD_Note_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("AD_Note_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getNote

	
	/**
	 * 	Get Workflow Activities
	 *	@return activities
	 */
	public ArrayList<MWFActivity> getActivities()
	{
		ArrayList<MWFActivity> list = new ArrayList<MWFActivity>();
		String sql = "SELECT * FROM AD_WF_Activity "
			+ "WHERE AD_User_ID=?"
			+ " AND Processed='N' "
			+ "ORDER BY Priority DESC, Created";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MWFActivity (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getActivities


	/**
	 * 	Get Acitivity.
	 * 	Needs to have ID set first
	 *	@return notification of User with ID
	 */
	public MWFActivity getActivity()
	{
		m_infoMessage = null;
		MWFActivity retValue = null;
		String sql = "SELECT * FROM AD_WF_Activity WHERE AD_User_ID=? AND AD_WF_Activity_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MWFActivity (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "AD_WF_Activity_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("AD_WF_Activity_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getActivity

	
	/**
	 * 	Get Expenses
	 *	@return expense reports
	 */
	public ArrayList<MTimeExpense> getExpenses()
	{
		m_infoMessage = null;
		ArrayList<MTimeExpense> list = new ArrayList<MTimeExpense>();
		String sql = "SELECT * FROM S_TimeExpense "
			+ "WHERE C_BPartner_ID=? "
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MTimeExpense (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getExpenses

	/**
	 * 	Get Expense Report.
	 * 	Needs to have ID set first
	 *	@return invoice of BP with ID
	 */
	public MTimeExpense getExpense()
	{
		m_infoMessage = null;
		MTimeExpense retValue = null;
		String sql = "SELECT * FROM S_TimeExpense WHERE C_BPartner_ID=? AND S_TimeExpense_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MTimeExpense (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "S_TimeExpense_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (retValue == null)
			retValue = new MTimeExpense (m_ctx, 0, null);
		log.fine("S_TimeExpense_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getExpense


	/**
	 * 	Get Registrations
	 *	@return registrations
	 */
	public ArrayList<MRegistration> getRegistrations()
	{
		m_infoMessage = null;
		ArrayList<MRegistration> list = new ArrayList<MRegistration>();
		String sql = "SELECT * FROM A_Registration "
			+ "WHERE C_BPartner_ID=? "
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MRegistration (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getRegistrations

	/**
	 * 	Get Registration.
	 * 	Needs to have ID set first
	 *	@return invoice of BP with ID
	 */
	public MRegistration getRegistration()
	{
		m_infoMessage = null;
		MRegistration retValue = null;
		String sql = "SELECT * FROM A_Registration WHERE C_BPartner_ID=? AND A_Registration_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			pstmt.setInt(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MRegistration (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "A_Registration_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	new registration
		if (retValue == null)
			retValue = new MRegistration (m_ctx, 0, null);
		log.fine("A_Registration_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getRegistration

	
	/**
	 * 	Get RfQs.
	 * 	Where Response is Accepted, Self Service and either
	 *  all vendors or this vendor is selected.
	 *	@return request for quotations
	 */
	public ArrayList<MRfQ> getRfQs()
	{
		m_infoMessage = null;
		ArrayList<MRfQ> list = new ArrayList<MRfQ>();
		String sql = "SELECT * "
			+ "FROM C_RfQ r "
			+ "WHERE r.IsRfQResponseAccepted='Y'"
			+ " AND r.IsSelfService='Y' AND r.IsActive='Y' AND r.Processed='N'"
			+ " AND (r.IsInvitedVendorsOnly='N'"
			+	" OR EXISTS (SELECT * FROM C_RfQResponse rr "
			+	" WHERE r.C_RfQ_ID=rr.C_RfQ_ID AND rr.C_BPartner_ID=?)) "
			+ "ORDER BY r.Name";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MRfQ (m_ctx, rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + list.size());
		return list;
	}	//	getRfQs

	/**
	 * 	Get RfQ Response.
	 * 	Needs to have ID set first
	 *	@return rfq of BP with ID
	 */
	public MRfQResponse getRfQResponse()
	{
		m_infoMessage = null;
		MRfQResponse retValue = null;
		String sql = "SELECT * FROM C_RfQResponse "
			+ "WHERE C_RfQ_ID=?"
			+ " AND C_BPartner_ID=? AND IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_id);
			pstmt.setInt(2, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MRfQResponse (m_ctx, rs, null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "C_RfQResponse_ID=" + m_id, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	No Response existing
		if (retValue == null)
		{
			MRfQ rfq = new MRfQ (m_ctx, m_id, null);
			//	We can create a Response ?
			if (rfq.get_ID() != 0 && rfq.isSelfService() 
				&& rfq.isRfQResponseAccepted() && !rfq.isInvitedVendorsOnly() 
				&& getC_BPartner_ID() > 0 && getAD_User_ID() > 0)
			{
				MBPartner bp = new MBPartner (m_ctx, getC_BPartner_ID(), null);
				bp.setPrimaryAD_User_ID(getAD_User_ID());				
				retValue = new MRfQResponse (rfq, bp);	//	may have no lines
				retValue.saveEx();
			}
		}
		//
		log.fine("C_RfQResponse_ID=" + m_id + " - " + retValue);
		return retValue;
	}	//	getRfQResponse
	
}	//	Info

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.*;
import java.util.*;
import java.sql.*;

import javax.swing.*;
import java.util.logging.*;
import org.compiere.util.*;
import org.compiere.model.*;


/**
 *	Order Callouts.
 *	
 *  @author Victor Perez 
 *  @version $Id: CalloutProfile.java,v 1.23 2004/08/27 21:24:12 vpj-cd Exp $
 */
public class CalloutProfile extends CalloutEngine
{
	/**	Debug Steps			*/
	private boolean steps = false;

	/**
	 *	Order Header Change - DocType.
	 *		- InvoiceRuld/DeliveryRule/PaymentRule
	 *		- temporary Document
	 *  Context:
	 *  	- DocSubTypeSO
	 *		- HasCharges
	 *	- (re-sets Business Partner info of required)
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String docType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer C_DocType_ID = (Integer)value;		//	Actually C_DocTypeTarget_ID
		if (C_DocType_ID == null || C_DocType_ID.intValue() == 0)
			return "";

		//	Re-Create new DocNo, if there is a doc number already
		//	and the existing source used a different Sequence number
		String oldDocNo = (String)mTab.getValue("DocumentNo");
		boolean newDocNo = (oldDocNo == null);
		if (!newDocNo && oldDocNo.startsWith("<") && oldDocNo.endsWith(">"))
			newDocNo = true;
		Integer oldC_DocType_ID = (Integer)mTab.getValue("C_DocType_ID");

		try
		{
			String SQL = "SELECT d.DocSubTypeSO,d.HasCharges,'N',"			//	1..3
				+ "d.IsDocNoControlled,s.CurrentNext,s.CurrentNextSys,"     //  4..6
				+ "s.AD_Sequence_ID,d.IsSOTrx "                             //	7..8
				+ "FROM C_DocType d, AD_Sequence s "
				+ "WHERE C_DocType_ID=?"	//	#1
				+ " AND d.DocNoSequence_ID=s.AD_Sequence_ID(+)";
			int AD_Sequence_ID = 0;

			//	Get old AD_SeqNo for comparison
			if (!newDocNo && oldC_DocType_ID.intValue() != 0)
			{
				PreparedStatement pstmt = DB.prepareStatement(SQL);
				pstmt.setInt(1, oldC_DocType_ID.intValue());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					AD_Sequence_ID = rs.getInt(6);
				rs.close();
				pstmt.close();
			}

			PreparedStatement pstmt = DB.prepareStatement(SQL);
			pstmt.setInt(1, C_DocType_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			String DocSubTypeSO = "";
			boolean IsSOTrx = true;
			if (rs.next())		//	we found document type
			{
				//	Set Context:	Document Sub Type for Sales Orders
				DocSubTypeSO = rs.getString(1);
				if (DocSubTypeSO == null)
					DocSubTypeSO = "--";
				Env.setContext(ctx, WindowNo, "OrderType", DocSubTypeSO);
				//	No Drop Ship other than Standard
				if (!DocSubTypeSO.equals(MOrder.DocSubTypeSO_Standard))
					mTab.setValue ("IsDropShip", "N");
				
				//	Delivery Rule
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS))
					mTab.setValue ("DeliveryRule", MOrder.DELIVERYRULE_Force);
				else if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_Prepay))
					mTab.setValue ("DeliveryRule", MOrder.DELIVERYRULE_AfterReceipt);
				else
					mTab.setValue ("DeliveryRule", MOrder.DELIVERYRULE_Availability);
				
				//	Invoice Rule
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS)
					|| DocSubTypeSO.equals(MOrder.DocSubTypeSO_Prepay)
					|| DocSubTypeSO.equals(MOrder.DocSubTypeSO_OnCredit) )
					mTab.setValue ("InvoiceRule", MOrder.INVOICERULE_Immediate);
				else
					mTab.setValue ("InvoiceRule", MOrder.INVOICERULE_AfterDelivery);
				
				//	Payment Rule - POS Order
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS))
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				else
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);

				//	IsSOTrx
				if ("N".equals(rs.getString(8)))
					IsSOTrx = false;

				//	Set Context:
				Env.setContext(ctx, WindowNo, "HasCharges", rs.getString(2));
				//	DocumentNo
				if (rs.getString(4).equals("Y"))			//	IsDocNoControlled
				{
					if (!newDocNo && AD_Sequence_ID != rs.getInt(7))
						newDocNo = true;
					//if (newDocNo)
					//	if (Ini.getPropertyBool(Ini.P_COMPIERESYS) && Env.getAD_Client_ID(Env.getCtx()) < 1000000)
					//		mTab.setValue("DocumentNo", "<" + rs.getString(6) + ">");
					//	else
					//		mTab.setValue("DocumentNo", "<" + rs.getString(5) + ">");
				}
			}
			rs.close();
			pstmt.close();
			//  When BPartner is changed, the Rules are not set if
			//  it is a POS or Credit Order (i.e. defaults from Standard BPartner)
			//  This re-reads the Rules and applies them.
			if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS) 
				|| DocSubTypeSO.equals(MOrder.DocSubTypeSO_Prepay))    //  not for POS/PrePay
				;
			else
			{
				SQL = "SELECT PaymentRule,C_PaymentTerm_ID,"            //  1..2
					+ "InvoiceRule,DeliveryRule,"                       //  3..4
					+ "FreightCostRule,DeliveryViaRule, "               //  5..6
					+ "PaymentRulePO,PO_PaymentTerm_ID "
					+ "FROM C_BPartner "
					+ "WHERE C_BPartner_ID=?";		//	#1
				pstmt = DB.prepareStatement(SQL);
				int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
				pstmt.setInt(1, C_BPartner_ID);
				//
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	PaymentRule
					String s = rs.getString(IsSOTrx ? "PaymentRule" : "PaymentRulePO");
					if (s != null && s.length() != 0)
					{
						if (IsSOTrx && (s.equals("B") || s.equals("S") || s.equals("U")))	//	No Cash/Check/Transfer for SO_Trx
							s = "P";										//  Payment Term
						if (!IsSOTrx && (s.equals("B")))					//	No Cash for PO_Trx
							s = "P";										//  Payment Term
						mTab.setValue("PaymentRule", s);
					}
					//	Payment Term
					Integer ii =new Integer(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID" : "PO_PaymentTerm_ID"));
					if (!rs.wasNull())
						mTab.setValue("C_PaymentTerm_ID", ii);
					//	InvoiceRule
					s = rs.getString(3);
					if (s != null && s.length() != 0)
						mTab.setValue("InvoiceRule", s);
					//	DeliveryRule
					s = rs.getString(4);
					if (s != null && s.length() != 0)
						mTab.setValue("DeliveryRule", s);
					//	FreightCostRule
					s = rs.getString(5);
					if (s != null && s.length() != 0)
						mTab.setValue("FreightCostRule", s);
					//	DeliveryViaRule
					s = rs.getString(6);
					if (s != null && s.length() != 0)
						mTab.setValue("DeliveryViaRule", s);
				}
				rs.close();
				pstmt.close();
			}   //  re-read customer rules
		}
		catch (SQLException e)
		{			
                        log.severe("docType" + e);
			return e.getLocalizedMessage();
		}

		return "";
	}	//	docType


	/**
	 *	Order Header - BPartner.
	 *		- M_PriceList_ID (+ Context)
	 *		- C_BPartner_Location_ID
	 *		- Bill_BPartner_ID/Bill_Location_ID
	 *		- AD_User_ID
	 *		- POReference
	 *		- SO_Description
	 *		- IsDiscountPrinted
	 *		- InvoiceRule/DeliveryRule/PaymentRule/FreightCost/DeliveryViaRule
	 *		- C_PaymentTerm_ID
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String bPartner (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer C_BPartner_ID = (Integer)value;
		if (C_BPartner_ID == null || C_BPartner_ID.intValue() == 0)
			return "";
		setCalloutActive(true);
		
		String sql = "SELECT p.AD_Language,p.C_PaymentTerm_ID,"
			+ "p.M_PriceList_ID,p.PaymentRule,p.POReference,"
			+ "p.SO_Description,p.IsDiscountPrinted,"
			+ "p.InvoiceRule,p.DeliveryRule,p.FreightCostRule,DeliveryViaRule,"
			+ "p.SO_CreditLimit, p.SO_CreditLimit-p.SO_CreditUsed AS CreditAvailable,"
			+ "lship.C_BPartner_Location_ID,c.AD_User_ID,"
			+ "p.PO_PriceList_ID, p.PaymentRulePO, p.PO_PaymentTerm_ID,"
			+ "lbill.C_BPartner_Location_ID AS Bill_Location_ID, p.SOCreditStatus "
			+ "FROM C_BPartner p"
			+ " LEFT OUTER JOIN C_BPartner_Location lbill ON (p.C_BPartner_ID=lbill.C_BPartner_ID AND lbill.IsBillTo='Y' AND lbill.IsActive='Y')"
			+ " LEFT OUTER JOIN C_BPartner_Location lship ON (p.C_BPartner_ID=lship.C_BPartner_ID AND lship.IsShipTo='Y' AND lship.IsActive='Y')"
			+ " LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
			+ "WHERE p.C_BPartner_ID=? AND p.IsActive='Y'";		//	#1

		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, C_BPartner_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	PriceList (indirect: IsTaxIncluded & Currency)
				Integer ii = new Integer(rs.getInt(IsSOTrx ? "M_PriceList_ID" : "PO_PriceList_ID"));
				if (!rs.wasNull())
					mTab.setValue("M_PriceList_ID", ii);
				else
				{	//	get default PriceList
					int i = Env.getContextAsInt(ctx, "#M_PriceList_ID");
					if (i != 0)
						mTab.setValue("M_PriceList_ID", new Integer(i));
				}

				//	Bill-To
				mTab.setValue("Bill_BPartner_ID", C_BPartner_ID);
				int bill_Location_ID = rs.getInt("Bill_Location_ID");
				if (bill_Location_ID == 0)
					mTab.setValue("Bill_Location_ID", null);
				else
					mTab.setValue("Bill_Location_ID", new Integer(bill_Location_ID));
				// Ship-To Location
				int shipTo_ID = rs.getInt("C_BPartner_Location_ID");
				//	overwritten by InfoBP selection - works only if InfoWindow
				//	was used otherwise creates error (uses last value, may belong to differnt BP)
				if (C_BPartner_ID.toString().equals(Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_ID")))
				{
					String loc = Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_Location_ID");
					if (loc.length() > 0)
						shipTo_ID = Integer.parseInt(loc);
				}
				if (shipTo_ID == 0)
					mTab.setValue("C_BPartner_Location_ID", null);
				else
					mTab.setValue("C_BPartner_Location_ID", new Integer(shipTo_ID));

				//	Contact - overwritten by InfoBP selection
				int contID = rs.getInt("AD_User_ID");
				if (C_BPartner_ID.toString().equals(Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_ID")))
				{
					String cont = Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "AD_User_ID");
					if (cont.length() > 0)
						contID = Integer.parseInt(cont);
				}
				if (contID == 0)
					mTab.setValue("AD_User_ID", null);
				else
				{
					mTab.setValue("AD_User_ID", new Integer(contID));
					mTab.setValue("Bill_User_ID", new Integer(contID));
				}

				//	CreditAvailable 
				if (IsSOTrx)
				{
					double CreditLimit = rs.getDouble("SO_CreditLimit");
					String SOCreditStatus = rs.getString("SOCreditStatus");
					if (CreditLimit != 0)
					{
						double CreditAvailable = rs.getDouble("CreditAvailable");
						if (!rs.wasNull() && CreditAvailable < 0)
							//mTab.fireDataStatusEEvent("CreditLimitOver",DisplayType.getNumberFormat(DisplayType.Amount).format(CreditAvailable));                                                                                                          
                                                        JOptionPane.showMessageDialog(null,"CreditLimitOver" , DisplayType.getNumberFormat(DisplayType.Amount).format(CreditAvailable) , JOptionPane.ERROR_MESSAGE);
					}
				}

				//	PO Reference
				String s = rs.getString("POReference");
				if (s != null && s.length() != 0)
					mTab.setValue("POReference", s);
				else
					mTab.setValue("POReference", null);
				//	SO Description
				s = rs.getString("SO_Description");
				if (s != null && s.trim().length() != 0)
					mTab.setValue("Description", s);
				//	IsDiscountPrinted
				s = rs.getString("IsDiscountPrinted");
				if (s != null && s.length() != 0)
					mTab.setValue("IsDiscountPrinted", s);
				else
					mTab.setValue("IsDiscountPrinted", "N");

				//	Defaults, if not Walkin Receipt or Walkin Invoice
				String OrderType = Env.getContext(ctx, WindowNo, "OrderType");
				mTab.setValue("InvoiceRule", MOrder.INVOICERULE_AfterDelivery);
				mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_Availability);
				mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
				if (OrderType.equals(MOrder.DocSubTypeSO_Prepay))
				{
					mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
					mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_AfterReceipt);
				}
				else if (OrderType.equals(MOrder.DocSubTypeSO_POS))	//  for POS
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				else
				{
					//	PaymentRule
					s = rs.getString(IsSOTrx ? "PaymentRule" : "PaymentRulePO");
					if (s != null && s.length() != 0)
					{
						if (s.equals("B"))				//	No Cache in Non POS
							s = "P";
						if (IsSOTrx && (s.equals("S") || s.equals("U")))	//	No Check/Transfer for SO_Trx
							s = "P";										//  Payment Term
						mTab.setValue("PaymentRule", s);
					}
					//	Payment Term
					ii = new Integer(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID" : "PO_PaymentTerm_ID"));
					if (!rs.wasNull())
						mTab.setValue("C_PaymentTerm_ID", ii);
					//	InvoiceRule
					s = rs.getString("InvoiceRule");
					if (s != null && s.length() != 0)
						mTab.setValue("InvoiceRule", s);
					//	DeliveryRule
					s = rs.getString("DeliveryRule");
					if (s != null && s.length() != 0)
						mTab.setValue("DeliveryRule", s);
					//	FreightCostRule
					s = rs.getString("FreightCostRule");
					if (s != null && s.length() != 0)
						mTab.setValue("FreightCostRule", s);
					//	DeliveryViaRule
					s = rs.getString("DeliveryViaRule");
					if (s != null && s.length() != 0)
						mTab.setValue("DeliveryViaRule", s);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.severe("bPartner" + e);
			setCalloutActive(false);
			return e.getLocalizedMessage();
		}
		setCalloutActive(false);
		return "";
	}	//	bPartner

	/**
	 *	Order Header - Invoice BPartner.
	 *		- M_PriceList_ID (+ Context)
	 *		- Bill_Location_ID
	 *		- Bill_User_ID
	 *		- POReference
	 *		- SO_Description
	 *		- IsDiscountPrinted
	 *		- InvoiceRule/PaymentRule
	 *		- C_PaymentTerm_ID
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String bPartnerBill (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		Integer bill_BPartner_ID = (Integer)value;
		if (bill_BPartner_ID == null || bill_BPartner_ID.intValue() == 0)
			return "";

		String sql = "SELECT p.AD_Language,p.C_PaymentTerm_ID,"
			+ "p.M_PriceList_ID,p.PaymentRule,p.POReference,"
			+ "p.SO_Description,p.IsDiscountPrinted,"
			+ "p.InvoiceRule,p.DeliveryRule,p.FreightCostRule,DeliveryViaRule,"
			+ "p.SO_CreditLimit, p.SO_CreditLimit-p.SO_CreditUsed AS CreditAvailable,"
			+ "c.AD_User_ID,"
			+ "p.PO_PriceList_ID, p.PaymentRulePO, p.PO_PaymentTerm_ID,"
			+ "lbill.C_BPartner_Location_ID AS Bill_Location_ID "
			+ "FROM C_BPartner p"
			+ " LEFT OUTER JOIN C_BPartner_Location lbill ON (p.C_BPartner_ID=lbill.C_BPartner_ID AND lbill.IsBillTo='Y' AND lbill.IsActive='Y')"
			+ " LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
			+ "WHERE p.C_BPartner_ID=? AND p.IsActive='Y'";		//	#1

		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, bill_BPartner_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	PriceList (indirect: IsTaxIncluded & Currency)
				Integer ii = new Integer(rs.getInt(IsSOTrx ? "M_PriceList_ID" : "PO_PriceList_ID"));
				if (!rs.wasNull())
					mTab.setValue("M_PriceList_ID", ii);
				else
				{	//	get default PriceList
					int i = Env.getContextAsInt(ctx, "#M_PriceList_ID");
					if (i != 0)
						mTab.setValue("M_PriceList_ID", new Integer(i));
				}

				int bill_Location_ID = rs.getInt("Bill_Location_ID");
				//	overwritten by InfoBP selection - works only if InfoWindow
				//	was used otherwise creates error (uses last value, may belong to differnt BP)
				if (bill_BPartner_ID.toString().equals(Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_ID")))
				{
					String loc = Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_Location_ID");
					if (loc.length() > 0)
						bill_Location_ID = Integer.parseInt(loc);
				}
				if (bill_Location_ID == 0)
					mTab.setValue("Bill_Location_ID", null);
				else
					mTab.setValue("Bill_Location_ID", new Integer(bill_Location_ID));

				//	Contact - overwritten by InfoBP selection
				int contID = rs.getInt("AD_User_ID");
				if (bill_BPartner_ID.toString().equals(Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "C_BPartner_ID")))
				{
					String cont = Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "AD_User_ID");
					if (cont.length() > 0)
						contID = Integer.parseInt(cont);
				}
				if (contID == 0)
					mTab.setValue("Bill_User_ID", null);
				else
					mTab.setValue("Bill_User_ID", new Integer(contID));

				//	CreditAvailable 
				if (IsSOTrx)
				{
					double CreditLimit = rs.getDouble("SO_CreditLimit");
					if (CreditLimit != 0)
					{
						double CreditAvailable = rs.getDouble("CreditAvailable");
						if (!rs.wasNull() && CreditAvailable < 0)                                                    
                                                //mTab.fireDataStatusEEvent("CreditLimitOver",isplayType.getNumberFormat(DisplayType.Amount).format(CreditAvailable));
                                                JOptionPane.showMessageDialog(null,"CreditLimitOver" , DisplayType.getNumberFormat(DisplayType.Amount).format(CreditAvailable) , JOptionPane.ERROR_MESSAGE);
					}
				}

				//	PO Reference
				String s = rs.getString("POReference");
				if (s != null && s.length() != 0)
					mTab.setValue("POReference", s);
				else
					mTab.setValue("POReference", null);
				//	SO Description
				s = rs.getString("SO_Description");
				if (s != null && s.trim().length() != 0)
					mTab.setValue("Description", s);
				//	IsDiscountPrinted
				s = rs.getString("IsDiscountPrinted");
				if (s != null && s.length() != 0)
					mTab.setValue("IsDiscountPrinted", s);
				else
					mTab.setValue("IsDiscountPrinted", "N");

				//	Defaults, if not Walkin Receipt or Walkin Invoice
				String OrderType = Env.getContext(ctx, WindowNo, "OrderType");
				mTab.setValue("InvoiceRule", MOrder.INVOICERULE_AfterDelivery);
				mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
				if (OrderType.equals(MOrder.DocSubTypeSO_Prepay))
					mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
				else if (OrderType.equals(MOrder.DocSubTypeSO_POS))	//  for POS
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				else
				{
					//	PaymentRule
					s = rs.getString(IsSOTrx ? "PaymentRule" : "PaymentRulePO");
					if (s != null && s.length() != 0)
					{
						if (s.equals("B"))				//	No Cache in Non POS
							s = "P";
						if (IsSOTrx && (s.equals("S") || s.equals("U")))	//	No Check/Transfer for SO_Trx
							s = "P";										//  Payment Term
						mTab.setValue("PaymentRule", s);
					}
					//	Payment Term
					ii = new Integer(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID" : "PO_PaymentTerm_ID"));
					if (!rs.wasNull())
						mTab.setValue("C_PaymentTerm_ID", ii);
					//	InvoiceRule
					s = rs.getString("InvoiceRule");
					if (s != null && s.length() != 0)
						mTab.setValue("InvoiceRule", s);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.severe("bPartnerBill" + e);
			return e.getLocalizedMessage();
		}

		return "";
	}	//	bPartnerBill


	/**
	 *	Order Header - PriceList.
	 *	(used also in Invoice)
	 *		- C_Currency_ID
	 *		- IsTaxIncluded
	 *	Window Context:
	 *		- EnforcePriceLimit
	 *		- StdPrecision
	 *		- M_PriceList_Version_ID
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String priceList (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_PriceList_ID = (Integer)value;
		if (M_PriceList_ID == null || M_PriceList_ID.intValue()== 0)
			return "";
		if (steps) log.warning("priceList - init");

		String SQL = "SELECT pl.IsTaxIncluded,pl.EnforcePriceLimit,pl.C_Currency_ID,c.StdPrecision,"
			+ "plv.M_PriceList_Version_ID,plv.ValidFrom "
			+ "FROM M_PriceList pl,C_Currency c,M_PriceList_Version plv "
			+ "WHERE pl.C_Currency_ID=c.C_Currency_ID"
			+ " AND pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND pl.M_PriceList_ID=? "						//	1
			+ "ORDER BY plv.ValidFrom DESC";
		//	Use newest price list - may not be future
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL);
			pstmt.setInt(1, M_PriceList_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	Tax Included
				mTab.setValue("IsTaxIncluded", new Boolean("Y".equals(rs.getString(1))));
				//	Price Limit Enforce
				Env.setContext(ctx, WindowNo, "EnforcePriceLimit", rs.getString(2));
				//	Currency
				Integer ii = new Integer(rs.getInt(3));
				mTab.setValue("C_Currency_ID", ii);
				//	Currency Precision
				Env.setContext(ctx, WindowNo, "StdPrecision", rs.getInt(4));
				//	PriceList Version
				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", rs.getInt(5));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{	
                 log.log(Level.SEVERE, "priceList" + e);
		 return e.getLocalizedMessage();
		}
		if (steps) 
                 log.log(Level.WARNING, "priceList - fini");    

		return "";
	}	//	priceList

	
	/*************************************************************************
	 *	Order Line - Product.
	 *		- reset C_Charge_ID / M_AttributeSetInstance_ID
	 *		- PriceList, PriceStd, PriceLimit, C_Currency_ID, EnforcePriceLimit
	 *		- UOM
	 *	Calls Tax
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
//	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
//	{
//		Integer M_Product_ID = (Integer)value;
//		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
//			return "";
//		if (steps) log.warn("product - init");
//		setCalloutActive(true);
//		//
//		mTab.setValue("C_Charge_ID", null);
//		//	Set Attribute
//		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
//			&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
//			mTab.setValue("M_AttributeSetInstance_ID", new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID")));
//		else
//			mTab.setValue("M_AttributeSetInstance_ID", null);
//			
//		/*****	Price Calculation see also qty	****/
//		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
//		BigDecimal Qty = (BigDecimal)mTab.getValue("QtyOrdered");
//		MProductPricing pp = new MProductPricing (M_Product_ID.intValue(), C_BPartner_ID, Qty);
//		//
//		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
//		pp.setM_PriceList_ID(M_PriceList_ID);
//		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
//		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
//		Timestamp orderDate = (Timestamp)mTab.getValue("DateOrdered");
//		pp.setPriceDate(orderDate);
//		//		
//		mTab.setValue("PriceList", pp.getPriceList());
//		mTab.setValue("PriceLimit", pp.getPriceLimit());
//		mTab.setValue("PriceActual", pp.getPriceStd());
//		mTab.setValue("PriceEntered", pp.getPriceStd());
//		mTab.setValue("C_Currency_ID", new Integer(pp.getC_Currency_ID()));
//		mTab.setValue("Discount", pp.getDiscount());
//		mTab.setValue("C_UOM_ID", new Integer(pp.getC_UOM_ID()));
//		mTab.setValue("QtyOrdered", mTab.getValue("QtyEntered"));
//		Env.setContext(ctx, WindowNo, "EnforcePriceLimit", pp.isEnforcePriceLimit() ? "Y" : "N");
//		Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");
//		
//		//	Check/Update Warehouse Setting
//		//	int M_Warehouse_ID = Env.getContextAsInt(ctx, Env.WINDOW_INFO, "M_Warehouse_ID");
//		//	Integer wh = (Integer)mTab.getValue("M_Warehouse_ID");
//		//	if (wh.intValue() != M_Warehouse_ID)
//		//	{
//		//		mTab.setValue("M_Warehouse_ID", new Integer(M_Warehouse_ID));
//		//		ADialog.warn(WindowNo, "WarehouseChanged");
//		//	}
//		
//		if ("Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx")))
//		{
//			MProduct product = MProduct.get (ctx, M_Product_ID.intValue());
//			if (product.isStocked())
//			{
//				int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
//				BigDecimal available = MStorage.getQtyAvailable(M_Warehouse_ID, M_Product_ID.intValue());
//				if (available == null)
//					mTab.fireDataStatusEEvent ("NoQtyAvailable", "0");
//				else if (available.compareTo(Env.ZERO) <= 0)
//					mTab.fireDataStatusEEvent ("NoQtyAvailable", available.toString());
//			}
//		}
//		//
//		setCalloutActive(false);
//		if (steps) log.warn("product - fini");
//		return tax (ctx, WindowNo, mTab, mField, value);
//	}	//	product

	/**
	 *	Order Line - Charge.
	 * 		- updates PriceActual from Charge
	 * 		- sets PriceLimit, PriceList to zero
	 * 	Calles tax
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String charge (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer C_Charge_ID = (Integer)value;
		if (C_Charge_ID == null || C_Charge_ID.intValue() == 0)
			return "";
		//	No Product defined
		if (mTab.getValue("M_Product_ID") != null)
		{
			mTab.setValue("C_Charge_ID", null);
			return "ChargeExclusively";
		}

		Env.setContext(ctx, WindowNo, "DiscountSchema", "N");
		try
		{
			String SQL = "SELECT ChargeAmt FROM C_Charge WHERE C_Charge_ID=?";
			PreparedStatement pstmt = DB.prepareStatement(SQL);
			pstmt.setInt(1, C_Charge_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				mTab.setValue ("PriceEntered", rs.getBigDecimal (1));
				mTab.setValue ("PriceActual", rs.getBigDecimal (1));
				mTab.setValue ("PriceLimit", Env.ZERO);
				mTab.setValue ("PriceList", Env.ZERO);
				mTab.setValue ("Discount", Env.ZERO);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
                        log.log(Level.SEVERE, "charge" + e);
			return e.getLocalizedMessage();
		}
		//
		return tax (ctx, WindowNo, mTab, mField, value);
	}	//	charge


	/**
	 *	Order Line - Tax.
	 *		- basis: Product, Charge, BPartner Location
	 *		- sets C_Tax_ID
	 *  Calles Amount
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String tax (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		String column = mField.getColumnName();
		if (value == null)
			return "";
		if (steps) log.log(Level.WARNING,"tax - init");
		
		//	Check Product
		int M_Product_ID = 0;
		if (column.equals("M_Product_ID"))
			M_Product_ID = ((Integer)value).intValue();
		else
			M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		int C_Charge_ID = 0;
		if (column.equals("C_Charge_ID"))
			C_Charge_ID = ((Integer)value).intValue();
		else
			C_Charge_ID = Env.getContextAsInt(ctx, WindowNo, "C_Charge_ID");
		log.log(Level.INFO, "tax - Product=" + M_Product_ID + ", C_Charge_ID=" + C_Charge_ID);
		if (M_Product_ID == 0 && C_Charge_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value);		//

		//	Check Partner Location
		int shipC_BPartner_Location_ID = 0;
		if (column.equals("C_BPartner_Location_ID"))
			shipC_BPartner_Location_ID = ((Integer)value).intValue();
		else
			shipC_BPartner_Location_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_Location_ID");
		if (shipC_BPartner_Location_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value);		//
		log.log(Level.INFO,"tax - Ship BP_Location=" + shipC_BPartner_Location_ID);

		//
		Timestamp billDate = Env.getContextAsDate(ctx, WindowNo, "DateOrdered");
		log.log(Level.INFO,"tax - Bill Date=" + billDate);

		Timestamp shipDate = Env.getContextAsDate(ctx, WindowNo, "DatePromised");
		log.log(Level.INFO,"tax - Ship Date=" + shipDate);

		int AD_Org_ID = Env.getContextAsInt(ctx, WindowNo, "AD_Org_ID");
		log.log(Level.INFO,"tax - Org=" + AD_Org_ID);

		int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
		log.log(Level.INFO,"tax - Warehouse=" + M_Warehouse_ID);

		int billC_BPartner_Location_ID = Env.getContextAsInt(ctx, WindowNo, "Bill_Location_ID");
		if (billC_BPartner_Location_ID == 0)
			billC_BPartner_Location_ID = shipC_BPartner_Location_ID;
		log.log(Level.INFO,"tax - Bill BP_Location=" + billC_BPartner_Location_ID);

		//
		int C_Tax_ID = Tax.get (ctx, M_Product_ID, C_Charge_ID, billDate, shipDate,
			AD_Org_ID, M_Warehouse_ID, billC_BPartner_Location_ID, shipC_BPartner_Location_ID,
			"Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx")));
		log.log(Level.INFO,"tax - Tax ID=" + C_Tax_ID);
		//
		if (C_Tax_ID == 0)
			//mTab.fireDataStatusEEvent(log.retrieveError());
                        JOptionPane.showMessageDialog(null,"ERROR:" , "log.retrieveError()" , JOptionPane.ERROR_MESSAGE);
                
		else
			mTab.setValue("C_Tax_ID", new Integer(C_Tax_ID));
		//
		if (steps) log.log(Level.WARNING,"tax - fini");
		return amt(ctx, WindowNo, mTab, mField, value);
	}	//	tax


	/**
	 *	Order Line - Amount.
	 *		- called from QtyOrdered, Discount and PriceActual
	 *		- calculates Discount or Actual Amount
	 *		- calculates LineNetAmt
	 *		- enforces PriceLimit
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String amt (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		if (steps) log.log(Level.WARNING,"amt - init");
//		int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
//		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
                // int PP_ProfileBOM_ID = Env.getContextAsInt(Env.getCtx(), WindowNo, "#PP_PrifileBOM_ID");
		int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                int StdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");
                
                
		BigDecimal PriceE,PriceEt, PriceT, PriceP, PriceF, Margin, QtyE,QtyEt, QtyT, PriceList,Qty;
		//	get values
		PriceList=Env.ZERO;
                Qty=Env.ZERO;
		//
                Integer PP_ProfileBOM_ID = (Integer)mTab.getValue("PP_ProfileBOM_ID");
                QtyE = (BigDecimal)mTab.getValue("QtyE");
                QtyEt = (BigDecimal)mTab.getValue("QtyEt");
		QtyT = (BigDecimal)mTab.getValue("QtyT");
		PriceE = (BigDecimal)mTab.getValue("PriceE");
                PriceEt = (BigDecimal)mTab.getValue("PriceEt");
		PriceT = (BigDecimal)mTab.getValue("PriceT");
		Margin = (BigDecimal)mTab.getValue("Margin");
		PriceP = (BigDecimal)mTab.getValue("PriceP");
		PriceF = (BigDecimal)mTab.getValue("PriceF");
		
		//	Qty changed - recalc price
		if ((mField.getColumnName().equals("QtyE") || mField.getColumnName().equals("QtyT") || mField.getColumnName().equals("QtyEt")))
		{
			int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
			if (mField.getColumnName().equals("QtyE"))
                        {   
                             Integer M_ProductE_ID = (Integer)mTab.getValue("M_ProductE_ID");
                              QtyE = (BigDecimal)value;
                             if (M_ProductE_ID == null || M_ProductE_ID.intValue() == 0)
                             { PriceE=Env.ZERO;  
                             }
                             else
                             {
                                 preciodeing(M_ProductE_ID);
                             }
			BigDecimal precioE =Env.ZERO;
                            precioE = m_PriceStd.multiply(QtyE);
			//
			
			mTab.setValue("PriceE", precioE);
			
                        }
                        if (mField.getColumnName().equals("QtyEt"))
                        {   
                             Integer M_ProductEt_ID = (Integer)mTab.getValue("M_ProductEt_ID");
                              QtyEt = (BigDecimal)value;
                             if (M_ProductEt_ID == null || M_ProductEt_ID.intValue() == 0)
                             { PriceEt=Env.ZERO;  
                             }
                             else
                             {
                                 preciodeing(M_ProductEt_ID);
                             }
			BigDecimal precioEt =Env.ZERO;
                            precioEt = m_PriceStd.multiply(QtyEt);
			//
			
			mTab.setValue("PriceEt", precioEt);
			
                        }
                        if (mField.getColumnName().equals("QtyT"))
                        {   Integer M_ProductT_ID = (Integer)mTab.getValue("M_ProductT_ID");
                            QtyT = (BigDecimal)value;
                            BigDecimal precioT =Env.ZERO;
                            precioT = PriceT.multiply(QtyT);
			//
			
			mTab.setValue("PriceT", precioT);
			
                        }
		}
		
System.out.println("profilebom------------ " +PP_ProfileBOM_ID);
try
            {
              StringBuffer plv=new StringBuffer("SELECT PriceList,Qty FROM PP_ProfileBOM WHERE IsActive='Y' AND AD_Client_ID=? and PP_ProfileBOM_ID=? ");
                         PreparedStatement pstmtplv = DB.prepareStatement(plv.toString());
			pstmtplv.setInt(1, AD_Client_ID);
                         pstmtplv.setInt(2, PP_ProfileBOM_ID.intValue());
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv = pstmtplv.executeQuery();
			//while (!m_calculated && rsplv.next())
                        if (rsplv.next())
			{
                            PriceList= rsplv.getBigDecimal(1);
                            Qty= rsplv.getBigDecimal(2);
                            System.out.println("costo del prod " +PriceList);
                        }
                        
                        rsplv.close();
                        pstmtplv.close();
                        
}
catch (SQLException e)
{
}
System.out.println("costo de la formula --------------" +PriceList);
               if (Qty.doubleValue()!=0.0)
               {
                  PriceList = PriceList.divide(Qty,3,PriceList.ROUND_HALF_UP);
                   
               }
		//	Line Net Amt
		BigDecimal LineNetAmt = Env.ZERO;
                LineNetAmt = PriceE.add(PriceT).add(PriceF).add(PriceEt).add(PriceP).add(PriceList);
                
               
                        //PriceList = (BigDecimal)mTab.getValue("PriceList");
			BigDecimal margen = Env.ZERO;
                        BigDecimal cien = Env.ONEHUNDRED;
                        BigDecimal uno = new BigDecimal(1.0);
                        
                        margen = Margin.divide(cien,3);
                        margen = uno.subtract(margen);
                        if (margen.doubleValue()!=0.0)
                        {
                            LineNetAmt = LineNetAmt.divide(margen,3,LineNetAmt.ROUND_HALF_UP);
                        }
                
                
                
		if (LineNetAmt.scale() > StdPrecision)
			LineNetAmt = LineNetAmt.setScale(StdPrecision, BigDecimal.ROUND_HALF_UP);
		log.info("amt = LineNetAmt=" + LineNetAmt);
		mTab.setValue("LineNetAmt", LineNetAmt);
		//
		setCalloutActive(false);
		return "";
	}	//	amt

	/**
	 *	Order Line - Quantity.
	 *		- called from C_UOM_ID, QtyEntered, QtyOrdered
	 *		- enforces qty UOM relationship
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String margin (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.log(Level.WARNING,"qty - init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal Margin, PriceList, LineNetAmt;
		
		//	No Product
		if (mField.getColumnName().equals("Margin") || mField.getColumnName().equals("PriceList"))
		{
			
			Margin = (BigDecimal)mTab.getValue("Margin");
                        PriceList = (BigDecimal)mTab.getValue("PriceList");
			BigDecimal margen = Env.ZERO;
                        BigDecimal cien = Env.ONEHUNDRED;
                        BigDecimal uno = new BigDecimal(1.0);
                        
                        margen = Margin.divide(cien,3).add(uno);
                        LineNetAmt = PriceList.multiply(margen);
			
			mTab.setValue("LineNetAmt", LineNetAmt);
		}
		//	QtyEntered changed - calculate QtyOrdered
		
		//
		setCalloutActive(false);
		return "";
	}	//	qty
        
        /**
	 *	Order Line - Quantity.
	 *		- called from C_UOM_ID, QtyEntered, QtyOrdered
	 *		- enforces qty UOM relationship
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);
		
		int M_Product_ID=0;
                int pba3 =0;
                String consecutivo="";
                 String consecutivo2="";
                 StringBuffer sbk= new StringBuffer("");
                 int categoria=0;
                Integer i_M_Product_ID = (Integer)value;
                M_Product_ID= i_M_Product_ID.intValue();
                String SQL = "SELECT Name, Value,VersionNo, M_Product_Category_ID From M_Product where IsActive='Y' and M_Product_ID=?";
		//	Use newest price list - may not be future
		try
		{  System.out.println("valor del value cortado int + 1" +SQL.toString());
			PreparedStatement pstmt = DB.prepareStatement(SQL);
			pstmt.setInt(1, M_Product_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	Tax Included
				mTab.setValue("Name", rs.getString(1));
                                  System.out.println("valor del nombre" +rs.getString(1));
                                  System.out.println("Categoria" +rs.getInt(4));
                                      //  mTab.setValue("M_Product_Category_ID", rs.getInt(4));
                                  categoria = rs.getInt(4);
                                String categoriast ="";
                                categoriast = categoriast.valueOf(categoria);
                                   System.out.println("Categoria" +categoriast);
                                      mTab.setValue("M_Product_Category_ID", categoriast);
                                  mTab.setValue("Specie", rs.getString(3));
                                consecutivo=rs.getString(2);
                                System.out.println("valor .........--------------- " +rs.getString(2));
				//	Price Limit Enforce
			//	Env.setContext(ctx, WindowNo, "EnforcePriceLimit", rs.getString(2));
				//	Currency
//			8	Integer ii = new Integer(rs.getInt(3));
//				mTab.setValue("C_Currency_ID", ii);
//				//	Currency Precision
//				Env.setContext(ctx, WindowNo, "StdPrecision", rs.getInt(4));
//				//	PriceList Version
//				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", rs.getInt(5));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
		}
                
                  StringBuffer SQL1 = new StringBuffer("SELECT Value From PP_ProfileBOM where IsActive='Y' and Value LIKE '");
		  SQL1.append(consecutivo);
                  SQL1.append("%' Order by Value DESC");
                  //	Use newest price list - may not be future
		try
		{
			PreparedStatement pstmt1 = DB.prepareStatement(SQL1.toString());
			//pstmt1.setInt(1, M_Product_ID);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next())
			{
				//	Tax Included
				
                                consecutivo2=rs1.getString(1);
                                System.out.println("valor 2" +consecutivo2);
//                                for(int i=9; i<consecutivo2.length(); i++)
//                                {
//                                    sbk.append(consecutivo2.charAt(i));
//                                }
                                String pba0 = consecutivo2.substring(10,consecutivo2.length());
                                //System.out.println("cadena ----------  "+sbk.toString());
                                Integer pba2= new Integer(pba0);
                                System.out.println("valor del value cortado en integer -----" +pba2);
                                pba3=pba2.intValue();
                                 System.out.println("valor del value cortado int " +pba3);
                                pba3++;
                                 System.out.println("valor del value cortado int + 1 " +pba3);
                                 
			sbk.append(consecutivo.toString() +"-0" +pba3);
                                 mTab.setValue("Value", sbk.toString());
				//	Price Limit Enforce
			//	Env.setContext(ctx, WindowNo, "EnforcePriceLimit", rs.getString(2));
				//	Currency
//				Integer ii = new Integer(rs.getInt(3));
//				mTab.setValue("C_Currency_ID", ii);
//				//	Currency Precision
//				Env.setContext(ctx, WindowNo, "StdPrecision", rs.getInt(4));
//				//	PriceList Version
//				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", rs.getInt(5));
			}
                        else
                        {
                            StringBuffer pba = new StringBuffer("");
                            pba.append(consecutivo);
                            
                            pba.append("-01");
                              System.out.println("valor delvalue nuevo" +pba.toString());
                            mTab.setValue("Value", pba.toString());
                            
                        }
			rs1.close();
			pstmt1.close();
		}
		catch (SQLException e)
		{
		}
                
                
		//
		setCalloutActive(false);
		return "";
	}	
        
        public String productEprice (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_ProductE_ID = (Integer)value;
		if (M_ProductE_ID == null || M_ProductE_ID.intValue() == 0)
			return "";
		if (steps) log.log(Level.WARNING,"product - init");
		setCalloutActive(true);
                
                
                Integer PP_ProfileBOM_ID = (Integer)mTab.getValue("PP_ProfileBOM_ID");
                BigDecimal Envasado = (BigDecimal)mTab.getValue("Envase");
		//
		//mTab.setValue("C_Charge_ID", null);
		//	Set Attribute
//		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
//			&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
//			mTab.setValue("M_AttributeSetInstance_ID", new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID")));
//		else
//			mTab.setValue("M_AttributeSetInstance_ID", null);
			
		/*****	Price Calculation see also qty	****/
                BigDecimal envase = Env.ZERO;
		int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                try
            {
              StringBuffer plv=new StringBuffer("SELECT p.ShelfWidth FROM PP_ProfileBOM mp INNER JOIN M_Product p ON(p.M_Product_ID=mp.M_Product_ID) WHERE p.IsActive='Y' AND p.AD_Client_ID=? and mp.PP_ProfileBOM_ID=? ");
                         PreparedStatement pstmtplv = DB.prepareStatement(plv.toString());
			pstmtplv.setInt(1, AD_Client_ID);
                         pstmtplv.setInt(2, PP_ProfileBOM_ID.intValue());
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv = pstmtplv.executeQuery();
			//while (!m_calculated && rsplv.next())
                        if (rsplv.next())
			{
                            envase= rsplv.getBigDecimal(1);
                           
                            System.out.println("envase " +envase);
                        }
                        rsplv.close();
                        pstmtplv.close();
}
catch (SQLException e)
{
}
                
                BigDecimal uno =Env.ONE;
                 BigDecimal cien = Env.ONEHUNDRED;
		//BigDecimal Qty = (BigDecimal)mTab.getValue("QtyE");
		BigDecimal Qty = Env.ZERO;
                Qty=uno.multiply(cien).divide(Envasado,3,uno.ROUND_HALF_UP);
                mTab.setValue("QtyE", Qty);
                mTab.setValue("QtyEt", Qty);
                preciodeing(M_ProductE_ID);
		Integer etiqueta = new Integer(1002823);
                BigDecimal precioE = Env.ZERO;
                BigDecimal precioEt = Env.ZERO;
                precioE=m_PriceStd.multiply(Qty).divide(cien,5,m_PriceStd.ROUND_HALF_UP);
                preciodeing(etiqueta);
                precioEt=m_PriceStd.multiply(Qty).divide(cien,5,m_PriceStd.ROUND_HALF_UP);
		//		
		mTab.setValue("PriceE", precioE);
		mTab.setValue("PriceEt", precioEt);
		
		
		
		
		//
		setCalloutActive(false);
		if (steps) log.log(Level.WARNING,"product - fini");
		return amt (ctx, WindowNo, mTab, mField, value);
	}	//	product
        
        public String productEtprice (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_ProductEt_ID = (Integer)value;
		if (M_ProductEt_ID == null || M_ProductEt_ID.intValue() == 0)
			return "";
		if (steps) log.log(Level.WARNING,"product - init");
		setCalloutActive(true);
                
                
                Integer PP_ProfileBOM_ID = (Integer)mTab.getValue("PP_ProfileBOM_ID");
                BigDecimal Envasado = (BigDecimal)mTab.getValue("Envase");
		//
		//mTab.setValue("C_Charge_ID", null);
		//	Set Attribute
//		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
//			&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
//			mTab.setValue("M_AttributeSetInstance_ID", new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID")));
//		else
//			mTab.setValue("M_AttributeSetInstance_ID", null);
			
		/*****	Price Calculation see also qty	****/
                BigDecimal envase = Env.ZERO;
		int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                try
            {
              StringBuffer plv=new StringBuffer("SELECT p.ShelfWidth FROM PP_ProfileBOM mp INNER JOIN M_Product p ON(p.M_Product_ID=mp.M_Product_ID) WHERE p.IsActive='Y' AND p.AD_Client_ID=? and mp.PP_ProfileBOM_ID=? ");
                         PreparedStatement pstmtplv = DB.prepareStatement(plv.toString());
			pstmtplv.setInt(1, AD_Client_ID);
                         pstmtplv.setInt(2, PP_ProfileBOM_ID.intValue());
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv = pstmtplv.executeQuery();
			//while (!m_calculated && rsplv.next())
                        if (rsplv.next())
			{
                            envase= rsplv.getBigDecimal(1);
                           
                            System.out.println("envase " +envase);
                        }
                        rsplv.close();
                        pstmtplv.close();
}
catch (SQLException e)
{
}
                
                BigDecimal uno =Env.ONE;
                 BigDecimal cien = Env.ONEHUNDRED;
		//BigDecimal Qty = (BigDecimal)mTab.getValue("QtyE");
		BigDecimal QtyEt = Env.ZERO;
                QtyEt=uno.multiply(cien).divide(Envasado,3,uno.ROUND_HALF_UP);
                mTab.setValue("QtyEt", QtyEt);
                preciodeing(M_ProductEt_ID);
		
                BigDecimal precioEt = Env.ZERO;
                precioEt=m_PriceStd.multiply(QtyEt).divide(cien,5,m_PriceStd.ROUND_HALF_UP);
		//		
		mTab.setValue("PriceEt", precioEt);
		
		
		
		
		//
		setCalloutActive(false);
		if (steps) log.log(Level.WARNING,"product - fini");
		return amt (ctx, WindowNo, mTab, mField, value);
	}	//	product
        
           public String productTprice (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_ProductT_ID = (Integer)value;
		if (M_ProductT_ID == null || M_ProductT_ID.intValue() == 0)
			return "";
		if (steps) log.log(Level.WARNING,"product - init");
		setCalloutActive(true);
                
                
                Integer PP_ProfileBOM_ID = (Integer)mTab.getValue("PP_ProfileBOM_ID");
                BigDecimal QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
		//
		//mTab.setValue("C_Charge_ID", null);
		//	Set Attribute
//		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
//			&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
//			mTab.setValue("M_AttributeSetInstance_ID", new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID")));
//		else
//			mTab.setValue("M_AttributeSetInstance_ID", null);
			
		/*****	Price Calculation see also qty	****/
                BigDecimal envase = Env.ZERO;
		int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                try
            {
              StringBuffer plv=new StringBuffer("SELECT mp.Qty FROM PP_ProfileBOM mp WHERE mp.AD_Client_ID=? and mp.PP_ProfileBOM_ID=? ");
                         PreparedStatement pstmtplv = DB.prepareStatement(plv.toString());
			pstmtplv.setInt(1, AD_Client_ID);
                         pstmtplv.setInt(2, PP_ProfileBOM_ID.intValue());
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv = pstmtplv.executeQuery();
			//while (!m_calculated && rsplv.next())
                        if (rsplv.next())
			{
                            envase= rsplv.getBigDecimal(1);
                           
                            System.out.println("envase " +envase);
                        }
                        rsplv.close();
                        pstmtplv.close();
}
catch (SQLException e)
{
}
                
                BigDecimal uno =Env.ONE;
                 BigDecimal cien = Env.ONEHUNDRED;
                 BigDecimal Qty = Env.ZERO;
                 if (QtyOrdered.doubleValue()!=0.0)
                    Qty = envase.divide(QtyOrdered,BigDecimal.ROUND_UP);
                 else
                     Qty = Env.ONE;
                 
		
                mTab.setValue("QtyT", Qty);
                
                preciodeing(M_ProductT_ID);
		
                BigDecimal precioT = Env.ZERO;
                if (envase.doubleValue()!=0.0)
                precioT=m_PriceStd.multiply(Qty).divide(envase,5,m_PriceStd.ROUND_HALF_UP);
                else
                    precioT=m_PriceStd.multiply(Qty).divide(uno,5,m_PriceStd.ROUND_HALF_UP);
		//		
		mTab.setValue("PriceT", precioT);
		
		
		
		
		//
		setCalloutActive(false);
		if (steps) log.log(Level.WARNING,"product - fini");
		return amt (ctx, WindowNo, mTab, mField, value);
	}	//	product
	
        private BigDecimal m_PriceStd=Env.ZERO;
        private BigDecimal m_PriceList=Env.ZERO;
        private BigDecimal m_PriceLimit=Env.ZERO;
        private int m_C_Currency_ID=0;
        private int m_M_PriceList_Version_ID=0;
        
        public BigDecimal preciodeing(Integer m_ing)
        {int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
        //    Integer m_ingint = new Integer(m_ing);
            m_PriceStd=Env.ZERO;
            try
            {
              StringBuffer plv=new StringBuffer("SELECT BOM_PriceStd(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceStd, BOM_PriceList(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceList, BOM_PriceLimit(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceLimit,pv.ValidFrom,pl.C_Currency_ID, pv.M_PriceList_Version_ID FROM M_Product p INNER JOIN M_ProductPrice pp ON (p.M_Product_ID=pp.M_Product_ID) INNER JOIN  M_PriceList_Version pv ON (pp.M_PriceList_Version_ID=pv.M_PriceList_Version_ID) INNER JOIN M_Pricelist pl ON (pv.M_PriceList_ID=pl.M_PriceList_ID) WHERE pv.IsActive='Y' AND AD_Client_ID=? and p.M_Product_ID=? AND pl.IsSOPriceList='N' and pl.IsDefault='Y'"); //ORDER BY pv.ValidFrom DESC");
                         PreparedStatement pstmtplv = DB.prepareStatement(plv.toString());
			pstmtplv.setInt(1, AD_Client_ID);
                         pstmtplv.setInt(2, m_ing.intValue());
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv = pstmtplv.executeQuery();
			//while (!m_calculated && rsplv.next())
                        if (rsplv.next())
			{
				Timestamp plDate = rsplv.getTimestamp(4);
				//	we have the price list
				//	if order date is after or equal PriceList validFrom
//				if (plDate == null) // || !m_PriceDate.before(plDate))
//				{
					//	Prices
                                
					m_PriceStd = rsplv.getBigDecimal (1);
                                        //preciostd.add(precios, rsplv.getString(1));
                                        System.out.println("cont precio 1 " +m_PriceStd);
            //                            precios++;
					if (rsplv.wasNull ())
						m_PriceStd = Env.ZERO;
					m_PriceList = rsplv.getBigDecimal (2);
					if (rsplv.wasNull ())
						m_PriceList = Env.ZERO;
					m_PriceLimit = rsplv.getBigDecimal (3);
					if (rsplv.wasNull ())
						m_PriceLimit = Env.ZERO;
						//
				//	m_C_UOM_ID = rsplv.getInt (4);
					m_C_Currency_ID = rsplv.getInt (5);
					m_M_PriceList_Version_ID = rsplv.getInt(6);
					//
                                        
//					m_calculated = true;
//					break;
//				}
                                
			}
			rsplv.close();
			pstmtplv.close();
                        BigDecimal convrate=Env.ZERO;
                        System.out.println("cont precio 2 " +m_PriceStd);
                        System.out.println("moneda--------2 " +m_C_Currency_ID);
                        System.out.println("version de lista de precios "+m_M_PriceList_Version_ID);
                        if (m_C_Currency_ID==100)
                        {
                           StringBuffer conv=new StringBuffer("select C_Conversion_Rate_ID, C_Currency_ID,C_Currency_ID_To, MultiplyRate from C_Conversion_Rate where AD_Client_ID=? and C_Currency_ID=? order by ValidFrom");
                         PreparedStatement pstmtplv1 = DB.prepareStatement(conv.toString());
			pstmtplv1.setInt(1, AD_Client_ID);
                         pstmtplv1.setInt(2, m_C_Currency_ID);
			//pstmt.setInt(2, m_M_PriceList_ID);
			ResultSet rsplv1 = pstmtplv1.executeQuery();
                        while (rsplv1.next())
                        {
                            if (rsplv1.getInt(3)==130)
                            {
                             convrate=rsplv1.getBigDecimal(4);  
                             System.out.println("tipo de cambio" +convrate);
                            }
                        }
                        rsplv1.close();
                        pstmtplv1.close();
                          m_PriceStd=m_PriceStd.multiply(convrate);
                         System.out.println("convertido precio " +m_PriceStd);
                        }
                        
                      
                        
            }
           catch (SQLException e)
		{
			log.log(Level.SEVERE,"No hay precio asignado a este producto", e);
                      
		}
            //System.out.println("ing "+m_ingint +" precio funcion "+m_PriceStd);
            return m_PriceStd;
        }
        
}	//	CalloutOrder


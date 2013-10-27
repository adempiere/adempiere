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
package org.compiere.wstore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.util.CLogger;
import org.compiere.util.WebUser;

/**
 *  Web Order
 *
 *  @author Jorg Janke
 *  @version $Id: WebOrder.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WebOrder
{
	/**
	 * 	Constructor
	 * 	@param wu web User
	 *	@param wb web basket
	 * 	@param ctx context
	 */
	public WebOrder(WebUser wu, WebBasket wb, Properties ctx)
	{
		m_ctx = ctx;
		createOrder (wu, wb);
	}	//	WebOrder

	/**
	 * 	Constructor
	 * 	@param order existing order
	 */
	public WebOrder(MOrder order)
	{
		m_ctx = order.getCtx();
		m_order = order;
	}	//	WebOrder

	/**	Attribute Name				*/
	public static final String		NAME = "webOrder";
	/**	Logging						*/
	private CLogger					log = CLogger.getCLogger(getClass());
	/**	Order						*/
	private MOrder					m_order;
	/** Context						*/
	private Properties				m_ctx;


	/**
	 * 	Create Order from Basket
	 * 	@param wu web User
	 *	@param wb web basket
	 * 	@return true if created & processed
	 */
	private boolean createOrder (WebUser wu, WebBasket wb)
	{
		m_order = new MOrder (m_ctx, 0, null);
		log.fine("AD_Client_ID=" + m_order.getAD_Client_ID()
			+ ",AD_Org_ID=" + m_order.getAD_Org_ID() + " - " + m_order);
		//
		m_order.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_Prepay);
		m_order.setPaymentRule(MOrder.PAYMENTRULE_CreditCard);
		m_order.setDeliveryRule(MOrder.DELIVERYRULE_AfterReceipt);
		m_order.setInvoiceRule(MOrder.INVOICERULE_Immediate);
		m_order.setIsSelfService(true);
		if (wb.getM_PriceList_ID() > 0)
			m_order.setM_PriceList_ID(wb.getM_PriceList_ID());
		if (wb.getSalesRep_ID() != 0)
			m_order.setSalesRep_ID(wb.getSalesRep_ID());

		//	BPartner
		m_order.setC_BPartner_ID(wu.getC_BPartner_ID());
		m_order.setC_BPartner_Location_ID(wu.getC_BPartner_Location_ID());
		m_order.setAD_User_ID(wu.getAD_User_ID());
		//
		m_order.setSendEMail(true);
		m_order.setDocAction(MOrder.DOCACTION_Prepare);
		m_order.saveEx();
		log.fine("ID=" + m_order.getC_Order_ID()
			+ ", DocNo=" + m_order.getDocumentNo());

		ArrayList<WebBasketLine> lines = wb.getLines();
		for (int i = 0; i < lines.size(); i++)
		{
			WebBasketLine wbl = lines.get(i);
			MOrderLine ol = new MOrderLine(m_order);
			ol.setM_Product_ID(wbl.getM_Product_ID(), true);
			ol.setQty(wbl.getQuantity());
			ol.setPrice();
			ol.setPrice(wbl.getPrice());
			ol.setTax();
			ol.saveEx();
		}	//	for all lines
		boolean ok = m_order.processIt (MOrder.DOCACTION_Prepare);
		m_order.saveEx();

		//	Web User = Customer
		if (!wu.isCustomer())
		{
		//	log.info("-------------------------------------- " + wu.isCustomer());
			wu.setIsCustomer(true);
			wu.save();
		//	log.info("-------------------------------------- " + wu.isCustomer());
		}
		BigDecimal amt = m_order.getGrandTotal();
		log.info("Amt=" + amt);
		return ok;
	}	//	createOrder


	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("WebOrder[");
		sb.append(m_order)
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Process/Complete Order
	 * 	@param payment payment
	 *	@return true if processed
	 */
	public boolean process (MPayment payment)
	{
		if (m_order == null)
			return false;
		if (payment.get_ID() == 0)
			payment.saveEx();
		m_order.setC_Payment_ID (payment.getC_Payment_ID());
		m_order.setDocAction (MOrder.DOCACTION_WaitComplete);
		boolean ok = m_order.processIt (MOrder.DOCACTION_WaitComplete);
		m_order.saveEx();
		//
		payment.setC_Order_ID (m_order.getC_Order_ID());
		payment.setC_Invoice_ID (getInvoice_ID());
		//
		return ok;
	}	//	process
	
	/**
	 * 	Get Order ID
	 *	@return id
	 */
	public int getC_Order_ID()
	{
		if (m_order != null)
			return m_order.getC_Order_ID();
		return 0;
	}	//	getC_Order_ID

	/**
	 * 	Get Order Org ID
	 *	@return id
	 */
	public int getAD_Org_ID()
	{
		if (m_order != null)
			return m_order.getAD_Org_ID();
		return 0;
	}	//	getAD_Org_ID

	/**
	 * 	Is order completed
	 *	@return true if completed or closed
	 */
	public boolean isCompleted()
	{
		if (m_order == null)
			return false;
		return MOrder.STATUS_Completed.equals(m_order.getDocStatus())
			|| MOrder.STATUS_Closed.equals(m_order.getDocStatus()); 
	}	//	isCompleted

	/**
	 * 	Is order in progress
	 *	@return true if in progress
	 */
	public boolean isInProgress()
	{
		if (m_order == null)
			return false;
		return MOrder.DOCSTATUS_InProgress.equals(m_order.getDocStatus());
	}	//	isInProgress


	/*************************************************************************/

	/**
	 * 	Get Document No
	 *	@return Document No
	 */
	public String getDocumentNo()
	{
		return m_order.getDocumentNo();
	}

	public BigDecimal getTotalLines()
	{
		return m_order.getTotalLines();
	}

	public BigDecimal getFreightAmt()
	{
		return m_order.getFreightAmt();
	}

	public BigDecimal getTaxAmt()
	{
		return m_order.getGrandTotal()
			.subtract(m_order.getTotalLines())
			.subtract(m_order.getFreightAmt());
	}
	public BigDecimal getGrandTotal()
	{
		return m_order.getGrandTotal();
	}
	public int getSalesRep_ID()
	{
		return m_order.getSalesRep_ID();
	}

	protected MOrder getOrder()
	{
		return m_order;
	}

	/*************************************************************************/

	private String	m_invoiceInfo = null;
	private int		m_C_Invoice_ID = -1;

	/**
	 * 	Get Invoice Info
	 * 	@return invoice document no
	 */
	public String getInvoiceInfo()
	{
		if (m_invoiceInfo == null)
		{
			MInvoice[] invoices = m_order.getInvoices();
			int length = invoices.length;
			if (length > 0)		//	get last invoice
			{
				m_C_Invoice_ID = invoices[length-1].getC_Invoice_ID();
				m_invoiceInfo = invoices[length-1].getDocumentNo();
			}
		}
		return m_invoiceInfo;
	}

	/**
	 * 	Get Invoice ID
	 * 	@return C_ Invoice_ID
	 */
	public int getInvoice_ID()
	{
		if (m_C_Invoice_ID == -1)
			getInvoiceInfo();
		return m_C_Invoice_ID;
	}	//	getInvoice_ID

	/**
	 *	Get Currency id
	 *	@return Currency id
	 */
	public int getC_Currency_ID()
	{
		if (m_order == null)
			return 0;
		return m_order.getC_Currency_ID();
	}	//	getC_Currency_ID

	/**
	 *	Get ISO Code of Currency
	 *	@return Currency ISO
	 */
	public String getCurrencyISO()
	{
		if (m_order == null)
			return "";
		return MCurrency.getISO_Code (m_ctx, m_order.getC_Currency_ID());
	}	//	getCurrencyISO

}	//	WebOrder

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
 * or via info@compiere.org or http://www.compiere.org/license.html 		  *
 * @contributor Karsten Thiemann / Schaeffer AG - kthiemann@adempiere.org     *
 *****************************************************************************/
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Order;
import org.compiere.model.MOrder;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Trx;


/**
 *	Order Batch Processing
 *	
 *  @author Jorg Janke
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *  @version $Id: OrderBatchProcess.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class OrderBatchProcess extends SvrProcess
{
	private int 		docTypeTargetId = 0;
	private String 		docStatus = null;
	private int 		partnerId = 0;
	private String 		isSelfService = null;
	private Timestamp 	dateOrderedFrom = null;
	private Timestamp 	dateOrderedTo = null;
	private String 		docAction = null;
	private String 		isDelivered = null;
	private String 		isInvoiced = null;

	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_DocTypeTarget_ID"))
				docTypeTargetId = para[i].getParameterAsInt();
			else if (name.equals("DocStatus"))
				docStatus = (String)para[i].getParameter();
			else if (name.equals("IsSelfService"))
				isSelfService = (String)para[i].getParameter();
			else if (name.equals("C_BPartner_ID"))
				partnerId = para[i].getParameterAsInt();
			else if (name.equals("DateOrdered"))
			{
				dateOrderedFrom = (Timestamp)para[i].getParameter();
				dateOrderedTo = (Timestamp)para[i].getParameter_To();
			}
			else if (name.equals("DocAction"))
				docAction = (String)para[i].getParameter();
			else if (name.equals("IsDelivered")) {
				isDelivered = (String)para[i].getParameter();
			} else if (name.equals("IsInvoiced")) {
				isInvoiced = (String)para[i].getParameter();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Process
	 *	@return msg
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_DocTypeTarget_ID=" + docTypeTargetId + ", DocStatus=" + docStatus
			+ ", IsSelfService=" + isSelfService + ", C_BPartner_ID=" + partnerId
			+ ", DateOrdered=" + dateOrderedFrom + "->" + dateOrderedTo
			+ ", DocAction=" + docAction + ", IsDelivered=" + isDelivered
			+ ", IsInvoiced=" + isInvoiced);
		
		if (docTypeTargetId == 0)
			throw new AdempiereUserError("@NotFound@: @C_DocTypeTarget_ID@");
		if (docStatus == null || docStatus.length() != 2)
			throw new AdempiereUserError("@NotFound@: @DocStatus@");
		if (docAction == null || docAction.length() != 2)
			throw new AdempiereUserError("@NotFound@: @DocAction@");
		
		//
		StringBuffer sql = new StringBuffer("SELECT * FROM C_Order o "
			+ " WHERE o.C_DocTypeTarget_ID=? AND o.DocStatus=? ");
		if (isSelfService != null && isSelfService.length() == 1)
			sql.append(" AND o.IsSelfService='").append(isSelfService).append("'");
		if (partnerId != 0)
			sql.append(" AND o.C_BPartner_ID=").append(partnerId);
		if (dateOrderedFrom != null)
			sql.append(" AND TRUNC(o.DateOrdered, 'DD') >= ").append(DB.TO_DATE(dateOrderedFrom, true));
		if (dateOrderedTo != null)
			sql.append(" AND TRUNC(o.DateOrdered, 'DD') <= ").append(DB.TO_DATE(dateOrderedTo, true));
		if ("Y".equals(isDelivered))
			sql.append(" AND NOT EXISTS (SELECT l.C_OrderLine_ID FROM C_OrderLine l ")
			.append(" WHERE l.C_Order_ID=o.C_Order_ID AND l.QtyOrdered>l.QtyDelivered) ");
		else if ("N".equals(isDelivered))
			sql.append(" AND EXISTS (SELECT l.C_OrderLine_ID FROM C_OrderLine l ")
			.append(" WHERE l.C_Order_ID=o.C_Order_ID AND l.QtyOrdered>l.QtyDelivered) ");
		if ("Y".equals(isInvoiced))
			sql.append(" AND NOT EXISTS (SELECT l.C_OrderLine_ID FROM C_OrderLine l ")
			.append(" WHERE l.C_Order_ID=o.C_Order_ID AND l.QtyOrdered>l.QtyInvoiced) ");
		else if ("N".equals(isInvoiced))
			sql.append(" AND EXISTS (SELECT l.C_OrderLine_ID FROM C_OrderLine l ")
			.append(" WHERE l.C_Order_ID=o.C_Order_ID AND l.QtyOrdered>l.QtyInvoiced) ");
		
		int counter = 0;
		int errCounter = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, docTypeTargetId);
			pstmt.setString(2, docStatus);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (process(rs.getInt(I_C_Order.COLUMNNAME_C_Order_ID)))
					counter++;
				else
					errCounter++;
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return "@Updated@=" + counter + ", @Errors@=" + errCounter;
	}	//	doIt
	
	/**
	 * 	Process Order
	 *	@param orderId order ID
	 *	@return true if ok
	 */
	private boolean process (int orderId)
	{
		try {
				Trx.run(trxName ->
				{
					MOrder order = new MOrder(getCtx(), orderId, trxName);
					log.info(order.toString());
					//
					order.setDocAction(docAction);
					if (order.processIt(docAction)) {
						order.saveEx();
						addLog(0, null, null, order.getDocumentNo() + ": OK");
					}
					else {
						addLog(0, null, null, order.getDocumentNo() + ": Error " + order.getProcessMsg());
						throw new AdempiereException(order.getDocumentNo() + ": Error " + order.getProcessMsg());
					}
				});
		}
		catch (Exception e)
		{
			addLog(e.getMessage());
			return false;
		}
		return true;
	}	//	process
	
}	//	OrderBatchProcess

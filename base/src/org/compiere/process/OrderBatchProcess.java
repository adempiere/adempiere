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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.util.Trx;

/**
 *	Order Batch Processing
 *	
 *  @author Jorg Janke
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *  @version $Id: OrderBatchProcess.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  @author Susanne Calderon Systemhaus Westfalia, El Salvador
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/758">
 * 		@see FR [ 758 ] SmartBrowser To Process Orders in Batch</a>
 */
public class OrderBatchProcess extends OrderBatchProcessAbstract {
	/**	Counter for processed orders	*/
	private int counter = 0;
	/**	Counter for failed orders		*/
	private int errCounter = 0;
	
	
	/**
	 * 	Process
	 *	@return msg
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		getSelectionKeys().stream().forEach(orderId -> {
			process(orderId);
		});
		//	
		return "@Updated@=" + counter + ", @Errors@=" + errCounter;
	}	//	doIt
	
	/**
	 * 	Process Order
	 *	@param orderId order ID
	 *	@return true if ok
	 */
	private boolean process (int orderId) {
		try {
			Trx.run(trxName ->
			{
				MOrder order = new MOrder(getCtx(), orderId, trxName);
				log.info(order.toString());
				//
				order.setDocAction(getDocAction());
				if (order.processIt(getDocAction())) {
					order.saveEx();
					addLog(0, null, null, order.getDocumentNo() + ": @OK@");
				} else {
					addLog(0, null, null, order.getDocumentNo() + ": @Error@ " + order.getProcessMsg());
					throw new AdempiereException(order.getDocumentNo() + ": @Error@ " + order.getProcessMsg());
				}
			});
			//	Count it
			counter++;
		} catch (Exception e) {
			errCounter++;
			addLog(e.getMessage());
			log.warning(e.getLocalizedMessage());
			return false;
		}
		return true;
	}	//	process
	
}	//	OrderBatchProcess

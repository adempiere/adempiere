/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MWMInOutBoundLine;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class GenerateShipmentOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	

	protected String p_DocAction = null;
	protected boolean p_IsIncludeNotAvailable = false;
	protected Timestamp p_MovementDate = null;
	private Hashtable m_shipments = new Hashtable();
	
	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		
		p_Record_ID = getRecord_ID();
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
			}
			else if (name.equals("IsIncludeNotAvailable"))
			{
				p_IsIncludeNotAvailable = "Y".equals(para.getParameter());
			}
			else if (name.equals("MovementDate"))
			{
				p_MovementDate = (Timestamp)para.getParameter();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@SuppressWarnings("unchecked")
	protected String doIt () throws Exception
	{
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=WM_InOutBoundLine.WM_InOutboundLine_ID)";		
		Collection <MWMInOutBoundLine>  boundlines = new Query(getCtx(), I_WM_InOutBoundLine.Table_Name, whereClause, get_TrxName())
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()})
										.list();
		
		int seq = 10;
		for (MWMInOutBoundLine boundline: boundlines)
		{
			if(boundline.getQtyToDeliver().signum() > 0 || p_IsIncludeNotAvailable)
			{	
				createMInOut(boundline);
			}	
			seq ++;
		}
		
		Enumeration shipments = m_shipments.elements();
		while (shipments.hasMoreElements())
		{	
			MInOut inout = (MInOut) shipments.nextElement();
			inout.setDocAction(p_DocAction);
			inout.processIt(p_DocAction);		
			if (!inout.processIt(p_DocAction))
				log.warning("Failed: " + inout);
			inout.saveEx();
		}			
		return ""; //;"@DocumentNo@ " + bound.getDocumentNo();
	}	
	

	/**
	 * Create Shipment to Out Bound Order
	 * @param Out Bound Order Line
	 */
	public void createMInOut(MWMInOutBoundLine line)
	{
		MOrderLine oline = line.getMOrderLine();
		if(line.getPickedQty().subtract(oline.getQtyDelivered()).signum() <= 0 && !p_IsIncludeNotAvailable)		
		{
			return;
		}
		
		MLocator standing = null;
		BigDecimal QtyDelivered = Env.ZERO;
		if(p_IsIncludeNotAvailable)
		{
			standing = MLocator.getDefault((MWarehouse)line.getParent().getM_Warehouse());
			QtyDelivered  = line.getQtyToPick().subtract(oline.getQtyDelivered());
		}
		else
		{	
			standing = line.getMLocator();
			QtyDelivered  = line.getPickedQty().subtract(oline.getQtyDelivered());
		}	
		
		MInOut inout = getMInOut(oline);
		inout.setIsSOTrx(true);
		inout.saveEx();
		MInOutLine shipmentLine = new MInOutLine(line.getCtx(), 0 , line.get_TrxName());
		shipmentLine.setM_InOut_ID(inout.getM_InOut_ID());
		shipmentLine.setM_Locator_ID(standing.getM_Locator_ID());
		shipmentLine.setM_Product_ID(line.getM_Product_ID());
		shipmentLine.setQtyEntered(QtyDelivered);
		shipmentLine.setMovementQty(QtyDelivered);
		shipmentLine.setC_OrderLine_ID(oline.getC_OrderLine_ID());
		shipmentLine.saveEx();
	}	
		
	/**
	 * get Document Type
	 * @param docBaseType
	 * @return int Document Type
	 */
	protected int getDocType(String docBaseType)
	{
		MDocType[] docs = MDocType.getOfDocBaseType(getCtx(), docBaseType);

		if (docs == null || docs.length == 0) 
		{
			String reference = Msg.getMsg(getCtx(), "SequenceDocNotFound");
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			throw new AdempiereException(textMsg);
		} 
		else
		{
			
			for (MDocType doc : docs)
			{	
				if(doc.isSOTrx())
				{	
					log.info("Doc Type for "+docBaseType+": "+ doc.getC_DocType_ID());
					return doc.getC_DocType_ID();
				}
			}
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			throw new AdempiereException(textMsg);
			
		}
	}
	
	/**
	 * Create Shipment heder
	 * @param oline Sales Order Line
	 * @return MInOut return the Shipment header
	 */
	private MInOut getMInOut(MOrderLine oline)
	{

		MInOut inout = (MInOut) m_shipments.get(oline.getC_Order_ID());
		if(inout != null)
		{
			return inout;
		}	
		MOrder order = oline.getParent();
		inout = new MInOut(order,getDocType(MDocType.DOCBASETYPE_MaterialDelivery), p_MovementDate);
		m_shipments.put(order.getC_Order_ID(), inout);
		return inout;
	}
}

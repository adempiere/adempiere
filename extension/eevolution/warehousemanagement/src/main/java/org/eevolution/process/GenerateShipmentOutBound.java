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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.X_T_Selection;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;
import org.eevolution.engines.Warehouse.WMRuleEngine;
import org.eevolution.model.I_WM_InOutBound;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.X_WM_InOutBound;

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
	private Timestamp Today = new Timestamp (System.currentTimeMillis());  
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
			else if (name.equals("WM_Section_Type_ID"))
			{
				p_IsIncludeNotAvailable = "Y".equals(para.getParameter());
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
		Collection <MWMInOutBound>  bounds = new Query(getCtx(), I_WM_InOutBound.Table_Name, whereClause, get_TrxName())
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()})
										.list();
		
		int seq = 10;
		for (MWMInOutBound bound: bounds)
		{
			if(MWMInOutBound.DOCSTATUS_Completed.equals(bound.getDocStatus()))
			{	
				createInOut(bound);
			}	
			seq ++;
		}
		return ""; //;"@DocumentNo@ " + bound.getDocumentNo();
	}	
	
	/**
	 * create Distribution Order
	 * @param boundline
	 * @param storages
	 * @throws AdempiereException
	 */
	protected void createInOut(MWMInOutBound bound)
	throws AdempiereException
	{				
		MWMInOutBoundLine[] lines = bound.getLines(true, MWMInOutBoundLine.COLUMNNAME_PickedQty); 
		for (MWMInOutBoundLine line : lines)		
		{
			if (p_IsIncludeNotAvailable)
			{
				MOrderLine oline = new MOrderLine(line.getCtx(),line.getC_OrderLine_ID(), line.get_TrxName());
				createMInOut(oline,line);
			}
			else if(line.getQtyToPick().signum() == 0)
			{
				MOrderLine oline = new MOrderLine(line.getCtx(),line.getC_OrderLine_ID(), line.get_TrxName());
				if(oline.getQtyOrdered().subtract(oline.getQtyDelivered()).signum() > 0)
				{	
					createMInOut(oline,line);
				}	
			}
			
		}
		
		Enumeration shipments = m_shipments.elements();
		while (shipments.hasMoreElements())
		{	
			MInOut inout = (MInOut) shipments.nextElement();
			inout.completeIt();
			inout.saveEx();
		}	
	}
	
	public void createMInOut(MOrderLine oline, MWMInOutBoundLine line)
	{
		MInOut inout = getMInOut(oline);
		MInOutLine shipmetLine = new MInOutLine(line.getCtx(), 0 , line.get_TrxName());
		shipmetLine.setM_InOut_ID(inout.getM_InOut_ID());
		shipmetLine.setM_Product_ID(line.getM_Product_ID());
		shipmetLine.setQtyEntered(line.getPickedQty());
		shipmetLine.setC_OrderLine_ID(oline.getC_OrderLine_ID());
		shipmetLine.saveEx();
	}	
		
	/**
	 * get Document Type
	 * @param docBaseType
	 * @return int Document Type
	 */
	protected int getDocType(String docBaseType)
	{
		MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), docBaseType);

		if (doc == null || doc.length == 0) 
		{
			String reference = Msg.getMsg(getCtx(), "SequenceDocNotFound");
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			throw new AdempiereException(textMsg);
		} 
		else
		{
			log.info("Doc Type for "+docBaseType+": "+ doc[0].getC_DocType_ID());
			return doc[0].getC_DocType_ID();
		}
	}
	
	private MInOut getMInOut(MOrderLine oline)
	{

		MInOut inout = (MInOut) m_shipments.get(oline.getC_Order_ID());
		if(inout != null)
		{
			return inout;
		}	
		MOrder order = oline.getParent();
		inout = new MInOut(order,getDocType(MDocType.DOCBASETYPE_MaterialDelivery), getToday());
		m_shipments.put(order.getC_Order_ID(), inout);
		return inout;
	}
	
	/**
	 * getToday 
	 * @return Timestamp with today
	 */
	protected Timestamp getToday()
	{
		return this.Today;
	}
}

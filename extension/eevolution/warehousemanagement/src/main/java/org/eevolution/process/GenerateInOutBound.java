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
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.exceptions.DocTypeNotFoundException;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/**
 *	Generate Outbound Document based Sales Order Lines and the Smart Browser Filter  
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class GenerateInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	protected int p_M_Locator_ID = 0;
	protected String p_DocAction = null;
	protected Timestamp p_ShipDate = null;
	protected Timestamp p_PickDate = null;
	protected int p_C_DocType_ID = 0;
	protected String p_DeliveryRule = null;
	protected String p_POReference = null;
	
	
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
			else if (name.equals("ShipDate"))
			{
				p_ShipDate =  (Timestamp)para.getParameter();
			}
			else if (name.equals("PickDate"))
			{
				p_PickDate =  (Timestamp)para.getParameter();
			}
			else if (name.equals("POReference"))
			{
				p_POReference =  (String)para.getParameter();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID =  para.getParameterAsInt();
			}
			else if (name.equals("DeliveryRule"))
			{
				p_DeliveryRule =  (String)para.getParameter();
			}
			else if (name.equals("C_DocType_ID"))
			{
				p_C_DocType_ID = para.getParameterAsInt();
			}
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
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
		MLocator locator = MLocator.get(getCtx(), p_M_Locator_ID);
		MWMInOutBound outbound = new MWMInOutBound(getCtx(), 0 , get_TrxName());
		
		outbound.setShipDate(p_ShipDate);
		outbound.setPickDate(p_PickDate);
		if(p_POReference != null)
		{	
			outbound.setPOReference(p_POReference);
		}	
		
		if(p_DeliveryRule != null)
		{	
			outbound.setDeliveryRule(p_DeliveryRule);
		}	
		
		if(p_C_DocType_ID > 0)
		{	
			outbound.setC_DocType_ID(p_C_DocType_ID);
		}	
		else
		{	
			int C_DocType_ID=MDocType.getDocType(MDocType.DOCBASETYPE_WarehouseManagementOrder);
			if( C_DocType_ID <= 0)
			{
				throw new DocTypeNotFoundException(MDocType.DOCBASETYPE_WarehouseManagementOrder, "");
			}
			else
			{	
				outbound.setC_DocType_ID(C_DocType_ID);
			}
		}	
		
		if(p_DocAction != null)
			outbound.setDocAction(p_DocAction);
		else
			outbound.setDocAction(MWMInOutBound.ACTION_Prepare);
		
		outbound.setDocStatus(MWMInOutBound.DOCSTATUS_Drafted);
		outbound.setM_Warehouse_ID(locator.getM_Warehouse_ID());
		outbound.setIsSOTrx(true);
		outbound.saveEx();
		int seq = 10;
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=C_OrderLine.C_OrderLine_ID)";		
		Collection <MOrderLine> olines = new Query(getCtx(), I_C_OrderLine.Table_Name, whereClause, get_TrxName())
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()	})
										.list();
		for (MOrderLine oline : olines)
		{
				MWMInOutBoundLine boundline = new MWMInOutBoundLine(outbound);
				boundline.setLine(seq);
				boundline.setM_Product_ID(oline.getM_Product_ID());
				boundline.setM_AttributeSetInstance_ID(oline.getM_AttributeSetInstance_ID());
				boundline.setMovementQty(oline.getQtyOrdered().subtract(oline.getQtyDelivered()));
				boundline.setC_UOM_ID(oline.getC_UOM_ID());
				boundline.setDescription(oline.getDescription());
				boundline.setC_OrderLine_ID(oline.getC_OrderLine_ID());
				boundline.setPickDate(outbound.getPickDate());
				boundline.setShipDate(outbound.getShipDate());
				boundline.saveEx();
				seq ++;
		}
		return "@DocumentNo@ " + outbound.getDocumentNo();
	}
}

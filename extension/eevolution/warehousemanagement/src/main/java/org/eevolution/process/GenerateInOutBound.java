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

import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.model.X_T_Selection;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class GenerateInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	protected int p_M_Locator_ID = 0;
	protected String p_DocAction = null;
	protected int p_WM_Area_ID = 0;
	protected int p_C_DocType_ID = 0;
	
	
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
			else if (name.equals("WM_Area_ID"))
			{
				p_WM_Area_ID = para.getParameterAsInt();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para.getParameterAsInt();
			}
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
			}
			else if (name.equals("C_DocType_ID"))
			{
				p_C_DocType_ID = para.getParameterAsInt();
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
		MWMInOutBound bound = new MWMInOutBound(getCtx(), 0 , get_TrxName());
		if(p_C_DocType_ID > 0)
			bound.setC_DocType_ID(p_C_DocType_ID);
		else	
			bound.setC_DocType_ID(103);
		
		bound.setDocStatus(MWMInOutBound.DOCSTATUS_Drafted);
		if(p_DocAction != null)
			bound.setDocAction(p_DocAction);
		else
			bound.setDocAction(MWMInOutBound.ACTION_Complete);
		
		bound.setM_Warehouse_ID(locator.getM_Warehouse_ID());
		bound.setIsSOTrx(true);
		bound.setMovementType(MWMInOutBound.MOVEMENTTYPE_CustomerShipment);		
		bound.saveEx();
		int seq = 10;
		for (X_T_Selection s : getSelected())
		{
				MOrderLine line = new MOrderLine(getCtx(), s.getT_Selection_ID(), get_TrxName());
				MWMInOutBoundLine boundline = new MWMInOutBoundLine(bound);
				boundline.setLine(seq);
				boundline.setM_Product_ID(line.getM_Product_ID());
				boundline.setM_AttributeSetInstance_ID(line.getM_Warehouse_ID());
				boundline.setQtyEntered(line.getQtyOrdered().subtract(line.getQtyDelivered()));
				boundline.setC_UOM_ID(line.getC_UOM_ID());
				boundline.setDescription(line.getDescription());
				boundline.setC_OrderLine_ID(line.getC_OrderLine_ID());
				boundline.saveEx();
				seq ++;
		}
		return "@DocumentNo@ " + bound.getDocumentNo();
	}
	
	protected Collection<X_T_Selection> getSelected()
	{
		final String whereClause = MPInstance.COLUMNNAME_AD_PInstance_ID + "= ?";
		return new Query(getCtx(),X_T_Selection.Table_Name,whereClause, get_TrxName())
		.setParameters(new Object[]{this.getAD_PInstance_ID()})
		.list();
	}
}

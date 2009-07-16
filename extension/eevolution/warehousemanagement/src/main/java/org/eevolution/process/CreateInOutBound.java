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

import org.compiere.model.MColumn;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_T_Selection;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MInOutBound;
import org.eevolution.model.MInOutBoundLine;
import org.eevolution.model.MSmartBrowse;
import org.eevolution.model.MSmartBrowseField;
import org.eevolution.model.MView;
import org.eevolution.model.MViewColumn;
import org.eevolution.model.MViewJoin;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class CreateInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		
		p_Record_ID = getRecord_ID();
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para: parameters)
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
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
		MInOutBound bound = new MInOutBound(getCtx(), 0 , get_TrxName());
		bound.setC_DocType_ID(0);
		bound.setDocStatus(MInOutBound.DOCSTATUS_Drafted);
		bound.setDocAction(MInOutBound.ACTION_Complete);
		bound.saveEx();
		for (X_T_Selection s : getSelected())
		{
			MOrder order = new MOrder(getCtx(), s.get_ID() , get_TrxName());
			for(MOrderLine line: order.getLines())
			{	
				MInOutBoundLine boundline = new MInOutBoundLine(bound);
				boundline.setM_Product_ID(line.getM_Product_ID());
				boundline.setM_AttributeSetInstance_ID(line.getM_Warehouse_ID());
				boundline.setQtyEntered(line.getQtyOrdered().subtract(line.getQtyDelivered()));
				boundline.setC_UOM_ID(line.getC_UOM_ID());
				boundline.setDescription(line.getDescription());
				//boundline.setLine()
				boundline.setC_OrderLine_ID(line.getC_OrderLine_ID());
				boundline.saveEx();
			}	
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

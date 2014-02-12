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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.adempiere.engine;

import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_C_AcctSchema;


/**
 *	Cost Callout
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class CalloutCost extends CalloutEngine
{

	/**
	 *	Validate cost type with costing method.
	 *	@param ctx context
	 *	@param WindowNo window no
	 *	@param mTab tab
	 *	@param mField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String validateCostingMethod (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		
		 I_C_AcctSchema as = GridTabWrapper.create(mTab,  I_C_AcctSchema.class);
		 if(!as.getCostingMethod().equals(as.getM_CostType().getCostingMethod()))
			 mTab.fireDataStatusEEvent ("", "The Costing method defined in Cost Type not match with the Costing Method defined in Account Schema", false);
		 
		return "";		
	}	

}	//	Callout Cost

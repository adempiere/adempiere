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

package org.compiere.crm.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_Order;
import org.adempiere.core.domains.models.X_C_Opportunity;

public class MOpportunity extends X_C_Opportunity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9052544341602655427L;

	public MOpportunity(Properties ctx, int C_Opportunity_ID, String trxName) {
		super(ctx, C_Opportunity_ID, trxName);
	}

	public MOpportunity(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if ( getC_Order_ID() > 0 )
		{
			I_C_Order order = getC_Order();
			if ( order != null )
				setOpportunityAmt(order.getGrandTotal());
		}
		return true;
	}

}

/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.X_M_ProductionPlan;

/**
 * Contributed from Adaxa
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/648">
 * 		@see FR [ 648 ] Add Support to document Action on Standard Production window</a>		
 */
public class MProductionPlan extends X_M_ProductionPlan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7676274002801937127L;

	/**
	 * @param ctx
	 * @param M_ProductionPlan_ID
	 * @param trxName
	 */
	public MProductionPlan(Properties ctx, int M_ProductionPlan_ID,
			String trxName) {
		super(ctx, M_ProductionPlan_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MProductionPlan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}

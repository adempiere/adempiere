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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;


/**
 * Generated for use cache
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/566">
 * 		@see FR [ 566 ] Process parameter don't have a parameter like only information</a>
 *
 */
public class MValRule extends X_AD_Val_Rule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037593173381313147L;

	/**
	 * @param ctx
	 * @param AD_Val_Rule_ID
	 * @param trxName
	 */
	public MValRule(Properties ctx, int AD_Val_Rule_ID, String trxName) {
		super(ctx, AD_Val_Rule_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MValRule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**	Cache						*/
	private static CCache<Integer, MValRule>	s_cache	= new CCache<Integer, MValRule>(Table_Name, 20);
	
	/**
	 * Get Validatio Rule from Cache
	 * @param ctx
	 * @param valRuleId
	 * @return
	 */
	public static MValRule get (Properties ctx, int valRuleId) {
		MValRule retValue = s_cache.get(valRuleId);
		if (retValue != null)
			return retValue;
		retValue = new MValRule(ctx, valRuleId, null);
		if (retValue.get_ID () != 0)
			s_cache.put (valRuleId, retValue);
		return retValue;
	}	//	get
}

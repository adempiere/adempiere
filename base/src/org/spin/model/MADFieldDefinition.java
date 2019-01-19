/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.compiere.util.Env;

import org.compiere.model.Query;

/**
 * Class for handle status bar
 * @author Raúl Muñoz, rMunoz@erpya.com , http://www.erpya.com
 * @see https://github.com/adempiere/adempiere/issues/1697
 * 		<a href="https://github.com/adempiere/adempiere/issues/1697">
 */
public class MADFieldDefinition extends X_AD_FieldDefinition {

	
	

	public MADFieldDefinition(Properties ctx, int AD_FieldDefinition_ID,
			String trxName) {
		super(ctx, AD_FieldDefinition_ID, trxName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -680533880971968911L;

	public MADFieldDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public List<MADFieldCondition> getConditions() {
		List<MADFieldCondition> list = new Query(Env.getCtx(), I_AD_FieldCondition.Table_Name, I_AD_FieldCondition.COLUMNNAME_AD_FieldDefinition_ID+"=?", get_TrxName())
			.setParameters(getAD_FieldDefinition_ID())
			.setOrderBy(MADFieldCondition.COLUMNNAME_AD_FieldCondition_ID)
			.list();
		return list;
	}
	

	public List<String> getGroupCondition() {
		

		ArrayList<String> list = new ArrayList<String>();
		//  Display
//		List<MFieldCondition> conditions = getConditions();
//		for( MFieldCondition condition : conditions  ){ 
//			Evaluator.parseDepends(list, condition.getCondition());
//		}
		return list;
	}
}

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
import java.util.Properties;

/**
 * Class for handle status bar
 * @author Raúl Muñoz, rMunoz@erpya.com , http://www.erpya.com
 * @see https://github.com/adempiere/adempiere/issues/1697
 * 		<a href="https://github.com/adempiere/adempiere/issues/1697">
 */
public class MADFieldCondition extends X_AD_FieldCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9136486688037408382L;

	public MADFieldCondition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} 

	public MADFieldCondition(Properties ctx, int AD_FieldCondition_ID,
			String trxName) {
		super(ctx, AD_FieldCondition_ID, trxName);
	}
}

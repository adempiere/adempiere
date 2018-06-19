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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com               					  *
 *****************************************************************************/
 
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1769">
 * 		@see FR [ 1769 ] Add option to restore the password from the login</a>
 *
 */
public class MToken extends X_AD_Token {


	private static final long serialVersionUID = -1171525387615789574L;

	public MToken(Properties ctx, int AD_Token_ID, String trxName) {
		
		super(ctx, AD_Token_ID, trxName);
	}

	public MToken(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	

}

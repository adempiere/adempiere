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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Forecast Run Line
 * 
 * @author victor.perez@e-evolution.com www.e-evolution.com , e-Evolution
 * 
 */
public class MPPForecastRunLine extends X_PP_ForecastRunLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5784783929174123217L;

	public MPPForecastRunLine(Properties ctx, int M_ForecastRunLine_ID,
			String trxName) {
		super(ctx, M_ForecastRunLine_ID, trxName);
	}

	public MPPForecastRunLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}

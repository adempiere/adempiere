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
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for C_NonBusinessDay
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class MNonBusinessDay extends X_C_NonBusinessDay {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MNonBusinessDay(Properties ctx, int C_NonBusinessDay_ID,
			String trxName) {
		super(ctx, C_NonBusinessDay_ID, trxName);
	}
	
	public MNonBusinessDay(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}

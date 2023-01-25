/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_C_ProjectProcessorChange;
import org.eevolution.model.MProjectProcessorLog;

/**
 * Project Processor Change
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class MProjectProcessorChange extends X_C_ProjectProcessorChange{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MProjectProcessorChange(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	public MProjectProcessorChange(Properties ctx, int C_ProjectProcessorChange_ID, String trxName) {
		super(ctx, C_ProjectProcessorChange_ID, trxName);
	}
	
	public MProjectProcessorChange(MProjectProcessorLog log) {
		this(log.getCtx(), 0, log.get_TrxName());
		setC_ProjectProcessorLog_ID(log.getC_ProjectProcessorLog_ID());
	}

	
}

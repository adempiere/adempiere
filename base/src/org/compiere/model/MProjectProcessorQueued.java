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

import org.adempiere.core.domains.models.X_C_ProjectProcessorQueued;
import org.eevolution.model.MProjectProcessorLog;

/**
 * Project Processor Queued
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class MProjectProcessorQueued extends X_C_ProjectProcessorQueued {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MProjectProcessorQueued(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MProjectProcessorQueued(Properties ctx, int C_ProjectProcessorQueued_ID, String trxName) {
		super(ctx, C_ProjectProcessorQueued_ID, trxName);
	}
	
	public MProjectProcessorQueued(MProjectProcessorLog parent) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setC_ProjectProcessorLog_ID(parent.getC_ProjectProcessorLog_ID());
	}
	
	public MProjectProcessorQueued(MProjectProcessorLog parent, int AD_User_ID) {
		this(parent.getCtx(), MProjectProcessorQueued.getMProjectProcessorQueued(parent, AD_User_ID), parent.get_TrxName());
		if (getC_ProjectProcessorQueued_ID()==0) {
			setAD_User_ID(AD_User_ID);
			setC_ProjectProcessorLog_ID(parent.getC_ProjectProcessorLog_ID());
			setSendEMail(false);
		}
	}
	
	/**
	 * Get Queued from user
	 * @param parent
	 * @param AD_User_ID
	 * @return
	 */
	public static int getMProjectProcessorQueued(MProjectProcessorLog parent, int AD_User_ID) {
		MProjectProcessorQueued queued = new Query(parent.getCtx(), MProjectProcessorQueued.Table_Name, "C_ProjectProcessorLog_ID = ? AND AD_User_ID = ? ", parent.get_TrxName())
										.setParameters(parent.getC_ProjectProcessorLog_ID(),AD_User_ID)
										.first();
		
		if (queued!=null) 
			return queued.getC_ProjectProcessorQueued_ID();
		
		return 0;
	}
}

/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;


/**
 * @author Trifon Trifonov
 */
public class MIMPProcessorLog 
	extends X_IMP_ProcessorLog
	implements AdempiereProcessorLog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2314394818152867856L;

	/**
	 * 
	 * @param ctx
	 * @param EXP_ReplicationProcessorLog_ID
	 * @param trxName
	 */
	public MIMPProcessorLog(Properties ctx,	int IMP_ProcessorLog_ID, String trxName) {
		super(ctx, IMP_ProcessorLog_ID, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MIMPProcessorLog(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param parent
	 * @param summary
	 */
	public MIMPProcessorLog (MIMPProcessor parent, String summary)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setIMP_Processor_ID(parent.getIMP_Processor_ID());
		setSummary(summary);
	}
	
}

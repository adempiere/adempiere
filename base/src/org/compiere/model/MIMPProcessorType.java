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
 * Copyright (C) 2012-2020 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_IMP_Processor_Type;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.rpl.ITestImportProcessor;
import org.compiere.util.CLogger;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com 
 */
public class MIMPProcessorType extends X_IMP_Processor_Type {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987531346397814095L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MIMPProcessorType.class);
	
	
	public MIMPProcessorType(Properties ctx, int EXP_Processor_Type_ID, String trxName) {
		super(ctx, EXP_Processor_Type_ID, trxName);
	}
	
	public MIMPProcessorType(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}
	
	/**
	 * Get processor instance from current type
	 * @return
	 * @throws Exception
	 */
	public ITestImportProcessor getProcessorTestInstance() throws Exception {
		try {
			Class<?> clazz = Class.forName(getJavaClass());
			if(ITestImportProcessor.class.isAssignableFrom(clazz)) {
				return (ITestImportProcessor) clazz.newInstance();
			} else {
				throw new AdempiereException("@UnsupportedClass@");
			}
		} catch (Exception e) {
			s_log.severe(e.toString());
			throw e;
		}
	}
}

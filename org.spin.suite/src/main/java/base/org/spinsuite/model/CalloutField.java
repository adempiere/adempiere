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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class CalloutField extends CalloutEngine
{

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 16:47:49
	 */
	public CalloutField()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set Name and Description of Sync Column
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 16:51:31
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	public String column (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		//	Sync Column ID
		Integer p_MSFASyncColumn_ID = (Integer) value;
		
		//	Validate
		if(p_MSFASyncColumn_ID.intValue() != 0){
			//	Objects Sync Column
			MSPSColumn m_MSFASyncColumn = new MSPSColumn(ctx, p_MSFASyncColumn_ID, null);
			
			//	Set Values
			mTab.setValue("Name", m_MSFASyncColumn.getName());	
			mTab.setValue("Description", m_MSFASyncColumn.getDescription());
		}
		
		return "";
	}
	
}

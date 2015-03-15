/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                     		 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 of the GNU General Public License as published   		 *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2013 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com				  		 *
 *************************************************************************************/
package org.spinsuite.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MRefList;

/**
 * 
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *
 */
public class CalloutPlanningVisit extends CalloutEngine {

	/**
	 * Set Business Partner Name on Name of Planning Visit
	 * @author Yamel Senih 13/06/2012, 16:12:15
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	public String bPartner (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		
		Integer m_C_BPartner_ID = (Integer) value;
		if(m_C_BPartner_ID.intValue() != 0){
			MBPartner bPartner = new MBPartner(ctx, m_C_BPartner_ID, null);
			mTab.setValue("Name", bPartner.getName());	
		}
		return "";
	}
	
	/**
	 * Set Name Visit Planning
	 * @author Yamel Senih 22/07/2012, 21:15:56
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	public String nameCall (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		
		//	Declare object
		MBPartner m_BPartner = null;
		MBPartnerLocation m_BPartnerLocation = null;
		StringBuffer name = new StringBuffer();
		Timestamp m_ValidFrom = (Timestamp) mTab.getValue("ValidFrom");
		int dayOfWeek = 0;
		//	Set day in Name
		if(m_ValidFrom != null){
			//	Set class instance with current date
			Calendar m_CalW = Calendar.getInstance();
			//	Set Date from Millis
			m_CalW.setTimeInMillis(m_ValidFrom.getTime());
			//	Get day of Week
			dayOfWeek = m_CalW.get(Calendar.DAY_OF_WEEK) - 1;
			//	Get day of Week on Month
			int dayOfWeekMonth = m_CalW.get(Calendar.DAY_OF_WEEK_IN_MONTH);

			//	Set Visit Day
			mTab.setValue("VisitDay", String.valueOf(dayOfWeek));
			
			name.append((dayOfWeekMonth < 3? 1: 2));
			name.append(" | ");
		}
		
		//	Appeand Day Name
		String day = MRefList.getListName(ctx, 
				//	Big Wire
				167, String.valueOf(dayOfWeek));
		if(day != null && day.length() != 0){
			name.append(day);
			name.append(" | ");
		}
		
		Integer m_C_BPartner_ID = (Integer) mTab.getValue("C_BPartner_ID");
		
		if(m_C_BPartner_ID != null && m_C_BPartner_ID.intValue() != 0){
			m_BPartner = new MBPartner(ctx, m_C_BPartner_ID, null);
			name.append(m_BPartner.getName());
			name.append(" | ");
		}
		
		Integer m_C_BPartner_Location_ID = (Integer) mTab.getValue("C_BPartner_Location_ID");		

		if(m_C_BPartner_Location_ID != null && m_C_BPartner_Location_ID.intValue() != 0){
			m_BPartnerLocation = new MBPartnerLocation(ctx, m_C_BPartner_Location_ID, null);
			name.append(m_BPartnerLocation.getName());
		}
		
		if(name.length() != 0)
			mTab.setValue("Name", name.toString());
		
		return "";
	}
	
	/**
	 * Set Sales Region and Business Partner
	 * en base al ID de la direccion de entrega
	 * @author Yamel Senih 23/07/2012, 08:48:59
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	public String salesRegion (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		
		MBPartnerLocation m_BPartnerLocation = null;
		Integer m_C_BPartner_Location_ID = (Integer) mTab.getValue("C_BPartner_Location_ID");		

		if(m_C_BPartner_Location_ID != null && m_C_BPartner_Location_ID.intValue() != 0){
			m_BPartnerLocation = new MBPartnerLocation(ctx, m_C_BPartner_Location_ID, null);
			mTab.setValue("C_SalesRegion_ID", m_BPartnerLocation.getC_SalesRegion_ID());
			mTab.setValue("C_BPartner_ID", m_BPartnerLocation.getC_BPartner_ID());
		}
		
		return "";
	}
	
}

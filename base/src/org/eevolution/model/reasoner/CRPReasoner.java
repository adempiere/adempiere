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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model.reasoner;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.*;        

import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.MResourceUnAvailable;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.*;

import org.eevolution.tools.DateTimeUtil;

import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderNode;
import org.eevolution.model.MPPOrderWorkflow;


/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class CRPReasoner {
	
	/**
	 * All the below cases expect exactly two parameters: The (1) begin and the (2) end of a day 
	 */
	
	/**
	 * Case 1: The time dependent process has already begun and ends at this day.
	 */
	public static final String RESTRICTION_DAY_CASE_1 = 
		"(datestartschedule<=''{0}'' AND datefinishschedule>=''{0}'' AND datefinishschedule<=''{1}'')";
	
	/**
	 * Case 2: The time dependent process begins and ends at this day.
	 */
	public static final String RESTRICTION_DAY_CASE_2 = 
		"(datestartschedule>=''{0}'' AND datestartschedule<=''{1}'' AND datefinishschedule>=''{0}'' AND datefinishschedule<=''{1}'')";

	/**
	 * Case 3: The time dependent process begins at this day and ends few days later.
	 */
	public static final String RESTRICTION_DAY_CASE_3 = 
		"(datestartschedule>=''{0}'' AND datestartschedule<=''{1}'' AND datefinishschedule>=''{1}'')";
	
	/**
	 * Case 4: The time dependent process has already begun and ends few days later.
	 */
	public static final String RESTRICTION_DAY_CASE_4 = 
		"(datestartschedule<=''{0}'' AND datefinishschedule>=''{1}'')";
        
        private static CLogger	log	= CLogger.getCLogger (CRPReasoner.class);
	
	
	private String getDayRestriction(Timestamp dateTime, MResource r) {
		
		Object[] params = { getBorderDayMin(dateTime, r).toString(), getBorderDayMax(dateTime, r).toString() };
		
		return 
			MessageFormat.format(RESTRICTION_DAY_CASE_1, params)+
			" OR "+MessageFormat.format(RESTRICTION_DAY_CASE_2, params)+
			" OR "+MessageFormat.format(RESTRICTION_DAY_CASE_3, params)+
			" OR "+MessageFormat.format(RESTRICTION_DAY_CASE_4, params);
	}
	
    public MPPOrder[] getPPOrdersNotCompleted(MResource r) {

         //String sql = "SELECT owf.PP_Order_Workflow_ID , o.DateStartSchedule , o.DateFinishSchedule ,o.QtyOrdered - o.QtyDelivered - o.QtyScrap AS QtyOpen FROM PP_Order o INNER JOIN PP_Order_Workflow owf ON (owf.PP_ORDER_ID = o.PP_Order_ID) WHERE o.DocStatus <> 'CL' AND o.AD_Client_ID = ? AND o.S_Resource_ID= ? ORDER BY DatePromised" ;		
     	 String where = 
      		 // Checks the requested resource id directly on order node, not on resource id of the order
      		 //"PP_Order_ID IN (SELECT PP_Order_ID FROM PP_Order_Node on WHERE on.S_Resource_ID="+r.getID()+")"
                 "S_Resource_ID="+r.get_ID() +" AND DocStatus <> 'CL' AND AD_Client_ID = " + r.getAD_Client_ID() ; //+ " AND PP_Order_ID = 1000031" ;
      		 // ... and completed orders needn't to be observed      		
      	 
      	 int[] orderIds = PO.getAllIDs("PP_Order", where, null);
      	 MPPOrder[] orders = new MPPOrder[orderIds.length];
      	 for(int i = 0; i < orderIds.length; i++) {
      		 
      		 orders[i] = new MPPOrder(Env.getCtx(), orderIds[i], null);
      	 }
      	 
      	 return orders;
    }
    
    public Timestamp getBorderDayMin(Timestamp dateTime, MResource r) {
    	
    	MResourceType t = new MResourceType(Env.getCtx(), r.getS_ResourceType_ID(), null);
    	Timestamp dMin = null;
    	return (t.isTimeSlot()) ? 
    			DateTimeUtil.getDayBorder(dateTime, t.getTimeSlotStart(), false) :
    			DateTimeUtil.getDayBorder(dateTime, null, false);
    }

    public Timestamp getBorderDayMax(Timestamp dateTime, MResource r) {
    	
    	MResourceType t = new MResourceType(Env.getCtx(), r.getS_ResourceType_ID(), null);
    	Timestamp dMin = null;
    	return (t.isTimeSlot()) ? 
    			DateTimeUtil.getDayBorder(dateTime, t.getTimeSlotEnd(), true) :
    			DateTimeUtil.getDayBorder(dateTime, null, true);
    }
    
    public boolean isResourceAvailable(Timestamp dateTime, MResource r) {
    	
    	MResourceType t = new MResourceType(Env.getCtx(), r.getS_ResourceType_ID(), null);

    	return ( checkResourceAvailability(dateTime, r) && checkResourceTypeAvailability(dateTime, t) );
    }
    
    public MPPOrder[] getPPOrders(Timestamp dateTime, MResource r) {

    	if(!isResourceAvailable(dateTime, r)) {
    		
    		return new MPPOrder[0];
    	}
    	
   	 	String where = 
          		 // Checks the requested resource id directly on order node, not on resource id of the order
   	 			 "PP_order_id in (select PP_order_id from PP_order_node where s_resource_id="+r.get_ID()
          		 // ... and only the orders running on given day
          		 +" AND ("+getDayRestriction(dateTime, r)+") ) AND AD_Client_ID =" + r.getAD_Client_ID();
          	 
       	 int[] orderIds = PO.getAllIDs("PP_Order", where, null);
      	 MPPOrder[] orders = new MPPOrder[orderIds.length];
      	 for(int i = 0; i < orderIds.length; i++) {
      		 
      		 orders[i] = new MPPOrder(Env.getCtx(), orderIds[i], null);
      	 }
          	 
       	 return orders;
    }  
    
    public MPPOrderNode[] getPPOrderNodes(Timestamp dateTime, MResource r) {

    	if(!isResourceAvailable(dateTime, r)) {
    		
    		return new MPPOrderNode[0];
    	}

    	String where = 
     		 "s_resource_id = "+r.get_ID()
     		 +" AND ("+getDayRestriction(dateTime, r)+") AND AD_Client_ID = " + r.getAD_Client_ID();
    	 log.log(Level.FINE,"getPPOrderNodes --> Where:" + where);
    	 int[] ids = PO.getAllIDs("PP_Order_Node", where, null);
    	 
    	 MPPOrderNode[] nodes = new MPPOrderNode[ids.length];
    	 for(int i = 0; i < ids.length; i++) {
    		 
    		 nodes[i] = new MPPOrderNode(Env.getCtx(), ids[i], null);
    	 }
    	 
    	 return nodes;
    }  

    public MPPOrderWorkflow getPPOrderWorkflow(MPPOrder o) {

     	 int[] ids = PO.getAllIDs("PP_Order_Workflow", "PP_Order_ID = "+o.get_ID() + " AND AD_Client_ID = " + o.getAD_Client_ID(), null);

      	 return (ids.length != 1) ? null : new MPPOrderWorkflow(Env.getCtx(), ids[0], null);
   }
   
    public boolean checkResourceTypeAvailability(MResourceType t) {
	
		if(!t.isDateSlot()) {
			
			return true;
		}

		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		for(int i = 0; i < 7; i++) {
			
			if(checkResourceTypeAvailability(dateTime, t)) {
				
				return true;
			}
			
			//dateTime = DateTimeUtil.incrementDay(dateTime);
                        dateTime = org.compiere.util.TimeUtil.addDays(dateTime, 1);
                        
		}
		
		return false;
	}
	
    public boolean checkResourceAvailability(Timestamp dateTime, MResource r) {
	
		int[] ids = PO.getAllIDs("S_ResourceUnAvailable", "S_Resource_ID = "+r.get_ID() + " AND AD_Client_ID = " + r.getAD_Client_ID(), null);

		Timestamp dateFrom = null;
		Timestamp dateTo = null;
		Timestamp dateActual = null;
		
		MResourceUnAvailable rua = null;
		for(int i = 0; i < ids.length; i++) {
	
			rua = new MResourceUnAvailable(Env.getCtx(), ids[i], null);
			
			dateFrom = DateTimeUtil.getDayBorder(rua.getDateFrom(), null, false);
			dateTo = DateTimeUtil.getDayBorder(rua.getDateTo(), null, true);
			dateActual = DateTimeUtil.getDayBorder(dateTime, null, false);
			
			if(dateFrom.compareTo(dateActual) <= 0 && dateTo.compareTo(dateActual) >= 0 ) {
				
				return false;
			}
		}
		
		return true;
	}
	
    public boolean checkResourceTypeAvailability(Timestamp dateTime, MResourceType t) {
		
		if(!t.isDateSlot()) {
			
			return true;
		}
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(dateTime.getTime());
	
		boolean retValue = false;
	 	switch(gc.get(Calendar.DAY_OF_WEEK)) {
				
	 		case Calendar.SUNDAY:
	 			retValue = t.isOnSunday();
	 			break;
	 			
			case Calendar.MONDAY:
				retValue = t.isOnMonday();
				break;
			
			case Calendar.TUESDAY:
				retValue = t.isOnTuesday();
				break;
			
			case Calendar.WEDNESDAY:
				retValue = t.isOnWednesday();
				break;
			
			case Calendar.THURSDAY:
				retValue = t.isOnThursday();
				break;
			
			case Calendar.FRIDAY:
				retValue = t.isOnFriday();
				break;
			
			case Calendar.SATURDAY:
				retValue = t.isOnSaturday();	
				break;
			} 
	 	
	 	return retValue;
	}
}

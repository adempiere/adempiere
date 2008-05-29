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

package org.eevolution.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderNode;
import org.eevolution.model.MPPOrderWorkflow;

import org.compiere.model.*;
import org.eevolution.model.reasoner.CRPReasoner;

import org.eevolution.tools.DateTimeUtil;

import java.math.BigDecimal;
import java.util.logging.*;
import java.sql.Timestamp;

/**
 * Capacity Requirement Planning
 * 
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany (Original by Victor Perez, e-Evolution, S.C.)
 * @version 1.0, October 14th 2005
 */
public class CRP extends SvrProcess {

	public static final String FORWARD_SCHEDULING = "F";
	public static final String BACKWARD_SCHEDULING = "B";
	
	private int p_S_Resource_ID;        
	private String p_ScheduleType;        
	private CRPReasoner reasoner;
	
 	public CRP() {
 		
 		super();
 		reasoner = new CRPReasoner();
 	}
       
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();

		if(para == null) {
			
			return;
		}
		
		for (int i = 0; i < para.length; i++) {
			
			if(para[i] == null) {
				
				continue;
			}

			String name = para[i].getParameterName();
			if (name.equals("S_Resource_ID")) {
            	
            	p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();
                    
            }
			else if (name.equals("ScheduleType")) {
				
				p_ScheduleType = ((String)para[i].getParameter());				 		
			}
            else {
		
            	log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
            }
		}
	}
       
     protected String doIt() throws Exception  {
  
         return runCRP();
     } 
    
     private String runCRP() {
        
 	MPPOrderWorkflow owf = null;
	MPPOrderNode node = null;
    	MResource resource = null;
        MResourceType resourceType = null;

        BigDecimal qtyOpen  = null;
    	
        Timestamp date = null; 
    	Timestamp dateStart = null;
    	Timestamp dateFinish = null;

	long nodeMillis = 0;
    	int nodeId = -1;

	resource = new MResource(Env.getCtx(), p_S_Resource_ID, null);        
    	MPPOrder[] orders = reasoner.getPPOrdersNotCompleted(resource);
        log.log(Level.INFO,"MPP_Order[] : " + orders.length);
        for(int i = 0; i < orders.length; i++) {
            	
        	qtyOpen = orders[i].getQtyOrdered().subtract(orders[i].getQtyDelivered()).subtract(orders[i].getQtyScrap());
        	owf = reasoner.getPPOrderWorkflow(orders[i]);
			if(owf == null) {
				
				return Msg.translate(Env.getCtx(), "Error");
			}
        	
        	// Schedule Fordward
        	if (p_ScheduleType.equals(FORWARD_SCHEDULING)) {
        		log.log(Level.FINE,"MPP_Order DocumentNo:" + orders[i].getDocumentNo());
                        log.log(Level.FINE,"MPP_Order Workflow:" + owf.getName());
        		date = orders[i].getDateStartSchedule(); 
        		nodeId = owf.getPP_Order_Node_ID();
        		while(nodeId != 0) {
        			
        			node = new MPPOrderNode(getCtx(),nodeId , get_TrxName());
                                log.log(Level.FINE,"MPP_Order Node:" + node.getName() + " Description:" + node.getDescription());
        			resource = new MResource(Env.getCtx(), node.getS_Resource_ID(), null);
        			resourceType = new MResourceType(Env.getCtx(), resource.getS_ResourceType_ID(), null);
        			
    				// Checks, whether the resource type is principal available on one day a week.
        			// If not, process breaks with a Info message about.
        			if(!reasoner.checkResourceTypeAvailability(resourceType)) {
        				
        				return Msg.getMsg(Env.getCtx(), "ResourceNotInSlotDay");
        			}
        			
        			nodeMillis = calculateMillisFor(node, resourceType, qtyOpen, owf.getDurationBaseSec());
        			dateFinish = scheduleForward(date, nodeMillis ,resource, resourceType);

        			node.setDateStartSchedule(date);
        			node.setDateFinishSchedule(dateFinish);	
        			node.save(get_TrxName());
				date = node.getDateFinishSchedule();
				nodeId = owf.getNext(nodeId,getAD_Client_ID());
                                 if (nodeId == 0)
                                     log.log(Level.FINE,"---------------MPP_Order Node Next not exist:" );
        		}
                if (node!=null)          
                orders[i].setDateFinishSchedule(node.getDateFinishSchedule());
        	}
        	// Schedule backward
        	else if (p_ScheduleType.equals(BACKWARD_SCHEDULING)) {
                    
                        log.log(Level.FINE,"MPP_Order DocumentNo:" + orders[i].getDocumentNo());
                        log.log(Level.FINE,"MPP_Order Workflow:" + owf.getName());
        		date = orders[i].getDateFinishSchedule(); 
        		nodeId = owf.getLast(0, getAD_Client_ID());
        		
    			while(nodeId != 0) {
                                
        			node = new MPPOrderNode(getCtx(),nodeId , get_TrxName());
                                log.log(Level.FINE,"MPP_Order Node:" + node.getName() + " Description:" + node.getDescription());
        			resource = new MResource(Env.getCtx(), node.getS_Resource_ID(), null);
        			resourceType = new MResourceType(Env.getCtx(), resource.getS_ResourceType_ID(), null);

        			// Checks, whether the resource type is principal available on one day a week.
        			// If not, process breaks with a Info message about.
        			if(!reasoner.checkResourceTypeAvailability(resourceType)) {
        				
        				return Msg.getMsg(Env.getCtx(), "ResourceNotInSlotDay");
        			}

        			nodeMillis = calculateMillisFor(node, resourceType, qtyOpen, owf.getDurationBaseSec());
        			dateStart = scheduleBackward(date, nodeMillis ,resource, resourceType);

					node.setDateStartSchedule(dateStart);
        			node.setDateFinishSchedule(date);
				node.save(get_TrxName());
        			
				date = node.getDateStartSchedule();
        			nodeId = owf.getPrevious(nodeId,getAD_Client_ID());
                                if (nodeId == 0)
                                     log.log(Level.FINE,"MPP_Order Node Previos not exist:" );
                                    
        		}
                  if (node != null)      
                  orders[i].setDateStartSchedule(node.getDateStartSchedule()) ;                      
        	}

            orders[i].save(get_TrxName());
        }
        
     	return "OK";
     }
     
     private long calculateMillisFor(MPPOrderNode node, MResourceType type, BigDecimal qty, long commonBase) {
    	 
	 		// A day of 24 hours in milliseconds
			double aDay24 = 24*60*60*1000;

			// Initializing available time as complete day in milliseconds.
			double actualDay = aDay24;
			
			// If resource type is timeslot, updating to available time of the resource. 
			if (type.isTimeSlot()) {
				
				actualDay = (double)DateTimeUtil.getTimeDifference(type.getTimeSlotStart(), type.getTimeSlotEnd());	
			}
			
			// Available time factor of the resource of the workflow node
			BigDecimal factorAvailablility = new BigDecimal((actualDay / aDay24));  

			// Total duration of workflow node (seconds) ...
			// ... its static single parts ...
			BigDecimal totalDuration = new BigDecimal(
					
					//node.getQueuingTime() 
					node.getSetupTimeRequiered() // Use the present required setup time to notice later changes  
					+ node.getMovingTime() 
					+ node.getWaitingTime()
			);
			// ... and its qty dependend working time ... (Use the present required duration time to notice later changes)
			//totalDuration = totalDuration.add(qty.multiply(new BigDecimal(node.getDurationRequiered()))); 
                        totalDuration = totalDuration.add(qty.multiply(new BigDecimal(node.getDuration()))); 
			// ... converted to common base.
			totalDuration = totalDuration.multiply(new BigDecimal(commonBase));

			// Returns the total duration of a node in milliseconds.
			return totalDuration.multiply(new BigDecimal(1000)).longValue(); 
     }

     private Timestamp scheduleForward(Timestamp start, long nodeDuration, MResource r, MResourceType t)	{

 		// Checks, whether the resource is available at this day and recall with 
 		// next day, if not.
 		if(!reasoner.checkResourceAvailability(start, r)) {
 			
			//return scheduleForward(Util.incrementDay(start), nodeDuration, r, t);
                        return scheduleForward(org.compiere.util.TimeUtil.addDays(start, 1) , nodeDuration, r, t);
 			
 		}
 		// Checks, whether the resource type (only if date slot) is available at
 		// this day and recall with next day, if not.
 		else if(t.isDateSlot()) {
			  
			if(!reasoner.checkResourceTypeAvailability(start, t)) {
				
				//return scheduleForward(DateTimeUtil.incrementDay(start), nodeDuration, r, t);
                                return scheduleForward(org.compiere.util.TimeUtil.addDays(start, 1), nodeDuration, r, t);
			}
		}

		Timestamp dayStart = null;
 		// Retrieve the principal days start time, dependent on timeslot or not
 		if(t.isTimeSlot()) {
 			
 			dayStart = DateTimeUtil.getDayBorder(start, t.getTimeSlotStart(), false);
 		}
 		else {
 			
 			dayStart = DateTimeUtil.getDayBorder(start, null, false);
 		}

 		Timestamp dayEnd = null;
 		// Retrieve the days end time, dependent on timeslot or not
 		if(t.isTimeSlot()) {
 			
 			dayEnd = DateTimeUtil.getDayBorder(start, t.getTimeSlotEnd(), true);
 		}
 		else {
 			
 			dayEnd = DateTimeUtil.getDayBorder(start, null, true);
 		}

 		// If working has already begon at this day and the value is in the range of the 
 		// resource's availability, switch start time to the given again
		if(start.after(dayStart) && start.before(dayEnd)) {

 			dayStart = start;
 		}
 		
 		// The available time at this day in milliseconds
 		long availableDayDuration = DateTimeUtil.getTimeDifference(dayStart, dayEnd);
 		
 		Timestamp retValue = null;
 		// The work can be finish on this day.
 		if(availableDayDuration >= nodeDuration) {
 			
 			retValue = new Timestamp(dayStart.getTime()+nodeDuration);
 		}
 		// Otherwise recall with next day and the remained node duration.
 		else {
 			
			//retValue = scheduleForward(DateTimeUtil.incrementDay(DateTimeUtil.getDayBorder(start, null, false)), nodeDuration-availableDayDuration, r, t);
                      retValue = scheduleForward(org.compiere.util.TimeUtil.addDays(DateTimeUtil.getDayBorder(start, null, false),1), nodeDuration-availableDayDuration, r, t);
 		}
 		
 		return retValue;
 	}  	
 	
 	private Timestamp scheduleBackward(Timestamp end, long nodeDuration, MResource r, MResourceType t)	{
                
                log.log(Level.FINE,"scheduleBackward --> end " +end);
                log.log(Level.FINE,"scheduleBackward --> nodeDuration " +nodeDuration);
                log.log(Level.FINE,"scheduleBackward --> ResourceType " + t);

 		// Checks, whether the resource is available at this day and recall with 
 		// next day, if not.
 		if(!reasoner.checkResourceAvailability(end, r)) {
 			
			//return scheduleBackward(DateTimeUtil.decrementDay(end), nodeDuration, r, t);
                        return scheduleBackward(org.compiere.util.TimeUtil.addDays(end , -1), nodeDuration, r, t);
 			
 		}

 		// Checks, whether the resource type (only if date slot) is available on 
 		// this day and recall with next day, if not.
		if(t.isDateSlot()) {
			  
			if(!reasoner.checkResourceTypeAvailability(end, t)) {
				
				//return scheduleBackward(DateTimeUtil.decrementDay(end), nodeDuration, r, t);
                                return scheduleBackward(org.compiere.util.TimeUtil.addDays(end , -1), nodeDuration, r, t);
			}
		}

		Timestamp dayEnd = null;
 		// Retrieve the principal days end time, dependent on timeslot or not
 		if(t.isTimeSlot()) {
 			
 			dayEnd = DateTimeUtil.getDayBorder(end, t.getTimeSlotEnd(), true);
 		}
 		else {
 			
 			dayEnd = DateTimeUtil.getDayBorder(end, null, true);
 		}

                log.log(Level.FINE,"scheduleBackward --> dayEnd " + dayEnd);
                 
 		Timestamp dayStart = null;
 		// Retrieve the start end time, dependent on timeslot or not
 		if(t.isTimeSlot()) {
 			
 			dayStart = DateTimeUtil.getDayBorder(end, t.getTimeSlotStart(), false);
 		}
 		else {
 			
 			dayStart = DateTimeUtil.getDayBorder(end, null, false);
 		}

                log.log(Level.FINE,"scheduleBackward --> dayStart " + dayStart);
                
 		// If working has already begon at this day and the value is in the range of the 
 		// resource's availability, switch end time to the given again
 		if(end.before(dayEnd) && end.after(dayStart)) {

 			dayEnd = end;
 		}
 		
                
                 
 		// The available time at this day in milliseconds
 		long availableDayDuration = DateTimeUtil.getTimeDifference(dayStart, dayEnd);
                
                log.log(Level.FINE,"scheduleBackward --> availableDayDuration  " + availableDayDuration );
                
 		Timestamp retValue = null;
 		// The work can be finish on this day.
 		if(availableDayDuration >= nodeDuration) {
 			log.log(Level.FINE,"scheduleBackward --> availableDayDuration >= nodeDuration true " + availableDayDuration + "|" + nodeDuration );
 			retValue = new Timestamp(dayEnd.getTime()-nodeDuration);
 		}
 		// Otherwise recall with previous day and the remained node duration.
 		else {
 			
			//retValue = scheduleBackward(DateTimeUtil.getDayBorder(end, null, true)), nodeDuration-availableDayDuration, r, t);
                        log.log(Level.FINE,"scheduleBackward --> availableDayDuration >= nodeDuration false " + availableDayDuration + "|" + nodeDuration );
                        log.log(Level.FINE,"scheduleBackward --> nodeDuration-availableDayDuration " + (nodeDuration-availableDayDuration) );
                        retValue = scheduleBackward(org.compiere.util.TimeUtil.addDays(DateTimeUtil.getDayBorder(end, null, true), -1), nodeDuration-availableDayDuration, r, t);
 		}
 		
                log.log(Level.FINE,"scheduleBackward -->  retValue  " +  retValue);
 		return retValue;
 	}  	
}
  

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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.POResultSet;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderNode;
import org.eevolution.model.MPPOrderWorkflow;
import org.eevolution.model.reasoner.CRPReasoner;

/**
 * Capacity Requirement Planning
 * 
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany (Original by Victor Perez, e-Evolution, S.C.)
 * @version 1.0, October 14th 2005
 * 
 * @author Teo Sarca, www.arhipac.ro
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

	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;			
			if (name.equals("S_Resource_ID")) {
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else if (name.equals("ScheduleType")) {
				p_ScheduleType = ((String)para[i].getParameter());				 		
			}
			else {
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
			}
		}
	}

	protected String doIt() throws Exception
	{
		return runCRP();
	} 

	private String runCRP()
	{
		POResultSet<MPPOrder> rs = reasoner.getPPOrdersNotCompletedQuery(p_S_Resource_ID, get_TrxName())
											.scroll();		
		try
		{
			while(rs.hasNext())
			{
				runCRP(rs.next());
			}
		}
		finally
		{
			DB.close(rs);
			rs = null;
		}

		return "OK";
	}
	
	public void runCRP(MPPOrder order)
	{
		log.info("PP_Order DocumentNo:" + order.getDocumentNo());
		BigDecimal qtyOpen = order.getQtyOpen();
		MPPOrderWorkflow owf = order.getMPPOrderWorkflow();
		log.info("PP_Order Workflow:" + owf.getName());

		// Schedule Fordward
		if (p_ScheduleType.equals(FORWARD_SCHEDULING))
		{
			Timestamp date = order.getDateStartSchedule(); 
			int nodeId = owf.getPP_Order_Node_ID();
			MPPOrderNode node = null;

			while(nodeId != 0)
			{
				node = owf.getNode(nodeId);
				log.info("PP_Order Node:" + node.getName() != null ? node.getName() : ""  + " Description:" + node.getDescription() != null ? node.getDescription() : "");
				MResource resource = MResource.get(getCtx(), node.getS_Resource_ID());
				
				// Skip this node if there is no resource
				if(resource == null)
				{						
					nodeId = owf.getNext(nodeId, getAD_Client_ID());
					continue;
				}
				
				if(!reasoner.isAvailable(resource))
				{
					throw new AdempiereException("@ResourceNotInSlotDay@");
				}

				MResourceType resourceType = resource.getResourceType();
				long nodeMillis = calculateMillisFor(node, resourceType, qtyOpen, owf.getDurationBaseSec());
				Timestamp dateFinish = scheduleForward(date, nodeMillis ,resource);

				node.setDateStartSchedule(date);
				node.setDateFinishSchedule(dateFinish);	
				node.saveEx(get_TrxName());

				date = node.getDateFinishSchedule();
				nodeId = owf.getNext(nodeId, getAD_Client_ID());
			}
			// Update order finish date
			if (node != null && node.getDateFinishSchedule()!= null)
			{
				order.setDateFinishSchedule(node.getDateFinishSchedule());
			}
		}
		// Schedule backward
		else if (p_ScheduleType.equals(BACKWARD_SCHEDULING))
		{
			Timestamp date = order.getDateFinishSchedule();
			int nodeId = owf.getNodeLastID(getAD_Client_ID());
			MPPOrderNode node = null;

			while(nodeId != 0)
			{
				node = owf.getNode(nodeId);
				log.info("PP_Order Node:" + node.getName() != null ? node.getName() : ""  + " Description:" + node.getDescription() != null ? node.getDescription() : "");
				MResource resource = MResource.get(getCtx(), node.getS_Resource_ID());
				
				// Skip this node if there is no resource
				if(resource == null)
				{						
					nodeId = owf.getPrevious(nodeId, getAD_Client_ID());
					continue;
				}
				
				if(!reasoner.isAvailable(resource))
				{
					throw new AdempiereException("@ResourceNotInSlotDay@");
				}

				MResourceType resourceType = resource.getResourceType();
				long nodeMillis = calculateMillisFor(node, resourceType, qtyOpen, owf.getDurationBaseSec());
				Timestamp dateStart = scheduleBackward(date, nodeMillis ,resource);

				node.setDateStartSchedule(dateStart);
				node.setDateFinishSchedule(date);
				node.saveEx();

				date = node.getDateStartSchedule();
				nodeId = owf.getPrevious(nodeId, getAD_Client_ID());
			}
			// Update order start date
			if (node != null && node.getDateStartSchedule() != null)
			{
				order.setDateStartSchedule(node.getDateStartSchedule()) ;
			}
		}
		else
		{
			throw new AdempiereException("Unknown scheduling method - "+p_ScheduleType);
		}

		order.saveEx(get_TrxName());
	}

	/**
	 * Calculate how many millis take to complete given qty on given node(operation).
	 * @param node operation
	 * @param type resource type
	 * @param resource resource involved in that operation
	 * @param qty required quantity 
	 * @param commonBase 
	 * @return duration in millis
	 */
	private long calculateMillisFor(MPPOrderNode node, MResourceType type, BigDecimal qty, long commonBase)
	{
//		// Available time factor of the resource of the workflow node
//		double actualDay = type.getDayDurationMillis();
//		final double aDay24 = 24*60*60*1000; // A day of 24 hours in milliseconds
//		BigDecimal factorAvailablility = new BigDecimal((actualDay / aDay24));  

		// Total duration of workflow node (seconds) ...
		// ... its static single parts ...
		long totalDuration =
				+ node.getQueuingTime() 
				+ node.getSetupTimeRequiered() // Use the present required setup time to notice later changes  
				+ node.getMovingTime() 
				+ node.getWaitingTime()
		;
		// ... and its qty dependend working time ... (Use the present required duration time to notice later changes)
		totalDuration += qty.doubleValue() * node.getDuration(); 

		// Returns the total duration of a node in milliseconds.
		return (long)(totalDuration * commonBase * 1000);
	}

	private Timestamp scheduleForward(Timestamp start, long nodeDurationMillis, MResource r)
	{
		MResourceType t = r.getResourceType();
		Timestamp end = null;
		int iteration = 0; // statistical interation count
		do
		{
			start = reasoner.getAvailableDate(r, start, false);
			Timestamp dayStart = t.getDayStart(start);
			Timestamp dayEnd = t.getDayEnd(start);
			// If working has already began at this day and the value is in the range of the 
			// resource's availability, switch start time to the given again
			if(start.after(dayStart) && start.before(dayEnd))
			{
				dayStart = start;
			}
	
			// The available time at this day in milliseconds
			long availableDayDuration = dayEnd.getTime() - dayStart.getTime();
			if (availableDayDuration < 0)
			{
				throw new AdempiereException("@TimeSlotStart@ > @TimeSlotEnd@ ("+dayEnd+" > "+dayStart+")");
			}
	
			// The work can be finish on this day.
			if(availableDayDuration >= nodeDurationMillis)
			{
				end = new Timestamp(dayStart.getTime() + nodeDurationMillis);
				nodeDurationMillis = 0;
				break;
			}
			// Otherwise recall with next day and the remained node duration.
			else
			{
				start = TimeUtil.addDays(TimeUtil.getDayBorder(start, null, false), 1);
				nodeDurationMillis -= availableDayDuration;
			}
			
			iteration++;
		} while (nodeDurationMillis > 0);

		return end;
	}  	

	/**
	 * Calculate start date having duration and resource
	 * @param end end date
	 * @param nodeDurationMillis duration [millis]
	 * @param r resource
	 * @return start date
	 */
	private Timestamp scheduleBackward(Timestamp end, long nodeDurationMillis, MResource r)
	{
		MResourceType t = r.getResourceType();
		log.info("--> ResourceType " + t);
		Timestamp start = null;
		int iteration = 0; // statistical iteration count
		do
		{
			log.info("--> end=" + end);
			log.info("--> nodeDuration=" + nodeDurationMillis);
	
			end = reasoner.getAvailableDate(r, end, true);
			log.info("--> end(available)=" + end);
			Timestamp dayEnd = t.getDayEnd(end);
			Timestamp dayStart = t.getDayStart(end);
			log.info("--> dayStart=" + dayStart + ", dayEnd=" + dayEnd);
			
			// If working has already began at this day and the value is in the range of the 
			// resource's availability, switch end time to the given again
			if(end.before(dayEnd) && end.after(dayStart))
			{
				dayEnd = end;
			}
	
			// The available time at this day in milliseconds
			long availableDayDuration = dayEnd.getTime() - dayStart.getTime();
			log.info("--> availableDayDuration  " + availableDayDuration);
			if (availableDayDuration < 0)
			{
				throw new AdempiereException("@TimeSlotStart@ > @TimeSlotEnd@ ("+dayEnd+" > "+dayStart+")");
			}
	
			// The work can be finish on this day.
			if(availableDayDuration >= nodeDurationMillis)
			{
				log.info("--> availableDayDuration >= nodeDuration true " + availableDayDuration + "|" + nodeDurationMillis );
				start = new Timestamp(dayEnd.getTime() - nodeDurationMillis);
				nodeDurationMillis = 0;
				break;
			}
			// Otherwise recall with previous day and the remained node duration.
			else
			{
				log.info("--> availableDayDuration >= nodeDuration false " + availableDayDuration + "|" + nodeDurationMillis );
				log.info("--> nodeDuration-availableDayDuration " + (nodeDurationMillis-availableDayDuration) );
				
				end = TimeUtil.addDays(TimeUtil.getDayBorder(end, null, true), -1);
				nodeDurationMillis -= availableDayDuration;
			}
			//
			iteration++;
		}
		while(nodeDurationMillis > 0);
	
		log.info("         -->  start=" +  start + " <---------------------------------------- ");
		return start;
	}  
	
	
}


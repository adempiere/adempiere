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

package org.eevolution.model.reasoner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_S_Resource;
import org.compiere.model.MResourceType;
import org.compiere.model.MResourceUnAvailable;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderNode;


/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 * 
 * @author Teo Sarca, http://www.arhipac.ro
 * @author Cristi Pup, http://www.arhipac.ro
 * 			<li>BF [ 2854937 ] CRP calculate wrong DateFinishSchedule 
 * 				https://sourceforge.net/tracker/?func=detail&atid=934929&aid=2854937&group_id=176962
 */
public class CRPReasoner
{
	public Properties getCtx()
	{
		return getCtx(null);
	}
	private Properties getCtx(Object o)
	{
		if (o instanceof PO)
		{
			return ((PO)o).getCtx();
		}
		else
		{
			return Env.getCtx();
		}
	}

	private String getSQLDayRestriction(Timestamp dateTime, I_S_Resource r, List<Object> params)
	{
		final MResourceType rt = MResourceType.get(getCtx(), r.getS_ResourceType_ID());
		Timestamp dayStart = rt.getDayStart(dateTime);
		Timestamp dayEnd = rt.getDayEnd(dateTime);
		
		String whereClause;
		
		//
		// Case 1: The time dependent process has already begun and ends at this day.
		whereClause = "(DateStartSchedule<=? AND DateFinishSchedule>=? AND DateFinishSchedule<=?)";
		params.add(dayStart);
		params.add(dayStart);
		params.add(dayEnd);
		
		//
		// Case 2: The time dependent process begins and ends at this day.
		whereClause += " OR (DateStartSchedule>=? AND DateStartSchedule<=?"
							+" AND DateFinishSchedule>=? AND DateFinishSchedule<=?)";
		params.add(dayStart);
		params.add(dayEnd);
		params.add(dayStart);
		params.add(dayEnd);

		//
		// Case 3: The time dependent process begins at this day and ends few days later.
		whereClause += " OR (DateStartSchedule>=? AND DateStartSchedule<=? AND DateFinishSchedule>=?)";
		params.add(dayStart);
		params.add(dayEnd);
		params.add(dayEnd);

		//
		// Case 4: The time dependent process has already begun and ends few days later.
		whereClause += " OR (DateStartSchedule<=? AND DateFinishSchedule>=?)";
		params.add(dayStart);
		params.add(dayEnd);

		return "("+whereClause+")";
	}

	public Query getPPOrdersNotCompletedQuery(int S_Resource_ID, String trxName)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		
		StringBuffer whereClause = new StringBuffer();
		
		// For current AD_Client_ID (security)
		whereClause.append("AD_Client_ID=?");
		params.add(Env.getAD_Client_ID(getCtx()));
		
		// Skip voided, reversed and closed orders:
		whereClause.append(" AND ").append(MPPOrder.COLUMNNAME_DocStatus).append(" NOT IN (?,?,?)");
		params.add(MPPOrder.DOCSTATUS_Voided);
		params.add(MPPOrder.DOCSTATUS_Reversed);
		params.add(MPPOrder.DOCSTATUS_Closed);
		
		
		// For given resource (if any)
		if (S_Resource_ID > 0)
		{
			whereClause.append(" AND ").append(MPPOrder.COLUMNNAME_S_Resource_ID).append("=?");
			params.add(S_Resource_ID);
		}
		
		return new Query(getCtx(), MPPOrder.Table_Name, whereClause.toString(), trxName)
					.setParameters(params)
					.setOnlyActiveRecords(true)
					.setOrderBy(MPPOrder.COLUMNNAME_DatePromised);
	}

	public MPPOrder[] getPPOrders(Timestamp dateTime, I_S_Resource r)
	{
		if(!isAvailable(r, dateTime))
		{
			return new MPPOrder[0];
		}

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(r.getS_Resource_ID());
		final String whereClause = 
			// Checks the requested resource id directly on order node, not on resource id of the order
			"EXISTS (SELECT 1 FROM PP_Order_Node WHERE "
								+" PP_Order_Node.PP_Order_ID=PP_Order.PP_Order_ID"
								+" AND S_Resource_ID=?"
								// ... and only the orders running on given day
								+" AND "+getSQLDayRestriction(dateTime, r, params)
			+")"
			+ " AND AD_Client_ID=?";
		params.add(r.getAD_Client_ID());
		
		List<MPPOrder> list = new Query(getCtx(r), MPPOrder.Table_Name, whereClause, null)
									.setParameters(params)
									.list();
		return list.toArray(new MPPOrder[list.size()]);
	}  

	public MPPOrderNode[] getPPOrderNodes(Timestamp dateTime, I_S_Resource r)
	{
		if(!isAvailable(r, dateTime))
		{
			return new MPPOrderNode[0];
		}

		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause = MPPOrderNode.COLUMNNAME_S_Resource_ID+"=? AND AD_Client_ID=?";
		params.add(r.getS_Resource_ID());
		params.add(r.getAD_Client_ID());
		
		whereClause += " AND "+getSQLDayRestriction(dateTime, r, params);
		
		List<MPPOrderNode> list = new Query(getCtx(r), MPPOrderNode.Table_Name, whereClause, null)
									.setParameters(params)
									.list();
		return list.toArray(new MPPOrderNode[list.size()]);
	}  

	public boolean isAvailable(I_S_Resource r, Timestamp dateTime)
	{
		MResourceType t = MResourceType.get(getCtx(r), r.getS_ResourceType_ID());
		return t.isDayAvailable(dateTime) && !MResourceUnAvailable.isUnAvailable(r, dateTime);
	}

	public boolean isAvailable(I_S_Resource r)
	{
		MResourceType t = MResourceType.get(getCtx(r), r.getS_ResourceType_ID());
		return t.isAvailable();
	}

	/**
	 * Get Next/Previous Available Date
	 * @param t
	 * @param dateTime
	 * @param isScheduleBackward
	 * @return
	 */
	private Timestamp getAvailableDate(MResourceType t, Timestamp dateTime, boolean isScheduleBackward)
	{
		Timestamp date = dateTime;
		int direction = isScheduleBackward ? -1 : +1; 
		int i = 0;
		do {			
			if (t.isDayAvailable(date))
			{
				return date;
			}
			date = TimeUtil.addDays(date, direction);
			i++;		
		} while (i < 7);
		return date;
	}

	/**
	 * @param r resource
	 * @param dateTime
	 * @return next available date
	 */
	public Timestamp getAvailableDate(I_S_Resource r, Timestamp dateTime, boolean isScheduleBackward)
	{
		MResourceType t = MResourceType.get(getCtx(r), r.getS_ResourceType_ID());
		Timestamp date = dateTime;
		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause;
		String orderByClause;
		int direction;
		if (isScheduleBackward)
		{
			whereClause = MResourceUnAvailable.COLUMNNAME_DateFrom+" <= ?";
			params.add(date);
			orderByClause = MResourceUnAvailable.COLUMNNAME_DateFrom+" DESC";
			direction = 1;
		}
		else
		{
			whereClause = MResourceUnAvailable.COLUMNNAME_DateTo+" >= ?";
			params.add(date);
			orderByClause = MResourceUnAvailable.COLUMNNAME_DateTo;
			direction = -1;
		}

		whereClause += " AND "+MResourceUnAvailable.COLUMNNAME_S_Resource_ID+"=? AND AD_Client_ID=?";
		params.add(r.getS_Resource_ID());
		params.add(r.getAD_Client_ID());
		
		POResultSet<MResourceUnAvailable> rs = new Query(getCtx(r), MResourceUnAvailable.Table_Name, whereClause, null)
												.setOrderBy(orderByClause)
												.setParameters(params)
												.scroll();
		try
		{
			while(rs.hasNext())
			{
				MResourceUnAvailable rua = rs.next();
				if (rua.isUnAvailable(date))
				{
					date = TimeUtil.addDays(rua.getDateTo(), 1 * direction);
				}
				date = getAvailableDate(t, dateTime, isScheduleBackward);
			}
		}
		finally
		{
			DB.close(rs);
		}
		//
		date = getAvailableDate(t, dateTime, isScheduleBackward);
		return date;
	}
}

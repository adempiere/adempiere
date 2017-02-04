/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;


/**
 *	Resource Unavailable
 *	
 *  @author Jorg Janke
 *  @version $Id: MResourceUnAvailable.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MResourceUnAvailable extends X_S_ResourceUnAvailable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1087763356022282086L;

	/**
	 * Check if a resource is not available
	 * @param r resource
	 * @param dateTime date (date is truncated to day)
	 * @return true if resource is unavailable
	 */
	public static boolean isUnAvailable(I_S_Resource r, Timestamp dateTime)
	{
		Timestamp date = TimeUtil.trunc(dateTime, TimeUtil.TRUNC_DAY);
		final String whereClause = COLUMNNAME_S_Resource_ID+"=? AND AD_Client_ID=?"
									+" AND TRUNC("+COLUMNNAME_DateFrom+", 'DD') <= ?"
									+" AND TRUNC("+COLUMNNAME_DateTo+", 'DD') >= ?";
		Properties ctx = r instanceof PO ? ((PO)r).getCtx() : Env.getCtx();
		return new Query(ctx, I_S_ResourceUnAvailable.Table_Name, whereClause, null)
						.setParameters(r.getS_Resource_ID(), r.getAD_Client_ID(), date, date)
						.match();
	}
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param S_ResourceUnAvailable_ID id
	 *	@param trxName trx 
	 */
	public MResourceUnAvailable (Properties ctx, int S_ResourceUnAvailable_ID, String trxName)
	{
		super (ctx, S_ResourceUnAvailable_ID, trxName);
	}	//	MResourceUnAvailable

	/**
	 * 	MResourceUnAvailable
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MResourceUnAvailable (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MResourceUnAvailable

	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		if (getDateTo() == null)
			setDateTo(getDateFrom());
		if (getDateFrom().after(getDateTo()))
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@DateTo@ > @DateFrom@"));
			return false;
		}
		return true;
	}	//	beforeSave
	

	/**
	 * Check if the resource is unavailable for date
	 * @param date
	 * @return true if valid
	 */
	public boolean isUnAvailable(Timestamp dateTime)
	{
		Timestamp date = TimeUtil.trunc(dateTime, TimeUtil.TRUNC_DAY);
		Timestamp dateFrom = getDateFrom();
		Timestamp dateTo = getDateTo();
		
		if (dateFrom != null && date.before(dateFrom))
			return false;
		if (dateTo != null && date.after(dateTo))
			return false;
		return true;
	}

	
}	//	MResourceUnAvailable

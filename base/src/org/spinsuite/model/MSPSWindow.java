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

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class MSPSWindow extends X_SPS_Window
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5063117578595987820L;
	
	/**	The Tabs						*/
	private MSPSTab[]		m_SPSTabs	= null;

	
	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 12:22:24
	 * @param ctx
	 * @param SPS_Window_ID
	 * @param trxName
	 */
	public MSPSWindow(Properties ctx, int SPS_Window_ID, String trxName)
	{
		super(ctx, SPS_Window_ID, trxName);
	}

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 12:22:24
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSWindow(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	
	/**
	 * Get SFA Tab
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 23:08:02
	 * @param reload
	 * @param get_TrxName
	 * @return
	 * @return MSFATab[]
	 */
	public MSPSTab[] get(boolean reload, String get_TrxName)
	{
		//	Validate m_SFATabs not instanced or reload is true
		if (m_SPSTabs != null && !reload)
			return m_SPSTabs;
		
		//	Where clause
		final String whereClause = I_SPS_Tab.COLUMNNAME_SPS_Window_ID + "=?";

		//	Lists of SFA Tab
		List<MSPSTab> list = new Query(getCtx(), I_SPS_Tab.Table_Name, whereClause, get_TrxName)
			.setParameters(getSPS_Window_ID())
			.setOrderBy(I_SPS_Tab.COLUMNNAME_SeqNo)
			.list();
		
		//	Instanced SFA Tab
		m_SPSTabs = new MSPSTab[list.size()];
		list.toArray(m_SPSTabs);
		return m_SPSTabs;
		
	}//	get Tabs

}

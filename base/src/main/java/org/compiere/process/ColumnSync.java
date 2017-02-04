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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 *	Synchronize Column with Database
 *	
 *  @author Victor Perez, Jorg Janke
 *  @version $Id: ColumnSync.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  @author Teo Sarca
 *  	<li>BF [ 2854358 ] SyncColumn should load table in transaction
 *  		https://sourceforge.net/tracker/?func=detail&aid=2854358&group_id=176962&atid=879332
 */
public class ColumnSync extends SvrProcess
{
	/** The Column				*/
	private int			p_AD_Column_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_AD_Column_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("C_Column_ID=" + p_AD_Column_ID);
		if (p_AD_Column_ID == 0)
			throw new AdempiereUserError("@No@ @AD_Column_ID@");
		MColumn column = new MColumn (getCtx(), p_AD_Column_ID, get_TrxName());
		if (column.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@ @AD_Column_ID@ " + p_AD_Column_ID);
		
		String sql = column.syncDatabase();
		addLog(sql);
		return sql;
	}	//	doIt

}	//	ColumnSync

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**Network Distribution
*  @author Victor Perez,e-Evolution,SC
*  @version $Id: MDDNetworkDistribution.java,v 
*/
public class MDDNetworkDistribution extends X_DD_NetworkDistribution
{
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MDDNetworkDistribution.class);
	
    /** Standard Constructor */
    public MDDNetworkDistribution (Properties ctx, int DD_NetworkDistribution_ID, String trxName)
    {
     	super (ctx, DD_NetworkDistribution_ID, trxName);
        if (DD_NetworkDistribution_ID == 0)
        {
			setDD_NetworkDistribution_ID (0);
			/*setName (null);
			setValue (null);*/
        }
    }

    /** Load Constructor */
    public MDDNetworkDistribution (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    /** Lines						*/
	private MDDNetworkDistributionLine[]		m_lines = null;
	
	/**
	 * 	Get Lines
	 *	@return array of lines MDDNetworkDistributionLine
	 */
	public MDDNetworkDistributionLine[] getLines()
	{
		if (m_lines != null)
			return m_lines;
		
		ArrayList<MDDNetworkDistributionLine> list = new ArrayList<MDDNetworkDistributionLine>();
		String sql = "SELECT * FROM DD_NetworkDistributionLine WHERE DD_NetworkDistribution_ID=? ORDER BY PriorityNo";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getDD_NetworkDistribution_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MDDNetworkDistributionLine (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getLines", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_lines = new MDDNetworkDistributionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines


/**
 * 	Get Lines
 *  @param M_Warehouse_ID ID Warehouse
 *	@return array of lines MDDNetworkDistributionLine
 */
public MDDNetworkDistributionLine[] getLines(int M_Warehouse_ID)
{
	if (m_lines != null)
		return m_lines;
	
	ArrayList<MDDNetworkDistributionLine> list = new ArrayList<MDDNetworkDistributionLine>();
	String sql = "SELECT * FROM DD_NetworkDistributionLine WHERE DD_NetworkDistribution_ID=? and M_Warehouse_ID=? ORDER BY PriorityNo, M_Shipper_ID";
	PreparedStatement pstmt = null;
	try
	{
		pstmt = DB.prepareStatement (sql, get_TrxName());
		pstmt.setInt (1, getDD_NetworkDistribution_ID());
		pstmt.setInt (2, M_Warehouse_ID);
		ResultSet rs = pstmt.executeQuery ();
		while (rs.next ())
			list.add (new MDDNetworkDistributionLine (getCtx(), rs, get_TrxName()));
		rs.close ();
		pstmt.close ();
		pstmt = null;
	}
	catch (Exception e)
	{
		log.log(Level.SEVERE, "getLines", e);
	}
	try
	{
		if (pstmt != null)
			pstmt.close ();
		pstmt = null;
	}
	catch (Exception e)
	{
		pstmt = null;
	}
	m_lines = new MDDNetworkDistributionLine[list.size ()];
	list.toArray (m_lines);
	return m_lines;
}	//	getLines
}
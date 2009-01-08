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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;

/**
 * Workflow Node Asset Model
 *	
 * @author Victor Perez www.e-evolution.com      
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPWFNodeAsset extends X_PP_WF_Node_Asset
{
	private static final long	serialVersionUID	= 1L;

	/** Cache */
	private static CCache<Integer, Collection<MPPWFNodeAsset>>
		s_cache = new CCache<Integer, Collection<MPPWFNodeAsset>>(Table_Name, 20);
	
	/**
	 * @return array of node assets
	 */
	public static Collection<MPPWFNodeAsset> forAD_WF_Node_ID(Properties ctx, int AD_WF_Node_ID)
	{
		Collection<MPPWFNodeAsset> lines = s_cache.get(AD_WF_Node_ID);
		if (lines != null)
		{
			return lines;
		}
		
		final String whereClause = COLUMNNAME_AD_WF_Node_ID+"=?";
		lines = new Query(ctx, Table_Name, whereClause, null)
											.setParameters(new Object[]{AD_WF_Node_ID})
											.setOnlyActiveRecords(true)
											.setOrderBy(COLUMNNAME_SeqNo)
											.list();
		s_cache.put(AD_WF_Node_ID, lines);
		return lines;
	}

	public MPPWFNodeAsset (Properties ctx, int PP_WF_Node_Asset_ID, String trxName)
	{
		super (ctx, PP_WF_Node_Asset_ID, trxName);
		if (PP_WF_Node_Asset_ID == 0)
		{		
		}
	}

	public MPPWFNodeAsset (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	
}

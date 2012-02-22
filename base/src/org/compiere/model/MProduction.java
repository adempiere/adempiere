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
 * Copyright (C) 2003-2011 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;

/**
 * MProduct
 * @author victor.perez@e-evolution.com,www.e-evolution.com
 */
public class MProduction extends X_M_Production {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5713474625045040806L;
	private static CCache<Integer, MProduction> s_cache = new CCache<Integer, MProduction>(
			MProduction.Table_Name, 50);

	/**
	 * Get from Cache
	 * 
	 * @param ctx
	 *            context
	 * @param M_Warehouse_ID
	 *            id
	 * @return warehouse
	 */
	public static MProduction get(Properties ctx, int M_Production_ID) {
		MProduction retValue = s_cache.get(M_Production_ID);
		if (retValue != null)
			return retValue;
		//
		retValue = new MProduction(ctx, M_Production_ID, null);
		s_cache.put(M_Production_ID, retValue);
		return retValue;
	} // get

	/** Standard Constructor */
	public MProduction(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
		if (M_Production_ID == 0) {
		}
	}

	/** Load Constructor */
	public MProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
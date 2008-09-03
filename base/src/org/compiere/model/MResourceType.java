/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
import java.util.Properties;

import org.compiere.util.CCache;


/**
 *	Resource Type Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MResourceType.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 				<li>FR [ 2051056 ] MResource[Type] should be cached
 */
public class MResourceType extends X_S_ResourceType
{
	private static final long serialVersionUID = 1L;
	/** Cache */
	private static CCache<Integer, MResourceType> s_cache = new CCache<Integer, MResourceType>(Table_Name, 20);
	
	/**
	 * Get from Cache
	 * @param ctx
	 * @param S_ResourceType_ID
	 * @return MResourceType
	 */
	public static MResourceType get(Properties ctx, int S_ResourceType_ID) 
	{
		if (S_ResourceType_ID <= 0)
			return null;
		
		MResourceType type = s_cache.get(S_ResourceType_ID);
		if (type == null) {
			type = new MResourceType(ctx, S_ResourceType_ID, null);
			if (type.get_ID() == S_ResourceType_ID) {
				s_cache.put(S_ResourceType_ID, type);
			}
		}
		return type;
	}
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param S_ResourceType_ID id
	 */
	public MResourceType (Properties ctx, int S_ResourceType_ID, String trxName)
	{
		super (ctx, S_ResourceType_ID, trxName);
	}	//	MResourceType

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MResourceType (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MResourceType
	
	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return false;
		
		//	Update Products
		if (!newRecord)
		{
			MProduct[] products = MProduct.get(getCtx(), "S_Resource_ID IN "
				+ "(SELECT S_Resource_ID FROM S_Resource WHERE S_ResourceType_ID=" 
				+ getS_ResourceType_ID() + ")", get_TrxName());
			for (int i = 0; i < products.length; i++)
			{
				MProduct product = products[i];
				if (product.setResource(this))
				{
					product.saveEx(get_TrxName());
				}
			}
		}
		
		return success;
	}	//	afterSave
}	//	MResourceType

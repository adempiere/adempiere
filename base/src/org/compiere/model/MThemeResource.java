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
import java.util.List;
import java.util.Properties;

/**
 *  Theme Resources
 *
 *	@author Michael McKay (mjmckay)
 */
public class MThemeResource extends X_AD_ThemeResource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2147517429817854005L;

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param AD_Theme_ID The ID to find. If zero, a new model will be generated using the defaults.
	 *	@param trxName transaction
	 */
	public MThemeResource(Properties ctx, int AD_Theme_ID, String trxName) 
	{
		super(ctx, AD_Theme_ID, trxName);
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MThemeResource (Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}

	/**
	 * 	Get the list of theme resources. 
	 *	@return MThemeResource[] or null
	 */
	public static MThemeResource[] getAllResources (Properties ctx, int AD_Theme_ID)
	{
		List<MThemeResource> list = new Query(ctx, I_AD_ThemeResource.Table_Name, "AD_Theme_ID=?", null)
								.setClient_ID()
								.setOnlyActiveRecords(true)
								.setParameters(AD_Theme_ID)
								.list();
		MThemeResource[] retValue = list.toArray(new MThemeResource[list.size()]);
		return retValue;
	}	//	getAllThemeResources
	
	/**
	 * Get the resource URI
	 * @return URI
	 */
	public String get_URI()
	{
		return (String) get_Value(COLUMNNAME_ResourceURI);
	}
}	//	MTheme

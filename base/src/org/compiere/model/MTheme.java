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

import org.compiere.model.I_AD_Theme;
import org.compiere.model.I_AD_ThemeResource;
import org.compiere.model.X_AD_Theme;

/**
 *  Theme - Model for themes available to the ZK interface
 *
 *	@author Michael McKay
 */
public class MTheme extends X_AD_Theme
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3581582228134325463L;

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param AD_Theme_ID The ID to find. If zero, a new model will be generated using the defaults.
	 *	@param trxName transaction
	 */
	public MTheme(Properties ctx, int AD_Theme_ID, String trxName) {
		super(ctx, AD_Theme_ID, trxName);
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTheme (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	/**
	 * 	Get the default theme.  This will be highest priority default theme. 
	 *	@return MTheme or null if no themes are marked as the default
	 */
	public static MTheme getDefault (Properties ctx)
	{
		MTheme theme = new Query(ctx, I_AD_Theme.Table_Name, "IsDefaultTheme='Y'", null)
				.setOrderBy("ThemePriority, ThemeName")
				.setOnlyActiveRecords(true)
				.first();
		
		return theme; // Return the first record or null
	}
	
	
	/**
	 * 	Get the list of availble themes.   
	 *	@return MTheme[]
	 */
	public static MTheme[] getAllThemes (Properties ctx)
	{
		List<MTheme> list = new Query(ctx, I_AD_Theme.Table_Name, null, null)
								.setClient_ID()
								.setOrderBy("ThemePriority Desc, ThemeName")
								.setOnlyActiveRecords(true)
								.list();
		MTheme[] retValue = list.toArray(new MTheme[list.size()]);
		return retValue;
	}	//	getAllThemes

	/**
	 * 	Get a theme by ID.   
	 *	@return MTheme
	 */
	public static MTheme get (Properties ctx, int AD_Theme_ID)
	{
		return new MTheme(ctx, AD_Theme_ID, null);
	}	//	get - by ID

	/**
	 * 	Get a theme by theme name.   
	 *	@return MTheme
	 */
	public static MTheme get (Properties ctx, String themeName)
	{
		final String whereClause = I_AD_Theme.COLUMNNAME_ThemeName+"=?";
		MTheme theme = new Query(ctx, I_AD_Theme.Table_Name, whereClause, null)
			.setOnlyActiveRecords(true)
			.setParameters(themeName)
			.first();
		
		return theme;
	}	//	get - by name

	/**
	 * 	Get Theme Resources
	 *	@return array of Theme Resources or null
	 */
	public MThemeResource[] getResources ()
	{
		if (!isActive())
			return null;

		final String whereClause = I_AD_ThemeResource.COLUMNNAME_AD_Theme_ID+"=?";
		List<MTheme> list = new Query(getCtx(), I_AD_ThemeResource.Table_Name, whereClause,  null)
			.setParameters(getAD_Theme_ID())
			.setOnlyActiveRecords(true)
			.list();	
	 
		MThemeResource [] resources = new MThemeResource[list.size ()];
		list.toArray (resources);
		return resources;
	}	//	getResources
	
	/**
	 * 	Get Theme Resource URIs
	 *	@return array of Theme Resources URIs
	 */
	public String[] getResourcesURIs ()
	{
		if (!isActive())
			return null;

		final String whereClause = I_AD_ThemeResource.COLUMNNAME_AD_Theme_ID+"=?";
		List<MTheme> list = new Query(getCtx(), I_AD_ThemeResource.Table_Name, whereClause,  null)
			.setParameters(getAD_Theme_ID())
			.setOnlyActiveRecords(true)
			.list();	
	 
		MThemeResource [] resources = new MThemeResource[list.size ()];
		list.toArray (resources);
		
		String retValue[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			retValue[i] = resources[i].get_URI();
		}
		
		return retValue;
	}	//	getResources
	
	public String get_origin() {
		return this.get_ValueAsString(COLUMNNAME_ThemeOrigin);
	}

	public int get_priority() {
		return this.get_ValueAsInt(COLUMNNAME_ThemePriority);
	}

	public String get_displayName() {
		return this.get_ValueAsString(COLUMNNAME_ThemeDisplay);
	}

	public String get_themeName() {
		return this.get_ValueAsString(COLUMNNAME_ThemeName);
	}
		
	/**
	 * Get the Cache Hours which determines the cach control.  Returns -1 if no caching.
	 */
	public int getCacheHours() {
		if (isCached()) {
			return super.getCacheHours();
		}
		else {
			return -1;
		}
	}
}	//	MTheme

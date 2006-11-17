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
package org.compiere.cm.cache;

import org.compiere.model.*;

/**
 * @author Yves Sandfort
 * @version $Id$
 */
public class Domain extends CO
{
	/**
	 * 	Get Web Project Domain
	 *	@param serverName
	 *	@return web project domain
	 */
	public MWebProjectDomain getWebProjectDomain (String serverName)
	{
		if (cache.containsKey (serverName))
		{
			use (serverName);
			return (MWebProjectDomain)cache.get (serverName);
		}
		else
		{
			int[] tableKeys = MWebProjectDomain.getAllIDs (
				"CM_WebProject_Domain",
				"lower(FQDN) LIKE '" + serverName + "'", "WebCM");
			if (tableKeys==null || tableKeys.length == 0)
			{
				// HardCoded to deliver the GardenWorld Site as default
				return null;
			}
			else if (tableKeys.length == 1)
			{
				MWebProjectDomain thisDomain = new MWebProjectDomain (
					ctx, tableKeys[0], "WebCM");
				put (thisDomain.getFQDN (), thisDomain);
				return thisDomain;
			}
			// We found more than one hit, this is bad, so we will try to use the first non system / gardenworld one
			else if (tableKeys.length>1)
			{
				for (int i=0;i<tableKeys.length;i++) {
					if (tableKeys[i]>=1000000) {
						MWebProjectDomain thisDomain = new MWebProjectDomain (
							ctx, tableKeys[i], "WebCM");
						put (thisDomain.getFQDN (), thisDomain);
						return thisDomain;
					}
				}
				// We can't find any non system/gardenworld hit, so we will try the first one
				for (int i=0;i<tableKeys.length;i++) {
					MWebProjectDomain thisDomain = new MWebProjectDomain (
						ctx, tableKeys[0], "WebCM");
					put (thisDomain.getFQDN (), thisDomain);
					return thisDomain;
				}
				// As even this doesn't work we return null
				return null;
			}
			else
			{
				return null;
			}
		}
	}	//	getWebProjectDomain
	
}	//	Domain

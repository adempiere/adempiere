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
package org.compiere.cm.cache;

import java.util.Hashtable;

import org.compiere.model.MCountry;

public class XML extends CO {
	
	protected Hashtable cacheContainerURL = new Hashtable(cacheSize);
	
	public String getXML(String ID) {
		if (cache.containsKey(ID)) {
			use(ID);
			return (String) cache.get(ID).toString ();
		} else {
			// This Cache is special we do not base on records we provide common data, i.e. Countries etc.
			StringBuffer result = new StringBuffer();
			if (ID.equals ("C_Country"))
				result = genCountryList(result);
			if (result!=null) {
				put (ID,result);
				return result.toString ();
			} else {
				return null;
			}
		}
	}
	
	private StringBuffer genCountryList(StringBuffer result) 
	{
		result.append("<c_country>\n");
		result.append("  <default>\n");
		MCountry defaultCountry = MCountry.getDefault (getCtx());
		result = defaultCountry.get_xmlString (result);
		result.append("  </default>\n");
		MCountry theseCountries[] = MCountry.getCountries(getCtx());
		for (int i=0;i<theseCountries.length;i++) {
			result = theseCountries[i].get_xmlString (result);
		}
		result.append("</c_country>\n");
		return result;
	}
}

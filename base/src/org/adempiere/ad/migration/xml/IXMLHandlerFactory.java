 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.xml;

import org.adempiere.util.ISingletonService;

public interface IXMLHandlerFactory extends ISingletonService
{
	/**
	 * Register a handler
	 * 
	 * @param nodeName
	 * @param beanClass bean class (e.g. I_AD_Migration)
	 * @param converterClass converter class (e.g. MigrationConverter)
	 */
	<T> void registerHandler(String nodeName, Class<T> beanClass, Class<? extends IXMLHandler<T>> converterClass);

	<T> Class<T> getBeanClassByNodeName(String nodeName);

	<T> IXMLHandler<T> getHandlerByNodeName(String nodeName);

	<T> IXMLHandler<T> getHandler(Class<T> clazz);

}

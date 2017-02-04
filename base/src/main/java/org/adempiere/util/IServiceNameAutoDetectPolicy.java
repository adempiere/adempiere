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
package org.adempiere.util;

/**
 * If {@link Services#isAutodetectServices()} is <code>true</code> and no service implementation has been registered for a given {@link IService} class, then an implementor of this class constructs
 * the classname of the service implementation class.
 * 
 * @see Services#setServiceNameAutoDetectPolicy(IServiceNameAutoDetectPolicy)
 * 
 */
public interface IServiceNameAutoDetectPolicy
{
	String getServiceImplementationClassName(Class<? extends IService> clazz);
}

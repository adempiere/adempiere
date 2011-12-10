/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) metas All Rights Reserved.                                   *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author: Ryan Wu, Engineer, Potix Corporation                              *
 *   (http://docs.zkoss.org/wiki/How_to_Test_ZK_Application_with_Selenium)    *
 * @author: Carlos Ruiz                                                       *
 * @author: Victor Perez                                                      *
 *****************************************************************************/

package org.adempiere.webui;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.IdGenerator;

/**
 * 
 * @author: Teo Sarca
 */
public class AdempiereIdGenerator implements IdGenerator
{
	public static final String SYSCONFIG_IdGenerator = "org.adempiere.webui.IdGenerator";
	public static final String SYSCONFIG_IdGenerator_Default = org.adempiere.webui.SahiIdGenerator.class.getCanonicalName();

	private static IdGenerator idGenerator = null;

	public static void setIdGenerator(IdGenerator generator)
	{
		idGenerator = generator;
	}

	public static IdGenerator getIdGenerator()
	{
		if (idGenerator == null)
		{
			String classname = MSysConfig.getValue(SYSCONFIG_IdGenerator, SYSCONFIG_IdGenerator_Default);
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (cl == null)
				cl = AdempiereIdGenerator.class.getClassLoader();

			try
			{
				idGenerator = (IdGenerator)cl.loadClass(classname).newInstance();
			}
			catch (Exception e)
			{
				throw new AdempiereException(e);
			}
		}
		return idGenerator;
	}

	@Override
	public String nextComponentUuid(Desktop desktop, Component comp)
	{
		return getIdGenerator().nextComponentUuid(desktop, comp);
	}

	@Override
	public String nextDesktopId(Desktop desktop)
	{
		return getIdGenerator().nextDesktopId(desktop);
	}

	@Override
	public String nextPageUuid(Page page)
	{
		return getIdGenerator().nextPageUuid(page);
	}
}

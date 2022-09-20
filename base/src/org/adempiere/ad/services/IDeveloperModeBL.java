/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
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
 *                                                                            *
 * @author Teo Sarca, t.sarca@metas.ro METAS GROUP 							  *
 *				                                                              *
 *****************************************************************************/

package org.adempiere.ad.services;

import java.util.Properties;

import org.adempiere.util.ISingletonService;


public interface IDeveloperModeBL extends ISingletonService
{
	public interface ContextRunnable
	{
		public void run(Properties sysCtx);
	}

	/**
	 * 
	 * @return true if developer mode is enabled
	 */
	boolean isEnabled();

	/**
	 * 
	 * @param ctx
	 * @return true if the given context is just a jailed sysadm context used to generate system entries from user mode
	 */
	boolean isJailedSysContext(Properties ctx);

	/**
	 * Execute {@link ContextRunnable} in SysAdm context
	 * 
	 * @param runnable
	 */
	void executeAsSystem(ContextRunnable runnable);

	/**
	 * Create AD_Message or AD_Element
	 * 
	 * @param adLanguage
	 * @param text
	 * @param checkMessage
	 *            try creating a message (if valid)
	 * @param checkElement
	 *            try creating an element (if valid)
	 * @return true if created or updated
	 */
	boolean createMessageOrElement(String adLanguage, String text, boolean checkMessage, boolean checkElement);
}

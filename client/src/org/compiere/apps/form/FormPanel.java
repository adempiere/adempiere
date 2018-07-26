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
package org.compiere.apps.form;

import org.compiere.model.MSysConfig;
import org.compiere.util.Env;

/**
 *	Form Panel Interface.
 *	for communicating between FormFrame and JPanel
 * 	@author 	Jorg Janke
 *  @author https://github.com/homebeaver
 *	@see <a href="https://github.com/adempiere/adempiere/issues/1657"> Java 8 default autoQuery</a>
 */
public interface FormPanel
{
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init (int WindowNo, FormFrame frame);

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose();
	
	static String SYSCONFIG_INFO_AUTO_QUERY = "INFO_AUTO_QUERY";
	
	/**
	 * get SYSCONFIG_INFO_AUTO_QUERY value as boolean
	 * @return true if SYSCONFIG_INFO_AUTO_QUERY=="Y" or null
	 */
	default boolean autoQuery() {
		return MSysConfig.getValue(SYSCONFIG_INFO_AUTO_QUERY,"Y",Env.getAD_Client_ID(Env.getCtx())).equals("Y");
	}
	


}	//	FormPanel

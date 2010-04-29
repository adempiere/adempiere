/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.process;

import org.adempiere.common.ADClassNameMap;
import org.compiere.process.ProcessInfo;

/**
 * @author hengsin
 *
 */
public class WProcessInfo extends ProcessInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2034681838152013686L;

	/**
	 * @param Title
	 * @param AD_Process_ID
	 * @param Table_ID
	 * @param Record_ID
	 */
	public WProcessInfo(String Title, int AD_Process_ID, int Table_ID,
			int Record_ID) {
		super(Title, AD_Process_ID, Table_ID, Record_ID);
	}

	/**
	 * @param Title
	 * @param AD_Process_ID
	 */
	public WProcessInfo(String Title, int AD_Process_ID) {
		super(Title, AD_Process_ID);
	}

	@Override
	public void setClassName(String ClassName) {
		String name = ClassName;
		if (name != null && name.trim().length() > 0) {
			name = ADClassNameMap.get(ClassName);
			if (name == null || name.trim().length() == 0) {
				name = ClassName;
			}
		}
		super.setClassName(name);
	}
}

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
package org.adempiere.webui.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author hengsin
 *
 */
public class ADClassNameMap {

	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("org.compiere.apps.form.ArchiveViewer", "org.adempiere.webui.apps.form.WArchiveViewer");
		map.put("org.compiere.apps.wf.WFActivity", "org.adempiere.webui.apps.wf.WWFActivity");
		map.put("org.compiere.apps.wf.WFPanel", "org.adempiere.webui.apps.wf.WFEditor");
		map.put("org.compiere.process.InvoicePrint", "org.adempiere.webui.process.InvoicePrint");
	}
	
	/**
	 * 
	 * @param src
	 * @return String
	 */
	public static String get(String src) {
		return map.get(src);
	}
	
	/**
	 * 
	 * @param src
	 * @param target
	 */
	public static void add(String src, String target) {
		map.put(src, target);
	}
}

package org.adempiere.webui.util;

import java.util.HashMap;
import java.util.Map;

public class ADClassNameMap {

	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("org.compiere.apps.form.ArchiveViewer", "org.adempiere.webui.apps.form.WArchiveViewer");
		map.put("org.compiere.apps.wf.WFActivity", "org.adempiere.webui.apps.wf.WWFActivity");
		map.put("org.compiere.apps.wf.WFPanel", "org.adempiere.webui.apps.wf.WFEditor");
		map.put("org.compiere.process.InvoicePrint", "org.adempiere.webui.process.InvoicePrint");
		map.put("org.compiere.process.CacheReset", "org.adempiere.webui.process.CacheReset");
		map.put("org.adempiere.apps.graph.ViewPI", "org.adempiere.webui.apps.graph.WViewPI");
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

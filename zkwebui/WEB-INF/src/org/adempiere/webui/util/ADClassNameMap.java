package org.adempiere.webui.util;

import java.util.HashMap;
import java.util.Map;

public class ADClassNameMap {

	private static Map<String, String> map = new HashMap<String, String>();
	
	static {
		map.put("org.compiere.apps.wf.WFPanel", "org.adempiere.webui.apps.wf.WFEditor");
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

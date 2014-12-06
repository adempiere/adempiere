/**
 * 
 */
package org.adempiere.util;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.compiere.model.MClient;
import org.compiere.util.DisplayType;
import org.compiere.util.Language;

/**
 * Misc utils
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public final class Util
{
	private Util()
	{
		// nothing
	}
	
	/**
	 * @param ctx
	 * @return DateFormat for current AD_Client's language 
	 */
	public static SimpleDateFormat getClientDateFormat(Properties ctx)
	{
		String lang = MClient.get(ctx).getAD_Language();
		return DisplayType.getDateFormat(Language.getLanguage(lang));
	}
	
	/**
	 * Check if strings are equal.
	 * We consider 2 strings equal if they both are null or they both are equal.
	 * @param s1
	 * @param s2
	 * @return true if string are equal
	 */
	public static boolean equals(String s1, String s2)
	{
		return (s1 == null && s2 == null)
			|| (s1 != null && s2 != null && s1.equals(s2));
	}
}

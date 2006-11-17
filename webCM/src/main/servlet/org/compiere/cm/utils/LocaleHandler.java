/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.cm.utils;

import java.util.*;

/**
 * @author YS
 * @version $Id$
 */
public class LocaleHandler
{
	/** Locale				*/
	private Locale			m_chosenLocale;
	/** Charset				*/
	private String			m_chosenCharset;
	
	/**	Locale - character set map	*/
	private static Hashtable<String,String> s_map;
	
	/** Static Init	*/
	static
	{
		s_map = new Hashtable<String,String> ();
		s_map.put ("ar", "ISO-8859-6");
		s_map.put ("be", "ISO-8859-5");
		s_map.put ("bg", "ISO-8859-5");
		s_map.put ("ca", "ISO-8859-1");
		s_map.put ("cs", "ISO-8859-2");
		s_map.put ("da", "ISO-8859-1");
		s_map.put ("de", "ISO-8859-1");
		s_map.put ("el", "ISO-8859-7");
		s_map.put ("en", "ISO-8859-1");
		s_map.put ("es", "ISO-8859-1");
		s_map.put ("et", "ISO-8859-1");
		s_map.put ("fi", "ISO-8859-1");
		s_map.put ("fr", "ISO-8859-1");
		s_map.put ("hr", "ISO-8859-2");
		s_map.put ("hu", "ISO-8859-2");
		s_map.put ("is", "ISO-8859-1");
		s_map.put ("it", "ISO-8859-1");
		s_map.put ("iw", "ISO-8859-8");
		s_map.put ("ja", "Shift_JIS");
		s_map.put ("ko", "EUC-KR");
		s_map.put ("lt", "ISO-8859-2");
		s_map.put ("lv", "ISO-8859-2");
		s_map.put ("mk", "ISO-8859-5");
		s_map.put ("nl", "ISO-8859-1");
		s_map.put ("no", "ISO-8859-1");
		s_map.put ("pl", "ISO-8859-2");
		s_map.put ("pt", "ISO-8859-1");
		s_map.put ("ro", "ISO-8859-2");
		s_map.put ("ru", "ISO-8859-5");
		s_map.put ("sh", "ISO-8859-5");
		s_map.put ("sk", "ISO-8859-2");
		s_map.put ("sl", "ISO-8859-2");
		s_map.put ("sq", "ISO-8859-2");
		s_map.put ("sr", "ISO-8859-5");
		s_map.put ("sv", "ISO-8859-1");
		s_map.put ("tr", "ISO-8859-9");
		s_map.put ("uk", "ISO-8859-5");
		s_map.put ("zh", "GB2312");
		s_map.put ("zh_TW", "Big5");
	}	//	static

	/**
     * Constructs a new LocaleHandler language list, and charset list.
     * 
     * @param languages
     *            the Accept-Language header
     * @param charsets
     *            the Accept-Charset header
     */
	public LocaleHandler (String languages, String charsets)
	{
		Locale defaultLocale = new Locale ("en", "US");
		String defaultCharset = "ISO-8859-1";
		// If no specific language we will keep the default values
		if (languages == null)
		{
			m_chosenLocale = defaultLocale;
			m_chosenCharset = defaultCharset;
			return; // quick exit
		}
		StringTokenizer langTokenizer = new StringTokenizer (languages, ",");
		while (langTokenizer.hasMoreTokens ())
		{
			String lang = langTokenizer.nextToken ();
			Locale loc = getLocaleForLanguage (lang);
			String charset = getCharsetForLocale (loc, charsets);
			if (charset == null)
				continue;
			// We can use the values if this point is reached
			m_chosenLocale = loc;
			m_chosenCharset = charset;
			return;
		}
		// We didn't found anything, so we will use the defaults
		m_chosenLocale = defaultLocale;
		m_chosenCharset = defaultCharset;
	}	//	LocaleHandler

	/**
     * Gets the chosen locale.
     * 
     * @return the chosen locale
     */
	public Locale getLocale ()
	{
		return m_chosenLocale;
	}	//	getLocale

	/**
     * Gets the chosen charset.
     * 
     * @return the chosen charset
     */
	public String getCharset ()
	{
		return m_chosenCharset;
	}	//	getCharset

	/*
     * Gets a Locale object for a given language string
     */
	private Locale getLocaleForLanguage (String lang)
	{
		Locale loc;
		int semi, dash;
		// Cut off any qvalue that might come after a semi-colon
		if ((semi = lang.indexOf (';')) != -1)
		{
			lang = lang.substring (0, semi);
		}
		// Trim any whitespace
		lang = lang.trim ();
		// Create a Locale from the language. A dash may separate the
		// language from the country.
		if ((dash = lang.indexOf ('-')) == -1)
		{
			loc = new Locale (lang, ""); // No dash, no country
		}
		else
		{
			loc = new Locale (lang.substring (0, dash), lang
				.substring (dash + 1));
		}
		return loc;
	}	//	getLanguageForLanguage

	/**
     * Gets the best charset for a given locale, selecting from a charset list.
     * Currently ignores the charset list. Subclasses can override this method
     * to take the list into account.
     * 
     * @param loc
     *            the locale
     * @param charsets
     *            a comma-separated charset list
     * @return the best charset for the given locale from the given list
     */
	protected String getCharsetForLocale (Locale loc, String charsets)
	{
		// Note: This method ignores the client-specified charsets
		return getCharset (loc);
	}	//	getCharsetForLocale

	/**
     * Gets the preferred charset for the given locale, or null if the locale is
     * not recognized.
     * 
     * @param loc
     *            the locale
     * @return the preferred charset
     */
	public static String getCharset (Locale loc)
	{
		String charset;
		// Try for an full name match (may include country)
		charset = (String)s_map.get (loc.toString ());
		if (charset != null)
			return charset;
		// If a full name didn't match, try just the language
		charset = (String)s_map.get (loc.getLanguage ());
		return charset; // may be null
	}	//	getCharset
	
}	//	LocaleHandler


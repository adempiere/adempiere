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
package org.compiere.util;

import java.awt.ComponentOrientation;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;

import javax.print.attribute.standard.MediaSize;

/**
 *  Language Management.
 *
 *  @author     Jorg Janke
 *  @version    $Id: Language.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  	<li> Add Support to Language Venezuela
 *  	@see <a href="https://adempiere.atlassian.net/browse/ADEMPIERE-397">...</a>
 *  	<a href="https://github.com/adempiere/adempiere/issues/580">
 * 		@see FR [ 580 ] Bad language for es Venezuela</a>
 */
public class Language implements Serializable
{
	/**
	 *
	 *
	 **************************************************************************
	 *  Languages
	 *      http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
	 *  Countries
	 *      http://www.iso.org/iso/country_codes/iso_3166_code_lists/english_country_names_and_code_elements.htm
	 *************************************************************************/

	/**
	 * 
	 */
	private static final long serialVersionUID = -964846521004545703L;
	/** Base Language               */
	public static final String  AD_Language_en_US = "en_US";
	/** Additional Languages         */
	private static final String AD_Language_ar_TN = "ar_TN";
	private static final String AD_Language_bg_BG = "bg_BG";
	private static final String AD_Language_ca_ES = "ca_ES";
	private static final String AD_Language_da_DK = "da_DK";
	private static final String AD_Language_de_DE = "de_DE";
	private static final String AD_Language_el_GR = "el_GR";
	private static final String AD_Language_en_AU = "en_AU";
	private static final String AD_Language_en_CA = "en_CA";
	private static final String AD_Language_en_GB = "en_GB";
	private static final String AD_Language_es_CL = "es_CL";
	private static final String AD_Language_es_CO = "es_CO";
	private static final String AD_Language_es_DO = "es_DO";
	private static final String AD_Language_es_ES = "es_ES";
	private static final String AD_Language_es_MX = "es_MX";
	private static final String AD_Language_es_PA = "es_PA";
	private static final String AD_Language_es_SV = "es_SV";
	private static final String AD_Language_es_VE = "es_VE";
	private static final String AD_Language_fa_IR = "fa_IR";
	private static final String AD_Language_fi_FI = "fi_FI";
	private static final String AD_Language_fr_CA = "fr_CA";
	private static final String AD_Language_fr_FR = "fr_FR";
	private static final String AD_Language_hr_HR = "hr_HR";
	private static final String AD_Language_hu_HU = "hu_HU";
	private static final String AD_Language_in_ID = "in_ID";
	private static final String AD_Language_it_IT = "it_IT";
	private static final String AD_Language_ja_JP = "ja_JP";
	private static final String AD_Language_ms_MY = "ms_MY";
	private static final String AD_Language_nl_NL = "nl_NL";
	private static final String AD_Language_no_NO = "no_NO";
	private static final String AD_Language_pl_PL = "pl_PL";
	private static final String AD_Language_pt_BR = "pt_BR";
	private static final String AD_Language_ro_RO = "ro_RO";
	private static final String AD_Language_ru_RU = "ru_RU";
	private static final String AD_Language_sl_SI = "sl_SI";
	private static final String AD_Language_sr_RS = "sr_RS";
	private static final String AD_Language_sv_SE = "sv_SE";
	private static final String AD_Language_th_TH = "th_TH";
	private static final String AD_Language_vi_VN = "vi_VN";
	private static final String AD_Language_zh_CN = "zh_CN";
	private static final String AD_Language_zh_TW = "zh_TW";

	
	/***
	 *  System Languages.
	 *  If you want to add a language, extend the array
	 *  - or use the addLanguage() method.
	 **/
	static private Language[] languages = {
		new Language ("English",
			AD_Language_en_US,  Locale.US,      null, null,
			MediaSize.NA.LETTER),							    //  Base Language
		//	ordered by locale
		//	Not predefined Locales - need to define decimal Point and date pattern (not sure about time)
		new Language ("\uFE94\uFEF4\uFE91\uFEAE\uFECC\uFEDF\uFE8D (AR)",
			AD_Language_ar_TN,  new Locale("ar","TN"), Boolean.TRUE, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("\u0411\u044A\u043B\u0433\u0430\u0440\u0441\u043A\u0438 (BG)",
			AD_Language_bg_BG,  new Locale("bg","BG"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Catal\u00e0",
			AD_Language_ca_ES, new Locale("ca", "ES"), null, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Deutsch",
			AD_Language_de_DE,  Locale.GERMANY, Boolean.TRUE, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("Dansk",
			AD_Language_da_DK,  new Locale("da","DK"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("English (AU)",
			AD_Language_en_AU,  new Locale("en","AU"), null, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("English (UK)",
			AD_Language_en_GB,  Locale.UK,      null, null,
			MediaSize.ISO.A4),
		new Language ("English (CA)",
	        AD_Language_en_CA,  new Locale("en","CA"),      null, "MM/dd/yyyy",
	        MediaSize.NA.LETTER),                               //  Canadian English
		new Language ("Espa\u00f1ol",
			AD_Language_es_ES,  new Locale("es","ES"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Espa\u00f1ol (SV)",
		    AD_Language_es_SV,  new Locale("es","SV"), Boolean.TRUE, "dd/MM/yyyy",
			MediaSize.NA.LETTER),

		new Language ("Espa\u00f1ol (MX)",
			AD_Language_es_MX,  new Locale("es","MX"), Boolean.TRUE, "dd/MM/yyyy",
			MediaSize.NA.LETTER),
		new Language ("Espa\u00f1ol (CL)",
			AD_Language_es_CL,  new Locale("es","CL"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.NA.LETTER),
		new Language ("Espa\u00f1ol (CO)",
			AD_Language_es_CO,  new Locale("es","ES"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.NA.LETTER),
		new Language ("Espa\u00f1ol (VE)",
			AD_Language_es_VE,  new Locale("es","VE"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.NA.LETTER),
		new Language ("Espa\u00f1ol (PA)",
				AD_Language_es_PA,  new Locale("es","PA"), Boolean.TRUE, "dd/MM/yyyy",
				MediaSize.NA.LETTER),
		new Language ("Espa\u00f1ol (EC)",
			AD_Language_es_ES,  new Locale("es","ES"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Espa\u00f1ol (DO)",
			AD_Language_es_DO,  new Locale("es","DO"), Boolean.TRUE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("\u0395\u03bb\u03bb\u03b7\u03bd\u03b9\u03ba\u03ac (GR)",
			AD_Language_el_GR,  new Locale("el","GR"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Farsi",
			AD_Language_fa_IR,  new Locale("fa","IR"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Finnish",
			AD_Language_fi_FI,  new Locale("fi","FI"), Boolean.TRUE, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("Fran\u00e7ais",
			AD_Language_fr_FR,  Locale.FRANCE, Boolean.TRUE, "dd-MM-yyyy",		//  dd.MM.yy
			MediaSize.ISO.A4),
		new Language ("Fran\u00e7ais (CA)",
			AD_Language_fr_CA,  new Locale("fr","CA"), Boolean.FALSE, "MM/dd/yyyy",	// MM/dd/yy
			MediaSize.NA.LETTER),
		new Language ("Hrvatski",
			AD_Language_hr_HR, new Locale("hr", "HR"), null, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("Indonesia Bahasa",
			AD_Language_in_ID, new Locale("in","ID"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Italiano",
			AD_Language_it_IT,  Locale.ITALY, Boolean.TRUE, "dd.MM.yyyy",		//  dd.MM.yy
			MediaSize.ISO.A4),
		new Language ("\u65e5\u672c\u8a9e (JP)",
			AD_Language_ja_JP, Locale.JAPAN, null, null,
			MediaSize.ISO.A4),
		new Language ("Malaysian",
			AD_Language_ms_MY, new Locale("ms","MY"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Magyar (HU)",
			AD_Language_hu_HU,  new Locale("hu","HU"), Boolean.FALSE, "yyyy.MM.dd",
			MediaSize.ISO.A4),
		new Language ("Nederlands",
			AD_Language_nl_NL, new Locale("nl","NL"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Norsk",
			AD_Language_no_NO,  new Locale("no","NO"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Polski",
			AD_Language_pl_PL, new Locale("pl","PL"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Portuguese (BR)",
			AD_Language_pt_BR, new Locale("pt","BR"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Rom\u00e2n\u0103",
			AD_Language_ro_RO, new Locale("ro","RO"), Boolean.FALSE, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("\u0420\u0443\u0441\u0441\u043a\u0438\u0439 (Russian)",
			AD_Language_ru_RU, new Locale("ru","RU"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		new Language ("Slovenski",
			AD_Language_sl_SI, new Locale("sl", "SI"), null, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("\u0421\u0440\u043F\u0441\u043A\u0438 (RS)",
			AD_Language_sr_RS, new Locale("sr", "RS"), null, "dd.MM.yyyy",
			MediaSize.ISO.A4),
		new Language ("Svenska",
			AD_Language_sv_SE,  new Locale("sv","SE"), Boolean.FALSE, "yyyy-MM-dd",
			MediaSize.ISO.A4),
		new Language ("\u0e44\u0e17\u0e22 (TH)",
			AD_Language_th_TH,  new Locale("th","TH"), Boolean.FALSE, "dd/MM/yyyy",
			MediaSize.ISO.A4),
		new Language ("Vi\u1EC7t Nam",
			AD_Language_vi_VN, new Locale("vi","VN"), Boolean.FALSE, "dd-MM-yyyy",
			MediaSize.ISO.A4),
		//	Need to have (Windows) Asian Language Pack installed to view properly
		new Language ("\u7b80\u4f53\u4e2d\u6587 (CN)",
			AD_Language_zh_CN,  Locale.CHINA,  null, "yyyy-MM-dd",
			MediaSize.ISO.A4),
		new Language ("\u7e41\u9ad4\u4e2d\u6587 (TW)",
			AD_Language_zh_TW,  Locale.TAIWAN,  null, null,		//  dd.MM.yy
			MediaSize.ISO.A4)

	};
	/** Default Language            */
	private static Language loginLanguage = languages[0];

	/**	Logger			*/
	private static final Logger log = Logger.getLogger(Language.class.getName());
	
	/**
	 *  Get Number of Languages
	 *  @return Language count
	 */
	public static int getLanguageCount()
	{
		return languages.length;
	}   //  getLanguageCount

	/**
	 *  Get Language
	 *  @param index index
	 *  @return Language
	 */
	public static Language getLanguage (int index)
	{
		if (index < 0 || index >= languages.length)
			return loginLanguage;
		return languages[index];
	}   //  getLanguage

	/**
	 *  Add Language to supported Languages
	 *  @param language new language
	 */
	public static void addLanguage (Language language)
	{
		if (language == null)
			return;
		ArrayList<Language> list = new ArrayList<>(Arrays.asList(languages));
		list.add(language);
		languages = new Language[list.size()];
		list.toArray(languages);
	}   //  addLanguage

	/**************************************************************************
	 *  Get Language.
	 * 	If language does not exist, create it on the fly assuming that it is valid
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return Name (e.g. Deutsch)
	 */
	public static Language getLanguage (String langInfo)
	{
		String lang = langInfo;
		if (lang == null || lang.length() == 0)
			lang = System.getProperty("user.language", "");

		//	Search existing Languages
		for (Language language : languages) {
			if (lang.equals(language.getName())
					|| lang.equals(language.getLanguageCode())
					|| lang.equals(language.getAD_Language()))
				return language;
		}

		//	Create Language on the fly
		if (lang.length() == 5)		//	standard format <language>_<Country>
		{
			String language = lang.substring(0,2);
			String country = lang.substring(3);
			Locale locale = new Locale(language, country);
			log.info ("Adding Language=" + language + ", Country=" + country + ", Locale=" + locale);
			Language ll = new Language (lang, lang, locale);
			//	Add to Languages
			ArrayList<Language> list = new ArrayList<Language>(Arrays.asList(languages));
			list.add(ll);
			languages = new Language [list.size()];
			list.toArray(languages);
			//	Return Language
			return ll;
		}
		//	Get the default one
		return loginLanguage;
	}   //  getLanguage

	/**
	 *  Is it the base language
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return true if base language
	 */
	public static boolean isBaseLanguage (String langInfo)
	{
		return langInfo == null || langInfo.length() == 0
				|| langInfo.equals(languages[0].getName())
				|| langInfo.equals(languages[0].getLanguageCode())
				|| langInfo.equals(languages[0].getAD_Language());
	}   //  isBaseLanguage

	/**
	 *  Get Base Language
	 *  @return Base Language
	 */
	public static Language getBaseLanguage()
	{
		return languages[0];
	}   //  getBase

	/**
	 *  Get Base Language code. (e.g. en-US)
	 *  @return Base Language
	 */
	public static String getBaseAD_Language()
	{
		return languages[0].getAD_Language();
	}   //  getBase

	/**
	 *  Get Supported Locale
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return Supported Locale
	 */
	public static Locale getLocale (String langInfo)
	{
		return getLanguage(langInfo).getLocale();
	}   //  getLocale

	/**
	 *  Get Supported Language
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return AD_Language (e.g. en-US)
	 */
	public static String getAD_Language (String langInfo)
	{
		return getLanguage(langInfo).getAD_Language();
	}   //  getAD_Language

	/**
	 *  Get Supported Language
	 *  @param locale Locale
	 *  @return AD_Language (e.g. en-US)
	 */
	public static String getAD_Language (Locale locale)
	{
		if (locale != null)
		{
			for (Language language : languages) {
				if (locale.getLanguage().equals(language.getLocale().getLanguage()))
					return language.getAD_Language();
			}
		}
		return loginLanguage.getAD_Language();
	}   //  getLocale

	/**
	 *  Get Language Name
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return Language Name (e.g. English)
	 */
	public static String getName (String langInfo)
	{
		return getLanguage(langInfo).getName();
	}   //  getAD_Language

	/**
	 *  Returns true if Decimal Point (not comma)
	 *  @param langInfo either language (en) or locale (en-US) or display name
	 *  @return use of decimal point
	 */
	public static boolean isDecimalPoint(String langInfo)
	{
		return getLanguage(langInfo).isDecimalPoint();
	}   //  getAD_Language

	/**
	 *  Get Display names of supported languages
	 *  @return Array of Language names
	 */
	public static String[] getNames()
	{
		String[] retValue = new String[languages.length];
		for (int i = 0; i < languages.length; i++)
			retValue[i] = languages[i].getName();
		return retValue;
	}   //  getNames

	
	/**************************************************************************
	 *  Get Default Login Language
	 *  @return default Language
	 */
	public static Language getLoginLanguage ()
	{
		return Env.getLanguage(Env.getCtx());
	}

	/**
	 *  Set Default Login Language
	 *  @param language language
	 */
	public static void setLoginLanguage (Language language)
	{
		if (language != null)
		{
			loginLanguage = language;
			log.config(loginLanguage.toString());
		}
	}   //  setLanguage

	
	/**************************************************************************
	 *  Define Language
	 *  @param name - displayed value, e.g. English
	 *  @param AD_Language - the code of system supported language, e.g. en_US
	 *  (might be different than Locale - i.e. if the system does not support the language)
	 *  @param locale - the Locale, e.g. Locale.US
	 *  @param decimalPoint true if Decimal Point - if null, derived from Locale
	 *  @param javaDatePattern Java date pattern as not all locales are defined - if null, derived from Locale
	 *  @param mediaSize default media size
	 */
	public Language (String name, String AD_Language, Locale locale,
		Boolean decimalPoint, String javaDatePattern, MediaSize mediaSize)
	{
		if (name == null || AD_Language == null || locale == null)
			throw new IllegalArgumentException ("Language - parameter is null");
		this.name = name;
		this.language = AD_Language;
		this.locale = locale;
		//
		this.decimalPoint = decimalPoint;
		setDateFormat (javaDatePattern);
		setMediaSize (mediaSize);
	}   //  Language

	/**
	 *  Define Language with A4 and default decimal point and date format
	 *  @param name - displayed value, e.g. English
	 *  @param AD_Language - the code of system supported language, e.g. en_US
	 *  (might be different than Locale - i.e. if the system does not support the language)
	 *  @param locale - the Locale, e.g. Locale.US
	 */
	public Language (String name, String AD_Language, Locale locale)
	{
		this (name, AD_Language, locale, null, null, null);
	}	//	Language


	/**	Name					*/
	private String  name;
	/**	Language (key)			*/
	private String  language;
	/** Locale					*/
	private Locale  locale;
	//
	private Boolean             decimalPoint;
	private Boolean				m_leftToRight;
	private SimpleDateFormat    m_dateFormat;
	private MediaSize 			m_mediaSize = MediaSize.ISO.A4;

	/**
	 *  Get Language Name.
	 *  e.g. English
	 *  @return name
	 */
	public String getName()
	{
		return name;
	}   //  getName

	/**
	 *  Get Application Dictionary Language (system supported).
	 *  e.g. en-US
	 *  @return AD_Language
	 */
	public String getAD_Language()
	{
		return language;
	}   //  getAD_Language

	/**
	 *  Set Application Dictionary Language (system supported).
	 *  @param AD_Language e.g. en-US
	 */
	public void setAD_Language (String AD_Language)
	{
		if (AD_Language != null)
		{
			language = AD_Language;
			log.config(toString());
		}
	}   //  getAD_Language

	/**
	 *  Get Locale
	 *  @return locale
	 */
	public Locale getLocale()
	{
		return locale;
	}   //  getLocale

	/**
	 *  Overwrite Locale
	 *  @param locale locale
	 */
	public void setLocale (Locale locale)
	{
		if (locale == null)
			return;
		this.locale = locale;
		decimalPoint = null;  //  reset
	}   //  getLocale

	/**
	 *  Get Language Code.
	 *  e.g. en - derived from Locale
	 *  @return language code
	 */
	public String getLanguageCode()
	{
		return locale.getLanguage();
	}   //  getLanguageCode

	/**
	 *  Component orientation is Left To Right
	 *  @return true if left-to-right
	 */
	public boolean isLeftToRight()
	{
		if (m_leftToRight == null)
			//  returns true if language not iw, ar, fa, ur
			m_leftToRight = ComponentOrientation.getOrientation(locale).isLeftToRight();
		return m_leftToRight;
	}   //  isLeftToRight

	/**
	 *  Returns true if Decimal Point (not comma)
	 *  @return use of decimal point
	 */
	public boolean isDecimalPoint()
	{
		if (decimalPoint == null)
		{
			DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
			decimalPoint = dfs.getDecimalSeparator() == '.';
		}
		return decimalPoint;
	}   //  isDecimalPoint

	/**
	 * 	Is This the Base Language
	 * 	@return true if base Language
	 */
	public boolean isBaseLanguage()
	{
		return this.equals(getBaseLanguage());
	}	//	isBaseLanguage

	/**
	 *  Set Date Pattern.
	 *  The date format is not checked for correctness
	 *  @param javaDatePattern for details see java.text.SimpleDateFormat,
	 *  format must be able to be converted to database date format by
	 *  using the upper case function.
	 *  It also must have leading zero for day and month.
	 */
	public void setDateFormat (String javaDatePattern)
	{
		if (javaDatePattern == null)
			return;
		m_dateFormat = (SimpleDateFormat)DateFormat.getDateInstance
				(DateFormat.SHORT, locale);
		try
		{
			m_dateFormat.applyPattern(javaDatePattern);
		}
		catch (Exception e)
		{
			log.severe(javaDatePattern + " - " + e);
			m_dateFormat = null;
		}
	}   //  setDateFormat

	/**
	 *  Get (Short) Date Format.
	 *  The date format must parseable by org.compiere.grid.ed.MDocDate
	 *  i.e. leading zero for date and month
	 *  @return date format MM/dd/yyyy - dd.MM.yyyy
	 */
	public SimpleDateFormat getDateFormat()
	{
		if (m_dateFormat == null)
		{
			m_dateFormat = (SimpleDateFormat)DateFormat.getDateInstance
				(DateFormat.SHORT, locale);
			String sFormat = m_dateFormat.toPattern();
			//	some short formats have only one M and/or d (e.g. ths US)
			if (!sFormat.contains("MM") || !sFormat.contains("dd"))
				{
				sFormat = sFormat.replaceFirst("d+", "dd");
				sFormat = sFormat.replaceFirst("M+", "MM");
				m_dateFormat.applyPattern(sFormat);
			}
			//	Unknown short format => use JDBC
			if (m_dateFormat.toPattern().length() != 8)
				m_dateFormat.applyPattern("yyyy-MM-dd");

			//	4 digit year
			if (!m_dateFormat.toPattern().contains("yyyy"))
			{
				sFormat = m_dateFormat.toPattern();
				StringBuilder nFormat = new StringBuilder();
				for (int i = 0; i < sFormat.length(); i++)
				{
					if (sFormat.charAt(i) == 'y')
						nFormat.append("yy");
					else
						nFormat.append(sFormat.charAt(i));
				}
				m_dateFormat.applyPattern(nFormat.toString());
			}
			m_dateFormat.setLenient(true);
		}
		return m_dateFormat;
	}   //  getDateFormat

	/**
	 * 	Get Date Time Format.
	 * 	Used for Display only
	 *  @return Date Time format MMM d, yyyy h:mm:ss a z -or- dd.MM.yyyy HH:mm:ss z
	 *  -or- j nnn aaaa, H' ?????? 'm' ????'
	 */
	public SimpleDateFormat getDateTimeFormat()
	{
		SimpleDateFormat retValue = (SimpleDateFormat)DateFormat.getDateTimeInstance
			(DateFormat.MEDIUM, DateFormat.LONG, locale);
		return retValue;
	}	//	getDateTimeFormat

	/**
	 * 	Get Time Format.
	 * 	Used for Display only
	 *  @return Time format h:mm:ss z or HH:mm:ss z
	 */
	public SimpleDateFormat getTimeFormat()
	{
		return (SimpleDateFormat)DateFormat.getTimeInstance
			(DateFormat.LONG, locale);
	}	//	getTimeFormat

	/**
	 *  Get Database Date Pattern.
	 *  Derive from date pattern (make upper case)
	 *  @return date pattern
	 */
	public String getDBdatePattern()
	{
		return getDateFormat().toPattern().toUpperCase(locale);
	}   //  getDBdatePattern

	/**
	 * 	Get default MediaSize
	 * 	@return media size
	 */
	public MediaSize getMediaSize()
	{
		return m_mediaSize;
	}	//	getMediaSize

	/**
	 * 	Set default MediaSize
	 * 	@param size media size
	 */
	public void setMediaSize (MediaSize size)
	{
		if (size != null)
			m_mediaSize = size;
	}	//	setMediaSize

	/**
	 *  String Representation
	 *  @return string representation
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder("Language=[");
		sb.append(name).append(",Locale=").append(locale.toString())
				.append(",AD_Language=").append(language)
				.append(",DatePattern=").append(getDBdatePattern())
				.append(",DecimalPoint=").append(isDecimalPoint())
				.append("]");
		return sb.toString();
	}   //  toString

	/**
	 * 	Hash Code
	 * 	@return hashcode
	 */
	public int hashCode()
	{
		return language.hashCode();
	}	//	hashcode

	/**
	 * 	Equals.
	 *  Two languages are equal, if they have the same AD_Language
	 * 	@param obj compare
	 * 	@return true if AD_Language is the same
	 */
	public boolean equals(Object obj)
	{
		if (obj instanceof Language)
		{
			Language cmp = (Language)obj;
			if (cmp.getAD_Language().equals(language))
				return true;
		}
		return false;
	}	//	equals

	
	/**************************************************************************
	 * 	Test
	 * 	@param args ignored
	 */
	public static void main(String[] args)
	{
		System.out.println(Locale.TRADITIONAL_CHINESE);
		System.out.println(Locale.TAIWAN);
		System.out.println(Locale.SIMPLIFIED_CHINESE);
		System.out.println(Locale.CHINESE);
		System.out.println(Locale.PRC);
	}

}   //  Language

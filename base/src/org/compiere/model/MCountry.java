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
package org.compiere.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *	Location Country Model (Value Object)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MCountry.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 *   
 *   * @author Michael Judd (Akuna Ltd)
 * 				<li>BF [ 2695078 ] Country is not translated on invoice
 */
public final class MCountry extends X_C_Country
	implements Comparator, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098295201595847612L;


	/**
	 * 	Get Country (cached)
	 * 	@param ctx context
	 *	@param C_Country_ID ID
	 *	@return Country
	 */
	public static MCountry get (Properties ctx, int C_Country_ID)
	{
		if (s_countries == null || s_countries.size() == 0)
			loadAllCountries(ctx);
		String key = String.valueOf(C_Country_ID);
		MCountry c = (MCountry)s_countries.get(key);
		if (c != null)
			return c;
		c = new MCountry (ctx, C_Country_ID, null);
		if (c.getC_Country_ID() == C_Country_ID)
		{
			s_countries.put(key, c);
			return c;
		}
		return null;
	}	//	get

	/**
	 * 	Get Default Country
	 * 	@param ctx context
	 *	@return Country
	 */
	public static MCountry getDefault (Properties ctx)
	{
		if (s_countries == null || s_countries.size() == 0)
			loadAllCountries(ctx);
		return s_default;
	}	//	get

	/**
	 *	Return Countries as Array
	 * 	@param ctx context
	 *  @return MCountry Array
	 */
	@SuppressWarnings("unchecked")
	public static MCountry[] getCountries(Properties ctx)
	{
		if (s_countries == null || s_countries.size() == 0)
			loadAllCountries(ctx);
		MCountry[] retValue = new MCountry[s_countries.size()];
		s_countries.values().toArray(retValue);
		Arrays.sort(retValue, new MCountry(ctx, 0, null));
		return retValue;
	}	//	getCountries

	/**
	 * 	Load Countries.
	 * 	Set Default Language to Client Language
	 *	@param ctx context
	 */
	private static void loadAllCountries (Properties ctx)
	{
		MClient client = MClient.get (ctx);
		MLanguage lang = MLanguage.get(ctx, client.getAD_Language());
		MCountry usa = null;
		//
		s_countries = new CCache<String,MCountry>("C_Country", 250);
		String sql = "SELECT * FROM C_Country WHERE IsActive='Y'";
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MCountry c = new MCountry (ctx, rs, null);
				s_countries.put(String.valueOf(c.getC_Country_ID()), c);
				//	Country code of Client Language
				if (lang != null && lang.getCountryCode().equals(c.getCountryCode()))
					s_default = c;
				if (c.getC_Country_ID() == 100)		//	USA
					usa = c;
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		if (s_default == null)
			s_default = usa;
		s_log.fine("#" + s_countries.size() 
			+ " - Default=" + s_default);
	}	//	loadAllCountries

	/**
	 *	Return Language
	 *  @return Name
	 */
	private String getEnvLanguage() {
		String lang = Env.getAD_Language(Env.getCtx());
		if (Language.isBaseLanguage(lang))
			return null;
		return lang;
	}

	/**
	 * 	Set the Language for Display (toString)
	 *	@param AD_Language language or null
	 *  @deprecated - not used at all, you can delete references to this method
	 */
	public static void setDisplayLanguage (String AD_Language)
	{
		s_AD_Language = AD_Language;
		if (Language.isBaseLanguage(AD_Language))
			s_AD_Language = null;
	}	//	setDisplayLanguage
	
	/**	Display Language				*/
	private static String		s_AD_Language = null;
	
	/**	Country Cache					*/
	private static CCache<String,MCountry>	s_countries = null;
	/**	Default Country 				*/
	private static MCountry		s_default = null;
	/**	Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (MCountry.class);
	//	Default DisplaySequence	*/
	private static String		DISPLAYSEQUENCE = "@C@, @P@";

	
	/*************************************************************************
	 *	Create empty Country
	 * 	@param ctx context
	 * 	@param C_Country_ID ID
	 *	@param trxName transaction
	 */
	public MCountry (Properties ctx, int C_Country_ID, String trxName)
	{
		super (ctx, C_Country_ID, trxName);
		if (C_Country_ID == 0)
		{
		//	setName (null);
		//	setCountryCode (null);
			setDisplaySequence(DISPLAYSEQUENCE);
			setHasRegion(false);
			setHasPostal_Add(false);
			setIsAddressLinesLocalReverse (false);
			setIsAddressLinesReverse (false);
		}
	}   //  MCountry

	/**
	 *	Create Country from current row in ResultSet
	 * 	@param ctx context
	 *  @param rs ResultSet
	 *	@param trxName transaction
	 */
	public MCountry (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCountry

	/**	Translated Name			*/
	private String	m_trlName = null;
	
	/**
	 *	Return Name - translated if DisplayLanguage is set.
	 *  @return Name
	 */
	public String toString()
	{
		if (getEnvLanguage() != null)
		{
			String nn = getTrlName();
			if (nn != null)
				return nn;
		}
		return getName();
	}   //  toString

	/**
	 * 	Get Translated Name
	 *	@return name
	 */
	public String getTrlName()
	{
		if (m_trlName == null && getEnvLanguage() != null)
		{
			m_trlName = get_Translation(COLUMNNAME_Name, getEnvLanguage());
			if (m_trlName == null)
				m_trlName = getName();
		}
		return m_trlName;
	}	//	getTrlName
	
	/**
	 * 	Get Translated Name
	 *  @param language 
	 *	@return name
	 */
	public String getTrlName(String language)
	{
		if ( language != null)
		{
			m_trlName = get_Translation(COLUMNNAME_Name, language);
		}
		return m_trlName;
	}	//	getTrlName
	
	
	/**
	 * 	Get Display Sequence
	 *	@return display sequence
	 */
	public String getDisplaySequence ()
	{
		String ds = super.getDisplaySequence ();
		if (ds == null || ds.length() == 0)
			ds = DISPLAYSEQUENCE;
		return ds;
	}	//	getDisplaySequence

	/**
	 * 	Get Local Display Sequence.
	 * 	If not defined get Display Sequence
	 *	@return local display sequence
	 */
	public String getDisplaySequenceLocal ()
	{
		String ds = super.getDisplaySequenceLocal();
		if (ds == null || ds.length() == 0)
			ds = getDisplaySequence();
		return ds;
	}	//	getDisplaySequenceLocal
	
	/**
	 *  Compare based on Name
	 *  @param o1 object 1
	 *  @param o2 object 2
	 *  @return -1,0, 1
	 */
	public int compare(Object o1, Object o2)
	{
		String s1 = o1.toString();
		if (s1 == null)
			s1 = "";
		String s2 = o2.toString();
		if (s2 == null)
			s2 = "";
		return s1.compareTo(s2);
	}	//	compare

	/**
	 * 	Is the region valid in the country
	 *	@param C_Region_ID region
	 *	@return true if valid
	 */
	public boolean isValidRegion(int C_Region_ID)
	{
		if (C_Region_ID == 0 
			|| getC_Country_ID() == 0
			|| !isHasRegion())
			return false;
		MRegion[] regions = MRegion.getRegions(getCtx(), getC_Country_ID());
		for (int i = 0; i < regions.length; i++)
		{
			if (C_Region_ID == regions[i].getC_Region_ID())
				return true;
		}
		return false;
	}	//	isValidRegion

	/**************************************************************************
	 * 	Insert Countries
	 * 	@param args none
	 */
	public static void main (String[] args)
	{
		/**	Migration before
		UPDATE C_Country SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID<>0 OR AD_Org_ID<>0;
		UPDATE C_Region SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID<>0 OR AD_Org_ID<>0;
		IDs migration for C_Location, C_City, C_Tax (C_Country, C_Region)
		**
		//	from http://www.iso.org/iso/en/prods-services/iso3166ma/02iso-3166-code-lists/list-en1-semic.txt
		String countries = "AFGHANISTAN;AF, ALBANIA;AL, ALGERIA;DZ, AMERICAN SAMOA;AS, ANDORRA;AD, ANGOLA;AO, ANGUILLA;AI, ANTARCTICA;AQ, ANTIGUA AND BARBUDA;AG, ARGENTINA;AR,"
			+ "ARMENIA;AM, ARUBA;AW, AUSTRALIA;AU, AUSTRIA;AT, AZERBAIJAN;AZ, BAHAMAS;BS, BAHRAIN;BH, BANGLADESH;BD, BARBADOS;BB, BELARUS;BY, BELGIUM;BE, BELIZE;BZ,"
			+ "BENIN;BJ, BERMUDA;BM, BHUTAN;BT, BOLIVIA;BO, BOSNIA AND HERZEGOVINA;BA, BOTSWANA;BW, BOUVET ISLAND;BV, BRAZIL;BR, BRITISH INDIAN OCEAN TERRITORY;IO, BRUNEI DARUSSALAM;BN,"
			+ "BULGARIA;BG, BURKINA FASO;BF, BURUNDI;BI, CAMBODIA;KH, CAMEROON;CM, CANADA;CA, CAPE VERDE;CV, CAYMAN ISLANDS;KY, CENTRAL AFRICAN REPUBLIC;CF, CHAD;TD, CHILE;CL,"
			+ "CHINA;CN, CHRISTMAS ISLAND;CX, COCOS (KEELING) ISLANDS;CC, COLOMBIA;CO, COMOROS;KM, CONGO;CG, CONGO THE DEMOCRATIC REPUBLIC OF THE;CD, COOK ISLANDS;CK,"
			+ "COSTA RICA;CR, COTE D'IVOIRE;CI, CROATIA;HR, CUBA;CU, CYPRUS;CY, CZECH REPUBLIC;CZ, DENMARK;DK, DJIBOUTI;DJ, DOMINICA;DM, DOMINICAN REPUBLIC;DO, ECUADOR;EC,"
			+ "EGYPT;EG, EL SALVADOR;SV, EQUATORIAL GUINEA;GQ, ERITREA;ER, ESTONIA;EE, ETHIOPIA;ET, FALKLAND ISLANDS (MALVINAS);FK, FAROE ISLANDS;FO, FIJI;FJ,"
			+ "FINLAND;FI, FRANCE;FR, FRENCH GUIANA;GF, FRENCH POLYNESIA;PF, FRENCH SOUTHERN TERRITORIES;TF, GABON;GA, GAMBIA;GM, GEORGIA;GE, GERMANY;DE, GHANA;GH,"
			+ "GIBRALTAR;GI, GREECE;GR, GREENLAND;GL, GRENADA;GD, GUADELOUPE;GP, GUAM;GU, GUATEMALA;GT, GUINEA;GN, GUINEA-BISSAU;GW, GUYANA;GY, HAITI;HT,"
			+ "HEARD ISLAND AND MCDONALD ISLANDS;HM, HOLY SEE (VATICAN CITY STATE);VA, HONDURAS;HN, HONG KONG;HK, HUNGARY;HU, ICELAND;IS, INDIA;IN, INDONESIA;ID,"
			+ "IRAN ISLAMIC REPUBLIC OF;IR, IRAQ;IQ, IRELAND;IE, ISRAEL;IL, ITALY;IT, JAMAICA;JM, JAPAN;JP, JORDAN;JO, KAZAKHSTAN;KZ, KENYA;KE, KIRIBATI;KI, KOREA DEMOCRATIC PEOPLE'S REPUBLIC OF;KP,"
			+ "KOREA REPUBLIC OF;KR, KUWAIT;KW, KYRGYZSTAN;KG, LAO PEOPLE'S DEMOCRATIC REPUBLIC;LA, LATVIA;LV, LEBANON;LB, LESOTHO;LS, LIBERIA;LR, LIBYAN ARAB JAMAHIRIYA;LY,"
			+ "LIECHTENSTEIN;LI, LITHUANIA;LT, LUXEMBOURG;LU, MACAO;MO, MACEDONIA FORMER YUGOSLAV REPUBLIC OF;MK, MADAGASCAR;MG, MALAWI;MW, MALAYSIA;MY, MALDIVES;MV, "
			+ "MALI;ML, MALTA;MT, MARSHALL ISLANDS;MH, MARTINIQUE;MQ, MAURITANIA;MR, MAURITIUS;MU, MAYOTTE;YT, MEXICO;MX, MICRONESIA FEDERATED STATES OF;FM,"
			+ "MOLDOVA REPUBLIC OF;MD, MONACO;MC, MONGOLIA;MN, MONTSERRAT;MS, MOROCCO;MA, MOZAMBIQUE;MZ, MYANMAR;MM, NAMIBIA;NA, NAURU;NR, NEPAL;NP,"
			+ "NETHERLANDS;NL, NETHERLANDS ANTILLES;AN, NEW CALEDONIA;NC, NEW ZEALAND;NZ, NICARAGUA;NI, NIGER;NE, NIGERIA;NG, NIUE;NU, NORFOLK ISLAND;NF,"
			+ "NORTHERN MARIANA ISLANDS;MP, NORWAY;NO, OMAN;OM, PAKISTAN;PK, PALAU;PW, PALESTINIAN TERRITORY OCCUPIED;PS, PANAMA;PA, PAPUA NEW GUINEA;PG,"
			+ "PARAGUAY;PY, PERU;PE, PHILIPPINES;PH, PITCAIRN;PN, POLAND;PL, PORTUGAL;PT, PUERTO RICO;PR, QATAR;QA, REUNION;RE, ROMANIA;RO, RUSSIAN FEDERATION;RU,"
			+ "RWANDA;RW, SAINT HELENA;SH, SAINT KITTS AND NEVIS;KN, SAINT LUCIA;LC, SAINT PIERRE AND MIQUELON;PM, SAINT VINCENT AND THE GRENADINES;VC,"
			+ "SAMOA;WS, SAN MARINO;SM, SAO TOME AND PRINCIPE;ST, SAUDI ARABIA;SA, SENEGAL;SN, SEYCHELLES;SC, SIERRA LEONE;SL, SINGAPORE;SG, SLOVAKIA;SK,"
			+ "SLOVENIA;SI, SOLOMON ISLANDS;SB, SOMALIA;SO, SOUTH AFRICA;ZA, SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS;GS, SPAIN;ES, SRI LANKA;LK,"
			+ "SUDAN;SD, SURINAME;SR, SVALBARD AND JAN MAYEN;SJ, SWAZILAND;SZ, SWEDEN;SE, SWITZERLAND;CH, SYRIAN ARAB REPUBLIC;SY, TAIWAN;TW,"
			+ "TAJIKISTAN;TJ, TANZANIA UNITED REPUBLIC OF;TZ, THAILAND;TH, TIMOR-LESTE;TL, TOGO;TG, TOKELAU;TK, TONGA;TO, TRINIDAD AND TOBAGO;TT,"
			+ "TUNISIA;TN, TURKEY;TR, TURKMENISTAN;TM, TURKS AND CAICOS ISLANDS;TC, TUVALU;TV, UGANDA;UG, UKRAINE;UA, UNITED ARAB EMIRATES;AE, UNITED KINGDOM;GB,"
			+ "UNITED STATES;US, UNITED STATES MINOR OUTLYING ISLANDS;UM, URUGUAY;UY, UZBEKISTAN;UZ, VANUATU;VU, VENEZUELA;VE, VIET NAM;VN, VIRGIN ISLANDS BRITISH;VG,"
			+ "VIRGIN ISLANDS U.S.;VI, WALLIS AND FUTUNA;WF, WESTERN SAHARA;EH, YEMEN;YE, YUGOSLAVIA;YU, ZAMBIA;ZM, ZIMBABWE;ZW";
		//
		org.compiere.Adempiere.startupClient();
		StringTokenizer st = new StringTokenizer(countries, ",", false);
		while (st.hasMoreTokens())
		{
			String s = st.nextToken().trim();
			int pos = s.indexOf(';');
			String name = Util.initCap(s.substring(0,pos));
			String cc = s.substring(pos+1);
			System.out.println(cc + " - " + name);
			//
			MCountry mc = new MCountry(Env.getCtx(), 0);
			mc.setCountryCode(cc);
			mc.setName(name);
			mc.setDescription(name);
			mc.saveEx();
		}
		**/
	}	//	main

}	//	MCountry

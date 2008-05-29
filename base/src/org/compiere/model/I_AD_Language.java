/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Language
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_AD_Language 
{

    /** TableName=AD_Language */
    public static final String Table_Name = "AD_Language";

    /** AD_Table_ID=111 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

    /** Column name AD_Language_ID */
    public static final String COLUMNNAME_AD_Language_ID = "AD_Language_ID";

	/** Set Language ID	  */
	public void setAD_Language_ID (int AD_Language_ID);

	/** Get Language ID	  */
	public int getAD_Language_ID();

    /** Column name CountryCode */
    public static final String COLUMNNAME_CountryCode = "CountryCode";

	/** Set ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode);

	/** Get ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode();

    /** Column name DatePattern */
    public static final String COLUMNNAME_DatePattern = "DatePattern";

	/** Set Date Pattern.
	  * Java Date Pattern
	  */
	public void setDatePattern (String DatePattern);

	/** Get Date Pattern.
	  * Java Date Pattern
	  */
	public String getDatePattern();

    /** Column name IsBaseLanguage */
    public static final String COLUMNNAME_IsBaseLanguage = "IsBaseLanguage";

	/** Set Base Language.
	  * The system information is maintained in this language
	  */
	public void setIsBaseLanguage (boolean IsBaseLanguage);

	/** Get Base Language.
	  * The system information is maintained in this language
	  */
	public boolean isBaseLanguage();

    /** Column name IsDecimalPoint */
    public static final String COLUMNNAME_IsDecimalPoint = "IsDecimalPoint";

	/** Set Decimal Point.
	  * The number notation has a decimal point (no decimal comma)
	  */
	public void setIsDecimalPoint (boolean IsDecimalPoint);

	/** Get Decimal Point.
	  * The number notation has a decimal point (no decimal comma)
	  */
	public boolean isDecimalPoint();

    /** Column name IsSystemLanguage */
    public static final String COLUMNNAME_IsSystemLanguage = "IsSystemLanguage";

	/** Set System Language.
	  * The screens, etc. are maintained in this Language
	  */
	public void setIsSystemLanguage (boolean IsSystemLanguage);

	/** Get System Language.
	  * The screens, etc. are maintained in this Language
	  */
	public boolean isSystemLanguage();

    /** Column name LanguageISO */
    public static final String COLUMNNAME_LanguageISO = "LanguageISO";

	/** Set ISO Language Code.
	  * Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public void setLanguageISO (String LanguageISO);

	/** Get ISO Language Code.
	  * Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public String getLanguageISO();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TimePattern */
    public static final String COLUMNNAME_TimePattern = "TimePattern";

	/** Set Time Pattern.
	  * Java Time Pattern
	  */
	public void setTimePattern (String TimePattern);

	/** Get Time Pattern.
	  * Java Time Pattern
	  */
	public String getTimePattern();
}

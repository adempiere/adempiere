/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2026 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.compiere.print;

import java.text.Normalizer;

import org.compiere.model.MTable;
import org.compiere.util.Env;

/**
 * Semantic aliases for the lookup tables joined by the report SQL.
 * <p>
 * A lookup is aliased as <code>&lt;Word&gt;_&lt;n&gt;</code> and its translation table as
 * <code>&lt;Word&gt;_Trl_&lt;n&gt;</code>, where n is the number of the lookup in the report
 * (1-based, in print format item order) and Word is the name of the table in the dictionary
 * (see {@link #word(String)}). Aliases have at most {@link #MAX_LENGTH} characters
 * (Oracle before 12.2); when the name is longer the word is truncated and the numeric suffix
 * is kept, so aliases stay unique inside one statement.
 *
 * @author Victor Perez, victor.perez@e-evolution.com, http://www.e-evolution.com
 */
public final class ReportLookupAlias
{
	/** Maximum alias length (Oracle before 12.2) */
	public static final int MAX_LENGTH = 30;

	private ReportLookupAlias()
	{
	}

	/**
	 * Alias of a lookup table
	 * @param tableName lookup table name
	 * @param n lookup number (1-based)
	 * @param baseTable table of the report, the alias is never equal to it
	 * @return alias in the form Word_n
	 */
	public static String lookup(String tableName, int lookupNumber, String baseTable)
	{
		return build(tableName, "_" + lookupNumber, baseTable);
	}

	/**
	 * Alias of a nested lookup table
	 * @param tableName lookup table name
	 * @param path lookup path: number of the top level lookup, then the number of the nested lookup in its
	 * parent, and so on (3_1_2)
	 * @param baseTable table of the report, the alias is never equal to it
	 * @return alias in the form Word_n_m (Word_n_m_k deeper)
	 */
	public static String lookup(String tableName, String path, String baseTable)
	{
		return build(tableName, "_" + path, baseTable);
	}

	/**
	 * Alias of the translation table of a nested lookup table
	 * @param tableName lookup table name (not the _Trl table)
	 * @param path lookup path, see {@link #lookup(String, String, String)}
	 * @param baseTable table of the report, the alias is never equal to it
	 * @return alias in the form Word_Trl_n_m
	 */
	public static String translation(String tableName, String path, String baseTable)
	{
		return build(tableName, "_Trl_" + path, baseTable);
	}

	/**
	 * Alias of the translation table of a lookup table
	 * @param tableName lookup table name (not the _Trl table)
	 * @param n lookup number (1-based)
	 * @param baseTable table of the report, the alias is never equal to it
	 * @return alias in the form Word_Trl_n
	 */
	public static String translation(String tableName, int lookupNumber, String baseTable)
	{
		return build(tableName, "_Trl_" + lookupNumber, baseTable);
	}

	/**
	 * The word that names a lookup table in the alias: the one rule to change if the naming changes.
	 * It is the name of the table in the dictionary (base language, from the cached MTable) in CamelCase;
	 * Business Partner gives BusinessPartner. The dictionary is in English. Only when nothing
	 * usable remains the table name without its prefix is used.
	 * @param tableName table name
	 * @return word of the alias
	 */
	public static String word(String tableName)
	{
		MTable table = MTable.get(Env.getCtx(), tableName);
		return word(tableName, table != null && table.get_ID() > 0 ? table.getName() : null);
	}

	/**
	 * Word of the alias from the dictionary name of the table: accents folded, split on every character
	 * that is not a letter or digit, first letter of each word in upper case (User/Contact gives
	 * UserContact, UOM gives UOM). When nothing usable remains, the table name without its prefix up to
	 * the first underscore is used (C_BPartner_Location gives BPartner_Location).
	 * @param tableName table name
	 * @param dictionaryName AD_Table.Name or null
	 * @return word of the alias
	 */
	public static String word(String tableName, String dictionaryName)
	{
		String camel = camelCase(dictionaryName);
		if (camel.length() > 0 && isAsciiLetter(camel.charAt(0)))
			return camel;
		int index = tableName.indexOf('_');
		if (index < 0 || index == tableName.length() - 1)
			return tableName;
		return tableName.substring(index + 1);
	}

	private static String camelCase(String name)
	{
		if (name == null)
			return "";
		String folded = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
		StringBuilder camel = new StringBuilder();
		for (String part : folded.split("[^A-Za-z0-9]+"))
		{
			if (part.length() == 0)
				continue;
			camel.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
		}
		return camel.toString();
	}

	private static boolean isAsciiLetter(char character)
	{
		return (character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z');
	}

	private static String build(String tableName, String suffix, String baseTable)
	{
		String word = word(tableName);
		String alias = truncate(word, suffix);
		if (alias.equalsIgnoreCase(baseTable))
			alias = truncate(word, "_" + suffix);
		return alias;
	}

	private static String truncate(String word, String suffix)
	{
		int maximumLength = MAX_LENGTH - suffix.length();
		if (word.length() > maximumLength)
			word = word.substring(0, maximumLength);
		return word + suffix;
	}
}	//	ReportLookupAlias

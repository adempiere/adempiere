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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Properties;

/**
 *  Decimal Pattern support for integer counters (serial numbers and document
 *  numbers, both driven by the AD_Element <code>DecimalPattern</code>).
 *
 *  <p>A decimal pattern here means a {@link DecimalFormat} pattern that renders
 *  an <b>integer</b> counter, e.g. {@code 0000} formats 23 as {@code 0023}.
 *  Constructs that alter or lose the counter value are rejected, because two
 *  different counter values must never render to the same serial number:
 *  <ul>
 *    <li>{@code E} - scientific notation rounds to the mantissa precision, so
 *        distinct counters collide at a point that depends on the pattern and
 *        cannot be bounded by probing: {@code 0E0} collides at 10/11,
 *        {@code 0.0E0} at 100/101, {@code 0.00E0} at 1000/1001.</li>
 *    <li>{@code .} - a fraction separator emits fraction digits for an integer
 *        counter and displaces the separator the author meant as a literal:
 *        {@code SN.0000} renders 23 as {@code SN23.0000}.</li>
 *    <li>{@code %} and {@code &#8240;} - scale the value by 100 / 1000:
 *        {@code %0} renders 23 as {@code %2300}.</li>
 *  </ul>
 *  Grouping ({@code #,##0}), a negative subpattern ({@code 0;(#0)}), the
 *  currency sign and quoted literals are kept: they do not change the value.
 *
 *  <p>The number symbols come from the locale of the user's context language
 *  ({@link Env#getLanguage(Properties)}), not from the JVM default locale, so
 *  the same pattern renders consistently for that user on any server.
 *
 *  @author e-Evolution
 *  @see <a href="https://github.com/adempiere/adempiere/pull/4447">#4447</a>
 */
public final class DecimalPatternUtil
{
	/** Utility class - no instances. */
	private DecimalPatternUtil()
	{
	}	//	DecimalPatternUtil

	/**
	 * 	Locale of the language in the user's context.
	 * 	Resolved from the in-memory language table, so this is safe to call
	 * 	from the document number hot path.
	 *	@param ctx context
	 *	@return locale, never null
	 */
	public static Locale getLocale (Properties ctx)
	{
		Language language = Env.getLanguage (ctx);
		Locale locale = language != null ? language.getLocale () : null;
		return locale != null ? locale : Locale.getDefault ();
	}	//	getLocale

	/**
	 * 	Create a formatter for an integer counter, using the number symbols of
	 * 	the user's context language.
	 *	@param ctx context
	 *	@param decimalPattern DecimalFormat pattern, not empty
	 *	@return formatter
	 *	@throws IllegalArgumentException if the pattern is malformed
	 */
	public static DecimalFormat create (Properties ctx, String decimalPattern)
	{
		return new DecimalFormat (decimalPattern, new DecimalFormatSymbols (getLocale (ctx)));
	}	//	create

	/**
	 * 	Format an integer counter with the given pattern.
	 *	@param ctx context
	 *	@param decimalPattern DecimalFormat pattern, may be null or empty
	 *	@param number counter value
	 *	@return formatted number; the plain number if there is no pattern
	 */
	public static String format (Properties ctx, String decimalPattern, int number)
	{
		if (decimalPattern == null || decimalPattern.length() == 0)
			return String.valueOf (number);
		return stripBidiControls (create (ctx, decimalPattern).format (number));
	}	//	format

	/**
	 * 	Remove invisible bidirectional formatting controls from a rendered
	 * 	identifier.
	 *
	 * 	<p>{@link DecimalFormat} emits U+061C ARABIC LETTER MARK in front of a
	 * 	literal separator when the symbols locale is right-to-left, so under
	 * 	{@code ar_EG} the pattern {@code SN-0000} renders as
	 * 	{@code SN\u061C-\u0660\u0660\u0662\u0663} (8 characters, not 7). A serial
	 * 	number is matched exactly by lookups, barcodes and integrations, so an
	 * 	invisible character there silently breaks equality while the value looks
	 * 	identical on screen. The visible digits and separators are kept.
	 *
	 *	@param text rendered text, may be null
	 *	@return text without bidi control characters
	 */
	public static String stripBidiControls (String text)
	{
		if (text == null || text.length() == 0)
			return text;

		StringBuilder sb = new StringBuilder (text.length());
		for (int i = 0; i < text.length(); i++)
		{
			char ch = text.charAt (i);
			//	ALM, LRM, RLM, bidi embeddings/overrides, bidi isolates
			if (ch == 0x061C || ch == 0x200E || ch == 0x200F
					|| (ch >= 0x202A && ch <= 0x202E)
					|| (ch >= 0x2066 && ch <= 0x2069))
				continue;
			sb.append (ch);
		}
		return sb.toString();
	}	//	stripBidiControls

	/**
	 * 	Check that a pattern can render an integer counter without altering or
	 * 	losing its value.
	 *
	 * 	<p>The pattern is parsed with the number symbols of the caller's context
	 * 	language, the same locale {@link #format} renders with, so validation and
	 * 	rendering can never disagree about what a pattern means.
	 *
	 * 	@param ctx context, used to resolve the number symbols
	 * 	@param decimalPattern pattern to check, may be null or empty
	 * 	@return null when the pattern is usable, otherwise the
	 * 			{@code AD_Message} key naming the offending construct
	 */
	public static String validate (Properties ctx, String decimalPattern)
	{
		if (decimalPattern == null || decimalPattern.length() == 0)
			return null;

		//	Must at least parse, with the symbols it will be rendered with
		try
		{
			new DecimalFormat (decimalPattern, new DecimalFormatSymbols (getLocale (ctx)));
		}
		catch (IllegalArgumentException e)
		{
			return MSG_MALFORMED;
		}

		//	Scan outside quoted literals. Pattern syntax itself is locale
			//	independent: '.' and ',' always mean decimal and grouping, the locale
			//	only decides what is emitted (under de_DE the pattern SN.0000 renders
			//	23 as "SN23,0000"), so the ASCII constructs below are the right ones
			//	to reject regardless of the active locale.
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		boolean hasDigitPlaceholder = false;
		for (int i = 0; i < decimalPattern.length(); i++)
		{
			char ch = decimalPattern.charAt (i);
			if (ch == '\'')
			{
				//	A doubled quote inside a quoted literal is an escaped quote
				if (inSingleQuote && i + 1 < decimalPattern.length()
						&& decimalPattern.charAt (i + 1) == '\'')
					i++;
				else
					inSingleQuote = !inSingleQuote;
				continue;
			}
			if (ch == '"')
			{
				inDoubleQuote = !inDoubleQuote;
				continue;
			}
			if (inSingleQuote || inDoubleQuote)
				continue;

			if (ch == '0' || ch == '#')
				hasDigitPlaceholder = true;
			else if (ch == 'E' || ch == 'e')
				return MSG_SCIENTIFIC;
			else if (ch == '.')
				return MSG_FRACTION;
			else if (ch == '%' || ch == '\u2030')
				return MSG_PERCENT;
		}

		if (!hasDigitPlaceholder)
			return MSG_NO_DIGIT;

		return null;
	}	//	validate

	/**
	 * 	Convenience wrapper around {@link #validate(Properties, String)}.
	 * 	@param decimalPattern pattern to check, may be null or empty
	 * 	@return true when the pattern is usable
	 */
	public static boolean isSupported (Properties ctx, String decimalPattern)
	{
		return validate (ctx, decimalPattern) == null;
	}	//	isSupported

	/**
	 * 	Build the message shown to the user when a pattern is refused.
	 *
	 *  <p>Every part of the text is resolved in the language of the context, so
	 *  the user never sees a label in one language glued to a reason in another.
	 *  For a malformed pattern the parser's own detail is appended: it is a
	 *  technical diagnostic coming from the JDK, not part of the message catalog.
	 *
	 * 	@param ctx context
	 * 	@param decimalPattern the rejected pattern
	 * 	@return localized reason, or null when the pattern is usable
	 */
	public static String getValidationError (Properties ctx, String decimalPattern)
	{
		String key = validate (ctx, decimalPattern);
		if (key == null)
			return null;

		String text = Msg.getMsg (ctx, key);
		if (MSG_MALFORMED.equals (key))
		{
			try
			{
				new DecimalFormat (decimalPattern, new DecimalFormatSymbols (getLocale (ctx)));
			}
			catch (IllegalArgumentException e)
			{
				text = text + " - " + e.getMessage ();
			}
		}
		return text;
	}	//	getValidationError

	/** AD_Message key: scientific notation collapses distinct counters. */
	public static final String MSG_SCIENTIFIC = "DecimalPatternScientific";
	/** AD_Message key: a fraction separator on an integer counter. */
	public static final String MSG_FRACTION = "DecimalPatternFractionSeparator";
	/** AD_Message key: percent or per-mille scales the counter. */
	public static final String MSG_PERCENT = "DecimalPatternPercent";
	/** AD_Message key: no digit placeholder at all. */
	public static final String MSG_NO_DIGIT = "DecimalPatternNoDigit";
	/** AD_Message key: the pattern does not parse. */
	public static final String MSG_MALFORMED = "DecimalPatternMalformed";

}	//	DecimalPatternUtil

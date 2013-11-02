
 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP		   			          *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.util;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.MessageFormat;

import org.adempiere.util.api.IMsgBL;

/**
 * 
 *
 */
public final class Check
{
	private static Class<? extends RuntimeException> defaultExClazz = RuntimeException.class;

	private Check()
	{
		super();
	}

	/**
	 * Set the class of exceptions to be thrown by this classe's methods. Usually ADempiere will call this method on startup, setting <code>org.adempiere.exceptions.AdempiereException</code>.
	 * 
	 * @param clazz
	 */
	public static void setDefaultExClass(Class<? extends RuntimeException> clazz)
	{
		defaultExClazz = clazz;
	}

	private static RuntimeException mkEx(final Class<? extends RuntimeException> exClazz, final String msg)
	{
		try
		{
			final Constructor<? extends RuntimeException> c = exClazz.getConstructor(String.class);
			final RuntimeException ex = c.newInstance(msg);
			return ex;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failure throwing exception with class '" + exClazz + "' and message '" + msg + "'", e);
		}
	}

	/**
	 * Little method that throws an {@link AdempiereException} if the given boolean condition is false. It might be a good idea to use "assume" instead of the assert keyword, because <li>assert is
	 * globally switched on and off and you never know what else libs are using assert</li> <li>there are critical assumptions that should always be validated. Not only during development time or when
	 * someone minds to use the -ea cmdline parameter</li>
	 * 
	 * @param cond
	 * @param errMsg the error message to pass to the assertion error, if the condition is <code>false</code>
	 * @param params message parameters (@see {@link MessageFormat})
	 */
	public static void assume(final boolean cond, final String errMsg, Object... params)
	{
		if (!cond)
		{
			final String errMsgFormated = Services.get(IMsgBL.class).formatMessage(errMsg, params);
			throw mkEx(defaultExClazz, "Assumption failure: " + errMsgFormated);
		}
	}

	/**
	 * Assumes that given <code>object</code> is not null
	 * 
	 * @param object
	 * @param assumptionMessage message
	 * @param params message parameters (@see {@link MessageFormat})
	 * @see #assume(boolean, String, Object...)
	 */
	public static void assumeNotNull(Object object, final String assumptionMessage, Object... params)
	{
		final boolean cond = object != null;
		assume(cond, assumptionMessage, params);
	}

	/**
	 * Assumes that given <code>object</code> is null
	 * 
	 * @param object
	 * @param assumptionMessage message
	 * @param params message parameters (@see {@link MessageFormat})
	 * @see #assume(boolean, String, Object...)
	 */
	public static void assumeNull(Object object, final String assumptionMessage, Object... params)
	{
		final boolean cond = object == null;
		assume(cond, assumptionMessage, params);
	}

	/**
	 * This method similar to {@link #assume(boolean, String, Object...)}, but the message should be formulated in terms of an error message instead of an assumption.
	 * <p>
	 * Example: instead of "parameter 'xy' is not null" (description of the assumption that was violated), one should write "parameter 'xy' is null" (description of the error).
	 * 
	 * @param cond
	 * @param errMsg
	 * @param params
	 */
	public static void errorUnless(final boolean cond, final String errMsg, Object... params)
	{
		if (!cond)
		{
			final String errMsgFormated = Services.get(IMsgBL.class).formatMessage(errMsg, params);
			throw mkEx(defaultExClazz, "Error: " + errMsgFormated);
		}
	}

	/**
	 * This method similar to {@link #assume(boolean, String, Object...)}, the error is throw <b>if the condition is true</b> and the message should be formulated in terms of an error message instead
	 * of an assumption.
	 * <p>
	 * Example: instead of "parameter 'xy' is not null" (description of the assumption that was violated), one should write "parameter 'xy' is null" (description of the error).
	 * 
	 * @param cond
	 * @param errMsg
	 * @param params
	 */
	public static void errorIf(final boolean cond, final String errMsg, Object... params)
	{
		if (cond)
		{
			final String errMsgFormated = Services.get(IMsgBL.class).formatMessage(errMsg, params);
			throw mkEx(defaultExClazz, "Error: " + errMsgFormated);
		}
	}

	public static boolean isEmpty(String str)
	{
		return isEmpty(str, false);
	}

	/**
	 * Is String Empty
	 * 
	 * @param str string
	 * @param trimWhitespaces trim whitespaces
	 * @return true if >= 1 char
	 */
	public static boolean isEmpty(String str, boolean trimWhitespaces)
	{
		if (str == null)
			return true;
		if (trimWhitespaces)
			return str.trim().length() == 0;
		else
			return str.length() == 0;
	}	// isEmpty

	/**
	 * 
	 * @param bd
	 * @return true if bd is null or bd.signum() is zero
	 */
	public static boolean isEmpty(BigDecimal bd)
	{
		return bd == null || bd.signum() == 0;
	}

	public static <T> boolean isEmpty(T[] arr)
	{
		return arr == null || arr.length == 0;
	}

}

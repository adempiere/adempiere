package org.adempiere.util;

public final class StringUtils
{
	private StringUtils()
	{
	}

	/**
	 * Truncate string to a given length.
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static final String trunc(final String str, final int length)
	{
		if (str == null)
		{
			return str;
		}

		if (str.length() <= length)
		{
			return str;
		}

		return str.substring(0, length);
	}

}

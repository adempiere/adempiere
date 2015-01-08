package org.adempiere.util.api.impl;

import java.text.MessageFormat;
import java.util.Properties;

import org.adempiere.util.api.IMsgBL;


public class PlainMsgBL implements IMsgBL
{

	@Override
	public String getMsg(String language, String message, Object[] params)
	{
		return language + "_" + message + "_" + params;
	}

	@Override
	public String parseTranslation(Properties ctx, String message)
	{
		return message;
	}

	@Override
	public final String formatMessage(String message, Object... params)
	{
		String messageFormated;
		if (params != null && params.length > 0)
		{
			try
			{
				messageFormated = MessageFormat.format(message, params);
			}
			catch (Exception e)
			{
				// In case message formating failed, we have a fallback format to use
				messageFormated = new StringBuilder()
						.append(message)
						.append(" (").append(params).append(")")
						.toString();
			}
		}
		else
		{
			messageFormated = message;
		}
		return messageFormated;
	}

}

package org.adempiere.util.api.impl;

import java.util.Properties;

import org.adempiere.util.api.IMsgBL;
import org.compiere.util.Msg;

/**
 * 
 * This implementation delegates to {@link Msg} and is therefore coupled with the database.
 * 
 */
public class MsgBL extends PlainMsgBL implements IMsgBL
{

	@Override
	public String getMsg(String language, String message, Object[] params)
	{
		return Msg.getMsg(language, message, params);
	}

	@Override
	public String parseTranslation(Properties ctx, String message)
	{
		return Msg.parseTranslation(ctx, message);
	}

}

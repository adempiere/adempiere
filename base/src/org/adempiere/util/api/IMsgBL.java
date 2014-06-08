package org.adempiere.util.api;

import java.util.Properties;

import org.adempiere.util.ISingletonService;

public interface IMsgBL extends ISingletonService
{
	String getMsg(String language, String message, Object[] params);
	
	String parseTranslation(Properties ctx, String message);
	
	
	String formatMessage(final String message, Object... params);
}

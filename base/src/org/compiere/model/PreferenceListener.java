package org.compiere.model;

import java.util.Properties;

public interface PreferenceListener {

	/**
	 * @param properties
	 */
	public void afterLoadPreferences(Properties properties);
	
}

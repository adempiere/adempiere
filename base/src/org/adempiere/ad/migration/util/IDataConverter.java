package org.adempiere.ad.migration.util;

import org.compiere.model.I_AD_Column;
import org.compiere.model.POInfoColumn;

public interface IDataConverter
{
	/**
	 * Value returned by converting methods, when the column was mandatory but no value was identified.
	 */
	Object VALUE_Unknown = new Object();

	/**
	 * Converts given string value to proper object format
	 * 
	 * @param column
	 * @param value
	 * @return converted object or {@link #VALUE_Unknown}
	 */
	Object stringToObject(I_AD_Column column, String value);

	String objectToString(I_AD_Column column, Object value);

	String objectToString(POInfoColumn columnInfo, Object value);

}

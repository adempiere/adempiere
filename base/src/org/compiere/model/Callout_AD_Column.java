/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

import org.adempiere.model.GridTabWrapper;

/**
 * @author teo_sarca
 *
 */
public class Callout_AD_Column extends CalloutEngine
{
	public String columnName (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_AD_Column column = GridTabWrapper.create(mTab, I_AD_Column.class);
		if (MColumn.isSuggestSelectionColumn(column.getColumnName(), true))
			column.setIsSelectionColumn(true);
		//
		return "";
	}

}

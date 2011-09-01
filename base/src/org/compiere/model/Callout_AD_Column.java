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
 *****************************************************************************/
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

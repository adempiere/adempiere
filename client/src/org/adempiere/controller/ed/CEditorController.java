/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.controller.ed;

import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MQuery;

/**
 * An interface for controllers of view editors for specific fields.  The controller manages the
 * interactions with the model in a consistent way across all view types.
 * 
 * @author Michael McKay, mckayERP
 *
 */
public interface CEditorController {
	
	/** Dispose of the controller */
	public void dispose();
	
	/**
	 * @return the columnName
	 */
	public String getColumnName();

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName);

	/**
	 * 	Set GridField
	 * 	@param field MField
	 */
	public void setGridField(GridField field);
	
	public GridField getGridField();

	boolean hasChanged();

	public boolean isMandatory();

	public void setMandatory(boolean mandatory);

	public void setReadOnly(boolean readOnly);

	public boolean isReadOnly();

	public void setValue(Object value);

	public void generateZoomQuery();

	public int getZoomAD_Window_ID();

	public MQuery getZoomQuery();

	public int getWindowNo();

	public void actionText();

	public void actionButton();

	public void enableControls();

	public void setLastDisplay(String display);

	public Lookup getLookup();

	public void set_oldValue();

	public void init();

}

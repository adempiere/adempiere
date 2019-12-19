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
package org.compiere.swing;

import java.util.Map;

import javax.swing.plaf.ColorUIResource;

/**
 * A list of UI resources and definitions that extend the look and feel.
 * 
 * @author unknown
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 
 * @version 3.9.4
 */
public interface ExtendedTheme {

	public ColorUIResource getWhite();

	public ColorUIResource getBlack();

	public ColorUIResource getErrorForeground();

	public ColorUIResource getErrorBackground();

	public ColorUIResource getInactiveBackground();

	public ColorUIResource getMandatoryBackground();

	public ColorUIResource getInfoBackground();

	public final static ColorUIResource DEFAULT_MANDATORY_BG = new ColorUIResource(224, 224, 255); // blue-isch
	public final static ColorUIResource DEFAULT_ERROR_BG = new ColorUIResource(255, 204, 204); // red-isch
	public final static ColorUIResource DEFAULT_ERROR_FG = new ColorUIResource(204, 0, 0); // dark red
	public final static ColorUIResource DEFAULT_INACTIVE_BG = new ColorUIResource(234, 234, 234); // light gray
	public final static ColorUIResource DEFAULT_INFO_BG = new ColorUIResource(253, 237, 207); // light yellow
	public final static ColorUIResource DEFAULT_SELECTED_BG = new ColorUIResource(240, 248, 255); // light gray
	public final static ColorUIResource DEFAULT_READONLY_BG = new ColorUIResource(255, 245, 238); // light yellow

	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_INACTIVE_NORMAL = DEFAULT_INACTIVE_BG;
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_INACTIVE_ERROR = new ColorUIResource(248, 234, 234);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_INACTIVE_MANDATORY = new ColorUIResource(200, 200, 234);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_ACTIVE_NORMAL = new ColorUIResource(249, 249, 249);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_ACTIVE_ERROR = new ColorUIResource(255, 204, 204);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_NOT_SELECTED_ACTIVE_MANDATORY = new ColorUIResource(205, 225, 245);

	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_INACTIVE_NORMAL = new ColorUIResource(240, 240, 240);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_INACTIVE_ERROR = new ColorUIResource(251, 243, 243);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_INACTIVE_MANDATORY = new ColorUIResource(210, 210, 238);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_ACTIVE_NORMAL = new ColorUIResource(252, 252, 252);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_ACTIVE_ERROR = new ColorUIResource(255, 213, 213);
	public final static ColorUIResource DEFAULT_TABLE_ROW_BG_SELECTED_ACTIVE_MANDATORY = new ColorUIResource(217, 232, 247);

	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_INACTIVE_NORMAL = new ColorUIResource(240, 240, 240);
	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_INACTIVE_ERROR = new ColorUIResource(251, 243, 243);
	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_INACTIVE_MANDATORY = new ColorUIResource(210, 210, 238);
	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_ACTIVE_NORMAL = new ColorUIResource(255, 255, 255);
	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_ACTIVE_ERROR = new ColorUIResource(255, 204, 204);;
	public final static ColorUIResource DEFAULT_TABLE_CELL_BG_FOCUSED_ACTIVE_MANDATORY = new ColorUIResource(224, 224, 255);

	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_NORMAL = new ColorUIResource(220, 220, 220);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_ERROR = new ColorUIResource(238, 202, 202);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_MANDATORY = new ColorUIResource(191, 191, 231);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_NORMAL = new ColorUIResource(243, 243, 243);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_ERROR = new ColorUIResource(255, 213, 213);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_MANDATORY = new ColorUIResource(197, 220, 243);

	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_INACTIVE_NORMAL = new ColorUIResource(240, 240, 240);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_INACTIVE_ERROR = new ColorUIResource(249, 237, 237);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_INACTIVE_MANDATORY = new ColorUIResource(204, 204, 236);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_ACTIVE_NORMAL = new ColorUIResource(246, 246, 246);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_ACTIVE_ERROR = new ColorUIResource(252, 208, 208);
	public final static ColorUIResource DEFAULT_TABLE_ALT_ROW_BG_SELECTED_ACTIVE_MANDATORY = new ColorUIResource(214, 230, 246); 
	
	
	public void setUIProperties(Map<?, ?> propertyMap);

	public final static String ERROR_BG_KEY = "TextField.errorBackground";
	public final static String ERROR_FG_KEY = "TextField.errorForeground";
	public final static String MANDATORY_BG_KEY = "TextField.mandatoryBackground";
	public final static String INACTIVE_BG_KEY = "TextField.inactiveBackground";
	public final static String INFO_BG_KEY = "Info.background";
	public final static String SELECTED_BG_KEY = "TextField.selectedBackground";
	public final static String READONLY_BG_KEY = "TextField.readonlyBackground";

	public final static String TABLE_ROW_BG_NOT_SELECTED_INACTIVE_NORMAL = "TableRowBG.notSelectedInactiveNormal";
	public final static String TABLE_ROW_BG_NOT_SELECTED_INACTIVE_ERROR = "TableRowBG.notSelectedInactiveError";
	public final static String TABLE_ROW_BG_NOT_SELECTED_INACTIVE_MANDATORY = "TableRowBG.notSelectedInactiveMandatory";
	public final static String TABLE_ROW_BG_NOT_SELECTED_ACTIVE_NORMAL = "TableRowBG.notSelectedActiveNormal";
	public final static String TABLE_ROW_BG_NOT_SELECTED_ACTIVE_ERROR = "TableRowBG.notSelectedActiveError";
	public final static String TABLE_ROW_BG_NOT_SELECTED_ACTIVE_MANDATORY = "TableRowBG.notSelectedActiveMandatory";

	public final static String TABLE_ROW_BG_SELECTED_INACTIVE_NORMAL = "TableRowBG.SelectedInactiveNormal";
	public final static String TABLE_ROW_BG_SELECTED_INACTIVE_ERROR = "TableRowBG.SelectedInactiveError";
	public final static String TABLE_ROW_BG_SELECTED_INACTIVE_MANDATORY = "TableRowBG.SelectedInactiveMandatory";
	public final static String TABLE_ROW_BG_SELECTED_ACTIVE_NORMAL = "TableRowBG.SelectedActiveNormal";
	public final static String TABLE_ROW_BG_SELECTED_ACTIVE_ERROR = "TableRowBG.SelectedActiveError";
	public final static String TABLE_ROW_BG_SELECTED_ACTIVE_MANDATORY = "TableRowBG.SelectedActiveMandatory";

	public final static String TABLE_CELL_BG_FOCUSED_INACTIVE_NORMAL = "TableCellBG.notSelectedInactiveNormal";
	public final static String TABLE_CELL_BG_FOCUSED_INACTIVE_ERROR = "TableCellBG.notSelectedInactiveError";
	public final static String TABLE_CELL_BG_FOCUSED_INACTIVE_MANDATORY = "TableCellBG.notSelectedInactiveMandatory";
	public final static String TABLE_CELL_BG_FOCUSED_ACTIVE_NORMAL = "TableCellBG.notSelectedActiveNormal";
	public final static String TABLE_CELL_BG_FOCUSED_ACTIVE_ERROR = "TableCellBG.notSelectedActiveError";
	public final static String TABLE_CELL_BG_FOCUSED_ACTIVE_MANDATORY = "TableCellBG.notSelectedActiveMandatory";

	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_NORMAL = "TableAltRowBG.notSelectedInactiveNormal";
	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_ERROR = "TableAltRowBG.notSelectedInactiveError";
	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_INACTIVE_MANDATORY = "TableAltRowBG.notSelectedInactiveMandatory";
	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_NORMAL = "TableAltRowBG.notSelectedActiveNormal";
	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_ERROR = "TableAltRowBG.notSelectedActiveError";
	public final static String TABLE_ALT_ROW_BG_NOT_SELECTED_ACTIVE_MANDATORY = "TableAltRowBG.notSelectedActiveMandatory";

	public final static String TABLE_ALT_ROW_BG_SELECTED_INACTIVE_NORMAL = "TableAltRowBG.SelectedInactiveNormal";
	public final static String TABLE_ALT_ROW_BG_SELECTED_INACTIVE_ERROR = "TableAltRowBG.SelectedInactiveError";
	public final static String TABLE_ALT_ROW_BG_SELECTED_INACTIVE_MANDATORY = "TableAltRowBG.SelectedInactiveMandatory";
	public final static String TABLE_ALT_ROW_BG_SELECTED_ACTIVE_NORMAL = "TableAltRowBG.SelectedActiveNormal";
	public final static String TABLE_ALT_ROW_BG_SELECTED_ACTIVE_ERROR = "TableAltRowBG.SelectedActiveError";
	public final static String TABLE_ALT_ROW_BG_SELECTED_ACTIVE_MANDATORY = "TableAltRowBG.SelectedActiveMandatory";

}

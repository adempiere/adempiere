/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.adempiere.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Synchronize Entity Type)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class SynchronizeEntityTypeAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "AD_EntityType Synchronize";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Synchronize Entity Type";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54646;
	/**	Parameter Name for Menu	*/
	public static final String AD_MENU_ID = "AD_Menu_ID";
	/**	Parameter Name for Entity Type	*/
	public static final String AD_ENTITYTYPE_ID = "AD_EntityType_ID";
	/**	Parameter Value for Menu	*/
	private int menuId;
	/**	Parameter Value for Entity Type	*/
	private int entityTypeId;

	@Override
	protected void prepare() {
		menuId = getParameterAsInt(AD_MENU_ID);
		entityTypeId = getParameterAsInt(AD_ENTITYTYPE_ID);
	}

	/**	 Getter Parameter Value for Menu	*/
	protected int getMenuId() {
		return menuId;
	}

	/**	 Setter Parameter Value for Menu	*/
	protected void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	/**	 Getter Parameter Value for Entity Type	*/
	protected int getEntityTypeId() {
		return entityTypeId;
	}

	/**	 Setter Parameter Value for Entity Type	*/
	protected void setEntityTypeId(int entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}
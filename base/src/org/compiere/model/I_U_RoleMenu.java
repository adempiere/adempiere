/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_RoleMenu
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_U_RoleMenu 
{

    /** TableName=U_RoleMenu */
    public static final String Table_Name = "U_RoleMenu";

    /** AD_Table_ID=52002 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public I_AD_Role getAD_Role() throws Exception;

    /** Column name U_RoleMenu_ID */
    public static final String COLUMNNAME_U_RoleMenu_ID = "U_RoleMenu_ID";

	/** Set Role Menu	  */
	public void setU_RoleMenu_ID (int U_RoleMenu_ID);

	/** Get Role Menu	  */
	public int getU_RoleMenu_ID();

    /** Column name U_WebMenu_ID */
    public static final String COLUMNNAME_U_WebMenu_ID = "U_WebMenu_ID";

	/** Set Web Menu	  */
	public void setU_WebMenu_ID (int U_WebMenu_ID);

	/** Get Web Menu	  */
	public int getU_WebMenu_ID();

	public I_U_WebMenu getU_WebMenu() throws Exception;
}

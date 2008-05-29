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

/** Generated Interface for U_Web_Properties
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_U_Web_Properties 
{

    /** TableName=U_Web_Properties */
    public static final String Table_Name = "U_Web_Properties";

    /** AD_Table_ID=52001 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name U_Key */
    public static final String COLUMNNAME_U_Key = "U_Key";

	/** Set Key	  */
	public void setU_Key (String U_Key);

	/** Get Key	  */
	public String getU_Key();

    /** Column name U_Value */
    public static final String COLUMNNAME_U_Value = "U_Value";

	/** Set Value	  */
	public void setU_Value (String U_Value);

	/** Get Value	  */
	public String getU_Value();

    /** Column name U_Web_Properties_ID */
    public static final String COLUMNNAME_U_Web_Properties_ID = "U_Web_Properties_ID";

	/** Set Web Properties	  */
	public void setU_Web_Properties_ID (int U_Web_Properties_ID);

	/** Get Web Properties	  */
	public int getU_Web_Properties_ID();
}

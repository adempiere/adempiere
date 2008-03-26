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

/** Generated Interface for ASP_Field
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_ASP_Field 
{

    /** TableName=ASP_Field */
    public static final String Table_Name = "ASP_Field";

    /** AD_Table_ID=53048 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Field_ID */
    public static final String COLUMNNAME_AD_Field_ID = "AD_Field_ID";

	/** Set Field.
	  * Field on a database table
	  */
	public void setAD_Field_ID (int AD_Field_ID);

	/** Get Field.
	  * Field on a database table
	  */
	public int getAD_Field_ID();

	public I_AD_Field getAD_Field() throws Exception;

    /** Column name AD_Tab_ID */
    public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";

	/** Set Tab.
	  * Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID);

	/** Get Tab.
	  * Tab within a Window
	  */
	public int getAD_Tab_ID();

	public I_AD_Tab getAD_Tab() throws Exception;

    /** Column name ASP_Level_ID */
    public static final String COLUMNNAME_ASP_Level_ID = "ASP_Level_ID";

	/** Set ASP Level	  */
	public void setASP_Level_ID (int ASP_Level_ID);

	/** Get ASP Level	  */
	public int getASP_Level_ID();

	public I_ASP_Level getASP_Level() throws Exception;

    /** Column name ASP_Status */
    public static final String COLUMNNAME_ASP_Status = "ASP_Status";

	/** Set ASP Status	  */
	public void setASP_Status (String ASP_Status);

	/** Get ASP Status	  */
	public String getASP_Status();
}

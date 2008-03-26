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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_Container_URL
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_CM_Container_URL 
{

    /** TableName=CM_Container_URL */
    public static final String Table_Name = "CM_Container_URL";

    /** AD_Table_ID=865 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name CM_Container_ID */
    public static final String COLUMNNAME_CM_Container_ID = "CM_Container_ID";

	/** Set Web Container.
	  * Web Container contains content like images, text etc.
	  */
	public void setCM_Container_ID (int CM_Container_ID);

	/** Get Web Container.
	  * Web Container contains content like images, text etc.
	  */
	public int getCM_Container_ID();

	public I_CM_Container getCM_Container() throws Exception;

    /** Column name CM_Container_URL_ID */
    public static final String COLUMNNAME_CM_Container_URL_ID = "CM_Container_URL_ID";

	/** Set Container URL.
	  * Contains info on used URLs
	  */
	public void setCM_Container_URL_ID (int CM_Container_URL_ID);

	/** Get Container URL.
	  * Contains info on used URLs
	  */
	public int getCM_Container_URL_ID();

    /** Column name Checked */
    public static final String COLUMNNAME_Checked = "Checked";

	/** Set Last Checked.
	  * Info when we did the last check
	  */
	public void setChecked (Timestamp Checked);

	/** Get Last Checked.
	  * Info when we did the last check
	  */
	public Timestamp getChecked();

    /** Column name Last_Result */
    public static final String COLUMNNAME_Last_Result = "Last_Result";

	/** Set Last Result.
	  * Contains data on the last check result
	  */
	public void setLast_Result (String Last_Result);

	/** Get Last Result.
	  * Contains data on the last check result
	  */
	public String getLast_Result();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();
}

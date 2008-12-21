/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_Container_URL
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
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

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

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

	public I_CM_Container getCM_Container() throws RuntimeException;

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

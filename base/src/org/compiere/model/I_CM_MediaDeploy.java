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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for CM_MediaDeploy
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:34.984
     */
    public interface I_CM_MediaDeploy 
{

    /** TableName=CM_MediaDeploy */
    public static final String Table_Name = "CM_MediaDeploy";

    /** AD_Table_ID=892 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name CM_MediaDeploy_ID */
    public static final String COLUMNNAME_CM_MediaDeploy_ID = "CM_MediaDeploy_ID";

	/** Set Media Deploy.
	  * Media Deployment Log
	  */
	public void setCM_MediaDeploy_ID (int CM_MediaDeploy_ID);

	/** Get Media Deploy.
	  * Media Deployment Log
	  */
	public int getCM_MediaDeploy_ID();

    /** Column name CM_Media_ID */
    public static final String COLUMNNAME_CM_Media_ID = "CM_Media_ID";

	/** Set Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public void setCM_Media_ID (int CM_Media_ID);

	/** Get Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public int getCM_Media_ID();

	public I_CM_Media getI_CM_Media() throws Exception;

    /** Column name CM_Media_Server_ID */
    public static final String COLUMNNAME_CM_Media_Server_ID = "CM_Media_Server_ID";

	/** Set Media Server.
	  * Media Server list to which content should get transfered
	  */
	public void setCM_Media_Server_ID (int CM_Media_Server_ID);

	/** Get Media Server.
	  * Media Server list to which content should get transfered
	  */
	public int getCM_Media_Server_ID();

	public I_CM_Media_Server getI_CM_Media_Server() throws Exception;

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsDeployed */
    public static final String COLUMNNAME_IsDeployed = "IsDeployed";

	/** Set Deployed.
	  * Entity is deployed
	  */
	public void setIsDeployed (boolean IsDeployed);

	/** Get Deployed.
	  * Entity is deployed
	  */
	public boolean isDeployed();

    /** Column name LastSynchronized */
    public static final String COLUMNNAME_LastSynchronized = "LastSynchronized";

	/** Set Last Synchronized.
	  * Date when last synchronized
	  */
	public void setLastSynchronized (Timestamp LastSynchronized);

	/** Get Last Synchronized.
	  * Date when last synchronized
	  */
	public Timestamp getLastSynchronized();
}

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

/** Generated Interface for AD_Replication_Log
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_AD_Replication_Log 
{

    /** TableName=AD_Replication_Log */
    public static final String Table_Name = "AD_Replication_Log";

    /** AD_Table_ID=604 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_ReplicationTable_ID */
    public static final String COLUMNNAME_AD_ReplicationTable_ID = "AD_ReplicationTable_ID";

	/** Set Replication Table.
	  * Data Replication Strategy Table Info
	  */
	public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID);

	/** Get Replication Table.
	  * Data Replication Strategy Table Info
	  */
	public int getAD_ReplicationTable_ID();

	public I_AD_ReplicationTable getAD_ReplicationTable() throws Exception;

    /** Column name AD_Replication_Log_ID */
    public static final String COLUMNNAME_AD_Replication_Log_ID = "AD_Replication_Log_ID";

	/** Set Replication Log.
	  * Data Replication Log Details
	  */
	public void setAD_Replication_Log_ID (int AD_Replication_Log_ID);

	/** Get Replication Log.
	  * Data Replication Log Details
	  */
	public int getAD_Replication_Log_ID();

    /** Column name AD_Replication_Run_ID */
    public static final String COLUMNNAME_AD_Replication_Run_ID = "AD_Replication_Run_ID";

	/** Set Replication Run.
	  * Data Replication Run
	  */
	public void setAD_Replication_Run_ID (int AD_Replication_Run_ID);

	/** Get Replication Run.
	  * Data Replication Run
	  */
	public int getAD_Replication_Run_ID();

	public I_AD_Replication_Run getAD_Replication_Run() throws Exception;

    /** Column name IsReplicated */
    public static final String COLUMNNAME_IsReplicated = "IsReplicated";

	/** Set Replicated.
	  * The data is successfully replicated
	  */
	public void setIsReplicated (boolean IsReplicated);

	/** Get Replicated.
	  * The data is successfully replicated
	  */
	public boolean isReplicated();

    /** Column name P_Msg */
    public static final String COLUMNNAME_P_Msg = "P_Msg";

	/** Set Process Message	  */
	public void setP_Msg (String P_Msg);

	/** Get Process Message	  */
	public String getP_Msg();
}

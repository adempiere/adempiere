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

/** Generated Interface for AD_Replication
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_AD_Replication 
{

    /** TableName=AD_Replication */
    public static final String Table_Name = "AD_Replication";

    /** AD_Table_ID=605 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_ReplicationStrategy_ID */
    public static final String COLUMNNAME_AD_ReplicationStrategy_ID = "AD_ReplicationStrategy_ID";

	/** Set Replication Strategy.
	  * Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID);

	/** Get Replication Strategy.
	  * Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID();

	public I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws Exception;

    /** Column name AD_Replication_ID */
    public static final String COLUMNNAME_AD_Replication_ID = "AD_Replication_ID";

	/** Set Replication.
	  * Data Replication Target
	  */
	public void setAD_Replication_ID (int AD_Replication_ID);

	/** Get Replication.
	  * Data Replication Target
	  */
	public int getAD_Replication_ID();

    /** Column name DateLastRun */
    public static final String COLUMNNAME_DateLastRun = "DateLastRun";

	/** Set Date last run.
	  * Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun);

	/** Get Date last run.
	  * Date the process was last run.
	  */
	public Timestamp getDateLastRun();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name HostAddress */
    public static final String COLUMNNAME_HostAddress = "HostAddress";

	/** Set Host Address.
	  * Host Address URL or DNS
	  */
	public void setHostAddress (String HostAddress);

	/** Get Host Address.
	  * Host Address URL or DNS
	  */
	public String getHostAddress();

    /** Column name HostPort */
    public static final String COLUMNNAME_HostPort = "HostPort";

	/** Set Host port.
	  * Host Communication Port
	  */
	public void setHostPort (int HostPort);

	/** Get Host port.
	  * Host Communication Port
	  */
	public int getHostPort();

    /** Column name IDRangeEnd */
    public static final String COLUMNNAME_IDRangeEnd = "IDRangeEnd";

	/** Set ID Range End.
	  * End if the ID Range used
	  */
	public void setIDRangeEnd (BigDecimal IDRangeEnd);

	/** Get ID Range End.
	  * End if the ID Range used
	  */
	public BigDecimal getIDRangeEnd();

    /** Column name IDRangeStart */
    public static final String COLUMNNAME_IDRangeStart = "IDRangeStart";

	/** Set ID Range Start.
	  * Start of the ID Range used
	  */
	public void setIDRangeStart (BigDecimal IDRangeStart);

	/** Get ID Range Start.
	  * Start of the ID Range used
	  */
	public BigDecimal getIDRangeStart();

    /** Column name IsRMIoverHTTP */
    public static final String COLUMNNAME_IsRMIoverHTTP = "IsRMIoverHTTP";

	/** Set Tunnel via HTTP.
	  * Connect to Server via HTTP Tunnel
	  */
	public void setIsRMIoverHTTP (boolean IsRMIoverHTTP);

	/** Get Tunnel via HTTP.
	  * Connect to Server via HTTP Tunnel
	  */
	public boolean isRMIoverHTTP();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Prefix */
    public static final String COLUMNNAME_Prefix = "Prefix";

	/** Set Prefix.
	  * Prefix before the sequence number
	  */
	public void setPrefix (String Prefix);

	/** Get Prefix.
	  * Prefix before the sequence number
	  */
	public String getPrefix();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Remote_Client_ID */
    public static final String COLUMNNAME_Remote_Client_ID = "Remote_Client_ID";

	/** Set Remote Client.
	  * Remote Client to be used to replicate / synchronize data with.
	  */
	public void setRemote_Client_ID (int Remote_Client_ID);

	/** Get Remote Client.
	  * Remote Client to be used to replicate / synchronize data with.
	  */
	public int getRemote_Client_ID();

    /** Column name Remote_Org_ID */
    public static final String COLUMNNAME_Remote_Org_ID = "Remote_Org_ID";

	/** Set Remote Organization.
	  * Remote Organization to be used to replicate / synchronize data with.
	  */
	public void setRemote_Org_ID (int Remote_Org_ID);

	/** Get Remote Organization.
	  * Remote Organization to be used to replicate / synchronize data with.
	  */
	public int getRemote_Org_ID();

    /** Column name Suffix */
    public static final String COLUMNNAME_Suffix = "Suffix";

	/** Set Suffix.
	  * Suffix after the number
	  */
	public void setSuffix (String Suffix);

	/** Get Suffix.
	  * Suffix after the number
	  */
	public String getSuffix();
}

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

/** Generated Interface for R_IssueSystem
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_R_IssueSystem 
{

    /** TableName=R_IssueSystem */
    public static final String Table_Name = "R_IssueSystem";

    /** AD_Table_ID=843 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public I_A_Asset getA_Asset() throws Exception;

    /** Column name DBAddress */
    public static final String COLUMNNAME_DBAddress = "DBAddress";

	/** Set DB Address.
	  * JDBC URL of the database server
	  */
	public void setDBAddress (String DBAddress);

	/** Get DB Address.
	  * JDBC URL of the database server
	  */
	public String getDBAddress();

    /** Column name ProfileInfo */
    public static final String COLUMNNAME_ProfileInfo = "ProfileInfo";

	/** Set Profile.
	  * Information to help profiling the system for solving support issues
	  */
	public void setProfileInfo (String ProfileInfo);

	/** Get Profile.
	  * Information to help profiling the system for solving support issues
	  */
	public String getProfileInfo();

    /** Column name R_IssueSystem_ID */
    public static final String COLUMNNAME_R_IssueSystem_ID = "R_IssueSystem_ID";

	/** Set Issue System.
	  * System creating the issue
	  */
	public void setR_IssueSystem_ID (int R_IssueSystem_ID);

	/** Get Issue System.
	  * System creating the issue
	  */
	public int getR_IssueSystem_ID();

    /** Column name StatisticsInfo */
    public static final String COLUMNNAME_StatisticsInfo = "StatisticsInfo";

	/** Set Statistics.
	  * Information to help profiling the system for solving support issues
	  */
	public void setStatisticsInfo (String StatisticsInfo);

	/** Get Statistics.
	  * Information to help profiling the system for solving support issues
	  */
	public String getStatisticsInfo();

    /** Column name SystemStatus */
    public static final String COLUMNNAME_SystemStatus = "SystemStatus";

	/** Set System Status.
	  * Status of the system - Support priority depends on system status
	  */
	public void setSystemStatus (String SystemStatus);

	/** Get System Status.
	  * Status of the system - Support priority depends on system status
	  */
	public String getSystemStatus();
}

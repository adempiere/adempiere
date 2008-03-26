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

/** Generated Interface for AD_Registration
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_AD_Registration 
{

    /** TableName=AD_Registration */
    public static final String Table_Name = "AD_Registration";

    /** AD_Table_ID=625 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Registration_ID */
    public static final String COLUMNNAME_AD_Registration_ID = "AD_Registration_ID";

	/** Set System Registration.
	  * System Registration
	  */
	public void setAD_Registration_ID (int AD_Registration_ID);

	/** Get System Registration.
	  * System Registration
	  */
	public int getAD_Registration_ID();

    /** Column name AD_System_ID */
    public static final String COLUMNNAME_AD_System_ID = "AD_System_ID";

	/** Set System.
	  * System Definition
	  */
	public void setAD_System_ID (int AD_System_ID);

	/** Get System.
	  * System Definition
	  */
	public int getAD_System_ID();

	public I_AD_System getAD_System() throws Exception;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws Exception;

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name IndustryInfo */
    public static final String COLUMNNAME_IndustryInfo = "IndustryInfo";

	/** Set Industry Info.
	  * Information of the industry (e.g. professional service, distribution of furnitures, ..)
	  */
	public void setIndustryInfo (String IndustryInfo);

	/** Get Industry Info.
	  * Information of the industry (e.g. professional service, distribution of furnitures, ..)
	  */
	public String getIndustryInfo();

    /** Column name IsAllowPublish */
    public static final String COLUMNNAME_IsAllowPublish = "IsAllowPublish";

	/** Set Allowed to be Published.
	  * You allow to publish the information, not just statistical summary info
	  */
	public void setIsAllowPublish (boolean IsAllowPublish);

	/** Get Allowed to be Published.
	  * You allow to publish the information, not just statistical summary info
	  */
	public boolean isAllowPublish();

    /** Column name IsAllowStatistics */
    public static final String COLUMNNAME_IsAllowStatistics = "IsAllowStatistics";

	/** Set Maintain Statistics.
	  * Maintain general statistics
	  */
	public void setIsAllowStatistics (boolean IsAllowStatistics);

	/** Get Maintain Statistics.
	  * Maintain general statistics
	  */
	public boolean isAllowStatistics();

    /** Column name IsInProduction */
    public static final String COLUMNNAME_IsInProduction = "IsInProduction";

	/** Set In Production.
	  * The system is in production
	  */
	public void setIsInProduction (boolean IsInProduction);

	/** Get In Production.
	  * The system is in production
	  */
	public boolean isInProduction();

    /** Column name IsRegistered */
    public static final String COLUMNNAME_IsRegistered = "IsRegistered";

	/** Set Registered.
	  * The application is registered.
	  */
	public void setIsRegistered (boolean IsRegistered);

	/** Get Registered.
	  * The application is registered.
	  */
	public boolean isRegistered();

    /** Column name NumberEmployees */
    public static final String COLUMNNAME_NumberEmployees = "NumberEmployees";

	/** Set Employees.
	  * Number of employees
	  */
	public void setNumberEmployees (int NumberEmployees);

	/** Get Employees.
	  * Number of employees
	  */
	public int getNumberEmployees();

    /** Column name PlatformInfo */
    public static final String COLUMNNAME_PlatformInfo = "PlatformInfo";

	/** Set Platform Info.
	  * Information about Server and Client Platform
	  */
	public void setPlatformInfo (String PlatformInfo);

	/** Get Platform Info.
	  * Information about Server and Client Platform
	  */
	public String getPlatformInfo();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name Remote_Addr */
    public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";

	/** Set Remote Addr.
	  * Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr);

	/** Get Remote Addr.
	  * Remote Address
	  */
	public String getRemote_Addr();

    /** Column name Remote_Host */
    public static final String COLUMNNAME_Remote_Host = "Remote_Host";

	/** Set Remote Host.
	  * Remote host Info
	  */
	public void setRemote_Host (String Remote_Host);

	/** Get Remote Host.
	  * Remote host Info
	  */
	public String getRemote_Host();

    /** Column name SalesVolume */
    public static final String COLUMNNAME_SalesVolume = "SalesVolume";

	/** Set Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public void setSalesVolume (int SalesVolume);

	/** Get Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public int getSalesVolume();

    /** Column name StartProductionDate */
    public static final String COLUMNNAME_StartProductionDate = "StartProductionDate";

	/** Set Start Implementation/Production.
	  * The day you started the implementation (if implementing) - or production (went life) with Adempiere
	  */
	public void setStartProductionDate (Timestamp StartProductionDate);

	/** Get Start Implementation/Production.
	  * The day you started the implementation (if implementing) - or production (went life) with Adempiere
	  */
	public Timestamp getStartProductionDate();
}

/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_ForecastRun
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_PP_ForecastRun 
{

    /** TableName=PP_ForecastRun */
    public static final String Table_Name = "PP_ForecastRun";

    /** AD_Table_ID=53391 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_WarehouseSource_ID */
    public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";

	/** Set Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID);

	/** Get Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID();

	public org.compiere.model.I_M_Warehouse getM_WarehouseSource() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name PP_Calendar_ID */
    public static final String COLUMNNAME_PP_Calendar_ID = "PP_Calendar_ID";

	/** Set Operational Calendar.
	  * Operational Period, allows to define the periods for the Operational Calendar
	  */
	public void setPP_Calendar_ID (int PP_Calendar_ID);

	/** Get Operational Calendar.
	  * Operational Period, allows to define the periods for the Operational Calendar
	  */
	public int getPP_Calendar_ID();

	public org.eevolution.model.I_PP_Calendar getPP_Calendar() throws RuntimeException;

    /** Column name PP_ForecastDefinition_ID */
    public static final String COLUMNNAME_PP_ForecastDefinition_ID = "PP_ForecastDefinition_ID";

	/** Set Forecast Definition	  */
	public void setPP_ForecastDefinition_ID (int PP_ForecastDefinition_ID);

	/** Get Forecast Definition	  */
	public int getPP_ForecastDefinition_ID();

	public org.eevolution.model.I_PP_ForecastDefinition getPP_ForecastDefinition() throws RuntimeException;

    /** Column name PP_ForecastRule_ID */
    public static final String COLUMNNAME_PP_ForecastRule_ID = "PP_ForecastRule_ID";

	/** Set Forecast Rule.
	  * Forecast Rules define the business logic according to a previously implemented algorithm.
	  */
	public void setPP_ForecastRule_ID (int PP_ForecastRule_ID);

	/** Get Forecast Rule.
	  * Forecast Rules define the business logic according to a previously implemented algorithm.
	  */
	public int getPP_ForecastRule_ID();

	public org.eevolution.model.I_PP_ForecastRule getPP_ForecastRule() throws RuntimeException;

    /** Column name PP_ForecastRun_ID */
    public static final String COLUMNNAME_PP_ForecastRun_ID = "PP_ForecastRun_ID";

	/** Set Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public void setPP_ForecastRun_ID (int PP_ForecastRun_ID);

	/** Get Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public int getPP_ForecastRun_ID();

    /** Column name PP_PeriodDefinition_ID */
    public static final String COLUMNNAME_PP_PeriodDefinition_ID = "PP_PeriodDefinition_ID";

	/** Set Current Period.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setPP_PeriodDefinition_ID (int PP_PeriodDefinition_ID);

	/** Get Current Period.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getPP_PeriodDefinition_ID();

	public org.eevolution.model.I_PP_PeriodDefinition getPP_PeriodDefinition() throws RuntimeException;

    /** Column name PeriodHistory */
    public static final String COLUMNNAME_PeriodHistory = "PeriodHistory";

	/** Set Periods of History.
	  * Number Period of History
	  */
	public void setPeriodHistory (int PeriodHistory);

	/** Get Periods of History.
	  * Number Period of History
	  */
	public int getPeriodHistory();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Ref_DefinitionPeriod_ID */
    public static final String COLUMNNAME_Ref_DefinitionPeriod_ID = "Ref_DefinitionPeriod_ID";

	/** Set Past Period Definition.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setRef_DefinitionPeriod_ID (int Ref_DefinitionPeriod_ID);

	/** Get Past Period Definition.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getRef_DefinitionPeriod_ID();

	public org.eevolution.model.I_PP_PeriodDefinition getRef_DefinitionPeriod() throws RuntimeException;

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}

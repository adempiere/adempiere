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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_ReplenishPlan
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_M_ReplenishPlan 
{

    /** TableName=M_ReplenishPlan */
    public static final String Table_Name = "M_ReplenishPlan";

    /** AD_Table_ID=53974 */
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

    /** Column name C_DocType_ConfirmedOrder */
    public static final String COLUMNNAME_C_DocType_ConfirmedOrder = "C_DocType_ConfirmedOrder";

	/** Set Confirmed Mfg Order Doc Type	  */
	public void setC_DocType_ConfirmedOrder (int C_DocType_ConfirmedOrder);

	/** Get Confirmed Mfg Order Doc Type	  */
	public int getC_DocType_ConfirmedOrder();

	public org.compiere.model.I_C_DocType getC_DocType_ConfirmedOr() throws RuntimeException;

    /** Column name C_DocType_PO */
    public static final String COLUMNNAME_C_DocType_PO = "C_DocType_PO";

	/** Set Purchase Order Doc Type	  */
	public void setC_DocType_PO (int C_DocType_PO);

	/** Get Purchase Order Doc Type	  */
	public int getC_DocType_PO();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_DocType_PlannedOrder */
    public static final String COLUMNNAME_C_DocType_PlannedOrder = "C_DocType_PlannedOrder";

	/** Set Planned Mfg Order Doc Type	  */
	public void setC_DocType_PlannedOrder (int C_DocType_PlannedOrder);

	/** Get Planned Mfg Order Doc Type	  */
	public int getC_DocType_PlannedOrder();

	public org.compiere.model.I_C_DocType getC_DocType_PlannedOr() throws RuntimeException;

    /** Column name C_DocType_Requisition */
    public static final String COLUMNNAME_C_DocType_Requisition = "C_DocType_Requisition";

	/** Set Replenish Plan Requisition Doc Type	  */
	public void setC_DocType_Requisition (int C_DocType_Requisition);

	/** Get Replenish Plan Requisition Doc Type	  */
	public int getC_DocType_Requisition();

	public org.compiere.model.I_C_DocType getC_DocType_Requisit() throws RuntimeException;

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

    /** Column name DateFinish */
    public static final String COLUMNNAME_DateFinish = "DateFinish";

	/** Set Finish Date.
	  * Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish);

	/** Get Finish Date.
	  * Finish or (planned) completion date
	  */
	public Timestamp getDateFinish();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

    /** Column name GenerateReport */
    public static final String COLUMNNAME_GenerateReport = "GenerateReport";

	/** Set Generate Report	  */
	public void setGenerateReport (String GenerateReport);

	/** Get Generate Report	  */
	public String getGenerateReport();

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

    /** Column name IsDeletePlannedPO */
    public static final String COLUMNNAME_IsDeletePlannedPO = "IsDeletePlannedPO";

	/** Set Delete Planned Purchase Orders	  */
	public void setIsDeletePlannedPO (boolean IsDeletePlannedPO);

	/** Get Delete Planned Purchase Orders	  */
	public boolean isDeletePlannedPO();

    /** Column name IsDeleteUnconfirmedProduction */
    public static final String COLUMNNAME_IsDeleteUnconfirmedProduction = "IsDeleteUnconfirmedProduction";

	/** Set Delete Unconfirmed Production	  */
	public void setIsDeleteUnconfirmedProduction (boolean IsDeleteUnconfirmedProduction);

	/** Get Delete Unconfirmed Production	  */
	public boolean isDeleteUnconfirmedProduction();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_ReplenishPlan_ID */
    public static final String COLUMNNAME_M_ReplenishPlan_ID = "M_ReplenishPlan_ID";

	/** Set M_ReplenishPlan ID	  */
	public void setM_ReplenishPlan_ID (int M_ReplenishPlan_ID);

	/** Get M_ReplenishPlan ID	  */
	public int getM_ReplenishPlan_ID();

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

    /** Column name PlannedProductionReport */
    public static final String COLUMNNAME_PlannedProductionReport = "PlannedProductionReport";

	/** Set Generate Planned Production Report	  */
	public void setPlannedProductionReport (String PlannedProductionReport);

	/** Get Generate Planned Production Report	  */
	public String getPlannedProductionReport();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ReplenishPlanInitialSetup */
    public static final String COLUMNNAME_ReplenishPlanInitialSetup = "ReplenishPlanInitialSetup";

	/** Set Replenish Plan Initial Setup	  */
	public void setReplenishPlanInitialSetup (String ReplenishPlanInitialSetup);

	/** Get Replenish Plan Initial Setup	  */
	public String getReplenishPlanInitialSetup();

    /** Column name SuggestedRequisitionReport */
    public static final String COLUMNNAME_SuggestedRequisitionReport = "SuggestedRequisitionReport";

	/** Set Generate Suggested Requisition Report	  */
	public void setSuggestedRequisitionReport (String SuggestedRequisitionReport);

	/** Get Generate Suggested Requisition Report	  */
	public String getSuggestedRequisitionReport();

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

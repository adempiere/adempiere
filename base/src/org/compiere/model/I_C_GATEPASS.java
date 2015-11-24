/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import org.compiere.util.KeyNamePair;

import java.math.BigDecimal;
import java.sql.Timestamp;

/** Generated Interface for C_GATEPASS
 *  @author Adempiere (generated) 
 *  @version R/3
 */
public interface I_C_GATEPASS 
{

    /** TableName=C_GATEPASS */
    public static final String Table_Name = "C_GATEPASS";

    /** AD_Table_ID=1000076 */
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
	public void setAD_Org_ID(int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_GATEPASS_ID */
    public static final String COLUMNNAME_C_GATEPASS_ID = "C_GATEPASS_ID";

	/** Set Gate Pass for Invoice Form ID	  */
	public void setC_GATEPASS_ID(int C_GATEPASS_ID);

	/** Get Gate Pass for Invoice Form ID	  */
	public int getC_GATEPASS_ID();

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

    /** Column name DLExpiryDate */
    public static final String COLUMNNAME_DLExpiryDate = "DLExpiryDate";

	/** Set Driver Licence Expiry Date	  */
	public void setDLExpiryDate(Timestamp DLExpiryDate);

	/** Get Driver Licence Expiry Date	  */
	public Timestamp getDLExpiryDate();

    /** Column name DLNumber */
    public static final String COLUMNNAME_DLNumber = "DLNumber";

	/** Set DL No	  */
	public void setDLNumber(int DLNumber);

	/** Get DL No	  */
	public int getDLNumber();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription(String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Destination */
    public static final String COLUMNNAME_Destination = "Destination";

	/** Set Destination	  */
	public void setDestination(String Destination);

	/** Get Destination	  */
	public String getDestination();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction(String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocDate */
    public static final String COLUMNNAME_DocDate = "DocDate";

	/** Set Document Date	  */
	public void setDocDate(Timestamp DocDate);

	/** Get Document Date	  */
	public Timestamp getDocDate();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus(String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DrName */
    public static final String COLUMNNAME_DrName = "DrName";

	/** Set Driver Name	  */
	public void setDrName(String DrName);

	/** Get Driver Name	  */
	public String getDrName();

    /** Column name DryWeight */
    public static final String COLUMNNAME_DryWeight = "DryWeight";

	/** Set Dry Weight	  */
	public void setDryWeight(BigDecimal DryWeight);

	/** Get Dry Weight	  */
	public BigDecimal getDryWeight();

    /** Column name GRNumber */
    public static final String COLUMNNAME_GRNumber = "GRNumber";

	/** Set GR Number	  */
	public void setGRNumber(int GRNumber);

	/** Get GR Number	  */
	public int getGRNumber();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive(boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved(boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name MatWeight */
    public static final String COLUMNNAME_MatWeight = "MatWeight";

	/** Set Material Weight	  */
	public void setMatWeight(BigDecimal MatWeight);

	/** Get Material Weight	  */
	public BigDecimal getMatWeight();

    /** Column name PANCARD */
    public static final String COLUMNNAME_PANCARD = "PANCARD";

	/** Set PANCARD	  */
	public void setPANCARD(int PANCARD);

	/** Get PANCARD	  */
	public int getPANCARD();

    /** Column name PayLoad */
    public static final String COLUMNNAME_PayLoad = "PayLoad";

	/** Set Pay Load	  */
	public void setPayLoad(BigDecimal PayLoad);

	/** Get Pay Load	  */
	public BigDecimal getPayLoad();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed(boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing(boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TotalWeight */
    public static final String COLUMNNAME_TotalWeight = "TotalWeight";

	/** Set Total Weight	  */
	public void setTotalWeight(BigDecimal TotalWeight);

	/** Get Total Weight	  */
	public BigDecimal getTotalWeight();

    /** Column name Transporter */
    public static final String COLUMNNAME_Transporter = "Transporter";

	/** Set Transporter	  */
	public void setTransporter(String Transporter);

	/** Get Transporter	  */
	public String getTransporter();

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

    /** Column name VMake */
    public static final String COLUMNNAME_VMake = "VMake";

	/** Set Vehicle Make	  */
	public void setVMake(String VMake);

	/** Get Vehicle Make	  */
	public String getVMake();

    /** Column name VNumber */
    public static final String COLUMNNAME_VNumber = "VNumber";

	/** Set Vehicle Number	  */
	public void setVNumber(String VNumber);

	/** Get Vehicle Number	  */
	public String getVNumber();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue(String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}

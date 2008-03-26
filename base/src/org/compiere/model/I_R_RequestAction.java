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

/** Generated Interface for R_RequestAction
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_R_RequestAction 
{

    /** TableName=R_RequestAction */
    public static final String Table_Name = "R_RequestAction";

    /** AD_Table_ID=418 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public I_AD_Role getAD_Role() throws Exception;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws Exception;

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

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public I_C_Activity getC_Activity() throws Exception;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws Exception;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws Exception;

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public I_C_Order getC_Order() throws Exception;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws Exception;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws Exception;

    /** Column name ConfidentialType */
    public static final String COLUMNNAME_ConfidentialType = "ConfidentialType";

	/** Set Confidentiality.
	  * Type of Confidentiality
	  */
	public void setConfidentialType (String ConfidentialType);

	/** Get Confidentiality.
	  * Type of Confidentiality
	  */
	public String getConfidentialType();

    /** Column name DateCompletePlan */
    public static final String COLUMNNAME_DateCompletePlan = "DateCompletePlan";

	/** Set Complete Plan.
	  * Planned Completion Date
	  */
	public void setDateCompletePlan (Timestamp DateCompletePlan);

	/** Get Complete Plan.
	  * Planned Completion Date
	  */
	public Timestamp getDateCompletePlan();

    /** Column name DateNextAction */
    public static final String COLUMNNAME_DateNextAction = "DateNextAction";

	/** Set Date next action.
	  * Date that this request should be acted on
	  */
	public void setDateNextAction (Timestamp DateNextAction);

	/** Get Date next action.
	  * Date that this request should be acted on
	  */
	public Timestamp getDateNextAction();

    /** Column name DateStartPlan */
    public static final String COLUMNNAME_DateStartPlan = "DateStartPlan";

	/** Set Start Plan.
	  * Planned Start Date
	  */
	public void setDateStartPlan (Timestamp DateStartPlan);

	/** Get Start Plan.
	  * Planned Start Date
	  */
	public Timestamp getDateStartPlan();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name IsEscalated */
    public static final String COLUMNNAME_IsEscalated = "IsEscalated";

	/** Set Escalated.
	  * This request has been escalated
	  */
	public void setIsEscalated (String IsEscalated);

	/** Get Escalated.
	  * This request has been escalated
	  */
	public String getIsEscalated();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (String IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public String getIsInvoiced();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (String IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public String getIsSelfService();

    /** Column name M_InOut_ID */
    public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

	/** Set Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID);

	/** Get Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getM_InOut_ID();

	public I_M_InOut getM_InOut() throws Exception;

    /** Column name M_ProductSpent_ID */
    public static final String COLUMNNAME_M_ProductSpent_ID = "M_ProductSpent_ID";

	/** Set Product Used.
	  * Product/Resource/Service used in Request
	  */
	public void setM_ProductSpent_ID (int M_ProductSpent_ID);

	/** Get Product Used.
	  * Product/Resource/Service used in Request
	  */
	public int getM_ProductSpent_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws Exception;

    /** Column name M_RMA_ID */
    public static final String COLUMNNAME_M_RMA_ID = "M_RMA_ID";

	/** Set RMA.
	  * Return Material Authorization
	  */
	public void setM_RMA_ID (int M_RMA_ID);

	/** Get RMA.
	  * Return Material Authorization
	  */
	public int getM_RMA_ID();

	public I_M_RMA getM_RMA() throws Exception;

    /** Column name NullColumns */
    public static final String COLUMNNAME_NullColumns = "NullColumns";

	/** Set Null Columns.
	  * Columns with NULL value
	  */
	public void setNullColumns (String NullColumns);

	/** Get Null Columns.
	  * Columns with NULL value
	  */
	public String getNullColumns();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority();

    /** Column name PriorityUser */
    public static final String COLUMNNAME_PriorityUser = "PriorityUser";

	/** Set User Importance.
	  * Priority of the issue for the User
	  */
	public void setPriorityUser (String PriorityUser);

	/** Get User Importance.
	  * Priority of the issue for the User
	  */
	public String getPriorityUser();

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

    /** Column name QtyPlan */
    public static final String COLUMNNAME_QtyPlan = "QtyPlan";

	/** Set Quantity Plan.
	  * Planned Quantity
	  */
	public void setQtyPlan (BigDecimal QtyPlan);

	/** Get Quantity Plan.
	  * Planned Quantity
	  */
	public BigDecimal getQtyPlan();

    /** Column name QtySpent */
    public static final String COLUMNNAME_QtySpent = "QtySpent";

	/** Set Quantity Used.
	  * Quantity used for this event
	  */
	public void setQtySpent (BigDecimal QtySpent);

	/** Get Quantity Used.
	  * Quantity used for this event
	  */
	public BigDecimal getQtySpent();

    /** Column name R_Category_ID */
    public static final String COLUMNNAME_R_Category_ID = "R_Category_ID";

	/** Set Category.
	  * Request Category
	  */
	public void setR_Category_ID (int R_Category_ID);

	/** Get Category.
	  * Request Category
	  */
	public int getR_Category_ID();

	public I_R_Category getR_Category() throws Exception;

    /** Column name R_Group_ID */
    public static final String COLUMNNAME_R_Group_ID = "R_Group_ID";

	/** Set Group.
	  * Request Group
	  */
	public void setR_Group_ID (int R_Group_ID);

	/** Get Group.
	  * Request Group
	  */
	public int getR_Group_ID();

	public I_R_Group getR_Group() throws Exception;

    /** Column name R_RequestAction_ID */
    public static final String COLUMNNAME_R_RequestAction_ID = "R_RequestAction_ID";

	/** Set Request History.
	  * Request has been changed
	  */
	public void setR_RequestAction_ID (int R_RequestAction_ID);

	/** Get Request History.
	  * Request has been changed
	  */
	public int getR_RequestAction_ID();

    /** Column name R_RequestType_ID */
    public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";

	/** Set Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID);

	/** Get Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID();

	public I_R_RequestType getR_RequestType() throws Exception;

    /** Column name R_Request_ID */
    public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";

	/** Set Request.
	  * Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID);

	/** Get Request.
	  * Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID();

	public I_R_Request getR_Request() throws Exception;

    /** Column name R_Resolution_ID */
    public static final String COLUMNNAME_R_Resolution_ID = "R_Resolution_ID";

	/** Set Resolution.
	  * Request Resolution
	  */
	public void setR_Resolution_ID (int R_Resolution_ID);

	/** Get Resolution.
	  * Request Resolution
	  */
	public int getR_Resolution_ID();

	public I_R_Resolution getR_Resolution() throws Exception;

    /** Column name R_Status_ID */
    public static final String COLUMNNAME_R_Status_ID = "R_Status_ID";

	/** Set Status.
	  * Request Status
	  */
	public void setR_Status_ID (int R_Status_ID);

	/** Get Status.
	  * Request Status
	  */
	public int getR_Status_ID();

	public I_R_Status getR_Status() throws Exception;

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name Summary */
    public static final String COLUMNNAME_Summary = "Summary";

	/** Set Summary.
	  * Textual summary of this request
	  */
	public void setSummary (String Summary);

	/** Get Summary.
	  * Textual summary of this request
	  */
	public String getSummary();

    /** Column name TaskStatus */
    public static final String COLUMNNAME_TaskStatus = "TaskStatus";

	/** Set Task Status.
	  * Status of the Task
	  */
	public void setTaskStatus (String TaskStatus);

	/** Get Task Status.
	  * Status of the Task
	  */
	public String getTaskStatus();
}

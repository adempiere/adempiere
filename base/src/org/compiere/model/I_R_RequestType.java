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

/** Generated Interface for R_RequestType
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_R_RequestType 
{

    /** TableName=R_RequestType */
    public static final String Table_Name = "R_RequestType";

    /** AD_Table_ID=529 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AutoDueDateDays */
    public static final String COLUMNNAME_AutoDueDateDays = "AutoDueDateDays";

	/** Set Auto Due Date Days.
	  * Automatic Due Date Days
	  */
	public void setAutoDueDateDays (int AutoDueDateDays);

	/** Get Auto Due Date Days.
	  * Automatic Due Date Days
	  */
	public int getAutoDueDateDays();

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

    /** Column name DueDateTolerance */
    public static final String COLUMNNAME_DueDateTolerance = "DueDateTolerance";

	/** Set Due Date Tolerance.
	  * Tolerance in days between the Date Next Action and the date the request is regarded as overdue
	  */
	public void setDueDateTolerance (int DueDateTolerance);

	/** Get Due Date Tolerance.
	  * Tolerance in days between the Date Next Action and the date the request is regarded as overdue
	  */
	public int getDueDateTolerance();

    /** Column name IsAutoChangeRequest */
    public static final String COLUMNNAME_IsAutoChangeRequest = "IsAutoChangeRequest";

	/** Set Create Change Request.
	  * Automatically create BOM (Engineering) Change Request
	  */
	public void setIsAutoChangeRequest (boolean IsAutoChangeRequest);

	/** Get Create Change Request.
	  * Automatically create BOM (Engineering) Change Request
	  */
	public boolean isAutoChangeRequest();

    /** Column name IsConfidentialInfo */
    public static final String COLUMNNAME_IsConfidentialInfo = "IsConfidentialInfo";

	/** Set Confidential Info.
	  * Can enter confidential information
	  */
	public void setIsConfidentialInfo (boolean IsConfidentialInfo);

	/** Get Confidential Info.
	  * Can enter confidential information
	  */
	public boolean isConfidentialInfo();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsEMailWhenDue */
    public static final String COLUMNNAME_IsEMailWhenDue = "IsEMailWhenDue";

	/** Set EMail when Due.
	  * Send EMail when Request becomes due
	  */
	public void setIsEMailWhenDue (boolean IsEMailWhenDue);

	/** Get EMail when Due.
	  * Send EMail when Request becomes due
	  */
	public boolean isEMailWhenDue();

    /** Column name IsEMailWhenOverdue */
    public static final String COLUMNNAME_IsEMailWhenOverdue = "IsEMailWhenOverdue";

	/** Set EMail when Overdue.
	  * Send EMail when Request becomes overdue
	  */
	public void setIsEMailWhenOverdue (boolean IsEMailWhenOverdue);

	/** Get EMail when Overdue.
	  * Send EMail when Request becomes overdue
	  */
	public boolean isEMailWhenOverdue();

    /** Column name IsIndexed */
    public static final String COLUMNNAME_IsIndexed = "IsIndexed";

	/** Set Indexed.
	  * Index the document for the internal search engine
	  */
	public void setIsIndexed (boolean IsIndexed);

	/** Get Indexed.
	  * Index the document for the internal search engine
	  */
	public boolean isIndexed();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

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

    /** Column name R_StatusCategory_ID */
    public static final String COLUMNNAME_R_StatusCategory_ID = "R_StatusCategory_ID";

	/** Set Status Category.
	  * Request Status Category
	  */
	public void setR_StatusCategory_ID (int R_StatusCategory_ID);

	/** Get Status Category.
	  * Request Status Category
	  */
	public int getR_StatusCategory_ID();

	public I_R_StatusCategory getR_StatusCategory() throws Exception;
}

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

/** Generated Interface for C_BP_EDI
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_C_BP_EDI 
{

    /** TableName=C_BP_EDI */
    public static final String Table_Name = "C_BP_EDI";

    /** AD_Table_ID=366 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Sequence_ID */
    public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";

	/** Set Sequence.
	  * Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID);

	/** Get Sequence.
	  * Document Sequence
	  */
	public int getAD_Sequence_ID();

    /** Column name C_BP_EDI_ID */
    public static final String COLUMNNAME_C_BP_EDI_ID = "C_BP_EDI_ID";

	/** Set EDI Definition.
	  * Electronic Data Interchange
	  */
	public void setC_BP_EDI_ID (int C_BP_EDI_ID);

	/** Get EDI Definition.
	  * Electronic Data Interchange
	  */
	public int getC_BP_EDI_ID();

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

    /** Column name CustomerNo */
    public static final String COLUMNNAME_CustomerNo = "CustomerNo";

	/** Set Customer No.
	  * EDI Identification Number 
	  */
	public void setCustomerNo (String CustomerNo);

	/** Get Customer No.
	  * EDI Identification Number 
	  */
	public String getCustomerNo();

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

    /** Column name EDIType */
    public static final String COLUMNNAME_EDIType = "EDIType";

	/** Set EDI Type	  */
	public void setEDIType (String EDIType);

	/** Get EDI Type	  */
	public String getEDIType();

    /** Column name EMail_Error_To */
    public static final String COLUMNNAME_EMail_Error_To = "EMail_Error_To";

	/** Set Error EMail.
	  * Email address to send error messages to
	  */
	public void setEMail_Error_To (String EMail_Error_To);

	/** Get Error EMail.
	  * Email address to send error messages to
	  */
	public String getEMail_Error_To();

    /** Column name EMail_From */
    public static final String COLUMNNAME_EMail_From = "EMail_From";

	/** Set From EMail.
	  * Full EMail address used to send requests - e.g. edi@organization.com
	  */
	public void setEMail_From (String EMail_From);

	/** Get From EMail.
	  * Full EMail address used to send requests - e.g. edi@organization.com
	  */
	public String getEMail_From();

    /** Column name EMail_From_Pwd */
    public static final String COLUMNNAME_EMail_From_Pwd = "EMail_From_Pwd";

	/** Set From EMail Password.
	  * Password of the sending EMail address
	  */
	public void setEMail_From_Pwd (String EMail_From_Pwd);

	/** Get From EMail Password.
	  * Password of the sending EMail address
	  */
	public String getEMail_From_Pwd();

    /** Column name EMail_From_Uid */
    public static final String COLUMNNAME_EMail_From_Uid = "EMail_From_Uid";

	/** Set From EMail User ID.
	  * User ID of the sending EMail address (on default SMTP Host) - e.g. edi
	  */
	public void setEMail_From_Uid (String EMail_From_Uid);

	/** Get From EMail User ID.
	  * User ID of the sending EMail address (on default SMTP Host) - e.g. edi
	  */
	public String getEMail_From_Uid();

    /** Column name EMail_Info_To */
    public static final String COLUMNNAME_EMail_Info_To = "EMail_Info_To";

	/** Set Info EMail.
	  * EMail address to send informational messages and copies
	  */
	public void setEMail_Info_To (String EMail_Info_To);

	/** Get Info EMail.
	  * EMail address to send informational messages and copies
	  */
	public String getEMail_Info_To();

    /** Column name EMail_To */
    public static final String COLUMNNAME_EMail_To = "EMail_To";

	/** Set To EMail.
	  * EMail address to send requests to - e.g. edi@manufacturer.com 
	  */
	public void setEMail_To (String EMail_To);

	/** Get To EMail.
	  * EMail address to send requests to - e.g. edi@manufacturer.com 
	  */
	public String getEMail_To();

    /** Column name IsAudited */
    public static final String COLUMNNAME_IsAudited = "IsAudited";

	/** Set Activate Audit.
	  * Activate Audit Trail of what numbers are generated
	  */
	public void setIsAudited (boolean IsAudited);

	/** Get Activate Audit.
	  * Activate Audit Trail of what numbers are generated
	  */
	public boolean isAudited();

    /** Column name IsInfoSent */
    public static final String COLUMNNAME_IsInfoSent = "IsInfoSent";

	/** Set Send Info.
	  * Send informational messages and copies
	  */
	public void setIsInfoSent (boolean IsInfoSent);

	/** Get Send Info.
	  * Send informational messages and copies
	  */
	public boolean isInfoSent();

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

	public I_M_Warehouse getM_Warehouse() throws Exception;

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

    /** Column name ReceiveInquiryReply */
    public static final String COLUMNNAME_ReceiveInquiryReply = "ReceiveInquiryReply";

	/** Set Received Inquiry Reply	  */
	public void setReceiveInquiryReply (boolean ReceiveInquiryReply);

	/** Get Received Inquiry Reply	  */
	public boolean isReceiveInquiryReply();

    /** Column name ReceiveOrderReply */
    public static final String COLUMNNAME_ReceiveOrderReply = "ReceiveOrderReply";

	/** Set Receive Order Reply	  */
	public void setReceiveOrderReply (boolean ReceiveOrderReply);

	/** Get Receive Order Reply	  */
	public boolean isReceiveOrderReply();

    /** Column name SendInquiry */
    public static final String COLUMNNAME_SendInquiry = "SendInquiry";

	/** Set Send Inquiry.
	  * Quantity Availability Inquiry
	  */
	public void setSendInquiry (boolean SendInquiry);

	/** Get Send Inquiry.
	  * Quantity Availability Inquiry
	  */
	public boolean isSendInquiry();

    /** Column name SendOrder */
    public static final String COLUMNNAME_SendOrder = "SendOrder";

	/** Set Send Order	  */
	public void setSendOrder (boolean SendOrder);

	/** Get Send Order	  */
	public boolean isSendOrder();
}

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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for C_RfQ
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:44.187
     */
    public interface I_C_RfQ 
{

    /** TableName=C_RfQ */
    public static final String Table_Name = "C_RfQ";

    /** AD_Table_ID=677 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = new BigDecimal(1);

    /** Load Meta Data */

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

	public I_AD_User getI_AD_User() throws Exception;

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getI_C_BPartner_Location() throws Exception;

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

	public I_C_Currency getI_C_Currency() throws Exception;

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

    /** Column name C_RfQ_ID */
    public static final String COLUMNNAME_C_RfQ_ID = "C_RfQ_ID";

	/** Set RfQ.
	  * Request for Quotation
	  */
	public void setC_RfQ_ID (int C_RfQ_ID);

	/** Get RfQ.
	  * Request for Quotation
	  */
	public int getC_RfQ_ID();

    /** Column name C_RfQ_Topic_ID */
    public static final String COLUMNNAME_C_RfQ_Topic_ID = "C_RfQ_Topic_ID";

	/** Set RfQ Topic.
	  * Topic for Request for Quotations
	  */
	public void setC_RfQ_Topic_ID (int C_RfQ_Topic_ID);

	/** Get RfQ Topic.
	  * Topic for Request for Quotations
	  */
	public int getC_RfQ_Topic_ID();

	public I_C_RfQ_Topic getI_C_RfQ_Topic() throws Exception;

    /** Column name CopyLines */
    public static final String COLUMNNAME_CopyLines = "CopyLines";

	/** Set Copy Lines	  */
	public void setCopyLines (String CopyLines);

	/** Get Copy Lines	  */
	public String getCopyLines();

    /** Column name CreatePO */
    public static final String COLUMNNAME_CreatePO = "CreatePO";

	/** Set Create PO.
	  * Create Purchase Order
	  */
	public void setCreatePO (String CreatePO);

	/** Get Create PO.
	  * Create Purchase Order
	  */
	public String getCreatePO();

    /** Column name CreateSO */
    public static final String COLUMNNAME_CreateSO = "CreateSO";

	/** Set Create SO	  */
	public void setCreateSO (String CreateSO);

	/** Get Create SO	  */
	public String getCreateSO();

    /** Column name DateResponse */
    public static final String COLUMNNAME_DateResponse = "DateResponse";

	/** Set Response Date.
	  * Date of the Response
	  */
	public void setDateResponse (Timestamp DateResponse);

	/** Get Response Date.
	  * Date of the Response
	  */
	public Timestamp getDateResponse();

    /** Column name DateWorkComplete */
    public static final String COLUMNNAME_DateWorkComplete = "DateWorkComplete";

	/** Set Work Complete.
	  * Date when work is (planned to be) complete
	  */
	public void setDateWorkComplete (Timestamp DateWorkComplete);

	/** Get Work Complete.
	  * Date when work is (planned to be) complete
	  */
	public Timestamp getDateWorkComplete();

    /** Column name DateWorkStart */
    public static final String COLUMNNAME_DateWorkStart = "DateWorkStart";

	/** Set Work Start.
	  * Date when work is (planned to be) started
	  */
	public void setDateWorkStart (Timestamp DateWorkStart);

	/** Get Work Start.
	  * Date when work is (planned to be) started
	  */
	public Timestamp getDateWorkStart();

    /** Column name DeliveryDays */
    public static final String COLUMNNAME_DeliveryDays = "DeliveryDays";

	/** Set Delivery Days.
	  * Number of Days (planned) until Delivery
	  */
	public void setDeliveryDays (int DeliveryDays);

	/** Get Delivery Days.
	  * Number of Days (planned) until Delivery
	  */
	public int getDeliveryDays();

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

    /** Column name IsInvitedVendorsOnly */
    public static final String COLUMNNAME_IsInvitedVendorsOnly = "IsInvitedVendorsOnly";

	/** Set Invited Vendors Only.
	  * Only invited vendors can respond to an RfQ
	  */
	public void setIsInvitedVendorsOnly (boolean IsInvitedVendorsOnly);

	/** Get Invited Vendors Only.
	  * Only invited vendors can respond to an RfQ
	  */
	public boolean isInvitedVendorsOnly();

    /** Column name IsQuoteAllQty */
    public static final String COLUMNNAME_IsQuoteAllQty = "IsQuoteAllQty";

	/** Set Quote All Quantities.
	  * Suppliers are requested to provide responses for all quantities
	  */
	public void setIsQuoteAllQty (boolean IsQuoteAllQty);

	/** Get Quote All Quantities.
	  * Suppliers are requested to provide responses for all quantities
	  */
	public boolean isQuoteAllQty();

    /** Column name IsQuoteTotalAmt */
    public static final String COLUMNNAME_IsQuoteTotalAmt = "IsQuoteTotalAmt";

	/** Set Quote Total Amt.
	  * The respnse can have just the total amount for the RfQ
	  */
	public void setIsQuoteTotalAmt (boolean IsQuoteTotalAmt);

	/** Get Quote Total Amt.
	  * The respnse can have just the total amount for the RfQ
	  */
	public boolean isQuoteTotalAmt();

    /** Column name IsRfQResponseAccepted */
    public static final String COLUMNNAME_IsRfQResponseAccepted = "IsRfQResponseAccepted";

	/** Set Responses Accepted.
	  * Are Resonses to the Request for Quotation accepted
	  */
	public void setIsRfQResponseAccepted (boolean IsRfQResponseAccepted);

	/** Get Responses Accepted.
	  * Are Resonses to the Request for Quotation accepted
	  */
	public boolean isRfQResponseAccepted();

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

    /** Column name Margin */
    public static final String COLUMNNAME_Margin = "Margin";

	/** Set Margin %.
	  * Margin for a product as a percentage
	  */
	public void setMargin (BigDecimal Margin);

	/** Get Margin %.
	  * Margin for a product as a percentage
	  */
	public BigDecimal getMargin();

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

    /** Column name PublishRfQ */
    public static final String COLUMNNAME_PublishRfQ = "PublishRfQ";

	/** Set Publish RfQ	  */
	public void setPublishRfQ (String PublishRfQ);

	/** Get Publish RfQ	  */
	public String getPublishRfQ();

    /** Column name QuoteType */
    public static final String COLUMNNAME_QuoteType = "QuoteType";

	/** Set RfQ Type.
	  * Request for Quotation Type
	  */
	public void setQuoteType (String QuoteType);

	/** Get RfQ Type.
	  * Request for Quotation Type
	  */
	public String getQuoteType();

    /** Column name RankRfQ */
    public static final String COLUMNNAME_RankRfQ = "RankRfQ";

	/** Set Rank RfQ	  */
	public void setRankRfQ (String RankRfQ);

	/** Get Rank RfQ	  */
	public String getRankRfQ();

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
}

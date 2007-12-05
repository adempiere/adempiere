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

/** Generated Interface for B_Offer
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_B_Offer 
{

    /** TableName=B_Offer */
    public static final String Table_Name = "B_Offer";

    /** AD_Table_ID=682 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

	public I_AD_User getAD_User() throws Exception;

    /** Column name B_Offer_ID */
    public static final String COLUMNNAME_B_Offer_ID = "B_Offer_ID";

	/** Set Offer.
	  * Offer for a Topic
	  */
	public void setB_Offer_ID (int B_Offer_ID);

	/** Get Offer.
	  * Offer for a Topic
	  */
	public int getB_Offer_ID();

    /** Column name B_SellerFunds_ID */
    public static final String COLUMNNAME_B_SellerFunds_ID = "B_SellerFunds_ID";

	/** Set Seller Funds.
	  * Seller Funds from Offers on Topics
	  */
	public void setB_SellerFunds_ID (int B_SellerFunds_ID);

	/** Get Seller Funds.
	  * Seller Funds from Offers on Topics
	  */
	public int getB_SellerFunds_ID();

	public I_B_SellerFunds getB_SellerFunds() throws Exception;

    /** Column name B_Topic_ID */
    public static final String COLUMNNAME_B_Topic_ID = "B_Topic_ID";

	/** Set Topic.
	  * Auction Topic
	  */
	public void setB_Topic_ID (int B_Topic_ID);

	/** Get Topic.
	  * Auction Topic
	  */
	public int getB_Topic_ID();

	public I_B_Topic getB_Topic() throws Exception;

    /** Column name IsWillingToCommit */
    public static final String COLUMNNAME_IsWillingToCommit = "IsWillingToCommit";

	/** Set Willing to commit	  */
	public void setIsWillingToCommit (boolean IsWillingToCommit);

	/** Get Willing to commit	  */
	public boolean isWillingToCommit();

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

    /** Column name PrivateNote */
    public static final String COLUMNNAME_PrivateNote = "PrivateNote";

	/** Set Private Note.
	  * Private Note - not visible to the other parties
	  */
	public void setPrivateNote (String PrivateNote);

	/** Get Private Note.
	  * Private Note - not visible to the other parties
	  */
	public String getPrivateNote();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}

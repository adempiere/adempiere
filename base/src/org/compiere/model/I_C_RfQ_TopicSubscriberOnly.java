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

/** Generated Interface for C_RfQ_TopicSubscriberOnly
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_RfQ_TopicSubscriberOnly 
{

    /** TableName=C_RfQ_TopicSubscriberOnly */
    public static final String Table_Name = "C_RfQ_TopicSubscriberOnly";

    /** AD_Table_ID=747 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name C_RfQ_TopicSubscriberOnly_ID */
    public static final String COLUMNNAME_C_RfQ_TopicSubscriberOnly_ID = "C_RfQ_TopicSubscriberOnly_ID";

	/** Set RfQ Topic Subscriber Restriction.
	  * Include Subscriber only for certain products or product categories
	  */
	public void setC_RfQ_TopicSubscriberOnly_ID (int C_RfQ_TopicSubscriberOnly_ID);

	/** Get RfQ Topic Subscriber Restriction.
	  * Include Subscriber only for certain products or product categories
	  */
	public int getC_RfQ_TopicSubscriberOnly_ID();

    /** Column name C_RfQ_TopicSubscriber_ID */
    public static final String COLUMNNAME_C_RfQ_TopicSubscriber_ID = "C_RfQ_TopicSubscriber_ID";

	/** Set RfQ Subscriber.
	  * Request for Quotation Topic Subscriber
	  */
	public void setC_RfQ_TopicSubscriber_ID (int C_RfQ_TopicSubscriber_ID);

	/** Get RfQ Subscriber.
	  * Request for Quotation Topic Subscriber
	  */
	public int getC_RfQ_TopicSubscriber_ID();

	public I_C_RfQ_TopicSubscriber getC_RfQ_TopicSubscriber() throws Exception;

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

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws Exception;

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
}

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_RfQ_TopicSubscriberOnly
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
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

	public I_C_RfQ_TopicSubscriber getC_RfQ_TopicSubscriber() throws RuntimeException;

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

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

	public I_M_Product getM_Product() throws RuntimeException;
}

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

/** Generated Interface for C_Subscription_Delivery
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_C_Subscription_Delivery 
{

    /** TableName=C_Subscription_Delivery */
    public static final String Table_Name = "C_Subscription_Delivery";

    /** AD_Table_ID=667 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name C_Subscription_Delivery_ID */
    public static final String COLUMNNAME_C_Subscription_Delivery_ID = "C_Subscription_Delivery_ID";

	/** Set Subscription Delivery.
	  * Optional Delivery Record for a Subscription
	  */
	public void setC_Subscription_Delivery_ID (int C_Subscription_Delivery_ID);

	/** Get Subscription Delivery.
	  * Optional Delivery Record for a Subscription
	  */
	public int getC_Subscription_Delivery_ID();

    /** Column name C_Subscription_ID */
    public static final String COLUMNNAME_C_Subscription_ID = "C_Subscription_ID";

	/** Set Subscription.
	  * Subscription of a Business Partner of a Product to renew
	  */
	public void setC_Subscription_ID (int C_Subscription_ID);

	/** Get Subscription.
	  * Subscription of a Business Partner of a Product to renew
	  */
	public int getC_Subscription_ID();

	public I_C_Subscription getC_Subscription() throws RuntimeException;
}

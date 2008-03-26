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

/** Generated Interface for C_Subscription_Delivery
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
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

	public I_C_Subscription getC_Subscription() throws Exception;
}

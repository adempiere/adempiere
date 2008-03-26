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

/** Generated Interface for R_StandardResponse
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_R_StandardResponse 
{

    /** TableName=R_StandardResponse */
    public static final String Table_Name = "R_StandardResponse";

    /** AD_Table_ID=775 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

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

    /** Column name R_StandardResponse_ID */
    public static final String COLUMNNAME_R_StandardResponse_ID = "R_StandardResponse_ID";

	/** Set Standard Response.
	  * Request Standard Response 
	  */
	public void setR_StandardResponse_ID (int R_StandardResponse_ID);

	/** Get Standard Response.
	  * Request Standard Response 
	  */
	public int getR_StandardResponse_ID();

    /** Column name ResponseText */
    public static final String COLUMNNAME_ResponseText = "ResponseText";

	/** Set Response Text.
	  * Request Response Text
	  */
	public void setResponseText (String ResponseText);

	/** Get Response Text.
	  * Request Response Text
	  */
	public String getResponseText();
}

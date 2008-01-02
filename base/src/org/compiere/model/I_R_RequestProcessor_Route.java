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

/** Generated Interface for R_RequestProcessor_Route
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_R_RequestProcessor_Route 
{

    /** TableName=R_RequestProcessor_Route */
    public static final String Table_Name = "R_RequestProcessor_Route";

    /** AD_Table_ID=474 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name Keyword */
    public static final String COLUMNNAME_Keyword = "Keyword";

	/** Set Keyword.
	  * Case insensitive keyword
	  */
	public void setKeyword (String Keyword);

	/** Get Keyword.
	  * Case insensitive keyword
	  */
	public String getKeyword();

    /** Column name R_RequestProcessor_ID */
    public static final String COLUMNNAME_R_RequestProcessor_ID = "R_RequestProcessor_ID";

	/** Set Request Processor.
	  * Processor for Requests
	  */
	public void setR_RequestProcessor_ID (int R_RequestProcessor_ID);

	/** Get Request Processor.
	  * Processor for Requests
	  */
	public int getR_RequestProcessor_ID();

	public I_R_RequestProcessor getR_RequestProcessor() throws Exception;

    /** Column name R_RequestProcessor_Route_ID */
    public static final String COLUMNNAME_R_RequestProcessor_Route_ID = "R_RequestProcessor_Route_ID";

	/** Set Request Routing.
	  * Automatic routing of requests
	  */
	public void setR_RequestProcessor_Route_ID (int R_RequestProcessor_Route_ID);

	/** Get Request Routing.
	  * Automatic routing of requests
	  */
	public int getR_RequestProcessor_Route_ID();

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}

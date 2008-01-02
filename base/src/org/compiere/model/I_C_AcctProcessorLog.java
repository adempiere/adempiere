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

/** Generated Interface for C_AcctProcessorLog
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_C_AcctProcessorLog 
{

    /** TableName=C_AcctProcessorLog */
    public static final String Table_Name = "C_AcctProcessorLog";

    /** AD_Table_ID=694 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name BinaryData */
    public static final String COLUMNNAME_BinaryData = "BinaryData";

	/** Set BinaryData.
	  * Binary Data
	  */
	public void setBinaryData (byte[] BinaryData);

	/** Get BinaryData.
	  * Binary Data
	  */
	public byte[] getBinaryData();

    /** Column name C_AcctProcessorLog_ID */
    public static final String COLUMNNAME_C_AcctProcessorLog_ID = "C_AcctProcessorLog_ID";

	/** Set Accounting Processor Log.
	  * Result of the execution of the Accounting Processor
	  */
	public void setC_AcctProcessorLog_ID (int C_AcctProcessorLog_ID);

	/** Get Accounting Processor Log.
	  * Result of the execution of the Accounting Processor
	  */
	public int getC_AcctProcessorLog_ID();

    /** Column name C_AcctProcessor_ID */
    public static final String COLUMNNAME_C_AcctProcessor_ID = "C_AcctProcessor_ID";

	/** Set Accounting Processor.
	  * Accounting Processor/Server Parameters
	  */
	public void setC_AcctProcessor_ID (int C_AcctProcessor_ID);

	/** Get Accounting Processor.
	  * Accounting Processor/Server Parameters
	  */
	public int getC_AcctProcessor_ID();

	public I_C_AcctProcessor getC_AcctProcessor() throws Exception;

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

    /** Column name IsError */
    public static final String COLUMNNAME_IsError = "IsError";

	/** Set Error.
	  * An Error occured in the execution
	  */
	public void setIsError (boolean IsError);

	/** Get Error.
	  * An Error occured in the execution
	  */
	public boolean isError();

    /** Column name Reference */
    public static final String COLUMNNAME_Reference = "Reference";

	/** Set Reference.
	  * Reference for this record
	  */
	public void setReference (String Reference);

	/** Get Reference.
	  * Reference for this record
	  */
	public String getReference();

    /** Column name Summary */
    public static final String COLUMNNAME_Summary = "Summary";

	/** Set Summary.
	  * Textual summary of this request
	  */
	public void setSummary (String Summary);

	/** Get Summary.
	  * Textual summary of this request
	  */
	public String getSummary();

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

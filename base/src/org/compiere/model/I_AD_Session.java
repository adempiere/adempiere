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

/** Generated Interface for AD_Session
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_AD_Session 
{

    /** TableName=AD_Session */
    public static final String Table_Name = "AD_Session";

    /** AD_Table_ID=566 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

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

    /** Column name Remote_Addr */
    public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";

	/** Set Remote Addr.
	  * Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr);

	/** Get Remote Addr.
	  * Remote Address
	  */
	public String getRemote_Addr();

    /** Column name Remote_Host */
    public static final String COLUMNNAME_Remote_Host = "Remote_Host";

	/** Set Remote Host.
	  * Remote host Info
	  */
	public void setRemote_Host (String Remote_Host);

	/** Get Remote Host.
	  * Remote host Info
	  */
	public String getRemote_Host();

    /** Column name WebSession */
    public static final String COLUMNNAME_WebSession = "WebSession";

	/** Set Web Session.
	  * Web Session ID
	  */
	public void setWebSession (String WebSession);

	/** Get Web Session.
	  * Web Session ID
	  */
	public String getWebSession();
}

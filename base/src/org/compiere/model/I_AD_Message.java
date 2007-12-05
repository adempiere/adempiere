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

/** Generated Interface for AD_Message
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_AD_Message 
{

    /** TableName=AD_Message */
    public static final String Table_Name = "AD_Message";

    /** AD_Table_ID=109 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Message_ID */
    public static final String COLUMNNAME_AD_Message_ID = "AD_Message_ID";

	/** Set Message.
	  * System Message
	  */
	public void setAD_Message_ID (int AD_Message_ID);

	/** Get Message.
	  * System Message
	  */
	public int getAD_Message_ID();

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

    /** Column name MsgText */
    public static final String COLUMNNAME_MsgText = "MsgText";

	/** Set Message Text.
	  * Textual Informational, Menu or Error Message
	  */
	public void setMsgText (String MsgText);

	/** Get Message Text.
	  * Textual Informational, Menu or Error Message
	  */
	public String getMsgText();

    /** Column name MsgTip */
    public static final String COLUMNNAME_MsgTip = "MsgTip";

	/** Set Message Tip.
	  * Additional tip or help for this message
	  */
	public void setMsgTip (String MsgTip);

	/** Get Message Tip.
	  * Additional tip or help for this message
	  */
	public String getMsgTip();

    /** Column name MsgType */
    public static final String COLUMNNAME_MsgType = "MsgType";

	/** Set Message Type.
	  * Type of message (Informational, Menu or Error)
	  */
	public void setMsgType (String MsgType);

	/** Get Message Type.
	  * Type of message (Informational, Menu or Error)
	  */
	public String getMsgType();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}

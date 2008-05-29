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

/** Generated Interface for W_MailMsg
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_W_MailMsg 
{

    /** TableName=W_MailMsg */
    public static final String Table_Name = "W_MailMsg";

    /** AD_Table_ID=780 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

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

    /** Column name MailMsgType */
    public static final String COLUMNNAME_MailMsgType = "MailMsgType";

	/** Set Message Type.
	  * Mail Message Type
	  */
	public void setMailMsgType (String MailMsgType);

	/** Get Message Type.
	  * Mail Message Type
	  */
	public String getMailMsgType();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

    /** Column name Message2 */
    public static final String COLUMNNAME_Message2 = "Message2";

	/** Set Message 2.
	  * Optional second part of the EMail Message
	  */
	public void setMessage2 (String Message2);

	/** Get Message 2.
	  * Optional second part of the EMail Message
	  */
	public String getMessage2();

    /** Column name Message3 */
    public static final String COLUMNNAME_Message3 = "Message3";

	/** Set Message 3.
	  * Optional third part of the EMail Message
	  */
	public void setMessage3 (String Message3);

	/** Get Message 3.
	  * Optional third part of the EMail Message
	  */
	public String getMessage3();

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

    /** Column name Subject */
    public static final String COLUMNNAME_Subject = "Subject";

	/** Set Subject.
	  * Email Message Subject
	  */
	public void setSubject (String Subject);

	/** Get Subject.
	  * Email Message Subject
	  */
	public String getSubject();

    /** Column name W_MailMsg_ID */
    public static final String COLUMNNAME_W_MailMsg_ID = "W_MailMsg_ID";

	/** Set Mail Message.
	  * Web Store Mail Message Template
	  */
	public void setW_MailMsg_ID (int W_MailMsg_ID);

	/** Get Mail Message.
	  * Web Store Mail Message Template
	  */
	public int getW_MailMsg_ID();

    /** Column name W_Store_ID */
    public static final String COLUMNNAME_W_Store_ID = "W_Store_ID";

	/** Set Web Store.
	  * A Web Store of the Client
	  */
	public void setW_Store_ID (int W_Store_ID);

	/** Get Web Store.
	  * A Web Store of the Client
	  */
	public int getW_Store_ID();

	public I_W_Store getW_Store() throws Exception;
}

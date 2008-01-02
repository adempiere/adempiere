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

/** Generated Interface for CM_ChatEntry
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_CM_ChatEntry 
{

    /** TableName=CM_ChatEntry */
    public static final String Table_Name = "CM_ChatEntry";

    /** AD_Table_ID=877 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name CM_ChatEntryGrandParent_ID */
    public static final String COLUMNNAME_CM_ChatEntryGrandParent_ID = "CM_ChatEntryGrandParent_ID";

	/** Set Chat Entry Grandparent.
	  * Link to Grand Parent (root level)
	  */
	public void setCM_ChatEntryGrandParent_ID (int CM_ChatEntryGrandParent_ID);

	/** Get Chat Entry Grandparent.
	  * Link to Grand Parent (root level)
	  */
	public int getCM_ChatEntryGrandParent_ID();

    /** Column name CM_ChatEntryParent_ID */
    public static final String COLUMNNAME_CM_ChatEntryParent_ID = "CM_ChatEntryParent_ID";

	/** Set Chat Entry Parent.
	  * Link to direct Parent
	  */
	public void setCM_ChatEntryParent_ID (int CM_ChatEntryParent_ID);

	/** Get Chat Entry Parent.
	  * Link to direct Parent
	  */
	public int getCM_ChatEntryParent_ID();

    /** Column name CM_ChatEntry_ID */
    public static final String COLUMNNAME_CM_ChatEntry_ID = "CM_ChatEntry_ID";

	/** Set Chat Entry.
	  * Individual Chat / Discussion Entry
	  */
	public void setCM_ChatEntry_ID (int CM_ChatEntry_ID);

	/** Get Chat Entry.
	  * Individual Chat / Discussion Entry
	  */
	public int getCM_ChatEntry_ID();

    /** Column name CM_Chat_ID */
    public static final String COLUMNNAME_CM_Chat_ID = "CM_Chat_ID";

	/** Set Chat.
	  * Chat or discussion thread
	  */
	public void setCM_Chat_ID (int CM_Chat_ID);

	/** Get Chat.
	  * Chat or discussion thread
	  */
	public int getCM_Chat_ID();

	public I_CM_Chat getCM_Chat() throws Exception;

    /** Column name CharacterData */
    public static final String COLUMNNAME_CharacterData = "CharacterData";

	/** Set Character Data.
	  * Long Character Field
	  */
	public void setCharacterData (String CharacterData);

	/** Get Character Data.
	  * Long Character Field
	  */
	public String getCharacterData();

    /** Column name ChatEntryType */
    public static final String COLUMNNAME_ChatEntryType = "ChatEntryType";

	/** Set Chat Entry Type.
	  * Type of Chat/Forum Entry
	  */
	public void setChatEntryType (String ChatEntryType);

	/** Get Chat Entry Type.
	  * Type of Chat/Forum Entry
	  */
	public String getChatEntryType();

    /** Column name ConfidentialType */
    public static final String COLUMNNAME_ConfidentialType = "ConfidentialType";

	/** Set Confidentiality.
	  * Type of Confidentiality
	  */
	public void setConfidentialType (String ConfidentialType);

	/** Get Confidentiality.
	  * Type of Confidentiality
	  */
	public String getConfidentialType();

    /** Column name ModeratorStatus */
    public static final String COLUMNNAME_ModeratorStatus = "ModeratorStatus";

	/** Set Moderation Status.
	  * Status of Moderation
	  */
	public void setModeratorStatus (String ModeratorStatus);

	/** Get Moderation Status.
	  * Status of Moderation
	  */
	public String getModeratorStatus();

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
}

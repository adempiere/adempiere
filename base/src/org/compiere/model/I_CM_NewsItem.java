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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_NewsItem
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_CM_NewsItem 
{

    /** TableName=CM_NewsItem */
    public static final String Table_Name = "CM_NewsItem";

    /** AD_Table_ID=871 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name Author */
    public static final String COLUMNNAME_Author = "Author";

	/** Set Author.
	  * Author/Creator of the Entity
	  */
	public void setAuthor (String Author);

	/** Get Author.
	  * Author/Creator of the Entity
	  */
	public String getAuthor();

    /** Column name CM_NewsChannel_ID */
    public static final String COLUMNNAME_CM_NewsChannel_ID = "CM_NewsChannel_ID";

	/** Set News Channel.
	  * News channel for rss feed
	  */
	public void setCM_NewsChannel_ID (int CM_NewsChannel_ID);

	/** Get News Channel.
	  * News channel for rss feed
	  */
	public int getCM_NewsChannel_ID();

	public I_CM_NewsChannel getCM_NewsChannel() throws Exception;

    /** Column name CM_NewsItem_ID */
    public static final String COLUMNNAME_CM_NewsItem_ID = "CM_NewsItem_ID";

	/** Set News Item / Article.
	  * News item or article defines base content
	  */
	public void setCM_NewsItem_ID (int CM_NewsItem_ID);

	/** Get News Item / Article.
	  * News item or article defines base content
	  */
	public int getCM_NewsItem_ID();

    /** Column name ContentHTML */
    public static final String COLUMNNAME_ContentHTML = "ContentHTML";

	/** Set Content HTML.
	  * Contains the content itself
	  */
	public void setContentHTML (String ContentHTML);

	/** Get Content HTML.
	  * Contains the content itself
	  */
	public String getContentHTML();

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

    /** Column name LinkURL */
    public static final String COLUMNNAME_LinkURL = "LinkURL";

	/** Set LinkURL.
	  * Contains URL to a target
	  */
	public void setLinkURL (String LinkURL);

	/** Get LinkURL.
	  * Contains URL to a target
	  */
	public String getLinkURL();

    /** Column name PubDate */
    public static final String COLUMNNAME_PubDate = "PubDate";

	/** Set Publication Date.
	  * Date on which this article will / should get published
	  */
	public void setPubDate (Timestamp PubDate);

	/** Get Publication Date.
	  * Date on which this article will / should get published
	  */
	public Timestamp getPubDate();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();
}

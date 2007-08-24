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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for K_Entry
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:48.703
     */
    public interface I_K_Entry 
{

    /** TableName=K_Entry */
    public static final String Table_Name = "K_Entry";

    /** AD_Table_ID=612 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

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

	public I_AD_Session getI_AD_Session() throws Exception;

    /** Column name DescriptionURL */
    public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";

	/** Set Description URL.
	  * URL for the description
	  */
	public void setDescriptionURL (String DescriptionURL);

	/** Get Description URL.
	  * URL for the description
	  */
	public String getDescriptionURL();

    /** Column name IsPublic */
    public static final String COLUMNNAME_IsPublic = "IsPublic";

	/** Set Public.
	  * Public can read entry
	  */
	public void setIsPublic (boolean IsPublic);

	/** Get Public.
	  * Public can read entry
	  */
	public boolean isPublic();

    /** Column name K_Entry_ID */
    public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";

	/** Set Entry.
	  * Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID);

	/** Get Entry.
	  * Knowledge Entry
	  */
	public int getK_Entry_ID();

    /** Column name K_Source_ID */
    public static final String COLUMNNAME_K_Source_ID = "K_Source_ID";

	/** Set Knowledge Source.
	  * Source of a Knowledge Entry
	  */
	public void setK_Source_ID (int K_Source_ID);

	/** Get Knowledge Source.
	  * Source of a Knowledge Entry
	  */
	public int getK_Source_ID();

	public I_K_Source getI_K_Source() throws Exception;

    /** Column name K_Topic_ID */
    public static final String COLUMNNAME_K_Topic_ID = "K_Topic_ID";

	/** Set Knowledge Topic.
	  * Knowledge Topic
	  */
	public void setK_Topic_ID (int K_Topic_ID);

	/** Get Knowledge Topic.
	  * Knowledge Topic
	  */
	public int getK_Topic_ID();

	public I_K_Topic getI_K_Topic() throws Exception;

    /** Column name Keywords */
    public static final String COLUMNNAME_Keywords = "Keywords";

	/** Set Keywords.
	  * List of Keywords - separated by space, comma or semicolon
	  */
	public void setKeywords (String Keywords);

	/** Get Keywords.
	  * List of Keywords - separated by space, comma or semicolon
	  */
	public String getKeywords();

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

    /** Column name Rating */
    public static final String COLUMNNAME_Rating = "Rating";

	/** Set Rating.
	  * Classification or Importance
	  */
	public void setRating (int Rating);

	/** Get Rating.
	  * Classification or Importance
	  */
	public int getRating();

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

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}

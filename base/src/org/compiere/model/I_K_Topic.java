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

/** Generated Interface for K_Topic
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_K_Topic 
{

    /** TableName=K_Topic */
    public static final String Table_Name = "K_Topic";

    /** AD_Table_ID=607 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name IsPublicWrite */
    public static final String COLUMNNAME_IsPublicWrite = "IsPublicWrite";

	/** Set Public Write.
	  * Public can write entries
	  */
	public void setIsPublicWrite (boolean IsPublicWrite);

	/** Get Public Write.
	  * Public can write entries
	  */
	public boolean isPublicWrite();

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

    /** Column name K_Type_ID */
    public static final String COLUMNNAME_K_Type_ID = "K_Type_ID";

	/** Set Knowldge Type.
	  * Knowledge Type
	  */
	public void setK_Type_ID (int K_Type_ID);

	/** Get Knowldge Type.
	  * Knowledge Type
	  */
	public int getK_Type_ID();

	public I_K_Type getK_Type() throws Exception;

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
}

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

/** Generated Interface for AD_Replication_Run
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_AD_Replication_Run 
{

    /** TableName=AD_Replication_Run */
    public static final String Table_Name = "AD_Replication_Run";

    /** AD_Table_ID=603 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Replication_ID */
    public static final String COLUMNNAME_AD_Replication_ID = "AD_Replication_ID";

	/** Set Replication.
	  * Data Replication Target
	  */
	public void setAD_Replication_ID (int AD_Replication_ID);

	/** Get Replication.
	  * Data Replication Target
	  */
	public int getAD_Replication_ID();

	public I_AD_Replication getAD_Replication() throws Exception;

    /** Column name AD_Replication_Run_ID */
    public static final String COLUMNNAME_AD_Replication_Run_ID = "AD_Replication_Run_ID";

	/** Set Replication Run.
	  * Data Replication Run
	  */
	public void setAD_Replication_Run_ID (int AD_Replication_Run_ID);

	/** Get Replication Run.
	  * Data Replication Run
	  */
	public int getAD_Replication_Run_ID();

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

    /** Column name IsReplicated */
    public static final String COLUMNNAME_IsReplicated = "IsReplicated";

	/** Set Replicated.
	  * The data is successfully replicated
	  */
	public void setIsReplicated (boolean IsReplicated);

	/** Get Replicated.
	  * The data is successfully replicated
	  */
	public boolean isReplicated();

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

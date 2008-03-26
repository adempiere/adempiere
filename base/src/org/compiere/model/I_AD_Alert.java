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

/** Generated Interface for AD_Alert
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_AD_Alert 
{

    /** TableName=AD_Alert */
    public static final String Table_Name = "AD_Alert";

    /** AD_Table_ID=594 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_AlertProcessor_ID */
    public static final String COLUMNNAME_AD_AlertProcessor_ID = "AD_AlertProcessor_ID";

	/** Set Alert Processor.
	  * Alert Processor/Server Parameter
	  */
	public void setAD_AlertProcessor_ID (int AD_AlertProcessor_ID);

	/** Get Alert Processor.
	  * Alert Processor/Server Parameter
	  */
	public int getAD_AlertProcessor_ID();

	public I_AD_AlertProcessor getAD_AlertProcessor() throws Exception;

    /** Column name AD_Alert_ID */
    public static final String COLUMNNAME_AD_Alert_ID = "AD_Alert_ID";

	/** Set Alert.
	  * Adempiere Alert
	  */
	public void setAD_Alert_ID (int AD_Alert_ID);

	/** Get Alert.
	  * Adempiere Alert
	  */
	public int getAD_Alert_ID();

    /** Column name AlertMessage */
    public static final String COLUMNNAME_AlertMessage = "AlertMessage";

	/** Set Alert Message.
	  * Message of the Alert
	  */
	public void setAlertMessage (String AlertMessage);

	/** Get Alert Message.
	  * Message of the Alert
	  */
	public String getAlertMessage();

    /** Column name AlertSubject */
    public static final String COLUMNNAME_AlertSubject = "AlertSubject";

	/** Set Alert Subject.
	  * Subject of the Alert
	  */
	public void setAlertSubject (String AlertSubject);

	/** Get Alert Subject.
	  * Subject of the Alert
	  */
	public String getAlertSubject();

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

    /** Column name EnforceClientSecurity */
    public static final String COLUMNNAME_EnforceClientSecurity = "EnforceClientSecurity";

	/** Set Enforce Client Security.
	  * Send alerts to recipient only if the client security rules of the role allows
	  */
	public void setEnforceClientSecurity (boolean EnforceClientSecurity);

	/** Get Enforce Client Security.
	  * Send alerts to recipient only if the client security rules of the role allows
	  */
	public boolean isEnforceClientSecurity();

    /** Column name EnforceRoleSecurity */
    public static final String COLUMNNAME_EnforceRoleSecurity = "EnforceRoleSecurity";

	/** Set Enforce Role Security.
	  * Send alerts to recipient only if the data security rules of the role allows
	  */
	public void setEnforceRoleSecurity (boolean EnforceRoleSecurity);

	/** Get Enforce Role Security.
	  * Send alerts to recipient only if the data security rules of the role allows
	  */
	public boolean isEnforceRoleSecurity();

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

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

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

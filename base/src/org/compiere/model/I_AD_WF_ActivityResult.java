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

    /** Generated Interface for AD_WF_ActivityResult
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:30.562
     */
    public interface I_AD_WF_ActivityResult 
{

    /** TableName=AD_WF_ActivityResult */
    public static final String Table_Name = "AD_WF_ActivityResult";

    /** AD_Table_ID=650 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(7);

    /** Load Meta Data */

    /** Column name AD_WF_ActivityResult_ID */
    public static final String COLUMNNAME_AD_WF_ActivityResult_ID = "AD_WF_ActivityResult_ID";

	/** Set Workflow Activity Result.
	  * Result of the Workflow Process Activity
	  */
	public void setAD_WF_ActivityResult_ID (int AD_WF_ActivityResult_ID);

	/** Get Workflow Activity Result.
	  * Result of the Workflow Process Activity
	  */
	public int getAD_WF_ActivityResult_ID();

    /** Column name AD_WF_Activity_ID */
    public static final String COLUMNNAME_AD_WF_Activity_ID = "AD_WF_Activity_ID";

	/** Set Workflow Activity.
	  * Workflow Activity
	  */
	public void setAD_WF_Activity_ID (int AD_WF_Activity_ID);

	/** Get Workflow Activity.
	  * Workflow Activity
	  */
	public int getAD_WF_Activity_ID();

	public I_AD_WF_Activity getI_AD_WF_Activity() throws Exception;

    /** Column name AttributeName */
    public static final String COLUMNNAME_AttributeName = "AttributeName";

	/** Set Attribute Name.
	  * Name of the Attribute
	  */
	public void setAttributeName (String AttributeName);

	/** Get Attribute Name.
	  * Name of the Attribute
	  */
	public String getAttributeName();

    /** Column name AttributeValue */
    public static final String COLUMNNAME_AttributeValue = "AttributeValue";

	/** Set Attribute Value.
	  * Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue);

	/** Get Attribute Value.
	  * Value of the Attribute
	  */
	public String getAttributeValue();

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
}

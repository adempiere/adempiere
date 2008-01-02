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

/** Generated Interface for AD_Menu
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_AD_Menu 
{

    /** TableName=AD_Menu */
    public static final String Table_Name = "AD_Menu";

    /** AD_Table_ID=116 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

	public I_AD_Form getAD_Form() throws Exception;

    /** Column name AD_Menu_ID */
    public static final String COLUMNNAME_AD_Menu_ID = "AD_Menu_ID";

	/** Set Menu.
	  * Identifies a Menu
	  */
	public void setAD_Menu_ID (int AD_Menu_ID);

	/** Get Menu.
	  * Identifies a Menu
	  */
	public int getAD_Menu_ID();

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public I_AD_Process getAD_Process() throws Exception;

    /** Column name AD_Task_ID */
    public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";

	/** Set OS Task.
	  * Operation System Task
	  */
	public void setAD_Task_ID (int AD_Task_ID);

	/** Get OS Task.
	  * Operation System Task
	  */
	public int getAD_Task_ID();

	public I_AD_Task getAD_Task() throws Exception;

    /** Column name AD_Window_ID */
    public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

	/** Set Window.
	  * Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID);

	/** Get Window.
	  * Data entry or display window
	  */
	public int getAD_Window_ID();

	public I_AD_Window getAD_Window() throws Exception;

    /** Column name AD_Workbench_ID */
    public static final String COLUMNNAME_AD_Workbench_ID = "AD_Workbench_ID";

	/** Set Workbench.
	  * Collection of windows, reports
	  */
	public void setAD_Workbench_ID (int AD_Workbench_ID);

	/** Get Workbench.
	  * Collection of windows, reports
	  */
	public int getAD_Workbench_ID();

	public I_AD_Workbench getAD_Workbench() throws Exception;

    /** Column name AD_Workflow_ID */
    public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

	/** Set Workflow.
	  * Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID);

	/** Get Workflow.
	  * Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID();

	public I_AD_Workflow getAD_Workflow() throws Exception;

    /** Column name Action */
    public static final String COLUMNNAME_Action = "Action";

	/** Set Action.
	  * Indicates the Action to be performed
	  */
	public void setAction (String Action);

	/** Get Action.
	  * Indicates the Action to be performed
	  */
	public String getAction();

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

    /** Column name IsReadOnly */
    public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

	/** Set Read Only.
	  * Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly);

	/** Get Read Only.
	  * Field is read only
	  */
	public boolean isReadOnly();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

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

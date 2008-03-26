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

/** Generated Interface for AD_WF_NodeNext
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_AD_WF_NodeNext 
{

    /** TableName=AD_WF_NodeNext */
    public static final String Table_Name = "AD_WF_NodeNext";

    /** AD_Table_ID=131 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_WF_Next_ID */
    public static final String COLUMNNAME_AD_WF_Next_ID = "AD_WF_Next_ID";

	/** Set Next Node.
	  * Next Node in workflow
	  */
	public void setAD_WF_Next_ID (int AD_WF_Next_ID);

	/** Get Next Node.
	  * Next Node in workflow
	  */
	public int getAD_WF_Next_ID();

    /** Column name AD_WF_NodeNext_ID */
    public static final String COLUMNNAME_AD_WF_NodeNext_ID = "AD_WF_NodeNext_ID";

	/** Set Node Transition.
	  * Workflow Node Transition
	  */
	public void setAD_WF_NodeNext_ID (int AD_WF_NodeNext_ID);

	/** Get Node Transition.
	  * Workflow Node Transition
	  */
	public int getAD_WF_NodeNext_ID();

    /** Column name AD_WF_Node_ID */
    public static final String COLUMNNAME_AD_WF_Node_ID = "AD_WF_Node_ID";

	/** Set Node.
	  * Workflow Node (activity), step or process
	  */
	public void setAD_WF_Node_ID (int AD_WF_Node_ID);

	/** Get Node.
	  * Workflow Node (activity), step or process
	  */
	public int getAD_WF_Node_ID();

	public I_AD_WF_Node getAD_WF_Node() throws Exception;

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

    /** Column name IsStdUserWorkflow */
    public static final String COLUMNNAME_IsStdUserWorkflow = "IsStdUserWorkflow";

	/** Set Std User Workflow.
	  * Standard Manual User Approval Workflow
	  */
	public void setIsStdUserWorkflow (boolean IsStdUserWorkflow);

	/** Get Std User Workflow.
	  * Standard Manual User Approval Workflow
	  */
	public boolean isStdUserWorkflow();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name TransitionCode */
    public static final String COLUMNNAME_TransitionCode = "TransitionCode";

	/** Set Transition Code.
	  * Code resulting in TRUE of FALSE
	  */
	public void setTransitionCode (String TransitionCode);

	/** Get Transition Code.
	  * Code resulting in TRUE of FALSE
	  */
	public String getTransitionCode();
}

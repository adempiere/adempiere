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

/** Generated Interface for AD_Scheduler_Para
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_AD_Scheduler_Para 
{

    /** TableName=AD_Scheduler_Para */
    public static final String Table_Name = "AD_Scheduler_Para";

    /** AD_Table_ID=698 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Process_Para_ID */
    public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";

	/** Set Process Parameter	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID);

	/** Get Process Parameter	  */
	public int getAD_Process_Para_ID();

	public I_AD_Process_Para getAD_Process_Para() throws Exception;

    /** Column name AD_Scheduler_ID */
    public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";

	/** Set Scheduler.
	  * Schedule Processes
	  */
	public void setAD_Scheduler_ID (int AD_Scheduler_ID);

	/** Get Scheduler.
	  * Schedule Processes
	  */
	public int getAD_Scheduler_ID();

	public I_AD_Scheduler getAD_Scheduler() throws Exception;

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

    /** Column name ParameterDefault */
    public static final String COLUMNNAME_ParameterDefault = "ParameterDefault";

	/** Set Default Parameter.
	  * Default value of the parameter
	  */
	public void setParameterDefault (String ParameterDefault);

	/** Get Default Parameter.
	  * Default value of the parameter
	  */
	public String getParameterDefault();
}

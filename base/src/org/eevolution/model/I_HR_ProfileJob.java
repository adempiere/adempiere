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
package org.eevolution.model;

import java.math.BigDecimal;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_ProfileJob
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.0b
 */
public interface I_HR_ProfileJob 
{

    /** TableName=HR_ProfileJob */
    public static final String Table_Name = "HR_ProfileJob";

    /** AD_Table_ID=1000004 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set HR_Job_ID	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get HR_Job_ID	  */
	public int getHR_Job_ID();

	public I_HR_Job getHR_Job() throws Exception;

    /** Column name HR_ProfileJob_ID */
    public static final String COLUMNNAME_HR_ProfileJob_ID = "HR_ProfileJob_ID";

	/** Set HR_ProfileJob_ID	  */
	public void setHR_ProfileJob_ID (int HR_ProfileJob_ID);

	/** Get HR_ProfileJob_ID	  */
	public int getHR_ProfileJob_ID();

    /** Column name HR_ProfileLine_ID */
    public static final String COLUMNNAME_HR_ProfileLine_ID = "HR_ProfileLine_ID";
}

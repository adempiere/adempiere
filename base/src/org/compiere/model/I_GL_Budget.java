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

/** Generated Interface for GL_Budget
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_GL_Budget 
{

    /** TableName=GL_Budget */
    public static final String Table_Name = "GL_Budget";

    /** AD_Table_ID=271 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name BudgetStatus */
    public static final String COLUMNNAME_BudgetStatus = "BudgetStatus";

	/** Set Budget Status.
	  * Indicates the current status of this budget
	  */
	public void setBudgetStatus (String BudgetStatus);

	/** Get Budget Status.
	  * Indicates the current status of this budget
	  */
	public String getBudgetStatus();

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

    /** Column name GL_Budget_ID */
    public static final String COLUMNNAME_GL_Budget_ID = "GL_Budget_ID";

	/** Set Budget.
	  * General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID);

	/** Get Budget.
	  * General Ledger Budget
	  */
	public int getGL_Budget_ID();

    /** Column name IsPrimary */
    public static final String COLUMNNAME_IsPrimary = "IsPrimary";

	/** Set Primary.
	  * Indicates if this is the primary budget
	  */
	public void setIsPrimary (boolean IsPrimary);

	/** Get Primary.
	  * Indicates if this is the primary budget
	  */
	public boolean isPrimary();

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

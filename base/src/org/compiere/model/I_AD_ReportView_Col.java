/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ReportView_Col
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_AD_ReportView_Col 
{

    /** TableName=AD_ReportView_Col */
    public static final String Table_Name = "AD_ReportView_Col";

    /** AD_Table_ID=428 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_ReportView_Col_ID */
    public static final String COLUMNNAME_AD_ReportView_Col_ID = "AD_ReportView_Col_ID";

	/** Set Report view Column	  */
	public void setAD_ReportView_Col_ID (int AD_ReportView_Col_ID);

	/** Get Report view Column	  */
	public int getAD_ReportView_Col_ID();

    /** Column name AD_ReportView_ID */
    public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";

	/** Set Report View.
	  * View used to generate this report
	  */
	public void setAD_ReportView_ID (int AD_ReportView_ID);

	/** Get Report View.
	  * View used to generate this report
	  */
	public int getAD_ReportView_ID();

	public I_AD_ReportView getAD_ReportView() throws RuntimeException;

    /** Column name FunctionColumn */
    public static final String COLUMNNAME_FunctionColumn = "FunctionColumn";

	/** Set Function Column.
	  * Overwrite Column with Function 
	  */
	public void setFunctionColumn (String FunctionColumn);

	/** Get Function Column.
	  * Overwrite Column with Function 
	  */
	public String getFunctionColumn();

    /** Column name IsGroupFunction */
    public static final String COLUMNNAME_IsGroupFunction = "IsGroupFunction";

	/** Set SQL Group Function.
	  * This function will generate a Group By Clause
	  */
	public void setIsGroupFunction (boolean IsGroupFunction);

	/** Get SQL Group Function.
	  * This function will generate a Group By Clause
	  */
	public boolean isGroupFunction();
}

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

/** Generated Interface for PA_ReportLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_PA_ReportLine 
{

    /** TableName=PA_ReportLine */
    public static final String Table_Name = "PA_ReportLine";

    /** AD_Table_ID=448 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AmountType */
    public static final String COLUMNNAME_AmountType = "AmountType";

	/** Set Amount Type.
	  * Type of amount to report
	  */
	public void setAmountType (String AmountType);

	/** Get Amount Type.
	  * Type of amount to report
	  */
	public String getAmountType();

    /** Column name CalculationType */
    public static final String COLUMNNAME_CalculationType = "CalculationType";

	/** Set Calculation	  */
	public void setCalculationType (String CalculationType);

	/** Get Calculation	  */
	public String getCalculationType();

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

	public I_GL_Budget getGL_Budget() throws Exception;

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name LineType */
    public static final String COLUMNNAME_LineType = "LineType";

	/** Set Line Type	  */
	public void setLineType (String LineType);

	/** Get Line Type	  */
	public String getLineType();

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

    /** Column name Oper_1_ID */
    public static final String COLUMNNAME_Oper_1_ID = "Oper_1_ID";

	/** Set Operand 1.
	  * First operand for calculation
	  */
	public void setOper_1_ID (int Oper_1_ID);

	/** Get Operand 1.
	  * First operand for calculation
	  */
	public int getOper_1_ID();

    /** Column name Oper_2_ID */
    public static final String COLUMNNAME_Oper_2_ID = "Oper_2_ID";

	/** Set Operand 2.
	  * Second operand for calculation
	  */
	public void setOper_2_ID (int Oper_2_ID);

	/** Get Operand 2.
	  * Second operand for calculation
	  */
	public int getOper_2_ID();

    /** Column name PA_ReportLineSet_ID */
    public static final String COLUMNNAME_PA_ReportLineSet_ID = "PA_ReportLineSet_ID";

	/** Set Report Line Set	  */
	public void setPA_ReportLineSet_ID (int PA_ReportLineSet_ID);

	/** Get Report Line Set	  */
	public int getPA_ReportLineSet_ID();

	public I_PA_ReportLineSet getPA_ReportLineSet() throws Exception;

    /** Column name PA_ReportLine_ID */
    public static final String COLUMNNAME_PA_ReportLine_ID = "PA_ReportLine_ID";

	/** Set Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID);

	/** Get Report Line	  */
	public int getPA_ReportLine_ID();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set PostingType.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get PostingType.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

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
}

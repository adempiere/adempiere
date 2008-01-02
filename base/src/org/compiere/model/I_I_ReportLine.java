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

/** Generated Interface for I_ReportLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_I_ReportLine 
{

    /** TableName=I_ReportLine */
    public static final String Table_Name = "I_ReportLine";

    /** AD_Table_ID=535 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public I_C_ElementValue getC_ElementValue() throws Exception;

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

    /** Column name ElementValue */
    public static final String COLUMNNAME_ElementValue = "ElementValue";

	/** Set Element Key.
	  * Key of the element
	  */
	public void setElementValue (String ElementValue);

	/** Get Element Key.
	  * Key of the element
	  */
	public String getElementValue();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_ReportLine_ID */
    public static final String COLUMNNAME_I_ReportLine_ID = "I_ReportLine_ID";

	/** Set Import Report Line Set.
	  * Import Report Line Set values
	  */
	public void setI_ReportLine_ID (int I_ReportLine_ID);

	/** Get Import Report Line Set.
	  * Import Report Line Set values
	  */
	public int getI_ReportLine_ID();

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

	public I_PA_ReportLine getPA_ReportLine() throws Exception;

    /** Column name PA_ReportSource_ID */
    public static final String COLUMNNAME_PA_ReportSource_ID = "PA_ReportSource_ID";

	/** Set Report Source.
	  * Restriction of what will be shown in Report Line
	  */
	public void setPA_ReportSource_ID (int PA_ReportSource_ID);

	/** Get Report Source.
	  * Restriction of what will be shown in Report Line
	  */
	public int getPA_ReportSource_ID();

	public I_PA_ReportSource getPA_ReportSource() throws Exception;

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ReportLineSetName */
    public static final String COLUMNNAME_ReportLineSetName = "ReportLineSetName";

	/** Set Report Line Set Name.
	  * Name of the Report Line Set
	  */
	public void setReportLineSetName (String ReportLineSetName);

	/** Get Report Line Set Name.
	  * Name of the Report Line Set
	  */
	public String getReportLineSetName();

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

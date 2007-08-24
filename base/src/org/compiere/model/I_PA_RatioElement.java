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

    /** Generated Interface for PA_RatioElement
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:53.578
     */
    public interface I_PA_RatioElement 
{

    /** TableName=PA_RatioElement */
    public static final String Table_Name = "PA_RatioElement";

    /** AD_Table_ID=836 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = new BigDecimal(2);

    /** Load Meta Data */

    /** Column name Account_ID */
    public static final String COLUMNNAME_Account_ID = "Account_ID";

	/** Set Account.
	  * Account used
	  */
	public void setAccount_ID (int Account_ID);

	/** Get Account.
	  * Account used
	  */
	public int getAccount_ID();

    /** Column name ConstantValue */
    public static final String COLUMNNAME_ConstantValue = "ConstantValue";

	/** Set Constant Value.
	  * Constant value
	  */
	public void setConstantValue (BigDecimal ConstantValue);

	/** Get Constant Value.
	  * Constant value
	  */
	public BigDecimal getConstantValue();

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

    /** Column name PA_MeasureCalc_ID */
    public static final String COLUMNNAME_PA_MeasureCalc_ID = "PA_MeasureCalc_ID";

	/** Set Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID);

	/** Get Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public int getPA_MeasureCalc_ID();

	public I_PA_MeasureCalc getI_PA_MeasureCalc() throws Exception;

    /** Column name PA_RatioElement_ID */
    public static final String COLUMNNAME_PA_RatioElement_ID = "PA_RatioElement_ID";

	/** Set Ratio Element.
	  * Performance Ratio Element
	  */
	public void setPA_RatioElement_ID (int PA_RatioElement_ID);

	/** Get Ratio Element.
	  * Performance Ratio Element
	  */
	public int getPA_RatioElement_ID();

    /** Column name PA_RatioUsed_ID */
    public static final String COLUMNNAME_PA_RatioUsed_ID = "PA_RatioUsed_ID";

	/** Set Ratio Used.
	  * Performace Ratio Used
	  */
	public void setPA_RatioUsed_ID (int PA_RatioUsed_ID);

	/** Get Ratio Used.
	  * Performace Ratio Used
	  */
	public int getPA_RatioUsed_ID();

    /** Column name PA_Ratio_ID */
    public static final String COLUMNNAME_PA_Ratio_ID = "PA_Ratio_ID";

	/** Set Ratio.
	  * Performace Ratio
	  */
	public void setPA_Ratio_ID (int PA_Ratio_ID);

	/** Get Ratio.
	  * Performace Ratio
	  */
	public int getPA_Ratio_ID();

	public I_PA_Ratio getI_PA_Ratio() throws Exception;

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

    /** Column name RatioElementType */
    public static final String COLUMNNAME_RatioElementType = "RatioElementType";

	/** Set Element Type.
	  * Ratio Element Type
	  */
	public void setRatioElementType (String RatioElementType);

	/** Get Element Type.
	  * Ratio Element Type
	  */
	public String getRatioElementType();

    /** Column name RatioOperand */
    public static final String COLUMNNAME_RatioOperand = "RatioOperand";

	/** Set Operand.
	  * Ratio Operand
	  */
	public void setRatioOperand (String RatioOperand);

	/** Get Operand.
	  * Ratio Operand
	  */
	public String getRatioOperand();

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

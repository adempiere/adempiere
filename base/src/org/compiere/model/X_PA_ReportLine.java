/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PA_ReportLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PA_ReportLine extends PO implements I_PA_ReportLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PA_ReportLine (Properties ctx, int PA_ReportLine_ID, String trxName)
    {
      super (ctx, PA_ReportLine_ID, trxName);
      /** if (PA_ReportLine_ID == 0)
        {
			setIsPrinted (true);
// Y
			setLineType (null);
			setName (null);
			setPA_ReportLineSet_ID (0);
			setPA_ReportLine_ID (0);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM PA_ReportLine WHERE PA_ReportLineSet_ID=@PA_ReportLineSet_ID@
        } */
    }

    /** Load Constructor */
    public X_PA_ReportLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PA_ReportLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** CalculationType AD_Reference_ID=236 */
	public static final int CALCULATIONTYPE_AD_Reference_ID=236;
	/** Add (Op1+Op2) = A */
	public static final String CALCULATIONTYPE_AddOp1PlusOp2 = "A";
	/** Subtract (Op1-Op2) = S */
	public static final String CALCULATIONTYPE_SubtractOp1_Op2 = "S";
	/** Percentage (Op1 of Op2) = P */
	public static final String CALCULATIONTYPE_PercentageOp1OfOp2 = "P";
	/** Add Range (Op1 to Op2) = R */
	public static final String CALCULATIONTYPE_AddRangeOp1ToOp2 = "R";
	/** Set Calculation.
		@param CalculationType Calculation	  */
	public void setCalculationType (String CalculationType)
	{

		set_Value (COLUMNNAME_CalculationType, CalculationType);
	}

	/** Get Calculation.
		@return Calculation	  */
	public String getCalculationType () 
	{
		return (String)get_Value(COLUMNNAME_CalculationType);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Fixed Percentage.
		@param FixedPercentage Fixed Percentage	  */
	public void setFixedPercentage (BigDecimal FixedPercentage)
	{
		set_Value (COLUMNNAME_FixedPercentage, FixedPercentage);
	}

	/** Get Fixed Percentage.
		@return Fixed Percentage	  */
	public BigDecimal getFixedPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FixedPercentage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_GL_Budget getGL_Budget() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Budget)MTable.get(getCtx(), org.compiere.model.I_GL_Budget.Table_Name)
			.getPO(getGL_Budget_ID(), get_TrxName());	}

	/** Set Budget.
		@param GL_Budget_ID 
		General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID)
	{
		if (GL_Budget_ID < 1) 
			set_Value (COLUMNNAME_GL_Budget_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Budget_ID, Integer.valueOf(GL_Budget_ID));
	}

	/** Get Budget.
		@return General Ledger Budget
	  */
	public int getGL_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Show Opposite Sign.
		@param IsShowOppositeSign 
		Display values with the opposite sign
	  */
	public void setIsShowOppositeSign (boolean IsShowOppositeSign)
	{
		set_Value (COLUMNNAME_IsShowOppositeSign, Boolean.valueOf(IsShowOppositeSign));
	}

	/** Get Show Opposite Sign.
		@return Display values with the opposite sign
	  */
	public boolean isShowOppositeSign () 
	{
		Object oo = get_Value(COLUMNNAME_IsShowOppositeSign);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** LineType AD_Reference_ID=241 */
	public static final int LINETYPE_AD_Reference_ID=241;
	/** Segment Value = S */
	public static final String LINETYPE_SegmentValue = "S";
	/** Calculation = C */
	public static final String LINETYPE_Calculation = "C";
	/** Tabbed Text = T */
	public static final String LINETYPE_TabbedText = "T";
	/** Customized Line = L */
	public static final String LINETYPE_CustomizedLine = "L";
	/** Blank line = B */
	public static final String LINETYPE_BlankLine = "B";
	/** Set Line Type.
		@param LineType Line Type	  */
	public void setLineType (String LineType)
	{

		set_Value (COLUMNNAME_LineType, LineType);
	}

	/** Get Line Type.
		@return Line Type	  */
	public String getLineType () 
	{
		return (String)get_Value(COLUMNNAME_LineType);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	public org.compiere.model.I_PA_ReportLine getOper_1() throws RuntimeException
    {
		return (org.compiere.model.I_PA_ReportLine)MTable.get(getCtx(), org.compiere.model.I_PA_ReportLine.Table_Name)
			.getPO(getOper_1_ID(), get_TrxName());	}

	/** Set Operand 1.
		@param Oper_1_ID 
		First operand for calculation
	  */
	public void setOper_1_ID (int Oper_1_ID)
	{
		if (Oper_1_ID < 1) 
			set_Value (COLUMNNAME_Oper_1_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_1_ID, Integer.valueOf(Oper_1_ID));
	}

	/** Get Operand 1.
		@return First operand for calculation
	  */
	public int getOper_1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_PA_ReportLine getOper_2() throws RuntimeException
    {
		return (org.compiere.model.I_PA_ReportLine)MTable.get(getCtx(), org.compiere.model.I_PA_ReportLine.Table_Name)
			.getPO(getOper_2_ID(), get_TrxName());	}

	/** Set Operand 2.
		@param Oper_2_ID 
		Second operand for calculation
	  */
	public void setOper_2_ID (int Oper_2_ID)
	{
		if (Oper_2_ID < 1) 
			set_Value (COLUMNNAME_Oper_2_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_2_ID, Integer.valueOf(Oper_2_ID));
	}

	/** Get Operand 2.
		@return Second operand for calculation
	  */
	public int getOper_2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** OverlineStrokeType AD_Reference_ID=53793 */
	public static final int OVERLINESTROKETYPE_AD_Reference_ID=53793;
	/** Solid = s */
	public static final String OVERLINESTROKETYPE_Solid = "s";
	/** Double Solid = d_s */
	public static final String OVERLINESTROKETYPE_DoubleSolid = "d_s";
	/** Dotted = d */
	public static final String OVERLINESTROKETYPE_Dotted = "d";
	/** Double Dotted = d_d */
	public static final String OVERLINESTROKETYPE_DoubleDotted = "d_d";
	/** Dashed = D */
	public static final String OVERLINESTROKETYPE_Dashed = "D";
	/** Double Dashed = d_D */
	public static final String OVERLINESTROKETYPE_DoubleDashed = "d_D";
	/** Set Overline Stroke Type.
		@param OverlineStrokeType Overline Stroke Type	  */
	public void setOverlineStrokeType (String OverlineStrokeType)
	{

		set_Value (COLUMNNAME_OverlineStrokeType, OverlineStrokeType);
	}

	/** Get Overline Stroke Type.
		@return Overline Stroke Type	  */
	public String getOverlineStrokeType () 
	{
		return (String)get_Value(COLUMNNAME_OverlineStrokeType);
	}

	/** PAAmountType AD_Reference_ID=53328 */
	public static final int PAAMOUNTTYPE_AD_Reference_ID=53328;
	/** Balance (expected sign) = B */
	public static final String PAAMOUNTTYPE_BalanceExpectedSign = "B";
	/** Credit Only = C */
	public static final String PAAMOUNTTYPE_CreditOnly = "C";
	/** Debit Only = D */
	public static final String PAAMOUNTTYPE_DebitOnly = "D";
	/** Quantity (expected sign) = Q */
	public static final String PAAMOUNTTYPE_QuantityExpectedSign = "Q";
	/** Balance (accounted sign) = S */
	public static final String PAAMOUNTTYPE_BalanceAccountedSign = "S";
	/** Quantity (accounted sign) = R */
	public static final String PAAMOUNTTYPE_QuantityAccountedSign = "R";
	/** Set Amount Type.
		@param PAAmountType 
		PA Amount Type for reporting
	  */
	public void setPAAmountType (String PAAmountType)
	{

		set_Value (COLUMNNAME_PAAmountType, PAAmountType);
	}

	/** Get Amount Type.
		@return PA Amount Type for reporting
	  */
	public String getPAAmountType () 
	{
		return (String)get_Value(COLUMNNAME_PAAmountType);
	}

	/** PAPeriodType AD_Reference_ID=53327 */
	public static final int PAPERIODTYPE_AD_Reference_ID=53327;
	/** Total = T */
	public static final String PAPERIODTYPE_Total = "T";
	/** Year = Y */
	public static final String PAPERIODTYPE_Year = "Y";
	/** Period = P */
	public static final String PAPERIODTYPE_Period = "P";
	/** Natural = N */
	public static final String PAPERIODTYPE_Natural = "N";
	/** Set Period Type.
		@param PAPeriodType 
		PA Period Type
	  */
	public void setPAPeriodType (String PAPeriodType)
	{

		set_Value (COLUMNNAME_PAPeriodType, PAPeriodType);
	}

	/** Get Period Type.
		@return PA Period Type
	  */
	public String getPAPeriodType () 
	{
		return (String)get_Value(COLUMNNAME_PAPeriodType);
	}

	public org.compiere.model.I_PA_ReportLineSet getPA_ReportLineSet() throws RuntimeException
    {
		return (org.compiere.model.I_PA_ReportLineSet)MTable.get(getCtx(), org.compiere.model.I_PA_ReportLineSet.Table_Name)
			.getPO(getPA_ReportLineSet_ID(), get_TrxName());	}

	/** Set Report Line Set.
		@param PA_ReportLineSet_ID Report Line Set	  */
	public void setPA_ReportLineSet_ID (int PA_ReportLineSet_ID)
	{
		if (PA_ReportLineSet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLineSet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLineSet_ID, Integer.valueOf(PA_ReportLineSet_ID));
	}

	/** Get Report Line Set.
		@return Report Line Set	  */
	public int getPA_ReportLineSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLineSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Line.
		@param PA_ReportLine_ID Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID)
	{
		if (PA_ReportLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, Integer.valueOf(PA_ReportLine_ID));
	}

	/** Get Report Line.
		@return Report Line	  */
	public int getPA_ReportLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PostingType AD_Reference_ID=125 */
	public static final int POSTINGTYPE_AD_Reference_ID=125;
	/** Actual = A */
	public static final String POSTINGTYPE_Actual = "A";
	/** Budget = B */
	public static final String POSTINGTYPE_Budget = "B";
	/** Commitment = E */
	public static final String POSTINGTYPE_Commitment = "E";
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
	}

	/** ReportLineStyle AD_Reference_ID=53564 */
	public static final int REPORTLINESTYLE_AD_Reference_ID=53564;
	/** Blank Line = S */
	public static final String REPORTLINESTYLE_BlankLine = "S";
	/** Underlined Descriptions = D */
	public static final String REPORTLINESTYLE_UnderlinedDescriptions = "D";
	/** Double Line for Total = Z */
	public static final String REPORTLINESTYLE_DoubleLineForTotal = "Z";
	/** Line = L */
	public static final String REPORTLINESTYLE_Line = "L";
	/** Total Line = X */
	public static final String REPORTLINESTYLE_TotalLine = "X";
	/** Centered Title = T */
	public static final String REPORTLINESTYLE_CenteredTitle = "T";
	/** Set Report Line Style.
		@param ReportLineStyle Report Line Style	  */
	public void setReportLineStyle (String ReportLineStyle)
	{

		set_Value (COLUMNNAME_ReportLineStyle, ReportLineStyle);
	}

	/** Get Report Line Style.
		@return Report Line Style	  */
	public String getReportLineStyle () 
	{
		return (String)get_Value(COLUMNNAME_ReportLineStyle);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tab Level.
		@param TabLevel 
		Hierarchical Tab Level (0 = top)
	  */
	public void setTabLevel (int TabLevel)
	{
		set_Value (COLUMNNAME_TabLevel, Integer.valueOf(TabLevel));
	}

	/** Get Tab Level.
		@return Hierarchical Tab Level (0 = top)
	  */
	public int getTabLevel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TabLevel);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** UnderlineStrokeType AD_Reference_ID=53793 */
	public static final int UNDERLINESTROKETYPE_AD_Reference_ID=53793;
	/** Solid = s */
	public static final String UNDERLINESTROKETYPE_Solid = "s";
	/** Double Solid = d_s */
	public static final String UNDERLINESTROKETYPE_DoubleSolid = "d_s";
	/** Dotted = d */
	public static final String UNDERLINESTROKETYPE_Dotted = "d";
	/** Double Dotted = d_d */
	public static final String UNDERLINESTROKETYPE_DoubleDotted = "d_d";
	/** Dashed = D */
	public static final String UNDERLINESTROKETYPE_Dashed = "D";
	/** Double Dashed = d_D */
	public static final String UNDERLINESTROKETYPE_DoubleDashed = "d_D";
	/** Set Underline Stroke Type.
		@param UnderlineStrokeType Underline Stroke Type	  */
	public void setUnderlineStrokeType (String UnderlineStrokeType)
	{

		set_Value (COLUMNNAME_UnderlineStrokeType, UnderlineStrokeType);
	}

	/** Get Underline Stroke Type.
		@return Underline Stroke Type	  */
	public String getUnderlineStrokeType () 
	{
		return (String)get_Value(COLUMNNAME_UnderlineStrokeType);
	}
}
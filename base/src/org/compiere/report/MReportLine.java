/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.report;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_PA_ReportSource;
import org.adempiere.core.domains.models.X_PA_ReportLine;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.Query;
import org.compiere.util.Util;

/**
 *	Report Line Model
 *
 *  @author Jorg Janke
 *  @version $Id: MReportLine.java,v 1.3 2006/08/03 22:16:52 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1302">
 * 		@see BR [ 1302 ] Financial Report not have a Posting Type as parameter</a>
 */
public class MReportLine extends X_PA_ReportLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3957315092529097396L;

	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param PA_ReportLine_ID id
	 * 	@param trxName transaction
	 */
	public MReportLine (Properties ctx, int PA_ReportLine_ID, String trxName) {
		super (ctx, PA_ReportLine_ID, trxName);
		if (PA_ReportLine_ID == 0) {
			setSeqNo (0);
		//	setIsSummary (false);		//	not active in DD 
			setIsPrinted (false);
		} else {
			loadSources();
		}
	}	//	MReportLine

	/**
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param rs ResultSet to load from
	 * 	@param trxName transaction
	 */
	public MReportLine (Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		loadSources();
	}	//	MReportLine

	/**	Containt Sources				*/
	private MReportSource[]		sources = null;
	/** Cache result					*/
	private String				whereClause = null;

	private String m_selectClauseCombination = null;
	List<String> combinationGroupBy = new ArrayList<String>();
	private Stroke underline_Stroke;
	private BasicStroke overline_Stroke;
	/**
	 * 	Load contained Sources
	 */
	private void loadSources() {
		List<MReportSource> list = new Query(getCtx(), I_PA_ReportSource.Table_Name, "PA_ReportLine_ID = ?", get_TrxName())
				.setParameters(getPA_ReportLine_ID())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.list();
		//
		sources = new MReportSource[list.size()];
		list.toArray(sources);
		log.finest("ID=" + getPA_ReportLine_ID()
			+ " - Size=" + list.size());
	}	//	loadSources

	/**
	 * 	Get Sources
	 * 	@return sources
	 */
	public MReportSource[] getSources()
	{
		return sources;
	}	//	getSources

	/**
	 * 	List Info
	 */
	public void list()
	{
		System.out.println("- " + toString());
		if (sources == null)
			return;
		for (int i = 0; i < sources.length; i++)
			System.out.println("  - " + sources[i].toString());
	}	//	list


	/**
	 * 	Get Source Column Name
	 * 	@return Source ColumnName
	 */
	public String getSourceColumnName()
	{
		String ColumnName = null;
		for (int i = 0; i < sources.length; i++)
		{
			String col = null;
			if(sources[i].getElementType().equals("CO"))
			{
				col = sources[i].getCombinationKey();
			}
			else
				col = MAcctSchemaElement.getColumnName (sources[i].getElementType());
			if (ColumnName == null || ColumnName.length() == 0)
				ColumnName = col;
			else if (!ColumnName.equals(col))
			{
				log.config("More than one: " + ColumnName + " - " + col);
				return null;
			}
		}
		return ColumnName;
	}	//	getColumnName

	/**
	 *  Get Value Query for Segment Type
	 * 	@return Query for first source element or null
	 */
	public String getSourceValueQuery()
	{
		if (sources != null && sources.length > 0)
			return MAcctSchemaElement.getValueQuery(sources[0].getElementType());
		return null;
	}	//


	/**
	 * 	Get SQL Select Clause.
	 * 	@param withSum with SUM() function
	 * 	@return select clause - AmtAcctCR+AmtAcctDR/etc or "null" if not defined
	 */
	public String getSelectClause (boolean withSum)
	{
		String at = getPAAmountType().substring(0,1);	//	first letter
		StringBuffer sb = new StringBuffer();
		if (withSum)
			sb.append("SUM(");
		if (PAAMOUNTTYPE_BalanceExpectedSign.equals(at))
		//	sb.append("AmtAcctDr-AmtAcctCr");
			sb.append("acctBalance(Account_ID,AmtAcctDr,AmtAcctCr)");
		else if (PAAMOUNTTYPE_BalanceAccountedSign.equals(at))
			sb.append("AmtAcctDr-AmtAcctCr");
		else if (PAAMOUNTTYPE_CreditOnly.equals(at))
			sb.append("AmtAcctCr");
		else if (PAAMOUNTTYPE_DebitOnly.equals(at))
			sb.append("AmtAcctDr");
		else if (PAAMOUNTTYPE_QuantityAccountedSign.equals(at))
			sb.append("Qty");
		else if (PAAMOUNTTYPE_QuantityExpectedSign.equals(at))
				sb.append("acctBalance(Account_ID,Qty,0)");		
		else
		{
			log.log(Level.SEVERE, "AmountType=" + getPAAmountType () + ", at=" + at);
			return "NULL";
		}
		if (withSum)
			sb.append(")");
		return sb.toString();
	}	//	getSelectClause

	/**
	 * 	Is it Period ?
	 * 	@return true if Period Amount Type
	 */
	public boolean isPeriod()
	{
		String pt = getPAPeriodType();
		if (pt == null)
			return false;
		return PAPERIODTYPE_Period.equals(pt);
	}	//	isPeriod

	/**
	 * 	Is it Year ?
	 * 	@return true if Year Amount Type
	 */
	public boolean isYear()
	{
		String pt = getPAPeriodType();
		if (pt == null)
			return false;
		return PAPERIODTYPE_Year.equals(pt);
	}	//	isYear

	/**
	 * 	Is it Total ?
	 * 	@return true if Year Amount Type
	 */
	public boolean isTotal()
	{
		String pt = getPAPeriodType();
		if (pt == null)
			return false;
		return PAPERIODTYPE_Total.equals(pt);
	}	//	isTotal
	
	/**
	 * Is it natural balance ?
	 * Natural balance means year balance for profit and loss a/c, total balance for balance sheet account
	 * @return true if Natural Balance Amount Type
	 */
	public boolean isNatural()
	{
		String pt = getPAPeriodType();
		if (pt == null)
			return false;
		
		return PAPERIODTYPE_Natural.equals(pt);
	}

	/**
	 * 	Get SQL where clause (sources, posting type)
	 * 	@param PA_Hierarchy_ID hierarchy
	 * 	@param optionalPostingType
	 * 	@return where clause
	 */
	public String getWhereClause(int PA_Hierarchy_ID, String optionalPostingType) {
		if (sources == null)
			return "";
		if (whereClause == null) {
			//	Only one
			if (sources.length == 0) {
				whereClause = "";
			} else if (sources.length == 1) {
				whereClause = sources[0].getWhereClause(PA_Hierarchy_ID);
			} else {
				//	Multiple
				StringBuffer sb = new StringBuffer ("(");
				for (int i = 0; i < sources.length; i++) {
					if (i > 0) {
						sb.append (" OR ");
					}
					sb.append (sources[i].getWhereClause(PA_Hierarchy_ID));
				}
				sb.append (")");
				whereClause = sb.toString ();
			}
			if(!Util.isEmpty(optionalPostingType)) {
				if (whereClause.length() > 0) {
					whereClause += " AND ";
				}
				whereClause += optionalPostingType;
			} else {
				//	Posting Type
				if (!Util.isEmpty(getPostingType())) {
					if (whereClause.length() > 0)
						whereClause += " AND ";
					whereClause += "PostingType='" + getPostingType() + "'";
					// globalqss - CarlosRuiz
					if (getPostingType().equals(MReportLine.POSTINGTYPE_Budget)) {
						if (getGL_Budget_ID() > 0) {
							whereClause += " AND GL_Budget_ID=" + getGL_Budget_ID();
						}
					}
					// end globalqss
				}
			}
			log.fine(whereClause);
		}
		return whereClause;
	}	//	getWhereClause

	/**
	 * 	Has Posting Type
	 *	@return true if posting
	 */
	public boolean isPostingType() {
		return !Util.isEmpty(getPostingType());
	}	//	isPostingType

	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MReportLine[")
			.append(get_ID()).append(" - ").append(getName()).append(" - ").append(getDescription())
			.append(", SeqNo=").append(getSeqNo()).append(", AmountType=").append(getPAAmountType())
			.append(", PeriodType=").append(getPAPeriodType())
			.append(" - LineType=").append(getLineType());
		if (isLineTypeCalculation())
			sb.append(" - Calculation=").append(getCalculationType())
				.append(" - ").append(getOper_1_ID()).append(" - ").append(getOper_2_ID());
		else	//	SegmentValue
			sb.append(" - SegmentValue - PostingType=").append(getPostingType())
				.append(", AmountType=").append(getPAAmountType())
				.append(", PeriodType=").append(getPAPeriodType());
		sb.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Line Type Calculation
	 *	@return true if calculation
	 */
	public boolean isLineTypeCalculation()
	{
		return LINETYPE_Calculation.equals(getLineType());
	}
	/**
	 * 	Line Type Segment Value
	 *	@return true if segment value
	 */
	public boolean isLineTypeSegmentValue()
	{
		return LINETYPE_SegmentValue.equals(getLineType());
	}
	
	/**
	 * 	Calculation Type Range
	 *	@return true if range
	 */
	public boolean isCalculationTypeRange()
	{
		return CALCULATIONTYPE_AddRangeOp1ToOp2.equals(getCalculationType());
	}
	/**
	 * 	Calculation Type Add
	 *	@return true if add
	 */
	public boolean isCalculationTypeAdd()
	{
		return CALCULATIONTYPE_AddOp1PlusOp2.equals(getCalculationType());
	}
	/**
	 * 	Calculation Type Subtract
	 *	@return true if subtract
	 */
	public boolean isCalculationTypeSubtract()
	{
		return CALCULATIONTYPE_SubtractOp1_Op2.equals(getCalculationType());
	}
	/**
	 * 	Calculation Type Percent
	 *	@return true if percent
	 */
	public boolean isCalculationTypePercent()
	{
		return CALCULATIONTYPE_PercentageOp1OfOp2.equals(getCalculationType());
	}

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (LINETYPE_SegmentValue.equals(getLineType()))
		{
			if (getCalculationType() != null)
				setCalculationType(null);
			if (getOper_1_ID() != 0)
				setOper_1_ID(0);
			if (getOper_2_ID() != 0)
				setOper_2_ID(0);
		}
		return true;
	}	//	beforeSave
	

	/**************************************************************************
	 * 	Copy
	 * 	@param ctx context
	 * 	@param AD_Client_ID parent
	 * 	@param AD_Org_ID parent
	 * 	@param PA_ReportLineSet_ID parent
	 * 	@param source copy source
	 * 	@param trxName transaction
	 * 	@return Report Line
	 */
	public static MReportLine copy (Properties ctx, int AD_Client_ID, int AD_Org_ID, 
		int PA_ReportLineSet_ID, MReportLine source, String trxName)
	{
		MReportLine retValue = new MReportLine (ctx, 0, trxName);
		MReportLine.copyValues(source, retValue, AD_Client_ID, AD_Org_ID);
		//
		retValue.setPA_ReportLineSet_ID(PA_ReportLineSet_ID);
		retValue.setOper_1_ID(0);
		retValue.setOper_2_ID(0);
		return retValue;
	}	//	copy

	/**
	 * @return
	 */
	public String getSelectClauseCombination() {
		if (sources == null)
			return "";
		if (m_selectClauseCombination == null) {
			if (sources.length == 0)
				m_selectClauseCombination = "";
			else {
				MReportSource source = sources[0];
				StringBuffer select = new StringBuffer("");
				if (source.getOrg_ID() != 0 || source.isIncludeNullsOrg()) {
					select.append(" AND fb.").append("AD_Org_ID").append("=x.")
							.append("AD_Org_ID");
					combinationGroupBy.add("AD_Org_ID");
				}
				if (source.getAD_OrgTrx_ID() != 0
						|| source.isIncludeNullsOrgTrx()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_AD_OrgTrx_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_AD_OrgTrx_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_AD_OrgTrx_ID);
				}
				if (source.getC_ElementValue_ID() != 0
						|| source.isIncludeNullsElementValue()) {
					select.append(" AND fb.").append("Account_ID")
							.append("=x.").append("Account_ID");
					combinationGroupBy.add("Account_ID");
				}
				if (source.getC_BPartner_ID() != 0
						|| source.isIncludeNullsBPartner()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_C_BPartner_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_C_BPartner_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_C_BPartner_ID);
				}
				if (source.getM_Product_ID() != 0
						|| source.isIncludeNullsProduct()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_M_Product_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_M_Product_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_M_Product_ID);
				}
				if (source.getC_Location_ID() != 0
						|| source.isIncludeNullsLocation()) {
					select.append(" AND fb.").append("C_LocFrom_ID")
							.append("=x.").append("C_LocFrom_ID");
					combinationGroupBy.add("C_LocFrom_ID");
				}
				if (source.getC_Project_ID() != 0
						|| source.isIncludeNullsProject()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_C_Project_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_C_Project_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_C_Project_ID);
				}
				if (source.getC_SalesRegion_ID() != 0
						|| source.isIncludeNullsSalesRegion()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_C_SalesRegion_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_C_SalesRegion_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_C_SalesRegion_ID);
				}
				if (source.getC_Activity_ID() != 0
						|| source.isIncludeNullsActivity()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_C_Activity_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_C_Activity_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_C_Activity_ID);
				}
				if (source.getC_Campaign_ID() != 0
						|| source.isIncludeNullsCampaign()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_C_Campaign_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_C_Campaign_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_C_Campaign_ID);
				}
				if (source.getUserElement1_ID() != 0
						|| source.isIncludeNullsUserElement1()) {
					select.append(" AND fb.")
							.append(MReportSource.COLUMNNAME_UserElement1_ID)
							.append("=x.")
							.append(MReportSource.COLUMNNAME_UserElement1_ID);
					combinationGroupBy
							.add(MReportSource.COLUMNNAME_UserElement1_ID);
				}
				if (source.get_ValueAsInt("User1_ID") != 0
						|| source.get_ValueAsBoolean("IsIncludeNullsUserList1")) {
					select.append(" AND fb.").append("User1_ID").append("=x.")
							.append("User1_ID");
					combinationGroupBy.add("User1_ID");
				}
				if (source.get_ValueAsInt("User2_ID") != 0
						|| source.get_ValueAsBoolean("IsIncludeNullsUserList2")) {
					select.append(" AND fb.").append("User2_ID").append("=x.")
							.append("User2_ID");
					combinationGroupBy.add("User2_ID");
				}
				if (source.get_ValueAsInt("User3_ID") != 0
						|| source.get_ValueAsBoolean("IsIncludeNullsUserList3")) {
					select.append(" AND fb.").append("User3_ID").append("=x.")
							.append("User3_ID");
					combinationGroupBy.add("User3_ID");
				}
				if (source.get_ValueAsInt("User4_ID") != 0
						|| source.get_ValueAsBoolean("IsIncludeNullsUserList4")) {
					select.append(" AND fb.").append("User4_ID").append("=x.")
							.append("User4_ID");
					combinationGroupBy.add("User4_ID");
				}
				m_selectClauseCombination = select.toString();
			}

			log.fine(m_selectClauseCombination);
		}
		return m_selectClauseCombination;
	} // getSelectClauseCombination

	/**
	 * @return
	 */
	public List<String> getCombinationGroupByColumns() {
		return combinationGroupBy;
	} // getCombinationGroupByColumns
	
	/**
	 * 	Get Pattern Dotted . . . .
	 *	@param width width of line
	 *	@return pattern
	 */
	private float[] getPatternDotted (float width)
	{
		return new float[] {2*width, 2*width};
	}	//	getPatternDotted

	/**
	 * 	Get Pattern Dashed - - - -
	 *	@param width width of line
	 *	@return pattern
	 */
	private float[] getPatternDashed (float width)
	{
		return new float[] {10*width, 4*width};
	}	//	getPatternDashed
	
	/**
	 * Get underline style
	 * 0 - none
	 * 1 - single
	 * 2 - double
	 * @return
	 */
	public int getUnderline()
	{
		if ( UNDERLINESTROKETYPE_Dotted.equals(getUnderlineStrokeType()) ||
				UNDERLINESTROKETYPE_Solid.equals(getUnderlineStrokeType()) ||
				UNDERLINESTROKETYPE_Dashed.equals(getUnderlineStrokeType()))
			return 1;
		else if ( UNDERLINESTROKETYPE_DoubleDotted.equals(getUnderlineStrokeType()) ||
				UNDERLINESTROKETYPE_DoubleSolid.equals(getUnderlineStrokeType()) ||
				UNDERLINESTROKETYPE_DoubleDashed.equals(getUnderlineStrokeType()))
			return 2;
		
		return 0;			
	}
	
	/**
	 * Get overline style
	 * 0 - none
	 * 1 - single
	 * 2 - double
	 * @return
	 */
	public int getOverline()
	{
		if ( OVERLINESTROKETYPE_Dotted.equals(getOverlineStrokeType()) ||
				OVERLINESTROKETYPE_Solid.equals(getOverlineStrokeType()) ||
				OVERLINESTROKETYPE_Dashed.equals(getOverlineStrokeType()))
			return 1;
		else if ( OVERLINESTROKETYPE_DoubleDotted.equals(getOverlineStrokeType()) ||
				OVERLINESTROKETYPE_DoubleSolid.equals(getOverlineStrokeType()) ||
				OVERLINESTROKETYPE_DoubleDashed.equals(getOverlineStrokeType()))
			return 2;
		
		return 0;			
	}

	/**
	 * 	Get UnderLine Stroke 
	 *	@return line based on line (1/2 of) width and stroke (default dotted 1/2p 
	 */
	public Stroke getUnderlineStroke(BigDecimal stroke)
	{
		if (underline_Stroke == null)
		{
			float width = stroke.floatValue() / 2;
			if ( UNDERLINESTROKETYPE_Dotted.equals(getUnderlineStrokeType()) || UNDERLINESTROKETYPE_DoubleDotted.equals(getUnderlineStrokeType()))
				underline_Stroke = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
					1.0f, getPatternDotted(width), 0.0f);			//	. . .
			//		
			else if (UNDERLINESTROKETYPE_Solid.equals(getUnderlineStrokeType()) || UNDERLINESTROKETYPE_DoubleSolid.equals(getUnderlineStrokeType()))
				underline_Stroke = new BasicStroke(width);				//	-
			else if (UNDERLINESTROKETYPE_Dashed.equals(getUnderlineStrokeType())|| UNDERLINESTROKETYPE_DoubleDashed.equals(getUnderlineStrokeType()))
				underline_Stroke = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
					1.0f, getPatternDashed(width), 0.0f);			//	- -
		
		}
		return underline_Stroke;
	}	//	getUnderine_Stroke
	
	/**
	 * 	Get OverLine Stroke 
	 *	@return line based on line (1/2 of) width and stroke (default dotted 1/2p 
	 */
	public Stroke getOverlineStroke(BigDecimal stroke)
	{
		if (overline_Stroke == null)
		{
			float width = stroke.floatValue() / 2;
			if ( UNDERLINESTROKETYPE_Dotted.equals(getOverlineStrokeType())  || UNDERLINESTROKETYPE_DoubleDotted.equals(getOverlineStrokeType()))
				overline_Stroke = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
					1.0f, getPatternDotted(width), 0.0f);			//	. . .
			//		
			else if (UNDERLINESTROKETYPE_Solid.equals(getOverlineStrokeType()) || UNDERLINESTROKETYPE_DoubleSolid.equals(getOverlineStrokeType()))
				overline_Stroke = new BasicStroke(width);				//	-
			else if (UNDERLINESTROKETYPE_Dashed.equals(getOverlineStrokeType()) || UNDERLINESTROKETYPE_DoubleDashed.equals(getOverlineStrokeType()))
				overline_Stroke = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
					1.0f, getPatternDashed(width), 0.0f);			//	- -
			
		}
		return overline_Stroke;
	}	//	getUnderine_Stroke
}	//	MReportLine

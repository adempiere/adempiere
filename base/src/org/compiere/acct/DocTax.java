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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Document Tax Line
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: DocTax.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public final class DocTax
{
	/**
	 *	Create Tax
	 *  @param C_Tax_ID tax
	 *  @param name name
	 *  @param rate rate
	 *  @param taxBaseAmt tax base amount
	 *  @param amount amount
	 * 	@param salesTax sales tax flag
	 */
	public DocTax (int C_Tax_ID, String name, BigDecimal rate, 
		BigDecimal taxBaseAmt, BigDecimal amount, boolean salesTax)
	{
		m_C_Tax_ID = C_Tax_ID;
		m_name = name;
		m_rate = rate;
		m_amount = amount;
		m_salesTax = salesTax;
	}	//	DocTax

	/** Tax ID              */
	private int			m_C_Tax_ID = 0;
	/** Amount              */
	private BigDecimal 	m_amount = null;
	/** Tax Rate            */
	private BigDecimal 	m_rate = null;
	/** Name                */
	private String 		m_name = null;
	/** Base Tax Amt        */
	private BigDecimal  m_taxBaseAmt = null;
	/** Included Tax		*/
	private BigDecimal	m_includedTax = Env.ZERO;
	/** Sales Tax			*/
	private boolean		m_salesTax = false;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(DocTax.class);
	

	/** Tax Due Acct        */
	public static final int    ACCTTYPE_TaxDue = 0;
	/** Tax Liability       */
	public static final int    ACCTTYPE_TaxLiability = 1;
	/** Tax Credit          */
	public static final int    ACCTTYPE_TaxCredit = 2;
	/** Tax Receivables     */
	public static final int    ACCTTYPE_TaxReceivables = 3;
	/** Tax Expense         */
	public static final int    ACCTTYPE_TaxExpense = 4;

	/**
	 *	Get Account
	 *  @param AcctType see ACCTTYPE_*
	 *  @param as account schema
	 *  @return Account
	 */
	public MAccount getAccount (int AcctType, MAcctSchema as)
	{
		if (AcctType < 0 || AcctType > 4)
			return null;
		//
		String sql = "SELECT T_Due_Acct, T_Liability_Acct, T_Credit_Acct, T_Receivables_Acct, T_Expense_Acct "
			+ "FROM C_Tax_Acct WHERE C_Tax_ID=? AND C_AcctSchema_ID=?";
		int validCombination_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_C_Tax_ID);
			pstmt.setInt(2, as.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombination_ID = rs.getInt(AcctType+1);    //  1..5
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombination_ID == 0)
			return null;
		return MAccount.get(as.getCtx(), validCombination_ID);
	}   //  getAccount

	/**
	 *	Get Amount
	 *  @return gross amount
	 */
	public BigDecimal getAmount()
	{
		return m_amount;
	}

	/**
	 *  Get Base Amount
	 *  @return net amount
	 */
	public BigDecimal getTaxBaseAmt()
	{
		return m_taxBaseAmt;
	}

	/**
	 *	Get Rate
	 *  @return tax rate in percent
	 */
	public BigDecimal getRate()
	{
		return m_rate;
	}

	/**
	 *  Get Name of Tax
	 *  @return name
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * 	Get C_Tax_ID
	 *	@return tax id
	 */
	public int getC_Tax_ID()
	{
		return m_C_Tax_ID;
	}	//	getC_Tax_ID
	
	/**
	 *  Get Description (Tax Name and Base Amount)
	 *  @return tax anme and base amount
	 */
	public String getDescription()
	{
		return m_name + " " + m_taxBaseAmt.toString();
	}   //  getDescription

	/**
	 * 	Add to Included Tax
	 *	@param amt amount
	 */
	public void addIncludedTax (BigDecimal amt)
	{
		m_includedTax = m_includedTax.add(amt);
	}	//	addIncludedTax
	
	/**
	 * 	Get Included Tax
	 *	@return tax amount
	 */
	public BigDecimal getIncludedTax()
	{
		return m_includedTax;
	}	//	getIncludedTax
	
	/**
	 * 	Get Included Tax Difference
	 *	@return tax ampunt - included amount
	 */
	public BigDecimal getIncludedTaxDifference()
	{
		return m_amount.subtract(m_includedTax);
	}	//	getIncludedTaxDifference
	
	/**
	 * 	Included Tax differs from tax amount
	 *	@return true if difference
	 */
	public boolean isIncludedTaxDifference()
	{
		return Env.ZERO.compareTo(getIncludedTaxDifference()) != 0;
	}	//	isIncludedTaxDifference
	
	/**
	 * 	Get AP Tax Type
	 *	@return AP tax type (Credit or Expense) 
	 */
	public int getAPTaxType()
	{
		if (isSalesTax())
			return ACCTTYPE_TaxExpense;
		return ACCTTYPE_TaxCredit;
	}	//	getAPTaxAcctType

	/**
	 * 	Is Sales Tax
	 *	@return sales tax
	 */
	public boolean isSalesTax()
	{
		return m_salesTax;
	}	//	isSalesTax
	
	
	/**
	 *	Return String representation
	 *  @return tax anme and base amount
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("Tax=(");
		sb.append(m_name);
		sb.append(" Amt=").append(m_amount);
		sb.append(")");
		return sb.toString();
	}	//	toString

}	//	DocTax

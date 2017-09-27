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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MFactAcct;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMovement;
import org.compiere.model.MRevenueRecognitionPlan;
import org.compiere.model.X_C_AcctSchema_Element;
import org.compiere.model.X_Fact_Acct;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Accounting Fact Entry.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: FactLine.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  Contributor(s):
 *  Chris Farley: Fix Bug [ 1657372 ] M_MatchInv records can not be balanced
 *    https://sourceforge.net/forum/message.php?msg_id=4151117
 *  Carlos Ruiz - globalqss: Add setAmtAcct method rounded by Currency
 *  Armen Rizal, Goodwill Consulting
 * 			<li>BF [ 1745154 ] Cost in Reversing Material Related Docs  
 *  Bayu Sistematika -  
 *  		<li>BF [ 2213252 ] Matching Inv-Receipt generated unproperly value for src amt
 *	Teo Sarca
 *			<li>FR [ 2819081 ] FactLine.getDocLine should be public
 *				https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2819081&group_id=176962
 *  
 */
public final class FactLine extends X_Fact_Acct
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6141312459030795891L;

	/**
	 *	Constructor
	 *	@param ctx context
	 *  @param AD_Table_ID  - Table of Document Source
	 *  @param Record_ID    - Record of document
	 *  @param Line_ID      - Optional line id
	 *  @param trxName transaction
	 */
	public FactLine (Properties ctx, int AD_Table_ID, int Record_ID, int Line_ID, String trxName)
	{
		super(ctx, 0, trxName);
		setAD_Client_ID(0);							//	do not derive
		setAD_Org_ID(0);							//	do not derive
		//
		setAmtAcctCr (Env.ZERO);
		setAmtAcctDr (Env.ZERO);
		setAmtSourceCr (Env.ZERO);
		setAmtSourceDr (Env.ZERO);
	//	Log.trace(this,Log.l1_User, "FactLine " + AD_Table_ID + ":" + Record_ID);
		setAD_Table_ID (AD_Table_ID);
		setRecord_ID (Record_ID);
		setLine_ID (Line_ID);
	}   //  FactLine

	/**	Account					*/
	private MAccount	m_acct = null;
	/** Accounting Schema		*/
	private MAcctSchema	m_acctSchema = null;
	/** Document Header			*/
	private Doc			m_doc = null;
	/** Document Line 			*/
	private DocLine 	m_docLine = null;
	
	/**
	 * 	Create Reversal (negate DR/CR) of the line
	 *	@param description new description
	 *	@return reversal line
	 */
	public FactLine reverse (String description)
	{
		FactLine reversal = new FactLine (getCtx(), getAD_Table_ID(), getRecord_ID(), getLine_ID(), get_TrxName());
		reversal.setClientOrg(this);	//	needs to be set explicitly
		reversal.setDocumentInfo(m_doc, m_docLine);
		reversal.setAccount(m_acctSchema, m_acct);
		reversal.setPostingType(getPostingType());
		//
		reversal.setAmtSource(getC_Currency_ID(), getAmtSourceDr().negate(), getAmtSourceCr().negate());
		reversal.setQty(getQty().negate());
		reversal.convert();
		reversal.setDescription(description);
		return reversal;
	}	//	reverse

	/**
	 * 	Create Accrual (flip CR/DR) of the line
	 *	@param description new description
	 *	@return accrual line
	 */
	public FactLine accrue (String description)
	{
		FactLine accrual = new FactLine (getCtx(), getAD_Table_ID(), getRecord_ID(), getLine_ID(), get_TrxName());
		accrual.setClientOrg(this);	//	needs to be set explicitly
		accrual.setDocumentInfo(m_doc, m_docLine);
		accrual.setAccount(m_acctSchema, m_acct);
		accrual.setPostingType(getPostingType());
		//
		accrual.setAmtSource(getC_Currency_ID(), getAmtSourceCr(), getAmtSourceDr());
		accrual.convert();
		accrual.setDescription(description);
		return accrual;
	}	//	reverse

	/**
	 *  Set Account Info
	 *  @param acctSchema account schema
	 *  @param acct account
	 */
	public void setAccount (MAcctSchema acctSchema, MAccount acct)
	{
		m_acctSchema = acctSchema;
		setC_AcctSchema_ID (acctSchema.getC_AcctSchema_ID());
		//
		m_acct = acct;
		if (getAD_Client_ID() == 0)
			setAD_Client_ID(m_acct.getAD_Client_ID());
		setAccount_ID (m_acct.getAccount_ID());
		setC_SubAcct_ID(m_acct.getC_SubAcct_ID());

		//	User Defined References
		MAcctSchemaElement acctSchemaElement1 = m_acctSchema.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserElement1);
		if (acctSchemaElement1 != null)
		{
			String ColumnName1 = acctSchemaElement1.getDisplayColumnName();
			if (ColumnName1 != null)
			{
				int userElement1Id = 0;
				if (m_docLine != null)
					userElement1Id = m_docLine.getValue(ColumnName1);
				if (userElement1Id == 0)
				{
					if (m_doc == null)
						throw new IllegalArgumentException("Document not set yet");
					userElement1Id = m_doc.getValue(ColumnName1);
				}
				if (userElement1Id == 0 && acct.getUserElement1_ID() > 0 )
					userElement1Id = acct.getUserElement1_ID();
				if (userElement1Id != 0)
					setUserElement1_ID(userElement1Id);
			}
		}
		MAcctSchemaElement acctSchemaElement2 = m_acctSchema.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserElement2);
		if (acctSchemaElement2 != null)
		{
			String ColumnName2 = acctSchemaElement2.getDisplayColumnName();
			if (ColumnName2 != null)
			{
				int userElement2Id = 0;
				if (m_docLine != null)
					userElement2Id = m_docLine.getValue(ColumnName2);
				if (userElement2Id == 0)
				{
					if (m_doc == null)
						throw new IllegalArgumentException("Document not set yet");
					userElement2Id = m_doc.getValue(ColumnName2);
				}
				if (userElement2Id == 0 && acct.getUserElement2_ID() > 0 )
					userElement2Id = acct.getUserElement2_ID();

				if (userElement2Id != 0)
					setUserElement2_ID(userElement2Id);
			}
		}
	}   //  setAccount

	/**
	 *  Set Source Amounts
	 *  @param C_Currency_ID currency
	 *  @param AmtSourceDr source amount dr
	 *  @param AmtSourceCr source amount cr
	 *  @return true, if any if the amount is not zero
	 */
	public boolean setAmtSource (int C_Currency_ID, BigDecimal AmtSourceDr, BigDecimal AmtSourceCr)
	{
		if (! m_acctSchema.isAllowNegativePosting()) {
	        // begin Victor Perez e-evolution 30.08.2005
			// fix Debit & Credit 
			if (AmtSourceDr != null)
			{	
				if (AmtSourceDr.compareTo(Env.ZERO) == -1)
				{	
					AmtSourceCr = AmtSourceDr.abs();
					AmtSourceDr = Env.ZERO;
				}
			}
			if (AmtSourceCr != null)
			{	
				if (AmtSourceCr.compareTo(Env.ZERO) == -1)
				{	
					AmtSourceDr = AmtSourceCr.abs();
					AmtSourceCr = Env.ZERO;
				}
			}
			// end Victor Perez e-evolution 30.08.2005
		}
		
		setC_Currency_ID (C_Currency_ID);
		if (AmtSourceDr != null)
			setAmtSourceDr (AmtSourceDr);
		if (AmtSourceCr != null)
			setAmtSourceCr (AmtSourceCr);

		//  one needs to be non zero
		//if (getAmtSourceDr().compareTo(Env.ZERO)==0 && getAmtSourceCr().compareTo(Env.ZERO)==0)
		//	return false;

		//	Currency Precision
		int precision = MCurrency.getStdPrecision(getCtx(), C_Currency_ID);
		if (AmtSourceDr != null && AmtSourceDr.scale() > precision)
		{
			BigDecimal AmtSourceDr1 = AmtSourceDr.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (AmtSourceDr1.compareTo(AmtSourceDr) != 0)
				log.warning("Source DR Precision " + AmtSourceDr + " -> " + AmtSourceDr1);
			setAmtSourceDr(AmtSourceDr1);
		}
		if (AmtSourceCr != null && AmtSourceCr.scale() > precision)
		{
			BigDecimal AmtSourceCr1 = AmtSourceCr.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (AmtSourceCr1.compareTo(AmtSourceCr) != 0)
				log.warning("Source CR Precision " + AmtSourceCr + " -> " + AmtSourceCr1);
			setAmtSourceCr(AmtSourceCr1);
		}
		return true;
	}   //  setAmtSource

	/**
	 *  Set Accounted Amounts (alternative: call convert)
	 *  @param AmtAcctDr acct amount dr
	 *  @param AmtAcctCr acct amount cr
	 */
	public void setAmtAcct(BigDecimal AmtAcctDr, BigDecimal AmtAcctCr)
	{
		if (! m_acctSchema.isAllowNegativePosting()) {
	        // begin Victor Perez e-evolution 30.08.2005
			// fix Debit & Credit 
			if (AmtAcctDr.compareTo(Env.ZERO) == -1)
			{	
				AmtAcctCr = AmtAcctDr.abs();
				AmtAcctDr = Env.ZERO;
			}
			if (AmtAcctCr.compareTo(Env.ZERO) == -1)
			{	
				AmtAcctDr = AmtAcctCr.abs();
				AmtAcctCr = Env.ZERO;
			}
			// end Victor Perez e-evolution 30.08.2005
		}
		setAmtAcctDr (AmtAcctDr);
		setAmtAcctCr (AmtAcctCr);
	}   //  setAmtAcct

	/**
	 *  Set Accounted Amounts rounded by currency
	 *  @param C_Currency_ID currency
	 *  @param AmtAcctDr acct amount dr
	 *  @param AmtAcctCr acct amount cr
	 */
	public void setAmtAcct(int C_Currency_ID, BigDecimal AmtAcctDr, BigDecimal AmtAcctCr)
	{

		if (! m_acctSchema.isAllowNegativePosting()) {
			if (AmtAcctDr.compareTo(Env.ZERO) == -1)
			{
				AmtAcctCr = AmtAcctDr.abs();
				AmtAcctDr = Env.ZERO;
			}
			if (AmtAcctCr.compareTo(Env.ZERO) == -1)
			{
				AmtAcctDr = AmtAcctCr.abs();
				AmtAcctCr = Env.ZERO;
			}
		}

		setAmtAcctDr (AmtAcctDr);
		setAmtAcctCr (AmtAcctCr);
		//	Currency Precision
		int precision = MCurrency.getStdPrecision(getCtx(), C_Currency_ID);
		if (AmtAcctDr != null && AmtAcctDr.scale() > precision)
		{
			BigDecimal AmtAcctDr1 = AmtAcctDr.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (AmtAcctDr1.compareTo(AmtAcctDr) != 0)
				log.warning("Accounted DR Precision " + AmtAcctDr + " -> " + AmtAcctDr1);
			setAmtAcctDr(AmtAcctDr1);
		}
		if (AmtAcctCr != null && AmtAcctCr.scale() > precision)
		{
			BigDecimal AmtAcctCr1 = AmtAcctCr.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (AmtAcctCr1.compareTo(AmtAcctCr) != 0)
				log.warning("Accounted CR Precision " + AmtAcctCr + " -> " + AmtAcctCr1);
			setAmtAcctCr(AmtAcctCr1);
		}
	}   //  setAmtAcct

	/**
	 *  Set Document Info
	 *  @param doc document
	 *  @param docLine doc line
	 */
	public void setDocumentInfo(Doc doc, DocLine docLine)
	{
		m_doc = doc;
		m_docLine = docLine;
		//	reset
		setAD_Org_ID(0);
		setC_SalesRegion_ID(0);
		if (docLine != null)
			setLine_ID(docLine.get_ID());
		//	Client
		if (getAD_Client_ID() == 0)
			setAD_Client_ID (m_doc.getAD_Client_ID());
		//	Date Trx
		setDateTrx (m_doc.getDateDoc());
		if (m_docLine != null && m_docLine.getDateDoc() != null)
			setDateTrx (m_docLine.getDateDoc());
		//	Date Acct
		setDateAcct (m_doc.getDateAcct());
		if (m_docLine != null && m_docLine.getDateAcct() != null)
			setDateAcct (m_docLine.getDateAcct());
		//	Period, Tax
		if (m_docLine != null &&  m_docLine.getC_Period_ID() != 0)
			setC_Period_ID(m_docLine.getC_Period_ID());
		else
			setC_Period_ID (m_doc.getC_Period_ID());
		if (m_docLine != null)
			setC_Tax_ID (m_docLine.getC_Tax_ID());
		//	Description
		StringBuffer description = new StringBuffer(m_doc.getDocumentNo());
		if (m_docLine != null)
		{
			description.append(" #").append(m_docLine.getLine());
			if (m_docLine.getDescription() != null)
				description.append(" (").append(m_docLine.getDescription()).append(")");
			else if (m_doc.getDescription() != null && m_doc.getDescription().length() > 0)
				description.append(" (").append(m_doc.getDescription()).append(")");		
		}
		else if (m_doc.getDescription() != null && m_doc.getDescription().length() > 0)
			description.append(" (").append(m_doc.getDescription()).append(")");
		setDescription(description.toString());
		//	Journal Info
		setGL_Budget_ID (m_doc.getGL_Budget_ID());
		setGL_Category_ID (m_doc.getGL_Category_ID());

		//	Product
		if (m_docLine != null)
			setM_Product_ID (m_docLine.getM_Product_ID());
		if (getM_Product_ID() == 0)
			setM_Product_ID (m_doc.getM_Product_ID());
		//	UOM
		if (m_docLine != null)
			setC_UOM_ID (m_docLine.getC_UOM_ID());
		//	Qty
		if (get_Value("Qty") == null)	// not previously set
		{
			setQty (m_doc.getQty());	//	neg = outgoing
			if (m_docLine != null)
				setQty (m_docLine.getQty());
		}
		
		//	Loc From (maybe set earlier)
		if (getC_LocFrom_ID() == 0 && m_docLine != null)
			setC_LocFrom_ID (m_docLine.getC_LocFrom_ID());
		if (getC_LocFrom_ID() == 0)
			setC_LocFrom_ID (m_doc.getC_LocFrom_ID());
		//	Loc To (maybe set earlier)
		if (getC_LocTo_ID() == 0 && m_docLine != null)
			setC_LocTo_ID (m_docLine.getC_LocTo_ID());
		if (getC_LocTo_ID() == 0)
			setC_LocTo_ID (m_doc.getC_LocTo_ID());
		//	BPartner
		if (m_docLine != null)
			setC_BPartner_ID (m_docLine.getC_BPartner_ID());
		if (getC_BPartner_ID() == 0)
			setC_BPartner_ID (m_doc.getC_BPartner_ID());
		//	Sales Region from BPLocation/Sales Rep
		//	Trx Org
		if (m_docLine != null)
			setAD_OrgTrx_ID (m_docLine.getAD_OrgTrx_ID());
		if (getAD_OrgTrx_ID() == 0)
			setAD_OrgTrx_ID (m_doc.getAD_OrgTrx_ID());
		//	Project
		if (m_docLine != null)
			setC_Project_ID (m_docLine.getC_Project_ID());
		if (getC_Project_ID() == 0)
			setC_Project_ID (m_doc.getC_Project_ID());
		if (m_docLine != null)
			setC_ProjectPhase_ID(m_docLine.getC_ProjectPhase_ID());
		if (getC_ProjectPhase_ID() == 0)
			setC_ProjectPhase_ID (m_doc.getC_ProjectPhase_ID());
		if (m_docLine != null)
			setC_ProjectTask_ID(m_docLine.getC_ProjectTask_ID());
		if (getC_ProjectTask_ID() == 0)
			setC_ProjectTask_ID (m_doc.getC_ProjectTask_ID());
		//	Campaign
		if (m_docLine != null)
			setC_Campaign_ID (m_docLine.getC_Campaign_ID());
		if (getC_Campaign_ID() == 0)
			setC_Campaign_ID (m_doc.getC_Campaign_ID());
		//	Activity
		if (m_docLine != null)
			setC_Activity_ID (m_docLine.getC_Activity_ID());
		if (getC_Activity_ID() == 0)
			setC_Activity_ID (m_doc.getC_Activity_ID());
		//	User List 1
		if (m_docLine != null)
			setUser1_ID (m_docLine.getUser1_ID());
		if (getUser1_ID() == 0)
			setUser1_ID (m_doc.getUser1_ID());
		//	User List 2
		if (m_docLine != null)
			setUser2_ID (m_docLine.getUser2_ID());
		if (getUser2_ID() == 0)
			setUser2_ID (m_doc.getUser2_ID());
		//	User List 3
		if (m_docLine != null)
			setUser3_ID (m_docLine.getUser3_ID());
		if (getUser3_ID() == 0)
			setUser3_ID (m_doc.getUser3_ID());
		//	User List 4
		if (m_docLine != null)
			setUser4_ID (m_docLine.getUser4_ID());
		if (getUser4_ID() == 0)
			setUser4_ID (m_doc.getUser4_ID());
		if (m_docLine != null)
			setUserElement1_ID (m_docLine.getUserElement1_ID());
		if (getUserElement1_ID() == 0)
			setUserElement1_ID (m_doc.getUserElement1_ID());
		//	User List 2
		if (m_docLine != null)
			setUserElement2_ID(m_docLine.getUserElement2_ID());
		if (getUserElement2_ID() == 0)
			setUserElement2_ID(m_doc.getUserElement2_ID());
		//	References in setAccount
	}   //  setDocumentInfo

	/**
	 * 	Get Document Line
	 *	@return doc line
	 */
	public DocLine getDocLine()
	{
		return m_docLine;
	}	//	getDocLine
	
	/**
	 * 	Set Description
	 *	@param description description
	 */
	public void addDescription (String description)
	{
		if (description == null)
			return;

		String original = getDescription();
		if (original == null || original.trim().length() == 0)
			super.setDescription(description);
		else
			super.setDescription(original + " - " + description);
	}	//	addDescription
	
	/**
	 *  Set Warehouse Locator.
	 *  - will overwrite Organization -
	 *  @param M_Locator_ID locator
	 */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		super.setM_Locator_ID (M_Locator_ID);
		setAD_Org_ID(0);	//	reset
	}   //  setM_Locator_ID

	
	/**************************************************************************
	 *  Set Location
	 *  @param C_Location_ID location
	 *  @param isFrom from
	 */
	public void setLocation (int C_Location_ID, boolean isFrom)
	{
		if (isFrom)
			setC_LocFrom_ID (C_Location_ID);
		else
			setC_LocTo_ID (C_Location_ID);
	}   //  setLocator

	/**
	 *  Set Location from Locator
	 *  @param M_Locator_ID locator
	 *  @param isFrom from
	 */
	public void setLocationFromLocator (int M_Locator_ID, boolean isFrom)
	{
		if (M_Locator_ID == 0)
			return;
		int C_Location_ID = 0;
		String sql = "SELECT w.C_Location_ID FROM M_Warehouse w, M_Locator l "
			+ "WHERE w.M_Warehouse_ID=l.M_Warehouse_ID AND l.M_Locator_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, M_Locator_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				C_Location_ID = rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (C_Location_ID != 0)
			setLocation (C_Location_ID, isFrom);
	}   //  setLocationFromLocator

	/**
	 *  Set Location from Busoness Partner Location
	 *  @param C_BPartner_Location_ID bp location
	 *  @param isFrom from
	 */
	public void setLocationFromBPartner (int C_BPartner_Location_ID, boolean isFrom)
	{
		if (C_BPartner_Location_ID == 0)
			return;
		int C_Location_ID = 0;
		String sql = "SELECT C_Location_ID FROM C_BPartner_Location WHERE C_BPartner_Location_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, C_BPartner_Location_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				C_Location_ID = rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (C_Location_ID != 0)
			setLocation (C_Location_ID, isFrom);
	}   //  setLocationFromBPartner

	/**
	 *  Set Location from Organization
	 *  @param AD_Org_ID org
	 *  @param isFrom from
	 */
	public void setLocationFromOrg (int AD_Org_ID, boolean isFrom)
	{
		if (AD_Org_ID == 0)
			return;
		int C_Location_ID = 0;
		String sql = "SELECT C_Location_ID FROM AD_OrgInfo WHERE AD_Org_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, AD_Org_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				C_Location_ID = rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (C_Location_ID != 0)
			setLocation (C_Location_ID, isFrom);
	}   //  setLocationFromOrg

	
	/**************************************************************************
	 *  Returns Source Balance of line
	 *  @return source balance
	 */
	public BigDecimal getSourceBalance()
	{
		if (getAmtSourceDr() == null)
			setAmtSourceDr (Env.ZERO);
		if (getAmtSourceCr() == null)
			setAmtSourceCr (Env.ZERO);
		//
		return getAmtSourceDr().subtract(getAmtSourceCr());
	}   //  getSourceBalance

	/**
	 *	Is Debit Source Balance
	 *  @return true if DR source balance
	 */
	public boolean isDrSourceBalance()
	{
		return getSourceBalance().signum() != -1;
	}   //  isDrSourceBalance

	/**
	 *  Get Accounted Balance
	 *  @return accounting balance
	 */
	public BigDecimal getAcctBalance()
	{
		if (getAmtAcctDr() == null)
			setAmtAcctDr (Env.ZERO);
		if (getAmtAcctCr() == null)
			setAmtAcctCr (Env.ZERO);
		return getAmtAcctDr().subtract(getAmtAcctCr());
	}   //  getAcctBalance

	/**
	 *	Is Account on Balance Sheet
	 *  @return true if account is a balance sheet account
	 */
	public boolean isBalanceSheet()
	{
		return m_acct.isBalanceSheet();
	}	//	isBalanceSheet

	/**
	 *	Currect Accounting Amount.
	 *  <pre>
	 *  Example:    1       -1      1       -1
	 *  Old         100/0   100/0   0/100   0/100
	 *  New         99/0    101/0   0/99    0/101
	 *  </pre>
	 *  @param deltaAmount delta amount
	 */
	public void currencyCorrect (BigDecimal deltaAmount)
	{
		boolean negative = deltaAmount.compareTo(Env.ZERO) < 0;
		boolean adjustDr = getAmtAcctDr().abs().compareTo(getAmtAcctCr().abs()) > 0;

		log.fine(deltaAmount.toString()
			+ "; Old-AcctDr=" + getAmtAcctDr() + ",AcctCr=" + getAmtAcctCr()
			+ "; Negative=" + negative + "; AdjustDr=" + adjustDr);

		if (adjustDr)
			if (negative)
				setAmtAcctDr (getAmtAcctDr().subtract(deltaAmount));
			else
				setAmtAcctDr (getAmtAcctDr().subtract(deltaAmount));
		else
			if (negative)
				setAmtAcctCr (getAmtAcctCr().add(deltaAmount));
			else
				setAmtAcctCr (getAmtAcctCr().add(deltaAmount));

		log.fine("New-AcctDr=" + getAmtAcctDr() + ",AcctCr=" + getAmtAcctCr());
	}	//	currencyCorrect

	/**
	 *  Convert to Accounted Currency
	 *  @return true if converted
	 */
	public boolean convert ()
	{
		//  Document has no currency
		if (getC_Currency_ID() == Doc.NO_CURRENCY)
			setC_Currency_ID (m_acctSchema.getC_Currency_ID());

		if (m_acctSchema.getC_Currency_ID() == getC_Currency_ID())
		{
			setAmtAcctDr (getAmtSourceDr());
			setAmtAcctCr (getAmtSourceCr());
			return true;
		}
		//	Get Conversion Type from Line or Header
		int C_ConversionType_ID = 0;
		int AD_Org_ID = 0;
		if (m_docLine != null)			//	get from line
		{
			C_ConversionType_ID = m_docLine.getC_ConversionType_ID();
			AD_Org_ID = m_docLine.getAD_Org_ID();
		}
		if (C_ConversionType_ID == 0)	//	get from header
		{
			if (m_doc == null)
			{
				log.severe ("No Document VO");
				return false;
			}
			C_ConversionType_ID = m_doc.getC_ConversionType_ID();
			if (AD_Org_ID == 0)
				AD_Org_ID = m_doc.getAD_Org_ID();
		}
		setAmtAcctDr (MConversionRate.convert (getCtx(),
			getAmtSourceDr(), getC_Currency_ID(), m_acctSchema.getC_Currency_ID(),
			getDateAcct(), C_ConversionType_ID, m_doc.getAD_Client_ID(), AD_Org_ID));
		if (getAmtAcctDr() == null)
			return false;
		setAmtAcctCr (MConversionRate.convert (getCtx(),
			getAmtSourceCr(), getC_Currency_ID(), m_acctSchema.getC_Currency_ID(),
			getDateAcct(), C_ConversionType_ID, m_doc.getAD_Client_ID(), AD_Org_ID));
		return true;
	}	//	convert

	/**
	 * 	Get Account
	 *	@return account
	 */
	public MAccount getAccount()
	{
		return m_acct;
	}	//	getAccount
	
	/**
	 *	To String
	 *  @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("FactLine=[");
		sb.append(getAD_Table_ID()).append(":").append(getRecord_ID())
			.append(",").append(m_acct)
			.append(",Cur=").append(getC_Currency_ID())
			.append(", DR=").append(getAmtSourceDr()).append("|").append(getAmtAcctDr())
			.append(", CR=").append(getAmtSourceCr()).append("|").append(getAmtAcctCr())
			.append("]");
		return sb.toString();
	}	//	toString

	
	/**
	 *  Get AD_Org_ID (balancing segment).
	 *  (if not set directly - from document line, document, account, locator)
	 *  <p>
	 *  Note that Locator needs to be set before - otherwise
	 *  segment balancing might produce the wrong results
	 *  @return AD_Org_ID
	 */
	public int getAD_Org_ID()
	{
		if (super.getAD_Org_ID() != 0)      //  set earlier
			return super.getAD_Org_ID();
		//	Prio 1 - get from locator - if exist
		if (getM_Locator_ID() != 0)
		{
			String sql = "SELECT AD_Org_ID FROM M_Locator WHERE M_Locator_ID=? AND AD_Client_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getM_Locator_ID());
				pstmt.setInt(2, getAD_Client_ID());
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					setAD_Org_ID (rs.getInt(1));
					log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (1 from M_Locator_ID=" + getM_Locator_ID() + ")");
				}
				else
					log.log(Level.SEVERE, "AD_Org_ID - Did not find M_Locator_ID=" + getM_Locator_ID());
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}   //  M_Locator_ID != 0

		//	Prio 2 - get from doc line - if exists (document context overwrites)
		if (m_docLine != null && super.getAD_Org_ID() == 0)
		{
			setAD_Org_ID (m_docLine.getAD_Org_ID());
			log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (2 from DocumentLine)");
		}
		//	Prio 3 - get from doc - if not GL
		if (m_doc != null && super.getAD_Org_ID() == 0)
		{
			if (Doc.DOCTYPE_GLJournal.equals (m_doc.getDocumentType()))
			{
				setAD_Org_ID (m_acct.getAD_Org_ID()); //	inter-company GL
				log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (3 from Acct)");
			}
			else
			{
				setAD_Org_ID (m_doc.getAD_Org_ID());
				log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (3 from Document)");
			}
		}
		//	Prio 4 - get from account - if not GL
		if (m_doc != null && super.getAD_Org_ID() == 0)
		{
			if (Doc.DOCTYPE_GLJournal.equals (m_doc.getDocumentType()))
			{
				setAD_Org_ID (m_doc.getAD_Org_ID());
				log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (4 from Document)");
			}
			else
			{
				setAD_Org_ID (m_acct.getAD_Org_ID());
				log.finer("AD_Org_ID=" + super.getAD_Org_ID() + " (4 from Acct)");
			}
		}
		return super.getAD_Org_ID();
	}   //  setAD_Org_ID


	/**
	 *	Get/derive Sales Region
	 *	@return Sales Region
	 */
	public int getC_SalesRegion_ID ()
	{
		if (super.getC_SalesRegion_ID() != 0)
			return super.getC_SalesRegion_ID();
		//
		if (m_docLine != null)
			setC_SalesRegion_ID (m_docLine.getC_SalesRegion_ID());
		if (m_doc != null)
		{
			if (super.getC_SalesRegion_ID() == 0)
				setC_SalesRegion_ID (m_doc.getC_SalesRegion_ID());
			if (super.getC_SalesRegion_ID() == 0 && m_doc.getBP_C_SalesRegion_ID() > 0)
				setC_SalesRegion_ID (m_doc.getBP_C_SalesRegion_ID());
			//	derive SalesRegion if AcctSegment
			if (super.getC_SalesRegion_ID() == 0
				&& m_doc.getC_BPartner_Location_ID() != 0
				&& m_doc.getBP_C_SalesRegion_ID() == -1)	//	never tried
			//	&& m_acctSchema.isAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_SalesRegion))
			{
				String sql = "SELECT COALESCE(C_SalesRegion_ID,0) FROM C_BPartner_Location WHERE C_BPartner_Location_ID=?";
				setC_SalesRegion_ID (DB.getSQLValue(null,
					sql, m_doc.getC_BPartner_Location_ID()));
				if (super.getC_SalesRegion_ID() != 0)		//	save in VO
				{
					m_doc.setBP_C_SalesRegion_ID(super.getC_SalesRegion_ID());
					log.fine("C_SalesRegion_ID=" + super.getC_SalesRegion_ID() + " (from BPL)" );
				}
				else	//	From Sales Rep of Document -> Sales Region
				{
					sql = "SELECT COALESCE(MAX(C_SalesRegion_ID),0) FROM C_SalesRegion WHERE SalesRep_ID=?";
					setC_SalesRegion_ID (DB.getSQLValue(null,
						sql, m_doc.getSalesRep_ID()));
					if (super.getC_SalesRegion_ID() != 0)		//	save in VO
					{
						m_doc.setBP_C_SalesRegion_ID(super.getC_SalesRegion_ID());
						log.fine("C_SalesRegion_ID=" + super.getC_SalesRegion_ID() + " (from SR)" );
					}
					else
						m_doc.setBP_C_SalesRegion_ID(-2);	//	don't try again
				}
			}
			if (m_acct != null && super.getC_SalesRegion_ID() == 0)
				setC_SalesRegion_ID (m_acct.getC_SalesRegion_ID());
		}
		//
	//	log.fine("C_SalesRegion_ID=" + super.getC_SalesRegion_ID() 
	//		+ ", C_BPartner_Location_ID=" + m_docVO.C_BPartner_Location_ID
	//		+ ", BP_C_SalesRegion_ID=" + m_docVO.BP_C_SalesRegion_ID 
	//		+ ", SR=" + m_acctSchema.isAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_SalesRegion));
		return super.getC_SalesRegion_ID();
	}	//	getC_SalesRegion_ID

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord)
		{
			log.fine(toString());
			//
			getAD_Org_ID();
			getC_SalesRegion_ID();
			//  Set Default Account Info
			if (getM_Product_ID() == 0)
				setM_Product_ID (m_acct.getM_Product_ID());
			if (getC_LocFrom_ID() == 0)
				setC_LocFrom_ID (m_acct.getC_LocFrom_ID());
			if (getC_LocTo_ID() == 0)
				setC_LocTo_ID (m_acct.getC_LocTo_ID());
			if (getC_BPartner_ID() == 0)
				setC_BPartner_ID (m_acct.getC_BPartner_ID());
			if (getAD_OrgTrx_ID() == 0)
				setAD_OrgTrx_ID (m_acct.getAD_OrgTrx_ID());
			if (getC_Project_ID() == 0)
				setC_Project_ID (m_acct.getC_Project_ID());
			if (getC_Campaign_ID() == 0)
				setC_Campaign_ID (m_acct.getC_Campaign_ID());
			if (getC_Activity_ID() == 0)
				setC_Activity_ID (m_acct.getC_Activity_ID());
			if (getUser1_ID() == 0)
				setUser1_ID (m_acct.getUser1_ID());
			if (getUser2_ID() == 0)
				setUser2_ID (m_acct.getUser2_ID());
			if (getUser3_ID() == 0)
				setUser3_ID (m_acct.getUser3_ID());
			if (getUser4_ID() == 0)
				setUser4_ID (m_acct.getUser4_ID());
			
			//  Revenue Recognition for AR Invoices
			if (m_doc.getDocumentType().equals(Doc.DOCTYPE_ARInvoice) 
				&& m_docLine != null 
				&& m_docLine.getC_RevenueRecognition_ID() != 0)
			{
				int AD_User_ID = 0;
				setAccount_ID (
					createRevenueRecognition (
						m_docLine.getC_RevenueRecognition_ID(), m_docLine.get_ID(),
						getAD_Client_ID(), getAD_Org_ID(), AD_User_ID, 
						getAccount_ID(), getC_SubAcct_ID(),
						getM_Product_ID(), getC_BPartner_ID(), getAD_OrgTrx_ID(),
						getC_LocFrom_ID(), getC_LocTo_ID(), 
						getC_SalesRegion_ID(), getC_Project_ID(),
						getC_Campaign_ID(), getC_Activity_ID(), 
						getUser1_ID(), getUser2_ID(), getUser3_ID(), getUser3_ID(),
						getUserElement1_ID(), getUserElement2_ID())
					);
			}
		}
		return true;
	}	//	beforeSave

	/**************************************************************************
	 *  Revenue Recognition.
	 *  Called from FactLine.save
	 *  <p>
	 *  Create Revenue recognition plan and return Unearned Revenue account
	 *  to be used instead of Revenue Account. If not found, it returns
	 *  the revenue account.
	 *
	 *  @param C_RevenueRecognition_ID revenue recognition
	 *  @param C_InvoiceLine_ID invoice line
	 *  @param AD_Client_ID client
	 *  @param AD_Org_ID org
	 *  @param AD_User_ID user
	 *  @param Account_ID of Revenue Account
	 *  @param C_SubAcct_ID sub account
	 *  @param M_Product_ID product
	 *  @param C_BPartner_ID bpartner
	 *  @param AD_OrgTrx_ID trx org
	 *  @param C_LocFrom_ID loc from
	 *  @param C_LocTo_ID loc to
	 *  @param C_SRegion_ID sales region
	 *  @param C_Project_ID project
	 *  @param C_Campaign_ID campaign
	 *  @param C_Activity_ID activity
	 *  @param User1_ID user1
	 *  @param User2_ID user2
	 *  @param UserElement1_ID user element 1
	 *  @param UserElement2_ID user element 2
	 *  @return Account_ID for Unearned Revenue or Revenue Account if not found
	 */
	@Deprecated
	private int createRevenueRecognition (
			int C_RevenueRecognition_ID, int C_InvoiceLine_ID,
			int AD_Client_ID, int AD_Org_ID, int AD_User_ID,
			int Account_ID, int C_SubAcct_ID,
			int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID,
			int C_LocFrom_ID, int C_LocTo_ID, int C_SRegion_ID, int C_Project_ID,
			int	C_Campaign_ID, int C_Activity_ID,
			int User1_ID, int User2_ID, int UserElement1_ID, int UserElement2_ID)
	{
		return createRevenueRecognition(C_RevenueRecognition_ID , C_InvoiceLine_ID,
				AD_Client_ID , AD_Org_ID , AD_User_ID ,
				Account_ID , C_SubAcct_ID ,
				M_Product_ID , C_BPartner_ID , AD_OrgTrx_ID ,
				C_LocFrom_ID , C_LocTo_ID , C_SRegion_ID , C_Project_ID , C_Campaign_ID , C_Activity_ID ,
				User1_ID, User2_ID , 0 , 0 , UserElement1_ID , UserElement2_ID);
	}

	
	/**************************************************************************
	 *  Revenue Recognition.
	 *  Called from FactLine.save
	 *  <p>
	 *  Create Revenue recognition plan and return Unearned Revenue account
	 *  to be used instead of Revenue Account. If not found, it returns
	 *  the revenue account.
	 *
	 *  @param C_RevenueRecognition_ID revenue recognition
	 *  @param C_InvoiceLine_ID invoice line
	 *  @param AD_Client_ID client
	 *  @param AD_Org_ID org
	 *  @param AD_User_ID user
	 *  @param Account_ID of Revenue Account
	 *  @param C_SubAcct_ID sub account
	 *  @param M_Product_ID product
	 *  @param C_BPartner_ID bpartner
	 *  @param AD_OrgTrx_ID trx org
	 *  @param C_LocFrom_ID loc from
	 *  @param C_LocTo_ID loc to
	 *  @param C_SRegion_ID sales region
	 *  @param C_Project_ID project
	 *  @param C_Campaign_ID campaign
	 *  @param C_Activity_ID activity
	 *  @param User1_ID user1
	 *  @param User2_ID user2
	 *  @param User3_ID user3
	 *  @param User4_ID user4
	 *  @param UserElement1_ID user element 1
	 *  @param UserElement2_ID user element 2
	 *  @return Account_ID for Unearned Revenue or Revenue Account if not found
	 */
	private int createRevenueRecognition (
		int C_RevenueRecognition_ID, int C_InvoiceLine_ID,
		int AD_Client_ID, int AD_Org_ID, int AD_User_ID, 
		int Account_ID, int C_SubAcct_ID,
		int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID,
		int C_LocFrom_ID, int C_LocTo_ID, int C_SRegion_ID, int C_Project_ID,
		int	C_Campaign_ID, int C_Activity_ID, 
		int User1_ID, int User2_ID, int User3_ID, int User4_ID, int UserElement1_ID, int UserElement2_ID)
	{
		log.fine("From Accout_ID=" + Account_ID);
		//  get VC for P_Revenue (from Product)
		MAccount revenue = MAccount.get(getCtx(),
			AD_Client_ID, AD_Org_ID, getC_AcctSchema_ID(), Account_ID, C_SubAcct_ID,
			M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SRegion_ID, 
			C_Project_ID, C_Campaign_ID, C_Activity_ID, 
			User1_ID, User2_ID , User3_ID, User4_ID, UserElement1_ID, UserElement2_ID, null);
		if (revenue != null && revenue.get_ID() == 0)
			revenue.saveEx();
		if (revenue == null || revenue.get_ID() == 0)
		{
			log.severe ("Revenue_Acct not found");
			return Account_ID;
		}
		int P_Revenue_Acct = revenue.get_ID();

		//  get Unearned Revenue Acct from BPartner Group
		int UnearnedRevenue_Acct = 0;
		int new_Account_ID = 0;
		String sql = "SELECT ga.UnearnedRevenue_Acct, vc.Account_ID "
			+ "FROM C_BP_Group_Acct ga, C_BPartner p, C_ValidCombination vc "
			+ "WHERE ga.C_BP_Group_ID=p.C_BP_Group_ID"
			+ " AND ga.UnearnedRevenue_Acct=vc.C_ValidCombination_ID"
			+ " AND ga.C_AcctSchema_ID=? AND p.C_BPartner_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_AcctSchema_ID());
			pstmt.setInt(2, C_BPartner_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				UnearnedRevenue_Acct = rs.getInt(1);
				new_Account_ID = rs.getInt(2);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (new_Account_ID == 0)
		{
			log.severe ("UnearnedRevenue_Acct not found");
			return Account_ID;
		}

		MRevenueRecognitionPlan plan = new MRevenueRecognitionPlan(getCtx(), 0, null);
		plan.setC_RevenueRecognition_ID (C_RevenueRecognition_ID);
		plan.setC_AcctSchema_ID (getC_AcctSchema_ID());
		plan.setC_InvoiceLine_ID (C_InvoiceLine_ID);
		plan.setUnEarnedRevenue_Acct (UnearnedRevenue_Acct);
		plan.setP_Revenue_Acct (P_Revenue_Acct);
		plan.setC_Currency_ID (getC_Currency_ID());
		plan.setTotalAmt (getAcctBalance());
		if (!plan.save(get_TrxName()))
		{
			log.severe ("Plan NOT created");
			return Account_ID;
		}
		log.fine("From Acctount_ID=" + Account_ID + " to " + new_Account_ID
			+ " - Plan from UnearnedRevenue_Acct=" + UnearnedRevenue_Acct + " to Revenue_Acct=" + P_Revenue_Acct);
		return new_Account_ID;
	}   //  createRevenueRecognition


	/**************************************************************************
	 * 	Update Line with reversed Original Amount in Accounting Currency.
	 * 	Also copies original dimensions like Project, etc.
	 * 	Called from Doc_MatchInv
	 * 	@param AD_Table_ID table
	 * 	@param Record_ID record
	 * 	@param Line_ID line
     * 	@param quantity Line
	 * 	@param multiplier targetQty/documentQty
	 * 	@return true if success
	 */
	public boolean updateReverseLine (
            int AD_Table_ID,
            int Record_ID,
            int Line_ID,
            BigDecimal quantity,
		    BigDecimal multiplier)
	{
		boolean success = false;

		String sql = "SELECT * "
			+ "FROM Fact_Acct "
			+ "WHERE C_AcctSchema_ID=? AND AD_Table_ID=? AND Record_ID=?"
			+ " AND Line_ID=? AND Account_ID=? AND Qty=?";
		// MZ Goodwill
		// for Inventory Move
		if (MMovement.Table_ID == AD_Table_ID)
			sql += " AND M_Locator_ID=?";
		// end MZ
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_AcctSchema_ID());
			pstmt.setInt(2, AD_Table_ID);
			pstmt.setInt(3, Record_ID);
			pstmt.setInt(4, Line_ID);
			pstmt.setInt(5, m_acct.getAccount_ID());
            pstmt.setBigDecimal(6, quantity.negate()); // Negate quantity to get the credit account fact
			// MZ Goodwill
			// for Inventory Move
			if (MMovement.Table_ID == AD_Table_ID)
				pstmt.setInt(7, getM_Locator_ID());
			// end MZ
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				MFactAcct fact = new MFactAcct(getCtx(), rs, get_TrxName());
				//  Accounted Amounts - reverse
				BigDecimal dr = fact.getAmtAcctDr();
				BigDecimal cr = fact.getAmtAcctCr();

				if (MMatchInv.Table_ID == getAD_Table_ID())
				{
					dr = fact.getAmtAcctCr();
					cr = fact.getAmtAcctDr();
				}

				// setAmtAcctDr (cr.multiply(multiplier));
				// setAmtAcctCr (dr.multiply(multiplier));
				setAmtAcct(fact.getC_Currency_ID(),  dr.multiply(multiplier) , cr.multiply(multiplier));
				//  
				//  Bayu Sistematika - Source Amounts
				//  Fixing source amounts
				BigDecimal drSourceAmt = fact.getAmtSourceDr();
				BigDecimal crSourceAmt = fact.getAmtSourceCr();

				if (MMatchInv.Table_ID == getAD_Table_ID())
				{
					drSourceAmt = fact.getAmtSourceCr();
					crSourceAmt = fact.getAmtSourceDr();
				}

				setAmtSource(fact.getC_Currency_ID(), drSourceAmt.multiply(multiplier) , crSourceAmt.multiply(multiplier));
				//  end Bayu Sistematika
				//
				success = true;
				log.fine(new StringBuffer("(Table=").append(AD_Table_ID)
					.append(",Record_ID=").append(Record_ID)
					.append(",Line=").append(Record_ID)
					.append(", Account=").append(m_acct)
					.append(",dr=").append(dr).append(",cr=").append(cr)
					.append(") - DR=").append(getAmtSourceDr()).append("|").append(getAmtAcctDr())
					.append(", CR=").append(getAmtSourceCr()).append("|").append(getAmtAcctCr())
					.toString());
				//	Dimensions
				setAD_OrgTrx_ID(fact.getAD_OrgTrx_ID());
				setC_Project_ID (fact.getC_Project_ID());
				setC_ProjectPhase_ID(fact.getC_ProjectPhase_ID());
				setC_ProjectTask_ID(fact.getC_ProjectTask_ID());
				setC_Activity_ID(fact.getC_Activity_ID());
				setC_Campaign_ID(fact.getC_Campaign_ID());
				setC_SalesRegion_ID(fact.getC_SalesRegion_ID());
				setC_LocFrom_ID(fact.getC_LocFrom_ID());
				setC_LocTo_ID(fact.getC_LocTo_ID());
				setM_Product_ID(fact.getM_Product_ID());
				setM_Locator_ID(fact.getM_Locator_ID());
				setUser1_ID(fact.getUser1_ID());
				setUser2_ID(fact.getUser2_ID());
				setUser3_ID(fact.getUser3_ID());
				setUser4_ID(fact.getUser4_ID());
				setC_UOM_ID(fact.getC_UOM_ID());
				setC_Tax_ID(fact.getC_Tax_ID());
				//	Org for cross charge
				setAD_Org_ID (fact.getAD_Org_ID());
			}
			else
				log.warning(new StringBuffer("Not Found (try later) ")
					.append(",C_AcctSchema_ID=").append(getC_AcctSchema_ID())
					.append(", AD_Table_ID=").append(AD_Table_ID)
					.append(",Record_ID=").append(Record_ID)
					.append(",Line_ID=").append(Line_ID)
					.append(", Account_ID=").append(m_acct.getAccount_ID()).toString());
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return success;
	}   //  updateReverseLine

}	//	FactLine

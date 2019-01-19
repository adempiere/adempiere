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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MDistribution;
import org.compiere.model.MDistributionLine;
import org.compiere.model.MElementValue;
import org.compiere.model.MFactAcct;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  Accounting Fact
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: Fact.java,v 1.2 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  BF [ 2789949 ] Multicurrency in matching posting
 */
public final class Fact
{
	/**
	 *	Constructor
	 *  @param  document    pointer to document
	 *  @param  acctSchema  Account Schema to create accounts
	 *  @param  defaultPostingType  the default Posting type (actual,..) for this posting
	 */
	public Fact (Doc document, MAcctSchema acctSchema, String defaultPostingType)
	{
		m_doc = document;
		m_acctSchema = acctSchema;
		m_postingType = defaultPostingType;
		// Fix [ 1884676 ] Fact not setting transaction
		m_trxName = document.getTrxName();
		//
		log.config(toString());
	}	//	Fact


	/**	Log					*/
	private CLogger			log = CLogger.getCLogger(getClass());

	/** Document            */
	private Doc             m_doc = null;
	/** Accounting Schema   */
	private MAcctSchema	    m_acctSchema = null;
	/** Transaction			*/
	private String m_trxName;

	/** Posting Type        */
	private String		    m_postingType = null;

	/** Actual Balance Type */
	public static final String	POST_Actual = MFactAcct.POSTINGTYPE_Actual;
	/** Budget Balance Type */
	public static final String	POST_Budget = MFactAcct.POSTINGTYPE_Budget;
	/** Encumbrance Posting */
	public static final String	POST_Commitment = MFactAcct.POSTINGTYPE_Commitment;
	/** Encumbrance Posting */
	public static final String	POST_Reservation = MFactAcct.POSTINGTYPE_Reservation;


	/** Is Converted        */
	private boolean		    m_converted = false;

	/** Lines               */
	private ArrayList<FactLine>	m_lines = new ArrayList<>();


	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_lines.clear();
		m_lines = null;
	}   //  dispose

	/**
	 *	Create and convert Fact Line.
	 *  Used to create a DR and/or CR entry
	 *
	 *	@param  docLine     the document line or null
	 *  @param  account     if null, line is not created
	 *  @param  C_Currency_ID   the currency
	 *  @param  debitAmt    debit amount, can be null
	 *  @param  creditAmt  credit amount, can be null
	 *  @return Fact Line
	 */
	public FactLine createLine (DocLine docLine, MAccount account,
		int C_Currency_ID, BigDecimal debitAmt, BigDecimal creditAmt)
	{
	//	log.fine("createLine - " + account	+ " - Dr=" + debitAmt + ", Cr=" + creditAmt);

		//  Data Check
		if (account == null)
		{
			log.info("No account for " + docLine 
				+ ": Amt=" + debitAmt + "/" + creditAmt 
				+ " - " + toString());			
			return null;
		}
		//
		FactLine line = new FactLine (m_doc.getCtx(), m_doc.get_Table_ID(), 
			m_doc.get_ID(),
			docLine == null ? 0 : docLine.get_ID(), m_trxName);
		//  Set Info & Account
		line.setDocumentInfo(m_doc, docLine);
		line.setPostingType(m_postingType);
		line.setAccount(m_acctSchema, account);

		//  Amounts - one needs to not zero
		if (!line.setAmtSource(C_Currency_ID, debitAmt, creditAmt))
		{
			if (docLine == null || docLine.getQty() == null || docLine.getQty().signum() == 0)
			{
				log.fine("Both amounts & qty = 0/Null - " + docLine		
					+ " - " + toString());			
				return null;
			}
			log.fine("Both amounts = 0/Null, Qty=" + docLine.getQty() + " - " + docLine		
				+ " - " + toString());			
		}
		//  Convert
		line.convert();
		//  Optionally overwrite Acct Amount
		if (docLine != null 
			&& (docLine.getAmtAcctDr() != null || docLine.getAmtAcctCr() != null))
			line.setAmtAcct(docLine.getAmtAcctDr(), docLine.getAmtAcctCr());
		//
		log.fine(line.toString());
		add(line);
		return line;
	}	//	createLine

	/**
	 *  Add Fact Line
	 *  @param line fact line
	 */
	public void add (FactLine line)
	{
		m_lines.add(line);
	}   //  add

	/**
	 *  Remove Fact Line
	 *  @param line fact line
	 */
	public void remove (FactLine line)
	{
		m_lines.remove(line);
	}   //  remove

	/**
	 *	Create and convert Fact Line.
	 *  Used to create either a DR or CR entry
	 *
	 *	@param  docLine     Document Line or null
	 *  @param  accountDr   Account to be used if Amt is DR balance
	 *  @param  accountCr   Account to be used if Amt is CR balance
	 *  @param  C_Currency_ID Currency
	 *  @param  Amt if negative Cr else Dr
	 *  @return FactLine
	 */
	public FactLine createLine (DocLine docLine, MAccount accountDr, MAccount accountCr,
		int C_Currency_ID, BigDecimal Amt)
	{
		if (Amt.signum() < 0)
			return createLine (docLine, accountCr, C_Currency_ID, null, Amt.abs());
		else
			return createLine (docLine, accountDr, C_Currency_ID, Amt, null);
	}   //  createLine

	/**
	 *	Create and convert Fact Line.
	 *  Used to create either a DR or CR entry
	 *
	 *	@param  docLine Document line or null
	 *  @param  account   Account to be used
	 *  @param  C_Currency_ID Currency
	 *  @param  Amt if negative Cr else Dr
	 *  @return FactLine
	 */
	public FactLine createLine (DocLine docLine, MAccount account,
		int C_Currency_ID, BigDecimal Amt)
	{
		if (Amt.signum() < 0)
			return createLine (docLine, account, C_Currency_ID, null, Amt.abs());
		else
			return createLine (docLine, account, C_Currency_ID, Amt, null);
	}   //  createLine

	/**
	 *  Is Posting Type
	 *  @param  PostingType - see POST_*
	 *  @return true if document is posting type
	 */
	public boolean isPostingType (String PostingType)
	{
		return m_postingType.equals(PostingType);
	}   //  isPostingType

	/**
	 *	Is converted
	 *  @return true if converted
	 */
	public boolean isConverted()
	{
		return m_converted;
	}	//	isConverted

	/**
	 *	Get AcctSchema
	 *  @return AcctSchema
	 */
	public MAcctSchema getAcctSchema()
	{
		return m_acctSchema;
	}	//	getAcctSchema

	
	/**************************************************************************
	 *	Are the lines Source Balanced
	 *  @return true if source lines balanced
	 */
	public boolean isSourceBalanced()
	{
		//AZ Goodwill
		//  Multi-Currency documents are source balanced by definition
		//  No lines -> balanced
		if (m_lines.size() == 0 || m_doc.isMultiCurrency())
			return true;
		BigDecimal balance = getSourceBalance();
		boolean retValue = balance.signum() == 0;
		if (retValue)
			log.finer(toString());
		else
			log.warning ("NO - Diff=" + balance + " - " + toString());
		return retValue;
	}	//	isSourceBalanced

	/**
	 *	Return Source Balance
	 *  @return source balance
	 */
	protected BigDecimal getSourceBalance()
	{
		BigDecimal result = Env.ZERO;
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine line = (FactLine)m_lines.get(i);
			result = result.add (line.getSourceBalance());
		}
	//	log.fine("getSourceBalance - " + result.toString());
		return result;
	}	//	getSourceBalance

	/**
	 *	Create Source Line for Suspense Balancing.
	 *  Only if Suspense Balancing is enabled and not a multi-currency document
	 *  (double check as otherwise the rule should not have fired)
	 *  If not balanced create balancing entry in currency of the document
	 *  @return FactLine
	 */
	public FactLine balanceSource()
	{
		if (!m_acctSchema.isSuspenseBalancing() || m_doc.isMultiCurrency())
			return null;
		BigDecimal diff = getSourceBalance();
		log.finer("Diff=" + diff);

		//  new line
		FactLine line = new FactLine (m_doc.getCtx(), m_doc.get_Table_ID(), 
			m_doc.get_ID(), 0, m_trxName);
		line.setDocumentInfo(m_doc, null);
		line.setPostingType(m_postingType);

		//	Account
		line.setAccount(m_acctSchema, m_acctSchema.getSuspenseBalancing_Acct());

		//  Amount
		if (diff.signum() < 0)   //  negative balance => DR
			line.setAmtSource(m_doc.getC_Currency_ID(), diff.abs(), Env.ZERO);
		else                                //  positive balance => CR
			line.setAmtSource(m_doc.getC_Currency_ID(), Env.ZERO, diff);
			
		//  Convert
		line.convert();
		//
		log.fine(line.toString());
		m_lines.add(line);
		return line;
	}   //  balancingSource

	
	/**************************************************************************
	 *  Are all segments balanced
	 *  @return true if segments are balanced
	 */
	public boolean isSegmentBalanced()
	{
		//AZ Goodwill
		//  Multi-Currency documents are source balanced by definition
		//  No lines -> balanced
		if (m_lines.size() == 0 || m_doc.isMultiCurrency())
			return true;

		MAcctSchemaElement[] elements = m_acctSchema.getAcctSchemaElements();
		//  check all balancing segments
		for (int i = 0; i < elements.length; i++)
		{
			MAcctSchemaElement ase = elements[i];
			if (ase.isBalanced() && !isSegmentBalanced (ase.getElementType()))
				return false;
		}
		return true;
	}   //  isSegmentBalanced

	/**
	 *  Is Source Segment balanced.
	 *  @param  segmentType - see AcctSchemaElement.SEGMENT_*
	 *  Implemented only for Org
	 *  Other sensible candidates are Project, User1/2
	 *  @return true if segments are balanced
	 */
	public boolean isSegmentBalanced (String segmentType)
	{
		if (segmentType.equals(MAcctSchemaElement.ELEMENTTYPE_Organization))
		{
			HashMap<Integer,BigDecimal> map = new HashMap<Integer,BigDecimal>();
			//  Add up values by key
			for (int i = 0; i < m_lines.size(); i++)
			{
				FactLine line = (FactLine)m_lines.get(i);
				Integer key = new Integer(line.getAD_Org_ID());
				BigDecimal bal = line.getSourceBalance();
				BigDecimal oldBal = (BigDecimal)map.get(key);
				if (oldBal != null)
					bal = bal.add(oldBal);
				map.put(key, bal);
			//	System.out.println("Add Key=" + key + ", Bal=" + bal + " <- " + line);
			}
			//  check if all keys are zero
			Iterator<BigDecimal> values = map.values().iterator();
			while (values.hasNext())
			{
				BigDecimal bal = values.next();
				if (bal.signum() != 0)
				{
					map.clear();
					log.warning ("(" + segmentType + ") NO - " + toString() + ", Balance=" + bal);
					return false;
				}
			}
			map.clear();
			log.finer("(" + segmentType + ") - " + toString());
			return true;
		}
		log.finer("(" + segmentType + ") (not checked) - " + toString());
		return true;
	}   //  isSegmentBalanced

	/**
	 *  Balance all segments.
	 *  - For all balancing segments
	 *      - For all segment values
	 *          - If balance <> 0 create dueTo/dueFrom line
	 *              overwriting the segment value
	 */
	public void balanceSegments()
	{
		MAcctSchemaElement[] elements = m_acctSchema.getAcctSchemaElements();
		//  check all balancing segments
		for (int i = 0; i < elements.length; i++)
		{
			MAcctSchemaElement ase = elements[i];
			if (ase.isBalanced())
				balanceSegment (ase.getElementType());
		}
	}   //  balanceSegments

	/**
	 *  Balance Source Segment
	 *  @param elementType segment element type
	 */
	private void balanceSegment (String elementType)
	{
		//  no lines -> balanced
		if (m_lines.size() == 0)
			return;

		log.fine ("(" + elementType + ") - " + toString());

		//  Org
		if (elementType.equals(MAcctSchemaElement.ELEMENTTYPE_Organization))
		{
			HashMap<Integer,Balance> map = new HashMap<Integer,Balance>();
			//  Add up values by key
			for (int i = 0; i < m_lines.size(); i++)
			{
				FactLine line = (FactLine)m_lines.get(i);
				Integer key = new Integer(line.getAD_Org_ID());
			//	BigDecimal balance = line.getSourceBalance();
				Balance oldBalance = (Balance)map.get(key);
				if (oldBalance == null)
				{
					oldBalance = new Balance (line.getAmtSourceDr(), line.getAmtSourceCr());
					map.put(key, oldBalance);
				}
				else
					oldBalance.add(line.getAmtSourceDr(), line.getAmtSourceCr());
			//	log.info ("Key=" + key + ", Balance=" + balance + " - " + line);
			}

			//  Create entry for non-zero element
			Iterator<Integer> keys = map.keySet().iterator();
			while (keys.hasNext())
			{
				Integer key = keys.next();
				Balance difference = map.get(key);
				log.info (elementType + "=" + key + ", " + difference);
				//
				if (!difference.isZeroBalance())
				{
					//  Create Balancing Entry
					FactLine line = new FactLine (m_doc.getCtx(), m_doc.get_Table_ID(), 
						m_doc.get_ID(), 0, m_trxName);
					line.setDocumentInfo(m_doc, null);
					line.setPostingType(m_postingType);
					//  Amount & Account
					if (difference.getBalance().signum() < 0)
					{
						if (difference.isReversal())
						{
							line.setAccount(m_acctSchema, m_acctSchema.getDueTo_Acct(elementType));
							line.setAmtSource(m_doc.getC_Currency_ID(), Env.ZERO, difference.getPostBalance());
						}
						else
						{
							line.setAccount(m_acctSchema, m_acctSchema.getDueFrom_Acct(elementType));
							line.setAmtSource(m_doc.getC_Currency_ID(), difference.getPostBalance(), Env.ZERO);
						}
					}
					else
					{
						if (difference.isReversal())
						{
							line.setAccount(m_acctSchema, m_acctSchema.getDueFrom_Acct(elementType));
							line.setAmtSource(m_doc.getC_Currency_ID(), difference.getPostBalance(), Env.ZERO);
						}
						else
						{
							line.setAccount(m_acctSchema, m_acctSchema.getDueTo_Acct(elementType));
							line.setAmtSource(m_doc.getC_Currency_ID(), Env.ZERO, difference.getPostBalance());
						}
					}
					line.convert();
					line.setAD_Org_ID(key.intValue());
					//
					m_lines.add(line);
					log.fine("(" + elementType + ") - " + line);
				}
			}
			map.clear();
		}
	}   //  balanceSegment

	
	/**************************************************************************
	 *	Are the lines Accounting Balanced
	 *  @return true if accounting lines are balanced
	 */
	public boolean isAcctBalanced()
	{
		//  no lines -> balanced
		if (m_lines.size() == 0)
			return true;
		BigDecimal balance = getAcctBalance();
		boolean retValue = balance.signum() == 0;
		if (retValue)
			log.finer(toString());
		else
			log.warning("NO - Diff=" + balance + " - " + toString());
		return retValue;
	}	//	isAcctBalanced

	/**
	 *	Return Accounting Balance
	 *  @return true if accounting lines are balanced
	 */
	protected BigDecimal getAcctBalance()
	{
		BigDecimal result = Env.ZERO;
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine line = (FactLine)m_lines.get(i);
			result = result.add(line.getAcctBalance());
		}
	//	log.fine(result.toString());
		return result;
	}	//	getAcctBalance

	/**
	 *  Balance Accounting Currency.
	 *  If the accounting currency is not balanced,
	 *      if Currency balancing is enabled
	 *          create a new line using the currency balancing account with zero source balance
	 *      or
	 *          adjust the line with the largest balance sheet account
	 *          or if no balance sheet account exist, the line with the largest amount
	 *  @return FactLine
	 */
	public FactLine balanceAccounting()
	{
		BigDecimal diff = getAcctBalance();		//	DR-CR
		log.fine("Balance=" + diff 
			+ ", CurrBal=" + m_acctSchema.isCurrencyBalancing() 
			+ " - " + toString());
		FactLine line = null;

		BigDecimal BSamount = Env.ZERO;
		FactLine BSline = null;
		BigDecimal PLamount = Env.ZERO;
		FactLine PLline = null;

		//  Find line biggest BalanceSheet or P&L line
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine l = (FactLine)m_lines.get(i);
			BigDecimal amt = l.getAcctBalance().abs();
			if (l.isBalanceSheet() && amt.compareTo(BSamount) > 0)
			{
				BSamount = amt;
				BSline = l;
			}
			else if (!l.isBalanceSheet() && amt.compareTo(PLamount) > 0)
			{
				PLamount = amt;
				PLline = l;
			}
		}
		
		//  Create Currency Balancing Entry
		if (m_acctSchema.isCurrencyBalancing())
		{
			line = new FactLine (m_doc.getCtx(), m_doc.get_Table_ID(), 
				m_doc.get_ID(), 0, m_trxName);
			line.setDocumentInfo (m_doc, null);
			line.setPostingType (m_postingType);
			line.setAccount (m_acctSchema, m_acctSchema.getCurrencyBalancing_Acct());
			
			//  Amount
			line.setAmtSource(m_doc.getC_Currency_ID(), Env.ZERO, Env.ZERO);
			line.convert();
			//	Accounted
			BigDecimal drAmt = Env.ZERO;
			BigDecimal crAmt = Env.ZERO;
			boolean isDR = diff.signum() < 0;
			BigDecimal difference = diff.abs();
			if (isDR)
				drAmt = difference;
			else
				crAmt = difference;
			//	Switch sides
			boolean switchIt = BSline != null 
				&& ((BSline.isDrSourceBalance() && isDR)
					|| (!BSline.isDrSourceBalance() && !isDR));
			if (switchIt)
			{
				drAmt = Env.ZERO;
				crAmt = Env.ZERO;
				if (isDR)
					crAmt = difference.negate();
				else
					drAmt = difference.negate();
			}
			line.setAmtAcct(drAmt, crAmt);
			log.fine(line.toString());
			m_lines.add(line);
		}
		else	//  Adjust biggest (Balance Sheet) line amount
		{
			if (BSline != null)
				line = BSline;
			else
				line = PLline;
			if (line == null)
				log.severe ("No Line found");
			else
			{
				log.fine("Adjusting Amt=" + diff + "; Line=" + line);
				line.currencyCorrect(diff);
				log.fine(line.toString());
			}
		}   //  correct biggest amount

		return line;
	}   //  balanceAccounting

	/**
	 * 	Check Accounts of Fact Lines
	 *	@return true if success
	 */
	public boolean checkAccounts()
	{
		//  no lines -> nothing to distribute
		if (m_lines.size() == 0)
			return true;
		
		//	For all fact lines
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine line = (FactLine)m_lines.get(i);
			MAccount account = line.getAccount();
			if (account == null)
			{
				log.warning("No Account for " + line);
				return false;
			}
			MElementValue ev = account.getAccount();
			if (ev == null)
			{
				log.warning("No Element Value for " + account 
					+ ": " + line);
				m_doc.p_Error = account.toString();
				return false;
			}
			if (ev.isSummary())
			{
				log.warning("Cannot post to Summary Account " + ev 
					+ ": " + line);
				m_doc.p_Error = ev.toString();
				return false;
			}
			if (!ev.isActive())
			{
				log.warning("Cannot post to Inactive Account " + ev 
					+ ": " + line);
				m_doc.p_Error = ev.toString();
				return false;
			}

		}	//	for all lines
		
		return true;
	}	//	checkAccounts
	
	/**
	 * 	GL Distribution of Fact Lines
	 *	@return true if success
	 */
	public boolean distribute()
	{
		//  no lines -> nothing to distribute
		if (m_lines.size() == 0)
			return true;
		
		ArrayList<FactLine> newLines = new ArrayList<FactLine>();
		//	For all fact lines
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine factLineSource = (FactLine)m_lines.get(i);
			List<MDistribution> distributions = MDistribution.get (factLineSource.getAccount(),
				m_postingType, m_doc.getC_DocType_ID(),factLineSource.getDateAcct());
			//	No Distribution for this line
			//AZ Goodwill
			//The above "get" only work in GL Journal because it's using ValidCombination Account
			//Old:
			//if (distributions == null || distributions.length == 0)
			//	continue;
			//For other document, we try the followings (from FactLine):
			//New:	
			if (distributions == null || distributions.size() == 0)
			{
				distributions = MDistribution.get (factLineSource.getCtx(), factLineSource.getC_AcctSchema_ID(),
					m_postingType, m_doc.getC_DocType_ID(),
					factLineSource.getAD_Org_ID(), factLineSource.getAccount_ID(),
					factLineSource.getM_Product_ID(), factLineSource.getC_BPartner_ID(), factLineSource.getC_Project_ID(),
					factLineSource.getC_Campaign_ID(), factLineSource.getC_Activity_ID(), factLineSource.getAD_OrgTrx_ID(),
					factLineSource.getC_SalesRegion_ID(), factLineSource.getC_LocTo_ID(), factLineSource.getC_LocFrom_ID(),
					factLineSource.getUser1_ID(), factLineSource.getUser2_ID(),
					factLineSource.getUser3_ID(), factLineSource.getUser4_ID() ,
					factLineSource.getDateAcct());
				if (distributions == null || distributions.size() == 0)
					continue;
			}
			//end AZ
			//	Just the first
			if (distributions.size() > 1){
				log.warning("More then one Distributiion for " + factLineSource.getAccount());
				//throw new AdempiereException("More then one Distribution for " + dLine.getAccount());
			}
			
			MDistribution distribution = distributions.get(0);
			List<MDistributionLine> distributionLines = distribution.getLines(false);

			if(distribution.getPercentTotal().signum() != 0)
			{
			// FR 2685367 - GL Distribution delete line instead reverse
				if (distribution.isCreateReversal() ){
					//	Add Reversal				
					FactLine reversal = factLineSource.reverse(distribution.getName());
					log.info("Reversal=" + reversal);
					newLines.add(reversal);		//	saved in postCommit
				
				} else  {
					MDistributionLine distributionLine = distributionLines.stream().findFirst().get();
					if (distributionLine.isOverwritePostingType()
							&& distributionLine.getPostingType() != null
							&& (distribution.getPostingType() != null && distributionLine.getPostingType() != null
							&& !distribution.getPostingType().equals(distributionLine.getPostingType()))
							&& distributionLine.getPercent().doubleValue() == 0)
						;
					else {
						// delete the line being distributed
						m_lines.remove(i);    // or it could be m_lines.remove(dLine);
						i--;
					}
				}		
			
			}
				
			//	Prepare
			distribution.distribute(factLineSource.getAccount(), factLineSource.getSourceBalance(), factLineSource.getQty(), factLineSource.getC_Currency_ID(),m_doc.getAmount().signum());
			for (MDistributionLine distributionLine : distributionLines)
			{
				FactLine factLine = new FactLine (m_doc.getCtx(), m_doc.get_Table_ID(), m_doc.get_ID(), 0 , m_trxName);
				//  Set Info & Account
				factLine.setDocumentInfo(m_doc, factLineSource.getDocLine());
				factLine.setAccount(m_acctSchema, distributionLine.getAccount());
				if (factLine.getLine_ID() <= 0 && factLineSource.getLine_ID() > 0)
					factLine.setLine_ID(factLineSource.getLine_ID());
				if (factLine.getAD_OrgTrx_ID() <= 0 && factLineSource.getAD_OrgTrx_ID() > 0)
					factLine.setAD_OrgTrx_ID(factLineSource.getAD_OrgTrx_ID());
				if (factLine.getC_Campaign_ID() <= 0 && factLineSource.getC_Campaign_ID() > 0)
					factLine.setC_Campaign_ID(factLineSource.getC_Campaign_ID());
				if (factLine.getC_Project_ID() <= 0 && factLineSource.getC_Project_ID() > 0)
					factLine.setC_Project_ID(factLineSource.getC_Project_ID());
				if (factLine.getC_ProjectPhase_ID() <= 0 && factLineSource.getC_ProjectPhase_ID() > 0)
					factLine.setC_ProjectPhase_ID(factLineSource.getC_ProjectPhase_ID());
				if (factLine.getC_ProjectTask_ID() <= 0 && factLineSource.getC_ProjectTask_ID() > 0)
					factLine.setC_ProjectTask_ID(factLineSource.getC_ProjectTask_ID());
				if (factLine.getC_Activity_ID() <= 0 && factLineSource.getC_Activity_ID() > 0)
					factLine.setC_Activity_ID(factLineSource.getC_Activity_ID());
				if (factLine.getC_SalesRegion_ID() <= 0 && factLineSource.getC_SalesRegion_ID() > 0)
					factLine.setC_SalesRegion_ID(factLineSource.getC_SalesRegion_ID());
				if (factLine.getUser1_ID() <= 0 && factLineSource.getUser1_ID() > 0 )
					factLine.setUser1_ID(factLineSource.getUser1_ID());
				if (factLine.getUser2_ID() <= 0 && factLineSource.getUser2_ID() > 0 )
					factLine.setUser2_ID(factLineSource.getUser2_ID());
				if (factLine.getUser3_ID() <= 0 && factLineSource.getUser3_ID() > 0)
					factLine.setUser3_ID(factLineSource.getUser3_ID());
				if (factLine.getUser4_ID() <= 0 && factLineSource.getUser4_ID() > 0)
					factLine.setUser4_ID(factLineSource.getUser4_ID());
				if (factLine.getUserElement1_ID() <= 0 && factLineSource.getUserElement1_ID() > 0)
					factLine.setUserElement1_ID(factLineSource.getUserElement1_ID());
				if (factLine.getUserElement2_ID() <= 0 && factLineSource.getUserElement2_ID() > 0)
					factLine.setUserElement2_ID(factLineSource.getUserElement2_ID());
				if (factLine.getC_LocFrom_ID() <= 0 && factLineSource.getC_LocFrom_ID() > 0)
					factLine.setC_LocFrom_ID(factLineSource.getC_LocFrom_ID());
				if (factLine.getC_LocTo_ID() <= 0 && factLineSource.getC_LocTo_ID() > 0)
					factLine.setC_LocTo_ID(factLineSource.getC_LocTo_ID());

				factLine.setPostingType(m_postingType);
				if (distributionLine.isOverwritePostingType()
						&& distributionLine.getPostingType() != null
						&& !distribution.getPostingType().equals(distributionLine.getPostingType())
						&& distributionLine.getPercent().doubleValue() == 0)
					factLine.setPostingType(distributionLine.getPostingType());
				if (distributionLine.isOverwriteOrg())	//	set Org explicitly
					factLine.setAD_Org_ID(distributionLine.getOrg_ID());
				// Silvano - freepath - F3P - Bug#2904994 Fact distribtution only overwriting Org
				if(distributionLine.isOverwriteAcct())
					factLine.setAccount_ID(distributionLine.getAccount_ID());
				if(distributionLine.isOverwriteActivity())
					factLine.setC_Activity_ID(distributionLine.getC_Activity_ID());
				if(distributionLine.isOverwriteBPartner())
					factLine.setC_BPartner_ID(distributionLine.getC_BPartner_ID());
				if(distributionLine.isOverwriteCampaign())
					factLine.setC_Campaign_ID(distributionLine.getC_Campaign_ID());
				if(distributionLine.isOverwriteLocFrom())
					factLine.setC_LocFrom_ID(distributionLine.getC_LocFrom_ID());
				if(distributionLine.isOverwriteLocTo())
					factLine.setC_LocTo_ID(distributionLine.getC_LocTo_ID());
				if(distributionLine.isOverwriteOrgTrx())
					factLine.setAD_OrgTrx_ID(distributionLine.getAD_OrgTrx_ID());
				if(distributionLine.isOverwriteProduct())
					factLine.setM_Product_ID(distributionLine.getM_Product_ID());
				if(distributionLine.isOverwriteProject())
					factLine.setC_Project_ID(distributionLine.getC_Project_ID());
				if(distributionLine.isOverwriteSalesRegion())
					factLine.setC_SalesRegion_ID(distributionLine.getC_SalesRegion_ID());
				if(distributionLine.isOverwriteUser1())
					factLine.setUser1_ID(distributionLine.getUser1_ID());
				if(distributionLine.isOverwriteUser2())
					factLine.setUser2_ID(distributionLine.getUser2_ID());
				if(distributionLine.isOverwriteUser3())
					factLine.setUser3_ID(distributionLine.getUser3_ID());
				if(distributionLine.isOverwriteUser4())
					factLine.setUser4_ID(distributionLine.getUser4_ID());
				// F3P end

				if (distributionLine.isInvertAccountSign()) {
					if (distributionLine.getAmt().signum() < 0)
						factLine.setAmtSource(factLineSource.getC_Currency_ID(), null, distributionLine.getAmt().abs());
					else
						factLine.setAmtSource(factLineSource.getC_Currency_ID(), distributionLine.getAmt(), null);
				}
				else
				{
					if (distributionLine.getAmt().signum() < 0)
						factLine.setAmtSource(factLineSource.getC_Currency_ID(), null, distributionLine.getAmt().abs());
					else
						factLine.setAmtSource(factLineSource.getC_Currency_ID(), distributionLine.getAmt(), null);
				}

				factLine.setQty(distributionLine.getQty());
				//  Convert
				factLine.convert();
				//
				String description = distribution.getName() + " #" + distributionLine.getLine();
				if (distributionLine.getDescription() != null)
					description += " - " + distributionLine.getDescription();
				factLine.addDescription(description);
				//
				log.info(factLine.toString());
				newLines.add(factLine);
			}
		}	//	for all lines
		
		//	Add Lines
		for (int i = 0; i < newLines.size(); i++)
			m_lines.add(newLines.get(i));
		
		return true;
	}	//	distribute	
	
	/**************************************************************************
	 * String representation
	 * @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("Fact[");
		sb.append(m_doc.toString());
		sb.append(",").append(m_acctSchema.toString());
		sb.append(",PostType=").append(m_postingType);
		sb.append("]");
		return sb.toString();
	}	//	toString

	/**
	 *	Get Lines
	 *  @return FactLine Array
	 */
	public FactLine[] getLines()
	{
		FactLine[] temp = new FactLine[m_lines.size()];
		m_lines.toArray(temp);
		return temp;
	}	//	getLines

	/**
	 *  Save Fact
	 *  @param trxName transaction
	 *  @return true if all lines were saved
	 */
	public boolean save (String trxName)
	{
		m_trxName = trxName;
		//  save Lines
		for (int i = 0; i < m_lines.size(); i++)
		{
			FactLine fl = (FactLine)m_lines.get(i);
		//	log.fine("save - " + fl);
			if (!fl.save(trxName))  //  abort on first error
				return false;
		}
		return true;
	}   //  commit

	/**
	 * 	Get Transaction
	 *	@return trx
	 */
	public String get_TrxName() 
	{
		return m_trxName;
	}	//	getTrxName

	/**
	 * 	Set Transaction name
	 * 	@param trxName
	 */
	@SuppressWarnings("unused")
	private void set_TrxName(String trxName) 
	{
		m_trxName = trxName;
	}	//	set_TrxName

	/**
	 * 	Fact Balance Utility
	 *	
	 *  @author Jorg Janke
	 *  @version $Id: Fact.java,v 1.2 2006/07/30 00:53:33 jjanke Exp $
	 */
	public class Balance
	{
		/**
		 * 	New Balance
		 *	@param dr DR
		 *	@param cr CR
		 */
		public Balance (BigDecimal dr, BigDecimal cr)
		{
			DR = dr;
			CR = cr;
		}
		
		/** DR Amount	*/
		public BigDecimal DR = Env.ZERO;
		/** CR Amount	*/
		public BigDecimal CR = Env.ZERO;
		
		/**
		 * 	Add 
		 *	@param dr DR
		 *	@param cr CR
		 */
		public void add (BigDecimal dr, BigDecimal cr)
		{
			DR = DR.add(dr);
			CR = CR.add(cr);
		}
		
		/**
		 * 	Get Balance
		 *	@return balance
		 */
		public BigDecimal getBalance()
		{
			return DR.subtract(CR);
		}	//	getBalance
		
		/**
		 * 	Get Post Balance
		 *	@return absolute balance - negative if reversal
		 */
		public BigDecimal getPostBalance()
		{
			BigDecimal bd = getBalance().abs();
			if (isReversal())
				return bd.negate();
			return bd;
		}	//	getPostBalance

		/**
		 * 	Zero Balance
		 *	@return true if 0
		 */
		public boolean isZeroBalance()
		{
			return getBalance().signum() == 0;
		}	//	isZeroBalance
		
		/**
		 * 	Reversal
		 *	@return true if both DR/CR are negative or zero
		 */
		public boolean isReversal()
		{
			return DR.signum() <= 0 && CR.signum() <= 0;
		}	//	isReversal
		
		/**
		 * 	String Representation
		 *	@return info
		 */
		public String toString ()
		{
			StringBuffer sb = new StringBuffer ("Balance[");
			sb.append ("DR=").append(DR)
				.append ("-CR=").append(CR)
				.append(" = ").append(getBalance())
				.append ("]");
			return sb.toString ();
		} //	toString
		
	}	//	Balance

	/**
	 * get Document
	 * @return
	 */
	public Doc getDocument()
	{
		return m_doc;
	}
	
}   //  Fact
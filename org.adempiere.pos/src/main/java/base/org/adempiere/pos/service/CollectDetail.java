/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.pos.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.ValueNamePair;

/**
 * 
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Aug 24, 2015, 8:57:21 PM
 *
 */
public class CollectDetail {
	
	/**
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_TenderType
	 * @param m_PayAmt
	 * @param m_ReferenceNo
	 * @param m_C_Bank_ID
	 * @param m_DateTrx
	 * @param m_CreditCardExpMM
	 * @param m_CreditCardExpYY
	 * @param m_CreditCardNumber
	 * @param m_CreditCardType
	 * @param m_CreditCardVV
	 * @param m_A_Name
	 * @param m_A_City
	 * @param m_A_State
	 * @param m_A_Street
	 * @param m_A_Zip
	 * @param m_A_Country
	 * @param m_A_EMail
	 * @param m_A_Ident_DL
	 * @param m_A_Ident_SSN
	 * @param m_RoutingNo
	 */
	public CollectDetail(String m_TenderType, BigDecimal m_PayAmt,
			String m_ReferenceNo, int m_C_Bank_ID, Timestamp m_DateTrx,
			String m_CreditCardExpMM, String m_CreditCardExpYY,
			String m_CreditCardNumber, String m_CreditCardType,
			String m_CreditCardVV, String m_A_Name, String m_A_City,
			String m_A_State, String m_A_Street, String m_A_Zip,
			String m_A_Country, String m_A_EMail, String m_A_Ident_DL,
			String m_A_Ident_SSN, String m_RoutingNo) {
		this.m_TenderType = m_TenderType;
		this.m_PayAmt = m_PayAmt;
		this.m_ReferenceNo = m_ReferenceNo;
		this.m_C_Bank_ID = m_C_Bank_ID;
		this.m_DateTrx = m_DateTrx;
		this.m_CreditCardExpMM = m_CreditCardExpMM;
		this.m_CreditCardExpYY = m_CreditCardExpYY;
		this.m_CreditCardNumber = m_CreditCardNumber;
		this.m_CreditCardType = m_CreditCardType;
		this.m_CreditCardVV = m_CreditCardVV;
		this.m_A_Name = m_A_Name;
		this.m_A_City = m_A_City;
		this.m_A_State = m_A_State;
		this.m_A_Street = m_A_Street;
		this.m_A_Zip = m_A_Zip;
		this.m_A_Country = m_A_Country;
		this.m_A_EMail = m_A_EMail;
		this.m_A_Ident_DL = m_A_Ident_DL;
		this.m_A_Ident_SSN = m_A_Ident_SSN;
		this.m_RoutingNo = m_RoutingNo;
	}
	
	/**
	 * Create Collect from Credit Card Data
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_PayAmt
	 * @param m_CreditCardExpMM
	 * @param m_CreditCardExpYY
	 * @param m_CreditCardNumber
	 * @param m_CreditCardType
	 * @param m_CreditCardVV
	 * @param m_A_Name
	 * @param m_A_City
	 * @param m_A_State
	 * @param m_A_Street
	 * @param m_A_Zip
	 * @param m_A_Country
	 * @param m_A_EMail
	 * @param m_A_Ident_DL
	 * @param m_A_Ident_SSN
	 * @param m_RoutingNo
	 * @return
	 * @return CollectType
	 */
	public static CollectDetail createCreditCard(BigDecimal m_PayAmt,
			String m_CreditCardExpMM, String m_CreditCardExpYY,
			String m_CreditCardNumber, String m_CreditCardType,
			String m_CreditCardVV, String m_A_Name, String m_A_City,
			String m_A_State, String m_A_Street, String m_A_Zip,
			String m_A_Country, String m_A_EMail, String m_A_Ident_DL,
			String m_A_Ident_SSN, String m_RoutingNo) {
		return new CollectDetail(X_C_Payment.TENDERTYPE_CreditCard, m_PayAmt, null, 0, null,
				m_CreditCardExpMM, m_CreditCardExpYY, m_CreditCardNumber, m_CreditCardType, 
				m_CreditCardVV, m_A_Name, m_A_City, m_A_State, m_A_Street, m_A_Zip, m_A_Country, 
				m_A_EMail, m_A_Ident_DL, m_A_Ident_SSN, m_RoutingNo);
	}
	
	/**
	 * Create Check
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_PayAmt
	 * @param m_CheckNo
	 * @param m_C_Bank_ID
	 * @param m_DateTrx
	 * @return
	 * @return CollectType
	 */
	public static CollectDetail createCheck(BigDecimal m_PayAmt, String m_CheckNo, int m_C_Bank_ID, Timestamp m_DateTrx) {
		return new CollectDetail(X_C_Payment.TENDERTYPE_Check, m_PayAmt, m_CheckNo, m_C_Bank_ID, m_DateTrx);
	}
	
	/**
	 * Create Cash
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_PayAmt
	 * @return
	 * @return CollectType
	 */
	public static CollectDetail createCash(BigDecimal m_PayAmt) {
		return new CollectDetail(X_C_Payment.TENDERTYPE_Cash, m_PayAmt);
	}
	
	/**
	 * Private Cash Constructor
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_TenderType
	 * @param m_PayAmt
	 */
	protected CollectDetail(String m_TenderType, BigDecimal m_PayAmt) {
		this.m_TenderType = m_TenderType;
		this.m_PayAmt = m_PayAmt;
		setInitPayAmt(m_PayAmt);
		m_CreditCardType = X_C_Payment.CREDITCARDTYPE_MasterCard;
	}
	
	/**
	 * Private Constructor for Check
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_TenderType
	 * @param m_PayAmt
	 * @param m_CheckNo
	 * @param m_C_Bank_ID
	 * @param m_DateTrx
	 */
	private CollectDetail(String m_TenderType, BigDecimal m_PayAmt, String m_CheckNo, int m_C_Bank_ID, Timestamp m_DateTrx) {
		this.m_TenderType = m_TenderType;
		this.m_PayAmt = m_PayAmt;
		this.m_ReferenceNo = m_CheckNo;
		this.m_C_Bank_ID = m_C_Bank_ID;
		this.m_DateTrx = m_DateTrx;
	}
	
	/**	Tender Type						*/
	private String 		m_TenderType;
	/**	Payment Amount					*/
	private BigDecimal	m_PayAmt;
	/**	Initial Payment Amount					*/
	private BigDecimal	m_InitPayAmt;
	/**	Reference No					*/
	private String 		m_ReferenceNo;
	/**	Bank							*/
	private int 		m_C_Bank_ID;
	/**	Payment Date					*/
	private Timestamp	m_DateTrx;
	/**	Credit Card						*/
	/**	Credit Card	Expedition Month	*/
	private String		m_CreditCardExpMM;
	/**	Credit Card Expedition Year		*/
	private String 		m_CreditCardExpYY;
	/**	Credit Card Number				*/
	private String 		m_CreditCardNumber;
	/**	Credit Card Type				*/
	private String		m_CreditCardType;
	/**	Credit Card Verif. Code			*/
	private String		m_CreditCardVV;
	/**	Credit Card Name				*/
	private String		m_A_Name;
	/**	Credit Card City				*/
	private String 		m_A_City;
	/**	Credit Card State				*/
	private String 		m_A_State;
	/**	Credit Card Street				*/
	private String 		m_A_Street;
	/**	Credit Card Zip Code			*/
	private String 		m_A_Zip;
	/**	Country							*/
	private String		m_A_Country;
	/**	Credit Card EMail				*/
	private String		m_A_EMail;
	/**	Driver License					*/
	private String 		m_A_Ident_DL;
	/**	Security Social Code			*/
	private String 		m_A_Ident_SSN;
	/**	Bank Routing No					*/
	private String		m_RoutingNo;
	/**	Invoice Credit Note				*/
	private MInvoice	m_CreditMemo;
	/**	ID Credit Invoice				*/
	private int			m_C_Invoice_ID;
	
	/**
	 * Get Months for Credit Card
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return ValueNamePair[]
	 */
	protected ValueNamePair[] getCCMonths() {
		//	Populate Month
		ArrayList<ValueNamePair> m_Months = new ArrayList<ValueNamePair>();
		Locale locale = Language.getLoginLanguage().getLocale();
		Calendar cal = Calendar.getInstance();
		for(int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {
			cal.set(1, i, 1);
			String displayName = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale);
			String value = String.format("%02d", i + 1);
			m_Months.add(new ValueNamePair(value, displayName + "-" + value));
		}
		//	Convert to Array
		ValueNamePair[] values = new ValueNamePair[m_Months.size()];
		m_Months.toArray(values);
		//	Return Values
		return values;
	}
	
	/**
	 * Get Year for Credit Card
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return ValueNamePair[]
	 */
	protected ValueNamePair[] getCCYears() {
		//	Populate Month
		ArrayList<ValueNamePair> m_Years = new ArrayList<ValueNamePair>();
		Locale locale = Language.getLoginLanguage().getLocale();
		Calendar cal = Calendar.getInstance(locale);
		for(int i = 0; i < 10; i++) {
			//	Seek in new year
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String shortYear = year.substring(2);
			m_Years.add(new ValueNamePair(shortYear, year));
			cal.add(Calendar.YEAR, 1);
		}
		//	Convert to Array
		ValueNamePair[] values = new ValueNamePair[m_Years.size()];
		m_Years.toArray(values);
		//	Return Values
		return values;
	}
	
	
	/**
	 * @return the m_TenderType
	 */
	public String getTenderType() {
		return m_TenderType;
	}

	/**
	 * @return the m_PayAmt
	 */
	public BigDecimal getPayAmt() {
		if(m_PayAmt == null) {
			return Env.ZERO;
		}
		//	Default Return
		return m_PayAmt;
	}

	/**
	 * @return the m_ReferenceNo
	 */
	public String getReferenceNo() {
		return m_ReferenceNo;
	}

	/**
	 * @return the m_C_Bank_ID
	 */
	public int getC_Bank_ID() {
		return m_C_Bank_ID;
	}

	/**
	 * @return the m_DateTrx
	 */
	public Timestamp getDateTrx() {
		return m_DateTrx;
	}

	/**
	 * @return the m_CreditCardExpMM
	 */
	public String getCreditCardExpMM() {
		return m_CreditCardExpMM;
	}

	/**
	 * @return the m_CreditCardExpYY
	 */
	public String getCreditCardExpYY() {
		return m_CreditCardExpYY;
	}

	/**
	 * @return the m_CreditCardNumber
	 */
	public String getCreditCardNumber() {
		return m_CreditCardNumber;
	}

	/**
	 * @return the m_CreditCardType
	 */
	public String getCreditCardType() {
		return m_CreditCardType;
	}

	/**
	 * @return the m_CreditCardVV
	 */
	public String getCreditCardVV() {
		return m_CreditCardVV;
	}

	/**
	 * @return the m_A_Name
	 */
	public String getA_Name() {
		return m_A_Name;
	}

	/**
	 * @return the m_A_City
	 */
	public String getA_City() {
		return m_A_City;
	}

	/**
	 * @return the m_A_State
	 */
	public String getA_State() {
		return m_A_State;
	}

	/**
	 * @return the m_A_Street
	 */
	public String getA_Street() {
		return m_A_Street;
	}

	/**
	 * @return the m_A_Zip
	 */
	public String getA_Zip() {
		return m_A_Zip;
	}

	/**
	 * @return the m_A_Country
	 */
	public String getA_Country() {
		return m_A_Country;
	}

	/**
	 * @return the m_A_EMail
	 */
	public String getA_EMail() {
		return m_A_EMail;
	}

	/**
	 * @return the m_A_Ident_DL
	 */
	public String getA_Ident_DL() {
		return m_A_Ident_DL;
	}

	/**
	 * @return the m_A_Ident_SSN
	 */
	public String getA_Ident_SSN() {
		return m_A_Ident_SSN;
	}

	/**
	 * @return the m_RoutingNo
	 */
	public String getRoutingNo() {
		return m_RoutingNo;
	}

	/**
	 * @param m_TenderType the m_TenderType to set
	 */
	public void setTenderType(String m_TenderType) {
		this.m_TenderType = m_TenderType;
	}

	/**
	 * @param m_PayAmt the m_PayAmt to set
	 */
	public void setPayAmt(BigDecimal m_PayAmt) {
		this.m_PayAmt = m_PayAmt;
	}

	/**
	 * @param m_ReferenceNo the m_ReferenceNo to set
	 */
	public void setReferenceNo(String m_ReferenceNo) {
		this.m_ReferenceNo = m_ReferenceNo;
	}

	/**
	 * @param m_C_Bank_ID the m_C_Bank_ID to set
	 */
	public void setC_Bank_ID(int m_C_Bank_ID) {
		this.m_C_Bank_ID = m_C_Bank_ID;
	}

	/**
	 * @param m_DateTrx the m_DateTrx to set
	 */
	public void setDateTrx(Timestamp m_DateTrx) {
		this.m_DateTrx = m_DateTrx;
	}

	/**
	 * @param m_CreditCardExpMM the m_CreditCardExpMM to set
	 */
	public void setCreditCardExpMM(String m_CreditCardExpMM) {
		this.m_CreditCardExpMM = m_CreditCardExpMM;
	}

	/**
	 * @param m_CreditCardExpYY the m_CreditCardExpYY to set
	 */
	public void setCreditCardExpYY(String m_CreditCardExpYY) {
		this.m_CreditCardExpYY = m_CreditCardExpYY;
	}

	/**
	 * @param m_CreditCardNumber the m_CreditCardNumber to set
	 */
	public void setCreditCardNumber(String m_CreditCardNumber) {
		this.m_CreditCardNumber = m_CreditCardNumber;
	}

	/**
	 * @param m_CreditCardType the m_CreditCardType to set
	 */
	public void setCreditCardType(String m_CreditCardType) {
		this.m_CreditCardType = m_CreditCardType;
	}

	/**
	 * @param m_CreditCardVV the m_CreditCardVV to set
	 */
	public void setCreditCardVV(String m_CreditCardVV) {
		this.m_CreditCardVV = m_CreditCardVV;
	}

	/**
	 * @param m_A_Name the m_A_Name to set
	 */
	public void setA_Name(String m_A_Name) {
		this.m_A_Name = m_A_Name;
	}

	/**
	 * @param m_A_City the m_A_City to set
	 */
	public void setA_City(String m_A_City) {
		this.m_A_City = m_A_City;
	}

	/**
	 * @param m_A_State the m_A_State to set
	 */
	public void setA_State(String m_A_State) {
		this.m_A_State = m_A_State;
	}

	/**
	 * @param m_A_Street the m_A_Street to set
	 */
	public void setA_Street(String m_A_Street) {
		this.m_A_Street = m_A_Street;
	}

	/**
	 * @param m_A_Zip the m_A_Zip to set
	 */
	public void setA_Zip(String m_A_Zip) {
		this.m_A_Zip = m_A_Zip;
	}

	/**
	 * @param m_A_Country the m_A_Country to set
	 */
	public void setA_Country(String m_A_Country) {
		this.m_A_Country = m_A_Country;
	}

	/**
	 * @param m_A_EMail the m_A_EMail to set
	 */
	public void setA_EMail(String m_A_EMail) {
		this.m_A_EMail = m_A_EMail;
	}

	/**
	 * @param m_A_Ident_DL the m_A_Ident_DL to set
	 */
	public void setA_Ident_DL(String m_A_Ident_DL) {
		this.m_A_Ident_DL = m_A_Ident_DL;
	}

	/**
	 * @param m_A_Ident_SSN the m_A_Ident_SSN to set
	 */
	public void setA_Ident_SSN(String m_A_Ident_SSN) {
		this.m_A_Ident_SSN = m_A_Ident_SSN;
	}

	/**
	 * @param m_RoutingNo the m_RoutingNo to set
	 */
	public void setRoutingNo(String m_RoutingNo) {
		this.m_RoutingNo = m_RoutingNo;
	}
	
	public int getC_Invoice_ID() {
		return m_C_Invoice_ID;
	}

	public void setC_Invoice_ID(int m_C_Invoice_ID) {
		this.m_C_Invoice_ID = m_C_Invoice_ID;
	}
	
	public BigDecimal getOpenAmtCreditMemo() {
		BigDecimal m_PayAmt = Env.ZERO ;
		if(m_C_Invoice_ID == 0)
			return m_PayAmt;
		
		m_CreditMemo = MInvoice.get(Env.getCtx(), m_C_Invoice_ID);
		MDocType dt = MDocType.get(m_CreditMemo.getCtx(), m_CreditMemo.getC_DocType_ID());
		
		m_PayAmt = m_CreditMemo.getOpenAmt();
		if (MDocType.DOCBASETYPE_APInvoice.equals(dt.getDocBaseType())
			|| MDocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType()) )
			m_PayAmt = m_PayAmt.negate();
		if(m_PayAmt.compareTo(getPayAmt())> 0) {
			m_PayAmt = getPayAmt();
		}
		
		return m_PayAmt;
	}
	

	/**
	 * Initial Pay Amount
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getInitPayAmt() {
		return m_InitPayAmt;
	}

	/**
	 * Initial Pay Amount set
	 * @author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_InitPayAmt
	 * @return void
	 */
	public void setInitPayAmt(BigDecimal m_InitPayAmt) {
		this.m_InitPayAmt = m_InitPayAmt;
	}
	
	/**
	 * Get Credit Memo 
	 * @author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_C_BPartner_ID
	 * @return
	 * @return MLookup
	 */
	protected MLookup getCreditMemoLockup(int p_C_BPartner_ID) {
		MLookup lookup = null ;
		//
		int AD_Column_ID = 12349; //	RV_OpenItem C_Invoice_ID
		//	Where Clause
		String whereClause = "IsPaid='N' and Processed='Y' and C_BPartner_ID= " + p_C_BPartner_ID 
				+ " and exists ( select 1 from C_DocType C_DocType where C_DocType.DocBaseType ='ARC' "
				+ "	and C_Invoice.C_DocTypeTarget_ID = C_DocType.C_DocType_ID)";
		try {
			lookup = MLookupFactory.get(Env.getCtx(), 0, AD_Column_ID, DisplayType.TableDir, Env.getLanguage(Env.getCtx()), 
					MInvoice.COLUMNNAME_C_Invoice_ID, 0, false, whereClause);
			
		} catch (Exception e) {
		}
		//
		return lookup;
	}
	/**
	 * @author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return m_CreditMemo
	 */
	public MInvoice getM_InvCreditMemo() {
		return m_CreditMemo;
	}

	/**
	 * @author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param m_CreditMemo
	 * @return void
	 */
	public void setCreditMemo(MInvoice m_CreditMemo) {
		this.m_CreditMemo = m_CreditMemo;
	}


	@Override
	public String toString() {
		return "CollectType [m_TenderType=" + m_TenderType + ", m_PayAmt="
				+ m_PayAmt + ", m_ReferenceNo=" + m_ReferenceNo
				+ ", m_C_Bank_ID=" + m_C_Bank_ID + ", m_DateTrx=" + m_DateTrx
				+ ", m_CreditCardExpMM=" + m_CreditCardExpMM
				+ ", m_CreditCardExpYY=" + m_CreditCardExpYY
				+ ", m_CreditCardNumber=" + m_CreditCardNumber
				+ ", m_CreditCardType=" + m_CreditCardType
				+ ", m_CreditCardVV=" + m_CreditCardVV + ", m_A_Name="
				+ m_A_Name + ", m_A_City=" + m_A_City + ", m_A_State="
				+ m_A_State + ", m_A_Street=" + m_A_Street + ", m_A_Zip="
				+ m_A_Zip + ", m_A_Country=" + m_A_Country + ", m_A_EMail="
				+ m_A_EMail + ", m_A_Ident_DL=" + m_A_Ident_DL
				+ ", m_A_Ident_SSN=" + m_A_Ident_SSN + ", m_RoutingNo="
				+ m_RoutingNo + "]";
	}
}
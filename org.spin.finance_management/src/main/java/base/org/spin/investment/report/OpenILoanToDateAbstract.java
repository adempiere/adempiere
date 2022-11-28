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

package org.spin.investment.report;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Open Loan To Date)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class OpenILoanToDateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "T_FM_OpenLoanToDate";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Open Loan To Date";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54215;
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Currency	*/
	public static final String C_CURRENCY_ID = "C_Currency_ID";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Sales Transaction	*/
	public static final String ISSOTRX = "IsSOTrx";
	/**	Parameter Name for Date To	*/
	public static final String DATETO = "DateTo";
	/**	Parameter Name for Financial Product	*/
	public static final String FM_PRODUCT_ID = "FM_Product_ID";
	/**	Parameter Name for Agreement Type	*/
	public static final String FM_AGREEMENTTYPE_ID = "FM_AgreementType_ID";
	/**	Parameter Name for Agreement	*/
	public static final String FM_AGREEMENT_ID = "FM_Agreement_ID";
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Currency	*/
	private int currencyId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Document Date(To)	*/
	private Timestamp dateDocTo;
	/**	Parameter Value for Sales Transaction	*/
	private boolean isSOTrx;
	/**	Parameter Value for Date To	*/
	private Timestamp dateTo;
	/**	Parameter Value for Financial Product	*/
	private int productId;
	/**	Parameter Value for Agreement Type	*/
	private int agreementTypeId;
	/**	Parameter Value for Agreement	*/
	private int agreementId;

	@Override
	protected void prepare() {
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		currencyId = getParameterAsInt(C_CURRENCY_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		dateDocTo = getParameterToAsTimestamp(DATEDOC);
		isSOTrx = getParameterAsBoolean(ISSOTRX);
		dateTo = getParameterAsTimestamp(DATETO);
		productId = getParameterAsInt(FM_PRODUCT_ID);
		agreementTypeId = getParameterAsInt(FM_AGREEMENTTYPE_ID);
		agreementId = getParameterAsInt(FM_AGREEMENT_ID);
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Currency	*/
	protected int getCurrencyId() {
		return currencyId;
	}

	/**	 Setter Parameter Value for Currency	*/
	protected void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Document Date(To)	*/
	protected Timestamp getDateDocTo() {
		return dateDocTo;
	}

	/**	 Setter Parameter Value for Document Date(To)	*/
	protected void setDateDocTo(Timestamp dateDocTo) {
		this.dateDocTo = dateDocTo;
	}

	/**	 Getter Parameter Value for Sales Transaction	*/
	protected boolean isSOTrx() {
		return isSOTrx;
	}

	/**	 Setter Parameter Value for Sales Transaction	*/
	protected void setIsSOTrx(boolean isSOTrx) {
		this.isSOTrx = isSOTrx;
	}

	/**	 Getter Parameter Value for Date To	*/
	protected Timestamp getDateTo() {
		return dateTo;
	}

	/**	 Setter Parameter Value for Date To	*/
	protected void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	/**	 Getter Parameter Value for Financial Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Financial Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Agreement Type	*/
	protected int getAgreementTypeId() {
		return agreementTypeId;
	}

	/**	 Setter Parameter Value for Agreement Type	*/
	protected void setAgreementTypeId(int agreementTypeId) {
		this.agreementTypeId = agreementTypeId;
	}

	/**	 Getter Parameter Value for Agreement	*/
	protected int getAgreementId() {
		return agreementId;
	}

	/**	 Setter Parameter Value for Agreement	*/
	protected void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}
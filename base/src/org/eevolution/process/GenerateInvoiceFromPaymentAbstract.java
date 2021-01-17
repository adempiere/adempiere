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

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Invoice from Payment)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class GenerateInvoiceFromPaymentAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_Payment Generate Invoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Invoice from Payment";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54364;
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Target Document Type	*/
	public static final String C_DOCTYPETARGET_ID = "C_DocTypeTarget_ID";
	/**	Parameter Name for Partner Location	*/
	public static final String C_BPARTNER_LOCATION_ID = "C_BPartner_Location_ID";
	/**	Parameter Name for Invoice Contact	*/
	public static final String BILL_USER_ID = "Bill_User_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Charge	*/
	public static final String C_CHARGE_ID = "C_Charge_ID";
	/**	Parameter Name for Description	*/
	public static final String DESCRIPTION = "Description";
	/**	Parameter Name for Payment amount	*/
	public static final String PAYAMT = "PayAmt";
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Target Document Type	*/
	private int docTypeTargetId;
	/**	Parameter Value for Partner Location	*/
	private int bPartnerLocationId;
	/**	Parameter Value for Invoice Contact	*/
	private int userId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Charge	*/
	private int chargeId;
	/**	Parameter Value for Description	*/
	private String description;
	/**	Parameter Value for Payment amount	*/
	private BigDecimal payAmt;

	@Override
	protected void prepare() {
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		docTypeTargetId = getParameterAsInt(C_DOCTYPETARGET_ID);
		bPartnerLocationId = getParameterAsInt(C_BPARTNER_LOCATION_ID);
		userId = getParameterAsInt(BILL_USER_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		chargeId = getParameterAsInt(C_CHARGE_ID);
		description = getParameterAsString(DESCRIPTION);
		payAmt = getParameterAsBigDecimal(PAYAMT);
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Target Document Type	*/
	protected int getDocTypeTargetId() {
		return docTypeTargetId;
	}

	/**	 Setter Parameter Value for Target Document Type	*/
	protected void setDocTypeTargetId(int docTypeTargetId) {
		this.docTypeTargetId = docTypeTargetId;
	}

	/**	 Getter Parameter Value for Partner Location	*/
	protected int getBPartnerLocationId() {
		return bPartnerLocationId;
	}

	/**	 Setter Parameter Value for Partner Location	*/
	protected void setBPartnerLocationId(int bPartnerLocationId) {
		this.bPartnerLocationId = bPartnerLocationId;
	}

	/**	 Getter Parameter Value for Invoice Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for Invoice Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Charge	*/
	protected int getChargeId() {
		return chargeId;
	}

	/**	 Setter Parameter Value for Charge	*/
	protected void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	/**	 Getter Parameter Value for Description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Setter Parameter Value for Description	*/
	protected void setDescription(String description) {
		this.description = description;
	}

	/**	 Getter Parameter Value for Payment amount	*/
	protected BigDecimal getPayAmt() {
		return payAmt;
	}

	/**	 Setter Parameter Value for Payment amount	*/
	protected void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
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
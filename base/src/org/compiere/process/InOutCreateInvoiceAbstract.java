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

package org.compiere.process;



/** Generated Process for (Generate Invoice from Receipt)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class InOutCreateInvoiceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_InOut_CreateInvoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Invoice from Receipt";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 154;
	/**	Parameter Name for Price List	*/
	public static final String M_PRICELIST_ID = "M_PriceList_ID";
	/**	Parameter Name for Invoice Document No	*/
	public static final String INVOICEDOCUMENTNO = "InvoiceDocumentNo";
	/**	Parameter Value for Price List	*/
	private int priceListId;
	/**	Parameter Value for Invoice Document No	*/
	private String invoiceDocumentNo;

	@Override
	protected void prepare() {
		priceListId = getParameterAsInt(M_PRICELIST_ID);
		invoiceDocumentNo = getParameterAsString(INVOICEDOCUMENTNO);
	}

	/**	 Getter Parameter Value for Price List	*/
	protected int getPriceListId() {
		return priceListId;
	}

	/**	 Setter Parameter Value for Price List	*/
	protected void setPriceListId(int priceListId) {
		this.priceListId = priceListId;
	}

	/**	 Getter Parameter Value for Invoice Document No	*/
	protected String getInvoiceDocumentNo() {
		return invoiceDocumentNo;
	}

	/**	 Setter Parameter Value for Invoice Document No	*/
	protected void setInvoiceDocumentNo(String invoiceDocumentNo) {
		this.invoiceDocumentNo = invoiceDocumentNo;
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
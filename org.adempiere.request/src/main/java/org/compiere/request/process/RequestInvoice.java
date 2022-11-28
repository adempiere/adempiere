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
package org.compiere.request.process;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestType;
import org.compiere.model.MRequestUpdate;
import org.compiere.model.MStatus;
import org.compiere.model.Query;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.Msg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 	Create Invoices for Requests
 *
 *
 * @author Jorg Janke
 * @author victor.perez@e-evolution.com , Victor Perez , e-Evolution Consultant, S.A. www.e-evolution.com
 * <li>Add relation between Request Update and Invoice Line #2484</li>
 * <li>https://github.com/adempiere/adempiere/issues/2484</li>
 */
public class RequestInvoice extends RequestInvoiceAbstract {
	private HashMap<Integer, MInvoice> invoicesByPartner = new HashMap<>();
	/**
	 * 	Prepare
	 */
	protected void prepare() {
		super.prepare();
	}	//	prepare
	
	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws Exception {
		log.info("R_RequestType_ID=" + getRequestTypeId() + ", R_Group_ID=" + getGroupId()
				+ ", R_Category_ID=" + getCategoryId() + ", C_BPartner_ID=" + getBPartnerId()
				+ ", p_M_Product_ID=" + getProductId());
		validate();
		getRequestsToInvoice().stream()
				.forEach(request -> {
					MInvoice invoice = Optional.ofNullable(invoicesByPartner.get(request.getC_BPartner_ID())).orElseGet(() -> invoiceNew(request));
					invoiceLine(request, invoice);
				});

		invoicesByPartner.entrySet().stream().forEach(entry -> {
			MInvoice invoice = entry.getValue();
			invoice.processIt(MInvoice.ACTION_Prepare);
			invoice.saveEx();
			addLog(invoice.getDocumentInfo());
		});

		return "@Ok@";
	}	//	doIt


	private boolean validate() throws AdempiereSystemError {
		MRequestType requestType = MRequestType.get(getCtx(), getRequestTypeId());
		if (requestType.get_ID() == 0)
			throw new AdempiereSystemError("@R_RequestType_ID@ @NotFound@ " + getRequestTypeId());
		if (!requestType.isInvoiced())
			throw new AdempiereSystemError("@R_RequestType_ID@ <> @IsInvoiced@");
		return true;
	}


	private List<MRequest> getRequestsToInvoice() {
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MRequest.COLUMNNAME_R_RequestType_ID).append("=? AND ");
		parameters.add(getRequestTypeId());
		whereClause.append(MRequest.COLUMNNAME_IsInvoiced).append("=? AND ");
		parameters.add("Y");

		whereClause.append(" EXISTS (SELECT 1 FROM ").append(MStatus.Table_Name)
				.append(" WHERE ").append(MRequest.Table_Name).append(".").append(MRequest.COLUMNNAME_R_Status_ID).append("=")
				.append(MStatus.Table_Name).append(".").append(MStatus.COLUMNNAME_R_Status_ID).append(" AND ")
				.append(MStatus.Table_Name).append(".").append(MStatus.COLUMNNAME_IsClosed).append("=?)  AND ");
		parameters.add("Y");
		if (getGroupId() > 0) {
			whereClause.append(MRequest.Table_Name).append(".").append(MRequest.COLUMNNAME_R_Group_ID).append("=? AND ");
			parameters.add(getGroupId());
		}
		if (getCategoryId() > 0) {
			whereClause.append(MRequest.Table_Name).append(".").append(MRequest.COLUMNNAME_R_Category_ID).append("=? AND ");
			parameters.add(getCategoryId());
		}
		if (getBPartnerId() > 0) {
			whereClause.append(MRequest.Table_Name).append(".").append(MRequest.COLUMNNAME_C_BPartner_ID).append("=? AND ");
			parameters.add(getBPartnerId());
		}

		whereClause.append(" EXISTS (SELECT 1 FROM ").append(MRequestUpdate.Table_Name).append(" WHERE ")
				.append(MRequestUpdate.Table_Name).append(".").append(MRequestUpdate.COLUMNNAME_R_Request_ID).append("=")
				.append(MRequest.Table_Name).append(".").append(MRequest.COLUMNNAME_R_Request_ID).append(" AND ")
				.append(MRequestUpdate.Table_Name).append(".").append(MRequestUpdate.COLUMNNAME_C_InvoiceLine_ID).append(" IS NULL )");

		return new Query(getCtx(), MRequest.Table_Name, whereClause.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(parameters)
				.setOrderBy(MRequest.COLUMNNAME_C_BPartner_ID)
				.list();
	}

	/**
	 * 	New Invoice
	 * @param request
	 * @return
	 */
	private MInvoice invoiceNew(MRequest request) {
		MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
		invoice.setIsSOTrx(true);
		MBPartner partner = new MBPartner(getCtx(), request.getC_BPartner_ID(), get_TrxName());
		invoice.setBPartner(partner);
		if (request.getAD_User_ID() > 0)
			invoice.setAD_User_ID(request.getAD_User_ID());
		invoice.saveEx();

		invoicesByPartner.put(partner.get_ID(), invoice);
		return invoice;
	}
	
	/**
	 * 	Invoice Line
	 *x	@param request request
	 */
	private void invoiceLine(MRequest request, MInvoice invoice) {
		AtomicInteger lineNo = new AtomicInteger(invoice.getLines().length);
		Arrays.stream(request.getUpdates(null))
				.filter(requestUpdate -> requestUpdate.getQtyInvoiced() != null && requestUpdate.getQtyInvoiced().signum() != 0
						&& (requestUpdate.getM_ProductSpent_ID() > 0 || getProductId() > 0))
				.forEach(requestUpdate -> {
					StringBuilder descriptionLine = new StringBuilder();
					descriptionLine.append("@").append(MRequest.COLUMNNAME_R_Request_ID).append("@").append(" : ")
							.append(request.getDocumentNo()).append(" ");
					Optional.ofNullable(request.getSubject()).ifPresent(subject -> descriptionLine.append(" ").append(subject).append(" \n"));
					Optional.ofNullable(requestUpdate.getStartTime()).ifPresent(startTime -> descriptionLine.append("@")
							.append(MRequestUpdate.COLUMNNAME_StartTime).append("@").append(" : ").append(startTime));
					Optional.ofNullable(requestUpdate.getEndTime()).ifPresent(endTime -> descriptionLine.append("@")
							.append(MRequestUpdate.COLUMNNAME_EndTime).append("@").append(" : ").append(endTime).append(" "));
					Optional.ofNullable(requestUpdate.getResult()).ifPresent(result -> descriptionLine.append("\n ").append(result));
					MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
					lineNo.updateAndGet(no -> no + 10);
					invoiceLine.setLine(lineNo.get());
					invoiceLine.setDescription(Msg.parseTranslation(invoice.getCtx(), descriptionLine.toString()));
					invoiceLine.setQty(requestUpdate.getQtyInvoiced());
					if (requestUpdate.getM_ProductSpent_ID() > 0)
						invoiceLine.setM_Product_ID(requestUpdate.getM_ProductSpent_ID());
					else if (getProductId() > 0) {
						invoiceLine.setM_Product_ID(getProductId());
					}
					invoiceLine.setPrice();
					invoiceLine.saveEx();
					requestUpdate.setC_InvoiceLine_ID(invoiceLine.get_ID());
					requestUpdate.saveEx();
				});
	}    //	invoiceLine
}	//	RequestInvoice

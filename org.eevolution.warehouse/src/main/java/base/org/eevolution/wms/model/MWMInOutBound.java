/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.eevolution.wms.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_InOut;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * Class Model for In & Out Bound Operation
 * @author victor.perez@e-evoluton.com, e-Evolution
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Fix get line method
 */
public class MWMInOutBound extends X_WM_InOutBound implements DocAction, DocOptions {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7216075035497599383L;
	/**	Order Lines					*/
	private List<MWMInOutBoundLine> inOutBoundLines = null;
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param M_InOutBound_ID  InOutBound ID
	 *	@param trxName transaction name 
	 */
	public MWMInOutBound (Properties ctx, int M_InOutBound_ID, String trxName)
	{
		super (ctx, M_InOutBound_ID, trxName);
		if (M_InOutBound_ID == 0)
		{
			setIsApproved(false);
			setIsInTransit(false);
			setIsPrinted(false);
			setSendEMail(false);
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param M_InOutBound_ID InOutBound ID
	 */
	public MWMInOutBound (Properties ctx, int M_InOutBound_ID)
	{
		this (ctx, M_InOutBound_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMInOutBound (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset

	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInOutBound[")
			.append (get_ID ())
			.append ("-")
			.append ("")
			.append ("]");
		return sb.toString ();
	}	//	toString

	public boolean approveIt() {
		setIsApproved(true);
		return true;
	}

	public File createPDF()
	{
		try {
			File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
			return createPDF(temp);
		}
		catch (Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	} //	getPDF
	
	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF(File file)
	{
		//ReportEngine re = ReportEngine.get(getCtx(), ReportEngine., this.getM_InOutBound_ID());
		//if (re == null)
		//	return null;
		//return re.getPDF(file);
		return null;
	} //	createPDF

	public BigDecimal getApprovalAmt()
	{
		return Env.ZERO;
	} //	getApprovalAmt

	public int getC_Currency_ID() {
		return 0;
	}

	public int getDoc_User_ID() {
		return getSalesRep_ID();
	}

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	} //	getDocumentInfo

	public String getProcessMsg()
	{
		return m_processMsg;
	}

	public String getSummary()
	{
		return "" + getDocumentNo() + "/" ;
	}
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt

	/**************************************************************************
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType documentType = MDocType.get(getCtx(), getC_DocType_ID());
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), isSOTrx() ? getPickDate() : getDateTrx(), documentType.getDocBaseType(), getAD_Org_ID())){
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Lines
		List<MWMInOutBoundLine> lines = getLines(true, MWMInOutBoundLine.COLUMNNAME_Line);
		if (lines.size() == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		// Bug 1564431
		if (getDeliveryRule() != null && getDeliveryRule().equals(MWMInOutBound.DELIVERYVIARULE_Delivery)) 
		{
			for (MWMInOutBoundLine line:lines) 
			{
				MProduct product = line.getProduct();
				if (product != null && product.isExcludeAutoDelivery())
				{
					m_processMsg = "@M_Product_ID@ "+product.getValue()+" @IsExcludeAutoDelivery@";
					return DocAction.STATUS_Invalid;
				}
			}
		}
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		return true;
	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		if (isProcessed())
			return false;
		
		for (MWMInOutBoundLine line : getLines(true, null)) {
			line.deleteEx(true);
		}
		return true;
	}	//	beforeDelete

	/**	Process Message 			*/
	private String m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean m_justPrepared = false;

	public boolean unlockIt()
	{
		log.info(toString());
		setProcessing(false);
		return true;
	} //	unlockIt

	public boolean invalidateIt()
	{
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} //	invalidateIt
	
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	} //	rejectIt

	public String completeIt()
	{
		//	Just prepare
		if (DOCACTION_Prepare.equals(getDocAction()))
		{
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}

		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		
		//	Implicit Approval
		if (!isApproved())
		{
			approveIt();
		}
		
		setProcessed(true);
		
		//	Generate receipt
		if(!isSOTrx()) {
			generateReceipt();
		}
	
		setDocAction(DOCACTION_Close);

		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		return DocAction.STATUS_Completed;
	} //	completeIt
	
	@Override
	public void setProcessed(boolean Processed) {
		super.setProcessed(Processed);
		for (MWMInOutBoundLine line : getLines(true, MWMInOutBoundLine.COLUMNNAME_Line)) {
			line.setProcessed(Processed);
			line.saveEx();
		}
	}
	
	/**
	 * reverse generated receipt
	 */
	private void reverseReceipt() {
		new Query(getCtx(), I_M_InOut.Table_Name, "DocStatus = 'CO' "
				+ "AND EXISTS(SELECT 1 FROM M_InOutLine iol "
				+ "		INNER JOIN WM_InOutBoundLine iobl ON(iobl.WM_InOutBoundLine_ID = iol.WM_InOutBoundLine_ID) "
				+ "		WHERE iobl.WM_InOutBound_ID = ?)", get_TrxName())
			.setParameters(getWM_InOutBound_ID())
			.<MInOut>list()
			.forEach(receipt -> {
				if(!receipt.processIt(MInOut.DOCACTION_Reverse_Correct)) {
					throw new AdempiereException("@Error@ " + receipt.getProcessMsg());
				}
				receipt.saveEx();
			});	
	}
	
	/**
	 * Generate Receipt from Express receipt
	 */
	private void generateReceipt() {
		Hashtable<Integer, MInOut> receipts = new Hashtable<Integer, MInOut>();
		//	
		for(MWMInOutBoundLine inboundLine : getLines(true, null)) {
			// Generate Shipment based on Outbound Order
			if (inboundLine.getC_OrderLine_ID() > 0) {
				MOrderLine orderLine = inboundLine.getOrderLine();
				BigDecimal qtyToReceipt = inboundLine.getMovementQty();
				MInOut receipt = receipts.get(orderLine.getC_Order_ID());
				if(receipt == null) {
					receipt = createReceipt(orderLine, inboundLine.getParent());
					receipts.put(orderLine.getC_Order_ID(), receipt);
				}
				MInOutLine receiptLine = new MInOutLine(inboundLine.getCtx(), 0 , inboundLine.get_TrxName());
				receiptLine.setM_InOut_ID(receipt.getM_InOut_ID());
				int locatorId = inboundLine.getM_Locator_ID();
				if(locatorId == 0) {
					locatorId = getM_Locator_ID();
				}
				MProduct product = MProduct.get(getCtx(), inboundLine.getM_Product_ID());
				receiptLine.setM_Locator_ID(locatorId);
				receiptLine.setProduct(product);
				receiptLine.setQtyEntered(qtyToReceipt);
				receiptLine.setMovementQty(qtyToReceipt);
				receiptLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
				receiptLine.setM_Shipper_ID(inboundLine.getM_Shipper_ID());
				receiptLine.setM_FreightCategory_ID(inboundLine.getM_FreightCategory_ID());
				receiptLine.setFreightAmt(inboundLine.getFreightAmt());
				receiptLine.setWM_InOutBoundLine_ID(inboundLine.getWM_InOutBoundLine_ID());
				receiptLine.saveEx();
			}
		}
		//	Process Receipts
		receipts.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			MInOut shipment = entry.getValue();
			shipment.setDocAction(getDocAction());
			if (!shipment.processIt(getDocAction())) {
				log.warning("@ProcessFailed@ :" + shipment.getDocumentInfo());
			}
			shipment.saveEx();
		});
	}
	
	/**
	 * Create receipt
	 * @param orderLine
	 * @param outbound
	 * @return
	 */
	private MInOut createReceipt(MOrderLine orderLine, MWMInOutBound outbound) {
		MOrder order = orderLine.getParent();
		MDocType orderDocumentType = MDocType.get(getCtx(), order.getC_DocTypeTarget_ID());
		int docTypeId = 0;
		if(orderDocumentType.getC_DocTypeShipment_ID() > 0) {
			docTypeId = orderDocumentType.getC_DocTypeShipment_ID();
		} else {
			docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialReceipt , orderLine.getAD_Org_ID());
		}
		MInOut shipment = new MInOut(order,docTypeId, getDateTrx());
		shipment.setIsSOTrx(false);
		shipment.setM_Shipper_ID(outbound.getM_Shipper_ID());
		shipment.setM_FreightCategory_ID(outbound.getM_FreightCategory_ID());
		shipment.setFreightCostRule(outbound.getFreightCostRule());
		shipment.setFreightAmt(outbound.getFreightAmt());
		shipment.saveEx();
		return shipment;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		//	Valid Document Action
		if (AD_Table_ID == Table_ID) {
			if (docStatus.equals(DocumentEngine.STATUS_Drafted)
					|| docStatus.equals(DocumentEngine.STATUS_InProgress)
					|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
					options[index++] = DocumentEngine.ACTION_Prepare;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
					
					options[index++] = DocumentEngine.ACTION_Void;
					options[index++] = DocumentEngine.ACTION_ReActivate;
					options[index++] = DocumentEngine.ACTION_Close;
					
				} else if (docStatus.equals(DocumentEngine.STATUS_Closed)) {
					options[index++] = DocumentEngine.ACTION_None;
				}
			
		}
		
		return index;
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		getLines(true, null)
				.forEach(inoutLine -> {
					if (inoutLine.getMovementQty().signum() != 0) {

						inoutLine.setDescription(Optional.ofNullable(inoutLine.getDescription()).orElse("")
								+ " " + Msg.getMsg(getCtx(), "Voided") + " @MovementQty@ = (" + inoutLine.getMovementQty() + ")");
						inoutLine.setMovementQty(BigDecimal.ZERO);
						inoutLine.setProcessed(true);
						inoutLine.saveEx();
					}
					//AZ Goodwill
				});
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		//	Reverse receipt
		if(!isSOTrx()) {
			reverseReceipt();
		}
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
        setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description) {
        String desc = getDescription();
        if (desc == null)
            setDescription(description);
        else
            setDescription(desc + " | " + description);
    }   //  addDescription
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt() {
		log.info("closeIt - " + toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		getLines(true, null)
				.forEach(inoutLine -> {
					if (inoutLine.getMovementQty().compareTo(inoutLine.getPickedQty()) != 0) {
						inoutLine.setDescription(Optional.ofNullable(inoutLine.getDescription()).orElse("")
								+ " " + Msg.getMsg(getCtx(), "@closed@ @MovementQty@ = (" + inoutLine.getMovementQty() + ")"));
						inoutLine.setMovementQty(inoutLine.getPickedQty());
						inoutLine.setProcessed(true);
						inoutLine.saveEx();
					}
				});
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;

		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
	}	//	reActivateIt
	
	/**
	 * get MWMInOutBoundLine lines
	 * @param requery
	 * @param orderBy
	 * @return Array with MWMInOutBoundLine
	 */
	public List<MWMInOutBoundLine> getLines (boolean requery, String orderBy)
	{
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		inOutBoundLines = getLines(null, orderClause);
		return inOutBoundLines;
	}	//	getLines
	
	/**
	 * get MWMInOutBoundLine lines 
	 * @param where
	 * @param orderClause
	 * @return Array with MWMInOutBoundLine
	 */
	public List<MWMInOutBoundLine> getLines (String where, String orderClause)
	{
		StringBuffer whereClause = new StringBuffer(MWMInOutBoundLine.COLUMNNAME_WM_InOutBound_ID+"=?");
		if (!Util.isEmpty(where, true))
			whereClause.append(where);
		if (orderClause.length() == 0)
			orderClause = MWMInOutBoundLine.COLUMNNAME_Line;
		//
		return new Query(getCtx(), MWMInOutBoundLine.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(new Object[]{get_ID()})
										.setOrderBy(orderClause)
										.list();
	}	//	getLines
}	

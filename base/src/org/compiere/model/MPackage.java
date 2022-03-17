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
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Package Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPackage.java,v 1.3 2006/07/30 00:51:04 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  Add support to package like document
 */
public class MPackage extends X_M_Package implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20200801L;

    /** Standard Constructor */
    public MPackage (Properties ctx, int M_Package_ID, String trxName) {
      super (ctx, M_Package_ID, trxName);
    }

    /** Load Constructor */
    public MPackage (Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }
    
	/**
	 * 	Shipment Constructor
	 *	@param shipment shipment
	 *	@param shipper shipper
	 */
	public MPackage(MInOut shipment, MShipper shipper) {
		this (shipment.getCtx(), 0, shipment.get_TrxName());
		setClientOrg(shipment);
		setM_InOut_ID(shipment.getM_InOut_ID());
		setM_Shipper_ID(shipper.getM_Shipper_ID());
		//	Set default values for references
		setC_BPartner_ID(shipment.getC_BPartner_ID());
		setC_BPartner_Location_ID(shipment.getC_BPartner_Location_ID());
		setM_Warehouse_ID(shipment.getM_Warehouse_ID());
		setDeliveryViaRule(shipment.getDeliveryViaRule());
		Optional<String> maybeFreightCostRule = Optional.ofNullable(shipment.getFreightCostRule());
		Optional<BigDecimal> maybeFreightAmt = Optional.ofNullable(shipment.getFreightAmt());
		Optional<Integer> maybeFreightCategoryId = shipment.getM_FreightCategory_ID() > 0 ? Optional.of(shipment.getM_FreightCategory_ID()) : Optional.empty();
		Optional<Integer> maybeDocumentType = Optional.of(MDocType.getDocType(MDocType.DOCBASETYPE_Package, getAD_Org_ID()));
		maybeDocumentType.ifPresent(this::setC_DocType_ID);
		//	Set Freight cost rule
		maybeFreightCostRule.ifPresent(this::setFreightCostRule);
		maybeFreightAmt.ifPresent(this::setFreightAmt);
		maybeFreightCategoryId.ifPresent(this::setM_FreightCategory_ID);
	}	//	MPackage
	
	/**
	 * 	Movement Constructor
	 *	@param movement shipment
	 *	@param shipper shipper
	 */
	public MPackage(MMovement movement, MShipper shipper) {
		this (movement.getCtx(), 0, movement.get_TrxName());
		setClientOrg(movement);
		setM_Movement_ID(movement.getM_Movement_ID());
		setM_Shipper_ID(shipper.getM_Shipper_ID());
		//	Set default values for references
		setC_BPartner_ID(movement.getC_BPartner_ID());
		setC_BPartner_Location_ID(movement.getC_BPartner_Location_ID());
		Optional<MWarehouse> maybeWarehouse = Arrays.stream(MWarehouse.getForOrg(getCtx(), movement.getAD_Org_ID())).findFirst();
		Optional<String> maybeFreightCostRule = Optional.ofNullable(movement.getFreightCostRule());
		Optional<BigDecimal> maybeFreightAmt = Optional.ofNullable(movement.getFreightAmt());
		Optional<Integer> maybeDocumentType = Optional.of(MDocType.getDocType(MDocType.DOCBASETYPE_Package, getAD_Org_ID()));
		maybeWarehouse.ifPresent(warehouse -> setM_Warehouse_ID(warehouse.getM_Warehouse_ID()));
		maybeFreightCostRule.ifPresent(this::setFreightCostRule);
		maybeFreightAmt.ifPresent(this::setFreightAmt);
		maybeDocumentType.ifPresent(this::setC_DocType_ID);
	}	//	MPackage
	
	/**
	 * 	Create one Package for Shipment 
	 *	@param shipment shipment
	 *	@param shipper shipper
	 *	@param shipDate null for today
	 *	@return package
	 */
	public static MPackage create(MInOut shipment, MShipper shipper, Timestamp shipDate) {
		MPackage pack = new MPackage (shipment, shipper);
		Optional<Timestamp> maybeShipDate  = Optional.ofNullable(shipDate);
		maybeShipDate.ifPresent(pack::setShipDate);
		pack.saveEx();
		//	Lines
		Arrays.asList(shipment.getLines(false))
			.forEach(inOutLine -> {
				MPackageLine packageLine = new MPackageLine (pack);
				packageLine.setInOutLine(inOutLine);
				if (inOutLine.getM_Product_ID() > 0) {
					MProduct product = MProduct.get(shipment.getCtx() , inOutLine.getM_Product_ID());
					BigDecimal totalWeight = inOutLine.getMovementQty().multiply(product.getWeight());
					BigDecimal totalVolume = inOutLine.getMovementQty().multiply(product.getVolume());
					packageLine.setWeight(totalWeight);
					packageLine.setVolume(totalVolume);
				}
				packageLine.saveEx();
			});
		return pack;
	}	//	create

	/**
	 * 	Create one Package for Movement 
	 *	@param movement shipment
	 *	@param shipper shipper
	 *	@param shipDate null for today
	 *	@return package
	 */
	public static MPackage create(MMovement movement, MShipper shipper, Timestamp shipDate) {
		MPackage pack = new MPackage (movement, shipper);
		Optional<Timestamp> maybeShipDate  = Optional.ofNullable(shipDate);
		maybeShipDate.ifPresent(pack::setShipDate);
		pack.saveEx();
		//	Lines
		Arrays.asList(movement.getLines(false))
			.forEach(movementLine -> {
				MPackageLine packageLine = new MPackageLine (pack);
				packageLine.setMovementLine(movementLine);
				if (movementLine.getM_Product_ID() > 0) {
					MProduct product = MProduct.get(movement.getCtx(), movementLine.getM_Product_ID());
					BigDecimal totalWeight = movementLine.getMovementQty().multiply(product.getWeight());
					BigDecimal totalVolume = movementLine.getMovementQty().multiply(product.getVolume());
					packageLine.setWeight(totalWeight);
					packageLine.setVolume(totalVolume);
					packageLine.saveEx();
				}
			});
		return pack;
	}	//	create
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		return docType.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
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
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	/**	Lines	*/
	private List<MPackageLine> linesList = null;
	
	/**
	 * Get lines from Ticket
	 * @param requery
	 * @return
	 */
	public List<MPackageLine> getLines(boolean requery) {
		if(linesList == null
				|| requery) {
			linesList = new Query(getCtx(), I_M_PackageLine.Table_Name, I_M_PackageLine.COLUMNNAME_M_Package_ID + " = ?", get_TrxName())
					.setParameters(getM_Package_ID())
					.list();
		}
		//	Return list
		return linesList;
	}

	/**
	 * Get Lines no re-query
	 * @return
	 */
	public List<MPackageLine> getLines() {
		return this.getLines(false);
	}
	

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), docType.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		//	Add up Amounts
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord
				|| is_ValueChanged(COLUMNNAME_M_Warehouse_ID)
				|| is_ValueChanged(COLUMNNAME_C_BPartner_ID)
				|| is_ValueChanged(COLUMNNAME_C_BPartner_Location_ID)) {
			//	Validate Business Partner and Warehouse
			if(getM_InOut_ID() == 0
					&& getM_Movement_ID() == 0) {
				//	Warehouse
				if(getM_Warehouse_ID() == 0) {
					throw new AdempiereException("@M_Warehouse_ID@ @IsMandatory@");
				}
				//	Business Partner
				if(getC_BPartner_ID() == 0) {
					throw new AdempiereException("@C_BPartner_ID@ @IsMandatory@");
				}
				//	Business Partner Location
				if(getC_BPartner_Location_ID() == 0) {
					throw new AdempiereException("@C_BPartner_Location_ID@ @IsMandatory@");
				}
			}
		}
		//	When Freight category is changed
		if(newRecord
				|| is_ValueChanged(COLUMNNAME_M_FreightCategory_ID)) {
			//	Freight category
			if(getM_FreightCategory_ID() != 0) {
				MFreightCategory freightCategory = MFreightCategory.getById(getCtx(), getM_FreightCategory_ID(), get_TrxName());
				setIsInvoiced(freightCategory.isInvoiced());
			}
		}
		return super.beforeSave(newRecord);
	}
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		if (docType.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp(System.currentTimeMillis()));
		}
		if (docType.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int tableId,
			String[] docAction, String[] options, int index) {
		//	Valid Document Action
		if (Table_ID == tableId) {
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
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
        setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	@Override
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		//	For lines
		getLines(true);
		if(linesList != null) {
			linesList.forEach(line -> {
				line.setProcessed(processed);
				line.saveEx();
			});
		}
	}
	
	/**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description) {
        Optional<String> maybeDescription = Optional.ofNullable(getDescription());
        if (!maybeDescription.isPresent())
            setDescription(description);
        else
            setDescription(maybeDescription.get() + " | " + description);
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
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID() {
		int currencyId = 0;
		if(getM_InOut_ID() != 0) {
			MInOut delivery = (MInOut) getM_InOut();
			return delivery.getC_Order().getC_Currency_ID();
		} else if(getM_Movement_ID() != 0) {
			MMovement movement = (MMovement) getM_Movement();
			return movement.getC_Currency_ID();
		}
		//	Default
		if(currencyId == 0) {
			currencyId = Env.getContextAsInt(getCtx(),"$C_Currency_ID");
		}
		return currencyId;
	}	//	getC_Currency_ID

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MMPackage[")
        .append(getSummary()).append("]");
      return sb.toString();
    }
}
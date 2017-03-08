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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MDDOrder;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *	Inventory Movement Model
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li>FR [ 1948157  ]  Is necessary the reference for document reverse
 *  		@see http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1948157&group_id=176962
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 *  @author Armen Rizal, Goodwill Consulting
 * 			<li>BF [ 1745154 ] Cost in Reversing Material Related Docs  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>FR [ 2214883 ] Remove SQL code and Replace for Query
 *  @version $Id: MMovement.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MMovement extends X_M_Movement implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1628932946440487727L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param movementId id
	 *	@param trxName transaction
	 */
	public MMovement (Properties ctx, int movementId, String trxName)
	{
		super (ctx, movementId, trxName);
		if (movementId == 0)
		{
		//	setC_DocType_ID (0);
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setIsApproved (false);
			setIsInTransit (false);
			setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setPosted (false);
			super.setProcessed (false);
		}	
	}	//	MMovement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMovement (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMovement

	/**
	 * Created an instance based Distribution Order
	 * @param order
	 * @param movementDate
     */
	public MMovement(MDDOrder order , Timestamp movementDate)
	{
		super(order.getCtx() , 0 , order.get_TrxName());
		setDD_Order_ID(order.getDD_Order_ID());
		setAD_User_ID(order.getAD_User_ID());
		setPOReference(order.getPOReference());
		setReversal_ID(0);
		setM_Shipper_ID(order.getM_Shipper_ID());
		setDescription(order.getDescription());
		setC_BPartner_ID(order.getC_BPartner_ID());
		setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
		setAD_Org_ID(order.getAD_Org_ID());
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setAD_User_ID(order.getAD_User_ID());
		setC_Activity_ID(order.getC_Activity_ID());
		setC_Charge_ID(order.getC_Charge_ID());
		setChargeAmt(order.getChargeAmt());
		setC_Campaign_ID(order.getC_Campaign_ID());
		setC_Project_ID(order.getC_Project_ID());
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setUser1_ID(order.getUser1_ID());
		setUser2_ID(order.getUser2_ID());
		setUser3_ID(order.getUser3_ID());
		setUser4_ID(order.getUser4_ID());
		setPriorityRule(order.getPriorityRule());
		if (movementDate != null)
			setMovementDate (movementDate);
		setDeliveryRule(order.getDeliveryRule());
		setDeliveryViaRule(order.getDeliveryViaRule());
		setDocStatus(MMovement.DOCSTATUS_Drafted);
		setDocAction(MMovement.ACTION_Prepare);
		setFreightCostRule (order.getFreightCostRule());
		setFreightAmt(order.getFreightAmt());
		setSalesRep_ID(order.getSalesRep_ID());
	}

	/**	Lines						*/
	private MMovementLine[] movementLines = null;
	/** Confirmations				*/
	private MMovementConfirm[] movementConfirms = null;
	/** Reversal Indicator			*/
	public static String	REVERSE_INDICATOR = "^";
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return array of lines
	 */
	public MMovementLine[] getLines (boolean requery)
	{
		if (movementLines != null && !requery) {
			set_TrxName(movementLines, get_TrxName());
			return movementLines;
		}
		//
		final String whereClause = "M_Movement_ID=?";
		List<MMovementLine> list = new Query(getCtx(), I_M_MovementLine.Table_Name, whereClause, get_TrxName())
		 										.setParameters(getM_Movement_ID())
		 										.setOrderBy(MMovementLine.COLUMNNAME_Line)
		 										.list();
		movementLines = new MMovementLine[list.size ()];
		list.toArray (movementLines);
		return movementLines;
	}	//	getLines

	/**
	 * 	Get Confirmations
	 * 	@param requery requery
	 *	@return array of Confirmations
	 */
	public MMovementConfirm[] getConfirmations(boolean requery)
	{
		if (movementConfirms != null && !requery)
			return movementConfirms;

		List<MMovementConfirm> list = new Query(getCtx(), I_M_MovementConfirm.Table_Name, "M_Movement_ID=?", get_TrxName())
										.setParameters(get_ID())
										.list();
		movementConfirms = list.toArray(new MMovementConfirm[list.size()]);
		return movementConfirms;
	}	//	getConfirmations

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
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

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getC_DocType_ID() == 0)
		{
			MDocType docTypes[] = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_MaterialMovement);
			if (docTypes.length > 0)	//	get first
				setC_DocType_ID(docTypes[0].getC_DocType_ID());
			else
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}
		}
		return true;
	}	//	beforeSave
	
	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
	 *	@param processed processed
	 */
	@Override
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		final String sql = "UPDATE M_MovementLine SET Processed=? WHERE M_Movement_ID=?";
		int noLine = DB.executeUpdateEx(sql, new Object[]{processed, get_ID()}, get_TrxName());
		movementLines = null;
		log.fine("Processed=" + processed + " - Lines=" + noLine);
	}	//	setProcessed
	
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMessage = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String processMessage = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
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
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), docType.getDocBaseType(), getAD_Org_ID()))
		{
			processMessage = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		MMovementLine[] lines = getLines(false);
		if (lines.length == 0)
		{
			processMessage = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Confirmation
		if (docType.isInTransit() && !isReversal())
			createConfirmation();

		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		
		justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Create Movement Confirmation
	 */
	private void createConfirmation()
	{
		MMovementConfirm[] movementConfirms = getConfirmations(false);
		if (movementConfirms.length > 0)
			return;
		
		//	Create Confirmation
		MMovementConfirm.create (this, false);
	}	//	createConfirmation
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info(toString());
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
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;

		//	Outstanding (not processed) Incoming Confirmations ?
		MMovementConfirm[] movementConfirms = getConfirmations(true);
		for (MMovementConfirm movementConfirm : movementConfirms)
		{
			if (!movementConfirm.isProcessed())
			{
					processMessage = "Open: @M_MovementConfirm_ID@ - " + movementConfirm.getDocumentNo();
					return DocAction.STATUS_InProgress;
			}
		}

		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		
		//
		MMovementLine[] movementLines = getLines(false);
		//for (int i = 0; i < movementLines.length; i++)
		for (MMovementLine movementLine : movementLines)
		{
			MTransaction transactionFrom = null;
			
			//Stock Movement - Counterpart MOrder.reserveStock
			MProduct product = movementLine.getProduct();
			if (product != null 
					&& product.isStocked() )
			{
				//Ignore the Material Policy when is Reverse Correction
				if(!isReversal())
					checkMaterialPolicy(movementLine);

				if (movementLine.getM_AttributeSetInstance_ID() == 0)
				{
					MMovementLineMA movementLineMAS[] = MMovementLineMA.get(getCtx(),
							movementLine.getM_MovementLine_ID(), get_TrxName());
					for (int j = 0; j < movementLineMAS.length; j++)
					{
						MMovementLineMA movementLineMA = movementLineMAS[j];
						//
						MLocator locator = new MLocator (getCtx(), movementLine.getM_Locator_ID(), get_TrxName());
						MLocator locatorTo = new MLocator (getCtx(), movementLine.getM_LocatorTo_ID(), get_TrxName());
						//Update Storage 
						if (!MStorage.add(getCtx(),locator.getM_Warehouse_ID(),
								movementLine.getM_Locator_ID(),
								movementLine.getM_Product_ID(),
								movementLineMA.getM_AttributeSetInstance_ID(), 0,
								movementLineMA.getMovementQty().negate(), Env.ZERO ,  Env.ZERO , get_TrxName()))
						{
							processMessage = "Cannot correct Inventory (MA)";
							return DocAction.STATUS_Invalid;
						}

						int attributeSetInstanceToId = movementLine.getM_AttributeSetInstanceTo_ID();
						//only can be same asi if locator is different
						if (attributeSetInstanceToId == 0 && movementLine.getM_Locator_ID() != movementLine.getM_LocatorTo_ID())
						{
							attributeSetInstanceToId = movementLineMA.getM_AttributeSetInstance_ID();
						}
						//Update Storage 
						if (!MStorage.add(getCtx(),locatorTo.getM_Warehouse_ID(),
								movementLine.getM_LocatorTo_ID(),
								movementLine.getM_Product_ID(),
								attributeSetInstanceToId, 0,
								movementLineMA.getMovementQty(), Env.ZERO ,  Env.ZERO , get_TrxName()))
						{
							processMessage = "Cannot correct Inventory (MA)";
							return DocAction.STATUS_Invalid;
						}

						//
						transactionFrom = new MTransaction (getCtx(), locator.getAD_Org_ID(),
								MTransaction.MOVEMENTTYPE_MovementFrom,
								movementLine.getM_Locator_ID(), movementLine.getM_Product_ID(), movementLineMA.getM_AttributeSetInstance_ID(),
								movementLineMA.getMovementQty().negate(), getMovementDate(), get_TrxName());
						transactionFrom.setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
						if (!transactionFrom.save())
						{
							processMessage = "Transaction From not inserted (MA)";
							return DocAction.STATUS_Invalid;
						}
						MTransaction transactionTo = new MTransaction (getCtx(), locatorTo.getAD_Org_ID(),
								MTransaction.MOVEMENTTYPE_MovementTo,
								movementLine.getM_LocatorTo_ID(), movementLine.getM_Product_ID(), attributeSetInstanceToId,
								movementLineMA.getMovementQty(), getMovementDate(), get_TrxName());
						transactionTo.setAD_Org_ID(locatorTo.getAD_Org_ID());
						transactionTo.setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
						if (!transactionTo.save())
						{
							processMessage = "Transaction To not inserted (MA)";
							return DocAction.STATUS_Invalid;
						}
					}
				}
				//	Fallback - We have ASI
				if (transactionFrom == null)
				{
					MLocator locator = new MLocator (getCtx(), movementLine.getM_Locator_ID(), get_TrxName());
					MLocator locatorTo = new MLocator (getCtx(), movementLine.getM_LocatorTo_ID(), get_TrxName());
					//Update Storage 
					if (!MStorage.add(getCtx(),locator.getM_Warehouse_ID(),
							movementLine.getM_Locator_ID(),
							movementLine.getM_Product_ID(),
							movementLine.getM_AttributeSetInstance_ID(), 0,
							movementLine.getMovementQty().negate(), Env.ZERO ,  Env.ZERO , get_TrxName()))
					{
						processMessage = "Cannot correct Inventory (MA)";
						return DocAction.STATUS_Invalid;
					}

					//Update Storage 
					if (!MStorage.add(getCtx(),locatorTo.getM_Warehouse_ID(),
							movementLine.getM_LocatorTo_ID(),
							movementLine.getM_Product_ID(),
							movementLine.getM_AttributeSetInstanceTo_ID(), 0,
							movementLine.getMovementQty(), Env.ZERO ,  Env.ZERO , get_TrxName()))
					{
						processMessage = "Cannot correct Inventory (MA)";
						return DocAction.STATUS_Invalid;
					}

					//
					transactionFrom = new MTransaction (getCtx(), locator.getAD_Org_ID(),
							MTransaction.MOVEMENTTYPE_MovementFrom,
							movementLine.getM_Locator_ID(), movementLine.getM_Product_ID(), movementLine.getM_AttributeSetInstance_ID(),
							movementLine.getMovementQty().negate(), getMovementDate(), get_TrxName());
					transactionFrom.setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
					if (!transactionFrom.save())
					{
						processMessage = "Transaction From not inserted";
						return DocAction.STATUS_Invalid;
					}

					MTransaction transactionTo = new MTransaction (getCtx(), locatorTo.getAD_Org_ID(),
							MTransaction.MOVEMENTTYPE_MovementTo,
							movementLine.getM_LocatorTo_ID(), movementLine.getM_Product_ID(), movementLine.getM_AttributeSetInstanceTo_ID(),
							movementLine.getMovementQty(), getMovementDate(), get_TrxName());
					transactionTo.setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
					transactionTo.setAD_Org_ID(locatorTo.getAD_Org_ID());
					if (!transactionTo.save())
					{
						processMessage = "Transaction To not inserted";
						return DocAction.STATUS_Invalid;
					}
				}	//	Fallback
			} // product stock	
		}	//	for all lines
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMessage = valid;
			return DocAction.STATUS_Invalid;
		}
		
		// Set the definite document number after completed (if needed)
		setDefiniteDocumentNo();

		//
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
			setMovementDate(new Timestamp (System.currentTimeMillis()));
		}
		if (docType.isOverwriteSeqOnComplete()) {
			String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
			if (value != null)
				setDocumentNo(value);
		}
	}

	/**
	 * 	Check Material Policy
	 * 	Sets line ASI
	 */
	private void checkMaterialPolicy(MMovementLine line)
	{
		int no = MMovementLineMA.deleteMovementLineMA(line.getM_MovementLine_ID(), get_TrxName());
		if (no > 0)
			log.config("Delete old #" + no);
		
		boolean needSave = false;

		//	Attribute Set Instance
		if (line.getM_AttributeSetInstance_ID() == 0)
		{
			MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
			String issuePolicy = product.getMMPolicy();
			MStorage[] storages = MStorage.getWarehouse(getCtx(), 0, line.getM_Product_ID(), 0, 
					null, MClient.MMPOLICY_FiFo.equals(issuePolicy), true, line.getM_Locator_ID(), get_TrxName());

			BigDecimal qtyToDeliver = line.getMovementQty();

			for (MStorage storage: storages)
			{
				if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
				{
					MMovementLineMA movementLineMA = new MMovementLineMA (line,
							storage.getM_AttributeSetInstance_ID(),
							qtyToDeliver);
					movementLineMA.saveEx();
					qtyToDeliver = Env.ZERO;
					log.fine( movementLineMA + ", QtyToDeliver=" + qtyToDeliver);
				}
				else
				{	
					MMovementLineMA movementLineMA = new MMovementLineMA (line,
								storage.getM_AttributeSetInstance_ID(),
								storage.getQtyOnHand());
					movementLineMA.saveEx();
					qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
					log.fine( movementLineMA + ", QtyToDeliver=" + qtyToDeliver);
				}
				if (qtyToDeliver.signum() == 0)
					break;
			}
							
			//	No AttributeSetInstance found for remainder
			if (qtyToDeliver.signum() != 0)
			{
				//deliver using new asi
				MAttributeSetInstance attributeSetInstance = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
				int attributeSetInstanceId = attributeSetInstance.getM_AttributeSetInstance_ID();
				MMovementLineMA movementLineMA = new MMovementLineMA (line, attributeSetInstanceId , qtyToDeliver);
				movementLineMA.saveEx();
				log.fine("##: " + movementLineMA);
			}
		}	//	attributeSetInstance
		
		if (needSave)
		{
			line.saveEx();
		}
	}	//	checkMaterialPolicy

	/**
	 * 	Void Document.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMessage != null)
			return false;
		
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMessage = "Document Closed: " + getDocStatus();
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
			//	Set lines to 0
			MMovementLine[] movementLines = getLines(false);
			for (int i = 0; i < movementLines.length; i++)
			{
				MMovementLine movementLine = movementLines[i];
				BigDecimal oldMovementLine = movementLine.getMovementQty();
				if (oldMovementLine.compareTo(Env.ZERO) != 0)
				{
					movementLine.setMovementQty(Env.ZERO);
					movementLine.addDescription("Void (" + oldMovementLine + ")");
					movementLine.saveEx();
				}
			}
		}
		else
		{
			return reverseCorrectIt();
		}
		// After Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMessage != null)
			return false;
			
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMessage != null)
			return false;

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);

		// After Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMessage != null)
			return false;
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return false 
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		// Before reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMessage != null)
			return false;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			processMessage = "@PeriodClosed@";
			return false;
		}

		//	Deep Copy
		MMovement reversalMovement = new MMovement(getCtx(), 0, get_TrxName());
		copyValues(this, reversalMovement, getAD_Client_ID(), getAD_Org_ID());
		reversalMovement.setDocStatus(DOCSTATUS_Drafted);
		reversalMovement.setDocAction(DOCACTION_Complete);
		reversalMovement.setIsApproved (false);
		reversalMovement.setIsInTransit (false);
		reversalMovement.setPosted(false);
		reversalMovement.setProcessed(false);
		reversalMovement.setDocumentNo(getDocumentNo() + REVERSE_INDICATOR);	//	indicate reversals
		reversalMovement.addDescription("{->" + getDocumentNo() + ")");
		//FR [ 1948157  ]
		reversalMovement.setReversal_ID(getM_Movement_ID());
		reversalMovement.setReversal(true);
		if (!reversalMovement.save())
		{
			processMessage = "Could not create Movement Reversal";
			return false;
		}
		reversalMovement.setReversal(true);

		//	Reverse Line Qty
		Arrays.stream(getLines(true)).forEach(movementLine -> {
			MMovementLine reversalMovementLine = new MMovementLine(getCtx(), 0, get_TrxName());
			copyValues(movementLine, reversalMovementLine, movementLine.getAD_Client_ID(), movementLine.getAD_Org_ID());
			reversalMovementLine.setM_Movement_ID(reversalMovement.getM_Movement_ID());
			// store original (voided/reversed) document line
			reversalMovementLine.setReversalLine_ID(movementLine.getM_MovementLine_ID());
			reversalMovementLine.setMovementQty(movementLine.getMovementQty().negate());
			reversalMovementLine.setTargetQty(movementLine.getTargetQty().negate());
			reversalMovementLine.setScrappedQty(movementLine.getScrappedQty().negate());
			reversalMovementLine.setConfirmedQty(movementLine.getConfirmedQty().negate());
			reversalMovementLine.setProcessed(false);
			reversalMovementLine.saveEx();
		});

		//After that those reverse movements confirmations are generated,
		//the reverse reference for movement and movement line are set
		Arrays.stream(getConfirmations(true)).forEach(movementConfirm -> {
			movementConfirm.reverseCorrectIt();
		});
		//After movements confirmations are reverce generate the movement id and movement line
		Arrays.stream(getConfirmations(true)).filter(movementConfirm -> movementConfirm.getReversal_ID() > 0)
				.forEach(movementConfirm -> {
					Arrays.stream(reversalMovement.getLines(true)).forEach(reversalMovementLine -> {
						Arrays.stream(movementConfirm.getLines(true))
								.filter(movementLineConfirm -> movementLineConfirm.getM_MovementLine_ID() == reversalMovementLine.getReversalLine_ID())
								.forEach(movementLineConfirm -> {
									movementLineConfirm.setM_MovementLine_ID(reversalMovementLine.getM_MovementLine_ID());
									movementLineConfirm.saveEx();
								});
					});
					movementConfirm.setM_Movement_ID(reversalMovement.getM_Movement_ID());
					movementConfirm.setIsReversal(true);
					movementConfirm.saveEx();
				});


		if (!reversalMovement.processIt(DocAction.ACTION_Complete))
		{
			processMessage = "Reversal ERROR: " + reversalMovement.getProcessMsg();
			return false;
		}
		reversalMovement.closeIt();
		reversalMovement.setDocStatus(DOCSTATUS_Reversed);
		reversalMovement.setDocAction(DOCACTION_None);
		reversalMovement.saveEx();
		processMessage = reversalMovement.getDocumentNo();

		// After reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMessage != null)
			return false;
		
		//	Update Reversed (this)
		addDescription("(" + reversalMovement.getDocumentNo() + "<-)");
		setReversal_ID(reversalMovement.getM_Movement_ID());
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		
		return true;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		// Before reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
		
		// After reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
		
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMessage != null)
			return false;	
		
		// After reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMessage != null)
			return false;
		
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		stringBuffer.append(": ")
			.append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			stringBuffer.append(" - ").append(getDescription());
		return stringBuffer.toString();
	}	//	getSummary
	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return processMessage;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID
	
	/** Reversal Flag		*/
	private boolean m_reversal = false;
	
	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	private void setReversal(boolean reversal)
	{
		m_reversal = reversal;
	}	//	setReversal
	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	private boolean isReversal()
	{
		return m_reversal;
	}	//	isReversal

	/**
	 * 	Document Status is Complete or Closed
	 *	@return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds) 
			|| DOCSTATUS_Closed.equals(ds)
			|| DOCSTATUS_Reversed.equals(ds);
	}	//	isComplete
	
	private boolean isSameCostDimension(MAcctSchema as, MTransaction trxFrom, MTransaction trxTo)
	{
		if (trxFrom.getM_Product_ID() != trxTo.getM_Product_ID())
		{
			throw new AdempiereException("Same product is needed - "+trxFrom+", "+trxTo);
		}
		MProduct product = MProduct.get(getCtx(), trxFrom.getM_Product_ID());
		String costingLevel = product.getCostingLevel(as,trxFrom.getAD_Org_ID());
		int orgId = trxFrom.getAD_Org_ID();
		int orgToId = trxTo.getAD_Org_ID();
		int attributeSetInstanceId = trxFrom.getM_AttributeSetInstance_ID();
		int attributeSetInstanceToId = trxTo.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(costingLevel))
		{
			orgId = 0;
			orgToId = 0;
			attributeSetInstanceId = 0;
			attributeSetInstanceToId = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
		{
			attributeSetInstanceId = 0;
			attributeSetInstanceToId = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
		{
			orgId = 0;
			orgToId = 0;
		}
		//
		return orgId == orgToId && attributeSetInstanceId == attributeSetInstanceToId;
	}
}	//	MMovement


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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	Inventory Movement Confirmation
 *	
 *  @author Jorg Janke
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 *  @version $Id: MMovementConfirm.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MMovementConfirm extends X_M_MovementConfirm implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5210710606049843678L;
	public static String	REVERSE_INDICATOR = "^";

	/**
	 * 	Create Confirmation or return existing one
	 *	@param move movement
	 *	@param checkExisting if false, new confirmation is created
	 *	@return Confirmation
	 */
	public static MMovementConfirm create (MMovement move, boolean checkExisting)
	{
		if (checkExisting)
		{
			MMovementConfirm[] confirmations = move.getConfirmations(false);
			for (int i = 0; i < confirmations.length; i++)
			{
				MMovementConfirm confirm = confirmations[i];
				return confirm;
			}
		}

		MMovementConfirm confirm = new MMovementConfirm (move);
		confirm.save(move.get_TrxName());
		MMovementLine[] moveLines = move.getLines(false);
		for (int i = 0; i < moveLines.length; i++)
		{
			MMovementLine mLine = moveLines[i];
			MMovementLineConfirm cLine = new MMovementLineConfirm (confirm);
			cLine.setMovementLine(mLine);
			cLine.save(move.get_TrxName());
		}
		return confirm;
	}	//	MInOutConfirm

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_MovementConfirm_ID id
	 *	@param trxName transaction
	 */
	public MMovementConfirm (Properties ctx, int M_MovementConfirm_ID, String trxName)
	{
		super (ctx, M_MovementConfirm_ID, trxName);
		if (M_MovementConfirm_ID == 0)
		{
		//	setM_Movement_ID (0);
			setDocAction (DOCACTION_Complete);
			setDocStatus (DOCSTATUS_Drafted);
			setIsApproved (false);	// N
			setProcessed (false);
		}
	}	//	MMovementConfirm

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMovementConfirm (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMovementConfirm

	/**
	 * 	Parent Constructor
	 *	@param move movement
	 */
	public MMovementConfirm (MMovement move)
	{
		this (move.getCtx(), 0, move.get_TrxName());
		setClientOrg(move);
		setM_Movement_ID(move.getM_Movement_ID());
	}	//	MInOutConfirm
	
	/**	Confirm Lines					*/
	private MMovementLineConfirm[] movementLineConfirms = null;
	
	/**	Physical Inventory From	*/
	private MInventory 				inventoryFrom = null;
	/**	Physical Inventory To	*/
	private MInventory 				inventoryTo = null;
	/**	Physical Inventory Info	*/
	private String 					inventoryInfo = null;

	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return array of lines
	 */
	public MMovementLineConfirm[] getLines (boolean requery)
	{
		if (movementLineConfirms != null && !requery) {
			set_TrxName(movementLineConfirms, get_TrxName());
			return movementLineConfirms;
		}
		String sql = "SELECT * FROM M_MovementLineConfirm "
			+ "WHERE M_MovementConfirm_ID=?";
		ArrayList<MMovementLineConfirm> list = new ArrayList<MMovementLineConfirm>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getM_MovementConfirm_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MMovementLineConfirm(getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e); 
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		movementLineConfirms = new MMovementLineConfirm[list.size ()];
		list.toArray (movementLineConfirms);
		return movementLineConfirms;
	}	//	getLines
	
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
	 * 	Set Approved
	 *	@param IsApproved approval
	 */
	public void setIsApproved (boolean IsApproved)
	{
		if (IsApproved && !isApproved())
		{
			int AD_User_ID = Env.getAD_User_ID(getCtx());
			MUser user = MUser.get(getCtx(), AD_User_ID);
			String info = user.getName() 
				+ ": "
				+ Msg.translate(getCtx(), "IsApproved")
				+ " - " + new Timestamp(System.currentTimeMillis());
			addDescription(info);
		}
		super.setIsApproved (IsApproved);
	}	//	setIsApproved
	
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		return Msg.getElement(getCtx(), "M_MovementConfirm_ID") + " " + getDocumentNo();
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

	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMesssage = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String processMesssage = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
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
		processMesssage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMesssage != null)
			return DocAction.STATUS_Invalid;

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getUpdated(), MDocType.DOCBASETYPE_MaterialMovement, getAD_Org_ID()))
		{
			processMesssage = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		MMovementLineConfirm[] movementLineConfirms = getLines(true);
		if (movementLineConfirms.length == 0)
		{
			processMesssage = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		boolean difference = false;
		for (int i = 0; i < movementLineConfirms.length; i++)
		{
			if (!movementLineConfirms[i].isFullyConfirmed())
			{
				difference = true;
				break;
			}
		}
		
		processMesssage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMesssage != null)
			return DocAction.STATUS_Invalid;

		//
		justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
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
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		processMesssage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMesssage != null)
			return DocAction.STATUS_Invalid;

		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info("completeIt - " + toString());
		//
		MMovement movement = new MMovement (getCtx(), getM_Movement_ID(), get_TrxName());
		MMovementLineConfirm[] movementLineConfirms = getLines(false);
		for (int i = 0; i < movementLineConfirms.length; i++)
		{
			MMovementLineConfirm movementLineConfirm = movementLineConfirms[i];
			movementLineConfirm.set_TrxName(get_TrxName());
			if (!movementLineConfirm.processLine ())
			{
				processMesssage = "ShipLine not saved - " + movementLineConfirm;
				return DocAction.STATUS_Invalid;
			}
			if (movementLineConfirm.isFullyConfirmed())
			{
				movementLineConfirm.setProcessed(true);
				movementLineConfirm.save(get_TrxName());
			}
			else
			{
				if (createDifferenceDoc (movement, movementLineConfirm))
				{
					movementLineConfirm.setProcessed(true);
					movementLineConfirm.save(get_TrxName());
				}
				else
				{
					log.log(Level.SEVERE, "completeIt - Scrapped=" + movementLineConfirm.getScrappedQty()
						+ " - Difference=" + movementLineConfirm.getDifferenceQty());
					
					processMesssage = "Differnce Doc not created";
					return DocAction.STATUS_Invalid;
				}
			}
		}	//	for all lines
		
		if (inventoryInfo != null)
		{
			processMesssage = " @M_Inventory_ID@: " + inventoryInfo;
			addDescription(Msg.translate(getCtx(), "M_Inventory_ID") 
				+ ": " + inventoryInfo);
		}
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMesssage = valid;
			return DocAction.STATUS_Invalid;
		}
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Create Difference Document.
	 * 	Creates one or two inventory lines
	 * 	@param move movement
	 *	@param confirm confirm line
	 *	@return true if created
	 */
	private boolean createDifferenceDoc (MMovement move, MMovementLineConfirm confirm)
	{
		MMovementLine movementLine = confirm.getLine();
		
		//	Difference - Create Inventory Difference for Source Location
		if (Env.ZERO.compareTo(confirm.getDifferenceQty()) != 0)
		{
			//	Get Warehouse for Source
			MLocator locator = MLocator.get(getCtx(), movementLine.getM_Locator_ID());
			if (inventoryFrom != null
				&& inventoryFrom.getM_Warehouse_ID() != locator.getM_Warehouse_ID())
				inventoryFrom = null;
			
			if (inventoryFrom == null)
			{
				MWarehouse warehouse = MWarehouse.get(getCtx(), locator.getM_Warehouse_ID());
				inventoryFrom = new MInventory (warehouse);
				inventoryFrom.setDescription(Msg.translate(getCtx(), "M_MovementConfirm_ID") + " " + getDocumentNo());
				if (!inventoryFrom.save(get_TrxName()))
				{
					processMesssage += "Inventory not created";
					return false;
				}
				//	First Inventory
				if (getM_Inventory_ID() == 0)
				{
					setM_Inventory_ID(inventoryFrom.getM_Inventory_ID());
					inventoryInfo = inventoryFrom.getDocumentNo();
				}
				else
					inventoryInfo += "," + inventoryFrom.getDocumentNo();
			}
			
			log.info("createDifferenceDoc - Difference=" + confirm.getDifferenceQty());
			MInventoryLine inventoryLine = new MInventoryLine (inventoryFrom,
					movementLine.getM_Locator_ID(), movementLine.getM_Product_ID(), movementLine.getM_AttributeSetInstance_ID(),
					confirm.getDifferenceQty(), Env.ZERO);
			inventoryLine.setDescription(Msg.translate(getCtx(), "DifferenceQty"));
			if (!inventoryLine.save(get_TrxName()))
			{
				processMesssage += "Inventory Line not created";
				return false;
			}
			confirm.setM_InventoryLine_ID(inventoryLine.getM_InventoryLine_ID());
		}	//	Difference
		
		//	Scrapped - Create Inventory Difference for Target Location
		if (Env.ZERO.compareTo(confirm.getScrappedQty()) != 0)
		{
			//	Get Warehouse for Target
			MLocator locator = MLocator.get(getCtx(), movementLine.getM_LocatorTo_ID());
			if (inventoryTo != null
				&& inventoryTo.getM_Warehouse_ID() != locator.getM_Warehouse_ID())
				inventoryTo = null;
		
			if (inventoryTo == null)
			{
				MWarehouse warehouse = MWarehouse.get(getCtx(), locator.getM_Warehouse_ID());
				inventoryTo = new MInventory (warehouse);
				inventoryTo.setDescription(Msg.translate(getCtx(), "M_MovementConfirm_ID") + " " + getDocumentNo());
				if (!inventoryTo.save(get_TrxName()))
				{
					processMesssage += "Inventory not created";
					return false;
				}
				//	First Inventory
				if (getM_Inventory_ID() == 0)
				{
					setM_Inventory_ID(inventoryTo.getM_Inventory_ID());
					inventoryInfo = inventoryTo.getDocumentNo();
				}
				else
					inventoryInfo += "," + inventoryTo.getDocumentNo();
			}
			
			log.info("createDifferenceDoc - Scrapped=" + confirm.getScrappedQty());
			MInventoryLine inventoryLine = new MInventoryLine (inventoryTo,
				movementLine.getM_LocatorTo_ID(), movementLine.getM_Product_ID(), movementLine.getM_AttributeSetInstance_ID(),
				confirm.getScrappedQty(), Env.ZERO);
			inventoryLine.setDescription(Msg.translate(getCtx(), "ScrappedQty"));
			if (!inventoryLine.save(get_TrxName()))
			{
				processMesssage += "Inventory Line not created";
				return false;
			}
			confirm.setM_InventoryLine_ID(inventoryLine.getM_InventoryLine_ID());
		}	//	Scrapped
		
		return true;
	}	//	createDifferenceDoc

	/**
	 * 	Void Document.
	 * 	@return false 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		// Before Void
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMesssage != null)
			return false;

		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMesssage = "Document Closed: " + getDocStatus();
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
			MMovementLineConfirm[] movementLineConfirms = getLines(false);
			Arrays.stream(movementLineConfirms).forEach( line -> {
				BigDecimal oldConfirmedQty = line.getConfirmedQty();
				if (oldConfirmedQty.compareTo(Env.ZERO) != 0)
				{
					line.setConfirmedQty(Env.ZERO);
					line.setDescription("Void (" + oldConfirmedQty + ")");
					line.saveEx();
				}
			});
		}
		else
			return reverseCorrectIt();

		// After Void
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMesssage != null)
			return false;

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());
		// Before Close
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMesssage != null)
			return false;

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);

		// After Close
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMesssage != null)
			return false;
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return false 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMesssage != null)
			return false;

		MMovementConfirm reversalMovementConfirm = new MMovementConfirm(getCtx(), 0, get_TrxName());
		copyValues(this, reversalMovementConfirm, getAD_Client_ID(), getAD_Org_ID());
		reversalMovementConfirm.setReversal_ID(getM_MovementConfirm_ID());
		reversalMovementConfirm.setM_Movement_ID(getM_Movement_ID());
		reversalMovementConfirm.setDocStatus(DOCSTATUS_Drafted);
		reversalMovementConfirm.setDocAction(DOCACTION_Complete);
		reversalMovementConfirm.setIsApproved (false);
		reversalMovementConfirm.setProcessed(false);
		reversalMovementConfirm.setDocumentNo(getDocumentNo() + REVERSE_INDICATOR);	//	indicate reversals
		reversalMovementConfirm.addDescription("{->" + getDocumentNo() + ")");
		reversalMovementConfirm.setIsReversal(true);
		reversalMovementConfirm.saveEx();

		//	Reverse Line Qty
		Arrays.stream(getLines(true)).forEach(movementLineConfirm -> {
			MMovementLineConfirm reverseMovementLineConfirm = new MMovementLineConfirm(getCtx(), 0, get_TrxName());
			copyValues(movementLineConfirm, reverseMovementLineConfirm, movementLineConfirm.getAD_Client_ID(), movementLineConfirm.getAD_Org_ID());
			reverseMovementLineConfirm.setM_MovementConfirm_ID(reversalMovementConfirm.getM_MovementConfirm_ID());
			reverseMovementLineConfirm.setReversalLine_ID(movementLineConfirm.getM_MovementLineConfirm_ID());
			reverseMovementLineConfirm.setM_MovementLine_ID(movementLineConfirm.getM_MovementLine_ID());
			reverseMovementLineConfirm.setConfirmedQty(movementLineConfirm.getConfirmedQty().negate());
			reverseMovementLineConfirm.setTargetQty(movementLineConfirm.getTargetQty().negate());
			reverseMovementLineConfirm.setScrappedQty(movementLineConfirm.getScrappedQty().negate());
			reverseMovementLineConfirm.setProcessed(false);
			reverseMovementLineConfirm.saveEx();
		});
		if (!reversalMovementConfirm.processIt(DocAction.ACTION_Complete))
		{
			processMesssage = "Reversal ERROR: " + reversalMovementConfirm.getProcessMsg();
			return false;
		}
		reversalMovementConfirm.closeIt();
		reversalMovementConfirm.setDocStatus(DOCSTATUS_Reversed);
		reversalMovementConfirm.setDocAction(DOCACTION_None);
		reversalMovementConfirm.saveEx();
		processMesssage = reversalMovementConfirm.getDocumentNo();

		// After reverseCorrect
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMesssage != null)
			return false;

		//	Update Reversed (this)
		setDescription("(" + reversalMovementConfirm.getDocumentNo() + "<-)");
		setIsReversal(true);
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);
		setDocAction(DOCACTION_None);
		saveEx();
		return true;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMesssage != null)
			return false;

		MMovementConfirm movementConfirm = new MMovementConfirm(getCtx() , 0 ,get_TrxName() );
		PO.copyValues(this, movementConfirm);

		
		// After reverseAccrual
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMesssage != null)
			return false;
				
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		// Before reActivate
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMesssage != null)
			return false;	
		
		// After reActivate
		processMesssage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMesssage != null)
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
		return processMesssage;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getUpdatedBy();
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

	
}	//	MMovementConfirm

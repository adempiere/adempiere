/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 *  Physical Inventory Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInventory.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MInventory extends X_M_Inventory implements DocAction
{
	/**
	 * 	Get Inventory from Cache
	 *	@param ctx context
	 *	@param M_Inventory_ID id
	 *	@return MInventory
	 */
	public static MInventory get (Properties ctx, int M_Inventory_ID)
	{
		Integer key = new Integer (M_Inventory_ID);
		MInventory retValue = (MInventory) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MInventory (ctx, M_Inventory_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MInventory> s_cache = new CCache<Integer,MInventory>("M_Inventory", 5, 5);
	
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context 
	 *	@param M_Inventory_ID id
	 *	@param trxName transaction
	 */
	public MInventory (Properties ctx, int M_Inventory_ID, String trxName)
	{
		super (ctx, M_Inventory_ID, trxName);
		if (M_Inventory_ID == 0)
		{
		//	setName (null);
		//  setM_Warehouse_ID (0);		//	FK
			setMovementDate (new Timestamp(System.currentTimeMillis()));
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setIsApproved (false);
			setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setPosted (false);
			setProcessed (false);
		}
	}	//	MInventory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MInventory (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInventory

	/**
	 * 	Warehouse Constructor
	 *	@param wh warehouse
	 */
	public MInventory (MWarehouse wh)
	{
		this (wh.getCtx(), 0, wh.get_TrxName());
		setClientOrg(wh);
		setM_Warehouse_ID(wh.getM_Warehouse_ID());
	}	//	MInventory
	
	
	/**	Lines						*/
	private MInventoryLine[]	m_lines = null;
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return array of lines
	 */
	public MInventoryLine[] getLines (boolean requery)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//
		ArrayList<MInventoryLine> list = new ArrayList<MInventoryLine>();
		String sql = "SELECT * FROM M_InventoryLine WHERE M_Inventory_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getM_Inventory_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MInventoryLine (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		
		m_lines = new MInventoryLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
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
	 * 	Overwrite Client/Org - from Import.
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInventory[");
		sb.append (get_ID())
			.append ("-").append (getDocumentNo())
			.append (",M_Warehouse_ID=").append(getM_Warehouse_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
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
			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_MaterialPhysicalInventory);
			if (types.length > 0)	//	get first
				setC_DocType_ID(types[0].getC_DocType_ID());
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
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE M_InventoryLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE M_Inventory_ID=" + getM_Inventory_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine("Processed=" + processed + " - Lines=" + noLine);
	}	//	setProcessed

	
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_MaterialPhysicalInventory))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		MInventoryLine[] lines = getLines(false);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		//	TODO: Add up Amounts
	//	setApprovalAmt();
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
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
		
		//vpj-cd begin e-evolution recalculate the attribute instances and qty.
		MInventoryLine[] linesup = getLines(false);
		for (int i = 0; i < linesup.length; i++)
		{
			MInventoryLine line = linesup[i];
			
			String sql1 = "Delete From M_InventoryLineMA "
						+ " WHERE M_InventoryLine_ID=" +line.getM_InventoryLine_ID();
			int no = DB.executeUpdate(sql1, get_TrxName());
			log.info("MA deleted " + no);
			
			StringBuffer sql = new StringBuffer(
				"SELECT s.M_Product_ID, s.M_Locator_ID, s.M_AttributeSetInstance_ID,"
				+ " s.QtyOnHand, p.M_AttributeSet_ID "
				+ "FROM M_Product p"
				+ " INNER JOIN M_Storage s ON (s.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID) "
				+ "WHERE l.M_Warehouse_ID=?"
					+ " AND p.IsActive='Y' AND p.IsStocked='Y' and p.ProductType='I'"
					+ " AND s.M_Locator_ID=" +line.getM_Locator_ID()
					+ " AND s.M_Product_ID=" +line.getM_Product_ID()
					+ " AND s.QtyOnHand <> 0 "
				);
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
				int index = 1;
				pstmt.setInt (index++, getM_Warehouse_ID());
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					MInventoryLineMA maup = new MInventoryLineMA (line, 
							rs.getInt(3), rs.getBigDecimal(4));
					
					if (!maup.save())
						;
				}
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql.toString(), e);
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
		}
		//vpj-cd e-evolution recalculate the attribute instances and qty END.
		//
		MInventoryLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInventoryLine line = lines[i];
			if (!line.isActive())
				continue;
			
			MTransaction trx = null; 
			if (line.getM_AttributeSetInstance_ID() == 0)
			{
				BigDecimal qtyDiff = line.getQtyInternalUse().negate();
				if (qtyDiff.signum() == 0)
					qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
				//
				if (qtyDiff.signum() > 0)
				{
					//	Storage
					MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
						line.getM_Product_ID(), 0, get_TrxName());
					if (storage == null)
						storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
							line.getM_Product_ID(), 0, get_TrxName());
					BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
					log.fine("Diff=" + qtyDiff 
						+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);
					storage.setQtyOnHand(qtyNew);
					storage.setDateLastInventory(getMovementDate());
					if (!storage.save(get_TrxName()))
					{
						m_processMsg = "Storage not updated(1)";
						return DocAction.STATUS_Invalid;
					}
					log.fine(storage.toString());
					//	Transaction
					trx = new MTransaction (getCtx(), line.getAD_Org_ID(), 
						MTransaction.MOVEMENTTYPE_InventoryIn,
						line.getM_Locator_ID(), line.getM_Product_ID(), 0,
						qtyDiff, getMovementDate(), get_TrxName());
					trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
					if (!trx.save())
					{
						m_processMsg = "Transaction not inserted(1)";
						return DocAction.STATUS_Invalid;
					}
				}
				else	//	negative qty
				{
					MInventoryLineMA mas[] = MInventoryLineMA.get(getCtx(),
						line.getM_InventoryLine_ID(), get_TrxName());
					for (int j = 0; j < mas.length; j++)
					{
						MInventoryLineMA ma = mas[j];
						//	Storage
						MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
							line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
						if (storage == null)
							storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
								line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
						//
						BigDecimal maxDiff = qtyDiff;
						if (maxDiff.signum() < 0 
							&& ma.getMovementQty().compareTo(maxDiff.negate()) < 0)
							maxDiff = ma.getMovementQty().negate();
						BigDecimal qtyNew = ma.getMovementQty().add(maxDiff);	//	Storage+Diff
						log.fine("MA Qty=" + ma.getMovementQty() 
							+ ",Diff=" + qtyDiff + "|" + maxDiff 
							+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew
							+ " {" + ma.getM_AttributeSetInstance_ID() + "}");
						//
						storage.setQtyOnHand(qtyNew);
						storage.setDateLastInventory(getMovementDate());
						if (!storage.save(get_TrxName()))
						{
							m_processMsg = "Storage not updated (MA)";
							return DocAction.STATUS_Invalid;
						}
						log.fine(storage.toString());
				
						//	Transaction
						trx = new MTransaction (getCtx(), line.getAD_Org_ID(), 
							MTransaction.MOVEMENTTYPE_InventoryIn,
							line.getM_Locator_ID(), line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
							maxDiff, getMovementDate(), get_TrxName());
						trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
						if (!trx.save())
						{
							m_processMsg = "Transaction not inserted (MA)";
							return DocAction.STATUS_Invalid;
						}
						//
						qtyDiff = qtyDiff.subtract(maxDiff);
						if (qtyDiff.signum() == 0)
							break;
					}					
				}	//	negative qty
			}
			
			//	Fallback
			if (trx == null)
			{
				//	Storage
				MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
					line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
				if (storage == null)
					storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
						line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
				//
				BigDecimal qtyDiff = line.getQtyInternalUse().negate();
				if (Env.ZERO.compareTo(qtyDiff) == 0)
					qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
				BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
				log.fine("Count=" + line.getQtyCount()
					+ ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff 
					+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);
				//
				storage.setQtyOnHand(qtyNew);
				storage.setDateLastInventory(getMovementDate());
				if (!storage.save(get_TrxName()))
				{
					m_processMsg = "Storage not updated(2)";
					return DocAction.STATUS_Invalid;
				}
				log.fine(storage.toString());
			
				//	Transaction
				trx = new MTransaction (getCtx(), line.getAD_Org_ID(), 
					MTransaction.MOVEMENTTYPE_InventoryIn,
					line.getM_Locator_ID(), line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
					qtyDiff, getMovementDate(), get_TrxName());
				trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
				if (!trx.save())
				{
					m_processMsg = "Transaction not inserted(2)";
					return DocAction.STATUS_Invalid;
				}
			}	//	Fallback
			
		}	//	for all lines
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
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
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setMovementDate(new Timestamp (System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
			if (value != null)
				setDocumentNo(value);
		}
	}

	/**
	 * 	Check Material Policy.
	 * 	(NOT USED)
	 * 	Sets line ASI
	 */
	private void checkMaterialPolicy()
	{
		int no = MInventoryLineMA.deleteInventoryMA(getM_Inventory_ID(), get_TrxName());
		if (no > 0)
			log.config("Delete old #" + no);
		MInventoryLine[] lines = getLines(false);
		
		//	Incoming Trx
		MClient client = MClient.get(getCtx());
		
		//	Check Lines
		for (int i = 0; i < lines.length; i++)
		{
			MInventoryLine line = lines[i];
			boolean needSave = false;

			//	Attribute Set Instance
			if (line.getM_AttributeSetInstance_ID() == 0)
			{
				MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
				BigDecimal qtyDiff = line.getQtyInternalUse().negate();
				if (Env.ZERO.compareTo(qtyDiff) == 0)
					qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
				log.fine("Count=" + line.getQtyCount()
					+ ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff); 
				if (qtyDiff.signum() > 0)	//	In
				{
					MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
					asi.setClientOrg(getAD_Client_ID(), 0);
					asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
					if (asi.save())
					{
						line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
						needSave = true;
					}
				}
				else	//	Outgoing Trx
				{
					MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
					String MMPolicy = pc.getMMPolicy();
					if (MMPolicy == null || MMPolicy.length() == 0)
						MMPolicy = client.getMMPolicy();
					//
					MStorage[] storages = MStorage.getAllWithASI(getCtx(), 
						line.getM_Product_ID(),	line.getM_Locator_ID(), 
						MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
					BigDecimal qtyToDeliver = qtyDiff.negate();
					for (int ii = 0; ii < storages.length; ii++)
					{
						MStorage storage = storages[ii];
						if (ii == 0)
						{
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
							{
								line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
								needSave = true;
								log.config("Direct - " + line);
								qtyToDeliver = Env.ZERO;
							}
							else
							{
								log.config("Split - " + line);
								MInventoryLineMA ma = new MInventoryLineMA (line, 
									storage.getM_AttributeSetInstance_ID(),
									storage.getQtyOnHand().negate());
								if (!ma.save())
									;
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
								log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
							}
						}
						else	//	 create addl material allocation
						{
							MInventoryLineMA ma = new MInventoryLineMA (line, 
								storage.getM_AttributeSetInstance_ID(),
								qtyToDeliver.negate());
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
								qtyToDeliver = Env.ZERO;
							else
							{
								ma.setMovementQty(storage.getQtyOnHand().negate());
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
							}
							if (!ma.save())
								;
							log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
						}
						if (qtyToDeliver.signum() == 0)
							break;
					}	//	 for all storages
					
					//	No AttributeSetInstance found for remainder
					if (qtyToDeliver.signum() != 0)
					{
						MInventoryLineMA ma = new MInventoryLineMA (line, 
							0, qtyToDeliver.negate());
						if (!ma.save())
							;
						log.fine("##: " + ma);
					}
				}	//	outgoing Trx
			}	//	attributeSetInstance
			
			if (needSave && !line.save())
				log.severe("NOT saved " + line);
		}	//	for all lines

	}	//	checkMaterialPolicy

	/**
	 * 	Void Document.
	 * 	@return false 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
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
			MInventoryLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++)
			{
				MInventoryLine line = lines[i];
				BigDecimal oldCount = line.getQtyCount();
				BigDecimal oldInternal = line.getQtyInternalUse();
				if (oldCount.compareTo(line.getQtyBook()) != 0 
					|| oldInternal.signum() != 0)
				{
					line.setQtyInternalUse(Env.ZERO);
					line.setQtyCount(line.getQtyBook());
					line.addDescription("Void (" + oldCount + "/" + oldInternal + ")");
					line.save(get_TrxName());
				}
			}
		}
		else
		{
			return reverseCorrectIt();
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
	 * 	Close Document.
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;		

		setDocAction(DOCACTION_None);
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
				
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType()))
		{
			m_processMsg = "@PeriodClosed@";
			return false;
		}

		//	Deep Copy
		MInventory reversal = new MInventory(getCtx(), 0, get_TrxName());
		copyValues(this, reversal, getAD_Client_ID(), getAD_Org_ID());
		reversal.setDocStatus(DOCSTATUS_Drafted);
		reversal.setDocAction(DOCACTION_Complete);
		reversal.setIsApproved (false);
		reversal.setPosted(false);
		reversal.setProcessed(false);
		reversal.addDescription("{->" + getDocumentNo() + ")");
		if (!reversal.save())
		{
			m_processMsg = "Could not create Inventory Reversal";
			return false;
		}
		
		//	Reverse Line Qty
		MInventoryLine[] oLines = getLines(true);
		for (int i = 0; i < oLines.length; i++)
		{
			MInventoryLine oLine = oLines[i];
			MInventoryLine rLine = new MInventoryLine(getCtx(), 0, get_TrxName());
			copyValues(oLine, rLine, oLine.getAD_Client_ID(), oLine.getAD_Org_ID());
			rLine.setM_Inventory_ID(reversal.getM_Inventory_ID());
			rLine.setParent(reversal);
			//
			rLine.setQtyBook (oLine.getQtyCount());		//	switch
			rLine.setQtyCount (oLine.getQtyBook());
			rLine.setQtyInternalUse (oLine.getQtyInternalUse().negate());
			if (!rLine.save())
			{
				m_processMsg = "Could not create Inventory Reversal Line";
				return false;
			}
		}
		//
		if (!reversal.processIt(DocAction.ACTION_Complete))
		{
			m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return false;
		}
		reversal.closeIt();
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.save();
		m_processMsg = reversal.getDocumentNo();
		
		//	Update Reversed (this)
		addDescription("(" + reversal.getDocumentNo() + "<-)");
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		
		return true;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;	
		
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
			.append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
			.append(" (#").append(getLines(false).length).append(")");
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
	
}	//	MInventory

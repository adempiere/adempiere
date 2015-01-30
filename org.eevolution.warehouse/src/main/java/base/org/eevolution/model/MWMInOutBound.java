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
package org.eevolution.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Class Model for In & Out Bound Operation
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBound extends X_WM_InOutBound implements DocAction
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7216075035497599383L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MWMInOutBound.class);
	/**	Order Lines					*/
	private MWMInOutBoundLine[]	m_lines = null;
	
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
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		/*if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}*/
		
		//	Lines
		MWMInOutBoundLine[] lines = getLines(true, MWMInOutBoundLine.COLUMNNAME_Line);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		// Bug 1564431
		if (getDeliveryRule() != null && getDeliveryRule().equals(MWMInOutBound.DELIVERYVIARULE_Delivery)) 
		{
			for (MWMInOutBoundLine line:lines) 
			{
				MProduct product = line.getMProduct();
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
		MWMInOutBoundLine[] lines = getLines(true, MWMInOutBoundLine.COLUMNNAME_Line);
		for (MWMInOutBoundLine line : lines)
		{
			line.setProcessed(true);
			line.saveEx();
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

	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
				
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} //	voidIt

	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
				
			
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		setDocStatus(DOCSTATUS_Closed);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} //	closeIt

	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return voidIt();
	} //	reverseCorrectionIt

	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	} //	reverseAccrualIt

	public boolean reActivateIt()
	{
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;		
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
	} //	reActivateIt
	
	/**
	 * get MWMInOutBoundLine lines
	 * @param requery
	 * @param orderBy
	 * @return Array with MWMInOutBoundLine
	 */
	public MWMInOutBoundLine[] getLines (boolean requery, String orderBy)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		m_lines = getLines(null, orderClause);
		return m_lines;
	}	//	getLines
	
	/**
	 * get MWMInOutBoundLine lines 
	 * @param where
	 * @param orderClause
	 * @return Array with MWMInOutBoundLine
	 */
	public MWMInOutBoundLine[] getLines (String where, String orderClause)
	{
		StringBuffer whereClause = new StringBuffer(MWMInOutBoundLine.COLUMNNAME_WM_InOutBound_ID+"=?");
		if (!Util.isEmpty(where, true))
			whereClause.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MWMInOutBoundLine.COLUMNNAME_Line;
		//
		List<MWMInOutBoundLine> list = new Query(getCtx(), MWMInOutBoundLine.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(new Object[]{get_ID()})
										.setOrderBy(orderClause)
										.list();	
		return list.toArray(new MWMInOutBoundLine[list.size()]);		
	}	//	getLines
}	

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
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Class Model for Inbound & Outbound Operation
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBound extends X_WM_InOutBound implements DocAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3097286111159086274L;
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
		
		//	Convert DocType to Target
		if (getC_DocType_ID() != getC_DocType_ID() )
		{
			//	Cannot change Std to anything else if different warehouses
			if (getC_DocType_ID() != 0)
			{
				MDocType dtOld = MDocType.get(getCtx(), getC_DocType_ID());
				if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dtOld.getDocSubTypeSO())		//	From SO
					&& !MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO()))	//	To !SO
				{
					for (MWMInOutBoundLine line:lines)
					{
						/*if (line.getM_Warehouse_ID() != getM_Warehouse_ID())
						{
							log.warning("different Warehouse " + line);
							m_processMsg = "@CannotChangeDocType@";
							return DocAction.STATUS_Invalid;
						}*/
					}
				}
			}
			
			//	New or in Progress/Invalid
			if (DOCSTATUS_Drafted.equals(getDocStatus()) 
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| getC_DocType_ID() == 0)
			{
				setC_DocType_ID(getC_DocType_ID());
			}
			else	//	convert only if offer
			{
				if (dt.isOffer())
					setC_DocType_ID(getC_DocType_ID());
				else
				{
					m_processMsg = "@CannotChangeDocType@";
					return DocAction.STATUS_Invalid;
				}
			}
		}	//	convert DocType

		//	Mandatory Product Attribute Set Instance
		String mandatoryType = "='Y'";	//	IN ('Y','S')
		String sql = "SELECT COUNT(*) "
			+ "FROM C_OrderLine ol"
			+ " INNER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)" 
			+ " INNER JOIN M_AttributeSet pas ON (p.M_AttributeSet_ID=pas.M_AttributeSet_ID) "
			+ "WHERE pas.MandatoryType" + mandatoryType		
			+ " AND (ol.M_AttributeSetInstance_ID is NULL OR ol.M_AttributeSetInstance_ID = 0)"
			+ " AND ol.C_Order_ID=?";
		int no = DB.getSQLValue(get_TrxName(), sql, getWM_InOutBound_ID());
		if (no != 0)
		{
			m_processMsg = "@LinesWithoutProductAttribute@ (" + no + ")";
			return DocAction.STATUS_Invalid;
		}

		/*if (!reserveStock(dt, lines))
		{
			m_processMsg = "Cannot reserve Stock";
			return DocAction.STATUS_Invalid;
		}*/
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
	//	if (!DOCACTION_Complete.equals(getDocAction()))		don't set for just prepare 
	//		setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt

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
	
	public MWMInOutBoundLine[] getLines (String where, String orderClause)
	{
		StringBuffer whereClause = new StringBuffer(MWMInOutBoundLine.COLUMNNAME_WM_InOutBound_ID+"=?");
		if (!Util.isEmpty(where, true))
			whereClause.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MWMInOutBoundLine.COLUMNNAME_Line;
		//
		return (MWMInOutBoundLine[]) new Query(getCtx(), MWMInOutBoundLine.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(new Object[]{get_ID()})
										.setOrderBy(orderClause)
										.list()
										.toArray();		
	}	//	getLines
	

	public boolean processIt(String action) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}	

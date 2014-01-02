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
package org.adempiere.model;

import java.util.List;

import org.adempiere.model.engines.CostEngineFactory;
import org.compiere.model.I_M_Transaction;
import org.compiere.model.MClient;
import org.compiere.model.MCostType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProductionPlan;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MRole;
import org.compiere.model.MTransaction;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;


/**
 *	Validator Example Implementation
 *	
 *	@author Jorg Janke
 *	@version $Id: MyValidator.java,v 1.2 2006/07/30 00:51:57 jjanke Exp $
 */
public class CEValidator implements ModelValidator
{
	/**
	 *	Constructor.
	 */
	public CEValidator ()
	{
		super ();
	}	//	MyValidator
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(CEValidator.class);
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	/** User	*/
	private int		m_AD_User_ID = -1;
	/** Role	*/
	private int		m_AD_Role_ID = -1;
	private int 	m_costtype_id_AverageInvoice = -1;
	private String 	sqlCosttype_ID = " and m_costtype_ID=";
	
	
	/**
	 *	Initialize Validation
	 *	@param engine validation engine 
	 *	@param client client
	 */
	public void initialize (ModelValidationEngine engine, MClient client)
	{
		//client = null for global validator
		if (client != null) {	
			m_AD_Client_ID = client.getAD_Client_ID();
			log.info(client.toString());
		}
		else  {
			log.info("Initializing global validator: "+this.toString());
		}
		
		//	We want to be informed when C_Order is created/changed
		//	We want to validate Order before preparing 
		engine.addDocValidate(MOrder.Table_Name, this);
		engine.addDocValidate(MInvoice.Table_Name, this);
		engine.addDocValidate(MInOut.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
		engine.addDocValidate(MProduction.Table_Name, this);
		engine.addDocValidate(MInventory.Table_Name, this);
		engine.addDocValidate(MProjectIssue.Table_Name, this);
		engine.addDocValidate(MPPCostCollector.Table_Name, this);
		engine.addDocValidate(MMatchInv.Table_Name, this);

	}	//	initialize

    /**
     *	Model Change of a monitored Table.
     *	Called after PO.beforeSave/PO.beforeDelete
     *	when you called addModelChange for the table
     *	@param po persistent object
     *	@param type TYPE_
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
     */
	public String modelChange (PO po, int type) throws Exception
	{	return "";
	}	//	modelChange
	
	/**
	 *	Validate Document.
	 *	Called as first step of DocAction.prepareIt 
     *	when you called addDocValidate for the table.
     *	Note that totals, etc. may not be correct.
	 *	@param po persistent object
	 *	@param timing see TIMING_ constants
     *	@return error message or null
	 */
	public String docValidate (PO po, int timing)
	{
		String error = null;
		log.info(po.get_TableName() + " Timing: "+timing);

		if (timing == TIMING_AFTER_COMPLETE) {	
			{
				if (m_costtype_id_AverageInvoice == -1)
				m_costtype_id_AverageInvoice = 
						MCostType.getByMethodCosting(po.getCtx(), MCostType.COSTINGMETHOD_AverageInvoice, 
								po.get_TrxName(), po.getAD_Client_ID()).getM_CostType_ID();
			}
			sqlCosttype_ID = " and m_costtype_ID=" + m_costtype_id_AverageInvoice;
			if (po.get_TableName().equals(MInOut.Table_Name))
			{
				error = calculateMInout((MInOut)po);
			}
			else if (po.get_TableName().equals(MOrder.Table_Name))
			{
				//error = testValidator((MOrder)po);
			}

			else if (po.get_TableName().equals(MInventory.Table_Name))
			{
				error = calculateMInventory((MInventory)po);
			}

			else if (po.get_TableName().equals(MMovement.Table_Name))
			{
				error = calculateMMovement((MMovement)po);
			}
			else if (po.get_TableName().equals(MProduction.Table_Name))
			{
				MProduction prod = new MProduction(po.getCtx(), po.get_ID(), po.get_TrxName());
				error = calculateMProduction(prod);
			}
			else if (po.get_TableName().equals(MProjectIssue.Table_Name))
			{
				error = calculateMProjectIssue((MProjectIssue)po);
			}
			else if (po.get_TableName().equals(MPPCostCollector.Table_Name))
			{
				error = calculateCostCollector((MPPCostCollector)po);
			}
			else if (po.get_TableName().equals(MInvoice.Table_Name))
			{
				error = calculateLandedCost((MInvoice)po);
			}
			else if (po.get_TableName().equals(MMatchInv.Table_Name))
			{
				error = calculateMMatchInv((MMatchInv)po);
			}
		}
		if (timing == TIMING_PREPAREPOST) {	
			
			sqlCosttype_ID = " and m_costtype_ID=" + m_costtype_id_AverageInvoice;
			if (po.get_TableName().equals(MInOut.Table_Name))
			{
				error = calculateMInoutBeforePosting((MInOut)po);
			}
			else if (po.get_TableName().equals(MOrder.Table_Name))
			{
				//error = testValidator((MOrder)po);
			}

			else if (po.get_TableName().equals(MInventory.Table_Name))
			{
				error = calculateMInventoryBeforePosting((MInventory)po);
			}

			else if (po.get_TableName().equals(MMovement.Table_Name))
			{
				error = calculateMMovementBeforPosting((MMovement)po);
			}
			else if (po.get_TableName().equals(MProduction.Table_Name))
			{
				MProduction prod = new MProduction(po.getCtx(), po.get_ID(), po.get_TrxName());
				error = calculateMProductionBeforePosting(prod);
			}
			else if (po.get_TableName().equals(MProjectIssue.Table_Name))
			{
				error = calculateMProjectIssueBeforePosting((MProjectIssue)po);
			}
			else if (po.get_TableName().equals(MPPCostCollector.Table_Name))
			{
				error = calculateCostCollectorBeforePosting((MPPCostCollector)po);
			}
			else if (po.get_TableName().equals(MInvoice.Table_Name))
			{
				error = calculateLandedCostBeforePosting((MInvoice)po);
			}
			else if (po.get_TableName().equals(MMatchInv.Table_Name))
			{
				error = calculateMMatchInvBeforePosting((MMatchInv)po);
			}
		}
		return error;
	}	//	docValidate	
	
	
	
	/**
	 *	User Login.
	 *	Called when preferences are set
	 *	@param AD_Org_ID org
	 *	@param AD_Role_ID role
	 *	@param AD_User_ID user
	 *	@return error message or null
	 */
	public String login (int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		log.info("AD_User_ID=" + AD_User_ID);
		m_AD_User_ID = AD_User_ID;
		m_AD_Role_ID = AD_Role_ID;
		return null;
	}	//	login

	/**
	 *	Get Client to be monitored
	 *	@return AD_Client_ID client
	 */
	public int getAD_Client_ID()
	{
		return m_AD_Client_ID;
	}	//	getAD_Client_ID

	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("CEValidator");
		return sb.toString ();
	}	//	toString

	/**
	 * Sample Validator Before Save Properties - to set mandatory properties on users
	 * avoid users changing propertiesconfirm
	 */
	public void beforeSaveProperties() {
		// not for SuperUser or role SysAdmin
		if (   m_AD_User_ID == 0  // System
			|| m_AD_User_ID == 100   // SuperUser
			|| m_AD_Role_ID == 0  // System Administrator
			|| m_AD_Role_ID == 1000000)  // ECO Admin
			return;

		log.info("Setting default Properties");

		MRole role = MRole.get(Env.getCtx(), m_AD_Role_ID);

		// Example - if you don't want user to select auto commit property
		// Ini.setProperty(Ini.P_A_COMMIT, false);
		
		// Example - if you don't want user to select auto login
		// Ini.setProperty(Ini.P_A_LOGIN, false);

		// Example - if you don't want user to select store password
		// Ini.setProperty(Ini.P_STORE_PWD, false);

		// Example - if you want your user inherit ALWAYS the show accounting from role
		// Ini.setProperty(Ini.P_SHOW_ACCT, role.isShowAcct());
		
		// Example - if you want to avoid your user from changing the working date
		/*
		Timestamp DEFAULT_TODAY =	new Timestamp(System.currentTimeMillis());
		//  Date (remove seconds)
		DEFAULT_TODAY.setHours(0);
		DEFAULT_TODAY.setMinutes(0);
		DEFAULT_TODAY.setSeconds(0);
		DEFAULT_TODAY.setNanos(0);
		Ini.setProperty(Ini.P_TODAY, DEFAULT_TODAY.toString());
		Env.setContext(Env.getCtx(), "#Date", DEFAULT_TODAY);
		*/
		

	}	// beforeSaveProperties
	
	
	private String calculateMInout(MInOut inout)
	{
		Trx trx = Trx.get(inout.get_TrxName(), true);
		trx.start();
		for (MInOutLine sLine:inout.getLines())
		{
			String delete = "Delete from m_costdetail where m_transaction_ID=?" + sqlCosttype_ID;

			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MInOutLine.Table_Name))
			{
				if (mtrx== null)
					continue;
				//int no = DB.executeUpdate(delete, mtrx.getM_Transaction_ID(), trx.getTrxName());
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
				trx.commit();
			}			
		}
		return "";
	}

	private String calculateMInoutBeforePosting(MInOut inout)
	{
		Trx trx = Trx.get(inout.get_TrxName(), true);
		trx.start();
		String exists = "select count(*) from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MInOutLine sLine:inout.getLines())
		{

			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MInOutLine.Table_Name))
			{
				if (mtrx== null)
					continue;				
				int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
				if (no==0)
				{
					CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					trx.commit();
					
				}
			}			
		}
		return "";
	}

	private String calculateMInventoryBeforePosting(MInventory inv)
	{
		Trx trx = Trx.get(inv.get_TrxName(), true);
		trx.start();
		String exists = "select count(*) from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MInventoryLine sLine:inv.getLines(true))
		{
			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MInventoryLine.Table_Name))
			{
				//Trx trx = Trx.get(mtrx.get_TrxName(), true);
				//trx.start();
				int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
				if (no==0)
				{
					CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					trx.commit();
					
				}
			}			
		}
		return "";
	}

	private String calculateMInventory(MInventory inv)
	{
		Trx trx = Trx.get(inv.get_TrxName(), true);
		trx.start();
		for (MInventoryLine sLine:inv.getLines(true))
		{
			String delete = "Delete from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MInventoryLine.Table_Name))
			{
				//Trx trx = Trx.get(mtrx.get_TrxName(), true);
				//trx.start();
				//DB.executeUpdate(delete, mtrx.getM_Transaction_ID(),trx.getTrxName());
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
				trx.commit();
			}			
		}
		return "";
	}
	

	private String calculateMProjectIssue(MProjectIssue issue)
	{
		String delete = "Delete from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MTransaction mtrx:trxs_getByDocumentLine(issue, MProjectIssue.Table_Name))
		{
			Trx trx = Trx.get(mtrx.get_TrxName(), true);
			trx.start();
			//DB.executeUpdate(delete, mtrx.getM_Transaction_ID(), issue.get_TrxName());
			CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
			trx.commit();
			trx.close();
		}	
		return "";
	}
	


	private String calculateMProjectIssueBeforePosting(MProjectIssue issue)
	{
		String exists = "select count(*) from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MTransaction mtrx:trxs_getByDocumentLine(issue, MProjectIssue.Table_Name))
		{
			Trx trx = Trx.get(mtrx.get_TrxName(), true);
			trx.start();
			int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
			if (no==0)
			{
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
				trx.commit();				
			}
		}	
		return "";
	}


	private String calculateCostCollectorBeforePosting(MPPCostCollector cc)
	{
		if (cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue) 
				|| cc.getPP_Order().getDocStatus().equals(MPPOrder.DOCSTATUS_Closed))
		{
			Trx trx = Trx.get(cc.get_TrxName(), true);
			trx.start();
			String exists = "select count(*) from m_costdetail where pp_cost_collector_ID=?"+ sqlCosttype_ID;
			for (MTransaction mtrx:trxs_getByDocumentLine(cc, MPPCostCollector.Table_Name))
			{
				int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
				if (no==0)
				{
					if (cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
						CostEngineFactory.getCostEngine(getAD_Client_ID()).createActivityControl(cc);
					else
						CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					trx.commit();				
				}
			}
		}
		else
			return "CC not Closed";
		return "";
	}
	

	private String calculateCostCollector(MPPCostCollector cc)
	{
		if (cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt) 
				&& !cc.getPP_Order().getDocStatus().equals(MPPOrder.DOCSTATUS_Closed))
			return "";
		Trx trx = Trx.get(cc.get_TrxName(), true);
		trx.start();
		String delete = "Delete from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MTransaction mtrx:trxs_getByDocumentLine(cc, MPPCostCollector.Table_Name))
		{
			//DB.executeUpdate(delete, mtrx.getM_Transaction_ID(), cc.get_TrxName());
			CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
			trx.commit();
		}	
		return "";
	}

	private String calculateMMovementBeforPosting(MMovement movement)
	{
		String exists = "select count(*) from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		Trx trx = Trx.get(movement.get_TrxName(), true);
		trx.start();
		for (MMovementLine sLine:movement.getLines(true))
		{
			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MMovementLine.Table_Name))
			{
				int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
				if (no==0)
				{
					CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					trx.commit();					
				}
			}			
		}
		return "";
	}
	

	private String calculateMMovement(MMovement movement)
	{
		Trx trx = Trx.get(movement.get_TrxName(), true);
		trx.start();
		for (MMovementLine sLine:movement.getLines(true))
		{
			String delete = "Delete from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
			for (MTransaction mtrx:trxs_getByDocumentLine(sLine, MMovementLine.Table_Name))
			{
				//DB.executeUpdate(delete, mtrx.getM_Transaction_ID(), sLine.get_TrxName());
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
				trx.commit();
			}			
		}
		return "";
	}
	

	private String calculateMProduction(MProduction prod)
	{
		Trx trx = Trx.get(prod.get_TrxName(), true);
		trx.start();
		for (MProductionPlan plan:prod.getLines(true))
		{
			for (MProductionLine pline:plan.getLines(true, MProductionLine.COLUMNNAME_MovementQty + " asc," + MProductionLine.COLUMNNAME_Line))
			{
				String delete = "Delete from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
				for (MTransaction mtrx:trxs_getByDocumentLine(pline, MProductionLine.Table_Name))
				{
					//int no = DB.executeUpdate(delete, mtrx.getM_Transaction_ID(), pline.get_TrxName());
					CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					trx.commit();
				}			
				
			}
		}
		return "";
	}
	


	private String calculateMProductionBeforePosting(MProduction prod)
	{
		Trx trx = Trx.get(prod.get_TrxName(), true);
		trx.start();
		String exists = "select count(*) from m_costdetail where m_transaction_ID=?"+ sqlCosttype_ID;
		for (MProductionPlan plan:prod.getLines(true))
		{
			for (MProductionLine pline:plan.getLines(true, MProductionLine.COLUMNNAME_MovementQty + " asc," + MProductionLine.COLUMNNAME_Line))
			{
				for (MTransaction mtrx:trxs_getByDocumentLine(pline, MProductionLine.Table_Name))
				{
					int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
					if (no==0)
					{
						CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
						trx.commit();						
					}
				}			
				
			}
		}
		return "";
	}
	
	private String calculateLandedCostBeforePosting(MInvoice inv)
	{
		Trx trx = Trx.get(inv.get_TrxName(), true);
		trx.start();
		if (inv.isSOTrx())
			return "";
		for (MInvoiceLine iLine:inv.getLines())
		{
			MLandedCostAllocation[] lcas = MLandedCostAllocation.getOfInvoiceLine(
					inv.getCtx(), iLine.getC_InvoiceLine_ID(), inv.get_TrxName());
			String exists = "select count(*) from m_costdetail where c_landedcostallocation_ID=?"+ sqlCosttype_ID;
			for (MLandedCostAllocation allocation:lcas)
			{
				int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
				if (no==0)
				{
					MInOutLine ioLine = (MInOutLine) allocation.getM_InOutLine();
					for (MTransaction mtrx: MTransaction.getByInOutLine(ioLine))
					{
							CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx, allocation);
							trx.commit();						
					}		
					
				}
			}
		}
		return "";
	}	
	private String calculateLandedCost(MInvoice inv)
	{
		Trx trx = Trx.get(inv.get_TrxName(), true);
		trx.start();
		if (inv.isSOTrx())
			return "";
		for (MInvoiceLine iLine:inv.getLines())
		{
			MLandedCostAllocation[] lcas = MLandedCostAllocation.getOfInvoiceLine(
					inv.getCtx(), iLine.getC_InvoiceLine_ID(), inv.get_TrxName());
			String delete = "Delete from m_costdetail where c_landedcostallocation_ID=?"+ sqlCosttype_ID;
			for (MLandedCostAllocation allocation:lcas)
			{
				//int no = DB.executeUpdate(delete,allocation.getC_LandedCostAllocation_ID() , inv.get_TrxName());
				MInOutLine ioLine = (MInOutLine) allocation.getM_InOutLine();
				for (MTransaction mtrx: MTransaction.getByInOutLine(ioLine))
				{
						CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx, allocation);
						trx.commit();						
				}		
			}
		}
		return "";
	}

	private String calculateMMatchInvBeforePosting(MMatchInv m_inv)
	{	

		Trx trx = Trx.get(m_inv.get_TrxName(), true);
		trx.start();
		MInOutLine inout_line = (MInOutLine)m_inv.getM_InOutLine();
		for (MTransaction mtrx: MTransaction.getByInOutLine(inout_line))
		{
			int no = 0;//DB.getSQLValue(trx.getTrxName(), exists, new Object[]{mtrx.getM_Transaction_ID()});
			if (no==0)
			{
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx, m_inv);
				trx.commit();										
			}
		}
		return "";
	}
	private String calculateMMatchInv(MMatchInv m_inv)
	{	

		Trx trx = Trx.get(m_inv.get_TrxName(), true);
		trx.start();
		MInOutLine inout_line = (MInOutLine) m_inv.getM_InOutLine();
		String delete = "Delete from m_costdetail where m_inoutline_ID=? and c_invoiceline_id=?"+ sqlCosttype_ID;
		for (MTransaction mtrx: MTransaction.getByInOutLine(inout_line))
			{
				//DB.executeUpdateEx(delete,new Object[]{m_inv.getM_InOutLine_ID(), m_inv.getC_InvoiceLine_ID()} , m_inv.get_TrxName());
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx, m_inv);
			}
		return "";
	}
	

	public  MTransaction[] trxs_getByDocumentLine(PO po, String tablename)
	{
		final String column_id = tablename + "_ID";	
		MTransaction[]	mtrxs = null;
		int id = po.get_ID();
		if (po instanceof MPPCostCollector)
		{
			MPPCostCollector cc = (MPPCostCollector)po;
			if ((cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt)
					|| cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue)))
			{
				final String whereClause = column_id + "=?";
				List<MTransaction> list = new Query (po.getCtx(), I_M_Transaction.Table_Name, whereClause, po.get_TrxName())
				.setClient_ID()
				.setParameters(po.get_ID())
				.list();
				mtrxs = list.toArray(new MTransaction[list.size()]);
			}
			else
			{
				String whereClause = " exists (select 1 from pp_cost_collector pc" +
						" where pc.pp_cost_collector_ID=m_transaction.pp_Cost_collector_ID and costcollectortype =? " +
						" and pc.pp_order_ID=?)";
				List<MTransaction> cclist = new Query(po.getCtx(), MTransaction.Table_Name, whereClause, po.get_TrxName())
				.setParameters(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt,cc.getPP_Order_ID())
				.list();
				mtrxs = cclist.toArray(new MTransaction[cclist.size()]);
			}
		}
		else
		{
			final String whereClause = column_id + "=?";
			List<MTransaction> list = new Query (po.getCtx(), I_M_Transaction.Table_Name, whereClause, po.get_TrxName())
			.setClient_ID()
			.setParameters(po.get_ID())
			.list();
			mtrxs = list.toArray(new MTransaction[list.size()]);
		}
		return mtrxs;
	}
	
	
	
	
	

	

	
	
	
	

}	//	MyValidator

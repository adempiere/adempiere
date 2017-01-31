/******************************************************************************
// * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.acct;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post Shipment/Receipt Documents.
 *  <pre>
 *  Table:              M_InOut (319)
 *  Document Types:     MMS, MMR
 *  </pre>
 *  @author Jorg Janke
 *  @author Armen Rizal, Goodwill Consulting
 * 			<li>BF [ 1745154 ] Cost in Reversing Material Related Docs
 * 			<li>BF [ 2858043 ] Correct Included Tax in Average Costing
 *  @version  $Id: Doc_InOut.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_InOut extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_InOut (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MInOut.class, rs, null, trxName);
	}   //  DocInOut

	private int				m_Reversal_ID = 0;
	private String			m_DocStatus = "";
	
	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID(NO_CURRENCY);
		MInOut inout = (MInOut)getPO();
		setDateDoc (inout.getMovementDate());
		m_Reversal_ID = inout.getReversal_ID();//store original (voided/reversed) document
		m_DocStatus = inout.getDocStatus();
		//	Contained Objects
		p_lines = loadLines(inout);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load InOut Line
	 *	@param inout shipment/receipt
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MInOut inout)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MInOutLine[] lines = inout.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInOutLine line = lines[i];
			if (line.isDescription() 
				|| line.getM_Product_ID() == 0
				|| line.getMovementQty().signum() == 0)
			{
				log.finer("Ignored: " + line);
				continue;
			}
			
			DocLine docLine = new DocLine (line, this);
			BigDecimal Qty = line.getMovementQty();
			docLine.setReversalLine_ID(line.getReversalLine_ID());		
			docLine.setQty (Qty, getDocumentType().equals(DOCTYPE_MatShipment));    //  sets Trx and Storage Qty
			
			//Define if Outside Processing 
			String sql = "SELECT PP_Cost_Collector_ID  FROM C_OrderLine WHERE C_OrderLine_ID=? AND PP_Cost_Collector_ID IS NOT NULL";
			int PP_Cost_Collector_ID = DB.getSQLValueEx(getTrxName(), sql, new Object[]{line.getC_OrderLine_ID()});
			docLine.setPP_Cost_Collector_ID(PP_Cost_Collector_ID);
			//
			log.fine(docLine.toString());
			list.add (docLine);
		}

		//	Return Array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	/**
	 *  Get Balance
	 *  @return Zero (always balanced)
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  MMS, MMR.
	 *  <pre>
	 *  Shipment
	 *      CoGS (RevOrg)   DR
	 *      Inventory               CR
	 *  Shipment of Project Issue
	 *      CoGS            DR
	 *      Project                 CR
	 *  Receipt
	 *      Inventory       DR
	 *      NotInvoicedReceipt      CR
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());

		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;

		//  *** Sales - Shipment
		if (getDocumentType().equals(DOCTYPE_MatShipment) && isSOTrx())
		{
			BigDecimal total = Env.ZERO;
			for (int i = 0; i < p_lines.length; i++)
			{
				DocLine line = p_lines[i];
				BigDecimal costs = null;			
				MProduct product = line.getProduct();
				
				for (MCostDetail cost :  line.getCostDetail(as,false))
				{	 
					if (!MCostDetail.existsCost(cost))
						continue;
					
					costs = MCostDetail.getTotalCost(cost, as);
					total = total.add(costs);
					
					//get costing method for product
					String description = cost.getM_CostElement().getName()
							+ " " + cost.getM_CostType().getName();
					
					//  CoGS            DR
					dr = fact.createLine(line,
							line.getAccount(ProductCost.ACCTTYPE_P_Cogs, as),
							as.getC_Currency_ID(), costs, null);
					if (dr == null)
					{
						p_Error = "FactLine DR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}
					dr.setM_Locator_ID(line.getM_Locator_ID());
					dr.setLocationFromLocator(line.getM_Locator_ID(), true);    //  from Loc
					dr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  //  to Loc
					dr.setAD_Org_ID(line.getOrder_Org_ID());		//	Revenue X-Org
                    dr.setM_Product_ID(cost.getM_Product_ID());
					dr.setQty(cost.getQty().negate());
					dr.addDescription(description);
					if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) 
							&& m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
					{
						//	Set AmtAcctDr from Original Shipment/Receipt
						if (!dr.updateReverseLine (MInOut.Table_ID, 
								m_Reversal_ID, line.getReversalLine_ID() , cost.getQty().negate() , Env.ONE.negate()))
						{
							p_Error = "Original Shipment/Receipt not posted yet";
							return null;
						}
					}
				
					//  Inventory               CR
					cr = fact.createLine(line,
							line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
							as.getC_Currency_ID(), null, costs);
					if (cr == null)
					{
						p_Error = "FactLine CR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}
					cr.setM_Locator_ID(line.getM_Locator_ID());
					cr.setLocationFromLocator(line.getM_Locator_ID(), true);    // from Loc
					cr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  // to Loc
					cr.addDescription(description);
                    cr.setM_Product_ID(cost.getM_Product_ID());
                    cr.setQty(cost.getQty());
					if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) 
							&& m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
					{
						//	Set AmtAcctCr from Original Shipment/Receipt
						if (!cr.updateReverseLine (MInOut.Table_ID, 
								m_Reversal_ID, line.getReversalLine_ID(),cost.getQty() ,Env.ONE.negate()))
						{
							p_Error = "Original Shipment/Receipt not posted yet";
							return null;
						}
						costs = cr.getAcctBalance(); //get original cost
					}
				} // costing elements
				if (total == null || total.signum() == 0)	//	zero costs OK
				{
					/*if (product.isStocked())
					{
						p_Error = "No Costs for " + line.getProduct().getName();
						log.log(Level.WARNING, p_Error);
						return null;
					}
					else	//	ignore service
						continue;
					*/	
				}
			}	//	for all linesQty


			/** Commitment release										****/
			if (as.isAccrual() && as.isCreateSOCommitment())
			{
				for (int i = 0; i < p_lines.length; i++)
				{
					DocLine line = p_lines[i];
					BigDecimal multiplier = Env.ONE.negate();
					if (m_Reversal_ID != 0 && m_Reversal_ID < get_ID())
						multiplier = multiplier.negate();
					Fact factcomm = Doc_Order.getCommitmentSalesRelease(as, this, 
						line.getQty(), line.get_ID(), multiplier);
					if (factcomm != null)
						facts.add(factcomm);
				}
			}	//	Commitment
		
		}	//	Shipment
        //	  *** Sales - Return
		else if ( getDocumentType().equals(DOCTYPE_MatReceipt) && isSOTrx() )
		{
			BigDecimal total = Env.ZERO;
			for (int i = 0; i < p_lines.length; i++)
			{
				DocLine line = p_lines[i];
				BigDecimal costs = null;				
				MProduct product = line.getProduct();
				for (MCostDetail cost : line.getCostDetail(as, false))
				{	
					if (!MCostDetail.existsCost(cost))
						continue;
					
					costs = MCostDetail.getTotalCost(cost, as);
					
					total = total.add(costs);
					
					String description = cost.getM_CostElement().getName() +" "+ cost.getM_CostType().getName();
										
					//  Inventory               DR
					dr = fact.createLine(line,
						line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
						as.getC_Currency_ID(), costs, null);
					if (dr == null)
					{
						p_Error = "FactLine DR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}
					dr.setM_Locator_ID(line.getM_Locator_ID());
					dr.setLocationFromLocator(line.getM_Locator_ID(), true);    // from Loc
					dr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  // to Loc
					dr.addDescription(description);
                    dr.setM_Product_ID(cost.getM_Product_ID());
                    dr.setQty(cost.getQty());
					if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) 
							&& m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
					{
						//	Set AmtAcctDr from Original Shipment/Receipt
						if (!dr.updateReverseLine (MInOut.Table_ID, 
								m_Reversal_ID, line.getReversalLine_ID() , cost.getQty() ,Env.ONE.negate()))
						{
							p_Error = "Original Shipment/Receipt not posted yet";
							return null;
						}
						costs = dr.getAcctBalance(); //get original cost
					}			
					//  CoGS            CR
					cr = fact.createLine(line,
						line.getAccount(ProductCost.ACCTTYPE_P_Cogs, as),
						as.getC_Currency_ID(), null, costs);
					if (cr == null)
					{
						p_Error = "FactLine CR not created: " + line;
						log.log(Level.WARNING, p_Error);
						return null;
					}
					cr.setM_Locator_ID(line.getM_Locator_ID());
					cr.setLocationFromLocator(line.getM_Locator_ID(), true);    //  from Loc
					cr.setLocationFromBPartner(getC_BPartner_Location_ID(), false);  //  to Loc
					cr.setAD_Org_ID(line.getOrder_Org_ID());		//	Revenue X-Org
                    cr.setM_Product_ID(cost.getM_Product_ID());
					cr.setQty(cost.getQty().negate());
					cr.addDescription(description);
					if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) 
							&& m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
					{
						//	Set AmtAcctCr from Original Shipment/Receipt
						if (!cr.updateReverseLine (MInOut.Table_ID, 
								m_Reversal_ID, line.getReversalLine_ID(),cost.getQty().negate() , Env.ONE.negate()))
						{
							p_Error = "Original Shipment/Receipt not posted yet";
							return null;
						}					
					}
				}
				
				if (total == null || total.signum() == 0)	//	zero costs OK
				{
					/*if (product.isStocked())
					{
						p_Error = "No Costs for " + line.getProduct().getName();
						log.log(Level.WARNING, p_Error);
						return null;
					}
					else	//	ignore service
						continue;
					*/	
				}
			}	//	for all lines
		}	//	Sales Return
		
		//  *** Purchasing - Receipt
		else if (getDocumentType().equals(DOCTYPE_MatReceipt) && !isSOTrx())
		{
			BigDecimal total = Env.ZERO;
			for (int i = 0; i < p_lines.length; i++)
			{
				int C_Currency_ID = as.getC_Currency_ID();
				//
				DocLine line = p_lines[i];
				BigDecimal costs = null;
				MProduct product = line.getProduct();
				for (MCostDetail cost : line.getCostDetail(as, true))
				{	
						if (!MCostDetail.existsCost(cost))
							continue;
						
						costs = MCostDetail.getTotalCost(cost, as);
						
						total = total.add(costs);
						
						String description = cost.getM_CostElement().getName() +" "+ cost.getM_CostType().getName();						
						//  Inventory/Asset			DR
						MAccount assets = line.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
						if (product.isService())
						{	
							//if the line is a Outside Processing then DR WIP
							if(line.getPP_Cost_Collector_ID() > 0)
								assets = line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);	
							else	
								assets = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
							
						}
						dr = fact.createLine(line, assets,
							C_Currency_ID, costs, null);
						dr.addDescription(description);
						//
						if (dr == null)
						{
							p_Error = "DR not created: " + line;
							log.log(Level.WARNING, p_Error);
							return null;
						}
						dr.setM_Locator_ID(line.getM_Locator_ID());
						dr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   // from Loc
						dr.setLocationFromLocator(line.getM_Locator_ID(), false);   // to Loc
                        dr.setM_Product_ID(cost.getM_Product_ID());
                        dr.setQty(cost.getQty());
						if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) && m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
						{
							//	Set AmtAcctDr from Original Shipment/Receipt
							if (!dr.updateReverseLine (MInOut.Table_ID, 
									m_Reversal_ID, line.getReversalLine_ID() , cost.getQty() ,Env.ONE.negate()))
							{
								p_Error = "Original Receipt not posted yet";
								return null;
							}
						}
						
						cr = fact.createLine(line,
							getAccount(Doc.ACCTTYPE_NotInvoicedReceipts, as),
							C_Currency_ID, null, costs);
						cr.addDescription(description);
						//
						if (cr == null)
						{
							p_Error = "CR not created: " + line;
							log.log(Level.WARNING, p_Error);
							return null;
						}
						cr.setM_Locator_ID(line.getM_Locator_ID());
						cr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   //  from Loc
						cr.setLocationFromLocator(line.getM_Locator_ID(), false);   //  to Loc
                        cr.setM_Product_ID(cost.getM_Product_ID());
						cr.setQty(cost.getQty().negate());
						if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) && m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
						{
							//	Set AmtAcctCr from Original Shipment/Receipt
							if (!cr.updateReverseLine (MInOut.Table_ID, 
									m_Reversal_ID, line.getReversalLine_ID(),cost.getQty().negate(),Env.ONE.negate()))
							{
								p_Error = "Original Receipt not posted yet";
								return null;
							}
						}
						cost.setProcessed(true);
						cost.saveEx();
					}
					
					/*if (total == null || total.signum() == 0)
					{
						p_Error = "Resubmit - No Costs for " + product.getName();
						log.log(Level.WARNING, p_Error);
						return null;
					}*/
				}
		}	//	Receipt
				//	  *** Purchasing - return
		else if (getDocumentType().equals(DOCTYPE_MatShipment) && !isSOTrx())
		{
			BigDecimal total = Env.ZERO;
			for (int i = 0; i < p_lines.length; i++)
			{

				int C_Currency_ID = as.getC_Currency_ID();
				//
				DocLine line = p_lines[i];
				BigDecimal costs = null;
				
				MProduct product = line.getProduct();
				for(MCostDetail cost : line.getCostDetail(as,true))
				{	
					if (!MCostDetail.existsCost(cost))
						continue;
					
					costs = MCostDetail.getTotalCost(cost, as);
					
					total = total.add(costs);
					
					String description = cost.getM_CostElement().getName() +" "+ cost.getM_CostType().getName();
						
						dr = fact.createLine(line,
							getAccount(Doc.ACCTTYPE_NotInvoicedReceipts, as),
							C_Currency_ID, costs , null);
						dr.addDescription(description);
						//
						if (dr == null)
						{
							p_Error = "CR not created: " + line;
							log.log(Level.WARNING, p_Error);
							return null;
						}
						dr.setM_Locator_ID(line.getM_Locator_ID());
						dr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   //  from Loc
						dr.setLocationFromLocator(line.getM_Locator_ID(), false);   //  to Loc
                        dr.setM_Product_ID(cost.getM_Product_ID());
						dr.setQty(cost.getQty().negate());
						if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) && m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
						{
							//	Set AmtAcctDr from Original Shipment/Receipt
							if (!dr.updateReverseLine (MInOut.Table_ID, 
									m_Reversal_ID, line.getReversalLine_ID(),cost.getQty().negate(),Env.ONE.negate()))
							{
								p_Error = "Original Receipt not posted yet";
								return null;
							}
						}
						
						//  Inventory/Asset			CR
						MAccount assets = line.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
						if (product.isService())
							assets = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
						cr = fact.createLine(line, assets,
							C_Currency_ID, null, costs);
						//
						if (cr == null)
						{
							p_Error = "DR not created: " + line;
							log.log(Level.WARNING, p_Error);
							return null;
						}
						cr.setM_Locator_ID(line.getM_Locator_ID());
						cr.setLocationFromBPartner(getC_BPartner_Location_ID(), true);   // from Loc
						cr.setLocationFromLocator(line.getM_Locator_ID(), false);   // to Loc
						cr.addDescription(description);
                        cr.setM_Product_ID(cost.getM_Product_ID());
                        cr.setQty(cost.getQty());
						if (m_DocStatus.equals(MInOut.DOCSTATUS_Reversed) && m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
						{
							//	Set AmtAcctCr from Original Shipment/Receipt
							if (!cr.updateReverseLine (MInOut.Table_ID, 
									m_Reversal_ID, line.getReversalLine_ID() , cost.getQty() ,Env.ONE.negate()))
							{
								p_Error = "Original Receipt not posted yet";
								return null;
							}
						}
				}		
				/*if (total == null || total.signum() == 0)
				{
					p_Error = "Resubmit - No Costs for " + product.getName();
					log.log(Level.WARNING, p_Error);
					return null;
				}*/
			}
		}	//	Purchasing Return
		else
		{
			p_Error = "DocumentType unknown: " + getDocumentType();
			log.log(Level.SEVERE, p_Error);
			return null;
		}
		//
		facts.add(fact);
		return facts;
	}   //  createFact
}   //  Doc_InOut

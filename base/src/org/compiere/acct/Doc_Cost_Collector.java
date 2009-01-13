/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.wf.MWFNode;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPOrderNode;

/**
 *  Post Cost Collector
 *  <pre>
 *  Table:              PP_Cost_Collector
 *  Document Types:     MOP
 *  </pre>
 *  @author victor.perez@e-evolution.com http://www.e-evolution.com
 */
public class Doc_Cost_Collector extends Doc
{

	
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	protected Doc_Cost_Collector (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MPPCostCollector.class, rs, DOCTYPE_MOrder, trxName);
	}   //Doc Cost Collector
	

	/**	Pseudo Line								*/
	private DocLine				m_line = null;
	
	/** Collector Cost									*/
	private MPPCostCollector		m_cc = null;
	
	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID (NO_CURRENCY);
		m_cc = (MPPCostCollector)getPO();
		setDateDoc (m_cc.getMovementDate());
		setDateAcct(m_cc.getMovementDate());
		
		//	Pseudo Line
		m_line = new DocLine (m_cc, this); 
		m_line.setQty (m_cc.getMovementQty(), false);    //  sets Trx and Storage Qty
		
		//	Pseudo Line Check
		if (m_line.getM_Product_ID() == 0)
			log.warning(m_line.toString() + " - No Product");
		log.fine(m_line.toString());
		return null;
	}   //  loadDocumentDetails

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
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		log.info("Start Create Facts for Cost Collector");
		
		//TODO: I need write the rule for calculate variances
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());
		MProduct product = MProduct.get(getCtx(), m_cc.getM_Product_ID());
		
		if(m_cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt))
		{
			//Finish good
			MAccount debit = m_line.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
			MAccount credit = m_line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
			BigDecimal cost = Env.ZERO;
			
			//Material
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Material,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_Material, as, fact, product, debit, credit, cost,m_cc.getMovementQty() );
			
			//Resource (Labor)
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Resource,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_Resource, as, fact, product, debit, credit, cost,m_cc.getMovementQty());
			
			//Burden
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_BurdenMOverhead,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, as, fact, product, debit, credit, cost,m_cc.getMovementQty());
			
			//Outsite Processing
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_OutsideProcessing,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Overhead Applied
			credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Overhead, as);
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Overhead,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Account Scrap 
			if(m_cc.getScrappedQty().signum() != 0)
			{
				credit = m_line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
				debit = m_line.getAccount(ProductCost.ACCTTYPE_P_Scrap, as);
				//Material
				cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
						   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Material,m_cc.getScrappedQty());
				createLines(MCostElement.COSTELEMENTTYPE_Material, as, fact, product, debit, credit, cost,m_cc.getScrappedQty());
				
				//Resource (Labor)
				cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Resource,m_cc.getScrappedQty());				
				createLines(MCostElement.COSTELEMENTTYPE_Resource, as, fact, product, debit, credit, cost, m_cc.getScrappedQty());
				
				//Burden
				cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_BurdenMOverhead,m_cc.getScrappedQty());
				createLines(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, as, fact, product, debit, credit, cost, m_cc.getScrappedQty());
				
				//Outsite Processing
				cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_OutsideProcessing,m_cc.getScrappedQty());
				createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getScrappedQty());
				
				//Overhead Applied
				credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Overhead, as);
				cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Overhead,m_cc.getScrappedQty());
				createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getScrappedQty());
			}
		}
		else if (m_cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue))
		{

			MAccount debit = m_line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
			MAccount credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
			final String whereCluase = MPPOrderBOMLine.COLUMNNAME_PP_Order_BOMLine_ID 
									   + "= ? AND "
									   + MPPOrderBOMLine.COLUMNNAME_IssueMethod
									   + "= ?";
										
			boolean isFloorStock = new Query(m_cc.getCtx(),MPPOrderBOMLine.Table_Name,whereCluase, m_cc.get_TableName())
									.setOnlyActiveRecords(true)
									.setParameters(new Object[]{m_cc.getPP_Order_BOMLine_ID(),MPPOrderBOMLine.ISSUEMETHOD_FloorStock})
									.match();						
			if(isFloorStock)
			{
				 	 credit = m_line.getAccount(ProductCost.ACCTTYPE_P_FloorStock, as);
			}
			
			BigDecimal cost = Env.ZERO;
			//Material
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
					   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Material,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_Material, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Resource (Labor)
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Resource,m_cc.getMovementQty());			
			createLines(MCostElement.COSTELEMENTTYPE_Resource, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Burden
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_BurdenMOverhead,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Outsite Processing
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_OutsideProcessing,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getMovementQty());
			
			//Overhead Applied
			credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Overhead, as);
			cost = MCost.getCostByCostingMethod(product, as, m_cc.getAD_Org_ID(), m_cc.getM_AttributeSetInstance_ID(), 
				   MCostElement.COSTINGMETHOD_StandardCosting, MCostElement.COSTELEMENTTYPE_Overhead,m_cc.getMovementQty());
			createLines(MCostElement.COSTELEMENTTYPE_OutsideProcessing, as, fact, product, debit, credit, cost, m_cc.getMovementQty());

			
		}
		else if (m_cc.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
		{
			MPPOrderNode activity = (MPPOrderNode) m_cc.getPP_Order_Node();
			MWFNode node = (MWFNode) activity.getAD_WF_Node();
			if(activity.getDocAction().equals(MPPOrderNode.DOCSTATUS_Completed))
			{
				//Labor Rate
				MAccount debit = m_line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
				MAccount credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Labor, as);
				BigDecimal labor = node.getCostForCostElementType(MCostElement.COSTELEMENTTYPE_Resource, as.getC_AcctSchema_ID(), as.getM_CostType_ID(), m_cc.getAD_Org_ID(), m_cc.getSetupTimeReal().intValue(), m_cc.getDurationReal().intValue());
				createLines(MCostElement.COSTELEMENTTYPE_Resource, as, fact, product, debit, credit, labor, m_cc.getMovementQty());
				//Burden Rate
				debit = m_line.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
			    credit = m_line.getAccount(ProductCost.ACCTTYPE_P_Burden, as);
				BigDecimal burden = node.getCostForCostElementType(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, as.getC_AcctSchema_ID(), as.getM_CostType_ID(), m_cc.getAD_Org_ID(), m_cc.getSetupTimeReal().intValue(), m_cc.getDurationReal().intValue());
				createLines(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, as, fact, product, debit, credit, burden, m_cc.getMovementQty());
			}
		}

		log.info("End CreateFacts Manufacturing"+fact);
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact
	
	private void createLines(String CostElementType, MAcctSchema as ,Fact fact , MProduct product,MAccount debit, MAccount credit, BigDecimal cost, BigDecimal qty)
	{
		if(cost == null | debit == null | credit == null)
			return;
		
		log.info("CostElementType: " +CostElementType + "Product: "+product.getName()+" Debit: " + debit.getDescription() + " Credit: "+ credit.getDescription() + " Cost: " + cost +" Qunatity: "+ qty);
		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;
		if(cost.signum() != 0 & qty != null)
		{	
			dr = fact.createLine(m_line, debit , as.getC_Currency_ID(), cost, null);
			dr.setQty(qty);
			dr.addDescription(Msg.translate(m_cc.getCtx(), CostElementType));
			dr.setC_Project_ID(m_cc.getC_Project_ID());
			dr.setC_Activity_ID(m_cc.getC_Activity_ID());
			dr.setC_Campaign_ID(m_cc.getC_Campaign_ID());
			dr.setM_Locator_ID(m_cc.getM_Locator_ID());

			cr = fact.createLine(m_line, credit,as.getC_Currency_ID(), null, cost);
			cr.setQty(qty);
			cr.addDescription(Msg.translate(m_cc.getCtx(), CostElementType));
			cr.setC_Project_ID(m_cc.getC_Project_ID());
			cr.setC_Activity_ID(m_cc.getC_Activity_ID());
			cr.setC_Campaign_ID(m_cc.getC_Campaign_ID());
			cr.setM_Locator_ID(m_cc.getM_Locator_ID());
		}			
	}
}   //  Doc Cost Collector

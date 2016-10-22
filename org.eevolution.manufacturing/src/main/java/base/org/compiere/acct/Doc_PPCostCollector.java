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
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.I_M_CostElement;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

/**
 *  Post Cost Collector
 *  <pre>
 *  Table:              PP_Cost_Collector
 *  Document Types:     MOP
 *  </pre>
 *  @author victor.perez@e-evolution.com http://www.e-evolution.com
 */
public class Doc_PPCostCollector extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_PPCostCollector (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MPPCostCollector.class, rs, MDocType.DOCBASETYPE_ManufacturingCostCollector, trxName);
	}   //Doc Cost Collector
	

	/**	Pseudo Line */
	protected DocLine_CostCollector docLineCostCollector = null;
	
	/** Collector Cost */
	protected MPPCostCollector costCollector = null;
	/** Manufacturing Order **/
	protected I_PP_Order manufacturingOrder = null;

	/** Routing Service */
	protected RoutingService routingService = null;

	
	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID (NO_CURRENCY);
		costCollector = (MPPCostCollector)getPO();
		manufacturingOrder = costCollector.getPP_Order();

		setDateDoc (costCollector.getMovementDate());
		setDateAcct(costCollector.getMovementDate());
		
		//	Pseudo Line
		docLineCostCollector = new DocLine_CostCollector (costCollector, this);
		docLineCostCollector.setQty (costCollector.getMovementQty(), false);    //  sets Trx and Storage Qty
		
		//	Pseudo Line Check
		if (docLineCostCollector.getM_Product_ID() == 0)
			log.warning(docLineCostCollector.toString() + " - No Product");
		log.fine(docLineCostCollector.toString());
		
		// Load the RoutingService
		routingService = RoutingServiceFactory.get().getRoutingService(costCollector.getAD_Client_ID());
		
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
		setC_Currency_ID(as.getC_Currency_ID());
		final ArrayList<Fact> facts = new ArrayList<Fact>();
		
		if(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createMaterialReceipt(as));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createComponentIssue(as));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_MethodChangeVariance));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_UsageVariance));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_UsageVariance));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_RateVariance.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_RateVariance));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_MixVariance.equals(costCollector.getCostCollectorType()))
		{
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_MixVariance));
		}
		else if (MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl.equals(costCollector.getCostCollectorType()))
		{
			facts.addAll(createActivityControl(as));
		}
		//
		return facts;
	}   //  createFact
	
	protected void createLines(MCostElement element, MAcctSchema as, Fact fact , MProduct product,
								MAccount debit, MAccount credit, BigDecimal cost, BigDecimal qty)
	{
		if(cost == null || debit == null || credit == null)
			return;
		
		log.info("CostElement: " +element+ "Product: "+product.getName()
					+" Debit: " + debit.getDescription() + " Credit: "+ credit.getDescription()
					+ " Cost: " + cost +" Qty: "+ qty);
		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;
		if(cost.signum() != 0)
		{	
			dr = fact.createLine(docLineCostCollector, debit , as.getC_Currency_ID(), cost, null);
			dr.setQty(qty);
			String desc = manufacturingOrder.getDocumentNo() + " - " + costCollector.getS_Resource().getName() + "- " + element.getName();
			dr.addDescription(desc);
			dr.setC_Project_ID(costCollector.getC_Project_ID());
			dr.setC_Activity_ID(costCollector.getC_Activity_ID());
			dr.setC_Campaign_ID(costCollector.getC_Campaign_ID());
			dr.setM_Locator_ID(costCollector.getM_Locator_ID());

			cr = fact.createLine(docLineCostCollector, credit,as.getC_Currency_ID(), null, cost);
			cr.setQty(qty);
			cr.addDescription(desc);
			cr.setC_Project_ID(costCollector.getC_Project_ID());
			cr.setC_Activity_ID(costCollector.getC_Activity_ID());
			cr.setC_Campaign_ID(costCollector.getC_Campaign_ID());
			cr.setM_Locator_ID(costCollector.getM_Locator_ID());
		}			
	}
	
	/**
	 * The Receipt Finish good product created the next account fact
	 * Debit Product Asset Account for each Cost Element using Current Cost 
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing
	 * Debit Scrap Account for each Cost Element using Current Cost 
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing			
	 * Credit Work in Process Account for each Cost Element using Current Cost 
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing		
	 */
	protected Fact createMaterialReceipt(MAcctSchema as)
	{
		final Fact fact = new Fact(this, as, Fact.POST_Actual);
		FactLine dr = null;
		FactLine cr = null;
		final MAccount credit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
		final MAccount burden = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Burden, as);
		MAccount debit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
		BigDecimal totalcosts = Env.ZERO;
		BigDecimal totalcostsScrapped = Env.ZERO;
		for (MCostDetail cd : docLineCostCollector.getCostDetail(as, true))
		{
			MCostElement element = MCostElement.get(getCtx(), cd.getM_CostElement_ID());
			String description = manufacturingOrder.getDocumentNo() + " - " + cd.getM_CostType().getName() + " - " + element.getName();
			if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(element.getCostElementType()))
			{
				BigDecimal cost = cd.getAmt().add(cd.getAmtLL());
				if (cost.scale() > as.getStdPrecision())
					cost = cost.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);
				if (cost.compareTo(Env.ZERO)== 0)
					continue;
				cr = fact.createLine(docLineCostCollector, burden,as.getC_Currency_ID(), null, cost);
				cr.setQty(costCollector.getMovementQty());
				cr.setDescription(description);
				totalcosts = totalcosts.add(cost);
				//createLines(element, as, fact, product, debit, burden, cost, m_cc.getMovementQty());
				continue;
			}			
			if (costCollector.getMovementQty().signum() != 0)
			{
				BigDecimal cost = cd.getAmt().add(cd.getAmtLL());
				if (cost.scale() > as.getStdPrecision())
					cost = cost.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);
				if (cost.compareTo(Env.ZERO)== 0)
					continue;
				cr = fact.createLine(docLineCostCollector, credit,as.getC_Currency_ID(), cost.negate());
				cr.setQty(costCollector.getMovementQty());
				cr.setDescription(description);
				totalcosts = totalcosts.add(cost);
			}
			if(costCollector.getScrappedQty().signum() != 0)
			{
				BigDecimal cost = cd.getPrice().multiply(costCollector.getScrappedQty()).add(cd.getCurrentCostPriceLL());
				if (cost.compareTo(Env.ZERO)== 0)
					continue;
				if (cost.scale() > as.getStdPrecision())
					cost = cost.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);

				cr = fact.createLine(docLineCostCollector, credit,as.getC_Currency_ID(), null, cost);
				cr.setQty(costCollector.getMovementQty());
				description = manufacturingOrder.getDocumentNo() + " - " + cd.getM_CostType().getName() + " - " + element.getName() + " - " + Msg.parseTranslation(getCtx() , "@Scrap@"); ;
				cr.setDescription(description);
				totalcostsScrapped = totalcostsScrapped.add(cost);
			}
		}

		String description = manufacturingOrder.getDocumentNo();
		dr = fact.createLine(docLineCostCollector, debit, as.getC_Currency_ID(), totalcosts);
		dr.setQty(costCollector.getMovementQty());
		dr.setDescription(description);
		if (totalcostsScrapped.compareTo(Env.ZERO)!= 0)
		{
			dr = fact.createLine(docLineCostCollector, debit, as.getC_Currency_ID(), totalcostsScrapped);
			dr.setQty(costCollector.getScrappedQty());
			description = Msg.parseTranslation(getCtx() , "@Scrap@");
			dr.setDescription(description);
		}
		return fact;
	}
	
	/**
	 * The Issue Component product created next account fact
	 * Debit Work in Process Account for each Cost Element using current cost
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing	
	 * Credit Product Asset Account for each Cost Element using current cost
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing		
	 * Credit Floor Stock Account for each Cost Element using current cost
	 * Create a fact line for each cost element type
	 * 		Material
	 * 		Labor(Resources)
	 * 		Burden
	 * 		Overhead 
	 * 		Outsite Processing		
	 */
	protected Fact createComponentIssue(MAcctSchema as)
	{
		final Fact fact = new Fact(this, as, Fact.POST_Actual);
		BigDecimal totalCost = Env.ZERO;

		FactLine dr = null;
		FactLine cr = null;
		MAccount debit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
		MAccount credit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
		if(costCollector.isFloorStock())
		{
			credit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_FloorStock, as);
		}

		for (MCostDetail cd : docLineCostCollector.getCostDetail(as , false))
		{
			BigDecimal cost =  MCostDetail.getTotalCost(cd, as);
			if (cost.compareTo(Env.ZERO)==0)
				continue;
			if (cost.scale() > as.getStdPrecision())
				cost = cost.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);
			dr = fact.createLine(docLineCostCollector, debit, as.getC_Currency_ID(), cost);
			I_M_CostElement element = cd.getM_CostElement();
			String description = manufacturingOrder.getDocumentNo() + " - " + cd.getM_CostType().getName() + " - " + element.getName();
			dr.setDescription(description);

			totalCost = totalCost.add(cost);
		}
		String description = manufacturingOrder.getDocumentNo();
		cr = fact.createLine(docLineCostCollector, credit, as.getC_Currency_ID(), totalCost.negate());
		cr.setDescription(description);
		return fact;	
	}
	
	/**
	 * Feedback Labor and Burden absorbed
	 * Debit Work in Process Account for each Labor or Burden using the current cost rate
	 * Create a fact line for labor or burden
	 * 		Labor(Resources)
	 * 		Burden
	 * Credit Labor Absorbed or Burden Absorbed Account 
	 * Create a fact line for labor or burden
	 * 		Labor Absorbed
	 * 		Burden Absorbed
	 */
	protected List<Fact> createActivityControl(MAcctSchema as)
	{
		final ArrayList<Fact> facts = new ArrayList<Fact>();
		final Fact fact = new Fact(this, as, Fact.POST_Actual);
		facts.add(fact);
		
		final MProduct product = costCollector.getM_Product();

		MAccount debit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);
		for (MCostDetail cd : getCostDetailsActivityControl())
		{
			//BigDecimal costs =  MCostDetail.getTotalCost(cd, as);//cd.getAmt().add(cd.getAmtLL()).negate();
			BigDecimal costs = cd.getAmt().add(cd.getAmtLL()).negate();
			
			if (costs.signum() == 0)
				continue;
			MCostElement element = MCostElement.get(getCtx(), cd.getM_CostElement_ID());
			MAccount credit = docLineCostCollector.getAccount(as, element);
			createLines(element, as, fact, product, debit, credit, costs, costCollector.getMovementQty());
		}
		//
		return facts;
	}
	
	protected Fact createVariance(MAcctSchema as, int VarianceAcctType)
	{
		final Fact fact = new Fact(this, as, Fact.POST_Actual);
		final MProduct product = costCollector.getM_Product();
		
		MAccount debit = docLineCostCollector.getAccount(VarianceAcctType, as);
		MAccount credit = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, as);

		for (MCostDetail cd : docLineCostCollector.getCostDetail(as ,false))
		{
			MCostElement element = MCostElement.get(getCtx(), cd.getM_CostElement_ID());
				BigDecimal costs =  MCostDetail.getTotalCost(cd, as).negate();
				if (costs == null)
					costs = BigDecimal.ZERO;

				if (costs.scale() > as.getStdPrecision())
					costs = costs.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);
				BigDecimal qty = cd.getQty();
				createLines(element, as, fact, product, debit, credit, costs, qty);
		}
		return fact;
	}
	
	private List<MCostDetail> getCostDetailsActivityControl()
	{
		if (costDetails == null)
		{
			String whereClause = MCostDetail.COLUMNNAME_PP_Cost_Collector_ID+"=? AND EXISTS(SELECT 1 FROM M_CostElement ce " +
								 "WHERE ce.M_CostElement_ID = M_CostDetail.M_CostElement_ID AND ce.CostElementType IN('R','O'))";
			costDetails = new Query(getCtx(), MCostDetail.Table_Name, whereClause, getTrxName())
			.setParameters(costCollector.getPP_Cost_Collector_ID())
			.setOrderBy(MCostDetail.COLUMNNAME_M_CostDetail_ID)
			.list();
		}
		return costDetails;
	}
	
	private List<MCostDetail> costDetails = null;
}   //  Doc Cost Collector

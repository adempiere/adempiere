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
	 * 	@param acctSchemas accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_PPCostCollector (MAcctSchema[] acctSchemas, ResultSet rs, String trxName)
	{
		super(acctSchemas, MPPCostCollector.class, rs, MDocType.DOCBASETYPE_ManufacturingCostCollector, trxName);
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
			facts.add(createMaterialReceipt(as));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue.equals(costCollector.getCostCollectorType()))
			facts.add(createComponentIssue(as));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance.equals(costCollector.getCostCollectorType()))
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_MethodChangeVariance));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance.equals(costCollector.getCostCollectorType()))
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_UsageVariance));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance.equals(costCollector.getCostCollectorType()))
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_UsageVariance));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_RateVariance.equals(costCollector.getCostCollectorType()))
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_RateVariance));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_MixVariance.equals(costCollector.getCostCollectorType()))
			facts.add(createVariance(as, ProductCost.ACCTTYPE_P_MixVariance));
		else if (MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl.equals(costCollector.getCostCollectorType()))
			facts.addAll(createActivityControl(as));
		//
		return facts;
	}   //  createFact
	
	protected void createLines(MCostElement element, MAcctSchema acctSchema, Fact fact , MProduct product,
								MAccount debit, MAccount credit, BigDecimal cost, BigDecimal qty)
	{
		if(cost == null || debit == null || credit == null)
			return;
		
		log.info("CostElement: " +element+ "Product: "+product.getName()
					+" Debit: " + debit.getDescription() + " Credit: "+ credit.getDescription()
					+ " Cost: " + cost +" Qty: "+ qty);
		//  Line pointers
		FactLine debitLine = null;
		FactLine creditLine = null;
		if(cost.signum() != 0)
		{	
			debitLine = fact.createLine(docLineCostCollector, debit , acctSchema.getC_Currency_ID(), cost, null);
			debitLine.setQty(qty);
			String desc = manufacturingOrder.getDocumentNo() + " - " + costCollector.getS_Resource().getName() + "- " + element.getName();
			debitLine.addDescription(desc);
			debitLine.setC_Project_ID(costCollector.getC_Project_ID());
			debitLine.setC_Activity_ID(costCollector.getC_Activity_ID());
			debitLine.setC_Campaign_ID(costCollector.getC_Campaign_ID());
			debitLine.setM_Locator_ID(costCollector.getM_Locator_ID());

			creditLine = fact.createLine(docLineCostCollector, credit,acctSchema.getC_Currency_ID(), null, cost);
			creditLine.setQty(qty);
			creditLine.addDescription(desc);
			creditLine.setC_Project_ID(costCollector.getC_Project_ID());
			creditLine.setC_Activity_ID(costCollector.getC_Activity_ID());
			creditLine.setC_Campaign_ID(costCollector.getC_Campaign_ID());
			creditLine.setM_Locator_ID(costCollector.getM_Locator_ID());
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
	protected Fact createMaterialReceipt(MAcctSchema acctSchema)
	{
		final Fact fact = new Fact(this, acctSchema, Fact.POST_Actual);
		FactLine debitLine = null;
		FactLine creditLine = null;
		final MAccount workInProcessAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, acctSchema);
		final MAccount burdenAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Burden, acctSchema);
		MAccount inventoryAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Asset, acctSchema);
		BigDecimal totalcosts = BigDecimal.ZERO;
		BigDecimal totalcostsScrapped = BigDecimal.ZERO;
		for (MCostDetail costDetail : docLineCostCollector.getCostDetail(acctSchema, true))
		{
			MCostElement costElement = MCostElement.get(getCtx(), costDetail.getM_CostElement_ID());
			String description = manufacturingOrder.getDocumentNo() + " - " + costDetail.getM_CostType().getName() + " - " + costElement.getName();
			if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(costElement.getCostElementType()))
			{
				BigDecimal absoluteCost = costDetail.getTotalCost(costDetail, acctSchema);
				if (absoluteCost.signum() == 0)
					continue;
				BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
				if (cost.scale() > acctSchema.getStdPrecision())
					cost = cost.setScale(acctSchema.getStdPrecision(), RoundingMode.HALF_UP);
				if (cost.compareTo(Env.ZERO)== 0)
					continue;

				creditLine = fact.createLine(docLineCostCollector, burdenAccount,acctSchema.getC_Currency_ID(), null, cost);
				creditLine.setQty(costCollector.getMovementQty());
				creditLine.setDescription(description);
				totalcosts = totalcosts.add(cost);
				continue;
			}			
			if (costCollector.getMovementQty().signum() != 0)
			{
				BigDecimal absoluteCost = costDetail.getTotalCost(costDetail, acctSchema);
				if (absoluteCost.signum() == 0)
					continue;
				BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
				if (cost.scale() > acctSchema.getStdPrecision())
					cost = cost.setScale(acctSchema.getStdPrecision(), RoundingMode.HALF_UP);
				if (cost.compareTo(Env.ZERO)== 0)
					continue;

				creditLine = fact.createLine(docLineCostCollector, workInProcessAccount,acctSchema.getC_Currency_ID(), cost.negate());
				creditLine.setQty(costCollector.getMovementQty());
				creditLine.setDescription(description);
				totalcosts = totalcosts.add(cost);
			}
			if(costCollector.getScrappedQty().signum() != 0)
			{

				BigDecimal absoluteCost =costDetail.getPrice().multiply(costCollector.getScrappedQty()).add(costDetail.getTotalCost(costDetail, acctSchema));
				if (absoluteCost.signum() == 0)
					continue;
				BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
				if (cost.scale() > acctSchema.getStdPrecision())
					cost = cost.setScale(acctSchema.getStdPrecision(), RoundingMode.HALF_UP);

				creditLine = fact.createLine(docLineCostCollector, workInProcessAccount,acctSchema.getC_Currency_ID(), null, cost.negate());
				creditLine.setQty(costCollector.getMovementQty());
				description = manufacturingOrder.getDocumentNo() + " - " + costDetail.getM_CostType().getName() + " - " + costElement.getName() + " - " + Msg.parseTranslation(getCtx() , "@Scrap@"); ;
				creditLine.setDescription(description);
				totalcostsScrapped = totalcostsScrapped.add(cost);
			}
		}

		String description = manufacturingOrder.getDocumentNo();
		// Debit Amount based on sign of total costs
		debitLine = fact.createLine(docLineCostCollector, inventoryAccount, acctSchema.getC_Currency_ID(), totalcosts);
		debitLine.setQty(costCollector.getMovementQty());
		debitLine.setDescription(description);
		if (totalcostsScrapped.compareTo(Env.ZERO)!= 0)
		{
			debitLine = fact.createLine(docLineCostCollector, inventoryAccount, acctSchema.getC_Currency_ID(), totalcostsScrapped);
			debitLine.setQty(costCollector.getScrappedQty());
			description = Msg.parseTranslation(getCtx() , "@Scrap@");
			debitLine.setDescription(description);
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
	protected Fact createComponentIssue(MAcctSchema acctSchema)
	{
		final Fact fact = new Fact(this, acctSchema, Fact.POST_Actual);
		BigDecimal totalCost = Env.ZERO;

		FactLine debitLine = null;
		FactLine creditLine = null;
		MAccount workInProcessAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, acctSchema);
		MAccount inventoryAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_Asset, acctSchema);
		if(costCollector.isFloorStock())
			inventoryAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_FloorStock, acctSchema);

		for (MCostDetail costDetail : docLineCostCollector.getCostDetail(acctSchema , false))
		{
			BigDecimal absoluteCost = costDetail.getTotalCost(costDetail, acctSchema);
			if (absoluteCost.signum() == 0)
				continue;
			BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
			if (cost.compareTo(Env.ZERO) == 0)
				continue;

			if (cost.scale() > acctSchema.getStdPrecision())
				cost = cost.setScale(acctSchema.getStdPrecision(), RoundingMode.HALF_UP);

			debitLine = fact.createLine(docLineCostCollector, workInProcessAccount, acctSchema.getC_Currency_ID(),  cost.negate());
			I_M_CostElement costElement = costDetail.getM_CostElement();
			String description = manufacturingOrder.getDocumentNo() + " - " + costDetail.getM_CostType().getName() + " - " + costElement.getName();
			debitLine.setDescription(description);
			totalCost = totalCost.add(cost);
		}
		String description = manufacturingOrder.getDocumentNo();
		creditLine = fact.createLine(docLineCostCollector, inventoryAccount, acctSchema.getC_Currency_ID(), totalCost);
		creditLine.setDescription(description);
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
	protected List<Fact> createActivityControl(MAcctSchema acctSchema)
	{
		final ArrayList<Fact> facts = new ArrayList<Fact>();
		final Fact fact = new Fact(this, acctSchema, Fact.POST_Actual);
		facts.add(fact);
		
		final MProduct product = costCollector.getM_Product();
		MAccount workInProcessAccount = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, acctSchema);
		for (MCostDetail costDetail : getCostDetailsActivityControl())
		{
			BigDecimal absoluteCost = costDetail.getTotalCost(costDetail, acctSchema);
			if (absoluteCost.signum() == 0)
				continue;
			BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
			MCostElement costElement = MCostElement.get(getCtx(), costDetail.getM_CostElement_ID());
			MAccount creditCostAccount = docLineCostCollector.getAccount(acctSchema, costElement);
			createLines(costElement, acctSchema, fact, product, workInProcessAccount, creditCostAccount, cost, costCollector.getMovementQty());
		}
		//
		return facts;
	}
	
	protected Fact createVariance(MAcctSchema acctSchema, int varianceAcctType)
	{
		final Fact fact = new Fact(this, acctSchema, Fact.POST_Actual);
		final MProduct product = costCollector.getM_Product();
		
		MAccount varianceAccount = docLineCostCollector.getAccount(varianceAcctType, acctSchema);
		MAccount workInProcess = docLineCostCollector.getAccount(ProductCost.ACCTTYPE_P_WorkInProcess, acctSchema);
		for (MCostDetail costDetail : docLineCostCollector.getCostDetail(acctSchema ,false))
		{
			MCostElement costElement = MCostElement.get(getCtx(), costDetail.getM_CostElement_ID());
			BigDecimal absoluteCost = costDetail.getTotalCost(costDetail, acctSchema);
			if (absoluteCost.signum() == 0)
				continue;
				BigDecimal cost = costDetail.getQty().signum() < 0 ?  absoluteCost.negate() : absoluteCost;
				if (cost == null)
					cost = BigDecimal.ZERO;

				if (cost.scale() > acctSchema.getStdPrecision())
					cost = cost.setScale(acctSchema.getStdPrecision(), RoundingMode.HALF_UP);
				BigDecimal qty = costDetail.getQty();
				createLines(costElement, acctSchema, fact, product, varianceAccount, workInProcess, cost, qty);
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

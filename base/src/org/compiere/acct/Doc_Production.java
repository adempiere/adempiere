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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MInventory;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.ProductCost;
import org.compiere.model.X_M_Product;
import org.compiere.model.X_M_Production;
import org.compiere.model.X_M_ProductionLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              M_Production (325)
 *  Document Types:     MMP
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Production.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Production extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */

	private int				m_Reversal_ID = 0;
	private String			m_DocStatus = "";
	public Doc_Production (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, X_M_Production.class, rs, DOCTYPE_MatProduction, trxName);
	}   //  Doc_Production

	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID (NO_CURRENCY);
		X_M_Production prod = (X_M_Production)getPO();
		setDateDoc (prod.getMovementDate());
		setDateAcct(prod.getMovementDate());
		m_Reversal_ID = prod.getReversal_ID();//store original (voided/reversed) document
		m_DocStatus = prod.getDocStatus();
		//	Contained Objects
		p_lines = loadLines(prod);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line
	 *	@param prod production
	 *  @return DoaLine Array
	 */
	private DocLine[] loadLines(X_M_Production prod)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		//	Production
		//	-- ProductionLine	- the real level
		String sqlPL = "SELECT * FROM M_ProductionLine pl "
				+ "WHERE pl.M_Production_ID=? "
				+ "ORDER BY pl.Line";

		try
		{

			PreparedStatement pstmtPL = DB.prepareStatement(sqlPL, getTrxName());
			pstmtPL.setInt(1,get_ID());
			ResultSet rsPL = pstmtPL.executeQuery();
			while (rsPL.next())
			{
				X_M_ProductionLine line = new X_M_ProductionLine(getCtx(), rsPL, getTrxName());
				if (line.getMovementQty().signum() == 0)
				{
					log.info("LineQty=0 - " + line);
					continue;
				}
				DocLine docLine = new DocLine (line, this);
				docLine.setQty (line.getMovementQty(), false);
				//	Identify finished BOM Product
				docLine.setProductionBOM(line.getM_Product_ID() == prod.getM_Product_ID());
				//
				log.fine(docLine.toString());
				list.add (docLine);
			}
			rsPL.close();
			pstmtPL.close();
		}
		catch (Exception ee)
		{
			log.log(Level.SEVERE, sqlPL, ee);
		}

		DocLine[] dl = new DocLine[list.size()];
		list.toArray(dl);
		return dl;
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
	 *  MMP.
	 *  <pre>
	 *  Production
	 *      Inventory       DR      CR
	 *  </pre>
	 *  @param as account schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());

		//  Line pointer
		FactLine factLine = null;
		BigDecimal total = Env.ZERO;
		DocLine parentProductionLine = null;

		for (DocLine line : p_lines) {

			MProduct product = line.getProduct();
			if (line.isProductionBOM())
				parentProductionLine = line;

			if (X_M_Product.PRODUCTTYPE_Item.equals(product.getProductType()))
			{
				BigDecimal totalCosts = Env.ZERO;
				BigDecimal qty = line.getQty();
				BigDecimal costTransaction = Env.ZERO;
				for (MCostDetail cost : line.getCostDetail(as, false)) {
					if (!MCostDetail.existsCost(cost))
						continue;
					costTransaction =  MCostDetail.getTotalCost(cost, as);
					totalCosts = totalCosts.add(costTransaction);
				}
				if (qty.signum()< 0)
					totalCosts= totalCosts.negate();
				total = total.add(totalCosts);
				factLine = fact.createLine(line,  line.getAccount(ProductCost.ACCTTYPE_P_Asset, as), line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
						as.getC_Currency_ID() , totalCosts);
				factLine.setM_Product_ID(line.getM_Product_ID());
				factLine.setM_Locator_ID(line.getM_LocatorTo_ID());
				factLine.setDescription("");
				factLine.saveEx();
			}
			else if (!X_M_Product.PRODUCTTYPE_Item.equals(product.getProductType()))
			{
				BigDecimal totalCosts = Env.ZERO;
				MAccount acct = null;
				for (MCostElement costElement :  MCostElement.getCostElement(line.getCtx(), line.getTrxName())) {
					
					if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(costElement.getCostElementType()))
						acct  = line.getAccount(ProductCost.ACCTTYPE_P_Burden, as);
					else if (MCostElement.COSTELEMENTTYPE_OutsideProcessing.equals(costElement.getCostElementType()))
						acct  = line.getAccount(ProductCost.ACCTTYPE_P_OutsideProcessing, as);
					else if (MCostElement.COSTELEMENTTYPE_Overhead.equals(costElement.getCostElementType()))
						acct  = line.getAccount(ProductCost.ACCTTYPE_P_Overhead, as);
					else if (MCostElement.COSTELEMENTTYPE_Resource.equals(costElement.getCostElementType()))
						acct  = line.getAccount(ProductCost.ACCTTYPE_P_Labor, as);
					else 
						acct  = line.getAccount(ProductCost.ACCTTYPE_P_OutsideProcessing, as);
					int warehouseId =
							DB.getSQLValue(line.getTrxName() , "SELECT M_Warehouse_ID from M_Locator WHERE M_Locator_ID=?", line.getM_Locator_ID());
					BigDecimal costTransaction = Env.ZERO;
					MCost costDimension = MCost.validateCostForCostType(
							as,
							(MCostType)as.getM_CostType() ,
							costElement,
							product.getM_Product_ID(),
							line.getAD_Org_ID() ,
							warehouseId ,
							line.getM_AttributeSetInstance_ID() ,
							line.getTrxName());

					if (costDimension != null)
						costTransaction = costDimension.getCurrentCostPrice().add(costDimension.getCurrentCostPriceLL());
					totalCosts = totalCosts.add(costTransaction.multiply(line.getQty()));
				}
				factLine = fact.createLine(line, acct, acct,as.getC_Currency_ID() , totalCosts);
				factLine.setM_Product_ID(line.getM_Product_ID());
				factLine.setM_Locator_ID(line.getM_LocatorTo_ID());
				if (m_DocStatus.equals(MProduction.DOCSTATUS_Reversed) && m_Reversal_ID !=0 && line.getReversalLine_ID() != 0)
				{
					//	Set AmtAcctDr from Original Phys.Inventory
					if (!factLine.updateReverseLine (MProduction.Table_ID, 
							m_Reversal_ID, line.getReversalLine_ID(), line.getQty() ,Env.ONE))
					{
						p_Error = "Original Physical Inventory not posted yet";
						return null;
					}
				}
				factLine.setDescription("");
				factLine.saveEx();
				total = total.add(totalCosts);
			}
		}

		// When total no is zero then create an adjustment cost , can happen when of resource rate was changes from original cost finish good
		if (total.signum() != 0)
		{
			factLine = fact.createLine( parentProductionLine , parentProductionLine.getAccount(ProductCost.ACCTTYPE_P_CostAdjustment, as),
					as.getC_Currency_ID() , total);
			factLine.setM_Product_ID(parentProductionLine.getM_Product_ID());
			factLine.setM_Locator_ID(parentProductionLine.getM_LocatorTo_ID());
			factLine.setDescription(" Adjustment Cost");
			factLine.saveEx();

		}

		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

	/**
	 *  Create Facts (the accounting logic) for
	 *  MMP.
	 *  <pre>
	 *  Production
	 *      Inventory       DR      CR
	 *  </pre>
	 *  @param as account schema
	 *  @return Fact
	 */
	/*public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());

		//  Line pointer
		FactLine fl = null;
		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine line = p_lines[i];
			//	Calculate Costs
			BigDecimal costs = null;

			 adaxa-pb don't use cost details
			// MZ Goodwill
			// if Production CostDetail exist then get Cost from Cost Detail
			MCostDetail cd = MCostDetail.get (as.getCtx(), "M_ProductionLine_ID=?",
					line.get_ID(), line.getM_AttributeSetInstance_ID(), as.getC_AcctSchema_ID(), getTrxName());
			if (cd != null)
				costs = cd.getAmt();
			else

			{
				int variedHeader = 0;
				BigDecimal variance = null;
				costs = line.getProductCosts(as, line.getAD_Org_ID(), false);
				if (line.isProductionBOM() && line.getM_Production_ID() != variedHeader )
				{
					//	Get BOM Cost - Sum of individual lines
					BigDecimal bomCost = Env.ZERO;
					for (int ii = 0; ii < p_lines.length; ii++)
					{
						DocLine line0 = p_lines[ii];
						if (line0.getM_Production_ID() != line.getM_Production_ID())
							continue;
						//pb changed this 20/10/06
						 if ( !line0.isProductionBOM() )
						bomCost = bomCost.add(line0.getProductCosts(as, line.getAD_Org_ID(), false).setScale(2,BigDecimal.ROUND_HALF_UP));
					}
					variance = (costs.setScale(2,BigDecimal.ROUND_HALF_UP)).subtract(bomCost.negate());
					//TODO use currency precision instead of hardcoded 2
					// get variance account
					int validCombination = MAcctSchemaDefault.get(getCtx(),
							as.get_ID()).getP_RateVariance_Acct();
					MAccount base = MAccount.get(getCtx(), validCombination);
					MAccount account = MAccount.get(getCtx(),as.getAD_Client_ID(),as.getAD_Org_ID(),
							as.get_ID(), base.getAccount_ID(), 0,0,0,0,0,0,0,0,0,0,0,0,0,0, null);
					//
					// only post variance if it's not zero
					if (variance.compareTo(new BigDecimal("0.00")) != 0)
					{
						//post variance
						fl = fact.createLine(line,
								account,
								as.getC_Currency_ID(), variance.negate());
						fl.setQty(Env.ZERO);
						if (fl == null)
						{
							p_Error = "Couldn't post variance " + line.getLine() + " - " + line;
							return null;
						}
					}
					// costs = bomCost.negate();
				}
				else
					costs = line.getProductCosts(as, line.getAD_Org_ID(), false);
			}
			// end MZ

			//  Inventory       DR      CR
			fl = fact.createLine(line,
				line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
				as.getC_Currency_ID(), costs);
			if (fl == null)
			{
				p_Error = "No Costs for Line " + line.getLine() + " - " + line;
				return null;
			}
			fl.setM_Locator_ID(line.getM_Locator_ID());
			fl.setQty(line.getQty());

			//	Cost Detail
			String description = line.getDescription();
			if (description == null)
				description = "";
			if (line.isProductionBOM())
				description += "(*)";
			MCostDetail.createProduction(as, line.getAD_Org_ID(),
				line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
				line.get_ID(), 0,
				costs, line.getQty(),
				description, getTrxName());
		}
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact
*/
}   //  Doc_Production
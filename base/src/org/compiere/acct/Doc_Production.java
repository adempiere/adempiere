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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.model.X_M_Production;
import org.compiere.model.X_M_ProductionLine;
import org.compiere.model.X_M_ProductionPlan;
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
		//	-- ProductionPlan
		//	-- -- ProductionLine	- the real level
		String sqlPP = "SELECT * FROM M_ProductionPlan pp "
			+ "WHERE pp.M_Production_ID=? "
			+ "ORDER BY pp.Line";
		String sqlPL = "SELECT * FROM M_ProductionLine pl "
			+ "WHERE pl.M_ProductionPlan_ID=? "
			+ "ORDER BY pl.movementqty desc";

		try
		{
			PreparedStatement pstmtPP = DB.prepareStatement(sqlPP, getTrxName());
			pstmtPP.setInt(1, get_ID());
			ResultSet rsPP = pstmtPP.executeQuery();
			//
			while (rsPP.next())
			{
				int M_Product_ID = rsPP.getInt("M_Product_ID");
				int M_ProductionPlan_ID = rsPP.getInt("M_ProductionPlan_ID");
				//
				try
				{
					PreparedStatement pstmtPL = DB.prepareStatement(sqlPL, getTrxName());
					pstmtPL.setInt(1, M_ProductionPlan_ID);
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
						docLine.setProductionBOM(line.getM_Product_ID() == M_Product_ID);
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
			}
			rsPP.close();
			pstmtPP.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sqlPP, e);
		}
		//	Return Array
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
		ArrayList<Fact> facts = new ArrayList<Fact>();

		//  Line pointer
		FactLine fl = null;
		DocLine prodBomLine = null;
		BigDecimal total = Env.ZERO;
		BigDecimal totalDr = Env.ZERO;
		BigDecimal totalCr = Env.ZERO;
		MProduct product = null;
		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine line = p_lines[i];
			if (p_lines[i].isProductionBOM())
			{	if (prodBomLine == null)
				prodBomLine = p_lines[i];
				if (totalDr.compareTo(totalCr)!=0)
				{
					ProductCost m_pc = new ProductCost (Env.getCtx(), 
							product.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), getTrxName());
					BigDecimal diff = totalDr.subtract(totalCr);
					fl = fact.createLine(null,
							m_pc.getAccount(ProductCost.ACCTTYPE_P_Asset,as),
							as.getC_Currency_ID(), diff);
				}
				product = line.getProduct();
				totalDr = Env.ZERO;
				totalCr = Env.ZERO;
				prodBomLine = p_lines[i];
				
			}
			BigDecimal costs = Env.ZERO;			
			for (MCostDetail cost : line.getCostDetail(as))
			{

				if (!MCostDetail.existsCost(cost))
					return null;

				costs = MCostDetail.getTotalCost(cost, as);

				//get costing method for product
				String description = cost.getM_CostElement().getName() +" "+ cost.getM_CostType().getName();
				if(cost.getQty().signum() > 0)
					//totalDr = totalDr.add(costs)
					;
				else
				{
					//totalCr = totalCr.add(costs);
					costs = costs.negate();	
				}

				if (cost != null)
				{	
					total = total.add(costs.abs());
				}	
				fl = fact.createLine(line,
						line.getAccount(ProductCost.ACCTTYPE_P_Asset,as),
						as.getC_Currency_ID(), costs);
				fl.setM_Locator_ID(line.getM_Locator_ID());
				fl.setQty(cost.getQty());
				if (fl.getAmtSourceDr().compareTo(Env.ZERO)==1)
					totalDr = totalDr.add(fl.getAmtSourceDr());
				else
					totalCr = totalCr.add(fl.getAmtSourceCr());
				//	Cost Detail
				//String description = line.getDescription();
				description = description + " " +line.getDescription();
				if (description == null)
					description = "";
				if (line.isProductionBOM())
					description += "(*)";
			}
		}

		if (totalDr.compareTo(totalCr)!=0)
		{
			ProductCost m_pc = new ProductCost (Env.getCtx(), 
					product.getM_Product_ID(), 0, getTrxName());
			BigDecimal diff = totalDr.subtract(totalCr);
			fl = fact.createLine(null,
					m_pc.getAccount(ProductCost.ACCTTYPE_P_Asset,as),
					as.getC_Currency_ID(), diff.negate());
			fl.setM_Product_ID(prodBomLine.getM_Product_ID());
			fl.setM_Locator_ID(prodBomLine.getM_Locator_ID());
			fl.setQty(Env.ONE);
		}
		if (total == null || total.signum() == 0)
		{
			p_Error = "Resubmit - No Costs for " + getDescription();
			log.log(Level.WARNING, p_Error);
			return null;
		}
		
		//
		facts.add(fact);
		return facts;
	}   //  createFact

	
}   //  Doc_Production

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
import java.util.List;
import java.util.logging.Level;

import javax.sound.sampled.Line;

import org.adempiere.engine.CostEngineFactory;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MTransaction;
import org.compiere.model.ProductCost;
import org.compiere.model.Query;
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
		//	-- ProductionLine	- the real level
		String sqlPL = "SELECT * FROM M_ProductionLine pl "
			+ "WHERE pl.M_Production_ID=? "
			+ "ORDER BY pl.m_productionplan_ID, movementqty , pl.Line";

		try
		{			
					PreparedStatement pstmtPL = DB.prepareStatement(sqlPL, getTrxName());
					pstmtPL.setInt(1,get_ID());
					ResultSet rsPL = pstmtPL.executeQuery();
					while (rsPL.next())
					{
						X_M_ProductionLine line = new X_M_ProductionLine(getCtx(), rsPL, getTrxName());
						int sel_product_ID = prod.getM_Product_ID() !=0 ? prod.getM_Product_ID():							
								line.getM_ProductionPlan().getM_Product_ID();
						if (line.getMovementQty().signum() == 0)
						{
							log.info("LineQty=0 - " + line);
							continue;
						}
						DocLine docLine = new DocLine (line, this);
						docLine.setQty (line.getMovementQty(), false);
						//	Identify finished BOM Product
						docLine.setProductionBOM(line.getM_Product_ID() == sel_product_ID);
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
		FactLine fl = null;
		BigDecimal totalCost = Env.ZERO;
		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine line = p_lines[i];	

			BigDecimal costs = null;
			// MZ Goodwill
			// if Production CostDetail exist then get Cost from Cost Detail 
			
				
				if (line.getCostDetail(as , false).size() == 0)
				{
					String sqlDelete = "delete from m_costdetail where m_productionline_ID=" + line.get_ID();
					if (line.isProductionBOM())
						DB.executeUpdateEx(sqlDelete, null);
					
					//	Get BOM Cost - Sum of individual lines
					List<MTransaction> transactions = new Query(getCtx(), MTransaction.Table_Name, "m_Productionline_ID=?", null)
						.setParameters(line.get_ID())
						.list();
					for (MTransaction mtrx :transactions)
					{
						CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(mtrx);
					}
				}	
				for (MCostDetail cost : line.getCostDetail(as , false))
				{
					if(!MCostDetail.existsCost(cost))
						continue;	 					
					costs = MCostDetail.getTotalCost(cost, as);						
				}
				if (line.isProductionBOM())
					costs = totalCost;
				else
					costs = costs.negate();
					
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

			if (!line.isProductionBOM())
			{
				totalCost = totalCost.add(fl.getAmtAcctCr());
			}
			fl.setM_Locator_ID(line.getM_Locator_ID());
			fl.setQty(line.getQty());
			
			//	Cost Detail
			String description = line.getDescription();
			if (description == null)
				description = "";
			if (line.isProductionBOM())
				description += "(*)";

			if (line.isProductionBOM())
				totalCost = Env.ZERO;
				/*
			MCostDetail.createProduction(as, line.getAD_Org_ID(), 
				line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), 
				line.get_ID(), 0, 
				costs, line.getQty(), 
				description, getTrxName());*/
		}
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

}   //  Doc_Production

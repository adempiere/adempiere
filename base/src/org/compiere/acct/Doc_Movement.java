/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.math.*;
import java.sql.*;
import java.util.*;

import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              M_Movement (323)
 *  Document Types:     MMM
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Movement.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Movement extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_Movement (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MMovement.class, rs, DOCTYPE_MatMovement, trxName);
	}   //  Doc_Movement

	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID(NO_CURRENCY);
		MMovement move = (MMovement)getPO();
		setDateDoc (move.getMovementDate());
		setDateAcct(move.getMovementDate());
		//	Contained Objects
		p_lines = loadLines(move);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line
	 *	@param move move
	 *  @return document lines (DocLine_Material)
	 */
	private DocLine[] loadLines(MMovement move)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MMovementLine[] lines = move.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MMovementLine line = lines[i];
			DocLine docLine = new DocLine (line, this);
			docLine.setQty(line.getMovementQty(), false);
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
	 *  @return balance (ZERO) - always balanced
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  MMM.
	 *  <pre>
	 *  Movement
	 *      Inventory       DR      CR
	 *      InventoryTo     DR      CR
	 *  </pre>
	 *  @param as account schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID(as.getC_Currency_ID());

		//  Line pointers
		FactLine dr = null;
		FactLine cr = null;

		for (int i = 0; i < p_lines.length; i++)
		{
			DocLine line = p_lines[i];
			// MZ Goodwill
			// if Inventory Move CostDetail exist then get Cost from Cost Detail 
			BigDecimal costs = line.getProductCosts(as, line.getAD_Org_ID(), true, "M_MovementLine_ID=? AND M_AttributeSetInstance_ID=? AND IsSOTrx='N'");
			// end MZ

			//  ** Inventory       DR      CR
			dr = fact.createLine(line,
				line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
				as.getC_Currency_ID(), costs.negate());		//	from (-) CR
			if (dr == null)
				continue;
			dr.setM_Locator_ID(line.getM_Locator_ID());
			dr.setQty(line.getQty().negate());	//	outgoing
			
			//  ** InventoryTo     DR      CR
			cr = fact.createLine(line,
				line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
				as.getC_Currency_ID(), costs);			//	to (+) DR
			if (cr == null)
				continue;
			cr.setM_Locator_ID(line.getM_LocatorTo_ID());
			cr.setQty(line.getQty());

			//	Only for between-org movements
			if (dr.getAD_Org_ID() != cr.getAD_Org_ID())
			{
				String costingLevel = as.getCostingLevel();
				MProductCategoryAcct pca = MProductCategoryAcct.get(getCtx(), 
					line.getProduct().getM_Product_Category_ID(), 
					as.getC_AcctSchema_ID(), getTrxName());
				if (pca.getCostingLevel() != null)
					costingLevel = pca.getCostingLevel();
				if (!MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
					continue;
				//
				String description = line.getDescription();
				if (description == null)
					description = "";
				//	Cost Detail From
				MCostDetail.createMovement(as, dr.getAD_Org_ID(), 	//	locator org
					line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
					line.get_ID(), 0,
					costs.negate(), line.getQty().negate(), true,
					description + "(|->)", getTrxName());
				//	Cost Detail To
				MCostDetail.createMovement(as, cr.getAD_Org_ID(),	//	locator org 
					line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
					line.get_ID(), 0,
					costs, line.getQty(), false,
					description + "(|<-)", getTrxName());
			}
		}

		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

}   //  Doc_Movement

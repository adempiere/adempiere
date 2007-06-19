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
 *  Post Inventory Documents.
 *  <pre>
 *  Table:              M_Inventory (321)
 *  Document Types:     MMI
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Inventory.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Inventory extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_Inventory (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MInventory.class, rs, DOCTYPE_MatInventory, trxName);
	}   //  Doc_Inventory

	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{
		setC_Currency_ID (NO_CURRENCY);
		MInventory inventory = (MInventory)getPO();
		setDateDoc (inventory.getMovementDate());
		setDateAcct(inventory.getMovementDate());
		//	Contained Objects
		p_lines = loadLines(inventory);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line
	 *	@param inventory inventory
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MInventory inventory)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MInventoryLine[] lines = inventory.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInventoryLine line = lines[i];
			//	nothing to post
			if (line.getQtyBook().compareTo(line.getQtyCount()) == 0
				&& line.getQtyInternalUse().signum() == 0)
				continue;
			//
			DocLine docLine = new DocLine (line, this); 
			BigDecimal Qty = line.getQtyInternalUse();
			if (Qty.signum() != 0)
				Qty = Qty.negate();		//	Internal Use entered positive
			else
			{
				BigDecimal QtyBook = line.getQtyBook();
				BigDecimal QtyCount = line.getQtyCount();
				Qty = QtyCount.subtract(QtyBook);
			}
			docLine.setQty (Qty, false);		// -5 => -5
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
	 *  MMI.
	 *  <pre>
	 *  Inventory
	 *      Inventory       DR      CR
	 *      InventoryDiff   DR      CR   (or Charge)
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
			// if Physical Inventory CostDetail is exist then get Cost from Cost Detail 
			BigDecimal costs = line.getProductCosts(as, line.getAD_Org_ID(), true, "M_InventoryLine_ID=? AND M_AttributeSetInstance_ID=?");
			// end MZ
			if (costs == null || costs.signum() == 0)
			{
				p_Error = "No Costs for " + line.getProduct().getName();
				return null;
			}
			//  Inventory       DR      CR
			dr = fact.createLine(line,
				line.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
				as.getC_Currency_ID(), costs);
			//  may be zero difference - no line created.
			if (dr == null)
				continue;
			dr.setM_Locator_ID(line.getM_Locator_ID());
			
			//  InventoryDiff   DR      CR
			//	or Charge
			MAccount invDiff = line.getChargeAccount(as, costs.negate());
			if (invDiff == null)
				invDiff = getAccount(Doc.ACCTTYPE_InvDifferences, as);
			cr = fact.createLine(line, invDiff,
				as.getC_Currency_ID(), costs.negate());
			if (cr == null)
				continue;
			cr.setM_Locator_ID(line.getM_Locator_ID());
			cr.setQty(line.getQty().negate());
			if (line.getC_Charge_ID() != 0)	//	explicit overwrite for charge
				cr.setAD_Org_ID(line.getAD_Org_ID());

			//	Cost Detail
			MCostDetail.createInventory(as, line.getAD_Org_ID(), 
				line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), 
				line.get_ID(), 0, 
				costs, line.getQty(), 
				line.getDescription(), getTrxName());
		}
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

}   //  Doc_Inventory

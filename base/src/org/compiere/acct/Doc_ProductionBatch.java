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
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.I_M_ProductionBatch;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MProductionBatch;
import org.compiere.util.Env;

/**
 * Post Order Documents.
 * 
 * <pre>
 *   Table:              M_ProductionBatch
 *   Document Types:     MPO (ProductionBatch)
 * </pre>
 * 
 * @author Mario Calderón
 * @version $Id: Doc_ProductionBatch.java,v 1.0 2017/10/05 17:53:00 
 */
public class Doc_ProductionBatch extends Doc
{
	/**
	 * Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_ProductionBatch (MAcctSchema[]  ass, ResultSet rs, String trxName)
	{
		super (ass, MProductionBatch.class, rs, DOCTYPE_ManufacturingPlannedOrder, trxName);
	}	//	Doc_ProductionBatch

	/**
	 *	Load Specific Document Details
	 *  only dates, amounts=zero
	 *  @return null
	 */
	protected String loadDocumentDetails ()
	{
		setC_Currency_ID(NO_CURRENCY);
		MProductionBatch pb = (MProductionBatch)getPO();
		setDateDoc (pb.getMovementDate());
		setDateAcct (pb.getMovementDate());
		// Amounts
		setAmount(AMTTYPE_Gross, Env.ZERO);
		setAmount(AMTTYPE_Net, Env.ZERO);
		// Contained Objects
		p_lines = loadLines (pb);
		return null;
	}	// loadDocumentDetails

	/**
	 *	Load Lines
	 *	@param pb production batch
	 *	@return empty DocLine Array
	 */
	private DocLine[] loadLines (MProductionBatch pb)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine> ();

		// Return Array
		DocLine[] dls = new DocLine[list.size ()];
		list.toArray (dls);
		return dls;
	}	// loadLines

	/***************************************************************************
	 * Get Source Currency Balance
	 * 
	 * @return Zero
	 */
	public BigDecimal getBalance ()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}	// getBalance

	/***************************************************************************
	 * Create Facts (the accounting logic) for MPO.
	 * <pre>
	 * no facts
	 * </pre>
	 * @param as accounting schema
	 * @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		
		return facts;
	} // createFacts
	
	public static String getDateAcctColumnName() {
        
         return I_M_ProductionBatch.COLUMNNAME_MovementDate;
    }

} // Doc_ProductionBatch

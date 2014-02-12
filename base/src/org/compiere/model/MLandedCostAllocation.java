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
package org.compiere.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.engine.IDocumentLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Landed Cost Allocation Model
 *  @author Jorg Janke
 *  @version $Id: MLandedCostAllocation.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MLandedCostAllocation extends X_C_LandedCostAllocation implements IDocumentLine
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8645283018475474574L;


	/**
	 * 	Get Cost Allocations for invoice Line
	 *	@param ctx context
	 *	@param C_InvoiceLine_ID invoice line
	 *	@param trxName trx
	 *	@return landed cost alloc
	 */
	public static MLandedCostAllocation[] getOfInvoiceLine (Properties ctx, 
		int C_InvoiceLine_ID, String trxName)
	{
		ArrayList<MLandedCostAllocation> list = new ArrayList<MLandedCostAllocation>();
		String sql = "SELECT * FROM C_LandedCostAllocation WHERE C_InvoiceLine_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_InvoiceLine_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MLandedCostAllocation (ctx, rs, trxName));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MLandedCostAllocation[] retValue = new MLandedCostAllocation[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfInvliceLine
	
	/**
	 * Get Landed Cost Allocation
	 * @param MInOutLine ioLine
	 * @param M_CostElement_ID
	 * @return List MLandedCostAllocation
	 */
	public static List<MLandedCostAllocation> getOfInOuline (MInOutLine ioLine, int M_CostElement_ID)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_C_LandedCostAllocation.COLUMNNAME_M_InOutLine_ID).append("=? AND ");
		whereClause.append(I_C_LandedCostAllocation.COLUMNNAME_M_CostElement_ID).append("=? ");
		return new Query(ioLine.getCtx(),I_C_LandedCostAllocation.Table_Name, whereClause.toString() , ioLine.get_TrxName())
		.setClient_ID()
		.setParameters(ioLine.getM_InOutLine_ID(), M_CostElement_ID)
		.list();
	}	//	getOfInvliceLine
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MLandedCostAllocation.class);
	
	
	/***************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_LandedCostAllocation_ID id
	 *	@param trxName trx
	 */
	public MLandedCostAllocation (Properties ctx, int C_LandedCostAllocation_ID, String trxName)
	{
		super (ctx, C_LandedCostAllocation_ID, trxName);
		if (C_LandedCostAllocation_ID == 0)
		{
		//	setM_CostElement_ID(0);
			setAmt (Env.ZERO);
			setQty (Env.ZERO);
			setBase (Env.ZERO);
		}
	}	//	MLandedCostAllocation

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result name
	 *	@param trxName trx
	 */
	public MLandedCostAllocation (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MLandedCostAllocation

	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param M_CostElement_ID cost element
	 */
	public MLandedCostAllocation (MInvoiceLine parent, int M_CostElement_ID)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setC_InvoiceLine_ID(parent.getC_InvoiceLine_ID());
		setM_CostElement_ID(M_CostElement_ID);
	}	//	MLandedCostAllocation
	
	/**
	 * 	Set Amt
	 *	@param Amt amount
	 *	@param precision precision
	 */
	public void setAmt (double Amt, int precision)
	{
		BigDecimal bd = new BigDecimal(Amt);
		if (bd.scale() > precision)
			bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
		super.setAmt(bd);
	}	//	setAmt

	/**
	 * 	Set Allocation Qty (e.g. free products)
	 *	@param Qty
	 */
	public void setQty (BigDecimal Qty)
	{
		super.setQty (Qty);
	}	//	setQty

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getM_Locator_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override //ancabradau
	public BigDecimal getMovementQty()
	{
		
		return this.getQty();
	}

	@Override //ancabradau
	public BigDecimal getPriceActual()
	{	
		final String where = "EXISTS (SELECT 1 FROM C_Invoice i INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID) WHERE C_Currency.C_Currency_ID=i.C_Currency_ID AND il.C_InvoiceLine_ID=?)";
		MCurrency currency = new Query (getCtx(), I_C_Currency.Table_Name, where , get_TrxName())
		.setParameters(getC_InvoiceLine_ID())
		.firstOnly();
		BigDecimal price = getAmt().divide(getQty(), currency.getCostingPrecision() ,  RoundingMode.HALF_UP);
		return price;
	}

	

	@Override
	public int getReversalLine_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSOTrx() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setM_Locator_ID(int M_Locator_ID) {
		// TODO Auto-generated method stub
		
	}
	
	public Timestamp getDateAcct() {
		return getM_InOutLine().getM_InOut().getDateAcct();
	}
	

	public IDocumentLine getReversalDocumentLine() {
		return null;
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getM_LocatorTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public int getC_DocType_ID() {
		return -1;
	}
	
}	//	MLandedCostAllocation

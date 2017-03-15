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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.engine.CostEngineFactory;
import org.adempiere.engine.IDocumentLine;
import org.adempiere.engine.CostingMethodFactory;
import org.adempiere.engine.ICostingMethod;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Match Invoice (Receipt<>Invoice) Model.
 *	Accounting:
 *	- Not Invoiced Receipts (relief)
 *	- IPV
 *	
 *  @author Jorg Janke
 *  @version $Id: MMatchInv.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1926113 ] MMatchInv.getNewerDateAcct() should work in trx
 * @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 * @author Bayu Cahya, Sistematika
 * 			<li>BF [ 2240484 ] Re MatchingPO, MMatchPO doesn't contains Invoice info
 * 
 */
public class MMatchInv extends X_M_MatchInv implements IDocumentLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3668871839074170205L;


    /**
     * get Match Inv list
     * @param inOutLine
     * @return  Match Inv list entities
     */
	public static List<MMatchInv> getInOutLine (MInOutLine inOutLine)
	{
		return new Query(inOutLine.getCtx(), I_M_MatchInv.Table_Name, I_M_MatchInv.COLUMNNAME_M_InOutLine_ID + "=?", inOutLine.get_TrxName())
		.setParameters(inOutLine.getM_InOutLine_ID())
		.list();
	}	//	getInOutLine

	
	/**
	 * 	Get InOut-Invoice Matches
	 *	@param ctx context
	 *	@param M_InOutLine_ID shipment
	 *	@param C_InvoiceLine_ID invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchInv[] get (Properties ctx, int M_InOutLine_ID, int C_InvoiceLine_ID, String trxName)
	{
		if (M_InOutLine_ID <= 0 || C_InvoiceLine_ID <= 0)
			return new MMatchInv[]{};
		//
		final String whereClause = "M_InOutLine_ID=? AND C_InvoiceLine_ID=?";
		List<MMatchInv> list = new Query(ctx, I_M_MatchInv.Table_Name, whereClause, trxName)
		.setParameters(M_InOutLine_ID, C_InvoiceLine_ID)
		.list();
		return list.toArray (new MMatchInv[list.size()]);
	}	//	get

	// MZ Goodwill
	/**
	 * 	Get Inv Matches for InvoiceLine
	 *	@param ctx context
	 *	@param C_InvoiceLine_ID invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchInv[] getInvoiceLine (Properties ctx, int C_InvoiceLine_ID, String trxName)
	{
		if (C_InvoiceLine_ID <= 0)
			return new MMatchInv[]{};
		//
		String whereClause = "C_InvoiceLine_ID=?";
		List<MMatchInv> list = new Query(ctx, I_M_MatchInv.Table_Name, whereClause, trxName)
		.setParameters(C_InvoiceLine_ID)
		.list();
		return list.toArray (new MMatchInv[list.size()]);
	}	//	getInvoiceLine
	// end MZ
	
	/**
	 * 	Get Inv Matches for InOut
	 *	@param ctx context
	 *	@param M_InOut_ID shipment
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchInv[] getInOut (Properties ctx, int M_InOut_ID, String trxName)
	{
		if (M_InOut_ID <= 0)
			return new MMatchInv[]{};
		//
		final String whereClause = "EXISTS (SELECT 1 FROM M_InOutLine l"
			+" WHERE M_MatchInv.M_InOutLine_ID=l.M_InOutLine_ID AND l.M_InOut_ID=?)"; 
		List<MMatchInv> list = new Query(ctx, I_M_MatchInv.Table_Name, whereClause, trxName)
		.setParameters(M_InOut_ID)
		.list();
		return list.toArray (new MMatchInv[list.size()]);
	}	//	getInOut

	/**
	 * 	Get Inv Matches for Invoice
	 *	@param ctx context
	 *	@param C_Invoice_ID invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchInv[] getInvoice (Properties ctx, 
		int C_Invoice_ID, String trxName)
	{
		if (C_Invoice_ID == 0)
			return new MMatchInv[]{};
		//
		final String whereClause = " EXISTS (SELECT 1 FROM C_InvoiceLine il"
				+" WHERE M_MatchInv.C_InvoiceLine_ID=il.C_InvoiceLine_ID AND il.C_Invoice_ID=?)";
		List<MMatchInv> list = new Query(ctx, I_M_MatchInv.Table_Name, whereClause, trxName)
		.setParameters(C_Invoice_ID)
		.list();
		return list.toArray (new MMatchInv[list.size()]);
	}	//	getInvoice

	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMatchInv.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_MatchInv_ID id
	 *	@param trxName transaction
	 */
	public MMatchInv (Properties ctx, int M_MatchInv_ID, String trxName)
	{
		super (ctx, M_MatchInv_ID, trxName);
		if (M_MatchInv_ID == 0)
		{
		//	setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	setC_InvoiceLine_ID (0);
		//	setM_InOutLine_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
		//	setQty (Env.ZERO);
			setPosted (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MMatchInv

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMatchInv (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMatchInv
	
	/**
	 * 	Invoice Line Constructor
	 *	@param iLine invoice line
	 *	@param dateTrx optional date
	 *	@param qty matched quantity
	 */
	public MMatchInv (MInvoiceLine iLine, Timestamp dateTrx, BigDecimal qty)
	{
		this (iLine.getCtx(), 0, iLine.get_TrxName());
		setClientOrg(iLine);
		setC_InvoiceLine_ID(iLine.getC_InvoiceLine_ID());
		setM_InOutLine_ID(iLine.getM_InOutLine_ID());
		if (dateTrx != null)
			setDateTrx (dateTrx);
		setM_Product_ID (iLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(iLine.getM_AttributeSetInstance_ID());
		setQty (qty);
		setProcessed(true);		//	auto
	}	//	MMatchInv

	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Trx Date
		if (getDateTrx() == null)
			setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	Set Acct Date
		if (getDateAcct() == null)
		{
			Timestamp ts = getNewerDateAcct();
			if (ts == null)
				ts = getDateTrx();
			setDateAcct (ts);
		}
		if (getM_AttributeSetInstance_ID() == 0 && getM_InOutLine_ID() != 0)
		{
			MInOutLine iol = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
			setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
		}
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save.
	 * 	Set Order Qty Delivered/Invoiced 
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
		{				
			MInOutLine inout_line = (MInOutLine) getM_InOutLine();
			for (MTransaction trx: MTransaction.getByInOutLine(inout_line))
			{
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(trx, this);
			}
		}
		//
		return success;
	}	//	afterSave
	
	/**
	 * 	Get the Date Acct from  shipment
	 *	@return date or null
	 */
	public Timestamp getNewerDateAcct()
	{
		String sql = "SELECT io.DateAcct "
			+ "FROM M_InOutLine iol"
			+ " INNER JOIN M_InOut io ON (io.M_InOut_ID=iol.M_InOut_ID) "
			+ "WHERE iol.M_InOutLine_ID=?";
		Timestamp shipDate = DB.getSQLValueTS(get_TrxName(), sql, getM_InOutLine_ID());
		return shipDate;
	}	//	getNewerDateAcct
	
	
	/**
	 * 	Before Delete
	 *	@return true if acct was deleted
	 */
	protected boolean beforeDelete ()
	{
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_MatchInvoice, getAD_Org_ID());
			setPosted(false);
			MFactAcct.deleteEx (Table_ID, get_ID(), get_TrxName());
		}
		return true;
	}	//	beforeDelete

	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
		{
			// AZ Goodwill
			deleteMatchInvCostDetail();
			// end AZ
			
			//	Get Order and decrease invoices
			MInvoiceLine iLine = new MInvoiceLine (getCtx(), getC_InvoiceLine_ID(), get_TrxName());
			int C_OrderLine_ID = iLine.getC_OrderLine_ID();
			if (C_OrderLine_ID == 0)
			{
				MInOutLine ioLine = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
				C_OrderLine_ID = ioLine.getC_OrderLine_ID();
			}
			//	No Order Found
			if (C_OrderLine_ID == 0)
				return success;
			//	Find MatchPO
			MMatchPO[] mPO = MMatchPO.get(getCtx(), C_OrderLine_ID, 
				getC_InvoiceLine_ID(), get_TrxName());
			for (int i = 0; i < mPO.length; i++)
			{
				if (mPO[i].getM_InOutLine_ID() == 0)
					mPO[i].delete(true);
				else
				{
					mPO[i].setC_InvoiceLine_ID(null);
					mPO[i].saveEx();
				}
			}
		}
		return success;
	}	//	afterDelete
	
	//AZ Goodwill
	private String deleteMatchInvCostDetail()
	{
		// Get Account Schemas to delete MCostDetail
		
		MAcctSchema[] acctschemas = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		for(int asn = 0; asn < acctschemas.length; asn++)
		{
			MAcctSchema as = acctschemas[asn];
			
			if (as.isSkipOrg(getAD_Org_ID()))
			{
				continue;
			}
			
			MProduct product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
			String CostingLevel = product.getCostingLevel(as);

			int Org_ID = getAD_Org_ID();
			int M_ASI_ID = getM_AttributeSetInstance_ID();
			if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
			{
				Org_ID = 0;
				M_ASI_ID = 0;
			}
			else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
				M_ASI_ID = 0;
			else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
				Org_ID = 0;
			
			List<MCostElement> ces = MCostElement.getCostElement(getCtx(), get_TrxName());
			List<MCostType> cts = MCostType.get(getCtx(), get_TrxName());
			for (MCostType ct : cts)
			{	
				if (!ct.isActive())
					continue;
				for (MCostElement ce : ces)
					
				{	
					String whereClause = "c_invoiceline_id =? and m_costtype_id=? and m_costelement_ID=?";
					List<MCostDetail> cds = new Query(getCtx(), MCostDetail.Table_Name, whereClause, get_TrxName())
					.setParameters(getC_InvoiceLine_ID(), ct.getM_CostType_ID(), ce.getM_CostElement_ID())
					.list();
					for (MCostDetail cd:cds)
					{
						//cd.setDeltaAmt(cd.getAmt().negate());
						cd.setCostAdjustment(Env.ZERO);
						cd.setCostAdjustmentLL(Env.ZERO);
						cd.saveEx();
					//	cd.setProcessed(false);
						//

						MInOutLine inout_line = (MInOutLine) getM_InOutLine();
						MCost dimension = new MCost (product, M_ASI_ID,
								as.getC_AcctSchema_ID(), Org_ID, inout_line.getM_Warehouse_ID() , cd.getM_CostType_ID(), cd.getM_CostElement_ID() , get_TrxName());
						
						for (MTransaction trx: MTransaction.getByInOutLine(inout_line))
						{
							final ICostingMethod method = CostingMethodFactory.get().getCostingMethod(ct.getCostingMethod());
							method.setCostingMethod(as, trx, this, dimension, Env.ZERO, Env.ZERO, false);
							//method.processCostDetail(cd);
						}
					}
				}
			}
		}
		
		return "";
	}
	
	// Bayu, Sistematika
	/**
	 * 	Get Inv Matches for InOutLine
	 *	@param ctx context
	 *	@param M_InOutLine_ID shipment
	 *	@param trxName transaction
	 *	@return array of matches
	 *
     * public static MMatchInv[] getInOutLine (Properties ctx,
		int M_InOutLine_ID, String trxName)
	{
		if (M_InOutLine_ID <= 0)
		{
			return new MMatchInv[]{};
		}
		//
		final String whereClause = MMatchInv.COLUMNNAME_M_InOutLine_ID+"=?";
		List<MMatchInv> list = new Query(ctx, I_M_MatchInv.Table_Name, whereClause, trxName)
		.setParameters(M_InOutLine_ID)
		.list();
		return list.toArray (new MMatchInv[list.size()]);
	}	//	getInOutLine
	*/
	// end Bayu

	@Override
	public int getM_Locator_ID() {
		return -1;
	}

	@Override
	public BigDecimal getMovementQty() {
		return getQty();
	}

	@Override
	public BigDecimal getPriceActual() {

		MInvoiceLine invoiceLine = (MInvoiceLine) getC_InvoiceLine();
		BigDecimal priceActual = MConversionRate.convertBase(getCtx(), invoiceLine.getPriceActual(), getC_Currency_ID(),
				getDateAcct(), getC_ConversionType_ID(),
				getAD_Client_ID(), getAD_Org_ID());	
		if (MDocType.DOCBASETYPE_APCreditMemo.equals(invoiceLine.getParent().getC_DocType().getDocBaseType()))
			return priceActual.negate();
		else
			return priceActual;
	}

	@Override
	public BigDecimal getPriceActualCurrency() {
		MInvoiceLine invoiceLine = (MInvoiceLine) getC_InvoiceLine();
		return invoiceLine.getPriceActual();
	}

	@Override
	public int getC_Currency_ID ()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT i.C_Currency_ID FROM C_InvoiceLine il INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) WHERE il.C_InvoiceLine_ID = ? ",
				getC_InvoiceLine_ID());
	}

	@Override
	public int getC_ConversionType_ID()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT i.C_ConversionType_ID FROM C_InvoiceLine il INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) WHERE il.C_InvoiceLine_ID = ? ",
				getC_InvoiceLine_ID());
	}

	@Override
	public int getReversalLine_ID() {
		return -1;
	}

	@Override
	public boolean isSOTrx() {
		return false;
	}

	@Override
	public void setM_Locator_ID(int M_Locator_ID) {
	;
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
}	//	MMatchInv

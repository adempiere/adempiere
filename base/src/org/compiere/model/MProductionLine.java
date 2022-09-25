/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_DocType;
import org.adempiere.core.domains.models.X_C_DocType;
import org.adempiere.core.domains.models.X_M_ProductionLine;
import org.adempiere.engine.IDocumentLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Contributed from Adaxa
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/648">
 * 		@see FR [ 648 ] Add Support to document Action on Standard Production window</a>		
 */
public class MProductionLine extends X_M_ProductionLine implements IDocumentLine{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Parent							*/
	private MProduction 	m_parent = null;

	/**	Product					*/
	private MProduct 		m_product = null;


	/**
	 * 	Standard Constructor
	 *	@param ctx ctx
	 *	@param M_ProductionLine_ID id
	 */
	public MProductionLine (Properties ctx, int M_ProductionLine_ID, String trxName)
	{
		super (ctx, M_ProductionLine_ID, trxName);
		if (M_ProductionLine_ID == 0)
		{
			setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_ProductionLine WHERE M_Production_ID=@M_Production_ID@
			setM_AttributeSetInstance_ID (0);
			//setM_Locator_ID (0);	// @M_Locator_ID@
			//setM_Product_ID (0);
			setM_ProductionLine_ID (0);
			setM_Production_ID (0);
			setMovementQty (Env.ZERO);
			setProcessed (false);
		}
			
	}	// MProductionLine
	
	public MProductionLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductionLine
	
	/**
	 * Parent Constructor
	 * @param plan
	 */
	public MProductionLine( MProduction header ) {
		super(header.getCtx(), 0, header.get_TrxName() );
		setM_Production_ID(header.getM_Production_ID());
		setAD_Client_ID(header.getAD_Client_ID());
		setAD_Org_ID(header.getAD_Org_ID());
	}

	public int deleteMA() {
		String sql = "DELETE FROM M_ProductionLineMA WHERE M_ProductionLine_ID = " + get_ID();
		int count = DB.executeUpdateEx( sql, get_TrxName() );
		return count;
	}



	public String toString() {
		if ( getM_Product_ID() == 0 )
			return ("No product defined for production line " + getLine());
		MProduct product = new MProduct(getCtx(),getM_Product_ID(), get_TrxName());
		return ( "Production line:" + getLine() + " -- " + getMovementQty() + " of " + product.getValue());
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {

		if ( getParent().getM_Product_ID() == getM_Product_ID() && getParent().getProductionQty().signum() == getMovementQty().signum())
			setIsEndProduct(true);
		else 
			setIsEndProduct(false);
		
		if ( isEndProduct() && getM_AttributeSetInstance_ID() != 0 )
		{
			String where = "M_QualityTest_ID IN (SELECT M_QualityTest_ID " +
			"FROM M_Product_QualityTest WHERE M_Product_ID=?) " +
			"AND M_QualityTest_ID NOT IN (SELECT M_QualityTest_ID " +
			"FROM M_QualityTestResult WHERE M_AttributeSetInstance_ID=?)";

			List<MQualityTest> tests = new Query(getCtx(), MQualityTest.Table_Name, where, get_TrxName())
			.setOnlyActiveRecords(true).setParameters(getM_Product_ID(), getM_AttributeSetInstance_ID()).list();
			// create quality control results
			for (MQualityTest test : tests)
			{
				test.createResult(getM_AttributeSetInstance_ID());
			}
		}
				return true;
	}
	
	@Override
	protected boolean beforeDelete() {
		if (getM_Production().isProcessed())
			return false;

		deleteMA();
		//if (pBatch.getHeaders(true).length)
		MProductionBatchLine pbLine = MProductionBatchLine.getbyProduct(getParent().getM_ProductionBatch_ID(), getM_Product_ID(), getCtx(), get_TrxName());
		if (pbLine != null)
		{
			BigDecimal qtyReserved = isEndProduct()?getMovementQty().negate():getMovementQty();
			pbLine.setQtyReserved(pbLine.getQtyReserved().add(qtyReserved));
			pbLine.saveEx();
		}
		return true;
	}

	/**
	 * get true if Production Line is Parent Product
	 * @return true
	 */
	public boolean isParent()
	{
		Boolean isParent =  getM_Product_ID() == getM_ProductionPlan().getM_Product_ID() ? true : false;
		if (!isParent)
			isParent =   getM_Product_ID() == getM_Production().getM_Product_ID() ? true : false;
		return isParent;
	}
	


	public Timestamp getDateAcct(){
		if (getM_ProductionPlan_ID() != 0)
			return getM_ProductionPlan().getM_Production().getMovementDate();
		else
			return getM_Production().getMovementDate();
	}
	
	public boolean isSOTrx(){
		return false;
	}
	
	public BigDecimal getPriceActual(){
		return Env.ZERO;
	}
	public IDocumentLine getReversalDocumentLine(){
		return this;
	}

	public int getM_AttributeSetInstanceTo_ID(){
		return -1;
	}
	
	public int getM_LocatorTo_ID(){
		return -1;
	}

	public int getC_DocType_ID(){

		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_C_DocType.COLUMNNAME_DocBaseType).append("=?");

		return new Query(getCtx() , X_C_DocType.Table_Name , whereClause.toString() , get_TrxName())
				.setClient_ID().setParameters(X_C_DocType.DOCBASETYPE_MaterialProduction)
				.firstId();
	}

	protected void setParent(MProduction parent)
	{
		m_parent = parent; 
	}	//	setParent

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MProduction getParent()
	{
		if (m_parent == null)
			m_parent = new MProduction (getCtx(), getM_Production_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	

	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		int M_ProductionBatch_ID = getParent().getM_ProductionBatch_ID();
		if (newRecord )
		{
			if (M_ProductionBatch_ID ==0)
				return true;
			MProductionBatchLine pbLine = MProductionBatchLine.getbyProduct(M_ProductionBatch_ID, getM_Product_ID(), getCtx(), get_TrxName());
			if (pbLine !=null)
			{
				BigDecimal movementQty = isEndProduct()?getMovementQty():getMovementQty().negate();
				pbLine.setQtyReserved(pbLine.getQtyReserved().add(movementQty));
				pbLine.saveEx();		
				return true;
			}
			BigDecimal movementQty = isEndProduct()?getMovementQty():getMovementQty().negate();
			pbLine = new MProductionBatchLine(getCtx(), 0, get_TrxName());
			pbLine.setM_ProductionBatch_ID(M_ProductionBatch_ID);
			pbLine.setM_Product_ID(getM_Product_ID());
			pbLine.setQtyReserved(movementQty);
			pbLine.setIsEndProduct(isEndProduct());
			pbLine.saveEx();		
			return true;
		}
		//	return true;
		if (this.is_ValueChanged(COLUMNNAME_MovementQty) )
		{
			BigDecimal oldValue= (BigDecimal)get_ValueOld(COLUMNNAME_MovementQty);
			BigDecimal diff = getMovementQty().subtract(oldValue);
			diff = isEndProduct()?diff:diff.negate();
			MProductionBatchLine pbLine = MProductionBatchLine.getbyProduct(M_ProductionBatch_ID, getM_Product_ID(), getCtx(), get_TrxName());
			if (pbLine == null)
			{
				pbLine = new MProductionBatchLine(getCtx(), 0, get_TrxName());
				pbLine.setM_ProductionBatch_ID(M_ProductionBatch_ID);
				pbLine.setM_Product_ID(getM_Product_ID());
				pbLine.setQtyReserved(getMovementQty().negate());
				pbLine.saveEx();				
			}
			pbLine.setQtyReserved(getQtyReserved().add(diff));
			pbLine.saveEx();
		}

		return super.afterSave(newRecord, success);
	}

	@Override
	public BigDecimal getPriceActualCurrency() {
		return BigDecimal.ZERO;
	}

	@Override
	public int getC_Currency_ID ()
	{
		MClient client  = MClient.get(getCtx());
		return client.getC_Currency_ID();
	}

	@Override
	public int getC_ConversionType_ID()
	{
		return  MConversionType.getDefault(getAD_Client_ID());
	}

	@Override
	public boolean isReversalParent() {
		return getM_ProductionLine_ID() < getReversalLine_ID();
	}
	
}

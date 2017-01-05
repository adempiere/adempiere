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
import java.util.logging.Level;

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
			setM_Locator_ID (0);	// @M_Locator_ID@
			setM_Product_ID (0);
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
	
	

	/**
	 * 
	 * @param date
	 * @return "" for success, error string if failed
	 */
	public String createTransactions(Timestamp date, boolean mustBeStocked) {
		// delete existing ASI records
		int deleted = deleteMA();
		log.log(Level.FINE, "Deleted " + deleted + " attribute records ");
		
		MProduct prod = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		log.log(Level.FINE,"Loaded Product " + prod.toString());
		
		if ( prod.getProductType().compareTo(MProduct.PRODUCTTYPE_Item ) != 0 )  {
			// no need to do any movements
			log.log(Level.FINE, "Production Line " + getLine() + " does not require stock movement");
			return "";
		}
		StringBuffer errorString = new StringBuffer();
		
		MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), getM_AttributeSetInstance_ID(), get_TrxName());
		String asiString = asi.getDescription();
		if ( asiString == null )
			asiString = "";
		log.log(Level.FINEST, "asi Description is: " + asiString);
		
		int batchLocatorID = getM_Production().getM_ProductionBatch().getM_Locator_ID();
		
		// create transactions for finished goods
		if ( getMovementQty().compareTo(Env.ZERO) > 0 ) {
			MProductionLineMA lineMA = new MProductionLineMA( this,
					asi.get_ID(), getMovementQty());
			if ( !lineMA.save(get_TrxName()) ) {
				log.log(Level.SEVERE, "Could not save MA for " + toString());
				errorString.append("Could not save MA for " + toString() + "\n" );
			}
			MTransaction matTrx = new MTransaction (getCtx(), getAD_Org_ID(), 
					"P+", 
					getM_Locator_ID(), getM_Product_ID(), asi.get_ID(), 
					getMovementQty(), date, get_TrxName());
			matTrx.setM_ProductionLine_ID(get_ID());
			if ( !matTrx.save(get_TrxName()) ) {
				log.log(Level.SEVERE, "Could not save transaction for " + toString());
				errorString.append("Could not save transaction for " + toString() + "\n");
			}
			MStorage storage = MStorage.getCreate(getCtx(), getM_Locator_ID(),
					getM_Product_ID(), asi.get_ID(), get_TrxName());
			storage.changeQtyOnHand(getMovementQty(), true);
			
			if (getM_Production().getReversal_ID() == 0)
			{
				if (!isEndProduct())
				{
					if (storage.getM_AttributeSetInstance_ID() == 0 && batchLocatorID == getM_Locator_ID())
					{
						storage.setQtyReserved(storage.getQtyReserved().subtract(getMovementQty()));
					}
					else
					{
						MStorage.add(getCtx(),
								getM_Production().getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(),
								batchLocatorID, getM_Product_ID(), 0, 0, Env.ZERO, getMovementQty().negate(), Env.ZERO,
								get_TrxName());
					}
					setQtyReserved(Env.ZERO);
				}
				else
				{
					if (storage.getM_AttributeSetInstance_ID() == 0 && batchLocatorID == getM_Locator_ID())
					{
						storage.setQtyOrdered(storage.getQtyOrdered().subtract(getMovementQty()));
					}
					else
					{
						MStorage.add(getCtx(),
								getM_Production().getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(),
								batchLocatorID, getM_Product_ID(), 0, 0, Env.ZERO, Env.ZERO, getMovementQty().negate(),
								get_TrxName());
					}
				}
			}
			
			if ( !storage.save(get_TrxName()) )  {
				log.log(Level.SEVERE, "Could not update storage for " + toString());
				errorString.append("Could not save transaction for " + toString() + "\n");
			}
			log.log(Level.FINE, "Created finished goods line " + getLine());
			
			return errorString.toString();
		}
		
		// create transactions and update stock used in production
		MStorage[] storages = MStorage.getAll( getCtx(), getM_Product_ID(),
				getM_Locator_ID(), get_TrxName());
		
		MProductionLineMA lineMA = null;
		MTransaction matTrx = null;
		BigDecimal qtyToMove = getMovementQty().negate();


		for (int sl = 0; sl < storages.length; sl++) {

			BigDecimal lineQty = storages[sl].getQtyOnHand();
			
			log.log(Level.FINE, "QtyAvailable " + lineQty );
			if (lineQty.signum() > 0) 
			{
				if (lineQty.compareTo(qtyToMove ) > 0)
						lineQty = qtyToMove;

				MAttributeSetInstance slASI = new MAttributeSetInstance(getCtx(),
						storages[sl].getM_AttributeSetInstance_ID(),get_TrxName());
				String slASIString = slASI.getDescription();
				if (slASIString == null)
					slASIString = "";
				
				log.log(Level.FINEST,"slASI-Description =" + slASIString);
					
				if ( slASIString.compareTo(asiString) == 0
						|| asi.getM_AttributeSet_ID() == 0  )  
				//storage matches specified ASI or is a costing asi (inc. 0)
			    // This process will move negative stock on hand quantities
				{
					lineMA = MProductionLineMA.get(this,storages[sl].getM_AttributeSetInstance_ID());
					lineMA.setMovementQty(lineMA.getMovementQty().add(lineQty.negate()));
					if ( !lineMA.save(get_TrxName()) ) {
						log.log(Level.SEVERE, "Could not save MA for " + toString());
						errorString.append("Could not save MA for " + toString() + "\n" );
					}
					else
						log.log(Level.FINE, "Saved MA for " + toString());
					matTrx = new MTransaction (getCtx(), getAD_Org_ID(), 
							"P-", 
							getM_Locator_ID(), getM_Product_ID(), asi.get_ID(), 
							lineQty.negate(), date, get_TrxName());
					matTrx.setM_ProductionLine_ID(get_ID());
					if ( !matTrx.save(get_TrxName()) ) {
						log.log(Level.SEVERE, "Could not save transaction for " + toString());
						errorString.append("Could not save transaction for " + toString() + "\n");
					}
					else
						log.log(Level.FINE, "Saved transaction for " + toString());
					storages[sl].changeQtyOnHand(lineQty, false);
					if (!isEndProduct())
					{
						if (storages[sl].getM_AttributeSetInstance_ID() == 0 && batchLocatorID == getM_Locator_ID())
						{
							storages[sl].setQtyReserved(storages[sl].getQtyReserved().subtract(lineQty));
						}
						else
						{
							MStorage.add(getCtx(),
									getM_Production().getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(),
									batchLocatorID, getM_Product_ID(), 0, 0, Env.ZERO, lineQty.negate(), Env.ZERO,
									get_TrxName());
						}
						setQtyReserved(getQtyReserved().subtract(lineQty));
					}
					if ( !storages[sl].save(get_TrxName()) )  {
						log.log(Level.SEVERE, "Could not update storage for " + toString());
						errorString.append("Could not update storage for " + toString() + "\n");
					}
					qtyToMove = qtyToMove.subtract(lineQty);
					log.log(Level.FINE, getLine() + " Qty moved = " + lineQty + ", Remaining = " + qtyToMove );
				}
			}
			
			if ( qtyToMove.signum() == 0 )			
				break;
			
		} // for available storages
		
		
		if ( !( qtyToMove.signum() == 0) ) {
			if (mustBeStocked)
			{
				MLocator loc = new MLocator(getCtx(), getM_Locator_ID(), get_TrxName());
				errorString.append( "Insufficient qty on hand of " + prod.toString() + " at "
						+ loc.toString() + "\n");
			}
			else
			{
				MStorage storage = MStorage.get(Env.getCtx(), getM_Locator_ID(), getM_Product_ID(), 0, get_TrxName());
				if (storage == null)
				{
					storage = new MStorage(Env.getCtx(), 0, get_TrxName());
					storage.setM_Locator_ID(getM_Locator_ID());
					storage.setM_Product_ID(getM_Product_ID());
					storage.setM_AttributeSetInstance_ID(0);
					storage.save();
					
				}
				
				BigDecimal lineQty = qtyToMove;
				MAttributeSetInstance slASI = new MAttributeSetInstance(getCtx(),
						storage.getM_AttributeSetInstance_ID(),get_TrxName());
				String slASIString = slASI.getDescription();
				if (slASIString == null)
					slASIString = "";
				
				log.log(Level.FINEST,"slASI-Description =" + slASIString);
					
				if ( slASIString.compareTo(asiString) == 0
						|| asi.getM_AttributeSet_ID() == 0  )  
				//storage matches specified ASI or is a costing asi (inc. 0)
			    // This process will move negative stock on hand quantities
				{
					//lineMA = new MProductionLineMA( this,
					//		storage.getM_AttributeSetInstance_ID(),
					//		lineQty.negate());
					lineMA = MProductionLineMA.get(this,storage.getM_AttributeSetInstance_ID());
					lineMA.setMovementQty(lineMA.getMovementQty().add(lineQty.negate()));
					
					if ( !lineMA.save(get_TrxName()) ) {
						log.log(Level.SEVERE, "Could not save MA for " + toString());
						errorString.append("Could not save MA for " + toString() + "\n" );
					}
					else
						log.log(Level.FINE, "Saved MA for " + toString());
					matTrx = new MTransaction (getCtx(), getAD_Org_ID(), 
							"P-", 
							getM_Locator_ID(), getM_Product_ID(), asi.get_ID(), 
							lineQty.negate(), date, get_TrxName());
					matTrx.setM_ProductionLine_ID(get_ID());
					if ( !matTrx.save(get_TrxName()) ) {
						log.log(Level.SEVERE, "Could not save transaction for " + toString());
						errorString.append("Could not save transaction for " + toString() + "\n");
					}
					else
						log.log(Level.FINE, "Saved transaction for " + toString());
					storage.changeQtyOnHand(lineQty, false);
					if(!isEndProduct())
					{
						if (storage.getM_AttributeSetInstance_ID() == 0 && batchLocatorID == getM_Locator_ID())
						{
							storage.setQtyReserved(storage.getQtyReserved().subtract(lineQty));
						}
						else
						{
							MStorage.add(getCtx(),
									getM_Production().getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(),
									batchLocatorID, getM_Product_ID(), 0, 0, Env.ZERO, lineQty.negate(), Env.ZERO,
									get_TrxName());
						}
						setQtyReserved(getQtyReserved().subtract(lineQty));
					}
					if ( !storage.save(get_TrxName()) )  {
						log.log(Level.SEVERE, "Could not update storage for " + toString());
						errorString.append("Could not update storage for " + toString() + "\n");
					}
					qtyToMove = qtyToMove.subtract(lineQty);
					log.log(Level.FINE, getLine() + " Qty moved = " + lineQty + ", Remaining = " + qtyToMove );
				}
				
			}
			
		}
			
		return errorString.toString();
		
	}
	
	public Boolean createTransaction (MProductionLine pLine)
	{
		MProduct product = pLine.getProduct();

		//	Qty & Type
		String MovementType = pLine.isEndProduct()?MTransaction.MOVEMENTTYPE_ProductionPlus:MTransaction.MOVEMENTTYPE_Production_;
		BigDecimal Qty = pLine.getMovementQty();      

		log.info("Line=" + pLine.getLine() + " - Qty=" + pLine.getMovementQty());

		if (product != null
				&& product.isStocked() )
		{
			//Ignore the Material Policy when is Reverse Correction
			
			if (product != null  )
			{
				if(pLine.getM_Production().getReversal_ID() ==0)
			{
				getParent().checkMaterialPolicy(pLine, MovementType);
			}
			BigDecimal movementqty = pLine.getMovementQty();
				log.fine("Material Transaction");
				MTransaction mtrx = null; 

				//If AttributeSetInstance = Zero then create new  AttributeSetInstance use Inventory Line MA else use current AttributeSetInstance
				if (pLine.getM_AttributeSetInstance_ID() == 0)
				{
					MProductionLineMA[]  list = MProductionLineMA.get(getCtx(),
							pLine.getM_ProductionLine_ID(), get_TrxName());
					for (MProductionLineMA ma:list)
					{
						if (pLine.getM_AttributeSetInstance_ID() !=0 && pLine.getM_AttributeSetInstance_ID() != ma.getM_AttributeSetInstance_ID())
							continue;
						BigDecimal QtyMA = ma.getMovementQty();
						BigDecimal reservedQty = Env.ZERO;
						BigDecimal orderedQty = Env.ZERO;
						if (pLine.getProduct().isStocked())
						{
							if (pLine.isEndProduct())
							{
								orderedQty = QtyMA.negate();
								reservedQty = Env.ZERO;
							}
							else
							{
								orderedQty = Env.ZERO;
								reservedQty = QtyMA.negate();							
							}
						}
						
						//Parameters: ctx,M_Warehouse_ID, M_Locator_ID, M_Product_ID,M_AttributeSetInstance_ID, reservationAttributeSetInstance_ID,
						//diffQtyOnHand, 	diffQtyReserved, diffQtyOrdered, trxName
						if (!MStorage.add(getCtx(), pLine.getM_Locator().getM_Warehouse_ID(),
								pLine.getM_Locator_ID(),
								pLine.getM_Product_ID(), 
								ma.getM_AttributeSetInstance_ID(), 0, 
								QtyMA.negate(),Env.ZERO, Env.ZERO, get_TrxName()))
						{
							return false;
						}
						//	Transaction
						mtrx = new MTransaction (getCtx(), pLine.getAD_Org_ID(), MovementType,
								pLine.getM_Locator_ID(), pLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
								QtyMA.negate(), pLine.getParent().getMovementDate(), get_TrxName());
						mtrx.setM_ProductionLine_ID(pLine.getM_ProductionLine_ID());
						BigDecimal qtyreserved = getMovementQty();
						MProductionBatchLine pbLine = MProductionBatchLine.getbyProduct(getM_Production().getM_ProductionBatch_ID(), getM_Product_ID(), getCtx(), get_TrxName());
						pbLine.setQtyReserved(pbLine.getQtyReserved().add(qtyreserved));
						pbLine.saveEx();
						if (!mtrx.save())
						{
							return false;
						}
					}	
				}	
				if (mtrx ==null)
				{
					MAttributeSetInstance asi = null;
					int reservationAttributeSetInstance_ID = pLine.getM_AttributeSetInstance_ID();
					int transactionAttributeSetInstance_ID = pLine.getM_AttributeSetInstance_ID();
					Boolean needSave = false;
					//auto balance negative on hand
					MStorage[] storages = MStorage.getWarehouse(getCtx(), pLine.getM_Locator().getM_Warehouse_ID(), pLine.getM_Product_ID(), 0,
							null, MClient.MMPOLICY_FiFo.equals(product.getMMPolicy()), false, pLine.getM_Locator_ID(), get_TrxName());
					//always create asi so fifo/lifo work.
					if (pLine.getM_AttributeSetInstance_ID() == 0)
					{
						asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
						pLine.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
						transactionAttributeSetInstance_ID = asi.getM_AttributeSetInstance_ID();
						log.config("New ASI=" + pLine);
						needSave = true;
					}	
					BigDecimal reservedDiff = Env.ZERO;
					BigDecimal orderedDiff = Env.ZERO;
						if (MovementType.equals(MTransaction.MOVEMENTTYPE_Production_))
							reservedDiff = movementqty.negate();
						else 
							orderedDiff = movementqty;

					//	Fallback: Update Storage - see also VMatch.createMatchRecord
					if (!MStorage.add(getCtx(), pLine.getM_Locator().getM_Warehouse_ID(),
							pLine.getM_Locator_ID(),
							pLine.getM_Product_ID(),
							transactionAttributeSetInstance_ID, reservationAttributeSetInstance_ID,
						Qty, Env.ZERO, Env.ZERO, get_TrxName()))
					{
						return false;
					}
					//	FallBack: Create Transaction
					mtrx = new MTransaction (getCtx(), pLine.getAD_Org_ID(),
						MovementType, pLine.getM_Locator_ID(),
						pLine.getM_Product_ID(), pLine.getM_AttributeSetInstance_ID(),
						Qty, pLine.getParent().getMovementDate(), get_TrxName());
					mtrx.setM_ProductionLine_ID(pLine.getM_ProductionLine_ID());
					if (!mtrx.save())
					{
						return false;
					}
					BigDecimal qtyreserved = isEndProduct()?getMovementQty():getMovementQty().negate();
					MProductionBatchLine pbLine = MProductionBatchLine.getbyProduct(getM_Production().getM_ProductionBatch_ID(), getM_Product_ID(), getCtx(), get_TrxName());
					pbLine.setQtyReserved(pbLine.getQtyReserved().subtract(qtyreserved));
					pbLine.saveEx();
				}
			}

		}	//	stock movement



		return true;
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
	public int getReversalLine_ID(){
		return -1;
	}
	public BigDecimal getPriceActual(){
		return Env.ZERO;
	}
	public IDocumentLine getReversalDocumentLine(){
		return null;
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
	
}

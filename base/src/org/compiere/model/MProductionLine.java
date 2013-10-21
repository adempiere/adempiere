package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInventoryLineMA;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTransaction;
import org.compiere.model.X_M_ProductionLine;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class MProductionLine extends X_M_ProductionLine {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MProduction parent;


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
		super( header.getCtx(), 0, header.get_TrxName() );
		setM_Production_ID( header.get_ID());
		setAD_Client_ID(header.getAD_Client_ID());
		setAD_Org_ID(header.getAD_Org_ID());
		parent = header;
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

	private int deleteMA() {
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
		if (parent == null )
			parent = new MProduction(getCtx(), getM_Production_ID(), get_TrxName());

		if ( parent.getM_Product_ID() == getM_Product_ID() && parent.getProductionQty().signum() == getMovementQty().signum())
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
		
		if ( !isEndProduct() )
		{
			setMovementQty(getQtyUsed().negate());
		}
		return true;
	}
	
	@Override
	protected boolean beforeDelete() {
		
		deleteMA();
		return true;
	}
	
}

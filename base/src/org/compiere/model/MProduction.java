package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.X_M_Production;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MProduction extends X_M_Production {

	/**
	 * 
	 */
	/** Log								*/
	private static CLogger		m_log = CLogger.getCLogger (MProduction.class);
	private static final long serialVersionUID = 1L;
	private int lineno;
	private int count;

	public MProduction(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
	}

	public MProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MProduction( MOrderLine line ) {
		super( line.getCtx(), 0, line.get_TrxName());
		setAD_Client_ID(line.getAD_Client_ID());
		setAD_Org_ID(line.getAD_Org_ID());
		setMovementDate( line.getDatePromised() );
	}

	

	public MProductionLine[] getLines() {
		ArrayList<MProductionLine> list = new ArrayList<MProductionLine>();
		
		String sql = "SELECT pl.M_ProductionLine_ID "
			+ "FROM M_ProductionLine pl "
			+ "WHERE pl.M_Production_ID = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add( new MProductionLine( getCtx(), rs.getInt(1), get_TrxName() ) );	
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			throw new AdempiereException("Unable to load production lines", ex);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
		MProductionLine[] retValue = new MProductionLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	
	public void deleteLines(String trxName) {

		for (MProductionLine line : getLines())
		{
			line.deleteEx(true);
		}

	}// deleteLines

	public int createLines(boolean mustBeStocked) {
		
		lineno = 100;

		count = 0;

		// product to be produced
		MProduct finishedProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		

		MProductionLine line = new MProductionLine( this );
		line.setLine( lineno );
		line.setM_Product_ID( finishedProduct.get_ID() );
		line.setM_Locator_ID( getM_Locator_ID() );
		line.setMovementQty( getProductionQty());
		line.setPlannedQty(getProductionQty());
		
		line.save();
		count++;
		
		createLines(mustBeStocked, finishedProduct, getProductionQty());
		
		return count;
	}

	private int createLines(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty) {
		
		int defaultLocator = 0;
		
		MLocator finishedLocator = MLocator.get(getCtx(), getM_Locator_ID());
		
		int M_Warehouse_ID = finishedLocator.getM_Warehouse_ID();
		
		int asi = 0;

		// products used in production
		String sql = "SELECT M_ProductBom_ID, BOMQty" + " FROM M_Product_BOM"
				+ " WHERE M_Product_ID=" + finishedProduct.getM_Product_ID() + " ORDER BY Line";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				lineno = lineno + 10;
				int BOMProduct_ID = rs.getInt(1);
				BigDecimal BOMQty = rs.getBigDecimal(2);
				BigDecimal BOMMovementQty = BOMQty.multiply(requiredQty);
				
				MProduct bomproduct = new MProduct(Env.getCtx(), BOMProduct_ID, get_TrxName());
				

				if ( bomproduct.isBOM() && bomproduct.isPhantom() )
				{
					createLines(mustBeStocked, bomproduct, BOMMovementQty);
				}
				else
				{

					defaultLocator = bomproduct.getM_Locator_ID();
					if ( defaultLocator == 0 )
						defaultLocator = getM_Locator_ID();

					if (!bomproduct.isStocked())
					{					
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed(BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.save(get_TrxName());

						lineno = lineno + 10;
						count++;					
					}
					else if (BOMMovementQty.signum() == 0) 
					{
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed( BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.save(get_TrxName());

						lineno = lineno + 10;
						count++;
					}
					else
					{

						// BOM stock info
						MStorage[] storages = null;
						MProduct usedProduct = MProduct.get(getCtx(), BOMProduct_ID);
						defaultLocator = usedProduct.getM_Locator_ID();
						if ( defaultLocator == 0 )
							defaultLocator = getM_Locator_ID();
						if (usedProduct == null || usedProduct.get_ID() == 0)
							return 0;

						MClient client = MClient.get(getCtx());
						MProductCategory pc = MProductCategory.get(getCtx(),
								usedProduct.getM_Product_Category_ID());
						String MMPolicy = pc.getMMPolicy();
						if (MMPolicy == null || MMPolicy.length() == 0) 
						{ 
							MMPolicy = client.getMMPolicy();
						}

						storages = MStorage.getWarehouse(getCtx(), M_Warehouse_ID, BOMProduct_ID, 0, null,
								MProductCategory.MMPOLICY_FiFo.equals(MMPolicy), true, 0, get_TrxName());

						MProductionLine BOMLine = null;
						int prevLoc = -1;
						int previousAttribSet = -1;
						// Create lines from storage until qty is reached
						for (int sl = 0; sl < storages.length; sl++) {

							BigDecimal lineQty = storages[sl].getQtyOnHand();
							if (lineQty.signum() != 0) {
								if (lineQty.compareTo(BOMMovementQty) > 0)
									lineQty = BOMMovementQty;


								int loc = storages[sl].getM_Locator_ID();
								int slASI = storages[sl].getM_AttributeSetInstance_ID();
								int locAttribSet = new MAttributeSetInstance(getCtx(), asi,
										get_TrxName()).getM_AttributeSet_ID();

								// roll up costing attributes if in the same locator
								if (locAttribSet == 0 && previousAttribSet == 0
										&& prevLoc == loc) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(lineQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.save(get_TrxName());

								}
								// otherwise create new line
								else {
									BOMLine = new MProductionLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( loc );
									BOMLine.setQtyUsed( lineQty);
									BOMLine.setPlannedQty( lineQty);
									if ( slASI != 0 && locAttribSet != 0 )  // ie non costing attribute
										BOMLine.setM_AttributeSetInstance_ID(slASI);
									BOMLine.save(get_TrxName());

									lineno = lineno + 10;
									count++;
								}
								prevLoc = loc;
								previousAttribSet = locAttribSet;
								// enough ?
								BOMMovementQty = BOMMovementQty.subtract(lineQty);
								if (BOMMovementQty.signum() == 0)
									break;
							}
						} // for available storages

						// fallback
						if (BOMMovementQty.signum() != 0 ) {
							if (!mustBeStocked)
							{

								// roll up costing attributes if in the same locator
								if ( previousAttribSet == 0
										&& prevLoc == defaultLocator) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(BOMMovementQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.save(get_TrxName());

								}
								// otherwise create new line
								else {

									BOMLine = new MProductionLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( defaultLocator );  
									BOMLine.setQtyUsed( BOMMovementQty);
									BOMLine.setPlannedQty( BOMMovementQty);
									BOMLine.save(get_TrxName());

									lineno = lineno + 10;
									count++;
								}

							}
							else
							{
								throw new AdempiereUserError("Not enough stock of " + BOMProduct_ID);
							}
						}
					}
				}
			} // for all bom products
		} catch (Exception e) {
			throw new AdempiereException("Failed to create production lines", e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return count;
	}
	
	@Override
	protected boolean beforeDelete() {
		deleteLines(get_TrxName());
		return true;
	}

}

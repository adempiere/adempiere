package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

/**
 * Asset Product
 * @author Teo Sarca, SC ARHIPAC SERIVCE SRL
 */
public class MAssetProduct extends X_A_Asset_Product
{
	private static final long serialVersionUID = 1L;

	/** Standard Constructor */
	public MAssetProduct (Properties ctx, int A_Asset_Product_ID, String trxName)
	{
		super (ctx, A_Asset_Product_ID, trxName);
	}
	
	/** Load Constructor */
	public MAssetProduct (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	/**
	 * Set product and ASI
	 * @param M_Product_ID
	 * @param M_ASI_ID
	 */
	public void setProduct(int M_Product_ID, int M_ASI_ID)
	{
		setM_Product_ID(M_Product_ID);
		setM_AttributeSetInstance_ID(M_ASI_ID);
	}
	
	/**
	 * Get/Create Asset Product.
	 * Doesn't save newly create one.
	 * @param ctx
	 * @param A_Asset_ID
	 * @param M_Product_ID
	 * @param M_ASI_ID
	 * @param trxName
	 * @return MAssetProduct
	 */
	public static MAssetProduct getCreate(Properties ctx,
											int A_Asset_ID, int M_Product_ID, int M_ASI_ID,
											String trxName)
	{
		if (M_Product_ID <= 0) {
			return null;
		}
		final String whereClause = COLUMNNAME_A_Asset_ID + "=?"
									+ " AND " + COLUMNNAME_M_Product_ID + "=?"
									+ " AND " + COLUMNNAME_M_AttributeSetInstance_ID + "=?";
		MAssetProduct ap = new Query(ctx, MAssetProduct.Table_Name, whereClause, trxName)
								.setParameters(new Object[]{A_Asset_ID, M_Product_ID, M_ASI_ID})
								.firstOnly();
		// If found, return
		if (ap != null)
			return ap;
		
		// Create new
		ap = new MAssetProduct(ctx, 0, trxName);
		ap.setA_Asset_ID(A_Asset_ID);
		ap.setProduct(M_Product_ID, M_ASI_ID);
		
		return ap;
	}	//	get
	
	/**
	 * Add given qty to current qty
	 * @param qty
	 */
	public void addA_Qty_Current(BigDecimal qty)
	{
		setA_QTY_Current(getA_QTY_Current().add(qty));
	}
	
	/**
	 * Update given asset.
	 * Note: does not save asset
	 */
	public void updateAsset(MAsset asset)
	{
		asset.setM_Product_ID(getM_Product_ID());
		asset.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
	}
}

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  PP Product BOM Model.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 * 
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class MPPProductBOM extends X_PP_Product_BOM
{
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MPPProductBOM.class);
	/**	Cache						*/
	private static CCache<Integer,MPPProductBOM> s_cache = new CCache<Integer,MPPProductBOM>(Table_Name, 40, 5);
	
	/**
	 * Load/Get Product BOM by ID (cached) 
	 * @param ctx
	 * @param PP_Product_BOM_ID
	 * @return product bom
	 */
	public static MPPProductBOM get(Properties ctx, int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID <= 0)
			return null;
		MPPProductBOM bom = s_cache.get(PP_Product_BOM_ID);
		if (bom != null)
			return bom;
		bom = new MPPProductBOM(ctx, PP_Product_BOM_ID, null);
		if (bom.get_ID() == PP_Product_BOM_ID) {
			s_cache.put(PP_Product_BOM_ID, bom);
		}
		else {
			bom = null;
		}
		return bom;
	}

	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPProductBOM(Properties ctx, int PP_Product_BOM_ID,String trxName)
	{
		super (ctx, PP_Product_BOM_ID, trxName);
	}	//	MPPProductBOM


	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPProductBOM(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MOrder

	/**
	 * 	Copy Lines From other BOM
	 *	@param order order
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MPPProductBOM bom)
	{
		if (bom == null)
			return 0;
		MPPProductBOMLine[] fromLines = bom.getLines();
		int count = 0;
		for (int i = 0; i < fromLines.length; i++)
		{
			MPPProductBOMLine line = new MPPProductBOMLine (this);
			PO.copyValues(fromLines[i], line, getAD_Client_ID(), getAD_Org_ID());
			line.setPP_Product_BOM_ID(getPP_Product_BOM_ID());
			if (line.save(get_TrxName()))
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE,"copyLinesFrom - Line difference - From=" + fromLines.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom

	/*************************************************************************/

	/**
	 * BUG #104
	 * @param lines
	 */
	private void setLines(List<MPPProductBOMLine> lines)
	{
		this.lines = lines;
	}

	private List<MPPProductBOMLine> lines = null;

	/**
	 * BUG #? - Does not persist objects!
	 * @param ctx
	 * @param from
	 * @param copyLines
	 * @return
	 */
	public static MPPProductBOM copyFrom(Properties ctx, MPPProductBOM from, boolean copyLines) {
		MPPProductBOM newBom = new MPPProductBOM(ctx, 0,null);
		PO.copyValues(from, newBom, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		newBom.setDocumentNo(null);

		if (copyLines) {
			List<MPPProductBOMLine> newLines = new ArrayList<MPPProductBOMLine>();
			MPPProductBOMLine[] fromLines = from.getLines();
			for (int i = 0; i < fromLines.length; i++) {
				MPPProductBOMLine line = new MPPProductBOMLine(ctx, 0,null);
				PO.copyValues(fromLines[i], line, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
				newLines.add(line);
			}
			newBom.setLines(newLines);
		}

		return newBom;
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPP_ProductBOM[")
		.append(get_ID()).append("-").append(getDocumentNo())
		.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get BOM for Product 
	 *	@param product product
	 *  @param ad_org_id Organization ID
	 *  @param trxName Transaction Name
	 *	@return BOM
	 */
	public static MPPProductBOM get(MProduct product, int ad_org_id, String trxName)
	{
		MPPProductBOM bom =  null;
		Properties ctx = product.getCtx();
		// find Default BOM in Product Data Planning  
		if (ad_org_id > 0 )
		{	
			MPPProductPlanning pp = MPPProductPlanning.get(ctx, product.getAD_Client_ID(),ad_org_id, product.getM_Product_ID(), trxName);
			if(pp != null && pp.getPP_Product_BOM_ID() > 0 )
			{
				bom = new MPPProductBOM(ctx, pp.getPP_Product_BOM_ID(),trxName);
			}
		}	
		if (bom == null)
		{
			//Find BOM with Default Logic where product = bom product and bom value = value 
			bom = getDefault(product, trxName);
		}	

		return bom;

	}	//	getBOM

	/**
	 * 	Get BOM with valid dates for Product 
	 *	@param product product
	 *  @param ad_org_id Organization ID
	 *  @param valid Date to Validate
	 *  @param trxName Transaction Name
	 *	@return BOM
	 */
	public static MPPProductBOM get(MProduct product, int ad_org_id, Timestamp valid, String trxName)
	{
		MPPProductBOM bom = get(product, ad_org_id, trxName);
		if (bom != null && bom.isValidFromTo(valid))
		{	
			return bom;
		}	
		return null;

	}	//	getBOM

	/**
	 * 	Get BOM Lines valid date for Product BOM
	 *  @param valid Date to Validate
	 * 	@return BOM Lines
	 */
	public MPPProductBOMLine[] getLines (Timestamp valid)
	{
		MPPProductBOMLine[] bomlines = getLines(); // All BOM Lines
		List<MPPProductBOMLine> list = new ArrayList<MPPProductBOMLine>(); // Selected BOM Lines Only
		for (MPPProductBOMLine bl : bomlines) {
			if (bl.isValidFromTo(valid)) {
				list.add(bl);
			}
		}
		//
		return list.toArray(new MPPProductBOMLine[list.size()]);
	}	//	getLines

	/**
	 * 	Get BOM Lines for Product BOM
	 * 	@return BOM Lines
	 */
	public  MPPProductBOMLine[] getLines()
	{
		if (this.lines == null)
		{
			final String whereClause = MPPProductBOMLine.COLUMNNAME_PP_Product_BOM_ID+"=?";
			this.lines = new Query(getCtx(), MPPProductBOMLine.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getPP_Product_BOM_ID()})
											.list();
		}
		return this.lines.toArray(new MPPProductBOMLine[this.lines.size()]);
	}	//	getLines    		

	/**
	 * Get PP_Product_BOM_ID for given M_Product_ID
	 * @param M_Product_ID
	 * @return PP_Product_BOM_ID
	 */
	public static int getBOMSearchKey(Properties ctx, MProduct product)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		String sql = "SELECT pb.PP_Product_BOM_ID FROM PP_Product_BOM pb"
						+" WHERE pb.Value = ? AND pb.M_Product_ID=? AND pb.AD_Client_ID = ?";
		return DB.getSQLValue(null, sql, product.getValue(), product.get_ID(), AD_Client_ID);
	}
	
	/**
	 * Get BOM with Default Logic (Product = BOM Product and BOM Value = Product Value) 
	 * @param product
	 * @param trxName
	 * @return product BOM
	 */
	public static MPPProductBOM getDefault(MProduct product, String trxName) {
		return new Query(product.getCtx(), Table_Name, "M_Product_ID=? AND Value=?", trxName)
				.setParameters(new Object[]{product.getM_Product_ID(), product.getValue()})
				.first();
	}
	
	public boolean isValidFromTo(Timestamp date)
	{
		Timestamp validFrom = getValidFrom();
		Timestamp validTo = getValidTo();
		
		if (validFrom != null && date.before(validFrom))
			return false;
		if (validTo != null && date.after(validTo))
			return false;
		return true;
	}

	@Override
	protected boolean afterDelete(boolean success) {
		if (!success)
			return false;
		
		updateProduct();
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success)
			return false;
		
		if (newRecord || is_ValueChanged("IsActive"))
		{
			updateProduct();
		}
		return true;
	}
	
	private void updateProduct()
	{
		int count = new Query(getCtx(), Table_Name, "M_Product_ID=? AND IsActive=?", get_TrxName())
							.setParameters(new Object[]{getM_Product_ID(), "Y"})
							.count();
		MProduct product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		product.setIsBOM(count > 0);
		product.saveEx();
	}
	
	
}	//	MPPProductBOM

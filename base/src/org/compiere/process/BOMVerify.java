/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.core.domains.models.I_PP_Product_BOMLine;
import org.adempiere.core.domains.models.X_PP_Product_BOMLine;
import org.compiere.model.MProduct;
import org.compiere.model.Query;

/**
 * 	Validate BOM
 *	
 *  @author Jorg Janke
 *  @version $Id: BOMVerify.java,v 1.1 2007/07/23 05:34:35 mfuggle Exp $
 */
public class BOMVerify extends BOMVerifyAbstract {
	/**	List of Products	*/
	private ArrayList<MProduct>	foundproducts = new ArrayList<MProduct>();
	private ArrayList<MProduct> validproducts = new ArrayList<MProduct>();
	private ArrayList<MProduct>	invalidproducts = new ArrayList<MProduct>();
	private ArrayList<MProduct> containinvalidproducts = new ArrayList<MProduct>();
	private ArrayList<MProduct> checkedproducts = new ArrayList<MProduct>();
	
	/**
	 * 	Prepare
	 */
	protected void prepare() {
		super.prepare();
		if(getProductId() == 0) {
			setProductId(getRecord_ID());
		}
	}	//	prepare

	/**
	 * 	Get Product List from parameters
	 * 	@return BOM Lines
	 */
	private List<Integer> getProductLists() {
		StringBuffer whereClause = new StringBuffer("IsBOM = 'Y'");
		List<Object> parameters = new ArrayList<>(); 
		if(getProductCategoryId() == 0) {
			whereClause.append(" AND AD_Client_ID = ?");
			parameters.add(getAD_Client_ID());
		} else {
			whereClause.append(" AND M_Product_Category_ID = ?");
			parameters.add(getProductCategoryId());
		}
		//	Re-validate
		if(!isReValidate()) {
			whereClause.append(" AND IsVerified <> 'Y'");
		}
		return new Query(getCtx(), I_M_Product.Table_Name, whereClause.toString(), get_TrxName())
				.setParameters(parameters)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setOrderBy(I_M_Product.COLUMNNAME_Name)
				.getIDsAsList();
	}	//	getLines 
	
	/**
	 * 	Process
	 *	@return Info
	 *	@throws Exception
	 */
	protected String doIt() throws Exception {
		if (getProductId() != 0) {
			log.info("M_Product_ID=" + getProductId());
			checkProduct(new MProduct(getCtx(), getProductId(), get_TrxName()));
			return "Product Checked";
		}
		log.info("M_Product_Category_ID=" + getProductCategoryId()
			+ ", IsReValidate=" + isReValidate());
		//
		AtomicInteger counter = new AtomicInteger(0);
		getProductLists().forEach(productId -> {
			checkProduct(new MProduct(getCtx(), productId, get_TrxName()));
			counter.incrementAndGet();
		});
		return "#" + counter;
	}	//	doIt

	private void checkProduct(MProduct product)
	{
		if (product.isBOM() && !checkedproducts.contains(product))
		{
			validateProduct(product);
		}
		
	}
	
	/**
	 * Get all the Product BOM line for a Component
	 * @param product Product
	 * @return list of MPPProductBOMLine
	 */
	public static List<Integer> getBOMLinesFromProduct(MProduct product) {
		final String whereClause = I_PP_Product_BOMLine.COLUMNNAME_PP_Product_BOM_ID 
							+ " IN ( SELECT PP_PRODUCT_BOM_ID FROM PP_PRODUCT_BOM WHERE M_PRODUCT_ID = " + product.getM_Product_ID() + ")";
		return new Query(product.getCtx(), I_PP_Product_BOMLine.Table_Name, whereClause, product.get_TrxName())
								.setClient_ID()
								.getIDsAsList();
	}
	
	/**
	 * 	Validate Product
	 *	@param product product
	 *	@return Info
	 */
	private boolean validateProduct (MProduct product)
	{
		if (!product.isBOM())
			return false;
		
	
		//	Check Old Product BOM Structure
		log.config(product.getName());
		
		foundproducts.add(product);
		AtomicBoolean containsinvalid = new AtomicBoolean(false);
		AtomicBoolean invalid = new AtomicBoolean(false);
		getBOMLinesFromProduct(product).forEach(productBomLineId -> {
			X_PP_Product_BOMLine productBomLine = new X_PP_Product_BOMLine(getCtx(), productBomLineId, get_TrxName());
			MProduct pp = new MProduct(getCtx(), productBomLine.getM_Product_ID(), get_TrxName());
			if (!pp.isBOM()) {
				log.finer(pp.getName());
			} else  {
				if (validproducts.contains(pp)) {
					//Do nothing, no need to recheck
				}
				if (invalidproducts.contains(pp)) {
					containsinvalid.set(true);
				} else if (foundproducts.contains(pp)) {
					invalid.set(true);
					addLog(0, null, null, product.getValue() + " recursively contains " + pp.getValue());
				} else if(!validateProduct(pp)){
					containsinvalid.set(true);
				}
			}
		});
		//	
		checkedproducts.add(product);
		foundproducts.remove(product);
		if (invalid.get()) {
			invalidproducts.add(product);
			product.setIsVerified(false);
			product.save();
			return false;
		} else if (containsinvalid.get()) {
			containinvalidproducts.add(product);
			product.setIsVerified(false);
			product.save();
			return false;
		} else {
			validproducts.add(product);
			product.setIsVerified(true);
			product.save();
			return true;
		}
	}	//	validateProduct
}	//	BOMValidate


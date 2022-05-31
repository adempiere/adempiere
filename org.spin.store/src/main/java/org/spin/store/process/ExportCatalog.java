/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

package org.spin.store.process;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttribute;
import org.compiere.model.MProduct;
import org.compiere.model.MTaxCategory;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.spin.store.model.MWCategory;
import org.spin.store.util.ElasticSearchHelper;
import org.spin.store.util.support.elasticsearch.Attribute;
import org.spin.store.util.support.elasticsearch.Category;
import org.spin.store.util.support.elasticsearch.Product;
import org.spin.store.util.support.elasticsearch.TaxCategory;

/** 
 * 	Generated Process for (Export Catalog to Elastic Search)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class ExportCatalog extends ExportCatalogAbstract {
	/**	Indicator	*/
	private AtomicInteger created = new AtomicInteger();
	private AtomicInteger errors = new AtomicInteger();
	
	@Override
	protected String doIt() throws Exception {
		try {
			//	Connect
			ElasticSearchHelper.getInstance().withWebStoreId(getStoreId()).connect();
			//	Product
			if(isExportProduct()) {
				AtomicInteger productCounter = new AtomicInteger();
				KeyNamePair [] productArray = DB.getKeyNamePairs("SELECT M_Product_ID, Name "
						+ "FROM M_Product "
						+ "WHERE AD_Client_ID = ? "
						+ "AND IsActive = 'Y' "
						+ "AND IsWebStoreFeatured = 'Y'", false, getAD_Client_ID());
				Arrays.asList(productArray)
					.forEach(productPair -> {
						try {
							MProduct product = MProduct.get(getCtx(), productPair.getKey());
							ElasticSearchHelper.getInstance().index(Product.newInstance().withProduct(product));
							addLog(productPair.getName() + ": @Ok@");
							productCounter.incrementAndGet();
							created.incrementAndGet();
						} catch (Exception e) {
							log.warning("Error: " + e.getLocalizedMessage());
							addLog("@ErrorProcessingFile@ " + productPair.getName() + ": " + e.getLocalizedMessage());
							errors.incrementAndGet();
						}
					});
				addLog("@M_Product_ID@: " + productCounter.get());
			}
			//	Product Category
			if(isExportProductCategory()) {
				AtomicInteger categoryCounter = new AtomicInteger();
				KeyNamePair [] categoryArray = DB.getKeyNamePairs("SELECT W_Category_ID, Name "
						+ "FROM W_Category "
						+ "WHERE AD_Client_ID = ? "
						+ "AND IsActive = 'Y'", false, getAD_Client_ID());
				Arrays.asList(categoryArray)
					.forEach(categoryPair -> {
						try {
							MWCategory category = MWCategory.getById(getCtx(), categoryPair.getKey(), get_TrxName());
							ElasticSearchHelper.getInstance().index(Category.newInstance().withCategoy(category));
							addLog(categoryPair.getName() + ": @Ok@");
							categoryCounter.incrementAndGet();
							created.incrementAndGet();
						} catch (Exception e) {
							log.warning("Error: " + e.getLocalizedMessage());
							addLog("@ErrorProcessingFile@ " + categoryPair.getName() + ": " + e.getLocalizedMessage());
							errors.incrementAndGet();
						}
					});
				addLog("@W_Category_ID@: " + categoryCounter);
			}
			//	Product Attribute
			if(isExportProductAttribute()) {
				AtomicInteger attributeCounter = new AtomicInteger();
				KeyNamePair [] attributeArray = DB.getKeyNamePairs("SELECT M_Attribute_ID, Name "
						+ "FROM M_Attribute "
						+ "WHERE AD_Client_ID = ? "
						+ "AND IsActive = 'Y' "
						+ "AND EXISTS(SELECT 1 FROM M_AttributeValue av WHERE av.M_Attribute_ID = M_Attribute.M_Attribute_ID)", false, getAD_Client_ID());
				Arrays.asList(attributeArray)
					.forEach(attributePair -> {
						try {
							MAttribute attribute = new MAttribute(getCtx(), attributePair.getKey(), get_TrxName());
							ElasticSearchHelper.getInstance().index(Attribute.newInstance().withAttribute(attribute));
							addLog(attributePair.getName() + ": @Ok@");
							attributeCounter.incrementAndGet();
							created.incrementAndGet();
						} catch (Exception e) {
							log.warning("Error: " + e.getLocalizedMessage());
							addLog("@ErrorProcessingFile@ " + attributePair.getName() + ": " + e.getLocalizedMessage());
							errors.incrementAndGet();
						}
					});
				addLog("@M_AttributeSet_ID@: " + attributeCounter);
			}
			//	Tax
			if(isExportTax()) {
				AtomicInteger taxCategoryCounter = new AtomicInteger();
				KeyNamePair [] taxCategoryArray = DB.getKeyNamePairs("SELECT C_TaxCategory_ID, Name "
						+ "FROM C_TaxCategory "
						+ "WHERE AD_Client_ID = ? "
						+ "AND IsActive = 'Y' "
						+ "AND EXISTS(SELECT 1 FROM C_Tax t WHERE t.C_TaxCategory_ID = C_TaxCategory.C_TaxCategory_ID AND (t.IsSalesTax = 'Y' OR t.SOPOType IN('S', 'B')))", false, getAD_Client_ID());
				Arrays.asList(taxCategoryArray)
					.forEach(taxCategoryPair -> {
						try {
							MTaxCategory taxCategory = new MTaxCategory(getCtx(), taxCategoryPair.getKey(), get_TrxName());
							ElasticSearchHelper.getInstance().index(TaxCategory.newInstance().withTaxCategory(taxCategory));
							addLog(taxCategoryPair.getName() + ": @Ok@");
							taxCategoryCounter.incrementAndGet();
							created.incrementAndGet();
						} catch (Exception e) {
							log.warning("Error: " + e.getLocalizedMessage());
							addLog("@ErrorProcessingFile@ " + taxCategoryPair.getName() + ": " + e.getLocalizedMessage());
							errors.incrementAndGet();
						}
					});
				addLog("@C_TaxCategory_ID@: " + taxCategoryCounter);
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		} finally {
			ElasticSearchHelper.getInstance().close();
		}
		return "@Created@: " + created.get() + " - @Errors@: " + errors.get();
	}
}
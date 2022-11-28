/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.util.support.elasticsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.compiere.model.MCountry;
import org.compiere.model.MPriceList;
import org.compiere.model.MStore;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;

/**
 * 	Wrapper for product attribute entity
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class TaxCategory implements IPersistenceWrapper {
	/**	Map	*/
	Map<String, Object> map;
	/**	Product	*/
	private MTaxCategory taxCategory;
	/**	Taxes	*/
	private List<MTax> taxes;
	/**	Web Store	*/
	private MStore webStore;
	/**
	 * Static builder
	 * @return
	 */
	public static TaxCategory newInstance() {
		return new TaxCategory();
	}
	
	/**
	 * Set attribute set
	 * @param taxCategory
	 * @return
	 */
	public TaxCategory withTaxCategory(MTaxCategory taxCategory) {
		this.taxCategory = taxCategory;
		return this;
	}
	
	/**
	 * Private constructor
	 */
	private TaxCategory() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * https://docs.storefrontapi.com/guide/integration/integration.html#category-entity
	 */
	@Override
	public Map<String, Object> getMap() {
		//	Load Taxes
		loadTaxes();
		map = new HashMap<String, Object>();
		map.put("id", taxCategory.getC_TaxCategory_ID());
		map.put("code", taxCategory.getName());
		map.put("label", taxCategory.getName());
		List<Integer> taxesIds = new ArrayList<>();
		List<Map<String, Object>> taxesList = new ArrayList<Map<String,Object>>();
		taxes.forEach(tax -> {
			Map<String, Object> taxMap = new HashMap<String, Object>();
			taxMap.put("id", tax.getC_Tax_ID());
			taxMap.put("code", tax.getTaxIndicator());
			taxMap.put("rate", tax.getRate().doubleValue());
			taxMap.put("tax_postcode", "*");
			if(tax.getC_Country_ID() != 0) {
				taxMap.put("tax_country_id", MCountry.get(taxCategory.getCtx(), tax.getC_Country_ID()).getCountryCode());
			}
			taxesList.add(taxMap);
			taxesIds.add(tax.getC_Tax_ID());
		});
		//	TODO: Add from store
		MPriceList priceList = MPriceList.get(taxCategory.getCtx(), webStore.getM_PriceList_ID(), taxCategory.get_TrxName());
		map.put("calculate_subtotal", priceList.isTaxIncluded());
		map.put("tax_rate_ids", taxesIds);
		map.put("rates", taxesList);
		//	Default
		map.put("is_active", taxCategory.isActive());
		map.put("created_at", ElasticSearch.convertedDate(taxCategory.getCreated().getTime()));
		map.put("updated_at", ElasticSearch.convertedDate(taxCategory.getUpdated().getTime()));
		return map;
	}
	
	/**
	 * Load taxes
	 */
	private void loadTaxes() {
		if(taxes == null) {
			taxes = Arrays.asList(MTax.getAll(taxCategory.getCtx()))
				.stream()
				.filter(tax -> tax.getC_TaxCategory_ID() == taxCategory.getC_TaxCategory_ID())
				.filter(tax -> tax.isSalesTax() || tax.getSOPOType().equals(MTax.SOPOTYPE_SalesTax) || tax.getSOPOType().equals(MTax.SOPOTYPE_Both))
				.collect(Collectors.toList());
		}
	}
	
	/**
	 * Validate if is valid
	 * @return
	 */
	@Override
	public boolean isValid() {
		loadTaxes();
		return taxes != null && taxes.size() > 0;
	}

	@Override
	public String getKeyValue() {
		if(taxCategory == null) {
			return "";
		}
		//	Return ID
		return String.valueOf(taxCategory.get_ID());
	}

	@Override
	public String getCatalogName() {
		return "taxrule";
	}

	@Override
	public IPersistenceWrapper withWebStoreId(int webStoreId) {
		if(webStoreId > 0) {
			webStore = MStore.get(taxCategory.getCtx(), webStoreId);
		}
		return this;
	}

	@Override
	public Map<String, Object> getMapping() {
		return null;
	}
}

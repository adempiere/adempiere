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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MClientInfo;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.MStorage;
import org.compiere.model.MStore;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.manufacturing.model.MPPProductBOM;
import org.spin.model.MADAttachmentReference;
import org.spin.store.model.MWCategory;

/**
 * 	Wrapper for product entity
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class Product implements IPersistenceWrapper {
	/**	Map	*/
	Map<String, Object> map;
	/**	Product	*/
	private MProduct product;
	/**	Web Store	*/
	private MStore webStore;
	
	/**
	 * Static builder
	 * @return
	 */
	public static Product newInstance() {
		return new Product();
	}
	
	/**
	 * Set product for convert
	 * @param product
	 * @return
	 */
	public Product withProduct(MProduct product) {
		this.product = product;
		return this;
	}
	
	/**
	 * Private constructor
	 */
	private Product() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * https://docs.storefrontapi.com/guide/integration/format-product.html#product-entity
	 */
	@Override
	public Map<String, Object> getMap() {
		map = new HashMap<String, Object>();
		if(Optional.ofNullable(product).isPresent()) {
			map.put("id", product.getM_Product_ID());
			map.put("name", product.getName());
			//	Get Image
			map.put("image", getImageName(product));
			map.put("sku", product.getSKU());
			map.put("url_key", getValidValue(product.getName()));
			map.put("type_id", getProductTypeFromProduct(product));
			map.put("price", getPrice(product));
			map.put("short_description", product.getDescription());
			map.put("description", product.getHelp());
//		    "special_price": 0,
//		    "price_incl_tax": null,
//		    "special_price_incl_tax": null,
//		    "special_to_date": null,
//		    "special_from_date": null,
			//	Status
			map.put("status", product.isActive()? "1": "2");
			//	Visibility
			//	Not Visible = 1
			//	In Catalog = 2
			//	Search = 3
			//	Both = 4
			map.put("visibility", "4");
//			"size": null,
//		    "color": null,
//		    "size_options": [
//		      167,
//		      168,
//		      169,
//		      170,
//		      171
//		    ],
//		    "color_options": [
//		      50,
//		      58,
//		      60
//		    ],
			//	Category
			List<Map<String, Object>> categories = new ArrayList<>();
			List<String> categoriesIds = new ArrayList<>();
			//	Groups
			List<MWCategory> productGroups = MWCategory.getOfProduct(product.getCtx(), product.getM_Product_ID(), product.get_TrxName());
			StringBuffer urlPath = new StringBuffer();
			if(productGroups != null
					&& productGroups.size() > 0) {
				productGroups.forEach(group -> {
					Category wrapperOfGroup = Category.newInstance().withCategoy(group);
//					categoryMap.put("id", group.getW_Category_ID());
//					categoryMap.put("category_id", group.getW_Category_ID());
//					categoryMap.put("name", group.getName());
//					categoryMap.put("slug", wrapperOfGroup.getURLKey(true));
//					categoryMap.put("path", wrapperOfGroup.getURLPath());
					//	Add
					categories.add(wrapperOfGroup.getMap());
					categoriesIds.add(wrapperOfGroup.getKeyValue());
					if(urlPath.length() == 0) {
						urlPath.append(wrapperOfGroup.getURLPath());
					}
				});
			}
			urlPath.append("/").append(getValidValue(product.getName() + "-" + product.getM_Product_ID())).append(".html");
			map.put("slug", getValidValue(product.getName() + "-" + product.getM_Product_ID()));
			map.put("url_path", urlPath.toString());
			//	Categories
			map.put("category_ids", categoriesIds);
			map.put("category", categories);
			//	Media Gallery
			List<Map<String, Object>> mediaList = new ArrayList<Map<String,Object>>();
			MClientInfo clientInfo = MClientInfo.get(product.getCtx());
			if(clientInfo.getFileHandler_ID() != 0) {
				AtomicInteger counter = new AtomicInteger(1);
				Optional.ofNullable(product.getAttachment()).ifPresent(attachment -> {
					MADAttachmentReference.getListByAttachmentId(product.getCtx(), clientInfo.getFileHandler_ID(), attachment.getAD_Attachment_ID(), product.get_TrxName())
					.forEach(attachmentReference -> {
						Map<String, Object> mediaMap = new HashMap<String, Object>();
						mediaMap.put("image", "/" + attachmentReference.getValidFileName());
						mediaMap.put("pos", counter.getAndIncrement());
						//	TODO: Add additional attributes
						//	lab, vid
						mediaList.add(mediaMap);
					});
				});
			}
			map.put("media_gallery", mediaList);
			//	For Product Attributes
			if(product.getM_AttributeSet_ID() != 0) {
				List<Map<String, Object>> configurableList = new ArrayList<Map<String,Object>>();
				AtomicInteger counter = new AtomicInteger(1);
				MAttributeSet attributeSet = MAttributeSet.get(product.getCtx(), product.getM_AttributeSet_ID());
				Arrays.asList(attributeSet.getMAttributes(true)).forEach(attribute -> {
					Map<String, Object> attributeMap = new HashMap<String, Object>();
					attributeMap.put("id", attribute.getM_Attribute_ID());
					attributeMap.put("attribute_id", attribute.getM_Attribute_ID());
					attributeMap.put("label", attribute.getName());
					attributeMap.put("pos", counter.getAndIncrement());
					//	For list of values
					if(attribute.getAttributeValueType().equals(MAttribute.ATTRIBUTEVALUETYPE_List)) {
						List<Map<String, Object>> attributeValuesList = new ArrayList<Map<String,Object>>();
						Arrays.asList(attribute.getMAttributeValues()).forEach(attributeValue -> {
							Map<String, Object> attributeValueMap = new HashMap<String, Object>();
							attributeValueMap.put("value_index", attributeValue.getM_AttributeValue_ID());
							attributeValueMap.put("label", attributeValue.getName());
							attributeValuesList.add(attributeValueMap);
						});
						attributeMap.put("values", attributeValuesList);
					}
					attributeMap.put("product_id", product.getM_Product_ID());
					attributeMap.put("attribute_code", attribute.getName().replaceAll(" ", ""));
					configurableList.add(attributeMap);
				});
			}
			//	Stock
			Map<String, Object> stockMap = new HashMap<String, Object>();
			stockMap.put("is_in_stock", product.isStocked());
			stockMap.put("qty", getStock(product));
			map.put("stock", stockMap);
			//	BOM
//		    "configurable_children": [
//		      {
//		        "type_id": null,
//		        "sku": "WT06-XS-Blue",
//		        "special_price": 0,
//		        "special_to_date": null,
//		        "special_from_date": null,
//		        "name": "Chloe Compete Tank-XS-Blue - tier price",
//		        "price": 39,
//		        "price_incl_tax": null,
//		        "special_price_incl_tax": null,
//		        "id": 1754,
//		        "image": "/w/t/wt06-blue_main.jpg",
//		        "url_key": "chloe-compete-tank-xs-blue",
//		        "url_path": null,
//		        "status": 1,
//		        "size": "167",
//		        "color": "50"
//		      }
//		    ]
			if(product.isBOM()
					&& product.isVerified()) {
				map.put("configurable_children", getBillOfMaterial(product));
			}
			//	Default
			map.put("is_active", product.isActive());
			map.put("created_at", ElasticSearch.convertedDate(product.getCreated().getTime()));
			map.put("updated_at", ElasticSearch.convertedDate(product.getUpdated().getTime()));
		};
		return map;
	}
	
	/**
	 * Get Image Name
	 * @param product
	 * @return
	 */
	private String getImageName(MProduct product) {
		MClientInfo clientInfo = MClientInfo.get(product.getCtx());
		AtomicReference<String> imageName = new AtomicReference<String>();
		if(clientInfo.getFileHandler_ID() != 0) {
			Optional.ofNullable(product.getAttachment()).ifPresent(attachment -> {
				Optional<MADAttachmentReference> maybeReference = MADAttachmentReference.getListByAttachmentId(
						product.getCtx(), 
						clientInfo.getFileHandler_ID(), 
						attachment.getAD_Attachment_ID(), 
						product.get_TrxName()).stream().findFirst();
				if(maybeReference.isPresent()) {
					imageName.set("/" + maybeReference.get().getValidFileName());
				}
			});	
		}
		return imageName.get();
	}
	
	/**
	 * Get Bill Of Material
	 * @param product
	 * @return
	 */
	private List<Map<String, Object>> getBillOfMaterial(MProduct product) {
		List<Map<String, Object>> bomList = new ArrayList<Map<String,Object>>();
		MPPProductBOM billOfMaterial = MPPProductBOM.getDefault(product, product.get_TrxName());
		if(Optional.ofNullable(billOfMaterial).isPresent()) {
			Map<String, Object> childMap = new HashMap<String, Object>();
			Arrays.asList(billOfMaterial.getLines()).forEach(child -> {
				MProduct childProduct = MProduct.get(product.getCtx(), child.getM_Product_ID());
				childMap.put("id", childProduct.getM_Product_ID());
				childMap.put("sku", childProduct.getSKU());
				childMap.put("name", childProduct.getName());
				childMap.put("url_key", getValidValue(childProduct.getName()));
				childMap.put("url_path", childProduct.getDescriptionURL());
				childMap.put("status", getProductTypeFromProduct(childProduct));
				childMap.put("image", getImageName(childProduct));
				childMap.put("price", getPrice(childProduct));
				childMap.put("type_id", getProductTypeFromProduct(childProduct));
				//	Add to list
				bomList.add(childMap);
			});
		}
		//	
		return bomList;
	}
	
	/**
	 * Get price from product
	 * @param product
	 * @return
	 */
	private double getPrice(MProduct product) {
		MPriceList priceList = MPriceList.get(product.getCtx(), webStore.getM_PriceList_ID(), product.get_TrxName());
		//	Get Valid From
		Timestamp validFrom = TimeUtil.getDay(System.currentTimeMillis());
		//	Get Price
		MProductPricing productPricing = new MProductPricing(product.getM_Product_ID(), 0, Env.ZERO, true, product.get_TrxName());
		productPricing.setM_PriceList_ID(priceList.getM_PriceList_ID());
		productPricing.setPriceDate(validFrom);
		return productPricing.getPriceStd().setScale(productPricing.getPrecision()).doubleValue();
	}
	
	/**
	 * Get Valid Value
	 * @param value
	 * @return
	 */
	private String getValidValue(String value) {
		return value.trim()
				.toLowerCase()
				.replaceAll(" ", "-")
				.replaceAll("[+^:,.&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$()/]", "");
	}
	
	/**
	 * Get Stock from product
	 * @param product
	 * @return
	 */
	private double getStock(MProduct product) {
		Optional<MStorage> maybeStorage = Arrays.asList(MStorage.getOfProduct(Env.getCtx(), product.getM_Product_ID(), product.get_TrxName()))
				.stream()
				.filter(storage -> storage.getQtyOnHand().signum() > 0)
				.filter(storage -> storage.getM_Warehouse_ID() == webStore.getM_Warehouse_ID())
				.findFirst();
		if(maybeStorage.isPresent()) {
			BigDecimal quantityOnHand = Env.ZERO;
			BigDecimal quantityReserved = Env.ZERO;
			//	On hand
			if(maybeStorage.get().getQtyOnHand() != null) {
				quantityOnHand = maybeStorage.get().getQtyOnHand(); 
			}
			//	Reserved
			if(maybeStorage.get().getQtyReserved() != null) {
				quantityReserved = maybeStorage.get().getQtyReserved();
			}
			if(quantityReserved.signum() < 0) {
				return 0.0;
			}
			//	Get Double
			return quantityOnHand.doubleValue();
		}
		//	
		return 0.0;
	}

	@Override
	public String getKeyValue() {
		if(product == null) {
			return "";
		}
		//	Return ID
		return String.valueOf(product.getM_Product_ID());
	}
	
	/**
	 * Get Render Product Type from Product
	 * @param product
	 * @return
	 */
	private String getProductTypeFromProduct(MProduct product) {
		if(Util.isEmpty(product.getProductType())) {
			return "simple";
		}
		//	Configurable Product
		if(product.getProductType().equals(MProduct.PRODUCTTYPE_Item)
				&& product.isStocked()
				&& product.getM_AttributeSet_ID() > 0) {
			return "configurable";
		}
		//	Grouped Product
		if(product.getProductType().equals(MProduct.PRODUCTTYPE_Item)
				&& product.isStocked()
				&& product.isBOM()) {
			return "grouped";
		}
		//	Virtual Product
		if(product.getProductType().equals(MProduct.PRODUCTTYPE_Service)) {
			return "configurable";
		}
		//	Bundle Product
		if(product.getProductType().equals(MProduct.PRODUCTTYPE_Item)
				&& !product.isStocked()
				&& product.isBOM()) {
			return "grouped";
		}
		//	Downloadable Product
		if(product.getProductType().equals(MProduct.PRODUCTTYPE_Service)
				&& product.getM_Product_Category().getA_Asset_Group_ID() > 0) {
			return "downloadable";
		}
		//	Gift Cards
		if((product.getProductType().equals(MProduct.PRODUCTTYPE_Item)
				|| product.getProductType().equals(MProduct.PRODUCTTYPE_Service))
				&& product.getR_MailText_ID() > 0) {
			return "gift";
		}
		//	Simple Product
		return "simple";
	}
	
	@Override
	public String getCatalogName() {
		return "product";
	}

	@Override
	public boolean isValid() {
		if(product == null
				|| webStore == null) {
			return false;
		}
		return true;
	}

	@Override
	public IPersistenceWrapper withWebStoreId(int webStoreId) {
		if(webStoreId > 0) {
			webStore = MStore.get(product.getCtx(), webStoreId);
		}
		return this;
	}

	@Override
	public Map<String, Object> getMapping() {
		Map<String, Object> createdAt = new HashMap<>();
		createdAt.put("type", "date");
		createdAt.put("format", ElasticSearch.DATE_FORMAT);
		Map<String, Object> updatedAt = new HashMap<>();
		updatedAt.put("type", "date");
		updatedAt.put("format", ElasticSearch.DATE_FORMAT);
		Map<String, Object> properties = new HashMap<>();
		properties.put("created_at", createdAt);
		properties.put("updated_at", updatedAt);
		Map<String, Object> slug = new HashMap<>();
		slug.put("type", "keyword");
		properties.put("slug", slug);
		Map<String, Object> urlPath = new HashMap<>();
		urlPath.put("type", "keyword");
		properties.put("url_path", urlPath);
		Map<String, Object> urlKey = new HashMap<>();
		urlKey.put("type", "keyword");
		properties.put("url_key", urlKey);
		Map<String, Object> sku = new HashMap<>();
		sku.put("type", "keyword");
		properties.put("sku", sku);
		Map<String, Object> price = new HashMap<>();
		price.put("type", "float");
		properties.put("price", price);
		Map<String, Object> finalPrice = new HashMap<>();
		finalPrice.put("type", "float");
		properties.put("final_price", finalPrice);
		Map<String, Object> sizeOptions = new HashMap<>();
		sizeOptions.put("type", "integer");
		properties.put("size_options", sizeOptions);
		//	Return mapping properties
		Map<String, Object> mapping = new HashMap<>();
		mapping.put("properties", properties);
		return mapping;
	}
}

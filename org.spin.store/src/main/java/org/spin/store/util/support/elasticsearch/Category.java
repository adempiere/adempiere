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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.compiere.util.CLogger;
import org.spin.store.model.MWCategory;

/**
 * 	Wrapper for product category entity
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class Category implements IPersistenceWrapper {
	/**	Map	*/
	Map<String, Object> map;
	/**	Product	*/
	private MWCategory category;
	/** Parent List	*/
	private List<Integer> parents;
	/** Logger */
	private static CLogger log = CLogger.getCLogger(Category.class);
	
	/**
	 * Static builder
	 * @return
	 */
	public static Category newInstance() {
		return new Category();
	}
	
	/**
	 * Set category
	 * @param category
	 * @return
	 */
	public Category withCategoy(MWCategory category) {
		this.category = category;
		return this;
	}
	
	/**
	 * Private constructor
	 */
	private Category() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * https://docs.storefrontapi.com/guide/integration/integration.html#category-entity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getMap() {
		map = new HashMap<String, Object>();
		if(Optional.ofNullable(category).isPresent()) {
			map.put("id", category.get_ID());
			map.put("name", category.getName());
			map.put("url_key", getURLKey(category, true));
			map.put("slug", getURLKey(category, true));
			map.put("path", getPath());
			map.put("url_path", getURLPath());
			map.put("position", getLevel());
			Map<String, Object> child = getChild(category);
			int childrenCount = 0;
			if(child.containsKey("children_data")) {
				List<Map<String,Object>> children = (List<Map<String,Object>>) child.get("children_data");
				map.put("children_data", child.get("children_data"));
				childrenCount = children.size();
			}
			map.put("children_count", childrenCount);
			if(category.getW_Category_Parent_ID() != 0) {
				map.put("parent_id", category.getW_Category_Parent_ID());
			}
			//	Find level
			map.put("level", getLevel());
			//	Product Count
			map.put("product_count", category.getProductCount());
			//	TODO: Add from ADempiere
//			map.put("include_in_menu", true);
			//	Default
			map.put("is_active", category.isActive() && category.isWebStoreFeatured());
			map.put("created_at", ElasticSearch.convertedDate(category.getCreated().getTime()));
			map.put("updated_at", ElasticSearch.convertedDate(category.getUpdated().getTime()));

		}
		return map;
	}
	
	/**
	 * Load parents
	 */
	private void loadParents() {
		if(parents == null) {
			parents = new ArrayList<Integer>();
			parents.add(category.getW_Category_ID());
			loadParents(category);
			Collections.reverse(parents);
		}
	}
	
	/**
	 * Load Parents
	 */
	private void loadParents(MWCategory child) {
		if(child.getW_Category_Parent_ID() != 0) {
			MWCategory parent = (MWCategory) child.getW_Category_Parent();
			if(parent != null) {
				//	Prevent a StackOverFlow
				if(!parents.contains(parent.getW_Category_ID())) {
					parents.add(parent.getW_Category_ID());
					loadParents(parent);
				} else {
					log.warning("Possible recursive call: " + child.getValue() + " - " + child.getName());
				}
			}
		}
	}
	
	/**
	 * Get URL Key
	 * @param withId
	 * @return
	 */
	public String getURLKey(boolean withId) {
		return getURLKey(category, withId);
	}
	
	/**
	 * Get URL Key
	 * @param category
	 * @return
	 */
	private String getURLKey(MWCategory category, boolean withId) {
		String value = getValidValue(category.getName());
		if(withId) {
			value = value + "-" + String.valueOf(category.getW_Category_ID());
		}
		return value;
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
	 * Get Level
	 * @param category
	 * @param currentLevel
	 * @return
	 */
	public int getLevel() {
		loadParents();
		int level = parents.size() + 2;
		if(category.getW_Category_Parent_ID() == 0) {
			level = 2;
		}
		return level;
	}
	
	/**
	 * Get URL Path
	 * @param category
	 * @return
	 */
	public String getURLPath() {
		loadParents();
		StringBuffer  urlPath = new StringBuffer();
		parents.forEach(categoryId -> {
			MWCategory categoryForUrl = MWCategory.getById(category.getCtx(), categoryId, category.get_TrxName());
			if(urlPath.length() > 0) {
				urlPath.append("/");
			}
			urlPath.append(getURLKey(categoryForUrl, categoryId == category.getW_Category_ID()));
		});
		//	Return path
		return urlPath.toString();
	}
	
	/**
	 * Get Path
	 * @param category
	 * @return
	 */
	public String getPath() {
		loadParents();
		StringBuffer  path = new StringBuffer();
		parents.forEach(categoryId -> {
			MWCategory categoryForPath = MWCategory.getById(category.getCtx(), categoryId, category.get_TrxName());
			if(path.length() > 0) {
				path.append("/");
			}
			path.append(categoryForPath.getW_Category_ID());
		});
		//	REturn path
		return path.toString();
	}
	
	/**
	 * Get Child
	 * @param category
	 * @param currentLevel
	 * @return
	 */
	private Map<String, Object> getChild(MWCategory category) {
		Map<String, Object> mapOfChild = new HashMap<String, Object>();
		List<Map<String, Object>> currentChildList = new ArrayList<Map<String,Object>>();
		category.getChildList().forEach(child -> {
			Map<String, Object> childMap = Category.newInstance().withCategoy(child).getMap();
			currentChildList.add(childMap);
		});
		mapOfChild.put("children_data", currentChildList);
		return mapOfChild;
	}

	@Override
	public String getKeyValue() {
		if(category == null) {
			return "";
		}
		//	Return ID
		return String.valueOf(category.get_ID());
	}

	@Override
	public String getCatalogName() {
		return "category";
	}

	@Override
	public boolean isValid() {
		return category != null;
	}

	@Override
	public IPersistenceWrapper withWebStoreId(int webStoreId) {
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
		//	Return mapping properties
		Map<String, Object> mapping = new HashMap<>();
		mapping.put("properties", properties);
		return mapping;
	}
}

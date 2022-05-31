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
import java.util.Optional;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeValue;

/**
 * 	Wrapper for product attribute entity
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class Attribute implements IPersistenceWrapper {
	/**	Map	*/
	Map<String, Object> map;
	/**	Product	*/
	private MAttribute attribute;
	/**	Attributes values	*/
	private List<MAttributeValue> attributeValues;
	
	/**
	 * Static builder
	 * @return
	 */
	public static Attribute newInstance() {
		return new Attribute();
	}
	
	/**
	 * Set attribute set
	 * @param attribute
	 * @return
	 */
	public Attribute withAttribute(MAttribute attribute) {
		this.attribute = attribute;
		return this;
	}
	
	/**
	 * Private constructor
	 */
	private Attribute() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * https://docs.storefrontapi.com/guide/integration/integration.html#category-entity
	 */
	@Override
	public Map<String, Object> getMap() {
		map = new HashMap<String, Object>();
		//	Instance
		Map<String, Object> attributeMap = new HashMap<String, Object>();
		map.put("id", attribute.getM_Attribute_ID());
		//	TODO: Make this dynamic
		map.put("is_user_defined", true);
		map.put("is_visible", true);
		map.put("attribute_code", getValidValue(attribute.getName()));
		map.put("default_frontend_label", attribute.getName());
		//	For list of values
		if(attribute.getAttributeValueType().equals(MAttribute.ATTRIBUTEVALUETYPE_List)) {
			List<Map<String, Object>> attributeValuesList = new ArrayList<Map<String,Object>>();
			Arrays.asList(attribute.getMAttributeValues())
				.stream().filter(attributeValue -> Optional.ofNullable(attributeValue).isPresent())
				.forEach(attributeValue -> {
				Map<String, Object> attributeValueMap = new HashMap<String, Object>();
				attributeValueMap.put("value", String.valueOf(attributeValue.getM_AttributeValue_ID()));
				attributeValueMap.put("label", attributeValue.getName());
				attributeValuesList.add(attributeValueMap);
			});
			if(attributeValuesList.size() == 2) {
				attributeMap.put("frontend_input", "boolean");
			} else {
				attributeMap.put("frontend_input", "select");
			}
			map.put("options", attributeValuesList);
		}
		//	Default
		map.put("is_active", attribute.isActive());
		map.put("created_at", ElasticSearch.convertedDate(attribute.getCreated().getTime()));
		map.put("updated_at", ElasticSearch.convertedDate(attribute.getUpdated().getTime()));
		return map;
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

	@Override
	public String getKeyValue() {
		if(attribute == null) {
			return "";
		}
		//	Return ID
		return String.valueOf(attribute.get_ID());
	}

	@Override
	public String getCatalogName() {
		return "attribute";
	}

	@Override
	public boolean isValid() {
		//	Null object
		if(attribute == null) {
			return false;
		}
		//	Only List
		if(!attribute.getAttributeValueType().equals(MAttribute.ATTRIBUTEVALUETYPE_List)) {
			return false;
		}
		//	
		if(attributeValues == null
				&& attribute.getMAttributeValues() != null
				&& attribute.getMAttributeValues().length > (attribute.isMandatory()? 0: 1)) {
			attributeValues = Arrays.asList(attribute.getMAttributeValues());
		}
		return attributeValues != null && attributeValues.size() > 0;
	}

	@Override
	public IPersistenceWrapper withWebStoreId(int webStoreId) {
		return this;
	}

	@Override
	public Map<String, Object> getMapping() {
		return null;
	}
}

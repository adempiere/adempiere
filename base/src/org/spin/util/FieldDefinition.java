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
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. 			  *
 * All Rights Reserved. 													  *
 * Contributor(s): Raul Muñoz www.erpya.com				  		          	  *
 ******************************************************************************/
package org.spin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.compiere.model.GridFieldVO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.spin.model.I_AD_FieldCondition;
import org.spin.model.MADFieldCondition;

/**
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1697">
 * 		@see FR [ 1697 ] Add definition for change style</a>
 *
 */
public class FieldDefinition {


	/**	 Field Definition		*/
	private static FieldDefinition fieldDefinition;
	/**	 Field Definition Map	*/
	private static HashMap<GridFieldVO, FieldDefinition> fieldDefinitionMap = new HashMap<GridFieldVO, FieldDefinition>();
	/**	 Field Condition Valid	*/
	private FieldCondition fieldConditionValid = null;
	/**	 Grid FieldVO			*/
	private GridFieldVO gridFielVO;
	/**	 Field Condition List	*/
	private List<FieldCondition> fieldConditionList = new ArrayList<FieldCondition>();

	private FieldDefinition(GridFieldVO gridFielVO) {
		this.gridFielVO = gridFielVO; 
		fieldDefinitionMap.put(gridFielVO, this);
		addFieldCondition(gridFielVO.AD_FieldDefinition_ID);
	}

	public static FieldDefinition getInstance(GridFieldVO gridFielVO) {
		 if(fieldDefinitionMap.get(gridFielVO) == null)
			fieldDefinition = new FieldDefinition(gridFielVO);
		 else {
			 fieldDefinition = fieldDefinitionMap.get(gridFielVO);
		}
		return fieldDefinition;
	}

	/**
	 * Add new Field Condition to list for availability
	 * @param fieldCondition
	 */
	private void addFieldCondition(FieldCondition fieldCondition) {
		if(fieldCondition == null)
			return;
		
		fieldConditionList.add(fieldCondition);
    }
	
	/**
	 * Add new Field Condition to list for availability
	 * @param fieldCondition
	 */
	public void addFieldCondition(int fieldConditionId) {
		
		if(fieldConditionId == 0) {
			return;
		}
		List<MADFieldCondition> fieldConditionLists = new Query(Env.getCtx(), I_AD_FieldCondition.Table_Name, "AD_FieldDefinition_ID = ?", null)
				.setParameters(fieldConditionId)
				.list();
		
		for(MADFieldCondition fieldCondition : fieldConditionLists) {
			FieldCondition condition = new FieldCondition (fieldCondition, gridFielVO);
			addFieldCondition(condition);
		}
		//
    }
	
	/**
	 * Get Condition List
	 * @return List FieldCondtion
	 */
	public List<FieldCondition> getConditionList(){
		return fieldConditionList;
	}
	
	/**
	 * Process Condition
	 * @return FieldCondition
	 */
	public FieldCondition processCondition() {
		fieldConditionList.stream().forEach(k -> {
			if(k.isValid()) {
				fieldConditionValid = k;
				return; 
			}
		});
		return fieldConditionValid;
	}

	/**
	 * Get Field Condition Valid
	 * @return FieldCondition
	 */
	public FieldCondition getConditionValid(HashMap<String,Object> columnvalue) {
		
		fieldConditionList.stream().forEach(k -> {
			if(k.isValid(columnvalue)) {
				fieldConditionValid = k;
				return; 
			}
		});
		return fieldConditionValid;
	}
}

/**
 * 
 */
package org.spin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.compiere.model.GridFieldVO;
import org.compiere.model.I_AD_FieldCondition;
import org.compiere.model.MFieldCondition;
import org.compiere.model.Query;
import org.compiere.util.Env;

/**
 * @author tt-02
 *
 */
public class FieldDefinition {

	private static FieldDefinition fieldDefinition;
	
	private FieldCondition fieldConditionValid = null;
	
	
	/**	DeviceManager Map	*/
	private List<FieldCondition> fieldConditionList = new ArrayList<FieldCondition>();
	
	private FieldDefinition(GridFieldVO gridFielVO) {
		List<MFieldCondition> fieldConditionLists = new Query(Env.getCtx(), I_AD_FieldCondition.Table_Name, "AD_FieldDefinition_ID = "+gridFielVO.AD_FieldDefinition_ID, null).list();
		
		for(MFieldCondition fieldCondition : fieldConditionLists) {
			FieldCondition condition = new FieldCondition (fieldCondition, gridFielVO);
			addFieldCondition(condition);
		}

	}
	
	
	public static FieldDefinition getInstance(GridFieldVO gridFielVO) {
		if(fieldDefinition == null)
			fieldDefinition = new FieldDefinition(gridFielVO);
		
		return fieldDefinition;
	}
	
	/**
	 * Add new Field Condition to list for availability
	 * @param fieldCondition
	 */
	private void addFieldCondition(FieldCondition fieldCondition) {
		if(fieldCondition == null) {
			return;
		}
		//
		fieldConditionList.add(fieldCondition);
    }
	
	public List<FieldCondition> getConditionList(){
		return fieldConditionList;
	}
	
	public FieldCondition processCondition() {
		fieldConditionList.stream().forEach(k -> {
			if(k.isValid()) {
				fieldConditionValid = k;
				return; 
			}
		});
		return fieldConditionValid;
	}

	public FieldCondition isConditionValid(HashMap<String,String> columnvalue) {
		
		fieldConditionList.stream().forEach(k -> {
			if(k.isValid(columnvalue)) {
				System.out.println(k);
				fieldConditionValid = k;
				return; 
			}
		});
		return fieldConditionValid;
	}
}

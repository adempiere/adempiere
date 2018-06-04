package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Evaluator;

public class MFieldDefinition extends X_AD_FieldDefinition {

	
	

	public MFieldDefinition(Properties ctx, int AD_FieldDefinition_ID,
			String trxName) {
		super(ctx, AD_FieldDefinition_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -680533880971968911L;

	public MFieldDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public List<MFieldCondition> getConditions() {
		List<MFieldCondition> list = new Query(Env.getCtx(), I_AD_FieldCondition.Table_Name, I_AD_FieldCondition.COLUMNNAME_AD_FieldDefinition_ID+"=?", get_TrxName())
			.setParameters(getAD_FieldDefinition_ID())
			.setOrderBy(MFieldCondition.COLUMNNAME_AD_FieldCondition_ID)
			.list();
		return list;
	}
	

	public List<String> getGroupCondition() {
		

		ArrayList<String> list = new ArrayList<String>();
		//  Display
//		List<MFieldCondition> conditions = getConditions();
//		for( MFieldCondition condition : conditions  ){ 
//			Evaluator.parseDepends(list, condition.getCondition());
//		}
		return list;
	}
}

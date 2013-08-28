package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MSalesHistory extends X_C_SalesHistory {

	
	static private List<MSalesHistory> getSalesByDefinitionLine(MPPForecastDefinitionLine line , Timestamp from , Timestamp to)
	{
		StringBuffer where = new StringBuffer(); 
		List<Object> parameters = new ArrayList<Object>();
		
		//Product filters
		if (line.getM_Product_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_ID());
		}	
		
		if (line.getM_Product_Category_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Category_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Category_ID());
		}	
		
		if (line.getM_Product_Classification_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Classification_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Classification_ID());
		}	
		
		if (line.getM_Product_Class_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Class_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Class_ID());
		}	
		
		if (line.getM_Product_Group_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Group_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Group_ID());
		}	
		
		//BPartner filter		
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_BPartner_ID);
			where.append(" AND ");
			parameters.add(line.getC_BPartner_ID());
		}	
		if (line.getC_BP_Group_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_BP_Group_ID);
			where.append(" AND ");
			parameters.add(line.getC_BP_Group_ID());
		}	
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_SalesRegion_ID);
			where.append(" AND ");
			parameters.add(line.getC_SalesRegion_ID());
		}			
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_Campaign_ID);
			where.append(" AND ");
			parameters.add(line.getC_Campaign_ID());
		}	
		
		where.append(MSalesHistory.COLUMNNAME_DateInvoiced);
		where.append(" BETWEEN ? AND ?");
		parameters.add(from);
		parameters.add(to);
		
		return new Query(line.getCtx(), MSalesHistory.Table_Name , where.toString() , line.get_TrxName())
		.setClient_ID()
		.setApplyAccessFilter(true)
		.setParameters(parameters)
		.setOrderBy(MSalesHistory.COLUMNNAME_DateInvoiced)
		.list();
	}
	
	
	static private int createForecastRunLine(MPPForecastDefinitionLine line , Timestamp from , Timestamp to)
	{
		StringBuffer where = new StringBuffer(); 
		List<Object> parameters = new ArrayList<Object>();
		
		//Product filters
		if (line.getM_Product_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_ID());
		}	
		
		if (line.getM_Product_Category_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Category_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Category_ID());
		}	
		
		if (line.getM_Product_Classification_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Classification_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Classification_ID());
		}	
		
		if (line.getM_Product_Class_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Class_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Class_ID());
		}	
		
		if (line.getM_Product_Group_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_M_Product_Group_ID);
			where.append(" AND ");
			parameters.add(line.getM_Product_Group_ID());
		}	
		
		//BPartner filter		
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_BPartner_ID);
			where.append(" AND ");
			parameters.add(line.getC_BPartner_ID());
		}	
		if (line.getC_BP_Group_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_BP_Group_ID);
			where.append(" AND ");
			parameters.add(line.getC_BP_Group_ID());
		}	
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_SalesRegion_ID);
			where.append(" AND ");
			parameters.add(line.getC_SalesRegion_ID());
		}			
		if (line.getC_BPartner_ID() > 0)
		{	
			where.append(MSalesHistory.COLUMNNAME_C_Campaign_ID);
			where.append(" AND ");
			parameters.add(line.getC_Campaign_ID());
		}	
		
		where.append(MSalesHistory.COLUMNNAME_DateInvoiced);
		where.append(" BETWEEN ? AND ?");
		parameters.add(from);
		parameters.add(to);
		return 0;
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8334884429848884655L;

	public MSalesHistory(Properties ctx, int C_SalesHistory_ID, String trxName) {
		super(ctx, C_SalesHistory_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSalesHistory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}

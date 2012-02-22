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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com http://www.e-evolution.com    *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MCostDetail;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;


/**
 *	Regenerate Cost Detail
 *	
 *	@author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class ValuationEffectiveDate extends SvrProcess
{
	
    /** Parameters **/
	private int p_M_Warehouse_ID;
	private int p_M_Product_ID;
	private int p_M_Product_Category_ID;
	private int p_M_CostType_ID;
	private int p_M_CostElement_ID;
	private Timestamp p_DateValue;
	
	private StringBuffer whereClause1 = new StringBuffer("WHERE 1=1 ");
	private StringBuffer whereClause2 = new StringBuffer("AND to_char(tc.DateAcct, 'yyyymmdd') || tc.M_Transaction_ID  IN (SELECT MAX(to_char(tc1.DateAcct, 'yyyymmdd') || tc1.M_Transaction_ID) FROM RV_M_Transaction_Costing tc1")
	.append(" INNER JOIN M_Locator l1 ON (tc1.M_Locator_ID=l1.M_Locator_ID) ")
	.append(" WHERE tc.M_Product_ID=tc1.M_Product_ID AND tc.M_AttributeSetInstance_ID=tc1.M_AttributeSetInstance_ID ");
	private ArrayList<Object> params1 = new ArrayList();  
	private ArrayList<Object> params2 = new ArrayList();  
	private ArrayList<Object> params = new ArrayList(); 
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
         ProcessInfoParameter[] parameters = getParameter();
         for(ProcessInfoParameter parameter: parameters)
         {
        	 String name = parameter.getParameterName();
 			if (parameter.getParameter() == null)
 				;
 			else if (name.equals("DateValue"))
 			{	
 				p_DateValue = (Timestamp) parameter.getParameter();
 				if(p_DateValue == null)
 		    		throw new FillMandatoryException("@DateValue@");
 				whereClause1.append("AND tc.DateAcct<= ").append(DB.TO_DATE(p_DateValue));
 				whereClause2.append("AND tc1.DateAcct<= ").append(DB.TO_DATE(p_DateValue));
 			}
 			else if (name.equals(MWarehouse.COLUMNNAME_M_Warehouse_ID))
 			{	
 				p_M_Warehouse_ID = parameter.getParameterAsInt();
 		    	if(p_M_Warehouse_ID > 0)
 		    	{
 		    		whereClause1.append(" AND l.M_Warehouse_ID=?");
 		    		params1.add(p_M_Warehouse_ID);
 		    		whereClause2.append(" AND tc1.M_Locator_ID=tc.M_Locator_ID");		    		
 		    	}
 			}
 			else if (name.equals(MCostDetail.COLUMNNAME_M_Product_ID))
 			{	
 				p_M_Product_ID = parameter.getParameterAsInt();
 		    	if(p_M_Product_ID > 0)
 		    	{
 		    		whereClause1.append(" AND p.M_Product_ID=? ");
 		    		params1.add(p_M_Product_ID);
 		    	}
 			}
 			else if (name.equals(MProduct.COLUMNNAME_M_Product_Category_ID))
 			{	
 				p_M_Product_Category_ID = parameter.getParameterAsInt();
 		    	if(p_M_Product_Category_ID > 0)
 		    	{
 		    		whereClause1.append(" AND p.M_Product_Category_ID =? ");
 		    		params1.add(p_M_Product_Category_ID);
 		    	}
 			}
 			else if (name.equals(MCostDetail.COLUMNNAME_M_CostType_ID))
 			{
 				p_M_CostType_ID =  parameter.getParameterAsInt();
 				if(p_M_CostType_ID > 0)
 		    	{ 					
 					whereClause1.append(" AND tc.M_CostType_ID =?  ");
 					params1.add(p_M_CostType_ID);
 		    		whereClause2.append(" AND tc1.M_CostType_ID=tc.M_CostType_ID ");
 		    		
 		    	}
 			}
 			else if (name.equals(MCostDetail.COLUMNNAME_M_CostElement_ID))
 			{	
 				p_M_CostElement_ID = parameter.getParameterAsInt();
 				if(p_M_CostElement_ID > 0)
 		    	{
 		    		whereClause1.append(" AND tc.M_CostElement_ID=? ");
 		    		params1.add(p_M_CostElement_ID);
 		    		whereClause2.append(" AND tc1.M_CostElement_ID = tc.M_CostElement_ID");
 		    	}
 			}
 				
         }
         whereClause2.append(")");
	}	//	prepare

	/**
	 * execute the Valuation Effective Date
	 */    
    protected String doIt() throws Exception                
	{
    	generateInventoryValue();
		return "@Ok@";
	
	}
    
    /**
     * Generate the Inventory Valuation
     */
    private void generateInventoryValue()
    {    	   	
    	StringBuffer insert = new StringBuffer();
    	insert
    	.append("INSERT INTO T_InventoryValue ")
    	.append("(AD_PInstance_ID,DateValue,AD_Client_ID,AD_Org_ID,M_CostElement_ID,M_CostType_ID,M_Warehouse_ID,")
    	.append("M_Product_ID,M_Product_Category_ID,M_AttributeSetInstance_ID,Classification,Group1,Group2,QtyOnHand,CostAmt,CostAmtLL) ")
    	.append("SELECT ")
    	.append(getAD_PInstance_ID()).append(",")
    	.append("tc.DateAcct").append(",")
    	.append("p.AD_Client_ID,p.AD_Org_ID,tc.M_CostElement_ID,tc.M_CostType_ID,l.M_Warehouse_ID,p.M_Product_ID,")
    	.append("p.M_Product_Category_ID,tc.M_AttributeSetInstance_ID,p.Classification,p.Group1,p.Group2,EndingQtyBalance AS QtyOnHand,")
    	.append("(tc.CostAmt + tc.CumulatedAmt) AS CostAmt,")
    	.append("(tc.CostAmtLL + tc.CumulatedAmtLL) AS CostAmtLL")
    	.append(" FROM M_Product p ")
    	.append(" INNER JOIN RV_M_Transaction_Costing tc ON (p.M_Product_ID=tc.M_Product_ID) ")
    	.append(" INNER JOIN M_Locator l ON (tc.M_Locator_ID=l.M_Locator_ID) ");

    	insert.append(whereClause1.append(whereClause2));
    	
    	for (Object o : params1)
    		params.add(o);
    	
    	for (Object o : params2)
    		params.add(o);
    	
    	DB.executeUpdateEx(insert.toString(), params.toArray(),get_TrxName());
    	DB.executeUpdate("UPDATE T_InventoryValue SET cost = CASE WHEN QtyOnHand <> 0 THEN (CostAmt + CostAmtLL) / QtyOnHand ELSE  0 END  , CumulatedAmt = CostAmt + CostAmtLL,  DateValue = "
    	+DB.TO_DATE(p_DateValue)+" WHERE AD_PInstance_ID=?", getAD_PInstance_ID(),get_TrxName());
    	
    }
}    
  

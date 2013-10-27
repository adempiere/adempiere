package org.compiere.process;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.logging.Level;

import javax.sql.RowSet;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class RollUpCosts extends SvrProcess {


	int category = 0;
	int product_id = 0;
	int client_id = 0; 
	int org_id = 0; 
	int user_id = 0;
	int costelement_id = 0;
	private HashSet<Integer> processed;
	
	protected void prepare() 
	{
	
		int chosen_id = 0;
				
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
		//	log.fine("prepare - " + para[i]);
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_Category_ID"))
				category = para[i].getParameterAsInt();
			else if (name.equals("M_Product_ID"))
				chosen_id = para[i].getParameterAsInt();
			else if (name.equals("M_CostElement_ID"))
				costelement_id = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		
	
		product_id = getRecord_ID();
		if (product_id == 0)
		{
			product_id = chosen_id;
		}

		
	}
	
	protected String doIt() throws Exception
	{
		client_id = Env.getAD_Client_ID(getCtx());
		org_id = Env.getAD_Org_ID(getCtx());
		user_id = Env.getAD_User_ID(getCtx());
		createView();
		String result = rollUp();
		deleteView();
		return result;
	}
	


	protected String rollUp() throws Exception {
		
		
		if (product_id != 0) //only for the product
		{
			rollUpCosts(product_id);
		}
		else if (category != 0) //roll up for all categories
		{
			String sql = "SELECT M_PRODUCT_ID FROM M_PRODUCT WHERE M_PRODUCT_CATEGORY_ID = " + 
			    category + " AND AD_CLIENT_ID = " + Env.getAD_Client_ID(getCtx()) + 
			    " AND M_PRODUCT_ID IN (SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM)";
			//System.err.println(sql);
		    RowSet results = DB.getRowSet(sql);
			while (results.next())
			{
				rollUpCosts(results.getInt(1));
			}
		}
		else //do it for all products 
		{
			String sql = "SELECT M_PRODUCT_ID FROM M_PRODUCT WHERE AD_CLIENT_ID = " + Env.getAD_Client_ID(getCtx()) + 
			   " AND M_PRODUCT_ID IN (SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM)";
		    //System.err.println(sql);
	        RowSet results = DB.getRowSet(sql);
		    while (results.next())
		    {
			    rollUpCosts(results.getInt(1));
		    }
	    }
		
		return "Roll Up Complete";
	}
    
	protected void createView() throws Exception
	{
		
		processed = new HashSet<Integer>();
		
	}
	
	protected void deleteView()
	{
	}
	
	protected void rollUpCosts(int p_id) throws Exception 
	{
		String sql = "SELECT M_ProductBOM_ID FROM M_Product_BOM WHERE M_Product_ID = " + 
		    p_id + " AND AD_Client_ID = " + Env.getAD_Client_ID(getCtx());
		//System.err.println(sql);
		RowSet results = DB.getRowSet(sql);
		
		while (results.next())
		{
			if ( !processed.contains(p_id)) {
				rollUpCosts(results.getInt(1));
			}
        }
		results.close();
			
		//once the subproducts costs are accurate, calculate the costs for this product
		String update = "UPDATE M_Cost set CurrentCostPrice = COALESCE((select Sum (b.BOMQty * c.currentcostprice)" + 
           " FROM M_Product_BOM b INNER JOIN M_Cost c ON (b.M_PRODUCTBOM_ID = c.M_Product_ID) " + 
           " WHERE b.M_Product_ID = " + p_id + " AND M_CostElement_ID = " + costelement_id + "),0)," + 
           " FutureCostPrice = COALESCE((select Sum (b.BOMQty * c.futurecostprice) FROM M_Product_BOM b " + 
           " INNER JOIN M_Cost c ON (b.M_PRODUCTBOM_ID = c.M_Product_ID) " + 
           " WHERE b.M_Product_ID = " + p_id + " AND M_CostElement_ID = " + costelement_id + "),0)" +
           " WHERE M_Product_ID = " + p_id + " AND AD_Client_ID = " + Env.getAD_Client_ID(getCtx()) +
           " AND M_CostElement_ID = " + costelement_id +
           " AND M_PRODUCT_ID IN (SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM)";;
        
		//System.err.println(sql);
		DB.executeUpdate(update, get_TrxName());

		processed.add(p_id);
		
	}
}

/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
* Created on Aug 19, 2005 by alok
*
*/

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductQuery;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.exceptions.OperationException;

public class StockInquiryManager 
{
    

			
	public static ArrayList<ProductBean> getAvailableTShirts(Properties ctx, ProductQuery query) throws  OperationException
	{
	    String sql =    " SELECT " 														+
	    				" s.AD_Org_ID," 												+
	    				" s.M_LOCATOR_ID," 												+
					    " s.M_Product_ID," 												+
					    " p.name,"														+
					    " p.m_attributesetinstance_id,"									+
					    " s.QtyOnHand," 												+
					    " s.QtyReserved," 												+
					    " s.QtyOrdered," 												+
					    " p.m_product_category_id,"										+
					    " p.description"												+
					    " FROM M_Storage s,m_product p,m_locator loc" 					+
					    " where s.M_PRODUCT_ID = p.M_PRODUCT_ID" 						+
					    " and s.m_locator_id = loc.m_locator_id" 						+
					    " and p.m_product_category_id=" + query.getProductCategoryId() 	+										
					    " and p.isWebstoreFeatured='Y'"									+
					    " AND s.AD_Client_ID = " + Env.getAD_Client_ID(ctx)				+
					    " @ATTRIBUTE_SEARCH_SQL@"										+
	    				" and s.ad_org_id=" + Env.getAD_Org_ID(ctx);
	    
	    String attributeSearchSQL = ProductManager.getAttributeSearchSQL(ctx, query);
	    
	    
		sql = sql.replaceAll("@ATTRIBUTE_SEARCH_SQL@",attributeSearchSQL);
		sql = StockManager.setBackOrderFilter(ctx, sql, query.getBackOrder());
	
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;
		
		ProductBean bean;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try
        {
		    rs = pstmt.executeQuery();
            while (rs.next())
            {
                bean = new ProductBean();
                bean.setOrgId(Integer.valueOf(rs.getInt(1)));
                bean.setLocatorId(Integer.valueOf(rs.getInt(2)));
                bean.setProductId(Integer.valueOf(rs.getInt(3)));
                bean.setProductName(rs.getString(4));
                bean.setAttributeSetInstanceId(Integer.valueOf(rs.getInt(5)));
                bean.setQtyOnHand(rs.getBigDecimal(6));
                bean.setQtyReserved(rs.getBigDecimal(7));
                bean.setQtyOrdered(rs.getBigDecimal(8));
                bean.setProductCategoryId(Integer.valueOf(rs.getInt(9)));
                bean.setDescription(rs.getString(10));
                productList.add(bean);
            }
            
            rs.close();
        } 
		catch (SQLException e)
        {
            throw new OperationException(e);
        }
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
			pstmt = null;
		}
	
		return productList;
	}
}

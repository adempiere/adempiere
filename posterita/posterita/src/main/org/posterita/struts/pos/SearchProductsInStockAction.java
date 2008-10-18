package org.posterita.struts.pos;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.ProductStatusBean;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.lib.UdiConstants;

public class SearchProductsInStockAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String productName = request.getParameter("productName");
		Integer orgId = (Integer)request.getSession().getAttribute(Constants.ORG_FROM_ID);
		Properties ctx = TmkJSPEnv.getCtx(request); 
		
		if (orgId == null)
		{
			orgId = Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM);
		}
		
		productName = productName.replaceAll("#", "&#");
				
		ArrayList<ProductStatusBean> productList =ProductManager.searchProductsFromStock(ctx, orgId, productName, null);
			
		ProductStatusBean bean = null;
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		if(productList == null || productList.size() == 0)
		{
			out.print("<div class='notfound'><label><b>Not Found - " + productName + "</b></label></div>");	
		}
		else
		{
			int size = productList.size();
				
			size = (size > productList.size())? productList.size() : size;
			
			if(size > 0)
			{
				out.print("<ul>");
			
				for(int i=0;i< size;i++)
				{
					bean = (ProductStatusBean)productList.get(i);
					
					out.print("<li productId='"+ bean.getProductId() + "' barcode='" + bean.getBarCode() +"'>");
					out.print(bean.getProductName());
					out.print("</li>");		
				}
				
				out.print("</ul>");		
			}
			
			out.print("<div><label><b>Displaying 1 to " + size + " of " + productList.size() + "</b></label></div>");
				
		}
		response.getOutputStream().print(sw.toString());
		response.getOutputStream().flush();
		response.getOutputStream().close();		
	
		return null;

	}

}

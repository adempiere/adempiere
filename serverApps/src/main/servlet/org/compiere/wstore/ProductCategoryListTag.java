/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.wstore;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.select;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.HtmlCode;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 *	Product Category List
 *	<code>
 *	<cws:productCategoryList/>
 *	</code>
 *	
 *  @author Jorg Janke
 *  @version $Id: ProductCategoryListTag.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class ProductCategoryListTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5125199548679756479L;
	/**	Logging						*/
	private static CLogger		log = CLogger.getCLogger(ProductCategoryListTag.class);
	
	
	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		String name = "M_Product_Category_ID";
		
		option[] options = getCategories (AD_Client_ID);
		select sel = new select (name, options);
		sel.setID("ID_" + name);

		log.fine("AD_Client_ID=" + AD_Client_ID + ", #=" + options.length);

		//	Assemble
		HtmlCode html = new HtmlCode();
		html.addElement(sel);
		
		JspWriter out = pageContext.getOut();
		html.output(out);
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	End Tag - NOP
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag

	/**
	 * 	Get Product Category Options.
	 * 	@param AD_Client_ID client
	 *	@return array of category options
	 */
	private option[] getCategories (int AD_Client_ID)
	{
		option[] options = (option[])s_categories.get(new Integer(AD_Client_ID));
		if (options != null)
			return options;
		
		String sql = "SELECT M_Product_Category_ID, Name "
			+ "FROM M_Product_Category "
			+ "WHERE AD_Client_ID=" + AD_Client_ID
			+ " AND IsActive='Y' AND IsSelfService='Y' "
			+ "ORDER BY Name";
		KeyNamePair[] pairs = DB.getKeyNamePairs(sql, true);
		options = new option[pairs.length];
		//
		for (int i = 0; i < pairs.length; i++)
		{
			if (i == 0)
			{
				options[i] = new option ("-1");
				options[i].addElement(" ");
			}
			else
			{
				options[i] = new option (pairs[i].getID());
				options[i].addElement(Util.maskHTML(pairs[i].getName()));
			}
		}
		//
		s_categories.put(new Integer(AD_Client_ID), options);
		return options;
	}	//	getCountries

	/** Client Category Cache		*/
	static CCache<Integer,option[]> s_categories
		= new CCache<Integer,option[]>("ProductCategory", 10, 60);

}	//	ProductCategoryListTag


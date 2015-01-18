/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.util.List;
import java.util.Properties;

import org.compiere.apps.form.TreeMaintenance;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.X_PP_Product_BOM;

public class TreeBOM {

	
	public static CLogger log = CLogger.getCLogger(TreeMaintenance.class);
	
	public Properties getCtx() {
		return Env.getCtx();
	}
	
	/**
	 * get Product Summary
	 * @param product Product
	 * @param isLeaf is Leaf
	 * @return String
	 */
	public String productSummary(MProduct product, boolean isLeaf) {
		MUOM uom = MUOM.get(getCtx(), product.getC_UOM_ID());
		String value = product.getValue();
		String name = product.get_Translation(MProduct.COLUMNNAME_Name);
		//
		StringBuffer sb = new StringBuffer(value);
		if (name != null && !value.equals(name))
			sb.append("_").append(product.getName());
		sb.append(" [").append(uom.get_Translation(MUOM.COLUMNNAME_UOMSymbol)).append("]");
		//
		return sb.toString();
	}
	
	/**
	 * get Product Summary
	 * @param bom Product BOM
	 * @return String 
	 */
	public String productSummary(MPPProductBOM bom) {
		String value = bom.getValue();
		String name = bom.get_Translation(MPPProductBOM.COLUMNNAME_Name);
		//
		StringBuffer sb = new StringBuffer(value);
		if (name != null && !name.equals(value))
			sb.append("_").append(name);
		//
		return sb.toString();
	}
	
}

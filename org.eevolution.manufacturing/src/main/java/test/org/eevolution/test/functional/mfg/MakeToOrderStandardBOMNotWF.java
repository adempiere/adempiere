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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com, www.e-evolution.com          *
 *****************************************************************************/
package org.eevolution.test.functional.mfg;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;

/**
 * Test Case Manufacturing Order Make-To-Kit 
 * Using Standard BOM and Manufacturing Workflow
 * @author Victor Perez, www.e-evolution.com
 */
public class MakeToOrderStandardBOMNotWF extends AbstractMakeToOrder
{
	
	public void test01() throws Exception
	{	
		Qty = new BigDecimal(10); 
		//Define Product
		product = MProduct.get(getCtx(), M_Product_ID);
		//Define Business Partner
		BPartner = new MBPartner(getCtx(), C_BPartner_ID, trxName);
		
		//Setting the BOM
		
		int PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(product);
		if(PP_Product_BOM_ID > 0)
			bom = new MPPProductBOM(getCtx(), PP_Product_BOM_ID , trxName);
		else 
			throw new AdempiereException("@NotFound@ @PP_ProductBOM_ID@");
		
		if(bom != null)
		{
			bom.setBOMType(MPPProductBOM.BOMTYPE_Make_To_Order);
			bom.setBOMUse(MPPProductBOM.BOMUSE_Manufacturing);
			bom.saveEx();
		}

		

		createOrder();
		MPPOrder expected = createPPOrder();
		
		
		I_PP_Order actual = MPPOrder.forC_OrderLine_ID(getCtx(), oline.get_ID(), oline.getM_Product_ID() , trxName);
		
		if(actual == null)
		{
			throw new AdempiereException("@NotFound@ @PP_Order_ID@ not was generate");
		}
		assertEquals("Confirming Manufacturing Order", expected , actual);
	}	

}

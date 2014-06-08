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
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;

import test.AdempiereTestCase;

/**
 * Test Case Manufacturing Order Make-To-Kit 
 * Using Standard BOM and Manufacturing Workflow
 * @author Victor Perez, www.e-evolution.com
 */
public class AbstractMakeToOrder extends AdempiereTestCase
{
	String trxName = getTrxName();
	int M_Product_ID = 145; //PatioSet
	int C_BPartner_ID = 120; //SeedFarm
	int AD_Org_ID = 50000; // Standard Order
	int AD_User_ID = 101; // GardenAdmin
	int M_Warehouse_ID = 50001; // Furniture
	int PP_Product_BOM_ID = 145;
	int  AD_Workflow_ID=50018 ; //AD_Workflow_ID=50018
	int  S_Resource_ID=50005; //Furniture Plant
	int C_DocType_ID = 132;
	Timestamp today = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()),"DD/MM/YYYY");
	Timestamp promisedDeta = TimeUtil.addDays(today, 10);
	MPPProductBOM bom = null;
	MProduct product = null;
	MBPartner BPartner = null;
	MWorkflow workflow = null;
	BigDecimal Qty = Env.ZERO;
	MOrderLine oline = null;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
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
		
		//Define Workflow as standardd
		workflow =  new MWorkflow(getCtx(), AD_Workflow_ID, trxName);
		workflow.setValue(product.getValue());
		workflow.saveEx();
		
		//int workflow_id =  MWorkflow.getWorkflowSearchKey(product);
		if( AD_Workflow_ID > 0)
			workflow = MWorkflow.get(getCtx(), AD_Workflow_ID);
		else
			throw new AdempiereException("@NotFound@ @AD_Workflow_ID@");
		

		createOrder();
		MPPOrder expected = createPPOrder();
		
		
		I_PP_Order actual = MPPOrder.forC_OrderLine_ID(getCtx(), oline.get_ID() , oline.getM_Product_ID() , trxName);
		
		if(actual == null)
		{
			throw new AdempiereException("@NotFound@ @PP_Order_ID@ not was generate");
		}
		assertEquals("Confirming Manufacturing Order", expected , actual);
	}	

	public MOrder createOrder()
	{
		MOrder order = new MOrder(getCtx(), 0 , trxName);
		order.setAD_Org_ID(AD_Org_ID);
		order.setDateOrdered(today);
		order.setDatePromised(promisedDeta);
		order.setIsSOTrx(true);
		order.setC_DocTypeTarget_ID(C_DocType_ID);
		order.setC_BPartner_ID(C_BPartner_ID);
		order.setAD_User_ID(AD_User_ID);
		order.setM_Warehouse_ID(M_Warehouse_ID);
		order.setDocStatus(MOrder.STATUS_InProgress);
		order.setDocAction(MOrder.DOCACTION_Complete);
		order.saveEx();
		
		oline = new MOrderLine(order);
		oline.setM_Product_ID(product.get_ID());
		oline.setQty(new BigDecimal(10));
		oline.saveEx();
		
		order.processIt(MOrder.DOCACTION_Complete);
		return order;
	}
	
	public MPPOrder createPPOrder()
	{
		MPPOrder expected = new MPPOrder(getCtx(), 0 , trxName);
		expected.setAD_Org_ID(AD_Org_ID);
		expected.setM_Product_ID(product.getM_Product_ID());
		expected.setDateOrdered(today);
		expected.setDatePromised(promisedDeta);
		expected.setDateFinish(promisedDeta);
		expected.setPP_Product_BOM_ID(PP_Product_BOM_ID);
		expected.setAD_Workflow_ID(AD_Workflow_ID);
		expected.setS_Resource_ID(S_Resource_ID);
		expected.setM_Warehouse_ID(M_Warehouse_ID);
		expected.setDocStatus(MPPOrder.DOCSTATUS_InProgress);
		expected.setQty(Qty);
		return expected;
	}
	
	public void assertEquals(String message, I_PP_Order expected, I_PP_Order actual) throws Exception
	{
		boolean equals = expected.getAD_Client_ID() == actual.getAD_Client_ID()
			&& expected.getAD_Org_ID() == actual.getAD_Org_ID()
			&& expected.getM_Warehouse_ID() == actual.getM_Warehouse_ID()
			&& expected.getM_Product_ID() == actual.getM_Product_ID()
			&& expected.getQtyOrdered().equals(actual.getQtyOrdered())
			&& expected.getDocStatus().equals(actual.getDocStatus())
			&& expected.getDatePromised().equals(actual.getDatePromised())
			&& expected.getDateOrdered().equals(actual.getDateOrdered());
		//
		StringBuffer sb = new StringBuffer(message)
							.append(": expected="+expected)
							.append(", actual="+actual);
		
		assertTrue(sb.toString(), equals);
	}
}

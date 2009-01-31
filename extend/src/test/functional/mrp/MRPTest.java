/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional.mrp;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;
import org.eevolution.model.I_PP_MRP;
import org.eevolution.model.I_PP_Product_Planning;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductPlanning;

import test.AdempiereTestCase;

/**
 * MRP Engine Test Case
 * @author Teo Sarca, www.arhipac.ro
 */
public class MRPTest extends AdempiereTestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void test01() throws Exception
	{
//		InputStream in = new FileInputStream("D:\\Kituri\\fm");
		InputStream in = getClass().getClassLoader().getResourceAsStream("test/functional/mrp/MRPTests.csv");
		CSVFactory factory = new CSVFactory();
		Collection<TestableMRP> tests = factory.read(in);
		//
		for (TestableMRP test : tests)
		{
			_testMRP(test);
			rollback();
		}
	}
	private void _testMRP(TestableMRP test) throws Exception
	{
		boolean ok = false;
		test.trxName = getTrxName();
		try
		{
			test.doIt();
//			test.dumpStatus();
			//
			assertEquals(test.name+": MRP Records# not match", test.expectedMRP.size(), test.actualMRP.size());
			for (int i = 0; i < test.expectedMRP.size(); i++)
			{
				assertEquals(test.name+": MRP Record not match",
						test.expectedMRP.get(i),
						test.actualMRP.get(i));
			}
			//
			assertEquals(test.name+": MRP Notices# not match", test.expectedNotices.size(), test.actualNotices.size());
			for (int i = 0; i < test.expectedNotices.size(); i++)
			{
				assertEquals(test.name+": MRP Record not match",
						test.expectedNotices.get(i).code,
						test.actualNotices.get(i).code);
			}
			//
			ok = true;
		}
		finally
		{
			if (!ok)
			{
				System.err.println("ERRROR_______________________________________");
				test.dumpStatus();
			}
		}
	}


	/**
	 * Helper Method : Create Product Planning
	 */
	public  static I_PP_Product_Planning getPlanning(String productValue,
			String Order_Policy,
			int Order_Min, int Order_Max, int Order_Pack, int SafetyStock,
			int Order_Period,
			int LeadTime)
	{
		boolean isPurchased = true;
		int PlanningHorizon = 365;
		//
		Properties ctx = Env.getCtx();
//		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int AD_Org_ID = MRPUtil.getFirst_Org_ID();
		MWarehouse wh = MRPUtil.getCreateWarehouse(AD_Org_ID, productValue);
		MResource plant = MRPUtil.getCreatePlant(productValue, wh.get_ID(), PlanningHorizon);
		MProduct product = getCreateProduct(ctx, productValue, isPurchased);
		//
		MPPProductPlanning pp = new MPPProductPlanning(ctx, 0, null);
		pp.setIsCreatePlan(true);
		pp.setIsRequiredMRP(true);
		pp.setIsRequiredDRP(false);
		pp.setM_Product_ID(product.get_ID());
		pp.setAD_Org_ID(AD_Org_ID);
		pp.setM_Warehouse_ID(wh.get_ID());
		pp.setS_Resource_ID(plant.get_ID());
		//
		pp.setOrder_Policy(Order_Policy);
		pp.setOrder_Min(BigDecimal.valueOf(Order_Min));
		pp.setOrder_Max(BigDecimal.valueOf(Order_Max));
		pp.setOrder_Pack(BigDecimal.valueOf(Order_Pack));
		pp.setSafetyStock(BigDecimal.valueOf(SafetyStock));
		pp.setOrder_Period(BigDecimal.valueOf(Order_Period));
		pp.setDeliveryTime_Promised(BigDecimal.valueOf(LeadTime));
		//
		return pp;
	}
	
	public static I_PP_MRP createMRP(I_PP_Product_Planning planning,
			String TypeMRP, String DocStatus, BigDecimal Qty, Timestamp DatePromised)
	{
		Properties ctx = Env.getCtx();
		//
		MPPMRP mrp = new MPPMRP(ctx, 0, null);
		mrp.setAD_Org_ID(planning.getAD_Org_ID());
		mrp.setName("MRP");
		mrp.setTypeMRP(TypeMRP);
		mrp.setDocStatus(DocStatus);
		mrp.setQty(Qty);
		mrp.setDatePromised(DatePromised);
		mrp.setDateStartSchedule(DatePromised);
		mrp.setDateFinishSchedule(DatePromised);
		mrp.setDateOrdered(DatePromised);
		mrp.setM_Product_ID(planning.getM_Product_ID());
		mrp.setM_Warehouse_ID(planning.getM_Warehouse_ID());
		return mrp;
	}
	
	/**
	 * Helper Method : Create Product
	 */
	public static MProduct getCreateProduct(Properties ctx, String value, boolean isPurchased)
	{
		MProduct[] arr = MProduct.get(ctx, "Value='"+value+"'", null);
		if (arr.length > 0)
		{
			return arr[0];
		}
		MProduct p = new MProduct(ctx, 0, null);
		p.setValue(value);
		p.setName(value);
		p.setAD_Org_ID(0);
		p.setProductType (MProduct.PRODUCTTYPE_Item);      // I
		p.setIsBOM (false);       // N
		p.setIsInvoicePrintDetails (false);
		p.setIsPickListPrintDetails (false);
		p.setIsPurchased (isPurchased);
		p.setIsSold (true);       // Y
		p.setIsStocked (true);    // Y
		p.setIsSummary (false);
		p.setIsVerified (false);  // N
		p.setIsWebStoreFeatured (false);
		p.setIsSelfService(true);
		p.setIsExcludeAutoDelivery(false);
		p.setProcessing (false);  // N
		p.setC_UOM_ID(100); // Each
		p.saveEx();
		return p;
	}
	
	public void assertEquals(String message, I_PP_MRP expected, I_PP_MRP actual) throws Exception
	{
		boolean equals = expected.getAD_Client_ID() == actual.getAD_Client_ID()
			&& expected.getAD_Org_ID() == actual.getAD_Org_ID()
			&& expected.getM_Warehouse_ID() == actual.getM_Warehouse_ID()
			&& expected.getM_Product_ID() == actual.getM_Product_ID()
			&& expected.getQty().equals(actual.getQty())
			&& expected.getTypeMRP().equals(actual.getTypeMRP())
			&& expected.getDocStatus().equals(actual.getDocStatus())
			&& expected.getDatePromised().equals(actual.getDatePromised())
			&& expected.getDateStartSchedule().equals(actual.getDateStartSchedule())
			&& expected.getDateFinishSchedule().equals(actual.getDateFinishSchedule())
			&& expected.getDateOrdered().equals(actual.getDateOrdered());
		//
		StringBuffer sb = new StringBuffer(message)
							.append(": expected="+expected)
							.append(", actual="+actual);
		
		assertTrue(sb.toString(), equals);
	}
}

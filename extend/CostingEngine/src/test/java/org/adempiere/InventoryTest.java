/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.adempiere;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostQueue;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;

import test.AdempiereTestCase;


/**
 * Run Inventory Tests
 * @author Teo Sarca
 */
public class InventoryTest extends AdempiereTestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		int currencyId = 0;
		// Read Currency_ID from property file. Use USD as Default value if $C_Currency_ID is not defined in property file!
		// 100 = USD; 346 = RON[Leu (new)]
		currencyId = Integer.parseInt( testProperties.getProperty("$C_Currency_ID", "100") );
		
		Env.setContext(Env.getCtx(), "$C_Currency_ID", currencyId);
		
//		MLocator locator = InventoryUtil.getCreateLocator(-1, "junit-wh1", "l1");
		Env.setContext(Env.getCtx(), "#M_Warehouse_ID", -1);

		Env.setContext(Env.getCtx(), "#PO_PriceList_ID",
				InventoryUtil.getCreatePriceList("junit-PO-" + currencyId, false).get_ID());
		Env.setContext(Env.getCtx(), "#SO_PriceList_ID",
				InventoryUtil.getCreatePriceList("junit-SO-" + currencyId, true).get_ID());
		
		//
		// Set default shipment document type for POO if not set
		DB.executeUpdateEx(
			"update C_DocType set C_DocTypeShipment_ID=122 where C_DocType_ID=126 and C_DocTypeShipment_ID is null",
			null);
	}
	
	public void test01() throws Exception
	{
		CLogMgt.setLevel(Level.CONFIG);
		
		InputStream in;
		if (System.getProperty("TestFile") != null)
		{
			in = new FileInputStream(System.getProperty("TestFile"));
		}
		else //if (System.getProperty("UseArhipacURL") != null)
		{
//			String url = "http://spreadsheets.google.com/pub?key=p_F3GDtQxWTA9YbH8MRkyKA&output=csv&gid=0"; 
	//		String url = "http://spreadsheets.google.com/a/tevsar.com/ccc?key=0AiPgrp_cUJUkdFhKbms1WTNraG96ODVweWc3Y05tdFE&hl=en";
			String url = "http://spreadsheets.google.com/pub?key=tXJnk5Y3khoz85pyg7cNmtQ&single=true&gid=0&output=csv";
	
			in = new URL(url).openStream();
		}
		//
		CSVFactory factory = new CSVFactory();
		Collection<MMScenario> tests = factory.read(in);
		String trxName = getTrxName();
		//
		for (MMScenario scenario : tests)
		{
			System.out.println("#### scenario.name = " + scenario.name);
			if ("junit-test-line_209".compareTo(scenario.name) == 0)
			runMMScenario(scenario, trxName);
//			break;
		}
	}
	
	private void runMMScenario(MMScenario scenario, String trxName)
{
		scenario.key = ""+System.currentTimeMillis();
		for (MMDocument doc : scenario.docs)
		{
			boolean ok = false;
			try
			{
				createDocument(doc, trxName);
				ok = true;
			}
			catch (Exception e)
			{
				InventoryTestException ie = (e instanceof InventoryTestException ? (InventoryTestException)e : new InventoryTestException(e));
				ie.scenario = scenario;
				ie.document = doc;
				throw ie;
			}
			finally
			{
				if (!ok)
				{
					dumpStatus(doc, trxName);
					try
					{
						commit(); 
					}
					catch (Exception e){}
				}
			}
		}
	}

	private void createDocument(final MMDocument doc, String trxName)
	{
		Trx.run(trxName, new TrxRunnable(){
			//@Override
			public void run(String trxName) {
				if (doc.IsReversal)
				{
					MMDocument docOrig = doc.scenario.get(doc.DocBaseType, doc.DocumentNo);
					doc.ProductValue = docOrig.ProductValue;
					doc.LocatorValue = docOrig.LocatorValue;
					doc.LocatorValueTo = docOrig.LocatorValueTo;
					InventoryUtil.processDocument(docOrig, DocAction.ACTION_Reverse_Correct, DocAction.STATUS_Reversed);
					return;
				}
				
				if (doc.ProductValue == null)
					throw new RuntimeException("ProductValue is null");
				doc.ProductValue = "junit-"+doc.ProductValue+"-"+doc.scenario.key;
				
				// Check document No conflict
				// commented by anca_bradau
	//			if (doc.scenario.get(doc.DocBaseType, doc.DocumentNo) != null)
	//			{
	//				throw new AdempiereException("DocumentNo conflict - "+doc.DocumentNo);
	//			}

				if (MDocType.DOCBASETYPE_PurchaseOrder.equals(doc.DocBaseType)
						|| MDocType.DOCBASETYPE_SalesOrder.equals(doc.DocBaseType) )
				{
					InventoryUtil.createOrder(doc, trxName);
				}
				else if (MDocType.DOCBASETYPE_MaterialReceipt.equals(doc.DocBaseType)
						|| MDocType.DOCBASETYPE_MaterialDelivery.equals(doc.DocBaseType) )
				{
					InventoryUtil.createInOut(doc, trxName);
				}
				else if (MDocType.DOCBASETYPE_MaterialMovement.equals(doc.DocBaseType))
				{
					InventoryUtil.createMovement(doc, trxName);
				}
				else if (MDocType.DOCBASETYPE_MaterialPhysicalInventory.equals(doc.DocBaseType))
				{
					InventoryUtil.createInventory(doc, trxName);
				}
				else if ("TST".equals(doc.DocBaseType))
				{
					assertStorage(doc, trxName);
				}
				else if ("TST_MTRX".equals(doc.DocBaseType))
				{
					assertMTransaction(doc, trxName);
				}
				else
				{
					throw new AdempiereException("DocBaseType not supported for "+doc);
				}
			}});
	}
	
	private void assertStorage(MMDocument doc, String trxName)
	{
		MLocator locator = InventoryUtil.getCreateLocator(-1, doc.LocatorValue, doc.LocatorValue);
		MProduct product = InventoryUtil.getCreateProduct(doc);
		int M_ASI_ID = -1;
		if (!Util.isEmpty(doc.ASI, true))
		{
			M_ASI_ID = doc.scenario.getM_ASI_ID(doc.ASI);
		}
		ArrayList<Object> params = new ArrayList<Object>();
		String sql = "SELECT"
			+" COALESCE(SUM(QtyOnHand),0)"
			+",COALESCE(SUM(QtyReserved),0)"
			+",COALESCE(SUM(QtyOrdered),0)"
			+" FROM M_Storage"
			+" WHERE M_Locator_ID=? AND M_Product_ID=?";
		params.add(locator.get_ID());
		params.add(product.get_ID());
		if (M_ASI_ID >= 0)
		{
			sql += " AND "+MStorage.COLUMNNAME_M_AttributeSetInstance_ID+"=?";
			params.add(M_ASI_ID);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal qtyOnHand = Env.ZERO;
		BigDecimal qtyOrdered = Env.ZERO;
		BigDecimal qtyReserved = Env.ZERO;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				qtyOnHand = rs.getBigDecimal(1);
				qtyReserved = rs.getBigDecimal(2);
				qtyOrdered = rs.getBigDecimal(3);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		//
		assertEquals("QtyOnHand not match "+doc, doc.Qty, qtyOnHand);
		assertEquals("QtyReserved not match "+doc, doc.QtyReserved, qtyReserved);
		assertEquals("QtyOrdered not match "+doc, doc.QtyOrdered, qtyOrdered);
	}
	
	private void assertMTransaction(MMDocument doc, String trxName)
	{
		final MLocator locator = InventoryUtil.getCreateLocator(-1, doc.LocatorValue, doc.LocatorValue);
		final MProduct product = InventoryUtil.getCreateProduct(doc);
		final int M_ASI_ID;
		if (Util.isEmpty(doc.ASI, true))
		{
			M_ASI_ID = -1;
		}
		else
		{
			M_ASI_ID = doc.scenario.getM_ASI_ID(doc.ASI);
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause = MTransaction.COLUMNNAME_M_Product_ID+"=?"
							+" AND "+MTransaction.COLUMNNAME_M_Locator_ID+"=?";
		params.add(product.get_ID());
		params.add(locator.get_ID());
		if (M_ASI_ID > 0)
		{
			whereClause+=" AND "+MTransaction.COLUMNNAME_M_AttributeSetInstance_ID+"=?";
			params.add(M_ASI_ID);
		}
		//
		MTransaction trx = new Query(getCtx(), MTransaction.Table_Name, whereClause, trxName)
		.setParameters(params)
		.setOrderBy(MTransaction.COLUMNNAME_M_Transaction_ID+" DESC") // fetch lasts first
		.first();
		//
		assertNotNull("No MTransaction found", trx);
		assertEquals("MTransaction.MovementQty not match", doc.Qty, trx.getMovementQty());
		if (doc.Date != null)
		{
			assertEquals("MTransaction.MovementDate not match", doc.Date, trx.getMovementDate());
		}
		//
		// TODO: check MCostDetail...
	}
	
	private void dumpStatus(MMDocument doc, String trxName)
	{
		MProduct product = InventoryUtil.getCreateProduct(doc); 
		MStorage[] storage = MStorage.getOfProduct(getCtx(), product.get_ID(), trxName);
		
		System.err.println(""+product.toString());
		System.err.println("STORAGE____________________________________________________");
		System.err.println("   "+doc);
		for (MStorage s : storage)
		{
			System.err.println(""+s);
		}
		//
		//
		System.err.println("COST QUEUE ________________________________________________");
		List<MCostQueue> queue = new Query(getCtx(), MCostQueue.Table_Name, "M_Product_ID=?", trxName)
			.setParameters(new Object[]{product.get_ID()})
			.setOrderBy(MCostQueue.COLUMNNAME_M_CostQueue_ID)
			.list();
		for (MCostQueue q : queue)
		{
			System.err.println(""+q);
		}
		//
		//
		System.err.println("COST Detail ________________________________________________");
		List<MCostDetail> details = new Query(getCtx(), MCostDetail.Table_Name, "M_Product_ID=?", trxName)
			.setParameters(new Object[]{product.get_ID()})
			.setOrderBy(MCostDetail.COLUMNNAME_M_CostDetail_ID)
			.list();
		for (MCostDetail cd : details)
		{
			System.err.println(""+cd.toString());
		}
		//
		//
		System.err.println(doc.scenario.toString());
		//	
		System.err.println("___________________________________________________________");
		System.err.flush();
		System.err.flush();
	}
}

class InventoryTestException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8354291934731598998L;
	public MMScenario scenario = null;
	public MMDocument document = null;
	
	public InventoryTestException(Exception e)
	{
		super(e);
	}
	
	@Override
	public String getMessage()
	{
		String retValue = "";

		retValue = super.getMessage()
		+ "\n document=" + this.document
		+ "\n scenario=" + this.scenario
		;

		return retValue;
	}
	
	
}
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
package test.functional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MTable;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Element;
import org.compiere.util.DB;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test {@link org.compiere.model.Query} class
 * @author Teo Sarca, www.arhipac.ro
 */
public class QueryTest extends AdempiereTestCase
{	
	public void testQuery_NoTable() throws Exception
	{
		assertExceptionThrown("", IllegalArgumentException.class, new Runnable(){
			public void run()
			{
				new Query(getCtx(), "NO_TABLE_DEFINED", null, getTrxName());
			}
		});
	}
	
	public void testList() throws Exception
	{
		List<MTable> list = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
								.setParameters(new Object[]{"C_Invoice", "M_InOut"})
								.setOrderBy("TableName")
								.list();
		assertEquals("Invalid list size", 2, list.size());
		assertEquals("Invalid object 1", list.get(0).getTableName(), "C_Invoice");
		assertEquals("Invalid object 2", list.get(1).getTableName(), "M_InOut");
	}

	public void testScroll() throws Exception
	{
		POResultSet<MTable> rs = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
									.setParameters(new Object[]{"C_Invoice", "M_InOut"})
									.setOrderBy("TableName")
									.scroll();
		try
		{
			int i = 0;
			while (rs.hasNext())
			{
				MTable t = rs.next();
				if (i == 0)
				{
					assertEquals("Invalid object "+i, "C_Invoice", t.getTableName());
				}
				else if (i == 1)
				{
					assertEquals("Invalid object "+i, "M_InOut", t.getTableName());
				}
				else
				{
					assertFalse("More objects retrived than expected", true);
				}
				i++;
			}
		}
		finally
		{
			DB.close(rs);
			rs = null;
		}
		
	}

	public void testIterate() throws Exception
	{
		Iterator<MTable> it = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
							.setParameters(new Object[]{"C_Invoice", "M_InOut"})
							.setOrderBy("TableName")
							.iterate();
		int i = 0;
		while(it.hasNext())
		{
			MTable t = it.next();
			if (i == 0)
			{
				assertEquals("Invalid object "+i, "C_Invoice", t.getTableName());
			}
			else if (i == 1)
			{
				assertEquals("Invalid object "+i, "M_InOut", t.getTableName());
			}
			else
			{
				assertFalse("More objects retrived than expected", true);
			}
			i++;
		}
		
	}

	public void testCount() throws Exception
	{
		int count = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
						.setParameters(new Object[]{"C_Invoice", "M_InOut"})
						.setOrderBy("TableName")
						.count();
		assertEquals("Invalid count", 2, count);
	}
	
	public void testCount_BadSQL() throws Exception
	{
		assertExceptionThrown(null, DBException.class, new Runnable(){
			public void run()
			{
				new Query(getCtx(), "AD_Table", "TableName IN (?,?) AND BAD_SQL", getTrxName())
				.setParameters(new Object[]{"C_Invoice", "M_InOut"})
				.setOrderBy("TableName")
				.count();
			}
		});
	}
	
	public void testCount_NoValues() throws Exception
	{
		int count = new Query(getCtx(), "AD_Table", "1=2", getTrxName()).count();
		assertEquals("Counter should be ZERO", 0, count);
	}

	public void testFirst() throws Exception
	{
		MTable t = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
						.setParameters(new Object[]{"C_Invoice", "M_InOut"})
						.setOrderBy("TableName")
						.first();
		assertEquals("Invalid object", "C_Invoice", t.getTableName());
	}
	
	public void testFirstId() throws Exception
	{
		int id = new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
		.setParameters(new Object[]{"C_Invoice", "M_InOut"})
		.setOrderBy("TableName")
		.firstId();
		int expectedId = 318; // C_Invoice
		assertEquals("Invalid ID", expectedId, id);
	}

	public void testFirstOnly() throws Exception
	{
		MTable t = new Query(getCtx(), "AD_Table", "AD_Table_ID=?", getTrxName())
						.setParameters(new Object[]{318})
						.firstOnly();
		assertEquals("Invalid table ID", 318, t.get_ID());
		//
		assertExceptionThrown(null, DBException.class, new Runnable(){
			public void run()
			{
				new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
						.setParameters(new Object[]{"C_Invoice", "M_InOut"})
						.setOrderBy("TableName")
						.firstOnly();
			}
		});
	}
	
	public void testFirstIdOnly() throws Exception
	{
		int expectedId = 318; // C_Invoice
		int id = new Query(getCtx(), "AD_Table", "AD_Table_ID=?", getTrxName())
		.setParameters(new Object[]{expectedId})
		.firstIdOnly();
		assertEquals("Invalid table ID", expectedId, id);
		//
		assertExceptionThrown(null, DBException.class, new Runnable(){
			public void run()
			{
				new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
				.setParameters(new Object[]{"C_Invoice", "M_InOut"})
				.setOrderBy("TableName")
				.firstIdOnly();
			}
		});
	}
	
	public void testSetClient_ID() throws Exception
	{
		int AD_Client_ID = Env.getAD_Client_ID(getCtx());
		String sql = "SELECT COUNT(*) FROM C_Invoice WHERE IsActive='Y' AND AD_Client_ID="+AD_Client_ID;
		int targetCount = DB.getSQLValue(null, sql);
		//
		int count = new Query(getCtx(), "C_Invoice", "1=1", getTrxName())
						.setOnlyActiveRecords(true)
						.setClient_ID()
						.count();
		assertEquals("Invoice # not match", targetCount, count);
	}
	
	public void testGet_IDs() throws Exception
	{
		final String whereClause = "AD_Element_ID IN (101, 102)";
		int[] ids = new Query(getCtx(), "AD_Element", whereClause, getTrxName())
						.setOrderBy("AD_Element_ID")
						.getIDs();
		assertNotNull(ids);
		assertEquals(2, ids.length);
		assertEquals(101, ids[0]);
		assertEquals(102, ids[1]);
	}
	
	public void testAggregate() throws Exception
	{
		final int AD_Client_ID = Env.getAD_Client_ID(getCtx());
		final String sqlFrom = "FROM C_InvoiceLine WHERE IsActive='Y' AND AD_Client_ID="+AD_Client_ID;
		final Query query = new Query(getCtx(), "C_InvoiceLine", null, getTrxName())
						.setOnlyActiveRecords(true)
						.setClient_ID();
		//
		// Test COUNT:
		assertEquals("COUNT not match",
				DB.getSQLValueBDEx(getTrxName(), "SELECT COUNT(*) "+sqlFrom),
				query.aggregate(null, Query.AGGREGATE_COUNT));
		//
		// Test SUM:
		assertEquals("SUM not match",
				DB.getSQLValueBDEx(getTrxName(), "SELECT SUM(LineNetAmt+TaxAmt) "+sqlFrom),
				query.aggregate("LineNetAmt+TaxAmt", Query.AGGREGATE_SUM));
		//
		// Test MIN:
		assertEquals("MIN not match",
				DB.getSQLValueBDEx(getTrxName(), "SELECT MIN(LineNetAmt) "+sqlFrom),
				query.aggregate("LineNetAmt", Query.AGGREGATE_MIN));
		//
		// Test MAX:
		assertEquals("MAX not match",
				DB.getSQLValueBDEx(getTrxName(), "SELECT MAX(LineNetAmt) "+sqlFrom),
				query.aggregate("LineNetAmt", Query.AGGREGATE_MAX));
		//
		// Test aggregate (String) - FR [ 2726447 ]
		assertEquals("MAX not match (String)",
				DB.getSQLValueStringEx(getTrxName(), "SELECT MAX(Description) "+sqlFrom),
				(String)query.aggregate("Description", Query.AGGREGATE_MAX, String.class));
		//
		// Test aggregate (Timestamp) - FR [ 2726447 ]
		assertEquals("MAX not match (Timestamp)",
				DB.getSQLValueTSEx(getTrxName(), "SELECT MAX(Updated) "+sqlFrom),
				(Timestamp)query.aggregate("Updated", Query.AGGREGATE_MAX, Timestamp.class));
		//
		// Test Exception : No Aggregate Function defined
		assertExceptionThrown("No Aggregate Function defined", DBException.class, new Runnable(){
			public void run()
			{
				query.aggregate("*", null);
			}});
		//
		// Test Exception : No Expression defined
		assertExceptionThrown("No Expression defined", DBException.class, new Runnable(){
			public void run()
			{
				query.aggregate(null, Query.AGGREGATE_SUM);
			}});
	}
	
	public void testOnlySelection() throws Exception
	{
		// Get one AD_PInstance_ID
		int AD_PInstance_ID = DB.getSQLValueEx(null, "SELECT MAX(AD_PInstance_ID) FROM AD_PInstance");
		assertTrue(AD_PInstance_ID > 0);

		// Create selection list
		List<Integer> elements = new ArrayList<Integer>();
		elements.add(102); // AD_Element_ID=102 => AD_Client_ID
		elements.add(104); // AD_Element_ID=104 => AD_Column_ID
		DB.executeUpdateEx("DELETE FROM T_Selection WHERE AD_PInstance_ID="+AD_PInstance_ID, getTrxName());
		DB.createT_Selection(AD_PInstance_ID, elements, getTrxName());
		
		String whereClause = "1=1"; // some dummy where clause
		int[] ids = new Query(getCtx(), X_AD_Element.Table_Name, whereClause, getTrxName())
		.setOnlySelection(AD_PInstance_ID)
		.setOrderBy(X_AD_Element.COLUMNNAME_AD_Element_ID)
		.getIDs();
		assertEquals("Resulting number of elements differ", elements.size(), ids.length);
		
		for (int i = 0; i < elements.size(); i++)
		{
			int expected = elements.get(i);
			assertEquals("Element "+i+" not equals", expected, ids[i]);
		}
	}
}

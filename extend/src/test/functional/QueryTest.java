/**
 * 
 */
package test.functional;

import java.util.Iterator;
import java.util.List;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MTable;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test {@link org.compiere.model.Query} class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class QueryTest extends AdempiereTestCase
{	
	public void testQuery_NoTable() throws Exception
	{
		assertExceptionThrowed("", IllegalArgumentException.class, new Runnable(){
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
		assertExceptionThrowed(null, DBException.class, new Runnable(){
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

	public void testFirstOnly() throws Exception
	{
		MTable t = new Query(getCtx(), "AD_Table", "AD_Table_ID=?", getTrxName())
						.setParameters(new Object[]{318})
						.firstOnly();
		assertEquals("Invalid table ID", 318, t.get_ID());
		//
		assertExceptionThrowed(null, DBException.class, new Runnable(){
			public void run()
			{
				new Query(getCtx(), "AD_Table", "TableName IN (?,?)", getTrxName())
						.setParameters(new Object[]{"C_Invoice", "M_InOut"})
						.setOrderBy("TableName")
						.firstOnly();
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

}

/**
 * 
 */
package test.functional;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

import test.AdempiereTestCase;

/**
 * Test {@link org.compiere.util.DB} class
 * @author Teo Sarca, www.arhipac.ro
 */
public class DBTest extends AdempiereTestCase
{
	public void test_getSQLValueEx() throws Exception
	{
		int result = DB.getSQLValueEx(null, "SELECT 10 FROM DUAL");
		assertEquals(10, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertEquals("No value should be returned", -1, result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}
	
	public void test_getSQLValue() throws Exception
	{
		int result = DB.getSQLValue(null, "SELECT 10 FROM DUAL");
		assertEquals(10, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertEquals("No value should be returned", -1, result);
		//
		result = DB.getSQLValue(null, "SELECT 10 FROM INEXISTENT_TABLE");
		assertEquals("Error should be signaled", -1, result);
	}
	
	public void test_getSQLValueBDEx() throws Exception
	{
		BigDecimal result = DB.getSQLValueBDEx(null, "SELECT 10 FROM DUAL");
		assertEquals(BigDecimal.TEN, result);
		//
		result = DB.getSQLValueBD(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueBDEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}
	
	public void test_getSQLValueBD() throws Exception
	{
		BigDecimal result = DB.getSQLValueBD(null, "SELECT 10 FROM DUAL");
		assertEquals(BigDecimal.TEN, result);
		//
		result = DB.getSQLValueBD(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueBD(null, "SELECT 10 FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}
	
	public void test_getSQLValueStringEx() throws Exception
	{
		String result = DB.getSQLValueStringEx(null, "SELECT 'string' FROM DUAL");
		assertEquals("string", result);
		//
		result = DB.getSQLValueStringEx(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueStringEx(null, "SELECT 'string' FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}
	
	public void test_getSQLValueString() throws Exception
	{
		String result = DB.getSQLValueString(null, "SELECT 'string' FROM DUAL");
		assertEquals("string", result);
		//
		result = DB.getSQLValueString(null, "SELECT 'string' FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueString(null, "SELECT 'string' FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}
	
	public void test_getSQLValueTSEx() throws Exception
	{
		final Timestamp target = TimeUtil.getDay(2008, 01, 01);
		//
		Timestamp result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM");
		assertEquals(target, result);
		//
		result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		DBException ex = null;
		try
		{
			result = DB.getSQLValueTSEx(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM INEXISTENT_TABLE");
		}
		catch (DBException e)
		{
			ex = e;
		}
		assertNotNull("No DBException Was Throwed", ex);
	}
	
	public void test_getSQLValueTS() throws Exception
	{
		final Timestamp target = TimeUtil.getDay(2008, 01, 01);
		//
		Timestamp result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM DUAL");
		assertEquals(target, result);
		//
		result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM WHERE 1=2");
		assertNull("No value should be returned", result);
		//
		result = DB.getSQLValueTS(null, "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM INEXISTENT_TABLE");
		assertNull("Error should be signaled", result);
	}
}

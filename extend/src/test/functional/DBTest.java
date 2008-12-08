/**
 * 
 */
package test.functional;

import java.math.BigDecimal;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;

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
			DB.getSQLValueEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
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
			DB.getSQLValueBDEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
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
}

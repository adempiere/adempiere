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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.TimeUtil;
import org.compiere.util.ValueNamePair;

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
	
	public void test_getValueNamePairs() throws Exception
	{
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
		final String sql = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=? ORDER BY Value";
		// Get (with optional item)
		ValueNamePair[] arr = DB.getValueNamePairs(sql, true, params);
		assertEquals("Invalid size", 6+1, arr.length);
		assertSame("First value should be EMPTY", ValueNamePair.EMPTY, arr[0]);
		assertEquals(arr[1].getValue(), "1");
		assertEquals(arr[2].getValue(), "2");
		assertEquals(arr[3].getValue(), "3");
		assertEquals(arr[4].getValue(), "4");
		assertEquals(arr[5].getValue(), "6");
		assertEquals(arr[6].getValue(), "7");
		// Get (NO optional item)
		arr = DB.getValueNamePairs(sql, false, params);
		assertEquals("Invalid size", 6, arr.length);
		assertEquals(arr[0].getValue(), "1");
		assertEquals(arr[1].getValue(), "2");
		assertEquals(arr[2].getValue(), "3");
		assertEquals(arr[3].getValue(), "4");
		assertEquals(arr[4].getValue(), "6");
		assertEquals(arr[5].getValue(), "7");
	}
	
	public void test_getKeyNamePairs() throws Exception
	{
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
		final String sql = "SELECT AD_Ref_List_ID, Value FROM AD_Ref_List WHERE AD_Reference_ID=? ORDER BY Value";
		// Get (with optional item)
		KeyNamePair[] arr = DB.getKeyNamePairs(sql, true, params);
		assertEquals("Invalid size", 6+1, arr.length);
		assertSame("First value should be EMPTY", KeyNamePair.EMPTY, arr[0]);
		assertEquals(arr[1].getName(), "1");
		assertEquals(arr[2].getName(), "2");
		assertEquals(arr[3].getName(), "3");
		assertEquals(arr[4].getName(), "4");
		assertEquals(arr[5].getName(), "6");
		assertEquals(arr[6].getName(), "7");
		// Get (NO optional item)
		arr = DB.getKeyNamePairs(sql, false, params);
		assertEquals("Invalid size", 6, arr.length);
		assertEquals(arr[0].getName(), "1");
		assertEquals(arr[1].getName(), "2");
		assertEquals(arr[2].getName(), "3");
		assertEquals(arr[3].getName(), "4");
		assertEquals(arr[4].getName(), "6");
		assertEquals(arr[5].getName(), "7");
	}

}

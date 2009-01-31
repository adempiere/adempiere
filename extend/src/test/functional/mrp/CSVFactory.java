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
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eevolution.model.I_PP_MRP;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 * Read a CSV file and produce {@link TestableMRP} objects, ready to be run. 
 * @author Teo Sarca, www.arhipac.ro
 */
public class CSVFactory
{
	public static final DateFormat s_dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	private ICsvListReader reader; 
	
	public Collection<TestableMRP> read(InputStream in) throws Exception
	{
		ArrayList<TestableMRP> tests = new ArrayList<TestableMRP>();
		//
		reader = new CsvListReader(new InputStreamReader(in), CsvPreference.STANDARD_PREFERENCE);
		String[] header = reader.getCSVHeader(true);
		System.out.println("HEADER: "+MRPUtil.toString(header));
		//
		List<String> line;
		int last_lineNo = -1;
		boolean isPlanningLine = true;
		TestableMRP mrpTest = null;
		try
		{
			while ( (line = reader.read()) != null)
			{
				if (last_lineNo == -1 || last_lineNo + 1 < reader.getLineNumber())
				{
					isPlanningLine = true;
					if (mrpTest != null)
					{
						tests.add(mrpTest);
					}
					mrpTest = new TestableMRP();
				}
				if (isPlanningLine)
				{
					readProductPlanning(mrpTest, header, line);
					isPlanningLine = false;
				}
				else
				{
					readMRPLine(mrpTest, header, line);
				}
				//
				last_lineNo = reader.getLineNumber();
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error on line "+reader.getLineNumber()+": "+e.getLocalizedMessage(), e);
		}
		//
		return tests;
	}
	
	private void readProductPlanning(TestableMRP mrpTest, String[] header, List<String> line)
	{
		mrpTest.name = "junit-test-line_"+reader.getLineNumber();
		mrpTest.description = "Test starts in CSV at line "+reader.getLineNumber();
		mrpTest.qtyOnHand = getValue("QtyOnHand", BigDecimal.class, header, line);
		mrpTest.today = getValue("Today", Timestamp.class, header, line);
		//
		int LeadTime = getValue("LeadTime", Integer.class, header, line);
		String Order_Policy = getValue("Order_Policy", String.class, header, line);
		int Order_Min = getValue("Order_Min", Integer.class, header, line);
		int Order_Pack = getValue("Order_Min", Integer.class, header, line);
		int Order_Max = getValue("Order_Max", Integer.class, header, line);
		int SafetyStock = getValue("SafetyStock", Integer.class, header, line);
		int Order_Period = 0;
		mrpTest.planning = MRPTest.getPlanning(mrpTest.productValue,
				Order_Policy, Order_Min, Order_Max, Order_Pack, SafetyStock, Order_Period, LeadTime);
	}

	private void readMRPLine(TestableMRP mrpTest, String[] header, List<String> line)
	{
		boolean isGenerated = getValue("Generated", Boolean.class, header, line);
		Timestamp DatePromised = getValue("DatePromised", Timestamp.class, header, line);
		String TypeMRP = getValue("TypeMRP", String.class, header, line);
		String DocStatus = getValue("DocStatus", String.class, header, line);
		BigDecimal Qty = getValue("Qty", BigDecimal.class, header, line);
		String MRP_Notice = getValue("MRP_Notice", String.class, header, line);
		//
		if (MRP_Notice != null && MRP_Notice.trim().length() > 0)
		{
			mrpTest.expectedNotices.add(new MRPNotice(MRP_Notice));
		}
		//
		if (TypeMRP == null || TypeMRP.trim().length() == 0)
		{
			;
		}
		else
		{
			I_PP_MRP mrp = MRPTest.createMRP(mrpTest.planning, TypeMRP, DocStatus, Qty, DatePromised);
			mrp.setDescription("CSV Line "+reader.getLineNumber());
			if (isGenerated)
			{
				mrpTest.expectedMRP.add(mrp);
			}
			else
			{
				mrpTest.initialMRP.add(mrp);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(String name, Class<T> clazz, String[] headers, List<String> values)
	{
		String value = null;
		for (int i = 0; i < headers.length; i++)
		{
			if (name.equalsIgnoreCase(headers[i]))
			{
				if (values.size() > i)
				{
					value = values.get(i);
				}
				break;
			}
		}
		if (value != null && value.trim().length() == 0)
		{
			value = null;
		}
		//
		if (String.class == clazz)
		{
			return (T)value;
		}
		else if (BigDecimal.class == clazz)
		{
			if (value == null)
				return (T)BigDecimal.ZERO;
			else
				return (T)new BigDecimal(value);
		}
		else if (Integer.class == clazz)
		{
			if (value == null)
				return (T)Integer.valueOf(0);
			else
				return (T)((Integer)Integer.parseInt(value)); 
		}
		else if (Timestamp.class == clazz)
		{
			if (value == null)
				return null;
			else
			{
				try
				{
					Date date = s_dateFormat.parse(value);
					return (T)new Timestamp(date.getTime());
				}
				catch(ParseException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
		else if (Boolean.class == clazz)
		{
			if (value == null)
				return (T)Boolean.FALSE;
			else if ("Y".equals(value))
				return (T)Boolean.TRUE;
			else if ("N".equals(value))
				return (T)Boolean.FALSE;
			else
				throw new IllegalStateException("Invalid boolean value '"+value+"'"); 
		}
		else
		{
			throw new IllegalArgumentException("clazz not supported - "+clazz);
		}
	}
}

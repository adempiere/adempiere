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
package test.functional.inventory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
public class CSVFactory
{
	public static final DateFormat s_dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	private ICsvListReader reader; 
	
	public Collection<MMScenario> read(InputStream in) throws Exception
	{
		ArrayList<MMScenario> tests = new ArrayList<MMScenario>();
		//
		reader = new CsvListReader(new InputStreamReader(in), CsvPreference.STANDARD_PREFERENCE);
		String[] header = getCSVHeader();
		//
		List<String> line;
		int last_lineNo = -1;
		MMScenario scenario = null;
		try
		{
			while ( (line = reader.read()) != null)
			{
				if (last_lineNo == -1 || last_lineNo + 1 < reader.getLineNumber())
				{
					if (scenario != null)
					{
						tests.add(scenario);
					}
					scenario = new MMScenario("junit-test-line_"+(new DecimalFormat("000").format(reader.getLineNumber())));
				}
				readDocument(scenario, header, line);
				last_lineNo = reader.getLineNumber();
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error on line "+reader.getLineNumber()+": "+e.getLocalizedMessage(), e);
		}
		if (scenario != null)
		{
			tests.add(scenario);
		}
		//
		return tests;
	}
	
	private String[] getCSVHeader() throws IOException
	{
		String[] header = reader.getCSVHeader(true);
		for (int i = 0; i < header.length; i++)
		{
			header[i] = header[i].trim().replaceAll("\\s", "");
		}
		return header;
	}
	
	private void readDocument(MMScenario scenario, String[] header, List<String> line)
	{
		MMDocument doc = new MMDocument(scenario);
		doc.csvLineNo = reader.getLineNumber();
		doc.DocBaseType = getValue("DocType", String.class, header, line);
		doc.DocumentNo = getValue("DocumentNo", String.class, header, line);
		doc.LocatorValue = getValue("LocatorValue", String.class, header, line);
		doc.LocatorValueTo = getValue("LocatorValueTo", String.class, header, line);
		doc.ProductValue = getValue("ProductValue", String.class, header, line);
		doc.Price = getValue("Price", BigDecimal.class, header, line);
		doc.Qty = getValue("Qty", BigDecimal.class, header, line);
		doc.QtyOrdered = getValue("QtyOrdered", BigDecimal.class, header, line);
		doc.QtyReserved = getValue("QtyReserved", BigDecimal.class, header, line);
		doc.ASI = getValue("ASI", String.class, header, line);
		doc.Date = getValue("MovementDate", Timestamp.class, header, line);
		doc.PODocumentNo = getValue("PODocumentNo", String.class, header, line);
		doc.IsReversal = getValue("IsReversal", Boolean.class, header, line);
		scenario.docs.add(doc);
	}

	/**
	 * TODO: refactor
	 * @param <T>
	 * @param name
	 * @param clazz
	 * @param headers
	 * @param values
	 * @return
	 */
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

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2010 Teo Sarca, teo.sarca@gmail.com                          *
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
package org.adempiere.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

/**
 * Wrap a PO object to a given bean interface.
 * Example
 * <pre>
 * public interface I_C_Invoice_Customized
 * {
 *	public int getCustomValue1();
 *	public void setCustomValue1(int customValue1);
 *	public String getCustomString1();
 *	public void setCustomString1(String customString1);
 * }
 * ....
 * MInvoice invoice = ......;
 * I_C_Invoice_Customized invoiceCustomized = POWrapper.create(invoice, I_C_Invoice_Customized.class);
 * invoiceCustomized.setCustomValue1(12345);
 * invoiceCustomized.setCustomString1("my test string");
 * invoice.saveEx();
 * </pre>
 * @author Teo Sarca, teo.sarca@gmail.com
 */
public class POWrapper implements InvocationHandler
{
	@SuppressWarnings("unchecked")
	public static <T> T create(Object po, Class<T> cl)
	{
		if (!(po instanceof PO))
		{
			throw new AdempiereException("Not a PO object - "+po);
		}
		if (cl.isInstance(po))
		{
			return (T)po;
		}
		return (T)Proxy.newProxyInstance(cl.getClassLoader(), new Class<?>[]{cl}, new POWrapper((PO)po));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends PO> T getPO(Object model)
	{
		POWrapper wrapper = (POWrapper)Proxy.getInvocationHandler(model);
		return (T)wrapper.getPO();
	}

	private final CLogger log = CLogger.getCLogger(getClass());
	private final PO po;
	
	private POWrapper(PO po)
	{
		super();
		this.po = po;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		String methodName = method.getName();
		if (methodName.startsWith("set") && args.length == 1)
		{
			String propertyName = methodName.substring(3);
			po.set_ValueOfColumn(propertyName, args[0]);
			return null;
		}
		else if (methodName.startsWith("get") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(3);
			Object value = null;
			final int idx = po.get_ColumnIndex(propertyName);
			if (idx >= 0)
				value = po.get_Value(propertyName);
			if (value != null)
			{
				return value;
			}
			//
			if (method.getReturnType() == int.class)
			{
				value = Integer.valueOf(0);
			}
			else if (method.getReturnType() == BigDecimal.class)
			{
				value = BigDecimal.ZERO;
			}
			else if (isModelInterface(method.getReturnType()))
			{
				value = getReferencedObject(propertyName, method);
			}
			else if (PO.class.isAssignableFrom(method.getReturnType()))
			{
				throw new IllegalArgumentException("Method not supported - "+methodName);
			}
			return value;
		}
		else if (methodName.startsWith("is") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(2);
			int ii = po.get_ColumnIndex(propertyName);
			if (ii >= 0)
			{
				return po.get_Value(ii);
			}
			ii = po.get_ColumnIndex("Is"+propertyName);
			if (ii >= 0)
			{
				return po.get_Value(ii);
			}
			//
			throw new IllegalArgumentException("Method not supported - "+methodName);
		}
		else
		{
			return method.invoke(po, args);
		}
	}
	
	public PO getPO()
	{
		return po;
	}
	
	/**
	 * Load object that is referenced by given property.
	 * Example: getReferencedObject("M_Product", method) should load the M_Product record
	 * with ID given by M_Product_ID property name;
	 * @param propertyName
	 * @param method
	 * @return
	 */
	private final Object getReferencedObject(String propertyName, Method method)
	{
		int i = po.get_ColumnIndex(propertyName+"_ID");
		if (i < 0)
			return null;
		
		// Fetch Record_ID
		final Integer record_id = po.get_ValueAsInt(i);
		if (record_id == null || record_id <= 0)
			return null;
			
		
		// Fetch TableName from returning class
		Class<?> cl = method.getReturnType();
		String tableName;
		try
		{
			tableName = (String)cl.getField("Table_Name").get(null);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return null;
		}
		
		// Load Persistent Object
		PO child = MTable.get(po.getCtx(), tableName).getPO(record_id, po.get_TrxName());
		return child;
	}
	
	private boolean isModelInterface(Class<?> cl)
	{
		try
		{
			String tableName = (String)cl.getField("Table_Name").get(null);
			return tableName != null;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}

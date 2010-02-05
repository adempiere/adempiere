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
package org.adempiere.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Wrap GridTab to ADempiere Bean Interface (i.e. generated interfaces).
 * Usage example:
 * <pre>
 * I_A_Asset_Disposed bean = GridTabWrapper.create(mTab, I_A_Asset_Disposed.class); 
 * Timestamp dateDoc = (Timestamp)value; 
 * bean.setDateAcct(dateDoc); 
 * bean.setA_Disposed_Date(dateDoc); 
 * </pre>
 * @author Teo Sarca, www.arhipac.ro
 */
public class GridTabWrapper implements InvocationHandler
{
	private CLogger log = CLogger.getCLogger(getClass());
	
	@SuppressWarnings("unchecked")
	public static <T> T create(GridTab gridTab, Class<T> cl)
	{
		return (T)Proxy.newProxyInstance(cl.getClassLoader(),
											new Class<?>[]{cl},
											new GridTabWrapper(gridTab));
	}
	
	private final GridTab m_gridTab;
	
	private GridTabWrapper(GridTab gridTab)
	{
		this.m_gridTab = gridTab;
	}
	
//	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
	throws Throwable
	{
		String methodName = method.getName();
		if (methodName.startsWith("set") && args.length == 1)
		{
			String propertyName = methodName.substring(3);
			m_gridTab.setValue(propertyName, args[0]);
			// TODO: handle GridTab.setValue returning error  
			return null;
		}
		else if (methodName.startsWith("get") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(3);
			Object value = m_gridTab.getValue(propertyName);
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
			GridField field = m_gridTab.getField(propertyName);
			if (field != null)
			{
				final Object value = field.getValue();
				return value instanceof Boolean ? value : "Y".equals(value);
			}
			//
			field = m_gridTab.getField("Is"+propertyName);
			if (field != null)
			{
				final Object value = field.getValue();
				return value instanceof Boolean ? value : "Y".equals(value);
			}
			//
			throw new IllegalArgumentException("Method not supported - "+methodName);
		}
		else
		{
			return method.invoke(m_gridTab, args);
		}
	}
	
	public GridTab getGridTab()
	{
		return this.m_gridTab;
	}
	
	private final Properties getCtx()
	{
		return Env.getCtx();
	}
	
	private final String getTrxName()
	{
		return null;
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
		final GridField idField = m_gridTab.getField(propertyName+"_ID");
		if (idField == null)
			return null;
		
		// Fetch Record_ID
		final Integer record_id = (Integer)m_gridTab.getValue(idField);
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
		PO po = MTable.get(getCtx(), tableName).getPO(record_id, getTrxName());
		return po;
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

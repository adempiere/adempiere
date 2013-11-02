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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Wrap GridTab to ADempiere Bean Interface (i.e. generated interfaces). Usage example:
 * 
 * <pre>
 * I_A_Asset_Disposed bean = GridTabWrapper.create(mTab, I_A_Asset_Disposed.class);
 * Timestamp dateDoc = (Timestamp)value;
 * bean.setDateAcct(dateDoc);
 * bean.setA_Disposed_Date(dateDoc);
 * </pre>
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
public class GridTabWrapper implements InvocationHandler
{
	private static final CLogger log = CLogger.getCLogger(GridTabWrapper.class);

	public static <T> T create(Object model, Class<T> cl)
	{
		if (model == null)
			return null;
		GridTab gridTab = null;
		if (model instanceof GridTab)
			gridTab = (GridTab)model;
		if (gridTab == null)
			gridTab = getGridTab(model);
		if (gridTab == null)
		{
			throw new AdempiereException("Cannot wrap " + model + " (class:" + model.getClass());
		}

		//
		// Check if given interface is compatible with gridTab, i.e.
		// * interface does not define a Table_Name field
		// * interface has a Table_Name static field, which is equal with gridTab's Table_Name
		final String interfaceTableName = InterfaceWrapperHelper.getTableNameOrNull(cl);
		if (interfaceTableName != null && !interfaceTableName.equals(gridTab.getTableName()))
		{
			throw new AdempiereException("Interface " + cl + " (tableName=" + interfaceTableName + ") is not compatible with " + gridTab + " (tableName=" + gridTab.getTableName() + ")");
		}

		@SuppressWarnings("unchecked")
		final T result = (T)Proxy.newProxyInstance(cl.getClassLoader(),
				new Class<?>[] { cl },
				new GridTabWrapper(gridTab));
		return result;
	}

	/**
	 * 
	 * @param model
	 * @return GridTab or null if model is not a {@link GridTab} or is not wrapping a {@link GridTab}
	 */
	public static GridTab getGridTab(Object model)
	{
		if (model == null)
			return null;
		if (model instanceof GridTab)
			return (GridTab)model;
		if (Proxy.isProxyClass(model.getClass()))
		{
			InvocationHandler ih = Proxy.getInvocationHandler(model);
			if (ih instanceof GridTabWrapper)
			{
				GridTabWrapper wrapper = (GridTabWrapper)ih;
				return wrapper.getGridTab();
			}
		}

		return null;
	}
	
	public static void refresh(Object model)
	{
		GridTab gridTab = getGridTab(model);
		if (gridTab != null)
		{
			gridTab.dataRefresh();
		}
		else
		{
			log.fine("Wrapped object is not a GridTab [SKIP]");
		}
	}

	private final GridTab m_gridTab;
	
	private boolean failOnColumnNotFound = false;

	private GridTabWrapper(GridTab gridTab)
	{
		this.m_gridTab = gridTab;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		String methodName = method.getName();
		if (methodName.startsWith("set") && args.length == 1)
		{
			final Class<?> paramType = method.getParameterTypes()[0];

			final String propertyName;
			final Object value;
			if (isModelInterface(paramType))
			{
				// Model setter - me00_03374
				propertyName = methodName.substring(3) + "_ID";
				value = InterfaceWrapperHelper.getId(args[0]);
			}
			else
			{
				propertyName = methodName.substring(3);
				value = POWrapper.checkZeroIdValue(propertyName, args[0]);
			}
			
			final String errorMsg = m_gridTab.setValue(propertyName, value);

			if (!Util.isEmpty(errorMsg))
			{
				final String msg = "Attempt to set field " + propertyName
						+ " of grid tab " + m_gridTab
						+ " to value '" + value + " (original: " + args[0] + ")"
						+ "' returned an error message: " + errorMsg;
				if (failOnColumnNotFound)
				{
					throw new AdempiereException(msg);
				}
				else
				{
					log.severe(msg);
				}
			}
			return null;
		}
		else if (methodName.startsWith("get") && (args == null || args.length == 0)
				// metas: GridTab direct calls should be forwarded to GridTab directly
				&& !methodName.startsWith("get_"))
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
		else if (method.getName().equals("get_TableName"))
		{
			return m_gridTab.get_TableName();
		}
		else
		{
			// TODO: this is not working; we need to find the similar method in m_gridTab.getClass() class
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
	 * Load object that is referenced by given property. Example: getReferencedObject("M_Product", method) should load the M_Product record with ID given by M_Product_ID property name;
	 * 
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

	public static boolean isHandled(Object model)
	{
		return getGridTab(model) != null;
	}

	public static void save(Object model)
	{
		GridTab gridTab = getGridTab(model);

		// We save the GridTab because else, even if there is no change it will return false
		boolean manualCmd = false;
		boolean ok = gridTab.dataSave(manualCmd);
		if (!ok)
			throw new AdempiereException("Error saving " + model); // TODO: fetch the actual error message
	}

	/**
	 * Check if given columnName's value is null
	 * 
	 * @param model
	 * @param columnName
	 * @return true if columnName's value is null
	 */
	public static boolean isNull(Object model, String columnName)
	{
		final GridTab gridTab = getGridTab(model);
		if (gridTab == null)
		{
			return true;
		}

		final Object value = gridTab.getValue(columnName);
		return value == null;
	}
}

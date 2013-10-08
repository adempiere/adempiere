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
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper.MissingTableNameException;
import org.adempiere.util.Check;
import org.compiere.model.GridTab;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Wrap a PO object to a given bean interface. Example
 * 
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
 * 
 * @author Teo Sarca, teo.sarca@gmail.com
 */
public class POWrapper implements InvocationHandler
{
	public static <T> T create(Object po, Class<T> cl)
	{
		return create(po, cl, false);
	}

	public static <T> T create(Object po, Class<T> cl, boolean useOldValues)
	{
		return create(po, cl, useOldValues, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Object obj, Class<T> cl, boolean useOldValues, String trlAdLanguage)
	{
		if (obj == null)
		{
			return null;
		}
		if (cl.isInstance(obj) && !useOldValues && trlAdLanguage == null)
		{
			return (T)obj;
		}
		final PO po;
		if (obj instanceof PO)
		{
			po = (PO)obj;
		}
		else
		{
			po = getPO(obj);
		}
		if (!(po instanceof PO))
		{
			throw new AdempiereException("Not a PO object - " + obj);
		}

		//
		// Check TableName
		final String classTableName = InterfaceWrapperHelper.getTableNameOrNull(cl);
		if (classTableName != null)
		{
			final String poTableName = po.get_TableName();
			if (!poTableName.equals(classTableName))
			{
				throw new IllegalArgumentException("PO " + po + " (TableName:" + poTableName + ") and class " + cl + " (TableName:" + classTableName + ") are not compatible");
		}
	}
	
		return (T)Proxy.newProxyInstance(cl.getClassLoader(),
				new Class<?>[] { cl },
				new POWrapper(cl, po, useOldValues, trlAdLanguage));
	}

	/**
	 * Create a new instance of given interface
	 * 
	 * @param <T> model interface
	 * @param ctx context
	 * @param id record id
	 * @param cl model interface class
	 * @param trxName db transaction name
	 * @return new instance or null if not found
	 */
	public static <T> T create(Properties ctx, int id, Class<T> cl, String trxName)
	{
		if (id < 0)
			return null;

		String tableName = getTableName(cl);
		return create(ctx, tableName, id, cl, trxName);
	}

	/**
	 * Create a new instance of given interface
	 * 
	 * @param <T> model interface
	 * @param ctx context
	 * @param tableName table name to be queried
	 * @param id record id
	 * @param cl model interface class
	 * @param trxName db transaction name
	 * @return new instance or null if not found
	 */
	public static <T> T create(Properties ctx, final String tableName, int id, Class<T> cl, String trxName)
	{
		if (id < 0)
			return null;

		Check.assumeNotNull(tableName, "tableName not null");
		PO po = MTable.get(ctx, tableName).getPO(id, trxName);

		if (po == null || po.get_ID() != id)
		{
			// throw new AdempiereException("@PONotFound@ @" + tableName + "@ (ID=" + record_id + ")");
			return null;
		}
		return create(po, cl);
	}

	public static <T> T create(Properties ctx, Class<T> cl, String trxName)
	{
		String tableName = getTableName(cl);
		PO po = MTable.get(ctx, tableName).getPO(0, trxName);
		if (po == null)
		{
			// throw new AdempiereException("@PONotFound@ @" + tableName + "@ (ID=" + record_id + ")");
			return null;
		}
		return create(po, cl);
	}

	public static <T> T translate(T model, Class<T> cl)
	{
		return translate(model, cl, null); // trlAdLanguage=null(autodetect from context)
	}

	public static <T> T translate(T model, Class<T> cl, String trlAdLanguage)
	{
		final PO po = getPO(model);

		if (trlAdLanguage == null)
		{
			final Properties ctx = getCtx(model);
			trlAdLanguage = Env.getAD_Language(ctx);
		}
		if (Env.isBaseLanguage(trlAdLanguage, po.get_TableName()))
		{
			// no need to translate because context language is same as base language
			trlAdLanguage = null;
		}

		final boolean useOldValues = false;
		return create(po, cl, useOldValues, trlAdLanguage);
	}

	/**
	 * Method calls {@link #getPO(Object, boolean)} with <code>checkOtherWrapper=true</code>.
	 * 
	 * @param <T>
	 * @param model
	 * @return underlying {@link PO} or null
	 */
	public static <T extends PO> T getPO(Object model)
	{
		final boolean checkOtherWrapper = true;
		@SuppressWarnings("unchecked")
		final T po = (T)getPO(model, checkOtherWrapper);

		return po;
	}

	/**
	 * 
	 * @param model
	 * @param checkOtherWrapper
	 *            if the given <code>model</code> is handled by a {@link GridTabWrapper} and this param is
	 *            <code>true</code>, then this method <b>loads a new PO from DB</b>, only using the given
	 *            <code>model</code>'s table name and record ID. If this param is <code>false</code> and
	 *            <code>model</code> is not handled by <code>POWrapper</code>, then this method returns
	 *            <code>null</code>.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends PO> T getPO(Object model, boolean checkOtherWrapper)
	{
		if (model == null)
			return null;

		if (model instanceof PO)
			return (T)model;

		if (Proxy.isProxyClass(model.getClass()))
		{
			InvocationHandler ih = Proxy.getInvocationHandler(model);
			if (ih instanceof POWrapper)
			{
				POWrapper wrapper = (POWrapper)ih;
		return (T)wrapper.getPO();
	}
			if (ih instanceof GridTabWrapper && checkOtherWrapper)
			{
				// using the grid tab wrapper to load the PO
				final GridTab gridTab = GridTabWrapper.getGridTab(model);
				final String tableName = gridTab.get_TableName();
				final int recordID = gridTab.getKeyID(gridTab.getCurrentRow());

				return (T)MTable.get(Env.getCtx(), tableName).getPO(recordID, null);
			}
		}

		return null;
	}

	/**
	 * Get model context.
	 * 
	 * @param model
	 * @return
	 */
	public static Properties getCtx(Object model)
	{
		return getCtx(model, false);
	}

	/**
	 * Get context form model. If <code>useClientOrgFromModel</code> is true, then the returned context will contain the given <code>model</code>'s AD_Client_ID and AD_Org_ID in its
	 * <code>#AD_client_ID</code> and <code>#AD_Org_ID</code> properties.
	 * 
	 * @param model
	 * @param useClientOrgFromModel
	 * @return
	 */
	public static Properties getCtx(final Object model, final boolean useClientOrgFromModel)
	{
		final PO po = getPO(model);
		
		if (po != null)
		{
			final Properties poCtx;
			if (useClientOrgFromModel)
			{
				poCtx = new Properties(po.getCtx()); // won't change the original. 
				
				Env.setContext(poCtx, "#AD_Client_ID", po.getAD_Client_ID());
				Env.setContext(poCtx, "#AD_Org_ID", po.getAD_Org_ID());
			}
			else
			{
				poCtx = po.getCtx();
			}
			return poCtx;
		}
		return Env.getCtx();
	}

	public static String getTrxName(Object model)
	{
		PO po = getPO(model);
		if (po != null)
			return po.get_TrxName();
		return null;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws AdempiereException
	 * @see {@link InterfaceWrapperHelper#getTableName(Class)}
	 */
	public static String getTableName(Class<?> clazz) throws AdempiereException
	{
		return InterfaceWrapperHelper.getTableName(clazz);
	}

	private final CLogger log = CLogger.getCLogger(getClass());
	private final Class<?> interfaceClass;
	private final PO po;
	private final boolean useOldValues;
	private final String trlAdLanguage;
	
	private POWrapper(Class<?> interfaceClass, PO po, boolean useOldValues, String trlAdLanguage)
	{
		super();
		this.po = po;
		this.useOldValues = useOldValues;
		this.interfaceClass = interfaceClass;
		this.trlAdLanguage = trlAdLanguage;
	}

	protected Properties getCtx()
	{
		return po.getCtx();
	}

	protected String getTrxName()
	{
		return po.get_TrxName();
	}

	protected int getColumnIndex(String name)
	{
		return po.get_ColumnIndex(name);
	}

	protected Object getValue(String columnName, int index, Class<?> returnType)
	{
		if (trlAdLanguage != null && String.class.equals(returnType))
		{
			return po.get_Translation(columnName, trlAdLanguage);
		}

		final Object value;
		if (useOldValues)
		{
			value = po.get_ValueOld(index);
		}
		else
		{
			value = po.get_Value(index);
		}

		if (boolean.class.equals(returnType))
		{
			if (value == null)
				return false;
			else
				return value instanceof Boolean ? value : "Y".equals(value);
		}
		else
		{
			return value;
		}

	}

	protected int getValueAsInt(int index)
	{
		if (useOldValues)
			return po.get_ValueOldAsInt(index);
		else
			return po.get_ValueAsInt(index);
	}

	protected void setValue(String name, Object value)
	{
		if (useOldValues)
			throw new AdempiereException("Setting values in an old object is not allowed");
		else
		{
			value = checkZeroIdValue(name, value);
			po.set_ValueOfColumn(name, value);
		}
	}

	protected void setValueFromPO(String name, Class<?> clazz, Object value)
	{
		if (useOldValues)
			throw new AdempiereException("Setting values in an old object is not allowed");
		else
		{
			po.set_ValueFromPO(name, clazz, value);
		}
	}

	protected static final Object checkZeroIdValue(String columnName, Object value)
	{
		// TODO: check and refactor with see ModelClassGenerator.createColumnMethods

		if (!(value instanceof Integer))
			return value;

		if (!columnName.endsWith("_ID"))
			return value;

		final int id = (Integer)value;
		if (id > 0)
			return id;

		int firstOK = 1;
		// check special column
		if (columnName.equals("AD_Client_ID") || columnName.equals("AD_Org_ID")
				|| columnName.equals("Record_ID") || columnName.equals("C_DocType_ID")
				|| columnName.equals("Node_ID") || columnName.equals("AD_Role_ID")
				|| columnName.equals("M_AttributeSet_ID") || columnName.equals("M_AttributeSetInstance_ID"))
			firstOK = 0;

		if (id < firstOK)
			return null;

		return id;
	}

	protected Object invokeParent(Method method, Object[] args) throws Exception
	{
		return method.invoke(po, args);
	}

	private boolean invokeEquals(Object[] args)
	{
		PO po2 = POWrapper.getPO(args[0]);
		return po == po2 || po.equals(po2);
	}

	private Method findPOMethod(Method ifaceMethod)
	{
		try
		{
			final Class<?> poClass = po.getClass();
			final String name = ifaceMethod.getName();
			final Class<?>[] parameterTypes = ifaceMethod.getParameterTypes();
			return poClass.getMethod(name, parameterTypes);

		}
		catch (SecurityException e)
		{
			throw new AdempiereException(e);
		}
		catch (NoSuchMethodException e)
		{
			return null;
		}
	}

	public PO getPO()
	{
		return po;
	}

	public Class<?> getInterfaceClass()
	{
		return interfaceClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		String methodName = method.getName();
		if (methodName.startsWith("set") && args.length == 1)
		{
			final String propertyName = methodName.substring(3);
			final Class<?> paramType = method.getParameterTypes()[0];
			final Object value = args[0];
			if (isModelInterface(paramType))
			{
				setValueFromPO(propertyName + "_ID", paramType, value);
			}
			else
			{
				setValue(propertyName, value);
			}
			return null;
		}
		else if (methodName.startsWith("get") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(3);

			if (isModelInterface(method.getReturnType()))
			{
				return getReferencedObject(propertyName, method);
			}

			Object value = null;
			final int idx = getColumnIndex(propertyName);
			if (idx >= 0)
				value = getValue(propertyName, idx, method.getReturnType());
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
			else if (PO.class.isAssignableFrom(method.getReturnType()))
			{
				throw new IllegalArgumentException("Method not supported - "+methodName);
			}
			return value;
		}
		else if (methodName.startsWith("is") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(2);
			int ii = getColumnIndex(propertyName);
			if (ii >= 0)
			{
				return getValue(propertyName, ii, method.getReturnType());
			}

			propertyName = "Is" + propertyName;
			ii = getColumnIndex(propertyName);
			if (ii >= 0)
			{
				return getValue(propertyName, ii, method.getReturnType());
			}
			//
			throw new IllegalArgumentException("Method not supported - "+methodName);
		}
		else if (methodName.equals("equals") && args.length == 1)
		{
			return invokeEquals(args);
	}
		else
	{
			return invokeParent(method, args);
		}
	}
	
	/**
	 * Load object that is referenced by given property. Example: getReferencedObject("M_Product", method) should load the M_Product record with ID given by M_Product_ID property name;
	 * 
	 * @param propertyName
	 * @param method
	 * @return
	 */
	private final Object getReferencedObject(String propertyName, Method method) throws Exception
	{
		Method poMethod = findPOMethod(method);
		if (poMethod != null)
		{
			final Object retValue = poMethod.invoke(po, (Object[])null);
			if (retValue == null)
				return null;
			else if (method.getReturnType().isAssignableFrom(retValue.getClass()))
				return retValue;
			else
				return POWrapper.create(retValue, method.getReturnType());
		}

		int i = getColumnIndex(propertyName + "_ID");
		if (i < 0)
			return null;
		
		// Fetch Record_ID
		final Integer record_id = getValueAsInt(i);
		if (record_id == null || record_id <= 0)
			return null;
			
		
		// Fetch TableName from returning class
		Class<?> cl = method.getReturnType();
		String tableName;
		try
		{
			tableName = getTableName(cl);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return null;
		}
		
		// Load Persistent Object
		PO child = MTable.get(getCtx(), tableName).getPO(record_id, getTrxName());
		if (child == null || child.get_ID() != record_id)
			throw new AdempiereException("@PONotFound@ @" + tableName + "@ (ID=" + record_id + ")");
		return POWrapper.create(child, method.getReturnType());
	}
	
	private boolean isModelInterface(Class<?> cl)
	{
		try
		{
			String tableName = getTableName(cl);
			return tableName != null;
		}
		catch (MissingTableNameException e)
		{
			return false;
		}
	}

	public static void save(Object o)
	{
		if (o == null)
			throw new IllegalArgumentException("model is null");

		PO po = getPO(o);
		if (po != null)
		{
			po.saveEx();
		}
		else
		{
			throw new IllegalArgumentException("Class is not supported - " + o.getClass());
		}
	}

	public static void delete(Object o)
	{
		if (o == null)
			throw new IllegalArgumentException("model is null");

		PO po = getPO(o);
		if (po != null)
		{
			po.deleteEx(false);
		}
		else
		{
			throw new IllegalArgumentException("Class is not supported - " + o.getClass());
		}
	}

	public static boolean isHandled(Object model)
	{
		return getPO(model, false) != null; // checkOtherWrapper=false
	}

	/**
	 * Reload underlying PO object in same transaction as it was
	 * 
	 * @param model
	 * @see #refresh(Object, String)
	 * @throws IllegalArgumentException if model is null
	 * @throws IllegalArgumentException if there is no underlying PO object (i.e. getPO(model) return null)
	 */
	public static void refresh(Object model)
	{
		if (model == null)
			throw new IllegalArgumentException("model is null");
		final PO po = getPO(model);
		if (po == null)
			throw new IllegalArgumentException("Class is not supported - " + model.getClass());

		po.load(po.get_TrxName());
	}

	/**
	 * Reload underlying PO object
	 * 
	 * @param model
	 * @param trxName transaction to be used for reloading
	 * @throws IllegalArgumentException if model is null
	 * @throws IllegalArgumentException if there is no underlying PO object (i.e. getPO(model) return null)
	 */
	public static void refresh(final Object model, final String trxName)
	{
		if (model == null)
			throw new IllegalArgumentException("model is null");
		final PO po = getPO(model);
		if (po == null)
			throw new IllegalArgumentException("Class is not supported - " + model.getClass());

		po.load(trxName);
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
		final PO po = getPO(model, false);
		if (po == null)
		{
			return true;
		}

		final Object value = po.get_Value(columnName);
		return value == null;
	}

	public static void setDynAttribute(final Object model, final String attributeName, final Object value)
	{
		getPO(model, false).setDynAttribute(attributeName, value);
	}

	public static <T> T getDynAttribute(final Object model, final String attributeName)
	{
		@SuppressWarnings("unchecked")
		final T value = (T)getPO(model, false).getDynAttribute(attributeName);
		return value;
	}
}

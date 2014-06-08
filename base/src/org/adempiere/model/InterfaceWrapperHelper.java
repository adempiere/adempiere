package org.adempiere.model;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Check;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class InterfaceWrapperHelper
{
	private static final transient CLogger logger = CLogger.getCLogger(InterfaceWrapperHelper.class);

	/**
	 * Creates a new instance of given object using same context and trxName as <code>contextProvider</code>
	 * 
	 * @param cl
	 * @param contextProvider any object that carries a context (e.g. a PO, a wrapped PO, GridTab, a wrapped GridTab etc)
	 * @return new instance
	 */
	public static <T> T newInstance(Class<T> cl, Object contextProvider)
	{
		Check.assumeNotNull(contextProvider, "contextProvider not null");
		final Properties ctx = getCtx(contextProvider);
		final String trxName = getTrxName(contextProvider);
		return create(ctx, cl, trxName);
	}

	public static <T> T create(Object model, Class<T> cl)
	{
		if (model == null)
		{
			return null;
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return GridTabWrapper.create(model, cl);
		}
		else if (POWrapper.isHandled(model))
		{
			return POWrapper.create(model, cl);
		}
		else
		{
			throw new AdempiereException("Model wrapping is not supported for " + model + " (class:" + model.getClass() + ")");
		}
	}

	public static <T> T create(Properties ctx, Class<T> cl, String trxName)
	{
		final T bean = POWrapper.create(ctx, cl, trxName);
		return bean;
	}

	public static <T> T create(Properties ctx, int id, Class<T> cl, String trxName)
	{
		final T bean = POWrapper.create(ctx, id, cl, trxName);
		return bean;
	}

	public static <T> T create(final Properties ctx, final String tableName, final int id, final Class<T> cl, final String trxName)
	{
		final T bean = POWrapper.create(ctx, tableName, id, cl, trxName);
		return bean;
	}

	public static void refresh(Object model)
	{
		if (GridTabWrapper.isHandled(model))
			GridTabWrapper.refresh(model);
		else if (POWrapper.isHandled(model))
			POWrapper.refresh(model);
		else
			throw new AdempiereException("Not supported model " + model + " (class:" + model.getClass() + ")");
	}

	public static void refresh(final Object model, final String trxName)
	{
		if (GridTabWrapper.isHandled(model))
		{
			GridTabWrapper.refresh(model);
		}
		else if (POWrapper.isHandled(model))
		{
			POWrapper.refresh(model, trxName);
		}
		else
		{
			throw new AdempiereException("Not supported model " + model + " (class:" + model.getClass() + ")");
		}
	}

	public static void save(Object model)
	{
		if (model == null)
		{
			logger.log(Level.WARNING, "Saving null model ignored. Possible development issue. Ignored.", new AdempiereException());
		}
		else if (GridTabWrapper.isHandled(model))
		{
			GridTabWrapper.save(model);
		}
		else if (POWrapper.isHandled(model))
		{
			POWrapper.save(model);
		}
		else
		{
			logger.log(Level.SEVERE, "PO not handled: " + model + "(class=" + model.getClass() + "). Ignored.", new AdempiereException());
		}
	}

	/**
	 * Get context from model.
	 * 
	 * @param model
	 * @return
	 */
	public static Properties getCtx(Object model)
	{
		return getCtx(model, false);
	}

	/**
	 * Get context from model and setting in context AD_Client_ID and AD_Org_ID according to the model if
	 * useClientOrgFromModel is true
	 * 
	 * @param model
	 * @param useClientOrgFromModel
	 * @return
	 */
	public static Properties getCtx(Object model, boolean useClientOrgFromModel)
	{

		if (model == null)
		{
			return Env.getCtx();
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return Env.getCtx();
		}
		else if (POWrapper.isHandled(model))
		{
			return POWrapper.getCtx(model, useClientOrgFromModel);
		}
		else
		{
			logger.log(Level.WARNING, "Cannot get context from object: " + model + ". Returning global context.", new AdempiereException());
			return Env.getCtx();
		}
	}

	public static String getTrxName(Object model)
	{
		if (model == null)
		{
			return null;
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return null;
		}
		else if (POWrapper.isHandled(model))
		{
			return POWrapper.getTrxName(model);
		}
		else
		{
			logger.log(Level.WARNING, "Cannot get trxName from object: " + model + ". Returning null.", new AdempiereException());
			return null;
		}
	}

	public static void delete(Object model)
	{
		Check.assume(model != null, "model is null");

		if (POWrapper.isHandled(model))
			POWrapper.delete(model);
		// else if (GridTabWrapper.isHandled(model))
		// GridTabWrapper.
		else
			throw new IllegalStateException("delete is not supported for " + model);
	}

	/**
	 * 
	 * @param model
	 * @return underlying {@link PO} or null
	 */
	public static PO getPO(Object model)
	{
		return POWrapper.getPO(model);
	}

	public static int getId(Object model)
	{
		if (model == null)
		{
			return -1;
		}
		else if (POWrapper.isHandled(model))
		{
			final PO po = POWrapper.getPO(model, false);
			if (po == null)
				return -1;

			final String[] keyColumns = po.get_KeyColumns();
			if (keyColumns == null || keyColumns.length != 1)
				return -1;

			return po.get_ID();
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return GridTabWrapper.getGridTab(model).getRecord_ID();
		}
		else
		{
			throw new AdempiereException("Not supported model " + model + " (class:" + model.getClass() + ")");
		}
	}

	/**
	 * Introducing this exception to be thrown instead of ADempiereException. Reason: It's a pain if you have a breakpoint on "AdempiereException" and the debugger stops every 2 seconds because
	 * InterfaceWrapperHelper throws it.
	 */
	/* package */static class MissingTableNameException extends AdempiereException
	{
		private static final long serialVersionUID = 6469196469943285793L;

		private MissingTableNameException(Throwable cause)
		{
			super(cause);
		}
	}

	/**
	 * Checks static variable "Table_Name" of given interface and returns it's content.
	 * 
	 * @param clazz
	 * @return tableName associated with given interface
	 * @throws AdempiereException if "Table_Name" static variable is not defined or is not accessible
	 */
	public static String getTableName(Class<?> clazz) throws AdempiereException
	{
		try
		{
			final Field field = clazz.getField("Table_Name");
			if (!field.isAccessible())
			{
				field.setAccessible(true);
			}
			return (String)field.get(null);
		}
		catch (Exception e)
		{
			throw new MissingTableNameException(e);
		}
	}

	/**
	 * Checks static variable "Table_Name" of given interface and returns it's content.
	 * 
	 * @param clazz
	 * @return tableName associated with given interface or null if interface has no Table_Name
	 */
	public static String getTableNameOrNull(Class<?> clazz)
	{
		try
		{
			final Field field = clazz.getField("Table_Name");
			if (!field.isAccessible())
			{
				field.setAccessible(true);
			}
			return (String)field.get(null);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * Get TableName of wrapped model. If model is null, an exception will be thrown
	 * 
	 * @param model
	 * @return table name
	 * @throws AdempiereException if model is null or model is not supported
	 */
	public static String getModelTableName(Object model)
	{
		if (model == null)
		{
			throw new AdempiereException("Cannot get TableName for a null model. Possible development issue.");
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return GridTabWrapper.getGridTab(model).getTableName();
		}
		else if (POWrapper.isHandled(model))
		{
			return POWrapper.getPO(model).get_TableName();
		}
		else
		{
			throw new AdempiereException("Cannot get TableName from model: " + model);
		}
	}

	/**
	 * Check if given columnName's value is null
	 * 
	 * @param model
	 * @param columnName
	 * @return true if columnName's value is null
	 */
	public static boolean isNull(final Object model, final String columnName)
	{
		if (model == null)
		{
			return true;
		}
		else if (GridTabWrapper.isHandled(model))
		{
			return GridTabWrapper.isNull(model, columnName);
		}
		else if (POWrapper.isHandled(model))
		{
			return POWrapper.isNull(model, columnName);
		}
		else
		{
			throw new AdempiereException("Model wrapping is not supported for " + model + " (class:" + model.getClass() + ")");
		}
	}

	public static <T> T getValueByColumnId(Object model, int adColumnId)
	{
		Check.assumeNotNull(model, "model is not null");
		Check.assume(adColumnId > 0, "adColumnId > 0");

		if (GridTabWrapper.isHandled(model))
		{
			final GridTab gridTab = GridTabWrapper.getGridTab(model);
			for (final GridField field : gridTab.getFields())
			{
				if (field.getAD_Column_ID() == adColumnId)
				{
					@SuppressWarnings("unchecked")
					final T value = (T)field.getValue();
					return value;
				}
			}

			throw new AdempiereException("No field with AD_Column_ID=" + adColumnId + " found in " + gridTab + " for " + model);
		}
		else if (POWrapper.isHandled(model))
		{
			PO po = POWrapper.getPO(model, false);
			@SuppressWarnings("unchecked")
			T value = (T)po.get_ValueOfColumn(adColumnId);
			return value;
		}
		// else if (POJOWrapper.isHandled(model))
		// {
		// final POJOWrapper wrapper = POJOWrapper.getWrapper(model);
		// // MColumn.getColumnName(ctx, AD_Column_ID)
		// }
		else
		{
			throw new AdempiereException("Model wrapping is not supported for " + model + " (class:" + model.getClass() + ")");
		}
	}
	
	public static <T> T getValueOrNull(final Object model, final String columnName)
	{
		final boolean throwExIfColumnNotFound = false;
		final T value = getValue(model, columnName, throwExIfColumnNotFound);
		return value;
	}

	public static <T> T getValue(final Object model, final String columnName)
	{
		final boolean throwExIfColumnNotFound = true;
		final T value = getValue(model, columnName, throwExIfColumnNotFound);
		return value;
	}

	private static <T> T getValue(final Object model, final String columnName, final boolean throwExIfColumnNotFound)
	{
		Check.assumeNotNull(model, "model is not null");
		Check.assumeNotNull(columnName, "columnName is not null");

		if (GridTabWrapper.isHandled(model))
		{
			final GridTab gridTab = GridTabWrapper.getGridTab(model);
			final GridField gridField = gridTab.getField(columnName);
			if (gridField == null)
			{
				if (throwExIfColumnNotFound)
				{
					throw new AdempiereException("No field with ColumnName=" + columnName + " found in " + gridTab + " for " + model);
				}
				else
				{
					return null;
				}
			}

			@SuppressWarnings("unchecked")
			final T value = (T)gridField.getValue();
			return value;
		}
		else if (POWrapper.isHandled(model))
		{
			PO po = POWrapper.getPO(model, false);
			final int idx = po.get_ColumnIndex(columnName);
			if (idx < 0)
			{
				if (throwExIfColumnNotFound)
				{
					throw new AdempiereException("No columnName " + columnName + " found for " + model);
				}
				else
				{
					return null;
				}
			}
			@SuppressWarnings("unchecked")
			T value = (T)po.get_Value(idx);
			return value;
		}
		else
		{
			throw new AdempiereException("Model wrapping is not supported for " + model + " (class:" + model.getClass() + ")");
		}
	}
	
}

package org.adempiere.document.service.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.ad.wrapper.POJOWrapper;
import org.adempiere.document.service.IDocActionBL;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.process.DocAction;
import org.compiere.util.Util.ArrayKey;

/**
 * Database decoupled implementation for {@link IDocActionBL}
 * 
 * @author tsa
 * 
 */
// @Ignore
public class PlainDocActionBL extends AbstractDocActionBL
{
	@Override
	public boolean isDocumentTable(final String tableName)
	{
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	protected DocAction getDocAction(Object document, boolean throwEx)
	{
		// throw new UnsupportedOperationException("Method not implemented");

		if (document == null)
		{
			if (throwEx)
			{
				throw new IllegalArgumentException("document is null");
			}
			return null;
		}

		if (document instanceof DocAction)
		{
			return (DocAction)document;
		}

		//
		// WARNING: this is just a partial implementation
		final POJOWrapper wrapper = POJOWrapper.getWrapper(document);
		final Class<?> interfaceClass = wrapper.getInterfaceClass();
		if (hasMethod(interfaceClass, String.class, "getDocStatus")
				&& hasMethod(interfaceClass, String.class, "getDocAction")
				&& hasMethod(interfaceClass, String.class, "getDocumentNo"))
		{
			return POJOWrapper.create(document, DocAction.class);
		}

		if (throwEx)
		{
			throw new IllegalArgumentException("Document '" + document + "' cannot be converted to " + DocAction.class);
		}
		return null;
	}

	private static final boolean hasMethod(Class<?> clazz, Class<?> returnType, String methodName, Class<?>... parameterTypes)
	{
		try
		{
			final Method method = clazz.getMethod(methodName, parameterTypes);
			if (method == null)
			{
				return false;
			}

			if (!returnType.equals(method.getReturnType()))
			{
				return false;
			}

			return true;
		}
		catch (SecurityException e)
		{
			throw new AdempiereException(e);
		}
		catch (NoSuchMethodException e)
		{
			return false;
		}
	}

	@Override
	public int getC_DocType_ID(final Properties ctx, final int AD_Table_ID, final int Record_ID)
	{
		final Object model = POJOLookupMap.get().lookup(AD_Table_ID, Record_ID);
		if (model == null)
		{
			return -1;
		}

		final POJOWrapper wrapper = POJOWrapper.getWrapper(model);
		final Map<String, Object> valuesMap = wrapper.getValuesMap();

		final Object docTypeId = valuesMap.get("C_DocType_ID");
		if (docTypeId instanceof Number)
		{
			return ((Number)docTypeId).intValue();
		}

		final Object docTypeTargetId = valuesMap.get("C_DocTypeTarget_ID");
		if (docTypeTargetId instanceof Number)
		{
			return ((Number)docTypeTargetId).intValue();
		}

		return -1;
	}

	@Override
	protected String retrieveString(Properties ctx, int adTableId, int recordId, String columnName)
	{
		final Object model = POJOLookupMap.get().lookup(adTableId, recordId);
		if (model == null)
		{
			return null;
		}

		final POJOWrapper wrapper = POJOWrapper.getWrapper(model);
		final Map<String, Object> valuesMap = wrapper.getValuesMap();

		Object value = null;
		if (valuesMap.containsKey(columnName))
		{
			value = valuesMap.get(columnName);
		}

		return value == null ? null : value.toString();
	}

	@Override
	protected Object retrieveModelOrNull(Properties ctx, int adTableId, int recordId)
	{
		return POJOLookupMap.get().lookup(adTableId, recordId);
	}

	@Override
	protected boolean processIt0(final DocAction doc, final String action) throws Exception
	{
		final String tableName = InterfaceWrapperHelper.getModelTableName(doc);
		final ArrayKey key = new ArrayKey(tableName, action);
		IProcessInterceptor interceptor = processInterceptors.get(key);
		if (interceptor == null)
		{
			interceptor = defaultProcessInterceptor;
		}

		return interceptor.processIt(doc, action);
	}

	public void registerProcessInterceptor(final String tableName, final String action, final IProcessInterceptor interceptor)
	{
		final ArrayKey key = new ArrayKey(tableName, action);
		processInterceptors.put(key, interceptor);
	}

	private final Map<ArrayKey, IProcessInterceptor> processInterceptors = new HashMap<ArrayKey, IProcessInterceptor>();

	private IProcessInterceptor defaultProcessInterceptor = PROCESSINTERCEPTOR_CompleteDirectly;

	public IProcessInterceptor getDefaultProcessInterceptor()
	{
		return defaultProcessInterceptor;
	}

	public void setDefaultProcessInterceptor(IProcessInterceptor defaultProcessInterceptor)
	{
		this.defaultProcessInterceptor = defaultProcessInterceptor;
	}

	public static interface IProcessInterceptor
	{
		boolean processIt(final DocAction doc, final String action) throws Exception;
	}

	public static final IProcessInterceptor PROCESSINTERCEPTOR_DirectCall = new IProcessInterceptor()
	{
		@Override
		public boolean processIt(DocAction doc, String action) throws Exception
		{
			return doc.processIt(action);
		}

	};

	public static final IProcessInterceptor PROCESSINTERCEPTOR_CompleteDirectly = new IProcessInterceptor()
	{

		@Override
		public boolean processIt(DocAction doc, String action) throws Exception
		{
			if (DocAction.ACTION_Complete.equals(action))
			{
				doc.setDocStatus(DocAction.STATUS_Completed);
				final POJOWrapper wrapper = POJOWrapper.getWrapper(doc);
				wrapper.setValue("DocAction", DocAction.ACTION_Close);
				wrapper.setValue("Processed", true);
				wrapper.setValue("Processing", false);
				return true;
			}
			else
			{
				return PROCESSINTERCEPTOR_DirectCall.processIt(doc, action);
			}
		}

	};
}

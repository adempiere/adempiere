package org.adempiere.ad.modelvalidator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.ad.modelvalidator.annotations.DocValidate;
import org.adempiere.ad.modelvalidator.annotations.DocValidates;
import org.adempiere.ad.modelvalidator.annotations.Init;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.ModelChanges;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.MClient;
import org.compiere.model.MSysConfig;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 * Wrapping class which introspect an object, identifies it's pointcuts (ModelChange, DocValidate etc) and maps them to {@link ModelValidator} interface.
 * 
 * @author tsa
 * 
 */
class AnnotatedModelValidator implements ModelValidator
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final transient Object annotatedObject;
	private final transient Class<?> annotatedClass;
	/**
	 * List of table names on which binding is allowed
	 */
	private transient List<?> allowedTargetTableNames = null;

	private final transient List<ValidatorInit> initializers = new ArrayList<ValidatorInit>();

	/** Map PointcutType(TableName,PointcutType) -> pointcuts list */
	private final transient Map<PointcutKey, List<Pointcut>> mapPointcuts = new HashMap<PointcutKey, List<Pointcut>>();

	private int clientId = -1;

	/**
	 * 
	 * @param annotatedObject
	 * @throws AdempiereException if annotations were not correctly used
	 */
	AnnotatedModelValidator(final Object annotatedObject) throws AdempiereException
	{
		Util.assume(annotatedObject != null, "annotatedObject is not null");
		this.annotatedObject = annotatedObject;
		this.annotatedClass = annotatedObject.getClass();

		loadAnnotatedClassDefinition();
		loadPointcuts();
	}

	/**
	 * 
	 * @return true if annotated class has any pointcuts (ModelChange, DocValidate etc)
	 */
	public boolean hasPointcuts()
	{
		return !mapPointcuts.isEmpty();
	}

	@Override
	public void initialize(final ModelValidationEngine engine, final MClient client)
	{
		if (client != null)
		{
			this.clientId = client.getAD_Client_ID();
		}

		bindPointcuts(engine);

		//
		// Execute initializers
		for (ValidatorInit init : initializers)
		{
			Object[] params = new Object[] {};
			if (init.isMethodRequiresEngine())
			{
				params = new Object[] { engine };
			}

			try
			{
				// Make sure the method is accessible
				final Method method = init.getMethod();
				if(!method.isAccessible())
				{
					method.setAccessible(true);
				}
				// Execute
				method.invoke(annotatedObject, params);
			}
			catch (Exception e)
			{
				throw new AdempiereException("Cannot initialize " + annotatedClass + ". Initializer " + init + " failed.", e);
			}

			logger.config("Initializer " + init + " executed successfully.");
		}
	}

	private void loadAnnotatedClassDefinition()
	{
		final Validator annValidator = annotatedClass.getAnnotation(Validator.class);
		if (annValidator == null)
		{
			throw new AdempiereException("Each model validator class shall be marked with " + Validator.class + " annotation");
		}

		final String tableName = InterfaceWrapperHelper.getTableNameOrNull(annValidator.value());
		if (tableName == null)
		{
			throw new AdempiereException("Cannot find tableName for " + annValidator.value());
		}
		allowedTargetTableNames = Arrays.asList(tableName);

		//
		// validate ModelValidator classname and:
		// * throw exception (if development mode)
		// * log warning if production mode
		if (!tableName.equals(annotatedClass.getSimpleName()))
		{
			final AdempiereException ex = new AdempiereException("According to metas best practices, model validator shall have the same name as the table."
					+ "Please rename class " + annotatedClass + " to " + tableName);
			if (MSysConfig.getBooleanValue("de.metas.adempiere.debug", false))
			{
				throw ex;
			}
			else
			{
				logger.log(Level.WARNING, ex.getLocalizedMessage(), ex);
			}
		}
	}

	private void loadPointcuts()
	{
		for (Method method : annotatedClass.getDeclaredMethods())
		{
			final Init annInit = method.getAnnotation(Init.class);
			if (annInit != null)
			{
				loadInitMethod(annInit, method);
			}

			final ModelChange annModelChange = method.getAnnotation(ModelChange.class);
			if (annModelChange != null)
			{
				loadPointcut(annModelChange, method);
			}

			final ModelChanges annModelChanges = method.getAnnotation(ModelChanges.class);
			if (annModelChanges != null && annModelChanges.value().length > 0)
			{
				for (ModelChange a : annModelChanges.value())
				{
					loadPointcut(a, method);
				}
			}

			final DocValidate annDocValidate = method.getAnnotation(DocValidate.class);
			if (annDocValidate != null)
			{
				loadPointcut(annDocValidate, method);
			}

			final DocValidates annDocValidates = method.getAnnotation(DocValidates.class);
			if (annDocValidates != null && annDocValidates.value().length > 0)
			{
				for (DocValidate a : annDocValidates.value())
				{
					loadPointcut(a, method);
				}
			}
		}
	}

	private void loadInitMethod(final Init annInit, final Method method)
	{
		final ValidatorInit init = new ValidatorInit(method);

		final Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length >= 1)
		{
			if (ModelValidationEngine.class.isAssignableFrom(parameterTypes[0]))
			{
				init.setMethodRequiresEngine(true);
			}
			else
			{
				throw new AdempiereException("Invalid initalizer method format. First argument shall be ModelValidationEngine if any - " + method);
			}
		}

		initializers.add(init);
	}

	private void loadPointcut(ModelChange annModelChange, Method method)
	{
		final Pointcut pointcut = new Pointcut(PointcutType.ModelChange, method, annModelChange.timings());
		pointcut.setChangedColumns(annModelChange.ifColumnsChanged());
		initPointcutAndAddToMap(pointcut);
	}

	private void loadPointcut(DocValidate annDocValidate, Method method)
	{
		final Pointcut pointcut = new Pointcut(PointcutType.DocValidate, method, annDocValidate.timings());
		initPointcutAndAddToMap(pointcut);
	}

	private void initPointcutAndAddToMap(final Pointcut pointcut)
	{
		final Method method = pointcut.getMethod();
		final Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes == null || parameterTypes.length == 0)
		{
			throw new AdempiereException("Invalid method " + pointcut.getMethod() + ": It has no arguments");
		}

		if (method.getReturnType() != void.class)
		{
			throw new AdempiereException("Return type should be void for " + method);
		}

		//
		// Model Class for binding
		final Class<?> modelClass = parameterTypes[0];
		pointcut.setModelClass(modelClass);

		//
		// Get table name
		final String tableName = InterfaceWrapperHelper.getTableNameOrNull(modelClass);
		if (Util.isEmpty(tableName))
		{
			throw new AdempiereException("Cannot find tablename for " + pointcut.getModelClass());
		}
		if (allowedTargetTableNames != null && !allowedTargetTableNames.contains(tableName))
		{
			throw new AdempiereException("Table " + tableName + " is not in the list of allowed tables names specified by " + Validator.class + " annotation: " + allowedTargetTableNames);
		}
		pointcut.setTableName(tableName);
		if (pointcut.getTableName() == null)
		{
			throw new AdempiereException("Invalid method " + pointcut.getMethod() + ": first argument is not a model class: " + pointcut.getModelClass());
		}

		//
		// Get/Validate timings
		if (pointcut.getTimings() == null || pointcut.getTimings().length == 0)
		{
			throw new AdempiereException("Invalid method " + pointcut.getMethod() + ": no timings were specified");
		}

		if (parameterTypes.length == 1)
		{
			pointcut.setMethodRequiresTiming(false);
		}
		else if (parameterTypes.length == 2 && (int.class.isAssignableFrom(parameterTypes[1])))
		{
			pointcut.setMethodRequiresTiming(true);
		}
		else
		{
			throw new AdempiereException("Invalid method " + pointcut.getMethod() + ": definition not supported");
		}

		// Add to map
		final PointcutKey key = mkKey(pointcut);
		List<Pointcut> list = mapPointcuts.get(key);
		if (list == null)
		{
			list = new ArrayList<Pointcut>();
			mapPointcuts.put(key, list);
		}
		list.add(pointcut);

		logger.config("Loaded " + pointcut);
	}

	private void bindPointcuts(ModelValidationEngine engine)
	{
		if (!hasPointcuts())
		{
			return;
		}

		logger.config("Binding pointcuts for " + annotatedClass);
		for (Map.Entry<PointcutKey, List<Pointcut>> e : mapPointcuts.entrySet())
		{
			final List<Pointcut> list = e.getValue();
			if (list == null || list.isEmpty())
			{
				continue;
			}

			final PointcutKey key = e.getKey();
			logger.config("Binding pointcuts for " + annotatedClass + " on " + key);

			switch (key.getType())
			{
				case ModelChange:
					engine.addModelChange(key.getTableName(), this);
					break;
				case DocValidate:
					engine.addDocValidate(key.getTableName(), this);
					break;
				default:
					throw new AdempiereException("Unknown PointcutType " + key.getType());
			}
		}
		logger.config("Binding pointcuts for " + annotatedClass + " done.");
	}

	@Override
	public int getAD_Client_ID()
	{
		return clientId;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		return null;
	}

	@Override
	public final String modelChange(PO po, int type)
	{
		execute(PointcutType.ModelChange, po, type);
		return null;
	}

	@Override
	public final String docValidate(PO po, int timing)
	{
		execute(PointcutType.DocValidate, po, timing);
		return null;
	}

	private void execute(PointcutType type, PO po, int timing)
	{
		final String tableName = po.get_TableName();
		final PointcutKey key = mkKey(tableName, type);
		final List<Pointcut> list = mapPointcuts.get(key);
		if (list == null || list.isEmpty())
		{
			return;
		}

		for (IPointcut info : list)
		{
			execute(info, po, timing);
		}
	}

	private void execute(IPointcut pointcut, PO po, int timing)
	{
		//
		// Check timings
		final int[] timings = pointcut.getTimings();
		if (timings == null || timings.length == 0)
		{
			return;
		}
		boolean timingFound = false;
		for (int t : timings)
		{
			if (t == timing)
			{
				timingFound = true;
				break;
			}
		}
		if (!timingFound)
		{
			return;
		}

		//
		// Check changed columns
		final String[] changedColumns = pointcut.getChangedColumns();
		if (changedColumns != null && changedColumns.length > 0)
		{
			boolean changed = false;
			for (String columnName : changedColumns)
			{
				final int idx = po.get_ColumnIndex(columnName);
				if (idx < 0)
				{
					logger.warning("Column " + columnName + " not found for " + po + ". Considering it as not changed");
					continue;
				}
				if (po.is_ValueChanged(idx))
				{
					changed = true;
					break;
				}
			}
			if (!changed)
			{
				// none of our columns had changed; skip
				return;
			}
		}

		final Object model = InterfaceWrapperHelper.create(po, pointcut.getModelClass());
		try
		{
			final Method method = pointcut.getMethod();

			// Make sure the method is accessible
			if (!method.isAccessible())
			{
				method.setAccessible(true);
			}

			if (CLogMgt.isLevel(Level.FINE))
			{
				logger.fine("Executing: " + pointcut);
			}
			if (pointcut.isMethodRequiresTiming())
			{
				method.invoke(annotatedObject, model, timing);
			}
			else
			{
				method.invoke(annotatedObject, model);
			}
		}
		catch (IllegalArgumentException e)
		{
			throw new AdempiereException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new AdempiereException(e);
		}
		catch (InvocationTargetException e)
		{
			final Throwable cause = e.getCause();
			// mo73_03444 if the pointcut method threw an adempiere exception, just forward it
			if(cause instanceof AdempiereException)
			{
				throw (AdempiereException)cause;
			}
			// mo73_03444 end
			throw new AdempiereException(cause);
		}
	}

	@Override
	public String toString()
	{
		return "annotated[" + this.annotatedClass.getName() + "]";
	}

	private final PointcutKey mkKey(Pointcut pointcut)
	{
		return mkKey(pointcut.getTableName(), pointcut.getType());
	}

	private final PointcutKey mkKey(String tableName, PointcutType type)
	{
		return new PointcutKey(tableName, type);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotatedClass == null) ? 0 : annotatedClass.hashCode());
		result = prime * result + ((annotatedObject == null) ? 0 : annotatedObject.hashCode());
		result = prime * result + clientId;
		return result;
	}

	@SuppressWarnings("PMD")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnotatedModelValidator other = (AnnotatedModelValidator)obj;
		if (annotatedClass == null)
		{
			if (other.annotatedClass != null)
				return false;
		}
		else if (!annotatedClass.equals(other.annotatedClass))
			return false;
		if (annotatedObject == null)
		{
			if (other.annotatedObject != null)
				return false;
		}
		else if (!annotatedObject.equals(other.annotatedObject))
			return false;
		if (clientId != other.clientId)
			return false;
		return true;
	}

	private static class PointcutKey
	{
		private final String tableName;
		private final PointcutType type;

		public PointcutKey(String tableName, PointcutType type)
		{
			super();
			this.tableName = tableName;
			this.type = type;
		}

		public String getTableName()
		{
			return tableName;
		}

		public PointcutType getType()
		{
			return type;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@SuppressWarnings("PMD")
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PointcutKey other = (PointcutKey)obj;
			if (tableName == null)
			{
				if (other.tableName != null)
					return false;
			}
			else if (!tableName.equals(other.tableName))
				return false;
			if (type != other.type)
				return false;
			return true;
		}

		@Override
		public String toString()
		{
			return "PointcutKey [tableName=" + tableName + ", type=" + type + "]";
		}
	}
}

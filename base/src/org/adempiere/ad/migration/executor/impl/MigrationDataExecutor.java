package org.adempiere.ad.migration.executor.impl;

import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.model.I_AD_MigrationData;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.ad.migration.util.IDataConverter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Column;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

public class MigrationDataExecutor
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IMigrationExecutorContext migrationCtx;
	private final I_AD_MigrationStep step;
	private final I_AD_MigrationData data;
	private final PO po;
	private final IDataConverter converter;

	public MigrationDataExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step, final I_AD_MigrationData data, final PO po, final IDataConverter converter)
	{
		this.migrationCtx = migrationCtx;
		this.step = step;
		this.data = data;
		this.po = po;
		this.converter = converter;
	}

	public void apply()
	{
		// TODO: option to apply only when existing value equals reference value

		final String value;
		if (data.isNewNull())
		{
			value = null;
		}
		else
		{
			value = data.getNewValue();
		}

		final I_AD_Column column = data.getAD_Column();
		if (column == null)
		{
			if (migrationCtx.isSkipMissingColumns())
			{
				logger.log(Level.INFO, "Skipped data for missing column name " + data.getColumnName());
				return;
			}
			else
			{
				throw new AdempiereException("Column name '" + data.getColumnName() + " not found for " + data);
			}
		}

		// backup existing value
		if (po != null && !po.is_new())
		{
			final Object backupValue = po.get_Value(column.getColumnName());
			if (backupValue == null)
			{
				data.setBackupValue(null);
				data.setIsBackupNull(true);
			}
			else
			{
				final String backupValueStr = converter.objectToString(column, backupValue);
				data.setBackupValue(backupValueStr);
				data.setIsBackupNull(false);
			}

			InterfaceWrapperHelper.save(data);
		}

		// apply new values
		if (X_AD_MigrationStep.ACTION_Insert.equals(step.getAction()) || X_AD_MigrationStep.ACTION_Update.equals(step.getAction()))
		{
			final Object valueObj = converter.stringToObject(column, value);
			if (IDataConverter.VALUE_Unknown != valueObj)
			{
				po.set_ValueNoCheck(column.getColumnName(), valueObj);
			}
		}
	}

	public void rollback()
	{
		final I_AD_Column column = data.getAD_Column();

		final Object value;
		if (data.isBackupNull())
		{
			value = null;
		}
		else
		{
			final String valueStr = data.getBackupValue();
			value = converter.stringToObject(column, valueStr);
		}

		if (value == IDataConverter.VALUE_Unknown)
		{
			return;
		}

		po.set_ValueNoCheck(column.getColumnName(), value);
	}

}

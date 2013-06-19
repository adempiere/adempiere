package org.adempiere.ad.migration.executor.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.model.I_AD_MigrationData;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.ad.migration.service.IMigrationDAO;
import org.adempiere.ad.migration.util.DefaultDataConverter;
import org.adempiere.ad.migration.util.IDataConverter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;

public class POMigrationStepExecutor extends AbstractMigrationStepExecutor
{
	private final IDataConverter converter = new DefaultDataConverter();

	private List<I_AD_MigrationData> m_migrationData;
	private List<I_AD_MigrationData> m_migrationDataKeys;

	public POMigrationStepExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step)
	{
		super(migrationCtx, step);
	}

	@Override
	public ExecutionResult apply(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		if (step.getAD_Table_ID() <= 0)
		{
			if (getMigrationExecutorContext().isSkipMissingTables())
			{
				return ExecutionResult.Skipped;
			}
			else
			{
				throw new AdempiereException("@NotFound@ @AD_Table_ID@=" + step.getAD_Table_ID() + " (@TableName@: " + step.getTableName() + ") on step " + step);
			}
		}

		//
		// Fetch PO, create if not exist and action is Insert
		final boolean createPOIfNotExists = X_AD_MigrationStep.ACTION_Insert.equals(step.getAction())
				&& getMigrationExecutorContext().isApplyDML();
		final PO po = fetchPO(createPOIfNotExists, trxName);

		//
		// DML: Apply migration data
		if (getMigrationExecutorContext().isApplyDML())
		{
			for (I_AD_MigrationData data : getMigrationData())
			{
				final MigrationDataExecutor dataExecutor = new MigrationDataExecutor(getMigrationExecutorContext(), step, data, po, converter);
				dataExecutor.apply();
			}
		}

		//
		// DDL: Synchronize with database
		if (X_AD_MigrationStep.ACTION_Delete.equals(step.getAction()))
		{
			if (getMigrationExecutorContext().isApplyDDL() && po != null)
			{
				if (I_AD_Column.Table_Name.equals(po.get_TableName()))
				{
					syncDBColumn(InterfaceWrapperHelper.create(po, I_AD_Column.class), true);
				}
			}

			if (getMigrationExecutorContext().isApplyDML())
			{
				po.deleteEx(false);
			}
		}
		else
		{
			if (getMigrationExecutorContext().isApplyDML())
			{
				po.saveEx();
			}

			if (I_AD_Column.Table_Name.equals(po.get_TableName())
					&& getMigrationExecutorContext().isApplyDDL())
			{
				syncDBColumn(InterfaceWrapperHelper.create(po, I_AD_Column.class), false);
			}
		}

		log(null, "Applied", false);
		return ExecutionResult.Executed;
	}

	@Override
	public ExecutionResult rollback(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		if (step.getAD_Table_ID() <= 0)
		{
			throw new AdempiereException("@NotFound@ @AD_Table_ID@");
		}

		final boolean createPOIfNotExists = X_AD_MigrationStep.ACTION_Delete.equals(step.getAction()); // create if not exist and action is Delete
		final PO po = fetchPO(createPOIfNotExists, trxName);

		if (po == null)
		{
			log("PO not found", "SKIP", true);
			return ExecutionResult.Skipped;
		}

		//
		// Action=Insert => Rollback action=Delete
		if (X_AD_MigrationStep.ACTION_Insert.equals(step.getAction()))
		{
			if (I_AD_Column.Table_Name.equals(po.get_TableName()))
			{
				syncDBColumn(InterfaceWrapperHelper.create(po, I_AD_Column.class), true);
			}

			po.deleteEx(false);
		}
		//
		// Action=Update/Delete => Rollback action=Insert/Update
		else if (X_AD_MigrationStep.ACTION_Update.equals(step.getAction())
				|| X_AD_MigrationStep.ACTION_Delete.equals(step.getAction()))
		{
			for (final I_AD_MigrationData data : getMigrationData())
			{
				final MigrationDataExecutor dataExecutor = new MigrationDataExecutor(getMigrationExecutorContext(), step, data, po, converter);
				dataExecutor.rollback();
			}

			po.saveEx();

			if (I_AD_Column.Table_Name.equals(po.get_TableName()))
			{
				syncDBColumn(InterfaceWrapperHelper.create(po, I_AD_Column.class), false);
			}
		}
		else
		{
			throw new AdempiereException("Unknown step action " + step.getAction());
		}

		log(null, "Rollback", false);
		return ExecutionResult.Executed;
	}

	private PO fetchPO(final boolean createIfNotExists, final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		final MTable table = MTable.get(getCtx(), step.getAD_Table_ID());
		final StringBuffer whereClause = new StringBuffer();
		final LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		//
		// Single Key Record
		if (step.getRecord_ID() > 0)
		{
			final String[] keyColumns = table.getKeyColumns();
			if (keyColumns == null || keyColumns.length != 1)
			{
				logger.warning("Table " + table + " has none or more then one key columns: " + keyColumns);
			}
			else
			{
				whereClause.append(keyColumns[0]).append("=?");
				params.put(keyColumns[0], step.getRecord_ID());
			}
		}

		//
		// Trying to identify the key columns from our records
		if (whereClause.length() == 0)
		{
			final List<I_AD_MigrationData> keys = getKeyData();
			for (I_AD_MigrationData key : keys)
			{
				if (whereClause.length() > 0)
				{
					whereClause.append(" AND ");
				}

				final I_AD_Column column = key.getAD_Column();
				final String columnName = column.getColumnName();
				final String valueStr = key.getNewValue();
				final Object value = converter.stringToObject(column, valueStr);

				whereClause.append(columnName).append("=?");
				params.put(columnName, value);
			}
		}

		//
		// Query PO
		PO po = new Query(getCtx(), table, whereClause.toString(), trxName)
				.setParameters(params.values().toArray())
				.firstOnly();

		//
		// Create new PO
		if (po == null && createIfNotExists)
		{
			po = table.getPO(0, trxName);

			for (String columnName : po.get_KeyColumns())
			{
				final Object value = params.get(columnName);
				if (value == null)
				{
					logger.warning("No value found for key " + columnName);
				}
				po.set_ValueNoCheck(columnName, value);
			}
			po.setIsAssignedID(true);
		}

		return po;
	}

	private List<I_AD_MigrationData> getMigrationData()
	{
		if (m_migrationData == null)
		{
			m_migrationData = Services.get(IMigrationDAO.class).retrieveMigrationData(getAD_MigrationStep());
			m_migrationDataKeys = null;
		}
		return m_migrationData;
	}

	private List<I_AD_MigrationData> getKeyData()
	{
		if (m_migrationDataKeys != null)
		{
			return m_migrationDataKeys;
		}

		final List<I_AD_MigrationData> dataKeys = new ArrayList<I_AD_MigrationData>();
		final List<I_AD_MigrationData> dataParents = new ArrayList<I_AD_MigrationData>();
		for (I_AD_MigrationData data : getMigrationData())
		{
			final I_AD_Column column = data.getAD_Column();
			if (column.isKey())
			{
				dataKeys.add(data);
			}
			if (column.isParent())
			{
				dataParents.add(data);
			}
		}

		if (dataKeys.size() == 1)
		{
			m_migrationDataKeys = dataKeys;
			return m_migrationDataKeys;
		}

		return m_migrationDataKeys;
	}

	private void syncDBColumn(final I_AD_Column column, final boolean drop)
	{
		//
		// Get MColumn object
		final PO po = InterfaceWrapperHelper.getPO(column);
		if (po == null)
		{
			throw new IllegalArgumentException("Invalid column " + column);
		}
		final MColumn columnPO;
		if (po instanceof MColumn)
		{
			columnPO = (MColumn)column;
		}
		else
		{
			// TODO: This case shall not happen
			columnPO = new MColumn(po.getCtx(), column.getAD_Column_ID(), po.get_TrxName());
			logger.warning("Loaded " + columnPO + " from " + column + ". Please consider optimizing it");
		}

		//
		if (drop)
		{
			// TODO unsync column?
			logger.warning("Please manualy drop column " + column.getColumnName() + "(" + column.getAD_Table() + ")");
		}
		else
		{
			columnPO.syncDatabase();
		}
	}

}

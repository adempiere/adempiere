package org.adempiere.ad.migration.logger.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.adempiere.ad.migration.logger.IMigrationLogger;
import org.adempiere.ad.migration.logger.IMigrationLoggerContext;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationData;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.ad.migration.service.IMigrationBL;
import org.adempiere.ad.migration.util.DefaultDataConverter;
import org.adempiere.ad.migration.util.IDataConverter;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.model.POWrapper;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Field;
import org.compiere.model.I_AD_Process_Para;
import org.compiere.model.I_AD_Ref_List;
import org.compiere.model.I_AD_Ref_Table;
import org.compiere.model.I_AD_Session;
import org.compiere.model.I_AD_Tab;
import org.compiere.model.I_AD_Table;
import org.compiere.model.MSession;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.POInfoColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;

public class MigrationLogger implements IMigrationLogger
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IDataConverter converter = new DefaultDataConverter();

	private final Set<String> tablesIgnoreList = new HashSet<String>();

	public MigrationLogger()
	{
		initTablesIgnoreList();
	}

	private void initTablesIgnoreList()
	{
		final List<String> list = Arrays.asList(new String[] {
				"AD_ACCESSLOG", "AD_ALERTPROCESSORLOG", "AD_CHANGELOG",
				"AD_ISSUE", "AD_LDAPPROCESSORLOG", "AD_PACKAGE_IMP",
				"AD_PACKAGE_IMP_BACKUP", "AD_PACKAGE_IMP_DETAIL", "AD_PACKAGE_IMP_INST",
				"AD_PACKAGE_IMP_PROC", "AD_PINSTANCE", "AD_PINSTANCE_LOG",
				"AD_PINSTANCE_PARA", "AD_REPLICATION_LOG", "AD_SCHEDULERLOG",
				"AD_SESSION", "AD_WORKFLOWPROCESSORLOG", "CM_WEBACCESSLOG",
				"C_ACCTPROCESSORLOG", "K_INDEXLOG", "R_REQUESTPROCESSORLOG",
				"T_AGING", "T_ALTER_COLUMN", "T_DISTRIBUTIONRUNDETAIL",
				"T_INVENTORYVALUE", "T_INVOICEGL", "T_REPLENISH",
				"T_REPORT", "T_REPORTSTATEMENT", "T_SELECTION",
				"T_SELECTION2", "T_SPOOL", "T_TRANSACTION",
				"T_TRIALBALANCE", "AD_PROCESS_ACCESS", "AD_WINDOW_ACCESS",
				"AD_WORKFLOW_ACCESS", "AD_FORM_ACCESS",
				"AD_MIGRATION", "AD_MIGRATIONSTEP", "AD_MIGRATIONDATA",
				// Don't log AD_Sequence because these will be created automatically (at least for Table_ID)
				"AD_SEQUENCE",
		});

		tablesIgnoreList.addAll(list);
	}

	private boolean isLogTableName(String tableName)
	{
		if (tablesIgnoreList.contains(tableName.toUpperCase()))
		{
			return false;
		}
		return true;
	}

	@Override
	public void addTableToIgnoreList(String tableName)
	{
		tablesIgnoreList.add(tableName.toUpperCase());
	}

	@Override
	public void removeTableFromIgnoreList(String tableName)
	{
		tablesIgnoreList.remove(tableName.toUpperCase());
	}

	@Override
	public void logMigration(I_AD_Session session, PO po, POInfo info, String event)
	{
		final IMigrationLoggerContext migrationCtx = getSessionMigrationLoggerContext(session);
		logMigration(migrationCtx, po, info, event);
	}

	@Override
	public void logMigration(IMigrationLoggerContext migrationCtx, PO po, POInfo info, String event)
	{
		if (!migrationCtx.isEnabled())
		{
			return;
		}
		if (!isLogTableName(po.get_TableName()))
		{
			return;
		}
		if (!isLogPO(po, info))
		{
			return;
		}

		I_AD_MigrationStep migrationStep = null;
		List<I_AD_MigrationData> stepDataList = null;

		final int size = po.get_ColumnCount();
		for (int i = 0; i < size; i++)
		{
			final I_AD_MigrationData data = createMigrationData(po, info, i, event);
			//
			// Save if needed
			if (data != null)
			{
				if (migrationStep == null)
				{
					migrationStep = createMigrationStep(migrationCtx, po, event);
					stepDataList = new ArrayList<I_AD_MigrationData>();
				}
				data.setAD_MigrationStep_ID(migrationStep.getAD_MigrationStep_ID());
				InterfaceWrapperHelper.save(data);
				stepDataList.add(data);
			}
		}

		//
		// Set migration comments (useful for quick review)
		if (migrationStep != null && !stepDataList.isEmpty())
		{
			setComments(po, migrationStep, stepDataList);
		}
	}

	private boolean isLogPO(PO po, POInfo pinfo)
	{
		// ignore statistic updates
		// TODO: metas: me00_02662: shall be deleted because it's handled by AD_Column.IsCalculated flag
		if (pinfo.getTableName().equalsIgnoreCase("AD_Process") && !po.is_new() && po.is_ValueChanged("Statistic_Count"))
		{
			return false;
		}
		return true;
	}

	private I_AD_MigrationData createMigrationData(PO po, POInfo info, int columnIndex, String event)
	{
		// Don't log encrypted columns
		if (info.isEncrypted(columnIndex))
		{
			return null;
		}
		// Don't log virtual columns
		if (info.isVirtualColumn(columnIndex))
		{
			return null;
		}
		// Don't log calculated columns
		if (info.isCalculated(columnIndex))
		{
			return null;
		}

		final POInfoColumn infoColumn = info.getColumn(columnIndex);

		final I_AD_MigrationData data = InterfaceWrapperHelper.create(po.getCtx(), I_AD_MigrationData.class, po.get_TrxName());
		data.setColumnName(infoColumn.ColumnName);
		data.setAD_Column_ID(infoColumn.AD_Column_ID);
		boolean create = false;

		//
		// Reference data (old value) on delete/update
		if (X_AD_MigrationStep.ACTION_Delete.equals(event)
				|| (X_AD_MigrationStep.ACTION_Update.equals(event) && po.is_ValueChanged(columnIndex)))
		{
			final Object valueOld = po.get_ValueOld(columnIndex);
			if (valueOld == null)
			{
				data.setIsOldNull(true);
				data.setOldValue(null);
			}
			else
			{
				final String valueOldStr = converter.objectToString(infoColumn, valueOld);
				data.setIsOldNull(false);
				data.setOldValue(valueOldStr);
			}
			create = true;
		}

		//
		// Save new value
		if (X_AD_MigrationStep.ACTION_Insert.equals(event)
				|| (X_AD_MigrationStep.ACTION_Update.equals(event) && po.is_ValueChanged(columnIndex)))
		{
			final Object value = po.get_Value(columnIndex);
			if (value == null)
			{
				data.setIsNewNull(true);
				data.setNewValue(null);
			}
			else
			{
				final String valueStr = converter.objectToString(infoColumn, value);
				data.setIsNewNull(false);
				data.setNewValue(valueStr);
			}
			create = true;
		}

		return create ? data : null;
	}

	/**
	 * Create and set a short description about what was changed in this step
	 * 
	 * @param po
	 * @param migrationStep
	 * @param stepDataList
	 */
	protected void setComments(PO po, I_AD_MigrationStep migrationStep, List<I_AD_MigrationData> stepDataList)
	{
		final boolean isDeleted = X_AD_MigrationStep.ACTION_Delete.equals(migrationStep.getAction());
		final String poStr = getSummary(po, isDeleted);
		if (Check.isEmpty(poStr))
		{
			return;
		}

		if (isDeleted) // X_AD_MigrationStep.ACTION_Delete.equals(migrationStep.getAction())
		{
			migrationStep.setComments("Deleted " + poStr);
		}
		else if (X_AD_MigrationStep.ACTION_Insert.equals(migrationStep.getAction()))
		{
			migrationStep.setComments("Created " + poStr);
		}
		else if (X_AD_MigrationStep.ACTION_Update.equals(migrationStep.getAction()))
		{
			// build fields info
			final StringBuffer fieldsInfo = new StringBuffer();
			for (I_AD_MigrationData data : stepDataList)
			{
				final String dataStr = getSummary(data);
				if (Check.isEmpty(dataStr, true))
				{
					continue;
				}
				if (fieldsInfo.length() > 0)
				{
					fieldsInfo.append(", ");
				}
				fieldsInfo.append(dataStr);
			}

			migrationStep.setComments("Updated " + poStr + ": " + fieldsInfo);
		}

		InterfaceWrapperHelper.save(migrationStep);
	}

	protected String getSummary(I_AD_MigrationData data)
	{
		String columnName = data.getAD_Column().getColumnName();
		return columnName;
	}

	protected String getSummary(PO po, boolean isDeleted)
	{
		if (po == null)
		{
			return null;
		}

		final String trxName = po.get_TrxName();

		//
		// Handle AD_Table
		if (I_AD_Table.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Table table = POWrapper.create(po, I_AD_Table.class, isDeleted);
			final String tableName = table.getTableName();
			return "Table " + tableName;
		}
		//
		// Handle AD_Column
		else if (I_AD_Column.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Column column = POWrapper.create(po, I_AD_Column.class, isDeleted);
			final String tableName = DB.getSQLValueString(trxName, "SELECT t.TableName FROM AD_Table t WHERE t.AD_Table_ID=?", column.getAD_Table_ID());
			return "Column " + tableName + "." + column.getColumnName();
		}
		//
		// Handle AD_Tab
		else if (I_AD_Tab.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Tab tab = POWrapper.create(po, I_AD_Tab.class, isDeleted);
			final String sql = "SELECT w.Name||'-'||?||'['||t.TableName||']'"
					+ " FROM AD_Window w, AD_Table t"
					+ " WHERE w.AD_Window_ID=? AND t.AD_Table_ID=?";
			final String tabStr = DB.getSQLValueString(trxName, sql, tab.getName(), tab.getAD_Window_ID(), tab.getAD_Table_ID());
			return "Tab " + tabStr;
		}
		//
		// Handle AD_Field
		else if (I_AD_Field.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Field field = POWrapper.create(po, I_AD_Field.class, isDeleted);
			final String sql = "SELECT w.Name||'-'||tt.Name||'['||t.TableName||']-'||?||'['||c.ColumnName||']'"
					+ " FROM AD_Tab tt, AD_Window w, AD_Table t, AD_Column c"
					+ " WHERE tt.AD_Tab_ID=? AND tt.AD_Window_ID=w.AD_Window_ID AND t.AD_Table_ID=tt.AD_Table_ID AND c.AD_Column_ID=?";

			final String fieldStr = DB.getSQLValueString(trxName, sql, field.getName(), field.getAD_Tab_ID(), field.getAD_Column_ID());
			return "Field " + fieldStr;
		}
		//
		// Handle AD_Process_Para
		else if (I_AD_Process_Para.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Process_Para ppara = POWrapper.create(po, I_AD_Process_Para.class, isDeleted);
			final String processName = DB.getSQLValueString(trxName, "SELECT p.Name FROM AD_Process p WHERE p.AD_Process_ID=?", ppara.getAD_Process_ID());
			return "Process param " + processName + "." + ppara.getColumnName();
		}
		//
		// Handle AD_Ref_List
		else if (I_AD_Ref_List.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Ref_List refList = POWrapper.create(po, I_AD_Ref_List.class, isDeleted);
			final String refName = DB.getSQLValueString(trxName, "SELECT r.Name FROM AD_Reference r WHERE r.AD_Reference_ID=?", refList.getAD_Reference_ID());
			return "Ref List " + refName + "." + refList.getValue() + "_" + refList.getName();
		}
		//
		// Handle AD_Ref_List
		else if (I_AD_Ref_Table.Table_Name.equals(po.get_TableName()))
		{
			final I_AD_Ref_Table refTable = POWrapper.create(po, I_AD_Ref_Table.class, isDeleted);
			final String refTableName = DB.getSQLValueString(trxName,
					"SELECT r.Name||'-'||t.TableName"
							+ " FROM AD_Reference r, AD_Table t"
							+ " WHERE r.AD_Reference_ID=? AND t.AD_Table_ID=?",
					refTable.getAD_Reference_ID(), refTable.getAD_Table_ID());
			return "Ref Table " + refTableName;
		}
		//
		// Handle general case
		else
		{
			final StringBuffer sb = new StringBuffer();
			final int size = po.get_ColumnCount();
			for (int i = 0; i < size; i++)
			{
				String columnName = po.get_ColumnName(i);
				if (columnName.indexOf("Name") >= 0
						|| columnName.equals("Value"))
				{
					final Object value = isDeleted ? po.get_ValueOld(i) : po.get_Value(i);
					final String valueStr = String.valueOf(value);
					if (Check.isEmpty(valueStr, true))
					{
						continue;
					}
					if (sb.length() > 0)
					{
						sb.append(", ");
					}
					sb.append(columnName).append("=").append(value);
				}
			}

			if (sb.length() == 0)
			{
				return null;
			}

			sb.insert(0, po.get_TableName() + "[");
			sb.append("]");
			return sb.toString();
		}
	}

	@Override
	public void logMigrationSQL(PO contextPO, String sql)
	{
		final Properties ctx = contextPO == null ? Env.getCtx() : contextPO.getCtx();
		final I_AD_Session session = MSession.get(ctx, false);
		if (session == null)
		{
			logger.warning("AD_Session not found");
			return;
		}
		final IMigrationLoggerContext migrationCtx = getSessionMigrationLoggerContext(session);

		if (!migrationCtx.isEnabled())
		{
			return;
		}

		final I_AD_MigrationStep migrationStep = createMigrationStep(migrationCtx, contextPO);
		migrationStep.setStepType(X_AD_MigrationStep.STEPTYPE_SQLStatement);
		migrationStep.setDBType(X_AD_MigrationStep.DBTYPE_AllDatabaseTypes);
		migrationStep.setSQLStatement(sql);
		InterfaceWrapperHelper.save(migrationStep);
	}

	protected I_AD_MigrationStep createMigrationStep(final IMigrationLoggerContext migrationCtx, final PO contextPO)
	{
		String entityType = null;
		final Properties ctx;
		final String trxName;
		if (contextPO != null)
		{
			final int idxEntityType = contextPO.get_ColumnIndex("EntityType");
			if (idxEntityType >= 0)
			{
				entityType = contextPO.get_ValueAsString("EntityType");
			}

			ctx = contextPO.getCtx();
			trxName = contextPO.get_TrxName();
		}
		else
		{
			ctx = Env.getCtx();
			trxName = null;
		}

		final I_AD_Migration migration = getCreateMigration(migrationCtx, entityType);

		final I_AD_MigrationStep migrationStep = InterfaceWrapperHelper.create(ctx, I_AD_MigrationStep.class, trxName);
		migrationStep.setAD_Migration(migration);

		return migrationStep;
	}

	protected I_AD_MigrationStep createMigrationStep(final IMigrationLoggerContext migrationCtx, PO po, String event)
	{
		final I_AD_MigrationStep migrationStep = createMigrationStep(migrationCtx, po);
		migrationStep.setStepType(X_AD_MigrationStep.STEPTYPE_ApplicationDictionary);
		migrationStep.setAction(event);
		migrationStep.setAD_Table_ID(po.get_Table_ID());
		migrationStep.setTableName(po.get_TableName());
		migrationStep.setRecord_ID(po.get_ID());
		Services.get(IMigrationBL.class).setSeqNo(migrationStep);
		InterfaceWrapperHelper.save(migrationStep);

		return migrationStep;
	}

	private I_AD_Migration getCreateMigration(final IMigrationLoggerContext mctx, String entityType)
	{
		String entityTypeActual = entityType;
		if (entityTypeActual == null)
		{
			entityTypeActual = getDefaultEntityType();
		}

		final String key = entityTypeActual;
		I_AD_Migration migration = mctx.getMigration(key);
		if (migration == null
				|| !migration.isActive()) // migration was closed, open another one
		{
			migration = createMigration(Env.getCtx(), entityTypeActual);
			mctx.putMigration(key, migration);
		}
		return migration;
	}

	protected I_AD_Migration createMigration(Properties ctx, String entityType)
	{
		// this record shall be created out of transaction since it will accessible in more then one transaction
		final String trxName = null;

		final I_AD_Migration migration = InterfaceWrapperHelper.create(ctx, I_AD_Migration.class, trxName);
		migration.setEntityType(entityType);
		Services.get(IMigrationBL.class).setSeqNo(migration);
		setName(migration);
		InterfaceWrapperHelper.save(migration);

		return migration;
	}

	protected void setName(I_AD_Migration migration)
	{
		final String name = MSysConfig.getValue("DICTIONARY_ID_COMMENTS");
		migration.setName(name);
	}

	protected String getDefaultEntityType()
	{
		boolean dict = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		return dict ? "D" : "U";
	}

	protected IMigrationLoggerContext getSessionMigrationLoggerContext(final I_AD_Session session)
	{
		final String key = getClass().getCanonicalName();
		IMigrationLoggerContext mctx = (IMigrationLoggerContext)InterfaceWrapperHelper.getDynAttribute(session, key);
		if (mctx == null)
		{
			mctx = new SessionMigrationLoggerContext();
			InterfaceWrapperHelper.setDynAttribute(session, key, mctx);
		}

		return mctx;
	}
}

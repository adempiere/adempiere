package org.adempiere.ad.migration.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.adempiere.ad.migration.logger.IMigrationLogger;
import org.adempiere.ad.migration.logger.IMigrationLoggerContext;
import org.adempiere.ad.migration.logger.impl.SingletonMigrationLoggerContext;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.adempiere.util.collections.IteratorUtils;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_Menu;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_AD_TreeNodeMM;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.Util;

public class AD_Migration_CreateFromEntityType extends SvrProcess
{
	private I_AD_Migration migration;
	private String entityType;

	@Override
	protected void prepare()
	{
		for (final ProcessInfoParameter p : getParameter())
		{
			String para = p.getParameterName();
			if ("EntityType".equals(para))
				entityType = (String)p.getParameter();
		}

		if (I_AD_Migration.Table_Name.equals(getTableName()) && getRecord_ID() > 0)
		{
			this.migration = InterfaceWrapperHelper.create(getCtx(), getRecord_ID(), I_AD_Migration.class, get_TrxName());
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		if (migration == null || migration.getAD_Migration_ID() <= 0)
		{
			throw new AdempiereException("@NotFound@ @AD_Migration_ID@");
		}

		final SingletonMigrationLoggerContext migrationCtx = new SingletonMigrationLoggerContext(migration);
		migrationCtx.setGenerateComments(false);

		final IMigrationLogger migrationLogger = Services.get(IMigrationLogger.class);

		final Iterator<I_AD_Table> tables = retrieveTablesWithEntityType();
		for (final I_AD_Table table : IteratorUtils.asIterable(tables))
		{
			final Iterator<PO> records = retrieveRecordsForEntityType(table, entityType);
			for (final PO record : IteratorUtils.asIterable(records))
			{
				DB.saveConstraints();
				DB.getConstraints().addAllowedTrxNamePrefix("POSave");
				try
				{
					migrationLogger.logMigration(migrationCtx, record, record.getPOInfo(), X_AD_MigrationStep.ACTION_Insert);
					logDependents(migrationCtx, migrationLogger, record);
				}
				finally
				{
					DB.restoreConstraints();
				}
			}
		}

		migration.setIsDeferredConstraints(true);
		InterfaceWrapperHelper.save(migration);

		return "OK";
	}

	private void logDependents(final IMigrationLoggerContext migrationCtx, final IMigrationLogger migrationLogger, final PO record)
	{
		final String tableName = InterfaceWrapperHelper.getModelTableName(record);
		if (I_AD_Column.Table_Name.equals(tableName))
		{
			final I_AD_Column adColumn = InterfaceWrapperHelper.create(record, I_AD_Column.class);

			final I_AD_Element adElement = adColumn.getAD_Element();
			if (!Util.equals(adElement.getEntityType(), entityType))
			{
				adElement.setEntityType("D"); // do NOT save, NEVER save!

				final PO adElementPO = InterfaceWrapperHelper.getPO(adElement);
				final POInfo adElementPOInfo = adElementPO.getPOInfo();

				migrationLogger.logMigration(migrationCtx, adElementPO, adElementPOInfo, X_AD_MigrationStep.ACTION_Insert);
			}
		}
		else if (I_AD_Menu.Table_Name.equals(tableName))
		{
			final I_AD_Menu menu = InterfaceWrapperHelper.create(record, I_AD_Menu.class);
			final POInfo poInfo = POInfo.getPOInfo(getCtx(), I_AD_TreeNodeMM.Table_Name);
			for (final I_AD_TreeNodeMM nodeMM : retrieveADTreeNodeMM(menu))
			{
				final PO po = InterfaceWrapperHelper.getPO(nodeMM);
				migrationLogger.logMigration(migrationCtx, po, poInfo, X_AD_MigrationStep.ACTION_Insert);
			}
		}
	}

	private List<I_AD_TreeNodeMM> retrieveADTreeNodeMM(final I_AD_Menu menu)
	{
		final Properties ctx = InterfaceWrapperHelper.getCtx(menu);
		final String trxName = InterfaceWrapperHelper.getTrxName(menu);

		final StringBuilder whereClause = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		whereClause.append(I_AD_TreeNodeMM.COLUMNNAME_Node_ID).append("=?");
		params.add(menu.getAD_Menu_ID());

		return new Query(ctx, I_AD_TreeNodeMM.Table_Name, whereClause.toString(), trxName)
				.setParameters(params)
				.list(I_AD_TreeNodeMM.class);
	}

	private Iterator<I_AD_Table> retrieveTablesWithEntityType()
	{
		final StringBuilder whereClause = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		whereClause.append(" EXISTS (select 1 from AD_Column c where c.AD_Table_ID=AD_Table.AD_Table_ID and c.ColumnName=?)");
		params.add("EntityType");

		whereClause.append(" AND ").append(I_AD_Table.COLUMNNAME_IsView).append("=?");
		params.add(false);

		return new Query(getCtx(), I_AD_Table.Table_Name, whereClause.toString(), Trx.TRXNAME_None)
				.setParameters(params)
				.setOrderBy(I_AD_Table.Table_Name)
				.list(I_AD_Table.class)
				.iterator();
	}

	private Iterator<PO> retrieveRecordsForEntityType(final I_AD_Table table, final String entityType)
	{
		final String whereClause = "EntityType=?";

		final Iterator<PO> records = new Query(getCtx(), table.getTableName(), whereClause, Trx.TRXNAME_None)
				.setParameters(entityType)
				.setOrderBy("Created")
				.iterate(null, false);

		return records;
	}
}

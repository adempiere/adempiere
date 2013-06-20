package org.adempiere.ad.migration.executor.impl;

import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IPostponedExecutable;
import org.adempiere.util.Check;
import org.compiere.model.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;

public class ColumnSyncDDLExecutable implements IPostponedExecutable
{
	protected final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IMigrationExecutorContext migrationCtx;
	private final int adColumnId;
	private final boolean drop;

	public ColumnSyncDDLExecutable(IMigrationExecutorContext migrationCtx, final int adColumnId, final boolean drop)
	{
		Check.assume(adColumnId > 0, "adColumnId > 0");

		this.migrationCtx = migrationCtx;
		this.adColumnId = adColumnId;
		this.drop = drop;
	}

	@Override
	public void execute()
	{
		final MColumn column = retrieveColumn();
		if (column == null)
		{
			logger.log(Level.INFO, "No AD_Column found for " + adColumnId + ". Skip");
			return;
		}

		//
		if (drop)
		{
			// TODO unsync column?
			logger.warning("Please manualy drop column " + column.getColumnName() + "(" + column.getAD_Table() + ")");
		}
		else
		{
			column.syncDatabase();
		}
	}

	private MColumn retrieveColumn()
	{
		final MColumn column = new Query(migrationCtx.getCtx(), I_AD_Column.Table_Name, I_AD_Column.COLUMNNAME_AD_Column_ID + "=?", Trx.TRXNAME_None)
				.setParameters(adColumnId)
				.firstOnly();
		return column;
	}
}

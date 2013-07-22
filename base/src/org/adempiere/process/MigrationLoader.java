package org.adempiere.process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutor;
import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.xml.XMLLoader;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.Adempiere;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;

public class MigrationLoader
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	public void load(Properties ctx)
	{
		scanClasspath();
		scanAdempiereHome();
	}

	private void scanClasspath()
	{
		final String resourceName = "/org/adempiere/migration/Migration.xml";
		final InputStream inputStream = getClass().getResourceAsStream(resourceName);
		if (inputStream == null)
		{
			logger.config("Resource name not found: " + resourceName + ". Skip migration.");
			return;
		}

		final XMLLoader loader = new XMLLoader(inputStream);
		load(loader);

	}

	private void scanAdempiereHome()
	{
		final File home = new File(Adempiere.getAdempiereHome() + File.separator + "migration");
		if (!home.exists() && !home.isDirectory())
		{
			logger.log(Level.WARNING, "No migration directory found (" + home + ")");
			return;
		}

		logger.log(Level.CONFIG, "Processing migration files in directory: " + home.getAbsolutePath());

		final File[] migrationFiles = home.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(final File dir, final String name)
			{
				return name.endsWith(".xml");
			}
		});

		for (final File migrationFile : migrationFiles)
		{
			final XMLLoader loader = new XMLLoader(migrationFile.getAbsolutePath());
			load(loader);
		}
	}

	private void load(final XMLLoader loader)
	{
		loader.load(Trx.TRXNAME_None);

		for (Object o : loader.getObjects())
		{
			if (o instanceof I_AD_Migration)
			{
				final I_AD_Migration migration = (I_AD_Migration)o;
				execute(migration);
			}
			else
			{
				logger.warning("Unhandled type " + o + " [SKIP]");
			}
		}
	}

	private void execute(I_AD_Migration migration)
	{
		final Properties ctx = InterfaceWrapperHelper.getCtx(migration);
		final int migrationId = migration.getAD_Migration_ID();

		final IMigrationExecutorProvider executorProvider = Services.get(IMigrationExecutorProvider.class);
		final IMigrationExecutorContext migrationCtx = executorProvider.createInitialContext(ctx);
		migrationCtx.setFailOnFirstError(true);

		final IMigrationExecutor executor = executorProvider.newMigrationExecutor(migrationCtx, migrationId);
		executor.setCommitLevel(IMigrationExecutor.CommitLevel.Batch);
		executor.execute(IMigrationExecutor.Action.Apply);
	}
}

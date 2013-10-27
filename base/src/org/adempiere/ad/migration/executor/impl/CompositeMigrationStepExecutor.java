/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Teo Sarca , t.sarca@metas.ro , METAS GROUP						  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.executor.impl;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;
import org.adempiere.ad.migration.executor.IMigrationStepExecutor;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;

public class CompositeMigrationStepExecutor extends AbstractMigrationStepExecutor
{
	public CompositeMigrationStepExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step)
	{
		super(migrationCtx, step);
	}

	@Override
	public ExecutionResult apply(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		// Already applied
		if (X_AD_MigrationStep.STATUSCODE_Applied.equals(step.getStatusCode()))
		{
			log("Already applied", "SKIP", false);
			return ExecutionResult.Ignored;
		}

		final IMigrationStepExecutor executor = createDelegatedMigrationStepExecutor(step);
		return executor.apply(trxName);
	}

	@Override
	public ExecutionResult rollback(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		// Not Applied, no rollback is needed
		if (!X_AD_MigrationStep.STATUSCODE_Applied.equals(step.getStatusCode()))
		{
			log("Not applied. Nothing to rollback", "SKIP", false);
			return ExecutionResult.Ignored;
		}

		final IMigrationStepExecutor executor = createDelegatedMigrationStepExecutor(step);
		return executor.rollback(trxName);
	}

	private IMigrationStepExecutor createDelegatedMigrationStepExecutor(final I_AD_MigrationStep step)
	{
		final IMigrationExecutorContext migrationCtx = getMigrationExecutorContext();
		final IMigrationExecutorProvider factory = migrationCtx.getMigrationExecutorProvider();
		final IMigrationStepExecutor executor = factory.newMigrationStepExecutor(migrationCtx, step);
		return executor;
	}

}

package org.adempiere.ad.migration.process;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.service.IMigrationBL;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.process.SvrProcess;

/**
 * Sort migration steps by Created
 * 
 * @author Teo Sarca
 * 
 */
public class MigrationSortSteps extends SvrProcess
{
	private I_AD_Migration migration;

	@Override
	protected void prepare()
	{
		if (I_AD_Migration.Table_Name.equals(getTableName()) && getRecord_ID() > 0)
		{
			this.migration = InterfaceWrapperHelper.create(getCtx(), getRecord_ID(), I_AD_Migration.class, get_TrxName());
		}
		if (I_AD_MigrationStep.Table_Name.equals(getTableName()) && getRecord_ID() > 0)
		{
			final I_AD_MigrationStep step = InterfaceWrapperHelper.create(getCtx(), getRecord_ID(), I_AD_MigrationStep.class, get_TrxName());
			migration = step.getAD_Migration();
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		if (migration == null || migration.getAD_Migration_ID() <= 0)
		{
			throw new AdempiereException("@NotFound@ @AD_Migration_ID@");
		}

		Services.get(IMigrationBL.class).sortStepsByCreated(migration);

		return "OK";
	}

}

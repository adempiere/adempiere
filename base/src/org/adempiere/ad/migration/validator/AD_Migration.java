package org.adempiere.ad.migration.validator;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.service.IMigrationBL;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.adempiere.util.Services;
import org.compiere.model.ModelValidator;

@Validator(I_AD_Migration.class)
public class AD_Migration
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void setSeqNo(I_AD_Migration migration)
	{
		if (migration.getSeqNo() == 0)
		{
			Services.get(IMigrationBL.class).setSeqNo(migration);
		}
	}
}

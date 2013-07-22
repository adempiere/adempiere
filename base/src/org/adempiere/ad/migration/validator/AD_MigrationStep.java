package org.adempiere.ad.migration.validator;

import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.service.IMigrationBL;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.ad.modelvalidator.annotations.Validator;
import org.adempiere.util.Services;
import org.compiere.model.ModelValidator;

@Validator(I_AD_MigrationStep.class)
public class AD_MigrationStep
{
	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void setSeqNo(final I_AD_MigrationStep step)
	{
		if (step.getSeqNo() == 0)
		{
			Services.get(IMigrationBL.class).setSeqNo(step);
		}
	}

}

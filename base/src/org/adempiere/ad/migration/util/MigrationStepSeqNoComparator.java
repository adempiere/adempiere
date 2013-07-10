package org.adempiere.ad.migration.util;

import java.util.Comparator;

import org.adempiere.ad.migration.model.I_AD_MigrationStep;

/**
 * Comparator which compares steps by SeqNo.
 * 
 * @author Teo Sarca
 * 
 */
public final class MigrationStepSeqNoComparator implements Comparator<I_AD_MigrationStep>
{
	public static final MigrationStepSeqNoComparator instance = new MigrationStepSeqNoComparator();

	private MigrationStepSeqNoComparator()
	{
		super();
	}

	@Override
	public int compare(final I_AD_MigrationStep step1, final I_AD_MigrationStep step2)
	{
		if (step1 == step2)
		{
			return 0;
		}
		if (step1 == null)
		{
			return -1;
		}
		if (step2 == null)
		{
			return +1;
		}
		return step1.getSeqNo() - step2.getSeqNo();
	}

}

package org.adempiere.ad.migration.validator;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.service.IMigrationBL;
import org.adempiere.model.POWrapper;
import org.adempiere.util.Services;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;

public class MigrationValidator implements ModelValidator
{

	private int clientId = -1;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client)
	{
		if (client != null)
		{
			clientId = client.getAD_Client_ID();
		}
	}

	@Override
	public int getAD_Client_ID()
	{
		return clientId;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception
	{
		if (I_AD_Migration.Table_Name.equals(po.get_TableName()))
		{
			modelChange(POWrapper.create(po, I_AD_Migration.class), type);
		}
		else if (I_AD_MigrationStep.Table_Name.equals(po.get_TableName()))
		{
			modelChange(POWrapper.create(po, I_AD_MigrationStep.class), type);
		}
		return null;
	}

	private void modelChange(I_AD_Migration migration, int type)
	{
		if ((type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) && migration.getSeqNo() == 0)
		{
			Services.get(IMigrationBL.class).setSeqNo(migration);
		}
	}

	private void modelChange(I_AD_MigrationStep step, int type)
	{
		if ((type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) && step.getSeqNo() == 0)
		{
			Services.get(IMigrationBL.class).setSeqNo(step);
		}
	}

	@Override
	public String docValidate(PO po, int timing)
	{
		return null;
	}

}

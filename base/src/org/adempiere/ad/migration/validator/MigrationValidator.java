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
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.validator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;

public class MigrationValidator implements ModelValidator
{
	private int adClientId = -1;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client)
	{
		if (client != null)
		{
			adClientId = client.getAD_Client_ID();
		}

		engine.addModelValidator(new AD_Migration(), client);
		engine.addModelValidator(new AD_MigrationStep(), client);
	}

	@Override
	public int getAD_Client_ID()
	{
		return adClientId;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception
	{
		return null;
	}

	@Override
	public String docValidate(PO po, int timing)
	{
		return null;
	}

}

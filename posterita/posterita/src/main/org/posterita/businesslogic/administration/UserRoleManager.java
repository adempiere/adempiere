/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 27-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic.administration;

import java.util.Properties;

import org.compiere.model.MUserRoles;
import org.compiere.util.Trx;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.OperationException;
import org.posterita.util.PoManager;


public class UserRoleManager
{

	
	public static MUserRoles createUserRoles(Properties ctx,int roleId,int userId) throws OperationException
	{
		Trx trx = Trx.get(Trx.createTrxName(TrxPrefix.getPrefix()), true);
		trx.start();
		MUserRoles userRoles;
		try
		{
			userRoles = new MUserRoles(ctx,userId,roleId,trx.getTrxName());
			PoManager.save(userRoles);
			trx.commit();
			trx.close();
		}
		catch(OperationException e)
		{
			trx.rollback();
			trx.close();
			throw e;
		}
		
		return userRoles;
	}
}

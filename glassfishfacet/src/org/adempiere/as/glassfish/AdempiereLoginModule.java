/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.adempiere.as.glassfish;

import javax.security.auth.login.LoginException;

import com.sun.appserv.security.AppservPasswordLoginModule;

/**
 * JAAS login module for adempiere
 * @author Low Heng Sin
 */
public class AdempiereLoginModule extends AppservPasswordLoginModule {
	@Override
	protected void authenticateUser() throws LoginException {
		// Get the current realm and check whether it is instance of your realm
        if (!(_currentRealm instanceof AdempiereRealm)) {
            throw new LoginException("Realm must be of type AdempiereRealm. CurrentRealm=" + _currentRealm.getClass());
        }
        
		AdempiereRealm realm = (AdempiereRealm) _currentRealm;
		
		String[] groups = realm.authenticate(_username, _password);
		
		commitUserAuthentication(groups);
	}
}

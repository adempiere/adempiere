/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author ashley
 */
package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrg;
import org.compiere.util.Env;

import org.posterita.exceptions.OperationException;


public class ContextManager 
{
    
    public static Properties getBPartnerCtx(Properties ctx, int bPartnerId) throws OperationException
    {
        MBPartner partner = new MBPartner(ctx, bPartnerId, null);
        
        MOrg org = new MOrg(ctx, partner.getAD_Org_ID(), null);
        
        Properties bpCtx = (Properties) ctx.clone();
        
        Env.setContext(bpCtx, "#AD_Org_ID",  org.get_ID());
        
        return bpCtx;
        
    }
}

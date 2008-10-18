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
 * Created on 21-Jun-2005 by alok
 *
 */
package org.posterita.model;


import java.util.Properties;

import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;


public class UDIMOrg extends UDIPO
{

	public UDIMOrg(MOrg org)
	{
		super(org);
	}

	public MOrg getMOrg()
	{
		return (MOrg) getPO();
	}
	
	public String getName()
	{
		return getMOrg().getName();
	}
	
	public int getLinkedC_BPartner_ID()
	{
		return getMOrg().getLinkedC_BPartner_ID(null);
	}
	
	public Properties getCtx()
	{
	    return getMOrg().getCtx();
	}
	
	public MOrgInfo getOrgInfo()
	{
	    return 	getMOrg().getInfo();
	}
	
	
	
}

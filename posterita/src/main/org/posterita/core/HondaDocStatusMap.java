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
 * Created on Jun 17, 2005 by din
 */

package org.posterita.core;

import java.util.LinkedHashMap;

import org.compiere.process.DocumentEngine;

public class HondaDocStatusMap
{
    static LinkedHashMap<Object, String> hondaDocStatusMap;

	public static LinkedHashMap getHondaDocStatusMap()
	{
		return hondaDocStatusMap;
	}

	
    static
    {
    	hondaDocStatusMap = new LinkedHashMap<Object, String>();
    	hondaDocStatusMap.put(DocumentEngine.STATUS_Drafted,"Drafted");
    	hondaDocStatusMap.put(DocumentEngine.STATUS_InProgress,"In Progress");
    	hondaDocStatusMap.put(DocumentEngine.STATUS_Completed,"Completed");
    	hondaDocStatusMap.put(DocumentEngine.STATUS_Voided,"Voided");
    	hondaDocStatusMap.put(DocumentEngine.STATUS_Approved,"Approved");
    }
}

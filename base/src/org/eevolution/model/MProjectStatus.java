/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */


package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;


/**
 * Project Status
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class MProjectStatus extends X_C_ProjectStatus{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Cache							*/
	static private CCache<Integer,MProjectStatus> s_cache
		= new CCache<Integer,MProjectStatus> ("C_ProjectStatus", 10);
	
	
    public MProjectStatus(Properties ctx, int C_ProjectStatus_ID, String trxName) {
        super(ctx, C_ProjectStatus_ID, trxName);
    }

    public MProjectStatus(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
	/**
	 * 	Get Project Status (cached)
	 *  FR [ 2202 ]
	 *	@param ctx context
	 *	@param C_ProjectStatus_ID id
	 *	@return Project Status or null
	 */
	public static MProjectStatus get (Properties ctx, int C_ProjectStatus_ID)
	{
		if (C_ProjectStatus_ID == 0)
			return null;
		Integer key = Integer.valueOf(C_ProjectStatus_ID);
		MProjectStatus retValue = (MProjectStatus)s_cache.get(key);
		if (retValue == null)
		{
			retValue = new MProjectStatus (ctx, C_ProjectStatus_ID, null);
			s_cache.put(key, retValue);
		}
		return retValue;
	}	//	get
}

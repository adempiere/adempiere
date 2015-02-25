/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it           *
 * under the terms version 2 of the GNU General Public License as published          *
 * by the Free Software Foundation. This program is distributed in the hope          *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied        *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  *
 * See the GNU General Public License for more details.                              *
 * You should have received a copy of the GNU General Public License along           *
 * with this program; if not, write to the Free Software Foundation, Inc.,           *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                            *
 * For the text or an alternative of this public license, you may reach us           *
 * Copyright (C) 2012-2013 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Carlos Parada www.erpcya.com                    					 *
 *************************************************************************************/
package org.spinsuite.service;

import org.codehaus.xfire.fault.XFireFault;
import com.erpcya.ILCallDocument;
import com.erpcya.ILResponseDocument;

/**
* @author Carlos Parada, cparada@erpcya.com, ERPCyA http://www.erpcya.com
*  	<li>FR [ 9223372036854775807 ] Add Default Web-Services for Initial Load Mobil
*  	@see https://adempiere.atlassian.net/browse/ADEMPIERE-403
**/

public interface SpinSuiteService {
	
	public ILResponseDocument InitialLoad(ILCallDocument req) throws XFireFault;
	public String getVersion();
}

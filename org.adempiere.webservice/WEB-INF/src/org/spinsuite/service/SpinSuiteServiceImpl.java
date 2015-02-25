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
 * Contributor(s): Carlos Parada www.erpcya.com                    *
 *************************************************************************************/
package org.spinsuite.service;


import java.sql.SQLException;
import javax.xml.namespace.QName;
import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import com.erpcya.ILCallDocument;
import com.erpcya.ILResponseDocument;
import com.erpcya.Response;

/**
* @author Carlos Parada, cparada@erpcya.com, ERPCyA http://www.erpcya.com
*  	<li>FR [ 9223372036854775807 ] Add Default Web-Services for Initial Load Mobil
*  	@see https://adempiere.atlassian.net/browse/ADEMPIERE-403
**/
public class SpinSuiteServiceImpl extends MSpinSuiteServiceImpl implements SpinSuiteService {

	/**
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 9:28:03 PM
	 * Get Response Initial Load 
	 */
	@Override
	public ILResponseDocument InitialLoad(ILCallDocument req)
			throws XFireFault {
		// TODO Auto-generated method stub
		
		ILResponseDocument resp;
		
		try {
			com.erpcya.Login il = req.getILCall();
			if (validateUser(req,il.getLang()))
				resp = initialLoad(il.getServiceDefinition(),il.getServiceMethod(),il.getServiceType(),il.getPage(),il.getWSNumber());
			else
			{
				resp = ILResponseDocument.Factory.newInstance();
				Response ds = resp.addNewILResponse();
				ds.setError(Msg.translate(Env.getCtx(), "UserPwdError"));
				throw new XFireFault(Msg.translate(Env.getCtx(), "UserPwdError"),new Throwable(Msg.translate(Env.getCtx(), "UserPwdError")), new QName("authenticate"));			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			resp = ILResponseDocument.Factory.newInstance();
			Response rs = resp.addNewILResponse();
			rs.setError(e.getMessage());
			throw new XFireFault(e.getClass().toString() + " " + e.getMessage() , e.getCause(), new QName("initLoad"));
		}
		return resp;
	}
	
	/**
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 9:28:03 PM
	 * Get Version Of AppDroid Services 
	 */
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "2.0";
	}
}

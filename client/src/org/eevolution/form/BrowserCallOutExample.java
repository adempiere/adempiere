/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Carlos Parada www.erpconsultoresyasociados.com             *
 *****************************************************************************/
package org.eevolution.form;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.GridField;
import org.compiere.util.CLogger;


/**
 * @author <a href="mailto:carlosaparada@gmail.com">Carlos Parada</a>
 *
 */
public class BrowserCallOutExample extends BrowserCalloutEngine {

	
	public String methodExample(Properties ctx,  int WindowNo,BrowserRows mRow, GridField mField, Object value, Object oldValue,int current_Row, int current_Column) 
	{
		System.out.println("Hi! this is a example of implementation callouts");
		
		System.out.println("This is a Value for :"+value);
		System.out.println("This is a Old Value :"+oldValue);
		System.out.println("This is a Value for GridField:"+mField.getValue());
		System.out.println("This is a Old Value for GridField:"+mField.getOldValue());
		mRow.setValueofColumn("SO_CreditUsed", new BigDecimal(9999.33),current_Row);
		return "";
	}
	
	static CLogger log = CLogger.getCLogger(BrowserCallOutExample.class);
}//BrowserCallOutExample

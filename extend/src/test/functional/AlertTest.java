/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.d3-soft.com)                                  *
***********************************************************************/
package test.functional;

//import org.compiere.model.I_AD_Alert;
//import org.compiere.model.I_AD_AlertProcessor;
import org.compiere.model.MAlert;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Alert;
import org.compiere.model.X_AD_AlertProcessor;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import test.AdempiereTestCase;


/**
 *	Example Test which shows usage of new methods.
 *	
 *  @author Trifon Trifonov
 *  @version $Id$
 */
public class AlertTest extends AdempiereTestCase
{

	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (AlertTest.class);
	
	/**
	 * 	Run
	 */
	public void testAlertCreation() throws Exception 
	{
		Trx trx = Trx.get(Trx.createTrxName("Test"), true);
		trx.start();
		log.info("trx = " + trx.toString());
		boolean resultSave = false;
		
		//----- Old way:
		MAlert alertOldWay = new MAlert(Env.getCtx(), 100, trx.getTrxName());
		log.info(alertOldWay.toString());
		
		X_AD_AlertProcessor alertProcessorOldWay = new X_AD_AlertProcessor(Env.getCtx(), alertOldWay.getAD_AlertProcessor_ID(), trx.getTrxName());
		log.info("alertProcessorOldWay.getAD_AlertProcessor_ID = " + alertProcessorOldWay.getAD_AlertProcessor_ID());
		
		alertOldWay.setDescription("Trifon test");
		resultSave = alertOldWay.save();
		log.info("resultSave = " + resultSave);
		
		System.out.println("New value of Description = " + alertOldWay.getDescription());
		
		//----- New way:
/*		I_AD_Alert alert = new MAlert(Env.getCtx(), 100, trx.getTrxName());
		log.info(alert.toString());
				
		I_AD_AlertProcessor alertProcessor = alert.getI_AD_AlertProcessor();
		log.info("I_AD_AlertProcessor.getAD_AlertProcessor_ID = " + alertProcessor.getAD_AlertProcessor_ID());

		log.info("alert.getAD_AlertProcessor_ID = " + alert.getAD_AlertProcessor_ID());
		
		alert.setDescription("Trifon test");
		//--- Save; TODO - Must be refactored. PO.save must be static method!!!
		//resultSave = ((X_AD_Alert) alert).save();
		resultSave = PO.save(alert);
		log.info("resultSave = " + resultSave);
				
		System.out.println("New value of Description = " + alert.getDescription());
*/
		trx.commit();
		trx.close();
	}
	
}

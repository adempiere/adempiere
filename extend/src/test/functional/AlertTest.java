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

import java.math.BigDecimal;

import org.compiere.model.MAlert;
import org.compiere.model.MAlertProcessor;
import org.compiere.model.MAlertProcessorLog;
import org.compiere.model.MProductPricing;
import org.compiere.model.X_AD_AlertProcessor;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

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
		log.info("trx = " + getTrxName());
		boolean resultSave = false;
		
		//----- Old way:
		MAlert alertOldWay = new MAlert(Env.getCtx(), 100, getTrxName());
		log.info(alertOldWay.toString());
		
		X_AD_AlertProcessor alertProcessorOldWay = new X_AD_AlertProcessor(Env.getCtx(), alertOldWay.getAD_AlertProcessor_ID(), getTrxName());
		log.info("alertProcessorOldWay.getAD_AlertProcessor_ID = " + alertProcessorOldWay.getAD_AlertProcessor_ID());
		
		alertOldWay.setDescription("Trifon test");
		//--- Save; 
		resultSave = alertOldWay.save();
		log.info("resultSave = " + resultSave);
		
		System.out.println("New value of Description = " + alertOldWay.getDescription());
		
		//----- New way:
/*		I_AD_Alert alert = new MAlert(Env.getCtx(), 100, trx.getTrxName());
		log.info(alert.toString());
				
		I_AD_AlertProcessor alertProcessor = alert.getI_AD_AlertProcessor();
		log.info("I_AD_AlertProcessor.getAD_AlertProcessor_ID = " + alertProcessor.getAD_AlertProcessor_ID());

		log.info("alert.getAD_AlertProcessor_ID = " + alert.getAD_AlertProcessor_ID());
		
		alert.setDescription("Trifon Description modified!");
		//--- Save; PO.save(PO) must be static method!!! Two way of usage: PO.save(Object) or PO.save(PO)
		//resultSave = PO.save((PO)alert); 
		resultSave = PO.save(alert);  // Overloaded save method; For simple usage!
		log.info("resultSave = " + resultSave);
				
		System.out.println("New value of Description = " + alert.getDescription());
*/
		
		commit();
	}
	public void testAlertProcessor() {
		MAlertProcessor alertpro = new MAlertProcessor(getCtx(), 100,null);
		alertpro.setAD_AlertProcessor_ID(100);
		MAlertProcessorLog[] test = (MAlertProcessorLog[]) alertpro.getLogs();
		assertTrue("Alert Processor has logs", test.length > 0);
		MAlert[] alerts = (MAlert[])alertpro.getAlerts(true);
		assertTrue("There are alerts", alerts.length> 0);
		assertTrue ("There are active recs", MAlertProcessor.getActive(getCtx()).length > 0);
	}
	
}

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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.queue.notification.telegram.setup;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.notification.telegram.support.Telegram;
import org.spin.util.ISetupDefinition;

/**
 * Add here your setup class
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class Deploy implements ISetupDefinition {
	
	@Override
	public String doIt(Properties context, String transactionName) {
		//	Add Model Validator
		createRegistration(context, transactionName);
		createSystemConfigHost(context, transactionName);
		createSystemConfigCode(context, transactionName);
		//	financial management
		return "@AD_SetupDefinition_ID@ @Ok@";
	}
	
	/**
	 * Create Model Vaidator
	 * @param context
	 * @param transactionName
	 * @return
	 */
	private void createRegistration(Properties context, String transactionName) {
		MADAppRegistration registration = new Query(context, MADAppRegistration.Table_Name,
				"EXISTS(SELECT 1 FROM AD_AppSupport s "
				+ "WHERE s.AD_AppSupport_ID = AD_AppRegistration.AD_AppSupport_ID "
				+ "AND s.ApplicationType = ?"
				+ "AND s.IsActive = 'Y'"
				+ "AND s.Classname = ?)", transactionName)
				.setParameters(DefaultNotifier.DefaultNotificationType_Telegram, Telegram.class.getName())
				.setClient_ID()
				.<MADAppRegistration>first();
		//	Validate
		if(registration != null
				&& registration.getAD_AppRegistration_ID() > 0) {
			return;
		}
		//	Get
		MADAppSupport applicationSupport = new Query(context, MADAppSupport.Table_Name,
				"Classname = ?", transactionName)
				.setParameters(Telegram.class.getName())
				.first();
		//	
		if(applicationSupport == null) {
			throw new AdempiereException("@AD_AppSupport_ID@ @NotFound@");
		}
		registration = new MADAppRegistration(context, 0, transactionName);
		registration.setApplicationType(applicationSupport.getApplicationType());
		registration.setAD_AppSupport_ID(applicationSupport.getAD_AppSupport_ID());
		registration.setAD_Org_ID(0);
		registration.setValue("Custom-Telegram-Sender");
		registration.setName("Custom Telegram Sender");
		registration.setVersionNo("1.0");
		registration.setHost("localhost");
		registration.setPort(0);
		registration.setTimeout(0);
		registration.saveEx();
		registration.setUUID("(*AutomaticSetup*)");
		registration.setIsDirectLoad(true);
		registration.saveEx();
	}
	
	/**
	 * Create system configurator host property
	 * @param context
	 * @param transactionName
	 */
	private void createSystemConfigHost(Properties context, String transactionName) {
		MSysConfig systemConfig = new MSysConfig(context, 0, transactionName);
		systemConfig.setName("CLIENT_HOST");
		systemConfig.setDescription("Host for current client or tenant");
		systemConfig.setEntityType("ECA34");
		systemConfig.setConfigurationLevel(MSysConfig.CONFIGURATIONLEVEL_Client);
		systemConfig.setAD_Org_ID(0);
		systemConfig.setValue("https://localhost");
		systemConfig.saveEx();
		systemConfig.setUUID("(*AutomaticSetup*)");
		systemConfig.setIsDirectLoad(true);
		systemConfig.saveEx();
	}
	
	/**
	 * Create system configurator host property
	 * @param context
	 * @param transactionName
	 */
	private void createSystemConfigCode(Properties context, String transactionName) {
		MSysConfig systemConfig = new MSysConfig(context, 0, transactionName);
		systemConfig.setName("CLIENT_CODE");
		systemConfig.setDescription("Client Code or identification");
		systemConfig.setEntityType("ECA34");
		systemConfig.setConfigurationLevel(MSysConfig.CONFIGURATIONLEVEL_Client);
		systemConfig.setAD_Org_ID(0);
		systemConfig.setValue("localhost");
		systemConfig.saveEx();
		systemConfig.setUUID("(*AutomaticSetup*)");
		systemConfig.setIsDirectLoad(true);
		systemConfig.saveEx();
	}
}

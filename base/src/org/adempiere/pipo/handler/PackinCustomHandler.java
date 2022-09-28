/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.adempiere.pipo.handler;

import java.util.HashMap;
import java.util.Map;

import org.adempiere.core.domains.models.I_AD_PrintFormat;
import org.adempiere.core.domains.models.I_AD_Workflow;
import org.adempiere.pipo.ElementHandler;
import org.compiere.util.Util;

/**
 * Custom Handler singleton
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class PackinCustomHandler {
	/**	Custom Handler instance	*/
	private static PackinCustomHandler instance = null;
	/**	Handlers	*/
	private Map<String, ElementHandler> handlers = null;
	
	/**
	 * Default constructor
	 */
	private PackinCustomHandler() {
		createDefaultHandlers();
	}
	
	/**
	 * Create default handlers
	 */
	private void createDefaultHandlers() {
		DataElementHandler dataHandler = new DataElementHandler();
    	handlers = new HashMap<String, ElementHandler>();
    	handlers.put("adempieredata", dataHandler);
    	handlers.put(GenericPOHandler.TAG_Name + "_" + I_AD_Workflow.Table_Name, new WorkflowElementHandler());
    	handlers.put("data", dataHandler);
    	handlers.put("dtable", dataHandler);
    	handlers.put("drow", dataHandler);
    	handlers.put("dcolumn", dataHandler);
    	handlers.put(GenericPOHandler.Column_TAG_Name, new TableElementHandler());
    	handlers.put(GenericPOHandler.TAG_Name, new GenericPOHandler());
    	handlers.put("codesnipit", new CodeSnipitElementHandler());
    	handlers.put("distfile", new DistFileElementHandler());
    	handlers.put("SQLStatement", new SQLStatementElementHandler());
    	handlers.put(EntityTypeElementHandler.TAG_Name, new EntityTypeElementHandler());
    	handlers.put(GenericPOHandler.TAG_Name + "_" + I_AD_PrintFormat.Table_Name, new PrintFormatElementHandler());
	}
	
	/**
	 * Get instance or create a new instance for it
	 * @return
	 */
	public static PackinCustomHandler getInstance() {
		if(instance == null) {
			instance = new PackinCustomHandler();
		}
		//	Default
		return instance;
	}
	
	/**
	 * Add or overwrite hadler for table
	 * @param tableName
	 * @param tableHandler
	 */
	public void addTableHandler(String tableName, ElementHandler tableHandler) {
		if(Util.isEmpty(tableName)) {
			return;
		}
		//	Put it
		handlers.put(GenericPOHandler.TAG_Name + "_" + tableName.trim(), tableHandler);
	}
	
	/**
	 * Add a custom handler
	 * @param keyHandler
	 * @param tableHandler
	 */
	public void addCustomHandler(String keyHandler, ElementHandler tableHandler) {
		if(Util.isEmpty(keyHandler)) {
			return;
		}
		//	Put it
		handlers.put(keyHandler.trim(), tableHandler);
	}
	
	/**
	 * Get Handler from key
	 * @param key
	 * @return
	 */
	public ElementHandler getHandler(String key) {
		if(Util.isEmpty(key)) {
			return null;
		}
		return handlers.get(key);
	}
	
	/**
	 * Get handler from table name
	 * @param tableName
	 * @return
	 */
	public ElementHandler getHandlerFromTableName(String tableName) {
		if(Util.isEmpty(tableName)) {
			return null;
		}
		return getHandler(GenericPOHandler.TAG_Name + "_" + tableName.trim());
	}
}

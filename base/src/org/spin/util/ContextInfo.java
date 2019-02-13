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
package org.spin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.compiere.model.GridTab;
import org.compiere.model.MMessage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.spin.model.MADContextInfo;

/**
 * Class for handle status bar
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1784 ] Add dynamic status bar info
 * @see https://github.com/adempiere/adempiere/issues/1784
 */
public class ContextInfo {
	
	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(ContextInfo.class);
	
	/**
	 * Get Context Info for Window
	 * @param gridTab
	 * @return
	 */
	public static String getInfoForWindow(GridTab gridTab) {
		//	From Tab
		MADContextInfo contextInfo = MADContextInfo.getFromTabId(Env.getCtx(), gridTab.getAD_Tab_ID());
		//	From Window
		if(contextInfo == null) {
			contextInfo = MADContextInfo.getFromWindowId(Env.getCtx(), gridTab.getAD_Window_ID());
		}
		//	From Table
		if(contextInfo == null) {
			contextInfo = MADContextInfo.getFromTableId(Env.getCtx(), gridTab.getAD_Table_ID());
		}
		//	Return
		return getContextInfoMessage(contextInfo, gridTab.getWindowNo());
	}
	
	/**
	 * Get Info for fields
	 * @param gridTab
	 * @return HashMap with Name of field and Context Info
	 */
	public static Map<String, String> getInfoForFiels(GridTab gridTab) {
		Map<String, MADContextInfo> contextInfoHash = MADContextInfo.getFromTabIdForField(Env.getCtx(), gridTab.getAD_Tab_ID());
		Map<String, String> values = null;
		if(contextInfoHash != null
				&& contextInfoHash.size() > 0) {
			values = new HashMap<String, String>();
			for(Entry<String, MADContextInfo> entry : contextInfoHash.entrySet()) {
				values.put(entry.getKey(), getContextInfoMessage(entry.getValue(), gridTab.getWindowNo()));
			}
		}
		//	
		return values;
	}
	
	/**
	 * Get Context Info Message from Context Info model
	 * @param contextInfo
	 * @param windowNo
	 * @return
	 */
	private static String getContextInfoMessage(MADContextInfo contextInfo, int windowNo) {
		//	Validate Status Bar
		if(contextInfo == null) {
			return " ";
		}
		//	
		try {
			MMessage message = MMessage.get(Env.getCtx(), contextInfo.getAD_Message_ID());
			if(message != null) {
				//	Parse
				Object[] arguments = contextInfo.getArguments(windowNo);
				if(arguments == null) {
					return null;
				}
				//	
				return Msg.getMsg(Env.getAD_Language(Env.getCtx()), message.getValue(), arguments);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, e.getLocalizedMessage());
		}
		//	
		return " ";
	}
}

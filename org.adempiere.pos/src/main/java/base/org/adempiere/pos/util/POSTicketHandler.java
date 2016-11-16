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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.pos.util;

import java.lang.reflect.Constructor;

import org.adempiere.pos.service.CPOS;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/672">
 * 		@see FR [ 672 ] Add abstract class for basic operation of POS</a>
 */
public abstract class POSTicketHandler {

	/**
	 * Default constructor
	 * @param pos
	 */
	public POSTicketHandler(CPOS pos) {
		this.pos = pos;
	}
	
	/**	POS Configuration	*/
	private CPOS pos;
	/**	Log					*/
	protected static CLogger log = CLogger.getCLogger (POSTicketHandler.class);
	/**	Cache for Instance	*/
	private static CCache<String, POSTicketHandler> s_cache = new CCache<String, POSTicketHandler>("POSTicketHandler", 40, 5);	//	5 minutes
	
	/**
	 * Get current POS
	 * @return
	 */
	public CPOS getPOS() {
		return pos;
	}
	
	/**
	 * Set POS, update current
	 * @param pos
	 */
	public void setPOS(CPOS pos) {
		this.pos = pos;
	}
	
	
	/**
	 * Get Instance for ticket
	 * @param pos
	 * @return
	 */
	public static POSTicketHandler getTicketHandler(CPOS pos) {
		if(pos == null) {
			log.severe("Not have POS");
			return null;
		}
		//	Get class from parent
		String className = pos.getTicketHandlerClassName();
		if(className == null
				|| className.trim().length() == 0) {
			log.fine("Get from GenericTicketHandlerClass");
			return new POSGenericTicketHandler(pos);
		}
		//	Handler
		POSTicketHandler ticketHandler = null;
		//	Get from cache
		ticketHandler = s_cache.get(className);
		//	update and return
		if(ticketHandler != null) {
			ticketHandler.setPOS(pos);
			return ticketHandler;
		}
		//	Reload
		try {
			Class<?> clazz = Class.forName(className);
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == POSTicketHandler.class) {
					break;
				}
				//	Get Supert Class
				superClazz = superClazz.getSuperclass();
			}
			//	When exists
			Constructor<?> constructor = null;
			constructor = clazz.getDeclaredConstructor(new Class[]{CPOS.class});
			ticketHandler = (POSTicketHandler) constructor.newInstance(new Object[] {pos});
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		//	Set Cache
		if(ticketHandler != null) {
			s_cache.put(className, ticketHandler);
		}
		//	Default Return
		return ticketHandler;
	}
	
	/**
	 * Print Ticket
	 */
	public abstract void printTicket();
	
	/**
	 * Open cash drawer
	 */
	public abstract void openDrawer();
	
	/**
	 * Show Message
	 * @param message
	 */
	public abstract void showMessage(String message);

}

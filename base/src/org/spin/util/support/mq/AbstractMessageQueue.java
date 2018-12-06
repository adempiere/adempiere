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
package org.spin.util.support.mq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.spin.util.support.AppRegistrationEvent;
import org.spin.util.support.AppRegistrationEventListener;
import org.spin.util.support.IAppSupport;


/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public abstract class AbstractMessageQueue implements IAppSupport {
	
	/**	Event Listener	*/
	private List<AppRegistrationEventListener> listeners = new ArrayList<AppRegistrationEventListener>();
	
	/**
	 * Publish a message
	 * @param channel
	 * @param payload
	 */
	public abstract void publish(String channel, IMessageQueue payload) throws Exception;
	
	/**
	 * Subscribe to channel
	 * @param channel
	 */
	public abstract void subscribe(String channel) throws Exception;
	
	/**
	 * Connect
	 * @throws Exception
	 */
	public abstract void connect() throws Exception;
	
	/**
	 * Disconnect
	 * @throws Exception
	 */
	public abstract void disconnect() throws Exception;
	
	/**
	 * Add Listener
	 * param listener
	 * @return void
	 */
	public synchronized void addDeviceListener(AppRegistrationEventListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove Listener
	 * @param listener
	 * @return void
	 */
	public synchronized void removeDeviceListener(AppRegistrationEventListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Fire device event
	 * @param eventType
	 * @return void
	 */
	protected synchronized void fireDeviceEvent(int eventType) {
		AppRegistrationEvent ev = new AppRegistrationEvent(this, eventType);
		//	Get Iterator
		Iterator<AppRegistrationEventListener> iterator = listeners.iterator();
        //	Iterate
		while(iterator.hasNext()) {
            ((AppRegistrationEventListener) iterator.next()).registerEvent(ev);
        }
	}

}

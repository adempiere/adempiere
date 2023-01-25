/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Session;
import org.compiere.model.MSession;
import org.compiere.model.Query;

/** 
 * 	Generated Process for (Close Expired Sessions)
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com  
 *  @version Release 3.9.3
 */
public class CloseExpiredSessions extends CloseExpiredSessionsAbstract {
	
	/**	Counter for tables	*/
	private AtomicInteger counter = new AtomicInteger();
	
	@Override
	protected String doIt() throws Exception {
		final long tolerance = System.currentTimeMillis() - getTimeout();
		getActiveSessionList()
			.stream()
			.filter(session -> session.getUpdated().getTime() < tolerance)
			.forEach(session -> {
				try {
					session.logout();
					counter.getAndIncrement();
				} catch (Exception e) {
					addLog(e.getMessage());
				}
		});
		return "@Closed@: " + counter.get();
	}
	
	/**
	 * Get Temporary Tables List
	 * @return
	 */
	private List<MSession> getActiveSessionList() {
		return new Query(getCtx(), I_AD_Session.Table_Name, I_AD_Session.COLUMNNAME_Processed + " = 'N'", get_TrxName())
				.setOnlyActiveRecords(true)
				.list();
	}
}
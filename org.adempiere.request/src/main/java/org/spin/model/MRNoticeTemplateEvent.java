/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public               *
 * License as published                                                       *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * 	Notice by Event
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *			<li> FR Mail Template for distint events
 */
public class MRNoticeTemplateEvent extends X_R_NoticeTemplateEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -529762729430874769L;

	public MRNoticeTemplateEvent(Properties ctx, int noticeTemplateEventId, String trxName) {
		super(ctx, noticeTemplateEventId, trxName);
	}

	public MRNoticeTemplateEvent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}

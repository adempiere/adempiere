/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.exceptions;

import java.sql.Timestamp;


/**
 * Period Closed Exception.
 * This exception is throwed by
 * {@link org.compiere.model.MPeriod#testPeriodOpen(java.util.Properties, Timestamp, int)} and
 * {@link org.compiere.model.MPeriod#testPeriodOpen(java.util.Properties, Timestamp, String)} methods.
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class PeriodClosedException extends AdempiereException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PeriodClosedException(Timestamp dateAcct, String docBaseType) {
		super("@PeriodClosed@ @Date@="+dateAcct+", @DocBaseType@="+docBaseType);
	}
}

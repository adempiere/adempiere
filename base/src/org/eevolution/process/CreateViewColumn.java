/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.process;

import java.util.logging.Level;

import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * Create Column View
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 */
public class CreateViewColumn extends SvrProcess {
	/** Record ID */
	protected int p_Record_ID = 0;

	/**
	 * Get Parameters
	 */
	protected void prepare() {

		p_Record_ID = getRecord_ID();
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para : parameters) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * Process - Generate Export Format
	 * 
	 * @return info
	 */
	@SuppressWarnings("unchecked")
	protected String doIt() throws Exception {
		MViewDefinition join = new MViewDefinition(getCtx(), p_Record_ID,
				get_TrxName());

		for (MViewColumn vcol : join.getADViewColunms()) {
			vcol.deleteEx(true);
		}

		for (MColumn attr : join.getEntityAttributes()) {
			MViewColumn column = new MViewColumn(attr);
			column.setAD_View_Definition_ID(join.getAD_View_Definition_ID());
			column.setColumnSQL(join.getTableAlias() + "."
					+ attr.getColumnName());
			column.setColumnName(join.getTableAlias().toUpperCase() + "_" + attr.getColumnName());
			column.setEntityType(join.getAD_View().getEntityType());
			column.setAD_View_ID(join.getAD_View_ID());
			column.saveEx();
			addLog(attr.getColumnName());
		}
		return "@Ok@";
	}
}

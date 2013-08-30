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

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * Create Browse Field
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 */
public class CreateBrowseField extends SvrProcess {
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
		MBrowse browse = new MBrowse(getCtx(), p_Record_ID, get_TrxName());
		MView view = browse.getAD_View();
		int seq = 10;
		for (MViewColumn column : view.getViewColumns()) {
			MBrowseField field = MBrowseField.get(browse, column);
			if (field != null)
				continue;

			field = new MBrowseField(browse, column);
			field.setAD_Browse_ID(browse.get_ID());
			field.setEntityType(browse.getEntityType());
			field.setIsDisplayed(false);
			field.setIsMandatory(false);
			field.setSeqNo(seq);
			field.saveEx();
			seq++;
			addLog(column.getColumnName());
		}
		return "@Ok@";
	}
}

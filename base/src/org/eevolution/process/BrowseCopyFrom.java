/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2013 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2013 Victor Pérez Juárez 								  * 
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

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import java.util.logging.Level;

/**
 * Copy Browse from other Browse
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 */
public class BrowseCopyFrom extends SvrProcess {
	/** Record ID */
	protected int p_Record_ID = 0;
	protected int p_AD_Browse_ID = 0;

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
			if (MBrowse.COLUMNNAME_AD_Browse_ID.equals(para.getParameterName()))
				p_AD_Browse_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * Copy Browse
	 * 
	 * @return info
	 */
	@SuppressWarnings("unchecked")
	protected String doIt() throws Exception {

		MBrowse browseFrom = new MBrowse(getCtx(), p_AD_Browse_ID,
				get_TrxName());
		MBrowse browseTo = new MBrowse(getCtx(), p_Record_ID, get_TrxName());
        String name = browseTo.getName();
        String value = browseTo.getValue();
		browseTo.copyValues(browseFrom, browseTo);
        browseTo.setName(name);
        browseTo.setValue(value);
		browseTo.saveEx();

		for (MBrowseField fieldFrom : browseFrom.getFields()) {
			MBrowseField fieldTo = new MBrowseField(getCtx(), 0, get_TrxName());
			fieldTo.copyValues(fieldFrom, fieldTo);
            fieldTo.setAD_Browse_ID(browseTo.getAD_Browse_ID());
			fieldTo.saveEx();
		}
		return "@Ok@";
	}
}

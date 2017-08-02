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

import java.util.logging.Level;

import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * Copy View from other View
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 * 
 */
public class ViewCopyFrom extends SvrProcess {
	/** Record ID */
	protected int p_Record_ID = 0;
	protected int p_AD_View_ID = 0;

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
			if (MView.COLUMNNAME_AD_View_ID.equals(para.getParameterName()))
				p_AD_View_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * Copy view from other view
	 * 
	 * @return result string
	 */
	@SuppressWarnings("unchecked")
	protected String doIt() throws Exception {
		MView viewFrom = new MView(getCtx(), p_AD_View_ID, get_TrxName());
		MView viewTo = new MView(getCtx(), p_Record_ID, get_TrxName());

        String name = viewTo.getName();
        String value = viewTo.getValue();

		viewTo.copyValues(viewFrom, viewTo);
        viewTo.setName(name);
        viewTo.setValue(value);
		viewTo.saveEx();

		for (MViewDefinition viewDefinitionFrom : viewFrom.getViewDefinitions()) {
			MViewDefinition viewDefinitionTo = new MViewDefinition(getCtx(), 0,
					get_TrxName());
			viewDefinitionTo.copyValues(viewDefinitionFrom, viewDefinitionTo);
            viewDefinitionTo.setAD_View_ID(viewTo.getAD_View_ID());
			viewDefinitionTo.saveEx();

			for (MViewColumn viewColumnFrom : viewDefinitionFrom
					.getADViewColumns()) {
				MViewColumn viewColumnTo = new MViewColumn(getCtx(), 0,
						get_TrxName());
				viewColumnTo.copyValues(viewColumnFrom, viewColumnTo);
                viewColumnTo.setAD_View_Definition_ID(viewDefinitionTo.getAD_View_Definition_ID());
				viewColumnTo.saveEx();
			}
		}
		return "@Ok@";
	}
}

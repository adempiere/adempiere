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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

/**
 * Forecast Definition Model
 * @author victor.perez@e-evolution.com www.e-evolution.com , e-Evolution
 *
 */
public class MPPForecastDefinition extends X_PP_ForecastDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7670565929821887169L;

	private List<MPPForecastDefinitionLine> m_lines = null;

	public MPPForecastDefinition(Properties ctx, int M_ForecastDefinition_ID,
			String trxName) {
		super(ctx, M_ForecastDefinition_ID, trxName);
	}

	public MPPForecastDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public List<MPPForecastDefinitionLine> getLines(boolean requery) {
		if (m_lines != null && !requery)
			return m_lines;

		m_lines = new Query(getCtx(), MPPForecastDefinitionLine.Table_Name,
				MPPForecastDefinitionLine.COLUMNNAME_PP_ForecastDefinition_ID
						+ "=?", get_TrxName()).setClient_ID()
				.setParameters(get_ID())
				.setOrderBy(MPPForecastDefinitionLine.COLUMNNAME_SeqNo).list();
		return m_lines;
	}
}

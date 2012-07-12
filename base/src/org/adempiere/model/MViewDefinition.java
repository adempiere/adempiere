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
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;

/**
 * Class Model for MViewJoin
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  <li>FR [ 3426137 ] Smart Browser
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 
 */
public class MViewDefinition extends X_AD_View_Definition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8557695983263069806L;

	/**
	 * get View Definition
	 * 
	 * @param ctx
	 * @param AD_View_Definition_ID
	 * @return MViewDefinition
	 */
	public static MViewDefinition get(Properties ctx, int AD_View_Definition_ID) {
		if (AD_View_Definition_ID <= 0)
			return null;
		//
		MViewDefinition definition = s_cache.get(AD_View_Definition_ID);
		if (definition != null)
			return definition;
		//
		definition = new MViewDefinition(ctx, AD_View_Definition_ID, null);
		if (definition.get_ID() == AD_View_Definition_ID) {
			s_cache.put(AD_View_Definition_ID, definition);
		} else {
			definition = null;
		}
		return definition;
	}

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MViewDefinition.class);
	/** Cache */
	private static CCache<Integer, MViewDefinition> s_cache = new CCache<Integer, MViewDefinition>(
			Table_Name, 100);
	/** Parent */
	private MView m_view = null;
	/** Join Table */
	private MTable m_table = null;

	/**************************************************************************
	 * MViewDefinition Constructor
	 * 
	 * @param ctx context
	 * @param AD_View_Definition_ID
	 * @param trxName transaction name
	 */
	public MViewDefinition(Properties ctx, int AD_View_Definition_ID,
			String trxName) {
		super(ctx, AD_View_Definition_ID, trxName);
		if (AD_View_Definition_ID == 0) {
		}
	}

	/**
	 * MViewDefinition Constructor
	 * 
	 * @param ctx context
	 * @param AD_View_Definition_ID
	 */
	public MViewDefinition(Properties ctx, int AD_View_Definition_ID) {
		this(ctx, AD_View_Definition_ID, null);
	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set record
	 * @param trxName transaction
	 */
	public MViewDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MViewDefinition

	/**
	 * String representation
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MViewDefinition[").append(get_ID())
				.append("-").append("").append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Parent View
	 * @return
	 */
	public MView getAD_View() {
		if (m_view == null) {
			m_view = (MView) super.getAD_View();
		}

		return m_view;
	}

	/**
	 * Get Parent View
	 * 
	 * @return
	 */
	public MTable getAD_Table() {
		if (m_table == null) {
			m_table = MTable.get(getCtx(), getAD_Table_ID());
		}

		return m_table;
	}

	public String getSelectClause() {
		StringBuffer selectClause = new StringBuffer();
		List<MColumn> atts = getEntityAttributes();
		for (MColumn attr : atts) {
			selectClause.append(getTableAlias()).append(attr.getColumnName())
					.append(",");
		}
		return selectClause.toString();
	}

	/**
	 * get List MColumn
	 * 
	 * @return List<MColumn>
	 */
	public List<MColumn> getEntityAttributes() {
		final String whereClause = MColumn.COLUMNNAME_AD_Table_ID + "=?";

		return new Query(getCtx(), MColumn.Table_Name, whereClause,
				get_TrxName()).setParameters(getAD_Table_ID())
				.setOnlyActiveRecords(true).list();
	}

	/**
	 * get List View Column
	 * 
	 * @return
	 */
	public List<MViewColumn> getADViewColunms() {
		final String whereClause = MViewColumn.COLUMNNAME_AD_View_Definition_ID
				+ "=?";

		return new Query(getCtx(), MViewColumn.Table_Name, whereClause,
				get_TrxName()).setParameters(getAD_View_Definition_ID()).list();
	}
}

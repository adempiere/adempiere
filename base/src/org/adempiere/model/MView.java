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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Class Model for Smart View
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  <li>FR [ 3426137 ] Smart Browser
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 */
public class MView extends X_AD_View {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4624429043533053271L;
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MView.class);
	private List<MViewColumn> m_ViewColumn = null;

	/**************************************************************************
	 * Smart View
	 * @param ctx context
	 * @param AD_SmartView_ID
	 * @param trxName transaction name
	 */
	public MView(Properties ctx, int AD_SmartView_ID, String trxName) {
		super(ctx, AD_SmartView_ID, trxName);
		if (AD_SmartView_ID == 0) {
		}
	}

	/**
	 * @param ctx context
	 * @param AD_View_ID View ID
	 */
	public MView(Properties ctx, int AD_SmartView_ID) {
		this(ctx, AD_SmartView_ID, null);
	}

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set record
	 * @param trxName transaction
	 */
	public MView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MView

	/**
	 * String representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MInOutBound[").append(get_ID())
				.append("-").append(getName()).append("]");
		return sb.toString();
	} // toString

	/**
	 * return the Smart View Joins
	 * 
	 * @return List<MViewDefinition>
	 */
	public List<MViewDefinition> getViewDefinitions() {
		final String whereClause = COLUMNNAME_AD_View_ID + "= ?";
		return new Query(getCtx(), MViewDefinition.Table_Name, whereClause,
				get_TrxName()).setParameters(new Object[] { get_ID() })
				.setOrderBy(MViewDefinition.COLUMNNAME_SeqNo)
				.setOnlyActiveRecords(true).list();
	}

	/**
	 * Get the Smart View Joins
	 * @return String View Joins
	 */
	public String getJoinsTables() {
		final String whereClause = COLUMNNAME_AD_View_ID + "= ?";
		Iterator<MViewDefinition> joins = new Query(getCtx(),
				MViewDefinition.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[] { get_ID() })
				.setOrderBy(MViewDefinition.COLUMNNAME_SeqNo)
				.setOnlyActiveRecords(true).iterate();
		StringBuffer tables = new StringBuffer("");
		while (joins.hasNext()) {
			MViewDefinition join = joins.next();
			tables.append(join.getAD_Table().getTableName());
			if (joins.hasNext()) {
				tables.append(",");
			}
		}
		return tables.toString();
	}

	/**
	 * get from Clause
	 * 
	 * @return String from Clause
	 */
	public String getFromClause() {
		String fromClause = " ";
		String joinClause = " ";
		for (MViewDefinition join : getViewDefinitions()) {
			if (join.getJoinClause() == null) {
				fromClause = fromClause + join.getAD_Table().getTableName()
						+ " " + join.getTableAlias();
			} else if (join.getJoinClause().length() > 0) {
				joinClause = joinClause + join.getJoinClause() + " ";
			}
		}

		return fromClause + joinClause;
	}

	/**
	 * getViewColumn
	 * 
	 * @param AD_View_ID
	 * @return MViewColumn
	 */
	public List<MViewColumn> getViewColumns() {
		if(m_ViewColumn != null)
			return m_ViewColumn;
		m_ViewColumn = new ArrayList<MViewColumn>();
		MView view = new MView(Env.getCtx(), getAD_View_ID(), get_TrxName());

		for (MViewDefinition def : view.getViewDefinitions()) {
			final String whereClause = MViewDefinition.COLUMNNAME_AD_View_Definition_ID
					+ "=?";
			List<MViewColumn> columns = new Query(Env.getCtx(),
					MViewColumn.Table_Name, whereClause, get_TrxName())
					.setParameters(def.get_ID()).setOnlyActiveRecords(true)
					.list();

			for (MViewColumn col : columns) {
				m_ViewColumn.add(col);
			}
		}
		
		return m_ViewColumn;
	}
	
	/**
	 * get Parent View Join
	 * 
	 * @return MViewDefinition
	 */
	public MViewDefinition getParentViewDefinition() {

		String whereClause = MViewDefinition.COLUMNNAME_AD_View_ID + "=? AND "
				+ MViewDefinition.COLUMNNAME_JoinClause + " IS NULL";

		MViewDefinition definition = new Query(getCtx(),
				MViewDefinition.Table_Name, whereClause, get_TrxName())
				.setParameters(getAD_View_ID())
				.setOnlyActiveRecords(true)
				.setOrderBy(MViewDefinition.COLUMNNAME_SeqNo).firstOnly();

		return definition;
	}

	/**
	 * Parent Entity Name
	 * @return String Table Name
	 */
	public String getParentEntityName() {
		return MTable.getTableName(getCtx(), getParentViewDefinition()
				.getAD_Table_ID());
	}

	/**
	 * get Parent Entity Alias
	 * @return
	 */
	public String getParentEntityAliasName() {
		return getParentViewDefinition().getTableAlias();
	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		DB.executeUpdate("DELETE FROM AD_View_Trl WHERE AD_View_ID=? ", getAD_View_ID(),get_TrxName());
		return true;
	}	//	beforeDelete

	/**
	 * get SQL from View
	 * 
	 * @param AD_View_ID
	 * @param trxName
	 * @return SQL string
	 */
	public static String getSQLFromView(int AD_View_ID, String trxName) {
		StringBuffer sql = new StringBuffer();
		StringBuffer joins = new StringBuffer();
		StringBuffer cols = new StringBuffer();
		String from = "";
		MView view = new MView(Env.getCtx(), AD_View_ID, null);

		sql.append("SELECT ");
		boolean co = false;
		for (MViewDefinition def : view.getViewDefinitions()) {
			List<MViewColumn> columns = new Query(Env.getCtx(),
					MViewColumn.Table_Name,
					MViewDefinition.COLUMNNAME_AD_View_Definition_ID + "=?",
					trxName).setParameters(def.get_ID())
					.setOnlyActiveRecords(true).list();

			for (MViewColumn col : columns) {
				if (co)
					cols.append(",");
				if (col.getColumnSQL() != null
						&& col.getColumnSQL().length() > 0) {

					cols.append(col.getColumnSQL() + " as " + col.getName());
					co = true;
				} else if (col.getColumnName() != null
						&& col.getColumnName().length() > 0) {

					cols.append(def.getTableAlias() + "." + col.getColumnName()
							+ " as " + col.getName());
					co = true;
				}
			}

			MTable table = new MTable(Env.getCtx(), def.getAD_Table_ID(), null);

			if (def.getJoinClause() != null && def.getJoinClause().length() > 0) {
				String jc = def.getJoinClause();

				joins.append(" ").append(jc).append(" ");
			} else
				from = table.getTableName() + " " + def.getTableAlias();
		}

		sql.append(cols).append(" from ").append(from).append(" ")
				.append(joins);

		return sql.toString();
	}

	public static boolean isValidValue(int AD_View_ID, String ColumnName,
			Object Value) {
		boolean valid = false;
		String whereClause = "Name = ? and AD_View_Definition_ID IN "
				+ "(SELECT AD_View_Definition_ID FROM AD_View_Definition WHERE AD_View_ID = ?)";
		MViewColumn column = new Query(Env.getCtx(), MViewColumn.Table_Name,
				whereClause, null).setParameters(ColumnName, AD_View_ID)
				.first();

		if (column != null) {
			if (column.get_ValueAsBoolean("IsMandatory")) {
				if (Value != null) {
					valid = true;
				}
			} else {
				valid = true;
			}
		}

		return valid;
	}
}

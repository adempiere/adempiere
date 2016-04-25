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

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Class Model for Smart Browse
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  <li>FR [ 3426137 ] Smart Browser
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 267 ] Smart Browse don't have a Sequence Tab for Search Criteria Fields
 * 		@see https://github.com/adempiere/adempiere/issues/267
 * 		<li>FR [ 344 ] Smart Browse Search View is not MVC
 * 		@see https://github.com/adempiere/adempiere/issues/344
 */
public class MBrowse extends X_AD_Browse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7723306980903810620L;
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MBrowse.class);
	private MView m_view = null;
	private String m_title = null;
	private List<MBrowseField> m_Fields = null;
	private List<MBrowseField> m_DisplayFields = null;
	private List<MBrowseField> m_CriterialFields = null;
	private List<MBrowseField> m_IdentifierFields = null;
	private List<MBrowseField> m_OrderByFields = null;
	private	MBrowseField m_fieldKey = null;
	/**	Cache						*/
	private static CCache<Integer,MBrowse>	s_cache	= new CCache<Integer,MBrowse>("AD_Browse", 20);

	/**************************************************************************
	 * Asset Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_SmartBrowse_ID
	 *            InOutBound ID
	 * @param trxName
	 *            transaction name
	 */
	public MBrowse(Properties ctx, int AD_SmartBrowse_ID, String trxName) {
		super(ctx, AD_SmartBrowse_ID, trxName);
		if (AD_SmartBrowse_ID == 0) {
		}
	}

	/**
	 * Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 * 
	 * @param ctx
	 *            context
	 * @param AD_SmartBrowse_ID
	 *            Cahs Flow ID
	 */
	public MBrowse(Properties ctx, int AD_SmartBrowse_ID) {
		this(ctx, AD_SmartBrowse_ID, null);
	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set record
	 * @param trxName
	 *            transaction
	 */
	public MBrowse(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} //

	/**
	 * String representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" MSmartBrowse[").append(get_ID())
				.append("-").append(getName()).append("]");
		return sb.toString();
	} // toString

	/**
	 * get Criteria Fields
	 * 
	 * @return List Browse field
	 */
	public List<MBrowseField> getCriteriaFields() {
		if (m_CriterialFields == null) {
			final StringBuilder whereClause = new StringBuilder(
					MBrowseField.COLUMNNAME_AD_Browse_ID);
			whereClause.append("=? AND ")
					.append(MBrowseField.COLUMNNAME_IsQueryCriteria)
					.append("=?");

			return new Query(getCtx(), MBrowseField.Table_Name,
					whereClause.toString(), get_TrxName())
					.setParameters(get_ID(), "Y").setOnlyActiveRecords(true)
					//	FR [ 267 ] Add SeqNoGrid
					.setOrderBy(MBrowseField.COLUMNNAME_SeqNoGrid).list();
		}
		return m_CriterialFields;
	}

	/**
	 * get Fields
	 * 
	 * @return List Fields
	 */
	public List<MBrowseField> getFields() {
		if (m_Fields == null) {
			final StringBuilder whereClause = new StringBuilder(
					MBrowseField.COLUMNNAME_AD_Browse_ID).append("=?");
			m_Fields = new Query(getCtx(), MBrowseField.Table_Name,
					whereClause.toString(), get_TrxName())
					.setParameters(get_ID()).setOnlyActiveRecords(true)
					.setOrderBy(MBrowseField.COLUMNNAME_SeqNo).list();
		}
		return m_Fields;
	}

	/**
	 * get Identifier Fields
	 * 
	 * @return List Fields
	 */
	public List<MBrowseField> getIdentifierFields() {
		if (m_IdentifierFields == null) {
			final StringBuilder whereClause = new StringBuilder(
					MBrowseField.COLUMNNAME_AD_Browse_ID);
			whereClause.append("=? AND ")
					.append(MBrowseField.COLUMNNAME_IsIdentifier).append("=?");
			m_IdentifierFields = new Query(getCtx(), MBrowseField.Table_Name,
					whereClause.toString(), get_TrxName())
					.setParameters(get_ID()).setOnlyActiveRecords(true)
					.setParameters(getAD_Browse_ID(), true)
					.setOrderBy(MBrowseField.COLUMNNAME_SeqNo).list();
		}
		return m_IdentifierFields;
	}

	/**
	 * get Display Fields
	 * 
	 * @return List Fields
	 */
	public List<MBrowseField> getDisplayFields() {
		if (m_DisplayFields == null) {
			final StringBuilder whereClause = new StringBuilder(
					MBrowseField.COLUMNNAME_AD_Browse_ID);
			whereClause.append(" = ? AND ")
					.append("(")
					.append(MBrowseField.COLUMNNAME_IsDisplayed).append(" = ? ")
					.append("OR ")
					.append(MBrowseField.COLUMNNAME_IsIdentifier).append(" = ?")
					.append(")");
			m_DisplayFields = new Query(getCtx(), MBrowseField.Table_Name,
					whereClause.toString(), get_TrxName())
					.setParameters(get_ID(), "Y", "Y")
					.setOnlyActiveRecords(true)
					.setOrderBy(MBrowseField.COLUMNNAME_SeqNo).list();
		}
		return m_DisplayFields;
	}

	/**
	 * get Display Fields
	 * 
	 * @return List Fields
	 */
	public List<MBrowseField> getOrderByFields() {
		if (m_OrderByFields == null) {
			final StringBuilder whereClause = new StringBuilder(
					MBrowseField.COLUMNNAME_AD_Browse_ID);
			whereClause.append("=? AND ")
			.append(MBrowseField.COLUMNNAME_IsOrderBy)
			.append("=? AND ")
			.append(MBrowseField.COLUMNNAME_IsDisplayed).append("=? ");
			m_OrderByFields = new Query(getCtx(), MBrowseField.Table_Name,
					whereClause.toString(), get_TrxName())
			.setParameters(get_ID(), true, true ).setOnlyActiveRecords(true)
			.setOrderBy(MBrowseField.COLUMNNAME_SortNo).list();
		}
		return m_OrderByFields;
	}
	/**
	 * get Fields is not ReadOnly
	 * 
	 * @return List Fields Update
	 */
	public List<MBrowseField> getNotReadOnlyFields() {
		final StringBuilder whereClause = new StringBuilder(
				MBrowseField.COLUMNNAME_AD_Browse_ID);
		whereClause.append("=? AND ")
				.append(MBrowseField.COLUMNNAME_IsDisplayed).append("=? AND ")
				.append(MBrowseField.COLUMNNAME_IsReadOnly).append("=? ");
		return new Query(getCtx(), MBrowseField.Table_Name,
				whereClause.toString(), get_TrxName())
				.setParameters(get_ID(), "Y", "N").setOnlyActiveRecords(true)
				.setOrderBy(MBrowseField.COLUMNNAME_SeqNo).list();
	}

	/**
	 * get field using name
	 * 
	 * @param name
	 *            field
	 * @return field
	 */
	public MBrowseField getField(String name) {
		for (MBrowseField field : getFields()) {
			if (field.getName().equals(name)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * get field Key
	 * 
	 * @param name
	 *            field
	 * @return field
	 */
	public MBrowseField getFieldKey() {
		if (m_fieldKey != null)
				return m_fieldKey;
		
		final String whereClause = MBrowse.COLUMNNAME_AD_Browse_ID + "=? AND "
				+ MBrowseField.COLUMNNAME_IsKey + "=? AND "
				+ MBrowseField.COLUMNNAME_Name + "!=? ";
		m_fieldKey = new Query(getCtx(), MBrowseField.Table_Name, whereClause,
				get_TrxName()).setParameters(this.getAD_Browse_ID(), "Y",
				getName()).firstOnly();
		return m_fieldKey;
	}

	/**
	 * get MView
	 */
	public MView getAD_View() {
		if (m_view == null) {
			m_view = new MView(getCtx(), getAD_View_ID(), get_TrxName());
		}
		return m_view;
	}

	/**
	 * Before Delete
	 * 
	 * @return true of it can be deleted
	 */
	protected boolean beforeDelete() {
		DB.executeUpdate("DELETE FROM AD_Browse_Access WHERE AD_Browse_ID=? ",
				getAD_Browse_ID(), get_TrxName());
		DB.executeUpdate("DELETE FROM AD_Browse_Trl WHERE AD_Browse_ID=? ",
				getAD_Browse_ID(), get_TrxName());
		return true;
	} // beforeDelete

	public String getTitle() {
		if (m_title != null)
			return m_title;

		final boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(),
				"AD_Browse");
		final String sql = "SELECT Name FROM AD_Browse_Trl WHERE AD_Browse_ID=? AND AD_LANGUAGE=?";
		m_title = baseLanguage ? getName() : DB.getSQLValueString(null, sql,
				getAD_Browse_ID(), Env.getAD_Language(Env.getCtx()));
		return m_title;
	}
	
	/**
	 * 	Get MBrowse from ID
	 *	@param ctx context 
	 *	@param Value value
	 *	@return MBrowse or null
	 */
	public static MBrowse get (Properties ctx, int AD_Browse_ID) {
		Integer key = new Integer (AD_Browse_ID);
		MBrowse retValue = (MBrowse) s_cache.get (key);
		if (retValue != null)
			return retValue;
		//	Get from disk
		s_log.fine("AD_Browse_ID = " + AD_Browse_ID + " get from disk no cache");
		retValue = new MBrowse (ctx, AD_Browse_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		//	Return
		return retValue;
	}	//	get
}

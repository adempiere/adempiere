/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_C_CommissionType;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Class for handle status bar
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2173 ] Commission calc based on custom query
 * @see https://github.com/adempiere/adempiere/issues/2173
 */
public class MCommissionType extends X_C_CommissionType {

	public MCommissionType(Properties ctx, int C_CommissionType_ID, String trxName) {
		super(ctx, C_CommissionType_ID, trxName);
	}
	
	public MCommissionType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MCommissionType> commissionTypeCacheIds = new CCache<Integer, MCommissionType>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MCommissionType> commissionTypeCacheValues = new CCache<String, MCommissionType>(Table_Name, 30);

	/**
	 * 
	 */
	private static final long serialVersionUID = 6876713053371206663L;
	
	/**	Current View	*/
	private MView view = null;

	/**
	 * Get/Load Commission Type[CACHED]
	 * @param ctx context
	 * @param commissionTypeId
	 * @param trxName
	 * @return activity or null
	 */
	public static MCommissionType getById(Properties ctx, int commissionTypeId, String trxName) {
		if (commissionTypeId <= 0)
			return null;

		MCommissionType commissionType = commissionTypeCacheIds.get(commissionTypeId);
		if (commissionType != null && commissionType.get_ID() > 0)
			return commissionType;

		commissionType = new Query(ctx , Table_Name , COLUMNNAME_C_CommissionType_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(commissionTypeId)
				.first();
		if (commissionType != null && commissionType.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + commissionType.getValue();
			commissionTypeCacheValues.put(key, commissionType);
			commissionTypeCacheIds.put(commissionType.get_ID(), commissionType);
		}
		return commissionType;
	}
	
	/**
	 * get Commission Type By Value [CACHED]
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MCommissionType getByValue(Properties ctx, String value, String trxName) {
		if (value == null)
			return null;
		if (commissionTypeCacheValues.size() == 0) {
			getAll(ctx, true, trxName);
		}

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + value;
		MCommissionType commissionType = commissionTypeCacheValues.get(key);
		if (commissionType != null && commissionType.get_ID() > 0 )
			return commissionType;

		commissionType =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setClient_ID()
				.setParameters(value)
				.setOrderBy(COLUMNNAME_Value)
				.first();

		if (commissionType != null && commissionType.get_ID() > 0) {
			commissionTypeCacheValues.put(key, commissionType);
			commissionTypeCacheIds.put(commissionType.get_ID() , commissionType);
		}
		return commissionType;
	}

	/**
	 * Get All Activity
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MCommissionType> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MCommissionType> commissionTypeList;
		if (resetCache || commissionTypeCacheIds.size() > 0 ) {
			commissionTypeList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			commissionTypeList.stream().forEach(definition -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + definition.getValue();
				commissionTypeCacheIds.put(definition.getC_CommissionType_ID(), definition);
				commissionTypeCacheValues.put(key, definition);
			});
			return commissionTypeList;
		}
		commissionTypeList = commissionTypeCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  commissionTypeList;
	}
	
	/**
	 * Get view
	 * @return
	 */
	public MView getView() {
		if(view == null) {
			view = (MView) getAD_View();
		}
		//	Return
		return view;
	}
	
	/**
	 * Get Amount Column
	 * @return
	 */
	private MViewColumn getAmountViewColumn() {
		return MViewColumn.getById(getCtx(), getAmountColumn_ID(), get_TrxName());
	}
	
	/**
	 * Get Quantity Column
	 * @return
	 */
	private MViewColumn getQuantityViewColumn() {
		return MViewColumn.getById(getCtx(), getQuantityColumn_ID(), get_TrxName());
	}
	
	/**
	 * Get Currency Column
	 * @return
	 */
	private MViewColumn getCurrencyViewColumn() {
		return MViewColumn.getById(getCtx(), getCurrencyColumn_ID(), get_TrxName());
	}
	
	/**
	 * Get Document Date Column
	 * @return
	 */
	private MViewColumn getDateDocViewColumn() {
		return MViewColumn.getById(getCtx(), getDateDocColumn_ID(), get_TrxName());
	}
	
	/**
	 * Get Column Name for Amount
	 * @return
	 */
	public String getSQLAmountColumnName() {
		MViewColumn viewColumn = getAmountViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnSQL();
	}
	
	/**
	 * Get Column Name for Quantity
	 * @return
	 */
	public String getSQLQuantityColumnName() {
		MViewColumn viewColumn = getQuantityViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnSQL();
	}
	
	/**
	 * Get Column Name for Currency
	 * @return
	 */
	public String getSQLCurrencyColumnName() {
		MViewColumn viewColumn = getCurrencyViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnSQL();
	}
	
	/**
	 * Get Column Name for Date
	 * @return
	 */
	public String getSQLDateDocColumnName() {
		MViewColumn viewColumn = getDateDocViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnSQL();
	}
	
	/**
	 * Get Column Name for Amount
	 * @return
	 */
	public String getAmountColumnName() {
		MViewColumn viewColumn = getAmountViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnName();
	}
	
	/**
	 * Get Column Name for Quantity
	 * @return
	 */
	public String getQuantityColumnName() {
		MViewColumn viewColumn = getQuantityViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnName();
	}
	
	/**
	 * Get Column Name for Currency
	 * @return
	 */
	public String getCurrencyColumnName() {
		MViewColumn viewColumn = getCurrencyViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnName();
	}
	
	/**
	 * Get Column Name for Date
	 * @return
	 */
	public String getDateDocColumnName() {
		MViewColumn viewColumn = getDateDocViewColumn();
		if(viewColumn == null) {
			return null;
		}
		return viewColumn.getColumnName();
	}
	
	/**
	 * Get Available Column Name
	 * @param columnNames
	 * @return
	 */
	public String getSQLColumnName(String... columnNames) {
		MView view = getView();
		if(view == null) {
			return null;
		}
		//	
		for(MViewColumn viewColumn : view.getViewColumns()) {
			if(viewColumn.getAD_Column_ID() > 0) {
				MColumn column = MColumn.get(getCtx(), viewColumn.getAD_Column_ID());
				for(String columnName : columnNames) {
					if(column.getColumnName().equals(columnName)) {
						return viewColumn.getColumnSQL();
					}
				}
			} else {
				for(String columnName : columnNames) {
					if(viewColumn.getColumnName().contains(columnName)) {
						return viewColumn.getColumnSQL();
					}
				}
			}
		}
		//	Default
		return null;
	}
	
	/**
	 * Get Available Column Name
	 * @param columnNames
	 * @return
	 */
	public String getColumnName(String... columnNames) {
		MView view = getView();
		if(view == null) {
			return null;
		}
		//	
		for(MViewColumn viewColumn : view.getViewColumns()) {
			if(viewColumn.getAD_Column_ID() > 0) {
				MColumn column = MColumn.get(getCtx(), viewColumn.getAD_Column_ID());
				for(String columnName : columnNames) {
					if(column.getColumnName().equals(columnName)) {
						return viewColumn.getColumnName();
					}
				}
			} else {
				for(String columnName : columnNames) {
					if(viewColumn.getColumnName().contains(columnName)) {
						return viewColumn.getColumnName();
					}
				}
			}
		}
		//	Default
		return null;
	}
	
	/**
	 * Verify if a column name is valid for filter
	 * @param columnName
	 * @return
	 */
	public boolean isValidColumn(String columnName) {
		MView view = getView();
		if(view == null) {
			return false;
		}
		//	
		for(MViewColumn viewColumn : view.getViewColumns()) {
			if(viewColumn.getAD_Column_ID() > 0) {
				MColumn column = MColumn.get(getCtx(), viewColumn.getAD_Column_ID());
				if(column.getColumnName().equals(columnName)) {
					return true;
				}
			}
		}
		//	Default
		return false;
	}
}

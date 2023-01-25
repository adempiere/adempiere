package org.adempiere.model;

import io.vavr.collection.List;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ResultSetIterable;

import java.util.ArrayList;
import java.util.Properties;

public class MDocumentStatus {

	private static CLogger log = CLogger.getCLogger(MDocumentStatus.class);

	/**
	 * 	Get User Goals
	 *	@param ctx context
	 *	@param userId user
	 * @param roleId 
	 *	@return array of goals
	 */
	public static IDocumentStatus[] getDocumentStatusIndicators(Properties ctx, int userId, int roleId)
	{
		if (userId < 0)
			return new IDocumentStatus[0];
		
		ArrayList<IDocumentStatus> list = new ArrayList<IDocumentStatus>();
		final String sql = "SELECT * FROM PA_DocumentStatus g "
			+ "WHERE IsActive = ? "
			+ " AND ((AD_User_ID IS NULL OR AD_User_ID=?) AND " //#2
				+ " ( AD_Role_ID IS NULL OR AD_Role_ID=?)) "	//	#3)
			+ "ORDER BY SeqNo";

		String sqlAccess = MRole.getDefault().addAccessSQL(sql, "g", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		DB.runResultSetFunction.apply(null , sqlAccess , List.of("Y",userId,roleId) , resultSet -> {
			new ResultSetIterable<>(resultSet, row -> {
				GenericPO indicator = new GenericPO(IDocumentStatus.Table_Name, ctx, row);
				return POWrapper.create(indicator, IDocumentStatus.class);
			}).forEach(documentStatus -> list.add(documentStatus));
		}).onFailure(throwable -> log.severe(throwable.getMessage()));
		IDocumentStatus[] retValue = new IDocumentStatus[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getUserGoals

	public static int evaluate(IDocumentStatus documentStatus) {
		StringBuffer sql = new StringBuffer("SELECT Count(*) FROM ");
		String tableName = MTable.getTableName(Env.getCtx(), documentStatus.getAD_Table_ID());
		sql.append(tableName);
		String where = getWhereClause(documentStatus);
		if (where != null && where.trim().length() > 0)
			sql.append(" WHERE " ).append(where);
		String parsedSql = MRole.getDefault().addAccessSQL(sql.toString(), tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		return DB.getSQLValue(null, parsedSql.toString());
	}

	public static String getWhereClause(IDocumentStatus documentStatus) {
		StringBuffer where = new StringBuffer(" AD_Client_ID= " + documentStatus.getAD_Client_ID() );
		if (documentStatus.getC_Project_ID() > 0) 
		{
			where.append(" AND C_Project_ID=").append(documentStatus.getC_Project_ID());
		}
		if (documentStatus.getAD_Org_ID() > 0) 
		{
			where.append(" AND AD_Org_ID=").append(documentStatus.getAD_Org_ID());
		}
		String extra = documentStatus.getWhereClause();
		if (extra != null && extra.trim().length() > 0)
		{
			where.append(" AND ( ").append(extra).append(" ) ");
		}
		return Env.parseContext(Env.getCtx(), 0, where.toString(), true);
	}
}

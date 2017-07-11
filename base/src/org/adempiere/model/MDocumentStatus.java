package org.adempiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MDocumentStatus {

	/**
	 * 	Get User Goals
	 *	@param ctx context
	 *	@param AD_User_ID user
	 * @param AD_Role_ID 
	 *	@return array of goals
	 */
	public static IDocumentStatus[] getDocumentStatusIndicators(Properties ctx, int AD_User_ID, int AD_Role_ID)
	{
		if (AD_User_ID < 0)
			return new IDocumentStatus[0];
		
		ArrayList<IDocumentStatus> list = new ArrayList<IDocumentStatus>();
		String sql = "SELECT * FROM PA_DocumentStatus g "
			+ "WHERE IsActive='Y'"
			+ " AND AD_Client_ID=?"		//	#1
			+ " AND ((AD_User_ID IS NULL OR AD_User_ID=?) AND " //#2
				+ " ( AD_Role_ID IS NULL OR AD_Role_ID=?)) "	//	#3)
			+ "ORDER BY SeqNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			pstmt.setInt (2, AD_User_ID);
			pstmt.setInt (3, AD_Role_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				GenericPO indicator = new GenericPO(IDocumentStatus.Table_Name, ctx, rs);
				list.add (POWrapper.create(indicator, IDocumentStatus.class));
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
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
		return DB.getSQLValue(null, sql.toString());
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

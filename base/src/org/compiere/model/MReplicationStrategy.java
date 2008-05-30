package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author Trifon N. Trifonov
 */
public class MReplicationStrategy extends X_AD_ReplicationStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MReplicationStrategy.class);
	
	
	public MReplicationStrategy(Properties ctx, int AD_ReplicationStrategy_ID, String trxName) {
		super(ctx, AD_ReplicationStrategy_ID, trxName);
	}
	
	public X_AD_ReplicationTable[] getReplicationTables() {
		List<X_AD_ReplicationTable> resultList = new ArrayList<X_AD_ReplicationTable>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_AD_ReplicationTable.Table_Name)
			.append(" WHERE ").append(X_AD_ReplicationTable.COLUMNNAME_AD_ReplicationStrategy_ID).append("=?") // #1
			.append(" AND IsActive = ?")	// #2
//			.append(" ORDER BY ").append(orderBy)
		;
		PreparedStatement pstmt = null;
		X_AD_ReplicationTable rplTable = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt(1, getAD_ReplicationStrategy_ID());
			pstmt.setString(2, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				rplTable = new X_AD_ReplicationTable (getCtx(), rs, get_TrxName());
				resultList.add(rplTable);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			try	{
				if (pstmt != null) pstmt.close ();
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}

		X_AD_ReplicationTable[] result = (X_AD_ReplicationTable[])resultList.toArray( new X_AD_ReplicationTable[0]);
		return result;
	}
	
	public X_AD_ReplicationDocument[] getReplicationDocuments() {
		List<X_AD_ReplicationDocument> resultList = new ArrayList<X_AD_ReplicationDocument>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_AD_ReplicationDocument.Table_Name)
			.append(" WHERE ").append(X_AD_ReplicationDocument.COLUMNNAME_AD_ReplicationStrategy_ID).append("=?") // #1
			.append(" AND IsActive = ?")	// #2
//			.append(" ORDER BY ").append(orderBy)
		;
		PreparedStatement pstmt = null;
		X_AD_ReplicationDocument rplDocument = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt(1, getAD_ReplicationStrategy_ID());
			pstmt.setString(2, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				rplDocument = new X_AD_ReplicationDocument (getCtx(), rs, get_TrxName());
				resultList.add(rplDocument);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			try	{
				if (pstmt != null) pstmt.close ();
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}

		X_AD_ReplicationDocument[] result = (X_AD_ReplicationDocument[])resultList.toArray( new X_AD_ReplicationDocument[0]);
		return result;
	}

}

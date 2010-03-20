/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 www.metas.de                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MQuery;
import org.compiere.model.MRMA;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Generic provider of zoom targets. Contains pieces of {@link org.compiere.apps.AZoomAcross}
 * methods <code>getZoomTargets</code> and <code>addTarget</code>
 * 
 * @author Tobias Schoeneberg, www.metas.de - FR [ 2897194  ] Advanced Zoom and RelationTypes
 * 
 */
public class GenericZoomProvider implements IZoomProvider {

	private static final CLogger logger = CLogger
			.getCLogger(GenericZoomProvider.class);

	public List<ZoomInfoFactory.ZoomInfo> retrieveZoomInfos(PO po) {

		String sql = "SELECT DISTINCT ws.AD_Window_ID,ws.Name, wp.AD_Window_ID,wp.Name, t.TableName "
				+ "FROM AD_Table t ";
		boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(), "AD_Window");
		if (baseLanguage)
			sql += "INNER JOIN AD_Window ws ON (t.AD_Window_ID=ws.AD_Window_ID)"
					+ " LEFT OUTER JOIN AD_Window wp ON (t.PO_Window_ID=wp.AD_Window_ID) ";
		else
			sql += "INNER JOIN AD_Window_Trl ws ON (t.AD_Window_ID=ws.AD_Window_ID AND ws.AD_Language=?)"
					+ " LEFT OUTER JOIN AD_Window_Trl wp ON (t.PO_Window_ID=wp.AD_Window_ID AND wp.AD_Language=?) ";
		//
		sql += "WHERE t.TableName NOT LIKE 'I%'" // No Import
				+ " AND EXISTS (SELECT * FROM AD_Tab tt " // First Tab
				+ "WHERE (tt.AD_Window_ID=ws.AD_Window_ID OR tt.AD_Window_ID=wp.AD_Window_ID)"
				+ " AND tt.AD_Table_ID=t.AD_Table_ID AND tt.SeqNo=10)"
				+ " AND t.AD_Table_ID IN "
				+ "(SELECT AD_Table_ID FROM AD_Column "
				+ "WHERE ColumnName=? AND IsKey='N' AND IsParent='N') " // #x
				+ "ORDER BY 2";

		final PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;
		try {

			int index = 1;
			if (!baseLanguage) {
				pstmt.setString(index++, Env.getAD_Language(Env.getCtx()));
				pstmt.setString(index++, Env.getAD_Language(Env.getCtx()));
			}
			pstmt.setString(index++, po.get_TableName() + "_ID");
			rs = pstmt.executeQuery();

			final List<ZoomInfoFactory.ZoomInfo> result = new ArrayList<ZoomInfoFactory.ZoomInfo>();
			while (rs.next()) {

				int AD_Window_ID = rs.getInt(1);
				String Name = rs.getString(2);
				int PO_Window_ID = rs.getInt(3);
				String targetTableName = rs.getString(5);

				if (PO_Window_ID == 0) {

					final MQuery query = evaluateQuery(targetTableName,
							AD_Window_ID, Name, null, po);
					result.add(new ZoomInfoFactory.ZoomInfo(AD_Window_ID,
							query, Name));

				} else {
					final MQuery query = evaluateQuery(targetTableName,
							AD_Window_ID, Name, Boolean.TRUE, po);
					result.add(new ZoomInfoFactory.ZoomInfo(AD_Window_ID,
							query, Name));
					// // PO
				}
				if (PO_Window_ID != 0) {

					Name = rs.getString(4);
					final MQuery query = evaluateQuery(targetTableName,
							PO_Window_ID, Name, Boolean.FALSE, po);

					result.add(new ZoomInfoFactory.ZoomInfo(PO_Window_ID,
							query, Name));
				}
			}
			return result;

		} catch (SQLException e) {
			logger.log(Level.SEVERE, sql, e);
			throw new AdempiereException();
		} finally {
			DB.close(rs, pstmt);
		}
	}

	private static MQuery evaluateQuery(String targetTableName,
			int AD_Window_ID, String Name, Boolean isSO, final PO po) {

		final MQuery query = new MQuery();

		query.addRestriction(po.get_TableName() + "_ID=" + po.get_ID());
		query.setZoomTableName(targetTableName);
		query.setZoomColumnName(po.get_KeyColumns()[0]);
		query.setZoomValue(po.get_ID());

		String sql = "SELECT COUNT(*) FROM " + targetTableName + " WHERE "
				+ query.getWhereClause(false);
		String sqlAdd = "";
		if (isSO != null) {
			/*
			 * For RMA, Material Receipt window should be loaded for
			 * IsSOTrx=true and Shipment for IsSOTrx=false
			 */

			if (MRMA.Table_Name.equals(po.get_TableName())
					&& (AD_Window_ID == 169 || AD_Window_ID == 184)) {
				isSO = !isSO;
			}
			sqlAdd = " AND IsSOTrx=" + (isSO.booleanValue() ? "'Y'" : "'N'");
		}
		int count = DB.getSQLValue(null, sql + sqlAdd);
		if (count < 0 && isSO != null) // error try again w/o SO
			count = DB.getSQLValue(null, sql);

		query.setRecordCount(count);

		return query;
	} // checkTarget

}

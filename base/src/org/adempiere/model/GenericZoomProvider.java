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
import org.compiere.model.MTab;
import org.compiere.model.PO;
import org.compiere.util.CCache;
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
	/**	Cache Tables	*/
	private static CCache<String, List<ZoomMetaInfo>> cache = new CCache<String, List<ZoomMetaInfo>>("ZoomMetaInfo", 40, 5);	//	5 minutes
	
	public List<ZoomInfoFactory.ZoomInfo> retrieveZoomInfos(PO po) {
		final List<ZoomInfoFactory.ZoomInfo> result = new ArrayList<ZoomInfoFactory.ZoomInfo>();
		List<ZoomMetaInfo> infoList = cache.get(po.get_TableName());
		if(infoList == null) {
			infoList = loadZoom(po.get_TableName());
		}
		//	get values
		infoList.stream().forEach(zoomInfoMetaData -> {
			final MQuery query = evaluateQuery(zoomInfoMetaData.getTargetTableName(),
					zoomInfoMetaData.getTabId(), zoomInfoMetaData.getName(), po);
			result.add(new ZoomInfoFactory.ZoomInfo(zoomInfoMetaData.getWindowId(),
					query, zoomInfoMetaData.getName()));
			if (zoomInfoMetaData.getpOWindowId() != 0 && zoomInfoMetaData.getpOTabId() != 0) {
				final MQuery querypo = evaluateQuery(zoomInfoMetaData.getTargetTableName(),
						zoomInfoMetaData.getpOTabId(), zoomInfoMetaData.getpOName(), po);
				result.add(new ZoomInfoFactory.ZoomInfo(zoomInfoMetaData.getpOWindowId(),
						querypo, zoomInfoMetaData.getpOName()));
			}
		});
		//	Return Info
		return result;
	}
	
	/**
	 * Load from Meta-Data
	 * @param tableName
	 */
	private List<ZoomMetaInfo> loadZoom(String tableName) {
		String sql = "SELECT DISTINCT ws.AD_Window_ID, ws.Name, wp.AD_Window_ID, wp.Name, t.TableName, tts.AD_Tab_ID, ttp.AD_Tab_ID "
				+ "FROM AD_Table t ";
		boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(), "AD_Window");
		if (baseLanguage)
			sql += "INNER JOIN AD_Window ws ON (t.AD_Window_ID=ws.AD_Window_ID)"
					+ " LEFT OUTER JOIN AD_Window wp ON (t.PO_Window_ID=wp.AD_Window_ID) ";
		else
			sql += "INNER JOIN AD_Window_Trl ws ON (t.AD_Window_ID=ws.AD_Window_ID AND ws.AD_Language=?)"
					+ " LEFT OUTER JOIN AD_Window_Trl wp ON (t.PO_Window_ID=wp.AD_Window_ID AND wp.AD_Language=?) ";
		// WARNING - HardCoded: first tab must have SeqNo = 10
		sql += "JOIN AD_Tab tts ON (tts.AD_Window_ID=ws.AD_Window_ID AND tts.AD_Table_ID=t.AD_Table_ID)" // first tab so
				+" LEFT OUTER JOIN AD_Tab ttp ON (ttp.AD_Window_ID=wp.AD_Window_ID AND ttp.AD_Table_ID=t.AD_Table_ID)" // first tab po
				+" WHERE t.TableName NOT LIKE 'I%'" // No Import
				+ " AND t.AD_Table_ID IN "
				+ "(SELECT AD_Table_ID FROM AD_Column "
				+ "WHERE ColumnName=? AND IsKey='N' AND IsParent='N' AND ColumnSQL IS NULL) " // #x
				+ "ORDER BY 2";

		final PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;
		List<ZoomMetaInfo> zoomList = new ArrayList<>();
		try {

			int index = 1;
			if (!baseLanguage) {
				pstmt.setString(index++, Env.getAD_Language(Env.getCtx()));
				pstmt.setString(index++, Env.getAD_Language(Env.getCtx()));
			}
			pstmt.setString(index++, tableName + "_ID");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int windowId = rs.getInt(1);
				String name = rs.getString(2);
				int pOWindowId = rs.getInt(3);
				String pOName = rs.getString(4);
				String targetTableName = rs.getString(5);
				int tabId = rs.getInt(6);
				int pOTabId = rs.getInt(7);
				zoomList.add(new ZoomMetaInfo(windowId, pOWindowId, tabId, pOTabId, name, pOName, targetTableName));
			}
			//	Add to cache
			cache.put(tableName, zoomList);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, sql, e);
			throw new AdempiereException(e);
		} finally {
			DB.close(rs, pstmt);
		}
		//	Default
		return zoomList;
	}

	private static MQuery evaluateQuery(String targetTableName,
			int AD_Tab_ID, String Name, final PO po) {
		
		MTab tab = new MTab(Env.getCtx(), AD_Tab_ID, null);
		final MQuery query = new MQuery();

		query.addRestriction(po.get_TableName() + "_ID=" + po.get_ID());
		if (tab.getWhereClause() != null && tab.getWhereClause().length() > 0)
			query.addRestriction("(" + tab.getWhereClause() + ")");
		query.setZoomTableName(targetTableName);
		query.setZoomColumnName(po.get_KeyColumns()[0]);
		query.setZoomValue(po.get_ID());

		String sql = "SELECT COUNT(*) FROM " + targetTableName + " WHERE "
				+ Env.parseVariable(query.getWhereClause(false), po, null, false);
		int count = DB.getSQLValue(null, sql);

		query.setRecordCount(count);

		return query;
	}
}

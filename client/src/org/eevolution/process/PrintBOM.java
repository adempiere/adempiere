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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.*;
import java.math.BigDecimal;

import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.eevolution.model.X_T_BOMLine;
import org.eevolution.model.X_RV_PP_Product_BOMLine;
import org.compiere.print.MPrintFormat;
import org.compiere.print.Viewer;
import org.compiere.print.ReportEngine;
import org.compiere.print.ReportCtl;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *  BOM lines explosion for print
 *
 *        @author victor.perez@e-evolution.com,Sergio Ramazzina
 *        @version $Id: PrintBOM.java,v 1.2 2005/04/19 12:54:30 srama Exp $
 */
public class PrintBOM extends SvrProcess {

	private static final Properties ctx = Env.getCtx();

	private int p_M_Product_ID = 0;

	private boolean p_implosion = false;

	private int LevelNo = 1;

	private int SeqNo = 0;

	private String levels = new String("....................");

	private int AD_PInstance_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID")) {
				p_M_Product_ID = ((BigDecimal) para[i].getParameter())
						.intValue();

			} else if (name.equals("Implotion")) {
				p_implosion = ((String) para[i].getParameter()).equals("N") ? false
						: true;
			}

			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	} //        prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {

		AD_PInstance_ID = getAD_PInstance_ID();

		loadBOM();

		print();

		return "@ProcessOK@";
	} //        doIt

	/**
	 * Print result generate for this report
	 */
	void print() {
		MPrintFormat format = null;
		Language language = Language.getLoginLanguage(); //	Base Language

		format = MPrintFormat.get(getCtx(), MPrintFormat
				.getPrintFormat_ID("Multi Level BOM & Formula Detail", 53063, getAD_Client_ID()), false);
		format.setLanguage(language);
		format.setTranslationLanguage(language);
		//	query
		MQuery query = MQuery.get(getCtx(), AD_PInstance_ID,
				"RV_PP_Product_BOMLine");
		query.addRestriction("AD_PInstance_ID", MQuery.EQUAL, AD_PInstance_ID);
		//	Engine
		PrintInfo info = new PrintInfo("RV_PP_Product_BOMLine",
				X_RV_PP_Product_BOMLine.Table_ID, getRecord_ID());
		ReportEngine re = new ReportEngine(getCtx(), format, query, info);
		
		ReportCtl.preview(re);

		String sql = "DELETE FROM T_BomLine WHERE AD_PInstance_ID = "
				+ AD_PInstance_ID;
		DB.executeUpdate(sql, get_TrxName());
	}

	/**
	 * 	Action: Fill Tree with all nodes
	 */
	private void loadBOM() {

		if (p_M_Product_ID == 0)
			return;

		X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
		tboml.setPP_Product_BOM_ID(0);
		tboml.setPP_Product_BOMLine_ID(0);
		tboml.setM_Product_ID(p_M_Product_ID);
		tboml.setSel_Product_ID(p_M_Product_ID);
		tboml.setImplosion(p_implosion);
		tboml.setLevelNo(0);
		tboml.setLevels("0");
		tboml.setSeqNo(0);
		tboml.setAD_PInstance_ID(AD_PInstance_ID);
		tboml.save();

		if (p_implosion) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = new String(
					"SELECT PP_Product_BOMLine_ID FROM PP_Product_BOMLine WHERE IsActive = 'Y' AND M_Product_ID = ? ");
			try {
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, p_M_Product_ID);
				rs = stmt.executeQuery();

				while (rs.next()) {
					parentImplotion(rs.getInt(1));
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "explodeBOM ", e);

			}

		} else {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = new String(
					"SELECT PP_Product_BOM_ID FROM PP_Product_BOM WHERE IsActive = 'Y' AND M_Product_ID = ? ");
			try {
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, p_M_Product_ID);
				rs = stmt.executeQuery();
				while (rs.next()) {
					parentExplotion(rs.getInt(1));
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "explodeBOM ", e);

			}

		}
	}

	/**
	 * Generate an Implotion for this BOM Line
	 * @param PP_Product_BOMLine_ID ID BOM Line
	 */
	public void parentImplotion(int PP_Product_BOMLine_ID) {

		X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
		int PP_Product_BOM_ID = DB
				.getSQLValue(
						null,
						"SELECT PP_Product_BOM_ID FROM PP_Product_BOMLine WHERE PP_Product_BOMLine_ID=?",
						PP_Product_BOMLine_ID);
		int M_Product_ID = DB
				.getSQLValue(
						null,
						"SELECT M_Product_ID FROM PP_Product_BOM WHERE PP_Product_BOM_ID=?",
						PP_Product_BOM_ID);
		tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
		tboml.setPP_Product_BOMLine_ID(PP_Product_BOMLine_ID);
		tboml.setM_Product_ID(M_Product_ID);
		tboml.setLevelNo(LevelNo);
		tboml.setSel_Product_ID(p_M_Product_ID);
		tboml.setImplosion(p_implosion);

		if (LevelNo >= 11)
			tboml.setLevels(levels + ">" + LevelNo);
		else if (LevelNo >= 1)
			tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);

		tboml.setSeqNo(SeqNo);
		tboml.setAD_PInstance_ID(AD_PInstance_ID);
		tboml.save();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new String(
				"SELECT PP_Product_BOM_ID, M_Product_ID FROM PP_Product_BOM WHERE IsActive = 'Y' AND M_Product_ID = ? ");
		try {
			stmt = DB.prepareStatement(sql, get_TrxName());
			stmt.setInt(1, M_Product_ID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				SeqNo += 1;
				component(rs.getInt(2));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "explodeBOM ", e);

		}

	}

	/**
	 * Generate an Explotion for this BOM
	 * @param PP_Product_BOMLine_ID ID BOM Line
	 */
	public void parentExplotion(int PP_Product_BOM_ID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new String(
				"SELECT PP_Product_BOMLine_ID, M_Product_ID FROM PP_Product_BOMLine boml WHERE IsActive = 'Y' AND PP_Product_BOM_ID = ? ");
		try {
			stmt = DB.prepareStatement(sql, get_TrxName());
			stmt.setInt(1, PP_Product_BOM_ID);
			rs = stmt.executeQuery();

			while (rs.next()) {

				SeqNo += 1;
				X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
				tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
				tboml.setPP_Product_BOMLine_ID(rs.getInt(1));
				tboml.setM_Product_ID(rs.getInt(2));
				tboml.setLevelNo(LevelNo);
				tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);
				tboml.setSeqNo(SeqNo);
				tboml.setAD_PInstance_ID(AD_PInstance_ID);
				tboml.setSel_Product_ID(p_M_Product_ID);
				tboml.setImplosion(p_implosion);
				tboml.save();
				component(rs.getInt(2));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "explodeBOM ", e);
		}

	}

	/**
	 * Find if this product as component
	 * @param M_Product_ID ID Component
	 */
	public void component(int M_Product_ID) {

		if (p_implosion) {

			LevelNo += 1;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = new String(
					"SELECT PP_Product_BOMLine_ID FROM PP_Product_BOMLine  WHERE IsActive = 'Y' AND M_Product_ID = ? ");
			try {
				stmt = DB.prepareStatement(sql, get_TrxName());
				stmt.setInt(1, M_Product_ID);
				rs = stmt.executeQuery();
				boolean level = false;
				while (rs.next()) {
					parentImplotion(rs.getInt(1));
				}
				rs.close();
				stmt.close();
				LevelNo -= 1;
				return;
			} catch (SQLException e) {
				log.log(Level.SEVERE, "explodeBOM ", e);

			}

		} else {

			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = new String(
					"SELECT PP_Product_BOM_ID FROM PP_Product_BOM  WHERE IsActive = 'Y' AND Value = ? ");
			try {
				stmt = DB.prepareStatement(sql, get_TrxName());
				String Value = DB.getSQLValueString(null,
						"SELECT Value FROM M_PRODUCT WHERE M_PRODUCT_ID=?",
						M_Product_ID);
				stmt.setString(1, Value);
				rs = stmt.executeQuery();
				boolean level = false;
				while (rs.next()) {
					if (!level)
						LevelNo += 1;

					level = true;
					parentExplotion(rs.getInt(1));
					LevelNo -= 1;
				}
				rs.close();
				stmt.close();
				return;

			} catch (SQLException e) {
				log.log(Level.SEVERE, "explodeBOM ", e);

			}

		}
	}

}

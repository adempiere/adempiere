/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Portions created by Layda Salas are Copyright (C) 2005 QSS Ltda.
 * Contributor(s): Layda Salas (globalqss)
 *****************************************************************************/

package org.compiere.process;

import java.sql.*;
import java.util.logging.*;

import org.compiere.model.MSequence;
import org.compiere.util.*;

/**
 * Copy and overwrite Accounts to products of this category
 * 
 * @author Layda Salas (globalqss)
 * @version $Id: M_Production_Run,v 1.0 2005/10/09 22:19:00 globalqss Exp $
 */
public class M_Production_Run extends SvrProcess {

	/** The Record */
	private int p_Record_ID = 0;

	private String mustBeStocked;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("MustBeStocked"))
				mustBeStocked = (String) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_Record_ID = getRecord_ID();
	} //prepare

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */

	protected String doIt() throws Exception {
		String sql;
		String sqlupd;
		String sqlins;
		String sqldel;
		int cntu = 0;
		int cntd = 0;
		int cnti = 0;
		int totu = 0;
		int toti = 0;
		int totd = 0;
		int nextNo;
		boolean isCreated = false;
		boolean processed = false;
		int ad_Client_ID = 0;
		int ad_Org_ID = 0;

		log.info("Search fields in M_Production");

		/**
		 * Get Info + Lock
		 */
		sql = " SELECT IsCreated " + ", Processed " + ", AD_Client_ID "
				+ ", AD_Org_ID " + " FROM M_Production "
				+ " WHERE M_Production_ID = " + p_Record_ID;
		PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			isCreated = rs.getString("IsCreated").equals("Y");
			processed = rs.getString("Processed").equals("Y");
			ad_Client_ID = rs.getInt("AD_Client_ID");
			ad_Org_ID = rs.getInt("AD_Org_ID");
		}
		rs.close();
		pstmt.close();
		pstmt = null;

		/**
		 * No Action
		 */
		if (processed) {
			log.info("Already Posted");
			return "@AlreadyPosted@";
		}

		/***********************************************************************
		 * Create Lines
		 */
		if (!isCreated) {
			// For every Production Plan
			sql = "SELECT m_productionplan_id " + " ,ad_client_id "
					+ " ,ad_org_id " + " ,isactive " + " ,created "
					+ " ,createdby " + " ,updated  " + " ,updatedby  "
					+ " ,m_production_id " + " ,line " + " ,m_product_id "
					+ " ,productionqty " + " ,m_locator_id  "
					+ " ,description  " + " ,processed  "
					+ " FROM	M_ProductionPlan " + " WHERE	M_Production_ID= "
					+ p_Record_ID + " ORDER BY Line, M_Product_ID";
			PreparedStatement curpp = null;
			curpp = DB.prepareStatement(sql, get_TrxName());
			ResultSet pp = curpp.executeQuery();
			while (pp.next()) {
				//	Delete prior lines
				sqldel = "DELETE M_ProductionLine "
						+ "WHERE M_ProductionPlan_ID ="
						+ pp.getInt("M_ProductionPlan_ID");

				cntd = DB.executeUpdate(sqldel, get_TrxName());
				if (cntd == -1)
					raiseError("Deleting Production Line:ERROR", sqldel);
				totd += cntd;

				//  OriginLine
				int line = 100;
				//	Getsequenses
				nextNo = MSequence.getNextID(ad_Client_ID, "M_ProductionLine",
						get_TrxName());
				//  INSERT IN M_ProductionLine
				sqlins = "INSERT INTO M_ProductionLine "
						+ "(M_ProductionLine_ID " + ", M_ProductionPlan_ID"
						+ ", Line  " + ", AD_Client_ID " + ", AD_Org_ID "
						+ ", IsActive " + ", Created " + ", CreatedBy "
						+ ", Updated " + ", UpdatedBy " + ", M_Product_ID "
						+ ", MovementQty " + ", M_Locator_ID "
						+ ", Description) " + " VALUES ( " + nextNo + ", "
						+ pp.getInt("M_ProductionPlan_ID") + ", " + line + ", "
						+ pp.getInt("ad_client_id") + ", "
						+ pp.getInt("AD_Org_ID") + ", 'Y' " + ", sysdate "
						+ ", 0 " + ", sysdate " + ", 0 " + ", "
						+ pp.getInt("M_Product_ID") + ", "
						+ pp.getInt("ProductionQty") + ", "
						+ pp.getInt("M_Locator_ID") + ", '"
						+ pp.getString("Description") + "')";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				if (cnti == -1)
					raiseError("INSERT IN M_ProductionLine", sqlins);
				toti += cnti;
				//	Create First Level
				sql = " SELECT m_product_bom_id, " + "ad_client_id, "
						+ "ad_org_id, " + "isactive, " + "created, "
						+ "createdby, " + "updated, " + "updatedby, "
						+ "line, " + "m_product_id, " + "m_productbom_id, "
						+ "bomqty, " + "description, " + "bomtype "
						+ " FROM	M_Product_BOM " + " WHERE M_Product_ID="
						+ pp.getInt("M_product_id") + " ORDER BY Line";
				PreparedStatement curbom = null;
				curbom = DB.prepareStatement(sql, get_TrxName());
				ResultSet bom = curbom.executeQuery();
				while (bom.next()) {
					line = line + 100;
					nextNo = MSequence.getNextID(pp.getInt("ad_client_id"),
							"M_ProductionLine", get_TrxName());
					sqlins = "INSERT INTO M_ProductionLine "
							+ "(M_ProductionLine_ID, M_ProductionPlan_ID, Line, "
							+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy, "
							+ "M_Product_ID, MovementQty, M_Locator_ID) "
							+ "VALUES "
							+ "("
							+ nextNo
							+ ","
							+ pp.getInt("M_ProductionPlan_ID")
							+ ","
							+ line
							+ ","
							+ pp.getInt("AD_Client_ID")
							+ ","
							+ pp.getInt("AD_Org_ID")
							+ ",'Y',SysDate,0,SysDate,0"
							+ ","
							+ bom.getInt("M_ProductBOM_ID")
							+ ","
							+ (-pp.getInt("ProductionQty") * bom
									.getInt("BOMQty")) + ","
							+ pp.getInt("M_Locator_ID") + ")";
					cnti = DB.executeUpdate(sqlins, get_TrxName());
					if (cnti == -1)
						raiseError("INSERT IN M_ProductionLine First Level",
								sqlins);
					toti += cnti;
				}
				bom.close();
				curbom.close();
				curbom = null;

				//	While we have BOMs
				while (true) {
					// Are there non-stored BOMs to list details?
					sql = "   SELECT COUNT(*) " + "  FROM M_ProductionLine pl"
							+ "     , M_Product p "
							+ "  WHERE pl.M_Product_ID=p.M_Product_ID "
							+ "  AND pl.M_ProductionPlan_ID="
							+ pp.getInt("M_ProductionPlan_ID")
							+ "  AND pl.Line<>100	" //	Origin Line
							+ "  AND p.IsBOM='Y' AND " + "  p.IsStocked='N'";

					PreparedStatement cntlp = null;
					cntlp = DB.prepareStatement(sql, get_TrxName());
					ResultSet ct = cntlp.executeQuery();
					int countNo = 0;
					if (ct.next())
						countNo = ct.getInt(1);
					ct.close();
					cntlp.close();
					cntlp = null;

					//	Nothing to do
					if (countNo == 0)
						break;

					// Resolve BOMs in ProductLine which are not stocked
					sql = " SELECT pl.M_ProductionLine_ID, "
							+ " pl.Line, "
							+ " pl.M_Product_ID,"
							+ " pl.MovementQty  "
							+ " FROM   M_ProductionLine pl, "
							+ "        M_Product p  "
							+ " WHERE  pl.M_ProductionPlan_ID = "
							+ pp.getInt("M_ProductionPlan_ID")
							+ " AND    pl.M_Product_ID        = p.M_Product_ID "
							+ " AND    pl.Line<>100	 " // Origin Line
							+ " AND    p.IsBOM='Y' AND p.IsStocked='N' ";
					PreparedStatement curPLineBOM = null;
					curPLineBOM = DB.prepareStatement(sql, get_TrxName());
					ResultSet pl = curPLineBOM.executeQuery();
					while (pl.next()) {
						line = pl.getInt("line");

						// Resolve BOM Line in product line
						sql = " SELECT m_product_bom_id, " + "ad_client_id, "
								+ "ad_org_id, " + "isactive, " + "created, "
								+ "createdby, " + "updated, " + "updatedby, "
								+ "line, " + "m_product_id, "
								+ "m_productbom_id, " + "bomqty, "
								+ "description, " + "bomtype "
								+ " FROM	M_Product_BOM "
								+ " WHERE	M_Product_ID="
								+ pl.getInt("M_Product_ID") + " ORDER BY Line";
						PreparedStatement curbom1 = null;
						curbom1 = DB.prepareStatement(sql, get_TrxName());
						ResultSet bom1 = curbom1.executeQuery();
						while (bom1.next()) {
							line = line + 10;
							nextNo = MSequence.getNextID(pp
									.getInt("ad_client_id"),
									"M_ProductionLine", get_TrxName());
							sqlins = " INSERT INTO M_ProductionLine "
									+ " (M_ProductionLine_ID, M_ProductionPlan_ID, Line, "
									+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy, "
									+ " M_Product_ID, MovementQty, M_Locator_ID) "
									+ " VALUES "
									+ "("
									+ nextNo
									+ ","
									+ pp.getInt("M_ProductionPlan_ID")
									+ ","
									+ line
									+ ","
									+ pp.getInt("AD_Client_ID")
									+ ","
									+ pp.getInt("AD_Org_ID")
									+ ",'Y',SysDate,0,SysDate,0"
									+ ","
									+ bom1.getInt("M_ProductBOM_ID")
									+ ","
									+ (pl.getInt("MovementQty") * bom1
											.getInt("BOMQty")) + ","
									+ pp.getInt("M_Locator_ID") + ")";
							cnti = DB.executeUpdate(sqlins, get_TrxName());
							if (cnti == -1)
								raiseError(
										"INSERT IN M_ProductionLine For Resolve BOM Line in product line",
										sqlins);
							toti += cnti;

						}
						bom1.close();
						curbom1.close();
						curbom1 = null;

						//Delete BOM line
						sqldel = " DELETE  M_ProductionLine "
								+ " WHERE M_ProductionLine_ID= "
								+ pl.getInt("M_ProductionLine_ID");

						cntd = DB.executeUpdate(sqldel, get_TrxName());
						if (cntd == -1)
							raiseError("Delete BOM line", sqldel);
						totd += cntd;
					}
					pl.close();
					curPLineBOM.close();
					curPLineBOM = null;
				}
			}
			pp.close();
			curpp.close();
			curpp = null;

			// Modifying locator to have sufficient stock
			// Indicate that it is Created
			sqlupd = " UPDATE M_Production " + " SET IsCreated='Y' "
					+ " WHERE M_Production_ID=" + p_Record_ID;
			cntu = DB.executeUpdate(sqlupd, get_TrxName());
			if (cntu == -1)
				raiseError("Modifying locator to have sufficient stock", sqlupd);
			totu += cntu;

			/*******************************************************************
			 * Post Lines
			 */
		} else {

			// All Production Lines
			sql = " SELECT pl.M_ProductionLine_ID, pl.AD_Client_ID, pl.AD_Org_ID,p.MovementDate, "
					+ " pl.M_Product_ID, pl.M_AttributeSetInstance_ID, pl.MovementQty,pl.M_Locator_ID "
					+ " FROM M_Production p, M_ProductionLine pl, M_ProductionPlan pp "
					+ " WHERE p.M_Production_ID=pp.M_Production_ID "
					+ " AND pp.M_ProductionPlan_ID=pl.M_ProductionPlan_ID "
					+ " AND pp.M_Production_ID= "
					+ p_Record_ID
					+ " ORDER BY pp.Line, pl.Line";

			PreparedStatement CUR_PL_Post = null;

			CUR_PL_Post = DB.prepareStatement(sql, get_TrxName());
			ResultSet pl = CUR_PL_Post.executeQuery();
			while (pl.next()) {
				sql = " select bomQtyOnHand (" + pl.getInt("M_Product_ID")
						+ ", null," + pl.getInt("M_Locator_ID") + ") FROM DUAL";
				PreparedStatement cnsql = null;
				cnsql = DB.prepareStatement(sql, get_TrxName());
				ResultSet cs = cnsql.executeQuery();
				int countTo = 0;
				if (cs.next())
					countTo = cs.getInt(1);
				cs.close();
				cnsql.close();
				cnsql = null;

				//	Check Stock levels for reductions
				if ((pl.getInt("MovementQty") < 0)
						&& ((countTo) + pl.getInt("MovementQty") < 0))

				{

					DB.rollback(true, get_TrxName());

					sql = " SELECT '@NotEnoughStocked@: ' || Name	"//INTO
							// Message
							+ " FROM M_Product WHERE M_Product_ID="
							+ pl.getInt("M_Product_ID");
					PreparedStatement cnMess = null;
					cnMess = DB.prepareStatement(sql, get_TrxName());
					ResultSet cm = cnMess.executeQuery();
					String varmess = null;
					if (cm.next())
						varmess = cm.getString(1);
					cm.close();
					cnMess.close();
					cnMess = null;
					return varmess;

				}

				//	Adjust Quantity at Location
				sqlupd = " UPDATE M_Storage " + " SET	QtyOnHand = QtyOnHand + "
						+ pl.getInt("MovementQty") + ","
						+ " Updated = SysDate " + " WHERE	M_Locator_ID = "
						+ pl.getInt("M_Locator_ID")
						+ " AND   M_AttributeSetInstance_ID = COALESCE("
						+ pl.getInt("M_AttributeSetInstance_ID") + ",0)"
						+ " AND	M_Product_ID =" + pl.getInt("M_Product_ID");
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError("Adjust Quantity at Location", sqlupd);
				totu += cntu;

				//    Product not on Stock yet
				if (cntu == 0) {
					sqlins = "INSERT INTO M_Storage "
							+ " (M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID, "
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
							+ " QtyOnHand, QtyReserved, QtyOrdered) "
							+ " VALUES " + "(" + pl.getInt("M_Product_ID")
							+ "," + pl.getInt("M_Locator_ID") + ", COALESCE("
							+ pl.getInt("M_AttributeSetInstance_ID") + ",0), "
							+ pl.getInt("AD_Client_ID") + ","
							+ pl.getInt("AD_Org_ID")
							+ ", 'Y', SysDate, 0, SysDate, 0, "
							+ pl.getInt("MovementQty") + ", 0, 0)";
					cnti = DB.executeUpdate(sqlins, get_TrxName());
					if (cnti == -1)
						raiseError(" Product not on Stock yet", sqlins);
					toti += cnti;

				}

				//    Create Transaction Entry
				nextNo = MSequence.getNextID(pl.getInt("AD_Org_ID"),
						"M_Transaction", get_TrxName());
				sqlins = " INSERT INTO M_Transaction"
						+ " (M_Transaction_ID, M_ProductionLine_ID,"
						+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
						+ " MovementType, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,"
						+ " MovementDate, MovementQty)" + " VALUES "
						+ "(?,?,?,?,'Y',SysDate,0,SysDate,0,"
						+ "'P+',?,?,COALESCE(?,0)," // not distinguishing
						// between
						// assemby/disassembly
						+ "?,?)";
				pstmt = DB.prepareStatement(sqlins,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE, get_TrxName());
				pstmt.setInt(1, nextNo);
				pstmt.setInt(2, pl.getInt("M_ProductionLine_ID"));
				pstmt.setInt(3, pl.getInt("AD_Client_ID"));
				pstmt.setInt(4, pl.getInt("AD_Org_ID"));
				pstmt.setInt(5, pl.getInt("M_Locator_ID"));
				pstmt.setInt(6, pl.getInt("M_Product_ID"));
				pstmt.setInt(7, pl.getInt("M_AttributeSetInstance_ID"));
				pstmt.setTimestamp(8, pl.getTimestamp("MovementDate"));
				pstmt.setDouble(9, pl.getDouble("MovementQty"));
				cnti = pstmt.executeUpdate();
				//
				if (cnti == -1)
					raiseError("Create Transaction Entry", sqlins);
				toti += cnti;

				//    Update M_ProductionLine
				sqlupd = " UPDATE M_ProductionLine " + " SET Processed='Y' "
						+ " WHERE M_ProductionLine_ID="
						+ pl.getInt("M_ProductionLine_ID");
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError("Update M_ProductionLine ", sqlupd);
				totu += cntu;

			}
			pl.close();
			CUR_PL_Post.close();
			CUR_PL_Post = null;

			//	Indicate that we are done
			sqlupd = "UPDATE	M_Production " + "SET Processed='Y' "
					+ "WHERE	M_Production_ID=" + p_Record_ID;
			cntu = DB.executeUpdate(sqlupd, get_TrxName());
			if (cntu == -1)
				raiseError("Indicate that we are done ", sqlupd);
			totu += cntu;

			sqlupd = " UPDATE M_ProductionPlan " + " SET Processed='Y' "
					+ " WHERE M_Production_ID=" + p_Record_ID;
			cntu = DB.executeUpdate(sqlupd, get_TrxName());
			if (cntu == -1)
				raiseError(
						"Update the indicator of Processed in M_ProductionPlan",
						sqlupd);
			totu += cntu;
		}

		//	Only commit when entire job successful
		log.fine("Committing ...");
		DB.commit(true, get_TrxName());

		return "OK";

	} // del doIt

	private void raiseError(String string, String sql) throws Exception {
		DB.rollback(false, get_TrxName());
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += sql;
		throw new AdempiereUserError(msg);
	}

} // M_Production_Run

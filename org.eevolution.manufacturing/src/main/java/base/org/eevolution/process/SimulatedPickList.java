/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.model.X_M_Product;
import org.compiere.model.X_M_Warehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.X_PP_Order_BOMLine;
import org.eevolution.model.X_PP_Product_BOM;
import org.eevolution.model.X_PP_Product_BOMLine;
import org.eevolution.model.X_T_BOMLine;

/**
 * Simulated Pick List
 * 
 * @author victor.perez@e-evolution.com, www.e-evoluton.com
 * 
 */
public class SimulatedPickList extends SvrProcess {
	private static final Properties ctx = Env.getCtx();
	private int p_M_Product_ID = 0;
	private int p_M_Warehouse_ID = 0;
	private Timestamp p_DateTrx;
	private BigDecimal p_QtyRequiered = Env.ONE;
	private String p_BackflushGroup = null;
	private int p_LevelNo = 1;
	private int LevelNo = 1;
	private int SeqNo = 0;
	private String levels = new String("....................");
	private int AD_PInstance_ID = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {

		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals(X_M_Product.COLUMNNAME_M_Product_ID)) {
				p_M_Product_ID = para.getParameterAsInt();
			} else if (name.equals(X_M_Warehouse.COLUMNNAME_M_Warehouse_ID)) {
				p_M_Warehouse_ID = para.getParameterAsInt();
			} else if (name.equals("DateTrx")) {
				p_DateTrx = (Timestamp) para.getParameter();
			} else if (name.equals(X_PP_Order_BOMLine.COLUMNNAME_QtyRequired)) {
				p_QtyRequiered = (BigDecimal) para.getParameter();
			} else if (name
					.equals(X_PP_Product_BOMLine.COLUMNNAME_BackflushGroup)) {
				p_BackflushGroup = (String) para.getParameter();
			} else if (name.equals(X_T_BOMLine.COLUMNNAME_LevelNo)) {
				p_LevelNo = para.getParameterAsInt();
			} else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception {
		AD_PInstance_ID = getAD_PInstance_ID();
		final MProduct product = MProduct.get(getCtx(), p_M_Product_ID);
		if (!product.isVerified())
			throw new AdempiereException(
					"Product BOM Configuration not verified. Please verify the product first - "
							+ product.getValue()); // TODO: translate

		try {
			loadBOM();
		} catch (Exception e) {
			log.log(Level.SEVERE, "PrintBOM", e.toString());
			throw new Exception(e.getLocalizedMessage());
		}

		return "@OK@";
	} // doIt

	public String getParamenterInfo(String name) {
		final String sql = "SELECT ip.Info FROM AD_PInstance_Para ip"
				+ " WHERE ip.AD_PInstance_ID=? AND ip.ParameterName=?";
		return DB.getSQLValueString(get_TrxName(), sql, getProcessInfo()
				.getAD_PInstance_ID(), name);
	}

	public String getParamenterName(String columnName) {
		boolean trl = !Env.isBaseLanguage(ctx, "AD_Process_Para");
		String sql = null;
		if (trl) {
			sql = "SELECT pp.Name FROM AD_Process_Para pp "
					+ "WHERE pp.IsActive='Y' "
					+ " AND pp.AD_Process_ID=? AND pp.ColumnName=?";
		} else {
			sql = "SELECT ppt.Name "
					+ "FROM AD_Process_Para pp , AD_Process_Para_Trl ppt "
					+ "WHERE pp.IsActive='Y'"
					+ " AND pp.AD_Process_Para_ID=ppt.AD_Process_Para_ID"
					+ " AND pp.AD_Process_ID=? AND pp.ColumnName=?";
		}
		return DB.getSQLValueString(get_TrxName(), sql, getProcessInfo()
				.getAD_Process_ID(), columnName);
	}

	/**
	 * Action: Fill Tree with all nodes
	 */
	private void loadBOM() throws Exception {
		int count = 0;
		if (p_M_Product_ID == 0)
			raiseError("Error: ", "Product ID not found");

		MProduct product = new MProduct(getCtx(), p_M_Product_ID, get_TrxName());
		X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
		tboml.setAD_Org_ID(product.getAD_Org_ID());
		tboml.setPP_Product_BOM_ID(0);
		tboml.setPP_Product_BOMLine_ID(0);
		tboml.setSel_Product_ID(p_M_Product_ID);
		tboml.setM_Product_ID(p_M_Product_ID);
		tboml.setSel_Product_ID(p_M_Product_ID);
		tboml.setDateTrx(p_DateTrx);
		tboml.setImplosion(false);
		tboml.setLevelNo(0);
		tboml.setLevels("0");
		tboml.setQtyBOM(Env.ONE);
		tboml.setQtyRequired(p_QtyRequiered);
		tboml.setM_Warehouse_ID(p_M_Warehouse_ID);
		tboml.setSeqNo(0);
		tboml.setAD_PInstance_ID(AD_PInstance_ID);
		tboml.saveEx();

		final String whereClause = MPPProductBOM.COLUMNNAME_M_Product_ID + "=?";
		List<MPPProductBOM> boms = new Query(getCtx(),
				X_PP_Product_BOM.Table_Name, whereClause, get_TrxName())
				.setClient_ID().setOnlyActiveRecords(true)
				.setParameters(p_M_Product_ID).list();

		for (MPPProductBOM bom : boms) {
			if (bom.isValidFromTo(p_DateTrx)) {
				parentExplotion(bom.get_ID(), p_QtyRequiered);
				++count;
			}
		}

		if (count == 0)
			raiseError("Error: ", "Product is not a BOM");
	}

	/**
	 * Generate an Explosion for this BOM
	 * 
	 * @param PP_Product_BOMLine_ID
	 *            ID BOM Line
	 */
	public void parentExplotion(int PP_Product_BOM_ID, BigDecimal qtyRequiered)
			throws Exception {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(PP_Product_BOM_ID);

		final StringBuilder whereClause = new StringBuilder(
				MPPProductBOMLine.COLUMNNAME_PP_Product_BOM_ID).append("=?");

		if (p_BackflushGroup != null) {
			whereClause.append(" AND ")
					.append(MPPProductBOMLine.COLUMNNAME_BackflushGroup)
					.append("LIKE ?");
			parameters.add(p_BackflushGroup);
		}

		List<MPPProductBOMLine> bomLines = new Query(getCtx(),
				MPPProductBOMLine.Table_Name, whereClause.toString(),
				get_TrxName()).setClient_ID().setOnlyActiveRecords(true)
				.setParameters(parameters)
				.setOrderBy(MPPProductBOMLine.COLUMNNAME_Line).list();

		for (MPPProductBOMLine line : bomLines) {
			if (line.isValidFromTo(p_DateTrx)) {
				SeqNo += 1;
				MProduct product = new MProduct(getCtx(),
						line.getM_Product_ID(), get_TrxName());
				X_T_BOMLine tboml = new X_T_BOMLine(ctx, 0, null);
				tboml.setAD_Org_ID(product.getAD_Org_ID());
				tboml.setPP_Product_BOM_ID(PP_Product_BOM_ID);
				tboml.setPP_Product_BOMLine_ID(line.get_ID());
				tboml.setM_Product_ID(line.getM_Product_ID());
				tboml.setLevelNo(LevelNo);
				tboml.setDateTrx(p_DateTrx);
				tboml.setLevels(levels.substring(0, LevelNo) + LevelNo);
				tboml.setSeqNo(SeqNo);
				tboml.setAD_PInstance_ID(AD_PInstance_ID);
				tboml.setSel_Product_ID(p_M_Product_ID);
				tboml.setQtyBOM(line.getQty(true));
				tboml.setQtyRequired(qtyRequiered.multiply(line.getQty(true)));
				tboml.setM_Warehouse_ID(p_M_Warehouse_ID);
				tboml.setImplosion(false);
				tboml.saveEx();
				component(line.getM_Product_ID(), tboml.getQtyBOM());
			}
		}
	}

	/**
	 * Find if this product as component
	 * 
	 * @param M_Product_ID
	 *            ID Component
	 */
	public void component(int M_Product_ID, BigDecimal qtyRequiered)
			throws Exception {

		if (LevelNo == p_LevelNo)
			return;

		String value = DB.getSQLValueString(get_TrxName(),
				"SELECT Value FROM M_Product WHERE M_Product_ID=?",
				M_Product_ID);

		final StringBuilder whereClause = new StringBuilder(
				MPPProductBOM.COLUMNNAME_Value).append("=? AND ")
				.append(MPPProductBOM.COLUMNNAME_M_Product_ID).append("=?");
		List<MPPProductBOM> boms = new Query(getCtx(),
				MPPProductBOM.Table_Name, whereClause.toString(), get_TrxName())
				.setClient_ID().setOnlyActiveRecords(true)
				.setParameters(value, M_Product_ID).list();

		boolean level = false;
		for (MPPProductBOM bom : boms) {
			if (bom.isValidFromTo(p_DateTrx)) {
				if (!level)
					LevelNo += 1;
				level = true;
				parentExplotion(bom.get_ID(), qtyRequiered);
				LevelNo -= 1;
			}
		}
	}

	private void raiseError(String string, String hint) throws Exception {
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += hint;
		throw new Exception(msg);
	}

}

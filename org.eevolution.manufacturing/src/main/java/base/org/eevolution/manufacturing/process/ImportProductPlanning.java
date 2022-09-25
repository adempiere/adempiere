/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Contributor: victor.perez@e-evolution.com                                  *
 *****************************************************************************/
package org.eevolution.manufacturing.process;

import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_DD_NetworkDistribution;
import org.adempiere.core.domains.models.I_PP_Product_BOM;
import org.adempiere.core.domains.models.I_S_Resource;
import org.adempiere.core.domains.models.X_I_ProductPlanning;
import org.adempiere.core.domains.models.X_S_Resource;
import org.compiere.model.MColumn;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.manufacturing.model.MPPProductPlanning;

/**
 * Import Product Planning
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author alberto.juarez@e-evolution.com, www.e-evolution.com <li>Create new
 *         importer for Planning Data <li>
 *         http://sourceforge.net/support/tracker.php?aid=2952245
 * @version
 */
public class ImportProductPlanning extends SvrProcess {
	/** Is Imported */
	private boolean isImported = false;
	/** Delete old Imported */
	private boolean p_DeleteOldImported = false;
	/** Import if no Errors */
	private boolean p_IsImportOnlyNoErrors = true;
	/** Record Import **/
	private int imported = 0;
	/** No import records */
	private int notimported = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] paramaters = getParameter();
		for (ProcessInfoParameter para : paramaters) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("IsImportOnlyNoErrors"))
				p_IsImportOnlyNoErrors = "Y".equals(para.getParameter());
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para.getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message
	 * @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {

		if (p_DeleteOldImported) {
			int no = 0;
			for (X_I_ProductPlanning ipp : getRecords(true, false)) {
				ipp.deleteEx(true);
				no++;
			}
			log.fine("Delete Old Impored =" + no);
		}

		// fill IDs using Search Key
		fillIDValues();
		// import record
		importRecords();
		return "Imported: " + imported + ", Not imported: " + notimported;
		//
	} // doIt

	/**
	 * import record using X_I_ProductPlanning table
	 */
	private void importRecords() {
		for (X_I_ProductPlanning ipp : getRecords(false, p_IsImportOnlyNoErrors)) {
			isImported = false;
			MPPProductPlanning pp = importProductPlanning(ipp);
			if (pp == null)
				isImported = false;

			if (isImported) {
				ipp.setPP_Product_Planning_ID(pp.getPP_Product_Planning_ID());
				ipp.setI_IsImported(true);
				ipp.setProcessed(true);
				ipp.setI_ErrorMsg("");
				ipp.saveEx();
				imported++;
			} else {
				ipp.setI_IsImported(false);
				ipp.setProcessed(true);
				ipp.saveEx();
				notimported++;
			}
		}
	}

	/**
	 * import record using X_I_ProductPlanning table
	 * 
	 * @param ipp
	 *            X_I_ProductPlanning
	 */
	private MPPProductPlanning importProductPlanning(X_I_ProductPlanning ipp) {
		try {

			MPPProductPlanning pp = null;

			if (ipp.getPP_Product_Planning_ID() > 0)
				pp = new MPPProductPlanning(getCtx(),
						ipp.getPP_Product_Planning_ID(), get_TrxName());
			else {
				pp = MPPProductPlanning.get(getCtx(), ipp.getAD_Client_ID(),
						ipp.getAD_Org_ID(), ipp.getM_Warehouse_ID(),
						ipp.getS_Resource_ID(), ipp.getM_Product_ID(),
						get_TrxName());
			}

			if (pp == null || pp.get_ID() <= 0) {
				pp = new MPPProductPlanning(Env.getCtx(), 0, get_TrxName());
				pp.setAD_Org_ID(ipp.getAD_Org_ID());
				pp.setM_Product_ID(ipp.getM_Product_ID());
				pp.setS_Resource_ID(ipp.getS_Resource_ID());
				pp.setM_Warehouse_ID(ipp.getM_Warehouse_ID());
				pp.setIsRequiredDRP(false);
				pp.setIsRequiredMRP(false);
			}

			fillValue(pp, ipp);

			if (ipp.getC_BPartner_ID() > 0 && ipp.getVendorProductNo() != null) {
				importPurchaseProductPlanning(ipp);
			}

			pp.saveEx();
			isImported = true;
			return pp;
		} catch (Exception e) {
			ipp.setI_ErrorMsg(e.getMessage());
			isImported = false;
			return null;
		}
	}

	/**
	 * Import record using X_I_ProductPlanning table
	 * 
	 * @param ipp
	 *            X_I_ProductPlanning
	 */
	private void importPurchaseProductPlanning(X_I_ProductPlanning ipp) {
		MProduct product = MProduct.get(getCtx(), ipp.getM_Product_ID());
		if (product.isPurchased()) {
			final StringBuffer whereClause = new StringBuffer();
			whereClause.append(MProductPO.COLUMNNAME_M_Product_ID).append(
					"=? AND ");
			whereClause.append(MProductPO.COLUMNNAME_C_BPartner_ID)
					.append("=?");
			MProductPO productPO = new Query(getCtx(), MProductPO.Table_Name,
					whereClause.toString(), get_TrxName())
					.setClient_ID()
					.setParameters(ipp.getM_Product_ID(),
							ipp.getC_BPartner_ID()).first();

			if (productPO == null) {
				productPO = new MProductPO(getCtx(), 0, get_TrxName());
				productPO.setM_Product_ID(ipp.getM_Product_ID());
				productPO.setC_BPartner_ID(ipp.getC_BPartner_ID());

			}

			productPO.setAD_Org_ID(ipp.getAD_Org_ID());
			productPO.setOrder_Min(ipp.getOrder_Min());
			productPO.setOrder_Pack(ipp.getOrder_Pack());
			productPO.setDeliveryTime_Promised(ipp.getDeliveryTime_Promised()
					.intValue());
			productPO.setVendorProductNo(ipp.getVendorProductNo());
			productPO.saveEx();
		}

	}

	/**
	 * fill MPPProductPlanning using I_ProductPlanning's values
	 * 
	 * @param pp
	 *            MPPProductPlanning
	 * @param ipp
	 *            I_ProductPlanning
	 */
	private void fillValue(MPPProductPlanning pp, X_I_ProductPlanning ipp) {

		for (MColumn col : getProductPlanningColumns()) {
			if (!pp.is_new() && !col.isUpdateable()
					&& ipp.get_ColumnIndex(col.getColumnName()) > 0)
				continue;
			
			if(MPPProductPlanning.COLUMNNAME_IsRequiredDRP.equals(col.getColumnName()) || 
			   MPPProductPlanning.COLUMNNAME_IsRequiredMRP.equals(col.getColumnName()) ||
			   MPPProductPlanning.COLUMNNAME_PP_Product_Planning_ID.equals(col.getColumnName()))
				continue;

			if (ipp.get_Value(col.getColumnName()) != null
					&& pp.get_Value(col.getColumnName()) != null
					&& pp.get_Value(col.getColumnName()).equals(
							ipp.get_Value(col.getColumnName())))
				continue;

			pp.set_ValueOfColumn(col.getColumnName(),
					ipp.get_Value(col.getColumnName()));

		}
		isImported = true;
		return;
	}

	/**
	 * get Product Planning Columns
	 * 
	 * @return array MColumn
	 */
	private MColumn[] getProductPlanningColumns() {
		return MTable.get(getCtx(), MPPProductPlanning.Table_Name).getColumns(
				false);
	}

	/**
	 * fill IDs values based on Search Key
	 */
	private void fillIDValues() {
		for (X_I_ProductPlanning ppi : getRecords(false, p_IsImportOnlyNoErrors)) {

			int AD_Org_ID = 0;
			if (ppi.getAD_Org_ID() > 0)
				AD_Org_ID = getID(MOrg.Table_Name, "AD_Org_ID = ?",
						ppi.getAD_Org_ID());

			if (AD_Org_ID <= 0 && ppi.getOrgValue() != null) {
				AD_Org_ID = getID(MOrg.Table_Name, "Value = ?",
						ppi.getOrgValue());
				ppi.setAD_Org_ID(AD_Org_ID);
			} else
				ppi.setAD_Org_ID(AD_Org_ID);

			int C_BPartner_ID = 0;
			if (ppi.getC_BPartner_ID() == 0)
				C_BPartner_ID = getID(I_C_BPartner.Table_Name,
						I_C_BPartner.COLUMNNAME_C_BPartner_ID + "=?",
						ppi.getC_BPartner_ID());

			if (C_BPartner_ID <= 0 && ppi.getBPartner_Value() != null) {
				C_BPartner_ID = getID(I_C_BPartner.Table_Name,
						I_C_BPartner.COLUMNNAME_Value + "=?",
						ppi.getBPartner_Value());
				ppi.setC_BPartner_ID(C_BPartner_ID);
			} else
				ppi.setC_BPartner_ID(C_BPartner_ID);

			// Product
			int M_Product_ID = 0;
			if (ppi.getM_Product_ID() > 0)
				M_Product_ID = getID(MProduct.Table_Name, "M_Product_ID = ?",
						ppi.getM_Product_ID());

			if (M_Product_ID <= 0 && ppi.getProductValue() != null) {
				M_Product_ID = getID(MProduct.Table_Name, "Value = ?",
						ppi.getProductValue());
				ppi.setM_Product_ID(M_Product_ID);
			} else
				ppi.setM_Product_ID(M_Product_ID);

			// Warehouse
			int M_Warehouse_ID = 0;
			if (ppi.getM_Warehouse_ID() > 0)
				M_Warehouse_ID = getID(MWarehouse.Table_Name,
						"M_Warehouse_ID = ?", ppi.getM_Warehouse_ID());

			if (M_Warehouse_ID <= 0 && ppi.getWarehouseValue() != null) {
				M_Warehouse_ID = getID(MWarehouse.Table_Name, "Value = ?",
						ppi.getWarehouseValue());
				ppi.setM_Warehouse_ID(M_Warehouse_ID);
			} else
				ppi.setM_Warehouse_ID(M_Warehouse_ID);

			int DD_NetworkDistribution_ID = 0;
			if (ppi.getDD_NetworkDistribution_ID() > 0)
				DD_NetworkDistribution_ID = getID(
						I_DD_NetworkDistribution.Table_Name,
						I_DD_NetworkDistribution.COLUMNNAME_DD_NetworkDistribution_ID
								+ " = ?", ppi.getDD_NetworkDistribution_ID());

			if (DD_NetworkDistribution_ID <= 0
					&& ppi.getNetworkDistributionValue() != null) {
				DD_NetworkDistribution_ID = getID(
						I_DD_NetworkDistribution.Table_Name,
						I_DD_NetworkDistribution.COLUMNNAME_Value + "= ?",
						ppi.getNetworkDistributionValue());
				ppi.setDD_NetworkDistribution_ID(DD_NetworkDistribution_ID);
			} else
				ppi.setDD_NetworkDistribution_ID(DD_NetworkDistribution_ID);

			int PP_Product_BOM_ID = 0;
			if (ppi.getPP_Product_BOM_ID() > 0)
				PP_Product_BOM_ID = getID(I_PP_Product_BOM.Table_Name,
						I_PP_Product_BOM.COLUMNNAME_PP_Product_BOM_ID + "= ?",
						ppi.getPP_Product_BOM_ID());

			if (PP_Product_BOM_ID <= 0 && ppi.getProduct_BOM_Value() != null) {
				PP_Product_BOM_ID = getID(I_PP_Product_BOM.Table_Name,
						I_PP_Product_BOM.COLUMNNAME_Value + "= ?",
						ppi.getProduct_BOM_Value());
				ppi.setPP_Product_BOM_ID(PP_Product_BOM_ID);
			} else
				ppi.setPP_Product_BOM_ID(PP_Product_BOM_ID);

			int S_Resource_ID = 0;
			if (ppi.getS_Resource_ID() > 0)
				S_Resource_ID = getID(I_S_Resource.Table_Name,
						I_S_Resource.COLUMNNAME_S_Resource_ID + "= ?",
						ppi.getS_Resource_ID());

			if (S_Resource_ID <= 0 && ppi.getResourceValue() != null) {
				S_Resource_ID = getID(
						I_S_Resource.Table_Name,
						I_S_Resource.COLUMNNAME_Value
								+ "=? AND "
								+ I_S_Resource.COLUMNNAME_ManufacturingResourceType
								+ "=?", ppi.getResourceValue(),
						X_S_Resource.MANUFACTURINGRESOURCETYPE_Plant);
				ppi.setS_Resource_ID(S_Resource_ID);
			} else
				ppi.setS_Resource_ID(S_Resource_ID);

			ppi.saveEx();

			StringBuffer err = new StringBuffer("");
			if (ppi.getAD_Org_ID() <= 0)
				err.append(" @AD_Org_ID@ @NotFound@,");

			if (ppi.getM_Product_ID() <= 0)
				err.append(" @M_Product_ID@ @NotFound@,");

			// if (ppi.getM_Warehouse_ID() <= 0)
			// err.append(" @M_Warehouse_ID@ @NotFound@,");

			// if (ppi.getC_BPartner_ID() <= 0)
			// err.append(" @S_Resource_ID@ @NotFound@,");

			if (err.toString() != null && err.toString().length() > 0) {
				notimported++;
				ppi.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
				ppi.saveEx();
			}

		}
	}

	/**
	 * get a record's ID
	 * 
	 * @param tableName
	 *            String
	 * @param whereClause
	 *            String
	 * @param values
	 *            Object[]
	 * @return unique record's ID in the table
	 */
	private int getID(String tableName, String whereClause,
			Object... parameters) {
		return new Query(getCtx(), tableName, whereClause, get_TrxName())
				.setParameters(parameters).firstId();
	}

	/**
	 * get all records in X_I_ProductPlanning table
	 * 
	 * @param imported
	 *            boolean
	 * @param isWithError
	 *            boolean
	 * @return List of X_I_ProductPlanning records
	 */
	private List<X_I_ProductPlanning> getRecords(boolean imported,
			boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_ProductPlanning.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_ProductPlanning.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}

		return new Query(getCtx(), X_I_ProductPlanning.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).list();
	}
} // Import Product Planning

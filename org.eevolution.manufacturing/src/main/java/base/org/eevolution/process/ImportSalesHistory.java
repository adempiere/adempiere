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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.model.MProductClass;
import org.adempiere.model.MProductClassification;
import org.adempiere.model.MProductGroup;
import org.compiere.model.MActivity;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MCampaign;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProject;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.model.MSalesRegion;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_C_SalesHistory;
import org.eevolution.model.MSalesHistory;
import org.eevolution.model.X_I_SalesHistory;

/**
 * Import Sales History from I_SalesHistory
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */

public class ImportSalesHistory extends SvrProcess {

	private boolean m_DeleteOldImported = false;
	private boolean m_IsImportOnlyNoErrors = true;
	private boolean isImported = false;
	private int imported = 0;
	private int notimported = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para : parameters) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;

			else if (name.equals("IsImportOnlyNoErrors"))
				m_IsImportOnlyNoErrors = "Y".equals(para.getParameter());
			else if (name.equals("DeleteOldImported"))
				m_DeleteOldImported = "Y".equals(para.getParameter());
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
		if (m_DeleteOldImported) {
			int no = 0;
			for (X_I_SalesHistory saleshistory : getRecords(true, false)) {
				saleshistory.deleteEx(true);
				no++;
			}
			log.fine("Delete Old Impored =" + no);
		}

		fillIDValues();
		importRecords();
		return "Imported: " + imported + ", Not imported: " + notimported;
	} // doIt

	/**
	 * import records using I_SalesHistory table
	 */
	private void importRecords() throws SQLException {
		for (X_I_SalesHistory salesHistoryImport : getRecords(false, m_IsImportOnlyNoErrors)) {
			isImported = false;
			MSalesHistory salesHistory = importMSalesHistory(salesHistoryImport);
			if (salesHistory == null)
				isImported = false;

			if (isImported) {
				salesHistoryImport.setC_SalesHistory_ID(salesHistory.getC_SalesHistory_ID());
				salesHistoryImport.setI_IsImported(true);
				salesHistoryImport.setProcessed(true);
				salesHistoryImport.setI_ErrorMsg("");
				salesHistoryImport.saveEx();
				imported++;
				salesHistoryImport.saveEx();
			} else {
				salesHistoryImport.setI_IsImported(false);
				salesHistoryImport.setProcessed(true);
				salesHistoryImport.saveEx();
				notimported++;
			}
            commitEx();
		}
	}

	/**
	 * Import Sales History using X_I_SalesHistory table
	 * 
	 * @param salesHistoryImporth
	 *            X_I_SalesHistory
	 * @return MSalesHistory Sales History
	 */
	private MSalesHistory importMSalesHistory(X_I_SalesHistory salesHistoryImporth) throws SQLException {
		final String whereClause = I_C_SalesHistory.COLUMNNAME_C_SalesHistory_ID
				+ "= ? ";

		MSalesHistory salesHistory = new Query(Env.getCtx(), I_C_SalesHistory.Table_Name,
				whereClause, get_TrxName()).setClient_ID()
				.setParameters(salesHistoryImporth.getC_SalesHistory_ID()).first();

		if (salesHistory == null) {
			salesHistory = new MSalesHistory(Env.getCtx(), 0, get_TrxName());
			salesHistory.setAD_Org_ID(salesHistoryImporth.getAD_Org_ID());
			salesHistory.setM_Product_ID(salesHistoryImporth.getM_Product_ID());
			salesHistory.setC_BPartner_ID(salesHistoryImporth.getC_BPartner_ID());
			salesHistory.setM_Warehouse_ID(salesHistoryImporth.getM_Warehouse_ID());
		}

		salesHistory.setC_BPartner_Location_ID(salesHistoryImporth.getC_BPartner_Location_ID());
		salesHistory.setC_BP_Group_ID(salesHistoryImporth.getC_BP_Group_ID());
		salesHistory.setSalesRep_ID(salesHistoryImporth.getSalesRep_ID());
		salesHistory.setM_Product_Category_ID(salesHistoryImporth.getM_Product_Category_ID());
		salesHistory.setM_Product_Class_ID(salesHistoryImporth.getM_Product_Class_ID());
		salesHistory.setM_Product_Classification_ID(salesHistoryImporth.getM_Product_Classification_ID());
		salesHistory.setM_Product_Group_ID(salesHistoryImporth.getM_Product_Group_ID());
		salesHistory.setDateInvoiced(salesHistoryImporth.getDateInvoiced());
		salesHistory.setQty(salesHistoryImporth.getQty());
		salesHistory.setTotalInvQty(salesHistoryImporth.getTotalInvQty());
		salesHistory.setCostAmt(salesHistoryImporth.getCostAmt());
		salesHistory.setTotalInvCost(salesHistoryImporth.getTotalInvCost());
		salesHistory.setPriceInvoiced(salesHistoryImporth.getPriceInvoiced());
		salesHistory.setDocumentNo(salesHistoryImporth.getDocumentNo());
		salesHistory.setTotalInvAmt(salesHistoryImporth.getTotalInvAmt());

		salesHistory.setC_Project_ID(salesHistoryImporth.getC_Project_ID());
		salesHistory.setC_ProjectPhase_ID(salesHistoryImporth.getC_ProjectPhase_ID());
		salesHistory.setC_ProjectTask_ID(salesHistoryImporth.getC_ProjectTask_ID());
		salesHistory.setC_Campaign_ID(salesHistoryImporth.getC_Campaign_ID());
		salesHistory.setC_Activity_ID(salesHistoryImporth.getC_Activity_ID());
		salesHistory.setUser1_ID(salesHistoryImporth.getUser1_ID());
		salesHistory.setUser2_ID(salesHistoryImporth.getUser2_ID());
		salesHistory.setUser3_ID(salesHistoryImporth.getUser3_ID());
		salesHistory.setUser4_ID(salesHistoryImporth.getUser4_ID());
		salesHistory.saveEx();


        isImported = true;

		return salesHistory;
	}

	/**
	 * fill IDs values based on Search Key
	 */
	private void fillIDValues() throws SQLException {
		for (X_I_SalesHistory ish : getRecords(false, m_IsImportOnlyNoErrors)) {
			// Organization
			int AD_Org_ID = 0;
			if (ish.getAD_Org_ID() > 0)
				AD_Org_ID = getID(MOrg.Table_Name, "AD_Org_ID = ?",
						ish.getAD_Org_ID());

			if (AD_Org_ID <= 0 && ish.getOrgValue() != null) {
				AD_Org_ID = getID(MOrg.Table_Name, "Value = ?",
						ish.getOrgValue());
				ish.setAD_Org_ID(AD_Org_ID);
			} else
				ish.setAD_Org_ID(AD_Org_ID);

			// BPartner ID
			int C_BPartner_ID = 0;
			if (ish.getC_BPartner_ID() > 0)
				C_BPartner_ID = getID(MBPartner.Table_Name,
						"C_BPartner_ID = ?", ish.getC_BPartner_ID());

			if (C_BPartner_ID <= 0 && ish.getBPartnerValue() != null) {
				C_BPartner_ID = getID(MBPartner.Table_Name, "Value = ?",
						ish.getBPartnerValue());
				ish.setC_BPartner_ID(C_BPartner_ID);
			} else
				ish.setC_BPartner_ID(C_BPartner_ID);

			// BPartner Group
			int C_BP_Group_ID = 0;
			if (ish.getC_BP_Group_ID() > 0)
				C_BP_Group_ID = getID(MBPGroup.Table_Name, "C_BP_Group_ID = ?",
						ish.getC_BP_Group_ID());

			if (C_BP_Group_ID <= 0 && ish.getGroupValue() != null) {
				C_BP_Group_ID = getID(MBPGroup.Table_Name, "Value = ?",
						ish.getGroupValue());
				ish.setC_BP_Group_ID(C_BP_Group_ID);
			} else
				ish.setC_BP_Group_ID(C_BP_Group_ID);

			// Sales Rep ID
			int SalesRep_ID = 0;
			if (ish.getSalesRep_ID() > 0)
				SalesRep_ID = getID(MUser.Table_Name, "AD_User_ID = ?",
						ish.getSalesRep_ID());

			if (SalesRep_ID <= 0 && ish.getSalesRep_Name() != null) {
				SalesRep_ID = getID(MUser.Table_Name, "Name = ?",
						ish.getSalesRep_Name());
				ish.setSalesRep_ID(SalesRep_ID);
			} else
				ish.setSalesRep_ID(SalesRep_ID);

			// Sales Region
			int C_SalesRegion_ID = 0;
			if (ish.getC_SalesRegion_ID() > 0)
				C_SalesRegion_ID = getID(MSalesRegion.Table_Name,
						"C_SalesRegion_ID = ?", ish.getC_SalesRegion_ID());

			if (C_SalesRegion_ID <= 0 && ish.getRegionName() != null) {
				C_SalesRegion_ID = getID(MSalesRegion.Table_Name, "Name = ?",
						ish.getRegionName());
				ish.setC_SalesRegion_ID(C_SalesRegion_ID);
			} else
				ish.setC_SalesRegion_ID(C_SalesRegion_ID);

			// Product
			int M_Product_ID = 0;
			if (ish.getM_Product_ID() > 0)
				M_Product_ID = getID(MProduct.Table_Name, "M_Product_ID = ?",
						ish.getM_Product_ID());

			if (M_Product_ID <= 0 && ish.getProductValue() != null) {
				M_Product_ID = getID(MProduct.Table_Name, "Value = ?",
						ish.getProductValue());
				ish.setM_Product_ID(M_Product_ID);
			} else
				ish.setM_Product_ID(M_Product_ID);

			// Product Category
			int M_Product_Category_ID = 0;
			if (ish.getM_Product_Category_ID() > 0)
				M_Product_Category_ID = getID(MProductCategory.Table_Name,
						"M_Product_Category_ID = ?",
						ish.getM_Product_Category_ID());

			if (M_Product_Category_ID <= 0 && ish.getCategoryName() != null) {
				M_Product_Category_ID = getID(MProductCategory.Table_Name,
						"Name = ?", ish.getCategoryName());
				ish.setM_Product_Category_ID(M_Product_Category_ID);
			} else
				ish.setM_Product_Category_ID(M_Product_Category_ID);

			// Product Classification ID
			int M_Product_Classification_ID = 0;
			if (ish.getM_Product_Classification_ID() > 0)
				M_Product_Classification_ID = getID(
						MProductClassification.Table_Name,
						"M_Product_Classification_ID = ?",
						ish.getM_Product_Classification_ID());
			ish.setM_Product_Classification_ID(M_Product_Classification_ID);

			// Product Class ID
			int M_Product_Class_ID = 0;
			if (ish.getM_Product_Class_ID() > 0)
				M_Product_Class_ID = getID(MProductClass.Table_Name,
						"M_Product_Class_ID = ?", ish.getM_Product_Class_ID());
			ish.setM_Product_Class_ID(M_Product_Class_ID);

			// Product Group
			int M_Product_Group_ID = 0;
			if (ish.getM_Product_Group_ID() > 0)
				M_Product_Group_ID = getID(MProductGroup.Table_Name,
						"M_Product_Group_ID = ?", ish.getM_Product_Group_ID());

			ish.setM_Product_Group_ID(M_Product_Group_ID);

			// Warehouse
			int M_Warehouse_ID = 0;
			if (ish.getM_Warehouse_ID() > 0)
				M_Warehouse_ID = getID(MWarehouse.Table_Name,
						"M_Warehouse_ID = ?", ish.getM_Warehouse_ID());

			if (M_Warehouse_ID <= 0 && ish.getWarehouseValue() != null) {
				M_Warehouse_ID = getID(MWarehouse.Table_Name, "Value = ?",
						ish.getWarehouseValue());
				ish.setM_Warehouse_ID(M_Warehouse_ID);
			} else
				ish.setM_Warehouse_ID(M_Warehouse_ID);

			// Project
			int C_Project_ID = 0;
			if (ish.getC_Project_ID() > 0)
				C_Project_ID = getID(MProject.Table_Name, "C_Project_ID = ?",
						ish.getC_Project_ID());

			if (C_Project_ID <= 0 && ish.getProjectName() != null) {
				C_Project_ID = getID(MProject.Table_Name, "Name = ?",
						ish.getProjectName());
				ish.setC_Project_ID(C_Project_ID);
			} else
				ish.setC_Project_ID(C_Project_ID);

			// Project Phase
			int C_ProjectPhase_ID = 0;
			if (ish.getC_ProjectPhase_ID() > 0)
				C_ProjectPhase_ID = getID(MProjectPhase.Table_Name,
						"C_ProjectPhase_ID = ?", ish.getC_ProjectPhase_ID());

			ish.setC_ProjectPhase_ID(C_ProjectPhase_ID);

			// Project task
			int C_ProjectTask_ID = 0;
			if (ish.getC_ProjectTask_ID() > 0)
				C_ProjectTask_ID = getID(MProjectTask.Table_Name,
						"C_ProjectTask_ID = ?", ish.getC_ProjectTask_ID());

			ish.setC_ProjectTask_ID(C_ProjectTask_ID);

			// Campaign
			int C_Campaign_ID = 0;
			if (ish.getC_Campaign_ID() > 0)
				C_Campaign_ID = getID(MCampaign.Table_Name,
						"C_Campaign_ID = ?", ish.getC_Campaign_ID());

			if (C_Campaign_ID <= 0 && ish.getCampaignValue() != null) {
				C_Campaign_ID = getID(MCampaign.Table_Name, "Value = ?",
						ish.getCampaignValue());
				ish.setC_Campaign_ID(C_Campaign_ID);
			} else
				ish.setC_Campaign_ID(C_Campaign_ID);

			// Activity
			int C_Activity_ID = 0;
			if (ish.getC_Activity_ID() > 0)
				C_Activity_ID = getID(MActivity.Table_Name,
						"C_Activity_ID = ?", ish.getC_Activity_ID());

			if (C_Activity_ID <= 0 && ish.getActivityValue() != null) {
				C_Activity_ID = getID(MActivity.Table_Name, "Value = ?",
						ish.getActivityValue());
				ish.setC_Activity_ID(C_Activity_ID);
			} else
				ish.setC_Activity_ID(C_Activity_ID);

			ish.saveEx();

			StringBuffer err = new StringBuffer("");
			if (ish.getAD_Org_ID() <= 0)
				err.append(" @AD_Org_ID@ @NotFound@,");

			if (ish.getM_Product_ID() <= 0)
				err.append(" @M_Product_ID@ @NotFound@,");

			if (ish.getM_Warehouse_ID() <= 0)
				err.append(" @M_Warehouse_ID@ @NotFound@,");

			if (ish.getC_BPartner_ID() <= 0)
				err.append(" @C_BPartner_ID@ @NotFound@,");

			if (err.toString() != null && err.toString().length() > 0) {
				notimported++;
				ish.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
				ish.saveEx();
			}
            commitEx();
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
	 * get all records in X_I_SalesHistory table
	 * 
	 * @param imported
	 *            boolean
	 * @param isWithError
	 *            boolean
	 * @return collection of X_I_SalesHistory records
	 */
	private List<X_I_SalesHistory> getRecords(boolean imported,
			boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_SalesHistory.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_SalesHistory.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}

		return new Query(getCtx(), X_I_SalesHistory.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).list();
	}
} // Import Inventory Move

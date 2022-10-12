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

package org.eevolution.manufacturing.process;

import org.compiere.model.MSequence;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.manufacturing.model.MSalesHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Sales History from Invoice history.
 * 
 * @author victor.perez@e-evolution.com ,www.e-evolution.com
 */
public class GenerateSalesHistory extends GenerateSalesHistoryAbstract {

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
	} // prepare

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception {
		return "@C_SalesHistory_ID@ # " + generateSalesHistory();
	}

	/**
	 * generate sales history from invoices lines
	 * 
	 * @return No record inserted
	 */
	private int generateSalesHistory() {
		List<Object> parameters = new ArrayList<Object>();
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO ").append(MSalesHistory.Table_Name)
				.append(" (");
		insert.append(MSalesHistory.COLUMNNAME_C_SalesHistory_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_InvoiceLine_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_DocumentNo).append(",");
		insert.append(MSalesHistory.COLUMNNAME_AD_Client_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_AD_Org_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BPartner_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BP_Group_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BPartner_Location_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_SalesRep_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Category_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Classification_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Class_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Group_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Warehouse_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_Activity_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_Campaign_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_SalesRegion_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_Project_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_ProjectPhase_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_ProjectTask_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_User1_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_User2_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_User3_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_User4_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_DateInvoiced).append(",");
		insert.append(MSalesHistory.COLUMNNAME_Qty).append(",");
		insert.append(MSalesHistory.COLUMNNAME_TotalInvQty).append(",");
		insert.append(MSalesHistory.COLUMNNAME_PriceInvoiced).append(",");
		insert.append(MSalesHistory.COLUMNNAME_TotalInvAmt).append(",");
		insert.append(MSalesHistory.COLUMNNAME_CostAmt).append(",");
		insert.append(MSalesHistory.COLUMNNAME_TotalInvCost).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_Tax_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_TaxAmt).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_UOM_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_Description).append(",");
		insert.append(MSalesHistory.COLUMNNAME_LineNetAmt).append(",");
		insert.append(MSalesHistory.COLUMNNAME_LineTotalAmt).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_AttributeSetInstance_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_PriceActual).append(",");
		insert.append(MSalesHistory.COLUMNNAME_PriceEntered).append(",");
		insert.append(MSalesHistory.COLUMNNAME_PriceList).append(",");
		insert.append(MSalesHistory.COLUMNNAME_QtyEntered).append(",");
		insert.append(MSalesHistory.COLUMNNAME_Created).append(",");
		insert.append(MSalesHistory.COLUMNNAME_CreatedBy).append(",");
		insert.append(MSalesHistory.COLUMNNAME_Updated).append(",");
		insert.append(MSalesHistory.COLUMNNAME_UpdatedBy).append(")");

		insert.append(" SELECT DISTINCT ");
		insert.append("nextidfunc(")
				.append(MSequence.get(getCtx(), MSalesHistory.Table_Name)
						.get_ID()).append(",'Y')").append(",");
		insert.append("il.C_InvoiceLine_ID,");
		insert.append("i.DocumentNo,");
		insert.append("il.AD_Client_ID,");
		insert.append("il.AD_Org_ID,");
		insert.append("i.C_BPartner_ID,");
		insert.append("bp.C_BP_Group_ID,");
		insert.append("i.C_BPartner_Location_ID,");
		insert.append("i.SalesRep_ID,");
		insert.append("il.M_Product_ID,");
		insert.append("p.M_Product_Category_ID,");
		insert.append("pcl.M_Product_Classification_ID,");
		insert.append("pclass.M_Product_Class_ID,");
		insert.append("pg.M_Product_Group_ID,");
		insert.append("COALESCE(l.M_Warehouse_ID,ol.M_Warehouse_ID) AS M_Warehouse_ID,");
		insert.append("il.C_Activity_ID,");
		insert.append("il.C_Campaign_ID,");
		insert.append("bpl.C_SalesRegion_ID,");
		insert.append("il.C_Project_ID,");
		insert.append("il.C_ProjectPhase_ID,");
		insert.append("il.C_ProjectTask_ID,");
		insert.append("il.User1_ID,");
		insert.append("il.User2_ID,");
		insert.append("il.User3_ID,");
		insert.append("il.User4_ID,");
		insert.append("i.DateInvoiced,");
		insert.append("il.QtyInvoiced,");
		insert.append("il.QtyInvoiced,");
		insert.append("il.PriceActual,");
		insert.append("il.LineTotalAmt,");
		insert.append("0.00").append(",");
		insert.append("0.00").append(",");
		insert.append("il.C_Tax_ID").append(",");
		insert.append("il.TaxAmt").append(",");
		insert.append("il.C_UOM_ID").append(",");
		insert.append("il.Description").append(",");
		insert.append("il.LineNetAmt").append(",");
		insert.append("il.LineTotalAmt").append(",");
		insert.append("il.M_AttributeSetInstance_ID").append(",");
		insert.append("il.PriceActual").append(",");
		insert.append("il.PriceEntered").append(",");
		insert.append("il.PriceList").append(",");
		insert.append("il.QtyEntered").append(",");
		insert.append("SYSDATE").append(",");
		insert.append(Env.getAD_User_ID(getCtx())).append(",");
		insert.append("SYSDATE").append(",");
		insert.append(Env.getAD_User_ID(getCtx()));
		insert.append(" FROM C_InvoiceLine il ");
		insert.append(" INNER JOIN  C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) ");
		insert.append(" INNER JOIN  C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID) ");
		insert.append(" INNER JOIN  M_Product p ON (il.M_Product_ID=p.M_Product_ID) ");
		insert.append(" LEFT JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)");
		insert.append(" LEFT JOIN M_Product_Category  pc ON (p.M_Product_Category_ID=pc.M_Product_Category_ID) ");
		insert.append(" LEFT JOIN M_Product_Classification  pcl ON (p.M_Product_Classification_ID=pcl.M_Product_Classification_ID) ");
		insert.append(" LEFT JOIN M_Product_Class  pclass ON (p.M_Product_Class_ID=pclass.M_Product_Class_ID) ");
		insert.append(" LEFT JOIN M_Product_Group  pg ON (p.M_Product_Group_ID=pg.M_Product_Group_ID) ");
		insert.append(" LEFT JOIN M_InOutLine iol ON (il.M_InOutLine_ID=iol.M_InOutLine_ID) ");
		insert.append(" LEFT JOIN C_OrderLine ol ON (il.C_OrderLine_ID = ol.C_OrderLine_ID) ");
		insert.append(" LEFT JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID) ");
		insert.append(" WHERE i.IsSOTrx='Y' AND NOT EXISTS (SELECT 1 FROM C_SalesHistory sh WHERE sh.C_InvoiceLine_ID=il.C_InvoiceLine_ID) AND ");

		StringBuffer whereClause = new StringBuffer();

		whereClause.append("il.AD_Client_ID=? AND ");
		parameters.add(getAD_Client_ID());

		if (getOrgId() > 0) {
			whereClause.append("il.AD_Org_ID=? AND ");
			parameters.add(getOrgId());
		}

		// Product Dimension
		if (getProductId() > 0) {
			whereClause.append("il.M_Product_ID=? AND ");
			parameters.add(getProductId());
		}

		if (getProductCategoryId() > 0) {
			whereClause.append("pc.M_Product_Category_ID=? AND ");
			parameters.add(getProductCategoryId());
		}

		if (getProductClassificationId() > 0) {
			whereClause.append("pcl.M_Product_Classification_ID=? AND ");
			parameters.add(getProductClassificationId());
		}

		if (getProductClassId() > 0) {
			whereClause.append("pclass.M_Product_Class_ID=? AND ");
			parameters.add(getProductClassId());
		}

		if (getProductGroupId() > 0) {
			whereClause.append("pg.M_Product_Group_ID=? AND ");
			parameters.add(getProductGroupId());
		}

		if (getWarehouseId() > 0) {
			whereClause.append("l.M_Warehouse_ID=? AND ");
			parameters.add(getWarehouseId());
		}

		// BPartner Dimension
		if (getBPartnerId() > 0) {
			whereClause.append("i.C_BPartner_ID=? AND ");
			parameters.add(getBPartnerId());
		}
		if (getBPGroupId() > 0) {
			whereClause.append("bp.C_BP_Group_ID=? AND ");
			parameters.add(getBPGroupId());
		}
		if (getBPartnerLocationId() > 0) {
			whereClause.append("il.C_BPartner_Location_ID=? AND ");
			parameters.add(getBPartnerLocationId());
		}
		if (getSalesRepId() > 0) {
			whereClause.append("i.SalesRep_ID=? AND ");
			parameters.add(getSalesRepId());
		}
		if (getSalesRegionId() > 0) {
			whereClause.append("bpl.C_SalesRegion_ID=? AND ");
			parameters.add(getSalesRegionId());
		}
		if (getCampaignId() > 0) {
			whereClause.append("il.C_Campaign_ID=? AND ");
			parameters.add(getCampaignId());
		}

		if (getProjectId() > 0) {
			whereClause.append("il.C_Project_ID=? AND ");
			parameters.add(getProjectId());
		}
		if (getActivityId() > 0) {
			whereClause.append("il.C_Activity_ID=? AND ");
			parameters.add(getActivityId());
		}

		if (getUser1Id() > 0) {
			whereClause.append("il.User1_ID=? AND ");
			parameters.add(getUser1Id());
		}

		if (getUser2Id() > 0) {
			whereClause.append("il.User2_ID=? AND ");
			parameters.add(getUser2Id());
		}

		if (getUser3Id() > 0) {
			whereClause.append("il.User3_ID=? AND ");
			parameters.add(getUser3Id());
		}

		if (getUser4Id() > 0) {
			whereClause.append("il.User4_ID=? AND ");
			parameters.add(getUser4Id());
		}

		if (getDateInvoiced() != null && getDateInvoicedTo() != null) {
			whereClause.append("i.DateInvoiced BETWEEN ? AND ? ");
			parameters.add(getDateInvoiced());
			parameters.add(getDateInvoicedTo());
		}

		insert.append(whereClause.toString());
		return DB.executeUpdateEx(insert.toString(), parameters.toArray(),
				get_TrxName());
	}
}

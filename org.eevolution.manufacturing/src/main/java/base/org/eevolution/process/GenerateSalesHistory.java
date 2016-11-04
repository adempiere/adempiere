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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_C_Invoice;
import org.compiere.model.MSequence;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.I_C_SalesHistory;
import org.eevolution.model.MSalesHistory;

/**
 * Generate Sales History from Invoice history.
 * 
 * @author victor.perez@e-evolution.com ,www.e-evolution.com
 */
public class GenerateSalesHistory extends SvrProcess {

	private int p_AD_Org_ID;
	private Timestamp p_DateInvoicedFrom;
	private Timestamp p_DateInvoicedTo;
	private int p_C_BPartner_ID;
	private int p_C_BP_Group_ID;
	private int p_C_BPartner_Location_ID;
	private int p_M_Product_ID;
	private int p_M_Product_Category_ID;
	private int p_C_Campaign_ID;
	private int p_C_SalesRegion_ID;
	private int p_C_Activity_ID;
	private int p_User1_ID;
	private int p_User2_ID;
	private int p_User3_ID;
	private int p_User4_ID;
	private int p_M_Warehouse_ID;
	private int p_M_Product_Classification_ID;
	private int p_M_Product_Class_ID;
	private int p_M_Product_Group_ID;
	private int p_C_Project_ID;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_AD_Org_ID))
				p_AD_Org_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_DateInvoiced)) {
				p_DateInvoicedFrom = (Timestamp) para.getParameter();
				p_DateInvoicedTo = (Timestamp) para.getParameter_To();
			} else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_BPartner_ID))
				p_C_BPartner_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_BP_Group_ID))
				p_C_BP_Group_ID = para.getParameterAsInt();
			else if (name
					.equals(I_C_SalesHistory.COLUMNNAME_C_BPartner_Location_ID))
				p_C_BPartner_Location_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_M_Product_ID))
				p_M_Product_ID = para.getParameterAsInt();
			else if (name
					.equals(I_C_SalesHistory.COLUMNNAME_M_Product_Category_ID))
				p_M_Product_Category_ID = para.getParameterAsInt();
			else if (name
					.equals(I_C_SalesHistory.COLUMNNAME_M_Product_Classification_ID))
				p_M_Product_Classification_ID = para.getParameterAsInt();
			else if (name
					.equals(I_C_SalesHistory.COLUMNNAME_M_Product_Class_ID))
				p_M_Product_Class_ID = para.getParameterAsInt();
			else if (name
					.equals(I_C_SalesHistory.COLUMNNAME_M_Product_Group_ID))
				p_M_Product_Group_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_M_Warehouse_ID))
				p_M_Warehouse_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_Campaign_ID))
				p_C_Campaign_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_SalesRegion_ID))
				p_C_SalesRegion_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_Project_ID))
				p_C_Project_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_C_Activity_ID))
				p_C_Activity_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_User1_ID))
				p_User1_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_User2_ID))
				p_User2_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_User3_ID))
				p_User3_ID = para.getParameterAsInt();
			else if (name.equals(I_C_SalesHistory.COLUMNNAME_User4_ID))
				p_User4_ID = para.getParameterAsInt();

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
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
		insert.append(MSalesHistory.COLUMNNAME_AD_Client_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_AD_Org_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BPartner_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BP_Group_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_C_BPartner_Location_ID).append(
				",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_ID).append(",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Category_ID).append(
				",");
		insert.append(MSalesHistory.COLUMNNAME_M_Product_Classification_ID)
				.append(",");
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
		insert.append(MSalesHistory.COLUMNNAME_Created).append(",");
		insert.append(MSalesHistory.COLUMNNAME_CreatedBy).append(",");
		insert.append(MSalesHistory.COLUMNNAME_Updated).append(",");
		insert.append(MSalesHistory.COLUMNNAME_UpdatedBy).append(")");

		insert.append(" SELECT DISTINCT ");
		insert.append("nextidfunc(")
				.append(MSequence.get(getCtx(), MSalesHistory.Table_Name)
						.get_ID()).append(",'Y')").append(",");
		insert.append("il.C_InvoiceLine_ID,");
		insert.append("il.AD_Client_ID,");
		insert.append("il.AD_Org_ID,");
		insert.append("i.C_BPartner_ID,");
		insert.append("bp.C_BP_Group_ID,");
		insert.append("i.C_BPartner_Location_ID,");
		insert.append("il.M_Product_ID,");
		insert.append("p.M_Product_Category_ID,");
		insert.append("pcl.M_Product_Classification_ID,");
		insert.append("pclass.M_Product_Class_ID,");
		insert.append("pg.M_Product_Group_ID,");
		insert.append("l.M_Warehouse_ID,");
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
		insert.append(" LEFT JOIN M_Product_Classification  pcl ON (p.Classification=pcl.Value) ");
		insert.append(" LEFT JOIN M_Product_Class  pclass ON (p.Group1=pclass.Value) ");
		insert.append(" LEFT JOIN M_Product_Group  pg ON (p.Group2=pg.Value) ");
		insert.append(" LEFT JOIN M_InOutLine iol ON (il.M_InOutLine_ID=iol.M_InOutLine_ID ) ");
		insert.append(" LEFT JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID) ");
		insert.append(" WHERE i.IsSOTrx='Y' AND NOT EXISTS (SELECT 1 FROM C_SalesHistory sh WHERE sh.C_InvoiceLine_ID=il.C_InvoiceLine_ID) AND ");

		StringBuffer whereClause = new StringBuffer();

		whereClause.append("il.AD_Client_ID=? AND ");
		parameters.add(getAD_Client_ID());

		if (p_AD_Org_ID > 0) {
			whereClause.append("il.AD_Org_ID=? AND ");
			parameters.add(p_AD_Org_ID);
		}

		// Product Dimension
		if (p_M_Product_ID > 0) {
			whereClause.append("il.M_Product_ID=? AND ");
			parameters.add(p_M_Product_ID);
		}

		if (p_M_Product_Category_ID > 0) {
			whereClause.append("pc.M_Product_Category_ID=? AND ");
			parameters.add(p_M_Product_Category_ID);
		}

		if (p_M_Product_Classification_ID > 0) {
			whereClause.append("pcl.M_Product_Classification_ID=? AND ");
			parameters.add(p_M_Product_Classification_ID);
		}

		if (p_M_Product_Class_ID > 0) {
			whereClause.append("pclass.M_Product_Class_ID=? AND ");
			parameters.add(p_M_Product_Class_ID);
		}

		if (p_M_Product_Group_ID > 0) {
			whereClause.append("pg.M_Product_Group_ID=? AND ");
			parameters.add(p_M_Product_Group_ID);
		}

		if (p_M_Warehouse_ID > 0) {
			whereClause.append("l.M_Warehouse_ID=? AND ");
			parameters.add(p_M_Warehouse_ID);
		}

		// BPartner Dimension
		if (p_C_BPartner_ID > 0) {
			whereClause.append("i.C_BPartner_ID=? AND ");
			parameters.add(p_C_BPartner_ID);
		}
		if (p_C_BP_Group_ID > 0) {
			whereClause.append("bp.C_BP_Group_ID=? AND ");
			parameters.add(p_C_BP_Group_ID);
		}
		if (p_C_BPartner_Location_ID > 0) {
			whereClause.append("il.C_BPartner_Location_ID=? AND ");
			parameters.add(p_C_BPartner_Location_ID);
		}

		if (p_C_SalesRegion_ID > 0) {
			whereClause.append("bpl.C_SalesRegion_ID=? AND ");
			parameters.add(p_C_SalesRegion_ID);
		}
		if (p_C_Campaign_ID > 0) {
			whereClause.append("il.C_Campaign_ID=? AND ");
			parameters.add(p_C_Campaign_ID);
		}

		if (p_C_Project_ID > 0) {
			whereClause.append("il.C_Project_ID=? AND ");
			parameters.add(p_C_Project_ID);
		}
		if (p_C_Activity_ID > 0) {
			whereClause.append("il.C_Activity_ID=? AND ");
			parameters.add(p_C_Activity_ID);
		}

		if (p_User1_ID > 0) {
			whereClause.append("il.User1_ID=? AND ");
			parameters.add(p_User1_ID);
		}

		if (p_User2_ID > 0) {
			whereClause.append("il.User2_ID=? AND ");
			parameters.add(p_User2_ID);
		}

		if (p_User3_ID > 0) {
			whereClause.append("il.User3_ID=? AND ");
			parameters.add(p_User3_ID);
		}

		if (p_User4_ID > 0) {
			whereClause.append("il.User4_ID=? AND ");
			parameters.add(p_User4_ID);
		}

		if (p_DateInvoicedFrom != null && p_DateInvoicedTo != null) {
			whereClause.append("i.DateInvoiced BETWEEN ? AND ? ");
			parameters.add(p_DateInvoicedFrom);
			parameters.add(p_DateInvoicedTo);
		}

		insert.append(whereClause.toString());
		return DB.executeUpdateEx(insert.toString(), parameters.toArray(),
				get_TrxName());
	}
}

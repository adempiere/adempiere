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

package org.eevolution.manufacturing.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_PP_ForecastDefinitionLine;
import org.compiere.util.DB;

/**
 * Forecast Rule Model
 * 
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 * 
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/3492">
 * 		@see BR [ 3492 ] The Calculate Forecast process gets the value of C_BP_Group_ID wrong in the where clause.</a>
 *
 */
public class MPPForecastDefinitionLine extends X_PP_ForecastDefinitionLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -10113541970559869L;

	public MPPForecastDefinitionLine(Properties ctx,
			int M_ForecastDefinitionLine_ID, String trxName) {
		super(ctx, M_ForecastDefinitionLine_ID, trxName);
	}

	public MPPForecastDefinitionLine(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}

	public String getSQlWhere(MPPPeriod period, int M_Warehouse_ID, String alias) {

		StringBuilder whereClause = new StringBuilder();

		whereClause.append(getSQlWhere(M_Warehouse_ID, alias));
		whereClause.append(MSalesHistory.COLUMNNAME_DateInvoiced)
				.append(" BETWEEN ").append(DB.TO_DATE(period.getStartDate()))
				.append(" AND ").append(DB.TO_DATE(period.getEndDate()));
		return whereClause.toString();
	}

	public String getSQlWhere(int M_Warehouse_ID, String alias) {

		StringBuffer whereClause = new StringBuffer();

		// Product Dimension
		if (getM_Product_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_M_Product_ID);
			whereClause.append("=").append(getM_Product_ID()).append(" AND ");
		}

		/*
		 * if (getM_Warehouse_ID() > 0) {
		 * whereClause.append(MSalesHistory.COLUMNNAME_M_Warehouse_ID);
		 * whereClause.append("=").append(M_Warehouse_ID).append(" AND"); }
		 */

		if (getM_Product_Category_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_M_Product_Category_ID);
			whereClause.append("=").append(getM_Product_Category_ID())
					.append(" AND ");
		}

		if (getM_Product_Classification_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause
					.append(MSalesHistory.COLUMNNAME_M_Product_Classification_ID);
			whereClause.append("=").append(getM_Product_Classification_ID())
					.append(" AND ");
		}

		if (getM_Product_Class_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_M_Product_Class_ID);
			whereClause.append("=").append(getM_Product_Class_ID())
					.append(" AND ");
		}

		if (getM_Product_Group_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_M_Product_Group_ID);
			whereClause.append("=").append(getM_Product_Group_ID())
					.append(" AND ");
		}

		// BPartner Dimension
		if (getC_BPartner_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_C_BPartner_ID);
			whereClause.append("=").append(getC_BPartner_ID()).append(" AND ");
		}
		if (getC_BP_Group_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_C_BP_Group_ID);
			whereClause.append("=").append(getC_BP_Group_ID())
					.append(" AND ");
		}

		if (getC_SalesRegion_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_C_SalesRegion_ID);
			whereClause.append("=").append(getC_SalesRegion_ID())
					.append(" AND ");
		}
		if (getC_Campaign_ID() > 0) {
			whereClause.append(alias).append(".");
			whereClause.append(MSalesHistory.COLUMNNAME_C_Campaign_ID);
			whereClause.append("=").append(getC_Campaign_ID()).append(" AND ");
		}

		/*
		 * if(getC_Activity_ID() > 0) {
		 * whereClause.append(MSalesHistory.COLUMNNAME_C_Activity_ID);
		 * whereClause.append("=").append(getC_Activity_ID()).append(" AND"); }
		 */

		return whereClause.toString();
	}
}

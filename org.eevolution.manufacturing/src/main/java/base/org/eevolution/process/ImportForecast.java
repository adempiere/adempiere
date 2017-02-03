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

import org.compiere.model.MForecast;
import org.compiere.model.MForecastLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPPeriod;
import org.eevolution.model.X_I_Forecast;

/**
 * Import Forecast
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */

public class ImportForecast extends SvrProcess {

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
			for (X_I_Forecast forecast : getRecords(true, false)) {
				forecast.deleteEx(true);
				no++;
			}
			log.fine("Delete Old Impored =" + no);
		}

		fillIDValues();
		importRecords();
		return "Imported: " + imported + ", Not imported: " + notimported;
	} // doIt

	/**
	 * import records using Import Forecast table
	 */
	private void importRecords() {
		for (X_I_Forecast ifl : getRecords(false, m_IsImportOnlyNoErrors)) {
			isImported = false;
			MForecastLine fl = importForecast(ifl);
			if (fl != null)
				isImported = true;

			if (isImported) {
				ifl.setM_ForecastLine_ID(fl.getM_ForecastLine_ID());
				ifl.setI_IsImported(true);
				ifl.setProcessed(true);
				ifl.setI_ErrorMsg("");
				ifl.saveEx();
				imported++;
			} else {
				ifl.setI_IsImported(false);
				ifl.setProcessed(true);
				ifl.saveEx();
				notimported++;
			}
		}
	}

	/**
	 * Import Forecast using X_I_Forecast table
	 * 
	 * @param X_I_Forecast
	 *            X_I_Forecast
	 * @return MForecastLine Forecast Line
	 */
	private MForecastLine importForecast(X_I_Forecast ifl) {
		MForecastLine fl = new MForecastLine(getCtx(),
				ifl.getM_ForecastLine_ID(), get_TrxName());
		MForecast f = (MForecast) ifl.getM_Forecast();
		if (fl == null || fl.get_ID() <= 0) {
			fl = new MForecastLine(Env.getCtx(), 0, get_TrxName());
			fl.setAD_Org_ID(ifl.getAD_Org_ID());
			fl.setM_Forecast_ID(ifl.getM_Forecast_ID());
			fl.setM_Product_ID(ifl.getM_Product_ID());
			fl.setM_Warehouse_ID(ifl.getM_Warehouse_ID());
		}

		fl.setQty(ifl.getQty());
		fl.setIsActive(true);
		fl.setSalesRep_ID(ifl.getSalesRep_ID());
		fl.setDatePromised(ifl.getDatePromised());
		MPPPeriod period = MPPPeriod.findByCalendar(getCtx(),
				ifl.getDatePromised(), f.getPP_Calendar_ID(), get_TrxName());
		if (period == null)
		{
			ifl.setI_ErrorMsg(Msg.parseTranslation(ifl.getCtx() , "@PP_Period_ID@ @NotFound@ @To@ @DatePromised@" + ifl.getDatePromised()));
			isImported = false;
			return null;
		}

		fl.setPP_Period_ID(period.get_ID());
		fl.saveEx();
		isImported = true;
		return fl;
	}

	/**
	 * fill IDs values based on Search Key
	 * 
	 * @throws SQLException
	 */
	private void fillIDValues() throws SQLException {
		for (X_I_Forecast ifl : getRecords(false, m_IsImportOnlyNoErrors)) {
			// Forecast ID
			int M_Forecast_ID = 0;
			if (ifl.getM_Forecast_ID() > 0)
				M_Forecast_ID = getID(MForecast.Table_Name,
						"M_Forecast_ID = ?", ifl.getM_Forecast_ID());

			// Organization
			int AD_Org_ID = 0;
			if (ifl.getAD_Org_ID() > 0)
				AD_Org_ID = getID(MOrg.Table_Name, "AD_Org_ID = ?",
						ifl.getAD_Org_ID());

			if (AD_Org_ID <= 0 && ifl.getOrgValue() != null) {
				AD_Org_ID = getID(MOrg.Table_Name, "Value = ?",
						ifl.getOrgValue());
				ifl.setAD_Org_ID(AD_Org_ID);
			} else
				ifl.setAD_Org_ID(AD_Org_ID);

			// Sales Rep ID
			int SalesRep_ID = 0;
			if (ifl.getSalesRep_ID() > 0)
				SalesRep_ID = getID(MUser.Table_Name, "AD_User_ID = ?",
						ifl.getSalesRep_ID());

			if (SalesRep_ID <= 0 && ifl.getSalesRep_Name() != null) {
				SalesRep_ID = getID(MUser.Table_Name, "Name = ?",
						ifl.getSalesRep_Name());
				ifl.setSalesRep_ID(SalesRep_ID);
			} else
				ifl.setSalesRep_ID(SalesRep_ID);

			// Product
			int M_Product_ID = 0;
			if (ifl.getM_Product_ID() > 0)
				M_Product_ID = getID(MProduct.Table_Name, "M_Product_ID = ?",
						ifl.getM_Product_ID());

			if (M_Product_ID <= 0 && ifl.getProductValue() != null) {
				M_Product_ID = getID(MProduct.Table_Name, "Value = ?",
						ifl.getProductValue());
				ifl.setM_Product_ID(M_Product_ID);
			} else
				ifl.setM_Product_ID(M_Product_ID);

			// Warehouse
			int M_Warehouse_ID = 0;
			if (ifl.getM_Warehouse_ID() > 0)
				M_Warehouse_ID = getID(MWarehouse.Table_Name,
						"M_Warehouse_ID = ?", ifl.getM_Warehouse_ID());

			if (M_Warehouse_ID <= 0 && ifl.getWarehouseValue() != null) {
				M_Warehouse_ID = getID(MWarehouse.Table_Name, "Value = ?",
						ifl.getWarehouseValue());
				ifl.setM_Warehouse_ID(M_Warehouse_ID);
			} else
				ifl.setM_Warehouse_ID(M_Warehouse_ID);

			ifl.saveEx();

			StringBuffer err = new StringBuffer("");

			if (ifl.getM_Forecast_ID() <= 0)
				err.append(" @M_Forecast_ID@ @NotFound@,");

			if (ifl.getAD_Org_ID() <= 0)
				err.append(" @AD_Org_ID@ @NotFound@,");

			if (ifl.getM_Product_ID() <= 0)
				err.append(" @M_Product_ID@ @NotFound@,");

			if (ifl.getM_Warehouse_ID() <= 0)
				err.append(" @M_Warehouse_ID@ @NotFound@,");

			if (ifl.getDatePromised() == null)
				err.append(" @DatePromised@ @NotFound@,");

			if (err.toString() != null && err.toString().length() > 0) {
				notimported++;
				ifl.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
				ifl.saveEx();
			}
			ifl.saveEx();
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
	private List<X_I_Forecast> getRecords(boolean imported, boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_Forecast.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_Forecast.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}

		return new Query(getCtx(), X_I_Forecast.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).list();
	}
} // Import Forecast Lines

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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.MSequence;
import org.compiere.model.MUOM;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.engine.forecast.DataElement;
import org.eevolution.engine.forecast.DataSet;
import org.eevolution.engine.forecast.ForecastEngine;
import org.eevolution.engine.forecast.ForecastFactory;
import org.eevolution.model.MPPForecastDefinition;
import org.eevolution.model.MPPForecastDefinitionLine;
import org.eevolution.model.MPPForecastRule;
import org.eevolution.model.MPPForecastRun;
import org.eevolution.model.MPPForecastRunDetail;
import org.eevolution.model.MPPForecastRunLine;
import org.eevolution.model.MPPForecastRunMaster;
import org.eevolution.model.MPPForecastRunResult;
import org.eevolution.model.MPPPeriod;
import org.eevolution.model.MPPPeriodDefinition;
import org.eevolution.model.MSalesHistory;

/**
 * Generate Simulation Forecast
 * 
 * @author victor.perez@e-evolution.com www.e-evolution.com
 * 
 */
public class ForecastRunCreate extends SvrProcess {

	private int p_PP_ForecastRun_ID = 0;

	private MPPForecastRun m_run = null;
	private MPPPeriodDefinition m_period_definition_base = null;
	private MPPPeriodDefinition m_period_definition_target = null;
	private MPPForecastDefinition m_forecast_definition = null;
	private MPPForecastRule m_forecast_rule = null;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_PP_ForecastRun_ID = getRecord_ID();
	} // prepare

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception {

		m_run = new MPPForecastRun(getCtx(), p_PP_ForecastRun_ID, get_TrxName());
		// Get Forecast Definition
		m_forecast_definition = (MPPForecastDefinition) m_run
				.getPP_ForecastDefinition();

		if (m_run.get_ID() == 0)
			throw new IllegalArgumentException("@NotFound@ @PP_ForecastRun_ID@");

		if (m_forecast_definition.get_ID() == 0)
			throw new IllegalArgumentException(
					"@NotFound@ @PP_ForecastDefinition_ID@");

		if (!m_run.deleteEntries(true))
			throw new IllegalArgumentException("Cannot delete existing entries");

		return "@PP_ForecastRunMaster_ID@ #"
				+ generateForecastResult(m_run.getM_Warehouse_ID());
	} // doIt

	/**
	 * Generate Forecast Result based on Forecast Detail and Forecast Definition
	 * 
	 * @param M_Warehouse_ID
	 * @return no records processed
	 */
	private int generateForecastResult(int M_Warehouse_ID) {

		// Preview Calculate Data
		deleteData();
		// Get Period Base
		m_period_definition_base = new MPPPeriodDefinition(getCtx(),
				m_run.getRef_DefinitionPeriod_ID(), get_TrxName());
		m_period_definition_target = new MPPPeriodDefinition(getCtx(),
				m_run.getPP_PeriodDefinition_ID(), get_TrxName());
		m_forecast_rule = (MPPForecastRule) m_run.getPP_ForecastRule();

		// Get Periods
		List<MPPPeriod> m_period_history = m_period_definition_base
				.getPeriodsOfHistory(m_run.getPeriodHistory());
		// Generate Forecast Run Master
		int selection = generateForecastRunMaster(m_run, m_forecast_definition,
				m_period_history.get(m_period_history.size() - 1),
				m_period_history.get(0));

		if (selection <= 0)
			throw new IllegalArgumentException("@FindZeroRecords@");

		for (MPPForecastRunMaster master : MPPForecastRunMaster.getLines(
				getCtx(), p_PP_ForecastRun_ID, get_TrxName())) {
			// Generate Forecast Detail for each period
			for (MPPPeriod period : m_period_history) {
				// Create Forecast Run Detail
				createForecastRunDetail(master, period);
			}
		}
		// Create Forecast Run Entry based on Forecast Run Detail
		return createForecastRunResult(M_Warehouse_ID);
	}

	/**
	 * Create the Forecast Result
	 * 
	 * @param M_Warehouse_ID
	 * @return no records processed
	 */
	private int createForecastRunResult(int M_Warehouse_ID) {
		// total record processed
		int records = 0;
		ForecastEngine engine = ForecastFactory
				.getForecastEngine(getAD_Client_ID());
		// Generate ForecastEntry based on forecast rule
		for (MPPForecastRunMaster master : MPPForecastRunMaster.getLines(
				getCtx(), p_PP_ForecastRun_ID, get_TrxName())) {

			DataSet series = new DataSet();
			series.setPeriods(m_run.getPeriodHistory());
			MProduct product = MProduct.get(getCtx(), master.getM_Product_ID());

			List<MPPForecastRunDetail> details = MPPForecastRunMaster
					.getDetails(getCtx(), master.get_ID(), get_TrxName());

			for (MPPForecastRunDetail detail : details) {
				MPPPeriod period = (MPPPeriod) detail.getPP_Period();
				DataElement data = new DataElement(master.get_ID(),
						period.getPeriodNo(), detail.getQtyCalculated(), null);
				series.addDataElement(data);
			}

			DataSet results = engine.getForecast(m_forecast_rule
					.getCalculationClass(), master.get_ID(), series, master
					.getFactorAlpha().doubleValue(), master.getFactorGamma()
					.doubleValue(), master.getFactorBeta().doubleValue(),
					master.getFactorMultiplier().doubleValue(), master
							.getFactorScale().doubleValue(), master
							.getFactorUser().doubleValue());

			Enumeration<DataElement> elements = results.getDataElements();

			while (elements.hasMoreElements()) {
				DataElement element = elements.nextElement();

				MUOM uom = (MUOM) product.getC_UOM();
				MPPForecastRunResult result = new MPPForecastRunResult(
						getCtx(), 0, get_TrxName());
				result.setPP_ForecastRun_ID(m_run.get_ID());
				result.setPP_ForecastRunMaster_ID(element.getKey());
				result.setDescription(element.getDescription());
				BigDecimal qty = (BigDecimal) element.getValue();
				result.setQtyCalculated(qty);
				result.setQtyPlan(uom.round(qty, true));
				result.setIsActive(true);
				result.setPP_Period_ID(MPPPeriod.getIDByPeriodNo(
						m_period_definition_target, element.getPeriodNo()));
				result.setPeriodNo(element.getPeriodNo());
				result.saveEx();
				records++;
			}
		}
		// m_run.setProcessed(true);
		// m_run.saveEx();
		return records;
	}

	/**
	 * Create Forecast Detail based on sales history
	 * 
	 * @param master
	 *            Forecast Master
	 * @param period
	 *            Operational Period
	 */
	private void createForecastRunDetail(MPPForecastRunMaster master,
			MPPPeriod period) {

		MPPForecastRunDetail forecastRunDetail = new MPPForecastRunDetail(
				getCtx(), 0, get_TrxName());
		forecastRunDetail.setPP_ForecastRun_ID(m_run.get_ID());
		forecastRunDetail.setPP_ForecastRunMaster_ID(master
				.getPP_ForecastRunMaster_ID());
		forecastRunDetail.setPP_Period_ID(period.get_ID());
		forecastRunDetail.setPeriodNo(period.getPeriodNo());
		forecastRunDetail.saveEx();

		generateForcastRunLines(forecastRunDetail);
		updateDetail(period.get_ID(), forecastRunDetail.get_ID());
	}

	/**
	 * Generate Forecast Master based on sales history
	 * 
	 * @param run
	 *            Forecast Simulation
	 * @param fd
	 *            Forecast Definition
	 * @param start
	 *            Start Operational Period
	 * @param end
	 *            End Operational Period
	 * @return no records processed
	 */
	private int generateForecastRunMaster(MPPForecastRun run,
			MPPForecastDefinition fd, MPPPeriod start, MPPPeriod end) {
		int count = 0;
		for (MPPForecastDefinitionLine fdl : fd.getLines(true)) {
			final StringBuffer select = new StringBuffer(
					"SELECT DISTINCT sh.M_Product_ID , sh.M_Warehouse_ID FROM C_SalesHistory sh LEFT JOIN  PP_ForecastRunMaster m ON (m.M_Product_ID=sh.M_Product_ID AND ");
			select.append(MPPForecastRunMaster.COLUMNNAME_PP_ForecastRun_ID);
			select.append("=").append(run.get_ID()).append(" ) WHERE  ");
			select.append(fdl.getSQlWhere(run.getM_WarehouseSource_ID(), "sh"));
			select.append(MSalesHistory.COLUMNNAME_DateInvoiced);
			select.append(" BETWEEN ");
			select.append(DB.TO_DATE(start.getStartDate()));
			select.append(" AND ");
			select.append(DB.TO_DATE(end.getEndDate()));

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(select.toString(), get_TrxName());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count++;
					int M_Product_ID = rs.getInt("M_Product_ID");
					MPPForecastRunMaster master = MPPForecastRunMaster
							.getByProduct(getCtx(), run.getPP_ForecastRun_ID(),
									M_Product_ID, get_TrxName());
					if (master != null)
						continue;

					master = new MPPForecastRunMaster(getCtx(), 0,
							get_TrxName());

					master.setAD_Org_ID(run.getAD_Org_ID());
					master.setPP_ForecastRun_ID(run.get_ID());
					master.setPP_ForecastDefinitionLine_ID(fdl
							.getPP_ForecastDefinitionLine_ID());
					master.setM_Product_ID(M_Product_ID);
					master.setM_Warehouse_ID(run.getM_Warehouse_ID());
					master.setFactorAlpha(fdl.getFactorAlpha());
					master.setFactorGamma(fdl.getFactorGamma());
					master.setFactorBeta(fdl.getFactorBeta());
					master.setFactorMultiplier(fdl.getFactorMultiplier());
					master.setFactorScale(fdl.getFactorScale());
					master.setFactorUser(fdl.getFactorUser());
					master.saveEx();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, select.toString(), e);
				getProcessInfo().addLog(getProcessInfo().getAD_PInstance_ID(),
						null, null, e.getLocalizedMessage());
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		return count;
	}

	/**
	 * Generate Forecast detail lines
	 * 
	 * @param frd
	 *            Forecast Detail
	 * @return no records processed
	 */
	public int generateForcastRunLines(MPPForecastRunDetail frd) {
		List<Object> parameters = new ArrayList<Object>();
		StringBuffer insertSQL = new StringBuffer();
		MPPPeriod period = (MPPPeriod) frd.getPP_Period();
		insertSQL.append("INSERT INTO ").append(MPPForecastRunLine.Table_Name)
				.append(" frl (");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_PP_ForecastRunLine_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_PP_ForecastRun_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_PP_ForecastRunDetail_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_AD_Client_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_AD_Org_ID).append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_C_SalesHistory_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_PP_Period_ID)
				.append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_Created).append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_CreatedBy).append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_Updated).append(",");
		insertSQL.append(MPPForecastRunLine.COLUMNNAME_UpdatedBy).append(")");

		insertSQL.append(" SELECT DISTINCT ");
		insertSQL
				.append("nextidfunc(")
				.append(MSequence.get(getCtx(), MPPForecastRunLine.Table_Name)
						.get_ID()).append(",'Y')").append(",");
		insertSQL.append(frd.getPP_ForecastRun_ID()).append(",");
		insertSQL.append(frd.getPP_ForecastRunDetail_ID()).append(",");
		insertSQL.append(MSalesHistory.COLUMNNAME_AD_Client_ID).append(",");
		insertSQL.append(MSalesHistory.COLUMNNAME_AD_Org_ID).append(",");
		insertSQL.append(MSalesHistory.COLUMNNAME_C_SalesHistory_ID)
				.append(",");
		insertSQL.append(period.getPP_Period_ID()).append(",");
		insertSQL.append("SYSDATE").append(",");
		insertSQL.append(Env.getAD_User_ID(getCtx())).append(",");
		insertSQL.append("SYSDATE").append(",");
		insertSQL.append(Env.getAD_User_ID(getCtx()));
		insertSQL.append(" FROM ").append(MSalesHistory.Table_Name);
		insertSQL.append(" WHERE ");
		insertSQL.append(MSalesHistory.COLUMNNAME_M_Product_ID).append(
				"=? AND ");
		insertSQL.append(MSalesHistory.COLUMNNAME_M_Warehouse_ID).append(
				"=? AND ");
		insertSQL.append(MSalesHistory.COLUMNNAME_DateInvoiced).append(
				" BETWEEN ? AND ? ");
		parameters.add(frd.getPP_ForecastRunMaster().getM_Product_ID());
		parameters.add(m_run.getM_WarehouseSource_ID());
		parameters.add(period.getStartDate());
		parameters.add(period.getEndDate());
		return DB.executeUpdateEx(insertSQL.toString(), parameters.toArray(),
				get_TrxName());
	}

	public void updateDetail(int PP_Period_ID, int PP_ForecastRunDetail_ID) {
		StringBuffer updateSQL = new StringBuffer();
		updateSQL.append("UPDATE PP_ForecastRunDetail frd SET");
		updateSQL.append(" QtyCalculated=(");
		updateSQL.append(" SELECT SUM(sh.Qty) AS Qty");
		updateSQL.append(" FROM PP_ForecastRunLine frl");
		updateSQL.append(" INNER JOIN C_SalesHistory sh ");
		updateSQL.append(" ON (sh.C_SalesHistory_ID=frl.C_SalesHistory_ID)");
		updateSQL.append(" WHERE frl.PP_ForecastRunDetail_ID=").append(
				PP_ForecastRunDetail_ID);
		updateSQL.append(" ),");
		updateSQL.append(" PP_Period_ID=").append(PP_Period_ID);
		updateSQL.append(" WHERE frd.PP_ForecastRunDetail_ID=").append(
				PP_ForecastRunDetail_ID);
		DB.executeUpdateEx(updateSQL.toString(), get_TrxName());
	} // updateEntry

	/**
	 * Delete data when an simulation is process again
	 */
	public void deleteData() {
		StringBuffer updateSQL = new StringBuffer();
		updateSQL.append(
				"DELETE FROM PP_ForecastRunLine WHERE PP_ForecastRun_ID=")
				.append(p_PP_ForecastRun_ID);
		DB.executeUpdateEx(updateSQL.toString(), get_TrxName());
		updateSQL = new StringBuffer();
		updateSQL.append(
				"DELETE FROM PP_ForecastRunDetail WHERE PP_ForecastRun_ID=")
				.append(p_PP_ForecastRun_ID);
		DB.executeUpdateEx(updateSQL.toString(), get_TrxName());
		updateSQL = new StringBuffer();
		updateSQL.append(
				"DELETE FROM PP_ForecastRunResult WHERE PP_ForecastRun_ID=")
				.append(p_PP_ForecastRun_ID);
		DB.executeUpdateEx(updateSQL.toString(), get_TrxName());
		updateSQL = new StringBuffer();
		updateSQL.append(
				"DELETE FROM PP_ForecastRunMaster WHERE PP_ForecastRun_ID=")
				.append(p_PP_ForecastRun_ID);
		DB.executeUpdateEx(updateSQL.toString(), get_TrxName());
	}

}

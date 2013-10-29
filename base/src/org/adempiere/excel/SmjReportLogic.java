package org.adempiere.excel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.logging.Level;

import org.compiere.model.ReportTO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 *  @version <li>SmartJSP: SmjReportLogic.java, 2012/02/15
 *          <ul TYPE ="circle">
 *          <li> Consultas para completar la informacion del reporte 
 *          <li> Query for fill report data
 *          </ul>
 * @author Freddy Rodriguez - "SmartJSP" - http://www.smartjsp.com/
 * SMJReport --> clase nueva 
 */
public class SmjReportLogic {

	private CLogger log = CLogger.getCLogger(SmjReportLogic.class);

	/**
	 * trae los datos de T_Report ** T_Report get Data
	 * 
	 * @param idReport
	 * @param nameTrx
	 */
	public LinkedList<ReportTO> getDataReport(Integer idReport,
			String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select seqno, name, description, col_0, col_1, col_2, col_3, col_4, col_5, col_6, col_7, col_8, ");
		sql.append("col_9, col_10, col_11, col_12, col_13, col_14, col_15, col_16, col_17, col_18, col_19, col_20, ");
		sql.append("tablevel, reportlinestyle, fixedPercentage from T_Report ");
		sql.append("where ad_pinstance_id = " + idReport + " ");
		sql.append(" order by seqno, name asc ");
//		System.out.println("data SQL::"+sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedList<ReportTO> data = new LinkedList<ReportTO>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportTO rpt = new ReportTO();
				rpt.setSeqno(rs.getInt("seqno"));
				rpt.setName(rs.getString("name"));
				rpt.setDescription(rs.getString("description"));
				rpt.setCol_0(rs.getBigDecimal("col_0"));
				rpt.setCol_1(rs.getBigDecimal("col_1"));
				rpt.setCol_2(rs.getBigDecimal("col_2"));
				rpt.setCol_3(rs.getBigDecimal("col_3"));
				rpt.setCol_4(rs.getBigDecimal("col_4"));
				rpt.setCol_5(rs.getBigDecimal("col_5"));
				rpt.setCol_6(rs.getBigDecimal("col_6"));
				rpt.setCol_7(rs.getBigDecimal("col_7"));
				rpt.setCol_8(rs.getBigDecimal("col_8"));
				rpt.setCol_9(rs.getBigDecimal("col_9"));
				rpt.setCol_10(rs.getBigDecimal("col_10"));
				rpt.setCol_11(rs.getBigDecimal("col_11"));
				rpt.setCol_12(rs.getBigDecimal("col_12"));
				rpt.setCol_13(rs.getBigDecimal("col_13"));
				rpt.setCol_14(rs.getBigDecimal("col_14"));
				rpt.setCol_15(rs.getBigDecimal("col_15"));
				rpt.setCol_16(rs.getBigDecimal("col_16"));
				rpt.setCol_17(rs.getBigDecimal("col_17"));
				rpt.setCol_18(rs.getBigDecimal("col_18"));
				rpt.setCol_19(rs.getBigDecimal("col_19"));
				rpt.setCol_20(rs.getBigDecimal("col_20"));
				//SMJReport
				rpt.setTablevel(rs.getInt("tablevel"));
				rpt.setReportlinestyle(rs.getString("reportlinestyle"));
				rpt.setFixedPercentage(rs.getInt("fixedPercentage"));
				data.add(rpt);
			}// while
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return data;
	}// dataReport

	/**
	 * regresa el titulo general del reporte ** get General title
	 * 
	 * @param idReport
	 * @param nameTrx
	 * @return
	 */
	public String[] getGeneralTitle(Integer idReport, String nameTrx) {
		StringBuffer sql = new StringBuffer();
		// cols = 0;
		sql.append("select name, prePeriodName, posPeriodName from PA_Report ");
		// Goodwill - BF: report title is taken wrongly from other report if report line set is shared
		sql.append("where pa_report_id = " + idReport + " ");
//		System.out.println("getGeneralTitle SQL: "+sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String data[] = new String[3];
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				data[0] = rs.getString("name");
				data[1] = rs.getString("prePeriodName");
				data[2] = rs.getString("posPeriodName");
			}// if rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return data;
	}// getGeneralTitle

	/**
	 * regresa el nombre de la compania (client) ** get Client Name
	 * 
	 * @param nameTrx
	 * @return String
	 */
	public String getClientName(String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select name from AD_Client ");
		sql.append("where AD_Client_ID = " + Env.getAD_Client_ID(Env.getCtx())+ " ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getClientName

	/**
	 * regresa el nombre de la Organizacion (client) ** get Org Name
	 * @param nameTrx
	 * @return
	 */
	public String getOrgName(String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select name from AD_Org where AD_Client_ID = ");
		sql.append(Env.getAD_Client_ID(Env.getCtx())+" and ad_org_id = "+ Env.getAD_Org_ID(Env.getCtx()) + " ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getOrgName
	
	/**
	 * regresa el Nit de la organizacion (org) ** get Org NIT
	 * 
	 * @param nameTrx
	 * @return
	 */
	public String getOrgNIT(String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select taxid from AD_OrgInfo where ad_client_id = ");
		sql.append(Env.getAD_Client_ID(Env.getCtx()) + " and ad_org_id = "+ Env.getAD_Org_ID(Env.getCtx()) + " ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("taxid");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getClientNIT

	/**
	 * regresa el nombre del periodo ** Get Period Name
	 * 
	 * @param idPeriod
	 * @param nameTrx
	 * @return
	 */
	public String getPeriodName(Integer idPeriod, String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select name from C_Period ");
		sql.append("where C_Period_ID = " + idPeriod + " ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getPeriodName

	/**
	 * regresa el nombre de la moneda usada por el client ** Get currency Name
	 * 
	 * @param nameTrx
	 * @return
	 */
	public String getCurrency(String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append("select description from c_currency ");
		sql.append("where c_currency_id = (select c_currency_id from C_AcctSchema ");
		sql.append("where AD_Client_ID = " + Env.getAD_Client_ID(Env.getCtx())+ ") ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("description");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getCurrency

	/**
	 * Regresa el tipo de fuente ** Get Code Font
	 * 
	 * @param nameTrx
	 * @param codeFont
	 * @return
	 */
	public String getCodeFont(String nameTrx, Integer codeFont) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT code FROM ad_printfont WHERE ad_printfont_id = "	+ codeFont + " ");
		// System.out.println("getCodeFont SQL: "+sql.toString());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("code");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getCodeFont

	/**
	 * regresa la ciudad de la organizacion (org) ** get Org City
	 * 
	 * @param nameTrx
	 * @return
	 */
	public String getClientCity(String nameTrx) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select city from c_location where c_location_id = ( ");
		sql.append(" select c_location_id from AD_OrgInfo where ad_client_id = ");
		sql.append(Env.getAD_Client_ID(Env.getCtx()) + " and ad_org_id = "+ Env.getAD_Org_ID(Env.getCtx()) + ") ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println("getClientCity SQL:: "+sql.toString());
		String name = "";
		try {
			pstmt = DB.prepareStatement(sql.toString(), nameTrx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("city");
			}// while rs
		}// try
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return name;
	}// getClientNIT
	
}// SmjReportLogic

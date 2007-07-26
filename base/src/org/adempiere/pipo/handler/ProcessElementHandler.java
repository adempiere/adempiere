/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.DatabaseAccessException;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.MSequence;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_AD_Process_Para;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ProcessElementHandler extends AbstractElementHandler {

	private ProcessParaElementHandler paraHandler = new ProcessParaElementHandler();

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));
		int id = 0;
		String entitytype = atts.getValue("EntityType");
		if (entitytype.equals("U") || (entitytype.equals("D") && getUpdateMode(ctx).equals("true"))) {
			String name = atts.getValue("Name");

			// Get New process.
			id = get_ID(ctx, "AD_Process", name);

			X_AD_Process m_Process = null;
			int AD_Backup_ID = -1;
			String Object_Status = null;
			if (id > 0) {
				m_Process = new X_AD_Process(ctx, id, getTrxName(ctx));
				AD_Backup_ID = copyRecord(ctx, "AD_Process", m_Process);
				Object_Status = "Update";
			} else {
				m_Process = new X_AD_Process(ctx, id, getTrxName(ctx));
				id = MSequence.getNextID(Env.getAD_Client_ID(ctx),
						"AD_Process", getTrxName(ctx));
				m_Process.setAD_Process_ID(id);
				Object_Status = "New";
				AD_Backup_ID = 0;
			}
			m_Process.setName(name);

			name = atts.getValue("ADWorkflowNameID");
			if (name != null && name.trim().length() > 0) {
				id = get_IDWithColumn(ctx, "AD_Workflow", "Name", name);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				m_Process.setAD_Workflow_ID(id);
			}

			name = atts.getValue("ADPrintFormatNameID");
			if (name != null && name.trim().length() > 0) {
				id = get_IDWithColumn(ctx, "AD_PrintFormat", "Name", name);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				m_Process.setAD_PrintFormat_ID(id);
			}

			name = atts.getValue("ADReportViewNameID");
			if (name != null && name.trim().length() > 0) {
				id = get_IDWithColumn(ctx, "AD_ReportView", "Name", name);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				m_Process.setAD_ReportView_ID(id);
			}

			m_Process.setAccessLevel(atts.getValue("AccessLevel"));
			m_Process.setClassname(atts.getValue("Classname"));
			m_Process.setDescription(atts.getValue("Description").replaceAll(
					"'", "''").replaceAll(",", ""));
			m_Process.setEntityType(atts.getValue("EntityType"));
			m_Process.setHelp(atts.getValue("Help").replaceAll("'", "''")
					.replaceAll(",", ""));
			m_Process.setIsBetaFunctionality(Boolean.valueOf(
					atts.getValue("isBetaFunctionality")).booleanValue());
			m_Process.setIsDirectPrint(Boolean.valueOf(
					atts.getValue("isDirectPrint")).booleanValue());
			m_Process.setIsReport(Boolean.valueOf(atts.getValue("isReport"))
					.booleanValue());
			m_Process.setName(atts.getValue("Name"));

			m_Process.setProcedureName(atts.getValue("ProcedureName"));
			m_Process.setStatistic_Count(0);
			m_Process.setIsActive(atts.getValue("isActive") != null ? Boolean
					.valueOf(atts.getValue("isActive")).booleanValue() : true);
			m_Process.setStatistic_Seconds(0);
			m_Process.setValue(atts.getValue("Value"));
			m_Process.setWorkflowValue(atts.getValue("WorkflowValue"));
			if (m_Process.save(getTrxName(ctx)) == true) {
				record_log(ctx, 1, m_Process.getName(), "Process", m_Process
						.get_ID(), AD_Backup_ID, Object_Status, "AD_Process",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Process"));
			} else {
				record_log(ctx, 0, m_Process.getName(), "Process", m_Process
						.get_ID(), AD_Backup_ID, Object_Status, "AD_Process",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Process"));
				throw new POSaveFailedException("Process");
			}
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_Process_ID = Env.getContextAsInt(ctx, "AD_Process_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		String sqlW = "SELECT * FROM AD_PROCESS WHERE AD_PROCESS_ID = "
				+ AD_Process_ID;

		AttributesImpl atts = new AttributesImpl();
		PreparedStatement pstmt1 = null;
		pstmt1 = DB.prepareStatement(sqlW, getTrxName(ctx));
		try {
			ResultSet rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				X_AD_Process m_Process = new X_AD_Process(ctx, rs1
						.getInt("AD_Process_ID"), null);
				log.log(Level.INFO, "AD_ReportView_ID: "
						+ rs1.getInt("AD_ReportView_ID"));

				if (rs1.getString("IsReport").equals('Y')
						&& rs1.getInt("AD_ReportView_ID") > 0) {

					packOut.createReportview(rs1.getInt("AD_ReportView_ID"),
							atts, document);
				}
				if (rs1.getString("IsReport").equals('Y')
						&& rs1.getInt("AD_PrintFormat_ID") > 0) {

					packOut.createPrintFormat(rs1.getInt("AD_PrintFormat_ID"),
							atts, document);
				}
				if (rs1.getInt("AD_Workflow_ID") > 0) {

					packOut.createWorkflow(rs1.getInt("AD_Workflow_ID"), atts,
							document);
				}
				atts = createProcessBinding(atts, m_Process);
				document.startElement("", "", "process", atts);
				// processpara tags
				String sqlP = "SELECT * FROM AD_PROCESS_PARA WHERE AD_PROCESS_ID = "
						+ AD_Process_ID;
				PreparedStatement pstmtP = null;
				pstmtP = DB.prepareStatement(sqlP, getTrxName(ctx));
				try {
					ResultSet rsP = pstmtP.executeQuery();
					while (rsP.next()) {
						if (rsP.getInt("AD_Reference_ID") > 0)
							packOut.createReference(rsP
									.getInt("AD_Reference_ID"), atts, document);
						if (rsP.getInt("AD_Reference_Value_ID") > 0)
							packOut.createReference(rsP
									.getInt("AD_Reference_Value_ID"), atts,
									document);

						createProcessPara(ctx, document, rsP
								.getInt("AD_Process_Para_ID"));
					}
					rsP.close();
					pstmtP.close();
					pstmtP = null;
				} catch (Exception e) {
					log.log(Level.SEVERE, "getProcess_Para", e);
					if (e instanceof SAXException)
						throw (SAXException) e;
					else if (e instanceof SQLException)
						throw new DatabaseAccessException("Failed to export process.", e);
					else if (e instanceof RuntimeException)
						throw (RuntimeException) e;
					else
						throw new RuntimeException("Failed to export process.", e);
				} finally {
					try {
						if (pstmtP != null)
							pstmtP.close();
					} catch (Exception e) {
					}
					pstmtP = null;
				}
				document.endElement("", "", "process");
			}
			rs1.close();
			pstmt1.close();
			pstmt1 = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "getProcess", e);
		} finally {
			try {
				if (pstmt1 != null)
					pstmt1.close();
			} catch (Exception e) {
			}
			pstmt1 = null;
		}

	}

	private void createProcessPara(Properties ctx, TransformerHandler document,
			int AD_Process_Para_ID) throws SAXException {
		Env.setContext(ctx, X_AD_Process_Para.COLUMNNAME_AD_Process_Para_ID,
				AD_Process_Para_ID);
		paraHandler.create(ctx, document);
		ctx.remove(X_AD_Process_Para.COLUMNNAME_AD_Process_Para_ID);
	}

	private AttributesImpl createProcessBinding(AttributesImpl atts,
			X_AD_Process m_Process) {
		String sql = null;
		String name = null;
		atts.clear();

		atts.addAttribute("", "", "Name", "CDATA",
				(m_Process.getName() != null ? m_Process.getName() : ""));

		if (m_Process.getAD_Workflow_ID() > 0) {
			sql = "SELECT Name FROM AD_Workflow WHERE AD_Workflow_ID=?";
			name = DB.getSQLValueString(null, sql, m_Process
					.getAD_Workflow_ID());
			atts.addAttribute("", "", "ADWorkflowNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADWorkflowNameID", "CDATA", "");
		if (m_Process.getAD_Process_ID() > 0) {
			sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
			name = DB
					.getSQLValueString(null, sql, m_Process.getAD_Process_ID());
			atts.addAttribute("", "", "ADProcessNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADProcessNameID", "CDATA", "");
		if (m_Process.getAD_PrintFormat_ID() > 0) {
			sql = "SELECT Name FROM AD_PrintFormat WHERE AD_PrintFormat_ID=?";
			name = DB.getSQLValueString(null, sql, m_Process
					.getAD_PrintFormat_ID());
			atts.addAttribute("", "", "ADPrintFormatNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADPrintFormatNameID", "CDATA", "");
		if (m_Process.getAD_ReportView_ID() > 0) {
			sql = "SELECT Name FROM AD_ReportView WHERE AD_ReportView_ID=?";
			name = DB.getSQLValueString(null, sql, m_Process
					.getAD_ReportView_ID());
			atts.addAttribute("", "", "ADReportViewNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADReportViewNameID", "CDATA", "");
		atts.addAttribute("", "", "AccessLevel", "CDATA", (m_Process
				.getAccessLevel() != null ? m_Process.getAccessLevel() : ""));
		atts.addAttribute("", "", "Classname", "CDATA", (m_Process
				.getClassname() != null ? m_Process.getClassname() : ""));
		atts.addAttribute("", "", "Description", "CDATA", (m_Process
				.getDescription() != null ? m_Process.getDescription() : ""));
		atts.addAttribute("", "", "EntityType", "CDATA", (m_Process
				.getEntityType() != null ? m_Process.getEntityType() : ""));
		atts.addAttribute("", "", "Help", "CDATA",
				(m_Process.getHelp() != null ? m_Process.getHelp() : ""));
		atts.addAttribute("", "", "isBetaFunctionality", "CDATA", (m_Process
				.isBetaFunctionality() == true ? "true" : "false"));
		atts.addAttribute("", "", "isDirectPrint", "CDATA", (m_Process
				.isDirectPrint() == true ? "true" : "false"));
		atts.addAttribute("", "", "isReport", "CDATA",
				(m_Process.isReport() == true ? "true" : "false"));
		atts.addAttribute("", "", "isActive", "CDATA",
				(m_Process.isActive() == true ? "true" : "false"));
		atts.addAttribute("", "", "ProcedureName", "CDATA",
				(m_Process.getProcedureName() != null ? m_Process
						.getProcedureName() : ""));
		atts.addAttribute("", "", "StatisticCount", "CDATA", "0");
		atts.addAttribute("", "", "StatisticSeconds", "CDATA", "0");
		atts.addAttribute("", "", "Value", "CDATA",
				(m_Process.getValue() != null ? m_Process.getValue() : ""));
		atts.addAttribute("", "", "WorkflowValue", "CDATA",
				(m_Process.getWorkflowValue() != null ? m_Process
						.getWorkflowValue() : ""));
		return atts;
	}
}

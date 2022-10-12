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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.pr.process;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;

import org.adempiere.core.domains.models.I_HR_Process;
import org.adempiere.core.domains.models.I_HR_ProcessReport;
import org.adempiere.core.domains.models.I_RV_HR_ProcessDetail;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Env;
import org.compiere.util.ExtensionFileFilter;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRPayroll;
import org.eevolution.hr.model.MHRProcess;
import org.eevolution.services.dsl.ProcessBuilder;
import org.spin.hr.util.AbstractPayrollReportExport;
import org.spin.pr.model.MHRProcessReport;
import org.spin.pr.model.MHRProcessReportTemplate;
import org.adempiere.core.domains.models.X_RV_HR_ProcessDetail;


/**
 * Process Report for Payroll Movements: show reports from movements and template
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1539">
 * 		@see FR [ 1539 ] Add Process for Reporting</a>
 *
 */
public class PayrollProcessReport extends PayrollProcessReportAbstract {
	/**	Default Print Format	*/
	private int printFormatId = 0;
	
	@Override
	protected void prepare() {
		super.prepare();
		//	For Rpt Process
		if(getHRProcessId() == 0
				&& getTable_ID() == I_HR_Process.Table_ID
				&& getRecord_ID() > 0) {
			setHRProcessId(getRecord_ID());
		}
	}

	@Override
	protected String doIt() throws Exception {
		//	Get Print Format
		MHRProcessReport processReport = MHRProcessReport.get(getCtx(), getProcessReportId());
		//	Action Export File
		if(processReport.isCanExport()) {
			//	Do It
			return exportToFile(processReport);
		}
		String name = processReport.getName();
		String printName = processReport.getPrintName();
		String textMsg = processReport.getTextMsg();
		String receiptFooterMsg = processReport.getReceiptFooterMsg();
		//	For template
		if(getProcessReportTemplateId() != 0) {
			MHRProcessReportTemplate template = MHRProcessReportTemplate.get(getCtx(), getProcessReportTemplateId());
			if(template != null) {
				printFormatId = template.getAD_PrintFormat_ID();
				if(!Util.isEmpty(template.getName())) {
					name = template.getName();
				}
				if(!Util.isEmpty(template.getPrintName())) {
					printName = template.getPrintName();
				}
				if(!Util.isEmpty(template.getTextMsg())) {
					textMsg = template.getTextMsg();
				}
				if(!Util.isEmpty(template.getReceiptFooterMsg())) {
					receiptFooterMsg = template.getReceiptFooterMsg();
				}
			}
		}
		//	Valid from Parameter
		if(printFormatId == 0) {
			printFormatId = processReport.getAD_PrintFormat_ID();
		}
		//	Get from Payroll
		if(printFormatId == 0) {
			//	Valid Payroll
			if(getPayrollId() != 0) {
				MHRPayroll payroll = MHRPayroll.getById(getCtx(), getPayrollId(), get_TrxName());
				printFormatId = payroll.getAD_PrintFormat_ID();
				//	Log
				log.info("Print Format from Payroll");
			}
		}
		//	Get From Process
		if(printFormatId == 0) {
			//	Valid Process
			if(getHRProcessId() != 0) { 
				//	Get Process
				MHRProcess process = new MHRProcess(getCtx(), getHRProcessId(), get_TrxName());
				//	Get Payroll from Process
				MHRPayroll payroll = MHRPayroll.getById(getCtx(), process.getHR_Payroll_ID(), get_TrxName());
				//	Get Print Format
				printFormatId = payroll.getAD_PrintFormat_ID();
				//	Log
				log.info("Print Format from Process");
			}
		}
		//	Valid Print Format
		if(printFormatId == 0)
			throw new AdempiereException("@AD_PrintFormat_ID@ @NotFound@");
		//	Get Format & Data
		MPrintFormat format = MPrintFormat.get (getCtx(), printFormatId, false);
		//	Get Print Format
		MQuery query = new MQuery(I_HR_ProcessReport.Table_Name);
			
		query.addRestriction(I_HR_ProcessReport.COLUMNNAME_HR_ProcessReport_ID, "=", getProcessReportId());
		
		//	Create object Print Info 
		PrintInfo printInfo = 
				new PrintInfo(Msg.translate(getCtx(), I_HR_ProcessReport.COLUMNNAME_HR_ProcessReport_ID), 
						getTable_ID(), getProcessReportId());
			
		printInfo.setAD_Table_ID(getTable_ID());
		
		//	If exists Print Format 
		if(format != null)	{
			//	Engine
			//	If report engine is not null
			if(format.getJasperProcess_ID() > 0) { 
				//	Execute Process
				ProcessBuilder builder = ProcessBuilder.create(getCtx())
					.process(format.getJasperProcess_ID())
					.withTitle(getProcessInfo().getTitle())
					.withRecordId(I_HR_Process.Table_ID, getProcessReportId())
					.withParameter("ProcessReportName", name)
					.withParameter("ProcessReportPrintName", printName)
					.withParameter("ProcessReportTextMsg", textMsg)
					.withParameter("ProcessReportReceiptFooterMsg", receiptFooterMsg);
				//	Add HR Process for internal window
				if(getRecord_ID() != 0) {
					builder.withParameter(HR_PROCESS_ID, new BigDecimal(getHRProcessId()));
				}
				//	Add current parameters
				Arrays.asList(getParameter()).forEach(parameter -> builder.withParameter(parameter.getParameterName(), parameter.getParameter(), parameter.getParameter_To()));
				//	Run it
				builder.withPrintPreview();
				ProcessInfo processInfo = builder.execute(get_TrxName());
				getProcessInfo().setPDFReport(processInfo.getPDFReport());
				getProcessInfo().setReportAsFile(processInfo.getPDFReport());
			}
		}
		//	
		return "Ok";
	}
	
	/**
	 * Load detail of payroll
	 * @param payrollReport
	 * @return
	 * @throws Exception
	 */
	public List<X_RV_HR_ProcessDetail> loadDetail(MHRProcessReport payrollReport) throws Exception {
		if(payrollReport.getFileExportClass() == null
				|| payrollReport.getFileExportClass().length() == 0)
			throw new AdempiereException("@FileExportClass@ @NotFound@");	
		//	Do It
		List<Object> parameters = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//	Get valid parameters
		for(ProcessInfoParameter parameter : getParameter()) {
			if(parameter.getParameter() == null
					&& parameter.getParameter_To() == null
					|| parameter.getParameterName().equals(HR_PROCESSREPORTTEMPLATE_ID)) {
				continue;
			}
			//	
			if(parameter.getParameter_To() == null) {
				if(parameter.getParameter() != null) {
					if(whereClause.length() > 0) {
						whereClause.append(" AND ");
					}
					whereClause.append(parameter.getParameterName()).append("=?");
					parameters.add(parameter.getParameter());
				}
			} else {
				if(parameter.getParameter() != null) {
					if(whereClause.length() > 0) {
						whereClause.append(" AND ");
					}
					whereClause.append(parameter.getParameterName()).append(">=?");
					parameters.add(parameter.getParameter());
				}
				//	For To
				if(parameter.getParameter_To() != null) {
					if(whereClause.length() > 0) {
						whereClause.append(" AND ");
					}
					whereClause.append(parameter.getParameterName()).append("<=?");
					parameters.add(parameter.getParameter_To());
				}
			}
		}
		//	Get result
		return new Query(getCtx(), I_RV_HR_ProcessDetail.Table_Name, whereClause.toString(), get_TrxName())
			.setClient_ID()
			.setParameters(parameters)
			.setOnlyActiveRecords(true)
			.<X_RV_HR_ProcessDetail>list();
	}
	
	/**
	 * Export to file
	 * @return String
	 */
	public String exportToFile(MHRProcessReport payrollReport) throws Exception {
		List<X_RV_HR_ProcessDetail> detail = loadDetail(payrollReport);
		if (!Ini.isClient()) {
			return "Ok";
		}
		//	Valid Array
		if(detail == null
				|| detail.size() == 0)
			return "Ok";
		//	
		AbstractPayrollReportExport reportExport = payrollReport.getPayrollReportExport();
		//	if null
		if(reportExport == null) {
			return "Ok";
		}
		//	
		File exportFile = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setDialogTitle(Msg.getMsg(getCtx(), "Export") + ": " + payrollReport.getName());
		if(!Util.isEmpty(reportExport.getExtension())) {
			//	Add only extension
			chooser.addChoosableFileFilter(new ExtensionFileFilter(reportExport.getExtension(), payrollReport.getName()));
		}
		//
		if (chooser.showSaveDialog(Env.getWindow(getProcessInfo().getWindowNo())) != JFileChooser.APPROVE_OPTION) {
			return "Ok";
		}
		exportFile = ExtensionFileFilter.getFile(chooser.getSelectedFile(), chooser.getFileFilter());
		String ext = exportFile.getPath();
		//	no extension
		if (ext.lastIndexOf('.') == -1) {
			if(Util.isEmpty(reportExport.getCompleteFileName())) {
				return "@Error@: @FileInvalidExtension@";
			}
			exportFile = new File(exportFile.getParentFile(), reportExport.getCompleteFileName());
		}
		//	
		ext = ext.substring(ext.lastIndexOf('.')+1).toLowerCase();
		log.config( "File=" + exportFile.getPath() + "; Type=" + ext);
		exportFile.createNewFile();
		//	Export
		boolean isExported = payrollReport.exportToFile(exportFile, detail);
		//	
		if (isExported && Ini.isClient()) {
			Env.startBrowser(exportFile.toURI().toString());
		}
		//	Show Path
		return "@Exported@ : " + exportFile.getPath();
	}
}

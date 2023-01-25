package com._3e.ADInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Lookup;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MBankStatement;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MOrder;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPayment;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ServerProcessCtl;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.NamePair;
import org.compiere.util.Trx;
import org.compiere.wf.MWFProcess;
import org.compiere.wf.MWorkflow;
import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;
import pl.x3E.adInterface.GetProcessParamsDocument;
import pl.x3E.adInterface.LookupValue;
import pl.x3E.adInterface.LookupValues;
import pl.x3E.adInterface.ProcessParam;
import pl.x3E.adInterface.ProcessParamList;
import pl.x3E.adInterface.ProcessParams;
import pl.x3E.adInterface.ProcessParamsDocument;
import pl.x3E.adInterface.RunProcess;
import pl.x3E.adInterface.RunProcessDocument;
import pl.x3E.adInterface.RunProcessResponse;
import pl.x3E.adInterface.RunProcessResponseDocument;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;

/*
 * ADEMPIERE/COMPIERE
 *
 * @author Víctor Perez, victor.perez@e-evolution.com, www.e-evolution.com , e-Evolution
 * <li>#2512 Problem with ADempiere web service when execute process for jasper report or ADempiere Report Engine
 * <li> https://github.com/adempiere/adempiere/issues/2512
 *
 * GridField na GridField
 * GridTab na GridTab
 */

public class Process {
	
	private static CLogger	log = CLogger.getCLogger(Process.class);

	public static ProcessParamsDocument getProcessParams(CompiereService compiereService, GetProcessParamsDocument getProcessParamsDocument) throws XFireFault {
		ProcessParamsDocument processParamsDocument = ProcessParamsDocument.Factory.newInstance();
		ProcessParams processParams = processParamsDocument.addNewProcessParams();
		ProcessParamList processParamList = processParams.addNewParams();

		int menuId = getProcessParamsDocument.getGetProcessParams().getADMenuID();
		int processId = getProcessParamsDocument.getGetProcessParams().getADProcessID();
		MProcess process  = null;

		if (menuId > 0 && processId == 0)
			process = MProcess.getFromMenu(compiereService.getM_ctx(), menuId);
		else if (menuId == 0 && processId > 0)
			process = new MProcess(compiereService.getM_ctx(), processId, null);

		if (process != null)
		{

			processParams.setDescription(process.getDescription());
			processParams.setHelp(process.getHelp());
			processParams.setName(process.getName());
			processParams.setADProcessID(process.getAD_Process_ID());

			MProcessPara[] parameter = process.getParameters();
			for (int i = 0; i < parameter.length; i++)
			{
				MProcessPara para = parameter[i];

				ProcessParam processParam = processParamList.addNewParam();
				processParam.setName(para.getName());
				processParam.setDescription(para.getDescription());
				processParam.setDisplayType(para.getAD_Reference_ID());
				processParam.setIsMandatory(para.isMandatory());
				processParam.setFieldLength(para.getFieldLength());
				processParam.setIsRange(para.isRange());
				processParam.setColumnName(para.getColumnName());
				processParam.setDefaultValue(para.getDefaultValue());
				processParam.setDefaultValue2(para.getDefaultValue2());
				if (para.getDefaultValue()!=null )
				{				
					if (DisplayType.isDate(para.getAD_Reference_ID()))
					{
						if (para.getDefaultValue().indexOf( "@#Date@")>=0) {
							//Object t = Env.getContextAsDate( cs.getM_ctx(), "#Date" );
							//String t = Env.getContext( cs.getM_ctx(), "#Date" );
							String t = compiereService.dateFormat.format(Env.getContextAsDate(compiereService.getM_ctx(), "#Date"));

							processParam.setDefaultValue(t); //cs.dateFormat.format( t ));
						}
					} else
					if (DisplayType.YesNo ==para.getAD_Reference_ID() )
					{
						if ("Y".equalsIgnoreCase(para.getDefaultValue()))
							processParam.setDefaultValue("true");
						else
							processParam.setDefaultValue("false");
					}
				} else {
					if (DisplayType.YesNo ==para.getAD_Reference_ID())
						processParam.setDefaultValue("false");
				}

				if (para.getDefaultValue2()!=null)
				{				
					if (DisplayType.isDate(para.getAD_Reference_ID()))
					{						
						if (para.getDefaultValue2().indexOf( "@#Date@")>=0) {
							//Object t = Env.getContextAsDate( cs.getM_ctx(), "#Date" );
							//String t = Env.getContext( cs.getM_ctx(), "#Date" );
							String t = compiereService.dateFormat.format(Env.getContextAsDate(compiereService.getM_ctx(), "#Date"));
							processParam.setDefaultValue2(t); //cs.dateFormat.format( t ) );
						}							
					}
				}
				
				
				if (para.isLookup())
				{
					LookupValues lvs = processParam.addNewLookup();
					Lookup lookup = para.getLookup();	
					try {
					ADLookup.fillLookupValues( lvs, lookup, para.isMandatory(), false /*isReadOnly*/ );
					} catch (Exception ex) {
						System.out.println("getProcessParams exception: " +ex.getMessage());
						ex.printStackTrace();
					}
				}
			}
		}

		return processParamsDocument;
	}

	/**
	 * Create Process Page
	 * @param compiereService
	 * @param runProcessDocument
	 * @return
	 */
	public static RunProcessResponseDocument runProcess(CompiereService compiereService, RunProcessDocument runProcessDocument) {
		RunProcessResponseDocument runProcessResponseDocument = RunProcessResponseDocument.Factory.newInstance();
		RunProcessResponse runProcessResponse = runProcessResponseDocument.addNewRunProcessResponse();
		RunProcess runProcess = runProcessDocument.getRunProcess();
		int processId = runProcess.getADProcessID();
		int recordId = runProcess.getADRecordID();

		MProcess process = MProcess.get(compiereService.getM_ctx(), processId);
		//	need to check if Role can access
		if (process == null) {
			// WebDoc doc = WebDoc.createWindow("Process not found");
			runProcessResponse.setError("Process not found");
			runProcessResponse.setIsError(true);
			return runProcessResponseDocument;
		}

		// Evaluate DocAction, if call have DocAction parameter, then try to set DocAction before calling workflow process
		Optional<String> maybeDocAction = Optional.ofNullable(runProcess.getDocAction());
		maybeDocAction.ifPresent(docAction -> {
			if (process.getAD_Workflow_ID() > 0) {
				MWorkflow workflow = MWorkflow.get(compiereService.getM_ctx(), process.getAD_Workflow_ID());
				if (workflow.getWorkflowType().equals(MWorkflow.WORKFLOWTYPE_DocumentProcess)) {
					// - get the table associated with the workflow document
					// - set DocAction in such table
					// get the PO for the tablename and record ID
					Optional<MTable> maybeTable = Optional.ofNullable(MTable.get(compiereService.getM_ctx(), workflow.getAD_Table_ID()));
					maybeTable.ifPresent(table -> {
						Optional<PO> maybePO = Optional.ofNullable(table.getPO(recordId, null));
						maybePO.ifPresent(po -> {
							po.set_ValueOfColumn("DocAction", docAction);
							po.saveEx();
						});

					});
				}
			}
		});

		try {
			//	Create Process Instance
			MPInstance instance = fillParameter(compiereService, runProcess.getParamValues(), process);
			instance.setRecord_ID(recordId);
			instance.saveEx();

			ProcessInfo processInfo = new ProcessInfo(process.getName(), process.getAD_Process_ID());
			processInfo.setAD_User_ID(Env.getAD_User_ID(compiereService.getM_ctx()));
			processInfo.setAD_Client_ID(Env.getAD_Client_ID(compiereService.getM_ctx()));
			processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			processInfo.setRecord_ID(recordId);
			//	Start
			if (process.isWorkflow()) {
				try {
					int workflowId = process.getAD_Workflow_ID();
					MWorkflow workflow = MWorkflow.get(Env.getCtx(), workflowId);
					Optional<MWFProcess> maybeWorkflowProcess = Optional.ofNullable(workflow.startWait(processInfo));    //	may return null
					maybeWorkflowProcess.ifPresent(workflowProcess -> {
						runProcessResponse.setSummary(processInfo.getSummary());
						runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
						runProcessResponse.setIsError(false);
					});
					return runProcessResponseDocument;
				} catch (Exception exeception) {
					runProcessResponse.setError(exeception.getMessage());
					runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
					runProcessResponse.setIsError(true);
					return runProcessResponseDocument;
				}
			}

			if (process.isJavaProcess() && !process.isReport()) {
				Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
				process.processIt(processInfo, trx);
				runProcessResponse.setSummary(processInfo.getSummary());
				runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
				runProcessResponse.setIsError(false);
			} else if (process.isReport()) {
				runProcessResponse.setIsReport(true);
				try {
					Optional<ReportEngine> maybeReportEngine = Optional.ofNullable(ReportEngine.get(Env.getCtx(), processInfo));
					if (maybeReportEngine.isPresent()) {
						ReportEngine reportEngine = maybeReportEngine.get();
						Optional<MPrintFormat> maybePrintFormat = Optional.ofNullable(reportEngine.getPrintFormat());
						if (maybePrintFormat.isPresent()) {
							MPrintFormat printFormat = maybePrintFormat.get();
							if (printFormat.isTableBased()) {
								final String fileType = "xls";
								CharArrayWriter charArrayWriter = new CharArrayWriter();
								ReportEngineEx.createEXCEL_HTML_wr(
										reportEngine, compiereService.getM_ctx(),
										charArrayWriter,
										false,
										reportEngine.getPrintFormat().getLanguage());
								Optional<String> mybeReportData = Optional.ofNullable(charArrayWriter.toString());
								mybeReportData.ifPresent(reportData -> {
									runProcessResponse.setData(reportData.getBytes());
									runProcessResponse.setReportFormat(fileType);
									runProcessResponse.setSummary(processInfo.getSummary());
									runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
									runProcessResponse.setIsError(false);
								});
							} else {
								final String fileType = "pdf";
								byte reportDataBytes[] = reportEngine.createPDFData();
								runProcessResponse.setData(reportDataBytes);
								runProcessResponse.setReportFormat(fileType);
								runProcessResponse.setSummary(processInfo.getSummary());
								runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
								runProcessResponse.setIsError(false);
							}
						} else {
							runProcessResponse.setError(" Print Format Not Found");
							runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
							runProcessResponse.setIsError(true);
						}
					} else {
						Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
						final String fileType = "pdf";
						processInfo.setPrintPreview(false);
						processInfo.setIsBatch(true);
						ServerProcessCtl worker = new ServerProcessCtl(null,  processInfo, trx);
						worker.run();
						Optional<File> maybePDF = Optional.ofNullable(processInfo.getPDFReport());
						if (maybePDF.isPresent()) {
							File pdf = maybePDF.get();
							InputStream inputStream = new FileInputStream(pdf);
							byte[] reportDataBytes = new byte[(int) pdf.length()];
							inputStream.read(reportDataBytes);
							inputStream.close();
							runProcessResponse.setData(reportDataBytes);
							runProcessResponse.setReportFormat(fileType);
							runProcessResponse.setSummary(processInfo.getSummary());
							runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
							runProcessResponse.setIsError(false);
						} else {
							runProcessResponse.setError("PDF Cannot be generate");
							runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
							runProcessResponse.setIsError(true);
						}
					}
				} catch (Exception exception) {
					runProcessResponse.setError("Cannot create report");
					runProcessResponse.setLogInfo(processInfo.getLogInfo(true));
					runProcessResponse.setIsError(true);
				}
			}
			compiereService.getM_ctx().put("AD_PInstance_ID=" + instance.getAD_PInstance_ID(), "ok");
		} catch (Exception exception) {
			runProcessResponse.setError("Cannot create report");
			runProcessResponse.setLogInfo(exception.getMessage());
			runProcessResponse.setIsError(true);
		}
		return runProcessResponseDocument;
	}    //	createProcessPage


	private static MPInstance fillParameter(CompiereService compiereService, DataRow dataRow, MProcess process) throws Exception {
		MPInstance instance = new MPInstance(process, 0);
		DataField dataFields[] = dataRow.getFieldArray();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		for (int i = 0; i < dataFields.length; i++)
			hashMap.put(dataFields[i].getColumn(), dataFields[i].getVal());
		//
		MPInstancePara[] instanceParameters = instance.getParameters();
		for (int pi = 0; pi < instanceParameters.length; pi++) {
			MPInstancePara iPara = instanceParameters[pi];
			String key = iPara.getParameterName();
			MProcessPara processParameter = process.getParameter(key);
			if (processParameter == null)
			{
				log.log(Level.SEVERE, "Parameter not found: " + key);
				continue;
			}
			int displayType = processParameter.getAD_Reference_ID();
			
			String valueString = null;
			Object ob = hashMap.get(key);
			if (ob!=null )
				valueString = ob.toString();
			String valueString2 = null;
			if (processParameter.isRange())
			{
				ob = hashMap.get(key + "_2");
				if (ob!=null)
				valueString2 = ob.toString(); 
			}
			log.fine("fillParameter - " + key + " = " + valueString);
			Object value = valueString;
			if (valueString != null && valueString.length() == 0)
				value = null;
			if (value != null && (DisplayType.List == displayType ||
					DisplayType.TableDir== displayType ||
					DisplayType.Table== displayType)&& value.equals("-1"))
				value= null;
			//	No Value
			if (value == null && DisplayType.YesNo != processParameter.getAD_Reference_ID())
			{
				if (processParameter.isMandatory())
					throw new Exception(" Parameter " + processParameter.getName() + " is required.");
			}
			else
			{
				//	Convert to Type
				try
				{
					if (DisplayType.isNumeric(displayType) 
						|| DisplayType.isID(displayType))
					{
						BigDecimal bd = null;
						if (value instanceof BigDecimal)
							bd = (BigDecimal)value;
						else if (value instanceof Integer)
							bd = new BigDecimal (((Integer)value).intValue());
						else
							bd = new BigDecimal (value.toString());
						iPara.setP_Number(bd);
						log.fine("fillParameter - " + key
								+ " = " + valueString + " (=" + bd + "=)");

						if (processParameter.isRange())
						{
							bd = null;
							bd = new BigDecimal (valueString2.toString());
							iPara.setP_Number_To( bd );
						}
					}
					else if (DisplayType.isDate(displayType))
					{
						java.util.Date d;
						if (displayType == DisplayType.DateTime)
							d = compiereService.dateFormat.parse(value.toString());
							
						else    // TODO: datetime
							d = compiereService.dateFormat.parse(value.toString());
							//d = m_cs.dateTimeFormat.parse(value.toString());
						 
						Timestamp ts = null;
						ts = new Timestamp(d.getTime());
						iPara.setP_Date(ts);

						if (processParameter.isRange())
						{
							if (displayType == DisplayType.DateTime)
								d = compiereService.dateFormat.parse(valueString2);
								//d = m_cs.dateTimeFormat.parse(valueString2);
							else
							{
								if (valueString2 == null || valueString2.length() == 0)
									d = new java.util.Date();
								else
									d = compiereService.dateFormat.parse(valueString2); //TODO: datetime
							}
							
							ts = new Timestamp(d.getTime());
							iPara.setP_Date_To(ts );
						}

						
						log.fine("fillParameter - " + key
							+ " = " + valueString + " (=" + ts + "=)");
					} else if (DisplayType.YesNo == processParameter.getAD_Reference_ID())
					{
						String bv = "N";
						if (value == null)
							bv = "N";
						else
						//if (value.toString().toLowerCase().charAt(0)=='t')
						{
							if ("true".equalsIgnoreCase(valueString) || "y".equalsIgnoreCase(valueString)) bv = "Y"; else bv = "N";
						}
							//bv ="Y";
						iPara.setP_String(bv);
					}
					else
					{
						iPara.setP_String(value.toString());
					}

					if (processParameter.isLookup()) // kolec - ustawia wartosc dla parametru Lookup
					{
						Lookup lok = processParameter.getLookup();
						if (lok != null)
						{
							NamePair np = lok.getDirect( value, false, false);
							if (np!=null)
							{
								iPara.setInfo( np.getName() );
							}
						}
					} else
					{
						if (value != null)
						  iPara.setInfo( value.toString() );
						if (valueString2 != null)
							iPara.setInfo_To( valueString2 );
					}
					
					//
					iPara.save();
				}
				catch (Exception e)
				{
					log.warning("fillParameter - " + key
						+ " = " + valueString + " (" + value
						+ ") " + value.getClass().getName()
						+ " - " + e.getLocalizedMessage());
				}
			}	//	not null
		}	//	instance parameter loop

		instance.save(); // kolec - tego chyba brakowalo

		return instance;
	}	//	fillParameter
	
	private static void renderOption(LookupValues lvs, java.util.Hashtable assoc, String idx)
	{
		Object ob = assoc.get(idx);
		if(ob == null) return;
		LookupValue lv = lvs.addNewLv();
		lv.setKey(idx);// o.setValue(idx);
		lv.setVal(ob.toString());		
	}
	
	public static void renderDocActionOptions(LookupValues lvs, GridTab tab)
	{
		String sql;
		java.util.Hashtable h = new java.util.Hashtable(); 
		if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
			sql = "SELECT Value, Name, Description FROM AD_Ref_List "
				+ "WHERE AD_Reference_ID=135 ORDER BY Name";
		else
			sql = "SELECT l.Value, t.Name, t.Description "
				+ "FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID"
				+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
				+ " AND l.AD_Reference_ID=135 ORDER BY t.Name";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				if (description == null)
					description = "";
				
				h.put(value, name);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return;
		}
		Object Processing = tab.getValue("Processing");
		String DocStatus = (String)tab.getValue("DocStatus");
		String DocAction = (String)tab.getValue("DocAction");
		String OrderType = Env.getContext(Env.getCtx(), tab.getWindowNo(), "OrderType");
		String IsSOTrx = Env.getContext(Env.getCtx(), tab.getWindowNo(), "IsSOTrx");
		int m_AD_Table_ID = tab.getAD_Table_ID();

//		Locked
		if (Processing != null)
		{
			boolean locked = "Y".equals(Processing);
			if (!locked && Processing instanceof Boolean)
				locked = ((Boolean)Processing).booleanValue();
			if (locked)
				renderOption(lvs, h, DocumentEngine.ACTION_Unlock);
		}

		//	Approval required           ..  NA
		if (DocStatus.equals(DocumentEngine.STATUS_NotApproved))
		{
			renderOption(lvs, h, DocumentEngine.ACTION_Prepare);
			renderOption(lvs, h, DocumentEngine.ACTION_Void);
		}
		//	Draft/Invalid				..  DR/IN
		else if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
			|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
		{
			renderOption(lvs, h, DocumentEngine.ACTION_Complete);
			renderOption(lvs, h, DocumentEngine.ACTION_Void);
		//	options[index++] = DocumentEngine.ACTION_Prepare;
		}
		//	In Process                  ..  IP
		else if (DocStatus.equals(DocumentEngine.STATUS_InProgress)
			|| DocStatus.equals(DocumentEngine.STATUS_Approved))
		{
			renderOption(lvs, h, DocumentEngine.ACTION_Complete);
			renderOption(lvs, h, DocumentEngine.ACTION_Void);
		}
		//	Complete                    ..  CO
		else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
		{
			renderOption(lvs, h, DocumentEngine.ACTION_Close);
		}
		//	Waiting Payment
		else if (DocStatus.equals(DocumentEngine.STATUS_WaitingPayment)
			|| DocStatus.equals(DocumentEngine.STATUS_WaitingConfirmation))
		{
			renderOption(lvs, h, DocumentEngine.ACTION_Void);
			renderOption(lvs, h, DocumentEngine.ACTION_Prepare);
		}
		//	Closed, Voided, REversed    ..  CL/VO/RE
		else if (DocStatus.equals(DocumentEngine.STATUS_Closed) 
			|| DocStatus.equals(DocumentEngine.STATUS_Voided) 
			|| DocStatus.equals(DocumentEngine.STATUS_Reversed))
			return;

		/********************
		 *  Order
		 */
		if (m_AD_Table_ID == MOrder.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
				|| DocStatus.equals(DocumentEngine.STATUS_InProgress)
				|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Prepare);
				//	Draft Sales Order Quote/Proposal - Process
				if ("Y".equals(IsSOTrx)
					&& ("OB".equals(OrderType) || "ON".equals(OrderType)))
					DocAction = DocumentEngine.ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_ReActivate);
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  Shipment
		 */
		else if (m_AD_Table_ID == MInOut.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Correct);
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  Invoice
		 */
		else if (m_AD_Table_ID == MInvoice.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Correct);
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  Payment
		 */
		else if (m_AD_Table_ID == MPayment.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Correct);
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  GL Journal
		 */
		else if (m_AD_Table_ID == MJournal.Table_ID || m_AD_Table_ID == MJournalBatch.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Correct);
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Accrual);
			}
		}
		/********************
		 *  Allocation
		 */
		else if (m_AD_Table_ID == MAllocationHdr.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Reverse_Correct);
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  Bank Statement
		 */
		else if (m_AD_Table_ID == MBankStatement.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				renderOption(lvs, h, DocumentEngine.ACTION_Void);
			}
		}

	}
	
	/*private static void ProcessDocAction(GridField field, int record_id, WWindowStatus ws)
	throws IOException
	{
		//select sel = new select();
		LookupValues lvs = LookupValues.Factory.newInstance(); //sel.setName("SetDocAction");
		renderDocActionOptions(lvs, ws.curTab);
		//frm.addElement(new input("hidden", "action", "DocAction"));
		
		//center.addElement( WebUtil.createClosePopupButton() );

	}*/
	
	public void doPost() throws  IOException {
		String column_name = "";//WebUtil.getParameter(request, "ColumnName");
		String action = "";//WebUtil.getParameter(request, "action");
	
		WWindowStatus ws = null; //WWindowStatus.get(request);
		
		
			String DA = "";  //WebUtil.getParameter(request, "SetDocAction"); 
			ws.curTab.setValue("DocAction", DA);
		
			if (ws.curTab.needSave(true, false)) //slain - nie wyrzucaj bledu, jesli nie musiales zapisac
				//if (! cmd_save(ws))
				{
				
					//doc.addWindowCenter(false).addElement("B��d zapisu..." );
					//return;
				}
	
			GridField f = ws.curTab.getField( column_name );
			int process_id = f.getAD_Process_ID();
			int record_id = ws.curTab.getRecord_ID();
			String qs = 
				"AD_Process_ID="+Integer.toString(process_id) +"&"+
				"AD_Record_ID="+Integer.toString(record_id);
			
			//RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WProcess?runas=get&"+qs);
			//disp.forward(request, response);

			return;
	}   //  doPost

	
	
}

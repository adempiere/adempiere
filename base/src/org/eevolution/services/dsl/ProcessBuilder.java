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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.services.dsl;


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;


/**
 * DSL Process Builder allow define and execute a process
 * This class is based on builder pattern https://en.wikipedia.org/wiki/Builder_pattern
 * This class is based on https://en.wikipedia.org/wiki/Fluent_interface
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 28/12/15.
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 244 ] Is Selection flag
 *		@see https://github.com/adempiere/adempiere/issues/244
 *		<li> FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 */
public class ProcessBuilder {

    private ProcessBuilder thisBuilder;
    private Properties context;
    private ProcessInfo processInfo;
    private String title;
    private Integer processId;
    private Integer recordId;
    private Integer tableId;
    private Integer windowNo;
    private Integer seqNo;
    private Integer clientId;
    private Integer userId;
    private MPInstance instance;
    private MProcess process;
    private ASyncProcess parent;
    private List<Integer> selectedRecordsIds;
    private int tableSelectionId;
    private boolean isBatch = false;
    private boolean isPrintPreview = false;
    private String reportExportFormat = null;

    private Boolean isManagedTransaction = true;
    private Boolean isExecuteUsingSystemRole =  false;
    /**	Multi-Selection Parameters	*/
    private LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection = null;
	private String aliasTableSelection;

    /**
     * Private constructor is called when an instance is created
     * @param context
     */
    private ProcessBuilder(Properties context) {
        this.context = context;
        this.recordId = 0;
        this.windowNo = 0;
        this.seqNo = 0;
        this.tableId = 0;
        this.parent = null;
        this.selectedRecordsIds = new ArrayList<>();
        this.tableSelectionId = 0;
        this.thisBuilder = this;
    }

    /**
     * Create a new Process Builder
     * @param context
     * @return
     */
    public static ProcessBuilder create(Properties context) {
        return new ProcessBuilder(context);
    }


    /**
     * Define process based on java class
     * @param processClass
     * @return
     */
    public ProcessBuilder process(final Class<?> processClass) {
        this.process = MProcess.getUsingJavaClass(processClass);
        if (this.process == null)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        this.processId = process.getAD_Process_ID();
        return this;
    }

    /**
     * Define process based on process search key
     * @param value
     * @return
     */
    public ProcessBuilder process(final String value) {
        if (value == null || value.length() == 0)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        this.process = MProcess.get(context, MProcess.getProcess_ID(value, null));
        if (this.process == null)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        this.processId = process.getAD_Process_ID();
        return this;
    }

    /**
     * Define process based on process id
     * @param processId
     * @return
     */
    public ProcessBuilder process(final Integer processId) {
        if (processId == null || processId == 0)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        this.process = MProcess.get(context , processId);
        if (this.process == null)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        this.processId = process.getAD_Process_ID();
        return this;
    }

    /**
     * Define instance for process
     */
    private void generateProcessInstance()
    {
        if (processId == 0)
            throw new AdempiereException("@AD_Process_ID@ @NotFound@");

        instance = new MPInstance(context , processId , recordId);
        instance.saveEx();
    }

    /**
     * Generate Process Info for this process
     */
    private void generateProcessInfo(String trxName)
    {
        if(instance == null)
            generateProcessInstance();

        //	FR [ 244 ]
        boolean isSelection = selectedRecordsIds.size() > 0;
        processInfo = new ProcessInfo(title, processId, tableId , recordId, isManagedTransaction);
        processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
        processInfo.setClassName(MProcess.get(context , processId).getClassname());
        processInfo.setTransactionName(trxName);
        processInfo.setIsSelection(isSelection);
        processInfo.setPrintPreview(isPrintPreview());
        processInfo.setAliasForTableSelection(aliasTableSelection);
        if(clientId != null) {
        	processInfo.setAD_Client_ID(clientId);
        }
        if(userId != null) {
        	processInfo.setAD_User_ID(userId);
        }
        if(!Util.isEmpty(getReportExportFormat())) {
        	processInfo.setReportType(getReportExportFormat());
        } else {
        	processInfo.setReportType(instance.getReportType());
        }
        processInfo.setIsBatch(isBatch());
        if (isExecuteUsingSystemRole) {
            processInfo.setAD_Client_ID(0);
            processInfo.setAD_User_ID(100);
        } else {
            processInfo.setAD_Client_ID(instance.getAD_Client_ID());
            processInfo.setAD_User_ID(instance.getAD_User_ID());
        }

        ProcessInfoUtil.setParameterFromDB(processInfo);

        //	FR [ 352 ]
        if (isSelection) {
            processInfo.setSelectionKeys(selectedRecordsIds);
            processInfo.setTableSelectionId(tableSelectionId);
            processInfo.setSelectionValues(selection);
        }
    }

    /**
     * Execute process based on client or server
     * @param trxName
     */
    private void run (String trxName)
    {
        Runnable processCtl;
        if (windowNo == 0)
            processCtl = processCtl("org.compiere.process.ServerProcessCtl", parent, windowNo, processInfo, Trx.get(trxName, false));
        else
            processCtl = processCtl("org.compiere.apps.ProcessCtl", parent, windowNo, processInfo, Trx.get(trxName, false));

        processCtl.run();
    }

    /**
     * Create instancel for process control
     * @param className
     * @param parent
     * @param windowNo
     * @param processInfo
     * @param trx
     * @return
     * @throws RuntimeException
     */
    private Runnable processCtl(String className , ASyncProcess parent, int windowNo, ProcessInfo processInfo, Trx trx) throws RuntimeException {
        Class<?> clazz;
        Runnable result = null;
        try {
            clazz = Class.forName(className);
            Constructor<?> constructor = null;
            if (windowNo == 0) {
                constructor = clazz.getDeclaredConstructor(ASyncProcess.class, ProcessInfo.class, Trx.class);
                result = (Runnable) constructor.newInstance(parent , processInfo , trx);
            }
            else {
                constructor = clazz.getDeclaredConstructor(ASyncProcess.class, Integer.class, ProcessInfo.class, Trx.class);
                result = (Runnable) constructor.newInstance(parent , windowNo , processInfo , trx);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ProcessInfo executeUsingSystemRole()
    {
        isExecuteUsingSystemRole = true;
        return execute();
    }

    /**
     * Execute ths process with new transaction
     * @return
     */
    public ProcessInfo execute() throws AdempiereException {
        try {
            Trx.run(trxName -> {
                generateProcessInfo(trxName);
                thisBuilder.run(trxName);
                if (processInfo.isError())
                    throw new AdempiereException("@ProcessRunError@ @Error@ " + processInfo.getSummary());
            });
        } catch (AdempiereException e) {
            if (processInfo.isError())
                throw new AdempiereException(e.getMessage());
            else {
                processInfo.setError(true);
                throw new AdempiereException("@ProcessRunError@ @Error@ " + e.getMessage());
            }
        }
        return processInfo;
    }
    
    /**
     * Get Process Information for result
     * @return
     */
    public ProcessInfo getProcessInfo() {
    	return processInfo;
    }

    /**
     * Execute the process based on transaction exists
     * @param trxName
     * @return
     */
    public ProcessInfo execute(String trxName) throws AdempiereException {
        try {

            Trx.run(trxName, new TrxRunnable() {
                public void run(String trxName) {
                    generateProcessInfo(trxName);
                    thisBuilder.run(trxName);
                    if (processInfo.isError())
                        throw new AdempiereException("@ProcessRunError@ @Error@ "  + processInfo.getSummary());
                }
            });

        } catch (AdempiereException e) {
            if (processInfo.isError())
                throw new AdempiereException(e.getMessage());
            else {
                processInfo.setError(true);
                throw new AdempiereException("@ProcessRunError@ @Error@ " + e.getMessage());
            }
        }
        return processInfo;
    }

    /**
     * Define table and Record id for this process
     * @param tableId
     * @param recordId
     * @return
     */
    public ProcessBuilder withRecordId(Integer tableId , Integer recordId) {
        if (instance == null)
            generateProcessInstance();
        this.tableId =  tableId;
        this.recordId = recordId;
        instance.setRecord_ID(recordId);
        instance.saveEx();
        return this;
    }

    /**
     * Define window no when process is called from Client UI
     * @param windowNo
     * @return
     */
    public ProcessBuilder withWindowNo(Integer windowNo)
    {
        this.windowNo = windowNo;
        return this;
    }
    
    /**
     * Define specific client id
     * @param clientId
     * @return
     */
    public ProcessBuilder withClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }
    
    /**
     * Define user for process
     * @param userId
     * @return
     */
    public ProcessBuilder withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Define mutiples select record ids to be processed
     * @param selectedRecordsIds
     * @return
     */
    public ProcessBuilder withSelectedRecordsIds(int tableSelectionId, List<Integer> selectedRecordsIds) {
        return withSelectedRecordsIds(tableSelectionId, null, selectedRecordsIds);
    }
    
    /**
     * Define mutiples select record ids to be processed
     * @param tableSelectionId
     * @param aliasTableSelection
     * @param selectedRecordsIds
     * @return
     */
    public ProcessBuilder withSelectedRecordsIds(int tableSelectionId, String aliasTableSelection, List<Integer> selectedRecordsIds) {
        this.selectedRecordsIds = selectedRecordsIds;
        this.tableSelectionId = tableSelectionId;
        this.aliasTableSelection = aliasTableSelection;
        return this;
    }

    /**
     * Define select record ids and values
     * @param selectedRecordsIds
     * @param selection
     * @return
     */
    public ProcessBuilder withSelectedRecordsIds(int tableSelectionId , List<Integer> selectedRecordsIds, LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection)
    {
        this.selectedRecordsIds = selectedRecordsIds;
        this.selection = selection;
        this.tableSelectionId = tableSelectionId;
        return this;
    }


   public ProcessBuilder withoutTransactionClose()
   {
       this.isManagedTransaction = false;
       return this;
   }

   /**
    * Define parameter with automatic sequence
    * @param name
    * @param value
    * @return
    */
   public ProcessBuilder withParameter(String name, Object value) {
	   return withParameter(name, value, (Object)null);
   }
   
   /**
    * Define parameter with automatic sequence and value to
    * @param name
    * @param value
    * @return
    */
   public ProcessBuilder withParameter(String name, Object value, Object valueTo) {
	   return withParameter(name, value, valueTo, seqNo + 10);
   }
   
   /**
    * Define parameter without to parameter
    * @param parameterName
    * @param value
    * @param sequence
    * @return
    */
   public ProcessBuilder withParameter(String parameterName, Object value, Integer sequence) {
	   return withParameter(parameterName, value, null, sequence);
   }

   /**
    * Define parameter and sequence
    * @param parameterName
    * @param value
    * @param sequence
    * @return
    */
    public ProcessBuilder withParameter(String parameterName, Object value, Object valueTo, Integer sequence) {
        if (Util.isEmpty(parameterName))
            return this;

        if (instance == null)
            generateProcessInstance();

        seqNo = sequence;
        instance.createParameter(sequence, parameterName, value);
        if(valueTo != null) {
        	instance.createParameter(sequence, parameterName + "_To", valueTo);
        }
        return this;
    }

    /**
     * Define execution title process
     * @param title
     * @return
     */
    public ProcessBuilder withTitle(String title)
    {
        this.title = title;
        return this;
    }

    /**
     * Define parent process execution
     * @param parent
     * @return
     */
    public ProcessBuilder withParentProcess(ASyncProcess parent) {
        this.parent = parent;
        return this;
    }

    public ProcessBuilder withoutPrintPreview()
    {
        this.isPrintPreview = false;
        return this;
    }

    public ProcessBuilder withPrintPreview()
    {
        this.isPrintPreview = true;
        return this;
    }

    public boolean isPrintPreview()
    {
        return this.isPrintPreview;
    }


    public ProcessBuilder withoutBatchMode()
    {
        this.isBatch = false;
        return  this;
    }

    public ProcessBuilder withBatchMode()
    {
        this.isBatch = true;
        return  this;
    }

    public boolean isBatch()
    {
        return this.isBatch;
    }
    
    /**
     * Set report Export format
     * @param reportExportFormat
     * @return
     */
    public ProcessBuilder withReportExportFormat(String reportExportFormat) {
    	this.reportExportFormat = reportExportFormat;
    	return this;
    }
    
    /**
     * Get report export format
     * @return
     */
    public String getReportExportFormat() {
    	return this.reportExportFormat;
    }

    /**
     * Main class for test
     * @param args
     */
    public static void main(String[] args) {
        ProcessBuilder
                .create(Env.getCtx())
                .process(org.eevolution.process.BankTransfer.class)
                .withParentProcess(null)
                .withParameter("", "")
                .withParameter("", "")
                .withRecordId(MInvoice.Table_ID ,10001)
                .withoutTransactionClose()
                .execute();
    }
}

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

package org.eevolution.service.dsl;


import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;


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

    static private ProcessBuilder processBuilder;
    static  private Properties context;
    private ProcessInfo processInfo;
    private String title;
    private Integer processId;
    private Integer recordId;
    private Integer tableId;
    private Integer windowNo;
    private Integer seqNo;
    private MPInstance instance;
    private MProcess process;
    private ASyncProcess parent;
    private List<Integer> selectedRecordsIds;
    private Boolean isManagedTransaction = true;
    private Boolean isExecuteUsingSystemRole =  false;
    /**	Multi-Selection Parameters	*/
    private LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection = null;

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
    }

    /**
     * Create a new Process Builder
     * @param context
     * @return
     */
    public static ProcessBuilder create(Properties context) {
        processBuilder = new ProcessBuilder(context);
        return processBuilder;
    }


    /**
     * Define process based on java class
     * @param processClass
     * @return
     */
    public ProcessBuilder process(final Class<?> processClass) {
        this.process= MProcess.getUsingJavaClass(processClass);
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
        if (isExecuteUsingSystemRole) {
            processInfo.setAD_Client_ID(0);
            processInfo.setAD_User_ID(100);
        }
        ProcessInfoUtil.setParameterFromDB(processInfo);

        //	FR [ 352 ]
        if (isSelection) {
            processInfo.setSelectionKeys(selectedRecordsIds);
            if (selection != null && selection.size() > 0) {
                processInfo.setSelectionValues(selection);
                //TODO : Need Remove duplicate functionality ProcessCtl , WProcessCtl , ServerProcessCtl
                //TODO : The WProcessCtl and ServerProcessCtl not save selection and smart browser selection
                if (windowNo == 0)
                        DB.createT_Selection_Browse(processInfo.getAD_PInstance_ID(), processInfo.getSelectionValues(), processInfo.getTransactionName());
            }
            //TODO : Need Remove duplicate functionality ProcessCtl , WProcessCtl , ServerProcessCtl
            //TODO : The WProcessCtl and ServerProcessCtl not save selection and smart browser selection
            if (windowNo == 0) // force the save selction the issue that not implement save selection
                DB.createT_Selection(processInfo.getAD_PInstance_ID(), processInfo.getSelectionKeys(), processInfo.getTransactionName());
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
                processBuilder.run(trxName);
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
     * Execute the process based on transaction exists
     * @param trxName
     * @return
     */
    public ProcessInfo execute(String trxName) throws AdempiereException {
        try {

            Trx.run(trxName, new TrxRunnable() {
                public void run(String trxName) {
                    generateProcessInfo(trxName);
                    processBuilder.run(trxName);
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
     * Define mutiples select record ids to be processed
     * @param selectedRecordsIds
     * @return
     */
    public ProcessBuilder withSelectedRecordsIds(List<Integer> selectedRecordsIds)
    {
        this.selectedRecordsIds = selectedRecordsIds;
        return this;
    }

    /**
     * Define select record ids and values
     * @param selectedRecordsIds
     * @param selection
     * @return
     */
    public ProcessBuilder withSelectedRecordsIds(List<Integer> selectedRecordsIds, LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection)
    {
        this.selectedRecordsIds = selectedRecordsIds;
        this.selection = selection;
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
        if (instance == null)
            generateProcessInstance();
        return withParameter(name, value , seqNo + 10);
    }

    /**
     * Define parameter and sequence
     * @param name
     * @param value
     * @param sequence
     * @return
     */
    public ProcessBuilder withParameter(String name, Object value , Integer sequence) {
        if (name == null || name.length() == 0)
            return this;

        if (instance == null)
            generateProcessInstance();

        seqNo = sequence;

        MPInstancePara parameter = new MPInstancePara(instance, sequence);
        if (value instanceof String)
            parameter.setParameter(name, (String) value);
        if (value instanceof Integer)
            parameter.setParameter(name, (Integer) value);
        if (value instanceof Timestamp)
            parameter.setParameter(name, (Timestamp) value);
        if (value instanceof Boolean)
            parameter.setParameter(name, (java.lang.Boolean) value);
        if (value instanceof BigDecimal)
            parameter.setParameter(name, (BigDecimal) value);
        parameter.saveEx();
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

/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic.administration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MDunning;
import org.compiere.model.MDunningLevel;
import org.compiere.model.MDunningRun;
import org.compiere.model.MPInstance;
import org.compiere.print.MPrintFormat;
import org.compiere.process.DunningPrint;
import org.compiere.process.DunningRunCreate;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.DunningBean;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.exceptions.OperationException;
import org.posterita.util.PoManager;


public class DunningManager
{
    private static final int DUNNING_CREATE_PROCESS_ID=289;
    private static final int DUNNING_PRINT_PROCESS_ID=312;
    
    
    public static MDunning createDunningAndLevel(Properties ctx,String dunningName,String desc,String dunningLevelName,String printText,String printNote,int daysAfterDue) throws OperationException
    {
        
        MDunning dunning  = new MDunning(ctx,0,null); 
        dunning.setIsDefault(true);
        dunning.setSendDunningLetter(true);
        dunning.setName(dunningName);
        dunning.setDescription(desc);
        
        PoManager.save(dunning);
        
        MDunningLevel dunningLevel = new MDunningLevel(ctx,0,null);
        dunningLevel.setC_Dunning_ID(dunning.get_ID());
        dunningLevel.setName(dunningLevelName);
        dunningLevel.setDaysAfterDue(new BigDecimal(daysAfterDue));
        dunningLevel.setDaysBetweenDunning(0);
        dunningLevel.setPrintName(printText);
        dunningLevel.setNote(printNote);
        
        int [] allIds = MPrintFormat.getAllIDs(MPrintFormat.Table_Name,"AD_CLIENT_ID=0 and name='Dunning_Header ** TEMPLATE **'",null);
        if(allIds.length==0)
            throw new OperationException("no dunning print format for system");
        if(allIds.length > 1)
            throw new OperationException("more than one dunning print format for system");
        dunningLevel.setDunning_PrintFormat_ID(allIds[0]);
        
        PoManager.save(dunningLevel);
        return dunning;
    }
    
    
    
    
    public static void printDunningLetters(Properties ctx, DunningBean bean) throws OperationException
    {
        if(bean.getBpartnerIds()!=null && bean.getBpartnerIds().length>0)
        {
            for(int i=0;i<bean.getBpartnerIds().length;i++)
            {
                bean.setBpartnerId(bean.getBpartnerIds()[i]);
                printLetters(ctx,bean);
            }
        }
        else
        {
            printLetters(ctx,bean);
        }
        
    }
    
    
    private static void printLetters(Properties ctx, DunningBean bean) throws OperationException
    {
        int dunningRunId=DunningRun(ctx,bean);
        if(dunningRunId!=0)
        {
            ProcessInfoParameter param[]=
            {
                    new ProcessInfoParameter("C_DunningRun_ID",new BigDecimal(dunningRunId),null,null,null),
            };
            
            MPInstance instance = new MPInstance(ctx,DUNNING_PRINT_PROCESS_ID,dunningRunId);
            instance.save();
            ProcessInfo poInfo = new ProcessInfo("Print Dunning Letters",DUNNING_PRINT_PROCESS_ID);
            poInfo.setParameter(param);
            poInfo.setRecord_ID(dunningRunId);
            poInfo.setAD_Process_ID(DUNNING_PRINT_PROCESS_ID);
            poInfo.setAD_PInstance_ID(instance.get_ID());
            DunningPrint dunningPrint = new DunningPrint();
            boolean success = dunningPrint.startProcess(ctx,poInfo,null);
            
            if(success==false)
                throw new OperationException("Problem encountered while printing");
            
        }
        
        else
            throw new OperationException("No dunning run created");
        
    }
    
    
    private static int DunningRun(Properties ctx, DunningBean bean) throws OperationException
    {
        
        
        int dunningRunIds [] = MDunningRun.getAllIDs(MDunningRun.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx),null);
        MDunningRun dunningRun=null;
        if(dunningRunIds.length==0)
        {
            dunningRun = new MDunningRun(ctx,0,null);
            if(bean.getDunningId()!=null)
            {
                int  dunningLevelIds []=MDunningLevel.getAllIDs(MDunningLevel.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_DUNNING_ID="+bean.getDunningId(),null);
                if(dunningLevelIds.length==0)
                    throw new OperationException("No dunning Level is defined, Please create one");
                dunningRun.setC_DunningLevel_ID(dunningLevelIds[0]);
            }
            else
            {
                int dunningLevelIds [] = MDunningLevel.getAllIDs(MDunningLevel.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx),null);
                if(dunningLevelIds.length==0)
                    throw new OperationException("No dunning Level is defined, Please create one");
                dunningRun.setC_DunningLevel_ID(dunningLevelIds[0]);
            }
            
        }
        else
        {
            dunningRun = new MDunningRun(ctx,dunningRunIds [0],null);  
        }
        dunningRun.setDunningDate(new Timestamp(System.currentTimeMillis()));

        PoManager.save(dunningRun);
        
        MPInstance instance = new MPInstance(ctx,DUNNING_CREATE_PROCESS_ID,dunningRun.get_ID());
        instance.save();
        ProcessInfo poInfo = new ProcessInfo("Create Dunning Run",DUNNING_CREATE_PROCESS_ID);
        
        if(bean.getBpartnerId()!=null) 
        {
            ProcessInfoParameter param1[]=
            {
                    new ProcessInfoParameter("C_BPartner_ID",new BigDecimal(bean.getBpartnerId()),null,null,null),    
                    new ProcessInfoParameter("SalesRep_ID",new BigDecimal(Env.getAD_User_ID(ctx)),null,null,null),
                    new ProcessInfoParameter("C_Currency_ID",new BigDecimal(POSTerminalManager.getDefaultSalesCurrency(ctx).get_ID()),null,null,null),   
                    new ProcessInfoParameter("C_Currency_ID",new BigDecimal(POSTerminalManager.getDefaultSalesCurrency(ctx).get_ID()),null,null,null),
            };
            poInfo.setParameter(param1); 
        }
        else if(bean.getBpartnerId()==null) 
        { 
            ProcessInfoParameter param2[]=
            {
                    new ProcessInfoParameter("SalesRep_ID",new BigDecimal(Env.getAD_User_ID(ctx)),null,null,null),
                    new ProcessInfoParameter("C_Currency_ID",new BigDecimal(POSTerminalManager.getDefaultSalesCurrency(ctx).get_ID()),null,null,null),   
                    new ProcessInfoParameter("C_Currency_ID",new BigDecimal(POSTerminalManager.getDefaultSalesCurrency(ctx).get_ID()),null,null,null),
            };
            poInfo.setParameter(param2);
        }
        poInfo.setRecord_ID(dunningRun.get_ID());
        poInfo.setAD_Process_ID(DUNNING_CREATE_PROCESS_ID);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        DunningRunCreate dunningCreate = new DunningRunCreate();
        dunningCreate.startProcess(ctx,poInfo,null);
        return dunningRun.get_ID();
        
    }
    
    public static void associateAllBpartnerWithDunning(Properties ctx) throws OperationException
    {
        String sql = "UPDATE c_Bpartner SET C_DUNNING_ID="+getOrCreateDunning(ctx)+
                    " WHERE AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        
        int no = DB.executeUpdate(sql, null);
        
        if (no == -1)
        {
        	throw new OperationException("Could not update BPartner dunning");
        }
        
    }
    
    private static int getOrCreateDunning(Properties ctx) throws OperationException
    {
       
        String dunningName=null;
        String description=null;
        String dunningLevelName=null;
        String printText =null;
        String printNote=null;
        int daysAfterDue=0;
        MDunning dunning=null;
        int dunningId=0;
        
            dunningName="Statement Of Account";
            description="Include due an non-due invoices";
            dunningLevelName="Statement Of Account";
            printText = "Statement Of Account";
            printNote="Please review your statement and submit due payments."; 
            daysAfterDue=-9999;
           
            int dunningIds []=MDunning.getAllIDs(MDunning.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and isActive='Y' and name='"+dunningName+"'",null);
            
            if (dunningIds.length==0)
            {
                dunning= createDunningAndLevel(ctx,dunningName,description,dunningLevelName,printText,printNote,daysAfterDue);
                dunningId = dunning.get_ID();
            }
            else
            {
                dunningId=dunningIds[0];
            }
            return dunningId;
    }
    
}

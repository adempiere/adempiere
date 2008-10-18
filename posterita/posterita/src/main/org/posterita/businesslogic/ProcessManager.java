/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
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
 * Created on 27-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
import org.compiere.report.ReportStarter;
import org.compiere.util.Trx;
import org.posterita.exceptions.OperationException;
import org.posterita.util.PoManager;


public class ProcessManager
{
	
	public static MProcess createProcess(Properties ctx,  String className) throws Exception
	{
		Trx trx = Trx.get(Trx.createTrxName(),true);
		trx.start();
		
		MProcess mProcess = new MProcess(ctx,0, trx.getTrxName());
		
		try{
				mProcess.setClassname(className);
				mProcess.setDescription(className);
				mProcess.setName(className);
				mProcess.setValue(className);
				
				PoManager.save(mProcess);
				
				trx.commit();
				trx.close();
			
			}
			catch(OperationException e)    
			{
				trx.rollback();
				trx.close();
				
			}
			
		return mProcess;
		
	}
	
	public static int getProcessId(Class cl) throws OperationException
	{
		if(cl == null)
			throw new OperationException("Class cannot be null");
		String whereClause = "ClassName='" + cl.getName() + "'";
		
		int processIds[] = MProcess.getAllIDs(MProcess.Table_Name, whereClause, null);
		
		if(processIds.length == 0)
			throw new OperationException("No process found with class name: " + cl.getName());
		else if(processIds.length > 1)
			throw new OperationException(processIds.length + " Processes found with class name: " + cl.getName());
		else
			return processIds[0];
	}
	
	public static int getProcessIdByValue(String processValue) throws OperationException
	{
		if(processValue == null)
			throw new OperationException("Process Value cannot be null");
		
		String whereClause = "value='" + processValue + "'";
		
		int processIds[] = MProcess.getAllIDs(MProcess.Table_Name, whereClause, null);
		
		if(processIds.length == 0)
			throw new OperationException("No process found with value: " + processValue);
		
		else if(processIds.length > 1)
			throw new OperationException(processIds.length + " Processes found with value: " + processValue);
		else
			return processIds[0];
	}	
	
	public static void startProcess(Properties ctx, String className, ProcessInfo pi, Trx trx) 
	throws InstantiationException,	IllegalAccessException, ClassNotFoundException  
	{
		if (className == null)
		{
			return;
		}		
		Class cl = Class.forName(className);
		
		if (cl == ReportStarter.class)
		{
			ReportStarter reportStarter = (ReportStarter)cl.newInstance();
			reportStarter.startProcess(ctx, pi, trx);
			return;
		}
		SvrProcess server = (SvrProcess)cl.newInstance();
		server.startProcess(ctx, pi, trx);
	}
	
	
}

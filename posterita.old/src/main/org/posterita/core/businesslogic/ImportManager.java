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
 *  
 **/
/**
	@author ashley
 */

package org.posterita.core.businesslogic;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.impexp.ImpFormat;
import org.compiere.process.ImportAccount;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Trx;

import org.posterita.businesslogic.ProcessManager;
import org.posterita.core.FileManager;
import org.posterita.exceptions.OperationException;

public class ImportManager
{
	public static int importFile(Properties ctx, File impFile, String importFormat, String trxName) throws OperationException
	{
		if(impFile == null)
			throw new OperationException("File to import cannot be null");
		if(!impFile.exists() || impFile.isDirectory())
			throw new OperationException("File does not exist or it is a directory, file: " + impFile);
		if(importFormat == null || importFormat.trim().length() == 0)
			throw new OperationException("Import format cannot be null");
		
		ImpFormat impFormat = ImpFormat.load(importFormat);
		
		if(impFormat == null)
			throw new OperationException("Could not load import format: " + importFormat);
		
		ArrayList<String> fileLinesList = FileManager.readLines(impFile);
		
		Iterator<String> fileLinesIter = fileLinesList.iterator();
		
		int importedLines = 0;
		while(fileLinesIter.hasNext())
		{
			String line = fileLinesIter.next();
			if(impFormat.updateDB(ctx, line, trxName))
				importedLines++;
		}
		
		return importedLines;
	}
	
	public static void importAccounting(Properties ctx, int clientId, int elementId, boolean updateDefaultAccounts, boolean createNewCombination, boolean deleteOldImported, String trxName) throws OperationException
	{
		ImportAccount impAccount = new ImportAccount();
		int accountImportProcessId = ProcessManager.getProcessId(ImportAccount.class);
		
		String updDefAccts = (updateDefaultAccounts) ? "Y" : "N";
		String crNewCombinations = (createNewCombination) ? "Y" : "N";
		String delOldImp = (deleteOldImported) ? "Y" : "N";
		
		ProcessInfo processInfo = new ProcessInfo("Import accounts", accountImportProcessId);
		
		ProcessInfoParameter clientParam = new ProcessInfoParameter("AD_Client_ID", new BigDecimal(clientId), new BigDecimal(clientId), "Client", "Client");
		ProcessInfoParameter elementParam = new ProcessInfoParameter("C_Element_ID", new BigDecimal(elementId), new BigDecimal(elementId), "Element", "Element");
		ProcessInfoParameter updDefAcctsParam = new ProcessInfoParameter("UpdateDefaultAccounts", updDefAccts, updDefAccts, "Update Default Accounts", "Update Default Accounts");
		ProcessInfoParameter createNewCombinationParam = new ProcessInfoParameter("CreateNewCombination", crNewCombinations, crNewCombinations, "Create New Combination", "Create New Combination");
		ProcessInfoParameter deleteOldParam = new ProcessInfoParameter("DeleteOldImported", delOldImp, delOldImp, "Delete Old Imported", "Delete Old Imported");
		
		ProcessInfoParameter parameters[] = new ProcessInfoParameter[5];
		parameters[0] = clientParam;
		parameters[1] = elementParam;
		parameters[2] = updDefAcctsParam;
		parameters[3] = createNewCombinationParam;
		parameters[4] = deleteOldParam;
		
		processInfo.setParameter(parameters);
		
		Trx trx = null;
		
		if(trxName != null)
			trx = Trx.get(trxName, false);
		
		if(!impAccount.startProcess(ctx, processInfo, trx))
			throw new OperationException("Could not run import accounting process");
	}
}

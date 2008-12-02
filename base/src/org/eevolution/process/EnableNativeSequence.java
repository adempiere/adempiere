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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;



import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MSequence;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_Table;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.model.Query;

/**
 *	Enable Native Sequence
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 */
public class EnableNativeSequence extends SvrProcess
{

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
	}	//	prepare
	
	protected String doIt()              
	{
		boolean SYSTEM_NATIVE_SEQUENCE = MSysConfig.getBooleanValue("SYSTEM_NATIVE_SEQUENCE",true,Env.getAD_Client_ID(Env.getCtx()));
		
		if(SYSTEM_NATIVE_SEQUENCE)
			throw new AdempiereException("Native Sequence is Actived");
		else
		{
			DB.executeUpdateEx("UPDATE AD_SysConfig SET Value='Y' WHERE Name='SYSTEM_NATIVE_SEQUENCE'",null);
			MSysConfig.resetCache();
		}
		
		List<MTable> tables = new Query(getCtx(),X_AD_Table.Table_Name,"", get_TrxName()).list();
		for(MTable table : tables)
		{
			if(!table.isView())
			{	
				if(!MSequence.createTableSequence(getCtx(), table.getTableName(), get_TrxName()))
				{	
					DB.executeUpdateEx("UPDATE AD_SysConfig SET Value='N' WHERE Name='SYSTEM_NATIVE_SEQUENCE'",null);
					MSysConfig.resetCache();
					new AdempiereException("Can not create Native Sequence");
				}	
				else
				{	
					this.addLog("Create Native Sequence for : "+table.getTableName());		
				}
			}
		}
		
		
		return "@OK@";
	} 
	
	/**
	 * Main test
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) 
	{
		
		try {
			Adempiere.startupEnvironment(true);
			CLogMgt.setLevel(Level.ALL);
			EnableNativeSequence seqs = new EnableNativeSequence();
			seqs.doIt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


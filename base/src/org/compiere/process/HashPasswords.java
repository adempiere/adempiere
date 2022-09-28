/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Column;
import org.compiere.Adempiere;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Reset Password
 *	
 *  @author Jorg Janke
 *  @version $Id: UserPassword.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class HashPasswords extends SvrProcess
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		String where = " Password IS NOT NULL AND Salt IS NULL ";
		
		int count = 0;
		boolean isEncrypted = MColumn.isEncrypted(417);

		List<MUser> users = MTable.get(getCtx(), MUser.Table_ID).createQuery( where, get_TrxName())
		.list();
		for ( MUser user : users )
		{
			if ( user.getAD_User_ID() == 0 )
			{
                String password = DB.getSQLValueString(get_TrxName(), "SELECT Password FROM AD_User WHERE AD_User_ID=?", 0);
                if (isEncrypted)
                	password = SecureEngine.decrypt(password);
                
				user.setPassword(password);
                String sql = "UPDATE AD_User SET Updated=SysDate, UpdatedBy=" + getAD_User_ID();
                if (!Util.isEmpty(password)) {
                    sql += ", Password=" + DB.TO_STRING( isEncrypted ? SecureEngine.encrypt(user.getPassword()) : user.getPassword());
                    sql += ", Salt=" +  DB.TO_STRING(user.getSalt());
                }
                sql += " WHERE AD_User_ID=0";
                DB.executeUpdateEx(sql, get_TrxName());
				count++;
			}
			else
			{
				user.setPassword(user.getPassword());
				count++;
				user.saveEx();
			}
		}
		
		return "@Updated@ " + count;
	}	//	doIt


    /**
     * 	Test
     *	@param args ignored
     */
    public static void main (String[] args) {

        Adempiere.startupEnvironment(false);
        CLogMgt.setLevel(Level.CONFIG);


        ColumnEncryption  columnEncryption = new ColumnEncryption();

        int processId= 328; // AD_ColumnEncryption
        int columnId = 417; // AD_User - Password
        Env.setContext(Env.getCtx(), I_AD_Column.COLUMNNAME_AD_Column_ID , columnId);

        MPInstance instance;
        MPInstancePara instanceParameters;

        instance = new MPInstance(Env.getCtx(), processId, columnId);
        instance.saveEx();

        instanceParameters = new MPInstancePara(instance, 10);
        instanceParameters.setParameter(I_AD_Column.COLUMNNAME_IsEncrypted, true );
        instanceParameters.saveEx();

        instanceParameters = new MPInstancePara(instance, 20);
        instanceParameters.setParameter("ChangeSetting", true );
        instanceParameters.saveEx();


        ProcessInfo pi = new ProcessInfo("AD_ColumnEncryption", processId);
        pi.setRecord_ID(instance.getRecord_ID());
        pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
        pi.setAD_Client_ID(0);
        pi.setAD_User_ID(100);

        columnEncryption.startProcess(Env.getCtx(), pi , null);

        /*List<MUser> users = new Query(Env.getCtx(), I_AD_User.Table_Name , "Password IS NOT NULL", null).list();
        for (MUser user : users)
        {
            user.setPassword(user.getPassword());
            user.saveEx();
        }*/

        processId=53259; // Convert password to hashed

        pi = new ProcessInfo("AD_User_HashPassword", processId);
        pi.setAD_Client_ID(0);
        pi.setAD_User_ID(100);

        HashPasswords process = new HashPasswords();
        process.startProcess(Env.getCtx(), pi , null);

    }

}	//	UserPassword


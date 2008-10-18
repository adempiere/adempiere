/**
 *
 * Copyright (c) 2007 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. TAMAK ICT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * 21 Dec 2007 17:34:13 by shameem
 *
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MInvoice;
import org.compiere.model.MPInstance;
import org.compiere.process.FactAcctReset;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class PostingManager 
{

	static private CLogger cLog = CLogger.getCLogger(FactAcctReset.class);
	
    public static void resubmitPostingForInvoice(Properties ctx) 
    {
		// TODO Auto-generated method stub
    	
    	BigDecimal clientId = new BigDecimal(Env.getAD_Client_ID(ctx));
    	
    	BigDecimal invoiceIdTableId = new BigDecimal(MInvoice.Table_ID);
    	
        ProcessInfoParameter param[] =
        {
                new ProcessInfoParameter("AD_Client_ID", clientId, null, null, null),
                new ProcessInfoParameter("AD_Table_ID", invoiceIdTableId, null, null, null),
                new ProcessInfoParameter("DeletePosting", "N", null, null, null),
        };

        
        MPInstance instance = new MPInstance(ctx, 175, Env.getAD_Client_ID(ctx));
       
        instance.save();
       
        ProcessInfo poInfo = new ProcessInfo("Resubmit Posting", 175);
        poInfo.setParameter(param);
        poInfo.setAD_Process_ID(175);
        poInfo.setAD_PInstance_ID(instance.get_ID());


        FactAcctReset factAcct = new FactAcctReset();
        boolean success = factAcct.startProcess(ctx,poInfo, null);
       
        
        
        if(success==false)
        {
            cLog.log(Level.FINE,"Error occurred while running process for resubmit posting for Invoice");
        }  
	}	
	
}

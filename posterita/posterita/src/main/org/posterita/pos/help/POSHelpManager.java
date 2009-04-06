/**
 *
 * Copyright (c) 2008 Posterita. All Rights Reserved.
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
 * 11 Feb 2008 10:20:44 by shameem
 *
 */

package org.posterita.pos.help;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.util.EMail;

public class POSHelpManager 
{	
	
	public static boolean sendEmail(Properties ctx, String from, String to, String subject, String body)
	{
		String emailAddress = to;
                		
        MClient client = MClient.get(ctx);
		
		EMail email = client.createEMail(emailAddress, subject, body);
		email.setFrom(from);
		if (email != null)
		{
			if (EMail.SENT_OK.equals (email.send ()))
			{
				return true;
			}
		}		
		
		client.get_Logger().log(Level.SEVERE, "Unable to sent email");
		
		return false;
		
	}
}

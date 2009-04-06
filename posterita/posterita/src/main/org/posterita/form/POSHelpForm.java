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
 * 6 Feb 2008 18:02:07 by shameem
 *
 */

package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.POSHelpBean;
import org.posterita.pos.help.POSHelpAction;
import org.posterita.struts.core.DefaultForm;

public class POSHelpForm extends DefaultForm
{
	private static final long serialVersionUID = 1L;
	
	public POSHelpForm()	
	{
		setBean(new POSHelpBean());
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		if(POSHelpAction.INIT_CONTACT_US.equals(action))
		{
			// no validation required
		}
		else if(POSHelpAction.SEND_EMAIL.equals(action))
		{
			addRequiredFields(new String[] {"fromAddress","subject","textMessage"}); 
		}
		else
		{
			
		}
		
		return super.validate(mapping, request);
	}
}

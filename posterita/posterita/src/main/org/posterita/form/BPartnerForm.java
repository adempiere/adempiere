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
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. POSTERITA SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Aug 12, 2008 11:01:56 PM by praveen
 *
 */

package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.BPartnerBean;
import org.posterita.struts.bPartner.BPartnerAction;
import org.posterita.struts.core.DefaultForm;

public class BPartnerForm extends DefaultForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7530406406407900075L;

	public BPartnerForm()
	{
		setBean(new BPartnerBean());
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		if(BPartnerAction.GET_CREDITOR_HISTORY.equals(this.action))
		{
			// add required fields here
		}
		
		return super.validate(mapping, request);
	}

}

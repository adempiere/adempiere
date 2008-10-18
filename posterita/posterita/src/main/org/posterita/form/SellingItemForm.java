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
 * 16 Jan 2008 13:10:40 by shameem
 *
 */

package org.posterita.form;

import org.posterita.beans.ReportBean;
import org.posterita.struts.core.DefaultForm;

public class SellingItemForm extends DefaultForm
{
	private static final long serialVersionUID = 1L;
	
	public SellingItemForm()	
	{
		setBean(new ReportBean());
	}
}

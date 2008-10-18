/*
*
* Copyright (c) 2006 Tamak ICT Ltd. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* UDI Ltd. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with UDI.
*
* UDI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
* SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
* NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
* A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UDI SHALL NOT
* BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
* MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*
* Created on Jun 23, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.exceptions;

import org.posterita.exceptions.DataException;

public class InvalidRequestUpdateException extends DataException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRequestUpdateException(String msg)
	{
		super(msg);
	}
	
	public InvalidRequestUpdateException(String msg, Exception ex)
	{
		super(msg, ex);
	}
}

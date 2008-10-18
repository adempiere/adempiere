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
 * 31-Aug-2006 17:23:19 by praveen
 *
 */

package org.posterita.exceptions;

public class InvalidInvokeOrderStatusException extends OperationException 
{
	private static final long serialVersionUID = 1L;
	private String status = null;
	
	public InvalidInvokeOrderStatusException(String xception,String status)
	{
		super(xception);
		this.status = status;
	}
	
	public InvalidInvokeOrderStatusException(String xception)
	{
		super(xception);
	}
	
	public InvalidInvokeOrderStatusException(Exception exception)
	{
		super(exception);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

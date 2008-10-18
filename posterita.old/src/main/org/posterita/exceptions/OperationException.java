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
*/

package org.posterita.exceptions;

public class OperationException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationException()
	{
		
	}
	
	public OperationException(String xception)
	{
		super(xception);
	}
	
	public OperationException(Exception exception)
	{
		super(exception);
	}
	
	public OperationException(String msg, Exception exception)
	{
		super(msg,exception);
	}
	
	
}

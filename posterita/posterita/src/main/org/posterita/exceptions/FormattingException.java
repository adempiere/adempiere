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
 * Created on Dec 26, 2006
 */
 
package org.posterita.exceptions;

import org.posterita.formatter.Formatter;



public class FormattingException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable cause;
	private Formatter formatter;
	
	public FormattingException(String message)
	{
		super(message);
	}
	
	public FormattingException(String message, Throwable cause)
	{
		super(message);
		
		this.cause = cause;
	}
	
	public Formatter getFormatter()
	{
		return formatter;
	}
	
	public void setFormatter(Formatter formatter)
	{
		this.formatter = formatter;
	}
	
	public void setCause(Throwable cause)
	{
		this.cause = cause;
	}
	
	public Throwable getCause()
	{
		return cause;
	}
	
	public String toString()
	{
		return super.toString() +(cause == null? "" : "\n Original Cause: \n" + cause.toString()); 
	}
	
	public void printStackTrace()
	{
		super.printStackTrace();
		
		if (cause != null)
		{
			System.out.println("\n Original Cause: \n");
			cause.printStackTrace();
		}
	}

}

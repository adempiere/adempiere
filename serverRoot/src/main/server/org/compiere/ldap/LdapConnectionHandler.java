/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.ldap;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import org.compiere.ldap.*;
import org.compiere.util.*;
import com.sun.jndi.ldap.*;

/**
 * 	LDAP Connection Handler
 *	
 *  @author Jorg Janke
 *  @version $Id: LdapConnectionHandler.java,v 1.1 2006/10/09 00:23:16 jjanke Exp $
 */
public class LdapConnectionHandler extends Thread
{
	/**
	 * 	Ldap Connection Handler
	 *	@param socket server socket
	 */
	public LdapConnectionHandler(Socket socket)
	{
		try
		{
			m_socket = socket;
			m_socket.setTcpNoDelay(true);	//	should not be required
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}	//	no timeout
	}	//	LdapConnectionHandler
	
	/** Socket				*/
	private Socket m_socket = null;
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (LdapConnectionHandler.class);
	
	
	/**
	 * 	Do Work
	 */
	public void run()
	{
		try
		{
			if (m_socket == null || m_socket.isClosed())
				return;
			
			boolean activeSession = true;
			while (activeSession)
			{
				InputStream in = m_socket.getInputStream();
				BufferedOutputStream out = new BufferedOutputStream(m_socket.getOutputStream());
				//	Read
				byte[] buffer = new byte[512];
				int length = in.read(buffer, 0, 512);
				
				LdapMessage msg = new LdapMessage (buffer, length);
				if (msg.getOperation() == LdapMessage.UNBIND_REQUEST)
				{
					activeSession = false;
					out.close();
				}
				else
				{
					LdapResult result = new LdapResult ();
					byte[] bytes = result.bindResponse();
					//
					out.write(bytes);
					out.flush();
				}
			}
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		
		try
		{
			m_socket.close();
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "Socket", e);
		}
		m_socket = null;
	}	//	run

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("LdapConnectionHandler[");
		sb.append (hashCode()).append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	LdapConnectionHandler

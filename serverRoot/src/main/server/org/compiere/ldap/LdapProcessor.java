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

import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.naming.ldap.*;
import org.compiere.*;
import org.compiere.ldap.*;
import org.compiere.model.*;
import org.compiere.server.*;
import org.compiere.util.*;

/**
 * 	LDAP Server
 *	
 *  @author Jorg Janke
 *  @version $Id: LdapProcessor.java,v 1.1 2006/10/09 00:23:16 jjanke Exp $
 */
public class LdapProcessor extends AdempiereServer
{
	/**
	 * 	Ldap Processor (Server)
	 *	@param model Ldap Model
	 */
	public LdapProcessor (LdapProcessorModel model)
	{
		super (model, 300);
		m_model = model;
		init();
	}	//	LdapProcessor

	/**	The Concrete Model			*/
	private LdapProcessorModel	m_model = null;
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	/** Client info					*/
	private MClient 			m_client = null;
	/** Server Socket				*/
	private ServerSocket 		m_serverSocket = null;
	/** Counter						*/
	private int					m_counter = 0;

	
	/**
	 * 	Do Work
	 */
	protected void doWork()
	{
		//	Close Socket
		if (m_serverSocket != null)
		{
			try
			{
				m_serverSocket.close();
			}
			catch (Exception e)
			{
			}
		}
		m_counter = 0;
		//
		m_summary = new StringBuffer(m_model.toString())
			.append(" - ");
		//
		
		try
		{
			m_serverSocket = new ServerSocket(m_model.getLdapPort());
			log.log(Level.INFO, "Opened Port=" + m_model.getLdapPort());
			while (!isInterrupted())
			{
				Socket socket = m_serverSocket.accept();	//	waits for connection
				log.log(Level.FINE, "Connection on Port=" + m_model.getLdapPort());
				LdapConnectionHandler handler = new LdapConnectionHandler (socket);
				handler.start();
				m_counter++;
			}
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "Port=" + m_model.getLdapPort(), e);
			m_summary.append(e.toString());
		}
		
	}	//	doWork

	/**
	 * 	Initialize
	 */
	private void init()
	{
		try
		{
			InitialLdapContext lctx = new InitialLdapContext();
		//	lctx.setRequestControls(critModCtls);
		//	lctx.modifyAttributes(name, mods);
			Control[] respCtls =  lctx.getResponseControls();
		}
		catch (Exception e)
		{
		}
	}	//
	
	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + m_summary.toString() 
			+ "; Counter=" + m_counter;
	}	//	getServerInfo

	/**
	 * 	Test
	 *	@param args
	 */
	public static void main(String[] args)
	{
		Adempiere.startup(true);
		new LdapProcessor(new LdapProcessorModel(new Properties())).doWork();
	}	//	main
	
}	//	LdapProcessor


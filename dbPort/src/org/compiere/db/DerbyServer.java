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
 * You may reach us at: ComPiere, Inc. - http://www.compiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@compiere.org 
 *****************************************************************************/
package org.compiere.db;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.apache.derby.drda.*;
import org.apache.derby.impl.drda.*;
import org.apache.derby.jdbc.*;
import org.compiere.util.*;

/**
 * 	Derby DB Server
 *	
 *  @author Jorg Janke
 *  @version $Id: DerbyServer.java,v 1.5 2006/07/30 00:55:13 jjanke Exp $
 */
public class DerbyServer extends Thread
{
	/**
	 * 	Get/Create Derby Server
	 *	@return server
	 */
	public static synchronized DerbyServer get()
	{
		if (s_server == null)
		{
			InetAddress address = null;
			int port = PORT;
			try
			{
				address = InetAddress.getByAddress(new byte[] {0,0,0,0});	//	all ports
				s_server = new DerbyServer(address, port);
				s_server.start();
			}
			catch (Exception e)
			{
				String msg = null;
				if (address == null)
					msg = "";
				else
					msg = address.toString();
				msg += ":" + port;
				log.log(Level.SEVERE, msg, e);
			}
		}
		return s_server;
	}	//	get
	
	/**
	 * 	Start Server
	 *	@return true if started
	 */
	public static boolean startServer()
	{
		if (get() == null)
			return false;
		return s_server.isServerStarted();
	}	//	startServer

	/**
	 * 	Stop Server
	 *	@return true if stopped / not running
	 */
	public static boolean stopServer()
	{
		if (s_server == null)
			return true;
		try
		{
			s_server.shutdown();
			s_server = null;
			return true;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;
	}	//	stopServer
	
	/** Singleton					*/
	private static DerbyServer 	s_server = null;
	/** Default Port	1527		*/
	public static final int		PORT = NetworkServerControl.DEFAULT_PORTNUMBER;
	
	
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (DerbyServer.class);

	
	/**************************************************************************
	 * 	Derby Server
	 *	@param address server address
	 *	@param port port
	 *	@throws Exception
	 */
	private DerbyServer (InetAddress address, int port) throws Exception
	{
		super("CompiereDerby");
		if (s_impl != null)
			throw new IllegalStateException("Derby Server already started");
		//
		String compiereHome = Ini.getAdempiereHome();
		s_derbyHome = compiereHome + File.separator + "derby";
		File dir = new File(s_derbyHome);
		if (!dir.exists())
			dir.mkdir();
		//
		System.setProperty("derby.system.home", s_derbyHome);
		System.setProperty("derby.drda.traceDirectory", s_derbyHome);
		//
		String logIt = "false";
		if (CLogMgt.isLevelFiner())
			logIt = "true";
		System.setProperty("derby.drda.logConnections", logIt);
		System.setProperty("derby.drda.traceAll", logIt);
		//
		System.setProperty("derby.connection.requireAuthentication", "true");
		System.setProperty("derby.authentication.provider", "BUILTIN");
		addUser("compiere", "compiere");
		//
		s_address = address;
		s_port = port;
		s_impl = new NetworkServerControlImpl (s_address, s_port);
	}	//	DerbyServer

	/** Singleton					*/
	private static NetworkServerControlImpl s_impl = null;
	/** Derby Home					*/
	private static String 			s_derbyHome = "";
	/** Address						*/
	private static InetAddress		s_address = null;
	/** Port						*/
	private static int 				s_port = 0;
	
	/**
	 * 	Run 
	 */
	public void run()
	{
		log.info("starting ...");
		PrintWriter consoleWriter = new PrintWriter(System.out, true);	//	flush
		try
		{
			s_impl.blockingStart(consoleWriter);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getMessage(), e);
			s_impl = null;
		}
		log.info("done");
	}	//	run

	/**
	 * 	Interrupt - shutdown
	 */
	public void interrupt ()
	{
		super.interrupt ();
		shutdown();
	}	//	interrupt
	
	/**
	 * 	Is Server Started
	 *	@return server started
	 */
	public boolean isServerStarted()
	{
		if (s_impl == null)
			return false;
		try 
		{
			s_impl.ping();
		}
		catch (Exception e) 
		{
			return false;
		}
		return true;
	}	//	isServerStarted

	/**
	 * 	Shutdown
	 *	@return true if shut down
	 */
	public boolean shutdown()
	{
		if (s_impl != null)
		{
			try
			{
				log.info("shutting down ...");
				s_impl.shutdown();
				log.info("shutdown");
				return true;
			}
			catch (Exception e)
			{
				return false;
			}
		}
		return true;
	}	//	shutdown
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("DerbyServer[");
		sb.append (s_derbyHome)
			.append(";").append(s_address)
			.append(":").append(s_port)
			.append(";Alive=").append(isAlive())
			.append(";Started=").append(isServerStarted());
		sb.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Get Derby Home
	 *	@return home
	 */
	public String getDerbyHome()
	{
		return s_derbyHome;
	}	//	getDerbyHome
	
	/**
	 *	Get Runtime Info
	 *	@return info
	 */
	public String getRuntimeInfo()
	{
		try
		{
			if (s_impl != null)
				return s_impl.runtimeInfo();
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		return "";
	}	//	getRuntimeInfo

	
	/**
	 *	Get System Info
	 *	@return info
	 */
	public String getSysInfo()
	{
		try
		{
			if (s_impl != null)
				return s_impl.sysinfo();
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		return "";
	}	//	getSysInfo

	/**
	 * Get current Network server properties.
	 * @return Properties object containing Network server properties
	 * @exception Exception	throws an exception if an error occurs
	 */
	public Properties getCurrentProperties() throws Exception
	{
		return s_impl.getCurrentProperties();
	}	//	getCurrentProperties

	/**
	 * 	Add User
	 *	@param dbUid user id
	 *	@param dbPwd user password
	 */
	public void addUser (String dbUid, String dbPwd)
	{
		System.setProperty("derby.user." + dbUid, dbPwd);
		Properties dp = new Properties();
		
	}	//	addUser
	
	/**
	 * 	Create Database
	 *	@param dbName database name
	 *	@param dbUid user id
	 *	@param dbPwd user password
	 *	@return true if exists or created
	 */
	public boolean createDatabase (String dbName, 
		String dbUid, String dbPwd)
	{
		addUser (dbUid, dbPwd);
		//	Embedded Driver
		try
		{
			EmbeddedDriver driver = new EmbeddedDriver();
			DriverManager.registerDriver (driver);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "DriverIssue", e);
			return false;
		}
		//	Connection
		String dbUrl = "jdbc:derby:" + dbName;
		Connection conn = null;
		try
		{
			String urlAttributes = ";create=true"
			//	+ "dataEncryption=true;bootPassword=cLo4u922sc23aPe"
			//	+ ";territory=en_US" 
				+ ";user=" + dbUid
				+ ";password=" + dbPwd;
			conn = DriverManager.getConnection(dbUrl + urlAttributes);
			log.info("Created DB: " + dbName);
		//	new JDBCInfo(conn);
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
		}
		try
		{
			if (conn != null)
				conn.close();
			conn = null;
		}
		catch (Exception e)
		{
		}
		
		//	Connection test
		try
		{
			conn = DriverManager.getConnection(dbUrl, dbUid, dbPwd);
			log.info("Connected to DB: " + dbName);
		//	new JDBCInfo(conn);
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
			conn = null;
			return false;
		}
		//	Connection test
		Statement stmt = null;
		try
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE XX (XX VARCHAR(5))");
			stmt.executeUpdate("INSERT INTO XX (XX) VALUES ('A')");
		}
		catch (Exception e)
		{
			log.info(e.getMessage());
		}
		try
		{
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			conn = null;
		}
		catch (Exception e)
		{
		}
		
		return true;
	}	//	createDatabase
	
	/**************************************************************************
	 * 	Start Derby Server
	 *	@param args
	 */
	public static void main (String[] args)
	{
		CLogMgt.setLevel(Level.FINE);
		DerbyServer server = DerbyServer.get();
		startServer();
		server.createDatabase("compiere", "compiere", "compiere");
	//	log.info(server.getRuntimeInfo());
	//	log.info(server.getSysInfo());
		
		try
		{
		//	log.info(server.getCurrentProperties().toString());
			//
			log.info("Sleeping " + get());
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			
		}
	//	stopServer();
	}	//	main
	
}	//	DerbyServer

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.commons.net.ftp.FTPClient;
import org.compiere.util.CLogger;

/**
 * 	Media Server Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMediaServer.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 *  @author red1 - FR: [ 2214883 ] Remove SQL code and Replace for Query
 */
public class MMediaServer extends X_CM_Media_Server
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1065424104545571149L;


	/**
	 * 	Get Media Server
	 *	@param project
	 *	@return server list
	 */
	public static MMediaServer[] getMediaServer (MWebProject project)
	{
		final String whereClause = I_CM_Media_Server.COLUMNNAME_CM_WebProject_ID+"=?";
		List<MMediaServer> list = new Query(project.getCtx(),MMediaServer.Table_Name,whereClause,project.get_TrxName())
		.setParameters(project.getCM_WebProject_ID())
		.setOrderBy(I_CM_Media_Server.COLUMNNAME_CM_Media_Server_ID)
		.list();
		MMediaServer[] retValue = new MMediaServer[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getMediaServer
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MMediaServer.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param CM_Media_Server_ID id
	 *	@param trxName transaction
	 */
	public MMediaServer (Properties ctx, int CM_Media_Server_ID, String trxName)
	{
		super (ctx, CM_Media_Server_ID, trxName);
	}	//	MMediaServer

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs request 
	 *	@param trxName transaction
	 */
	public MMediaServer (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MMediaServer
	
	/**
	 * 	(Re-)Deploy all media
	 * 	@param media array of media to deploy
	 * 	@return true if deployed
	 */
	public boolean deploy (MMedia[] media)
	{
		// Check whether the host is our example localhost, we will not deploy locally, but this is no error
		if (this.getIP_Address().equals("127.0.0.1") || this.getName().equals("localhost")) {
			log.warning("You have not defined your own server, we will not really deploy to localhost!");
			return true;
		}

		FTPClient ftp = new FTPClient();
		try
		{
			ftp.connect (getIP_Address());
			if (ftp.login (getUserName(), getPassword()))
				log.info("Connected to " + getIP_Address() + " as " + getUserName());
			else
			{
				log.warning("Could NOT connect to " + getIP_Address() + " as " + getUserName());
				return false;
			}
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "Could NOT connect to " + getIP_Address() 
				+ " as " + getUserName(), e);
			return false;
		}

		boolean success = true;
		String cmd = null;
		//	List the files in the directory
		try
		{
			cmd = "cwd";
			ftp.changeWorkingDirectory (getFolder());
			//
			cmd = "list";
			String[] fileNames = ftp.listNames();
			log.log(Level.FINE, "Number of files in " + getFolder() + ": " + fileNames.length);
			
			/*
			FTPFile[] files = ftp.listFiles();
			log.config("Number of files in " + getFolder() + ": " + files.length);
			for (int i = 0; i < files.length; i++)
				log.fine(files[i].getTimestamp() + " \t" + files[i].getName());*/
			//
			cmd = "bin";
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			//
			for (int i = 0; i < media.length; i++)
			{
				if (!media[i].isSummary()) {
					log.log(Level.INFO, " Deploying Media Item:" + media[i].get_ID() + media[i].getExtension());
					MImage thisImage = media[i].getImage();
		
					// Open the file and output streams
					byte[] buffer = thisImage.getData();
					ByteArrayInputStream is = new ByteArrayInputStream(buffer);

					String fileName = media[i].get_ID() + media[i].getExtension();
					cmd = "put " + fileName;
					ftp.storeFile(fileName, is);
					is.close();
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, cmd, e);
			success = false;
		}
		//	Logout from the FTP Server and disconnect
		try
		{
			cmd = "logout";
			ftp.logout();
			cmd = "disconnect";
			ftp.disconnect();
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, cmd, e);
		}
		ftp = null;
		return success;
	}	//	deploy
	
}	//	MMediaServer

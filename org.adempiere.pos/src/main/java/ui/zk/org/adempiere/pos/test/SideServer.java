/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import org.compiere.model.MSysConfig;
import org.compiere.print.ReportCtl;
import org.compiere.util.CLogger;


public class SideServer implements Runnable {
	
	public SideServer() {
		
    }
	/**  PORT							*/
	public 	static final int 		PORT = 5444;
	/**	 Sever Socket					*/
    private static ServerSocket 	serverSocket = null;
    /**	 Client Socket					*/
    private static Socket 			clientSocket = null;
    /**	 Entry							*/
    private static BufferedReader 	m_Entry = null;
    /**	 Thread							*/
    protected Thread       			runningThread= null;
    /**	 Is Stopped						*/
    protected static boolean      	isStopped    = false;
    /**	 log							*/
    private static CLogger 			log = CLogger.getCLogger (SideServer.class);
	    
	@Override
	public void run() {
		openServerSocket();
		
	}
	
	public void conectClient(){
		
	}
	
	public void reportPrint(int record_ID) {
		if(!isStopped()) {
		  DataOutputStream dos;
		try {
			dos = new DataOutputStream( clientSocket.getOutputStream() );
			dos.writeInt(record_ID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		}

	}
	
	public boolean printFile(byte[] p_file, int p_record_ID) {
			if(!isStopped()) {
	              Random rnd = new Random();
			    String m_file = System.getProperty("user.home")+"/test"+rnd.nextInt(10000)+".txt";
			    log.severe("POS temporary print file: m_file== " + m_file);
			    
			    try{
			    	OutputStream out = null;
			    	out= new FileOutputStream(m_file); 
			    	
			    	out.write(p_file); 
			    	out.close();         

				    File file = new File(m_file);
			    	
				    int sizeFile = ( int )file.length();
				    DataOutputStream dos = new DataOutputStream( clientSocket.getOutputStream() );
			    	
			    	dos.writeInt(p_record_ID);
				    dos.writeUTF(file.getName());
				    dos.writeInt(sizeFile);
				 
				    FileInputStream fis = new FileInputStream( m_file );
				    BufferedInputStream bis = new BufferedInputStream( fis ); 
				 
				    BufferedOutputStream bos = new BufferedOutputStream( clientSocket.getOutputStream());
				 
				    byte[] buffer = new byte[sizeFile];
				 
				    bis.read( buffer ); 
				    
				    bis.close();
				    
				 
				    for( int i = 0; i < buffer.length; i++ )
				    {
				        bos.write( buffer[ i ] ); 
				    } 
				    bos.close();
				    if(file.delete())
				    	log.severe("POS file Temp Deleted" + m_file);
			  }
			  catch( Exception e )
			  {
			    log.severe(e.toString());
			  }
		}
		return true;
	}
	private static boolean isStopped() {
	        return isStopped;
	    }
	 
	public synchronized void stop(){
        isStopped = true;
        try {
        	m_Entry.close();
			clientSocket.close();
			serverSocket.close();
			
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
	
	private static void openServerSocket() {
		int port = MSysConfig.getIntValue("ZK_PORT_SERVER_PRINT", PORT);
        try {
        	if(serverSocket == null)
        		serverSocket = new ServerSocket(port);
            
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+port, e);
        }
        try {
        	while(true){
        		clientSocket = serverSocket.accept();
        		System.out.println("Connection Accept: "+ clientSocket);
        	}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}
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
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 **/

public class POSClientSide extends Thread {  

	public POSClientSide(String p_Host, String p_Print) {
		m_Host = p_Host;
		m_Print = p_Print;
		try {
			connect();
			socketClient.setKeepAlive(true);
			isStopped = false;
			start();
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(null, "Error Connecting: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}		
		    
		
	}
	
	/** Socket Client */
	private Socket 	socketClient = null;
	/** Host Name */
	private String 	m_Host = null;
	/** Print Name */
	private String 	m_Print = null;
	/** Is Stop */
	private boolean isStopped;
	/** Data Input Stream */
	DataInputStream dis = null;
	
	private void connect() throws UnknownHostException, IOException {
		socketClient = new Socket(m_Host, 5444);
	}
		
	public void run(){
		

  	  
	    try {
	    	 
	      while(!isStopped) {
			 connect();
	    	 dis = new DataInputStream(socketClient.getInputStream());
              String name = "zk"+dis.readUTF().toString(); 

              // Size File
              int tam = dis.readInt(); 

              String path = System.getProperty("user.home")+File.separator+name ;
              FileOutputStream fos = new FileOutputStream(path);
              BufferedOutputStream out = new BufferedOutputStream( fos );
              BufferedInputStream in = new BufferedInputStream( socketClient.getInputStream() );

              byte[] buffer = new byte[tam];
              for( int i = 0; i < buffer.length; i++ ) {
                 buffer[ i ] = ( byte )in.read( ); 
              }
              
              out.write( buffer ); 
              out.flush(); 
    		  out.close();
    		  try{
    			  String[] cmd = new String[] { "lp" , "-d", m_Print, path};
    			  Runtime.getRuntime().exec(cmd);
    		  }catch(Exception a){
    			  JOptionPane.showMessageDialog(null, "No Printing", "Error", JOptionPane.ERROR_MESSAGE);
    		  }

  		    System.out.println(socketClient.isConnected());
	       }     
	      	
	    } catch (IOException e) {
	    	isStopped=true;
	    	System.out.println("IOException: " + e.getLocalizedMessage());
	    }
	   if(!socketClient.isConnected()) {
			try {
				
				connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	}

	public void closeConnect(){
		try {
			isStopped = true;
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
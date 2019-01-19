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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Properties;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JTextArea;

import org.compiere.model.MSysConfig;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.zkoss.util.media.AMedia;


/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 **/

public class POSClientSide extends Thread {  

	public POSClientSide(Properties p_ctx, String p_Host, String p_Print, JTextArea m_Terminal) {
		m_ctx = p_ctx;
		m_Host = p_Host;
		m_Print = p_Print;
		fTerminal = m_Terminal;
		m_port = MSysConfig.getIntValue("ZK_PORT_SERVER_PRINT", PORT);
		if(!connect())
			this.start();
	}
	/**	Properties		*/
	Properties m_ctx = null;
	/**  Port	Default			*/
	public 	static final int 	PORT = 5400;
	/** S.O Default				*/
	private static final String LINUXSO = "LINUX";
	/** Socket Client 			*/
	private Socket 				socketClient = null;
	/** Host Name 				*/
	private String 				m_Host = null;
	/** Print Name 				*/
	private String 				m_Print = null;
	/** Is Stop 				*/
	private boolean 			isStopped = true;
	/** Data Input Stream 		*/
	private DataInputStream 	dis = null;
	/** Field Terminal    		*/
	private JTextArea 			fTerminal = null;
	private int m_port;
    /**	 log							*/
    private static CLogger 			log = CLogger.getCLogger (POSClientSide.class);
	/**
	 * Connect with Server
	 * @return
	 * @return boolean
	 */
	private boolean connect() {
		if(isStopped()){
			
			try {
				socketClient = new Socket(m_Host, m_port);
				socketClient.setKeepAlive(true);
				isStopped = false;
		    	setText("Connected to host:" + m_Host + " and to port " + m_port);
				
				return isStopped;
			} catch (IOException e) {
		    	setText("Error Connecting: "+e.getMessage());
		    	return isStopped;
			}
		}
		else {
			return isStopped;
		}
	}
		
	public void run(){
		
	    try {
	    	FileOutputStream fos = null;
	    	BufferedOutputStream out = null; 
	      while(!isStopped || !isInterrupted()) {
			 connect();
	    	 dis = new DataInputStream(socketClient.getInputStream());
             int record_ID = dis.readInt(); 
              
	    	 // Name File
             String name = "zk"+dis.readUTF().toString(); 

              // Size File
              int tam = dis.readInt(); 

              String path = System.getProperty("user.home")+File.separator+name ;
			  setText("File path: " + path);
			  log.severe("path== " + path);
              fos = new FileOutputStream(path);
              out = new BufferedOutputStream( fos );
              BufferedInputStream in = new BufferedInputStream( socketClient.getInputStream() );

              FileInputStream fis = new FileInputStream(path);
              byte[] buffer = new byte[tam];
              for( int i = 0; i < buffer.length; i++ ) {
                 buffer[ i ] = ( byte )in.read( ); 
              }

              out.write( buffer );
			  setText("File Received");

		      out.flush(); 
			  out.close(); 
    		  try{
        		  setText("Operating system: " + System.getProperty("os.name"));
    			  if(!System.getProperty("os.name").equalsIgnoreCase(LINUXSO)){
    				  ReportCtl.startDocumentPrint(0, record_ID, true);
//    				  printOtherOS(fis);
    			  }
    			  else {
    				String[] cmd = new String[] { "lp" , "-d", m_Print, path};
        			  Runtime.getRuntime().exec(cmd);
        			  setText("Printing File");
    			  }
    	            
    		  }catch(Exception a){
    			  setText("Error while executing printing: "+a.getMessage());
    		  }
    		  finally {
    			  isStopped = true;
    			  setText("Printing finished");
    			  connect();
    		  }
	    	}
		  
	  
	    } catch (IOException e) {
			  setText("Error in printing process");
	    }
	    finally {
	    	isStopped=true;
	    	setText("Printing process finished");
			  connect();
	    }
	}
	
	/**
	 * Print Other S.O
	 * @param fis
	 * @return void
	 */
	private void printOtherOS(FileInputStream fis){
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(fis, docFormat, null);
 
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
 
        PrintService[] listsPrintService = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);
 
        	 try {
        		 DocPrintJob printJob=null;
        	for (int x=0; x<listsPrintService.length;x++){
        		
        		if(listsPrintService[x].getName().equals(m_Print)){
        			printJob = listsPrintService[x].createPrintJob();
        		}
        	
        	}
                printJob.print(document, attributeSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
	 
	}
	/**
	 * Set Text
	 * @param m_Text
	 * @return void
	 */
	private void setText(String m_Text) {
		fTerminal.setText(getText()+m_Text+"\n");
	}
	
	/**
	 * Get Text
	 * @return String
	 */
	private String getText() {
		return fTerminal.getText();
	}
	
	/**
	 * IsStopped
	 * @return boolean
	 */
	public boolean isStopped() {
		return isStopped;
	}
	
	/**
	 * Close Connection 
	 * @return void
	 */
	public void closeConnect(){
		
			isStopped = true;
			this.interrupt();
			setText("Disconnected");
		
	} 
	
	public String toString(String[] s)
    {
		String res= "";
        for (String temp: s)
        {
          res = res + temp + " ";
        }
        return res;
    }

}
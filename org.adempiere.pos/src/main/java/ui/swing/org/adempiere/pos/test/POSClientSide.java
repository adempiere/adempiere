package org.adempiere.pos.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class POSClientSide {  

	private Socket socketClient = null;
	private String m_Host = null;
	private String m_Print = null;
	
	public POSClientSide(String p_Host, String p_Print) {
		m_Host = p_Host;
		m_Print = p_Print;
	}
	
	public void connect() {
		try {
		      socketClient = new Socket(m_Host, 5444);
		    } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Connecting", "Error", JOptionPane.ERROR_MESSAGE);
		}
	    try {
	      while (true) {
	    	  DataInputStream dis = new DataInputStream(socketClient.getInputStream());
              String name = dis.readUTF().toString(); 

              // Size File
              int tam = dis.readInt(); 
       
              String path = System.getProperty("java.io.tmpdir")+File.separator+name;
              FileOutputStream fos = new FileOutputStream(path);
              BufferedOutputStream out = new BufferedOutputStream( fos );
              BufferedInputStream in = new BufferedInputStream( socketClient.getInputStream() );

              byte[] buffer = new byte[tam];

              for( int i = 0; i < buffer.length; i++ ) {
                 buffer[ i ] = ( byte )in.read( ); 
              }

              out.write( buffer ); 
    		    
    		  try{
    			  String[] cmd = new String[] { "lp" , "-d", m_Print, path};
    			  Runtime.getRuntime().exec(cmd);
    		  }catch(Exception a){
    			  JOptionPane.showMessageDialog(null, "No Printing", "Error", JOptionPane.ERROR_MESSAGE);
    		  }
    		        
	      }	      
	      
	    } catch (IOException e) {
		System.out.println("IOException: " + e.getMessage());
	    }
	}


	public void closeConnect(){
		try {
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
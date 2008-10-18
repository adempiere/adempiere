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
 * 17-Nov-2006 10:46:34 by praveen
 *
 */

package org.posterita.core;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PrintOrderApplet extends Applet implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintService _printService = null;
	private PrintRequestAttributeSet _printRequestAttributeSet = null;
	private byte[] _printData = null;
	
	private String _dataURL = null;
	private String _dataContentType = null;
	
	private JTextField tf;
	
	public void init(){
		//initialising printer
		_printRequestAttributeSet = new HashPrintRequestAttributeSet();
		_printService = PrintServiceLookup.lookupDefaultPrintService();
		
		//drawing applet
		JLabel lbl = new JLabel();
		add(lbl);
		
		tf = new JTextField(40);
		add(tf);
		
		JButton btn = new JButton("Print");
		btn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String url = tf.getText();
						printURL(url);
					}}
				);
		
		add(btn);
		
		if(_printService == null){
			lbl.setText("Unable to find default printer");
		}
		else
		{
			lbl.setText("Found default printer: " + _printService.getName());
		}
		
	}
	
	public void run() {
		if(_printService == null){
			System.out.println("Could not find default printer.");
			return;
		}
			
		boolean isDataReady = loadPrintData();
		
		if(isDataReady){
			printData();
		}
		else
		{
			System.out.println("Unable to load data.");
		}
		
	}
	
	private boolean loadPrintData(){
		try {
			URL url = new URL(_dataURL);
			
			System.out.println("Connecting to :" + _dataURL);
			
			URLConnection conn = url.openConnection();
			_dataContentType = conn.getContentType();
			
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			System.out.println("Reading ......");
			
			byte[] buf = new byte[1024];
	        int len;
	        
	        while ((len = bis.read(buf)) > 0) 
	        {	        	
	        	bos.write(buf, 0, len);
	        }
	        bis.close();
	        
	        bos.flush();
	        bos.close();
	        
	        System.out.println("Reading completed successfully");
	        
	        _printData =  bos.toByteArray();	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean printData(){
		try 
		{			
			SimpleDoc doc;
			
			System.out.println("Printing data as :" + _dataContentType);
			
			//Print data as byte or PDF
			if(_dataContentType.equalsIgnoreCase("application/pdf"))
			{
				//print as pdf
				doc = new SimpleDoc(_printData, DocFlavor.BYTE_ARRAY.PDF, null);
			}
			else
			{
				doc = new SimpleDoc(_printData, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
			}
			
			DocPrintJob job = _printService.createPrintJob();		
			job.print(doc, _printRequestAttributeSet);
			
			System.out.println("Job sent to printer succesfully");
			return true;
			
		} catch (PrintException e) {
			e.printStackTrace();
			System.out.println("Unable to sent job to printer");
			return false;
		}		
	}
	
	public void printURL(String url)
	{
		_dataURL = url;	
		System.out.println("Printing URL: " + url);
		
		Thread thread = new Thread(this);
		thread.start();
		
	}

}

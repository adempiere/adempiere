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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jpedal.PdfDecoder;

/**
 * Modifications: Added pdf printing capability
 * @author ashley
 */
public class PrintOrderApplet extends Applet implements Runnable, ItemListener
{
	private static final long serialVersionUID = 1L;
	/** Printer Name **/
	private String printerName = null;
	/** Print Data **/
	private byte[] _printData = null;
	private String _dataURL = null;
	private String _dataContentType = null;
	private JComboBox cmbPrintServices;
	private PrintService pServices[];
	private JTextField txtUrl;
	
	/**
	 * Initialise the applet and the print services
	 * @see java.applet.Applet#init()
	 */
	public void init()
	{
	    pServices = getPrintServices();
	    cmbPrintServices = new JComboBox();
	    cmbPrintServices.setSize(100, 20);
	    cmbPrintServices.addItemListener(this);
	    
	    for (int i = 0; i < pServices.length; i++)
	    {
	        cmbPrintServices.addItem(pServices[i].getName());
	    }
	    
	    add(cmbPrintServices);
		
		if(pServices.length == 0l)
		{
			cmbPrintServices.addItem("No Printers found on the system");
		}
		
		JLabel lbl = new JLabel();
        add(lbl);
        
        txtUrl = new JTextField(40);
        add(txtUrl);
        
        JButton btn = new JButton("Print");
        btn.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) 
                    {
                        String url = txtUrl.getText();
                        printURL(url);
                    }}
                );
        
        add(btn);
	}
	
	/**
	 * Triggered when printer is changed
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent e)
    {
	    String pName = (String)e.getItem();
	    if (e.getStateChange() == ItemEvent.SELECTED)
	    {
	        this.printerName = pName;
	    }
    }
	
	/**
	 * Launch printing in a thread
	 * @see java.lang.Runnable#run()
	 */
	public void run() 
	{
		PrintService printService = getPrintService(printerName);
		
		if (printService == null)
		{
		    System.out.println("Could not get printer with name: " + printerName);
		    System.out.println("Taking default print service");
		    printService = getDefaultPrintService();
		}
		
		if (printService == null)
		{
		    System.out.println("No printers found");
		    return;
		}
		
		boolean isDataReady = loadPrintData();
		
		if(isDataReady)
		{
			printData(printService);
		}
		else
		{
			System.out.println("Unable to print data.");
		}
	}
	
	/**
	 * Removes network protocol from printer name (Java problem)
	 * @param name Name of printer
	 * @return Name trimmed from Network protocol
	 */
	private String getValidatedPrinterName(String name)
	{
	    if (name == null || name.length() == 0)
	    {
	        return name;
	    }
	    
	    if (name.startsWith("IPP") || name.startsWith("Win32"))
	    {
	        int index = name.indexOf(":");
	        name = name.substring(index + 1, name.length());
	        name = name.trim();
	    }
	    return name;
	}
	
	/**
	 * Get the system's print service by name
	 * @param name Name of Printer
	 * @return PrintService with the 
	 */
	private PrintService getPrintService(String name)
	{
		PrintService pService = null;
		if (name == null || name.trim().length() == 0)
		{
		    return null;
		}
		
		name = getValidatedPrinterName(name);
        PrintService pServices[] = getPrintServices();
        for (int i = 0; i < pServices.length; i++)
        {
            String pServiceName = pServices[i].getName();
            pServiceName = getValidatedPrinterName(pServiceName);
            if (name.equalsIgnoreCase(pServiceName))
            {
                pService = pServices[i];
                break;
            }
        }
		return pService;
	}
	
	/**
	 * Get the system's default print service
	 * @return Default PrintService
	 */
	private PrintService getDefaultPrintService()
	{
	    return PrintServiceLookup.lookupDefaultPrintService();
	}
	
	/**
	 * Get all the print services available on the system
	 * @return System available Print Services
	 */
	private PrintService[] getPrintServices()
	{
	    if (pServices != null && pServices.length > 0)
	    {
	        return pServices;
	    }
	    
	    return PrintServiceLookup.lookupPrintServices(null, null);
	}
	
	/**
	 * Retrieves the data from URL Provided
	 * @return true if loading of data successful
	 */
	private boolean loadPrintData()
	{
		try 
		{
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
	        
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sends the data to the print service based on the content type of the data
	 * @param printService Printer on which to print
	 * @return true if print command sent successfully
	 */
	private boolean printData(PrintService printService)
	{
		try 
		{			
			SimpleDoc doc;
			
			System.out.println("Printing data as:" + _dataContentType);
			
			//Print data as byte or PDF
			if(_dataContentType.equalsIgnoreCase("application/pdf"))
			{
				PdfDecoder decodePDF = new PdfDecoder(true);
				decodePDF.openPdfArray(_printData);
				decodePDF.setPagePrintRange(1, decodePDF.getPageCount());
				decodePDF.setUsePDFPaperSize(true);
				doc = new SimpleDoc(decodePDF, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
			}
			else
			{
				doc = new SimpleDoc(_printData, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
			}
			
			DocPrintJob job = printService.createPrintJob();		
			job.print(doc, new HashPrintRequestAttributeSet());
			
			System.out.println("Job sent to printer succesfully");
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}		
	}
	
	/**
	 * Prints data retrieved from URL
	 * @param url Data URL
	 */
	public void printURL(String url)
	{
		_dataURL = url;	
		System.out.println("Printing URL: " + url);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Returns flat string of the printers 
	 * @return All printers available separated by ###
	 */
	public String getPrintersForJS()
	{
	    if (pServices == null || pServices.length == 0)
	    {
	        return "No printers found, check settings";
	    }
	    
	    String printers = "";
	    for (int i = 0; i < pServices.length - 1; i++)
	    {
	        printers += pServices[i].getName() + "###";
	    }
	    
	    printers += pServices[pServices.length - 1];
	    
	    return printers;
	}
	
    /**
     * @return the printerName
     */
    public String getPrinterName()
    {
        return printerName;
    }

    /**
     * @param printerName the printerName to set
     */
    public void setPrinterName(String printerName)
    {
        this.printerName = printerName;
    }
}

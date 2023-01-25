/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util;

import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.compiere.util.CLogger;
import org.compiere.util.Ini;

/**
 * Util class that replace the base calling of printer
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class PrinterUtil {
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (PrinterUtil.class);
	
	private static PrintService[]   s_services = PrintServiceLookup.lookupPrintServices(null,null);
	
	/**
	 *  Return default PrinterJob
	 *  @return PrinterJob
	 */
	public static PrinterJob getPrinterJob()
	{
		return getPrinterJob(Ini.getProperty(Ini.P_PRINTER));
	}   //  getPrinterJob

	/**
	 *  Return PrinterJob with selected printer name.
	 *  @param printerName if null, get default printer (Ini)
	 *  @return PrinterJob
	 */
	public static PrinterJob getPrinterJob (String printerName)
	{
		PrinterJob pj = null;
		PrintService ps = null;
		try
		{
			pj = PrinterJob.getPrinterJob();

			//  find printer service
			if (printerName == null || printerName.length() == 0)
				printerName = Ini.getProperty(Ini.P_PRINTER);
			if (printerName != null && printerName.length() != 0)
			{
			//	System.out.println("CPrinter.getPrinterJob - searching " + printerName);
				for (int i = 0; i < s_services.length; i++)
				{
					String serviceName = s_services[i].getName();
					if (printerName.equals(serviceName))
					{
						ps = s_services[i];
					//	System.out.println("CPrinter.getPrinterJob - found " + printerName);
						break;
					}
				//	System.out.println("CPrinter.getPrinterJob - not: " + serviceName);
				}
			}   //  find printer service

			try
			{
				if (ps != null)
					pj.setPrintService(ps);
			}
			catch (Exception e)
			{
				log.warning("Could not set Print Service: " + e.toString());
			}
			//
			PrintService psUsed = pj.getPrintService();
			if (psUsed == null)
				log.warning("Print Service not Found");
			else
			{
				String serviceName = psUsed.getName();
				if (printerName != null && !printerName.equals(serviceName))
					log.warning("Not found: " + printerName + " - Used: " + serviceName);
			}
		}
		catch (Exception e)
		{
			log.warning("Could not create for " + printerName + ": " + e.toString());
		}
		return pj;
	}   //  getPrinterJob
	
	/**
	 *  Get Print (Services) Names
	 *  @return Printer Name array
	 */
	public static String[] getPrinterNames()
	{
		// Refresh print services every time the combobox is constructed
		s_services = PrintServiceLookup.lookupPrintServices(null,null);

		String[] retValue = new String[s_services.length];
		for (int i = 0; i < s_services.length; i++)
			retValue[i] = s_services[i].getName();
		return retValue;
	}   //  getPrintServiceNames
	
	/**
	 * 	Get PrintService
	 * 	@return print service
	 */
	public PrintService getPrintService(String currentService)
	{
		for (int i = 0; i < s_services.length; i++)
		{
			if (s_services[i].getName().equals(currentService))
				return s_services[i];
		}
		return PrintServiceLookup.lookupDefaultPrintService();
	}	//	getPrintService
}

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
package org.compiere.print;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.DefaultComboBoxModel;

import org.compiere.swing.CComboBox;
import org.compiere.util.CLogger;
import org.compiere.util.Ini;

/**
 *  Adempiere Printer Selection
 *
 *  @author     Jorg Janke
 *  @version    $Id: CPrinter.java,v 1.3 2006/07/30 00:53:02 jjanke Exp $
 */
public class CPrinter extends CComboBox implements ActionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6366208617152587573L;


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
	 *  Return default PrinterJob
	 *  <br>Deprecated since 3.9.4. Use {@link org.compiere.print.PrintUtil#getPrinterJob()}
	 *  @return PrinterJob
	 */
	@Deprecated
	public static PrinterJob getPrinterJob()
	{
		return PrintUtil.getPrinterJob();
	}   //  getPrinterJob

	/**
	 *  Return PrinterJob with selected printer name.
	 *  <br>Deprecated since 3.9.4. Use {@link org.compiere.print.PrintUtil#getPrinterJob(String)}
	 *  @param printerName if null, get default printer (Ini)
	 *  @return PrinterJob
	 */
	@Deprecated
	public static PrinterJob getPrinterJob (String printerName)
	{
		return PrintUtil.getPrinterJob(printerName);
	}   //  getPrinterJob


	/** Available Printer Services  */
//	private static PrintService[]   s_services = PrinterJob.lookupPrintServices();
	private static PrintService[]   s_services = PrintServiceLookup.lookupPrintServices(null,null);

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (CPrinter.class);
	
	
	/**************************************************************************
	 *  Create PrinterJob
	 */
	public CPrinter()
	{
		super(getPrinterNames());
		//  Set Default
		setValue(Ini.getProperty(Ini.P_PRINTER));
		this.addActionListener(this);
	}   //  CPrinter

	/**
	 * 	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{

	}	//	actionPerformed

	/**
	 * 	Get PrintService
	 * 	@return print service
	 */
	public PrintService getPrintService()
	{
		String currentService = (String)getSelectedItem();
		for (int i = 0; i < s_services.length; i++)
		{
			if (s_services[i].getName().equals(currentService))
				return s_services[i];
		}
		return PrintServiceLookup.lookupDefaultPrintService();
	}	//	getPrintService
	
	/**
	 * 	Refresh printer list
	 */
	public void refresh() {
		String current = (String) getSelectedItem();
		removeAllItems();
		setModel(new DefaultComboBoxModel(getPrinterNames()));
		if (current != null) {
			for (int i = 0; i < getItemCount(); i++) {
				String item = (String) getItemAt(i);
				if (item.equals(current))
					setSelectedIndex(i);
			}
		}
	}

}   //  CPrinter

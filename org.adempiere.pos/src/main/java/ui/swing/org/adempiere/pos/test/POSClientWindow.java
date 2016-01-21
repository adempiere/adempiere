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

import java.awt.Container;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *	Point of Sales Main Client. 
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSClientWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -8800776062735213378L;
 
	public POSClientWindow(){
		init();
		setTitle("Print POS");
		setSize(300,180);
		setLocationRelativeTo(null);
		setResizable(false);
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
	}
	final SystemTray tray = SystemTray.getSystemTray();
	/**	Container			*/
	private Container 	container;
	/**	Button Connect		*/
	private JButton 	btnConnect;
	/**	Button Disconnect		*/
	private JButton 	btnDisconnect;
	/**	Title				*/
	private JLabel 		lblTitle;
	/**	Host				*/
	private JLabel 		lblHost;
	/**	Print				*/
	private JLabel 		lblPrint;
	/**	Host				*/
	private JTextField 	fHost;
	/**	Select Print		*/
	private JComboBox 	cPrint;	
	
	private POSClientSide	p_Client;
	
	private void init() {
		  container = getContentPane();
		  container.setLayout(null);
		  
		  btnConnect = new JButton();
		  btnConnect.setText("Connect");
		  btnConnect.setBounds(60, 100, 100, 23);
		  btnConnect.addActionListener(this);
		  
		  btnDisconnect = new JButton();
		  btnDisconnect.setText("Disconnect");
		  btnDisconnect.setBounds(160, 100, 120, 23);
		  btnDisconnect.addActionListener(this);
		  btnDisconnect.setEnabled(false);
		  
		  lblTitle = new JLabel();
		  lblTitle.setText("Print POS");
		  lblTitle.setBounds(80, 23, 180, 23);
		  
		  lblHost = new JLabel();
		  lblHost.setText("Host Server");
		  lblHost.setBounds(15, 46, 190, 23);
		  
		  lblPrint= new JLabel();
		  lblPrint.setText("Printer");
		  lblPrint.setBounds(45, 69, 190, 23);
		  
		  fHost = new JTextField();
		  fHost.setText("Host Server");
		  fHost.setBounds(100, 46, 180, 23);
		  
		  PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		  cPrint = new JComboBox();
		  for(PrintService s: services){
			  cPrint.addItem(s.getName());
		  }
		  
		  cPrint.setBounds(100, 69, 180, 23);
	
		  container.add(lblTitle);
		  container.add(lblHost);
		  container.add(fHost);
		  container.add(lblPrint);
		  container.add(cPrint);
		  container.add(btnConnect);
		  container.add(btnDisconnect);
		  
	 }
	
	
  @Override
  public void actionPerformed(ActionEvent e)  {
	  boolean status;
      if (e.getSource()==btnConnect) {
    	  String m_print = (String)cPrint.getSelectedItem();
    	  p_Client = new POSClientSide(fHost.getText(), m_print);
    	  status = p_Client.isAlive();
    	  
			btnConnect.setEnabled(!status);
			btnDisconnect.setEnabled(status);
      }
      else if(e.getSource()==btnDisconnect) {
    	  p_Client.closeConnect();
    	  btnConnect.setEnabled(true);
    	  btnDisconnect.setEnabled(false);
      }
    }
  
}

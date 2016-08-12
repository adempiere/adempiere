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

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.compiere.grid.ed.VNumber;
import org.compiere.util.Env;


/**
 *	Point of Sales Main Client. 
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSClientWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -8800776062735213378L;
	
	public POSClientWindow(Properties p_ctx){
		m_ctx=p_ctx;
		init();
		setTitle("Print POS");
		setSize(350,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**	Properties		*/
	Properties m_ctx = null;
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
	/**	Field Terminal		*/
    private JTextArea 	fTerminal = new JTextArea("");
    /** POS Client Side		*/
	private POSClientSide	p_Client;
	
	
	
	private void init() {
		  container = getContentPane();
		  container.setLayout(null);
		  
		  btnConnect = new JButton();
		  btnConnect.setText("Connect");
		  btnConnect.setBounds(70, 123, 100, 23);
		  btnConnect.addActionListener(this);
		  
		  btnDisconnect = new JButton();
		  btnDisconnect.setText("Disconnect");
		  btnDisconnect.setBounds(170, 123, 120, 23);
		  btnDisconnect.addActionListener(this);
		  btnDisconnect.setEnabled(false);
		  
		  lblTitle = new JLabel();
		  lblTitle.setText("Print POS");
		  lblTitle.setBounds(120, 13, 180, 23);
		  
		  lblHost = new JLabel();
		  lblHost.setText("Host Server");
		  lblHost.setBounds(55, 40, 190, 23);
		  
		  fHost = new JTextField();
		  fHost.setText("localhost");
		  fHost.setBounds(140, 40, 180, 23);
		  
		  lblPrint= new JLabel();
		  lblPrint.setText("Printer");
		  lblPrint.setBounds(85, 67, 190, 23);
		  
		  PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		  cPrint = new JComboBox();
		  for(PrintService s: services){
			  cPrint.addItem(s.getName());
		  }
		  cPrint.setBounds(140, 67, 180, 23);
		  
		  fTerminal.setBounds(0,190,300,300);
	        fTerminal.setBackground(Color.black);
	        fTerminal.setForeground(Color.green);
	        fTerminal.setSelectionColor(Color.red);
	        fTerminal.setFont(new Font("consolas",1,10));
	        fTerminal.setEnabled(false);
			fTerminal.setWrapStyleWord(true);
			fTerminal.setLineWrap(true);
	        
		  container.add(lblTitle);
		  container.add(lblHost);
		  container.add(fHost);
		  container.add(lblPrint);
		  container.add(cPrint);
		  container.add(btnConnect);
		  container.add(btnDisconnect);
		  
	       JScrollPane scroll = new JScrollPane(fTerminal);

	        scroll.setBounds(new Rectangle(25,150,300,120));
		  getContentPane().add(scroll);
	 }
	

	public void setHostName(String p_HostName) {
		fHost.setText(p_HostName);
	}
  @Override
  public void actionPerformed(ActionEvent e)  {
	  boolean status;
      if (e.getSource()==btnConnect) {
    	  String m_print = (String)cPrint.getSelectedItem();
    	  Env.setContext(m_ctx, "#Printer", m_print);
    	  p_Client = new POSClientSide(m_ctx, fHost.getText(), m_print, fTerminal);
    	  status = p_Client.isStopped();
    	  
			btnConnect.setEnabled(status);
			btnDisconnect.setEnabled(!status);
      }
      else if(e.getSource()==btnDisconnect) {
    	  p_Client.closeConnect();
    	  btnConnect.setEnabled(true);
    	  btnDisconnect.setEnabled(false);
      }
    }
  
}

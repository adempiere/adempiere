/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.compiere.pos;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.*;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import org.compiere.swing.*;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MOrder;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 *	Checkout Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
 *  @version $Id: SubCheckout.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class SubCheckout extends PosSubPanel implements ActionListener
{
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public SubCheckout (PosPanel posPanel)
	{
		super (posPanel);
	}	//	PosSubCheckout
	
	private CButton f_register = null;
	private CButton f_summary = null;
	private CButton f_process = null;
	private CButton f_print = null;

	//TODO: credit card
/*	private CLabel f_lcreditCardNumber = null;
	private CTextField f_creditCardNumber = null;
	private CLabel f_lcreditCardExp = null;
	private CTextField f_creditCardExp = null;
	private CLabel f_lcreditCardVV = null;
	private CTextField f_creditCardVV = null;
	private CButton f_creditPayment = null;
*/
	private CLabel f_lcashGiven = null;
	private VNumber f_cashGiven;
	private CLabel f_lcashReturn = null;
	private VNumber f_cashReturn;
	private CButton f_cashPayment = null;
	
	private CButton f_cashRegisterFunctions;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubCheckout.class);
	
	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Title
		TitledBorder border = new TitledBorder(Msg.getMsg(Env.getCtx(), "Checkout"));
		setBorder(border);
		
		//	Content
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = INSETS2;
		
		//	--	0
		gbc.gridx = 0;
		f_register = createButtonAction("Register", null);
		gbc.gridy = 0;
		add (f_register, gbc);
		//
		f_summary = createButtonAction("Summary", null);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		add (f_summary, gbc);
		//
		f_process = createButtonAction("Process", null);
		gbc.gridy = 2;
		gbc.gridheight = 1;
		add (f_process, gbc);
		//
		f_print = createButtonAction("Print", null);
		gbc.gridy = 3;
		gbc.gridheight =1;
		add (f_print, gbc);

		//	--	1 -- Cash 
		gbc.gridx = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = .1;
		CPanel cash = new CPanel(new GridBagLayout());
		cash.setBorder(new TitledBorder(Msg.getMsg(Env.getCtx(), "Cash")));
		gbc.gridy = 1;
		add (cash, gbc);
		GridBagConstraints gbc0 = new GridBagConstraints();
		gbc0.insets = INSETS2;
		gbc0.anchor = GridBagConstraints.WEST;
		//
		f_lcashGiven = new CLabel(Msg.getMsg(Env.getCtx(),"CashGiven"));
		cash.add (f_lcashGiven, gbc0);
		f_cashGiven = new VNumber("CashGiven", false, false, true, DisplayType.Amount, 
			Msg.translate(Env.getCtx(), "CashGiven"));
		f_cashGiven.setColumns(10, 25);
		cash.add (f_cashGiven, gbc0);
		f_cashGiven.setValue(Env.ZERO);
		f_cashGiven.addActionListener(this);  //para que actualice el cambio con el dinero entregado
		//
		f_lcashReturn = new CLabel(Msg.getMsg(Env.getCtx(),"CashReturn"));
		cash.add (f_lcashReturn, gbc0);
		f_cashReturn = new VNumber("CashReturn", false, true, false, DisplayType.Amount, 
			"CashReturn");
		f_cashReturn.setColumns(10, 25);
		cash.add (f_cashReturn, gbc0);
		f_cashReturn.setValue(Env.ZERO);
		f_cashPayment = createButtonAction("Payment", null);
		f_cashPayment.setActionCommand("Cash");
		gbc0.anchor = GridBagConstraints.EAST;
		gbc0.weightx = 0.1;		
		cash.add (f_cashPayment, gbc0);  

		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridheight=1;
		CPanel caja = new CPanel();
		add(caja,gbc);
		caja.setPreferredSize(new Dimension(30,30)); 
		f_cashRegisterFunctions = createButtonAction("CashRegisterFunction", null);
		f_cashRegisterFunctions.setText("Cash Drawer\n Movements");
	    f_cashRegisterFunctions.setPreferredSize(new Dimension(160,30));
	    f_cashRegisterFunctions.setMaximumSize(new Dimension(160,30));
		f_cashRegisterFunctions.setMinimumSize(new Dimension(160,30));
		caja.add(f_cashRegisterFunctions);

		
	
//TODO: Credit card
/*  Panel para la introducci�n de los datos de CreditCard para el pago quitada por ConSerTi al no considerar
 *  que sea �til de momento
 	
		//	--	1 -- Creditcard 
		CPanel creditcard = new CPanel(new GridBagLayout());
		creditcard.setBorder(new TitledBorder(Msg.translate(Env.getCtx(), "CreditCardType")));
		gbc.gridy = 2;
		add (creditcard, gbc);
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.insets = INSETS2;
		gbc1.anchor = GridBagConstraints.WEST;
		
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		f_lcreditCardNumber = new CLabel(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		creditcard.add (f_lcreditCardNumber, gbc1);
		gbc1.gridy = 1;
		f_creditCardNumber = new CTextField(18); 
		creditcard.add (f_creditCardNumber, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		f_lcreditCardExp = new CLabel(Msg.translate(Env.getCtx(),"CreditCardExp"));
		creditcard.add (f_lcreditCardExp, gbc1);
		gbc1.gridy = 1;
		f_creditCardExp = new CTextField(5); 
		creditcard.add (f_creditCardExp, gbc1);
		gbc1.gridx = 2;
		gbc1.gridy = 0;
		f_lcreditCardVV = new CLabel(Msg.translate(Env.getCtx(), "CreditCardVV"));
		creditcard.add (f_lcreditCardVV, gbc1);
		gbc1.gridy = 1;
		f_creditCardVV = new CTextField(5); 
		creditcard.add (f_creditCardVV, gbc1);
		//
		gbc1.gridx = 3;
		gbc1.gridy = 0;
		gbc1.gridheight = 2;
		f_creditPayment = createButtonAction("Payment", null);
		f_creditPayment.setActionCommand("CreditCard");
		gbc1.anchor = GridBagConstraints.EAST;
		gbc1.weightx = 0.1;
		creditcard.add (f_creditPayment, gbc1);
		
		**/ //fin del comentario para quitar la parte del CreditCard
	}	//	init

	/**
	 * 	Get Panel Position
	 */
	public GridBagConstraints getGridBagConstraints()
	{
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		return gbc;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		super.dispose();
	}	//	dispose


	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();		
		if (action == null || action.length() == 0)
			return;
		log.info( "PosSubCheckout - actionPerformed: " + action);
		
		//	Register
		if (action.equals("Register"))
		{
			p_posPanel.f_queryTicket.reset();
			p_posPanel.openQuery(p_posPanel.f_queryTicket);
		}
		//	Summary
		else 
		if (action.equals("Summary"))
		{
			displaySummary();			
		}
		else if (action.equals("Process"))
		{
			if (isOrderFullyPay())
			{
				displaySummary();
				printTicket();
				processOrder();
				openCashDrawer();
			}
			else
			{
				p_posPanel.f_status.setStatusLine("Order not fully pay.");
			}
		}
		//	Print
		else if (action.equals("Print"))
		{
			if (isOrderFullyPay())
			{
				displaySummary();
				printTicket();
				openCashDrawer();
			}
			else
			{
				p_posPanel.f_status.setStatusLine("Order not fully pay.");
			}
		}
		//	Cash (Payment)
		else if (action.equals("Cash"))
		{
			displayReturn();			
			openCashDrawer();
		}
		else if (action.equals("CashRegisterFunction"))
		{
			p_posPanel.openQuery(p_posPanel.f_cashfunctions);
		}
		else if (e.getSource() == f_cashGiven)
			displayReturn();
		
/*		//	CreditCard (Payment)
		else if (action.equals("CreditCard"))
		{
			Log.print("CreditCard");
		}  fin del comentario para la Credit Card*/
		
		p_posPanel.updateInfo();
	}	//	actionPerformed

	private void displaySummary() {
		p_posPanel.f_status.setStatusLine(p_posPanel.f_curLine.getOrder().getSummary());
		displayReturn();
	}

	/**
	 * 	Process Order
	 *  @author Comunidad de Desarrollo OpenXpertya 
	 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
	 *         *Copyright � ConSerTi
	 */
	public void processOrder()
	{
		
		p_posPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		MOrder order = p_posPanel.f_curLine.getOrder();
		if (order != null)
			if (order.getDocStatus().equals("DR"))
			{ 
				order.setDocAction(DocAction.ACTION_Complete);
				order.processIt(DocAction.ACTION_Complete);
				order.save();
	
				order = null;
				p_posPanel.newOrder();
				f_cashGiven.setValue(Env.ZERO);
			}
		p_posPanel.setCursor(Cursor.getDefaultCursor());
	}	
	
	/**
	 * 	Print Ticket
	 *  @author Comunidad de Desarrollo OpenXpertya 
	 *  *Basado en Codigo Original Modificado, Revisado y Optimizado de:
	 *  *Copyright � ConSerTi
	 */
	public void printTicket()
	{
		MOrder order = p_posPanel.f_curLine.getOrder();
		//int windowNo = p_posPanel.getWindowNo();
		//Properties m_ctx = p_posPanel.getPropiedades();
		
		if (order != null)
		{
			try 
			{
				//TODO: to incorporate work from Posterita
				/*
				if (p_pos.getAD_PrintLabel_ID() != 0)
					PrintLabel.printLabelTicket(order.getC_Order_ID(), p_pos.getAD_PrintLabel_ID());
				*/ 
				//print standard document
				ReportCtl.startDocumentPrint(ReportEngine.ORDER, order.getC_Order_ID(), null, Env.getWindowNo(this), true);
			}
			catch (Exception e) 
			{
				log.severe("PrintTicket - Error Printing Ticket");
			}
		}	  
	}	

	/**
	 * 	Display cash return
	 *  Display the difference between tender amount and bill amount
	 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � ConSerTi
	 */
	public void displayReturn()
	{
		BigDecimal given = new BigDecimal(f_cashGiven.getValue().toString());
		if (p_posPanel != null && p_posPanel.f_curLine != null)
		{
			MOrder order = p_posPanel.f_curLine.getOrder();
			BigDecimal total = new BigDecimal(0);
			if (order != null)
				total = order.getGrandTotal();
			double vuelta = given.doubleValue() - total.doubleValue();
			f_cashReturn.setValue(new BigDecimal(vuelta));
		}
	}	

	/**
	 * Is order fully pay ?
	 * Calculates if the given money is sufficient to pay the order
	 * 
	 * @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � ConSerTi
	 */
	public boolean isOrderFullyPay()
	{
		BigDecimal given = new BigDecimal(f_cashGiven.getValue().toString());
		boolean paid = false;
		if (p_posPanel != null && p_posPanel.f_curLine != null)
		{
			MOrder order = p_posPanel.f_curLine.getOrder();
			BigDecimal total = new BigDecimal(0);
			if (order != null)
				total = order.getGrandTotal();
			paid = given.doubleValue() >= total.doubleValue();
		}
		return paid;
	}
	
	
	/**
	 * 	Abrir caja
	 *  Abre la caja registradora
	 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � ConSerTi
	 */
	public void openCashDrawer()
	{
		String puerto = null;
		//TODO - to incorporate work from Posterita
		/*
		try
		{
			String sql = "SELECT p.Port"
					+ " FROM AD_PrintLabel l"
					+ " INNER JOIN AD_LabelPrinter p ON (l.AD_LabelPrinter_ID=p.AD_LabelPrinter_ID)"
					+ " WHERE l.AD_PrintLabel_ID=?";
			puerto = DB.getSQLValueString(null, sql, p_pos.getAD_PrintLabel_ID());
		}
		catch(Exception e)
		{
			log.severe("AbrirCaja - Puerto no encontrado");
		}*/
		
		/*
		if (puerto == null)
			log.severe("Port is mandatory for cash drawner");
		
		try
		{
			byte data[] = new byte[5];
			data[0] = 27;
			data[1] = 112;
			data[2] = 0;
			data[3] = 50;
			data[4] = 50;
			FileOutputStream fos = new FileOutputStream(puerto);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(data, 0, data.length);
			bos.close();
			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}	
}	//	PosSubCheckout

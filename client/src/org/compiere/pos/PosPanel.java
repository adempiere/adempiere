/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.apps.ADialog;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MPOS;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Point of Sales Main Window.
 *
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @version $Id: PosPanel.java,v 1.10 2004/07/12 04:10:04 jjanke Exp $
 */
public class PosPanel extends CPanel
	implements FormPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3010214392188209281L;

	/**
	 * 	Constructor - see init 
	 */
	public PosPanel()
	{
		super (new GridBagLayout());
		originalKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m_focusMgr = new PosKeyboardFocusManager();
		KeyboardFocusManager.setCurrentKeyboardFocusManager(m_focusMgr);
	}	//	PosPanel

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	/**	Logger				*/
	private CLogger			log = CLogger.getCLogger(getClass());
	/** Context				*/
	private Properties		m_ctx = Env.getCtx();
	/** Sales Rep 			*/
	private int				m_SalesRep_ID = 0;
	/** POS Model			*/
	protected MPOS			p_pos = null;
	/** Keyoard Focus Manager		*/
	private PosKeyboardFocusManager	m_focusMgr = null;

	/**	Status Bar					*/
	protected StatusBar			f_status = new StatusBar();
	/** Customer Panel				*/
	protected SubBPartner 		f_bpartner = null;
	/** Sales Rep Panel				*/
	protected SubSalesRep 		f_salesRep = null;
	/** Current Line				*/
	protected SubCurrentLine 	f_curLine = null;
	/** Product	Selection			*/
	protected SubProduct 		f_product = null;
	/** All Lines					*/
	protected SubLines 			f_lines = null;
	/** Function Keys				*/
	protected SubFunctionKeys 	f_functionKeys = null;
	/** Checkout					*/
	protected SubCheckout 		f_checkout = null;
	/** Basic Keys					*/
//	protected SubBasicKeys 		f_basicKeys = null;
	
	/**	Product Query Window		*/
	protected QueryProduct		f_queryProduct = null;
	/**	BPartner Query Window		*/
	protected QueryBPartner		f_queryBPartner = null;
	/** Ticket Query Window			*/
	protected QueryTicket 		f_queryTicket = null;
	
	protected CashSubFunctions 	f_cashfunctions;
	
	//	Today's (login) date		*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	
	private KeyboardFocusManager originalKeyboardFocusManager;
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		frame.setMaximize(true);
		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		log.info("init - SalesRep_ID=" + m_SalesRep_ID);
		m_WindowNo = WindowNo;
		m_frame = frame;
		//
		try
		{
			if (!dynInit())
			{
				dispose();
				frame.dispose();
				return;
			}
			frame.getContentPane().add(this, BorderLayout.CENTER);
			frame.getContentPane().add(f_status, BorderLayout.SOUTH);
		 	this.setPreferredSize(new Dimension (850-20,500-20));
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
		log.config( "PosPanel.init - " + getPreferredSize());
		m_focusMgr.start();
	}	//	init

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		if (m_focusMgr != null)
			m_focusMgr.stop();
		m_focusMgr = null;
		KeyboardFocusManager.setCurrentKeyboardFocusManager(originalKeyboardFocusManager);
		//
		if (f_bpartner != null)
			f_bpartner.dispose();
		f_bpartner = null;
		if (f_salesRep != null)
			f_salesRep.dispose();
		f_salesRep = null;
		if (f_curLine != null)
		{
			f_curLine.deleteOrder();
			f_curLine.dispose();
		}
		f_curLine = null;
		if (f_product != null)
			f_product.dispose();
		f_product = null;
		if (f_lines != null)
			f_lines.dispose();
		f_lines = null;
		if (f_functionKeys != null)
			f_functionKeys.dispose();
		f_functionKeys = null;
		if (f_checkout != null)
			f_checkout.dispose();
		f_checkout = null;
/*		if (f_basicKeys != null)
			f_basicKeys.dispose();			removed by ConSerTi upon not appreciating its functionality
		f_basicKeys = null;
*/		//
		if (f_queryProduct != null)
			f_queryProduct.dispose();
		f_queryProduct = null;
		if (f_queryBPartner != null)
			f_queryBPartner.dispose();
		f_queryBPartner = null;
		if (f_queryTicket != null)
			f_queryTicket.dispose();
		f_queryTicket = null;
		//
		if (f_cashfunctions != null)
			f_cashfunctions.dispose();
		f_cashfunctions = null;
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
		m_ctx = null;
	}	//	dispose

	
	/**************************************************************************
	 * 	Dynamic Init.
	 * 	PosPanel has a GridBagLayout.
	 * 	The Sub Panels return their position
	 */
	private boolean dynInit()
	{
		if (!setMPOS())
			return false;
		
		//	Create Sub Panels
		f_bpartner = new SubBPartner (this);
		add (f_bpartner, f_bpartner.getGridBagConstraints());
		//
		f_salesRep = new SubSalesRep (this);
		add (f_salesRep, f_salesRep.getGridBagConstraints());
		//
		f_curLine = new SubCurrentLine (this);
		add (f_curLine, f_curLine.getGridBagConstraints());
		//
		f_product = new SubProduct (this);
		add (f_product, f_product.getGridBagConstraints());
		//
		f_lines = new SubLines (this);
		add (f_lines, f_lines.getGridBagConstraints());
		//
		f_functionKeys = new SubFunctionKeys (this);
		add (f_functionKeys, f_functionKeys.getGridBagConstraints());
		//
		f_checkout = new SubCheckout (this);
		add (f_checkout, f_checkout.getGridBagConstraints());
		//
/*		f_basicKeys = new SubBasicKeys (this);
		add (f_basicKeys, f_basicKeys.getGridBagConstraints());  removed by ConSerTi upon not appreciating its functionality
*/		
		//	--	Query
		f_queryProduct = new QueryProduct (this);
		add (f_queryProduct, f_queryProduct.getGridBagConstraints());
		//
		f_queryBPartner = new QueryBPartner (this);
		add (f_queryBPartner, f_queryBPartner.getGridBagConstraints());
		//
		f_queryTicket = new QueryTicket(this);
		add (f_queryTicket, f_queryTicket.getGridBagConstraints());
		//
		f_cashfunctions = new CashSubFunctions(this);
		add (f_cashfunctions, f_cashfunctions.getGridBagConstraints());
		
		newOrder();
		return true;
	}	//	dynInit

	/**
	 * 	Set MPOS
	 *	@return true if found/set
	 */
	private boolean setMPOS()
	{
		MPOS[] poss = null;
		if (m_SalesRep_ID == 100)	//	superUser
			poss = getPOSs (0);
		else
			poss = getPOSs (m_SalesRep_ID);
		//
		if (poss.length == 0)
		{
			ADialog.error(m_WindowNo, m_frame, "NoPOSForUser");
			return false;
		}
		else if (poss.length == 1)
		{
			p_pos = poss[0];
			return true;
		}

		//	Select POS
		String msg = Msg.getMsg(m_ctx, "SelectPOS");
		String title = Env.getHeader(m_ctx, m_WindowNo);
		Object selection = JOptionPane.showInputDialog(m_frame, msg, title, 
			JOptionPane.QUESTION_MESSAGE, null, poss, poss[0]);
		if (selection != null)
		{
			p_pos = (MPOS)selection;
			return true;
		}
		return false;
	}	//	setMPOS
	
	/**
	 * 	Get POSs for specific Sales Rep or all
	 *	@param SalesRep_ID
	 *	@return array of POS
	 */
	private MPOS[] getPOSs (int SalesRep_ID)
	{
		String pass_field = "SalesRep_ID";
		int pass_ID = SalesRep_ID;
		if (SalesRep_ID==0)
			{
			pass_field = "AD_Client_ID";
			pass_ID = Env.getAD_Client_ID(m_ctx);
			}
		return MPOS.getAll(m_ctx, pass_field, pass_ID);
	}	//	getPOSs
	
	/**
	 * 	Set Visible
	 *	@param aFlag visible
	 */
	public void setVisible (boolean aFlag)
	{
		super.setVisible (aFlag);
		f_product.f_name.requestFocus();
	}	//	setVisible

	
	/**
	 * 	Open Query Window
	 *	@param panel
	 */
	public void openQuery (CPanel panel)
	{
			f_bpartner.setVisible(false);
			f_salesRep.setVisible(false);
			f_curLine.setVisible(false);
			f_product.setVisible(false);

		f_checkout.setVisible(false);
//		f_basicKeys.setVisible(false);  removed by ConSerTi upon not appreciating its functionality
		f_lines.setVisible(false);
		f_functionKeys.setVisible(false);
		panel.setVisible(true);
		
	}	//	closeQuery

	/**
	 * 	Close Query Window
	 *	@param panel
	 */
	public void closeQuery (CPanel panel)
	{
		panel.setVisible(false);
		f_bpartner.setVisible(true);
		f_salesRep.setVisible(true);
		f_curLine.setVisible(true);
		f_product.setVisible(true);
//		f_basicKeys.setVisible(true);   removed by ConSerTi upon not appreciating its functionality
		f_lines.setVisible(true);
		f_functionKeys.setVisible(true);
		f_checkout.setVisible(true);
	}	//	closeQuery

	/**************************************************************************
	 * 	Get Today's date
	 *	@return date
	 */
	public Timestamp getToday()
	{
		return m_today;
	}	//	getToday
	
	/**
	 * 	New Order
	 *   
	 */
	public void newOrder()
	{
		log.info( "PosPanel.newOrder");
		f_bpartner.setC_BPartner_ID(0);
		f_curLine.newOrder();
		f_curLine.newLine();
		f_product.f_name.requestFocus();
		updateInfo();
	}	//	newOrder
	
	/**
	 * Get the number of the window for the function calls that it needs 
	 * 
	 * @return the window number
	 */
	public int getWindowNo()
	{
		return m_WindowNo;
	}
	
	/**
	 * Get the properties for the process calls that it needs
	 * 
	 * @return las Propiedades m_ctx
	 */
	public Properties getPropiedades()
	{
		return m_ctx;
	}
	
	public void updateInfo()
	{
		if (f_lines != null)
			f_lines.updateTable(f_curLine.getOrder());
		if (f_checkout != null)
			f_checkout.displayReturn();
	}	
}	//	PosPanel


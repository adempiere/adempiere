/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.pos;

import java.awt.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import org.compiere.apps.*;
import org.compiere.apps.form.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Point of Sales Main Window.
 *
 *  @author Jorg Janke
 *  @version $Id: PosPanel.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class PosPanel extends CPanel
	implements FormPanel
{
	/**
	 * 	Constructor - see init 
	 */
	public PosPanel()
	{
		super (new GridBagLayout());
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
	protected SubBasicKeys 		f_basicKeys = null;
	
	/**	Product Query Window		*/
	protected QueryProduct		f_queryProduct = null;
	/**	BPartner Query Window		*/
	protected QueryBPartner		f_queryBPartner = null;
	
	
	//	Today's (login) date		*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
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
		//	this.setPreferredSize(new Dimension (800-20,600-20));
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
		//
		if (f_bpartner != null)
			f_bpartner.dispose();
		f_bpartner = null;
		if (f_salesRep != null)
			f_salesRep.dispose();
		f_salesRep = null;
		if (f_curLine != null)
			f_curLine.dispose();
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
		if (f_basicKeys != null)
			f_basicKeys.dispose();
		f_basicKeys = null;
		//
		if (f_queryProduct != null)
			f_queryProduct.dispose();
		f_queryProduct = null;
		if (f_queryBPartner != null)
			f_queryBPartner.dispose();
		f_queryBPartner = null;
		//
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
		f_basicKeys = new SubBasicKeys (this);
		add (f_basicKeys, f_basicKeys.getGridBagConstraints());
		
		//	--	Query
		f_queryProduct = new QueryProduct (this);
		add (f_queryProduct, f_queryProduct.getGridBagConstraints());
		//
		f_queryBPartner = new QueryBPartner (this);
		add (f_queryBPartner, f_queryBPartner.getGridBagConstraints());
		//
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
			p_pos = (MPOS)selection;;
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
		ArrayList<MPOS> list = new ArrayList<MPOS>();
		String sql = "SELECT * FROM C_POS WHERE SalesRep_ID=?";
		if (SalesRep_ID == 0)
			sql = "SELECT * FROM C_POS WHERE AD_Client_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			if (SalesRep_ID != 0)
				pstmt.setInt (1, m_SalesRep_ID);
			else
				pstmt.setInt (1, Env.getAD_Client_ID(m_ctx));
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MPOS(m_ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MPOS[] retValue = new MPOS[list.size ()];
		list.toArray (retValue);
		return retValue;
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
		f_checkout.setVisible(false);
		f_basicKeys.setVisible(false);
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
		f_checkout.setVisible(true);
		f_basicKeys.setVisible(true);
		f_lines.setVisible(true);
		f_functionKeys.setVisible(true);
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
	 */
	public void newOrder()
	{
		log.info( "PosPabel.newOrder");
		f_bpartner.setC_BPartner_ID(0);
		f_curLine.newLine();
		f_product.f_name.requestFocus();
	}	//	newOrder
	
}	//	PosPanel


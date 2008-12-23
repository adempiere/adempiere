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
package compiere.model;

import java.math.BigDecimal;

import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRole;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;


/**
 *	Validator Example Implementation
 *	
 *	@author Jorg Janke
 *	@version $Id: MyValidator.java,v 1.2 2006/07/30 00:51:57 jjanke Exp $
 */
public class MyValidator implements ModelValidator
{
	/**
	 *	Constructor.
	 */
	public MyValidator ()
	{
		super ();
	}	//	MyValidator
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MyValidator.class);
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	/** User	*/
	private int		m_AD_User_ID = -1;
	/** Role	*/
	private int		m_AD_Role_ID = -1;
	
	/**
	 *	Initialize Validation
	 *	@param engine validation engine 
	 *	@param client client
	 */
	public void initialize (ModelValidationEngine engine, MClient client)
	{
		//client = null for global validator
		if (client != null) {	
			m_AD_Client_ID = client.getAD_Client_ID();
			log.info(client.toString());
		}
		else  {
			log.info("Initializing global validator: "+this.toString());
		}
		
		//	We want to be informed when C_Order is created/changed
		engine.addModelChange(MOrder.Table_Name, this);
		//	We want to validate Order before preparing 
		engine.addDocValidate(MOrder.Table_Name, this);
	}	//	initialize

    /**
     *	Model Change of a monitored Table.
     *	Called after PO.beforeSave/PO.beforeDelete
     *	when you called addModelChange for the table
     *	@param po persistent object
     *	@param type TYPE_
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
     */
	public String modelChange (PO po, int type) throws Exception
	{
		if (po.get_TableName().equals("C_Order") && type == TYPE_CHANGE)
		{
			MOrder order = (MOrder)po;
			log.info(po.toString());
		}
		return null;
	}	//	modelChange
	
	/**
	 *	Validate Document.
	 *	Called as first step of DocAction.prepareIt 
     *	when you called addDocValidate for the table.
     *	Note that totals, etc. may not be correct.
	 *	@param po persistent object
	 *	@param timing see TIMING_ constants
     *	@return error message or null
	 */
	public String docValidate (PO po, int timing)
	{
		log.info(po.get_TableName() + " Timing: "+timing);
		
		//	Ignore all after Complete events
		if (timing == TIMING_AFTER_COMPLETE)
			return null;
		
		if (timing == TIMING_BEFORE_PREPARE) {
			if (po.get_TableName().equals(MOrder.Table_Name))
			{
				/**	Order Discount Example	*
				MOrder order = (MOrder)po;
				String error = orderDiscount(order);
				if (error != null)
					return error;
				/** Order Discount Example */
				log.info(po.toString());
			}
		}

		return null;
	}	//	docValidate
	
	/**
	 * 	Order Discount.
	 * 	Make sure that last line is discount and check correctness
	 *	@param order order
	 *	@return error message or null 
	 */
	private String orderDiscount (MOrder order)
	{
		String DISCOUNT = "Discount";
		int C_Tax_ID = 0;
		BigDecimal totalLines = Env.ZERO;
		MOrderLine discountLine = null;
		//
		MOrderLine[] lines = order.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MOrderLine oLine = lines[i];
			String description = oLine.getDescription();
			if (description != null && description.equals(DISCOUNT))
				discountLine = oLine;
			else
			{
				totalLines = totalLines.add(oLine.getLineNetAmt());
				if (C_Tax_ID == 0)
					C_Tax_ID = oLine.getC_Tax_ID();
				else if (C_Tax_ID != oLine.getC_Tax_ID())
					return "Order has more then one Tax, cannot add discount";
			}
		}
		if (discountLine == null)
		{
			discountLine = new MOrderLine(order);
			discountLine.setDescription(DISCOUNT);
			discountLine.setQty(Env.ONE);
			discountLine.setC_Tax_ID(C_Tax_ID);
			discountLine.setLine(9999);
		}
		//	Calculate Discount
		BigDecimal discountPercent = new BigDecimal(3);	//	3% example
		BigDecimal discountAmt = totalLines.multiply(discountPercent);
		discountAmt = discountAmt.divide(Env.ONEHUNDRED, order.getPrecision(), BigDecimal.ROUND_HALF_UP);
		discountLine.setPrice(discountAmt.negate());
		if (!discountLine.save())
			return "Could not save discount line";
		
		log.info(discountLine.toString());
		order.getLines(true, null);
		return null;
	}	//	orderDiscount
	
	
	
	/**
	 *	User Login.
	 *	Called when preferences are set
	 *	@param AD_Org_ID org
	 *	@param AD_Role_ID role
	 *	@param AD_User_ID user
	 *	@return error message or null
	 */
	public String login (int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		log.info("AD_User_ID=" + AD_User_ID);
		m_AD_User_ID = AD_User_ID;
		m_AD_Role_ID = AD_Role_ID;
		return null;
	}	//	login

	/**
	 *	Get Client to be monitored
	 *	@return AD_Client_ID client
	 */
	public int getAD_Client_ID()
	{
		return m_AD_Client_ID;
	}	//	getAD_Client_ID

	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MyValidator[Order@Gardenworld");
		sb.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * Sample Validator Before Save Properties - to set mandatory properties on users
	 * avoid users changing properties
	 */
	public void beforeSaveProperties() {
		// not for SuperUser or role SysAdmin
		if (   m_AD_User_ID == 0  // System
			|| m_AD_User_ID == 100   // SuperUser
			|| m_AD_Role_ID == 0  // System Administrator
			|| m_AD_Role_ID == 1000000)  // ECO Admin
			return;

		log.info("Setting default Properties");

		MRole role = MRole.get(Env.getCtx(), m_AD_Role_ID);

		// Example - if you don't want user to select auto commit property
		// Ini.setProperty(Ini.P_A_COMMIT, false);
		
		// Example - if you don't want user to select auto login
		// Ini.setProperty(Ini.P_A_LOGIN, false);

		// Example - if you don't want user to select store password
		// Ini.setProperty(Ini.P_STORE_PWD, false);

		// Example - if you want your user inherit ALWAYS the show accounting from role
		// Ini.setProperty(Ini.P_SHOW_ACCT, role.isShowAcct());
		
		// Example - if you want to avoid your user from changing the working date
		/*
		Timestamp DEFAULT_TODAY =	new Timestamp(System.currentTimeMillis());
		//  Date (remove seconds)
		DEFAULT_TODAY.setHours(0);
		DEFAULT_TODAY.setMinutes(0);
		DEFAULT_TODAY.setSeconds(0);
		DEFAULT_TODAY.setNanos(0);
		Ini.setProperty(Ini.P_TODAY, DEFAULT_TODAY.toString());
		Env.setContext(Env.getCtx(), "#Date", DEFAULT_TODAY);
		*/

	}	// beforeSaveProperties

}	//	MyValidator

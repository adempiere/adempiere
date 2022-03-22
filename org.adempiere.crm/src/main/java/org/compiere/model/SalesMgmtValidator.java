package org.compiere.model;
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


import org.compiere.util.CLogger;


/**
 *	Validator for Sales Management module
 *	
 *  @author Paul Bowden www.adaxa.com.au
 */
public class SalesMgmtValidator implements ModelValidator
{
	public SalesMgmtValidator ()
	{
		super ();
	}
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SalesMgmtValidator.class);
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	
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

		//	Tables to be monitored
		engine.addModelChange(MOrder.Table_Name, this);
		engine.addModelChange(MOrderLine.Table_Name, this);
		
		//	Documents to be monitored
	//	engine.addDocValidate(MInvoice.Table_Name, this);

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
		log.info(po.get_TableName() + " Type: "+type);

		if (po.get_TableName().equals(MOrder.Table_Name) &&
				(type == ModelValidator.TYPE_AFTER_NEW || type == ModelValidator.TYPE_AFTER_CHANGE ) )
		{
			if ( po == null )
				return null;
			
			MOrder order = (MOrder) po;

			syncOpportunity(order);
		}
		

		if (po.get_TableName().equals(MOrderLine.Table_Name) &&
				(type == ModelValidator.TYPE_AFTER_NEW || type == ModelValidator.TYPE_AFTER_CHANGE ) )
		{
			if ( po == null )
				return null;
			
			MOrderLine line = (MOrderLine) po;
			
			MOrder order = (MOrder) line.getC_Order();

			syncOpportunity(order);
		}
		
		return null;
	}	//	modelChange

	private void syncOpportunity(MOrder order) {
		int opId = order.get_ValueAsInt("C_Opportunity_ID");
		if ( opId > 0 )
		{
			MOpportunity op = new MOpportunity(order.getCtx(), opId, order.get_TrxName());
			if (op != null && op.getC_Order_ID() == order.getC_Order_ID())
				op.setOpportunityAmt(order.getGrandTotal());
			op.saveEx();
		}
	}

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
		return null;
	}	//	docValidate

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
		StringBuffer sb = new StringBuffer ("SalesMgmtValidator");
		return sb.toString ();
	}	//	toString
	
}	//	Validator
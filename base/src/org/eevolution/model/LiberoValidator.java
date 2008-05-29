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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.*;
import java.util.logging.Level;
import java.util.*;

import org.compiere.model.*;
import org.compiere.util.*;


/**
 *	LiberoValidator 
 *	
 *	@author Victor Perez
 *	@version $Id: LiberoValidator.java,v 1 vpj-cd Exp $
 */
public class LiberoValidator implements ModelValidator
{
	/**
	 *	Constructor.
	 *	The class is instanciated when logging in and client is selected/known
	 */
	public LiberoValidator ()
	{
		super ();
	}	//	LiberoValidator
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(LiberoValidator.class);
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
		engine.addModelChange(MRequisitionLine.Table_Name, this);
		engine.addModelChange(MClient.Table_Name, this);
		engine.addModelChange(X_M_ForecastLine.Table_Name, this);
		//engine.addModelChange(MDDOrderLine.Table_Name, this);
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
		if (po.get_TableName().equals(MOrderLine.Table_Name) && ( type == TYPE_NEW || type == TYPE_AFTER_CHANGE ))
		{
			MOrderLine ol = (MOrderLine)po;
			org.eevolution.model.MPPMRP.C_OrderLine(ol, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MOrderLine.Table_Name) && type == TYPE_DELETE)
		{
			MOrderLine ol = (MOrderLine)po;
			org.eevolution.model.MPPMRP.C_OrderLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && (type == TYPE_NEW  ||  type == TYPE_AFTER_CHANGE ))
		{
			MRequisitionLine rl = (MRequisitionLine)po;
			org.eevolution.model.MPPMRP.M_RequisitionLine(rl, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && type == TYPE_DELETE )
		{
			MRequisitionLine ol = (MRequisitionLine)po;
			org.eevolution.model.MPPMRP.M_RequisitionLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name) && (type == TYPE_NEW || type ==  TYPE_AFTER_CHANGE ))
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			org.eevolution.model.MPPMRP.M_ForecastLine(ol, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name) && type == TYPE_DELETE)
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			org.eevolution.model.MPPMRP.M_ForecastLine(ol, true);
			log.info(po.toString());
		}
		/*if (po.get_TableName().equals(MDDOrderLine.Table_Name) && (type == TYPE_NEW || type ==  TYPE_AFTER_CHANGE ))
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			org.eevolution.model.MPPMRP.DD_Order_Line(ol , false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MDDOrderLine.Table_Name) && type == TYPE_DELETE)
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			org.eevolution.model.MPPMRP.M_ForecastLine(ol, true);
			log.info(po.toString());
		}*/
		
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
		//if (timing == TIMING_AFTER_COMPLETE)
		//	return null;
		
		if (timing == TIMING_AFTER_COMPLETE) {
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
		StringBuffer sb = new StringBuffer ("LiberoValidator");
		return sb.toString ();
	}	//	toString
	
}	//	LiberoValidator

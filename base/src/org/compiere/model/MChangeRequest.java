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
package org.compiere.model;

import java.sql.*;
import java.util.*;

import org.compiere.util.*;

/**
 * 	Change Request Model
 *  @author Jorg Janke
 *  @version $Id: MChangeRequest.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class MChangeRequest extends X_M_ChangeRequest
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ChangeRequest_ID ix
	 *	@param trxName trx
	 */
	public MChangeRequest (Properties ctx, int M_ChangeRequest_ID, String trxName)
	{
		super (ctx, M_ChangeRequest_ID, trxName);
		if (M_ChangeRequest_ID == 0)
		{
		//	setName (null);
			setIsApproved(false);
			setProcessed(false);
		}
	}	//	MChangeRequest

	/**
	 * 	CRM Request Constructor
	 *	@param request request
	 *	@param group request group
	 */
	public MChangeRequest (MRequest request, MGroup group)
	{
		this (request.getCtx(), 0, request.get_TrxName());
		setClientOrg(request);
		setName(Msg.getElement(getCtx(), "R_Request_ID") + ": " + request.getDocumentNo());
		setHelp(request.getSummary());
		//
		setM_BOM_ID(group.getM_BOM_ID());
		setM_ChangeNotice_ID(group.getM_ChangeNotice_ID());
	}	//	MChangeRequest
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MChangeRequest (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MChangeRequest

	/**
	 * 	Get CRM Requests of Change Requests
	 *	@return requests
	 */
	public MRequest[] getRequests()
	{
		String sql = "SELECT * FROM R_Request WHERE M_ChangeRequest_ID=?";
		return null;
	}	//	getRequests
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true/false
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Have at least one
		if (getM_BOM_ID() == 0 && getM_ChangeNotice_ID() == 0)
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@: @M_BOM_ID@ / @M_ChangeNotice_ID@"));
			return false;
		}
		
		//	Derive ChangeNotice from BOM if defined
		if (newRecord && getM_BOM_ID() != 0 && getM_ChangeNotice_ID() == 0)
		{
			MBOM bom = new MBOM (getCtx(), getM_BOM_ID(), get_TrxName());
			if (bom.getM_ChangeNotice_ID() != 0)
				setM_BOM_ID(bom.getM_ChangeNotice_ID());
		}
		
		return true;
	}	//	beforeSave
	
}	//	MChangeRequest

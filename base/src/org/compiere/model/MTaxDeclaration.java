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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.TimeUtil;

/**
 * 	Tax Declaration Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MTaxDeclaration.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MTaxDeclaration extends X_C_TaxDeclaration
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 560499885058913281L;

	/**
	 * 	Standard Constructors
	 *	@param ctx context
	 *	@param C_TaxDeclaration_ID ic
	 *	@param trxName trx
	 */
	public MTaxDeclaration (Properties ctx, int C_TaxDeclaration_ID, String trxName)
	{
		super (ctx, C_TaxDeclaration_ID, trxName);
	}	//	MTaxDeclaration

	/**
	 * 	Load Constructor
	 *	@param ctx context 
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MTaxDeclaration (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MTaxDeclaration
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (is_ValueChanged("DateFrom"))
			setDateFrom(TimeUtil.getDay(getDateFrom()));
		if (is_ValueChanged("DateTo"))
			setDateTo(TimeUtil.getDay(getDateTo()));
		return true;
	}	//	beforeSave
	
}	//	MTaxDeclaration

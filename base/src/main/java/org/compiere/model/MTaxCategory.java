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
import java.util.List;

import org.adempiere.exceptions.AdempiereException;

/**
 * 	Tax Category Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MTaxCategory.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MTaxCategory extends X_C_TaxCategory
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5521670797405300136L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_TaxCategory_ID id
	 *	@param trxName trx
	 */
	public MTaxCategory (Properties ctx, int C_TaxCategory_ID, String trxName)
	{
		super (ctx, C_TaxCategory_ID, trxName);
		if (C_TaxCategory_ID == 0)
		{
		//	setName (null);
			setIsDefault (false);
		}
	}	//	MTaxCategory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MTaxCategory (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MTaxCategory
	
	/**
	 * 	getDefaultTax
	 *	Get the default tax id associated with this tax category
	 *	
	 */
	public MTax getDefaultTax()
	{
		MTax m_tax = new MTax(getCtx(), 0, get_TrxName());
		
		final String whereClause = COLUMNNAME_C_TaxCategory_ID+"=? AND "+ COLUMNNAME_IsDefault+"='Y'";
		List<MTax> list = new Query(getCtx(), I_C_Tax.Table_Name, whereClause,  get_TrxName())
			.setParameters(getC_TaxCategory_ID())
			.setOnlyActiveRecords(true)
			.list();
		if (list.size() == 0) {
			throw new AdempiereException("NoDefaultTaxRate"); // Error - should be at least one default
		} else if (list.size() == 1) {
			m_tax = list.get(0);
		} else {
			throw new AdempiereException("TooManyDefaults"); // Error - should only be one default
		}
		
		return m_tax;
	} // getDefaultTax
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName()+"["+get_ID()
		+", Name="+getName()
		+", IsDefault="+isDefault()
		+", IsActive="+isActive()
		+"]";
	}
}	//	MTaxCategory

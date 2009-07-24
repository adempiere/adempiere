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

import org.compiere.util.Env;

/**
 * 	Tax Declaration Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MTaxDeclarationLine.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MTaxDeclarationLine extends X_C_TaxDeclarationLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2723299110241208689L;

	/**
	 * 	Standard Constructor
	 *	@param ctx ctx
	 *	@param C_TaxDeclarationLine_ID id
	 *	@param trxName trx
	 */
	public MTaxDeclarationLine (Properties ctx, int C_TaxDeclarationLine_ID, String trxName)
	{
		super (ctx, C_TaxDeclarationLine_ID, trxName);
		if (C_TaxDeclarationLine_ID == 0)
		{
			setIsManual(true);
			setTaxAmt (Env.ZERO);
			setTaxBaseAmt (Env.ZERO);
		}
	}	//	MTaxDeclarationLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs rs
	 *	@param trxName trx
	 */
	public MTaxDeclarationLine (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MTaxDeclarationLine

	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param invoice invoice
	 *	@param iLine invoice line
	 */
	public MTaxDeclarationLine (MTaxDeclaration parent, MInvoice invoice, MInvoiceLine iLine)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(invoice);
		setC_TaxDeclaration_ID(parent.getC_TaxDeclaration_ID());
		setIsManual(false);
		//
		setC_Invoice_ID(invoice.getC_Invoice_ID());
		setC_BPartner_ID (invoice.getC_BPartner_ID());
		setC_Currency_ID (invoice.getC_Currency_ID());
		setDateAcct (invoice.getDateAcct());
		//
		setC_InvoiceLine_ID(iLine.getC_InvoiceLine_ID());
		setC_Tax_ID (iLine.getC_Tax_ID());
		if (invoice.isTaxIncluded())
		{
			setTaxBaseAmt (iLine.getLineNetAmt());
			setTaxAmt (iLine.getTaxAmt());
		}
		else
		{
			setTaxBaseAmt (iLine.getLineNetAmt());
			setTaxAmt (iLine.getTaxAmt());
		}
	}	//	MTaxDeclarationLine
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param invoice invoice
	 *	@param tLine tax line
	 */
	public MTaxDeclarationLine (MTaxDeclaration parent, MInvoice invoice, MInvoiceTax tLine)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(invoice);
		setC_TaxDeclaration_ID(parent.getC_TaxDeclaration_ID());
		setIsManual(false);
		//
		setC_Invoice_ID(invoice.getC_Invoice_ID());
		setC_BPartner_ID (invoice.getC_BPartner_ID());
		setC_Currency_ID (invoice.getC_Currency_ID());
		setDateAcct (invoice.getDateAcct());
		//
		setC_Tax_ID (tLine.getC_Tax_ID());
		setTaxBaseAmt (tLine.getTaxBaseAmt());
		setTaxAmt (tLine.getTaxAmt());
	}	//	MTaxDeclarationLine
	

}	//	MTaxDeclarationLine

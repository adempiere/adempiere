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
package org.compiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaySelectionLine;
import org.compiere.model.X_C_Order;
import org.compiere.util.AdempiereUserError;
 

/**
 *	Create Checks from Payment Selection Line
 *	
 *  @author Jorg Janke
 *  @version $Id: PaySelectionCreateCheck.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document, this process is changed to 
 *			document workflow of Payment Selection
 *		@see https://github.com/adempiere/adempiere/issues/297
 *	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
@Deprecated
public class PaySelectionCreateCheck extends SvrProcess
{
	/**	Target Payment Rule			*/
	private String		p_PaymentRule = null;
	/**	Payment Selection			*/
	private int			p_C_PaySelection_ID = 0;
	/** The checks					*/
	private List<MPaySelectionCheck> paySelectionChecks = new ArrayList<MPaySelectionCheck>();
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("PaymentRule"))
				p_PaymentRule = (String)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_PaySelection_ID = getRecord_ID();
		if (p_PaymentRule != null && p_PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDebit))
			p_PaymentRule = null;
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt () throws Exception
	{
		log.info ("C_PaySelection_ID=" + p_C_PaySelection_ID
			+ ", PaymentRule=" + p_PaymentRule);
		
		MPaySelection paySelection = new MPaySelection (getCtx(), p_C_PaySelection_ID, get_TrxName());
		if (paySelection.get_ID() == 0)
			throw new IllegalArgumentException("Not found C_PaySelection_ID=" + p_C_PaySelection_ID);
		if (paySelection.isProcessed())
			throw new IllegalArgumentException("@Processed@");

		for (MPaySelectionLine paySelectionLine : paySelection.getLines(false))
		{
			if (!paySelectionLine.isActive() || paySelectionLine.isProcessed())
				continue;
			createCheck (paySelectionLine);
		}
		//
		paySelection.setProcessed(true);
		paySelection.saveEx();
		
		return "@C_PaySelectionCheck_ID@ - #" + paySelectionChecks.size();
	}	//	doIt

	/**
	 * 	Create Check from line
	 *	@param line
	 *	@throws Exception for invalid bank accounts
	 */
	private void createCheck (MPaySelectionLine line) throws Exception
	{
		//	Try to find one
		for (int i = 0; i < paySelectionChecks.size(); i++)
		{
			MPaySelectionCheck check = (MPaySelectionCheck) paySelectionChecks.get(i);
			//	Add to existing
			if (check.getC_BPartner_ID() == line.getInvoice().getC_BPartner_ID())
			{
				check.addLine(line);
				if (!check.save())
					throw new IllegalStateException("Cannot save MPaySelectionCheck");
				line.setC_PaySelectionCheck_ID(check.getC_PaySelectionCheck_ID());
				line.setProcessed(true);
				if (!line.save())
					throw new IllegalStateException("Cannot save MPaySelectionLine");
				return;
			}
		}
		//	Create new
		String PaymentRule = line.getPaymentRule();
		if (p_PaymentRule != null)
		{
			if (!X_C_Order.PAYMENTRULE_DirectDebit.equals(PaymentRule))
				PaymentRule = p_PaymentRule;
		}
		MPaySelectionCheck check = new MPaySelectionCheck(line, PaymentRule);
		if (!check.isValid())
		{
			int C_BPartner_ID = check.getC_BPartner_ID();
			MBPartner bp = MBPartner.get(getCtx(), C_BPartner_ID);
			String msg = "@NotFound@ @C_BP_BankAccount@: " + bp.getName();
			throw new AdempiereUserError(msg);
		}
		if (!check.save())
			throw new IllegalStateException("Cannot save MPaySelectionCheck");
		line.setC_PaySelectionCheck_ID(check.getC_PaySelectionCheck_ID());
		line.setProcessed(true);
		if (!line.save())
			throw new IllegalStateException("Cannot save MPaySelectionLine");
		paySelectionChecks.add(check);
	}	//	createCheck
	
}	//	PaySelectionCreateCheck

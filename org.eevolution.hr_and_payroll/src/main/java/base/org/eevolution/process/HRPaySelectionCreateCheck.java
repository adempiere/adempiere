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
package org.eevolution.process;

import java.util.ArrayList;
import java.util.logging.Level;

import org.eevolution.model.MHRPaySelection;
import org.eevolution.model.MHRPaySelectionCheck;
import org.eevolution.model.MHRPaySelectionLine;
import org.compiere.model.X_C_Order;
import org.compiere.util.AdempiereUserError;
import org.compiere.process.*;
 

/**
 *	Create Checks from Payment Selection Line
 *	
 *  @author ogomezislas
 *  @version $Id: HRPaySelectionCreateCheck.java,v 1.0 2009/11/27 00:51:01 ogomezislas Exp $
 */
public class HRPaySelectionCreateCheck extends SvrProcess
{
	/**	Target Payment Rule			*/
	private String		p_PaymentRule = null;
	/**	Payment Selection			*/
	private int			p_HR_PaySelection_ID = 0;
	/** The checks					*/
	private ArrayList<MHRPaySelectionCheck>	m_list = new ArrayList<MHRPaySelectionCheck>();
	
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
		p_HR_PaySelection_ID = getRecord_ID();
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
		log.info ("HR_PaySelection_ID=" + p_HR_PaySelection_ID
			+ ", PaymentRule=" + p_PaymentRule);
		
		MHRPaySelection psel = new MHRPaySelection (getCtx(), p_HR_PaySelection_ID, get_TrxName());
		if (psel.get_ID() == 0)
			throw new IllegalArgumentException("Not found HR_PaySelection_ID=" + p_HR_PaySelection_ID);
		if (psel.isProcessed())
			throw new IllegalArgumentException("@Processed@");
		//
		MHRPaySelectionLine[] lines = psel.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MHRPaySelectionLine line = lines[i];
			if (!line.isActive() || line.isProcessed())
				continue;
			createCheck (line);
		}
		//
		psel.setProcessed(true);
		psel.save();
		
		return "@C_PaySelectionCheck_ID@ - #" + m_list.size();
	}	//	doIt

	/**
	 * 	Create Check from line
	 *	@param line
	 *	@throws Exception for invalid bank accounts
	 */
	private void createCheck (MHRPaySelectionLine line) throws Exception
	{
		//	Try to find one
		for (MHRPaySelectionCheck check : m_list)
		{
			//	Add to existing
			if (check.getC_BPartner_ID() == line.getHR_Movement().getC_BPartner_ID())
			{
				check.addLine(line);
				if (!check.save())
					throw new IllegalStateException("Cannot save MHRPaySelectionCheck");
				line.setHR_PaySelectionCheck_ID(check.getHR_PaySelectionCheck_ID());
				line.setProcessed(true);
				if (!line.save())
					throw new IllegalStateException("Cannot save MHRPaySelectionLine");
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
		MHRPaySelectionCheck check = new MHRPaySelectionCheck(line, PaymentRule);
		/*
		if (!check.isValid())
		{
			int C_BPartner_ID = check.getC_BPartner_ID();
			MBPartner bp = MBPartner.get(getCtx(), C_BPartner_ID);
			String msg = "@NotFound@ @C_BP_BankAccount@: " + bp.getName();
			throw new AdempiereUserError(msg);
		}
		*/
		if (!check.save())
			throw new IllegalStateException("Cannot save MHRPaySelectionCheck");
		line.setHR_PaySelectionCheck_ID(check.getHR_PaySelectionCheck_ID());
		line.setProcessed(true);
		if (!line.save())
			throw new IllegalStateException("Cannot save MHRPaySelectionLine");
		m_list.add(check);
	}	//	createCheck
	
}	//	HRPaySelectionCreateCheck

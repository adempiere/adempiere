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
package org.eevolution.process;



import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;


/**
 * Invoice Calculate Tax let re calculate Tax Invoice
 * @author Victor Perez
 * @author Teo Sarca, www.arhipac.ro
 */

public class InvoiceCalculateTax extends SvrProcess
{
    private int p_C_Invoice_ID = 0;

    @Override
    protected void prepare() 
    {
    	for (ProcessInfoParameter para : getParameter())
    	{
    		String name = para.getParameterName();
    		if (para.getParameter() == null)
    		{
    			;
    		}
    		else if (name.equals("C_Invoice_ID"))
    		{
    			p_C_Invoice_ID = para.getParameterAsInt();
    		}
    	}

    } //        prepare

    @Override
    protected String doIt() throws Exception
    {
		MInvoice invoice = new MInvoice(getCtx(), p_C_Invoice_ID, get_TrxName());
		invoice.calculateTaxTotal();
		invoice.saveEx();
		//
		// Update balance
		MBPartner bp = new MBPartner (getCtx(), invoice.getC_BPartner_ID(), get_TrxName());
		bp.setTotalOpenBalance();
		bp.setSOCreditStatus();
		bp.saveEx();
		//
        return "@ProcessOK@";
    } //        doIt
}

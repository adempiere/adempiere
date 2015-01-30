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

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

/**
 *	CopyFromBOM Process
 *	Copies BOM Lines from Selected BOM to the Current BOM
 *	The BOM being copied to must have no pre-existing BOM Lines  
 *	
 *  @author Tony Snook 
 *  @version $Id: CopyFromBOM.java,v 1.0 2008/07/04 05:24:03 tspc Exp $
 */
public class CopyFromBOM extends SvrProcess {
	/**					*/
	private int	p_Record_ID = 0;
	private int p_PP_Product_BOM_ID = 0;
	private int no = 0;
	private Properties ctx = Env.getCtx();

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("PP_Product_BOM_ID"))
				p_PP_Product_BOM_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		p_Record_ID = getRecord_ID();
	} //	prepare

	protected String doIt() throws Exception
	{
		log.info("From PP_Product_BOM_ID=" + p_PP_Product_BOM_ID + " to " + p_Record_ID);
		if (p_Record_ID == 0)
			throw new IllegalArgumentException("Target PP_Product_BOM_ID == 0");
		if (p_PP_Product_BOM_ID == 0)
			throw new IllegalArgumentException("Source PP_Product_BOM_ID == 0");
		if (p_Record_ID == p_PP_Product_BOM_ID)
			return "";

		MPPProductBOM fromBom = new MPPProductBOM(ctx, p_PP_Product_BOM_ID, get_TrxName());
		MPPProductBOM toBOM = new MPPProductBOM(ctx, p_Record_ID, get_TrxName());
		if (toBOM.getLines().length > 0)
		{
			throw new AdempiereSystemError("@Error@ Existing BOM Line(s)");
		}

		MPPProductBOMLine[] frombomlines = fromBom.getLines();
		for (MPPProductBOMLine frombomline : frombomlines)
		{
			MPPProductBOMLine tobomline = new MPPProductBOMLine(ctx, 0, get_TrxName());
			MPPProductBOMLine.copyValues(frombomline, tobomline);
			tobomline.setPP_Product_BOM_ID(toBOM.getPP_Product_BOM_ID());
			tobomline.save();
			++no;
		}
		return "OK";
	}
	
	@Override
	protected void postProcess(boolean success)
	{
		this.addLog("@Copied@=" + no);
	}
}

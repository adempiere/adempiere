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
 * Portions created by Carlos Ruiz are Copyright (C) 2005 QSS Ltda.
 * Contributor(s): Carlos Ruiz (globalqss)
 *****************************************************************************/
package org.eevolution.process;

import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

/**
 * Title: Check BOM Structure (free of cycles) Description: Tree cannot contain
 * BOMs which are already referenced
 * 
 * @author Tony Snook (tspc)
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class PP_Product_BOM_Check extends SvrProcess
{

	/** The Record */
	private int p_Record_ID = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_Record_ID = getRecord_ID();
	} // prepare

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("Check BOM Structure");

		// Record ID is M_Product_ID of product to be tested
		MProduct xp = new MProduct(Env.getCtx(), p_Record_ID, get_TrxName());

		if (!xp.isBOM())
		{
			log.info("Product is not a BOM");
			// No BOM - should not happen, but no problem
			return "OK";
		}

		// Check Parent Level
		int lowlevel = MPPProductBOMLine.getLowLevel(getCtx(), p_Record_ID, get_TrxName());
		xp.setLowLevel(lowlevel);
		xp.setIsVerified(true);
		xp.saveEx();

		// Get Default BOM from this product
		MPPProductBOM tbom = MPPProductBOM.getDefault(xp, get_TrxName());
		if (tbom == null) {
			raiseError("No Default BOM found: ", "Check BOM Parent search key");
		}
		
		// Check All BOM Lines
		if (tbom.getM_Product_ID() != 0)
		{
			MPPProductBOMLine[] tbomlines = tbom.getLines();
			for (MPPProductBOMLine tbomline : tbomlines)
			{
				lowlevel = tbomline.getLowLevel(); 
				MProduct p = new MProduct(getCtx(), tbomline.getM_Product_ID(), get_TrxName());
				p.setLowLevel(lowlevel);
				p.setIsVerified(true);
				p.saveEx();
			}
		}
		return "OK";
	} // doIt

	private void raiseError(String string, String hint) throws Exception
	{
		DB.rollback(false, get_TrxName());
		MProduct xp = new MProduct(getCtx(), p_Record_ID, null); // parent
		xp.setIsVerified(false); // set BOM not verified
		xp.saveEx();
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += hint;
		throw new AdempiereUserError(msg);
	}

} //	M_Product_BOM_Check

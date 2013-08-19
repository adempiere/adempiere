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

import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOMLine;

/**
 *	CalculateLowLevel for MRP
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CalculateLowLevel.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class CalculateLowLevel extends SvrProcess
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
	} //	prepare

	protected String doIt() throws Exception
	{
		int count_ok = 0;
		int count_err = 0;
		//
		POResultSet<MProduct> rs = new Query(getCtx(), MProduct.Table_Name, "AD_Client_ID=?", get_TrxName())
									.setParameters(new Object[]{Env.getAD_Client_ID(getCtx())})
									.setOrderBy(MProduct.COLUMNNAME_M_Product_ID)
									.scroll();
		rs.setCloseOnError(true);
		while(rs.hasNext()) {
			MProduct product = rs.next();
			try {
				int lowlevel = MPPProductBOMLine.getLowLevel(getCtx(), product.get_ID(), get_TrxName());
				product.setLowLevel(lowlevel);
				product.saveEx();
				count_ok++;
			}
			catch(Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				count_err++;
			}
		}
		rs.close();

		return "@Ok@ #"+count_ok+" @Error@ #"+count_err;
	}
}

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
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.util.*;
import java.sql.*;
import java.math.*;

import org.compiere.util.*;
import org.compiere.model.*;

/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly. 
 * 	They are set in the process() method. 
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 */
public class MPPOrderBOM extends X_PP_Order_BOM
{
	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrderBOM(Properties ctx, int PP_Order_BOM_ID,String trxName)
	{
		super (ctx,  PP_Order_BOM_ID,trxName);
		//  New
		if ( PP_Order_BOM_ID == 0)
		{
			setProcessing(false);
		}
	}	//	MOrder
        
       

	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderBOM(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MOrder
        
  
        
        
	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg




	/*************************************************************************/

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderBOM[")
			.append(get_ID()).append("-").append(getDocumentNo())
			.append ("]");
		return sb.toString ();
	}	//	toString


	
	/**************************************************************************
	 * 	Get Invoices of Order
	 * 	@return invoices
	 */
	public MPPOrderBOMLine[] getLines()
	{
		return getLines (getPP_Order_BOM_ID());
	}	//	getLines

	/**
	 * 	Get Invoices of Order
	 * 	@param C_Order_ID id
	 * 	@return invoices
	 */
	public  MPPOrderBOMLine[] getLines (int PP_Order_ID)
	{
		ArrayList list = new ArrayList();
                
                QueryDB query = new QueryDB("org.compiere.model.X_PP_Order_BOMLine");
                String filter = "PP_Order_ID = " + PP_Order_ID;
                List results = query.execute(filter);
                Iterator select = results.iterator();
                while (select.hasNext())
                {
                   X_PP_Order_BOMLine bomline = (X_PP_Order_BOMLine) select.next();
                   System.out.println("linea de product bom2 ************ " + bomline.getPP_Order_BOMLine_ID());
                   list.add(new MPPOrderBOMLine(getCtx(), bomline.getPP_Order_BOM_ID(),"PP_Order_BOM_Line"));
                   //list.add(bomline);
                }
                MPPOrderBOMLine[] retValue = new MPPOrderBOMLine[list.size()];
                list.toArray(retValue);
                return retValue; 
                
                
             
	}	//	getLines



}	//	MOrder

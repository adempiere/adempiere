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

import java.util.logging.Level;

import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;


/**
 *	Update Quantity Count with final count plus picked quantity 
 *	
 *  @author Jorg Janke
 *  @version $Id: InventoryCountCreate.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  @author Tony Snook - Physical Inventory Stocktake Enhancement
 */
public class UpdateQuantityCount extends SvrProcess
{
	
	/** Physical Inventory Parameter		*/
	private int			p_M_Inventory_ID = 0;
	/** Physical Inventory					*/
	private MInventory 	m_inventory = null;
	
	/** Inventory Line				*/
	private MInventoryLine	m_line = null; 

	
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
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_M_Inventory_ID = getRecord_ID();
	}	//	prepare

	
	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("M_Inventory_ID=" + p_M_Inventory_ID);
		
		m_inventory = new MInventory (getCtx(), p_M_Inventory_ID, get_TrxName());
		if (m_inventory.get_ID() == 0)
			throw new AdempiereSystemError ("Not found: M_Inventory_ID=" + p_M_Inventory_ID);
		if (m_inventory.isProcessed())
			throw new AdempiereSystemError ("@M_Inventory_ID@ @Processed@");
		
		//	Update Quantity Count
		String sql1 = "UPDATE M_InventoryLine l "
			+ "SET QtyCount=COALESCE(SecondCountQty,FirstCountQty) + PickedQty "
			+ "WHERE M_Inventory_ID=" + p_M_Inventory_ID;
		int no = DB.executeUpdate(sql1, get_TrxName());
		log.info("Update Quantity Count #" + no);

		//
		return "@M_Inventory_ID@ - #" + no;
	}	//	doIt
	
	
	
}	//	InventoryCountCreate

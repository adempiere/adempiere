/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.eevolution.exceptions;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

/**
 * Throwed when no Plant was found for given Warehouse
 * @author Teo Sarca, www.arhipac.ro
 */
public class NoPlantForWarehouseException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4986043215550031772L;

	public NoPlantForWarehouseException(int M_Warehouse_ID)
	{
		//TODO: vpj-cd create the msg for error
		super ("@NoPlantForWarehouseException@"
				+" @M_Warehouse_ID@ : "+MWarehouse.get(Env.getCtx(), M_Warehouse_ID).getName()
		); 
	}
}

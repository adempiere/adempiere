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
package test.functional.mrp;

import java.math.BigDecimal;

import org.compiere.model.MProduct;

/**
 * MRP Notice Value Object 
 * @author Teo Sarca, www.arhipac.ro
 */
public class MRPNotice
{
	public String code;
	public int AD_Org_ID;
	public int PP_MRP_ID;
	public MProduct product;
	public String documentNo;
	public BigDecimal qty;
	public String comment;
	
	public MRPNotice(String code)
	{
		this.code = code;
	}
	
	public String toString()
	{
		return this.code + "["
			+"AD_Org_ID="+this.AD_Org_ID
			+", PP_MRP_ID="+this.PP_MRP_ID
			+", Product="+(this.product != null ? this.product.getValue() : "null")
			+", DocumentNo="+this.documentNo
			+", Qty="+this.qty
			+", Comment="+this.comment
			+"]";
	}
}

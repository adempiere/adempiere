/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 08-Aug-2005 by alok
 *
 */
package org.posterita.beans;

import java.sql.Timestamp;


public class InOutHistoryBean extends FilterBean
{
	
	public String getPartnerName()
	{
		return partnerName;
	}
	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}
	
	public Integer getInOutId()
	{
		return inOutId;
	}
	
	public void setInOutId(Integer inOutId)
	{
		this.inOutId = inOutId;
	}
	
	public Timestamp getDateMovement()
	{
		return dateMovement;
	}
	public void setDateMovement(Timestamp dateMovement)
	{
		this.dateMovement = dateMovement;
	}
	public Timestamp getDateOrdered()
	{
		return dateOrdered;
	}
	public void setDateOrdered(Timestamp dateOrdered)
	{
		this.dateOrdered = dateOrdered;
	}	
	public Integer getInvoiceId()
	{
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	public Boolean getIsSotrx()
	{
		return isSoTrx;
	}
	public void setIsSotrx(Boolean isSotrx)
	{
		this.isSoTrx = isSotrx;
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	public String getPoReference()
	{
		return poReference;
	}
	public void setPoReference(String poReference)
	{
		this.poReference = poReference;
	}
	public Integer getRefInOutId()
	{
		return refInOutId;
	}
	public void setRefInOutId(Integer refInOutId)
	{
		this.refInOutId = refInOutId;
	}
	public Integer getWarehouseId()
	{
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId)
	{
		this.warehouseId = warehouseId;
	}
}

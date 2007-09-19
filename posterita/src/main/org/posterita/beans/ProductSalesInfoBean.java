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
 * 10-Oct-2006 12:34:54 by praveen
 *
 */

package org.posterita.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ProductSalesInfoBean {
	private String DocumentNo;
	private Date dateOrdered;
	private String bpartnerName;
	private String orderType;
	private BigDecimal qtyOrderded;
	private Integer orderId;
	private BigDecimal lineAmount;
	
	public String getBpartnerName() { 
		return bpartnerName;
	}
	public void setBpartnerName(String bpartnerName) {
		this.bpartnerName = bpartnerName;
	}
	public Date getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public String getDocumentNo() {
		return DocumentNo;
	}
	public void setDocumentNo(String documentNo) {
		DocumentNo = documentNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getQtyOrderded() {
		return qtyOrderded;
	}
	public void setQtyOrderded(BigDecimal qtyOrderded) {
		this.qtyOrderded = qtyOrderded;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getLineAmount() {
		return lineAmount;
	}
	public void setLineAmount(BigDecimal lineAmount) {
		this.lineAmount = lineAmount;
	}
	
	

}

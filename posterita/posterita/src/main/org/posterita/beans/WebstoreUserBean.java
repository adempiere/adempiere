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
 *  
 **/
package org.posterita.beans;

import java.math.BigDecimal;

public class WebstoreUserBean
{

	protected UserBean userBean;
	protected AddressBean shipToAddrBean;
	protected CreditCardBean creditCardBean;
	protected Integer chargeId; // not in udibean yet
	protected String tenderType; //i.e. Payment Method: by card, cash (not in udibean yet)
	protected String deliveryViaRule; // MInOut.DELIVERYVIARULE_Shipper etc
	protected String chargeName;
	protected BigDecimal chargeAmount;
	/*
	 * Will be used only in session.
	 * Step 1: Delivery Information
	 * Step 2: Payment Information
	 * Step 3: Order Confirmation
	 * Step 4: Finished 
	 */
	protected String step;

	
	
	public String getStep()
	{
		return step;
	}
	public void setStep(String step)
	{
		this.step = step;
	}

	public CreditCardBean getCreditCardBean()
	{
		return creditCardBean;
	}
	public void setCreditCardBean(CreditCardBean creditCardBean)
	{
		this.creditCardBean = creditCardBean;
	}
	public AddressBean getShipToAddrBean()
	{
		return shipToAddrBean;
	}
	public void setShipToAddrBean(AddressBean shipToAddrBean)
	{
		this.shipToAddrBean = shipToAddrBean;
	}
	public UserBean getUserBean()
	{
		return userBean;
	}
	public void setUserBean(UserBean userBean)
	{
		this.userBean = userBean;
	}

	public String getTenderType()
	{
		return tenderType;
	}
	public void setTenderType(String tenderType)
	{
		this.tenderType = tenderType;
	}
	public Integer getChargeId()
	{
		return chargeId;
	}
	public void setChargeId(Integer chargeId)
	{
		this.chargeId = chargeId;
	}
	public String getChargeName()
	{
		return chargeName;
	}
	public void setChargeName(String chargeName)
	{
		this.chargeName = chargeName;
	}
	public BigDecimal getChargeAmount()
	{
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount)
	{
		this.chargeAmount = chargeAmount;
	}
	public String getDeliveryViaRule()
	{
		return deliveryViaRule;
	}
	public void setDeliveryViaRule(String deliveryViaRule)
	{
		this.deliveryViaRule = deliveryViaRule;
	}


	
	
	

}

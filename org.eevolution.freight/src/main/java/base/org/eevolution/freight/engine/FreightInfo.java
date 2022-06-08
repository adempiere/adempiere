/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public License as    *
 * published                                                                  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2020 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.eevolution.freight.engine;

import java.math.BigDecimal;

import org.compiere.util.Env;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Add support to freight information from engine
 */
public class FreightInfo {
    /**	Freight Reference	*/
	private int freightId;
	/**	Freight Rate applied	*/
	private BigDecimal freightRate;
	/**	Freight Amount Calculated	*/
	private BigDecimal freightAmount;
	
	/**
	 * Default constructor with a zero instance for values
	 */
	public FreightInfo() {
		freightRate = Env.ZERO;
		freightAmount = Env.ZERO;
	}
	
	/**
	 * @return the freightId
	 */
	public final int getFreightId() {
		return freightId;
	}
	
	/**
	 * @param freightId the freightId to set
	 */
	public final FreightInfo withFreightId(int freightId) {
		this.freightId = freightId;
		return this;
	}
	
	/**
	 * @return the freightRate
	 */
	public final BigDecimal getFreightRate() {
		return freightRate;
	}
	
	/**
	 * @param freightRate the freightRate to set
	 */
	public final FreightInfo withFreightRate(BigDecimal freightRate) {
		this.freightRate = freightRate;
		return this;
	}
	
	/**
	 * @return the freightAmount
	 */
	public final BigDecimal getFreightAmount() {
		return freightAmount;
	}
	
	/**
	 * @param freightAmount the freightAmount to set
	 */
	public final FreightInfo withFreightAmount(BigDecimal freightAmount) {
		this.freightAmount = freightAmount;
		return this;
	}
	
	@Override
	public String toString() {
		return "FreightInfo [freightId=" + freightId + ", freightRate=" + freightRate + ", freightAmount="
				+ freightAmount + "]";
	}
}

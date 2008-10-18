/**
 * 
 * Copyright (c) 2008 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. TAMAK ICT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * May 21, 2008 1:11:53 PM by praveen
 *
 */

package org.posterita.beans;

import java.math.BigDecimal;

import org.compiere.util.Env;

public class PaymentDetailsBean 
{
	private BigDecimal cashAmt = Env.ZERO; 
	private BigDecimal cardAmt = Env.ZERO;
	private BigDecimal chequeAmt = Env.ZERO;
	private BigDecimal writeOffAmt = Env.ZERO;
	private BigDecimal discountAmt = Env.ZERO;
	private BigDecimal tenderedAmt = Env.ZERO;
	private BigDecimal refundedAmt = Env.ZERO;
	private BigDecimal payAmt = Env.ZERO;
	
	public BigDecimal getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}
	public BigDecimal getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}
	public BigDecimal getChequeAmt() {
		return chequeAmt;
	}
	public void setChequeAmt(BigDecimal chequeAmt) {
		this.chequeAmt = chequeAmt;
	}
	public BigDecimal getWriteOffAmt() {
		return writeOffAmt;
	}
	public void setWriteOffAmt(BigDecimal writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}
	public BigDecimal getTenderedAmt() {
		return tenderedAmt;
	}
	public void setTenderedAmt(BigDecimal tenderedAmt) {
		this.tenderedAmt = tenderedAmt;
	}
	public BigDecimal getRefundedAmt() {
		return refundedAmt;
	}
	public void setRefundedAmt(BigDecimal refundedAmt) {
		this.refundedAmt = refundedAmt;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}	
}

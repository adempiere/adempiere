package org.adempiere.process;

/**
 * Class that contains information about a promised date update message
 * 
 * @author Daniel Tamm
 *
 */
public class DatePromisedMsg {

	public String			bPartnerNo;
	public String			bPartnerArticleNo;
	public java.util.Date	promisedDate;
	public java.util.Date	msgTime;
	public String			promisedDatePrecision;
	public double			deliveryCount;
	public double			estimatedPriceEach;
	public String			currencyISO;
	
	
	public String getbPartnerNo() {
		return bPartnerNo;
	}
	public void setbPartnerNo(String bPartnerNo) {
		this.bPartnerNo = bPartnerNo;
	}
	public String getbPartnerArticleNo() {
		return bPartnerArticleNo;
	}
	public void setbPartnerArticleNo(String bPartnerArticleNo) {
		this.bPartnerArticleNo = bPartnerArticleNo;
	}
	public java.util.Date getPromisedDate() {
		return promisedDate;
	}
	public void setPromisedDate(java.util.Date promisedDate) {
		this.promisedDate = promisedDate;
	}
	public java.util.Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(java.util.Date msgTime) {
		this.msgTime = msgTime;
	}
	public String getPromisedDatePrecision() {
		return promisedDatePrecision;
	}
	public void setPromisedDatePrecision(String promisedDatePrecision) {
		this.promisedDatePrecision = promisedDatePrecision;
	}
	public double getDeliveryCount() {
		return deliveryCount;
	}
	public void setDeliveryCount(double deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
	public double getEstimatedPriceEach() {
		return estimatedPriceEach;
	}
	public void setEstimatedPriceEach(double estimatedPriceEach) {
		this.estimatedPriceEach = estimatedPriceEach;
	}
	public String getCurrencyISO() {
		return currencyISO;
	}
	public void setCurrencyISO(String currencyISO) {
		this.currencyISO = currencyISO;
	}
	
}

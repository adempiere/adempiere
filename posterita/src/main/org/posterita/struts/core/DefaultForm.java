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
 */
package org.posterita.struts.core;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MInOut;

import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.PaymentBean;
import org.posterita.beans.ProductImageBean;
import org.posterita.beans.UDIBean;


public class DefaultForm extends BaseForm
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
     

	protected String aaCardnumber;
	protected String blackListedId;
	protected String blackListedBankName;
	protected String blackListedChequeNo;
    
    protected String forward;
    protected String accountNo;
    protected String accountType;
    protected String accountName;
    protected String address1;
    protected String address2;
    protected String agreement;
    protected String aisle;
    protected String allocationId;
    protected String allocationStatus;
    protected String attribute;
    protected String attributeId;
    protected String attributeSetId;
    protected String attributeSetInstanceId;
    protected Integer [] sernoAttributeSetInstanceIds;
    protected String attributeValue;
    protected String attributeValueId;
    protected String bankId;
    protected String bankName;
    protected String bin;
    protected String bpartnerId;   
 
    protected String customSize;
    protected String documentId;
    protected String documentNo;
    protected String documentType;    
    protected String email;
    protected String endDay;
    protected String endHour;
    protected String endMinute;
    protected String endMonth;
    protected String endYear;	
    protected String engineNo;
    protected String engineNumber;
    protected String fax;
    protected FormFile file;
    protected FormFile image;
    protected String day;
    protected String fromDate;
    protected String fromOrderLineId;
    protected String grandTotal;
    protected String id;
    protected String invoiceId;
    protected String isAccessAllOrgs= "false";
    protected String isActive="false";
    protected String isApproved;
    protected String isAvailableForBackOrder="true";
    protected String isAutomobile;
    protected String isCustomer="true";
    protected String isDefault = "true";
    protected String isDelivered;
    protected String isEmployee="true";
    protected String isInvoiced;
    protected String isMotorcycle;
    protected String isNatisReleased;   
    protected String isPublic="false";
    protected String isQtyReserved;
    
    protected Integer [] checkBox;
    protected String checkNo;
    protected String city;
    protected String colour;
    protected String compiereDocStatus;
    protected String confirmEmail;
    protected String confirmPassword;
    protected String counterOrderStatusInfo;
    protected String counterOrgID;
    protected String counterOrgName;
    protected String currentBalance;
    protected String custIdNumber;
    protected String dateCreated;
    protected String dateOrdered;
    protected String datePromised;
    protected String dateTrx;
    protected String description;
    protected String docStatus;
    protected String docStatusCode;
    
    
    protected String isReceipt;
    protected String isRetailer ="true";
    protected String isSalesRep="false";
    protected String isSotrx;
    protected String isVendor="true";
    protected String isVisible;
    protected String isWholesaler="true";
    protected String level;
    protected ArrayList list;
    protected String locationId;
    protected String locatorId;
    protected FormFile logo;
    protected String logoName;
    protected String maintenanceContractNumber;
    protected String make;
    protected String makeAttributeValueId;
    protected String menuId;
    protected String menuItemName;
    protected String menuLink;
    protected String message;
    protected String messageID;
    protected String mobile;
    protected String model;
    protected String modelGroup;
    protected String month;	
    protected String movementDate;
    protected String movementDescription;
    protected String name;
    protected String natisApplicationDate;
    protected String natisControlNumber;
    protected String natisReleaseId;
    protected String newEmail;
    protected String newPassword;
    protected String oldPassword;
    
    protected PaymentBean paymentBeanIndex;
    protected String orderId;
    protected ArrayList orderLineBean;    
    protected String orderLineId;
    protected OrderLineBean orderLineIndexed;
    protected ArrayList<OrderLineBean> orderLineList = new ArrayList<OrderLineBean>();  
    protected String orderStatusInfo;    
    protected String orderType; 
    protected String orgId;
    protected String allocatedAmount;
    protected String OrgID;
    protected String orgName;
    protected String orgType;
    protected String orgUser;
    protected String parentMenuId;
    protected String partnerId;    
    protected String partnerName="";
    protected String password;
    protected String payAmt;
    protected String paymentId;
    protected String phone;
    protected String phone2; 
    protected String poReference;
    protected String POReference;   
    protected String postalAddress;
    protected String postalAddress1;
    protected String postalCity;
    protected String postalCode;
    protected String postalPostalCode;
    protected String prefOrderLineId;
    protected String price;
    protected String priceEntered;
    protected String priceList;
    protected String priceListVersionId;
    
    protected AttributeValuesPair productAttributes;
    protected String productAttributeSetInstanceId;
    protected String productCategoryId;
    protected String productId;
    
    protected String productName;
    protected String qty;
    protected String qtyReserved;
    protected String [] qtyTargeted;
    protected String quantity;
    protected String read;
    protected Integer refToQuote;
    protected String region;
    protected String regionId;
    protected String registerNumber;
    protected String reserveStatus;
    protected String roleId;
    protected String roleName;
    protected String routingNumber;
    protected String sernoAttributeSetInstance;
    protected String sotrxFlag;
    protected String startDay;
    protected String startHour;
    protected String startMinute;
    protected String startMonth;
    protected String startYear;
    protected String stdPurchasePrice;
    protected String stdSalesPrice;	
    protected String subject;
    protected String submit;
    
    protected String surname;
    protected Integer[] targetId;
    protected String termsAgreement;	
    protected String toDate;    
    protected String toOrderLineId;
    protected String totalLines;
    protected String transmission;
    protected String transmissionAttributeValueId;
    protected String userId;    
    protected String username;
    protected String value;
    protected String vinNumber;
    protected String warehouseId;
    protected String warehouseName;
    protected String warehouseType;
    protected String year;
    protected String yearAttributeValueId;
    protected String first;
    protected String licensingDistrictId;
    protected String licensingDistrict;    
    protected String tradeInOrderlineId;
    protected String passportNo;  
    protected String companyRegNo;
    protected ArrayList showroomTrafficBean;
   
    protected String sales;
    protected String traffic;
    protected String prospects;
    
    protected String modelId;    
    protected String trxId;
    protected String trx;
    protected String modelCode;
    protected String modelCodeId; 
    protected String dealerCode;
    protected String dealerCodeId;
    protected String name2;
    protected String isAllocationWarehouse="false";
    protected String canAlterOrder;
    protected String canCreateOrder;
    protected String canViewOrder;
    protected String salesRepId;     
    
    protected String imagePath;
    protected String imageTitle;
    protected String altText;   
    protected String dateFrom;
    protected String dateTo;
    
    protected String weekNo;
    protected String startDate;
    protected String endDate;
    protected String financeOption;
    protected String status;
    
    protected String newVinNumber;
    protected String countryOfImpCode;
    protected String enginePower;
    protected String mainColourCode;
    protected String natisModelNo;
    protected String noOfWheels;
    protected String tare;
    protected String unitNumber;
    protected String isTradeIn = "true";
    
    protected String attributesetinstanceIdFrom;
    protected String attributesetinstanceIdTo;
    protected String releasedBy;
    protected Integer productIds[];
    protected String ETA;
    protected String ETAFilter;
    
    protected String engineCapacity;
    protected String colourCodeHES;
    protected String colourName;
    protected String natisModelDescription;
    protected Integer orderIds[];
    
    protected String colourCodeMatchId;
    protected String modelMatchID;
    protected String natisModelDesc;
    protected String modelDesc;
    protected String trans;
    protected String showActiveOnly = "true";
    protected String updateAttrValueId;
    protected String active;
    protected String visible;
    protected String beginingBalance;
    protected String statementDifference;
    protected String endingBalance;
    protected String amount;
    protected String trxType;
    protected String amountGiven;
    protected String amountRefunded;
    protected Integer cashBookId;
    protected String cashId;
    protected String cashLineId;
    protected String cashBookName;
    protected String chequeNo;
    protected String creditCardType;
    protected String creditCardNumber;
    protected String creditCardNumberEncrypted;
    protected String creditCardExpMonth;
    protected String creditCardExpMonthName;    
    protected String creditCardExpYear;
    protected Integer orderLineIds[];
    protected Integer [] posOrderLineIds;
    protected Integer [] bpartnerIds;
    protected Integer [] invoiceIds;
    
    private String qtySmall;
    private String qtyMedium;
    private String qtyLarge;
    private String qtyXLarge;
    private String clientName;
    protected String transferAmount;
    protected String cashBookIds;
    protected String designAttributeValueId;
    protected String modelAttributeValueId;
    protected String sizeAttributeValueId;
    protected String colourAttributeValueId;
    protected String shoppingCartItemId;
    protected String keyword;
    protected String searchText;
    protected String backOrder;
    
    protected String fieldName;
    protected String applicationName;
    protected String storeId;
    protected String isWebstoreFeatured="false";
    protected String isNotWebstoreFeatured;
    protected String imageName;
    
    protected String posId;
    protected String purchasePriceList;
    protected String purchasePriceLimit;
    protected String purchasePriceStandard;
    protected String salesPriceList;
    protected String salesPriceLimit;
    protected String salesPriceStandard;
    protected String barCode; 
    protected String trackingNo;
    protected String reportType;
    protected String noOfProductsRequired;
    protected String qtyAndItem;
    protected String [] discountPercent;
    protected String [] actualPrice;
    protected String userSurname;
    protected String countryId;
    protected String countryName;
    
    protected String newName;
    protected ArrayList <ProductImageBean>productsList = new ArrayList<ProductImageBean>();
    protected ProductImageBean productsIndexed;
    protected String paymentByCash;
    protected String paymentByCard;
    protected String paymentByChq;
    protected String isSales;
    protected String ifAdd="true";
    
    protected String brandName;
    protected String modelName;
    protected String designName;
    protected String sizeName;
    protected String productCategory;
    protected String productClassification;
    protected String [] sizes;
    protected String revenueRecognition;
    protected String cvv;
	protected String creditCardPayment;
	protected String shipmentMethod = MInOut.DELIVERYVIARULE_Pickup;
	protected String shipTo;
	protected String billTo;
	protected String shipperId;
	protected String paymentMethod;

    protected String revenueRecoId;
    protected String isTextileProduct;
    protected String qtyFilter;
    protected ArrayList attributeValueList;
    protected String [] attributeValueIds;
    protected String attributeSetName;
    protected String attributeName;
        
    
    protected String requestId;
	protected String fromAddress;
	protected String toAddresses;
	protected String ccAddresses;
	protected String bccAddresses;
	protected String textMessage;
	protected String toUserId;
	protected String priority;
	protected String title;
	protected String chargeId;
	protected String statusId;
    protected String totalActualPrice;
    protected String productType="I";
    protected String qtyNumberFilter;
    protected String taxCategoryId;
    protected String taxCategoryName;
    protected String endingBalanceAsString;
    protected String beginingBalanceAsString;
    protected String vendorName;
    protected String timePeriod;
    protected String chartType;
    protected String salesGroup;
    protected String taxRate;
    protected String state;
    protected String accountId;
    protected String dateRange;
    protected String reportName;
    protected String orderGrandTotal;
    protected String transferAllAmount;
    protected String adjustmentAmount;
 
    protected String differenceAmt;
    protected String tillGrandTotal;
    protected String cashTotal;
    protected String cardTotal;
    protected String chequeTotal;
    protected String creatingFromOrder;
    protected String transferType;
    protected String priceQtyFilter;    
    protected String deliveryViaRule;
    protected String userPIN;
    protected String paymentRule;
    protected String cardDifference;
    protected String chequeDifference;
    
  
    protected String isSelfService;  
    protected String documentNote;
    protected String currencySymbole;
    protected String userDiscount;
    
   
    
    
    protected String invoiceDate;
    protected String netDays;
    protected String daysDue;
    protected String dueDate;
    protected String discountDate;
    protected String discountAmt;
    protected String invoiceGrandTotal;
    protected String paidAmt;
    protected String openAmt;
    protected String currencyId;
    protected String paymentTermId;
    protected String invoiceScheduleId;
    
    protected String invoicedAmt;
    protected String pastDue91_plus;
    protected String pastDue61_90;
    protected String pastDue31_60;
    protected String pastDue1_30;
    protected String pastDue8_30;
    protected String pastDue1_7;
    protected String pastDueAmt;
    protected String dueAmt;
    protected String due0;
    protected String due1_7;
    protected String due8_30;
    protected String due0_30;
    protected String due31_60;
    protected String due61_90;
    protected String due91_PLUS;
    
   
    protected String paymentAmt;
    protected String overUnderPayment;
    protected String writeOffAmt;

    protected String invoiceNo;
    protected String paymentTermName;
    
    protected String dunningLevelId;
    protected String dunningLevelName;
    protected String dunningPrintText;
    protected String dunningPrintNote;
    protected String BP_Group_ID ;
    protected String dunningId;
    protected String dunningName;
    protected String  creditLimit;
    protected String creditStatus;
    protected String creditUsed;
    public String docBasisType;
    protected String amtMultiplier; 
    protected String frequencyType;
    protected String commissionAmtId;
    protected String subtractAmt;
    
    protected String taxName;
    protected String taxId;
    protected String isTaxExempted;

    protected Integer [] paymentIds;
    
    
    protected String inventoryLineId;
    protected String inventoryId;
    protected String qtyBook;
    protected String qtyCount;
    
    protected String afterDelivery;
    protected String fixedDueDate;
    protected String nextBusinessday;
    protected String fixedMonthDay;
    protected String fixedMonthOffset;
    protected String fiedMonthCutoff;
    protected String toBeShipped;
    protected String discountDay1;
    protected String discountDay2;
    protected String discountAmt1;
    protected String discountAmt2;
    protected String [] allocateAmount;
    protected String group1;
    protected String group2;
    protected Integer [] inventoryIds;
    
    
    protected String isFullAccess;
    protected String historyType;
    
    protected String birthdate;
    protected String openDrawer="true";
    
    public String getHistoryType()
	{
		return historyType;
	}


	public void setHistoryType(String historyType)
	{
		this.historyType = historyType;
	}

    public Integer[] getPaymentIds() {
        return paymentIds;
    }


    public void setPaymentIds(Integer[] paymentIds) {
        this.paymentIds = paymentIds;
    }


    public DefaultForm()
    {
        setBean(new UDIBean());
    }
    
    
    public String getOrderGrandTotal() {
        return orderGrandTotal;
    }
    public void setOrderGrandTotal(String orderGrandTotal) {
        this.orderGrandTotal = orderGrandTotal;
    }
    public String getSalesGroup() {
		return salesGroup;
	}
	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}    
    
    public String getBeginingBalanceAsString() {
        return beginingBalanceAsString;
    }
    public void setBeginingBalanceAsString(String beginingBalanceAsString) {
        this.beginingBalanceAsString = beginingBalanceAsString;
    }
    public String getEndingBalanceAsString() {
        return endingBalanceAsString;
    }
    public void setEndingBalanceAsString(String endingBalanceAsString) {
        this.endingBalanceAsString = endingBalanceAsString;
    }
    public String getAttributeName() {
        return attributeName;
    }
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    public String getAttributeSetName() {
        return attributeSetName;
    }
    public void setAttributeSetName(String attributeSetName) {
        this.attributeSetName = attributeSetName;
    }
   

    
    
    
    public String getBillTo()
	{
		return billTo;
	}




	public void setBillTo(String billTo)
	{
		this.billTo = billTo;
	}




	public String getShipmentMethod()
	{
		return shipmentMethod;
	}




	public void setShipmentMethod(String shipmentMethod)
	{
		this.shipmentMethod = shipmentMethod;
	}




	public String getShipTo()
	{
		return shipTo;
	}




	public void setShipTo(String shipTo)
	{
		this.shipTo = shipTo;
	}




	public String[] getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(String[] actualPrice) {
        this.actualPrice = actualPrice;
    }
    
    public String[] getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(String[] discountPercent) {
        this.discountPercent = discountPercent;
    }
    public String getQtyAndItem() {
        return qtyAndItem;
    }
    public void setQtyAndItem(String qtyAndItem) {
        this.qtyAndItem = qtyAndItem;
    }
    public String getIsTradeIn()
    {
        return isTradeIn;
    }
    
    public void setIsTradeIn(String isTradeIn)
    {
        this.isTradeIn = isTradeIn;
    }
    
    public String getLicensingDistrictId() {
        return licensingDistrictId;
    }
    public void setLicensingDistrictId(String licensingDistrictId) {
        this.licensingDistrictId = licensingDistrictId;
    }
    public String getIsAvailableForBackOrder() {
        return isAvailableForBackOrder;
    }
    public void setIsAvailableForBackOrder(String isAvailableForBackOrder) {
        this.isAvailableForBackOrder = isAvailableForBackOrder;
    }
    public String getAaCardnumber() {
        return aaCardnumber;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getAddress1() {
        return address1;
    }
    public String getAddress2() {
        return address2;
    }
    
    public String getAgreement() 
    {
        return agreement;
    }
    public String getAisle() {
        return aisle;
    }
    
    
    public String getAllocationId()
    {
        return allocationId;
    }
    
    
    
    public String getAllocationStatus()
    {
        return allocationStatus;
    }
    public String getAttribute() {
        return attribute;
    }
    public String getAttributeId() {
        return attributeId;
    }
    public String getAttributeSetId() {
        return attributeSetId;
    }
    public String getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    public String getAttributeValue() {
        return attributeValue;
    }
    public String getBankId() {
        return bankId;
    }
    public String getBankName() {
        return bankName;
    }
    public String getBin() {
        return bin;
    }
    public String getBpartnerId() {
        return bpartnerId;
    }
    
    public Integer[] getCheckBox()
    {
        return checkBox;
    }
    
    public String getCheckNo() {
        return checkNo;
    }
    public String getCity() {
        return city;
    }
    public String getColour() {
        return colour;
    }
    public String getColourAttributeValueId() {
        return colourAttributeValueId;
    }
    
    public String getCompiereDocStatus()
    {
        return compiereDocStatus;
    }
    
    public String getConfirmEmail() {
        return confirmEmail;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String getCounterOrderStatusInfo() {
        return counterOrderStatusInfo;
    }
    public String getCounterOrgID() {
        return counterOrgID;
    }
    public String getCounterOrgName() {
        return counterOrgName;
    }
    public String getCurrentBalance() {
        return currentBalance;
    }
    public String getCustIdNumber() {
        return custIdNumber;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public String getDateOrdered() {
        return dateOrdered;
    }
    public String getDatePromised() {
        return datePromised;
    }
    public String getDateTrx() {
        return dateTrx;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDocStatus() {
        return docStatus;
    }
    public String getDocStatusCode() {
        return docStatusCode;
    }
    public String getDocumentId() {
        return documentId;
    }
    public String getDocumentNo() {
        return documentNo;
    }
    public String getDocumentType() {
        return documentType;
    }
    public String getEmail() {
        return email;
    }
    
    public String getEndDay() {
        return endDay;
    }
    public String getEndHour() {
        return endHour;
    }
    public String getEndMinute() {
        return endMinute;
    }
    public String getEndMonth() {
        return endMonth;
    }
    public String getEndYear() {
        return endYear;
    }
    
    
    public String getEngineNo() {
        return engineNo;
    }
    
    
    public String getEngineNumber()
    {
        return engineNumber;
    }
    public FormFile getFile() 
    {
        return file;
    }
    
    public String getFromDate() {
        return fromDate;
    }
    
    public String getFromOrderLineId()
    {
        return fromOrderLineId;
    }
    
    public String getGrandTotal() {
        return grandTotal;
    }
    public String getId() {
        return id;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    public String getIsAccessAllOrgs() {
        return isAccessAllOrgs;
    }
    public String getIsActive() {
        return isActive;
    }
    public String getIsApproved() {
        return isApproved;
    }
    public String getIsAutomobile() {
        return isAutomobile;
    }
    public String getIsCustomer() {
        return isCustomer;
    }
    public String getIsDefault() {
        return isDefault;
    }
    public String getIsDelivered() {
        return isDelivered;
    }
    public String getIsEmployee() {
        return isEmployee;
    }
    public String getIsInvoiced() {
        return isInvoiced;
    }
    public String getIsMotorcycle() {
        return isMotorcycle;
    }
    public String getIsNatisReleased() {
        return isNatisReleased;
    }
    public String getIsPublic() {
        return isPublic;
    }
    
    
    public String getIsQtyReserved() {
        return isQtyReserved;
    }
    
    public String getIsReceipt() {
        return isReceipt;
    }
    public String getIsRetailer() {
        return isRetailer;
    }
    public String getIsSalesRep() {
        return isSalesRep;
    }
    public String getIsSotrx() {
        return isSotrx;
    }
    public String getIsVendor() {
        return isVendor;
    }
    public String getIsWholesaler() {
        return isWholesaler;
    }
    public String getLevel() {
        return level;
    }
    public ArrayList getList()
    {
        return list;
    }
    
    public String getLocationId() {
        return locationId;
    }
    
    
    public String getLocatorId()
    {
        return locatorId;
    }
    
    public FormFile getLogo()
    {
        return logo;
    }
    public String getLogoName()
    {
        return logoName;
    }
    public String getMaintenanceContractNumber() {
        return maintenanceContractNumber;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public String getMakeAttributeValueId()
    {
        return makeAttributeValueId;
    }
    
    public String getMenuId() {
        return menuId;
    }
    public String getMenuItemName() {
        return menuItemName;
    }
    public String getMenuLink() {
        return menuLink;
    }
    public String getMessage() {
        return message;
    }
    public String getMessageID() {
        return messageID;
    }
    public String getMobile() {
        return mobile;
    }
    public String getModel() {
        return model;
    }
    public String getModelAttributeValueId() {
        return modelAttributeValueId;
    }
    
    public String getModelGroup() {
        return modelGroup;
    }
    public String getMonth() {
        return month;
    }
    
    public String getMovementDate()
    {
        return movementDate;
    }
    
    public String getMovementDescription()
    {
        return movementDescription;
    }
    public String getName() {
        return name;
    }
    public String getNatisApplicationDate()
    {
        return natisApplicationDate;
    }
    public String getNatisControlNumber()
    {
        return natisControlNumber;
    }
    public String getNatisReleaseId()
    {
        return natisReleaseId;
    }
    public String getNewEmail() {
        return newEmail;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getOrderId() {
        return orderId;
    }
    public ArrayList getOrderLineBean()
    {
        return orderLineBean;
    }
    
    public String getOrderLineId()
    {
        return orderLineId;
    }
    public OrderLineBean getOrderLineIndexed()
    {
        return orderLineIndexed;
    }
    
    public OrderLineBean getOrderLineIndexed(int index)
    {
        while (index >= orderLineList.size())
            orderLineList.add(new OrderLineBean());
        return (OrderLineBean) orderLineList.get(index);
    }
    
    public ArrayList getOrderLineList()
    {
        return orderLineList;
    }
    
    public String getOrderStatusInfo() {
        return orderStatusInfo;
    }
    
    
    public String getOrderType()
    {
        return orderType;
    }
    public String getOrgId()
    {
        return orgId;
    }
    public String getOrgID() {
        return OrgID;
    }
    public String getOrgName() {
        return orgName;
    }
    public String getOrgType() {
        return orgType;
    }
    public String getOrgUser() {
        return orgUser;
    }
    public String getParentMenuId() {
        return parentMenuId;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public String getPassword() {
        return password;
    }
    public String getPayAmt() {
        return payAmt;
    }
    public String getPaymentId() {
        return paymentId;
    }
    
    public String getPhone1() {
        return phone;
    }
    public String getPhoneNo() {
        return phone2;
    }
    public String getPoReference() {
        return poReference;
    }
    public String getPOReference()
    {
        return POReference;
    }
    
    public String getPostalAddress() {
        return postalAddress;
    }
    public String getPostalAddress1() {
        return postalAddress1;
    }
    public String getPostalCity() {
        return postalCity;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getPostalPostalCode() {
        return postalPostalCode;
    }
    public String getPrefOrderLineId()
    {
        return prefOrderLineId;
    }
    
    public String getPriceEntered()
    {
        return priceEntered;
    }
    
    public String getPriceListVersionId() {
        return priceListVersionId;
    }
    public AttributeValuesPair getProductAttributes()
    {
        return productAttributes;
    }
    
    public String getProductAttributeSetInstanceId()
    {
        return productAttributeSetInstanceId;
    }
    
    public String getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public String getProductId()
    {
        return productId;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public String getQty() {
        return qty;
    }
    public String getQtyReserved()
    {
        return qtyReserved;
    }
    
    public String[] getQtyTargeted() {
        return qtyTargeted;
    }
    
    public String getQuantity()
    {
        return quantity;
    }
    
    public String getRead() {
        return read;
    }
    
    
    public Integer getRefToQuote()
    {
        return refToQuote;
    }
    public String getRegion() {
        return region;
    }
    
    public String getRegionId() {
        return regionId;
    }
    public String getRegisterNumber()
    {
        return registerNumber;
    }
    
    public String getReserveStatus()
    {
        return reserveStatus;
    }
    public String getRoleId() {
        return roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getRoutingNumber() {
        return routingNumber;
    }
    
    public String getSernoAttributeSetInstance()
    {
        return sernoAttributeSetInstance;
    }
    
    public String getSotrxFlag()
    {
        return sotrxFlag;
    }
    public String getStartDay() {
        return startDay;
    }
    public String getStartHour() {
        return startHour;
    }
    public String getStartMinute() {
        return startMinute;
    }
    public String getStartMonth() {
        return startMonth;
    }
    public String getStartYear() {
        return startYear;
    }
    public String getStdPurchasePrice() {
        return stdPurchasePrice;
    }
    public String getStdSalesPrice() {
        return stdSalesPrice;
    }
    public String getSubject() {
        return subject;
    }
    
    public String getSubmit()
    {
        return submit;
    }
    public String getSurname() {
        return surname;
    }
    
    public Integer[] getTargetId() {
        return targetId;
    }
    public String getTermsAgreement()
    {
        return termsAgreement;
    }
    
    public String getToDate() {
        return toDate;
    }
    
    public String getToOrderLineId()
    {
        return toOrderLineId;
    }
    
    public String getTotalLines()
    {
        return totalLines;
    }
    
    public String getTransmission() {
        return transmission;
    }
    public String getTransmissionAttributeValueId() {
        return transmissionAttributeValueId;
    }
    public String getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    public String getValue() {
        return value;
    }
    
    public String getVinNumber()
    {
        return vinNumber;
    }
    public String getWarehouseId() {
        return warehouseId;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    
    public String getWarehouseType()
    {
        return warehouseType;
    }
    public String getYear() {
        return year;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    
    
    public void setAllocationId(String allocationId)
    {
        this.allocationId = allocationId;
    }
    
    public void setAllocationStatus(String allocationStatus)
    {
        this.allocationStatus = allocationStatus;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }
    public void setAttributeSetId(String attributeSetId) {
        this.attributeSetId = attributeSetId;
    }
    public void setAttributeSetInstanceId(String attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setBin(String bin) {
        this.bin = bin;
    }
    public void setBpartnerId(String partnerId) {
        bpartnerId = partnerId;
    }
    public void setCheckBox(Integer[] checkBox)
    {
        this.checkBox = checkBox;
    }
    
    public String getYearAttributeValueId() {
        return yearAttributeValueId;
    }
    public void setAaCardnumber(String aaCardnumber) {
        this.aaCardnumber = aaCardnumber;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public void setAgreement(String agreement) 
    {
        this.agreement = agreement;
    }
    
    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public void setColourAttributeValueId(String colourAttributeValueId) {
        this.colourAttributeValueId = colourAttributeValueId;
    }
    
    public void setCompiereDocStatus(String compiereDocStatus)
    {
        this.compiereDocStatus = compiereDocStatus;
    }
    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public void setCounterOrderStatusInfo(String counterOrderStatusInfo) {
        this.counterOrderStatusInfo = counterOrderStatusInfo;
    }
    public void setCounterOrgID(String counterOrgID) {
        this.counterOrgID = counterOrgID;
    }
    public void setCounterOrgName(String counterOrgName) {
        this.counterOrgName = counterOrgName;
    }
    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }
    public void setCustIdNumber(String custIdNumber) {
        this.custIdNumber = custIdNumber;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }
    public void setDatePromised(String datePromised) {
        this.datePromised = datePromised;
    }
    public void setDateTrx(String dateTrx) {
        this.dateTrx = dateTrx;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }
    public void setDocStatusCode(String docStatusCode) {
        this.docStatusCode = docStatusCode;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    public void setEngineNumber(String engineNumber)
    {
        this.engineNumber = engineNumber;
    }
    
    public void setFile(FormFile file) 
    {
        this.file = file;
    }
    
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    
    public void setFromOrderLineId(String fromOrderLineId)
    {
        this.fromOrderLineId = fromOrderLineId;
    }
    
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public void setIsAccessAllOrgs(String isAccessAllOrgs) {
        this.isAccessAllOrgs = isAccessAllOrgs;
    }    
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }
    public void setIsAutomobile(String isAutomobile) {
        this.isAutomobile = isAutomobile;
    }
    public void setIsCustomer(String isCustomer) {
        this.isCustomer = isCustomer;
    }
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
    public void setIsDelivered(String isDelivered) {
        this.isDelivered = isDelivered;
    }
    public void setIsEmployee(String isEmployee) {
        this.isEmployee = isEmployee;
    }
    public void setIsInvoiced(String isInvoiced) {
        this.isInvoiced = isInvoiced;
    }
    public void setIsMotorcycle(String isMotorcycle) {
        this.isMotorcycle = isMotorcycle;
    }
    public void setIsNatisReleased(String isNatisReleased) {
        this.isNatisReleased = isNatisReleased;
    }    
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }
    public void setIsQtyReserved(String isQtyReserved) {
        this.isQtyReserved = isQtyReserved;
    }
    
    public void setIsReceipt(String isReceipt) {
        this.isReceipt = isReceipt;
    }
    public void setIsRetailer(String isRetailer) {
        this.isRetailer = isRetailer;
    }
    public void setIsSalesRep(String isSalesRep) {
        this.isSalesRep = isSalesRep;
    }
    public void setIsSotrx(String isSotrx) {
        this.isSotrx = isSotrx;
    }
    public void setIsVendor(String isVendor) {
        this.isVendor = isVendor;
    }
    public void setIsWholesaler(String isWholesaler) {
        this.isWholesaler = isWholesaler;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public void setList(ArrayList list)
    {
        this.list = list;
    }
    
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    
    public void setLocatorId(String locatorId)
    {
        this.locatorId = locatorId;
    }
    
    public void setLogo(FormFile logo)
    {
        this.logo = logo;
    }
    public void setLogoName(String logoName)
    {
        this.logoName = logoName;
    }
    public void setMaintenanceContractNumber(String maintenanceContractNumber) {
        this.maintenanceContractNumber = maintenanceContractNumber;
    }
    
    public void setMake(String make)
    {
        this.make = make;
    }
    
    public void setMakeAttributeValueId(String makeAttributeValueId)
    {
        this.makeAttributeValueId = makeAttributeValueId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }
    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setModelAttributeValueId(String modelAttributeValueId) {
        this.modelAttributeValueId = modelAttributeValueId;
    }
    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    
    public void setMovementDate(String movementDate)
    {
        this.movementDate = movementDate;
    }
    
    public void setMovementDescription(String movementDescription)
    {
        this.movementDescription = movementDescription;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNatisApplicationDate(String natisApplicationDate)
    {
        this.natisApplicationDate = natisApplicationDate;
    }
    public void setNatisControlNumber(String natisControlNumber)
    {
        this.natisControlNumber = natisControlNumber;
    }
    public void setNatisReleaseId(String natisReleaseId)
    {
        this.natisReleaseId = natisReleaseId;
    }
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setOrderLineBean(ArrayList orderLineBean)
    {
        this.orderLineBean = orderLineBean;
    }
    
    public void setOrderLineId(String orderLineId)
    {
        this.orderLineId = orderLineId;
    }
    
    public void setOrderLineIndexed(int index, OrderLineBean value)
    {
        orderLineList.add(index, value);
    }
    
    public void setOrderLineIndexed(OrderLineBean orderLineIndexed)
    {
        this.orderLineIndexed = orderLineIndexed;
    }
    
    public void setOrderLineList(ArrayList<OrderLineBean> orderLineList)
    {
        this.orderLineList = orderLineList;
    }
    
    public void setOrderStatusInfo(String orderStatusInfo) {
        this.orderStatusInfo = orderStatusInfo;
    }
    
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }
    public void setOrgID(String orgID) {
        OrgID = orgID;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
    public void setOrgUser(String orgUser) {
        this.orgUser = orgUser;
    }
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPayAmt(String payAmt) {
        this.payAmt = payAmt;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public void setPhone1(String phone1) {
        this.phone = phone1;
    }
    public void setPhoneNo(String phoneNo) {
        this.phone2 = phoneNo;
    }
    public void setPoReference(String poReference) {
        this.poReference = poReference;
    }
    public void setPOReference(String reference)
    {
        POReference = reference;
    }
    
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }
    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setPostalPostalCode(String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
    }
    
    public void setPrefOrderLineId(String prefOrderLineId)
    {
        this.prefOrderLineId = prefOrderLineId;
    }
    
    public void setPriceEntered(String priceEntered)
    {
        this.priceEntered = priceEntered;
    }
    
    public void setPriceListVersionId(String priceListVersionId) {
        this.priceListVersionId = priceListVersionId;
    }
    public void setProductAttributes(AttributeValuesPair productAttributes)
    {
        this.productAttributes = productAttributes;
    }
    
    public void setProductAttributeSetInstanceId(String productAttributeSetInstanceId)
    {
        this.productAttributeSetInstanceId = productAttributeSetInstanceId;
    }
    
    public void setProductCategoryId(String productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }    
    public void setQtyReserved(String qtyReserved)
    {
        this.qtyReserved = qtyReserved;
    }
    public void setQtyTargeted(String[] qtyTargeted) {
        this.qtyTargeted = qtyTargeted;
    }
    
    
    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }
    
    public void setRead(String read) {
        this.read = read;
    }
    public void setRefToQuote(Integer refToQuote) 
    {
        this.refToQuote = refToQuote;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    
    
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public void setRegisterNumber(String registerNumber)
    {
        this.registerNumber = registerNumber;
    }
    
    public void setReserveStatus(String reserveStatus)
    {
        this.reserveStatus = reserveStatus;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }   
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getPriceList() {
        return priceList;
    }
    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }
    
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }
    
    public void setSernoAttributeSetInstance(String sernoAttributeSetInstance)
    {
        this.sernoAttributeSetInstance = sernoAttributeSetInstance;
    }
    
    public void setSotrxFlag(String sotrxFlag)
    {
        this.sotrxFlag = sotrxFlag;
    }
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }
    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    public void setStdPurchasePrice(String stdPurchasePrice) {
        this.stdPurchasePrice = stdPurchasePrice;
    }
    public void setStdSalesPrice(String stdSalesPrice) {
        this.stdSalesPrice = stdSalesPrice;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setSubmit(String submit)
    {
        this.submit = submit;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setTargetId(Integer[] targetId) {
        this.targetId = targetId;
    }
    public void setTermsAgreement(String termsAgreement)
    {
        this.termsAgreement = termsAgreement;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public void setToOrderLineId(String toOrderLineId)
    {
        this.toOrderLineId = toOrderLineId;
    }
    
    public void setTotalLines(String totalLines)
    {
        this.totalLines = totalLines;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public void setTransmissionAttributeValueId(
            String transmissionAttributeValueId) {
        this.transmissionAttributeValueId = transmissionAttributeValueId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setVinNumber(String vinNumber)
    {
        this.vinNumber = vinNumber;
    }
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    
    public void setWarehouseType(String warehouseType)
    {
        this.warehouseType = warehouseType;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    public void setYearAttributeValueId(String yearAttributeValueId) {
        this.yearAttributeValueId = yearAttributeValueId;
    }
    
    public String getFirst()
    {
        return first;
    }
    
    public void setFirst(String first)
    {
        this.first = first;
    }
    
    public String getLicensingDistrict()
    {
        return licensingDistrict;
    }
    
    public void setLicensingDistrict(String licensingDistrict)
    {
        this.licensingDistrict = licensingDistrict;
    }
    
    public String getCompanyRegNo()
    {
        return companyRegNo;
    }
    
    public void setCompanyRegNo(String companyRegNo)
    {
        this.companyRegNo = companyRegNo;
    }
    
    public String getPassportNo()
    {
        return passportNo;
    }
    
    public void setPassportNo(String passportNo)
    {
        this.passportNo = passportNo;
    }    
    
    public String getTradeInOrderlineId() 
    {
        return tradeInOrderlineId;
    }
    
    public void setTradeInOrderlineId(String tradeInOrderlineId) 
    {
        this.tradeInOrderlineId = tradeInOrderlineId;
    }
    
    public String getProspects()
    {
        return prospects;
    }
    
    public void setProspects(String prospects)
    {
        this.prospects = prospects;
    }
    
    public String getSales()
    {
        return sales;
    }
    
    public void setSales(String sales)
    {
        this.sales = sales;
    }
    
    public ArrayList getShowroomTrafficBean()
    {
        return showroomTrafficBean;
    }
    
    public void setShowroomTrafficBean(ArrayList showroomTrafficBean)
    {
        this.showroomTrafficBean = showroomTrafficBean;
    }
    
    
    
    public String getTraffic()
    {
        return traffic;
    }
    
    public void setTraffic(String traffic)
    {
        this.traffic = traffic;
    }
    
   
    
    public String getModelCode() {
        return modelCode;
    }
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
    public String getModelCodeId() {
        return modelCodeId;
    }
    public void setModelCodeId(String modelCodeId) {
        this.modelCodeId = modelCodeId;
    }
    public String getModelId() {
        return modelId;
    }
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
    public String getTrx() {
        return trx;
    }
    public void setTrx(String trx) {
        this.trx = trx;
    }
    public String getTrxId() {
        return trxId;
    }
    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }
    public String getDealerCode() {
        return dealerCode;
    }
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    public String getDealerCodeId() {
        return dealerCodeId;
    }
    public void setDealerCodeId(String dealerCodeId) {
        this.dealerCodeId = dealerCodeId;
    }    
    public String getName2()
    {
        return name2;
    }
    public void setName2(String name2)
    {
        this.name2 = name2;
    }
    public String getIsAllocationWarehouse() {
        return isAllocationWarehouse;
    }
    public void setIsAllocationWarehouse(String isAllocationWarehouse) {
        this.isAllocationWarehouse = isAllocationWarehouse;
    }
    
    
    public String getCanAlterOrder() {
        return canAlterOrder;
    }
    public void setCanAlterOrder(String canAlterOrder) {
        this.canAlterOrder = canAlterOrder;
    }
    public String getCanCreateOrder() {
        return canCreateOrder;
    }
    public void setCanCreateOrder(String canCreateOrder) {
        this.canCreateOrder = canCreateOrder;
    }
    public String getCanViewOrder() {
        return canViewOrder;
    }
    public void setCanViewOrder(String canViewOrder) {
        this.canViewOrder = canViewOrder;
    }
    public String getSalesRepId() {
        return salesRepId;
    }
    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }
    public String getAltText() {
        return altText;
    }
    public void setAltText(String altText) {
        this.altText = altText;
    }
    public String getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    public String getDateTo() {
        return dateTo;
    }
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getImageTitle() {
        return imageTitle;
    }
    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getWeekNo()
    {
        return weekNo;
    }
    public void setWeekNo(String weekNo)
    {
        this.weekNo = weekNo;
    }    
    public String getFax()
    {
        return fax;
    }
    public void setFax(String fax)
    {
        this.fax = fax;
    }
    
    public String getFinanceOption()
    {
        return financeOption;
    }
    public void setFinanceOption(String financeOption)
    {
        this.financeOption = financeOption;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    
    
    public String getCountryOfImpCode()
    {
        return countryOfImpCode;
    }
    
    
    
    public void setCountryOfImpCode(String countryOfImpCode)
    {
        this.countryOfImpCode = countryOfImpCode;
    }
    
    
    
    public String getEnginePower()
    {
        return enginePower;
    }
    
    
    
    public void setEnginePower(String enginePower)
    {
        this.enginePower = enginePower;
    }
    
    
    
    public String getMainColourCode()
    {
        return mainColourCode;
    }
    
    
    
    public void setMainColourCode(String mainColourCode)
    {
        this.mainColourCode = mainColourCode;
    }
    
    
    
    public String getNatisModelNo()
    {
        return natisModelNo;
    }
    
    
    
    public void setNatisModelNo(String natisModelNo)
    {
        this.natisModelNo = natisModelNo;
    }
    
    
    
    public String getNewVinNumber()
    {
        return newVinNumber;
    }
    
    
    
    public void setNewVinNumber(String newVinNumber)
    {
        this.newVinNumber = newVinNumber;
    }
    
    
    
    public String getNoOfWheels()
    {
        return noOfWheels;
    }
    
    
    
    public void setNoOfWheels(String noOfWheels)
    {
        this.noOfWheels = noOfWheels;
    }
    
    
    
    public String getTare()
    {
        return tare;
    }
    
    
    
    public void setTare(String tare)
    {
        this.tare = tare;
    }
    
    
    
    public String getUnitNumber()
    {
        return unitNumber;
    }
    
    
    
    public void setUnitNumber(String unitNumber)
    {
        this.unitNumber = unitNumber;
    }
    
    public String getAttributesetinstanceIdFrom() {
        return attributesetinstanceIdFrom;
    }
    public void setAttributesetinstanceIdFrom(String attributesetinstanceIdFrom) {
        this.attributesetinstanceIdFrom = attributesetinstanceIdFrom;
    }
    public String getAttributesetinstanceIdTo() {
        return attributesetinstanceIdTo;
    }
    public void setAttributesetinstanceIdTo(String attributesetinstanceIdTo) {
        this.attributesetinstanceIdTo = attributesetinstanceIdTo;
    }
    
    public String getReleasedBy() {
        return releasedBy;
    }
    public void setReleasedBy(String releasedBy) {
        this.releasedBy = releasedBy;
    }
    
    public Integer[] getProductIds() {
        return productIds;
    }
    public void setProductIds(Integer[] productIds) {
        this.productIds = productIds;
    }
    public String getColourCodeHES()
    {
        return colourCodeHES;
    }
    
    
    
    public void setColourCodeHES(String colourCodeHES)
    {
        this.colourCodeHES = colourCodeHES;
    }
    public String getNatisModelDescription()
    {
        return natisModelDescription;
    }
    
    
    
    public void setNatisModelDescription(String natisModelDescription)
    {
        this.natisModelDescription = natisModelDescription;
    }
    
    public String getETA()
    {
        return ETA;
    }
    public void setETA(String eta)
    {
        ETA = eta;
    }
    
    public String getETAFilter()
    {
        return ETAFilter;
    }
    public void setETAFilter(String filter)
    {
        ETAFilter = filter;
    }
    
    
    public String getEngineCapacity()
    {
        return engineCapacity;
    }
    
    public void setEngineCapacity(String engineCapacity)
    {
        this.engineCapacity = engineCapacity;
    }
    
    public Integer[] getOrderIds() 
    {
        return orderIds;
    }
    public void setOrderIds(Integer[] orderIds) {
        this.orderIds = orderIds;
    }
    
    public Integer[] getSernoAttributeSetInstanceIds()
    {
        return sernoAttributeSetInstanceIds;
    }
    public void setSernoAttributeSetInstanceIds(
            Integer[] sernoAttributeSetInstanceIds)
    {
        this.sernoAttributeSetInstanceIds = sernoAttributeSetInstanceIds;
    }
    
    
    public String getModelDesc()
    {
        return modelDesc;
    }
    
    public void setModelDesc(String modelDesc)
    {
        this.modelDesc = modelDesc;
    }
    
    public String getModelMatchID()
    {
        return modelMatchID;
    }
    
    public void setModelMatchID(String modelMatchID)
    {
        this.modelMatchID = modelMatchID;
    }
    
    public String getNatisModelDesc()
    {
        return natisModelDesc;
    }
    
    public void setNatisModelDesc(String natisModelDesc)
    {
        this.natisModelDesc = natisModelDesc;
    }
    
    public String getTrans()
    {
        return trans;
    }
    
    public void setTrans(String trans)
    {
        this.trans = trans;
    }
    public String getAttributeValueId() {
        return attributeValueId;
    }
    public void setAttributeValueId(String attributeValueId) {
        this.attributeValueId = attributeValueId;
    }
    public String getShowActiveOnly() {
        return showActiveOnly;
    }
    public void setShowActiveOnly(String showActiveOnly) {
        this.showActiveOnly = showActiveOnly;
    }
    public String getUpdateAttrValueId() {
        return updateAttrValueId;
    }
    public void setUpdateAttrValueId(String updateAttrValueId) {
        this.updateAttrValueId = updateAttrValueId;
    }
    
    public String getIsVisible()
    {
        return isVisible;
    }
    
    public void setIsVisible(String isVisible)
    {
        this.isVisible = isVisible;
    }
    
    public String getActive()
    {
        return active;
    }
    
    public void setActive(String active)
    {
        this.active = active;
    }
    
    public String getVisible()
    {
        return visible;
    }
    
    public void setVisible(String visible)
    {
        this.visible = visible;
    }
    
    public String getColourName()
    {
        return colourName;
    }
    
    public void setColourName(String colourName)
    {
        this.colourName = colourName;
    }
    
    public String getColourCodeMatchId()
    {
        return colourCodeMatchId;
    }
    
    public void setColourCodeMatchId(String colourCodeMatchId)
    {
        this.colourCodeMatchId = colourCodeMatchId;
    }
    
    
    
    public String getBeginingBalance()
    {
        return beginingBalance;
    }
    
    public void setBeginingBalance(String beginingBalance)
    {
        this.beginingBalance = beginingBalance;
    }
    
    public String getEndingBalance() 
    {
        return endingBalance;
    }
    
    public void setEndingBalance(String endingBalance) 
    {
        this.endingBalance = endingBalance;
    }
    
    public String getStatementDifference()
    {
        return statementDifference;
    }
    
    public void setStatementDifference(String statementDifference)
    {
        this.statementDifference = statementDifference;
    }
    
    public String getAmount() 
    {
        return amount;
    }
    
    
    public void setAmount(String amount)
    {
        this.amount = amount;
    }
    
    
    public String getTrxType() 
    {
        return trxType;
    }
    
    
    public void setTrxType(String trxType) 
    {
        this.trxType = trxType;
    }
    
    
    
    public String getAmountGiven() {
        return amountGiven;
    }
    
    public void setAmountGiven(String amountGiven) {
        this.amountGiven = amountGiven;
    }
    
    public String getAmountRefunded() 
    {
        return amountRefunded;
    }
    
    public void setAmountRefunded(String amountRefunded)
    {
        this.amountRefunded = amountRefunded;
    }
    
    
    
    public Integer getCashBookId() 
    {
        return cashBookId;
    }
    
    public void setCashBookId(Integer cashBookId)
    {
        this.cashBookId = cashBookId;
    }
    
    public String getCashBookName()
    {
        return cashBookName;
    }
    
    
    public void setCashBookName(String cashBookName)
    {
        this.cashBookName = cashBookName;
    }
    
    
    
    public String getChequeNo() 
    {
        return chequeNo;
    }
    
    public void setChequeNo(String chequeNo)
    {
        this.chequeNo = chequeNo;
    }
    
    public String getCreditCardExpMonth() 
    {
        return creditCardExpMonth;
    }
    
    public void setCreditCardExpMonth(String creditCardExpMonth)
    {
        this.creditCardExpMonth = creditCardExpMonth;
    }
    
    public String getCreditCardExpYear() 
    {
        return creditCardExpYear;
    }
    
    public void setCreditCardExpYear(String creditCardExpYear)
    {
        this.creditCardExpYear = creditCardExpYear;
    }
    
    public String getCreditCardNumber() 
    {
        return creditCardNumber;
    }
    
    public void setCreditCardNumber(String creditCardNumber) 
    {
        this.creditCardNumber = creditCardNumber;
    }
    
    public String getCreditCardType() 
    {
        return creditCardType;
    }
    
    public void setCreditCardType(String creditCardType) 
    {
        this.creditCardType = creditCardType;
    }
    
    
    
    public Integer[] getOrderLineIds() 
    {
        return orderLineIds;
    }
    
    public void setOrderLineIds(Integer[] orderLineIds) 
    {
        this.orderLineIds = orderLineIds;
    }
    
    
    public Integer[] getPosOrderLineIds()
    {
        return posOrderLineIds;
    }
    
    public void setPosOrderLineIds(Integer[] posOrderLineIds) 
    {
        this.posOrderLineIds = posOrderLineIds;
    }
    public String getQtyLarge()
    {
        return qtyLarge;
    }
    
    public void setQtyLarge(String qtyLarge)
    {
        this.qtyLarge = qtyLarge;
    }
    
    public String getQtyMedium()
    {
        return qtyMedium;
    }
    
    public void setQtyMedium(String qtyMedium)
    {
        this.qtyMedium = qtyMedium;
    }
    
    public String getQtySmall()
    {
        return qtySmall;
    }
    
    public void setQtySmall(String qtySmall)
    {
        this.qtySmall = qtySmall;
    }
    
    public String getQtyXLarge()
    {
        return qtyXLarge;
    }
    
    public void setQtyXLarge(String qtyXLarge)
    {
        this.qtyXLarge = qtyXLarge;
    }
    
    public String getClientName()
    {
        return clientName;
    }
    
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
    
    
    
    public String getCashBookIds() {
        return cashBookIds;
    }
    public void setCashBookIds(String cashBookIds) {
        this.cashBookIds = cashBookIds;
    }
    public String getTransferAmount() {
        return transferAmount;
    }
    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }
    public String getApplicationName() {
        return applicationName;
    }
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    
    public String getPosId() {
        return this.posId;
    }
    
    public String getBackOrder()
    {
        return backOrder;
    }
    
    public void setBackOrder(String backOrder)
    {
        this.backOrder = backOrder;
    }
    
    public String getDesignAttributeValueId()
    {
        return designAttributeValueId;
    }
    
    public void setDesignAttributeValueId(String designAttributeValueId)
    {
        this.designAttributeValueId = designAttributeValueId;
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
    
    public String getSearchText()
    {
        return searchText;
    }
    
    public void setSearchText(String searchText)
    {
        this.searchText = searchText;
    }
    
    public String getShoppingCartItemId()
    {
        return shoppingCartItemId;
    }
    
    public void setShoppingCartItemId(String shoppingCartItemId)
    {
        this.shoppingCartItemId = shoppingCartItemId;
    }
    
    public String getSizeAttributeValueId()
    {
        return sizeAttributeValueId;
    }
    
    public void setSizeAttributeValueId(String sizeAttributeValueId)
    {
        this.sizeAttributeValueId = sizeAttributeValueId;
    }
        
    public String getIsNotWebstoreFeatured()
    {
        return isNotWebstoreFeatured;
    }
    
    public void setIsNotWebstoreFeatured(String isNotWebstoreFeatured)
    {
        this.isNotWebstoreFeatured = isNotWebstoreFeatured;
    }
    
    public String getIsWebstoreFeatured()
    {
        return isWebstoreFeatured;
    }
    
    public void setIsWebstoreFeatured(String isWebstoreFeatured)
    {
        this.isWebstoreFeatured = isWebstoreFeatured;
    }
    
    public String getImageName()
    {
        return imageName;
    }
    
    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }
    
    public ProductImageBean getProductsIndexed()
    {
        return productsIndexed;
    }
    
    public ProductImageBean getProductsIndexed(int index)
    {
        while (index >= productsList.size())
            productsList.add(new ProductImageBean());
        return (ProductImageBean) productsList.get(index);
    }
    
    public void setProductsIndexed(ProductImageBean productsIndexed)
    {
        this.productsIndexed = productsIndexed;
    }
    
    public void setProductsIndexed(int index, ProductImageBean value)
    {
        productsList.add(index, value);
    }
    public void setPosId(String posId) {
        this.posId = posId;
    }
    
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getPurchasePriceStandard() {
        return purchasePriceStandard;
    }
    public void setPurchasePriceStandard(String purchasePriceStandard) {
        this.purchasePriceStandard = purchasePriceStandard;
    }
    
    
    public String getPurchasePriceLimit() {
        return purchasePriceLimit;
    }
    public void setPurchasePriceLimit(String purchasePriceLimit) {
        this.purchasePriceLimit = purchasePriceLimit;
    }
    public String getPurchasePriceList() {
        return purchasePriceList;
    }
    public void setPurchasePriceList(String purchasePriceList) {
        this.purchasePriceList = purchasePriceList;
    }
    public String getSalesPriceLimit() {
        return salesPriceLimit;
    }
    public void setSalesPriceLimit(String salesPriceLimit) {
        this.salesPriceLimit = salesPriceLimit;
    }
    public String getSalesPriceList() {
        return salesPriceList;
    }
    public void setSalesPriceList(String salesPriceList) {
        this.salesPriceList = salesPriceList;
    }
    public String getSalesPriceStandard() {
        return salesPriceStandard;
    }
    public void setSalesPriceStandard(String salesPriceStandard) {
        this.salesPriceStandard = salesPriceStandard;
    }
    public String getNoOfProductsRequired() {
        return noOfProductsRequired;
    }
    public void setNoOfProductsRequired(String noOfProductsRequired) {
        this.noOfProductsRequired = noOfProductsRequired;
    }
    public String getReportType() {
        return reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public String getUserSurname() {
        return userSurname;
    }
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
    public String getCountryId() {
        return countryId;
    }
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public ArrayList getProductsList()
    {
        return productsList;
    }
    
    public void setProductsList(ArrayList<ProductImageBean> productsList)
    {
        this.productsList = productsList;
    }
    
    public String getNewName()
    {
        return newName;
    }
    
    public void setNewName(String newName)
    {
        this.newName = newName;
    }
    public String getPaymentByCard() {
        return paymentByCard;
    }
    public void setPaymentByCard(String paymentByCard) {
        this.paymentByCard = paymentByCard;
    }
    public String getPaymentByCash() {
        return paymentByCash;
    }
    public void setPaymentByCash(String paymentByCash) {
        this.paymentByCash = paymentByCash;
    }
    public String getPaymentByChq() {
        return paymentByChq;
    }
    public void setPaymentByChq(String paymentByChq) {
        this.paymentByChq = paymentByChq;
    }
    public String getIsSales() {
        return isSales;
    }
    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }
    public String getProductClassification() {
        return productClassification;
    }
    
    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
    }
    
    
    public String getTrackingNo() {
        return trackingNo;
    }
    
    
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
    
    public String[] getSizes()
    {
        return sizes;
    }
    public void setSizes(String[] sizes)
    {
        this.sizes = sizes;
    }
    
    public String getRevenueRecognition()
    {
        return revenueRecognition;
    }
    public void setRevenueRecognition(String revenueRecognition)
    {
        this.revenueRecognition = revenueRecognition;
    }
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    public String getDesignName() 
    {
        return designName;
    }
    
    public void setDesignName(String designName) {
        this.designName = designName;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) 
    {
        this.productCategory = productCategory;
    }
    
    public String getSizeName() 
    {
        return sizeName;
    }
    
    public String getRevenueRecoId()
    {
        return revenueRecoId;
    }
    
    
    public void setRevenueRecoId(String revenueRecoId)
    {
        this.revenueRecoId = revenueRecoId;
    }
    
        
    public void setSizeName(String sizeName) 
    {
        this.sizeName = sizeName;
    }
    
    
    
    public String getIsTextileProduct() 
    {
        return isTextileProduct;
    }
      
    
    public void setIsTextileProduct(String isTextileProduct) 
    {
        this.isTextileProduct = isTextileProduct;
    }


    public String getQtyFilter()
    {
        return qtyFilter;
    }


    public void setQtyFilter(String qtyFilter)
    {
        this.qtyFilter = qtyFilter;
    }
    public ArrayList getAttributeValueList() {
        return attributeValueList;
    }
    public void setAttributeValueList(ArrayList attributeValueList) {
        this.attributeValueList = attributeValueList;
    }
    public String[] getAttributeValueIds() {
        return attributeValueIds;
    }
    public void setAttributeValueIds(String[] attributeValueIds) {
        this.attributeValueIds = attributeValueIds;
    }
    
    

	public String getCvv()
	{
		return cvv;
	}

	public void setCvv(String cvv)
	{
		this.cvv = cvv;
	}




	public String getCreditCardPayment()
	{
		return creditCardPayment;
	}




	public void setCreditCardPayment(String creditCardPayment)
	{
		this.creditCardPayment = creditCardPayment;
	}



	
    
    


	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}




	public String getBccAddresses()
	{
		return bccAddresses;
	}




	public void setBccAddresses(String bccAddresses)
	{
		this.bccAddresses = bccAddresses;
	}




	public String getCcAddresses()
	{
		return ccAddresses;
	}




	public void setCcAddresses(String ccAddresses)
	{
		this.ccAddresses = ccAddresses;
	}




	public String getFromAddress()
	{
		return fromAddress;
	}




	public void setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
	}




	public String getTextMessage()
	{
		return textMessage;
	}




	public void setTextMessage(String textMessage)
	{
		this.textMessage = textMessage;
	}




	public String getToAddresses()
	{
		return toAddresses;
	}




	public void setToAddresses(String toAddresses)
	{
		this.toAddresses = toAddresses;
	}




	public String getShipperId()
	{
		return shipperId;
	}




	public void setShipperId(String shipperId)
	{
		this.shipperId = shipperId;
	}




	public String getAccountName()
	{
		return accountName;
	}




	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}




	public String getCreditCardExpMonthName()
	{
		return creditCardExpMonthName;
	}




	public void setCreditCardExpMonthName(String creditCardExpMonthName)
	{
		this.creditCardExpMonthName = creditCardExpMonthName;
	}




	public String getCreditCardNumberEncrypted()
	{
		return creditCardNumberEncrypted;
	}




	public void setCreditCardNumberEncrypted(String creditCardNumberEncrypted)
	{
		this.creditCardNumberEncrypted = creditCardNumberEncrypted;
	}




	public String getPaymentMethod()
	{
		return paymentMethod;
	}




	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	
	public String getToUserId()
	{
		return toUserId;
	}




	public void setToUserId(String toUserId)
	{
		this.toUserId = toUserId;
	}




	public String getPriority()
	{
		return priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getChargeId()
	{
		return chargeId;
	}
	public void setChargeId(String chargeId)
	{
		this.chargeId = chargeId;
	}
	public String getStatusId()
	{
		return statusId;
	}
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}
	public String getProductType()
	{
		return productType;
	}
	public void setProductType(String productType)
	{
		this.productType = productType;
	}
	public String getQtyNumberFilter()
	{
		return qtyNumberFilter;
	}
	public void setQtyNumberFilter(String qtyNumberFilter)
	{
		this.qtyNumberFilter = qtyNumberFilter;
	}
	public String getTaxCategoryId()
	{
		return taxCategoryId;
	}
	public void setTaxCategoryId(String taxCategoryId)
	{
		this.taxCategoryId = taxCategoryId;
	}
	public String getTaxCategoryName()
	{
		return taxCategoryName;
	}
	public void setTaxCategoryName(String taxCategoryName)
	{
		this.taxCategoryName = taxCategoryName;
	}
	public String getTotalActualPrice()
	{
		return totalActualPrice;
	}
	public void setTotalActualPrice(String totalActualPrice)
	{
		this.totalActualPrice = totalActualPrice;
	}
    public String getIfAdd() {
        return ifAdd;
    }
    public void setIfAdd(String ifAdd) {
        this.ifAdd = ifAdd;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}	

	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	public String getTaxRate() {
        return taxRate;
    }


    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }


    public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
    
    public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


    public String getTransferAllAmount() {
        return transferAllAmount;
    }


    public void setTransferAllAmount(String transferAllAmount) {
        this.transferAllAmount = transferAllAmount;
    }


    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }


    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }


    public String getDifferenceAmt() {
        return differenceAmt;
    }


    public void setDifferenceAmt(String differenceAmt) {
        this.differenceAmt = differenceAmt;
    }


    public String getCardTotal() {
        return cardTotal;
    }


    public void setCardTotal(String cardTotal) {
        this.cardTotal = cardTotal;
    }


    public String getCashTotal() {
        return cashTotal;
    }


    public void setCashTotal(String cashTotal) {
        this.cashTotal = cashTotal;
    }


    public String getChequeTotal() {
        return chequeTotal;
    }


    public void setChequeTotal(String chequeTotal) {
        this.chequeTotal = chequeTotal;
    }


    public String getTillGrandTotal() {
        return tillGrandTotal;
    }


    public void setTillGrandTotal(String tillGrandTotal) {
        this.tillGrandTotal = tillGrandTotal;
    }


    public String getCreatingFromOrder() {
        return creatingFromOrder;
    }


    public void setCreatingFromOrder(String creatingFromOrder) {
        this.creatingFromOrder = creatingFromOrder;
    }


    public String getTransferType() {
        return transferType;
    }


    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
public String getCustomSize() {
		return customSize;
	}


	public void setCustomSize(String customSize) {
		this.customSize = customSize;
	}


	public String getPriceQtyFilter() {
		return priceQtyFilter;
	}
	
	public String getDeliveryViaRule()
	{
		return deliveryViaRule;
	}


	public void setPriceQtyFilter(String priceQtyFilter) {
		this.priceQtyFilter = priceQtyFilter;
	}

	public void setDeliveryViaRule(String deliveryViaRule)
	{
		this.deliveryViaRule = deliveryViaRule;
	}


	public String getCashId()
	{
		return cashId;
	}


	public void setCashId(String cashId)
	{
		this.cashId = cashId;
	}


	public String getCashLineId()
	{
		return cashLineId;
	}


	public void setCashLineId(String cashLineId)
	{
		this.cashLineId = cashLineId;
	}
    public String getUserPIN() {
        return userPIN;
    }


    public void setUserPIN(String userPIN) {
        this.userPIN = userPIN;
    }

    public String getPaymentRule() {
        return paymentRule;
    }


    public void setPaymentRule(String paymentRule) {
        this.paymentRule = paymentRule;
    }

	public String getIsSelfService()
	{
		return isSelfService;
	}


	public void setIsSelfService(String isSelfService)
	{
		this.isSelfService = isSelfService;
	}


    public String getCardDifference() {
        return cardDifference;
    }


    public void setCardDifference(String cardDifference) {
        this.cardDifference = cardDifference;
    }


    public String getChequeDifference() {
        return chequeDifference;
    }


    public void setChequeDifference(String chequeDifference) {
        this.chequeDifference = chequeDifference;
    }

    

	public String getDocumentNote()
	{
		return documentNote;
	}


	public void setDocumentNote(String documentNote)
	{
		this.documentNote = documentNote;
	}


    public String getCurrencySymbole() {
        return currencySymbole;
    }


    public void setCurrencySymbole(String currencySymbole) {
        this.currencySymbole = currencySymbole;
    }

public String getUserDiscount() {
		return userDiscount;
	}


	public void setUserDiscount(String userDiscount) {
		this.userDiscount = userDiscount;
	}


    public String getOpenAmt() {
        return openAmt;
    }


    public void setOpenAmt(String openAmt) {
        this.openAmt = openAmt;
    }


    public String getOverUnderPayment() {
        return overUnderPayment;
    }


    public void setOverUnderPayment(String overUnderPayment) {
        this.overUnderPayment = overUnderPayment;
    }


    public String getPaidAmt() {
        return paidAmt;
    }


    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }


    public String getPaymentAmt() {
        return paymentAmt;
    }


    public void setPaymentAmt(String paymentAmt) {
        this.paymentAmt = paymentAmt;
    }


    public String getWriteOffAmt() {
        return writeOffAmt;
    }


    public void setWriteOffAmt(String writeOffAmt) {
        this.writeOffAmt = writeOffAmt;
    }



    public String getCurrencyId() {
        return currencyId;
    }


    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }


    public String getDaysDue() {
        return daysDue;
    }


    public void setDaysDue(String daysDue) {
        this.daysDue = daysDue;
    }


    public String getDiscountAmt() {
        return discountAmt;
    }


    public void setDiscountAmt(String discountAmt) {
        this.discountAmt = discountAmt;
    }


    public String getDiscountDate() {
        return discountDate;
    }


    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }


    public String getDue0() {
        return due0;
    }


    public void setDue0(String due0) {
        this.due0 = due0;
    }


    public String getDue0_30() {
        return due0_30;
    }


    public void setDue0_30(String due0_30) {
        this.due0_30 = due0_30;
    }


    public String getDue1_7() {
        return due1_7;
    }


    public void setDue1_7(String due1_7) {
        this.due1_7 = due1_7;
    }


    public String getDue31_60() {
        return due31_60;
    }


    public void setDue31_60(String due31_60) {
        this.due31_60 = due31_60;
    }


    public String getDue61_90() {
        return due61_90;
    }


    public void setDue61_90(String due61_90) {
        this.due61_90 = due61_90;
    }


    public String getDue8_30() {
        return due8_30;
    }


    public void setDue8_30(String due8_30) {
        this.due8_30 = due8_30;
    }


    public String getDue91_PLUS() {
        return due91_PLUS;
    }


    public void setDue91_PLUS(String due91_PLUS) {
        this.due91_PLUS = due91_PLUS;
    }


    public String getDueAmt() {
        return dueAmt;
    }


    public void setDueAmt(String dueAmt) {
        this.dueAmt = dueAmt;
    }


    public String getDueDate() {
        return dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


    public String getInvoicedAmt() {
        return invoicedAmt;
    }


    public void setInvoicedAmt(String invoicedAmt) {
        this.invoicedAmt = invoicedAmt;
    }


    public String getInvoiceDate() {
        return invoiceDate;
    }


    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }


    public String getInvoiceGrandTotal() {
        return invoiceGrandTotal;
    }


    public void setInvoiceGrandTotal(String invoiceGrandTotal) {
        this.invoiceGrandTotal = invoiceGrandTotal;
    }


    public String getInvoiceScheduleId() {
        return invoiceScheduleId;
    }


    public void setInvoiceScheduleId(String invoiceScheduleId) {
        this.invoiceScheduleId = invoiceScheduleId;
    }


    public String getNetDays() {
        return netDays;
    }


    public void setNetDays(String netDays) {
        this.netDays = netDays;
    }


    public String getPastDue1_30() {
        return pastDue1_30;
    }


    public void setPastDue1_30(String pastDue1_30) {
        this.pastDue1_30 = pastDue1_30;
    }


    public String getPastDue1_7() {
        return pastDue1_7;
    }


    public void setPastDue1_7(String pastDue1_7) {
        this.pastDue1_7 = pastDue1_7;
    }


    public String getPastDue31_60() {
        return pastDue31_60;
    }


    public void setPastDue31_60(String pastDue31_60) {
        this.pastDue31_60 = pastDue31_60;
    }


    public String getPastDue61_90() {
        return pastDue61_90;
    }


    public void setPastDue61_90(String pastDue61_90) {
        this.pastDue61_90 = pastDue61_90;
    }


    public String getPastDue8_30() {
        return pastDue8_30;
    }


    public void setPastDue8_30(String pastDue8_30) {
        this.pastDue8_30 = pastDue8_30;
    }


    public String getPastDue91_plus() {
        return pastDue91_plus;
    }


    public void setPastDue91_plus(String pastDue91_plus) {
        this.pastDue91_plus = pastDue91_plus;
    }


    public String getPastDueAmt() {
        return pastDueAmt;
    }


    public void setPastDueAmt(String pastDueAmt) {
        this.pastDueAmt = pastDueAmt;
    }


    public String getPaymentTermId() {
        return paymentTermId;
    }


    public void setPaymentTermId(String paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

	
	public FormFile getImage()
	{
		return image;
	}


	public void setImage(FormFile image)
	{
		this.image = image;
	}


	public String getBlackListedId()
	{
		return blackListedId;
	}


	public void setBlackListedId(String blackListedId)
	{
		this.blackListedId = blackListedId;
	}


	public String getBlackListedBankName()
	{
		return blackListedBankName;
	}


	public void setBlackListedBankName(String blackListedBankName)
	{
		this.blackListedBankName = blackListedBankName;
	}


	public String getBlackListedChequeNo()
	{
		return blackListedChequeNo;
	}


	public void setBlackListedChequeNo(String blackListedChequeNo)
	{
		this.blackListedChequeNo = blackListedChequeNo;
	}
	
	 public String getInvoiceNo() 
     {
     
        return invoiceNo;
     }
    public void setInvoiceNo(String invoiceNo) 
    {
        this.invoiceNo = invoiceNo;
    }


	
	


    public Integer[] getBpartnerIds() {
        return bpartnerIds;
    }


    public void setBpartnerIds(Integer[] bpartnerIds) {
        this.bpartnerIds = bpartnerIds;
    }


    public String getPaymentTermName() {
        return paymentTermName;
    }


    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }


    public String getBP_Group_ID() {
        return BP_Group_ID;
    }


    public void setBP_Group_ID(String group_ID) {
        BP_Group_ID = group_ID;
    }


    public String getDunningLevelId() {
        return dunningLevelId;
    }


    public void setDunningLevelId(String dunningLevelId) {
        this.dunningLevelId = dunningLevelId;
    }


    public String getDunningLevelName() {
        return dunningLevelName;
    }


    public void setDunningLevelName(String dunningLevelName) {
        this.dunningLevelName = dunningLevelName;
    }


    public String getDunningPrintNote() {
        return dunningPrintNote;
    }


    public void setDunningPrintNote(String dunningPrintNote) {
        this.dunningPrintNote = dunningPrintNote;
    }


    public String getDunningPrintText() {
        return dunningPrintText;
    }


    public void setDunningPrintText(String dunningPrintText) {
        this.dunningPrintText = dunningPrintText;
    }


  

    public String getDunningId() {
        return dunningId;
    }


    public void setDunningId(String dunningId) {
        this.dunningId = dunningId;
    }


    public String getDunningName() {
        return dunningName;
    }


    public void setDunningName(String dunningName) {
        this.dunningName = dunningName;
    }


    public String getCreditLimit() {
        return creditLimit;
    }


    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }


    public String getCreditStatus() {
        return creditStatus;
    }


    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }


    public String getCreditUsed() {
        return creditUsed;
    }


    public void setCreditUsed(String creditUsed) {
        this.creditUsed = creditUsed;
    }


    public String getDocBasisType() {
        return docBasisType;
    }


    public void setDocBasisType(String docBasisType) {
        this.docBasisType = docBasisType;
    }


    public String getAmtMultiplier() {
        return amtMultiplier;
    }


    public void setAmtMultiplier(String amtMultiplier) {
        this.amtMultiplier = amtMultiplier;
    }


    public String getFrequencyType() {
        return frequencyType;
    }


    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }


    public String getCommissionAmtId() {
        return commissionAmtId;
    }


    public void setCommissionAmtId(String commissionAmtId) {
        this.commissionAmtId = commissionAmtId;
    }


    public String getSubtractAmt() {
        return subtractAmt;
    }


    public void setSubtractAmt(String subtractAmt) {
        this.subtractAmt = subtractAmt;
    }


    public String getForward() {
        return forward;
    }


    public void setForward(String forward) {
        this.forward = forward;
    }


    public String getIsTaxExempted() {
        return isTaxExempted;
    }


    public void setIsTaxExempted(String isTaxExempted) {
        this.isTaxExempted = isTaxExempted;
    }


    public String getTaxId() {
        return taxId;
    }


    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }


    public String getTaxName() {
        return taxName;
    }


    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }


    public Integer[] getInvoiceIds() {
        return invoiceIds;
    }


    public void setInvoiceIds(Integer[] invoiceIds) {
        this.invoiceIds = invoiceIds;
    }


    public String getInventoryId() {
        return inventoryId;
    }


    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }


    public String getInventoryLineId() {
        return inventoryLineId;
    }


    public void setInventoryLineId(String inventoryLineId) {
        this.inventoryLineId = inventoryLineId;
    }


    public String getQtyBook() {
        return qtyBook;
    }


    public void setQtyBook(String qtyBook) {
        this.qtyBook = qtyBook;
    }


    public String getQtyCount() {
        return qtyCount;
    }


    public void setQtyCount(String qtyCount) {
        this.qtyCount = qtyCount;
    }


    public String getAfterDelivery() {
        return afterDelivery;
    }


    public void setAfterDelivery(String afterDelivery) {
        this.afterDelivery = afterDelivery;
    }


    public String getFiedMonthCutoff() {
        return fiedMonthCutoff;
    }


    public void setFiedMonthCutoff(String fiedMonthCutoff) {
        this.fiedMonthCutoff = fiedMonthCutoff;
    }


    public String getFixedDueDate() {
        return fixedDueDate;
    }


    public void setFixedDueDate(String fixedDueDate) {
        this.fixedDueDate = fixedDueDate;
    }


    public String getFixedMonthDay() {
        return fixedMonthDay;
    }


    public void setFixedMonthDay(String fixedMonthDay) {
        this.fixedMonthDay = fixedMonthDay;
    }


    public String getFixedMonthOffset() {
        return fixedMonthOffset;
    }


    public void setFixedMonthOffset(String fixedMonthOffset) {
        this.fixedMonthOffset = fixedMonthOffset;
    }


    public String getNextBusinessday() {
        return nextBusinessday;
    }


    public void setNextBusinessday(String nextBusinessday) {
        this.nextBusinessday = nextBusinessday;
    }


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPhone2() {
		return phone2;
	}


	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	public String getToBeShipped() {
        return toBeShipped;
    }


    public void setToBeShipped(String toBeShipped) {
        this.toBeShipped = toBeShipped;
    }


    public String getDiscountAmt1() {
        return discountAmt1;
    }


    public void setDiscountAmt1(String discountAmt1) {
        this.discountAmt1 = discountAmt1;
    }


    public String getDiscountAmt2() {
        return discountAmt2;
    }


    public void setDiscountAmt2(String discountAmt2) {
        this.discountAmt2 = discountAmt2;
    }


    public String getDiscountDay1() {
        return discountDay1;
    }


    public void setDiscountDay1(String discountDay1) {
        this.discountDay1 = discountDay1;
    }


    public String getDiscountDay2() {
        return discountDay2;
    }


    public void setDiscountDay2(String discountDay2) {
        this.discountDay2 = discountDay2;
    }
	






    public PaymentBean getPaymentBeanIndex() {
        return paymentBeanIndex;
    }


    public void setPaymentBeanIndex(PaymentBean paymentBeanIndex) {
        this.paymentBeanIndex = paymentBeanIndex;
    }


    public String getAllocatedAmount() {
        return allocatedAmount;
    }


    public void setAllocatedAmount(String allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }


	public String getGroup1() {
		return group1;
	}


	public void setGroup1(String group1) {
		this.group1 = group1;
	}


	public String getGroup2() {
		return group2;
	}


	public void setGroup2(String group2) {
		this.group2 = group2;
	}

   

    public String getIsFullAccess() {
        return isFullAccess;
    }


    public void setIsFullAccess(String isFullAccess) {
        this.isFullAccess = isFullAccess;
    }


	public Integer[] getInventoryIds()
	{
		return inventoryIds;
	}


	public void setInventoryIds(Integer[] inventoryIds)
	{
		this.inventoryIds = inventoryIds;
	}


	public String[] getAllocateAmount() {
		return allocateAmount;
	}


	public void setAllocateAmount(String[] allocateAmount) {
		this.allocateAmount = allocateAmount;
	}


	public String getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getOpenDrawer() {
		return openDrawer;
	}


	public void setOpenDrawer(String openDrawer) {
		this.openDrawer = openDrawer;
	}


	public String getStoreId() {
		return storeId;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


}





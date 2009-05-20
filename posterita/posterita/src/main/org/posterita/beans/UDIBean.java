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
package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.posterita.core.JulianDate;

public class UDIBean
{
	protected String bpAddress2;
    protected String aaCardnumber;
    protected String accountNo;
    protected String accountType;
    protected String accountName;    
    protected String address1;
    protected String address2;
    protected Boolean agreement;    
    protected String aisle;
    protected Integer allocationId;
    protected String allocationStatus;
    protected String area;
    protected String attribute; 
    protected Integer attributeId;
    protected Integer attributeSetId;
    protected Integer attributeSetInstanceId;
    protected Integer [] sernoAttributeSetInstanceIds;
    protected String attributeValue; 
    protected String availableForBackOrderFilter ="availableForBackOrder in ('Y', 'N')";
    protected Integer bankId;
    protected Integer bankAccountId;
    protected String bankName;
    protected Integer basePriceListId;
    protected String bin;
    protected Integer bpartnerId;    
    protected Integer bPartnerLocationId;
    protected Integer [] checkBox; 
    protected Boolean isChecked;
    protected String checkNo;
    protected String city;
    protected Timestamp closingDate;
    protected Integer colour;
    protected String confirmEmail;
    protected String confirmPassword;
    protected Integer cost;    
    protected String counterOrderStatusInfo;
    protected Integer counterOrgID;
    protected String counterOrgName;
    protected Double currentBalance;
    protected String custIdNumber;
    protected Timestamp dateCreated;
    protected Timestamp dateUpdated;
    protected Timestamp dateInvoiced;   
    protected Timestamp dateMovement;
    protected Timestamp dateOrdered;
    protected String datePromised;    
    protected Timestamp dateTrx;
    protected String dealerName;	
    protected String description;
    protected String district;
    protected String docStatus;
    protected Integer tradeInOrderlineId;
    protected Integer attributeValueId;
    protected String forward;
    protected String birthdate;
    
    protected Boolean isFullAccess;
    protected Boolean isOverwritePriceLimit;
    
    protected Boolean openDrawer = Boolean.TRUE;     
    
    
    
    protected String docStatusCode;   
    
    //1 is reserved -0 not reserved
    
    protected Integer documentId;
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
    protected String fromDate;	    
    protected Integer fromOrderLineId;
    protected Integer grandTotal;    
    protected BigDecimal grossProfit;
    protected BigDecimal grossProfitPercentage;
    protected BigDecimal totalGrossProfit;
    protected BigDecimal totalGrossProfitPercentage;
    protected Integer id;    
    protected Integer index;
    protected Integer inOutId;    
    protected Integer invoiceId;
    protected Boolean isAccessAllOrgs;
    protected Boolean a;
    protected String isApproved;    
    protected Boolean isAutomobile;
    protected Boolean isAvailableForBackOrder = Boolean.valueOf("True");
    protected Boolean isClosable;
    protected Boolean isCustomer;
    protected Boolean isDefault = false;
    protected String isDelivered;    
    protected Boolean isEmployee;
   
    protected String isInvoiced;    
    protected Boolean isMandatory = false;
    protected Boolean isMotorcycle;
    protected Boolean isNatisReleased;
    protected String isPaid;   
    protected Boolean isPresentForProduct;
    protected Boolean isPublic;
    protected Boolean isQtyReserved;
    protected String isReceipt;
    protected Boolean isRetailer;
    protected Boolean isSalesRep;
    protected Boolean isActive = false; 
    protected Boolean isSoTrx;    
    protected Boolean isVendor;
    protected Boolean isVisible;
    protected Boolean isWholesaler;
    protected String level;
    protected BigDecimal limitPrice;
    protected BigDecimal limitPriceIncl;
    protected BigDecimal lineAmt;
    protected Integer lineNetAmt;
    protected Integer lineNo;
    protected Integer lineTotalAmt;
    protected ArrayList list;
    protected BigDecimal listPrice;
    protected BigDecimal listPriceIncl;
    protected String location;
    protected Integer locationId;
    protected Integer locatorId;
    protected String locatorName;
    protected String logoName;
    protected Integer m_attributesetinstance_id;  
    protected String maintenanceContractNumber;
    protected Integer make;
    protected Integer menuId;
    protected String menuItemName;
    protected String menuLink;
    protected String message;
    protected Integer messageID;
    protected Integer minOutId;
    protected String mobile;
    protected Integer model;
    protected Integer modelGroup;
    protected Integer month;
    protected Integer movementId;
    protected Integer moveConfirmId;
    protected Integer movementLineId;
    protected String name;    

    
    protected Integer natisReleaseId;
    protected String newEmail;
    protected String newPassword;
    protected Integer nextAvailableLocatorId;	
    protected String oldPassword;
    protected Integer orderId;
    protected ArrayList orderLineBean;
    protected ArrayList<RoleBean> roleBeanList = new ArrayList<RoleBean>();
    protected Integer orderLineId;
    protected int [] orderLineIds;
    protected ArrayList orderLineList = new ArrayList();
    protected ArrayList InventoryLineList = new ArrayList();
    protected String orderStatusInfo;
    protected String orderType;
    protected Integer orgFromId;
    protected String orgFromName;
    protected Integer orgToId;
    protected String orgToName;
    protected String orgName;
    protected String orgType;
    protected Vector orgUsers;
    protected Integer parentMenuId;
    protected Integer partnerId;
    protected Integer partnerLocationId;
    protected String partnerName;
    protected String password;
    protected Stack path;
    protected Integer payAmt;    
    protected Integer paymentId;
    protected String phone;
    protected String phone2;
    protected String poReference;
    protected String POReference;    
    protected String postalAddress;   
    protected String postalAddress1;
    protected String postalCity;
    protected String postalCode;
    protected String postalPostalCode;
    protected Integer prefOrderLineId;
    protected BigDecimal priceActual;
    protected Double priceEntered;
    protected BigDecimal priceLimit;
    protected BigDecimal priceList;
    protected PriceListBean priceListBean;
    protected ArrayList<PriceListBean> priceListBeanList;
    protected Integer priceListVersionId;
    protected ProductBean productBean;
    protected ArrayList<ProductBean> productBeanList = new ArrayList<ProductBean>();
    protected Integer productCategoryId;
    protected BigDecimal price;
    
    protected String productDetails;
    protected Integer productId;
    protected String productName;
    protected Integer productQuantity;    
    protected Integer qtyInvoiced;
    protected String qtyReserved;
    protected BigDecimal quantity;
    protected BigDecimal qtyOnHand;
    protected BigDecimal qtyToMove;
    protected String read;
    protected Integer refInOutId;
    protected Integer refNo;
    protected Integer refOrderId;
    protected Integer refOrderLineid;
    protected Integer refToQuote; 
    protected ReportBean report;
    ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
    protected String region;
    protected Integer regionId;
    protected String regionName;
    protected String registerNumber;
    
    protected Integer reserveStatus;
    
    protected Integer roleId;
    protected String roleName;
    protected String routingNumber;
    protected String sernoAttributeSetInstance;
    protected String startDay;
    protected String startHour;
    protected String startMinute;
    protected String startMonth;
    protected String startYear;
    protected Boolean status;
    protected BigDecimal stdPrice;
    protected BigDecimal stdPriceIncl;
    protected Double stdPurchasePrice;
    protected Double stdSalesPrice;
    protected StockMovementBean stock;
	protected ArrayList<StockMovementBean> stockList = new ArrayList<StockMovementBean>();
    protected String subject;
    protected String submit;
    
    protected String surname;
    protected Integer taxAmt;
    protected Integer[] targetId;
    protected String termsAgreement;
    protected String toDate;    
    protected Integer toOrderLineId;
    protected Integer totalLines;
    protected Integer transmission;
    protected Integer transmissionAttributeValueId;
    protected String uom;
    protected Integer userId;
    protected Integer userID;
    protected String username; 
    protected String vinNumber;
    protected Integer warehouseId;
    
    protected Integer wareHouseId;
    protected String warehouseName;
    protected String warehouseType;
    protected Integer year;
    protected Integer yearAttributeValueId;	
    protected Integer makeAttributeValueId;
    protected String[] qtyTargeted;
    protected String sotrxFlag;
    protected String bpName;
    protected String compiereDocStatus;
    protected String hondaDocStatus;
    protected Integer first;
    protected Integer licensingDistrictId;
    protected String licensingDistrict;
    
    protected String passportNo;
    protected String companyRegNo;
    
    protected String unitNumber;
    protected Integer unitsPerPack;
    protected String productCode;
    protected Integer vehicleSpecsId;
    protected String natisApplicationDate;
    protected Timestamp processedDate;
    
    protected Timestamp natisRegistrationDate;
    protected Timestamp wholesaleDate;
    protected String natisControlNumber;
    protected String natisRegisterNumber;
    protected Integer natisModelNo;
    protected String natisModelDescription;
    
    protected Integer enginePower;
    protected Integer engineCapacity;
    protected Integer tare;
    protected Integer noOfWheels;
    
    protected Integer colourCodeMatchId;
	protected String colourName;
	protected String transmissionName;
	protected String yearName;
	
	protected String mainColourCode;
	protected String countryOfImpCode;
	protected String shippingInvNo;
	protected String estimatedTimeOfArrival;
	protected String colourCodeHES;
	
	protected String licensingDistrictDescription;
	protected String licensingDistrictCode;
	
	protected String bpAddress;
	protected String bpPostalAddress;
	
	protected String bpPostalCode;
	protected String bpCity;
	protected String bpRegionName;
	protected Integer bpRegionId;
	
	protected String releaseStatus;
	protected String releasedBy;
	protected String reservedStatus;
	protected Timestamp natisreleaseDate;
	
	protected String uAddress1;
	protected String uAddress2;
	protected String uPostalAddress;
	protected String uPostalCd;
	protected String uCity;
	protected String uRegion;
	
	protected String colourGroup;
	
	protected String modelMatching;
	
	protected boolean isCar;
    protected ArrayList showroomTrafficBean;
   
    protected ArrayList showroomTrafficList = new ArrayList();
    protected Integer sales;
    protected Integer traffic;
    protected Integer prospects;
    protected String modelName;
    protected Integer modelId;
    protected Integer trxId;
    protected Integer modelCode;
    protected Integer modelCodeId;
    protected String dealerCode;
    protected Integer dealerCodeId;
    protected Integer salesRepId;
    protected Boolean isAllocationWarehouse;
    protected String name2;
    protected Boolean canAlterOrder;
    protected Boolean canCreateOrder;
    protected Boolean canViewOrder;
    protected Timestamp wholeSaleDate;
    protected Integer orderlineCount;
    protected Integer orderlineInvoicedCount;
    protected JulianDate startDate;
    protected JulianDate endDate;
    protected Integer weekNo;
    protected Integer attributeSetInstance;
    protected String makeName;
    protected String financeOption;
  
    protected String newVinNumber;
    protected Integer attributesetinstanceIdFrom;
    protected Integer attributesetinstanceIdTo;
    protected ArrayList vinNumberList;
    protected Integer productIds[];
    protected String reservedBy;
    protected Boolean isTradeIn = Boolean.valueOf("true");
    protected String ETAFilter;
    protected BigDecimal orderGrandTotal;
    protected Integer orderIds[];
    
    
    protected Integer mImportVehiclesId;
	protected String natisModelNumber;
	protected Timestamp vehEstimateTimeOfArrival;
    protected String vehTare;
    protected String vehEngineCapacity;
    protected String vehNoOfWheels;
    protected String vehEnginePower;
    protected Boolean colourMatchingPresent;
    protected Boolean modelMatchingPresent;
    protected Boolean productPresent;
    protected String vehImportErrDesc;
    
    protected Integer modelMatchID;
    protected String natisModelDesc;
    protected String modelDesc;
    protected String trans;
    protected Boolean showActiveOnly;
    protected Integer updateAttrValueId;
    protected String active;
    protected String visible;
    protected Integer style;
    protected Integer design;
    protected Integer size;
    protected String clientName;
    
    protected Integer designAttributeValueId;
    protected Integer modelAttributeValueId;
    protected Integer sizeAttributeValueId;
    protected Integer colourAttributeValueId;
   
    
    protected BigDecimal endingBalance;
    protected String  endingBalanceAsString;
    protected BigDecimal amount;
    protected String trxType;
    protected BigDecimal amountGiven;
    protected BigDecimal amountRefunded;
    protected Integer cashBookId;
    protected Integer cashId;
    protected Integer cashLineId;
    protected String cashType;
    protected String cashTypeName;
    protected String cashBookName;
    protected String chequeNo;
    protected String creditCardType;
    protected String creditCardNumber;
    protected String creditCardNumberEncrypted;
    protected Integer creditCardExpMonth;
    protected String creditCardExpMonthName;
    protected Integer creditCardExpYear;
    protected Integer [] posOrderLineIds;
    protected Integer posId;
    protected String posName;
    protected String posDesc;
    protected String saleRepName;
    protected Integer priceListId;
    protected String priceListName;
    protected String paymentRule;
    protected Integer cashJournalId;
    protected String cashJournalName;
    protected String cashJournalDisc;
    protected String statmentDate;
    protected Timestamp dateAcct;
	protected BigDecimal invoiceAmount;
	protected BigDecimal differenceAmount;
	protected BigDecimal generalExpenseAmount;
	protected BigDecimal generalReceiptsAmount;
	protected BigDecimal bankAcctTransferAmount;
    protected BigDecimal transferAmount;
    protected Integer cashBookIds;
    protected BigDecimal beginingBalance;
    protected String beginingBalanceAsString;
    protected BigDecimal statementDifference;
    protected String keyword;
    protected String backOrder;
    protected String fieldName;
    protected String applicationName;
    protected String applicationWebContext;
    protected Integer storeId;
    protected Boolean isWebstoreFeatured;
	protected String brandName;
	protected String designName;
    protected String sizeName;
    protected String keyword1;
    protected String keyword2;
    protected String keyword3;
    protected String keyword4;
    protected String[] sizes;
    protected String[] colours;
    protected String imageLink;
    
    protected BigDecimal priceStandard;
    protected String currency;//
    
    protected  BigDecimal cashTotal;
    protected BigDecimal chequeTotal;
    protected BigDecimal cardTotal;
    protected BigDecimal tillGrandTotal;
    protected String purchasePriceList;
	protected String purchasePriceLimit;
	protected String purchasePriceStandard;
	protected String barCode;
	protected String salesPriceList;
	protected String salesPriceLimit;
	protected String salesPriceStandard;
	protected String reportType;
    protected Integer noOfProductsRequired;
    protected BigDecimal openingBalanceQty;
    protected BigDecimal qtyOfGoodsSold;
    protected BigDecimal qtyOfGoodsReceived;
    protected BigDecimal qtyOfGoodsReturned;
    protected BigDecimal closeingBalanceQty;
    protected BigDecimal qtyReturnedByCustomer;
    protected String qtyAndItem;
    protected String[] discountPercent;
    protected String [] actualPrice;
    protected FormFile file;
    protected FormFile logo;
    protected FormFile image;
    
    protected String userSurname;
    protected Integer countryId;
    protected String countryName;
    protected String countryCode;

    protected String newName;   
    protected String imageName;  
    protected ArrayList productsList;
    protected Double paymentByCash;
    protected String paymentMethod;
    protected Double paymentByCard;
    protected Double paymentByChq;
    protected Boolean isDeleteOldRecords=false;
    protected String isSales;
    protected Boolean isSOPriceList = false;
    protected String ifAdd;
    protected String productCategory;
    protected String productClassification;
    protected String trackingNo;
    protected String revenueRecognition;
    protected String cvv;
	protected String creditCardPayment;
	protected String shipmentMethod;
	protected Boolean shipTo;
	protected Boolean billTo;
	protected Integer chargeId;
	protected String chargeName;
	protected BigDecimal chargeAmount; 
	
    protected Integer revenueRecoId;
    
    protected int numberOfRequests;
    protected String userName;
    protected Integer requestId;
	protected Integer toUserId;
	protected String fromAddress;
	protected String toAddresses;
	protected String ccAddresses;
	protected String bccAddresses;
	protected String textMessage;
	protected String hostAddress;
	protected String priority;
	protected String title;
	protected Integer statusId;
    
    protected AttributeValuesPair attributeValuesPair;
    protected HashMap attributeValuesMap;
    protected Boolean isTextileProduct;
    protected String qtyFilter;
    protected String productType;

    
    protected String attributeSetName;
   
    protected String attributeName;
    protected ArrayList attributeValueList;
    protected String [] attributeValueIds;  
    protected Double totalActualPrice;
    protected String qtyNumberFilter;
    protected Integer taxCategoryId;
    protected String taxCategoryName;
    protected String vendorName;
    protected String timePeriod;
    protected String chartType;
    protected String salesGroup;
    protected BigDecimal taxRate;
    protected Integer accountId;    
    protected String dateRange;
    protected Boolean transferAllAmount;
    protected BigDecimal adjustmentAmount;
    protected BigDecimal differenceAmt;
    protected Boolean creatingFromOrder;
    protected String transferType;
    protected String priceQtyFilter;
    protected Boolean hasImage;
    protected String userPIN;
    protected Boolean isSelfService; 
    protected BigDecimal cardDifference;
    protected BigDecimal chequeDifference;
    protected String documentNote;
    protected String currencySymbole;
    protected String invoiceDate;
    protected Integer netDays;
    protected Integer daysDue;
    protected Timestamp dueDate;
    protected String discountDate;
    protected BigDecimal discountAmt;
    protected BigDecimal invoiceGrandTotal;
    protected BigDecimal paidAmt;
    protected BigDecimal openAmt;
    protected Integer currencyId;
    protected Integer paymentTermId;
    protected Integer invoiceScheduleId;
     protected BigDecimal userDiscount;
    
    protected BigDecimal invoicedAmt;
    protected BigDecimal pastDue91_plus;
    protected BigDecimal pastDue61_90;
    protected BigDecimal pastDue31_60;
    protected BigDecimal pastDue1_30;
    protected BigDecimal pastDue8_30;
    protected BigDecimal pastDue1_7;
    protected BigDecimal pastDueAmt;
    protected BigDecimal dueAmt;
    protected BigDecimal due0;
    protected BigDecimal due1_7;
    protected BigDecimal due8_30;
    protected BigDecimal due0_30;
    protected BigDecimal due31_60;
    protected BigDecimal due61_90;
    protected BigDecimal due91_PLUS;
    
   
    protected BigDecimal paymentAmt;
    protected BigDecimal overUnderPayment;
    protected BigDecimal writeOffAmt;
    protected String tenderType;
    protected String invoiceNo;
    protected BigDecimal creditUsed;
    protected BigDecimal creditLimit;
    protected BigDecimal revenue;
    protected String paymentTermName;
    protected Integer [] bpartnerIds;
    
    protected Integer blackListedId;
	protected String blackListedBankName;
	protected String blackListedChequeNo;
	
    protected Integer dunningId;
    protected String dunningName;
   
    protected Integer dunningLevelId;
    protected String dunningLevelName;
    protected String dunningPrintText;
    protected String dunningPrintNote;
    protected Integer BP_Group_ID ;
    protected String soCreditStatus;
    protected String bpfirstSale;
    protected String creditStatus;
    public String docBasisType;
    
    //protected String applicationType;
    protected BigDecimal amtMultiplier; 
    
 
    protected Integer commissionAmtId;
    protected Integer commissionRunId;
    protected Integer commissionLineId;
    protected BigDecimal convertedAmt;
    protected BigDecimal actualQty;
    protected BigDecimal commissionAmt;
    protected String commissionLineName;
    protected Integer commissionDetailId;
    protected String reference;
    protected Integer invoiceLineId;
    protected String info;
    protected BigDecimal actualAmt;
    protected String frequencyType;
    protected String periodAndCurrencyDesc;
    protected BigDecimal subtractAmt;
    protected BigDecimal qtyInventoryIn;
    protected BigDecimal qtyInventoryOut;
    
    protected String taxName;
    protected Integer taxId;
    protected Boolean isTaxExempted;
    protected Integer [] invoiceIds;
    protected Integer [] paymentIds;
    protected String paymentNo;
    protected BigDecimal taxedAmt;
    protected String trxDate;
	
    protected Integer creditMemoId;
    protected String creditMemoNumber;
    
    
    protected Integer inventoryLineId;
    protected Integer inventoryId;
    protected BigDecimal qtyBook;
    protected BigDecimal qtyCount;
    protected BigDecimal qtyCsv;
    
    protected String inventoryNo;
    protected String movementDate;
    
    protected Boolean afterDelivery;
    protected Boolean fixedDueDate;
    protected Boolean nextBusinessday;
    protected Integer fixedMonthDay;
    protected Integer fixedMonthOffset;
    protected Integer fiedMonthCutoff;
    protected String toBeShipped;
    protected Integer discountDay1;
    protected Integer discountDay2;
    protected BigDecimal discountAmt1;
    protected BigDecimal discountAmt2;
    protected BigDecimal allocatedAmount;
    protected BigDecimal bookQtyValue;
    protected BigDecimal countQtyValue;
    
    protected PaymentBean paymentBeanIndex;
    
    protected BigDecimal [] allocateAmount;
    
	protected BigDecimal paymentAllocatedAmt;
	protected BigDecimal AvailableAmt;
	protected Integer [] inventoryIds;
	protected Integer [] inventoryLineIds;
	protected String historyType;
   
	protected String accountingValues;
	 
	protected String fullDetails;
	protected BigDecimal unitPrice;
	protected BigDecimal unitPurchasePrice;
	 
	 // Additional fields required for user registration
	protected String company;
	protected String industry;
	protected String comments;
	
	protected BigDecimal roundOffFactor;
	protected Integer stdPrecision;
	protected String isoCode;
	protected String curSymbol;
	protected String isSalesReport;
	protected String receiptFooterMsg;
	protected String branch;
	protected String taxNo;
	
	/** Organisation **/
	protected Integer   orgId;
	
    /** Common field used for searching **/
    protected String    searchText;
    
    protected Integer webMenuId;
	   
	public Boolean getIsActive() 
	{
		return isActive;
	}
	public void setIsActive(Boolean isActive) 
	{
		this.isActive = isActive;
	}
	
	/**
	 * @param searchText search term
	 */
	public void setSearchText(String searchText)
	{
	    this.searchText = searchText;
	}
	
	/**
	 * @return search term
	 */
	public String getSearchText()
	{
	    return this.searchText;
	}
	
	/**
	 * @return Organisation
	 */
    public Integer getOrgId()
    {
        return orgId;
    }
    
    /**
     * @param orgId Organisation
     */
    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }
  
}

    


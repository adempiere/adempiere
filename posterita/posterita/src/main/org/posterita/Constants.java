/*
 *
 * Copyright (c) 2005 UDI Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * UDI Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with UDI.
 *
 * UDI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UDI SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Created on Jul 6, 2005 by vishee
 */

package org.posterita;

import org.compiere.model.MPayment;

public class Constants
{   
	public static final String ACCESSIBLE_ORGS = "accessibleOrgs";
    public static final String ACTION = "action";
    public static final String ADD_ORDERLINE_ID = "addOrderLineId";
    public static final String ADMIN = "admin";
    public static final String ADMIN_USER_ID = "adminUserId";
    public static final String ALL_BANKS = "allBanks";
    public static final String ALL_CUSTOMERS = "allCustomers";
    public static final String ALL_ORDERS = "allOrders";
    public static final String ALL_ORGS = "allOrgs";
    public static final String ALL_ROLES = "allRoles";
    public static final String ALL_STOCK_REPORT = "allStockReport";    
    public static final String ALL_USERS = "allUsers";
    public static final String ALL_WAREHOUSES="allwarehouses";
    public static final String ALL_WEEKS = "allWeeks";
    public static final String ALLBUSINESSPARTNERS = "allBp"; 
    public static final String ALLOCATION = "allocation";
    public static final String ALLOCATION_DATE = "allocationDate";       
    public static final String ALLOCATION_ID = "allocationId";
    public static final String ALLOCATION_LINES="allocationsLines";	
    public static final String ALLOCATION_LIST = "allocationList";
    public static final String ALLOCATION_ORDERS = "allocationOrders";
    
    
    public static final String ALLOCATION_REPORT = "allocationReport";
    public static final String ALLOCATION_STOCK = "Allocation Stock";
    public static final String ALLWAREHOUSES="allwarehouses";
   
    public static final String APP_CRM = "crm";
    public static final String APP_DCS = "dcs";    
    public static final String APP_IMMOBILIER_WEBSTORE = "ImmobilierWebstore";    
    public static final String APP_NAME = "appName";    
    //APPLICATION NAME
    public static final String APP_POS = "pos";
    public static final String APP_WEBSTORE = "webstore";
    public static final String APP_WEBSTORE_ADMIN = "webstoreadmin";
    public static final String APPROVE = "APPROVE";
    public static final String ASSIGNED_ROLES = "assignedRoles";
    public static final String ATTRIBUTE_LIST = "attributeList";
    public static final String ATTRIBUTE_NAME = "atttributeName";
    public static final String ATTRIBUTE_SET = "attributeSet";
    public static final String ATTRIBUTE_SET_ID = "attributeSetId";
    public static final String ATTRIBUTE_VALUES = "attributeValues";
    public static final String ATTRIBUTE_VALUES_LIST = "attributeValuesList";
    
    
    public static final int ATTRIBUTE_YEAR_MIN_VALUE = 2000;
    public static final String ATTRIBUTESET = "AttributeSet";
    public static final String ATTRIBUTESETINSTANCE_ID = "attributeSetInstanceId";
    public static final String AUCTION_MODULE="Auction Module";    
    public static final String AVAILABLE_COLOURS = "availableColours";
    public static final String AVAILABLE_COLOURS_SIZES = "availableColoursSizes";
    public static final String AVAILABLE_SIZES = "availableSizes";

    
    public static final String AVAILABLE_VIN = "availableVin";
    public static final String B_PARTNER_ID = "bPartnerId";
    public static final String BACK_ORDER = "backOrder"; 
    public static final String BANK = "bank";
    public static final String BANKS = "banks";
    public static final String BANK_ACCOUNTS = "bankAccounts";
    public static final String BASIC_DETAILS = "basicDetails";
    public static final String BARCODE_CART = "barcodeCart";
    public static final String BARCODE_CART_ITEMS = "barcodeCartItems";
    public static final String MMOVEMENT_CART = "mmovementCart";
    public static final String MMOVEMENT_CART_ITEMS = "mmovementCartItems";
    public static final String BP_LIST = "bpList";
    public static final String BPARTNER = "bPartner";
    public static final String BRAND_ATTRIBUTE_VALUES="brandAttributeValues";
    public static final String BREADCRUMB = "breadcrumb";
    public static final String BUSINESSPARTNER = "businessPartner";
    public static final String BUSINESSPARTNER_ORDER_DETAILS ="bPartnerOrderDetails";
    public static final String BUSINESSPARTNER_TRX_DETAILS = "businessPartnerTrxdetails";
    public static final String BUSINESSPARTNERSDETAILS = "bPartnerDetails";    
    public static final String CAN_TRADE_IN = "canTradeIn";
    public static final String CANCEL_DO = "cancelDO";    
    public static final String CANCEL_DT = "cancelDT";    
    public static final String CANCEL_INVOICE = "cancelInvoice";   
    public static final String CANCEL_SCO = "cancelSCO";
    public static final String CARD = "card";   
    public static final String CASH = "cash";
    public static final String CASH_BOOK = "cashBook";    
    public static final String CASH_DETAILS = "cashDetails";
        
    public static final String CASH_LINE_DETAILS = "cashLineDetails";
    public static final String CASH_SUMMARY = "cashSummary";
    public static final String CERTIFICATE = "certificate";
    public static final String CHANGE = "change";
    public static final String CHECKED_PRODUCTS = "checkedProducts";
    public static final String CHECKING_ACCOUNT_TYPE = "C";
    public static final String CHECKOUT_ACTION = "/CheckoutAction";
    public static final String CHOOSE = "Choose";
    public static final String CHOOSE_SHIPPING_ADDRESS = "chooseShippingAddress";
    
    public static final String CHOOSE_USER = "chooseUser";
    public static final String CHOOSE_VIN = "chooseVin";
    public static final String CLIENT_ID="client";
    public static final String CLIENT_NAME="clientName";
    public static final Integer COGS 	= Integer.valueOf(51100);
    public static final String COLOUR_ATTRIBUTE_VALUE_ID = "colourAttributeValueId";
    public static final String COLOUR_ATTRIBUTE_VALUES = "colourAttributeValues";
    public static final String COLOURCODE_MATCHING = "colourCodeMatching";
    public static final String COMPLETED_INVOICE = "completedInvoice";
    public static final String COMPLETED_POS_ORDER = "completedPOSOrder";
    public static final String COMPLEX_COMMAND = "complexCommand";
    public static final String CONTENT = "content";
    public static final String SUBCLASSIFICATION = "subClassification";
    public static final String COUNTRY_CLASSIFICATION = "countryClassification";
    public static final String BRAND_CLASSIFICATION = "brandClassification";
    public static final String COUNTRIES = "countries";
    public static final String CREATE = "create";
    public static final String CREATE_BANKACCOUNT = "createBankAccount";
    public static final String CREATE_DRAFTED_ORDER = "createDraftedOrder";
    public static final String CREATE_NEW_CAR = "createNewCar";
    public static final String CREATE_PRODUCT_ATTRIBUTE_VALUE_ACTION = "/CreateProductAttributeValueAction";
    public static final String CREATE_PRODUCT_ATTRIBUTE_VALUE_FORM = "CreateProductAttributeValueForm";
    public static final String CREATE_USED_CAR = "createUsedCar";
    public static final String CREATE_WEBSTORE_CUSTOMER_ACTION = "/CreateWebstoreCustomerAction";
    public static final String CREATEPRODUCTS = "createProducts";   
    public static final String CREATING_FROM_ORDER = "creatingFromOrder";
    public static final String CREDITCARDTYPE_AMEX = "Amex";
    public static final String CREDITCARDTYPE_DISCOVER = "Discover";
    public static final String CREDITCARDTYPE_MASTERCARD = "MasterCard";
    public static final String CREDITCARDTYPE_VISA = "Visa";
    public static final String CRM_REQUEST_USERS = "crmUsers";
    public static final String CSV = "csv";
    public static final String CSV_FILE = "csvFile";    
    public static final String CSV_FILE2 = "csvFile2";
    public static final String PDF_FILE = "pdfFile"; 
    
    public static final String CURRENCIES = "currencies";
    public static final String CURRENCY_SYMBOLE = "currencySymbole";
    public static final String CURRENT_PARTIAL_POS_ORDER_ID = "currentPartialPOSOrderId";
    public static final String CURRENT_POS_ORDER="currentPOSOrder";
    public static final String CURRENT_POS_ORDER_ID = "currentPOSOrderId";
    public static final String CURRENT_PRODUCT = "currentProduct";    
    public static final String CURRENT_REQUEST = "currentRequest";
    public static final String CURRENT_TILL_AMOUNT = "currentTillAmount";
    public static final String CURRENT_TILL_AMOUNT_POS = "currentTillAmountPOS";
    public static final String CURRENT_YEAR = "currentYear";
    public static final String CUSTOM_DATE_RANGE = "customDateRange";
    public static final String CUSTOMER = "customer";
    public static final String CUSTOMER_CREATED="CustomerCreated";
    public static final String CUSTOMER_DETAILS = "customerDetails";
    
    public static final String CUSTOMER_ID = "customerId";
    public static final String CUSTOMER_RETURN_ORDER = "customerReturnOrder";
    public static final String CUSTOMER_RETURN_ORDER_ID = "customerReturnOrderId";
    
    public static final String CUSTOMER_RETURN_ORDER_LINES = "customerReturnOrderLine";
    public static final String CUSTOMER_RETURN_ORDER_SHOPPING_CART = "customerReturnOrderShoppingCart";
    public static final String CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS = "customerReturnOrderShoppingCartItems";
    public static final String DEALER_COUNTER_REF = "dealerCounterRef";
    public static final String DEALER_DETAILS = "dealerDetails";
    public static final String DEALER_REF = "dealerRef";
    
    public static final String DEALERCODE_LIST = "dealerCodeList";
    public static final String DEALERS = "dealers";
    public static final String DEALERS_DETAIL = "dealersDetail";
    
    
    public static final String DELETE_ORDER_TYPE = "deleteOrderType";
    public static final String DELIVERY = "Delivery";
    public static final String DESCRIPTION ="description";
    public static final String DESIGN_ATTRIBUTE_VALUES="designAttributeValues";
    public static final String DISPLAY_REPORT = "displayReport";
    public static final String DMS = "DMS";
    public static final String DOC_STATUS = "docStatus";
    public static final String DOC_NO = "docNo";
    
    public static final String DOCUMENT_HISTORY = "documentHistory";
    public static final String DOCUMENT_PDF = "documentPDF";
    public static final String DRAFT_INVOICE = "draftInvoice";
    
    public static final String EDITWAREHOUSE="editWarehouse";
    public static final String EMAIL = "email";
    
    //Email specific
	public static final String EMAIL_FOOTER= "email.footer";
    public static final String EMAIL_REGARDS = "emailRegards";
    public static final String EMAIL_SIGNIN_INFO = "signin.info";  
    public static final String END_OF_THE_DAY_DETAILS = "endOfTheDayDetails";
    public static final String ERROR_TITLE = "errorTitle";
    public static final String ERRORS = "errors";
    public static final String ETA_LIST = "ETAList";    
    //public static final String EURO_SIGN = "&#8364";
    public static final String EXISTING_BAR_CODE = "existingBarCode";   
    public static final String FILE_FOR_UPDATE = "fileForUpdate";
    public static final String FINISHED_AL ="finishedAL";
    public static final String FINISHED_DO = "finishedDO";
    public static final String FINISHED_DT ="finishedDT";
    
    public static final String FINISHED_MRO = "finishedMRO";
    
    public static final String FINISHED_NR = "finishedNR";
    public static final String FINISHED_SCO = "finishedSCO";
    public static final String FIXED_DATE_RANGE = "fixedDateRange";
    public static final String FREE_STOCK = "Free Stock";
    public static final String FROM_DATE = "fromDate";
    public static final String FULL_DETAILS = "fullDetails";
    public static final String FULL_IMAGE = "fullImage";
    public static final String FULLIMAGE = "fullImage";
    public static final String GENERATE_INVOICE = "generateInvoice";
    public static final String GENERATE_INVOICE_ACTION = "Invoice";
    public static final String GENERATE_INVOICE_PAYMENT_ACTION = "InvoicePayment";
    public static final String GENERATE_WEBSTORE_INVOICE = "generateWebstoreInvoice";
    public static final String GET_CREDIT_CARD_DETAILS = "getCreditCardDetails";
    public static final String GET_LOGO="getLogo";
    
    public static final String GET_PRODUCT_CATEGORY_ACTION = "/GetProductCategory";
    public static final String GOING_RATE_REPORT = "goingRateReport";
    public static final String GOODS_RECEIVE_NOTE = "goodsReceiveNote";
    public static final String GOODS_RECEIVE_NOTE_ID = "goodsReceiveNoteId";  
    public static final String GOODS_RECEIVE_NOTE_LINES = "goodsReceiveNoteLines";
    public static final String GOODS_RECEIVE_NOTE_SHOPPING_CART = "goodsReceiveNoteShoppingCart";
    public static final String GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS = "goodsReceiveNoteShoppingCartItems";
    public static final String GOODS_RETURN_NOTE = "goodsReturnNote";
    public static final String GOODS_RETURN_NOTE_ID = "goodsReturnNoteId";
    
    public static final String GOODS_RETURN_NOTE_LINES = "goodsReturnNoteLines";
    public static final String GOODS_RETURN_NOTE_SHOPPING_CART = "goodsReturnNoteShoppingCart";
    public static final String GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS = "goodsReturnNoteShoppingCartItems";
    public static final String GRAND_TOTAL = "grandTotal";
    public static final String HIDDEN_STOCK = "Hidden Stock";
    public static final String HOMEPAGE_PRODUCTS = "homePageProducts";
    public static final String HTML = "html";
    public static final String IMAGE_NAME = "ImageName";
    public static final String IMAGES_DIRECTORY = "images/";
    public static final String IMPORTED_VEHICLES = "importedVehicles";
    public static final String InitPriceListAction = "/InitPriceListAction"; 
    public static final String INSERT_VIN = "insertVin";
    public static final String INVOICE_ACTION_CANCEL = "Cancel";
    public static final String INVOICE_ACTION_COMPLETE = "Complete";
    public static final String INVOICE_ACTION_DELETE = "Delete";
    public static final String INVOICE_ACTION_PAID = "Paid";    
    public static final String INVOICE_ACTION_PAID_AND_SHIP = "Paid and Ship";    
    public static final String INVOICE_HISTORY = "invoiceHistory";
    public static final String INVOICE_ID = "invoiceId";
    public static final String INVENTORY_CART = "inventoryCart";
    public static final String INVENTORY_CART_ITEMS = "inventoryCartItems";
   
    public static final String INVOICE_PAID = "Invoice & Pay";    
    public static final String INVOICE_PAID_SHIP = "Inv/Paid/Ship";    
    public static final String INVOICE_TAX = "invoiceTax";
    public static final String IS_PAID = "isPaid";
    public static final String KEYWORD1_LINK_LIST = "keyword1LinkList";
    public static final String KEYWORD2_LINK_LIST = "keyword2LinkList";
    
    public static final String KIA = "kia";
    public static final String LEFT_MENUS = "leftMenus";  
    public static final String LICENSING_AREA="licensingAreas";
    public static final String LICENSING_INFORMATION = "licensingInformation";
    public static final String APP_LICENSING_INFORMATION = "appLicensingInformation";
    public static final String LOCATORS = "locators";
    public static final String LOGIN = "login";
    
    public static final String LOGIN_LINK = "link";
    public static final String MAKE_ATTRIBUTE_VALUES = "makeAttributeValues";
    public static final String MAX_SOLD_ITEMS = "maxSoldItems";    
    public static final String ME = "me";
    public static final String ME_LOCATION = "meLocation";
    public static final String MENU = "menu";    
    public static final String MENU_ITEMS = "menuItems";
  
    public static final String MENU_LIST = "menuList";
    public static final String MENUS = "menus";
    public static final String MESSAGE = "message";
    public static final String MESSAGE_LOG_BEAN = "messageLogBean";
    public static final String MESSAGE_LOGS = "messageLogs";    
    public static final String MIN_SOLD_ITEMS = "minSoldItems";    
    public static final String MINOUT = "minout";
    public static final String MINOUT_ACTION_CANCEL = "Cancel";
    public static final String MINOUT_ACTION_COMPLETE = "Complete";
    public static final String MINOUT_ACTION_DELETE = "Delete";
    
    public static final String MINOUT_ACTION_RECEIVE = "Receive";
    public static final String MINOUT_ACTION_SHIP = "Ship";
    public static final String MINOUT_LINES_COLLECTION = "minOutLinesCollection";
    public static final String MINVOICE = "minvoice";
    public static final String MINVOICE_LINES_COLLECTION = "invoiceLinesCollection";
    public static final String MMOVEMENT_LOCATOR = "toLocator";
    public static final String MMOVEMENT_LOCATORS = "organisationLocators";
    
    public static final String MMOVEMENT_PRODUCT = "materialMovementProduct";
    public static final String MMOVEMENT_PRODUCTS = "organisationProducts";
    public static final String MMOVEMENT_WAREHOUSES = "organisationWarehouses";
    
    public static final String MODEL_ATTRIBUTE_VALUE_ID = "modelAttributeValueId";
    public static final String MODEL_ATTRIBUTE_VALUES="modelAttributeValues";
    public static final String MODEL_MATCHING_MODELS = "modelMatchingModels";
    public static final String MODEL_MATCHING_YEARS = "modelMatchingYears";    
    public static final String MODELCODE_LIST = "modelCodeList";
    public static final String MODELGROUP_ATTRIBUTE_VALUES = "modelGroupAttributeValues";
    public static final String MODELMATCH = "modelmatch";
    public static final String MONTH_SALES = "monthSales";
    public static final String MORDER =  "morder";
    public static final String MORDER_LINES_COLLECTION = "orderLinesCollection";
    public static final String MPAYMENT = "payment";
    public static final String MY_RECEIVED_MESSAGE = "myReceivedMessage";
    public static final String MY_RECEIVED_MESSAGES = "myReceivedMessages";
    public static final String MY_SENT_MESSAGES = "mySentMessages";
    public static final String MY_STOCKS = "myStocks";
    public static final String NAAMSA_REPORT = "Naamsa Report";    
    public static final String NAAMSAINFO = "naamsaInfo";    
    public static final String NATIS_RELEASE_INFO = "natisReleaseInfo";
    public static final String NATIS_XML_ENCODING = "ISO-8859-1";
    public static final String NEW_PRODUCT = "newProduct";
    public static final String NO_CHAR = "N";
    public static final String NO_OF_VEHICLES_SOLD = "noOfVehicleSold";
    public static final String OLD_MODELMATCH = "oldmodelmatch";
    public static final String OPEN_CASH_BOOK = "openCashBook";
    public static final String OPENED_CASH_BOOK = "openedCashBook";
    public static final String ORDER_ACTION_ACCEPT = "Accept";
    public static final String ORDER_ACTION_ACCEPT_WO="Create";
    public static final String ORDER_ACTION_CANCEL = "Cancel";
    public static final String ORDER_ACTION_CLOSE = "Close";
    public static final String ORDER_ACTION_COMPLETE = "Complete";
    public static final String ORDER_ACTION_CONFIRM = "Confirm";
    public static final String ORDER_ACTION_DELETE = "Delete";
    public static final String ORDER_ACTION_GENERATE_ORDER = "Generate Order";
    public static final String ORDER_ACTION_PREPARE = "Reserve Stock";    
    
    public static final String ORDER_ACTION_PREPARE_SCO = "Prepare";
    public static final String ORDER_ACTION_PREPARE_SCO_ORDER="Prepare Order";
    public static final String ORDER_ACTION_REJECT = "Reject";
    public static final String ORDER_ACTION_SAVE = "Save";
    public static final String ORDER_ACTION_SPLIT = "Split";
    
    public static final String ORDER_ACTION_SUBMIT = "Submit";
    public static final String ORDER_BEAN = "orderBean";
    public static final String ORDER_COLLECTION = "orderCollection";	
    public static final String ORDER_DETAILS = "orderDetails";	
    public static final String ORDER_HISTORY = "orderHistory";
    public static final String ORDER_ID = "orderId";    
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_LINE_BEAN = "orderLineBean";
    public static final String ORDER_LINE_ID = "orderLineId";
    public static final String ORDER_LINES = "orderLines";
    public static final String ORDER_LINES_FOR_ORDER = "orderLinesForOrder";
    public static final String ORDER_TAX = "orderTax";
    public static final String ORDER_TYPE = "orderType";
    public static final String ORDER_TYPE_BULK = "bulk";
    
    public static final String ORDER_TYPE_SIMPLE = "simple";
    
    public static final String ORDER_TYPES = "orderTypes";
    public static final String ORG_FOR_RESERVED_PRODUCTS="orgForReservedProducts";
    public static final String ORG_ID = "orgId";
    public static final String ORG_USERS = "orgUsers";
    public static final String ORGANISATION = "organisation";
    public static final String ORGANISATION_VALUES="organisationValues";
    public static final String ORGS = "orgs";
    public static final String ORGTYPE = "orgType";
    
    public static final String PAID_INVOICE = "paidInvoice";
    public static final String PAIR_VIN_NUMBERS = "pairVinNos";
   
    public static final String PARTIAL_ORDER_SHOPPING_CART = "partialOrderShoppingCart";
    public static final String PARTIAL_ORDER_SHOPPING_CART_ITEMS = "partialOrderShoppingCartItems";
    public static final String PARTIAL_POS_ORDER = "partialPosOrder";
    
    public static final String PARTIAL_POS_ORDER_LINES = "partialPOSOrderlines";
    public static final String PASSWORD = "password";
    public static final String PATH_CREATE_TAMAK_ORDER_ACTION = "/CreateTamakOrderAction";
    public static final String PATH_VIEW_ORDER_ACTION = "/ViewOrderAction";
    public static final String PAYMENT_ACTION_CANCEL = "Cancel";
    public static final String PAYMENT_ACTION_COMPLETE = "Complete";
    public static final String PAYMENT_BY_CARD = "paymentByCard";
    
    public static final String PAYMENT_BY_CASH = "paymentByCash";
    public static final String PAYMENT_BY_CHEQUE = "paymentByCheque";
    
    public static final String PAYMENT_HISTORY = "paymentHistory";
	public static final String PAYMENT_RULE_CARD="Card";
	public static final String PAYMENT_RULE_CASH="Cash";
	public static final String PAYMENT_RULE_CHEQUE="Cheque";
	public static final String PAYMENT_RULE_CREDIT="Credit";
	public static final String PAYMENT_RULE_MIXED="Mixed";
	public static final String PAYMENT_RULES = "paymentRules";
	public static final String PAYMENT_TYPE = "paymentType";
    public static final String PDF = "pdf";
    public static final String POOL_ORDERLINES = "poolOrderLines";
    public static final String POS_GENERAL_STOCK = "posGeneralStock";
    public static final String CARD_AMT_TENDERED = "cardAmtTendered";
    public static final String CHEQUE_AMT_TENDERED = "chequeAmtTendered";
    
    public static final String POS_HISTORY = "posHistory";
    
    public static final String POS_INFO = "posInfo";
    
    public static final String POS_ORDER = "posOrder";
    public static final String POS_ORDER_CUSTOMER_COMPULSORY = "posOrderCustomerCompulsory";
    public static final String POS_ORDER_DOC_NUMBER = "POSOrderDocumentNumber";
    public static final String POS_ORDER_ID = "posOrderId";
    public static final String POS_ORDER_LINES = "posOrderLines";
    public static final String POS_ORDER_WITHOUT_ADVANCED = "posOrderWithoutAdvanced";
    public static final String POS_PRODUCTS_FOR_UPDATE = "posProductsForUpdate";
    public static final String POS_SALES_PRICE_LIST = "posSalesPriceList";
    
    public static final String POS_STOCK = "posStock";
    public static final String POSTERMINAL = "POSTerminal";
    public static final String POSTERMINALS = "POSTerminals";
    public static final String PRICE = "price";
        
    public static final String PRICE_LIST = "priceList";
    public static final String PRICE_LIST_VERSION = "priceListVersion";
    public static final String PRICE_LIST_VERSION_ID = "priceListVersionId";
    public static final String PriceListForm = "PriceListForm";
    
    public static final String PRICEQTY_FILTER = "priceQtyFilter";
    public static final String PROCESS_CLASS = "process.class";
    public static final String PROCESS_NAME = "process.name";
    
    public static final String PRODUCT = "product";
    public static final String PRODUCT_ATTRIBUTE_LIST = "productAttributeList";
    public static final String PRODUCT_ATTRIBUTE_SET = "productAttributeSet";
    public static final String PRODUCT_BRANDS = "productBrands";
    public static final String PRODUCT_CART = "productCart";
    public static final String PRODUCT_CART_ITEMS = "productCartItems";
    public static final String PRODUCT_CATEGORY_ID = "productCategoryId";
    //public static final String WEBSTORE_SHIPPING_ADDR = "webstoreShippingAddr";
    //public static final String WEBSTORE_BILLING_ADDR = "webstoreBillingAddr";
    public static final String PRODUCT_CLASSIFICATION ="productClassification";
    public static final String PRODUCT_CREATED = "productsCreated";
    public static final String PRODUCT_DETAIL_INFO = "productDetailInfo";
    public static final String PRODUCT_DETAILS = "productDetails";
    
    public static final String PRODUCT_FILTER_BEAN = "productFilterBean";
    
    public static final String PRODUCT_ID = "productId";
    
    public static final String PRODUCT_IDS = "productIds";
    public static final String PRODUCT_IMAGE = "productImage";
    public static final String PRODUCT_IMAGE_BEAN = "productImageBean";
    public static final String PRODUCT_INFO = "productInfo";
    public static final String PRODUCT_KEYWORDS = "productKeywords";
    
    public static final String PRODUCT_LINES = "productLines";
    public static final String PRODUCT_LIST = "productList";
    public static final String PRODUCT_SALES_DETAILS = "productSalesDetails";
    public static final String PRODUCT_SALES_SUMMARY = "productSalesSummary";
    public static final String PRODUCTS = "products";
    public static final String PRODUCTS_IN_STOCK = "productsInStock";
    public static final Integer PROFIT_MARGIN   = Integer.valueOf(99999);
    public static final String PROGRESS_DO = "progressDO";
    public static final String PROGRESS_DT = "progressDT";
   public static final String PROGRESS_NR = "progressNR";
    public static final String PROGRESS_SCO = "progressSCO";
    public static final String PROGRESS_TI = "progressTI";
    public static final String PROJECT_NAME = "project.name";
    public static final String PURCHASE_PRICELISTS = "purchase.pricelists";
    public static final String QUANTITY = "quantity";
    public static final String QUICK_POS_ORDER = "quickPosOrder";
    public static final String RECEIPT_HISTORY = "receiptHistory";
    public static final String RECEIVED_MESSAGE = "receivedMessage";
    public static final String REGION_VALUES = "regionValues";
    public static final String REGIONS = "regions";
    
    public static final String RELATED_PRODUCT_IMAGES = "relatedProductImages";
    public static final String REPORT_URL = "reportURL";
    public static final String REPRICE_ORDER_TYPE ="repriceOrderType";
    
    public static final String REQUEST_PRIORITIES = "requestPriorities";
    public static final String REQUEST_STATUS = "requestStatus";
    // --> CRM
    public static final String REQUESTS_SUMMARY = "requestsSummary";
    public static final String RESERVE_STATUS_VALUES = "reserveStatusValues";
    public static final String RETAIL_REPORT = "retailReport";
    public static final String REVENUE_RECOGNITION = "Revenue Recognition";
    public static final String ROLE = "role";
    public static final String ROLE_MENUS = "roleMenus";
    public static final String ROLES = "roles";
    
    public static final String SALES_ANALYSIS = "salesAnalysis";
    public static final String SALES_REP = "salesRep";
    public static final String SALES_REPORT = "salesReport";
    public static final String SALESGROUP_LIST = "salesGroupList";
    public static final String SALES_PRICELISTS = "sales.pricelists";
    public static final String SAVING_ACCOUNT_TYPE = "S";
    public static final String SCO_FILTER_BEAN = "SCOFilterBean";
    public static final String SCO_PRODUCT_LIST = "SCO Product List";
    public static final String SEARCH_VIN_NUMBERS="searchVinNumbers";
    public static final String SEARCH_VIN_NUMBERS_INQUIRY="searchVinNumberForInquiry";
    public static final String SELECTED_PRODUCTS_FOR_BULK_ORDER = "selectedProductsForBulkOrder";
    public static final String SELECTED_PRODUCTS_FOR_SIMPLE_ORDER = "selectedProductsForSimpleOrder";
    public static final String SENT_MESSAGE = "sentMessage";
    public static final String SHIPMENT_HISTORY = "shipmentHistory";
    public static final String SHIPPER_LIST = "shipperList";
    public static final String SHOPPING_CART = "shoppingCart";
    public static final String SHOPPING_CART_ITEMS = "shoppingCartItems";
    public static final String CHECKOUT_BEAN = "checkoutBean";
    public static final String SHOPPING_GRN_CART = "shoppingGRNCart";
    public static final String SHOPPING_GRN_CART_ITEMS = "shoppingGRNCartItems";
    public static final String SHOPPING_ORDER_CART = "shoppingOrderCart";
    public static final String SHOPPING_ORDER_CART_ITEMS = "shoppingOrderCartItems";
    public static final String SIMPLE_COMMAND = "simpleCommand";
    
    public static final String SIMPLE_PRODUCT_CART = "simpleProductCart";
    public static final String SIZE_ATTRIBUTE_VALUES="sizeAttributeValues";
    public static final String SPLIT_ORDER_PROCESS = "splitOrderProcess";
    public static final String STATUS = "status";
    public static final String STATUS_OFF = "OFF";
    
    public static final String STATUS_ON = "ON";
    public static final String STATUS_VALUES = "statusValues";
    public static final String STOCK_HISTORY_REPORT = "stockHistoryReport";
    
    public static final String STOCK_MOVEMENT = "stockMovement";
    public static final String SUB_MENU_ITEMS = "subMenuItems";
    public static final String SUB_MENU_NAME = "subMenuName";
    public static final String SUBJECT = "subject";
    public static final String SUB_TOTAL = "subTotal";
    public static final String SUCCESS = "success";
    public static final String SUCCESS_CREATE_CUSTOMER = "successCreateCustomer";
    public static final String SUPERUSER = "superUser";
    public static final String SWAP_PRODUCT_ID = "swapProductId";
    public static final String SWAP_VIN = "swapVin";
    public static final String SWAP_VIN_NOS_ACTION = "Save";
    public static final String SWAP_VIN_NUMBER_BEAN = "swapVinNumberBean";
    public static final String TABULAR_REPORT_DATA = "tabularReportData";
    
    public static final String TABULAR_REPORT_DATA2 = "tabularReport2";
    public static final String TABULAR_REPORT_SUBTITLE = "tabularReportSubtitle";
    public static final String TABULAR_REPORT_TITLE = "tabularReportTitle";
    public static final String TARGET_SALES = "targetSales";
    public static final String TAX_CATEGORY_ID = "taxCategoryId";
   
    public static final Integer TAX_CREDIT          = Integer.valueOf(12610);
    public static final Integer TAX_DUE                 = Integer.valueOf(21610);
    public static final String THUMBNAIL = "thumbnail";
    public static final String TIME_REMAINING = "timeRemaining";
    public static final String TO_DATE = "toDate";
    public static final String TOP_MENUS = "topMenus";
    public static final String MAIN_MENUS = "mainMenus";
    public static final String TOTAL_LINES = "totalLines";
    public static final String TRADE_IN_LIST = "tradeInList";
    public static final String TRADE_IN_PRODUCT_ID = "tradeInProductId";
    public static final Integer TRADE_REVENUE   = Integer.valueOf(41000);
    public static final String TRAFFIC_LIST = "trafficList";
    public static final String TRX_ATTRIBUTE_VALUE_ID = "trxAttributeValueId";
    public static final String TRX_ATTRIBUTE_VALUES="trxAttributeValues";
    public static final String [] TSHIRT_SIZE={"S","M","XL"};
    public static final String UDIADMIN = "udiadmin";   
    public static final String UDIADMINAUTO = "udiadminauto";
    public static final String UDIADMINBIKE = "udiadminbike";
    public static final String UNFINISHED_AL = "unfinishedAL";
    
    public static final String UNFINISHED_DO = "unfinishedDO";
    public static final String UNFINISHED_DT = "unfinishedDT";
    public static final String UNFINISHED_INV = "unfinishedInvoice";
    public static final String UNFINISHED_NR = "unfinishedNR";
    public static final String UNFINISHED_SCO = "unfinishedSCO";
    public static final String UNFINISHED_WO = "unfinishedWO";
    public static final String UNIMPORTED_VEHICLES = "unimportedVehicles";
    public static final String UNPAID_INVOICE = "unPaidInvoice";
    public static final String UPDATE_PRODUCT_DETAILS = "updateProductDetails";
    public static final String USED_CAR_PURCHASE_ORDER = "usedCarPurchaseOrder";
    public static final String USER_DETAILS = "userDetails";
    public static final String VEHICLE_DETAILS = "vehicleDetails";
    public static final String VEHICLES = "vehicles";
    public static final String VENDOR_DETAILS = "vendorDetails";
    public static final String BLACKLISTED_DETAILS = "blackListedDetails";
    public static final String BLACKLISTED_LISTS="blackListedList";
    
    
    public static final String VENDOR_LIST = "vendorList";
    public static final String VIEW_ALL_ALLOCATIONS = "viewAllAllocations";
    public static final String VIEW_INVOICE_DETAILS="view.invoice.detail";
    public static final String VIEW_MINOUT = "viewMinOut";
    public static final String VIEW_MY_SENT_MESSAGES="viewMySentMessages";
    public static final String VIEW_PAYMENT = "viewPayment";
    public static final String VIEW_POS_ORDER_ACTION = "/ViewPOSOrderAction";
    
    public static final String VIEW_POS_PRODUCTS = "viewPOSProducts";
    
      public static final String VIEW_SHOPPING_CART = "viewShoppingCart";
    public static final String VIEW_VIN_NUMBER_DETAILS = "viewVinNumberDetails";
    public static final String VIN_NOS = "vinNos";
    
    public static final String VIN_NUMBER_FROM = "vinNumberFrom";
    public static final String WAREHOUSE="warehouse";
    public static final String WAREHOUSE_NAMES = "warehouseNames";
    public static final String WAREHOUSE_TYPES = "warehouseTypes";
    public static final String WAREHOUSEDETAILS="warehouseDetails";
    
    public static final String WAREHOUSES = "warehouses";
    public static final String WEB_APPLICATIONS = "WebApplications";
    public static final String WEB_DOCUMENT_HEADER_BEAN = "webDocumentHeaderBean";
    public static final String WEB_INVOICE_BEAN = "webInvoiceBean";
    
    
    
    public static final String WEB_MINOUT_BEAN = "webMinOutBean";
    public static final String WEB_ORDER_BEAN = "webOrderBean";
    public static final String WEB_ORDER_LINE_BEAN = "webOrderLineBean";
    public static final String WEB_PAYMENT_BEAN = "webPaymentBean";
    
    
    public static final String WEBSTORE_CREATE_CUSTOMER = "webstoreCreateCustomer";
    public static final String WEBSTORE_CREDITCARD_BEAN = "creditCardBean";
    public static final String WEBSTORE_CUSTOMER = "Webstore Customer";
    public static final String WEBSTORE_INVOICE = "webstoreInvoice";
    /*public static final String WEBSTORE_LINK = UDIFilePropertiesManager.getProperty().get(new Properties(), "webstore.url");*/
    public static final String WEBSTORE_LOGIN_ACTION = "/WebstoreLoginAction";
    public static final String WEBSTORE_PROGRESS_DO = "webstoreProgressDO";
    /*public static final String WEBSTORE_SHIPPING_PROMOTION_PRICE = UDIFilePropertiesManager.getProperty().get(new Properties(), "webstore.shipping.promotion.price");
    public static final String WEBSTORE_SHIPPING_PROMOTION_STATUS = UDIFilePropertiesManager.getProperty().get(new Properties(), "webstore.shipping.promotion.status");
*/
    public static final String WEBSTORE_USER_BEAN = "webstoreUserBean";
    public static final String WEBSTORE_USER_DETAILS = "webstoreUserDetails";
    public static final String WESBANK = "wesbank";
    public static final String WESBANK_RESPONSE = "WesbankResponse";
    public static final String WESBANK_STATUS = "wesbank.status";
    public static final String WHOLESALE_REPORT = "wholesaleReport";
    public static final String YEAR_ATTRIBUTE_VALUE_ID = "yearAttributeValueId";
    public static final String YEAR_ATTRIBUTE_VALUES="yearAttribueValues";
    
    
    public static final String YES_CHAR = "Y";
    public static final String YOU = "you";
    public static final String YOU_BP_LOCATION = "youbpLocation";
    public static final String YOU_LOCATION = "youLocation";
    public static final String CURRENCY_SYMBOLE_PURCHASE = "currencySymbolePurchase";
    public static final String CREDIT_ORDER = "creditOrder";
    public static final String CREDIT_ORDER_SHOPPING_CART = "creditOrderShoppingCart";
    public static final String CREDIT_ORDER_SHOPPING_CART_ITEMS = "creditOrderShoppingCartItems";
    public static final String OPEN_ITEMS = "openItems";
    public static final String AGING_ITEMS = "agingItems";
    public static final String CREDIT_INVOICE_INFO = "creditInvoiceInfo";
    public static final String DISCOUNT_ALLOWED = "discountAllowed";
    public static final String PRODUCT_SALES_BUCKET = "productSalesBucket";
    public static final String BPARTNERS = "bPartners";
    public static final String CASH_PAYMENT_MADE = "cashPaymentMade";
    public static final String CASH_PAYMENT_LIST = "cashPaymentList";
    public static final String CARD_CHQ_PAYMENT_LIST = "cardChqPaymentList";
    public static final String INVOICE_GRAND_TOTAL = "invoiceGrandTotal";
    public static final String NO_IMAGE_CSV_REPORT = "noImageCSVReport";
    public static final String MCASHLINE = "cashLine";
    public static final String DEBTORS = "debtors";
    public static final String PAYMENT_ALLOCATIONS = "paymentAllocations";
    public static final String PAYMENT_TERM = "paymentTerm";
    public static final String PAYMENT_TERM_ID = "paymentTermId";
    public static final String ALL_PAYMENT_TERMS = "allPaymentsTerms";
    public static final String EDIT_PAYMENT_TERM = "editPaymentTerm";
    public static final String NO_CREDIT_CHECK="No Credit Check";
    public static final String CREDIT_OK="Credit OK";
    public static final String CREDIT_STOP="Credit Stop";
    public static final String BUSINESSPARTNERINFO = "bPartnerInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String COMMISSION_AMT = "commissionAmt";
    public static final String COMMISSION_AMT_DETAILS = "commissionAmtDetails";
    public static final String CREDIT_HOLD = "creditHold";
    public static final String CREDIT_WATCH = "creditWatch";
    public static final String CREDIT_PAYMENT_TERM_ID = "creditPaymentTermId";
    public static final String PRESENT_POS_ORDER = "presentPOSOrder";
    public static final String PRESENT_CREDIT_ORDER = "presentCreditOrder";
    public static final String PRESENT_POS_ORDER_ID = "presentPOSOrderId";
    public static final String PRESENT_GRN_ORDER_ID = "presentGRNOrderId";
    public static final String PRESENT_GRN_ORDER = "presentGRNOrder";
    public static final String PRESENT_GOODS_RET_ORDER = "presentGoodsReturnOrder";
    public static final String PRESENT_GOODS_RET_ORDER_ID = "presentGoodsReturnOrderId";
    public static final String PRESENT_CREDIT_ORDER_ID ="presentCreditOrderId";
    public static final String PRESENT_CUSTOMER_RET_ORDER = "presentCustomerReturnOrder";
    public static final String PRESENT_CUSTOMER_RET_ORDER_ID = "presentCustomerReturnOrderId";
    public static final String CUSTOMER_CART = "customerCart";
    public static final String ALL_CART_CUSTOMERS = "allCartCustomers";
    public static final String ALL_TAX_RATE ="allTaxRates";
    public static final String EDIT_TAX = "editTax";
    public static final String INVENTORY_LINE_LIST = "inventoryLineList";
    public static final String CREDITORDER_BPARTNER = "creditOrderBPartner";
    public static final String ORDER_TITLE = "orderTitle";
    public static final String UNALLOCATED_PAYMENTS = "unallocatedPayments";
    public static final String COMPLETED_INVENTORY_LINE_LIST = "completedInventoryLineList";
    public static final String INVENTORY_HISTORY_LIST = "inventoryHistoryList";
    public static final String WHOLE_INVENTORY_LINE_LIST = "wholeInventoryLineList";
    public static final String SHIPMENT_REQUIRED = "shipmentRequired";
    public static final String INVOICE_FOR_ALLOCATION = "invoiceForAllocation";
    public static final String TO_BE_SHIPPED = "toBeShipped";
    public static final String POS_HISTORY_ORDER_TYPE = "posHistoryOrderType";
    public static final String GROUP1 = "group1";
    public static final String GROUP2 = "group2";   
    public static final Object CREATE_PAYMENT = "createPayment";
    public static final String INVENTORY_ID = "inventoryId";
    public static final String PAID="Paid";
    public static final String UNPAID="UnPaid";
    public static final String PARTIALLY_PAID="Partially Paid";
    public static final String OVER_PAID = "overPaid";
    public static final String INVENTORY_DESCRIPTION ="inventoryDescription";
    public static final String PARTIAL_POS_OREDR = "partialPOSOrder";
    public static final String ALL_ORDER_HISTORY = "allOrderHistory";
    
    public static final String CLOSE_TILL_PRINT_DATA="closeTillPrintData";
    public static final String SHOPPING_CART_NAME = "shoppingCartName";
    
    public static final String PREFERENCE_PRINTER_TYPE = "preference.printerType";
    public static final String UOM_LIST = "uomList";
    public static final String USER_ORGS = "userOrgs";
    public static final String ALL_ORGANISATIONS = "*";
    public static final String ROLE_ORG_ACCESS_LIST = "roleOrgAccessList";
    public static final String DETAILED_SALES_REPORT = "detailedSalesReport";
    // Constants related to Material Movement
    public static final String ORG_FROM_ID = "orgFromId";
    public static final String ORG_TO_ID = "orgToId";
    public static final String USER_ORGS_STOCK = "userOrgsStockMovement";
    
    public static final String STOCK_LIST = "stockList";
    public static final String MOVE_CONFIRM = "moveConfirm";
    public static final String MATERIAL_MOVEMENT_ITEMS = "materialMovementItems";
    public static final String MATERIAL_MOVEMENT = "materialMovement";
    public static final String MATERIAL_MOVEMENT_LIST = "materialMovementList";
    public static final String MATERIAL_MOVEMENT_LINES = "materialMovementLines";
    public static final String MATERIAL_MOVEMENT_DETAILS = "materialMovementDetails";
    public static final String MATERIAL_MOVEMENT_ID = "materialMovementId";
    public static final String MOVE_CONFIRM_LINES = "moveConfirmLines";
    public static final String MMOVEMENT = "movement";
    public static final String MMOVEMENT_ID = "movementId";
    
    public static final String PAYMENT_RULE_CASH_MSG = "payment.rule.cash";
    public static final String PAYMENT_RULE_CARD_MSG = "payment.rule.card";
    public static final String PAYMENT_RULE_CHEQUE_MSG = "payment.rule.cheque";
    public static final String PAYMENT_RULE_MIXED_MSG = "payment.rule.mixed";
    public static final String PAYMENT_RULE_CREDIT_MSG = "payment.rule.credit";
    
    
    public static final String DOC_STATUS_DRAFTED = "document.status.drafted";
    public static final String DOC_STATUS_INPROGRESS = "document.status.inprogress";
    public static final String DOC_STATUS_COMPLETED = "document.status.completed";
    public static final String DOC_STATUS_INVALID = "document.status.invalid";
    public static final String DOC_STATUS_VOID = "document.status.void";
    public static final String DOC_STATUS_CLOSED = "document.status.closed";
    public static final String STOCK_SALES_REPORT_LIST = "stock.sales.report.list";
    public static final String STOCK_SALES_REPORT_DATA = "stockSalesReportData";
    public static final String SALES_REPORT_LIST = "salesReportList";
    public static final String STOCK_REPORT_LIST = "stockReportList";
    public static final String SALES_REPORT_LIST_COMPLETE = "salesReportListComplete";
    public static final String STOCK_REPORT_LIST_COMPLETE = "stockReportListComplete";
    public static final String PRODUCT_SALES_COMPLETE_SET = "productSalesCompleteSet";
    public static final String PRODUCT_STOCK_COMPLETE_SET = "productStockCompleteSet";
    public static final String PRODUCT_ORG_STOCK_MAP = "productOrgStockMap";
    public static final String PRODUCT_ORG_SALES_MAP = "productOrgSalesMap";
    public static final String STOCK_PRODUCT_LIST = "stockProductList";
    public static final String MMOVEMENT_LINES = "movementLines";
    public static final String STOCKMOVEMENT_MAP = "stockMovementMap";
    public static final String PRE_ORG_FROM_ID = "preOrgFromId";
    public static final int SALES_TYPE = 1;
    public static final int STOCK_TYPE = 2;
    public static final String STOCK_SALES_REPORT_ORGS = "stockSalesReportOrgs";
    public static final String MONTHLY_SALES_REPORT_DATA = "monthlySalesReportData";
    public static final String DETAILED_SALES_REPORT_PER_PRODUCT = "detailedSalesReportPerProduct";
    public static final String DETAILED_GRN_REPORT_PER_PRODUCT = "detailedGRNReportPerProduct";
    public static final String STOCK_ADJUSTMENTS_REPORT_DATA = "stockAdjustmentsReportData";
    public static final String INVENTORY_MOVE_HISTORY = "viewInventoryMoveHistory";
    public static final String LIST_PRICE_LISTS = "list.price.lists";
    public static final String CREATE_PRICE_LIST = "create.price.list";
    public static final String PRODUCT_PRICE_LISTS = "product.price.lists";
    public static final String PRICE_LIST_HEADERS = "price.list.headers";
    public static final String USER_PRICE_LISTS = "user.price.lists";
    public static final String REGISTER_USERS = "RegisterUser";
    public static final String USER_BEAN = "userBean";
    public static final String USER_PURCHASE_PRICE_LISTS = "user.purchase.price.lists";
    public static final String USER_SALES_PRICE_LISTS = "user.sales.price.lists";
    public static final String CONFIGURATION = "configuration";
    public static final String WEB_COMPONENTS = "web.components";
    public static final String REPORT_DESCRIPTION = "report.description";
    public static final String PROCESS_ID = "process.id";
    public static final String IMPORT_FAIL_CSV_FILE = "import.fail.csv.file";
    
    public static final String CURRENCY_LIST = "currencyList";
    public static final String CURRENCY = "currency";
    public static final String IS_FROM_DELETE_PRODUCT_PRICE = "is.from.delete.product.price";
    public static final String REPORT_COMMENT = "report.comment";
    public static final String SHOPPING_CART_TOTAL = "shoppingcartTotal";
    public static final String SHOPPING_CART_DISCOUNTED_TOTAL = "shoppingcartDiscountedTotal";
    public static final String SHOPPING_CART_DISCOUNT_PERCENTAGE = "shoppingcartDiscountPercentage";
    public static final String BPARTNER_ID = "bpartner.id";
    public static final String BPARTNER_NAME = "bpartner.name";
    public static final String BPARTNER_CREDIT = "bpartner.credit";
    public static final String ORDER_PRICE_LIST = "order.price.list";
    public static final String IS_SOTRX = "isSOTrx";
    
    public static final String ACCESSIBLE_BANKACCOUNTS = "accessible.bankaccounts";
    public static final String ACCESSIBLE_CASHBOOKS = "accessible.cashbooks";
    public static final String CASHBOOKS = "cashbooks";
    public static final String TERMINALS = "terminals";
    public static final String TERMINAL_TRANSFER_TYPE = "terminalTransferType";
    public static final String DISPLAYTAG_TABLE_ID = "displayTagTableId";
    public static final String ORDER_PAYMENT = "orderPayment";
    public static final String ORDER_WRITE_OFF = "orderWriteOff";
    public static final String ORDER_DICOUNT = "orderDiscount";
    public static final String ORDER_CARD_AMOUNT = MPayment.TENDERTYPE_CreditCard;
    public static final String ORDER_CHECK_AMOUNT = MPayment.TENDERTYPE_Check;
    public static final String ORDER_CASH_AMOUNT = "cashAmount";
	public static final String DISCOUNT_ON_ORDER_TOTAL = "discountOnOrderTotal";
	public static final String DISCOUNT_ON_ORDER_TOTAL_PERCENT = "discountOnOrderTotalPercent";
	
	public static final String CREDITORS = "creditors";
	//public static final String DEBTORS = "";
	public static final String CREDITOR_HISTORY = "creditorHistory";
	public static final String DEBTOR_HISTORY = "debtorHistory";
}
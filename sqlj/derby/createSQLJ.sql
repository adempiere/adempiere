--
-- 	Create SQL Java Functions (Derby)
-- 
-- 	Author + Copyright 1999-2005 Jorg Janke
-- 	09-28-2006 Jinglun Zhang     modified from sqlj/oracle, ignore drop errors
-- 
 
-- load sqlj.jar to Derby

Call SQLJ.REMOVE_JAR ('adempiere_sqlj',0);

Call SQLJ.INSTALL_JAR ('c:\adempiere\adempiere-all2\sqlj\sqlj.jar', 'adempiere_sqlj', 0);

call SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath','ADEMPIERE.adempiere_sqlj');


-- drop/create functions

DROP FUNCTION adempiereVersion;
 
CREATE FUNCTION adempiereVersion()
 	RETURNS VARCHAR(255)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getVersion';

DROP FUNCTION adempiereProperties;
 
CREATE FUNCTION adempiereProperties()
 	RETURNS VARCHAR(1022)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getProperties';

DROP FUNCTION adempiereProperty;
 
CREATE FUNCTION adempiereProperty(p_key VARCHAR(255))
 	RETURNS VARCHAR(1022)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getProperty';


-- Product	-- 
DROP FUNCTION productAttribute;
 
CREATE FUNCTION productAttribute (M_AttributeSetInstance_ID int)
 	RETURNS VARCHAR(255)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.attributeName';


DROP FUNCTION bomPriceLimit;
 
CREATE FUNCTION bomPriceLimit (M_Product_ID int, M_PriceList_Version_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceLimit';

DROP FUNCTION bomPriceList;
 
CREATE FUNCTION bomPriceList (M_Product_ID int, M_PriceList_Version_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceList';

DROP FUNCTION bomPriceStd;
 
CREATE FUNCTION bomPriceStd (M_Product_ID int, M_PriceList_Version_ID int)
 	RETURNS Decimal(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceStd';


DROP FUNCTION bomQtyAvailable;
 
CREATE FUNCTION bomQtyAvailable (M_Product_ID int, M_Warehouse_ID int, 
        M_Locator_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyAvailable';

DROP FUNCTION bomQtyOnHand;
 
CREATE FUNCTION bomQtyOnHand (M_Product_ID int, M_Warehouse_ID int, 
        M_Locator_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyOnHand';

DROP FUNCTION bomQtyOrdered;
 
CREATE FUNCTION bomQtyOrdered (M_Product_ID int, M_Warehouse_ID int, 
        M_Locator_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyOrdered';

DROP FUNCTION bomQtyReserved;
 
CREATE FUNCTION bomQtyReserved (M_Product_ID int, M_Warehouse_ID int, 
        M_Locator_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyReserved';


-- Currency -- 
DROP FUNCTION currencyBase;
 
CREATE FUNCTION currencyBase (Amount DECIMAL(31,5), C_CurrencyFrom_ID int, 
        ConversionDate TIMESTAMP, AD_Client_ID int, AD_Org_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Currency.base';

DROP FUNCTION currencyConvert;
 
--CREATE FUNCTION currencyConvert (Amount DOUBLE, C_CurrencyFrom_ID int, 
--        C_CurrencyTo_ID int,
--    ConversionDate TIMESTAMP, C_ConversionType_ID int, AD_Client_ID int, AD_Org_ID int)
-- 	RETURNS DOUBLE
CREATE FUNCTION currencyConvert (Amount DECIMAL(31,5), C_CurrencyFrom_ID int, 
        C_CurrencyTo_ID int,
    ConversionDate TIMESTAMP, C_ConversionType_ID int, AD_Client_ID int, AD_Org_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Currency.convert';

DROP FUNCTION currencyRate;
 
CREATE FUNCTION currencyRate (C_CurrencyFrom_ID int, C_CurrencyTo_ID int,
        ConversionDate TIMESTAMP, C_ConversionType_ID int, AD_Client_ID int, AD_Org_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Currency.rate';

DROP FUNCTION currencyRound;
 
CREATE FUNCTION currencyRound (Amt DECIMAL(31,5), C_CurrencyTo_ID int, IsCosting VARCHAR(2))
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Currency.round';


-- BPartner -- 
DROP FUNCTION bpartnerRemitLocation;
 
CREATE FUNCTION bpartnerRemitLocation (p_C_BPartner_ID int)
 	RETURNS int
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.BPartner.remitLocation';


-- Invoice -- 
DROP FUNCTION invoiceOpen;
 
CREATE FUNCTION invoiceOpen (p_C_Invoice_ID int, p_C_InvoicePaySchedule_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.open';

DROP FUNCTION invoicePaid;
 
CREATE FUNCTION invoicePaid (p_C_Invoice_ID int, p_C_Currency_ID int, 
        p_MultiplierAP int)
 	RETURNS DECIMAL(31,5)
 	PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.paid';

DROP FUNCTION invoiceDiscount;
 
CREATE FUNCTION invoiceDiscount (p_C_Invoice_ID int, p_PayDate TIMESTAMP, 
        p_C_InvoicePaySchedule_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.discount';


-- Payment Term -- 
DROP FUNCTION paymentTermDueDays;
 
CREATE FUNCTION paymentTermDueDays (p_C_PaymentTerm_ID int, p_DocDate TIMESTAMP, 
        p_PayDate TIMESTAMP)
 	RETURNS int
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.dueDays';

DROP FUNCTION paymentTermDiscount;
 
CREATE FUNCTION paymentTermDiscount (p_Amount DECIMAL(31,5), p_C_Currency_ID int,
		p_C_PaymentTerm_ID int, p_DocDate TIMESTAMP, p_PayDate TIMESTAMP)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.discount';

DROP FUNCTION paymentTermDueDate;
 
CREATE FUNCTION paymentTermDueDate (p_C_PaymentTerm_ID int, p_DocDate TIMESTAMP)
 	RETURNS TIMESTAMP
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.dueDate';


-- Payment -- 
DROP FUNCTION paymentAllocated;
 
CREATE FUNCTION paymentAllocated (p_C_Payment_ID int, p_C_Currency_ID int)
 	RETURNS DECIMAL(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Payment.allocated';

DROP FUNCTION paymentAvailable;
 
CREATE FUNCTION paymentAvailable (p_C_Payment_ID int)
 	RETURNS Decimal(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Payment.available';


-- Account -- 
DROP FUNCTION acctBalance;
 
CREATE FUNCTION acctBalance (p_Account_ID int, p_AmtDr Decimal(31,5), p_AmtCr Decimal(31,5))
 	RETURNS Decimal(31,5)
 	 PARAMETER STYLE JAVA LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Account.balance';


-- General	-- 
--BEGIN
--	dbms_java.grant_permission('ADEMPIERE','SYS:java.util.PropertyPermission', '*', 'read,write');
--END;


-- Get Character at Position   
DROP FUNCTION charAt;
CREATE FUNCTION charAt
(
    p_string    VARCHAR(2046),
    p_pos       int
)
 	RETURNS CHAR
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.charAt';

-- GetDate                     
DROP FUNCTION getdate;
CREATE FUNCTION getdate()
 	RETURNS TIMESTAMP
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getDate';

-- First Of DD/DY/MM/Q         
DROP FUNCTION firstOf;
CREATE FUNCTION firstOf
(
    p_date      TIMESTAMP,
    p_datePart  VARCHAR(26)
)
 	RETURNS TIMESTAMP
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.firstOf';

-- Add Number of Days      
DROP FUNCTION addDays;
CREATE FUNCTION addDays
(
    p_date      TIMESTAMP,
    p_days      int
)
 	RETURNS TIMESTAMP
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.addDays';

-- Difference in Days      
DROP FUNCTION getDaysBetween;
CREATE FUNCTION getDaysBetween
(
    p_date1     TIMESTAMP,
    p_date2     TIMESTAMP
)
 	RETURNS int
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getDaysBetween';


-- 	Truncate Date     
DROP FUNCTION trunc;
CREATE FUNCTION trunc
(
    p_dateTime     TIMESTAMP
)
 	RETURNS TIMESTAMP
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.trunc';


-- 	convert number to chars     
DROP FUNCTION getChars;
CREATE FUNCTION getChars
(
    p_number     DECIMAL(31,5)
)
 	RETURNS VARCHAR(38)
 	 PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA 
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getChars';


SELECT adempiereVersion(), adempiereProperty('java.vendor'), 
    getdate() FROM sysibm.sysdummy1;
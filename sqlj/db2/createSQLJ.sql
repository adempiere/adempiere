/**
 *	Create SQL Java Functions (DB2)
 */

-- Oracle equvalents are in comments above for temporary reference.

--CREATE OR REPLACE FUNCTION adempiereVersion
--	RETURN VARCHAR2
--	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Adempiere.getVersion() return java.lang.String';

CREATE FUNCTION adempiereVersion()
	RETURNS VARCHAR
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getVersion';
	

--CREATE OR REPLACE FUNCTION adempiereProperties
-- 	RETURN VARCHAR2
--	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Adempiere.getProperties() return java.lang.String';

CREATE FUNCTION adempiereProperties()
	RETURNS VARCHAR
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getProperties';


--CREATE OR REPLACE FUNCTION adempiereProperty(p_key VARCHAR2)
-- 	RETURN VARCHAR2
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Adempiere.getProperty(java.lang.String) return java.lang.String';

CREATE FUNCTION adempiereProperty(p_key VARCHAR)
	RETURNS VARCHAR
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Adempiere.getProperty';


/** Product	**/

--CREATE OR REPLACE FUNCTION productAttribute (M_AttributeSetInstance_ID NUMBER)
-- 	RETURN NVARCHAR2
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.attributeName(int) return java.lang.String';

CREATE FUNCTION productAttribute (M_AttributeSetInstance_ID FLOAT)
	RETURNS VARCHAR
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.attributeName';


--CREATE OR REPLACE FUNCTION bomPriceLimit (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomPriceLimit(int,int) return java.math.BigDecimal';

CREATE FUNCTION bomPriceLimit (M_Product_ID FLOAT, M_PriceList_Version_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceLimit';


--CREATE OR REPLACE FUNCTION bomPriceList (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomPriceList(int,int) return java.math.BigDecimal';

CREATE FUNCTION bomPriceList (M_Product_ID FLOAT, M_PriceList_Version_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceList';


--CREATE OR REPLACE FUNCTION bomPriceStd (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomPriceStd(int,int) return java.math.BigDecimal';

CREATE FUNCTION bomPriceStd (M_Product_ID FLOAT, M_PriceList_Version_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomPriceStd';


--CREATE OR REPLACE FUNCTION bomQtyAvailable (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, 
--        M_Locator_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomQtyAvailable(int,int,int) return --java.math.BigDecimal';

CREATE FUNCTION bomQtyAvailable (M_Product_ID FLOAT, M_Warehouse_ID FLOAT, M_Locator_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyAvailable';


--CREATE OR REPLACE FUNCTION bomQtyOnHand (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, 
--        M_Locator_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomQtyOnHand(int,int,int) return java.math.BigDecimal';

CREATE FUNCTION bomQtyOnHand (M_Product_ID FLOAT, M_Warehouse_ID FLOAT, M_Locator_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyOnHand';


--CREATE OR REPLACE FUNCTION bomQtyOrdered (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, 
--        M_Locator_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomQtyOrdered(int,int,int) return --java.math.BigDecimal';

CREATE FUNCTION bomQtyOrdered (M_Product_ID FLOAT, M_Warehouse_ID FLOAT, M_Locator_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyOrdered';


--CREATE OR REPLACE FUNCTION bomQtyReserved (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, 
--        M_Locator_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Product.bomQtyReserved(int,int,int) return --java.math.BigDecimal';

CREATE FUNCTION bomQtyReserved (M_Product_ID FLOAT, M_Warehouse_ID FLOAT, M_Locator_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Product.bomQtyReserved';


/** Currency **/

--CREATE OR REPLACE FUNCTION currencyBase (Amount NUMBER, C_CurrencyFrom_ID NUMBER, 
--        ConversionDate DATE, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Currency.base--(java.math.BigDecimal,int,java.sql.Timestamp,int,int) return java.math.BigDecimal';

CREATE FUNCTION currencyBase (Amount FLOAT, C_CurrencyFrom_ID FLOAT, ConversionDate TIMESTAMP, AD_Client_ID FLOAT, AD_Org_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Currency.base';


--CREATE OR REPLACE FUNCTION currencyConvert (Amount NUMBER, C_CurrencyFrom_ID NUMBER, 
--        C_CurrencyTo_ID NUMBER,
--    ConversionDate DATE, C_ConversionType_ID NUMBER, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Currency.convert--(java.math.BigDecimal,int,int,java.sql.Timestamp,int,int,int) return java.math.BigDecimal';

CREATE FUNCTION currencyBase (Amount FLOAT, C_CurrencyFrom_ID FLOAT, ConversionDate TIMESTAMP, C_CurrencyTo_ID FLOAT, ConversionDate TIMESTAMP, C_ConversionType_ID FLOAT, AD_Client_ID FLOAT, AD_Org_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Currency.convert';


--CREATE OR REPLACE FUNCTION currencyRate (C_CurrencyFrom_ID NUMBER, C_CurrencyTo_ID NUMBER,
--        ConversionDate DATE, C_ConversionType_ID NUMBER, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Currency.rate(int,int,java.sql.Timestamp,int,int,int) return --java.math.BigDecimal';

CREATE FUNCTION currencyRate (C_CurrencyFrom_ID FLOAT, C_CurrencyTo_ID FLOAT, ConversionDate TIMESTAMP, C_ConversionType_ID FLOAT, AD_Client_ID FLOAT, AD_Org_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Currency.rate';


--CREATE OR REPLACE FUNCTION currencyRound (Amt NUMBER, C_CurrencyTo_ID NUMBER, IsCosting --VARCHAR2)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Currency.round(java.math.BigDecimal,int,java.lang.String) --return java.math.BigDecimal';

CREATE FUNCTION currencyRound (Amt FLOAT, C_CurrencyTo_ID FLOAT, IsCosting VARCHAR)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Currency.round';


/** BPartner **/

CREATE OR REPLACE FUNCTION bpartnerRemitLocation (p_C_BPartner_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.compiere.sqlj.BPartner.remitLocation(int) return int';

CREATE FUNCTION bpartnerRemitLocation (p_C_BPartner_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.BPartner.remitLocation';


/** Invoice **/

--CREATE OR REPLACE FUNCTION invoiceOpen (p_C_Invoice_ID NUMBER, p_C_InvoicePaySchedule_ID --NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Invoice.open(int,int) return java.math.BigDecimal';

CREATE FUNCTION invoiceOpen (p_C_Invoice_ID FLOAT, p_C_InvoicePaySchedule_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.open';


--CREATE OR REPLACE FUNCTION invoicePaid (p_C_Invoice_ID NUMBER, p_C_Currency_ID NUMBER, 
--        p_MultiplierAP NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Invoice.paid(int,int,int) return java.math.BigDecimal';

CREATE FUNCTION invoicePaid (p_C_Invoice_ID FLOAT, p_C_Currency_ID FLOAT, p_MultiplierAP FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.paid';


--CREATE OR REPLACE FUNCTION invoiceDiscount (p_C_Invoice_ID NUMBER, p_PayDate Date, 
--        p_C_InvoicePaySchedule_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Invoice.discount(int,java.sql.Timestamp,int) return --java.math.BigDecimal';

CREATE FUNCTION invoiceDiscount (p_C_Invoice_ID FLOAT, p_PayDate TIMESTAMP, p_C_InvoicePaySchedule_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Invoice.discount';


/** Payment Term **/

--CREATE OR REPLACE FUNCTION paymentTermDueDays (p_C_PaymentTerm_ID NUMBER, p_DocDate DATE, 
--        p_PayDate DATE)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.PaymentTerm.dueDays(int,java.sql.Timestamp,java.sql.Timestamp) --return int';

CREATE FUNCTION paymentTermDueDays (p_C_PaymentTerm_ID FLOAT, p_DocDate TIMESTAMP, p_PayDate TIMESTAMP)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.dueDays';


--CREATE OR REPLACE FUNCTION paymentTermDiscount (p_Amount NUMBER, p_C_Currency_ID NUMBER,
--		p_C_PaymentTerm_ID NUMBER, p_DocDate DATE, p_PayDate DATE)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.PaymentTerm.discount--(java.math.BigDecimal,int,int,java.sql.Timestamp,java.sql.Timestamp) return --java.math.BigDecimal';

CREATE FUNCTION paymentTermDiscount (p_Amount FLOAT, p_C_Currency_ID FLOAT, p_C_PaymentTerm_ID FLOAT, p_DocDate TIMESTAMP, p_PayDate TIMESTAMP)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.discount';


--CREATE OR REPLACE FUNCTION paymentTermDueDate (p_C_PaymentTerm_ID NUMBER, p_DocDate DATE)
-- 	RETURN DATE
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.PaymentTerm.dueDate(int,java.sql.Timestamp) return --java.sql.Timestamp';

CREATE FUNCTION paymentTermDueDate (p_C_PaymentTerm_ID FLOAT, p_DocDate TIMESTAMP)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.PaymentTerm.dueDate';


/** Payment **/

--CREATE OR REPLACE FUNCTION paymentAllocated (p_C_Payment_ID NUMBER, p_C_Currency_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Payment.allocated(int,int) return java.math.BigDecimal';

CREATE FUNCTION paymentTermDueDate (p_C_Payment_ID FLOAT, p_C_Currency_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Payment.allocated';


--CREATE OR REPLACE FUNCTION paymentAvailable (p_C_Payment_ID NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Payment.available(int) return java.math.BigDecimal';

CREATE FUNCTION paymentAvailable (p_C_Payment_ID FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Payment.available';


/** Account **/

--CREATE OR REPLACE FUNCTION acctBalance (p_Account_ID NUMBER, p_AmtDr NUMBER, p_AmtCr NUMBER)
-- 	RETURN NUMBER
-- 	AS LANGUAGE JAVA 
--	NAME 'org.compiere.sqlj.Account.balance(int,java.math.BigDecimal,java.math.BigDecimal) --return java.math.BigDecimal';

CREATE FUNCTION paymentAvailable (p_Account_ID FLOAT, p_AmtDr FLOAT, p_AmtCr FLOAT)
	RETURNS FLOAT
	LANGUAGE JAVA
	EXTERNAL NAME 'org.compiere.sqlj.Account.balance';

--
--Below this is not translated yet
--

/** General	**/

--BEGIN
--	dbms_java.grant_permission('ADEMPIERE','SYS:java.util.PropertyPermission', '*', 'read,write');
--END;

--Don't know if we have to do any corresponding action in DB2 for the above command.


/** Get Character at Position   */

--CREATE OR REPLACE FUNCTION charAt
--(
--    p_string    VARCHAR2,
--    p_pos       NUMBER
--)
-- 	RETURN VARCHAR2
--AS
--BEGIN
--    RETURN SUBSTR(p_string, p_pos, 1);
--END;    

CREATE FUNCTION charAt (p_string VARCHAR, p_pos FLOAT)
	RETURNS VARCHAR
	LANGUAGE SQL
	CONTAINS SQL
	NO EXTERNAL ACTION
	DETERMINISTIC
	RETURN SUBSTR(p_string, p_pos, 1);


/** GetDate                     */

--CREATE OR REPLACE FUNCTION getdate
-- 	RETURN DATE
--AS
--BEGIN
--    RETURN SysDate;
--END;    

CREATE FUNCTION getdate ()
	RETURNS TIMESTAMP
	LANGUAGE SQL
	CONTAINS SQL
	NO EXTERNAL ACTION
	DETERMINISTIC
	RETURN CURRENT TIMESTAMP;


/** First Of DD/DY/MM/Q         */

--CREATE OR REPLACE FUNCTION firstOf
--(
--    p_date      DATE,
--    p_datePart  VARCHAR2
--)
-- 	RETURN DATE
--AS
--BEGIN
--    RETURN TRUNC(p_date, p_datePart);
--END;    

CREATE FUNCTION firstOf (p_date TIMESTAMP, p_datePart VARCHAR)
	RETURNS TIMESTAMP
	LANGUAGE SQL
	CONTAINS SQL
	NO EXTERNAL ACTION
	DETERMINISTIC
	RETURN TRUNC(p_date, p_datePart);


/** Add Number of Days      */

--CREATE OR REPLACE FUNCTION addDays
--(
--    p_date      DATE,
--    p_days      NUMBER
--)
-- 	RETURN DATE
--AS
--BEGIN
--    RETURN TRUNC(p_date) + p_days;
--END;    

CREATE FUNCTION addDays (p_date TIMESTAMP, p_days FLOAT)
	RETURNS TIMESTAMP
	LANGUAGE SQL
	CONTAINS SQL
	NO EXTERNAL ACTION
	DETERMINISTIC
	RETURN TRUNC(p_date) + p_days;


/** Difference in Days      */

--CREATE OR REPLACE FUNCTION daysBetween
--(
--    p_date1     DATE,
--    p_date2     DATE
--)
-- 	RETURN NUMBER
--AS
--BEGIN
--    RETURN TRUNC(p_date1) - TRUNC(p_date2);
--END;    

CREATE FUNCTION daysBetween (p_date1 TIMESTAMP, p_date2 TIMESTAMP)
	RETURNS FLOAT
	LANGUAGE SQL
	CONTAINS SQL
	NO EXTERNAL ACTION
	DETERMINISTIC
	RETURN TRUNC(p_date1) - TRUNC(p_date2);


--SELECT adempiereVersion(), adempiereProperty('java.vendor'), 
--    TRUNC(getdate()) FROM DUAL

SELECT adempiereVersion(), adempiereProperty('java.vendor'), TRUNC(getdate())
FROM FROM SYSIBM.SYSDUMMY1!;


EXIT

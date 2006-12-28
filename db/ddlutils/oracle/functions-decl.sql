CREATE OR REPLACE FUNCTION acctBalance
(
    p_Account_ID    IN NUMBER,
    p_AmtDr         IN NUMBER,
    p_AmtCr         IN NUMBER
)
RETURN NUMBER AS
BEGIN
	RETUNR 0;
END;
/

CREATE OR REPLACE FUNCTION bomPriceLimit
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomPriceList
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomPriceStd
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomQtyAvailable
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomQtyOnHand
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomQtyOrdered
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bomQtyReserved
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION currencyBase
(
	p_Amount			IN	NUMBER,
	p_CurFrom_ID		IN	NUMBER,
	p_ConvDate			IN	DATE,
	p_Client_ID			IN	NUMBER,
	p_Org_ID			IN NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION bpartnerRemitLocation
(
	p_C_BPartner_ID	  C_BPartner.C_BPartner_ID%TYPE
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION currencyConvert
(
	p_Amount			IN	NUMBER,
	p_CurFrom_ID		IN	NUMBER,
	p_CurTo_ID		    IN	NUMBER,
	p_ConvDate		    IN	DATE,
	p_ConversionType_ID IN	NUMBER,
	p_Client_ID		    IN	NUMBER,
	p_Org_ID			IN	NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION currencyRate
(
	p_CurFrom_ID		IN	NUMBER,
	p_CurTo_ID		    IN	NUMBER,
	p_ConvDate		    IN	DATE,
	p_ConversionType_ID	IN	NUMBER,
	p_Client_ID		    IN	NUMBER,
	p_Org_ID			IN	NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION currencyRound
(
	p_Amount		IN	NUMBER,
	p_CurTo_ID	IN	NUMBER,
	p_Costing		IN	VARCHAR2		--	Default 'N'
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION invoiceDiscount
(
	p_C_Invoice_ID		        IN NUMBER,
	p_PayDate			        IN	DATE,
	p_C_InvoicePaySchedule_ID	IN	NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION invoiceOpen
(
	p_C_Invoice_ID	            IN	NUMBER,
    p_C_InvoicePaySchedule_ID   IN  NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION invoicePaid
(
	p_C_Invoice_ID		IN	NUMBER,
	p_C_Currency_ID	    IN	NUMBER,
	p_MultiplierAP		IN	NUMBER	-- DEFAULT 1
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMBER,
	p_C_Currency_ID	IN	NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION paymentAvailable
(
	p_C_Payment_ID	IN	NUMBER
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION paymentTermDiscount
(
	Amount			IN	NUMBER,
    Currency_ID     IN  NUMBER,
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION paymentTermDueDate
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE
)
RETURN DATE AS
BEGIN
	RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION paymentTermDueDays
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER AS
BEGIN
	RETURN 0;
END;
/

CREATE OR REPLACE FUNCTION DBA_ConstraintCmd
(
	p_ConstraintName	IN	VARCHAR2
)
RETURN VARCHAR2 AS
BEGIN
	RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION DBA_DisplayType
(
	AD_Reference_ID		IN	NUMBER
)
RETURN VARCHAR2 AS
BEGIN
	RETURN NULL;
END;
/

CREATE OR REPLACE FUNCTION productAttribute
(
    p_M_AttributeSetInstance_ID     IN NUMBER
)
RETURN VARCHAR2 AS
BEGIN
	RETURN NULL;
END;
/


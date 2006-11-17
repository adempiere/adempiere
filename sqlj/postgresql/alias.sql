/** Change Numeric for Integer   */
CREATE OR REPLACE FUNCTION adempiere.ID (record NUMERIC) 
RETURNS INTEGER AS $$
DECLARE 
ID integer := 0;
BEGIN
    ID := CAST(record AS INTEGER);
    --RAISE NOTICE 'Quantity here is %', ID;
    RETURN ID;
END;
$$ LANGUAGE plpgsql VOLATILE;
  
CREATE OR REPLACE FUNCTION adempiere.getdate()
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN now();
END;
$$ LANGUAGE plpgsql VOLATILE;

/**TIMESTAMP WITH TIME ZONE    **/
CREATE OR REPLACE FUNCTION adempiere.addDays (day TIMESTAMP WITH TIME ZONE, days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,adempiere.ID(days));
END;
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.subtractdays (day TIMESTAMP WITH TIME ZONE, days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,adempiere.ID(days * -1));
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.addDays (day TIMESTAMP WITH TIME ZONE, days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,Idays);
END;
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.subtractdays (day TIMESTAMP WITH TIME ZONE, days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,days * -1);
END;
$$ LANGUAGE plpgsql VOLATILE;


-- TIMESTAMP
/*
CREATE OR REPLACE FUNCTION addDays (day TIMESTAMP , days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN addDays(CAST (day AS TIMESTAMP WITH TIME ZONE),ID(days));
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION subtractdays (day TIMESTAMP , days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN addDays(CAST (day AS TIMESTAMP WITH TIME ZONE),ID(days * -1));
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION addDays (day TIMESTAMP , days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN addDays(CAST (day AS TIMESTAMP WITH TIME ZONE),ID(days));
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION subtractdays (day TIMESTAMP , days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN addDays(CAST (day AS TIMESTAMP WITH TIME ZONE),ID(days * -1));
END;
$$ LANGUAGE plpgsql VOLATILE;*/

/** Product	**/
/*CREATE OR REPLACE FUNCTION adempiere.productAttribute (M_AttributeSetInstance_ID NUMERIC) 
RETURNS VARCHAR AS $$
BEGIN    
    RETURN adempiere.productAttribute(adempiere.ID(M_AttributeSetInstance_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomPriceLimit (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomPriceLimit(adempiere.ID(M_Product_ID),ID(M_PriceList_Version_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomPriceList (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomPriceList(adempiere.ID(M_Product_ID),adempiere.ID(M_PriceList_Version_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomPriceStd (M_Product_ID NUMERIC, M_PriceList_Version_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomPriceStd(adempiere.ID(M_Product_ID),adempiere.ID(M_PriceList_Version_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomQtyAvailable (M_Product_ID NUMERIC, M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomQtyAvailable(adempiere.ID(M_Product_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION bomQtyOnHand (M_Product_ID NUMERIC, M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN bomQtyOnHand(adempiere.ID(M_Product_ID),ID(M_Warehouse_ID),ID(M_Locator_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomQtyOrdered (M_Product_ID NUMERIC, M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomQtyOrdered(adempiere.ID(M_Product_ID),adempiere.ID(M_Warehouse_adempiere.ID),adempiere.ID(M_Locator_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bomQtyReserved (M_Product_ID NUMERIC, M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.bomQtyReserved(adempiere.ID(M_Product_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.bomQtyAvailable(M_Product_ID NUMERIC, M_AttributeSetInstance_ID NUMERIC,M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
  RETURNS NUMERIC AS
$BODY$
BEGIN
    RETURN adempiere.bomQtyAvailable(adempiere.ID(M_Product_ID),adempiere.ID(M_AttributeSetInstance_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$BODY$
LANGUAGE plpgsql VOLATILE;
--ALTER FUNCTION adempiere.bomqtyavailable("numeric", "numeric", "numeric", "numeric") OWNER TO adempiere;

CREATE OR REPLACE FUNCTION adempiere.bomQtyOnHand(M_Product_ID NUMERIC, M_AttributeSetInstance_ID NUMERIC,M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
  RETURNS NUMERIC AS
$BODY$
BEGIN
   RETURN adempiere.bomQtyOnHand(adempiere.ID(M_Product_ID), adempiere.ID(M_AttributeSetInstance_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$BODY$
LANGUAGE plpgsql VOLATILE;
--ALTER FUNCTION adempiere.bomqtyonhand("numeric", "numeric", "numeric", "numeric") OWNER TO adempiere;

CREATE OR REPLACE FUNCTION adempiere.bomQtyOrdered(M_Product_ID NUMERIC, M_AttributeSetInstance_ID NUMERIC,M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
  RETURNS NUMERIC AS
$BODY$
BEGIN
    RETURN adempiere.bomQtyOrdered(adempiere.ID(M_Product_ID), adempiere.ID(M_AttributeSetInstance_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$BODY$
LANGUAGE plpgsql VOLATILE;
--ALTER FUNCTION adempiere.bomqtyordered("numeric", "numeric", "numeric", "numeric") OWNER TO adempiere;

CREATE OR REPLACE FUNCTION adempiere.bomQtyReserved(M_Product_ID NUMERIC, M_AttributeSetInstance_ID INTEGER,M_Warehouse_ID NUMERIC, M_Locator_ID NUMERIC)
  RETURNS NUMERIC AS
$BODY$
BEGIN
    RETURN adempiere.bomQtyReserved(adempiere.ID(M_Product_ID), adempiere.ID(M_AttributeSetInstance_ID),adempiere.ID(M_Warehouse_ID),adempiere.ID(M_Locator_ID));
END;    
$BODY$
LANGUAGE plpgsql VOLATILE;
--ALTER FUNCTION adempiere.bomqtyreserved("numeric", "numeric", "numeric", "numeric") OWNER TO adempiere;
*
CREATE OR REPLACE FUNCTION adempiere.currencyBase (Amount NUMERIC, C_CurrencyFrom_ID NUMERIC, 
    	ConversionDate TIMESTAMP WITH TIME ZONE, AD_Client_ID NUMERIC, AD_Org_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.currencyBase (Amount,adempiere.ID(C_CurrencyFrom_ID),ConversionDate,adempiere.ID(AD_Client_ID),adempiere.ID(AD_Org_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.currencyConvert (Amount NUMERIC, C_CurrencyFrom_ID NUMERIC, C_CurrencyTo_ID NUMERIC,
        ConversionDate TIMESTAMP WITH TIME ZONE, C_ConversionType_ID NUMERIC, AD_Client_ID NUMERIC, AD_Org_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.currencyConvert (Amount ,adempiere.ID(C_CurrencyFrom_ID),adempiere.ID(C_CurrencyTo_ID),ConversionDate,adempiere.ID(C_ConversionType_ID),adempiere.ID(AD_Client_ID),adempiere.ID(AD_Org_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.currencyRate (C_CurrencyFrom_ID NUMERIC, C_CurrencyTo_ID NUMERIC,
        ConversionDate TIMESTAMP WITH TIME ZONE, C_ConversionType_ID NUMERIC, AD_Client_ID NUMERIC, AD_Org_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.currencyRate (adempiere.ID(C_CurrencyFrom_ID), adempiere.ID(C_CurrencyTo_ID),ConversionDate,adempiere.ID(C_ConversionType_ID),adempiere.ID(AD_Client_ID),adempiere.ID(AD_Org_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.bpartnerRemitLocation (p_C_BPartner_ID NUMERIC)
RETURNS INTEGER AS $$
BEGIN
    RETURN adempiere.partnerRemitLocation (adempiere.ID(p_C_BPartner_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.invoiceOpen (p_C_Invoice_ID NUMERIC, p_C_InvoicePaySchedule_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.invoiceOpen (adempiere.ID(p_C_Invoice_ID),adempiere.ID(p_C_InvoicePaySchedule_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.invoicePaid (p_C_Invoice_ID NUMERIC, p_C_Currency_ID NUMERIC, p_MultiplierAP NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.invoicePaid (adempiere.ID(p_C_Invoice_ID),adempiere.ID(p_C_Currency_ID),adempiere.ID(p_MultiplierAP));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.invoiceDiscount (p_C_Invoice_ID NUMERIC, p_PayDate TIMESTAMP WITH TIME ZONE, p_C_InvoicePaySchedule_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.invoiceDiscount (adempiere.ID(p_C_Invoice_ID), p_PayDate , adempiere.ID(p_C_InvoicePaySchedule_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.paymentTermDueDays (p_C_PaymentTerm_ID NUMERIC, p_DocDate TIMESTAMP WITH TIME ZONE, p_PayDate TIMESTAMP WITH TIME ZONE)
RETURNS INTEGER AS $$
BEGIN
    RETURN adempiere.paymentTermDueDays (adempiere.ID(p_C_PaymentTerm_ID), p_DocDate , p_PayDate);
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.paymentTermDueDate (p_C_PaymentTerm_ID NUMERIC, p_DocDate TIMESTAMP WITH TIME ZONE)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.paymentTermDueDays (adempiere.ID(p_C_PaymentTerm_ID), p_DocDate );
END;    
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.paymentTermDiscount (p_Amount NUMERIC, p_C_PaymentTerm_ID NUMERIC, p_DocDate TIMESTAMP WITH TIME ZONE, p_PayDate TIMESTAMP WITH TIME ZONE)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.paymentTermDiscount (p_Amount, adempiere.ID(p_C_PaymentTerm_ID), p_DocDate , p_PayDate );
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.paymentAllocated (p_C_Payment_ID NUMERIC, p_C_Currency_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.paymentAllocated (adempiere.ID(p_C_Payment_ID),adempiere.ID(p_C_Currency_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.paymentAvailable (p_C_Payment_ID NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.paymentAvailable (adempiere.ID(p_C_Payment_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.acctBalance (p_Account_ID NUMERIC, p_AmtDr NUMERIC, p_AmtCr NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    RETURN adempiere.acctBalance (adempiere.ID(p_Account_ID), p_AmtDr , p_AmtCr);
END;    
$$ LANGUAGE plpgsql VOLATILE;	

CREATE OR REPLACE FUNCTION adempiere.documentNo (p_MPC_MRP_ID NUMERIC)
RETURNS VARCHAR AS $$
BEGIN
    RETURN adempiere.documentNo(adempiere.ID(p_MPC_MRP_ID));
END;    
$$ LANGUAGE plpgsql VOLATILE;	
*/

DROP OPERATOR adempiere.+ (timestamptz, INTEGER);
CREATE OPERATOR adempiere.+ ( PROCEDURE = adempiere.adddays,
LEFTARG = TIMESTAMPTZ, RIGHTARG = INTEGER,
COMMUTATOR = +);

DROP OPERATOR adempiere.- (timestamptz, INTEGER);
CREATE OPERATOR adempiere.- ( PROCEDURE = adempiere. subtractdays,
LEFTARG = TIMESTAMPTZ, RIGHTARG = INTEGER,
COMMUTATOR = -);


/*DROP OPERATOR adempiere.+ (interval, numeric);
CREATE OPERATOR adempiere.+ ( PROCEDURE = adempiere.adddays,
LEFTARG = INTERVAL, RIGHTARG = NUMERIC,
COMMUTATOR = +);

DROP OPERATOR adempiere.- (interval, numeric);
CREATE OPERATOR adempiere.- ( PROCEDURE = adempiere.subtractdays,
LEFTARG = INTERVAL, RIGHTARG = NUMERIC,
COMMUTATOR = -);
*/ 

/*
DROP OPERATOR adempiere.+ (interval, integer);
CREATE OPERATOR adempiere.+ ( PROCEDURE = adempiere.adddays,
LEFTARG = INTERVAL, RIGHTARG = INTEGER,
COMMUTATOR = +);

DROP OPERATOR adempiere.- (interval, integer);
CREATE OPERATOR adempiere.- ( PROCEDURE = adempiere.subtractdays,
LEFTARG = INTERVAL, RIGHTARG = INTEGER,
COMMUTATOR = -);
*/

/*CREATE OR REPLACE FUNCTION nextID ( p_AD_Sequence_ID NUMERIC, p_System CHAR)
RETURNS NUMERIC AS $$
DECLARE
 o_NextID INTEGER := -1;
BEGIN
    IF (p_System = 'Y') THEN
    
    LOCK TABLE AD_Sequence IN ACCESS EXCLUSIVE MODE;
    --LOCK TABLE films IN SHARE ROW EXCLUSIVE MODE;
        SELECT CurrentNextSys
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --FOR UPDATE OF CurrentNextSys;
        --
        UPDATE AD_Sequence
          SET CurrentNextSys = CurrentNextSys + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --COMMIT;
        RETURN o_NextID;
    ELSE
    LOCK TABLE AD_Sequence IN ACCESS EXCLUSIVE MODE;
    --LOCK TABLE films IN SHARE ROW EXCLUSIVE MODE;
        SELECT CurrentNext
            INTO o_NextID
       FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --FOR UPDATE OF CurrentNext;
        --
        UPDATE AD_Sequence
          SET CurrentNext = CurrentNext + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
	--COMMIT;
        RETURN o_NextID;
    END IF;
    --
RAICE EXCEPTION 'Failed to update' ;
RETURN null;
END;    
$$ LANGUAGE plpgsql;
*/

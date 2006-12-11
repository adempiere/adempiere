DROP FUNCTION INCOICEDISCOUNT;

DROP FUNCTION INCOICEOPEN;

DROP FUNCTION INCOICEPAID;

DROP FUNCTION COMPIEREPROPERTIES;

DROP FUNCTION COMPIEREPROPERTY;

DROP FUNCTION COMPIEREVERSION;

DROP JAVA RESOURCE "META-INF/MANIFEST.MF";

DROP JAVA CLASS "org/compiere/sqlj/Account";

DROP JAVA CLASS "org/compiere/sqlj/BPartner";

DROP JAVA CLASS "org/compiere/sqlj/Compiere";

DROP JAVA CLASS "org/compiere/sqlj/Currency";

DROP JAVA CLASS "org/compiere/sqlj/Invoice";

DROP JAVA CLASS "org/compiere/sqlj/Payment";

DROP JAVA CLASS "org/compiere/sqlj/PaymentTerm";

DROP JAVA CLASS "org/compiere/sqlj/Product";

CREATE OR REPLACE FUNCTION paymentTermDiscount
(
	Amount			IN	NUMBER,
    Currency_ID     IN  NUMBER,
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_Discount.sql,v 1.5 2005/07/24 19:37:42 jjanke Exp $
 ***
 * Title:	Calculate Discount
 * Description:
 *	Calculate the allowable Discount Amount of the Payment Term
 *
 *	Test:	SELECT C_PaymentTerm_Discount(17777, 103, '10-DEC-1999') FROM DUAL
 ************************************************************************/

AS
	Discount			NUMBER := 0;
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	Discount1Date		DATE;
	Discount2Date		DATE;
	Add1Date			NUMBER := 0;
	Add2Date			NUMBER := 0;
BEGIN
	--	No Data - No Discount
	IF (Amount IS NULL OR PaymentTerm_ID IS NULL OR DocDate IS NULL) THEN
		RETURN 0;
	END IF;

	FOR p IN Cur_PT LOOP	--	for convineance only
--		DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		Discount1Date := TRUNC(DocDate + p.DiscountDays + p.GraceDays);
		Discount2Date := TRUNC(DocDate + p.DiscountDays2 + p.GraceDays);

		--	Next Business Day
		IF (p.IsNextBusinessDay='Y') THEN
			--	Not fully correct - only does weekends (7=Saturday, 1=Sunday)
			SELECT 	DECODE(TO_CHAR(Discount1Date,'D'), '7',2, '1',1, 0),
					DECODE(TO_CHAR(Discount2Date,'D'), '7',2, '1',1, 0)
			  INTO	Add1Date, Add2Date
			FROM 	DUAL;
			Discount1Date := Discount1Date+Add1Date;
			Discount2Date := Discount2Date+Add2Date;
		END IF;

		--	Discount 1
		IF (Discount1Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 1 ' || TO_CHAR(Discount1Date) || ' ' || p.Discount);
			Discount := Amount * p.Discount / 100;
		--	Discount 2
		ELSIF (Discount2Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 2 ' || TO_CHAR(Discount2Date) || ' ' || p.Discount2);
			Discount := Amount * p.Discount2 / 100;
		END IF;	
	END LOOP;
	--
    RETURN ROUND(NVL(Discount,0), 2);	--	fixed rounding
END paymentTermDiscount;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION paymentTermDueDate
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE
)
RETURN DATE
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_DueDate.sql,v 1.1 2005/05/09 22:13:22 jjanke Exp $
 ***
 * Title:	Get Due Date
 * Description:
 *	Returns the due date
 ************************************************************************/
AS
 	Days				NUMBER := 0;
	DueDate				DATE := TRUNC(DocDate);
	--
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	FirstDay			DATE;
	NoDays				NUMBER;
BEGIN
	FOR p IN Cur_PT LOOP	--	for convineance only
	--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN		
		--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Day = ' || p.FixMonthDay);
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := TRUNC(DocDate) - FirstDay;
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := ADD_MONTHS(DueDate, p.FixMonthOffset);
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := ADD_MONTHS(DueDate, 1);
			END IF;
		ELSE
		--	DBMS_OUTPUT.PUT_LINE('Net = ' || p.NetDays);
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;
--	DBMS_OUTPUT.PUT_LINE('Due = ' || TO_CHAR(DueDate) || ', Pay = ' || TO_CHAR(PayDate));

	RETURN DueDate;
END paymentTermDueDate;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION paymentTermDueDays
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_DueDays.sql,v 1.3 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:	Get Due Days
 * Description:
 *	Returns the days due (positive) or the days till due (negative)
 *	Grace days are not considered!
 *	If record is not found it assumes due immediately
 *
 *	Test:	SELECT C_PaymentTerm_DueDays(103, '01-DEC-2000', '15-DEC-2000') FROM DUAL
 ************************************************************************/
AS
 	Days				NUMBER := 0;
	DueDate				DATE := TRUNC(DocDate);
	--
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	FirstDay			DATE;
	NoDays				NUMBER;
BEGIN
	FOR p IN Cur_PT LOOP	--	for convineance only
	--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN		
		--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Day = ' || p.FixMonthDay);
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := TRUNC(DocDate) - FirstDay;
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := ADD_MONTHS(DueDate, p.FixMonthOffset);
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := ADD_MONTHS(DueDate, 1);
			END IF;
		ELSE
		--	DBMS_OUTPUT.PUT_LINE('Net = ' || p.NetDays);
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;
--	DBMS_OUTPUT.PUT_LINE('Due = ' || TO_CHAR(DueDate) || ', Pay = ' || TO_CHAR(PayDate));

	Days := TRUNC(PayDate) - DueDate;
	RETURN Days;
END paymentTermDueDays;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION currencyRound
(
	p_Amount		IN	NUMBER,
	p_CurTo_ID	IN	NUMBER,
	p_Costing		IN	VARCHAR2		--	Default 'N'
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Currency_Round.SQL,v 1.4 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:	Round amount for Traget Currency
 * Description:
 *		Round Amount using Costing or Standard Precision
 *		Returns unmodified amount if currency not found
 * Test:
 *		SELECT C_Currency_Round(C_Currency_Convert(100,116,100,null,null),100,null) FROM DUAL => 64.72 
 ************************************************************************/
AS
	v_StdPrecision		NUMBER;
	v_CostPrecision		NUMBER;
BEGIN
	--	Nothing to convert
	IF (p_Amount IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN p_Amount;
	END IF;

	--	Ger Precision
	SELECT	MAX(StdPrecision), MAX(CostingPrecision)
	  INTO	v_StdPrecision, v_CostPrecision
	FROM	C_Currency
	  WHERE	C_Currency_ID = p_CurTo_ID;
	--	Currency Not Found
	IF (v_StdPrecision IS NULL) THEN
		RETURN p_Amount;
	END IF;

	IF (p_Costing = 'Y') THEN
		RETURN ROUND (p_Amount, v_CostPrecision);
	END IF;

	RETURN ROUND (p_Amount, v_StdPrecision);
END currencyRound;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION daysBetween
(
    p_date1     DATE,
    p_date2     DATE
)
 	RETURN NUMBER
AS
BEGIN
    RETURN (TRUNC(p_date1) - TRUNC(p_date2));
END;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION currencyRate
(
	p_CurFrom_ID		IN	NUMBER,
	p_CurTo_ID		    IN	NUMBER,
	p_ConvDate		    IN	DATE,
	p_ConversionType_ID	IN	NUMBER,
	p_Client_ID		    IN	NUMBER,
	p_Org_ID			IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Currency_Rate.sql,v 1.8 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Return Conversion Rate
 * Description:
 *		from CurrencyFrom_ID to CurrencyTo_ID
 *		Returns NULL, if rate not found
 * Test
 *		SELECT C_Currency_Rate(116, 100, null, null) FROM DUAL;     => .647169
 *		SELECT C_Currency_Rate(116, 100) FROM DUAL;                 => .647169
 ************************************************************************/
AS
	--	Currency From variables
	cf_IsEuro			CHAR(1);
	cf_IsEMUMember		CHAR(1);
	cf_EMUEntryDate	DATE;
	cf_EMURate		NUMBER;
	--	Currency To variables
	ct_IsEuro			CHAR(1);
	ct_IsEMUMember		CHAR(1);
	ct_EMUEntryDate	DATE;
	ct_EMURate		NUMBER;
	--	Triangle
	v_CurrencyFrom		NUMBER;
	v_CurrencyTo		NUMBER;
	v_CurrencyEuro		NUMBER;
	--
	v_ConvDate		    DATE := SysDate;
	v_ConversionType_ID	NUMBER := 0;
	v_Rate			    NUMBER;
BEGIN
	--	No Conversion
	IF (p_CurFrom_ID = p_CurTo_ID) THEN
		RETURN 1;
	END IF;
	--	Default Date Parameter
	IF (p_ConvDate IS NOT NULL) THEN
		v_ConvDate := p_ConvDate;   --  SysDate
	END IF;
    --  Default Conversion Type
	IF (p_ConversionType_ID IS NULL OR p_ConversionType_ID = 0) THEN
        BEGIN
            SELECT C_ConversionType_ID 
              INTO v_ConversionType_ID
            FROM C_ConversionType 
            WHERE IsDefault='Y'
              AND AD_Client_ID IN (0,p_Client_ID)
              AND ROWNUM=1
            ORDER BY AD_Client_ID DESC;
        EXCEPTION WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Conversion Type Not Found');
        END;
    ELSE
        v_ConversionType_ID := p_ConversionType_ID;
	END IF;

	--	Get Currency Info
	SELECT	MAX(IsEuro), MAX(IsEMUMember), MAX(EMUEntryDate), MAX(EMURate)
	  INTO	cf_IsEuro, cf_IsEMUMember, cf_EMUEntryDate, cf_EMURate
	FROM		C_Currency
	  WHERE	C_Currency_ID = p_CurFrom_ID;
	-- Not Found
	IF (cf_IsEuro IS NULL) THEN
		DBMS_OUTPUT.PUT_LINE('From Currency Not Found');
		RETURN NULL;
	END IF;
	SELECT	MAX(IsEuro), MAX(IsEMUMember), MAX(EMUEntryDate), MAX(EMURate)
	  INTO	ct_IsEuro, ct_IsEMUMember, ct_EMUEntryDate, ct_EMURate
	FROM		C_Currency
	  WHERE	C_Currency_ID = p_CurTo_ID;
	-- Not Found
	IF (ct_IsEuro IS NULL) THEN
		DBMS_OUTPUT.PUT_LINE('To Currency Not Found');
		RETURN NULL;
	END IF;

	--	Fixed - From Euro to EMU
	IF (cf_IsEuro = 'Y' AND ct_IsEMUMember ='Y' AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN ct_EMURate;
	END IF;

	--	Fixed - From EMU to Euro
	IF (ct_IsEuro = 'Y' AND cf_IsEMUMember ='Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
		RETURN 1 / cf_EMURate;
	END IF;

	--	Fixed - From EMU to EMU
	IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'
			AND v_ConvDate >= cf_EMUEntryDate AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN ct_EMURate / cf_EMURate;
	END IF;

	--	Flexible Rates
	v_CurrencyFrom := p_CurFrom_ID;
	v_CurrencyTo := p_CurTo_ID;

	-- if EMU Member involved, replace From/To Currency
	IF ((cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate)
	  OR (ct_isEMUMember = 'Y' AND v_ConvDate >= ct_EMUEntryDate)) THEN
		SELECT	MAX(C_Currency_ID)
		  INTO	v_CurrencyEuro
		FROM		C_Currency
		WHERE	IsEuro = 'Y';
		-- Conversion Rate not Found
		IF (v_CurrencyEuro IS NULL) THEN
			DBMS_OUTPUT.PUT_LINE('Euro Not Found');
			RETURN NULL;
		END IF;
		IF (cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
			v_CurrencyFrom := v_CurrencyEuro;
		ELSE
			v_CurrencyTo := v_CurrencyEuro;
		END IF;
	END IF;

	--	Get Rate
	DECLARE
		CURSOR	CUR_Rate	IS
			SELECT	MultiplyRate
			FROM	C_Conversion_Rate
			WHERE	C_Currency_ID=v_CurrencyFrom AND C_Currency_ID_To=v_CurrencyTo
			  AND	C_ConversionType_ID=v_ConversionType_ID
			  AND	v_ConvDate BETWEEN ValidFrom AND ValidTo
			  AND	AD_Client_ID IN (0,p_Client_ID) AND AD_Org_ID IN (0,p_Org_ID)
			ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC;
	BEGIN
		FOR c IN CUR_Rate LOOP
			v_Rate := c.MultiplyRate;
			EXIT;	--	only first
		END LOOP;
	END;
	--	Not found
	IF (v_Rate IS NULL) THEN
		DBMS_OUTPUT.PUT_LINE('Conversion Rate Not Found');
		RETURN NULL;
	END IF;

	--	Currency From was EMU
	IF (cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
		RETURN v_Rate / cf_EMURate;
	END IF;

	--	Currency To was EMU
	IF (ct_isEMUMember = 'Y' AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN v_Rate * ct_EMURate;
	END IF;

	RETURN v_Rate;

EXCEPTION WHEN OTHERS THEN
	DBMS_OUTPUT.PUT_LINE(SQLERRM);
	RETURN NULL;

END currencyRate;
/

SHOW ERRORS;

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
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Currency_Convert.sql,v 1.11 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Convert Amount (using IDs)
 * Description:
 *		from CurrencyFrom_ID to CurrencyTo_ID
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT C_Currency_Convert(100,116,100,null,null) FROM DUAL  => 64.72
 *		SELECT C_Currency_Convert(100,116,100) FROM DUAL            => 64.72
 ************************************************************************/
AS
	v_Rate				NUMBER;
BEGIN
	--	Return Amount
	IF (p_Amount = 0 OR p_CurFrom_ID = p_CurTo_ID) THEN
		RETURN p_Amount;
	END IF;
	--	Return NULL
	IF (p_Amount IS NULL OR p_CurFrom_ID IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN NULL;
	END IF;

	--	Get Rate
	v_Rate := currencyRate (p_CurFrom_ID, p_CurTo_ID, p_ConvDate, p_ConversionType_ID, p_Client_ID, p_Org_ID);
	IF (v_Rate IS NULL) THEN
		RETURN NULL;
	END IF;

	--	Standard Precision
	RETURN currencyRound(p_Amount * v_Rate, p_CurTo_ID, null);	
END currencyConvert;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION currencyBase
(
	p_Amount			IN	NUMBER,
	p_CurFrom_ID		IN	NUMBER,
	p_ConvDate			IN	DATE,
	p_Client_ID			IN	NUMBER,
	p_Org_ID			IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Base_Convert.sql,v 1.8 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Convert Amount to Base Currency of Client
 * Description:
 *		Get CurrencyTo from Client
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT C_Base_Convert(100,116,11,null) FROM DUAL => 64.72
 ************************************************************************/
AS
	v_CurTo_ID			NUMBER;
BEGIN
	--	Get Currency
	SELECT	MAX(ac.C_Currency_ID)
	  INTO	v_CurTo_ID
	FROM	AD_ClientInfo ci, C_AcctSchema ac
	WHERE	ci.C_AcctSchema1_ID=ac.C_AcctSchema_ID
	  AND	ci.AD_Client_ID=p_Client_ID;
	--	Same as Currency_Conversion - if currency/rate not found - return 0
	IF (v_CurTo_ID IS NULL) THEN
		RETURN NULL;
	END IF;
	--	Same currency
	IF (p_CurFrom_ID = v_CurTo_ID) THEN
		RETURN p_Amount;
	END IF;

	RETURN currencyConvert (p_Amount, p_CurFrom_ID, v_CurTo_ID, p_ConvDate, null, p_Client_ID, p_Org_ID);
END currencyBase;
/

SHOW ERRORS;

CREATE OR REPLACE FUNCTION acctBalance
(
    p_Account_ID    IN NUMBER,
    p_AmtDr         IN NUMBER,
    p_AmtCr         IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2004 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Acct_Balance.sql,v 1.5 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Aclculate Balance based on Account Sign + Type
 * Description:
 *  If an account is specified and found
 *  - If the account sign is Natural it sets it based on Account Type
 *  Returns Credit or Debit Balance
 * Test:
    SELECT Acct_Balance (0,11,22) FROM DUAL
    SELECT AccountType, AccountSign, 
        Acct_Balance(C_ElementValue_ID, 20, 10) "DR Balance",
        Acct_Balance(C_ElementValue_ID, 10, 20) "CR Balance"
    FROM C_ElementValue
    WHERE AccountSign<>'N'
    ORDER BY AccountSign
 ************************************************************************/
AS
    v_balance           NUMBER;
    v_AccountType       C_ElementValue.AccountType%TYPE;
    v_AccountSign       C_ElementValue.AccountSign%TYPE;
BEGIN
    v_balance := p_AmtDr - p_AmtCr;
    --  
    IF (p_Account_ID > 0) THEN
        SELECT AccountType, AccountSign
          INTO v_AccountType, v_AccountSign
        FROM C_ElementValue
        WHERE C_ElementValue_ID=p_Account_ID;
   --   DBMS_OUTPUT.PUT_LINE('Type=' || v_AccountType || ' - Sign=' || v_AccountSign);
        --  Natural Account Sign
        IF (v_AccountSign='N') THEN
            IF (v_AccountType IN ('A','E')) THEN
                v_AccountSign := 'D';
            ELSE
                v_AccountSign := 'C';
            END IF;
        --  DBMS_OUTPUT.PUT_LINE('Type=' || v_AccountType || ' - Sign=' || v_AccountSign);
        END IF;
        --  Debit Balance
        IF (v_AccountSign = 'C') THEN
            v_balance := p_AmtCr - p_AmtDr;
        END IF;
    END IF;
    --
    RETURN v_balance;
EXCEPTION WHEN OTHERS THEN
    -- In case Acct not found
    RETURN  p_AmtDr - p_AmtCr;
END acctBalance;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomQtyOnHand
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity on hand for BOM
 */
AS
	myWarehouse_ID	NUMBER;
 	Quantity		NUMBER := 99999;	--	unlimited
	IsBOM			CHAR(1);
	IsStocked		CHAR(1);
	ProductType		CHAR(1);
 	ProductQty		NUMBER;
	StdPrecision	NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=Product_ID;
	--
BEGIN
	--	Check Parameters
	myWarehouse_ID := Warehouse_ID;
	IF (myWarehouse_ID IS NULL) THEN
		IF (Locator_ID IS NULL) THEN
			RETURN 0;
		ELSE
			SELECT 	SUM(M_Warehouse_ID) INTO myWarehouse_ID
			FROM	M_Locator
			WHERE	M_Locator_ID=Locator_ID;
		END IF;
	END IF;
	IF (myWarehouse_ID IS NULL) THEN
		RETURN 0;
	END IF;
--	DBMS_OUTPUT.PUT_LINE('Warehouse=' || myWarehouse_ID);

	--	Check, if product exists and if it is stocked
	BEGIN
		SELECT	IsBOM, ProductType, IsStocked
	 	  INTO	IsBOM, ProductType, IsStocked
		FROM M_Product
		WHERE M_Product_ID=Product_ID;
		--
	EXCEPTION	--	not found
		WHEN OTHERS THEN
			RETURN 0;
	END;
	--	Unimited capacity if no item
	IF (IsBOM='N' AND (ProductType<>'I' OR IsStocked='N')) THEN
		RETURN Quantity;
	--	Stocked item
	ELSIF (IsStocked='Y') THEN
		--	Get ProductQty
		SELECT 	NVL(SUM(QtyOnHand), 0)
		  INTO	ProductQty
		FROM 	M_Storage s
		WHERE M_Product_ID=Product_ID
		  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
		  	AND l.M_Warehouse_ID=myWarehouse_ID);
		--
	--	DBMS_OUTPUT.PUT_LINE('Qty=' || ProductQty);
		RETURN ProductQty;
	END IF;

	--	Go though BOM
--	DBMS_OUTPUT.PUT_LINE('BOM');
	FOR bom IN CUR_BOM LOOP
		--	Stocked Items "leaf node"
		IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
			--	Get ProductQty
			SELECT 	NVL(SUM(QtyOnHand), 0)
			  INTO	ProductQty
			FROM 	M_Storage s
			WHERE M_Product_ID=bom.M_ProductBOM_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=myWarehouse_ID);
			--	Get Rounding Precision
			SELECT 	NVL(MAX(u.StdPrecision), 0)
			  INTO	StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom.M_ProductBOM_ID;
			--	How much can we make with this product
			ProductQty := ROUND (ProductQty/bom.BOMQty, StdPrecision);
			--	How much can we make overall
			IF (ProductQty < Quantity) THEN
				Quantity := ProductQty;
			END IF;
		--	Another BOM
		ELSIF (bom.IsBOM = 'Y') THEN
			ProductQty := bomQtyOnHand (bom.M_ProductBOM_ID, myWarehouse_ID, Locator_ID);
			--	How much can we make overall
			IF (ProductQty < Quantity) THEN
				Quantity := ProductQty;
			END IF;
		END IF;
	END LOOP;	--	BOM

	IF (Quantity > 0) THEN
		--	Get Rounding Precision for Product
		SELECT 	NVL(MAX(u.StdPrecision), 0)
		  INTO	StdPrecision
		FROM 	C_UOM u, M_Product p 
		WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=Product_ID;
		--
		RETURN ROUND (Quantity, StdPrecision);
	END IF;
	RETURN 0;
END bomQtyOnHand;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomQtyReserved
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity reserved for BOM
 */
AS
	v_Warehouse_ID		NUMBER;
 	v_Quantity			NUMBER := 99999;	--	unlimited
	v_IsBOM				CHAR(1);
	v_IsStocked			CHAR(1);
	v_ProductType		CHAR(1);
 	v_ProductQty		NUMBER;
	v_StdPrecision		NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=p_Product_ID;
	--
BEGIN
	--	Check Parameters
	v_Warehouse_ID := p_Warehouse_ID;
	IF (v_Warehouse_ID IS NULL) THEN
		IF (p_Locator_ID IS NULL) THEN
			RETURN 0;
		ELSE
			SELECT 	MAX(M_Warehouse_ID) INTO v_Warehouse_ID
			FROM	M_Locator
			WHERE	M_Locator_ID=p_Locator_ID;
		END IF;
	END IF;
	IF (v_Warehouse_ID IS NULL) THEN
		RETURN 0;
	END IF;
--	DBMS_OUTPUT.PUT_LINE('Warehouse=' || v_Warehouse_ID);

	--	Check, if product exists and if it is stocked
	BEGIN
		SELECT	IsBOM, ProductType, IsStocked
		  INTO	v_IsBOM, v_ProductType, v_IsStocked
		FROM M_Product
		WHERE M_Product_ID=p_Product_ID;
		--
	EXCEPTION	--	not found
		WHEN OTHERS THEN
			RETURN 0;
	END;

	--	No reservation for non-stocked
	IF (v_IsBOM='N' AND (v_ProductType<>'I' OR v_IsStocked='N')) THEN
		RETURN 0;
	--	Stocked item
	ELSIF (v_IsStocked='Y') THEN
		--	Get ProductQty
		SELECT 	NVL(SUM(QtyReserved), 0)
		  INTO	v_ProductQty
		FROM 	M_Storage s
		WHERE M_Product_ID=p_Product_ID
		  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
		  	AND l.M_Warehouse_ID=v_Warehouse_ID);
		--
		RETURN v_ProductQty;
	END IF;

	--	Go though BOM
--	DBMS_OUTPUT.PUT_LINE('BOM');
	FOR bom IN CUR_BOM LOOP
		--	Stocked Items "leaf node"
		IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
			--	Get ProductQty
			SELECT 	NVL(SUM(QtyReserved), 0)
			  INTO	v_ProductQty
			FROM 	M_Storage s
			WHERE 	M_Product_ID=bom.M_ProductBOM_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=v_Warehouse_ID);
			--	Get Rounding Precision
			SELECT 	NVL(MAX(u.StdPrecision), 0)
			  INTO	v_StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom.M_ProductBOM_ID;
			--	How much can we make with this product
			v_ProductQty := ROUND (v_ProductQty/bom.BOMQty, v_StdPrecision);
			--	How much can we make overall
			IF (v_ProductQty < v_Quantity) THEN
				v_Quantity := v_ProductQty;
			END IF;
		--	Another BOM
		ELSIF (bom.IsBOM = 'Y') THEN
			v_ProductQty := bomQtyReserved (bom.M_ProductBOM_ID, v_Warehouse_ID, p_Locator_ID);
			--	How much can we make overall
			IF (v_ProductQty < v_Quantity) THEN
				v_Quantity := v_ProductQty;
			END IF;
		END IF;
	END LOOP;	--	BOM

	--	Unlimited (e.g. only services)
	IF (v_Quantity = 99999) THEN
		RETURN 0;
	END IF;

	IF (v_Quantity > 0) THEN
		--	Get Rounding Precision for Product
		SELECT 	NVL(MAX(u.StdPrecision), 0)
		  INTO	v_StdPrecision
		FROM 	C_UOM u, M_Product p 
		WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
		--
		RETURN ROUND (v_Quantity, v_StdPrecision);
	END IF;
	RETURN 0;
END bomQtyReserved;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomPriceLimit
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: BOM_PriceLimit.sql,v 1.4 2005/02/04 16:33:46 jjanke Exp $
 ***
 * Title:	Return Limit Price of Product/BOM
 * Description:
 *			if not found: 0
 ************************************************************************/
AS
	v_Price			NUMBER;
	v_ProductPrice	NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=Product_ID;
	--
BEGIN
	--	Try to get price from PriceList directly
	SELECT	COALESCE (SUM(PriceLimit), 0)
      INTO	v_Price
   	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;
--	DBMS_OUTPUT.PUT_LINE('Price=' || v_Price);

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN CUR_BOM LOOP
			v_ProductPrice := bomPriceLimit (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		END LOOP;	
	END IF;
	--
	RETURN v_Price;
END bomPriceLimit;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomPriceList
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: BOM_PriceList.sql,v 1.4 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:	Return List Price of Product/BOM
 * Description:
 *			if not found: 0
 ************************************************************************/
AS
	v_Price			NUMBER;
	v_ProductPrice	NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=Product_ID;
	--
BEGIN
	--	Try to get price from pricelist directly
	SELECT	COALESCE (SUM(PriceList), 0)
      INTO	v_Price
   	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;
--	DBMS_OUTPUT.PUT_LINE('Price=' || Price);

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN CUR_BOM LOOP
			v_ProductPrice := bomPriceList (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		--	DBMS_OUTPUT.PUT_LINE('Qry=' || bom.BOMQty || ' @ ' || v_ProductPrice || ', Price=' || v_Price);
		END LOOP;	--	BOM
	END IF;
	--
	RETURN v_Price;
END bomPriceList;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomQtyAvailable
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity available for BOM
 */
AS
BEGIN
	RETURN bomQtyOnHand(Product_ID, Warehouse_ID, Locator_ID)
		- bomQtyReserved(Product_ID, Warehouse_ID, Locator_ID);
END bomQtyAvailable;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomPriceStd
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: BOM_PriceStd.sql,v 1.4 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:	Return Standard Price of Product/BOM
 * Description:
 *			if not found: 0
 ************************************************************************/
AS
	v_Price			NUMBER;
	v_ProductPrice	NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=Product_ID;
	--
BEGIN
	--	Try to get price from pricelist directly
	SELECT	COALESCE(SUM(PriceStd), 0)
      INTO	v_Price
   	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;
--	DBMS_OUTPUT.PUT_LINE('Price=' || v_Price);

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN CUR_BOM LOOP
			v_ProductPrice := bomPriceStd (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		--	DBMS_OUTPUT.PUT_LINE('Price=' || v_Price);
		END LOOP;	--	BOM
	END IF;
	--
	RETURN v_Price;
END bomPriceStd;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION invoiceDiscount
(
	p_C_Invoice_ID		        IN NUMBER,
	p_PayDate			        IN	DATE,
	p_C_InvoicePaySchedule_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Invoice_Discount.sql,v 1.6 2005/07/24 19:37:42 jjanke Exp $
 ***
 * Title:	Calculate Payment Discount Amount
 * Description:
 *			- Calculate discountable amount (i.e. with or without tax)
 *			- Calculate and return payment discount
 ************************************************************************/
AS
	v_Amount			NUMBER;
	v_IsDiscountLineAmt	CHAR(1);
	v_GrandTotal		NUMBER;
	v_TotalLines		NUMBER;
	v_C_PaymentTerm_ID	NUMBER(10);
	v_DocDate			DATE;
	v_PayDate			DATE := SysDate;
    v_IsPayScheduleValid    CHAR(1);

BEGIN
	SELECT 	ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines,
		i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid
	  INTO 	v_IsDiscountLineAmt, v_GrandTotal, v_TotalLines,
		v_C_PaymentTerm_ID, v_DocDate, v_IsPayScheduleValid
	FROM 	AD_ClientInfo ci, C_Invoice i
	WHERE 	ci.AD_Client_ID=i.AD_Client_ID
	  AND 	i.C_Invoice_ID=p_C_Invoice_ID;
	--	What Amount is the Discount Base?
 	IF (v_IsDiscountLineAmt = 'Y') THEN
		v_Amount := v_TotalLines;
	ELSE
		v_Amount := v_GrandTotal;
	END IF;

	--	Anything to discount?
	IF (v_Amount = 0) THEN
		RETURN 0;
   	END IF;
	IF (p_PayDate IS NOT NULL) THEN
		v_PayDate := p_PayDate;
  	END IF;

    --  Valid Payment Schedule
    IF (v_IsPayScheduleValid='Y' AND p_C_InvoicePaySchedule_ID > 0) THEN
        SELECT COALESCE(MAX(DiscountAmt),0)
          INTO v_Amount
        FROM C_InvoicePaySchedule
        WHERE C_InvoicePaySchedule_ID=p_C_InvoicePaySchedule_ID
          AND DiscountDate <= v_PayDate;
        --
        RETURN v_Amount;
    END IF;

	--	return discount amount	
	RETURN paymentTermDiscount (v_Amount, 0, v_C_PaymentTerm_ID, v_DocDate, p_PayDate);

--	Most likely if invoice not found
EXCEPTION
	WHEN OTHERS THEN
		RETURN NULL;
END invoiceDiscount;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION invoiceOpen
(
	p_C_Invoice_ID	            IN	NUMBER,
    p_C_InvoicePaySchedule_ID   IN  NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Invoice_Open.sql,v 1.16 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Calculate Open Item Amount in Invoice Currency 
 * Description:
 *	Add up total amount open for C_Invoice_ID if no split payment.
 *  Grand Total minus Sum of Allocations in Invoice Currency
 *
 *  For Split Payments:
 *  Allocate Payments starting from first schedule.

SELECT C_Invoice_Open (109) FROM DUAL;
SELECT C_Invoice_Open (109, null) FROM DUAL;
SELECT C_Invoice_Open (109, 11) FROM DUAL;
SELECT C_Invoice_Open (109, 102) FROM DUAL;
SELECT C_Invoice_Open (109, 103) FROM DUAL;
SELECT * FROM RV_OpenItem WHERE C_Invoice_ID=109;
SELECT C_InvoicePaySchedule_ID, DueAmt FROM C_InvoicePaySchedule WHERE C_Invoice_ID=109 ORDER BY DueDate;

 *  Cannot be used for IsPaid as mutating
 ************************************************************************/
AS
	v_Currency_ID		NUMBER(10);
	v_TotalOpenAmt  	NUMBER := 0;
	v_PaidAmt  	        NUMBER := 0;
	v_Remaining	        NUMBER := 0;
    v_MultiplierAP      NUMBER := 0;
    v_MultiplierCM      NUMBER := 0;
    v_Temp              NUMBER := 0;
    --
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, 
            al.Amount, al.DiscountAmt, al.WriteOffAmt, 
            a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Invoice_ID = p_C_Invoice_ID
          AND   a.IsActive='Y';
    --
	CURSOR	Cur_PaySchedule	IS
        SELECT  C_InvoicePaySchedule_ID, DueAmt 
        FROM    C_InvoicePaySchedule 
		WHERE	C_Invoice_ID = p_C_Invoice_ID
          AND   IsValid='Y'
        ORDER BY DueDate;

BEGIN
	--	Get Currency
	BEGIN
		SELECT	MAX(C_Currency_ID), SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier)
		  INTO	v_Currency_ID, v_TotalOpenAmt, v_MultiplierAP, v_MultiplierCM
		FROM	C_Invoice_v		--	corrected for CM / Split Payment
		WHERE	C_Invoice_ID = p_C_Invoice_ID;
	EXCEPTION	--	Invoice in draft form
		WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('InvoiceOpen - ' || SQLERRM);
			RETURN NULL;
	END;
--  DBMS_OUTPUT.PUT_LINE('== C_Invoice_ID=' || p_C_Invoice_ID || ', Total=' || v_TotalOpenAmt || ', AP=' || v_MultiplierAP || ', CM=' || v_MultiplierCM);

	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
        v_Temp := a.Amount + a.DisCountAmt + a.WriteOffAmt;
		v_PaidAmt := v_PaidAmt
        -- Allocation
			+ currencyConvert(v_Temp * v_MultiplierAP,
				a.C_Currency_ID, v_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
      DBMS_OUTPUT.PUT_LINE('   PaidAmt=' || v_PaidAmt || ', Allocation=' || v_Temp || ' * ' || v_MultiplierAP);
	END LOOP;
    
    --  Do we have a Payment Schedule ?
    IF (p_C_InvoicePaySchedule_ID > 0) THEN --   if not valid = lists invoice amount
        v_Remaining := v_PaidAmt;
        FOR s IN Cur_PaySchedule LOOP
            IF (s.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
                v_TotalOpenAmt := (s.DueAmt*v_MultiplierCM) + v_Remaining;
                IF (s.DueAmt - v_Remaining < 0) THEN
                    v_TotalOpenAmt := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Sched Total=' || v_TotalOpenAmt || ', Due=' || s.DueAmt || ',Remaining=' || v_Remaining || ',CM=' || v_MultiplierCM);
            ELSE -- calculate amount, which can be allocated to next schedule
                v_Remaining := v_Remaining - s.DueAmt;
                IF (v_Remaining < 0) THEN
                    v_Remaining := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Remaining=' || v_Remaining);
            END IF;
        END LOOP;
    ELSE
        v_TotalOpenAmt := v_TotalOpenAmt - v_PaidAmt;
    END IF;
--  DBMS_OUTPUT.PUT_LINE('== Total=' || v_TotalOpenAmt);

	--	Ignore Rounding
	IF (v_TotalOpenAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_TotalOpenAmt := 0;
	END IF;
    
	--	Round to penny
	v_TotalOpenAmt := ROUND(COALESCE(v_TotalOpenAmt,0), 2);
	RETURN	v_TotalOpenAmt;
END invoiceOpen;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION invoicePaid
(
	p_C_Invoice_ID		IN	NUMBER,
	p_C_Currency_ID	    IN	NUMBER,
	p_MultiplierAP		IN	NUMBER	-- DEFAULT 1
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Invoice_Paid.sql,v 1.14 2005/04/27 17:48:07 jjanke Exp $
 ***
 * Title:	Calculate Paid/Allocated amount in Currency
 * Description:
 *	Add up total amount paid for for C_Invoice_ID.
 *  Split Payments are ignored.
 *  all allocation amounts  converted to invoice C_Currency_ID
 *	round it to the nearest cent
 *	and adjust for CreditMemos by using C_Invoice_v
 *  and for Payments with the multiplierAP (-1, 1)
 *
    SELECT C_Invoice_ID, IsPaid, IsSOTrx, GrandTotal, 
    C_Invoice_Paid (C_Invoice_ID, C_Currency_ID, MultiplierAP)
    FROM C_Invoice_v;
    --
    UPDATE C_Invoice_v1	
 	SET IsPaid = CASE WHEN C_Invoice_Paid(C_Invoice_ID,C_Currency_ID,MultiplierAP)=GrandTotal THEN 'Y' ELSE 'N' END 
    WHERE C_Invoice_ID>1000000
 *	
 ************************************************************************/
AS
	v_MultiplierAP		NUMBER := 1;
	v_PaymentAmt		NUMBER := 0;
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, 
            al.Amount, al.DiscountAmt, al.WriteOffAmt, 
            a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Invoice_ID = p_C_Invoice_ID
          AND   a.IsActive='Y';
BEGIN
	--	Default
	IF (p_MultiplierAP IS NOT NULL) THEN
		v_MultiplierAP := p_MultiplierAP;
	END IF;
	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
		v_PaymentAmt := v_PaymentAmt
			+ currencyConvert(a.Amount + a.DisCountAmt + a.WriteOffAmt,
				a.C_Currency_ID, p_C_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
	END LOOP;
	--
	RETURN	ROUND(NVL(v_PaymentAmt,0), 2) * v_MultiplierAP;
END invoicePaid;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMBER,
	p_C_Currency_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Payment_Allocated.sql,v 1.8 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:	Calculate Allocated Payment Amount in Payment Currency
 * Description:
    --
    SELECT C_Payment_Allocated(C_Payment_ID,C_Currency_ID), PayAmt, IsAllocated
    FROM C_Payment_v 
    WHERE C_Payment_ID>=1000000;
    --
    UPDATE C_Payment_v 
    SET IsAllocated=CASE WHEN C_Payment_Allocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE C_Payment_ID>=1000000;
 
 ************************************************************************/
AS
	v_AllocatedAmt		NUMBER := 0;
    v_PayAmt            NUMBER;
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
          AND   a.IsActive='Y';
		--  AND	al.C_Invoice_ID IS NOT NULL;
BEGIN
    --  Charge - nothing available
    SELECT MAX(PayAmt) 
      INTO v_PayAmt
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_PayAmt IS NOT NULL) THEN
        RETURN 0;
    END IF;
    
	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
		v_AllocatedAmt := v_AllocatedAmt
			+ currencyConvert(a.Amount, a.C_Currency_ID, p_C_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
	END LOOP;
	--	Round to penny
	v_AllocatedAmt := ROUND(NVL(v_AllocatedAmt,0), 2);
	RETURN	v_AllocatedAmt;
END paymentAllocated;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomQtyOrdered
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity ordered for BOM
 */
AS
	v_Warehouse_ID		NUMBER;
 	v_Quantity			NUMBER := 99999;	--	unlimited
	v_IsBOM				CHAR(1);
	v_IsStocked			CHAR(1);
	v_ProductType		CHAR(1);
 	v_ProductQty		NUMBER;
	v_StdPrecision		NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=p_Product_ID;
	--
BEGIN
	--	Check Parameters
	v_Warehouse_ID := p_Warehouse_ID;
	IF (v_Warehouse_ID IS NULL) THEN
		IF (p_Locator_ID IS NULL) THEN
			RETURN 0;
		ELSE
			SELECT 	MAX(M_Warehouse_ID) INTO v_Warehouse_ID
			FROM	M_Locator
			WHERE	M_Locator_ID=p_Locator_ID;
		END IF;
	END IF;
	IF (v_Warehouse_ID IS NULL) THEN
		RETURN 0;
	END IF;
--	DBMS_OUTPUT.PUT_LINE('Warehouse=' || v_Warehouse_ID);

	--	Check, if product exists and if it is stocked
	BEGIN
		SELECT	IsBOM, ProductType, IsStocked
		  INTO	v_IsBOM, v_ProductType, v_IsStocked
		FROM 	M_Product
		WHERE 	M_Product_ID=p_Product_ID;
		--
	EXCEPTION	--	not found
		WHEN OTHERS THEN
			RETURN 0;
	END;

	--	No reservation for non-stocked
	IF (v_IsBOM='N' AND (v_ProductType<>'I' OR v_IsStocked='N')) THEN
		RETURN 0;
	--	Stocked item
	ELSIF (v_IsStocked='Y') THEN
		--	Get ProductQty
		SELECT 	NVL(SUM(QtyOrdered), 0)
		  INTO	v_ProductQty
		FROM 	M_Storage s
		WHERE 	M_Product_ID=p_Product_ID
		  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
		  	AND l.M_Warehouse_ID=v_Warehouse_ID);
		--
		RETURN v_ProductQty;
	END IF;

	--	Go though BOM
--	DBMS_OUTPUT.PUT_LINE('BOM');
	FOR bom IN CUR_BOM LOOP
		--	Stocked Items "leaf node"
		IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
			--	Get ProductQty
			SELECT 	NVL(SUM(QtyOrdered), 0)
			  INTO	v_ProductQty
			FROM 	M_Storage s
			WHERE 	M_Product_ID=bom.M_ProductBOM_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=v_Warehouse_ID);
			--	Get Rounding Precision
			SELECT 	NVL(MAX(u.StdPrecision), 0)
			  INTO	v_StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom.M_ProductBOM_ID;
			--	How much can we make with this product
			v_ProductQty := ROUND (v_ProductQty/bom.BOMQty, v_StdPrecision);
			--	How much can we make overall
			IF (v_ProductQty < v_Quantity) THEN
				v_Quantity := v_ProductQty;
			END IF;
		--	Another BOM
		ELSIF (bom.IsBOM = 'Y') THEN
			v_ProductQty := bomQtyOrdered (bom.M_ProductBOM_ID, v_Warehouse_ID, p_Locator_ID);
			--	How much can we make overall
			IF (v_ProductQty < v_Quantity) THEN
				v_Quantity := v_ProductQty;
			END IF;
		END IF;
	END LOOP;	--	BOM

	--	Unlimited (e.g. only services)
	IF (v_Quantity = 99999) THEN
		RETURN 0;
	END IF;

	IF (v_Quantity > 0) THEN
		--	Get Rounding Precision for Product
		SELECT 	NVL(MAX(u.StdPrecision), 0)
		  INTO	v_StdPrecision
		FROM 	C_UOM u, M_Product p 
		WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
		--
		RETURN ROUND (v_Quantity, v_StdPrecision);
	END IF;
	--
	RETURN 0;
END bomQtyOrdered;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION paymentAvailable
(
	p_C_Payment_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Payment_Available.sql,v 1.10 2005/02/04 16:33:46 jjanke Exp $
 ***
 * Title:	Calculate Available Payment Amount in Payment Currency
 * Description:
 *		similar to C_Invoice_Open
 ************************************************************************/
AS
	v_Currency_ID		NUMBER(10);
	v_AvailableAmt		NUMBER := 0;
    v_IsReceipt         C_Payment.IsReceipt%TYPE;
    v_Amt               NUMBER := 0;
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
          AND   a.IsActive='Y';
		--  AND	al.C_Invoice_ID IS NOT NULL;
BEGIN
    --  Charge - fully allocated
    SELECT MAX(PayAmt) 
      INTO v_Amt
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_Amt IS NOT NULL) THEN
        RETURN v_Amt;
    END IF;

	--	Get Currency
	SELECT	C_Currency_ID, PayAmt, IsReceipt
	  INTO	v_Currency_ID, v_AvailableAmt, v_IsReceipt
	FROM	C_Payment_v     -- corrected for AP/AR
	WHERE	C_Payment_ID = p_C_Payment_ID;
--  DBMS_OUTPUT.PUT_LINE('== C_Payment_ID=' || p_C_Payment_ID || ', PayAmt=' || v_AvailableAmt || ', Receipt=' || v_IsReceipt);

	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
        v_Amt := currencyConvert(a.Amount, a.C_Currency_ID, v_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
	    v_AvailableAmt := v_AvailableAmt - v_Amt;
--      DBMS_OUTPUT.PUT_LINE('  Allocation=' || a.Amount || ' - Available=' || v_AvailableAmt);
	END LOOP;
	--	Ignore Rounding
	IF (v_AvailableAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_AvailableAmt := 0;
	END IF;
	--	Round to penny
	v_AvailableAmt := ROUND(NVL(v_AvailableAmt,0), 2);
	RETURN	v_AvailableAmt;
END paymentAvailable;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION productAttribute
(
    p_M_AttributeSetInstance_ID     IN NUMBER
)
RETURN NVARCHAR2
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Attribute_Name.sql,v 1.4 2005/10/11 02:28:41 jjanke Exp $
 ***
 * Title:	Return Instance Attribute Info
 * Description:
 *		
 * Test:
    SELECT M_Attribute_Name (M_AttributeSetInstance_ID) 
    FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0
    --
    SELECT p.Name
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    SELECT p.Name || M_Attribute_Name (il.M_AttributeSetInstance_ID) 
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    
 ************************************************************************/
AS
    v_Name          NVARCHAR2(2000) := NULL;
    v_NameAdd       NVARCHAR2(2000) := '';
    --
    v_Lot           M_AttributeSetInstance.Lot%TYPE;
    v_LotStart      M_AttributeSet.LotCharSOverwrite%TYPE;
    v_LotEnd        M_AttributeSet.LotCharEOverwrite%TYPE;
    v_SerNo         M_AttributeSetInstance.SerNo%TYPE;
    v_SerNoStart    M_AttributeSet.SerNoCharSOverwrite%TYPE;
    v_SerNoEnd      M_AttributeSet.SerNoCharEOverwrite%TYPE;
    v_GuaranteeDate M_AttributeSetInstance.GuaranteeDate%TYPE;
    --
    CURSOR CUR_Attributes IS
        SELECT ai.Value, a.Name
        FROM M_AttributeInstance ai
          INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
        WHERE ai.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;

BEGIN
/*    --  Get Product Name
    SELECT Name 
      INTO v_Name
    FROM M_Product WHERE M_Product_ID=p_M_Product_ID;
*/
    --  Get Product Attribute Set Instance
    IF (p_M_AttributeSetInstance_ID > 0) THEN
        SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate,
            COALESCE(a.SerNoCharSOverwrite, N'#'), COALESCE(a.SerNoCharEOverwrite, N''),
            COALESCE(a.LotCharSOverwrite, N'«'), COALESCE(a.LotCharEOverwrite, N'»')
          INTO v_Lot, v_SerNo, v_GuaranteeDate,
            v_SerNoStart, v_SerNoEnd, v_LotStart, v_LotEnd
        FROM M_AttributeSetInstance asi
          INNER JOIN M_AttributeSet a ON (asi.M_AttributeSet_ID=a.M_AttributeSet_ID)
        WHERE asi.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;
        --
        IF (v_SerNo IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_SerNoStart || v_SerNo || v_SerNoEnd || ' ';
        END IF;
        IF (v_Lot IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_LotStart || v_Lot || v_LotEnd || ' ';
        END IF;
        IF (v_GuaranteeDate IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_GuaranteeDate || ' ';
        END IF;
        --
        FOR a IN CUR_Attributes LOOP
            v_NameAdd := v_NameAdd || a.Name || ':' || a.Value || ' ';
        END LOOP;
        --
        IF (LENGTH(v_NameAdd) > 0) THEN
            v_Name := v_Name || ' (' || TRIM(v_NameAdd) || ')';
        END IF;
    END IF;
    
    RETURN v_Name;
END productAttribute;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bpartnerRemitLocation
(
	p_C_BPartner_ID	  C_BPartner.C_BPartner_ID%TYPE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_BPartner_RemitLocation.SQL,v 1.3 2005/02/04 16:33:47 jjanke Exp $
 ***
 * Title:   Return the first RemitTo C_Location_ID of a Business Partner
 * Description:
 *      
 ************************************************************************/
AS
	v_C_Location_ID			NUMBER := NULL;
	CURSOR	CUR_BPLoc	IS
		SELECT	IsRemitTo, C_Location_ID
		FROM	C_BPartner_Location
		WHERE	C_BPartner_ID=p_C_BPartner_ID
		ORDER BY IsRemitTo DESC;
BEGIN
	FOR l IN CUR_BPLoc LOOP
		IF (v_C_Location_ID IS NULL) THEN
			v_C_Location_ID := l.C_Location_ID;
		END IF;
	END LOOP;
	RETURN v_C_Location_ID;
END bpartnerRemitLocation;
/

SHOW ERRORS;

ALTER PROCEDURE M_PriceList_Create COMPILE;
SHOW ERRORS;

ALTER PROCEDURE M_Production_Run COMPILE;
SHOW ERRORS;

ALTER PROCEDURE T_InventoryValue_Create COMPILE;
SHOW ERRORS;

-- 
-- VIEWS
--
ALTER VIEW C_Invoice_LineTax_v COMPILE;
SHOW ERRORS;

ALTER VIEW C_Invoice_LineTax_vt COMPILE;
SHOW ERRORS;

ALTER VIEW C_Order_LineTax_v COMPILE;
SHOW ERRORS;

ALTER VIEW C_Order_LineTax_vt COMPILE;
SHOW ERRORS;

ALTER VIEW C_PaySelection_Check_v COMPILE;
SHOW ERRORS;

ALTER VIEW C_PaySelection_Check_vt COMPILE;
SHOW ERRORS;

ALTER VIEW C_RfQResponseLine_v COMPILE;
SHOW ERRORS;

ALTER VIEW C_RfQResponseLine_vt COMPILE;
SHOW ERRORS;

ALTER VIEW M_InOut_Line_v COMPILE;
SHOW ERRORS;

ALTER VIEW M_InOut_Line_vt COMPILE;
SHOW ERRORS;

ALTER VIEW RV_BPartnerOpen COMPILE;
SHOW ERRORS;

ALTER VIEW RV_Cash_Detail COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_InvoiceLine COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_CustomerProdQtr COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_CustomerVendQtr COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_Day COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_Month COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_ProdMonth COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_ProductMonth COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_ProductQtr COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_ProdWeek COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_VendorMonth COMPILE;
SHOW ERRORS;

ALTER VIEW RV_C_Invoice_Week COMPILE;
SHOW ERRORS;

ALTER VIEW RV_InOutDetails COMPILE;
SHOW ERRORS;

ALTER VIEW RV_OpenItem COMPILE;
SHOW ERRORS;

ALTER VIEW RV_OrderDetail COMPILE;
SHOW ERRORS;

ALTER VIEW RV_Payment COMPILE;
SHOW ERRORS;

ALTER VIEW RV_ProjectCycle COMPILE;
SHOW ERRORS;

ALTER VIEW RV_Storage COMPILE;
SHOW ERRORS;

ALTER VIEW RV_WarehousePrice COMPILE;
SHOW ERRORS;

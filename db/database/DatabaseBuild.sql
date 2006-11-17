--------------------------------------------------------------------------------
-- FILE                : DatabaseBuild.sql
-- CREATED BY/DATE     : Rapid SQL on 8/10/2006 20:58:46.208
-- COMMENTS            : Built from project Database
--------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION bomPriceLimit
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION bomPriceList
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION bomPriceStd
( 
	Product_ID 				IN NUMBER,
	PriceList_Version_ID	IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION bomQtyAvailable
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	Return quantity available for BOM
 */
AS
BEGIN
	RETURN bomQtyOnHand(Product_ID, Warehouse_ID, Locator_ID)
		- bomQtyReserved(Product_ID, Warehouse_ID, Locator_ID);
END bomQtyAvailable;
/

CREATE OR REPLACE FUNCTION bomQtyOnHand
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
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

CREATE OR REPLACE FUNCTION bomQtyOrdered
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
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

CREATE OR REPLACE FUNCTION bomQtyReserved
(
	p_Product_ID 		IN NUMBER,
    p_Warehouse_ID		IN NUMBER,
	p_Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
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
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION invoiceOpen
(
	p_C_Invoice_ID	            IN	NUMBER,
    p_C_InvoicePaySchedule_ID   IN  NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION invoicePaid
(
	p_C_Invoice_ID		IN	NUMBER,
	p_C_Currency_ID	    IN	NUMBER,
	p_MultiplierAP		IN	NUMBER	-- DEFAULT 1
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMBER,
	p_C_Currency_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION paymentAvailable
(
	p_C_Payment_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION paymentTermDueDays
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE PROCEDURE AD_Sequence_Doc 
(
	p_SequenceName	IN	VARCHAR2,
	p_AD_Client_ID	IN	NUMBER,
	o_DocumentNo	OUT VARCHAR2
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Get the next DocumentNo of TableName
 * Description:
 *		store in parameter o_DocumentNo
 *		if ID < 1000000, use System Doc Sequence
 ************************************************************************/
	v_NextNo			NUMBER;
	v_NextNoSys			NUMBER;
	v_Prefix			VARCHAR2(30);
	v_Suffix			VARCHAR2(30);
BEGIN
	SELECT	CurrentNext, CurrentNextSys, Prefix, Suffix
	  INTO	v_NextNo, v_NextNoSys, v_Prefix, v_Suffix
	FROM	AD_Sequence
	WHERE	Name = p_SequenceName
	  AND	IsActive = 'Y'
	  AND	IsTableID = 'N'
	  AND	IsAutoSequence = 'Y'
	  AND	AD_Client_ID = p_AD_Client_ID
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	IF (v_NextNoSys <> -1 AND p_AD_Client_ID < 1000000) THEN	--	System No
		UPDATE	AD_Sequence
		  SET	CurrentNextSys = CurrentNextSys + IncrementNo,
				Updated = SysDate
		WHERE	Name = p_SequenceName;
		o_DocumentNo := NVL(v_Prefix, '') || v_NextNoSys || NVL(v_Suffix, '');
	ELSE								--	Standard No
		UPDATE	AD_Sequence
		  SET	CurrentNext = CurrentNext + IncrementNo,
				Updated = SysDate
		WHERE	Name = p_SequenceName;
		o_DocumentNo := NVL(v_Prefix, '') || v_NextNo || NVL(v_Suffix, '');
	END IF;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR (-20100, 'Document Sequence not found - ' || p_SequenceName);

END AD_Sequence_Doc;
/

CREATE OR REPLACE PROCEDURE AD_Sequence_DocType
(
	p_DocType_ID		IN	NUMBER,
	p_ID				IN	NUMBER,
	p_DocumentNo		OUT	VARCHAR2
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Get the next DocumentNo of Document Type
 * Description:
 *		store in parameter p_DocumentNo
 *		If ID < 1000000, use System Doc Sequence
 *		If no Document Sequence is defined, return null !
 *			Use AD_Sequence_Doc('DocumentNo_myTable',.. to get it directly
 ************************************************************************/

	v_NextNo			NUMBER;
	v_NextNoSys			NUMBER;
	v_Sequence_ID		NUMBER	:= NULL;
	v_Prefix			VARCHAR2(30);
	v_Suffix			VARCHAR2(30);
BEGIN
	--	Is a document Sequence defined and valid?
	BEGIN
		SELECT	DocNoSequence_ID
		  INTO	v_Sequence_ID
		FROM	C_DocType
		WHERE	C_DocType_ID=p_DocType_ID	--	parameter
		  AND	IsDocNoControlled='Y'
		  AND	IsActive='Y';
	EXCEPTION
		WHEN OTHERS THEN
			NULL;
	END;
    
	IF (v_Sequence_ID IS NULL) THEN		--	No Sequence Number
		p_DocumentNo := '';				--	Return NULL
		DBMS_OUTPUT.PUT_LINE('[AD_Sequence_DocType: not found - C_DocType_ID=' || p_DocType_ID || ']');
		RETURN;
	END IF;

	--	Get the numbers
	SELECT	s.AD_Sequence_ID, s.CurrentNext, s.CurrentNextSys, s.Prefix, s.Suffix
	  INTO	v_Sequence_ID, v_NextNo, v_NextNoSys, v_Prefix, v_Suffix
	FROM	C_DocType d, AD_Sequence s
	WHERE	d.C_DocType_ID=p_DocType_ID	--	parameter
	  AND	d.DocNoSequence_ID=s.AD_Sequence_ID
	  AND	s.IsActive = 'Y'
	  AND	s.IsTableID = 'N'
	  AND	s.IsAutoSequence = 'Y'
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	IF (v_NextNoSys <> -1 AND p_ID < 1000000) THEN	--	System No
		UPDATE	AD_Sequence
		  SET	CurrentNextSys = CurrentNextSys + IncrementNo
		WHERE	AD_Sequence_ID = v_Sequence_ID;
		p_DocumentNo := NVL(v_Prefix, '') || v_NextNoSys || NVL(v_Suffix, '');
	ELSE						--	Standard No
		UPDATE AD_Sequence
		  SET CurrentNext = CurrentNext + IncrementNo
		WHERE AD_Sequence_ID = v_Sequence_ID;
		p_DocumentNo := NVL(v_Prefix, '') || v_NextNo || NVL(v_Suffix, '');
	END IF;
--	DBMS_OUTPUT.PUT_LINE(p_DocumentNo);

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR (-20100, 'AD_Sequence_DocType: not found - DocType_ID='
			|| p_DocType_ID || ', Sequence_ID=' || v_Sequence_ID);

END AD_Sequence_DocType;
/

CREATE OR REPLACE PROCEDURE AD_Sequence_Next 
(
	p_TableName		IN	VARCHAR2,
	p_ID			IN	NUMBER,
	p_NextNo		OUT	NUMBER
 )
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Get the next sequence number of TableName
 * Description:
 *		store in parameter p_NextNo
 *		if ID < 1000000, use System Doc Sequence
 ************************************************************************/

	v_NextNoSys		NUMBER;
	v_ResultStr		VARCHAR(255);
BEGIN
	v_ResultStr := 'Read';
	SELECT CurrentNext, CurrentNextSys
	  INTO p_NextNo, v_NextNoSys
	FROM AD_Sequence
	WHERE Name = p_TableName
	  AND IsActive = 'Y'
	  AND IsTableID = 'Y'
	  AND IsAutoSequence = 'Y'
	FOR UPDATE OF CurrentNext, CurrentNextSys;

	v_ResultStr := 'Write';
	IF (v_NextNoSys <> -1 AND p_ID < 1000000) THEN	--	System No
		UPDATE 	AD_Sequence
		  SET 	CurrentNextSys = CurrentNextSys + IncrementNo,
				Updated = SysDate
		WHERE 	Name = p_TableName;
		p_NextNo := v_NextNoSys;
	ELSE						--	Standard No
		UPDATE 	AD_Sequence
		  SET	CurrentNext = CurrentNext + IncrementNo,
				Updated = SysDate
		WHERE Name = p_TableName;
	END IF;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
	RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found ');
-- 		|| v_ResultStr || ': ' || p_TableName);

END AD_Sequence_Next;
/

CREATE OR REPLACE FUNCTION currencyRound
(
	p_Amount		IN	NUMBER,
	p_CurTo_ID	IN	NUMBER,
	p_Costing		IN	VARCHAR2		--	Default 'N'
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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
CREATE OR REPLACE PROCEDURE C_Order_DrillDown
(
	PInstance_ID		IN NUMBER
)
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	List Orders with their Shipments and Invoices
 *	Spool to T_Spool
 */
AS
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000);
	Record_ID						NUMBER;
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter
	C_Order_ID						NUMBER;
	--
	CURSOR Cur_Order IS
		SELECT	o.C_Order_ID, d.Name, o.DocumentNo, o.DocStatus, o.DocAction, o.Processed
		FROM	C_Order o, C_DocType d
		WHERE	o.C_Order_ID=C_Order_ID
		  AND	o.C_DocType_ID=d.C_DocType_ID
		ORDER BY o.DocumentNo DESC;

BEGIN
	--	No locking or Updating

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		Record_ID := p.Record_ID;
		IF (p.ParameterName = 'C_Order_ID') THEN
 			C_Order_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  C_Order_ID=' || C_Order_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	IF (C_Order_ID IS NULL) THEN
		C_Order_ID := Record_ID;
	END IF;

	--	Should be nothing there
	DELETE 	T_Spool
	WHERE	AD_PInstance_ID=PInstance_ID;

	--	Order Info
	FOR o IN Cur_Order LOOP

		INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
			o.Name || ' ' || o.DocumentNo || ':  @DocStatus@=' || o.DocStatus
			|| ', @DocAction@=' || o.DocAction || ', @Processed@=' || o.Processed);

		--	Order Lines
		DECLARE
			CURSOR	Cur_OrderLine	IS
				SELECT	*
				FROM	C_OrderLine
				WHERE	C_Order_ID=o.C_Order_ID
				ORDER BY Line;
		BEGIN
			FOR ol IN Cur_OrderLine LOOP
				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'   @QtyOrdered@=' || ol.QtyOrdered || ', @QtyReserved@=' || ol.QtyReserved
					|| ', @QtyDelivered@=' || ol.QtyDelivered || ', @QtyInvoiced@=' || ol.QtyInvoiced
					|| ' - Wh=' || ol.M_Warehouse_ID
					|| ', Prd=' || ol.M_Product_ID);
			END LOOP;
		END;
	
		-- Shipment
		DECLARE
			CURSOR 	Cur_InOut		IS
				SELECT	s.M_InOut_ID, d.Name, s.DocumentNo, s.DocStatus, s.Processed, s.M_Warehouse_ID
				FROM	M_InOut s, C_DocType d
				WHERE	s.C_Order_ID = o.C_Order_ID
				  AND	s.C_DocType_ID=d.C_DocType_ID;
		BEGIN
			FOR s IN Cur_InOut LOOP
				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'> ' || s.Name || ' ' || s.DocumentNo || ':  @DocStatus@=' || s.DocStatus 
					|| ', @Processed@=' || s.Processed || ', Wh=' || s.M_Warehouse_ID);

				--	Shipment Lines
				DECLARE
					CURSOR	Cur_InOutLine	IS
						SELECT	*
						FROM	M_InOutLine
						WHERE	M_InOut_ID=s.M_InOut_ID
						ORDER BY Line;
				BEGIN
					FOR sl IN Cur_InOutLine LOOP
						INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
							'   @QtyDelivered@=' || sl.MovementQty || ', Prd=' || sl.M_Product_ID);
					END LOOP;
				END;	-- 	Shipment Lines
 			END LOOP;	--	Shipments
		END; --	Shipment

		-- Invoice
		DECLARE
			CURSOR 	Cur_Invoice		IS
				SELECT	i.C_Invoice_ID, d.Name, i.DocumentNo, i.DocStatus, i.Processed
				FROM	C_Invoice i, C_DocType d
				WHERE	i.C_DocType_ID=d.C_DocType_ID
				  AND EXISTS (SELECT * FROM C_InvoiceLine l, C_OrderLine ol
 				  	WHERE 	i.C_Invoice_ID = l.C_Invoice_ID
					  AND	l.C_OrderLine_ID = ol.C_OrderLine_ID
					  AND	ol.C_Order_ID=o.C_Order_ID);
		BEGIN
			FOR i IN Cur_Invoice LOOP

				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'> ' || i.Name || ' ' || i.DocumentNo || ':  @DocStatus@=' || i.DocStatus
					|| ', @Processed@=' || i.Processed);

				--	Invoice Lines
				DECLARE
					CURSOR	Cur_InvoiceLine	IS
						SELECT	*
						FROM	C_InvoiceLine
						WHERE	C_Invoice_ID=i.C_Invoice_ID
						ORDER BY Line;
				BEGIN
					FOR il IN Cur_InvoiceLine LOOP
						INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
							'   @QtyInvoiced@=' || il.QtyInvoiced || ', Prd=' || il.M_Product_ID);
					END LOOP;
				END;	-- 	Invoice Lines
 			END LOOP;	--	Invoices
		END; --	Invoice
	
	END LOOP;	-- Order


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = Message
    WHERE   AD_PInstance_ID=PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		ResultStr := ResultStr || ': ' || SQLERRM || ' - ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = ResultStr
        WHERE   AD_PInstance_ID=PInstance_ID;
        COMMIT;
        RETURN;

END C_Order_DrillDown;
/

-- File c_yearperiods.sql was not found.

CREATE OR REPLACE FUNCTION DBA_ConstraintCmd
(
	p_ConstraintName	IN	VARCHAR2
)
RETURN VARCHAR2
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 * $Source: /cvs/adempiere/db/database/DatabaseBuild.sql,v $
 ***
 * Title:	 Create DML command for given constraint
 * Description:
 *		SELECT DBA_ConstraintCmd(Constraint_Name) FROM User_Constraints WHERE CONSTRAINT_TYPE='R'
 ************************************************************************/
AS
	v_Result			VARCHAR2(2000);
	v_TableName			VARCHAR2(256);
	v_ColumnName		VARCHAR2(256);
	v_ConstraintName	VARCHAR2(256);
	v_DeleteRule		VARCHAR2(256);
BEGIN
	--	Get First Part
	SELECT c.Table_Name, cc.Column_name, c.R_Constraint_Name, c.Delete_Rule
	  INTO	v_TableName, v_ColumnName, v_ConstraintName, v_DeleteRule
	FROM USER_Constraints c, USER_Cons_Columns cc
	WHERE c.Constraint_Name=cc.Constraint_Name
	  AND cc.Constraint_Name=p_ConstraintName;
	--	Create First Part
	v_Result := 'ALTER TABLE ' || v_TableName || ' ADD CONSTRAINT ' || p_ConstraintName
		|| ' FOREIGN KEY (' || v_ColumnName || ') ';

	--	Not a valid FK Reference
	IF (v_ConstraintName IS NULL) THEN
		RETURN NULL;
   	END IF;

	--	Get Second Part
	SELECT c.Table_Name, cc.Column_name
	  INTO	v_TableName, v_ColumnName
	FROM USER_Constraints c, USER_Cons_Columns cc
	WHERE c.Constraint_Name=cc.Constraint_Name
	  AND cc.Constraint_Name=v_ConstraintName;
	--	Create Second Part
	v_Result := v_Result || 'REFERENCES ' || v_TableName || '(' || v_ColumnName || ')';

	IF (v_DeleteRule = 'CASCADE') THEN
		v_Result := v_Result || ' ON DELETE CASCADE';
   	END IF;
--	DBMS_OUTPUT.PUT_LINE(v_Result);
	RETURN v_Result;

END DBA_ConstraintCmd;
/


CREATE OR REPLACE PROCEDURE M_PriceList_Create
( 
	PInstance_ID			IN NUMBER
)
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Create Pricelist
 * Description:
 *		Create PriceList by copying purchase prices (M_Product_PO) 
 *		and applying product category discounts (M_CategoryDiscount)
 ************************************************************************/
	--	Logistice
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000) := '';
	NoRate							EXCEPTION;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_PriceList_Version_ID			NUMBER;
	p_DeleteOld						CHAR(1) := 'N';
	--
	v_Currency_ID					NUMBER;
	v_Client_ID						NUMBER;
	v_Org_ID						NUMBER;
	v_UpdatedBy						NUMBER;
	v_StdPrecision					NUMBER;
	v_DiscountSchema_ID				NUMBER;
	v_PriceList_Version_Base_ID		NUMBER;
	--
	v_NextNo						NUMBER := 0;

	--	Get PL Parameter
	CURSOR Cur_DiscountLine (DiscountSchema_ID NUMBER) IS
		SELECT	* 
		FROM	M_DiscountSchemaLine
		WHERE	M_DiscountSchema_ID=DiscountSchema_ID
		  AND	IsActive='Y'
		ORDER BY SeqNo;

BEGIN
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing');
	ResultStr := 'PInstanceNotFound';
	UPDATE AD_PInstance
	SET Created = SysDate,
		IsProcessing = 'Y'
	WHERE AD_PInstance_ID=PInstance_ID;
	COMMIT;

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		p_PriceList_Version_ID := p.Record_ID;
		IF (p.ParameterName = 'DeleteOld') THEN
			p_DeleteOld := p.P_String;
			DBMS_OUTPUT.PUT_LINE('  DeleteOld=' || p_DeleteOld);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
		END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  PriceList_Version_ID=' || p_PriceList_Version_ID);

	--	Checking Prerequisites
	--	--	PO Prices must exists
	ResultStr := 'CorrectingProductPO';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	UPDATE	M_Product_PO
	  SET	PriceList = 0
	WHERE	PriceList IS NULL;
	UPDATE	M_Product_PO	
	  SET	PriceLastPO = 0
	WHERE	PriceLastPO IS NULL;
	UPDATE	M_Product_PO
	  SET	PricePO = PriceLastPO
	WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0;
	UPDATE	M_Product_PO
	  SET	PricePO = 0
	WHERE	PricePO IS NULL;
	-- Set default current vendor
	UPDATE	M_Product_PO p
	  SET	IsCurrentVendor = 'Y'
	WHERE	IsCurrentVendor = 'N' 
	  AND NOT EXISTS 
		(SELECT pp.M_Product_ID FROM M_Product_PO pp
		WHERE pp.M_Product_ID=p.M_Product_ID
		GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1);
	COMMIT;

	/**
	 *	Make sure that we have only one active product
	 */
	ResultStr := 'CorrectingDuplicates';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	DECLARE
		--	All duplicate products
		CURSOR	Cur_Duplicates	IS
			SELECT	DISTINCT M_Product_ID
			FROM	M_Product_PO po
			WHERE	IsCurrentVendor='Y' AND IsActive='Y'
			  AND EXISTS (	SELECT M_Product_ID FROM M_Product_PO x 
							WHERE x.M_Product_ID=po.M_Product_ID 
							GROUP BY M_Product_ID HAVING COUNT(*) > 1 )
			ORDER BY 1;
		--	All vendors of Product - expensive first
		CURSOR	Cur_Vendors	(Product_ID NUMBER) IS
			SELECT	M_Product_ID, C_BPartner_ID
			FROM	M_Product_PO
			WHERE	IsCurrentVendor='Y' AND IsActive='Y'
			  AND	M_Product_ID=Product_ID
			ORDER BY PriceList DESC;
		--
		Product_ID				NUMBER;
		BPartner_ID				NUMBER;
	BEGIN
		FOR dupl IN Cur_Duplicates LOOP
			OPEN Cur_Vendors (dupl.M_Product_ID);
			FETCH Cur_Vendors INTO Product_ID, BPartner_ID;		--	Leave First
			LOOP
				FETCH Cur_Vendors INTO Product_ID, BPartner_ID;	--	Get Record ID
				EXIT WHEN Cur_Vendors%NOTFOUND;
				--
				DBMS_OUTPUT.PUT_LINE('  Record: ' || Product_ID || ' / ' || BPartner_ID);
				UPDATE	M_Product_PO
				  SET	IsCurrentVendor='N'
				WHERE	M_Product_ID=Product_ID AND C_BPartner_ID=BPartner_ID;
			END LOOP;
			CLOSE Cur_Vendors;
		END LOOP;
		COMMIT;
	END;
	
	/**	Delete Old Data	*/
	ResultStr := 'DeletingOld';
	IF (p_DeleteOld = 'Y') THEN
		DELETE	M_ProductPrice
		WHERE	M_PriceList_Version_ID = p_PriceList_Version_ID;
		Message := '@Deleted@=' || SQL%ROWCOUNT || ' - ';
		DBMS_OUTPUT.PUT_LINE(Message);
	END IF;

	--	Get PriceList Info
	ResultStr := 'GetPLInfo';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	SELECT	p.C_Currency_ID, c.StdPrecision,
		v.AD_Client_ID, v.AD_Org_ID, v.UpdatedBy, 
		v.M_DiscountSchema_ID, M_PriceList_Version_Base_ID
	  INTO	v_Currency_ID, v_StdPrecision,
		v_Client_ID, v_Org_ID, v_UpdatedBy, 
		v_DiscountSchema_ID, v_PriceList_Version_Base_ID
	FROM	M_PriceList p, M_PriceList_Version v, C_Currency c
	WHERE	p.M_PriceList_ID=v.M_PriceList_ID 
	  AND	p.C_Currency_ID=c.C_Currency_ID
	  AND	v.M_PriceList_Version_ID=p_PriceList_Version_ID;

	/**
	 *	For All Discount Lines in Sequence
	 */
	FOR dl IN Cur_DiscountLine (v_DiscountSchema_ID) LOOP
		ResultStr := 'Parameter Seq=' || dl.SeqNo;
	--	DBMS_OUTPUT.PUT_LINE(ResultStr);

		--	Clear Temporary Table
		DELETE FROM T_Selection;

		--	-----------------------------------
		--	Create Selection in temporary table
		--	-----------------------------------
		IF (v_PriceList_Version_Base_ID IS NULL) THEN
		--	Create Selection from M_Product_PO
			INSERT INTO T_Selection (T_Selection_ID)
			SELECT	DISTINCT po.M_Product_ID 
			FROM	M_Product p, M_Product_PO po
			WHERE	p.M_Product_ID=po.M_Product_ID
			  AND	(p.AD_Client_ID=v_Client_ID OR p.AD_Client_ID=0)
			  AND	p.IsActive='Y' AND po.IsActive='Y' AND po.IsCurrentVendor='Y'
			--	Optional Restrictions
			  AND (dl.M_Product_Category_ID IS NULL OR p.M_Product_Category_ID=dl.M_Product_Category_ID)
			  AND (dl.C_BPartner_ID IS NULL OR po.C_BPartner_ID=dl.C_BPartner_ID)
			  AND (dl.M_Product_ID IS NULL OR p.M_Product_ID=dl.M_Product_ID);
		ELSE
		--	Create Selection from existing PriceList
			INSERT INTO T_Selection (T_Selection_ID)
			SELECT	DISTINCT p.M_Product_ID 
			FROM	M_Product p, M_ProductPrice pp
			WHERE	p.M_Product_ID=pp.M_Product_ID
			  AND	pp.M_PriceList_Version_ID=v_PriceList_Version_Base_ID
			  AND	p.IsActive='Y' AND pp.IsActive='Y'
			--	Optional Restrictions
			  AND	(dl.M_Product_Category_ID IS NULL OR p.M_Product_Category_ID=dl.M_Product_Category_ID)
			  AND	(dl.C_BPartner_ID IS NULL OR EXISTS 
					(SELECT * FROM M_Product_PO po WHERE po.M_Product_ID=p.M_Product_ID AND po.C_BPartner_ID=dl.C_BPartner_ID))
			  AND	(dl.M_Product_ID IS NULL OR p.M_Product_ID=dl.M_Product_ID);
		END IF;
		Message := Message || '@Selected@=' || SQL%ROWCOUNT;
	--	DBMS_OUTPUT.PUT_LINE(Message);

		--	Delete Prices in Selection, so that we can insert
		IF (v_PriceList_Version_Base_ID IS NULL
				OR v_PriceList_Version_Base_ID <> p_PriceList_Version_ID) THEN
			ResultStr := ResultStr || ', Delete';
			DELETE	M_ProductPrice pp
			WHERE	pp.M_PriceList_Version_ID = p_PriceList_Version_ID
			  AND EXISTS (SELECT * FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID);
			Message := ', @Deleted@=' || SQL%ROWCOUNT;
		END IF;

		--	--------------------
		--	Copy (Insert) Prices
		--	--------------------
		IF (v_PriceList_Version_Base_ID = p_PriceList_Version_ID) THEN
		--	We have Prices already
			NULL;
		ELSIF (v_PriceList_Version_Base_ID IS NULL) THEN
		--	Copy and Convert from Product_PO
			ResultStr := ResultStr || ',Copy_PO';
			INSERT INTO M_ProductPrice
				(M_PriceList_Version_ID, M_Product_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				PriceList, PriceStd, PriceLimit)
			SELECT 
				p_PriceList_Version_ID, po.M_Product_ID, 
				v_Client_ID, v_Org_ID, 'Y', SysDate, v_UpdatedBy, SysDate, v_UpdatedBy,
				--	Price List
				COALESCE(currencyConvert(po.PriceList, 
					po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Std
				COALESCE(currencyConvert(po.PriceList, 
					po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Limit
				COALESCE(currencyConvert(po.PricePO,
						po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0)
			FROM	M_Product_PO po
			WHERE EXISTS (SELECT * FROM T_Selection s WHERE po.M_Product_ID=s.T_Selection_ID)
			  AND	po.IsCurrentVendor='Y' AND po.IsActive='Y';
		ELSE
		--	Copy and Convert from other PriceList_Version
			ResultStr := ResultStr || ',Copy_PL';
			INSERT INTO M_ProductPrice
				(M_PriceList_Version_ID, M_Product_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				PriceList, PriceStd, PriceLimit)
			SELECT 
				p_PriceList_Version_ID, pp.M_Product_ID, 
				v_Client_ID, v_Org_ID, 'Y', SysDate, v_UpdatedBy, SysDate, v_UpdatedBy,
				--	Price List
				COALESCE(currencyConvert(pp.PriceList, 
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Std
				COALESCE(currencyConvert(pp.PriceStd, 
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Limit
				COALESCE(currencyConvert(pp.PriceLimit,
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0)
			FROM M_ProductPrice pp
                INNER JOIN M_PriceList_Version plv ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID)
                INNER JOIN M_PriceList pl ON (plv.M_PriceList_ID=pl.M_PriceList_ID)
			WHERE	pp.M_PriceList_Version_ID=v_PriceList_Version_Base_ID
			  AND EXISTS (SELECT * FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID)
			  AND	pp.IsActive='Y';
		END IF;
		Message := Message || ', @Inserted@=' || SQL%ROWCOUNT;

		--	-----------
		--	Calculation
		--	-----------
		ResultStr := ResultStr || ',Calc';
		UPDATE	M_ProductPrice p
		  SET	PriceList = (DECODE(dl.List_Base, 'S', PriceStd, 'X', PriceLimit, PriceList) 
					+ dl.List_AddAmt) * (1 - dl.List_Discount/100),
				PriceStd = (DECODE(dl.Std_Base, 'L', PriceList, 'X', PriceLimit, PriceStd) 
					+ dl.Std_AddAmt) * (1 - dl.Std_Discount/100),
				PriceLimit = (DECODE(dl.Limit_Base, 'L', PriceList, 'S', PriceStd, PriceLimit) 
					+ dl.Limit_AddAmt) * (1 - dl.Limit_Discount/100)
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID);

		--	--------
		-- 	Rounding	(AD_Reference_ID=155)
		--	--------
		ResultStr := ResultStr || ',Round';
		UPDATE	M_ProductPrice p
		  SET	PriceList = DECODE(dl.List_Rounding, 
					'N', PriceList,
					'0', ROUND(PriceList, 0),	--	Even .00					
					'D', ROUND(PriceList, 1),	--	Dime .10
					'T', ROUND(PriceList, -1),	--	Ten 10.00
					'5', ROUND(PriceList*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceList*4,0)/4,	--	Quarter .25	
					ROUND(PriceList, v_StdPrecision)),--	Currency
				PriceStd = DECODE(dl.Std_Rounding, 
					'N', PriceStd, 
					'0', ROUND(PriceStd, 0),	--	Even .00					
					'D', ROUND(PriceStd, 1),	--	Dime .10
					'T', ROUND(PriceStd, -1),	--	Ten 10.00
					'5', ROUND(PriceStd*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceStd*4,0)/4,		--	Quarter .25	
					ROUND(PriceStd, v_StdPrecision)),	--	Currency
				PriceLimit = DECODE(dl.Limit_Rounding, 
					'N', PriceLimit,
					'0', ROUND(PriceLimit, 0),	--	Even .00					
					'D', ROUND(PriceLimit, 1),	--	Dime .10
					'T', ROUND(PriceLimit, -1),	--	Ten 10.00
					'5', ROUND(PriceLimit*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceLimit*4,0)/4,	--	Quarter .25	
					ROUND(PriceLimit, v_StdPrecision))--	Currency
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID);
		Message := Message || ', @Updated@=' || SQL%ROWCOUNT;

		--	Fixed Price overwrite
		ResultStr := ResultStr || ',Fix';
		UPDATE	M_ProductPrice p
		  SET	PriceList = DECODE(dl.List_Base, 'F', dl.List_Fixed, PriceList), 
				PriceStd = DECODE(dl.Std_Base, 'F', dl.Std_Fixed, PriceStd),
				PriceLimit = DECODE(dl.Limit_Base, 'F', dl.Limit_Fixed, PriceLimit) 
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID);

		--	Log Info
		INSERT INTO AD_PInstance_Log	(AD_PInstance_ID, Log_ID, P_ID, P_NUMBER, P_MSG)
		VALUES							(PInstance_ID, v_NextNo, null, dl.SeqNo, Message);
		--
		v_NextNo := v_NextNo + 1;
		Message := '';
	END LOOP;	--	For all DiscountLines

	--	Delete Temporary Selection
	DELETE FROM T_Selection;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE(Message);
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished');
	UPDATE	AD_PInstance
	SET Updated = SysDate,
		IsProcessing = 'N',
		Result = 1,					-- success
		ErrorMsg = Message
	WHERE	AD_PInstance_ID=PInstance_ID;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN OTHERS THEN
		ResultStr := ResultStr || ':' || SQLERRM || ' ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = 0,				-- failure
			ErrorMsg = ResultStr
		WHERE	AD_PInstance_ID=PInstance_ID;
		COMMIT;
		RETURN;

END M_PriceList_Create;
/

CREATE OR REPLACE PROCEDURE M_Product_BOM_Check
(
	PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Check BOM Structure (free of cycles)
 * Description:
 *		Tree cannot contain BOMs which are already referenced
 ************************************************************************/
AS
	--	Logistice
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000);
	Record_ID						NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Variables
	Verified				CHAR(1) := 'Y';
	IsBOM					CHAR(1);
	CountNo					NUMBER;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || PInstance_ID);
    ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=PInstance_ID;
    COMMIT;

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		Record_ID := p.Record_ID;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	--	Record ID is M_Product_ID of product to be tested
	SELECT 	IsBOM
	  INTO	IsBOM
	FROM	M_Product
	WHERE	M_Product_ID=Record_ID;

	--	No BOM - should not happen, but no problem
	IF (IsBOM = 'N') THEN
		GOTO FINISH_PROCESS;
	--	Did not find product
	ELSIF (IsBOM <> 'Y') THEN
		RETURN;
	END IF;

	--	Checking BOM Structure
	ResultStr := 'InsertingRoot';
	--	Table to put all BOMs - duplicate will cause exception
	DELETE FROM T_Selection2 WHERE Query_ID = 0;
	INSERT INTO T_Selection2 (Query_ID, T_Selection_ID) VALUES (0, Record_ID);
	--	Table of root modes
	DELETE FROM T_Selection;
	INSERT INTO T_Selection (T_Selection_ID) VALUES (Record_ID);

	LOOP
		--	How many do we have?
		SELECT 	COUNT(*) 
		  INTO	CountNo
		FROM	T_Selection;
		--	Nothing to do
		EXIT WHEN (CountNo = 0);

		--	Insert BOM Nodes into "All" table
		INSERT INTO T_Selection2 (Query_ID, T_Selection_ID)
		SELECT 0, p.M_Product_ID
		FROM M_Product p
		WHERE IsBOM='Y' 
		  AND EXISTS (SELECT * FROM M_Product_BOM b WHERE p.M_Product_ID=b.M_ProductBOM_ID
		  	AND b.M_Product_ID IN (SELECT T_Selection_ID FROM T_Selection));

		--	Insert BOM Nodes into temporary table
		DELETE FROM T_Selection2 WHERE Query_ID = 1;
		INSERT INTO T_Selection2 (Query_ID, T_Selection_ID)
		SELECT 1, p.M_Product_ID
		FROM M_Product p
		WHERE IsBOM='Y' 
		  AND EXISTS (SELECT * FROM M_Product_BOM b WHERE p.M_Product_ID=b.M_ProductBOM_ID
		  	AND b.M_Product_ID IN (SELECT T_Selection_ID FROM T_Selection));

		--	Copy into root table
		DELETE FROM T_Selection;
		INSERT INTO T_Selection (T_Selection_ID) 
		SELECT 	T_Selection_ID
		FROM	T_Selection2
		WHERE Query_ID = 1;

	END LOOP;

<<FINISH_PROCESS>>
	--	OK
	Message := 'OK';
	UPDATE M_Product
      SET IsVerified = 'Y'
	WHERE	M_Product_ID=Record_ID;

	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = Message
    WHERE   AD_PInstance_ID=PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		ResultStr := ResultStr || ': ' || SQLERRM || ' - ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = ResultStr
        WHERE   AD_PInstance_ID=PInstance_ID;
        COMMIT;
		--
		UPDATE M_Product
    	  SET IsVerified = 'N'
		WHERE	M_Product_ID=Record_ID;
		COMMIT;
		--
        RETURN;

END M_Product_BOM_Check;
/

CREATE OR REPLACE PROCEDURE M_Product_Delete
(
	whereClause	IN VARCHAR2	DEFAULT NULL 
)
AS
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	Delete Products
 */
	CURSOR CUR_DEL IS
		SELECT 	M_Product_ID, Value, Name
		FROM 	M_Product 
		WHERE	IsActive='N';
	--
	SQL_Base		VARCHAR2(255) := 'SELECT M_Product_ID FROM M_Product WHERE ';
--	SQL_Where		VARCHAR2(255) := 'ValueX IN (SELECT ValueX FROM M_Product GROUP BY ValueX HAVING Count(*) <> 1) AND INSTR(Value,''@'') <> 0';
	SQL_Statement	VARCHAR2(255);
BEGIN
	--	Delete inactive
	IF (whereClause IS NULL OR LENGTH(whereClause) = 0) THEN
		For d IN CUR_DEL LOOP
			BEGIN
				DBMS_OUTPUT.PUT('Deleting ' || d.Name || ' - ');
				DELETE M_Product
				WHERE M_Product_ID=d.M_Product_ID;
				DBMS_OUTPUT.PUT_LINE('OK');
			EXCEPTION WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE('Error ' || SQLERRM);
			END;
		END LOOP;
	END IF;
END M_Product_Delete;
/

CREATE OR REPLACE PROCEDURE M_Production_Run
(
	PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Production of BOMs
 * Description:
 *		1) Creating ProductionLines when IsCreated = 'N'
 *		2) Posting the Lines (optionally only when fully stocked)
 ************************************************************************/
AS
	--	Logistice
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000);
	Record_ID						NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	MustBeStocked					CHAR(1);
	IsCreated						CHAR(1);
	Processed						CHAR(1);
	Client_ID						NUMBER;
	Org_ID							NUMBER;
	--
	Line							NUMBER;
	NextNo							NUMBER;
	CountNo							NUMBER;
	--	ProductionPlan
	CURSOR CUR_PP	IS
		SELECT 	*
		FROM	M_ProductionPlan
		WHERE	M_Production_ID=Record_ID
		ORDER BY Line, M_Product_ID;
	--	BOM Lines
	CURSOR CUR_BOM (Product_ID NUMBER)	IS
		SELECT 	*
		FROM	M_Product_BOM
		WHERE	M_Product_ID=Product_ID
		ORDER BY Line;
	--	ProductionLines which are non-stocked BOMs (need to be resolved)
	CURSOR CUR_PLineBOM (ProductionPlan_ID NUMBER) 	IS
		SELECT pl.M_ProductionLine_ID, pl.Line, pl.M_Product_ID, pl.MovementQty
		FROM M_ProductionLine pl, M_Product p
		WHERE pl.M_ProductionPlan_ID=ProductionPlan_ID
		  AND pl.M_Product_ID=p.M_Product_ID
		  AND pl.Line<>100	--	Origin Line
		  AND p.IsBOM='Y' AND p.IsStocked='N';

	--	Posting
	CURSOR CUR_PL_Post	IS
		SELECT pl.M_ProductionLine_ID, pl.AD_Client_ID, pl.AD_Org_ID, p.MovementDate,
			pl.M_Product_ID, pl.M_AttributeSetInstance_ID, pl.MovementQty, pl.M_Locator_ID
		FROM M_Production p, M_ProductionLine pl, M_ProductionPlan pp
		WHERE p.M_Production_ID=pp.M_Production_ID
		  AND pp.M_ProductionPlan_ID=pl.M_ProductionPlan_ID
		  AND pp.M_Production_ID=Record_ID
		ORDER BY pp.Line, pl.Line;



BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || PInstance_ID);
    ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=PInstance_ID;
    COMMIT;

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		Record_ID := p.Record_ID;
		IF (p.ParameterName = 'MustBeStocked') THEN
 			MustBeStocked := p.P_String;
			DBMS_OUTPUT.PUT_LINE('  MustBeStocked=' || MustBeStocked);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	--	Processing??? Lock ????
	--	TODO

	/**
	 *	Get Info + Lock
	 */
	ResultStr := 'ReadingRecord';
	SELECT 	IsCreated, Processed, AD_Client_ID, AD_Org_ID
	  INTO 	IsCreated, Processed, Client_ID, Org_ID
	FROM	M_Production
	WHERE	M_Production_ID=Record_ID
	FOR UPDATE;

	/**
	 *	No Action
	 */
	IF (Processed <> 'N') THEN
		Message := '@AlreadyPosted@';
		GOTO FINISH_PROCESS;
	END IF;
	
	/**************************************************************************
	 *	Create Lines
	 */
	IF (IsCreated <> 'Y') THEN
		-- For every Production Plan
		FOR pp IN CUR_PP LOOP
			--	Delete prior lines
			DELETE 	M_ProductionLine
			WHERE 	M_ProductionPlan_ID=pp.M_ProductionPlan_ID;
		--	DBMS_OUTPUT.PUT_LINE('ProductionPlan=' || pp.M_ProductionPlan_ID);
			--	Create BOM Line
			ResultStr := 'CreatingLine BOM';
			Line := 100;	--	OriginLine
			AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
			INSERT INTO M_ProductionLine
				(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
				AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
				M_Product_ID, MovementQty, M_Locator_ID, Description)
		   	VALUES
				(NextNo, pp.M_ProductionPlan_ID, Line,
				pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
				pp.M_Product_ID, pp.ProductionQty, pp.M_Locator_ID, pp.Description);

			--	Create First Level
			FOR bom IN CUR_BOM (pp.M_Product_ID) LOOP
				ResultStr := 'CreatingLine Products';
				Line := Line + 100;
				AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
				INSERT INTO M_ProductionLine
					(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
					AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
					M_Product_ID, MovementQty, M_Locator_ID)
			   	VALUES
					(NextNo, pp.M_ProductionPlan_ID, Line,
					pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
					bom.M_ProductBOM_ID, -pp.ProductionQty*bom.BOMQty, pp.M_Locator_ID);
			END LOOP;

			--	While we have BOMs
			LOOP
				--	Are there non-stored BOMs to list details?
				ResultStr := 'CreatingLine CheckBOM';
				SELECT COUNT(*) INTO CountNo
				FROM M_ProductionLine pl, M_Product p
				WHERE pl.M_Product_ID=p.M_Product_ID
				  AND pl.M_ProductionPlan_ID=pp.M_ProductionPlan_ID
				  AND pl.Line<>100	--	Origin Line
				  AND p.IsBOM='Y' AND p.IsStocked='N';
				--	Nothing to do	
				EXIT WHEN (CountNo = 0);
				--

				--	Resolve BOMs in ProductLine which are not stocked
				FOR pl IN CUR_PLineBOM (pp.M_ProductionPlan_ID) LOOP
					ResultStr := 'CreatingLineBOM Resolution';
					Line := pl.Line;
					--	Resolve BOM Line in product line
					FOR bom IN CUR_BOM (pl.M_Product_ID) LOOP
						ResultStr := 'CreatingLine Products2';
						Line := Line + 10;
						AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
						INSERT INTO M_ProductionLine
							(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
							AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
							M_Product_ID, MovementQty, M_Locator_ID)
			   			VALUES
							(NextNo, pp.M_ProductionPlan_ID, Line,
							pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
							bom.M_ProductBOM_ID, pl.MovementQty*bom.BOMQty, pp.M_Locator_ID);
					END LOOP;
					--	Delete BOM line
					DELETE  M_ProductionLine
					WHERE 	M_ProductionLine_ID=pl.M_ProductionLine_ID;
				END LOOP;
			END LOOP;	--	While we have BOMs

		END LOOP;	--	For every Production Plan

		--	Modifying locator to have sufficient stock


		--	Indicate that it is Created
		UPDATE	M_Production
		  SET	IsCreated='Y'
		WHERE	M_Production_ID=Record_ID;

	/**************************************************************************
	 *	Post Lines
	 */
	ELSE
		--	All Production Lines
		FOR pl IN CUR_PL_Post LOOP
		--	M_ProductionLine_ID, AD_Client_ID, AD_Org_ID, MovementDate, M_Product_ID, MovementQty, M_Locator_ID
		--	DBMS_OUTPUT.PUT_LINE('ProductionLine=' || pl.M_ProductionLine_ID);
		--	DBMS_OUTPUT.PUT_LINE('  Qty=' || pl.MovementQty || ', OnHand=' || BOM_Qty_OnHand(pl.M_Product_ID, NULL, pl.M_Locator_ID));
			--	Check Stock levels for reductions
			IF (pl.MovementQty < 0 AND MustBeStocked <> 'N'
					AND bomQtyOnHand(pl.M_Product_ID, NULL, pl.M_Locator_ID)+pl.MovementQty < 0) THEN
				ROLLBACK;
				SELECT '@NotEnoughStocked@: ' || Name	INTO Message
				FROM M_Product WHERE M_Product_ID=pl.M_Product_ID;
				GOTO FINISH_PROCESS;
			END IF;

			--	Adjust Quantity at Location
			UPDATE	M_Storage
			  SET	QtyOnHand = QtyOnHand + pl.MovementQty,
					Updated = SysDate
		 	WHERE	M_Locator_ID = pl.M_Locator_ID
              AND   M_AttributeSetInstance_ID = COALESCE(pl.M_AttributeSetInstance_ID,0)
			  AND	M_Product_ID = pl.M_Product_ID;
			--	Product not on Stock yet
			IF (SQL%ROWCOUNT = 0) THEN
				INSERT INTO M_Storage
					(M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID,
					 AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
					 QtyOnHand, QtyReserved, QtyOrdered)
			 	VALUES
					(pl.M_Product_ID, pl.M_Locator_ID, COALESCE(pl.M_AttributeSetInstance_ID,0),
					 pl.AD_Client_ID, pl.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,
					 pl.MovementQty, 0, 0);
			END IF;
				
			--	Create Transaction Entry
			ResultStr := 'CreateTransaction';
			AD_Sequence_Next('M_Transaction', pl.AD_Org_ID, NextNo);
			INSERT INTO M_Transaction
				(M_Transaction_ID, M_ProductionLine_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				MovementType, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				MovementDate, MovementQty)
			VALUES
				(NextNo, pl.M_ProductionLine_ID,
				pl.AD_Client_ID, pl.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,
				'P+', pl.M_Locator_ID, pl.M_Product_ID,	COALESCE(pl.M_AttributeSetInstance_ID,0),	-- not distinguishing between assemby/disassembly
				pl.MovementDate, pl.MovementQty);
            --
    		UPDATE	M_ProductionLine
	    	  SET	Processed='Y'
		    WHERE	M_ProductionLine_ID=pl.M_ProductionLine_ID;
		END LOOP;

		--	Indicate that we are done
		UPDATE	M_Production
		  SET	Processed='Y'
		WHERE	M_Production_ID=Record_ID;
		UPDATE	M_ProductionPlan
		  SET	Processed='Y'
		WHERE	M_Production_ID=Record_ID;

	END IF;
	--	Only commit when entire job successful
	COMMIT;

<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = Message
    WHERE   AD_PInstance_ID=PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		ResultStr := ResultStr || ': ' || SQLERRM || ' - ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = ResultStr
        WHERE   AD_PInstance_ID=PInstance_ID;
        COMMIT;
        RETURN;

END M_Production_Run;
/

CREATE OR REPLACE PROCEDURE DBA_Recompile
(
	p_PInstance_ID			IN NUMBER --	DEFAULT NULL
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	 Recompile all User_Objects
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_Message						VARCHAR2(2000) := ' ';
	v_Result						NUMBER := 1;	-- 	0=failure
	--
	v_Buffer						VARCHAR2(2000);
	v_Line						VARCHAR(100);
	v_PrintInfo					CHAR(1) := 'N';	--	Diagnostic
	--
	CURSOR	Cur_Invalids IS
		SELECT	object_id, object_name, object_type
		FROM	user_objects
		WHERE	status <> 'VALID'
		  AND	object_type IN ('VIEW', 'PACKAGE', 'PACKAGE BODY', 'FUNCTION', 
								'PROCEDURE', 'TRIGGER', 'JAVA CLASS')
		ORDER BY object_type, object_name;

	CURSOR	Cur_Valids (p_id NUMBER) IS
		SELECT	'FOUND'
		FROM	user_objects
		WHERE	status = 'VALID'
		  AND	object_id = p_id;

	--  failed compile
	TYPE invalid_tab IS TABLE OF Cur_Invalids%ROWTYPE INDEX BY BINARY_INTEGER;
	invalid_tab_rec invalid_tab;

	count_compiled	PLS_INTEGER;
	valid_text		VARCHAR2(5);
	exec_cursor		PLS_INTEGER := DBMS_SQL.OPEN_CURSOR;
	sql_statement	VARCHAR2(200);
	count_object	PLS_INTEGER := 0;

BEGIN
	LOOP
		count_compiled := 0;
		FOR ci IN Cur_Invalids LOOP
			--  not unsuccessfuly compiled yet
			IF NOT invalid_tab_rec.EXISTS(ci.object_id) THEN
				IF (ci.object_type = 'JAVA CLASS') THEN
					sql_statement := 'ALTER JAVA CLASS "' || ci.object_name || '" RESOLVE';
				ELSIF (ci.object_type = 'PACKAGE BODY') THEN
					sql_statement := 'ALTER PACKAGE ' || ci.object_name || ' COMPILE BODY';
				ELSE
					sql_statement := 'ALTER ' || ci.object_type || ' ' || ci.object_name || ' COMPILE';
				END IF;
				--  compile
				BEGIN
					count_object := count_object + 1;
					DBMS_SQL.PARSE(exec_cursor, sql_statement, DBMS_SQL.NATIVE);
				EXCEPTION
					WHEN OTHERS THEN
						NULL;
				END;
				-- 
				OPEN Cur_Valids (ci.object_ID);
				FETCH Cur_Valids INTO valid_text;
				IF Cur_Valids%ROWCOUNT > 0 THEN
					IF (v_PrintInfo = 'Y') THEN
						DBMS_OUTPUT.PUT_LINE('OK: ' || ci.object_type || ' ' || ci.object_name);
					END IF;
					count_compiled := count_compiled + 1;
					CLOSE Cur_Valids;
					EXIT;
				ELSE
					IF (LENGTH(v_Message) < 1950) THEN
						v_Message := v_Message || ci.object_name || ' ';
					END IF;
					IF (v_PrintInfo = 'Y') THEN
						DBMS_OUTPUT.PUT_LINE('Error: ' || ci.object_type || ' ' || ci.object_name);
					END IF;
					--
					invalid_tab_rec(ci.object_id).object_name := ci.object_name;
					invalid_tab_rec(ci.object_id).object_type := ci.object_type;
					CLOSE Cur_Valids;
				END IF;
			END IF; -- not unsuccessfuly compiled yet
		END LOOP;	-- Cur_Invalids
		--  any other to be compiled
		IF count_compiled = 0 THEN
			EXIT;
		END IF;
	END LOOP;	-- outer loop

	DBMS_SQL.CLOSE_CURSOR(exec_cursor);
	--
	--	Print Message
	IF (LENGTH(v_Message) = 1) THEN
		v_Message := 'All valid';
		DBMS_OUTPUT.PUT_LINE(v_Message);
	ELSIF (LENGTH(v_Message) > 80) THEN
		v_Buffer := v_Message;
		DBMS_OUTPUT.PUT_LINE('>');
		WHILE (LENGTH(v_Buffer) > 0) LOOP
			v_Line := SUBSTR(v_Buffer, 1, 80);
			DBMS_OUTPUT.PUT_LINE(v_Line);
			v_Buffer := SUBSTR(v_Buffer, 81);
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('<');		
		v_Result := 0;
		DBMS_OUTPUT.PUT_LINE('ERROR');
	ELSE
		DBMS_OUTPUT.PUT_LINE('>' || v_Message || '<');
		v_Result := 0;
		DBMS_OUTPUT.PUT_LINE('ERROR');
	END IF;

<<FINISH_PROCESS>>
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = v_Result,			-- 1=success
			ErrorMsg = v_Message
		WHERE	AD_PInstance_ID=p_PInstance_ID;
	END IF;
	COMMIT;
	RETURN;


EXCEPTION
	WHEN OTHERS THEN
		DBMS_OUTPUT.PUT_LINE(SQLERRM);
		IF DBMS_SQL.IS_OPEN(exec_cursor) THEN
			DBMS_SQL.CLOSE_CURSOR(exec_cursor);
		END IF;
		IF Cur_Valids%ISOPEN THEN
			CLOSE Cur_Valids;
		END IF;
END DBA_Recompile;
/

CREATE OR REPLACE PROCEDURE DBA_AfterImport
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 * $Source: /cvs/adempiere/db/database/DatabaseBuild.sql,v $
 ***
 * Title:	 Run after Import 
 * Description:
 *  - Set Java Permissions
 *	- Recompile
 *	- Compute Statistics
 *****************************************************************************/
 
	--	Statistics
	CURSOR Cur_Stat IS
		SELECT 	Table_Name, Blocks
		FROM 	USER_TABLES 
		WHERE 	DURATION IS NULL		--	No temporary tables
          AND   Table_Name NOT LIKE '%$%'
		  AND	(LAST_ANALYZED IS NULL OR LAST_ANALYZED < SysDate-7);
	--
	v_Cmd				VARCHAR2(256);
	v_NoC				NUMBER := 0;
	--
BEGIN
	--	Recompile
	DBA_Recompile(NULL);
	
	--	Statistics
	FOR s IN Cur_Stat LOOP
		v_Cmd := 'ANALYZE TABLE ' || s.Table_Name || ' COMPUTE STATISTICS';
    --	DBMS_OUTPUT.PUT_LINE (v_Cmd);
		v_NoC := v_NoC + 1;
		EXECUTE IMMEDIATE v_Cmd;
	END LOOP;
	DBMS_OUTPUT.PUT_LINE ('Statistics computed: ' || v_NoC);
	--
END DBA_AfterImport;
/

CREATE OR REPLACE PROCEDURE DBA_Cleanup
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Cleanup old temporary data
 * Description:
 ************************************************************************/
AS
BEGIN
	DBMS_OUTPUT.PUT_LINE('DBA_Cleanup');
	--  Clean up data
    /**
--	C_Invoice_CheckPaid();
    UPDATE C_Payment_v 
      SET IsAllocated=CASE WHEN paymentAllocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE IsAllocated='N';
    UPDATE C_Invoice_v1	
 	  SET IsPaid = CASE WHEN invoicePaid(C_Invoice_ID,C_Currency_ID,MultiplierAP)=GrandTotal THEN 'Y' ELSE 'N' END 
    WHERE IsPaid='N';
    **/
    
	--  Temporary Tables
	DELETE FROM T_Aging;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Aging=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_DistributionRunDetail;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_DistributionRunDetail=' || SQL%ROWCOUNT);
    END IF;

	DELETE FROM T_InventoryValue;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_InventoryValue=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Replenish;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Replenish=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Report;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Report=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_ReportStatement;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_ReportStatement=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_TrialBalance;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_TrialBalance=' || SQL%ROWCOUNT);
    END IF;

	DELETE FROM T_Selection;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Selection=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Selection2;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Selection2=' || SQL%ROWCOUNT);
    END IF;
    
	DELETE FROM T_Spool;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' T_Spool=' || SQL%ROWCOUNT);
    END IF;
    
	--	Search Info
	DELETE FROM AD_Find;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' AD_Find=' || SQL%ROWCOUNT);
    END IF;
    
	--	Processes older than a week
	DELETE FROM AD_PInstance WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_PInstance=' || SQL%ROWCOUNT);
    END IF;
    
    /**  Old Session (1 Week) 
    DELETE FROM AD_ChangeLog WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_ChangeLock=' || SQL%ROWCOUNT);
    END IF;
    DELETE FROM AD_Session WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_Session=' || SQL%ROWCOUNT);
    END IF;
    /**    */

	--	Errors older than 1 week
	DELETE FROM AD_Error WHERE Created < SysDate-7;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Old AD_Error=' || SQL%ROWCOUNT);
    END IF;
    
	--  Acknowledged Notes older than a day
	DELETE FROM AD_Note WHERE Processed='Y' AND Updated < SysDate-1;
    IF (SQL%ROWCOUNT <> 0) THEN
    	DBMS_OUTPUT.PUT_LINE(' Processed AD_Note=' || SQL%ROWCOUNT);
    END IF;
    
	--
	COMMIT;
END DBA_Cleanup;
/

CREATE OR REPLACE FUNCTION DBA_DisplayType
(
	AD_Reference_ID		IN	NUMBER
)
RETURN VARCHAR2
AS
BEGIN
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Show clear text od DisplayType
 * Description:
 ************************************************************************/
	IF (AD_Reference_ID = 10)    THEN RETURN 'String    = 10';
	ELSIF (AD_Reference_ID = 11) THEN RETURN 'Integer   = 11';
	ELSIF (AD_Reference_ID = 12) THEN RETURN 'Amount    = 12';
	ELSIF (AD_Reference_ID = 13) THEN RETURN 'ID        = 13';
	ELSIF (AD_Reference_ID = 14) THEN RETURN 'Text      = 14';
	ELSIF (AD_Reference_ID = 15) THEN RETURN 'Date      = 15';
	ELSIF (AD_Reference_ID = 16) THEN RETURN 'DateTime  = 16';
	ELSIF (AD_Reference_ID = 17) THEN RETURN 'List      = 17';
	ELSIF (AD_Reference_ID = 18) THEN RETURN 'Table     = 18';
	ELSIF (AD_Reference_ID = 19) THEN RETURN 'TableDir  = 19';
	ELSIF (AD_Reference_ID = 20) THEN RETURN 'YesNo     = 20';
	ELSIF (AD_Reference_ID = 21) THEN RETURN 'Location  = 21';
	ELSIF (AD_Reference_ID = 22) THEN RETURN 'Number    = 22';
	ELSIF (AD_Reference_ID = 23) THEN RETURN 'Binary    = 23';
	ELSIF (AD_Reference_ID = 24) THEN RETURN 'Time      = 24';
	ELSIF (AD_Reference_ID = 25) THEN RETURN 'Account   = 25';
	ELSIF (AD_Reference_ID = 26) THEN RETURN 'RowID     = 26';
	ELSIF (AD_Reference_ID = 27) THEN RETURN 'Color     = 27';
	ELSIF (AD_Reference_ID = 28) THEN RETURN 'Button    = 28';
	ELSIF (AD_Reference_ID = 29) THEN RETURN 'Quantity  = 29';
	ELSIF (AD_Reference_ID = 30) THEN RETURN 'Search    = 30';
	ELSIF (AD_Reference_ID = 31) THEN RETURN 'Locator   = 31';
	ELSIF (AD_Reference_ID = 32) THEN RETURN 'Image     = 32';  
	ELSIF (AD_Reference_ID = 33) THEN RETURN 'Assignment= 33';  
	ELSE  RETURN 'Unknown ('||AD_Reference_ID||')';  END IF;
END DBA_DisplayType;
/

CREATE OR REPLACE PROCEDURE AD_Synchronize
(
	p_PInstance_ID			IN NUMBER --	DEFAULT NULL
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Syncronize Application Dictionary
 * Description:
 *		Synchronize Elements
 *		Update Column and Field with Names from Element and Process
 *		Update Process Parameters from Elements
 *		Update Workflow Notes from Windows
 *		Update Menu from Window/Form/Process/Task
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr					VARCHAR2(2000);
	v_Message					VARCHAR2(2000);
	v_Result					NUMBER := 1;	-- 0=failure
	v_Record_ID					NUMBER;
	v_AD_User_ID				NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables

BEGIN
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
		v_ResultStr := 'PInstanceNotFound';
		UPDATE AD_PInstance
		SET Created = SysDate,
			IsProcessing = 'Y'
		WHERE AD_PInstance_ID=p_PInstance_ID;
		COMMIT;

		--	Get Parameters
		v_ResultStr := 'ReadingParameters';
		FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
			v_Record_ID := p.Record_ID;
			v_AD_User_ID := p.AD_User_ID;
		END LOOP;	--	Get Parameter
		DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);
	END IF;

	---------------------------------------------------------------------------

	DBMS_OUTPUT.PUT_LINE('Adding missing Elements');
	DECLARE
		NextNo		NUMBER;
		CURSOR Cur_Column	IS
			SELECT DISTINCT ColumnName, Name, Description, Help, EntityType
			FROM	AD_Column c
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Element e
				WHERE UPPER(c.ColumnName)=UPPER(e.ColumnName));
		CURSOR Cur_Process	IS
			SELECT DISTINCT ColumnName, Name, Description, Help, EntityType
			FROM	AD_Process_Para p
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Element e
				WHERE UPPER(p.ColumnName)=UPPER(e.ColumnName));
		CC	Cur_Column%ROWTYPE;
	BEGIN
		DBMS_OUTPUT.PUT_LINE('Column:');
		FOR CC IN Cur_Column LOOP
			AD_Sequence_Next('AD_Element', 0, NextNo);	--	get ID
			INSERT INTO AD_ELEMENT
				(AD_ELEMENT_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				ColumnName, Name, PrintName, Description, Help, EntityType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				CC.ColumnName, CC.Name, CC.Name, CC.Description, CC.Help, CC.EntityType);
			DBMS_OUTPUT.PUT_LINE('  added ' || cc.ColumnName);
			COMMIT;
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('Parameter:');	
		FOR CC IN Cur_Process LOOP
			AD_Sequence_Next('AD_Element', 0, NextNo);	--	get ID
			INSERT INTO AD_ELEMENT
				(AD_ELEMENT_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				ColumnName, Name, PrintName, Description, Help, EntityType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				CC.ColumnName, CC.Name, CC.Name, CC.Description, CC.Help, CC.EntityType);
			DBMS_OUTPUT.PUT_LINE('  added ' || cc.ColumnName);
			COMMIT;
		END LOOP;		
	END;


	DBMS_OUTPUT.PUT_LINE('Adding missing Element Translations');
	INSERT INTO AD_Element_Trl (AD_Element_ID, AD_Language, AD_Client_ID, AD_Org_ID,
		IsActive, Created, CreatedBy, Updated, UpdatedBy,
		Name, PrintName, Description, Help, IsTranslated)
	SELECT m.AD_Element_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
		m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
		m.Name, m.PrintName, m.Description, m.Help, 'N'
	FROM	AD_Element m, AD_Language l
	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Element_ID || AD_Language NOT IN 
		(SELECT AD_Element_ID || AD_Language FROM AD_Element_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);


	DBMS_OUTPUT.PUT_LINE('Creating link from Element to Column');
	UPDATE	AD_Column c
	SET		AD_Element_id = 
				(SELECT AD_Element_ID FROM AD_Element e 
				WHERE UPPER(c.ColumnName)=UPPER(e.ColumnName))
	WHERE AD_Element_ID IS NULL;
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    COMMIT;


	DBMS_OUTPUT.PUT_LINE('Deleting unused Elements');
	DELETE	AD_Element_Trl
	WHERE	AD_Element_ID IN
		(SELECT AD_Element_ID FROM AD_Element e 
		WHERE NOT EXISTS
			(SELECT * FROM AD_Column c WHERE UPPER(e.ColumnName)=UPPER(c.ColumnName))
		AND NOT EXISTS
			(SELECT * FROM AD_Process_Para p WHERE UPPER(e.ColumnName)=UPPER(p.ColumnName)));

	DELETE	AD_Element e
	WHERE NOT EXISTS
		(SELECT * FROM AD_Column c WHERE UPPER(e.ColumnName)=UPPER(c.ColumnName))
	AND NOT EXISTS
		(SELECT * FROM AD_Process_Para p WHERE UPPER(e.ColumnName)=UPPER(p.ColumnName));
	DBMS_OUTPUT.PUT_LINE('  rows deleted: ' || SQL%ROWCOUNT);

	---------------------------------------------------------------------------

	--	Columns
	DBMS_OUTPUT.PUT_LINE('Synchronize Column');
    /*  Identify offending column
SELECT UPPER(ColumnName)
FROM AD_Element
GROUP BY UPPER(ColumnName)
HAVING COUNT(UPPER(ColumnName)) > 1

SELECT c.ColumnName, e.ColumnName
FROM AD_Column c
  INNER JOIN AD_Element e ON (c.AD_Element_ID=e.AD_Element_ID)
WHERE c.ColumnName <> e.ColumnName
    */
	UPDATE AD_Column c
		SET	(ColumnName, Name, Description, Help) = 
                (SELECT ColumnName, Name, Description, Help 
                FROM AD_Element e WHERE c.AD_Element_ID=e.AD_Element_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Element e 
				WHERE c.AD_Element_ID=e.AD_Element_ID
				  AND (c.ColumnName <> e.ColumnName OR c.Name <> e.Name 
					OR NVL(c.Description,' ') <> NVL(e.Description,' ') OR NVL(c.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Fields should now be syncronized
	DBMS_OUTPUT.PUT_LINE('Synchronize Field');
	UPDATE AD_Field f
		SET (Name, Description, Help) = 
                (SELECT e.Name, e.Description, e.Help
                FROM AD_Element e, AD_Column c
	    	    WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e, AD_Column c
				WHERE f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND (f.Name <> e.Name OR NVL(f.Description,' ') <> NVL(e.Description,' ') OR NVL(f.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize Field Translations');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT e.Name FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Description = (SELECT e.Description FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Help = (SELECT e.Help FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			IsTranslated = (SELECT e.IsTranslated FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Field f, AD_Element_trl e, AD_Column c
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND trl.AD_Language=e.AD_Language
				  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				  AND (trl.Name <> e.Name OR NVL(trl.Description,' ') <> NVL(e.Description,' ') OR NVL(trl.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Fields should now be syncronized
	DBMS_OUTPUT.PUT_LINE('Synchronize PO Field');
	UPDATE AD_Field f
		SET Name = (SELECT e.PO_Name FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Description = (SELECT e.PO_Description FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Help = (SELECT e.PO_Help FROM AD_Element e, AD_Column c
					WHERE e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e, AD_Column c
				WHERE f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND (f.Name <> e.PO_Name OR NVL(f.Description,' ') <> NVL(e.PO_Description,' ') OR NVL(f.Help,' ') <> NVL(e.PO_Help,' '))
				  AND e.PO_Name IS NOT NULL)
	  AND EXISTS (SELECT * FROM AD_Tab t, AD_Window w
				WHERE f.AD_Tab_ID=t.AD_Tab_ID
				  AND t.AD_Window_ID=w.AD_Window_ID
				  AND w.IsSOTrx='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize PO Field Translations');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT e.PO_Name FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Description = (SELECT e.PO_Description FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Help = (SELECT e.PO_Help FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			IsTranslated = (SELECT e.IsTranslated FROM AD_Element_trl e, AD_Column c, AD_Field f
					WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID 
					  AND c.AD_Column_ID=f.AD_Column_ID AND f.AD_Field_ID=trl.AD_Field_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Field f, AD_Element_trl e, AD_Column c
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Column_ID=c.AD_Column_ID
				  AND c.AD_Element_ID=e.AD_Element_ID AND c.AD_Process_ID IS NULL
				  AND trl.AD_Language=e.AD_Language
				  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				  AND (trl.Name <> e.PO_Name OR NVL(trl.Description,' ') <> NVL(e.PO_Description,' ') OR NVL(trl.Help,' ') <> NVL(e.PO_Help,' '))
				  AND e.PO_Name IS NOT NULL)
	  AND EXISTS (SELECT * FROM AD_Field f, AD_Tab t, AD_Window w
				WHERE trl.AD_Field_ID=f.AD_Field_ID
				  AND f.AD_Tab_ID=t.AD_Tab_ID
				  AND t.AD_Window_ID=w.AD_Window_ID
				  AND w.IsSOTrx='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);


	--	Fields from Process
	DBMS_OUTPUT.PUT_LINE('Synchronize Field from Process');
	UPDATE AD_Field f
		SET Name = (SELECT p.Name FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Description = (SELECT p.Description FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Help = (SELECT p.Help FROM AD_Process p, AD_Column c WHERE p.AD_Process_ID=c.AD_Process_ID
					AND c.AD_Column_ID=f.AD_Column_ID),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y' 
	  AND EXISTS (SELECT * FROM AD_Process p, AD_Column c
				WHERE c.AD_Process_ID=p.AD_Process_ID AND f.AD_Column_ID=c.AD_Column_ID
				AND (f.Name<>p.Name OR NVL(f.Description,' ')<>NVL(p.Description,' ') OR NVL(f.Help,' ')<>NVL(p.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Field Translations from Process
	DBMS_OUTPUT.PUT_LINE('Synchronize Field Trl from Process Trl');
	UPDATE AD_Field_trl trl
		SET Name = (SELECT p.Name FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Description = (SELECT p.Description FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Help = (SELECT p.Help FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			IsTranslated = (SELECT p.IsTranslated FROM AD_Process_trl p, AD_Column c, AD_Field f 
					WHERE p.AD_Process_ID=c.AD_Process_ID AND c.AD_Column_ID=f.AD_Column_ID
					AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Process_Trl p, AD_Column c, AD_Field f
				WHERE c.AD_Process_ID=p.AD_Process_ID AND f.AD_Column_ID=c.AD_Column_ID
				AND f.AD_Field_ID=trl.AD_Field_ID AND p.AD_Language=trl.AD_Language
				AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
				AND (trl.Name<>p.Name OR NVL(trl.Description,' ')<>NVL(p.Description,' ') OR NVL(trl.Help,' ')<>NVL(p.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Sync Parameter ColumnName
	UPDATE	AD_Process_Para f
		SET	ColumnName = (SELECT e.ColumnName FROM AD_Element e
					WHERE UPPER(e.ColumnName)=UPPER(f.ColumnName))
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e
		WHERE UPPER(e.ColumnName)=UPPER(f.ColumnName)
		AND e.ColumnName<>f.ColumnName);


	--	Paramenter Fields
	UPDATE	AD_Process_Para p
	  SET	IsCentrallyMaintained = 'N'
	WHERE	IsCentrallyMaintained <> 'N'
	  AND NOT EXISTS (SELECT * FROM AD_Element e WHERE p.ColumnName=e.ColumnName); 

	--	Parameter Fields
	DBMS_OUTPUT.PUT_LINE('Synchronize Process Parameter');
	UPDATE AD_Process_Para f
		SET Name = (SELECT e.Name FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Description = (SELECT e.Description FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Help = (SELECT e.Help FROM AD_Element e
					WHERE e.ColumnName=f.ColumnName),
			Updated = SysDate
	WHERE f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
	  AND EXISTS (SELECT * FROM AD_Element e
				WHERE e.ColumnName=f.ColumnName
				  AND (f.Name <> e.Name OR NVL(f.Description,' ') <> NVL(e.Description,' ') OR NVL(f.Help,' ') <> NVL(e.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Parameter Translations
	DBMS_OUTPUT.PUT_LINE('Synchronize Process Parameter Trl');
	UPDATE AD_Process_Para_Trl trl
		SET Name = (SELECT et.Name FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Description = (SELECT et.Description FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Help = (SELECT et.Help FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			IsTranslated = (SELECT et.IsTranslated FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID),
			Updated = SysDate
	WHERE EXISTS (SELECT * FROM AD_Element_Trl et, AD_Element e, AD_Process_Para f
					WHERE et.AD_Language=trl.AD_Language AND et.AD_Element_ID=e.AD_Element_ID
					  AND e.ColumnName=f.ColumnName AND f.AD_Process_Para_ID=trl.AD_Process_Para_ID
					  AND f.IsCentrallyMaintained='Y' AND f.IsActive='Y'
					  AND (trl.Name <> et.Name OR NVL(trl.Description,' ') <> NVL(et.Description,' ') OR NVL(trl.Help,' ') <> NVL(et.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);


	--	Workflow Node - Window
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Window');
	UPDATE AD_WF_Node n
		SET Name = (SELECT w.Name FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID),
			Description = (SELECT w.Description FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID),
			Help = (SELECT w.Help FROM AD_Window w
					WHERE w.AD_Window_ID=n.AD_Window_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Window w
				WHERE w.AD_Window_ID=n.AD_Window_ID
				  AND (w.Name <> n.Name OR NVL(w.Description,' ') <> NVL(n.Description,' ') OR NVL(w.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Window
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Window Trl');
	UPDATE AD_WF_Node_Trl trl
		SET Name = (SELECT t.Name FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language),
			Description = (SELECT t.Description FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language),
			Help = (SELECT t.Help FROM AD_Window_trl t, AD_WF_Node n
					WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
					  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Window_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Window_ID=t.AD_Window_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Node - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Form');
	UPDATE AD_WF_Node n
		SET (Name, Description, Help) = (SELECT f.Name, f.Description, f.Help 
				FROM AD_Form f
				WHERE f.AD_Form_ID=n.AD_Form_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Form f
				WHERE f.AD_Form_ID=n.AD_Form_ID
				  AND (f.Name <> n.Name OR NVL(f.Description,' ') <> NVL(n.Description,' ') OR NVL(f.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Form Trl');
	UPDATE AD_WF_Node_Trl trl
		SET (Name, Description, Help) = (SELECT t.Name, t.Description, t.Help
			FROM AD_Form_trl t, AD_WF_Node n
			WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Form_ID=t.AD_Form_ID
			  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Form_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Form_ID=t.AD_Form_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Node - Report
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node from Process');
	UPDATE AD_WF_Node n
		SET (Name, Description, Help) = (SELECT f.Name, f.Description, f.Help 
				FROM AD_Process f
				WHERE f.AD_Process_ID=n.AD_Process_ID)
	WHERE n.IsCentrallyMaintained = 'Y'
	  AND EXISTS  (SELECT * FROM AD_Process f
				WHERE f.AD_Process_ID=n.AD_Process_ID
				  AND (f.Name <> n.Name OR NVL(f.Description,' ') <> NVL(n.Description,' ') OR NVL(f.Help,' ') <> NVL(n.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	--	Workflow Translations - Form
	DBMS_OUTPUT.PUT_LINE('Synchronize Workflow Node Trl from Process Trl');
	UPDATE AD_WF_Node_Trl trl
		SET (Name, Description, Help) = (SELECT t.Name, t.Description, t.Help
			FROM AD_Process_trl t, AD_WF_Node n
			WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Process_ID=t.AD_Process_ID
			  AND trl.AD_Language=t.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Process_Trl t, AD_WF_Node n
				WHERE trl.AD_WF_Node_ID=n.AD_WF_Node_ID AND n.AD_Process_ID=t.AD_Process_ID
				  AND trl.AD_Language=t.AD_Language AND n.IsCentrallyMaintained='Y' AND n.IsActive='Y'
				  AND (trl.Name <> t.Name OR NVL(trl.Description,' ') <> NVL(t.Description,' ') OR NVL(trl.Help,' ') <> NVL(t.Help,' ')));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

    --  Need centrally maintained flag here!
	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Name from Element');
	UPDATE AD_PrintFormatItem pfi
	  SET Name = (SELECT e.Name 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID)
	WHERE pfi.IsCentrallyMaintained='Y'
      AND EXISTS (SELECT * 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND e.Name<>pfi.Name)
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=pfi.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem PrintName from Element');
	UPDATE AD_PrintFormatItem pfi
	  SET PrintName = (SELECT e.PrintName 
		FROM AD_Element e, AD_Column c
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID)
	WHERE pfi.IsCentrallyMaintained='Y'
      AND EXISTS (SELECT * 
		FROM AD_Element e, AD_Column c, AD_PrintFormat pf
		WHERE e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND LENGTH(pfi.PrintName) > 0
		  AND e.PrintName<>pfi.PrintName
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID
		  AND pf.IsForm='N' AND IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=pfi.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Trl from Element Trl (Multi-Lingual)');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = (SELECT e.PrintName 
		FROM AD_Element_Trl e, AD_Column c, AD_PrintFormatItem pfi
		WHERE e.AD_Language=trl.AD_Language
		  AND e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
	WHERE EXISTS (SELECT * 
		FROM AD_Element_Trl e, AD_Column c, AD_PrintFormatItem pfi, AD_PrintFormat pf
		WHERE e.AD_Language=trl.AD_Language
		  AND e.AD_Element_ID=c.AD_Element_ID
		  AND c.AD_Column_ID=pfi.AD_Column_ID
		  AND pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID
          AND pfi.IsCentrallyMaintained='Y'
		  AND LENGTH(pfi.PrintName) > 0
		  AND (e.PrintName<>trl.PrintName OR trl.PrintName IS NULL)
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID 
		  AND pf.IsForm='N' AND IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Synchronize PrintFormatItem Trl (Not Multi-Lingual)');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = (SELECT pfi.PrintName 
		FROM AD_PrintFormatItem pfi
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
	WHERE EXISTS (SELECT * 
		FROM AD_PrintFormatItem pfi, AD_PrintFormat pf
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID
          AND pfi.IsCentrallyMaintained='Y'
		  AND LENGTH(pfi.PrintName) > 0
		  AND pfi.PrintName<>trl.PrintName
		  AND pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID 
		  AND pf.IsForm='N' AND pf.IsTableBased='Y')
	  AND EXISTS (SELECT * FROM AD_Client 
		WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='N');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	DBMS_OUTPUT.PUT_LINE('Reset PrintFormatItem Trl where not used in base table');
	UPDATE AD_PrintFormatItem_Trl trl
	  SET PrintName = NULL
	WHERE PrintName IS NOT NULL
	  AND EXISTS (SELECT *
		FROM AD_PrintFormatItem pfi
		WHERE pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID 
          AND pfi.IsCentrallyMaintained='Y'
		  AND (LENGTH (pfi.PrintName) = 0 OR pfi.PrintName IS NULL));
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

/**
SELECT 	e.PrintName "Element", pfi.PrintName "FormatItem", trl.AD_Language, trl.PrintName "Trl"
FROM 	AD_Element e
  INNER JOIN AD_Column c ON (e.AD_Element_ID=c.AD_Element_ID)
  INNER JOIN AD_PrintFormatItem pfi ON (c.AD_Column_ID=pfi.AD_Column_ID)
  INNER JOIN AD_PrintFormatItem_Trl trl ON (pfi.AD_PrintFormatItem_ID=trl.AD_PrintFormatItem_ID)
WHERE pfi.AD_PrintFormatItem_ID=?
**/

	--	Sync Names - Window
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Window');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID),
			Description = (SELECT Description FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID)
	WHERE	AD_Window_ID IS NOT NULL
		AND Action = 'W';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT wt.Name FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language),
			Description = (SELECT wt.Description FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language),
			IsTranslated = (SELECT wt.IsTranslated FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Window_Trl wt, AD_Menu m 
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Window_ID=wt.AD_Window_ID 
					AND mt.AD_Language=wt.AD_Language
					AND m.AD_Window_ID IS NOT NULL
					AND m.Action = 'W');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names - Process
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Processes');
	UPDATE	AD_Menu m
	SET		Name = (SELECT p.Name FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID),
			Description = (SELECT p.Description FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID)
	WHERE	m.AD_Process_ID IS NOT NULL
	  AND	m.Action IN ('R', 'P');
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT pt.Name FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language),
			Description = (SELECT pt.Description FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language),
			IsTranslated = (SELECT pt.IsTranslated FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Process_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Process_ID=pt.AD_Process_ID
					AND mt.AD_Language=pt.AD_Language
					AND m.AD_Process_ID IS NOT NULL
					AND	Action IN ('R', 'P'));
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names = Form
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Forms');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID),
			Description = (SELECT Description FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID)
	WHERE	AD_Form_ID IS NOT NULL
	  AND	Action = 'X';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT ft.Name FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language),
			Description = (SELECT ft.Description FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language),
			IsTranslated = (SELECT ft.IsTranslated FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Form_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Form_ID=ft.AD_Form_ID
					AND mt.AD_Language=ft.AD_Language
					AND m.AD_Form_ID IS NOT NULL
					AND	Action = 'X');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names - Workflow
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Workflows');
	UPDATE	AD_Menu m
	SET		Name = (SELECT p.Name FROM AD_Workflow p WHERE m.AD_Workflow_ID=p.AD_Workflow_ID),
			Description = (SELECT p.Description FROM AD_Workflow p WHERE m.AD_Workflow_ID=p.AD_Workflow_ID)
	WHERE	m.AD_Workflow_ID IS NOT NULL
	  AND	m.Action = 'F';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT pt.Name FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language),
			Description = (SELECT pt.Description FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language),
			IsTranslated = (SELECT pt.IsTranslated FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Workflow_Trl pt, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Workflow_ID=pt.AD_Workflow_ID
					AND mt.AD_Language=pt.AD_Language
					AND m.AD_Workflow_ID IS NOT NULL
					AND	Action = 'F');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

	--	Sync Names = Task
	DBMS_OUTPUT.PUT_LINE('Synchronizing Menu with Tasks');
	UPDATE	AD_Menu m
	SET		Name = (SELECT Name FROM AD_Task f WHERE m.AD_Task_ID=f.AD_Task_ID),
			Description = (SELECT Description FROM AD_Task f WHERE m.AD_Task_ID=f.AD_Task_ID)
	WHERE	AD_Task_ID IS NOT NULL
	  AND	Action = 'T';
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);

	UPDATE	AD_Menu_Trl mt
	SET		Name = (SELECT ft.Name FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language),
			Description = (SELECT ft.Description FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language),
			IsTranslated = (SELECT ft.IsTranslated FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language)
	WHERE EXISTS (SELECT * FROM AD_Task_Trl ft, AD_Menu m
					WHERE mt.AD_Menu_ID=m.AD_Menu_ID AND m.AD_Task_ID=ft.AD_Task_ID
					AND mt.AD_Language=ft.AD_Language
					AND m.AD_Task_ID IS NOT NULL
					AND	Action = 'T');
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

    --  Column Name + Element
	DBMS_OUTPUT.PUT_LINE('Synchronizing Column with Element');
    UPDATE AD_Column c
      SET (Name,Description,Help) = 
        (SELECT e.Name,e.Description,e.Help 
        FROM AD_Element e WHERE c.AD_Element_ID=e.AD_Element_ID)
    WHERE EXISTS 
        (SELECT * FROM AD_Element e 
        WHERE c.AD_Element_ID=e.AD_Element_ID
          AND c.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Column_Trl ct
      SET Name = (SELECT e.Name
        FROM AD_Column c INNER JOIN AD_Element_Trl e ON (c.AD_Element_ID=e.AD_Element_ID)
        WHERE ct.AD_Column_ID=c.AD_Column_ID AND ct.AD_Language=e.AD_Language)
    WHERE EXISTS 
        (SELECT * FROM AD_Column c INNER JOIN AD_Element_Trl e ON (c.AD_Element_ID=e.AD_Element_ID)
        WHERE ct.AD_Column_ID=c.AD_Column_ID AND ct.AD_Language=e.AD_Language
          AND ct.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);
    
    
    --  Table Name + Element
	DBMS_OUTPUT.PUT_LINE('Synchronizing Table with Element');
    UPDATE AD_Table t
      SET (Name,Description) = (SELECT e.Name,e.Description FROM AD_Element e 
        WHERE t.TableName||'_ID'=e.ColumnName)
    WHERE EXISTS (SELECT * FROM AD_Element e 
        WHERE t.TableName||'_ID'=e.ColumnName
          AND t.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Table_Trl tt
      SET Name = (SELECT e.Name 
        FROM AD_Table t INNER JOIN AD_Element ex ON (t.TableName||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language)
    WHERE EXISTS (SELECT * 
        FROM AD_Table t INNER JOIN AD_Element ex ON (t.TableName||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language
          AND tt.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

    --  Trl Table Name + Element
    UPDATE AD_Table t
      SET (Name,Description) = (SELECT e.Name||' Trl', e.Description 
        FROM AD_Element e 
        WHERE SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=e.ColumnName)
    WHERE TableName LIKE '%_Trl'
      AND EXISTS (SELECT * FROM AD_Element e 
        WHERE SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=e.ColumnName
          AND t.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  rows updated: ' || SQL%ROWCOUNT);
    UPDATE AD_Table_Trl tt
      SET Name = (SELECT e.Name || ' **'
        FROM AD_Table t INNER JOIN AD_Element ex ON (SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language)
    WHERE EXISTS (SELECT * 
        FROM AD_Table t INNER JOIN AD_Element ex ON (SUBSTR(t.TableName,1,LENGTH(t.TableName)-4)||'_ID'=ex.ColumnName)
          INNER JOIN AD_Element_Trl e ON (ex.AD_Element_ID=e.AD_Element_ID)
        WHERE tt.AD_Table_ID=t.AD_Table_ID AND tt.AD_Language=e.AD_Language
          AND t.TableName LIKE '%_Trl'
          AND tt.Name<>e.Name);
	DBMS_OUTPUT.PUT_LINE('  trl rows updated: ' || SQL%ROWCOUNT);

    /** Remaining tables
    SELECT Name, TableName FROM AD_Table t WHERE Name=TableName ORDER BY 1
    **/


<<FINISH_PROCESS>>
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = v_Result,			-- 1=success
			ErrorMsg = v_Message
		WHERE	AD_PInstance_ID=p_PInstance_ID;
	END IF;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
		IF (p_PInstance_ID IS NOT NULL) THEN
			UPDATE	AD_PInstance
			SET Updated = SysDate,
				IsProcessing = 'N',
				Result = 0,				-- failure
				ErrorMsg = v_ResultStr
			WHERE	AD_PInstance_ID=p_PInstance_ID;
			COMMIT;
		END IF;
		RETURN;

END AD_Synchronize;
/

CREATE OR REPLACE PROCEDURE T_InventoryValue_Create
(
	p_PInstance_ID			IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Inventory Valuation Temporary Table
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr					VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	v_Result						NUMBER := 1;	-- 0=failure
	v_Record_ID					NUMBER;
	v_AD_User_ID					NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_M_PriceList_Version_ID			NUMBER(10);
	p_DateValue					DATE;
	p_M_Warehouse_ID				NUMBER(10);
	p_C_Currency_ID				NUMBER(10);

BEGIN
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
	v_ResultStr := 'PInstanceNotFound';
	UPDATE AD_PInstance
	SET Created = SysDate,
		IsProcessing = 'Y'
	WHERE AD_PInstance_ID=p_PInstance_ID;
	COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
		v_Record_ID := p.Record_ID;
		v_AD_User_ID := p.AD_User_ID;
		IF (p.ParameterName = 'M_PriceList_Version_ID') THEN
			p_M_PriceList_Version_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  M_PriceList_Version_ID=' || p_M_PriceList_Version_ID);
		ELSIF (p.ParameterName = 'DateValue') THEN
			p_DateValue := p.P_Date;
			DBMS_OUTPUT.PUT_LINE('  DateValue=' || p_DateValue);
		ELSIF (p.ParameterName = 'M_Warehouse_ID') THEN
			p_M_Warehouse_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  M_Warehouse_ID=' || p_M_Warehouse_ID);
		ELSIF (p.ParameterName = 'C_Currency_ID') THEN
			p_C_Currency_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  C_Currency_ID=' || p_C_Currency_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
		END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);

	-- Clear
--	v_ResultStr := 'ClearTable';
--	DELETE T_InventoryValue WHERE M_Warehouse_ID=p_M_Warehouse_ID;
--	COMMIT;

	--	Insert Products
	v_ResultStr := 'InsertStockedProducts';
	INSERT INTO T_InventoryValue 
		(AD_Client_ID,AD_Org_ID, AD_PInstance_ID, M_Warehouse_ID,M_Product_ID)
	SELECT AD_Client_ID,AD_Org_ID, p_PInstance_ID, p_M_Warehouse_ID,M_Product_ID
	FROM M_Product 
	WHERE IsStocked='Y';
	--
	IF (SQL%ROWCOUNT = 0) THEN
		v_Message := '@Created@ = 0';
		GOTO FINISH_PROCESS;
	END IF;

	-- Update Constants
	v_ResultStr := 'UpdateConstants';
	UPDATE T_InventoryValue 
	  SET	DateValue = TRUNC(p_DateValue) + 0.9993,
			M_PriceList_Version_ID = p_M_PriceList_Version_ID,
			C_Currency_ID = p_C_Currency_ID
	WHERE	M_Warehouse_ID = p_M_Warehouse_ID;

	--	Get current QtyOnHand
	v_ResultStr := 'GetQtyOnHand';
	UPDATE T_InventoryValue iv
	  SET	QtyOnHand = (SELECT SUM(QtyOnHand) FROM M_Storage s, M_Locator l
				WHERE iv.M_Product_ID=s.M_Product_ID
				 AND l.M_Locator_ID=s.M_Locator_ID
				 AND l.M_Warehouse_ID=iv.M_Warehouse_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	-- Adjust for Valuation Date
	v_ResultStr := 'AdjustQtyOnHand';
	UPDATE T_InventoryValue iv 
	  SET	QtyOnHand = 
				(SELECT iv.QtyOnHand - NVL(SUM(t.MovementQty), 0) 
				FROM M_Transaction t, M_Locator l
				WHERE t.M_Product_ID=iv.M_Product_ID 
    --            AND t.M_AttributeSetInstance_ID=iv.M_AttributeSetInstance_ID
				  AND t.MovementDate > iv.DateValue
				  AND t.M_Locator_ID=l.M_Locator_ID 
				  AND l.M_Warehouse_ID=iv.M_Warehouse_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	--	Delete Records w/o OnHand Qty
	v_ResultStr := 'DeleteZeroQtyOnHand';
	DELETE T_InventoryValue 
	WHERE	QtyOnHand=0 
	  OR	QtyOnHand IS NULL;

	-- Update Prices
	v_ResultStr := 'GetPrices';
	UPDATE T_InventoryValue iv
	  SET	PricePO = 
				(SELECT currencyConvert (po.PriceList,po.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_Product_PO po WHERE po.M_Product_ID=iv.M_Product_ID
				AND po.IsCurrentVendor='Y' AND RowNum=1),
			PriceList = 
				(SELECT currencyConvert(pp.PriceList,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID),
			PriceStd = 
				(SELECT currencyConvert(pp.PriceStd,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID), 
			PriceLimit = 
				(SELECT currencyConvert(pp.PriceLimit,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID),
			CostStandard = 
				(SELECT currencyConvert(pc.CurrentCostPrice,acs.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM AD_ClientInfo ci, C_AcctSchema acs, M_Product_Costing pc
				WHERE iv.AD_Client_ID=ci.AD_Client_ID AND ci.C_AcctSchema1_ID=acs.C_AcctSchema_ID
				 AND acs.C_AcctSchema_ID=pc.C_AcctSchema_ID
				 AND iv.M_Product_ID=pc.M_Product_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	--	Update Values
	v_ResultStr := 'UpdateValue';
	UPDATE T_InventoryValue 
	  SET	PricePOAmt = QtyOnHand * PricePO, 
			PriceListAmt = QtyOnHand * PriceList, 
			PriceStdAmt = QtyOnHand * PriceStd, 
			PriceLimitAmt = QtyOnHand * PriceLimit, 
			CostStandardAmt = QtyOnHand * CostStandard
	WHERE	M_Warehouse_ID = p_M_Warehouse_ID;

	v_Message := '@Created@ = ' || SQL%ROWCOUNT;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
	UPDATE	AD_PInstance
	SET Updated = SysDate,
		IsProcessing = 'N',
		Result = v_Result,			-- 1=success
		ErrorMsg = v_Message
	WHERE	AD_PInstance_ID=p_PInstance_ID;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = 0,				-- failure
			ErrorMsg = v_ResultStr
		WHERE	AD_PInstance_ID=p_PInstance_ID;
		COMMIT;
		RETURN;

END T_InventoryValue_Create;
/

CREATE OR REPLACE PROCEDURE AD_Column_Sync
(
	p_PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Synchronize Column with Database
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr						VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	v_Result						NUMBER := 1;	-- 0=failure
	v_Record_ID						NUMBER;
	v_AD_User_ID					NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	--	Variables
	v_TableName					AD_Table.TableName%TYPE;
	v_ColumnName				AD_Column.ColumnName%TYPE;
	v_AD_Reference_ID			AD_Column.AD_Reference_ID%TYPE;
	v_FieldLength				AD_Column.FieldLength%TYPE;
	v_DefaultValue				AD_Column.DefaultValue%TYPE;
	v_IsMandatory				AD_Column.IsMandatory%TYPE;
	--
	v_DB_DataType				USER_TAB_COLUMNS.DATA_TYPE%TYPE;
	v_Cmd						VARCHAR2(255);
	v_DB_TableName				VARCHAR(60) := NULL;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
	v_ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=p_PInstance_ID;
    COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
		v_Record_ID := p.Record_ID;
		v_AD_User_ID := p.AD_User_ID;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);

	--	Get Table/Column Info
	v_ResultStr := 'ReadingColumnInfo';
	SELECT	t.TableName, c.ColumnName, c.AD_Reference_ID, c.FieldLength, 
		c.DefaultValue, c.IsMandatory
	  INTO	v_TableName, v_ColumnName, v_AD_Reference_ID, v_FieldLength, 
		v_DefaultValue, v_IsMandatory
   	FROM	AD_Table t, AD_Column c
	WHERE	t.AD_Table_ID = c.AD_Table_ID
	  AND	c.AD_Column_ID = v_Record_ID;


	--	Check if Table exists
	v_ResultStr := 'ReadingDBTableInfo';
	BEGIN
		SELECT	Table_Name 
		  INTO	v_DB_TableName
		FROM 	USER_TABLES 
		WHERE 	Table_Name=UPPER(v_TableName);
		EXCEPTION
			WHEN OTHERS THEN NULL;
	END;

	--	Table does not exists
	IF (v_DB_TableName IS NULL) THEN
		v_ResultStr := 'CreateTableCommand';
		BEGIN
			v_CMD := 'CREATE TABLE ' || SYS_CONTEXT('USERENV', 'CURRENT_USER') || '.'
				|| UPPER(v_TableName) || ' (XXXX CHAR(1))';
			EXECUTE IMMEDIATE v_Cmd;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
				GOTO FINISH_PROCESS;
		END;		
	END IF;


	--	Get Data Dictionary Info
	v_ResultStr := 'ReadingDBColumnInfo';
	BEGIN
		SELECT 	DATA_TYPE	--, DATA_LENGTH, DATA_PRECISION, DATA_SCALE, NULLABLE, DATA_DEFAULT
		  INTO	v_DB_DataType
		FROM 	USER_TAB_COLUMNS
		WHERE 	TABLE_NAME=UPPER(v_TableName)
		  AND 	COLUMN_NAME=UPPER(v_ColumnName);
		EXCEPTION
			WHEN OTHERS THEN NULL;
	END;


	/**
	 *	Create Column in Database
	 */
	IF (v_DB_DataType IS NULL) THEN
		v_ResultStr := 'CreateALTERCommand';
		BEGIN
			--	 Get TableName
			v_Cmd := 'ALTER TABLE ' || v_TableName || ' ADD ' || v_ColumnName || ' ';
			--	Map Data Type
			IF (v_AD_Reference_ID IN (10,14)) THEN	
				--	String, Text
				v_Cmd := v_Cmd || 'NVARCHAR2(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (17,20,28)) THEN	
				--	List,YesNo,Button
				v_Cmd := v_Cmd || 'CHAR(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (13,18,19,21,25,27,30,31)) THEN	
				--	ID,Table,TableDir,Location,Account,Color,Search,Locator
				v_Cmd := v_Cmd || 'NUMBER(10)';
		   	ELSIF (v_AD_Reference_ID IN (11,12,22,29)) THEN	
				--	Integer,Amount,Number,Quantity
				v_Cmd := v_Cmd || 'NUMBER';
		   	ELSIF (v_AD_Reference_ID IN (15,16)) THEN	
				--	Date,DateTime
				v_Cmd := v_Cmd || 'DATE';
			ELSE	--	23-Binary, 24-Radio, 26-RowID, 32-Image
				v_Result := 0;	-- failure
				v_Message := 'DisplayType Not Supported';
			END IF;
			--	Default (literal)
			IF (v_DefaultValue IS NOT NULL AND LENGTH(v_DefaultValue) <> 0) THEN
				IF (v_AD_Reference_ID IN (10,14,17,20,28)) THEN	
					v_Cmd := v_Cmd || ' DEFAULT (''' || v_DefaultValue || ''')';
				ELSE
					v_Cmd := v_Cmd || ' DEFAULT ' || v_DefaultValue;
				END IF;
			END IF;
			--	Mandatory
			IF (v_IsMandatory = 'Y') THEN
				IF (v_DefaultValue IS NULL OR LENGTH(v_DefaultValue) = 0) THEN
					v_Result := 0;	-- failure
					v_Message := 'Mandatory requites literal default value';
			   	ELSE
					v_Cmd := v_Cmd || ' NOT NULL';
				END IF;
		  	END IF;
			--	Execute it
			IF (v_Result = 1) THEN
				EXECUTE IMMEDIATE v_Cmd;
				v_Message := '@Created@ - ' || v_Cmd;
			END IF;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;

	/**
	 *	Change certain Attributes
	 */
	ELSE
		v_ResultStr := 'CreateALTERCommand';
		BEGIN
			--	 Get TableName
			v_Cmd := 'ALTER TABLE ' || v_TableName || ' MODIFY ' || v_ColumnName || ' ';
			--	Map Data Type
			IF (v_AD_Reference_ID IN (10,14)) THEN	
				--	String, Text
				v_Cmd := v_Cmd || 'NVARCHAR2(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (17,20,28)) THEN	
				--	List,YesNo,Button
				v_Cmd := v_Cmd || 'CHAR(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (13,18,19,21,25,27,30,31)) THEN	
				--	ID,Table,TableDir,Location,Account,Color,Search,Locator
				v_Cmd := v_Cmd || 'NUMBER(10)';
		   	ELSIF (v_AD_Reference_ID IN (11,12,22,29)) THEN	
				--	Integer,Amount,Number,Quantity
				v_Cmd := v_Cmd || 'NUMBER';
		   	ELSIF (v_AD_Reference_ID IN (15,16)) THEN	
				--	Date,DateTime
				v_Cmd := v_Cmd || 'DATE';
			ELSE	--	23-Binary, 24-Radio, 26-RowID, 32-Image
				v_Result := 0;	-- failure
				v_Message := 'DisplayType Not Supported';
			END IF;
			--	Default (literal)
			IF (v_DefaultValue IS NOT NULL AND LENGTH(v_DefaultValue) <> 0) THEN
				IF (v_AD_Reference_ID IN (10,14,17,20,28)) THEN	
					v_Cmd := v_Cmd || ' DEFAULT (''' || v_DefaultValue || ''')';
				ELSE
					v_Cmd := v_Cmd || ' DEFAULT ' || v_DefaultValue;
				END IF;
			END IF;
			--	Mandatory
			IF (v_IsMandatory = 'Y') THEN
				IF (v_DefaultValue IS NULL OR LENGTH(v_DefaultValue) = 0) THEN
					v_Result := 0;	-- failure
					v_Message := 'Mandatory requites literal default value';
			   	ELSE
					v_Cmd := v_Cmd || ' NOT NULL';
				END IF;
		  	END IF;
			--	Execute it
			IF (v_Result = 1) THEN
				EXECUTE IMMEDIATE v_Cmd;
				v_Message := '@Updated@ - ' || v_Cmd;
			END IF;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;
	END IF;

	/**
	 *	Delete Column
	 *
	ELSE
		v_Cmd := 'ALTER TABLE ' || v_TableName 
			|| ' DROP COLUMN ' || v_ColumnName;
		--	Execute it
		EXECUTE IMMEDIATE v_Cmd;
	END IF;
	/**/


	--	Table did not exist - drop initial column
	IF (v_DB_TableName IS NULL) THEN
		v_ResultStr := 'CreateDropXXColumnCommand';
		BEGIN
			v_CMD := 'ALTER TABLE ' || v_TableName || ' DROP COLUMN XXXX';
			EXECUTE IMMEDIATE v_Cmd;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;		
	END IF;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = v_Result,			-- 1=success
        ErrorMsg = v_Message
    WHERE   AD_PInstance_ID=p_PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = v_ResultStr
        WHERE   AD_PInstance_ID=p_PInstance_ID;
        COMMIT;
        RETURN;

END AD_Column_Sync;
/

CREATE OR REPLACE FUNCTION invoiceDiscount
(
	p_C_Invoice_ID		        IN NUMBER,
	p_PayDate			        IN	DATE,
	p_C_InvoicePaySchedule_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION bpartnerRemitLocation
(
	p_C_BPartner_ID	  C_BPartner.C_BPartner_ID%TYPE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE PROCEDURE AD_PrintPaper_Default
(
	p_AD_PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Set Current Format as Default
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr						VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	p_Record_ID						NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_AD_Client_ID					NUMBER := NULL;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_AD_PInstance_ID);
    v_ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=p_AD_PInstance_ID;
    COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_AD_PInstance_ID) LOOP
		p_Record_ID := p.Record_ID;
		IF (p.ParameterName = 'AD_Client_ID') THEN
 			p_AD_Client_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  AD_Client_ID=' || p_AD_Client_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || p_Record_ID);


	v_ResultStr := 'Updating';
	UPDATE	AD_PrintFormat pf
	  SET	AD_PrintPaper_ID = p_Record_ID
	WHERE	(AD_Client_ID = p_AD_Client_ID OR p_AD_Client_ID IS NULL)
	  AND EXISTS (SELECT * FROM AD_PrintPaper pp
 	  	WHERE 	pf.AD_PrintPaper_ID=pp.AD_PrintPaper_ID
		  AND	IsLandscape = (SELECT IsLandscape FROM AD_PrintPaper 
		  		WHERE AD_PrintPaper_ID=p_Record_ID));
	v_Message := '@Copied@=' || SQL%ROWCOUNT;

<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = v_Message
    WHERE   AD_PInstance_ID=p_AD_PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = v_ResultStr
        WHERE   AD_PInstance_ID=p_AD_PInstance_ID;
        COMMIT;
        RETURN;

END AD_PrintPaper_Default;
/

CREATE OR REPLACE PROCEDURE Fact_Acct_Balance_Update
(
	p_DeleteFirst		IN	VARCHAR2	DEFAULT 'N'
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:		Update ALL Balances	
 * Description:
 *	- Recreates all Balances
 ************************************************************************/
AS
BEGIN

	IF (p_DeleteFirst = 'Y') THEN
		DELETE Fact_Acct_Balance;
		DBMS_OUTPUT.PUT_LINE('  Deletes=' || SQL%ROWCOUNT);
	ELSE
		/** Update		**/
		UPDATE Fact_Acct_Balance ab
		  SET (AmtAcctDr, AmtAcctCr, Qty) =
			(SELECT COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0)
			FROM Fact_Acct a
			WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID
				AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)
				AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType
				AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)
				AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)
				AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)
				AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)
				AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) 
			GROUP BY AD_Client_ID,AD_Org_ID, 
				C_AcctSchema_ID, TRUNC(DateAcct),
				Account_ID, PostingType,
				M_Product_ID, C_BPartner_ID,
				C_Project_ID, AD_OrgTrx_ID,
				C_SalesRegion_ID, C_Activity_ID,
				C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
				User1_ID, User2_ID, GL_Budget_ID)
		WHERE EXISTS 
			(SELECT *
			FROM Fact_Acct a
			WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID
				AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)
				AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType
				AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)
				AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)
				AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)
				AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)
				AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) 
			GROUP BY AD_Client_ID,AD_Org_ID, 
				C_AcctSchema_ID, TRUNC(DateAcct),
				Account_ID, PostingType,
				M_Product_ID, C_BPartner_ID,
				C_Project_ID, AD_OrgTrx_ID,
				C_SalesRegion_ID, C_Activity_ID,
				C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
				User1_ID, User2_ID, GL_Budget_ID);
		DBMS_OUTPUT.PUT_LINE('  Updates=' || SQL%ROWCOUNT);
	END IF;


	/** Insert		**/
	INSERT INTO Fact_Acct_Balance ab
		(AD_Client_ID, AD_Org_ID, 
		C_AcctSchema_ID, DateAcct,
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID,C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID, 
		AmtAcctDr, AmtAcctCr, Qty)
	--
	SELECT AD_Client_ID, AD_Org_ID, 
		C_AcctSchema_ID, TRUNC(DateAcct),
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID,C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID, 
		COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0)
	FROM Fact_Acct a
	WHERE NOT EXISTS 
		(SELECT * 
		FROM Fact_Acct_Balance x
		WHERE a.AD_Client_ID=x.AD_Client_ID AND a.AD_Org_ID=x.AD_Org_ID
			AND a.C_AcctSchema_ID=x.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(x.DateAcct)
			AND a.Account_ID=x.Account_ID AND a.PostingType=x.PostingType
			AND COALESCE(a.M_Product_ID,0)=COALESCE(x.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(x.C_BPartner_ID,0)
			AND COALESCE(a.C_Project_ID,0)=COALESCE(x.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(x.AD_OrgTrx_ID,0)
			AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(x.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(x.C_Activity_ID,0)
			AND COALESCE(a.C_Campaign_ID,0)=COALESCE(x.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(x.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(x.C_LocFrom_ID,0)
			AND COALESCE(a.User1_ID,0)=COALESCE(x.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(x.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(x.GL_Budget_ID,0) )
	GROUP BY AD_Client_ID,AD_Org_ID, 
		C_AcctSchema_ID, TRUNC(DateAcct),
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID, C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID;
	DBMS_OUTPUT.PUT_LINE('  Inserts=' || SQL%ROWCOUNT);

	-----------------------
	COMMIT;

END Fact_Acct_Balance_Update;
/

CREATE OR REPLACE FUNCTION productAttribute
(
    p_M_AttributeSetInstance_ID     IN NUMBER
)
RETURN NVARCHAR2
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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
            COALESCE(a.LotCharSOverwrite, N'�'), COALESCE(a.LotCharEOverwrite, N'�')
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

CREATE OR REPLACE FUNCTION paymentTermDueDate
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE
)
RETURN DATE
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE FUNCTION acctBalance
(
    p_Account_ID    IN NUMBER,
    p_AmtDr         IN NUMBER,
    p_AmtCr         IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2004 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
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

CREATE OR REPLACE PROCEDURE nextID
(
	p_AD_Sequence_ID    		IN  NUMBER,
    p_System                    IN  CHAR,
    o_NextID                    OUT NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2005 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Get Next ID - no Commit
 * Description:
 *          Test via
DECLARE
    v_NextID       NUMBER;
BEGIN
    nextID(2, 'Y', v_NextID);
	DBMS_OUTPUT.PUT_LINE(v_NextID);
END;
 * 
 ************************************************************************/
AS
BEGIN
    IF (p_System = 'Y') THEN
        SELECT CurrentNextSys
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID
        FOR UPDATE OF CurrentNextSys;
        --
        UPDATE AD_Sequence
          SET CurrentNextSys = CurrentNextSys + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    ELSE
        SELECT CurrentNext
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID
        FOR UPDATE OF CurrentNext;
        --
        UPDATE AD_Sequence
          SET CurrentNext = CurrentNext + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    END IF;
    --
EXCEPTION
    WHEN  OTHERS THEN
    	DBMS_OUTPUT.PUT_LINE(SQLERRM);
END nextID;
/

/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Temporary Tables
 * Description:	
 ************************************************************************/

DROP TABLE T_Selection CASCADE CONSTRAINTS
/
--	Truely temporary table
CREATE GLOBAL TEMPORARY TABLE T_Selection	
(
	T_Selection_ID	NUMBER(10, 0) NOT NULL	
		CONSTRAINT T_Selection_Key PRIMARY KEY
)
ON COMMIT DELETE ROWS
/


DROP TABLE T_Selection2
/
--	Temporary table over commit
CREATE GLOBAL TEMPORARY TABLE T_Selection2 
(
	Query_ID	   NUMBER	  NOT NULL,
	T_Selection_ID NUMBER(10) NOT NULL,
	CONSTRAINT T_Selection2_Key PRIMARY KEY (Query_ID,T_Selection_ID)
)
ON COMMIT PRESERVE ROWS 
/


/**
 *	Spool Table
 */
DROP SEQUENCE T_Spool_Seq
/
CREATE SEQUENCE T_Spool_Seq
	INCREMENT BY 1
	START WITH	 1
/
-- INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, Msg) VALUES (123, T_Spool_Seq.NextVal, 'ggg');

DROP TABLE T_InventoryValue
/
CREATE TABLE T_InventoryValue(
    AD_PInstance_ID              NUMBER(10, 0)    NOT NULL,
    M_Warehouse_ID               NUMBER(10, 0)    NOT NULL,
    M_Product_ID                 NUMBER(10, 0)    NOT NULL,
    M_AttributeSetInstance_ID    NUMBER(10, 0)    NOT NULL,
    AD_Client_ID                 NUMBER(10, 0),
    AD_Org_ID                    NUMBER(10, 0),
    M_PriceList_Version_ID       NUMBER(10, 0),
    DateValue                    DATE,
    C_Currency_ID                NUMBER(10, 0),
    QtyOnHand                    NUMBER           DEFAULT 0,
    PricePO                      NUMBER           DEFAULT 0,
    PriceList                    NUMBER           DEFAULT 0,
    PriceStd                     NUMBER           DEFAULT 0,
    PriceLimit                   NUMBER           DEFAULT 0,
    CostStandard                 NUMBER           DEFAULT 0,
    Cost                         NUMBER           DEFAULT 0,
    PricePOAmt                   NUMBER           DEFAULT 0,
    PriceListAmt                 NUMBER           DEFAULT 0,
    PriceStdAmt                  NUMBER           DEFAULT 0,
    PriceLimitAmt                NUMBER           DEFAULT 0,
    CostStandardAmt              NUMBER           DEFAULT 0,
    CostAmt                      NUMBER           DEFAULT 0,
    M_CostElement_ID             NUMBER(10, 0),
    CONSTRAINT T_InventoryValue_Key PRIMARY KEY (AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID), 
    CONSTRAINT MPLVersion_TInventoryValue FOREIGN KEY (M_PriceList_Version_ID)
    REFERENCES M_PriceList_Version(M_PriceList_Version_ID),
    CONSTRAINT CCurrency_TInventoryValue FOREIGN KEY (C_Currency_ID)
    REFERENCES C_Currency(C_Currency_ID),
    CONSTRAINT MWarehouse_TInventoryValue FOREIGN KEY (M_Warehouse_ID)
    REFERENCES M_Warehouse(M_Warehouse_ID) ON DELETE CASCADE,
    CONSTRAINT MProduct_TInventoryValue FOREIGN KEY (M_Product_ID)
    REFERENCES M_Product(M_Product_ID) ON DELETE CASCADE,
    CONSTRAINT ADPInstance_TInventoryValue FOREIGN KEY (AD_PInstance_ID)
    REFERENCES AD_PInstance(AD_PInstance_ID) ON DELETE CASCADE,
    CONSTRAINT MCostElement_TInventoryValue FOREIGN KEY (M_CostElement_ID)
    REFERENCES M_CostElement(M_CostElement_ID),
    CONSTRAINT MASI_TInventoryValue FOREIGN KEY (M_AttributeSetInstance_ID)
    REFERENCES M_AttributeSetInstance(M_AttributeSetInstance_ID)
)
/


/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Sequences
 * Description:	
 *			(Re)Create  Sequences
 ************************************************************************/



/**
 *	Error Messages (deleteable) Primary Key
 */
TRUNCATE TABLE AD_Error
/
DROP SEQUENCE AD_Error_Seq
/
CREATE SEQUENCE AD_Error_Seq 
	START WITH 1
	INCREMENT BY 1
/


/**
 * Process Log
 */
DELETE FROM T_Report
/
DELETE FROM AD_PInstance
/
DROP SEQUENCE AD_PInstance_Seq
/
CREATE SEQUENCE AD_PInstance_Seq 
	START WITH 1
	INCREMENT BY 1
/

/**
 *	T_Spool (Global Temporary Table) Primary Key
 */
DROP SEQUENCE T_Spool_Seq
/
CREATE SEQUENCE T_Spool_Seq 
	START WITH 1
	INCREMENT BY 1
/

/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DatabaseBuild.sql,v 1.4 2006/08/11 03:20:00 jjanke Exp $
 ***
 * Title:	Views
 * Description:	
 *		These views should be generated out of ER/Studio, but due to bugs
 *		this needs to be done manually at this time.
 *      Afterwards run: PrintFormatUtil
 *
 *		Dictionary
 *			AD_Field_v / _vt
 *			AD_Tab_v / _vt
 *			AD_Window_vt
 *			AD_User_Roles_v
 *          AD_Org_v
 *
 *		Report
 *			C_Invoice_Header_v / _vt
 *			C_Invoice_LineTax_v / _vt
 *			C_Order_Header_v / _vt
 *			C_Order_LineTax_v / _vt
 *			C_PaySelection_Check_v / _vt
 *			C_PaySelection_Remittance_v / _vt
 *			M_InOut_Header_v / _vt
 *			M_InOut_Line_v / _vt
 *			C_Project_Header_v / _vt
 *			C_Project_Details_v / _vt
 *          C_RfQ_v, C_RfQLine_v, C_RfQLineQty_v /_vt
 *
 *		Other
 *			R_Request_v
 *
 *		Report Views
 *			RV_C_Invoice, RV_C_InvoiceLine, RV_C_InvoiceTax
 *			RV_C_Invoice_Day, RV_C_Invoice_Week, RV_C_Invoice_Month 
 *			RV_C_Invoice_CustomerProdQtr, RV_C_Invoice_CustomerVendQtr 
 *			RV_C_Invoice_ProdWeek, RV_C_Invoice_ProdMonth 
 *			RV_C_Invoice_VendorMonth
 *			RV_M_Transaction, RV_M_Transaction_Sum
 *			RV_OpenItem
 *			RV_Order_Open
 *			RV_Cash_Detail
 *			RV_BPartner
 *			RV_Product_Costing
 *          RV_ProjectCycle
 *          RV_Asset_Customer, RV_Asset_Delivery, RV_Asset_SumMonth
 *          RV_Storage, RV_Transaction
 *          RV_Click_Month, RV_Click_Umprocessed
 *          RV_UnPosted
 *          RV_WarehousePrice
 *          RV_Fact_Acct*
 *          RV_C_RfQ_UnAnswered, RV_C_RfQResponse
 *          RV_M_Requisition
 *          RV_InOutConfirm, RV_InOutLineConfirm
 *          RV_Allocation
 *          RV_InOutDetails
 *
 *		Utility Views
 *			C_Invoice_v, C_InvoiceLine_v
 *			C_Invoice_Candidate_v 
 *			M_InOut_Candidate_v 
 *			GL_JournalLine_Acct_v
 *			C_Payment_v
 *
 ************************************************************************/


-----   Temp Conveniance - original in createSQLJ.sql
 

/** Get Character at Position   */
CREATE OR REPLACE FUNCTION charAt
(
    p_string    VARCHAR2,
    p_pos       NUMBER
)
 	RETURN VARCHAR2
AS
BEGIN
    RETURN SUBSTR(p_string, p_pos, 1);
END;    
/
/** GetDate                     */
CREATE OR REPLACE FUNCTION getdate
 	RETURN DATE
AS
BEGIN
    RETURN SysDate;
END;    
/
/** First Of DD/DY/MM/Q         */
CREATE OR REPLACE FUNCTION firstOf
(
    p_date      DATE,
    p_datePart  VARCHAR2
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date, p_datePart);
END;    
/
/** Add Number of Days      */
CREATE OR REPLACE FUNCTION addDays
(
    p_date      DATE,
    p_days      NUMBER
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date) + p_days;
END;    
/
/** Difference in Days      */
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


-- 
-- VIEW: AD_Field_v 
--
CREATE OR REPLACE VIEW AD_Field_v 
AS
SELECT t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	f.Name, f.Description, f.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, 
    c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fg.Name AS FieldGroup, vr.Code AS ValidationCode
FROM AD_Field f 
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y'
/

-- 
-- VIEW: AD_Field_vt 
--
CREATE OR REPLACE VIEW AD_Field_vt 
AS
SELECT trl.AD_Language, t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	trl.Name, trl.Description, trl.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fgt.Name AS FieldGroup, vr.Code AS ValidationCode
FROM AD_Field f 
	INNER JOIN AD_Field_Trl trl ON (f.AD_Field_ID = trl.AD_Field_ID)
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup_Trl fgt ON 
	(f.AD_FieldGroup_ID = fgt.AD_FieldGroup_ID AND trl.AD_Language=fgt.AD_Language)
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y'
/

-- 
-- VIEW: AD_Tab_v 
--
CREATE OR REPLACE VIEW AD_Tab_v 
AS
SELECT t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, t.Name, t.Description, 
    t.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, t.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y'
/

-- 
-- VIEW: AD_Tab_vt 
--
CREATE OR REPLACE VIEW AD_Tab_vt 
AS
SELECT trl.AD_Language, t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, trl.Name, trl.Description, 
    trl.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, trl.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Tab_Trl trl ON (t.AD_Tab_ID = trl.AD_Tab_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y'
/

-- 
-- VIEW: AD_User_Roles_v 
--
CREATE OR REPLACE VIEW AD_User_Roles_v 
AS
SELECT	u.Name, r.Name AS RoleName
FROM AD_User_Roles ur
	INNER JOIN AD_User u ON (ur.AD_User_ID=u.AD_User_ID)
	INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID)
/

-- 
-- VIEW: AD_Org_v 
--
CREATE OR REPLACE VIEW AD_Org_v 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, 
    o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
    o.Value, o.Name, o.Description, o.IsSummary,
    i.C_Location_ID, i.DUNS, i.TaxID,
    i.Supervisor_ID, i.Parent_Org_ID, 
    i.AD_OrgType_ID, i.M_Warehouse_ID,
    bp.C_BPartner_ID
FROM AD_Org o
    INNER JOIN AD_OrgInfo i ON (o.AD_Org_ID=i.AD_Org_ID)
    LEFT OUTER JOIN C_BPartner bp ON (o.AD_Org_ID=bp.AD_OrgBP_ID)
/

-- 
-- VIEW: AD_Window_vt 
--
CREATE OR REPLACE VIEW AD_Window_vt 
AS 
SELECT trl.AD_Language, 
	bt.AD_Window_ID, trl.Name, trl.Description, trl.Help, bt.WindowType, 
	bt.AD_Color_ID, bt.AD_Image_ID, bt.IsActive, bt.WinWidth, bt.WinHeight,
    bt.IsSOTrx
FROM AD_Window bt
	INNER JOIN AD_Window_Trl trl ON (bt.AD_Window_ID=trl.AD_Window_ID)
WHERE bt.IsActive='Y'
/

-- 
-- VIEW: C_Invoice_Header_v 
--
CREATE OR REPLACE VIEW C_Invoice_Header_v 
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
	'en_US' AS AD_Language,
	i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus,  i.C_DocType_ID,
	i.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	i.C_Order_ID, i.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	i.DateInvoiced,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
	i.Description,
	i.POReference,
	i.DateOrdered,
	i.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines,
	i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID,
	i.IsTaxIncluded,
	i.C_Campaign_ID,
	i.C_Project_ID,
	i.C_Activity_ID,
	i.IsPaid
FROM C_Invoice i
	INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
/

-- 
-- VIEW: C_Invoice_Header_vt
--
CREATE OR REPLACE VIEW C_Invoice_Header_vt 
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
	dt.AD_Language,
	i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus,  i.C_DocType_ID,
	i.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	i.C_Order_ID, i.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	i.DateInvoiced,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
	i.Description,
	i.POReference,
	i.DateOrdered,
	i.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines,
	i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID,
	i.IsTaxIncluded,
	i.C_Campaign_ID,
	i.C_Project_ID,
	i.C_Activity_ID,
	i.IsPaid
FROM C_Invoice i
	INNER JOIN C_DocType_Trl dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
/

-- 
-- VIEW: C_Invoice_LineTax_v 
--
CREATE OR REPLACE VIEW C_Invoice_LineTax_v 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual, 
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered, 
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, 
    asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate, 
    p.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_InvoiceLine il
	INNER JOIN C_UOM uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
    uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US', il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_InvoiceLine il
WHERE il.C_UOM_ID IS NULL
UNION   --  empty line
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_Invoice
UNION   --   tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	'en_US', it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax t ON (it.C_Tax_ID=t.C_Tax_ID)
/

-- 
-- VIEW: C_Invoice_LineTax_vt
--
CREATE OR REPLACE VIEW C_Invoice_LineTax_vt 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
	CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,COALESCE(pt.Name,p.Name)||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,pt.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual, 
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered, 
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_InvoiceLine il
	INNER JOIN C_UOM_Trl uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax_Trl t ON (il.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (il.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
	uom.UOMSymbol,
	COALESCE(pt.Name,p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment line
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	l.AD_Language, il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_InvoiceLine il, AD_Language l
WHERE il.C_UOM_ID IS NULL
  AND l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  empty line
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
	AD_Language, i.C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_Invoice i, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	t.AD_Language, it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_InvoiceTax it
	INNER JOIN C_Tax_Trl t ON (it.C_Tax_ID=t.C_Tax_ID)
/

-- 
-- VIEW: C_Order_Header_v 
--
CREATE OR REPLACE VIEW C_Order_Header_v 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	'en_US' AS AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded, o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)    
/

-- 
-- VIEW: C_Order_Header_vt
--
CREATE OR REPLACE VIEW C_Order_Header_vt 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded, o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)    
/


-- 
-- VIEW: C_Order_LineTax_v 
--
CREATE OR REPLACE VIEW C_Order_LineTax_v 
AS
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN ol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual, 
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered, 
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    p.Description as ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM C_OrderLine ol
	INNER JOIN C_UOM uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax t ON (ol.C_Tax_ID=t.C_Tax_ID)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, p.Description as ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_Order
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	'en_US', ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null,
    null,null,null,null,null
FROM C_OrderTax ot
	INNER JOIN C_Tax t ON (ot.C_Tax_ID=t.C_Tax_ID)
/

-- 
-- VIEW: C_Order_LineTax_vt
--
CREATE OR REPLACE VIEW C_Order_LineTax_vt 
AS
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,pt.Name, p.Name) IS NOT NULL THEN ol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual, 
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered, 
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    pt.Description as ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM C_OrderLine ol
	INNER JOIN C_UOM_Trl uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (ol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax_Trl t ON (ol.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	COALESCE(pt.Name, p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, pt.Description AS ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
UNION
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	l.AD_Language, o.C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_Order o, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	t.AD_Language, ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null,
    null,null,null,null,null
FROM C_OrderTax ot
	INNER JOIN C_Tax_Trl t ON (ot.C_Tax_ID=t.C_Tax_ID)
/

-- 
-- VIEW: C_PaySelection_Check_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Check_v 
AS
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	'en_US' AS AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
/

-- 
-- VIEW: C_PaySelection_Check_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Check_vt 
AS
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	l.AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
    LEFT OUTER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND bpg.AD_Language=l.AD_Language)
/

-- 
-- VIEW: C_PaySelection_Remittance_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Remittance_v 
AS
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	'en_US' AS AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID)
/

-- 
-- VIEW: C_PaySelection_Remittance_vt
--
CREATE OR REPLACE VIEW C_PaySelection_Remittance_vt 
AS
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	l.AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
/

-- 
-- VIEW: M_InOut_Header_v 
--
CREATE OR REPLACE VIEW M_InOut_Header_v 
AS
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	'en_US' AS AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, 
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule
FROM M_InOut io
	INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
/

-- 
-- VIEW: M_InOut_Header_vt 
--
CREATE OR REPLACE VIEW M_InOut_Header_vt 
AS
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	dt.AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, bpc.Phone,
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule
FROM M_InOut io
	INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
/

-- 
-- VIEW: M_InOut_Line_v 
--
CREATE OR REPLACE VIEW M_InOut_Line_v 
AS
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(p.Name||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM M_InOutLine iol
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION   --  BOM lines
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	p.Name, -- main line
	b.Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
/

-- 
-- VIEW: M_InOut_Line_vt
--
CREATE OR REPLACE VIEW M_InOut_Line_vt 
AS
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name)||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name,c.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
From	M_InOutLine iol
	INNER JOIN C_UOM_Trl uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	COALESCE (pt.Name, p.Name) AS Name, -- main line
	b.Description, -- second line
	COALESCE (pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
/

-- 
-- VIEW: C_Project_Header_v 
--
CREATE OR REPLACE VIEW C_Project_Header_v 
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, 
	'en_US' AS AD_Language, p.C_Project_ID,
    p.Value, p.Name AS ProjectName, p.Description, p.Note, p.IsSummary, p.ProjectCategory,
    oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    p.C_ProjectType_ID, pjt.Name AS ProjectTypeName, p.C_Phase_ID, pjp.Name AS ProjectPhaseName,
	p.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	p.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
    p.POReference,
    p.C_Currency_ID, p.M_PriceList_Version_ID,
    p.C_Campaign_ID,
    p.PlannedAmt, p.PlannedQty, p.PlannedMarginAmt, p.InvoicedAmt, p.InvoicedQty, p.ProjectBalanceAmt,
    p.IsCommitment, p.CommittedAmt, p.CommittedQty, p.DateContract, p.DateFinish, p.IsCommitCeiling,
    p.M_Warehouse_ID
FROM C_Project p
	LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (p.AD_Org_ID=oi.AD_Org_ID)
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
/

-- 
-- VIEW: C_Project_Header_vt
--
CREATE OR REPLACE VIEW C_Project_Header_vt 
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, 
	pt.AD_Language, p.C_Project_ID,
    p.Value, p.Name AS ProjectName, p.Description, p.Note, p.IsSummary, p.ProjectCategory,
    oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    p.C_ProjectType_ID, pjt.Name AS ProjectTypeName, p.C_Phase_ID, pjp.Name AS ProjectPhaseName,
	p.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	p.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
    p.POReference,
    p.C_Currency_ID, p.M_PriceList_Version_ID,
    p.C_Campaign_ID,
    p.PlannedAmt, p.PlannedQty, p.PlannedMarginAmt, p.InvoicedAmt, p.InvoicedQty, p.ProjectBalanceAmt,
    p.IsCommitment, p.CommittedAmt, p.CommittedQty, p.DateContract, p.DateFinish, p.IsCommitCeiling,
    p.M_Warehouse_ID
FROM C_Project p
	LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (p.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN C_PaymentTerm_Trl pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
/

-- 
-- VIEW: C_Project_Details_v 
--
CREATE OR REPLACE VIEW C_Project_Details_v 
AS
SELECT pl.AD_Client_ID, pl.AD_Org_ID, pl.IsActive, pl.Created, pl.CreatedBy, pl.Updated, pl.UpdatedBy,
	'en_US' AS AD_Language,
    pj.C_Project_ID, pl.C_ProjectLine_ID,
    pl.Line, 
    pl.PlannedQty, pl.PlannedPrice, pl.PlannedAmt, pl.PlannedMarginAmt,
    pl.CommittedAmt,
    pl.M_Product_ID,
	COALESCE(p.Name, pl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN pl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    pl.M_Product_Category_ID,
    pl.InvoicedAmt, pl.InvoicedQty, pl.CommittedQty
FROM C_ProjectLine pl
  INNER JOIN C_Project pj ON (pl.C_Project_ID=pj.C_Project_ID)
  LEFT OUTER JOIN M_Product p ON (pl.M_Product_ID=p.M_Product_ID)
WHERE pl.IsPrinted='Y'
/

-- 
-- VIEW: C_Project_Details_vt
--
CREATE OR REPLACE VIEW C_Project_Details_vt 
AS
SELECT pl.AD_Client_ID, pl.AD_Org_ID, pl.IsActive, pl.Created, pl.CreatedBy, pl.Updated, pl.UpdatedBy,
	l.AD_Language,
    pj.C_Project_ID, pl.C_ProjectLine_ID,
    pl.Line, 
    pl.PlannedQty, pl.PlannedPrice, pl.PlannedAmt, pl.PlannedMarginAmt,
    pl.CommittedAmt,
    pl.M_Product_ID,
	COALESCE(p.Name, pl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN pl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    pl.M_Product_Category_ID,
    pl.InvoicedAmt, pl.InvoicedQty, pl.CommittedQty
FROM C_ProjectLine pl
  INNER JOIN C_Project pj ON (pl.C_Project_ID=pj.C_Project_ID)
  LEFT OUTER JOIN M_Product p ON (pl.M_Product_ID=p.M_Product_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE pl.IsPrinted='Y'  
/

--
--  VIEW: RfQ Response Header
--
CREATE OR REPLACE VIEW C_RfQResponse_v
AS
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	'en_US' AS AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID)
/

--
--  VIEW: RfQ Response Header (trl)
--
CREATE OR REPLACE VIEW C_RfQResponse_vt
AS
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	l.AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
/

--
--  VIEW: RfQ Response Line
--
CREATE OR REPLACE VIEW C_RfQResponseLine_v
AS
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	'en_US' AS AD_Language,
    rl.Line,
    rl.M_Product_ID, rl.M_AttributeSetInstance_ID,
	COALESCE(p.Name||productAttribute(rl.M_AttributeSetInstance_ID), rl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN rl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    rl.Help,
    rl.DateWorkStart, rl.DeliveryDays,
    q.C_UOM_ID, uom.UOMSymbol, q.BenchmarkPrice,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN C_RfQResponseLine rrl ON (rq.C_RfQResponseLine_ID=rrl.C_RfQResponseLine_ID)
  INNER JOIN C_RfQLine rl ON (rrl.C_RfQLine_ID=rl.C_RfQLine_ID)
  LEFT OUTER JOIN M_Product p ON (rl.M_Product_ID=p.M_Product_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y'
/

--
--  VIEW: RfQ Response Line (trl)
--
CREATE OR REPLACE VIEW C_RfQResponseLine_vt
AS
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	l.AD_Language,
    rl.Line,
    rl.M_Product_ID, rl.M_AttributeSetInstance_ID,
	COALESCE(p.Name||productAttribute(rl.M_AttributeSetInstance_ID), rl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN rl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    rl.Help,
    rl.DateWorkStart, rl.DeliveryDays,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN C_RfQResponseLine rrl ON (rq.C_RfQResponseLine_ID=rrl.C_RfQResponseLine_ID)
  INNER JOIN C_RfQLine rl ON (rrl.C_RfQLine_ID=rl.C_RfQLine_ID)
  LEFT OUTER JOIN M_Product p ON (rl.M_Product_ID=p.M_Product_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y'
/

--
--  VIEW: RfQ Reponse Line Qty
--
CREATE OR REPLACE VIEW C_RfQResponseLineQty_v
AS
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	'en_US' AS AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
/

--
--  VIEW: RfQ Reponse Line Qty (trl)
--
CREATE OR REPLACE VIEW C_RfQResponseLineQty_vt
AS
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	l.AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y'
/


-- 
-- VIEW: M_InOutConfirm_v 
--
CREATE OR REPLACE VIEW M_InOutConfirm_v 
AS
SELECT ioc.AD_Client_ID, ioc.AD_Org_ID, ioc.IsActive, ioc.Created, ioc.CreatedBy, ioc.Updated, ioc.UpdatedBy,
  'en_US' AS AD_Language,
  ioc.M_InOutConfirm_ID,
  ioc.DocumentNo, ioc.ConfirmType,
  ioc.IsApproved, ioc.IsCancelled, ioc.Description,
  --
  io.M_InOut_ID, io.Description AS ShipDescription,
  io.C_BPartner_ID, io.C_BPartner_Location_ID, io.AD_User_ID,
  io.SalesRep_ID, io.C_DocType_ID, dt.PrintName AS DocumentType,
  io.C_Order_ID, io.DateOrdered, io.MovementDate, io.MovementType, 
  io.M_Warehouse_ID, io.POReference,
  io.DeliveryRule, io.FreightCostRule,
  io.DeliveryViaRule, io.M_Shipper_ID, PriorityRule,
  ioc.Processed
FROM M_InOutConfirm ioc
  INNER JOIN M_InOut io ON (ioc.M_InOut_ID=io.M_InOut_ID)
  INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
/

-- 
-- VIEW: M_InOutConfirm_vt
--
CREATE OR REPLACE VIEW M_InOutConfirm_vt 
AS
SELECT ioc.AD_Client_ID, ioc.AD_Org_ID, ioc.IsActive, ioc.Created, ioc.CreatedBy, ioc.Updated, ioc.UpdatedBy,
  dt.AD_Language,
  ioc.M_InOutConfirm_ID,
  ioc.DocumentNo, ioc.ConfirmType,
  ioc.IsApproved, ioc.IsCancelled, ioc.Description,
  --
  io.M_InOut_ID, io.Description AS ShipDescription,
  io.C_BPartner_ID, io.C_BPartner_Location_ID, io.AD_User_ID,
  io.SalesRep_ID, io.C_DocType_ID, dt.PrintName AS DocumentType,
  io.C_Order_ID, io.DateOrdered, io.MovementDate, io.MovementType, 
  io.M_Warehouse_ID, io.POReference,
  io.DeliveryRule, io.FreightCostRule,
  io.DeliveryViaRule, io.M_Shipper_ID, PriorityRule,
  ioc.Processed
FROM M_InOutConfirm ioc
  INNER JOIN M_InOut io ON (ioc.M_InOut_ID=io.M_InOut_ID)
  INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
/

-- 
-- VIEW: M_InOut_LineConfirm_v 
--
CREATE OR REPLACE VIEW M_InOut_LineConfirm_v 
AS
SELECT iolc.AD_Client_ID, iolc.AD_Org_ID, iolc.IsActive, iolc.Created, iolc.CreatedBy, iolc.Updated, iolc.UpdatedBy,
	'en_US' AS AD_Language,
    iolc.M_InOutLineConfirm_ID, iolc.M_InOutConfirm_ID,
    iolc.TargetQty, iolc.ConfirmedQty, iolc.DifferenceQty, iolc.ScrappedQty,
    iolc.Description, iolc.Processed,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	iol.MovementQty, uom.UOMSymbol, ol.QtyOrdered-ol.QtyDelivered AS QtyBackOrdered,
	COALESCE(p.Name, iol.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN iol.Description END AS ShipDescription, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate
FROM M_InOutLineConfirm iolc
    INNER JOIN M_InOutLine iol ON (iolc.M_InOutLine_ID=iol.M_InOutLine_ID)
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
/

-- 
-- VIEW: M_InOut_LineConfirm_vt
--
CREATE OR REPLACE VIEW M_InOut_LineConfirm_vt 
AS
SELECT iolc.AD_Client_ID, iolc.AD_Org_ID, iolc.IsActive, iolc.Created, iolc.CreatedBy, iolc.Updated, iolc.UpdatedBy,
	uom.AD_Language,
    iolc.M_InOutLineConfirm_ID, iolc.M_InOutConfirm_ID,
    iolc.TargetQty, iolc.ConfirmedQty, iolc.DifferenceQty, iolc.ScrappedQty,
    iolc.Description, iolc.Processed,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	iol.MovementQty, uom.UOMSymbol, ol.QtyOrdered-ol.QtyDelivered AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name), iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name) IS NOT NULL THEN iol.Description END AS ShipDescription, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate
FROM M_InOutLineConfirm iolc
    INNER JOIN M_InOutLine iol ON (iolc.M_InOutLine_ID=iol.M_InOutLine_ID)
	INNER JOIN C_UOM_Trl uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
/

-- 
-- VIEW: C_Dunning_Header_v 
--
CREATE OR REPLACE VIEW C_Dunning_Header_v 
AS
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	'en_US' AS AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dl.PrintName, dl.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
    dre.Amt, dre.Qty, dre.Note
FROM C_DunningRun dr
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
/

CREATE OR REPLACE VIEW C_Dunning_Header_vt
AS
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	dlt.AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dlt.PrintName, dlt.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
    dre.Amt, dre.Qty, dre.Note
FROM C_DunningRun dr
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningLevel_Trl dlt ON (dl.C_DunningLevel_ID=dlt.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID
        AND dlt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID
        AND dlt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
/

CREATE OR REPLACE VIEW C_Dunning_Line_v
AS
SELECT drl.AD_Client_ID, drl.AD_Org_ID, drl.IsActive, drl.Created, drl.CreatedBy, drl.Updated, drl.UpdatedBy, 
	'en_US' AS AD_Language,
    drl.C_DunningRunLine_ID, drl.C_DunningRunEntry_ID,
    drl.Amt, drl.ConvertedAmt, drl.DaysDue, drl.TimesDunned, 
    drl.InterestAmt, drl.FeeAmt, drl.TotalAmt,
	drl.C_Invoice_ID, 
    COALESCE(i.IsSOTrx,p.IsReceipt) AS IsSOTrx,
    COALESCE(i.DocumentNo,p.DocumentNo) AS DocumentNo,
    COALESCE(i.DocStatus,p.DocStatus) AS DocStatus, 
	COALESCE(i.DateInvoiced, p.DateTrx) AS DateTrx,
    COALESCE(i.C_DocType_ID,p.C_DocType_ID) AS C_DocType_ID,
    COALESCE(dt.PrintName,dtp.PrintName) AS DocumentType, 
    COALESCE(i.Description,p.Description) AS Description, 
	COALESCE(i.C_Currency_ID,p.C_Currency_ID) AS C_Currency_ID, 
    COALESCE(i.C_Campaign_ID,p.C_Campaign_ID) AS C_Campaign_ID, 
    COALESCE(i.C_Project_ID,p.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,p.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.User1_ID,p.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID,p.User2_ID) AS User2_ID,
    COALESCE(i.DateAcct,p.DateAcct) AS DateAcct,
    COALESCE(i.C_ConversionType_ID,i.C_ConversionType_ID) AS C_ConversionType_ID,
    COALESCE(i.AD_OrgTrx_ID,p.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    i.POReference, i.DateOrdered,
	i.DateInvoiced, i.IsInDispute,
	pt.Name AS PaymentTerm,
    i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines, i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID, i.IsPaid,
    p.IsAllocated, p.TenderType, p.DiscountAmt
FROM C_DunningRunLine drl
    LEFT OUTER JOIN C_Invoice i ON (drl.C_Invoice_ID=i.C_Invoice_ID)
	LEFT OUTER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_Payment p ON (drl.C_Payment_ID=p.C_Payment_ID)
	LEFT OUTER JOIN C_DocType dtp ON (p.C_DocType_ID=dtp.C_DocType_ID)
/

CREATE OR REPLACE VIEW C_Dunning_Line_vt
AS
SELECT drl.AD_Client_ID, drl.AD_Org_ID, drl.IsActive, drl.Created, drl.CreatedBy, drl.Updated, drl.UpdatedBy, 
	COALESCE(dt.AD_Language,dtp.AD_Language) AS AD_Language,
    drl.C_DunningRunLine_ID, drl.C_DunningRunEntry_ID,
    drl.Amt, drl.ConvertedAmt, drl.DaysDue, drl.TimesDunned, 
    drl.InterestAmt, drl.FeeAmt, drl.TotalAmt,
	drl.C_Invoice_ID, 
    COALESCE(i.IsSOTrx,p.IsReceipt) AS IsSOTrx,
    COALESCE(i.DocumentNo,p.DocumentNo) AS DocumentNo,
    COALESCE(i.DocStatus,p.DocStatus) AS DocStatus, 
	COALESCE(i.DateInvoiced, p.DateTrx) AS DateTrx,
    COALESCE(i.C_DocType_ID,p.C_DocType_ID) AS C_DocType_ID,
    COALESCE(dt.PrintName,dtp.PrintName) AS DocumentType, 
    COALESCE(i.Description,p.Description) AS Description, 
	COALESCE(i.C_Currency_ID,p.C_Currency_ID) AS C_Currency_ID, 
    COALESCE(i.C_Campaign_ID,p.C_Campaign_ID) AS C_Campaign_ID, 
    COALESCE(i.C_Project_ID,p.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,p.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.User1_ID,p.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID,p.User2_ID) AS User2_ID,
    COALESCE(i.DateAcct,p.DateAcct) AS DateAcct,
    COALESCE(i.C_ConversionType_ID,i.C_ConversionType_ID) AS C_ConversionType_ID,
    COALESCE(i.AD_OrgTrx_ID,p.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    i.POReference, i.DateOrdered,
	i.DateInvoiced, i.IsInDispute,
	pt.Name AS PaymentTerm,
    i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines, i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID, i.IsPaid,
    p.IsAllocated, p.TenderType, p.DiscountAmt
FROM C_DunningRunLine drl
    LEFT OUTER JOIN C_Invoice i ON (drl.C_Invoice_ID=i.C_Invoice_ID)
	LEFT OUTER JOIN C_DocType_Trl dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	LEFT OUTER JOIN C_PaymentTerm_Trl pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID
        AND pt.AD_Language=dt.AD_Language)
    LEFT OUTER JOIN C_Payment p ON (drl.C_Payment_ID=p.C_Payment_ID)
	LEFT OUTER JOIN C_DocType_Trl dtp ON (p.C_DocType_ID=dtp.C_DocType_ID) 
WHERE COALESCE(dt.AD_Language,dtp.AD_Language)=COALESCE(dtp.AD_Language,dt.AD_Language)
/

-- 
-- VIEW: R_Request_v 
--
CREATE OR REPLACE VIEW R_Request_v 
AS
SELECT *
FROM R_Request
WHERE IsActive='Y' AND Processed='N'
  AND getdate() > DateNextAction
/


-------------------------------------------------------------------------------

--	Invoice corrected for Credit Memo

CREATE OR REPLACE VIEW RV_C_Invoice
AS
SELECT i.C_Invoice_ID, 
	i.AD_Client_ID,i.AD_Org_ID,i.IsActive,i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
	i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, 
	i.IsPrinted, i.IsDiscountPrinted, i.Processing, i.Processed, i.IsTransferred, i.IsPaid,
	i.C_DocType_ID, i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, 
	i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct,
	i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, b.C_BP_Group_ID,
	i.POReference, i.DateOrdered, i.C_Currency_ID, C_ConversionType_ID, i.PaymentRule, i.C_PaymentTerm_ID,
	i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.IsPayScheduleValid,
    loc.C_Country_ID, loc.C_Region_ID, loc.Postal, loc.City,
	--	Amounts
	i.C_Charge_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM  C_Invoice i
 INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
 INNER JOIN C_BPartner b ON (i.C_BPartner_ID=b.C_BPartner_ID)
 INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
 INNER JOIN C_Location loc ON (bpl.C_Location_ID=loc.C_Location_ID)
/

--	Invoice Lines corrected for Credit Memo

CREATE OR REPLACE VIEW RV_C_InvoiceLine 
AS SELECT 
	il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID,
	i.C_BPartner_ID, i.C_BP_Group_ID,
	il.M_Product_ID, p.M_Product_Category_ID,
	i.DateInvoiced, i.DateAcct, i.IsSOTrx, i.C_DocType_ID, i.DocStatus, i.IsPaid,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID,
	--	Qty
	il.QtyInvoiced*i.Multiplier AS QtyInvoiced,
	il.QtyEntered*i.Multiplier AS QtyEntered,
    -- Attributes
    il.M_AttributeSetInstance_ID, productAttribute(il.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	--	Item Amounts
	il.PriceList, il.PriceActual, il.PriceLimit, il.PriceEntered,
	CASE WHEN PriceList=0 THEN 0 ELSE
	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  (PriceActual-PriceLimit)*QtyInvoiced END AS MarginAmt,
	--	Line Amounts
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE
		ROUND(i.Multiplier*PriceLimit*QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced-LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE
		ROUND(i.Multiplier*LineNetAmt-PriceLimit*QtyInvoiced,2) END AS LineOverLimitAmt
FROM  RV_C_Invoice i
  INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID)
  LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (il.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
/

--  Invoice Tax adjusted for CM

CREATE OR REPLACE VIEW RV_C_InvoiceTax
AS
SELECT 
    i.AD_Client_ID, i.AD_Org_ID, i.IsActive, t.Created, t.CreatedBy, t.Updated, t.UpdatedBy,
    t.C_Tax_ID, i.C_Invoice_ID, i.C_DocType_ID,
    i.C_BPartner_ID, bp.TaxID, bp.IsTaxExempt, 
    i.DateAcct, i.DateInvoiced, i.IsSOTrx, i.DocumentNo, i.IsPaid, i.C_Currency_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxBaseAmt*-1 ELSE t.TaxBaseAmt END AS TaxBaseAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxAmt*-1 ELSE t.TaxAmt END AS TaxAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN (t.TaxBaseAmt + t.TaxAmt)*-1 ELSE (t.TaxBaseAmt + t.TaxAmt) END AS TaxLineTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM C_InvoiceTax t
  INNER JOIN C_Invoice i ON (t.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
  INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
/
  
--	Invoice By Day

CREATE OR REPLACE VIEW RV_C_Invoice_Day 
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DD') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DD'), IsSOTrx
/

--	Invoice By Week

CREATE OR REPLACE VIEW RV_C_Invoice_Week 
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY'), IsSOTrx
/

--	Invoice By Month

CREATE OR REPLACE VIEW RV_C_Invoice_Month
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'MM') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'MM'), IsSOTrx
/

--	Invoice By Customer and Quarter

CREATE OR REPLACE VIEW RV_C_Invoice_CustomerProdQtr 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	il.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'Q'), IsSOTrx
/

--	Invoice By Vendor and Quarter

CREATE OR REPLACE VIEW RV_C_Invoice_CustomerVendQtr 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	il.C_BPartner_ID, po.C_BPartner_ID AS Vendor_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced
FROM RV_C_InvoiceLine il
  INNER JOIN M_Product_PO po ON (il.M_Product_ID=po.M_Product_ID)
WHERE il.IsSOTrx='Y'
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.C_BPartner_ID, po.C_BPartner_ID,
	firstOf(il.DateInvoiced, 'Q')
/

--	Invoice By Product Category and Week

CREATE OR REPLACE VIEW RV_C_Invoice_ProdWeek 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'DY') AS DateInvoiced,
	SUM(il.LineNetAmt) AS LineNetAmt,
	SUM(il.LineListAmt) AS LineListAmt,
	SUM(il.LineLimitAmt) AS LineLimitAmt,
	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'DY'), IsSOTrx
/

--	Invoice By Product Category and Month

CREATE OR REPLACE VIEW RV_C_Invoice_ProdMonth 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM') AS DateInvoiced,
	SUM(il.LineNetAmt) AS LineNetAmt,
	SUM(il.LineListAmt) AS LineListAmt,
	SUM(il.LineLimitAmt) AS LineLimitAmt,
	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM'), IsSOTrx
/


--	Invoice By Product and Month

CREATE OR REPLACE VIEW RV_C_Invoice_ProductMonth 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'MM') AS DateInvoiced,
	SUM(il.LineNetAmt) AS LineNetAmt,
	SUM(il.LineListAmt) AS LineListAmt,
	SUM(il.LineLimitAmt) AS LineLimitAmt,
	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'MM'), IsSOTrx
/

--	Invoice By Product and Quarter

CREATE OR REPLACE VIEW RV_C_Invoice_ProductQtr
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,
	SUM(il.LineNetAmt) AS LineNetAmt,
	SUM(il.LineListAmt) AS LineListAmt,
	SUM(il.LineLimitAmt) AS LineLimitAmt,
	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'Q'), IsSOTrx
/

--	Invoice By Vendor and Month

CREATE OR REPLACE VIEW RV_C_Invoice_VendorMonth 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	po.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced
FROM RV_C_InvoiceLine il
    INNER JOIN M_Product_PO po ON (il.M_Product_ID=po.M_Product_ID)
WHERE il.IsSOTrx='Y'
GROUP BY il.AD_Client_ID, il.AD_Org_ID, po.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM')
/

--	Product Transactions

CREATE OR REPLACE VIEW RV_M_Transaction_Sum
AS
SELECT t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate,
	SUM(t.MovementQty) AS MovementQty
FROM M_Transaction t, M_Locator l
WHERE t.M_Locator_ID=l.M_Locator_ID
GROUP BY t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate
/

--	Product Transactions Detail

CREATE OR REPLACE VIEW RV_M_Transaction 
AS
SELECT t.AD_Client_ID,t.AD_Org_ID, t.MovementDate, t.MovementQty, 
	t.M_Product_ID, t.M_Locator_ID, t.M_AttributeSetInstance_ID,
	p.M_Product_Category_ID, p.Value, 
	po.C_BPartner_ID, po.PricePO, po.PriceLastPO, po.PriceList
FROM M_Transaction t
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_Product_PO po ON (t.M_Product_ID=po.M_Product_ID)
WHERE po.IsCurrentVendor='Y'
/

--	Business Partners

CREATE OR REPLACE VIEW RV_BPartner
AS
SELECT bp.AD_Client_ID, bp.AD_Org_ID, 
	bp.IsActive, bp.Created, bp.CreatedBy, bp.Updated, bp.UpdatedBy,
    bp.C_BPartner_ID, bp.Value, bp.Name, bp.Name2, bp.Description, bp.IsSummary,
    bp.C_BP_Group_ID, bp.IsOneTime, bp.IsProspect, bp.IsVendor, bp.IsCustomer, bp.IsEmployee, bp.IsSalesRep,
    bp.ReferenceNo, bp.Duns, bp.URL, bp.AD_Language, bp.TaxID, bp.IsTaxExempt, 
    bp.C_InvoiceSchedule_ID, bp.Rating, bp.SalesVolume, bp.NumberEmployees, bp.NAICS,
    bp.FirstSale, bp.AcqusitionCost, bp.PotentialLifeTimeValue, bp.ActualLifeTimeValue,
    bp.ShareOfCustomer, bp.PaymentRule, 
    bp.SO_CreditLimit, bp.SO_CreditUsed, bp.SO_CreditUsed-bp.SO_CreditLimit AS SO_CreditAvailable,
    bp.C_PaymentTerm_ID, bp.M_PriceList_ID, bp.M_DiscountSchema_ID, bp.C_Dunning_ID,
    bp.IsDiscountPrinted, bp.SO_Description, bp.POReference, PaymentRulePO,
    bp.PO_PriceList_ID, bp.PO_DiscountSchema_ID, bp.PO_PaymentTerm_ID,
    bp.DocumentCopies, bp.C_Greeting_ID, bp.InvoiceRule, bp.DeliveryRule,
    bp.FreightCostRule, bp.DeliveryViaRule, bp.SalesRep_ID,
    bp.SendEMail, bp.BPartner_Parent_ID, bp.Invoice_PrintFormat_ID,
    bp.SOCreditStatus, bp.ShelfLifeMinPct, bp.AD_OrgBP_ID,
    bp.FlatDiscount, bp.TotalOpenBalance,
    -- Contact
    c.AD_User_ID, c.Name AS ContactName, c.Description AS ContactDescription,
    c.EMail, c.Supervisor_ID, 
    c.EMailUser, c.C_Greeting_ID AS BPContactGreeting,
    c.Title, c.Comments, c.Phone, c.Phone2, c.Fax,
    c.LastContact, c.LastResult, c.BirthDay, c.AD_OrgTrx_ID,
    c.EMailVerify, c.LDAPUser, c.EMailVerifyDate, c.NotificationType,
    -- Location
	l.C_BPartner_Location_ID, a.Postal, a.City, a.Address1, a.Address2, a.Address3, 
    a.C_Region_ID, COALESCE(r.Name,a.RegionName) AS RegionName,
    a.C_Country_ID, cc.Name AS CountryName
FROM C_BPartner bp
 LEFT OUTER JOIN C_BPartner_Location l ON (bp.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')
 LEFT OUTER JOIN AD_User c ON (bp.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y')
 LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
 LEFT OUTER JOIN C_Region r ON (a.C_Region_ID=r.C_Region_ID)
 INNER JOIN C_Country cc ON (a.C_Country_ID=cc.C_Country_ID)
/

--	Open Items

CREATE OR REPLACE VIEW RV_OpenItem 
AS
SELECT i.AD_Org_ID, i.AD_Client_ID, 
	i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, 
    p.NetDays, 
	paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DueDate,
	paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, getdate()) AS DaysDue,
    addDays(i.DateInvoiced,p.DiscountDays) AS DiscountDate, 
    ROUND(i.GrandTotal*p.Discount/100,2) AS DiscountAmt,
	i.GrandTotal, 
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID,0) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID, 
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)
WHERE --    i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,0) <> 0
    AND i.IsPayScheduleValid<>'Y'
    AND i.DocStatus<>'DR'
UNION
SELECT i.AD_Org_ID, i.AD_Client_ID, 
    i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced,
    daysBetween(ips.DueDate,i.DateInvoiced) AS NetDays,
    ips.DueDate,
    daysBetween(getdate(),ips.DueDate) AS DaysDue,
    ips.DiscountDate, 
    ips.DiscountAmt,
	ips.DueAmt AS GrandTotal, 
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID, ips.C_InvoicePaySchedule_ID) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID, 
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE  --   i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,ips.C_InvoicePaySchedule_ID) <> 0
    AND i.IsPayScheduleValid='Y'
    AND i.DocStatus<>'DR'
    AND ips.IsValid='Y'
/

--	Order Detail

CREATE OR REPLACE VIEW RV_OrderDetail
AS
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, o.IsCreditApproved,
	o.SalesRep_ID, 
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID, o.IsDropShip,
    l.C_BPartner_ID, l.C_BPartner_Location_ID, o.AD_User_ID,
	o.POReference, o.C_Currency_ID, o.IsSOTrx,
    l.C_Campaign_ID, l.C_Project_ID, l.C_Activity_ID, l.C_ProjectPhase_ID, l.C_ProjectTask_ID,
	l.C_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Warehouse_ID,
    l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.QtyInvoiced, 
    l.PriceActual, l.PriceEntered,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.QtyOrdered-l.QtyInvoiced AS QtyToInvoice,
	(l.QtyOrdered-l.QtyInvoiced)*l.PriceActual AS NetAmtToInvoice,
    l.QtyLostSales, l.QtyLostSales*l.PriceActual AS AmtLostSales,
	CASE WHEN PriceList=0 THEN 0 ELSE
	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  (PriceActual-PriceLimit)*QtyDelivered END AS MarginAmt
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
/

--	Cash Journal Detail

CREATE OR REPLACE VIEW RV_Cash_Detail
AS
SELECT cl.C_Cash_ID, cl.C_CashLine_ID,
	c.AD_Client_ID, c.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
	c.C_CashBook_ID, c.Name, c.StatementDate, c.DateAcct, c.Processed, c.Posted,
	cl.Line, cl.Description, cl.CashType, cl.C_Currency_ID, cl.Amount, 
	currencyConvert(cl.Amount,cl.C_Currency_ID,cb.C_Currency_ID,c.StatementDate,0, c.AD_Client_ID, c.AD_Org_ID) AS ConvertedAmt,
	cl.C_BankAccount_ID, cl.C_Invoice_ID, cl.C_Charge_ID
FROM C_Cash c
 INNER JOIN C_CashLine cl ON (c.C_Cash_ID=cl.C_Cash_ID)
 INNER JOIN C_CashBook cb ON (c.C_CashBook_ID=cb.C_CashBook_ID)
/

CREATE OR REPLACE VIEW RV_Product_Costing
AS
SELECT	pc.M_Product_ID, pc.C_AcctSchema_ID, p.Value, p.Name, p.M_Product_Category_ID,
	pc.AD_Client_ID, pc.AD_Org_ID, pc.IsActive, pc.Created,pc.CreatedBy,pc.Updated,pc.UpdatedBy,
	pc.CurrentCostPrice,
	--	Standard Costing
	pc.FutureCostPrice, pc.CostStandard, 
	pc.CostStandardPOQty, pc.CostStandardPOAmt, 
	CASE WHEN pc.CostStandardPOQty=0 THEN 0 ELSE pc.CostStandardPOAmt/pc.CostStandardPOQty END AS CostStandardPODiff,
	pc.CostStandardCumQty, pc.CostStandardCumAmt,
	CASE WHEN pc.CostStandardCumQty=0 THEN 0 ELSE pc.CostStandardCumAmt/pc.CostStandardCumQty END AS CostStandardInvDiff,
	--	Average Costing
	pc.CostAverage,
	pc.CostAverageCumQty, pc.CostAverageCumAmt,
	pc.TotalInvQty, pc.TotalInvAmt,
	CASE WHEN pc.TotalInvQty=0 THEN 0 ELSE pc.TotalInvAmt/pc.TotalInvQty END AS TotalInvCost,
	--	LastPrice
	pc.PriceLastPO, pc.PriceLastInv
FROM M_Product_Costing pc
  INNER JOIN M_Product p ON (pc.M_Product_ID=p.M_Product_ID)
/

CREATE OR REPLACE VIEW RV_CostSummary
AS
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 0 AS CreatedBy,SysDate AS Created,0 AS UpdatedBy,SysDate AS Updated,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    SUM(c.CurrentCostPrice) AS CurrentCostPrice, SUM(c.FutureCostPrice) AS FutureCostPrice
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
WHERE acct.M_CostType_ID=c.M_CostType_ID
GROUP BY c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, acct.C_AcctSchema_ID, acct.C_Currency_ID
/

CREATE OR REPLACE VIEW RV_Cost
AS
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    ce.M_CostElement_ID, ce.CostElementType, ce.CostingMethod, ce.IsCalculated, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.CurrentCostPrice, c.FutureCostPrice, c.Description
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
/

CREATE OR REPLACE VIEW RV_CostDetail
AS
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_InOutLine_ID, c.C_InvoiceLine_ID,
    asi.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.Lot, asi.SerNo,
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.Amt, c.Qty, c.Description, Processed
FROM M_CostDetail c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
  INNER JOIN M_AttributeSetInstance asi ON (c.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
/


CREATE OR REPLACE VIEW RV_ProjectCycle
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy, p.Updated,p.UpdatedBy,
    c.C_Cycle_ID, c.Name AS CycleName, c.C_Currency_ID,
    cs.C_CycleStep_ID, cs.Name AS CycleStepName, cs.SeqNo, cs.RelativeWeight,
    pp.C_Phase_ID, pp.Name AS ProjectPhaseName, 
    pt.C_ProjectType_ID, pt.Name AS ProjectTypeName,
    p.Value AS ProjectValue, p.Name AS ProjectName, p.Description, p.Note, 
    p.C_BPartner_ID, p.C_BPartner_Location_ID, p.AD_User_ID, p.POReference,
    p.SalesRep_ID, p.M_Warehouse_ID, p.ProjectCategory,
    p.DateContract, p.DateFinish,
    p.IsCommitment, p.IsCommitCeiling, 
    p.CommittedQty*cs.RelativeWeight AS CommittedQty,
    currencyConvert (p.CommittedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS CommittedAmt,
    p.PlannedQty*cs.RelativeWeight AS PlannedQty, 
    currencyConvert (p.PlannedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedAmt, 
    currencyConvert (p.PlannedMarginAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedMarginAmt,
    currencyConvert (p.InvoicedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS InvoicedAmt, 
    p.InvoicedQty*cs.RelativeWeight AS InvoicedQty,
    currencyConvert (p.ProjectBalanceAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS ProjectBalanceAmt
FROM C_Cycle c
  INNER JOIN C_CycleStep cs ON (c.C_Cycle_ID=cs.C_Cycle_ID)
  INNER JOIN C_CyclePhase cp ON (cs.C_CycleStep_ID=cp.C_CycleStep_ID)
  INNER JOIN C_Phase pp ON (cp.C_Phase_ID=pp.C_Phase_ID)
  INNER JOIN C_Project p ON (cp.C_Phase_ID=p.C_Phase_ID)
  INNER JOIN C_ProjectType pt ON (p.C_ProjectType_ID=pt.C_ProjectType_ID)
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_Asset_Customer
AS
SELECT A_Asset_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
  Value, Name, Description, Help, A_Asset_Group_ID, M_Product_ID, SerNo, Lot, VersionNo,
  GuaranteeDate, AssetServiceDate,
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  (SELECT COUNT(*) FROM A_Asset_Delivery ad WHERE a.A_Asset_ID=ad.A_Asset_ID) AS DeliveryCount
FROM A_Asset a
WHERE C_BPartner_ID IS NOT NULL
/

CREATE OR REPLACE VIEW RV_Asset_Delivery
AS
SELECT ad.A_Asset_Delivery_ID, ad.AD_Client_ID, ad.AD_Org_ID, ad.IsActive, ad.Created, ad.CreatedBy, ad.Updated, ad.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, ad.AD_User_ID,
  ad.MovementDate, ad.SerNo, ad.Lot, ad.VersionNo,
  ad.M_InOutLine_ID, 
  ad.Email, ad.MessageID, ad.DeliveryConfirmation,
  ad.URL, ad.Remote_Addr, ad.Remote_Host, ad.Referrer,
  ad.Description
FROM A_Asset_Delivery ad
 INNER JOIN A_Asset a ON (a.A_Asset_ID=ad.A_Asset_ID)
/

CREATE OR REPLACE VIEW RV_Asset_SumMonth
AS
SELECT a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM') AS MovementDate,
  COUNT(*) AS DeliveryCount
FROM A_Asset a
 INNER JOIN A_Asset_Delivery ad ON (a.A_Asset_ID=ad.A_Asset_ID)
GROUP BY a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM')
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_ProjectLineIssue
AS
SELECT COALESCE(l.AD_Client_ID,i.AD_Client_ID) AS AD_Client_ID,COALESCE(l.AD_Org_ID,i.AD_Org_ID) AS AD_Org_ID,
    COALESCE(l.IsActive,i.IsActive) AS IsActive,
    COALESCE(l.Created,i.Created) AS Created,COALESCE(l.CreatedBy,i.CreatedBy) AS CreatedBy,
    COALESCE(l.Updated,i.Updated) AS Updated,COALESCE(l.UpdatedBy,i.UpdatedBy) AS UpdatedBy,
    COALESCE(l.C_Project_ID,i.C_Project_ID) AS C_Project_ID,
    COALESCE(l.M_Product_ID,i.M_Product_ID) AS M_Product_ID,
    --
    l.C_ProjectLine_ID, l.Line, l.Description, l.PlannedQty, l.PlannedPrice, l.PlannedAmt, 
    l.PlannedMarginAmt, l.CommittedQty,
    --
    i.C_ProjectIssue_ID, i.M_Locator_ID, i.MovementQty, i.MovementDate,
    i.Line AS IssueLine, i.Description AS IssueDescription,
    i.M_InOutLine_ID, i.S_TimeExpenseLine_ID,
    --
    fa.C_AcctSchema_ID, fa.Account_ID, 
    fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    --
    l.PlannedAmt-fa.AmtSourceDr+fa.AmtSourceCr AS LineMargin
FROM C_ProjectLine l
    FULL JOIN C_ProjectIssue i ON (i.C_Project_ID=l.C_Project_ID AND i.C_ProjectIssue_ID=l.C_ProjectIssue_ID)
    LEFT OUTER JOIN Fact_Acct fa ON (fa.AD_Table_ID=623 AND fa.Record_ID=i.C_ProjectIssue_ID AND fa.M_Locator_ID IS NULL)
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_UnPosted 
AS
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID, GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx
FROM GL_Journal  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated, pi.UpdatedBy, pi. IsActive,
    p.Name || '_' || pi.Line, pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N' 
FROM C_ProjectIssue pi INNER JOIN C_Project p ON (pi.C_Project_ID=p.C_Project_ID)  
WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID, IsSOTrx
FROM C_Invoice  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID, IsSOTrx 
FROM M_InOut  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 321, M_Inventory_ID, 'N' 
FROM M_Inventory  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 323, M_Movement_ID, 'N' 
FROM M_Movement  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, MovementDate, MovementDate, 325, M_Production_ID, 'N'
FROM M_Production  WHERE Posted<>'Y' -- AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, StatementDate, DateAcct, 407, C_Cash_ID, 'N' 
FROM C_Cash  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N'
FROM C_Payment  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID, 'N' 
FROM C_AllocationHdr  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    Name, StatementDate, StatementDate, 392, C_BankStatement_ID, 'N' 
FROM C_BankStatement  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N' 
FROM M_MatchInv  WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N'
FROM M_MatchPO  WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID, IsSOTrx
FROM C_Order  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateRequired, DateRequired, 702, M_Requisition_ID, 'N'
FROM M_Requisition  WHERE Posted<>'Y' AND DocStatus<>'VO'
/    

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_WarehousePrice
AS
SELECT w.AD_Client_ID, w.AD_Org_ID, 
    CASE WHEN p.Discontinued='N' THEN 'Y' ELSE 'N' END AS IsActive, 
    pr.Created, pr.CreatedBy, pr.Updated, pr.UpdatedBy,
    p.M_Product_ID, pr.M_PriceList_Version_ID, w.M_Warehouse_ID,
    p.Value, p.Name, p.UPC, p.SKU,
    uom.C_UOM_ID, uom.UOMSymbol,
    bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin, 
    bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit, 
    w.Name AS WarehouseName,
    bomQtyAvailable(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyAvailable, 
    bomQtyOnHand(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOnHand, 
    bomQtyReserved(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyReserved, 
    bomQtyOrdered(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOrdered, 
    COALESCE (pa.IsInstanceAttribute, 'N') AS IsInstanceAttribute
FROM M_Product p 
    INNER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID)
    INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)
    INNER JOIN M_Warehouse w ON (p.AD_Client_ID=w.AD_Client_ID)
WHERE p.IsSummary='N' AND p.IsActive='Y' AND pr.IsActive='Y' AND w.IsActive='Y'
--AND pr.M_PriceList_Version_ID=? 
--AND w.M_Warehouse_ID=?
--AND UPPER(p.Value) LIKE ? AND UPPER(p.Name) LIKE ? 
--AND UPPER(p.UPC) LIKE ? AND UPPER(p.SKU) LIKE ? 
--ORDER BY QtyAvailable DESC, Margin DESC
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_Storage 
AS
SELECT s.AD_Client_ID, s.AD_Org_ID,
    -- Product
    s.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    p.GuaranteeDays,p.GuaranteeDaysMin,
    --  Locator
    s.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Storage
    s.QtyOnHand, s.QtyReserved, s.QtyOnHand-s.QtyReserved AS QtyAvailable, 
    s.QtyOrdered, s.DateLastInventory,
    -- Instance
    s.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,
    asi.GuaranteeDate,  -- see PAttributeInstance.java
    daysBetween(asi.GuaranteeDate,getdate()) AS ShelfLifeDays,
    daysBetween(asi.GuaranteeDate,getdate())-p.GuaranteeDaysMin AS GoodForDays,
    ROUND((daysBetween(asi.GuaranteeDate,getdate())/p.GuaranteeDays)*100,0) AS ShelfLifeRemainingPct
FROM M_Storage s
  INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
/

CREATE OR REPLACE VIEW RV_Transaction 
AS
SELECT t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
    t.MovementType,t.MovementDate,t.MovementQty,
    -- Instance
    t.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    -- Product
    t.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    -- Locator
    t.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Inventory
    t.M_InventoryLine_ID,il.M_Inventory_ID,
    -- Movement
    t.M_MovementLine_ID,ml.M_Movement_ID,
    -- In/Out
    t.M_InOutLine_ID,iol.M_InOut_ID,
    -- Production
    t.M_ProductionLine_ID,prdl.M_ProductionPlan_ID,prdp.M_Production_ID,
    -- ProjectIssue
    t.C_ProjectIssue_ID,pjl.C_Project_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
    LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID)
/

-------------------------------------------------------------------------------

--  Click Count
CREATE OR REPLACE VIEW RV_Click_Month
AS
SELECT cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID, 
    firstOf(c.Created,'MM') AS Created,
    COUNT(*) AS Counter
FROM W_ClickCount cc
  INNER JOIN W_Click c ON (cc.W_ClickCount_ID=c.W_ClickCount_ID)
WHERE cc.IsActive='Y'
GROUP BY cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID,
    firstOf(c.Created,'MM')
/

CREATE OR REPLACE VIEW RV_Click_Unprocessed
AS
SELECT *
FROM W_Click
WHERE W_ClickCount_ID IS NULL OR Processed='N'
/

-------------------------------------------------------------------------------
--	Corrected for Credit Memo (See similar RV_C_Invoice)

CREATE OR REPLACE VIEW C_Invoice_v 
AS
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
WHERE i.IsPayScheduleValid<>'Y'
UNION
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID,
    null AS ChargeAmt, 
    null AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN ips.DueAmt*-1 ELSE ips.DueAmt END AS GrandTotal, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE i.IsPayScheduleValid='Y'
    AND ips.IsValid='Y'
/
COMMENT ON TABLE C_Invoice_v IS 'Invoice Information corrected for Credit Memos (Split)'
/

CREATE OR REPLACE VIEW C_Invoice_v1
AS
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
/
COMMENT ON TABLE C_Invoice_v1 IS 'Invoice Information corrected for Credit Memos'
/

CREATE OR REPLACE VIEW C_InvoiceLine_v 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, 
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID, 
	i.C_BPartner_ID, il.M_Product_ID,  
	i.DocumentNo, i.DateInvoiced, i.DateAcct,
	i.IsSOTrx, i.DocStatus,
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE ROUND(i.Multiplier*PriceLimit*QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced-LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE ROUND(i.Multiplier*LineNetAmt-PriceLimit*QtyInvoiced,2) END AS LineOverLimitAmt,
	il.QtyInvoiced, il.QtyEntered,
	il.Line, il.C_OrderLine_ID, il.C_UOM_ID,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_Invoice_v i, C_InvoiceLine il
WHERE i.C_Invoice_ID=il.C_Invoice_ID
/
COMMENT ON TABLE C_InvoiceLine_v IS 'Invoice Line Summary for Reporting Views - Corrected for Credit Memos'
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW C_Invoice_Candidate_v 
AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
	SUM((l.QtyOrdered-l.QtyInvoiced)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN C_InvoiceSchedule si ON (bp.C_InvoiceSchedule_ID=si.C_InvoiceSchedule_ID)
WHERE	o.DocStatus IN ('CO','CL','IP')	-- Standard Orders are IP
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
	--	we need to invoice
	AND	l.QtyOrdered <> l.QtyInvoiced
	--
	AND (
		--	Immediate
		o.InvoiceRule='I'
		--	Order compete ** not supported **
		OR	o.InvoiceRule='O'
		--	Delivery
		OR	(o.InvoiceRule='D' AND l.QtyInvoiced<>l.QtyDelivered)
		--	Order Schedule, but none defined on Business Partner level
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NULL)
		--	Schedule defined at BP
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NOT NULL AND
				(
				--	Daily or none
					(si.InvoiceFrequency IS NULL OR si.InvoiceFrequency='D')
				--	Weekly
				OR	(si.InvoiceFrequency='W')
				--	Bi-Monthly
				OR	(si.InvoiceFrequency='T'
					AND ((TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)
					OR	(TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff+14
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay+14))
					)
				--	Monthly
				OR	(si.InvoiceFrequency='M'
					AND TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1	-- after cutoff
					AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)		-- after invoice day
				)
			)
		)
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID
/

CREATE OR REPLACE VIEW M_InOut_Candidate_v 
AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Warehouse_ID,
	SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
    --  Delivery Rule - not manual
    AND o.DeliveryRule<>'M'
    AND (l.M_Product_ID IS NULL OR EXISTS 
        (SELECT * FROM M_Product p 
        WHERE l.M_Product_ID=p.M_Product_ID AND p.IsExcludeAutoDelivery='N'))
	--	we need to ship
	AND	l.QtyOrdered <> l.QtyDelivered
	AND o.IsDropShip='N'
    AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
    --  Not confirmed shipment
    AND NOT EXISTS (SELECT * FROM M_InOutLine iol 
        INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)
        WHERE iol.C_OrderLine_ID=l.C_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
	--
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
    o.POReference, o.Description, o.SalesRep_ID, l.M_Warehouse_ID
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW C_Payment_v 
AS
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, IsReceipt, C_DocType_ID, TrxType,
    C_BankAccount_ID, C_BPartner_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_PaymentBatch_ID,
    TenderType, CreditCardType, CreditCardNumber, CreditCardVV, CreditCardExpMM, CreditCardExpYY,
    Micr, RoutingNo, AccountNo, CheckNo,
    A_Name, A_Street, A_City, A_State, A_Zip, A_Ident_DL, A_Ident_SSN, A_EMail,
    VoiceAuthCode, Orig_TrxID, PONum,
    C_Currency_ID, C_ConversionType_ID,
    CASE IsReceipt WHEN 'Y' THEN PayAmt ELSE PayAmt*-1 END AS PayAmt,
    CASE IsReceipt WHEN 'Y' THEN DiscountAmt ELSE DiscountAmt*-1 END AS DiscountAmt, 
    CASE IsReceipt WHEN 'Y' THEN WriteOffAmt ELSE WriteOffAmt*-1 END AS WriteOffAmt,
    CASE IsReceipt WHEN 'Y' THEN TaxAmt ELSE TaxAmt*-1 END AS TaxAmt, 
    CASE IsReceipt WHEN 'Y' THEN OverUnderAmt ELSE OverUnderAmt*-1 END AS OverUnderAmt,
    CASE IsReceipt WHEN 'Y' THEN 1 ELSE -1 END AS MultiplierAP,
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted,
    C_Campaign_ID, C_Project_ID, C_Activity_ID
FROM C_Payment
/
COMMENT ON TABLE C_Payment_v IS 'Payment Information corrected for AP/AR'
/

CREATE OR REPLACE VIEW RV_Payment 
AS
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, IsReceipt, C_DocType_ID, TrxType,
    C_BankAccount_ID, C_BPartner_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_PaymentBatch_ID,
    TenderType, CreditCardType, CreditCardNumber, CreditCardVV, CreditCardExpMM, CreditCardExpYY,
    Micr, RoutingNo, AccountNo, CheckNo,
    A_Name, A_Street, A_City, A_State, A_Zip, A_Ident_DL, A_Ident_SSN, A_EMail,
    VoiceAuthCode, Orig_TrxID, PONum,
    C_Currency_ID, C_ConversionType_ID,
    CASE IsReceipt WHEN 'Y' THEN PayAmt ELSE PayAmt*-1 END AS PayAmt,
    CASE IsReceipt WHEN 'Y' THEN DiscountAmt ELSE DiscountAmt*-1 END AS DiscountAmt, 
    CASE IsReceipt WHEN 'Y' THEN WriteOffAmt ELSE WriteOffAmt*-1 END AS WriteOffAmt,
    CASE IsReceipt WHEN 'Y' THEN TaxAmt ELSE TaxAmt*-1 END AS TaxAmt, 
    CASE IsReceipt WHEN 'Y' THEN OverUnderAmt ELSE OverUnderAmt*-1 END AS OverUnderAmt,
    CASE IsReceipt WHEN 'Y' THEN 1 ELSE -1 END AS MultiplierAP,
    paymentAllocated(C_Payment_ID, C_Currency_ID) AS AllocatedAmt,
    paymentAvailable(C_Payment_ID) AS AvailableAmt,
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted,
    C_Campaign_ID, C_Project_ID, C_Activity_ID
FROM C_Payment
/
COMMENT ON TABLE RV_Payment IS 'Payment Information corrected for AP/AR'
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW GL_JournalLine_Acct_v
AS
SELECT 
	gl.GL_JournalLine_ID, gl.AD_Client_ID, gl.AD_Org_ID, gl.IsActive, 
	gl.Created, gl.CreatedBy, gl.Updated, gl.UpdatedBy, gl.GL_Journal_ID, 
	gl.Line, gl.IsGenerated, gl.Description, 
	gl.AmtSourceDr, gl.AmtSourceCr, gl.C_Currency_ID, 
	gl.C_ConversionType_ID, gl.CurrencyRate, gl.DateAcct, 
	gl.AmtAcctDr, gl.AmtAcctCr, gl.C_UOM_ID, gl.Qty, gl.C_ValidCombination_ID, 
	vc.C_AcctSchema_ID, vc.Account_ID, vc.M_Product_ID, vc.C_BPartner_ID,
	vc.AD_OrgTrx_ID, vc.C_LocFrom_ID, vc.C_LocTo_ID, vc.C_SalesRegion_ID, 
	vc.C_Project_ID, vc.C_Campaign_ID, vc.User1_ID, vc.User2_ID, 
	vc.IsFullyQualified, vc.C_Activity_ID
FROM GL_JournalLine gl, C_ValidCombination vc
WHERE gl.C_ValidCombination_ID = vc.C_ValidCombination_ID
/

--  Acct with Rate

CREATE OR REPLACE VIEW RV_Fact_Acct
AS
SELECT f.AD_Client_ID, f.AD_Org_ID, f.IsActive,f.Created,f.CreatedBy,f.Updated,f.UpdatedBy,
    f.Fact_Acct_ID,
    f.C_AcctSchema_ID, f.Account_ID, f.DateTrx, f.DateAcct, f.C_Period_ID, 
    f.AD_Table_ID, f.Record_ID, f.Line_ID,
    f.GL_Category_ID, f.GL_Budget_ID, f.C_Tax_ID, f.M_Locator_ID, 
    f.PostingType, f.C_Currency_ID,
    f.AmtSourceDr, f.AmtSourceCr, (f.AmtSourceDr - f.AmtSourceCr) AS AmtSource,
    f.AmtAcctDr, f.AmtAcctCr, (f.AmtAcctDr - f.AmtAcctCr) AS AmtAcct,
    CASE WHEN (f.AmtSourceDr - f.AmtSourceCr) = 0 THEN 0 ELSE
        (f.AmtAcctDr - f.AmtAcctCr) / (f.AmtSourceDr - f.AmtSourceCr) END AS Rate,
    f.C_UOM_ID, f.Qty,
    f.M_Product_ID, f.C_BPartner_ID, f.AD_OrgTrx_ID, 
    f.C_LocFrom_ID, f.C_LocTo_ID, f.C_SalesRegion_ID,
    f.C_Project_ID, f.C_Campaign_ID, f.C_Activity_ID, 
    f.User1_ID, f.User2_ID, f.A_Asset_ID,
    f.Description,
    o.Value AS OrgValue, o.Name AS OrgName,
    ev.Value AS AccountValue, ev.Name, ev.AccountType,
    bp.Value AS BPartnerValue, bp.Name AS BPName, bp.C_BP_Group_ID, 
    p.Value AS ProductValue, p.Name AS ProductName, p.UPC, p.M_Product_Category_ID
FROM Fact_Acct f
  INNER JOIN AD_Org o ON (f.AD_Org_ID=o.AD_Org_ID)
  INNER JOIN C_ElementValue ev ON (f.Account_ID=ev.C_ElementValue_ID)
  LEFT OUTER JOIN C_BPartner bp ON (f.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN M_Product p ON (f.M_Product_ID=p.M_Product_ID)
/

--  Acct Details Daily

CREATE OR REPLACE VIEW RV_Fact_Acct_Day
AS
SELECT AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, firstOf(DateAcct, 'DD') AS DateAcct, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    SUM(AmtSourceDr) AS AmtSourceDr, SUM(AmtSourceCr) AS AmtSourceCr, SUM(AmtSourceDr - AmtSourceCr) AS AmtSource,
    SUM(AmtAcctDr) AS AmtAcctDr, SUM(AmtAcctCr) AS AmtAcctCr, SUM(AmtAcctDr - AmtAcctCr) AS AmtAcct,
    CASE WHEN SUM(AmtSourceDr - AmtSourceCr) = 0 THEN 0 ELSE
        SUM(AmtAcctDr - AmtAcctCr) / SUM(AmtSourceDr - AmtSourceCr) END AS Rate,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
FROM Fact_Acct
GROUP BY AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, firstOf(DateAcct, 'DD'), C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
/

--  Acct Details Period

CREATE OR REPLACE VIEW RV_Fact_Acct_Period
AS
SELECT AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    SUM(AmtSourceDr) AS AmtSourceDr, SUM(AmtSourceCr) AS AmtSourceCr, SUM(AmtSourceDr - AmtSourceCr) AS AmtSource,
    SUM(AmtAcctDr) AS AmtAcctDr, SUM(AmtAcctCr) AS AmtAcctCr, SUM(AmtAcctDr - AmtAcctCr) AS AmtAcct,
    CASE WHEN SUM(AmtSourceDr - AmtSourceCr) = 0 THEN 0 ELSE
        SUM(AmtAcctDr - AmtAcctCr) / SUM(AmtSourceDr - AmtSourceCr) END AS Rate,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
FROM Fact_Acct
GROUP BY AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
/

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_C_RfQ_UnAnswered
AS
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.Name, q.Description, q.Help, q.SalesRep_ID,
    q.C_RfQ_Topic_ID, q.QuoteType, q.IsQuoteTotalAmt, q.IsQuoteAllQty, q.C_Currency_ID,
    q.DateResponse, q.IsRfQResponseAccepted, q.DateWorkStart, q.DeliveryDays, q.DateWorkComplete,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID
FROM C_RfQ q
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID=r.C_RfQ_ID)
WHERE r.IsComplete='N'
    AND q.Processed='N'
/

CREATE OR REPLACE VIEW RV_C_RfQResponse
AS
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.C_RfQ_Topic_ID,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID,
    r.C_RfQResponse_ID,
    r.C_Currency_ID, r.DateResponse, r.DateWorkStart, r.DeliveryDays, r.DateWorkComplete,
    r.Price, r.Ranking, r.IsSelfService,
    r.Description, r.Help,
    --  Line
    ql.M_Product_ID, ql.M_AttributeSetInstance_ID, 	
    ql.Line, rl.DateWorkStart AS LineDateWorkStart, rl.DeliveryDays AS LineDeliveryDays, rl.DateWorkComplete AS LineDateworkComplete,
    rl.Description AS LineDescription, rl.Help AS LineHelp,
    --  Qty
    qlq.C_UOM_ID, qlq.Qty, qlq.BenchmarkPrice, rlq.Price-qlq.BenchmarkPrice AS BenchmarkDifference,
    rlq.Price AS QtyPrice, rlq.Discount, rlq.Ranking AS QtyRanking
FROM C_RfQ q
    INNER JOIN C_RfQLine ql ON (q.C_RfQ_ID = ql.C_RfQ_ID)
    INNER JOIN C_RfQLineQty qlq ON (ql.C_RfQLine_ID = qlq.C_RfQLine_ID)
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID = r.C_RfQ_ID)
    INNER JOIN C_RfQResponseLine rl ON 
        (r.C_RfQResponse_ID = rl.C_RfQResponse_ID AND ql.C_RfQLine_ID = rl.C_RfQLine_ID)
    INNER JOIN C_RfQResponseLineQty rlq ON 
        (rl.C_RfQResponseLine_ID = rlq.C_RfQResponseLine_ID AND qlq.C_RfQLineQty_ID = rlq.C_RfQLineQty_ID)
WHERE r.IsComplete='Y'
    AND q.Processed='N'
/

CREATE OR REPLACE VIEW RV_M_Requisition
AS
SELECT r.M_Requisition_ID,
    r.AD_Client_ID, r.AD_Org_ID, r.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.DocumentNo, r.Description, r.Help,
    r.AD_User_ID, r.M_PriceList_ID, r.M_Warehouse_ID, r.IsApproved, r.PriorityRule,
    r.DateRequired, r.TotalLines, r.DocAction, r.DocStatus, r.Processed,
    l.M_RequisitionLine_ID, l.Line,
    l.Qty, l.M_Product_ID,
    l.Description AS LineDescription,
    l.PriceActual, l.LineNetAmt
FROM M_Requisition r
  INNER JOIN M_RequisitionLine l ON (r.M_Requisition_ID=l.M_Requisition_ID)
/

CREATE OR REPLACE VIEW RV_InOutConfirm
AS
SELECT c.M_InOutConfirm_ID,
  c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created, c.CreatedBy, c.Updated, c.UpdatedBy,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  c.Description, c.Processing, c.Processed,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx
FROM M_InOutConfirm c
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID)
/

CREATE OR REPLACE VIEW RV_InOutLineConfirm
AS
SELECT cl.M_InOutConfirm_ID, cl.M_InOutLineConfirm_ID,
  cl.AD_Client_ID, cl.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
  cl.TargetQty, cl.ConfirmedQty, cl.DifferenceQty, cl.ScrappedQty,
  cl.Description, cl.Processed,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx,
  cl.M_InOutLine_ID, il.M_Product_ID, il.M_AttributeSetInstance_ID, il.M_Locator_ID
FROM M_InOutLineConfirm cl
  INNER JOIN M_InOutConfirm c ON (cl.M_InOutConfirm_ID=c.M_InOutConfirm_ID)
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID)
  INNER JOIN M_InOutLine il ON (cl.M_InOutLine_ID=il.M_InOutLine_ID)
/

CREATE OR REPLACE VIEW RV_Allocation
AS
SELECT h.C_AllocationHdr_ID, h.AD_Client_ID, h.AD_Org_ID, 
  h.IsActive, h.Created, h.CreatedBy, h.Updated, h.UpdatedBy,
  h.DocumentNo, h.Description, h.DateTrx, h.DateAcct,
  h.C_Currency_ID, h.ApprovalAmt, h.IsManual, h.DocStatus, h.DocAction, h.Processed,
  l.C_AllocationLine_ID,
  l.C_Invoice_ID, l.C_BPartner_ID, l.C_Order_ID, l.C_Payment_ID, l.C_CashLine_ID,
  l.Amount, l.DiscountAmt, l.WriteOffAmt, l.OverUnderAmt
FROM C_AllocationHdr h
  INNER JOIN C_AllocationLine l ON (h.C_AllocationHdr_ID=l.C_AllocationHdr_ID)
/  


CREATE OR REPLACE VIEW RV_PrintFormatDetail
AS
SELECT f.AD_Client_ID, f.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    f.AD_PrintFormat_ID,
    f.Name, f.Description,
    f.IsTableBased,f.IsForm,f.AD_Table_ID,f.AD_ReportView_ID,
    f.AD_PrintPaper_ID, 
    f.AD_PrintColor_ID AS Default_AD_PrintColor_ID,
    f.AD_PrintFont_ID AS Default_AD_PrintFont_ID,
    f.IsStandardHeaderFooter,
    f.AD_PrintTableFormat_ID,
    f.HeaderMargin,f.FooterMargin,
    f.PrinterName,f.IsDefault,
    i.AD_PrintFormatItem_ID,
    i.Name AS ItemName, i.PrintName, i.PrintNameSuffix, i.IsPrinted, i.PrintAreaType, i.SeqNo,
    i.PrintFormatType, i.AD_Column_ID, i.AD_PrintFormatChild_ID, i.ImageIsAttached,i.ImageURL,
    i.IsRelativePosition, i.IsNextLine, XSpace,YSpace, XPosition,YPosition,
    i.MaxWidth, IsHeightOneLine, MaxHeight, i.IsFixedWidth,
    i.IsSetNLPosition,i.IsSuppressNull, i.BelowColumn,
    i.FieldAlignmentType,i.LineAlignmentType,
    i.AD_PrintColor_ID,i.AD_PrintFont_ID,
    i.IsOrderBy,i.SortNo, i.IsGroupBy,i.IsPageBreak,i.IsNextPage,
    i.IsSummarized,i.IsAveraged,i.IsCounted,i.IsMinCalc,i.IsMaxCalc,
    i.IsVarianceCalc,i.IsDeviationCalc,
    i.IsRunningTotal,i.RunningTotalLines,
    i.AD_PrintGraph_ID
FROM AD_PrintFormat f
  INNER JOIN AD_PrintFormatItem i ON (f.AD_PrintFormat_ID=i.AD_PrintFormat_ID)
/

CREATE OR REPLACE VIEW RV_InOutDetails
AS
SELECT h.AD_Client_ID, h.AD_Org_ID, l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
  h.M_InOut_ID,
  h.IsSOTrx, h.DocumentNo, h.DocAction, h.DocStatus, h.Posted, h.Processed,
  h.C_DocType_ID, h.Description, h.C_Order_ID, h.DateOrdered,
  h.MovementType, h.MovementDate, h.DateAcct,
  h.C_BPartner_ID, h.C_BPartner_Location_ID, h.AD_User_ID,
  h.SalesRep_ID,
  h.M_Warehouse_ID,
  h.POReference, h.DeliveryRule, h.FreightCostRule, h.FreightAmt,
  h.DeliveryViaRule, h.M_Shipper_ID, -- h.C_CHARGE_ID, h.CHARGEAmt,
  h.PriorityRule, h.DatePrinted,
  h.NoPackages, h.PickDate, h.ShipDate, h.TrackingNo,
  h.AD_OrgTrx_ID, h.C_Project_ID, h.C_Campaign_ID, h.C_Activity_ID, h.User1_ID, h.User2_ID,
  h.DateReceived, h.IsApproved, h.IsInDispute,
  l.M_InOutLine_ID, l.Line, l.Description AS LineDescription,
  l.C_OrderLine_ID, l.M_Locator_ID,
  l.M_Product_ID, l.C_UOM_ID,
  l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
  pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
  l.MovementQty, l.QtyEntered,
  l.IsDescription,
  l.ConfirmedQty, l.PickedQty, l.ScrappedQty, l.TargetQty,
  loc.Value AS LocatorValue, loc.X, loc.Y, loc.Z
FROM M_InOut h
  INNER JOIN M_InOutLine l ON (h.M_InOut_ID=l.M_InOut_ID)
  LEFT OUTER JOIN M_Locator loc ON (l.M_Locator_ID=loc.M_Locator_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
/

CREATE OR REPLACE VIEW AD_ChangeLog_v
AS
SELECT l.AD_Session_ID, l.AD_ChangeLog_ID, 
    t.TableName, l.Record_ID, c.ColumnName, 
    l.OldValue, l.NewValue, 
    u.Name, l.Created
FROM AD_ChangeLog l
    INNER JOIN AD_Table t ON (l.AD_Table_ID=t.AD_Table_ID)
    INNER JOIN AD_Column c ON (l.AD_Column_ID=c.AD_Column_ID)
    INNER JOIN AD_User u ON (l.CreatedBy=u.AD_User_ID)
ORDER BY l.AD_Session_ID, l.AD_ChangeLog_ID, t.TableName, l.Record_ID, c.ColumnName
/

CREATE OR REPLACE VIEW RV_BPartnerOpen
AS
SELECT i.AD_Client_ID,i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    i.C_BPartner_ID, i.C_Currency_ID,
    i.GrandTotal*i.MultiplierAP AS Amt,
    invoiceOpen (i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)*MultiplierAP AS OpenAmt,
    i.DateInvoiced AS DateDoc, 
    COALESCE(daysBetween(getdate(),ips.DueDate), paymentTermDueDays(C_PaymentTerm_ID,DateInvoiced,getdate())) AS DaysDue,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM C_Invoice_v i 
  LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID)
WHERE IsPaid='N'
 AND DocStatus IN ('CO','CL')
UNION
SELECT p.AD_Client_ID,p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy,p.Updated,p.UpdatedBy,
    p.C_BPartner_ID, p.C_Currency_ID,
    p.PayAmt*MultiplierAP*-1 AS Amt,
    paymentAvailable(p.C_Payment_ID)*p.MultiplierAP*-1 AS OpenAmt,
    p.DateTrx AS DateDoc,
    null,
    p.C_Campaign_ID, p.C_Project_ID, p.C_Activity_ID
FROM C_Payment_v p 
WHERE p.IsAllocated='N' AND p.C_BPartner_ID IS NOT NULL
 AND p.DocStatus IN ('CO','CL')
/

CREATE OR REPLACE VIEW RV_CommissionRunDetail
AS
SELECT cr.AD_Client_ID, cr.AD_Org_ID, cr.IsActive, cr.Created,cr.CreatedBy, cr.Updated,cr.UpdatedBy,
    -- Run
    cr.C_CommissionRun_ID, cr.DocumentNo, cr.Description, 
    cr.StartDate, cr.GrandTotal, cr.Processed,
    -- Commission
    c.C_Commission_ID, c.C_BPartner_ID AS Commission_BPartner_ID,
    --  Commission Amount
    ca.C_CommissionAmt_ID, 
    ca.ConvertedAmt AS CommissionConvertedAmt, ca.ActualQty AS CommissionQty, 
    ca.CommissionAmt,
    --  Commission Detail
    cd.C_CommissionDetail_ID,
    cd.Reference,
    cd.C_OrderLine_ID,
    cd.C_InvoiceLine_ID,
    cd.Info,
    cd.C_Currency_ID, cd.ActualAmt, cd.ConvertedAmt,
    cd.ActualQty,
    -- Invoice/Order
    i.DocumentNo AS InvoiceDocumentNo,
    COALESCE (i.DateInvoiced, o.DateOrdered) AS DateDoc,
    COALESCE (il.M_Product_ID,ol.M_Product_ID) AS M_Product_ID,
    COALESCE (i.C_BPartner_ID,o.C_BPartner_ID) AS C_BPartner_ID,
    COALESCE (i.C_BPartner_Location_ID,o.C_BPartner_Location_ID) AS C_BPartner_Location_ID,
    COALESCE (i.AD_User_ID,o.AD_User_ID) AS AD_User_ID,
    COALESCE (i.C_DocType_ID,o.C_DocType_ID) AS C_DocType_ID
FROM C_CommissionRun cr
    INNER JOIN C_Commission c ON (cr.C_Commission_ID=c.C_Commission_ID)
    INNER JOIN C_CommissionAmt ca ON (cr.C_CommissionRun_ID=ca.C_CommissionRun_ID)
    INNER JOIN C_CommissionDetail cd ON (ca.C_CommissionAmt_ID=cd.C_CommissionAmt_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (cd.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_InvoiceLine il ON (cd.C_InvoiceLine_ID=il.C_InvoiceLine_ID)
    LEFT OUTER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID)
    LEFT OUTER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
/

CREATE OR REPLACE VIEW M_Transaction_v
AS
SELECT M_Transaction_ID, 
  t.AD_Client_ID, t.AD_Org_ID, t.IsActive, t.Created,t.CreatedBy, t.Updated,t.UpdatedBy,
  t.MovementType, t.M_Locator_ID, t.M_Product_ID, t.MovementDate, t.MovementQty,
  t.M_InventoryLine_ID, i.M_Inventory_ID,
  t.M_MovementLine_ID, m.M_Movement_ID,
  t.M_InOutLine_ID, io.M_InOut_ID,
  t.M_ProductionLine_ID, pp.M_Production_ID,
  t.C_ProjectIssue_ID, pi.C_Project_ID,
  t.M_AttributeSetInstance_ID
FROM M_Transaction t
  LEFT OUTER JOIN M_InOutLine io ON (t.M_InOutLine_ID=io.M_InOutLine_ID AND t.M_AttributeSetInstance_ID=io.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_MovementLine m ON (t.M_MovementLine_ID=m.M_MovementLine_ID AND t.M_AttributeSetInstance_ID=m.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine i ON (t.M_InventoryLine_ID=i.M_InventoryLine_ID AND t.M_AttributeSetInstance_ID=i.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN C_ProjectIssue pi ON (t.C_ProjectIssue_ID=pi.C_ProjectIssue_ID AND t.M_AttributeSetInstance_ID=pi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionLine pl ON (t.M_ProductionLine_ID=pl.M_ProductionLine_ID AND t.M_AttributeSetInstance_ID=pl.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionPlan pp ON (pl.M_ProductionPlan_ID=pp.M_ProductionPlan_ID)
/


CREATE OR REPLACE VIEW RV_RequestUpdates
AS
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    NULL AS R_Group_ID, NULL AS R_RequestType_ID, NULL AS R_Category_ID
FROM R_RequestUpdates
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    r.R_Group_ID, NULL, NULL
FROM R_GroupUpdates u
    INNER JOIN R_Request r ON (u.R_Group_ID=r.R_Group_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, r.R_RequestType_ID, NULL
FROM R_RequestTypeUpdates u
    INNER JOIN R_Request r ON (u.R_RequestType_ID=r.R_RequestType_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, NULL, r.R_Category_ID
FROM R_CategoryUpdates u
    INNER JOIN R_Request r ON (u.R_Category_ID=r.R_Category_ID)
UNION   --  BP User
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    NULL, NULL, NULL
FROM R_Request
WHERE AD_User_ID IS NOT NULL
UNION   --  SalesRep
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, r.R_Category_ID
FROM AD_User u
    INNER JOIN R_Request r ON (u.AD_User_ID=r.SalesRep_ID)
UNION   --  Role
SELECT r.AD_Client_ID, r.AD_Org_ID, u.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, NULL
FROM R_Request r
    INNER JOIN AD_User_Roles u ON (u.AD_Role_ID=r.AD_Role_ID)
/
CREATE OR REPLACE VIEW RV_RequestUpdates_Only
AS
SELECT MIN(AD_Client_ID) AS AD_Client_ID, MIN(AD_Org_ID) AS AD_ORG_ID, 'Y' AS IsActive, 
    SysDate AS Created, 0 AS CreatedBy, SysDate AS Updated, 0 AS UpdatedBy,
    R_Request_ID, AD_User_ID
FROM RV_RequestUpdates
WHERE IsActive='Y'
GROUP BY R_Request_ID, AD_User_ID
/

--  Combination of Invoice and Accounting

CREATE OR REPLACE VIEW T_InvoiceGL_v
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy, i.Updated,i.UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced, i.DateAcct, i.C_PaymentTerm_ID,
    i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
    i.C_Currency_ID, i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.AD_OrgTrx_ID, i.User1_ID, i.User2_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    fa.Fact_Acct_ID, fa.C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
    fa.C_Tax_ID, fa.M_Locator_ID,
    fa.PostingType, fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    fa.C_UOM_ID, fa.Qty,
--  GL
    gl.AD_PInstance_ID, gl.APAR, gl.OpenAmt, gl.Percent,
    gl.AmtRevalDr, gl.AmtRevalCr, gl.DateReval, gl.C_ConversionTypeReval_ID,
    gl.AmtSourceBalance, gl.AmtAcctBalance, gl.C_DocTypeReval_ID,
    gl.AmtRevalDrDiff, gl.AmtRevalCrDiff, gl.IsAllCurrencies
FROM T_InvoiceGL gl
  INNER JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID)
/

CREATE OR REPLACE VIEW T_InvoiceGL_vt
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy, i.Updated,i.UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced, i.DateAcct, i.C_PaymentTerm_ID,
    i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
    i.C_Currency_ID, i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.AD_OrgTrx_ID, i.User1_ID, i.User2_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    fa.Fact_Acct_ID, fa.C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
    fa.C_Tax_ID, fa.M_Locator_ID,
    fa.PostingType, fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    fa.C_UOM_ID, fa.Qty, 
--  GL
    gl.AD_PInstance_ID, gl.APAR, gl.OpenAmt, gl.Percent,
    gl.AmtRevalDr, gl.AmtRevalCr, gl.DateReval, gl.C_ConversionTypeReval_ID,
    gl.AmtSourceBalance, gl.AmtAcctBalance, gl.C_DocTypeReval_ID,
    gl.AmtRevalDrDiff, gl.AmtRevalCrDiff, gl.IsAllCurrencies
FROM T_InvoiceGL gl
  INNER JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID)
/

CREATE OR REPLACE VIEW M_InOutLineMA_v
AS
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_InOut_ID, m.M_InOutLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID
FROM M_InOutLineMA m INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_InOut_ID, M_InOutLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID
FROM M_InOutLine 
/

CREATE OR REPLACE VIEW M_InOutLineMA_vt
AS
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_InOut_ID, m.M_InOutLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID
FROM M_InOutLineMA m INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_InOut_ID, M_InOutLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID
FROM M_InOutLine 
/

CREATE OR REPLACE VIEW M_MovementLineMA_v
AS
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_Movement_ID, m.M_MovementLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID, l.M_LocatorTo_ID
FROM M_MovementLineMA m INNER JOIN M_MovementLine l ON (m.M_MovementLine_ID=l.M_MovementLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_Movement_ID, M_MovementLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID, M_LocatorTo_ID
FROM M_MovementLine 
/

CREATE OR REPLACE VIEW M_MovementLineMA_vt
AS
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_Movement_ID, m.M_MovementLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID, l.M_LocatorTo_ID
FROM M_MovementLineMA m INNER JOIN M_MovementLine l ON (m.M_MovementLine_ID=l.M_MovementLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_Movement_ID, M_MovementLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID, M_LocatorTo_ID
FROM M_MovementLine 
/

SELECT 'End of Views' FROM DUAL
/



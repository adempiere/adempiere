DROP FUNCTION adempiereproperties();

DROP FUNCTION adempiereproperty(p_key character varying);

DROP FUNCTION adempiereversion();

DROP FUNCTION bomqtyavailable(m_product_id numeric, m_attributesetinstance_id numeric, m_warehouse_id numeric, m_locator_id numeric);

DROP FUNCTION bomqtyonhand(m_product_id numeric, m_attributesetinstance_id numeric, m_warehouse_id numeric, m_locator_id numeric);

DROP FUNCTION bomqtyordered(m_product_id numeric, m_attributesetinstance_id numeric, m_warehouse_id numeric, m_locator_id numeric);

DROP FUNCTION bomqtyreserved(m_product_id numeric, m_attributesetinstance_id numeric, m_warehouse_id numeric, m_locator_id numeric);

-- DROP FUNCTION documentno(p_mpc_mrp_id numeric);   -- you can enable this line for 340


CREATE OR REPLACE FUNCTION acctBalance(p_Account_ID numeric, p_AmtDr numeric, p_AmtCr numeric) RETURNS numeric AS $body$
DECLARE
	v_balance	NUMERIC;
	v_AccountType   C_ElementValue.AccountType%TYPE;
    	v_AccountSign   C_ElementValue.AccountSign%TYPE;

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
	
END;

$body$ LANGUAGE plpgsql;
  

 	  	 


CREATE OR REPLACE FUNCTION bompricelimit(Product_id numeric, Pricelist_version_id numeric) RETURNS numeric AS $bompricelimit$
DECLARE
	v_Price	NUMERIC;
	v_ProductPrice	NUMERIC;
	bom RECORD;

BEGIN
	--	Try to get price from PriceList directly
	SELECT	COALESCE (SUM(PriceLimit), 0)
      	INTO	v_Price
   	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=Product_ID
		LOOP
			v_ProductPrice := bomPriceLimit (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		END LOOP;
	END IF;
	--
	RETURN v_Price;
	
END;

$bompricelimit$ LANGUAGE plpgsql;
  
  
  
  
ALTER FUNCTION bompricelimit(m_product_id numeric, m_pricelist_version_id numeric) OWNER TO adempiere;


CREATE OR REPLACE FUNCTION bompricelist(Product_id numeric, Pricelist_version_id numeric) RETURNS numeric AS $bompricelist$
DECLARE
	v_Price	NUMERIC;
	v_ProductPrice	NUMERIC;
	bom RECORD;

BEGIN
	--	Try to get price from pricelist directly
	SELECT	COALESCE (SUM(PriceList), 0)
	INTO	v_Price
	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=Product_ID
		LOOP
			v_ProductPrice := bomPriceList (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		END LOOP;
	END IF;
	--
	RETURN v_Price;
	
END;

$bompricelist$ LANGUAGE plpgsql;
  
  
ALTER FUNCTION bompricelist(m_product_id numeric, m_pricelist_version_id numeric) OWNER TO adempiere;

CREATE OR REPLACE FUNCTION bompricestd(Product_id numeric, Pricelist_version_id numeric) RETURNS numeric AS $bompricestd$
DECLARE
	v_Price	NUMERIC;
	v_ProductPrice	NUMERIC;
	bom RECORD;

BEGIN
	--	Try to get price from PriceList directly
	SELECT	COALESCE(SUM(PriceStd), 0)
	INTO	v_Price
	FROM	M_ProductPrice
	WHERE M_PriceList_Version_ID=PriceList_Version_ID AND M_Product_ID=Product_ID;

	--	No Price - Check if BOM
	IF (v_Price = 0) THEN
		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=Product_ID
		LOOP
			v_ProductPrice := bomPriceStd (bom.M_ProductBOM_ID, PriceList_Version_ID);
			v_Price := v_Price + (bom.BOMQty * v_ProductPrice);
		END LOOP;
	END IF;
	--
	RETURN v_Price;
	
END;

$bompricestd$ LANGUAGE plpgsql;
  
ALTER FUNCTION bompricestd(m_product_id numeric, m_pricelist_version_id numeric) OWNER TO adempiere;

CREATE OR REPLACE FUNCTION bomqtyavailable(Product_ID numeric, Attributesetinstance_id numeric, Warehouse_ID numeric, Locator_ID numeric)
  RETURNS numeric AS $bomqtyavailable$

BEGIN
	
	RETURN bomQtyOnHand(Product_ID, Attributesetinstance_id, Warehouse_ID, Locator_ID)
		- bomQtyReserved(Product_ID, Attributesetinstance_id, Warehouse_ID, Locator_ID);
	
END;

$bomqtyavailable$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION bomqtyavailable(Product_id numeric, Warehouse_id numeric, Locator_id numeric)
  RETURNS numeric AS $bomqtyavailable$
DECLARE
	v_Price	NUMERIC;
	v_ProductPrice	NUMERIC;
	bom RECORD;

BEGIN
	
		RETURN bomQtyOnHand(Product_ID, Warehouse_ID, Locator_ID)
		- bomQtyReserved(Product_ID, Warehouse_ID, Locator_ID);
	
END;

$bomqtyavailable$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION bomqtyonhand(p_Product_ID numeric, p_Warehouse_ID numeric, p_Locator_ID numeric)
  RETURNS numeric AS $bomqtyonhand$
DECLARE
	v_Warehouse_ID		NUMERIC;
	v_Quantity		NUMERIC := 99999;	--	unlimited
	v_IsBOM			CHAR(1);
	v_IsStocked		CHAR(1);
	v_ProductType		CHAR(1);
	v_ProductQty		NUMERIC;
	v_StdPrecision		NUMERIC;
	bom RECORD;

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
			SELECT 	COALESCE(SUM(QtyOnHand), 0)
			  INTO	v_ProductQty
			FROM 	M_Storage s
			WHERE M_Product_ID=p_Product_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=v_Warehouse_ID);
			--
			RETURN v_ProductQty;
		END IF;
	
		--	Go though BOM

		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=p_Product_ID
		LOOP
			--	Stocked Items "leaf node"
			IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
				--	Get ProductQty
				SELECT 	COALESCE(SUM(QtyOnHand), 0)
				  INTO	v_ProductQty
				FROM 	M_Storage s
				WHERE 	M_Product_ID=bom.M_ProductBOM_ID
				  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
				  	AND l.M_Warehouse_ID=v_Warehouse_ID);
				--	Get Rounding Precision
				SELECT 	COALESCE(MAX(u.StdPrecision), 0)
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
				v_ProductQty := bomQtyOnHand (bom.M_ProductBOM_ID, v_Warehouse_ID, p_Locator_ID);
				--	How much can we make overall
				IF (v_ProductQty < v_Quantity) THEN
					v_Quantity := v_ProductQty;
				END IF;
			END IF;
		END LOOP;	--	BOM
	
	
		IF (v_Quantity > 0) THEN
			--	Get Rounding Precision for Product
			SELECT 	COALESCE(MAX(u.StdPrecision), 0)
			  INTO	v_StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
			--
			RETURN ROUND (v_Quantity, v_StdPrecision);
		END IF;
	RETURN 0;
	
END;

$bomqtyonhand$ LANGUAGE plpgsql;



-- ?? CREATE OR REPLACE FUNCTION BomqtyonhandASI

CREATE OR REPLACE FUNCTION bomqtyordered(p_Product_ID numeric, p_Warehouse_ID numeric, p_Locator_ID numeric)
  RETURNS numeric AS $bomqtyordered$
DECLARE
	v_Warehouse_ID		NUMERIC;
	v_Quantity		NUMERIC := 99999;	--	unlimited
	v_IsBOM			CHAR(1);
	v_IsStocked		CHAR(1);
	v_ProductType		CHAR(1);
	v_ProductQty		NUMERIC;
	v_StdPrecision		NUMERIC;
	bom RECORD;

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
			SELECT 	COALESCE(SUM(QtyOrdered), 0)
			  INTO	v_ProductQty
			FROM 	M_Storage s
			WHERE M_Product_ID=p_Product_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=v_Warehouse_ID);
			--
			RETURN v_ProductQty;
		END IF;
	
		--	Go though BOM

		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=p_Product_ID
		LOOP
			--	Stocked Items "leaf node"
			IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
				--	Get ProductQty
				SELECT 	COALESCE(SUM(QtyOrdered), 0)
				  INTO	v_ProductQty
				FROM 	M_Storage s
				WHERE 	M_Product_ID=bom.M_ProductBOM_ID
				  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
				  	AND l.M_Warehouse_ID=v_Warehouse_ID);
				--	Get Rounding Precision
				SELECT 	COALESCE(MAX(u.StdPrecision), 0)
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
			SELECT 	COALESCE(MAX(u.StdPrecision), 0)
			  INTO	v_StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
			--
			RETURN ROUND (v_Quantity, v_StdPrecision);
		END IF;
	RETURN 0;
	
END;

$bomqtyordered$ LANGUAGE plpgsql;



-- ?? CREATE OR REPLACE FUNCTION BomqtyorderedASI

CREATE OR REPLACE FUNCTION bomqtyreserved(p_Product_ID numeric, p_Warehouse_ID numeric, p_Locator_ID numeric)
  RETURNS numeric AS $bomqtyreserved$
DECLARE
	v_Warehouse_ID		NUMERIC;
	v_Quantity		NUMERIC := 99999;	--	unlimited
	v_IsBOM			CHAR(1);
	v_IsStocked		CHAR(1);
	v_ProductType		CHAR(1);
	v_ProductQty		NUMERIC;
	v_StdPrecision		NUMERIC;
	bom RECORD;

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
			SELECT 	COALESCE(SUM(QtyReserved), 0)
			  INTO	v_ProductQty
			FROM 	M_Storage s
			WHERE M_Product_ID=p_Product_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=v_Warehouse_ID);
			--
			RETURN v_ProductQty;
		END IF;
	
		--	Go though BOM

		FOR bom IN  
			SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
			FROM M_Product_BOM b, M_Product p
			WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  	AND b.M_Product_ID=p_Product_ID
		LOOP
			--	Stocked Items "leaf node"
			IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
				--	Get ProductQty
				SELECT 	COALESCE(SUM(QtyReserved), 0)
				  INTO	v_ProductQty
				FROM 	M_Storage s
				WHERE 	M_Product_ID=bom.M_ProductBOM_ID
				  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
				  	AND l.M_Warehouse_ID=v_Warehouse_ID);
				--	Get Rounding Precision
				SELECT 	COALESCE(MAX(u.StdPrecision), 0)
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
			SELECT 	COALESCE(MAX(u.StdPrecision), 0)
			  INTO	v_StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
			--
			RETURN ROUND (v_Quantity, v_StdPrecision);
		END IF;
	RETURN 0;
	
END;

$bomqtyreserved$ LANGUAGE plpgsql;


-- ?? CREATE OR REPLACE FUNCTION BomqtyreservedASI

CREATE OR REPLACE FUNCTION bpartnerRemitLocation(p_C_BPartner_ID C_BPartner.C_BPartner_ID%TYPE) 
RETURNS numeric AS $body$

DECLARE
	v_C_Location_ID	NUMERIC := NULL;
	l RECORD;

BEGIN
	FOR l IN 
		SELECT	IsRemitTo, C_Location_ID
		FROM	C_BPartner_Location
		WHERE	C_BPartner_ID=p_C_BPartner_ID
		ORDER BY IsRemitTo DESC
	LOOP
		IF (v_C_Location_ID IS NULL) THEN
			v_C_Location_ID := l.C_Location_ID;
		END IF;
	END LOOP;
	RETURN v_C_Location_ID;
	
END;

$body$ LANGUAGE plpgsql;
  


create or replace FUNCTION currencyBase
(
	p_Amount	NUMERIC,
	p_CurFrom_ID	NUMERIC,
	p_ConvDate	timestamp with time zone,
	p_Client_ID	NUMERIC,
	p_Org_ID	NUMERIC
)
RETURNS numeric AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * 
 ***
 * Title:	Convert Amount to Base Currency of Client
 * Description:
 *		Get CurrencyTo from Client
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT currencyBase(100,116,null,11,null) FROM AD_System; => 64.72
 ************************************************************************/
DECLARE
	v_CurTo_ID	NUMERIC;
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
END;

$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION currencyConvert(
	p_Amount		NUMERIC,
	p_CurFrom_ID		NUMERIC,
	p_CurTo_ID		NUMERIC,
	p_ConvDate		timestamp with time zone,
	p_ConversionType_ID IN	NUMERIC,
	p_Client_ID		NUMERIC,
	p_Org_ID		NUMERIC
	) 

RETURNS numeric AS $body$
	
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Convert Amount (using IDs)
 * Description:
 *		from CurrencyFrom_ID to CurrencyTo_ID
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *	SELECT currencyConvert(100,116,100,null,null,null,null) FROM AD_System;  => 64.72
 ************************************************************************/	
	
	
DECLARE
	v_Rate				NUMERIC;

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
	
END;

$body$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION currencyRate(
	p_CurFrom_ID		NUMERIC,
	p_CurTo_ID		NUMERIC,
	p_ConvDate		timestamp with time zone,
	p_ConversionType_ID	NUMERIC,
	p_Client_ID		NUMERIC,
	p_Org_ID		NUMERIC
	) 

RETURNS numeric AS $body$
	
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Return Conversion Rate
 * Description:
 *		from CurrencyFrom_ID to CurrencyTo_ID
 *		Returns NULL, if rate not found
 * Test
 *		SELECT currencyrate(116, 100, null, null, null, null) FROM AD_System;  => .647169
 ************************************************************************/
	
	
DECLARE
	--	Currency From variables
	cf_IsEuro		CHAR(1);
	cf_IsEMUMember		CHAR(1);
	cf_EMUEntryDate		timestamp with time zone;
	cf_EMURate		NUMERIC;
	--	Currency To variables
	ct_IsEuro		CHAR(1);
	ct_IsEMUMember		CHAR(1);
	ct_EMUEntryDate	DATE;
	ct_EMURate		NUMERIC;
	--	Triangle
	v_CurrencyFrom		NUMERIC;
	v_CurrencyTo		NUMERIC;
	v_CurrencyEuro		NUMERIC;
	--
	v_ConvDate		timestamp with time zone := now();
	v_ConversionType_ID	NUMERIC := 0;
	v_Rate			NUMERIC;
	c			RECORD;			

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
		    RAISE NOTICE 'Conversion Type Not Found';
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
		RAISE NOTICE 'From Currency Not Found';
		RETURN NULL;
	END IF;
	SELECT	MAX(IsEuro), MAX(IsEMUMember), MAX(EMUEntryDate), MAX(EMURate)
	  INTO	ct_IsEuro, ct_IsEMUMember, ct_EMUEntryDate, ct_EMURate
	FROM		C_Currency
	  WHERE	C_Currency_ID = p_CurTo_ID;
	-- Not Found
	IF (ct_IsEuro IS NULL) THEN
		RAISE NOTICE 'To Currency Not Found';
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
			RAISE NOTICE 'Euro Not Found';
			RETURN NULL;
		END IF;
		IF (cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
			v_CurrencyFrom := v_CurrencyEuro;
		ELSE
			v_CurrencyTo := v_CurrencyEuro;
		END IF;
	END IF;

	--	Get Rate

	BEGIN
		FOR c IN SELECT	MultiplyRate
			FROM	C_Conversion_Rate
			WHERE	C_Currency_ID=v_CurrencyFrom AND C_Currency_ID_To=v_CurrencyTo
			  AND	C_ConversionType_ID=v_ConversionType_ID
			  AND	v_ConvDate BETWEEN ValidFrom AND ValidTo
			  AND	AD_Client_ID IN (0,p_Client_ID) AND AD_Org_ID IN (0,p_Org_ID)
			ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC
		LOOP
			v_Rate := c.MultiplyRate;
			EXIT;	--	only first
		END LOOP;
	END;
	--	Not found
	IF (v_Rate IS NULL) THEN
		RAISE NOTICE 'Conversion Rate Not Found';
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
	RAISE NOTICE '%', SQLERRM;
	RETURN NULL;

	
END;

$body$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION invoiceDiscount
(
	p_C_Invoice_ID		       	NUMERIC,
	p_paydate 			timestamp with time zone,
	p_C_InvoicePaySchedule_ID	NUMERIC
)
RETURNS numeric AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Calculate Payment Discount Amount
 * Description:
 *			- Calculate discountable amount (i.e. with or without tax)
 *			- Calculate and return payment discount
 * Test:
 * 		select invoiceDiscount(109, now(), 103) from ad_system; => 0
 ************************************************************************/
DECLARE
	v_Amount		NUMERIC;
	v_IsDiscountLineAmt	CHAR(1);
	v_GrandTotal		NUMERIC;
	v_TotalLines		NUMERIC;
	v_C_PaymentTerm_ID	NUMERIC(10);
	v_DocDate		timestamp with time zone;
	v_PayDate		timestamp with time zone := now();
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
END;

$body$ LANGUAGE plpgsql;

 	  	 


CREATE OR REPLACE FUNCTION invoiceOpen
(
	p_C_Invoice_ID	            IN	NUMERIC,
    	p_C_InvoicePaySchedule_ID   IN  NUMERIC
)
RETURNS numeric AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Calculate Open Item Amount in Invoice Currency
 * Description:
 *	Add up total amount open for C_Invoice_ID if no split payment.
 *  Grand Total minus Sum of Allocations in Invoice Currency
 *
 *  For Split Payments:
 *  Allocate Payments starting from first schedule.
 *  Cannot be used for IsPaid as mutating
 *
 * Test:
 * 	SELECT C_InvoicePaySchedule_ID, DueAmt FROM C_InvoicePaySchedule WHERE C_Invoice_ID=109 ORDER BY DueDate;
 * 	SELECT invoiceOpen (109, null) FROM AD_System; - converted to default client currency
 * 	SELECT invoiceOpen (109, 11) FROM AD_System; - converted to default client currency
 * 	SELECT invoiceOpen (109, 102) FROM AD_System;
 * 	SELECT invoiceOpen (109, 103) FROM AD_System;
 ************************************************************************/
DECLARE
	v_Currency_ID		NUMERIC(10);
	v_TotalOpenAmt  	NUMERIC := 0;
	v_PaidAmt  	        NUMERIC := 0;
	v_Remaining	        NUMERIC := 0;
    	v_MultiplierAP      	NUMERIC := 0;
    	v_MultiplierCM      	NUMERIC := 0;
    	v_Temp              	NUMERIC := 0;
    	ar			RECORD;
    	s			RECORD;

BEGIN
	--	Get Currency
	BEGIN
		SELECT	MAX(C_Currency_ID), SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier)
		INTO	v_Currency_ID, v_TotalOpenAmt, v_MultiplierAP, v_MultiplierCM
		FROM	C_Invoice_v		--	corrected for CM / Split Payment
		WHERE	C_Invoice_ID = p_C_Invoice_ID;
	EXCEPTION	--	Invoice in draft form
		WHEN OTHERS THEN
            	RAISE NOTICE 'InvoiceOpen - %', SQLERRM;
			RETURN NULL;
	END;

	--	Calculate Allocated Amount
	FOR ar IN 
		SELECT	a.AD_Client_ID, a.AD_Org_ID,
		al.Amount, al.DiscountAmt, al.WriteOffAmt,
		a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
		INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Invoice_ID = p_C_Invoice_ID
          	AND   a.IsActive='Y'
	LOOP
        v_Temp := ar.Amount + ar.DisCountAmt + ar.WriteOffAmt;
		v_PaidAmt := v_PaidAmt
        -- Allocation
			+ currencyConvert(v_Temp * v_MultiplierAP,
				ar.C_Currency_ID, v_Currency_ID, ar.DateTrx, null, ar.AD_Client_ID, ar.AD_Org_ID);
      	RAISE NOTICE '   PaidAmt=% , Allocation= % * %', v_PaidAmt, v_Temp, v_MultiplierAP;
	END LOOP;

    --  Do we have a Payment Schedule ?
    IF (p_C_InvoicePaySchedule_ID > 0) THEN --   if not valid = lists invoice amount
        v_Remaining := v_PaidAmt;
        FOR s IN 
        	SELECT  C_InvoicePaySchedule_ID, DueAmt
	        FROM    C_InvoicePaySchedule
		WHERE	C_Invoice_ID = p_C_Invoice_ID
	        AND   IsValid='Y'
        	ORDER BY DueDate
        LOOP
            IF (s.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
                v_TotalOpenAmt := (s.DueAmt*v_MultiplierCM) - v_Remaining;
                IF (s.DueAmt - v_Remaining < 0) THEN
                    v_TotalOpenAmt := 0;
                END IF;
            ELSE -- calculate amount, which can be allocated to next schedule
                v_Remaining := v_Remaining - s.DueAmt;
                IF (v_Remaining < 0) THEN
                    v_Remaining := 0;
                END IF;
            END IF;
        END LOOP;
    ELSE
        v_TotalOpenAmt := v_TotalOpenAmt - v_PaidAmt;
    END IF;
--  RAISE NOTICE '== Total=' || v_TotalOpenAmt;

	--	Ignore Rounding
	IF (v_TotalOpenAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_TotalOpenAmt := 0;
	END IF;

	--	Round to penny
	v_TotalOpenAmt := ROUND(COALESCE(v_TotalOpenAmt,0), 2);
	RETURN	v_TotalOpenAmt;
END;

$body$ LANGUAGE plpgsql;





 	  	 


CREATE OR REPLACE FUNCTION invoicePaid
(
	p_C_Invoice_ID		NUMERIC,
	p_C_Currency_ID	    	NUMERIC,
	p_MultiplierAP		NUMERIC	-- DEFAULT 1
)
RETURNS numeric AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
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
 *
 * Test:
    SELECT C_Invoice_ID, IsPaid, IsSOTrx, GrandTotal, 
    invoicePaid (C_Invoice_ID, C_Currency_ID, MultiplierAP)
    FROM C_Invoice_v;
 *	
 ************************************************************************/
DECLARE
	v_MultiplierAP		NUMERIC := 1;
	v_PaymentAmt		NUMERIC := 0;
	ar			RECORD;

BEGIN
	--	Default
	IF (p_MultiplierAP IS NOT NULL) THEN
		v_MultiplierAP := p_MultiplierAP;
	END IF;
	--	Calculate Allocated Amount
	FOR ar IN 
		SELECT	a.AD_Client_ID, a.AD_Org_ID, 
		al.Amount, al.DiscountAmt, al.WriteOffAmt, 
		a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
		INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Invoice_ID = p_C_Invoice_ID
		AND   a.IsActive='Y'
	LOOP
		v_PaymentAmt := v_PaymentAmt
			+ currencyConvert(ar.Amount + ar.DisCountAmt + ar.WriteOffAmt,
				ar.C_Currency_ID, p_C_Currency_ID, ar.DateTrx, null, ar.AD_Client_ID, ar.AD_Org_ID);
	END LOOP;
	--
	RETURN	ROUND(COALESCE(v_PaymentAmt,0), 2) * v_MultiplierAP;
END;

$body$ LANGUAGE plpgsql;
 	  	 


CREATE OR REPLACE FUNCTION nextid(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR,
	o_NextID		OUT	INTEGER
)
  RETURNS INTEGER AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2005 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Get Next ID - no Commit
 * Description: Returns the next id of the sequence.
 * Test:
 *	select * from nextid((select ad_sequence_id from ad_sequence where name = 'Test')::Integer, 'Y'::Varchar);
 * 
 ************************************************************************/

BEGIN
    IF (p_System = 'Y') THEN
	RAISE NOTICE 'system';
        SELECT CurrentNextSys
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNextSys = CurrentNextSys + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    ELSE
        SELECT CurrentNext
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNext = CurrentNext + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    END IF;
    --
EXCEPTION
    WHEN  OTHERS THEN
    	RAISE NOTICE '%',SQLERRM;
END;

$body$ LANGUAGE plpgsql;


 	  	 
CREATE OR REPLACE FUNCTION nextidfunc(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR
)
  RETURNS INTEGER AS $body$
DECLARE
          o_NextIDFunc INTEGER;
	  dummy INTEGER;
BEGIN
    o_NextIDFunc := nextid(p_AD_Sequence_ID, p_System);
    RETURN o_NextIDFunc;
END;
$body$ LANGUAGE plpgsql;


create or replace FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMERIC,
	p_C_Currency_ID	IN	NUMERIC
)
RETURNS NUMERIC AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Calculate Allocated Payment Amount in Payment Currency
 * Description:
    --
    SELECT paymentAllocated(C_Payment_ID,C_Currency_ID), PayAmt, IsAllocated
    FROM C_Payment_v 
    WHERE C_Payment_ID<1000000;
    --
    UPDATE C_Payment_v 
    SET IsAllocated=CASE WHEN paymentAllocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE C_Payment_ID>=1000000;
 
 ************************************************************************/
DECLARE
	v_AllocatedAmt		NUMERIC := 0;
    	v_PayAmt        	NUMERIC;
    	r   			RECORD;
BEGIN
    --  Charge - nothing available
    SELECT 
      INTO v_PayAmt MAX(PayAmt) 
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    
    IF (v_PayAmt IS NOT NULL) THEN
        RETURN v_PayAmt;
    END IF;
    
	--	Calculate Allocated Amount
	FOR r IN
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
			FROM	C_AllocationLine al
	          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
			WHERE	al.C_Payment_ID = p_C_Payment_ID
          	AND   a.IsActive='Y'
	LOOP
		v_AllocatedAmt := v_AllocatedAmt
			+ currencyConvert(r.Amount, r.C_Currency_ID, p_C_Currency_ID, r.DateTrx, null, r.AD_Client_ID, r.AD_Org_ID);
	END LOOP;
	--	Round to penny
	v_AllocatedAmt := ROUND(COALESCE(v_AllocatedAmt,0), 2);
	RETURN	v_AllocatedAmt;
END;

$body$ LANGUAGE plpgsql;

 	  	 


create or replace FUNCTION  paymentAvailable
(
	p_C_Payment_ID	IN	NUMERIC
)
RETURNS NUMERIC AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Calculate Available Payment Amount in Payment Currency
 * Description:
 *		similar to C_Invoice_Open
 ************************************************************************/
DECLARE
	v_Currency_ID		NUMERIC(10);
	v_AvailableAmt		NUMERIC := 0;
    	v_IsReceipt         	C_Payment.IsReceipt%TYPE;
    	v_Amt               	NUMERIC := 0;
    	r   			RECORD;

BEGIN
    --  Charge - fully allocated
    SELECT MAX(PayAmt) 
      INTO v_Amt
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_Amt IS NOT NULL) THEN
        RETURN 0;
    END IF;

	--	Get Currency
	SELECT	C_Currency_ID, PayAmt, IsReceipt
	  INTO	v_Currency_ID, v_AvailableAmt, v_IsReceipt
	FROM	C_Payment_v     -- corrected for AP/AR
	WHERE	C_Payment_ID = p_C_Payment_ID;
--  DBMS_OUTPUT.PUT_LINE('== C_Payment_ID=' || p_C_Payment_ID || ', PayAmt=' || v_AvailableAmt || ', Receipt=' || v_IsReceipt);

	--	Calculate Allocated Amount
	FOR r IN
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
	        INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
          	AND   a.IsActive='Y'
	LOOP
        v_Amt := currencyConvert(r.Amount, r.C_Currency_ID, v_Currency_ID, r.DateTrx, null, r.AD_Client_ID, r.AD_Org_ID);
	    v_AvailableAmt := v_AvailableAmt - v_Amt;
--      DBMS_OUTPUT.PUT_LINE('  Allocation=' || a.Amount || ' - Available=' || v_AvailableAmt);
	END LOOP;
	--	Ignore Rounding
	IF (v_AvailableAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_AvailableAmt := 0;
	END IF;
	--	Round to penny
	v_AvailableAmt := ROUND(COALESCE(v_AvailableAmt,0), 2);
	RETURN	v_AvailableAmt;
END;

$body$ LANGUAGE plpgsql;


create or replace FUNCTION  paymenttermDiscount
(
	Amount			NUMERIC,
    	Currency_ID     	NUMERIC,
	PaymentTerm_ID		NUMERIC,
	DocDate			timestamp with time zone,
	PayDate			timestamp with time zone
)
RETURNS NUMERIC AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Calculate Discount
 * Description:
 *	Calculate the allowable Discount Amount of the Payment Term
 *
 *	Test:	SELECT paymenttermDiscount(110, 103, 106, now(), now()) FROM TEST; => 2.20
 ************************************************************************/

DECLARE
	Discount		NUMERIC := 0;
	Discount1Date		timestamp with time zone;
	Discount2Date		timestamp with time zone;
	Add1Date		NUMERIC := 0;
	Add2Date		NUMERIC := 0;
	p   			RECORD;
BEGIN
	--	No Data - No Discount
	IF (Amount IS NULL OR PaymentTerm_ID IS NULL OR DocDate IS NULL) THEN
		RETURN 0;
	END IF;

	FOR p IN 
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID
	LOOP	--	for convineance only
		Discount1Date := TRUNC(DocDate + p.DiscountDays + p.GraceDays);
		Discount2Date := TRUNC(DocDate + p.DiscountDays2 + p.GraceDays);

		--	Next Business Day
		IF (p.IsNextBusinessDay='Y') THEN
			Discount1Date := nextBusinessDay(Discount1Date, p.AD_Client_ID);
			Discount2Date := nextBusinessDay(Discount2Date, p.AD_Client_ID);
		END IF;

		--	Discount 1
		IF (Discount1Date >= TRUNC(PayDate)) THEN
			Discount := Amount * p.Discount / 100;
		--	Discount 2
		ELSIF (Discount2Date >= TRUNC(PayDate)) THEN
			Discount := Amount * p.Discount2 / 100;
		END IF;	
	END LOOP;
	--
    RETURN ROUND(COALESCE(Discount,0), 2);	--	fixed rounding
END;

$body$ LANGUAGE plpgsql;
 	  	 


create or replace FUNCTION    paymenttermDueDate
(
	PaymentTerm_ID	IN	NUMERIC,
	DocDate			IN	timestamp with time zone
)
RETURNS timestamp with time zone AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Get Due timestamp with time zone
 * Description:
 *	Returns the due timestamp with time zone
 * Test:
 *	select paymenttermDueDate(106, now()) from Test; => now()+30 days
 ************************************************************************/
DECLARE
 	Days				NUMERIC := 0;
	DueDate				timestamp with time zone := TRUNC(DocDate);
	--
	FirstDay			timestamp with time zone;
	NoDays				NUMERIC;
	p   			RECORD;
BEGIN
	FOR p IN 
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID
	LOOP	--	for convineance only
		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN		
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := TRUNC(DocDate) - FirstDay;
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := ADD_MONTHS(DueDate, p.FixMonthOffset);
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := ADD_MONTHS(DueDate, 1);
			END IF;
		ELSE
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;

	RETURN DueDate;
END;

$body$ LANGUAGE plpgsql;

 	  	 


create or replace FUNCTION   paymenttermDueDays 
(
	PaymentTerm_ID	IN	NUMERIC,
	DocDate			IN	timestamp with time zone,
	PayDate			IN	timestamp with time zone
)
RETURNS INTEGER AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Get Due Days
 * Description:
 *	Returns the days due (positive) or the days till due (negative)
 *	Grace days are not considered!
 *	If record is not found it assumes due immediately
 *
 *	Test:	SELECT paymenttermDueDays(103, now(), now());
 *
 * Contributor(s): Carlos Ruiz - globalqss - match with SQLJ version
 ************************************************************************/
DECLARE
 	Days			NUMERIC := 0;
	DueDate			timestamp with time zone := NULL;
	calDueDate		timestamp with time zone;
	FixMonthOffset		C_PaymentTerm.FixMonthOffset%TYPE;
	MaxDayCut		NUMERIC;
	MaxDay			NUMERIC;
	v_PayDate		timestamp with time zone;
	p   			RECORD;
	--
	FirstDay			timestamp with time zone;
	NoDays				NUMERIC;
BEGIN

    	IF PaymentTerm_ID = 0 OR DocDate IS NULL THEN
	    RETURN 0;
	END IF;

    	v_PayDate := PayDate;
	IF v_PayDate IS NULL THEN
	    v_PayDate := TRUNC(now());
	END IF;

	FOR p IN 
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID
	LOOP	--	for convineance only

		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := extract (day from (TRUNC(DocDate) - FirstDay));
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := DueDate + (p.FixMonthOffset || ' month')::interval;
			
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := DueDate + '1 month'::interval;
			END IF;
			-- raise notice 'FirstDay: %, NoDays: %, DueDate: %', FirstDay, NoDays, DueDate;
			
			calDueDate := TRUNC(DocDate);
			MaxDayCut := extract (day from (cast(date_trunc('month', calDueDate) + '1 month'::interval as date) - 1));
			-- raise notice 'last day(MaxDayCut): %' , MaxDayCut;

			IF p.FixMonthCutoff > MaxDayCut THEN
				-- raise notice 'p.FixMonthCutoff > MaxDayCut';
			    calDueDate := cast(date_trunc('month', TRUNC(calDueDate)) + '1 month'::interval as date) - 1;
				-- raise notice 'last day(calDueDate): %' , calDueDate;
			ELSE
			    -- set day fixmonthcutoff on duedate
			    calDueDate := TRUNC(calDueDate, 'MM') + (((p.FixMonthCutoff-1)|| ' days')::interval);
			    -- raise notice 'calDueDate: %' , calDueDate;
			    
			END IF;
			FixMonthOffset := p.FixMonthOffset;
			IF DocDate > calDueDate THEN
			    FixMonthOffset := FixMonthOffset + 1;
				raise notice 'FixMonthOffset: %' , FixMonthOffset;
			END IF;

			calDueDate := calDueDate + (FixMonthOffset || ' month')::interval;
			-- raise notice 'calDueDate: %' , calDueDate;

			MaxDay := extract (day from (cast(date_trunc('month', calDueDate) + '1 month'::interval as date) - 1));


			IF    (p.FixMonthDay > MaxDay)    --	32 -> 28
			   OR (p.FixMonthDay >= 30 AND MaxDay > p.FixMonthDay) THEN  	--	30 -> 31
				calDueDate := TRUNC(calDueDate, 'MM') + (((MaxDay-1)|| ' days')::interval);
				-- raise notice 'calDueDate: %' , calDueDate;			
			ELSE
				calDueDate := TRUNC(calDueDate, 'MM') + (((p.FixMonthDay-1)|| ' days')::interval);
				-- raise notice 'calDueDate: %' , calDueDate;			
			END IF;
			DueDate := calDueDate; 

		ELSE
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;

    IF DueDate IS NULL THEN
	    RETURN 0;
	END IF;


	Days := EXTRACT(day from (TRUNC(v_PayDate) - DueDate));
	RETURN Days;
END;

$body$ LANGUAGE plpgsql;


 	  	 


CREATE OR REPLACE FUNCTION      ProductAttribute 
(
    p_M_AttributeSetInstance_ID     NUMERIC
)
RETURNS VARCHAR AS $body$

/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title: Return Instance Attribute Info
 * Description:
 *  
 * Test:
    SELECT ProductAttribute (M_AttributeSetInstance_ID) 
    FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0
    --
    SELECT p.Name
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    SELECT p.Name || ProductAttribute (il.M_AttributeSetInstance_ID) 
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    
 ************************************************************************/

	
DECLARE

    v_Name          VARCHAR(2000) := '';
    v_NameAdd       VARCHAR(2000) := '';
    --
    v_Lot           M_AttributeSetInstance.Lot%TYPE;
    v_LotStart      M_AttributeSet.LotCharSOverwrite%TYPE;
    v_LotEnd        M_AttributeSet.LotCharEOverwrite%TYPE;
    v_SerNo         M_AttributeSetInstance.SerNo%TYPE;
    v_SerNoStart    M_AttributeSet.SerNoCharSOverwrite%TYPE;
    v_SerNoEnd      M_AttributeSet.SerNoCharEOverwrite%TYPE;
    v_GuaranteeDate M_AttributeSetInstance.GuaranteeDate%TYPE;
    
    r   RECORD;
    --

BEGIN
    --  Get Product Attribute Set Instance
    IF (p_M_AttributeSetInstance_ID > 0) THEN
        SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate,
            COALESCE(a.SerNoCharSOverwrite, '#'::CHAR(1)), COALESCE(a.SerNoCharEOverwrite, ''::CHAR(1)),
            COALESCE(a.LotCharSOverwrite, '«'::CHAR(1)), COALESCE(a.LotCharEOverwrite, '»'::CHAR(1))
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
        
        FOR r IN
	     SELECT ai.Value, a.Name
	        FROM M_AttributeInstance ai
	        INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
        	WHERE ai.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID
    	LOOP
            v_NameAdd := v_NameAdd || r.Name || ':' || r.Value || ' ';
        END LOOP;
        --
        IF (LENGTH(v_NameAdd) > 0) THEN
            v_Name := v_Name || ' (' || TRIM(v_NameAdd) || ')';
	ELSE 
	    v_Name := NULL;
        END IF;
    END IF;
    RETURN v_Name;
END;

$body$ LANGUAGE plpgsql;
 	  	 



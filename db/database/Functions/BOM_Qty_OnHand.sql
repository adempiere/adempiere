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

/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 victor.perez@e-evolution.com, e-Evolution
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 *	Return quantity reserved for Product
 */
CREATE OR REPLACE FUNCTION prodQtyReserved
(
	p_Product_ID 		numeric,
    p_Warehouse_ID		numeric,
	p_Locator_ID		numeric
)
RETURNS numeric
AS
$BODY$
DECLARE
	v_Warehouse_ID		numeric;
 	v_Quantity			numeric := 99999;	--	unlimited
	v_IsBOM	 			CHAR(1);
	v_IsStocked			CHAR(1);
	v_ProductType		CHAR(1);
 	v_ProductQty		numeric;
	v_StdPrecision		int;
BEGIN
	--	Check Parameters
	v_Warehouse_ID := p_Warehouse_ID;
	IF (v_Warehouse_ID IS NULL) THEN
		IF (p_Locator_ID IS NULL) THEN
			RETURN 0;
		ELSE
			SELECT 	MAX(M_Warehouse_ID) INTO v_Warehouse_ID
			FROM	M_LOCATOR
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
	FROM M_PRODUCT
	WHERE M_Product_ID=p_Product_ID;
 --
	EXCEPTION	--	not found
	WHEN OTHERS THEN
	RETURN 0;
	END;

 --	No reservation for non-stocked
	IF (v_IsStocked='Y') THEN
 --	Get ProductQty
	SELECT -1*COALESCE(SUM(MovementQty), 0)
	INTO	v_ProductQty
	FROM M_ProductionLine p
	WHERE M_Product_ID=p_Product_ID AND MovementQty < 0 AND p.Processed = 'N'
	AND EXISTS (SELECT * FROM M_LOCATOR l WHERE p.M_Locator_ID=l.M_Locator_ID
	AND l.M_Warehouse_ID=v_Warehouse_ID);
 --
	RETURN v_ProductQty;
	END IF;
	--	Unlimited (e.g. only services)
	IF (v_Quantity = 99999) THEN
		RETURN 0;
	END IF;

	IF (v_Quantity > 0) THEN
		--	Get Rounding Precision for Product
		SELECT 	COALESCE(MAX(u.StdPrecision), 0)
		  INTO	v_StdPrecision
		FROM 	C_UOM u, M_PRODUCT p
		WHERE 	u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=p_Product_ID;
		--
		RETURN ROUND (v_Quantity, v_StdPrecision);
	END IF;
	RETURN 0;
END;
$BODY$
LANGUAGE 'plpgsql' ;

 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************
 * 
 * Title.........: 
 * Description...: Return quantity on hand for BOM
 *                                     Prd, Wrh, Lct
 * Test..........: SELECT BomQtyOnHand(123, 103, 101)$$   => 20.0000000000
 *                 SELECT BomQtyOnHand(133, 103, null)$$  => 30.0000000000
 *                 SELECT BomQtyOnHand(133, null, 101)$$  => 30.0000000000
 *                 SELECT BomQtyOnHand(133, null, null)$$ => 0.0000000000
 *                 SELECT Bomqtyonhand(0, null, 101)$$    => 0.0000000000
 * 
 *                 SET @result = 0.0;
 *                 Call BomQtyOnHandProc(123, 103, 101, @result)       => 
 *                 SELECT @result;
 *                 +---------------+
 *                 | @result       |
 *                 +---------------+
 *                 | 40.0000000000 | 
 *                 +---------------+
 *
 *                 M_Product_ID |  Name | IsBOM | ProductType | IsStocked
 *                          123 |   Oak |     N |           I | Y
 *                          124 |   Elm |     N |           I | Y
 *                          133 | PChair|     Y |           I | Y
 *                 M_Warehouse
 *                  103 = HQ Warehouse 
 *                  104 = Store Central
 *                  M_Locator
 *                   101 = Default HQ Locator 
 *                   102 = Default Store Locator
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP PROCEDURE IF EXISTS BomQtyOnHandProc $$

-- ## Create statement
CREATE PROCEDURE BomQtyOnHandProc (
      Product_ID    DECIMAL(10, 0),
      Warehouse_ID  DECIMAL(10, 0),
      Locator_ID    DECIMAL(10, 0),  -- Only used, if warehouse is null
 OUT  BomQtyOnHand  DECIMAL(20, 10)
)
  DETERMINISTIC
proc: BEGIN
  DECLARE myWarehouse_ID DECIMAL(10, 0);
  DECLARE Quantity DECIMAL(20, 10) DEFAULT 99999.99; -- unlimited [99999]
  DECLARE IsBOM CHAR(1);
  DECLARE IsStocked CHAR(1);
  DECLARE ProductType CHAR(1);
  DECLARE ProductQty DECIMAL(20, 10);
  DECLARE StdPrecision DECIMAL(20, 10);
  DECLARE returnValue DECIMAL(20, 10);
  
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare cursor "BOM Product info" and all need for this Cursor Variables
  -- bom_M_ProductBOM_ID, bom_BomQty, bom_IsBOM, bom_IsStocked, bom_ProductType
  DECLARE bom_M_ProductBOM_ID DECIMAL(10, 0);
  DECLARE bom_BomQty DECIMAL(20, 10);
  DECLARE bom_IsBOM CHAR(1);
  DECLARE bom_IsStocked CHAR(1);
  DECLARE bom_ProductType CHAR(1);
  
  DECLARE CUR_BOM CURSOR FOR
       SELECT bl.M_Product_ID AS M_ProductBOM_ID
          , CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS BomQty
          , p.IsBOM, p.IsStocked, p.ProductType 
        FROM PP_Product_BOM b
        INNER JOIN M_Product p ON (p.M_Product_ID=b.M_Product_ID)
        INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
		WHERE b.M_Product_ID = Product_ID;
   
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' 
      SET done = TRUE;
        
  -- Check Parameters
  SET myWarehouse_ID = Warehouse_ID;
  IF (myWarehouse_ID IS NULL) THEN
    IF (Locator_ID IS NULL) THEN
      SET BomQtyOnHand = 0;
      LEAVE proc;
    ELSE
      SELECT M_Warehouse_ID 
       INTO myWarehouse_ID
      FROM M_Locator
       WHERE M_Locator_ID=Locator_ID;
    END IF;
  END IF;
  IF (myWarehouse_ID IS NULL) THEN
    SET BomQtyOnHand = 0;
    LEAVE proc;
  END IF;

  -- Check, if product exists and if it is stocked
  BEGIN
    DECLARE EXIT HANDLER FOR NOT FOUND
      SET done = TRUE;
    		
    SELECT p.IsBOM, p.ProductType, p.IsStocked
      INTO IsBOM, ProductType, IsStocked
    FROM M_Product p
     WHERE p.M_Product_ID=Product_ID;
  END;
  IF done THEN
    SET BomQtyOnHand = 0;
    LEAVE proc;
  END IF;

  -- Unlimited capacity if no item
  IF (IsBOM='N' AND (ProductType<>'I' OR IsStocked='N')) THEN
    SET BomQtyOnHand = Quantity;
    LEAVE proc;
    -- Stocked item
  ELSEIF (IsStocked='Y') THEN
    -- Get ProductQty
    SELECT COALESCE(SUM(QtyOnHand), 0)
      INTO ProductQty
    FROM M_Storage s
    WHERE M_Product_ID=Product_ID
     AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
       AND l.M_Warehouse_ID=myWarehouse_ID);
    --
    SET BomQtyOnHand = ProductQty;
    LEAVE proc;
  END IF;
	
  OPEN CUR_BOM;
  myLoop: LOOP
        FETCH CUR_BOM INTO bom_M_ProductBOM_ID, bom_BomQty, bom_IsBOM, bom_IsStocked, bom_ProductType;

        IF done THEN
            CLOSE CUR_BOM;
            LEAVE myLoop;
        END IF;

        -- Stocked Items "leaf node"
		IF (bom_ProductType = 'I' AND bom_IsStocked = 'Y') THEN
			-- Get ProductQty
			SELECT COALESCE(SUM(QtyOnHand), 0)
			  INTO ProductQty
			FROM   M_Storage s
			WHERE M_Product_ID=bom_M_ProductBOM_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=myWarehouse_ID);
			-- Get Rounding Precision
			SELECT COALESCE(MAX(u.StdPrecision), 0)
			  INTO StdPrecision
			FROM C_UOM u, M_Product p
			WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom_M_ProductBOM_ID;
			-- How much can we make with this product
			SET ProductQty = ROUND (ProductQty/bom_BOMQty, StdPrecision);
			-- How much can we make overall
			IF (ProductQty < Quantity) THEN
				SET Quantity = ProductQty;
			END IF;
		-- Another BOM
		ELSEIF (bom_IsBOM = 'Y') THEN
            CALL BomQtyOnHandProc (
                bom_M_ProductBOM_ID,
                myWarehouse_ID,
                Locator_ID,
                ProductQty
            );
			-- How much can we make overall
			IF (ProductQty < Quantity) THEN
				SET Quantity = ProductQty;
			END IF;
		END IF;

    END LOOP myLoop;

	IF (Quantity > 0) THEN
		-- Get Rounding Precision for Product
		SELECT COALESCE(MAX(u.StdPrecision), 0)
		  INTO StdPrecision
		FROM   C_UOM u, M_Product p
		WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=Product_ID;
		--
		SET BomQtyOnHand = ROUND (Quantity, StdPrecision);
        LEAVE PROC;
	END IF;
	SET BomQtyOnHand = 0;
END$$


DROP FUNCTION IF EXISTS BomQtyOnHand
$$

CREATE FUNCTION BomQtyOnHand (
  Product_ID    DECIMAL(10, 0),
  Warehouse_ID  DECIMAL(10, 0),
  Locator_ID    DECIMAL(10, 0)  -- Only used, if warehouse is null
)
RETURNS DECIMAL(20, 10)
BEGIN
  DECLARE BomQtyOnHand DECIMAL(20, 10);
  CALL BomQtyOnHandProc (
    Product_ID,
    Warehouse_ID,
    Locator_ID,
    BomQtyOnHand
  );
  RETURN BomQtyOnHand;
END$$
DELIMITER ;
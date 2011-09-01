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
 * Description...: Return quantity Ordered for BOM
 *                                         Prd, ASI, Wrh, Lct
 * Test..........: SELECT bomQtyOrderedASI(123,   0, 103, 101)$$   => 0.0000000000
 *                 SELECT bomQtyOrderedASI(133,   0, 103, null)$$  => 0.0000000000
 *                 SELECT bomQtyOrderedASI(133,   0, null, 101)$$  => 0.0000000000
 *                 SELECT bomQtyOrderedASI(133,   0, null, null)$$ => 0.0000000000
 *                 SELECT bomQtyOrderedASI(  0,   0, null, 101)$$  => 0.0000000000
 * 
 *                 SET @result = 0.0;
 *                 Call bomQtyOrderedASIProc(123, 103, 101, @result)  => 
 *                 SELECT @result;
 *                 +---------------+
 *                 | @result       |
 *                 +---------------+
 *                 |  0.0000000000 |
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
DROP PROCEDURE IF EXISTS bomQtyOrderedASIProc $$

-- ## Create statement
CREATE PROCEDURE bomQtyOrderedASIProc (
       Product_ID               DECIMAL(10, 0),
       AttributeSetInstance_ID  DECIMAL(10, 0),
       Warehouse_ID             DECIMAL(10, 0),
       Locator_ID               DECIMAL(10, 0),  -- Only used, if warehouse is null
  OUT  BomQtyOrderedASI         DECIMAL(20, 10)
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
        FROM PP_PRODUCT_BOM b
        INNER JOIN M_PRODUCT p ON (p.M_Product_ID=b.M_Product_ID)
        INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
		WHERE b.M_Product_ID = Product_ID;
   
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' 
      SET done = TRUE;
        
  -- Check Parameters
  SET myWarehouse_ID = Warehouse_ID;
  IF (myWarehouse_ID IS NULL) THEN
    IF (Locator_ID IS NULL) THEN
      SET BomQtyOrderedASI = 0;
      LEAVE proc;
    ELSE
      SELECT M_Warehouse_ID INTO myWarehouse_ID
      FROM M_LOCATOR
       WHERE M_Locator_ID=Locator_ID;
    END IF;
  END IF;
  IF (myWarehouse_ID IS NULL) THEN
    SET BomQtyOrderedASI = 0;
    LEAVE proc;
  END IF;
  
  -- Check, if product exists and if it is stocked
  BEGIN
    DECLARE EXIT HANDLER FOR NOT FOUND
      SET done = TRUE;
    		
    SELECT p.IsBOM, p.ProductType, p.IsStocked
      INTO IsBOM, ProductType, IsStocked
    FROM M_PRODUCT p
     WHERE p.M_Product_ID=Product_ID;
  END;
  IF done THEN
    SET BomQtyOrderedASI = 0;
    LEAVE proc;
  END IF;
  
  -- No reservation for non-stocked
  IF (IsBOM='N' AND (ProductType<>'I' OR IsStocked='N')) THEN
    SET BomQtyOrderedASI = 0;
    LEAVE proc;
    -- Stocked item
  ELSEIF (IsStocked='Y') THEN
    -- Get ProductQty
    SELECT COALESCE(SUM(s.QtyOrdered), 0)
      INTO ProductQty
    FROM M_STORAGE s
    WHERE s.M_Product_ID=Product_ID
     AND EXISTS (SELECT * FROM M_LOCATOR l WHERE s.M_Locator_ID=l.M_Locator_ID
       AND l.M_Warehouse_ID=myWarehouse_ID)
     AND (s.M_AttributeSetInstance_ID = AttributeSetInstance_ID OR COALESCE(AttributeSetInstance_ID, 0) = 0);
    --
    SET BomQtyOrderedASI = ProductQty;
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
      SELECT COALESCE(SUM(s.QtyOrdered), 0)
        INTO ProductQty
      FROM   M_STORAGE s
      WHERE s.M_Product_ID=bom_M_ProductBOM_ID
        AND EXISTS (SELECT * FROM M_LOCATOR l WHERE s.M_Locator_ID=l.M_Locator_ID
                     AND l.M_Warehouse_ID=myWarehouse_ID)
        AND (s.M_AttributeSetInstance_ID = AttributeSetInstance_ID OR COALESCE(AttributeSetInstance_ID, 0) = 0);
      -- Get Rounding Precision
      SELECT COALESCE(MAX(u.StdPrecision), 0)
        INTO StdPrecision
      FROM C_UOM u, M_PRODUCT p
      WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom_M_ProductBOM_ID;
      -- How much can we make with this product
      SET ProductQty = ROUND (ProductQty/bom_BOMQty, StdPrecision);
      -- How much can we make overall
      IF (ProductQty < Quantity) THEN
        SET Quantity = ProductQty;
      END IF;
      -- Another BOM
    ELSEIF (bom_IsBOM = 'Y') THEN
      CALL BomQtyOrderedASIProc (
                bom_M_ProductBOM_ID,
                AttributeSetInstance_ID,
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
  
  -- Unlimited (e.g. only services)
  IF (Quantity = 99999) THEN
    SET BomQtyOrderedASI = 0;
    LEAVE proc;
  END IF;
  
  IF (Quantity > 0) THEN
    -- Get Rounding Precision for Product
    SELECT COALESCE(MAX(u.StdPrecision), 0)
      INTO StdPrecision
    FROM   C_UOM u, M_PRODUCT p
    WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=Product_ID;
    --
    SET BomQtyOrderedASI = ROUND (Quantity, StdPrecision);
    LEAVE proc;
  END IF;
  
  SET BomQtyOrderedASI = 0;
END$$


DROP FUNCTION IF EXISTS bomQtyOrderedASI
$$

CREATE FUNCTION bomQtyOrderedASI(
  p_Product_ID               DECIMAL(10, 0), 
  p_AttributeSetInstance_ID  DECIMAL(10, 0), 
  p_Warehouse_ID             DECIMAL(10, 0), 
  p_Locator_ID               DECIMAL(10, 0)  
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_result DECIMAL(20, 10);
  
  CALL BomQtyOrderedProc (
    p_Product_ID,
    p_AttributeSetInstance_ID,
    p_Warehouse_ID,
    p_Locator_ID,
    v_result
  );
  
  return v_result;
END $$
DELIMITER ;
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
 * Title.........: Return Limit Price of Product/BOM
 * Description...: if not found: 0 
 * Test..........: SELECT bomPriceLimit(123, 105)$$ => 53.6000000000
 *                 SELECT bomPriceLimit(124, 105)$$ => 49.1000000000
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP PROCEDURE IF EXISTS bomPriceLimitProc $$

-- ## Create statement
CREATE PROCEDURE bomPriceLimitProc (
       Product_ID             DECIMAL(10, 0),
       PriceList_Version_ID   DECIMAL(10, 0),
  OUT  bomPriceLimit          DECIMAL(20, 10)
)
  DETERMINISTIC
proc: BEGIN
  DECLARE v_Price DECIMAL(20, 10);
  DECLARE v_ProductPrice DECIMAL(20, 10);
  
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare variables needed for the cursor
  DECLARE cur_M_ProductBOM_ID DECIMAL(10, 0);
  DECLARE cur_BomQty DECIMAL(20, 10);
  DECLARE cur_IsBOM CHAR(1);
  -- Declare cursor BOM Product info
  DECLARE CUR_BOM CURSOR FOR
    /*SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM
	  FROM M_PRODUCT_BOM b, M_PRODUCT p
      WHERE b.M_ProductBOM_ID=p.M_Product_ID
        AND b.M_Product_ID=Product_ID;*/
    SELECT bl.M_Product_ID AS M_ProductBOM_ID
      , CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS BomQty
      , p.IsBOM 
    FROM PP_PRODUCT_BOM b
    INNER JOIN M_PRODUCT p ON (p.M_Product_ID=b.M_Product_ID)
    INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
    WHERE b.M_Product_ID = Product_ID;

  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;

  -- Try to get price from pricelist directly
  SELECT COALESCE(pp.PriceLimit, 0)
    INTO v_Price
  FROM M_ProductPrice pp
  WHERE pp.M_PriceList_Version_ID=PriceList_Version_ID 
   AND pp.M_Product_ID=Product_ID;
  
  -- No Price - Check if BOM
  IF (v_Price = 0) THEN
    OPEN CUR_BOM;
  
    myLoop: LOOP
      FETCH CUR_BOM INTO cur_M_ProductBOM_ID, cur_BomQty, cur_IsBOM;

      IF done THEN
        CLOSE CUR_BOM;
        LEAVE myLoop;
      END IF;

      CALL bomPriceLimitProc (
         cur_M_ProductBOM_ID,
         PriceList_Version_ID,
         v_ProductPrice
      );
      SET v_Price = v_Price + (cur_BOMQty * v_ProductPrice);
    END LOOP myLoop;
  END IF;
  
  SET bomPriceLimit = v_Price;
END$$


DROP FUNCTION IF EXISTS bomPriceLimit $$

CREATE FUNCTION bomPriceLimit (
  p_Product_ID             DECIMAL(10, 0),
  p_PriceList_Version_ID   DECIMAL(10, 0)
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_result DECIMAL(20, 10);
  
  CALL bomPriceLimitProc (
    p_Product_ID,
    p_PriceList_Version_ID,
    v_result
  );
  
  return v_result;
END $$
DELIMITER ;
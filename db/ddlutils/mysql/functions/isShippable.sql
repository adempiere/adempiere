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
 * Title.........: IsShippable
 * Description...: Returns Y or N depending if this is a physical 'shippable' product or not.
 * Test..........: SELECT isshippable(136);  => N
 *                 SELECT isshippable(134);  => Y
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS isshippable;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION isshippable (
  product_id   DECIMAL(10, 0)
)
  RETURNS CHAR(1)
  DETERMINISTIC
BEGIN
  DECLARE name_val VARCHAR(255);
  DECLARE v_IsStocked     CHAR(1);
  DECLARE v_IsBom         CHAR(1);
  DECLARE v_ProductType   CHAR(1);
  DECLARE v_return        CHAR(1);
	
  SELECT IsStocked, IsBom, ProductType 
   INTO v_IsStocked, v_IsBom, v_ProductType
  FROM M_Product 
  WHERE M_Product_ID=product_id;
  
  IF (v_IsStocked='Y' AND v_ProductType='I' AND v_IsBom='N') THEN
    SET v_return = 'Y';
  ELSE
    SET v_return = 'N';
  END IF;
  
  return v_return;
END$$

DELIMITER ;
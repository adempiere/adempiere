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
 * Description...: Return number of allocated products of the specific product in the given warehouse. 
 * Test..........: SELECT get_allocated_on_order(106, ); => 
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS get_allocated_on_order;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION get_allocated_on_order (
  p_product_id     DECIMAL(10, 0), 
  p_warehouse_id   DECIMAL(10, 0)
)
  RETURNS DECIMAL(22, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_sum DECIMAL(22, 10);
  --  Get Product Attribute Set Instance
  SELECT sum(ol.qtyallocated) into v_sum 
  FROM C_OrderLine ol
  JOIN C_Order o on (o.C_Order_ID=ol.C_Order_ID)
  WHERE M_Product_ID=p_product_id 
    AND COALESCE(ol.M_Warehouse_ID, o.M_Warehouse_ID)=p_warehouse_id;

  RETURN v_sum;
END$$
DELIMITER ;
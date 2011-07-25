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
 * Title.........: is_inout_candidate_order
 * Description...: Returns Y or N depending if this order can be shipped or not. 
 *                 Delivery Policy, Shipping rule etc is considered.
 * 
 * Test..........: SELECT is_inout_candidate_order(100);   => N
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS is_inout_candidate_order;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION is_inout_candidate_order (
  p_order_id DECIMAL(10, 0)
)
  RETURNS CHAR(1)
  DETERMINISTIC
BEGIN
  DECLARE v_lines_ready	   DECIMAL(22, 10);
  DECLARE v_lines_total	   DECIMAL(22, 10);
  DECLARE v_deliveryRule   CHAR;
  
  -- Get order info
  -- Only orders that are complete, not delivered, delivery rule anything else than manual and is a sales order
  -- can be inout candidates
  SELECT ord.DeliveryRule INTO v_deliveryRule 
  FROM C_Order ord 
  WHERE ord.c_order_id=p_order_id 
    AND ord.docstatus = 'CO'
    AND ord.isdelivered = 'N'
    AND ord.deliveryrule <> 'M'
    AND (ord.c_doctype_id IN ( SELECT c_doctype.c_doctype_id 
                               FROM c_doctype
				               WHERE c_doctype.docbasetype = 'SOO' 
				                AND c_doctype.docsubtypeso NOT IN('ON','OB','WR')
				             ));
  
  IF (v_deliveryRule IS NULL) THEN
    RETURN 'N';
  END IF;
  
  IF (v_deliveryRule='F') THEN 
    RETURN 'Y'; 
  END IF; -- Force
  
  -- Check lines
  SELECT sum(is_inout_candidate_orderline(ol.c_orderline_id)), sum(1) 
       INTO v_lines_ready, v_lines_total
  FROM c_orderline ol 
  WHERE ol.c_order_id=p_order_id;

  IF (v_deliveryRule='L') THEN      -- Complete Line
    IF (v_lines_ready > 0) THEN RETURN 'Y'; END IF;
  ELSEIF (v_deliveryRule='L') THEN  -- Availability
    IF (v_lines_ready > 0) THEN RETURN 'Y'; END IF;
  ELSEIF (v_deliveryRule='L') THEN  -- Complete Order
    IF (v_lines_ready = v_lines_total) THEN RETURN 'Y'; END IF;
  END IF;
  
  return 'N';
END$$

DELIMITER ;
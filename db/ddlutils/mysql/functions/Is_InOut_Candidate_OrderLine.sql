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
 * Title.........: Is_InOut_Candidate_OrderLine
 * Description...: Returns Y or N depending if Order Line can be shipped or not. 
 *                 Delivery Policy, Shipping rule etc is considered.
 * Test..........: SELECT is_inout_candidate_orderline(100);  => 
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS is_inout_candidate_orderline;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION is_inout_candidate_orderline (
  c_order_line_id DECIMAL(10, 0)
)
  RETURNS DECIMAL(22, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_qtyordered     DECIMAL(22, 10);
  DECLARE v_qtydelivered   DECIMAL(22, 10);
  DECLARE v_qtyallocated   DECIMAL(22, 10);
  DECLARE v_qtyonhand      DECIMAL(22, 10);
  DECLARE v_qtytodeliver   DECIMAL(22, 10);
  DECLARE v_qtyreserved    DECIMAL(22, 10);
  DECLARE v_order_id       DECIMAL(10, 0);
  DECLARE v_inoutExists	   CHAR(1);
  DECLARE v_warehouse_id   DECIMAL(10, 0);
  DECLARE v_product_id     DECIMAL(10, 0);
  DECLARE v_orderReady     DECIMAL(22, 10);
  DECLARE v_isShippable    CHAR(1);
  DECLARE v_deliveryRule   CHAR(1);
  DECLARE v_deliveryPolicy CHAR(1);
  DECLARE v_return	       CHAR(1);

  SELECT qtyordered, qtydelivered, qtyallocated, qtyreserved, c_order_id,
	     get_delivery_policy(m_warehouse_id), isshippable(m_product_id),
	     m_warehouse_id, m_product_id
  INTO
	     v_qtyordered, v_qtydelivered, v_qtyallocated, v_qtyreserved, v_order_id,
	     v_deliveryPolicy, v_isShippable,
	     v_warehouse_id, v_product_id
  FROM C_OrderLine 
  WHERE C_OrderLine_ID=c_order_line_id;

  -- If all is already delivered then it's not a candidate
  IF v_qtyordered = v_qtydelivered THEN
    -- RAISE NOTICE 'All is delivered';
    RETURN 0;
  END IF;
  
  -- Non shippable (ie non physical items) are always inout candidate
  IF v_isShippable='N' THEN
    -- RAISE NOTICE 'Non physical item, always deliverable';
    RETURN 1;
  END IF;

  SELECT 1 INTO v_inoutExists 
  FROM m_inoutline iol
  JOIN m_inout io ON iol.m_inout_id = io.m_inout_id
  WHERE iol.c_orderline_id = c_order_line_id 
   AND (io.docstatus IN ('IP', 'WC', 'IN'));

  -- If an in-out line is in progress this is not a candidate
  IF v_inoutExists = 1 THEN
    -- RAISE NOTICE 'Already being shipped';
    RETURN 0;
  END IF;
  
  -- Check delivery rule
  SELECT o.DeliveryRule INTO v_deliveryRule
  FROM C_Order o 
  WHERE o.C_Order_ID=v_order_id;

  IF v_deliveryRule='F' THEN 
    -- RAISE NOTICE 'Delivery rule = Force';
    RETURN 1; 
  END IF; -- Force
  
  SET v_qtytodeliver = v_qtyordered - v_qtydelivered;
  IF v_qtytodeliver = 0 THEN
    -- RAISE NOTICE 'Nothing to deliver';
    RETURN 0;
  END IF;

  IF v_DeliveryPolicy = 'O' THEN -- Deliver in strict order, compare with qty allocated
  BEGIN
    -- RAISE NOTICE 'Delivery policy = Strict order';
    CASE v_deliveryRule
      WHEN 'L' THEN -- Complete line
        IF v_qtytodeliver = v_qtyallocated THEN 
          -- RAISE NOTICE 'Quantity to deliver = qty allocated';
          RETURN 1; 
        END IF;
      WHEN 'O' THEN -- Complete order
        IF v_qtytodeliver > v_qtyallocated THEN 
          -- RAISE NOTICE 'Not enough allocated for complete order';
          RETURN 0; 
        END IF;
      WHEN 'A' THEN -- Availability
        IF v_qtyallocated > 0 THEN 
          -- RAISE NOTICE 'Something to deliver';
          RETURN 1;
        END IF;
    END CASE;
    -- RAISE NOTICE 'No inout candidate';
    RETURN 0;
  END;
  END IF;
  
  IF v_DeliveryPolicy = 'N' THEN -- No hold, only compare with on hand
  BEGIN
    -- RAISE NOTICE 'Delivery policy = No hold';
    SELECT qtyonhand INTO v_qtyonhand 
    FROM m_product_stock_v 
    WHERE M_Product_ID=v_product_id 
     AND M_Warehouse_ID=v_warehouse_id;
    
    CASE v_deliveryRule
      WHEN 'L' THEN   -- Complete line
        IF (v_qtytodeliver = v_qtyreserved AND v_qtytodeliver <= v_qtyonhand) THEN RETURN 1; END IF;
      WHEN 'O' THEN   -- Complete order
        IF v_qtytodeliver < v_qtyreserved OR v_qtytodeliver >= v_qtyonhand THEN RETURN 0; END IF;
      WHEN 'A' THEN   -- Availability
        IF v_qtyonhand > 0 THEN RETURN 1; END IF;
    END CASE;
  END;
  END IF;

  -- RAISE NOTICE 'Default answer, something to deliver';
  return 1;
END$$

DELIMITER ;
/************************************************************************
 * Function Is_InOut_Candidate_OrderLine - Return Y or N depending if 
 * order line can be shipped or not. 
 * Delivery Policy, Shipping rule etc is considered.
 * Author: Daniel Tamm (usrdno)
************************************************************************/
CREATE OR REPLACE FUNCTION is_inout_candidate_orderline(c_order_line_id numeric)
  RETURNS numeric AS
$BODY$
DECLARE
	v_qtyordered	numeric;
	v_qtydelivered	numeric;
	v_qtyallocated	numeric;
	v_qtyonhand	numeric;
	v_qtytodeliver	numeric;
	v_qtyreserved	numeric;
	v_order_id	numeric;
	v_inoutExists	numeric;
	v_warehouse_id	numeric;
	v_product_id	numeric;
	v_orderReady	numeric;
	v_isShippable	character(1);
	v_deliveryRule	character(1);
	v_deliveryPolicy character(1);
	v_return	character(1);
BEGIN
	SELECT qtyordered, qtydelivered, qtyallocated, qtyreserved, c_order_id,
	       get_delivery_policy(m_warehouse_id), isshippable(m_product_id),
	       m_warehouse_id, m_product_id
		INTO
	       v_qtyordered, v_qtydelivered, v_qtyallocated, v_qtyreserved, v_order_id,
	       v_deliveryPolicy, v_isShippable,
	       v_warehouse_id, v_product_id
	       FROM
	       C_OrderLine where C_OrderLine_ID=c_order_line_id;

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

	SELECT 1 INTO v_inoutExists FROM m_inoutline iol
		      JOIN m_inout io ON iol.m_inout_id = io.m_inout_id
			WHERE iol.c_orderline_id = c_order_line_id AND (io.docstatus = ANY (ARRAY['IP'::bpchar, 'WC'::bpchar, 'IN'::bpchar]));

	-- If an in-out line is in progress this is not a candidate
	IF v_inoutExists = 1 THEN
		-- RAISE NOTICE 'Already being shipped';
		RETURN 0;
	END IF;
	
	-- Check delivery rule
	SELECT DeliveryRule INTO
		v_deliveryRule
		FROM 
		C_Order where C_Order_ID=v_order_id;

	IF v_deliveryRule='F' THEN 
		-- RAISE NOTICE 'Delivery rule = Force';
		RETURN 1; 
	END IF; -- Force

	v_qtytodeliver := v_qtyordered - v_qtydelivered;
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
		SELECT qtyonhand INTO 
			v_qtyonhand 
			FROM m_product_stock_v 
			WHERE M_Product_ID=v_product_id AND M_Warehouse_ID=v_warehouse_id;
	
		CASE v_deliveryRule
			WHEN 'L' THEN	-- Complete line
				IF (v_qtytodeliver = v_qtyreserved AND v_qtytodeliver <= v_qtyonhand) THEN RETURN 1; END IF;
			WHEN 'O' THEN	-- Complete order
				IF v_qtytodeliver < v_qtyreserved OR v_qtytodeliver >= v_qtyonhand THEN RETURN 0; END IF;
			WHEN 'A' THEN   -- Availability
				IF v_qtyonhand > 0 THEN RETURN 1; END IF;
		END CASE;
	END;
	END IF;

	-- RAISE NOTICE 'Default answer, something to deliver';
	return 1;	
END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;

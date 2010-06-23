/************************************************************************
 * Function Is_InOut_Candidate_Order - Return Y or N depending if 
 * this order can be shipped or not. 
 * Delivery Policy, Shipping rule etc is considered.
 * Author: Daniel Tamm (usrdno)
************************************************************************/
CREATE OR REPLACE FUNCTION is_inout_candidate_order
(
		p_order_id IN NUMBER
)
  RETURN CHAR AS

	v_lines_ready	NUMBER;
	v_lines_total	NUMBER;
	v_deliveryRule	CHAR;
BEGIN

	-- Get order info
	-- Only orders that are complete, not delivered, delivery rule anything else than manual and is a sales order
	-- can be inout candidates
	select DeliveryRule INTO v_deliveryRule FROM C_Order WHERE
			c_order_id=p_order_id AND
			docstatus = 'CO' AND 
			isdelivered = 'N' AND 
			deliveryrule <> 'M' AND 
			(c_doctype_id IN ( SELECT c_doctype.c_doctype_id FROM c_doctype
				WHERE c_doctype.docbasetype = 'SOO' AND c_doctype.docsubtypeso NOT IN('ON','OB','WR')));

	IF v_deliveryRule IS NULL THEN
		RETURN 'N';
	END IF;

	IF v_deliveryRule='F' THEN RETURN 'Y'; END IF; -- Force

	-- Check lines
	SELECT sum(is_inout_candidate_orderline(c_orderline_id)), sum(1) 
		INTO v_lines_ready, v_lines_total
	FROM c_orderline where c_order_id=p_order_id;

	CASE v_deliveryRule
		WHEN 'L' THEN -- Complete line
			IF v_lines_ready > 0 THEN RETURN 'Y'; END IF;
	  WHEN 'A' THEN -- Availability 
			IF v_lines_ready > 0 THEN RETURN 'Y'; END IF;
		WHEN 'O' THEN -- Complete order
			IF v_lines_ready = v_lines_total THEN RETURN 'Y'; END IF;
	END CASE;
	
	return 'N';
END;
/

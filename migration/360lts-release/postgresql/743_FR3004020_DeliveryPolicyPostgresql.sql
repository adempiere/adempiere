-- Adjust, alter view M_Product_Stock_v
DROP VIEW IF EXISTS m_product_stock_v;
CREATE OR REPLACE VIEW m_product_stock_v AS 
 SELECT ms.isactive, ms.created, ms.createdby, ms.updated, ms.updatedby, 
	ms.m_product_id, mp.value, mp.name, mp.help, 
	ms.qtyonhand - ms.qtyreserved AS qtyavailable, 
	ms.qtyonhand, ms.qtyreserved, 
	ms.qtyallocated, 
	mp.description, 
	mw.name AS warehouse, 
	mw.m_warehouse_id, 
	mw.ad_client_id, 
	mw.ad_org_id,
	mp.documentnote
   FROM m_storage ms
   JOIN m_product mp ON ms.m_product_id = mp.m_product_id
   JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
   JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
  ORDER BY mw.name;

ALTER TABLE m_product_stock_v OWNER TO adempiere;

-- Create function isShippable
CREATE OR REPLACE FUNCTION isshippable(product_id numeric)
  RETURNS character AS
$BODY$
DECLARE
	v_IsStocked	character(1);
	v_IsBom		character(1);
	v_ProductType	character(1);
	v_return	character(1);
BEGIN
	IF product_id = NULL THEN
		return 'N';
	END IF;
	
	SELECT IsStocked, IsBom, ProductType 
	INTO v_IsStocked, v_IsBom, v_ProductType
	FROM M_Product WHERE M_Product_ID=product_id;

	IF (v_IsStocked='Y' AND v_ProductType='I' AND v_IsBom='N') THEN
		v_return := 'Y';
	ELSE
		v_return := 'N';
	END IF;
	
	return v_return;	
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION isshippable(numeric) OWNER TO adempiere;

-- Function to get delivery policy

CREATE OR REPLACE FUNCTION get_delivery_policy(warehouse_id numeric)
  RETURNS character AS
$BODY$
DECLARE
	v_orgId		numeric;
	v_clientId	numeric;
	v_return	character(1);
BEGIN
	SELECT ad_client_id, ad_org_id INTO
		v_clientId, v_orgId FROM
		M_Warehouse WHERE M_Warehouse_ID=warehouse_id;

	SELECT COALESCE(ad_orginfo.deliverypolicy, ad_clientinfo.deliverypolicy) INTO 
	v_return
	FROM AD_ClientInfo
	JOIN AD_OrgInfo ON (AD_ClientInfo.AD_Client_ID=AD_OrgInfo.AD_Client_ID)
	WHERE AD_ClientInfo.AD_Client_ID = v_clientId AND
	      AD_OrgInfo.AD_Org_ID = v_orgId;

	return v_return;	
END;
$BODY$

  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION get_delivery_policy(numeric) OWNER TO adempiere;

-- Get allocated on order

CREATE OR REPLACE FUNCTION get_allocated_on_order(p_product_id numeric, p_warehouse_id numeric)
  RETURNS numeric AS
$BODY$
	
DECLARE

    v_sum		numeric;

BEGIN
    --  Get Product Attribute Set Instance
	SELECT sum(qtyallocated) into v_sum from C_OrderLine ol
		JOIN C_Order o on (o.C_Order_ID=ol.C_Order_ID)
		WHERE 
		M_Product_ID=p_product_id AND
		COALESCE(ol.M_Warehouse_ID, o.M_Warehouse_ID)=p_warehouse_id;

        RETURN v_sum;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION get_allocated_on_order(numeric, numeric) OWNER TO adempiere;

-- IN OUT CANDIDATE ORDERLINE

-- DROP FUNCTION is_inout_candidate_orderline(numeric);
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
ALTER FUNCTION is_inout_candidate_orderline(numeric) OWNER TO adempiere;

-- INOUT CANDIDATE ORDER

CREATE OR REPLACE FUNCTION is_inout_candidate_order(p_order_id numeric)
  RETURNS character AS
$BODY$
DECLARE
	v_lines_ready	numeric;
	v_lines_total	numeric;
	v_deliveryRule	character(1);
BEGIN

	-- Get order info
	-- Only orders that are complete, not delivered, delivery rule anything else than manual and is a sales order
	-- can be inout candidates
	select DeliveryRule INTO v_deliveryRule FROM C_Order WHERE
			c_order_id=p_order_id AND
			docstatus = 'CO'::bpchar AND 
			isdelivered = 'N'::bpchar AND 
			deliveryrule <> 'M'::bpchar AND 
			(c_doctype_id IN ( SELECT c_doctype.c_doctype_id FROM c_doctype
				WHERE c_doctype.docbasetype = 'SOO'::bpchar AND (c_doctype.docsubtypeso <> ALL (ARRAY['ON'::bpchar, 'OB'::bpchar, 'WR'::bpchar]))));

	IF v_deliveryRule IS NULL THEN
		RETURN 'N';
	END IF;

	IF v_deliveryRule='F' THEN RETURN 'Y'; END IF; -- Force

	-- Check lines
	SELECT sum(is_inout_candidate_orderline(c_orderline_id)), sum(1) 
		INTO v_lines_ready, v_lines_total
	FROM c_orderline where c_order_id=p_order_id;

	CASE v_deliveryRule
		WHEN 'L','A' THEN -- Complete line and Availability
			IF v_lines_ready > 0 THEN RETURN 'Y'; END IF;
		WHEN 'O' THEN -- Complete order
			IF v_lines_ready = v_lines_total THEN RETURN 'Y'; END IF;
	END CASE;
	
	return 'N';
END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION is_inout_candidate_order(numeric) OWNER TO adempiere;

-- INOUT CANDIDATE

CREATE OR REPLACE VIEW m_inout_candidate_v AS 
SELECT 
	o.ad_client_id, 
	o.ad_org_id, 
	o.c_bpartner_id, 
	o.c_order_id, 
	o.documentno, 
	o.dateordered, 
	o.c_doctype_id, 
	o.poreference, 
	o.description, 
	o.salesrep_id, 
	l.m_warehouse_id, 
	sum((l.qtyordered - l.qtydelivered) * l.priceactual) AS totallines
	
   FROM c_order o
   JOIN c_orderline l ON o.c_order_id = l.c_order_id
  WHERE 
	(l.m_product_id IS NULL OR (EXISTS ( SELECT 1
					FROM m_product p
					WHERE l.m_product_id = p.m_product_id AND p.isexcludeautodelivery = 'N'::bpchar))) AND
	(l.m_product_id IS NOT NULL OR l.c_charge_id IS NOT NULL) AND
	is_inout_candidate_order(o.c_order_id) = 'Y'
			
  GROUP BY o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id, o.poreference, o.description, o.salesrep_id, l.m_warehouse_id;
ALTER TABLE m_inout_candidate_v OWNER TO adempiere;

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



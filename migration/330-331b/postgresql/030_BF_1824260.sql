-- BF [ 1824260 ] TRUNC function not working like in Oracle
-- http://sourceforge.net/tracker/?func=detail&atid=879332&aid=1824260&group_id=176962
DROP VIEW c_invoice_candidate_v;

drop function trunc(datetime TIMESTAMP WITH TIME ZONE);
CREATE OR REPLACE FUNCTION trunc(datetime TIMESTAMP WITH TIME ZONE)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
	RETURN CAST(datetime AS DATE);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW c_invoice_candidate_v AS 
 SELECT o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id, sum((l.qtyordered - l.qtyinvoiced) * l.priceactual) AS totallines
   FROM c_order o
   JOIN c_orderline l ON o.c_order_id = l.c_order_id
   JOIN c_bpartner bp ON o.c_bpartner_id = bp.c_bpartner_id
   LEFT JOIN c_invoiceschedule si ON bp.c_invoiceschedule_id = si.c_invoiceschedule_id
  WHERE (o.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar, 'IP'::bpchar])) AND (o.c_doctype_id IN ( SELECT c_doctype.c_doctype_id
   FROM c_doctype
  WHERE c_doctype.docbasetype = 'SOO'::bpchar AND (c_doctype.docsubtypeso <> ALL (ARRAY['ON'::bpchar, 'OB'::bpchar, 'WR'::bpchar])))) AND l.qtyordered <> l.qtyinvoiced AND (o.invoicerule = 'I'::bpchar OR o.invoicerule = 'O'::bpchar OR o.invoicerule = 'D'::bpchar AND l.qtyinvoiced <> l.qtydelivered OR o.invoicerule = 'S'::bpchar AND bp.c_invoiceschedule_id IS NULL OR o.invoicerule = 'S'::bpchar AND bp.c_invoiceschedule_id IS NOT NULL AND (si.invoicefrequency IS NULL OR si.invoicefrequency = 'D'::bpchar OR si.invoicefrequency = 'W'::bpchar OR si.invoicefrequency = 'T'::bpchar AND (trunc(o.dateordered::timestamp with time zone) <= (firstof(getdate(), 'MM'::character varying)::timestamp with time zone + si.invoicedaycutoff - 1) AND trunc(getdate()) >= (firstof(o.dateordered::timestamp with time zone, 'MM'::character varying)::timestamp with time zone + si.invoiceday - 1) OR trunc(o.dateordered::timestamp with time zone) <= (firstof(getdate(), 'MM'::character varying)::timestamp with time zone + si.invoicedaycutoff + 14) AND trunc(getdate()) >= (firstof(o.dateordered::timestamp with time zone, 'MM'::character varying)::timestamp with time zone + si.invoiceday + 14)) OR si.invoicefrequency = 'M'::bpchar AND trunc(o.dateordered::timestamp with time zone) <= (firstof(getdate(), 'MM'::character varying)::timestamp with time zone + si.invoicedaycutoff - 1) AND trunc(getdate()) >= (firstof(o.dateordered::timestamp with time zone, 'MM'::character varying)::timestamp with time zone + si.invoiceday - 1)))
  GROUP BY o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id;

ALTER TABLE c_invoice_candidate_v OWNER TO adempiere;

CREATE OR REPLACE FUNCTION trunc(datetime TIMESTAMP WITH TIME ZONE, format varchar)
RETURNS DATE AS $$
BEGIN
	IF format = 'Q' THEN
		RETURN CAST(DATE_Trunc('quarter',datetime) as DATE);
	ELSIF format = 'Y' or format = 'YEAR' THEN
		RETURN CAST(DATE_Trunc('year',datetime) as DATE);
	ELSIF format = 'MM' or format = 'MONTH' THEN
		RETURN CAST(DATE_Trunc('month',datetime) as DATE);
	ELSIF format = 'DD' THEN
		RETURN CAST(DATE_Trunc('day',datetime) as DATE);
	ELSIF format = 'DY' THEN
		RETURN CAST(DATE_Trunc('day',datetime) as DATE);
	ELSE
		RETURN CAST(datetime AS DATE);
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trunc(i INTERVAL)
RETURNS INTEGER AS $$
BEGIN
	RETURN EXTRACT(DAY FROM i);
END;
$$ LANGUAGE plpgsql;

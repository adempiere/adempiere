CREATE OR REPLACE FUNCTION linenetamtrealorderline(p_c_orderline_id numeric)
  RETURNS numeric AS
$BODY$
DECLARE
          v_amt numeric;  
BEGIN
	select case when pl.istaxincluded = 'Y' AND t.rate &lt;&gt; 0 then round(ol.linenetamt/(1+(t.rate/100)),cur.stdprecision) else linenetamt end  into v_amt
	from c_orderline ol
	inner join c_order o on ol.c_order_ID = o.c_order_ID
	inner join m_pricelist pl on o.m_pricelist_ID = pl.m_pricelist_ID
	inner join c_Tax t on ol.c_tax_ID = t.c_tax_ID
	inner join c_currency cur on o.c_currency_ID = cur.c_Currency_ID
	where ol.c_orderline_ID=p_c_orderLine_ID;

    RETURN coalesce(v_amt,0);
END;
$BODY$
  LANGUAGE plpgsql;
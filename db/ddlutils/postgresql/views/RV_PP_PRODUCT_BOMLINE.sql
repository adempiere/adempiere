-- DROP VIEW rv_pp_product_bomline;
CREATE OR REPLACE VIEW rv_pp_product_bomline AS 
SELECT 
t.seqno,
t.levelno,
t.levels,
t.ad_client_id,
t.ad_org_id,
t.createdby,
t.updatedby,
t.updated,
t.created,
t.ad_pinstance_id,
bl.isactive,
bl.pp_product_bom_id,
bl.pp_product_bomline_id,
bl.description, bl.iscritical,
bl.componenttype,
t.m_product_id,
bl.c_uom_id,
bl.issuemethod,
bl.line,
bl.m_attributesetinstance_id,
bl.scrap,
bl.validfrom,
bl.validto,
bl.qtybom,
bl.qtybatch,
bl.isqtypercentage
FROM pp_product_bomline bl
RIGHT JOIN t_bomline t ON t.pp_product_bomline_id = bl.pp_product_bomline_id
ORDER BY t.seqno;

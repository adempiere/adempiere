-- June 1, 2008 1:20:00 PM EST
-- BF1981079 - MultiLevel BOM & Formula Detail
DROP VIEW rv_pp_product_bomline;

CREATE VIEW rv_pp_product_bomline (seqno, levelno, levels, ad_client_id, ad_org_id, createdby, updatedby, updated, created, ad_pinstance_id, implosion, m_product_id, isactive, pp_product_bom_id, pp_product_bomline_id, description, iscritical, componenttype, tm_product_id, c_uom_id, issuemethod, line, m_attributesetinstance_id, scrap, validfrom, validto, qtybom, qtybatch, isqtypercentage) AS 
 SELECT t.seqno, t.levelno, t.levels, t.ad_client_id, t.ad_org_id, t.createdby, t.updatedby, t.updated, t.created, t.ad_pinstance_id, t.implosion, t.sel_product_id, bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, bl.iscritical, bl.componenttype, t.m_product_id, bl.c_uom_id, bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap, bl.validfrom, bl.validto, bl.qtybom, bl.qtybatch, bl.isqtypercentage
 FROM t_bomline t 
 LEFT JOIN pp_product_bomline bl ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
 ORDER BY t.seqno;

ALTER TABLE T_BOMLine DROP COLUMN Implotion;

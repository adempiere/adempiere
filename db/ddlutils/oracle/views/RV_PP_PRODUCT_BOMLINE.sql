CREATE OR REPLACE VIEW rv_pp_product_bomline 
  AS 
    SELECT  t.seqno, t.levelno, t.levels, t.ad_client_id,
            t.ad_org_id, t.createdby, t.updatedby, t.updated,
            t.created, t.ad_pinstance_id, t.implosion, t.sel_product_id as m_product_id,
            bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, 
            bl.iscritical, bl.componenttype, t.m_product_id as tm_product_id, bl.c_uom_id,
            bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap,
            bl.validfrom, bl.validto, bl.qtybom, bl.qtybatch, bl.isqtypercentage
       FROM t_bomline t LEFT OUTER JOIN pp_product_bomline bl 
            ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
;

-- June 1, 2008 1:20:00 PM EST
-- BF1981079 - MultiLevel BOM & Formula Detail
DROP VIEW RV_PP_PRODUCT_BOMLINE;

CREATE VIEW RV_PP_PRODUCT_BOMLINE (SEQNO, LEVELNO, LEVELS, AD_CLIENT_ID, AD_ORG_ID, CREATEDBY, UPDATEDBY, UPDATED, CREATED, AD_PINSTANCE_ID, IMPLOSION, M_PRODUCT_ID, ISACTIVE, PP_PRODUCT_BOM_ID, PP_PRODUCT_BOMLINE_ID, DESCRIPTION, ISCRITICAL, COMPONENTTYPE, TM_PRODUCT_ID, C_UOM_ID, ISSUEMETHOD, LINE, M_ATTRIBUTESETINSTANCE_ID, SCRAP, VALIDFROM, VALIDTO, QTYBOM, QTYBATCH, ISQTYPERCENTAGE) AS 
 SELECT t.seqno, t.levelno, t.levels, t.ad_client_id, t.ad_org_id, t.createdby, t.updatedby, t.updated, t.created, t.ad_pinstance_id, t.implosion, t.sel_product_id, bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, bl.iscritical, bl.componenttype, t.m_product_id, bl.c_uom_id, bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap, bl.validfrom, bl.validto, bl.qtybom, bl.qtybatch, bl.isqtypercentage
 FROM t_bomline t 
 LEFT JOIN pp_product_bomline bl ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
 ORDER BY t.seqno;

ALTER TABLE T_BOMLine DROP COLUMN Implotion;

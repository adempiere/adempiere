-- BF2033473 RV_PP_Product_BOMLine
-- fix missing columns, add Order By and TM_Product print name
DROP VIEW rv_pp_product_bomline;

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

-- Aug 1, 2008 2:55:39 PM EST
-- Default comment for updating dictionary
UPDATE AD_Element SET PrintName='Product',Updated=TO_DATE('2008-08-01 14:55:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53465
;

-- Aug 1, 2008 2:55:39 PM EST
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53465
;

-- Aug 1, 2008 2:55:39 PM EST
-- Default comment for updating dictionary
UPDATE AD_PrintFormatItem pi SET PrintName='Product', Name='TM_Product_ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53465)
;

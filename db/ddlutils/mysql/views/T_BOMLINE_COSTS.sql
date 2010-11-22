CREATE OR REPLACE VIEW T_BOMLINE_COSTS 
AS 
SELECT  t.seqno, t.levelno, t.levels, t.ad_client_id,t.C_AcctSchema_ID,
	t.ad_org_id, t.createdby, t.updatedby, t.updated,
	t.created, t.ad_pinstance_id, t.implosion, 
	t.sel_product_id as m_product_id,
	t.m_costelement_id, 
	t.currentcostprice,currentcostpricell,
	t.futurecostprice,futurecostpricell,
	t.iscostfrozen,
	t.QtyBOM,
	(t.currentcostprice + t.currentcostpricell) * t.QtyBOM as Cost,
	(t.futurecostprice + t.futurecostpricell) * t.QtyBOM as CostStandard,
	t.M_CostType_ID,
	t.CostingMethod,
	bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, 
	bl.iscritical, bl.componenttype, t.m_product_id as tm_product_id, bl.c_uom_id,
	bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap,
	bl.validfrom, bl.validto, bl.isqtypercentage
FROM t_bomline t
LEFT OUTER JOIN pp_product_bomline bl ON (t.pp_product_bomline_id = bl.pp_product_bomline_id) 
;

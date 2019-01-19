CREATE OR REPLACE VIEW RV_M_REPLENISHPLAN_REQUISITION 
(AD_CLIENT_ID,
AD_ORG_ID,
M_REPLENISHPLANLINE_ID,
M_REPLENISHPLAN_ID,
M_REQUISITION_ID,
DOCUMENTNO,
LINE,
M_PRODUCT_ID,
VENDORPRODUCTNO,
C_BPARTNER_ID,
QTY,
PRICEACTUAL,
ISACTIVE,
DATEREQUIRED,
M_PRODUCT_CATEGORY_ID,
AMOUNT)

AS 
 SELECT mrl.ad_client_id,
    mrl.ad_org_id,
    mrl.m_replenishplanline_id,
    mrl.m_replenishplan_id,
    r.m_requisition_id,
    r.documentno,
    rl.line,
    p.m_product_id,
    po.vendorproductno,
    rl.c_bpartner_id,
    rl.qty,
    rl.priceactual,
    rl.isactive,
    r.daterequired,
    p.m_product_category_id,
    rl.priceactual * rl.qty AS amount
   FROM m_replenishplanline mrl
   JOIN m_requisitionline rl ON rl.m_requisition_id = mrl.m_requisition_id AND rl.m_product_id = mrl.m_product_id
   JOIN m_requisition r ON r.m_requisition_id = rl.m_requisition_id
   JOIN m_product p ON p.m_product_id = rl.m_product_id
   LEFT JOIN m_product_po po ON po.m_product_id = p.m_product_id AND po.c_bpartner_id = rl.c_bpartner_id
  ORDER BY r.m_requisition_id, rl.line;
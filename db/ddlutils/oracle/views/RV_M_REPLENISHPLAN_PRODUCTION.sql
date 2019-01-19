CREATE OR REPLACE VIEW RV_M_REPLENISHPLAN_PRODUCTION 
(AD_CLIENT_ID,
AD_ORG_ID,
M_REPLENISHPLAN_ID,
M_PRODUCT_ID,
M_PRODUCTION_ID,
PRODUCTIONQTY,
DATEPROMISED,
MOVEMENTDATE,
ISACTIVE,
M_PRODUCT_CATEGORY_ID,
CURRENTCOSTPRICE,
CURRENTCOSTVALUE)

AS 
 SELECT mrl.ad_client_id,
    mrl.ad_org_id,
    mrl.m_replenishplan_id,
    p.m_product_id,
    pd.m_production_id,
    pd.productionqty,
    pd.datepromised,
    pd.movementdate,
    pd.isactive,
    p.m_product_category_id,
    trunc(c.currentcostprice, 6) AS currentcostprice,
    trunc(c.currentcostprice, 6) * pd.productionqty AS currentcostvalue
   FROM m_replenishplanline mrl
   INNER JOIN m_production pd ON pd.m_production_id = mrl.m_production_id
   INNER JOIN m_product p ON p.m_product_id = pd.m_product_id
   LEFT OUTER JOIN m_cost c ON c.m_product_id = p.m_product_id
  WHERE mrl.recordtype= 'Planned Production' AND c.m_costelement_id = (( SELECT m_costelement.m_costelement_id
   FROM m_costelement
  WHERE m_costelement.ad_client_id = p.ad_client_id AND m_costelement.costingmethod = 'S' AND m_costelement.costelementtype = 'M'))
  ORDER BY pd.m_production_id;
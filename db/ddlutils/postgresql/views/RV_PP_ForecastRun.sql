DROP VIEW RV_PP_ForecastRun;
CREATE OR REPLACE VIEW RV_PP_ForecastRun AS 
 SELECT
 frun.IsActive,
 frun.Created,
 frun.CreatedBy,
 frun.Updated,
 frun.UpdatedBy,
 frun.ad_client_id,
 frun.ad_org_id,
 frun.pp_forecastrun_id,
 frun.documentno,
 frun.description,
 frun.pp_forecastrule_id,
 frun.pp_calendar_id,
 frun.pp_perioddefinition_id,
 frun.ref_definitionperiod_id,
 frun.periodhistory,
 fmaster.m_product_id,
 p.value,
 p.name, 
 pc.m_product_category_id, 
 pcl.m_product_classification_id, 
 pclass.m_product_class_id, 
 pg.m_product_group_id, 
 frun.m_warehousesource_id, 
 fmaster.factoralpha, 
 fmaster.factorgamma, 
 fmaster.factormultiplier, 
 fmaster.factorscale, 
 pd.name AS periodname, 
 frun.m_warehouse_id, 
 fdetail.qtycalculated AS qtyinvoiced, 
 fresult.pp_period_id, 
 pr.startdate, pr.enddate, 
 fresult.periodno, 
 fresult.description AS linedescription, 
 fresult.qtycalculated, 
 fresult.qtyplan, 
 fresult.qtyabnormal
FROM pp_forecastrun frun
JOIN pp_forecastrunmaster fmaster ON fmaster.pp_forecastrun_id = frun.pp_forecastrun_id
JOIN pp_forecastrundetail fdetail ON fdetail.pp_forecastrunmaster_id = fmaster.pp_forecastrunmaster_id
LEFT JOIN pp_forecastrunresult fresult ON fresult.pp_forecastrunmaster_id = fmaster.pp_forecastrunmaster_id
JOIN pp_period pd ON pd.pp_period_id = fdetail.pp_period_id
JOIN pp_period pr ON pr.pp_period_id = fresult.pp_period_id
JOIN m_product p ON p.m_product_id = fmaster.m_product_id
LEFT JOIN m_product_category pc ON pc.m_product_category_id = p.m_product_category_id
LEFT JOIN m_product_classification pcl ON pcl.m_product_classification_id = p.m_product_classification_id
LEFT JOIN m_product_class pclass ON pclass.m_product_class_id = p.m_product_class_id
LEFT JOIN m_product_group pg ON pg.m_product_group_id = p.m_product_group_id;
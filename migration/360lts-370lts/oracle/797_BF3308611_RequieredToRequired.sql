-- May 27, 2011 6:41:09 PM CDT
-- Fixed wrong field name
UPDATE AD_Element SET ColumnName='QtyRequired', Name='Qty Required',Updated=TO_DATE('2011-05-27 18:41:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53288
;

-- May 27, 2011 6:41:09 PM CDT
-- Fixed wrong field name
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53288
;

-- May 27, 2011 6:41:09 PM CDT
-- Fixed wrong field name
UPDATE AD_Column SET ColumnName='QtyRequired', Name='Qty Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53288
;

-- May 27, 2011 6:41:11 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='QtyRequired', Name='Qty Required', Description=NULL, Help=NULL, AD_Element_ID=53288 WHERE UPPER(ColumnName)='QTYREQUIRED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 27, 2011 6:41:11 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='QtyRequired', Name='Qty Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53288 AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 6:41:11 PM CDT
-- Fixed wrong field name
UPDATE AD_Field SET Name='Qty Required', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53288) AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 6:41:11 PM CDT
-- Fixed wrong field name
UPDATE AD_PrintFormatItem pi SET PrintName='Qty Required', Name='Qty Required' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53288)
;


-- May 27, 2011 7:45:25 PM CDT
-- Fixed wrong field name
UPDATE AD_Element SET ColumnName='DurationRequired', Name='Duration Required', PrintName='Duration Required',Updated=TO_DATE('2011-05-27 19:45:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53284
;

-- May 27, 2011 7:45:26 PM CDT
-- Fixed wrong field name
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53284
;

-- May 27, 2011 7:45:26 PM CDT
-- Fixed wrong field name
UPDATE AD_Column SET ColumnName='DurationRequired', Name='Duration Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53284
;

-- May 27, 2011 7:45:39 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='DurationRequired', Name='Duration Required', Description=NULL, Help=NULL, AD_Element_ID=53284 WHERE UPPER(ColumnName)='DURATIONREQUIRED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 27, 2011 7:45:43 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='DurationRequired', Name='Duration Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53284 AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 7:45:43 PM CDT
-- Fixed wrong field name
UPDATE AD_Field SET Name='Duration Required', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53284) AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 7:45:46 PM CDT
-- Fixed wrong field name
UPDATE AD_PrintFormatItem pi SET PrintName='Duration Required', Name='Duration Required' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53284)
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Element SET ColumnName='SetupTimeRequired', Name='Setup Time Required', PrintName='Setup Time Required',Updated=TO_DATE('2011-05-27 19:46:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53291
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53291
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Column SET ColumnName='SetupTimeRequired', Name='Setup Time Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53291
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='SetupTimeRequired', Name='Setup Time Required', Description=NULL, Help=NULL, AD_Element_ID=53291 WHERE UPPER(ColumnName)='SETUPTIMEREQUIRED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Process_Para SET ColumnName='SetupTimeRequired', Name='Setup Time Required', Description=NULL, Help=NULL WHERE AD_Element_ID=53291 AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_Field SET Name='Setup Time Required', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53291) AND IsCentrallyMaintained='Y'
;

-- May 27, 2011 7:46:47 PM CDT
-- Fixed wrong field name
UPDATE AD_PrintFormatItem pi SET PrintName='Setup Time Required', Name='Setup Time Required' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53291)
;


ALTER TABLE pp_order_bomline RENAME COLUMN qtyrequiered TO qtyrequired;
ALTER TABLE PP_Order_Node RENAME COLUMN qtyrequiered TO qtyrequired;
ALTER TABLE PP_Order_Node RENAME COLUMN SetupTimeRequiered TO SetupTimeRequired;
ALTER TABLE PP_Order_Node RENAME COLUMN DurationRequiered TO DurationRequired;


DROP VIEW  PP_Order_BOMLine_v;	
DROP VIEW PP_Order_BOMLine_vt;	
DROP VIEW PP_Order_Node_v;
DROP VIEW PP_Order_Node_vt;
DROP VIEW rv_pp_order_bomline;
DROP VIEW rv_pp_order_receipt_issue;
DROP VIEW rv_pp_order_storage;
DROP VIEW rv_pp_operation_activity;

CREATE OR REPLACE VIEW PP_Order_BOMLine_v
AS 
SELECT obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
cast('en_US' as varchar2(6)) AS AD_Language,
obl.Description , feature , obl.M_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, obl.help ,
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID,obl.pp_order_bomLine_id,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,
round(obl.qtyrequired, 4) AS qtyrequired,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequired / o.qtybatchs, 4) END AS qtybatchsize  
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID);

CREATE OR REPLACE VIEW PP_Order_BOMLine_vt
AS 
SELECT 
obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
oblt.AD_Language,
oblt.Description , obl.feature , obl.m_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, oblt.help , 
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID, obl.pp_order_bomline_ID,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,o.QtyBatchs,
round(obl.qtyrequired, 4) AS qtyrequired,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequired / o.qtybatchs, 4) END AS qtybatchsize 
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID)
LEFT JOIN PP_Order_BOMLine_Trl oblt ON (oblt.PP_Order_BOMLine_ID=obl.PP_Order_BOMLine_ID);


CREATE OR REPLACE VIEW PP_Order_Node_v
AS
SELECT
onode.AD_Client_ID, onode.AD_Org_ID, onode.IsActive, onode.Created, onode.CreatedBy, onode.Updated, onode.UpdatedBy,
cast('en_US' as varchar2(6)) AS AD_Language,
name, c_bpartner_id, cost, datefinish, datefinishschedule, datestart, datestartschedule , 
description, docaction, docstatus,duration, durationreal, durationrequired, help, ismilestone,
issubcontracting, movingtime, overlapunits, 
pp_order_id, pp_order_workflow_id, onode.pp_order_node_id,priority, qtydelivered, qtyrequired, 
qtyscrap , queuingtime , s_resource_id , setuptime ,setuptimereal,  unitscycles ,  validfrom , validto , value , waitingtime , workingtime , yield 
FROM PP_Order_Node onode;


CREATE OR REPLACE VIEW PP_Order_Node_vt
AS
SELECT
onode.AD_Client_ID, onode.AD_Org_ID, onode.IsActive, onode.Created, onode.CreatedBy, onode.Updated, onode.UpdatedBy,
ont.AD_Language,
ont.name, c_bpartner_id, cost, datefinish, datefinishschedule, datestart, datestartschedule , 
ont.description, docaction, docstatus, duration, durationreal, durationrequired, ont.help, ismilestone,
issubcontracting, movingtime, overlapunits, 
pp_order_id, pp_order_workflow_id,onode.pp_order_node_id, priority, qtydelivered, qtyrequired , 
qtyscrap , queuingtime , s_resource_id , setuptime ,setuptimereal,  unitscycles ,  validfrom , validto , value , waitingtime , workingtime , yield 
FROM PP_Order_Node onode
LEFT JOIN PP_Order_Node_Trl ont ON (ont.PP_Order_Node_ID=onode.PP_Order_Node_ID);


CREATE OR REPLACE VIEW rv_pp_operation_activity AS 
SELECT n.ad_client_id,
n.ad_org_id,
n.created,
n.createdby,
n.isactive,
n.updated,
n.updatedby,
n.pp_order_id,
n.docstatus,
n.value,
n.s_resource_id,
n.durationrequired,
n.durationreal,
n.durationrequired - n.durationreal AS duration,
n.qtydelivered,
n.qtyreject,
n.qtyscrap,
n.datestartschedule,
n.datefinishschedule
FROM pp_order_node n;

CREATE OR REPLACE VIEW rv_pp_order_bomline AS 
SELECT 
obl.ad_client_id,
obl.ad_org_id,
obl.createdby,
obl.updatedby,
obl.updated,
obl.created,
obl.isactive,
obl.pp_order_bom_id,
obl.pp_order_bomline_id,
obl.pp_order_id,
obl.iscritical, 
obl.componenttype,
obl.m_product_id,
obl.c_uom_id,
round(obl.qtyrequired, 4) AS qtyrequired,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
obl.m_warehouse_id,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequired / o.qtybatchs, 4) END AS qtybatchsize
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id;


CREATE OR REPLACE VIEW rv_pp_order_storage AS 
SELECT 
obl.ad_client_id,
obl.ad_org_id,
obl.createdby,
obl.updatedby,
obl.updated,
obl.created,
obl.isactive,
obl.pp_order_bom_id,
obl.pp_order_bomline_id,
obl.pp_order_id,
obl.iscritical,
obl.m_product_id,
( SELECT p.name FROM m_product p WHERE p.m_product_id = o.m_product_id) AS name,
obl.c_uom_id,
s.qtyonhand,
round(obl.qtyrequired, 4) AS qtyrequired, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequired / o.qtybatchs, 4) END AS qtybatchsize,
round(bomqtyreserved(obl.m_product_id,obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id,0), 4) AS qtyavailable,
obl.m_warehouse_id,
obl.qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch,
obl.m_attributesetinstance_id,
l.m_locator_id,
l.x,
l.y,
l.z
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id
LEFT JOIN m_storage s ON s.m_product_id = obl.m_product_id AND s.qtyonhand <> 0 AND obl.m_warehouse_id = (( SELECT ld.m_warehouse_id FROM m_locator ld WHERE s.m_locator_id = ld.m_locator_id))
LEFT JOIN m_locator l ON l.m_locator_id = s.m_locator_id
ORDER BY obl.m_product_id;



CREATE OR REPLACE VIEW rv_pp_order_receipt_issue AS 
SELECT obl.pp_order_bomline_id,
obl.iscritical,
p.value,
obl.m_product_id,
mos.name AS productname,
mos.m_attributesetinstance_id,
asi.description AS instancename,
mos.c_uom_id,
u.name AS uomname,
obl.qtyrequired,
obl.qtyreserved AS qtyreserved_order,
mos.qtyonhand,
mos.qtyreserved AS qtyreserved_storage,
mos.qtyavailable,
mos.m_locator_id,
mos.m_warehouse_id,
w.name AS warehousename,
mos.qtybom,
mos.isqtypercentage,
mos.qtybatch,
obl.componenttype,
mos.qtyrequired - obl.qtydelivered AS qtyopen,
obl.pp_order_id
FROM rv_pp_order_storage mos
JOIN pp_order_bomline obl ON mos.pp_order_bomline_id = obl.pp_order_bomline_id
JOIN m_attributesetinstance asi ON mos.m_attributesetinstance_id = asi.m_attributesetinstance_id
JOIN c_uom u ON mos.c_uom_id = u.c_uom_id
JOIN m_product p ON mos.m_product_id = p.m_product_id
JOIN m_warehouse w ON mos.m_warehouse_id = w.m_warehouse_id;



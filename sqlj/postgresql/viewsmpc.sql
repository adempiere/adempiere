CREATE TABLE compiere.MPC_Order_Node_Trl
(
  ad_language varchar(6) NOT NULL,
  mpc_order_node_id int4 NOT NULL,
  mpc_order_id int4 NOT NULL,
  ad_client_id int4 NOT NULL,
  ad_org_id int4 NOT NULL,
  isactive char(1) NOT NULL DEFAULT 'Y'::bpchar,
  created timestamp NOT NULL DEFAULT ('now'::text)::timestamp(6) with time zone,
  createdby int4 NOT NULL,
  updated timestamp NOT NULL DEFAULT ('now'::text)::timestamp(6) with time zone,
  updatedby int4 NOT NULL,
  name varchar(60) NOT NULL,
  description varchar(255),
  help varchar(2000),
  istranslated char(1) NOT NULL DEFAULT 'N'::bpchar,
  CONSTRAINT mpc_order_node_trl_key PRIMARY KEY (ad_language, mpc_order_node_id),
  CONSTRAINT ad_language_ordernodetrl FOREIGN KEY (ad_language) REFERENCES compiere.ad_language (ad_language) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mpc_ordernodetrl FOREIGN KEY (mpc_order_node_id, mpc_order_id) REFERENCES compiere.mpc_order_node (mpc_order_node_id, mpc_order_id) ON UPDATE NO ACTION ON DELETE CASCADE
) 
WITH OIDS;

CREATE TABLE compiere.mpc_order_workflow_trl
(
  mpc_order_workflow_id int4 NOT NULL,
  mpc_order_id int4 NOT NULL,
  ad_language varchar(6) NOT NULL,
  ad_client_id int4 NOT NULL,
  ad_org_id int4 NOT NULL,
  isactive char(1) NOT NULL DEFAULT 'Y'::bpchar,
  created timestamp NOT NULL DEFAULT ('now'::text)::timestamp(6) with time zone,
  createdby int4 NOT NULL,
  updated timestamp NOT NULL DEFAULT ('now'::text)::timestamp(6) with time zone,
  updatedby int4 NOT NULL,
  name varchar(60) NOT NULL,
  description varchar(255),
  help varchar(2000),
  istranslated char(1) NOT NULL DEFAULT 'N'::bpchar,
  CONSTRAINT mpc_order_workflow_trl_key PRIMARY KEY (ad_language, mpc_order_workflow_id),
  CONSTRAINT ad_language_workflowtrl FOREIGN KEY (ad_language) REFERENCES compiere.ad_language (ad_language) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mpc_order_workflowtrl FOREIGN KEY (mpc_order_workflow_id, mpc_order_id) REFERENCES compiere.mpc_order_workflow (mpc_order_workflow_id, mpc_order_id) ON UPDATE NO ACTION ON DELETE CASCADE
) 
WITH OIDS;

CREATE OR REPLACE  VIEW RV_MPC_Order AS 
  SELECT o.AD_Client_ID, o.AD_Org_ID,o.IsActive,o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
o.MPC_Order_ID, o.DocumentNo,o.DocStatus,o.M_Warehouse_ID,o.M_Product_ID,o.QtyEntered,
o.QtyReject,o.QtyScrap,o.QtyBatchs, o.QtyBatchSize,o.DateOrdered, o.DatePromised,o.DateStart,
o.DateStartSchedule,o.DateFinish,o.DateFinishSchedule,o.DateConfirm,o.DateDelivered,o.Lot,
o.MPC_Product_BOM_ID,o.AD_Workflow_ID, (select p.Weight from M_Product p where p.M_Product_ID=o.M_Product_ID) as Weight
FROM MPC_Order o;

CREATE OR REPLACE  VIEW RV_MPC_Order_BOMLine AS 
SELECT
obl.AD_Client_ID ,
obl.AD_Org_ID ,
obl.CreatedBy ,
obl.UpdatedBY ,
obl.Updated ,
obl.Created ,
obl.IsActive ,
obl.MPC_Order_BOM_ID,
obl.MPC_Order_BOMLine_ID  ,
obl.MPC_Order_ID ,
obl.IsCritical ,
obl.ComponentType,
obl.M_Product_ID  ,
obl.C_UOM_ID  ,
ROUND(obl.QtyRequiered ,4) AS QtyRequiered ,
ROUND(BOMQtyReserved(obl.M_Product_ID,obl.M_Warehouse_ID,0), 4) AS QtyReserved ,
ROUND(BOMQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0),4) AS QtyAvailable ,
ROUND(BOMQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0),4) AS QtyOnHand  ,
obl.M_Warehouse_ID  ,
ROUND(obl.QtyBom,4) AS QtyBom,
obl.isQtyPercentage ,
ROUND(obl.QtyBatch,4) AS QtyBatch,
CASE WHEN  o.QtyBatchs = 0 THEN 1 ELSE  ROUND( obl.QtyRequiered / o.QtyBatchs, 4) END AS QtyBatchSize 
--DECODE(o.QtyBatchs , 0 , 0 ,  ROUND( obl.QtyRequiered / o.QtyBatchs, 4) ) AS QtyBatchSize
FROM MPC_Order_BOMLine obl INNER JOIN MPC_Order o ON (o.MPC_Order_ID = obl.MPC_Order_ID);

--DROP VIEW RV_MPC_Order_Storage;
CREATE OR REPLACE  VIEW RV_MPC_Order_Storage AS 
SELECT
obl.AD_Client_ID ,
obl.AD_Org_ID ,
obl.CreatedBy ,
obl.UpdatedBY ,
obl.Updated ,
obl.Created ,
obl.IsActive ,
obl.MPC_Order_BOM_ID,
obl.MPC_Order_BOMLine_ID  ,
obl.MPC_Order_ID ,
obl.IsCritical ,
obl.M_Product_ID  ,
(Select p.Name FROM M_Product p Where p.M_Product_ID=o.M_Product_ID) as Name,
obl.C_UOM_ID  ,
s.QtyOnhand ,
ROUND(obl.QtyRequiered ,4) AS QtyRequiered ,
CASE  WHEN o.QtyBatchs = 0 THEN 1 ELSE ROUND( obl.QtyRequiered / o.QtyBatchs, 4) END AS QtyBatchSize,
--DECODE(o.QtyBatchs , 0 , 0 ,  ROUND( obl.QtyRequiered / o.QtyBatchs, 4)  ) AS QtyBatchSize,
ROUND(BOMQtyReserved(obl.M_Product_ID,obl.M_Warehouse_ID,0), 4) AS QtyReserved ,
ROUND(BOMQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0),4) AS QtyAvailable ,
obl.M_Warehouse_ID  ,
obl.QtyBom ,
obl.isQtyPercentage ,
ROUND(obl.QtyBatch,4) AS QtyBatch ,
l.M_Locator_ID ,
s.m_attributesetinstance_id, 
l.x ,
l.y ,
l.z 
FROM MPC_Order_BOMLine obl INNER JOIN MPC_Order o ON (o.MPC_Order_ID = obl.MPC_Order_ID)  LEFT OUTER  JOIN M_Storage s ON ( s.M_Product_ID = obl.M_Product_ID AND s.QtyOnHand <> 0 AND  obl.M_Warehouse_ID = (SELECT ld.M_Warehouse_ID FROM M_Locator ld WHERE  s.M_Locator_ID=ld.M_Locator_ID))   LEFT OUTER JOIN M_Locator l ON (l.M_Locator_ID =  s.M_Locator_ID)  ORDER BY obl.M_Product_ID 
;

CREATE OR REPLACE  VIEW RV_MPC_Order_Transactions AS 
SELECT DISTINCT o.AD_Client_ID,o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.UpdatedBy, o.Updated,o.DocumentNo,ol.M_Product_ID, mt.M_Locator_ID,mt.MovementDate,o.MPC_Order_ID,
o.QtyDelivered, o.QtyScrap ,ol.QtyDelivered AS QtyDeliveredLine , (o.QtyDelivered  * ol.QtyBatch)/100 AS QtyIssueShouldbe,
ol.QtyScrap AS QtyScrapLine , (o.QtyScrap  * ol.QtyBatch)/100 AS QtyIssueScrapShouldBe , mt.CreatedBy AS CreatedByIssue, mt.UpdatedBy AS UpdatedByIssue,
(SELECT SUM(t.MovementQty) FROM M_Transaction t WHERE t.MPC_Order_BOMLine_ID=ol.MPC_Order_BOMLine_ID) AS QtyToDeliver,
((((o.QtyDelivered + o.QtyScrap) * ol.QtyBatch)/100) + (SELECT SUM(t.MovementQty) FROM M_Transaction t WHERE t.MPC_Order_BOMLine_ID  = ol.MPC_Order_BOMLine_ID)) AS DifferenceQty
FROM MPC_Order o INNER JOIN MPC_Order_BOMLine ol ON (ol.MPC_Order_ID=o.MPC_Order_ID)  LEFT JOIN M_Transaction mt ON( mt.MPC_Order_BOMLine_ID  = ol.MPC_Order_BOMLine_ID)
;
 
--DROP  VIEW RV_MPC_PRODUCT_BOMLINE ;
--DROP  VIEW RV_MPC_PRODUCT_BOMLINE ;
CREATE OR REPLACE VIEW RV_MPC_PRODUCT_BOMLINE  AS 
SELECT
t.SeqNo,
t.LevelNo,
t.Levels,
t.AD_Client_ID ,
t.AD_Org_ID ,
t.CreatedBy ,
t.UpdatedBy ,
t.Updated ,
t.Created ,
t.AD_PInstance_ID,
bl.IsActive ,
bl.MPC_Product_BOM_ID,
bl.MPC_Product_BOMLine_ID  ,
bl.Description,
bl.IsCritical ,
bl.ComponentType,
t.M_Product_ID  ,
bl.C_UOM_ID ,
bl.IssueMethod,
bl.Line,
bl.M_AttributeSetInstance_ID,
bl.Scrap,
bl.ValidFrom,
bl.ValidTo,
bl.QtyBom,
bl.QtyBatch,
bl.isQtyPercentage 
FROM  MPC_Product_BOMLine bl  RIGHT  JOIN  T_BOMLine t ON (t.MPC_Product_BOMLine_ID = bl.MPC_Product_BOMLine_ID) ORDER BY SeqNo ;
create view compiere.mpc_order_receipt_issue as
select
	obl.mpc_order_bomline_id, 
	obl.iscritical, 
	p.value, 
	obl.m_product_id, 
	mos.name as productname, 
	mos.m_attributesetinstance_id, 
	asi.description as instancename,
	mos.c_uom_id, 
	u.name as uomname, 
	obl.qtyrequiered, 
	obl.qtyreserved as qtyreserved_order,
	mos.qtyonhand,
	mos.qtyreserved as qtyreserved_storage,
	mos.qtyavailable,
	mos.m_locator_id, 
	mos.m_warehouse_id, 
	w.name as warehousename, 
	mos.qtybom, 
	mos.isqtypercentage, 
	mos.qtybatch, 
	obl.ComponentType, 
	mos.QtyRequiered - obl.QtyDelivered AS QtyOpen, 
	obl.mpc_order_id
from compiere.rv_mpc_order_storage mos
inner join compiere.mpc_order_bomline obl on mos.mpc_order_bomline_id = obl.mpc_order_bomline_id
inner join compiere.m_attributesetinstance asi on mos.m_attributesetinstance_id = asi.m_attributesetinstance_id
inner join compiere.c_uom u on mos.c_uom_id = u.c_uom_id
inner join compiere.m_product p on mos.m_product_id = p.m_product_id 
inner join compiere.m_warehouse w on mos.m_warehouse_id = w.m_warehouse_id;
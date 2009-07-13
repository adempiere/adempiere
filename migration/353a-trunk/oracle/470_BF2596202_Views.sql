CREATE OR REPLACE VIEW PP_Order_BOM_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar2(6)) AS AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	d.PrintName AS DocumentType, d.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	--ob.PP_Product_BOM_ID,
	ob.BOMType,ob.BOMUse, ob.Description , ob.Help , ob.M_AttributeSetInstance_ID , ob.M_Product_ID, ob.Name , ob.Revision, ob.ValidFrom , ob.ValidTo 	
FROM PP_Order o
	INNER JOIN C_DocType d ON (o.C_DocType_ID=d.C_DocType_ID)
	INNER JOIN PP_Order_BOM ob ON (ob.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/
CREATE OR REPLACE VIEW PP_Order_BOM_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	obt.AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	--ob.PP_Product_BOM_ID,
	ob.BOMType,ob.BOMUse, obt.Description , obt.Help , ob.M_AttributeSetInstance_ID , ob.M_Product_ID, obt.Name , ob.Revision, ob.ValidFrom , ob.ValidTo 	
FROM PP_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN PP_Order_BOM ob ON (ob.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN PP_Order_BOM_Trl obt ON (obt.PP_Order_BOM_ID=ob.PP_Order_BOM_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/
CREATE OR REPLACE VIEW PP_Order_BOMLine_v
AS 
SELECT obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
cast('en_US' as varchar2(6)) AS AD_Language,
obl.Description , feature , obl.M_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, obl.help ,
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID,obl.pp_order_bomLine_id,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize  
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID);
/
CREATE OR REPLACE VIEW PP_Order_BOMLine_vt
AS 
SELECT 
obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
oblt.AD_Language,
oblt.Description , obl.feature , obl.m_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, oblt.help , 
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID, obl.pp_order_bomline_ID,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,o.QtyBatchs,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize 
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID)
LEFT JOIN PP_Order_BOMLine_Trl oblt ON (oblt.PP_Order_BOMLine_ID=obl.PP_Order_BOMLine_ID);
/	
CREATE OR REPLACE VIEW PP_Order_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar2(6)) AS AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
 	o.Description,
	o.M_Product_ID,o.M_AttributeSetInstance_ID,o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID, o.User1_ID , o.User2_ID , o.AD_OrgTrx_ID ,o.C_DocTypeTarget_ID,o.ScheduleType , o.IsApproved , o.DocAction , o.Posted , o.IsPrinted, o.OrderType
FROM PP_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/
--DROP VIEW PP_Order_Header_v;
--/
CREATE OR REPLACE VIEW PP_Order_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
 	o.Description,
	o.M_Product_ID,o.M_AttributeSetInstance_ID,o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID, o.User1_ID , o.User2_ID , o.AD_OrgTrx_ID ,o.C_DocTypeTarget_ID,o.ScheduleType , o.IsApproved , o.DocAction , o.Posted , o.IsPrinted, o.OrderType
FROM PP_Order o
	INNER JOIN C_DocType_trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/
CREATE OR REPLACE VIEW PP_Order_Node_v
AS
SELECT
onode.AD_Client_ID, onode.AD_Org_ID, onode.IsActive, onode.Created, onode.CreatedBy, onode.Updated, onode.UpdatedBy,
cast('en_US' as varchar2(6)) AS AD_Language,
name, c_bpartner_id, cost, datefinish, datefinishschedule, datestart, datestartschedule , 
description, docaction, docstatus,duration, durationreal, durationrequiered, help, ismilestone,
issubcontracting, movingtime, overlapunits, 
pp_order_id, pp_order_workflow_id, onode.pp_order_node_id,priority, qtydelivered, qtyrequiered , 
qtyscrap , queuingtime , s_resource_id , setuptime ,setuptimereal,  unitscycles ,  validfrom , validto , value , waitingtime , workingtime , yield 
FROM PP_Order_Node onode;
/
CREATE OR REPLACE VIEW PP_Order_Node_vt
AS
SELECT
onode.AD_Client_ID, onode.AD_Org_ID, onode.IsActive, onode.Created, onode.CreatedBy, onode.Updated, onode.UpdatedBy,
ont.AD_Language,
ont.name, c_bpartner_id, cost, datefinish, datefinishschedule, datestart, datestartschedule , 
ont.description, docaction, docstatus, duration, durationreal, durationrequiered, ont.help, ismilestone,
issubcontracting, movingtime, overlapunits, 
pp_order_id, pp_order_workflow_id,onode.pp_order_node_id, priority, qtydelivered, qtyrequiered , 
qtyscrap , queuingtime , s_resource_id , setuptime ,setuptimereal,  unitscycles ,  validfrom , validto , value , waitingtime , workingtime , yield 
FROM PP_Order_Node onode
LEFT JOIN PP_Order_Node_Trl ont ON (ont.PP_Order_Node_ID=onode.PP_Order_Node_ID);
/
CREATE OR REPLACE VIEW PP_Order_Workflow_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar2(6)) AS AD_Language,
	o.PP_Order_ID, 
	--o.DocumentNo,
	o.DocStatus,
	o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	d.PrintName AS DocumentType, d.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	-- Order Workflow Field
	ow.Name ,ow.Description ,ow.Help,
	ow.Author, ow.Cost, ow.DocumentNo ,  ow.Duration, ow.DurationUnit , ow.Version, ow.ValidFrom , ow.ValidTo ,
	ow.MovingTime, ow.OverlapUnits , ow.PublishStatus , ow.QueuingTime , ow.SetupTime , ow.UnitsCycles,
	ow.WaitingTime , ow.WorkflowType, ow.WorkingTime , ow.Yield	
FROM PP_Order o
	INNER JOIN PP_Order_Workflow ow ON (ow.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN C_DocType d ON (o.C_DocType_ID=d.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/	
CREATE OR REPLACE VIEW PP_Order_Workflow_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	owt.AD_Language,
	o.PP_Order_ID, 
	--o.DocumentNo,
	o.DocStatus,
	o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	o.C_UOM_ID,o.PP_Product_BOM_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.S_Resource_ID ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,	
	--Trl Field 
	owt.Name , owt.Description, owt.Help,
	-- Order Workflow Field
	ow.Author, ow.Cost, ow.DocumentNo  , ow.Duration, ow.DurationUnit, ow.Version, ow.ValidFrom , ow.ValidTo ,
	ow.MovingTime, ow.OverlapUnits, ow.AD_Workflow_ID, ow.PublishStatus , ow.QueuingTime , ow.SetupTime , ow.UnitsCycles,
	ow.WaitingTime , ow.WorkflowType ,  ow.WorkingTime , ow.Yield	
FROM PP_Order o
	INNER JOIN PP_Order_Workflow ow ON (ow.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN PP_Order_Workflow_Trl owt ON (owt.PP_Order_Workflow_ID=ow.PP_Order_Workflow_ID)
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);
/
CREATE OR REPLACE VIEW PP_Product_BOMLine_v AS
SELECT 
  feature ,
  bl.ad_org_id ,
  assay ,
  backflushgroup ,
  c_uom_id,
  componenttype,
  bl.created ,
  bl.createdby,
  cast('en_US' as varchar2(6)) AS AD_Language,
  blt.description,
  forecast,
  blt.help ,
  bl.isactive ,
  iscritical ,
  isqtypercentage,
  issuemethod ,
  leadtimeoffset ,
  line ,
  m_attributesetinstance_id ,
  m_changenotice_id ,
  m_product_id ,
  bl.pp_product_bomline_id ,
  pp_product_bom_id ,
  qtybom ,
  qtybatch ,
  scrap ,
  bl.updated ,
  bl.updatedby,
  validfrom ,
  bl.ad_client_id ,
  validto  
 FROM PP_Product_BOMLine bl
 INNER JOIN PP_Product_BOMLine_Trl blt ON (blt.PP_Product_BOMLine_ID=bl.PP_Product_BOMLine_ID);
/
CREATE OR REPLACE VIEW PP_Product_BOMLine_vt AS
SELECT 
  feature ,
  bl.ad_org_id ,
  assay ,
  backflushgroup ,
  c_uom_id,
  componenttype,
  bl.created ,
  bl.createdby,
  blt.AD_Language,
  blt.description,
  forecast,
  blt.help ,
  bl.isactive ,
  iscritical ,
  isqtypercentage,
  issuemethod ,
  leadtimeoffset ,
  line ,
  m_attributesetinstance_id ,
  m_changenotice_id ,
  m_product_id ,
  bl.pp_product_bomline_id ,
  pp_product_bom_id ,
  qtybom ,
  qtybatch ,
  scrap ,
  bl.updated ,
  bl.updatedby,
  validfrom ,
  bl.ad_client_id ,
  validto  
 FROM PP_Product_BOMLine bl
 INNER JOIN PP_Product_BOMLine_Trl blt ON (blt.PP_Product_BOMLine_ID=bl.PP_Product_BOMLine_ID);
/
CREATE OR REPLACE VIEW DD_ORDER_HEADER_V
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar2(6)) AS AD_Language,
	o.DD_Order_ID,o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	o.Description,
	o.POReference,
	o.C_Charge_ID, o.ChargeAmt,
	o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule
FROM DD_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);
/

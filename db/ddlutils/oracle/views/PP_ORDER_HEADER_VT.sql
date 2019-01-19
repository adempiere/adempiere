DROP VIEW PP_Order_Header_VT;
CREATE OR REPLACE VIEW PP_Order_Header_VT
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
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID, o.User1_ID , o.User2_ID , o.User3_ID , o.User4_ID , o.AD_OrgTrx_ID ,o.C_DocTypeTarget_ID,o.ScheduleType , o.IsApproved , o.DocAction , o.Posted , o.IsPrinted, o.OrderType,
	COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN C_DocType_trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


		

SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Sep 11, 2009 12:01:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View (AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50001,TO_DATE('2009-09-11 12:01:38','YYYY-MM-DD HH24:MI:SS'),0,'Allow select the Outbound Order lines to be release to pick.','EE03','Y','Outbound Order to Release',TO_DATE('2009-09-11 12:01:38','YYYY-MM-DD HH24:MI:SS'),0,'OutboundOrderToRelease')
;

-- Sep 11, 2009 12:01:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Trl (AD_Language,AD_View_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_View_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_View t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_View_ID=50001 AND EXISTS (SELECT * FROM AD_View_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_View_ID!=t.AD_View_ID)
;

-- Sep 11, 2009 12:03:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53234,50007,50001,TO_DATE('2009-09-11 12:03:06','YYYY-MM-DD HH24:MI:SS'),0,'Y','N',10,'iobl',TO_DATE('2009-09-11 12:03:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58236,0,50323,50007,50001,'WM_InOutBoundLine_AD_Client_ID','iobl.AD_Client_ID',TO_DATE('2009-09-11 12:03:12','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_DATE('2009-09-11 12:03:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58244,0,50324,50007,50001,'WM_InOutBoundLine_AD_OrgTrx_ID','iobl.AD_OrgTrx_ID',TO_DATE('2009-09-11 12:03:13','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_DATE('2009-09-11 12:03:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:15 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58237,0,50325,50007,50001,'WM_InOutBoundLine_AD_Org_ID','iobl.AD_Org_ID',TO_DATE('2009-09-11 12:03:14','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_DATE('2009-09-11 12:03:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58245,0,50326,50007,50001,'WM_InOutBoundLine_C_Activity_I','iobl.C_Activity_ID',TO_DATE('2009-09-11 12:03:15','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_DATE('2009-09-11 12:03:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:17 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58246,0,50327,50007,50001,'WM_InOutBoundLine_C_Campaign_I','iobl.C_Campaign_ID',TO_DATE('2009-09-11 12:03:16','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_DATE('2009-09-11 12:03:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58247,0,50328,50007,50001,'WM_InOutBoundLine_C_Charge_ID','iobl.C_Charge_ID',TO_DATE('2009-09-11 12:03:17','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Charge',TO_DATE('2009-09-11 12:03:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58248,0,50329,50007,50001,'WM_InOutBoundLine_C_OrderLine_','iobl.C_OrderLine_ID',TO_DATE('2009-09-11 12:03:19','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03','The Sales Order Line is a unique identifier for a line in an order.','Y','Sales Order Line',TO_DATE('2009-09-11 12:03:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58249,0,50330,50007,50001,'WM_InOutBoundLine_C_ProjectPha','iobl.C_ProjectPhase_ID',TO_DATE('2009-09-11 12:03:20','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03','Y','Project Phase',TO_DATE('2009-09-11 12:03:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:22 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58250,0,50331,50007,50001,'WM_InOutBoundLine_C_ProjectTas','iobl.C_ProjectTask_ID',TO_DATE('2009-09-11 12:03:21','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03','A Project Task in a Project Phase represents the actual work.','Y','Project Task',TO_DATE('2009-09-11 12:03:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:23 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58251,0,50332,50007,50001,'WM_InOutBoundLine_C_Project_ID','iobl.C_Project_ID',TO_DATE('2009-09-11 12:03:22','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Project',TO_DATE('2009-09-11 12:03:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58252,0,50333,50007,50001,'WM_InOutBoundLine_C_UOM_ID','iobl.C_UOM_ID',TO_DATE('2009-09-11 12:03:23','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE03','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_DATE('2009-09-11 12:03:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58239,0,50334,50007,50001,'WM_InOutBoundLine_Created','iobl.Created',TO_DATE('2009-09-11 12:03:24','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Created',TO_DATE('2009-09-11 12:03:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58241,0,50335,50007,50001,'WM_InOutBoundLine_CreatedBy','iobl.CreatedBy',TO_DATE('2009-09-11 12:03:25','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Created By',TO_DATE('2009-09-11 12:03:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:27 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58253,0,50336,50007,50001,'WM_InOutBoundLine_Description','iobl.Description',TO_DATE('2009-09-11 12:03:25','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Description',TO_DATE('2009-09-11 12:03:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:27 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58238,0,50337,50007,50001,'WM_InOutBoundLine_IsActive','iobl.IsActive',TO_DATE('2009-09-11 12:03:27','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_DATE('2009-09-11 12:03:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:28 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58254,0,50338,50007,50001,'WM_InOutBoundLine_IsDescriptio','iobl.IsDescription',TO_DATE('2009-09-11 12:03:27','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE03','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Description Only',TO_DATE('2009-09-11 12:03:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:29 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58255,0,50339,50007,50001,'WM_InOutBoundLine_Line','iobl.Line',TO_DATE('2009-09-11 12:03:28','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE03','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Line No',TO_DATE('2009-09-11 12:03:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:30 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58256,0,50340,50007,50001,'WM_InOutBoundLine_M_AttributeS','iobl.M_AttributeSetInstance_ID',TO_DATE('2009-09-11 12:03:29','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_DATE('2009-09-11 12:03:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:31 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58258,0,50341,50007,50001,'WM_InOutBoundLine_M_Product_ID','iobl.M_Product_ID',TO_DATE('2009-09-11 12:03:30','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_DATE('2009-09-11 12:03:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58259,0,50342,50007,50001,'WM_InOutBoundLine_MovementQty','iobl.MovementQty',TO_DATE('2009-09-11 12:03:31','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.','EE03','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Movement Quantity',TO_DATE('2009-09-11 12:03:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:34 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58264,0,50343,50007,50001,'WM_InOutBoundLine_PickDate','iobl.PickDate',TO_DATE('2009-09-11 12:03:33','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Pick Date',TO_DATE('2009-09-11 12:03:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:35 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58260,0,50344,50007,50001,'WM_InOutBoundLine_PickedQty','iobl.PickedQty',TO_DATE('2009-09-11 12:03:34','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Picked Quantity',TO_DATE('2009-09-11 12:03:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58261,0,50345,50007,50001,'WM_InOutBoundLine_Processed','iobl.Processed',TO_DATE('2009-09-11 12:03:35','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_DATE('2009-09-11 12:03:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:37 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58265,0,50346,50007,50001,'WM_InOutBoundLine_ShipDate','iobl.ShipDate',TO_DATE('2009-09-11 12:03:36','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Ship Date',TO_DATE('2009-09-11 12:03:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:38 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58240,0,50347,50007,50001,'WM_InOutBoundLine_Updated','iobl.Updated',TO_DATE('2009-09-11 12:03:37','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Updated',TO_DATE('2009-09-11 12:03:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:39 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58242,0,50348,50007,50001,'WM_InOutBoundLine_UpdatedBy','iobl.UpdatedBy',TO_DATE('2009-09-11 12:03:38','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_DATE('2009-09-11 12:03:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:40 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58262,0,50349,50007,50001,'WM_InOutBoundLine_User1_ID','iobl.User1_ID',TO_DATE('2009-09-11 12:03:39','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_DATE('2009-09-11 12:03:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58263,0,50350,50007,50001,'WM_InOutBoundLine_User2_ID','iobl.User2_ID',TO_DATE('2009-09-11 12:03:40','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_DATE('2009-09-11 12:03:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:43 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58243,0,50351,50007,50001,'WM_InOutBoundLine_WM_InOutBoun','iobl.WM_InOutBoundLine_ID',TO_DATE('2009-09-11 12:03:41','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & Outbound Order Line ID',TO_DATE('2009-09-11 12:03:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:03:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58257,0,50352,50007,50001,'WM_InOutBoundLine_WM_InOutBoun','iobl.WM_InOutBound_ID',TO_DATE('2009-09-11 12:03:43','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & Outbound Order',TO_DATE('2009-09-11 12:03:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53233,50008,50001,TO_DATE('2009-09-11 12:06:35','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN WM_InOutBound iob ON (iob.WM_InOutBound_ID=iobl.WM_InOutBound_ID)','N',20,'iob',TO_DATE('2009-09-11 12:06:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:40 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58192,0,50353,50008,50001,'WM_InOutBound_AD_Client_ID','iob.AD_Client_ID',TO_DATE('2009-09-11 12:06:39','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_DATE('2009-09-11 12:06:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58200,0,50354,50008,50001,'WM_InOutBound_AD_OrgTrx_ID','iob.AD_OrgTrx_ID',TO_DATE('2009-09-11 12:06:40','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_DATE('2009-09-11 12:06:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:42 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58193,0,50355,50008,50001,'WM_InOutBound_AD_Org_ID','iob.AD_Org_ID',TO_DATE('2009-09-11 12:06:41','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_DATE('2009-09-11 12:06:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:43 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58201,0,50356,50008,50001,'WM_InOutBound_C_Activity_ID','iob.C_Activity_ID',TO_DATE('2009-09-11 12:06:42','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_DATE('2009-09-11 12:06:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58202,0,50357,50008,50001,'WM_InOutBound_C_Campaign_ID','iob.C_Campaign_ID',TO_DATE('2009-09-11 12:06:43','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_DATE('2009-09-11 12:06:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:45 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58203,0,50358,50008,50001,'WM_InOutBound_C_DocType_ID','iob.C_DocType_ID',TO_DATE('2009-09-11 12:06:44','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03','The Document Type determines document sequence and processing rules','Y','Document Type',TO_DATE('2009-09-11 12:06:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:46 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58195,0,50359,50008,50001,'WM_InOutBound_Created','iob.Created',TO_DATE('2009-09-11 12:06:45','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Created',TO_DATE('2009-09-11 12:06:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58197,0,50360,50008,50001,'WM_InOutBound_CreatedBy','iob.CreatedBy',TO_DATE('2009-09-11 12:06:46','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Created By',TO_DATE('2009-09-11 12:06:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:48 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58204,0,50361,50008,50001,'WM_InOutBound_DatePrinted','iob.DatePrinted',TO_DATE('2009-09-11 12:06:47','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE03','Indicates the Date that a document was printed.','Y','Date printed',TO_DATE('2009-09-11 12:06:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:49 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58205,0,50362,50008,50001,'WM_InOutBound_DeliveryRule','iob.DeliveryRule',TO_DATE('2009-09-11 12:06:48','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Delivery Rule',TO_DATE('2009-09-11 12:06:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:52 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58206,0,50363,50008,50001,'WM_InOutBound_DeliveryViaRule','iob.DeliveryViaRule',TO_DATE('2009-09-11 12:06:49','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Delivery Via',TO_DATE('2009-09-11 12:06:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:54 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58207,0,50364,50008,50001,'WM_InOutBound_Description','iob.Description',TO_DATE('2009-09-11 12:06:52','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Description',TO_DATE('2009-09-11 12:06:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:55 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58208,0,50365,50008,50001,'WM_InOutBound_DocAction','iob.DocAction',TO_DATE('2009-09-11 12:06:54','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03','You find the current status in the Document Status field. The options are listed in a popup','Y','Document Action',TO_DATE('2009-09-11 12:06:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:56 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58209,0,50366,50008,50001,'WM_InOutBound_DocStatus','iob.DocStatus',TO_DATE('2009-09-11 12:06:55','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE03','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Document Status',TO_DATE('2009-09-11 12:06:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:57 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58210,0,50367,50008,50001,'WM_InOutBound_DocumentNo','iob.DocumentNo',TO_DATE('2009-09-11 12:06:56','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE03','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_DATE('2009-09-11 12:06:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:06:59 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58211,0,50368,50008,50001,'WM_InOutBound_DropShip_BPartne','iob.DropShip_BPartner_ID',TO_DATE('2009-09-11 12:06:57','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE03','If empty the business partner will be shipped to.','Y','Drop Shipment Partner',TO_DATE('2009-09-11 12:06:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:00 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58212,0,50369,50008,50001,'WM_InOutBound_DropShip_Locatio','iob.DropShip_Location_ID',TO_DATE('2009-09-11 12:06:59','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE03','Y','Drop Shipment Location',TO_DATE('2009-09-11 12:06:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:01 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58213,0,50370,50008,50001,'WM_InOutBound_DropShip_User_ID','iob.DropShip_User_ID',TO_DATE('2009-09-11 12:07:00','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE03','Y','Drop Shipment Contact',TO_DATE('2009-09-11 12:07:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:01 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58214,0,50371,50008,50001,'WM_InOutBound_FreightAmt','iob.FreightAmt',TO_DATE('2009-09-11 12:07:01','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Freight Amount',TO_DATE('2009-09-11 12:07:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:03 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58215,0,50372,50008,50001,'WM_InOutBound_FreightCostRule','iob.FreightCostRule',TO_DATE('2009-09-11 12:07:01','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Freight Cost Rule',TO_DATE('2009-09-11 12:07:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:04 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58194,0,50373,50008,50001,'WM_InOutBound_IsActive','iob.IsActive',TO_DATE('2009-09-11 12:07:03','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_DATE('2009-09-11 12:07:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:05 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58216,0,50374,50008,50001,'WM_InOutBound_IsApproved','iob.IsApproved',TO_DATE('2009-09-11 12:07:04','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE03','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Approved',TO_DATE('2009-09-11 12:07:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:06 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58217,0,50375,50008,50001,'WM_InOutBound_IsDropShip','iob.IsDropShip',TO_DATE('2009-09-11 12:07:05','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE03','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Drop Shipment',TO_DATE('2009-09-11 12:07:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58218,0,50376,50008,50001,'WM_InOutBound_IsInTransit','iob.IsInTransit',TO_DATE('2009-09-11 12:07:06','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit','EE03','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','In Transit',TO_DATE('2009-09-11 12:07:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:09 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58219,0,50377,50008,50001,'WM_InOutBound_IsPrinted','iob.IsPrinted',TO_DATE('2009-09-11 12:07:07','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE03','The Printed checkbox indicates if this document or line will included when printing.','Y','Printed',TO_DATE('2009-09-11 12:07:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:10 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58220,0,50378,50008,50001,'WM_InOutBound_IsSOTrx','iob.IsSOTrx',TO_DATE('2009-09-11 12:07:09','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE03','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Sales Transaction',TO_DATE('2009-09-11 12:07:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:11 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58221,0,50379,50008,50001,'WM_InOutBound_M_Shipper_ID','iob.M_Shipper_ID',TO_DATE('2009-09-11 12:07:10','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Shipper',TO_DATE('2009-09-11 12:07:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58222,0,50380,50008,50001,'WM_InOutBound_M_Warehouse_ID','iob.M_Warehouse_ID',TO_DATE('2009-09-11 12:07:11','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_DATE('2009-09-11 12:07:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58223,0,50381,50008,50001,'WM_InOutBound_POReference','iob.POReference',TO_DATE('2009-09-11 12:07:12','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Order Reference',TO_DATE('2009-09-11 12:07:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58224,0,50382,50008,50001,'WM_InOutBound_PickDate','iob.PickDate',TO_DATE('2009-09-11 12:07:12','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Pick Date',TO_DATE('2009-09-11 12:07:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58225,0,50383,50008,50001,'WM_InOutBound_PriorityRule','iob.PriorityRule',TO_DATE('2009-09-11 12:07:13','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE03','The Priority indicates the importance (high, medium, low) of this document','Y','Priority',TO_DATE('2009-09-11 12:07:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:15 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58226,0,50384,50008,50001,'WM_InOutBound_Processed','iob.Processed',TO_DATE('2009-09-11 12:07:14','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_DATE('2009-09-11 12:07:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58227,0,50385,50008,50001,'WM_InOutBound_Processing','iob.Processing',TO_DATE('2009-09-11 12:07:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Process Now',TO_DATE('2009-09-11 12:07:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:17 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58228,0,50386,50008,50001,'WM_InOutBound_SalesRep_ID','iob.SalesRep_ID',TO_DATE('2009-09-11 12:07:16','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_DATE('2009-09-11 12:07:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:18 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58229,0,50387,50008,50001,'WM_InOutBound_SendEMail','iob.SendEMail',TO_DATE('2009-09-11 12:07:17','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Send EMail',TO_DATE('2009-09-11 12:07:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58230,0,50388,50008,50001,'WM_InOutBound_ShipDate','iob.ShipDate',TO_DATE('2009-09-11 12:07:18','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Ship Date',TO_DATE('2009-09-11 12:07:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58231,0,50389,50008,50001,'WM_InOutBound_TrackingNo','iob.TrackingNo',TO_DATE('2009-09-11 12:07:19','YYYY-MM-DD HH24:MI:SS'),0,'Number to track the shipment','EE03','Y','Tracking No',TO_DATE('2009-09-11 12:07:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58196,0,50390,50008,50001,'WM_InOutBound_Updated','iob.Updated',TO_DATE('2009-09-11 12:07:20','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Updated',TO_DATE('2009-09-11 12:07:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:22 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58198,0,50391,50008,50001,'WM_InOutBound_UpdatedBy','iob.UpdatedBy',TO_DATE('2009-09-11 12:07:21','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_DATE('2009-09-11 12:07:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:23 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58232,0,50392,50008,50001,'WM_InOutBound_User1_ID','iob.User1_ID',TO_DATE('2009-09-11 12:07:22','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_DATE('2009-09-11 12:07:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58233,0,50393,50008,50001,'WM_InOutBound_User2_ID','iob.User2_ID',TO_DATE('2009-09-11 12:07:23','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_DATE('2009-09-11 12:07:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58234,0,50394,50008,50001,'WM_InOutBound_Volume','iob.Volume',TO_DATE('2009-09-11 12:07:24','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE03','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Volume',TO_DATE('2009-09-11 12:07:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:26 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58199,0,50395,50008,50001,'WM_InOutBound_WM_InOutBound_ID','iob.WM_InOutBound_ID',TO_DATE('2009-09-11 12:07:25','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & OutBound Order ID',TO_DATE('2009-09-11 12:07:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:07:27 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,58235,0,50396,50008,50001,'WM_InOutBound_Weight','iob.Weight',TO_DATE('2009-09-11 12:07:26','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE03','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Weight',TO_DATE('2009-09-11 12:07:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:32 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,291,50009,50001,TO_DATE('2009-09-11 12:38:31','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_BPartner bp ON (pb.C_BPartner_ID=ol.C_BPartner_ID)','N',0,'bp',TO_DATE('2009-09-11 12:38:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2894,0,50397,50009,50001,'C_BPartner_AD_Client_ID','bp.AD_Client_ID',TO_DATE('2009-09-11 12:38:35','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_DATE('2009-09-11 12:38:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:37 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2914,0,50398,50009,50001,'C_BPartner_AD_Language','bp.AD_Language',TO_DATE('2009-09-11 12:38:36','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','D','The Language identifies the language to use for display and formatting','Y','Language',TO_DATE('2009-09-11 12:38:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:38 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10927,0,50399,50009,50001,'C_BPartner_AD_OrgBP_ID','bp.AD_OrgBP_ID',TO_DATE('2009-09-11 12:38:37','YYYY-MM-DD HH24:MI:SS'),0,'The Business Partner is another Organization for explicit Inter-Org transactions','D','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Linked Organization',TO_DATE('2009-09-11 12:38:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:39 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2895,0,50400,50009,50001,'C_BPartner_AD_Org_ID','bp.AD_Org_ID',TO_DATE('2009-09-11 12:38:38','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_DATE('2009-09-11 12:38:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:40 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2922,0,50401,50009,50001,'C_BPartner_AcqusitionCost','bp.AcqusitionCost',TO_DATE('2009-09-11 12:38:39','YYYY-MM-DD HH24:MI:SS'),0,'The cost of gaining the prospect as a customer','D','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Acquisition Cost',TO_DATE('2009-09-11 12:38:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:42 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2925,0,50402,50009,50001,'C_BPartner_ActualLifeTimeValue','bp.ActualLifeTimeValue',TO_DATE('2009-09-11 12:38:40','YYYY-MM-DD HH24:MI:SS'),0,'Actual Life Time Revenue','D','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Actual Life Time Value',TO_DATE('2009-09-11 12:38:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:43 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8768,0,50403,50009,50001,'C_BPartner_BPartner_Parent_ID','bp.BPartner_Parent_ID',TO_DATE('2009-09-11 12:38:42','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Parent','D','The parent (organization) of the Business Partner for reporting purposes.','Y','Partner Parent',TO_DATE('2009-09-11 12:38:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4940,0,50404,50009,50001,'C_BPartner_C_BP_Group_ID','bp.C_BP_Group_ID',TO_DATE('2009-09-11 12:38:43','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','D','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Business Partner Group',TO_DATE('2009-09-11 12:38:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:46 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2893,0,50405,50009,50001,'C_BPartner_C_BPartner_ID','bp.C_BPartner_ID',TO_DATE('2009-09-11 12:38:44','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_DATE('2009-09-11 12:38:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3085,0,50406,50009,50001,'C_BPartner_C_Dunning_ID','bp.C_Dunning_ID',TO_DATE('2009-09-11 12:38:46','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Rules for overdue invoices','D','The Dunning indicates the rules and method of dunning for past due payments.','Y','Dunning',TO_DATE('2009-09-11 12:38:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:48 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4291,0,50407,50009,50001,'C_BPartner_C_Greeting_ID','bp.C_Greeting_ID',TO_DATE('2009-09-11 12:38:47','YYYY-MM-DD HH24:MI:SS'),0,'Greeting to print on correspondence','D','The Greeting identifies the greeting to print on correspondence.','Y','Greeting',TO_DATE('2009-09-11 12:38:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:49 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2917,0,50408,50009,50001,'C_BPartner_C_InvoiceSchedule_I','bp.C_InvoiceSchedule_ID',TO_DATE('2009-09-11 12:38:48','YYYY-MM-DD HH24:MI:SS'),0,'Schedule for generating Invoices','D','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Invoice Schedule',TO_DATE('2009-09-11 12:38:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:50 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2924,0,50409,50009,50001,'C_BPartner_C_PaymentTerm_ID','bp.C_PaymentTerm_ID',TO_DATE('2009-09-11 12:38:49','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','D','Payment Terms identify the method and timing of payment.','Y','Payment Term',TO_DATE('2009-09-11 12:38:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:51 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,54463,0,50410,50009,50001,'C_BPartner_C_TaxGroup_ID','bp.C_TaxGroup_ID',TO_DATE('2009-09-11 12:38:50','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Tax Group',TO_DATE('2009-09-11 12:38:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:52 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2897,0,50411,50009,50001,'C_BPartner_Created','bp.Created',TO_DATE('2009-09-11 12:38:51','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_DATE('2009-09-11 12:38:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:53 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2898,0,50412,50009,50001,'C_BPartner_CreatedBy','bp.CreatedBy',TO_DATE('2009-09-11 12:38:52','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_DATE('2009-09-11 12:38:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:54 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2906,0,50413,50009,50001,'C_BPartner_DUNS','bp.DUNS',TO_DATE('2009-09-11 12:38:53','YYYY-MM-DD HH24:MI:SS'),0,'Dun & Bradstreet Number','D','Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','D-U-N-S',TO_DATE('2009-09-11 12:38:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:55 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4430,0,50414,50009,50001,'C_BPartner_DeliveryRule','bp.DeliveryRule',TO_DATE('2009-09-11 12:38:54','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Delivery Rule',TO_DATE('2009-09-11 12:38:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:56 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4433,0,50415,50009,50001,'C_BPartner_DeliveryViaRule','bp.DeliveryViaRule',TO_DATE('2009-09-11 12:38:55','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','D','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Delivery Via',TO_DATE('2009-09-11 12:38:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:58 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2903,0,50416,50009,50001,'C_BPartner_Description','bp.Description',TO_DATE('2009-09-11 12:38:56','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_DATE('2009-09-11 12:38:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:38:59 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3086,0,50417,50009,50001,'C_BPartner_DocumentCopies','bp.DocumentCopies',TO_DATE('2009-09-11 12:38:58','YYYY-MM-DD HH24:MI:SS'),0,'Number of copies to be printed','D','The Document Copies indicates the number of copies of each document that will be generated.','Y','Document Copies',TO_DATE('2009-09-11 12:38:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:00 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,53246,0,50418,50009,50001,'C_BPartner_DunningGrace','bp.DunningGrace',TO_DATE('2009-09-11 12:38:59','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Dunning Grace',TO_DATE('2009-09-11 12:38:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:01 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2919,0,50419,50009,50001,'C_BPartner_FirstSale','bp.FirstSale',TO_DATE('2009-09-11 12:39:00','YYYY-MM-DD HH24:MI:SS'),0,'Date of First Sale','D','The First Sale Date identifies the date of the first sale to this Business Partner','Y','First Sale',TO_DATE('2009-09-11 12:39:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:02 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,12406,0,50420,50009,50001,'C_BPartner_FlatDiscount','bp.FlatDiscount',TO_DATE('2009-09-11 12:39:01','YYYY-MM-DD HH24:MI:SS'),0,'Flat discount percentage ','D','Y','Flat Discount %',TO_DATE('2009-09-11 12:39:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:03 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4432,0,50421,50009,50001,'C_BPartner_FreightCostRule','bp.FreightCostRule',TO_DATE('2009-09-11 12:39:02','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','D','The Freight Cost Rule indicates the method used when charging for freight.','Y','Freight Cost Rule',TO_DATE('2009-09-11 12:39:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:04 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4429,0,50422,50009,50001,'C_BPartner_InvoiceRule','bp.InvoiceRule',TO_DATE('2009-09-11 12:39:03','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','D','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Invoice Rule',TO_DATE('2009-09-11 12:39:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:05 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9332,0,50423,50009,50001,'C_BPartner_Invoice_PrintFormat','bp.Invoice_PrintFormat_ID',TO_DATE('2009-09-11 12:39:04','YYYY-MM-DD HH24:MI:SS'),0,'Print Format for printing Invoices','D','You need to define a Print Format to print the document.','Y','Invoice Print Format',TO_DATE('2009-09-11 12:39:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:06 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2896,0,50424,50009,50001,'C_BPartner_IsActive','bp.IsActive',TO_DATE('2009-09-11 12:39:05','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_DATE('2009-09-11 12:39:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2916,0,50425,50009,50001,'C_BPartner_IsCustomer','bp.IsCustomer',TO_DATE('2009-09-11 12:39:06','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Customer','D','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Customer',TO_DATE('2009-09-11 12:39:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:08 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4301,0,50426,50009,50001,'C_BPartner_IsDiscountPrinted','bp.IsDiscountPrinted',TO_DATE('2009-09-11 12:39:07','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','D','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Discount Printed',TO_DATE('2009-09-11 12:39:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:09 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2927,0,50427,50009,50001,'C_BPartner_IsEmployee','bp.IsEmployee',TO_DATE('2009-09-11 12:39:08','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  this Business Partner is an employee','D','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Employee',TO_DATE('2009-09-11 12:39:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:10 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,3080,0,50428,50009,50001,'C_BPartner_IsOneTime','bp.IsOneTime',TO_DATE('2009-09-11 12:39:09','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','One time transaction',TO_DATE('2009-09-11 12:39:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:11 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2918,0,50429,50009,50001,'C_BPartner_IsProspect','bp.IsProspect',TO_DATE('2009-09-11 12:39:10','YYYY-MM-DD HH24:MI:SS'),0,'Indicates this is a Prospect','D','The Prospect checkbox indicates an entity that is an active prospect.','Y','Prospect',TO_DATE('2009-09-11 12:39:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2929,0,50430,50009,50001,'C_BPartner_IsSalesRep','bp.IsSalesRep',TO_DATE('2009-09-11 12:39:11','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  the business partner is a sales representative or company agent','D','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Sales Representative',TO_DATE('2009-09-11 12:39:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2911,0,50431,50009,50001,'C_BPartner_IsSummary','bp.IsSummary',TO_DATE('2009-09-11 12:39:12','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','D','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Summary Level',TO_DATE('2009-09-11 12:39:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3082,0,50432,50009,50001,'C_BPartner_IsTaxExempt','bp.IsTaxExempt',TO_DATE('2009-09-11 12:39:13','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax','D','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Tax exempt',TO_DATE('2009-09-11 12:39:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:15 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2915,0,50433,50009,50001,'C_BPartner_IsVendor','bp.IsVendor',TO_DATE('2009-09-11 12:39:14','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Vendor','D','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Vendor',TO_DATE('2009-09-11 12:39:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,58113,0,50434,50009,50001,'C_BPartner_Logo_ID','bp.Logo_ID',TO_DATE('2009-09-11 12:39:15','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Logo',TO_DATE('2009-09-11 12:39:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:17 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,6579,0,50435,50009,50001,'C_BPartner_M_DiscountSchema_ID','bp.M_DiscountSchema_ID',TO_DATE('2009-09-11 12:39:16','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the trade discount percentage','D','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Discount Schema',TO_DATE('2009-09-11 12:39:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2930,0,50436,50009,50001,'C_BPartner_M_PriceList_ID','bp.M_PriceList_ID',TO_DATE('2009-09-11 12:39:17','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Price List',TO_DATE('2009-09-11 12:39:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2910,0,50437,50009,50001,'C_BPartner_NAICS','bp.NAICS',TO_DATE('2009-09-11 12:39:19','YYYY-MM-DD HH24:MI:SS'),0,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','D','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','NAICS/SIC',TO_DATE('2009-09-11 12:39:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2902,0,50438,50009,50001,'C_BPartner_Name','bp.Name',TO_DATE('2009-09-11 12:39:20','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Name',TO_DATE('2009-09-11 12:39:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:22 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,4216,0,50439,50009,50001,'C_BPartner_Name2','bp.Name2',TO_DATE('2009-09-11 12:39:21','YYYY-MM-DD HH24:MI:SS'),0,'Additional Name','D','Y','Name 2',TO_DATE('2009-09-11 12:39:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:23 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2907,0,50440,50009,50001,'C_BPartner_NumberEmployees','bp.NumberEmployees',TO_DATE('2009-09-11 12:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Number of employees','D','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Employees',TO_DATE('2009-09-11 12:39:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4215,0,50441,50009,50001,'C_BPartner_POReference','bp.POReference',TO_DATE('2009-09-11 12:39:23','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','D','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Order Reference',TO_DATE('2009-09-11 12:39:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:26 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6580,0,50442,50009,50001,'C_BPartner_PO_DiscountSchema_I','bp.PO_DiscountSchema_ID',TO_DATE('2009-09-11 12:39:24','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the purchase trade discount percentage','D','Y','PO Discount Schema',TO_DATE('2009-09-11 12:39:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:27 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5826,0,50443,50009,50001,'C_BPartner_PO_PaymentTerm_ID','bp.PO_PaymentTerm_ID',TO_DATE('2009-09-11 12:39:26','YYYY-MM-DD HH24:MI:SS'),0,'Payment rules for a purchase order','D','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','PO Payment Term',TO_DATE('2009-09-11 12:39:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:28 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2931,0,50444,50009,50001,'C_BPartner_PO_PriceList_ID','bp.PO_PriceList_ID',TO_DATE('2009-09-11 12:39:27','YYYY-MM-DD HH24:MI:SS'),0,'Price List used by this Business Partner','D','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Purchase Pricelist',TO_DATE('2009-09-11 12:39:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:29 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3084,0,50445,50009,50001,'C_BPartner_PaymentRule','bp.PaymentRule',TO_DATE('2009-09-11 12:39:28','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','D','The Payment Rule indicates the method of invoice payment.','Y','Payment Rule',TO_DATE('2009-09-11 12:39:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:30 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4021,0,50446,50009,50001,'C_BPartner_PaymentRulePO','bp.PaymentRulePO',TO_DATE('2009-09-11 12:39:29','YYYY-MM-DD HH24:MI:SS'),0,'Purchase payment option','D','The Payment Rule indicates the method of purchase payment.','Y','Payment Rule',TO_DATE('2009-09-11 12:39:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:31 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2923,0,50447,50009,50001,'C_BPartner_PotentialLifeTimeVa','bp.PotentialLifeTimeValue',TO_DATE('2009-09-11 12:39:30','YYYY-MM-DD HH24:MI:SS'),0,'Total Revenue expected','D','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Potential Life Time Value',TO_DATE('2009-09-11 12:39:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:32 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3083,0,50448,50009,50001,'C_BPartner_Rating','bp.Rating',TO_DATE('2009-09-11 12:39:31','YYYY-MM-DD HH24:MI:SS'),0,'Classification or Importance','D','The Rating is used to differentiate the importance','Y','Rating',TO_DATE('2009-09-11 12:39:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2905,0,50449,50009,50001,'C_BPartner_ReferenceNo','bp.ReferenceNo',TO_DATE('2009-09-11 12:39:32','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site','D','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Reference No',TO_DATE('2009-09-11 12:39:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:34 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9862,0,50450,50009,50001,'C_BPartner_SOCreditStatus','bp.SOCreditStatus',TO_DATE('2009-09-11 12:39:33','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Credit Status','D','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Credit Status',TO_DATE('2009-09-11 12:39:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:35 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2920,0,50451,50009,50001,'C_BPartner_SO_CreditLimit','bp.SO_CreditLimit',TO_DATE('2009-09-11 12:39:34','YYYY-MM-DD HH24:MI:SS'),0,'Total outstanding invoice amounts allowed','D','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Credit Limit',TO_DATE('2009-09-11 12:39:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2921,0,50452,50009,50001,'C_BPartner_SO_CreditUsed','bp.SO_CreditUsed',TO_DATE('2009-09-11 12:39:35','YYYY-MM-DD HH24:MI:SS'),0,'Current open balance','D','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Credit Used',TO_DATE('2009-09-11 12:39:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:37 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4302,0,50453,50009,50001,'C_BPartner_SO_Description','bp.SO_Description',TO_DATE('2009-09-11 12:39:36','YYYY-MM-DD HH24:MI:SS'),0,'Description to be used on orders','D','The Order Description identifies the standard description to use on orders for this Customer.','Y','Order Description',TO_DATE('2009-09-11 12:39:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:38 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4431,0,50454,50009,50001,'C_BPartner_SalesRep_ID','bp.SalesRep_ID',TO_DATE('2009-09-11 12:39:37','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_DATE('2009-09-11 12:39:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:39 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2904,0,50455,50009,50001,'C_BPartner_SalesVolume','bp.SalesVolume',TO_DATE('2009-09-11 12:39:38','YYYY-MM-DD HH24:MI:SS'),0,'Total Volume of Sales in Thousands of Currency','D','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Sales Volume in 1.000',TO_DATE('2009-09-11 12:39:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:40 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8167,0,50456,50009,50001,'C_BPartner_SendEMail','bp.SendEMail',TO_DATE('2009-09-11 12:39:39','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','D','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Send EMail',TO_DATE('2009-09-11 12:39:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2926,0,50457,50009,50001,'C_BPartner_ShareOfCustomer','bp.ShareOfCustomer',TO_DATE('2009-09-11 12:39:40','YYYY-MM-DD HH24:MI:SS'),0,'Share of Customer''s business as a percentage','D','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Share',TO_DATE('2009-09-11 12:39:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:42 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10122,0,50458,50009,50001,'C_BPartner_ShelfLifeMinPct','bp.ShelfLifeMinPct',TO_DATE('2009-09-11 12:39:41','YYYY-MM-DD HH24:MI:SS'),0,'Minimum Shelf Life in percent based on Product Instance Guarantee Date','D','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Min Shelf Life %',TO_DATE('2009-09-11 12:39:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:43 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2909,0,50459,50009,50001,'C_BPartner_TaxID','bp.TaxID',TO_DATE('2009-09-11 12:39:42','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','D','The Tax ID field identifies the legal Identification number of this Entity.','Y','Tax ID',TO_DATE('2009-09-11 12:39:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12533,0,50460,50009,50001,'C_BPartner_TotalOpenBalance','bp.TotalOpenBalance',TO_DATE('2009-09-11 12:39:43','YYYY-MM-DD HH24:MI:SS'),0,'Total Open Balance Amount in primary Accounting Currency','D','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Open Balance',TO_DATE('2009-09-11 12:39:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:45 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3081,0,50461,50009,50001,'C_BPartner_URL','bp.URL',TO_DATE('2009-09-11 12:39:44','YYYY-MM-DD HH24:MI:SS'),0,'Full URL address - e.g. http://www.adempiere.org','D','The URL defines an fully qualified web address like http://www.adempiere.org','Y','URL',TO_DATE('2009-09-11 12:39:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2899,0,50462,50009,50001,'C_BPartner_Updated','bp.Updated',TO_DATE('2009-09-11 12:39:45','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_DATE('2009-09-11 12:39:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2900,0,50463,50009,50001,'C_BPartner_UpdatedBy','bp.UpdatedBy',TO_DATE('2009-09-11 12:39:47','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_DATE('2009-09-11 12:39:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:39:50 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2901,0,50464,50009,50001,'C_BPartner_Value','bp.Value',TO_DATE('2009-09-11 12:39:47','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Search Key',TO_DATE('2009-09-11 12:39:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:47:57 PM ECT
-- Warehouse Management
UPDATE AD_View_Definition SET SeqNo=40,Updated=TO_DATE('2009-09-11 12:47:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Definition_ID=50009
;

-- Sep 11, 2009 12:49:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,260,50010,50001,TO_DATE('2009-09-11 12:49:05','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_OrderLine ol ON (ol.C_OrderLine_ID= iobl.C_OrderLine_ID)','N',30,'ol',TO_DATE('2009-09-11 12:49:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:11 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2206,0,50465,50010,50001,'C_OrderLine_AD_Client_ID','ol.AD_Client_ID',TO_DATE('2009-09-11 12:49:10','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_DATE('2009-09-11 12:49:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15861,0,50466,50010,50001,'C_OrderLine_AD_OrgTrx_ID','ol.AD_OrgTrx_ID',TO_DATE('2009-09-11 12:49:11','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_DATE('2009-09-11 12:49:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2207,0,50467,50010,50001,'C_OrderLine_AD_Org_ID','ol.AD_Org_ID',TO_DATE('2009-09-11 12:49:12','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_DATE('2009-09-11 12:49:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15848,0,50468,50010,50001,'C_OrderLine_C_Activity_ID','ol.C_Activity_ID',TO_DATE('2009-09-11 12:49:13','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_DATE('2009-09-11 12:49:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2764,0,50469,50010,50001,'C_OrderLine_C_BPartner_ID','ol.C_BPartner_ID',TO_DATE('2009-09-11 12:49:14','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_DATE('2009-09-11 12:49:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:17 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3404,0,50470,50010,50001,'C_OrderLine_C_BPartner_Locatio','ol.C_BPartner_Location_ID',TO_DATE('2009-09-11 12:49:16','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','D','The Partner address indicates the location of a Business Partner','Y','Partner Location',TO_DATE('2009-09-11 12:49:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:18 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15847,0,50471,50010,50001,'C_OrderLine_C_Campaign_ID','ol.C_Campaign_ID',TO_DATE('2009-09-11 12:49:17','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_DATE('2009-09-11 12:49:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:18 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3050,0,50472,50010,50001,'C_OrderLine_C_Charge_ID','ol.C_Charge_ID',TO_DATE('2009-09-11 12:49:18','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Charge',TO_DATE('2009-09-11 12:49:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2230,0,50473,50010,50001,'C_OrderLine_C_Currency_ID','ol.C_Currency_ID',TO_DATE('2009-09-11 12:49:18','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','D','Indicates the Currency to be used when processing or reporting on this record','Y','Currency',TO_DATE('2009-09-11 12:49:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2205,0,50474,50010,50001,'C_OrderLine_C_OrderLine_ID','ol.C_OrderLine_ID',TO_DATE('2009-09-11 12:49:19','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','D','The Sales Order Line is a unique identifier for a line in an order.','Y','Sales Order Line',TO_DATE('2009-09-11 12:49:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2213,0,50475,50010,50001,'C_OrderLine_C_Order_ID','ol.C_Order_ID',TO_DATE('2009-09-11 12:49:20','YYYY-MM-DD HH24:MI:SS'),0,'Order','D','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Order',TO_DATE('2009-09-11 12:49:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:23 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,15457,0,50476,50010,50001,'C_OrderLine_C_ProjectPhase_ID','ol.C_ProjectPhase_ID',TO_DATE('2009-09-11 12:49:21','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','D','Y','Project Phase',TO_DATE('2009-09-11 12:49:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15458,0,50477,50010,50001,'C_OrderLine_C_ProjectTask_ID','ol.C_ProjectTask_ID',TO_DATE('2009-09-11 12:49:23','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','D','A Project Task in a Project Phase represents the actual work.','Y','Project Task',TO_DATE('2009-09-11 12:49:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14092,0,50478,50010,50001,'C_OrderLine_C_Project_ID','ol.C_Project_ID',TO_DATE('2009-09-11 12:49:24','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','D','A Project allows you to track and control internal or external activities.','Y','Project',TO_DATE('2009-09-11 12:49:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2235,0,50479,50010,50001,'C_OrderLine_C_Tax_ID','ol.C_Tax_ID',TO_DATE('2009-09-11 12:49:24','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','D','The Tax indicates the type of tax used in document line.','Y','Tax',TO_DATE('2009-09-11 12:49:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:26 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2222,0,50480,50010,50001,'C_OrderLine_C_UOM_ID','ol.C_UOM_ID',TO_DATE('2009-09-11 12:49:25','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','D','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_DATE('2009-09-11 12:49:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:28 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2209,0,50481,50010,50001,'C_OrderLine_Created','ol.Created',TO_DATE('2009-09-11 12:49:26','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_DATE('2009-09-11 12:49:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:29 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2210,0,50482,50010,50001,'C_OrderLine_CreatedBy','ol.CreatedBy',TO_DATE('2009-09-11 12:49:28','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_DATE('2009-09-11 12:49:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:30 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2218,0,50483,50010,50001,'C_OrderLine_DateDelivered','ol.DateDelivered',TO_DATE('2009-09-11 12:49:29','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','D','Y','Date Delivered',TO_DATE('2009-09-11 12:49:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2219,0,50484,50010,50001,'C_OrderLine_DateInvoiced','ol.DateInvoiced',TO_DATE('2009-09-11 12:49:30','YYYY-MM-DD HH24:MI:SS'),0,'Date printed on Invoice','D','The Date Invoice indicates the date printed on the invoice.','Y','Date Invoiced',TO_DATE('2009-09-11 12:49:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2216,0,50485,50010,50001,'C_OrderLine_DateOrdered','ol.DateOrdered',TO_DATE('2009-09-11 12:49:33','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','D','Indicates the Date an item was ordered.','Y','Date Ordered',TO_DATE('2009-09-11 12:49:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:38 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2217,0,50486,50010,50001,'C_OrderLine_DatePromised','ol.DatePromised',TO_DATE('2009-09-11 12:49:36','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','D','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_DATE('2009-09-11 12:49:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:39 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2220,0,50487,50010,50001,'C_OrderLine_Description','ol.Description',TO_DATE('2009-09-11 12:49:38','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_DATE('2009-09-11 12:49:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:40 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4031,0,50488,50010,50001,'C_OrderLine_Discount','ol.Discount',TO_DATE('2009-09-11 12:49:39','YYYY-MM-DD HH24:MI:SS'),0,'Discount in percent','D','The Discount indicates the discount applied or taken as a percentage.','Y','Discount %',TO_DATE('2009-09-11 12:49:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3049,0,50489,50010,50001,'C_OrderLine_FreightAmt','ol.FreightAmt',TO_DATE('2009-09-11 12:49:40','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','D','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Freight Amount',TO_DATE('2009-09-11 12:49:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:42 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2208,0,50490,50010,50001,'C_OrderLine_IsActive','ol.IsActive',TO_DATE('2009-09-11 12:49:41','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_DATE('2009-09-11 12:49:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9868,0,50491,50010,50001,'C_OrderLine_IsDescription','ol.IsDescription',TO_DATE('2009-09-11 12:49:42','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','D','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Description Only',TO_DATE('2009-09-11 12:49:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:45 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2214,0,50492,50010,50001,'C_OrderLine_Line','ol.Line',TO_DATE('2009-09-11 12:49:44','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','D','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Line No',TO_DATE('2009-09-11 12:49:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3723,0,50493,50010,50001,'C_OrderLine_LineNetAmt','ol.LineNetAmt',TO_DATE('2009-09-11 12:49:45','YYYY-MM-DD HH24:MI:SS'),0,'Line Extended Amount (Quantity * Actual Price) without Freight and Charges','D','Indicates the extended line amount based on the quantity and the actual price.  Any additional charges or freight are not included.  The Amount may or may not include tax.  If the price list is inclusive tax, the line amount is the same as the line total.','Y','Line Amount',TO_DATE('2009-09-11 12:49:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55323,0,50494,50010,50001,'C_OrderLine_Link_OrderLine_ID','ol.Link_OrderLine_ID',TO_DATE('2009-09-11 12:49:47','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order line to the purchase order line that is generated from it.','D','Y','Linked Order Line',TO_DATE('2009-09-11 12:49:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:48 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8767,0,50495,50010,50001,'C_OrderLine_M_AttributeSetInst','ol.M_AttributeSetInstance_ID',TO_DATE('2009-09-11 12:49:47','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','D','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_DATE('2009-09-11 12:49:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:49 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2221,0,50496,50010,50001,'C_OrderLine_M_Product_ID','ol.M_Product_ID',TO_DATE('2009-09-11 12:49:48','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','D','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_DATE('2009-09-11 12:49:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:50 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,57128,0,50497,50010,50001,'C_OrderLine_M_Promotion_ID','ol.M_Promotion_ID',TO_DATE('2009-09-11 12:49:49','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Promotion',TO_DATE('2009-09-11 12:49:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:51 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2229,0,50498,50010,50001,'C_OrderLine_M_Shipper_ID','ol.M_Shipper_ID',TO_DATE('2009-09-11 12:49:50','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','D','The Shipper indicates the method of delivering product','Y','Shipper',TO_DATE('2009-09-11 12:49:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:52 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2223,0,50499,50010,50001,'C_OrderLine_M_Warehouse_ID','ol.M_Warehouse_ID',TO_DATE('2009-09-11 12:49:51','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','D','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_DATE('2009-09-11 12:49:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:53 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,56532,0,50500,50010,50001,'C_OrderLine_PP_Cost_Collector_','ol.PP_Cost_Collector_ID',TO_DATE('2009-09-11 12:49:52','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Manufacturing Cost Collector',TO_DATE('2009-09-11 12:49:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:54 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2232,0,50501,50010,50001,'C_OrderLine_PriceActual','ol.PriceActual',TO_DATE('2009-09-11 12:49:53','YYYY-MM-DD HH24:MI:SS'),0,'Actual Price ','D','The Actual or Unit Price indicates the Price for a product in source currency.','Y','Unit Price',TO_DATE('2009-09-11 12:49:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:55 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14200,0,50502,50010,50001,'C_OrderLine_PriceCost','ol.PriceCost',TO_DATE('2009-09-11 12:49:54','YYYY-MM-DD HH24:MI:SS'),0,'Price per Unit of Measure including all indirect costs (Freight, etc.)','D','Optional Purchase Order Line cost price.','Y','Cost Price',TO_DATE('2009-09-11 12:49:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:56 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12875,0,50503,50010,50001,'C_OrderLine_PriceEntered','ol.PriceEntered',TO_DATE('2009-09-11 12:49:55','YYYY-MM-DD HH24:MI:SS'),0,'Price Entered - the price based on the selected/base UoM','D','The price entered is converted to the actual price based on the UoM conversion','Y','Price',TO_DATE('2009-09-11 12:49:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:57 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4022,0,50504,50010,50001,'C_OrderLine_PriceLimit','ol.PriceLimit',TO_DATE('2009-09-11 12:49:56','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','D','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Limit Price',TO_DATE('2009-09-11 12:49:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:49:59 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2231,0,50505,50010,50001,'C_OrderLine_PriceList','ol.PriceList',TO_DATE('2009-09-11 12:49:57','YYYY-MM-DD HH24:MI:SS'),0,'List Price','D','The List Price is the official List Price in the document currency.','Y','List Price',TO_DATE('2009-09-11 12:49:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:00 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12069,0,50506,50010,50001,'C_OrderLine_Processed','ol.Processed',TO_DATE('2009-09-11 12:49:59','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','D','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_DATE('2009-09-11 12:49:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:01 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2226,0,50507,50010,50001,'C_OrderLine_QtyDelivered','ol.QtyDelivered',TO_DATE('2009-09-11 12:50:00','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','D','The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','Delivered Quantity',TO_DATE('2009-09-11 12:50:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:02 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12876,0,50508,50010,50001,'C_OrderLine_QtyEntered','ol.QtyEntered',TO_DATE('2009-09-11 12:50:01','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','D','The Quantity Entered is converted to base product UoM quantity','Y','Quantity',TO_DATE('2009-09-11 12:50:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:04 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2227,0,50509,50010,50001,'C_OrderLine_QtyInvoiced','ol.QtyInvoiced',TO_DATE('2009-09-11 12:50:02','YYYY-MM-DD HH24:MI:SS'),0,'Invoiced Quantity','D','The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Quantity Invoiced',TO_DATE('2009-09-11 12:50:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:06 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14206,0,50510,50010,50001,'C_OrderLine_QtyLostSales','ol.QtyLostSales',TO_DATE('2009-09-11 12:50:04','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of potential sales','D','When an order is closed and there is a difference between the ordered quantity and the delivered (invoiced) quantity is the Lost Sales Quantity.  Note that the Lost Sales Quantity is 0 if you void an order, so close the order if you want to track lost opportunities.  [Void = data entry error - Close = the order is finished]','Y','Lost Sales Qty',TO_DATE('2009-09-11 12:50:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2224,0,50511,50010,50001,'C_OrderLine_QtyOrdered','ol.QtyOrdered',TO_DATE('2009-09-11 12:50:06','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','D','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Ordered Quantity',TO_DATE('2009-09-11 12:50:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:08 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2225,0,50512,50010,50001,'C_OrderLine_QtyReserved','ol.QtyReserved',TO_DATE('2009-09-11 12:50:07','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','D','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Reserved Quantity',TO_DATE('2009-09-11 12:50:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:09 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15460,0,50513,50010,50001,'C_OrderLine_RRAmt','ol.RRAmt',TO_DATE('2009-09-11 12:50:08','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Amount','D','The amount for revenue recognition calculation.  If empty, the complete invoice amount is used.  The difference between Revenue Recognition Amount and Invoice Line Net Amount is immediately recognized as revenue.','Y','Revenue Recognition Amt',TO_DATE('2009-09-11 12:50:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:10 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15459,0,50514,50010,50001,'C_OrderLine_RRStartDate','ol.RRStartDate',TO_DATE('2009-09-11 12:50:09','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Start Date','D','The date the revenue reconition starts.','Y','Revenue Recognition Start',TO_DATE('2009-09-11 12:50:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:11 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7812,0,50515,50010,50001,'C_OrderLine_Ref_OrderLine_ID','ol.Ref_OrderLine_ID',TO_DATE('2009-09-11 12:50:10','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','D','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Referenced Order Line',TO_DATE('2009-09-11 12:50:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6775,0,50516,50010,50001,'C_OrderLine_S_ResourceAssignme','ol.S_ResourceAssignment_ID',TO_DATE('2009-09-11 12:50:11','YYYY-MM-DD HH24:MI:SS'),0,'Resource Assignment','D','Y','Resource Assignment',TO_DATE('2009-09-11 12:50:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2211,0,50517,50010,50001,'C_OrderLine_Updated','ol.Updated',TO_DATE('2009-09-11 12:50:12','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_DATE('2009-09-11 12:50:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2212,0,50518,50010,50001,'C_OrderLine_UpdatedBy','ol.UpdatedBy',TO_DATE('2009-09-11 12:50:13','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_DATE('2009-09-11 12:50:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:15 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15849,0,50519,50010,50001,'C_OrderLine_User1_ID','ol.User1_ID',TO_DATE('2009-09-11 12:50:14','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_DATE('2009-09-11 12:50:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 12:50:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15850,0,50520,50010,50001,'C_OrderLine_User2_ID','ol.User2_ID',TO_DATE('2009-09-11 12:50:15','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_DATE('2009-09-11 12:50:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:24:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,Name,Processing,Updated,UpdatedBy,Value,WhereClause,AccessLevel) VALUES (50001,0,0,50001,TO_DATE('2009-09-11 13:24:26','YYYY-MM-DD HH24:MI:SS'),0,'This Smart Browse allow select the Outbound Order to release the lines that need be pick.','EE03','Y','N','Outbound Order to Release','N',TO_DATE('2009-09-11 13:24:26','YYYY-MM-DD HH24:MI:SS'),0,'OutboundOrderToRelease','IsSOTrx=''Y'' AND DocStatus=''DR''','1')
;

-- Sep 11, 2009 1:24:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50001 AND EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_ID!=t.AD_Browse_ID)
;

-- Sep 11, 2009 1:24:36 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET AD_Process_ID=53184,Updated=TO_DATE('2009-09-11 13:24:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50001
;

-- Sep 11, 2009 1:24:50 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET AccessLevel='3',Updated=TO_DATE('2009-09-11 13:24:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50001
;

-- Sep 11, 2009 1:25:29 PM ECT
-- Warehouse Management
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57530
;

-- Sep 11, 2009 1:25:29 PM ECT
-- Warehouse Management
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57455
;

-- Sep 11, 2009 1:25:45 PM ECT
-- Warehouse Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-09-11 13:25:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57530
;

-- Sep 11, 2009 1:26:07 PM ECT
-- Warehouse Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-09-11 13:26:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58110
;

UPDATE AD_Browse SET AccessLevel= 4
;

-- Sep 11, 2009 1:26:45 PM ECT
-- Warehouse Management
UPDATE AD_Column SET DefaultValue='3',Updated=TO_DATE('2009-09-11 13:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58110
;

-- Sep 11, 2009 1:27:30 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET AD_Process_ID=53186, Description='This Smart Browse allow select the Sales Order to generate an Outbound Order.',Updated=TO_DATE('2009-09-11 13:27:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50000
;

-- Sep 11, 2009 1:27:30 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Trl SET IsTranslated='N' WHERE AD_Browse_ID=50000
;

-- Sep 11, 2009 1:27:36 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET AccessLevel='3',Updated=TO_DATE('2009-09-11 13:27:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50000
;

-- Sep 11, 2009 1:29:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50323,50001,0,102,0,19,50323,TO_DATE('2009-09-11 13:29:43','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_Client_ID',10,TO_DATE('2009-09-11 13:29:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50323 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50324,50001,0,112,0,18,50324,TO_DATE('2009-09-11 13:29:44','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_OrgTrx_ID',11,TO_DATE('2009-09-11 13:29:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50324 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50325,50001,0,113,0,19,50325,TO_DATE('2009-09-11 13:29:45','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_Org_ID',12,TO_DATE('2009-09-11 13:29:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50325 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50326,50001,0,1005,0,19,50326,TO_DATE('2009-09-11 13:29:47','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Activity_I',13,TO_DATE('2009-09-11 13:29:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50326 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50327,50001,0,550,0,19,50327,TO_DATE('2009-09-11 13:29:49','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Campaign_I',14,TO_DATE('2009-09-11 13:29:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50327 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50328,50001,0,968,0,19,50328,TO_DATE('2009-09-11 13:29:50','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Charge_ID',15,TO_DATE('2009-09-11 13:29:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50328 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50329,50001,0,561,0,19,50329,TO_DATE('2009-09-11 13:29:51','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_OrderLine_',16,TO_DATE('2009-09-11 13:29:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50329 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50330,50001,0,2073,0,19,50330,TO_DATE('2009-09-11 13:29:52','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_C_ProjectPha',17,TO_DATE('2009-09-11 13:29:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50330 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50331,50001,0,2074,0,19,50331,TO_DATE('2009-09-11 13:29:53','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_ProjectTas',18,TO_DATE('2009-09-11 13:29:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50331 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50332,50001,0,208,0,19,50332,TO_DATE('2009-09-11 13:29:54','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Project_ID',19,TO_DATE('2009-09-11 13:29:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50332 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50333,50001,0,215,0,19,50333,TO_DATE('2009-09-11 13:29:55','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE03','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','WM_InOutBoundLine_C_UOM_ID',20,TO_DATE('2009-09-11 13:29:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50333 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50334,50001,0,245,0,16,50334,TO_DATE('2009-09-11 13:29:56','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','WM_InOutBoundLine_Created',21,TO_DATE('2009-09-11 13:29:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50334 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50335,50001,0,246,0,19,50335,TO_DATE('2009-09-11 13:29:57','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','WM_InOutBoundLine_CreatedBy',22,TO_DATE('2009-09-11 13:29:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50335 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:29:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50336,50001,0,275,0,14,50336,TO_DATE('2009-09-11 13:29:58','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','WM_InOutBoundLine_Description',23,TO_DATE('2009-09-11 13:29:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:29:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50336 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50337,50001,0,348,0,20,50337,TO_DATE('2009-09-11 13:29:59','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','WM_InOutBoundLine_IsActive',24,TO_DATE('2009-09-11 13:29:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50337 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50338,50001,0,2183,0,20,50338,TO_DATE('2009-09-11 13:30:00','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE03','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','WM_InOutBoundLine_IsDescriptio',25,TO_DATE('2009-09-11 13:30:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50338 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50339,50001,0,439,0,11,50339,TO_DATE('2009-09-11 13:30:01','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE03','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','WM_InOutBoundLine_Line',26,TO_DATE('2009-09-11 13:30:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50339 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50340,50001,0,2019,0,35,50340,TO_DATE('2009-09-11 13:30:03','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','WM_InOutBoundLine_M_AttributeS',27,TO_DATE('2009-09-11 13:30:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50340 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50341,50001,0,454,0,30,50341,TO_DATE('2009-09-11 13:30:04','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','WM_InOutBoundLine_M_Product_ID',28,TO_DATE('2009-09-11 13:30:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50341 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50342,50001,0,1038,0,29,50342,TO_DATE('2009-09-11 13:30:05','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.','EE03','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Y','Y','N','N','N','WM_InOutBoundLine_MovementQty',29,TO_DATE('2009-09-11 13:30:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50342 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50343,50001,0,2117,0,16,50343,TO_DATE('2009-09-11 13:30:06','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_PickDate',30,TO_DATE('2009-09-11 13:30:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50343 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50344,50001,0,2422,0,29,50344,TO_DATE('2009-09-11 13:30:07','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_PickedQty',31,TO_DATE('2009-09-11 13:30:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50344 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50345,50001,0,1047,0,20,50345,TO_DATE('2009-09-11 13:30:08','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','WM_InOutBoundLine_Processed',32,TO_DATE('2009-09-11 13:30:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50345 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50346,50001,0,2123,0,16,50346,TO_DATE('2009-09-11 13:30:09','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Y','N','N','N','N','WM_InOutBoundLine_ShipDate',33,TO_DATE('2009-09-11 13:30:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50346 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50347,50001,0,607,0,16,50347,TO_DATE('2009-09-11 13:30:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','WM_InOutBoundLine_Updated',34,TO_DATE('2009-09-11 13:30:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50347 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50348,50001,0,608,0,19,50348,TO_DATE('2009-09-11 13:30:11','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','WM_InOutBoundLine_UpdatedBy',35,TO_DATE('2009-09-11 13:30:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50348 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50349,50001,0,613,0,18,50349,TO_DATE('2009-09-11 13:30:12','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBoundLine_User1_ID',36,TO_DATE('2009-09-11 13:30:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50349 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50350,50001,0,614,0,18,50350,TO_DATE('2009-09-11 13:30:13','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBoundLine_User2_ID',37,TO_DATE('2009-09-11 13:30:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50350 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50351,50001,0,53913,0,13,50351,TO_DATE('2009-09-11 13:30:14','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_WM_InOutBoun',38,TO_DATE('2009-09-11 13:30:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50351 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50352,50001,0,53912,0,30,50352,TO_DATE('2009-09-11 13:30:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','Y','N','N','N','WM_InOutBoundLine_WM_InOutBoun',39,TO_DATE('2009-09-11 13:30:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50352 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50353,50001,0,102,0,19,50353,TO_DATE('2009-09-11 13:30:16','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','WM_InOutBound_AD_Client_ID',40,TO_DATE('2009-09-11 13:30:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50353 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50354,50001,0,399,0,20,50377,TO_DATE('2009-09-11 13:30:17','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE03','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','N','N','N','WM_InOutBound_IsPrinted',41,TO_DATE('2009-09-11 13:30:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50354 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50355,50001,0,112,0,18,50354,TO_DATE('2009-09-11 13:30:18','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','WM_InOutBound_AD_OrgTrx_ID',42,TO_DATE('2009-09-11 13:30:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50355 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50356,50001,0,113,0,19,50355,TO_DATE('2009-09-11 13:30:19','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','WM_InOutBound_AD_Org_ID',43,TO_DATE('2009-09-11 13:30:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50356 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50357,50001,0,1005,0,19,50356,TO_DATE('2009-09-11 13:30:20','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','WM_InOutBound_C_Activity_ID',44,TO_DATE('2009-09-11 13:30:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50357 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50358,50001,0,550,0,19,50357,TO_DATE('2009-09-11 13:30:23','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','WM_InOutBound_C_Campaign_ID',45,TO_DATE('2009-09-11 13:30:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50358 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50359,50001,0,196,0,18,50358,TO_DATE('2009-09-11 13:30:24','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03','The Document Type determines document sequence and processing rules','Y','Y','N','N','N','N','WM_InOutBound_C_DocType_ID',46,TO_DATE('2009-09-11 13:30:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50359 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50360,50001,0,245,0,16,50359,TO_DATE('2009-09-11 13:30:25','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','WM_InOutBound_Created',47,TO_DATE('2009-09-11 13:30:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50360 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50361,50001,0,246,0,19,50360,TO_DATE('2009-09-11 13:30:26','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','WM_InOutBound_CreatedBy',48,TO_DATE('2009-09-11 13:30:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50361 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50362,50001,0,1091,0,15,50361,TO_DATE('2009-09-11 13:30:28','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE03','Indicates the Date that a document was printed.','Y','Y','N','N','N','N','WM_InOutBound_DatePrinted',49,TO_DATE('2009-09-11 13:30:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50362 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50363,50001,0,555,0,17,50362,TO_DATE('2009-09-11 13:30:29','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','WM_InOutBound_DeliveryRule',50,TO_DATE('2009-09-11 13:30:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50363 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50364,50001,0,274,0,17,50363,TO_DATE('2009-09-11 13:30:30','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','WM_InOutBound_DeliveryViaRule',51,TO_DATE('2009-09-11 13:30:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50364 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50365,50001,0,275,0,14,50364,TO_DATE('2009-09-11 13:30:31','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','WM_InOutBound_Description',52,TO_DATE('2009-09-11 13:30:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50365 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50366,50001,0,287,0,28,50365,TO_DATE('2009-09-11 13:30:32','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','N','N','WM_InOutBound_DocAction',53,TO_DATE('2009-09-11 13:30:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50366 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50367,50001,0,289,0,17,50366,TO_DATE('2009-09-11 13:30:33','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE03','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','N','N','WM_InOutBound_DocStatus',54,TO_DATE('2009-09-11 13:30:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50367 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50368,50001,0,290,0,10,50367,TO_DATE('2009-09-11 13:30:33','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE03','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','WM_InOutBound_DocumentNo',55,TO_DATE('2009-09-11 13:30:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50368 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50369,50001,0,53458,0,18,50368,TO_DATE('2009-09-11 13:30:34','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE03','If empty the business partner will be shipped to.','Y','Y','N','N','N','N','WM_InOutBound_DropShip_BPartne',56,TO_DATE('2009-09-11 13:30:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50369 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50370,50001,0,53459,0,18,50369,TO_DATE('2009-09-11 13:30:35','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE03','Y','Y','N','N','N','N','WM_InOutBound_DropShip_Locatio',57,TO_DATE('2009-09-11 13:30:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50370 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50371,50001,0,53460,0,18,50370,TO_DATE('2009-09-11 13:30:36','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_DropShip_User_ID',58,TO_DATE('2009-09-11 13:30:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50371 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50372,50001,0,306,0,12,50371,TO_DATE('2009-09-11 13:30:37','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','WM_InOutBound_FreightAmt',59,TO_DATE('2009-09-11 13:30:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50372 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50373,50001,0,1007,0,17,50372,TO_DATE('2009-09-11 13:30:38','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','WM_InOutBound_FreightCostRule',60,TO_DATE('2009-09-11 13:30:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50373 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50374,50001,0,348,0,20,50373,TO_DATE('2009-09-11 13:30:39','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','WM_InOutBound_IsActive',61,TO_DATE('2009-09-11 13:30:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50374 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50375,50001,0,351,0,20,50374,TO_DATE('2009-09-11 13:30:40','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE03','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','N','N','N','N','WM_InOutBound_IsApproved',62,TO_DATE('2009-09-11 13:30:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50375 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50376,50001,0,2466,0,20,50375,TO_DATE('2009-09-11 13:30:41','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE03','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','N','N','N','N','WM_InOutBound_IsDropShip',63,TO_DATE('2009-09-11 13:30:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50376 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50377,50001,0,2397,0,20,50376,TO_DATE('2009-09-11 13:30:42','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit','EE03','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','Y','N','N','N','N','WM_InOutBound_IsInTransit',64,TO_DATE('2009-09-11 13:30:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50377 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50378,50001,0,1106,0,20,50378,TO_DATE('2009-09-11 13:30:43','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE03','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','WM_InOutBound_IsSOTrx',65,TO_DATE('2009-09-11 13:30:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50378 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50379,50001,0,455,0,19,50379,TO_DATE('2009-09-11 13:30:44','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','WM_InOutBound_M_Shipper_ID',66,TO_DATE('2009-09-11 13:30:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50379 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50380,50001,0,459,0,19,50380,TO_DATE('2009-09-11 13:30:45','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','WM_InOutBound_M_Warehouse_ID',67,TO_DATE('2009-09-11 13:30:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50380 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50381,50001,0,952,0,10,50381,TO_DATE('2009-09-11 13:30:46','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','WM_InOutBound_POReference',68,TO_DATE('2009-09-11 13:30:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50381 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50382,50001,0,2117,0,16,50382,TO_DATE('2009-09-11 13:30:47','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_PickDate',69,TO_DATE('2009-09-11 13:30:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50382 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50383,50001,0,522,0,17,50383,TO_DATE('2009-09-11 13:30:49','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE03','The Priority indicates the importance (high, medium, low) of this document','Y','Y','N','N','N','N','WM_InOutBound_PriorityRule',70,TO_DATE('2009-09-11 13:30:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50383 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50384,50001,0,1047,0,20,50384,TO_DATE('2009-09-11 13:30:50','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','WM_InOutBound_Processed',71,TO_DATE('2009-09-11 13:30:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50384 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50385,50001,0,524,0,28,50385,TO_DATE('2009-09-11 13:30:51','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBound_Processing',72,TO_DATE('2009-09-11 13:30:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50385 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50386,50001,0,1063,0,18,50386,TO_DATE('2009-09-11 13:30:52','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','WM_InOutBound_SalesRep_ID',73,TO_DATE('2009-09-11 13:30:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50386 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50387,50001,0,1978,0,20,50387,TO_DATE('2009-09-11 13:30:53','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','WM_InOutBound_SendEMail',74,TO_DATE('2009-09-11 13:30:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50387 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50388,50001,0,2123,0,16,50388,TO_DATE('2009-09-11 13:30:55','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Y','N','N','N','N','WM_InOutBound_ShipDate',75,TO_DATE('2009-09-11 13:30:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50388 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50389,50001,0,2126,0,10,50389,TO_DATE('2009-09-11 13:30:56','YYYY-MM-DD HH24:MI:SS'),0,'Number to track the shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_TrackingNo',76,TO_DATE('2009-09-11 13:30:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50389 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:30:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50390,50001,0,607,0,16,50390,TO_DATE('2009-09-11 13:30:57','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','WM_InOutBound_Updated',77,TO_DATE('2009-09-11 13:30:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:30:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50390 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50391,50001,0,608,0,19,50391,TO_DATE('2009-09-11 13:30:58','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','WM_InOutBound_UpdatedBy',78,TO_DATE('2009-09-11 13:30:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50391 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50392,50001,0,613,0,18,50392,TO_DATE('2009-09-11 13:31:00','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBound_User1_ID',79,TO_DATE('2009-09-11 13:31:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50392 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50393,50001,0,614,0,18,50393,TO_DATE('2009-09-11 13:31:01','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBound_User2_ID',80,TO_DATE('2009-09-11 13:31:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50393 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50394,50001,0,627,0,22,50394,TO_DATE('2009-09-11 13:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE03','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','N','N','N','N','WM_InOutBound_Volume',81,TO_DATE('2009-09-11 13:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50394 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50395,50001,0,53912,0,13,50395,TO_DATE('2009-09-11 13:31:04','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBound_WM_InOutBound_ID',82,TO_DATE('2009-09-11 13:31:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50395 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50396,50001,0,629,0,22,50396,TO_DATE('2009-09-11 13:31:05','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE03','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','N','N','N','N','WM_InOutBound_Weight',83,TO_DATE('2009-09-11 13:31:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50396 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50397,50001,0,102,0,19,50397,TO_DATE('2009-09-11 13:31:06','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_BPartner_AD_Client_ID',84,TO_DATE('2009-09-11 13:31:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50397 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50398,50001,0,109,0,18,50398,TO_DATE('2009-09-11 13:31:08','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE03','The Language identifies the language to use for display and formatting','Y','Y','N','N','N','N','C_BPartner_AD_Language',85,TO_DATE('2009-09-11 13:31:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50398 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50399,50001,0,2354,0,28,50399,TO_DATE('2009-09-11 13:31:09','YYYY-MM-DD HH24:MI:SS'),0,'The Business Partner is another Organization for explicit Inter-Org transactions','EE03','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Y','N','N','N','N','C_BPartner_AD_OrgBP_ID',86,TO_DATE('2009-09-11 13:31:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50399 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50400,50001,0,113,0,19,50400,TO_DATE('2009-09-11 13:31:10','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_BPartner_AD_Org_ID',87,TO_DATE('2009-09-11 13:31:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50400 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50401,50001,0,151,0,37,50401,TO_DATE('2009-09-11 13:31:11','YYYY-MM-DD HH24:MI:SS'),0,'The cost of gaining the prospect as a customer','EE03','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Y','N','N','N','N','C_BPartner_AcqusitionCost',88,TO_DATE('2009-09-11 13:31:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50401 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50402,50001,0,153,0,12,50402,TO_DATE('2009-09-11 13:31:12','YYYY-MM-DD HH24:MI:SS'),0,'Actual Life Time Revenue','EE03','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_ActualLifeTimeValue',89,TO_DATE('2009-09-11 13:31:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50402 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50403,50001,0,2031,0,13,50403,TO_DATE('2009-09-11 13:31:13','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Parent','EE03','The parent (organization) of the Business Partner for reporting purposes.','Y','Y','N','N','N','N','C_BPartner_BPartner_Parent_ID',90,TO_DATE('2009-09-11 13:31:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50403 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50404,50001,0,1383,0,19,50404,TO_DATE('2009-09-11 13:31:14','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE03','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','C_BPartner_C_BP_Group_ID',91,TO_DATE('2009-09-11 13:31:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50404 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50405,50001,0,187,0,13,50405,TO_DATE('2009-09-11 13:31:15','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_BPartner_C_BPartner_ID',92,TO_DATE('2009-09-11 13:31:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50405 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50406,50001,0,838,0,19,50406,TO_DATE('2009-09-11 13:31:16','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Rules for overdue invoices','EE03','The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','N','N','N','N','C_BPartner_C_Dunning_ID',93,TO_DATE('2009-09-11 13:31:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50406 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50407,50001,0,1159,0,19,50407,TO_DATE('2009-09-11 13:31:17','YYYY-MM-DD HH24:MI:SS'),0,'Greeting to print on correspondence','EE03','The Greeting identifies the greeting to print on correspondence.','Y','Y','N','N','N','N','C_BPartner_C_Greeting_ID',94,TO_DATE('2009-09-11 13:31:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50407 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50408,50001,0,560,0,19,50408,TO_DATE('2009-09-11 13:31:18','YYYY-MM-DD HH24:MI:SS'),0,'Schedule for generating Invoices','EE03','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Y','N','N','N','N','C_BPartner_C_InvoiceSchedule_I',95,TO_DATE('2009-09-11 13:31:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50408 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50409,50001,0,204,0,19,50409,TO_DATE('2009-09-11 13:31:19','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','EE03','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','C_BPartner_C_PaymentTerm_ID',96,TO_DATE('2009-09-11 13:31:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50409 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50410,50001,0,53356,0,19,50410,TO_DATE('2009-09-11 13:31:20','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_C_TaxGroup_ID',97,TO_DATE('2009-09-11 13:31:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50410 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50411,50001,0,245,0,16,50411,TO_DATE('2009-09-11 13:31:20','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_BPartner_Created',98,TO_DATE('2009-09-11 13:31:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50411 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50412,50001,0,246,0,18,50412,TO_DATE('2009-09-11 13:31:21','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_BPartner_CreatedBy',99,TO_DATE('2009-09-11 13:31:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50412 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50413,50001,0,260,0,10,50413,TO_DATE('2009-09-11 13:31:22','YYYY-MM-DD HH24:MI:SS'),0,'Dun & Bradstreet Number','EE03','Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','Y','N','N','N','N','C_BPartner_DUNS',100,TO_DATE('2009-09-11 13:31:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50413 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50414,50001,0,555,0,17,50414,TO_DATE('2009-09-11 13:31:23','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','C_BPartner_DeliveryRule',101,TO_DATE('2009-09-11 13:31:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50414 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50415,50001,0,274,0,17,50415,TO_DATE('2009-09-11 13:31:24','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','C_BPartner_DeliveryViaRule',102,TO_DATE('2009-09-11 13:31:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50415 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50416,50001,0,275,0,10,50416,TO_DATE('2009-09-11 13:31:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_BPartner_Description',103,TO_DATE('2009-09-11 13:31:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50416 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50417,50001,0,866,0,11,50417,TO_DATE('2009-09-11 13:31:27','YYYY-MM-DD HH24:MI:SS'),0,'Number of copies to be printed','EE03','The Document Copies indicates the number of copies of each document that will be generated.','Y','Y','N','N','N','N','C_BPartner_DocumentCopies',104,TO_DATE('2009-09-11 13:31:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50417 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50418,50001,0,53223,0,15,50418,TO_DATE('2009-09-11 13:31:28','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_DunningGrace',105,TO_DATE('2009-09-11 13:31:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50418 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50419,50001,0,305,0,15,50419,TO_DATE('2009-09-11 13:31:29','YYYY-MM-DD HH24:MI:SS'),0,'Date of First Sale','EE03','The First Sale Date identifies the date of the first sale to this Business Partner','Y','Y','N','N','N','N','C_BPartner_FirstSale',106,TO_DATE('2009-09-11 13:31:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50419 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50420,50001,0,1712,0,22,50420,TO_DATE('2009-09-11 13:31:30','YYYY-MM-DD HH24:MI:SS'),0,'Flat discount percentage ','EE03','Y','Y','N','N','N','N','C_BPartner_FlatDiscount',107,TO_DATE('2009-09-11 13:31:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50420 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50421,50001,0,1007,0,17,50421,TO_DATE('2009-09-11 13:31:31','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','C_BPartner_FreightCostRule',108,TO_DATE('2009-09-11 13:31:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50421 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50422,50001,0,559,0,17,50422,TO_DATE('2009-09-11 13:31:34','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','EE03','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','C_BPartner_InvoiceRule',109,TO_DATE('2009-09-11 13:31:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50422 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50423,50001,0,1822,0,18,50423,TO_DATE('2009-09-11 13:31:35','YYYY-MM-DD HH24:MI:SS'),0,'Print Format for printing Invoices','EE03','You need to define a Print Format to print the document.','Y','Y','N','N','N','N','C_BPartner_Invoice_PrintFormat',110,TO_DATE('2009-09-11 13:31:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50423 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50424,50001,0,348,0,20,50424,TO_DATE('2009-09-11 13:31:36','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_BPartner_IsActive',111,TO_DATE('2009-09-11 13:31:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50424 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50425,50001,0,364,0,20,50425,TO_DATE('2009-09-11 13:31:37','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Customer','EE03','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','N','N','N','N','C_BPartner_IsCustomer',112,TO_DATE('2009-09-11 13:31:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50425 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50426,50001,0,1239,0,20,50426,TO_DATE('2009-09-11 13:31:38','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','EE03','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','C_BPartner_IsDiscountPrinted',113,TO_DATE('2009-09-11 13:31:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50426 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50427,50001,0,373,0,20,50427,TO_DATE('2009-09-11 13:31:40','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  this Business Partner is an employee','EE03','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','N','N','N','N','C_BPartner_IsEmployee',114,TO_DATE('2009-09-11 13:31:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50427 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50428,50001,0,922,0,20,50428,TO_DATE('2009-09-11 13:31:41','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_IsOneTime',115,TO_DATE('2009-09-11 13:31:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50428 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50429,50001,0,402,0,20,50429,TO_DATE('2009-09-11 13:31:42','YYYY-MM-DD HH24:MI:SS'),0,'Indicates this is a Prospect','EE03','The Prospect checkbox indicates an entity that is an active prospect.','Y','Y','N','N','N','N','C_BPartner_IsProspect',116,TO_DATE('2009-09-11 13:31:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50429 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50430,50001,0,409,0,20,50430,TO_DATE('2009-09-11 13:31:45','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  the business partner is a sales representative or company agent','EE03','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Y','N','N','N','N','C_BPartner_IsSalesRep',117,TO_DATE('2009-09-11 13:31:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50430 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50431,50001,0,416,0,20,50431,TO_DATE('2009-09-11 13:31:46','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','EE03','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','N','N','N','N','C_BPartner_IsSummary',118,TO_DATE('2009-09-11 13:31:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50431 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50432,50001,0,930,0,20,50432,TO_DATE('2009-09-11 13:31:47','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax','EE03','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','N','N','N','N','C_BPartner_IsTaxExempt',119,TO_DATE('2009-09-11 13:31:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50432 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50433,50001,0,426,0,20,50433,TO_DATE('2009-09-11 13:31:48','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Vendor','EE03','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','N','N','N','N','C_BPartner_IsVendor',120,TO_DATE('2009-09-11 13:31:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50433 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50434,50001,0,53909,0,32,50434,TO_DATE('2009-09-11 13:31:49','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_Logo_ID',121,TO_DATE('2009-09-11 13:31:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50434 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50435,50001,0,1714,0,18,50435,TO_DATE('2009-09-11 13:31:50','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the trade discount percentage','EE03','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Y','N','N','N','N','C_BPartner_M_DiscountSchema_ID',122,TO_DATE('2009-09-11 13:31:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50435 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50436,50001,0,449,0,19,50436,TO_DATE('2009-09-11 13:31:51','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE03','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','C_BPartner_M_PriceList_ID',123,TO_DATE('2009-09-11 13:31:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50436 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50437,50001,0,468,0,10,50437,TO_DATE('2009-09-11 13:31:52','YYYY-MM-DD HH24:MI:SS'),0,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','EE03','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','Y','N','N','N','N','C_BPartner_NAICS',124,TO_DATE('2009-09-11 13:31:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50437 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50438,50001,0,469,0,10,50438,TO_DATE('2009-09-11 13:31:53','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','C_BPartner_Name',125,TO_DATE('2009-09-11 13:31:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50438 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50439,50001,0,1111,0,10,50439,TO_DATE('2009-09-11 13:31:54','YYYY-MM-DD HH24:MI:SS'),0,'Additional Name','EE03','Y','Y','N','N','N','N','C_BPartner_Name2',126,TO_DATE('2009-09-11 13:31:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50439 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50440,50001,0,473,0,11,50440,TO_DATE('2009-09-11 13:31:56','YYYY-MM-DD HH24:MI:SS'),0,'Number of employees','EE03','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Y','N','N','N','N','C_BPartner_NumberEmployees',127,TO_DATE('2009-09-11 13:31:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50440 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50441,50001,0,952,0,10,50441,TO_DATE('2009-09-11 13:31:57','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','C_BPartner_POReference',128,TO_DATE('2009-09-11 13:31:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50441 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:31:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50442,50001,0,1717,0,18,50442,TO_DATE('2009-09-11 13:31:58','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the purchase trade discount percentage','EE03','Y','Y','N','N','N','N','C_BPartner_PO_DiscountSchema_I',129,TO_DATE('2009-09-11 13:31:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:31:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50442 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50443,50001,0,1576,0,18,50443,TO_DATE('2009-09-11 13:31:59','YYYY-MM-DD HH24:MI:SS'),0,'Payment rules for a purchase order','EE03','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','Y','N','N','N','N','C_BPartner_PO_PaymentTerm_ID',130,TO_DATE('2009-09-11 13:31:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50443 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50444,50001,0,480,0,18,50444,TO_DATE('2009-09-11 13:32:00','YYYY-MM-DD HH24:MI:SS'),0,'Price List used by this Business Partner','EE03','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Y','N','N','N','N','C_BPartner_PO_PriceList_ID',131,TO_DATE('2009-09-11 13:32:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50444 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50445,50001,0,1143,0,17,50445,TO_DATE('2009-09-11 13:32:01','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','EE03','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRule',132,TO_DATE('2009-09-11 13:32:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50445 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50446,50001,0,950,0,17,50446,TO_DATE('2009-09-11 13:32:02','YYYY-MM-DD HH24:MI:SS'),0,'Purchase payment option','EE03','The Payment Rule indicates the method of purchase payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRulePO',133,TO_DATE('2009-09-11 13:32:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50446 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50447,50001,0,515,0,12,50447,TO_DATE('2009-09-11 13:32:03','YYYY-MM-DD HH24:MI:SS'),0,'Total Revenue expected','EE03','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_PotentialLifeTimeVa',134,TO_DATE('2009-09-11 13:32:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50447 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50448,50001,0,962,0,10,50448,TO_DATE('2009-09-11 13:32:05','YYYY-MM-DD HH24:MI:SS'),0,'Classification or Importance','EE03','The Rating is used to differentiate the importance','Y','Y','N','N','N','N','C_BPartner_Rating',135,TO_DATE('2009-09-11 13:32:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50448 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50449,50001,0,540,0,10,50449,TO_DATE('2009-09-11 13:32:07','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site','EE03','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Y','N','N','N','N','C_BPartner_ReferenceNo',136,TO_DATE('2009-09-11 13:32:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50449 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50450,50001,0,2181,0,17,50450,TO_DATE('2009-09-11 13:32:08','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Credit Status','EE03','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Y','N','N','N','N','C_BPartner_SOCreditStatus',137,TO_DATE('2009-09-11 13:32:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50450 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50451,50001,0,553,0,12,50451,TO_DATE('2009-09-11 13:32:09','YYYY-MM-DD HH24:MI:SS'),0,'Total outstanding invoice amounts allowed','EE03','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditLimit',138,TO_DATE('2009-09-11 13:32:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50451 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50452,50001,0,554,0,12,50452,TO_DATE('2009-09-11 13:32:09','YYYY-MM-DD HH24:MI:SS'),0,'Current open balance','EE03','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditUsed',139,TO_DATE('2009-09-11 13:32:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50452 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50453,50001,0,1244,0,10,50453,TO_DATE('2009-09-11 13:32:10','YYYY-MM-DD HH24:MI:SS'),0,'Description to be used on orders','EE03','The Order Description identifies the standard description to use on orders for this Customer.','Y','Y','N','N','N','N','C_BPartner_SO_Description',140,TO_DATE('2009-09-11 13:32:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50453 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50454,50001,0,1063,0,18,50454,TO_DATE('2009-09-11 13:32:12','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','C_BPartner_SalesRep_ID',141,TO_DATE('2009-09-11 13:32:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50454 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50455,50001,0,563,0,11,50455,TO_DATE('2009-09-11 13:32:13','YYYY-MM-DD HH24:MI:SS'),0,'Total Volume of Sales in Thousands of Currency','EE03','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Y','N','N','N','N','C_BPartner_SalesVolume',142,TO_DATE('2009-09-11 13:32:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50455 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50456,50001,0,1978,0,20,50456,TO_DATE('2009-09-11 13:32:14','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','C_BPartner_SendEMail',143,TO_DATE('2009-09-11 13:32:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50456 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50457,50001,0,569,0,11,50457,TO_DATE('2009-09-11 13:32:16','YYYY-MM-DD HH24:MI:SS'),0,'Share of Customer''s business as a percentage','EE03','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Y','N','N','N','N','C_BPartner_ShareOfCustomer',144,TO_DATE('2009-09-11 13:32:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50457 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50458,50001,0,268,0,15,50485,TO_DATE('2009-09-11 13:32:16','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE03','Indicates the Date an item was ordered.','Y','Y','N','N','N','N','C_OrderLine_DateOrdered',145,TO_DATE('2009-09-11 13:32:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50458 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50459,50001,0,2240,0,11,50458,TO_DATE('2009-09-11 13:32:17','YYYY-MM-DD HH24:MI:SS'),0,'Minimum Shelf Life in percent based on Product Instance Guarantee Date','EE03','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Y','N','N','N','N','C_BPartner_ShelfLifeMinPct',146,TO_DATE('2009-09-11 13:32:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50459 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50460,50001,0,590,0,10,50459,TO_DATE('2009-09-11 13:32:18','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE03','The Tax ID field identifies the legal Identification number of this Entity.','Y','Y','N','N','N','N','C_BPartner_TaxID',147,TO_DATE('2009-09-11 13:32:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50460 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50461,50001,0,2562,0,12,50460,TO_DATE('2009-09-11 13:32:19','YYYY-MM-DD HH24:MI:SS'),0,'Total Open Balance Amount in primary Accounting Currency','EE03','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Y','N','N','N','N','C_BPartner_TotalOpenBalance',148,TO_DATE('2009-09-11 13:32:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50461 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50462,50001,0,983,0,40,50461,TO_DATE('2009-09-11 13:32:20','YYYY-MM-DD HH24:MI:SS'),0,'Full URL address - e.g. http://www.adempiere.org','EE03','The URL defines an fully qualified web address like http://www.adempiere.org','Y','Y','N','N','N','N','C_BPartner_URL',149,TO_DATE('2009-09-11 13:32:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50462 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50463,50001,0,607,0,16,50462,TO_DATE('2009-09-11 13:32:22','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_BPartner_Updated',150,TO_DATE('2009-09-11 13:32:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50463 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50464,50001,0,608,0,18,50463,TO_DATE('2009-09-11 13:32:23','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_BPartner_UpdatedBy',151,TO_DATE('2009-09-11 13:32:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50464 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50465,50001,0,620,0,10,50464,TO_DATE('2009-09-11 13:32:24','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE03','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','N','N','N','N','C_BPartner_Value',152,TO_DATE('2009-09-11 13:32:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50465 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50466,50001,0,102,0,19,50465,TO_DATE('2009-09-11 13:32:25','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_OrderLine_AD_Client_ID',153,TO_DATE('2009-09-11 13:32:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50466 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50467,50001,0,112,0,18,50466,TO_DATE('2009-09-11 13:32:26','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','C_OrderLine_AD_OrgTrx_ID',154,TO_DATE('2009-09-11 13:32:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50467 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50468,50001,0,113,0,19,50467,TO_DATE('2009-09-11 13:32:27','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_OrderLine_AD_Org_ID',155,TO_DATE('2009-09-11 13:32:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50468 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50469,50001,0,1005,0,19,50468,TO_DATE('2009-09-11 13:32:28','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','C_OrderLine_C_Activity_ID',156,TO_DATE('2009-09-11 13:32:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50469 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50470,50001,0,187,0,30,50469,TO_DATE('2009-09-11 13:32:29','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_ID',157,TO_DATE('2009-09-11 13:32:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50470 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50471,50001,0,189,0,19,50470,TO_DATE('2009-09-11 13:32:30','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE03','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_Locatio',158,TO_DATE('2009-09-11 13:32:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50471 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50472,50001,0,550,0,19,50471,TO_DATE('2009-09-11 13:32:31','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','C_OrderLine_C_Campaign_ID',159,TO_DATE('2009-09-11 13:32:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50472 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50473,50001,0,968,0,19,50472,TO_DATE('2009-09-11 13:32:32','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','C_OrderLine_C_Charge_ID',160,TO_DATE('2009-09-11 13:32:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50473 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50474,50001,0,193,0,19,50473,TO_DATE('2009-09-11 13:32:33','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','EE03','Indicates the Currency to be used when processing or reporting on this record','Y','Y','N','N','N','N','C_OrderLine_C_Currency_ID',161,TO_DATE('2009-09-11 13:32:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50474 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50475,50001,0,561,0,13,50474,TO_DATE('2009-09-11 13:32:34','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','N','N','N','N','C_OrderLine_C_OrderLine_ID',162,TO_DATE('2009-09-11 13:32:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50475 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50476,50001,0,558,0,30,50475,TO_DATE('2009-09-11 13:32:35','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE03','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','Y','N','N','N','C_OrderLine_C_Order_ID',163,TO_DATE('2009-09-11 13:32:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50476 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50477,50001,0,2073,0,19,50476,TO_DATE('2009-09-11 13:32:36','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03','Y','Y','N','N','N','N','C_OrderLine_C_ProjectPhase_ID',164,TO_DATE('2009-09-11 13:32:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50477 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50478,50001,0,2074,0,19,50477,TO_DATE('2009-09-11 13:32:37','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','C_OrderLine_C_ProjectTask_ID',165,TO_DATE('2009-09-11 13:32:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50478 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50479,50001,0,208,0,19,50478,TO_DATE('2009-09-11 13:32:38','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','C_OrderLine_C_Project_ID',166,TO_DATE('2009-09-11 13:32:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50479 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50480,50001,0,213,0,19,50479,TO_DATE('2009-09-11 13:32:39','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','EE03','The Tax indicates the type of tax used in document line.','Y','Y','N','N','N','N','C_OrderLine_C_Tax_ID',167,TO_DATE('2009-09-11 13:32:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50480 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50481,50001,0,215,0,19,50480,TO_DATE('2009-09-11 13:32:41','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE03','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','C_OrderLine_C_UOM_ID',168,TO_DATE('2009-09-11 13:32:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50481 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50482,50001,0,245,0,16,50481,TO_DATE('2009-09-11 13:32:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_OrderLine_Created',169,TO_DATE('2009-09-11 13:32:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50482 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50483,50001,0,246,0,18,50482,TO_DATE('2009-09-11 13:32:43','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_OrderLine_CreatedBy',170,TO_DATE('2009-09-11 13:32:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50483 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50484,50001,0,264,0,15,50483,TO_DATE('2009-09-11 13:32:44','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE03','Y','Y','N','N','N','N','C_OrderLine_DateDelivered',171,TO_DATE('2009-09-11 13:32:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50484 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50485,50001,0,267,0,15,50484,TO_DATE('2009-09-11 13:32:45','YYYY-MM-DD HH24:MI:SS'),0,'Date printed on Invoice','EE03','The Date Invoice indicates the date printed on the invoice.','Y','Y','N','N','N','N','C_OrderLine_DateInvoiced',172,TO_DATE('2009-09-11 13:32:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50485 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50486,50001,0,269,0,15,50486,TO_DATE('2009-09-11 13:32:47','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE03','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','N','N','N','C_OrderLine_DatePromised',173,TO_DATE('2009-09-11 13:32:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50486 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50487,50001,0,275,0,14,50487,TO_DATE('2009-09-11 13:32:47','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_OrderLine_Description',174,TO_DATE('2009-09-11 13:32:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50487 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50488,50001,0,280,0,22,50488,TO_DATE('2009-09-11 13:32:48','YYYY-MM-DD HH24:MI:SS'),0,'Discount in percent','EE03','The Discount indicates the discount applied or taken as a percentage.','Y','Y','N','N','N','N','C_OrderLine_Discount',175,TO_DATE('2009-09-11 13:32:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50488 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50489,50001,0,306,0,12,50489,TO_DATE('2009-09-11 13:32:49','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','C_OrderLine_FreightAmt',176,TO_DATE('2009-09-11 13:32:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50489 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50490,50001,0,348,0,20,50490,TO_DATE('2009-09-11 13:32:50','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_OrderLine_IsActive',177,TO_DATE('2009-09-11 13:32:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50490 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50491,50001,0,2183,0,20,50491,TO_DATE('2009-09-11 13:32:51','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE03','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','C_OrderLine_IsDescription',178,TO_DATE('2009-09-11 13:32:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50491 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50492,50001,0,439,0,11,50492,TO_DATE('2009-09-11 13:32:52','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE03','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','C_OrderLine_Line',179,TO_DATE('2009-09-11 13:32:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50492 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50493,50001,0,441,0,12,50493,TO_DATE('2009-09-11 13:32:54','YYYY-MM-DD HH24:MI:SS'),0,'Line Extended Amount (Quantity * Actual Price) without Freight and Charges','EE03','Indicates the extended line amount based on the quantity and the actual price.  Any additional charges or freight are not included.  The Amount may or may not include tax.  If the price list is inclusive tax, the line amount is the same as the line total.','Y','Y','Y','N','N','N','C_OrderLine_LineNetAmt',180,TO_DATE('2009-09-11 13:32:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50493 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50494,50001,0,53463,0,18,50494,TO_DATE('2009-09-11 13:32:55','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order line to the purchase order line that is generated from it.','EE03','Y','Y','N','N','N','N','C_OrderLine_Link_OrderLine_ID',181,TO_DATE('2009-09-11 13:32:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50494 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50495,50001,0,2019,0,35,50495,TO_DATE('2009-09-11 13:32:56','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','C_OrderLine_M_AttributeSetInst',182,TO_DATE('2009-09-11 13:32:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50495 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50496,50001,0,454,0,30,50496,TO_DATE('2009-09-11 13:32:57','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03','Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','C_OrderLine_M_Product_ID',183,TO_DATE('2009-09-11 13:32:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50496 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:32:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50497,50001,0,53802,0,19,50497,TO_DATE('2009-09-11 13:32:58','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_OrderLine_M_Promotion_ID',184,TO_DATE('2009-09-11 13:32:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:32:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50497 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50498,50001,0,455,0,19,50498,TO_DATE('2009-09-11 13:32:59','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','C_OrderLine_M_Shipper_ID',185,TO_DATE('2009-09-11 13:32:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50498 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50499,50001,0,459,0,18,50499,TO_DATE('2009-09-11 13:33:00','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','C_OrderLine_M_Warehouse_ID',186,TO_DATE('2009-09-11 13:33:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50499 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50500,50001,0,53310,0,19,50500,TO_DATE('2009-09-11 13:33:01','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_OrderLine_PP_Cost_Collector_',187,TO_DATE('2009-09-11 13:33:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50500 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50501,50001,0,519,0,37,50501,TO_DATE('2009-09-11 13:33:02','YYYY-MM-DD HH24:MI:SS'),0,'Actual Price ','EE03','The Actual or Unit Price indicates the Price for a product in source currency.','Y','Y','N','N','N','N','C_OrderLine_PriceActual',188,TO_DATE('2009-09-11 13:33:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50501 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50502,50001,0,2821,0,37,50502,TO_DATE('2009-09-11 13:33:03','YYYY-MM-DD HH24:MI:SS'),0,'Price per Unit of Measure including all indirect costs (Freight, etc.)','EE03','Optional Purchase Order Line cost price.','Y','Y','N','N','N','N','C_OrderLine_PriceCost',189,TO_DATE('2009-09-11 13:33:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50502 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50503,50001,0,2588,0,37,50503,TO_DATE('2009-09-11 13:33:04','YYYY-MM-DD HH24:MI:SS'),0,'Price Entered - the price based on the selected/base UoM','EE03','The price entered is converted to the actual price based on the UoM conversion','Y','Y','N','N','N','N','C_OrderLine_PriceEntered',190,TO_DATE('2009-09-11 13:33:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50503 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50504,50001,0,955,0,37,50504,TO_DATE('2009-09-11 13:33:05','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','EE03','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Y','N','N','N','N','C_OrderLine_PriceLimit',191,TO_DATE('2009-09-11 13:33:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50504 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50505,50001,0,520,0,37,50505,TO_DATE('2009-09-11 13:33:06','YYYY-MM-DD HH24:MI:SS'),0,'List Price','EE03','The List Price is the official List Price in the document currency.','Y','Y','N','N','N','N','C_OrderLine_PriceList',192,TO_DATE('2009-09-11 13:33:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50505 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50506,50001,0,1047,0,20,50506,TO_DATE('2009-09-11 13:33:07','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','C_OrderLine_Processed',193,TO_DATE('2009-09-11 13:33:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50506 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50507,50001,0,528,0,29,50507,TO_DATE('2009-09-11 13:33:08','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE03','The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','Y','N','N','N','N','C_OrderLine_QtyDelivered',194,TO_DATE('2009-09-11 13:33:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50507 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50508,50001,0,2589,0,29,50508,TO_DATE('2009-09-11 13:33:09','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE03','The Quantity Entered is converted to base product UoM quantity','Y','Y','N','N','N','N','C_OrderLine_QtyEntered',195,TO_DATE('2009-09-11 13:33:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50508 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50509,50001,0,529,0,29,50509,TO_DATE('2009-09-11 13:33:11','YYYY-MM-DD HH24:MI:SS'),0,'Invoiced Quantity','EE03','The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Y','N','N','N','N','C_OrderLine_QtyInvoiced',196,TO_DATE('2009-09-11 13:33:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50509 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50510,50001,0,2826,0,29,50510,TO_DATE('2009-09-11 13:33:12','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of potential sales','EE03','When an order is closed and there is a difference between the ordered quantity and the delivered (invoiced) quantity is the Lost Sales Quantity.  Note that the Lost Sales Quantity is 0 if you void an order, so close the order if you want to track lost opportunities.  [Void = data entry error - Close = the order is finished]','Y','Y','N','N','N','N','C_OrderLine_QtyLostSales',197,TO_DATE('2009-09-11 13:33:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50510 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50511,50001,0,531,0,29,50511,TO_DATE('2009-09-11 13:33:13','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE03','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Y','N','N','N','N','C_OrderLine_QtyOrdered',198,TO_DATE('2009-09-11 13:33:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50511 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50512,50001,0,532,0,29,50512,TO_DATE('2009-09-11 13:33:14','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE03','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Y','N','N','N','N','C_OrderLine_QtyReserved',199,TO_DATE('2009-09-11 13:33:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50512 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50513,50001,0,3033,0,12,50513,TO_DATE('2009-09-11 13:33:16','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Amount','EE03','The amount for revenue recognition calculation.  If empty, the complete invoice amount is used.  The difference between Revenue Recognition Amount and Invoice Line Net Amount is immediately recognized as revenue.','Y','Y','N','N','N','N','C_OrderLine_RRAmt',200,TO_DATE('2009-09-11 13:33:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50513 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50514,50001,0,3032,0,16,50514,TO_DATE('2009-09-11 13:33:17','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Start Date','EE03','The date the revenue reconition starts.','Y','Y','N','N','N','N','C_OrderLine_RRStartDate',201,TO_DATE('2009-09-11 13:33:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50514 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50515,50001,0,1905,0,30,50515,TO_DATE('2009-09-11 13:33:18','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','EE03','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Y','N','N','N','N','C_OrderLine_Ref_OrderLine_ID',202,TO_DATE('2009-09-11 13:33:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50515 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50516,50001,0,1778,0,33,50516,TO_DATE('2009-09-11 13:33:19','YYYY-MM-DD HH24:MI:SS'),0,'Resource Assignment','EE03','Y','Y','N','N','N','N','C_OrderLine_S_ResourceAssignme',203,TO_DATE('2009-09-11 13:33:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50516 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50517,50001,0,607,0,16,50517,TO_DATE('2009-09-11 13:33:20','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_OrderLine_Updated',204,TO_DATE('2009-09-11 13:33:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50517 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50518,50001,0,608,0,18,50518,TO_DATE('2009-09-11 13:33:21','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_OrderLine_UpdatedBy',205,TO_DATE('2009-09-11 13:33:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50518 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50519,50001,0,613,0,18,50519,TO_DATE('2009-09-11 13:33:22','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User1_ID',206,TO_DATE('2009-09-11 13:33:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50519 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:33:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50520,50001,0,614,0,18,50520,TO_DATE('2009-09-11 13:33:23','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User2_ID',207,TO_DATE('2009-09-11 13:33:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 1:33:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50520 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50438
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50486
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50329
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50333
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50341
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50342
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50343
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50344
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50346
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50351
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50368
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50324
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50325
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50326
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50327
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50328
;

-- Sep 11, 2009 1:40:01 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50330
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50331
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50332
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50334
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50335
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50336
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50337
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50338
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50339
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50340
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50345
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50347
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50348
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50349
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50350
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50353
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50354
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50355
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50356
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50357
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50358
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50359
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50360
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50361
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50362
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50363
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50364
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50365
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50366
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50367
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50369
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50370
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50371
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50372
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50373
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50374
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50375
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50376
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50377
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50378
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50381
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50383
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50384
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50385
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50386
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50387
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50388
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50389
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50390
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50391
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50392
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=610,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50393
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=620,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50394
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=630,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=640,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50396
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=650,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50397
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50398
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=670,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50399
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=680,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50400
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=690,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50401
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=700,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50402
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=710,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50403
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=720,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50404
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=730,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50405
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=740,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50406
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=750,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50407
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=760,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50408
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=770,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50409
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=780,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50410
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=790,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50411
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=800,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50412
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=810,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50413
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=820,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50414
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=830,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50415
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=840,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50416
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=850,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50417
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=860,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50418
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=870,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50419
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=880,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50420
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=890,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50421
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=900,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50422
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=910,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50423
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=920,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50424
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=930,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50425
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=940,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50426
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=950,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50427
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=960,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50428
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=970,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50429
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=980,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50430
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=990,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50431
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1000,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50432
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1010,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50433
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1020,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50434
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1030,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50435
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1040,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50436
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1050,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50437
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1060,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50439
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1070,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50440
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1080,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50441
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1090,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50442
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50443
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50444
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50445
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50446
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50447
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50448
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50449
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50450
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1180,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50451
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1190,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50452
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1200,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50453
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1210,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50454
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1220,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50455
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1230,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50456
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1240,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50457
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1250,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50458
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1260,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50459
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1270,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50460
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1280,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50461
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1290,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50462
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1300,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50463
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1310,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50464
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1320,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50465
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1330,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50466
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1340,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50467
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1350,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50468
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1360,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50469
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1370,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50470
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1380,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50471
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1390,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50472
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1400,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50473
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1410,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50474
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1420,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50475
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1430,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50476
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1440,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50477
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1450,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50478
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1460,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50479
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1470,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50480
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1480,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50481
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1490,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50482
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1500,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50483
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1510,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50484
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1520,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50485
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1530,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50487
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1540,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50488
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1550,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50489
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1560,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50490
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1570,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50491
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1580,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50492
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1590,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50493
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1600,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50494
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1610,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50495
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1620,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50496
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1630,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50497
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1640,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50498
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1650,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50499
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1660,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50500
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1670,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50501
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1680,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50502
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1690,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50503
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1700,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50504
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1710,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50505
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1720,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50506
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1730,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50507
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1740,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50508
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1750,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50509
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1760,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50510
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1770,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50511
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1780,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50512
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1790,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50513
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1800,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50514
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1810,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50515
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1820,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50516
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1830,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50517
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1840,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50518
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1850,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50519
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1860,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50520
;

-- Sep 11, 2009 1:40:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=1870,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 1:40:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_C_Activity_ID',Updated=TO_DATE('2009-09-11 13:40:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50326
;

-- Sep 11, 2009 1:40:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50326
;

-- Sep 11, 2009 1:40:56 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_C_Campaign_ID',Updated=TO_DATE('2009-09-11 13:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50327
;

-- Sep 11, 2009 1:40:56 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50327
;

-- Sep 11, 2009 1:47:05 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_C_ProjectPhase_ID',Updated=TO_DATE('2009-09-11 13:47:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50330
;

-- Sep 11, 2009 1:47:05 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50330
;

-- Sep 11, 2009 1:47:19 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_C_ProjectTask_ID',Updated=TO_DATE('2009-09-11 13:47:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50331
;

-- Sep 11, 2009 1:47:19 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50331
;

-- Sep 11, 2009 1:47:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_IsDescription',Updated=TO_DATE('2009-09-11 13:47:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50338
;

-- Sep 11, 2009 1:47:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50338
;

-- Sep 11, 2009 1:47:46 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-11 13:47:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50340
;

-- Sep 11, 2009 1:47:46 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50340
;

-- Sep 11, 2009 1:48:53 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_WM_InOutBound_ID',Updated=TO_DATE('2009-09-11 13:48:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 1:48:53 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 1:49:09 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBound_DropShip_BPartner_ID',Updated=TO_DATE('2009-09-11 13:49:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50369
;

-- Sep 11, 2009 1:49:09 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50369
;

-- Sep 11, 2009 1:49:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBound_DropShip_Location_ID',Updated=TO_DATE('2009-09-11 13:49:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50370
;

-- Sep 11, 2009 1:49:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50370
;

-- Sep 11, 2009 1:49:36 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_BPartner_C_InvoiceSchedule_ID',Updated=TO_DATE('2009-09-11 13:49:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50408
;

-- Sep 11, 2009 1:49:36 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50408
;

-- Sep 11, 2009 1:50:06 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_BPartner_PO_DiscountSchema_ID',Updated=TO_DATE('2009-09-11 13:50:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50442
;

-- Sep 11, 2009 1:50:06 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50442
;

-- Sep 11, 2009 1:50:37 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_OrderLine_C_BPartner_Location_ID',Updated=TO_DATE('2009-09-11 13:50:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50471
;

-- Sep 11, 2009 1:50:37 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50471
;

-- Sep 11, 2009 1:51:00 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_OrderLine_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-11 13:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50495
;

-- Sep 11, 2009 1:51:00 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50495
;

-- Sep 11, 2009 1:51:09 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_OrderLine_PP_Cost_Collector_ID',Updated=TO_DATE('2009-09-11 13:51:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50500
;

-- Sep 11, 2009 1:51:09 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50500
;

-- Sep 11, 2009 1:51:25 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='C_OrderLine_S_ResourceAssignment_ID',Updated=TO_DATE('2009-09-11 13:51:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50516
;

-- Sep 11, 2009 1:51:25 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50516
;

-- Sep 11, 2009 1:56:29 PM ECT
-- Warehouse Management
UPDATE AD_Menu SET AD_Browse_ID=50000, AD_Process_ID=NULL, Action='S',Updated=TO_DATE('2009-09-11 13:56:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53233
;

-- Sep 11, 2009 1:57:16 PM ECT
-- Warehouse Management
UPDATE AD_Menu SET AD_Browse_ID=50001, AD_Process_ID=NULL, Action='S',Updated=TO_DATE('2009-09-11 13:57:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53232
;


-- Sep 11, 2009 2:07:23 PM ECT
-- Warehouse Management
UPDATE AD_View SET Description='Allow select the Outbound Order lines to be release to pick or ship.', Name='Outbound Order Lines', Value='OutboundOrderLines',Updated=TO_DATE('2009-09-11 14:07:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_ID=50001
;

-- Sep 11, 2009 2:07:23 PM ECT
-- Warehouse Management
UPDATE AD_View_Trl SET IsTranslated='N' WHERE AD_View_ID=50001
;

-- Sep 11, 2009 2:14:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_View_ID,AccessLevel,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,Name,Processing,Updated,UpdatedBy,Value,WhereClause) VALUES (50002,0,0,53184,50001,'3',TO_DATE('2009-09-11 14:14:01','YYYY-MM-DD HH24:MI:SS'),0,'This Smart Browse allow select the Outbound Order Lines ready to be to ship.','EE03','Y','N','Outbound Order Lines to Ship','N',TO_DATE('2009-09-11 14:14:01','YYYY-MM-DD HH24:MI:SS'),0,'OutboundOrderToShip','iob.IsSOTrx=''Y'' AND iob.DocStatus=''CO'' AND iobl.PickedQty > iobl.MovementQty AND iobl.Processed=''N''')
;

-- Sep 11, 2009 2:14:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50002 AND EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_ID!=t.AD_Browse_ID)
;

-- Sep 11, 2009 2:14:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50521,50002,0,102,0,19,50323,TO_DATE('2009-09-11 14:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_Client_ID',10,TO_DATE('2009-09-11 14:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50521 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50522,50002,0,112,0,18,50324,TO_DATE('2009-09-11 14:14:09','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_OrgTrx_ID',11,TO_DATE('2009-09-11 14:14:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50522 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50523,50002,0,113,0,19,50325,TO_DATE('2009-09-11 14:14:10','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','WM_InOutBoundLine_AD_Org_ID',12,TO_DATE('2009-09-11 14:14:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50523 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50524,50002,0,1005,0,19,50326,TO_DATE('2009-09-11 14:14:11','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Activity_I',13,TO_DATE('2009-09-11 14:14:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50524 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50525,50002,0,550,0,19,50327,TO_DATE('2009-09-11 14:14:12','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Campaign_I',14,TO_DATE('2009-09-11 14:14:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50525 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50526,50002,0,968,0,19,50328,TO_DATE('2009-09-11 14:14:13','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Charge_ID',15,TO_DATE('2009-09-11 14:14:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50526 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50527,50002,0,561,0,19,50329,TO_DATE('2009-09-11 14:14:15','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_OrderLine_',16,TO_DATE('2009-09-11 14:14:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50527 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50528,50002,0,2073,0,19,50330,TO_DATE('2009-09-11 14:14:16','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_C_ProjectPha',17,TO_DATE('2009-09-11 14:14:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50528 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50529,50002,0,2074,0,19,50331,TO_DATE('2009-09-11 14:14:17','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_ProjectTas',18,TO_DATE('2009-09-11 14:14:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50529 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50530,50002,0,208,0,19,50332,TO_DATE('2009-09-11 14:14:19','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','WM_InOutBoundLine_C_Project_ID',19,TO_DATE('2009-09-11 14:14:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50530 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50531,50002,0,215,0,19,50333,TO_DATE('2009-09-11 14:14:20','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE03','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','WM_InOutBoundLine_C_UOM_ID',20,TO_DATE('2009-09-11 14:14:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50531 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50532,50002,0,245,0,16,50334,TO_DATE('2009-09-11 14:14:21','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','WM_InOutBoundLine_Created',21,TO_DATE('2009-09-11 14:14:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50532 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50533,50002,0,246,0,19,50335,TO_DATE('2009-09-11 14:14:22','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','WM_InOutBoundLine_CreatedBy',22,TO_DATE('2009-09-11 14:14:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50533 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50534,50002,0,275,0,14,50336,TO_DATE('2009-09-11 14:14:23','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','WM_InOutBoundLine_Description',23,TO_DATE('2009-09-11 14:14:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50534 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50535,50002,0,348,0,20,50337,TO_DATE('2009-09-11 14:14:25','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','WM_InOutBoundLine_IsActive',24,TO_DATE('2009-09-11 14:14:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50535 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50536,50002,0,2183,0,20,50338,TO_DATE('2009-09-11 14:14:26','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE03','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','WM_InOutBoundLine_IsDescriptio',25,TO_DATE('2009-09-11 14:14:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50536 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50537,50002,0,439,0,11,50339,TO_DATE('2009-09-11 14:14:27','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE03','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','WM_InOutBoundLine_Line',26,TO_DATE('2009-09-11 14:14:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50537 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50538,50002,0,2019,0,35,50340,TO_DATE('2009-09-11 14:14:28','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','WM_InOutBoundLine_M_AttributeS',27,TO_DATE('2009-09-11 14:14:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50538 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50539,50002,0,454,0,30,50341,TO_DATE('2009-09-11 14:14:29','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','WM_InOutBoundLine_M_Product_ID',28,TO_DATE('2009-09-11 14:14:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50539 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50540,50002,0,1038,0,29,50342,TO_DATE('2009-09-11 14:14:30','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.','EE03','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Y','Y','N','N','N','WM_InOutBoundLine_MovementQty',29,TO_DATE('2009-09-11 14:14:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50540 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50541,50002,0,2117,0,16,50343,TO_DATE('2009-09-11 14:14:31','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_PickDate',30,TO_DATE('2009-09-11 14:14:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50541 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50542,50002,0,2422,0,29,50344,TO_DATE('2009-09-11 14:14:32','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_PickedQty',31,TO_DATE('2009-09-11 14:14:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50542 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50543,50002,0,1047,0,20,50345,TO_DATE('2009-09-11 14:14:35','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','WM_InOutBoundLine_Processed',32,TO_DATE('2009-09-11 14:14:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50543 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50544,50002,0,2123,0,16,50346,TO_DATE('2009-09-11 14:14:36','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Y','N','N','N','N','WM_InOutBoundLine_ShipDate',33,TO_DATE('2009-09-11 14:14:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50544 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50545,50002,0,607,0,16,50347,TO_DATE('2009-09-11 14:14:37','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','WM_InOutBoundLine_Updated',34,TO_DATE('2009-09-11 14:14:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50545 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50546,50002,0,608,0,19,50348,TO_DATE('2009-09-11 14:14:38','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','WM_InOutBoundLine_UpdatedBy',35,TO_DATE('2009-09-11 14:14:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50546 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50547,50002,0,613,0,18,50349,TO_DATE('2009-09-11 14:14:41','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBoundLine_User1_ID',36,TO_DATE('2009-09-11 14:14:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50547 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50548,50002,0,614,0,18,50350,TO_DATE('2009-09-11 14:14:43','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBoundLine_User2_ID',37,TO_DATE('2009-09-11 14:14:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50548 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50549,50002,0,53913,0,13,50351,TO_DATE('2009-09-11 14:14:44','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBoundLine_WM_InOutBoun',38,TO_DATE('2009-09-11 14:14:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50549 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50550,50002,0,53912,0,30,50352,TO_DATE('2009-09-11 14:14:45','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','Y','N','N','N','WM_InOutBoundLine_WM_InOutBoun',39,TO_DATE('2009-09-11 14:14:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50550 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50551,50002,0,102,0,19,50353,TO_DATE('2009-09-11 14:14:47','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','WM_InOutBound_AD_Client_ID',40,TO_DATE('2009-09-11 14:14:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50551 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50552,50002,0,399,0,20,50377,TO_DATE('2009-09-11 14:14:49','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE03','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','N','N','N','WM_InOutBound_IsPrinted',41,TO_DATE('2009-09-11 14:14:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50552 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50553,50002,0,112,0,18,50354,TO_DATE('2009-09-11 14:14:50','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','WM_InOutBound_AD_OrgTrx_ID',42,TO_DATE('2009-09-11 14:14:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50553 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50554,50002,0,113,0,19,50355,TO_DATE('2009-09-11 14:14:51','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','WM_InOutBound_AD_Org_ID',43,TO_DATE('2009-09-11 14:14:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50554 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50555,50002,0,1005,0,19,50356,TO_DATE('2009-09-11 14:14:54','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','WM_InOutBound_C_Activity_ID',44,TO_DATE('2009-09-11 14:14:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50555 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50556,50002,0,550,0,19,50357,TO_DATE('2009-09-11 14:14:55','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','WM_InOutBound_C_Campaign_ID',45,TO_DATE('2009-09-11 14:14:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50556 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50557,50002,0,196,0,18,50358,TO_DATE('2009-09-11 14:14:57','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03','The Document Type determines document sequence and processing rules','Y','Y','N','N','N','N','WM_InOutBound_C_DocType_ID',46,TO_DATE('2009-09-11 14:14:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50557 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:14:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50558,50002,0,245,0,16,50359,TO_DATE('2009-09-11 14:14:58','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','WM_InOutBound_Created',47,TO_DATE('2009-09-11 14:14:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:14:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50558 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50559,50002,0,246,0,19,50360,TO_DATE('2009-09-11 14:14:59','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','WM_InOutBound_CreatedBy',48,TO_DATE('2009-09-11 14:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50559 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50560,50002,0,1091,0,15,50361,TO_DATE('2009-09-11 14:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE03','Indicates the Date that a document was printed.','Y','Y','N','N','N','N','WM_InOutBound_DatePrinted',49,TO_DATE('2009-09-11 14:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50560 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50561,50002,0,555,0,17,50362,TO_DATE('2009-09-11 14:15:02','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','WM_InOutBound_DeliveryRule',50,TO_DATE('2009-09-11 14:15:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50561 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50562,50002,0,274,0,17,50363,TO_DATE('2009-09-11 14:15:03','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','WM_InOutBound_DeliveryViaRule',51,TO_DATE('2009-09-11 14:15:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50562 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50563,50002,0,275,0,14,50364,TO_DATE('2009-09-11 14:15:04','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','WM_InOutBound_Description',52,TO_DATE('2009-09-11 14:15:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50563 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50564,50002,0,287,0,28,50365,TO_DATE('2009-09-11 14:15:05','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','N','N','WM_InOutBound_DocAction',53,TO_DATE('2009-09-11 14:15:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50564 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50565,50002,0,289,0,17,50366,TO_DATE('2009-09-11 14:15:07','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE03','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','N','N','WM_InOutBound_DocStatus',54,TO_DATE('2009-09-11 14:15:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50565 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50566,50002,0,290,0,10,50367,TO_DATE('2009-09-11 14:15:08','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE03','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','WM_InOutBound_DocumentNo',55,TO_DATE('2009-09-11 14:15:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50566 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50567,50002,0,53458,0,18,50368,TO_DATE('2009-09-11 14:15:09','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE03','If empty the business partner will be shipped to.','Y','Y','N','N','N','N','WM_InOutBound_DropShip_BPartne',56,TO_DATE('2009-09-11 14:15:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50567 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50568,50002,0,53459,0,18,50369,TO_DATE('2009-09-11 14:15:10','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE03','Y','Y','N','N','N','N','WM_InOutBound_DropShip_Locatio',57,TO_DATE('2009-09-11 14:15:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50568 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50569,50002,0,53460,0,18,50370,TO_DATE('2009-09-11 14:15:11','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_DropShip_User_ID',58,TO_DATE('2009-09-11 14:15:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50569 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50570,50002,0,306,0,12,50371,TO_DATE('2009-09-11 14:15:13','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','WM_InOutBound_FreightAmt',59,TO_DATE('2009-09-11 14:15:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50570 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50571,50002,0,1007,0,17,50372,TO_DATE('2009-09-11 14:15:14','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','WM_InOutBound_FreightCostRule',60,TO_DATE('2009-09-11 14:15:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50571 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50572,50002,0,348,0,20,50373,TO_DATE('2009-09-11 14:15:15','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','WM_InOutBound_IsActive',61,TO_DATE('2009-09-11 14:15:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50572 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50573,50002,0,351,0,20,50374,TO_DATE('2009-09-11 14:15:17','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE03','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','N','N','N','N','WM_InOutBound_IsApproved',62,TO_DATE('2009-09-11 14:15:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50573 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50574,50002,0,2466,0,20,50375,TO_DATE('2009-09-11 14:15:18','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE03','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','N','N','N','N','WM_InOutBound_IsDropShip',63,TO_DATE('2009-09-11 14:15:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50574 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50575,50002,0,2397,0,20,50376,TO_DATE('2009-09-11 14:15:20','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit','EE03','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','Y','N','N','N','N','WM_InOutBound_IsInTransit',64,TO_DATE('2009-09-11 14:15:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50575 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50576,50002,0,1106,0,20,50378,TO_DATE('2009-09-11 14:15:22','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE03','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','WM_InOutBound_IsSOTrx',65,TO_DATE('2009-09-11 14:15:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50576 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50577,50002,0,455,0,19,50379,TO_DATE('2009-09-11 14:15:23','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','WM_InOutBound_M_Shipper_ID',66,TO_DATE('2009-09-11 14:15:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50577 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50578,50002,0,459,0,19,50380,TO_DATE('2009-09-11 14:15:25','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','WM_InOutBound_M_Warehouse_ID',67,TO_DATE('2009-09-11 14:15:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50578 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50579,50002,0,952,0,10,50381,TO_DATE('2009-09-11 14:15:26','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','WM_InOutBound_POReference',68,TO_DATE('2009-09-11 14:15:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50579 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50580,50002,0,2117,0,16,50382,TO_DATE('2009-09-11 14:15:28','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_PickDate',69,TO_DATE('2009-09-11 14:15:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50580 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50581,50002,0,522,0,17,50383,TO_DATE('2009-09-11 14:15:29','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE03','The Priority indicates the importance (high, medium, low) of this document','Y','Y','N','N','N','N','WM_InOutBound_PriorityRule',70,TO_DATE('2009-09-11 14:15:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50581 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50582,50002,0,1047,0,20,50384,TO_DATE('2009-09-11 14:15:30','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','WM_InOutBound_Processed',71,TO_DATE('2009-09-11 14:15:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50582 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50583,50002,0,524,0,28,50385,TO_DATE('2009-09-11 14:15:31','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBound_Processing',72,TO_DATE('2009-09-11 14:15:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50583 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50584,50002,0,1063,0,18,50386,TO_DATE('2009-09-11 14:15:33','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','WM_InOutBound_SalesRep_ID',73,TO_DATE('2009-09-11 14:15:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50584 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50585,50002,0,1978,0,20,50387,TO_DATE('2009-09-11 14:15:34','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','WM_InOutBound_SendEMail',74,TO_DATE('2009-09-11 14:15:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50585 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50586,50002,0,2123,0,16,50388,TO_DATE('2009-09-11 14:15:35','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03','Actual Date/Time of Shipment (pick up)','Y','Y','N','N','N','N','WM_InOutBound_ShipDate',75,TO_DATE('2009-09-11 14:15:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50586 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50587,50002,0,2126,0,10,50389,TO_DATE('2009-09-11 14:15:36','YYYY-MM-DD HH24:MI:SS'),0,'Number to track the shipment','EE03','Y','Y','N','N','N','N','WM_InOutBound_TrackingNo',76,TO_DATE('2009-09-11 14:15:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50587 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50588,50002,0,607,0,16,50390,TO_DATE('2009-09-11 14:15:38','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','WM_InOutBound_Updated',77,TO_DATE('2009-09-11 14:15:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50588 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50589,50002,0,608,0,19,50391,TO_DATE('2009-09-11 14:15:39','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','WM_InOutBound_UpdatedBy',78,TO_DATE('2009-09-11 14:15:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50589 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50590,50002,0,613,0,18,50392,TO_DATE('2009-09-11 14:15:40','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBound_User1_ID',79,TO_DATE('2009-09-11 14:15:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50590 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50591,50002,0,614,0,18,50393,TO_DATE('2009-09-11 14:15:42','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','WM_InOutBound_User2_ID',80,TO_DATE('2009-09-11 14:15:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50591 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50592,50002,0,627,0,22,50394,TO_DATE('2009-09-11 14:15:43','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE03','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','N','N','N','N','WM_InOutBound_Volume',81,TO_DATE('2009-09-11 14:15:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50592 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50593,50002,0,53912,0,13,50395,TO_DATE('2009-09-11 14:15:45','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','WM_InOutBound_WM_InOutBound_ID',82,TO_DATE('2009-09-11 14:15:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50593 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50594,50002,0,629,0,22,50396,TO_DATE('2009-09-11 14:15:47','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE03','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','N','N','N','N','WM_InOutBound_Weight',83,TO_DATE('2009-09-11 14:15:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50594 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50595,50002,0,102,0,19,50397,TO_DATE('2009-09-11 14:15:48','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_BPartner_AD_Client_ID',84,TO_DATE('2009-09-11 14:15:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50595 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50596,50002,0,109,0,18,50398,TO_DATE('2009-09-11 14:15:50','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE03','The Language identifies the language to use for display and formatting','Y','Y','N','N','N','N','C_BPartner_AD_Language',85,TO_DATE('2009-09-11 14:15:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50596 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50597,50002,0,2354,0,28,50399,TO_DATE('2009-09-11 14:15:51','YYYY-MM-DD HH24:MI:SS'),0,'The Business Partner is another Organization for explicit Inter-Org transactions','EE03','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Y','N','N','N','N','C_BPartner_AD_OrgBP_ID',86,TO_DATE('2009-09-11 14:15:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50597 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50598,50002,0,113,0,19,50400,TO_DATE('2009-09-11 14:15:53','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_BPartner_AD_Org_ID',87,TO_DATE('2009-09-11 14:15:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50598 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50599,50002,0,151,0,37,50401,TO_DATE('2009-09-11 14:15:54','YYYY-MM-DD HH24:MI:SS'),0,'The cost of gaining the prospect as a customer','EE03','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Y','N','N','N','N','C_BPartner_AcqusitionCost',88,TO_DATE('2009-09-11 14:15:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50599 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50600,50002,0,153,0,12,50402,TO_DATE('2009-09-11 14:15:56','YYYY-MM-DD HH24:MI:SS'),0,'Actual Life Time Revenue','EE03','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_ActualLifeTimeValue',89,TO_DATE('2009-09-11 14:15:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50600 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:15:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50601,50002,0,2031,0,13,50403,TO_DATE('2009-09-11 14:15:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Parent','EE03','The parent (organization) of the Business Partner for reporting purposes.','Y','Y','N','N','N','N','C_BPartner_BPartner_Parent_ID',90,TO_DATE('2009-09-11 14:15:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:15:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50601 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50602,50002,0,1383,0,19,50404,TO_DATE('2009-09-11 14:15:59','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE03','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','C_BPartner_C_BP_Group_ID',91,TO_DATE('2009-09-11 14:15:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50602 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50603,50002,0,187,0,13,50405,TO_DATE('2009-09-11 14:16:01','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_BPartner_C_BPartner_ID',92,TO_DATE('2009-09-11 14:16:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50603 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50604,50002,0,838,0,19,50406,TO_DATE('2009-09-11 14:16:02','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Rules for overdue invoices','EE03','The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','N','N','N','N','C_BPartner_C_Dunning_ID',93,TO_DATE('2009-09-11 14:16:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50604 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50605,50002,0,1159,0,19,50407,TO_DATE('2009-09-11 14:16:04','YYYY-MM-DD HH24:MI:SS'),0,'Greeting to print on correspondence','EE03','The Greeting identifies the greeting to print on correspondence.','Y','Y','N','N','N','N','C_BPartner_C_Greeting_ID',94,TO_DATE('2009-09-11 14:16:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50605 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50606,50002,0,560,0,19,50408,TO_DATE('2009-09-11 14:16:05','YYYY-MM-DD HH24:MI:SS'),0,'Schedule for generating Invoices','EE03','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Y','N','N','N','N','C_BPartner_C_InvoiceSchedule_I',95,TO_DATE('2009-09-11 14:16:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50606 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50607,50002,0,204,0,19,50409,TO_DATE('2009-09-11 14:16:07','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','EE03','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','C_BPartner_C_PaymentTerm_ID',96,TO_DATE('2009-09-11 14:16:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50607 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50608,50002,0,53356,0,19,50410,TO_DATE('2009-09-11 14:16:08','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_C_TaxGroup_ID',97,TO_DATE('2009-09-11 14:16:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50608 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50609,50002,0,245,0,16,50411,TO_DATE('2009-09-11 14:16:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_BPartner_Created',98,TO_DATE('2009-09-11 14:16:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50609 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50610,50002,0,246,0,18,50412,TO_DATE('2009-09-11 14:16:11','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_BPartner_CreatedBy',99,TO_DATE('2009-09-11 14:16:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50610 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50611,50002,0,260,0,10,50413,TO_DATE('2009-09-11 14:16:12','YYYY-MM-DD HH24:MI:SS'),0,'Dun & Bradstreet Number','EE03','Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','Y','N','N','N','N','C_BPartner_DUNS',100,TO_DATE('2009-09-11 14:16:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50611 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50612,50002,0,555,0,17,50414,TO_DATE('2009-09-11 14:16:13','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','C_BPartner_DeliveryRule',101,TO_DATE('2009-09-11 14:16:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50612 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50613,50002,0,274,0,17,50415,TO_DATE('2009-09-11 14:16:15','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','C_BPartner_DeliveryViaRule',102,TO_DATE('2009-09-11 14:16:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50613 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50614,50002,0,275,0,10,50416,TO_DATE('2009-09-11 14:16:16','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_BPartner_Description',103,TO_DATE('2009-09-11 14:16:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50614 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50615,50002,0,866,0,11,50417,TO_DATE('2009-09-11 14:16:17','YYYY-MM-DD HH24:MI:SS'),0,'Number of copies to be printed','EE03','The Document Copies indicates the number of copies of each document that will be generated.','Y','Y','N','N','N','N','C_BPartner_DocumentCopies',104,TO_DATE('2009-09-11 14:16:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50615 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50616,50002,0,53223,0,15,50418,TO_DATE('2009-09-11 14:16:18','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_DunningGrace',105,TO_DATE('2009-09-11 14:16:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50616 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50617,50002,0,305,0,15,50419,TO_DATE('2009-09-11 14:16:20','YYYY-MM-DD HH24:MI:SS'),0,'Date of First Sale','EE03','The First Sale Date identifies the date of the first sale to this Business Partner','Y','Y','N','N','N','N','C_BPartner_FirstSale',106,TO_DATE('2009-09-11 14:16:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50617 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50618,50002,0,1712,0,22,50420,TO_DATE('2009-09-11 14:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Flat discount percentage ','EE03','Y','Y','N','N','N','N','C_BPartner_FlatDiscount',107,TO_DATE('2009-09-11 14:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50618 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50619,50002,0,1007,0,17,50421,TO_DATE('2009-09-11 14:16:23','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','C_BPartner_FreightCostRule',108,TO_DATE('2009-09-11 14:16:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50619 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50620,50002,0,559,0,17,50422,TO_DATE('2009-09-11 14:16:24','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','EE03','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','C_BPartner_InvoiceRule',109,TO_DATE('2009-09-11 14:16:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50620 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50621,50002,0,1822,0,18,50423,TO_DATE('2009-09-11 14:16:26','YYYY-MM-DD HH24:MI:SS'),0,'Print Format for printing Invoices','EE03','You need to define a Print Format to print the document.','Y','Y','N','N','N','N','C_BPartner_Invoice_PrintFormat',110,TO_DATE('2009-09-11 14:16:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50621 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50622,50002,0,348,0,20,50424,TO_DATE('2009-09-11 14:16:27','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_BPartner_IsActive',111,TO_DATE('2009-09-11 14:16:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50622 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50623,50002,0,364,0,20,50425,TO_DATE('2009-09-11 14:16:28','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Customer','EE03','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','N','N','N','N','C_BPartner_IsCustomer',112,TO_DATE('2009-09-11 14:16:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50623 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50624,50002,0,1239,0,20,50426,TO_DATE('2009-09-11 14:16:29','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','EE03','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','C_BPartner_IsDiscountPrinted',113,TO_DATE('2009-09-11 14:16:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50624 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:31 PM ECT
-- Warehouse Management
UPDATE AD_Menu SET AD_Browse_ID=50002, AD_Process_ID=NULL, Action='S',Updated=TO_DATE('2009-09-11 14:16:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=193
;

-- Sep 11, 2009 2:16:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50625,50002,0,373,0,20,50427,TO_DATE('2009-09-11 14:16:30','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  this Business Partner is an employee','EE03','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','N','N','N','N','C_BPartner_IsEmployee',114,TO_DATE('2009-09-11 14:16:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50625 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50626,50002,0,922,0,20,50428,TO_DATE('2009-09-11 14:16:31','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_IsOneTime',115,TO_DATE('2009-09-11 14:16:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50626 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50627,50002,0,402,0,20,50429,TO_DATE('2009-09-11 14:16:32','YYYY-MM-DD HH24:MI:SS'),0,'Indicates this is a Prospect','EE03','The Prospect checkbox indicates an entity that is an active prospect.','Y','Y','N','N','N','N','C_BPartner_IsProspect',116,TO_DATE('2009-09-11 14:16:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50627 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50628,50002,0,409,0,20,50430,TO_DATE('2009-09-11 14:16:34','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  the business partner is a sales representative or company agent','EE03','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Y','N','N','N','N','C_BPartner_IsSalesRep',117,TO_DATE('2009-09-11 14:16:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50628 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50629,50002,0,416,0,20,50431,TO_DATE('2009-09-11 14:16:35','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','EE03','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','N','N','N','N','C_BPartner_IsSummary',118,TO_DATE('2009-09-11 14:16:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50629 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50630,50002,0,930,0,20,50432,TO_DATE('2009-09-11 14:16:36','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax','EE03','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','N','N','N','N','C_BPartner_IsTaxExempt',119,TO_DATE('2009-09-11 14:16:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50630 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50631,50002,0,426,0,20,50433,TO_DATE('2009-09-11 14:16:38','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Vendor','EE03','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','N','N','N','N','C_BPartner_IsVendor',120,TO_DATE('2009-09-11 14:16:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50631 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50632,50002,0,53909,0,32,50434,TO_DATE('2009-09-11 14:16:39','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_BPartner_Logo_ID',121,TO_DATE('2009-09-11 14:16:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50632 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50633,50002,0,1714,0,18,50435,TO_DATE('2009-09-11 14:16:41','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the trade discount percentage','EE03','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Y','N','N','N','N','C_BPartner_M_DiscountSchema_ID',122,TO_DATE('2009-09-11 14:16:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50633 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50634,50002,0,449,0,19,50436,TO_DATE('2009-09-11 14:16:42','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE03','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','C_BPartner_M_PriceList_ID',123,TO_DATE('2009-09-11 14:16:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50634 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50635,50002,0,468,0,10,50437,TO_DATE('2009-09-11 14:16:44','YYYY-MM-DD HH24:MI:SS'),0,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','EE03','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','Y','N','N','N','N','C_BPartner_NAICS',124,TO_DATE('2009-09-11 14:16:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50635 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50636,50002,0,469,0,10,50438,TO_DATE('2009-09-11 14:16:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','C_BPartner_Name',125,TO_DATE('2009-09-11 14:16:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50636 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50637,50002,0,1111,0,10,50439,TO_DATE('2009-09-11 14:16:46','YYYY-MM-DD HH24:MI:SS'),0,'Additional Name','EE03','Y','Y','N','N','N','N','C_BPartner_Name2',126,TO_DATE('2009-09-11 14:16:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50637 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50638,50002,0,473,0,11,50440,TO_DATE('2009-09-11 14:16:48','YYYY-MM-DD HH24:MI:SS'),0,'Number of employees','EE03','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Y','N','N','N','N','C_BPartner_NumberEmployees',127,TO_DATE('2009-09-11 14:16:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50638 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50639,50002,0,952,0,10,50441,TO_DATE('2009-09-11 14:16:50','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','C_BPartner_POReference',128,TO_DATE('2009-09-11 14:16:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50639 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50640,50002,0,1717,0,18,50442,TO_DATE('2009-09-11 14:16:51','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the purchase trade discount percentage','EE03','Y','Y','N','N','N','N','C_BPartner_PO_DiscountSchema_I',129,TO_DATE('2009-09-11 14:16:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50640 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50641,50002,0,1576,0,18,50443,TO_DATE('2009-09-11 14:16:52','YYYY-MM-DD HH24:MI:SS'),0,'Payment rules for a purchase order','EE03','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','Y','N','N','N','N','C_BPartner_PO_PaymentTerm_ID',130,TO_DATE('2009-09-11 14:16:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50641 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50642,50002,0,480,0,18,50444,TO_DATE('2009-09-11 14:16:54','YYYY-MM-DD HH24:MI:SS'),0,'Price List used by this Business Partner','EE03','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Y','N','N','N','N','C_BPartner_PO_PriceList_ID',131,TO_DATE('2009-09-11 14:16:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50642 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50643,50002,0,1143,0,17,50445,TO_DATE('2009-09-11 14:16:56','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','EE03','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRule',132,TO_DATE('2009-09-11 14:16:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50643 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:16:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50644,50002,0,950,0,17,50446,TO_DATE('2009-09-11 14:16:57','YYYY-MM-DD HH24:MI:SS'),0,'Purchase payment option','EE03','The Payment Rule indicates the method of purchase payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRulePO',133,TO_DATE('2009-09-11 14:16:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:16:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50644 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50645,50002,0,515,0,12,50447,TO_DATE('2009-09-11 14:16:59','YYYY-MM-DD HH24:MI:SS'),0,'Total Revenue expected','EE03','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_PotentialLifeTimeVa',134,TO_DATE('2009-09-11 14:16:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50645 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50646,50002,0,962,0,10,50448,TO_DATE('2009-09-11 14:17:01','YYYY-MM-DD HH24:MI:SS'),0,'Classification or Importance','EE03','The Rating is used to differentiate the importance','Y','Y','N','N','N','N','C_BPartner_Rating',135,TO_DATE('2009-09-11 14:17:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50646 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50647,50002,0,540,0,10,50449,TO_DATE('2009-09-11 14:17:02','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site','EE03','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Y','N','N','N','N','C_BPartner_ReferenceNo',136,TO_DATE('2009-09-11 14:17:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50647 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50648,50002,0,2181,0,17,50450,TO_DATE('2009-09-11 14:17:03','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Credit Status','EE03','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Y','N','N','N','N','C_BPartner_SOCreditStatus',137,TO_DATE('2009-09-11 14:17:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50648 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50649,50002,0,553,0,12,50451,TO_DATE('2009-09-11 14:17:05','YYYY-MM-DD HH24:MI:SS'),0,'Total outstanding invoice amounts allowed','EE03','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditLimit',138,TO_DATE('2009-09-11 14:17:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50649 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50650,50002,0,554,0,12,50452,TO_DATE('2009-09-11 14:17:06','YYYY-MM-DD HH24:MI:SS'),0,'Current open balance','EE03','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditUsed',139,TO_DATE('2009-09-11 14:17:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50650 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50651,50002,0,1244,0,10,50453,TO_DATE('2009-09-11 14:17:08','YYYY-MM-DD HH24:MI:SS'),0,'Description to be used on orders','EE03','The Order Description identifies the standard description to use on orders for this Customer.','Y','Y','N','N','N','N','C_BPartner_SO_Description',140,TO_DATE('2009-09-11 14:17:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50651 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50652,50002,0,1063,0,18,50454,TO_DATE('2009-09-11 14:17:09','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','C_BPartner_SalesRep_ID',141,TO_DATE('2009-09-11 14:17:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50652 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50653,50002,0,563,0,11,50455,TO_DATE('2009-09-11 14:17:11','YYYY-MM-DD HH24:MI:SS'),0,'Total Volume of Sales in Thousands of Currency','EE03','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Y','N','N','N','N','C_BPartner_SalesVolume',142,TO_DATE('2009-09-11 14:17:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50653 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50654,50002,0,1978,0,20,50456,TO_DATE('2009-09-11 14:17:12','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','C_BPartner_SendEMail',143,TO_DATE('2009-09-11 14:17:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50654 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50655,50002,0,569,0,11,50457,TO_DATE('2009-09-11 14:17:14','YYYY-MM-DD HH24:MI:SS'),0,'Share of Customer''s business as a percentage','EE03','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Y','N','N','N','N','C_BPartner_ShareOfCustomer',144,TO_DATE('2009-09-11 14:17:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50655 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50656,50002,0,268,0,15,50485,TO_DATE('2009-09-11 14:17:15','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE03','Indicates the Date an item was ordered.','Y','Y','N','N','N','N','C_OrderLine_DateOrdered',145,TO_DATE('2009-09-11 14:17:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50656 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50657,50002,0,2240,0,11,50458,TO_DATE('2009-09-11 14:17:17','YYYY-MM-DD HH24:MI:SS'),0,'Minimum Shelf Life in percent based on Product Instance Guarantee Date','EE03','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Y','N','N','N','N','C_BPartner_ShelfLifeMinPct',146,TO_DATE('2009-09-11 14:17:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50657 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50658,50002,0,590,0,10,50459,TO_DATE('2009-09-11 14:17:18','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE03','The Tax ID field identifies the legal Identification number of this Entity.','Y','Y','N','N','N','N','C_BPartner_TaxID',147,TO_DATE('2009-09-11 14:17:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50658 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50659,50002,0,2562,0,12,50460,TO_DATE('2009-09-11 14:17:19','YYYY-MM-DD HH24:MI:SS'),0,'Total Open Balance Amount in primary Accounting Currency','EE03','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Y','N','N','N','N','C_BPartner_TotalOpenBalance',148,TO_DATE('2009-09-11 14:17:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50659 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50660,50002,0,983,0,40,50461,TO_DATE('2009-09-11 14:17:21','YYYY-MM-DD HH24:MI:SS'),0,'Full URL address - e.g. http://www.adempiere.org','EE03','The URL defines an fully qualified web address like http://www.adempiere.org','Y','Y','N','N','N','N','C_BPartner_URL',149,TO_DATE('2009-09-11 14:17:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50660 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50661,50002,0,607,0,16,50462,TO_DATE('2009-09-11 14:17:22','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_BPartner_Updated',150,TO_DATE('2009-09-11 14:17:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50661 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50662,50002,0,608,0,18,50463,TO_DATE('2009-09-11 14:17:23','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_BPartner_UpdatedBy',151,TO_DATE('2009-09-11 14:17:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50662 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50663,50002,0,620,0,10,50464,TO_DATE('2009-09-11 14:17:25','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE03','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','N','N','N','N','C_BPartner_Value',152,TO_DATE('2009-09-11 14:17:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50663 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50664,50002,0,102,0,19,50465,TO_DATE('2009-09-11 14:17:26','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_OrderLine_AD_Client_ID',153,TO_DATE('2009-09-11 14:17:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50664 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50665,50002,0,112,0,18,50466,TO_DATE('2009-09-11 14:17:27','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','C_OrderLine_AD_OrgTrx_ID',154,TO_DATE('2009-09-11 14:17:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50665 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50666,50002,0,113,0,19,50467,TO_DATE('2009-09-11 14:17:28','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_OrderLine_AD_Org_ID',155,TO_DATE('2009-09-11 14:17:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50666 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50667,50002,0,1005,0,19,50468,TO_DATE('2009-09-11 14:17:30','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','C_OrderLine_C_Activity_ID',156,TO_DATE('2009-09-11 14:17:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50667 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50668,50002,0,187,0,30,50469,TO_DATE('2009-09-11 14:17:31','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_ID',157,TO_DATE('2009-09-11 14:17:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50668 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50669,50002,0,189,0,19,50470,TO_DATE('2009-09-11 14:17:32','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE03','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_Locatio',158,TO_DATE('2009-09-11 14:17:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50669 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50670,50002,0,550,0,19,50471,TO_DATE('2009-09-11 14:17:33','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','C_OrderLine_C_Campaign_ID',159,TO_DATE('2009-09-11 14:17:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50670 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50671,50002,0,968,0,19,50472,TO_DATE('2009-09-11 14:17:35','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','C_OrderLine_C_Charge_ID',160,TO_DATE('2009-09-11 14:17:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50671 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50672,50002,0,193,0,19,50473,TO_DATE('2009-09-11 14:17:36','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','EE03','Indicates the Currency to be used when processing or reporting on this record','Y','Y','N','N','N','N','C_OrderLine_C_Currency_ID',161,TO_DATE('2009-09-11 14:17:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50672 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50673,50002,0,561,0,13,50474,TO_DATE('2009-09-11 14:17:37','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','N','N','N','N','C_OrderLine_C_OrderLine_ID',162,TO_DATE('2009-09-11 14:17:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50673 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50674,50002,0,558,0,30,50475,TO_DATE('2009-09-11 14:17:39','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE03','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','Y','N','N','N','C_OrderLine_C_Order_ID',163,TO_DATE('2009-09-11 14:17:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50674 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50675,50002,0,2073,0,19,50476,TO_DATE('2009-09-11 14:17:40','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03','Y','Y','N','N','N','N','C_OrderLine_C_ProjectPhase_ID',164,TO_DATE('2009-09-11 14:17:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50675 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50676,50002,0,2074,0,19,50477,TO_DATE('2009-09-11 14:17:42','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','C_OrderLine_C_ProjectTask_ID',165,TO_DATE('2009-09-11 14:17:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50676 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50677,50002,0,208,0,19,50478,TO_DATE('2009-09-11 14:17:43','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','C_OrderLine_C_Project_ID',166,TO_DATE('2009-09-11 14:17:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50677 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50678,50002,0,213,0,19,50479,TO_DATE('2009-09-11 14:17:44','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','EE03','The Tax indicates the type of tax used in document line.','Y','Y','N','N','N','N','C_OrderLine_C_Tax_ID',167,TO_DATE('2009-09-11 14:17:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50678 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50679,50002,0,215,0,19,50480,TO_DATE('2009-09-11 14:17:46','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE03','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','C_OrderLine_C_UOM_ID',168,TO_DATE('2009-09-11 14:17:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50679 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50680,50002,0,245,0,16,50481,TO_DATE('2009-09-11 14:17:47','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_OrderLine_Created',169,TO_DATE('2009-09-11 14:17:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50680 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50681,50002,0,246,0,18,50482,TO_DATE('2009-09-11 14:17:50','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_OrderLine_CreatedBy',170,TO_DATE('2009-09-11 14:17:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50681 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50682,50002,0,264,0,15,50483,TO_DATE('2009-09-11 14:17:56','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE03','Y','Y','N','N','N','N','C_OrderLine_DateDelivered',171,TO_DATE('2009-09-11 14:17:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50682 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:17:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50683,50002,0,267,0,15,50484,TO_DATE('2009-09-11 14:17:57','YYYY-MM-DD HH24:MI:SS'),0,'Date printed on Invoice','EE03','The Date Invoice indicates the date printed on the invoice.','Y','Y','N','N','N','N','C_OrderLine_DateInvoiced',172,TO_DATE('2009-09-11 14:17:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:17:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50683 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50684,50002,0,269,0,15,50486,TO_DATE('2009-09-11 14:17:59','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE03','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','N','N','N','C_OrderLine_DatePromised',173,TO_DATE('2009-09-11 14:17:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50684 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50685,50002,0,275,0,14,50487,TO_DATE('2009-09-11 14:18:00','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_OrderLine_Description',174,TO_DATE('2009-09-11 14:18:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50685 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50686,50002,0,280,0,22,50488,TO_DATE('2009-09-11 14:18:01','YYYY-MM-DD HH24:MI:SS'),0,'Discount in percent','EE03','The Discount indicates the discount applied or taken as a percentage.','Y','Y','N','N','N','N','C_OrderLine_Discount',175,TO_DATE('2009-09-11 14:18:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50686 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50687,50002,0,306,0,12,50489,TO_DATE('2009-09-11 14:18:03','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','C_OrderLine_FreightAmt',176,TO_DATE('2009-09-11 14:18:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50687 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50688,50002,0,348,0,20,50490,TO_DATE('2009-09-11 14:18:04','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_OrderLine_IsActive',177,TO_DATE('2009-09-11 14:18:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50688 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50689,50002,0,2183,0,20,50491,TO_DATE('2009-09-11 14:18:05','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE03','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','C_OrderLine_IsDescription',178,TO_DATE('2009-09-11 14:18:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50689 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50690,50002,0,439,0,11,50492,TO_DATE('2009-09-11 14:18:06','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE03','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','C_OrderLine_Line',179,TO_DATE('2009-09-11 14:18:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50690 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50691,50002,0,441,0,12,50493,TO_DATE('2009-09-11 14:18:07','YYYY-MM-DD HH24:MI:SS'),0,'Line Extended Amount (Quantity * Actual Price) without Freight and Charges','EE03','Indicates the extended line amount based on the quantity and the actual price.  Any additional charges or freight are not included.  The Amount may or may not include tax.  If the price list is inclusive tax, the line amount is the same as the line total.','Y','Y','Y','N','N','N','C_OrderLine_LineNetAmt',180,TO_DATE('2009-09-11 14:18:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50691 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50692,50002,0,53463,0,18,50494,TO_DATE('2009-09-11 14:18:09','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order line to the purchase order line that is generated from it.','EE03','Y','Y','N','N','N','N','C_OrderLine_Link_OrderLine_ID',181,TO_DATE('2009-09-11 14:18:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50692 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50693,50002,0,2019,0,35,50495,TO_DATE('2009-09-11 14:18:10','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','C_OrderLine_M_AttributeSetInst',182,TO_DATE('2009-09-11 14:18:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50693 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50694,50002,0,454,0,30,50496,TO_DATE('2009-09-11 14:18:12','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03','Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','C_OrderLine_M_Product_ID',183,TO_DATE('2009-09-11 14:18:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50694 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50695,50002,0,53802,0,19,50497,TO_DATE('2009-09-11 14:18:13','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_OrderLine_M_Promotion_ID',184,TO_DATE('2009-09-11 14:18:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50695 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50696,50002,0,455,0,19,50498,TO_DATE('2009-09-11 14:18:14','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','C_OrderLine_M_Shipper_ID',185,TO_DATE('2009-09-11 14:18:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50696 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50697,50002,0,459,0,18,50499,TO_DATE('2009-09-11 14:18:16','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','C_OrderLine_M_Warehouse_ID',186,TO_DATE('2009-09-11 14:18:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50697 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50698,50002,0,53310,0,19,50500,TO_DATE('2009-09-11 14:18:17','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_OrderLine_PP_Cost_Collector_',187,TO_DATE('2009-09-11 14:18:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50698 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50699,50002,0,519,0,37,50501,TO_DATE('2009-09-11 14:18:19','YYYY-MM-DD HH24:MI:SS'),0,'Actual Price ','EE03','The Actual or Unit Price indicates the Price for a product in source currency.','Y','Y','N','N','N','N','C_OrderLine_PriceActual',188,TO_DATE('2009-09-11 14:18:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50699 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50700,50002,0,2821,0,37,50502,TO_DATE('2009-09-11 14:18:20','YYYY-MM-DD HH24:MI:SS'),0,'Price per Unit of Measure including all indirect costs (Freight, etc.)','EE03','Optional Purchase Order Line cost price.','Y','Y','N','N','N','N','C_OrderLine_PriceCost',189,TO_DATE('2009-09-11 14:18:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50700 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50701,50002,0,2588,0,37,50503,TO_DATE('2009-09-11 14:18:21','YYYY-MM-DD HH24:MI:SS'),0,'Price Entered - the price based on the selected/base UoM','EE03','The price entered is converted to the actual price based on the UoM conversion','Y','Y','N','N','N','N','C_OrderLine_PriceEntered',190,TO_DATE('2009-09-11 14:18:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50701 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50702,50002,0,955,0,37,50504,TO_DATE('2009-09-11 14:18:22','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','EE03','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Y','N','N','N','N','C_OrderLine_PriceLimit',191,TO_DATE('2009-09-11 14:18:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50702 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50703,50002,0,520,0,37,50505,TO_DATE('2009-09-11 14:18:23','YYYY-MM-DD HH24:MI:SS'),0,'List Price','EE03','The List Price is the official List Price in the document currency.','Y','Y','N','N','N','N','C_OrderLine_PriceList',192,TO_DATE('2009-09-11 14:18:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50703 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50704,50002,0,1047,0,20,50506,TO_DATE('2009-09-11 14:18:24','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','C_OrderLine_Processed',193,TO_DATE('2009-09-11 14:18:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50704 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50705,50002,0,528,0,29,50507,TO_DATE('2009-09-11 14:18:25','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE03','The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','Y','N','N','N','N','C_OrderLine_QtyDelivered',194,TO_DATE('2009-09-11 14:18:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50705 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50706,50002,0,2589,0,29,50508,TO_DATE('2009-09-11 14:18:26','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE03','The Quantity Entered is converted to base product UoM quantity','Y','Y','N','N','N','N','C_OrderLine_QtyEntered',195,TO_DATE('2009-09-11 14:18:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50706 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50707,50002,0,529,0,29,50509,TO_DATE('2009-09-11 14:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Invoiced Quantity','EE03','The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Y','N','N','N','N','C_OrderLine_QtyInvoiced',196,TO_DATE('2009-09-11 14:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50707 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50708,50002,0,2826,0,29,50510,TO_DATE('2009-09-11 14:18:30','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of potential sales','EE03','When an order is closed and there is a difference between the ordered quantity and the delivered (invoiced) quantity is the Lost Sales Quantity.  Note that the Lost Sales Quantity is 0 if you void an order, so close the order if you want to track lost opportunities.  [Void = data entry error - Close = the order is finished]','Y','Y','N','N','N','N','C_OrderLine_QtyLostSales',197,TO_DATE('2009-09-11 14:18:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50708 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50709,50002,0,531,0,29,50511,TO_DATE('2009-09-11 14:18:32','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE03','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Y','N','N','N','N','C_OrderLine_QtyOrdered',198,TO_DATE('2009-09-11 14:18:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50709 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50710,50002,0,532,0,29,50512,TO_DATE('2009-09-11 14:18:33','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE03','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Y','N','N','N','N','C_OrderLine_QtyReserved',199,TO_DATE('2009-09-11 14:18:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50710 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50711,50002,0,3033,0,12,50513,TO_DATE('2009-09-11 14:18:35','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Amount','EE03','The amount for revenue recognition calculation.  If empty, the complete invoice amount is used.  The difference between Revenue Recognition Amount and Invoice Line Net Amount is immediately recognized as revenue.','Y','Y','N','N','N','N','C_OrderLine_RRAmt',200,TO_DATE('2009-09-11 14:18:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50711 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50712,50002,0,3032,0,16,50514,TO_DATE('2009-09-11 14:18:37','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Start Date','EE03','The date the revenue reconition starts.','Y','Y','N','N','N','N','C_OrderLine_RRStartDate',201,TO_DATE('2009-09-11 14:18:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50712 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50713,50002,0,1905,0,30,50515,TO_DATE('2009-09-11 14:18:38','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','EE03','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Y','N','N','N','N','C_OrderLine_Ref_OrderLine_ID',202,TO_DATE('2009-09-11 14:18:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50713 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50714,50002,0,1778,0,33,50516,TO_DATE('2009-09-11 14:18:40','YYYY-MM-DD HH24:MI:SS'),0,'Resource Assignment','EE03','Y','Y','N','N','N','N','C_OrderLine_S_ResourceAssignme',203,TO_DATE('2009-09-11 14:18:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50714 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50715,50002,0,607,0,16,50517,TO_DATE('2009-09-11 14:18:43','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_OrderLine_Updated',204,TO_DATE('2009-09-11 14:18:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50715 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50716,50002,0,608,0,18,50518,TO_DATE('2009-09-11 14:18:45','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_OrderLine_UpdatedBy',205,TO_DATE('2009-09-11 14:18:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50716 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50717,50002,0,613,0,18,50519,TO_DATE('2009-09-11 14:18:46','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User1_ID',206,TO_DATE('2009-09-11 14:18:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50717 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:18:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50718,50002,0,614,0,18,50520,TO_DATE('2009-09-11 14:18:47','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User2_ID',207,TO_DATE('2009-09-11 14:18:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:18:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50718 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:23:24 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:23:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 2:23:57 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:23:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 2:24:35 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_DATE('2009-09-11 14:24:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50388
;

-- Sep 11, 2009 2:24:47 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='N',Updated=TO_DATE('2009-09-11 14:24:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50388
;

-- Sep 11, 2009 2:25:06 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_DATE('2009-09-11 14:25:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 2:25:46 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:25:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50595
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50596
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50597
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50598
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50599
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50600
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50601
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50602
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50603
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50604
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50605
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50606
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50607
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50608
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50609
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50610
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50611
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50612
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50613
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50614
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50615
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50616
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50617
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50618
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50619
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50620
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50621
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50622
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50623
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50624
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50625
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50626
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50627
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50628
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50629
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50630
;

-- Sep 11, 2009 2:31:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50631
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50632
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50633
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50634
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50635
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50637
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50638
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50639
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50640
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50641
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50642
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50643
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50644
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50645
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50646
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50647
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50650
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50651
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50652
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50653
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50654
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50655
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50657
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50658
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50659
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50660
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50661
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50662
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50663
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50664
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50665
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50666
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50667
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50669
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50670
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50671
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50672
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50673
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50675
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50676
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50677
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50678
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50679
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50680
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50681
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50682
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50683
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50656
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50684
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50685
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50686
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50687
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50688
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50689
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50690
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50691
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50692
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50693
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50694
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50695
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50696
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50697
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50698
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50699
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50700
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50701
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50702
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50703
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50704
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50705
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50706
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50707
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50708
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50709
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50710
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50711
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50712
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50713
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50714
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50715
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50716
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50717
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50718
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50521
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50522
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50523
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50524
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50525
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50526
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50527
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50528
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50529
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50530
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50531
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50532
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50533
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50535
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50536
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50537
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50538
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50539
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50540
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50541
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50543
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50544
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50545
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50546
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50547
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50548
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50549
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50551
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50553
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50554
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50555
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50556
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50557
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50558
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50559
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50560
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50561
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50562
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50563
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50564
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50565
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50566
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50567
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50568
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50569
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50570
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50571
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50572
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50573
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50574
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50575
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50552
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50576
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50577
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50579
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50580
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50581
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50582
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50583
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50584
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50585
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50586
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50587
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50588
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50589
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50590
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50591
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50592
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50593
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50594
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50550
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50636
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50649
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50648
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50534
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50674
;

-- Sep 11, 2009 2:31:34 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50542
;

-- Sep 11, 2009 2:35:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 2:35:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 2:35:55 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsDisplayed='N', IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:35:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 2:36:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:36:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50674
;

-- Sep 11, 2009 2:36:28 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 14:36:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 2:38:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,259,50011,50001,TO_DATE('2009-09-11 14:38:06','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_Order o ON (o.C_Order_ID = ol.C_Order_ID)','N',40,'o',TO_DATE('2009-09-11 14:38:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:17 PM ECT
-- Warehouse Management
UPDATE AD_View_Definition SET SeqNo=50,Updated=TO_DATE('2009-09-11 14:38:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Definition_ID=50009
;

-- Sep 11, 2009 2:38:42 PM ECT
-- Warehouse Management
UPDATE AD_View_Definition SET JoinClause='INNER JOIN C_Order o ON (o.C_Order_ID = ol.C_Order_ID) ',Updated=TO_DATE('2009-09-11 14:38:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Definition_ID=50011
;

-- Sep 11, 2009 2:38:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2168,0,50521,50011,50001,'C_Order_UpdatedBy','o.UpdatedBy',TO_DATE('2009-09-11 14:38:45','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_DATE('2009-09-11 14:38:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:48 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2161,0,50522,50011,50001,'C_Order_C_Order_ID','o.C_Order_ID',TO_DATE('2009-09-11 14:38:47','YYYY-MM-DD HH24:MI:SS'),0,'Order','D','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Order',TO_DATE('2009-09-11 14:38:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:49 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3046,0,50523,50011,50001,'C_Order_C_Charge_ID','o.C_Charge_ID',TO_DATE('2009-09-11 14:38:48','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Charge',TO_DATE('2009-09-11 14:38:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:50 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2192,0,50524,50011,50001,'C_Order_InvoiceRule','o.InvoiceRule',TO_DATE('2009-09-11 14:38:49','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','D','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Invoice Rule',TO_DATE('2009-09-11 14:38:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:52 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2195,0,50525,50011,50001,'C_Order_FreightAmt','o.FreightAmt',TO_DATE('2009-09-11 14:38:50','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','D','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Freight Amount',TO_DATE('2009-09-11 14:38:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:53 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2196,0,50526,50011,50001,'C_Order_DeliveryViaRule','o.DeliveryViaRule',TO_DATE('2009-09-11 14:38:52','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','D','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Delivery Via',TO_DATE('2009-09-11 14:38:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:54 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2175,0,50527,50011,50001,'C_Order_IsApproved','o.IsApproved',TO_DATE('2009-09-11 14:38:53','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','D','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Approved',TO_DATE('2009-09-11 14:38:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:38:59 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2176,0,50528,50011,50001,'C_Order_IsCreditApproved','o.IsCreditApproved',TO_DATE('2009-09-11 14:38:54','YYYY-MM-DD HH24:MI:SS'),0,'Credit  has been approved','D','Credit Approved indicates if the credit approval was successful for Orders','Y','Credit Approved',TO_DATE('2009-09-11 14:38:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:01 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2177,0,50529,50011,50001,'C_Order_IsDelivered','o.IsDelivered',TO_DATE('2009-09-11 14:38:59','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Delivered',TO_DATE('2009-09-11 14:38:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:03 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2178,0,50530,50011,50001,'C_Order_IsInvoiced','o.IsInvoiced',TO_DATE('2009-09-11 14:39:01','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?','D','If selected, invoices are created','Y','Invoiced',TO_DATE('2009-09-11 14:39:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:04 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2202,0,50531,50011,50001,'C_Order_M_Warehouse_ID','o.M_Warehouse_ID',TO_DATE('2009-09-11 14:39:03','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','D','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_DATE('2009-09-11 14:39:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:06 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2164,0,50532,50011,50001,'C_Order_IsActive','o.IsActive',TO_DATE('2009-09-11 14:39:04','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_DATE('2009-09-11 14:39:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:08 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2183,0,50533,50011,50001,'C_Order_DateAcct','o.DateAcct',TO_DATE('2009-09-11 14:39:06','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Date','D','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Account Date',TO_DATE('2009-09-11 14:39:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:09 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2186,0,50534,50011,50001,'C_Order_SalesRep_ID','o.SalesRep_ID',TO_DATE('2009-09-11 14:39:08','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_DATE('2009-09-11 14:39:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:10 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2165,0,50535,50011,50001,'C_Order_Created','o.Created',TO_DATE('2009-09-11 14:39:09','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_DATE('2009-09-11 14:39:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2454,0,50536,50011,50001,'C_Order_C_Campaign_ID','o.C_Campaign_ID',TO_DATE('2009-09-11 14:39:10','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_DATE('2009-09-11 14:39:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2187,0,50537,50011,50001,'C_Order_C_PaymentTerm_ID','o.C_PaymentTerm_ID',TO_DATE('2009-09-11 14:39:12','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','D','Payment Terms identify the method and timing of payment.','Y','Payment Term',TO_DATE('2009-09-11 14:39:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:15 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2173,0,50538,50011,50001,'C_Order_C_DocTypeTarget_ID','o.C_DocTypeTarget_ID',TO_DATE('2009-09-11 14:39:13','YYYY-MM-DD HH24:MI:SS'),0,'Target document type for conversing documents','D','You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.','Y','Target Document Type',TO_DATE('2009-09-11 14:39:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2191,0,50539,50011,50001,'C_Order_C_Currency_ID','o.C_Currency_ID',TO_DATE('2009-09-11 14:39:15','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','D','Indicates the Currency to be used when processing or reporting on this record','Y','Currency',TO_DATE('2009-09-11 14:39:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:18 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2200,0,50540,50011,50001,'C_Order_TotalLines','o.TotalLines',TO_DATE('2009-09-11 14:39:16','YYYY-MM-DD HH24:MI:SS'),0,'Total of all document lines','D','The Total amount displays the total of all lines in document currency','Y','Total Lines',TO_DATE('2009-09-11 14:39:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2201,0,50541,50011,50001,'C_Order_GrandTotal','o.GrandTotal',TO_DATE('2009-09-11 14:39:18','YYYY-MM-DD HH24:MI:SS'),0,'Total amount of document','D','The Grand Total displays the total amount including Tax and Freight in document currency','Y','Grand Total',TO_DATE('2009-09-11 14:39:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2171,0,50542,50011,50001,'C_Order_DocAction','o.DocAction',TO_DATE('2009-09-11 14:39:19','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','D','You find the current status in the Document Status field. The options are listed in a popup','Y','Document Action',TO_DATE('2009-09-11 14:39:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2172,0,50543,50011,50001,'C_Order_C_DocType_ID','o.C_DocType_ID',TO_DATE('2009-09-11 14:39:20','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','D','The Document Type determines document sequence and processing rules','Y','Document Type',TO_DATE('2009-09-11 14:39:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:22 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2170,0,50544,50011,50001,'C_Order_DocStatus','o.DocStatus',TO_DATE('2009-09-11 14:39:21','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','D','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Document Status',TO_DATE('2009-09-11 14:39:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2163,0,50545,50011,50001,'C_Order_AD_Org_ID','o.AD_Org_ID',TO_DATE('2009-09-11 14:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_DATE('2009-09-11 14:39:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2169,0,50546,50011,50001,'C_Order_DocumentNo','o.DocumentNo',TO_DATE('2009-09-11 14:39:24','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','D','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_DATE('2009-09-11 14:39:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:26 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2179,0,50547,50011,50001,'C_Order_IsPrinted','o.IsPrinted',TO_DATE('2009-09-11 14:39:25','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','D','The Printed checkbox indicates if this document or line will included when printing.','Y','Printed',TO_DATE('2009-09-11 14:39:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:28 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2198,0,50548,50011,50001,'C_Order_PriorityRule','o.PriorityRule',TO_DATE('2009-09-11 14:39:26','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','D','The Priority indicates the importance (high, medium, low) of this document','Y','Priority',TO_DATE('2009-09-11 14:39:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:30 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9569,0,50549,50011,50001,'C_Order_User1_ID','o.User1_ID',TO_DATE('2009-09-11 14:39:28','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_DATE('2009-09-11 14:39:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:31 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2763,0,50550,50011,50001,'C_Order_AD_User_ID','o.AD_User_ID',TO_DATE('2009-09-11 14:39:30','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','D','The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','User/Contact',TO_DATE('2009-09-11 14:39:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2762,0,50551,50011,50001,'C_Order_C_BPartner_ID','o.C_BPartner_ID',TO_DATE('2009-09-11 14:39:31','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_DATE('2009-09-11 14:39:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:34 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2162,0,50552,50011,50001,'C_Order_AD_Client_ID','o.AD_Client_ID',TO_DATE('2009-09-11 14:39:33','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_DATE('2009-09-11 14:39:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3402,0,50553,50011,50001,'C_Order_C_Project_ID','o.C_Project_ID',TO_DATE('2009-09-11 14:39:34','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','D','A Project allows you to track and control internal or external activities.','Y','Project',TO_DATE('2009-09-11 14:39:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:37 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3045,0,50554,50011,50001,'C_Order_POReference','o.POReference',TO_DATE('2009-09-11 14:39:36','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','D','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Order Reference',TO_DATE('2009-09-11 14:39:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:38 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2174,0,50555,50011,50001,'C_Order_Description','o.Description',TO_DATE('2009-09-11 14:39:37','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_DATE('2009-09-11 14:39:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:39 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2197,0,50556,50011,50001,'C_Order_M_Shipper_ID','o.M_Shipper_ID',TO_DATE('2009-09-11 14:39:38','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','D','The Shipper indicates the method of delivering product','Y','Shipper',TO_DATE('2009-09-11 14:39:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:41 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3047,0,50557,50011,50001,'C_Order_ChargeAmt','o.ChargeAmt',TO_DATE('2009-09-11 14:39:39','YYYY-MM-DD HH24:MI:SS'),0,'Charge Amount','D','The Charge Amount indicates the amount for an additional charge.','Y','Charge amount',TO_DATE('2009-09-11 14:39:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:42 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2166,0,50558,50011,50001,'C_Order_CreatedBy','o.CreatedBy',TO_DATE('2009-09-11 14:39:41','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_DATE('2009-09-11 14:39:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:43 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2180,0,50559,50011,50001,'C_Order_IsTransferred','o.IsTransferred',TO_DATE('2009-09-11 14:39:42','YYYY-MM-DD HH24:MI:SS'),0,'Transferred to General Ledger (i.e. accounted)','D','The transferred checkbox indicates if the transactions associated with this document should be transferred to the General Ledger.','Y','Transferred',TO_DATE('2009-09-11 14:39:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:44 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2181,0,50560,50011,50001,'C_Order_DateOrdered','o.DateOrdered',TO_DATE('2009-09-11 14:39:43','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','D','Indicates the Date an item was ordered.','Y','Date Ordered',TO_DATE('2009-09-11 14:39:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:46 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4298,0,50561,50011,50001,'C_Order_IsDiscountPrinted','o.IsDiscountPrinted',TO_DATE('2009-09-11 14:39:44','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','D','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Discount Printed',TO_DATE('2009-09-11 14:39:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:47 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2453,0,50562,50011,50001,'C_Order_Processing','o.Processing',TO_DATE('2009-09-11 14:39:46','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Process Now',TO_DATE('2009-09-11 14:39:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:48 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2167,0,50563,50011,50001,'C_Order_Updated','o.Updated',TO_DATE('2009-09-11 14:39:47','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_DATE('2009-09-11 14:39:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:49 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2182,0,50564,50011,50001,'C_Order_DatePromised','o.DatePromised',TO_DATE('2009-09-11 14:39:48','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','D','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_DATE('2009-09-11 14:39:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:50 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,4699,0,50565,50011,50001,'C_Order_IsSelected','o.IsSelected',TO_DATE('2009-09-11 14:39:49','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Selected',TO_DATE('2009-09-11 14:39:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:51 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3721,0,50566,50011,50001,'C_Order_DeliveryRule','o.DeliveryRule',TO_DATE('2009-09-11 14:39:50','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Delivery Rule',TO_DATE('2009-09-11 14:39:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:52 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3400,0,50567,50011,50001,'C_Order_C_BPartner_Location_ID','o.C_BPartner_Location_ID',TO_DATE('2009-09-11 14:39:51','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','D','The Partner address indicates the location of a Business Partner','Y','Partner Location',TO_DATE('2009-09-11 14:39:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:54 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4650,0,50568,50011,50001,'C_Order_Posted','o.Posted',TO_DATE('2009-09-11 14:39:52','YYYY-MM-DD HH24:MI:SS'),0,'Posting status','D','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Posted',TO_DATE('2009-09-11 14:39:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:55 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3719,0,50569,50011,50001,'C_Order_DatePrinted','o.DatePrinted',TO_DATE('2009-09-11 14:39:54','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','D','Indicates the Date that a document was printed.','Y','Date printed',TO_DATE('2009-09-11 14:39:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:57 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3718,0,50570,50011,50001,'C_Order_IsSOTrx','o.IsSOTrx',TO_DATE('2009-09-11 14:39:55','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','D','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Sales Transaction',TO_DATE('2009-09-11 14:39:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:58 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3403,0,50571,50011,50001,'C_Order_C_Activity_ID','o.C_Activity_ID',TO_DATE('2009-09-11 14:39:57','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_DATE('2009-09-11 14:39:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:39:59 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8166,0,50572,50011,50001,'C_Order_SendEMail','o.SendEMail',TO_DATE('2009-09-11 14:39:58','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','D','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Send EMail',TO_DATE('2009-09-11 14:39:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:00 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3722,0,50573,50011,50001,'C_Order_FreightCostRule','o.FreightCostRule',TO_DATE('2009-09-11 14:39:59','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','D','The Freight Cost Rule indicates the method used when charging for freight.','Y','Freight Cost Rule',TO_DATE('2009-09-11 14:39:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:02 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8764,0,50574,50011,50001,'C_Order_Bill_BPartner_ID','o.Bill_BPartner_ID',TO_DATE('2009-09-11 14:40:00','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to be invoiced','D','If empty the shipment business partner will be invoiced','Y','Invoice Partner',TO_DATE('2009-09-11 14:40:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:03 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5348,0,50575,50011,50001,'C_Order_C_Payment_ID','o.C_Payment_ID',TO_DATE('2009-09-11 14:40:02','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','D','The Payment is a unique identifier of this payment.','Y','Payment',TO_DATE('2009-09-11 14:40:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:04 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,10925,0,50576,50011,50001,'C_Order_Pay_BPartner_ID','o.Pay_BPartner_ID',TO_DATE('2009-09-11 14:40:03','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner responsible for the payment','D','Y','Payment BPartner',TO_DATE('2009-09-11 14:40:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:05 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5349,0,50577,50011,50001,'C_Order_C_CashLine_ID','o.C_CashLine_ID',TO_DATE('2009-09-11 14:40:04','YYYY-MM-DD HH24:MI:SS'),0,'Cash Journal Line','D','The Cash Journal Line indicates a unique line in a cash journal.','Y','Cash Journal Line',TO_DATE('2009-09-11 14:40:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:06 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,8763,0,50578,50011,50001,'C_Order_Bill_User_ID','o.Bill_User_ID',TO_DATE('2009-09-11 14:40:05','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for invoicing','D','Y','Invoice Contact',TO_DATE('2009-09-11 14:40:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:07 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8765,0,50579,50011,50001,'C_Order_CopyFrom','o.CopyFrom',TO_DATE('2009-09-11 14:40:06','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record','D','Copy From Record','Y','Copy From',TO_DATE('2009-09-11 14:40:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:08 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,8766,0,50580,50011,50001,'C_Order_Bill_Location_ID','o.Bill_Location_ID',TO_DATE('2009-09-11 14:40:07','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for invoicing','D','Y','Invoice Location',TO_DATE('2009-09-11 14:40:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:12 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3398,0,50581,50011,50001,'C_Order_Processed','o.Processed',TO_DATE('2009-09-11 14:40:08','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','D','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_DATE('2009-09-11 14:40:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:13 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4651,0,50582,50011,50001,'C_Order_IsTaxIncluded','o.IsTaxIncluded',TO_DATE('2009-09-11 14:40:12','YYYY-MM-DD HH24:MI:SS'),0,'Tax is included in the price ','D','The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.','Y','Price includes Tax',TO_DATE('2009-09-11 14:40:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:14 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8832,0,50583,50011,50001,'C_Order_IsSelfService','o.IsSelfService',TO_DATE('2009-09-11 14:40:13','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','D','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Self-Service',TO_DATE('2009-09-11 14:40:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:16 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9568,0,50584,50011,50001,'C_Order_User2_ID','o.User2_ID',TO_DATE('2009-09-11 14:40:14','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_DATE('2009-09-11 14:40:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:17 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,10924,0,50585,50011,50001,'C_Order_Pay_Location_ID','o.Pay_Location_ID',TO_DATE('2009-09-11 14:40:16','YYYY-MM-DD HH24:MI:SS'),0,'Location of the Business Partner responsible for the payment','D','Y','Payment Location',TO_DATE('2009-09-11 14:40:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:18 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9331,0,50586,50011,50001,'C_Order_AD_OrgTrx_ID','o.AD_OrgTrx_ID',TO_DATE('2009-09-11 14:40:17','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_DATE('2009-09-11 14:40:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:19 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10297,0,50587,50011,50001,'C_Order_C_ConversionType_ID','o.C_ConversionType_ID',TO_DATE('2009-09-11 14:40:18','YYYY-MM-DD HH24:MI:SS'),0,'Currency Conversion Rate Type','D','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Currency Type',TO_DATE('2009-09-11 14:40:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:20 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10926,0,50588,50011,50001,'C_Order_Ref_Order_ID','o.Ref_Order_ID',TO_DATE('2009-09-11 14:40:19','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','D','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Referenced Order',TO_DATE('2009-09-11 14:40:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:21 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15899,0,50589,50011,50001,'C_Order_Volume','o.Volume',TO_DATE('2009-09-11 14:40:20','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','D','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Volume',TO_DATE('2009-09-11 14:40:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:22 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15900,0,50590,50011,50001,'C_Order_Weight','o.Weight',TO_DATE('2009-09-11 14:40:21','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','D','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Weight',TO_DATE('2009-09-11 14:40:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:23 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2204,0,50591,50011,50001,'C_Order_M_PriceList_ID','o.M_PriceList_ID',TO_DATE('2009-09-11 14:40:22','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Price List',TO_DATE('2009-09-11 14:40:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:24 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52063,0,50592,50011,50001,'C_Order_OrderType','o.OrderType',TO_DATE('2009-09-11 14:40:23','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','OrderType',TO_DATE('2009-09-11 14:40:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:25 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52064,0,50593,50011,50001,'C_Order_AmountTendered','o.AmountTendered',TO_DATE('2009-09-11 14:40:24','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AmountTendered',TO_DATE('2009-09-11 14:40:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:26 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52065,0,50594,50011,50001,'C_Order_AmountRefunded','o.AmountRefunded',TO_DATE('2009-09-11 14:40:25','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AmountRefunded',TO_DATE('2009-09-11 14:40:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:28 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,52070,0,50595,50011,50001,'C_Order_C_POS_ID','o.C_POS_ID',TO_DATE('2009-09-11 14:40:26','YYYY-MM-DD HH24:MI:SS'),0,'Point of Sales Terminal','D','The POS Terminal defines the defaults and functions available for the POS Form','Y','POS Terminal',TO_DATE('2009-09-11 14:40:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:29 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55322,0,50596,50011,50001,'C_Order_Link_Order_ID','o.Link_Order_ID',TO_DATE('2009-09-11 14:40:28','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order to the purchase order that is generated from it.','D','Y','Linked Order',TO_DATE('2009-09-11 14:40:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:30 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4019,0,50597,50011,50001,'C_Order_PaymentRule','o.PaymentRule',TO_DATE('2009-09-11 14:40:29','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','D','The Payment Rule indicates the method of invoice payment.','Y','Payment Rule',TO_DATE('2009-09-11 14:40:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:32 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,56376,0,50598,50011,50001,'C_Order_M_FreightCategory_ID','o.M_FreightCategory_ID',TO_DATE('2009-09-11 14:40:30','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','D','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Freight Category',TO_DATE('2009-09-11 14:40:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,55314,0,50599,50011,50001,'C_Order_DropShip_BPartner_ID','o.DropShip_BPartner_ID',TO_DATE('2009-09-11 14:40:32','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','D','If empty the business partner will be shipped to.','Y','Drop Shipment Partner',TO_DATE('2009-09-11 14:40:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:33 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55315,0,50600,50011,50001,'C_Order_DropShip_Location_ID','o.DropShip_Location_ID',TO_DATE('2009-09-11 14:40:33','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','D','Y','Drop Shipment Location',TO_DATE('2009-09-11 14:40:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:35 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55316,0,50601,50011,50001,'C_Order_DropShip_User_ID','o.DropShip_User_ID',TO_DATE('2009-09-11 14:40:34','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','D','Y','Drop Shipment Contact',TO_DATE('2009-09-11 14:40:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:36 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,11580,0,50602,50011,50001,'C_Order_IsDropShip','o.IsDropShip',TO_DATE('2009-09-11 14:40:35','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Drop Shipment',TO_DATE('2009-09-11 14:40:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:40:37 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,57127,0,50603,50011,50001,'C_Order_PromotionCode','o.PromotionCode',TO_DATE('2009-09-11 14:40:36','YYYY-MM-DD HH24:MI:SS'),0,'User entered promotion code at sales time','D','If present, user entered the promotion code at sales time to get this promotion','Y','Promotion Code',TO_DATE('2009-09-11 14:40:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50719,50002,0,193,0,19,50539,TO_DATE('2009-09-11 14:43:28','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','EE03','Indicates the Currency to be used when processing or reporting on this record','Y','Y','N','N','N','N','C_Order_C_Currency_ID',10,TO_DATE('2009-09-11 14:43:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50719 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50720,50002,0,608,0,18,50521,TO_DATE('2009-09-11 14:43:30','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE03','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_Order_UpdatedBy',11,TO_DATE('2009-09-11 14:43:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50720 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50721,50002,0,558,0,13,50522,TO_DATE('2009-09-11 14:43:31','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE03','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','N','N','N','N','C_Order_C_Order_ID',12,TO_DATE('2009-09-11 14:43:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50721 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50722,50002,0,968,0,18,50523,TO_DATE('2009-09-11 14:43:32','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','C_Order_C_Charge_ID',13,TO_DATE('2009-09-11 14:43:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50722 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50723,50002,0,559,0,17,50524,TO_DATE('2009-09-11 14:43:33','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','EE03','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','C_Order_InvoiceRule',14,TO_DATE('2009-09-11 14:43:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50723 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50724,50002,0,306,0,12,50525,TO_DATE('2009-09-11 14:43:35','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','C_Order_FreightAmt',15,TO_DATE('2009-09-11 14:43:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50724 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50725,50002,0,274,0,17,50526,TO_DATE('2009-09-11 14:43:37','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE03','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','C_Order_DeliveryViaRule',16,TO_DATE('2009-09-11 14:43:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50725 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50726,50002,0,351,0,20,50527,TO_DATE('2009-09-11 14:43:38','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE03','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','N','N','N','N','C_Order_IsApproved',17,TO_DATE('2009-09-11 14:43:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50726 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50727,50002,0,363,0,20,50528,TO_DATE('2009-09-11 14:43:39','YYYY-MM-DD HH24:MI:SS'),0,'Credit  has been approved','EE03','Credit Approved indicates if the credit approval was successful for Orders','Y','Y','N','N','N','N','C_Order_IsCreditApproved',18,TO_DATE('2009-09-11 14:43:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50727 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50728,50002,0,367,0,20,50529,TO_DATE('2009-09-11 14:43:45','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_IsDelivered',19,TO_DATE('2009-09-11 14:43:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50728 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50729,50002,0,387,0,20,50530,TO_DATE('2009-09-11 14:43:46','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?','EE03','If selected, invoices are created','Y','Y','N','N','N','N','C_Order_IsInvoiced',20,TO_DATE('2009-09-11 14:43:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50729 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50730,50002,0,459,0,19,50531,TO_DATE('2009-09-11 14:43:48','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','C_Order_M_Warehouse_ID',21,TO_DATE('2009-09-11 14:43:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50730 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50731,50002,0,348,0,20,50532,TO_DATE('2009-09-11 14:43:50','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_Order_IsActive',22,TO_DATE('2009-09-11 14:43:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50731 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50732,50002,0,263,0,15,50533,TO_DATE('2009-09-11 14:43:52','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Date','EE03','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','N','N','N','N','C_Order_DateAcct',23,TO_DATE('2009-09-11 14:43:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50732 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50733,50002,0,1063,0,18,50534,TO_DATE('2009-09-11 14:43:53','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','C_Order_SalesRep_ID',24,TO_DATE('2009-09-11 14:43:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50733 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50734,50002,0,245,0,16,50535,TO_DATE('2009-09-11 14:43:55','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE03','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_Order_Created',25,TO_DATE('2009-09-11 14:43:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50734 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:43:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50735,50002,0,550,0,19,50536,TO_DATE('2009-09-11 14:43:57','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','C_Order_C_Campaign_ID',26,TO_DATE('2009-09-11 14:43:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:43:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50735 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50736,50002,0,204,0,19,50537,TO_DATE('2009-09-11 14:43:58','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','EE03','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','C_Order_C_PaymentTerm_ID',27,TO_DATE('2009-09-11 14:43:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50736 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50737,50002,0,197,0,18,50538,TO_DATE('2009-09-11 14:44:00','YYYY-MM-DD HH24:MI:SS'),0,'Target document type for conversing documents','EE03','You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.','Y','Y','N','N','N','N','C_Order_C_DocTypeTarget_ID',28,TO_DATE('2009-09-11 14:44:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50737 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50738,50002,0,598,0,12,50540,TO_DATE('2009-09-11 14:44:01','YYYY-MM-DD HH24:MI:SS'),0,'Total of all document lines','EE03','The Total amount displays the total of all lines in document currency','Y','Y','N','N','N','N','C_Order_TotalLines',29,TO_DATE('2009-09-11 14:44:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50738 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50739,50002,0,316,0,12,50541,TO_DATE('2009-09-11 14:44:02','YYYY-MM-DD HH24:MI:SS'),0,'Total amount of document','EE03','The Grand Total displays the total amount including Tax and Freight in document currency','Y','Y','N','N','N','N','C_Order_GrandTotal',30,TO_DATE('2009-09-11 14:44:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50739 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50740,50002,0,287,0,28,50542,TO_DATE('2009-09-11 14:44:03','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','N','N','C_Order_DocAction',31,TO_DATE('2009-09-11 14:44:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50740 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50741,50002,0,196,0,19,50543,TO_DATE('2009-09-11 14:44:04','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03','The Document Type determines document sequence and processing rules','Y','Y','N','N','N','N','C_Order_C_DocType_ID',32,TO_DATE('2009-09-11 14:44:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50741 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50742,50002,0,289,0,17,50544,TO_DATE('2009-09-11 14:44:05','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE03','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','N','N','C_Order_DocStatus',33,TO_DATE('2009-09-11 14:44:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50742 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50743,50002,0,113,0,19,50545,TO_DATE('2009-09-11 14:44:07','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_Order_AD_Org_ID',34,TO_DATE('2009-09-11 14:44:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50743 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50744,50002,0,290,0,10,50546,TO_DATE('2009-09-11 14:44:08','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE03','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','C_Order_DocumentNo',35,TO_DATE('2009-09-11 14:44:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50744 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50745,50002,0,399,0,20,50547,TO_DATE('2009-09-11 14:44:09','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE03','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','N','N','N','C_Order_IsPrinted',36,TO_DATE('2009-09-11 14:44:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50745 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50746,50002,0,522,0,17,50548,TO_DATE('2009-09-11 14:44:11','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE03','The Priority indicates the importance (high, medium, low) of this document','Y','Y','N','N','N','N','C_Order_PriorityRule',37,TO_DATE('2009-09-11 14:44:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50746 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50747,50002,0,613,0,18,50549,TO_DATE('2009-09-11 14:44:12','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_Order_User1_ID',38,TO_DATE('2009-09-11 14:44:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50747 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50748,50002,0,138,0,19,50550,TO_DATE('2009-09-11 14:44:14','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','EE03','The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','Y','N','N','N','N','C_Order_AD_User_ID',39,TO_DATE('2009-09-11 14:44:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50748 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50749,50002,0,187,0,30,50551,TO_DATE('2009-09-11 14:44:18','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_Order_C_BPartner_ID',40,TO_DATE('2009-09-11 14:44:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50749 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50750,50002,0,102,0,19,50552,TO_DATE('2009-09-11 14:44:19','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_Order_AD_Client_ID',41,TO_DATE('2009-09-11 14:44:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50750 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50751,50002,0,208,0,19,50553,TO_DATE('2009-09-11 14:44:20','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','C_Order_C_Project_ID',42,TO_DATE('2009-09-11 14:44:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50751 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50752,50002,0,952,0,10,50554,TO_DATE('2009-09-11 14:44:23','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','C_Order_POReference',43,TO_DATE('2009-09-11 14:44:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50752 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50753,50002,0,275,0,14,50555,TO_DATE('2009-09-11 14:44:30','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_Order_Description',44,TO_DATE('2009-09-11 14:44:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50753 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50754,50002,0,455,0,19,50556,TO_DATE('2009-09-11 14:44:32','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','C_Order_M_Shipper_ID',45,TO_DATE('2009-09-11 14:44:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50754 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50755,50002,0,849,0,12,50557,TO_DATE('2009-09-11 14:44:33','YYYY-MM-DD HH24:MI:SS'),0,'Charge Amount','EE03','The Charge Amount indicates the amount for an additional charge.','Y','Y','N','N','N','N','C_Order_ChargeAmt',46,TO_DATE('2009-09-11 14:44:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50755 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50756,50002,0,246,0,18,50558,TO_DATE('2009-09-11 14:44:35','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE03','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_Order_CreatedBy',47,TO_DATE('2009-09-11 14:44:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50756 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50757,50002,0,419,0,20,50559,TO_DATE('2009-09-11 14:44:36','YYYY-MM-DD HH24:MI:SS'),0,'Transferred to General Ledger (i.e. accounted)','EE03','The transferred checkbox indicates if the transactions associated with this document should be transferred to the General Ledger.','Y','Y','N','N','N','N','C_Order_IsTransferred',48,TO_DATE('2009-09-11 14:44:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50757 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50758,50002,0,268,0,15,50560,TO_DATE('2009-09-11 14:44:37','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE03','Indicates the Date an item was ordered.','Y','Y','Y','N','N','N','C_Order_DateOrdered',49,TO_DATE('2009-09-11 14:44:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50758 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50759,50002,0,1239,0,20,50561,TO_DATE('2009-09-11 14:44:38','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','EE03','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','C_Order_IsDiscountPrinted',50,TO_DATE('2009-09-11 14:44:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50759 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50760,50002,0,524,0,28,50562,TO_DATE('2009-09-11 14:44:39','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_Processing',51,TO_DATE('2009-09-11 14:44:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50760 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50761,50002,0,607,0,16,50563,TO_DATE('2009-09-11 14:44:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE03','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_Order_Updated',52,TO_DATE('2009-09-11 14:44:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50761 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50762,50002,0,269,0,15,50564,TO_DATE('2009-09-11 14:44:43','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE03','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','N','N','N','C_Order_DatePromised',53,TO_DATE('2009-09-11 14:44:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50762 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50763,50002,0,1321,0,20,50565,TO_DATE('2009-09-11 14:44:45','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_IsSelected',54,TO_DATE('2009-09-11 14:44:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50763 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50764,50002,0,555,0,17,50566,TO_DATE('2009-09-11 14:44:48','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','C_Order_DeliveryRule',55,TO_DATE('2009-09-11 14:44:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50764 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50765,50002,0,189,0,19,50567,TO_DATE('2009-09-11 14:44:49','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE03','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_Order_C_BPartner_Location_ID',56,TO_DATE('2009-09-11 14:44:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50765 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50766,50002,0,1308,0,28,50568,TO_DATE('2009-09-11 14:44:50','YYYY-MM-DD HH24:MI:SS'),0,'Posting status','EE03','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Y','N','N','N','N','C_Order_Posted',57,TO_DATE('2009-09-11 14:44:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50766 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50767,50002,0,1091,0,15,50569,TO_DATE('2009-09-11 14:44:51','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE03','Indicates the Date that a document was printed.','Y','Y','N','N','N','N','C_Order_DatePrinted',58,TO_DATE('2009-09-11 14:44:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50767 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50768,50002,0,1106,0,20,50570,TO_DATE('2009-09-11 14:44:52','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE03','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','C_Order_IsSOTrx',59,TO_DATE('2009-09-11 14:44:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50768 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50769,50002,0,1005,0,19,50571,TO_DATE('2009-09-11 14:44:54','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','C_Order_C_Activity_ID',60,TO_DATE('2009-09-11 14:44:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50769 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50770,50002,0,1978,0,20,50572,TO_DATE('2009-09-11 14:44:56','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','C_Order_SendEMail',61,TO_DATE('2009-09-11 14:44:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50770 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50771,50002,0,1007,0,17,50573,TO_DATE('2009-09-11 14:44:57','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE03','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','C_Order_FreightCostRule',62,TO_DATE('2009-09-11 14:44:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50771 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:44:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50772,50002,0,2039,0,18,50574,TO_DATE('2009-09-11 14:44:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to be invoiced','EE03','If empty the shipment business partner will be invoiced','Y','Y','N','N','N','N','C_Order_Bill_BPartner_ID',63,TO_DATE('2009-09-11 14:44:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:44:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50772 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50773,50002,0,1384,0,30,50575,TO_DATE('2009-09-11 14:44:59','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','EE03','The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','C_Order_C_Payment_ID',64,TO_DATE('2009-09-11 14:44:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50773 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50774,50002,0,2420,0,13,50576,TO_DATE('2009-09-11 14:45:00','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner responsible for the payment','EE03','Y','Y','N','N','N','N','C_Order_Pay_BPartner_ID',65,TO_DATE('2009-09-11 14:45:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50774 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50775,50002,0,1464,0,30,50577,TO_DATE('2009-09-11 14:45:01','YYYY-MM-DD HH24:MI:SS'),0,'Cash Journal Line','EE03','The Cash Journal Line indicates a unique line in a cash journal.','Y','Y','N','N','N','N','C_Order_C_CashLine_ID',66,TO_DATE('2009-09-11 14:45:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50775 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50776,50002,0,2041,0,18,50578,TO_DATE('2009-09-11 14:45:02','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for invoicing','EE03','Y','Y','N','N','N','N','C_Order_Bill_User_ID',67,TO_DATE('2009-09-11 14:45:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50776 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50777,50002,0,2037,0,28,50579,TO_DATE('2009-09-11 14:45:04','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record','EE03','Copy From Record','Y','Y','N','N','N','N','C_Order_CopyFrom',68,TO_DATE('2009-09-11 14:45:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50777 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50778,50002,0,2040,0,18,50580,TO_DATE('2009-09-11 14:45:05','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for invoicing','EE03','Y','Y','N','N','N','N','C_Order_Bill_Location_ID',69,TO_DATE('2009-09-11 14:45:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50778 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50779,50002,0,1047,0,20,50581,TO_DATE('2009-09-11 14:45:06','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','C_Order_Processed',70,TO_DATE('2009-09-11 14:45:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50779 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50780,50002,0,1065,0,20,50582,TO_DATE('2009-09-11 14:45:08','YYYY-MM-DD HH24:MI:SS'),0,'Tax is included in the price ','EE03','The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.','Y','Y','N','N','N','N','C_Order_IsTaxIncluded',71,TO_DATE('2009-09-11 14:45:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50780 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50781,50002,0,2063,0,20,50583,TO_DATE('2009-09-11 14:45:10','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','EE03','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Y','N','N','N','N','C_Order_IsSelfService',72,TO_DATE('2009-09-11 14:45:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50781 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50782,50002,0,614,0,18,50584,TO_DATE('2009-09-11 14:45:11','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_Order_User2_ID',73,TO_DATE('2009-09-11 14:45:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50782 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50783,50002,0,2421,0,13,50585,TO_DATE('2009-09-11 14:45:12','YYYY-MM-DD HH24:MI:SS'),0,'Location of the Business Partner responsible for the payment','EE03','Y','Y','N','N','N','N','C_Order_Pay_Location_ID',74,TO_DATE('2009-09-11 14:45:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50783 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50784,50002,0,112,0,18,50586,TO_DATE('2009-09-11 14:45:13','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','C_Order_AD_OrgTrx_ID',75,TO_DATE('2009-09-11 14:45:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50784 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50785,50002,0,2278,0,19,50587,TO_DATE('2009-09-11 14:45:14','YYYY-MM-DD HH24:MI:SS'),0,'Currency Conversion Rate Type','EE03','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Y','N','N','N','N','C_Order_C_ConversionType_ID',76,TO_DATE('2009-09-11 14:45:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50785 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50786,50002,0,2431,0,30,50588,TO_DATE('2009-09-11 14:45:16','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','EE03','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Y','N','N','N','N','C_Order_Ref_Order_ID',77,TO_DATE('2009-09-11 14:45:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50786 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50787,50002,0,627,0,22,50589,TO_DATE('2009-09-11 14:45:17','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE03','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','N','N','N','N','C_Order_Volume',78,TO_DATE('2009-09-11 14:45:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50787 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50788,50002,0,629,0,22,50590,TO_DATE('2009-09-11 14:45:18','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE03','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','N','N','N','N','C_Order_Weight',79,TO_DATE('2009-09-11 14:45:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50788 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50789,50002,0,449,0,19,50591,TO_DATE('2009-09-11 14:45:19','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE03','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','C_Order_M_PriceList_ID',80,TO_DATE('2009-09-11 14:45:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50789 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50790,50002,0,52020,0,10,50592,TO_DATE('2009-09-11 14:45:20','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_OrderType',81,TO_DATE('2009-09-11 14:45:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50790 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50791,50002,0,52021,0,22,50593,TO_DATE('2009-09-11 14:45:22','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_AmountTendered',82,TO_DATE('2009-09-11 14:45:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50791 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50792,50002,0,52022,0,22,50594,TO_DATE('2009-09-11 14:45:23','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y','N','N','N','N','C_Order_AmountRefunded',83,TO_DATE('2009-09-11 14:45:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50792 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50793,50002,0,2581,0,19,50595,TO_DATE('2009-09-11 14:45:25','YYYY-MM-DD HH24:MI:SS'),0,'Point of Sales Terminal','EE03','The POS Terminal defines the defaults and functions available for the POS Form','Y','Y','N','N','N','N','C_Order_C_POS_ID',84,TO_DATE('2009-09-11 14:45:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50793 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50794,50002,0,53462,0,18,50596,TO_DATE('2009-09-11 14:45:26','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order to the purchase order that is generated from it.','EE03','Y','Y','N','N','N','N','C_Order_Link_Order_ID',85,TO_DATE('2009-09-11 14:45:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50794 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50795,50002,0,1143,0,28,50597,TO_DATE('2009-09-11 14:45:31','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','EE03','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','C_Order_PaymentRule',86,TO_DATE('2009-09-11 14:45:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50795 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50796,50002,0,2111,0,19,50598,TO_DATE('2009-09-11 14:45:32','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','EE03','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Y','N','N','N','N','C_Order_M_FreightCategory_ID',87,TO_DATE('2009-09-11 14:45:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50796 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50797,50002,0,53458,0,30,50599,TO_DATE('2009-09-11 14:45:36','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE03','If empty the business partner will be shipped to.','Y','Y','N','N','N','N','C_Order_DropShip_BPartner_ID',88,TO_DATE('2009-09-11 14:45:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50797 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50798,50002,0,53459,0,18,50600,TO_DATE('2009-09-11 14:45:38','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE03','Y','Y','N','N','N','N','C_Order_DropShip_Location_ID',89,TO_DATE('2009-09-11 14:45:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50798 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50799,50002,0,53460,0,18,50601,TO_DATE('2009-09-11 14:45:39','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE03','Y','Y','N','N','N','N','C_Order_DropShip_User_ID',90,TO_DATE('2009-09-11 14:45:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50799 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50800,50002,0,2466,0,20,50602,TO_DATE('2009-09-11 14:45:40','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE03','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','N','N','N','N','C_Order_IsDropShip',91,TO_DATE('2009-09-11 14:45:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50800 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:45:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50801,50002,0,53809,0,10,50603,TO_DATE('2009-09-11 14:45:41','YYYY-MM-DD HH24:MI:SS'),0,'User entered promotion code at sales time','EE03','If present, user entered the promotion code at sales time to get this promotion','Y','Y','N','N','N','N','C_Order_PromotionCode',92,TO_DATE('2009-09-11 14:45:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 11, 2009 2:45:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50801 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50674
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50750
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50784
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50743
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50748
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50792
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50791
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50772
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50778
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50776
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50769
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50749
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50765
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50735
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50775
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50722
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50785
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50719
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50737
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50741
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50793
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50736
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50773
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50751
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50755
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50777
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50734
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50756
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50732
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50758
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50767
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50762
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50764
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50725
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50753
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50740
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50742
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50744
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50797
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50798
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50799
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50724
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50771
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50739
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50723
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50731
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50726
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50727
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50728
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50759
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50800
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50729
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50745
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50768
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50763
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50781
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50780
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50757
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50794
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50796
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50789
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50754
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50730
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50790
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50752
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50774
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50783
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50795
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50766
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50746
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50779
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50760
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50801
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50786
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50733
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50770
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50738
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50761
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50720
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50747
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50782
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50787
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50788
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50721
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50636
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50649
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50648
;

-- Sep 11, 2009 2:47:40 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50534
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50636
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50649
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50648
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50534
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50542
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50721
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50744
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50752
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50684
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50581
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50656
;

-- Sep 11, 2009 3:02:52 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50577
;

-- Sep 11, 2009 3:03:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 15:03:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50577
;

-- Sep 11, 2009 3:03:23 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 15:03:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50581
;

-- Sep 11, 2009 3:03:35 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_DATE('2009-09-11 15:03:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50656
;

-- Sep 11, 2009 3:03:59 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_DATE('2009-09-11 15:03:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50684
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50397
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50398
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50399
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50400
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50401
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50402
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50403
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50404
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50405
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50406
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50407
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50408
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50409
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50410
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50411
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50412
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50413
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50414
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50415
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50416
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50417
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50418
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50419
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50420
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50421
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50422
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50423
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50424
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50425
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50426
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50427
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50428
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50429
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50430
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50431
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50432
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50433
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50434
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50435
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50436
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50437
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50439
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50440
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50441
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50442
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50443
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50444
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50445
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50446
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50447
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50448
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50449
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50450
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50451
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50452
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50453
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50454
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50455
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50456
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50457
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50459
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50460
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50461
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50462
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50463
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50464
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50465
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50466
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50467
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50468
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50469
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50470
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50471
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50472
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50473
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50474
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50475
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50476
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50477
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50478
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50479
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50480
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50481
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50482
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50483
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50484
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50485
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50458
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50487
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50488
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50489
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50490
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50491
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50492
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50493
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50494
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50495
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50496
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50497
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50498
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50499
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50500
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50501
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50502
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50503
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50504
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50505
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50506
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50507
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50508
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50509
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50510
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50511
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50512
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50513
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50514
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50515
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50516
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50517
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50518
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50519
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50520
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50323
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50324
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50325
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50326
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50327
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50328
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50330
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50331
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50332
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50334
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50335
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50336
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50337
;

-- Sep 11, 2009 3:05:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50338
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50339
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50340
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50345
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50347
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50348
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50349
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50350
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50353
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50355
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50356
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50357
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50358
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50359
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50360
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50361
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50362
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50363
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50364
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50365
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50366
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50367
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50369
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50370
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50371
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50372
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50373
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50374
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50375
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50376
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50377
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50354
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50378
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50381
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50383
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50384
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50385
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50386
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50387
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50388
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50389
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50390
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50391
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50392
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50393
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50394
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50396
;

-- Sep 11, 2009 3:05:11 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50368
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50368
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50475
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50341
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50342
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50344
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50346
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50438
;

-- Sep 11, 2009 3:10:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50470
;

-- Sep 11, 2009 3:10:56 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_DATE('2009-09-11 15:10:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50470
;
-- Sep 11, 2009 3:13:54 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='Y',Updated=TO_DATE('2009-09-11 15:13:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50550
;

-- Sep 11, 2009 3:15:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=65,Updated=TO_DATE('2009-09-11 15:15:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50578
;

-- Sep 11, 2009 3:16:03 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,Updated=TO_DATE('2009-09-11 15:16:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50656
;

-- Sep 11, 2009 3:17:06 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=105,Updated=TO_DATE('2009-09-11 15:17:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50668
;

-- Sep 11, 2009 3:17:42 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,Updated=TO_DATE('2009-09-11 15:17:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50577
;

-- Sep 11, 2009 3:17:47 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,Updated=TO_DATE('2009-09-11 15:17:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50581
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50475
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50341
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50342
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50344
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50346
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50438
;

-- Sep 11, 2009 3:22:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50470
;

-- Sep 11, 2009 3:22:25 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='Y',Updated=TO_DATE('2009-09-11 15:22:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50352
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50475
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50341
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50342
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50344
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50382
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50346
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50438
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50395
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 3:25:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50379
;

-- Sep 11, 2009 3:37:48 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=15,Updated=TO_DATE('2009-09-11 15:37:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50380
;

-- Sep 11, 2009 3:37:55 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=16,Updated=TO_DATE('2009-09-11 15:37:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50379
;


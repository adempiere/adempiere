DROP VIEW rv_pp_mrp;
CREATE OR REPLACE VIEW rv_pp_mrp AS 
SELECT 
mrp.pp_mrp_id,
mrp.ad_client_id,
mrp.ad_org_id,
mrp.created,
mrp.createdby,
mrp.isactive,
mrp.isavailable,
mrp.updated,
mrp.updatedby,
pp.ismps,
mrp.name,
mrp.description,
mrp.c_order_id,
mrp.c_orderline_id,
mrp.dateordered,
mrp.dateconfirm,
mrp.datepromised,
mrp.datestartschedule,
mrp.datefinishschedule,
mrp.datestart,
mrp.datesimulation,
mrp.docstatus,
mrp.m_forecast_id,
mrp.m_forecastline_id,
mrp.value,
mrp.m_product_id,
mrp.m_requisition_id,
mrp.m_requisitionline_id,
mrp.m_warehouse_id,
mrp.pp_order_id,
mrp.pp_order_bomline_id,
mrp.dd_order_id,
mrp.dd_orderline_id,
mrp.qty,
mrp.name,
mrp.s_resource_id,
mrp.planner_id,
mrp.priority,
mrp.ordertype,
mrp.typemrp,
p.LowLevel,
mrp.C_BPartner_ID,
documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_mrp mrp
INNER JOIN M_Product p ON (mrp.M_Product_ID = p.M_Product_ID)
LEFT JOIN pp_product_planning pp ON (pp.m_product_id = mrp.m_product_id AND mrp.m_warehouse_id = pp.m_warehouse_id)
WHERE mrp.Qty<>0
UNION
SELECT 
0 ,
pp.ad_client_id,
pp.ad_org_id,
pp.created,
pp.createdby,
pp.isactive,
'Y',--mrp.isavailable
pp.updated,
pp.updatedby,
pp.ismps,
null, --name
null, --description
null, --mrp.c_order_id
null, --mrp.c_orderline_id
CURRENT_TIMESTAMP, --mrp.dateordered,
CURRENT_TIMESTAMP, --mrp.dateconfirm,
CURRENT_TIMESTAMP, --mrp.datepromised,
CURRENT_TIMESTAMP, --mrp.datestartschedule,
CURRENT_TIMESTAMP, --mrp.datefinishschedule,
CURRENT_TIMESTAMP, --mrp.datestart,
CURRENT_TIMESTAMP, --mrp.datesimulation,
'CO',  --mrp.docstatus,
null, --mrp.m_forecast_id,
null, --mrp.m_forecastline_id,
null, --mrp.value,
pp.m_product_id,
null, --mrp.m_requisition_id,
null, --mrp.m_requisitionline_id,
pp.m_warehouse_id,
null, --mrp.pp_order_id,
null, --pp_order_bomline_id
null, --mrp.dd_order_id,
null, --mrp.dd_orderline_id,
pp.safetystock - bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) AS qty, --mrp.qty,
null, --mrp.name,
pp.s_resource_id,
null, --planner_id
null, --mrp.priority,
'STK', --mrp.ordertype,
'D' , --mrp.typemrp,
p.LowLevel,
null, --C_BPartner_ID
'Safety Strock'   --documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_product_planning pp 
INNER JOIN M_Product p ON (pp.M_Product_ID = p.M_Product_ID)
WHERE bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) < pp.safetystock 
;

-- Jan 8, 2010 4:12:56 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53561,53229,TO_TIMESTAMP('2010-01-08 16:12:54','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Safety Stock',TO_TIMESTAMP('2010-01-08 16:12:54','YYYY-MM-DD HH24:MI:SS'),0,'STK')
;

-- Jan 8, 2010 4:12:56 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53561 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jan 8, 2010 4:16:25 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58801,53316,0,19,53021,'PP_MRP_ID',TO_TIMESTAMP('2010-01-08 16:16:24','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Material Requirement Planning',TO_TIMESTAMP('2010-01-08 16:16:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 8, 2010 4:16:25 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58801 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jan 8, 2010 4:16:26 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58802,53274,0,11,53021,'LowLevel',TO_TIMESTAMP('2010-01-08 16:16:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Low Level',TO_TIMESTAMP('2010-01-08 16:16:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 8, 2010 4:16:26 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58802 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jan 8, 2010 4:16:27 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58803,187,0,19,53021,'C_BPartner_ID',TO_TIMESTAMP('2010-01-08 16:16:26','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',131089,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','N','Business Partner ',TO_TIMESTAMP('2010-01-08 16:16:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 8, 2010 4:16:27 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58803 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jan 8, 2010 4:16:27 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58804,290,0,14,53021,'DocumentNo',TO_TIMESTAMP('2010-01-08 16:16:27','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',2147483647,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','N','Document No',TO_TIMESTAMP('2010-01-08 16:16:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 8, 2010 4:16:27 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58804 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jan 8, 2010 5:02:00 PM CST
-- MRP Sefety Stock
UPDATE AD_Window SET IsSOTrx='N',Updated=TO_TIMESTAMP('2010-01-08 17:02:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53064
;

-- Jan 8, 2010 5:02:22 PM CST
-- MRP Sefety Stock
UPDATE AD_Tab SET AD_Table_ID=53021,Updated=TO_TIMESTAMP('2010-01-08 17:02:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53180
;

-- Jan 8, 2010 5:02:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:02:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:02:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:02:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:02:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56442
;

-- Jan 8, 2010 5:02:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:02:56 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2010-01-08 17:02:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56413
;

-- Jan 8, 2010 5:03:06 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2010-01-08 17:03:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56433
;


-- Jan 8, 2010 5:05:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56413
;

-- Jan 8, 2010 5:05:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53443, Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', Name='Client',Updated=TO_TIMESTAMP('2010-01-08 17:05:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56413
;

-- Jan 8, 2010 5:05:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56413
;


-- Jan 8, 2010 5:05:55 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56433
;

-- Jan 8, 2010 5:06:00 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53414, Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', Name='Organization',Updated=TO_TIMESTAMP('2010-01-08 17:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56433
;

-- Jan 8, 2010 5:06:00 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56433
;

-- Jan 8, 2010 5:06:18 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56436
;

-- Jan 8, 2010 5:06:24 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53431, Description='Product, Service, Item', Help='Identifies an item which is either purchased or sold in this organization.', Name='Product',Updated=TO_TIMESTAMP('2010-01-08 17:06:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56436
;

-- Jan 8, 2010 5:06:24 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56436
;


-- Jan 8, 2010 5:06:33 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:06:37 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53438, Description='Resource', Help=NULL, Name='Resource',Updated=TO_TIMESTAMP('2010-01-08 17:06:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:06:37 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:06:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:06:49 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53434, Description='Storage Warehouse and Service Point', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', Name='Warehouse',Updated=TO_TIMESTAMP('2010-01-08 17:06:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:06:49 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:06:59 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:07:02 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53422, Description='Date Order was promised', Help='The Date Promised indicates the date, if any, that an Order was promised for.', Name='Date Promised',Updated=TO_TIMESTAMP('2010-01-08 17:07:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:07:02 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:07:14 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:07:22 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53425, Description=NULL, Help=NULL, Name='DateStartSchedule',Updated=TO_TIMESTAMP('2010-01-08 17:07:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:07:22 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:07:43 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:07:50 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53420, Description=NULL, Help=NULL, Name='DateFinishSchedule',Updated=TO_TIMESTAMP('2010-01-08 17:07:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:07:50 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:08:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:08:09 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53437, Description='Quantity', Help='The Quantity indicates the number of a specific product or item for this document.', Name='Quantity',Updated=TO_TIMESTAMP('2010-01-08 17:08:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:08:09 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:08:16 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:08:33 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53439, Description=NULL, Help=NULL, Name='TypeMRP',Updated=TO_TIMESTAMP('2010-01-08 17:08:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:08:33 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:08:43 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:08:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53440, Description=NULL, Help=NULL, Name='OrderType',Updated=TO_TIMESTAMP('2010-01-08 17:08:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:08:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:08:54 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:08:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53416, Description='Order', Help='The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.', Name='Order',Updated=TO_TIMESTAMP('2010-01-08 17:08:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:08:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:09:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:09:09 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53415, Description='Sales Order Line', Help='The Sales Order Line is a unique identifier for a line in an order.', Name='Sales Order Line',Updated=TO_TIMESTAMP('2010-01-08 17:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:09:09 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56441
;


-- Jan 8, 2010 5:09:15 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:09:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53435, Description=NULL, Help=NULL, Name='Manufacturing Order',Updated=TO_TIMESTAMP('2010-01-08 17:09:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:09:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56427
;


-- Jan 8, 2010 5:09:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:09:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=54048, Description=NULL, Help=NULL, Name='Manufacturing Order BOM Line',Updated=TO_TIMESTAMP('2010-01-08 17:09:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:09:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:10:01 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:10:06 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=55336, Description=NULL, Help=NULL, Name='Distribution Order',Updated=TO_TIMESTAMP('2010-01-08 17:10:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:10:06 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56422
;


-- Jan 8, 2010 5:10:14 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:10:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=55337, Description=NULL, Help=NULL, Name='Distribution Order Line',Updated=TO_TIMESTAMP('2010-01-08 17:10:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:10:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56423
;


-- Jan 8, 2010 5:10:24 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:10:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53433, Description='Material Requisition', Help=NULL, Name='Requisition',Updated=TO_TIMESTAMP('2010-01-08 17:10:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:10:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:10:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:10:41 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53432, Description='Material Requisition Line', Help=NULL, Name='Requisition Line',Updated=TO_TIMESTAMP('2010-01-08 17:10:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:10:41 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:10:56 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:11:01 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53430, Description='Material Forecast', Help='Material Forecast', Name='Forecast',Updated=TO_TIMESTAMP('2010-01-08 17:11:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:11:01 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56425
;


-- Jan 8, 2010 5:11:20 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:11:23 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53429, Description='Forecast Line', Help='Forecast of Product Qyantity by Period', Name='Forecast Line',Updated=TO_TIMESTAMP('2010-01-08 17:11:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:11:23 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:11:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:11:45 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=58803, Description='Identifies a Business Partner', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', Name='Business Partner ',Updated=TO_TIMESTAMP('2010-01-08 17:11:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:11:45 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:11:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:12:06 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53426, Description='The current status of the document', Help='The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field', Name='Document Status',Updated=TO_TIMESTAMP('2010-01-08 17:12:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:12:06 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:12:13 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:12:16 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=54050, Description=NULL, Help=NULL, Name='Planner',Updated=TO_TIMESTAMP('2010-01-08 17:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:12:16 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:12:24 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:12:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53427, Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', Name='Active',Updated=TO_TIMESTAMP('2010-01-08 17:12:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:12:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:12:39 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:12:45 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=54040, Description='Resource is available', Help='Resource is available for assignments', Name='Available',Updated=TO_TIMESTAMP('2010-01-08 17:12:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:12:45 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:12:51 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:12:55 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53421, Description='Date of Order', Help='Indicates the Date an item was ordered.', Name='Date Ordered',Updated=TO_TIMESTAMP('2010-01-08 17:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:12:55 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:14:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:15:01 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53419, Description=NULL, Help=NULL, Name='DateConfirm',Updated=TO_TIMESTAMP('2010-01-08 17:15:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:15:01 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56416
;


-- Jan 8, 2010 5:15:10 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:15:15 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53423, Description=NULL, Help=NULL, Name='DateSimulation',Updated=TO_TIMESTAMP('2010-01-08 17:15:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:15:15 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56418
;


-- Jan 8, 2010 5:15:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:15:26 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53424, Description=NULL, Help=NULL, Name='DateStart',Updated=TO_TIMESTAMP('2010-01-08 17:15:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:15:26 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56419
;


-- Jan 8, 2010 5:15:40 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:15:43 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=54037, Description='Optional short description of the record', Help='A description is limited to 255 characters.', Name='Description',Updated=TO_TIMESTAMP('2010-01-08 17:15:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:15:43 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:15:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:15:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=58801, Description=NULL, Help=NULL, Name='Material Requirement Planning',Updated=TO_TIMESTAMP('2010-01-08 17:15:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:15:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:16:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:16:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53413, Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', Name='Name',Updated=TO_TIMESTAMP('2010-01-08 17:16:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:16:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:16:20 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56435
;

-- Jan 8, 2010 5:16:23 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53436, Description='Indicates if this request is of a high, medium or low priority.', Help='The Priority indicates the importance of this request.', Name='Priority',Updated=TO_TIMESTAMP('2010-01-08 17:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56435
;

-- Jan 8, 2010 5:16:23 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56435
;

-- Jan 8, 2010 5:16:34 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56442
;

-- Jan 8, 2010 5:16:37 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=53444, Description='Search key for the record in the format required - must be unique', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', Name='Search Key',Updated=TO_TIMESTAMP('2010-01-08 17:16:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56442
;

-- Jan 8, 2010 5:16:37 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56442
;

-- Jan 8, 2010 5:16:51 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:16:56 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_Column_ID=54060, Description='Version of the table definition', Help='The Version indicates the version of this table definition.', Name='Version',Updated=TO_TIMESTAMP('2010-01-08 17:16:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:16:56 PM CST
-- MRP Sefety Stock
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:17:15 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58804,58572,0,53180,TO_TIMESTAMP('2010-01-08 17:17:13','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document',2147483647,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Document No',TO_TIMESTAMP('2010-01-08 17:17:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 8, 2010 5:17:15 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58572 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jan 8, 2010 5:17:16 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53428,58573,0,53180,TO_TIMESTAMP('2010-01-08 17:17:15','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Is MPS',TO_TIMESTAMP('2010-01-08 17:17:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 8, 2010 5:17:16 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58573 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jan 8, 2010 5:17:16 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58802,58574,0,53180,TO_TIMESTAMP('2010-01-08 17:17:16','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Low Level',TO_TIMESTAMP('2010-01-08 17:17:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 8, 2010 5:17:16 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58574 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jan 8, 2010 5:18:32 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53257,0,53064,'W',TO_TIMESTAMP('2010-01-08 17:18:31','YYYY-MM-DD HH24:MI:SS'),0,'View MRP Records','EE01','Y','N','N','N','View MRP Records',TO_TIMESTAMP('2010-01-08 17:18:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 8, 2010 5:18:32 PM CST
-- MRP Sefety Stock
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53257 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Jan 8, 2010 5:18:32 PM CST
-- MRP Sefety Stock
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53257, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53257)
;

-- Jan 8, 2010 5:18:43 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53035
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53036
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53037
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53038
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53039
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53040
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53224
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53257
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53041
;

-- Jan 8, 2010 5:18:44 PM CST
-- MRP Sefety Stock
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53042
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58573
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56435
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56442
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:50:57 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:52:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56429
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56436
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:53:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56410
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56411
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56436
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:54:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Jan 8, 2010 5:54:42 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:54:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56444
;

-- Jan 8, 2010 5:54:51 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:54:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58574
;

-- Jan 8, 2010 5:55:04 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:55:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 5:56:00 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-01-08 17:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56440
;

-- Jan 8, 2010 5:56:09 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:56:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56445
;

-- Jan 8, 2010 5:57:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:57:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 5:58:15 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-01-08 17:58:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58572
;

-- Jan 8, 2010 5:58:35 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET AD_Reference_ID=10, FieldLength=40,Updated=TO_TIMESTAMP('2010-01-08 17:58:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58804
;

-- Jan 8, 2010 7:26:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_TIMESTAMP('2010-01-08 19:26:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56414
;

-- Jan 8, 2010 7:26:26 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_TIMESTAMP('2010-01-08 19:26:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56415
;

-- Jan 8, 2010 7:26:32 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_TIMESTAMP('2010-01-08 19:26:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56419
;

-- Jan 8, 2010 7:26:37 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_TIMESTAMP('2010-01-08 19:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56420
;

-- Jan 8, 2010 7:26:52 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_TIMESTAMP('2010-01-08 19:26:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56416
;

-- Jan 8, 2010 7:27:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET AD_FieldGroup_ID=50009,Updated=TO_TIMESTAMP('2010-01-08 19:27:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56418
;

-- Jan 8, 2010 7:38:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='MRP Type determines whether a record is demand or supply', Name='MRP Type', PrintName='MRP Type',Updated=TO_TIMESTAMP('2010-01-08 19:38:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53282
;

-- Jan 8, 2010 7:38:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53282
;

-- Jan 8, 2010 7:38:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='TypeMRP', Name='MRP Type', Description='MRP Type determines whether a record is demand or supply', Help=NULL WHERE AD_Element_ID=53282
;

-- Jan 8, 2010 7:38:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='TypeMRP', Name='MRP Type', Description='MRP Type determines whether a record is demand or supply', Help=NULL, AD_Element_ID=53282 WHERE UPPER(ColumnName)='TYPEMRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:38:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='TypeMRP', Name='MRP Type', Description='MRP Type determines whether a record is demand or supply', Help=NULL WHERE AD_Element_ID=53282 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:38:28 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='MRP Type', Description='MRP Type determines whether a record is demand or supply', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53282) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:38:28 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='MRP Type', Name='MRP Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53282)
;

-- Jan 8, 2010 7:44:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', Name='Order Type', PrintName='Order Type',Updated=TO_TIMESTAMP('2010-01-08 19:44:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52020
;

-- Jan 8, 2010 7:44:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52020
;

-- Jan 8, 2010 7:44:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='OrderType', Name='Order Type', Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', Help=NULL WHERE AD_Element_ID=52020
;

-- Jan 8, 2010 7:44:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='OrderType', Name='Order Type', Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', Help=NULL, AD_Element_ID=52020 WHERE UPPER(ColumnName)='ORDERTYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:44:17 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='OrderType', Name='Order Type', Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', Help=NULL WHERE AD_Element_ID=52020 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:44:18 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Order Type', Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52020) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:44:18 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Order Type', Name='Order Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=52020)
;

-- Jan 8, 2010 7:47:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='The Low Level is used to calculate the material plan and determines if a net requirement should be exploited',Updated=TO_TIMESTAMP('2010-01-08 19:47:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53274
;

-- Jan 8, 2010 7:47:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53274
;

-- Jan 8, 2010 7:47:19 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='LowLevel', Name='Low Level', Description='The Low Level is used to calculate the material plan and determines if a net requirement should be exploited', Help=NULL WHERE AD_Element_ID=53274
;

-- Jan 8, 2010 7:47:20 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='LowLevel', Name='Low Level', Description='The Low Level is used to calculate the material plan and determines if a net requirement should be exploited', Help=NULL, AD_Element_ID=53274 WHERE UPPER(ColumnName)='LOWLEVEL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:47:20 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='LowLevel', Name='Low Level', Description='The Low Level is used to calculate the material plan and determines if a net requirement should be exploited', Help=NULL WHERE AD_Element_ID=53274 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:47:20 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Low Level', Description='The Low Level is used to calculate the material plan and determines if a net requirement should be exploited', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53274) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Determines if this product is part of the master production schedule',Updated=TO_TIMESTAMP('2010-01-08 19:49:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53261
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description='Determines if this product is part of the master production schedule', Help=NULL WHERE AD_Element_ID=53261
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Determines if this product is part of the master production schedule', Help=NULL, AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Determines if this product is part of the master production schedule', Help=NULL WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:49:05 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Is MPS', Description='Determines if this product is part of the master production schedule', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='MRP ID',Updated=TO_TIMESTAMP('2010-01-08 19:49:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53316
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53316
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='PP_MRP_ID', Name='Material Requirement Planning', Description='MRP ID', Help=NULL WHERE AD_Element_ID=53316
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='PP_MRP_ID', Name='Material Requirement Planning', Description='MRP ID', Help=NULL, AD_Element_ID=53316 WHERE UPPER(ColumnName)='PP_MRP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='PP_MRP_ID', Name='Material Requirement Planning', Description='MRP ID', Help=NULL WHERE AD_Element_ID=53316 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:49:46 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Material Requirement Planning', Description='MRP ID', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53316) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Manufacturing Order',Updated=TO_TIMESTAMP('2010-01-08 19:50:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53276
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53276
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='PP_Order_ID', Name='Manufacturing Order', Description='Manufacturing Order', Help=NULL WHERE AD_Element_ID=53276
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='PP_Order_ID', Name='Manufacturing Order', Description='Manufacturing Order', Help=NULL, AD_Element_ID=53276 WHERE UPPER(ColumnName)='PP_ORDER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='PP_Order_ID', Name='Manufacturing Order', Description='Manufacturing Order', Help=NULL WHERE AD_Element_ID=53276 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:50:08 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Manufacturing Order', Description='Manufacturing Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53276) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Scheduled start date for this Order', Name='Date Start Schedule', PrintName='Date Start Schedule',Updated=TO_TIMESTAMP('2010-01-08 19:51:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53281
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53281
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateStartSchedule', Name='Date Start Schedule', Description='Scheduled start date for this Order', Help=NULL WHERE AD_Element_ID=53281
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateStartSchedule', Name='Date Start Schedule', Description='Scheduled start date for this Order', Help=NULL, AD_Element_ID=53281 WHERE UPPER(ColumnName)='DATESTARTSCHEDULE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateStartSchedule', Name='Date Start Schedule', Description='Scheduled start date for this Order', Help=NULL WHERE AD_Element_ID=53281 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Date Start Schedule', Description='Scheduled start date for this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53281) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:51:30 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Start Schedule', Name='Date Start Schedule' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53281)
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Date Start for this Order', Name='Date Start', PrintName='Date Start',Updated=TO_TIMESTAMP('2010-01-08 19:52:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53280
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53280
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateStart', Name='Date Start', Description='Date Start for this Order', Help=NULL WHERE AD_Element_ID=53280
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateStart', Name='Date Start', Description='Date Start for this Order', Help=NULL, AD_Element_ID=53280 WHERE UPPER(ColumnName)='DATESTART' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateStart', Name='Date Start', Description='Date Start for this Order', Help=NULL WHERE AD_Element_ID=53280 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Date Start', Description='Date Start for this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53280) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:52:21 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Start', Name='Date Start' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53280)
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Simulation date for this Material Plan', Name='Date Simulation', PrintName='Date Simulation',Updated=TO_TIMESTAMP('2010-01-08 19:53:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53279
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53279
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateSimulation', Name='Date Simulation', Description='Simulation date for this Material Plan', Help=NULL WHERE AD_Element_ID=53279
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateSimulation', Name='Date Simulation', Description='Simulation date for this Material Plan', Help=NULL, AD_Element_ID=53279 WHERE UPPER(ColumnName)='DATESIMULATION' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateSimulation', Name='Date Simulation', Description='Simulation date for this Material Plan', Help=NULL WHERE AD_Element_ID=53279 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Date Simulation', Description='Simulation date for this Material Plan', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53279) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:53:38 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Simulation', Name='Date Simulation' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53279)
;

-- Jan 8, 2010 7:53:54 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnSQL=NULL,Updated=TO_TIMESTAMP('2010-01-08 19:53:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53420
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Scheduled Finish date for this Order', PrintName='Date Finish Schedule',Updated=TO_TIMESTAMP('2010-01-08 19:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateFinishSchedule', Name='DateFinishSchedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateFinishSchedule', Name='DateFinishSchedule', Description='Scheduled Finish date for this Order', Help=NULL, AD_Element_ID=53278 WHERE UPPER(ColumnName)='DATEFINISHSCHEDULE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateFinishSchedule', Name='DateFinishSchedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Element_ID=53278 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='DateFinishSchedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53278) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:54:27 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Finish Schedule', Name='DateFinishSchedule' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53278)
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Date Confirm of this Order', PrintName='Date Confirm',Updated=TO_TIMESTAMP('2010-01-08 19:55:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53277
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53277
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateConfirm', Name='DateConfirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Element_ID=53277
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateConfirm', Name='DateConfirm', Description='Date Confirm of this Order', Help=NULL, AD_Element_ID=53277 WHERE UPPER(ColumnName)='DATECONFIRM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateConfirm', Name='DateConfirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Element_ID=53277 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='DateConfirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53277) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:55:30 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Confirm', Name='DateConfirm' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53277)
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Name='Date Finish Schedule',Updated=TO_TIMESTAMP('2010-01-08 19:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='DateFinishSchedule', Name='Date Finish Schedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Element_ID=53278
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateFinishSchedule', Name='Date Finish Schedule', Description='Scheduled Finish date for this Order', Help=NULL, AD_Element_ID=53278 WHERE UPPER(ColumnName)='DATEFINISHSCHEDULE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='DateFinishSchedule', Name='Date Finish Schedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Element_ID=53278 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Date Finish Schedule', Description='Scheduled Finish date for this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53278) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:57:25 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Date Finish Schedule', Name='Date Finish Schedule' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53278)
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='% Scrap Quantity for this componet', Help='% Scrap Quantity for this componet', Name='% Scrap',Updated=TO_TIMESTAMP('2010-01-08 19:59:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='QtyScrap', Name='% Scrap', Description='% Scrap Quantity for this componet', Help='% Scrap Quantity for this componet' WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description='% Scrap Quantity for this componet', Help='% Scrap Quantity for this componet', AD_Element_ID=53289 WHERE UPPER(ColumnName)='QTYSCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description='% Scrap Quantity for this componet', Help='% Scrap Quantity for this componet' WHERE AD_Element_ID=53289 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='% Scrap', Description='% Scrap Quantity for this componet', Help='% Scrap Quantity for this componet' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53289) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 7:59:29 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='% Scrap', Name='% Scrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53289)
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Indicate the Scrap %  for calculate the Scrap Quantity', Name='Scrap %', PrintName='Scrap %',Updated=TO_TIMESTAMP('2010-01-08 20:00:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53257
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='Scrap', Name='Scrap %', Description='Indicate the Scrap %  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='Scrap %', Description='Indicate the Scrap %  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', AD_Element_ID=53257 WHERE UPPER(ColumnName)='SCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='Scrap %', Description='Indicate the Scrap %  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Scrap %', Description='Indicate the Scrap %  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53257) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 8:00:53 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Scrap %', Name='Scrap %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53257)
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Element SET Description='Scrap % Quantity for this componet', Help='Scrap % Quantity for this componet', Name='Scrap %', PrintName='Scrap %',Updated=TO_TIMESTAMP('2010-01-08 20:01:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Column SET ColumnName='QtyScrap', Name='Scrap %', Description='Scrap % Quantity for this componet', Help='Scrap % Quantity for this componet' WHERE AD_Element_ID=53289
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='Scrap %', Description='Scrap % Quantity for this componet', Help='Scrap % Quantity for this componet', AD_Element_ID=53289 WHERE UPPER(ColumnName)='QTYSCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='Scrap %', Description='Scrap % Quantity for this componet', Help='Scrap % Quantity for this componet' WHERE AD_Element_ID=53289 AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_Field SET Name='Scrap %', Description='Scrap % Quantity for this componet', Help='Scrap % Quantity for this componet' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53289) AND IsCentrallyMaintained='Y'
;

-- Jan 8, 2010 8:01:31 PM CST
-- MRP Sefety Stock
UPDATE AD_PrintFormatItem SET PrintName='Scrap %', Name='Scrap %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53289)
;


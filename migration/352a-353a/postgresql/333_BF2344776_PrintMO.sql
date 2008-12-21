
-- Nov 24, 2008 5:04:59 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='Manufacturing Order ** TEMPLATE ** ',Updated=TO_TIMESTAMP('2008-11-24 17:04:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50001
;

-- Nov 24, 2008 5:05:13 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintAreaType='C', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 17:05:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50077
;

-- Nov 24, 2008 5:08:16 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=56101, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:08:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50117
;

-- Nov 24, 2008 5:08:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=56102, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:08:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50118
;

-- Nov 24, 2008 5:08:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=56111, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:08:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50119
;

-- Nov 24, 2008 5:08:51 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=56103, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:08:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50120
;

-- Nov 24, 2008 5:14:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=53659, IsGroupBy='N', IsPageBreak='N', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 17:14:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50077
;

-- Nov 24, 2008 5:16:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsActive='N', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:16:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50059
;

-- Nov 24, 2008 5:27:32 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='Operation Activity ** TEMPLATE **', Name='Operation Activity ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 17:27:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50009
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50351
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50352
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50353
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50354
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50355
;

-- Nov 24, 2008 5:27:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50356
;

-- Nov 24, 2008 5:29:55 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_Column_ID=53659, AD_PrintFormatChild_ID=50009, IsActive='Y', IsGroupBy='N', IsPageBreak='N', PrintFormatType='P', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 17:29:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- Nov 24, 2008 5:31:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET AD_PrintFont_ID=119,Updated=TO_TIMESTAMP('2008-11-24 17:31:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50001
;

-- Nov 24, 2008 5:32:13 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET AD_PrintFont_ID=119,Updated=TO_TIMESTAMP('2008-11-24 17:32:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50009
;

-- Nov 24, 2008 5:35:07 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Name='Manufacturing_Order_ ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 17:35:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50001
;

-- Nov 24, 2008 5:35:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='Manufacturing Order', Name='Manufacturing_Order_Header ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 17:35:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50001
;

-- Nov 24, 2008 5:36:54 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='Manufacturing Order BOM Line', Name='Manufacturing_Order_BOMLine ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 17:36:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50002
;

-- Nov 24, 2008 5:37:22 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='MRP Detail',Updated=TO_TIMESTAMP('2008-11-24 17:37:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50000
;

------------------------------------------------
-- Nov 24, 2008 6:01:41 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53158,'3',TO_TIMESTAMP('2008-11-24 18:01:39','YYYY-MM-DD HH24:MI:SS'),0,'View Product Workflow','EE01','N','Y','N','N','N','N','Y','View Product Workflow','L','RV_PP_Product_Workflow',TO_TIMESTAMP('2008-11-24 18:01:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 24, 2008 6:01:41 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53158 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Nov 24, 2008 6:03:19 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_Table SET Description='View Order Workflow', Name='View Order Workflow', TableName='RV_PP_Order_Workflow',Updated=TO_TIMESTAMP('2008-11-24 18:03:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53158
;

-- Nov 24, 2008 6:03:19 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53158
;

-- Nov 24, 2008 6:05:58 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56483,102,0,19,53158,'AD_Client_ID',TO_TIMESTAMP('2008-11-24 18:05:56','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2008-11-24 18:05:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:05:58 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56483 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56484,113,0,19,53158,'AD_Org_ID',TO_TIMESTAMP('2008-11-24 18:05:59','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-11-24 18:05:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56484 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56485,245,0,16,53158,'Created',TO_TIMESTAMP('2008-11-24 18:06:00','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2008-11-24 18:06:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56485 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:01 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56486,246,0,18,110,53158,'CreatedBy',TO_TIMESTAMP('2008-11-24 18:06:00','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-11-24 18:06:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:01 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56486 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:02 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56487,348,0,20,53158,'IsActive',TO_TIMESTAMP('2008-11-24 18:06:01','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','N','Active',TO_TIMESTAMP('2008-11-24 18:06:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:02 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56487 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:04 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56488,607,0,16,53158,'Updated',TO_TIMESTAMP('2008-11-24 18:06:02','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-11-24 18:06:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:04 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56488 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:08 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56489,608,0,18,110,53158,'UpdatedBy',TO_TIMESTAMP('2008-11-24 18:06:04','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-11-24 18:06:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:08 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56489 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56490,53276,0,19,53158,'PP_Order_ID',TO_TIMESTAMP('2008-11-24 18:06:08','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Manufacturing Order',TO_TIMESTAMP('2008-11-24 18:06:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56490 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:11 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56491,289,0,17,53158,'DocStatus',TO_TIMESTAMP('2008-11-24 18:06:10','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','N','Document Status',TO_TIMESTAMP('2008-11-24 18:06:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:11 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56491 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:13 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56492,620,0,10,53158,'Value',TO_TIMESTAMP('2008-11-24 18:06:11','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE01',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','N','Search Key',TO_TIMESTAMP('2008-11-24 18:06:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:13 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56492 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:18 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56493,1777,0,19,53158,'S_Resource_ID',TO_TIMESTAMP('2008-11-24 18:06:13','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','N','N','Resource',TO_TIMESTAMP('2008-11-24 18:06:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:18 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56493 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:19 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56494,53284,0,11,53158,'DurationRequiered',TO_TIMESTAMP('2008-11-24 18:06:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Duration Requiered',TO_TIMESTAMP('2008-11-24 18:06:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:19 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56494 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:20 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56495,53283,0,11,53158,'DurationReal',TO_TIMESTAMP('2008-11-24 18:06:19','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Duration Real',TO_TIMESTAMP('2008-11-24 18:06:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:20 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56495 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:22 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56496,2320,0,22,53158,'Duration',TO_TIMESTAMP('2008-11-24 18:06:20','YYYY-MM-DD HH24:MI:SS'),0,'Normal Duration in Duration Unit','EE01',131089,'Expected (normal) Length of time for the execution','Y','N','N','N','N','N','N','N','N','N','Duration',TO_TIMESTAMP('2008-11-24 18:06:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:22 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56496 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:23 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56497,528,0,29,53158,'QtyDelivered',TO_TIMESTAMP('2008-11-24 18:06:22','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','N','Delivered Quantity',TO_TIMESTAMP('2008-11-24 18:06:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:23 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56497 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:24 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56498,53287,0,29,53158,'QtyReject',TO_TIMESTAMP('2008-11-24 18:06:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty Reject',TO_TIMESTAMP('2008-11-24 18:06:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:24 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56498 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:26 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56499,53289,0,29,53158,'QtyScrap',TO_TIMESTAMP('2008-11-24 18:06:24','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty Scrap',TO_TIMESTAMP('2008-11-24 18:06:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:26 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56499 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:27 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56500,53281,0,16,53158,'DateStartSchedule',TO_TIMESTAMP('2008-11-24 18:06:26','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateStartSchedule',TO_TIMESTAMP('2008-11-24 18:06:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:27 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56500 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:06:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56501,53278,0,16,53158,'DateFinishSchedule',TO_TIMESTAMP('2008-11-24 18:06:27','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateFinishSchedule',TO_TIMESTAMP('2008-11-24 18:06:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:06:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56501 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:08:52 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56502,53286,0,19,53158,'PP_Order_Workflow_ID',TO_TIMESTAMP('2008-11-24 18:08:50','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Manufacturing Order Workflow',TO_TIMESTAMP('2008-11-24 18:08:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:08:52 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56502 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:11:42 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,120,50037,101,100,53158,'N',TO_TIMESTAMP('2008-11-24 18:11:39','YYYY-MM-DD HH24:MI:SS'),0,'Manufacturing Order Workflow',0,0,'Y','N','N','N','Y','Manufacturing_Order_Workflow ** TEMPLATE **',TO_TIMESTAMP('2008-11-24 18:11:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 24, 2008 6:12:27 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET AD_PrintFont_ID=119,Updated=TO_TIMESTAMP('2008-11-24 18:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:12:58 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=130, AD_PrintPaper_ID=100, AD_Table_ID=53158, Name='RV_PP_Order_Workflow_2',Updated=TO_TIMESTAMP('2008-11-24 18:12:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:13:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56487,0,50964,50037,0,TO_TIMESTAMP('2008-11-24 18:12:58','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Active','C','F','Active',0,'N',0,TO_TIMESTAMP('2008-11-24 18:12:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50964 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56487) WHERE AD_PrintFormatItem_ID = 50964 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56487 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50964) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:21 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56483,0,50965,50037,0,TO_TIMESTAMP('2008-11-24 18:13:12','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,'N',0,TO_TIMESTAMP('2008-11-24 18:13:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:21 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50965 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56483) WHERE AD_PrintFormatItem_ID = 50965 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56483 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50965) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:32 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56485,0,50966,50037,0,TO_TIMESTAMP('2008-11-24 18:13:21','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created','C','F','Created',0,'N',0,TO_TIMESTAMP('2008-11-24 18:13:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:32 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50966 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:32 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56485) WHERE AD_PrintFormatItem_ID = 50966 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56485 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50966) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:36 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56486,0,50967,50037,0,TO_TIMESTAMP('2008-11-24 18:13:32','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created By','C','F','Created By',0,'N',0,TO_TIMESTAMP('2008-11-24 18:13:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:36 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50967 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56486) WHERE AD_PrintFormatItem_ID = 50967 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56486 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50967) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:40 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56501,0,50968,50037,0,TO_TIMESTAMP('2008-11-24 18:13:36','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'DateFinishSchedule','C','F','DateFinishSchedule',5,'N',0,TO_TIMESTAMP('2008-11-24 18:13:36','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:40 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50968 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56501) WHERE AD_PrintFormatItem_ID = 50968 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56501 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50968) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:42 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56500,0,50969,50037,0,TO_TIMESTAMP('2008-11-24 18:13:40','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'DateStartSchedule','C','F','DateStartSchedule',6,'N',0,TO_TIMESTAMP('2008-11-24 18:13:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:43 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50969 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:43 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56500) WHERE AD_PrintFormatItem_ID = 50969 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56500 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50969) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:46 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56497,0,50970,50037,0,TO_TIMESTAMP('2008-11-24 18:13:43','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Delivered Quantity','C','F','Delivered Qty',7,'N',0,TO_TIMESTAMP('2008-11-24 18:13:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:46 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50970 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56497) WHERE AD_PrintFormatItem_ID = 50970 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56497 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50970) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:48 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56491,0,50971,50037,0,TO_TIMESTAMP('2008-11-24 18:13:46','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Document Status','C','F','Doc Status',8,'N',0,TO_TIMESTAMP('2008-11-24 18:13:46','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:48 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50971 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:48 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56491) WHERE AD_PrintFormatItem_ID = 50971 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56491 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50971) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:50 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56496,0,50972,50037,0,TO_TIMESTAMP('2008-11-24 18:13:49','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Duration','C','F','Duration',9,'N',0,TO_TIMESTAMP('2008-11-24 18:13:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:50 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50972 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56496) WHERE AD_PrintFormatItem_ID = 50972 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56496 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50972) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:52 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56495,0,50973,50037,0,TO_TIMESTAMP('2008-11-24 18:13:50','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Duration Real','C','F','Duration Real',10,'N',0,TO_TIMESTAMP('2008-11-24 18:13:50','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:52 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50973 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:52 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56495) WHERE AD_PrintFormatItem_ID = 50973 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56495 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50973) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:54 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56494,0,50974,50037,0,TO_TIMESTAMP('2008-11-24 18:13:52','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Duration Requiered','C','F','Duration Requiered',11,'N',0,TO_TIMESTAMP('2008-11-24 18:13:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:54 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50974 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:54 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56494) WHERE AD_PrintFormatItem_ID = 50974 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56494 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50974) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:57 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56490,0,50975,50037,0,TO_TIMESTAMP('2008-11-24 18:13:54','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Manufacturing Order','C','F','Manufacturing Order',12,'N',0,TO_TIMESTAMP('2008-11-24 18:13:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:57 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50975 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:57 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56490) WHERE AD_PrintFormatItem_ID = 50975 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56490 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50975) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:13:59 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56502,0,50976,50037,0,TO_TIMESTAMP('2008-11-24 18:13:57','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Manufacturing Order Workflow','C','F','Manufacturing Order Workflow',13,'N',0,TO_TIMESTAMP('2008-11-24 18:13:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:13:59 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50976 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:13:59 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56502) WHERE AD_PrintFormatItem_ID = 50976 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56502 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50976) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56484,0,50977,50037,0,TO_TIMESTAMP('2008-11-24 18:13:59','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,'N',0,TO_TIMESTAMP('2008-11-24 18:13:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:00 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50977 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56484) WHERE AD_PrintFormatItem_ID = 50977 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56484 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50977) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:04 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56498,0,50978,50037,0,TO_TIMESTAMP('2008-11-24 18:14:00','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Qty Reject','C','F','Qty Reject',15,'N',0,TO_TIMESTAMP('2008-11-24 18:14:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:04 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50978 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:04 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56498) WHERE AD_PrintFormatItem_ID = 50978 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56498 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50978) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:07 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56499,0,50979,50037,0,TO_TIMESTAMP('2008-11-24 18:14:04','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Qty Scrap','C','F','Qty Scrap',16,'N',0,TO_TIMESTAMP('2008-11-24 18:14:04','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:07 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50979 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:07 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56499) WHERE AD_PrintFormatItem_ID = 50979 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56499 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50979) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56493,0,50980,50037,0,TO_TIMESTAMP('2008-11-24 18:14:07','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Resource','C','F','Resource',17,'N',0,TO_TIMESTAMP('2008-11-24 18:14:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50980 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56493) WHERE AD_PrintFormatItem_ID = 50980 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56493 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50980) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:15 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56492,0,50981,50037,0,TO_TIMESTAMP('2008-11-24 18:14:12','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Search Key','C','F','Search Key',18,'N',0,TO_TIMESTAMP('2008-11-24 18:14:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:15 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50981 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:15 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56492) WHERE AD_PrintFormatItem_ID = 50981 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56492 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50981) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:19 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56488,0,50982,50037,0,TO_TIMESTAMP('2008-11-24 18:14:15','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated','C','F','Updated',0,'N',0,TO_TIMESTAMP('2008-11-24 18:14:15','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:19 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50982 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:19 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56488) WHERE AD_PrintFormatItem_ID = 50982 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56488 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50982) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:14:21 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56489,0,50983,50037,0,TO_TIMESTAMP('2008-11-24 18:14:19','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated By','C','F','Updated By',0,'N',0,TO_TIMESTAMP('2008-11-24 18:14:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:14:21 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50983 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:14:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56489) WHERE AD_PrintFormatItem_ID = 50983 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56489 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50983) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:15:06 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Name='Manufacturing Order Workflow ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 18:15:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:15:20 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Name='Manufacturing_Order_Workflow ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 18:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50970
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50973
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50975
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50976
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50978
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50979
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=10,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50980
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:17:15 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56503,469,0,10,53158,'Name',TO_TIMESTAMP('2008-11-24 18:17:13','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','N','N','Name',1,TO_TIMESTAMP('2008-11-24 18:17:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:17:15 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56503 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:18:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56503,0,50984,50037,0,TO_TIMESTAMP('2008-11-24 18:18:10','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Name','C','F','Name',35,'N',0,TO_TIMESTAMP('2008-11-24 18:18:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:18:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50984 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:18:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56503) WHERE AD_PrintFormatItem_ID = 50984 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56503 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50984) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Nov 24, 2008 6:24:59 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56504,53240,0,11,53158,'MovingTime',TO_TIMESTAMP('2008-11-24 18:24:57','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Moving Time',TO_TIMESTAMP('2008-11-24 18:24:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:24:59 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56504 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:02 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56505,2331,0,11,53158,'WaitingTime',TO_TIMESTAMP('2008-11-24 18:24:59','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Waiting time','EE01',10,'Amount of time needed to prepare the performance of the task on Duration Units','Y','N','N','N','N','N','N','N','N','N','Waiting Time',TO_TIMESTAMP('2008-11-24 18:24:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:02 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56505 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:03 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56506,2777,0,11,53158,'SetupTime',TO_TIMESTAMP('2008-11-24 18:25:02','YYYY-MM-DD HH24:MI:SS'),0,'Setup time before starting Production','EE01',10,'Once per operation','Y','N','N','N','N','N','N','N','N','N','Setup Time',TO_TIMESTAMP('2008-11-24 18:25:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:03 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56506 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:06 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56507,53234,0,11,53158,'QueuingTime',TO_TIMESTAMP('2008-11-24 18:25:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Queuing Time',TO_TIMESTAMP('2008-11-24 18:25:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:06 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56507 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:08 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56508,187,0,19,53158,'C_BPartner_ID',TO_TIMESTAMP('2008-11-24 18:25:06','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','N','Business Partner ',TO_TIMESTAMP('2008-11-24 18:25:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:08 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56508 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56509,275,0,10,53158,'Description',TO_TIMESTAMP('2008-11-24 18:25:08','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Description',TO_TIMESTAMP('2008-11-24 18:25:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56509 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56510,53237,0,20,53158,'IsMilestone',TO_TIMESTAMP('2008-11-24 18:25:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','Is Milestone',TO_TIMESTAMP('2008-11-24 18:25:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:12 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56510 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:25:14 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56511,53238,0,20,53158,'IsSubcontracting',TO_TIMESTAMP('2008-11-24 18:25:12','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','Is Subcontracting',TO_TIMESTAMP('2008-11-24 18:25:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 24, 2008 6:25:14 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56511 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 24, 2008 6:26:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56509,0,100,50985,50037,0,0,TO_TIMESTAMP('2008-11-24 18:26:24','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Description','C','F',20,80,'N',0,TO_TIMESTAMP('2008-11-24 18:26:24','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:26:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50985 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:26:53 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56508,0,100,50986,50037,0,0,TO_TIMESTAMP('2008-11-24 18:26:51','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Business Partner','C','F',20,90,'N',0,TO_TIMESTAMP('2008-11-24 18:26:51','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:26:53 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50986 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:27:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56510,0,100,50987,50037,0,0,TO_TIMESTAMP('2008-11-24 18:27:26','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Is Milestone','C','F',20,100,'N',0,TO_TIMESTAMP('2008-11-24 18:27:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:27:28 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50987 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:27:45 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56511,0,100,50988,50037,0,0,TO_TIMESTAMP('2008-11-24 18:27:43','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'IsSubcontracting','C','F',20,110,'N',0,TO_TIMESTAMP('2008-11-24 18:27:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:27:45 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50988 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:28:22 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56504,0,100,50989,50037,0,0,TO_TIMESTAMP('2008-11-24 18:28:19','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'MovingTime','C','F',20,120,'N',0,TO_TIMESTAMP('2008-11-24 18:28:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:28:22 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50989 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:28:46 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56507,0,100,50990,50037,0,0,TO_TIMESTAMP('2008-11-24 18:28:45','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Queuing Time','C','F',20,130,'N',0,TO_TIMESTAMP('2008-11-24 18:28:45','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:28:46 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50990 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:29:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56505,0,100,50991,50037,0,0,TO_TIMESTAMP('2008-11-24 18:29:08','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Waiting Time','C','F',20,140,'N',0,TO_TIMESTAMP('2008-11-24 18:29:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:29:10 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50991 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:29:26 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:29:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:29:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:29:32 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:29:33 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:29:35 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:29:43 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', PrintName='Description', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:29:43 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:29:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Business Partner', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:29:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:29:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Is Milestone', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:29:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:29:57 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Is Subcontracting', PrintName='IsSubcontracting', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:29:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:29:57 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:30:03 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Moving Time', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:30:04 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Moving Time', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:30:04 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:30:07 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Queuing Time', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:30:07 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:30:25 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Waiting Time', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:30:25 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:30:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:30:35 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:30:39 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:30:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:30:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:30:47 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:30:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:30:57 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:30:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:31:09 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='L', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:31:10 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:31:11 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:31:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:31:13 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:31:14 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:31:16 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:31:17 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:31:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:31:26 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:31:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:31:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SeqNo=100, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:31:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SeqNo=90, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:31:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:31:49 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:31:52 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:31:57 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:31:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:33:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:33:47 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56506,0,100,50992,50037,0,0,TO_TIMESTAMP('2008-11-24 18:33:46','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Setup Time','C','F',20,160,'N',0,TO_TIMESTAMP('2008-11-24 18:33:46','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Nov 24, 2008 6:33:47 PM ECT
-- fix Manufacturing Order Print
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50992 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:34:00 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:34:08 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- Nov 24, 2008 6:34:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- Nov 24, 2008 6:34:22 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:34:25 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:34:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:34:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:34:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:34:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:34:39 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:34:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:34:48 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:34:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:35:21 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintFormatChild_ID=50037, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:35:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- Nov 24, 2008 6:38:10 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET Description='Manufacturing Order Node', Name='Manufacturing_Order_Node ** TEMPLATE **',Updated=TO_TIMESTAMP('2008-11-24 18:38:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:39:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, FieldAlignmentType='L', IsGroupBy='N', IsHeightOneLine='N', IsPageBreak='N', PrintName='Setup Time', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:39:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:39:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:40:08 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormat SET AD_PrintFont_ID=119,Updated=TO_TIMESTAMP('2008-11-24 18:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- Nov 24, 2008 6:43:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Start', PrintName='Start', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:43:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:43:50 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:45:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Finish', PrintName='Finish', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:45:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:45:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:45:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Waiting T', PrintName='Waiting T', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:45:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:45:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:45:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Setup T', PrintName='Setup T', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:45:41 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:45:48 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Duration T', PrintName='Duration T', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:45:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:45:48 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:45:58 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Moving T', PrintName='Moving T', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:45:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:45:58 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:46:03 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Queuing T', PrintName='Queuing T', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:46:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:46:03 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:46:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Status', PrintName='Status', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:46:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:46:12 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50971
;

-- Nov 24, 2008 6:46:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Is Subcontracting', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:46:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:46:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:47:45 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='B.P.', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:47:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:47:45 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50986
;

-- Nov 24, 2008 6:48:01 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Requiered', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:48:01 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:48:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='S.K.', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:48:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:48:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:49:05 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET BelowColumn=3, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:49:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

--------------------------------------------
-- Nov 24, 2008 6:49:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:49:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:53:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:53:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:53:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:53:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:53:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:53:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50974
;



-- Nov 24, 2008 6:50:46 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET BelowColumn=2, IsGroupBy='N', IsNextLine='Y', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:50:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:51:11 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET FieldAlignmentType='B', IsGroupBy='N', IsNextLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:51:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:51:27 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET BelowColumn=5, IsGroupBy='N', IsNextLine='Y', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- Nov 24, 2008 6:51:48 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET BelowColumn=9, IsGroupBy='N', IsNextLine='Y', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:51:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- Nov 24, 2008 6:52:09 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET BelowColumn=14, IsGroupBy='N', IsNextLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:52:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:52:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='S.K.- Name', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:52:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:52:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:52:38 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Start - Finish', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:52:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:52:38 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:52:51 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Duration T - Requiered', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:52:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:52:51 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:55:20 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='S.K./ Name', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:55:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:55:20 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:55:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='S.K.', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:55:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:55:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- Nov 24, 2008 6:55:38 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsNextLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:55:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:55:43 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsNextLine='Y', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:55:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- Nov 24, 2008 6:55:58 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Start/Finish', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:55:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:55:58 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50969
;

-- Nov 24, 2008 6:56:04 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Name/Description', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:56:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:56:04 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50984
;

-- Nov 24, 2008 6:56:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Duration/Total', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:56:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:56:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 6:56:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='M', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:56:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:56:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50987
;

-- Nov 24, 2008 6:56:39 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='S', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:56:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:56:39 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50988
;

-- Nov 24, 2008 6:58:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Queuing', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:58:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:58:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50990
;

-- Nov 24, 2008 6:58:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Moving', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:58:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:58:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50989
;

-- Nov 24, 2008 6:58:47 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Setup', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:58:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:58:47 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50992
;

-- Nov 24, 2008 6:58:52 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Waiting', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:58:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:58:52 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50991
;

-- Nov 24, 2008 6:59:06 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', MaxWidth=40, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 18:59:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- Nov 24, 2008 7:01:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Drt/Req', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:01:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 7:01:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- Nov 24, 2008 7:07:27 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Finish Date', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:07:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50091
;

-- Nov 24, 2008 7:07:27 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50091
;

-- Nov 24, 2008 7:07:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Warehouse   :', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:07:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:07:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:07:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Product :', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:07:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50082
;

-- Nov 24, 2008 7:07:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50082
;

-- Nov 24, 2008 7:07:47 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Quantity :', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:07:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50083
;

-- Nov 24, 2008 7:07:47 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50083
;

-- Nov 24, 2008 7:07:56 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Orden Date:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:07:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50087
;

-- Nov 24, 2008 7:07:56 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50087
;

-- Nov 24, 2008 7:08:06 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Promised D:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50088
;

-- Nov 24, 2008 7:08:06 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50088
;

-- Nov 24, 2008 7:08:13 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Date:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50087
;

-- Nov 24, 2008 7:08:13 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50087
;

-- Nov 24, 2008 7:08:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Promised:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50088
;

-- Nov 24, 2008 7:08:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50088
;

-- Nov 24, 2008 7:08:26 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Delivered Date:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50094
;

-- Nov 24, 2008 7:08:26 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50094
;

-- Nov 24, 2008 7:08:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Lot', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50095
;

-- Nov 24, 2008 7:08:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50095
;

-- Nov 24, 2008 7:08:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Workflow:', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:08:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- Nov 24, 2008 7:08:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50096
;

-- Nov 24, 2008 7:10:14 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Warehouse:', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:10:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:10:14 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:10:23 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Document Status:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:10:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:10:23 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:10:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET PrintName='Document No:', XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:10:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- Nov 24, 2008 7:10:44 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50076
;

-- Nov 24, 2008 7:11:15 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:11:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50077
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50080
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50082
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50083
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50084
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50085
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50086
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50079
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50087
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50088
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50089
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50090
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50091
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50092
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50093
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50094
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50095
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50077
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=230,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50096
;

-- Nov 24, 2008 7:12:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET SeqNo=240,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50059
;

-- Nov 24, 2008 7:14:49 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='N', SortNo=0, XPosition=340, XSpace=0, YPosition=10, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:14:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:15:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=130, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:15:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:16:31 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', SortNo=0, XPosition=20, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:16:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:17:23 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='Y', PrintAreaType='H', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:17:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:18:27 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET XPosition=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:18:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- Nov 24, 2008 7:18:34 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:18:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:19:25 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET XPosition=20, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:19:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- Nov 24, 2008 7:19:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, XSpace=200, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:19:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:19:35 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=20, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:19:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:19:54 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='N', SortNo=0, XPosition=200, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:19:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:20:15 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0, YSpace=40,Updated=TO_TIMESTAMP('2008-11-24 19:20:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:20:28 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XSpace=0, YPosition=50, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:20:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50081
;

-- Nov 24, 2008 7:21:40 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET XPosition=5, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:21:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- Nov 24, 2008 7:22:24 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='N', SortNo=0, XPosition=200, XSpace=0, YPosition=40, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:22:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- Nov 24, 2008 7:22:30 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=520, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:22:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:22:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=5, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:22:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- Nov 24, 2008 7:24:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Reserved Qty:', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-24 19:24:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50080
;

-- Nov 24, 2008 7:24:36 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50080
;

-- Nov 24, 2008 7:24:51 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Qty Reject:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:24:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50084
;

-- Nov 24, 2008 7:24:51 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50084
;

-- Nov 24, 2008 7:24:55 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Qty Scrap:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:24:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50085
;

-- Nov 24, 2008 7:24:55 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50085
;

-- Nov 24, 2008 7:25:03 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Qty Batchs:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:25:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50086
;

-- Nov 24, 2008 7:25:03 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50086
;

-- Nov 24, 2008 7:25:08 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Qty Batch Size:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:25:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50079
;

-- Nov 24, 2008 7:25:08 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50079
;

-- Nov 24, 2008 7:25:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='# Batch:', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2008-11-24 19:25:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50086
;

-- Nov 24, 2008 7:25:18 PM ECT
-- fix Manufacturing Order Print
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50086
;

UPDATE AD_PrintFormatItem SET AD_PrintFormatChild_ID=50002, IsGroupBy='N', IsPageBreak='N', PrintFormatType='P', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-11-25 17:15:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50077
;

-- Nov 25, 2008 2:22:37 PM ECT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_PrintForm SET Distrib_Order_PrintFormat_ID=50011, Manuf_Order_PrintFormat_ID=50001,Updated=TO_TIMESTAMP('2008-11-25 14:22:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintForm_ID=101
;

CREATE VIEW rv_pp_order_workflow AS
SELECT n.ad_client_id, n.ad_org_id, n.created, n.createdby, n.isactive, n.updated, n.updatedby, owf.PP_Order_Workflow_ID,
 n.name,n.pp_order_id, n.docstatus, n.value, n.s_resource_id, 
 n.durationrequiered, n.durationreal, n.durationrequiered - n.durationreal AS duration,n.movingtime,n.waitingtime,n.setuptime,n.queuingtime,   
 n.qtydelivered, n.qtyreject, n.qtyscrap, n.datestartschedule, n.datefinishschedule, n.c_bpartner_id, n.description,n.ismilestone,n.issubcontracting
 FROM pp_order_workflow owf INNER JOIN PP_Order_Node n ON (n.PP_Order_Workflow_ID=owf.PP_Order_Workflow_ID);


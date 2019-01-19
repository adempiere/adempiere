-- Sep 6, 2009 11:42:41 PM ECT
-- Warehouse Management
UPDATE AD_EntityType SET Classpath=NULL, Description='Libero Warehouse Management covers all Warehouse inbound and outbound operation', EntityType='EE03', Help='Project
http://wiki.adempiere.net/Sponsored_Development:_Libero_Warehouse_Management

Sponsored Development www.e-evolution.com', IsActive='Y', ModelPackage='org.eevolution.model', Name='e-Evolution Libero Warehouse Management', Version='1.00',Updated=TO_TIMESTAMP('2009-09-06 23:42:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_EntityType_ID=50007
;

-- Sep 6, 2009 11:42:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53090,TO_TIMESTAMP('2009-09-06 23:42:41','YYYY-MM-DD HH24:MI:SS'),0,'Outbound Order allow picking the products of Warehouse ','EE03','The Outbound Document will generate the Shipment Customer.','Y','Y','N','Y','Outbound Order','N',TO_TIMESTAMP('2009-09-06 23:42:41','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:42:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53090 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:42:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53233,'3',TO_TIMESTAMP('2009-09-06 23:42:44','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & OutBound Order','EE03','N','Y','N','Y','N','N','N','Inbound & OutBound Order','L','WM_InOutBound',TO_TIMESTAMP('2009-09-06 23:42:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:42:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53233 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:42:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53341,TO_TIMESTAMP('2009-09-06 23:42:47','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_InOutBound',1,'Y','N','Y','Y','WM_InOutBound','N',1000000,TO_TIMESTAMP('2009-09-06 23:42:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:42:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58192,102,0,19,53233,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:42:49','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:42:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:42:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58192 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:42:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58193,113,0,19,53233,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:42:52','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:42:52','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:42:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58193 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:42:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58194,348,0,20,53233,'IsActive',TO_TIMESTAMP('2009-09-06 23:42:55','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:42:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:42:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58194 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58195,245,0,16,53233,'Created',TO_TIMESTAMP('2009-09-06 23:42:58','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:42:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58195 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58196,607,0,16,53233,'Updated',TO_TIMESTAMP('2009-09-06 23:43:01','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:43:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58196 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58197,246,0,19,110,53233,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:43:05','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:43:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58197 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58198,608,0,19,110,53233,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:43:08','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:43:08','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58198 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53912,0,'WM_InOutBound_ID',TO_TIMESTAMP('2009-09-06 23:43:11','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & OutBound Order ID','Inbound & OutBound Order ID',TO_TIMESTAMP('2009-09-06 23:43:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:43:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53912 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:43:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58199,53912,0,13,53233,'WM_InOutBound_ID',TO_TIMESTAMP('2009-09-06 23:43:14','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Inbound & OutBound Order ID',TO_TIMESTAMP('2009-09-06 23:43:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58199 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:17 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_InOutBound_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Inbound & Outbound Order', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inbound & Outbound Order',Updated=TO_TIMESTAMP('2009-09-06 23:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53912
;

-- Sep 6, 2009 11:43:17 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53912
;

-- Sep 6, 2009 11:43:18 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Sep 6, 2009 11:43:18 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Sep 6, 2009 11:43:18 PM ECT
-- Warehouse Management
CREATE TABLE WM_InOutBound (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_InOutBound_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_InOutBound_Key PRIMARY KEY (WM_InOutBound_ID))
;

-- Sep 6, 2009 11:43:18 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2009-09-06 23:43:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Sep 6, 2009 11:43:18 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=129
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='AD_OrgTrx_ID', Description='Performing or initiating organization', EntityType='D', Help='The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.', IsActive='Y', Name='Trx Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Trx Organization',Updated=TO_TIMESTAMP('2009-09-06 23:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=112
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=112
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Organization selection, no summary, no 0', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Org (Trx)', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=130
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=130
;

-- Sep 6, 2009 11:43:19 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 155, AD_Display = 522, AD_Key = 528, isValueDisplayed = 'Y', OrderByClause = 'AD_Org.Name', EntityType ='D', WhereClause = 'AD_Org.IsSummary=''N'' AND AD_Org_ID <> 0' WHERE AD_Reference_ID = 130
;

-- Sep 6, 2009 11:43:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58200,112,0,18,130,53233,'AD_OrgTrx_ID',TO_TIMESTAMP('2009-09-06 23:43:20','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03',22,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','Y','N','Y','Trx Organization',TO_TIMESTAMP('2009-09-06 23:43:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58200 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:23 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN AD_OrgTrx_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:43:23 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2009-09-06 23:43:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Sep 6, 2009 11:43:23 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Sep 6, 2009 11:43:24 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_Activity_ID', Description='Business Activity', EntityType='D', Help='Activities indicate tasks that are performed and used to utilize Activity based Costing', IsActive='Y', Name='Activity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Activity',Updated=TO_TIMESTAMP('2009-09-06 23:43:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1005
;

-- Sep 6, 2009 11:43:24 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1005
;

-- Sep 6, 2009 11:43:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58201,1005,0,19,53233,'C_Activity_ID',TO_TIMESTAMP('2009-09-06 23:43:24','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03',22,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','Y','N','Y','Activity',TO_TIMESTAMP('2009-09-06 23:43:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58201 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:27 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN C_Activity_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:43:27 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_Campaign_ID', Description='Marketing Campaign', EntityType='D', Help='The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.', IsActive='Y', Name='Campaign', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Campaign',Updated=TO_TIMESTAMP('2009-09-06 23:43:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=550
;

-- Sep 6, 2009 11:43:27 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=550
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58202,550,0,19,53233,'C_Campaign_ID',TO_TIMESTAMP('2009-09-06 23:43:27','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','Y','N','Y','Campaign',TO_TIMESTAMP('2009-09-06 23:43:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58202 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN C_Campaign_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_DocType_ID', Description='Document type or rules', EntityType='D', Help='The Document Type determines document sequence and processing rules', IsActive='Y', Name='Document Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Type',Updated=TO_TIMESTAMP('2009-09-06 23:43:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=196
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=196
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='C_DocType', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:43:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=170
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=170
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 217, AD_Display = 1509, AD_Key = 1501, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'C_DocType.AD_Client_ID=@#AD_Client_ID@' WHERE AD_Reference_ID = 170
;

-- Sep 6, 2009 11:43:30 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='C_DocType.DocBaseType IN (''WMO'') AND C_DocType.IsSOTrx=''@IsSOTrx@''', Description='Document Type Warehouse Management', EntityType='EE03', IsActive='Y', Name='C_DocType Outbound', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:43:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52060
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58203,196,0,18,170,53233,52060,'org.compiere.model.CalloutInOut.docType','C_DocType_ID',TO_TIMESTAMP('2009-09-06 23:43:30','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03',22,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','Y','N','N','Y','N','N','Document Type',TO_TIMESTAMP('2009-09-06 23:43:30','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58203 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN C_DocType_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2009-09-06 23:43:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Sep 6, 2009 11:43:33 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Sep 6, 2009 11:43:34 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2009-09-06 23:43:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Sep 6, 2009 11:43:34 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Sep 6, 2009 11:43:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:43:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Sep 6, 2009 11:43:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Sep 6, 2009 11:43:34 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Sep 6, 2009 11:43:35 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DatePrinted', Description='Date the document was printed.', EntityType='D', Help='Indicates the Date that a document was printed.', IsActive='Y', Name='Date printed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date printed',Updated=TO_TIMESTAMP('2009-09-06 23:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1091
;

-- Sep 6, 2009 11:43:35 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1091
;

-- Sep 6, 2009 11:43:35 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Sep 6, 2009 11:43:35 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58204,1091,0,15,53233,'DatePrinted',TO_TIMESTAMP('2009-09-06 23:43:35','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE03',7,'Indicates the Date that a document was printed.','Y','N','N','N','N','N','N','N','Y','N','Y','Date printed',TO_TIMESTAMP('2009-09-06 23:43:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58204 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DatePrinted TIMESTAMP DEFAULT NULL 
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DeliveryRule', Description='Defines the timing of Delivery', EntityType='D', Help='The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.', IsActive='Y', Name='Delivery Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Delivery Rule',Updated=TO_TIMESTAMP('2009-09-06 23:43:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=555
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=555
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Delivery Rules list', EntityType='D', Help=NULL, IsActive='Y', Name='C_Order DeliveryRule', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:43:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=151
;

-- Sep 6, 2009 11:43:38 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=151
;

-- Sep 6, 2009 11:43:39 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=151, Description=NULL, EntityType='D', IsActive='Y', Name='Manual', Value='M',Updated=TO_TIMESTAMP('2009-09-06 23:43:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=791
;

-- Sep 6, 2009 11:43:39 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=791
;

-- Sep 6, 2009 11:43:39 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=151, Description=NULL, EntityType='D', IsActive='Y', Name='Force', Value='F',Updated=TO_TIMESTAMP('2009-09-06 23:43:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=678
;

-- Sep 6, 2009 11:43:39 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=678
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58205,555,0,17,151,53233,'DeliveryRule',TO_TIMESTAMP('2009-09-06 23:43:39','YYYY-MM-DD HH24:MI:SS'),0,'A','Defines the timing of Delivery','EE03',1,'The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','N','N','N','N','Y','N','N','Y','N','Y','Delivery Rule',TO_TIMESTAMP('2009-09-06 23:43:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58205 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DeliveryRule CHAR(1) DEFAULT 'A' NOT NULL
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DeliveryViaRule', Description='How the order will be delivered', EntityType='D', Help='The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.', IsActive='Y', Name='Delivery Via', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Delivery Via',Updated=TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=274
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=274
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Delivery via Rule list', EntityType='D', Help=NULL, IsActive='Y', Name='C_Order DeliveryViaRule', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=152
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=152
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=152, Description=NULL, EntityType='D', IsActive='Y', Name='Pickup', Value='P',Updated=TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=222
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=222
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=152, Description=NULL, EntityType='D', IsActive='Y', Name='Delivery', Value='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=223
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=223
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=152, Description=NULL, EntityType='D', IsActive='Y', Name='Shipper', Value='S',Updated=TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=224
;

-- Sep 6, 2009 11:43:43 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=224
;

-- Sep 6, 2009 11:43:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58206,274,0,17,152,53233,'DeliveryViaRule',TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),0,'P','How the order will be delivered','EE03',1,'The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','N','N','N','N','Y','N','N','Y','N','Y','Delivery Via',TO_TIMESTAMP('2009-09-06 23:43:43','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58206 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:46 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DeliveryViaRule CHAR(1) DEFAULT 'P' NOT NULL
;

-- Sep 6, 2009 11:43:46 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2009-09-06 23:43:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- Sep 6, 2009 11:43:46 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Sep 6, 2009 11:43:47 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Sep 6, 2009 11:43:47 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58207,275,0,14,53233,'Description',TO_TIMESTAMP('2009-09-06 23:43:47','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:43:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58207 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DocAction', Description='The targeted status of the document', EntityType='D', Help='You find the current status in the Document Status field. The options are listed in a popup', IsActive='Y', Name='Document Action', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Action',Updated=TO_TIMESTAMP('2009-09-06 23:43:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=287
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=287
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:43:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Document action list', EntityType='D', Help=NULL, IsActive='Y', Name='_Document Action', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:43:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=135
;

-- Sep 6, 2009 11:43:50 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=135
;

-- Sep 6, 2009 11:43:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,Description,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsValid,"limit",Name,Priority,PublishStatus,Updated,UpdatedBy,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (0,0,53233,50019,'1','e-Evolution',0,TO_TIMESTAMP('2009-09-06 23:43:52','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Process InOutBound Order)','10000000',1,'D','EE03','Y','Y','N','N',0,'Process_InOutBound_Order',0,'R',TO_TIMESTAMP('2009-09-06 23:43:52','YYYY-MM-DD HH24:MI:SS'),0,'Process_InOutBound_Order',0,0,'P',0)
;

-- Sep 6, 2009 11:43:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50019 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Sep 6, 2009 11:43:59 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50094,50019,'Z',0,TO_TIMESTAMP('2009-09-06 23:43:55','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Node)','CO',0,0,'EE03','Y','N','X','(Start)',0,'X',TO_TIMESTAMP('2009-09-06 23:43:55','YYYY-MM-DD HH24:MI:SS'),0,'(Start)',0,0,0,5,5)
;

-- Sep 6, 2009 11:43:59 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50094 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 6, 2009 11:44:02 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50095,50019,'D',0,TO_TIMESTAMP('2009-09-06 23:43:59','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Node)','PR',0,0,'EE03','Y','N','X','(DocPrepare)',0,'X',TO_TIMESTAMP('2009-09-06 23:43:59','YYYY-MM-DD HH24:MI:SS'),0,'(DocPrepare)',0,0,0,230,5)
;

-- Sep 6, 2009 11:44:02 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50095 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 6, 2009 11:44:04 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50096,50019,'D',0,TO_TIMESTAMP('2009-09-06 23:44:02','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Node)','CO',0,0,'EE03','Y','N','X','(DocComplete)',0,'X',TO_TIMESTAMP('2009-09-06 23:44:02','YYYY-MM-DD HH24:MI:SS'),0,'(DocComplete)',0,0,0,230,111)
;

-- Sep 6, 2009 11:44:04 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50096 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 6, 2009 11:44:07 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50097,50019,'D',0,TO_TIMESTAMP('2009-09-06 23:44:04','YYYY-MM-DD HH24:MI:SS'),0,'(DocAuto)','--',0,0,'EE03','Y','N','X','(DocAuto)',0,'X',TO_TIMESTAMP('2009-09-06 23:44:04','YYYY-MM-DD HH24:MI:SS'),0,'(DocAuto)',0,0,0,5,116)
;

-- Sep 6, 2009 11:44:07 PM ECT
-- Warehouse Management
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50097 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 6, 2009 11:44:07 PM ECT
-- Warehouse Management
UPDATE AD_Workflow SET AD_WF_Node_ID=50094, IsValid='Y',Updated=TO_TIMESTAMP('2009-09-06 23:44:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50019
;

-- Sep 6, 2009 11:44:07 PM ECT
-- Warehouse Management
UPDATE AD_Workflow SET AD_WF_Node_ID=181, IsValid='Y',Updated=TO_TIMESTAMP('2009-09-06 23:44:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50019
;

-- Sep 6, 2009 11:44:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Workflow_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53182,50019,'1',TO_TIMESTAMP('2009-09-06 23:44:07','YYYY-MM-DD HH24:MI:SS'),0,'Process In/Out Bound','EE03','Process In/Out Bound will create the Picking or Putaway  Order to pick or put the products into of Locators','Y','N','N','N','Process In/Out Bound','Y',0,0,TO_TIMESTAMP('2009-09-06 23:44:07','YYYY-MM-DD HH24:MI:SS'),0,'WM_InOutbound Process',NULL)
;

-- Sep 6, 2009 11:44:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53182 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58208,287,0,53182,28,135,53233,'DocAction',TO_TIMESTAMP('2009-09-06 23:44:11','YYYY-MM-DD HH24:MI:SS'),0,'CO','The targeted status of the document','EE03',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','N','N','N','N','Y','N','N','Y','N','Y','Document Action',TO_TIMESTAMP('2009-09-06 23:44:11','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58208 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DocAction CHAR(2) DEFAULT 'CO' NOT NULL
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DocStatus', Description='The current status of the document', EntityType='D', Help='The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field', IsActive='Y', Name='Document Status', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Status',Updated=TO_TIMESTAMP('2009-09-06 23:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=289
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=289
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Document Status list', EntityType='D', Help=NULL, IsActive='Y', Name='_Document Status', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=131
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=131
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Drafted', Value='DR',Updated=TO_TIMESTAMP('2009-09-06 23:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=164
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=164
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Completed', Value='CO',Updated=TO_TIMESTAMP('2009-09-06 23:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=165
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=165
;

-- Sep 6, 2009 11:44:14 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Approved', Value='AP',Updated=TO_TIMESTAMP('2009-09-06 23:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=166
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=166
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Not Approved', Value='NA',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=168
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=168
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Voided', Value='VO',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=172
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=172
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Invalid', Value='IN',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=173
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=173
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Reversed', Value='RE',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=176
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=176
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Closed', Value='CL',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=177
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=177
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Unknown', Value='??',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=190
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=190
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='In Progress', Value='IP',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=341
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=341
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Waiting Confirmation', Value='WC',Updated=TO_TIMESTAMP('2009-09-06 23:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=670
;

-- Sep 6, 2009 11:44:15 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=670
;

-- Sep 6, 2009 11:44:16 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Waiting Payment', Value='WP',Updated=TO_TIMESTAMP('2009-09-06 23:44:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=346
;

-- Sep 6, 2009 11:44:16 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=346
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58209,289,0,17,131,53233,'DocStatus',TO_TIMESTAMP('2009-09-06 23:44:16','YYYY-MM-DD HH24:MI:SS'),0,'DR','The current status of the document','EE03',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','Y','N','N','Y','N','Y','Document Status',TO_TIMESTAMP('2009-09-06 23:44:16','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58209 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DocStatus VARCHAR(2) DEFAULT 'DR' NOT NULL
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2009-09-06 23:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=290
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Sep 6, 2009 11:44:19 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58210,290,0,10,53233,'DocumentNo',TO_TIMESTAMP('2009-09-06 23:44:19','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE03',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','Y','N','N','Y','N','N','Document No',1,TO_TIMESTAMP('2009-09-06 23:44:19','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58210 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DocumentNo VARCHAR(30) NOT NULL
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DropShip_BPartner_ID', Description='Business Partner to ship to', EntityType='D', Help='If empty the business partner will be shipped to.', IsActive='Y', Name='Drop Shipment Partner', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Drop Shipment  Partner',Updated=TO_TIMESTAMP('2009-09-06 23:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53458
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53458
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Business Partner selection (no Summary)', EntityType='D', Help=NULL, IsActive='Y', Name='C_BPartner (Trx)', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=138
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=138
;

-- Sep 6, 2009 11:44:23 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 291, AD_Display = 2902, AD_Key = 2893, isValueDisplayed = 'Y', OrderByClause = '', EntityType ='D', WhereClause = 'C_BPartner.IsSummary=''N'' AND C_BPartner.IsActive=''Y''' WHERE AD_Reference_ID = 138
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58211,53458,0,18,138,53233,'DropShip_BPartner_ID',TO_TIMESTAMP('2009-09-06 23:44:23','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE03',10,'If empty the business partner will be shipped to.','Y','N','N','N','N','N','N','N','Y','N','Y','Drop Shipment Partner',TO_TIMESTAMP('2009-09-06 23:44:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58211 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DropShip_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DropShip_Location_ID', Description='Business Partner Location for shipping to', EntityType='D', Help=NULL, IsActive='Y', Name='Drop Shipment Location', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Drop Shipment Location',Updated=TO_TIMESTAMP('2009-09-06 23:44:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53459
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53459
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Locations of a Business Partner', EntityType='D', Help=NULL, IsActive='Y', Name='C_BPartner Location', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:44:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=159
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=159
;

-- Sep 6, 2009 11:44:27 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 293, AD_Display = 2960, AD_Key = 3434, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 159
;

-- Sep 6, 2009 11:44:28 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='C_BPartner_Location.C_BPartner_ID=@DropShip_BPartner_ID@ AND C_BPartner_Location.IsShipTo=''Y''', Description=NULL, EntityType='D', IsActive='Y', Name='C_BPartner_Loc - Drop Ship To', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:44:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=120
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58212,53459,0,18,159,53233,120,'DropShip_Location_ID',TO_TIMESTAMP('2009-09-06 23:44:28','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE03',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Drop Shipment Location',TO_TIMESTAMP('2009-09-06 23:44:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58212 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DropShip_Location_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='DropShip_User_ID', Description='Business Partner Contact for drop shipment', EntityType='D', Help=NULL, IsActive='Y', Name='Drop Shipment Contact', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Drop Shipment Contact',Updated=TO_TIMESTAMP('2009-09-06 23:44:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53460
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53460
;

-- Sep 6, 2009 11:44:31 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='AD_User.C_BPartner_ID=@DropShip_BPartner_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='AD_User BPartner Drop', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:44:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=168
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58213,53460,0,18,110,53233,168,'DropShip_User_ID',TO_TIMESTAMP('2009-09-06 23:44:31','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE03',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Drop Shipment Contact',TO_TIMESTAMP('2009-09-06 23:44:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58213 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN DropShip_User_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='FreightAmt', Description='Freight Amount ', EntityType='D', Help='The Freight Amount indicates the amount charged for Freight in the document currency.', IsActive='Y', Name='Freight Amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Freight Amt',Updated=TO_TIMESTAMP('2009-09-06 23:44:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=306
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=306
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:44:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=12
;

-- Sep 6, 2009 11:44:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58214,306,0,12,53233,'FreightAmt',TO_TIMESTAMP('2009-09-06 23:44:34','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE03',22,'The Freight Amount indicates the amount charged for Freight in the document currency.','Y','N','N','N','N','N','N','N','Y','N','Y','Freight Amount',TO_TIMESTAMP('2009-09-06 23:44:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58214 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN FreightAmt NUMERIC DEFAULT NULL 
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='FreightCostRule', Description='Method for charging Freight', EntityType='D', Help='The Freight Cost Rule indicates the method used when charging for freight.', IsActive='Y', Name='Freight Cost Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Freight Cost Rule',Updated=TO_TIMESTAMP('2009-09-06 23:44:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1007
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1007
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Freight cost Rule list', EntityType='D', Help=NULL, IsActive='Y', Name='C_Order FreightCostRule', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:44:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=153
;

-- Sep 6, 2009 11:44:37 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=153
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58215,1007,0,17,153,53233,'FreightCostRule',TO_TIMESTAMP('2009-09-06 23:44:38','YYYY-MM-DD HH24:MI:SS'),0,'I','Method for charging Freight','EE03',1,'The Freight Cost Rule indicates the method used when charging for freight.','Y','N','N','N','N','Y','N','N','Y','N','Y','Freight Cost Rule',TO_TIMESTAMP('2009-09-06 23:44:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58215 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN FreightCostRule CHAR(1) DEFAULT 'I' NOT NULL
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2009-09-06 23:44:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:44:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsApproved', Description='Indicates if this document requires approval', EntityType='D', Help='The Approved checkbox indicates if this document requires approval before it can be processed.', IsActive='Y', Name='Approved', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Approved',Updated=TO_TIMESTAMP('2009-09-06 23:44:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=351
;

-- Sep 6, 2009 11:44:41 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=351
;

-- Sep 6, 2009 11:44:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58216,351,0,20,53233,'IsApproved',TO_TIMESTAMP('2009-09-06 23:44:41','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE03',1,'The Approved checkbox indicates if this document requires approval before it can be processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Approved',TO_TIMESTAMP('2009-09-06 23:44:41','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58216 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:44 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN IsApproved CHAR(1) CHECK (IsApproved IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:44:44 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsDropShip', Description='Drop Shipments are sent from the Vendor directly to the Customer', EntityType='D', Help='Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.', IsActive='Y', Name='Drop Shipment', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Drop Ship',Updated=TO_TIMESTAMP('2009-09-06 23:44:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2466
;

-- Sep 6, 2009 11:44:44 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2466
;

-- Sep 6, 2009 11:44:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58217,2466,0,20,53233,'IsDropShip',TO_TIMESTAMP('2009-09-06 23:44:44','YYYY-MM-DD HH24:MI:SS'),0,'N','Drop Shipments are sent from the Vendor directly to the Customer','EE03',1,'Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','N','N','N','N','N','N','N','Y','N','Y','Drop Shipment',TO_TIMESTAMP('2009-09-06 23:44:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:44:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58217 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:47 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN IsDropShip CHAR(1) DEFAULT 'N' CHECK (IsDropShip IN ('Y','N'))
;

-- Sep 6, 2009 11:44:47 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsInTransit', Description='Movement is in transit', EntityType='D', Help='Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.', IsActive='Y', Name='In Transit', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='In Transit',Updated=TO_TIMESTAMP('2009-09-06 23:44:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2397
;

-- Sep 6, 2009 11:44:47 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2397
;

-- Sep 6, 2009 11:44:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58218,2397,0,20,53233,'IsInTransit',TO_TIMESTAMP('2009-09-06 23:44:47','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit','EE03',1,'Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','N','N','N','N','Y','N','N','Y','N','Y','In Transit',TO_TIMESTAMP('2009-09-06 23:44:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58218 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:50 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN IsInTransit CHAR(1) CHECK (IsInTransit IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:44:51 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsPrinted', Description='Indicates if this document / line is printed', EntityType='D', Help='The Printed checkbox indicates if this document or line will included when printing.', IsActive='Y', Name='Printed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Printed',Updated=TO_TIMESTAMP('2009-09-06 23:44:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=399
;

-- Sep 6, 2009 11:44:51 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=399
;

-- Sep 6, 2009 11:44:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58219,399,0,20,53233,'IsPrinted',TO_TIMESTAMP('2009-09-06 23:44:51','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE03',1,'The Printed checkbox indicates if this document or line will included when printing.','Y','N','N','N','N','Y','N','N','Y','N','Y','Printed',TO_TIMESTAMP('2009-09-06 23:44:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58219 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:54 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN IsPrinted CHAR(1) CHECK (IsPrinted IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:44:54 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsSOTrx', Description='This is a Sales Transaction', EntityType='D', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', IsActive='Y', Name='Sales Transaction', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sales Transaction',Updated=TO_TIMESTAMP('2009-09-06 23:44:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1106
;

-- Sep 6, 2009 11:44:54 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1106
;

-- Sep 6, 2009 11:44:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58220,1106,0,20,53233,'IsSOTrx',TO_TIMESTAMP('2009-09-06 23:44:54','YYYY-MM-DD HH24:MI:SS'),0,'@IsSOTrx@','This is a Sales Transaction','EE03',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','N','N','N','N','Y','N','N','Y','N','Y','Sales Transaction',TO_TIMESTAMP('2009-09-06 23:44:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:44:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58220 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:44:57 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN IsSOTrx CHAR(1) CHECK (IsSOTrx IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:44:57 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_Shipper_ID', Description='Method or manner of product delivery', EntityType='D', Help='The Shipper indicates the method of delivering product', IsActive='Y', Name='Shipper', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Shipper',Updated=TO_TIMESTAMP('2009-09-06 23:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=455
;

-- Sep 6, 2009 11:44:57 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=455
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58221,455,0,19,53233,'M_Shipper_ID',TO_TIMESTAMP('2009-09-06 23:44:57','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE03',22,'The Shipper indicates the method of delivering product','Y','N','N','N','N','N','N','N','Y','N','Y','Shipper',TO_TIMESTAMP('2009-09-06 23:44:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58221 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN M_Shipper_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_TIMESTAMP('2009-09-06 23:45:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=459
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Sep 6, 2009 11:45:00 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='M_Warehouse.AD_Org_ID=@AD_Org_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='M_Warehouse Org', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:45:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=189
;

-- Sep 6, 2009 11:45:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58222,459,0,19,53233,189,'org.compiere.model.CalloutInOut.warehouse','M_Warehouse_ID',TO_TIMESTAMP('2009-09-06 23:45:00','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','Y','N','N','Y','N','N','Warehouse',TO_TIMESTAMP('2009-09-06 23:45:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58222 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:03 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN M_Warehouse_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:45:03 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='POReference', Description='Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner', EntityType='D', Help='The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.', IsActive='Y', Name='Order Reference', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Reference',Updated=TO_TIMESTAMP('2009-09-06 23:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=952
;

-- Sep 6, 2009 11:45:03 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=952
;

-- Sep 6, 2009 11:45:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58223,952,0,10,53233,'POReference',TO_TIMESTAMP('2009-09-06 23:45:03','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE03',20,'The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','N','N','N','N','N','N','N','Y','N','Y','Order Reference',TO_TIMESTAMP('2009-09-06 23:45:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58223 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:06 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN POReference VARCHAR(20) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:06 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='PickDate', Description='Date/Time when picked for Shipment', EntityType='D', Help=NULL, IsActive='Y', Name='Pick Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Pick Date',Updated=TO_TIMESTAMP('2009-09-06 23:45:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2117
;

-- Sep 6, 2009 11:45:06 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2117
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58224,2117,0,16,53233,'PickDate',TO_TIMESTAMP('2009-09-06 23:45:06','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03',7,'Y','N','N','N','N','N','N','N','Y','N','Y','Pick Date',TO_TIMESTAMP('2009-09-06 23:45:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58224 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN PickDate TIMESTAMP DEFAULT NULL 
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='PriorityRule', Description='Priority of a document', EntityType='D', Help='The Priority indicates the importance (high, medium, low) of this document', IsActive='Y', Name='Priority', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Priority',Updated=TO_TIMESTAMP('2009-09-06 23:45:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=522
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=522
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Priority scale list', EntityType='D', Help=NULL, IsActive='Y', Name='_PriorityRule', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-06 23:45:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=154
;

-- Sep 6, 2009 11:45:10 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=154
;

-- Sep 6, 2009 11:45:11 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=154, Description=NULL, EntityType='D', IsActive='Y', Name='Urgent', Value='1',Updated=TO_TIMESTAMP('2009-09-06 23:45:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=759
;

-- Sep 6, 2009 11:45:11 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=759
;

-- Sep 6, 2009 11:45:11 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List SET AD_Reference_ID=154, Description=NULL, EntityType='D', IsActive='Y', Name='Minor', Value='9',Updated=TO_TIMESTAMP('2009-09-06 23:45:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=760
;

-- Sep 6, 2009 11:45:11 PM ECT
-- Warehouse Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=760
;

-- Sep 6, 2009 11:45:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58225,522,0,17,154,53233,'PriorityRule',TO_TIMESTAMP('2009-09-06 23:45:11','YYYY-MM-DD HH24:MI:SS'),0,'5','Priority of a document','EE03',1,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','Y','N','N','Y','N','Y','Priority',TO_TIMESTAMP('2009-09-06 23:45:11','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58225 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:14 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN PriorityRule CHAR(1) DEFAULT '5' NOT NULL
;

-- Sep 6, 2009 11:45:14 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2009-09-06 23:45:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1047
;

-- Sep 6, 2009 11:45:14 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Sep 6, 2009 11:45:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58226,1047,0,20,53233,'Processed',TO_TIMESTAMP('2009-09-06 23:45:14','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_TIMESTAMP('2009-09-06 23:45:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58226 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:17 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN Processed CHAR(1) CHECK (Processed IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:45:17 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2009-09-06 23:45:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Sep 6, 2009 11:45:17 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58227,524,0,28,53233,'Processing',TO_TIMESTAMP('2009-09-06 23:45:17','YYYY-MM-DD HH24:MI:SS'),0,'EE03',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2009-09-06 23:45:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58227 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Sales Representative', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User - SalesRep', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:45:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=190
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=190
;

-- Sep 6, 2009 11:45:21 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'EXISTS (SELECT * FROM C_BPartner bp WHERE AD_User.C_BPartner_ID=bp.C_BPartner_ID AND bp.IsSalesRep=''Y'')
' WHERE AD_Reference_ID = 190
;

-- Sep 6, 2009 11:45:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58228,1063,0,18,190,53233,'SalesRep_ID',TO_TIMESTAMP('2009-09-06 23:45:21','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE03',22,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','Y','Sales Representative',TO_TIMESTAMP('2009-09-06 23:45:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58228 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:25 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN SalesRep_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:25 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='SendEMail', Description='Enable sending Document EMail', EntityType='D', Help='Send emails with document attached (e.g. Invoice, Delivery Note, etc.)', IsActive='Y', Name='Send EMail', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Send EMail',Updated=TO_TIMESTAMP('2009-09-06 23:45:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1978
;

-- Sep 6, 2009 11:45:25 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1978
;

-- Sep 6, 2009 11:45:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58229,1978,0,20,53233,'SendEMail',TO_TIMESTAMP('2009-09-06 23:45:25','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE03',1,'Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','N','N','N','N','Y','N','N','Y','N','Y','Send EMail',TO_TIMESTAMP('2009-09-06 23:45:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58229 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:27 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN SendEMail CHAR(1) CHECK (SendEMail IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:45:27 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='ShipDate', Description='Shipment Date/Time', EntityType='D', Help='Actual Date/Time of Shipment (pick up)', IsActive='Y', Name='Ship Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Ship Date',Updated=TO_TIMESTAMP('2009-09-06 23:45:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2123
;

-- Sep 6, 2009 11:45:27 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2123
;

-- Sep 6, 2009 11:45:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58230,2123,0,16,53233,'ShipDate',TO_TIMESTAMP('2009-09-06 23:45:27','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03',7,'Actual Date/Time of Shipment (pick up)','Y','N','N','N','N','N','N','N','Y','N','Y','Ship Date',TO_TIMESTAMP('2009-09-06 23:45:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58230 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:30 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN ShipDate TIMESTAMP DEFAULT NULL 
;

-- Sep 6, 2009 11:45:30 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='TrackingNo', Description='Number to track the shipment', EntityType='D', Help=NULL, IsActive='Y', Name='Tracking No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Tracking No',Updated=TO_TIMESTAMP('2009-09-06 23:45:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2126
;

-- Sep 6, 2009 11:45:30 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2126
;

-- Sep 6, 2009 11:45:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58231,2126,0,10,53233,'TrackingNo',TO_TIMESTAMP('2009-09-06 23:45:30','YYYY-MM-DD HH24:MI:SS'),0,'Number to track the shipment','EE03',60,'Y','Y','N','N','N','N','N','N','Y','N','Y','Tracking No',TO_TIMESTAMP('2009-09-06 23:45:30','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58231 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:33 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN TrackingNo VARCHAR(60) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:33 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2009-09-06 23:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Sep 6, 2009 11:45:33 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2009-09-06 23:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='User1_ID', Description='User defined list element #1', EntityType='D', Help='The user defined element displays the optional elements that have been defined for this account combination.', IsActive='Y', Name='User List 1', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='User 1',Updated=TO_TIMESTAMP('2009-09-06 23:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=613
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=613
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='User1 selection based on Client', EntityType='D', Help=NULL, IsActive='Y', Name='Account_ID - User1', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=134
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=134
;

-- Sep 6, 2009 11:45:34 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 188, AD_Display = 1135, AD_Key = 1125, isValueDisplayed = 'Y', OrderByClause = 'C_ElementValue.Value', EntityType ='D', WhereClause = 'C_ElementValue.IsActive=''Y'' AND C_ElementValue.IsSummary=''N'' 
AND C_ElementValue.C_Element_ID IN (SELECT C_Element_ID FROM C_AcctSchema_Element ase WHERE ase.ElementType=''U1'' AND ase.AD_Client_ID=@AD_Client_ID@)' WHERE AD_Reference_ID = 134
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58232,613,0,18,134,53233,'User1_ID',TO_TIMESTAMP('2009-09-06 23:45:34','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03',22,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 1',TO_TIMESTAMP('2009-09-06 23:45:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58232 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN User1_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='User2_ID', Description='User defined list element #2', EntityType='D', Help='The user defined element displays the optional elements that have been defined for this account combination.', IsActive='Y', Name='User List 2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='User 2',Updated=TO_TIMESTAMP('2009-09-06 23:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=614
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=614
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='User2 selection based on Client', EntityType='D', Help=NULL, IsActive='Y', Name='Account_ID - User2', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=137
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=137
;

-- Sep 6, 2009 11:45:37 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 188, AD_Display = 1135, AD_Key = 1125, isValueDisplayed = 'Y', OrderByClause = 'C_ElementValue.Value', EntityType ='D', WhereClause = 'C_ElementValue.IsActive=''Y'' AND C_ElementValue.IsSummary=''N'' 
AND C_ElementValue.C_Element_ID IN (SELECT C_Element_ID FROM C_AcctSchema_Element ase WHERE ase.ElementType=''U2'' AND ase.AD_Client_ID=@AD_Client_ID@)' WHERE AD_Reference_ID = 137
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58233,614,0,18,137,53233,'User2_ID',TO_TIMESTAMP('2009-09-06 23:45:37','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03',22,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 2',TO_TIMESTAMP('2009-09-06 23:45:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58233 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN User2_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Volume', Description='Volume of a product', EntityType='D', Help='The Volume indicates the volume of the product in the Volume UOM of the Client', IsActive='Y', Name='Volume', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Volume',Updated=TO_TIMESTAMP('2009-09-06 23:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=627
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=627
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Sep 6, 2009 11:45:40 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Sep 6, 2009 11:45:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58234,627,0,22,53233,'Volume',TO_TIMESTAMP('2009-09-06 23:45:40','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE03',22,'The Volume indicates the volume of the product in the Volume UOM of the Client','Y','N','N','N','N','N','N','N','Y','N','Y','Volume',TO_TIMESTAMP('2009-09-06 23:45:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:45:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58234 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:43 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN Volume NUMERIC DEFAULT NULL 
;

-- Sep 6, 2009 11:45:43 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Weight', Description='Weight of a product', EntityType='D', Help='The Weight indicates the weight  of the product in the Weight UOM of the Client', IsActive='Y', Name='Weight', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Weight',Updated=TO_TIMESTAMP('2009-09-06 23:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=629
;

-- Sep 6, 2009 11:45:43 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=629
;

-- Sep 6, 2009 11:45:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58235,629,0,22,53233,'Weight',TO_TIMESTAMP('2009-09-06 23:45:43','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE03',22,'The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','N','N','N','N','N','N','N','Y','N','Y','Weight',TO_TIMESTAMP('2009-09-06 23:45:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:45:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58235 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:45:46 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBound ADD COLUMN Weight NUMERIC DEFAULT NULL 
;

-- Sep 6, 2009 11:45:49 PM ECT
-- Warehouse Management
INSERT INTO AD_FieldGroup (AD_Client_ID,AD_FieldGroup_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,50013,0,TO_TIMESTAMP('2009-09-06 23:45:46','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Delivery Rules',TO_TIMESTAMP('2009-09-06 23:45:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:45:49 PM ECT
-- Warehouse Management
INSERT INTO AD_FieldGroup_Trl (AD_Language,AD_FieldGroup_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_FieldGroup_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_FieldGroup t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_FieldGroup_ID=50013 AND EXISTS (SELECT * FROM AD_FieldGroup_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_FieldGroup_ID!=t.AD_FieldGroup_ID)
;

-- Sep 6, 2009 11:45:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53183,'3',TO_TIMESTAMP('2009-09-06 23:45:49','YYYY-MM-DD HH24:MI:SS'),0,'** Special ** Do not change **','EE03','Y','N','Y','Y','Picking Order Print','Y',0,0,TO_TIMESTAMP('2009-09-06 23:45:49','YYYY-MM-DD HH24:MI:SS'),0,'Rpt M_InOutBound',NULL)
;

-- Sep 6, 2009 11:45:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53183 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2009 11:45:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53234,'3',TO_TIMESTAMP('2009-09-06 23:45:52','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Order Line','EE03','N','Y','N','Y','N','N','N','Inbound & Outbound Order Line','L','WM_InOutBoundLine',TO_TIMESTAMP('2009-09-06 23:45:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:45:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53234 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:45:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53342,TO_TIMESTAMP('2009-09-06 23:45:55','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_InOutBoundLine',1,'Y','N','Y','Y','WM_InOutBoundLine','N',1000000,TO_TIMESTAMP('2009-09-06 23:45:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:46:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58236,102,0,19,53234,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:45:57','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:45:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58236 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58237,113,0,19,53234,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:46:00','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:46:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58237 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58238,348,0,20,53234,'IsActive',TO_TIMESTAMP('2009-09-06 23:46:02','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:46:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58238 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58239,245,0,16,53234,'Created',TO_TIMESTAMP('2009-09-06 23:46:05','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:46:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58239 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58240,607,0,16,53234,'Updated',TO_TIMESTAMP('2009-09-06 23:46:07','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:46:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58240 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58241,246,0,19,110,53234,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:46:10','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:46:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58241 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58242,608,0,19,110,53234,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:46:12','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:46:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58242 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53913,0,'WM_InOutBoundLine_ID',TO_TIMESTAMP('2009-09-06 23:46:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & Outbound Order Line ID','Inbound & Outbound Order Line ID',TO_TIMESTAMP('2009-09-06 23:46:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:46:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53913 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:46:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58243,53913,0,13,53234,'WM_InOutBoundLine_ID',TO_TIMESTAMP('2009-09-06 23:46:18','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Inbound & Outbound Order Line ID',TO_TIMESTAMP('2009-09-06 23:46:18','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58243 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:22 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_InOutBoundLine_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Inbound & Outbound Order Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inbound & Outbound Order Line',Updated=TO_TIMESTAMP('2009-09-06 23:46:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53913
;

-- Sep 6, 2009 11:46:22 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53913
;

-- Sep 6, 2009 11:46:22 PM ECT
-- Warehouse Management
CREATE TABLE WM_InOutBoundLine (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_InOutBoundLine_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_InOutBoundLine_Key PRIMARY KEY (WM_InOutBoundLine_ID))
;

-- Sep 6, 2009 11:46:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58244,112,0,18,130,53234,'AD_OrgTrx_ID',TO_TIMESTAMP('2009-09-06 23:46:25','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE03',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','Y','N','Y','Trx Organization',TO_TIMESTAMP('2009-09-06 23:46:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58244 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:27 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN AD_OrgTrx_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:27 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='C_Activity.IsActive=''Y'' AND C_Activity.IsSummary=''N''', Description=NULL, EntityType='D', IsActive='Y', Name='C_Activity (Trx)', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:46:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=235
;

-- Sep 6, 2009 11:46:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58245,1005,0,19,53234,235,'C_Activity_ID',TO_TIMESTAMP('2009-09-06 23:46:27','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE03',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','Y','N','Y','Activity',TO_TIMESTAMP('2009-09-06 23:46:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58245 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:30 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_Activity_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:30 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='C_Campaign.IsActive=''Y'' AND C_Campaign.IsSummary=''N''', Description=NULL, EntityType='D', IsActive='Y', Name='C_Campaign (Trx)', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:46:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=236
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58246,550,0,19,53234,236,'C_Campaign_ID',TO_TIMESTAMP('2009-09-06 23:46:30','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE03',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','Y','N','Y','Campaign',TO_TIMESTAMP('2009-09-06 23:46:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58246 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_Campaign_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_Charge_ID', Description='Additional document charges', EntityType='D', Help='The Charge indicates a type of Charge (Handling, Shipping, Restocking)', IsActive='Y', Name='Charge', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Charge',Updated=TO_TIMESTAMP('2009-09-06 23:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=968
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=968
;

-- Sep 6, 2009 11:46:33 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='(C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocType_ID@
	) OR
 (SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)', Description=NULL, EntityType='D', IsActive='Y', Name='C_Charge by Doc Type', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52029
;

-- Sep 6, 2009 11:46:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58247,968,0,19,53234,52029,'C_Charge_ID',TO_TIMESTAMP('2009-09-06 23:46:33','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE03',10,'The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','N','N','N','N','N','N','N','Y','N','Y','Charge',TO_TIMESTAMP('2009-09-06 23:46:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58247 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:35 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_Charge_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:36 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='C_OrderLine.C_Order_ID=@C_Order_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_OrderLine of Order', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=203
;

-- Sep 6, 2009 11:46:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58248,561,0,19,53234,203,'org.compiere.model.CalloutInOut.orderLine','C_OrderLine_ID',TO_TIMESTAMP('2009-09-06 23:46:36','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE03',22,'The Sales Order Line is a unique identifier for a line in an order.','Y','N','N','N','N','N','N','N','Y','N','N','Sales Order Line',TO_TIMESTAMP('2009-09-06 23:46:36','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58248 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:38 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_OrderLine_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:38 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_ProjectPhase_ID', Description='Phase of a Project', EntityType='D', Help=NULL, IsActive='Y', Name='Project Phase', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project Phase',Updated=TO_TIMESTAMP('2009-09-06 23:46:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2073
;

-- Sep 6, 2009 11:46:38 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2073
;

-- Sep 6, 2009 11:46:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58249,2073,0,19,53234,262,'C_ProjectPhase_ID',TO_TIMESTAMP('2009-09-06 23:46:38','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE03',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Project Phase',TO_TIMESTAMP('2009-09-06 23:46:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58249 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:41 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_ProjectPhase_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:41 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_ProjectTask_ID', Description='Actual Project Task in a Phase', EntityType='D', Help='A Project Task in a Project Phase represents the actual work.', IsActive='Y', Name='Project Task', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project Task',Updated=TO_TIMESTAMP('2009-09-06 23:46:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2074
;

-- Sep 6, 2009 11:46:41 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2074
;

-- Sep 6, 2009 11:46:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58250,2074,0,19,53234,263,'C_ProjectTask_ID',TO_TIMESTAMP('2009-09-06 23:46:42','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE03',10,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','Y','N','Y','Project Task',TO_TIMESTAMP('2009-09-06 23:46:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58250 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:44 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_ProjectTask_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:44 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_Project_ID', Description='Financial Project', EntityType='D', Help='A Project allows you to track and control internal or external activities.', IsActive='Y', Name='Project', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project',Updated=TO_TIMESTAMP('2009-09-06 23:46:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=208
;

-- Sep 6, 2009 11:46:44 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=208
;

-- Sep 6, 2009 11:46:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58251,208,0,19,53234,227,'C_Project_ID',TO_TIMESTAMP('2009-09-06 23:46:44','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE03',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','Y','N','Y','Project',TO_TIMESTAMP('2009-09-06 23:46:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:46:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58251 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:47 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_Project_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:47 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_TIMESTAMP('2009-09-06 23:46:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=215
;

-- Sep 6, 2009 11:46:47 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Sep 6, 2009 11:46:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58252,215,0,19,53234,'org.compiere.model.CalloutInOut.qty','C_UOM_ID',TO_TIMESTAMP('2009-09-06 23:46:47','YYYY-MM-DD HH24:MI:SS'),0,'@#C_UOM_ID@','Unit of Measure','EE03',22,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','Y','N','N','Y','N','N','UOM',TO_TIMESTAMP('2009-09-06 23:46:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58252 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:50 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN C_UOM_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:46:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58253,275,0,14,53234,'Description',TO_TIMESTAMP('2009-09-06 23:46:50','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:46:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:52 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58253 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:53 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:46:53 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='IsDescription', Description='if true, the line is just description and no transaction', EntityType='D', Help='If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.', IsActive='Y', Name='Description Only', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2009-09-06 23:46:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2183
;

-- Sep 6, 2009 11:46:53 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2183
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58254,2183,0,20,53234,'IsDescription',TO_TIMESTAMP('2009-09-06 23:46:53','YYYY-MM-DD HH24:MI:SS'),0,'N','if true, the line is just description and no transaction','EE03',1,'If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','N','N','N','N','Y','N','N','Y','N','Y','Description Only',TO_TIMESTAMP('2009-09-06 23:46:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58254 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN IsDescription CHAR(1) DEFAULT 'N' CHECK (IsDescription IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Line', Description='Unique line for this document', EntityType='D', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', Name='Line No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Line No',Updated=TO_TIMESTAMP('2009-09-06 23:46:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=439
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=439
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:46:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Sep 6, 2009 11:46:55 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58255,439,0,11,53234,'Line',TO_TIMESTAMP('2009-09-06 23:46:55','YYYY-MM-DD HH24:MI:SS'),0,'@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_InOutLine WHERE M_InOut_ID=@M_InOut_ID@','Unique line for this document','EE03',22,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Line No',1,TO_TIMESTAMP('2009-09-06 23:46:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58255 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN Line NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_AttributeSetInstance_ID', Description='Product Attribute Set Instance', EntityType='D', Help='The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.', IsActive='Y', Name='Attribute Set Instance', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Attribute Set Instance',Updated=TO_TIMESTAMP('2009-09-06 23:46:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2019
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2019
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Product Attribute', EntityType='D', Help=NULL, IsActive='Y', Name='Product Attribute', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:46:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=35
;

-- Sep 6, 2009 11:46:58 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=35
;

-- Sep 6, 2009 11:47:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58256,2019,0,35,53234,'org.compiere.model.CalloutInOut.asi','M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-09-06 23:46:58','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE03',22,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','Y','N','Y','Attribute Set Instance',TO_TIMESTAMP('2009-09-06 23:46:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58256 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:01 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN M_AttributeSetInstance_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:47:01 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:47:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Sep 6, 2009 11:47:01 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58257,53912,0,30,53234,'WM_InOutBound_ID',TO_TIMESTAMP('2009-09-06 23:47:01','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','Y','N','Y','Y','N','Y','N','N','Inbound & Outbound Order',4,TO_TIMESTAMP('2009-09-06 23:47:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58257 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN WM_InOutBound_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2009-09-06 23:47:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=454
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Sep 6, 2009 11:47:04 PM ECT
-- Warehouse Management
UPDATE AD_Val_Rule SET Code='M_Product.IsSummary=''N'' AND M_Product.IsActive=''Y''', Description=NULL, EntityType='D', IsActive='Y', Name='M_Product (Trx)', Type='S',Updated=TO_TIMESTAMP('2009-09-06 23:47:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=231
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58258,454,0,30,53234,231,'org.compiere.model.CalloutInOut.product','M_Product_ID',TO_TIMESTAMP('2009-09-06 23:47:04','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','Y','N','N','N','N','Y','N','Y','Product',3,TO_TIMESTAMP('2009-09-06 23:47:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58258 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='MovementQty', Description='Quantity of a product moved.', EntityType='D', Help='The Movement Quantity indicates the quantity of a product that has been moved.', IsActive='Y', Name='Movement Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_TIMESTAMP('2009-09-06 23:47:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1038
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1038
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:47:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Sep 6, 2009 11:47:07 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Sep 6, 2009 11:47:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58259,1038,0,29,53234,'org.compiere.model.CalloutInOut.qty','MovementQty',TO_TIMESTAMP('2009-09-06 23:47:07','YYYY-MM-DD HH24:MI:SS'),0,'1','Quantity of a product moved.','EE03',22,'The Movement Quantity indicates the quantity of a product that has been moved.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Movement Quantity',2,TO_TIMESTAMP('2009-09-06 23:47:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58259 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:09 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN MovementQty NUMERIC DEFAULT '1' NOT NULL
;

-- Sep 6, 2009 11:47:09 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='PickedQty', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Picked Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Picked Quantity',Updated=TO_TIMESTAMP('2009-09-06 23:47:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2422
;

-- Sep 6, 2009 11:47:09 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2422
;

-- Sep 6, 2009 11:47:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58260,2422,0,29,53234,'PickedQty',TO_TIMESTAMP('2009-09-06 23:47:09','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Picked Quantity',TO_TIMESTAMP('2009-09-06 23:47:09','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58260 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:12 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN PickedQty NUMERIC DEFAULT NULL 
;

-- Sep 6, 2009 11:47:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58261,1047,0,20,53234,'Processed',TO_TIMESTAMP('2009-09-06 23:47:12','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE03',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_TIMESTAMP('2009-09-06 23:47:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:47:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58261 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:14 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN Processed CHAR(1) CHECK (Processed IN ('Y','N')) NOT NULL
;

-- Sep 6, 2009 11:47:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58262,613,0,18,134,53234,'User1_ID',TO_TIMESTAMP('2009-09-06 23:47:15','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE03',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 1',TO_TIMESTAMP('2009-09-06 23:47:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:47:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58262 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:17 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN User1_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:47:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58263,614,0,18,137,53234,'User2_ID',TO_TIMESTAMP('2009-09-06 23:47:17','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE03',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 2',TO_TIMESTAMP('2009-09-06 23:47:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:47:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58263 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:20 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN User2_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:47:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58264,2117,0,16,53234,'PickDate',TO_TIMESTAMP('2009-09-06 23:47:20','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment','EE03',7,'Y','N','N','N','N','N','N','N','Y','N','Y','Pick Date',TO_TIMESTAMP('2009-09-06 23:47:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:47:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58264 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:22 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN PickDate TIMESTAMP DEFAULT NULL 
;

-- Sep 6, 2009 11:47:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58265,2123,0,16,53234,'ShipDate',TO_TIMESTAMP('2009-09-06 23:47:22','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time','EE03',7,'Actual Date/Time of Shipment (pick up)','Y','N','N','N','N','N','N','N','Y','N','Y','Ship Date',TO_TIMESTAMP('2009-09-06 23:47:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:47:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58265 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:47:24 PM ECT
-- Warehouse Management
ALTER TABLE WM_InOutBoundLine ADD COLUMN ShipDate TIMESTAMP DEFAULT NULL 
;

-- Sep 6, 2009 11:47:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53258,53234,53090,NULL,TO_TIMESTAMP('2009-09-06 23:47:24','YYYY-MM-DD HH24:MI:SS'),0,'EE03','N','Y','N','N','Y','N','N','N','N','OutBound Order Line','N',20,1,TO_TIMESTAMP('2009-09-06 23:47:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53258 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:47:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58236,57536,0,53258,TO_TIMESTAMP('2009-09-06 23:47:27','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_TIMESTAMP('2009-09-06 23:47:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57536 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58237,57537,0,53258,TO_TIMESTAMP('2009-09-06 23:47:30','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Organization',0,0,TO_TIMESTAMP('2009-09-06 23:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57537 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58238,57538,0,53258,TO_TIMESTAMP('2009-09-06 23:47:33','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Active',0,0,TO_TIMESTAMP('2009-09-06 23:47:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57538 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58250,57539,0,53258,TO_TIMESTAMP('2009-09-06 23:47:36','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase',10,'EE01','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','N','Project Task',0,0,TO_TIMESTAMP('2009-09-06 23:47:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57539 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58251,57540,0,53258,TO_TIMESTAMP('2009-09-06 23:47:38','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project',10,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','Project',0,0,TO_TIMESTAMP('2009-09-06 23:47:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57540 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58254,57541,0,53258,TO_TIMESTAMP('2009-09-06 23:47:41','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction',1,'EE01','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','N','Description Only',0,0,TO_TIMESTAMP('2009-09-06 23:47:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57541 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58257,57542,0,53258,TO_TIMESTAMP('2009-09-06 23:47:44','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','N','N','N','N','N','Inbound & Outbound Order',0,0,TO_TIMESTAMP('2009-09-06 23:47:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57542 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58261,57543,0,53258,TO_TIMESTAMP('2009-09-06 23:47:47','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','Processed',0,0,TO_TIMESTAMP('2009-09-06 23:47:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57543 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58262,57544,0,53258,TO_TIMESTAMP('2009-09-06 23:47:50','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1',10,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','User List 1',0,0,TO_TIMESTAMP('2009-09-06 23:47:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57544 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58263,57545,0,53258,TO_TIMESTAMP('2009-09-06 23:47:53','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2',10,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','User List 2',0,0,TO_TIMESTAMP('2009-09-06 23:47:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57545 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:47:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58264,57546,0,53258,TO_TIMESTAMP('2009-09-06 23:47:56','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment',7,'EE03','Y','Y','N','N','N','N','N','Pick Date',0,0,TO_TIMESTAMP('2009-09-06 23:47:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:47:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57546 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58265,57547,0,53258,TO_TIMESTAMP('2009-09-06 23:47:59','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time',7,'EE03','Actual Date/Time of Shipment (pick up)','Y','Y','N','N','N','N','N','Ship Date',0,0,TO_TIMESTAMP('2009-09-06 23:47:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57547 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58244,57548,0,53258,TO_TIMESTAMP('2009-09-06 23:48:01','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',10,'EE01','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','N','Trx Organization',0,0,TO_TIMESTAMP('2009-09-06 23:48:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57548 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58245,57549,0,53258,TO_TIMESTAMP('2009-09-06 23:48:04','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity',10,'EE01','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','N','Activity',0,0,TO_TIMESTAMP('2009-09-06 23:48:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57549 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58246,57550,0,53258,TO_TIMESTAMP('2009-09-06 23:48:07','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign',10,'EE01','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','Campaign',0,0,TO_TIMESTAMP('2009-09-06 23:48:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57550 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58247,57551,0,53258,TO_TIMESTAMP('2009-09-06 23:48:10','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges',10,'EE01','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','N','Charge',0,0,TO_TIMESTAMP('2009-09-06 23:48:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57551 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58249,57552,0,53258,TO_TIMESTAMP('2009-09-06 23:48:13','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project',10,'EE01','Y','Y','N','N','N','N','N','Project Phase',0,0,TO_TIMESTAMP('2009-09-06 23:48:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57552 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58255,57553,0,53258,TO_TIMESTAMP('2009-09-06 23:48:17','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document',22,'EE01','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','N','Line No',10,0,TO_TIMESTAMP('2009-09-06 23:48:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57553 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58258,57554,0,53258,TO_TIMESTAMP('2009-09-06 23:48:20','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',22,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','Product',20,0,TO_TIMESTAMP('2009-09-06 23:48:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57554 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58252,57555,0,53258,TO_TIMESTAMP('2009-09-06 23:48:23','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure',22,'EE01','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','Y','UOM',30,0,TO_TIMESTAMP('2009-09-06 23:48:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57555 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58256,57556,0,53258,TO_TIMESTAMP('2009-09-06 23:48:26','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance',22,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','Attribute Set Instance',40,0,TO_TIMESTAMP('2009-09-06 23:48:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57556 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58259,57557,0,53258,TO_TIMESTAMP('2009-09-06 23:48:29','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.',22,'EE03','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Y','Y','N','N','N','N','Movement Quantity',50,0,TO_TIMESTAMP('2009-09-06 23:48:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57557 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58260,57558,0,53258,TO_TIMESTAMP('2009-09-06 23:48:32','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','Y','Picked Quantity',60,0,TO_TIMESTAMP('2009-09-06 23:48:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57558 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58253,57559,0,53258,TO_TIMESTAMP('2009-09-06 23:48:35','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',70,0,TO_TIMESTAMP('2009-09-06 23:48:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57559 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58248,57560,0,53258,TO_TIMESTAMP('2009-09-06 23:48:38','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line',22,'EE01','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','Y','N','N','N','N','Sales Order Line',80,0,TO_TIMESTAMP('2009-09-06 23:48:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57560 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58243,57561,0,53258,TO_TIMESTAMP('2009-09-06 23:48:41','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','N','N','N','N','N','Inbound & Outbound Order Line',0,0,TO_TIMESTAMP('2009-09-06 23:48:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57561 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:48:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53184,'3','org.eevolution.process.ReleaseInOutBound',TO_TIMESTAMP('2009-09-06 23:48:44','YYYY-MM-DD HH24:MI:SS'),0,'This process generate the Picking lists in order to prepare for shipping, thus allowing one or serveral distribution orders to be generated as a result of the Inbound/Outbound Strategies previously defined in the Inbound/Outbound Definition window.','EE03','Those Distribuition Orders are generated from different locations based on the Picking rules from the Inbound/Outbound Strategy.

Once the Picking process is completed the process to generate shipments can be fulfilled.','Y','Y','N','N','Release & Print Outbound Order','Y',0,0,TO_TIMESTAMP('2009-09-06 23:48:44','YYYY-MM-DD HH24:MI:SS'),0,'WM_InOutbound Print & Release Picking',NULL)
;

-- Sep 6, 2009 11:48:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53184 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2009 11:48:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53184,53325,20,'IsCreateSupply',TO_TIMESTAMP('2009-09-06 23:48:48','YYYY-MM-DD HH24:MI:SS'),0,'Create supply for product not available','EE03',0,'Allow create Manufacturing Order or Requisition Material for product not available','Y','Y','N','N','Create supply',60,TO_TIMESTAMP('2009-09-06 23:48:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53325 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:48:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53184,53326,20,'IsPrintPickList',TO_TIMESTAMP('2009-09-06 23:48:51','YYYY-MM-DD HH24:MI:SS'),0,'Indicate if a Pick List is print','EE03',0,'When you select this checkbox a Pick list is printed','Y','Y','N','N','Print Pick List',50,TO_TIMESTAMP('2009-09-06 23:48:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53326 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:48:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53184,53327,17,135,'DocAction',TO_TIMESTAMP('2009-09-06 23:48:54','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','Document Action',40,TO_TIMESTAMP('2009-09-06 23:48:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:48:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53327 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:48:58 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Warehouse Locator Data type', EntityType='D', Help=NULL, IsActive='Y', Name='Locator (WH)', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:48:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=31
;

-- Sep 6, 2009 11:48:58 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=31
;

-- Sep 6, 2009 11:49:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,448,0,53184,53328,31,'M_Locator_ID',TO_TIMESTAMP('2009-09-06 23:48:58','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Stanging Locator','EE03',22,'The Locator indicates where in a Warehouse a product is located.','Y','Y','N','N','Stanging Locator',30,TO_TIMESTAMP('2009-09-06 23:48:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53328 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,196,0,53184,53329,19,'C_DocType_ID',TO_TIMESTAMP('2009-09-06 23:49:01','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03',22,'The Document Type determines document sequence and processing rules','Y','Y','N','N','Document Type',20,TO_TIMESTAMP('2009-09-06 23:49:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53329 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,555,0,53184,53330,17,151,'DeliveryRule',TO_TIMESTAMP('2009-09-06 23:49:04','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03',1,'The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','Delivery Rule',10,TO_TIMESTAMP('2009-09-06 23:49:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53330 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53185,'3','org.eevolution.process.GenerateShipmentOutBound',TO_TIMESTAMP('2009-09-06 23:49:06','YYYY-MM-DD HH24:MI:SS'),0,'These process allows to generate multiple shipments for products that were succesfuly picked from an Outbound Order.','EE03','

Once the Picking process is completed the process to generate shipments can be fulfilled.','Y','Y','N','N','Generate Shipments from Outbound Order','Y',0,0,TO_TIMESTAMP('2009-09-06 23:49:06','YYYY-MM-DD HH24:MI:SS'),0,'WM_InOutbound Generate Shipment',NULL)
;

-- Sep 6, 2009 11:49:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53185 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2009 11:49:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53185,53331,17,135,'DocAction',TO_TIMESTAMP('2009-09-06 23:49:09','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE03',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','Document Action',20,TO_TIMESTAMP('2009-09-06 23:49:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53331 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53185,53332,20,'IsIncludeNotAvailable',TO_TIMESTAMP('2009-09-06 23:49:12','YYYY-MM-DD HH24:MI:SS'),0,'The product not available lines are include in the shipment','EE03',0,'Y','Y','N','N','Add product not available lines',10,TO_TIMESTAMP('2009-09-06 23:49:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53332 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53186,'3','org.eevolution.process.GenerateInOutBound',TO_TIMESTAMP('2009-09-06 23:49:15','YYYY-MM-DD HH24:MI:SS'),0,'This process allows to generate a new document as the result of gathering Sales Orders, Manufacture Orders, Distribution Orders based on several criteria such as :','EE03','Warehouse, Order, Document Type, Promised Date range, Ordered Dates, Business Partner, Country, Zip Code, Product and Shipper, these parameters can be configured leveraging the Smart Browse artifact.

Thus, it is required to previously define those parameters to be used in orther to determine wich products are to be included in the Picking Orders, these paraneters are:

*Area Type
*Section type
*Overwrite Delivery Rule
*Generate Picking List
*Print Picking List
*Stand-by Location
*Document Status for the Picking Order','Y','Y','N','N','Generare Outbound Order','Y',0,0,TO_TIMESTAMP('2009-09-06 23:49:15','YYYY-MM-DD HH24:MI:SS'),0,'WM_InOutbound Generate',NULL)
;

-- Sep 6, 2009 11:49:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53186 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2009 11:49:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53914,0,'WM_Section_ID',TO_TIMESTAMP('2009-09-06 23:49:18','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Source Warehouse Section','Warehouse Section',TO_TIMESTAMP('2009-09-06 23:49:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53914 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:49:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53914,0,53186,53333,19,'WM_Section_ID',TO_TIMESTAMP('2009-09-06 23:49:18','YYYY-MM-DD HH24:MI:SS'),0,'The Warehouse Section is an grouping of Locators with similar features.','EE03',22,'The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ','Y','Y','N','N','Source Warehouse Section',15,TO_TIMESTAMP('2009-09-06 23:49:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53333 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53915,0,'WM_Area_ID',TO_TIMESTAMP('2009-09-06 23:49:23','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Source Warehouse Area','Warehouse Area',TO_TIMESTAMP('2009-09-06 23:49:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53915 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:49:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53915,0,53186,53334,19,'WM_Area_ID',TO_TIMESTAMP('2009-09-06 23:49:23','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section','EE03',22,'Y','Y','N','N','Source Warehouse Area',10,TO_TIMESTAMP('2009-09-06 23:49:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53334 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53186,53335,17,135,'DocAction',TO_TIMESTAMP('2009-09-06 23:49:28','YYYY-MM-DD HH24:MI:SS'),0,'PR','The targeted status of the document','EE03',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','Document Action',50,TO_TIMESTAMP('2009-09-06 23:49:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53335 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,196,0,53186,53336,19,'C_DocType_ID',TO_TIMESTAMP('2009-09-06 23:49:31','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE03',22,'The Document Type determines document sequence and processing rules','Y','Y','N','N','Document Type',40,TO_TIMESTAMP('2009-09-06 23:49:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53336 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,555,0,53186,53337,17,151,'DeliveryRule',TO_TIMESTAMP('2009-09-06 23:49:33','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE03',1,'The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','Overview Delivery Rule',30,TO_TIMESTAMP('2009-09-06 23:49:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53337 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,448,0,53186,53338,19,'M_Locator_ID',TO_TIMESTAMP('2009-09-06 23:49:36','YYYY-MM-DD HH24:MI:SS'),0,'The Locator indicates where in a Warehouse a product is located.','EE03',22,'Y','Y','Y','N','Standing Locator',20,TO_TIMESTAMP('2009-09-06 23:49:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53338 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2009 11:49:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53091,TO_TIMESTAMP('2009-09-06 23:49:38','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Structure','EE03','In order to Implement the Warehouse Management functionality you need to configure a logical mapping of your warehouse that accurately matches its physical layout. Warehouses are usually subdivided into Areas, Sections and Locations, therefore it has the ability to handle multiple warehouse areas and each can be further divided into different sections as well.

Finally, warehouse sections can be grouped into different locations and section types.','Y','Y','N','N','Warehouse Structure','N',TO_TIMESTAMP('2009-09-06 23:49:38','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:49:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53091 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:49:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53235,'3',TO_TIMESTAMP('2009-09-06 23:49:40','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section','EE03','N','Y','N','Y','N','N','N','Warehouse Area','L','WM_Area',TO_TIMESTAMP('2009-09-06 23:49:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53235 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:49:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53343,TO_TIMESTAMP('2009-09-06 23:49:42','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Area',1,'Y','N','Y','Y','WM_Area','N',1000000,TO_TIMESTAMP('2009-09-06 23:49:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:49:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58266,102,0,19,53235,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:49:44','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:49:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58266 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:49:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58267,113,0,19,53235,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:49:47','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:49:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:49 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58267 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:49:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58268,348,0,20,53235,'IsActive',TO_TIMESTAMP('2009-09-06 23:49:49','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:49:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58268 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:49:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58269,245,0,16,53235,'Created',TO_TIMESTAMP('2009-09-06 23:49:51','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:49:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58269 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:49:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58270,607,0,16,53235,'Updated',TO_TIMESTAMP('2009-09-06 23:49:53','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:49:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58270 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:49:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58271,246,0,19,110,53235,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:49:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:49:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:49:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58271 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58272,608,0,19,110,53235,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:49:58','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:49:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:50:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58272 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:00 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Area_ID', Description='Warehouse Area allow grouping the Warehouse Section', EntityType='EE03', Help=NULL, IsActive='Y', Name='Warehouse Area', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse Area',Updated=TO_TIMESTAMP('2009-09-06 23:50:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53915
;

-- Sep 6, 2009 11:50:00 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53915
;

-- Sep 6, 2009 11:50:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58273,53915,0,13,53235,'WM_Area_ID',TO_TIMESTAMP('2009-09-06 23:50:00','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section','EE03',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Warehouse Area',TO_TIMESTAMP('2009-09-06 23:50:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:50:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58273 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:03 PM ECT
-- Warehouse Management
CREATE TABLE WM_Area (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Area_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Area_Key PRIMARY KEY (WM_Area_ID))
;

-- Sep 6, 2009 11:50:05 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_TIMESTAMP('2009-09-06 23:50:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- Sep 6, 2009 11:50:05 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Sep 6, 2009 11:50:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58274,469,0,10,53235,'Name',TO_TIMESTAMP('2009-09-06 23:50:05','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:50:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:50:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58274 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:07 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:50:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58275,275,0,10,53235,'Description',TO_TIMESTAMP('2009-09-06 23:50:07','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:50:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:50:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58275 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:10 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:50:10 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Warehouse of Client', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-06 23:50:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=197
;

-- Sep 6, 2009 11:50:10 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=197
;

-- Sep 6, 2009 11:50:10 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET AD_Table_ID = 190, AD_Display = 1152, AD_Key = 1151, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'M_Warehouse.AD_Client_ID=@#AD_Client_ID@' WHERE AD_Reference_ID = 197
;

-- Sep 6, 2009 11:50:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58276,459,0,18,197,53235,'M_Warehouse_ID',TO_TIMESTAMP('2009-09-06 23:50:10','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','Y','Y','N','Y','N','N','Warehouse',TO_TIMESTAMP('2009-09-06 23:50:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:50:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58276 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:12 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area ADD COLUMN M_Warehouse_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:50:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53916,0,'WM_Area_Type_ID',TO_TIMESTAMP('2009-09-06 23:50:12','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type','EE01','Y','Warehouse Area Type','Warehouse Area Type',TO_TIMESTAMP('2009-09-06 23:50:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53916 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:50:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58277,53916,0,19,53235,'WM_Area_Type_ID',TO_TIMESTAMP('2009-09-06 23:50:14','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type','EE03',22,'Y','N','N','N','N','Y','N','N','Y','N','Y','Warehouse Area Type',TO_TIMESTAMP('2009-09-06 23:50:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:50:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58277 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:50:16 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area ADD COLUMN WM_Area_Type_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:50:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53259,53235,53091,NULL,TO_TIMESTAMP('2009-09-06 23:50:16','YYYY-MM-DD HH24:MI:SS'),0,'Define Warehouse Area','EE03','N','A Warehouse Area represents a physical area within the warehouse space, therefore it is required to have at least one to implement the Warehouse Management System.','Y','N','N','Y','N','Y','N','N','Warehouse Area','N',20,1,TO_TIMESTAMP('2009-09-06 23:50:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53259 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:50:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58273,57562,0,53259,TO_TIMESTAMP('2009-09-06 23:50:19','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section',22,'EE03','Y','Y','N','N','N','N','N','Warehouse Area',0,0,TO_TIMESTAMP('2009-09-06 23:50:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57562 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58266,57563,0,53259,TO_TIMESTAMP('2009-09-06 23:50:21','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:50:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57563 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58267,57564,0,53259,TO_TIMESTAMP('2009-09-06 23:50:24','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:50:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57564 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58276,57565,0,53259,TO_TIMESTAMP('2009-09-06 23:50:27','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',22,'EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','Warehouse',30,0,TO_TIMESTAMP('2009-09-06 23:50:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57565 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58274,57566,0,53259,TO_TIMESTAMP('2009-09-06 23:50:30','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2009-09-06 23:50:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57566 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58275,57567,0,53259,TO_TIMESTAMP('2009-09-06 23:50:33','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-06 23:50:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57567 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58268,57568,0,53259,TO_TIMESTAMP('2009-09-06 23:50:36','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2009-09-06 23:50:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57568 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58277,57569,0,53259,TO_TIMESTAMP('2009-09-06 23:50:39','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type',22,'EE03','Y','Y','Y','N','N','N','N','Warehouse Area Type',70,0,TO_TIMESTAMP('2009-09-06 23:50:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57569 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:43 PM ECT
-- Warehouse Management
UPDATE AD_Table SET AD_Window_ID=139, AccessLevel='3', Description='Storage Warehouse and Service Point', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Warehouse', ReplicationType='L', TableName='M_Warehouse',Updated=TO_TIMESTAMP('2009-09-06 23:50:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=190
;

-- Sep 6, 2009 11:50:44 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_Location_ID', Description='Location or Address', EntityType='D', Help='The Location / Address field defines the location of an entity.', IsActive='Y', Name='Address', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Address',Updated=TO_TIMESTAMP('2009-09-06 23:50:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=202
;

-- Sep 6, 2009 11:50:44 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=202
;

-- Sep 6, 2009 11:50:44 PM ECT
-- Warehouse Management
UPDATE AD_Reference SET Description='Location/Address', EntityType='D', Help=NULL, IsActive='Y', Name='Location (Address)', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-06 23:50:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=21
;

-- Sep 6, 2009 11:50:44 PM ECT
-- Warehouse Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=21
;

-- Sep 6, 2009 11:50:47 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_TIMESTAMP('2009-09-06 23:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=620
;

-- Sep 6, 2009 11:50:47 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Sep 6, 2009 11:50:47 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Separator', Description='Element Separator', EntityType='D', Help='The Element Separator defines the delimiter printed between elements of the structure', IsActive='Y', Name='Element Separator', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Element Separator',Updated=TO_TIMESTAMP('2009-09-06 23:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=565
;

-- Sep 6, 2009 11:50:47 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=565
;

-- Sep 6, 2009 11:50:48 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_WarehouseSource_ID', Description='Optional Warehouse to replenish from', EntityType='D', Help='If defined, the warehouse selected is used to replenish the product(s)', IsActive='Y', Name='Source Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Source Warehouse',Updated=TO_TIMESTAMP('2009-09-06 23:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2814
;

-- Sep 6, 2009 11:50:48 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2814
;

-- Sep 6, 2009 11:50:48 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='ReplenishmentClass', Description='Custom class to calculate Quantity to Order', EntityType='D', Help='If you select a custom replenishment type, you need to create a class implementing org.compiere.util.ReplenishInterface and set that on warehouse level.', IsActive='Y', Name='Replenishment Class', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Replenishment Class',Updated=TO_TIMESTAMP('2009-09-06 23:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2815
;

-- Sep 6, 2009 11:50:48 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2815
;

-- Sep 6, 2009 11:50:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53260,190,53091,NULL,TO_TIMESTAMP('2009-09-06 23:50:49','YYYY-MM-DD HH24:MI:SS'),0,'Define Warehouse','EE03','N','Y','N','N','Y','N','N','N','N','Warehouse','N',10,0,TO_TIMESTAMP('2009-09-06 23:50:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53260 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:50:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1151,57570,0,53260,TO_TIMESTAMP('2009-09-06 23:50:53','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',14,'EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','N','Warehouse',0,0,TO_TIMESTAMP('2009-09-06 23:50:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57570 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:50:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1248,57571,0,53260,TO_TIMESTAMP('2009-09-06 23:50:56','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:50:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:50:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57571 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1247,57572,0,53260,TO_TIMESTAMP('2009-09-06 23:50:58','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:50:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57572 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3005,57573,0,53260,TO_TIMESTAMP('2009-09-06 23:51:01','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',11,'EE03','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2009-09-06 23:51:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57573 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1152,57574,0,53260,TO_TIMESTAMP('2009-09-06 23:51:04','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,1,TO_TIMESTAMP('2009-09-06 23:51:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57574 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1153,57575,0,53260,TO_TIMESTAMP('2009-09-06 23:51:06','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-06 23:51:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57575 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1249,57576,0,53260,TO_TIMESTAMP('2009-09-06 23:51:09','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2009-09-06 23:51:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57576 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54240,57577,0,53260,TO_TIMESTAMP('2009-09-06 23:51:12','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit',1,'EE03','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','Y','Y','N','N','N','Y','In Transit',70,0,TO_TIMESTAMP('2009-09-06 23:51:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57577 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,1154,57578,0,53260,TO_TIMESTAMP('2009-09-06 23:51:15','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address',26,'EE03','The Location / Address field defines the location of an entity.','Y','Y','Y','N','N','N','N','Address',80,0,TO_TIMESTAMP('2009-09-06 23:51:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57578 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,4767,57579,0,53260,TO_TIMESTAMP('2009-09-06 23:51:18','YYYY-MM-DD HH24:MI:SS'),0,'Element Separator',5,'EE03','The Element Separator defines the delimiter printed between elements of the structure','Y','Y','Y','N','N','N','N','Element Separator',90,0,TO_TIMESTAMP('2009-09-06 23:51:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57579 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,14097,57580,0,53260,TO_TIMESTAMP('2009-09-06 23:51:21','YYYY-MM-DD HH24:MI:SS'),0,'Optional Warehouse to replenish from',10,'EE03','If defined, the warehouse selected is used to replenish the product(s)','Y','Y','Y','N','N','N','N','Source Warehouse',100,0,TO_TIMESTAMP('2009-09-06 23:51:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57580 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,14098,57581,0,53260,TO_TIMESTAMP('2009-09-06 23:51:23','YYYY-MM-DD HH24:MI:SS'),0,'Custom class to calculate Quantity to Order',20,'EE03','If you select a custom replenishment type, you need to create a class implementing org.compiere.util.ReplenishInterface and set that on warehouse level.','Y','Y','Y','N','N','N','Y','Replenishment Class',110,0,TO_TIMESTAMP('2009-09-06 23:51:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57581 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:51:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53236,'3',TO_TIMESTAMP('2009-09-06 23:51:26','YYYY-MM-DD HH24:MI:SS'),0,'Define the Locators belonging to an Warehouse Section','EE03','N','Y','N','Y','N','N','N','Define the Locators belonging to an Warehouse Section','L','WM_Section_Detail',TO_TIMESTAMP('2009-09-06 23:51:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53236 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:51:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53344,TO_TIMESTAMP('2009-09-06 23:51:29','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Section_Detail',1,'Y','N','Y','Y','WM_Section_Detail','N',1000000,TO_TIMESTAMP('2009-09-06 23:51:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58278,102,0,19,53236,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:51:32','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:51:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58278 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58279,113,0,19,53236,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:51:35','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:51:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58279 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58280,348,0,20,53236,'IsActive',TO_TIMESTAMP('2009-09-06 23:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:51:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58280 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58281,245,0,16,53236,'Created',TO_TIMESTAMP('2009-09-06 23:51:41','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:51:41','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58281 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58282,607,0,16,53236,'Updated',TO_TIMESTAMP('2009-09-06 23:51:44','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:51:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58282 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58283,246,0,19,110,53236,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:51:47','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:51:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58283 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58284,608,0,19,110,53236,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:51:50','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:51:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58284 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53917,0,'WM_Section_Detail_ID',TO_TIMESTAMP('2009-09-06 23:51:53','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Define the Locators belonging to an Warehouse Section ID','Define the Locators belonging to an Warehouse Section ID',TO_TIMESTAMP('2009-09-06 23:51:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:51:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53917 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:51:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58285,53917,0,13,53236,'WM_Section_Detail_ID',TO_TIMESTAMP('2009-09-06 23:51:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Define the Locators belonging to an Warehouse Section ID',TO_TIMESTAMP('2009-09-06 23:51:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:51:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58285 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:51:59 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Section_Detail_ID', Description='Define the Locations that belong to this section', EntityType='EE03', Help=NULL, IsActive='Y', Name='Warehouse Section Detail', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse Section Deatil',Updated=TO_TIMESTAMP('2009-09-06 23:51:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53917
;

-- Sep 6, 2009 11:51:59 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53917
;

-- Sep 6, 2009 11:51:59 PM ECT
-- Warehouse Management
CREATE TABLE WM_Section_Detail (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Section_Detail_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Section_Detail_Key PRIMARY KEY (WM_Section_Detail_ID))
;

-- Sep 6, 2009 11:52:02 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Section_ID', Description='The Warehouse Section is an grouping of Locators with similar features.', EntityType='EE03', Help='The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ', IsActive='Y', Name='Warehouse Section', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse Section',Updated=TO_TIMESTAMP('2009-09-06 23:52:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53914
;

-- Sep 6, 2009 11:52:02 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53914
;

-- Sep 6, 2009 11:52:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58286,53914,0,19,53236,'WM_Section_ID',TO_TIMESTAMP('2009-09-06 23:52:02','YYYY-MM-DD HH24:MI:SS'),0,'The Warehouse Section is an grouping of Locators with similar features.','EE03',22,'The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ','Y','N','N','N','N','Y','Y','N','Y','N','N','Warehouse Section',TO_TIMESTAMP('2009-09-06 23:52:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:52:05 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58286 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:05 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section_Detail ADD COLUMN WM_Section_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:52:05 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_Locator_ID', Description='Warehouse Locator', EntityType='D', Help='The Locator indicates where in a Warehouse a product is located.', IsActive='Y', Name='Locator', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Locator',Updated=TO_TIMESTAMP('2009-09-06 23:52:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=448
;

-- Sep 6, 2009 11:52:05 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=448
;

-- Sep 6, 2009 11:52:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58287,448,0,31,53236,'M_Locator_ID',TO_TIMESTAMP('2009-09-06 23:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE03',22,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','Y','N','N','Y','N','Y','Locator',TO_TIMESTAMP('2009-09-06 23:52:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:52:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58287 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:08 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section_Detail ADD COLUMN M_Locator_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:52:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53261,53236,53091,NULL,TO_TIMESTAMP('2009-09-06 23:52:08','YYYY-MM-DD HH24:MI:SS'),0,'EE03','N','Y','N','N','Y','N','N','N','N','Warehouse Section Detail','N',40,3,TO_TIMESTAMP('2009-09-06 23:52:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53261 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:52:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58278,57582,0,53261,TO_TIMESTAMP('2009-09-06 23:52:11','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_TIMESTAMP('2009-09-06 23:52:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57582 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58279,57583,0,53261,TO_TIMESTAMP('2009-09-06 23:52:14','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Organization',0,0,TO_TIMESTAMP('2009-09-06 23:52:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57583 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58285,57584,0,53261,TO_TIMESTAMP('2009-09-06 23:52:18','YYYY-MM-DD HH24:MI:SS'),0,'Define the Locations that belong to this section',22,'EE03','Y','Y','N','N','N','N','N','Warehouse Section Detail',0,0,TO_TIMESTAMP('2009-09-06 23:52:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57584 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58286,57585,0,53261,TO_TIMESTAMP('2009-09-06 23:52:22','YYYY-MM-DD HH24:MI:SS'),0,'The Warehouse Section is an grouping of Locators with similar features.',22,'EE03','The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ','Y','Y','N','N','N','N','N','Warehouse Section',0,0,TO_TIMESTAMP('2009-09-06 23:52:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57585 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58287,57586,0,53261,TO_TIMESTAMP('2009-09-06 23:52:26','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator',22,'EE03','The Locator indicates where in a Warehouse a product is located.','Y','Y','Y','N','N','N','N','Locator',10,0,TO_TIMESTAMP('2009-09-06 23:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57586 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58280,57587,0,53261,TO_TIMESTAMP('2009-09-06 23:52:29','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',20,0,TO_TIMESTAMP('2009-09-06 23:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57587 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:52:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53237,'3',TO_TIMESTAMP('2009-09-06 23:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Section allow grouping the Warehouse Locator','EE03','N','Y','N','Y','N','N','N','Warehouse Section','L','WM_Section',TO_TIMESTAMP('2009-09-06 23:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53237 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:52:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53345,TO_TIMESTAMP('2009-09-06 23:52:36','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Section',1,'Y','N','Y','Y','WM_Section','N',1000000,TO_TIMESTAMP('2009-09-06 23:52:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:52:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58288,102,0,19,53237,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:52:40','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:52:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58288 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58289,113,0,19,53237,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:52:42','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:52:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58289 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58290,348,0,20,53237,'IsActive',TO_TIMESTAMP('2009-09-06 23:52:45','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:52:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58290 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58291,245,0,16,53237,'Created',TO_TIMESTAMP('2009-09-06 23:52:48','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:52:48','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58291 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58292,607,0,16,53237,'Updated',TO_TIMESTAMP('2009-09-06 23:52:51','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:52:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58292 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58293,246,0,19,110,53237,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:52:54','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:52:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58293 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:52:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58294,608,0,19,110,53237,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:52:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:52:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:52:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58294 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58295,53914,0,13,53237,'WM_Section_ID',TO_TIMESTAMP('2009-09-06 23:52:59','YYYY-MM-DD HH24:MI:SS'),0,'The Warehouse Section is an grouping of Locators with similar features.','EE03',22,'The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ','Y','N','N','N','Y','Y','N','N','Y','N','N','Warehouse Section',TO_TIMESTAMP('2009-09-06 23:52:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:53:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58295 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:02 PM ECT
-- Warehouse Management
CREATE TABLE WM_Section (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Section_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Section_Key PRIMARY KEY (WM_Section_ID))
;

-- Sep 6, 2009 11:53:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58296,275,0,10,53237,'Description',TO_TIMESTAMP('2009-09-06 23:53:04','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:53:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:53:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58296 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:07 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:53:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58297,469,0,10,53237,'Name',TO_TIMESTAMP('2009-09-06 23:53:07','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:53:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:53:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58297 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:10 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:53:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58298,53915,0,19,53237,'WM_Area_ID',TO_TIMESTAMP('2009-09-06 23:53:10','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section','EE03',22,'Y','N','N','N','N','Y','Y','N','Y','N','N','Warehouse Area',TO_TIMESTAMP('2009-09-06 23:53:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:53:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58298 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:13 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section ADD COLUMN WM_Area_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:53:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53918,0,'WM_Section_Type_ID',TO_TIMESTAMP('2009-09-06 23:53:13','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Warehouse Section Type','Warehouse Section Type',TO_TIMESTAMP('2009-09-06 23:53:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53918 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:53:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58299,53918,0,19,53237,'WM_Section_Type_ID',TO_TIMESTAMP('2009-09-06 23:53:16','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','Y','N','N','Y','N','Y','Warehouse Section Type',TO_TIMESTAMP('2009-09-06 23:53:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:53:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58299 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:53:19 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section ADD COLUMN WM_Section_Type_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:53:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53262,53237,53091,NULL,TO_TIMESTAMP('2009-09-06 23:53:19','YYYY-MM-DD HH24:MI:SS'),0,'Define Warehouse Section','EE03','N','A Warehouse Section represents another physical warehouse area even more specific than the Warehouse Area itself, therefore it is required to have at least one warehouse section within a warehouse area, furthermore a Warehouse Section is used for grouping several locations.
','Y','N','N','Y','N','Y','N','N','Warehouse Section','N',30,4,TO_TIMESTAMP('2009-09-06 23:53:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53262 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:53:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58288,57588,0,53262,TO_TIMESTAMP('2009-09-06 23:53:22','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:53:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57588 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58289,57589,0,53262,TO_TIMESTAMP('2009-09-06 23:53:25','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:53:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57589 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58298,57590,0,53262,TO_TIMESTAMP('2009-09-06 23:53:27','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area allow grouping the Warehouse Section',22,'EE03','Y','Y','Y','N','N','N','N','Warehouse Area',30,0,TO_TIMESTAMP('2009-09-06 23:53:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57590 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58297,57591,0,53262,TO_TIMESTAMP('2009-09-06 23:53:30','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2009-09-06 23:53:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57591 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58296,57592,0,53262,TO_TIMESTAMP('2009-09-06 23:53:34','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-06 23:53:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57592 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58290,57593,0,53262,TO_TIMESTAMP('2009-09-06 23:53:37','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2009-09-06 23:53:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:40 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57593 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58299,57594,0,53262,TO_TIMESTAMP('2009-09-06 23:53:40','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','N','Warehouse Section Type',70,0,TO_TIMESTAMP('2009-09-06 23:53:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:43 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57594 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58295,57595,0,53262,TO_TIMESTAMP('2009-09-06 23:53:43','YYYY-MM-DD HH24:MI:SS'),0,'The Warehouse Section is an grouping of Locators with similar features.',22,'EE03','The criteria for grouping the locators can be for Moving Type (Fast, Slow ), heavy part, bulky Material ',53261,'Y','Y','Y','N','N','N','N','Warehouse Section',80,0,TO_TIMESTAMP('2009-09-06 23:53:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57595 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:53:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53092,TO_TIMESTAMP('2009-09-06 23:53:47','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Area Type','EE03','The Area Type allows to define serveral area types within the Warehouse, according to the use of the space e.g. wether it is for technical purposes or as putaway space:
Open Area
Bulk Materials Area
Gethering Area
Shelves Area
Etc.','Y','Y','N','Y','Warehouse Area Type','N',TO_TIMESTAMP('2009-09-06 23:53:47','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:53:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53092 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:53:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53238,'3',TO_TIMESTAMP('2009-09-06 23:53:51','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type','EE03','N','Y','N','Y','N','N','N','Warehouse Area Type ','L','WM_Area_Type',TO_TIMESTAMP('2009-09-06 23:53:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:53:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53238 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:53:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53346,TO_TIMESTAMP('2009-09-06 23:53:54','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Area_Type',1,'Y','N','Y','Y','WM_Area_Type','N',1000000,TO_TIMESTAMP('2009-09-06 23:53:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58300,102,0,19,53238,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:53:56','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:53:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58300 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58301,113,0,19,53238,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:54:00','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:54:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58301 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58302,348,0,20,53238,'IsActive',TO_TIMESTAMP('2009-09-06 23:54:04','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:54:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58302 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58303,245,0,16,53238,'Created',TO_TIMESTAMP('2009-09-06 23:54:07','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:54:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58303 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58304,607,0,16,53238,'Updated',TO_TIMESTAMP('2009-09-06 23:54:10','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:54:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58304 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58305,246,0,19,110,53238,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:54:14','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:54:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58305 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58306,608,0,19,110,53238,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:54:17','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:54:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58306 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58307,53916,0,13,53238,'WM_Area_Type_ID',TO_TIMESTAMP('2009-09-06 23:54:21','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type','EE03',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Warehouse Area Type',TO_TIMESTAMP('2009-09-06 23:54:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:54:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58307 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:23 PM ECT
-- Warehouse Management
CREATE TABLE WM_Area_Type (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Area_Type_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Area_Type_Key PRIMARY KEY (WM_Area_Type_ID))
;

-- Sep 6, 2009 11:54:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58308,469,0,10,53238,'Name',TO_TIMESTAMP('2009-09-06 23:54:26','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:54:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:54:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58308 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:29 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area_Type ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:54:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58309,275,0,10,53238,'Description',TO_TIMESTAMP('2009-09-06 23:54:29','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:54:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:54:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58309 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:54:33 PM ECT
-- Warehouse Management
ALTER TABLE WM_Area_Type ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:54:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53263,53238,53092,NULL,TO_TIMESTAMP('2009-09-06 23:54:33','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type','EE03','N','The Warehouse Area Type Tab defines different Warehouse Area used for different purpose in a Warehouse.','Y','N','N','Y','N','Y','N','N','Warehouse Area Type','N',10,0,TO_TIMESTAMP('2009-09-06 23:54:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53263 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:54:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58300,57596,0,53263,TO_TIMESTAMP('2009-09-06 23:54:36','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:54:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57596 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58301,57597,0,53263,TO_TIMESTAMP('2009-09-06 23:54:38','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:54:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57597 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58308,57598,0,53263,TO_TIMESTAMP('2009-09-06 23:54:42','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,0,TO_TIMESTAMP('2009-09-06 23:54:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57598 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58309,57599,0,53263,TO_TIMESTAMP('2009-09-06 23:54:45','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2009-09-06 23:54:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57599 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58302,57600,0,53263,TO_TIMESTAMP('2009-09-06 23:54:48','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2009-09-06 23:54:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57600 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58307,57601,0,53263,TO_TIMESTAMP('2009-09-06 23:54:51','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type',22,'EE01','Y','Y','N','N','N','N','N','Warehouse Area Type',0,0,TO_TIMESTAMP('2009-09-06 23:54:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:54:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57601 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:54:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53093,TO_TIMESTAMP('2009-09-06 23:54:54','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Section Type','EE03','The Warehouse Section type is required for picking and putaway the material, it can be defined according to:

*Heavy Parts
*Bulky Materials
*Slow or Fast moving products

Also, these types allow to determine the Operation Type e.g. either picking or putaway within the Warehouse

The Warehouse Section Type also define the Warehouse Section for Inbound or Outbound Operations

Also the Section Type allows to define the type Warehouse Management operation

Picking Section (Inbound)
Putaway Section (Outbound)','Y','Y','N','N','Warehouse Section Type','N',TO_TIMESTAMP('2009-09-06 23:54:54','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:54:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53093 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:55:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53239,'3',TO_TIMESTAMP('2009-09-06 23:54:58','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Section Type allow grouping the Warehouse Section for Type','EE03','N','Y','N','Y','N','N','N','Warehouse Section Type','L','WM_Section_Type',TO_TIMESTAMP('2009-09-06 23:54:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:55:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53239 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:55:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53347,TO_TIMESTAMP('2009-09-06 23:55:00','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Section_Type',1,'Y','N','Y','Y','WM_Section_Type','N',1000000,TO_TIMESTAMP('2009-09-06 23:55:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:55:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58310,102,0,19,53239,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:55:04','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:55:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58310 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58311,113,0,19,53239,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:55:07','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:55:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58311 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58312,348,0,20,53239,'IsActive',TO_TIMESTAMP('2009-09-06 23:55:10','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:55:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:14 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58312 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58313,245,0,16,53239,'Created',TO_TIMESTAMP('2009-09-06 23:55:14','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:55:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58313 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58314,607,0,16,53239,'Updated',TO_TIMESTAMP('2009-09-06 23:55:17','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:55:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58314 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58315,246,0,19,110,53239,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:55:20','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:55:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58315 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58316,608,0,19,110,53239,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:55:24','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:55:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58316 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58317,53918,0,13,53239,'WM_Section_Type_ID',TO_TIMESTAMP('2009-09-06 23:55:27','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Warehouse Section Type',TO_TIMESTAMP('2009-09-06 23:55:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:55:30 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58317 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:30 PM ECT
-- Warehouse Management
CREATE TABLE WM_Section_Type (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Section_Type_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Section_Type_Key PRIMARY KEY (WM_Section_Type_ID))
;

-- Sep 6, 2009 11:55:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58318,469,0,10,53239,'Name',TO_TIMESTAMP('2009-09-06 23:55:33','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:55:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:55:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58318 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:36 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section_Type ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:55:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58319,275,0,10,53239,'Description',TO_TIMESTAMP('2009-09-06 23:55:36','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:55:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:55:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58319 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:39 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section_Type ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:55:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53919,0,'InOutBoundType',TO_TIMESTAMP('2009-09-06 23:55:39','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type','EE03','The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','Inbound & Outbound Type','Inbound & Outbound Type',TO_TIMESTAMP('2009-09-06 23:55:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:55:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53919 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:55:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53321,TO_TIMESTAMP('2009-09-06 23:55:42','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','WM_InOutBound InOutboundType',TO_TIMESTAMP('2009-09-06 23:55:42','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Sep 6, 2009 11:55:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53321 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Sep 6, 2009 11:55:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53508,53321,TO_TIMESTAMP('2009-09-06 23:55:45','YYYY-MM-DD HH24:MI:SS'),0,'Material Picking','EE03','Y','Outbound Operation',TO_TIMESTAMP('2009-09-06 23:55:45','YYYY-MM-DD HH24:MI:SS'),0,'O')
;

-- Sep 6, 2009 11:55:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53508 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:55:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53509,53321,TO_TIMESTAMP('2009-09-06 23:55:48','YYYY-MM-DD HH24:MI:SS'),0,'Material Putaway','EE03','Y','Inbound Operation',TO_TIMESTAMP('2009-09-06 23:55:48','YYYY-MM-DD HH24:MI:SS'),0,'I')
;

-- Sep 6, 2009 11:55:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53509 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:55:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58320,53919,0,17,53321,53239,'InOutBoundType',TO_TIMESTAMP('2009-09-06 23:55:51','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type','EE03',1,'The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','N','N','N','N','Y','N','N','Y','N','Y','Inbound & Outbound Type',TO_TIMESTAMP('2009-09-06 23:55:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:55:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58320 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:55:54 PM ECT
-- Warehouse Management
ALTER TABLE WM_Section_Type ADD COLUMN InOutBoundType CHAR(1) NOT NULL
;

-- Sep 6, 2009 11:55:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53264,53239,53093,NULL,TO_TIMESTAMP('2009-09-06 23:55:54','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Section Type','U','N','The Warehouse Section Type Tab defines different Warehouse Section used for different purpose in a Warehouse.','Y','N','N','Y','N','Y','N','N','Warehouse Section Type','N',10,0,TO_TIMESTAMP('2009-09-06 23:55:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:55:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53264 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:56:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58317,57602,0,53264,TO_TIMESTAMP('2009-09-06 23:55:57','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','N','N','N','N','N','Warehouse Section Type',0,0,TO_TIMESTAMP('2009-09-06 23:55:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57602 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58310,57603,0,53264,TO_TIMESTAMP('2009-09-06 23:56:00','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:56:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57603 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58311,57604,0,53264,TO_TIMESTAMP('2009-09-06 23:56:03','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:56:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57604 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58318,57605,0,53264,TO_TIMESTAMP('2009-09-06 23:56:06','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,0,TO_TIMESTAMP('2009-09-06 23:56:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:08 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57605 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58319,57606,0,53264,TO_TIMESTAMP('2009-09-06 23:56:08','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2009-09-06 23:56:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:11 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57606 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58312,57607,0,53264,TO_TIMESTAMP('2009-09-06 23:56:11','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2009-09-06 23:56:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57607 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58320,57608,0,53264,TO_TIMESTAMP('2009-09-06 23:56:13','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type',1,'EE03','The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','Y','Y','N','N','N','N','Inbound & Outbound Type',60,0,TO_TIMESTAMP('2009-09-06 23:56:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57608 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:56:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53094,TO_TIMESTAMP('2009-09-06 23:56:15','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Inbound/Outbound Definition','EE03','Inbound/Outbound Definition window allows to define a set of conditions in order to implement a Inbound(Putaway) and Outbound(Picking) Strategy using different criteria such as:

Product
Product category
Product Group 1
Product Group 2 
Product Classification
Business Partner
Business Partner Group
Warehouse Area Type
Warehouse Section Type
Warehouse Location.
Priority
','Y','Y','N','N','Inbound/Outbound Definition','N',TO_TIMESTAMP('2009-09-06 23:56:15','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:56:17 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53094 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:56:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53240,'3',TO_TIMESTAMP('2009-09-06 23:56:17','YYYY-MM-DD HH24:MI:SS'),0,'EE03','N','Y','N','Y','N','N','N','Inboud & Outbound Definition','L','WM_Definition',TO_TIMESTAMP('2009-09-06 23:56:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:20 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53240 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:56:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53348,TO_TIMESTAMP('2009-09-06 23:56:20','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Definition',1,'Y','N','Y','Y','WM_Definition','N',1000000,TO_TIMESTAMP('2009-09-06 23:56:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58321,102,0,19,53240,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:56:22','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:56:22','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:25 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58322,113,0,19,53240,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:56:25','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:56:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:27 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58322 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58323,348,0,20,53240,'IsActive',TO_TIMESTAMP('2009-09-06 23:56:27','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:56:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58323 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58324,245,0,16,53240,'Created',TO_TIMESTAMP('2009-09-06 23:56:29','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:56:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58324 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58325,607,0,16,53240,'Updated',TO_TIMESTAMP('2009-09-06 23:56:32','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:56:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:35 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58325 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58326,246,0,19,110,53240,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:56:35','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:56:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58326 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58327,608,0,19,110,53240,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:56:37','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:56:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58327 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53920,0,'WM_Definition_ID',TO_TIMESTAMP('2009-09-06 23:56:39','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inboud & Outbound Definition ID','Inboud & Outbound Definition ID',TO_TIMESTAMP('2009-09-06 23:56:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:56:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53920 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:56:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58328,53920,0,13,53240,'WM_Definition_ID',TO_TIMESTAMP('2009-09-06 23:56:42','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Inboud & Outbound Definition ID',TO_TIMESTAMP('2009-09-06 23:56:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:56:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58328 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:45 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Definition_ID', Description=NULL, EntityType='EE03', Help=NULL, IsActive='Y', Name='Inboud & Outbound Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inboud & Outbound Definition',Updated=TO_TIMESTAMP('2009-09-06 23:56:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53920
;

-- Sep 6, 2009 11:56:45 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53920
;

-- Sep 6, 2009 11:56:45 PM ECT
-- Warehouse Management
CREATE TABLE WM_Definition (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Definition_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Definition_Key PRIMARY KEY (WM_Definition_ID))
;

-- Sep 6, 2009 11:56:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58329,454,0,30,53240,'M_Product_ID',TO_TIMESTAMP('2009-09-06 23:56:47','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE03',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_TIMESTAMP('2009-09-06 23:56:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:56:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58329 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:50 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:56:50 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='M_Product_Category_ID', Description='Category of a Product', EntityType='D', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', Name='Product Category', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Category',Updated=TO_TIMESTAMP('2009-09-06 23:56:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=453
;

-- Sep 6, 2009 11:56:50 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=453
;

-- Sep 6, 2009 11:56:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58330,453,0,19,53240,'M_Product_Category_ID',TO_TIMESTAMP('2009-09-06 23:56:50','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE03',22,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','Y','Product Category',TO_TIMESTAMP('2009-09-06 23:56:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:56:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58330 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:53 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN M_Product_Category_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:56:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58331,448,0,31,53240,'M_Locator_ID',TO_TIMESTAMP('2009-09-06 23:56:53','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE03',22,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','N','N','N','Y','N','Y','Locator',TO_TIMESTAMP('2009-09-06 23:56:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:56:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58331 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:55 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN M_Locator_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:56:55 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_BPartner_ID', Description='Identifies a Business Partner', EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', Name='Business Partner ', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner ',Updated=TO_TIMESTAMP('2009-09-06 23:56:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=187
;

-- Sep 6, 2009 11:56:55 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=187
;

-- Sep 6, 2009 11:56:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58332,187,0,30,53240,'C_BPartner_ID',TO_TIMESTAMP('2009-09-06 23:56:55','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE03',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner ',TO_TIMESTAMP('2009-09-06 23:56:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:56:58 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58332 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:56:58 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN C_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:56:58 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='C_BP_Group_ID', Description='Business Partner Group', EntityType='D', Help='The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.', IsActive='Y', Name='Business Partner Group', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BPartner Group',Updated=TO_TIMESTAMP('2009-09-06 23:56:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1383
;

-- Sep 6, 2009 11:56:58 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1383
;

-- Sep 6, 2009 11:57:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58333,1383,0,19,53240,'C_BP_Group_ID',TO_TIMESTAMP('2009-09-06 23:56:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE03',22,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner Group',TO_TIMESTAMP('2009-09-06 23:56:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:01 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58333 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:01 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN C_BP_Group_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53921,0,'WM_Strategy_ID',TO_TIMESTAMP('2009-09-06 23:57:01','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Warehouse Managamet Strategy','Warehouse Managamet Strategy',TO_TIMESTAMP('2009-09-06 23:57:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53921 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:57:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58334,53921,0,19,53240,'WM_Strategy_ID',TO_TIMESTAMP('2009-09-06 23:57:03','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','Y','N','N','Y','N','Y','Warehouse Managamet Strategy',TO_TIMESTAMP('2009-09-06 23:57:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:06 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58334 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:06 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN WM_Strategy_ID NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:57:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58335,53916,0,19,53240,'WM_Area_Type_ID',TO_TIMESTAMP('2009-09-06 23:57:06','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type','EE03',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse Area Type',TO_TIMESTAMP('2009-09-06 23:57:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:09 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58335 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:09 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN WM_Area_Type_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58336,53918,0,19,53240,'WM_Section_Type_ID',TO_TIMESTAMP('2009-09-06 23:57:09','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse Section Type',TO_TIMESTAMP('2009-09-06 23:57:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:12 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58336 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:12 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN WM_Section_Type_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:12 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Classification', Description='Classification for grouping', EntityType='D', Help='The Classification can be used to optionally group products.', IsActive='Y', Name='Classification', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Classification',Updated=TO_TIMESTAMP('2009-09-06 23:57:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=852
;

-- Sep 6, 2009 11:57:12 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=852
;

-- Sep 6, 2009 11:57:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58337,852,0,10,53240,'Classification',TO_TIMESTAMP('2009-09-06 23:57:12','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping','EE03',1,'The Classification can be used to optionally group products.','Y','N','N','N','N','N','N','N','Y','N','Y','Classification',TO_TIMESTAMP('2009-09-06 23:57:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:15 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58337 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:15 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN Classification VARCHAR(1) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:15 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Group1', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group1', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group1',Updated=TO_TIMESTAMP('2009-09-06 23:57:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52018
;

-- Sep 6, 2009 11:57:15 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52018
;

-- Sep 6, 2009 11:57:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58338,52018,0,10,53240,'Group1',TO_TIMESTAMP('2009-09-06 23:57:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Group1',TO_TIMESTAMP('2009-09-06 23:57:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:18 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58338 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:18 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN Group1 VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:18 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='Group2', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group2',Updated=TO_TIMESTAMP('2009-09-06 23:57:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52019
;

-- Sep 6, 2009 11:57:18 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52019
;

-- Sep 6, 2009 11:57:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58339,52019,0,10,53240,'Group2',TO_TIMESTAMP('2009-09-06 23:57:18','YYYY-MM-DD HH24:MI:SS'),0,'EE03',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Group2',TO_TIMESTAMP('2009-09-06 23:57:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:21 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58339 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:21 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN Group2 VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:21 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='SeqNo', Description='Method of ordering records; lowest number comes first', EntityType='D', Help='The Sequence indicates the order of records', IsActive='Y', Name='Sequence', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sequence',Updated=TO_TIMESTAMP('2009-09-06 23:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=566
;

-- Sep 6, 2009 11:57:21 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=566
;

-- Sep 6, 2009 11:57:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58340,566,0,11,53240,'SeqNo',TO_TIMESTAMP('2009-09-06 23:57:21','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE03',22,'The Sequence indicates the order of records','Y','N','N','N','N','Y','N','N','Y','N','Y','Sequence',TO_TIMESTAMP('2009-09-06 23:57:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:23 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58340 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:23 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN SeqNo NUMERIC(10) NOT NULL
;

-- Sep 6, 2009 11:57:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58341,469,0,10,53240,'Name',TO_TIMESTAMP('2009-09-06 23:57:23','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:57:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58341 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:26 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:57:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58342,275,0,10,53240,'Description',TO_TIMESTAMP('2009-09-06 23:57:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:57:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58342 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:29 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58343,522,0,17,154,53240,'PriorityRule',TO_TIMESTAMP('2009-09-06 23:57:29','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE03',1,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','N','N','N','Y','N','Y','Priority',TO_TIMESTAMP('2009-09-06 23:57:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:57:32 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58343 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:57:32 PM ECT
-- Warehouse Management
ALTER TABLE WM_Definition ADD COLUMN PriorityRule CHAR(1) DEFAULT NULL 
;

-- Sep 6, 2009 11:57:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_ColumnSortOrder_ID,AD_ColumnSortYesNo_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,58340,58323,0,53265,53240,53094,NULL,TO_TIMESTAMP('2009-09-06 23:57:32','YYYY-MM-DD HH24:MI:SS'),0,'The sequence tab allows to set the order of priority for the Inbound (Putaway) and Outbound(Picking) Strategy','EE03','N','Y','N','N','Y','N','N','Y','N','Sort of the combinations','N',20,1,TO_TIMESTAMP('2009-09-06 23:57:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53265 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:57:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53266,53240,53094,NULL,TO_TIMESTAMP('2009-09-06 23:57:34','YYYY-MM-DD HH24:MI:SS'),0,'Define the Inbound(Putaway) and Outbound(Picking) Strategy ','EE03','N','The Inbound(Putaway) and Outbound(Picking) definition tab allows to define a set of conditions in order to select the Inbound(Putaway) and Outbound(Picking) Strategy from a given sequence ordered by priority.','Y','N','N','Y','N','Y','N','N','Inbound & Outboud Definition','N',10,0,TO_TIMESTAMP('2009-09-06 23:57:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:37 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53266 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:57:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58321,57609,0,53266,TO_TIMESTAMP('2009-09-06 23:57:37','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:57:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57609 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58322,57610,0,53266,TO_TIMESTAMP('2009-09-06 23:57:39','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:57:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57610 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58340,57611,0,53266,TO_TIMESTAMP('2009-09-06 23:57:42','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',22,'EE03','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','Sequence',30,0,TO_TIMESTAMP('2009-09-06 23:57:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:45 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57611 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58341,57612,0,53266,TO_TIMESTAMP('2009-09-06 23:57:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2009-09-06 23:57:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57612 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58342,57613,0,53266,TO_TIMESTAMP('2009-09-06 23:57:47','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-06 23:57:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57613 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58323,57614,0,53266,TO_TIMESTAMP('2009-09-06 23:57:50','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2009-09-06 23:57:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57614 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58334,106,57615,0,53266,TO_TIMESTAMP('2009-09-06 23:57:53','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','N','Warehouse Managamet Strategy',70,0,TO_TIMESTAMP('2009-09-06 23:57:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:56 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57615 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:57:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58329,106,57616,0,53266,TO_TIMESTAMP('2009-09-06 23:57:56','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',22,'EE03','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','Product',80,0,TO_TIMESTAMP('2009-09-06 23:57:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:57:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57616 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58330,106,57617,0,53266,TO_TIMESTAMP('2009-09-06 23:57:59','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product',22,'EE03','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','Y','Product Category',90,0,TO_TIMESTAMP('2009-09-06 23:57:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:02 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57617 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58338,106,57618,0,53266,TO_TIMESTAMP('2009-09-06 23:58:02','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','N','Group1',100,0,TO_TIMESTAMP('2009-09-06 23:58:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:04 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57618 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58339,106,57619,0,53266,TO_TIMESTAMP('2009-09-06 23:58:04','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','Y','Group2',110,0,TO_TIMESTAMP('2009-09-06 23:58:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:07 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57619 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58337,106,57620,0,53266,TO_TIMESTAMP('2009-09-06 23:58:07','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping',1,'EE03','The Classification can be used to optionally group products.','Y','Y','Y','N','N','N','N','Classification',120,0,TO_TIMESTAMP('2009-09-06 23:58:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57620 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58332,107,57621,0,53266,TO_TIMESTAMP('2009-09-06 23:58:11','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',22,'EE03','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','Business Partner ',130,0,TO_TIMESTAMP('2009-09-06 23:58:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57621 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58333,107,57622,0,53266,TO_TIMESTAMP('2009-09-06 23:58:13','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group',22,'EE03','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','Y','N','N','N','Y','Business Partner Group',140,0,TO_TIMESTAMP('2009-09-06 23:58:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57622 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58335,108,57623,0,53266,TO_TIMESTAMP('2009-09-06 23:58:16','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Area Type allow grouping the Warehouse Area for Type',22,'EE03','Y','Y','Y','N','N','N','N','Warehouse Area Type',150,0,TO_TIMESTAMP('2009-09-06 23:58:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57623 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58336,108,57624,0,53266,TO_TIMESTAMP('2009-09-06 23:58:19','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','Y','Warehouse Section Type',160,0,TO_TIMESTAMP('2009-09-06 23:58:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57624 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58331,108,57625,0,53266,TO_TIMESTAMP('2009-09-06 23:58:22','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator',22,'EE03','The Locator indicates where in a Warehouse a product is located.','Y','Y','Y','N','N','N','N','Locator',170,0,TO_TIMESTAMP('2009-09-06 23:58:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:26 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57625 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58328,57626,0,53266,TO_TIMESTAMP('2009-09-06 23:58:26','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','N','N','N','N','N','Inboud & Outbound Definition',0,0,TO_TIMESTAMP('2009-09-06 23:58:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:29 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57626 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58343,57627,0,53266,TO_TIMESTAMP('2009-09-06 23:58:29','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document',1,'EE03','The Priority indicates the importance (high, medium, low) of this document','Y','Y','Y','N','N','N','Y','Priority',0,0,TO_TIMESTAMP('2009-09-06 23:58:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57627 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:58:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53095,TO_TIMESTAMP('2009-09-06 23:58:31','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Inbound/Outbound Rule','EE03','The Inbound(Putaway) and Outbound(Picking)  Rules define the business logic according to a previously implemented algorithm, these rules are used by the Inbound(Putaway) and Outbound(Picking)  Strategy to determine the business logic and the operation type within the warehouse.

','Y','Y','N','N','Inbound/Outbound Rule','N',TO_TIMESTAMP('2009-09-06 23:58:31','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 6, 2009 11:58:33 PM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53095 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2009 11:58:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53241,'3',TO_TIMESTAMP('2009-09-06 23:58:34','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Rules are used to determine the putaway or pick location for goods stocked in the warehouse','EE03','Inbound & Outbound Rules can be used to define which locators should be considered for putaway or picking. In addition to the pre-defined rules, custom java classes or Rule Engine can be provided to determine the set of potential locators to be considered by the putaway and picking processes.','N','Y','N','Y','N','N','N','Inbound & Outbound Rules','L','WM_Rule',TO_TIMESTAMP('2009-09-06 23:58:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:36 PM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53241 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2009 11:58:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53349,TO_TIMESTAMP('2009-09-06 23:58:36','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Rule',1,'Y','N','Y','Y','WM_Rule','N',1000000,TO_TIMESTAMP('2009-09-06 23:58:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:58:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58344,102,0,19,53241,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-06 23:58:39','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-06 23:58:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58344 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58345,113,0,19,53241,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-06 23:58:42','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-06 23:58:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58345 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58346,348,0,20,53241,'IsActive',TO_TIMESTAMP('2009-09-06 23:58:44','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-06 23:58:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:46 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58346 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58347,245,0,16,53241,'Created',TO_TIMESTAMP('2009-09-06 23:58:46','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-06 23:58:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:50 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58347 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58348,607,0,16,53241,'Updated',TO_TIMESTAMP('2009-09-06 23:58:50','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-06 23:58:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:53 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58348 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58349,246,0,19,110,53241,'CreatedBy',TO_TIMESTAMP('2009-09-06 23:58:53','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-06 23:58:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:55 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58349 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:58:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58350,608,0,19,110,53241,'UpdatedBy',TO_TIMESTAMP('2009-09-06 23:58:55','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-06 23:58:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:58:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58350 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53922,0,'WM_Rule_ID',TO_TIMESTAMP('2009-09-06 23:58:57','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & Outbound Rules ID','Inbound & Outbound Rules ID',TO_TIMESTAMP('2009-09-06 23:58:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:00 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53922 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:59:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58351,53922,0,13,53241,'WM_Rule_ID',TO_TIMESTAMP('2009-09-06 23:59:00','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Inbound & Outbound Rules ID',TO_TIMESTAMP('2009-09-06 23:59:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 6, 2009 11:59:03 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58351 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:03 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Rule_ID', Description=NULL, EntityType='EE03', Help=NULL, IsActive='Y', Name='Inbound & Outbound Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inbound & Outbound Rule',Updated=TO_TIMESTAMP('2009-09-06 23:59:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53922
;

-- Sep 6, 2009 11:59:03 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53922
;

-- Sep 6, 2009 11:59:04 PM ECT
-- Warehouse Management
CREATE TABLE WM_Rule (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Rule_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Rule_Key PRIMARY KEY (WM_Rule_ID))
;

-- Sep 6, 2009 11:59:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58352,275,0,10,53241,'Description',TO_TIMESTAMP('2009-09-06 23:59:06','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-06 23:59:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:10 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58352 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:10 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 6, 2009 11:59:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58353,469,0,10,53241,'Name',TO_TIMESTAMP('2009-09-06 23:59:10','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-06 23:59:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:13 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58353 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:13 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 6, 2009 11:59:13 PM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='AD_Rule_ID', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Rule',Updated=TO_TIMESTAMP('2009-09-06 23:59:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53332
;

-- Sep 6, 2009 11:59:13 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53332
;

-- Sep 6, 2009 11:59:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58354,53332,0,19,53241,'AD_Rule_ID',TO_TIMESTAMP('2009-09-06 23:59:13','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Rule',TO_TIMESTAMP('2009-09-06 23:59:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:16 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58354 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:16 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN AD_Rule_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 6, 2009 11:59:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58355,53919,0,17,53321,53241,'InOutBoundType',TO_TIMESTAMP('2009-09-06 23:59:16','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type','EE03',1,'The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','N','N','N','N','Y','N','N','Y','N','Y','Inbound & Outbound Type',TO_TIMESTAMP('2009-09-06 23:59:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:19 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58355 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:19 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN InOutBoundType CHAR(1) NOT NULL
;

-- Sep 6, 2009 11:59:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53923,0,'InOutBoundRule',TO_TIMESTAMP('2009-09-06 23:59:19','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Rule determinated the putaway or pick location for goods stocked in the warehouse','EE03','Inbound & Outbound Rule are used to define which locators should be considered for putaway or picking.','Y','Inbound & Outbound Rule','Inbound & Outbound Rule',TO_TIMESTAMP('2009-09-06 23:59:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:22 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53923 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:59:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53322,TO_TIMESTAMP('2009-09-06 23:59:22','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','WM_InOutBound Inbound & Outbound Rule',TO_TIMESTAMP('2009-09-06 23:59:22','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Sep 6, 2009 11:59:24 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53322 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Sep 6, 2009 11:59:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53510,53322,TO_TIMESTAMP('2009-09-06 23:59:24','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Custum Interface',TO_TIMESTAMP('2009-09-06 23:59:24','YYYY-MM-DD HH24:MI:SS'),0,'WMI')
;

-- Sep 6, 2009 11:59:28 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53510 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:59:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53511,53322,TO_TIMESTAMP('2009-09-06 23:59:28','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Find any locator with available capacity',TO_TIMESTAMP('2009-09-06 23:59:28','YYYY-MM-DD HH24:MI:SS'),0,'WMC')
;

-- Sep 6, 2009 11:59:31 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53511 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:59:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53512,53322,TO_TIMESTAMP('2009-09-06 23:59:31','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','For the material oldest using FIFO',TO_TIMESTAMP('2009-09-06 23:59:31','YYYY-MM-DD HH24:MI:SS'),0,'WMF')
;

-- Sep 6, 2009 11:59:34 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53512 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:59:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53513,53322,TO_TIMESTAMP('2009-09-06 23:59:34','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','For the material most recent using LIFO',TO_TIMESTAMP('2009-09-06 23:59:34','YYYY-MM-DD HH24:MI:SS'),0,'WML')
;

-- Sep 6, 2009 11:59:38 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53513 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 6, 2009 11:59:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58356,53923,0,17,53322,53241,'InOutBoundRule',TO_TIMESTAMP('2009-09-06 23:59:38','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Rule determinated the putaway or pick location for goods stocked in the warehouse','EE03',3,'Inbound & Outbound Rule are used to define which locators should be considered for putaway or picking.','Y','N','N','N','N','Y','N','N','Y','N','Y','Inbound & Outbound Rule',TO_TIMESTAMP('2009-09-06 23:59:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:41 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58356 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:41 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN InOutBoundRule VARCHAR(3) NOT NULL
;

-- Sep 6, 2009 11:59:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53924,0,'InOutboundClass',TO_TIMESTAMP('2009-09-06 23:59:41','YYYY-MM-DD HH24:MI:SS'),0,'Custom class to implemeted new Inbound & Outbound Rule logic','EE03','If you select a custom Inbound & Outbound type, you need to create a class implementing org.eevolution.util.IInOutboundRule and set that on Inbound & Outbound Rule.','Y','Inbound & Outbound Class','Â© Class',TO_TIMESTAMP('2009-09-06 23:59:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:44 PM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53924 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2009 11:59:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58357,53924,0,10,53241,'InOutboundClass',TO_TIMESTAMP('2009-09-06 23:59:44','YYYY-MM-DD HH24:MI:SS'),0,'Custom class to implemeted new Inbound & Outbound Rule logic','EE03',60,'If you select a custom Inbound & Outbound type, you need to create a class implementing org.eevolution.util.IInOutboundRule and set that on Inbound & Outbound Rule.','Y','N','N','N','N','N','N','N','Y','N','Y','Inbound & Outbound Class',TO_TIMESTAMP('2009-09-06 23:59:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 6, 2009 11:59:47 PM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58357 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2009 11:59:47 PM ECT
-- Warehouse Management
ALTER TABLE WM_Rule ADD COLUMN InOutboundClass VARCHAR(60) DEFAULT NULL 
;

-- Sep 6, 2009 11:59:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53267,53241,53095,NULL,TO_TIMESTAMP('2009-09-06 23:59:47','YYYY-MM-DD HH24:MI:SS'),0,'Define Inbound/Outbound Rule','EE03','N','The Inbound(Putaway) and Outbound(Picking)  Rules define the business logic according to a previously implemented algorithm, these rules are used by the Inbound(Putaway) and Outbound(Picking)  Strategy to determine the business logic and the operation type within the warehouse.
','Y','N','N','Y','N','Y','N','N','Inbound/Outbound Rule','N',10,0,TO_TIMESTAMP('2009-09-06 23:59:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:51 PM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53267 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2009 11:59:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58344,57628,0,53267,TO_TIMESTAMP('2009-09-06 23:59:51','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2009-09-06 23:59:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:54 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57628 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:59:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58345,57629,0,53267,TO_TIMESTAMP('2009-09-06 23:59:54','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-06 23:59:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57629 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2009 11:59:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58353,57630,0,53267,TO_TIMESTAMP('2009-09-06 23:59:57','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,0,TO_TIMESTAMP('2009-09-06 23:59:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 6, 2009 11:59:59 PM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57630 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58352,57631,0,53267,TO_TIMESTAMP('2009-09-06 23:59:59','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2009-09-06 23:59:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57631 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58346,57632,0,53267,TO_TIMESTAMP('2009-09-07 00:00:06','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2009-09-07 00:00:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57632 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58356,57633,0,53267,TO_TIMESTAMP('2009-09-07 00:00:10','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Rule determinated the putaway or pick location for goods stocked in the warehouse',3,'EE03','Inbound & Outbound Rule are used to define which locators should be considered for putaway or picking.','Y','Y','Y','N','N','N','N','Inbound & Outbound Rule',70,0,TO_TIMESTAMP('2009-09-07 00:00:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57633 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58355,57634,0,53267,TO_TIMESTAMP('2009-09-07 00:00:15','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type',1,'EE03','The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','Y','Y','N','N','N','Y','Inbound & Outbound Type',80,0,TO_TIMESTAMP('2009-09-07 00:00:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57634 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58357,57635,0,53267,TO_TIMESTAMP('2009-09-07 00:00:19','YYYY-MM-DD HH24:MI:SS'),0,'Custom class to implemeted new Inbound & Outbound Rule logic',22,'@InOutBoundRule@=''WMI''','EE03','If you select a custom Inbound & Outbound type, you need to create a class implementing org.eevolution.util.IInOutboundRule and set that on Inbound & Outbound Rule.','Y','Y','Y','N','N','N','N','Inbound & Outbound Class',90,0,TO_TIMESTAMP('2009-09-07 00:00:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57635 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58354,57636,0,53267,TO_TIMESTAMP('2009-09-07 00:00:23','YYYY-MM-DD HH24:MI:SS'),0,22,'@InOutBoundRule@=''WMI''','EE03','Y','Y','Y','N','N','N','Y','Rule',100,0,TO_TIMESTAMP('2009-09-07 00:00:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57636 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58351,57637,0,53267,TO_TIMESTAMP('2009-09-07 00:00:27','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','N','N','N','N','N','Inbound & Outbound Rule',0,0,TO_TIMESTAMP('2009-09-07 00:00:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57637 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:00:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53096,TO_TIMESTAMP('2009-09-07 00:00:30','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Inbound/Outbound Strategy','EE03','The Inbound(Putaway) and Outbound(Picking) Strategy allows to define several rules and their sequences as to how they are to be applied in order to get the right locations to be used in the warehouse operation, thus allowing you to define your own Inbound(Putaway) and Outbound(Picking) strategies for the material.','Y','Y','N','Y','Inbound/Outbound Strategy','N',TO_TIMESTAMP('2009-09-07 00:00:30','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 7, 2009 12:00:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53096 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 7, 2009 12:00:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53242,'3',TO_TIMESTAMP('2009-09-07 00:00:35','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Inbound & Outbound Strategy Detail','EE03','Inbound & Outbound Strategy Detail defined the sequences to apply the Inbound & Outbound  Rule','N','Y','N','Y','N','N','N','Inbound & Outbound Strategy Detail','L','WM_Strategy_Detail',TO_TIMESTAMP('2009-09-07 00:00:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53242 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 7, 2009 12:00:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53350,TO_TIMESTAMP('2009-09-07 00:00:39','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Strategy_Detail',1,'Y','N','Y','Y','WM_Strategy_Detail','N',1000000,TO_TIMESTAMP('2009-09-07 00:00:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:00:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58358,102,0,19,53242,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-07 00:00:42','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-07 00:00:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:00:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58358 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:00:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58359,113,0,19,53242,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-07 00:00:46','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-07 00:00:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:00:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58359 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:00:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58360,348,0,20,53242,'IsActive',TO_TIMESTAMP('2009-09-07 00:00:51','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-07 00:00:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:00:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58360 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:00:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58361,245,0,16,53242,'Created',TO_TIMESTAMP('2009-09-07 00:00:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-07 00:00:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:00:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58361 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58362,607,0,16,53242,'Updated',TO_TIMESTAMP('2009-09-07 00:00:59','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-07 00:00:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:01:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58362 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58363,246,0,19,110,53242,'CreatedBy',TO_TIMESTAMP('2009-09-07 00:01:03','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-07 00:01:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:01:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58363 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58364,608,0,19,110,53242,'UpdatedBy',TO_TIMESTAMP('2009-09-07 00:01:06','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-07 00:01:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:01:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58364 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53925,0,'WM_Strategy_Detail_ID',TO_TIMESTAMP('2009-09-07 00:01:09','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Inbound & Outbound Strategy Detail ID','Inbound & Outbound Strategy Detail ID',TO_TIMESTAMP('2009-09-07 00:01:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53925 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 7, 2009 12:01:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58365,53925,0,13,53242,'WM_Strategy_Detail_ID',TO_TIMESTAMP('2009-09-07 00:01:12','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE03',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Inbound & Outbound Strategy Detail ID',TO_TIMESTAMP('2009-09-07 00:01:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:01:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58365 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:15 AM ECT
-- Warehouse Management
UPDATE AD_Element SET ColumnName='WM_Strategy_Detail_ID', Description=NULL, EntityType='EE03', Help=NULL, IsActive='Y', Name='Inbound & Outbound Strategy Detail ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Inbound & Outbound Strategy Detail ID',Updated=TO_TIMESTAMP('2009-09-07 00:01:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53925
;

-- Sep 7, 2009 12:01:15 AM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53925
;

-- Sep 7, 2009 12:01:15 AM ECT
-- Warehouse Management
CREATE TABLE WM_Strategy_Detail (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Strategy_Detail_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Strategy_Detail_Key PRIMARY KEY (WM_Strategy_Detail_ID))
;

-- Sep 7, 2009 12:01:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58366,566,0,11,53242,'SeqNo',TO_TIMESTAMP('2009-09-07 00:01:18','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE03',22,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','Y','N','Y','Sequence',TO_TIMESTAMP('2009-09-07 00:01:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:01:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58366 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:22 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy_Detail ADD COLUMN SeqNo NUMERIC(10) DEFAULT NULL 
;

-- Sep 7, 2009 12:01:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58367,53922,0,19,53242,'WM_Rule_ID',TO_TIMESTAMP('2009-09-07 00:01:22','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Inbound & Outbound Rule',TO_TIMESTAMP('2009-09-07 00:01:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:01:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58367 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:26 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy_Detail ADD COLUMN WM_Rule_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 7, 2009 12:01:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58368,53921,0,19,53242,'WM_Strategy_ID',TO_TIMESTAMP('2009-09-07 00:01:26','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','N','N','Y','N','Y','N','N','Warehouse Managamet Strategy',TO_TIMESTAMP('2009-09-07 00:01:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:01:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58368 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:01:33 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy_Detail ADD COLUMN WM_Strategy_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 7, 2009 12:01:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53268,53242,53096,NULL,TO_TIMESTAMP('2009-09-07 00:01:33','YYYY-MM-DD HH24:MI:SS'),0,'EE03','N','Y','N','N','Y','N','N','N','N','Inbound/Outbound Strategy Detail','N',20,1,TO_TIMESTAMP('2009-09-07 00:01:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53268 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 7, 2009 12:01:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58368,57638,0,53268,TO_TIMESTAMP('2009-09-07 00:01:36','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','N','N','N','N','N','Warehouse Managamet Strategy',0,0,TO_TIMESTAMP('2009-09-07 00:01:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57638 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:01:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58359,57639,0,53268,TO_TIMESTAMP('2009-09-07 00:01:39','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Organization',0,0,TO_TIMESTAMP('2009-09-07 00:01:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57639 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:01:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58358,57640,0,53268,TO_TIMESTAMP('2009-09-07 00:01:43','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_TIMESTAMP('2009-09-07 00:01:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57640 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:01:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58366,57641,0,53268,TO_TIMESTAMP('2009-09-07 00:01:47','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',22,'EE03','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','Sequence',10,0,TO_TIMESTAMP('2009-09-07 00:01:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57641 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:01:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58367,57642,0,53268,TO_TIMESTAMP('2009-09-07 00:01:51','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','Y','N','N','N','N','Inbound & Outbound Rule',20,0,TO_TIMESTAMP('2009-09-07 00:01:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57642 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:01:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58360,57643,0,53268,TO_TIMESTAMP('2009-09-07 00:01:53','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',30,0,TO_TIMESTAMP('2009-09-07 00:01:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:01:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57643 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:02:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58365,57644,0,53268,TO_TIMESTAMP('2009-09-07 00:01:57','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03','Y','Y','N','N','N','N','N','Inbound & Outbound Strategy Detail ID',0,0,TO_TIMESTAMP('2009-09-07 00:01:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:02:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57644 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:02:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53243,'3',TO_TIMESTAMP('2009-09-07 00:02:01','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Inbound & Outbound Strategy','EE03','Inbound & Outbound Strategy are used to determine the sequenced grouping of Inbound & Outbound rules that determine the putaway or pick location for goods stocked in the warehouse. ','N','Y','N','Y','N','N','N','Inbound & Outbound Strategy','L','WM_Strategy',TO_TIMESTAMP('2009-09-07 00:02:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:02:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53243 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 7, 2009 12:02:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53351,TO_TIMESTAMP('2009-09-07 00:02:05','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table WM_Strategy',1,'Y','N','Y','Y','WM_Strategy','N',1000000,TO_TIMESTAMP('2009-09-07 00:02:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:02:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58369,102,0,19,53243,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-07 00:02:07','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE03',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-07 00:02:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:02:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58369 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:02:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58370,113,0,19,53243,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-07 00:02:26','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE03',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-07 00:02:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:02:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58370 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:02:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58371,348,0,20,53243,'IsActive',TO_TIMESTAMP('2009-09-07 00:02:35','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE03',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-07 00:02:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:02:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58371 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58372,245,0,16,53243,'Created',TO_TIMESTAMP('2009-09-07 00:02:47','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE03',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-07 00:02:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:03:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58372 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58373,607,0,16,53243,'Updated',TO_TIMESTAMP('2009-09-07 00:03:00','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE03',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-07 00:03:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:03:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58373 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58374,246,0,19,110,53243,'CreatedBy',TO_TIMESTAMP('2009-09-07 00:03:13','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE03',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-07 00:03:13','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:03:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58374 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58375,608,0,19,110,53243,'UpdatedBy',TO_TIMESTAMP('2009-09-07 00:03:25','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE03',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-07 00:03:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:03:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58375 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58376,53921,0,13,53243,'WM_Strategy_ID',TO_TIMESTAMP('2009-09-07 00:03:35','YYYY-MM-DD HH24:MI:SS'),0,'EE03',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Warehouse Managamet Strategy',TO_TIMESTAMP('2009-09-07 00:03:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 7, 2009 12:03:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58376 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:03:49 AM ECT
-- Warehouse Management
CREATE TABLE WM_Strategy (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WM_Strategy_ID NUMERIC(10) NOT NULL, CONSTRAINT WM_Strategy_Key PRIMARY KEY (WM_Strategy_ID))
;

-- Sep 7, 2009 12:04:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58377,469,0,10,53243,'Name',TO_TIMESTAMP('2009-09-07 00:03:51','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE03',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-07 00:03:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:04:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58377 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:04:01 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 7, 2009 12:04:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58378,275,0,10,53243,'Description',TO_TIMESTAMP('2009-09-07 00:04:01','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE03',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-07 00:04:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:04:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58378 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:04:11 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 7, 2009 12:04:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58379,459,0,18,197,53243,189,'M_Warehouse_ID',TO_TIMESTAMP('2009-09-07 00:04:11','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE03',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','Y','N','N','Y','N','Y','Warehouse',TO_TIMESTAMP('2009-09-07 00:04:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:04:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58379 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:04:22 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy ADD COLUMN M_Warehouse_ID NUMERIC(10) NOT NULL
;

-- Sep 7, 2009 12:04:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58380,53919,0,17,53321,53243,'InOutBoundType',TO_TIMESTAMP('2009-09-07 00:04:22','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type','EE03',1,'The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','N','N','N','N','Y','N','N','Y','N','Y','Inbound & Outbound Type',TO_TIMESTAMP('2009-09-07 00:04:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 7, 2009 12:04:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58380 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 7, 2009 12:04:36 AM ECT
-- Warehouse Management
ALTER TABLE WM_Strategy ADD COLUMN InOutBoundType CHAR(1) NOT NULL
;

-- Sep 7, 2009 12:04:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53269,53243,53096,NULL,TO_TIMESTAMP('2009-09-07 00:04:36','YYYY-MM-DD HH24:MI:SS'),0,'Define the Inbound/Outbound Strategy','EE03','N','Y','N','N','Y','N','Y','N','N','Inbound/Outbound Strategy','N',10,0,TO_TIMESTAMP('2009-09-07 00:04:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:04:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53269 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 7, 2009 12:05:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58369,57645,0,53269,TO_TIMESTAMP('2009-09-07 00:04:49','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE03','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2009-09-07 00:04:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57645 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58370,57646,0,53269,TO_TIMESTAMP('2009-09-07 00:05:03','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE03','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-07 00:05:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57646 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58377,57647,0,53269,TO_TIMESTAMP('2009-09-07 00:05:16','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE03','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,0,TO_TIMESTAMP('2009-09-07 00:05:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57647 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58378,57648,0,53269,TO_TIMESTAMP('2009-09-07 00:05:30','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE03','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2009-09-07 00:05:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57648 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58371,57649,0,53269,TO_TIMESTAMP('2009-09-07 00:05:44','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE03','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2009-09-07 00:05:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57649 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58379,57650,0,53269,TO_TIMESTAMP('2009-09-07 00:05:49','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',22,'EE03','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','Warehouse',60,0,TO_TIMESTAMP('2009-09-07 00:05:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57650 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:05:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58380,57651,0,53269,TO_TIMESTAMP('2009-09-07 00:05:53','YYYY-MM-DD HH24:MI:SS'),0,'Inbound & Outbound Type',1,'EE03','The Inbound & Outbound Type defines the type of In & Out Operation to be Putaway or Picking.','Y','Y','Y','N','N','N','Y','Inbound & Outbound Type',70,0,TO_TIMESTAMP('2009-09-07 00:05:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:05:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57651 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58376,57652,0,53269,TO_TIMESTAMP('2009-09-07 00:05:57','YYYY-MM-DD HH24:MI:SS'),0,22,'EE03',53268,'Y','Y','Y','N','N','N','N','Warehouse Managamet Strategy',80,0,TO_TIMESTAMP('2009-09-07 00:05:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57652 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:04 AM ECT
-- Warehouse Management
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50095,50072,50094,TO_TIMESTAMP('2009-09-07 00:06:01','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y',10,TO_TIMESTAMP('2009-09-07 00:06:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:11 AM ECT
-- Warehouse Management
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50097,50073,50094,TO_TIMESTAMP('2009-09-07 00:06:04','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N',100,TO_TIMESTAMP('2009-09-07 00:06:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:15 AM ECT
-- Warehouse Management
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50096,50074,50095,TO_TIMESTAMP('2009-09-07 00:06:11','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N',100,TO_TIMESTAMP('2009-09-07 00:06:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53183,53270,53233,53090,NULL,TO_TIMESTAMP('2009-09-07 00:06:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03','N','Y','N','N','Y','N','Y','N','N','Outbound Order','N',10,0,TO_TIMESTAMP('2009-09-07 00:06:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53270 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 7, 2009 12:06:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58226,57653,0,53270,TO_TIMESTAMP('2009-09-07 00:06:18','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','Processed',0,0,TO_TIMESTAMP('2009-09-07 00:06:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57653 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58204,57654,0,53270,TO_TIMESTAMP('2009-09-07 00:06:21','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.',7,'EE01','Indicates the Date that a document was printed.','Y','Y','N','N','N','N','N','Date printed',0,0,TO_TIMESTAMP('2009-09-07 00:06:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57654 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58216,57655,0,53270,TO_TIMESTAMP('2009-09-07 00:06:26','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval',1,'EE01','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','N','N','N','N','N','Approved',0,0,TO_TIMESTAMP('2009-09-07 00:06:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57655 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58229,57656,0,53270,TO_TIMESTAMP('2009-09-07 00:06:29','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail',1,'EE01','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','N','Send EMail',0,0,TO_TIMESTAMP('2009-09-07 00:06:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57656 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58220,57657,0,53270,TO_TIMESTAMP('2009-09-07 00:06:31','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction',1,'EE01','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','N','Sales Transaction',0,0,TO_TIMESTAMP('2009-09-07 00:06:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57657 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58194,57658,0,53270,TO_TIMESTAMP('2009-09-07 00:06:33','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Active',0,0,TO_TIMESTAMP('2009-09-07 00:06:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57658 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58218,57659,0,53270,TO_TIMESTAMP('2009-09-07 00:06:36','YYYY-MM-DD HH24:MI:SS'),0,'Movement is in transit',1,'EE01','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.','Y','Y','N','N','N','N','N','In Transit',0,0,TO_TIMESTAMP('2009-09-07 00:06:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57659 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58219,57660,0,53270,TO_TIMESTAMP('2009-09-07 00:06:40','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed',1,'EE01','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','N','N','N','N','Printed',0,0,TO_TIMESTAMP('2009-09-07 00:06:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57660 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58227,57661,0,53270,TO_TIMESTAMP('2009-09-07 00:06:45','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','N','N','N','N','N','Process Now',0,0,TO_TIMESTAMP('2009-09-07 00:06:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57661 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58192,57662,0,53270,TO_TIMESTAMP('2009-09-07 00:06:49','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-07 00:06:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57662 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58193,57663,0,53270,TO_TIMESTAMP('2009-09-07 00:06:52','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-07 00:06:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57663 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:06:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58210,57664,0,53270,TO_TIMESTAMP('2009-09-07 00:06:55','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document',20,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Document No',30,0,TO_TIMESTAMP('2009-09-07 00:06:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:06:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57664 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58223,57665,0,53270,TO_TIMESTAMP('2009-09-07 00:06:58','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner',20,'EE01','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','Y','N','N','N','Y','Order Reference',40,0,TO_TIMESTAMP('2009-09-07 00:06:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57665 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58207,57666,0,53270,TO_TIMESTAMP('2009-09-07 00:07:03','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-07 00:07:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57666 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58203,57667,0,53270,TO_TIMESTAMP('2009-09-07 00:07:06','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules',22,'EE01','The Document Type determines document sequence and processing rules','Y','Y','Y','N','N','N','N','Document Type',60,0,TO_TIMESTAMP('2009-09-07 00:07:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57667 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58224,57668,0,53270,TO_TIMESTAMP('2009-09-07 00:07:12','YYYY-MM-DD HH24:MI:SS'),0,'Date/Time when picked for Shipment',7,'EE01','Y','Y','Y','N','N','N','N','Pick Date',70,0,TO_TIMESTAMP('2009-09-07 00:07:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57668 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58230,57669,0,53270,TO_TIMESTAMP('2009-09-07 00:07:15','YYYY-MM-DD HH24:MI:SS'),0,'Shipment Date/Time',7,'EE01','Actual Date/Time of Shipment (pick up)','Y','Y','Y','N','N','N','Y','Ship Date',80,0,TO_TIMESTAMP('2009-09-07 00:07:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57669 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58225,57670,0,53270,TO_TIMESTAMP('2009-09-07 00:07:18','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document',1,'EE01','The Priority indicates the importance (high, medium, low) of this document','Y','Y','Y','N','N','N','N','Priority',90,0,TO_TIMESTAMP('2009-09-07 00:07:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57670 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58217,57671,0,53270,TO_TIMESTAMP('2009-09-07 00:07:20','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer',1,'EE01','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','Y','N','N','N','Y','Drop Shipment',100,0,TO_TIMESTAMP('2009-09-07 00:07:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57671 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58211,57672,0,53270,TO_TIMESTAMP('2009-09-07 00:07:25','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to',10,'@IsDropShip@=''Y''','EE01','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','Drop Shipment Partner',110,0,TO_TIMESTAMP('2009-09-07 00:07:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57672 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58212,57673,0,53270,TO_TIMESTAMP('2009-09-07 00:07:28','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to',10,'@IsDropShip@=''Y''','EE01','Y','Y','Y','N','N','N','Y','Drop Shipment Location',120,0,TO_TIMESTAMP('2009-09-07 00:07:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57673 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58213,57674,0,53270,TO_TIMESTAMP('2009-09-07 00:07:30','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment',10,'@IsDropShip@=''Y''','EE01','Y','Y','Y','N','N','N','N','Drop Shipment Contact',130,0,TO_TIMESTAMP('2009-09-07 00:07:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57674 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58205,50013,57675,0,53270,TO_TIMESTAMP('2009-09-07 00:07:34','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery',1,'EE01','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','Y','N','N','N','N','Delivery Rule',140,0,TO_TIMESTAMP('2009-09-07 00:07:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57675 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58206,50013,57676,0,53270,TO_TIMESTAMP('2009-09-07 00:07:36','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered',1,'EE01','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','Y','N','N','N','Y','Delivery Via',150,0,TO_TIMESTAMP('2009-09-07 00:07:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57676 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58221,50013,57677,0,53270,TO_TIMESTAMP('2009-09-07 00:07:38','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery',22,'@DeliveryViaRule@=''S''','EE01','The Shipper indicates the method of delivering product','Y','Y','Y','N','N','N','N','Shipper',160,0,TO_TIMESTAMP('2009-09-07 00:07:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57677 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58231,50013,57678,0,53270,TO_TIMESTAMP('2009-09-07 00:07:40','YYYY-MM-DD HH24:MI:SS'),0,'Number to track the shipment',60,'@DeliveryViaRule@=''S''','EE01','Y','Y','Y','N','N','N','Y','Tracking No',170,0,TO_TIMESTAMP('2009-09-07 00:07:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57678 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58215,50013,57679,0,53270,TO_TIMESTAMP('2009-09-07 00:07:44','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight',1,'EE01','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','Y','N','N','N','N','Freight Cost Rule',180,0,TO_TIMESTAMP('2009-09-07 00:07:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57679 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58214,50013,57680,0,53270,TO_TIMESTAMP('2009-09-07 00:07:47','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ',22,'@FreightCostRule@=''F''','EE01','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','Y','N','N','N','Y','Freight Amount',190,0,TO_TIMESTAMP('2009-09-07 00:07:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57680 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58222,50013,57681,0,53270,TO_TIMESTAMP('2009-09-07 00:07:49','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',22,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','Warehouse',200,0,TO_TIMESTAMP('2009-09-07 00:07:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57681 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58228,50013,57682,0,53270,TO_TIMESTAMP('2009-09-07 00:07:50','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent',22,'EE01','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','Y','N','N','N','N','Sales Representative',210,0,TO_TIMESTAMP('2009-09-07 00:07:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57682 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58235,50013,57683,0,53270,TO_TIMESTAMP('2009-09-07 00:07:52','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product',22,'EE01','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','Y','N','N','Y','N','Weight',220,0,TO_TIMESTAMP('2009-09-07 00:07:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57683 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58234,50013,57684,0,53270,TO_TIMESTAMP('2009-09-07 00:07:54','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product',22,'EE01','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','Y','N','N','Y','Y','Volume',230,0,TO_TIMESTAMP('2009-09-07 00:07:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57684 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:07:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58199,57685,0,53270,TO_TIMESTAMP('2009-09-07 00:07:56','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01',53258,'Y','Y','Y','N','N','N','N','Inbound & Outbound Order',240,0,TO_TIMESTAMP('2009-09-07 00:07:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:07:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57685 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58201,104,57686,0,53270,TO_TIMESTAMP('2009-09-07 00:07:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity',22,'EE01','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','N','N','N','N','Activity',250,0,TO_TIMESTAMP('2009-09-07 00:07:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57686 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58202,104,57687,0,53270,TO_TIMESTAMP('2009-09-07 00:08:00','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign',22,'EE01','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','Y','Campaign',260,0,TO_TIMESTAMP('2009-09-07 00:08:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57687 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58232,104,57688,0,53270,TO_TIMESTAMP('2009-09-07 00:08:01','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1',22,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','User List 1',270,0,TO_TIMESTAMP('2009-09-07 00:08:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57688 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58233,104,57689,0,53270,TO_TIMESTAMP('2009-09-07 00:08:03','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2',22,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','Y','User List 2',280,0,TO_TIMESTAMP('2009-09-07 00:08:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57689 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58200,104,57690,0,53270,TO_TIMESTAMP('2009-09-07 00:08:05','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',22,'EE01','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','N','Trx Organization',290,0,TO_TIMESTAMP('2009-09-07 00:08:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57690 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58209,125,57691,0,53270,TO_TIMESTAMP('2009-09-07 00:08:07','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','Y','N','Document Status',310,0,TO_TIMESTAMP('2009-09-07 00:08:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57691 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58208,125,57692,0,53270,TO_TIMESTAMP('2009-09-07 00:08:10','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document',2,'EE01','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','Y','N','N','N','Y','Document Action',320,0,TO_TIMESTAMP('2009-09-07 00:08:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57692 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 7, 2009 12:08:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53229,0,TO_TIMESTAMP('2009-09-07 00:08:12','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','N','Y','Warehouse Management',TO_TIMESTAMP('2009-09-07 00:08:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53229 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:15 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 0,13, 10, 53229)
;

-- Sep 7, 2009 12:08:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53230,0,TO_TIMESTAMP('2009-09-07 00:08:15','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','N','Y','Outbound Operations',TO_TIMESTAMP('2009-09-07 00:08:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53230 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:18 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53229,2, 10, 53230)
;

-- Sep 7, 2009 12:08:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53231,0,53090,'W',TO_TIMESTAMP('2009-09-07 00:08:18','YYYY-MM-DD HH24:MI:SS'),0,'Outbound Order allow picking the products of Warehouse ','EE03','Y','N','N','N','Outbound Order',TO_TIMESTAMP('2009-09-07 00:08:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53231 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:20 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53230,2, 10, 53231)
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53232,0,53184,'P',TO_TIMESTAMP('2009-09-07 00:08:20','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','N','N','Release Packing Order',TO_TIMESTAMP('2009-09-07 00:08:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53232 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53230,3, 10, 53232)
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
UPDATE AD_Menu SET AD_Process_ID=53185, "action"='P', Description=NULL, EntityType='EE03', IsActive='Y', IsReadOnly='N', IsSOTrx='N', IsSummary='N', Name='Generate Shipments',Updated=TO_TIMESTAMP('2009-09-07 00:08:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=193
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=193
;

-- Sep 7, 2009 12:08:23 AM ECT
-- Warehouse Management
UPDATE AD_TREENODEMM SET Parent_ID = 53230 , SeqNo = 4 WHERE AD_Tree_ID = 10 AND Node_ID = 193
;

-- Sep 7, 2009 12:08:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53233,0,53186,'P',TO_TIMESTAMP('2009-09-07 00:08:23','YYYY-MM-DD HH24:MI:SS'),0,'This process allows to generate a new document as the result of gathering Sales Orders, Manufacture Orders, Distribution Orders based on several criteria such as :','EE03','Y','N','N','N','Generare Outbound Order',TO_TIMESTAMP('2009-09-07 00:08:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53233 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:30 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53230,1, 10, 53233)
;

-- Sep 7, 2009 12:08:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53234,0,TO_TIMESTAMP('2009-09-07 00:08:30','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','N','Y','Inbound Operations',TO_TIMESTAMP('2009-09-07 00:08:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53234 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:35 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53229,1, 10, 53234)
;

-- Sep 7, 2009 12:08:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53235,0,TO_TIMESTAMP('2009-09-07 00:08:35','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','N','Y','Warehouse Management Setup',TO_TIMESTAMP('2009-09-07 00:08:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53235 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:42 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53229,0, 10, 53235)
;

-- Sep 7, 2009 12:08:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53236,0,53091,'W',TO_TIMESTAMP('2009-09-07 00:08:42','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Structure','EE03','Y','N','N','N','Warehouse Structure',TO_TIMESTAMP('2009-09-07 00:08:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53236 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:48 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,2, 10, 53236)
;

-- Sep 7, 2009 12:08:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53237,0,53092,'W',TO_TIMESTAMP('2009-09-07 00:08:48','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Area Type','EE03','Y','N','N','N','Warehouse Area Type',TO_TIMESTAMP('2009-09-07 00:08:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:08:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53237 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:08:54 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,0, 10, 53237)
;

-- Sep 7, 2009 12:09:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53238,0,53093,'W',TO_TIMESTAMP('2009-09-07 00:08:54','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Section Type','EE03','Y','N','N','N','Warehouse Section Type',TO_TIMESTAMP('2009-09-07 00:08:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:09:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53238 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:09:00 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,1, 10, 53238)
;

-- Sep 7, 2009 12:09:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53239,0,53094,'W',TO_TIMESTAMP('2009-09-07 00:09:00','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Inbound/Outbound Definition','EE03','Y','N','N','N','Inbound/Outbound Definition',TO_TIMESTAMP('2009-09-07 00:09:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:09:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53239 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:09:04 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,5, 10, 53239)
;

-- Sep 7, 2009 12:09:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53240,0,53095,'W',TO_TIMESTAMP('2009-09-07 00:09:04','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Inbound/Outbound Rule','EE03','Y','N','N','N','Inbound/Outbound Rule',TO_TIMESTAMP('2009-09-07 00:09:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:09:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53240 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:09:09 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,3, 10, 53240)
;

-- Sep 7, 2009 12:09:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53241,0,53096,'W',TO_TIMESTAMP('2009-09-07 00:09:09','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Warehouse Inbound/Outbound Strategy','EE03','Y','N','N','N','Inbound/Outbound Strategy',TO_TIMESTAMP('2009-09-07 00:09:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 7, 2009 12:09:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53241 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 7, 2009 12:09:17 AM ECT
-- Warehouse Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53235,4, 10, 53241)
;

-- Sep 7, 2009 12:09:17 AM ECT
-- Warehouse Management
UPDATE AD_Workflow SET AD_WF_Node_ID=50094, IsValid='Y',Updated=TO_TIMESTAMP('2009-09-07 00:09:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50019
;


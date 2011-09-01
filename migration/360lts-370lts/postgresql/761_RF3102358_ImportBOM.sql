-- Oct 25, 2010 7:23:19 PM CDT
-- Import BOM
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53129,TO_TIMESTAMP('2010-10-25 19:23:16','YYYY-MM-DD HH24:MI:SS'),0,'Import Product BOM','EE01','Y','N','N','Y','Import Product BOM','N',TO_TIMESTAMP('2010-10-25 19:23:16','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Oct 25, 2010 7:23:19 PM CDT
-- Import BOM
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53129 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Oct 25, 2010 7:23:21 PM CDT
-- Import BOM
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53292,53129,'3',TO_TIMESTAMP('2010-10-25 19:23:19','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','Y','N','N','N','Import Product BOM','L','I_Product_BOM',TO_TIMESTAMP('2010-10-25 19:23:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:23:21 PM CDT
-- Import BOM
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53292 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Oct 25, 2010 7:23:22 PM CDT
-- Import BOM
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53405,TO_TIMESTAMP('2010-10-25 19:23:21','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table I_Product_BOM',1,'Y','N','Y','Y','I_Product_BOM','N',1000000,TO_TIMESTAMP('2010-10-25 19:23:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:23:24 PM CDT
-- Import BOM
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54337,0,'I_Product_BOM_ID',TO_TIMESTAMP('2010-10-25 19:23:22','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','I_Product_BOM_ID','I_Product_BOM_ID',TO_TIMESTAMP('2010-10-25 19:23:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:23:24 PM CDT
-- Import BOM
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54337 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 25, 2010 7:23:24 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Oct 25, 2010 7:23:24 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Oct 25, 2010 7:23:26 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59991,54337,0,13,53292,'I_Product_BOM_ID',TO_TIMESTAMP('2010-10-25 19:23:24','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','I_Product_BOM_ID',TO_TIMESTAMP('2010-10-25 19:23:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:26 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59991 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:26 PM CDT
-- Import BOM
CREATE TABLE I_Product_BOM (I_Product_BOM_ID NUMERIC(10) NOT NULL, CONSTRAINT I_Product_BOM_Key PRIMARY KEY (I_Product_BOM_ID))
;

-- Oct 25, 2010 7:23:27 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2010-10-25 19:23:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Oct 25, 2010 7:23:27 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Oct 25, 2010 7:23:28 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Oct 25, 2010 7:23:28 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Oct 25, 2010 7:23:30 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59992,113,0,19,53292,'AD_Org_ID',TO_TIMESTAMP('2010-10-25 19:23:28','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','Y','Organization',TO_TIMESTAMP('2010-10-25 19:23:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:30 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59992 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:30 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN AD_Org_ID NUMERIC(10) NOT NULL
;

-- Oct 25, 2010 7:23:30 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2010-10-25 19:23:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Oct 25, 2010 7:23:30 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Oct 25, 2010 7:23:32 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59993,102,0,19,53292,'AD_Client_ID',TO_TIMESTAMP('2010-10-25 19:23:30','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','Y','Client',TO_TIMESTAMP('2010-10-25 19:23:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:32 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59993 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:32 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN AD_Client_ID NUMERIC(10) NOT NULL
;

-- Oct 25, 2010 7:23:32 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2010-10-25 19:23:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Oct 25, 2010 7:23:32 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Oct 25, 2010 7:23:33 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Oct 25, 2010 7:23:33 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59994,245,0,16,53292,'Created',TO_TIMESTAMP('2010-10-25 19:23:33','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',10,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2010-10-25 19:23:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59994 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2010-10-25 19:23:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Oct 25, 2010 7:23:34 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Oct 25, 2010 7:23:35 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2010-10-25 19:23:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Oct 25, 2010 7:23:35 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Oct 25, 2010 7:23:35 PM CDT
-- Import BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59995,246,0,18,110,53292,'CreatedBy',TO_TIMESTAMP('2010-10-25 19:23:35','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2010-10-25 19:23:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59995 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2010-10-25 19:23:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Oct 25, 2010 7:23:37 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59996,348,0,20,53292,'IsActive',TO_TIMESTAMP('2010-10-25 19:23:37','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2010-10-25 19:23:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59996 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2010-10-25 19:23:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Oct 25, 2010 7:23:39 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Oct 25, 2010 7:23:41 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59997,607,0,15,53292,'Updated',TO_TIMESTAMP('2010-10-25 19:23:39','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',10,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2010-10-25 19:23:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:41 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59997 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:41 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Oct 25, 2010 7:23:41 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2010-10-25 19:23:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Oct 25, 2010 7:23:41 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Oct 25, 2010 7:23:42 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59998,608,0,18,110,53292,'UpdatedBy',TO_TIMESTAMP('2010-10-25 19:23:41','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2010-10-25 19:23:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:42 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59998 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:42 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Oct 25, 2010 7:23:43 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='I_ErrorMsg', Description='Messages generated from import process', EntityType='D', Help='The Import Error Message displays any error messages generated during the import process.', IsActive='Y', Name='Import Error Message', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Error Message',Updated=TO_TIMESTAMP('2010-10-25 19:23:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=912
;

-- Oct 25, 2010 7:23:43 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=912
;

-- Oct 25, 2010 7:23:43 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Oct 25, 2010 7:23:43 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Oct 25, 2010 7:23:45 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59999,912,0,10,53292,'I_ErrorMsg',TO_TIMESTAMP('2010-10-25 19:23:43','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process','EE01',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_TIMESTAMP('2010-10-25 19:23:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:45 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59999 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:45 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN I_ErrorMsg VARCHAR(2000) DEFAULT NULL 
;

-- Oct 25, 2010 7:23:45 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='I_IsImported', Description='Has this import been processed', EntityType='D', Help='The Imported check box indicates if this import has been processed.', IsActive='Y', Name='Imported', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Imported',Updated=TO_TIMESTAMP('2010-10-25 19:23:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=913
;

-- Oct 25, 2010 7:23:45 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=913
;

-- Oct 25, 2010 7:23:47 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60000,913,0,20,53292,'I_IsImported',TO_TIMESTAMP('2010-10-25 19:23:45','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed','EE01',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Imported',TO_TIMESTAMP('2010-10-25 19:23:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:47 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60000 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:47 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN I_IsImported CHAR(1) CHECK (I_IsImported IN ('Y','N')) NOT NULL
;

-- Oct 25, 2010 7:23:47 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2010-10-25 19:23:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1047
;

-- Oct 25, 2010 7:23:47 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60001,1047,0,20,53292,'Processed',TO_TIMESTAMP('2010-10-25 19:23:47','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE01',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','Y','N','Y','Processed',TO_TIMESTAMP('2010-10-25 19:23:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60001 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Processed CHAR(1) DEFAULT NULL CHECK (Processed IN ('Y','N'))
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2010-10-25 19:23:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:23:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Oct 25, 2010 7:23:49 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Oct 25, 2010 7:23:51 PM CDT
-- Import BOM
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53231,'3','org.eevolution.process.ImportProductBOM',TO_TIMESTAMP('2010-10-25 19:23:49','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','Import Product BOM','Y',0,0,TO_TIMESTAMP('2010-10-25 19:23:49','YYYY-MM-DD HH24:MI:SS'),0,'Import_ProductBOM',NULL)
;

-- Oct 25, 2010 7:23:51 PM CDT
-- Import BOM
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53231 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Oct 25, 2010 7:23:53 PM CDT
-- Import BOM
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2169,0,53231,53467,20,'IsImportOnlyNoErrors',TO_TIMESTAMP('2010-10-25 19:23:51','YYYY-MM-DD HH24:MI:SS'),0,'EE01',0,'Y','Y','N','N','IsImportOnlyNoErrors',10,TO_TIMESTAMP('2010-10-25 19:23:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:23:53 PM CDT
-- Import BOM
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53467 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Oct 25, 2010 7:23:55 PM CDT
-- Import BOM
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53231,53468,20,'DeleteOldImported',TO_TIMESTAMP('2010-10-25 19:23:53','YYYY-MM-DD HH24:MI:SS'),0,'U',0,'Y','Y','N','N','DeleteOldImported',20,TO_TIMESTAMP('2010-10-25 19:23:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:23:55 PM CDT
-- Import BOM
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53468 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Oct 25, 2010 7:23:57 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60002,524,0,53231,28,53292,'Processing',TO_TIMESTAMP('2010-10-25 19:23:55','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2010-10-25 19:23:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:57 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60002 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:57 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Oct 25, 2010 7:23:57 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='PP_Product_BOMLine_ID', Description='BOM Line', EntityType='EE01', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', IsActive='Y', Name='BOM Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Line',Updated=TO_TIMESTAMP('2010-10-25 19:23:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53254
;

-- Oct 25, 2010 7:23:57 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53254
;

-- Oct 25, 2010 7:23:59 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60003,53254,0,19,53292,'PP_Product_BOMLine_ID',TO_TIMESTAMP('2010-10-25 19:23:57','YYYY-MM-DD HH24:MI:SS'),0,'BOM Line','EE01',10,'The BOM Line is a unique identifier for a BOM line in an BOM.','Y','N','N','N','N','N','N','N','Y','N','N','BOM Line',TO_TIMESTAMP('2010-10-25 19:23:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:23:59 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60003 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:23:59 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN PP_Product_BOMLine_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:23:59 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ProductValue', Description='Key of the Product', EntityType='D', Help=NULL, IsActive='Y', Name='Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Key',Updated=TO_TIMESTAMP('2010-10-25 19:23:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1675
;

-- Oct 25, 2010 7:23:59 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1675
;

-- Oct 25, 2010 7:24:02 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60004,1675,0,10,53292,'ProductValue',TO_TIMESTAMP('2010-10-25 19:23:59','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product','EE01',60,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Key',TO_TIMESTAMP('2010-10-25 19:23:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:02 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60004 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:02 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN ProductValue VARCHAR(60) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:02 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2010-10-25 19:24:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=454
;

-- Oct 25, 2010 7:24:02 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Oct 25, 2010 7:24:04 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60005,454,0,19,53292,'M_Product_ID',TO_TIMESTAMP('2010-10-25 19:24:02','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_TIMESTAMP('2010-10-25 19:24:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:04 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60005 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:04 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:04 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='X12DE355', Description='UOM EDI X12 Code', EntityType='D', Help='The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)', IsActive='Y', Name='UOM Code', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM Code',Updated=TO_TIMESTAMP('2010-10-25 19:24:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=634
;

-- Oct 25, 2010 7:24:04 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=634
;

-- Oct 25, 2010 7:24:06 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60006,634,0,10,53292,'X12DE355',TO_TIMESTAMP('2010-10-25 19:24:05','YYYY-MM-DD HH24:MI:SS'),0,'UOM EDI X12 Code','EE01',60,'The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)','Y','N','N','N','N','N','N','N','Y','N','Y','UOM Code',TO_TIMESTAMP('2010-10-25 19:24:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:06 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60006 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:06 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN X12DE355 VARCHAR(60) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:06 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_TIMESTAMP('2010-10-25 19:24:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=215
;

-- Oct 25, 2010 7:24:06 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Oct 25, 2010 7:24:07 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60007,215,0,19,53292,'C_UOM_ID',TO_TIMESTAMP('2010-10-25 19:24:06','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','Y','UOM',TO_TIMESTAMP('2010-10-25 19:24:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:07 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60007 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:07 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN C_UOM_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:08 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_TIMESTAMP('2010-10-25 19:24:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=620
;

-- Oct 25, 2010 7:24:08 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Oct 25, 2010 7:24:10 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60008,620,0,10,53292,'Value',TO_TIMESTAMP('2010-10-25 19:24:08','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE01',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2010-10-25 19:24:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:10 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60008 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:10 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Value VARCHAR(40) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:10 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_TIMESTAMP('2010-10-25 19:24:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- Oct 25, 2010 7:24:10 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Oct 25, 2010 7:24:13 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60009,469,0,10,53292,'Name',TO_TIMESTAMP('2010-10-25 19:24:10','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','N','N','N','N','N','Y','N','Y','Name',TO_TIMESTAMP('2010-10-25 19:24:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:13 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60009 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:13 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Name VARCHAR(60) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:13 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ValidFrom', Description='Valid from including this date (first day)', EntityType='D', Help='The Valid From date indicates the first day of a date range', IsActive='Y', Name='Valid from', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Valid from',Updated=TO_TIMESTAMP('2010-10-25 19:24:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=617
;

-- Oct 25, 2010 7:24:13 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=617
;

-- Oct 25, 2010 7:24:15 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60010,617,0,15,53292,'ValidFrom',TO_TIMESTAMP('2010-10-25 19:24:13','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',7,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid from',TO_TIMESTAMP('2010-10-25 19:24:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:15 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60010 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:15 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN ValidFrom TIMESTAMP DEFAULT NULL 
;

-- Oct 25, 2010 7:24:15 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BOMType', Description='Type of BOM', EntityType='D', Help='The type of Bills of Materials determines the state', IsActive='Y', Name='BOM Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Type',Updated=TO_TIMESTAMP('2010-10-25 19:24:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2030
;

-- Oct 25, 2010 7:24:15 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2030
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_BOM Type', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=347
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=347
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Current Active', Value='A',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=742
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=742
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='EE01', IsActive='Y', Name='Product Configure', Value='C',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53242
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53242
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Future', Value='F',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=746
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=746
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Maintenance', Value='M',Updated=TO_TIMESTAMP('2010-10-25 19:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=747
;

-- Oct 25, 2010 7:24:16 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=747
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Make-To-Order', Value='O',Updated=TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=743
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=743
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Previous', Value='P',Updated=TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=744
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=744
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Repair', Value='R',Updated=TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=748
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=748
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Previous, Spare', Value='S',Updated=TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=745
;

-- Oct 25, 2010 7:24:17 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=745
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60011,2030,0,17,347,53292,'BOMType',TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),0,'Type of BOM','EE01',2,'The type of Bills of Materials determines the state','Y','N','N','N','N','N','N','N','Y','N','Y','BOM Type',TO_TIMESTAMP('2010-10-25 19:24:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60011 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN BOMType VARCHAR(2) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BOMUse', Description='The use of the Bill of Material', EntityType='D', Help='By default the Master BOM is used, if the alternatives are not defined', IsActive='Y', Name='BOM Use', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Use',Updated=TO_TIMESTAMP('2010-10-25 19:24:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2784
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2784
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_BOM Use', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 19:24:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=348
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=348
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Master', Value='A',Updated=TO_TIMESTAMP('2010-10-25 19:24:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=749
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=749
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Engineering', Value='E',Updated=TO_TIMESTAMP('2010-10-25 19:24:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=750
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=750
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Manufacturing', Value='M',Updated=TO_TIMESTAMP('2010-10-25 19:24:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=751
;

-- Oct 25, 2010 7:24:19 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=751
;

-- Oct 25, 2010 7:24:20 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Planning', Value='P',Updated=TO_TIMESTAMP('2010-10-25 19:24:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=752
;

-- Oct 25, 2010 7:24:20 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=752
;

-- Oct 25, 2010 7:24:20 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Quality', Value='Q',Updated=TO_TIMESTAMP('2010-10-25 19:24:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53243
;

-- Oct 25, 2010 7:24:20 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53243
;

-- Oct 25, 2010 7:24:21 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60012,2784,0,17,348,53292,'BOMUse',TO_TIMESTAMP('2010-10-25 19:24:20','YYYY-MM-DD HH24:MI:SS'),0,'The use of the Bill of Material','EE01',2,'By default the Master BOM is used, if the alternatives are not defined','Y','N','N','N','N','N','N','N','Y','N','Y','BOM Use',TO_TIMESTAMP('2010-10-25 19:24:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:21 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60012 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:21 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN BOMUse VARCHAR(2) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:21 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2010-10-25 19:24:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- Oct 25, 2010 7:24:21 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Oct 25, 2010 7:24:22 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60013,275,0,10,53292,'Description',TO_TIMESTAMP('2010-10-25 19:24:21','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2010-10-25 19:24:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:22 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60013 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:22 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:22 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2010-10-25 19:24:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=290
;

-- Oct 25, 2010 7:24:22 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Oct 25, 2010 7:24:24 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60014,290,0,10,53292,'DocumentNo',TO_TIMESTAMP('2010-10-25 19:24:23','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',22,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Document No',TO_TIMESTAMP('2010-10-25 19:24:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:24 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60014 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:24 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN DocumentNo VARCHAR(22) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:24 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ValidTo', Description='Valid to including this date (last day)', EntityType='D', Help='The Valid To date indicates the last day of a date range', IsActive='Y', Name='Valid to', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Valid to',Updated=TO_TIMESTAMP('2010-10-25 19:24:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=618
;

-- Oct 25, 2010 7:24:24 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=618
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60015,618,0,15,53292,'ValidTo',TO_TIMESTAMP('2010-10-25 19:24:24','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',7,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid to',TO_TIMESTAMP('2010-10-25 19:24:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60015 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN ValidTo TIMESTAMP DEFAULT NULL 
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Help', Description='Comment or Hint', EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', Name='Comment/Help', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Comment',Updated=TO_TIMESTAMP('2010-10-25 19:24:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=326
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=326
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:24:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Oct 25, 2010 7:24:26 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Oct 25, 2010 7:24:28 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60016,326,0,14,53292,'Help',TO_TIMESTAMP('2010-10-25 19:24:26','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2010-10-25 19:24:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:28 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60016 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:28 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ComponentType', Description='Component Type for a Bill of Material or Formula', EntityType='EE01', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
', IsActive='Y', Name='Component Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Component Type',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53249
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53249
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_ComponentType', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53225
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53225
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='By-Product', Value='BY',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53257
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53257
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Component', Value='CO',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53258
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53258
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Co-Product', Value='CP',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53481
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53481
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Option', Value='OP',Updated=TO_TIMESTAMP('2010-10-25 19:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53263
;

-- Oct 25, 2010 7:24:29 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53263
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Phantom', Value='PH',Updated=TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53259
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53259
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Packing', Value='PK',Updated=TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53260
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53260
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Planning', Value='PL',Updated=TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53261
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53261
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Tools', Value='TL',Updated=TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53262
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53262
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Variant', Value='VA',Updated=TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53264
;

-- Oct 25, 2010 7:24:30 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53264
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60017,53249,0,17,53225,53292,'ComponentType',TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),0,'Component Type for a Bill of Material or Formula','EE01',2,'The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
','Y','N','N','N','N','N','N','N','Y','N','Y','Component Type',TO_TIMESTAMP('2010-10-25 19:24:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60017 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN ComponentType VARCHAR(2) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='QtyBOM', Description='Indicate the Quantity  use in this BOM', EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity',Updated=TO_TIMESTAMP('2010-10-25 19:24:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53255
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:24:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Oct 25, 2010 7:24:32 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Oct 25, 2010 7:24:34 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60018,53255,0,22,53292,'QtyBOM',TO_TIMESTAMP('2010-10-25 19:24:32','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity  use in this BOM','EE01',22,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity',TO_TIMESTAMP('2010-10-25 19:24:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:34 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60018 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:34 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN QtyBOM NUMERIC DEFAULT NULL 
;

-- Oct 25, 2010 7:24:34 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='QtyBatch', Description='Indicate the Quantity % use in this Formula', EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', IsActive='Y', Name='Quantity in %', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity in %',Updated=TO_TIMESTAMP('2010-10-25 19:24:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53256
;

-- Oct 25, 2010 7:24:34 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53256
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60019,53256,0,22,53292,'QtyBatch',TO_TIMESTAMP('2010-10-25 19:24:34','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity % use in this Formula','EE01',22,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity in %',TO_TIMESTAMP('2010-10-25 19:24:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60019 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN QtyBatch NUMERIC DEFAULT NULL 
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IssueMethod', Description='There are two methods for issue the components to Manufacturing Order', EntityType='EE01', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.', IsActive='Y', Name='Issue Method', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Issue Method',Updated=TO_TIMESTAMP('2010-10-25 19:24:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53253
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53253
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Issue Method', EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_Product_BOM IssueMethod', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 19:24:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53226
;

-- Oct 25, 2010 7:24:36 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53226
;

-- Oct 25, 2010 7:24:38 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60020,53253,0,17,53226,53292,'IssueMethod',TO_TIMESTAMP('2010-10-25 19:24:36','YYYY-MM-DD HH24:MI:SS'),0,'There are two methods for issue the components to Manufacturing Order','EE01',1,'Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.','Y','N','N','N','N','N','N','N','Y','N','Y','Issue Method',TO_TIMESTAMP('2010-10-25 19:24:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:38 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60020 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:38 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN IssueMethod CHAR(1) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:38 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsQtyPercentage', Description='Indicate that this component is based in % Quantity', EntityType='EE01', Help='Indicate that this component is based in % Quantity', IsActive='Y', Name='Is Qty Percentage', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is Qty Percentage',Updated=TO_TIMESTAMP('2010-10-25 19:24:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53252
;

-- Oct 25, 2010 7:24:38 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53252
;

-- Oct 25, 2010 7:24:40 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60021,53252,0,20,53292,'IsQtyPercentage',TO_TIMESTAMP('2010-10-25 19:24:38','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that this component is based in % Quantity','EE01',1,'Indicate that this component is based in % Quantity','Y','N','N','N','N','N','N','N','Y','N','Y','Is Qty Percentage',TO_TIMESTAMP('2010-10-25 19:24:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:40 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60021 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:41 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN IsQtyPercentage CHAR(1) DEFAULT NULL CHECK (IsQtyPercentage IN ('Y','N'))
;

-- Oct 25, 2010 7:24:41 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Product_BOM_Value', Description='Key of Product BOM', EntityType='U', Help=NULL, IsActive='Y', Name='Product BOM Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product BOM Key',Updated=TO_TIMESTAMP('2010-10-25 19:24:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=54119
;

-- Oct 25, 2010 7:24:41 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54119
;

-- Oct 25, 2010 7:24:43 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60022,54119,0,10,53292,'Product_BOM_Value',TO_TIMESTAMP('2010-10-25 19:24:41','YYYY-MM-DD HH24:MI:SS'),0,'Key of Product BOM','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Product BOM Key',TO_TIMESTAMP('2010-10-25 19:24:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:43 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60022 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:43 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Product_BOM_Value VARCHAR(40) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:43 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='OrgValue', Description='Key of the Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Key',Updated=TO_TIMESTAMP('2010-10-25 19:24:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2115
;

-- Oct 25, 2010 7:24:43 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2115
;

-- Oct 25, 2010 7:24:44 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60023,2115,0,10,53292,'OrgValue',TO_TIMESTAMP('2010-10-25 19:24:43','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Key',TO_TIMESTAMP('2010-10-25 19:24:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:44 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60023 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:44 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN OrgValue VARCHAR(40) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:46 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Product selection no summary', EntityType='D', Help=NULL, IsActive='Y', Name='M_Product (no summary)', ValidationType='T',Updated=TO_TIMESTAMP('2010-10-25 19:24:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=162
;

-- Oct 25, 2010 7:24:46 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=162
;

-- Oct 25, 2010 7:24:46 PM CDT
-- Import BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 208, AD_Display = 1410, AD_Key = 1402, isValueDisplayed = 'N', OrderByClause = 'M_Product.Value', EntityType ='D', WhereClause = 'M_Product.IsSummary=''N''' WHERE AD_Reference_ID = 162
;

-- Oct 25, 2010 7:24:48 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_ChangeNotice_ID', Description='Bill of Materials (Engineering) Change Notice (Version)', EntityType='D', Help=NULL, IsActive='Y', Name='Change Notice', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Change Notice',Updated=TO_TIMESTAMP('2010-10-25 19:24:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2783
;

-- Oct 25, 2010 7:24:48 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2783
;

-- Oct 25, 2010 7:24:50 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60025,2783,0,19,53292,'M_ChangeNotice_ID',TO_TIMESTAMP('2010-10-25 19:24:48','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials (Engineering) Change Notice (Version)','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Change Notice',TO_TIMESTAMP('2010-10-25 19:24:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:50 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60025 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:50 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN M_ChangeNotice_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:24:50 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='CostAllocationPerc', Description='Cost allocation percent in case of a co-product.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Cost Allocation Percent', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Allocation%',Updated=TO_TIMESTAMP('2010-10-25 19:24:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=54077
;

-- Oct 25, 2010 7:24:50 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54077
;

-- Oct 25, 2010 7:24:54 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60026,54077,0,22,53292,'CostAllocationPerc',TO_TIMESTAMP('2010-10-25 19:24:50','YYYY-MM-DD HH24:MI:SS'),0,'Cost allocation percent in case of a co-product.','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Cost Allocation Percent',TO_TIMESTAMP('2010-10-25 19:24:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:54 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60026 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:54 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN CostAllocationPerc NUMERIC DEFAULT NULL 
;

-- Oct 25, 2010 7:24:55 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Scrap', Description='Indicate the Scrap %  for calculate the Scrap Quantity', EntityType='EE01', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', IsActive='Y', Name='Scrap %', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Scrap %',Updated=TO_TIMESTAMP('2010-10-25 19:24:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53257
;

-- Oct 25, 2010 7:24:55 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60027,53257,0,22,53292,'Scrap',TO_TIMESTAMP('2010-10-25 19:24:55','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Scrap %  for calculate the Scrap Quantity','EE01',22,'Scrap is useful to determinate a rigth Standard Cost and management a good supply.','Y','N','N','N','N','N','N','N','Y','N','Y','Scrap %',TO_TIMESTAMP('2010-10-25 19:24:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60027 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Scrap NUMERIC DEFAULT NULL 
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Assay', Description='Indicated the Quantity Assay to use into Quality Order', EntityType='EE01', Help='Indicated the Quantity Assay to use into Quality Order', IsActive='Y', Name='Quantity Assay', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity Assay',Updated=TO_TIMESTAMP('2010-10-25 19:24:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53247
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53247
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:24:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Oct 25, 2010 7:24:57 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Oct 25, 2010 7:24:59 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60028,53247,0,29,53292,'Assay',TO_TIMESTAMP('2010-10-25 19:24:57','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order','EE01',22,'Indicated the Quantity Assay to use into Quality Order','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity Assay',TO_TIMESTAMP('2010-10-25 19:24:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:24:59 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60028 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:24:59 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN Assay NUMERIC DEFAULT NULL 
;

-- Oct 25, 2010 7:24:59 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BackflushGroup', Description='The Grouping Components to the Backflush', EntityType='EE01', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.', IsActive='Y', Name='Backflush Group', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Backflush Group',Updated=TO_TIMESTAMP('2010-10-25 19:24:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53248
;

-- Oct 25, 2010 7:24:59 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53248
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60029,53248,0,10,53292,'BackflushGroup',TO_TIMESTAMP('2010-10-25 19:24:59','YYYY-MM-DD HH24:MI:SS'),0,'The Grouping Components to the Backflush','EE01',20,'When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.','Y','N','N','N','N','N','N','N','Y','N','Y','Backflush Group',TO_TIMESTAMP('2010-10-25 19:24:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60029 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN BackflushGroup VARCHAR(20) DEFAULT NULL 
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='LeadTimeOffset', Description='Optional Lead Time offset before starting production', EntityType='D', Help='Optional Lead Time offset before starting production', IsActive='Y', Name='Lead Time Offset', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Lead Time Offset',Updated=TO_TIMESTAMP('2010-10-25 19:25:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2789
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2789
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 19:25:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Oct 25, 2010 7:25:01 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Oct 25, 2010 7:25:03 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60030,2789,0,11,53292,'LeadTimeOffset',TO_TIMESTAMP('2010-10-25 19:25:01','YYYY-MM-DD HH24:MI:SS'),0,'Optional Lead Time offset before starting production','EE01',10,'Optional Lead Time offset before starting production','Y','N','N','N','N','N','N','N','Y','N','Y','Lead Time Offset',TO_TIMESTAMP('2010-10-25 19:25:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:25:03 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60030 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:25:03 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN LeadTimeOffset NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:25:03 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsCritical', Description='Indicate that a Manufacturing Order can not begin without have this component', EntityType='EE01', Help='Indicate that a Manufacturing Order can not begin without have this component', IsActive='Y', Name='Is Critical Component', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is Critical Component',Updated=TO_TIMESTAMP('2010-10-25 19:25:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53251
;

-- Oct 25, 2010 7:25:03 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53251
;

-- Oct 25, 2010 7:25:05 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60031,53251,0,20,53292,'IsCritical',TO_TIMESTAMP('2010-10-25 19:25:03','YYYY-MM-DD HH24:MI:SS'),0,'N','Indicate that a Manufacturing Order can not begin without have this component','EE01',1,'Indicate that a Manufacturing Order can not begin without have this component','Y','N','N','N','N','N','N','N','Y','N','Y','Is Critical Component',TO_TIMESTAMP('2010-10-25 19:25:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:25:05 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60031 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:25:05 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN IsCritical CHAR(1) DEFAULT 'N' CHECK (IsCritical IN ('Y','N'))
;

-- Oct 25, 2010 7:25:05 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_BOMProduct_ID', Description='Bill of Material Component (Product)', EntityType='D', Help='The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.', IsActive='Y', Name='BOM Component', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Component',Updated=TO_TIMESTAMP('2010-10-25 19:25:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2786
;

-- Oct 25, 2010 7:25:05 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2786
;

-- Oct 25, 2010 7:25:06 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60032,2786,0,18,162,53292,'M_BOMProduct_ID',TO_TIMESTAMP('2010-10-25 19:25:05','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Material Component (Product)','EE01',10,'The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.','Y','N','N','N','N','N','N','N','Y','N','Y','BOM Component',TO_TIMESTAMP('2010-10-25 19:25:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:25:06 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60032 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:25:06 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN M_BOMProduct_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:25:07 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', EntityType='EE01', Help=NULL, IsActive='Y', Name='BOM & Formula', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM & Formula',Updated=TO_TIMESTAMP('2010-10-25 19:25:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53245
;

-- Oct 25, 2010 7:25:07 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53245
;

-- Oct 25, 2010 7:25:08 PM CDT
-- Import BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,60033,53245,0,19,53292,'PP_Product_BOM_ID',TO_TIMESTAMP('2010-10-25 19:25:07','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','BOM & Formula',TO_TIMESTAMP('2010-10-25 19:25:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 25, 2010 7:25:08 PM CDT
-- Import BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60033 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 25, 2010 7:25:08 PM CDT
-- Import BOM
ALTER TABLE I_Product_BOM ADD COLUMN PP_Product_BOM_ID NUMERIC(10) DEFAULT NULL 
;

-- Oct 25, 2010 7:25:11 PM CDT
-- Import BOM
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53350,53292,53129,NULL,TO_TIMESTAMP('2010-10-25 19:25:08','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','Y','N','Y','N','N','Import Product BOM','N',10,0,TO_TIMESTAMP('2010-10-25 19:25:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:11 PM CDT
-- Import BOM
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53350 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Oct 25, 2010 7:25:13 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59996,59862,0,53350,TO_TIMESTAMP('2010-10-25 19:25:11','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','Active',0,0,TO_TIMESTAMP('2010-10-25 19:25:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:13 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59862 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:14 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59991,59863,0,53350,TO_TIMESTAMP('2010-10-25 19:25:13','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','N','N','N','N','N','I_Product_BOM_ID',0,0,TO_TIMESTAMP('2010-10-25 19:25:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:14 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59863 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:15 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60000,59864,0,53350,TO_TIMESTAMP('2010-10-25 19:25:14','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed',1,'EE01','The Imported check box indicates if this import has been processed.','Y','Y','Y','N','N','Y','N','Imported',10,0,TO_TIMESTAMP('2010-10-25 19:25:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:15 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59864 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:17 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60033,59865,0,53350,TO_TIMESTAMP('2010-10-25 19:25:15','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',10,'EE01','Y','Y','Y','N','N','Y','N','BOM & Formula',20,0,TO_TIMESTAMP('2010-10-25 19:25:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:17 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59865 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:20 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60003,59866,0,53350,TO_TIMESTAMP('2010-10-25 19:25:17','YYYY-MM-DD HH24:MI:SS'),0,'BOM Line',10,'EE01','The BOM Line is a unique identifier for a BOM line in an BOM.','Y','Y','Y','N','N','Y','Y','BOM Line',30,0,TO_TIMESTAMP('2010-10-25 19:25:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:20 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59866 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:21 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59999,59867,0,53350,TO_TIMESTAMP('2010-10-25 19:25:20','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process',2000,'EE01','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','N','N','Y','N','Import Error Message',40,0,TO_TIMESTAMP('2010-10-25 19:25:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:21 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59867 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:23 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59993,59868,0,53350,TO_TIMESTAMP('2010-10-25 19:25:21','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',50,0,TO_TIMESTAMP('2010-10-25 19:25:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:23 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59868 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:25 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59992,59869,0,53350,TO_TIMESTAMP('2010-10-25 19:25:23','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',60,0,TO_TIMESTAMP('2010-10-25 19:25:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:25 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59869 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:26 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60023,59870,0,53350,TO_TIMESTAMP('2010-10-25 19:25:25','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization',20,'EE01','Y','Y','Y','N','N','N','N','Org Key',70,0,TO_TIMESTAMP('2010-10-25 19:25:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:26 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59870 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:29 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60004,59871,0,53350,TO_TIMESTAMP('2010-10-25 19:25:26','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product',20,'EE01','Y','Y','Y','N','N','N','N','Product Key',80,0,TO_TIMESTAMP('2010-10-25 19:25:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:29 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59871 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:31 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60005,59872,0,53350,TO_TIMESTAMP('2010-10-25 19:25:29','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',10,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','Y','Product',90,0,TO_TIMESTAMP('2010-10-25 19:25:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:31 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59872 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:33 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60008,59873,0,53350,TO_TIMESTAMP('2010-10-25 19:25:31','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',20,'EE01','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',100,0,TO_TIMESTAMP('2010-10-25 19:25:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:33 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59873 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:35 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60009,59874,0,53350,TO_TIMESTAMP('2010-10-25 19:25:33','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',110,0,TO_TIMESTAMP('2010-10-25 19:25:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:35 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59874 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:37 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60014,59875,0,53350,TO_TIMESTAMP('2010-10-25 19:25:35','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document',22,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','Y','Document No',120,0,TO_TIMESTAMP('2010-10-25 19:25:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:37 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59875 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:39 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60010,59876,0,53350,TO_TIMESTAMP('2010-10-25 19:25:37','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)',7,'EE01','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','Valid from',130,0,TO_TIMESTAMP('2010-10-25 19:25:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:39 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59876 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:41 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60015,59877,0,53350,TO_TIMESTAMP('2010-10-25 19:25:39','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)',7,'EE01','The Valid To date indicates the last day of a date range','Y','Y','Y','N','N','N','Y','Valid to',140,0,TO_TIMESTAMP('2010-10-25 19:25:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:41 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59877 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:42 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60011,59878,0,53350,TO_TIMESTAMP('2010-10-25 19:25:41','YYYY-MM-DD HH24:MI:SS'),0,'Type of BOM',20,'EE01','The type of Bills of Materials determines the state','Y','Y','Y','N','N','N','N','BOM Type',150,0,TO_TIMESTAMP('2010-10-25 19:25:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:42 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59878 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:44 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60012,59879,0,53350,TO_TIMESTAMP('2010-10-25 19:25:42','YYYY-MM-DD HH24:MI:SS'),0,'The use of the Bill of Material',20,'EE01','By default the Master BOM is used, if the alternatives are not defined','Y','Y','Y','N','N','N','Y','BOM Use',160,0,TO_TIMESTAMP('2010-10-25 19:25:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:44 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59879 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:45 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60013,59880,0,53350,TO_TIMESTAMP('2010-10-25 19:25:44','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',170,0,TO_TIMESTAMP('2010-10-25 19:25:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:45 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59880 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:49 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60016,59882,0,53350,TO_TIMESTAMP('2010-10-25 19:25:47','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',180,0,TO_TIMESTAMP('2010-10-25 19:25:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:49 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59882 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:51 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60022,59883,0,53350,TO_TIMESTAMP('2010-10-25 19:25:49','YYYY-MM-DD HH24:MI:SS'),0,'Key of Product BOM',20,'EE01','Y','Y','Y','N','N','N','N','Product BOM Key',190,0,TO_TIMESTAMP('2010-10-25 19:25:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:51 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59883 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:52 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60032,59884,0,53350,TO_TIMESTAMP('2010-10-25 19:25:51','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Material Component (Product)',10,'EE01','The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.','Y','Y','Y','N','N','N','Y','BOM Component',200,0,TO_TIMESTAMP('2010-10-25 19:25:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:52 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59884 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:54 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60025,59885,0,53350,TO_TIMESTAMP('2010-10-25 19:25:52','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials (Engineering) Change Notice (Version)',10,'EE01','Y','Y','Y','N','N','N','N','Change Notice',210,0,TO_TIMESTAMP('2010-10-25 19:25:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:54 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59885 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:56 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60006,59886,0,53350,TO_TIMESTAMP('2010-10-25 19:25:54','YYYY-MM-DD HH24:MI:SS'),0,'UOM EDI X12 Code',20,'EE01','The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)','Y','Y','Y','N','N','N','N','UOM Code',220,0,TO_TIMESTAMP('2010-10-25 19:25:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:56 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59886 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:25:58 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60007,59887,0,53350,TO_TIMESTAMP('2010-10-25 19:25:56','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure',10,'EE01','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','Y','UOM',230,0,TO_TIMESTAMP('2010-10-25 19:25:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:25:58 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59887 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:01 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60017,59888,0,53350,TO_TIMESTAMP('2010-10-25 19:25:58','YYYY-MM-DD HH24:MI:SS'),0,'Component Type for a Bill of Material or Formula',20,'EE01','The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
','Y','Y','Y','N','N','N','N','Component Type',240,0,TO_TIMESTAMP('2010-10-25 19:25:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:01 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59888 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:02 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60021,59889,0,53350,TO_TIMESTAMP('2010-10-25 19:26:01','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that this component is based in % Quantity',1,'EE01','Indicate that this component is based in % Quantity','Y','Y','Y','N','N','N','N','Is Qty Percentage',250,0,TO_TIMESTAMP('2010-10-25 19:26:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:02 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59889 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:04 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60031,59890,0,53350,TO_TIMESTAMP('2010-10-25 19:26:02','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that a Manufacturing Order can not begin without have this component',1,'EE01','Indicate that a Manufacturing Order can not begin without have this component','Y','Y','Y','N','N','N','Y','Is Critical Component',260,0,TO_TIMESTAMP('2010-10-25 19:26:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:04 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59890 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:06 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60018,59891,0,53350,TO_TIMESTAMP('2010-10-25 19:26:04','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity  use in this BOM',22,'EE01','Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','Y','Y','N','N','N','N','Quantity',270,0,TO_TIMESTAMP('2010-10-25 19:26:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:06 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59891 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:08 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60019,59892,0,53350,TO_TIMESTAMP('2010-10-25 19:26:06','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity % use in this Formula',22,'@IsQtyPercentage@=''Y''','EE01','Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','Y','Y','N','N','N','Y','Quantity in %',280,0,TO_TIMESTAMP('2010-10-25 19:26:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:08 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59892 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:10 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60026,59893,0,53350,TO_TIMESTAMP('2010-10-25 19:26:08','YYYY-MM-DD HH24:MI:SS'),0,'Cost allocation percent in case of a co-product.',22,'EE01','Y','Y','Y','N','N','N','N','Cost Allocation Percent',290,0,TO_TIMESTAMP('2010-10-25 19:26:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:10 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59893 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:11 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60027,59894,0,53350,TO_TIMESTAMP('2010-10-25 19:26:10','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Scrap %  for calculate the Scrap Quantity',22,'EE01','Scrap is useful to determinate a rigth Standard Cost and management a good supply.','Y','Y','Y','N','N','N','N','Scrap %',300,0,TO_TIMESTAMP('2010-10-25 19:26:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:11 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59894 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:12 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60028,59895,0,53350,TO_TIMESTAMP('2010-10-25 19:26:11','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order',22,'EE01','Indicated the Quantity Assay to use into Quality Order','Y','Y','Y','N','N','N','Y','Quantity Assay',310,0,TO_TIMESTAMP('2010-10-25 19:26:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:12 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59895 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:14 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60020,59896,0,53350,TO_TIMESTAMP('2010-10-25 19:26:12','YYYY-MM-DD HH24:MI:SS'),0,'There are two methods for issue the components to Manufacturing Order',20,'EE01','Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.','Y','Y','Y','N','N','N','N','Issue Method',320,0,TO_TIMESTAMP('2010-10-25 19:26:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:14 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59896 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:16 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60029,59897,0,53350,TO_TIMESTAMP('2010-10-25 19:26:14','YYYY-MM-DD HH24:MI:SS'),0,'The Grouping Components to the Backflush',20,'EE01','When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.','Y','Y','Y','N','N','N','Y','Backflush Group',330,0,TO_TIMESTAMP('2010-10-25 19:26:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:16 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59897 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:18 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60030,59898,0,53350,TO_TIMESTAMP('2010-10-25 19:26:16','YYYY-MM-DD HH24:MI:SS'),0,'Optional Lead Time offset before starting production',10,'EE01','Optional Lead Time offset before starting production','Y','Y','Y','N','N','N','N','Lead Time Offset',340,0,TO_TIMESTAMP('2010-10-25 19:26:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:18 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59898 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:20 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60002,59899,0,53350,TO_TIMESTAMP('2010-10-25 19:26:18','YYYY-MM-DD HH24:MI:SS'),0,20,'EE01','Y','Y','Y','N','N','N','N','Process Now',350,0,TO_TIMESTAMP('2010-10-25 19:26:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:20 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59899 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:21 PM CDT
-- Import BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60001,59900,0,53350,TO_TIMESTAMP('2010-10-25 19:26:20','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','Y','Y','Processed',360,0,TO_TIMESTAMP('2010-10-25 19:26:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:21 PM CDT
-- Import BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59900 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 25, 2010 7:26:22 PM CDT
-- Import BOM
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53303,0,53129,'W',TO_TIMESTAMP('2010-10-25 19:26:21','YYYY-MM-DD HH24:MI:SS'),0,'Import Product BOM','EE01','Y','N','N','N','Import Product BOM',TO_TIMESTAMP('2010-10-25 19:26:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2010 7:26:23 PM CDT
-- Import BOM
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53303 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Oct 25, 2010 7:26:23 PM CDT
-- Import BOM
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 163,4, 10, 53303)
;

-- Oct 25, 2010 10:28:42 PM CDT
-- Import BOM
UPDATE AD_Window SET Description='Import Product BOM', EntityType='EE01', Help=NULL, IsActive='Y', IsBetaFunctionality='N', IsDefault='N', IsSOTrx='Y', Name='Import Product BOM', Processing='N', WindowType='M',Updated=TO_TIMESTAMP('2010-10-25 22:28:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53129
;

-- Oct 25, 2010 10:28:42 PM CDT
-- Import BOM
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53129
;

-- Oct 25, 2010 10:28:44 PM CDT
-- Import BOM
UPDATE AD_Table SET AD_Window_ID=53129, AccessLevel='3', Description=NULL, EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Import Product BOM', ReplicationType='L', TableName='I_Product_BOM',Updated=TO_TIMESTAMP('2010-10-25 22:28:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53292
;

-- Oct 25, 2010 10:28:44 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='I_Product_BOM_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='I_Product_BOM_ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='I_Product_BOM_ID',Updated=TO_TIMESTAMP('2010-10-25 22:28:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54337
;

-- Oct 25, 2010 10:28:44 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54337
;

-- Oct 25, 2010 10:28:45 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=13
;

-- Oct 25, 2010 10:28:45 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Oct 25, 2010 10:28:46 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=54337, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='I_Product_BOM_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=10, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='I_Product_BOM_ID', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59991
;

-- Oct 25, 2010 10:28:46 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='I_Product_BOM_ID', Description=NULL, Help=NULL WHERE AD_Column_ID=59991 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:28:47 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2010-10-25 22:28:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Oct 25, 2010 10:28:47 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Oct 25, 2010 10:28:48 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Oct 25, 2010 10:28:48 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Oct 25, 2010 10:28:48 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=10, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59992
;

-- Oct 25, 2010 10:28:49 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2010-10-25 22:28:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Oct 25, 2010 10:28:49 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Oct 25, 2010 10:28:50 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=10, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59993
;

-- Oct 25, 2010 10:28:50 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2010-10-25 22:28:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- Oct 25, 2010 10:28:50 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Oct 25, 2010 10:28:50 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Oct 25, 2010 10:28:50 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=10, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59994
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2010-10-25 22:28:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2010-10-25 22:28:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Oct 25, 2010 10:28:51 PM CDT
-- Import BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Oct 25, 2010 10:28:52 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=10, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59995
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2010-10-25 22:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59996
;

-- Oct 25, 2010 10:28:53 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2010-10-25 22:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=10, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59997
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2010-10-25 22:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- Oct 25, 2010 10:28:54 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Oct 25, 2010 10:28:55 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=10, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59998
;

-- Oct 25, 2010 10:28:55 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='I_ErrorMsg', Description='Messages generated from import process', EntityType='D', Help='The Import Error Message displays any error messages generated during the import process.', IsActive='Y', Name='Import Error Message', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Error Message',Updated=TO_TIMESTAMP('2010-10-25 22:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=912
;

-- Oct 25, 2010 10:28:55 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=912
;

-- Oct 25, 2010 10:28:55 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Oct 25, 2010 10:28:55 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Oct 25, 2010 10:28:56 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=912, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='I_ErrorMsg', ColumnSQL=NULL, DefaultValue=NULL, Description='Messages generated from import process', EntityType='EE01', FieldLength=2000, Help='The Import Error Message displays any error messages generated during the import process.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Import Error Message', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59999
;

-- Oct 25, 2010 10:28:56 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='I_IsImported', Description='Has this import been processed', EntityType='D', Help='The Imported check box indicates if this import has been processed.', IsActive='Y', Name='Imported', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Imported',Updated=TO_TIMESTAMP('2010-10-25 22:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=913
;

-- Oct 25, 2010 10:28:56 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=913
;

-- Oct 25, 2010 10:28:56 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=913, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='I_IsImported', ColumnSQL=NULL, DefaultValue=NULL, Description='Has this import been processed', EntityType='EE01', FieldLength=1, Help='The Imported check box indicates if this import has been processed.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Imported', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60000
;

-- Oct 25, 2010 10:28:57 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2010-10-25 22:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1047
;

-- Oct 25, 2010 10:28:57 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Oct 25, 2010 10:28:57 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=1047, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processed', ColumnSQL=NULL, DefaultValue=NULL, Description='The document has been processed', EntityType='EE01', FieldLength=1, Help='The Processed checkbox indicates that a document has been processed.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Processed', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60001
;

-- Oct 25, 2010 10:28:57 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2010-10-25 22:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=524
;

-- Oct 25, 2010 10:28:57 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Oct 25, 2010 10:28:58 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:28:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=28
;

-- Oct 25, 2010 10:28:58 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Oct 25, 2010 10:28:58 PM CDT
-- Import BOM
UPDATE AD_Process SET AccessLevel='3', Classname='org.eevolution.process.ImportProductBOM', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', IsBetaFunctionality='N', IsDirectPrint='N', IsReport='N', JasperReport=NULL, Name='Import Product BOM', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='Import_ProductBOM', WorkflowValue=NULL,Updated=TO_TIMESTAMP('2010-10-25 22:28:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53231
;

-- Oct 25, 2010 10:28:58 PM CDT
-- Import BOM
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53231
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Process_Para SET AD_Element_ID=2169, AD_Process_ID=53231, AD_Reference_ID=20, ColumnName='IsImportOnlyNoErrors', DefaultValue=NULL, DefaultValue2=NULL, Description=NULL, EntityType='EE01', FieldLength=0, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='IsImportOnlyNoErrors', SeqNo=10, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_TIMESTAMP('2010-10-25 22:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53467
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53467
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Process_Para SET AD_Element_ID=1922, AD_Process_ID=53231, AD_Reference_ID=20, ColumnName='DeleteOldImported', DefaultValue=NULL, DefaultValue2=NULL, Description=NULL, EntityType='U', FieldLength=0, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='DeleteOldImported', SeqNo=20, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_TIMESTAMP('2010-10-25 22:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53468
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53468
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=524, AD_Process_ID=53231, AD_Reference_ID=28, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processing', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=1, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Now', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60002
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=60002 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='PP_Product_BOMLine_ID', Description='BOM Line', EntityType='EE01', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', IsActive='Y', Name='BOM Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Line',Updated=TO_TIMESTAMP('2010-10-25 22:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53254
;

-- Oct 25, 2010 10:28:59 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53254
;

-- Oct 25, 2010 10:29:00 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53254, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Product_BOMLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='BOM Line', EntityType='EE01', FieldLength=10, Help='The BOM Line is a unique identifier for a BOM line in an BOM.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='BOM Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60003
;

-- Oct 25, 2010 10:29:00 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ProductValue', Description='Key of the Product', EntityType='D', Help=NULL, IsActive='Y', Name='Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Key',Updated=TO_TIMESTAMP('2010-10-25 22:29:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1675
;

-- Oct 25, 2010 10:29:00 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1675
;

-- Oct 25, 2010 10:29:00 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=1675, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ProductValue', ColumnSQL=NULL, DefaultValue=NULL, Description='Key of the Product', EntityType='EE01', FieldLength=60, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product Key', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60004
;

-- Oct 25, 2010 10:29:00 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Product Key', Description='Key of the Product', Help=NULL WHERE AD_Column_ID=60004 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:01 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2010-10-25 22:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Oct 25, 2010 10:29:01 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Oct 25, 2010 10:29:01 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=454, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product, Service, Item', EntityType='EE01', FieldLength=10, Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60005
;

-- Oct 25, 2010 10:29:01 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='X12DE355', Description='UOM EDI X12 Code', EntityType='D', Help='The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)', IsActive='Y', Name='UOM Code', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM Code',Updated=TO_TIMESTAMP('2010-10-25 22:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=634
;

-- Oct 25, 2010 10:29:01 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=634
;

-- Oct 25, 2010 10:29:02 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=634, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='X12DE355', ColumnSQL=NULL, DefaultValue=NULL, Description='UOM EDI X12 Code', EntityType='EE01', FieldLength=60, Help='The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='UOM Code', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60006
;

-- Oct 25, 2010 10:29:02 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_TIMESTAMP('2010-10-25 22:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=215
;

-- Oct 25, 2010 10:29:02 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Oct 25, 2010 10:29:03 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=215, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_UOM_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Unit of Measure', EntityType='EE01', FieldLength=10, Help='The UOM defines a unique non monetary Unit of Measure', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='UOM', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60007
;

-- Oct 25, 2010 10:29:03 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_TIMESTAMP('2010-10-25 22:29:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=620
;

-- Oct 25, 2010 10:29:03 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Oct 25, 2010 10:29:03 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=620, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Value', ColumnSQL=NULL, DefaultValue=NULL, Description='Search key for the record in the format required - must be unique', EntityType='EE01', FieldLength=40, Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Search Key', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60008
;

-- Oct 25, 2010 10:29:04 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_TIMESTAMP('2010-10-25 22:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=469
;

-- Oct 25, 2010 10:29:04 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Oct 25, 2010 10:29:04 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='EE01', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60009
;

-- Oct 25, 2010 10:29:04 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ValidFrom', Description='Valid from including this date (first day)', EntityType='D', Help='The Valid From date indicates the first day of a date range', IsActive='Y', Name='Valid from', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Valid from',Updated=TO_TIMESTAMP('2010-10-25 22:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=617
;

-- Oct 25, 2010 10:29:04 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=617
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=617, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ValidFrom', ColumnSQL=NULL, DefaultValue=NULL, Description='Valid from including this date (first day)', EntityType='EE01', FieldLength=7, Help='The Valid From date indicates the first day of a date range', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valid from', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60010
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BOMType', Description='Type of BOM', EntityType='D', Help='The type of Bills of Materials determines the state', IsActive='Y', Name='BOM Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Type',Updated=TO_TIMESTAMP('2010-10-25 22:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2030
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2030
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=17
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_BOM Type', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 22:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=347
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=347
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Current Active', Value='A',Updated=TO_TIMESTAMP('2010-10-25 22:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=742
;

-- Oct 25, 2010 10:29:05 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=742
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='EE01', IsActive='Y', Name='Product Configure', Value='C',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53242
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53242
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Future', Value='F',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=746
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=746
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Maintenance', Value='M',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=747
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=747
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Make-To-Order', Value='O',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=743
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=743
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Previous', Value='P',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=744
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=744
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='Y', Name='Repair', Value='R',Updated=TO_TIMESTAMP('2010-10-25 22:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=748
;

-- Oct 25, 2010 10:29:06 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=748
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=347, Description=NULL, EntityType='D', IsActive='N', Name='Previous, Spare', Value='S',Updated=TO_TIMESTAMP('2010-10-25 22:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=745
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=745
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2030, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=347, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='BOMType', ColumnSQL=NULL, DefaultValue=NULL, Description='Type of BOM', EntityType='EE01', FieldLength=2, Help='The type of Bills of Materials determines the state', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='BOM Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60011
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BOMUse', Description='The use of the Bill of Material', EntityType='D', Help='By default the Master BOM is used, if the alternatives are not defined', IsActive='Y', Name='BOM Use', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Use',Updated=TO_TIMESTAMP('2010-10-25 22:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2784
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2784
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_BOM Use', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 22:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=348
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=348
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Master', Value='A',Updated=TO_TIMESTAMP('2010-10-25 22:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=749
;

-- Oct 25, 2010 10:29:07 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=749
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Engineering', Value='E',Updated=TO_TIMESTAMP('2010-10-25 22:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=750
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=750
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Manufacturing', Value='M',Updated=TO_TIMESTAMP('2010-10-25 22:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=751
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=751
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Planning', Value='P',Updated=TO_TIMESTAMP('2010-10-25 22:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=752
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=752
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=348, Description=NULL, EntityType='D', IsActive='Y', Name='Quality', Value='Q',Updated=TO_TIMESTAMP('2010-10-25 22:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53243
;

-- Oct 25, 2010 10:29:08 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53243
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2784, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=348, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='BOMUse', ColumnSQL=NULL, DefaultValue=NULL, Description='The use of the Bill of Material', EntityType='EE01', FieldLength=2, Help='By default the Master BOM is used, if the alternatives are not defined', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='BOM Use', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60012
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2010-10-25 22:29:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=275
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='EE01', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60013
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2010-10-25 22:29:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- Oct 25, 2010 10:29:09 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Oct 25, 2010 10:29:10 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=290, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DocumentNo', ColumnSQL=NULL, DefaultValue=NULL, Description='Document sequence number of the document', EntityType='EE01', FieldLength=22, Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Document No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60014
;

-- Oct 25, 2010 10:29:10 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ValidTo', Description='Valid to including this date (last day)', EntityType='D', Help='The Valid To date indicates the last day of a date range', IsActive='Y', Name='Valid to', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Valid to',Updated=TO_TIMESTAMP('2010-10-25 22:29:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=618
;

-- Oct 25, 2010 10:29:10 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=618
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=618, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ValidTo', ColumnSQL=NULL, DefaultValue=NULL, Description='Valid to including this date (last day)', EntityType='EE01', FieldLength=7, Help='The Valid To date indicates the last day of a date range', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valid to', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60015
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Help', Description='Comment or Hint', EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', Name='Comment/Help', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Comment',Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=326
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=326
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=14
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=326, AD_Process_ID=NULL, AD_Reference_ID=14, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Help', ColumnSQL=NULL, DefaultValue=NULL, Description='Comment or Hint', EntityType='EE01', FieldLength=2000, Help='The Help field contains a hint, comment or help about the use of this item.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Comment/Help', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60016
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='ComponentType', Description='Component Type for a Bill of Material or Formula', EntityType='EE01', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
', IsActive='Y', Name='Component Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Component Type',Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53249
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53249
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_ComponentType', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 22:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53225
;

-- Oct 25, 2010 10:29:11 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53225
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='By-Product', Value='BY',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53257
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53257
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Component', Value='CO',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53258
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53258
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Co-Product', Value='CP',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53481
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53481
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Option', Value='OP',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53263
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53263
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Phantom', Value='PH',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53259
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53259
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Packing', Value='PK',Updated=TO_TIMESTAMP('2010-10-25 22:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53260
;

-- Oct 25, 2010 10:29:12 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53260
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Planning', Value='PL',Updated=TO_TIMESTAMP('2010-10-25 22:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53261
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53261
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Tools', Value='TL',Updated=TO_TIMESTAMP('2010-10-25 22:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53262
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53262
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Variant', Value='VA',Updated=TO_TIMESTAMP('2010-10-25 22:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53264
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53264
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53249, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=53225, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ComponentType', ColumnSQL=NULL, DefaultValue=NULL, Description='Component Type for a Bill of Material or Formula', EntityType='EE01', FieldLength=2, Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Component Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60017
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='QtyBOM', Description='Indicate the Quantity  use in this BOM', EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity',Updated=TO_TIMESTAMP('2010-10-25 22:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53255
;

-- Oct 25, 2010 10:29:13 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53255
;

-- Oct 25, 2010 10:29:14 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=22
;

-- Oct 25, 2010 10:29:14 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Oct 25, 2010 10:29:17 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53255, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyBOM', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicate the Quantity  use in this BOM', EntityType='EE01', FieldLength=22, Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60018
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='QtyBatch', Description='Indicate the Quantity % use in this Formula', EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', IsActive='Y', Name='Quantity in %', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity in %',Updated=TO_TIMESTAMP('2010-10-25 22:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53256
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53256
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53256, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyBatch', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicate the Quantity % use in this Formula', EntityType='EE01', FieldLength=22, Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Quantity in %', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60019
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IssueMethod', Description='There are two methods for issue the components to Manufacturing Order', EntityType='EE01', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.', IsActive='Y', Name='Issue Method', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Issue Method',Updated=TO_TIMESTAMP('2010-10-25 22:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53253
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53253
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Issue Method', EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_Product_BOM IssueMethod', ValidationType='L',Updated=TO_TIMESTAMP('2010-10-25 22:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53226
;

-- Oct 25, 2010 10:29:18 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53226
;

-- Oct 25, 2010 10:29:19 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53253, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=53226, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IssueMethod', ColumnSQL=NULL, DefaultValue=NULL, Description='There are two methods for issue the components to Manufacturing Order', EntityType='EE01', FieldLength=1, Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Issue Method', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60020
;

-- Oct 25, 2010 10:29:19 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsQtyPercentage', Description='Indicate that this component is based in % Quantity', EntityType='EE01', Help='Indicate that this component is based in % Quantity', IsActive='Y', Name='Is Qty Percentage', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is Qty Percentage',Updated=TO_TIMESTAMP('2010-10-25 22:29:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53252
;

-- Oct 25, 2010 10:29:19 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53252
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53252, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsQtyPercentage', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicate that this component is based in % Quantity', EntityType='EE01', FieldLength=1, Help='Indicate that this component is based in % Quantity', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Is Qty Percentage', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60021
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Product_BOM_Value', Description='Key of Product BOM', EntityType='U', Help=NULL, IsActive='Y', Name='Product BOM Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product BOM Key',Updated=TO_TIMESTAMP('2010-10-25 22:29:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54119
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54119
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=54119, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Product_BOM_Value', ColumnSQL=NULL, DefaultValue=NULL, Description='Key of Product BOM', EntityType='EE01', FieldLength=40, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product BOM Key', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60022
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Product BOM Key', Description='Key of Product BOM', Help=NULL WHERE AD_Column_ID=60022 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='OrgValue', Description='Key of the Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Key',Updated=TO_TIMESTAMP('2010-10-25 22:29:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2115
;

-- Oct 25, 2010 10:29:20 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2115
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2115, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='OrgValue', ColumnSQL=NULL, DefaultValue=NULL, Description='Key of the Organization', EntityType='EE01', FieldLength=40, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Org Key', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60023
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Org Key', Description='Key of the Organization', Help=NULL WHERE AD_Column_ID=60023 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_BOMProduct_ID', Description='Bill of Material Component (Product)', EntityType='D', Help='The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.', IsActive='Y', Name='BOM Component', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM Component',Updated=TO_TIMESTAMP('2010-10-25 22:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2786
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2786
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Product selection no summary', EntityType='D', Help=NULL, IsActive='Y', Name='M_Product (no summary)', ValidationType='T',Updated=TO_TIMESTAMP('2010-10-25 22:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=162
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=162
;

-- Oct 25, 2010 10:29:21 PM CDT
-- Import BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 208, AD_Display = 1410, AD_Key = 1402, isValueDisplayed = 'N', OrderByClause = 'M_Product.Value', EntityType ='D', WhereClause = 'M_Product.IsSummary=''N''' WHERE AD_Reference_ID = 162
;

-- Oct 25, 2010 10:29:22 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2786, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=162, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_BOMProduct_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Bill of Material Component (Product)', EntityType='EE01', FieldLength=10, Help='The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='BOM Component', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60032
;

-- Oct 25, 2010 10:29:22 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='M_ChangeNotice_ID', Description='Bill of Materials (Engineering) Change Notice (Version)', EntityType='D', Help=NULL, IsActive='Y', Name='Change Notice', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Change Notice',Updated=TO_TIMESTAMP('2010-10-25 22:29:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2783
;

-- Oct 25, 2010 10:29:22 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2783
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2783, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_ChangeNotice_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Bill of Materials (Engineering) Change Notice (Version)', EntityType='EE01', FieldLength=10, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Change Notice', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60025
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Change Notice', Description='Bill of Materials (Engineering) Change Notice (Version)', Help=NULL WHERE AD_Column_ID=60025 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='CostAllocationPerc', Description='Cost allocation percent in case of a co-product.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Cost Allocation Percent', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Allocation%',Updated=TO_TIMESTAMP('2010-10-25 22:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54077
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54077
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=54077, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CostAllocationPerc', ColumnSQL=NULL, DefaultValue=NULL, Description='Cost allocation percent in case of a co-product.', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Cost Allocation Percent', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60026
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='Cost Allocation Percent', Description='Cost allocation percent in case of a co-product.', Help=NULL WHERE AD_Column_ID=60026 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Scrap', Description='Indicate the Scrap %  for calculate the Scrap Quantity', EntityType='EE01', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', IsActive='Y', Name='Scrap %', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Scrap %',Updated=TO_TIMESTAMP('2010-10-25 22:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53257
;

-- Oct 25, 2010 10:29:23 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53257, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Scrap', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicate the Scrap %  for calculate the Scrap Quantity', EntityType='EE01', FieldLength=22, Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Scrap %', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60027
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='Assay', Description='Indicated the Quantity Assay to use into Quality Order', EntityType='EE01', Help='Indicated the Quantity Assay to use into Quality Order', IsActive='Y', Name='Quantity Assay', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity Assay',Updated=TO_TIMESTAMP('2010-10-25 22:29:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53247
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53247
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:29:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Oct 25, 2010 10:29:24 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53247, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Assay', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicated the Quantity Assay to use into Quality Order', EntityType='EE01', FieldLength=22, Help='Indicated the Quantity Assay to use into Quality Order', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Quantity Assay', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60028
;

-- Oct 25, 2010 10:29:25 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='BackflushGroup', Description='The Grouping Components to the Backflush', EntityType='EE01', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.', IsActive='Y', Name='Backflush Group', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Backflush Group',Updated=TO_TIMESTAMP('2010-10-25 22:29:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53248
;

-- Oct 25, 2010 10:29:25 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53248
;

-- Oct 25, 2010 10:29:25 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53248, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='BackflushGroup', ColumnSQL=NULL, DefaultValue=NULL, Description='The Grouping Components to the Backflush', EntityType='EE01', FieldLength=20, Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Backflush Group', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60029
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='LeadTimeOffset', Description='Optional Lead Time offset before starting production', EntityType='D', Help='Optional Lead Time offset before starting production', IsActive='Y', Name='Lead Time Offset', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Lead Time Offset',Updated=TO_TIMESTAMP('2010-10-25 22:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2789
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2789
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2010-10-25 22:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=11
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=2789, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='LeadTimeOffset', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional Lead Time offset before starting production', EntityType='EE01', FieldLength=10, Help='Optional Lead Time offset before starting production', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Lead Time Offset', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60030
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='IsCritical', Description='Indicate that a Manufacturing Order can not begin without have this component', EntityType='EE01', Help='Indicate that a Manufacturing Order can not begin without have this component', IsActive='Y', Name='Is Critical Component', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is Critical Component',Updated=TO_TIMESTAMP('2010-10-25 22:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53251
;

-- Oct 25, 2010 10:29:26 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53251
;

-- Oct 25, 2010 10:29:27 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53251, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsCritical', ColumnSQL=NULL, DefaultValue='N', Description='Indicate that a Manufacturing Order can not begin without have this component', EntityType='EE01', FieldLength=1, Help='Indicate that a Manufacturing Order can not begin without have this component', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Is Critical Component', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60031
;

-- Oct 25, 2010 10:29:27 PM CDT
-- Import BOM
UPDATE AD_Element SET ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', EntityType='EE01', Help=NULL, IsActive='Y', Name='BOM & Formula', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM & Formula',Updated=TO_TIMESTAMP('2010-10-25 22:29:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53245
;

-- Oct 25, 2010 10:29:27 PM CDT
-- Import BOM
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53245
;

-- Oct 25, 2010 10:29:28 PM CDT
-- Import BOM
UPDATE AD_Column SET AD_Element_ID=53245, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53292, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Product_BOM_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='BOM & Formula', EntityType='EE01', FieldLength=10, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='BOM & Formula', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60033
;

-- Oct 25, 2010 10:29:28 PM CDT
-- Import BOM
UPDATE AD_Field SET Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Column_ID=60033 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 10:29:28 PM CDT
-- Import BOM
UPDATE AD_Tab SET AD_Table_ID=53292, AD_Window_ID=53129, CommitWarning=NULL, Description=NULL, EntityType='EE01', HasTree='N', Help=NULL, ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Import Product BOM', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2010-10-25 22:29:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53350
;

-- Oct 25, 2010 10:29:28 PM CDT
-- Import BOM
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53350
;

-- Oct 25, 2010 10:29:29 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=59996, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='EE01', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Active', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59862
;

-- Oct 25, 2010 10:29:29 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=59991, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description=NULL, DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='I_Product_BOM_ID', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59863
;

-- Oct 25, 2010 10:29:29 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59863
;

-- Oct 25, 2010 10:29:29 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60000, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Has this import been processed', DisplayLength=1, DisplayLogic=NULL, EntityType='EE01', Help='The Imported check box indicates if this import has been processed.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Imported', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59864
;

-- Oct 25, 2010 10:29:30 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60033, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='BOM & Formula', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='BOM & Formula', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59865
;

-- Oct 25, 2010 10:29:30 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59865
;

-- Oct 25, 2010 10:29:30 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60003, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='BOM Line', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='BOM Line', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59866
;

-- Oct 25, 2010 10:29:31 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=59999, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Messages generated from import process', DisplayLength=2000, DisplayLogic=NULL, EntityType='EE01', Help='The Import Error Message displays any error messages generated during the import process.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Import Error Message', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59867
;

-- Oct 25, 2010 10:29:31 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=59993, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Client/Tenant for this installation.', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59868
;

-- Oct 25, 2010 10:29:32 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=59992, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Organizational entity within client', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59869
;

-- Oct 25, 2010 10:29:32 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60023, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Key of the Organization', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Org Key', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59870
;

-- Oct 25, 2010 10:29:32 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59870
;

-- Oct 25, 2010 10:29:33 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60004, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Key of the Product', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Product Key', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59871
;

-- Oct 25, 2010 10:29:33 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59871
;

-- Oct 25, 2010 10:29:33 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60005, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Product, Service, Item', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Product', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59872
;

-- Oct 25, 2010 10:29:33 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60008, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Search key for the record in the format required - must be unique', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Search Key', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59873
;

-- Oct 25, 2010 10:29:34 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60009, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='EE01', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59874
;

-- Oct 25, 2010 10:29:34 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60014, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Document sequence number of the document', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Document No', SeqNo=120, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59875
;

-- Oct 25, 2010 10:29:34 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60010, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Valid from including this date (first day)', DisplayLength=7, DisplayLogic=NULL, EntityType='EE01', Help='The Valid From date indicates the first day of a date range', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Valid from', SeqNo=130, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59876
;

-- Oct 25, 2010 10:29:34 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60015, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Valid to including this date (last day)', DisplayLength=7, DisplayLogic=NULL, EntityType='EE01', Help='The Valid To date indicates the last day of a date range', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Valid to', SeqNo=140, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59877
;

-- Oct 25, 2010 10:29:35 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60011, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Type of BOM', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='The type of Bills of Materials determines the state', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='BOM Type', SeqNo=150, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59878
;

-- Oct 25, 2010 10:29:35 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60012, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='The use of the Bill of Material', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='By default the Master BOM is used, if the alternatives are not defined', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='BOM Use', SeqNo=160, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59879
;

-- Oct 25, 2010 10:29:36 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60013, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Optional short description of the record', DisplayLength=255, DisplayLogic=NULL, EntityType='EE01', Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=170, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59880
;

-- Oct 25, 2010 10:29:36 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60016, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Comment or Hint', DisplayLength=2000, DisplayLogic=NULL, EntityType='EE01', Help='The Help field contains a hint, comment or help about the use of this item.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Comment/Help', SeqNo=180, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59882
;

-- Oct 25, 2010 10:29:36 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60022, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Key of Product BOM', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Product BOM Key', SeqNo=190, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59883
;

-- Oct 25, 2010 10:29:36 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59883
;

-- Oct 25, 2010 10:29:37 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60032, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Bill of Material Component (Product)', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='BOM Component', SeqNo=200, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59884
;

-- Oct 25, 2010 10:29:37 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60025, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Bill of Materials (Engineering) Change Notice (Version)', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Change Notice', SeqNo=210, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59885
;

-- Oct 25, 2010 10:29:37 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59885
;

-- Oct 25, 2010 10:29:37 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60006, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='UOM EDI X12 Code', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='UOM Code', SeqNo=220, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59886
;

-- Oct 25, 2010 10:29:37 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60007, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Unit of Measure', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='The UOM defines a unique non monetary Unit of Measure', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='UOM', SeqNo=230, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59887
;

-- Oct 25, 2010 10:29:38 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60017, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Component Type for a Bill of Material or Formula', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Component Type', SeqNo=240, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59888
;

-- Oct 25, 2010 10:29:38 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60021, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicate that this component is based in % Quantity', DisplayLength=1, DisplayLogic=NULL, EntityType='EE01', Help='Indicate that this component is based in % Quantity', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Is Qty Percentage', SeqNo=250, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59889
;

-- Oct 25, 2010 10:29:39 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60031, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicate that a Manufacturing Order can not begin without have this component', DisplayLength=1, DisplayLogic=NULL, EntityType='EE01', Help='Indicate that a Manufacturing Order can not begin without have this component', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Is Critical Component', SeqNo=260, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59890
;

-- Oct 25, 2010 10:29:39 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60018, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicate the Quantity  use in this BOM', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Quantity', SeqNo=270, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59891
;

-- Oct 25, 2010 10:29:39 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60019, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicate the Quantity % use in this Formula', DisplayLength=22, DisplayLogic='@IsQtyPercentage@=''Y''', EntityType='EE01', Help='Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Quantity in %', SeqNo=280, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59892
;

-- Oct 25, 2010 10:29:40 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60026, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Cost allocation percent in case of a co-product.', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Cost Allocation Percent', SeqNo=290, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59893
;

-- Oct 25, 2010 10:29:40 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59893
;

-- Oct 25, 2010 10:29:40 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60027, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicate the Scrap %  for calculate the Scrap Quantity', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Scrap %', SeqNo=300, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59894
;

-- Oct 25, 2010 10:29:41 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60028, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Indicated the Quantity Assay to use into Quality Order', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help='Indicated the Quantity Assay to use into Quality Order', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Quantity Assay', SeqNo=310, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59895
;

-- Oct 25, 2010 10:29:41 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60020, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='There are two methods for issue the components to Manufacturing Order', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Issue Method', SeqNo=320, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59896
;

-- Oct 25, 2010 10:29:42 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60029, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='The Grouping Components to the Backflush', DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help='When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Backflush Group', SeqNo=330, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59897
;

-- Oct 25, 2010 10:29:42 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60030, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='Optional Lead Time offset before starting production', DisplayLength=10, DisplayLogic=NULL, EntityType='EE01', Help='Optional Lead Time offset before starting production', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Lead Time Offset', SeqNo=340, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59898
;

-- Oct 25, 2010 10:29:43 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60002, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description=NULL, DisplayLength=20, DisplayLogic=NULL, EntityType='EE01', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Import Product BOM', SeqNo=350, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59899
;

-- Oct 25, 2010 10:29:43 PM CDT
-- Import BOM
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59899
;

-- Oct 25, 2010 10:29:44 PM CDT
-- Import BOM
UPDATE AD_Field SET AD_Column_ID=60001, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53350, AD_Val_Rule_ID=NULL, Description='The document has been processed', DisplayLength=1, DisplayLogic=NULL, EntityType='EE01', Help='The Processed checkbox indicates that a document has been processed.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Processed', SeqNo=360, SortNo=0,Updated=TO_TIMESTAMP('2010-10-25 22:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59900
;

-- Oct 25, 2010 10:29:45 PM CDT
-- Import BOM
UPDATE AD_TREENODEMM SET Parent_ID = 163 , SeqNo = 4 WHERE AD_Tree_ID = 10 AND Node_ID = 53303
;
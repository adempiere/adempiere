-- Jun 20, 2010 7:40:16 PM CDT
-- Import Inventory Move
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53121,TO_TIMESTAMP('2010-06-20 19:40:14','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','Y','Import Inventory Movement','N',TO_TIMESTAMP('2010-06-20 19:40:14','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Jun 20, 2010 7:40:16 PM CDT
-- Import Inventory Move
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53121 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Jun 20, 2010 7:40:18 PM CDT
-- Import Inventory Move
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53278,53121,'3',TO_TIMESTAMP('2010-06-20 19:40:17','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','Y','N','N','N','Import Inventory Movement','L','I_Movement',TO_TIMESTAMP('2010-06-20 19:40:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:18 PM CDT
-- Import Inventory Move
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53278 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Jun 20, 2010 7:40:19 PM CDT
-- Import Inventory Move
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53390,TO_TIMESTAMP('2010-06-20 19:40:18','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table I_Movement',1,'Y','N','Y','Y','I_Movement','N',1000000,TO_TIMESTAMP('2010-06-20 19:40:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:21 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54238,0,'I_Movement_ID',TO_TIMESTAMP('2010-06-20 19:40:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','I_Movement_ID','I_Movement_ID',TO_TIMESTAMP('2010-06-20 19:40:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:21 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54238 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 20, 2010 7:40:22 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Jun 20, 2010 7:40:22 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Jun 20, 2010 7:40:23 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59540,54238,0,13,53278,'I_Movement_ID',TO_TIMESTAMP('2010-06-20 19:40:22','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','I_Movement_ID',TO_TIMESTAMP('2010-06-20 19:40:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:23 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59540 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:23 PM CDT
-- Import Inventory Move
CREATE TABLE I_Movement (I_Movement_ID NUMERIC(10) NOT NULL, CONSTRAINT I_Movement_Key PRIMARY KEY (I_Movement_ID))
;

-- Jun 20, 2010 7:40:23 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2010-06-20 19:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Jun 20, 2010 7:40:23 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Jun 20, 2010 7:40:24 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Jun 20, 2010 7:40:24 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Jun 20, 2010 7:40:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59541,113,0,19,53278,'AD_Org_ID',TO_TIMESTAMP('2010-06-20 19:40:24','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2010-06-20 19:40:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59541 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:25 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN AD_Org_ID NUMERIC(10) NOT NULL
;

-- Jun 20, 2010 7:40:25 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2010-06-20 19:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Jun 20, 2010 7:40:25 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59542,102,0,19,53278,'AD_Client_ID',TO_TIMESTAMP('2010-06-20 19:40:25','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2010-06-20 19:40:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59542 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN AD_Client_ID NUMERIC(10) NOT NULL
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2010-06-20 19:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Jun 20, 2010 7:40:26 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Jun 20, 2010 7:40:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59543,607,0,16,53278,'Updated',TO_TIMESTAMP('2010-06-20 19:40:26','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',10,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2010-06-20 19:40:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59543 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:27 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Jun 20, 2010 7:40:27 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2010-06-20 19:40:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Jun 20, 2010 7:40:27 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Jun 20, 2010 7:40:28 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Jun 20, 2010 7:40:28 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Jun 20, 2010 7:40:28 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2010-06-20 19:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Jun 20, 2010 7:40:28 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Jun 20, 2010 7:40:28 PM CDT
-- Import Inventory Move
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59544,608,0,18,110,53278,'UpdatedBy',TO_TIMESTAMP('2010-06-20 19:40:28','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2010-06-20 19:40:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59544 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2010-06-20 19:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Jun 20, 2010 7:40:29 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Jun 20, 2010 7:40:30 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59545,348,0,20,53278,'IsActive',TO_TIMESTAMP('2010-06-20 19:40:29','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2010-06-20 19:40:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:30 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59545 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:30 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Jun 20, 2010 7:40:30 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2010-06-20 19:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Jun 20, 2010 7:40:30 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Jun 20, 2010 7:40:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59546,245,0,16,53278,'Created',TO_TIMESTAMP('2010-06-20 19:40:30','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2010-06-20 19:40:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59546 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:31 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Jun 20, 2010 7:40:31 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2010-06-20 19:40:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Jun 20, 2010 7:40:31 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Jun 20, 2010 7:40:32 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59547,246,0,18,110,53278,'CreatedBy',TO_TIMESTAMP('2010-06-20 19:40:31','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2010-06-20 19:40:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:32 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59547 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:32 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Jun 20, 2010 7:40:32 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2010-06-20 19:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1047
;

-- Jun 20, 2010 7:40:32 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Jun 20, 2010 7:40:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59548,1047,0,20,53278,'Processed',TO_TIMESTAMP('2010-06-20 19:40:32','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE01',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_TIMESTAMP('2010-06-20 19:40:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59548 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:34 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN Processed CHAR(1) CHECK (Processed IN ('Y','N')) NOT NULL
;

-- Jun 20, 2010 7:40:34 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='I_IsImported', Description='Has this import been processed', EntityType='D', Help='The Imported check box indicates if this import has been processed.', IsActive='Y', Name='Imported', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Imported',Updated=TO_TIMESTAMP('2010-06-20 19:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=913
;

-- Jun 20, 2010 7:40:34 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=913
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59549,913,0,20,53278,'I_IsImported',TO_TIMESTAMP('2010-06-20 19:40:34','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed','EE01',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Imported',TO_TIMESTAMP('2010-06-20 19:40:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59549 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN I_IsImported CHAR(1) CHECK (I_IsImported IN ('Y','N')) NOT NULL
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2010-06-20 19:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Jun 20, 2010 7:40:35 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Jun 20, 2010 7:40:36 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53216,'7','org.eevolution.process.ImportInventoryMove',TO_TIMESTAMP('2010-06-20 19:40:35','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','ImportInventoryMovement','Y',0,0,TO_TIMESTAMP('2010-06-20 19:40:35','YYYY-MM-DD HH24:MI:SS'),0,'ImportInventoryMovement',NULL)
;

-- Jun 20, 2010 7:40:36 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53216 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Jun 20, 2010 7:40:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53216,53419,20,'DeleteOldImported',TO_TIMESTAMP('2010-06-20 19:40:36','YYYY-MM-DD HH24:MI:SS'),0,'Before processing delete old imported records in the import table','EE01',0,'Y','Y','N','N','Delete old imported records',10,TO_TIMESTAMP('2010-06-20 19:40:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53419 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 20, 2010 7:40:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2169,0,53216,53420,20,'IsImportOnlyNoErrors',TO_TIMESTAMP('2010-06-20 19:40:37','YYYY-MM-DD HH24:MI:SS'),0,'Y','Only start the import, if there are no validation Errors','EE01',0,'Y','Y','N','N','Import only if No Errors',20,TO_TIMESTAMP('2010-06-20 19:40:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53420 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 20, 2010 7:40:38 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Jun 20, 2010 7:40:38 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Jun 20, 2010 7:40:38 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Document action list', EntityType='D', Help=NULL, IsActive='Y', Name='_Document Action', ValidationType='L',Updated=TO_TIMESTAMP('2010-06-20 19:40:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=135
;

-- Jun 20, 2010 7:40:38 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=135
;

-- Jun 20, 2010 7:40:40 PM CDT
-- Import Inventory Move
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value IN (''CO'',''PR'')', Description=NULL, EntityType='D', IsActive='Y', Name='List - DocAction - Complete or Prepare', Type='S',Updated=TO_TIMESTAMP('2010-06-20 19:40:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=219
;

-- Jun 20, 2010 7:40:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53216,53421,17,135,219,'DocAction',TO_TIMESTAMP('2010-06-20 19:40:40','YYYY-MM-DD HH24:MI:SS'),0,'PR','EE01',1,'Y','Y','N','N','Document Action',30,TO_TIMESTAMP('2010-06-20 19:40:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53421 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 20, 2010 7:40:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59550,524,0,53216,28,53278,'Processing',TO_TIMESTAMP('2010-06-20 19:40:40','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2010-06-20 19:40:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59550 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:41 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN Processing CHAR(1) NOT NULL
;

-- Jun 20, 2010 7:40:42 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='I_ErrorMsg', Description='Messages generated from import process', EntityType='D', Help='The Import Error Message displays any error messages generated during the import process.', IsActive='Y', Name='Import Error Message', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Error Message',Updated=TO_TIMESTAMP('2010-06-20 19:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=912
;

-- Jun 20, 2010 7:40:42 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=912
;

-- Jun 20, 2010 7:40:42 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Jun 20, 2010 7:40:42 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59551,912,0,14,53278,'I_ErrorMsg',TO_TIMESTAMP('2010-06-20 19:40:42','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process','EE01',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_TIMESTAMP('2010-06-20 19:40:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59551 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN I_ErrorMsg VARCHAR(2000) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='ProductValue', Description='Key of the Product', EntityType='D', Help=NULL, IsActive='Y', Name='Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Key',Updated=TO_TIMESTAMP('2010-06-20 19:40:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1675
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1675
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Jun 20, 2010 7:40:43 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59552,1675,0,10,53278,'ProductValue',TO_TIMESTAMP('2010-06-20 19:40:43','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Key',TO_TIMESTAMP('2010-06-20 19:40:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59552 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN ProductValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2010-06-20 19:40:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=454
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Jun 20, 2010 7:40:44 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Jun 20, 2010 7:40:45 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59553,454,0,30,53278,'M_Product_ID',TO_TIMESTAMP('2010-06-20 19:40:44','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_TIMESTAMP('2010-06-20 19:40:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:45 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59553 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:45 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:45 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='MovementDate', Description='Date a product was moved in or out of inventory', EntityType='D', Help='The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.', IsActive='Y', Name='Movement Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Movement Date',Updated=TO_TIMESTAMP('2010-06-20 19:40:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1037
;

-- Jun 20, 2010 7:40:45 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1037
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59554,1037,0,15,53278,'MovementDate',TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),0,'Date a product was moved in or out of inventory','EE01',7,'The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.','Y','N','N','N','N','N','N','N','Y','N','Y','Movement Date',TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59554 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN MovementDate TIMESTAMP DEFAULT NULL 
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='M_Locator_ID', Description='Warehouse Locator', EntityType='D', Help='The Locator indicates where in a Warehouse a product is located.', IsActive='Y', Name='Locator', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Locator',Updated=TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=448
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=448
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Warehouse Locator Data type', EntityType='D', Help=NULL, IsActive='Y', Name='Locator (WH)', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=31
;

-- Jun 20, 2010 7:40:46 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=31
;

-- Jun 20, 2010 7:40:49 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59555,448,0,31,53278,'M_Locator_ID',TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE01',10,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','N','N','N','Y','N','Y','Locator',TO_TIMESTAMP('2010-06-20 19:40:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:49 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59555 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:49 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_Locator_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:49 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='LocatorValue', Description='Key of the Warehouse Locator', EntityType='D', Help=NULL, IsActive='Y', Name='Locator Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Locator Key',Updated=TO_TIMESTAMP('2010-06-20 19:40:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2109
;

-- Jun 20, 2010 7:40:49 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2109
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59556,2109,0,10,53278,'LocatorValue',TO_TIMESTAMP('2010-06-20 19:40:49','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Warehouse Locator','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Locator Key',TO_TIMESTAMP('2010-06-20 19:40:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59556 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN LocatorValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='M_LocatorTo_ID', Description='Location inventory is moved to', EntityType='D', Help='The Locator To indicates the location where the inventory is being moved to.', IsActive='Y', Name='Locator To', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Locator To',Updated=TO_TIMESTAMP('2010-06-20 19:40:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1029
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1029
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59557,1029,0,31,53278,'M_LocatorTo_ID',TO_TIMESTAMP('2010-06-20 19:40:50','YYYY-MM-DD HH24:MI:SS'),0,'Location inventory is moved to','EE01',10,'The Locator To indicates the location where the inventory is being moved to.','Y','N','N','N','N','N','N','N','Y','N','Y','Locator To',TO_TIMESTAMP('2010-06-20 19:40:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59557 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:50 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_LocatorTo_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:51 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54239,0,'LocatorToValue',TO_TIMESTAMP('2010-06-20 19:40:51','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Locator To Key','Locator To Key',TO_TIMESTAMP('2010-06-20 19:40:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:40:51 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54239 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 20, 2010 7:40:52 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59558,54239,0,10,53278,'LocatorToValue',TO_TIMESTAMP('2010-06-20 19:40:51','YYYY-MM-DD HH24:MI:SS'),0,'EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Locator To Key',TO_TIMESTAMP('2010-06-20 19:40:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:52 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59558 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:52 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN LocatorToValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:52 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='OrgValue', Description='Key of the Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Key',Updated=TO_TIMESTAMP('2010-06-20 19:40:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2115
;

-- Jun 20, 2010 7:40:52 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2115
;

-- Jun 20, 2010 7:40:53 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59559,2115,0,10,53278,'OrgValue',TO_TIMESTAMP('2010-06-20 19:40:52','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Key',TO_TIMESTAMP('2010-06-20 19:40:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:53 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59559 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:53 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN OrgValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:53 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='MovementQty', Description='Quantity of a product moved.', EntityType='D', Help='The Movement Quantity indicates the quantity of a product that has been moved.', IsActive='Y', Name='Movement Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_TIMESTAMP('2010-06-20 19:40:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1038
;

-- Jun 20, 2010 7:40:53 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1038
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2010-06-20 19:40:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59560,1038,0,29,53278,'MovementQty',TO_TIMESTAMP('2010-06-20 19:40:54','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.','EE01',10,'The Movement Quantity indicates the quantity of a product that has been moved.','Y','N','N','N','N','N','N','N','Y','N','Y','Movement Quantity',TO_TIMESTAMP('2010-06-20 19:40:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59560 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN MovementQty NUMERIC DEFAULT NULL 
;

-- Jun 20, 2010 7:40:54 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2010-06-20 19:40:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=290
;

-- Jun 20, 2010 7:40:55 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Jun 20, 2010 7:40:56 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59561,290,0,10,53278,'DocumentNo',TO_TIMESTAMP('2010-06-20 19:40:55','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',40,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Document No',TO_TIMESTAMP('2010-06-20 19:40:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:56 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59561 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:56 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN DocumentNo VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:56 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='C_DocType_ID', Description='Document type or rules', EntityType='D', Help='The Document Type determines document sequence and processing rules', IsActive='Y', Name='Document Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Type',Updated=TO_TIMESTAMP('2010-06-20 19:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=196
;

-- Jun 20, 2010 7:40:56 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=196
;

-- Jun 20, 2010 7:40:57 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59562,196,0,19,53278,'C_DocType_ID',TO_TIMESTAMP('2010-06-20 19:40:56','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','Y','N','Y','Document Type',TO_TIMESTAMP('2010-06-20 19:40:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:57 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59562 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:57 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN C_DocType_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:57 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='DocTypeName', Description='Name of the Document Type', EntityType='D', Help=NULL, IsActive='Y', Name='Document Type Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='DocType Name',Updated=TO_TIMESTAMP('2010-06-20 19:40:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2098
;

-- Jun 20, 2010 7:40:57 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2098
;

-- Jun 20, 2010 7:40:58 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59563,2098,0,10,53278,'DocTypeName',TO_TIMESTAMP('2010-06-20 19:40:57','YYYY-MM-DD HH24:MI:SS'),0,'Name of the Document Type','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Document Type Name',TO_TIMESTAMP('2010-06-20 19:40:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:58 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59563 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:58 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN DocTypeName VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:58 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='C_BPartner_ID', Description='Identifies a Business Partner', EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', Name='Business Partner ', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner ',Updated=TO_TIMESTAMP('2010-06-20 19:40:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=187
;

-- Jun 20, 2010 7:40:58 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=187
;

-- Jun 20, 2010 7:40:59 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59564,187,0,19,53278,'C_BPartner_ID',TO_TIMESTAMP('2010-06-20 19:40:58','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner ',TO_TIMESTAMP('2010-06-20 19:40:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:40:59 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59564 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:40:59 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN C_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:40:59 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='BPartnerValue', Description='Key of the Business Partner', EntityType='D', Help=NULL, IsActive='Y', Name='Business Partner Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner Key',Updated=TO_TIMESTAMP('2010-06-20 19:40:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2094
;

-- Jun 20, 2010 7:40:59 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2094
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59565,2094,0,10,53278,'BPartnerValue',TO_TIMESTAMP('2010-06-20 19:40:59','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Business Partner','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner Key',TO_TIMESTAMP('2010-06-20 19:40:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59565 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN BPartnerValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='M_Shipper_ID', Description='Method or manner of product delivery', EntityType='D', Help='The Shipper indicates the method of delivering product', IsActive='Y', Name='Shipper', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Shipper',Updated=TO_TIMESTAMP('2010-06-20 19:41:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=455
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=455
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59566,455,0,19,53278,'M_Shipper_ID',TO_TIMESTAMP('2010-06-20 19:41:00','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE01',10,'The Shipper indicates the method of delivering product','Y','N','N','N','N','N','N','N','Y','N','Y','Shipper',TO_TIMESTAMP('2010-06-20 19:41:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59566 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:00 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_Shipper_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:01 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54240,0,'ShipperName',TO_TIMESTAMP('2010-06-20 19:41:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','ShipperName','Shipper Name',TO_TIMESTAMP('2010-06-20 19:41:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:01 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54240 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 20, 2010 7:41:02 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59567,54240,0,10,53278,'ShipperName',TO_TIMESTAMP('2010-06-20 19:41:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','ShipperName',TO_TIMESTAMP('2010-06-20 19:41:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:02 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59567 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:02 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN ShipperName VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:02 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='AD_User_ID', Description='User within the system - Internal or Business Partner Contact', EntityType='D', Help='The User identifies a unique user in the system. This could be an internal user or a business partner contact', IsActive='Y', Name='Usuario', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Usuario',Updated=TO_TIMESTAMP('2010-06-20 19:41:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=138
;

-- Jun 20, 2010 7:41:02 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=138
;

-- Jun 20, 2010 7:41:03 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59568,138,0,19,53278,'AD_User_ID',TO_TIMESTAMP('2010-06-20 19:41:02','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','EE01',10,'The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','N','N','N','N','N','N','N','Y','N','Y','Usuario',TO_TIMESTAMP('2010-06-20 19:41:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:03 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59568 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:03 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN AD_User_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:03 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='DeliveryViaRule', Description='How the order will be delivered', EntityType='D', Help='The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.', IsActive='Y', Name='Delivery Via', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Delivery Via',Updated=TO_TIMESTAMP('2010-06-20 19:41:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=274
;

-- Jun 20, 2010 7:41:03 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=274
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59569,274,0,10,53278,'DeliveryViaRule',TO_TIMESTAMP('2010-06-20 19:41:03','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE01',40,'The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','N','N','N','N','N','N','N','Y','N','Y','Delivery Via',TO_TIMESTAMP('2010-06-20 19:41:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59569 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN DeliveryViaRule VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='DeliveryRule', Description='Defines the timing of Delivery', EntityType='D', Help='The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.', IsActive='Y', Name='Delivery Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Delivery Rule',Updated=TO_TIMESTAMP('2010-06-20 19:41:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=555
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=555
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59570,555,0,10,53278,'DeliveryRule',TO_TIMESTAMP('2010-06-20 19:41:05','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE01',40,'The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','N','N','N','N','N','N','N','Y','N','Y','Delivery Rule',TO_TIMESTAMP('2010-06-20 19:41:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59570 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:05 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN DeliveryRule VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='PriorityRule', Description='Priority of a document', EntityType='D', Help='The Priority indicates the importance (high, medium, low) of this document', IsActive='Y', Name='Priority', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Priority',Updated=TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=522
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=522
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59571,522,0,10,53278,'PriorityRule',TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE01',40,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','N','N','N','Y','N','Y','Priority',TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59571 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN PriorityRule VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='FreightCostRule', Description='Method for charging Freight', EntityType='D', Help='The Freight Cost Rule indicates the method used when charging for freight.', IsActive='Y', Name='Freight Cost Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Freight Cost Rule',Updated=TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1007
;

-- Jun 20, 2010 7:41:06 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1007
;

-- Jun 20, 2010 7:41:07 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59572,1007,0,10,53278,'FreightCostRule',TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE01',40,'The Freight Cost Rule indicates the method used when charging for freight.','Y','N','N','N','N','N','N','N','Y','N','Y','Freight Cost Rule',TO_TIMESTAMP('2010-06-20 19:41:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:07 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59572 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:07 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN FreightCostRule VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:07 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='C_Project_ID', Description='Financial Project', EntityType='D', Help='A Project allows you to track and control internal or external activities.', IsActive='Y', Name='Project', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project',Updated=TO_TIMESTAMP('2010-06-20 19:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=208
;

-- Jun 20, 2010 7:41:07 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=208
;

-- Jun 20, 2010 7:41:08 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59573,208,0,19,53278,'C_Project_ID',TO_TIMESTAMP('2010-06-20 19:41:07','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE01',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','Y','N','Y','Project',TO_TIMESTAMP('2010-06-20 19:41:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:08 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59573 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:08 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN C_Project_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:08 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='ProjectValue', Description='Key of the Project', EntityType='D', Help=NULL, IsActive='Y', Name='Project Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Project Key',Updated=TO_TIMESTAMP('2010-06-20 19:41:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2118
;

-- Jun 20, 2010 7:41:08 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2118
;

-- Jun 20, 2010 7:41:09 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59574,2118,0,10,53278,'ProjectValue',TO_TIMESTAMP('2010-06-20 19:41:08','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Project','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Project Key',TO_TIMESTAMP('2010-06-20 19:41:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:09 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59574 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:09 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN ProjectValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:09 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='C_Campaign_ID', Description='Marketing Campaign', EntityType='D', Help='The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.', IsActive='Y', Name='Campaign', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Campaign',Updated=TO_TIMESTAMP('2010-06-20 19:41:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=550
;

-- Jun 20, 2010 7:41:09 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=550
;

-- Jun 20, 2010 7:41:10 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59575,550,0,19,53278,'C_Campaign_ID',TO_TIMESTAMP('2010-06-20 19:41:09','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE01',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','Y','N','Y','Campaign',TO_TIMESTAMP('2010-06-20 19:41:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:10 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59575 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:10 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN C_Campaign_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:11 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54241,0,'CampaignValue',TO_TIMESTAMP('2010-06-20 19:41:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','CampaignValue','CampaignValue',TO_TIMESTAMP('2010-06-20 19:41:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:11 PM CDT
-- Import Inventory Move
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54241 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 20, 2010 7:41:12 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59576,54241,0,10,53278,'CampaignValue',TO_TIMESTAMP('2010-06-20 19:41:11','YYYY-MM-DD HH24:MI:SS'),0,'EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','CampaignValue',TO_TIMESTAMP('2010-06-20 19:41:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:12 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59576 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:12 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN CampaignValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:12 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='AD_OrgTrx_ID', Description='Performing or initiating organization', EntityType='D', Help='The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.', IsActive='Y', Name='Trx Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Trx Organization',Updated=TO_TIMESTAMP('2010-06-20 19:41:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=112
;

-- Jun 20, 2010 7:41:12 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=112
;

-- Jun 20, 2010 7:41:13 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59577,112,0,19,53278,'AD_OrgTrx_ID',TO_TIMESTAMP('2010-06-20 19:41:12','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE01',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','Y','N','Y','Trx Organization',TO_TIMESTAMP('2010-06-20 19:41:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:13 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59577 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:13 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN AD_OrgTrx_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:13 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='OrgTrxValue', Description='Key of the Transaction Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Trx Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Trx Org Key',Updated=TO_TIMESTAMP('2010-06-20 19:41:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2114
;

-- Jun 20, 2010 7:41:13 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2114
;

-- Jun 20, 2010 7:41:14 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59578,2114,0,10,53278,'OrgTrxValue',TO_TIMESTAMP('2010-06-20 19:41:13','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Transaction Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Trx Org Key',TO_TIMESTAMP('2010-06-20 19:41:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:14 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59578 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:14 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN OrgTrxValue VARCHAR(40) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:14 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='User1_ID', Description='User defined list element #1', EntityType='D', Help='The user defined element displays the optional elements that have been defined for this account combination.', IsActive='Y', Name='User List 1', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='User 1',Updated=TO_TIMESTAMP('2010-06-20 19:41:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=613
;

-- Jun 20, 2010 7:41:14 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=613
;

-- Jun 20, 2010 7:41:15 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59579,613,0,10,53278,'User1_ID',TO_TIMESTAMP('2010-06-20 19:41:14','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE01',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 1',TO_TIMESTAMP('2010-06-20 19:41:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:15 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59579 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:15 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN User1_ID VARCHAR(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:15 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='User2_ID', Description='User defined list element #2', EntityType='D', Help='The user defined element displays the optional elements that have been defined for this account combination.', IsActive='Y', Name='User List 2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='User 2',Updated=TO_TIMESTAMP('2010-06-20 19:41:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=614
;

-- Jun 20, 2010 7:41:15 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=614
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59580,614,0,10,53278,'User2_ID',TO_TIMESTAMP('2010-06-20 19:41:15','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE01',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','Y','N','Y','User List 2',TO_TIMESTAMP('2010-06-20 19:41:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59580 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN User2_ID VARCHAR(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
UPDATE AD_Element SET ColumnName='M_Movement_ID', Description='Movement of Inventory', EntityType='D', Help='The Inventory Movement uniquely identifies a group of movement lines.', IsActive='Y', Name='Inventory Move', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Move',Updated=TO_TIMESTAMP('2010-06-20 19:41:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1030
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1030
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Movement', ValidationType='T',Updated=TO_TIMESTAMP('2010-06-20 19:41:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53252
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53252
;

-- Jun 20, 2010 7:41:16 PM CDT
-- Import Inventory Move
UPDATE AD_Ref_Table SET AD_Table_ID = 323, AD_Display = 3577, AD_Key = 3569, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 53252
;

-- Jun 20, 2010 7:41:18 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59581,1030,0,30,53252,53278,'M_Movement_ID',TO_TIMESTAMP('2010-06-20 19:41:16','YYYY-MM-DD HH24:MI:SS'),0,'Movement of Inventory','EE01',10,'The Inventory Movement uniquely identifies a group of movement lines.','Y','N','N','N','N','N','N','N','Y','N','Y','Inventory Move',TO_TIMESTAMP('2010-06-20 19:41:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:41:18 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59581 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:41:18 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_Movement_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:41:19 PM CDT
-- Import Inventory Move
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53335,53278,53121,NULL,TO_TIMESTAMP('2010-06-20 19:41:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','Y','N','N','N','N','Import Inventory Movement','N',10,0,TO_TIMESTAMP('2010-06-20 19:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:19 PM CDT
-- Import Inventory Move
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53335 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Jun 20, 2010 7:41:21 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59540,59412,0,53335,TO_TIMESTAMP('2010-06-20 19:41:19','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','N','N','N','N','N','I_Movement_ID',0,0,TO_TIMESTAMP('2010-06-20 19:41:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:21 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59412 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:22 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59542,59413,0,53335,TO_TIMESTAMP('2010-06-20 19:41:22','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2010-06-20 19:41:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:22 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59413 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:24 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59581,59414,0,53335,TO_TIMESTAMP('2010-06-20 19:41:23','YYYY-MM-DD HH24:MI:SS'),0,'Movement of Inventory',10,'EE01','The Inventory Movement uniquely identifies a group of movement lines.','Y','Y','Y','N','N','Y','Y','Inventory Move',20,0,TO_TIMESTAMP('2010-06-20 19:41:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:24 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59414 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59559,59415,0,53335,TO_TIMESTAMP('2010-06-20 19:41:24','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization',20,'EE01','Y','Y','Y','N','N','N','N','Org Key',30,0,TO_TIMESTAMP('2010-06-20 19:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59415 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59541,59416,0,53335,TO_TIMESTAMP('2010-06-20 19:41:25','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',40,0,TO_TIMESTAMP('2010-06-20 19:41:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:25 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59416 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:26 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59554,59417,0,53335,TO_TIMESTAMP('2010-06-20 19:41:25','YYYY-MM-DD HH24:MI:SS'),0,'Date a product was moved in or out of inventory',7,'EE01','The Movement Date indicates the date that a product moved in or out of inventory.  This is the result of a shipment, receipt or inventory movement.','Y','Y','Y','N','N','N','N','Movement Date',50,0,TO_TIMESTAMP('2010-06-20 19:41:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:26 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59417 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59561,59418,0,53335,TO_TIMESTAMP('2010-06-20 19:41:26','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document',40,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','Y','Document No',60,0,TO_TIMESTAMP('2010-06-20 19:41:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59418 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59563,59419,0,53335,TO_TIMESTAMP('2010-06-20 19:41:27','YYYY-MM-DD HH24:MI:SS'),0,'Name of the Document Type',20,'EE01','Y','Y','Y','N','N','N','N','Document Type Name',70,0,TO_TIMESTAMP('2010-06-20 19:41:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:27 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59419 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:28 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59562,59420,0,53335,TO_TIMESTAMP('2010-06-20 19:41:27','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules',10,'EE01','The Document Type determines document sequence and processing rules','Y','Y','Y','N','N','N','Y','Document Type',80,0,TO_TIMESTAMP('2010-06-20 19:41:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:28 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59420 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59565,59421,0,53335,TO_TIMESTAMP('2010-06-20 19:41:28','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Business Partner',20,'EE01','Y','Y','Y','N','N','N','N','Business Partner Key',90,0,TO_TIMESTAMP('2010-06-20 19:41:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59421 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59564,59422,0,53335,TO_TIMESTAMP('2010-06-20 19:41:29','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',10,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','Y','Business Partner ',100,0,TO_TIMESTAMP('2010-06-20 19:41:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:29 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59422 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:30 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59567,59423,0,53335,TO_TIMESTAMP('2010-06-20 19:41:29','YYYY-MM-DD HH24:MI:SS'),0,20,'EE01','Y','Y','Y','N','N','N','N','ShipperName',110,0,TO_TIMESTAMP('2010-06-20 19:41:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:30 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59423 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59566,59424,0,53335,TO_TIMESTAMP('2010-06-20 19:41:30','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery',10,'EE01','The Shipper indicates the method of delivering product','Y','Y','Y','N','N','N','Y','Shipper',120,0,TO_TIMESTAMP('2010-06-20 19:41:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59424 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:32 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59568,59425,0,53335,TO_TIMESTAMP('2010-06-20 19:41:31','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact',10,'EE01','The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','Y','Y','N','N','N','N','Usuario',130,0,TO_TIMESTAMP('2010-06-20 19:41:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:32 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59425 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:33 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59569,59426,0,53335,TO_TIMESTAMP('2010-06-20 19:41:32','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered',20,'EE01','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','Y','N','N','N','N','Delivery Via',140,0,TO_TIMESTAMP('2010-06-20 19:41:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:33 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59426 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59570,59427,0,53335,TO_TIMESTAMP('2010-06-20 19:41:33','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery',20,'EE01','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','Y','N','N','N','Y','Delivery Rule',150,0,TO_TIMESTAMP('2010-06-20 19:41:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59427 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59571,59428,0,53335,TO_TIMESTAMP('2010-06-20 19:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document',20,'EE01','The Priority indicates the importance (high, medium, low) of this document','Y','Y','Y','N','N','N','N','Priority',160,0,TO_TIMESTAMP('2010-06-20 19:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:34 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59428 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59572,59429,0,53335,TO_TIMESTAMP('2010-06-20 19:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight',20,'EE01','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','Y','N','N','N','Y','Freight Cost Rule',170,0,TO_TIMESTAMP('2010-06-20 19:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59429 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59574,59430,0,53335,TO_TIMESTAMP('2010-06-20 19:41:35','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Project',20,'EE01','Y','Y','Y','N','N','N','N','Project Key',180,0,TO_TIMESTAMP('2010-06-20 19:41:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:35 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59430 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59573,59431,0,53335,TO_TIMESTAMP('2010-06-20 19:41:35','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project',10,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','Y','Project',190,0,TO_TIMESTAMP('2010-06-20 19:41:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59431 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59576,59432,0,53335,TO_TIMESTAMP('2010-06-20 19:41:37','YYYY-MM-DD HH24:MI:SS'),0,20,'EE01','Y','Y','Y','N','N','N','N','CampaignValue',200,0,TO_TIMESTAMP('2010-06-20 19:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:37 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59432 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:38 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59575,59433,0,53335,TO_TIMESTAMP('2010-06-20 19:41:37','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign',10,'EE01','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','Y','Campaign',210,0,TO_TIMESTAMP('2010-06-20 19:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:38 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59433 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:39 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59578,59434,0,53335,TO_TIMESTAMP('2010-06-20 19:41:38','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Transaction Organization',20,'EE01','Y','Y','Y','N','N','N','N','Trx Org Key',220,0,TO_TIMESTAMP('2010-06-20 19:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:39 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59434 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:39 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59577,59435,0,53335,TO_TIMESTAMP('2010-06-20 19:41:39','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',10,'EE01','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','Y','Trx Organization',230,0,TO_TIMESTAMP('2010-06-20 19:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:39 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59435 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59579,59436,0,53335,TO_TIMESTAMP('2010-06-20 19:41:39','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1',10,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','User List 1',240,0,TO_TIMESTAMP('2010-06-20 19:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59436 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59580,59437,0,53335,TO_TIMESTAMP('2010-06-20 19:41:40','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2',10,'EE01','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','Y','User List 2',250,0,TO_TIMESTAMP('2010-06-20 19:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:40 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59437 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59552,59438,0,53335,TO_TIMESTAMP('2010-06-20 19:41:40','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product',10,'EE01','Y','Y','Y','N','N','N','N','Product Key',260,0,TO_TIMESTAMP('2010-06-20 19:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59438 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:42 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59553,59439,0,53335,TO_TIMESTAMP('2010-06-20 19:41:41','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',10,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','Y','Product',270,0,TO_TIMESTAMP('2010-06-20 19:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:42 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59439 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:43 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59556,59440,0,53335,TO_TIMESTAMP('2010-06-20 19:41:42','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Warehouse Locator',10,'EE01','Y','Y','Y','N','N','N','N','Locator Key',280,0,TO_TIMESTAMP('2010-06-20 19:41:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:43 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59440 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59555,59441,0,53335,TO_TIMESTAMP('2010-06-20 19:41:43','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator',10,'EE01','The Locator indicates where in a Warehouse a product is located.','Y','Y','Y','N','N','N','Y','Locator',290,0,TO_TIMESTAMP('2010-06-20 19:41:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59441 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59558,59442,0,53335,TO_TIMESTAMP('2010-06-20 19:41:44','YYYY-MM-DD HH24:MI:SS'),0,20,'EE01','Y','Y','Y','N','N','N','N','Locator To Key',300,0,TO_TIMESTAMP('2010-06-20 19:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:44 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59442 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:45 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59557,59443,0,53335,TO_TIMESTAMP('2010-06-20 19:41:44','YYYY-MM-DD HH24:MI:SS'),0,'Location inventory is moved to',10,'EE01','The Locator To indicates the location where the inventory is being moved to.','Y','Y','Y','N','N','N','Y','Locator To',310,0,TO_TIMESTAMP('2010-06-20 19:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:45 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59443 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:46 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59560,59444,0,53335,TO_TIMESTAMP('2010-06-20 19:41:45','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.',10,'EE01','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Y','Y','N','N','N','N','Movement Quantity',320,0,TO_TIMESTAMP('2010-06-20 19:41:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:46 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59444 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:47 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59545,59445,0,53335,TO_TIMESTAMP('2010-06-20 19:41:46','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','Y','Active',330,0,TO_TIMESTAMP('2010-06-20 19:41:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:47 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59445 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:47 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59549,59446,0,53335,TO_TIMESTAMP('2010-06-20 19:41:47','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed',1,'EE01','The Imported check box indicates if this import has been processed.','Y','Y','Y','N','N','N','N','Imported',340,0,TO_TIMESTAMP('2010-06-20 19:41:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:47 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59446 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:48 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59548,59447,0,53335,TO_TIMESTAMP('2010-06-20 19:41:47','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','Y','Processed',350,0,TO_TIMESTAMP('2010-06-20 19:41:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:48 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59447 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:49 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59551,59448,0,53335,TO_TIMESTAMP('2010-06-20 19:41:48','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process',2000,'EE01','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','N','N','Y','N','Import Error Message',360,0,TO_TIMESTAMP('2010-06-20 19:41:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:49 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59448 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59550,59449,0,53335,TO_TIMESTAMP('2010-06-20 19:41:49','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','Process Now',370,0,TO_TIMESTAMP('2010-06-20 19:41:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:50 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59449 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:41:51 PM CDT
-- Import Inventory Move
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53282,0,53121,'W',TO_TIMESTAMP('2010-06-20 19:41:50','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','Import Inventory Movement',TO_TIMESTAMP('2010-06-20 19:41:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:41:51 PM CDT
-- Import Inventory Move
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53282 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Jun 20, 2010 7:41:51 PM CDT
-- Import Inventory Move
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 0,999, 10, 53282)
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=222
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=223
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=185
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=340
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53206
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53264
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=339
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=338
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=363
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53282
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=376
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=382
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=486
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=425
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=378
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=374
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=423
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=373
;

-- Jun 20, 2010 7:42:50 PM CDT
-- Import Inventory Move
UPDATE AD_TreeNodeMM SET Parent_ID=163, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=424
;

-- Jun 20, 2010 7:47:15 PM CDT
-- Import Inventory Move
UPDATE AD_Window SET Description='This window allow import Inventory Move transaction', Name='Import Inventory Move',Updated=TO_TIMESTAMP('2010-06-20 19:47:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53121
;

-- Jun 20, 2010 7:47:15 PM CDT
-- Import Inventory Move
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53121
;

-- Jun 20, 2010 7:47:15 PM CDT
-- Import Inventory Move
UPDATE AD_Menu SET Description='This window allow import Inventory Move transaction', IsActive='Y', Name='Import Inventory Move',Updated=TO_TIMESTAMP('2010-06-20 19:47:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53282
;

-- Jun 20, 2010 7:47:15 PM CDT
-- Import Inventory Move
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53282
;

-- Jun 20, 2010 7:47:35 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-06-20 19:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59413
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=59448
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=59415
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=59416
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=59417
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=59418
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=59419
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=59420
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=59421
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=59422
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=59423
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=59424
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=59425
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=59426
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=59427
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=59428
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=59429
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59430
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59431
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59432
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=59433
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=59434
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=59435
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=59436
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=59437
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=59438
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=59439
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=59440
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=59441
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=59442
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=59443
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=59444
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=59445
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=59446
;

-- Jun 20, 2010 7:50:15 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=59447
;

-- Jun 20, 2010 7:51:23 PM CDT
-- Import Inventory Move
UPDATE AD_Table SET Name='Import Inventory Move',Updated=TO_TIMESTAMP('2010-06-20 19:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53278
;

-- Jun 20, 2010 7:51:23 PM CDT
-- Import Inventory Move
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53278
;

-- Jun 20, 2010 7:51:45 PM CDT
-- Import Inventory Move
UPDATE AD_Column SET AD_Reference_ID=10,Updated=TO_TIMESTAMP('2010-06-20 19:51:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59551
;

-- Jun 20, 2010 7:52:14 PM CDT
-- Import Inventory Move
UPDATE AD_Tab SET IsSingleRow='Y', Name='Import Inventory Move',Updated=TO_TIMESTAMP('2010-06-20 19:52:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53335
;

-- Jun 20, 2010 7:52:14 PM CDT
-- Import Inventory Move
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53335
;

-- Jun 20, 2010 7:52:48 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=59414
;

-- Jun 20, 2010 7:52:49 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=59413
;

-- Jun 20, 2010 7:53:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59582,1031,0,19,53278,'M_MovementLine_ID',TO_TIMESTAMP('2010-06-20 19:53:30','YYYY-MM-DD HH24:MI:SS'),0,'Inventory Move document Line','EE01',10,'The Movement Line indicates the inventory movement document line (if applicable) for this transaction','Y','Y','N','N','N','N','N','N','N','N','Y','N','Y','Move Line',TO_TIMESTAMP('2010-06-20 19:53:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 20, 2010 7:53:31 PM CDT
-- Import Inventory Move
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59582 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 20, 2010 7:53:34 PM CDT
-- Import Inventory Move
ALTER TABLE I_Movement ADD COLUMN M_MovementLine_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 20, 2010 7:53:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59582,59450,0,53335,TO_TIMESTAMP('2010-06-20 19:53:40','YYYY-MM-DD HH24:MI:SS'),0,'Inventory Move document Line',10,'EE01','The Movement Line indicates the inventory movement document line (if applicable) for this transaction','Y','Y','Y','N','N','N','N','N','Move Line',TO_TIMESTAMP('2010-06-20 19:53:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2010 7:53:41 PM CDT
-- Import Inventory Move
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59450 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=59450
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=59413
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=59448
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=59415
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=59416
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=59417
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=59418
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=59419
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=59420
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=59421
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=59422
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=59423
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=59424
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=59425
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=59426
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=59427
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=59428
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59429
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59430
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59431
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=59432
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=59433
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=59434
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=59435
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=59436
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=59437
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=59438
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=59439
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=59440
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=59441
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=59442
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=59443
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=59444
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=59445
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=59446
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=59447
;

-- Jun 20, 2010 7:53:59 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=59449
;

-- Jun 20, 2010 7:54:05 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_TIMESTAMP('2010-06-20 19:54:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59450
;

-- Jun 20, 2010 7:55:35 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsCentrallyMaintained='N', Name='Import Inventory Move',Updated=TO_TIMESTAMP('2010-06-20 19:55:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59449
;

-- Jun 20, 2010 7:55:35 PM CDT
-- Import Inventory Move
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=59449
;

-- Jun 20, 2010 7:55:42 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-06-20 19:55:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59447
;

-- Jun 20, 2010 7:55:47 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2010-06-20 19:55:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59446
;

-- Jun 20, 2010 7:55:54 PM CDT
-- Import Inventory Move
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_TIMESTAMP('2010-06-20 19:55:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59445
;

-- Jun 20, 2010 7:56:38 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','M_MovementLine_ID','NUMERIC(10)',null,'NULL')
;

-- Jun 24, 2010 9:26:08 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','Processing','CHAR(1)',null,'N')
;

-- Jun 24, 2010 9:26:09 PM CDT
-- Import Inventory Move
UPDATE I_Movement SET Processing='N' WHERE Processing IS NULL
;

-- Jun 24, 2010 9:26:29 PM CDT
-- Import Inventory Move
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2010-06-24 21:26:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59548
;

-- Jun 24, 2010 9:26:31 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','Processed','CHAR(1)',null,'N')
;

-- Jun 24, 2010 9:26:32 PM CDT
-- Import Inventory Move
UPDATE I_Movement SET Processed='N' WHERE Processed IS NULL
;

-- Jun 24, 2010 9:26:58 PM CDT
-- Import Inventory Move
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2010-06-24 21:26:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59549
;

-- Jun 24, 2010 9:27:01 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','I_IsImported','CHAR(1)',null,'N')
;

-- Jun 24, 2010 9:27:01 PM CDT
-- Import Inventory Move
UPDATE I_Movement SET I_IsImported='N' WHERE I_IsImported IS NULL
;

-- Jun 24, 2010 9:27:16 PM CDT
-- Import Inventory Move
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-06-24 21:27:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59561
;

-- Jun 24, 2010 9:27:19 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','DocumentNo','VARCHAR(40)',null,null)
;

-- Jun 24, 2010 9:27:19 PM CDT
-- Import Inventory Move
INSERT INTO t_alter_column values('i_movement','DocumentNo',null,'NOT NULL',null)
;


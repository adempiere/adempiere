-- Feb 15, 2010 1:05:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53109,TO_TIMESTAMP('2010-02-15 13:05:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','Y','Import Product Planning','N',TO_TIMESTAMP('2010-02-15 13:05:05','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Feb 15, 2010 1:05:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53109 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Feb 15, 2010 1:05:11 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53260,53109,'2',TO_TIMESTAMP('2010-02-15 13:05:09','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','Y','N','N','N','Import Product Planning','L','I_ProductPlanning',TO_TIMESTAMP('2010-02-15 13:05:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:05:11 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53260 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Feb 15, 2010 1:05:12 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53369,TO_TIMESTAMP('2010-02-15 13:05:11','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table I_ProductPlanning',1,'Y','N','Y','Y','I_ProductPlanning','N',1000000,TO_TIMESTAMP('2010-02-15 13:05:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:05:13 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54117,0,'I_ProductPlanning_ID',TO_TIMESTAMP('2010-02-15 13:05:12','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Import Product Planning','Import Product Planning',TO_TIMESTAMP('2010-02-15 13:05:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:05:13 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54117 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:05:14 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Feb 15, 2010 1:05:14 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Feb 15, 2010 1:05:15 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58977,54117,0,13,53260,'I_ProductPlanning_ID',TO_TIMESTAMP('2010-02-15 13:05:14','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','Import Product Planning',TO_TIMESTAMP('2010-02-15 13:05:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:15 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58977 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:15 PM CST
-- Create new importer for Planning Data and Forecast
CREATE TABLE I_ProductPlanning (I_ProductPlanning_ID NUMERIC(10) NOT NULL, CONSTRAINT I_ProductPlanning_Key PRIMARY KEY (I_ProductPlanning_ID))
;

-- Feb 15, 2010 1:05:16 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2010-02-15 13:05:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Feb 15, 2010 1:05:16 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Feb 15, 2010 1:05:17 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Feb 15, 2010 1:05:17 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Feb 15, 2010 1:05:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58978,102,0,19,53260,'AD_Client_ID',TO_TIMESTAMP('2010-02-15 13:05:17','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2010-02-15 13:05:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58978 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:18 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN AD_Client_ID NUMERIC(10) NOT NULL
;

-- Feb 15, 2010 1:05:18 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2010-02-15 13:05:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Feb 15, 2010 1:05:18 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58979,113,0,19,53260,'AD_Org_ID',TO_TIMESTAMP('2010-02-15 13:05:18','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2010-02-15 13:05:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58979 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN AD_Org_ID NUMERIC(10) NOT NULL
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2010-02-15 13:05:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Feb 15, 2010 1:05:19 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58980,245,0,16,53260,'Created',TO_TIMESTAMP('2010-02-15 13:05:19','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',14,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2010-02-15 13:05:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58980 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2010-02-15 13:05:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Feb 15, 2010 1:05:20 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Feb 15, 2010 1:05:21 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58981,246,0,11,53260,'CreatedBy',TO_TIMESTAMP('2010-02-15 13:05:20','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',14,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2010-02-15 13:05:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:21 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58981 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:21 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Feb 15, 2010 1:05:21 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2010-02-15 13:05:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Feb 15, 2010 1:05:21 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Feb 15, 2010 1:05:22 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Feb 15, 2010 1:05:22 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Feb 15, 2010 1:05:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58982,348,0,20,53260,'IsActive',TO_TIMESTAMP('2010-02-15 13:05:22','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2010-02-15 13:05:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58982 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:22 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Feb 15, 2010 1:05:23 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2010-02-15 13:05:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Feb 15, 2010 1:05:23 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Feb 15, 2010 1:05:24 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58983,607,0,16,53260,'Updated',TO_TIMESTAMP('2010-02-15 13:05:23','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2010-02-15 13:05:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:24 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58983 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:24 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Feb 15, 2010 1:05:24 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2010-02-15 13:05:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Feb 15, 2010 1:05:24 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58984,608,0,11,53260,'UpdatedBy',TO_TIMESTAMP('2010-02-15 13:05:24','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',14,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2010-02-15 13:05:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58984 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='BPartner_Value', Description='The Key of the Business Partner', EntityType='D', Help=NULL, IsActive='Y', Name='Business Partner Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Partner Key',Updated=TO_TIMESTAMP('2010-02-15 13:05:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1906
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1906
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Feb 15, 2010 1:05:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58985,1906,0,10,53260,'BPartner_Value',TO_TIMESTAMP('2010-02-15 13:05:25','YYYY-MM-DD HH24:MI:SS'),0,'The Key of the Business Partner','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner Key',TO_TIMESTAMP('2010-02-15 13:05:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58985 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN BPartner_Value VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='C_BPartner_ID', Description='Identifies a Business Partner', EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', Name='Business Partner ', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner ',Updated=TO_TIMESTAMP('2010-02-15 13:05:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=187
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=187
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Feb 15, 2010 1:05:26 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Feb 15, 2010 1:05:27 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58986,187,0,30,53260,'C_BPartner_ID',TO_TIMESTAMP('2010-02-15 13:05:26','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner ',TO_TIMESTAMP('2010-02-15 13:05:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:27 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58986 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:27 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN C_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:27 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='I_ErrorMsg', Description='Messages generated from import process', EntityType='D', Help='The Import Error Message displays any error messages generated during the import process.', IsActive='Y', Name='Import Error Message', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Error Message',Updated=TO_TIMESTAMP('2010-02-15 13:05:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=912
;

-- Feb 15, 2010 1:05:27 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=912
;

-- Feb 15, 2010 1:05:28 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58987,912,0,10,53260,'I_ErrorMsg',TO_TIMESTAMP('2010-02-15 13:05:27','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process','EE01',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_TIMESTAMP('2010-02-15 13:05:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:28 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58987 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:29 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN I_ErrorMsg VARCHAR(2000) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:29 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='I_IsImported', Description='Has this import been processed', EntityType='D', Help='The Imported check box indicates if this import has been processed.', IsActive='Y', Name='Imported', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Imported',Updated=TO_TIMESTAMP('2010-02-15 13:05:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=913
;

-- Feb 15, 2010 1:05:29 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=913
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58988,913,0,20,53260,'I_IsImported',TO_TIMESTAMP('2010-02-15 13:05:29','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed','EE01',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Imported',TO_TIMESTAMP('2010-02-15 13:05:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58988 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN I_IsImported CHAR(1) CHECK (I_IsImported IN ('Y','N')) NOT NULL
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2010-02-15 13:05:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1047
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58989,1047,0,20,53260,'Processed',TO_TIMESTAMP('2010-02-15 13:05:30','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE01',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','Y','N','N','Processed',TO_TIMESTAMP('2010-02-15 13:05:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:30 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58989 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:31 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Processed CHAR(1) DEFAULT NULL CHECK (Processed IN ('Y','N'))
;

-- Feb 15, 2010 1:05:31 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2010-02-15 13:05:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Feb 15, 2010 1:05:31 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Feb 15, 2010 1:05:31 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Feb 15, 2010 1:05:31 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Feb 15, 2010 1:05:32 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53200,'3','org.eevolution.process.ImportProductPlanning',TO_TIMESTAMP('2010-02-15 13:05:31','YYYY-MM-DD HH24:MI:SS'),0,'Import Product Planning and Forecast','EE01','Import and update  product planning data and forecast','Y','N','N','N','Import Product Planning and Forecast','Y',0,0,TO_TIMESTAMP('2010-02-15 13:05:31','YYYY-MM-DD HH24:MI:SS'),0,'Import_ProductPlanning',NULL)
;

-- Feb 15, 2010 1:05:32 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53200 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Feb 15, 2010 1:05:33 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53200,53402,20,'DeleteOldImported',TO_TIMESTAMP('2010-02-15 13:05:32','YYYY-MM-DD HH24:MI:SS'),0,'Before processing delete old imported records in the import table','EE01',0,'Y','Y','N','N','Delete old imported records',30,TO_TIMESTAMP('2010-02-15 13:05:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:05:33 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53402 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Feb 15, 2010 1:05:34 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2169,0,53200,53403,20,'IsImportOnlyNoErrors',TO_TIMESTAMP('2010-02-15 13:05:33','YYYY-MM-DD HH24:MI:SS'),0,'Y','Only start the import, if there are no validation Errors','EE01',0,'Y','Y','N','N','Import only if No Errors',60,TO_TIMESTAMP('2010-02-15 13:05:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:05:34 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53403 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58990,524,0,53200,28,53260,'Processing',TO_TIMESTAMP('2010-02-15 13:05:34','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2010-02-15 13:05:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58990 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='AD_Workflow_ID', Description='Workflow or combination of tasks', EntityType='D', Help='The Workflow field identifies a unique Workflow in the system.', IsActive='Y', Name='Workflow', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Workflow',Updated=TO_TIMESTAMP('2010-02-15 13:05:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=144
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=144
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Feb 15, 2010 1:05:35 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Feb 15, 2010 1:05:36 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Val_Rule SET Code='WorkflowType=''M''', Description=NULL, EntityType='EE01', IsActive='Y', Name='AD_Workflow Manufacturing', Type='S',Updated=TO_TIMESTAMP('2010-02-15 13:05:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52003
;

-- Feb 15, 2010 1:05:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58991,144,0,18,53260,52003,'AD_Workflow_ID',TO_TIMESTAMP('2010-02-15 13:05:36','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01',22,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Workflow',TO_TIMESTAMP('2010-02-15 13:05:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58991 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:40 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN AD_Workflow_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:40 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='DD_NetworkDistribution_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Network Distribution', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Network Distribution',Updated=TO_TIMESTAMP('2010-02-15 13:05:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53340
;

-- Feb 15, 2010 1:05:40 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53340
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58992,53340,0,18,53260,'DD_NetworkDistribution_ID',TO_TIMESTAMP('2010-02-15 13:05:40','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Network Distribution',TO_TIMESTAMP('2010-02-15 13:05:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58992 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN DD_NetworkDistribution_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='DeliveryTime_Promised', Description='Promised days between order and delivery', EntityType='D', Help='The Promised Delivery Time indicates the number of days between the order date and the date that delivery was promised.', IsActive='Y', Name='Promised Delivery Time', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Promised Delivery Time',Updated=TO_TIMESTAMP('2010-02-15 13:05:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1256
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1256
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Feb 15, 2010 1:05:41 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Feb 15, 2010 1:05:42 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58993,1256,0,29,53260,'DeliveryTime_Promised',TO_TIMESTAMP('2010-02-15 13:05:41','YYYY-MM-DD HH24:MI:SS'),0,'Promised days between order and delivery','EE01',10,'The Promised Delivery Time indicates the number of days between the order date and the date that delivery was promised.','Y','N','N','N','N','N','N','N','Y','N','Y','Promised Delivery Time',TO_TIMESTAMP('2010-02-15 13:05:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:42 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58993 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:42 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN DeliveryTime_Promised NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:05:42 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='IsCreatePlan', Description='Indicates whether planned orders will be generated by MRP', EntityType='EE01', Help='Indicates whether planned orders will be generated by MRP, if this flag is not just MRP generate a ''Create'' action notice', IsActive='Y', Name='Create Plan', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Create Plan',Updated=TO_TIMESTAMP('2010-02-15 13:05:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53258
;

-- Feb 15, 2010 1:05:42 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53258
;

-- Feb 15, 2010 1:05:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58994,53258,0,20,53260,'IsCreatePlan',TO_TIMESTAMP('2010-02-15 13:05:42','YYYY-MM-DD HH24:MI:SS'),0,'Indicates whether planned orders will be generated by MRP','EE01',1,'Indicates whether planned orders will be generated by MRP, if this flag is not just MRP generate a ''Create'' action notice','Y','N','N','N','N','Y','N','N','Y','N','Y','Create Plan',TO_TIMESTAMP('2010-02-15 13:05:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58994 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:43 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN IsCreatePlan CHAR(1) CHECK (IsCreatePlan IN ('Y','N')) NOT NULL
;

-- Feb 15, 2010 1:05:43 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='IsMPS', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Is MPS', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is MPS',Updated=TO_TIMESTAMP('2010-02-15 13:05:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53261
;

-- Feb 15, 2010 1:05:43 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Feb 15, 2010 1:05:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58995,53261,0,20,53260,'IsMPS',TO_TIMESTAMP('2010-02-15 13:05:43','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Is MPS',TO_TIMESTAMP('2010-02-15 13:05:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58995 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:48 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN IsMPS CHAR(1) DEFAULT NULL CHECK (IsMPS IN ('Y','N'))
;

-- Feb 15, 2010 1:05:49 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='IsPhantom', Description='Phantom Component', EntityType='D', Help='Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.', IsActive='Y', Name='Phantom', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Phantom',Updated=TO_TIMESTAMP('2010-02-15 13:05:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2788
;

-- Feb 15, 2010 1:05:49 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2788
;

-- Feb 15, 2010 1:05:50 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58996,2788,0,20,53260,'IsPhantom',TO_TIMESTAMP('2010-02-15 13:05:49','YYYY-MM-DD HH24:MI:SS'),0,'Phantom Component','EE01',1,'Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.','Y','N','N','N','N','Y','N','N','Y','N','Y','Phantom',TO_TIMESTAMP('2010-02-15 13:05:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:50 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58996 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:50 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN IsPhantom CHAR(1) CHECK (IsPhantom IN ('Y','N')) NOT NULL
;

-- Feb 15, 2010 1:05:50 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2010-02-15 13:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=454
;

-- Feb 15, 2010 1:05:50 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Feb 15, 2010 1:05:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58997,454,0,30,53260,'M_Product_ID',TO_TIMESTAMP('2010-02-15 13:05:50','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_TIMESTAMP('2010-02-15 13:05:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58997 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:51 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:51 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_TIMESTAMP('2010-02-15 13:05:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=459
;

-- Feb 15, 2010 1:05:51 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Feb 15, 2010 1:05:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58998,459,0,19,53260,'M_Warehouse_ID',TO_TIMESTAMP('2010-02-15 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,'-1','Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse',TO_TIMESTAMP('2010-02-15 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58998 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:52 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN M_Warehouse_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:53 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Max', Description='Maximum order quantity in UOM', EntityType='EE01', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.', IsActive='Y', Name='Maximum Order Qty', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Maximum Order Qty',Updated=TO_TIMESTAMP('2010-02-15 13:05:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53264
;

-- Feb 15, 2010 1:05:53 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53264
;

-- Feb 15, 2010 1:05:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58999,53264,0,29,53260,'Order_Max',TO_TIMESTAMP('2010-02-15 13:05:53','YYYY-MM-DD HH24:MI:SS'),0,'Maximum order quantity in UOM','EE01',10,'The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.','Y','N','N','N','N','N','N','N','Y','N','Y','Maximum Order Qty',TO_TIMESTAMP('2010-02-15 13:05:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58999 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:54 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Max NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:05:54 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Min', Description='Minimum order quantity in UOM', EntityType='D', Help='The Minimum Order Quantity indicates the smallest quantity of this product which can be ordered.', IsActive='Y', Name='Minimum Order Qty', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Minimum Order Qty',Updated=TO_TIMESTAMP('2010-02-15 13:05:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=942
;

-- Feb 15, 2010 1:05:54 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=942
;

-- Feb 15, 2010 1:05:55 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59000,942,0,29,53260,'Order_Min',TO_TIMESTAMP('2010-02-15 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,'Minimum order quantity in UOM','EE01',14,'The Minimum Order Quantity indicates the smallest quantity of this product which can be ordered.','Y','N','N','N','N','N','N','N','Y','N','Y','Minimum Order Qty',TO_TIMESTAMP('2010-02-15 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:55 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59000 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:55 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Min NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:05:55 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Pack', Description='Package order size in UOM (e.g. order set of 5 units)', EntityType='D', Help='The Order Pack Quantity indicates the number of units in each pack of this product.', IsActive='Y', Name='Order Pack Qty', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Pack Qty',Updated=TO_TIMESTAMP('2010-02-15 13:05:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=943
;

-- Feb 15, 2010 1:05:55 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=943
;

-- Feb 15, 2010 1:05:56 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59001,943,0,29,53260,'Order_Pack',TO_TIMESTAMP('2010-02-15 13:05:55','YYYY-MM-DD HH24:MI:SS'),0,'Package order size in UOM (e.g. order set of 5 units)','EE01',14,'The Order Pack Quantity indicates the number of units in each pack of this product.','Y','N','N','N','N','N','N','N','Y','N','Y','Order Pack Qty',TO_TIMESTAMP('2010-02-15 13:05:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:56 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59001 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:56 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Pack NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:05:56 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Period', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Order Period', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Period',Updated=TO_TIMESTAMP('2010-02-15 13:05:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53265
;

-- Feb 15, 2010 1:05:56 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53265
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59002,53265,0,29,53260,'Order_Period',TO_TIMESTAMP('2010-02-15 13:05:56','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Order Period',TO_TIMESTAMP('2010-02-15 13:05:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59002 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Period NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Policy', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Order Policy', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Policy',Updated=TO_TIMESTAMP('2010-02-15 13:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53266
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53266
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_Product_Planning Order Policy', ValidationType='L',Updated=TO_TIMESTAMP('2010-02-15 13:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53228
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53228
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List SET AD_Reference_ID=53228, Description=NULL, EntityType='EE01', IsActive='Y', Name='Fixed Order Quantity', Value='FOQ',Updated=TO_TIMESTAMP('2010-02-15 13:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53272
;

-- Feb 15, 2010 1:05:57 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53272
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List SET AD_Reference_ID=53228, Description=NULL, EntityType='EE01', IsActive='Y', Name='Lot-for-Lot', Value='LFL',Updated=TO_TIMESTAMP('2010-02-15 13:05:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53273
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53273
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List SET AD_Reference_ID=53228, Description=NULL, EntityType='EE01', IsActive='Y', Name='Period Order Quantity', Value='POQ',Updated=TO_TIMESTAMP('2010-02-15 13:05:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53274
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53274
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59003,53266,0,17,53228,53260,'Order_Policy',TO_TIMESTAMP('2010-02-15 13:05:58','YYYY-MM-DD HH24:MI:SS'),0,'EE01',3,'Y','N','N','N','N','N','N','N','Y','N','Y','Order Policy',TO_TIMESTAMP('2010-02-15 13:05:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59003 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:58 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Policy VARCHAR(3) DEFAULT NULL 
;

-- Feb 15, 2010 1:05:59 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Order_Qty', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Order Qty', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Qty',Updated=TO_TIMESTAMP('2010-02-15 13:05:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53267
;

-- Feb 15, 2010 1:05:59 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53267
;

-- Feb 15, 2010 1:05:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59004,53267,0,29,53260,'Order_Qty',TO_TIMESTAMP('2010-02-15 13:05:59','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Order Qty',TO_TIMESTAMP('2010-02-15 13:05:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:05:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59004 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:05:59 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Order_Qty NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:06:00 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Planner_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Planner', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Planner',Updated=TO_TIMESTAMP('2010-02-15 13:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53269
;

-- Feb 15, 2010 1:06:00 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53269
;

-- Feb 15, 2010 1:06:00 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2010-02-15 13:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Feb 15, 2010 1:06:00 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Feb 15, 2010 1:06:00 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Feb 15, 2010 1:06:01 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59005,53269,0,18,110,53260,164,'Planner_ID',TO_TIMESTAMP('2010-02-15 13:06:00','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Planner',TO_TIMESTAMP('2010-02-15 13:06:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:01 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59005 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:01 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Planner_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:01 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', EntityType='EE01', Help=NULL, IsActive='Y', Name='BOM & Formula', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM & Formula',Updated=TO_TIMESTAMP('2010-02-15 13:06:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53245
;

-- Feb 15, 2010 1:06:01 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53245
;

-- Feb 15, 2010 1:06:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59006,53245,0,19,53260,'PP_Product_BOM_ID',TO_TIMESTAMP('2010-02-15 13:06:01','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','BOM & Formula',TO_TIMESTAMP('2010-02-15 13:06:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59006 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:02 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN PP_Product_BOM_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:02 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='SafetyStock', Description='Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs', EntityType='EE01', Help='Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock', IsActive='Y', Name='Safety Stock Qty', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Safety Stock Qty',Updated=TO_TIMESTAMP('2010-02-15 13:06:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53354
;

-- Feb 15, 2010 1:06:02 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53354
;

-- Feb 15, 2010 1:06:03 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59007,53354,0,29,53260,'SafetyStock',TO_TIMESTAMP('2010-02-15 13:06:03','YYYY-MM-DD HH24:MI:SS'),0,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs','EE01',22,'Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','Y','N','N','N','N','N','N','N','Y','N','Y','Safety Stock Qty',TO_TIMESTAMP('2010-02-15 13:06:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:03 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59007 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:03 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN SafetyStock NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:06:03 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='S_Resource_ID', Description='Resource', EntityType='D', Help=NULL, IsActive='Y', Name='Resource', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Resource',Updated=TO_TIMESTAMP('2010-02-15 13:06:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1777
;

-- Feb 15, 2010 1:06:03 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1777
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Manufacturing Resources', EntityType='EE01', Help=NULL, IsActive='Y', Name='S_Resource_Manufacturing', ValidationType='T',Updated=TO_TIMESTAMP('2010-02-15 13:06:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53320
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53320
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Ref_Table SET AD_Table_ID = 487, AD_Display = 6853, AD_Key = 6862, isValueDisplayed = 'N', OrderByClause = '', EntityType ='EE01', WhereClause = '' WHERE AD_Reference_ID = 53320
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Val_Rule SET Code='IsManufacturingResource=''Y'' AND ManufacturingResourceType=''PT''', Description=NULL, EntityType='EE01', IsActive='Y', Name='S_Resource Plant', Type='S',Updated=TO_TIMESTAMP('2010-02-15 13:06:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52002
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59008,1777,0,18,53320,53260,52002,'S_Resource_ID',TO_TIMESTAMP('2010-02-15 13:06:04','YYYY-MM-DD HH24:MI:SS'),0,'-1','Resource','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Resource',TO_TIMESTAMP('2010-02-15 13:06:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59008 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:04 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN S_Resource_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:05 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='TimeFence', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Time Fence', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Time Fence',Updated=TO_TIMESTAMP('2010-02-15 13:06:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53270
;

-- Feb 15, 2010 1:06:05 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53270
;

-- Feb 15, 2010 1:06:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59009,53270,0,29,53260,'TimeFence',TO_TIMESTAMP('2010-02-15 13:06:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Time Fence',TO_TIMESTAMP('2010-02-15 13:06:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59009 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:07 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN TimeFence NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:06:07 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='TransfertTime', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Transfert Time', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Transfert Time',Updated=TO_TIMESTAMP('2010-02-15 13:06:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53271
;

-- Feb 15, 2010 1:06:07 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53271
;

-- Feb 15, 2010 1:06:08 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59010,53271,0,29,53260,'TransfertTime',TO_TIMESTAMP('2010-02-15 13:06:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Transfert Time',TO_TIMESTAMP('2010-02-15 13:06:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:08 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59010 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:08 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN TransfertTime NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:06:08 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='WorkingTime', Description='Workflow Simulation Execution Time', EntityType='D', Help='Amount of time the performer of the activity needs to perform the task in Duration Unit', IsActive='Y', Name='Working Time', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Working Time',Updated=TO_TIMESTAMP('2010-02-15 13:06:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2333
;

-- Feb 15, 2010 1:06:08 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2333
;

-- Feb 15, 2010 1:06:09 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59011,2333,0,29,53260,'WorkingTime',TO_TIMESTAMP('2010-02-15 13:06:08','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Execution Time','EE01',22,'Amount of time the performer of the activity needs to perform the task in Duration Unit','Y','N','N','N','N','N','N','N','Y','N','Y','Working Time',TO_TIMESTAMP('2010-02-15 13:06:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:09 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59011 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:09 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN WorkingTime NUMERIC DEFAULT NULL 
;

-- Feb 15, 2010 1:06:09 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', EntityType='EE01', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

', IsActive='Y', Name='Yield %', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Yield %',Updated=TO_TIMESTAMP('2010-02-15 13:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53272
;

-- Feb 15, 2010 1:06:09 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53272
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59012,53272,0,11,53260,'Yield',TO_TIMESTAMP('2010-02-15 13:06:09','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',22,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','N','N','N','N','N','N','N','Y','N','Y','Yield %',TO_TIMESTAMP('2010-02-15 13:06:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59012 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Yield NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='DatePromised', Description='Date Order was promised', EntityType='D', Help='The Date Promised indicates the date, if any, that an Order was promised for.', IsActive='Y', Name='Date Promised', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date Promised',Updated=TO_TIMESTAMP('2010-02-15 13:06:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=269
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=269
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2010-02-15 13:06:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Feb 15, 2010 1:06:10 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Feb 15, 2010 1:06:11 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59013,269,0,15,53260,'DatePromised',TO_TIMESTAMP('2010-02-15 13:06:10','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',7,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','Y','N','Y','Date Promised',TO_TIMESTAMP('2010-02-15 13:06:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:11 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59013 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:11 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN DatePromised TIMESTAMP DEFAULT NULL 
;

-- Feb 15, 2010 1:06:11 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='M_Forecast_ID', Description='Material Forecast', EntityType='D', Help='Material Forecast', IsActive='Y', Name='Forecast', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast',Updated=TO_TIMESTAMP('2010-02-15 13:06:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2498
;

-- Feb 15, 2010 1:06:11 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2498
;

-- Feb 15, 2010 1:06:12 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59014,2498,0,19,53260,'M_Forecast_ID',TO_TIMESTAMP('2010-02-15 13:06:11','YYYY-MM-DD HH24:MI:SS'),0,'Material Forecast','EE01',22,'Material Forecast','Y','N','N','N','N','N','N','N','Y','N','Y','Forecast',TO_TIMESTAMP('2010-02-15 13:06:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:12 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59014 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:12 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN M_Forecast_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:12 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_TIMESTAMP('2010-02-15 13:06:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=526
;

-- Feb 15, 2010 1:06:12 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- Feb 15, 2010 1:06:13 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59015,526,0,29,53260,'Qty',TO_TIMESTAMP('2010-02-15 13:06:12','YYYY-MM-DD HH24:MI:SS'),0,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','Y','N','N','Y','N','Y','Quantity',TO_TIMESTAMP('2010-02-15 13:06:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:13 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59015 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:13 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Qty NUMERIC NOT NULL
;

-- Feb 15, 2010 1:06:14 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59016,1063,0,18,53260,'SalesRep_ID',TO_TIMESTAMP('2010-02-15 13:06:13','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_User_ID@','Sales Representative or Company Agent','EE01',22,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','Y','Sales Representative',TO_TIMESTAMP('2010-02-15 13:06:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:14 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59016 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:14 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN SalesRep_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:14 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='ProductValue', Description='Key of the Product', EntityType='D', Help=NULL, IsActive='Y', Name='Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Key',Updated=TO_TIMESTAMP('2010-02-15 13:06:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1675
;

-- Feb 15, 2010 1:06:14 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1675
;

-- Feb 15, 2010 1:06:15 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59017,1675,0,10,53260,'ProductValue',TO_TIMESTAMP('2010-02-15 13:06:14','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Key',TO_TIMESTAMP('2010-02-15 13:06:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:15 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59017 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:15 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN ProductValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:15 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='WarehouseValue', Description='Key of the Warehouse', EntityType='D', Help='Key to identify the Warehouse', IsActive='Y', Name='Warehouse Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse Key',Updated=TO_TIMESTAMP('2010-02-15 13:06:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2070
;

-- Feb 15, 2010 1:06:15 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2070
;

-- Feb 15, 2010 1:06:16 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59018,2070,0,10,53260,'WarehouseValue',TO_TIMESTAMP('2010-02-15 13:06:15','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Warehouse','EE01',40,'Key to identify the Warehouse','Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse Key',TO_TIMESTAMP('2010-02-15 13:06:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:16 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59018 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:16 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN WarehouseValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:16 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='OrgValue', Description='Key of the Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Key',Updated=TO_TIMESTAMP('2010-02-15 13:06:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2115
;

-- Feb 15, 2010 1:06:16 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2115
;

-- Feb 15, 2010 1:06:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59019,2115,0,10,53260,'OrgValue',TO_TIMESTAMP('2010-02-15 13:06:16','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Key',TO_TIMESTAMP('2010-02-15 13:06:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59019 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:17 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN OrgValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54118,0,'NetworkDistributionValue',TO_TIMESTAMP('2010-02-15 13:06:17','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Network Distribution','EE01','Y','Network Distribution Key','Network Distribution Key',TO_TIMESTAMP('2010-02-15 13:06:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:17 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54118 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:06:18 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59020,54118,0,10,53260,'NetworkDistributionValue',TO_TIMESTAMP('2010-02-15 13:06:17','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Network Distribution','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Network Distribution Key',TO_TIMESTAMP('2010-02-15 13:06:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:18 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59020 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:18 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN NetworkDistributionValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:18 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54119,0,'Product_BOM_Value',TO_TIMESTAMP('2010-02-15 13:06:18','YYYY-MM-DD HH24:MI:SS'),0,'Key of Product BOM','U','Y','Product BOM Key','Product BOM Key',TO_TIMESTAMP('2010-02-15 13:06:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:18 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54119 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:06:19 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59021,54119,0,10,53260,'Product_BOM_Value',TO_TIMESTAMP('2010-02-15 13:06:18','YYYY-MM-DD HH24:MI:SS'),0,'Key of Product BOM','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Product BOM Key',TO_TIMESTAMP('2010-02-15 13:06:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:19 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59021 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:19 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN Product_BOM_Value VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54120,0,'ForecastValue',TO_TIMESTAMP('2010-02-15 13:06:19','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Forecast','EE01','Y','Forecast Key','Forecast Key',TO_TIMESTAMP('2010-02-15 13:06:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54120 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:06:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59022,54120,0,10,53260,'ForecastValue',TO_TIMESTAMP('2010-02-15 13:06:20','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Forecast','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Forecast Key',TO_TIMESTAMP('2010-02-15 13:06:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:20 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59022 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:20 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN ForecastValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:21 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54121,0,'ResourceValue',TO_TIMESTAMP('2010-02-15 13:06:20','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Resource','EE01','Y','Resource Key','Resource Key',TO_TIMESTAMP('2010-02-15 13:06:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:21 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54121 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:06:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59023,54121,0,10,53260,'ResourceValue',TO_TIMESTAMP('2010-02-15 13:06:21','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Resource','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Resource Key',TO_TIMESTAMP('2010-02-15 13:06:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59023 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:22 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN ResourceValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54122,0,'PlannerValue',TO_TIMESTAMP('2010-02-15 13:06:22','YYYY-MM-DD HH24:MI:SS'),0,'Search Key of the Planning','EE01','Y','Planner Key','Planner Key',TO_TIMESTAMP('2010-02-15 13:06:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:22 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54122 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 15, 2010 1:06:23 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59024,54122,0,10,53260,'PlannerValue',TO_TIMESTAMP('2010-02-15 13:06:22','YYYY-MM-DD HH24:MI:SS'),0,'Search Key of the Planning','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Planner Key',TO_TIMESTAMP('2010-02-15 13:06:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:23 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59024 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:23 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN PlannerValue VARCHAR(40) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:23 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='M_ForecastLine_ID', Description='Forecast Line', EntityType='D', Help='Forecast of Product Qyantity by Period', IsActive='Y', Name='Forecast Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Line',Updated=TO_TIMESTAMP('2010-02-15 13:06:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2499
;

-- Feb 15, 2010 1:06:23 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2499
;

-- Feb 15, 2010 1:06:24 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59025,2499,0,19,53260,'M_ForecastLine_ID',TO_TIMESTAMP('2010-02-15 13:06:23','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Line','EE01',10,'Forecast of Product Qyantity by Period','Y','N','N','N','N','N','N','N','Y','N','N','Forecast Line',TO_TIMESTAMP('2010-02-15 13:06:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:24 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59025 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:24 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN M_ForecastLine_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:24 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='PP_Product_Planning_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Product Planning', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Planning',Updated=TO_TIMESTAMP('2010-02-15 13:06:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53268
;

-- Feb 15, 2010 1:06:24 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53268
;

-- Feb 15, 2010 1:06:25 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59026,53268,0,19,53260,'PP_Product_Planning_ID',TO_TIMESTAMP('2010-02-15 13:06:24','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Product Planning',TO_TIMESTAMP('2010-02-15 13:06:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:25 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59026 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:25 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN PP_Product_Planning_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element SET ColumnName='VendorProductNo', Description='Product Key of the Business Partner', EntityType='D', Help='The Business Partner Product Key identifies the number used by the Business Partner for this product. It can be printed on orders and invoices when you include the Product Key in the print format.', IsActive='Y', Name='Partner Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BPartner Product Key',Updated=TO_TIMESTAMP('2010-02-15 13:06:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=623
;

-- Feb 15, 2010 1:06:25 PM CST
-- Create new importer for Planning Data and Forecast
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=623
;

-- Feb 15, 2010 1:06:31 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59027,623,0,10,53260,'VendorProductNo',TO_TIMESTAMP('2010-02-15 13:06:25','YYYY-MM-DD HH24:MI:SS'),0,'Product Key of the Business Partner','EE01',30,'The Business Partner Product Key identifies the number used by the Business Partner for this product. It can be printed on orders and invoices when you include the Product Key in the print format.','Y','N','N','N','N','N','N','N','Y','N','Y','Partner Product Key',TO_TIMESTAMP('2010-02-15 13:06:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 15, 2010 1:06:31 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59027 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 15, 2010 1:06:31 PM CST
-- Create new importer for Planning Data and Forecast
ALTER TABLE I_ProductPlanning ADD COLUMN VendorProductNo VARCHAR(30) DEFAULT NULL 
;

-- Feb 15, 2010 1:06:32 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53307,53260,53109,NULL,TO_TIMESTAMP('2010-02-15 13:06:31','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','Y','N','N','N','N','Import Product Planning','N',10,0,TO_TIMESTAMP('2010-02-15 13:06:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:32 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53307 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Feb 15, 2010 1:06:34 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58982,58726,0,53307,TO_TIMESTAMP('2010-02-15 13:06:32','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Active',0,0,TO_TIMESTAMP('2010-02-15 13:06:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:34 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58726 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:35 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59011,58727,0,53307,TO_TIMESTAMP('2010-02-15 13:06:34','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Execution Time',22,'EE01','Amount of time the performer of the activity needs to perform the task in Duration Unit','Y','Y','N','N','N','N','Y','Working Time',0,0,TO_TIMESTAMP('2010-02-15 13:06:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:35 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58727 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:36 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58978,58728,0,53307,TO_TIMESTAMP('2010-02-15 13:06:35','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2010-02-15 13:06:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:36 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58728 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:37 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59026,50010,58729,0,53307,TO_TIMESTAMP('2010-02-15 13:06:36','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','Y','Y','Product Planning',20,0,TO_TIMESTAMP('2010-02-15 13:06:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:37 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58729 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:38 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59019,50010,58730,0,53307,TO_TIMESTAMP('2010-02-15 13:06:37','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization',22,'EE01','Y','Y','Y','N','N','N','N','Org Key',30,0,TO_TIMESTAMP('2010-02-15 13:06:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:38 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58730 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:39 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58979,50010,58731,0,53307,TO_TIMESTAMP('2010-02-15 13:06:38','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',40,0,TO_TIMESTAMP('2010-02-15 13:06:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:39 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58731 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:39 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59017,50010,58732,0,53307,TO_TIMESTAMP('2010-02-15 13:06:39','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Product',22,'EE01','Y','Y','Y','N','N','N','N','Product Key',50,0,TO_TIMESTAMP('2010-02-15 13:06:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:39 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58732 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58997,50010,58733,0,53307,TO_TIMESTAMP('2010-02-15 13:06:39','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',22,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','Y','Product',60,0,TO_TIMESTAMP('2010-02-15 13:06:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58733 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59016,50010,58734,0,53307,TO_TIMESTAMP('2010-02-15 13:06:40','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent',22,'EE01','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','Y','N','N','N','N','Sales Representative',70,0,TO_TIMESTAMP('2010-02-15 13:06:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:40 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58734 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59023,50010,58735,0,53307,TO_TIMESTAMP('2010-02-15 13:06:40','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Resource',22,'EE01','Y','Y','Y','N','N','N','N','Resource Key',80,0,TO_TIMESTAMP('2010-02-15 13:06:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58735 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59008,50010,58736,0,53307,TO_TIMESTAMP('2010-02-15 13:06:41','YYYY-MM-DD HH24:MI:SS'),0,'Resource',22,'EE01','Y','Y','Y','N','N','N','Y','Resource',90,0,TO_TIMESTAMP('2010-02-15 13:06:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:41 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58736 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:42 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59018,50010,58737,0,53307,TO_TIMESTAMP('2010-02-15 13:06:41','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Warehouse',22,'EE01','Key to identify the Warehouse','Y','Y','Y','N','N','N','N','Warehouse Key',100,0,TO_TIMESTAMP('2010-02-15 13:06:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:42 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58737 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58998,50010,58738,0,53307,TO_TIMESTAMP('2010-02-15 13:06:42','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',22,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','Y','Warehouse',110,0,TO_TIMESTAMP('2010-02-15 13:06:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58738 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59024,50010,58739,0,53307,TO_TIMESTAMP('2010-02-15 13:06:43','YYYY-MM-DD HH24:MI:SS'),0,'Search Key of the Planning',22,'EE01','Y','Y','Y','N','N','N','N','Planner Key',120,0,TO_TIMESTAMP('2010-02-15 13:06:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:43 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58739 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:44 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59005,50010,58740,0,53307,TO_TIMESTAMP('2010-02-15 13:06:43','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','Y','Planner',130,0,TO_TIMESTAMP('2010-02-15 13:06:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:44 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58740 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:45 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59021,50010,58741,0,53307,TO_TIMESTAMP('2010-02-15 13:06:44','YYYY-MM-DD HH24:MI:SS'),0,'Key of Product BOM',22,'EE01','Y','Y','Y','N','N','N','N','Product BOM Key',140,0,TO_TIMESTAMP('2010-02-15 13:06:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:45 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58741 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:46 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59006,50010,58742,0,53307,TO_TIMESTAMP('2010-02-15 13:06:45','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',22,'EE01','Y','Y','Y','N','N','N','Y','BOM & Formula',150,0,TO_TIMESTAMP('2010-02-15 13:06:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:46 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58742 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:46 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58991,50010,58743,0,53307,TO_TIMESTAMP('2010-02-15 13:06:46','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks',60,'EE01','The Workflow field identifies a unique Workflow in the system.','Y','Y','Y','N','N','N','N','Workflow',160,0,TO_TIMESTAMP('2010-02-15 13:06:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:46 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58743 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:47 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59020,50010,58744,0,53307,TO_TIMESTAMP('2010-02-15 13:06:46','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Network Distribution',22,'EE01','Y','Y','Y','N','N','N','N','Network Distribution Key',170,0,TO_TIMESTAMP('2010-02-15 13:06:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:47 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58744 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58992,50010,58745,0,53307,TO_TIMESTAMP('2010-02-15 13:06:47','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','Y','Network Distribution',180,0,TO_TIMESTAMP('2010-02-15 13:06:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58745 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58995,50010,58746,0,53307,TO_TIMESTAMP('2010-02-15 13:06:48','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','Is MPS',190,0,TO_TIMESTAMP('2010-02-15 13:06:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:48 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58746 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:49 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58994,50010,58747,0,53307,TO_TIMESTAMP('2010-02-15 13:06:48','YYYY-MM-DD HH24:MI:SS'),0,'Indicates whether planned orders will be generated by MRP',1,'EE01','Indicates whether planned orders will be generated by MRP, if this flag is not just MRP generate a ''Create'' action notice','Y','Y','Y','N','N','N','Y','Create Plan',200,0,TO_TIMESTAMP('2010-02-15 13:06:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:49 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58747 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:49 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58993,50010,58748,0,53307,TO_TIMESTAMP('2010-02-15 13:06:49','YYYY-MM-DD HH24:MI:SS'),0,'Promised days between order and delivery',10,'EE01','The Promised Delivery Time indicates the number of days between the order date and the date that delivery was promised.','Y','Y','Y','N','N','N','N','Promised Delivery Time',210,0,TO_TIMESTAMP('2010-02-15 13:06:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:49 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58748 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:50 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59009,50010,58749,0,53307,TO_TIMESTAMP('2010-02-15 13:06:49','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','Y','Time Fence',220,0,TO_TIMESTAMP('2010-02-15 13:06:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:50 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58749 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59010,50010,58750,0,53307,TO_TIMESTAMP('2010-02-15 13:06:50','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','Transfert Time',230,0,TO_TIMESTAMP('2010-02-15 13:06:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58750 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59003,50010,58751,0,53307,TO_TIMESTAMP('2010-02-15 13:06:51','YYYY-MM-DD HH24:MI:SS'),0,3,'EE01','Y','Y','Y','N','N','N','Y','Order Policy',240,0,TO_TIMESTAMP('2010-02-15 13:06:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:51 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58751 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59002,50010,58752,0,53307,TO_TIMESTAMP('2010-02-15 13:06:51','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','Order Period',250,0,TO_TIMESTAMP('2010-02-15 13:06:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58752 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59004,50010,58753,0,53307,TO_TIMESTAMP('2010-02-15 13:06:52','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','Y','Order Qty',260,0,TO_TIMESTAMP('2010-02-15 13:06:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:52 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58753 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:53 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59001,50010,58754,0,53307,TO_TIMESTAMP('2010-02-15 13:06:52','YYYY-MM-DD HH24:MI:SS'),0,'Package order size in UOM (e.g. order set of 5 units)',14,'EE01','The Order Pack Quantity indicates the number of units in each pack of this product.','Y','Y','Y','N','N','N','N','Order Pack Qty',270,0,TO_TIMESTAMP('2010-02-15 13:06:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:53 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58754 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59000,50010,58755,0,53307,TO_TIMESTAMP('2010-02-15 13:06:53','YYYY-MM-DD HH24:MI:SS'),0,'Minimum order quantity in UOM',14,'EE01','The Minimum Order Quantity indicates the smallest quantity of this product which can be ordered.','Y','Y','Y','N','N','N','Y','Minimum Order Qty',280,0,TO_TIMESTAMP('2010-02-15 13:06:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58755 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58999,50010,58756,0,53307,TO_TIMESTAMP('2010-02-15 13:06:54','YYYY-MM-DD HH24:MI:SS'),0,'Maximum order quantity in UOM',10,'EE01','The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.','Y','Y','Y','N','N','N','N','Maximum Order Qty',290,0,TO_TIMESTAMP('2010-02-15 13:06:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:54 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58756 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:55 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59007,50010,58757,0,53307,TO_TIMESTAMP('2010-02-15 13:06:54','YYYY-MM-DD HH24:MI:SS'),0,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs',22,'EE01','Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','Y','Y','Y','N','N','N','Y','Safety Stock Qty',300,0,TO_TIMESTAMP('2010-02-15 13:06:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:55 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58757 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:56 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59012,50010,58758,0,53307,TO_TIMESTAMP('2010-02-15 13:06:55','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent',22,'EE01','ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','Y','Y','N','N','N','N','Yield %',310,0,TO_TIMESTAMP('2010-02-15 13:06:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:56 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58758 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58996,50010,58759,0,53307,TO_TIMESTAMP('2010-02-15 13:06:56','YYYY-MM-DD HH24:MI:SS'),0,'Phantom Component',1,'EE01','Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.','Y','Y','Y','N','N','N','Y','Phantom',320,0,TO_TIMESTAMP('2010-02-15 13:06:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58759 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58985,106,58760,0,53307,TO_TIMESTAMP('2010-02-15 13:06:57','YYYY-MM-DD HH24:MI:SS'),0,'The Key of the Business Partner',22,'EE01','Y','Y','Y','N','N','N','N','Business Partner Key',330,0,TO_TIMESTAMP('2010-02-15 13:06:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:57 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58760 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:58 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58986,106,58761,0,53307,TO_TIMESTAMP('2010-02-15 13:06:57','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',22,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','Y','Business Partner ',340,0,TO_TIMESTAMP('2010-02-15 13:06:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:58 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58761 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59027,106,58762,0,53307,TO_TIMESTAMP('2010-02-15 13:06:58','YYYY-MM-DD HH24:MI:SS'),0,'Product Key of the Business Partner',22,'EE01','The Business Partner Product Key identifies the number used by the Business Partner for this product. It can be printed on orders and invoices when you include the Product Key in the print format.','Y','Y','Y','N','N','N','N','Partner Product Key',350,0,TO_TIMESTAMP('2010-02-15 13:06:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58762 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:06:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59022,106,58763,0,53307,TO_TIMESTAMP('2010-02-15 13:06:59','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Forecast',22,'EE01','Y','Y','Y','N','N','N','N','Forecast Key',360,0,TO_TIMESTAMP('2010-02-15 13:06:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:06:59 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58763 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:00 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59014,106,58764,0,53307,TO_TIMESTAMP('2010-02-15 13:06:59','YYYY-MM-DD HH24:MI:SS'),0,'Material Forecast',22,'EE01','Material Forecast','Y','Y','Y','N','N','N','N','Forecast',370,0,TO_TIMESTAMP('2010-02-15 13:06:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:00 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58764 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:01 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59025,106,58765,0,53307,TO_TIMESTAMP('2010-02-15 13:07:00','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Line',10,'EE01','Forecast of Product Qyantity by Period','Y','Y','Y','N','N','Y','Y','Forecast Line',380,0,TO_TIMESTAMP('2010-02-15 13:07:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:01 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58765 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59013,106,58766,0,53307,TO_TIMESTAMP('2010-02-15 13:07:01','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','Y','N','N','N','N','Date Promised',390,0,TO_TIMESTAMP('2010-02-15 13:07:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58766 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59015,106,58767,0,53307,TO_TIMESTAMP('2010-02-15 13:07:02','YYYY-MM-DD HH24:MI:SS'),0,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','Y','Quantity',400,0,TO_TIMESTAMP('2010-02-15 13:07:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:02 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58767 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:03 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58987,58768,0,53307,TO_TIMESTAMP('2010-02-15 13:07:02','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process',2000,'EE01','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','N','N','Y','N','Import Error Message',410,0,TO_TIMESTAMP('2010-02-15 13:07:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:03 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58768 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58988,58769,0,53307,TO_TIMESTAMP('2010-02-15 13:07:03','YYYY-MM-DD HH24:MI:SS'),0,'Has this import been processed',1,'EE01','The Imported check box indicates if this import has been processed.','Y','Y','Y','N','N','Y','N','Imported',420,0,TO_TIMESTAMP('2010-02-15 13:07:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58769 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58989,58770,0,53307,TO_TIMESTAMP('2010-02-15 13:07:04','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','Y','Y','Processed',430,0,TO_TIMESTAMP('2010-02-15 13:07:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:04 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58770 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:05 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58990,58771,0,53307,TO_TIMESTAMP('2010-02-15 13:07:04','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','N','Y','N','N','N','N','Import Product Planning Data',440,0,TO_TIMESTAMP('2010-02-15 13:07:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:05 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58771 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:06 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58977,58772,0,53307,TO_TIMESTAMP('2010-02-15 13:07:05','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','N','N','N','N','N','Import Product Planning',0,0,TO_TIMESTAMP('2010-02-15 13:07:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:06 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58772 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 15, 2010 1:07:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53264,0,53109,'W',TO_TIMESTAMP('2010-02-15 13:07:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','Import Product Planning',TO_TIMESTAMP('2010-02-15 13:07:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 15, 2010 1:07:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53264 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Feb 15, 2010 1:07:07 PM CST
-- Create new importer for Planning Data and Forecast
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 163,4, 10, 53264)
;


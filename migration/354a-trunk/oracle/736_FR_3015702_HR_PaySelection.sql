-- 2/12/2009 05:45:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53103,TO_DATE('2009-12-02 17:45:50','YYYY-MM-DD HH24:MI:SS'),100,'Select Payroll Concept for Payment','EE02','The Payroll Payment Selection Window allows you to select and process the Payroll Concepts you want to pay.
(a) You can create a Payment Selection and select the Payroll Concepts or generate based on Payroll Process.  You can change the amount to be paid or delete payroll concept, you do not want to pay.

(b) If you are happy with the Payment Selection, you create the payments.

(c) You print or export your payments in the Window Payroll Payment Print/Export','Y','N','N','N','Payroll Payment Selection','N',TO_DATE('2009-12-02 17:45:50','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- 2/12/2009 05:45:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53103 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- 2/12/2009 05:45:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53248,'3',TO_DATE('2009-12-02 17:45:55','YYYY-MM-DD HH24:MI:SS'),100,'HR Payment Selection','EE02','N','Y','N','Y','N','N','N','HR Payment Selection','L','HR_PaySelection',TO_DATE('2009-12-02 17:45:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:45:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53248 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 2/12/2009 05:45:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53358,TO_DATE('2009-12-02 17:45:56','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table HR_PaySelection',1,'Y','N','Y','Y','HR_PaySelection','N',1000000,TO_DATE('2009-12-02 17:45:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:45:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54089,0,'HR_PaySelection_ID',TO_DATE('2009-12-02 17:45:57','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','Payroll Payment Selection ID','Payroll Payment Selection ID',TO_DATE('2009-12-02 17:45:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:45:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54089 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 2/12/2009 05:45:57 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_DATE('2009-12-02 17:45:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=13
;

-- 2/12/2009 05:45:57 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- 2/12/2009 05:45:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58693,54089,0,13,53248,'HR_PaySelection_ID',TO_DATE('2009-12-02 17:45:57','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Payroll Payment Selection ID',TO_DATE('2009-12-02 17:45:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:45:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58693 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:45:58 PM CST
-- Payroll Payment Selection
CREATE TABLE HR_PaySelection (HR_PaySelection_ID NUMBER(10) NOT NULL, CONSTRAINT HR_PaySelection_Key PRIMARY KEY (HR_PaySelection_ID))
;

-- 2/12/2009 05:45:59 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2009-12-02 17:45:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- 2/12/2009 05:45:59 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- 2/12/2009 05:45:59 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2009-12-02 17:45:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- 2/12/2009 05:45:59 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- 2/12/2009 05:45:59 PM CST
-- Payroll Payment Selection
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2009-12-02 17:45:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- 2/12/2009 05:46:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58694,102,0,19,53248,129,'AD_Client_ID',TO_DATE('2009-12-02 17:45:59','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE02',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','Y','Client',TO_DATE('2009-12-02 17:45:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58694 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:00 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:00 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2009-12-02 17:46:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- 2/12/2009 05:46:00 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58695,113,0,19,53248,104,'AD_Org_ID',TO_DATE('2009-12-02 17:46:00','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE02',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','Y','Organization',TO_DATE('2009-12-02 17:46:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58695 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_DATE('2009-12-02 17:46:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- 2/12/2009 05:46:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58696,348,0,20,53248,'IsActive',TO_DATE('2009-12-02 17:46:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE02',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2009-12-02 17:46:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58696 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_DATE('2009-12-02 17:46:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- 2/12/2009 05:46:02 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- 2/12/2009 05:46:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58697,245,0,16,53248,'Created',TO_DATE('2009-12-02 17:46:02','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE02',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created',TO_DATE('2009-12-02 17:46:02','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58697 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:03 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Created DATE NOT NULL
;

-- 2/12/2009 05:46:03 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_DATE('2009-12-02 17:46:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- 2/12/2009 05:46:03 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58698,607,0,16,53248,'Updated',TO_DATE('2009-12-02 17:46:03','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE02',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated',TO_DATE('2009-12-02 17:46:03','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58698 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Updated DATE NOT NULL
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_DATE('2009-12-02 17:46:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2009-12-02 17:46:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58699,246,0,19,110,53248,'CreatedBy',TO_DATE('2009-12-02 17:46:04','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE02',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created By',TO_DATE('2009-12-02 17:46:04','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58699 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:04 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD CreatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:05 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_DATE('2009-12-02 17:46:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- 2/12/2009 05:46:05 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- 2/12/2009 05:46:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58700,608,0,19,110,53248,'UpdatedBy',TO_DATE('2009-12-02 17:46:05','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE02',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated By',TO_DATE('2009-12-02 17:46:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58700 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:06 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD UpdatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:06 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_BankAccount_ID', Description='Account at the Bank', EntityType='D', Help='The Bank Account identifies an account at this Bank.', IsActive='Y', Name='Bank Account', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Bank Account',Updated=TO_DATE('2009-12-02 17:46:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=836
;

-- 2/12/2009 05:46:06 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=836
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58701,836,0,19,53248,'C_BankAccount_ID',TO_DATE('2009-12-02 17:46:06','YYYY-MM-DD HH24:MI:SS'),100,'Account at the Bank','EE02',22,'The Bank Account identifies an account at this Bank.','Y','N','N','N','N','Y','N','N','Y','N','Y','Bank Account',TO_DATE('2009-12-02 17:46:06','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58701 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD C_BankAccount_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='CreateFrom', Description='Process which will generate a new document lines based on an existing document', EntityType='D', Help='The Create From process will create a new document based on information in an existing document selected by the user.', IsActive='Y', Name='Create lines from', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Create lines from',Updated=TO_DATE('2009-12-02 17:46:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1490
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1490
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=28
;

-- 2/12/2009 05:46:07 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- 2/12/2009 05:46:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53190,'1','org.eevolution.process.HRPaySelectionCreateFrom',TO_DATE('2009-12-02 17:46:07','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','N','N','N','Create From ...','Y',0,0,TO_DATE('2009-12-02 17:46:07','YYYY-MM-DD HH24:MI:SS'),100,'HR_PaySelection_CreateFrom',NULL)
;

-- 2/12/2009 05:46:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53190 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- 2/12/2009 05:46:09 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53393,0,53190,53365,19,'HR_Payroll_ID',TO_DATE('2009-12-02 17:46:08','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','Y','Y','N','Payroll',10,TO_DATE('2009-12-02 17:46:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:09 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53365 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:10 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52072,'HR_Process.HR_Payroll_ID = @HR_Payroll_ID@  AND HR_Process.DocStatus IN(''CO'',''CL'') AND HR_Process.HR_Process_ID IN(SELECT HR_Process_ID FROM HR_Movement WHERE HR_Concept_ID IN(SELECT HR_Concept_ID FROM HR_Concept WHERE IsPaid=''Y'') 
AND HR_Movement_ID NOT IN(SELECT HR_Movement_ID FROM HR_PaySelectionLine WHERE Processed=''Y''))',TO_DATE('2009-12-02 17:46:09','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','HR_Process of Payroll','S',TO_DATE('2009-12-02 17:46:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:11 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53407,0,53190,53366,19,52072,'HR_Process_ID',TO_DATE('2009-12-02 17:46:10','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','Y','Y','N','Payroll Process',11,TO_DATE('2009-12-02 17:46:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:11 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53366 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1383,0,53190,53367,19,'C_BP_Group_ID',TO_DATE('2009-12-02 17:46:11','YYYY-MM-DD HH24:MI:SS'),100,'-1','Business Partner Group','EE02',22,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','Business Partner Group',15,TO_DATE('2009-12-02 17:46:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53367 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:12 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=30
;

-- 2/12/2009 05:46:12 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- 2/12/2009 05:46:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52073,'C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID FROM HR_Movement m WHERE  m.HR_Process_ID = @HR_Process_ID@)',TO_DATE('2009-12-02 17:46:12','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','C_BPartner of Payroll Process','S',TO_DATE('2009-12-02 17:46:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53190,53368,30,52073,'C_BPartner_ID',TO_DATE('2009-12-02 17:46:12','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE02',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','Business Partner ',20,TO_DATE('2009-12-02 17:46:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53368 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=17
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='In & Out Payment Options', EntityType='D', Help=NULL, IsActive='Y', Name='_Payment Rule', ValidationType='L',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=195
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=195
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Cash', Value='B',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=332
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=332
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Direct Debit', Value='D',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=652
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=652
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Credit Card', Value='K',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=333
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=333
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Mixed', Value='M',Updated=TO_DATE('2009-12-02 17:46:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=52000
;

-- 2/12/2009 05:46:13 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=52000
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='On Credit', Value='P',Updated=TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=337
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=337
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Check', Value='S',Updated=TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=335
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=335
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List SET AD_Reference_ID=195, Description=NULL, EntityType='D', IsActive='Y', Name='Direct Deposit', Value='T',Updated=TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=334
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=334
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1143,0,53190,53369,17,195,'PaymentRule',TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice','EE02',1,'The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','Payment Rule',30,TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53369 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:15 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52074,'HR_Concept.HR_Concept_ID IN (SELECT HR_Concept_ID FROM HR_Movement m WHERE  m.HR_Process_ID = @HR_Process_ID@) AND HR_Concept.isPaid=''Y''',TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','HR_Concept of Payroll','S',TO_DATE('2009-12-02 17:46:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:15 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53398,0,53190,53370,19,52074,'HR_Concept_ID',TO_DATE('2009-12-02 17:46:15','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','Y','N','N','Payroll Concept',40,TO_DATE('2009-12-02 17:46:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:15 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53370 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:16 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52075,'HR_Job.HR_Job_ID IN (SELECT HR_Job_ID FROM HR_Movement m WHERE  m.HR_Process_ID = @HR_Process_ID@ AND m.HR_Job_ID=HR_Job.HR_Job_ID)',TO_DATE('2009-12-02 17:46:15','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','HR_Job_ID of Process','S',TO_DATE('2009-12-02 17:46:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:16 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53390,0,53190,53371,19,52075,'HR_Department_ID',TO_DATE('2009-12-02 17:46:16','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','Y','N','N','Payroll Department',50,TO_DATE('2009-12-02 17:46:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53371 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53392,0,53190,53372,19,'HR_Job_ID',TO_DATE('2009-12-02 17:46:17','YYYY-MM-DD HH24:MI:SS'),100,'EE02',12,'Y','Y','N','N','Payroll Job',60,TO_DATE('2009-12-02 17:46:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53372 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58702,1490,0,53190,28,53248,'CreateFrom',TO_DATE('2009-12-02 17:46:17','YYYY-MM-DD HH24:MI:SS'),100,'Process which will generate a new document lines based on an existing document','EE02',1,'The Create From process will create a new document based on information in an existing document selected by the user.','Y','N','N','N','N','N','N','N','Y','N','Y','Create lines from',TO_DATE('2009-12-02 17:46:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58702 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD CreateFrom CHAR(1) DEFAULT NULL 
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_DATE('2009-12-02 17:46:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=275
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- 2/12/2009 05:46:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- 2/12/2009 05:46:19 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58703,275,0,10,53248,'Description',TO_DATE('2009-12-02 17:46:18','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE02',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','Y','N','Y','Description',TO_DATE('2009-12-02 17:46:18','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:19 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58703 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:19 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- 2/12/2009 05:46:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsApproved', Description='Indicates if this document requires approval', EntityType='D', Help='The Approved checkbox indicates if this document requires approval before it can be processed.', IsActive='Y', Name='Approved', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Approved',Updated=TO_DATE('2009-12-02 17:46:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=351
;

-- 2/12/2009 05:46:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=351
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58704,351,0,20,53248,'IsApproved',TO_DATE('2009-12-02 17:46:19','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document requires approval','EE02',1,'The Approved checkbox indicates if this document requires approval before it can be processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Approved',TO_DATE('2009-12-02 17:46:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58704 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD IsApproved CHAR(1) CHECK (IsApproved IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=469
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58705,469,0,10,53248,'Name',TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','Alphanumeric identifier of the entity','EE02',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58705 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Name NVARCHAR2(60) NOT NULL
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='PayDate', Description='Date Payment made', EntityType='D', Help='The Payment Date indicates the date the payment was made.', IsActive='Y', Name='Payment date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payment date',Updated=TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1538
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1538
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- 2/12/2009 05:46:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- 2/12/2009 05:46:21 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58706,1538,0,15,53248,'PayDate',TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','Date Payment made','EE02',7,'The Payment Date indicates the date the payment was made.','Y','N','N','N','N','Y','N','N','Y','N','Y','Payment date',TO_DATE('2009-12-02 17:46:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:21 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58706 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:21 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD PayDate DATE NOT NULL
;

-- 2/12/2009 05:46:21 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_DATE('2009-12-02 17:46:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1047
;

-- 2/12/2009 05:46:21 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58707,1047,0,20,53248,'Processed',TO_DATE('2009-12-02 17:46:21','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','EE02',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_DATE('2009-12-02 17:46:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58707 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Processed CHAR(1) CHECK (Processed IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_DATE('2009-12-02 17:46:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=524
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53191,'1','org.eevolution.process.HRPaySelectionCreateCheck',TO_DATE('2009-12-02 17:46:22','YYYY-MM-DD HH24:MI:SS'),100,'Create Prepared Payments (Checks) to be paid','EE02','You create the actual Payments via Payroll Payment Print/Export','Y','N','N','N','Payroll Prepare Payment','Y',0,0,TO_DATE('2009-12-02 17:46:22','YYYY-MM-DD HH24:MI:SS'),100,'HR_PaySelection_CreatePayment',NULL)
;

-- 2/12/2009 05:46:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53191 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1143,0,53191,53373,17,195,'PaymentRule',TO_DATE('2009-12-02 17:46:22','YYYY-MM-DD HH24:MI:SS'),100,'EE02',0,'Y','Y','N','N','PaymentRule',10,TO_DATE('2009-12-02 17:46:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53373 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58708,524,0,53191,28,53248,'Processing',TO_DATE('2009-12-02 17:46:23','YYYY-MM-DD HH24:MI:SS'),100,'EE02',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','Process Now',TO_DATE('2009-12-02 17:46:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58708 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD Processing CHAR(1) NOT NULL
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='TotalAmt', Description='Total Amount', EntityType='D', Help='The Total Amount indicates the total document amount.', IsActive='Y', Name='Total Amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Total Amt',Updated=TO_DATE('2009-12-02 17:46:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1539
;

-- 2/12/2009 05:46:23 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1539
;

-- 2/12/2009 05:46:24 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=12
;

-- 2/12/2009 05:46:24 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- 2/12/2009 05:46:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58709,1539,0,12,53248,'TotalAmt',TO_DATE('2009-12-02 17:46:24','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','EE02',22,'The Total Amount indicates the total document amount.','Y','N','N','N','N','Y','N','N','Y','N','Y','Total Amount',TO_DATE('2009-12-02 17:46:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58709 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:24 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelection ADD TotalAmt NUMBER NOT NULL
;

-- 2/12/2009 05:46:25 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53293,53248,53103,NULL,TO_DATE('2009-12-02 17:46:24','YYYY-MM-DD HH24:MI:SS'),100,'EE02','N','Y','N','N','Y','N','Y','N','N','Payroll Payment Selection','N',10,0,TO_DATE('2009-12-02 17:46:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:25 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53293 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- 2/12/2009 05:46:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58707,58468,0,53293,TO_DATE('2009-12-02 17:46:25','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'EE02','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','Processed',0,0,TO_DATE('2009-12-02 17:46:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58468 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58694,58469,0,53293,TO_DATE('2009-12-02 17:46:26','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'EE02','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2009-12-02 17:46:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58469 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58695,58470,0,53293,TO_DATE('2009-12-02 17:46:26','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'EE02','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2009-12-02 17:46:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58470 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58705,58471,0,53293,TO_DATE('2009-12-02 17:46:27','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'EE02','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,0,TO_DATE('2009-12-02 17:46:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58471 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58703,58472,0,53293,TO_DATE('2009-12-02 17:46:28','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'EE02','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_DATE('2009-12-02 17:46:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58472 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:29 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58696,58473,0,53293,TO_DATE('2009-12-02 17:46:28','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE02','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_DATE('2009-12-02 17:46:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:29 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58473 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58701,58474,0,53293,TO_DATE('2009-12-02 17:46:29','YYYY-MM-DD HH24:MI:SS'),100,'Account at the Bank',22,'EE02','The Bank Account identifies an account at this Bank.','Y','Y','Y','N','N','N','N','Bank Account',60,0,TO_DATE('2009-12-02 17:46:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58474 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58706,58475,0,53293,TO_DATE('2009-12-02 17:46:31','YYYY-MM-DD HH24:MI:SS'),100,'Date Payment made',7,'EE02','The Payment Date indicates the date the payment was made.','Y','Y','Y','N','N','N','N','Payment date',70,0,TO_DATE('2009-12-02 17:46:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58475 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58704,58476,0,53293,TO_DATE('2009-12-02 17:46:31','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document requires approval',1,'EE02','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','Y','N','N','N','Y','Approved',80,0,TO_DATE('2009-12-02 17:46:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58476 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58693,58477,0,53293,TO_DATE('2009-12-02 17:46:32','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','Y','N','N','N','N','Payroll Payment Selection ID',90,0,TO_DATE('2009-12-02 17:46:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58477 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58709,58478,0,53293,TO_DATE('2009-12-02 17:46:33','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount',22,'EE02','The Total Amount indicates the total document amount.','Y','Y','Y','N','N','Y','N','Total Amount',100,0,TO_DATE('2009-12-02 17:46:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58478 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58702,58479,0,53293,TO_DATE('2009-12-02 17:46:33','YYYY-MM-DD HH24:MI:SS'),100,'Process which will generate a new document lines based on an existing document',1,'EE02','The Create From process will create a new document based on information in an existing document selected by the user.','Y','Y','Y','N','N','N','N','Create lines from',110,0,TO_DATE('2009-12-02 17:46:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58479 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58708,58480,0,53293,TO_DATE('2009-12-02 17:46:34','YYYY-MM-DD HH24:MI:SS'),100,1,'EE02','Y','Y','Y','N','N','N','Y','Process Now',120,0,TO_DATE('2009-12-02 17:46:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58480 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:46:39 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53249,'3',TO_DATE('2009-12-02 17:46:38','YYYY-MM-DD HH24:MI:SS'),100,'Payroll Payment Selection Line','EE02','N','Y','N','Y','N','N','N','Payroll Payment Selection Line','L','HR_PaySelectionLine',TO_DATE('2009-12-02 17:46:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:39 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53249 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 2/12/2009 05:46:42 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53359,TO_DATE('2009-12-02 17:46:39','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table HR_PaySelectionLine',1,'Y','N','Y','Y','HR_PaySelectionLine','N',1000000,TO_DATE('2009-12-02 17:46:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54090,0,'HR_PaySelectionLine_ID',TO_DATE('2009-12-02 17:46:42','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','Payroll Payment Selection Line ID','Payroll Payment Selection Line ID',TO_DATE('2009-12-02 17:46:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54090 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 2/12/2009 05:46:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58710,54090,0,13,53249,'HR_PaySelectionLine_ID',TO_DATE('2009-12-02 17:46:43','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Payroll Payment Selection Line ID',TO_DATE('2009-12-02 17:46:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58710 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:43 PM CST
-- Payroll Payment Selection
CREATE TABLE HR_PaySelectionLine (HR_PaySelectionLine_ID NUMBER(10) NOT NULL, CONSTRAINT HR_PaySelectionLine_Key PRIMARY KEY (HR_PaySelectionLine_ID))
;

-- 2/12/2009 05:46:44 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58711,102,0,19,53249,129,'AD_Client_ID',TO_DATE('2009-12-02 17:46:43','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE02',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','Y','Client',TO_DATE('2009-12-02 17:46:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:44 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58711 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:44 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58712,113,0,19,53249,104,'AD_Org_ID',TO_DATE('2009-12-02 17:46:44','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE02',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','Y','Organization',TO_DATE('2009-12-02 17:46:44','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58712 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58713,348,0,20,53249,'IsActive',TO_DATE('2009-12-02 17:46:45','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE02',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2009-12-02 17:46:45','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58713 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:45 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58714,245,0,16,53249,'Created',TO_DATE('2009-12-02 17:46:45','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE02',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created',TO_DATE('2009-12-02 17:46:45','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58714 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:46 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD Created DATE NOT NULL
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58715,607,0,16,53249,'Updated',TO_DATE('2009-12-02 17:46:46','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE02',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated',TO_DATE('2009-12-02 17:46:46','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58715 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD Updated DATE NOT NULL
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58716,246,0,19,110,53249,'CreatedBy',TO_DATE('2009-12-02 17:46:47','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE02',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created By',TO_DATE('2009-12-02 17:46:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58716 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:47 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD CreatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58717,608,0,19,110,53249,'UpdatedBy',TO_DATE('2009-12-02 17:46:47','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE02',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated By',TO_DATE('2009-12-02 17:46:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58717 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:48 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD UpdatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54091,0,'HR_PaySelectionCheck_ID',TO_DATE('2009-12-02 17:46:48','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','Payroll Pay Selection Check ID','Payroll Pay Selection Check ID',TO_DATE('2009-12-02 17:46:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:46:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54091 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 2/12/2009 05:46:49 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58718,54091,0,19,53249,'HR_PaySelectionCheck_ID',TO_DATE('2009-12-02 17:46:49','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Payroll Pay Selection Check ID',TO_DATE('2009-12-02 17:46:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:46:49 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58718 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:49 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD HR_PaySelectionCheck_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58719,54089,0,19,53249,'HR_PaySelection_ID',TO_DATE('2009-12-02 17:46:49','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','N','Y','Y','N','Y','N','N','Payroll Payment Selection ID',TO_DATE('2009-12-02 17:46:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58719 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD HR_PaySelection_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58720,275,0,10,53249,'Description',TO_DATE('2009-12-02 17:46:50','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE02',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_DATE('2009-12-02 17:46:50','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58720 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='DifferenceAmt', Description='Difference Amount', EntityType='D', Help=NULL, IsActive='Y', Name='Difference', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Difference',Updated=TO_DATE('2009-12-02 17:46:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1635
;

-- 2/12/2009 05:46:50 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1635
;

-- 2/12/2009 05:46:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58721,1635,0,12,53249,'DifferenceAmt',TO_DATE('2009-12-02 17:46:50','YYYY-MM-DD HH24:MI:SS'),100,'Difference Amount','EE02',22,'Y','N','N','N','N','Y','N','N','Y','N','N','Difference',TO_DATE('2009-12-02 17:46:50','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58721 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:51 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD DifferenceAmt NUMBER NOT NULL
;

-- 2/12/2009 05:46:51 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='DiscountAmt', Description='Calculated amount of discount', EntityType='D', Help='The Discount Amount indicates the discount amount for a document or line.', IsActive='Y', Name='Discount Amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Discount',Updated=TO_DATE('2009-12-02 17:46:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1395
;

-- 2/12/2009 05:46:51 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1395
;

-- 2/12/2009 05:46:52 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58722,1395,0,12,53249,'DiscountAmt',TO_DATE('2009-12-02 17:46:51','YYYY-MM-DD HH24:MI:SS'),100,'Calculated amount of discount','EE02',22,'The Discount Amount indicates the discount amount for a document or line.','Y','N','N','N','N','Y','N','N','Y','N','N','Discount Amount',TO_DATE('2009-12-02 17:46:51','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:52 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58722 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:52 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD DiscountAmt NUMBER NOT NULL
;

-- 2/12/2009 05:46:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsManual', Description='This is a manual process', EntityType='D', Help='The Manual check box indicates if the process will done manually.', IsActive='Y', Name='Manual', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Manual',Updated=TO_DATE('2009-12-02 17:46:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1474
;

-- 2/12/2009 05:46:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1474
;

-- 2/12/2009 05:46:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58723,1474,0,20,53249,'IsManual',TO_DATE('2009-12-02 17:46:52','YYYY-MM-DD HH24:MI:SS'),100,'This is a manual process','EE02',1,'The Manual check box indicates if the process will done manually.','Y','N','N','N','N','Y','N','N','Y','N','Y','Manual',TO_DATE('2009-12-02 17:46:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58723 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:53 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD IsManual CHAR(1) CHECK (IsManual IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsSOTrx', Description='This is a Sales Transaction', EntityType='D', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', IsActive='Y', Name='Sales Transaction', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sales Transaction',Updated=TO_DATE('2009-12-02 17:46:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1106
;

-- 2/12/2009 05:46:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1106
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58724,1106,0,20,53249,'IsSOTrx',TO_DATE('2009-12-02 17:46:53','YYYY-MM-DD HH24:MI:SS'),100,'This is a Sales Transaction','EE02',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','N','N','N','N','Y','N','N','Y','N','Y','Sales Transaction',TO_DATE('2009-12-02 17:46:53','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58724 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD IsSOTrx CHAR(1) CHECK (IsSOTrx IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Line', Description='Unique line for this document', EntityType='D', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', Name='Line No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Line No',Updated=TO_DATE('2009-12-02 17:46:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=439
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=439
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_DATE('2009-12-02 17:46:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=11
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58725,439,0,11,53249,'Line',TO_DATE('2009-12-02 17:46:54','YYYY-MM-DD HH24:MI:SS'),100,'@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@','Unique line for this document','EE02',22,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','Y','N','N','Y','N','Y','Line No',TO_DATE('2009-12-02 17:46:54','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58725 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:54 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD Line NUMBER(10) NOT NULL
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='OpenAmt', Description='Open item amount', EntityType='D', Help=NULL, IsActive='Y', Name='Open Amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Open',Updated=TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1526
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1526
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58726,1526,0,12,53249,'OpenAmt',TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),100,'Open item amount','EE02',22,'Y','N','N','N','N','Y','N','N','Y','N','N','Open Amount',TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58726 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD OpenAmt NUMBER NOT NULL
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='PayAmt', Description='Amount being paid', EntityType='D', Help='Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.', IsActive='Y', Name='Payment amount', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payment Amt',Updated=TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1477
;

-- 2/12/2009 05:46:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1477
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58727,1477,0,12,53249,'org.compiere.model.CalloutPaySelection.payAmt','PayAmt',TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),100,'Amount being paid','EE02',22,'Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.','Y','N','N','N','N','Y','N','N','Y','N','Y','Payment amount',TO_DATE('2009-12-02 17:46:55','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58727 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD PayAmt NUMBER NOT NULL
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='PaymentRule', Description='How you pay the invoice', EntityType='D', Help='The Payment Rule indicates the method of invoice payment.', IsActive='Y', Name='Payment Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payment Rule',Updated=TO_DATE('2009-12-02 17:46:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1143
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1143
;

-- 2/12/2009 05:46:56 PM CST
-- Payroll Payment Selection
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''B'' AND AD_Ref_List.Value <> ''M''', Description=NULL, EntityType='D', IsActive='Y', Name='All_Payment Rule - no Cash', Type='S',Updated=TO_DATE('2009-12-02 17:46:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=161
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58728,1143,0,17,195,53249,161,'PaymentRule',TO_DATE('2009-12-02 17:46:56','YYYY-MM-DD HH24:MI:SS'),100,'S','How you pay the invoice','EE02',1,'The Payment Rule indicates the method of invoice payment.','Y','N','N','N','N','Y','N','N','Y','N','Y','Payment Rule',TO_DATE('2009-12-02 17:46:56','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58728 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD PaymentRule CHAR(1) DEFAULT 'S' NOT NULL
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58729,1047,0,20,53249,'Processed',TO_DATE('2009-12-02 17:46:57','YYYY-MM-DD HH24:MI:SS'),100,'N','The document has been processed','EE02',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_DATE('2009-12-02 17:46:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58729 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:57 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:46:58 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='HR_Movement_ID', Description=NULL, EntityType='EE02', Help=NULL, IsActive='Y', Name='Payroll Movement', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payroll Movement',Updated=TO_DATE('2009-12-02 17:46:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53417
;

-- 2/12/2009 05:46:58 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53417
;

-- 2/12/2009 05:46:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58730,53417,0,19,53249,'HR_Movement_ID',TO_DATE('2009-12-02 17:46:58','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Payroll Movement',TO_DATE('2009-12-02 17:46:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:46:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58730 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:46:58 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionLine ADD HR_Movement_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:46:59 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,58719,0,53294,53249,53103,NULL,TO_DATE('2009-12-02 17:46:58','YYYY-MM-DD HH24:MI:SS'),100,'EE02','N','Y','N','N','Y','N','N','N','N','Payroll Payment Selection Line','N',20,0,TO_DATE('2009-12-02 17:46:58','YYYY-MM-DD HH24:MI:SS'),100,'HR_PaySelection_ID=@HR_PaySelection_ID@')
;

-- 2/12/2009 05:46:59 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53294 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- 2/12/2009 05:47:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58711,58481,0,53294,TO_DATE('2009-12-02 17:46:59','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'EE02','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_DATE('2009-12-02 17:46:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58481 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58723,58482,0,53294,TO_DATE('2009-12-02 17:47:00','YYYY-MM-DD HH24:MI:SS'),100,'This is a manual process',1,'EE02','The Manual check box indicates if the process will done manually.','Y','Y','N','N','N','N','N','Manual',0,0,TO_DATE('2009-12-02 17:47:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58482 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58712,58483,0,53294,TO_DATE('2009-12-02 17:47:00','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'EE02','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Organization',0,0,TO_DATE('2009-12-02 17:47:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58483 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58719,58484,0,53294,TO_DATE('2009-12-02 17:47:01','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','N','N','N','N','N','Payroll Payment Selection ID',0,0,TO_DATE('2009-12-02 17:47:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58484 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58729,58485,0,53294,TO_DATE('2009-12-02 17:47:01','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'EE02','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','Processed',0,0,TO_DATE('2009-12-02 17:47:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58485 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58724,58486,0,53294,TO_DATE('2009-12-02 17:47:02','YYYY-MM-DD HH24:MI:SS'),100,'This is a Sales Transaction',1,'EE02','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','N','Sales Transaction',0,0,TO_DATE('2009-12-02 17:47:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58486 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58722,58487,0,53294,TO_DATE('2009-12-02 17:47:03','YYYY-MM-DD HH24:MI:SS'),100,'Calculated amount of discount',22,'EE02','The Discount Amount indicates the discount amount for a document or line.','Y','Y','N','N','N','N','Y','Discount Amount',0,0,TO_DATE('2009-12-02 17:47:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58487 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58721,58488,0,53294,TO_DATE('2009-12-02 17:47:03','YYYY-MM-DD HH24:MI:SS'),100,'Difference Amount',22,'EE02','Y','Y','N','N','N','N','N','Difference',0,0,TO_DATE('2009-12-02 17:47:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:04 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58488 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:05 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58710,58489,0,53294,TO_DATE('2009-12-02 17:47:04','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','N','N','N','N','N','Payroll Payment Selection Line ID',0,0,TO_DATE('2009-12-02 17:47:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:05 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58489 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58730,58490,0,53294,TO_DATE('2009-12-02 17:47:05','YYYY-MM-DD HH24:MI:SS'),100,70,'EE02','Y','Y','Y','N','N','N','N','Payroll Movement',10,0,TO_DATE('2009-12-02 17:47:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58490 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58725,58491,0,53294,TO_DATE('2009-12-02 17:47:06','YYYY-MM-DD HH24:MI:SS'),100,'Unique line for this document',22,'EE02','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','N','Line No',20,0,TO_DATE('2009-12-02 17:47:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:06 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58491 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58720,58492,0,53294,TO_DATE('2009-12-02 17:47:06','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'EE02','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',30,0,TO_DATE('2009-12-02 17:47:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58492 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58713,58493,0,53294,TO_DATE('2009-12-02 17:47:07','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE02','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',40,0,TO_DATE('2009-12-02 17:47:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:07 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58493 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58728,58494,0,53294,TO_DATE('2009-12-02 17:47:07','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice',1,'EE02','The Payment Rule indicates the method of invoice payment.','Y','Y','Y','N','N','N','N','Payment Rule',50,0,TO_DATE('2009-12-02 17:47:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58494 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58726,58495,0,53294,TO_DATE('2009-12-02 17:47:08','YYYY-MM-DD HH24:MI:SS'),100,'Open item amount',22,'EE02','Y','Y','Y','N','N','N','Y','Open Amount',60,0,TO_DATE('2009-12-02 17:47:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:08 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58495 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:09 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58727,58496,0,53294,TO_DATE('2009-12-02 17:47:08','YYYY-MM-DD HH24:MI:SS'),100,'Amount being paid',22,'EE02','Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.','Y','Y','Y','N','N','N','N','Payment amount',70,0,TO_DATE('2009-12-02 17:47:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:09 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58496 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:09 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58718,58497,0,53294,TO_DATE('2009-12-02 17:47:09','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','Y','N','N','N','Y','Payroll Pay Selection Check ID',80,0,TO_DATE('2009-12-02 17:47:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:10 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58497 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:10 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53250,'3',TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100,'Payroll Payment Selection Check','EE02','N','Y','N','Y','N','N','N','Payroll Pay Selection Check','L','HR_PaySelectionCheck',TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:10 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53250 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 2/12/2009 05:47:10 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53360,TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table HR_PaySelectionCheck',1,'Y','N','Y','Y','HR_PaySelectionCheck','N',1000000,TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:11 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58731,54091,0,13,53250,'HR_PaySelectionCheck_ID',TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Payroll Pay Selection Check ID',TO_DATE('2009-12-02 17:47:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:11 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58731 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:11 PM CST
-- Payroll Payment Selection
CREATE TABLE HR_PaySelectionCheck (HR_PaySelectionCheck_ID NUMBER(10) NOT NULL, CONSTRAINT HR_PaySelectionCheck_Key PRIMARY KEY (HR_PaySelectionCheck_ID))
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58732,102,0,19,53250,129,'AD_Client_ID',TO_DATE('2009-12-02 17:47:11','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE02',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','Y','Client',TO_DATE('2009-12-02 17:47:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58732 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58733,113,0,19,53250,104,'AD_Org_ID',TO_DATE('2009-12-02 17:47:12','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE02',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','Y','Organization',TO_DATE('2009-12-02 17:47:12','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58733 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:12 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58734,348,0,20,53250,'IsActive',TO_DATE('2009-12-02 17:47:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE02',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2009-12-02 17:47:12','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58734 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58735,245,0,16,53250,'Created',TO_DATE('2009-12-02 17:47:13','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE02',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created',TO_DATE('2009-12-02 17:47:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58735 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:13 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD Created DATE NOT NULL
;

-- 2/12/2009 05:47:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58736,607,0,16,53250,'Updated',TO_DATE('2009-12-02 17:47:13','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE02',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated',TO_DATE('2009-12-02 17:47:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58736 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:14 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD Updated DATE NOT NULL
;

-- 2/12/2009 05:47:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58737,246,0,19,110,53250,'CreatedBy',TO_DATE('2009-12-02 17:47:14','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE02',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Created By',TO_DATE('2009-12-02 17:47:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:14 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58737 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD CreatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58738,608,0,19,110,53250,'UpdatedBy',TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE02',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','Y','Updated By',TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58738 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD UpdatedBy NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_BP_BankAccount_ID', Description='Bank Account of the Business Partner', EntityType='D', Help='The Partner Bank Account identifies the bank account to be used for this Business Partner', IsActive='Y', Name='Partner Bank Account', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Partner Bank Account',Updated=TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=837
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=837
;

-- 2/12/2009 05:47:15 PM CST
-- Payroll Payment Selection
UPDATE AD_Val_Rule SET Code='C_BP_BankAccount.C_BPartner_ID=@C_BPartner_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_BP_BankAccount of BPartner', Type='S',Updated=TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=269
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58739,837,0,19,53250,269,'C_BP_BankAccount_ID',TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account of the Business Partner','EE02',10,'The Partner Bank Account identifies the bank account to be used for this Business Partner','Y','N','N','N','N','N','N','N','Y','N','Y','Partner Bank Account',TO_DATE('2009-12-02 17:47:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58739 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD C_BP_BankAccount_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_BPartner_ID', Description='Identifies a Business Partner', EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', Name='Business Partner ', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner ',Updated=TO_DATE('2009-12-02 17:47:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=187
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=187
;

-- 2/12/2009 05:47:16 PM CST
-- Payroll Payment Selection
UPDATE AD_Val_Rule SET Code='C_BPartner.IsActive=''Y'' AND C_BPartner.IsSummary=''N''', Description=NULL, EntityType='D', IsActive='Y', Name='C_BPartner (Trx)', Type='S',Updated=TO_DATE('2009-12-02 17:47:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=230
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58740,187,0,30,53250,230,'C_BPartner_ID',TO_DATE('2009-12-02 17:47:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE02',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','Y','N','N','Y','N','Y','Business Partner ',TO_DATE('2009-12-02 17:47:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58740 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD C_BPartner_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58741,54089,0,19,53250,'HR_PaySelection_ID',TO_DATE('2009-12-02 17:47:17','YYYY-MM-DD HH24:MI:SS'),100,'EE02',22,'Y','N','N','N','N','Y','Y','N','Y','N','N','Payroll Payment Selection ID',TO_DATE('2009-12-02 17:47:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58741 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:17 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD HR_PaySelection_ID NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_Payment_ID', Description='Payment identifier', EntityType='D', Help='The Payment is a unique identifier of this payment.', IsActive='Y', Name='Payment', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payment',Updated=TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1384
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1384
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='C_Payment', ValidationType='T',Updated=TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=343
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=343
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_Table SET AD_Table_ID = 335, AD_Display = 5401, AD_Key = 5043, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 343
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58742,1384,0,30,343,53250,'C_Payment_ID',TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),100,'Payment identifier','EE02',22,'The Payment is a unique identifier of this payment.','Y','N','N','N','N','N','N','N','Y','N','Y','Payment',TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58742 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:18 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD C_Payment_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:19 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58743,1395,0,12,53250,'DiscountAmt',TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),100,'Calculated amount of discount','EE02',22,'The Discount Amount indicates the discount amount for a document or line.','Y','N','N','N','N','Y','N','N','Y','N','Y','Discount Amount',TO_DATE('2009-12-02 17:47:18','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:19 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58743 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:19 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD DiscountAmt NUMBER NOT NULL
;

-- 2/12/2009 05:47:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsGeneratedDraft', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Generated Draft', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Generated Draft',Updated=TO_DATE('2009-12-02 17:47:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53334
;

-- 2/12/2009 05:47:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53334
;

-- 2/12/2009 05:47:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58744,53334,0,20,53250,'IsGeneratedDraft',TO_DATE('2009-12-02 17:47:19','YYYY-MM-DD HH24:MI:SS'),100,'N','EE02',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','Generated Draft',TO_DATE('2009-12-02 17:47:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:20 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58744 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:20 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD IsGeneratedDraft CHAR(1) DEFAULT 'N' CHECK (IsGeneratedDraft IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:47:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsPrinted', Description='Indicates if this document / line is printed', EntityType='D', Help='The Printed checkbox indicates if this document or line will included when printing.', IsActive='Y', Name='Printed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Printed',Updated=TO_DATE('2009-12-02 17:47:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=399
;

-- 2/12/2009 05:47:20 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=399
;

-- 2/12/2009 05:47:21 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58745,399,0,20,53250,'IsPrinted',TO_DATE('2009-12-02 17:47:20','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document / line is printed','EE02',1,'The Printed checkbox indicates if this document or line will included when printing.','Y','N','N','N','N','Y','N','N','Y','N','Y','Printed',TO_DATE('2009-12-02 17:47:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:21 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58745 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:21 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD IsPrinted CHAR(1) CHECK (IsPrinted IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:47:21 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='IsReceipt', Description='This is a sales transaction (receipt)', EntityType='D', Help=NULL, IsActive='Y', Name='Receipt', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Receipt',Updated=TO_DATE('2009-12-02 17:47:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1634
;

-- 2/12/2009 05:47:21 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1634
;

-- 2/12/2009 05:47:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58746,1634,0,20,53250,'IsReceipt',TO_DATE('2009-12-02 17:47:21','YYYY-MM-DD HH24:MI:SS'),100,'This is a sales transaction (receipt)','EE02',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','Receipt',TO_DATE('2009-12-02 17:47:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:22 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58746 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:22 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD IsReceipt CHAR(1) CHECK (IsReceipt IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:47:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58747,1477,0,12,53250,'PayAmt',TO_DATE('2009-12-02 17:47:22','YYYY-MM-DD HH24:MI:SS'),100,'Amount being paid','EE02',22,'Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.','Y','N','N','N','N','Y','N','N','Y','N','Y','Payment amount',TO_DATE('2009-12-02 17:47:22','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58747 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:23 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD PayAmt NUMBER NOT NULL
;

-- 2/12/2009 05:47:23 PM CST
-- Payroll Payment Selection
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''M''', Description=NULL, EntityType='D', IsActive='Y', Name='All_Payment Rule - No mixed', Type='S',Updated=TO_DATE('2009-12-02 17:47:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52033
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58748,1143,0,17,195,53250,52033,'PaymentRule',TO_DATE('2009-12-02 17:47:23','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice','EE02',1,'The Payment Rule indicates the method of invoice payment.','Y','N','N','N','N','Y','N','N','Y','N','Y','Payment Rule',TO_DATE('2009-12-02 17:47:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58748 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD PaymentRule CHAR(1) NOT NULL
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58749,1047,0,20,53250,'Processed',TO_DATE('2009-12-02 17:47:24','YYYY-MM-DD HH24:MI:SS'),100,'N','The document has been processed','EE02',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Processed',TO_DATE('2009-12-02 17:47:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58749 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_DATE('2009-12-02 17:47:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=526
;

-- 2/12/2009 05:47:24 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- 2/12/2009 05:47:25 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58750,526,0,11,53250,'Qty',TO_DATE('2009-12-02 17:47:24','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE02',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','Y','N','N','Y','N','Y','Quantity',TO_DATE('2009-12-02 17:47:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/12/2009 05:47:25 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58750 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:25 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD Qty NUMBER(10) NOT NULL
;

-- 2/12/2009 05:47:25 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_DATE('2009-12-02 17:47:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- 2/12/2009 05:47:25 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- 2/12/2009 05:47:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58751,290,0,10,53250,'DocumentNo',TO_DATE('2009-12-02 17:47:25','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE02',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','N','N','N','Y','N','Y','Document No',1,TO_DATE('2009-12-02 17:47:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58751 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:26 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_PaySelectionCheck ADD DocumentNo NVARCHAR2(30) DEFAULT NULL 
;

-- 2/12/2009 05:47:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53295,53250,53103,NULL,TO_DATE('2009-12-02 17:47:26','YYYY-MM-DD HH24:MI:SS'),100,'EE02','N','Y','N','N','N','Y','N','N','N','Payroll Payment Selection Check','N',30,0,TO_DATE('2009-12-02 17:47:26','YYYY-MM-DD HH24:MI:SS'),100,'HR_PaySelection_ID=@HR_PaySelection_ID@')
;

-- 2/12/2009 05:47:26 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53295 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- 2/12/2009 05:47:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58734,58498,0,53295,TO_DATE('2009-12-02 17:47:26','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE02','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Active',0,0,TO_DATE('2009-12-02 17:47:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58498 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58744,58499,0,53295,TO_DATE('2009-12-02 17:47:27','YYYY-MM-DD HH24:MI:SS'),100,1,'EE02','Y','Y','N','N','N','N','N','Generated Draft',0,0,TO_DATE('2009-12-02 17:47:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:28 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58499 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58739,58500,0,53295,TO_DATE('2009-12-02 17:47:28','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account of the Business Partner',10,'EE02','The Partner Bank Account identifies the bank account to be used for this Business Partner','Y','Y','N','N','N','N','N','Partner Bank Account',0,0,TO_DATE('2009-12-02 17:47:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:31 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58500 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58731,58501,0,53295,TO_DATE('2009-12-02 17:47:31','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','N','N','N','N','N','Payroll Pay Selection Check ID',0,0,TO_DATE('2009-12-02 17:47:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58501 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58732,58502,0,53295,TO_DATE('2009-12-02 17:47:32','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'EE02','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2009-12-02 17:47:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58502 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58733,58503,0,53295,TO_DATE('2009-12-02 17:47:32','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'EE02','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2009-12-02 17:47:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58503 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58741,58504,0,53295,TO_DATE('2009-12-02 17:47:33','YYYY-MM-DD HH24:MI:SS'),100,22,'EE02','Y','Y','Y','N','N','N','N','Payroll Payment Selection ID',30,0,TO_DATE('2009-12-02 17:47:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58504 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58740,58505,0,53295,TO_DATE('2009-12-02 17:47:34','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',22,'EE02','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','Business Partner ',40,0,TO_DATE('2009-12-02 17:47:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58505 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58751,58506,0,53295,TO_DATE('2009-12-02 17:47:34','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document',30,'EE02','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Document No',50,0,TO_DATE('2009-12-02 17:47:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58506 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58748,58507,0,53295,TO_DATE('2009-12-02 17:47:35','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice',1,'EE02','The Payment Rule indicates the method of invoice payment.','Y','Y','Y','N','N','N','N','Payment Rule',60,0,TO_DATE('2009-12-02 17:47:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58507 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:36 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58750,58508,0,53295,TO_DATE('2009-12-02 17:47:35','YYYY-MM-DD HH24:MI:SS'),100,'Quantity',22,'EE02','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','Y','Quantity',70,0,TO_DATE('2009-12-02 17:47:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:36 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58508 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:36 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58747,58509,0,53295,TO_DATE('2009-12-02 17:47:36','YYYY-MM-DD HH24:MI:SS'),100,'Amount being paid',22,'EE02','Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.','Y','Y','Y','N','N','N','N','Payment amount',80,0,TO_DATE('2009-12-02 17:47:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:36 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58509 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:37 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58743,58510,0,53295,TO_DATE('2009-12-02 17:47:36','YYYY-MM-DD HH24:MI:SS'),100,'Calculated amount of discount',22,'EE02','The Discount Amount indicates the discount amount for a document or line.','Y','Y','Y','N','N','N','Y','Discount Amount',90,0,TO_DATE('2009-12-02 17:47:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:37 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58510 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58742,58511,0,53295,TO_DATE('2009-12-02 17:47:37','YYYY-MM-DD HH24:MI:SS'),100,'Payment identifier',22,'EE02','The Payment is a unique identifier of this payment.','Y','Y','Y','N','N','N','N','Payment',100,0,TO_DATE('2009-12-02 17:47:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58511 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58746,58512,0,53295,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100,'This is a sales transaction (receipt)',1,'EE02','Y','Y','Y','N','N','N','Y','Receipt',110,0,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58512 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58745,58513,0,53295,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document / line is printed',1,'EE02','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','Y','N','N','N','N','Printed',120,0,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:38 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58513 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:39 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58749,58514,0,53295,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'EE02','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','Y','Processed',130,0,TO_DATE('2009-12-02 17:47:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:39 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58514 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 05:47:40 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Form (AD_Client_ID,AD_Form_ID,AD_Org_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES (0,53014,0,'1','org.eevolution.form.VHRPayPrint',TO_DATE('2009-12-02 17:47:39','YYYY-MM-DD HH24:MI:SS'),100,'Payroll Print or export your payments','EE02','Y','N','Payroll Payment Print/Export',TO_DATE('2009-12-02 17:47:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:40 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53014 AND NOT EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Form_ID=t.AD_Form_ID)
;

-- 2/12/2009 05:47:40 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53251,'3',TO_DATE('2009-12-02 17:47:40','YYYY-MM-DD HH24:MI:SS'),100,'EE02','N','Y','N','Y','N','N','N','C_HR_PaySelection_Check_v','L','C_HR_PaySelection_Check_v',TO_DATE('2009-12-02 17:47:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:40 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53251 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 2/12/2009 05:47:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53361,TO_DATE('2009-12-02 17:47:40','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table C_HR_PaySelection_Check_v',1,'Y','N','Y','Y','C_HR_PaySelection_Check_v','N',1000000,TO_DATE('2009-12-02 17:47:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:47:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58752,102,0,19,53251,'AD_Client_ID',TO_DATE('2009-12-02 17:47:41','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE02',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','Y','N','N','Client',TO_DATE('2009-12-02 17:47:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58752 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:41 PM CST
-- Payroll Payment Selection
CREATE TABLE C_HR_PaySelection_Check_v (AD_Client_ID NUMBER(10) DEFAULT NULL )
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58753,113,0,19,53251,'AD_Org_ID',TO_DATE('2009-12-02 17:47:41','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE02',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','Y','N','N','Organization',TO_DATE('2009-12-02 17:47:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58753 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD AD_Org_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='AD_Language', Description='Language for this entity', EntityType='D', Help='The Language identifies the language to use for display and formatting', IsActive='Y', Name='Language', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Language',Updated=TO_DATE('2009-12-02 17:47:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=109
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=109
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58754,109,0,19,53251,'AD_Language',TO_DATE('2009-12-02 17:47:42','YYYY-MM-DD HH24:MI:SS'),100,'Language for this entity','EE02',10,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','N','N','N','Y','N','Y','Language',TO_DATE('2009-12-02 17:47:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58754 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:42 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD AD_Language VARCHAR2(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_PaySelection_ID', Description='Payment Selection', EntityType='D', Help='The Payment Selection identifies a unique Payment', IsActive='Y', Name='Payment Selection', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Payment Selection',Updated=TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1532
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1532
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58755,1532,0,19,53251,'C_PaySelection_ID',TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),100,'Payment Selection','EE02',131089,'The Payment Selection identifies a unique Payment','Y','N','N','N','N','N','N','N','Y','N','Y','Payment Selection',TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58755 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD C_PaySelection_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_PaySelectionCheck_ID', Description='Payment Selection Check', EntityType='D', Help=NULL, IsActive='Y', Name='Pay Selection Check', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Pay Selection Check',Updated=TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1882
;

-- 2/12/2009 05:47:43 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1882
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58756,1882,0,19,53251,'C_PaySelectionCheck_ID',TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),100,'Payment Selection Check','EE02',131089,'Y','N','N','N','N','N','N','N','Y','N','Y','Pay Selection Check',TO_DATE('2009-12-02 17:47:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58756 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD C_PaySelectionCheck_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Org_Location_ID', Description='Organization Location/Address', EntityType='D', Help=NULL, IsActive='Y', Name='Org Address', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Address',Updated=TO_DATE('2009-12-02 17:47:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1874
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1874
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2009-12-02 17:47:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Organization selection, no summary, no 0', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Org (Trx)', ValidationType='T',Updated=TO_DATE('2009-12-02 17:47:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=130
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=130
;

-- 2/12/2009 05:47:44 PM CST
-- Payroll Payment Selection
UPDATE AD_Ref_Table SET AD_Table_ID = 155, AD_Display = 522, AD_Key = 528, isValueDisplayed = 'Y', OrderByClause = 'AD_Org.Name', EntityType ='D', WhereClause = 'AD_Org.IsSummary=''N'' AND AD_Org_ID <> 0' WHERE AD_Reference_ID = 130
;

-- 2/12/2009 05:47:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58757,1874,0,18,130,53251,'Org_Location_ID',TO_DATE('2009-12-02 17:47:44','YYYY-MM-DD HH24:MI:SS'),100,'Organization Location/Address','EE02',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Address',TO_DATE('2009-12-02 17:47:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:45 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58757 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:45 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD Org_Location_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:45 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='TaxID', Description='Tax Identification', EntityType='D', Help='The Tax ID field identifies the legal Identification number of this Entity.', IsActive='Y', Name='Tax ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Tax ID',Updated=TO_DATE('2009-12-02 17:47:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=590
;

-- 2/12/2009 05:47:45 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=590
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58758,590,0,10,53251,'TaxID',TO_DATE('2009-12-02 17:47:45','YYYY-MM-DD HH24:MI:SS'),100,'Tax Identification','EE02',20,'The Tax ID field identifies the legal Identification number of this Entity.','Y','N','N','N','N','N','N','N','Y','N','Y','Tax ID',TO_DATE('2009-12-02 17:47:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58758 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD TaxID NVARCHAR2(20) DEFAULT NULL 
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_DocType_ID', Description='Document type or rules', EntityType='D', Help='The Document Type determines document sequence and processing rules', IsActive='Y', Name='Document Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Type',Updated=TO_DATE('2009-12-02 17:47:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=196
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=196
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58759,196,0,19,53251,'C_DocType_ID',TO_DATE('2009-12-02 17:47:46','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules','EE02',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','Y','N','Y','Document Type',TO_DATE('2009-12-02 17:47:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58759 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:46 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD C_DocType_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58760,187,0,19,53251,'C_BPartner_ID',TO_DATE('2009-12-02 17:47:46','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE02',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner ',TO_DATE('2009-12-02 17:47:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58760 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD C_BPartner_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='BPValue', Description='Business Partner Key Value', EntityType='D', Help='Search Key of Business Partner', IsActive='Y', Name='BP Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Customer No',Updated=TO_DATE('2009-12-02 17:47:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1876
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1876
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58761,1876,0,10,53251,'BPValue',TO_DATE('2009-12-02 17:47:47','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Key Value','EE02',40,'Search Key of Business Partner','Y','N','N','N','N','N','N','N','Y','N','Y','BP Search Key',TO_DATE('2009-12-02 17:47:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58761 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:47 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD BPValue NVARCHAR2(40) DEFAULT NULL 
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='BPTaxID', Description='Tax ID of the Business Partner', EntityType='D', Help=NULL, IsActive='Y', Name='Partner Tax ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BP Tax TD',Updated=TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2266
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2266
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58762,2266,0,10,53251,'BPTaxID',TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),100,'Tax ID of the Business Partner','EE02',20,'Y','N','N','N','N','N','N','N','Y','N','Y','Partner Tax ID',TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58762 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD BPTaxID NVARCHAR2(20) DEFAULT NULL 
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='NAICS', Description='Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html', EntityType='D', Help='The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.', IsActive='Y', Name='NAICS/SIC', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='NAICS/SIC',Updated=TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=468
;

-- 2/12/2009 05:47:48 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=468
;

-- 2/12/2009 05:47:49 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58763,468,0,10,53251,'NAICS',TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),100,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','EE02',6,'The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','N','N','N','N','N','N','N','Y','N','Y','NAICS/SIC',TO_DATE('2009-12-02 17:47:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:49 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58763 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:49 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD NAICS NVARCHAR2(6) DEFAULT NULL 
;

-- 2/12/2009 05:47:49 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='DUNS', Description='Dun & Bradstreet Number', EntityType='D', Help='Used for EDI - For details see   www.dnb.com/dunsno/list.htm', IsActive='Y', Name='D-U-N-S', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='D-U-N-S',Updated=TO_DATE('2009-12-02 17:47:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=260
;

-- 2/12/2009 05:47:49 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=260
;

-- 2/12/2009 05:47:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58764,260,0,10,53251,'DUNS',TO_DATE('2009-12-02 17:47:49','YYYY-MM-DD HH24:MI:SS'),100,'Dun & Bradstreet Number','EE02',11,'Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','N','N','N','N','N','N','N','Y','N','Y','D-U-N-S',TO_DATE('2009-12-02 17:47:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:50 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58764 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:50 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD DUNS NVARCHAR2(11) DEFAULT NULL 
;

-- 2/12/2009 05:47:50 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='BPGreeting', Description='Greeting for Business Partner', EntityType='D', Help=NULL, IsActive='Y', Name='BP Greeting', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BP Greeting',Updated=TO_DATE('2009-12-02 17:47:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1838
;

-- 2/12/2009 05:47:50 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1838
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58765,1838,0,10,53251,'BPGreeting',TO_DATE('2009-12-02 17:47:50','YYYY-MM-DD HH24:MI:SS'),100,'Greeting for Business Partner','EE02',60,'Y','N','N','N','N','N','N','N','Y','N','Y','BP Greeting',TO_DATE('2009-12-02 17:47:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58765 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD BPGreeting NVARCHAR2(60) DEFAULT NULL 
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58766,469,0,10,53251,'Name',TO_DATE('2009-12-02 17:47:51','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','EE02',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','Y','N','Y','Name',1,TO_DATE('2009-12-02 17:47:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58766 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD Name NVARCHAR2(60) DEFAULT NULL 
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='Name2', Description='Additional Name', EntityType='D', Help=NULL, IsActive='Y', Name='Name 2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name 2',Updated=TO_DATE('2009-12-02 17:47:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1111
;

-- 2/12/2009 05:47:51 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1111
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58767,1111,0,10,53251,'Name2',TO_DATE('2009-12-02 17:47:51','YYYY-MM-DD HH24:MI:SS'),100,'Additional Name','EE02',60,'Y','N','N','N','N','N','N','N','Y','N','Y','Name 2',TO_DATE('2009-12-02 17:47:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58767 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD Name2 NVARCHAR2(60) DEFAULT NULL 
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='C_Location_ID', Description='Location or Address', EntityType='D', Help='The Location / Address field defines the location of an entity.', IsActive='Y', Name='Address', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Address',Updated=TO_DATE('2009-12-02 17:47:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=202
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=202
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Location/Address', EntityType='D', Help=NULL, IsActive='Y', Name='Location (Address)', ValidationType='D',Updated=TO_DATE('2009-12-02 17:47:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=21
;

-- 2/12/2009 05:47:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=21
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58768,202,0,21,53251,'C_Location_ID',TO_DATE('2009-12-02 17:47:52','YYYY-MM-DD HH24:MI:SS'),100,'Location or Address','EE02',131089,'The Location / Address field defines the location of an entity.','Y','N','N','N','N','N','N','N','Y','N','Y','Address',TO_DATE('2009-12-02 17:47:52','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58768 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD C_Location_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='ReferenceNo', Description='Your customer or vendor number at the Business Partner''s site', EntityType='D', Help='The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.', IsActive='Y', Name='Reference No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Reference No',Updated=TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=540
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=540
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58769,540,0,10,53251,'ReferenceNo',TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),100,'Your customer or vendor number at the Business Partner''s site','EE02',40,'The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','N','N','N','N','N','N','N','Y','N','Y','Reference No',TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58769 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD ReferenceNo NVARCHAR2(40) DEFAULT NULL 
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='POReference', Description='Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner', EntityType='D', Help='The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.', IsActive='Y', Name='Order Reference', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Reference',Updated=TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=952
;

-- 2/12/2009 05:47:53 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=952
;

-- 2/12/2009 05:47:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58770,952,0,10,53251,'POReference',TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE02',20,'The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','N','N','N','N','N','N','N','Y','N','Y','Order Reference',TO_DATE('2009-12-02 17:47:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:54 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58770 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:54 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD POReference NVARCHAR2(20) DEFAULT NULL 
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58771,1538,0,16,53251,'PayDate',TO_DATE('2009-12-02 17:47:54','YYYY-MM-DD HH24:MI:SS'),100,'Date Payment made','EE02',29,'The Payment Date indicates the date the payment was made.','Y','N','N','N','N','N','N','N','Y','N','Y','Payment date',TO_DATE('2009-12-02 17:47:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58771 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD PayDate DATE DEFAULT NULL 
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58772,1477,0,12,53251,'PayAmt',TO_DATE('2009-12-02 17:47:55','YYYY-MM-DD HH24:MI:SS'),100,'Amount being paid','EE02',131089,'Indicates the amount this payment is for.  The payment amount can be for single or multiple invoices or a partial payment for an invoice.','Y','N','N','N','N','N','N','N','Y','N','Y','Payment amount',TO_DATE('2009-12-02 17:47:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58772 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD PayAmt NUMBER DEFAULT NULL 
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element SET ColumnName='AmtInWords', Description='Amount in words', EntityType='D', Help='Amount in words will be printed.', IsActive='Y', Name='Amt in Words', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Amt in Words',Updated=TO_DATE('2009-12-02 17:47:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1836
;

-- 2/12/2009 05:47:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1836
;

-- 2/12/2009 05:47:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58773,1836,0,12,53251,'AmtInWords',TO_DATE('2009-12-02 17:47:55','YYYY-MM-DD HH24:MI:SS'),100,'Amount in words','EE02',131089,'Amount in words will be printed.','Y','N','N','N','N','N','N','N','Y','N','Y','Amt in Words',TO_DATE('2009-12-02 17:47:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:56 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58773 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:56 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD AmtInWords NUMBER DEFAULT NULL 
;

-- 2/12/2009 05:47:56 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_DATE('2009-12-02 17:47:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- 2/12/2009 05:47:56 PM CST
-- Payroll Payment Selection
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- 2/12/2009 05:47:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58774,526,0,29,53251,'Qty',TO_DATE('2009-12-02 17:47:56','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE02',131089,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity',TO_DATE('2009-12-02 17:47:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:57 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58774 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:57 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD Qty NUMBER DEFAULT NULL 
;

-- 2/12/2009 05:47:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58775,1143,0,20,53251,'PaymentRule',TO_DATE('2009-12-02 17:47:57','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice','EE02',1,'The Payment Rule indicates the method of invoice payment.','Y','N','N','N','N','N','N','N','Y','N','Y','Payment Rule',TO_DATE('2009-12-02 17:47:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:58 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58775 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:58 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD PaymentRule CHAR(1) DEFAULT NULL  CHECK (PaymentRule IN ('Y','N'))
;

-- 2/12/2009 05:47:59 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58776,290,0,10,53251,'DocumentNo',TO_DATE('2009-12-02 17:47:58','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE02',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Document No',TO_DATE('2009-12-02 17:47:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:47:59 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58776 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:47:59 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD DocumentNo NVARCHAR2(30) DEFAULT NULL 
;

-- 2/12/2009 05:48:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58777,54089,0,19,53251,'HR_PaySelection_ID',TO_DATE('2009-12-02 17:47:59','YYYY-MM-DD HH24:MI:SS'),100,'EE02',131089,'Y','N','N','N','N','N','N','N','Y','N','Y','Payroll Payment Selection ID',TO_DATE('2009-12-02 17:47:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:48:00 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58777 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:48:00 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD HR_PaySelection_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:48:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58778,54091,0,19,53251,'HR_PaySelectionCheck_ID',TO_DATE('2009-12-02 17:48:00','YYYY-MM-DD HH24:MI:SS'),100,'EE02',131089,'Y','N','N','N','N','N','N','N','Y','N','Y','Payroll Pay Selection Check ID',TO_DATE('2009-12-02 17:48:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 05:48:01 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58778 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 05:48:01 PM CST
-- Payroll Payment Selection
ALTER TABLE C_HR_PaySelection_Check_v ADD HR_PaySelectionCheck_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 05:48:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET AD_Column_ID=58693, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53293, AD_Val_Rule_ID=NULL, Description=NULL, DisplayLength=22, DisplayLogic=NULL, EntityType='EE02', Help=NULL, Included_Tab_ID=53294, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Payroll Payment Selection ID', SeqNo=90, SortNo=0,Updated=TO_DATE('2009-12-02 17:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58477
;

-- 2/12/2009 05:48:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58477
;

-- 2/12/2009 05:48:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53254,0,53103,'W',TO_DATE('2009-12-02 17:48:01','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','N','N','N','Payroll Payment Selection',TO_DATE('2009-12-02 17:48:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:48:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53254 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 2/12/2009 05:48:02 PM CST
-- Payroll Payment Selection
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53114,10, 10, 53254)
;

-- 2/12/2009 05:48:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Menu (AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53014,53255,0,'X',TO_DATE('2009-12-02 17:48:02','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','Payroll Payment Print/Export',TO_DATE('2009-12-02 17:48:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 05:48:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53255 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 2/12/2009 05:48:03 PM CST
-- Payroll Payment Selection
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53114,11, 10, 53255)
;

-- 2/12/2009 06:00:32 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52076,'C_Invoice.C_BPartner_ID=@C_BPartner_ID@',TO_DATE('2009-12-02 18:00:31','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','HR_Attribute Invoice by C_BPartner','S',TO_DATE('2009-12-02 18:00:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:01:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58779,1008,0,30,53087,52076,'C_Invoice_ID',TO_DATE('2009-12-02 18:01:36','YYYY-MM-DD HH24:MI:SS'),100,'Invoice Identifier','EE02',10,'The Invoice Document.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@IsInvoiced@=''Y'' & @IsPaid@=''N''','Invoice',0,TO_DATE('2009-12-02 18:01:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 06:01:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58779 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 06:01:47 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_Attribute ADD C_Invoice_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 06:03:47 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52077,'CASE WHEN ''@IsPaid@''= ''Y'' THEN C_DocType.DocBaseType IN(''API'') ELSE C_DocType.DocBaseType IN (''ARC'') END',TO_DATE('2009-12-02 18:03:44','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','HR_Attribute C_DocType','S',TO_DATE('2009-12-02 18:03:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:04:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58780,196,0,19,53087,52077,'C_DocType_ID',TO_DATE('2009-12-02 18:04:27','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules','EE02',10,'The Document Type determines document sequence and processing rules','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@IsInvoiced@=''Y'' ','Document Type',0,TO_DATE('2009-12-02 18:04:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 06:04:27 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58780 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 06:04:32 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_Attribute ADD C_DocType_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 06:05:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58781,968,0,19,53087,'C_Charge_ID',TO_DATE('2009-12-02 18:05:32','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges','EE02',10,'The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@IsInvoiced@=''Y''','Charge',0,TO_DATE('2009-12-02 18:05:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 06:05:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58781 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 06:05:36 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_Attribute ADD C_Charge_ID NUMBER(10) DEFAULT NULL 
;

-- 2/12/2009 06:07:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58782,387,0,20,53090,'IsInvoiced',TO_DATE('2009-12-02 18:07:40','YYYY-MM-DD HH24:MI:SS'),100,'Is this invoiced?','EE02',1,'If selected, invoices are created','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Invoiced',0,TO_DATE('2009-12-02 18:07:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/12/2009 06:07:41 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58782 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2/12/2009 06:07:50 PM CST
-- Payroll Payment Selection
ALTER TABLE HR_Concept ADD IsInvoiced CHAR(1) DEFAULT NULL  CHECK (IsInvoiced IN ('Y','N'))
;

-- 2/12/2009 06:12:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58782,58515,0,53111,TO_DATE('2009-12-02 18:12:22','YYYY-MM-DD HH24:MI:SS'),100,'Is this invoiced?',1,'EE02','If selected, invoices are created','Y','Y','Y','N','N','N','N','N','Invoiced',TO_DATE('2009-12-02 18:12:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:12:23 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58515 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 06:14:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58781,58516,0,53113,TO_DATE('2009-12-02 18:14:33','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges',10,'EE02','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','Y','N','N','N','N','N','Charge',TO_DATE('2009-12-02 18:14:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:14:33 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58516 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 06:14:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58780,58517,0,53113,TO_DATE('2009-12-02 18:14:33','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules',10,'EE02','The Document Type determines document sequence and processing rules','Y','Y','Y','N','N','N','N','N','Document Type',TO_DATE('2009-12-02 18:14:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:14:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58517 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 06:14:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58779,58518,0,53113,TO_DATE('2009-12-02 18:14:34','YYYY-MM-DD HH24:MI:SS'),100,'Invoice Identifier',10,'EE02','The Invoice Document.','Y','Y','Y','N','N','N','N','N','Invoice',TO_DATE('2009-12-02 18:14:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:14:34 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58518 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 06:14:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54773,58519,0,53113,TO_DATE('2009-12-02 18:14:34','YYYY-MM-DD HH24:MI:SS'),100,10,'EE02','Y','Y','Y','N','N','N','N','N','Payroll Employee',TO_DATE('2009-12-02 18:14:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/12/2009 06:14:35 PM CST
-- Payroll Payment Selection
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58519 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2/12/2009 06:15:34 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58519
;

-- 2/12/2009 06:15:34 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=58517
;

-- 2/12/2009 06:15:34 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=58518
;

-- 2/12/2009 06:15:34 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=58516
;

-- 2/12/2009 06:16:21 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsInvoiced@=''Y''',Updated=TO_DATE('2009-12-02 18:16:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58517
;

-- 2/12/2009 06:16:27 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsInvoiced@=''Y'' & @IsPaid@ = ''N''',Updated=TO_DATE('2009-12-02 18:16:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58518
;

-- 2/12/2009 06:16:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsInvoiced@ = ''Y''',Updated=TO_DATE('2009-12-02 18:16:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58516
;

-- 2/12/2009 06:27:36 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsInvoiced@ = ''Y''  | @IsPayment@= ''Y''',Updated=TO_DATE('2009-12-02 18:27:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58516
;

-- 2/12/2009 06:27:52 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-12-02 18:27:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58518
;

-- 2/12/2009 06:28:11 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsEmployee@= ''Y'' | @IsInvoiced@= ''Y''',Updated=TO_DATE('2009-12-02 18:28:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55034
;

-- 2/12/2009 06:29:18 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET MandatoryLogic='@IsEmployee@=''Y'' | @IsInvoiced@=''Y''',Updated=TO_DATE('2009-12-02 18:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54764
;

-- 2/12/2009 06:33:37 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET DisplayLogic='@IsInvoiced@ = ''Y''  | @IsPaid@= ''Y''',Updated=TO_DATE('2009-12-02 18:33:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58516
;

-- 2/12/2009 06:34:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET MandatoryLogic='@IsInvoiced@=''Y'' | @IsPaid@ = ''Y''',Updated=TO_DATE('2009-12-02 18:34:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58781
;

-- 2/12/2009 06:38:25 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=58518
;

-- 2/12/2009 06:38:25 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=58517
;

-- 2/12/2009 06:38:30 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-12-02 18:38:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58516
;

-- 2/12/2009 06:43:09 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment', IsUpdateable='N',Updated=TO_DATE('2009-12-02 18:43:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58719
;

-- 2/12/2009 06:43:09 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Payment Selection ID', Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment' WHERE AD_Column_ID=58719 AND IsCentrallyMaintained='Y'
;

-- 2/12/2009 06:44:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection Check',Updated=TO_DATE('2009-12-02 18:44:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58718
;

-- 2/12/2009 06:44:01 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Pay Selection Check ID', Description='Payroll Payment Selection Check', Help=NULL WHERE AD_Column_ID=58718 AND IsCentrallyMaintained='Y'
;

-- 2/12/2009 06:44:27 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection Line', Help='The Payroll Payment Selection Line identifies a unique line in a payment', IsUpdateable='N',Updated=TO_DATE('2009-12-02 18:44:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58710
;

-- 2/12/2009 06:44:27 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Payment Selection Line ID', Description='Payroll Payment Selection Line', Help='The Payroll Payment Selection Line identifies a unique line in a payment' WHERE AD_Column_ID=58710 AND IsCentrallyMaintained='Y'
;

-- 2/12/2009 06:44:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection Check', IsUpdateable='N',Updated=TO_DATE('2009-12-02 18:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58731
;

-- 2/12/2009 06:44:55 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Pay Selection Check ID', Description='Payroll Payment Selection Check', Help=NULL WHERE AD_Column_ID=58731 AND IsCentrallyMaintained='Y'
;

-- 2/12/2009 06:46:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment', IsUpdateable='N',Updated=TO_DATE('2009-12-02 18:46:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58741
;

-- 2/12/2009 06:46:19 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Payment Selection ID', Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment' WHERE AD_Column_ID=58741 AND IsCentrallyMaintained='Y'
;

-- 2/12/2009 06:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Column SET Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment', IsUpdateable='N',Updated=TO_DATE('2009-12-02 18:46:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58693
;

-- 2/12/2009 06:46:54 PM CST
-- Payroll Payment Selection
UPDATE AD_Field SET Name='Payroll Payment Selection ID', Description='Payroll Payment Selection', Help='The Payroll Payment Selection identifies a unique Payment' WHERE AD_Column_ID=58693 AND IsCentrallyMaintained='Y'
;


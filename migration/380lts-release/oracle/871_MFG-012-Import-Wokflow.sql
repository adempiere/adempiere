SET SQLBLANKLINES ON
SET DEFINE OFF
-- May 12, 2012 12:39:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53175,TO_DATE('2012-05-12 12:39:40','YYYY-MM-DD HH24:MI:SS'),0,'Import Workflow','EE01','The Import Workflow Window is an interim table which is used when importing external data into the system.  Selecting the ''Process'' button will either add or modify the appropriate records.','Y','N','N','Y','Import Workflow','N',TO_DATE('2012-05-12 12:39:40','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- May 12, 2012 12:39:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53175 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- May 12, 2012 12:39:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53380,53175,'3',TO_DATE('2012-05-12 12:39:42','YYYY-MM-DD HH24:MI:SS'),0,'Import Workflow Standatd & Manufacturing','EE01','N','Y','N','Y','N','N','N','Import Workflow','L','I_Workflow',TO_DATE('2012-05-12 12:39:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:39:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53380 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- May 12, 2012 12:39:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53529,TO_DATE('2012-05-12 12:39:42','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table I_Workflow',1,'Y','N','Y','Y','I_Workflow','N',1000000,TO_DATE('2012-05-12 12:39:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:39:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55484,0,'I_Workflow_ID',TO_DATE('2012-05-12 12:39:43','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','I_Workflow_ID','Import Workflow',TO_DATE('2012-05-12 12:39:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:39:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55484 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:39:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63045,55484,0,13,53380,'I_Workflow_ID',TO_DATE('2012-05-12 12:39:43','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','I_Workflow_ID',TO_DATE('2012-05-12 12:39:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63045 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
CREATE TABLE I_Workflow (I_Workflow_ID NUMBER(10) NOT NULL, CONSTRAINT I_Workflow_Key PRIMARY KEY (I_Workflow_ID))
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63046,102,0,19,53380,'AD_Client_ID',TO_DATE('2012-05-12 12:39:44','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_DATE('2012-05-12 12:39:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63046 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63047,113,0,19,53380,'AD_Org_ID',TO_DATE('2012-05-12 12:39:45','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_DATE('2012-05-12 12:39:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63047 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 12:39:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63048,245,0,16,53380,'Created',TO_DATE('2012-05-12 12:39:45','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_DATE('2012-05-12 12:39:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63048 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Created DATE NOT NULL
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63049,246,0,18,110,53380,'CreatedBy',TO_DATE('2012-05-12 12:39:46','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','Y','N','Y','Created By',TO_DATE('2012-05-12 12:39:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63049 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD CreatedBy NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63050,348,0,20,53380,'IsActive',TO_DATE('2012-05-12 12:39:47','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2012-05-12 12:39:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63050 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- May 12, 2012 12:39:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63051,607,0,16,53380,'Updated',TO_DATE('2012-05-12 12:39:48','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_DATE('2012-05-12 12:39:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63051 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Updated DATE NOT NULL
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63052,608,0,18,110,53380,'UpdatedBy',TO_DATE('2012-05-12 12:39:48','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_DATE('2012-05-12 12:39:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63052 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD UpdatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63053,912,0,10,53380,'I_ErrorMsg',TO_DATE('2012-05-12 12:39:49','YYYY-MM-DD HH24:MI:SS'),0,'Messages generated from import process','EE01',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_DATE('2012-05-12 12:39:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63053 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD I_ErrorMsg NVARCHAR2(2000) DEFAULT NULL 
;

-- May 12, 2012 12:39:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63054,913,0,20,53380,'I_IsImported',TO_DATE('2012-05-12 12:39:49','YYYY-MM-DD HH24:MI:SS'),0,'N','Has this import been processed','EE01',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Imported',TO_DATE('2012-05-12 12:39:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63054 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD I_IsImported CHAR(1) DEFAULT 'N' CHECK (I_IsImported IN ('Y','N')) NOT NULL
;

-- May 12, 2012 12:39:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63055,1047,0,20,53380,'Processed',TO_DATE('2012-05-12 12:39:50','YYYY-MM-DD HH24:MI:SS'),0,'N','The document has been processed','EE01',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','Y','N','Y','Processed',TO_DATE('2012-05-12 12:39:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63055 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N'))
;

-- May 12, 2012 12:39:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53296,'3','org.eevolution.process.ImportWorkflow',TO_DATE('2012-05-12 12:39:52','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','Import Workflow','Y',0,0,TO_DATE('2012-05-12 12:39:52','YYYY-MM-DD HH24:MI:SS'),0,'Import_Workflow',NULL)
;

-- May 12, 2012 12:39:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53296 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- May 12, 2012 12:39:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2169,0,53296,53649,20,'IsImportOnlyNoErrors',TO_DATE('2012-05-12 12:39:52','YYYY-MM-DD HH24:MI:SS'),0,'Y','EE01',0,'Y','Y','N','N','Import Only No Errors',10,TO_DATE('2012-05-12 12:39:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:39:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53649 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- May 12, 2012 12:39:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53296,53650,20,'DeleteOldImported',TO_DATE('2012-05-12 12:39:53','YYYY-MM-DD HH24:MI:SS'),0,'EE01',0,'Y','Y','N','N','Delete Old Imported',20,TO_DATE('2012-05-12 12:39:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:39:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53650 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63056,524,0,53296,28,53380,'Processing',TO_DATE('2012-05-12 12:39:53','YYYY-MM-DD HH24:MI:SS'),0,'N','EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_DATE('2012-05-12 12:39:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63056 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Processing CHAR(1) DEFAULT 'N'
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63057,2115,0,10,53380,'OrgValue',TO_DATE('2012-05-12 12:39:54','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Key',TO_DATE('2012-05-12 12:39:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63057 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD OrgValue NVARCHAR2(40) DEFAULT NULL 
;

-- May 12, 2012 12:39:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63058,620,0,10,53380,'Value',TO_DATE('2012-05-12 12:39:55','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE01',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','N','N','N','Y','N','Y','Search Key',1,TO_DATE('2012-05-12 12:39:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63058 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Value NVARCHAR2(40) DEFAULT NULL 
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63059,469,0,10,53380,'Name',TO_DATE('2012-05-12 12:39:55','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','Y','N','Y','Name',2,TO_DATE('2012-05-12 12:39:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63059 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Name NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63060,275,0,10,53380,'Description',TO_DATE('2012-05-12 12:39:56','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_DATE('2012-05-12 12:39:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63060 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- May 12, 2012 12:39:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63061,2554,0,20,53380,'IsBetaFunctionality',TO_DATE('2012-05-12 12:39:56','YYYY-MM-DD HH24:MI:SS'),0,'This functionality is considered Beta','EE01',1,'Beta functionality is not fully tested or completed.','Y','N','N','N','N','N','N','N','Y','N','Y','Beta Functionality',TO_DATE('2012-05-12 12:39:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63061 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsBetaFunctionality CHAR(1) DEFAULT NULL  CHECK (IsBetaFunctionality IN ('Y','N'))
;

-- May 12, 2012 12:39:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63062,2626,0,17,328,53380,'WorkflowType',TO_DATE('2012-05-12 12:39:57','YYYY-MM-DD HH24:MI:SS'),0,'Type of Workflow','EE01',1,'The type of workflow determines how the workflow is started.','Y','N','N','N','N','N','N','N','Y','N','Y','Workflow Type',TO_DATE('2012-05-12 12:39:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:39:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63062 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:39:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD WorkflowType CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63063,145,0,17,5,53380,'AccessLevel',TO_DATE('2012-05-12 12:39:59','YYYY-MM-DD HH24:MI:SS'),0,'1','Access Level required','EE01',1,'Indicates the access level required for this record or process.','Y','N','N','N','N','N','N','N','Y','N','Y','Data Access Level',TO_DATE('2012-05-12 12:39:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63063 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AccessLevel CHAR(1) DEFAULT '1'
;

-- May 12, 2012 12:40:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63064,2314,0,19,53380,'AD_WF_Responsible_ID',TO_DATE('2012-05-12 12:40:00','YYYY-MM-DD HH24:MI:SS'),0,'Responsible for Workflow Execution','EE01',10,'The ultimate responsibility for a workflow is with an actual user. The Workflow Responsible allows to define ways to find that actual User.','Y','N','N','N','N','N','N','N','Y','N','Y','Workflow Responsible',TO_DATE('2012-05-12 12:40:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63064 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_WF_Responsible_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63065,1514,0,11,53380,'Priority',TO_DATE('2012-05-12 12:40:01','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this request is of a high, medium or low priority.','EE01',22,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','Y','N','Y','Priority',TO_DATE('2012-05-12 12:40:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63065 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Priority NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63066,617,0,15,53380,'ValidFrom',TO_DATE('2012-05-12 12:40:02','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',7,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid from',TO_DATE('2012-05-12 12:40:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63066 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ValidFrom DATE DEFAULT NULL 
;

-- May 12, 2012 12:40:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63067,618,0,15,53380,'ValidTo',TO_DATE('2012-05-12 12:40:03','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',7,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid to',TO_DATE('2012-05-12 12:40:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63067 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ValidTo DATE DEFAULT NULL 
;

-- May 12, 2012 12:40:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63068,2338,0,17,310,53380,'PublishStatus',TO_DATE('2012-05-12 12:40:03','YYYY-MM-DD HH24:MI:SS'),0,'U','Status of Publication','EE01',1,'Used for internal documentation','Y','N','N','N','N','N','N','N','Y','N','Y','Publication Status',TO_DATE('2012-05-12 12:40:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63068 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD PublishStatus CHAR(1) DEFAULT 'U'
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63069,624,0,11,53380,'Version',TO_DATE('2012-05-12 12:40:04','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition','EE01',22,'The Version indicates the version of this table definition.','Y','N','N','N','N','N','N','N','Y','N','Y','Version',TO_DATE('2012-05-12 12:40:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63069 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Version NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63070,2318,0,10,53380,'Author',TO_DATE('2012-05-12 12:40:05','YYYY-MM-DD HH24:MI:SS'),0,'Author/Creator of the Entity','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Author',TO_DATE('2012-05-12 12:40:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63070 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:05 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Author NVARCHAR2(22) DEFAULT NULL 
;

-- May 12, 2012 12:40:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63071,1103,0,20,53380,'IsDefault',TO_DATE('2012-05-12 12:40:05','YYYY-MM-DD HH24:MI:SS'),0,'Default value','EE01',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','N','N','N','N','N','N','N','Y','N','Y','Default',TO_DATE('2012-05-12 12:40:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63071 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsDefault CHAR(1) DEFAULT NULL  CHECK (IsDefault IN ('Y','N'))
;

-- May 12, 2012 12:40:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63072,142,0,19,53380,'AD_WF_Node_ID',TO_DATE('2012-05-12 12:40:06','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Node (activity), step or process','EE01',10,'The Workflow Node indicates a unique step or process in a Workflow.','Y','N','N','N','N','N','N','N','Y','N','Y','Node',TO_DATE('2012-05-12 12:40:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63072 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_WF_Node_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63073,2358,0,19,53380,'AD_WorkflowProcessor_ID',TO_DATE('2012-05-12 12:40:07','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Processor Server','EE01',10,'Workflow Processor Server','Y','N','N','N','N','N','N','N','Y','N','Y','Workflow Processor',TO_DATE('2012-05-12 12:40:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63073 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_WorkflowProcessor_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63074,2321,0,17,299,53380,'DurationUnit',TO_DATE('2012-05-12 12:40:09','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Duration','EE01',1,'Unit to define the length of time for the execution','Y','N','N','N','N','N','N','N','Y','N','Y','Duration Unit',TO_DATE('2012-05-12 12:40:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63074 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DurationUnit CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63075,2323,0,11,53380,'Limit',TO_DATE('2012-05-12 12:40:10','YYYY-MM-DD HH24:MI:SS'),0,'Maximum Duration in Duration Unit','EE01',22,'Maximum (critical) Duration for time management purposes (e.g. starting an escalation procedure, etc.) in Duration Units.','Y','N','N','N','N','N','N','N','Y','N','Y','Duration Limit',TO_DATE('2012-05-12 12:40:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63075 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Limit NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63076,2320,0,11,53380,'Duration',TO_DATE('2012-05-12 12:40:10','YYYY-MM-DD HH24:MI:SS'),0,'Normal Duration in Duration Unit','EE01',22,'Expected (normal) Length of time for the execution','Y','N','N','N','N','N','N','N','Y','N','Y','Duration',TO_DATE('2012-05-12 12:40:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63076 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Duration NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63077,2319,0,37,53380,'Cost',TO_DATE('2012-05-12 12:40:11','YYYY-MM-DD HH24:MI:SS'),0,'Cost information','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Cost',TO_DATE('2012-05-12 12:40:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63077 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Cost NUMBER DEFAULT NULL 
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63078,2333,0,11,53380,'WorkingTime',TO_DATE('2012-05-12 12:40:12','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Execution Time','EE01',22,'Amount of time the performer of the activity needs to perform the task in Duration Unit','Y','N','N','N','N','N','N','N','Y','N','Y','Working Time',TO_DATE('2012-05-12 12:40:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63078 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD WorkingTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63079,2331,0,11,53380,'WaitingTime',TO_DATE('2012-05-12 12:40:13','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Waiting time','EE01',22,'Amount of time needed to prepare the performance of the task on Duration Units','Y','N','N','N','N','N','N','N','Y','N','Y','Waiting Time',TO_DATE('2012-05-12 12:40:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63079 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD WaitingTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63080,126,0,19,53380,'AD_Table_ID',TO_DATE('2012-05-12 12:40:13','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information','EE01',10,'The Database Table provides the information of the table definition','Y','N','N','N','N','N','N','N','Y','N','Y','Table',TO_DATE('2012-05-12 12:40:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63080 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Table_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63081,2624,0,10,53380,'DocValueLogic',TO_DATE('2012-05-12 12:40:14','YYYY-MM-DD HH24:MI:SS'),0,'Logic to determine Workflow Start - If true, a workflow process is started for the document','EE01',2000,'You can enter simple logic using variables like @Created@=@Updated@, which fires, when a record is created. If you need to evaluate also values of other records, you need to use SQL logic and need to prefix this logic with "SQL=". Example: start a Order verify workflow, when a business partner ordered something and is over the credit limit  "SQL=EXISTS (SELECT * FROM C_BPartner bp WHERE C_Order. C_BPartner_ID=bp. C_BPartner_ID AND SO_CreditUsed > SO_CreditLimit)".
Note that the SQL based logic checks for duplicate workflows (i.e. a workflow is started only once per record).','Y','N','N','N','N','N','N','N','Y','N','Y','Document Value Logic',TO_DATE('2012-05-12 12:40:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63081 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DocValueLogic NVARCHAR2(2000) DEFAULT NULL 
;

-- May 12, 2012 12:40:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63082,1682,0,18,389,53380,'EntityType',TO_DATE('2012-05-12 12:40:15','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization','EE01',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','N','N','N','Y','N','Y','Entity Type',TO_DATE('2012-05-12 12:40:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63082 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD EntityType VARCHAR2(40) DEFAULT NULL 
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55485,0,'ResponsibleName',TO_DATE('2012-05-12 12:40:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Responsible Name','Responsible Name',TO_DATE('2012-05-12 12:40:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55485 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63083,55485,0,10,53380,'ResponsibleName',TO_DATE('2012-05-12 12:40:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','Y','N','Y','Responsible Name',TO_DATE('2012-05-12 12:40:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63083 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ResponsibleName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55486,0,'NodeValue',TO_DATE('2012-05-12 12:40:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Node Key','Node Key',TO_DATE('2012-05-12 12:40:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55486 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:40:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63084,55486,0,10,53380,'NodeValue',TO_DATE('2012-05-12 12:40:17','YYYY-MM-DD HH24:MI:SS'),0,'EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Node Key',TO_DATE('2012-05-12 12:40:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63084 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD NodeValue NVARCHAR2(40) DEFAULT NULL 
;

-- May 12, 2012 12:40:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55487,0,'WFProcessorName',TO_DATE('2012-05-12 12:40:17','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','WF Processor Name','WF Processor Name',TO_DATE('2012-05-12 12:40:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55487 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:40:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63085,55487,0,10,53380,'WFProcessorName',TO_DATE('2012-05-12 12:40:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','Y','N','Y','WF Processor Name',TO_DATE('2012-05-12 12:40:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63085 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD WFProcessorName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63086,587,0,10,53380,'TableName',TO_DATE('2012-05-12 12:40:18','YYYY-MM-DD HH24:MI:SS'),0,'Name of the table in the database','EE01',60,'The DB Table Name indicates the name of the table in database.','Y','N','N','N','N','N','N','N','Y','N','Y','DB Table Name',TO_DATE('2012-05-12 12:40:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63086 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD TableName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55488,0,'NodeName',TO_DATE('2012-05-12 12:40:19','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Node Name','Node Name',TO_DATE('2012-05-12 12:40:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55488 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63087,55488,0,10,53380,'NodeName',TO_DATE('2012-05-12 12:40:19','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','Y','N','Y','Node Name',TO_DATE('2012-05-12 12:40:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63087 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD NodeName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63088,362,0,20,53380,'IsCentrallyMaintained',TO_DATE('2012-05-12 12:40:20','YYYY-MM-DD HH24:MI:SS'),0,'Information maintained in System Element table','EE01',1,'The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','N','N','N','N','N','N','N','Y','N','Y','Centrally maintained',TO_DATE('2012-05-12 12:40:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63088 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsCentrallyMaintained CHAR(1) DEFAULT NULL  CHECK (IsCentrallyMaintained IN ('Y','N'))
;

-- May 12, 2012 12:40:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63089,2327,0,17,303,53380,'StartMode',TO_DATE('2012-05-12 12:40:20','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Activity Start Mode ','EE01',1,'How is the execution of an activity triggered. Automatic are triggered implicitly by the system, Manual explicitly by the User.','Y','N','N','N','N','N','N','N','Y','N','Y','Start Mode',TO_DATE('2012-05-12 12:40:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63089 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD StartMode CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63090,2322,0,17,303,53380,'FinishMode',TO_DATE('2012-05-12 12:40:21','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Activity Finish Mode','EE01',1,'How the system operated at the end of an activity. Automatic  implies return when the invoked applications finished control - Manual the user has to explicitly terminate the activity.','Y','N','N','N','N','N','N','N','Y','N','Y','Finish Mode',TO_DATE('2012-05-12 12:40:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63090 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD FinishMode CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63091,2336,0,17,301,53380,'JoinElement',TO_DATE('2012-05-12 12:40:22','YYYY-MM-DD HH24:MI:SS'),0,'X','Semantics for multiple incoming Transitions','EE01',1,'Semantics for multiple incoming Transitions for a Node/Activity. AND joins all concurrent threads - XOR requires one thread (no synchronization).','Y','N','N','N','N','N','N','N','Y','N','Y','Join Element',TO_DATE('2012-05-12 12:40:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63091 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD JoinElement CHAR(1) DEFAULT 'X'
;

-- May 12, 2012 12:40:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63092,2337,0,17,301,53380,'SplitElement',TO_DATE('2012-05-12 12:40:22','YYYY-MM-DD HH24:MI:SS'),0,'X','Semantics for multiple outgoing Transitions','EE01',1,'Semantics for multiple outgoing Transitions for a Node/Activity.  AND represents multiple concurrent threads - XOR represents the first transition with a true Transition condition.','Y','N','N','N','N','N','N','N','Y','N','Y','Split Element',TO_DATE('2012-05-12 12:40:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63092 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD SplitElement CHAR(1) DEFAULT 'X'
;

-- May 12, 2012 12:40:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63093,152,0,17,302,53380,'Action',TO_DATE('2012-05-12 12:40:24','YYYY-MM-DD HH24:MI:SS'),0,'W','Indicates the Action to be performed','EE01',1,'The Action field is a drop down list box which indicates the Action to be performed for this Item.','Y','N','N','N','N','N','N','N','Y','N','Y','Action',TO_DATE('2012-05-12 12:40:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63093 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Action CHAR(1) DEFAULT 'W'
;

-- May 12, 2012 12:40:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63094,1639,0,19,53380,'AD_Image_ID',TO_DATE('2012-05-12 12:40:24','YYYY-MM-DD HH24:MI:SS'),0,'Image or Icon','EE01',22,'Images and Icon can be used to display supported graphic formats (gif, jpg, png).
You can either load the image (in the database) or point to a graphic via a URI (i.e. it can point to a resource, http address)','Y','N','N','N','N','N','N','N','Y','N','Y','Image',TO_DATE('2012-05-12 12:40:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63094 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Image_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63095,2638,0,17,221,53380,'DynPriorityUnit',TO_DATE('2012-05-12 12:40:25','YYYY-MM-DD HH24:MI:SS'),0,'Change of priority when Activity is suspended waiting for user','EE01',1,'Starting with the Process / Node priority level, the priority of the suspended activity can be changed dynamically. Example +5 every 10 minutes','Y','N','N','N','N','N','N','N','Y','N','Y','Dynamic Priority Unit',TO_DATE('2012-05-12 12:40:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63095 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DynPriorityUnit CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63096,2636,0,22,53380,'DynPriorityChange',TO_DATE('2012-05-12 12:40:26','YYYY-MM-DD HH24:MI:SS'),0,'Change of priority when Activity is suspended waiting for user','EE01',22,'Starting with the Process / Node priority level, the priority of the suspended activity can be changed dynamically. Example +5 every 10 minutes','Y','N','N','N','N','N','N','N','Y','N','Y','Dynamic Priority Change',TO_DATE('2012-05-12 12:40:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63096 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DynPriorityChange NUMBER DEFAULT NULL 
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63097,287,0,17,135,53380,'DocAction',TO_DATE('2012-05-12 12:40:28','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE01',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','N','N','N','N','N','N','N','Y','N','Y','Document Action',TO_DATE('2012-05-12 12:40:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63097 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DocAction NVARCHAR2(2) DEFAULT NULL 
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63098,143,0,19,53380,'AD_Window_ID',TO_DATE('2012-05-12 12:40:28','YYYY-MM-DD HH24:MI:SS'),0,'Data entry or display window','EE01',10,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Window',TO_DATE('2012-05-12 12:40:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63098 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Window_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:29 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63099,1298,0,19,53380,'AD_Form_ID',TO_DATE('2012-05-12 12:40:29','YYYY-MM-DD HH24:MI:SS'),0,'Special Form','EE01',10,'The Special Form field identifies a unique Special Form in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Special Form',TO_DATE('2012-05-12 12:40:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:29 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63099 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:29 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Form_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63100,104,0,19,53380,'AD_Column_ID',TO_DATE('2012-05-12 12:40:29','YYYY-MM-DD HH24:MI:SS'),0,'Column in the table','EE01',1,'Link to the database column of the table','Y','N','N','N','N','N','N','N','Y','N','Y','Column',TO_DATE('2012-05-12 12:40:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63100 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Column_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63101,228,0,10,53380,'ColumnName',TO_DATE('2012-05-12 12:40:30','YYYY-MM-DD HH24:MI:SS'),0,'Name of the column in the database','EE01',60,'The Column Name indicates the name of a column on a table as defined in the database.','Y','N','N','N','N','N','N','N','Y','N','Y','DB Column Name',TO_DATE('2012-05-12 12:40:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63101 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ColumnName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:31 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63102,2315,0,10,53380,'AttributeName',TO_DATE('2012-05-12 12:40:30','YYYY-MM-DD HH24:MI:SS'),0,'Name of the Attribute','EE01',60,'Identifier of the attribute','Y','N','N','N','N','N','N','N','Y','N','Y','Attribute Name',TO_DATE('2012-05-12 12:40:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:31 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63102 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:31 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AttributeName NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:32 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63103,2317,0,10,53380,'AttributeValue',TO_DATE('2012-05-12 12:40:31','YYYY-MM-DD HH24:MI:SS'),0,'Value of the Attribute','EE01',60,'Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD','Y','N','N','N','N','N','N','N','Y','N','Y','Attribute Value',TO_DATE('2012-05-12 12:40:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:32 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63103 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:32 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AttributeValue NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:33 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63104,2879,0,17,363,53380,'EMailRecipient',TO_DATE('2012-05-12 12:40:33','YYYY-MM-DD HH24:MI:SS'),0,'Recipient of the EMail','EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','EMail Recipient',TO_DATE('2012-05-12 12:40:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:33 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63104 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:33 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD EMailRecipient CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63105,881,0,10,53380,'EMail',TO_DATE('2012-05-12 12:40:33','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Mail Address','EE01',60,'The Email Address is the Electronic Mail ID for this User and should be fully qualified (e.g. joe.smith@company.com). The Email Address is used to access the self service application functionality from the web.','Y','N','N','N','N','N','N','N','Y','N','Y','EMail Address',TO_DATE('2012-05-12 12:40:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63105 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD EMail NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63106,1515,0,19,53380,'R_MailText_ID',TO_DATE('2012-05-12 12:40:34','YYYY-MM-DD HH24:MI:SS'),0,'Text templates for mailings','EE01',10,'The Mail Template indicates the mail template for return messages. Mail text can include variables.  The priority of parsing is User/Contact, Business Partner and then the underlying business object (like Request, Dunning, Workflow object).<br>
So, @Name@ would resolve into the User name (if user is defined defined), then Business Partner name (if business partner is defined) and then the Name of the business object if it has a Name.<br>
For Multi-Lingual systems, the template is translated based on the Business Partner''s language selection.','Y','N','N','N','N','N','N','N','Y','N','Y','Mail Template',TO_DATE('2012-05-12 12:40:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63106 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD R_MailText_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:35 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63107,128,0,19,53380,'AD_Task_ID',TO_DATE('2012-05-12 12:40:34','YYYY-MM-DD HH24:MI:SS'),0,'Operation System Task','EE01',22,'The Task field identifies a Operation System Task in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','OS Task',TO_DATE('2012-05-12 12:40:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:35 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63107 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:35 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Task_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63108,2328,0,17,307,53380,'SubflowExecution',TO_DATE('2012-05-12 12:40:35','YYYY-MM-DD HH24:MI:SS'),0,'Mode how the sub-workflow is executed','EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Subflow Execution',TO_DATE('2012-05-12 12:40:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63108 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD SubflowExecution CHAR(1) DEFAULT NULL 
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63109,117,0,19,53380,'AD_Process_ID',TO_DATE('2012-05-12 12:40:36','YYYY-MM-DD HH24:MI:SS'),0,'Process or Report','EE01',10,'The Process field identifies a unique Process or Report in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Process',TO_DATE('2012-05-12 12:40:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63109 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Process_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:37 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63110,2464,0,11,53380,'WaitTime',TO_DATE('2012-05-12 12:40:37','YYYY-MM-DD HH24:MI:SS'),0,'Time in minutes to wait (sleep)','EE01',22,'Time in minutes to be suspended (sleep)','Y','N','N','N','N','N','N','N','Y','N','Y','Wait Time',TO_DATE('2012-05-12 12:40:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:37 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63110 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:37 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD WaitTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63111,144,0,19,53380,'AD_Workflow_ID',TO_DATE('2012-05-12 12:40:37','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01',10,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Workflow',TO_DATE('2012-05-12 12:40:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63111 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD AD_Workflow_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63112,290,0,10,53380,'DocumentNo',TO_DATE('2012-05-12 12:40:38','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','Y','Y','N','Y','Document No',TO_DATE('2012-05-12 12:40:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63112 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD DocumentNo NVARCHAR2(30) DEFAULT NULL 
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63113,53239,0,22,53380,'UnitsCycles',TO_DATE('2012-05-12 12:40:38','YYYY-MM-DD HH24:MI:SS'),0,'The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.','EE01',14,'When Units by Cycles are defined the duration time is the total of time to manufactured the units','Y','N','N','N','N','N','N','N','Y','N','Y','Units by Cycles',TO_DATE('2012-05-12 12:40:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63113 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD UnitsCycles NUMBER DEFAULT NULL 
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63114,53241,0,11,53380,'OverlapUnits',TO_DATE('2012-05-12 12:40:39','YYYY-MM-DD HH24:MI:SS'),0,'Overlap Units are number of units that must be completed before they are moved the next activity','EE01',22,'When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.','Y','N','N','N','N','N','N','N','Y','N','Y','Overlap Units',TO_DATE('2012-05-12 12:40:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63114 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD OverlapUnits NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63115,53237,0,20,53380,'IsMilestone',TO_DATE('2012-05-12 12:40:39','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Is Milestone',TO_DATE('2012-05-12 12:40:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63115 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsMilestone CHAR(1) DEFAULT NULL  CHECK (IsMilestone IN ('Y','N'))
;

-- May 12, 2012 12:40:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63116,53238,0,20,53380,'IsSubcontracting',TO_DATE('2012-05-12 12:40:40','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Is Subcontracting',TO_DATE('2012-05-12 12:40:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63116 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD IsSubcontracting CHAR(1) DEFAULT NULL  CHECK (IsSubcontracting IN ('Y','N'))
;

-- May 12, 2012 12:40:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63117,53243,0,29,53380,'QtyBatchSize',TO_DATE('2012-05-12 12:40:41','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Qty Batch Size',TO_DATE('2012-05-12 12:40:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63117 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD QtyBatchSize NUMBER DEFAULT NULL 
;

-- May 12, 2012 12:40:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63118,53242,0,17,53224,53380,'ProcessType',TO_DATE('2012-05-12 12:40:42','YYYY-MM-DD HH24:MI:SS'),0,'EE01',2,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Type',TO_DATE('2012-05-12 12:40:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63118 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ProcessType NVARCHAR2(2) DEFAULT NULL 
;

-- May 12, 2012 12:40:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63119,326,0,14,53380,'Help',TO_DATE('2012-05-12 12:40:43','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_DATE('2012-05-12 12:40:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63119 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Help NVARCHAR2(2000) DEFAULT NULL 
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63120,1777,0,19,53380,'S_Resource_ID',TO_DATE('2012-05-12 12:40:43','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Resource',TO_DATE('2012-05-12 12:40:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63120 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD S_Resource_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63121,53240,0,11,53380,'MovingTime',TO_DATE('2012-05-12 12:40:44','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Moving Time',TO_DATE('2012-05-12 12:40:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63121 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD MovingTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63122,53234,0,11,53380,'QueuingTime',TO_DATE('2012-05-12 12:40:44','YYYY-MM-DD HH24:MI:SS'),0,'Queue time is the time a job waits at a work center before begin handled.','EE01',10,'Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.','Y','N','N','N','N','N','N','N','Y','N','Y','Queuing Time',TO_DATE('2012-05-12 12:40:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63122 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD QueuingTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63123,2777,0,11,53380,'SetupTime',TO_DATE('2012-05-12 12:40:45','YYYY-MM-DD HH24:MI:SS'),0,'Setup time before starting Production','EE01',22,'Once per operation','Y','N','N','N','N','N','N','N','Y','N','Y','Setup Time',TO_DATE('2012-05-12 12:40:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63123 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD SetupTime NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63124,53272,0,11,53380,'Yield',TO_DATE('2012-05-12 12:40:46','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',22,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities','Y','N','N','N','N','N','N','N','Y','N','Y','Yield %',TO_DATE('2012-05-12 12:40:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63124 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:46 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD Yield NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 12:40:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63125,54121,0,10,53380,'ResourceValue',TO_DATE('2012-05-12 12:40:46','YYYY-MM-DD HH24:MI:SS'),0,'Key of the Resource','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Resource Key',TO_DATE('2012-05-12 12:40:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63125 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD ResourceValue NVARCHAR2(40) DEFAULT NULL 
;

-- May 12, 2012 12:40:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55489,0,'NodeNextValue',TO_DATE('2012-05-12 12:40:47','YYYY-MM-DD HH24:MI:SS'),0,'Next Node in Transition','EE01','The Next Node Serach Key indicates the next step or task in this Workflow.','Y','Node Next Key','Node Next Key',TO_DATE('2012-05-12 12:40:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:47 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55489 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 12:40:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63126,55489,0,10,53380,'NodeNextValue',TO_DATE('2012-05-12 12:40:47','YYYY-MM-DD HH24:MI:SS'),0,'Next Node in Transition','EE01',22,'The Next Node Serach Key indicates the next step or task in this Workflow.','Y','N','N','N','N','N','N','N','Y','N','Y','Node Next Key',TO_DATE('2012-05-12 12:40:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 12, 2012 12:40:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63126 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 12:40:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
ALTER TABLE I_Workflow ADD NodeNextValue NVARCHAR2(22) DEFAULT NULL 
;

-- May 12, 2012 12:40:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53509,53380,53175,NULL,TO_DATE('2012-05-12 12:40:48','YYYY-MM-DD HH24:MI:SS'),0,'Import Workflow','EE01','N','Before importing, Adempiere checks the IDs,  Adempiere tries to map to existing Workflow. If the imported record could be matched, Workflow field values will only be overwritten, if the corresponding  Import field is explicitly.','Y','N','N','Y','N','N','N','N','Import Workflow','N',10,0,TO_DATE('2012-05-12 12:40:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:48 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53509 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- May 12, 2012 12:40:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63050,64233,0,53509,TO_DATE('2012-05-12 12:40:48','YYYY-MM-DD HH24:MI:SS'),0,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','Y','Active',0,0,0,TO_DATE('2012-05-12 12:40:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64233 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63077,64234,0,53509,TO_DATE('2012-05-12 12:40:49','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Cost information',22,'EE01','N','Y','Y','N','N','N','N','Y','Cost',0,0,0,TO_DATE('2012-05-12 12:40:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:49 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64234 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63110,64235,0,53509,TO_DATE('2012-05-12 12:40:49','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Time in minutes to wait (sleep)',22,'EE01','Time in minutes to be suspended (sleep)','N','Y','Y','N','N','N','N','Y','Wait Time',0,0,0,TO_DATE('2012-05-12 12:40:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64235 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63045,64236,0,53509,TO_DATE('2012-05-12 12:40:50','YYYY-MM-DD HH24:MI:SS'),0,NULL,10,'EE01','N','Y','Y','N','N','N','N','N','I_Workflow_ID',0,0,0,TO_DATE('2012-05-12 12:40:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:50 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64236 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:51 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63054,64237,0,53509,TO_DATE('2012-05-12 12:40:50','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Has this import been processed',1,'EE01','The Imported check box indicates if this import has been processed.','N','Y','Y','Y','N','N','Y','N','Imported',0,10,0,TO_DATE('2012-05-12 12:40:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:51 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64237 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:51 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63111,64238,0,53509,TO_DATE('2012-05-12 12:40:51','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow or combination of tasks',10,'EE01','The Workflow field identifies a unique Workflow in the system.','N','Y','Y','Y','N','N','Y','N','Workflow',0,20,0,TO_DATE('2012-05-12 12:40:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:51 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64238 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63072,64239,0,53509,TO_DATE('2012-05-12 12:40:51','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Node (activity), step or process',10,'EE01','The Workflow Node indicates a unique step or process in a Workflow.','N','Y','Y','Y','N','N','Y','Y','Node',0,30,0,TO_DATE('2012-05-12 12:40:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64239 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63053,64240,0,53509,TO_DATE('2012-05-12 12:40:52','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Messages generated from import process',2000,'EE01','The Import Error Message displays any error messages generated during the import process.','N','Y','Y','Y','N','N','Y','N','Import Error Message',0,40,0,TO_DATE('2012-05-12 12:40:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:52 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64240 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63046,64241,0,53509,TO_DATE('2012-05-12 12:40:52','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','Y','N','N','N','N','Client',0,50,0,TO_DATE('2012-05-12 12:40:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64241 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63057,64242,0,53509,TO_DATE('2012-05-12 12:40:53','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Key of the Organization',20,'EE01','N','Y','Y','Y','N','N','N','N','Org Key',0,60,0,TO_DATE('2012-05-12 12:40:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:53 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64242 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63047,64243,0,53509,TO_DATE('2012-05-12 12:40:53','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','Y','N','N','N','Y','Organization',0,70,0,TO_DATE('2012-05-12 12:40:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64243 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63058,64244,0,53509,TO_DATE('2012-05-12 12:40:54','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Search key for the record in the format required - must be unique',20,'EE01','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N','Y','Y','Y','N','N','N','N','Search Key',0,80,0,TO_DATE('2012-05-12 12:40:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:54 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64244 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63059,64245,0,53509,TO_DATE('2012-05-12 12:40:54','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Alphanumeric identifier of the entity',20,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','N','Y','Y','Y','N','N','N','Y','Name',0,90,0,TO_DATE('2012-05-12 12:40:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64245 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63062,64246,0,53509,TO_DATE('2012-05-12 12:40:55','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Type of Workflow',1,'EE01','The type of workflow determines how the workflow is started.','N','Y','Y','Y','N','N','N','N','Workflow Type',0,100,0,TO_DATE('2012-05-12 12:40:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:55 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64246 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63061,64247,0,53509,TO_DATE('2012-05-12 12:40:55','YYYY-MM-DD HH24:MI:SS'),0,NULL,'This functionality is considered Beta',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Beta functionality is not fully tested or completed.','N','Y','Y','Y','N','N','N','Y','Beta Functionality',0,110,0,TO_DATE('2012-05-12 12:40:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64247 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63063,64248,0,53509,TO_DATE('2012-05-12 12:40:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Access Level required',1,'EE01','Indicates the access level required for this record or process.','N','Y','Y','Y','N','N','N','N','Data Access Level',0,120,0,TO_DATE('2012-05-12 12:40:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:56 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64248 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63071,64249,0,53509,TO_DATE('2012-05-12 12:40:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Default value',1,'EE01','The Default Checkbox indicates if this record will be used as a default value.','N','Y','Y','Y','N','N','N','Y','Default',0,130,0,TO_DATE('2012-05-12 12:40:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64249 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63117,64250,0,53509,TO_DATE('2012-05-12 12:40:57','YYYY-MM-DD HH24:MI:SS'),0,NULL,10,'@WorkflowType@=''M'' | @WorkflowType@=''Q''','EE01','N','Y','Y','Y','N','N','N','N','Qty Batch Size',0,140,0,TO_DATE('2012-05-12 12:40:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:57 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64250 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63118,64251,0,53509,TO_DATE('2012-05-12 12:40:57','YYYY-MM-DD HH24:MI:SS'),0,NULL,2,'@WorkflowType@=''M'' | @WorkflowType@=''Q''','EE01','N','Y','Y','Y','N','N','N','Y','Process Type',0,150,0,TO_DATE('2012-05-12 12:40:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64251 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63066,64252,0,53509,TO_DATE('2012-05-12 12:40:58','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Valid from including this date (first day)',7,'EE01','The Valid From date indicates the first day of a date range','N','Y','Y','Y','N','N','N','N','Valid from',0,160,0,TO_DATE('2012-05-12 12:40:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:58 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64252 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:59 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63067,64253,0,53509,TO_DATE('2012-05-12 12:40:58','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Valid to including this date (last day)',7,'EE01','The Valid To date indicates the last day of a date range','N','Y','Y','Y','N','N','N','Y','Valid to',0,170,0,TO_DATE('2012-05-12 12:40:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:59 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64253 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:40:59 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63112,64254,0,53509,TO_DATE('2012-05-12 12:40:59','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Document sequence number of the document',20,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N','Y','Y','Y','N','N','N','N','Document No',0,180,0,TO_DATE('2012-05-12 12:40:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:40:59 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64254 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63069,64255,0,53509,TO_DATE('2012-05-12 12:40:59','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Version of the table definition',22,'EE01','The Version indicates the version of this table definition.','N','Y','Y','Y','N','N','N','Y','Version',0,190,0,TO_DATE('2012-05-12 12:40:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64255 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63070,64256,0,53509,TO_DATE('2012-05-12 12:41:00','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Author/Creator of the Entity',22,'EE01','N','Y','Y','Y','N','N','N','N','Author',0,200,0,TO_DATE('2012-05-12 12:41:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:00 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64256 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63065,64257,0,53509,TO_DATE('2012-05-12 12:41:00','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Indicates if this request is of a high, medium or low priority.',22,'EE01','The Priority indicates the importance of this request.','N','Y','Y','Y','N','N','N','Y','Priority',0,210,0,TO_DATE('2012-05-12 12:41:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64257 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63085,64258,0,53509,TO_DATE('2012-05-12 12:41:01','YYYY-MM-DD HH24:MI:SS'),0,NULL,20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','N','Y','Y','Y','N','N','N','N','WF Processor Name',0,220,0,TO_DATE('2012-05-12 12:41:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:01 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64258 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63073,64259,0,53509,TO_DATE('2012-05-12 12:41:01','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Processor Server',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Workflow Processor Server','N','Y','Y','Y','N','N','N','Y','Workflow Processor',0,230,0,TO_DATE('2012-05-12 12:41:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64259 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63074,64260,0,53509,TO_DATE('2012-05-12 12:41:02','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Unit of Duration',1,'EE01','Unit to define the length of time for the execution','N','Y','Y','Y','N','N','N','N','Duration Unit',0,240,0,TO_DATE('2012-05-12 12:41:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:02 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64260 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63068,64261,0,53509,TO_DATE('2012-05-12 12:41:03','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Status of Publication',1,'EE01','Used for internal documentation','N','Y','Y','Y','N','N','N','Y','Publication Status',0,250,0,TO_DATE('2012-05-12 12:41:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64261 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63086,64262,0,53509,TO_DATE('2012-05-12 12:41:03','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Name of the table in the database',20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The DB Table Name indicates the name of the table in database.','N','Y','Y','Y','N','N','N','N','DB Table Name',0,260,0,TO_DATE('2012-05-12 12:41:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:03 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64262 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63080,64263,0,53509,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Database Table information',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Database Table provides the information of the table definition','N','Y','Y','Y','N','N','N','Y','Table',0,270,0,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64263 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63081,64264,0,53509,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Logic to determine Workflow Start - If true, a workflow process is started for the document',2000,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','You can enter simple logic using variables like @Created@=@Updated@, which fires, when a record is created. If you need to evaluate also values of other records, you need to use SQL logic and need to prefix this logic with "SQL=". Example: start a Order verify workflow, when a business partner ordered something and is over the credit limit  "SQL=EXISTS (SELECT * FROM C_BPartner bp WHERE C_Order. C_BPartner_ID=bp. C_BPartner_ID AND SO_CreditUsed > SO_CreditLimit)".
Note that the SQL based logic checks for duplicate workflows (i.e. a workflow is started only once per record).','N','Y','Y','Y','N','N','N','N','Document Value Logic',0,280,0,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:04 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64264 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63082,64265,0,53509,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Dictionary Entity Type; Determines ownership and synchronization',20,'EE01','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','N','Y','Y','Y','N','N','N','N','Entity Type',0,290,0,TO_DATE('2012-05-12 12:41:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64265 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63088,64266,0,53509,TO_DATE('2012-05-12 12:41:06','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Information maintained in System Element table',1,'EE01','The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','N','Y','Y','Y','N','N','N','Y','Centrally maintained',0,300,0,TO_DATE('2012-05-12 12:41:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64266 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:06 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63084,64267,0,53509,TO_DATE('2012-05-12 12:41:06','YYYY-MM-DD HH24:MI:SS'),0,NULL,20,'EE01','N','Y','Y','Y','N','N','N','N','Node Key',0,310,0,TO_DATE('2012-05-12 12:41:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64267 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63126,64268,0,53509,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Next Node in Transition',22,'EE01','The Next Node Serach Key indicates the next step or task in this Workflow.','N','Y','Y','Y','N','N','N','Y','Node Next Key',0,320,0,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64268 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63087,64269,0,53509,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0,NULL,60,'EE01','N','Y','Y','Y','N','N','N','N','Node Name',0,330,0,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:07 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64269 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:08 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63060,64270,0,53509,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','N','Y','Y','Y','N','N','N','N','Description',0,340,0,TO_DATE('2012-05-12 12:41:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:08 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64270 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:08 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63119,64271,0,53509,TO_DATE('2012-05-12 12:41:08','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','N','Y','Y','Y','N','N','N','N','Comment/Help',0,350,0,TO_DATE('2012-05-12 12:41:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:08 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64271 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63125,64272,0,53509,TO_DATE('2012-05-12 12:41:08','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Key of the Resource',25,'@WorkflowType@=''M'' | @WorkflowType@=''Q''','EE01','N','Y','Y','Y','N','N','N','N','Resource Key',0,360,0,TO_DATE('2012-05-12 12:41:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64272 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63120,64273,0,53509,TO_DATE('2012-05-12 12:41:09','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Resource',22,'@WorkflowType@=''M'' | @WorkflowType@=''Q''','EE01','N','Y','Y','Y','N','N','N','Y','Resource',0,370,0,TO_DATE('2012-05-12 12:41:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:09 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64273 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63115,64274,0,53509,TO_DATE('2012-05-12 12:41:09','YYYY-MM-DD HH24:MI:SS'),0,NULL,1,'EE01','N','Y','Y','Y','N','N','N','N','Is Milestone',0,380,0,TO_DATE('2012-05-12 12:41:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64274 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63116,64275,0,53509,TO_DATE('2012-05-12 12:41:10','YYYY-MM-DD HH24:MI:SS'),0,NULL,1,'EE01','N','Y','Y','Y','N','N','N','Y','Is Subcontracting',0,390,0,TO_DATE('2012-05-12 12:41:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:10 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64275 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63083,64276,0,53509,TO_DATE('2012-05-12 12:41:10','YYYY-MM-DD HH24:MI:SS'),0,NULL,20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','N','Y','Y','Y','N','N','N','N','Responsible Name',0,400,0,TO_DATE('2012-05-12 12:41:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64276 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63064,64277,0,53509,TO_DATE('2012-05-12 12:41:11','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Responsible for Workflow Execution',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The ultimate responsibility for a workflow is with an actual user. The Workflow Responsible allows to define ways to find that actual User.','N','Y','Y','Y','N','N','N','Y','Workflow Responsible',0,410,0,TO_DATE('2012-05-12 12:41:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:11 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64277 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63089,64278,0,53509,TO_DATE('2012-05-12 12:41:11','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Activity Start Mode ',1,'EE01','How is the execution of an activity triggered. Automatic are triggered implicitly by the system, Manual explicitly by the User.','N','Y','Y','Y','N','N','N','N','Start Mode',0,420,0,TO_DATE('2012-05-12 12:41:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64278 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63090,64279,0,53509,TO_DATE('2012-05-12 12:41:12','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Activity Finish Mode',1,'EE01','How the system operated at the end of an activity. Automatic  implies return when the invoked applications finished control - Manual the user has to explicitly terminate the activity.','N','Y','Y','Y','N','N','N','Y','Finish Mode',0,430,0,TO_DATE('2012-05-12 12:41:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:12 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64279 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63091,64280,0,53509,TO_DATE('2012-05-12 12:41:12','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Semantics for multiple incoming Transitions',1,'EE01','Semantics for multiple incoming Transitions for a Node/Activity. AND joins all concurrent threads - XOR requires one thread (no synchronization).','N','Y','Y','Y','N','N','N','N','Join Element',0,440,0,TO_DATE('2012-05-12 12:41:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64280 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63092,64281,0,53509,TO_DATE('2012-05-12 12:41:13','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Semantics for multiple outgoing Transitions',1,'EE01','Semantics for multiple outgoing Transitions for a Node/Activity.  AND represents multiple concurrent threads - XOR represents the first transition with a true Transition condition.','N','Y','Y','Y','N','N','N','Y','Split Element',0,450,0,TO_DATE('2012-05-12 12:41:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:13 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64281 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63093,64282,0,53509,TO_DATE('2012-05-12 12:41:13','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Indicates the Action to be performed',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Action field is a drop down list box which indicates the Action to be performed for this Item.','N','Y','Y','Y','N','N','N','N','Action',0,460,0,TO_DATE('2012-05-12 12:41:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64282 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63094,64283,0,53509,TO_DATE('2012-05-12 12:41:14','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Image or Icon',22,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Images and Icon can be used to display supported graphic formats (gif, jpg, png).
You can either load the image (in the database) or point to a graphic via a URI (i.e. it can point to a resource, http address)','N','Y','Y','Y','N','N','N','Y','Image',0,470,0,TO_DATE('2012-05-12 12:41:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:14 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64283 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63097,64284,0,53509,TO_DATE('2012-05-12 12:41:14','YYYY-MM-DD HH24:MI:SS'),0,NULL,'The targeted status of the document',2,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','You find the current status in the Document Status field. The options are listed in a popup','N','Y','Y','Y','N','N','N','N','Document Action',0,480,0,TO_DATE('2012-05-12 12:41:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64284 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63075,64285,0,53509,TO_DATE('2012-05-12 12:41:15','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Maximum Duration in Duration Unit',22,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Maximum (critical) Duration for time management purposes (e.g. starting an escalation procedure, etc.) in Duration Units.','N','Y','Y','Y','N','N','N','Y','Duration Limit',0,490,0,TO_DATE('2012-05-12 12:41:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:15 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64285 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63095,64286,0,53509,TO_DATE('2012-05-12 12:41:15','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Change of priority when Activity is suspended waiting for user',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Starting with the Process / Node priority level, the priority of the suspended activity can be changed dynamically. Example +5 every 10 minutes','N','Y','Y','Y','N','N','N','N','Dynamic Priority Unit',0,500,0,TO_DATE('2012-05-12 12:41:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64286 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63096,64287,0,53509,TO_DATE('2012-05-12 12:41:16','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Change of priority when Activity is suspended waiting for user',22,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Starting with the Process / Node priority level, the priority of the suspended activity can be changed dynamically. Example +5 every 10 minutes','N','Y','Y','Y','N','N','N','Y','Dynamic Priority Change',0,510,0,TO_DATE('2012-05-12 12:41:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:16 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64287 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63114,64288,0,53509,TO_DATE('2012-05-12 12:41:16','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Overlap Units are number of units that must be completed before they are moved the next activity',22,'EE01','When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.','N','Y','Y','Y','N','N','N','N','Overlap Units',0,520,0,TO_DATE('2012-05-12 12:41:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64288 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63113,64289,0,53509,TO_DATE('2012-05-12 12:41:17','YYYY-MM-DD HH24:MI:SS'),0,NULL,'The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.',14,'EE01','When Units by Cycles are defined the duration time is the total of time to manufactured the units','N','Y','Y','Y','N','N','N','Y','Units by Cycles',0,530,0,TO_DATE('2012-05-12 12:41:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:17 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64289 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63123,64290,0,53509,TO_DATE('2012-05-12 12:41:17','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Setup time before starting Production',22,'EE01','Once per operation','N','Y','Y','Y','N','N','N','N','Setup Time',0,540,0,TO_DATE('2012-05-12 12:41:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64290 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63076,64291,0,53509,TO_DATE('2012-05-12 12:41:18','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Normal Duration in Duration Unit',22,'EE01','Expected (normal) Length of time for the execution','Y','Y','Y','Y','N','N','N','Y','Duration',0,550,0,TO_DATE('2012-05-12 12:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:18 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64291 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63121,64292,0,53509,TO_DATE('2012-05-12 12:41:18','YYYY-MM-DD HH24:MI:SS'),0,NULL,22,'EE01','N','Y','Y','Y','N','N','N','N','Moving Time',0,560,0,TO_DATE('2012-05-12 12:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64292 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63122,64293,0,53509,TO_DATE('2012-05-12 12:41:19','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Queue time is the time a job waits at a work center before begin handled.',10,'EE01','Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.','N','Y','Y','Y','N','N','N','Y','Queuing Time',0,570,0,TO_DATE('2012-05-12 12:41:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:19 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64293 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63078,64294,0,53509,TO_DATE('2012-05-12 12:41:19','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Simulation Execution Time',22,'EE01','Amount of time the performer of the activity needs to perform the task in Duration Unit','N','Y','Y','Y','N','N','N','N','Working Time',0,580,0,TO_DATE('2012-05-12 12:41:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64294 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63079,64295,0,53509,TO_DATE('2012-05-12 12:41:20','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Workflow Simulation Waiting time',22,'EE01','Amount of time needed to prepare the performance of the task on Duration Units','N','Y','Y','Y','N','N','N','Y','Waiting Time',0,590,0,TO_DATE('2012-05-12 12:41:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:20 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64295 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63124,64296,0,53509,TO_DATE('2012-05-12 12:41:20','YYYY-MM-DD HH24:MI:SS'),0,NULL,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent',22,'EE01','ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities','N','Y','Y','Y','N','N','N','N','Yield %',0,600,0,TO_DATE('2012-05-12 12:41:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64296 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63102,64297,0,53509,TO_DATE('2012-05-12 12:41:21','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Name of the Attribute',20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Identifier of the attribute','N','Y','Y','Y','N','N','N','N','Attribute Name',0,610,0,TO_DATE('2012-05-12 12:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:21 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64297 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63103,64298,0,53509,TO_DATE('2012-05-12 12:41:21','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Value of the Attribute',20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Adempiere converts the (string) field values to the attribute data type.  Booleans (Yes-No) may have the values "true" and "false", the date format is YYYY-MM-DD','N','Y','Y','Y','N','N','N','Y','Attribute Value',0,620,0,TO_DATE('2012-05-12 12:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64298 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63101,64299,0,53509,TO_DATE('2012-05-12 12:41:22','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Name of the column in the database',20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Column Name indicates the name of a column on a table as defined in the database.','N','Y','Y','Y','N','N','N','N','DB Column Name',0,630,0,TO_DATE('2012-05-12 12:41:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:22 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64299 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63100,64300,0,53509,TO_DATE('2012-05-12 12:41:22','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Column in the table',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','Link to the database column of the table','N','Y','Y','Y','N','N','N','Y','Column',0,640,0,TO_DATE('2012-05-12 12:41:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64300 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63105,64301,0,53509,TO_DATE('2012-05-12 12:41:23','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Electronic Mail Address',20,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Email Address is the Electronic Mail ID for this User and should be fully qualified (e.g. joe.smith@company.com). The Email Address is used to access the self service application functionality from the web.','N','Y','Y','Y','N','N','N','N','EMail Address',0,650,0,TO_DATE('2012-05-12 12:41:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:23 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64301 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63104,64302,0,53509,TO_DATE('2012-05-12 12:41:23','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Recipient of the EMail',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','N','Y','Y','Y','N','N','N','Y','EMail Recipient',0,660,0,TO_DATE('2012-05-12 12:41:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64302 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63106,64303,0,53509,TO_DATE('2012-05-12 12:41:24','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Text templates for mailings',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Mail Template indicates the mail template for return messages. Mail text can include variables.  The priority of parsing is User/Contact, Business Partner and then the underlying business object (like Request, Dunning, Workflow object).<br>
So, @Name@ would resolve into the User name (if user is defined defined), then Business Partner name (if business partner is defined) and then the Name of the business object if it has a Name.<br>
For Multi-Lingual systems, the template is translated based on the Business Partner''s language selection.','N','Y','Y','Y','N','N','N','N','Mail Template',0,670,0,TO_DATE('2012-05-12 12:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:24 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64303 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63107,64304,0,53509,TO_DATE('2012-05-12 12:41:24','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Operation System Task',22,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Task field identifies a Operation System Task in the system.','N','Y','Y','Y','N','N','N','Y','OS Task',0,680,0,TO_DATE('2012-05-12 12:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64304 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63109,64305,0,53509,TO_DATE('2012-05-12 12:41:25','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Process or Report',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Process field identifies a unique Process or Report in the system.','N','Y','Y','Y','N','N','N','N','Process',0,690,0,TO_DATE('2012-05-12 12:41:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:25 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64305 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63099,64306,0,53509,TO_DATE('2012-05-12 12:41:25','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Special Form',10,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','The Special Form field identifies a unique Special Form in the system.','N','Y','Y','Y','N','N','N','Y','Special Form',0,700,0,TO_DATE('2012-05-12 12:41:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64306 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63108,64307,0,53509,TO_DATE('2012-05-12 12:41:26','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Mode how the sub-workflow is executed',1,'@WorkflowType@=''G'' | @WorkflowType@=''P'' | @WorkflowType@=''V'' | @WorkflowType@=''''','EE01','N','Y','Y','Y','N','N','N','N','Subflow Execution',0,710,0,TO_DATE('2012-05-12 12:41:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:26 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64307 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63098,64308,0,53509,TO_DATE('2012-05-12 12:41:26','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Data entry or display window',10,'EE01','The Window field identifies a unique Window in the system.','N','Y','Y','Y','N','N','N','N','Window',0,720,0,TO_DATE('2012-05-12 12:41:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64308 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63056,64309,0,53509,TO_DATE('2012-05-12 12:41:27','YYYY-MM-DD HH24:MI:SS'),0,NULL,1,'EE01','N','Y','N','Y','N','N','N','N','Import Workflow',0,730,0,TO_DATE('2012-05-12 12:41:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:27 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64309 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63055,64310,0,53509,TO_DATE('2012-05-12 12:41:27','YYYY-MM-DD HH24:MI:SS'),0,NULL,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','N','Y','N','Y','N','N','Y','Y','Processed',0,740,0,TO_DATE('2012-05-12 12:41:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64310 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 12:41:28 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat (AD_Client_ID,AD_ImpFormat_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,Description,FormatType,IsActive,Name,Processing,Updated,UpdatedBy) VALUES (0,50015,0,53380,TO_DATE('2012-05-12 12:41:28','YYYY-MM-DD HH24:MI:SS'),0,'Format for importing workflows standard and manufacturing ','C','Y','Import Workflow','N',TO_DATE('2012-05-12 12:41:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:29 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63057,50015,50240,0,NULL,NULL,TO_DATE('2012-05-12 12:41:28','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Organization Name',NULL,10,1,TO_DATE('2012-05-12 12:41:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:29 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63058,50015,50241,0,NULL,NULL,TO_DATE('2012-05-12 12:41:29','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Wrokflow Search Key',NULL,20,2,TO_DATE('2012-05-12 12:41:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63059,50015,50242,0,NULL,NULL,TO_DATE('2012-05-12 12:41:29','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Workflow Name',NULL,30,3,TO_DATE('2012-05-12 12:41:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:30 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63062,50015,50243,0,NULL,NULL,TO_DATE('2012-05-12 12:41:30','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Workflow Type',NULL,40,4,TO_DATE('2012-05-12 12:41:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:31 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63068,50015,50244,0,NULL,NULL,TO_DATE('2012-05-12 12:41:30','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Plublication Status',NULL,50,5,TO_DATE('2012-05-12 12:41:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:31 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63112,50015,50245,0,NULL,NULL,TO_DATE('2012-05-12 12:41:31','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Document No',NULL,60,6,TO_DATE('2012-05-12 12:41:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:32 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63069,50015,50246,0,NULL,NULL,TO_DATE('2012-05-12 12:41:31','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Verstion',NULL,70,7,TO_DATE('2012-05-12 12:41:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:32 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63070,50015,50247,0,NULL,NULL,TO_DATE('2012-05-12 12:41:32','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Author',NULL,80,8,TO_DATE('2012-05-12 12:41:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:33 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63118,50015,50248,0,NULL,NULL,TO_DATE('2012-05-12 12:41:32','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Process Type',NULL,90,9,TO_DATE('2012-05-12 12:41:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:33 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63117,50015,50249,0,NULL,NULL,TO_DATE('2012-05-12 12:41:33','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Batch Size',NULL,100,10,TO_DATE('2012-05-12 12:41:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63074,50015,50250,0,NULL,NULL,TO_DATE('2012-05-12 12:41:33','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Duration Unit',NULL,110,11,TO_DATE('2012-05-12 12:41:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:34 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63084,50015,50251,0,NULL,NULL,TO_DATE('2012-05-12 12:41:34','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Node Key',NULL,120,12,TO_DATE('2012-05-12 12:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:35 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63087,50015,50252,0,NULL,NULL,TO_DATE('2012-05-12 12:41:34','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Node Name',NULL,140,14,TO_DATE('2012-05-12 12:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:35 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63060,50015,50253,0,NULL,NULL,TO_DATE('2012-05-12 12:41:35','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Node Description',NULL,150,15,TO_DATE('2012-05-12 12:41:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63119,50015,50254,0,NULL,NULL,TO_DATE('2012-05-12 12:41:35','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Node Help',NULL,160,16,TO_DATE('2012-05-12 12:41:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:36 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63114,50015,50255,0,NULL,NULL,TO_DATE('2012-05-12 12:41:36','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Overlap Units',NULL,180,18,TO_DATE('2012-05-12 12:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:37 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63113,50015,50256,0,NULL,NULL,TO_DATE('2012-05-12 12:41:36','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Units Cycles',NULL,190,19,TO_DATE('2012-05-12 12:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:37 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63115,50015,50257,0,NULL,NULL,TO_DATE('2012-05-12 12:41:37','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Is Milestone',NULL,200,20,TO_DATE('2012-05-12 12:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63116,50015,50258,0,NULL,NULL,TO_DATE('2012-05-12 12:41:37','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Is Subcontract',NULL,210,21,TO_DATE('2012-05-12 12:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:38 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63066,50015,50259,0,NULL,NULL,TO_DATE('2012-05-12 12:41:38','YYYY-MM-DD HH24:MI:SS'),0,'DDMMYYYY','D','.','N',0,'Y','Valid From',NULL,220,22,TO_DATE('2012-05-12 12:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63067,50015,50260,0,NULL,NULL,TO_DATE('2012-05-12 12:41:38','YYYY-MM-DD HH24:MI:SS'),0,'DDMMYYYY','D','.','N',0,'Y','Valid To ',NULL,230,23,TO_DATE('2012-05-12 12:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:39 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63065,50015,50261,0,NULL,NULL,TO_DATE('2012-05-12 12:41:39','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Priority',NULL,240,24,TO_DATE('2012-05-12 12:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63123,50015,50262,0,NULL,NULL,TO_DATE('2012-05-12 12:41:39','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Setup Time',NULL,250,25,TO_DATE('2012-05-12 12:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:40 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63076,50015,50263,0,NULL,NULL,TO_DATE('2012-05-12 12:41:40','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Duration',NULL,260,26,TO_DATE('2012-05-12 12:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63079,50015,50264,0,NULL,NULL,TO_DATE('2012-05-12 12:41:40','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Waiting Time',NULL,270,27,TO_DATE('2012-05-12 12:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:41 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63121,50015,50265,0,NULL,NULL,TO_DATE('2012-05-12 12:41:41','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Moving Time',NULL,280,28,TO_DATE('2012-05-12 12:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:42 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63122,50015,50266,0,NULL,NULL,TO_DATE('2012-05-12 12:41:41','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','Queuing Time ',NULL,290,29,TO_DATE('2012-05-12 12:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63124,50015,50267,0,NULL,NULL,TO_DATE('2012-05-12 12:41:42','YYYY-MM-DD HH24:MI:SS'),0,NULL,'N','.','N',0,'Y','% Yield',NULL,300,30,TO_DATE('2012-05-12 12:41:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:43 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63082,50015,50268,0,NULL,NULL,TO_DATE('2012-05-12 12:41:43','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Entity Type',NULL,310,31,TO_DATE('2012-05-12 12:41:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63125,50015,50269,0,NULL,NULL,TO_DATE('2012-05-12 12:41:43','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Resource Value',NULL,170,17,TO_DATE('2012-05-12 12:41:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:44 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63126,50015,50270,0,NULL,NULL,TO_DATE('2012-05-12 12:41:44','YYYY-MM-DD HH24:MI:SS'),0,NULL,'S','.','N',0,'Y','Node Next Key',NULL,130,13,TO_DATE('2012-05-12 12:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53415,0,53175,'W',TO_DATE('2012-05-12 12:41:44','YYYY-MM-DD HH24:MI:SS'),0,'Import Workflow','EE01','Y','N','N','N','Import Workflow',TO_DATE('2012-05-12 12:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 12:41:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53415 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- May 12, 2012 12:41:45 PM CDT
-- Import Workflow http://adempiere.atlassian.net/browse/MFG-12
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 163,6, 10, 53415)
;


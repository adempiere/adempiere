-- [FR 1834749 ] Control applied migration scripts
CREATE TABLE ad_migrationscript ( 
    ad_client_id         	NUMBER(10) NOT NULL,
    ad_migrationscript_id	NUMBER(10) NOT NULL,
    ad_org_id            	NUMBER(10) NOT NULL,
    created              	DATE NOT NULL,
    createdby            	NUMBER(10) NOT NULL,
    description          	VARCHAR2(2000) NULL,
    developername        	VARCHAR2(60) NULL,
    isactive             	CHAR(1) NOT NULL,
    NAME                 	VARCHAR2(60) NOT NULL,
    projectname          	VARCHAR2(60) NOT NULL,
    reference            	VARCHAR2(2000) NULL,
    releaseno            	VARCHAR2(4) NOT NULL,
    scriptroll           	CHAR(1) NULL,
    status               	CHAR(2) NOT NULL,
    url                  	VARCHAR2(2000) NULL,
    updated              	DATE NOT NULL,
    updatedby            	NUMBER(10) NOT NULL,
    isapply              	CHAR(1) NOT NULL,
    CHECK (IsActive IN ('Y','N')),
    CONSTRAINT ad_migrationscript_KEY PRIMARY KEY(ad_migrationscript_id)
);

ALTER TABLE ad_migrationscript
    ADD CONSTRAINT ad_migrationscript_isapply_chk
	CHECK (IsApply IN ('Y','N'));
	
ALTER TABLE ad_migrationscript
    ADD CONSTRAINT ad_migrationscript_isact_chk
	CHECK (IsActive IN ('Y','N'));

-- 15/02/2008 14h57min19s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53350,'AD_MigrationScript_ID',TO_DATE('2008-02-15 14:57:10','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Migration Script','Table to check wether the migration script has been applied',TO_DATE('2008-02-15 14:57:10','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 14h57min19s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53350 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 15/02/2008 14h58min2s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53351,'DeveloperName',TO_DATE('2008-02-15 14:57:57','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Developer Name','Developer Name',TO_DATE('2008-02-15 14:57:57','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 14h58min2s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53351 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 15/02/2008 14h59min12s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53352,'isApply',TO_DATE('2008-02-15 14:59:10','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Apply Script','Apply Script',TO_DATE('2008-02-15 14:59:10','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 14h59min12s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53352 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 15/02/2008 15h0min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53353,'ScriptRoll',TO_DATE('2008-02-15 15:00:34','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Roll the Script','Roll the Script',TO_DATE('2008-02-15 15:00:34','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h0min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53353 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 15/02/2008 15h1min44s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TABLE (AD_Org_ID,AD_Client_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,NAME,ReplicationType,TableName,Updated,AccessLevel,UpdatedBy) VALUES (0,0,53064,'N',TO_DATE('2008-02-15 15:01:41','YYYY-MM-DD HH24:MI:SS'),100,'Migration Scripts for the System','A','N','Y','N','Y','N','N','N',0,'Migration Script','L','AD_MigrationScript',TO_DATE('2008-02-15 15:01:41','YYYY-MM-DD HH24:MI:SS'),'4',100)
;

-- 15/02/2008 15h1min44s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53064 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- 15/02/2008 15h1min47s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_SEQUENCE (AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy,AD_Client_ID) VALUES (0,53081,TO_DATE('2008-02-15 15:01:44','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table AD_MigrationScript',1,'Y','N','Y','Y','AD_MigrationScript','N',1000000,TO_DATE('2008-02-15 15:01:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 15/02/2008 15h2min44s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,53350,13,53064,'AD_MigrationScript_ID',TO_DATE('2008-02-15 15:02:38','YYYY-MM-DD HH24:MI:SS'),100,'A',10,'Y','N','N','N','Y','Y','N','N','N','N','Migration Script',TO_DATE('2008-02-15 15:02:38','YYYY-MM-DD HH24:MI:SS'),100,0,0,54360)
;

-- 15/02/2008 15h2min44s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54360 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min46s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,102,19,53064,'AD_Client_ID',TO_DATE('2008-02-15 15:02:44','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','A',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','Client',TO_DATE('2008-02-15 15:02:44','YYYY-MM-DD HH24:MI:SS'),100,0,0,54361)
;

-- 15/02/2008 15h2min46s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54361 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min48s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,113,19,53064,'AD_Org_ID',TO_DATE('2008-02-15 15:02:46','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','A',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','Organization',TO_DATE('2008-02-15 15:02:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,54362)
;

-- 15/02/2008 15h2min48s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54362 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min50s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,348,20,53064,'IsActive',TO_DATE('2008-02-15 15:02:48','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','A',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_DATE('2008-02-15 15:02:48','YYYY-MM-DD HH24:MI:SS'),100,0,0,54363)
;

-- 15/02/2008 15h2min50s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54363 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min53s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,245,16,53064,'Created',TO_DATE('2008-02-15 15:02:50','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','A',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','Created',TO_DATE('2008-02-15 15:02:50','YYYY-MM-DD HH24:MI:SS'),100,0,0,54364)
;

-- 15/02/2008 15h2min53s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54364 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min54s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,110,246,18,53064,'CreatedBy',TO_DATE('2008-02-15 15:02:53','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','A',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','Created By',TO_DATE('2008-02-15 15:02:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,54365)
;

-- 15/02/2008 15h2min54s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54365 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min57s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,607,16,53064,'Updated',TO_DATE('2008-02-15 15:02:54','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','A',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','Updated',TO_DATE('2008-02-15 15:02:54','YYYY-MM-DD HH24:MI:SS'),100,0,0,54366)
;

-- 15/02/2008 15h2min57s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54366 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h2min58s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,110,608,18,53064,'UpdatedBy',TO_DATE('2008-02-15 15:02:57','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','A',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','Updated By',TO_DATE('2008-02-15 15:02:57','YYYY-MM-DD HH24:MI:SS'),100,0,0,54367)
;

-- 15/02/2008 15h2min58s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54367 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min0s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,SeqNo,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,469,10,53064,'Name',TO_DATE('2008-02-15 15:02:58','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','A',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','Y','Name',1,TO_DATE('2008-02-15 15:02:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,54368)
;

-- 15/02/2008 15h3min0s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54368 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min2s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,275,14,53064,'Description',TO_DATE('2008-02-15 15:03:00','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','A',2000,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_DATE('2008-02-15 15:03:00','YYYY-MM-DD HH24:MI:SS'),100,0,0,54369)
;

-- 15/02/2008 15h3min2s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54369 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min3s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,2161,10,53064,'ProjectName',TO_DATE('2008-02-15 15:03:02','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Project','A',60,'Y','N','N','N','N','Y','N','N','N','Y','Project',TO_DATE('2008-02-15 15:03:02','YYYY-MM-DD HH24:MI:SS'),100,0,0,54370)
;

-- 15/02/2008 15h3min3s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54370 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min5s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,2122,10,53064,'ReleaseNo',TO_DATE('2008-02-15 15:03:03','YYYY-MM-DD HH24:MI:SS'),100,'Internal Release Number','A',10,'Y','N','N','N','N','Y','N','N','N','Y','Release No',TO_DATE('2008-02-15 15:03:03','YYYY-MM-DD HH24:MI:SS'),100,0,0,54371)
;

-- 15/02/2008 15h3min5s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54371 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min8s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,53351,10,53064,'DeveloperName',TO_DATE('2008-02-15 15:03:05','YYYY-MM-DD HH24:MI:SS'),100,'A',60,'Y','N','N','N','N','Y','N','N','N','Y','Developer Name',TO_DATE('2008-02-15 15:03:05','YYYY-MM-DD HH24:MI:SS'),100,0,0,54372)
;

-- 15/02/2008 15h3min8s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54372 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min9s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,539,14,53064,'Reference',TO_DATE('2008-02-15 15:03:08','YYYY-MM-DD HH24:MI:SS'),100,'Reference for this record','A',2000,'The Reference displays the source document number.','Y','N','N','N','N','N','N','N','N','Y','Reference',TO_DATE('2008-02-15 15:03:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,54373)
;

-- 15/02/2008 15h3min9s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54373 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min11s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,983,14,53064,'URL',TO_DATE('2008-02-15 15:03:10','YYYY-MM-DD HH24:MI:SS'),100,'Full URL address - e.g. http://www.adempiere.org','A',2000,'The URL defines an fully qualified web address like http://www.adempiere.org','Y','N','N','N','N','N','N','N','N','Y','URL',TO_DATE('2008-02-15 15:03:10','YYYY-MM-DD HH24:MI:SS'),100,0,0,54374)
;

-- 15/02/2008 15h3min11s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54374 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min13s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,53352,20,53064,'isApply',TO_DATE('2008-02-15 15:03:11','YYYY-MM-DD HH24:MI:SS'),100,'A',1,'Y','N','N','N','N','Y','N','N','N','Y','Apply Script',TO_DATE('2008-02-15 15:03:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,54375)
;

-- 15/02/2008 15h3min13s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54375 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min15s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,3020,17,53064,'Status',TO_DATE('2008-02-15 15:03:13','YYYY-MM-DD HH24:MI:SS'),100,'Status of the currently running check','A',2,'Status of the currently running check','Y','N','N','N','N','Y','N','N','N','Y','Status',TO_DATE('2008-02-15 15:03:13','YYYY-MM-DD HH24:MI:SS'),100,0,0,54376)
;

-- 15/02/2008 15h3min15s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54376 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h3min17s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,53353,20,53064,'ScriptRoll',TO_DATE('2008-02-15 15:03:15','YYYY-MM-DD HH24:MI:SS'),100,'A',1,'Y','N','N','N','N','Y','N','N','N','Y','Roll the Script',TO_DATE('2008-02-15 15:03:15','YYYY-MM-DD HH24:MI:SS'),100,0,0,54377)
;

-- 15/02/2008 15h3min17s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54377 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h5min11s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_Value_ID=50002, AD_Reference_ID=17,Updated=TO_DATE('2008-02-15 15:05:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- 15/02/2008 15h5min11s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Release No', Description='Internal Release Number', Help=NULL WHERE AD_Column_ID=54371 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 15h5min48s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,50002,53324,TO_DATE('2008-02-15 15:05:46','YYYY-MM-DD HH24:MI:SS'),100,'Release 3.3.0','A','Y','Release 3.3.0',TO_DATE('2008-02-15 15:05:46','YYYY-MM-DD HH24:MI:SS'),100,0,'Release 3.3.0')
;

-- 15/02/2008 15h5min49s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53324 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h6min12s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,50002,53325,TO_DATE('2008-02-15 15:06:06','YYYY-MM-DD HH24:MI:SS'),100,'Release 3.3.1','A','Y','Release 3.3.1',TO_DATE('2008-02-15 15:06:06','YYYY-MM-DD HH24:MI:SS'),100,0,'Release 3.3.1')
;

-- 15/02/2008 15h6min12s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53325 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h6min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,50002,53326,TO_DATE('2008-02-15 15:06:36','YYYY-MM-DD HH24:MI:SS'),100,'Release 3.3.2','A','Y','Release 3.3.2',TO_DATE('2008-02-15 15:06:36','YYYY-MM-DD HH24:MI:SS'),100,0,'Release 3.3.2')
;

-- 15/02/2008 15h6min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53326 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h7min21s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST SET Description='Release 3.1.0', NAME='Release 3.1.0', VALUE='Release 3.1.0',Updated=TO_DATE('2008-02-15 15:07:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53324
;

-- 15/02/2008 15h7min22s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST_TRL SET IsTranslated='N' WHERE AD_Ref_List_ID=53324
;

-- 15/02/2008 15h7min30s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST SET Description='Release 3.2.0', NAME='Release 3.2.0', VALUE='Release 3.2.0',Updated=TO_DATE('2008-02-15 15:07:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53325
;

-- 15/02/2008 15h7min30s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST_TRL SET IsTranslated='N' WHERE AD_Ref_List_ID=53325
;

-- 15/02/2008 15h7min39s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST SET Description='Release 3.3.0', NAME='Release 3.3.0', VALUE='Release 3.3.0',Updated=TO_DATE('2008-02-15 15:07:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53326
;

-- 15/02/2008 15h7min39s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_REF_LIST_TRL SET IsTranslated='N' WHERE AD_Ref_List_ID=53326
;

-- 15/02/2008 15h8min3s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET FieldLength=40,Updated=TO_DATE('2008-02-15 15:08:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- 15/02/2008 15h8min4s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Release No', Description='Internal Release Number', Help=NULL WHERE AD_Column_ID=54371 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 15h8min7s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY ReleaseNo CHAR(40) DEFAULT  NULL 
;

-- 15/02/2008 15h8min41s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REFERENCE (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53239,TO_DATE('2008-02-15 15:08:39','YYYY-MM-DD HH24:MI:SS'),100,'Migration Script Status','A','Y','MigrationScriptStatus',TO_DATE('2008-02-15 15:08:39','YYYY-MM-DD HH24:MI:SS'),100,'L',0)
;

-- 15/02/2008 15h8min41s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REFERENCE_TRL (AD_LANGUAGE,AD_Reference_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Reference_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REFERENCE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53239 AND EXISTS (SELECT * FROM AD_REFERENCE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 15/02/2008 15h10min9s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,53239,53327,TO_DATE('2008-02-15 15:10:08','YYYY-MM-DD HH24:MI:SS'),100,'In Progress','A','Y','In Progress',TO_DATE('2008-02-15 15:10:08','YYYY-MM-DD HH24:MI:SS'),100,0,'IP')
;

-- 15/02/2008 15h10min9s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53327 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h10min22s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,53239,53328,TO_DATE('2008-02-15 15:10:21','YYYY-MM-DD HH24:MI:SS'),100,'Completed','A','Y','Completed',TO_DATE('2008-02-15 15:10:21','YYYY-MM-DD HH24:MI:SS'),100,0,'CO')
;

-- 15/02/2008 15h10min22s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53328 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h10min33s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,NAME,Updated,UpdatedBy,AD_Client_ID,VALUE) VALUES (0,53239,53329,TO_DATE('2008-02-15 15:10:32','YYYY-MM-DD HH24:MI:SS'),100,'Error','A','Y','Error',TO_DATE('2008-02-15 15:10:32','YYYY-MM-DD HH24:MI:SS'),100,0,'ER')
;

-- 15/02/2008 15h10min33s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53329 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 15/02/2008 15h10min42s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_Value_ID=53239,Updated=TO_DATE('2008-02-15 15:10:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54376
;

-- 15/02/2008 15h10min43s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Status', Description='Status of the currently running check', Help='Status of the currently running check' WHERE AD_Column_ID=54376 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 15h10min53s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=40,Updated=TO_DATE('2008-02-15 15:10:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54374
;

-- 15/02/2008 15h10min53s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='URL', Description='Full URL address - e.g. http://www.adempiere.org', Help='The URL defines an fully qualified web address like http://www.adempiere.org' WHERE AD_Column_ID=54374 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 15h11min21s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=28,Updated=TO_DATE('2008-02-15 15:11:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54377
;

-- 15/02/2008 15h11min22s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Roll the Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54377 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 15h17min10s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS (AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,NAME,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,VALUE,AccessLevel,AD_Client_ID) VALUES (0,53069,'org.adempiere.process.ApplyMigrationScripts',TO_DATE('2008-02-15 15:17:08','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N','N','N','N','Apply Migration Scripts','Y',0,0,TO_DATE('2008-02-15 15:17:08','YYYY-MM-DD HH24:MI:SS'),100,'ApplyMigrationScripts','4',0)
;

-- 15/02/2008 15h17min10s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_TRL (AD_LANGUAGE,AD_Process_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53069 AND EXISTS (SELECT * FROM AD_PROCESS_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- 15/02/2008 15h17min10s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53069,0,TO_DATE('2008-02-15 15:17:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:17:10','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h17min10s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53069,102,TO_DATE('2008-02-15 15:17:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:17:10','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h17min11s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53069,103,TO_DATE('2008-02-15 15:17:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:17:11','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h17min11s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53069,50001,TO_DATE('2008-02-15 15:17:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:17:11','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h40min25s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,SeqNo,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,2295,39,53064,'FileName',TO_DATE('2008-02-15 15:40:24','YYYY-MM-DD HH24:MI:SS'),100,'Name of the local file or URL','A',500,'Name of a file in the local directory space - or URL (file://.., http://.., ftp://..)','Y','N','N','N','N','Y','N','N','N','N','Y','File Name',0,TO_DATE('2008-02-15 15:40:24','YYYY-MM-DD HH24:MI:SS'),100,0,0,54378)
;

-- 15/02/2008 15h40min25s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54378 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 15h40min28s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript ADD FileName NVARCHAR2(500) NOT NULL
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW (AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,NAME,Processing,Updated,UpdatedBy,WindowType,WinHeight,AD_Client_ID,WinWidth) VALUES (0,53019,TO_DATE('2008-02-15 15:41:58','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N','N','N','Migration Scripts','N',TO_DATE('2008-02-15 15:41:58','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0,0)
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW_TRL (AD_LANGUAGE,AD_Window_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Window_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_WINDOW t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53019 AND EXISTS (SELECT * FROM AD_WINDOW_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW_ACCESS (AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,0,53019,TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW_ACCESS (AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,102,53019,TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW_ACCESS (AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,103,53019,TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h41min59s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_WINDOW_ACCESS (AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,50001,53019,TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:41:59','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h42min9s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_WINDOW_ACCESS SET IsActive='N',Updated=TO_DATE('2008-02-15 15:42:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=102 AND AD_Window_ID=53019
;

-- 15/02/2008 15h42min19s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_WINDOW_ACCESS SET IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:42:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=102 AND AD_Window_ID=53019
;

-- 15/02/2008 15h42min22s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_WINDOW_ACCESS SET IsActive='N', IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:42:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=103 AND AD_Window_ID=53019
;

-- 15/02/2008 15h42min25s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_WINDOW_ACCESS SET IsActive='N', IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:42:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50001 AND AD_Window_ID=53019
;

-- 15/02/2008 15h43min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TAB (AD_Org_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy,AD_Client_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID) VALUES (0,TO_DATE('2008-02-15 15:43:36','YYYY-MM-DD HH24:MI:SS'),100,'A','N','N','Y','N','N','Y','N','N','N','N','Migration Scripts','N',10,0,TO_DATE('2008-02-15 15:43:36','YYYY-MM-DD HH24:MI:SS'),100,0,53073,53064,53019)
;

-- 15/02/2008 15h43min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, Description,Help,NAME,CommitWarning, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.Description,t.Help,t.NAME,t.CommitWarning, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53073 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- 15/02/2008 15h45min27s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54363,0,54404,53073,TO_DATE('2008-02-15 15:45:25','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'A','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2008-02-15 15:45:25','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min27s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54404 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min28s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54375,0,54405,53073,TO_DATE('2008-02-15 15:45:27','YYYY-MM-DD HH24:MI:SS'),100,1,'A','Y','Y','Y','N','N','N','N','N','Apply Script',TO_DATE('2008-02-15 15:45:27','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min28s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54405 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min30s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54361,0,54406,53073,TO_DATE('2008-02-15 15:45:29','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',10,'A','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2008-02-15 15:45:29','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min30s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54406 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min31s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54369,0,54407,53073,TO_DATE('2008-02-15 15:45:30','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',2000,'A','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2008-02-15 15:45:30','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min31s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54407 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min32s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54372,0,54408,53073,TO_DATE('2008-02-15 15:45:31','YYYY-MM-DD HH24:MI:SS'),100,60,'A','Y','Y','Y','N','N','N','N','N','Developer Name',TO_DATE('2008-02-15 15:45:31','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min32s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54408 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min33s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54378,0,54409,53073,TO_DATE('2008-02-15 15:45:32','YYYY-MM-DD HH24:MI:SS'),100,'Name of the local file or URL',500,'A','Name of a file in the local directory space - or URL (file://.., http://.., ftp://..)','Y','Y','Y','N','N','N','N','N','File Name',TO_DATE('2008-02-15 15:45:32','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min33s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54409 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min34s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54360,0,54410,53073,TO_DATE('2008-02-15 15:45:33','YYYY-MM-DD HH24:MI:SS'),100,10,'A','Y','Y','N','N','N','N','N','N','Migration Script',TO_DATE('2008-02-15 15:45:33','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min34s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54410 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min35s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54368,0,54411,53073,TO_DATE('2008-02-15 15:45:34','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'A','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2008-02-15 15:45:34','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min35s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54411 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min36s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54362,0,54412,53073,TO_DATE('2008-02-15 15:45:35','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',10,'A','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2008-02-15 15:45:35','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54412 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54370,0,54413,53073,TO_DATE('2008-02-15 15:45:37','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Project',60,'A','Y','Y','Y','N','N','N','N','N','Project',TO_DATE('2008-02-15 15:45:37','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min38s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54413 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min39s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54373,0,54414,53073,TO_DATE('2008-02-15 15:45:38','YYYY-MM-DD HH24:MI:SS'),100,'Reference for this record',2000,'A','The Reference displays the source document number.','Y','Y','Y','N','N','N','N','N','Reference',TO_DATE('2008-02-15 15:45:38','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min39s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54414 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min40s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54371,0,54415,53073,TO_DATE('2008-02-15 15:45:39','YYYY-MM-DD HH24:MI:SS'),100,'Internal Release Number',40,'A','Y','Y','Y','N','N','N','N','N','Release No',TO_DATE('2008-02-15 15:45:39','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min40s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54415 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min41s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54377,0,54416,53073,TO_DATE('2008-02-15 15:45:40','YYYY-MM-DD HH24:MI:SS'),100,1,'A','Y','Y','Y','N','N','N','N','N','Roll the Script',TO_DATE('2008-02-15 15:45:40','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min41s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54416 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min42s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54376,0,54417,53073,TO_DATE('2008-02-15 15:45:41','YYYY-MM-DD HH24:MI:SS'),100,'Status of the currently running check',2,'A','Status of the currently running check','Y','Y','Y','N','N','N','N','N','Status',TO_DATE('2008-02-15 15:45:41','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min42s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54417 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h45min43s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,AD_Client_ID,UpdatedBy) VALUES (54374,0,54418,53073,TO_DATE('2008-02-15 15:45:42','YYYY-MM-DD HH24:MI:SS'),100,'Full URL address - e.g. http://www.adempiere.org',2000,'A','The URL defines an fully qualified web address like http://www.adempiere.org','Y','Y','Y','N','N','N','N','N','URL',TO_DATE('2008-02-15 15:45:42','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h45min44s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54418 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=54406
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=54412
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=54404
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54411
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54409
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54408
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54405
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54413
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54407
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54414
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54418
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54415
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54417
;

-- 15/02/2008 15h47min20s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54416
;

-- 15/02/2008 15h47min37s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=50,Updated=TO_DATE('2008-02-15 15:47:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54409
;

-- 15/02/2008 15h47min43s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54411
;

-- 15/02/2008 15h47min44s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54409
;

-- 15/02/2008 15h47min46s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54406
;

-- 15/02/2008 15h47min50s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54412
;

-- 15/02/2008 15h47min51s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54408
;

-- 15/02/2008 15h47min55s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:47:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54413
;

-- 15/02/2008 15h48min1s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54407
;

-- 15/02/2008 15h48min2s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:48:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54414
;

-- 15/02/2008 15h48min4s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:48:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54418
;

-- 15/02/2008 15h48min8s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=14,Updated=TO_DATE('2008-02-15 15:48:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54415
;

-- 15/02/2008 15h48min37s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET IsSameLine='Y',Updated=TO_DATE('2008-02-15 15:48:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54412
;

-- 15/02/2008 15h48min47s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET IsSameLine='Y',Updated=TO_DATE('2008-02-15 15:48:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54408
;

-- 15/02/2008 15h48min51s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET IsSameLine='Y',Updated=TO_DATE('2008-02-15 15:48:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54418
;

-- 15/02/2008 15h48min53s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET IsSameLine='Y',Updated=TO_DATE('2008-02-15 15:48:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54417
;

-- 15/02/2008 15h49min23s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_MENU (AD_Org_ID,AD_Client_ID,AD_Menu_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,Action,UpdatedBy) VALUES (0,0,53089,53019,TO_DATE('2008-02-15 15:49:21','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N','N','N','Migration Scripts',TO_DATE('2008-02-15 15:49:21','YYYY-MM-DD HH24:MI:SS'),'W',100)
;

-- 15/02/2008 15h49min23s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_MENU_TRL (AD_LANGUAGE,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MENU t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53089 AND EXISTS (SELECT * FROM AD_MENU_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 15/02/2008 15h49min23s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TREENODEMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SYSDATE, 0, SYSDATE, 0,t.AD_Tree_ID, 53089, 0, 999 FROM AD_TREE t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TREENODEMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53089)
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=1000000
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=586
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=138
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=139
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=249
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=141
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=589
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=216
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=140
;

-- 15/02/2008 15h49min33s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=142
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53012
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=143
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=11, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=201
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=12, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=176
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=13, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53086
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=14, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=239
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=15, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=517
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=16, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=499
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=17, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53089
;

-- 15/02/2008 15h49min34s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=18, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=50001
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS (AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,NAME,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,VALUE,AccessLevel,AD_Client_ID) VALUES (0,53070,'org.adempiere.process.PrepareMigrationScripts',TO_DATE('2008-02-15 15:50:36','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N','N','N','N','Prepare Migration Scripts','Y',0,0,TO_DATE('2008-02-15 15:50:36','YYYY-MM-DD HH24:MI:SS'),100,'PrepareMigrationScripts','4',0)
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_TRL (AD_LANGUAGE,AD_Process_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53070 AND EXISTS (SELECT * FROM AD_PROCESS_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53070,0,TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53070,102,TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53070,103,TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h50min37s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_ACCESS (AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53070,50001,TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-15 15:50:37','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 15/02/2008 15h52min26s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_PARA (AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,NAME,SeqNo,Updated,UpdatedBy,AD_Client_ID,AD_Element_ID) VALUES (0,53070,53131,38,'ScriptsPath',TO_DATE('2008-02-15 15:52:24','YYYY-MM-DD HH24:MI:SS'),100,'A',500,'Y','Y','Y','N','Scripts Path',10,TO_DATE('2008-02-15 15:52:24','YYYY-MM-DD HH24:MI:SS'),100,0,50022)
;

-- 15/02/2008 15h52min26s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_PROCESS_PARA_TRL (AD_LANGUAGE,AD_Process_Para_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_Para_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS_PARA t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53131 AND EXISTS (SELECT * FROM AD_PROCESS_PARA_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 15/02/2008 15h52min37s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_PROCESS_ACCESS SET IsActive='N', IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:52:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53070 AND AD_Role_ID=102
;

-- 15/02/2008 15h52min39s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_PROCESS_ACCESS SET IsActive='N', IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:52:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53070 AND AD_Role_ID=103
;

-- 15/02/2008 15h52min42s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_PROCESS_ACCESS SET IsActive='N', IsReadWrite='N',Updated=TO_DATE('2008-02-15 15:52:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53070 AND AD_Role_ID=50001
;

-- 15/02/2008 15h53min13s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_MENU (AD_Org_ID,AD_Client_ID,AD_Menu_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,Action,UpdatedBy) VALUES (0,0,53090,53070,TO_DATE('2008-02-15 15:53:12','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N','N','N','Prepare Migration Scripts',TO_DATE('2008-02-15 15:53:12','YYYY-MM-DD HH24:MI:SS'),'P',100)
;

-- 15/02/2008 15h53min13s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_MENU_TRL (AD_LANGUAGE,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MENU t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53090 AND EXISTS (SELECT * FROM AD_MENU_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 15/02/2008 15h53min13s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_TREENODEMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SYSDATE, 0, SYSDATE, 0,t.AD_Tree_ID, 53090, 0, 999 FROM AD_TREE t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TREENODEMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53090)
;

-- 15/02/2008 15h53min15s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 15/02/2008 15h53min15s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=0, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=1000000
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=586
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=138
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=139
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=249
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=141
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=589
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=216
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=140
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=142
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53012
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=143
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=11, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=201
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=12, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=176
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=13, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53086
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=14, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=239
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=15, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=517
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=16, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=499
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=17, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53089
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=18, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53090
;

-- 15/02/2008 15h53min16s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_TREENODEMM SET Parent_ID=153, SeqNo=19, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=50001
;











-- 15/02/2008 17h46min17s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-02-15 17:46:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54372
;

-- 15/02/2008 17h46min17s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Developer Name', Description=NULL, Help=NULL WHERE AD_Column_ID=54372 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 17h46min21s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY DeveloperName NVARCHAR2(60) DEFAULT  NULL 
;






-- 15/02/2008 17h46min36s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-02-15 17:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54375
;

-- 15/02/2008 17h46min37s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Apply Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54375 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 17h46min38s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;

-- 15/02/2008 17h46min38s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply NULL
;

-- 15/02/2008 17h51min7s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-02-15 17:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- 15/02/2008 17h51min7s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Release No', Description='Internal Release Number', Help=NULL WHERE AD_Column_ID=54371 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 17h52min47s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=36, FieldLength=0, IsUpdateable='N',Updated=TO_DATE('2008-02-15 17:52:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54360
;

-- 15/02/2008 17h52min47s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Migration Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54360 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 17h52min57s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=13, FieldLength=10, IsUpdateable='N',Updated=TO_DATE('2008-02-15 17:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54360
;

-- 15/02/2008 17h52min57s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Migration Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54360 AND IsCentrallyMaintained='Y'
;

-- 15/02/2008 17h56min31s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,SeqNo,Updated,UpdatedBy,VERSION,AD_Client_ID,AD_Column_ID) VALUES (0,1718,23,53064,'Script',TO_DATE('2008-02-15 17:56:26','YYYY-MM-DD HH24:MI:SS'),100,'Dynamic Java Language Script to calculate result','A',0,'Use Java language constructs to define the result of the calculation','Y','N','N','N','N','N','N','N','N','N','N','Script',0,TO_DATE('2008-02-15 17:56:26','YYYY-MM-DD HH24:MI:SS'),100,0,0,54379)
;

-- 15/02/2008 17h56min31s PYST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54379 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 15/02/2008 17h56min37s PYST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript ADD Script BLOB
;

-- 15/02/2008 17h56min45s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET FieldLength=0,Updated=TO_DATE('2008-02-15 17:56:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54379
;

-- 15/02/2008 17h56min45s PYST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Script', Description='Dynamic Java Language Script to calculate result', Help='Use Java language constructs to define the result of the calculation' WHERE AD_Column_ID=54379 AND IsCentrallyMaintained='Y'
;

-- 18/02/2008 11h12min22s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsMandatory='Y', FieldLength=20,Updated=TO_DATE('2008-02-18 11:12:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- 18/02/2008 11h12min22s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Release No', Description='Internal Release Number', Help=NULL WHERE AD_Column_ID=54371 AND IsCentrallyMaintained='Y'
;

-- 18/02/2008 11h12min24s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY ReleaseNo CHAR(20) DEFAULT  NULL 
;






-- 18/02/2008 11h12min47s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY ReleaseNo CHAR(20) DEFAULT  NULL 
;






-- 18/02/2008 11h13min44s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY IsActive CHAR(1) DEFAULT  NULL 
;

-- 18/02/2008 11h14min27s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsUpdateable='N',Updated=TO_DATE('2008-02-18 11:14:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54376
;

-- 18/02/2008 11h14min27s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Status', Description='Status of the currently running check', Help='Status of the currently running check' WHERE AD_Column_ID=54376 AND IsCentrallyMaintained='Y'
;

-- 18/02/2008 11h14min29s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY Status CHAR(2) DEFAULT  NULL 
;

-- 18/02/2008 11h14min50s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;

-- 18/02/2008 11h16min1s BRST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,NAME,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,100,'Y','Y','Y',TO_DATE('2008-02-18 11:15:59','YYYY-MM-DD HH24:MI:SS'),0,54419,'Dynamic Java Language Script to calculate result',0,54379,'N',100,'Use Java language constructs to define the result of the calculation',TO_DATE('2008-02-18 11:15:59','YYYY-MM-DD HH24:MI:SS'),'Script',53073,'N','N','N','A')
;

-- 18/02/2008 11h16min2s BRST
-- [FR 1834749 ] Control applied migration scripts
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54419 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18/02/2008 11h16min32s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=35,Updated=TO_DATE('2008-02-18 11:16:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54419
;

-- 18/02/2008 11h17min4s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET DisplayLength=30,Updated=TO_DATE('2008-02-18 11:17:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54419
;

-- 18/02/2008 11h18min50s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=28,Updated=TO_DATE('2008-02-18 11:18:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54375
;

-- 18/02/2008 11h18min50s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Apply Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54375 AND IsCentrallyMaintained='Y'
;

-- 18/02/2008 11h18min52s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;

-- 18/02/2008 11h19min1s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_ID=20,Updated=TO_DATE('2008-02-18 11:19:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54375
;

-- 18/02/2008 11h19min1s BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Apply Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54375 AND IsCentrallyMaintained='Y'
;

-- 18/02/2008 11h19min3s BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;

-- Feb 18, 2008 11:32:12 AM BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET AD_Reference_Value_ID=NULL, FieldLength=4, AD_Reference_ID=14,Updated=TO_DATE('2008-02-18 11:32:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54371
;

-- Feb 18, 2008 11:32:12 AM BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Release No', Description='Internal Release Number', Help=NULL WHERE AD_Column_ID=54371 AND IsCentrallyMaintained='Y'
;

-- Feb 18, 2008 11:32:29 AM BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY ReleaseNo NVARCHAR2(4) DEFAULT  NULL 
;

-- Feb 18, 2008 11:32:48 AM BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY ReleaseNo NVARCHAR2(4) DEFAULT  NULL 
;




















-- Feb 18, 2008 11:34:43 AM BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2008-02-18 11:34:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54375
;

-- Feb 18, 2008 11:34:43 AM BRST
-- [FR 1834749 ] Control applied migration scripts
UPDATE AD_FIELD SET NAME='Apply Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54375 AND IsCentrallyMaintained='Y'
;

-- Feb 18, 2008 11:34:45 AM BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;

-- Feb 18, 2008 11:34:46 AM BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply NOT NULL
;

-- Feb 18, 2008 11:34:57 AM BRST
-- [FR 1834749 ] Control applied migration scripts
ALTER TABLE AD_MigrationScript MODIFY isApply CHAR(1) DEFAULT  NULL 
;






-- Feb 18, 2008 3:04:58 PM BRST
-- Default comment for updating dictionary
UPDATE AD_COLUMN SET IsMandatory='N',Updated=TO_DATE('2008-02-18 15:04:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54377
;

-- Feb 18, 2008 3:04:58 PM BRST
-- Default comment for updating dictionary
UPDATE AD_FIELD SET NAME='Roll the Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54377 AND IsCentrallyMaintained='Y'
;

-- Feb 18, 2008 3:05:00 PM BRST
-- Default comment for updating dictionary
ALTER TABLE AD_MigrationScript MODIFY ScriptRoll CHAR(1) DEFAULT  NULL
;

-- Feb 18, 2008 3:07:50 PM BRST
-- Default comment for updating dictionary
UPDATE AD_COLUMN SET AD_Process_ID=53069,Updated=TO_DATE('2008-02-18 15:07:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54377
;

-- Feb 18, 2008 3:07:50 PM BRST
-- Default comment for updating dictionary
UPDATE AD_FIELD SET NAME='Roll the Script', Description=NULL, Help=NULL WHERE AD_Column_ID=54377 AND IsCentrallyMaintained='Y'
;

-- Feb 18, 2008 3:07:52 PM BRST
-- Default comment for updating dictionary
ALTER TABLE AD_MigrationScript MODIFY ScriptRoll CHAR(1) DEFAULT  NULL
;

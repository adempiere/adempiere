-- Apr 24, 2014 7:04:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsCentrallyMaintained,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('3',0,0,53618,'N',TO_TIMESTAMP('2013-10-11 16:23:56','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','Y','N','Y','N','N','N',0,'I_Budget','L','I_Budget',TO_TIMESTAMP('2013-10-11 16:23:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:04:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53618 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Apr 24, 2014 7:04:54 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53763,TO_TIMESTAMP('2013-10-11 16:23:59','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table I_Budget',1,'Y','N','Y','Y','I_Budget','N',1000000,TO_TIMESTAMP('2013-10-11 16:23:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:04:56 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68682,1884,0,19,53618,'A_Asset_ID',TO_TIMESTAMP('2013-10-11 16:24:36','YYYY-MM-DD HH24:MI:SS'),100,'Asset used internally or by customers','D',10,'An asset is either created by purchasing or by delivering a product.  An asset can be used internally or be a customer asset.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Asset',0,TO_TIMESTAMP('2013-10-11 16:24:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:04:56 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68682 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:04:57 PM GMT05:30
-- Import Budget AD Chages
CREATE TABLE I_Budget (A_Asset_ID NUMERIC(10) DEFAULT NULL )
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68683,148,0,11,362,53618,'Account_ID',TO_TIMESTAMP('2013-10-11 16:25:14','YYYY-MM-DD HH24:MI:SS'),100,'Account used','D',14,'The (natural) account used','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Account',0,TO_TIMESTAMP('2013-10-11 16:25:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68683 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Account_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68684,2083,0,10,53618,'AccountValue',TO_TIMESTAMP('2013-10-11 16:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Key of Account Element','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Account Key',0,TO_TIMESTAMP('2013-10-11 16:26:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68684 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:04:59 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AccountValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68685,2084,0,10,53618,'AcctSchemaName',TO_TIMESTAMP('2013-10-11 16:26:38','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Accounting Schema','D',40,'Y','Y','N','N','N','N','N','N','N','Y','N','N','Y','Account Schema Name',0,TO_TIMESTAMP('2013-10-11 16:26:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68685 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:00 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AcctSchemaName VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:01 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68686,102,0,19,53618,129,'AD_Client_ID',TO_TIMESTAMP('2013-10-11 16:27:35','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Client',0,TO_TIMESTAMP('2013-10-11 16:27:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:01 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68686 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:01 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AD_Client_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68687,113,0,19,53618,104,'AD_Org_ID',TO_TIMESTAMP('2013-10-11 16:28:33','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Organization',0,TO_TIMESTAMP('2013-10-11 16:28:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68687 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AD_Org_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68688,112,0,18,130,53618,'AD_OrgTrx_ID',TO_TIMESTAMP('2013-10-11 16:29:54','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization','D',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Trx Organization',0,TO_TIMESTAMP('2013-10-11 16:29:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68688 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:02 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AD_OrgTrx_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:03 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56460,0,'AssetValue',TO_TIMESTAMP('2013-10-11 16:30:42','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Asset Value','Asset Value',TO_TIMESTAMP('2013-10-11 16:30:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:03 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56460 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:03 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68689,56460,0,10,53618,'AssetValue',TO_TIMESTAMP('2013-10-11 16:30:57','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Asset Value',0,TO_TIMESTAMP('2013-10-11 16:30:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:03 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68689 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:03 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN AssetValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68690,2093,0,10,53618,'BatchDocumentNo',TO_TIMESTAMP('2013-10-11 16:31:55','YYYY-MM-DD HH24:MI:SS'),100,'Document Number of the Batch','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Batch Document No',0,TO_TIMESTAMP('2013-10-11 16:31:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68690 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:04 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN BatchDocumentNo VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68691,2094,0,10,53618,'BPartnerValue',TO_TIMESTAMP('2013-10-11 16:32:30','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Business Partner','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner Key',0,TO_TIMESTAMP('2013-10-11 16:32:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68691 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:05 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN BPartnerValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56461,0,'BudgetCode',TO_TIMESTAMP('2013-10-11 16:32:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Budget Code ','Budget Code',TO_TIMESTAMP('2013-10-11 16:32:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56461 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68692,56461,0,10,53618,'BudgetCode',TO_TIMESTAMP('2013-10-11 16:33:19','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Budget Code ',0,TO_TIMESTAMP('2013-10-11 16:33:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68692 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN BudgetCode VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68693,181,0,19,53618,'C_AcctSchema_ID',TO_TIMESTAMP('2013-10-11 16:33:54','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting','D',10,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accounting Schema',0,TO_TIMESTAMP('2013-10-11 16:33:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68693 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:06 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_AcctSchema_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68694,1005,0,30,142,53618,'C_Activity_ID',TO_TIMESTAMP('2013-10-11 16:34:24','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','D',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Activity',0,TO_TIMESTAMP('2013-10-11 16:34:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68694 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_Activity_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68695,187,0,30,53618,'C_BPartner_ID',TO_TIMESTAMP('2013-10-11 16:34:47','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner ',0,TO_TIMESTAMP('2013-10-11 16:34:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68695 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:07 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68696,550,0,30,143,53618,'C_Campaign_ID',TO_TIMESTAMP('2013-10-11 16:35:31','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','D',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_TIMESTAMP('2013-10-11 16:35:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68696 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:08 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_Campaign_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68697,200,0,18,133,53618,'C_LocFrom_ID',TO_TIMESTAMP('2013-10-11 16:36:03','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from','D',10,'The Location From indicates the location that a product was moved from.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location From',0,TO_TIMESTAMP('2013-10-11 16:36:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68697 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:09 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_LocFrom_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68698,201,0,18,133,53618,'C_LocTo_ID',TO_TIMESTAMP('2013-10-11 16:36:30','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to','D',10,'The Location To indicates the location that a product was moved to.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location To',0,TO_TIMESTAMP('2013-10-11 16:36:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68698 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:10 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_LocTo_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68699,208,0,19,53618,'C_Project_ID',TO_TIMESTAMP('2013-10-11 16:36:58','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','D',10,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_TIMESTAMP('2013-10-11 16:36:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68699 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_Project_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68700,210,0,19,53618,'C_SalesRegion_ID',TO_TIMESTAMP('2013-10-11 16:37:17','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region','D',10,'The Sales Region indicates a specific area of sales coverage.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sales Region',0,TO_TIMESTAMP('2013-10-11 16:37:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68700 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:11 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_SalesRegion_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68701,222,0,19,53618,'C_ValidCombination_ID',TO_TIMESTAMP('2013-10-11 16:38:14','YYYY-MM-DD HH24:MI:SS'),100,'Valid Account Combination','D',10,'The Combination identifies a valid combination of element which represent a GL account.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Combination',0,TO_TIMESTAMP('2013-10-11 16:38:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68701 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:12 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN C_ValidCombination_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:15 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68703,245,0,16,53618,'Created',TO_TIMESTAMP('2013-10-11 16:39:20','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Created',0,TO_TIMESTAMP('2013-10-11 16:39:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:15 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68703 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:15 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Created TIMESTAMP DEFAULT NULL 
;

-- Apr 24, 2014 7:05:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68704,246,0,18,110,53618,'CreatedBy',TO_TIMESTAMP('2013-10-11 16:39:48','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',10,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Created By',0,TO_TIMESTAMP('2013-10-11 16:39:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68704 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:17 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN CreatedBy NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68705,308,0,19,53618,'GL_Budget_ID',TO_TIMESTAMP('2013-10-11 16:40:28','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Budget','D',10,'The General Ledger Budget identifies a user defined budget.  These can be used in reporting as a comparison against your actual amounts.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Budget',0,TO_TIMESTAMP('2013-10-11 16:40:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68705 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:18 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN GL_Budget_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68706,313,0,30,53618,'GL_JournalBatch_ID',TO_TIMESTAMP('2013-10-11 16:40:47','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal Batch','D',10,'The General Ledger Journal Batch identifies a group of journals to be processed as a group.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Journal Batch',0,TO_TIMESTAMP('2013-10-11 16:40:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68706 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:19 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN GL_JournalBatch_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56463,0,'I_Budget_ID',TO_TIMESTAMP('2013-10-11 16:41:11','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','I_Budget_ID','I_Budget_ID',TO_TIMESTAMP('2013-10-11 16:41:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56463 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68707,56463,0,13,53618,'I_Budget_ID',TO_TIMESTAMP('2013-10-11 16:41:44','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','Y','Y','Y','N','N','N','N','N','I_Budget_ID',1,TO_TIMESTAMP('2013-10-11 16:41:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68707 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN I_Budget_ID NUMERIC(10) NOT NULL
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD CONSTRAINT I_Budget_Key PRIMARY KEY (I_Budget_ID)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68708,912,0,10,53618,'I_ErrorMsg',TO_TIMESTAMP('2013-10-11 16:43:26','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process','D',2000,'The Import Error Message displays any error messages generated during the import process.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Import Error Message',0,TO_TIMESTAMP('2013-10-11 16:43:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68708 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:20 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN I_ErrorMsg VARCHAR(2000) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68709,913,0,20,53618,'I_IsImported',TO_TIMESTAMP('2013-10-11 16:43:50','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed','D',1,'The Imported check box indicates if this import has been processed.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Imported',0,TO_TIMESTAMP('2013-10-11 16:43:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68709 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:21 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN I_IsImported CHAR(1) CHECK (I_IsImported IN ('Y','N')) NOT NULL
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68710,348,0,20,53618,'IsActive',TO_TIMESTAMP('2013-10-11 16:46:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Active',0,TO_TIMESTAMP('2013-10-11 16:46:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68710 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N'))
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68711,454,0,19,53618,'M_Product_ID',TO_TIMESTAMP('2013-10-11 16:48:45','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',10,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product',0,TO_TIMESTAMP('2013-10-11 16:48:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68711 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:22 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN M_Product_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56464,0,'Month_0_Amt',TO_TIMESTAMP('2013-10-11 16:49:12','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_0_Amt','Month_0_Amt',TO_TIMESTAMP('2013-10-11 16:49:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56464 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68712,56464,0,12,53618,'Month_0_Amt',TO_TIMESTAMP('2013-10-11 16:49:24','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_0_Amt',0,TO_TIMESTAMP('2013-10-11 16:49:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68712 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_0_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56465,0,'Month_1_Amt',TO_TIMESTAMP('2013-10-11 16:50:21','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_1_Amt','Month_1_Amt',TO_TIMESTAMP('2013-10-11 16:50:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56465 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68713,56465,0,12,53618,'Month_1_Amt',TO_TIMESTAMP('2013-10-11 16:50:30','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_1_Amt',0,TO_TIMESTAMP('2013-10-11 16:50:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68713 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:24 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_1_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56466,0,'Month_10_Amt',TO_TIMESTAMP('2013-10-11 16:50:53','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_10_Amt','Month_10_Amt',TO_TIMESTAMP('2013-10-11 16:50:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56466 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68714,56466,0,12,53618,'Month_10_Amt',TO_TIMESTAMP('2013-10-11 16:51:04','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_10_Amt',0,TO_TIMESTAMP('2013-10-11 16:51:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68714 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_10_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56467,0,'Month_11_Amt',TO_TIMESTAMP('2013-10-11 16:51:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_11_Amt','Month_11_Amt',TO_TIMESTAMP('2013-10-11 16:51:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56467 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68715,56467,0,12,53618,'Month_11_Amt',TO_TIMESTAMP('2013-10-11 16:51:36','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_11_Amt',0,TO_TIMESTAMP('2013-10-11 16:51:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68715 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:26 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_11_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56468,0,'Month_2_Amt',TO_TIMESTAMP('2013-10-11 16:51:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_2_Amt','Month_2_Amt',TO_TIMESTAMP('2013-10-11 16:51:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56468 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68716,56468,0,12,53618,'Month_2_Amt',TO_TIMESTAMP('2013-10-11 16:52:10','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_2_Amt',0,TO_TIMESTAMP('2013-10-11 16:52:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68716 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_2_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56469,0,'Month_3_Amt',TO_TIMESTAMP('2013-10-11 16:52:26','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_3_Amt','Month_3_Amt',TO_TIMESTAMP('2013-10-11 16:52:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56469 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68717,56469,0,12,53618,'Month_3_Amt',TO_TIMESTAMP('2013-10-11 16:52:37','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_3_Amt',0,TO_TIMESTAMP('2013-10-11 16:52:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68717 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:28 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_3_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56470,0,'Month_4_Amt',TO_TIMESTAMP('2013-10-11 16:53:02','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_4_Amt','Month_4_Amt',TO_TIMESTAMP('2013-10-11 16:53:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56470 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68718,56470,0,12,53618,'Month_4_Amt',TO_TIMESTAMP('2013-10-11 16:53:13','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_4_Amt',0,TO_TIMESTAMP('2013-10-11 16:53:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68718 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:29 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_4_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56471,0,'Month_5_Amt',TO_TIMESTAMP('2013-10-11 16:53:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_5_Amt','Month_5_Amt',TO_TIMESTAMP('2013-10-11 16:53:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56471 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68719,56471,0,12,53618,'Month_5_Amt',TO_TIMESTAMP('2013-10-11 16:53:48','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_5_Amt',0,TO_TIMESTAMP('2013-10-11 16:53:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68719 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_5_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56472,0,'Month_6_Amt',TO_TIMESTAMP('2013-10-11 16:54:07','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_6_Amt','Month_6_Amt',TO_TIMESTAMP('2013-10-11 16:54:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56472 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68720,56472,0,12,53618,'Month_6_Amt',TO_TIMESTAMP('2013-10-11 16:54:17','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_6_Amt',0,TO_TIMESTAMP('2013-10-11 16:54:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68720 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_6_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56473,0,'Month_7_Amt',TO_TIMESTAMP('2013-10-11 16:54:32','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_7_Amt','Month_7_Amt',TO_TIMESTAMP('2013-10-11 16:54:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56473 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68721,56473,0,12,53618,'Month_7_Amt',TO_TIMESTAMP('2013-10-11 16:54:41','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_7_Amt',0,TO_TIMESTAMP('2013-10-11 16:54:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68721 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:31 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_7_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56474,0,'Month_8_Amt',TO_TIMESTAMP('2013-10-11 16:54:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_8_Amt','Month_8_Amt',TO_TIMESTAMP('2013-10-11 16:54:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56474 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68722,56474,0,12,53618,'Month_8_Amt',TO_TIMESTAMP('2013-10-11 16:55:08','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_8_Amt',0,TO_TIMESTAMP('2013-10-11 16:55:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68722 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_8_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56475,0,'Month_9_Amt',TO_TIMESTAMP('2013-10-11 16:55:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month_9_Amt','Month_9_Amt',TO_TIMESTAMP('2013-10-11 16:55:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56475 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:33 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68723,56475,0,12,53618,'Month_9_Amt',TO_TIMESTAMP('2013-10-11 16:55:36','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Month_9_Amt',0,TO_TIMESTAMP('2013-10-11 16:55:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:33 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68723 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:33 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_9_Amt NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:05:34 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68724,2114,0,10,53618,'OrgTrxValue',TO_TIMESTAMP('2013-10-11 16:55:59','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Transaction Organization','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Trx Org Key',0,TO_TIMESTAMP('2013-10-11 16:55:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:34 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68724 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:34 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN OrgTrxValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:36 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68725,1047,0,20,53618,'Processed',TO_TIMESTAMP('2013-10-11 16:56:44','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','D',1,'The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Processed',0,TO_TIMESTAMP('2013-10-11 16:56:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:36 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68725 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:36 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Processed CHAR(1) DEFAULT NULL CHECK (Processed IN ('Y','N'))
;

-- Apr 24, 2014 7:05:38 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53487,'org.compiere.process.ImportBudget','N',TO_TIMESTAMP('2013-10-11 16:57:54','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','Import Budget','Y',0,0,TO_TIMESTAMP('2013-10-11 16:57:54','YYYY-MM-DD HH24:MI:SS'),100,'10000000')
;

-- Apr 24, 2014 7:05:38 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53487 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Apr 24, 2014 7:05:40 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,53487,54132,15,'DateAcct',TO_TIMESTAMP('2013-10-11 16:58:37','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','Y','N','Start Date',10,TO_TIMESTAMP('2013-10-11 16:58:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:40 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54132 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:41 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56476,0,'No_Of_Periods',TO_TIMESTAMP('2013-10-11 16:59:03','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','No_Of_Periods','No_Of_Periods',TO_TIMESTAMP('2013-10-11 16:59:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:42 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56476 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:42 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,56476,0,53487,54133,11,'No_Of_Periods',TO_TIMESTAMP('2013-10-11 16:59:19','YYYY-MM-DD HH24:MI:SS'),100,'0','D',0,'Y','Y','N','N','No Of Periods',20,TO_TIMESTAMP('2013-10-11 16:59:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:42 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54133 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:43 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53487,54134,19,130,'AD_Org_ID',TO_TIMESTAMP('2013-10-11 17:00:03','YYYY-MM-DD HH24:MI:SS'),100,'-1','D',0,'Y','Y','Y','N','Organization',30,TO_TIMESTAMP('2013-10-11 17:00:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:43 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54134 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2092,0,53487,54135,10,'BatchDescription',TO_TIMESTAMP('2013-10-11 17:00:55','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','Y','N','BatchDescription',40,TO_TIMESTAMP('2013-10-11 17:00:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54135 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:45 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,53487,54136,19,'C_AcctSchema_ID',TO_TIMESTAMP('2013-10-11 17:01:25','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Schema','D',0,'Y','Y','Y','N','Accounting Schema',50,TO_TIMESTAMP('2013-10-11 17:01:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:45 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54136 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:46 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53487,54137,20,'DeleteOldImported',TO_TIMESTAMP('2013-10-11 17:01:47','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','Delete old imported records',60,TO_TIMESTAMP('2013-10-11 17:01:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:46 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54137 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 24, 2014 7:05:47 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68726,524,0,53487,28,53618,'Processing',TO_TIMESTAMP('2013-10-11 17:02:26','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Process Now',0,TO_TIMESTAMP('2013-10-11 17:02:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:47 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68726 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:47 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:48 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68727,1675,0,10,53618,'ProductValue',TO_TIMESTAMP('2013-10-11 17:02:55','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Product','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Key',0,TO_TIMESTAMP('2013-10-11 17:02:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:48 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68727 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:48 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN ProductValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68728,2118,0,10,53618,'ProjectValue',TO_TIMESTAMP('2013-10-11 17:03:27','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Project','D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Key',0,TO_TIMESTAMP('2013-10-11 17:03:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68728 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:50 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN ProjectValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56477,0,'SalesRegionValue',TO_TIMESTAMP('2013-10-11 17:03:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Sales Region Value','Sales Region Value',TO_TIMESTAMP('2013-10-11 17:03:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:05:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56477 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:05:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68729,56477,0,10,53618,'SalesRegionValue',TO_TIMESTAMP('2013-10-11 17:04:08','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sales Region Value',0,TO_TIMESTAMP('2013-10-11 17:04:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68729 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:52 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN SalesRegionValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:54 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68730,607,0,16,53618,'Updated',TO_TIMESTAMP('2013-10-11 17:04:39','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Updated',0,TO_TIMESTAMP('2013-10-11 17:04:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:54 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68730 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:54 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Updated TIMESTAMP DEFAULT NULL 
;

-- Apr 24, 2014 7:05:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68731,608,0,18,110,53618,'UpdatedBy',TO_TIMESTAMP('2013-10-11 17:05:17','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',10,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Updated By',0,TO_TIMESTAMP('2013-10-11 17:05:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68731 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:55 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN UpdatedBy NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:57 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68732,613,0,18,134,53618,'User1_ID',TO_TIMESTAMP('2013-10-11 17:05:53','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User List 1',0,TO_TIMESTAMP('2013-10-11 17:05:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:57 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68732 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:57 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN User1_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:58 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,68733,614,0,18,137,53618,'User2_ID',TO_TIMESTAMP('2013-10-11 17:06:29','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User List 2',0,TO_TIMESTAMP('2013-10-11 17:06:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:05:58 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68733 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:05:58 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN User2_ID NUMERIC(10) DEFAULT NULL 
;

-- Apr 24, 2014 7:05:59 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,WinWidth) VALUES (0,0,53286,TO_TIMESTAMP('2013-10-11 17:07:17','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','Y','Import Budget','N',TO_TIMESTAMP('2013-10-11 17:07:17','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0)
;

-- Apr 24, 2014 7:06:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53286 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Apr 24, 2014 7:06:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53780,53618,53286,TO_TIMESTAMP('2013-10-11 17:07:49','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','Y','N','N','Import Budget ','N',10,0,TO_TIMESTAMP('2013-10-11 17:07:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53780 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Apr 24, 2014 7:06:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68707,69831,0,53780,TO_TIMESTAMP('2013-10-11 17:08:57','YYYY-MM-DD HH24:MI:SS'),100,14,'Y=N','D','Y','Y','Y','Y','N','N','N','N','N','I_Budget_ID',10,10,0,TO_TIMESTAMP('2013-10-11 17:08:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69831 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET Name='Import Budget',Updated=TO_TIMESTAMP('2014-04-24 19:06:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69831
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69831
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68709,69832,0,53780,TO_TIMESTAMP('2013-10-11 17:09:45','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed',0,'D','The Imported check box indicates if this import has been processed.','Y','Y','Y','Y','N','N','N','Y','N','Imported',20,20,0,TO_TIMESTAMP('2013-10-11 17:09:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69832 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68708,69833,0,53780,TO_TIMESTAMP('2013-10-11 17:10:43','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process',60,'D','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','Y','N','N','N','Y','N','Import Error Message',30,30,0,TO_TIMESTAMP('2013-10-11 17:10:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:05 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69833 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68706,69834,0,53780,TO_TIMESTAMP('2013-10-11 17:11:26','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal Batch',0,'D','The General Ledger Journal Batch identifies a group of journals to be processed as a group.','Y','Y','Y','Y','N','N','N','Y','N','Journal Batch',40,40,0,TO_TIMESTAMP('2013-10-11 17:11:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69834 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68686,69835,0,53780,TO_TIMESTAMP('2013-10-11 17:12:49','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',0,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','Y','N','N','N','Y','Y','Client',50,50,0,TO_TIMESTAMP('2013-10-11 17:12:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69835 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68690,69836,0,53780,TO_TIMESTAMP('2013-10-11 17:13:15','YYYY-MM-DD HH24:MI:SS'),100,'Document Number of the Batch',0,'D','Y','Y','Y','Y','N','N','N','N','N','Batch Document No',60,60,0,TO_TIMESTAMP('2013-10-11 17:13:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69836 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2014-04-24 19:06:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=68690
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','BatchDocumentNo','VARCHAR(40)',null,null)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','BatchDocumentNo',null,'NOT NULL',null)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68687,69837,0,53780,TO_TIMESTAMP('2013-10-11 17:15:49','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',0,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','Y','N','N','N','N','Y','Organization',70,70,0,TO_TIMESTAMP('2013-10-11 17:15:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:07 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69837 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68685,69838,0,53780,TO_TIMESTAMP('2013-10-11 17:16:25','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Accounting Schema',0,'D','Y','Y','Y','Y','N','N','N','N','N','Account Schema Name',80,80,0,TO_TIMESTAMP('2013-10-11 17:16:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69838 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68693,69839,0,53780,TO_TIMESTAMP('2013-10-11 17:16:48','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting',0,'D','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','Y','N','N','N','N','Y','Accounting Schema',90,90,0,TO_TIMESTAMP('2013-10-11 17:16:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69839 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68692,69840,0,53780,TO_TIMESTAMP('2013-10-11 17:17:13','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Budget Code ',100,100,0,TO_TIMESTAMP('2013-10-11 17:17:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69840 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68705,69841,0,53780,TO_TIMESTAMP('2013-10-11 17:17:35','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Budget',0,'D','The General Ledger Budget identifies a user defined budget.  These can be used in reporting as a comparison against your actual amounts.','Y','Y','Y','Y','N','N','N','N','Y','Budget',110,110,0,TO_TIMESTAMP('2013-10-11 17:17:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69841 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68701,69842,126,0,53780,TO_TIMESTAMP('2013-10-11 17:18:01','YYYY-MM-DD HH24:MI:SS'),100,'Valid Account Combination',0,'D','The Combination identifies a valid combination of element which represent a GL account.','Y','Y','Y','Y','N','N','N','N','N','Combination',120,120,0,TO_TIMESTAMP('2013-10-11 17:18:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:09 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69842 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68684,69843,0,53780,TO_TIMESTAMP('2013-10-11 17:18:27','YYYY-MM-DD HH24:MI:SS'),100,'Key of Account Element',0,'D','Y','Y','Y','Y','N','N','N','N','N','Account Key',130,130,0,TO_TIMESTAMP('2013-10-11 17:18:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69843 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68694,69844,0,53780,TO_TIMESTAMP('2013-10-11 17:18:53','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity',0,'D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','Y','N','N','N','N','Y','Activity',140,140,0,TO_TIMESTAMP('2013-10-11 17:18:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69844 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68691,69845,0,53780,TO_TIMESTAMP('2013-10-11 17:19:15','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Business Partner',0,'D','Y','Y','Y','Y','N','N','N','N','N','Business Partner Key',150,150,0,TO_TIMESTAMP('2013-10-11 17:19:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69845 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68695,69846,0,53780,TO_TIMESTAMP('2013-10-11 17:19:48','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',0,'D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','Y','N','N','N','N','Y','Business Partner ',160,160,0,TO_TIMESTAMP('2013-10-11 17:19:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69846 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68727,69847,0,53780,TO_TIMESTAMP('2013-10-11 17:20:33','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Product',0,'D','Y','Y','Y','Y','N','N','N','N','N','Product Key',170,170,0,TO_TIMESTAMP('2013-10-11 17:20:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:11 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69847 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68711,69848,0,53780,TO_TIMESTAMP('2013-10-11 17:21:00','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',0,'D','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','Y','N','N','N','N','Y','Product',180,180,0,TO_TIMESTAMP('2013-10-11 17:21:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69848 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68728,69849,0,53780,TO_TIMESTAMP('2013-10-11 17:21:40','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Project',0,'D','Y','Y','Y','Y','N','N','N','N','N','Project Key',190,190,0,TO_TIMESTAMP('2013-10-11 17:21:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69849 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68699,69850,0,53780,TO_TIMESTAMP('2013-10-11 17:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',0,'D','A Project allows you to track and control internal or external activities.','Y','Y','Y','Y','N','N','N','N','Y','Project',200,200,0,TO_TIMESTAMP('2013-10-11 17:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69850 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68724,69851,0,53780,TO_TIMESTAMP('2013-10-11 17:22:44','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Transaction Organization',0,'D','Y','Y','Y','Y','N','N','N','N','N','Trx Org Key',210,210,0,TO_TIMESTAMP('2013-10-11 17:22:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69851 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68688,69852,0,53780,TO_TIMESTAMP('2013-10-11 17:23:15','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization',0,'D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','Y','N','N','N','N','Y','Trx Organization',220,220,0,TO_TIMESTAMP('2013-10-11 17:23:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69852 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68696,69854,0,53780,TO_TIMESTAMP('2013-10-11 17:23:58','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign',0,'D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','Y','N','N','N','N','Y','Campaign',240,240,0,TO_TIMESTAMP('2013-10-11 17:23:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69854 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68732,69855,0,53780,TO_TIMESTAMP('2013-10-11 17:24:15','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1',0,'D','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','Y','N','N','N','N','N','User List 1',250,250,0,TO_TIMESTAMP('2013-10-11 17:24:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69855 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68733,69856,0,53780,TO_TIMESTAMP('2013-10-11 17:24:33','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2',0,'D','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','Y','N','N','N','N','Y','User List 2',260,260,0,TO_TIMESTAMP('2013-10-11 17:24:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69856 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68729,69857,0,53780,TO_TIMESTAMP('2013-10-11 17:24:50','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Sales Region Value',270,270,0,TO_TIMESTAMP('2013-10-11 17:24:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69857 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68700,69858,0,53780,TO_TIMESTAMP('2013-10-11 17:25:23','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region',0,'D','The Sales Region indicates a specific area of sales coverage.','Y','Y','Y','Y','N','N','N','N','Y','Sales Region',280,280,0,TO_TIMESTAMP('2013-10-11 17:25:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69858 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68697,69859,0,53780,TO_TIMESTAMP('2013-10-11 17:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from',0,'D','The Location From indicates the location that a product was moved from.','Y','Y','Y','Y','N','N','N','N','N','Location From',290,290,0,TO_TIMESTAMP('2013-10-11 17:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69859 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68698,69860,0,53780,TO_TIMESTAMP('2013-10-11 17:26:03','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to',0,'D','The Location To indicates the location that a product was moved to.','Y','Y','Y','Y','N','N','N','N','N','Location To',300,300,0,TO_TIMESTAMP('2013-10-11 17:26:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69860 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68689,69861,0,53780,TO_TIMESTAMP('2013-10-11 17:26:18','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Asset Value',310,310,0,TO_TIMESTAMP('2013-10-11 17:26:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69861 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68682,69862,0,53780,TO_TIMESTAMP('2013-10-11 17:26:30','YYYY-MM-DD HH24:MI:SS'),100,'Asset used internally or by customers',0,'D','An asset is either created by purchasing or by delivering a product.  An asset can be used internally or be a customer asset.','Y','Y','Y','Y','N','N','N','N','Y','Asset',320,320,0,TO_TIMESTAMP('2013-10-11 17:26:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69862 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68712,69863,0,53780,TO_TIMESTAMP('2013-10-11 17:26:47','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_0_Amt',330,330,0,TO_TIMESTAMP('2013-10-11 17:26:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69863 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68713,69864,0,53780,TO_TIMESTAMP('2013-10-11 17:27:01','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_1_Amt',340,340,0,TO_TIMESTAMP('2013-10-11 17:27:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69864 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68716,69865,0,53780,TO_TIMESTAMP('2013-10-11 17:27:21','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_2_Amt',350,350,0,TO_TIMESTAMP('2013-10-11 17:27:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69865 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68717,69866,0,53780,TO_TIMESTAMP('2013-10-11 17:27:39','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_3_Amt',360,360,0,TO_TIMESTAMP('2013-10-11 17:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69866 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68718,69867,0,53780,TO_TIMESTAMP('2013-10-11 17:28:10','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_4_Amt',370,370,0,TO_TIMESTAMP('2013-10-11 17:28:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:25 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69867 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68719,69868,0,53780,TO_TIMESTAMP('2013-10-11 17:28:28','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_5_Amt',380,380,0,TO_TIMESTAMP('2013-10-11 17:28:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:26 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69868 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68720,69869,0,53780,TO_TIMESTAMP('2013-10-11 17:28:44','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_6_Amt',390,390,0,TO_TIMESTAMP('2013-10-11 17:28:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69869 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68721,69870,0,53780,TO_TIMESTAMP('2013-10-11 17:28:59','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_7_Amt',400,400,0,TO_TIMESTAMP('2013-10-11 17:28:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69870 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68722,69871,0,53780,TO_TIMESTAMP('2013-10-11 17:29:12','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_8_Amt',410,410,0,TO_TIMESTAMP('2013-10-11 17:29:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69871 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68723,69872,0,53780,TO_TIMESTAMP('2013-10-11 17:29:24','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_9_Amt',420,420,0,TO_TIMESTAMP('2013-10-11 17:29:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69872 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68714,69873,0,53780,TO_TIMESTAMP('2013-10-11 17:29:34','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Month_10_Amt',430,430,0,TO_TIMESTAMP('2013-10-11 17:29:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:31 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69873 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68715,69874,0,53780,TO_TIMESTAMP('2013-10-11 17:29:49','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','Y','Month_11_Amt',440,440,0,TO_TIMESTAMP('2013-10-11 17:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:32 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69874 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:06:33 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2014-04-24 19:06:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69860
;

-- Apr 24, 2014 7:06:35 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53649,0,53286,TO_TIMESTAMP('2013-10-11 17:33:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','Y','N','Import Budget',TO_TIMESTAMP('2013-10-11 17:33:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:06:35 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53649 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Apr 24, 2014 7:08:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68703,69875,0,53780,TO_TIMESTAMP('2013-10-11 17:36:21','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created',0,'D','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','Created',450,450,0,TO_TIMESTAMP('2013-10-11 17:36:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69875 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68704,69876,0,53780,TO_TIMESTAMP('2013-10-11 17:36:37','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records',0,'D','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','Created By',460,460,0,TO_TIMESTAMP('2013-10-11 17:36:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:17 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69876 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68730,69877,0,53780,TO_TIMESTAMP('2013-10-11 17:36:59','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated',0,'D','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','Updated',470,470,0,TO_TIMESTAMP('2013-10-11 17:36:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69877 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68731,69878,0,53780,TO_TIMESTAMP('2013-10-11 17:37:36','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records',0,'D','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','Updated By',480,480,0,TO_TIMESTAMP('2013-10-11 17:37:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69878 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68710,69879,0,53780,TO_TIMESTAMP('2013-10-11 17:37:47','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',0,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','N','N','Active',490,490,0,TO_TIMESTAMP('2013-10-11 17:37:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69879 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68726,69880,0,53780,TO_TIMESTAMP('2013-10-11 17:47:42','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','Y','N','N','N','N','N','Process Now',500,500,0,TO_TIMESTAMP('2013-10-11 17:47:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69880 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:08:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,68725,69881,0,53780,TO_TIMESTAMP('2013-10-11 17:47:55','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',0,'D','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','Y','N','N','N','N','Y','Processed',510,510,0,TO_TIMESTAMP('2013-10-11 17:47:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:08:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69881 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2014-04-24 19:09:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=68690
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','BatchDocumentNo','VARCHAR(40)',null,'NULL')
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','BatchDocumentNo',null,'NULL',null)
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2014-04-24 19:09:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=68709
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','I_IsImported','CHAR(1)',null,'NULL')
;

-- Apr 24, 2014 7:09:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO t_alter_column values('i_budget','I_IsImported',null,'NULL',null)
;

-- Apr 24, 2014 7:09:13 PM GMT05:30
-- Import Budget AD Chages
DELETE FROM AD_Menu_Trl WHERE AD_Menu_ID=53649
;

-- Apr 24, 2014 7:09:13 PM GMT05:30
-- Import Budget AD Chages
DELETE FROM AD_Menu WHERE AD_Menu_ID=53649
;

-- Apr 24, 2014 7:09:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53665,0,53286,TO_TIMESTAMP('2013-10-24 13:01:13','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','N','N','Import Budget',TO_TIMESTAMP('2013-10-24 13:01:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:13 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53665 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Apr 24, 2014 7:09:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat (AD_Client_ID,AD_ImpFormat_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,FormatType,IsActive,Name,Processing,Updated,UpdatedBy) VALUES (0,50031,0,53618,TO_TIMESTAMP('2013-10-11 17:55:28','YYYY-MM-DD HH24:MI:SS'),100,'C','Y','Import Budget','N',TO_TIMESTAMP('2013-10-11 17:55:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68690,50031,50465,0,TO_TIMESTAMP('2013-10-11 17:56:10','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Batch Document No',10,1,TO_TIMESTAMP('2013-10-11 17:56:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68692,50031,50466,0,TO_TIMESTAMP('2013-10-11 17:56:25','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Budget Code',20,2,TO_TIMESTAMP('2013-10-11 17:56:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68685,50031,50467,0,TO_TIMESTAMP('2013-10-11 17:56:39','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Account Schema Name',30,3,TO_TIMESTAMP('2013-10-11 17:56:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:49 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68691,50031,50468,0,TO_TIMESTAMP('2013-10-11 17:57:13','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Business Partner Key',40,4,TO_TIMESTAMP('2013-10-11 17:57:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68727,50031,50469,0,TO_TIMESTAMP('2013-10-11 17:57:29','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Product Key',50,5,TO_TIMESTAMP('2013-10-11 17:57:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68728,50031,50470,0,TO_TIMESTAMP('2013-10-11 17:57:51','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Project Key',60,6,TO_TIMESTAMP('2013-10-11 17:57:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68732,50031,50471,0,TO_TIMESTAMP('2013-10-11 17:58:10','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','User List 1',70,7,TO_TIMESTAMP('2013-10-11 17:58:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68733,50031,50472,0,TO_TIMESTAMP('2013-10-11 17:58:31','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','User List 2',80,8,TO_TIMESTAMP('2013-10-11 17:58:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68694,50031,50473,0,TO_TIMESTAMP('2013-10-11 17:58:44','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','C_Activity_ID',90,9,TO_TIMESTAMP('2013-10-11 17:58:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68724,50031,50474,0,TO_TIMESTAMP('2013-10-11 17:59:00','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Trx Organisation',100,10,TO_TIMESTAMP('2013-10-11 17:59:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:51 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_ImpFormat_Row SET DataType='N',Updated=TO_TIMESTAMP('2014-04-24 19:09:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ImpFormat_Row_ID=50473
;

-- Apr 24, 2014 7:09:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68729,50031,50476,0,TO_TIMESTAMP('2013-10-11 18:00:01','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Sales Region Value',120,12,TO_TIMESTAMP('2013-10-11 18:00:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68689,50031,50477,0,TO_TIMESTAMP('2013-10-11 18:01:13','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Asset Value',130,13,TO_TIMESTAMP('2013-10-11 18:01:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68684,50031,50478,0,TO_TIMESTAMP('2013-10-11 18:01:31','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Account Key',140,14,TO_TIMESTAMP('2013-10-11 18:01:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68712,50031,50479,0,TO_TIMESTAMP('2013-10-11 18:01:56','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_0_Amt',150,15,TO_TIMESTAMP('2013-10-11 18:01:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68713,50031,50480,0,TO_TIMESTAMP('2013-10-11 18:02:31','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_1_Amt',160,160,TO_TIMESTAMP('2013-10-11 18:02:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_ImpFormat_Row SET StartNo=16,Updated=TO_TIMESTAMP('2014-04-24 19:09:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ImpFormat_Row_ID=50480
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68716,50031,50481,0,TO_TIMESTAMP('2013-10-11 18:03:01','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_2_Amt',170,17,TO_TIMESTAMP('2013-10-11 18:03:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68717,50031,50482,0,TO_TIMESTAMP('2013-10-11 18:03:16','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_3_Amt',180,18,TO_TIMESTAMP('2013-10-11 18:03:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68718,50031,50483,0,TO_TIMESTAMP('2013-10-11 18:03:30','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_4_Amt',190,19,TO_TIMESTAMP('2013-10-11 18:03:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68719,50031,50484,0,TO_TIMESTAMP('2013-10-11 18:03:45','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_5_Amt',200,20,TO_TIMESTAMP('2013-10-11 18:03:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68720,50031,50485,0,TO_TIMESTAMP('2013-10-11 18:04:02','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_6_Amt',210,0,TO_TIMESTAMP('2013-10-11 18:04:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68721,50031,50486,0,TO_TIMESTAMP('2013-10-11 18:07:43','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_7_Amt',220,22,TO_TIMESTAMP('2013-10-11 18:07:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68722,50031,50487,0,TO_TIMESTAMP('2013-10-11 18:07:57','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_8_Amt',230,23,TO_TIMESTAMP('2013-10-11 18:07:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68723,50031,50488,0,TO_TIMESTAMP('2013-10-11 18:08:11','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_9_Amt',240,24,TO_TIMESTAMP('2013-10-11 18:08:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68714,50031,50489,0,TO_TIMESTAMP('2013-10-11 18:08:24','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_10_Amt',250,25,TO_TIMESTAMP('2013-10-11 18:08:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:09:53 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,68715,50031,50490,0,TO_TIMESTAMP('2013-10-11 18:08:33','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Month_11_Amt',260,26,TO_TIMESTAMP('2013-10-11 18:08:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:10:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_ImpFormat_Row SET StartNo=21,Updated=TO_TIMESTAMP('2014-04-24 19:10:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ImpFormat_Row_ID=50485
;

-- Apr 24, 2014 7:25:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57129,0,'Jnl_Line_Description',TO_TIMESTAMP('2014-04-24 19:25:01','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Jnl_Line_Description','Jnl_Line_Description',TO_TIMESTAMP('2014-04-24 19:25:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:25:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57129 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Element SET Name='Journal Line Description', PrintName='Journal Line Description',Updated=TO_TIMESTAMP('2014-04-24 19:25:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=57129
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=57129
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET ColumnName='Jnl_Line_Description', Name='Journal Line Description', Description=NULL, Help=NULL WHERE AD_Element_ID=57129
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Process_Para SET ColumnName='Jnl_Line_Description', Name='Journal Line Description', Description=NULL, Help=NULL, AD_Element_ID=57129 WHERE UPPER(ColumnName)='JNL_LINE_DESCRIPTION' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Process_Para SET ColumnName='Jnl_Line_Description', Name='Journal Line Description', Description=NULL, Help=NULL WHERE AD_Element_ID=57129 AND IsCentrallyMaintained='Y'
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET Name='Journal Line Description', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=57129) AND IsCentrallyMaintained='Y'
;

-- Apr 24, 2014 7:25:19 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_PrintFormatItem SET PrintName='Journal Line Description', Name='Journal Line Description' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=57129)
;

-- Apr 24, 2014 7:25:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72699,57129,0,14,53618,'Jnl_Line_Description',TO_TIMESTAMP('2014-04-24 19:25:36','YYYY-MM-DD HH24:MI:SS'),100,'D',255,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Journal Line Description',0,TO_TIMESTAMP('2014-04-24 19:25:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:25:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72699 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:25:40 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Jnl_Line_Description VARCHAR(255) DEFAULT NULL 
;

-- Apr 24, 2014 7:26:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57130,0,'Month_0_Qty',TO_TIMESTAMP('2014-04-24 19:26:07','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_0_Qty','Month_0_Qty',TO_TIMESTAMP('2014-04-24 19:26:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:26:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57130 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:26:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72700,57130,0,29,53618,'Month_0_Qty',TO_TIMESTAMP('2014-04-24 19:26:23','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_0_Qty',0,TO_TIMESTAMP('2014-04-24 19:26:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:26:24 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72700 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:26:28 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_0_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:26:42 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57131,0,'Month_10_Qty',TO_TIMESTAMP('2014-04-24 19:26:41','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_10_Qty','Month_10_Qty',TO_TIMESTAMP('2014-04-24 19:26:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:26:42 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57131 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:27:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72701,57131,0,29,53618,'Month_10_Qty',TO_TIMESTAMP('2014-04-24 19:26:59','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_10_Qty',0,TO_TIMESTAMP('2014-04-24 19:26:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:27:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72701 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:27:03 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_10_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:27:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57132,0,'Month_11_Qty',TO_TIMESTAMP('2014-04-24 19:27:19','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_11_Qty','Month_11_Qty',TO_TIMESTAMP('2014-04-24 19:27:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:27:19 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57132 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:27:35 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72702,57132,0,29,53618,'Month_11_Qty',TO_TIMESTAMP('2014-04-24 19:27:34','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_11_Qty',0,TO_TIMESTAMP('2014-04-24 19:27:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:27:35 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72702 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:27:37 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_11_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:27:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57133,0,'Month_1_Qty',TO_TIMESTAMP('2014-04-24 19:27:50','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_1_Qty','Month_1_Qty',TO_TIMESTAMP('2014-04-24 19:27:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:27:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57133 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:28:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72703,57133,0,29,53618,'Month_1_Qty',TO_TIMESTAMP('2014-04-24 19:28:00','YYYY-MM-DD HH24:MI:SS'),100,'D',14,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_1_Qty',0,TO_TIMESTAMP('2014-04-24 19:28:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:28:00 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72703 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:28:06 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET FieldLength=20,Updated=TO_TIMESTAMP('2014-04-24 19:28:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=72703
;

-- Apr 24, 2014 7:28:08 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_1_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:28:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57134,0,'Month_2_Qty',TO_TIMESTAMP('2014-04-24 19:28:21','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_2_Qty','Month_2_Qty',TO_TIMESTAMP('2014-04-24 19:28:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:28:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57134 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:28:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72704,57134,0,29,53618,'Month_2_Qty',TO_TIMESTAMP('2014-04-24 19:28:27','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_2_Qty',0,TO_TIMESTAMP('2014-04-24 19:28:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:28:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72704 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:28:31 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_2_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:28:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57135,0,'Month_3_Qty',TO_TIMESTAMP('2014-04-24 19:28:44','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_3_Qty','Month_3_Qty',TO_TIMESTAMP('2014-04-24 19:28:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:28:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57135 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:28:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72705,57135,0,29,53618,'Month_3_Qty',TO_TIMESTAMP('2014-04-24 19:28:51','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_3_Qty',0,TO_TIMESTAMP('2014-04-24 19:28:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:28:51 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72705 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:28:54 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_3_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:29:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57136,0,'Month_4_Qty',TO_TIMESTAMP('2014-04-24 19:29:06','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_4_Qty','Month_4_Qty',TO_TIMESTAMP('2014-04-24 19:29:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:29:06 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57136 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:29:14 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72706,57136,0,29,53618,'Month_4_Qty',TO_TIMESTAMP('2014-04-24 19:29:13','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_4_Qty',0,TO_TIMESTAMP('2014-04-24 19:29:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:29:14 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72706 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:29:16 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_4_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:29:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57137,0,'Month_5_Qty',TO_TIMESTAMP('2014-04-24 19:29:29','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_5_Qty','Month_5_Qty',TO_TIMESTAMP('2014-04-24 19:29:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:29:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57137 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:29:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72707,57137,0,29,53618,'Month_5_Qty',TO_TIMESTAMP('2014-04-24 19:29:37','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_5_Qty',0,TO_TIMESTAMP('2014-04-24 19:29:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:29:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72707 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:29:41 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_5_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:30:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57138,0,'Month_6_Qty',TO_TIMESTAMP('2014-04-24 19:30:22','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_6_Qty','Month_6_Qty',TO_TIMESTAMP('2014-04-24 19:30:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:30:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57138 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:30:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72708,57138,0,29,53618,'Month_6_Qty',TO_TIMESTAMP('2014-04-24 19:30:27','YYYY-MM-DD HH24:MI:SS'),100,'D',14,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_6_Qty',0,TO_TIMESTAMP('2014-04-24 19:30:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:30:28 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72708 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:30:33 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Column SET FieldLength=20,Updated=TO_TIMESTAMP('2014-04-24 19:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=72708
;

-- Apr 24, 2014 7:30:36 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_6_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:30:47 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57139,0,'Month_7_Qty',TO_TIMESTAMP('2014-04-24 19:30:47','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_7_Qty','Month_7_Qty',TO_TIMESTAMP('2014-04-24 19:30:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:30:47 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57139 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:30:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72709,57139,0,29,53618,'Month_7_Qty',TO_TIMESTAMP('2014-04-24 19:30:54','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_7_Qty',0,TO_TIMESTAMP('2014-04-24 19:30:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:30:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72709 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:30:57 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_7_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:31:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57140,0,'Month_8_Qty',TO_TIMESTAMP('2014-04-24 19:31:43','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_8_Qty','Month_8_Qty',TO_TIMESTAMP('2014-04-24 19:31:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:31:44 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57140 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:31:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72710,57140,0,29,53618,'Month_8_Qty',TO_TIMESTAMP('2014-04-24 19:31:52','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_8_Qty',0,TO_TIMESTAMP('2014-04-24 19:31:52','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:31:52 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72710 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:31:55 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_8_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:32:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,57141,0,'Month_9_Qty',TO_TIMESTAMP('2014-04-24 19:32:09','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Month_9_Qty','Month_9_Qty',TO_TIMESTAMP('2014-04-24 19:32:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:32:12 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=57141 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 24, 2014 7:32:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72711,57141,0,29,53618,'Month_9_Qty',TO_TIMESTAMP('2014-04-24 19:32:19','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Month_9_Qty',0,TO_TIMESTAMP('2014-04-24 19:32:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 24, 2014 7:32:20 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72711 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 24, 2014 7:32:23 PM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN Month_9_Qty NUMERIC DEFAULT NULL 
;

-- Apr 24, 2014 7:40:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72700,73347,0,53780,TO_TIMESTAMP('2014-04-24 19:40:28','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Month_0_Qty',0,520,520,0,TO_TIMESTAMP('2014-04-24 19:40:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:40:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73347 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:40:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72703,73348,0,53780,TO_TIMESTAMP('2014-04-24 19:40:49','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_1_Qty',0,530,530,0,TO_TIMESTAMP('2014-04-24 19:40:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:40:50 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73348 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72704,73349,0,53780,TO_TIMESTAMP('2014-04-24 19:41:02','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Month_2_Qty',0,540,540,0,TO_TIMESTAMP('2014-04-24 19:41:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:02 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73349 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72705,73350,0,53780,TO_TIMESTAMP('2014-04-24 19:41:17','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','N','Y','N','N','N','N','Y','Month_3_Qty',0,550,550,0,TO_TIMESTAMP('2014-04-24 19:41:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:18 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73350 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72706,73351,0,53780,TO_TIMESTAMP('2014-04-24 19:41:27','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Month_4_Qty',0,560,560,0,TO_TIMESTAMP('2014-04-24 19:41:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:27 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73351 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72707,73352,0,53780,TO_TIMESTAMP('2014-04-24 19:41:35','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_5_Qty',0,570,570,0,TO_TIMESTAMP('2014-04-24 19:41:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:37 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73352 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:46 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72708,73353,0,53780,TO_TIMESTAMP('2014-04-24 19:41:45','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Month_6_Qty',0,580,580,0,TO_TIMESTAMP('2014-04-24 19:41:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:46 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73353 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:41:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72709,73354,0,53780,TO_TIMESTAMP('2014-04-24 19:41:54','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_7_Qty',0,590,590,0,TO_TIMESTAMP('2014-04-24 19:41:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:41:55 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73354 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:42:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72710,73355,0,53780,TO_TIMESTAMP('2014-04-24 19:42:08','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_8_Qty',0,600,600,0,TO_TIMESTAMP('2014-04-24 19:42:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:42:08 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73355 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:42:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72711,73356,0,53780,TO_TIMESTAMP('2014-04-24 19:42:21','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_9_Qty',0,610,610,0,TO_TIMESTAMP('2014-04-24 19:42:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:42:22 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73356 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:42:33 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72701,73357,0,53780,TO_TIMESTAMP('2014-04-24 19:42:32','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Month_10_Qty',0,620,620,0,TO_TIMESTAMP('2014-04-24 19:42:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:42:33 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73357 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:42:41 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72702,73358,0,53780,TO_TIMESTAMP('2014-04-24 19:42:41','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','Y','Month_11_Qty',0,630,630,0,TO_TIMESTAMP('2014-04-24 19:42:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:42:41 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73358 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:43:15 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69879
;

-- Apr 24, 2014 7:43:15 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69875
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69876
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73350
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69877
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69878
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69854
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69855
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69856
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69857
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69858
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69859
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69860
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69861
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69862
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69863
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69864
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69865
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69866
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69867
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69868
;

-- Apr 24, 2014 7:43:16 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2014-04-24 19:43:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69869
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69870
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69871
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69872
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69873
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69874
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73347
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73348
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=460,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73349
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=470,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73351
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=480,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73352
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=490,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73353
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=500,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73354
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=510,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73355
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=520,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73356
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=530,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73357
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=540,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73358
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=550,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69880
;

-- Apr 24, 2014 7:43:17 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=560,Updated=TO_TIMESTAMP('2014-04-24 19:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69881
;

-- Apr 24, 2014 7:44:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72699,73359,0,53780,TO_TIMESTAMP('2014-04-24 19:44:22','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','Journal Line Description',0,570,570,0,TO_TIMESTAMP('2014-04-24 19:44:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:44:23 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73359 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=70,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73359
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69837
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69838
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69839
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69840
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69841
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69842
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69843
;

-- Apr 24, 2014 7:44:55 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2014-04-24 19:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69844
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69845
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69846
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69847
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69848
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69849
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69850
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69851
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69852
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69854
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69855
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69856
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69857
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69858
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69859
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69860
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69861
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69862
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69863
;

-- Apr 24, 2014 7:44:56 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_TIMESTAMP('2014-04-24 19:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69864
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69865
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69866
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69867
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69868
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69869
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69870
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69871
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69872
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69873
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69874
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73347
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=460,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73348
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=470,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73349
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=480,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73351
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=490,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73352
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=500,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73353
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=510,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73354
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=520,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73355
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=530,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73356
;

-- Apr 24, 2014 7:44:57 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=540,Updated=TO_TIMESTAMP('2014-04-24 19:44:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73357
;

-- Apr 24, 2014 7:44:58 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=550,Updated=TO_TIMESTAMP('2014-04-24 19:44:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73358
;

-- Apr 24, 2014 7:44:58 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=560,Updated=TO_TIMESTAMP('2014-04-24 19:44:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69880
;

-- Apr 24, 2014 7:44:58 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=570,Updated=TO_TIMESTAMP('2014-04-24 19:44:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69881
;

-- Apr 24, 2014 7:50:18 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_ImpFormat_Row SET AD_Column_ID=68682,Updated=TO_TIMESTAMP('2014-04-24 19:50:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ImpFormat_Row_ID=50465
;

-- Apr 24, 2014 7:50:23 PM GMT05:30
-- Import Budget AD Chages
UPDATE AD_ImpFormat_Row SET DataType='N',Updated=TO_TIMESTAMP('2014-04-24 19:50:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ImpFormat_Row_ID=50490
;

-- Apr 24, 2014 7:51:04 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72700,50031,50548,0,TO_TIMESTAMP('2014-04-24 19:51:02','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_0_Qty',270,0,TO_TIMESTAMP('2014-04-24 19:51:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:51:15 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72703,50031,50549,0,TO_TIMESTAMP('2014-04-24 19:51:15','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_1_Qty',280,0,TO_TIMESTAMP('2014-04-24 19:51:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:51:29 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72704,50031,50550,0,TO_TIMESTAMP('2014-04-24 19:51:26','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_2_Qty',290,0,TO_TIMESTAMP('2014-04-24 19:51:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:51:38 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72705,50031,50551,0,TO_TIMESTAMP('2014-04-24 19:51:37','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_3_Qty',300,0,TO_TIMESTAMP('2014-04-24 19:51:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:51:48 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72706,50031,50552,0,TO_TIMESTAMP('2014-04-24 19:51:48','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_4_Qty',310,0,TO_TIMESTAMP('2014-04-24 19:51:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:51:58 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72707,50031,50553,0,TO_TIMESTAMP('2014-04-24 19:51:57','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_5_Qty',320,0,TO_TIMESTAMP('2014-04-24 19:51:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:52:10 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72708,50031,50554,0,TO_TIMESTAMP('2014-04-24 19:52:10','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_6_Qty',330,0,TO_TIMESTAMP('2014-04-24 19:52:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:52:21 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72709,50031,50555,0,TO_TIMESTAMP('2014-04-24 19:52:20','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_7_Qty',340,0,TO_TIMESTAMP('2014-04-24 19:52:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:52:30 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72710,50031,50556,0,TO_TIMESTAMP('2014-04-24 19:52:30','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_8_Qty',350,0,TO_TIMESTAMP('2014-04-24 19:52:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:52:39 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72711,50031,50557,0,TO_TIMESTAMP('2014-04-24 19:52:39','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_9_Qty',360,0,TO_TIMESTAMP('2014-04-24 19:52:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:52:54 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72701,50031,50558,0,TO_TIMESTAMP('2014-04-24 19:52:53','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_10_Qty',370,0,TO_TIMESTAMP('2014-04-24 19:52:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 24, 2014 7:53:03 PM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72702,50031,50559,0,TO_TIMESTAMP('2014-04-24 19:53:02','YYYY-MM-DD HH24:MI:SS'),100,'N','.','N',0,'Y','Month_11_Qty',380,0,TO_TIMESTAMP('2014-04-24 19:53:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 25, 2014 11:00:44 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,72737,54241,0,10,53618,'CampaignValue',TO_TIMESTAMP('2014-04-25 11:00:43','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Y','CampaignValue',0,TO_TIMESTAMP('2014-04-25 11:00:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 25, 2014 11:00:45 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=72737 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 25, 2014 11:00:47 AM GMT05:30
-- Import Budget AD Chages
ALTER TABLE I_Budget ADD COLUMN CampaignValue VARCHAR(40) DEFAULT NULL 
;

-- Apr 25, 2014 11:02:24 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,72737,73385,0,53780,TO_TIMESTAMP('2014-04-25 11:02:23','YYYY-MM-DD HH24:MI:SS'),100,0,'D','N','Y','Y','Y','Y','N','N','N','N','N','CampaignValue',0,235,590,0,TO_TIMESTAMP('2014-04-25 11:02:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 25, 2014 11:02:24 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=73385 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 25, 2014 11:02:51 AM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field SET Name='Campaign Value',Updated=TO_TIMESTAMP('2014-04-25 11:02:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73385
;

-- Apr 25, 2014 11:02:51 AM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=73385
;

-- Apr 25, 2014 11:17:41 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53487,54724,17,135,'DocAction',TO_TIMESTAMP('2014-04-25 11:17:31','YYYY-MM-DD HH24:MI:SS'),100,'DR','The targeted status of the document','D',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','Y','Y','N','Doc Action',70,TO_TIMESTAMP('2014-04-25 11:17:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 25, 2014 11:17:41 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54724 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 25, 2014 11:22:46 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,VFormat) VALUES (0,0,53688,TO_TIMESTAMP('2014-04-25 11:22:45','YYYY-MM-DD HH24:MI:SS'),100,'Document action list','D','Y','N','_Document Action Import budget',TO_TIMESTAMP('2014-04-25 11:22:45','YYYY-MM-DD HH24:MI:SS'),100,'L','LL')
;

-- Apr 25, 2014 11:22:46 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53688 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Apr 25, 2014 11:23:04 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54401,53688,TO_TIMESTAMP('2014-04-25 11:23:03','YYYY-MM-DD HH24:MI:SS'),100,'Complete','D','Y','Complete',TO_TIMESTAMP('2014-04-25 11:23:03','YYYY-MM-DD HH24:MI:SS'),100,'CO')
;

-- Apr 25, 2014 11:23:04 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54401 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Apr 25, 2014 11:23:16 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54402,53688,TO_TIMESTAMP('2014-04-25 11:23:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Draft',TO_TIMESTAMP('2014-04-25 11:23:16','YYYY-MM-DD HH24:MI:SS'),100,'DR')
;

-- Apr 25, 2014 11:23:16 AM GMT05:30
-- Import Budget AD Chages
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54402 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Apr 25, 2014 11:24:22 AM GMT05:30
-- Import Budget AD Chages
UPDATE AD_Process_Para SET AD_Reference_Value_ID=53688,Updated=TO_TIMESTAMP('2014-04-25 11:24:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=54724
;

-- Apr 25, 2014 11:40:00 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2014-04-25 11:40:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73385
;

-- Apr 25, 2014 11:40:00 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2014-04-25 11:40:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69854
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69855
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69856
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69857
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69858
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69859
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69860
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69861
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69862
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69863
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69864
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69865
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69866
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69867
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69868
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69869
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69870
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69871
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69872
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69873
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69874
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=460,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73347
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=470,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73348
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=480,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73349
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=490,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73350
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=500,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73351
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=510,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73352
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=520,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73353
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=530,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73354
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=540,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73355
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=550,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73356
;

-- Apr 25, 2014 11:40:01 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=560,Updated=TO_TIMESTAMP('2014-04-25 11:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73357
;

-- Apr 25, 2014 11:40:02 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=570,Updated=TO_TIMESTAMP('2014-04-25 11:40:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=73358
;

-- Apr 25, 2014 11:40:02 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=580,Updated=TO_TIMESTAMP('2014-04-25 11:40:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69880
;

-- Apr 25, 2014 11:40:02 AM GMT05:30
-- Import Budget AD Changes
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=590,Updated=TO_TIMESTAMP('2014-04-25 11:40:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=69881
;

-- Apr 25, 2014 1:50:22 PM GMT05:30
-- Import Budget menu problem
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,Parent_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_TIMESTAMP('2014-04-25 13:50:20','YYYY-MM-DD HH24:MI:SS'),100,'Y',53665,163,8,TO_TIMESTAMP('2014-04-25 13:50:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 25, 2014 2:45:14 PM GMT05:30
-- Import Budge import loader
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Created,CreatedBy,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,72737,50031,50560,0,TO_TIMESTAMP('2014-04-25 14:45:13','YYYY-MM-DD HH24:MI:SS'),100,'S','.','N',0,'Y','Campaign Value',110,11,TO_TIMESTAMP('2014-04-25 14:45:13','YYYY-MM-DD HH24:MI:SS'),100)
;

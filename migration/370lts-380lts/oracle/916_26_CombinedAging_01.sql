SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- 01/07/2011 4:39:28 PM
-- --
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('3',0,0,53323,TO_DATE('2011-07-01 16:39:20','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','Y','N','N','N',145,'T_CombinedAging','L','T_CombinedAging',TO_DATE('2011-07-01 16:39:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 01/07/2011 4:39:28 PM
-- --
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53323 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 01/07/2011 4:39:29 PM
-- --
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53434,TO_DATE('2011-07-01 16:39:28','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table T_CombinedAging',1,'Y','N','Y','Y','T_CombinedAging','N',1000000,TO_DATE('2011-07-01 16:39:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 01/07/2011 4:39:37 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61758,102,0,19,53323,129,'AD_Client_ID',TO_DATE('2011-07-01 16:39:36','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2011-07-01 16:39:36','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:37 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61758 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:39 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61759,113,0,19,53323,104,'AD_Org_ID',TO_DATE('2011-07-01 16:39:37','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2011-07-01 16:39:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:39 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61759 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:40 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61760,114,0,19,53323,'AD_PInstance_ID',TO_DATE('2011-07-01 16:39:39','YYYY-MM-DD HH24:MI:SS'),100,'Instance of the process','D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','Process Instance',0,TO_DATE('2011-07-01 16:39:39','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:40 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61760 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:42 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61761,1005,0,19,53323,'C_Activity_ID',TO_DATE('2011-07-01 16:39:40','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','D',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','N','Y','Activity',0,TO_DATE('2011-07-01 16:39:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:39:42 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61761 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:43 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61762,187,0,30,53323,'C_BPartner_ID',TO_DATE('2011-07-01 16:39:42','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','Y','Y','N','N','N','N','Business Partner ',0,TO_DATE('2011-07-01 16:39:42','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:43 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61762 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:45 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61763,1383,0,19,53323,'C_BP_Group_ID',TO_DATE('2011-07-01 16:39:43','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Group','D',22,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','N','N','N','N','Y','N','N','N','N','Y','Business Partner Group',0,TO_DATE('2011-07-01 16:39:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:45 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61763 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:46 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61764,550,0,19,53323,'C_Campaign_ID',TO_DATE('2011-07-01 16:39:45','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','D',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2011-07-01 16:39:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:39:46 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61764 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:48 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61765,193,0,19,53323,'C_Currency_ID',TO_DATE('2011-07-01 16:39:46','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record','D',22,'Indicates the Currency to be used when processing or reporting on this record','Y','N','N','N','N','Y','Y','N','N','N','N','Currency',0,TO_DATE('2011-07-01 16:39:46','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:48 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61765 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:49 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61766,1008,0,30,53323,'C_Invoice_ID',TO_DATE('2011-07-01 16:39:48','YYYY-MM-DD HH24:MI:SS'),100,'0','Invoice Identifier','D',22,'The Invoice Document.','Y','N','N','N','N','N','Y','N','N','N','N','Invoice',0,TO_DATE('2011-07-01 16:39:48','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:49 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61766 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:51 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61767,1995,0,19,53323,'C_InvoicePaySchedule_ID',TO_DATE('2011-07-01 16:39:49','YYYY-MM-DD HH24:MI:SS'),100,'0','Invoice Payment Schedule','D',22,'The Invoice Payment Schedule determines when partial payments are due.','Y','N','N','N','N','N','N','N','N','N','Y','Invoice Payment Schedule',0,TO_DATE('2011-07-01 16:39:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:51 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61767 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:52 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61768,208,0,19,53323,'C_Project_ID',TO_DATE('2011-07-01 16:39:51','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','D',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_DATE('2011-07-01 16:39:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:39:52 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61768 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:54 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61769,245,0,16,53323,'Created',TO_DATE('2011-07-01 16:39:52','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2011-07-01 16:39:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:54 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61769 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:55 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61770,246,0,18,110,53323,'CreatedBy',TO_DATE('2011-07-01 16:39:54','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2011-07-01 16:39:54','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:39:55 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61770 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:39:57 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61771,263,0,20,53323,'DateAcct',TO_DATE('2011-07-01 16:39:55','YYYY-MM-DD HH24:MI:SS'),100,'N','Accounting Date','D',1,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','N','N','N','N','N','N','N','N','N','Y','Account Date',0,TO_DATE('2011-07-01 16:39:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:39:57 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61771 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:04 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61772,1496,0,11,53323,'DaysDue',TO_DATE('2011-07-01 16:39:57','YYYY-MM-DD HH24:MI:SS'),100,'Number of days due (negative: due in number of days)','D',10,'Y','N','N','N','N','N','N','N','N','N','Y','Days due',0,TO_DATE('2011-07-01 16:39:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:40:04 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61772 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:05 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61773,2241,0,12,53323,'Due0',TO_DATE('2011-07-01 16:40:04','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due Today',0,TO_DATE('2011-07-01 16:40:04','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:05 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61773 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:07 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61774,2242,0,12,53323,'Due0_30',TO_DATE('2011-07-01 16:40:05','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due Today-30',0,TO_DATE('2011-07-01 16:40:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:07 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61774 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:08 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61775,2243,0,12,53323,'Due0_7',TO_DATE('2011-07-01 16:40:07','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due Today-7',0,TO_DATE('2011-07-01 16:40:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:08 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61775 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:10 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61776,2244,0,12,53323,'Due1_7',TO_DATE('2011-07-01 16:40:08','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due 1-7',0,TO_DATE('2011-07-01 16:40:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:10 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61776 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:11 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61777,2245,0,12,53323,'Due31_60',TO_DATE('2011-07-01 16:40:10','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due 31-60',0,TO_DATE('2011-07-01 16:40:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:11 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61777 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:13 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61778,2246,0,12,53323,'Due31_Plus',TO_DATE('2011-07-01 16:40:11','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due > 31',0,TO_DATE('2011-07-01 16:40:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:13 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61778 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:14 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61779,2247,0,12,53323,'Due61_90',TO_DATE('2011-07-01 16:40:13','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due 61-90',0,TO_DATE('2011-07-01 16:40:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:14 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61779 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:15 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61780,2248,0,12,53323,'Due61_Plus',TO_DATE('2011-07-01 16:40:14','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due > 61',0,TO_DATE('2011-07-01 16:40:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:15 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61780 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:17 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61781,2249,0,12,53323,'Due8_30',TO_DATE('2011-07-01 16:40:15','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due 8-30',0,TO_DATE('2011-07-01 16:40:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:17 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61781 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:18 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61782,2250,0,12,53323,'Due91_Plus',TO_DATE('2011-07-01 16:40:17','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Due > 91',0,TO_DATE('2011-07-01 16:40:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:18 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61782 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:20 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61783,1999,0,12,53323,'DueAmt',TO_DATE('2011-07-01 16:40:18','YYYY-MM-DD HH24:MI:SS'),100,'Amount of the payment due','D',22,'Full amount of the payment due','Y','N','N','N','N','Y','N','N','N','N','Y','Amount due',0,TO_DATE('2011-07-01 16:40:18','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:20 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61783 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:21 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61784,2000,0,15,53323,'DueDate',TO_DATE('2011-07-01 16:40:20','YYYY-MM-DD HH24:MI:SS'),100,'Date when the payment is due','D',7,'Date when the payment is due without deductions or discount','Y','N','N','N','N','Y','N','N','N','N','Y','Due Date',0,TO_DATE('2011-07-01 16:40:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:21 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61784 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:23 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61785,2044,0,12,53323,'InvoicedAmt',TO_DATE('2011-07-01 16:40:21','YYYY-MM-DD HH24:MI:SS'),100,'The amount invoiced','D',22,'The amount invoiced','Y','N','N','N','N','Y','N','N','N','N','Y','Invoiced Amount',0,TO_DATE('2011-07-01 16:40:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:23 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61785 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:24 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61786,348,0,20,53323,'IsActive',TO_DATE('2011-07-01 16:40:23','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2011-07-01 16:40:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:24 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61786 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:26 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61787,2260,0,20,53323,'IsListInvoices',TO_DATE('2011-07-01 16:40:24','YYYY-MM-DD HH24:MI:SS'),100,'Include List of Invoices','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','List Invoices',0,TO_DATE('2011-07-01 16:40:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:26 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61787 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:27 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61788,1106,0,20,53323,'IsSOTrx',TO_DATE('2011-07-01 16:40:26','YYYY-MM-DD HH24:MI:SS'),100,'This is a Sales Transaction','D',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','N','N','N','N','Y','N','N','N','N','Y','Sales Transaction',0,TO_DATE('2011-07-01 16:40:26','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:27 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61788 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:29 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61789,1526,0,12,53323,'OpenAmt',TO_DATE('2011-07-01 16:40:27','YYYY-MM-DD HH24:MI:SS'),100,'Open item amount','D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Open Amount',0,TO_DATE('2011-07-01 16:40:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:29 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61789 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:30 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61790,2251,0,12,53323,'PastDue1_30',TO_DATE('2011-07-01 16:40:29','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due 1-30',0,TO_DATE('2011-07-01 16:40:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:30 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61790 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:32 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61791,2252,0,12,53323,'PastDue1_7',TO_DATE('2011-07-01 16:40:30','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due 1-7',0,TO_DATE('2011-07-01 16:40:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:32 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61791 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:33 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61792,2253,0,12,53323,'PastDue31_60',TO_DATE('2011-07-01 16:40:32','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due 31-60',0,TO_DATE('2011-07-01 16:40:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:33 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61792 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:40 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61793,2254,0,12,53323,'PastDue31_Plus',TO_DATE('2011-07-01 16:40:33','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due > 31',0,TO_DATE('2011-07-01 16:40:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:40 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61793 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:42 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61794,2255,0,12,53323,'PastDue61_90',TO_DATE('2011-07-01 16:40:40','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due 61-90',0,TO_DATE('2011-07-01 16:40:40','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:42 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61794 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:43 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61795,2256,0,12,53323,'PastDue61_Plus',TO_DATE('2011-07-01 16:40:42','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due > 61',0,TO_DATE('2011-07-01 16:40:42','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:43 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61795 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:45 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61796,2257,0,12,53323,'PastDue8_30',TO_DATE('2011-07-01 16:40:43','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due 8-30',0,TO_DATE('2011-07-01 16:40:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:45 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61796 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:46 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61797,2258,0,12,53323,'PastDue91_Plus',TO_DATE('2011-07-01 16:40:45','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due > 91',0,TO_DATE('2011-07-01 16:40:45','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:46 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61797 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:48 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61798,2259,0,12,53323,'PastDueAmt',TO_DATE('2011-07-01 16:40:46','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Past Due',0,TO_DATE('2011-07-01 16:40:46','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:48 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61798 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:49 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61799,1434,0,15,53323,'StatementDate',TO_DATE('2011-07-01 16:40:48','YYYY-MM-DD HH24:MI:SS'),100,'Date of the statement','D',7,'The Statement Date field defines the date of the statement.','Y','N','N','N','N','Y','N','N','N','N','Y','Statement date',0,TO_DATE('2011-07-01 16:40:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:40:49 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61799 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:51 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61800,607,0,16,53323,'Updated',TO_DATE('2011-07-01 16:40:49','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2011-07-01 16:40:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:51 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61800 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:40:52 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61801,608,0,18,110,53323,'UpdatedBy',TO_DATE('2011-07-01 16:40:51','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2011-07-01 16:40:51','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 01/07/2011 4:40:52 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61801 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:41:16 PM
-- --
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=61771
;

-- 01/07/2011 4:41:16 PM
-- --
DELETE FROM AD_Column WHERE AD_Column_ID=61771
;

-- 01/07/2011 4:41:39 PM
-- --
UPDATE AD_Column SET AD_Element_ID=1136, ColumnName='TrxAmt', Description='Amount of a transaction', Help='The Transaction Amount indicates the amount for a single transaction.', Name='Transaction Amount',Updated=TO_DATE('2011-07-01 16:41:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=61785
;

-- 01/07/2011 4:41:39 PM
-- --
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=61785
;

-- 01/07/2011 4:41:39 PM
-- --
UPDATE AD_Field SET Name='Transaction Amount', Description='Amount of a transaction', Help='The Transaction Amount indicates the amount for a single transaction.' WHERE AD_Column_ID=61785 AND IsCentrallyMaintained='Y'
;

-- 01/07/2011 4:42:00 PM
-- --
UPDATE AD_Column SET AD_Element_ID=1983, ColumnName='ListSources', Description='List Report Line Sources', Help='List the Source Accounts for Summary Accounts selected', Name='List Sources',Updated=TO_DATE('2011-07-01 16:42:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=61787
;

-- 01/07/2011 4:42:00 PM
-- --
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=61787
;

-- 01/07/2011 4:42:00 PM
-- --
UPDATE AD_Field SET Name='List Sources', Description='List Report Line Sources', Help='List the Source Accounts for Summary Accounts selected' WHERE AD_Column_ID=61787 AND IsCentrallyMaintained='Y'
;

-- 01/07/2011 4:43:06 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61802,1384,0,19,53323,'C_Payment_ID',TO_DATE('2011-07-01 16:42:54','YYYY-MM-DD HH24:MI:SS'),100,'Payment identifier','D',10,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_DATE('2011-07-01 16:42:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:43:06 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61802 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:44:21 PM
-- --
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55220,0,'IsIncludePayments',TO_DATE('2011-07-01 16:44:04','YYYY-MM-DD HH24:MI:SS'),100,'Include payments in the aging report','D','Y','Include Payments','Include Payments',TO_DATE('2011-07-01 16:44:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 01/07/2011 4:44:22 PM
-- --
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55220 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 01/07/2011 4:44:54 PM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61803,55220,0,20,53323,'IsIncludePayments',TO_DATE('2011-07-01 16:44:42','YYYY-MM-DD HH24:MI:SS'),100,'Include payments in the aging report','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Include Payments',0,TO_DATE('2011-07-01 16:44:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 01/07/2011 4:44:54 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61803 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 01/07/2011 4:48:09 PM
-- --
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53035,53323,TO_DATE('2011-07-01 16:48:02','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','T_CombinedAging',TO_DATE('2011-07-01 16:48:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 07/07/2011 1:13:30 PM
-- --
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2011-07-07 13:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=61802
;

-- 07/07/2011 1:14:14 PM
-- --
CREATE TABLE T_CombinedAging (AD_Client_ID NUMBER(10) NOT NULL, AD_Org_ID NUMBER(10) NOT NULL, AD_PInstance_ID NUMBER(10) NOT NULL, C_Activity_ID NUMBER(10) DEFAULT NULL , C_BPartner_ID NUMBER(10) NOT NULL, C_BP_Group_ID NUMBER(10) NOT NULL, C_Campaign_ID NUMBER(10) DEFAULT NULL , C_Currency_ID NUMBER(10) NOT NULL, C_Invoice_ID NUMBER(10) DEFAULT 0, C_InvoicePaySchedule_ID NUMBER(10) DEFAULT 0, C_Payment_ID NUMBER(10) DEFAULT NULL , C_Project_ID NUMBER(10) DEFAULT NULL , Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, DaysDue NUMBER(10) DEFAULT NULL , Due0 NUMBER NOT NULL, Due0_30 NUMBER NOT NULL, Due0_7 NUMBER NOT NULL, Due1_7 NUMBER NOT NULL, Due31_60 NUMBER NOT NULL, Due31_Plus NUMBER NOT NULL, Due61_90 NUMBER NOT NULL, Due61_Plus NUMBER NOT NULL, Due8_30 NUMBER NOT NULL, Due91_Plus NUMBER NOT NULL, DueAmt NUMBER NOT NULL, DueDate DATE NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, IsIncludePayments CHAR(1) DEFAULT NULL  CHECK (IsIncludePayments IN ('Y','N')), IsSOTrx CHAR(1) CHECK (IsSOTrx IN ('Y','N')) NOT NULL, ListSources CHAR(1) CHECK (ListSources IN ('Y','N')) NOT NULL, OpenAmt NUMBER NOT NULL, PastDue1_30 NUMBER NOT NULL, PastDue1_7 NUMBER NOT NULL, PastDue31_60 NUMBER NOT NULL, PastDue31_Plus NUMBER NOT NULL, PastDue61_90 NUMBER NOT NULL, PastDue61_Plus NUMBER NOT NULL, PastDue8_30 NUMBER NOT NULL, PastDue91_Plus NUMBER NOT NULL, PastDueAmt NUMBER NOT NULL, StatementDate DATE NOT NULL, TrxAmt NUMBER NOT NULL, Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, CONSTRAINT T_CombinedAging_Key PRIMARY KEY (AD_PInstance_ID, C_BPartner_ID, C_Currency_ID, C_Invoice_ID, C_Payment_ID, C_InvoicePaySchedule_ID))
;
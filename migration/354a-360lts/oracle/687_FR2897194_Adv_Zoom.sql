-- 13.11.2009 14:59:58 MEZ
-- FR 2897194 Advanced Zoom and RelationTypes
INSERT INTO AD_Table (AD_Org_ID,AD_Client_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,AccessLevel,UpdatedBy) VALUES (0,0,53246,'N',TO_DATE('2009-11-13 14:59:55','YYYY-MM-DD HH24:MI:SS'),100,'Defines the sets of record pairs (and the conditions a given pair must fulfill to be part of one)','D','N','Y','N','Y','N','N','N',0,'Relation Type','L','AD_RelationType',TO_DATE('2009-11-13 14:59:55','YYYY-MM-DD HH24:MI:SS'),'7',100)
;

-- 13.11.2009 14:59:58 MEZ
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53246 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 13.11.2009 14:59:58 MEZ
INSERT INTO AD_Sequence (AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy,AD_Client_ID) VALUES (0,53355,TO_DATE('2009-11-13 14:59:58','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table AD_RelationType',1,'Y','N','Y','Y','AD_RelationType','N',1000000,TO_DATE('2009-11-13 14:59:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 13.11.2009 15:00:17 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,102,19,53246,129,'AD_Client_ID',TO_DATE('2009-11-13 15:00:16','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-11-13 15:00:16','YYYY-MM-DD HH24:MI:SS'),100,1,0,58571)
;

-- 13.11.2009 15:00:17 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58571 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:18 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,113,19,53246,104,'AD_Org_ID',TO_DATE('2009-11-13 15:00:17','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-11-13 15:00:17','YYYY-MM-DD HH24:MI:SS'),100,1,0,58572)
;

-- 13.11.2009 15:00:18 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58572 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:19 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,193,19,53246,'C_Currency_ID',TO_DATE('2009-11-13 15:00:18','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record','D',22,'Indicates the Currency to be used when processing or reporting on this record','Y','N','N','N','N','Y','N','N','N','N','Y','Currency',0,TO_DATE('2009-11-13 15:00:18','YYYY-MM-DD HH24:MI:SS'),100,1,0,58573)
;

-- 13.11.2009 15:00:19 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58573 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:20 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54070,'AD_RelationType_ID',TO_DATE('2009-11-13 15:00:19','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Relation Type','Relation Type',TO_DATE('2009-11-13 15:00:19','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:00:20 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54070 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:00:22 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,54070,13,53246,'AD_RelationType_ID',TO_DATE('2009-11-13 15:00:19','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','Relation Type',0,TO_DATE('2009-11-13 15:00:19','YYYY-MM-DD HH24:MI:SS'),100,1,0,58574)
;

-- 13.11.2009 15:00:22 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58574 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:23 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,245,16,53246,'Created',TO_DATE('2009-11-13 15:00:22','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-11-13 15:00:22','YYYY-MM-DD HH24:MI:SS'),100,1,0,58575)
;

-- 13.11.2009 15:00:23 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58575 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:24 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,110,246,18,53246,'CreatedBy',TO_DATE('2009-11-13 15:00:23','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-11-13 15:00:23','YYYY-MM-DD HH24:MI:SS'),100,1,0,58576)
;

-- 13.11.2009 15:00:24 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58576 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:25 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,275,10,53246,'Description',TO_DATE('2009-11-13 15:00:24','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-11-13 15:00:24','YYYY-MM-DD HH24:MI:SS'),100,1,0,58577)
;

-- 13.11.2009 15:00:25 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58577 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:26 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,348,20,53246,'IsActive',TO_DATE('2009-11-13 15:00:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-11-13 15:00:25','YYYY-MM-DD HH24:MI:SS'),100,1,0,58578)
;

-- 13.11.2009 15:00:26 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58578 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:27 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,469,10,53246,'Name',TO_DATE('2009-11-13 15:00:26','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',1,TO_DATE('2009-11-13 15:00:26','YYYY-MM-DD HH24:MI:SS'),100,1,0,58579)
;

-- 13.11.2009 15:00:27 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58579 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:28 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,607,16,53246,'Updated',TO_DATE('2009-11-13 15:00:27','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-11-13 15:00:27','YYYY-MM-DD HH24:MI:SS'),100,1,0,58580)
;

-- 13.11.2009 15:00:28 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58580 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:29 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,110,608,18,53246,'UpdatedBy',TO_DATE('2009-11-13 15:00:28','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-11-13 15:00:28','YYYY-MM-DD HH24:MI:SS'),100,1,0,58581)
;

-- 13.11.2009 15:00:29 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58581 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:00:57 MEZ
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=58573
;

-- 13.11.2009 15:00:57 MEZ
DELETE FROM AD_Column WHERE AD_Column_ID=58573
;

-- 13.11.2009 15:02:11 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54071,'AD_Reference_Source_ID',TO_DATE('2009-11-13 15:02:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Source Reference','Source Reference',TO_DATE('2009-11-13 15:02:08','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:02:11 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54071 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:02:32 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54072,'AD_Reference_Target_ID',TO_DATE('2009-11-13 15:02:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Target Reference','Target Reference',TO_DATE('2009-11-13 15:02:30','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:02:32 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54072 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:03:03 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54073,'Role_Source',TO_DATE('2009-11-13 15:02:58','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Source Role','Source Role',TO_DATE('2009-11-13 15:02:58','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:03:03 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54073 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:04:41 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54074,'IsDirected',TO_DATE('2009-11-13 15:04:41','YYYY-MM-DD HH24:MI:SS'),100,'Tells whether one "sees" the other end of the relation from each end or just from the source','D','Y','Directed','Directed',TO_DATE('2009-11-13 15:04:41','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:04:41 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54074 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:05:49 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53330,TO_DATE('2009-11-13 15:05:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','AD_Reference Table',TO_DATE('2009-11-13 15:05:49','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 13.11.2009 15:05:49 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53330 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 13.11.2009 15:06:48 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (130,129,0,TO_DATE('2009-11-13 15:06:48','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2009-11-13 15:06:48','YYYY-MM-DD HH24:MI:SS'),100,0,'AD_Reference.ValidationType=''T''
AND exists ( /* reference must have an active AD_Ref_Table entry */
 select * from AD_Ref_Table t 
 where t.AD_Reference_ID=AD_Reference.AD_Reference_ID
   and t.IsActive=''Y''
)',53330,102)
;

-- 13.11.2009 15:07:12 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53330,54071,18,53246,'AD_Reference_Source_ID',TO_DATE('2009-11-13 15:07:12','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Source Reference',0,TO_DATE('2009-11-13 15:07:12','YYYY-MM-DD HH24:MI:SS'),100,0,0,58582)
;

-- 13.11.2009 15:07:12 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58582 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:07:34 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53330,54072,18,53246,'AD_Reference_Target_ID',TO_DATE('2009-11-13 15:07:33','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Target Reference',0,TO_DATE('2009-11-13 15:07:33','YYYY-MM-DD HH24:MI:SS'),100,0,0,58583)
;

-- 13.11.2009 15:07:34 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58583 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:08:12 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,54074,20,53246,'IsDirected',TO_DATE('2009-11-13 15:08:11','YYYY-MM-DD HH24:MI:SS'),100,'N','Tells whether one "sees" the other end of the relation from each end or just from the source','U',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Directed',0,TO_DATE('2009-11-13 15:08:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,58584)
;

-- 13.11.2009 15:08:12 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58584 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:09:50 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53331,TO_DATE('2009-11-13 15:09:50','YYYY-MM-DD HH24:MI:SS'),100,'Defines the possible "roles" a the records of a relation can have','D','Y','N','AD_RelationType Role',TO_DATE('2009-11-13 15:09:50','YYYY-MM-DD HH24:MI:SS'),100,'L',0)
;

-- 13.11.2009 15:09:50 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53331 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 13.11.2009 15:10:12 MEZ
INSERT INTO AD_Ref_List (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53331,53552,TO_DATE('2009-11-13 15:10:11','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Order',TO_DATE('2009-11-13 15:10:11','YYYY-MM-DD HH24:MI:SS'),100,0,'Order')
;

-- 13.11.2009 15:10:12 MEZ
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53552 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 13.11.2009 15:10:21 MEZ
INSERT INTO AD_Ref_List (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53331,53553,TO_DATE('2009-11-13 15:10:21','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Invoice',TO_DATE('2009-11-13 15:10:21','YYYY-MM-DD HH24:MI:SS'),100,0,'Invoice')
;

-- 13.11.2009 15:10:21 MEZ
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53553 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 13.11.2009 15:10:58 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53331,54073,17,53246,'Role_Source',TO_DATE('2009-11-13 15:10:57','YYYY-MM-DD HH24:MI:SS'),100,'U',50,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Source Role',0,TO_DATE('2009-11-13 15:10:57','YYYY-MM-DD HH24:MI:SS'),100,0,0,58585)
;

-- 13.11.2009 15:10:58 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58585 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:12:02 MEZ
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,54075,'Role_Target',TO_DATE('2009-11-13 15:12:01','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Target Role','Target Role',TO_DATE('2009-11-13 15:12:01','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 13.11.2009 15:12:02 MEZ
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54075 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 13.11.2009 15:12:35 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53331,54075,17,53246,'Role_Target',TO_DATE('2009-11-13 15:12:35','YYYY-MM-DD HH24:MI:SS'),100,'U',50,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Target Role',0,TO_DATE('2009-11-13 15:12:35','YYYY-MM-DD HH24:MI:SS'),100,0,0,58586)
;

-- 13.11.2009 15:12:35 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58586 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:12:38 MEZ
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-11-13 15:12:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58586
;

-- 13.11.2009 15:15:10 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53332,TO_DATE('2009-11-13 15:15:09','YYYY-MM-DD HH24:MI:SS'),100,'"Type" of a relation type','D','For now we only have implicit realtion types, i.e. the record pairs are defined by the rule itself. In future we would like to have explicit type also. An explizit type just defines a template, the actual pairs can be added by a user or by the system itself.','Y','N','AD_RelationType Type',TO_DATE('2009-11-13 15:15:09','YYYY-MM-DD HH24:MI:SS'),100,'L',0)
;

-- 13.11.2009 15:15:10 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53332 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 13.11.2009 15:15:32 MEZ
INSERT INTO AD_Ref_List (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53332,53554,TO_DATE('2009-11-13 15:15:31','YYYY-MM-DD HH24:MI:SS'),100,NULL,'D','Y','Implicit',TO_DATE('2009-11-13 15:15:31','YYYY-MM-DD HH24:MI:SS'),100,0,'I')
;

-- 13.11.2009 15:15:32 MEZ
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53554 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 13.11.2009 15:16:19 MEZ
INSERT INTO AD_Ref_List (AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53332,53555,TO_DATE('2009-11-13 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Explicit',TO_DATE('2009-11-13 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,0,'E')
;

-- 13.11.2009 15:16:19 MEZ
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53555 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 13.11.2009 15:16:40 MEZ
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53332,600,17,53246,'Type',TO_DATE('2009-11-13 15:16:39','YYYY-MM-DD HH24:MI:SS'),100,'Type of Validation (SQL, Java Script, Java Language)','U',1,'The Type indicates the type of validation that will occur.  This can be SQL, Java Script or Java Language.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Type',0,TO_DATE('2009-11-13 15:16:39','YYYY-MM-DD HH24:MI:SS'),100,0,0,58587)
;

-- 13.11.2009 15:16:40 MEZ
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58587 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 13.11.2009 15:16:56 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:16:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58587
;

-- 13.11.2009 15:16:59 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:16:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58586
;

-- 13.11.2009 15:17:02 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:17:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58585
;

-- 13.11.2009 15:17:05 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:17:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58584
;

-- 13.11.2009 15:17:11 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58583
;

-- 13.11.2009 15:17:33 MEZ
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-11-13 15:17:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58582
;

-- 13.11.2009 15:18:44 MEZ
INSERT INTO AD_Window (AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,AD_Client_ID,WinWidth) VALUES (0,53102,TO_DATE('2009-11-13 15:18:43','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','Y','Relation Type','N',TO_DATE('2009-11-13 15:18:43','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0,0)
;

-- 13.11.2009 15:18:44 MEZ
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53102 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- 13.11.2009 15:19:04 MEZ
UPDATE AD_Table SET AD_Window_ID=53102,Updated=TO_DATE('2009-11-13 15:19:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53246
;

-- 13.11.2009 15:19:44 MEZ
INSERT INTO AD_Tab (AD_Org_ID,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,AD_Client_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created) VALUES (0,100,'D','N','N','Y','N','N','Y','N','N','N','N','Relation Type','N',10,0,TO_DATE('2009-11-13 15:19:43','YYYY-MM-DD HH24:MI:SS'),100,0,53285,53246,53102,TO_DATE('2009-11-13 15:19:43','YYYY-MM-DD HH24:MI:SS'))
;

-- 13.11.2009 15:19:44 MEZ
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, Description,Help,Name,CommitWarning, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.Description,t.Help,t.Name,t.CommitWarning, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53285 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- 13.11.2009 15:20:14 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58578,0,53285,TO_DATE('2009-11-13 15:20:13','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-11-13 15:20:13','YYYY-MM-DD HH24:MI:SS'),0,100,58064)
;

-- 13.11.2009 15:20:14 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58064 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:15 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58571,0,53285,TO_DATE('2009-11-13 15:20:14','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-11-13 15:20:14','YYYY-MM-DD HH24:MI:SS'),0,100,58065)
;

-- 13.11.2009 15:20:15 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58065 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:15 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58577,0,53285,TO_DATE('2009-11-13 15:20:15','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-11-13 15:20:15','YYYY-MM-DD HH24:MI:SS'),0,100,58066)
;

-- 13.11.2009 15:20:15 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58066 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:16 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58584,0,53285,TO_DATE('2009-11-13 15:20:15','YYYY-MM-DD HH24:MI:SS'),100,'Tells whether one "sees" the other end of the relation from each end or just from the source',1,'D','Y','Y','Y','N','N','N','N','N','Directed',TO_DATE('2009-11-13 15:20:15','YYYY-MM-DD HH24:MI:SS'),0,100,58067)
;

-- 13.11.2009 15:20:16 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58067 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:16 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58579,0,53285,TO_DATE('2009-11-13 15:20:16','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-11-13 15:20:16','YYYY-MM-DD HH24:MI:SS'),0,100,58068)
;

-- 13.11.2009 15:20:16 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58068 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:17 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58572,0,53285,TO_DATE('2009-11-13 15:20:16','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-11-13 15:20:16','YYYY-MM-DD HH24:MI:SS'),0,100,58069)
;

-- 13.11.2009 15:20:17 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58069 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:17 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58574,0,53285,TO_DATE('2009-11-13 15:20:17','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','Relation Type',TO_DATE('2009-11-13 15:20:17','YYYY-MM-DD HH24:MI:SS'),0,100,58070)
;

-- 13.11.2009 15:20:17 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58070 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:18 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58582,0,53285,TO_DATE('2009-11-13 15:20:17','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Source Reference',TO_DATE('2009-11-13 15:20:17','YYYY-MM-DD HH24:MI:SS'),0,100,58071)
;

-- 13.11.2009 15:20:18 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58071 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:19 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58585,0,53285,TO_DATE('2009-11-13 15:20:18','YYYY-MM-DD HH24:MI:SS'),100,50,'D','Y','Y','Y','N','N','N','N','N','Source Role',TO_DATE('2009-11-13 15:20:18','YYYY-MM-DD HH24:MI:SS'),0,100,58072)
;

-- 13.11.2009 15:20:19 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58072 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:19 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58583,0,53285,TO_DATE('2009-11-13 15:20:19','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Target Reference',TO_DATE('2009-11-13 15:20:19','YYYY-MM-DD HH24:MI:SS'),0,100,58073)
;

-- 13.11.2009 15:20:19 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58073 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:20 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58586,0,53285,TO_DATE('2009-11-13 15:20:19','YYYY-MM-DD HH24:MI:SS'),100,50,'D','Y','Y','Y','N','N','N','N','N','Target Role',TO_DATE('2009-11-13 15:20:19','YYYY-MM-DD HH24:MI:SS'),0,100,58074)
;

-- 13.11.2009 15:20:20 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58074 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:20:20 MEZ
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (58587,0,53285,TO_DATE('2009-11-13 15:20:20','YYYY-MM-DD HH24:MI:SS'),100,'Type of Validation (SQL, Java Script, Java Language)',1,'D','The Type indicates the type of validation that will occur.  This can be SQL, Java Script or Java Language.','Y','Y','Y','N','N','N','N','N','Type',TO_DATE('2009-11-13 15:20:20','YYYY-MM-DD HH24:MI:SS'),0,100,58075)
;

-- 13.11.2009 15:20:20 MEZ
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58075 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=58065
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=58069
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=58068
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58064
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58066
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=58067
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=58075
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58071
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58072
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58073
;

-- 13.11.2009 15:21:25 MEZ
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=58074
;

-- 13.11.2009 15:21:38 MEZ
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-11-13 15:21:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58064
;

-- 13.11.2009 15:21:52 MEZ
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-11-13 15:21:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58072
;

-- 13.11.2009 15:21:57 MEZ
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-11-13 15:21:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58074
;

-- 13.11.2009 15:22:03 MEZ
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-11-13 15:22:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58075
;

-- 13.11.2009 15:22:46 MEZ
INSERT INTO AD_Menu (AD_Org_ID,AD_Client_ID,AD_Menu_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,Action,UpdatedBy) VALUES (0,0,53251,53102,TO_DATE('2009-11-13 15:22:45','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Relation Type',TO_DATE('2009-11-13 15:22:45','YYYY-MM-DD HH24:MI:SS'),'W',100)
;

-- 13.11.2009 15:22:46 MEZ
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53251 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 13.11.2009 15:22:46 MEZ
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53251, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53251)
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53251
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=155
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=156
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=175
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=157
;

-- 13.11.2009 15:22:54 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=552
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53251
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=155
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=156
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=175
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=157
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53251
;

-- 13.11.2009 15:22:59 MEZ
UPDATE AD_TreeNodeMM SET Parent_ID=218, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=552
;

-- 13.11.2009 15:25:11 MEZ
UPDATE AD_Column SET DefaultValue='I',Updated=TO_DATE('2009-11-13 15:25:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58587
;

-- 13.11.2009 15:26:25 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53333,TO_DATE('2009-11-13 15:26:25','YYYY-MM-DD HH24:MI:SS'),100,'Finds C_OrderIDs for a given C_Invoice_ID','D','Y','N','RelType C_Invoice_ID->C_Order',TO_DATE('2009-11-13 15:26:25','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 13.11.2009 15:26:25 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53333 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 13.11.2009 15:27:09 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (2169,2161,0,TO_DATE('2009-11-13 15:27:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_DATE('2009-11-13 15:27:09','YYYY-MM-DD HH24:MI:SS'),100,0,'C_Order_ID IN (
  select o.c_order_id from c_order o
    left join c_orderline ol on o.c_order_id = ol.c_order_id
    left join c_invoiceline il on ol.c_orderline_id = il.c_orderline_id
  where il.C_Invoice_ID=@C_Invoice_ID@
)',53333,259)
;

-- 13.11.2009 15:27:40 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53334,TO_DATE('2009-11-13 15:27:40','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType C_Order_ID->C_Invoice',TO_DATE('2009-11-13 15:27:40','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 13.11.2009 15:27:40 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53334 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 13.11.2009 15:28:08 MEZ
UPDATE AD_Reference SET Description='Finds C_Invoice_IDs for a given C_C_Order_ID',Updated=TO_DATE('2009-11-13 15:28:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- 13.11.2009 15:28:08 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53334
;

-- 13.11.2009 15:28:14 MEZ
UPDATE AD_Reference SET Description='Finds C_Order_IDs for a given C_Invoice_ID',Updated=TO_DATE('2009-11-13 15:28:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53333
;

-- 13.11.2009 15:28:14 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53333
;

-- 13.11.2009 15:29:23 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (3492,3484,0,TO_DATE('2009-11-13 15:29:23','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_DATE('2009-11-13 15:29:23','YYYY-MM-DD HH24:MI:SS'),100,0,'C_Invoice_ID IN (
  select i.C_Invoice_ID from C_Invoice i
    left join C_InvoiceLine il on il.C_Invoice_ID = i.C_Invoice_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@
)',53334,318)
;


-- 13.11.2009 15:30:58 MEZ
CREATE TABLE AD_RelationType (AD_Client_ID NUMBER(10) NOT NULL, AD_Org_ID NUMBER(10) NOT NULL, AD_Reference_Source_ID NUMBER(10) DEFAULT NULL , AD_Reference_Target_ID NUMBER(10) DEFAULT NULL , AD_RelationType_ID NUMBER(10) NOT NULL, Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, Description NVARCHAR2(255) DEFAULT NULL , IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, IsDirected CHAR(1) DEFAULT 'N' CHECK (IsDirected IN ('Y','N')) NOT NULL, Name NVARCHAR2(60) NOT NULL, Role_Source NVARCHAR2(50) NOT NULL, Role_Target NVARCHAR2(50) NOT NULL, Type CHAR(1) DEFAULT 'I' NOT NULL, Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, CONSTRAINT AD_RelationType_Key PRIMARY KEY (AD_RelationType_ID))
;

-- 13.11.2009 15:49:25 MEZ
INSERT INTO AD_Message (AD_Org_ID,AD_Message_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53088,TO_DATE('2009-11-13 15:49:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','PO {0} has {1} key columns. Needs to have exactly one.','E',TO_DATE('2009-11-13 15:49:24','YYYY-MM-DD HH24:MI:SS'),100,0,'MRelationType_Err_KeyColumns_2P')
;

-- 13.11.2009 15:49:25 MEZ
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53088 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 13.11.2009 15:51:17 MEZ
INSERT INTO AD_Message (AD_Org_ID,AD_Message_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53089,TO_DATE('2009-11-13 15:51:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Neither reference {0} nor table {1} have an AD_Window_ID. IsSOTrx: {2}','E',TO_DATE('2009-11-13 15:51:16','YYYY-MM-DD HH24:MI:SS'),100,0,'MRelationType_Err_Window_3P')
;

-- 13.11.2009 15:51:17 MEZ
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53089 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16.11.2009 17:42:58 MEZ
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-11-16 17:42:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58585
;

-- 16.11.2009 17:43:02 MEZ
ALTER TABLE AD_RelationType MODIFY Role_Source NVARCHAR2(50) DEFAULT NULL 
;

-- 16.11.2009 17:43:02 MEZ
ALTER TABLE AD_RelationType MODIFY Role_Source NULL
;

-- 16.11.2009 17:43:17 MEZ
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-11-16 17:43:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58586
;

-- 16.11.2009 17:43:18 MEZ
ALTER TABLE AD_RelationType MODIFY Role_Target NVARCHAR2(50) DEFAULT NULL 
;

-- 16.11.2009 17:43:18 MEZ
ALTER TABLE AD_RelationType MODIFY Role_Target NULL
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Element SET Description='If set, this role will be used as label for the zoom target instead of the zoom target''s window name',Updated=TO_DATE('2009-11-16 17:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Column SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom target instead of the zoom target''s window name', Help=NULL WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom target instead of the zoom target''s window name', Help=NULL, AD_Element_ID=54075 WHERE UPPER(ColumnName)='ROLE_TARGET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom target instead of the zoom target''s window name', Help=NULL WHERE AD_Element_ID=54075 AND IsCentrallyMaintained='Y'
;

-- 16.11.2009 17:45:03 MEZ
UPDATE AD_Field SET Name='Target Role', Description='If set, this role will be used as label for the zoom target instead of the zoom target''s window name', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54075) AND IsCentrallyMaintained='Y'
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Element SET Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name',Updated=TO_DATE('2009-11-16 17:45:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Column SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Element_ID=54075
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL, AD_Element_ID=54075 WHERE UPPER(ColumnName)='ROLE_TARGET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Target', Name='Target Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Element_ID=54075 AND IsCentrallyMaintained='Y'
;

-- 16.11.2009 17:45:25 MEZ
UPDATE AD_Field SET Name='Target Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54075) AND IsCentrallyMaintained='Y'
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Element SET Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name',Updated=TO_DATE('2009-11-16 17:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54073
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54073
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Column SET ColumnName='Role_Source', Name='Source Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Element_ID=54073
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Source', Name='Source Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL, AD_Element_ID=54073 WHERE UPPER(ColumnName)='ROLE_SOURCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Process_Para SET ColumnName='Role_Source', Name='Source Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Element_ID=54073 AND IsCentrallyMaintained='Y'
;

-- 16.11.2009 17:45:40 MEZ
UPDATE AD_Field SET Name='Source Role', Description='If set, this role will be used as label for the zoom destination instead of the destinations''s window name', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54073) AND IsCentrallyMaintained='Y'
;


-- 13.11.2009 15:31:34 MEZ
INSERT INTO AD_RelationType (AD_Org_ID,AD_Client_ID,AD_Reference_Target_ID,AD_RelationType_ID,Created,CreatedBy,IsActive,IsDirected,Name,Role_Source,Role_Target,Type,Updated,UpdatedBy,AD_Reference_Source_ID) VALUES (0,0,53334,50001,TO_DATE('2009-11-13 15:31:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Order<->Invoice',NULL,NULL,'I',TO_DATE('2009-11-13 15:31:33','YYYY-MM-DD HH24:MI:SS'),100,53333)
;

-- Jun 29, 2012 9:57:04 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63484,53905,0,19,50006,'AD_View_ID',TO_DATE('2012-06-29 09:56:57','YYYY-MM-DD HH24:MI:SS'),100,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','View',0,TO_DATE('2012-06-29 09:56:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 29, 2012 9:57:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63484 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 29, 2012 9:57:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_Package_Exp_Detail ADD AD_View_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 29, 2012 9:57:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63485,53902,0,19,50006,'AD_Browse_ID',TO_DATE('2012-06-29 09:57:28','YYYY-MM-DD HH24:MI:SS'),100,'U',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Smart Browse',0,TO_DATE('2012-06-29 09:57:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 29, 2012 9:57:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63485 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 29, 2012 9:57:31 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_Package_Exp_Detail ADD AD_Browse_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 29, 2012 9:57:43 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_Package_Exp_Detail MODIFY AD_Browse_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 29, 2012 9:59:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63485,64590,0,50006,TO_DATE('2012-06-29 09:59:10','YYYY-MM-DD HH24:MI:SS'),100,22,'U','Y','Y','Y','N','N','N','N','N','Smart Browse',TO_DATE('2012-06-29 09:59:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 9:59:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64590 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 9:59:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63484,64591,0,50006,TO_DATE('2012-06-29 09:59:11','YYYY-MM-DD HH24:MI:SS'),100,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','Y','N','N','N','N','N','View',TO_DATE('2012-06-29 09:59:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 9:59:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64591 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:00:32 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53776,50004,TO_DATE('2012-06-29 10:00:30','YYYY-MM-DD HH24:MI:SS'),100,'EE07','Y','Smart Browse',TO_DATE('2012-06-29 10:00:30','YYYY-MM-DD HH24:MI:SS'),100,'SB')
;

-- Jun 29, 2012 10:00:32 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53776 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jun 29, 2012 10:01:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53777,50004,TO_DATE('2012-06-29 10:01:02','YYYY-MM-DD HH24:MI:SS'),100,'EE07','Y','Smart View',TO_DATE('2012-06-29 10:01:02','YYYY-MM-DD HH24:MI:SS'),100,'SV')
;

-- Jun 29, 2012 10:01:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53777 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jun 29, 2012 10:01:42 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Type@=SV',Updated=TO_DATE('2012-06-29 10:01:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64591
;

-- Jun 29, 2012 10:01:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Type@=SB',Updated=TO_DATE('2012-06-29 10:01:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64590
;

-- Jun 29, 2012 10:05:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63486,53905,0,19,129,'AD_View_ID',TO_DATE('2012-06-29 10:05:19','YYYY-MM-DD HH24:MI:SS'),100,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','View',0,TO_DATE('2012-06-29 10:05:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 29, 2012 10:05:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63486 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 29, 2012 10:05:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_WF_Node ADD AD_View_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 29, 2012 10:05:41 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63487,53902,0,19,129,'AD_Browse_ID',TO_DATE('2012-06-29 10:05:41','YYYY-MM-DD HH24:MI:SS'),100,'U',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Smart Browse',0,TO_DATE('2012-06-29 10:05:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 29, 2012 10:05:41 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63487 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 29, 2012 10:05:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_WF_Node ADD AD_Browse_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 29, 2012 10:05:50 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Column SET EntityType='EE07',Updated=TO_DATE('2012-06-29 10:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63487
;

-- Jun 29, 2012 10:06:43 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53309,64592,0,122,TO_DATE('2012-06-29 10:06:42','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',10,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_DATE('2012-06-29 10:06:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:43 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64592 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53304,64593,0,122,TO_DATE('2012-06-29 10:06:43','YYYY-MM-DD HH24:MI:SS'),100,1,'EE01','Y','Y','Y','N','N','N','N','N','Is Milestone',TO_DATE('2012-06-29 10:06:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64593 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53305,64594,0,122,TO_DATE('2012-06-29 10:06:44','YYYY-MM-DD HH24:MI:SS'),100,1,'EE01','Y','Y','Y','N','N','N','N','N','Is Subcontracting',TO_DATE('2012-06-29 10:06:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64594 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53307,64595,0,122,TO_DATE('2012-06-29 10:06:44','YYYY-MM-DD HH24:MI:SS'),100,22,'EE01','Y','Y','Y','N','N','N','N','N','Moving Time',TO_DATE('2012-06-29 10:06:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64595 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53308,64596,0,122,TO_DATE('2012-06-29 10:06:45','YYYY-MM-DD HH24:MI:SS'),100,'Overlap Units are number of units that must be completed before they are moved the next activity',22,'EE01','When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.','Y','Y','Y','N','N','N','N','N','Overlap Units',TO_DATE('2012-06-29 10:06:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64596 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:46 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53310,64597,0,122,TO_DATE('2012-06-29 10:06:45','YYYY-MM-DD HH24:MI:SS'),100,'Queue time is the time a job waits at a work center before begin handled.',22,'EE01','Queuing time has no implication on costs, but on Capacity Requirement Planning (CRP) to calculate the total time needed to manufacture a product.','Y','Y','Y','N','N','N','N','N','Queuing Time',TO_DATE('2012-06-29 10:06:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:46 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64597 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:46 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53311,64598,0,122,TO_DATE('2012-06-29 10:06:46','YYYY-MM-DD HH24:MI:SS'),100,'Resource',10,'EE01','Y','Y','Y','N','N','N','N','N','Resource',TO_DATE('2012-06-29 10:06:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:46 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64598 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:47 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53312,64599,0,122,TO_DATE('2012-06-29 10:06:46','YYYY-MM-DD HH24:MI:SS'),100,'Setup time before starting Production',22,'EE01','Once per operation','Y','Y','Y','N','N','N','N','N','Setup Time',TO_DATE('2012-06-29 10:06:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:47 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64599 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63487,64600,0,122,TO_DATE('2012-06-29 10:06:47','YYYY-MM-DD HH24:MI:SS'),100,22,'EE07','Y','Y','Y','N','N','N','N','N','Smart Browse',TO_DATE('2012-06-29 10:06:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64600 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53306,64601,0,122,TO_DATE('2012-06-29 10:06:48','YYYY-MM-DD HH24:MI:SS'),100,'The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.',14,'EE01','When Units by Cycles are defined the duration time is the total of time to manufactured the units','Y','Y','Y','N','N','N','N','N','Units by Cycles',TO_DATE('2012-06-29 10:06:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64601 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:49 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53313,64602,0,122,TO_DATE('2012-06-29 10:06:48','YYYY-MM-DD HH24:MI:SS'),100,'Valid from including this date (first day)',7,'EE01','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','N','Valid from',TO_DATE('2012-06-29 10:06:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:49 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64602 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:49 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53303,64603,0,122,TO_DATE('2012-06-29 10:06:49','YYYY-MM-DD HH24:MI:SS'),100,'Valid to including this date (last day)',7,'EE01','The Valid To date indicates the last day of a date range','Y','Y','Y','N','N','N','N','N','Valid to',TO_DATE('2012-06-29 10:06:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:49 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64603 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:50 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63486,64604,0,122,TO_DATE('2012-06-29 10:06:49','YYYY-MM-DD HH24:MI:SS'),100,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','Y','N','N','N','N','N','View',TO_DATE('2012-06-29 10:06:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:50 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64604 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:06:51 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56776,64605,0,122,TO_DATE('2012-06-29 10:06:50','YYYY-MM-DD HH24:MI:SS'),100,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent',10,'EE01','ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities','Y','Y','Y','N','N','N','N','N','Yield %',TO_DATE('2012-06-29 10:06:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:06:51 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64605 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64592
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64593
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64594
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64595
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64596
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64597
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64598
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64599
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64601
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64602
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64603
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=64605
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=64600
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=64604
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=10091
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=10090
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12615
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12614
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=12616
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=8761
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=1269
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=1268
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=8766
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=2578
;

-- Jun 29, 2012 10:07:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=8771
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=10180
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=10089
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=10920
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=10921
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=8765
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=8767
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=8769
;

-- Jun 29, 2012 10:07:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=8768
;

-- Jun 29, 2012 10:08:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Action@=SB',Updated=TO_DATE('2012-06-29 10:08:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64600
;

-- Jun 29, 2012 10:08:31 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Action@=SV',Updated=TO_DATE('2012-06-29 10:08:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64604
;

-- Jun 29, 2012 10:09:47 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53778,302,TO_DATE('2012-06-29 10:09:46','YYYY-MM-DD HH24:MI:SS'),100,'EE07','Y','Smart View',TO_DATE('2012-06-29 10:09:46','YYYY-MM-DD HH24:MI:SS'),100,'Q')
;

-- Jun 29, 2012 10:09:47 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53778 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jun 29, 2012 10:12:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53779,302,TO_DATE('2012-06-29 10:12:05','YYYY-MM-DD HH24:MI:SS'),100,'EE07','Y','Smart Browse',TO_DATE('2012-06-29 10:12:05','YYYY-MM-DD HH24:MI:SS'),100,'S')
;

-- Jun 29, 2012 10:12:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53779 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jun 29, 2012 10:12:43 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Action@=S',Updated=TO_DATE('2012-06-29 10:12:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64600
;

-- Jun 29, 2012 10:12:49 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET DisplayLogic='@Action@=Q',Updated=TO_DATE('2012-06-29 10:12:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64604
;

-- Jun 29, 2012 10:16:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=64590
;

-- Jun 29, 2012 10:16:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=64591
;

-- Jun 29, 2012 10:16:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=50117
;

-- Jun 29, 2012 10:16:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53284
;

-- Jun 29, 2012 10:16:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=57418
;

-- Jun 29, 2012 10:20:22 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_WF_Node SET Action='Q',Updated=TO_DATE('2012-06-29 10:20:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=139
;


-- Jun 27, 2012 11:28:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55507,0,18,251,'Axis_Column_ID',TO_DATE('2012-06-27 11:28:39','YYYY-MM-DD HH24:MI:SS'),100,'Axis the link column.','EE07',22,'Axis Column defines the base column to show the records on this table as columns inside the browser','Y','Axis Column','Axis Column',TO_DATE('2012-06-27 11:28:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 27, 2012 11:28:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55507 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 27, 2012 11:30:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53437,TO_DATE('2012-06-27 11:30:43','YYYY-MM-DD HH24:MI:SS'),100,'View Column selection','EE07','Y','N','AD_View_Column',TO_DATE('2012-06-27 11:30:43','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Jun 27, 2012 11:30:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53437 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Jun 27, 2012 11:31:12 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,58106,58100,0,53437,53232,TO_DATE('2012-06-27 11:31:12','YYYY-MM-DD HH24:MI:SS'),100,'EE07','Y','N',TO_DATE('2012-06-27 11:31:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 27, 2012 11:31:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55508,0,18,53437,'Axis_Parent_Column_ID',TO_DATE('2012-06-27 11:31:27','YYYY-MM-DD HH24:MI:SS'),100,'The link Axis column view on the parent key','EE07',22,'Axis Parent Column filters the records used by Axis Column, the values for the filter are obtained from the context of the Field Browser defined as query criteria
context.','Y','Axis Parent Column','Axis Parent Column',TO_DATE('2012-06-27 11:31:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 27, 2012 11:31:48 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55508 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 27, 2012 11:35:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63218,55507,0,18,53437,53223,'Axis_Column_ID',TO_DATE('2012-06-27 11:35:07','YYYY-MM-DD HH24:MI:SS'),100,'Axis the link column.','EE07',22,'Axis Column defines the base column to show the records on this table as columns inside the browser','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Axis Column',0,TO_DATE('2012-06-27 11:35:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 27, 2012 11:35:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63218 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 27, 2012 11:35:16 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
ALTER TABLE AD_Browse_Field ADD Axis_Column_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 27, 2012 11:35:35 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63219,55508,0,18,53437,53223,'Axis_Parent_Column_ID',TO_DATE('2012-06-27 11:35:31','YYYY-MM-DD HH24:MI:SS'),100,'The link Axis column view on the parent key','EE07',22,'Axis Parent Column filters the records used by Axis Column, the values for the filter are obtained from the context of the Field Browser defined as query criteria
context.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Axis Parent Column',0,TO_DATE('2012-06-27 11:35:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 27, 2012 11:35:35 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63219 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 27, 2012 11:35:37 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
ALTER TABLE AD_Browse_Field ADD Axis_Parent_Column_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 27, 2012 11:37:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63218,64393,0,53247,TO_DATE('2012-06-27 11:36:22','YYYY-MM-DD HH24:MI:SS'),100,'Axis the link column.',22,'EE07','Axis Column defines the base column to show the records on this table as columns inside the browser','Y','Y','Y','N','N','N','N','N','Axis Column',TO_DATE('2012-06-27 11:36:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 27, 2012 11:37:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64393 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 27, 2012 11:37:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63219,64394,0,53247,TO_DATE('2012-06-27 11:37:07','YYYY-MM-DD HH24:MI:SS'),100,'The link Axis column view on the parent key',22,'EE07','Axis Parent Column filters the records used by Axis Column, the values for the filter are obtained from the context of the Field Browser defined as query criteria
context.','Y','Y','Y','N','N','N','N','N','Axis Parent Column',TO_DATE('2012-06-27 11:37:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 27, 2012 11:37:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64394 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 27, 2012 11:38:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Jun 27, 2012 11:38:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Jun 27, 2012 11:38:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Jun 27, 2012 11:38:44 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET DisplayLogic='@AD_Reference_ID@=18 | @AD_Reference_ID@=17 | @AD_Reference_ID@=19  | @AD_Reference_ID@=20',Updated=TO_DATE('2012-06-27 11:38:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64393
;

-- Jun 27, 2012 11:39:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET DisplayLogic='@Axis_Column_ID@ ! 0',Updated=TO_DATE('2012-06-27 11:39:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64394
;

-- Jun 27, 2012 11:39:45 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-06-27 11:39:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64394
;

-- Jun 29, 2012 10:34:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Column SET AD_Reference_Value_ID=53437,Updated=TO_DATE('2012-06-29 22:34:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63218
;

-- Jun 29, 2012 10:37:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52125,'AD_View_Column_ID IN (SELECT vc.AD_View_Column_ID FROM AD_Browse_Field bf INNER JOIN AD_View_Column vc ON (vc.AD_View_Column_ID=bf.AD_View_Column_ID) WHERE bf.AD_Browse_ID=@AD_Browse_ID@)',TO_DATE('2012-06-29 22:37:01','YYYY-MM-DD HH24:MI:SS'),100,'View must be previously defined','EE07','Y','AD_View_Column in AD_Browse','S',TO_DATE('2012-06-29 22:37:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:37:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Column SET AD_Val_Rule_ID=52125,Updated=TO_DATE('2012-06-29 22:37:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63218
;

-- Jun 29, 2012 10:40:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52126,'AD_View_Column_ID IN (SELECT vc.AD_View_Column_ID FROM AD_Browse_Field bf INNER JOIN AD_View_Column vc ON (vc.AD_View_Column_ID=bf.AD_View_Column_ID) WHERE bf.AD_Browse_ID=@AD_Browse_ID@ AND bf.IsQueryCriteria=''Y'')',TO_DATE('2012-06-29 22:40:05','YYYY-MM-DD HH24:MI:SS'),100,'View must be previously defined','EE07','Y','AD_View_Column IsQueryCriteria','S',TO_DATE('2012-06-29 22:40:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:40:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-128 New feature lets you define a field as column axis
UPDATE AD_Column SET AD_Val_Rule_ID=52126,Updated=TO_DATE('2012-06-29 22:40:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63219
;



-- 2/10/2008 11:02:34
-- Financial reporting improvements
INSERT INTO AD_Element (AD_Org_ID,AD_Client_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PO_Name,PrintName,Updated,UpdatedBy) VALUES (0,0,53690,'IsMultiLineHeader',TO_DATE('2008-10-02 11:00:41','YYYY-MM-DD HH24:MI:SS'),100,'Print column headers on mutliple lines if necessary.','D','If selected, column header text will wrap onto the next line -- otherwise the text will be truncated.','Y','Multi Line Header',NULL,'Multi Line Header',TO_DATE('2008-10-02 11:00:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/10/2008 11:02:34
-- Financial reporting improvements
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53690 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 2/10/2008 11:03:39
-- Financial reporting improvements
INSERT INTO AD_Column (AD_Org_ID,AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,0,56358,53690,20,523,'IsMultiLineHeader',TO_DATE('2008-10-02 11:03:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','Print column headers on mutliple lines if necessary.','D',1,'If selected, column header text will wrap onto the next line -- otherwise the text will be truncated.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Multi Line Header',0,TO_DATE('2008-10-02 11:03:36','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 2/10/2008 11:03:39
-- Financial reporting improvements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56358 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2/10/2008 11:04:17
-- Financial reporting improvements
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2008-10-02 11:04:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56358
;

-- 2/10/2008 11:04:23
-- 2/10/2008 11:05:08
-- Financial reporting improvements
ALTER TABLE AD_PrintTableFormat ADD IsMultiLineHeader CHAR(1) DEFAULT 'N' CHECK (IsMultiLineHeader IN ('Y','N')) NOT NULL
;

-- 2/10/2008 11:23:44
-- Financial reporting improvements
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Client_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (56358,0,0,56377,435,TO_DATE('2008-10-02 11:23:40','YYYY-MM-DD HH24:MI:SS'),100,'Print column headers on mutliple lines if necessary.',14,'D','If selected, column header text will wrap onto the next line -- otherwise the text will be truncated.','Y','Y','Y','N','N','N','N','Y','Multi Line Header',95,0,TO_DATE('2008-10-02 11:23:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/10/2008 11:23:44
-- Financial reporting improvements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56377 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


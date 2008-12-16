-- 3/10/2008 16:15:23
-- Financial reporting improvements
INSERT INTO AD_Element (AD_Org_ID,AD_Client_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,0,53691,'IsSuppressRepeats',TO_TIMESTAMP('2008-10-03 16:15:17','YYYY-MM-DD HH24:MI:SS'),100,'Suppress repeated elements in column.','D','Determines whether repeated elements in a column are repeated in a printed table.','Y','Suppress Repeats','Suppress Repeats',TO_TIMESTAMP('2008-10-03 16:15:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 3/10/2008 16:15:23
-- Financial reporting improvements
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53691 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 3/10/2008 16:15:52
-- Financial reporting improvements
INSERT INTO AD_Column (AD_Org_ID,AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,0,56359,53691,20,489,'IsSuppressRepeats',TO_TIMESTAMP('2008-10-03 16:15:50','YYYY-MM-DD HH24:MI:SS'),100,'Suppress repeated elements in column.','D',1,'Determines whether repeated elements in a column are repeated in a printed table.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Suppress Repeats',0,TO_TIMESTAMP('2008-10-03 16:15:50','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 3/10/2008 16:15:52
-- Financial reporting improvements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56359 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 3/10/2008 16:19:40
-- Financial reporting improvements
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Client_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (56359,0,0,56378,426,TO_TIMESTAMP('2008-10-03 16:19:39','YYYY-MM-DD HH24:MI:SS'),100,'Suppress repeated elements in column.',1,'@IsForm@=N & @PrintFormatType@=F','D','Determines whether repeated elements in a column are repeated in a printed table.','Y','Y','Y','N','N','N','N','Y','Suppress Repeats',115,TO_TIMESTAMP('2008-10-03 16:19:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 3/10/2008 16:19:40
-- Financial reporting improvements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56378 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 3/10/2008 16:33:36
-- Financial reporting improvements
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-10-03 16:33:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56359
;

-- 3/10/2008 16:33:41
-- Financial reporting improvements
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2008-10-03 16:33:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56359
;

alter table ad_printformatitem add column IsSuppressRepeats CHAR(1) DEFAULT 'N' CHECK (IsSuppressRepeats IN ('Y','N')) NOT NULL;

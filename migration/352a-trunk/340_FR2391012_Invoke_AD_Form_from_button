-- 4/12/2008 17:08:52
-- Call form from button
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56515,1298,0,19,284,'AD_Form_ID',TO_DATE('2008-12-04 17:08:50','YYYY-MM-DD HH24:MI:SS'),100,'Special Form','D',10,'The Special Form field identifies a unique Special Form in the system.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Special Form',0,TO_DATE('2008-12-04 17:08:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 4/12/2008 17:08:52
-- Call form from button
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56515 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 4/12/2008 17:08:57
-- Call form from button
ALTER TABLE AD_Process ADD AD_Form_ID NUMBER(10)
;

-- 4/12/2008 17:11:17
-- Call form from button
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56515,56497,0,245,TO_DATE('2008-12-04 17:11:03','YYYY-MM-DD HH24:MI:SS'),100,'Special Form',14,'D','The Special Form field identifies a unique Special Form in the system.','Y','Y','Y','N','N','N','N','N','Special Form',155,0,TO_DATE('2008-12-04 17:11:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/12/2008 17:11:17
-- Call form from button
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56497 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


-- Aug 29, 2009 11:55:21 PM ECT
-- Create Field IncludeTab_ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57957,2026,0,19,107,'Included_Tab_ID',TO_TIMESTAMP('2009-08-29 23:55:18','YYYY-MM-DD HH24:MI:SS'),100,'Included Tab in this Tab (Master Dateail)','D',10,'You can include a Tab in a Tab. If displayed in single row record, the included tab is displayed as multi-row table.','Y','N','N','N','N','N','N','N','N','Y','Included Tab',TO_TIMESTAMP('2009-08-29 23:55:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 29, 2009 11:55:21 PM ECT
-- Create Field IncludeTab_ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57957 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Aug 29, 2009 11:55:59 PM ECT
-- Create Field IncludeTab_ID
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=278,Updated=TO_TIMESTAMP('2009-08-29 23:55:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57957
;
-- Create Field IncludeTab_ID
UPDATE AD_Field SET AD_Column_ID =57957 WHERE  AD_Field_ID=6432
;

-- 22/04/2009 12:25:02
-- Table reference selectable zoom target window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57270,143,0,19,103,'AD_Window_ID',TO_TIMESTAMP('2009-04-22 12:25:01','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',22,'The Window field identifies a unique Window in the system.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Window',0,TO_TIMESTAMP('2009-04-22 12:25:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 22/04/2009 12:25:02
-- Table reference selectable zoom target window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57270 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22/04/2009 12:25:03
-- Table reference selectable zoom target window
ALTER TABLE AD_Ref_Table ADD COLUMN AD_Window_ID NUMERIC(10)
;

-- 22/04/2009 12:25:55
-- Table reference selectable zoom target window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57270,57011,0,103,TO_TIMESTAMP('2009-04-22 12:25:54','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window',0,'D','The Window field identifies a unique Window in the system.','Y','Y','Y','N','N','N','N','N','Window',120,0,TO_TIMESTAMP('2009-04-22 12:25:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22/04/2009 12:25:55
-- Table reference selectable zoom target window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57011 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

alter table
   AD_Ref_Table
add constraint
   ADWindow_ADRefTable FOREIGN KEY (AD_Window_ID)
references
   AD_Window (AD_Window_ID);

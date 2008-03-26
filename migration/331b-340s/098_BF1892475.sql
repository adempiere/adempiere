-- Feb 13, 2008 4:16:25 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53348,0,'IsOrderByValue',TO_DATE('2008-02-13 16:16:22','YYYY-MM-DD HH24:MI:SS'),100,'Order list using the value column instead of the name column','D','Order list using the value column instead of the name column','Y','Order By Value','Order By Value',TO_DATE('2008-02-13 16:16:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 4:16:25 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53348 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 13, 2008 4:17:15 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54355,53348,0,20,102,'IsOrderByValue',TO_DATE('2008-02-13 16:17:13','YYYY-MM-DD HH24:MI:SS'),100,'N','Order list using the value column instead of the name column','D',1,'Order list using the value column instead of the name column','Y','N','N','N','N','N','N','N','N','N','Y','Order By Value',0,TO_DATE('2008-02-13 16:17:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 13, 2008 4:17:15 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54355 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 13, 2008 4:17:19 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
ALTER TABLE AD_Reference ADD IsOrderByValue CHAR(1) DEFAULT 'N'
;

-- Feb 13, 2008 4:19:22 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54355,54400,0,102,TO_DATE('2008-02-13 16:19:20','YYYY-MM-DD HH24:MI:SS'),100,'Order list using the value column instead of the name column',0,'@ValidationType@=L','U','Order list using the value column instead of the name column','Y','Y','Y','N','N','N','N','N','Order By Value',110,0,TO_DATE('2008-02-13 16:19:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 4:19:22 PM SGT
-- [ 1892475 ] AD_Reference list is hardcoded to always sort by name
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54400 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


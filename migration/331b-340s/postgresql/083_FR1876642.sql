-- Jan 30, 2008 10:11:15 AM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53336,0,'IsCollapsedByDefault',TO_TIMESTAMP('2008-01-30 10:11:13','YYYY-MM-DD HH24:MI:SS'),100,'Flag to set the initial state of collapsible field group.','U','Y','Collapsed By Default','Collapsed By Default',TO_TIMESTAMP('2008-01-30 10:11:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 30, 2008 10:11:16 AM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53336 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jan 30, 2008 12:07:42 PM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54259,53336,0,20,414,'IsCollapsedByDefault',TO_TIMESTAMP('2008-01-30 12:07:41','YYYY-MM-DD HH24:MI:SS'),100,'N','Flag to set the initial state of collapsible field group.','D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Collapsed By Default',0,TO_TIMESTAMP('2008-01-30 12:07:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jan 30, 2008 12:07:42 PM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54259 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 30, 2008 12:09:43 PM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54259,54348,0,342,TO_TIMESTAMP('2008-01-30 12:09:42','YYYY-MM-DD HH24:MI:SS'),100,'Flag to set the initial state of collapsible field group.',0,'@FieldGroupType@=''C''','D','Y','Y','Y','N','N','N','N','N','Collapsed By Default',70,0,TO_TIMESTAMP('2008-01-30 12:09:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 30, 2008 12:09:43 PM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54348 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 30, 2008 12:10:36 PM SGT
-- [ 1876642 ] Add Collapsed by Default to Field Group
ALTER TABLE AD_FieldGroup ADD COLUMN IsCollapsedByDefault CHAR(1) DEFAULT 'N' CHECK (IsCollapsedByDefault IN ('Y','N'))
;


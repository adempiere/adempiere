-- 11/06/2009 14:51:45
-- Add Parent_Column_ID to tab
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53874,0,'Parent_Column_ID',TO_DATE('2009-06-11 14:51:43','YYYY-MM-DD HH24:MI:SS'),100,'The link column on the parent tab.','D','Y','Parent Column','Parent Column',TO_DATE('2009-06-11 14:51:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/06/2009 14:51:45
-- Add Parent_Column_ID to tab
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53874 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 11/06/2009 14:53:27
-- Add Parent_Column_ID to tab
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52056,'/* attempt to get the columns in the parent tab table */
AD_Column.AD_Table_ID IN (SELECT t.AD_Table_ID FROM AD_Tab t
                                                   WHERE t.AD_Window_ID = @AD_Window_ID@
	                         AND t.TabLevel = @TabLevel@-1
		AND t.SeqNo < @SeqNo@
		AND NOT EXISTS (SELECT 1 FROM AD_Tab t2
                                                      where t2.AD_Window_ID=t.AD_Window_ID
                                                      AND t2.TabLevel = t.TabLevel
		AND t2.SeqNo < @SeqNo@
                                                     AND t2.SeqNo > t.SeqNo) )',TO_DATE('2009-06-11 14:53:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','AD_Column (Parent tab link column)','S',TO_DATE('2009-06-11 14:53:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/06/2009 14:55:41
-- Add Parent_Column_ID to tab
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57842,53874,0,18,251,106,52056,'Parent_Column_ID',TO_DATE('2009-06-11 14:55:39','YYYY-MM-DD HH24:MI:SS'),100,'The link column on the parent tab.','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Parent Column',0,TO_DATE('2009-06-11 14:55:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 11/06/2009 14:55:41
-- Add Parent_Column_ID to tab
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57842 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 11/06/2009 14:55:45
-- Add Parent_Column_ID to tab
ALTER TABLE AD_Tab ADD Parent_Column_ID NUMBER(10) DEFAULT NULL 
;

-- 11/06/2009 14:57:07
-- Add Parent_Column_ID to tab
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2009-06-11 14:57:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2575
;

-- 11/06/2009 14:58:54
-- Add Parent_Column_ID to tab
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57842,57266,0,106,TO_DATE('2009-06-11 14:58:53','YYYY-MM-DD HH24:MI:SS'),100,'The link column on the parent tab.',14,'D','Y','Y','Y','N','N','N','N','Y','Parent Column',205,0,TO_DATE('2009-06-11 14:58:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/06/2009 14:58:54
-- Add Parent_Column_ID to tab
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57266 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

CREATE OR REPLACE VIEW AD_Tab_V
AS
SELECT t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, t.Name, t.Description, 
    t.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, t.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID, t.Parent_Column_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y';

CREATE OR REPLACE VIEW AD_Tab_VT
AS
SELECT trl.AD_Language, t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, trl.Name, trl.Description, 
    trl.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, trl.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID, t.Parent_Column_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Tab_Trl trl ON (t.AD_Tab_ID = trl.AD_Tab_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y';
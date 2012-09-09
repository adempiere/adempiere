
-- Aug 21, 2012 9:09:49 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET SeqNo=2,Updated=TO_TIMESTAMP('2012-08-21 21:09:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58105
;

-- Aug 21, 2012 9:11:39 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=1,Updated=TO_TIMESTAMP('2012-08-21 21:11:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58107
;

-- Aug 21, 2012 9:11:54 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsIdentifier='N', SeqNo=NULL,Updated=TO_TIMESTAMP('2012-08-21 21:11:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58105
;

-- Aug 21, 2012 9:18:23 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET Callout='org.adempiere.model.CalloutParameter.element',Updated=TO_TIMESTAMP('2012-08-21 21:18:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7729
;

-- Aug 27, 2012 9:32:59 AM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64221,573,0,22,53223,'SortNo',TO_TIMESTAMP('2012-08-27 09:32:56','YYYY-MM-DD HH24:MI:SS'),100,'Determines in what order the records are displayed','EE07',22,'The Record Sort No indicates the ascending sort sequence of the records. If the number is negative, the records are sorted descending. 
Example: A tab with C_DocType_ID (1), DocumentNo (-2) will be sorted ascending by document type and descending by document number (SQL: ORDER BY C_DocType, DocumentNo DESC)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Record Sort No',0,TO_TIMESTAMP('2012-08-27 09:32:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 27, 2012 9:32:59 AM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64221 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 27, 2012 9:33:03 AM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD COLUMN SortNo NUMERIC DEFAULT NULL 
;

-- Aug 27, 2012 9:34:46 AM CDT
-- Smart Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64222,1803,0,20,53223,'IsOrderBy',TO_TIMESTAMP('2012-08-27 09:34:44','YYYY-MM-DD HH24:MI:SS'),100,'Include in sort order','EE07',1,'The records are ordered by the value of this column. If a column is used for grouping, it needs to be included in the sort order as well.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Order by',0,TO_TIMESTAMP('2012-08-27 09:34:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 27, 2012 9:34:46 AM CDT
-- Smart Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64222 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 27, 2012 9:34:49 AM CDT
-- Smart Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsOrderBy CHAR(1) DEFAULT NULL CHECK (IsOrderBy IN ('Y','N'))
;

-- Aug 27, 2012 9:35:01 AM CDT
-- Smart Browse
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2012-08-27 09:35:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64222
;

-- Aug 27, 2012 9:35:03 AM CDT
-- Smart Browse
INSERT INTO t_alter_column values('ad_browse_field','IsOrderBy','CHAR(1)',null,'N')
;

-- Aug 27, 2012 9:36:05 AM CDT
-- Smart Browse
UPDATE AD_Column SET AD_Reference_ID=11,Updated=TO_TIMESTAMP('2012-08-27 09:36:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64221
;

-- Aug 27, 2012 9:37:07 AM CDT
-- Smart Browse
UPDATE AD_Tab SET Description='Define Browse Fields', Name='Browse Field',Updated=TO_TIMESTAMP('2012-08-27 09:37:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53247
;

-- Aug 27, 2012 9:37:07 AM CDT
-- Smart Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53247
;

-- Aug 27, 2012 9:37:18 AM CDT
-- Smart Browse
UPDATE AD_Tab SET Name='Browse Fields Tranlation',Updated=TO_TIMESTAMP('2012-08-27 09:37:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53251
;

-- Aug 27, 2012 9:37:18 AM CDT
-- Smart Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53251
;

-- Aug 27, 2012 9:37:44 AM CDT
-- Smart Browse
UPDATE AD_Tab SET Description='Define Browse Fields Sequence', Name='Browse Fields Sequence',Updated=TO_TIMESTAMP('2012-08-27 09:37:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53252
;

-- Aug 27, 2012 9:37:44 AM CDT
-- Smart Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53252
;

-- Aug 27, 2012 9:40:00 AM CDT
-- Smart Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_ColumnSortOrder_ID,AD_ColumnSortYesNo_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,64221,64222,0,53566,53223,53088,TO_TIMESTAMP('2012-08-27 09:39:57','YYYY-MM-DD HH24:MI:SS'),100,'Sort Order of the  Browse Fields','EE07','N','N','Y','N','N','Y','N','N','Y','N','Sort Order','N',70,1,TO_TIMESTAMP('2012-08-27 09:39:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 27, 2012 9:40:00 AM CDT
-- Smart Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53566 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 27, 2012 9:41:52 AM CDT
-- Smart Browse
UPDATE AD_Tab SET Name='Sort Order Fields',Updated=TO_TIMESTAMP('2012-08-27 09:41:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53566
;

-- Aug 27, 2012 9:41:52 AM CDT
-- Smart Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53566
;

-- Aug 27, 2012 9:43:09 AM CDT
-- Smart Browse
UPDATE AD_Tab SET Name='Sort Order Browse Fields',Updated=TO_TIMESTAMP('2012-08-27 09:43:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53566
;

-- Aug 27, 2012 9:43:09 AM CDT
-- Smart Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53566
;

UPDATE AD_Browse_Field SET IsOrderBy = 'N' , SortNo = 0;
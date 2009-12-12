-- Dec 12, 2009 1:44:30 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,174,0,53298,107,100,TO_DATE('2009-12-12 01:44:27','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','N','Y','N','N','N','Used in Field','N',45,2,TO_DATE('2009-12-12 01:44:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 12, 2009 1:44:30 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53298 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Dec 12, 2009 1:46:19 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,172,58568,0,53298,TO_DATE('2009-12-12 01:46:17','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window',0,'D','The Tab indicates a tab that displays within a window.','Y','Y','Y','N','N','N','N','N','Tab',10,0,TO_DATE('2009-12-12 01:46:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 12, 2009 1:46:20 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58568 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 12, 2009 1:46:39 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,167,58569,0,53298,TO_DATE('2009-12-12 01:46:38','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table',0,'D','The Field identifies a field on a database table.','Y','Y','Y','N','N','N','N','Y','Field',20,0,TO_DATE('2009-12-12 01:46:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 12, 2009 1:46:39 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58569 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 12, 2009 1:47:54 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
UPDATE AD_Field SET AD_Column_ID=168, Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', Name='Name',Updated=TO_DATE('2009-12-12 01:47:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58569
;

-- Dec 12, 2009 1:47:54 AM CET
-- Add a 'Used in Fields' tab to 'Table and Column' window
-- http://sourceforge.net/tracker/?func=detail&atid=883808&aid=2830830&group_id=176962
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58569
;


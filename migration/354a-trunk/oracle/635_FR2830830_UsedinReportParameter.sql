-- Dec 11, 2009 9:53:05 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,7729,0,53297,285,151,TO_DATE('2009-12-11 09:53:04','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','N','N','N','N','N','Used in Process Parameter','N',40,1,TO_DATE('2009-12-11 09:53:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 11, 2009 9:53:05 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53297 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Dec 11, 2009 9:54:09 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2825,58566,0,53297,TO_DATE('2009-12-11 09:54:08','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report',0,'D','The Process field identifies a unique Process or Report in the system.','Y','Y','Y','N','N','N','N','N','Process',10,0,TO_DATE('2009-12-11 09:54:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 11, 2009 9:54:09 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58566 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 11, 2009 9:57:03 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2814,58567,0,53297,TO_DATE('2009-12-11 09:56:57','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','N','N','N','N','N','Process Parameter',20,0,TO_DATE('2009-12-11 09:56:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 11, 2009 9:57:03 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58567 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 11, 2009 9:59:03 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
UPDATE AD_Tab SET IsReadOnly='Y',Updated=TO_DATE('2009-12-11 09:59:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53297
;

-- Dec 11, 2009 9:59:31 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
UPDATE AD_Field SET AD_Column_ID=4017, Description='Name of the column in the database', Help='The Column Name indicates the name of a column on a table as defined in the database.', Name='DB Column Name',Updated=TO_DATE('2009-12-11 09:59:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58567
;

-- Dec 11, 2009 9:59:31 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58567
;

-- Dec 11, 2009 10:00:16 AM CET
-- FR[2830830] - New  tab Used in Process Parameter into "System Element"
-- https://sourceforge.net/tracker/?func=detail&aid=2830830&group_id=176962&atid=883808
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-12-11 10:00:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58567
;


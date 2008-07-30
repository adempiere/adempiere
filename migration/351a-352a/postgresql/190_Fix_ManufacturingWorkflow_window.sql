-- 02.06.2008 11:01:31 EEST
-- Fix Manufacturing Workflows window
DELETE FROM AD_Tab_Trl WHERE AD_Tab_ID=53018
;

-- 02.06.2008 11:01:31 EEST
-- Fix Manufacturing Workflows window
DELETE FROM AD_Tab WHERE AD_Tab_ID=53018
;

-- 02.06.2008 11:01:50 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Tab SET IsInsertRecord='N', TabLevel=1,Updated=TO_TIMESTAMP('2008-06-02 11:01:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53019
;

-- 02.06.2008 11:03:14 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,701,56255,0,53019,TO_TIMESTAMP('2008-06-02 11:03:12','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2008-06-02 11:03:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:14 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56255 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:14 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1213,56256,0,53019,TO_TIMESTAMP('2008-06-02 11:03:14','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-06-02 11:03:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:14 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56256 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:15 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,316,56257,0,53019,TO_TIMESTAMP('2008-06-02 11:03:14','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_TIMESTAMP('2008-06-02 11:03:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:15 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56257 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:16 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,315,56258,0,53019,TO_TIMESTAMP('2008-06-02 11:03:15','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_TIMESTAMP('2008-06-02 11:03:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:16 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56258 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:16 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,313,56259,0,53019,TO_TIMESTAMP('2008-06-02 11:03:16','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'D','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_TIMESTAMP('2008-06-02 11:03:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:16 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56259 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:17 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,314,56260,0,53019,TO_TIMESTAMP('2008-06-02 11:03:16','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_TIMESTAMP('2008-06-02 11:03:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:17 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56260 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:18 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1214,56261,0,53019,TO_TIMESTAMP('2008-06-02 11:03:17','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-06-02 11:03:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:18 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56261 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:19 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,317,56262,0,53019,TO_TIMESTAMP('2008-06-02 11:03:18','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'D','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_TIMESTAMP('2008-06-02 11:03:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:19 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56262 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:03:19 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,312,56263,0,53019,TO_TIMESTAMP('2008-06-02 11:03:19','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks',22,'D','The Workflow field identifies a unique Workflow in the system.','Y','Y','Y','N','N','N','N','N','Workflow',TO_TIMESTAMP('2008-06-02 11:03:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 02.06.2008 11:03:19 EEST
-- Fix Manufacturing Workflows window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56263 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56256
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56261
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56263
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56259
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56255
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56262
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56260
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56258
;

-- 02.06.2008 11:05:20 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56257
;

-- 02.06.2008 11:07:03 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=14, IsReadOnly='Y',Updated=TO_TIMESTAMP('2008-06-02 11:07:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56256
;

-- 02.06.2008 11:07:12 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=14, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-06-02 11:07:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56261
;

-- 02.06.2008 11:07:25 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=14, IsReadOnly='Y',Updated=TO_TIMESTAMP('2008-06-02 11:07:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56263
;

-- 02.06.2008 11:08:21 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='Y', IsSameLine='N', SeqNo=40,Updated=TO_TIMESTAMP('2008-06-02 11:08:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56259
;

-- 02.06.2008 11:08:21 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=80,Updated=TO_TIMESTAMP('2008-06-02 11:08:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56258
;

-- 02.06.2008 11:08:21 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=90,Updated=TO_TIMESTAMP('2008-06-02 11:08:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56257
;

-- 02.06.2008 11:11:28 EEST
-- Fix Manufacturing Workflows window
UPDATE AD_Tab SET TabLevel=2,Updated=TO_TIMESTAMP('2008-06-02 11:11:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53026
;


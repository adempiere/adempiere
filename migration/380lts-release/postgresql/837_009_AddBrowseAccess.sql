-- Jul 2, 2012 1:56:30 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53533,53225,111,TO_TIMESTAMP('2012-07-02 13:56:26','YYYY-MM-DD HH24:MI:SS'),100,'Browse Access','EE07','N','The Browse Access Tab defines the Browses and type of access that this Role is granted.','Y','N','N','Y','N','N','N','N','Browse Access','N',60,1,TO_TIMESTAMP('2012-07-02 13:56:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:30 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53533 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Jul 2, 2012 1:56:39 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58006,64637,0,53533,TO_TIMESTAMP('2012-07-02 13:56:39','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2012-07-02 13:56:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:39 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64637 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:56:40 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58004,64638,0,53533,TO_TIMESTAMP('2012-07-02 13:56:39','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2012-07-02 13:56:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:40 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64638 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:56:41 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58005,64639,0,53533,TO_TIMESTAMP('2012-07-02 13:56:40','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2012-07-02 13:56:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:41 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64639 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:56:41 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58013,64640,0,53533,TO_TIMESTAMP('2012-07-02 13:56:41','YYYY-MM-DD HH24:MI:SS'),100,'Field is read / write',1,'EE07','The Read Write indicates that this field may be read and updated.','Y','Y','Y','N','N','N','N','N','Read Write',TO_TIMESTAMP('2012-07-02 13:56:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:41 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64640 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:56:42 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58012,64641,0,53533,TO_TIMESTAMP('2012-07-02 13:56:41','YYYY-MM-DD HH24:MI:SS'),100,'Responsibility Role',22,'EE07','The Role determines security and access a user who has this Role will have in the System.','Y','Y','Y','N','N','N','N','N','Role',TO_TIMESTAMP('2012-07-02 13:56:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:42 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64641 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:56:42 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58011,64642,0,53533,TO_TIMESTAMP('2012-07-02 13:56:42','YYYY-MM-DD HH24:MI:SS'),100,22,'EE07','Y','Y','Y','N','N','N','N','N','Smart Browse',TO_TIMESTAMP('2012-07-02 13:56:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 2, 2012 1:56:42 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64642 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=64638
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=64639
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=64642
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=64641
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=64640
;

-- Jul 2, 2012 1:57:07 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=64637
;

-- Jul 2, 2012 1:57:33 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=64641
;

-- Jul 2, 2012 1:57:33 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=64642
;

-- Jul 2, 2012 1:57:33 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=64637
;

-- Jul 2, 2012 1:57:33 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=64640
;

-- Jul 2, 2012 1:57:41 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-07-02 13:57:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64639
;

-- Jul 2, 2012 1:58:21 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2012-07-02 13:58:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64638
;

-- Jul 2, 2012 1:58:31 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2012-07-02 13:58:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64641
;

-- Jul 2, 2012 1:59:00 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=58012,Updated=TO_TIMESTAMP('2012-07-02 13:59:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53533
;

-- Jul 2, 2012 2:00:00 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET SeqNo=65,Updated=TO_TIMESTAMP('2012-07-02 14:00:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53533
;


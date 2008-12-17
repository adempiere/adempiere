SET DEFINE OFF;

-- Nov 15, 2008 10:11:57 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=102, AD_Reference_ID=19, AD_Val_Rule_ID=129, DefaultValue='@AD_Client_ID@', EntityType='A', FieldLength=22, IsUpdateable='N',Updated=TO_DATE('2008-11-15 10:11:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52042
;

-- Nov 15, 2008 10:11:58 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Reference_ID=19, DefaultValue='@#AD_Client_ID@', IsMandatory='Y',Updated=TO_DATE('2008-11-15 10:11:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52042
;

-- Nov 15, 2008 10:11:58 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=113, AD_Reference_ID=19, AD_Val_Rule_ID=104, DefaultValue='@AD_Org_ID@', EntityType='A', FieldLength=22, IsUpdateable='N',Updated=TO_DATE('2008-11-15 10:11:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52043
;

-- Nov 15, 2008 10:11:59 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Reference_ID=19, DefaultValue='@#AD_Org_ID@', IsMandatory='Y',Updated=TO_DATE('2008-11-15 10:11:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52043
;

-- Nov 15, 2008 10:12:07 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=102, AD_Reference_ID=19, AD_Val_Rule_ID=129, DefaultValue='@AD_Client_ID@', EntityType='A', FieldLength=22, IsUpdateable='N',Updated=TO_DATE('2008-11-15 10:12:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52077
;

-- Nov 15, 2008 10:12:07 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=102, AD_Reference_ID=19, AD_Val_Rule_ID=129, DefaultValue='@#AD_Client_ID@', IsMandatory='Y', Name='Client',Updated=TO_DATE('2008-11-15 10:12:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52077
;

-- Nov 15, 2008 10:12:08 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=113, AD_Reference_ID=19, AD_Val_Rule_ID=104, DefaultValue='@AD_Org_ID@', EntityType='A', FieldLength=22, IsUpdateable='N',Updated=TO_DATE('2008-11-15 10:12:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52078
;

-- Nov 15, 2008 10:12:08 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=113, AD_Reference_ID=19, AD_Val_Rule_ID=130, DefaultValue='@#AD_Org_ID@', IsMandatory='Y', Name='Organization',Updated=TO_DATE('2008-11-15 10:12:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52078
;

-- Nov 15, 2008 10:12:15 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52041, AD_Reference_ID=20, EntityType='A', FieldLength=1,Updated=TO_DATE('2008-11-15 10:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52091
;

-- Nov 15, 2008 10:12:15 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52041, AD_Reference_ID=17, AD_Reference_Value_ID=52002, Name='Card Transfer Type',Updated=TO_DATE('2008-11-15 10:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52091
;

-- Nov 15, 2008 10:12:17 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52031, AD_Reference_ID=20, EntityType='A', FieldLength=1,Updated=TO_DATE('2008-11-15 10:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52080
;

-- Nov 15, 2008 10:12:17 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52031, AD_Reference_ID=17, AD_Reference_Value_ID=52002, Description='Where the money in the cash book should be transfered to. Either a Bank Account or another Cash Book', IsMandatory='Y', Name='Cash Book Transfer Type',Updated=TO_DATE('2008-11-15 10:12:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52080
;

-- Nov 15, 2008 10:12:19 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52037, AD_Reference_ID=20, EntityType='A', FieldLength=1,Updated=TO_DATE('2008-11-15 10:12:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52087
;

-- Nov 15, 2008 10:12:20 AM EET
-- Posterita Integration
UPDATE AD_Column SET AD_Element_ID=52037, AD_Reference_ID=17, AD_Reference_Value_ID=52002, Name='Check Transfer Type',Updated=TO_DATE('2008-11-15 10:12:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52087
;

----------------------------
-- Nov 15, 2008 10:22:43 AM EET
-- Posterita Integration
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,Name,Updated,UpdatedBy) VALUES (0,53196,0,TO_DATE('2008-11-15 10:22:39','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','N','N','Web POS Terminal',TO_DATE('2008-11-15 10:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:43 AM EET
-- Posterita Integration
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53196 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Nov 15, 2008 10:22:44 AM EET
-- Posterita Integration
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53065,TO_DATE('2008-11-15 10:22:43','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Web POS Terminal','N',TO_DATE('2008-11-15 10:22:43','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Nov 15, 2008 10:22:44 AM EET
-- Posterita Integration
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53065 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Nov 15, 2008 10:22:46 AM EET
-- Posterita Integration
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,IsActive,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53181,52004,53065,TO_DATE('2008-11-15 10:22:44','YYYY-MM-DD HH24:MI:SS'),0,'A','N','Y','N','Y','N','N','Terminal','N',10,0,TO_DATE('2008-11-15 10:22:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:46 AM EET
-- Posterita Integration
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53181 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Nov 15, 2008 10:22:48 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52077,56450,0,53181,TO_DATE('2008-11-15 10:22:46','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Client',10,TO_DATE('2008-11-15 10:22:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:48 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56450 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:48 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=10,Updated=TO_DATE('2008-11-15 10:22:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56450
;

-- Nov 15, 2008 10:22:49 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52078,56451,0,53181,TO_DATE('2008-11-15 10:22:48','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Organization',20,TO_DATE('2008-11-15 10:22:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:49 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56451 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:49 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20,Updated=TO_DATE('2008-11-15 10:22:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56451
;

-- Nov 15, 2008 10:22:50 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52102,56452,0,53181,TO_DATE('2008-11-15 10:22:49','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Name',30,TO_DATE('2008-11-15 10:22:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:50 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56452 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:50 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=30,Updated=TO_DATE('2008-11-15 10:22:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56452
;

-- Nov 15, 2008 10:22:51 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52097,56453,0,53181,TO_DATE('2008-11-15 10:22:50','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Active',40,TO_DATE('2008-11-15 10:22:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:51 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56453 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:51 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=40,Updated=TO_DATE('2008-11-15 10:22:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56453
;

-- Nov 15, 2008 10:22:52 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52095,56454,0,53181,TO_DATE('2008-11-15 10:22:51','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Description',50,TO_DATE('2008-11-15 10:22:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:52 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56454 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:52 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=255, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=50,Updated=TO_DATE('2008-11-15 10:22:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56454
;

-- Nov 15, 2008 10:22:53 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52096,56455,0,53181,TO_DATE('2008-11-15 10:22:52','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Comment/Help',60,TO_DATE('2008-11-15 10:22:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:53 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56455 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:53 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=2000, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Comment/Help', SeqNo=60,Updated=TO_DATE('2008-11-15 10:22:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56455
;

-- Nov 15, 2008 10:22:54 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52101,56456,0,53181,TO_DATE('2008-11-15 10:22:53','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Warehouse',70,TO_DATE('2008-11-15 10:22:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:54 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56456 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:54 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Warehouse', SeqNo=70,Updated=TO_DATE('2008-11-15 10:22:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56456
;

-- Nov 15, 2008 10:22:55 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52079,56457,0,53181,TO_DATE('2008-11-15 10:22:54','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Auto Lock',80,TO_DATE('2008-11-15 10:22:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:55 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56457 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:55 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Auto Lock', SeqNo=80,Updated=TO_DATE('2008-11-15 10:22:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56457
;

-- Nov 15, 2008 10:22:56 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52099,56458,0,53181,TO_DATE('2008-11-15 10:22:55','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Locked',90,TO_DATE('2008-11-15 10:22:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:56 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56458 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:56 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', Name='Locked', SeqNo=90,Updated=TO_DATE('2008-11-15 10:22:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56458
;

-- Nov 15, 2008 10:22:56 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52100,56459,0,53181,TO_DATE('2008-11-15 10:22:56','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Lock Time',100,TO_DATE('2008-11-15 10:22:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:57 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56459 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:57 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=10, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Lock Time', SeqNo=100,Updated=TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56459
;

-- Nov 15, 2008 10:22:57 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52098,56460,0,53181,TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Last Lock Time',110,TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:57 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56460 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:57 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=7, IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', Name='Last Lock Time', SeqNo=110,Updated=TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56460
;

-- Nov 15, 2008 10:22:58 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52107,56461,0,53181,TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Unlocking Time',120,TO_DATE('2008-11-15 10:22:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:58 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56461 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:58 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=7, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Unlocking Time', SeqNo=120,Updated=TO_DATE('2008-11-15 10:22:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56461
;

-- Nov 15, 2008 10:22:59 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52094,56462,0,53181,TO_DATE('2008-11-15 10:22:58','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Template BPartner',130,TO_DATE('2008-11-15 10:22:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:22:59 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56462 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:22:59 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Template BPartner', SeqNo=130,Updated=TO_DATE('2008-11-15 10:22:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56462
;

-- Nov 15, 2008 10:23:00 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52104,56463,0,53181,TO_DATE('2008-11-15 10:22:59','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Printer Name',140,TO_DATE('2008-11-15 10:22:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:00 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56463 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:00 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Printer Name', SeqNo=140,Updated=TO_DATE('2008-11-15 10:23:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56463
;

-- Nov 15, 2008 10:23:01 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52103,56464,0,53181,TO_DATE('2008-11-15 10:23:00','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Purchase Pricelist',150,TO_DATE('2008-11-15 10:23:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:01 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56464 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:01 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Purchase Pricelist', SeqNo=150,Updated=TO_DATE('2008-11-15 10:23:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56464
;

-- Nov 15, 2008 10:23:02 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52106,56465,0,53181,TO_DATE('2008-11-15 10:23:01','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Sales Pricelist',160,TO_DATE('2008-11-15 10:23:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:02 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56465 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:02 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', Name='Sales Pricelist', SeqNo=160,Updated=TO_DATE('2008-11-15 10:23:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56465
;

-- Nov 15, 2008 10:23:03 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52105,56466,0,53181,TO_DATE('2008-11-15 10:23:02','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Sales Representative',170,TO_DATE('2008-11-15 10:23:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:03 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56466 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:03 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Sales Representative', SeqNo=170,Updated=TO_DATE('2008-11-15 10:23:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56466
;

-- Nov 15, 2008 10:23:04 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52091,56467,0,53181,TO_DATE('2008-11-15 10:23:03','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Card Transfer Type',180,TO_DATE('2008-11-15 10:23:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:04 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56467 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:04 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Card Transfer Type', SeqNo=180,Updated=TO_DATE('2008-11-15 10:23:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56467
;

-- Nov 15, 2008 10:23:05 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52088,56468,0,53181,TO_DATE('2008-11-15 10:23:04','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Card Bank Account',190,TO_DATE('2008-11-15 10:23:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:05 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56468 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:05 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Card Bank Account', SeqNo=190,Updated=TO_DATE('2008-11-15 10:23:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56468
;

-- Nov 15, 2008 10:23:05 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52090,56469,0,53181,TO_DATE('2008-11-15 10:23:05','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Transfer Card trx to Cash Book',200,TO_DATE('2008-11-15 10:23:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:06 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56469 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:06 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Card trx to Cash Book', SeqNo=200,Updated=TO_DATE('2008-11-15 10:23:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56469
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52089,56470,0,53181,TO_DATE('2008-11-15 10:23:06','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Transfer Card trx to Bank account',210,TO_DATE('2008-11-15 10:23:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56470 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Card trx to Bank account', SeqNo=210,Updated=TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56470
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52080,56471,0,53181,TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Cash Book Transfer Type',220,TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56471 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:07 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Cash Book Transfer Type', SeqNo=220,Updated=TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56471
;

-- Nov 15, 2008 10:23:08 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52083,56472,0,53181,TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Cash Book',230,TO_DATE('2008-11-15 10:23:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:09 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56472 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:09 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Cash Book', SeqNo=230,Updated=TO_DATE('2008-11-15 10:23:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56472
;

-- Nov 15, 2008 10:23:09 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52084,56473,0,53181,TO_DATE('2008-11-15 10:23:09','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Cash BPartner',240,TO_DATE('2008-11-15 10:23:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:10 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56473 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:10 AM EET
-- Posterita Integration
UPDATE AD_Field SET Description='BPartner to be used for Cash transactions', DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Cash BPartner', SeqNo=240,Updated=TO_DATE('2008-11-15 10:23:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56473
;

-- Nov 15, 2008 10:23:10 AM EET
-- Posterita Integration
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56473
;

-- Nov 15, 2008 10:23:10 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52082,56474,0,53181,TO_DATE('2008-11-15 10:23:10','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Transfer Cash trx to Cash Book',250,TO_DATE('2008-11-15 10:23:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:10 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56474 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:11 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Cash trx to Cash Book', SeqNo=250,Updated=TO_DATE('2008-11-15 10:23:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56474
;

-- Nov 15, 2008 10:23:11 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52081,56475,0,53181,TO_DATE('2008-11-15 10:23:11','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Transfer Cash trx to Bank Account',260,TO_DATE('2008-11-15 10:23:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:12 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56475 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:12 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Cash trx to Bank Account', SeqNo=260,Updated=TO_DATE('2008-11-15 10:23:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56475
;

-- Nov 15, 2008 10:23:13 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52087,56476,0,53181,TO_DATE('2008-11-15 10:23:12','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Check Transfer Type',270,TO_DATE('2008-11-15 10:23:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:13 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56476 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:13 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Check Transfer Type', SeqNo=270,Updated=TO_DATE('2008-11-15 10:23:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56476
;

-- Nov 15, 2008 10:23:15 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52085,56477,0,53181,TO_DATE('2008-11-15 10:23:13','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Check Bank Account',280,TO_DATE('2008-11-15 10:23:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:15 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56477 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:16 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Check Bank Account', SeqNo=280,Updated=TO_DATE('2008-11-15 10:23:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56477
;

-- Nov 15, 2008 10:23:17 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52111,56478,0,53181,TO_DATE('2008-11-15 10:23:16','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Transfer Check trx to Cash Book',290,TO_DATE('2008-11-15 10:23:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:17 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56478 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:17 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Check trx to Cash Book', SeqNo=290,Updated=TO_DATE('2008-11-15 10:23:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56478
;

-- Nov 15, 2008 10:23:18 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52086,56479,0,53181,TO_DATE('2008-11-15 10:23:17','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','Tranfer Check trx to Bank Account',300,TO_DATE('2008-11-15 10:23:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:18 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56479 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:18 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', Name='Transfer Check trx to Bank Account', SeqNo=300,Updated=TO_DATE('2008-11-15 10:23:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56479
;

-- Nov 15, 2008 10:23:19 AM EET
-- Posterita Integration
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,52110,56480,0,53181,TO_DATE('2008-11-15 10:23:18','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','Y','Y','N','N','N','N','N','POS Terminal',310,TO_DATE('2008-11-15 10:23:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 15, 2008 10:23:19 AM EET
-- Posterita Integration
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56480 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 15, 2008 10:23:19 AM EET
-- Posterita Integration
UPDATE AD_Field SET DisplayLength=22, IsDisplayed='N', IsReadOnly='N', IsSameLine='N', Name='POS Terminal',Updated=TO_DATE('2008-11-15 10:23:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56480
;

-- Nov 15, 2008 10:23:19 AM EET
-- Posterita Integration
UPDATE AD_Menu SET AD_Window_ID=53065, Action='W', IsSummary='N',Updated=TO_DATE('2008-11-15 10:23:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53196
;

-- Nov 15, 2008 10:23:19 AM EET
-- Posterita Integration
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,Parent_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_DATE('2008-11-15 10:23:19','YYYY-MM-DD HH24:MI:SS'),0,'Y',53196,52001,60,TO_DATE('2008-11-15 10:23:19','YYYY-MM-DD HH24:MI:SS'),0)
;


--------------------------------
-- Nov 15, 2008 10:38:15 AM EET
-- Posterita Integration
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,IsReadWrite,Updated,UpdatedBy) VALUES (11,0,102,53065,TO_DATE('2008-11-15 10:38:14','YYYY-MM-DD HH24:MI:SS'),0,'Y','Y',TO_DATE('2008-11-15 10:38:14','YYYY-MM-DD HH24:MI:SS'),0)
;



--------------------------------
-- Nov 15, 2008 12:07:12 PM EET
-- Posterita Integration
INSERT INTO U_POSTerminal (AD_Client_ID,AD_Org_ID,AutoLock,C_CashBPartner_ID,C_CashBook_ID,C_TemplateBPartner_ID,CardTransferCashBook_ID,CardTransferType,CashBookTransferType,CashTransferCashBook_ID,CheckTransferCashBook_ID,CheckTransferType,Created,CreatedBy,Description,IsActive,LockTime,Locked,M_Warehouse_ID,Name,PO_PriceList_ID,SO_PriceList_ID,SalesRep_ID,U_POSTerminal_ID,Updated,UpdatedBy) VALUES (11,11,'N',119,101,119,101,'B','C',101,101,'C',TO_DATE('2008-11-15 12:06:59','YYYY-MM-DD HH24:MI:SS'),100,'GardenWorld','Y',0,'N',103,'GardenWorld',102,101,101,50000,TO_DATE('2008-11-15 12:06:59','YYYY-MM-DD HH24:MI:SS'),100)
;



--------------------------------
-- Nov 15, 2008 12:12:11 PM EET
-- Posterita Integration
UPDATE AD_User SET UserPIN='1234',Updated=TO_DATE('2008-11-15 12:12:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE AD_User_ID=101
;




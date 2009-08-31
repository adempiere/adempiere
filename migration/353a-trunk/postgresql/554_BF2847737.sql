-- Aug 31, 2009 4:49:32 PM EEST
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53320,TO_TIMESTAMP('2009-08-31 16:49:32','YYYY-MM-DD HH24:MI:SS'),0,'Manufacturing Resources','EE01','Y','N','S_Resource_Manufacturing',TO_TIMESTAMP('2009-08-31 16:49:32','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Aug 31, 2009 4:49:32 PM EEST
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53320 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Aug 31, 2009 4:50:18 PM EEST
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,6853,6862,0,53320,487,53004,TO_TIMESTAMP('2009-08-31 16:50:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N',TO_TIMESTAMP('2009-08-31 16:50:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 31, 2009 4:52:06 PM EEST
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52059,'IsManufacturingResource=''Y''',TO_TIMESTAMP('2009-08-31 16:52:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','S_Resource_Manufacturing','S',TO_TIMESTAMP('2009-08-31 16:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 31, 2009 4:52:24 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320, AD_Val_Rule_ID=52059,Updated=TO_TIMESTAMP('2009-08-31 16:52:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53837
;

-- Aug 31, 2009 4:52:45 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:52:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54053
;

-- Aug 31, 2009 4:53:13 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:53:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53622
;

-- Aug 31, 2009 4:53:33 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320, AD_Val_Rule_ID=52059,Updated=TO_TIMESTAMP('2009-08-31 16:53:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53494
;

-- Aug 31, 2009 4:53:55 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320, AD_Val_Rule_ID=52059,Updated=TO_TIMESTAMP('2009-08-31 16:53:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53715
;

-- Aug 31, 2009 4:54:23 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:54:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- Aug 31, 2009 4:55:59 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:55:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57354
;

-- Aug 31, 2009 4:56:20 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:56:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57302
;

-- Aug 31, 2009 4:56:34 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:56:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57531
;

-- Aug 31, 2009 4:56:54 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:56:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57456
;

-- Aug 31, 2009 4:57:13 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:57:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53438
;

-- Aug 31, 2009 4:57:23 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:57:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53860
;

-- Aug 31, 2009 4:57:37 PM EEST
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53320,Updated=TO_TIMESTAMP('2009-08-31 16:57:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56493
;


-- Aug 31, 2009 4:36:51 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53319,TO_TIMESTAMP('2009-08-31 16:36:50','YYYY-MM-DD HH24:MI:SS'),0,'Manufacturing Workflows','EE01','Y','N','AD_Workflow_Manufacturing',TO_TIMESTAMP('2009-08-31 16:36:50','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Aug 31, 2009 4:36:51 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53319 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Aug 31, 2009 4:37:13 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,236,351,0,53319,117,53005,TO_TIMESTAMP('2009-08-31 16:37:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N',TO_TIMESTAMP('2009-08-31 16:37:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 31, 2009 4:38:15 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:38:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53533
;

-- Aug 31, 2009 4:38:32 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:38:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53684
;

-- Aug 31, 2009 4:39:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:39:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53456
;

-- Aug 31, 2009 4:39:22 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:39:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53691
;

-- Aug 31, 2009 4:39:41 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:39:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53377
;

-- Aug 31, 2009 4:41:04 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53319,Updated=TO_TIMESTAMP('2009-08-31 16:41:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53995
;


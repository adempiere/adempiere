-- 04-jun-2008 23:12:26 CDT
-- Fix Libero
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56076,56266,0,701,TO_TIMESTAMP('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'),0,'The low level cost is using to accumulation the cost for low level in a bill of material or formula.',22,'EE01','The low level cost is using to accumulation the cost for low level in a bill of material or formula.','Y','Y','Y','N','N','N','N','N','Current Cost Price Low Level',TO_TIMESTAMP('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 04-jun-2008 23:12:26 CDT
-- Fix Libero
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56266 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56266
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=11352
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12318
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12319
;

-- 04-jun-2008 23:14:39 CDT
-- Fix Libero
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-06-04 23:14:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=11352
;


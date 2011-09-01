-- Jul 22, 2010 6:13:43 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET SeqNo=30,Updated=TO_TIMESTAMP('2010-07-22 18:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58569
;

-- Jul 22, 2010 6:14:05 PM COT
-- Add zoom for used in tabs
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Reference_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,167,59576,0,19,53298,TO_TIMESTAMP('2010-07-22 18:14:04','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table',10,'D','The Field identifies a field on a database table.','Y','Y','Y','N','N','N','N','N','Field',20,0,TO_TIMESTAMP('2010-07-22 18:14:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 22, 2010 6:14:05 PM COT
-- Add zoom for used in tabs
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59576 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 22, 2010 6:14:14 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET DisplayLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:14:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58569
;

-- Jul 22, 2010 6:14:19 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET DisplayLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:14:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58568
;

-- Jul 22, 2010 6:14:32 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=4599
;

-- Jul 22, 2010 6:14:32 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=4574
;

-- Jul 22, 2010 6:14:41 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET AD_Reference_ID=19,Updated=TO_TIMESTAMP('2010-07-22 18:14:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4599
;

-- Jul 22, 2010 6:14:55 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET DisplayLength=10,Updated=TO_TIMESTAMP('2010-07-22 18:14:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58566
;

-- Jul 22, 2010 6:15:02 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET DisplayLength=10, SeqNo=30,Updated=TO_TIMESTAMP('2010-07-22 18:15:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58567
;

-- Jul 22, 2010 6:15:22 PM COT
-- Add zoom for used in tabs
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2814,59577,0,53297,TO_TIMESTAMP('2010-07-22 18:15:22','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Process Parameter',20,0,TO_TIMESTAMP('2010-07-22 18:15:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 22, 2010 6:15:22 PM COT
-- Add zoom for used in tabs
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59577 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 22, 2010 6:16:15 PM COT
-- Add zoom for used in tabs
UPDATE AD_Field SET AD_Reference_ID=19, IsMandatory=NULL,Updated=TO_TIMESTAMP('2010-07-22 18:16:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59577
;


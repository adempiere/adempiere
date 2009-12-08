-- Dec 7, 2009 7:06:33 PM COT
-- Problems with Payment Window (as Cash)
UPDATE AD_SysConfig SET Value='N',Updated=TO_DATE('2009-12-07 19:06:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50028
;

-- Dec 7, 2009 8:05:26 PM COT
-- Problems with Payment Window (as Cash)
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52078,TO_DATE('2009-12-07 20:05:25','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Tender Type - not Cash','S',TO_DATE('2009-12-07 20:05:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 7, 2009 8:05:39 PM COT
-- Problems with Payment Window (as Cash)
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''X''',Updated=TO_DATE('2009-12-07 20:05:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52078
;

-- Dec 7, 2009 8:05:49 PM COT
-- Problems with Payment Window (as Cash)
UPDATE AD_Column SET AD_Val_Rule_ID=52078,Updated=TO_DATE('2009-12-07 20:05:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9118
;

-- Dec 7, 2009 8:05:56 PM COT
-- Problems with Payment Window (as Cash)
UPDATE AD_Column SET AD_Val_Rule_ID=52078,Updated=TO_DATE('2009-12-07 20:05:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5046
;


-- Sep 13, 2009 5:37:29 PM COT
-- FR2857807-Allow accounting posting in standalone user mode
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50034,'C',TO_TIMESTAMP('2009-09-13 17:37:28','YYYY-MM-DD HH24:MI:SS'),100,'Enable client Accounting -> D - Disabled (default) / Q - Queue (enabled to post by hand - queue documents for posterior processing) / I - Immediate (immediate post)','D','Y','CLIENT_ACCOUNTING',TO_TIMESTAMP('2009-09-13 17:37:28','YYYY-MM-DD HH24:MI:SS'),100,'D')
;


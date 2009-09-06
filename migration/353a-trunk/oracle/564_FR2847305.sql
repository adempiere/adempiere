-- Sep 6, 2009 9:20:34 AM EEST
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50032,'C',TO_DATE('2009-09-06 09:20:32','YYYY-MM-DD HH24:MI:SS'),0,'Info windows - Specify if the records should be checked(selected) by default (multi selection mode only)','D','Y','INFO_DEFAULTSELECTED',TO_DATE('2009-09-06 09:20:32','YYYY-MM-DD HH24:MI:SS'),0,'N')
;

-- Sep 6, 2009 9:21:13 AM EEST
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50033,'C',TO_DATE('2009-09-06 09:21:12','YYYY-MM-DD HH24:MI:SS'),0,'Info Window - True if double click on a row toggles if row is selected (multi selection mode only)','D','Y','INFO_DOUBLECLICKTOGGLESSELECTION',TO_DATE('2009-09-06 09:21:12','YYYY-MM-DD HH24:MI:SS'),0,'50000')
;

-- Sep 6, 2009 9:21:22 AM EEST
UPDATE AD_SysConfig SET Value='N',Updated=TO_DATE('2009-09-06 09:21:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_SysConfig_ID=50033
;


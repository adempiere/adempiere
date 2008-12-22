-- Aug 25, 2008 5:23:17 PM EEST
-- 
UPDATE AD_Window SET Description='View MRP Notices',Updated=TO_DATE('2008-08-25 17:23:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53008
;

-- Aug 25, 2008 5:23:17 PM EEST
-- 
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53008
;

-- Aug 25, 2008 5:23:17 PM EEST
-- 
UPDATE AD_Menu SET Description='View MRP Notices', IsActive='Y', Name='MRP Notice',Updated=TO_DATE('2008-08-25 17:23:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53040
;

-- Aug 25, 2008 5:23:17 PM EEST
-- 
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53040
;

-- Aug 25, 2008 5:26:15 PM EEST
-- 
UPDATE AD_Tab SET WhereClause='(AD_User_ID IN (0,@#AD_User_ID@) OR AD_User_ID IS NULL) AND EXISTS (SELECT 1 FROM AD_Message m WHERE m.AD_Message_ID=AD_Note.AD_Message_ID AND m.Value like ''MRP-%'')',Updated=TO_DATE('2008-08-25 17:26:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53035
;


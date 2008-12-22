SET SQLBLANKLINES ON
-- Aug 5, 2008 9:09:09 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-08-05 21:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53220
;

-- Aug 5, 2008 9:09:14 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-08-05 21:09:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53240
;

-- Aug 5, 2008 9:12:37 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET AD_Element_ID=53470, ColumnName='IsRequiredDRP', Description='Based in DRP Demand', IsActive='Y', IsCentrallyMaintained='N', Name='Based in DRP Demand',Updated=TO_DATE('2008-08-05 21:12:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:12:37 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:12:54 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsCentrallyMaintained='Y',Updated=TO_DATE('2008-08-05 21:12:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53221
;

-- Aug 5, 2008 9:13:03 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET Help=NULL,Updated=TO_DATE('2008-08-05 21:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:13:03 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:23:05 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-08-05 21:23:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:23:17 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-08-05 21:23:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53219
;

-- Aug 5, 2008 9:23:24 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-08-05 21:23:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53221
;

-- Aug 5, 2008 9:24:13 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET AD_Val_Rule_ID=189,Updated=TO_DATE('2008-08-05 21:24:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53222
;

-- Aug 5, 2008 9:25:30 PM CDT
-- DRP Functionality
UPDATE AD_Process SET Help='Based in DRP Demand is selected then the  redistribute the quantity is based in % of Demand

Please note that due to rounding, the total quantity of the order(s) is likely to be higher then the quantity entered.

Consolidate to one Document let the distribution in current Documents with ranges dates promised, if do not is selected then a new Order is generate.',Updated=TO_DATE('2008-08-05 21:25:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53150
;

-- Aug 5, 2008 9:25:30 PM CDT
-- DRP Functionality
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53150
;


SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Sep 18, 2009 6:24:13 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_WM_InOutBoundLine_ID',Updated=TO_DATE('2009-09-18 18:24:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50351
;

-- Sep 18, 2009 6:24:13 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50351
;

-- Sep 18, 2009 6:24:54 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50352
;

-- Sep 18, 2009 6:25:10 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='N',Updated=TO_DATE('2009-09-18 18:25:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50352
;

-- Sep 18, 2009 6:25:16 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsDisplayed='N',Updated=TO_DATE('2009-09-18 18:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50352
;

-- Sep 18, 2009 6:25:27 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='Y',Updated=TO_DATE('2009-09-18 18:25:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50381
;

-- Sep 18, 2009 6:28:59 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='N',Updated=TO_DATE('2009-09-18 18:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50381
;

-- Sep 18, 2009 6:33:47 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='N',Updated=TO_DATE('2009-09-18 18:33:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50381
;

-- Sep 18, 2009 6:34:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='Y',Updated=TO_DATE('2009-09-18 18:34:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50351
;

-- Sep 18, 2009 9:48:27 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET AD_Reference_Value_ID=154,Updated=TO_DATE('2009-09-18 21:48:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50581
;

-- Sep 18, 2009 9:49:13 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Description=NULL, Name='WM_InOutBoundLine_WM_InOutBound_ID',Updated=TO_DATE('2009-09-18 21:49:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:49:13 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:50:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:50:33 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50549
;

-- Sep 18, 2009 9:51:03 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='WM_InOutBoundLine_WM_InOutBoundLine_ID',Updated=TO_DATE('2009-09-18 21:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50549
;

-- Sep 18, 2009 9:51:03 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50549
;

-- Sep 18, 2009 9:52:32 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:52:46 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='N',Updated=TO_DATE('2009-09-18 21:52:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:52:50 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsDisplayed='N',Updated=TO_DATE('2009-09-18 21:52:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50550
;

-- Sep 18, 2009 9:53:03 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsKey='Y',Updated=TO_DATE('2009-09-18 21:53:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=50549
;

-- Sep 18, 2009 10:31:02 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='iob.IsSOTrx=''Y'' AND iob.DocStatus=''CO'' AND iobl.PickedQty > iobl.MovementQty',Updated=TO_DATE('2009-09-18 22:31:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_ID=50002
;


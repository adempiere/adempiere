SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Sep 21, 2009 8:38:35 PM MST
UPDATE AD_Process_Para SET IsActive='Y',Updated=TO_DATE('2009-09-21 20:38:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53338
;

-- Sep 21, 2009 9:21:18 PM MST
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50352
;

-- Sep 21, 2009 9:21:18 PM MST
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50351
;

-- Sep 24, 2009 8:23:05 PM MST
UPDATE AD_Browse SET WhereClause='NOT EXISTS (SELECT 1 FROM M_InOutLine WHERE M_InOutLine.C_OrderLine_ID = iobl.C_OrderLine_ID AND iobl.PickedQty >= M_InOutLine.MovementQty)  AND iob.IsSOTrx=''Y''  AND iobl.PickedQty > 0',Updated=TO_DATE('2009-09-24 20:23:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50002
;

-- Sep 24, 2009 8:45:51 PM MST
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-09-24 20:45:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57558
;
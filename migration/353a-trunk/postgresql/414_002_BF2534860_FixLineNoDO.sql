-- Feb 6, 2009 1:06:43 PM ECT
-- Fix Distribution Order
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM DD_OrderLine WHERE DD_OrderLine_ID=@DD_Order_ID@',Updated=TO_TIMESTAMP('2009-02-06 13:06:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53926
;

-- Feb 6, 2009 1:11:43 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:11:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54033
;

-- Feb 6, 2009 1:12:10 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54029
;

-- Feb 6, 2009 1:12:25 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=54034
;

-- Feb 6, 2009 1:12:25 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=54033
;

-- Feb 6, 2009 1:12:36 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:12:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54010
;

-- Feb 6, 2009 1:12:40 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:12:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54031
;

-- Feb 6, 2009 1:12:45 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:12:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54037
;

-- Feb 6, 2009 1:12:51 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-06 13:12:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54039
;

-- Feb 6, 2009 1:24:14 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-02-06 13:24:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54029
;

-- Feb 6, 2009 5:45:49 PM ECT
-- Fix Distribution Order
UPDATE AD_Column SET AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2009-02-06 17:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53891
;

-- Feb 6, 2009 5:47:42 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET Description='The current status of the document', Help='The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field', IsReadOnly='Y', Name='DocStatus',Updated=TO_TIMESTAMP('2009-02-06 17:47:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54225
;

-- Feb 6, 2009 5:47:42 PM ECT
-- Fix Distribution Order
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54225
;

-- Feb 6, 2009 5:50:07 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET Description='Document Action', Help='You find the current status in the Document Status field. The options are listed in a popup', Name='DocAction',Updated=TO_TIMESTAMP('2009-02-06 17:50:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54225
;

-- Feb 6, 2009 5:50:07 PM ECT
-- Fix Distribution Order
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54225
;

-- Feb 6, 2009 5:50:48 PM ECT
-- Fix Distribution Order
UPDATE AD_Column SET AD_Process_ID=53042, AD_Reference_ID=28, AD_Reference_Value_ID=135,Updated=TO_TIMESTAMP('2009-02-06 17:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53891
;

-- Feb 6, 2009 5:52:12 PM ECT
-- Fix Distribution Order
UPDATE AD_Field SET IsReadOnly='N',Updated=TO_TIMESTAMP('2009-02-06 17:52:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54225
;


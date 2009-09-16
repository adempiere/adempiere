-- Sep 14, 2009 9:34:00 PM COT
-- FR1756793 - RMA Feature
UPDATE AD_Column SET Callout='org.compiere.model.CalloutInOut.rma',Updated=TO_DATE('2009-09-14 21:34:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52009
;

-- Sep 14, 2009 9:34:39 PM COT
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-09-14 21:34:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57847
;

-- Sep 14, 2009 9:34:49 PM COT
UPDATE AD_Field SET DisplayLogic='@C_Order_ID@!0',Updated=TO_DATE('2009-09-14 21:34:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57845
;

-- Sep 14, 2009 9:34:51 PM COT
UPDATE AD_Field SET DisplayLogic='@C_Order_ID@!0',Updated=TO_DATE('2009-09-14 21:34:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57846
;

-- Sep 14, 2009 9:35:15 PM COT
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-09-14 21:35:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57713
;

-- Sep 14, 2009 9:43:31 PM COT
UPDATE AD_Field SET DisplayLogic='@MovementType@=''V-''',Updated=TO_DATE('2009-09-14 21:43:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57875
;

-- Sep 14, 2009 9:49:38 PM COT
UPDATE AD_Table SET PO_Window_ID=53099,Updated=TO_DATE('2009-09-14 21:49:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=661
;

-- Sep 14, 2009 9:49:43 PM COT
UPDATE AD_Table SET PO_Window_ID=53099,Updated=TO_DATE('2009-09-14 21:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=660
;

-- Sep 14, 2009 10:17:19 PM COT
UPDATE AD_Field SET IsReadOnly='N',Updated=TO_DATE('2009-09-14 22:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57713
;

-- Sep 14, 2009 10:18:53 PM COT
UPDATE AD_Column SET Callout='org.compiere.model.CalloutInOut.rmaLine',Updated=TO_DATE('2009-09-14 22:18:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52010
;

-- Sep 14, 2009 10:19:02 PM COT
UPDATE AD_Field SET AD_Column_ID=52010, Description='Return Material Authorization Line', DisplayLogic=NULL, Help='Detail information about the returned goods', IsReadOnly='N', Name='RMA Line',Updated=TO_DATE('2009-09-14 22:19:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57762
;

-- Sep 14, 2009 10:19:02 PM COT
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57762
;

-- Sep 14, 2009 10:19:31 PM COT
UPDATE AD_Field SET AD_Column_ID=52010, Description='Return Material Authorization Line', Help='Detail information about the returned goods', Name='RMA Line',Updated=TO_DATE('2009-09-14 22:19:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57901
;

-- Sep 14, 2009 10:19:31 PM COT
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57901
;

-- Sep 14, 2009 10:25:37 PM COT
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52067,'(IsSOTrx=''@IsSOTrx@'' AND DocStatus=''CO'')',TO_DATE('2009-09-14 22:25:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','M_RMA in M_InOut (Complete and IsSOTrx)','S',TO_DATE('2009-09-14 22:25:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 14, 2009 10:26:08 PM COT
UPDATE AD_Column SET AD_Reference_ID=30, AD_Val_Rule_ID=52067,Updated=TO_DATE('2009-09-14 22:26:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52009
;

-- Sep 14, 2009 10:27:34 PM COT
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52068,'M_RMALine.M_RMA_ID=@M_RMA_ID@',TO_DATE('2009-09-14 22:27:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','M_RMALine of RMA','S',TO_DATE('2009-09-14 22:27:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 14, 2009 10:27:51 PM COT
UPDATE AD_Column SET AD_Val_Rule_ID=52068,Updated=TO_DATE('2009-09-14 22:27:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52010
;

-- Sep 14, 2009 10:32:53 PM COT
UPDATE AD_Column SET ReadOnlyLogic=NULL,Updated=TO_DATE('2009-09-14 22:32:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52006
;

-- Sep 14, 2009 10:33:30 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57965
;

-- Sep 14, 2009 10:33:42 PM COT
UPDATE AD_Field SET DisplayLogic='@Processed@=''Y'' & @C_Order_ID@=0 & @DocStatus@=''CO'' & @IsSOTrx@=''N''',Updated=TO_DATE('2009-09-14 22:33:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57965
;

-- Sep 14, 2009 10:33:52 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57964
;

-- Sep 14, 2009 10:33:52 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57965
;


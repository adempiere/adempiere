-- Feb 20, 2009 2:35:16 PM EET
-- 
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-02-20 14:35:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53038
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53744
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53742
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53732
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53745
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53743
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53733
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=53734
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=53735
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53736
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53737
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53738
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53739
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53740
;

-- Feb 20, 2009 2:36:11 PM EET
-- 
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53741
;

-- Feb 20, 2009 2:36:15 PM EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53739
;

-- Feb 20, 2009 2:36:15 PM EET
-- 
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53740
;

-- Feb 20, 2009 2:36:16 PM EET
-- 
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53741
;

-- Feb 20, 2009 2:37:13 PM EET
-- 
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2009-02-20 14:37:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53547
;

-- Feb 20, 2009 2:37:26 PM EET
-- 
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2009-02-20 14:37:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53549
;

-- Feb 20, 2009 2:38:02 PM EET
-- 
UPDATE AD_Field SET SortNo=1.000000000000,Updated=TO_DATE('2009-02-20 14:38:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53736
;

-- Feb 20, 2009 2:38:17 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10, SortNo=2.000000000000,Updated=TO_DATE('2009-02-20 14:38:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53737
;

-- Feb 20, 2009 2:38:22 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53734
;

-- Feb 20, 2009 2:38:28 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53735
;

-- Feb 20, 2009 2:38:33 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53736
;

-- Feb 20, 2009 2:38:41 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53738
;

-- Feb 20, 2009 2:38:47 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53740
;

-- Feb 20, 2009 2:38:54 PM EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-20 14:38:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53741
;

update PP_Order_Cost c set
M_CostType_ID=(select mas.M_CostType_ID from C_AcctSchema mas where mas.C_AcctSchema_ID=c.C_AcctSchema_ID)
where M_CostType_ID is null;

-- Feb 20, 2009 3:10:48 PM EET
-- 
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-02-20 15:10:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53548
;

-- Feb 20, 2009 3:10:51 PM EET
-- 
ALTER TABLE PP_Order_Cost MODIFY M_CostType_ID NUMBER(10)
;

-- Feb 20, 2009 3:10:51 PM EET
-- 
ALTER TABLE PP_Order_Cost MODIFY M_CostType_ID NOT NULL
;


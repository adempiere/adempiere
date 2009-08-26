-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=440
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=939
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=441
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=442
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=443
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=1553
;

-- Fix Replication strategy position
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56981
;

-- Fix Replication strategy position
UPDATE AD_Column SET AD_Reference_ID=19,Updated=TO_DATE('2009-08-26 11:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57227
;

